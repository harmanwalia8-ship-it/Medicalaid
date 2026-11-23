#!/usr/bin/env python3
"""Convert a Gemini protocol batch (format in .claude-work/gemini-prompts/*.md) into a Kotlin protocol data file.

Compiled as written (user decision, 23 Nov 2026): the text is only re-shaped. Links are stripped out of point/note
text and become the step's/note's citations (the app shows them as a source list). A source whose URL already
exists in the app reuses that Source id; others become new Sources in the generated file. `Dose` lines become
StepDose calculators only in the exact "label | N(–M) unit/kg per … | max X unit | note" form.

Usage:
  python3 -I convert_protocols.py <repo> <batch.md> <OutFile.kt> <sourcesVal> <protocolsVal>
  python3 -I convert_protocols.py <repo> <batch.md> --rewrite        (replace existing protocols in their own files)
"""
import json, re, sys
from pathlib import Path

ROOT = Path(sys.argv[1])
BATCH = Path(sys.argv[2])
CONTENT = ROOT / "composeApp/src/commonMain/kotlin/com/medicalaid/app/content"
DATA = CONTENT / "protocols/data"
URL = r"https?://(?:[^()\s]|\([^()\s]*\))+"
LINK = re.compile(r"\[([^\]]*)\]\((" + URL + r")\)")
CATEGORIES = {"Cardiac", "Respiratory", "Neuro", "Metabolic", "Infection", "Obstetric", "Paediatric", "Trauma", "Environmental", "Poisoning"}
# Optional 6th argument forces one category for the whole batch (e.g. Paediatric for the child emergency batches).
FORCE_CATEGORY = sys.argv[6] if len(sys.argv) > 6 else None
TOOLS = {"OnsetClock", "ThrombolysisDose", "BurnsChart", "AcidBase"}
AUTH = set(re.findall(r"^\s{4}(\w+)\(", (CONTENT / "Model.kt").read_text().split("enum class Authority")[1].split("}")[0], re.M))
SCORES = set(re.findall(r'^\s*id = "([a-z0-9-]+)"', "\n".join(p.read_text() for p in (CONTENT / "scores/data").glob("*.kt")), re.M))
report = {"warnings": []}


def warn(m):
    report["warnings"].append(m)


def norm(u):
    return u.split("#")[0].rstrip("/").replace("http://", "https://")


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


# Existing app sources: id and URL from every Source(...) declaration under content/.
existing_by_url, existing_ids = {}, set()
SELF = (DATA / sys.argv[3]).resolve() if sys.argv[3] != "--rewrite" else None
for f in CONTENT.rglob("*.kt"):
    if SELF is not None and f.resolve() == SELF:
        continue  # the file being regenerated must keep its own sources
    txt = f.read_text()
    for m in re.finditer(r"Source\(\s*\"([a-z0-9-]+)\",\s*Authority\.\w+(.*?)\"(https://[^\"]+)\"", txt, re.S):
        sid, url = m.group(1), m.group(3)
        if m.group(2).count("Source(") == 0:
            existing_ids.add(sid)
            existing_by_url.setdefault(norm(url), sid)

md = BATCH.read_text()
srcs = []
for sid, auth, doc, title, year, url in re.findall(
        r"^\| `([a-z0-9-]+)` \| `?(\w+)`? \| (.*?) \| (.*?) \| (.*?) \| \[[^\]]*\]\((" + URL + r")\) \|$", md, re.M):
    srcs.append((sid, auth, plain(doc), plain(title), plain(year), url))
url_to_id, new_sources = {}, []
for sid, auth, doc, title, year, url in srcs:
    if norm(url) in existing_by_url:
        url_to_id[norm(url)] = existing_by_url[norm(url)]
    elif sid in existing_ids:
        url_to_id[norm(url)] = sid
    else:
        if auth not in AUTH:
            warn(f"source {sid}: authority {auth} not in app; using Paper")
            auth = "Paper"
        url_to_id[norm(url)] = sid
        new_sources.append((sid, auth, doc, title, year, url))


def cites(text, where):
    out = []
    for label, url in LINK.findall(text):
        sid = url_to_id.get(norm(url)) or existing_by_url.get(norm(url))
        if not sid:
            warn(f"{where}: link not in Sources table: {url}")
            continue
        lab = plain(label)
        loc = lab.split(" — ", 1)[1].strip() if " — " in lab else None
        if (sid, loc) not in out:
            out.append((sid, loc))
    return out


