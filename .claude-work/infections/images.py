#!/usr/bin/env python3
"""Fetch and licence-check the photos named in the Gemini infection drafts.

For each photo: read the Commons file title from the draft's "Source Page" link, ask the Wikimedia Commons API
for the file's own licence and author (extmetadata), and keep the photo only if Commons lists it as public domain,
CC0, CC BY or CC BY-SA (no NC/ND). The credit and licence shown in the app come from Commons, not from the draft.
Kept photos are resized to 640 px wide JPEG and written to composeResources/drawable/<resName>.jpg; results go to
images.json for convert.py.

Usage: python3 -I images.py <repo-root>
"""
import html, io, json, re, sys, time, urllib.parse, urllib.request
from pathlib import Path
from PIL import Image

ROOT = Path(sys.argv[1])
SRC = ROOT / ".claude-work/infections"
DRAW = ROOT / "composeApp/src/commonMain/composeResources/drawable"
UA = {"User-Agent": "Medicalaid-build/1.0 (offline clinical reference app; licence check)"}
OK_LICENCE = re.compile(r"^(public domain|pd\b|cc0|cc by(-sa)? \d(\.\d)?)", re.I)


def get(url: str) -> bytes:
    for i in range(4):
        try:
            with urllib.request.urlopen(urllib.request.Request(url, headers=UA), timeout=60) as r:
                return r.read()
        except Exception:
            if i == 3:
                raise
            time.sleep(2 ** i)


def text(v) -> str:
    v = html.unescape(re.sub(r"<[^>]+>", "", v or ""))
    return re.sub(r"\s+", " ", v).strip()


photos = []
for md in sorted(SRC.glob("gemini-batch*.md")):
    for block in re.split(r"^### Clinical Photo", md.read_text(), flags=re.M)[1:]:
        block = block.split("\n### ")[0]
        res = re.search(r"`resName`\):\*\* `([a-z0-9_]+)`", block)
        page = re.search(r"Source Page:\*\* \[[^\]]*\]\((https://commons\.wikimedia\.org/wiki/(File:(?:[^()\s]|\([^()\s]*\))+))\)", block)
        if res:
            photos.append((res.group(1), page.group(1) if page else None, urllib.parse.unquote(page.group(2)) if page else None))

DRAW.mkdir(parents=True, exist_ok=True)
prev = json.loads((SRC / "images.json").read_text()) if (SRC / "images.json").exists() else {}
out = {}


def clean_credit(a: str) -> str:
    a = re.sub(r"^(Photo Credit:\s*)?", "", a)
    a = re.sub(r"Content Provider(\(s\)|s\(s\)|s)?:\s*", "", a)
    a = re.sub(r"\s*Author info.*$", "", a)
    if a.startswith("http"):
        a = "CDC DPDx" if "cdc.gov" in a else "See source page"
    return a.strip(" -;,")[:160]


for res, page, title in photos:
    if prev.get(res, {}).get("ok") and (DRAW / f"{res}.jpg").exists():
        out[res] = dict(prev[res], credit=clean_credit(prev[res]["credit"]))
        continue
    time.sleep(4)
    if not title:
        out[res] = {"ok": False, "why": "no Commons file page in draft"}
        continue
    q = "https://commons.wikimedia.org/w/api.php?" + urllib.parse.urlencode({
        "action": "query", "titles": title, "prop": "imageinfo", "iiprop": "extmetadata|url", "iiurlwidth": 640,
        "format": "json", "redirects": 1}).replace("iiurlwidth=640", "iiurlwidth=960")
    try:
        pages = json.loads(get(q))["query"]["pages"]
        info = next(iter(pages.values())).get("imageinfo", [None])[0]
    except Exception as e:
        out[res] = {"ok": False, "why": f"lookup failed: {e}"}
        continue
    if not info:
        out[res] = {"ok": False, "why": "file not found on Commons"}
        continue
    meta = info.get("extmetadata", {})
    lic = text(meta.get("LicenseShortName", {}).get("value"))
    if not OK_LICENCE.match(lic) or re.search(r"\b(nc|nd)\b", lic, re.I):
        out[res] = {"ok": False, "why": f"licence not acceptable: {lic or 'none'}"}
        continue
    artist = text(meta.get("Artist", {}).get("value")) or text(meta.get("Credit", {}).get("value")) or "Unknown author"
    try:
        img = Image.open(io.BytesIO(get(info.get("thumburl") or info["url"]))).convert("RGB")
        if img.width > 640:
            img = img.resize((640, round(img.height * 640 / img.width)), Image.LANCZOS)
        img.save(DRAW / f"{res}.jpg", "JPEG", quality=80, optimize=True)
    except Exception as e:
        out[res] = {"ok": False, "why": f"download failed: {e}"}
        continue
    out[res] = {"ok": True, "credit": clean_credit(artist), "licence": lic, "page": info.get("descriptionurl") or page}

(SRC / "images.json").write_text(json.dumps(out, indent=1, ensure_ascii=False) + "\n")
print(json.dumps({"photos": len(photos), "kept": sum(v["ok"] for v in out.values()),
                  "dropped": {k: v["why"] for k, v in out.items() if not v["ok"]}}, indent=1, ensure_ascii=False))
