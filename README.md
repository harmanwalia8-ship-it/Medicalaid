# Medicalaid

A quick-reference app for clinicians — **decision support, not a replacement for clinical judgement.**
Every recommendation shows the guideline it comes from and links to the official document.

Built with Kotlin Multiplatform + Compose Multiplatform: one shared codebase for Android, iOS and desktop.
Works fully offline and stores no patient data.

## What's in it

Organised by clinical situation (0.7.0). Side menu: **Home** (search everything, Pinned, Recent), **Emergency**
(protocols by category with chips, bedside tools: GCS, qSOFA, CT-head and C-spine rules, burns chart, ABG, HEART),
**Pregnancy & labour** (dating and antenatal schedule, obstetric emergencies, treatment in pregnancy), **Infections**
(A–Z), **Adult medicine**, **Drugs**, then **Scores**, **Calculators**, **Sources**, **About**. Every topic page has a pin
button (pinned and recent pages are stored on the device like settings — page ids only), "Related" links to the same
subject in other sections, and sources collapsed to one line ("Sources · NICE, MoHFW") that expands on tap. Protocols
open with a step outline to jump to any step. A one-time "for clinicians" notice explains the intended use.

**Pregnancy**
- LMP → EDD (Naegele, +280 days), gestational age (weeks + days), trimester, term category.
- Optional dating ultrasound: compares with LMP using the ACOG CO 700 redating table and redates when the
  discrepancy exceeds the threshold (bands are by LMP gestational age, as the table specifies).
- Antenatal schedule — tests, scans, vaccines, supplements and visits — with calendar dates and status
  (due now / coming up / first visit / window passed). Filter by **India (MoHFW, ICMR)** or **ACOG / CDC**.

**Treatment** — browse or search (synonyms work: "vomiting", "itching", "pre-eclampsia", drug names)
- *In pregnancy:* nausea & vomiting, fever & pain, headache, heartburn, high BP & pre-eclampsia,
  itching & cholestasis, UTI, vaginal infections, anaemia, diabetes (GDM & pre-existing), hypothyroidism, asthma.
- *General adult:* diabetes (type 2), hypothyroidism, asthma.
- *Infections (0.6.0):* 40 infectious diseases A–Z (acute diarrhoea/cholera to Zika), each with first steps,
  investigation of choice, drug of choice and alternatives, what to avoid, red flags and an openly licensed reference
  photo (35 of 40) with its Wikimedia Commons credit and licence. Drafted by the user with Gemini and compiled as
  written — see Known gaps.
- Doses as written by the source; weight-based doses calculate from an entered weight.

**Drugs** — searchable database summarising official US FDA labels (DailyMed): uses, dose, tests before
starting, monitoring, kidney and liver dosing, common vs serious side effects, pharmacokinetics (absorption,
distribution, metabolism, elimination, half-life), key interactions, pregnancy and breastfeeding. Every section
cites the label section it came from. "Look up elsewhere" links open DailyMed, LiverTox, LactMed, BNF, Medscape,
Drugs.com and UpToDate in the browser — linked, never copied. Condition pages link to the drug profile.

**Scores** — 25 clinical scores and decision rules (Wells, HEART, CURB-65, NIHSS, ABCD², qSOFA, Glasgow-Blatchford,
Canadian C-spine Rule, NEXUS and others), each with its derivation paper and endorsing guideline. Long results
end with "Show more"; tap the result panel to read all of it.

**Protocols** — step-by-step pathways with timing, checklists, weight-based dose calculation and the source behind
every step; "Where guidelines differ" notes compare India and international guidance.
- *Neurological:* acute stroke (thrombolysis doses, onset clock).
- *Poisoning & envenomation (0.4.0):* first steps for the poisoned patient, corrosives (Harpic, acids, alkalis),
  carbon monoxide, kerosene/hydrocarbons, organophosphates/carbamates, aluminium phosphide (Celphos), zinc
  phosphide, yellow phosphorus (Ratol), superwarfarin rat poison, paraquat, dhatura, oleander, snakebite,
  scorpion sting, opioids, pregabalin/gabapentin, benzodiazepines, cannabis, paracetamol, alcohol, methanol,
  cocaine (toxicity, formication / "cocaine bugs"; dependence treatment is on the Treatment page).

