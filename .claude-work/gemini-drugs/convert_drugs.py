#!/usr/bin/env python3
"""Convert a Gemini drug batch (Format A in .claude-work/gemini-prompts/medicines.md) into a Kotlin drug data file.

Compiled as written (user decision): sections become Facts with their links as citations; `Dose` lines in Children
become StepDose calculators only in the exact "label | N(–M) unit/kg per … | max X unit[/day|/dose] | note" form, and
a maximum caps the calculator only when it is per the same thing (per day vs per dose). Each drug is emitted as its
own function so no JVM method grows past 64 KB.

Usage: python3 -I convert_drugs.py <repo> <batch.md> <OutFile.kt> <prefix>   e.g. d1.md GeminiDrugsD1.kt D1
"""
import json, re, sys
from pathlib import Path

ROOT, BATCH, OUT, TAG = Path(sys.argv[1]), Path(sys.argv[2]), sys.argv[3], sys.argv[4]
CONTENT = ROOT / "composeApp/src/commonMain/kotlin/com/medicalaid/app/content"
OUTFILE = CONTENT / "drugs/data" / OUT
URL = r"https?://(?:[^()\s]|\([^()\s]*\))+"
LINK = re.compile(r"\[([^\]]*)\]\((" + URL + r")\)")
AUTH = set(re.findall(r"^\s{4}(\w+)\(", (CONTENT / "Model.kt").read_text().split("enum class Authority")[1].split("}")[0], re.M))
SECTIONS = {"Boxed warning": "boxedWarning", "Dose": "dose", "Children": "children", "Before starting": "beforeStarting",
            "Monitoring": "monitoring", "Kidney": "renal", "Liver": "hepatic", "Common side effects": "commonEffects",
            "Serious side effects": "seriousEffects", "Interactions": "interactions", "Pregnancy": "pregnancy",
            "Breastfeeding": "lactation"}
report = {"warnings": []}
warn = lambda m: report["warnings"].append(m)
norm = lambda u: u.split("#")[0].rstrip("/").replace("http://", "https://")


def plain(t):
    t = t.replace("**", "").replace("`", "")
    t = re.sub(r"(?<![\w*])\*(?!\s)([^*]+?)\*(?![\w*])", r"\1", t)
    return re.sub(r"\s+", " ", t).strip()


def strip_links(t):
    t = LINK.sub("", t)
    t = re.sub(r"\(\s*[;,]?\s*(?:[;,]\s*)*\)", "", t)
    t = re.sub(r"\s+([.,;:])", r"\1", t)
    return plain(t).rstrip(" ;,")


def kt(s):
    return '"' + s.replace("\\", "\\\\").replace('"', '\\"').replace("$", "\\$") + '"'


# Existing app sources (reuse by URL), excluding the file being regenerated.
existing_by_url, existing_ids = {}, set()
for f in CONTENT.rglob("*.kt"):
    if f.resolve() == OUTFILE.resolve():
        continue
    for m in re.finditer(r"Source\(\s*\"([a-z0-9-]+)\",\s*Authority\.\w+(.*?)\"(https://[^\"]+)\"", f.read_text(), re.S):
        if "Source(" not in m.group(2):
            existing_ids.add(m.group(1))
            existing_by_url.setdefault(norm(m.group(3)), m.group(1))

md = BATCH.read_text()
url_to_id, new_sources = {}, []
for sid, auth, doc, title, year, url in re.findall(
        r"^\| `?([a-z0-9-]+)`? \| `?(\w+)`? \| (.*?) \| (.*?) \| (.*?) \| \[[^\]]*\]\((" + URL + r")\) \|$", md, re.M):
    if norm(url) in existing_by_url:
        url_to_id[norm(url)] = existing_by_url[norm(url)]
        continue
    if norm(url) in url_to_id:
        continue
    if auth not in AUTH:
        warn(f"source {sid}: authority {auth} not in app; using Paper")
        auth = "Paper"
    if sid in existing_ids or any(s[0] == sid for s in new_sources):
        sid = f"{sid}-{TAG.lower()}"
    url_to_id[norm(url)] = sid
    new_sources.append((sid, auth, plain(doc), plain(title), plain(year), url, None))


def domain_authority(url):
    for key, a in (("dailymed", "FDA"), ("fda.gov", "FDA"), ("who.int", "WHO"), ("icmr", "ICMR"), ("mohfw", "MoHFW"),
                   ("nice.org", "NICE"), ("cdc.gov", "CDC"), ("idsociety", "IDSA")):
        if key in url:
            return a
    return "Paper"