def c_list(cs):
    return "listOf(" + ", ".join(f"cite({kt(s)}{', ' + kt(l) if l else ''})" for s, l in cs) + ")"


def parse_dose(line, where):
    parts = [p.strip() for p in line.split("|")]
    if len(parts) != 4:
        warn(f"{where}: dose line not in 4-part form, kept as text: {line}")
        return None
    label, spec, mx, note = parts
    m = re.match(r"^(\d+(?:\.\d+)?)(?:\s*[–-]\s*(\d+(?:\.\d+)?))?\s*(mg|mcg|µg|g|mL|IU|units|mmol|mEq)/kg\b\s*(.*)$", plain(spec))
    if not m or plain(spec).count("/kg") != 1:
        warn(f"{where}: dose not a simple per-kg figure, kept as text: {spec}")
        return None
    lo, hi, unit, per = float(m.group(1)), float(m.group(2) or m.group(1)), m.group(3), m.group(4).strip() or "per dose"
    cap = None
    mm = re.match(r"^max\s+([\d,.]+)\s*(\S+)", plain(mx))
    if mm:
        val, munit = float(mm.group(1).replace(",", "")), mm.group(2).rstrip(".,;")
        mass = {"mcg": 0.001, "µg": 0.001, "mg": 1.0, "g": 1000.0}
        if munit == unit:
            cap = val
        elif munit in mass and unit in mass:
            cap = round(val * mass[munit] / mass[unit], 6)  # e.g. max 1 mg for a mcg/kg dose -> 1000 mcg
        else:
            note = (note + f"; max {mm.group(1)} {mm.group(2)}").strip("; ")
            warn(f"{where}: max in {munit} cannot cap a {unit}/kg dose; kept as text")
    return (plain(label), lo, hi, unit, per, strip_links(note) or None, cap)


protocols = []
for block in re.split(r"^(?=## Protocol: )", md, flags=re.M)[1:]:
    block = block.split("\n## Sources")[0].split("\n## Possible issues")[0]
    pid = block.split("\n", 1)[0].replace("## Protocol:", "").strip()
    fld = lambda n: plain((re.search(rf"^- \*\*{n}:\*\* (.*)$", block, re.M) or [None, ""])[1])
    cat = FORCE_CATEGORY or fld("Category")
    if cat not in CATEGORIES:
        warn(f"{pid}: category {cat} unknown; using Obstetric")
        cat = "Obstetric"
    p = {"id": pid, "title": fld("Title"), "summary": fld("Summary"), "category": cat,
         "keywords": re.findall(r"`([^`]+)`", (re.search(r"^- \*\*Keywords:\*\* (.*)$", block, re.M) or [None, ""])[1]),
         "steps": [], "notes": []}
    for sec in re.split(r"^### ", block, flags=re.M)[1:]:
        head, _, body = sec.partition("\n")
        if head.startswith("Note"):
            for line in re.findall(r"^- (.*)$", body, re.M):
                nc = cites(line, f"{pid} note")
                if not nc:
                    warn(f"{pid}: note without citation dropped: {line[:80]}")
                    continue
                p["notes"].append((strip_links(line), nc))
            continue
        title = plain(head.replace("Step:", ""))
        st = {"title": title, "timing": None, "points": [], "ctitle": None, "checklist": [], "scores": [], "tool": None,
              "doses": [], "cites": []}
        mode = None
        for line in body.split("\n"):
            m = re.match(r"^- \*\*(Timing|Points|Checklist|Scores|Tool|Dose|Citations):\*\*\s*(.*)$", line)
            if m:
                key, val = m.groups()
                mode = key
                if key == "Timing":
                    st["timing"] = strip_links(val)
                elif key == "Checklist":
                    st["ctitle"] = plain(val) or "Checklist"
                elif key == "Scores":
                    for s in re.findall(r"`([a-z0-9-]+)`", val):
                        (st["scores"].append(s) if s in SCORES else warn(f"{pid}: unknown score {s} dropped"))
                elif key == "Tool":
                    t = plain(val)
                    (st.__setitem__("tool", t) if t in TOOLS else warn(f"{pid}: unknown tool {t}"))
                elif key == "Dose":
                    d = parse_dose(val, f"{pid}/{title}")
                    if d:
                        st["doses"].append(d)
                    else:
                        st["points"].append(strip_links(val.replace(" | ", "; ")))
                    for c in cites(val, pid):
                        st["cites"].append(c)
                elif key == "Citations":
                    st["cites"] += cites(val, f"{pid}/{title}")
                continue
            m = re.match(r"^\s+- (.*)$", line)
            if m and mode == "Points":
                st["points"].append(strip_links(m.group(1)))
                st["cites"] += cites(m.group(1), f"{pid}/{title}")
            elif m and mode == "Checklist":
                st["checklist"].append(strip_links(m.group(1)))
                st["cites"] += cites(m.group(1), f"{pid}/{title}")
        st["cites"] = list(dict.fromkeys(st["cites"]))
        if not st["cites"]:
            warn(f"{pid}/{title}: step has no citation")
        p["steps"].append(st)
    protocols.append(p)


