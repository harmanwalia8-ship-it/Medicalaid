#!/usr/bin/env python3
"""Convert the user's Gemini "Infections A–Z" Markdown batches into Kotlin condition pages.

Content is compiled as written (user decision, 23 Nov 2026): no clinical rewording. The script only
re-shapes the Markdown into the app's Condition model, maps inline links to Source ids from each batch's
reference table, and turns a weight-based dose into a calculator only when it is a single, simple
"N(–M) unit/kg ..." expression. Photo licence/credit come from images.json (Wikimedia Commons metadata),
not from the Markdown; a photo without verified metadata is dropped.

Usage: python3 -I convert.py <repo-root>
"""
import json, re, sys
from pathlib import Path

ROOT = Path(sys.argv[1])
SRC_DIR = ROOT / ".claude-work/infections"
OUT_DIR = ROOT / "composeApp/src/commonMain/kotlin/com/medicalaid/app/content/infections"
IMAGES = json.loads((SRC_DIR / "images.json").read_text()) if (SRC_DIR / "images.json").exists() else {}

BATCHES = [
    ("gemini-batch1-A-C.md", "AC"), ("gemini-batch2-D-H.md", "DH"), ("gemini-batch3-H-L.md", "HL"),
    ("gemini-batch4-L-R.md", "LR"), ("gemini-batch5-S-Z.md", "SZ"),
]
AUTHORITY = {"MoHFW": "MoHFW", "ICMR": "ICMR", "NCDC": "NCDC", "NCVBDC": "NCVBDC", "NTEP": "NTEP", "IAP": "IAP",
             "ISCCM": "ISCCM", "WHO": "WHO", "CDC": "CDC", "IDSA": "IDSA", "FDA": "FDA", "RCOG": "RCOG", "Paper": "Paper"}

URL = r"https?://(?:[^()\s]|\([^()\s]*\))+"
LINK = re.compile(r"\[([^\]]*)\]\((" + URL + r")\)")
EXTRA_SOURCES = {
    "LR": [("inf-lr-who-malaria-treatment", "WHO", "Web page (Global Malaria Programme)", "Malaria case management: treatment", "2026",
            "https://www.who.int/teams/global-malaria-programme/case-management/treatment")],
    "SZ": [("inf-sz-ntep-dstb-treatment", "NTEP", "NTEP Knowledge Base", "DS-TB treatment and care", "2026",
            "https://ntep.in/node/2865/sts-ds-tb-treatment-and-care"),
           ("inf-sz-who-afro-ebola", "WHO", "WHO AFRO Health Topic", "Ebola disease", "2026",
            "https://www.afro.who.int/health-topics/ebola-disease"),
           ("inf-sz-cdc-zika-causes", "CDC", "Web page", "How Zika spreads", "2026",
            "https://www.cdc.gov/zika/causes/index.html")],
}
EXTRA_STATUS = "Cited in the Gemini draft but missing from its source table; title from the draft's link text"
# Info blocks the draft left without any link: cite the disease's main investigation source, saying so.
UNCITED_INFO_FALLBACK = {("inf-filariasis", "Other Investigations & Key Findings"):
                         ("inf-dh-ncvbdc-elf-2024", "draft gives no source for this block")}
report = {"unmapped_links": [], "uncited": [], "weightdose_text_only": [], "photos_dropped": []}


def norm(u: str) -> str:
    return u.split("#")[0].rstrip("/").replace("http://", "https://")


def plain(t: str) -> str:
    """Strip Markdown emphasis/code marks, keep the words."""
    t = t.replace("**", "").replace("`", "")
    t = re.sub(r"(?<![\w*])\*(?!\s)([^*]+?)\*(?![\w*])", r"\1", t)  # *italic*
    return re.sub(r"\s+", " ", t).strip()


def kt(s: str) -> str:
    return '"' + s.replace("\\", "\\\\").replace('"', '\\"').replace("$", "\\$") + '"'