def cites(text, where):
    out = []
    for label, url in LINK.findall(text):
        sid = url_to_id.get(norm(url)) or existing_by_url.get(norm(url))
        if not sid:  # cited inline but missing from the batch's table: keep it as a labelled source
            sid = f"dr-{TAG.lower()}-x{len(new_sources) + 1}"
            lab = plain(label).split(" — ")[0] or "Source"
            new_sources.append((sid, domain_authority(url), "Web page", lab, "", url,
                                "Cited in the Gemini draft but missing from its source table; title from its link text"))
            url_to_id[norm(url)] = sid
            warn(f"{where}: link not in Sources table, added as {sid}")
        lab = plain(label)
        loc = lab.split(" — ", 1)[1].strip() if " — " in lab else None
        if (sid, loc) not in out:
            out.append((sid, loc))
    return out


def c_list(cs):
    return "listOf(" + ", ".join(f"cite({kt(s)}{', ' + kt(l) if l else ''})" for s, l in cs) + ")"


MASS = {"mcg": 0.001, "µg": 0.001, "mg": 1.0, "g": 1000.0}


def parse_dose(line, where):
    parts = [p.strip() for p in line.split("|")]
    if len(parts) != 4:
        return None
    label, spec, mx, note = parts
    m = re.match(r"^(\d+(?:\.\d+)?)(?:\s*[–-]\s*(\d+(?:\.\d+)?))?\s*(mg|mcg|µg|g|mL|IU|units|mmol|mEq)/kg\b\s*(.*)$", plain(spec))
    if not m or plain(spec).count("/kg") != 1:
        return None
    lo, hi, unit, per = float(m.group(1)), float(m.group(2) or m.group(1)), m.group(3), m.group(4).strip(" ,") or "per dose"
    per_scope = "day" if re.search(r"per day|/day|daily", per, re.I) else "dose"
    cap = None
    mm = re.match(r"^max\s+([\d,.]+)\s*([a-zA-Zµ]+)(?:/(day|dose|24 ?h))?", plain(mx))
    note = strip_links(note)
    if mm:
        val, munit, scope = float(mm.group(1).replace(",", "")), mm.group(2), (mm.group(3) or "").replace("24h", "day").replace("24 h", "day")
        scope = scope or per_scope
        same = munit == unit or (munit in MASS and unit in MASS)
        if same and scope == per_scope:
            cap = val if munit == unit else round(val * MASS[munit] / MASS[unit], 6)
        else:
            note = (note + f"; max {mm.group(1)} {mm.group(2)}{'/' + mm.group(3) if mm.group(3) else ''}").strip("; ")
            if same:
                warn(f"{where}: max is per {scope} but dose is per {per_scope}; kept as text")
    return (plain(label), lo, hi, unit, per, note or None, cap)


drugs = []
for block in re.split(r"^(?=## Drug: )", md.split("\n## Sources")[0], flags=re.M)[1:]:
    did = block.split("\n", 1)[0].replace("## Drug:", "").strip()
    fld = lambda n: (re.search(rf"^- \*\*{n}:\*\* (.*)$", block, re.M) or [None, ""])[1]
    d = {"id": did, "name": plain(fld("Name")), "aliases": re.findall(r"`([^`]+)`", fld("Aliases")),
         "cls": plain(fld("Class")), "uses": plain(fld("Uses")),
         "clearance": [c.strip() for c in plain(fld("Clearance")).split(",") if c.strip() in ("Renal", "Hepatic", "Biliary")],
         "facts": {}, "doses": [], "pk": None}
    for sec in re.split(r"^### ", block, flags=re.M)[1:]:
        head, _, body = sec.partition("\n")
        head = head.strip()
        if head == "Pharmacokinetics":
            joined = " ".join(l.strip() for l in body.split("\n") if l.strip())
            pk = {}
            for key in ("Absorption", "Distribution", "Metabolism", "Elimination", "Half-life", "Citations"):
                mm = re.search(rf"\*\*{key}:\*\*\s*(.*?)(?=\s-?\s*\*\*(?:Absorption|Distribution|Metabolism|Elimination|Half-life|Citations):\*\*|$)", joined)
                if mm:
                    pk[key] = mm.group(1).strip(" -")
            pc = cites(joined, f"{did} PK")
            d["pk"] = ({k: strip_links(v) for k, v in pk.items() if k != "Citations"}, pc)
            continue
        key = SECTIONS.get(head)
        if not key:
            warn(f"{did}: unknown section '{head}' skipped")
            continue
        items, cs = [], []
        for line in re.findall(r"^- (.*)$", body, re.M):
            if line.startswith("**Dose:**"):
                dose = parse_dose(line[len("**Dose:**"):].strip(), f"{did}/{head}")
                if dose and key == "children":
                    d["doses"].append(dose)
                else:
                    items.append(strip_links(line.replace("**Dose:**", "").replace(" | ", "; ")))
                for c in cites(line, did):
                    if c not in cs:
                        cs.append(c)
                continue
            items.append(strip_links(line))
            for c in cites(line, f"{did}/{head}"):
                if c not in cs:
                    cs.append(c)
        if items and not cs:
            warn(f"{did}/{head}: no citation; section dropped")
            continue
        if items:
            d["facts"][key] = (items, cs)
    for req in ("commonEffects", "seriousEffects"):
        if req not in d["facts"]:
            warn(f"{did}: required section {req} missing")
    if not d["pk"] or not d["pk"][1]:
        warn(f"{did}: pharmacokinetics missing citations")
    drugs.append(d)