def emit_protocol(p):
    L = ["    Protocol(", f"        id = {kt(p['id'])},", f"        title = {kt(p['title'])},",
         f"        summary = {kt(p['summary'])},",
         "        keywords = listOf(" + ", ".join(kt(k) for k in p["keywords"]) + "),",
         f"        category = ProtocolCategory.{p['category']},", "        steps = listOf("]
    allc = []
    for s in p["steps"]:
        L.append("            Step(")
        L.append(f"                title = {kt(s['title'])},")
        if s["timing"]:
            L.append(f"                timing = {kt(s['timing'])},")
        L.append("                points = listOf(" + "".join(f"\n                    {kt(x)}," for x in s["points"]) + "\n                ),")
        if s["checklist"]:
            L.append(f"                checklistTitle = {kt(s['ctitle'])},")
            L.append("                checklist = listOf(" + "".join(f"\n                    {kt(x)}," for x in s["checklist"]) + "\n                ),")
        if s["tool"]:
            L.append(f"                tool = ProtocolTool.{s['tool']},")
        if s["scores"]:
            L.append("                scoreIds = listOf(" + ", ".join(kt(x) for x in s["scores"]) + "),")
        if s["doses"]:
            L.append("                doses = listOf(")
            for label, lo, hi, unit, per, note, cap in s["doses"]:
                extra = (f", note = {kt(note)}" if note else "") + (f", maxTotal = {cap}" if cap is not None else "")
                L.append(f"                    StepDose({kt(label)}, WeightDose({lo}, {hi}, {kt(unit)}, {kt(per)}){extra}),")
            L.append("                ),")
        L.append(f"                citations = {c_list(s['cites'])},")
        L.append("            ),")
        allc += [c for c in s["cites"] if c[0] not in [a for a, _ in allc]]
    L.append("        ),")
    L.append("        notes = listOf(" + "".join(f"\n            ProtocolNote({kt(t)}, {c_list(c)})," for t, c in p["notes"]) + "\n        ),")
    L.append(f"        citations = {c_list([(s, None) for s in dict.fromkeys(a for a, _ in allc)])},")
    L.append("    ),")
    return "\n".join(L)


def used_ids(ps):
    u = set()
    for p in ps:
        for s in p["steps"]:
            u |= {a for a, _ in s["cites"]}
        for _, c in p["notes"]:
            u |= {a for a, _ in c}
    return u


HEADER = """package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.WeightDose
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.ProtocolCategory
import com.medicalaid.app.content.protocols.ProtocolNote
import com.medicalaid.app.content.protocols.ProtocolTool
import com.medicalaid.app.content.protocols.Step
import com.medicalaid.app.content.protocols.StepDose
"""

if sys.argv[3] != "--rewrite":
    out, sval, pval = DATA / sys.argv[3], sys.argv[4], sys.argv[5]
    used = used_ids(protocols)
    keep = [s for s in new_sources if s[0] in used]
    body = [HEADER, f"// GENERATED by .claude-work/gemini-protocols/convert_protocols.py from {BATCH.name} (Gemini).",
            "// Compiled as written at the user's request: not independently reviewed. Edit the Markdown and re-run.", "",
            f"internal val {sval}: List<Source> = listOf("]
    for sid, auth, doc, title, year, url in keep:
        body.append(f"    Source({kt(sid)}, Authority.{auth}, {kt(doc)}, {kt(title)}, {kt(year)}, {kt(url)}),")
    body += [")", "", f"internal val {pval}: List<Protocol> = listOf("]
    body += [emit_protocol(p) for p in protocols]
    body.append(")")
    out.write_text("\n".join(body) + "\n")
    report["written"] = str(out.relative_to(ROOT))
report["protocols"] = [(p["id"], len(p["steps"]), len(p["notes"]), sum(len(s["doses"]) for s in p["steps"])) for p in protocols]
report["new_sources"] = len(new_sources)
report["reused_sources"] = len(srcs) - len(new_sources)
print(json.dumps(report, indent=1, ensure_ascii=False))
