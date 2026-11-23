# Prompt for Gemini — rewrite the existing Medicalaid protocols in quick-reference style

Paste everything below the line into Gemini, attach `medicalaid-protocols-current.zip` (sent by Claude; it has the
current protocol files), and ask for one batch per reply ("Do batch R1"). Upload each reply to Claude as a `.md` file.

---

Medicalaid is an offline **quick-reference** app for emergency, casualty and labour-room doctors in India — not a
research app. The attached zip has the current protocol files (`protocols/data/*.kt`), the model
(`protocols/ProtocolModel.kt`, `Model.kt`) and `Sources.kt`. The protocols are accurate and fully sourced but too
wordy: long attributions in every sentence, evidence grades, trial details. **Rewrite each protocol in the lean
style below, without changing its clinical content.** Your Markdown will be converted to Kotlin by a script, so
follow the output format exactly.

## Rewrite rules
1. **Keep every clinical fact that matters at the bedside**: every drug, dose, route, rate, interval, maximum, time
   window, threshold/cut-off, contraindication, red flag, monitoring requirement, antidote and "do not" item. Numbers
   must stay exactly as written. A script will compare every number in your version with the original.
2. **Cut only wording**: guideline names inside sentences, evidence grades ("2C", "Level III"), trial names and
   results, history, repeated statements, and "X recommends / Y suggests" phrasing.
3. Short, direct statements a busy doctor can act on, ≤ 20 words each, e.g. "Give MgSO4 4 g IV over 15–20 min, then
   1 g/h IV". **Do not name the guideline in the sentence** — put the source link at the end of the line; the app
   shows links as a tappable source list. Name sources in the text **only** where guidelines disagree in a way that
   changes management (e.g. "India: within 3 h; NICE/BOAST: within 1 h"); explain it in a Note.
4. 4–8 steps per protocol, 2–5 points per step, summary one line. Keep `Timing`, `Checklist`, `Scores`, `Tool` and
   weight-based `Dose` lines (with their caps) from the original. **Notes**: at most 3 per protocol, only real
   disagreements or key caveats, ≤ 30 words each. Drop the rest of the notes.
5. **Keep exactly**: each protocol's `id`, `title`, category, and any cross-references to other protocols by title.
   Keep keywords (you may trim duplicates).
6. **Sources**: reuse the Sources already cited by that protocol — the same Source **id** and **URL** as in the
   Kotlin file (the URL is how links are matched). Keep the most specific locator (section/table/page) in the link
   label after " — ". Do not add new sources.
7. Do not add new clinical content. If you think something is wrong or outdated, do not change it — list it under a
   final heading `## Possible issues` (protocol id, the text, why, source).

## Output format (exact)
```
# Batch <BATCH-ID>

## Protocol: <protocol-id>
- **Title:** <title>
- **Summary:** <one line, ≤ 20 words>
- **Category:** <same category as in the Kotlin file>
- **Keywords:** `kw1`, `kw2`, `kw3`

### Step: <step title>
- **Timing:** <time target>                         (optional)
- **Points:**
  - <short direct statement> ([Short label — locator](https://full.url))
  - <statement> ([Label — locator](https://url); [Label2 — locator](https://url2))
- **Checklist:** <checklist title>                   (optional)
  - <item>
  - <item>
- **Scores:** `qsofa`, `gcs`                          (optional)
- **Tool:** <OnsetClock | ThrombolysisDose | BurnsChart | AcidBase>   (only if the Kotlin step has `tool =`)
- **Dose:** <Drug — purpose> | <min>–<max> <mg|mcg|g|mL|IU|units|mmol|mEq>/kg <per … e.g. "per dose IV over 20 min"> | max <number> <unit> | <route/rate/dilution note>   (optional, repeatable; write "max —" if no cap)
- **Citations:** [Label — locator](https://url), [Label — locator](https://url)

### Note
- <only a real disagreement or key caveat, ≤ 30 words> ([Label — locator](https://url))

## Sources
| Source ID | Authority | Document | Title | Year | URL |
|---|---|---|---|---|---|
| `<existing source id from the Kotlin file>` | `MoHFW` | <document> | <title> | <year> | [site](https://same.url.as.in.kotlin) |
```
- **Authority** must be one of: `MoHFW ICMR NCDC NCVBDC NTEP IAP FOGSI ISCCM NDMA WHO NICE RCOG ACOG SMFM FIGO SOGC
  CDC FDA AHA ERC RCUK ESC IDSA GINA BTS ILAE Paper`.
- Every URL used in a link must appear in the Sources table (exact same URL). Full https URLs, no shorteners.
- The **locator** (after " — " in the link label) is the section/table/recommendation/page, e.g. "Rec 12", "Table 3",
  "§4.2", "p. 37". It appears only in the source list, never in the sentence.
- Every Step has a `Citations` line; every Note has at least one link. Source IDs use the batch prefix given below.
- Write `Dose` lines only in the exact `|`-separated form above.

## Batches (one per reply; files are in `protocols/data/`)
- **R1 Poisoning A**: HouseholdTox.kt (4), Pesticides.kt (5), Envenomation.kt (2)
- **R2 Poisoning B**: DrugTox.kt (4), ToxAlcohols.kt (3), Paraquat.kt, Datura.kt, Oleander.kt, Cocaine.kt (1 each)
- **R3 Cardiac & resuscitation**: CardiacEmergencies.kt (5), Resuscitation.kt (3), Stroke.kt (1)
- **R4 Respiratory & neuro**: RespiratoryEmergencies.kt (4), NeuroEmergencies.kt (4)
- **R5 Metabolic**: EmergencyEndocrine.kt (6), AcidBaseDisorders.kt (4), Electrolytes.kt (3)
- **R6 Infection, bleeding & environmental**: InfectionBleeding.kt (4), Environmental.kt (3)
- **R7 Obstetric**: Eclampsia.kt (2), PostpartumHaemorrhage.kt (1), MaternalCollapse.kt (2), EarlyPregnancy.kt (3)
- **R8 Trauma**: Trauma.kt (4), TraumaHeadSpine.kt (2), WoundsBites.kt (2), TraumaLimbAbdomen.kt (3)
Start each reply with `# Batch R<n>`, then one `## Protocol:` section per protocol, then one `## Sources` table
for the batch, then `## Possible issues` (or "None").