def facts(v):
    items, cs = v
    return "Facts(listOf(" + "".join(f"\n            {kt(i)}," for i in items) + f"\n        ), {c_list(cs)})"


fn = lambda did: "drug" + "".join(p.capitalize() for p in re.split(r"[^a-z0-9]+", did))
L = ["package com.medicalaid.app.content.drugs.data", "",
     "import com.medicalaid.app.content.Authority", "import com.medicalaid.app.content.Source",
     "import com.medicalaid.app.content.WeightDose", "import com.medicalaid.app.content.cite",
     "import com.medicalaid.app.content.drugs.Clearance", "import com.medicalaid.app.content.drugs.DrugMonograph",
     "import com.medicalaid.app.content.drugs.Facts", "import com.medicalaid.app.content.drugs.Pharmacokinetics",
     "import com.medicalaid.app.content.protocols.StepDose", "",
     f"// GENERATED by .claude-work/gemini-drugs/convert_drugs.py from {BATCH.name} (Gemini). Compiled as written at the",
     "// user's request: not independently reviewed. Edit the Markdown and re-run; do not edit by hand.", ""]
used = set()
for d in drugs:
    for _, cs in d["facts"].values():
        used |= {s for s, _ in cs}
    if d["pk"]:
        used |= {s for s, _ in d["pk"][1]}
L.append(f"internal val drugs{TAG}Sources: List<Source> = listOf(")
for sid, auth, doc, title, year, url, status in new_sources:
    if sid in used:
        st = f", status = {kt(status)}" if status else ""
        L.append(f"    Source({kt(sid)}, Authority.{auth}, {kt(doc)}, {kt(title)}, {kt(year)}, {kt(url)}{st}),")
L += [")", "", f"internal val drugs{TAG}: List<DrugMonograph> = listOf(" + ", ".join(f"{fn(d['id'])}()" for d in drugs) + ")", ""]
for d in drugs:
    pkv, pkc = d["pk"] or ({}, [])
    pkargs = ", ".join(f"{k} = {kt(pkv[n])}" for k, n in (("absorption", "Absorption"), ("distribution", "Distribution"),
                       ("metabolism", "Metabolism"), ("elimination", "Elimination"), ("halfLife", "Half-life")) if n in pkv)
    L.append(f"private fun {fn(d['id'])}() = DrugMonograph(")
    L.append(f"    id = {kt(d['id'])}, name = {kt(d['name'])},")
    L.append("    aliases = listOf(" + ", ".join(kt(a) for a in d["aliases"]) + "),")
    L.append(f"    drugClass = {kt(d['cls'])},")
    L.append(f"    uses = {kt(d['uses'])},")
    L.append("    clearance = setOf(" + ", ".join(f"Clearance.{c}" for c in d["clearance"]) + "),")
    for key in ("boxedWarning", "dose", "children", "beforeStarting", "monitoring", "renal", "hepatic",
                "commonEffects", "seriousEffects", "interactions", "pregnancy", "lactation"):
        if key in d["facts"]:
            L.append(f"    {key} = {facts(d['facts'][key])},")
        elif key in ("commonEffects", "seriousEffects"):
            L.append(f"    {key} = Facts(emptyList(), emptyList()),")
    if d["doses"]:
        L.append("    childDoses = listOf(")
        for label, lo, hi, unit, per, note, cap in d["doses"]:
            extra = (f", note = {kt(note)}" if note else "") + (f", maxTotal = {cap}" if cap is not None else "")
            L.append(f"        StepDose({kt(label)}, WeightDose({lo}, {hi}, {kt(unit)}, {kt(per)}){extra}),")
        L.append("    ),")
    L.append(f"    pk = Pharmacokinetics({pkargs}{', ' if pkargs else ''}citations = {c_list(pkc)}),")
    L.append(")")
    L.append("")
OUTFILE.write_text("\n".join(L) + "\n")
report["drugs"] = len(drugs)
report["child_dose_calculators"] = sum(len(d["doses"]) for d in drugs)
report["new_sources"] = len([s for s in new_sources if s[0] in used])
print(json.dumps(report, indent=1, ensure_ascii=False))