class Batch:
    def __init__(self, md: str, tag: str):
        self.tag, self.text = tag, md
        self.sources = []  # (id, authority, document, title, year, url)
        self.url_to_id = {}
        for row in re.findall(r"^\| `([a-z0-9-]+)` \| `?([A-Za-z/&-]+)`? \| (.*?) \| (.*?) \| (.*?) \| \[[^\]]*\]\((" + URL + r")\) \|$", md, re.M):
            sid, auth, doc, title, year, url = row
            if auth not in AUTHORITY:
                raise SystemExit(f"Unknown authority {auth} in {sid}")
            self.sources.append((sid, AUTHORITY[auth], plain(doc), plain(title), plain(year), url))
            self.url_to_id.setdefault(norm(url), sid)
        self.status = {}
        for sid, auth, doc, title, year, url in EXTRA_SOURCES.get(tag, []):
            self.sources.append((sid, AUTHORITY[auth], doc, title, year, url))
            self.url_to_id.setdefault(norm(url), sid)
            self.status[sid] = EXTRA_STATUS

    def cites(self, text: str, where: str):
        out = []
        for label, url in LINK.findall(text):
            sid = self.url_to_id.get(norm(url))
            if sid is None:
                report["unmapped_links"].append(f"{self.tag} {where}: {url}")
                continue
            loc = None
            lab = plain(label)
            for sep in (" — ", " – ", " - "):
                if sep in lab:
                    loc = lab.split(sep, 1)[1].strip()
                    break
            if loc is None:
                m = re.search(r"(§\s?[\w.–-]+|Table [\w.–-]+|Ch(?:apter)?\.? ?[\w.–-]+|p\. ?\d+)", lab)
                loc = m.group(1) if m else None
            c = (sid, loc)
            if c not in out:
                out.append(c)
        return out

    @staticmethod
    def strip_links(text: str) -> str:
        t = LINK.sub(lambda m: "", text)
        t = re.sub(r"\(\s*[;,]?\s*(?:[;,]\s*)*\)", "", t)  # empty "( ; )" left by removed links
        t = re.sub(r"\s+([.,;:])", r"\1", t)
        return plain(t).rstrip(" ;,")


def weight_dose(spec: str):
    s = plain(spec)
    if s.count("/kg") != 1:
        return None
    m = re.match(r"^(\d+(?:\.\d+)?)(?:\s*[–-]\s*(\d+(?:\.\d+)?))?\s*(mg|mcg|µg|g|mL|IU|units)/kg\b\s*(.*)$", s)
    if not m:
        return None
    lo = float(m.group(1)); hi = float(m.group(2) or m.group(1))
    return lo, hi, m.group(3), m.group(4).strip() or "per dose"


