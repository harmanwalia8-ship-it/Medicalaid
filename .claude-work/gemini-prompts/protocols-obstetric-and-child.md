# Prompt for Gemini — Medicalaid protocols (obstetric batch 2, then child emergencies)

Paste everything below the line into Gemini, with the repo zip attached. Ask for one batch per reply
("Do batch OB-A"), then upload each reply to Claude as a `.md` file.

---

You are writing clinical **emergency protocols** for **Medicalaid**, an offline Kotlin Multiplatform reference app
for emergency, casualty, labour-room and paediatric doctors in India (Punjab), from district hospitals to medical
colleges. The repo zip is attached; the protocol model is in
`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/protocols/ProtocolModel.kt` and a complete example
is `content/protocols/data/Stroke.kt`. Your Markdown will be converted to Kotlin by a script, so **follow the
output format exactly**.

## Content rules
1. **India first**: MoHFW / NHM (SBA, LaQshya, Dakshata, MNH toolkit, antenatal corticosteroid operational
   guidelines, facility-based newborn care, IMNCI, SAM guidelines), ICMR Standard Treatment Workflows, NCVBDC,
   IAP, FOGSI, NNF. Then WHO, NICE, RCOG, ACOG, SMFM, ERC/AHA 2025, ISPAD, BTS/SIGN, as relevant. Use the
   **latest edition** (check for 2024–2026 updates).
2. **Write it like a quick-reference card, as in the Infections A–Z pages.** Short, direct statements a busy doctor
   can act on, e.g. "Give MgSO4 4 g IV over 15–20 min, then 1 g/h IV". **Do not name the guideline in the sentence**:
   put the source link at the end of the line — the app turns the links into a tappable source list under each
   step, so the text stays clean. Name sources in the text **only** where two guidelines genuinely disagree on that
   point and it changes management ("RCOG: …; WHO: …"), and explain it in a Note.
3. **Verify every dose, rate, threshold, time window and gestational/weight cut-off in the primary document.**
   Never fill from memory; if you cannot verify something, leave it out.
4. Do not copy the proprietary **ATLS** or **APLS** manuals; use openly published guidelines.
5. Keep it lean: 4–8 steps per protocol, 2–5 points per step, each point ≤ 20 words; summary one line. Use `Timing`
   for time-critical steps and a `Checklist` for criteria, contraindications, red flags or don'ts. **Notes**: at most
   3 per protocol, only for real disagreements or important caveats — no history, no evidence grading, no trial
   details.
6. **Weight-based doses** go in `Dose` lines (they become calculators) **only** when the dose is one simple
   per-kg figure or range for one drug. Anything compound (loading + maintenance, multi-drug, by-age bands) goes
   in a point as text instead.
7. Link existing app scores only by these ids: `qsofa gcs nihss abcd2 rosier cha2ds2-vasc has-bled wells-dvt
   wells-pe perc heart timi-ua-nstemi curb-65 news2 alvarado air glasgow-blatchford child-pugh centor-mcisaac bishop
   ottawa-ankle ottawa-foot canadian-ct-head canadian-c-spine nexus-c-spine` (NEWS2 is not for use in pregnancy).
8. Stay consistent with protocols already in the app and refer to them by title where relevant: Postpartum
   haemorrhage, Eclampsia, Severe pre-eclampsia, Maternal collapse, Trauma in pregnancy, Ectopic pregnancy,
   Miscarriage and septic abortion, Hyperemesis gravidarum, Sepsis and septic shock, DKA, Anaphylaxis, Status
   epilepticus, Acute severe asthma, Severe dengue, Severe malaria, Bacterial meningitis, Tetanus prophylaxis.
9. Keywords: abbreviations, Hindi/Punjabi lay terms and Indian brand names — only ones you can verify.

## Output format (exact)
```
# Batch <BATCH-ID>

## Protocol: <protocol-id>
- **Title:** <title>
- **Summary:** <≤ 10 words, a list-row subtitle, e.g. "Atropine titration, pralidoxime, ventilation">
- **Category:** <Obstetric | Neuro | Respiratory | Metabolic | Infection | Cardiac>
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
- **Dose:** <Drug — purpose> | <min>–<max> <mg|mcg|g|mL|IU|units|mmol|mEq>/kg <per … e.g. "per dose IV over 20 min"> | max <number> <unit> | <route/rate/dilution note>   (optional, repeatable; write "max —" if no cap)
- **Citations:** [Label — locator](https://url), [Label — locator](https://url)

### Note
- <only a real disagreement or key caveat, ≤ 30 words> ([Label — locator](https://url))

## Sources
| Source ID | Authority | Document | Title | Year | URL |
|---|---|---|---|---|---|
| `<prefix>-<short>` | `MoHFW` | <document type/number> | <title> | <year> | [site](https://full.url) |
```
- **Authority** must be one of: `MoHFW ICMR NCDC NCVBDC NTEP IAP FOGSI ISCCM NDMA WHO NICE RCOG ACOG SMFM FIGO SOGC
  CDC FDA AHA ERC RCUK ESC IDSA GINA BTS ILAE Paper`.
