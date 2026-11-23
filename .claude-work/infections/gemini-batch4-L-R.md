# Medicalaid — Infections A–Z: Batch 4 (L–R) Clinical Review & Implementation Doc

**Section:** Infectious diseases (`Population.Infections`)  
**Order:** Strict alphabetical — Batch 4 of A–Z (8 diseases, following Batches 1–3: A–L)  
**Sourcing policy:** India first (NCDC, NCVBDC, MoHFW, ICMR, IAP), then WHO and CDC. Every statement, investigation, dose, and caution carries a verified primary source URL.  
**Purpose of this document:** Complete clinician review copy (with all 8 verified Public Domain CDC PHIL clinical photos embedded inline) **and** drop-in specification for Claude Code to add directly into the `Medicalaid` repository (`InfectionsLR.kt`) once reviewed.

---

## Batch 4 Overview (L–R)

| # | Disease | Condition ID | Investigation of Choice (IOC) | First-Line Drug of Choice (DOC) | Open-Licence Photo |
|---|---|---|---|---|---|
| 1 | [Leptospirosis](#1-leptospirosis) | `inf-leptospirosis` | Blood PCR (Week 1); IgM ELISA (Days 5–7+); MAT (gold standard) + Modified Faine's criteria | Mild: Doxycycline 100 mg BD × 7 d (Ampicillin/Amoxicillin in pregnancy/<8 yr); Severe/Weil's: IV Penicillin G 1.5 MU q6h or IV Ceftriaxone 1–2 g OD × 7 d | Yes (`inf_leptospira_sem`, PD) |
| 2 | [Malaria (*P. vivax* & *P. falciparum*)](#2-malaria-p-vivax--p-falciparum) | `inf-malaria` | Giemsa thick & thin peripheral blood smears (gold standard) + bivalent Pf/Pv RDT | *Pv*: Chloroquine + Primaquine 14 d (check G6PD); *Pf*: ACT-AL (NE India) or ACT-SP (rest of India) + Day-2 PQ; Severe: IV Artesunate 2.4 mg/kg (3.0 if <20 kg) | Yes (`inf_malaria_pf_rings`, PD) |
| 3 | [Measles (rubeola)](#3-measles-rubeola) | `inf-measles` | Clinical triad + Koplik spots; Serum anti-measles IgM ELISA (Days 4–28) & throat/NP RT-PCR | Supportive care + mandatory **Vitamin A** (Day 1 & Day 2: 50k / 100k / 200k IU by age); antibiotics ONLY for bacterial superinfection | Yes (`inf_measles_koplik`, PD) |
| 4 | [Mumps](#4-mumps) | `inf-mumps` | Clinical parotitis + buccal swab rRT-PCR (≤3 d post-onset) & serum IgM (>3 d) | Supportive care (paracetamol/ibuprofen, compresses, scrotal support for orchitis) + 5-day droplet isolation | Yes (`inf_mumps_parotitis`, PD) |
| 5 | [Pertussis (whooping cough)](#5-pertussis-whooping-cough) | `inf-pertussis` | Posterior nasopharyngeal aspirate/swab PCR (0–4 wk) & culture (0–2 wk) | Azithromycin × 5 d (preferred in neonates <1 mo); Clarithromycin/Erythromycin (≥1 mo); TMP-SMX (≥2 mo alternative) | Yes (`inf_pertussis_gram`, PD) |
| 6 | [Plague (bubonic, septicaemic & pneumonic)](#6-plague-bubonic-septicaemic--pneumonic) | `inf-plague` | Bubo aspirate / blood / sputum Wayson or Gram stain ('safety-pin' bipolar rods) + culture/PCR + IDSP alert | IV Gentamicin 5 mg/kg OD (or Streptomycin) OR Ciprofloxacin / Levofloxacin OR Doxycycline × 10–14 d (+ Chloramphenicol if meningitis) | Yes (`inf_plague_wayson`, PD) |
| 7 | [Rabies & animal-bite PEP](#7-rabies--animal-bite-pep) | `inf-rabies` | Clinical Category I/II/III exposure triage (NEVER wait for animal testing); ante-mortem saliva PCR + nuchal biopsy DFA + CSF/serum Ab | Immediate 15-min soap/water wash + ID 2-site UTRC (Days 0, 3, 7, 28) or IM Essen (0, 3, 7, 14, 28) + wound infiltration of RIG/RmAb (Cat III) | Yes (`inf_rabies_negri`, PD) |
| 8 | [Rubella (German measles)](#8-rubella-german-measles) | `inf-rubella` | Serum anti-rubella IgM ELISA (Days 4–28) + throat/NP RT-PCR (Days 0–4) + maternal IgG avidity in pregnancy | Supportive care (paracetamol) + 7-day droplet isolation; urgent obstetric/fetal counselling if pregnant (<20 wk CRS risk) | Yes (`inf_rubella_rash`, PD) |

---

## 1. Leptospirosis

- **Condition ID:** `inf-leptospirosis`
- **Population:** `Population.Infections`
- **Summary:** Zoonotic spirochaetal infection (*Leptospira* spp.) after flood/stagnant-water exposure: start empiric doxycycline (mild) or IV penicillin G/ceftriaxone (severe/Weil's) immediately without awaiting IgM ELISA or MAT.
- **Search Keywords:** `leptospirosis`, `Leptospira`, `Weil's disease`, `Weil's syndrome`, `conjunctival suffusion`, `calf tenderness`, `flood fever`, `monsoon fever`, `pulmonary haemorrhage`, `MAT`, `Modified Faine's criteria`, `doxycycline`, `penicillin G`, `ceftriaxone`

### Clinical Photo


- **Resource name (`resName`):** `inf_leptospira_sem`
- **Caption:** Scanning electron micrograph (SEM) depicting *Leptospira* spp. motile spirochetes atop a 0.1 µm polycarbonate filter (CDC PHIL #138).
- **Credit:** CDC / Rob Weyant (CDC PHIL #138)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Leptospira_scanning_micrograph.jpg](https://commons.wikimedia.org/wiki/File:Leptospira_scanning_micrograph.jpg)

### First Steps
1. Suspect early in acute fever + headache + severe calf myalgia + conjunctival suffusion after monsoon/flood-water or animal-urine exposure ([NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf)).
2. Start **empirical antibiotics immediately** without awaiting serology or MAT confirmation ([NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf); [CDC 2026](https://www.cdc.gov/leptospirosis/hcp/clinical-overview/index.html)).
3. Screen for organ dysfunction (jaundice, oliguria/anuria, dyspnoea/haemoptysis, bleeding, altered sensorium) — admit/transfer immediately to District Hospital/ICU if present ([NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf)).
4. Draw baseline CBC, LFT, RFT, CPK, urinalysis, and exclude dengue, malaria, scrub typhus, and enteric fever.

### Screening & Investigation of Choice (IOC)
- **Week 1 (Septicaemic phase) IOC:** Whole-blood/serum **real-time PCR** before seroconversion ([CDC 2026](https://www.cdc.gov/leptospirosis/hcp/clinical-overview/index.html)).
- **Days 5–7+ Screening & Confirmation:** Rapid IgM ICT / **IgM ELISA**; **Microscopic Agglutination Test (MAT)** is the serological gold standard (≥4-fold paired rise or high single endemic titre ≥1:100–1:400) ([NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf)).
- **Modified Faine's Criteria (India):** Clinical (A) + Epidemiological (B) + Lab (C) — presumptive if `A + B ≥ 26` or `A + B + C ≥ 25`.

### Other Investigations & Key Findings
- Weil's syndrome hallmarks: marked conjugated hyperbilirubinaemia with **only mildly elevated AST/ALT**, **markedly elevated serum CPK**, non-oliguric→oliguric AKI, thrombocytopenia, CXR alveolar infiltrates (pulmonary haemorrhage), and ECG arrhythmias/AV block ([NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf)).

### Treatment Tiers

#### Tier 1: Drug of choice — Mild & Severe (Weil's Syndrome) Leptospirosis
- **Doxycycline PO (Mild / Anicteric Adults & Children ≥8 yr)**
  - **Regimen:** `Adults: 100 mg PO twice daily × 7 days; Children ≥8 yr: 2 mg/kg/day PO in 2 divided doses (max 200 mg/day) × 7 days`
  - **Max Dose:** `200 mg/day`
  - **Weight-based calculator (`WeightDose`):** `2.0 mg/kg per day PO in 2 divided doses × 7 days (≥8 yr; max 200 mg/day)`
  - **Cautions:** Avoid in pregnancy, lactation, and children <8 yr (NCDC India).
  - **Citations:** [NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf), [CDC 2026](https://www.cdc.gov/leptospirosis/hcp/clinical-overview/index.html)
- **IV Penicillin G OR IV Ceftriaxone (Severe / Weil's Syndrome)**
  - **Regimen:** `Penicillin G 1.5 million units (MU) IV every 6 h × 7 days OR Ceftriaxone 1 g IV once daily (1–2 g OD in severe/CNS disease) × 7 days`
  - **Cautions:** Watch for Jarisch–Herxheimer reaction; adjust penicillin G in severe AKI.
  - **Citations:** [NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf), [CDC 2026](https://www.cdc.gov/leptospirosis/hcp/clinical-overview/index.html)

#### Tier 2: Pregnancy, Children <8 yr, & Flood Outbreak Chemoprophylaxis
- **Ampicillin / Amoxicillin / Azithromycin (Pregnancy & Children <8 yr)**
  - **Regimen:** `Pregnancy/lactation: Ampicillin 500 mg PO q6h × 7 d (NCDC) OR Amoxicillin 500 mg q6h × 7 d / Azithromycin 500 mg OD × 3 d (CDC). Children <8 yr: Amoxicillin/Ampicillin 30–50 mg/kg/day PO in divided doses × 7 d.`
  - **Citations:** [NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf), [CDC 2026](https://www.cdc.gov/leptospirosis/hcp/clinical-overview/index.html)
- **Flood Outbreak Chemoprophylaxis (India)**
  - **Regimen:** `Doxycycline 200 mg PO once weekly for up to 6 weeks (never >8 weeks) during high-risk flood/agricultural exposure (avoid in pregnancy & <8 yr)`
  - **Citations:** [IMA/India Protocol](https://www.ima-india.org/ima/pdfdata/Treatment-Algorithm-Flip-Chart-03122021.pdf), [CDC 2026](https://www.cdc.gov/leptospirosis/hcp/clinical-overview/index.html)

### Avoid
- **Delaying antibiotics awaiting MAT/IgM**, **doxycycline in pregnancy/lactation/<8 yr**, **weekly doxycycline prophylaxis >8 weeks**, and **relying on negative week-1 rapid tests to rule out severe disease** ([NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf)).

### Escalate / Red Flags
- Jaundice, oliguria/anuria/AKI, dyspnoea or haemoptysis (pulmonary haemorrhage/ARDS), shock, arrhythmias/AV block, meningitis/altered sensorium, bleeding, or any leptospirosis in pregnancy — immediate ICU/tertiary referral ([NCDC India 2015](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf)).

---

## 2. Malaria (*P. vivax* & *P. falciparum*)

- **Condition ID:** `inf-malaria`
- **Population:** `Population.Infections`
- **Summary:** *Anopheles*-borne *Plasmodium* infection: confirm by thick/thin smear or bivalent RDT; treat *P. vivax* with chloroquine + 14-day primaquine (after G6PD check), uncomplicated *P. falciparum* with regional ACT + Day-2 primaquine, and severe malaria immediately with IV artesunate.
- **Search Keywords:** `malaria`, `Plasmodium vivax`, `Plasmodium falciparum`, `ACT-SP`, `ACT-AL`, `artemether-lumefantrine`, `artesunate`, `chloroquine`, `primaquine`, `G6PD`, `severe malaria`, `cerebral malaria`, `bivalent RDT`, `NCVBDC`

### Clinical Photo


- **Resource name (`resName`):** `inf_malaria_pf_rings`
- **Caption:** Peripheral blood smear photomicrograph (1150×) revealing numerous *Plasmodium falciparum* ring-form trophozoites inside erythrocytes (CDC PHIL #4885).
- **Credit:** CDC Public Health Image Library (PHIL #4885)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Plasmodium_falciparum_rings_form_parasites4885_lores.jpg](https://commons.wikimedia.org/wiki/File:Plasmodium_falciparum_rings_form_parasites4885_lores.jpg)

### First Steps
1. Confirm parasitologically via **thick & thin blood smear** or **bivalent Pf/Pv RDT** before treating; never give presumptive chloroquine ([NCVBDC 2013](https://ncvbdc.mohfw.gov.in/Doc/Diagnosis-Treatment-Malaria-2013.pdf); [WHO](https://www.who.int/teams/global-malaria-programme/case-management/treatment)).
2. Screen immediately for severe malaria danger signs (GCS, convulsions, hypoglycaemia, SpO2, oliguria, shock, inability to take oral drugs) — start **IV artesunate immediately** if any severe sign is present ([NCVBDC 2014](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/20627628441542176662.pdf)).
3. Match therapy to species (*Pv*, *Pf*, mixed), Indian region (North-East = ACT-AL; rest of India = ACT-SP), pregnancy status, and infant age ([NCVBDC 2013](https://ncvbdc.mohfw.gov.in/Doc/National-Drug-Policy-2013.pdf)).
4. Counsel taking ACT-AL with fatty food/milk and primaquine after meals; check G6PD before 14-day primaquine.

### Screening & Investigation of Choice (IOC)
- **Gold Standard IOC:** **Giemsa-stained thick and thin peripheral blood smears** (thick = high sensitivity; thin = species ID & parasite density %; repeat daily in severe malaria until negative) ([NCVBDC 2013](https://ncvbdc.mohfw.gov.in/Doc/Diagnosis-Treatment-Malaria-2013.pdf); [CDC Severe Malaria](https://www.cdc.gov/malaria/hcp/clinical-guidance/treatment-of-severe-malaria-1.html)).
- **Point-of-Care Screening:** **Bivalent RDT** (Pf HRP-2 + Pv/pan pLDH) where timely microscopy is unavailable ([NCVBDC 2013](https://ncvbdc.mohfw.gov.in/Doc/Diagnosis-Treatment-Malaria-2013.pdf)).

### Other Investigations & Key Findings
- **G6PD testing** before 14-day primaquine. Severe malaria thresholds: glucose `<40 mg/dL`, lactate `>5 mmol/L` or `pH <7.3`, creatinine `>3 mg/dL`, bilirubin `>3 mg/dL` with `>100,000 parasites/µL`, Hb `<7 g/dL` (adults) / `<5 g/dL` (children), or hyperparasitaemia (`≥5–10%`). Monitor weekly for 4 weeks post-IV artesunate for **post-artesunate delayed haemolysis (PADH)** ([CDC Severe Malaria](https://www.cdc.gov/malaria/hcp/clinical-guidance/treatment-of-severe-malaria-1.html)).

### Treatment Tiers

#### Tier 1: Drug of choice — Uncomplicated *P. vivax*, Uncomplicated *P. falciparum*, & Severe Malaria
- **Uncomplicated *P. vivax* (NCVBDC India)**
  - **Regimen:** `Chloroquine 25 mg base/kg total over 3 days (10 mg/kg D1, 10 mg/kg D2, 5 mg/kg D3; adult max 1,500 mg base) PLUS Primaquine 0.25 mg base/kg PO OD × 14 days (adult 15 mg/day; after G6PD check)`
  - **Weight-based calculator (`WeightDose`):** `Chloroquine 10.0 mg/kg D1–D2, 5.0 mg/kg D3; Primaquine 0.25 mg/kg OD × 14 d`
  - **Cautions:** Stop PQ immediately if dark urine/haemolysis. Mild/moderate G6PD deficiency (WHO): PQ 0.75 mg/kg once weekly × 8 wk under supervision. Omit PQ in pregnancy & infants <1 yr.
  - **Citations:** [NCVBDC 2013](https://ncvbdc.mohfw.gov.in/Doc/Diagnosis-Treatment-Malaria-2013.pdf), [WHO Guidelines 2026](https://www.who.int/publications/i/item/guidelines-for-malaria)
- **Uncomplicated *P. falciparum* & Mixed Infection (NCVBDC India)**
  - **Regimen:** `North-East India: ACT-AL (Artemether 20 / Lumefantrine 120 mg) BD × 3 d (6 doses: 5–<15 kg 1 tab; 15–<25 kg 2 tabs; 25–<35 kg 3 tabs; ≥35 kg 4 tabs/dose with fatty food). Rest of India: ACT-SP (Artesunate 4 mg/kg OD × 3 d + SP 25/1.25 mg/kg D1). PLUS single-dose Primaquine Day 2 (0.75 mg/kg NCVBDC; 0.25 mg/kg WHO) — or 14-day PQ if mixed Pv+Pf.`
  - **Cautions:** Omit PQ in pregnancy & infants <1 yr.
  - **Citations:** [NCVBDC 2013](https://ncvbdc.mohfw.gov.in/Doc/National-Drug-Policy-2013.pdf), [NCVBDC ASHA 2025](https://ncvbdc.mohfw.gov.in/Doc/Malaria-Training-Module-ASHA-2025.pdf)
- **Severe Malaria (Any Species — Medical Emergency DOC)**
  - **Regimen:** `IV Artesunate at 0, 12, and 24 h, then once daily (min 24 h) until oral tolerated → followed by full 3-day oral ACT. Dose: ≥20 kg & adults = 2.4 mg/kg/dose IV/IM; <20 kg = 3.0 mg/kg/dose IV/IM.`
  - **Weight-based calculator (`WeightDose`):** `2.4 mg/kg IV (3.0 mg/kg if <20 kg) at 0, 12, 24 h then OD`
  - **Cautions:** Reconstitute 60 mg vial in 5% NaHCO3 + dilute in 5% dextrose/NS; add 14-day PQ if *P. vivax*. WHO recommends IV artesunate in all trimesters of pregnancy for severe malaria.
  - **Citations:** [NCVBDC 2014](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/20627628441542176662.pdf), [CDC Severe Malaria](https://www.cdc.gov/malaria/hcp/clinical-guidance/treatment-of-severe-malaria-1.html)

#### Tier 2: Pregnancy, Severe Alternatives, & ACT Failure
- **Pregnancy (Uncomplicated) & Severe Alternatives**
  - **Regimen:** `1st-trimester Pf: Quinine salt 10 mg/kg PO TID × 7 d (+ Clindamycin 20 mg/kg/d TID × 7 d) per NCVBDC — WHO 2024 & CDC now endorse ACT-AL as preferred 1st-trimester option. 2nd/3rd-trimester Pf: regional ACT. Pv (any trimester): Chloroquine (NO Primaquine). Severe fallback if IV artesunate unavailable: IM Artemether (3.2 mg/kg load then 1.6 mg/kg OD) OR IV Quinine infusion (20 mg/kg over 4 h, then 10 mg/kg q8h — NEVER IV bolus).`
  - **Citations:** [NCVBDC 2013](https://ncvbdc.mohfw.gov.in/Doc/National-Drug-Policy-2013.pdf), [WHO Pregnancy 2024](https://www.who.int/publications/i/item/9789240069404), [CDC Treatment Tables](https://www.cdc.gov/malaria/hcp/clinical-guidance/appendix-a-treatment-tables.html)

### Avoid
- **Primaquine in pregnancy, infants <1 yr (WHO <6 mo), or severe G6PD deficiency**, **chloroquine for *P. falciparum* in India**, **oral artemisinin monotherapy**, **IV bolus quinine** (fatal arrhythmia), and **α-β arteether in children** ([NCVBDC 2013](https://ncvbdc.mohfw.gov.in/Doc/National-Drug-Policy-2013.pdf); [NCVBDC 2014](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/20627628441542176662.pdf)).

### Escalate / Red Flags
- Coma (GCS <11), prostration, ≥2 seizures, severe anaemia, hypoglycaemia (<40 mg/dL), acidosis, AKI, ARDS/SpO2 <92%, shock, jaundice, DIC, or persistent vomiting — immediate IV artesunate + ICU; pre-referral rectal/IM artesunate in remote settings for children <6 yr ([NCVBDC 2014](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/20627628441542176662.pdf)).

---

## 3. Measles (rubeola)

- **Condition ID:** `inf-measles`
- **Population:** `Population.Infections`
- **Summary:** Highly contagious airborne *Morbillivirus* illness with fever, cough/coryza/conjunctivitis, Koplik spots, and descending maculopapular rash; isolate immediately, notify IDSP, and give mandatory age-specific oral Vitamin A on Days 1 & 2.
- **Search Keywords:** `measles`, `rubeola`, `khasra`, `morbillivirus`, `koplik spots`, `maculopapular rash`, `three Cs`, `vitamin A`, `MR vaccine`, `MMR`, `SSPE`, `xerophthalmia`, `airborne isolation`

### Clinical Photo


- **Resource name (`resName`):** `inf_measles_koplik`
- **Caption:** Pathognomonic Koplik spots on the buccal mucosa on the third pre-eruptive day of measles, appearing as bright-red macules with bluish-white central dots.
- **Credit:** CDC Public Health Image Library (PHIL #6111)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Koplik_spots,_measles_6111_lores.jpg](https://commons.wikimedia.org/wiki/File:Koplik_spots,_measles_6111_lores.jpg)

### First Steps
1. Isolate immediately under **airborne precautions** (N95 for staff) for ≥4 days post-rash onset (entire illness if severely immunocompromised/malnourished) ([CDC 2026](https://www.cdc.gov/measles/hcp/clinical-overview/index.html); [WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154436/)).
2. Notify IDSP / VPD surveillance immediately (single suspected case is mandatory) and collect serum IgM + throat/NP swab RT-PCR ([NCDC VPD 2026](https://ncdc.mohfw.gov.in/uploads/resource/1785229413_OG_VPD%20Surveillance_15.07.26.pdf)).
3. Give the first age-specific oral **Vitamin A** dose immediately on clinical diagnosis + supportive fluids/nutrition/paracetamol, and assess susceptible contacts for PEP ([WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154436/)).

### Screening & Investigation of Choice (IOC)
- **Clinical:** Fever + cephalocaudal maculopapular rash + cough/coryza/conjunctivitis + pathognomonic **Koplik spots** ([WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154436/)).
- **IOC:** Serum **anti-measles IgM ELISA** (optimal **days 4–28 post-rash**; repeat if drawn <72 h and negative) **+** throat/NP swab **RT-PCR** (early ≤5–7 d post-rash) ([WHO Lab Manual Ch. 4](https://www.who.int/docs/default-source/immunization/vpd_surveillance/lab_networks/measles_rubella/manual/chapter-4.pdf?sfvrsn=ec496bf2_2); [CDC 2026](https://www.cdc.gov/measles/hcp/clinical-overview/index.html)).

### Other Investigations & Key Findings
- Pulse oximetry/CXR if respiratory signs (>50% of measles pneumonia has bacterial superinfection), otoscopy (acute otitis media), and corneal exam for **xerophthalmia/corneal clouding**. Late fatal complication: SSPE (7–10 yr post-infection) ([WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154436/); [CDC 2026](https://www.cdc.gov/measles/hcp/clinical-overview/index.html)).

### Treatment Tiers

#### Tier 1: Mandatory Vitamin A Protocol & Supportive Care
- **Oral Vitamin A (Mandatory for ALL children with measles)**
  - **Regimen:** `Day 1 at diagnosis AND Day 2 (plus 3rd dose at 2–4 wk if eye signs/severe malnutrition): <6 months = 50,000 IU PO; 6–11 months = 100,000 IU PO; ≥12 months = 200,000 IU PO`
  - **Cautions:** Avoid excessive dosing (hypervitaminosis A); **contraindicated at high doses in pregnancy** (teratogenic).
  - **Citations:** [WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154436/), [CDC 2026](https://www.cdc.gov/measles/hcp/clinical-overview/index.html)
- **Supportive Care (Paracetamol, Hydration, Eye/Mouth Care)**
  - **Regimen:** `Paracetamol 10–15 mg/kg/dose PO q4–6h (adult 500–1000 mg q6h, max 4 g/d); ORS/nutrition; purulent conjunctivitis → Tetracycline eye ointment TID × 7 days`
  - **Citations:** [WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154436/)

#### Tier 2: Secondary Bacterial Superinfection & Post-Exposure Prophylaxis (PEP)
- **Secondary Bacterial Pneumonia / Otitis Media (ONLY if bacterial superinfection evident)**
  - **Regimen:** `Non-severe: Oral Amoxicillin 40 mg/kg/dose BD × ≥5 days. Severe hospitalized: IV Ampicillin 50 mg/kg q6h + IV Gentamicin 7.5 mg/kg OD (or IV Ceftriaxone 80–100 mg/kg OD).`
  - **Citations:** [WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154436/)
- **Post-Exposure Prophylaxis (PEP) & UIP Prevention**
  - **Regimen:** `Immunocompetent non-pregnant ≥6 mo: MR/MMR vaccine within 72 hours. Infants <6 mo, pregnant, or severely immunocompromised: Immune Globulin within 6 days — IGIM 0.5 mL/kg IM (max 15 mL) OR IGIV 400 mg/kg IV (preferred in pregnancy/immunocompromised). NEVER co-administer MMR + IG together. India UIP: MR at 9–12 mo & 16–24 mo (IAP: MMR at 9 mo, 15 mo, 4–6 yr).`
  - **Citations:** [CDC Vaccine PEP](https://www.cdc.gov/measles/hcp/vaccine-considerations/index.html), [CDC IG PEP](https://www.cdc.gov/measles/downloads/measles-immune-globulin-post-exposure-prophylaxis.pdf), [MoHFW U-WIN](https://uwin.mohfw.gov.in/)

### Avoid
- **Omitting Vitamin A**, **aspirin in children** (Reye syndrome), **routine antibiotics in uncomplicated measles**, **steroid eye ointments** (corneal perforation) or **steroids for measles croup**, **live MR/MMR in pregnancy/severe immunosuppression**, and **co-administering MMR + IG** ([WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154436/); [CDC 2026](https://www.cdc.gov/measles/hcp/clinical-overview/index.html)).

### Escalate / Red Flags
- Inability to drink, convulsions, lethargy/coma (encephalitis), stridor/croup, chest indrawing or SpO2 ≤90%, corneal clouding/ulceration, severe malnutrition, or infection in infants <12 mo/pregnancy/immunocompromised ([WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154436/)).

---

## 4. Mumps

- **Condition ID:** `inf-mumps`
- **Population:** `Population.Infections`
- **Summary:** Droplet-transmitted *Rubulavirus* causing painful parotid swelling: isolate for 5 days post-parotitis onset, confirm with buccal swab rRT-PCR (≤3 d), treat supportively, and monitor for orchitis, aseptic meningitis, pancreatitis, or sensorineural deafness.
- **Search Keywords:** `mumps`, `epidemic parotitis`, `galsua`, `rubulavirus`, `parotid swelling`, `orchitis`, `aseptic meningitis`, `buccal swab RT-PCR`, `MMR vaccine`, `droplet isolation`, `sensorineural deafness`

### Clinical Photo


- **Resource name (`resName`):** `inf_mumps_parotitis`
- **Caption:** Characteristic parotid salivary gland swelling obscuring the angle of the mandible in a child with mumps.
- **Credit:** CDC / Barbara Rice (CDC PHIL #130)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Mumps_PHIL_130_lores.jpg](https://commons.wikimedia.org/wiki/File:Mumps_PHIL_130_lores.jpg)

### First Steps
1. Isolate under **droplet precautions** until **5 days after parotitis onset** ([CDC Mumps 2025](https://www.cdc.gov/mumps/hcp/clinical-overview/index.html)).
2. Massage the affected parotid gland for 30 s and collect a **buccal swab for rRT-PCR** (ideally ≤3 days of onset); notify IDSP ([CDC Mumps Lab 2024](https://www.cdc.gov/mumps/php/laboratories/index.html)).
3. Start supportive analgesia (paracetamol/ibuprofen), warm/cold compresses, soft non-acidic diet, and screen for orchitis, meningitis, pancreatitis, or hearing loss ([CDC Pink Book Ch. 15](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-15-mumps.html)).

### Screening & Investigation of Choice (IOC)
- **IOC (≤3 days post-onset):** **Buccal/oral swab rRT-PCR** after 30 s parotid massage (sensitivity drops sharply >10 days) ([CDC Mumps Lab 2024](https://www.cdc.gov/mumps/php/laboratories/index.html)).
- **>3 days post-onset:** Buccal swab rRT-PCR **+** serum **mumps IgM** (note: IgM may be negative in previously vaccinated patients; negative test does not exclude mumps) ([CDC Pink Book Ch. 15](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-15-mumps.html)).

### Other Investigations & Key Findings
- Orchitis (~30% unvaccinated post-pubertal males): urgent **scrotal Color Doppler USG** to rule out **testicular torsion**. Pancreatitis: **serum lipase** (total amylase is raised by parotitis alone). Meningitis: CSF lymphocytic pleocytosis. Hearing loss: pure-tone audiometry ([CDC Pink Book Ch. 15](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-15-mumps.html)).

### Treatment Tiers

#### Tier 1: Supportive Care (No Specific Antiviral Exists)
- **Paracetamol or Ibuprofen + Local Care**
  - **Regimen:** `Paracetamol 10–15 mg/kg/dose PO q4–6h (adult 500–1000 mg q6h, max 4 g/d) OR Ibuprofen (≥6 mo) 5–10 mg/kg/dose PO q6–8h (max 40 mg/kg/d; adult 200–400 mg q4–6h). Warm/cold parotid compresses; soft non-acidic diet; 5-day droplet isolation.`
  - **Cautions:** Avoid ibuprofen in dehydration, AKI, GI bleed, or pregnancy >20 wk.
  - **Citations:** [StatPearls Mumps](https://www.ncbi.nlm.nih.gov/books/NBK534785/), [CDC Mumps 2025](https://www.cdc.gov/mumps/hcp/clinical-overview/index.html)

#### Tier 2: Orchitis Support & MMR Prevention
- **Mumps Orchitis Support & IAP/WHO MMR Prevention**
  - **Regimen:** `Orchitis: bed rest, scrotal elevation/support, ice packs, NSAIDs (steroids have NO proven benefit). Prevention: IAP ACVIP recommends 3 doses of MMR at 9 mo, 15 mo, and 4–6 yr (India UIP uses MR without mumps); WHO/CDC recommend 2 routine MMR doses + 3rd dose in outbreaks.`
  - **Citations:** [IAP ACVIP Mumps](https://acvip.org/parents/columns/mumps.php), [WHO Mumps 2024](https://www.who.int/publications/i/item/who-wer-9911-115-133)

### Avoid
- **Aspirin in children/adolescents** (Reye syndrome), **routine antibiotics** in uncomplicated viral mumps, **routine steroids for orchitis**, **relying on post-exposure MMR/IG** to prevent mumps, and **live MMR in pregnancy/severe immunosuppression** ([CDC ACIP 2013](https://www.cdc.gov/mmwr/preview/mmwrhtml/rr6204a1.htm); [StatPearls Mumps](https://www.ncbi.nlm.nih.gov/books/NBK534785/)).

### Escalate / Red Flags
- Severe headache/neck stiffness/altered sensorium (meningitis/encephalitis), acute severe scrotal pain (urgent Doppler USG for testicular torsion), severe epigastric pain/vomiting (pancreatitis), sudden hearing loss/vertigo (ENT/audiology), or fluctuant purulent parotitis (bacterial parotitis) ([CDC Pink Book Ch. 15](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-15-mumps.html)).

---

## 5. Pertussis (whooping cough)

- **Condition ID:** `inf-pertussis`
- **Population:** `Population.Infections`
- **Summary:** Highly contagious *Bordetella pertussis* respiratory infection causing paroxysmal cough, inspiratory whoop, post-tussive vomiting, and life-threatening infant apnoea; confirm by posterior nasopharyngeal PCR and treat early with azithromycin (preferred in neonates <1 mo).
- **Search Keywords:** `pertussis`, `whooping cough`, `Bordetella pertussis`, `paroxysmal cough`, `inspiratory whoop`, `post-tussive vomiting`, `infant apnoea`, `azithromycin`, `erythromycin`, `IHPS`, `Tdap`, `DPT`

### Clinical Photo


- **Resource name (`resName`):** `inf_pertussis_gram`
- **Caption:** Gram stain of *Bordetella pertussis* showing tiny Gram-negative coccobacilli (CDC PHIL #2121).
- **Credit:** CDC Public Health Image Library (PHIL #2121)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Bordetella_pertussis.jpg](https://commons.wikimedia.org/wiki/File:Bordetella_pertussis.jpg)

### First Steps
1. Enforce **droplet precautions** until 5 days of effective antibiotic therapy are completed (or 21 days from cough onset if untreated) ([CDC Pertussis Treatment 2026](https://www.cdc.gov/pertussis/hcp/clinical-care/index.html)).
2. Admit all **infants <6 months** (especially <3 months) or any child with apnoea, cyanosis, hypoxia, or feeding difficulty for cardiorespiratory monitoring.
3. Collect a **posterior nasopharyngeal aspirate or Dacron/rayon/flocked swab** for PCR/culture (never throat or cotton/calcium-alginate swabs) and start **empiric azithromycin** immediately without awaiting results in high-risk/infant cases ([CDC Pertussis PCR 2025](https://www.cdc.gov/pertussis/php/pcr-bestpractices/index.html); [CDC Pertussis Treatment 2026](https://www.cdc.gov/pertussis/hcp/clinical-care/index.html)).

### Screening & Investigation of Choice (IOC)
- **IOC (0–4 weeks of cough):** **Posterior nasopharyngeal aspirate/swab PCR** (optimal sensitivity in first 3 weeks; sensitivity drops after week 4 or >5 days of antibiotics) ([CDC Pertussis Lab 2024](https://www.cdc.gov/pertussis/php/laboratories/index.html); [CDC Pertussis PCR 2025](https://www.cdc.gov/pertussis/php/pcr-bestpractices/index.html)).
- **Gold Standard (0–2 weeks):** **NP culture** (also enables macrolide-resistance testing — monitor for A2047G 23S rRNA resistance in Asia) ([CDC Pertussis AMR 2025](https://www.cdc.gov/pertussis/hcp/antibiotic-resistance/index.html)).

### Other Investigations & Key Findings
- Three stages: catarrhal (1–2 wk, most infectious), paroxysmal (staccato cough, inspiratory whoop, post-tussive vomiting), convalescent. In infants, CBC shows marked **lymphocytosis** — extreme leukocytosis (`>50,000–100,000/µL`) predicts pulmonary hypertension and high mortality. Treat within 3 weeks of cough onset (≥1 yr) or up to **6 weeks** in infants <1 yr and 3rd-trimester pregnant women ([CDC Pertussis Treatment 2026](https://www.cdc.gov/pertussis/hcp/clinical-care/index.html)).

### Treatment Tiers

#### Tier 1: Drug of choice — Macrolides (Treatment & Post-Exposure Prophylaxis)
- **Azithromycin PO (First-line — ONLY preferred macrolide in neonates <1 month)**
  - **Regimen:** `Infants <6 mo (incl. <1 mo): 10 mg/kg PO OD × 5 days. Infants/children ≥6 mo: 10 mg/kg (max 500 mg) Day 1, then 5 mg/kg (max 250 mg) OD Days 2–5. Adults: 500 mg Day 1, then 250 mg OD Days 2–5.`
  - **Max Dose:** `500 mg Day 1; 250 mg/day Days 2–5`
  - **Weight-based calculator (`WeightDose`):** `10.0 mg/kg OD × 5 d (<6 mo); 10.0 mg/kg D1 then 5.0 mg/kg D2–5 (≥6 mo)`
  - **Cautions:** Monitor neonates <1 mo for infantile hypertrophic pyloric stenosis (IHPS — risk of severe pertussis outweighs IHPS risk). Same regimen for PEP in household/high-risk contacts (within 21 days of exposure).
  - **Citations:** [CDC Pertussis Treatment 2026](https://www.cdc.gov/pertussis/hcp/clinical-care/index.html), [CDC MMWR RR5414](https://www.cdc.gov/mmwr/preview/mmwrhtml/rr5414a1.htm)
- **Clarithromycin or Erythromycin (Alternatives for ≥1 month ONLY)**
  - **Regimen:** `Clarithromycin (≥1 mo): 15 mg/kg/day in 2 divided doses × 7 d (adults 500 mg BD × 7 d; avoid in pregnancy). Erythromycin (≥1 mo): 40–50 mg/kg/day in 4 divided doses × 14 d (adults 500 mg QID × 14 d).`
  - **Citations:** [CDC MMWR RR5414](https://www.cdc.gov/mmwr/preview/mmwrhtml/rr5414a1.htm)

#### Tier 2: Second-Line (Macrolide Allergy or Suspected Resistance — ≥2 months ONLY)
- **Trimethoprim–Sulfamethoxazole (TMP-SMX)**
  - **Regimen:** `Children ≥2 mo: TMP 8 mg/kg/day + SMX 40 mg/kg/day PO in 2 divided doses × 14 days. Adults: TMP 320 mg + SMX 1,600 mg/day (1 DS tab BD) × 14 days.`
  - **Weight-based calculator (`WeightDose`):** `TMP 8.0 mg/kg/day in 2 divided doses × 14 days (≥2 mo)`
  - **Cautions:** **Contraindicated** in infants <2 months, pregnancy, and nursing mothers (kernicterus risk). Prevention: India UIP Pentavalent (6, 10, 14 wk) + DPT boosters (16–24 mo, 5–6 yr); maternal Tdap at 27–36 weeks pregnancy.
  - **Citations:** [CDC MMWR RR5414](https://www.cdc.gov/mmwr/preview/mmwrhtml/rr5414a1.htm), [CDC Pertussis Treatment 2026](https://www.cdc.gov/pertussis/hcp/clinical-care/index.html)

### Avoid
- **Erythromycin or clarithromycin in neonates <1 month** (high IHPS risk; use azithromycin), **TMP-SMX in <2 mo or pregnancy**, **throat/cotton swabs for PCR**, and **cough suppressants/antihistamines/steroids** (ineffective and unsafe in infants) ([CDC Pertussis Treatment 2026](https://www.cdc.gov/pertussis/hcp/clinical-care/index.html); [CDC Pertussis PCR 2025](https://www.cdc.gov/pertussis/php/pcr-bestpractices/index.html)).

### Escalate / Red Flags
- Apnoea, cyanotic spells, hypoxia, seizures, inability to feed, extreme leukocytosis (`>50,000/µL`), or any infant <6 months — immediate hospital/PICU admission ([CDC Pertussis Treatment 2026](https://www.cdc.gov/pertussis/hcp/clinical-care/index.html)).

---

## 6. Plague (bubonic, septicaemic & pneumonic)

- **Condition ID:** `inf-plague`
- **Population:** `Population.Infections`
- **Summary:** Rapidly fatal zoonotic *Yersinia pestis* infection (flea-borne bubonic/septicaemic or droplet-spread pneumonic): isolate pneumonic suspects immediately, notify IDSP/NCDC, and start IV gentamicin or ciprofloxacin/doxycycline without awaiting culture.
- **Search Keywords:** `plague`, `Yersinia pestis`, `bubonic plague`, `pneumonic plague`, `septicaemic plague`, `bubo`, `Wayson stain`, `safety-pin`, `gentamicin`, `streptomycin`, `ciprofloxacin`, `doxycycline`, `Black Death`, `NCDC`

### Clinical Photo


- **Resource name (`resName`):** `inf_plague_wayson`
- **Caption:** Wayson stain of *Yersinia pestis* showing the characteristic bipolar "safety-pin" staining appearance of the coccobacilli (CDC).
- **Credit:** Centers for Disease Control and Prevention (CDC)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Yersinia_pestis_wayson.jpg](https://commons.wikimedia.org/wiki/File:Yersinia_pestis_wayson.jpg)

### First Steps
1. Place any suspected **pneumonic plague** patient under strict **droplet isolation** immediately (mask, eye protection, gown, gloves) until ≥48 h of effective antibiotics with clinical improvement ([CDC Plague 2021](https://www.cdc.gov/mmwr/volumes/70/rr/rr7003a1.htm); [WHO Plague 2026](https://www.who.int/news-room/fact-sheets/detail/plague)).
2. Collect blood cultures, bubo aspirate, or sputum before antibiotics **only if immediate**, and start **IV antibiotics immediately** (untreated pneumonic/septicaemic plague is ~100% fatal; survival is high if treated within 24 h) ([WHO Plague 2026](https://www.who.int/news-room/fact-sheets/detail/plague); [CDC Plague 2024](https://www.cdc.gov/plague/hcp/clinical-care/index.html)).
3. Notify **IDSP / NCDC Plague Surveillance Unit** immediately and identify close contacts (<6 feet of pneumonic case) for 7-day PEP ([NCDC Bengaluru PSU](https://ncdc.mohfw.gov.in/includes/Branches/Bengaluru.php); [CDC Plague 2021](https://www.cdc.gov/mmwr/volumes/70/rr/rr7003a1.htm)).

### Screening & Investigation of Choice (IOC)
- **IOC:** **Bubo aspirate, blood, or sputum** **Wayson / Giemsa / Gram stain** showing bipolar **"safety-pin" Gram-negative coccobacilli**, confirmed by **culture** (biosafety alert to lab), **PCR**, or **F1 antigen RDT** ([CDC Plague 2021](https://www.cdc.gov/mmwr/volumes/70/rr/rr7003a1.htm)).

### Other Investigations & Key Findings
- Three forms: **Bubonic** (tender regional lymphadenitis/bubo, 30–60% untreated fatality), **Septicaemic** (DIC, shock, acral cyanosis/necrosis = "Black Death"), **Pneumonic** (incubation as short as 24 h, haemoptysis, multilobar pneumonia, person-to-person droplet spread — invariably fatal untreated) ([WHO Plague 2026](https://www.who.int/news-room/fact-sheets/detail/plague)).

### Treatment Tiers

#### Tier 1: Drug of choice — Pneumonic, Septicaemic, & Bubonic Plague (10–14 Days)
- **Gentamicin IV/IM OR Ciprofloxacin / Levofloxacin (First-line — CDC 2021)**
  - **Regimen:** `Gentamicin: Adults 5 mg/kg IV/IM OD (or 2 mg/kg load then 1.7 mg/kg q8h); Children 2.5 mg/kg IV/IM q8h. OR Ciprofloxacin: Adults 400 mg IV q8–12h (500–750 mg PO BD). OR Levofloxacin 500–750 mg IV/PO OD. Alternative first-line (bubonic): Doxycycline 100 mg IV/PO BD (or Streptomycin 1 g IM q12h). Total duration: 10–14 days (step down to oral once improving).`
  - **Weight-based calculator (`WeightDose`):** `Gentamicin 5.0 mg/kg IV/IM OD (adults); 2.5 mg/kg IV/IM q8h (children)`
  - **Cautions:** Use **dual therapy** (two distinct classes, e.g., Gentamicin + Ciprofloxacin/Levofloxacin) in severe pneumonic/septicaemic plague or **pregnancy** ([CDC Plague 2021](https://www.cdc.gov/mmwr/volumes/70/rr/rr7003a1.htm)).
  - **Citations:** [CDC Plague 2021](https://www.cdc.gov/mmwr/volumes/70/rr/rr7003a1.htm), [CDC Plague Antibiotics](https://www.cdc.gov/plague/resources/recommended-antibiotics-for-plague-web-site-rev-jan2018-p.pdf)

#### Tier 2: Plague Meningitis & Close-Contact Post-Exposure Prophylaxis (7 Days)
- **Plague Meningitis & PEP**
  - **Regimen:** `Meningitis: Dual therapy with IV Chloramphenicol + Moxifloxacin or Levofloxacin (continue entire regimen ≥10 additional days). Close-contact PEP (pneumonic exposure <6 ft): Oral Ciprofloxacin 500 mg BD OR Levofloxacin 500–750 mg OD OR Doxycycline 100 mg BD × 7 days (short-course doxycycline ≤21 d is safe in children <8 yr for plague).`
  - **Citations:** [CDC Plague 2021](https://www.cdc.gov/mmwr/volumes/70/rr/rr7003a1.htm)

### Avoid
- **Beta-lactams (penicillins, cephalosporins) and macrolides** (clinically ineffective against *Y. pestis*), **delaying antibiotics awaiting culture**, and **routine incision/drainage of non-suppurative buboes** (infectious aerosol/bleeding risk) ([CDC Plague 2021](https://www.cdc.gov/mmwr/volumes/70/rr/rr7003a1.htm)).

### Escalate / Red Flags
- Bloody sputum/dyspnoea/hypoxia (pneumonic plague), septic shock/DIC/acral necrosis (septicaemic plague), nuchal rigidity/altered sensorium (plague meningitis), or pregnancy — immediate ICU isolation + dual IV antibiotics + urgent public health alert ([WHO Plague 2026](https://www.who.int/news-room/fact-sheets/detail/plague); [CDC Plague 2021](https://www.cdc.gov/mmwr/volumes/70/rr/rr7003a1.htm)).

---

## 7. Rabies & animal-bite PEP

- **Condition ID:** `inf-rabies`
- **Population:** `Population.Infections`
- **Summary:** Virtually 100% fatal lyssavirus encephalomyelitis (~97% dog bites in India): wash all bite wounds ≥15 min with soap and running water, classify Category I/II/III, and give immediate rabies vaccine + Category III wound RIG/RmAb infiltration without waiting for animal testing.
- **Search Keywords:** `rabies`, `animal bite`, `dog bite`, `PEP`, `post-exposure prophylaxis`, `hydrophobia`, `aerophobia`, `ARV`, `RIG`, `eRIG`, `hRIG`, `RmAb`, `Rabishield`, `Twinrab`, `Updated Thai Red Cross`, `Essen regimen`, `NRCP`

### Clinical Photo


- **Resource name (`resName`):** `inf_rabies_negri`
- **Caption:** H&E-stained brain histopathology of rabies encephalitis showing characteristic intracytoplasmic eosinophilic Negri bodies within neurons (CDC PHIL #3377).
- **Credit:** CDC / Dr. Daniel P. Perl (CDC PHIL #3377)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Rabies_encephalitis_Negri_bodies_PHIL_3377_lores.jpg](https://commons.wikimedia.org/wiki/File:Rabies_encephalitis_Negri_bodies_PHIL_3377_lores.jpg)

### First Steps
1. **Wash wounds immediately for ≥15 minutes** with soap/detergent and copious running water, then apply **povidone-iodine** or alcohol virucide ([WHO Rabies 2026](https://www.who.int/news-room/fact-sheets/detail/rabies)).
2. Classify exposure (**Cat I**: intact skin lick → wash only; **Cat II**: minor scratch/nibbling without bleeding → wash + vaccine; **Cat III**: transdermal bite/scratch, mucous membrane/broken skin saliva, wild animal → wash + vaccine + RIG/RmAb) and start PEP **immediately** without awaiting animal observation ([NCDC 2019](https://ncdc.mohfw.gov.in/uploads/pdf/COH14.pdf); [WHO Rabies 2026](https://www.who.int/news-room/fact-sheets/detail/rabies)).
3. Update tetanus toxoid and prescribe antibiotics if clinically infected.

### Screening & Investigation of Choice (IOC)
- **PEP Triage IOC:** Clinical WHO/NCDC **Category I/II/III** assessment (no test detects human rabies prior to symptom onset; never delay PEP awaiting animal testing) ([NCDC MO Module](https://ncdc.mohfw.gov.in/uploads/pdf/COH12.pdf)).
- **Suspected Human Rabies (Ante-Mortem):** Multi-specimen panel — **saliva RT-PCR**, **nuchal skin biopsy DFA/PCR**, and **serum + CSF antibodies** (corneal smear is insensitive and no longer recommended; post-mortem gold standard = brainstem/cerebellum DFA) ([CDC Rabies Specimen 2024](https://www.cdc.gov/rabies/hcp/suspected-human-rabies/index.html)).

### Other Investigations & Key Findings
- Incubation 2–3 months (1 wk to >1 yr); **furious rabies (~80%)** presents with hydrophobia, aerophobia, agitation; **paralytic rabies (~20%)** causes ascending flaccid paralysis. Virtually 100% fatal once symptomatic ([WHO Rabies 2026](https://www.who.int/news-room/fact-sheets/detail/rabies)).

### Treatment Tiers

#### Tier 1: Drug of choice — Active Rabies Vaccination (ID & IM) + Category III RIG / RmAb
- **Rabies Vaccine (CCEEV: PVRV / PCECV) — Intradermal (India NRCP) or Intramuscular**
  - **Regimen:** `India NRCP ID (Updated Thai Red Cross 2-2-2-0-2): 0.1 mL ID × 2 deltoid sites on Days 0, 3, 7, and 28 (WHO 2018 abridged 1-week IPC: Days 0, 3, 7). IM Essen 5-dose: 1 full vial IM (deltoid; anterolateral thigh if <2 yr — NEVER gluteal) on Days 0, 3, 7, 14, and 28.`
  - **Cautions:** **No contraindications** — safe in pregnancy, lactation, and infants.
  - **Citations:** [NCDC 2019](https://ncdc.mohfw.gov.in/uploads/pdf/COH14.pdf), [WHO Position Paper 2018](https://cdn.who.int/media/docs/default-source/immunization/position_paper_documents/rabies/pp-rabies-summary-2018.pdf)
- **Rabies Immunoglobulin (RIG) or Monoclonal Antibody (RmAb) — All Cat III & Immunocompromised Cat II**
  - **Regimen:** `Infiltrate directly into and around ALL wounds on Day 0 (dilute in sterile NS if multiple wounds): Equine RIG (eRIG) max 40 IU/kg OR Human RIG (hRIG) max 20 IU/kg OR Rabishield (RmAb) 3.33 IU/kg OR Twinrab (RmAb cocktail) 40 IU/kg`
  - **Weight-based calculator (`WeightDose`):** `eRIG max 40.0 IU/kg; hRIG max 20.0 IU/kg; Rabishield 3.33 IU/kg; Twinrab 40.0 IU/kg`
  - **Cautions:** Skin test before purified eRIG is NOT required (keep adrenaline ready). Never exceed max IU/kg; never inject in same syringe/site as vaccine; never give >7 days after 1st vaccine dose.
  - **Citations:** [NCDC 2019](https://ncdc.mohfw.gov.in/uploads/pdf/COH14.pdf), [SII Rabishield](https://www.seruminstitute.com/product_ind_rabishield.php), [PMC13411875](https://pmc.ncbi.nlm.nih.gov/articles/PMC13411875/)

#### Tier 2: Re-Exposure (Previously Vaccinated) & Immunocompromised Hosts
- **Re-Exposure Booster & Immunocompromised PEP**
  - **Regimen:** `Previously vaccinated (documented full PrEP/PEP): 2-dose booster (1-site ID 0.1 mL OR 1-site IM) on Days 0 and 3; NO RIG (if re-exposed <3 mo post-PEP, WHO advises wound wash only). Immunocompromised: full vaccine course + RIG/RmAb for BOTH Cat II and Cat III even if previously vaccinated.`
  - **Citations:** [WHO Position Paper 2018](https://cdn.who.int/media/docs/default-source/immunization/position_paper_documents/rabies/pp-rabies-summary-2018.pdf), [NCDC 2019](https://ncdc.mohfw.gov.in/uploads/pdf/COH14.pdf)

### Avoid
- **Gluteal IM rabies vaccine** (poor immunogenicity), **tight primary suturing** of Category III wounds before RIG infiltration, **applying chilli/turmeric/oil/herbs** to bite wounds, **RIG >7 days post-1st vaccine dose**, and **withholding PEP in pregnancy/infants or awaiting animal observation** ([NCDC 2019](https://ncdc.mohfw.gov.in/uploads/pdf/COH14.pdf); [WHO Rabies FAQs](https://cdn.who.int/media/docs/default-source/ntds/rabies/rabies-clinicians-faqs-20sep2018.pdf?sfvrsn=97d94712_4)).

### Escalate / Red Flags
- Head/face/neck/hand/genital or multiple deep bites (shortest incubation — prioritize immediate RIG/RmAb), eRIG anaphylaxis, or any clinical rabies sign (hydrophobia, aerophobia, wound paresthesia, ascending paralysis — isolate, notify IDSP, palliative care) ([NCDC MO Module](https://ncdc.mohfw.gov.in/uploads/pdf/COH12.pdf)).

---

## 8. Rubella (German measles)

- **Condition ID:** `inf-rubella`
- **Population:** `Population.Infections`
- **Summary:** Mild droplet-borne 3-day rash with posterior auricular/suboccipital lymphadenopathy, but highly teratogenic in early pregnancy (>85% Congenital Rubella Syndrome risk in first 12 weeks); confirm via IgM/RT-PCR, isolate 7 days, and never give live MR/MMR in pregnancy.
- **Search Keywords:** `rubella`, `German measles`, `3-day measles`, `CRS`, `congenital rubella syndrome`, `Forchheimer spots`, `MR vaccine`, `MMR`, `IgM ELISA`, `IgG avidity`, `TORCH`, `pregnancy rash`

### Clinical Photo


- **Resource name (`resName`):** `inf_rubella_rash`
- **Caption:** Generalized, discrete, faint pink maculopapular rash on the abdomen caused by acute rubella (German measles) (CDC PHIL #4514).
- **Credit:** CDC Public Health Image Library (PHIL #4514)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Rubella.jpg](https://commons.wikimedia.org/wiki/File:Rubella.jpg)

### First Steps
1. Enforce **droplet isolation for 7 days after rash onset**, strictly avoiding pregnant women and non-immune infants ([CDC Pink Book Ch. 20](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-20-rubella.html)).
2. Check **pregnancy status** immediately in the patient and exposed contacts (first 12 weeks gestation carries >85% CRS risk) ([WHO Rubella 2026](https://www.who.int/news-room/fact-sheets/detail/rubella)).
3. Collect serum IgM + throat/NP swab RT-PCR (clinical diagnosis is unreliable), notify fever-and-rash surveillance, and provide supportive care ([CDC Surveillance Ch. 14](https://www.cdc.gov/surv-manual/php/table-of-contents/chapter-14-rubella.html)).

### Screening & Investigation of Choice (IOC)
- **IOC:** Serum **anti-rubella IgM ELISA** (optimal **days 4–28 post-rash**; repeat if negative <5 days) **+** throat/NP swab **RT-PCR** (highest yield **days 0–3/4 post-rash**) ([CDC Pink Book Ch. 20](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-20-rubella.html); [CDC Surveillance Ch. 14](https://www.cdc.gov/surv-manual/php/table-of-contents/chapter-14-rubella.html)).
- **Pregnancy confirmation:** **Maternal IgG avidity** (low avidity = acute primary infection; high avidity = past immunity) or ≥4-fold paired IgG rise ([CDC Surveillance Ch. 14](https://www.cdc.gov/surv-manual/php/table-of-contents/chapter-14-rubella.html)).

### Other Investigations & Key Findings
- Tender **posterior auricular/suboccipital** lymphadenopathy, soft-palate petechiae (Forchheimer spots), and arthralgia in up to 70% of adult women. **CRS triad:** sensorineural deafness, congenital cataracts/glaucoma, and CHD (especially **PDA**) — CRS infants shed virus up to 1 year ([CDC Pink Book Ch. 20](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-20-rubella.html); [ICMR-NIV India CRS 2023](https://pmc.ncbi.nlm.nih.gov/articles/PMC10474486/)).

### Treatment Tiers

#### Tier 1: Supportive Care (No Specific Antiviral Exists)
- **Paracetamol + Isolation**
  - **Regimen:** `Paracetamol: Children 10–15 mg/kg/dose PO q4–6h (max 60 mg/kg/day); Adults 500–1,000 mg PO q4–6h (max 4 g/day) + hydration + 7-day droplet isolation`
  - **Citations:** [CDC Pink Book Ch. 20](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-20-rubella.html), [Medsafe Paracetamol](https://www.medsafe.govt.nz/committees/marc/reports/181-Dosing-of-Paracetamol.pdf)

#### Tier 2: Prevention (India UIP / IAP) & Pregnancy Exposure Management
- **MR / MMR Immunization & Pregnancy Referral**
  - **Regimen:** `India UIP: MR live vaccine 0.5 mL SC at 9 completed months & 16–24 months (IAP ACVIP: MMR at 9 mo, 15 mo, 4–6 yr). Pregnant exposure/infection: urgent obstetric/fetal medicine referral for IgG avidity, serial USG, and gestational-age CRS counselling (vaccinate seronegative women postpartum).`
  - **Cautions:** If inadvertently vaccinated in pregnancy, termination is **NOT** recommended (0 CRS cases in 1,980 followed susceptible pregnant women).
  - **Citations:** [MoHFW MR Guidelines](https://jsiindia.in/wp-content/uploads/2024/07/rubella.pdf), [IAP Guidebook 2019](https://acvip.org/files/iap-guidebook-2019.pdf), [CDC Pink Book Ch. 20](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-20-rubella.html)

### Avoid
- **Live MR/MMR vaccine in pregnancy** (avoid pregnancy for 4 weeks post-vaccination) or **severe immunocompromise**, **aspirin in children** (Reye syndrome), and **interpreting rubella IgM within 6–56 days of MR/MMR vaccination** ([CDC Pink Book Ch. 20](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-20-rubella.html)).

### Escalate / Red Flags
- Any rubella exposure/infection in pregnancy (<20 wk — urgent obstetric/fetal medicine referral), altered sensorium/seizures (encephalitis ~1:6,000), bleeding/petechiae (thrombocytopenic purpura ~1:3,000), or neonate with suspected CRS (cataracts, murmur, failed hearing screen — multidisciplinary referral + prolonged isolation) ([CDC Pink Book Ch. 20](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-20-rubella.html)).

---

## Primary Sources Reference Table (Batch 4: Verified Sources)

| Source ID | Authority | Document | Title | Year | URL |
|---|---|---|---|---|---|
| `inf-lr-ncdc-lep-2015` | `NCDC` | National Guidelines | Diagnosis, Case Management, Prevention & Control of Leptospirosis | 2015 | [ncdc.mohfw.gov.in](https://ncdc.mohfw.gov.in/uploads/pdf/lep4.pdf) |
| `inf-lr-cdc-lep-2026` | `CDC` | Clinical Guidance | Clinical Overview of Leptospirosis | 2026 | [cdc.gov](https://www.cdc.gov/leptospirosis/hcp/clinical-overview/index.html) |
| `inf-lr-ima-lep-2021` | `Paper` | IMA India Protocol | Leptospirosis Treatment & Chemoprophylaxis Algorithm | 2021 | [ima-india.org](https://www.ima-india.org/ima/pdfdata/Treatment-Algorithm-Flip-Chart-03122021.pdf) |
| `inf-lr-ncvbdc-ndp-2013` | `NCVBDC` | National Drug Policy | National Drug Policy on Malaria | 2013 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/Doc/National-Drug-Policy-2013.pdf) |
| `inf-lr-ncvbdc-dtm-2013` | `NCVBDC` | Clinical Protocol | Diagnosis and Treatment of Malaria | 2013 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/Doc/Diagnosis-Treatment-Malaria-2013.pdf) |
| `inf-lr-ncvbdc-nimr-2014` | `NCVBDC` | National Guidelines | Guidelines for Diagnosis and Treatment of Malaria in India | 2014 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/20627628441542176662.pdf) |
| `inf-lr-ncvbdc-asha-2025` | `NCVBDC` | Operational Module | Malaria Training Module for ASHA | 2025 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/Doc/Malaria-Training-Module-ASHA-2025.pdf) |
| `inf-lr-who-mal-2026` | `WHO` | Consolidated Guidelines | WHO Guidelines for Malaria | 2026 | [who.int](https://www.who.int/publications/i/item/guidelines-for-malaria) |
| `inf-lr-who-mal-preg-2024` | `WHO` | Policy Guidance | Safety of Antimalarials in the First Trimester of Pregnancy | 2024 | [who.int](https://www.who.int/publications/i/item/9789240069404) |
| `inf-lr-cdc-mal-tbl-2026` | `CDC` | Treatment Tables | Malaria Treatment Tables | 2026 | [cdc.gov](https://www.cdc.gov/malaria/hcp/clinical-guidance/appendix-a-treatment-tables.html) |
| `inf-lr-cdc-mal-sev-2026` | `CDC` | Clinical Guidance | Treatment of Severe Malaria | 2026 | [cdc.gov](https://www.cdc.gov/malaria/hcp/clinical-guidance/treatment-of-severe-malaria-1.html) |
| `inf-lr-ncdc-vpd-2026` | `NCDC` | Operational Guidelines | Operational Guidelines for VPD Surveillance | 2026 | [ncdc.mohfw.gov.in](https://ncdc.mohfw.gov.in/uploads/resource/1785229413_OG_VPD%20Surveillance_15.07.26.pdf) |
| `inf-lr-mohfw-uwin-2026` | `MoHFW` | UIP Schedule | National Immunization Schedule (U-WIN Portal) | 2026 | [uwin.mohfw.gov.in](https://uwin.mohfw.gov.in/) |
| `inf-lr-who-pb-2013` | `WHO` | Pocket Book Ch. 6 | Pocket Book of Hospital Care for Children: Fever & Measles | 2013 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK154436/) |
| `inf-lr-who-mr-lab-2018` | `WHO` | Lab Manual Ch. 4 | Laboratory-Based Surveillance of Measles, Rubella & CRS | 2018 | [who.int](https://www.who.int/docs/default-source/immunization/vpd_surveillance/lab_networks/measles_rubella/manual/chapter-4.pdf?sfvrsn=ec496bf2_2) |
| `inf-lr-cdc-measles-2026` | `CDC` | Clinical Guidance | Clinical Overview of Measles | 2026 | [cdc.gov](https://www.cdc.gov/measles/hcp/clinical-overview/index.html) |
| `inf-lr-cdc-measles-pep` | `CDC` | PEP Guidance | Measles Vaccine Recommendations & Immune Globulin PEP | 2026 | [cdc.gov](https://www.cdc.gov/measles/hcp/vaccine-considerations/index.html) |
| `inf-lr-cdc-measles-ig` | `CDC` | Clinical Reference | Immune Globulin for Measles Post-Exposure Prophylaxis | 2024 | [cdc.gov](https://www.cdc.gov/measles/downloads/measles-immune-globulin-post-exposure-prophylaxis.pdf) |
| `inf-lr-iap-mumps-2020` | `IAP` | ACVIP Guidance | Mumps — IAP ACVIP MMR Recommendations | 2020 | [acvip.org](https://acvip.org/parents/columns/mumps.php) |
| `inf-lr-who-mumps-2024` | `WHO` | Position Paper | Mumps Virus Vaccines: WHO Position Paper | 2024 | [who.int](https://www.who.int/publications/i/item/who-wer-9911-115-133) |
| `inf-lr-cdc-mumps-2025` | `CDC` | Clinical Guidance | Clinical Overview of Mumps | 2025 | [cdc.gov](https://www.cdc.gov/mumps/hcp/clinical-overview/index.html) |
| `inf-lr-cdc-mumps-lab` | `CDC` | Lab Guidance | Laboratory Testing for Mumps | 2024 | [cdc.gov](https://www.cdc.gov/mumps/php/laboratories/index.html) |
| `inf-lr-cdc-pb-mumps` | `CDC` | Pink Book Ch. 15 | Chapter 15: Mumps | 2024 | [cdc.gov](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-15-mumps.html) |
| `inf-lr-cdc-acip-2013` | `CDC` | ACIP MMWR | Prevention of Measles, Rubella, CRS, and Mumps | 2013 | [cdc.gov](https://www.cdc.gov/mmwr/preview/mmwrhtml/rr6204a1.htm) |
| `inf-lr-statpearls-mumps` | `Paper` | StatPearls | Mumps (NBK534785) | 2026 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK534785/) |
| `inf-lr-cdc-pert-tx-2026` | `CDC` | Clinical Guidance | Treatment of Pertussis | 2026 | [cdc.gov](https://www.cdc.gov/pertussis/hcp/clinical-care/index.html) |
| `inf-lr-cdc-pert-mmwr` | `CDC` | MMWR RR5414 | Recommended Antimicrobial Agents for Pertussis Treatment & PEP | 2005 | [cdc.gov](https://www.cdc.gov/mmwr/preview/mmwrhtml/rr5414a1.htm) |
| `inf-lr-cdc-pert-lab` | `CDC` | Lab Guidance | Laboratory Testing for Pertussis | 2024 | [cdc.gov](https://www.cdc.gov/pertussis/php/laboratories/index.html) |
| `inf-lr-cdc-pert-pcr` | `CDC` | PCR Best Practices | Best Practices for PCR Diagnosing Pertussis | 2025 | [cdc.gov](https://www.cdc.gov/pertussis/php/pcr-bestpractices/index.html) |
| `inf-lr-cdc-pert-amr` | `CDC` | AMR Surveillance | Antibiotic-Resistant *Bordetella pertussis* | 2025 | [cdc.gov](https://www.cdc.gov/pertussis/hcp/antibiotic-resistance/index.html) |
| `inf-lr-ncdc-plague-psu` | `NCDC` | Plague Surveillance Unit | NCDC Bengaluru Plague Surveillance Branch | 2026 | [ncdc.mohfw.gov.in](https://ncdc.mohfw.gov.in/includes/Branches/Bengaluru.php) |
| `inf-lr-who-plague-2026` | `WHO` | Fact Sheet | Plague Fact Sheet | 2026 | [who.int](https://www.who.int/news-room/fact-sheets/detail/plague) |
| `inf-lr-cdc-plague-2021` | `CDC` | MMWR RR7003 | Antimicrobial Treatment and Prophylaxis of Plague | 2021 | [cdc.gov](https://www.cdc.gov/mmwr/volumes/70/rr/rr7003a1.htm) |
| `inf-lr-cdc-plague-care` | `CDC` | Clinical Guidance | Clinical Care of Plague | 2024 | [cdc.gov](https://www.cdc.gov/plague/hcp/clinical-care/index.html) |
| `inf-lr-cdc-plague-abx` | `CDC` | Dosing Table | Recommended Antibiotic Treatment for Plague | 2018 | [cdc.gov](https://www.cdc.gov/plague/resources/recommended-antibiotics-for-plague-web-site-rev-jan2018-p.pdf) |
| `inf-lr-ncdc-rabies-2019` | `NCDC` | NRCP National Guideline | National Guidelines for Rabies Prophylaxis | 2019 | [ncdc.mohfw.gov.in](https://ncdc.mohfw.gov.in/uploads/pdf/COH14.pdf) |
| `inf-lr-ncdc-rabies-mo` | `NCDC` | NRCP Training Module | Training Module on Rabies for Medical Officers | 2019 | [ncdc.mohfw.gov.in](https://ncdc.mohfw.gov.in/uploads/pdf/COH12.pdf) |
| `inf-lr-who-rabies-2018` | `WHO` | Position Paper Summary | Rabies Vaccines and Immunoglobulins: WHO Position | 2018 | [cdn.who.int](https://cdn.who.int/media/docs/default-source/immunization/position_paper_documents/rabies/pp-rabies-summary-2018.pdf) |
| `inf-lr-who-rabies-faq` | `WHO` | Clinicians FAQ | Frequently Asked Questions about Rabies for Clinicians | 2018 | [cdn.who.int](https://cdn.who.int/media/docs/default-source/ntds/rabies/rabies-clinicians-faqs-20sep2018.pdf?sfvrsn=97d94712_4) |
| `inf-lr-who-rabies-2026` | `WHO` | Fact Sheet | Rabies Fact Sheet | 2026 | [who.int](https://www.who.int/news-room/fact-sheets/detail/rabies) |
| `inf-lr-cdc-rabies-spec` | `CDC` | Lab Guidance | Rabies Specimen Collection (Suspected Human Rabies) | 2024 | [cdc.gov](https://www.cdc.gov/rabies/hcp/suspected-human-rabies/index.html) |
| `inf-lr-sii-rabishield` | `Paper` | Product Monograph | RABISHIELD — Rabies Human Monoclonal Antibody | 2017 | [seruminstitute.com](https://www.seruminstitute.com/product_ind_rabishield.php) |
| `inf-lr-kims-rmab-2025` | `Paper` | KIMS Observational Study | Safety & Feasibility of Single vs Cocktail Rabies mAbs (PMC13411875) | 2025 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC13411875/) |
| `inf-lr-mohfw-mr-2017` | `MoHFW` | Operational Guidelines | Introduction of Measles-Rubella Vaccine: National Guidelines | 2017 | [jsiindia.in](https://jsiindia.in/wp-content/uploads/2024/07/rubella.pdf) |
| `inf-lr-iap-guide-2019` | `IAP` | Immunization Guidebook | IAP Guidebook on Immunization 2018–2019 | 2019 | [acvip.org](https://acvip.org/files/iap-guidebook-2019.pdf) |
| `inf-lr-who-rubella-2026` | `WHO` | Fact Sheet | Rubella Fact Sheet | 2026 | [who.int](https://www.who.int/news-room/fact-sheets/detail/rubella) |
| `inf-lr-cdc-pb-rubella` | `CDC` | Pink Book Ch. 20 | Chapter 20: Rubella | 2024 | [cdc.gov](https://www.cdc.gov/pinkbook/hcp/table-of-contents/chapter-20-rubella.html) |
| `inf-lr-cdc-surv-rubella` | `CDC` | VPD Surveillance Ch. 14 | Chapter 14: Rubella | 2025 | [cdc.gov](https://www.cdc.gov/surv-manual/php/table-of-contents/chapter-14-rubella.html) |
| `inf-lr-icmr-crs-2023` | `ICMR` | Multicentric Study | Congenital Rubella Syndrome Cardiac Lesions in India (PMC10474486) | 2023 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC10474486/) |
| `inf-lr-medsafe-para` | `Paper` | Dosing Guidance | Dosing of Paracetamol (Pediatric & Adult Maximums) | 2020 | [medsafe.govt.nz](https://www.medsafe.govt.nz/committees/marc/reports/181-Dosing-of-Paracetamol.pdf) |

---

## Claude Code Integration Spec (Drop-In Instructions)

When this document is placed in the `Medicalaid` repository and approved by the clinician:

1. **Prerequisites (from Batch 1):** Ensure `Model.kt` has `Population.Infections("Infectious diseases")`, `data class ConditionImage(...)`, and `val image: ConditionImage? = null` on `Condition`.
2. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/InfectionsLR.kt`**
   - Create `infectionsLRSources: List<Source>` (using the `inf-lr-...` IDs above) and `infectionsLRConditions: List<Condition>` (all 8 diseases above with exact IDs, keywords, `image`, `firstSteps`, `info`, `tiers`, `WeightDose`, `maxDose`, `avoid`, `escalate`, and `citations`).
   - Confirm zero unused sources (`ContentIntegrityTest` enforces 100% citation usage).
3. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/Conditions.kt`**
   - Add `Group(infectionsLRConditions, infectionsLRSources)` to `inReviewInfections` (or directly to `releasedInfections` once approved).
4. **Offline Image Bundle (`composeApp/src/commonMain/composeResources/drawable/`)**
   - Append these 8 verified Public Domain CDC PHIL images to `scripts/fetch-infection-images.sh` (fetch at 800 px width and compress to `.webp` via `cwebp -q 80` if available):
     - `inf_leptospira_sem` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Leptospira_scanning_micrograph.jpg?width=800`
     - `inf_malaria_pf_rings` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Plasmodium_falciparum_rings_form_parasites4885_lores.jpg?width=800`
     - `inf_measles_koplik` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Koplik_spots,_measles_6111_lores.jpg?width=800`
     - `inf_mumps_parotitis` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Mumps_PHIL_130_lores.jpg?width=800`
     - `inf_pertussis_gram` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Bordetella_pertussis.jpg?width=800`
     - `inf_plague_wayson` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Yersinia_pestis_wayson.jpg?width=800`
     - `inf_rabies_negri` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Rabies_encephalitis_Negri_bodies_PHIL_3377_lores.jpg?width=800`
     - `inf_rubella_rash` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Rubella.jpg?width=800`
5. **Verification:** Run `./gradlew :composeApp:allTests` to confirm `ContentIntegrityTest` passes.