def parse_disease(b: Batch, block: str):
    title = plain(re.match(r"## \d+\.\s*(.*)", block).group(1))
    field = lambda name: (re.search(rf"^- \*\*{re.escape(name)}:\*\* (.*)$", block, re.M) or [None, None])[1]
    cid = plain(field("Condition ID"))
    summary = plain(field("Summary"))
    keywords = re.findall(r"`([^`]+)`", field("Search Keywords") or "")
    sections = re.split(r"^### ", block, flags=re.M)[1:]
    cond = {"id": cid, "title": title, "summary": summary, "keywords": keywords, "firstSteps": [], "info": [],
            "tiers": [], "avoid": [], "escalate": [], "image": None, "cites": []}
    allc = cond["cites"]

    def addc(cs):
        for c in cs:
            if c not in allc:
                allc.append(c)

    for sec in sections:
        head, _, body = sec.partition("\n")
        head = head.strip()
        if head == "Clinical Photo":
            res = (re.search(r"`resName`\):\*\* `([a-z0-9_]+)`", body) or [None, None])[1]
            cap = (re.search(r"^- \*\*Caption:\*\* (.*)$", body, re.M) or [None, None])[1]
            meta = IMAGES.get(res or "")
            if res and cap and meta and meta.get("ok"):
                cond["image"] = (res, plain(cap), meta["credit"], meta["licence"], meta["page"])
            elif res:
                report["photos_dropped"].append(f"{cid}: {res} ({(meta or {}).get('why', 'no metadata')})")
        elif head == "First Steps":
            for line in re.findall(r"^\d+\.\s+(.*)$", body, re.M):
                cond["firstSteps"].append(Batch.strip_links(line)); addc(b.cites(line, f"{cid} first step"))
        elif head == "Treatment Tiers":
            for tier in re.split(r"^#### ", body, flags=re.M)[1:]:
                thead, _, tbody = tier.partition("\n")
                label = plain(re.sub(r"^Tier \d+:\s*", "", thead.strip()))
                note = (re.search(r"^> \*Note:\*\s*(.*)$", tbody, re.M) or [None, None])[1]
                drugs = []
                for d in re.split(r"^- \*\*", tbody, flags=re.M)[1:]:
                    dname_line, _, dbody = d.partition("\n")
                    dname = plain(dname_line.rstrip("*").rstrip())
                    f = dict(re.findall(r"^\s+- \*\*([^*]+?):\*\* (.*)$", dbody, re.M))
                    regimen = f.pop("Regimen", "")
                    maxd = f.pop("Max Dose", None)
                    wd_spec = f.pop("Weight-based calculator (`WeightDose`)", None)
                    cites_line = f.pop("Citations", "")
                    caution_text = f.pop("Cautions", None)
                    dc = b.cites(cites_line + " " + regimen + " " + (caution_text or ""), f"{cid}/{dname}")
                    cautions = [Batch.strip_links(caution_text)] if caution_text else []
                    for k, v in f.items():
                        cautions.append(f"{plain(k)}: {Batch.strip_links(v)}")
                    wd = weight_dose(wd_spec) if wd_spec else None
                    if wd_spec and wd is None:
                        report["weightdose_text_only"].append(f"{cid}/{dname}: {plain(wd_spec)}")
                        cautions.append("Weight-based dosing: " + plain(wd_spec))
                    if not dc:
                        report["uncited"].append(f"{cid} drug {dname}")
                    addc(dc)
                    drugs.append({"name": dname, "regimen": Batch.strip_links(regimen), "max": plain(maxd) if maxd else None,
                                  "wd": wd, "cautions": [c for c in cautions if c], "cites": dc})
                if drugs:
                    cond["tiers"].append({"label": label, "note": Batch.strip_links(note) if note else None, "drugs": drugs})
        elif head == "Avoid":
            for name, reason in re.findall(r"^- \*\*(.+?):\*\*\s*(.*)$", body, re.M):
                ac = b.cites(reason, f"{cid} avoid")
                if not ac:
                    report["uncited"].append(f"{cid} avoid '{plain(name)}'")
                addc(ac)
                cond["avoid"].append({"name": plain(name), "reason": Batch.strip_links(reason), "cites": ac})
        elif head.startswith("Escalate"):
            for line in re.findall(r"^- (.*)$", body, re.M):
                cond["escalate"].append(Batch.strip_links(line)); addc(b.cites(line, f"{cid} escalate"))
        else:
            points, ic = [], []
            for line in re.findall(r"^\s*- (.*)$", body, re.M):
                points.append(Batch.strip_links(line))
                for c in b.cites(line, f"{cid} info"):
                    if c not in ic:
                        ic.append(c)
            if points:
                t = "Investigation of choice (IOC)" if head.startswith("Screening & Investigation") else plain(head)
                if not ic and (cid, plain(head)) in UNCITED_INFO_FALLBACK:
                    ic = [UNCITED_INFO_FALLBACK[(cid, plain(head))]]
                if not ic:
                    report["uncited"].append(f"{cid} info '{t}'")
                addc(ic)
                cond["info"].append({"title": t, "points": points, "cites": ic})
    return cond


def c_list(cs):
    return "listOf(" + ", ".join(f"cite({kt(s)}{', ' + kt(l) if l else ''})" for s, l in cs) + ")"