- Every URL used in a link must appear in the Sources table (exact same URL). Full https URLs, no shorteners.
- The **locator** (after " — " in the link label) is the section/table/recommendation/page, e.g. "Rec 12", "Table 3",
  "§4.2", "p. 37". It appears only in the source list, never in the sentence.
- Every Step has a `Citations` line; every Note has at least one link. Source IDs use the batch prefix given below.
- Write `Dose` lines only in the exact `|`-separated form above.

## Batches (do one per reply)

### OB-A (prefix `ob-aph-`)
1. `antepartum-haemorrhage` — causes; no digital vaginal examination until placenta praevia is excluded; resuscitation
   and blood; fetal assessment; abruption (DIC risk, delivery decisions); praevia by bleeding severity and gestation;
   vasa praevia; uterine rupture signs; anti-D and Kleihauer; corticosteroids if preterm. RCOG GTG 63 and 27a/b,
   MoHFW (SBA/LaQshya/MNH), FOGSI, WHO MCPC 2017.
2. `preterm-labour` — diagnosis; antenatal corticosteroids (gestational window, dose, interval, conditions — WHO 2022,
   MoHFW operational guidelines); tocolysis (when, nifedipine regimens, contraindications); magnesium sulfate for
   fetal neuroprotection (gestation threshold, regimen); PPROM antibiotics (erythromycin); NICE NG25, ACOG.

### OB-B (prefix `ob-lab-`)
1. `cord-prolapse` — recognition; relieving compression (manual elevation, positioning, bladder filling); avoid
   handling the cord; delivery route and timing; community/transfer actions. RCOG GTG 50, WHO MCPC.
2. `shoulder-dystocia` — help, McRoberts, suprapubic pressure, internal manoeuvres, posterior arm, all-fours; no
   fundal pressure or excessive traction; neonatal team; documentation. RCOG GTG 42, ACOG PB 178, WHO MCPC.
3. `uterine-inversion` — recognition, immediate manual replacement, uterotonic timing (stop until replaced, then
   give), tocolysis/anaesthesia to aid replacement, shock and PPH management, surgical options. WHO MCPC, RCOG.
4. `retained-placenta` — time definitions; uterotonic and controlled cord traction; manual removal with
   analgesia/anaesthesia and antibiotic prophylaxis; what WHO advises against. WHO PPH recommendations, RCOG GTG 52,
   MoHFW.

### OB-C (prefix `ob-mm-`)
1. `maternal-sepsis` — WHO definition; red flags and obstetric early-warning scores (MEOWS, as text); antibiotics
   within the sourced time target, empirical regimens and doses; source control; fluids/vasopressors; fetal
   considerations. WHO 2017, RCOG GTG 64a/64b, SMFM 2023, MoHFW/ICMR.
2. `vte-in-pregnancy` — suspected DVT/PE imaging order (compression ultrasound, CXR, V/Q vs CTPA); start LMWH while
   awaiting results; treatment-dose LMWH by booking weight (Dose line if a single per-kg figure); prophylactic doses
   by weight band (as text); massive PE; peripartum and neuraxial timing. RCOG GTG 37a/37b, ESC 2019, ASH 2018.
3. `dka-in-pregnancy` — lower glucose thresholds and euglycaemic DKA; fetal monitoring; fluids, insulin and potassium
   with pregnancy-specific points; involve obstetric team. JBDS-IP DKA in pregnancy, ADA 2024 hyperglycaemic crises.

### CH-A (prefix `ped-`; Category as fits each protocol) — child emergencies, part 1
India first: IAP, MoHFW (facility-based newborn care, IMNCI, SAM), ICMR STWs, NNF; then WHO Pocket Book of Hospital
Care for Children, ERC/AHA 2025 paediatric life support, NICE, ISPAD 2022. Weight-based doses with maxima.
1. `paediatric-arrest-seriously-ill-child` — ABCDE, weight estimation, arrest drugs and defibrillation energy.
2. `neonatal-resuscitation` — IAP NRP / NSSK.
3. `paediatric-sepsis` — recognition, fluids by weight, antibiotics, inotropes.
4. `paediatric-status-epilepticus-febrile-seizures`.
5. `paediatric-dka` — ISPAD 2022 fluid deficit, insulin, cerebral oedema.

### CH-B (prefix `ped-`) — child emergencies, part 2
1. `acute-asthma-child`, 2. `bronchiolitis`, 3. `croup`, 4. `dehydration-diarrhoea-child` (WHO plans A/B/C, ORS,
zinc), 5. `sam-with-complications` (F-75/F-100, hypoglycaemia, hypothermia), 6. `anaphylaxis-dengue-malaria-child`
(child-specific doses and fluids).