**How protocol content is checked.** One writer drafts each group from primary sources (India first). Two
independent reviewers then check it: one checks every number against the source, the other checks safety and
attribution. A fixer re-checks each finding against the source before changing anything. Groups that have not
finished this cycle stay in `Protocols.inReview`: they are compiled and tested but never shown.
- *Medical emergencies (0.5.0):* DKA, HHS, hypoglycaemia, adrenal crisis, thyroid storm, myxoedema coma;
  metabolic and respiratory acidosis/alkalosis (with the ABG interpreter); hyper/hypokalaemia, hyponatraemia;
  STEMI (streptokinase/tenecteplase), NSTE-ACS, acute heart failure, hypertensive emergency, aortic dissection;
  cardiac arrest, tachy- and bradyarrhythmias; acute severe asthma, COPD exacerbation, pulmonary embolism,
  anaphylaxis; status epilepticus, acute migraine, bacterial meningitis, subarachnoid haemorrhage; burns (with the
  body chart), heat stroke, electrical injury; sepsis, upper GI bleed, severe dengue, severe malaria.
- *Obstetric emergencies (0.5.0):* eclampsia and severe pre-eclampsia, postpartum haemorrhage, maternal collapse
  and perimortem caesarean, trauma in pregnancy.
- *Trauma (0.5.1):* major trauma (primary survey), traumatic haemorrhage, chest trauma, traumatic cardiac arrest;
  head injury (CT criteria, raised ICP, CRASH-3) and spinal injury (with the Canadian C-spine Rule and NEXUS);
  animal bites and rabies post-exposure prophylaxis (NCDC 2019), tetanus prophylaxis.
- *Trauma (0.5.2):* abdominal and pelvic trauma (eFAST, pelvic binder, non-operative management, REBOA positions),
  open fractures (antibiotics by source, bleeding control, gentamicin renal cautions), compartment syndrome and
  crush injury (pressure thresholds, fasciotomy, crush fluids, hyperkalaemia and dialysis).

- *Obstetric emergencies (0.6.0):* ectopic pregnancy (methotrexate, NICE 2026 hCG rules), miscarriage and septic
  abortion (misoprostol/mifepristone, MTP Act), hyperemesis gravidarum (RCOG GTG 69 2024).
- *Obstetric emergencies (0.6.1, Gemini-written):* antepartum haemorrhage (praevia, abruption, vasa praevia, uterine
  rupture, anti-D), preterm labour and PPROM (antenatal steroids, nifedipine, magnesium neuroprotection, erythromycin).
- *Obstetric emergencies (0.8.0, Gemini-written):* maternal sepsis, VTE in pregnancy, DKA in pregnancy.
- *Child emergencies (0.8.0, Gemini-written; "Children" in Emergency):* cardiac arrest and the seriously ill child,
  neonatal resuscitation, sepsis, status epilepticus and febrile seizures, DKA, acute asthma, bronchiolitis, croup,
  diarrhoea and dehydration, SAM with complications, anaphylaxis/severe dengue/severe malaria in children.
- *Labour-room emergencies (0.8.1, Gemini-written):* cord prolapse, shoulder dystocia, uterine inversion, retained
  placenta. All obstetric emergency protocols are now written.

**Calculators**
- Burns: tappable Lund–Browder body chart (six age columns) → %TBSA → fluid plan. Adults: Parkland 4 and ABA 2024
  2 mL/kg/%TBSA; children: JTS 3 and MoHFW 2 mL/kg/%TBSA plus dextrose maintenance (4-2-1). Shown only above the
  MoHFW thresholds; warns above 250 mL/kg/24 h (JTS).
- ABG interpreter: reference ranges pH 7.35–7.45, PaCO₂ 35–45 mm Hg, HCO₃ 22–26 mmol/L (StatPearls), with the
  pH 7.40 cut-off for a normal pH and PaCO₂ compared with 40 mm Hg; expected secondary response, anion gap with
  albumin correction and Δ–Δ follow Berend (NEJM 2014). Arterial samples only.
- Glucose ↔ HbA1c (ADAG equation, as used by the ADA eAG calculator) with ADA diagnostic band and the
  ICMR therapy band. Clearly marked as a rough estimate; not offered in pregnancy (ADA 2026, rec 15.13).