def emit(b: Batch, conds):
    used = {s for c in conds for s, _ in c["cites"]}
    for c in conds:
        for t in c["tiers"]:
            for d in t["drugs"]:
                used |= {s for s, _ in d["cites"]}
        for a in c["avoid"]:
            used |= {s for s, _ in a["cites"]}
        for i in c["info"]:
            used |= {s for s, _ in i["cites"]}
    unused = [s[0] for s in b.sources if s[0] not in used]
    if unused:
        report.setdefault("unused_sources", []).extend(unused)
    L = ["// GENERATED by .claude-work/infections/convert.py from the user's Gemini draft " +
         f"({[f for f, t in BATCHES if t == b.tag][0]}). Do not edit by hand; edit the Markdown and re-run.",
         "// Compiled as written at the user's request (23 Nov 2026): not independently reviewed.",
         "package com.medicalaid.app.content", "",
         f"internal val infections{b.tag}Sources: List<Source> = listOf("]
    for sid, auth, doc, title, year, url in b.sources:
        if sid in used:
            st = f", status = {kt(b.status[sid])}" if sid in b.status else ""
            L.append(f"    Source({kt(sid)}, Authority.{auth}, {kt(doc)}, {kt(title)}, {kt(year)}, {kt(url)}{st}),")
    L += [")", "", f"internal val infections{b.tag}Conditions: List<Condition> = listOf("]
    for c in conds:
        L.append("    Condition(")
        L.append(f"        id = {kt(c['id'])}, title = {kt(c['title'])},")
        L.append(f"        summary = {kt(c['summary'])},")
        L.append("        keywords = listOf(" + ", ".join(kt(k) for k in c["keywords"]) + "),")
        L.append("        population = Population.Infections,")
        if c["image"]:
            r, cap, cr, li, pg = c["image"]
            L.append(f"        image = ConditionImage({kt(r)}, {kt(cap)}, {kt(cr)}, {kt(li)}, {kt(pg)}),")
        L.append("        firstSteps = listOf(" + "".join(f"\n            {kt(s)}," for s in c["firstSteps"]) + "\n        ),")
        L.append("        info = listOf(")
        for i in c["info"]:
            L.append(f"            InfoBlock({kt(i['title'])}, listOf(" + "".join(f"\n                {kt(p)}," for p in i["points"]) +
                     f"\n            ), {c_list(i['cites'])}),")
        L.append("        ),")
        L.append("        tiers = listOf(")
        for t in c["tiers"]:
            L.append(f"            Tier({kt(t['label'])}, {kt(t['note']) if t['note'] else 'null'}, listOf(")
            for d in t["drugs"]:
                wd = ""
                if d["wd"]:
                    lo, hi, unit, per = d["wd"]
                    wd = f", weightDose = WeightDose({lo}, {hi}, {kt(unit)}, {kt(per)})"
                mx = f", maxDose = {kt(d['max'])}" if d["max"] else ""
                ca = ", cautions = listOf(" + ", ".join(kt(x) for x in d["cautions"]) + ")" if d["cautions"] else ""
                L.append(f"                Drug({kt(d['name'])}, {kt(d['regimen'])}{mx}{wd}{ca}, citations = {c_list(d['cites'])}),")
            L.append("            )),")
        L.append("        ),")
        L.append("        avoid = listOf(" + "".join(
            f"\n            Caution({kt(a['name'])}, {kt(a['reason'])}, {c_list(a['cites'])})," for a in c["avoid"]) + "\n        ),")
        L.append("        escalate = listOf(" + "".join(f"\n            {kt(e)}," for e in c["escalate"]) + "\n        ),")
        L.append(f"        citations = {c_list(c['cites'])},")
        L.append("    ),")
    L.append(")")
    return "\n".join(L) + "\n"


OUT_DIR.mkdir(parents=True, exist_ok=True)
total = 0
for fname, tag in BATCHES:
    md = (SRC_DIR / fname).read_text()
    b = Batch(md, tag)
    body = md.split("## Primary Sources")[0]
    blocks = re.split(r"^(?=## \d+\. )", body, flags=re.M)[1:]
    conds = [parse_disease(b, blk.split("\n---")[0]) for blk in blocks]
    total += len(conds)
    (OUT_DIR / f"Infections{tag}.kt").write_text(emit(b, conds))
print(json.dumps({"conditions": total, **{k: v for k, v in report.items()}}, indent=1, ensure_ascii=False))
