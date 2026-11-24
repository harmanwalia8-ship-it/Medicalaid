# Prompt for Gemini — Medicalaid medicines (drug profiles, children's doses, Treatment pages)

Paste everything below the line into Gemini (attach the repo zip if you like; it is not required). Ask for ONE batch
per reply ("Do batch D1"), then upload each reply to Claude as a `.md` file.

---

You are writing medicine content for **Medicalaid**, an offline **quick-reference** app (not a research app) for
doctors in India (Punjab) — casualty, wards, OPD, labour room, paediatrics. Your Markdown is converted to app code by a
script, so **follow the output formats exactly**.

## Rules for every batch
1. **Sources, India first**: NLEM 2022 / National Formulary of India, ICMR Treatment Guidelines for Antimicrobial Use
   (latest edition), ICMR Standard Treatment Workflows, MoHFW and national programmes (NTEP, NCVBDC, NACO, NVHCP, UIP),
   IAP (for children), FOGSI. Drug facts (dose, renal/hepatic adjustment, interactions, side effects, PK) come from the
   **official US FDA label on DailyMed** (cite the section, e.g. "§2.2", "§5.3"), plus WHO Model Formulary for
   Children / WHO guidelines where relevant. NICE/BNF may be cited, never copied. Latest editions (check 2024–2026).
2. **Verify every dose, interval, maximum, age/weight band, renal cut-off and interaction in the source.** Never from
   memory; if you cannot verify it, leave it out.