- TSH in pregnancy — shows **ATA 2026** and **MoHFW 2014** verdicts side by side (they differ).
- TSH in non-pregnant adults — NICE NG145, ATA 2014, ATA/AACE 2012, ICMR; levothyroxine starting dose by weight.

## Content rules

1. Government of India (MoHFW, ICMR) and ACOG first. Where they are silent: CDC, SMFM, USPSTF, FDA, ADA, ATA,
   GINA, NHLBI, NICE, RCOG, IDSA.
2. No source, no entry. `ContentIntegrityTest` fails the build if any drug, caution, info block or schedule item
   lacks a citation, cites an unknown source, or if a source is unused.
3. Where guidelines disagree, both are shown and labelled — never silently merged.
4. Sources were checked against the issuing body's own page in **November 2026** (`Sources.REVIEWED`).

### Known gaps — review before clinical release
- **Gemini-written protocols (APH, preterm labour, labour-room emergencies; maternal sepsis, VTE, DKA in pregnancy; all
  child emergencies)**
  were compiled as written by
  `.claude-work/gemini-protocols/convert_protocols.py`, without the two-reviewer check the earlier protocols had.
- **Protocols rewritten in quick-reference style by Gemini (0.8.2: poisoning and envenomation, batch R1)** were
  converted with `convert_protocols.py --rewrite`. An automatic check compares every number in each old and new
  protocol (report: `.claude-work/gemini-protocols/r1-numbercheck.json`); dropped trial and evidence details are
  intentional, and one dropped safety rule (snakebite high-risk ASV patients) was restored from the reviewed original.
- **Infections A–Z (40 pages) were not independently reviewed.** They were drafted with Gemini from Indian and
  international sources and compiled as written at the user's request (generated by
  `.claude-work/infections/convert.py`). Unlike the protocols, no second check of doses, cut-offs or citations was
  done. Compound weight-based doses are shown as text, not as calculators. Four photos (hepatitis C, malaria, scrub
  typhus, UTI) are missing because Wikimedia rate-limited the download; re-run `images.py` then `convert.py` to add them.
- **Headache (ACOG CPG No. 3):** full text is paywalled; doses were taken from published summaries of the
  guideline and need checking against the original.
- **ACOG PB 190 / PB 201 (diabetes in pregnancy):** paywalled, so not used for clinical content; ADA 2026 Section 15
  and MoHFW 2018 are used instead.
- **Heartburn:** NICE NG201 recommends a trial of antacid/alginate but gives no dose.
- **IV iron per-dose amounts:** in a separate MoHFW IV-iron guideline not yet incorporated.
- **Not yet covered:** constipation, haemorrhoids, cough/cold/allergic rhinitis (NICE CKS dosing pages are
  geo-restricted; an alternative authoritative dose source is needed).
- **COVID-19 vaccine in pregnancy:** US federal guidance changed in 2025 and remains contested; ACOG's position is shown.
- ADA publishes a new Standards of Care each December — re-check the 2027 edition.
- **ABG:** for a compensated gas such as pH 7.39 / PaCO₂ 51 / HCO₃ 30, Berend's chronic band reports "additional
  metabolic alkalosis" while StatPearls calls it compensatory — a clinical reviewer should look at this edge.
- **MoHFW STG Burns 2025, Figure 5:** four Lund–Browder values in the government figure are wrong; the app uses the
  US Joint Trauma System reproduction instead.
- **Poisoning:** India-availability of physostigmine and digoxin-specific Fab, and the AHA 2008 cocaine statement,
  could not be verified; the protocols say so. No ICMR STW exists for most poisons; Indian content comes from
  ISCCM/IJCCM reviews and national programme documents.
- **Rabies monoclonal antibodies (Rabishield, Twinrab):** no CDSCO approval text or package insert was found online;
  doses come from the (manufacturer-funded) trials and the manufacturer's page. India's NCDC 2019 guideline has not
  adopted them. rabiesfreeindia.mohfw.gov.in and NCDC's 2024–2025 rabies documents could not be opened.
- **Tetanus:** no Indian body publishes a tetanus-by-vaccination-history table, so that table is CDC/ACIP.
- **Trauma in pregnancy:** MoHFW STG Trauma 2025 Ch. 13 also advises 50% more IV crystalloid, PaCO₂ 30 mm Hg, pelvic
  examination only after placental localisation, and perimortem caesarean only in non-hypovolaemic arrest; these
  need a clinical decision before being added. The tilt angle differs (MoHFW HDU 2016: 15°; STG 2025: about 30°).
