# Medicalaid — Infections A–Z: Batch 3 (H–L) Clinical Review & Implementation Doc

**Section:** Infectious diseases (`Population.Infections`)  
**Order:** Strict alphabetical — Batch 3 of A–Z (8 diseases, following Batch 1 A–C and Batch 2 D–H)  
**Sourcing policy:** India first (NVHCP, NACO, NLEP, NCVBDC, MoHFW, ICMR, IAP, INASL), then WHO, CDC, IDSA, AASLD, FDA. Every statement, investigation, dose, and caution carries a verified primary source URL.  
**Purpose of this document:** Complete clinician review copy (with all 8 verified open-licence clinical photos embedded inline) **and** drop-in specification for Claude Code to add directly into the `Medicalaid` repository (`InfectionsHL.kt`) once reviewed.

---

## Batch 3 Overview (H–L)

| # | Disease | Condition ID | Investigation of Choice (IOC) | First-Line Drug of Choice (DOC) | Open-Licence Photo |
|---|---|---|---|---|---|
| 1 | [Hepatitis C](#1-hepatitis-c) | `inf-hepatitis-c` | Anti-HCV screen → confirmatory HCV RNA PCR + FibroScan / APRI (>2.0) | Sofosbuvir 400 mg + Daclatasvir 60 mg OD × 12 wk (24 wk cirrhosis) OR Sofosbuvir/Velpatasvir 400/100 mg OD × 12 wk | Yes (`inf_hcv_em`, PD) |
| 2 | [Herpes simplex & herpes zoster](#2-herpes-simplex--herpes-zoster) | `inf-herpes` | Lesion swab HSV-1/2 or VZV PCR (CSF PCR for CNS); clinical diagnosis for classic zoster | Genital HSV: Acyclovir 400 mg TDS × 7–10 d; Zoster (<72 h): Acyclovir 800 mg 5×/d or Valacyclovir 1 g TDS × 7 d; CNS/severe: IV Acyclovir 10 mg/kg q8h | Yes (`inf_herpes_zoster`, CC BY-SA 3.0) |
| 3 | [HIV infection & AIDS](#3-hiv-infection--aids) | `inf-hiv` | NACO 3-test serial HIV algorithm (DNA PCR <18 mo); CD4 + HIV-1 RNA viral load + TB/CrAg screen | Lifelong TLD (TDF 300 mg + 3TC 300 mg + DTG 50 mg) PO OD — double DTG to 50 mg BD with rifampicin | Yes (`inf_hiv_budding`, PD) |
| 4 | [Hookworm infection (ancylostomiasis)](#4-hookworm-infection-ancylostomiasis) | `inf-hookworm` | Stool microscopy (formalin–ethyl acetate / wet mount) for thin-shelled oval eggs + CBC/ferritin | Albendazole 400 mg PO SD (200 mg for 12–23 mo) or Mebendazole 100 mg BD × 3 d **+** oral iron/folic acid | Yes (`inf_hookworm_egg`, PD) |
| 5 | [Influenza (seasonal & H1N1)](#5-influenza-seasonal--h1n1) | `inf-influenza` | MoHFW Cat A/B/C triage (no test Cat A/B); nasopharyngeal/throat swab RT-PCR for Cat C / hospitalized | Oral Oseltamivir 75 mg BD × 5 d (weight-banded in children; safe & priority in pregnancy) | Yes (`inf_influenza_h1n1`, PD) |
| 6 | [Japanese encephalitis (JE)](#6-japanese-encephalitis-je) | `inf-japanese-encephalitis` | CSF (preferred) & serum anti-JE IgM MAC-ELISA + exclude malaria, bacterial meningitis, HSV, scrub typhus | No specific antiviral — neuro-ICU supportive care (ICP/seizure control) + empiric IV Ceftriaxone & Acyclovir until mimics excluded | Yes (`inf_japanese_encephalitis`, CC BY 2.0) |
| 7 | [Kala-azar (visceral leishmaniasis)](#7-kala-azar-visceral-leishmaniasis) | `inf-kala-azar` | rK39 rapid dipstick (primary VL); bone marrow/splenic aspirate for LD bodies (relapse/HIV/rK39-negative) | Single-dose IV Liposomal Amphotericin B (L-AmB) 10 mg/kg over ≥2 h in 5% dextrose | Yes (`inf_kala_azar_ld_bodies`, CC BY-SA 4.0) |
| 8 | [Leprosy (Hansen's disease)](#8-leprosy-hansens-disease) | `inf-leprosy` | 3 WHO cardinal clinical signs + slit-skin smear (BI/MI) | 3-drug MDT (Rifampicin + Clofazimine + Dapsone) for BOTH PB (6 mo) & MB (12 mo); Prednisolone for acute neuritis/reactions | Yes (`inf_leprosy_mb`, CC0 1.0) |

---

## 1. Hepatitis C

- **Condition ID:** `inf-hepatitis-c`
- **Population:** `Population.Infections`
- **Summary:** Blood-borne chronic viral hepatitis (HCV) progressing silently to cirrhosis and HCC; confirm viremia by HCV RNA PCR, check HBsAg/cirrhosis status, and cure (>95% SVR12) with 12–24 weeks of all-oral pangenotypic DAAs.
- **Search Keywords:** `hepatitis c`, `hcv`, `anti-hcv`, `hcv rna`, `daa`, `sofosbuvir`, `daclatasvir`, `velpatasvir`, `svr12`, `apri`, `fibroscan`, `cirrhosis`, `nvhcp`, `pangenotypic`

### Clinical Photo


- **Resource name (`resName`):** `inf_hcv_em`
- **Caption:** Transmission electron micrograph of hepatitis C virus (HCV) particles purified from cell culture (scale bar = 50 nm).
- **Credit:** M. T. Catanese, M. Kopp, K. Uryu, & C. Rice (Rockefeller University)
- **Licence:** Public Domain
- **Source Page:** [Wikimedia Commons — File:HCV_EM_picture_2.png](https://commons.wikimedia.org/wiki/File:HCV_EM_picture_2.png)

### First Steps
1. Confirm active viremia with **HCV RNA RT-PCR** before starting DAAs — never treat on an anti-HCV antibody result alone ([NVHCP Lab Guidelines](https://nvhcp.mohfw.gov.in/common_libs/national-laboratory-guidelines.pdf); [WHO HCV 2022](https://www.ncbi.nlm.nih.gov/books/NBK600697/)).
2. Stage fibrosis/cirrhosis via **FibroScan** or **APRI / FIB-4** and compute the **Child-Turcotte-Pugh (CTP)** score if cirrhotic (determines 12 vs 24 wk duration and specialist referral) ([WHO HCV 2022](https://www.ncbi.nlm.nih.gov/books/NBK600697/)).
3. Screen every patient for **HBsAg, anti-HBc, and HIV** before DAA initiation to prevent fatal HBV reactivation and check ART interactions ([FDA Epclusa Label](https://www.accessdata.fda.gov/drugsatfda_docs/nda/2021/214187Orig1s000Lbl.pdf); [AASLD-IDSA](https://pmc.ncbi.nlm.nih.gov/articles/PMC9710295/)).
4. Review concurrent drugs (efavirenz, rifampicin, amiodarone, anticonvulsants, PPIs) and counsel on adherence and reinfection prevention.

### Screening & Investigation of Choice (IOC)
- **Screening test:** Anti-HCV antibody by ELISA or RDT ([NVHCP Lab Guidelines](https://nvhcp.mohfw.gov.in/common_libs/national-laboratory-guidelines.pdf)).
- **Confirmatory IOC:** **HCV RNA RT-PCR** (qualitative/quantitative); **SVR12** (undetectable HCV RNA 12 weeks post-treatment) confirms virological cure ([NVHCP Lab Guidelines](https://nvhcp.mohfw.gov.in/common_libs/national-laboratory-guidelines.pdf); [WHO HCV 2022](https://www.ncbi.nlm.nih.gov/books/NBK600697/)).
- **Cirrhosis staging:** FibroScan (`≥12.5 kPa` = F4 cirrhosis) or **APRI `>2.0`** / **FIB-4 `>3.25`**. Genotyping is not required for pangenotypic DAAs (note: Genotype 3 causes >70% of Indian HCV) ([WHO HCV 2022](https://www.ncbi.nlm.nih.gov/books/NBK600697/)).

### Other Investigations & Key Findings
- Mandatory pre-DAA HBV panel (HBsAg, anti-HBc) and HIV serology; baseline CBC (platelets `<150,000/µL` suggests portal hypertension), LFTs, INR, creatinine/eGFR, pregnancy test, and abdominal USG ([FDA Epclusa Label](https://www.accessdata.fda.gov/drugsatfda_docs/nda/2021/214187Orig1s000Lbl.pdf)).
- CTP Class A (5–6) = compensated; CTP Class B/C (≥7: ascites, encephalopathy, bilirubin >2–3, albumin ≤3.5, INR ≥1.7) = decompensated cirrhosis requiring hepatology care. Cirrhotic patients require lifelong 6-monthly USG + AFP HCC surveillance even after SVR12.

### Treatment Tiers

#### Tier 1: Drug of choice — Pangenotypic All-Oral DAAs (NVHCP India & WHO 2022)
- **Sofosbuvir + Daclatasvir (NVHCP India public-sector first-line)**
  - **Regimen:** `Sofosbuvir 400 mg + Daclatasvir 60 mg PO once daily × 12 weeks (non-cirrhotic) OR × 24 weeks (compensated cirrhosis or treatment-experienced)`
  - **Cautions:** Extend to 24 weeks in cirrhosis because Genotype 3 (dominant in India) achieves higher SVR12 with 24 weeks. Paediatrics (≥3 yr): `≥25 kg` → 400/60 mg OD; `14–25 kg` → 200/30 mg OD × 12 wk.
  - **Citations:** [WHO HCV 2022](https://www.ncbi.nlm.nih.gov/books/NBK600697/), [NVHCP Punjab Elimination Study](https://pmc.ncbi.nlm.nih.gov/articles/PMC9875791/)
- **Sofosbuvir / Velpatasvir FDC (Single-tablet pangenotypic first-line)**
  - **Regimen:** `Sofosbuvir 400 mg / Velpatasvir 100 mg (1 tablet) PO once daily × 12 weeks (non-cirrhotic & compensated cirrhosis CTP A)`
  - **Cautions:** Avoid efavirenz, rifampicin, strong CYP/P-gp inducers, and co-administered PPIs.
  - **Citations:** [WHO HCV 2022](https://www.ncbi.nlm.nih.gov/books/NBK600697/), [FDA Epclusa Label](https://www.accessdata.fda.gov/drugsatfda_docs/nda/2021/214187Orig1s000Lbl.pdf)

#### Tier 2: Decompensated Cirrhosis, Retreatment, & Special Groups
- **Decompensated Cirrhosis (Child-Pugh B/C)**
  - **Regimen:** `Sofosbuvir 400 mg / Velpatasvir 100 mg PO OD + weight-based Ribavirin (start 600 mg/day, titrate up to 1,000 mg/day [<75 kg] or 1,200 mg/day [≥75 kg] in 2 divided doses) × 12 weeks — OR SOF/VEL × 24 weeks without ribavirin if RBV-ineligible`
  - **Cautions:** Manage at hepatology/transplant centre; monitor Hb for haemolysis; **never use protease inhibitors**.
  - **Citations:** [FDA Epclusa Label](https://www.accessdata.fda.gov/drugsatfda_docs/nda/2021/214187Orig1s000Lbl.pdf)
- **DAA Failure Retreatment & Special Groups**
  - **Regimen:** `Retreatment: SOF/VEL/Voxilaprevir 400/100/100 mg OD × 12 wk (CTP A only) OR NVHCP ATC fallback SOF/VEL + Ribavirin × 24 wk. Pregnancy: defer DAA until postpartum/after breastfeeding. HBsAg+: add/continue tenofovir through 12 wk post-DAA.`
  - **Citations:** [WHO HCV 2022](https://www.ncbi.nlm.nih.gov/books/NBK600697/), [FDA Epclusa Label](https://www.accessdata.fda.gov/drugsatfda_docs/nda/2021/214187Orig1s000Lbl.pdf), [PMC12425630](https://pmc.ncbi.nlm.nih.gov/articles/PMC12425630/)

### Avoid
- **Protease inhibitors (glecaprevir/pibrentasvir, voxilaprevir) in Child-Pugh B/C decompensated cirrhosis:** Risk of fatal hepatic decompensation ([FDA Epclusa Label](https://www.accessdata.fda.gov/drugsatfda_docs/nda/2021/214187Orig1s000Lbl.pdf)).
- **Starting DAAs without checking HBsAg/anti-HBc** (fulminant HBV reactivation), **ribavirin in pregnancy** (teratogenic), and **treating on anti-HCV antibody alone** ([FDA Epclusa Label](https://www.accessdata.fda.gov/drugsatfda_docs/nda/2021/214187Orig1s000Lbl.pdf); [WHO HCV 2022](https://www.ncbi.nlm.nih.gov/books/NBK600697/)).

### Escalate / Red Flags
- Decompensated cirrhosis (CTP B/C: ascites, encephalopathy, variceal bleed, bilirubin >3 mg/dL) or focal liver lesion/suspected HCC — refer to hepatology / NVHCP Apex Treatment Centre.
- Acute ALT/bilirubin flare during/after DAA (rule out HBV reactivation immediately) or detectable HCV RNA at SVR12 (refer for retreatment) ([FDA Epclusa Label](https://www.accessdata.fda.gov/drugsatfda_docs/nda/2021/214187Orig1s000Lbl.pdf)).

---

## 2. Herpes simplex & herpes zoster

- **Condition ID:** `inf-herpes`
- **Population:** `Population.Infections`
- **Summary:** Mucocutaneous, ocular, or CNS HSV-1/2 or dermatomal VZV (shingles) infections: start systemic acyclovir/valacyclovir early (<72 h zoster, <24 h recurrent HSV), give immediate IV acyclovir 10 mg/kg q8h for suspected encephalitis/disseminated disease, and never use topical steroids alone in dendritic keratitis.
- **Search Keywords:** `herpes simplex`, `hsv-1`, `hsv-2`, `genital herpes`, `herpes zoster`, `shingles`, `vzv`, `acyclovir`, `valacyclovir`, `famciclovir`, `tzanck`, `hsv encephalitis`, `hutchinson sign`, `ramsay hunt`, `post-herpetic neuralgia`, `naco kit 5`

### Clinical Photo


- **Resource name (`resName`):** `inf_herpes_zoster`
- **Caption:** Early unilateral dermatomal vesicular eruption of herpes zoster (shingles) on the back, respecting the midline.
- **Credit:** Apple2000 (Wikimedia Commons)
- **Licence:** CC BY-SA 3.0
- **Source Page:** [Wikimedia Commons — File:Herpes_zoster_back.jpg](https://commons.wikimedia.org/wiki/File:Herpes_zoster_back.jpg)

### First Steps
1. Screen immediately for emergencies: **CNS signs** (fever, altered sensorium, seizures), **ocular involvement** (V1 rash, **Hutchinson sign** on nasal tip, eye pain/photophobia), **Ramsay Hunt syndrome** (ear vesicles + facial palsy), **disseminated rash** (>20 lesions outside primary dermatome), or **pregnancy/neonatal exposure** ([CDC Shingles 2024](https://www.cdc.gov/shingles/hcp/clinical-overview/index.html)).
2. Start systemic antiviral empirically without awaiting lab confirmation — within **72 h** for zoster, within **24 h** for recurrent genital HSV, and **immediately IV** for suspected HSV encephalitis ([CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm); [IDSA Encephalitis](https://academic.oup.com/cid/article/47/3/303/313455)).
3. Offer mandatory **HIV testing** in all genital herpes patients, check renal function before IV/high-dose antivirals, and cover zoster lesions until crusted ([CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)).

### Screening & Investigation of Choice (IOC)
- **Genital / Mucocutaneous HSV IOC:** Lesion swab **HSV-1/2 NAAT (PCR)** (sensitivity 91–100%; differentiates HSV-1 vs HSV-2) ([CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)).
- **Herpes Zoster IOC:** Clinical diagnosis for classic unilateral dermatomal vesicular rash; lesion swab **VZV PCR** for atypical, disseminated, or immunocompromised cases ([CDC Shingles 2024](https://www.cdc.gov/shingles/hcp/clinical-overview/index.html)).
- **HSV Encephalitis IOC:** **CSF HSV-1/2 PCR** + brain MRI (temporal lobe hyperintensity); Tzanck smear (multinucleated giant cells) is a low-sensitivity bedside fallback only where PCR is unavailable ([IDSA Encephalitis](https://academic.oup.com/cid/article/47/3/303/313455); [CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)).

### Other Investigations & Key Findings
- Slit-lamp fluorescein exam for suspected HSV dendritic keratitis or Herpes Zoster Ophthalmicus (HZO); adjust antiviral doses for CrCl (`CrCl 10–25`: 50% dose; `CrCl <10`: 25% dose) ([ICMR STW Vol. 3 2022](https://www.icmr.gov.in/icmrobject/custom_data/pdf/downloadable-books/STW_Vol_3_2022.pdf); [NACO 2024 Ready Reckoner](https://pmc.ncbi.nlm.nih.gov/articles/PMC12716636/)).
- Pregnancy: primary genital HSV near delivery carries 30–50% neonatal transmission risk vs <1% with recurrent history; check blood HSV PCR in pregnant women with fever + severe unexplained transaminitis ([CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)).

### Treatment Tiers

#### Tier 1: Drug of choice — Genital HSV & Acute Herpes Zoster (Oral)
- **Primary Genital HSV (NACO Kit 5 / CDC 2021)**
  - **Regimen:** `Acyclovir 400 mg PO three times daily × 7 days (NACO Kit 5; CDC: 7–10 days) — OR Valacyclovir 1 g PO BD × 7–10 d (NACO: 500 mg BD × 7 d) — OR Famciclovir 250 mg PO TDS × 7–10 d`
  - **Citations:** [NACO 2024](https://naco.mohfw.gov.in/files/National%20Technical%20Guidelines%20on%20STI%20and%20RTI_Final.pdf), [CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)
- **Recurrent Genital HSV (Episodic & Daily Suppressive)**
  - **Regimen:** `Episodic (<24 h): Acyclovir 800 mg PO BD × 5 d (or 800 mg TDS × 2 d) OR Valacyclovir 500 mg BD × 3 d. Suppressive (≥4–6 episodes/yr): Acyclovir 400 mg PO BD OR Valacyclovir 500 mg PO OD (1 g OD if ≥10/yr).`
  - **Citations:** [NACO 2024 Ready Reckoner](https://pmc.ncbi.nlm.nih.gov/articles/PMC12716636/), [CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)
- **Acute Herpes Zoster (Immunocompetent Adult, start within 72 h)**
  - **Regimen:** `Acyclovir 800 mg PO 5×/day (max 4,000 mg/day) × 7–10 days OR Valacyclovir 1 g PO TDS × 7 days OR Famciclovir 500 mg PO TDS × 7 days`
  - **Max Dose:** `Acyclovir 4,000 mg/day; Valacyclovir 3,000 mg/day`
  - **Cautions:** Still initiate >72 h if new vesicles continue, HZO, or immunocompromised; manage PHN (pain ≥90 d) with gabapentin, pregabalin, or amitriptyline.
  - **Citations:** [CDC Shingles 2024](https://www.cdc.gov/shingles/hcp/clinical-overview/index.html), [ICMR STW Vol. 3 2022](https://www.icmr.gov.in/icmrobject/custom_data/pdf/downloadable-books/STW_Vol_3_2022.pdf)

#### Tier 2: Severe / CNS / Disseminated Disease, Pregnancy Suppression, & Neonatal HSV
- **Acyclovir IV (HSV Encephalitis, Disseminated VZV, Immunocompromised)**
  - **Regimen:** `10 mg/kg IV every 8 hours (infuse over ≥1 h with IV hydration): 14–21 days for HSV encephalitis; 7–10 days for disseminated VZV/zoster. Neonatal HSV: 20 mg/kg IV q8h × 14 d (SEM) or 21 d (CNS/disseminated).`
  - **Weight-based calculator (`WeightDose`):** `10.0 mg/kg per dose IV every 8 h (20.0 mg/kg q8h in neonates)`
  - **Cautions:** Dose on **ideal body weight** in obesity and maintain IV hydration to prevent crystal nephropathy/AKI. Acyclovir-resistant HSV: IV Foscarnet 40 mg/kg q8h.
  - **Citations:** [IDSA Encephalitis](https://academic.oup.com/cid/article/47/3/303/313455), [CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)
- **Pregnancy Suppression (`≥36 weeks`)**
  - **Regimen:** `Acyclovir 400 mg PO BD (NACO 2024) or 400 mg PO TDS (CDC 2021; alternative Valacyclovir 500 mg BD) from 36 weeks until delivery`
  - **Cautions:** Perform **caesarean delivery** if active genital HSV lesions or prodrome are present at onset of labour.
  - **Citations:** [NACO 2024 Ready Reckoner](https://pmc.ncbi.nlm.nih.gov/articles/PMC12716636/), [CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)

### Avoid
- **Topical corticosteroids alone in HSV dendritic keratitis:** Precipitates geographic corneal ulceration and perforation ([AAO HSV Keratitis 2014](https://www.aao.org/education/clinical-statement/herpes-simplex-virus-keratitis-treatment-guideline)).
- **Delaying empiric IV acyclovir in suspected HSV encephalitis** awaiting LP/MRI, **topical antiviral creams for genital HSV**, **routine HSV IgM testing**, and **vaginal delivery with active genital lesions** ([IDSA Encephalitis](https://academic.oup.com/cid/article/47/3/303/313455); [CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)).

### Escalate / Red Flags
- Fever + altered sensorium, seizures, or focal deficits (start IV acyclovir 10 mg/kg q8h immediately) ([IDSA Encephalitis](https://academic.oup.com/cid/article/47/3/303/313455)).
- Hutchinson sign / V1 ophthalmic zoster / eye pain / vision loss (urgent ophthalmology), Ramsay Hunt syndrome (ENT/neurology), disseminated rash (>20 lesions), or pregnant fever + severe transaminitis ([ICMR STW Vol. 3 2022](https://www.icmr.gov.in/icmrobject/custom_data/pdf/downloadable-books/STW_Vol_3_2022.pdf); [CDC STI 2021](https://www.cdc.gov/std/treatment-guidelines/herpes.htm)).

---

## 3. HIV infection & AIDS

- **Condition ID:** `inf-hiv`
- **Population:** `Population.Infections`
- **Summary:** Chronic retroviral infection causing progressive CD4+ T-cell depletion; confirm via NACO 3-test serial algorithm, screen for TB/cryptococcosis, and initiate rapid lifelong dolutegravir-based ART (TLD) under India's "Treat All" policy.
- **Search Keywords:** `HIV`, `AIDS`, `retrovirus`, `ART`, `TLD`, `dolutegravir`, `CD4`, `viral load`, `opportunistic infection`, `PEP`, `PrEP`, `PMTCT`, `EVTHS`, `IRIS`, `NACO`

### Clinical Photo


- **Resource name (`resName`):** `inf_hiv_budding`
- **Caption:** Colorized scanning electron micrograph of HIV-1 virions (green) assembling and budding from the surface of a cultured human lymphocyte.
- **Credit:** CDC / C. Goldsmith, P. Feorino, E. L. Palmer, W. R. McManus (CDC PHIL #10000)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:HIV-budding-Color.jpg](https://commons.wikimedia.org/wiki/File:HIV-budding-Color.jpg)

### First Steps
1. Confirm HIV status via the **NACO serial testing algorithm** (3-test Strategy III for asymptomatic; 2-test Strategy IIB for symptomatic) with confidential counselling and immediate ART centre linkage ([NACO HCTS 2024](https://naco.mohfw.gov.in/wp-content/uploads/2025/05/HCTS-Guideline-2024_14.05.2025_compressed.pdf)).
2. Perform the **WHO 4-symptom TB screen** (cough, fever, weight loss, night sweats) and check for meningitis signs before starting ART ([WHO AHD Policy Brief](https://iris.who.int/bitstreams/ed5db6d3-6ed4-4778-afc7-e939891b0a71/download)).
3. Initiate **rapid ART ("Treat All")** same day or within 7 days regardless of CD4 count once TB/cryptococcal meningitis is excluded ([NACO 2021](https://naco.mohfw.gov.in/files/National_Guidelines_for_HIV_Care_and_Treatment%202021.pdf); [WHO 2021](https://www.who.int/publications/i/item/9789240031593)).
4. Order baseline CD4 count and safety labs (CBC, creatinine/eGFR, ALT/AST, HBsAg, anti-HCV, VDRL/RPR, pregnancy test) without delaying ART unless severe renal/haematological disease is suspected ([NACO 2021](https://naco.mohfw.gov.in/files/National_Guidelines_for_HIV_Care_and_Treatment%202021.pdf); [JAPI 2026](https://japi.org/article/japi-74-6-88)).

### Screening & Investigation of Choice (IOC)
- **Diagnosis IOC:** NACO **3-test serial rapid/ELISA HIV antibody algorithm** (adults & children ≥18 mo); **HIV DNA PCR (EID)** for infants <18 months ([NACO HCTS 2024](https://naco.mohfw.gov.in/wp-content/uploads/2025/05/HCTS-Guideline-2024_14.05.2025_compressed.pdf)).
- **Staging & Monitoring IOC:** Baseline **CD4 cell count** (Advanced HIV Disease = CD4 `<200 cells/mm³` or WHO Stage 3/4, or any child `<5 yr`) and **Plasma HIV-1 RNA viral load** at 6 months and routinely thereafter (virologic failure = persistent VL `>1,000 copies/mL` despite adherence) ([WHO AHD Policy Brief](https://iris.who.int/bitstreams/ed5db6d3-6ed4-4778-afc7-e939891b0a71/download); [JAPI 2026](https://japi.org/article/japi-74-6-88)).
- **Mandatory OI Screening:** 4-symptom TB screen at every visit (+ GeneXpert/mWRD & **urine LF-LAM** if symptomatic, hospitalized CD4 <200, or outpatient CD4 <100) + **serum CrAg** if **CD4 <100 cells/mm³** (consider <200) ([WHO AHD Policy Brief](https://iris.who.int/bitstreams/ed5db6d3-6ed4-4778-afc7-e939891b0a71/download)).

### Other Investigations & Key Findings
- Baseline CBC, creatinine/eGFR, LFTs, HBsAg, anti-HCV, VDRL/RPR, and periodic fasting glucose/lipids; all serum CrAg-positive patients require LP with CSF CrAg to rule out cryptococcal meningitis ([WHO AHD Policy Brief](https://iris.who.int/bitstreams/ed5db6d3-6ed4-4778-afc7-e939891b0a71/download)).

### Treatment Tiers

#### Tier 1: Drug of choice — First-Line ART & Co-infection Adjustments (NACO & WHO)
- **TLD Fixed-Dose Combination (Adults, Adolescents ≥10 yr & ≥30 kg, & Pregnancy)**
  - **Regimen:** `Tenofovir (TDF) 300 mg + Lamivudine (3TC) 300 mg + Dolutegravir (DTG) 50 mg (1 tablet) PO once daily (lifelong)`
  - **Cautions:** Safe and preferred in pregnancy/breastfeeding. Switch TDF to ABC or AZT if eGFR <50–60 mL/min. Separate DTG from Mg/Al antacids, iron, or calcium supplements. Children ≥4 wk & 6–<30 kg: **ABC + 3TC + weight-band DTG** (dispersible pADL).
  - **Citations:** [NACO 2021](https://naco.mohfw.gov.in/files/National_Guidelines_for_HIV_Care_and_Treatment%202021.pdf), [NACO EVTHS 2024](https://www.fogsi.org/wp-content/uploads/2024/12/EVTHS-Guidelines-2024_05.12.2024.pdf), [WHO-PQ DTG SmPC](https://extranet.who.int/prequal/sites/default/files/whopar_files/HA701part4v5.pdf)
- **TB Co-infection (Rifampicin Co-administration)**
  - **Regimen:** `TLD once daily PLUS an extra DTG 50 mg tablet 12 hours later (total DTG 50 mg PO twice daily) throughout rifampicin therapy`
  - **Cautions:** Start ATT first; start ART within 2 weeks for pulmonary/non-CNS TB, but **defer ART 4–8 weeks for TB meningitis** (4–6 weeks for cryptococcal meningitis) to prevent fatal CNS IRIS.
  - **Citations:** [WHO-PQ DTG SmPC](https://extranet.who.int/prequal/sites/default/files/whopar_files/HA701part4v5.pdf), [JAPI 2026](https://japi.org/article/japi-74-6-88)

#### Tier 2: OI Prophylaxis (CPT & TPT), PEP, PrEP, & Second-Line ART
- **Co-trimoxazole Preventive Therapy (CPT) & TB Preventive Treatment (TPT)**
  - **Regimen:** `CPT (CD4 ≤350 or WHO Stage 3/4 or active TB): Co-trimoxazole 960 mg (1 DS tablet) PO OD. TPT (after ruling out active TB): 6H (Isoniazid 300 mg PO OD × 6 mo + pyridoxine) OR 3HP (INH 900 mg + Rifapentine 900 mg PO once weekly × 12 wk + pyridoxine).`
  - **Citations:** [WHO CPT](https://iris.who.int/server/api/core/bitstreams/828f75d0-1992-48db-b1b8-45f1ded14589/content), [WHO TPT](https://iris.who.int/server/api/core/bitstreams/f4eae88f-c458-4c74-80d6-19ee96d09765/content)
- **Post-Exposure Prophylaxis (PEP — Emergency ≤72 h) & PrEP**
  - **Regimen:** `PEP (ideally ≤2 h, strictly ≤72 h): TLD (TDF 300 + 3TC 300 + DTG 50 mg) PO OD × 28 days (full starter pack up front; retest HIV at 4–6 wk & 3 mo). PrEP (confirmed HIV-negative high-risk): TDF 300 + FTC 200 mg (or 3TC 300 mg) PO OD. Second-line ART (confirmed VL >1,000): Boosted PI (LPV/r or ATV/r) + 2 unused NRTIs.`
  - **Citations:** [WHO PEP 2024](https://iris.who.int/server/api/core/bitstreams/fc3c8ce2-35d8-4b39-93b1-cb4d060767f0/content), [NACO PrEP](https://naco.mohfw.gov.in/wp-content/uploads/2026/08/National-Technical-GuidelinesWeb-1.pdf), [JAPI 2026](https://japi.org/article/japi-74-6-88)

### Avoid
- **Starting ART immediately in TB meningitis (defer 4–8 wk) or cryptococcal meningitis (defer 4–6 wk):** Fatal CNS IRIS risk ([JAPI 2026](https://japi.org/article/japi-74-6-88)).
- **Once-daily DTG with rifampicin** (must double to 50 mg BD), **TDF with eGFR <50–60 mL/min**, **abruptly stopping TDF in HBV co-infection** (hepatic flare), and **starting PrEP without excluding acute HIV** ([WHO-PQ DTG SmPC](https://extranet.who.int/prequal/sites/default/files/whopar_files/HA701part4v5.pdf); [NACO PrEP](https://naco.mohfw.gov.in/wp-content/uploads/2026/08/National-Technical-GuidelinesWeb-1.pdf)).

### Escalate / Red Flags
- Headache, neck stiffness, seizures, altered sensorium (cryptococcal/TB meningitis/toxoplasmosis), severe hypoxia (PCP/disseminated TB), severe IRIS, abacavir hypersensitivity/SJS/severe hepatotoxicity, or high-risk HIV exposure within 72 hours (PEP emergency) ([WHO AHD Policy Brief](https://iris.who.int/bitstreams/ed5db6d3-6ed4-4778-afc7-e939891b0a71/download); [WHO PEP 2024](https://iris.who.int/server/api/core/bitstreams/fc3c8ce2-35d8-4b39-93b1-cb4d060767f0/content)).

---

## 4. Hookworm infection (ancylostomiasis)

- **Condition ID:** `inf-hookworm`
- **Population:** `Population.Infections`
- **Summary:** Soil-transmitted intestinal nematode (*Ancylostoma duodenale* / *Necator americanus*) causing chronic occult GI blood loss and iron-deficiency anaemia; diagnose by stool ova microscopy and treat with single-dose albendazole 400 mg PLUS oral iron/folic acid replacement.
- **Search Keywords:** `hookworm`, `ancylostomiasis`, `necatoriasis`, `Ancylostoma duodenale`, `Necator americanus`, `STH`, `iron-deficiency anaemia`, `albendazole`, `mebendazole`, `ground itch`, `National Deworming Day`, `cutaneous larva migrans`

### Clinical Photo


- **Resource name (`resName`):** `inf_hookworm_egg`
- **Caption:** Microscopic wet-mount appearance of a characteristic thin-shelled, oval hookworm egg (indistinguishable between *Ancylostoma duodenale* and *Necator americanus*), measuring 60–75 µm × 35–40 µm.
- **Credit:** CDC Public Health Image Library (PHIL)
- **Licence:** Public Domain
- **Source Page:** [Wikimedia Commons — File:Necator_americanus,_Ancylostoma_duodenale_egg.jpg](https://commons.wikimedia.org/wiki/File:Necator_americanus,_Ancylostoma_duodenale_egg.jpg)

### First Steps
1. Assess for iron-deficiency anaemia (pallor, exertional dyspnoea, maternal anaemia, childhood growth faltering) and barefoot soil exposure / "ground itch" ([CDC Hookworm 2024](https://www.cdc.gov/sth/about/hookworm.html); [MoHFW 2014](https://nhsrcindia.org/sites/default/files/2021-03/Guidelines%20for%20Deworming%20in%20Pregnancy.pdf)).
2. Confirm with stool microscopy for hookworm ova and check CBC/Hb to grade anaemia severity ([CDC DPDx 2019](https://www.cdc.gov/dpdx/hookworm/index.html)).
3. Give single-dose albendazole (defer in 1st-trimester pregnancy), co-prescribe oral iron + folic acid replacement, and counsel on footwear and sanitation ([MoHFW 2014](https://nhsrcindia.org/sites/default/files/2021-03/Guidelines%20for%20Deworming%20in%20Pregnancy.pdf); [WHO STH 2023](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections)).

### Screening & Investigation of Choice (IOC)
- **IOC:** **Stool microscopy** (formalin–ethyl acetate concentration preferred for light infections, or direct wet mount) showing characteristic **thin-shelled, colourless, oval segmented eggs (60–75 × 35–40 µm)** (*A. duodenale* and *N. americanus* eggs look identical) ([CDC DPDx 2019](https://www.cdc.gov/dpdx/hookworm/index.html)).
- **Anaemia workup:** CBC with peripheral smear (microcytic, hypochromic anaemia + eosinophilia) and serum ferritin ([CDC Hookworm 2024](https://www.cdc.gov/sth/about/hookworm.html)).

### Other Investigations & Key Findings
- Heavy chronic infection causes hypoalbuminaemia/oedema and positive faecal occult blood. Delayed stool samples may hatch into rhabditiform larvae (differentiate from *Strongyloides stercoralis*, which requires ivermectin) ([CDC DPDx 2019](https://www.cdc.gov/dpdx/hookworm/index.html)).
- Zoonotic hookworms (*A. braziliense*, *A. caninum*) cause **Cutaneous Larva Migrans (CLM)** — clinical diagnosis of serpiginous pruritic dermal tracks; stool microscopy is negative ([CDC DPDx Zoonotic](https://www.cdc.gov/dpdx/zoonotichookworm/index.html)).

### Treatment Tiers

#### Tier 1: Drug of choice — Anthelmintic + Mandatory Iron Replacement
- **Albendazole (First-line — MoHFW India NDD, Anaemia Mukt Bharat, WHO, CDC)**
  - **Regimen:** `Adults & children ≥2 yr: 400 mg PO single dose (empty stomach); Children 12–23 months: 200 mg PO single dose`
  - **Cautions:** Avoid in 1st trimester of pregnancy; safe in 2nd–3rd trimesters (MoHFW ANC DOT) and lactation.
  - **Citations:** [MoHFW Deworming in Pregnancy 2014](https://nhsrcindia.org/sites/default/files/2021-03/Guidelines%20for%20Deworming%20in%20Pregnancy.pdf), [WHO STH 2023](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections), [CDC STH Care 2024](https://www.cdc.gov/sth/hcp/clinical-care/index.html)
- **Mebendazole (Alternative first-line)**
  - **Regimen:** `100 mg PO twice daily for 3 days (higher hookworm cure rate than 500 mg single dose)`
  - **Cautions:** Avoid in 1st trimester of pregnancy.
  - **Citations:** [CDC STH Care 2024](https://www.cdc.gov/sth/hcp/clinical-care/index.html), [WHO STH 2023](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections)
- **Oral Iron & Folic Acid Replacement (Anaemia Mukt Bharat)**
  - **Regimen:** `Mild–moderate IDA (adults/adolescents/pregnancy): 120 mg elemental iron + 1,000 mcg folic acid daily (2 standard IFA tablets of 60 mg/500 mcg daily). Prophylactic ANC: 1 IFA tablet daily × 180 d. Severe maternal anaemia: IV iron sucrose/FCM or transfusion.`
  - **Citations:** [MoHFW Anaemia Mukt Bharat](https://sansad.in/getFile/loksabhaquestions/annex/1711/AU3979.pdf?source=pqals), [ICMR IJMR IDA 2026](https://ijmr.org.in/treatment-and-tracking-algorithm-for-iron-deficiency-anaemia-among-children-and-adolescents-a-policy-informed-evidence-based-framework-for-india/)

#### Tier 2: Second-Line Alternative
- **Pyrantel pamoate**
  - **Regimen:** `11 mg/kg PO once daily for 3 days`
  - **Max Dose:** `1 g/day`
  - **Weight-based calculator (`WeightDose`):** `11.0 mg/kg PO once daily × 3 days (max 1 g/day)`
  - **Citations:** [CDC STH Care 2024](https://www.cdc.gov/sth/hcp/clinical-care/index.html)

### Avoid
- **Albendazole or mebendazole in the 1st trimester of pregnancy**, **giving 400 mg albendazole to children <24 months** (use 200 mg), and **deworming without iron replacement** ([MoHFW 2014](https://nhsrcindia.org/sites/default/files/2021-03/Guidelines%20for%20Deworming%20in%20Pregnancy.pdf); [WHO eLENA Children 2023](https://www.who.int/tools/elena/interventions/deworming)).

### Escalate / Red Flags
- Severe anaemia (`Hb <7 g/dL`) or high-output heart failure / severe late-pregnancy anaemia (inpatient IV iron or PRBC transfusion), anasarca/severe hypoalbuminaemia, or overt melena/haematochezia ([MoHFW AMB](https://sansad.in/getFile/loksabhaquestions/annex/1711/AU3979.pdf?source=pqals); [CDC DPDx 2019](https://www.cdc.gov/dpdx/hookworm/index.html)).

---

## 5. Influenza (seasonal & H1N1)

- **Condition ID:** `inf-influenza`
- **Population:** `Population.Infections`
- **Summary:** Contagious respiratory viral illness (Influenza A H1N1/H3N2 & B) triaged in India by MoHFW Category A/B/C; start empiric oral oseltamivir immediately in Category B/C, pregnant, and hospitalized patients without awaiting RT-PCR.
- **Search Keywords:** `influenza`, `seasonal flu`, `H1N1`, `swine flu`, `ILI`, `Category A B C`, `oseltamivir`, `Tamiflu`, `zanamivir`, `baloxavir`, `viral pneumonia`, `Reye syndrome`

### Clinical Photo


- **Resource name (`resName`):** `inf_influenza_h1n1`
- **Caption:** Colorized negative-stained transmission electron micrograph (TEM) demonstrating the ultrastructural morphology of the Influenza A (H1N1) virus (`A/CA/4/09`).
- **Credit:** C. S. Goldsmith & A. Balish, CDC (CDC PHIL #11215)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:CDC-11215-swine-flu.jpg](https://commons.wikimedia.org/wiki/File:CDC-11215-swine-flu.jpg)

### First Steps
1. Triage into **MoHFW Category A, B (B1/B2), or C** based on fever severity, high-risk status/pregnancy, and danger signs ([MoHFW Categorization 2019](https://ncdc.mohfw.gov.in/uploads/pdf/72513234671561442053-1.pdf)).
2. Enforce droplet + hand-hygiene precautions (triple-layer mask, home isolation for Cat A/B; isolation ward + PPE for Cat C) ([MoHFW Clinical Protocol 2019](https://ncdc.mohfw.gov.in/uploads/pdf/66872871241561442115.pdf)).
3. Check `SpO2`, respiratory rate, and BP; immediately admit Category C (`SpO2 <90%`, tachypnoea, hypotension, altered sensorium) ([MoHFW Categorization 2019](https://ncdc.mohfw.gov.in/uploads/pdf/72513234671561442053-1.pdf)).
4. Start **empiric oral oseltamivir immediately** in Category B, Category C, pregnant, and hospitalized patients — ideally ≤48 h, and even >48 h in severe/hospitalized cases — without waiting for RT-PCR ([MoHFW Clinical Protocol 2019](https://ncdc.mohfw.gov.in/uploads/pdf/66872871241561442115.pdf); [CDC 2026](https://www.cdc.gov/flu/hcp/antivirals/summary-clinicians.html)).

### Screening & Investigation of Choice (IOC)
- **MoHFW India Triage:** **Cat A** (mild ILI, no risk factors) → no testing, no oseltamivir, home isolation; **Cat B1/B2** (high fever ≥102°F/severe sore throat OR high-risk: pregnancy/≤2 wk postpartum, ≤5 yr, ≥65 yr, chronic lung/heart/kidney/liver/neuro disease, diabetes, HIV/steroids, BMI ≥40) → no testing needed, start empiric oseltamivir; **Cat C** (any danger sign) → **test + admit + immediate oseltamivir** ([MoHFW Categorization 2019](https://ncdc.mohfw.gov.in/uploads/pdf/72513234671561442053-1.pdf)).
- **Laboratory IOC (Cat C / Hospitalized):** **Real-time RT-PCR** on **nasopharyngeal or combined nasopharyngeal/throat swab** in VTM (endotracheal aspirate/BAL if intubated) ([MoHFW Clinical Protocol 2019](https://ncdc.mohfw.gov.in/uploads/pdf/66872871241561442115.pdf); [IDSA 2018](https://academic.oup.com/cid/article/68/6/e1/5251935)).

### Other Investigations & Key Findings
- Pulse oximetry/ABG (`SpO2 <90%` = Cat C), Chest X-ray (viral pneumonia, ARDS, or secondary bacterial pneumonia from *S. aureus*, *S. pneumoniae*, *S. pyogenes*), and multiplex PCR/SARS-CoV-2 co-testing in hospitalized cases ([MoHFW Clinical Protocol 2019](https://ncdc.mohfw.gov.in/uploads/pdf/66872871241561442115.pdf); [CDC 2026](https://www.cdc.gov/flu/hcp/antivirals/summary-clinicians.html)).

### Treatment Tiers

#### Tier 1: Drug of choice — Oral Oseltamivir (All Ages, Pregnancy, & Hospitalized)
- **Oseltamivir PO**
  - **Regimen:** `Adults & children >40 kg: 75 mg PO twice daily × 5 days (extend >5 d in severe ICU/immunocompromised). Children ≥1 yr (× 5 d): ≤15 kg = 30 mg BD; 15–23 kg = 45 mg BD; 24–40 kg = 60 mg BD; >40 kg = 75 mg BD. Infants <1 yr: 3 mg/kg/dose PO BD × 5 d (MoHFW age bands: <3 mo 12 mg, 3–5 mo 20 mg, 6–11 mo 25 mg BD).`
  - **Max Dose:** `150 mg/day (75 mg BD)`
  - **Weight-based calculator (`WeightDose`):** `3.0 mg/kg per dose PO BD × 5 days (infants <1 yr)`
  - **Cautions:** **Safe and priority in all trimesters of pregnancy** (75 mg BD × 5 d). Renal adjustment (`CrCl 31–60`: 30 mg BD; `CrCl 11–30`: 30 mg OD; HD: 30 mg post-dialysis). Chemoprophylaxis (high-risk exposed): 75 mg PO OD × 7–10 days.
  - **Citations:** [MoHFW Clinical Protocol 2019](https://ncdc.mohfw.gov.in/uploads/pdf/66872871241561442115.pdf), [CDC 2026](https://www.cdc.gov/flu/hcp/antivirals/summary-clinicians.html)

#### Tier 2: Alternatives (Uncomplicated Outpatients ≤48 h) & IV Option
- **Inhaled Zanamivir / Oral Baloxavir / IV Peramivir**
  - **Regimen:** `Zanamivir (≥7 yr outpatient): 10 mg inhaled BD × 5 d (contraindicated in asthma/COPD). Baloxavir (≥5 yr uncomplicated outpatient ≤48 h): 40 mg PO once (20–<80 kg) or 80 mg once (≥80 kg); avoid dairy/cations & pregnancy. IV Peramivir (if unable to absorb enteric oseltamivir): 600 mg IV single infusion (children 6 mo–12 yr: 12 mg/kg, max 600 mg).`
  - **Citations:** [CDC 2026](https://www.cdc.gov/flu/hcp/antivirals/summary-clinicians.html), [WHO Influenza 2024](https://www.who.int/publications/i/item/9789240097759)

### Avoid
- **Aspirin/salicylates in children and adolescents** (Reye syndrome), **delaying oseltamivir awaiting RT-PCR**, **routine antibiotics** without bacterial superinfection, **routine systemic steroids**, **amantadine/rimantadine**, and **inhaled zanamivir in asthma/COPD** ([MoHFW Clinical Protocol 2019](https://ncdc.mohfw.gov.in/uploads/pdf/66872871241561442115.pdf); [CDC 2026](https://www.cdc.gov/flu/hcp/antivirals/summary-clinicians.html)).

### Escalate / Red Flags
- Category C: breathlessness, haemoptysis, `SpO2 <90%`, cyanosis, hypotension, altered sensorium, seizures, oliguria, somnolence/poor feeding in children, or worsening >72 h — admit immediately; ICU/ventilation if `SpO2 <90%` on `FiO2 <0.6` or ARDS (`PaO2/FiO2 <200`) ([MoHFW Categorization 2019](https://ncdc.mohfw.gov.in/uploads/pdf/72513234671561442053-1.pdf)).

---

## 6. Japanese encephalitis (JE)

- **Condition ID:** `inf-japanese-encephalitis`
- **Population:** `Population.Infections`
- **Summary:** *Culex*-borne flaviviral Acute Encephalitis Syndrome (AES) endemic in India (20–30% fatality): diagnose by CSF anti-JE IgM MAC-ELISA, start empiric IV ceftriaxone + acyclovir (± scrub typhus/malaria cover) until mimics are excluded, and deliver neuro-ICU supportive care.
- **Search Keywords:** `Japanese encephalitis`, `JE`, `JEV`, `AES`, `acute encephalitis syndrome`, `Culex`, `IgM MAC-ELISA`, `thalamic hyperintensity`, `raised ICP`, `SA 14-14-2`, `viral encephalitis`

### Clinical Photo


- **Resource name (`resName`):** `inf_japanese_encephalitis`
- **Caption:** Clinical neurological examination of a patient during a laboratory-confirmed Japanese Encephalitis outbreak investigation. *(No open-licence human JE thalamic MRI verified on Wikimedia Commons.)*
- **Credit:** CDC Global / Dr. Samsad Rabbani Khan (Bangladesh FETP)
- **Licence:** CC BY 2.0
- **Source Page:** [Wikimedia Commons — File:Examination_of_Encephalitis_Patient_-_Bangladesh_(17057553625).jpg](https://commons.wikimedia.org/wiki/File:Examination_of_Encephalitis_Patient_-_Bangladesh_(17057553625).jpg)

### First Steps
1. Stabilize ABC immediately: lateral/semi-prone airway protection, NPO + NG decompression, O2, and intubate/ventilate if `GCS <8` or `SpO2 <92%` ([NCVBDC AES/JE 2009](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Revised_guidelines_on_AES_JE.pdf)).
2. Check capillary glucose immediately (treat hypoglycaemia with IV 10%/25% dextrose), resuscitate shock (20 mL/kg RL/NS), then restrict to 2/3 maintenance once euvolemic ([NCVBDC AES/JE 2009](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Revised_guidelines_on_AES_JE.pdf)).
3. Control seizures (IV/IN/PR benzodiazepine + loading anticonvulsant) and fever (tepid sponging + paracetamol) ([NCVBDC AES/JE 2009](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Revised_guidelines_on_AES_JE.pdf)).
4. Start **empiric therapy for treatable AES mimics immediately** (IV ceftriaxone + IV acyclovir ± scrub typhus/malaria cover in endemic Indian districts) without awaiting LP/MRI; notify IDSP ([IAP AES 2012](https://pubmed.ncbi.nlm.nih.gov/23255700/); [ICMR 2019](https://www.icmr.gov.in/icmrobject/custom_data/pdf/resource-guidelines/Treatment_Guidelines_2019_Final.pdf)).

### Screening & Investigation of Choice (IOC)
- **IOC:** **Anti-JE IgM Antibody Capture ELISA (MAC-ELISA)** on **CSF (preferred)** and serum (CSF avoids flavivirus/dengue cross-reactivity and recent vaccine interference; IgM appears days 3–8; repeat convalescent serum at 10–14 d if early sample negative) ([NCVBDC NPPCJA 2014](https://ncvbdc.mohfw.gov.in/Doc/JE-AES-Prevention-Control(NPPCJA).pdf); [CDC JE Diagnosis 2026](https://www.cdc.gov/japanese-encephalitis/hcp/clinical-diagnosis/index.html)).
- **Mandatory mimic workup:** Malaria smear/RDT, CSF Gram stain/culture/bacterial PCR, CSF HSV-1/2 PCR, and scrub typhus IgM ELISA ([IAP AES 2012](https://pubmed.ncbi.nlm.nih.gov/23255700/)).

### Other Investigations & Key Findings
- CSF: lymphocytic pleocytosis, mildly elevated protein, **normal CSF:plasma glucose ratio**; MRI brain: classic **bilateral thalamic** (± basal ganglia/brainstem) T2/FLAIR hyperintensities; blood electrolytes (watch for hyponatraemia/SIADH) ([NCVBDC AES/JE 2009](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Revised_guidelines_on_AES_JE.pdf); [CDC JE Diagnosis 2026](https://www.cdc.gov/japanese-encephalitis/hcp/clinical-diagnosis/index.html)).

### Treatment Tiers

#### Tier 1: Neuro-ICU Supportive Care (No Specific Antiviral Exists)
- **Raised ICP Control (Mannitol 20% or 3% Hypertonic Saline)**
  - **Regimen:** `Head-end 15–30°, euvolemia. IV 20% Mannitol: 1 g/kg (5 mL/kg) IV over 15–30 min, then 0.25–0.5 g/kg q6h up to 48 h (after correcting shock; osmolarity <310 mOsm/L). OR 3% Hypertonic Saline (preferred in shock/hyponatraemia): 2.5–5 mL/kg IV bolus over 5–20 min (or 0.1–1 mL/kg/h; target Na 145–155 mEq/L).`
  - **Weight-based calculator (`WeightDose`):** `1.0 g/kg IV 20% mannitol load (5 mL/kg), then 0.25–0.5 g/kg q6h`
  - **Citations:** [NCVBDC JE Clinical Management](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Clinical_Management-JE.pdf), [IJCCM ICP 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6707500/)
- **Seizure Control (Benzodiazepine + Loading Anticonvulsant)**
  - **Regimen:** `Emergent: IV Lorazepam 0.05–0.1 mg/kg (max 4 mg/dose) OR Midazolam 0.2 mg/kg IN/IM (max 10 mg). Loading: IV Levetiracetam 60 mg/kg over 15 min (max 4.5 g) OR IV Phenytoin 15–20 mg/kg in Normal Saline ONLY (maintenance 5–8 mg/kg/d) OR IV Phenobarbitone 20 mg/kg.`
  - **Citations:** [NCVBDC AES/JE 2009](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Revised_guidelines_on_AES_JE.pdf), [ENLS SE 2022](https://www.neurocriticalcare.org/Portals/0/ENLS%205.0/ENLS%205.0%20Protocol%20-%20SE.pdf)

#### Tier 2: Empiric AES Mimic Coverage & UIP Prevention
- **Empiric IV Ceftriaxone + IV Acyclovir (± Scrub Typhus / Malaria Cover)**
  - **Regimen:** `Ceftriaxone: Adults 2 g IV q12h; Children 50 mg/kg IV q12h (max 4 g/day). PLUS Acyclovir 10 mg/kg IV q8h over 1 h with hydration. Add Doxycycline 100 mg BD (Azithromycin in children/pregnancy) for scrub typhus & IV Artesunate if malaria unexcluded. Prevention (India UIP endemic districts): Live SA 14-14-2 vaccine at 9 mo & 16–24 mo.`
  - **Citations:** [NCVBDC NPPCJA 2014](https://ncvbdc.mohfw.gov.in/Doc/JE-AES-Prevention-Control(NPPCJA).pdf), [IJCCM Meningoencephalitis 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6707493/), [IAP AES 2012](https://pubmed.ncbi.nlm.nih.gov/23255700/)

### Avoid
- **Routine corticosteroids** or **routine IVIG** in confirmed viral JE, **delaying empiric IV ceftriaxone/acyclovir** in undifferentiated AES, **aspirin/NSAIDs** in children, **diluting IV phenytoin in dextrose** (precipitates), and **oral feeding** in altered sensorium ([NCVBDC AES/JE 2009](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Revised_guidelines_on_AES_JE.pdf); [NCVBDC NPPCJA 2014](https://ncvbdc.mohfw.gov.in/Doc/JE-AES-Prevention-Control(NPPCJA).pdf)).

### Escalate / Red Flags
- `GCS <8` or progressive coma, refractory status epilepticus, herniation signs (asymmetric pupils, tonic posturing, Cushing triad), respiratory depression/cyanosis, or fluid-refractory shock — immediate PICU/Neuro-ICU transfer ([NCVBDC AES/JE 2009](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Revised_guidelines_on_AES_JE.pdf)).

---

## 7. Kala-azar (visceral leishmaniasis)

- **Condition ID:** `inf-kala-azar`
- **Population:** `Population.Infections`
- **Summary:** Sandfly-borne (*Phlebotomus argentipes*) *Leishmania donovani* infection causing fever >2 weeks, splenomegaly, and pancytopenia; diagnose primary VL with rK39 dipstick and cure with single-dose IV Liposomal Amphotericin B 10 mg/kg in 5% dextrose.
- **Search Keywords:** `kala-azar`, `visceral leishmaniasis`, `Leishmania donovani`, `Phlebotomus argentipes`, `rK39`, `LD bodies`, `amastigotes`, `liposomal amphotericin B`, `AmBisome`, `miltefosine`, `paromomycin`, `PKDL`, `splenomegaly`, `pancytopenia`, `NCVBDC`

### Clinical Photo


- **Resource name (`resName`):** `inf_kala_azar_ld_bodies`
- **Caption:** Giemsa-stained FNAC smear demonstrating intracellular (within a macrophage) and extracellular *Leishmania donovani* amastigotes (Leishman-Donovan bodies), the confirmatory parasitological finding in visceral leishmaniasis (kala-azar).
- **Credit:** Ajay Kumar Chaurasiya (Wikimedia Commons)
- **Licence:** CC BY-SA 4.0
- **Source Page:** [Wikimedia Commons — File:Leishmania_donovani_amastigotes_in_Giemsa_stained_smear_of_FNAC.jpg](https://commons.wikimedia.org/wiki/File:Leishmania_donovani_amastigotes_in_Giemsa_stained_smear_of_FNAC.jpg)

### First Steps
1. Suspect kala-azar in endemic areas (Bihar, Jharkhand, West Bengal, eastern UP) with irregular fever >2 weeks + splenomegaly unresponsive to antimalarials/antibiotics ([NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf)).
2. Perform **rK39 rapid dipstick** (read strictly at 10 min; do NOT use for relapse/re-infection) ([NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf)).
3. Offer mandatory **HIV testing (PICT)**, screen for TB, check CBC/RFT/LFT and pregnancy status, and treat uncomplicated primary VL with single-dose IV Liposomal Amphotericin B (10 mg/kg) ([NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf); [WHO HIV-VL 2022](https://www.ncbi.nlm.nih.gov/books/NBK581529/)).

### Screening & Investigation of Choice (IOC)
- **Frontline IOC (Primary VL in India):** **rK39 rapid immunochromatographic dipstick** (>90% field sensitivity/specificity; 100%/97% in Indian validation) ([NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf)).
- **Gold-Standard Parasitological IOC (Relapse, HIV, or rK39-Negative):** Microscopy for **Leishman-Donovan (LD) bodies** on **bone marrow aspirate** (~70% sensitivity, safer) or **splenic aspirate** (~96% sensitivity; tertiary centre only, requires platelets ≥40,000, PT ≤5 s prolonged, Hb ≥3 g/dL). PKDL: slit-skin smear/biopsy or PCR ([NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf); [NCVBDC SOP 2020](https://ncvbdc.mohfw.gov.in/Doc/SOP_Kala-azar_PKDL_Aug_2020.pdf)).

### Other Investigations & Key Findings
- CBC shows pancytopenia; hypergammaglobulinaemia and hypoalbuminaemia; mandatory HIV co-testing (2–5.6% co-infected in India). Review clinically at 6 months for cure and counsel on **PKDL** (5–10% develop macular/nodular facial lesions with **preserved sensation** 6 mo–5+ yr later) ([NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf)).

### Treatment Tiers

#### Tier 1: Drug of choice — Single-Dose Liposomal Amphotericin B (NCVBDC India & WHO)
- **Liposomal Amphotericin B (L-AmB / AmBisome)**
  - **Regimen:** `10 mg/kg IV as a single dose — reconstitute in sterile water, filter (5 µm) into 5% dextrose ONLY (NEVER normal saline), give 10-min test dose (2 drops/min), then infuse over ≥2 hours`
  - **Weight-based calculator (`WeightDose`):** `10.0 mg/kg IV single infusion in 5% dextrose over ≥2 hours`
  - **Cautions:** Safe in pregnancy, elderly, and all ages ≥1 month; monitor vitals q30 min for infusion reactions/anaphylaxis.
  - **Citations:** [NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf), [EMC AmBisome SmPC 2025](https://www.medicines.org.uk/emc/product/1022/smpc)

#### Tier 2: Second-Line Combos, PKDL, & HIV–VL Co-infection
- **Paromomycin IM + Miltefosine PO (10-day combo where L-AmB unavailable)**
  - **Regimen:** `Paromomycin 11 mg/kg/day (base) IM + Miltefosine PO × 10 days (Adults >25 kg: 50 mg BD; <25 kg: 50 mg OD; Children 2–11 yr: 2.5 mg/kg/day, max 100 mg/day). Alternative (pregnancy/<2 yr/L-AmB unavailable): Amphotericin B deoxycholate 1 mg/kg IV alternate days × 15 doses in 5% dextrose over 6–8 h.`
  - **Cautions:** Miltefosine is **teratogenic** (contraindicated in pregnancy, lactation, <2 yr, and women without contraception for therapy + 2–3 mo); avoid paromomycin in renal disease.
  - **Citations:** [NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf)
- **PKDL & HIV–VL Co-infection**
  - **Regimen:** `PKDL: Oral Miltefosine 100 mg/day (weight-adjusted <25 kg / 2–11 yr) × 12 weeks (or L-AmB 5 mg/kg IV twice weekly × 3 wk = 30 mg/kg). HIV–VL (WHO 2022 preferred): L-AmB 30 mg/kg total (5 mg/kg days 1, 3, 5, 7, 9, 11) + Miltefosine 100 mg/day × 14 days (or L-AmB 40 mg/kg total monotherapy) + ART + secondary prophylaxis until CD4 ≥350.`
  - **Citations:** [WHO HIV-VL 2022](https://www.ncbi.nlm.nih.gov/books/NBK581529/), [NCVBDC PKDL Guidelines](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/PKDL-Guidelines-220512.pdf)

### Avoid
- **Sodium stibogluconate (SSG) in Bihar/India** (~60% resistance + cardiotoxicity), **mixing L-AmB with normal saline** (precipitates — flush line & dilute strictly in 5% dextrose), **miltefosine in pregnancy/lactation/<2 yr/without contraception**, and **rK39 to diagnose relapse** ([NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf); [EMC AmBisome SmPC 2025](https://www.medicines.org.uk/emc/product/1022/smpc)).

### Escalate / Red Flags
- Severe anaemia (Hb <4–5 g/dL) / heart failure, active bleeding / platelets <40,000, jaundice/AKI, severe malnutrition (BMI <16), HIV–VL co-infection, pregnancy, infants <2 yr, relapse, or L-AmB anaphylaxis — District Hospital / tertiary centre ([NCVBDC 2015](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf); [NCVBDC SOP 2020](https://ncvbdc.mohfw.gov.in/Doc/SOP_Kala-azar_PKDL_Aug_2020.pdf)).

---

## 8. Leprosy (Hansen's disease)

- **Condition ID:** `inf-leprosy`
- **Population:** `Population.Infections`
- **Summary:** Chronic curable *Mycobacterium leprae* infection of skin and peripheral nerves diagnosed by 3 WHO cardinal signs; treat BOTH PB (6 mo) and MB (12 mo) with 3-drug MDT (Rifampicin + Clofazimine + Dapsone), never stop MDT during lepra reactions, and treat acute neuritis as an emergency with prednisolone.
- **Search Keywords:** `leprosy`, `Hansen's disease`, `Mycobacterium leprae`, `paucibacillary`, `multibacillary`, `MDT`, `rifampicin`, `clofazimine`, `dapsone`, `slit-skin smear`, `Type 1 reversal reaction`, `erythema nodosum leprosum`, `ENL`, `neuritis`, `lagophthalmos`, `NLEP`

### Clinical Photo


- **Resource name (`resName`):** `inf_leprosy_mb`
- **Caption:** Mottled, erythematous cutaneous patches on the right lower back and flank in a patient with multibacillary (lepromatous) leprosy caused by *Mycobacterium leprae*.
- **Credit:** CDC Public Health Image Library (PHIL #18971)
- **Licence:** CC0 1.0 (Public Domain)
- **Source Page:** [Wikimedia Commons — File:Multibacillary_leprosy_lesion.jpg](https://commons.wikimedia.org/wiki/File:Multibacillary_leprosy_lesion.jpg)

### First Steps
1. Perform full-body skin inspection in daylight, test light-touch sensation within patches, and palpate peripheral nerves (ulnar, median, radial, lateral popliteal, posterior tibial, greater auricular) ([NLEP DPMR](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/516.pdf)).
2. Classify PB vs MB per NLEP 2025/WHO and record baseline Sensory Testing, Voluntary Muscle Testing, eye exam, and WHO Disability Grade / EHF score (0–12) ([NLEP DGHS India](https://dghs.mohfw.gov.in/nlep.php)).
3. Notify case (notifiable in India, Nikusth 2.0), start free 3-drug MDT immediately, counsel on red urine (rifampicin) and reversible brownish skin pigmentation (clofazimine), and screen contacts (≥2 yr) for single-dose rifampicin (SDR) PEP ([NLEP DGHS India](https://dghs.mohfw.gov.in/nlep.php); [WHO Leprosy 2018](https://www.sfdermato.org/media/image/upload-editor/files/Leprosy%20guidelines%202018.pdf)).

### Screening & Investigation of Choice (IOC)
- **Frontline IOC — 3 WHO Cardinal Clinical Signs (≥1 confirms diagnosis):** (1) Definite loss of sensation in a hypopigmented/reddish skin patch; (2) Thickened peripheral nerve with sensory/motor loss in its distribution; (3) Acid-fast bacilli (AFB) on slit-skin smear ([NLEP DPMR](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/516.pdf)).
- **Microbiological IOC:** **Slit-skin smear (SSS)** with Ziehl-Neelsen stain reporting **Bacteriological Index (BI 0 to 6+)** and **Morphological Index (MI % solid viable rods)**; Fite-Faraco skin/nerve biopsy or PCR for doubtful/pure neuritic/relapse cases ([NLEP DPMR](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/516.pdf)).

### Other Investigations & Key Findings
- **NLEP India Classification (effective April 2025):** **PB** = 1–5 skin lesions, 0 nerves involved, AND smear negative; **MB** = >5 lesions, OR ≥1 nerve involved, OR smear positive ([NLEP DGHS India](https://dghs.mohfw.gov.in/nlep.php)).
- Check baseline Hb (repeat at 1 mo for dapsone haemolysis) and G6PD where available; screen for TB, diabetes, and parasites before systemic steroids for reactions ([WHO Technical Guidance 2020](https://ilepfederation.org/wp-content/uploads/2020/02/WHO-Technical-Guidance.pdf)).

### Treatment Tiers

#### Tier 1: Drug of choice — 3-Drug Multidrug Therapy (NLEP India 2025 & WHO 2018)
- **3-Drug MDT — Adults (>14 yr) (BOTH PB × 6 mo & MB × 12 mo)**
  - **Regimen:** `Rifampicin 600 mg monthly (supervised) + Clofazimine 300 mg monthly (supervised) & 50 mg daily + Dapsone 100 mg daily — 6 months for PB; 12 months for MB`
  - **Citations:** [NLEP DGHS India](https://dghs.mohfw.gov.in/nlep.php), [WHO Leprosy Treatment](https://www.who.int/teams/control-of-neglected-tropical-diseases/leprosy/treatment)
- **3-Drug MDT — Children (10–14 yr & <10 yr / <40 kg)**
  - **Regimen:** `10–14 yr: Rifampicin 450 mg monthly + Clofazimine 150 mg monthly & 50 mg alternate day + Dapsone 50 mg daily (PB 6 mo / MB 12 mo). <10 yr or <40 kg: Rifampicin 10 mg/kg monthly + Clofazimine 100 mg monthly & 50 mg twice weekly (or 6 mg/kg monthly + 1 mg/kg daily) + Dapsone 2 mg/kg daily.`
  - **Weight-based calculator (`WeightDose`):** `Rifampicin 10.0 mg/kg monthly; Dapsone 2.0 mg/kg daily (<10 yr / <40 kg)`
  - **Citations:** [NLEP DGHS India](https://dghs.mohfw.gov.in/nlep.php), [NLEP DPMR](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/516.pdf)

#### Tier 2: Lepra Reactions (Type 1 & Type 2 ENL) & Rifampicin-Resistant Disease
- **Type 1 (Reversal) Reaction & Acute Neuritis (<6 mo)**
  - **Regimen:** `Severe / Neuritis: Oral Prednisolone 0.5–1 mg/kg/day (typically 40 mg/day; up to 60 mg/day) morning dose after food, tapered over 12–20 weeks (WHO 20-wk taper: 40→30→25→20→10→5 mg) + gastroprotection + Albendazole 400 mg BD × 3 d`
  - **Cautions:** **Continue MDT uninterrupted**; reassess nerve function q2 wk. Start 30 mg/day in pregnancy/children (max 1 mg/kg/day) under specialist care.
  - **Citations:** [WHO Technical Guidance 2020](https://ilepfederation.org/wp-content/uploads/2020/02/WHO-Technical-Guidance.pdf), [NLEP DPMR](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/516.pdf)
- **Type 2 Reaction (ENL) & Rifampicin-Resistant Leprosy**
  - **Regimen:** `Severe ENL: Prednisolone 30–40 mg/day tapered + steroid-sparing high-dose Clofazimine (300 mg/d × 1 mo → 200 mg/d × 3–6 mo → 100 mg/d; max 12 mo). Refractory tertiary ENL: Thalidomide 300–400 mg/d tapered (strict pregnancy prevention). Rifampicin-resistant (24 mo): Clofazimine 50 mg + ≥2 of Ofloxacin 400 mg / Minocycline 100 mg / Clarithromycin 500 mg daily × 6 mo, then Clofazimine + 1 drug × 18 mo.`
  - **Citations:** [WHO Technical Guidance 2020](https://ilepfederation.org/wp-content/uploads/2020/02/WHO-Technical-Guidance.pdf), [WHO Leprosy 2018](https://www.sfdermato.org/media/image/upload-editor/files/Leprosy%20guidelines%202018.pdf)

### Avoid
- **Stopping MDT during lepra reactions**, **dapsone in severe G6PD deficiency/haemolysis or dapsone hypersensitivity syndrome**, **thalidomide in pregnancy or without strict contraception**, **single-drug monotherapy**, and **systemic steroids in untreated TB or corneal ulcer** ([NLEP DPMR](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/516.pdf); [WHO Technical Guidance 2020](https://ilepfederation.org/wp-content/uploads/2020/02/WHO-Technical-Guidance.pdf)).

### Escalate / Red Flags
- **Acute neuritis / new nerve weakness (<6 mo)** (medical emergency — prevent permanent claw hand/foot drop), **sight-threatening eye signs** (red painful eye/iridocyclitis, corneal ulcer, lagophthalmos >6 mm — ophthalmic emergency), ulcerating ENL/orchitis, dapsone SJS/rifampicin jaundice, or warm swollen anaesthetic "hot foot" (acute Charcot foot — immobilize immediately) ([NLEP DPMR](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/516.pdf)).

---

## Primary Sources Reference Table (Batch 3: Verified Sources)

| Source ID | Authority | Document | Title | Year | URL |
|---|---|---|---|---|---|
| `inf-hl-nvhcp-lab-2018` | `MoHFW` | NVHCP Lab Guidelines | National Laboratory Guidelines for Testing of Viral Hepatitis | 2018 | [nvhcp.mohfw.gov.in](https://nvhcp.mohfw.gov.in/common_libs/national-laboratory-guidelines.pdf) |
| `inf-hl-who-hcv-2022` | `WHO` | HCV Guidelines Ch. 4 | Updated Recommendations on Chronic HCV Treatment & Simplified Delivery | 2022 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK600697/) |
| `inf-hl-fda-epclusa-2021` | `FDA` | Prescribing Information | Epclusa (Sofosbuvir/Velpatasvir) Label — HBV Reactivation & CTP B/C | 2021 | [accessdata.fda.gov](https://www.accessdata.fda.gov/drugsatfda_docs/nda/2021/214187Orig1s000Lbl.pdf) |
| `inf-hl-aasld-hcv-2019` | `IDSA` | AASLD-IDSA Guidance | Hepatitis C Guidance 2019 Update | 2019 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC9710295/) |
| `inf-hl-nvhcp-punjab-2023` | `Paper` | NVHCP Punjab Cohort | Hepatitis C Elimination Model in Punjab under NVHCP | 2023 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC9875791/) |
| `inf-hl-hcv-retx-india` | `Paper` | Indian HCV Retreatment | NVHCP Apex Treatment Centre DAA Failure Retreatment Outcomes | 2025 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC12425630/) |
| `inf-hl-naco-sti-2024` | `MoHFW` | NACO STI Guidelines | National Technical Guidelines on STI & RTI – 2024 | 2024 | [naco.mohfw.gov.in](https://naco.mohfw.gov.in/files/National%20Technical%20Guidelines%20on%20STI%20and%20RTI_Final.pdf) |
| `inf-hl-ijstd-sti-2025` | `Paper` | IJSTD Ready Reckoner | Management Guidelines for STIs (NACO 2024 / CDC 2021) | 2025 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC12716636/) |
| `inf-hl-icmr-stw-v3-2022` | `ICMR` | STW Vol. 3 | Standard Treatment Workflows Vol. 3 — Varicella & Herpes Zoster | 2022 | [icmr.gov.in](https://www.icmr.gov.in/icmrobject/custom_data/pdf/downloadable-books/STW_Vol_3_2022.pdf) |
| `inf-hl-cdc-herpes-2021` | `CDC` | STI Guidelines | Genital Herpes — STI Treatment Guidelines 2021 | 2021 | [cdc.gov](https://www.cdc.gov/std/treatment-guidelines/herpes.htm) |
| `inf-hl-cdc-shingles-2024` | `CDC` | Clinical Overview | Clinical Overview of Shingles (Herpes Zoster) | 2024 | [cdc.gov](https://www.cdc.gov/shingles/hcp/clinical-overview/index.html) |
| `inf-hl-idsa-enceph-2008` | `IDSA` | Clinical Practice Guideline | The Management of Encephalitis | 2008 | [academic.oup.com](https://academic.oup.com/cid/article/47/3/303/313455) |
| `inf-hl-aao-keratitis-2014` | `Paper` | AAO Clinical Statement | Herpes Simplex Virus Keratitis: A Treatment Guideline | 2014 | [aao.org](https://www.aao.org/education/clinical-statement/herpes-simplex-virus-keratitis-treatment-guideline) |
| `inf-hl-naco-hiv-2021` | `MoHFW` | NACO HIV Guidelines | National Guidelines for HIV Care and Treatment | 2021 | [naco.mohfw.gov.in](https://naco.mohfw.gov.in/files/National_Guidelines_for_HIV_Care_and_Treatment%202021.pdf) |
| `inf-hl-naco-hcts-2024` | `MoHFW` | NACO Testing Guidelines | National HIV Counselling and Testing Guidelines | 2024 | [naco.mohfw.gov.in](https://naco.mohfw.gov.in/wp-content/uploads/2025/05/HCTS-Guideline-2024_14.05.2025_compressed.pdf) |
| `inf-hl-naco-evths-2024` | `MoHFW` | NACO EVTHS Guidelines | Elimination of Vertical Transmission of HIV & Syphilis (EVTHS) | 2024 | [fogsi.org](https://www.fogsi.org/wp-content/uploads/2024/12/EVTHS-Guidelines-2024_05.12.2024.pdf) |
| `inf-hl-naco-prep-2026` | `MoHFW` | NACO PrEP Guidelines | National Technical Guidelines for Pre-Exposure Prophylaxis | 2026 | [naco.mohfw.gov.in](https://naco.mohfw.gov.in/wp-content/uploads/2026/08/National-Technical-GuidelinesWeb-1.pdf) |
| `inf-hl-japi-art-2026` | `Paper` | JAPI Clinical Review | Antiretroviral Therapy in India (2025): Drugs, PEP/PrEP & Monitoring | 2026 | [japi.org](https://japi.org/article/japi-74-6-88) |
| `inf-hl-who-hiv-2021` | `WHO` | Consolidated Guidelines | Consolidated Guidelines on HIV Prevention, Testing, Treatment & Monitoring | 2021 | [who.int](https://www.who.int/publications/i/item/9789240031593) |
| `inf-hl-who-pep-2024` | `WHO` | PEP Guidelines | Guidelines for HIV Post-Exposure Prophylaxis | 2024 | [iris.who.int](https://iris.who.int/server/api/core/bitstreams/fc3c8ce2-35d8-4b39-93b1-cb4d060767f0/content) |
| `inf-hl-who-ahd-2023` | `WHO` | Policy Brief | Identifying Common Opportunistic Infections in Advanced HIV Disease | 2023 | [iris.who.int](https://iris.who.int/bitstreams/ed5db6d3-6ed4-4778-afc7-e939891b0a71/download) |
| `inf-hl-who-cpt-2006` | `WHO` | Clinical Guideline | Co-trimoxazole Prophylaxis for HIV-Related Infections | 2006 | [iris.who.int](https://iris.who.int/server/api/core/bitstreams/828f75d0-1992-48db-b1b8-45f1ded14589/content) |
| `inf-hl-who-tpt-2020` | `WHO` | Consolidated Guideline | WHO Consolidated Guidelines on Tuberculosis Preventive Treatment | 2020 | [iris.who.int](https://iris.who.int/server/api/core/bitstreams/f4eae88f-c458-4c74-80d6-19ee96d09765/content) |
| `inf-hl-who-pq-dtg` | `WHO` | WHO-PQ SmPC | Dolutegravir / TLD Summary of Product Characteristics | 2026 | [extranet.who.int](https://extranet.who.int/prequal/sites/default/files/whopar_files/HA701part4v5.pdf) |
| `inf-hl-mohfw-deworm-2014` | `MoHFW` | National Guidelines | National Guidelines for Deworming in Pregnancy | 2014 | [nhsrcindia.org](https://nhsrcindia.org/sites/default/files/2021-03/Guidelines%20for%20Deworming%20in%20Pregnancy.pdf) |
| `inf-hl-mohfw-amb-2023` | `MoHFW` | Programme Strategy | Anaemia Mukt Bharat & National Deworming Day Strategy | 2023 | [sansad.in](https://sansad.in/getFile/loksabhaquestions/annex/1711/AU3979.pdf?source=pqals) |
| `inf-hl-icmr-ida-2026` | `ICMR` | IJMR Framework | Treatment & Tracking Algorithm for Iron-Deficiency Anaemia in India | 2026 | [ijmr.org.in](https://ijmr.org.in/treatment-and-tracking-algorithm-for-iron-deficiency-anaemia-among-children-and-adolescents-a-policy-informed-evidence-based-framework-for-india/) |
| `inf-hl-who-sth-2023` | `WHO` | Fact Sheet | Soil-Transmitted Helminth Infections | 2023 | [who.int](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections) |
| `inf-hl-who-elena-deworm` | `WHO` | WHO eLENA | Deworming in Children | 2023 | [who.int](https://www.who.int/tools/elena/interventions/deworming) |
| `inf-hl-cdc-sth-2024` | `CDC` | Clinical Care | Clinical Care of Soil-Transmitted Helminths | 2024 | [cdc.gov](https://www.cdc.gov/sth/hcp/clinical-care/index.html) |
| `inf-hl-cdc-hookworm-2024` | `CDC` | Clinical Overview | About Hookworm | 2024 | [cdc.gov](https://www.cdc.gov/sth/about/hookworm.html) |
| `inf-hl-cdc-dpdx-hook` | `CDC` | DPDx Reference | DPDx — Intestinal Hookworm | 2019 | [cdc.gov](https://www.cdc.gov/dpdx/hookworm/index.html) |
| `inf-hl-cdc-dpdx-clm` | `CDC` | DPDx Reference | DPDx — Zoonotic Hookworm (Cutaneous Larva Migrans) | 2019 | [cdc.gov](https://www.cdc.gov/dpdx/zoonotichookworm/index.html) |
| `inf-hl-mohfw-flu-cat` | `NCDC` | MoHFW Categorization | Guidelines on Categorization of Seasonal Influenza Cases (Cat A/B/C) | 2019 | [ncdc.mohfw.gov.in](https://ncdc.mohfw.gov.in/uploads/pdf/72513234671561442053-1.pdf) |
| `inf-hl-mohfw-flu-cmp` | `NCDC` | MoHFW Clinical Protocol | Clinical Management Protocol for Seasonal Influenza | 2019 | [ncdc.mohfw.gov.in](https://ncdc.mohfw.gov.in/uploads/pdf/66872871241561442115.pdf) |
| `inf-hl-who-flu-2024` | `WHO` | Clinical Practice Guideline | Clinical Practice Guidelines for Influenza | 2024 | [who.int](https://www.who.int/publications/i/item/9789240097759) |
| `inf-hl-cdc-flu-2026` | `CDC` | Clinical Guidance | Influenza Antiviral Medications: Summary for Clinicians | 2026 | [cdc.gov](https://www.cdc.gov/flu/hcp/antivirals/summary-clinicians.html) |
| `inf-hl-idsa-flu-2018` | `IDSA` | Clinical Practice Guideline | 2018 Update on Seasonal Influenza Diagnosis & Treatment | 2018 | [academic.oup.com](https://academic.oup.com/cid/article/68/6/e1/5251935) |
| `inf-hl-ncvbdc-aes-2009` | `NCVBDC` | National Guideline | Clinical Management of Acute Encephalitis Syndrome Including JE | 2009 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Revised_guidelines_on_AES_JE.pdf) |
| `inf-hl-ncvbdc-je-cm` | `NCVBDC` | Clinical Guideline | Guidelines: Clinical Management of Japanese Encephalitis | 2009 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/Clinical_Management-JE.pdf) |
| `inf-hl-ncvbdc-nppcja` | `NCVBDC` | Operational Guidelines | National Programme for Prevention & Control of JE/AES (NPPCJA) | 2014 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/Doc/JE-AES-Prevention-Control(NPPCJA).pdf) |
| `inf-hl-iap-aes-2012` | `IAP` | Consensus Guideline | Evaluation & Management of Suspected Acute Viral Encephalitis in Children in India | 2012 | [pubmed.ncbi.nlm.nih.gov](https://pubmed.ncbi.nlm.nih.gov/23255700/) |
| `inf-hl-icmr-amu-2019` | `ICMR` | National Guideline | Treatment Guidelines for Antimicrobial Use in Common Syndromes | 2019 | [icmr.gov.in](https://www.icmr.gov.in/icmrobject/custom_data/pdf/resource-guidelines/Treatment_Guidelines_2019_Final.pdf) |
| `inf-hl-cdc-je-dx-2026` | `CDC` | Clinical Guidance | Clinical Features and Diagnosis of Japanese Encephalitis | 2026 | [cdc.gov](https://www.cdc.gov/japanese-encephalitis/hcp/clinical-diagnosis/index.html) |
| `inf-hl-enls-se-2022` | `Paper` | ENLS 5.0 Protocol | Emergency Neurological Life Support: Status Epilepticus Protocol | 2022 | [neurocriticalcare.org](https://www.neurocriticalcare.org/Portals/0/ENLS%205.0/ENLS%205.0%20Protocol%20-%20SE.pdf) |
| `inf-hl-ijccm-icp-2019` | `ISCCM` | IJCCM Review | Raised Intracranial Pressure Syndrome: A Stepwise Approach | 2019 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC6707500/) |
| `inf-hl-ijccm-men-2019` | `ISCCM` | IJCCM Review | Managing Meningoencephalitis in Indian ICU | 2019 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC6707493/) |
| `inf-hl-ncvbdc-ka-2015` | `NCVBDC` | Operational Guidelines | Operational Guidelines on Kala-azar Elimination in India | 2015 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/Doc/opertional-guideline-KA-2015.pdf) |
| `inf-hl-ncvbdc-ka-sop` | `NCVBDC` | SOP | Standard Operating Procedure for Kala-azar & PKDL Case Search | 2020 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/Doc/SOP_Kala-azar_PKDL_Aug_2020.pdf) |
| `inf-hl-ncvbdc-pkdl` | `NCVBDC` | Clinical Guidelines | Guidelines for Treatment of Post-Kala-Azar Dermal Leishmaniasis (PKDL) | 2012 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/PKDL-Guidelines-220512.pdf) |
| `inf-hl-who-hiv-vl-2022` | `WHO` | Clinical Guideline | Treatment of Visceral Leishmaniasis in HIV Co-infected Patients (SE Asia) | 2022 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK581529/) |
| `inf-hl-emc-ambisome` | `Paper` | UK SmPC | AmBisome (Liposomal Amphotericin B) Prescribing Information | 2025 | [medicines.org.uk](https://www.medicines.org.uk/emc/product/1022/smpc) |
| `inf-hl-nlep-dghs-2025` | `MoHFW` | NLEP Revised Protocol | National Leprosy Eradication Programme: Revised Classification & 3-Drug MDT (2025) | 2025 | [dghs.mohfw.gov.in](https://dghs.mohfw.gov.in/nlep.php) |
| `inf-hl-nlep-dpmr-2012` | `MoHFW` | NLEP DPMR Guidelines | Disability Prevention & Medical Rehabilitation (DPMR) Guidelines | 2012 | [clinicalestablishments.mohfw.gov.in](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/516.pdf) |
| `inf-hl-who-lep-2018` | `WHO` | Clinical Guideline | Guidelines for the Diagnosis, Treatment and Prevention of Leprosy | 2018 | [sfdermato.org](https://www.sfdermato.org/media/image/upload-editor/files/Leprosy%20guidelines%202018.pdf) |
| `inf-hl-who-lep-tx` | `WHO` | Programme Page | Treatment of Leprosy — Neglected Tropical Diseases | 2026 | [who.int](https://www.who.int/teams/control-of-neglected-tropical-diseases/leprosy/treatment) |
| `inf-hl-who-lep-rxn-2020` | `WHO` | Technical Guidance | Leprosy/Hansen Disease: Management of Reactions & Prevention of Disabilities | 2020 | [ilepfederation.org](https://ilepfederation.org/wp-content/uploads/2020/02/WHO-Technical-Guidance.pdf) |

---

## Claude Code Integration Spec (Drop-In Instructions)

When this document is placed in the `Medicalaid` repository and approved by the clinician:

1. **Prerequisites (from Batch 1):** Ensure `Model.kt` has `Population.Infections("Infectious diseases")`, `data class ConditionImage(...)`, and `val image: ConditionImage? = null` on `Condition`.
2. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/InfectionsHL.kt`**
   - Create `infectionsHLSources: List<Source>` (using the `inf-hl-...` IDs above) and `infectionsHLConditions: List<Condition>` (all 8 diseases above with exact IDs, keywords, `image`, `firstSteps`, `info`, `tiers`, `WeightDose`, `maxDose`, `avoid`, `escalate`, and `citations`).
   - Confirm zero unused sources (`ContentIntegrityTest` enforces 100% citation usage).
3. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/Conditions.kt`**
   - Add `Group(infectionsHLConditions, infectionsHLSources)` to `inReviewInfections` (or directly to `releasedInfections` once approved).
4. **Offline Image Bundle (`composeApp/src/commonMain/composeResources/drawable/`)**
   - Append these 8 verified open-licence images to `scripts/fetch-infection-images.sh` (fetch at 800 px width and compress to `.webp` via `cwebp -q 80` if available):
     - `inf_hcv_em` ← `https://commons.wikimedia.org/wiki/Special:FilePath/HCV_EM_picture_2.png?width=800`
     - `inf_herpes_zoster` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Herpes_zoster_back.jpg?width=800`
     - `inf_hiv_budding` ← `https://commons.wikimedia.org/wiki/Special:FilePath/HIV-budding-Color.jpg?width=800`
     - `inf_hookworm_egg` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Necator_americanus,_Ancylostoma_duodenale_egg.jpg?width=800`
     - `inf_influenza_h1n1` ← `https://commons.wikimedia.org/wiki/Special:FilePath/CDC-11215-swine-flu.jpg?width=800`
     - `inf_japanese_encephalitis` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Examination_of_Encephalitis_Patient_-_Bangladesh_(17057553625).jpg?width=800`
     - `inf_kala_azar_ld_bodies` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Leishmania_donovani_amastigotes_in_Giemsa_stained_smear_of_FNAC.jpg?width=800`
     - `inf_leprosy_mb` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Multibacillary_leprosy_lesion.jpg?width=800`
5. **Verification:** Run `./gradlew :composeApp:allTests` to confirm `ContentIntegrityTest` passes.