3. **Quick-reference style**: short direct lines ≤ 20 words; **no guideline names inside sentences** — put the source
   link at the end of the line ([Short label — locator](https://full.url)); name sources in the text only where they
   disagree in a way that changes treatment ("India: …; FDA: …"). No trial details, no evidence grades.
4. Every line ends with at least one link; every URL used must appear in the batch's Sources table (same URL).
5. Indian brand names only in `Aliases`/keywords and only if you can verify them.
6. Children's doses: give the **age band**, **mg/kg**, **frequency**, **route** and the **maximum** (per dose or per day).

## Format A — a NEW drug profile
```
## Drug: <id, lower-case-hyphenated, e.g. cefixime>
- **Name:** <as used in India, e.g. Cefixime>
- **Aliases:** `other generic name`, `brand (if verified)`
- **Class:** <e.g. Third-generation oral cephalosporin>
- **Uses:** <one line, ≤ 15 words>
- **Clearance:** <Renal | Hepatic | Biliary — comma-separated, the main routes>

### Boxed warning            (only if the FDA label has one)
- <line> ([FDA label — Boxed warning](https://dailymed…))
### Dose
- <adult dose lines>
### Children
- <age band: mg/kg, frequency, route, max> ([source — locator](url))
- **Dose:** <Drug — indication> | <min>–<max> mg/kg <per dose|per day, in N divided doses …> | max <number> <unit> | <route/note>
### Before starting
### Monitoring
### Kidney
### Liver
### Common side effects      (required)
### Serious side effects     (required)
### Pharmacokinetics         (required)
- **Absorption:** … - **Distribution:** … - **Metabolism:** … - **Elimination:** … - **Half-life:** …
- **Citations:** [FDA label — §12.3](https://dailymed…)
### Interactions
### Pregnancy
### Breastfeeding
```
Leave out any optional section the source does not cover. `Dose` lines (they become weight calculators) only for one
drug with one simple mg/kg figure or range; compound regimens go in a normal line. Write "max —" if no cap.

## Format B — children's doses for a drug ALREADY in the app
```
## Child dosing: <existing id>
### Children
- <age band: mg/kg, frequency, route, max> ([source — locator](url))
- **Dose:** <Drug — indication> | <min>–<max> mg/kg <per …> | max <number> <unit> | <note>
```

## Format C — a Treatment page (same format as your Infections A–Z pages)
```
## <N>. <Title>
- **Condition ID:** `<id>`
- **Population:** `Population.General` or `Population.Pregnancy`
- **Summary:** <≤ 10 words, a list subtitle>
- **Search Keywords:** `kw1`, `kw2`
### First Steps
1. <line> ([label — locator](url))
### Screening & Investigation of Choice (IOC)
- <line> (links)
### Other Investigations & Key Findings
- <line> (links)
### Treatment Tiers
#### Tier 1: <label, e.g. First line>
> *Note:* <optional one line>
- **<Drug>**
  - **Regimen:** `<dose, route, frequency, duration>`
  - **Max Dose:** `<if the source gives one>`
  - **Weight-based calculator (`WeightDose`):** `<min>–<max> mg/kg per … (only one simple mg/kg figure)`
  - **Cautions:** <short>
  - **Citations:** [label — locator](url)
### Avoid
- **<Thing to avoid>:** <why> (link)
### Escalate / Red Flags
- <line> (link)
```

## Sources table (end of every batch)
```
## Sources
| Source ID | Authority | Document | Title | Year | URL |
|---|---|---|---|---|---|
| `<batch-prefix>-<short>` | `FDA` | Drug label (DailyMed) | <product> | <year> | [site](https://full.url) |
```
Authority: `MoHFW ICMR NCDC NCVBDC NTEP IAP FOGSI WHO FDA NICE CDC IDSA ACOG RCOG ADA ATA GINA GOLD ILAE EndoSoc
KDIGO ESC AHA Paper`. Then `## Possible issues` (or "None"): anything you think is wrong or unverifiable.

## Already in the app — do not create these again
Drug profiles (75): nitrofurantoin, amoxicillin, amoxicillin-clavulanate, cefalexin, cefuroxime, ceftriaxone,
azithromycin, doxycycline, metronidazole, clindamycin, warfarin, apixaban, rivaroxaban, dabigatran, glimepiride,
sitagliptin, insulin-glargine, regular-human-insulin, premixed-human-insulin-70-30, levothyroxine,
ursodeoxycholic-acid, labetalol, nifedipine, methyldopa, hydralazine, hydrochlorothiazide, chlorthalidone,
amlodipine, telmisartan, losartan, enalapril, ramipril, metoprolol, atorvastatin, rosuvastatin, ezetimibe,
clopidogrel, furosemide, spironolactone, sacubitril-valsartan, dapagliflozin, empagliflozin, metformin, budesonide,
budesonide-formoterol, salbutamol, levosalbutamol, ipratropium, tiotropium, montelukast, prednisolone, omeprazole,
pantoprazole, iron-sucrose, ferric-carboxymaltose, doxylamine-pyridoxine, ondansetron, metoclopramide, promethazine,
prochlorperazine, diphenhydramine, chlorpromazine, paracetamol, ibuprofen, sumatriptan, methylprednisolone,
magnesium-sulphate, isoniazid, rifampicin, pyrazinamide, ethambutol, artemether-lumefantrine, artesunate,
chloroquine, primaquine.
Treatment pages: asthma, hypothyroidism, type 2 diabetes, hypertension, dyslipidaemia, CKD, heart failure, AF,
chronic coronary disease, cocaine use disorder; in pregnancy: nausea & vomiting, fever & pain, headache, high BP,
itching/ICP, UTI, vaginal infections, anaemia, heartburn, asthma, thyroid, diabetes. Infections A–Z (40 diseases,
incl. scabies, TB, HIV, hepatitis) and emergency protocols (incl. VTE in pregnancy, preterm labour) also exist.

## Batches (one per reply)
**New drug profiles — Format A** (batch prefix for Source IDs in brackets)
- **D1** [`dr-d1-`] Penicillins and cephalosporins: ampicillin, cloxacillin, benzathine benzylpenicillin,
  benzylpenicillin, cefixime, cefpodoxime, cefazolin, cefotaxime, ceftazidime, cefepime, cefoperazone–sulbactam,
  piperacillin–tazobactam.
- **D2** [`dr-d2-`] Reserve and hospital antibiotics: meropenem, imipenem–cilastatin, ertapenem, gentamicin, amikacin,
  vancomycin, teicoplanin, linezolid, colistin, polymyxin B, tigecycline, fosfomycin.
- **D3** [`dr-d3-`] Quinolones, macrolides and other oral agents: ciprofloxacin, levofloxacin, ofloxacin,
  moxifloxacin, norfloxacin, clarithromycin, erythromycin, co-trimoxazole, tinidazole, ornidazole, cefadroxil.
- **D4** [`dr-d4-`] Antifungal, antiviral, antiparasitic: fluconazole, itraconazole, amphotericin B (liposomal and
  conventional), terbinafine, aciclovir, valaciclovir, oseltamivir, tenofovir disoproxil, entecavir, albendazole,
  ivermectin, diethylcarbamazine, praziquantel, quinine.
- **D5** [`dr-d5-`] Emergency and common children's medicines not yet in the app: adrenaline, midazolam, lorazepam,
  diazepam, levetiracetam, phenytoin, phenobarbitone, sodium valproate, dexamethasone, hydrocortisone, cetirizine,
  oral rehydration salts (low-osmolarity), zinc sulfate.

**Children's doses for existing drugs — Format B**
- **P1** [`dr-p1-`] paracetamol, ibuprofen, amoxicillin, amoxicillin-clavulanate, cefalexin, cefuroxime, ceftriaxone,
  azithromycin, doxycycline (from 8 years), metronidazole, clindamycin, nitrofurantoin.
- **P2** [`dr-p2-`] salbutamol, levosalbutamol, ipratropium, budesonide, montelukast, prednisolone,
  methylprednisolone, ondansetron, omeprazole, promethazine, diphenhydramine, iron (iron sucrose only if a paediatric
  label dose exists).
- **P3** [`dr-p3-`] isoniazid, rifampicin, pyrazinamide, ethambutol (NTEP weight bands as lines), artemether-lumefantrine,
  artesunate, chloroquine, primaquine (NCVBDC age/weight tables as lines), levothyroxine, furosemide, spironolactone.

**Treatment pages — Format C**
- **T1** [`tx-t1-`] hyperthyroidism (Graves'), COPD long-term care, GERD and peptic ulcer with H. pylori, corticosteroid
  injections bought from a chemist (Cushing's, adrenal suppression, safe tapering, sick-day rules), anabolic steroid
  use for bodybuilding (harms, monitoring, hypogonadism after stopping) — Population.General.
- **T2** [`tx-t2-`] gout, osteoarthritis, rheumatoid arthritis (methotrexate baseline tests), osteoporosis, epilepsy
  (valproate, carbamazepine HLA-B*15:02), migraine (adult), depression and anxiety, stroke secondary prevention —
  Population.General.
- **T3** [`tx-t3-`] tinea, acne, urticaria, contraception (WHO MEC), PCOS, menopause, breastfeeding medicine safety —
  Population.General.
- **T4** [`tx-t4-`] in pregnancy: epilepsy, depression, constipation, haemorrhoids, cough/cold/allergic rhinitis,
  thromboprophylaxis with LMWH by weight band — Population.Pregnancy.