- **Crush injury:** the ISN/ERBP 2012 recommendations are paywalled; their fluid algorithm and dialysis criteria are
  shown as reproduced by ISN task-force authors (Sever 2023, RMMJ 2011). NDMA/NDRF guidance could not be retrieved.
  Children's crush-fluid doses have no source-stated maximum (the app says so). MoHFW STG 2025 Table 5 prints
  "2.5 m/kg" for gentamicin; read as mg/kg.

## Project layout

```
composeApp/src/
  commonMain/kotlin/com/medicalaid/app/
    content/      Sources.kt, Schedule.kt, PregnancyConditions.kt, ChronicConditions.kt, Search.kt, Model.kt
    content/drugs/  DrugModel.kt, Drugs.kt (registry + search), ExternalRefs.kt, data/ (one file per drug group)
    pregnancy/    Dating.kt            (EDD, GA, ACOG redating)
    tools/        Glycemia.kt, Thyroid.kt
    platform/     Prefs (settings only — never patient data)
    ui/           theme, components, home, pregnancy, medicines, drugs, tools, sources screens
  commonTest/     dating, calculators, search and content-integrity tests
  androidMain/    MainActivity, launcher icon
  iosMain/        MainViewController (entry point for Swift)
  desktopMain/    desktop window (also used for screenshots)
  desktopTest/    Screenshots.kt — renders key screens to PNG
iosApp/           Xcode project (SwiftUI host)
```

## Build

Requirements: JDK 17+, Android SDK (API 36).

```bash
./gradlew :composeApp:testDebugUnitTest          # all shared tests
./gradlew :composeApp:assembleRelease            # Android APK → composeApp/build/outputs/apk/release/
./gradlew :composeApp:run                        # desktop window
./gradlew :composeApp:desktopTest -Pscreenshots  # PNGs → composeApp/build/screenshots/
```

The release build is currently signed with the local debug key so it can be side-loaded for testing.
Create a real upload key before publishing to the Play Store.

**iOS** (needs a Mac with Xcode): set `TEAM_ID` in `iosApp/Configuration/Config.xcconfig`, open
`iosApp/iosApp.xcodeproj`, and run. The Xcode build phase calls `./gradlew :composeApp:embedAndSignAppleFrameworkForXcode`.

## Adding content

1. Add the document to `Sources.kt` (official URL, document number, year, status).
2. Add the entry to the right `Condition` or to `Schedule.items`, with `cite("source-id", "Table/section")`.
3. Run the tests — the integrity test catches missing or unknown citations.

Drug profiles: add a `DrugMonograph` to the right file in `content/drugs/data/` (see `Metabolic.kt` for the
template), with a `Source` for the DailyMed label and a section-level citation on every block.

## Before any public release

- **ESC guidelines:** content summarised from European Society of Cardiology guidelines is kept for private use.
  Request permission from ESC (customerservices@escardio.org / help.escardio.org) before publishing. If permission
  is refused, remove it: ESC sources use `Authority.Paper` with a `document` starting "ESC", and ESC statements
  start with "ESC" (search the `content/` folder).
- **AHA/ASA 2026 stroke guideline correction** (Stroke 2026;57:e461–e467): review and update the stroke protocol.
- **Clinical review** of every page by a second clinician (see CONTRIBUTING.md), especially items flagged as
  summarised from secondary sources (ACOG CPG 3 headache doses, TIMI criteria).
- **Release signing key** for Play Store / F-Droid; check whether Apple's non-profit fee waiver applies.
- **Regulatory check** (CDSCO medical-device software rules) before store release.

## Licences and legal

- Code: Apache License 2.0 (`LICENSE`). Written content: CC BY 4.0 (`CONTENT-LICENSE.md`). Third-party: `NOTICE`.
- Medical disclaimer and intended use: `DISCLAIMER.md` (also shown in the app). Contribution rules,
  including clinical review of content changes: `CONTRIBUTING.md`.
- Facts from guidelines and labels are stated in our own words with a citation; no third-party text, tables or
  images are reproduced, and no logos are used.
