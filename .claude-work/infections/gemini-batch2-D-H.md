# Medicalaid — Infections A–Z: Batch 2 (D–H) Clinical Review & Implementation Doc

**Section:** Infectious diseases (`Population.Infections`)  
**Order:** Strict alphabetical — Batch 2 of A–Z (8 diseases, following Batch 1 A–C)  
**Sourcing policy:** India first (NCVBDC, ICMR, MoHFW, NCDC, NACO, NVHCP, IAP, INASL), then WHO, CDC, AASLD, Cochrane. Every statement, investigation, dose, and caution carries a verified primary source URL.  
**Purpose of this document:** Complete clinician review copy (with all 8 verified open-licence clinical photos embedded inline) **and** drop-in specification for Claude Code to add directly into the `Medicalaid` repository (`InfectionsDH.kt`) once reviewed.

---

## Batch 2 Overview (D–H)

| # | Disease | Condition ID | Investigation of Choice (IOC) | First-Line Drug of Choice (DOC) | Open-Licence Photo |
|---|---|---|---|---|---|
| 1 | [Dengue fever](#1-dengue-fever) | `inf-dengue` | NS1 ELISA (Days 1–5); IgM MAC-ELISA (>Day 5); serial Hct/platelets | Phase- & Hct-guided isotonic crystalloids (0.9% NS / RL) + Paracetamol (no specific antiviral) | Yes (`inf_dengue_rash`, CC BY-SA 4.0) |
| 2 | [Diphtheria](#2-diphtheria) | `inf-diphtheria` | Clinical + 2 swabs (throat & NP) culture + modified Elek toxigenicity test (do NOT delay DAT) | Immediate IV Diphtheria Antitoxin (DAT 20k–80k IU) + Azithromycin or Erythromycin × 14 d | Yes (`inf_diphtheria_bull_neck`, PD) |
| 3 | [Enteric fever (typhoid & paratyphoid)](#3-enteric-fever-typhoid--paratyphoid) | `inf-enteric-fever` | Blood culture (Week 1 gold standard; avoid relying on single Widal) | Azithromycin or Cefixime (OPD); IV Ceftriaxone (hospitalized/severe); Meropenem if XDR | Yes (`inf_typhoid_rose_spots`, PD) |
| 4 | [Filariasis (lymphatic filariasis)](#4-filariasis-lymphatic-filariasis) | `inf-filariasis` | Night blood smear (10 pm–2 am); daytime FTS antigen (*W. bancrofti*); Doppler USG ('filarial dance sign') | Individual: DEC 6 mg/kg/day × 12 d; MDA: DA/IDA; Chronic: MMDP hygiene + hydrocelectomy | Yes (`inf_wuchereria_bancrofti`, PD) |
| 5 | [Giardiasis](#5-giardiasis) | `inf-giardiasis` | Stool DFA / antigen EIA / multiplex PCR (3 serial stool specimens if microscopy/antigen) | Tinidazole 2 g PO SD or Metronidazole 250 mg TDS × 5–7 d (Paromomycin in 1st trimester) | Yes (`inf_giardia_lamblia`, PD) |
| 6 | [Gonorrhoea](#6-gonorrhoea) | `inf-gonorrhoea` | Site-specific NAAT (urine/vaginal/pharyngeal/rectal); male urethral Gram stain; culture/AST for AMR | Ceftriaxone 500 mg IM SD (NACO/CDC; 1 g WHO 2024) + Doxycycline 100 mg BD × 7 d (Azithromycin 1 g SD in pregnancy) | Yes (`inf_neisseria_gonorrhoeae`, PD) |
| 7 | [Hepatitis A & E (acute viral hepatitis)](#7-hepatitis-a--e-acute-viral-hepatitis) | `inf-hepatitis-a-e` | Serum Anti-HAV IgM (HAV) & Anti-HEV IgM (HEV); PT/INR to screen for ALF | Supportive care only (admit pregnant HEV); HAV PEP within 14 d (vaccine ± IG 0.1 mL/kg) | Yes (`inf_scleral_icterus`, CC BY 4.0) |
| 8 | [Hepatitis B (acute & chronic)](#8-hepatitis-b-acute--chronic) | `inf-hepatitis-b` | HBsAg (+ IgM anti-HBc if acute); HBV DNA PCR, HBeAg, ALT, APRI/FibroScan | Tenofovir (TDF 300 mg OD) or Entecavir (0.5 mg OD; 1 mg decompensated/LAM-exposed); TAF if renal/bone risk | Yes (`inf_hepatitis_b_virions`, PD) |

---

## 1. Dengue fever

- **Condition ID:** `inf-dengue`
- **Population:** `Population.Infections`
- **Summary:** Acute *Aedes*-borne flavivirus (*DENV-1–4*) febrile illness that can progress around defervescence (days 3–7) to capillary plasma leakage, shock, severe bleeding, or organ failure; managed with phase- and haematocrit-guided isotonic fluids.
- **Search Keywords:** `dengue`, `DENV`, `breakbone fever`, `DHF`, `DSS`, `dengue shock syndrome`, `NS1`, `thrombocytopenia`, `plasma leakage`, `Aedes aegypti`, `tourniquet test`, `A90`

### Clinical Photo


- **Resource name (`resName`):** `inf_dengue_rash`
- **Caption:** Confluent erythematous convalescent dengue rash demonstrating the classic "white islands in a sea of red" sparing pattern seen during the recovery phase.
- **Credit:** Ranjan Premaratna, University of Kelaniya, Sri Lanka (Wikimedia VRTS #2015010610019038)
- **Licence:** CC BY-SA 4.0 International
- **Source Page:** [Wikimedia Commons — File:Dengue_recovery_rash_(White_islands_in_red_sea).jpg](https://commons.wikimedia.org/wiki/File:Dengue_recovery_rash_(White_islands_in_red_sea).jpg)

### First Steps
1. Triage by exact day of fever onset and clinical group (A, B, or C): assess oral fluid tolerance, urine output, high-risk comorbidities (pregnancy, infancy, age >65 y, obesity, diabetes, CKD/CLD/CAD), and check for warning signs or shock ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf); [CDC 2026](https://www.cdc.gov/dengue/hcp/clinical-care/index.html)).
2. Assess haemodynamics immediately: pulse volume, BP, pulse pressure (narrow if <20 mmHg), capillary refill time (CRT >2 s), extremity warmth, postural hypotension (SBP drop ≥20 mmHg or DBP drop ≥10 mmHg), and tourniquet test ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf)).
3. Order baseline CBC (Hb, Hct/PCV, TLC, platelets) and day-appropriate dengue assay; never delay fluid therapy awaiting lab confirmation ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf); [CDC 2025](https://www.cdc.gov/dengue/hcp/diagnosis-testing/index.html)).
4. Give paracetamol + oral fluids (ORS/coconut water) for Group A, admit Groups B/C for monitored isotonic IV crystalloids, and notify the District Vector Borne Disease (VBD) Officer ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf)).

### Screening & Investigation of Choice (IOC)
- Days 1–5 of fever (India NCVBDC primary rule) — IOC: **NS1 antigen by ELISA** (or RT-PCR/NAAT in acute-phase serum) ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf); [ICMR STW 2019](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952338_paediatrics_dengue_fever.pdf)).
- After Day 5 (>5 days of fever) — IOC: **Dengue IgM by MAC-ELISA** (CDC 2025 recommends NAAT or NS1 ELISA + IgM during days 0–7, and IgM alone >7 days) ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf); [CDC 2025](https://www.cdc.gov/dengue/hcp/diagnosis-testing/index.html)).
- Rapid Diagnostic Tests (RDTs) are **not recommended** by NCVBDC for diagnosis or clinical decisions (positive RDT = "probable" dengue only); single-sample IgG-ELISA reflects past infection only ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf)).

### Other Investigations & Key Findings
- Serial Haematocrit (Hct/PCV) & Platelets: check daily in Group A (fever >3 d), every 4–6 h in admitted patients, and before/after (never during) each fluid bolus in shock. A **≥10% Hct rise** above baseline signals plasma leakage (≥20% in moderate/severe dengue); rising Hct with rapidly falling platelets heralds the critical phase ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf)).
- Tourniquet test: inflate cuff midway between SBP and DBP for 5 min; positive = ≥10 petechiae per 2.5 cm² (>20 in severe dengue; may be negative in profound shock) ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf)).
- Moderate/severe workup: RBS, ABG + lactate, electrolytes (Na+, K+, Ca2+), RFT, LFT (AST/ALT ≥1,000 U/L = severe organ involvement), PT/aPTT/fibrinogen, blood grouping/crossmatch, CXR/USG (pleural effusion, ascites, IVC collapsibility), and screen for endemic co-infections (malaria, scrub typhus, leptospirosis, typhoid, chikungunya) ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf); [ICMR STW 2019](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952338_paediatrics_dengue_fever.pdf)).

### Treatment Tiers

#### Tier 1: Group A (Outpatient — no warning signs) & Group B (Inpatient — warning signs / high-risk)
> *Note:* No specific antiviral exists; careful isotonic fluid titration guided by Hct and urine output (~0.5 mL/kg/h) is life-saving. Calculate fluid volumes using **Ideal Body Weight (IBW)** in overweight/obese patients (NCVBDC 2023).

- **Paracetamol (Group A & inpatient antipyretic)**
  - **Regimen:** `Adults: 500–1000 mg PO q6h (max 4 g/day); Children: 10–15 mg/kg/dose PO q6h`
  - **Max Dose:** `4 g/day in adults`
  - **Weight-based calculator (`WeightDose`):** `10.0–15.0 mg/kg per dose PO every 6 h`
  - **Cautions:** Pair with oral hydration (ORS, coconut water) and tepid sponging; daily CBC/Hct review in Group A.
  - **Citations:** [NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf), [CDC 2026](https://www.cdc.gov/dengue/hcp/clinical-care/index.html)
- **Isotonic Crystalloid — 0.9% NaCl or Ringer's Lactate (Group B inpatient)**
  - **Regimen:** `Start 7–10 mL/kg over 1 h (children: 5–10 mL/kg/h); if improving, taper 5–7 mL/kg/h (2–4 h) → 3–5 mL/kg/h (2–4 h) → 2–4 mL/kg/h (2–4 h); stop IV fluids at 24–48 h`
  - **Cautions:** If no improvement after 1st bolus and Hct >45%, give 2nd bolus 10 mL/kg over 1 h (if still unimproved → Group C). If Hct <45% with worsening status, suspect occult bleeding → whole blood 10 mL/kg or PRBC 5 mL/kg. Infants <6 mo may use 0.45% NaCl during critical phase.
  - **Citations:** [NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf), [ICMR STW 2019](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952338_paediatrics_dengue_fever.pdf)

#### Tier 2: Group C — Severe Dengue (Shock, Severe Bleeding, & Special Groups)
- **Isotonic Crystalloid / Colloid Resuscitation (Compensated & Hypotensive Shock)**
  - **Regimen:** `Compensated shock: 0.9% NS or RL 10–20 mL/kg over 1 h, then taper (7–10 → 5–7 → 3–5 → 2–3 mL/kg/h up to 24–48 h). Decompensated/hypotensive shock: 20 mL/kg over 15 min (max 500 mL/bolus; up to 3 rapid boluses if Hct >45%); if refractory leakage, hyper-oncotic colloid e.g. Dextran 40 10 mL/kg bolus (max 30 mL/kg/day)`
  - **Cautions:** Correct ABCS (Acidosis, Bleeding, Ca/Na/K, Sugar); start vasopressors/inotropes (noradrenaline/adrenaline/dopamine) for refractory hypotension. Stop IV fluids once out of critical phase (>24–48 h afebrile); for frank pulmonary oedema give furosemide 1 mg/kg IV (children 0.1–0.5 mg/kg/dose).
  - **Citations:** [NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf), [ICMR STW 2019](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952338_paediatrics_dengue_fever.pdf)
- **Blood Products (Severe Bleeding / Falling Hct ONLY)**
  - **Regimen:** `Fresh whole blood 10 mL/kg OR PRBC 5 mL/kg (children with persistent shock + falling Hct: 20 mL/kg). Platelets ONLY if active bleeding (or prophylactic ONLY if <10,000/mm³ without bleeding): 5–6 units RDP or 1 unit SDP (adults); 10–15 mL/kg (infants)`
  - **Cautions:** Pregnancy: admit all symptomatic pregnant patients (high pulmonary oedema/bleeding risk). CAD: hold aspirin/antiplatelets if platelets <1,00,000/mm³ or with bleeding/leakage.
  - **Citations:** [NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf), [ICMR STW 2019](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952338_paediatrics_dengue_fever.pdf)

### Avoid
- **Aspirin and NSAIDs (ibuprofen, diclofenac):** Markedly increase mucosal and GI bleeding risk ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf); [CDC 2026](https://www.cdc.gov/dengue/hcp/clinical-care/index.html)).
- **Routine corticosteroids:** No therapeutic benefit in dengue (reserved only for specific indications such as raised ICP in hepatic encephalopathy or HLH) ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf); [CDC 2026](https://www.cdc.gov/dengue/hcp/clinical-care/index.html)).
- **Routine prophylactic platelet transfusion:** Platelet count poorly predicts bleeding; transfusing above 10,000/mm³ without bleeding causes harm without preventing haemorrhage ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf); [CDC 2026](https://www.cdc.gov/dengue/hcp/clinical-care/index.html)).
- **IM injections, unnecessary invasive tapping, hypotonic IV fluids (>6 mo), or continuing IV fluids >48 h past critical phase:** Risk of fatal haemorrhage, hyponatraemia, or pulmonary oedema ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf)).

### Escalate / Red Flags
- Warning signs (admit immediately): persistent vomiting (≥3/h or ≥4 in 6 h), severe abdominal pain, clinical fluid accumulation (ascites/pleural effusion), mucosal bleeding, lethargy/restlessness, hepatomegaly >2 cm, progressive Hct rise with rapid platelet drop ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf)).
- Severe dengue / ICU: narrow pulse pressure (<20 mmHg), hypotension, CRT >2 s, oliguria (<0.5 mL/kg/h), SpO2 <90%, GCS <9 or seizures, AST/ALT ≥1,000 U/L, severe bleeding, myocarditis, or AKI ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf); [ICMR STW 2019](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952338_paediatrics_dengue_fever.pdf)).

---

## 2. Diphtheria

- **Condition ID:** `inf-diphtheria`
- **Population:** `Population.Infections`
- **Summary:** Life-threatening toxin-mediated upper airway infection (*Corynebacterium diphtheriae*) with adherent greyish-white pseudomembrane, airway obstruction, myocarditis, and neuropathy; give IV Diphtheria Antitoxin (DAT) + macrolide immediately on clinical suspicion.
- **Search Keywords:** `diphtheria`, `Corynebacterium diphtheriae`, `pseudomembrane`, `bull neck`, `DAT`, `diphtheria antitoxin`, `Elek test`, `stridor`, `myocarditis`, `A36`

### Clinical Photo


- **Resource name (`resName`):** `inf_diphtheria_bull_neck`
- **Caption:** Child with severe respiratory diphtheria showing marked diffuse cervical lymphadenopathy and soft-tissue oedema ("bull neck"), an indication for high-dose (80,000 IU) diphtheria antitoxin and urgent airway vigilance.
- **Credit:** CDC Public Health Image Library (PHIL #5325)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Diphtheria_bull_neck.5325_lores.jpg](https://commons.wikimedia.org/wiki/File:Diphtheria_bull_neck.5325_lores.jpg)

### First Steps
1. Isolate immediately with **droplet + contact precautions** (separate ventilated area, ≥1 m spacing, mask/N95 for airway procedures, eye protection, gown, gloves) ([WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1); [CDC 2026](https://www.cdc.gov/diphtheria/hcp/clinical-guidance/index.html)).
2. Assess airway gently without agitating the patient (stridor, tachypnoea, chest indrawing, drooling, restlessness, SpO2 <90%); alert ENT/anaesthesia immediately if compromised ([WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1)).
3. Gently collect 2 swabs (throat from edge/beneath membrane + nasopharyngeal) before antibiotics if feasible without delaying therapy ([WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1); [CDC 2026](https://www.cdc.gov/diphtheria/php/laboratories/index.html)).
4. Administer **Diphtheria Antitoxin (DAT)** and **first-line macrolide antibiotic** immediately on clinical suspicion — never wait for culture/Elek results; notify IDSP for contact tracing ([WHO 2024](https://iris.who.int/server/api/core/bitstreams/6f1840f0-056e-4823-991c-19fdae2d09d3/content); [ICMR-NITM 2017](https://www.icmr.gov.in/icmrobject/custom_data/pdf/policy-briefs/ICMR_NITM_ROY_Policy_Brief_Diptheria_Final_2017.pdf)).

### Screening & Investigation of Choice (IOC)
- Clinical provisional diagnosis: pharyngitis/tonsillitis/laryngitis with firmly adherent greyish-white pseudomembrane (bleeds on dislodgement) or diffuse neck swelling ("bull neck") ([WHO 2024](https://iris.who.int/server/api/core/bitstreams/6f1840f0-056e-4823-991c-19fdae2d09d3/content)).
- Laboratory IOC: **Throat + nasopharyngeal swabs** in Amies transport media (2–8°C) cultured on **blood agar + tellurite media (Tinsdale agar)** (highest yield day 2–21 prior to antibiotics), followed by **modified Elek immunoprecipitation test** (24–48 h) to confirm actual toxin expression (PCR for *tox* gene alone can detect non-expressing strains) ([CDC 2026](https://www.cdc.gov/diphtheria/php/laboratories/index.html); [WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1)).
- Clearance / test of cure: **2 consecutive negative cultures taken 24 h apart**, starting ≥24 h after completing antibiotics ([CDC 2026](https://www.cdc.gov/diphtheria/hcp/clinical-guidance/index.html)).

### Other Investigations & Key Findings
- Cardiac monitoring: baseline and serial **12-lead ECG** + rhythm monitoring (diphtheritic myocarditis/conduction blocks peak **2–7 weeks**, range 1–12 weeks post-onset) ([WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1); [CDC 2026](https://www.cdc.gov/diphtheria/hcp/clinical-guidance/index.html)).
- Neurologic & renal monitoring: serial cranial nerve/palatal/respiratory muscle checks (neuropathy develops 1–3 months post-onset), serial RFT/urine output for toxin-mediated AKI, and isolate AST given documented penicillin resistance in India ([WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1); [ICMR-NITM 2017](https://www.icmr.gov.in/icmrobject/custom_data/pdf/policy-briefs/ICMR_NITM_ROY_Policy_Brief_Diptheria_Final_2017.pdf)).

### Treatment Tiers

#### Tier 1: Drug of choice — Diphtheria Antitoxin (DAT) + First-Line Macrolide (WHO 2024)
> *Note:* DAT neutralizes only unbound toxin — give as a single IV infusion (same dose for children & adults; do NOT repeat). WHO 2024 strongly prefers macrolides over penicillins due to lower resistance.

- **Diphtheria Antitoxin (DAT) — Equine**
  - **Regimen:** `Single IV dose in 250–500 mL 0.9% NS over 2–4 h: Laryngitis/pharyngitis <48 h = 20,000 IU; Nasopharyngeal/extensive <48 h = 40,000 IU; Bull neck, ANY disease ≥48 h, or severe = 80,000 IU (WHO 2024; CRI Kasauli India 10,000 IU/10 mL vials)`
  - **Cautions:** WHO 2024 strongly recommends **against** routine skin sensitivity testing (delays therapy and increases mortality); keep **IM adrenaline 1:1000** (<6 y: 0.15 mL; 6–12 y: 0.3 mL; ≥12 y: 0.5 mL), oxygen, and airway kit at bedside. Pregnancy: specialist risk–benefit required.
  - **Citations:** [WHO 2024](https://iris.who.int/server/api/core/bitstreams/6f1840f0-056e-4823-991c-19fdae2d09d3/content), [WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1), [ICMR-NITM 2017](https://www.icmr.gov.in/icmrobject/custom_data/pdf/policy-briefs/ICMR_NITM_ROY_Policy_Brief_Diptheria_Final_2017.pdf)
- **Azithromycin (First-line antibiotic, preferred)**
  - **Regimen:** `Adults: 500 mg PO/IV once daily × 14 days; Children: 10–12 mg/kg PO/IV once daily × 14 days`
  - **Max Dose:** `500 mg/day`
  - **Weight-based calculator (`WeightDose`):** `10.0–12.0 mg/kg PO/IV once daily × 14 days (max 500 mg/day)`
  - **Cautions:** Use IV if unable to swallow, step down to PO; usually non-contagious after 48 h of effective therapy.
  - **Citations:** [WHO 2024](https://iris.who.int/server/api/core/bitstreams/6f1840f0-056e-4823-991c-19fdae2d09d3/content)
- **Erythromycin (Alternative first-line macrolide)**
  - **Regimen:** `10–15 mg/kg PO/IV every 6 h (40–50 mg/kg/day) for 14 days`
  - **Max Dose:** `500 mg/dose (2 g/day)`
  - **Weight-based calculator (`WeightDose`):** `10.0–15.0 mg/kg per dose PO/IV every 6 h × 14 days (max 500 mg/dose)`
  - **Citations:** [WHO 2024](https://iris.who.int/server/api/core/bitstreams/6f1840f0-056e-4823-991c-19fdae2d09d3/content), [WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1)

#### Tier 2: Second-Line Penicillins & Close-Contact Prophylaxis
- **Aqueous Benzylpenicillin (Penicillin G) or Procaine Penicillin (only if macrolides unavailable & AST-susceptible)**
  - **Regimen:** `Penicillin G IV/IM: 100,000 IU/kg/day divided 25,000 IU/kg q6h (max 4 MIU / 2.4 g/day) OR Procaine penicillin IM 50 mg/kg OD (max 1.2 g/day) × 14 days (step down to Penicillin V 10–15 mg/kg q6h, max 500 mg/dose)`
  - **Cautions:** Documented penicillin resistance reported in India (ICMR-NITM 2017); check allergy.
  - **Citations:** [WHO 2024](https://iris.who.int/server/api/core/bitstreams/6f1840f0-056e-4823-991c-19fdae2d09d3/content), [ICMR-NITM 2017](https://www.icmr.gov.in/icmrobject/custom_data/pdf/policy-briefs/ICMR_NITM_ROY_Policy_Brief_Diptheria_Final_2017.pdf)
- **Close-Contact Prophylaxis & Convalescent Vaccination**
  - **Regimen:** `Contacts (monitor 7–10 d + swabs): single-dose IM Benzathine penicillin (600,000 IU ≤5 y; 1,200,000 IU >5 y) OR 7-day PO Erythromycin (40 mg/kg/day; adults 250 mg q6h) OR 7-day PO Azithromycin (10–12 mg/kg OD; adults 500 mg OD). Vaccinate index case during convalescence + unimmunized/overdue contacts (Pentavalent/Td).`
  - **Citations:** [WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1), [CDC 2026](https://www.cdc.gov/diphtheria/hcp/clinical-guidance/index.html)

### Avoid
- **Delaying DAT awaiting culture/Elek or performing routine DAT skin sensitivity tests:** Intracellular toxin damage is irreversible; skin testing delays therapy and raises mortality ([WHO 2024](https://iris.who.int/server/api/core/bitstreams/6f1840f0-056e-4823-991c-19fdae2d09d3/content)).
- **Aggressive swabbing, scraping the pseudomembrane, or routine NG tubes:** Provokes profuse bleeding and can precipitate fatal airway obstruction ([WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1)).
- **Muscle relaxants / heavy sedation during airway securing or fluid boluses in myocarditis cardiogenic shock:** Risks "can't intubate, can't ventilate" catastrophe or pulmonary oedema ([WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1)).

### Escalate / Red Flags
- Inspiratory stridor, chest indrawing, drooling, restlessness, cyanosis, or SpO2 <90% — urgent ENT/anaesthesia airway securing (tracheostomy/needle cricothyroidotomy; temporizing nebulized adrenaline 2 mL of 1:1000) ([WHO 2017](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1)).
- "Bull neck", shock, ECG conduction blocks/arrhythmias/heart failure, bulbar or respiratory paralysis, or oliguria/AKI — immediate ICU admission ([WHO 2024](https://iris.who.int/server/api/core/bitstreams/6f1840f0-056e-4823-991c-19fdae2d09d3/content)).

---

## 3. Enteric fever (typhoid & paratyphoid)

- **Condition ID:** `inf-enteric-fever`
- **Population:** `Population.Infections`
- **Summary:** Systemic *Salmonella* Typhi/Paratyphi infection with stepwise fever: blood culture in week 1 is IOC (avoid relying on single Widal); treat with azithromycin/cefixime (OPD) or IV ceftriaxone (severe) and avoid empiric fluoroquinolones in India (>95% resistance).
- **Search Keywords:** `enteric fever`, `typhoid`, `paratyphoid`, `Salmonella Typhi`, `Salmonella Paratyphi`, `rose spots`, `step-ladder fever`, `Widal`, `XDR typhoid`, `ileal perforation`

### Clinical Photo


- **Resource name (`resName`):** `inf_typhoid_rose_spots`
- **Caption:** Faint, flat, rose-colored maculopapular lesions ("rose spots") on the chest of a patient with typhoid fever caused by *Salmonella enterica* serovar Typhi.
- **Credit:** CDC / Armed Forces Institute of Pathology, Charles N. Farmer (CDC PHIL #2215)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Salmonella_typhi_typhoid_fever_PHIL_2215_lores.jpg](https://commons.wikimedia.org/wiki/File:Salmonella_typhi_typhoid_fever_PHIL_2215_lores.jpg)

### First Steps
1. Draw paired **blood cultures** before antibiotics whenever possible without delaying empiric therapy in toxic/critically ill patients ([CDC 2024](https://www.cdc.gov/typhoid-fever/hcp/clinical-guidance/index.html); [IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)).
2. Start empiric non-fluoroquinolone therapy by severity: oral azithromycin or cefixime (uncomplicated OPD); IV ceftriaxone (hospitalized/complicated) ([ICMR AMR 2023](https://www.icmr.gov.in/icmrobject/uploads/Documents/1725536060_annual_report_2023.pdf)).
3. Give antipyretics, hydration, and nutrition while monitoring daily for abdominal guarding, GI bleeding, or encephalopathy; defervescence normally takes 3–7 days ([CDC Yellow Book 2025](https://www.cdc.gov/yellow-book/hcp/travel-associated-infections-diseases/typhoid-and-paratyphoid-fever.html)).
4. Review blood culture MICs at 48–72 h and step down to oral therapy once afebrile and clinically stable.

### Screening & Investigation of Choice (IOC)
- **Blood Culture (Gold-Standard IOC):** Highest yield in **week 1** (up to 90%, declining to ~40% by week 4); send paired sets (children: 5–10 mL total blood, 1:5 blood-to-broth ratio) prior to antibiotics for confirmation and MIC/AST ([CDC 2024](https://www.cdc.gov/typhoid-fever/hcp/clinical-guidance/index.html); [IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)).
- **Widal Test Limitations in India:** Not recommended for acute clinical decisions — O/H antibodies appear only in week 2, baseline titres are elevated in endemic India, false positives occur in malaria/rickettsial/other Enterobacteriaceae infections, and paired 4-fold rise is impractical acutely ([CDC 2024](https://www.cdc.gov/typhoid-fever/hcp/clinical-guidance/index.html); [IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)).

### Other Investigations & Key Findings
- Bone marrow culture (~80% sensitivity) remains positive even after prior antibiotics or late in PUO; stool/urine cultures are used mainly to detect chronic carriers (>3–12 months) ([CDC 2024](https://www.cdc.gov/typhoid-fever/hcp/clinical-guidance/index.html); [IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)).
- Rapid serology (Typhidot/EIA) has poor specificity (~37%) and is not recommended; supportive labs show leukopenia/cytopenia and transaminitis; erect abdominal X-ray/USG if perforation suspected ([IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)).

### Treatment Tiers

#### Tier 1: Drug of choice — Uncomplicated OPD & Hospitalized / Severe Enteric Fever
> *Note:* ICMR AMR 2023 reports >95% fluoroquinolone resistance in Indian *S. Typhi* vs 96% susceptibility to azithromycin/cefixime and 97.3% to ceftriaxone.

- **Azithromycin (First-line uncomplicated OPD)**
  - **Regimen:** `Adults: 500 mg–1 g PO once daily × 7 days; Children: 10–20 mg/kg/day PO once daily × 7 days`
  - **Max Dose:** `1 g/day`
  - **Weight-based calculator (`WeightDose`):** `10.0–20.0 mg/kg PO once daily × 7 days (max 1 g/day)`
  - **Cautions:** Safe in pregnancy; IAP 2022 also notes reserving azithromycin for refractory/ceftriaxone-resistant cases if cefixime is used first-line.
  - **Citations:** [ICMR AMR 2023](https://www.icmr.gov.in/icmrobject/uploads/Documents/1725536060_annual_report_2023.pdf), [IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)
- **Cefixime (Alternative first-line uncomplicated OPD / IAP paediatric)**
  - **Regimen:** `Adults: 200 mg PO twice daily (or 20 mg/kg/day in 2 divided doses) × 10–14 days (or ≥7 days post-defervescence); Children: 20 mg/kg/day PO in 2 divided doses × 10–14 days`
  - **Max Dose:** `400 mg twice daily`
  - **Weight-based calculator (`WeightDose`):** `20.0 mg/kg per day PO in 2 divided doses × 10–14 days (max 400 mg BD)`
  - **Citations:** [ICMR AMR 2023](https://www.icmr.gov.in/icmrobject/uploads/Documents/1725536060_annual_report_2023.pdf), [Cochrane 2022](https://pmc.ncbi.nlm.nih.gov/articles/PMC9686137/)
- **Ceftriaxone IV (First-line hospitalized / complicated / pregnancy severe)**
  - **Regimen:** `Adults: 2 g IV once daily (50–75 mg/kg/day, max 4 g/day) × 10–14 days; Children: 50–100 mg/kg/day IV (max 2–4 g/day) × 10–14 days (step down to oral cefixime or azithromycin once afebrile/stable)`
  - **Max Dose:** `4 g/day`
  - **Weight-based calculator (`WeightDose`):** `50.0–100.0 mg/kg IV once daily × 10–14 days (max 4 g/day)`
  - **Cautions:** Safe in pregnancy; watch for rising MICs (CLSI resistance ≥4 µg/mL).
  - **Citations:** [ICMR AMR 2023](https://www.icmr.gov.in/icmrobject/uploads/Documents/1725536060_annual_report_2023.pdf), [ICMR STG 2019](https://www.icmr.gov.in/icmrobject/custom_data/pdf/resource-guidelines/Treatment_Guidelines_2019_Final.pdf)

#### Tier 2: Severe Encephalopathy / Shock Adjunct, XDR Typhoid, & Confirmed-Susceptible Alternatives
- **Dexamethasone IV (Severe typhoid with delirium, coma, or shock ONLY)**
  - **Regimen:** `3 mg/kg IV loading dose, then 1 mg/kg IV every 6 h for 48 hours (8 doses total)`
  - **Weight-based calculator (`WeightDose`):** `3.0 mg/kg IV load, then 1.0 mg/kg IV q6h × 48 h`
  - **Cautions:** Indicated ONLY for delirium, obtundation, coma, or shock; monitor for GI bleeding/perforation.
  - **Citations:** [IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)
- **Meropenem IV (XDR / Ceftriaxone-Resistant Severe Typhoid)**
  - **Regimen:** `Adults: 1 g IV every 8 h × 10–14 days; Children: 20–40 mg/kg IV every 8 h (max 1–2 g/dose) × 10–14 days (± oral azithromycin if poor response)`
  - **Weight-based calculator (`WeightDose`):** `20.0–40.0 mg/kg per dose IV every 8 h × 10–14 days`
  - **Cautions:** Suspect if ceftriaxone MIC ≥4 µg/mL or travel from Pakistan/Iraq; for uncomplicated XDR use oral azithromycin 20 mg/kg/day (max 1 g/day) × 7 days.
  - **Citations:** [CDC 2024](https://www.cdc.gov/typhoid-fever/hcp/clinical-guidance/index.html), [CDC Yellow Book 2025](https://www.cdc.gov/yellow-book/hcp/travel-associated-infections-diseases/typhoid-and-paratyphoid-fever.html)
- **Co-trimoxazole / TMP-SMX (Susceptibility-confirmed oral alternative)**
  - **Regimen:** `Adults: 1 DS tablet PO BD × 14 days; Children: TMP 8–10 mg/kg/day in 2 divided doses × 14 days`
  - **Cautions:** 97% susceptible in India (ICMR 2023); avoid in 1st and late 3rd trimester pregnancy.
  - **Citations:** [ICMR AMR 2023](https://www.icmr.gov.in/icmrobject/uploads/Documents/1725536060_annual_report_2023.pdf), [IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)

### Avoid
- **Empiric fluoroquinolones (ciprofloxacin/ofloxacin) in India:** >95% resistance in *S. Typhi* and contraindicated in children ([ICMR AMR 2023](https://www.icmr.gov.in/icmrobject/uploads/Documents/1725536060_annual_report_2023.pdf); [IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)).
- **Relying on a single Widal or Typhidot test** to diagnose or switch antibiotics ([CDC 2024](https://www.cdc.gov/typhoid-fever/hcp/clinical-guidance/index.html); [IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)).
- **Aminoglycosides (amikacin/gentamicin)** (clinically ineffective against intracellular *Salmonella*) and **premature antibiotic switching before day 5–7** when clinically improving ([IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)).

### Escalate / Red Flags
- GI haemorrhage (melena, haematochezia, hypotension) or ileal perforation/peritonitis (severe abdominal pain, guarding, shock — weeks 2–3; urgent surgical repair) ([CDC Yellow Book 2025](https://www.cdc.gov/yellow-book/hcp/travel-associated-infections-diseases/typhoid-and-paratyphoid-fever.html)).
- Typhoid encephalopathy (delirium, obtundation, coma), septic shock, or persistent toxic fever >7 days on optimal therapy (evaluate for XDR, abscess, HLH) ([IAP STG 2022](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198)).

---

## 4. Filariasis (lymphatic filariasis)

- **Condition ID:** `inf-filariasis`
- **Population:** `Population.Infections`
- **Summary:** Mosquito-borne lymphatic nematode (*Wuchereria bancrofti* / *Brugia malayi*): diagnose via night blood smear or daytime FTS antigen; treat active infection with DEC 6 mg/kg/day × 12 days, eliminate via MDA (DA/IDA), and manage chronic lymphedema with lifelong hygiene (MMDP).
- **Search Keywords:** `lymphatic filariasis`, `filariasis`, `Wuchereria bancrofti`, `Brugia malayi`, `elephantiasis`, `lymphedema`, `hydrocele`, `chyluria`, `filarial dance sign`, `DEC`, `MDA`, `IDA`, `tropical pulmonary eosinophilia`, `ADLA`

### Clinical Photo


- **Resource name (`resName`):** `inf_wuchereria_bancrofti`
- **Caption:** Thick peripheral blood smear (hematoxylin stain) showing a sheathed microfilaria of *Wuchereria bancrofti* with a gently curved body and tapered tail free of terminal nuclei.
- **Credit:** CDC DPDx (Public Health Image Library)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Wuchereria_bancrofti_1_DPDX.JPG](https://commons.wikimedia.org/wiki/File:Wuchereria_bancrofti_1_DPDX.JPG)

### First Steps
1. Confirm active infection with a **night blood smear (10 pm–2 am)** or daytime **Filariasis Test Strip (FTS)** for *W. bancrofti* antigen before starting antifilarial drugs ([NCVBDC 2024](https://ncvbdc.mohfw.gov.in/Doc/Guidelines/Fil/ELF-Guideline-2024.pdf); [CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)).
2. Check travel/exposure history to exclude **onchocerciasis** and **loiasis** before prescribing DEC ([CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)).
3. Separate active infection from chronic lymphedema/hydrocele (most chronic lymphedema patients are amicrofilaremic and need MMDP hygiene/hydrocelectomy rather than DEC) ([WHO 2024](https://www.who.int/news-room/fact-sheets/detail/lymphatic-filariasis); [CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)).
4. In acute adenolymphangitis (ADLA), prioritize rest, limb elevation, paracetamol, skin care, and systemic antibiotics for secondary bacterial infection ([CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)).

### Screening & Investigation of Choice (IOC)
- **Night Blood Smear (10 pm–2 am):** Thick peripheral blood smear (Giemsa/hematoxylin) is the standard parasitological IOC for nocturnal periodic microfilariae (*W. bancrofti* and *B. malayi*) ([NCVBDC 2024](https://ncvbdc.mohfw.gov.in/Doc/Guidelines/Fil/ELF-Guideline-2024.pdf)).
- **Filariasis Test Strip (FTS):** Daytime point-of-care immunochromatographic assay detecting *W. bancrofti* circulating filarial antigen (does not detect *Brugia* spp.; may cross-react in heavy *Loa loa*) ([NCVBDC 2024](https://ncvbdc.mohfw.gov.in/Doc/Guidelines/Fil/ELF-Guideline-2024.pdf); [WHO 2024](https://www.who.int/news-room/fact-sheets/detail/lymphatic-filariasis)).
- **High-Frequency Doppler Ultrasound ("Filarial Dance Sign"):** Directly visualizes thrashing live adult *W. bancrofti* worms inside dilated scrotal/inguinal lymphatics ([CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)).

### Other Investigations & Key Findings
- CBC & serum IgE: marked peripheral eosinophilia and high IgE (+ interstitial CXR infiltrates) in Tropical Pulmonary Eosinophilia (TPE).
- Urine analysis in chyluria: milky urine with fat globules, elevated urinary triglycerides, and sterile culture.

### Treatment Tiers

#### Tier 1: Drug of choice — Individual Active Infection & India NCVBDC MDA
- **Diethylcarbamazine citrate (DEC) — Individual Active Infection**
  - **Regimen:** `6 mg/kg/day PO in 3 divided doses for 12 days (after meals); Tropical Pulmonary Eosinophilia (TPE): 6 mg/kg/day PO for 14–21 days`
  - **Weight-based calculator (`WeightDose`):** `6.0 mg/kg per day PO in 3 divided doses × 12 days (14–21 d for TPE)`
  - **Cautions:** Take after meals to reduce nausea/headache; CDC 2024 notes 1-day and 12-day courses have similar efficacy, while NCVBDC 2024 uses 12 days for confirmed individual cases.
  - **Citations:** [NCVBDC 2024](https://ncvbdc.mohfw.gov.in/Doc/Guidelines/Fil/ELF-Guideline-2024.pdf), [CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)
- **India NCVBDC / WHO Mass Drug Administration (DA & IDA)**
  - **Regimen:** `DA: DEC 6 mg/kg + Albendazole 400 mg PO single annual dose (≥5 rounds; also for children 2–5 yr in IDA districts). IDA (accelerated): Ivermectin 200 mcg/kg + DEC 6 mg/kg + Albendazole 400 mg PO single annual dose (2 rounds)`
  - **Cautions:** Exclude children <2 yr (and <5 yr from ivermectin), pregnant women, and seriously ill patients; never give on an empty stomach.
  - **Citations:** [NCVBDC 2024](https://ncvbdc.mohfw.gov.in/Doc/Guidelines/Fil/ELF-Guideline-2024.pdf), [WHO 2017 MDA Guideline](https://iris.who.int/server/api/core/bitstreams/7459f928-4584-415b-af52-ebd242af9463/content), [WHO 2024](https://www.who.int/news-room/fact-sheets/detail/lymphatic-filariasis)

#### Tier 2: Anti-*Wolbachia* Adjunct & Chronic Morbidity (MMDP)
- **Doxycycline (Macrofilaricidal / Anti-*Wolbachia* adjunct)**
  - **Regimen:** `200 mg PO once daily for 4–6 weeks`
  - **Cautions:** Depletes endosymbiotic *Wolbachia*, sterilizing/killing adult worms and stabilizing mild–moderate lymphedema; contraindicated in pregnancy, breastfeeding, and children <8 years.
  - **Citations:** [CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)
- **Morbidity Management & Disability Prevention (MMDP)**
  - **Regimen:** `Daily soap-and-water limb washing, drying skin folds, topical antifungal/antibiotic for entry lesions, limb elevation, exercise, footwear; hydrocelectomy for hydrocele`
  - **Cautions:** Lifelong self-care; DEC does not reverse established chronic lymphedema or hydrocele.
  - **Citations:** [WHO 2024](https://www.who.int/news-room/fact-sheets/detail/lymphatic-filariasis), [CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)

### Avoid
- **DEC in co-endemic onchocerciasis or heavy loiasis (>8,000 mf/mm³):** Risk of severe Mazzotti reaction, blindness, or fatal encephalopathy/renal failure ([CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html); [CDC Onchocerciasis 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/onchocerciasis.html)).
- **DEC during acute ADLA without supportive care/antibiotics** or routine DEC for chronic amicrofilaremic lymphedema ([CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)).
- **MDA drugs in children <2 yr (ivermectin <5 yr), pregnancy, or seriously ill patients**, and **doxycycline in pregnancy/<8 yr** ([NCVBDC 2024](https://ncvbdc.mohfw.gov.in/Doc/Guidelines/Fil/ELF-Guideline-2024.pdf)).

### Escalate / Red Flags
- Severe ADLA / secondary bacterial cellulitis with high fever, rigors, spreading erythema, or sepsis — urgent systemic antibiotics and wound care ([CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)).
- Acute painful/massive hydrocele (rule out pyocele; refer for hydrocelectomy), chyluria/haematochyluria, severe hypoxic TPE, or post-DEC encephalopathy ([NCVBDC 2024](https://ncvbdc.mohfw.gov.in/Doc/Guidelines/Fil/ELF-Guideline-2024.pdf); [CDC 2024](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html)).

---

## 5. Giardiasis

- **Condition ID:** `inf-giardiasis`
- **Population:** `Population.Infections`
- **Summary:** Waterborne protozoal enteritis (*Giardia duodenalis*) causing foul-smelling greasy non-bloody diarrhoea, bloating, and malabsorption; diagnose via stool antigen EIA/DFA or PCR and treat with tinidazole, metronidazole, or nitazoxanide (paromomycin in 1st trimester).
- **Search Keywords:** `giardiasis`, `Giardia lamblia`, `Giardia duodenalis`, `Giardia intestinalis`, `protozoal diarrhea`, `steatorrhea`, `persistent diarrhea`, `malabsorption`, `tinidazole`, `metronidazole`, `nitazoxanide`, `paromomycin`

### Clinical Photo


- **Resource name (`resName`):** `inf_giardia_lamblia`
- **Caption:** Scanning electron micrograph (SEM) of a flagellated *Giardia lamblia* (*G. duodenalis*) trophozoite demonstrating its pear-shaped body and paired flagella, the replicative stage that adheres to proximal small-bowel mucosa.
- **Credit:** CDC / Mahmud Tari (CDC PHIL #8698)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Giardia_lamblia_SEM_8698_lores.jpg](https://commons.wikimedia.org/wiki/File:Giardia_lamblia_SEM_8698_lores.jpg)

### First Steps
1. Assess hydration and start reduced-osmolarity ORS (IV fluids if severe dehydration), prioritizing infants and pregnant women ([CDC 2024](https://www.cdc.gov/giardia/hcp/clinical-care/index.html)).
2. Take exposure history (diarrhoea >3 d, greasy foul-smelling stools without blood/mucus, untreated water, travel, childcare) and order stool antigen EIA/DFA or PCR ([CDC 2024](https://www.cdc.gov/giardia/hcp/diagnosis-testing/index.html)).
3. Counsel handwashing with **soap and water** (alcohol hand rubs do not kill *Giardia* cysts), boiling/filtering drinking water, and avoiding swimming for 2 weeks after recovery ([NIH Pediatric OI](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis)).

### Screening & Investigation of Choice (IOC)
- **IOC:** Stool **direct fluorescent antibody (DFA)** (CDC gold standard), **stool antigen EIA** (sensitivity 87–100%, specificity 100%), or **multiplex GI PCR** (sensitivity 92–100%) ([CDC 2024](https://www.cdc.gov/giardia/hcp/diagnosis-testing/index.html); [NIH Pediatric OI](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis)).
- **Serial sampling:** Collect **3 consecutive stool specimens** over several days if using microscopy or antigen testing due to intermittent cyst shedding (single microscopy sensitivity ~70%) ([CDC 2024](https://www.cdc.gov/giardia/hcp/diagnosis-testing/index.html)).
- Routine screening/treatment of asymptomatic carriers is not recommended except to prevent spread in high-risk settings (childcare, food handlers, household contacts of pregnant/immunocompromised) ([CDC 2024](https://www.cdc.gov/giardia/hcp/clinical-care/index.html)).

### Other Investigations & Key Findings
- Wet mount/trichrome microscopy identifies pear-shaped binucleate flagellated trophozoites (unformed stool <1 h) or oval cysts; fecal blood, mucus, and leukocytes are absent ([NIH Pediatric OI](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis)).
- Refractory or chronic giardiasis: evaluate for post-*Giardia* acquired lactase deficiency (20–40%; trial temporary dairy avoidance) and screen for selective IgA deficiency, hypogammaglobulinaemia, and HIV ([NIH Pediatric OI](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis)).

### Treatment Tiers

#### Tier 1: Drug of choice (First-line)
- **Tinidazole (Preferred single-dose)**
  - **Regimen:** `Adults: 2 g PO single dose with food; Children ≥3 yr: 50 mg/kg PO single dose with food`
  - **Max Dose:** `2 g`
  - **Weight-based calculator (`WeightDose`):** `50.0 mg/kg PO single dose with food (children ≥3 yr; max 2 g)`
  - **Cautions:** Highest cure rate (80–100%); avoid in children <3 yr, 1st-trimester pregnancy, and with alcohol.
  - **Citations:** [MoHFW/NCDC](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/86111.pdf), [NIH Pediatric OI](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis)
- **Metronidazole (Widely used first-line in India)**
  - **Regimen:** `Adults: 250 mg PO three times daily × 5–7 days (MoHFW/NCDC also lists 500 mg BD × 7 d or 2 g SD); Children (all ages): 5 mg/kg PO every 8 h (15 mg/kg/day) × 5–7 days`
  - **Weight-based calculator (`WeightDose`):** `15.0 mg/kg per day PO in 3 divided doses × 5–7 days`
  - **Cautions:** Avoid alcohol during and for 48 h after therapy; safe in 2nd–3rd trimesters if needed.
  - **Citations:** [ICMR STG 2019](https://www.icmr.gov.in/icmrobject/custom_data/pdf/resource-guidelines/Treatment_Guidelines_2019_Final.pdf), [MoHFW/NCDC](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/86111.pdf)
- **Nitazoxanide (Alternative / paediatric suspension)**
  - **Regimen:** `≥12 yr & adults: 500 mg PO every 12 h × 3 days with food; 4–11 yr: 200 mg PO q12h × 3 days; 1–3 yr: 100 mg PO q12h × 3 days`
  - **Cautions:** Well tolerated; may cause transient yellow urine discolouration.
  - **Citations:** [NIH Pediatric OI](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis)

#### Tier 2: Special Groups — 1st-Trimester Pregnancy & Nitroimidazole-Refractory Disease
- **Paromomycin (1st-trimester pregnancy)**
  - **Regimen:** `500 mg PO three times daily (25–35 mg/kg/day in 3 divided doses) × 5–10 days`
  - **Max Dose:** `1,500 mg/day`
  - **Weight-based calculator (`WeightDose`):** `25.0–35.0 mg/kg per day PO in 3 divided doses × 5–10 days (max 1,500 mg/day)`
  - **Cautions:** Poorly absorbed luminal aminoglycoside; preferred in 1st trimester when symptomatic treatment is required (cure 55–90%).
  - **Citations:** [Gardner & Hill CMR](https://pmc.ncbi.nlm.nih.gov/articles/PMC88965/)
- **Combination Therapy (Nitroimidazole-Refractory)**
  - **Regimen:** `Nitroimidazole (e.g., Metronidazole) PLUS Albendazole 400 mg PO once daily × 5 days (children: 15 mg/kg/day, max 400 mg/day)`
  - **Cautions:** Confirm persistent infection with 3 stool tests ≥2 weeks post-therapy and exclude reinfection/lactase deficiency before escalating.
  - **Citations:** [CDC 2024](https://www.cdc.gov/giardia/hcp/clinical-care/index.html), [NIH Pediatric OI](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis)

### Avoid
- **Alcohol with metronidazole or tinidazole** (disulfiram-like reaction), and **tinidazole in 1st trimester or children <3 yr** ([NIH Pediatric OI](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis)).
- **Loperamide in young children or bloody diarrhoea** ([ICMR 2022](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)) and **alcohol-based hand sanitizers alone** (do not inactivate *Giardia* cysts) ([NIH Pediatric OI](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis)).

### Escalate / Red Flags
- Severe dehydration, intractable vomiting, or bloody stools/high fever (evaluate for invasive dysentery or amoebiasis) ([CDC 2024](https://www.cdc.gov/giardia/hcp/clinical-care/index.html)).
- Failure to thrive/malnutrition in children, or persistent stool positivity despite combination therapy (screen for IgA deficiency/hypogammaglobulinaemia/HIV and refer to GI/ID) ([CDC 2024](https://www.cdc.gov/giardia/hcp/clinical-care/index.html)).

---

## 6. Gonorrhoea

- **Condition ID:** `inf-gonorrhoea`
- **Population:** `Population.Infections`
- **Summary:** *Neisseria gonorrhoeae* STI affecting urogenital, rectal, pharyngeal, and ocular mucosa: diagnose with site-specific NAAT; treat same day with IM ceftriaxone (+ doxycycline for chlamydia; azithromycin in pregnancy) and never use fluoroquinolones or azithromycin monotherapy.
- **Search Keywords:** `gonorrhoea`, `gonorrhea`, `Neisseria gonorrhoeae`, `urethral discharge`, `cervicitis`, `pelvic inflammatory disease`, `PID`, `disseminated gonococcal infection`, `DGI`, `ophthalmia neonatorum`, `ceftriaxone`, `AMR`, `STI`

### Clinical Photo


- **Resource name (`resName`):** `inf_neisseria_gonorrhoeae`
- **Caption:** Gram stain of urethral exudate showing polymorphonuclear leukocytes containing intracellular Gram-negative diplococci (*Neisseria gonorrhoeae*), diagnostic of gonococcal urethritis in symptomatic men.
- **Credit:** CDC / Dr. Norman Jacobs (CDC PHIL #3693)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Neisseria_gonorrhoeae_PHIL_3693_lores.jpg](https://commons.wikimedia.org/wiki/File:Neisseria_gonorrhoeae_PHIL_3693_lores.jpg)

### First Steps
1. Take a non-judgemental sexual history covering all exposed sites (urogenital, rectal, pharyngeal), partner symptoms, pregnancy status, and allergies ([NACO 2024](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/); [CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm)).
2. Collect site-specific NAATs (plus urethral Gram stain in symptomatic men; add culture + AST before treatment if treatment failure or DGI is suspected) ([CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm)).
3. Give same-day, directly observed IM ceftriaxone + antichlamydial coverage if chlamydia is not excluded (do not delay awaiting NAAT in symptomatic/high-risk patients) ([NACO 2024](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/); [CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm)).
4. Advise 7 days sexual abstinence until all partners from the past 60 days are treated and symptoms resolve; perform mandatory co-testing for HIV, syphilis, and chlamydia ([NACO 2024](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/); [CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm)).

### Screening & Investigation of Choice (IOC)
- **IOC:** **NAAT** on first-void urine (men), vaginal/endocervical swab (women), and pharyngeal/rectal swabs at all exposed sites ([CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm); [NACO 2024](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/)).
- **Rapid POC (symptomatic men only):** Urethral exudate **Gram stain** showing intracellular Gram-negative diplococci inside PMNs (>95% sensitivity, >99% specificity); NOT recommended for asymptomatic men, endocervical, pharyngeal, rectal, or prepubertal specimens ([CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm)).
- **Culture & AST:** Mandatory in suspected cephalosporin treatment failure, persistent discharge, DGI, ophthalmia neonatorum, and AMR surveillance ([CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm)).
- **Test of Cure (TOC) & Retesting:** Mandatory TOC at **7–14 days** for all **pharyngeal** infections and post-failure cases; **retest all treated patients at 3 months** for reinfection ([CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm)).

### Other Investigations & Key Findings
- Mandatory STI co-testing: **HIV**, **syphilis** (RPR/VDRL or Dual HIV-Syphilis RDT per NACO 2024), and ***Chlamydia trachomatis***; consider *T. vaginalis* and *M. genitalium* in persistent discharge ([NACO 2024](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/)).
- Urine/serum pregnancy test in women with pelvic pain; blood + synovial fluid cultures/NAAT in suspected DGI ([CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm)).

### Treatment Tiers

#### Tier 1: Drug of choice — Uncomplicated Urogenital, Rectal, & Pharyngeal Gonorrhoea
- **Ceftriaxone IM + Doxycycline PO (NACO India 2024 & CDC 2021; WHO 2024 uses 1 g IM)**
  - **Regimen:** `Ceftriaxone 500 mg IM single dose (NACO 2024 & CDC 2021; 1 g IM if ≥150 kg or per WHO 2024) PLUS Doxycycline 100 mg PO twice daily × 7 days if chlamydia not excluded`
  - **Cautions:** Pregnancy: replace doxycycline with **Azithromycin 1 g PO single dose** (retest in 3rd trimester). Children ≤45 kg: Ceftriaxone 25–50 mg/kg IV/IM single dose (max 250 mg). Mandatory 7–14 day TOC for pharyngeal infection.
  - **Citations:** [NACO 2024](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/), [CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm), [WHO 2024](https://www.ncbi.nlm.nih.gov/books/NBK605896/)

#### Tier 2: Alternatives (No Ceftriaxone / IgE Allergy) & Complicated Disease (PID, DGI, Neonatal)
- **Cefixime PO (ONLY if IM ceftriaxone unavailable — urogenital/rectal ONLY)**
  - **Regimen:** `Cefixime 800 mg PO single dose PLUS Doxycycline 100 mg PO BD × 7 days (NACO Kit-1 Grey: Cefixime 800 mg + Azithromycin 1 g PO SD; WHO 2024: Cefixime 800 mg + TOC or + Azithromycin 2 g PO)`
  - **Cautions:** Unreliable for pharyngeal gonorrhoea; not recommended in children.
  - **Citations:** [NACO 2024](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/), [WHO 2024](https://www.ncbi.nlm.nih.gov/books/NBK605896/), [CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm)
- **Gentamicin IM + Azithromycin PO (Severe IgE Cephalosporin Allergy)**
  - **Regimen:** `Gentamicin 240 mg IM single dose PLUS Azithromycin 2 g PO single dose`
  - **Cautions:** Avoid gentamicin in pregnancy; WHO 2024 notes splitting azithromycin 2 g into 1 g doses 6–12 h apart if vomiting risk.
  - **Citations:** [CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm), [WHO 2024](https://www.ncbi.nlm.nih.gov/books/NBK605896/)
- **Complicated: PID, DGI, & Ophthalmia Neonatorum**
  - **Regimen:** `PID (OPD): Ceftriaxone 500 mg IM SD + Doxycycline 100 mg PO BD + Metronidazole 400–500 mg PO BD × 14 days. DGI (arthritis/dermatitis): Ceftriaxone 1 g IV/IM q24h ≥7 d (meningitis: 1–2 g IV q12–24h × 10–14 d; endocarditis >4 wk). Ophthalmia neonatorum: Ceftriaxone 25–50 mg/kg IV/IM SD (max 250 mg; use Cefotaxime 100 mg/kg if IV calcium).`
  - **Citations:** [NACO 2024](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/), [CDC PID 2021](https://www.cdc.gov/std/treatment-guidelines/pid.htm), [CDC Neonates](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-neonates.htm)

### Avoid
- **Fluoroquinolones, penicillins, tetracyclines, and azithromycin monotherapy:** High AMR and rapid resistance selection ([CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm); [WHO 2024](https://www.ncbi.nlm.nih.gov/books/NBK605896/)).
- **Oral cefixime for pharyngeal gonorrhoea**, **doxycycline/gentamicin in pregnancy**, **ceftriaxone in neonates on IV calcium**, and **topical-only therapy for ophthalmia neonatorum** ([CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm); [CDC Neonates](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-neonates.htm)).

### Escalate / Red Flags
- Suspected DGI (acral pustules, tenosynovitis, septic arthritis, meningitis, endocarditis), severe PID (TOA, pregnancy, fever >38.5°C, no response at 72 h), or gonococcal conjunctivitis/ophthalmia neonatorum (corneal perforation/blindness emergency) — immediate admission ([CDC 2021](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm); [CDC PID 2021](https://www.cdc.gov/std/treatment-guidelines/pid.htm)).
- Suspected cephalosporin treatment failure (collect culture/AST, consult ID) or gonorrhoea in prepubertal children (urgent child abuse evaluation and mandatory POCSO reporting in India) ([NACO 2024](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/); [CDC Children](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-children.htm)).

---

## 7. Hepatitis A & E (acute viral hepatitis)

- **Condition ID:** `inf-hepatitis-a-e`
- **Population:** `Population.Infections`
- **Summary:** Faecal–oral acute viral hepatitides (HAV/HEV) diagnosed by Anti-HAV IgM / Anti-HEV IgM; managed with supportive care — screen every patient for acute liver failure (INR ≥1.5 + encephalopathy) and admit pregnant women with HEV (20–25% 3rd-trimester maternal mortality).
- **Search Keywords:** `hepatitis A`, `hepatitis E`, `HAV`, `HEV`, `acute viral hepatitis`, `jaundice`, `scleral icterus`, `Anti-HAV IgM`, `Anti-HEV IgM`, `fulminant hepatitis`, `acute liver failure`, `pregnancy jaundice`

### Clinical Photo


- **Resource name (`resName`):** `inf_scleral_icterus`
- **Caption:** Marked scleral icterus (jaundice) in a patient hospitalized with acute viral hepatitis.
- **Credit:** Sheila J. Toro (Wikimedia Commons)
- **Licence:** CC BY 4.0 International
- **Source Page:** [Wikimedia Commons — File:Scleral_Icterus.jpg](https://commons.wikimedia.org/wiki/File:Scleral_Icterus.jpg)

### First Steps
1. Triage for **acute liver failure (ALF)** immediately: check vital signs, hydration, sensorium (any grade of hepatic encephalopathy), bleeding, and point-of-care blood glucose ([ICMR STW Liver Failure](https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567562_liver_failure.pdf); [INASL ALF 2020](https://pmc.ncbi.nlm.nih.gov/articles/PMC7335721/)).
2. Send acute viral hepatitis serology (Anti-HAV IgM, Anti-HEV IgM, HBsAg, anti-HCV) plus LFTs and **PT/INR** ([NVHCP Lab Guidelines](https://nvhcp.mohfw.gov.in/common_libs/national-laboratory-guidelines.pdf); [ICMR STW Liver Failure](https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567562_liver_failure.pdf)).
3. Check pregnancy status in all women of reproductive age — strongly consider inpatient admission and close maternal-foetal monitoring for pregnant women with suspected/confirmed HEV ([WHO HEV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-e)).
4. Stop alcohol, unnecessary paracetamol, and herbal/unregulated tonics; counsel WASH hygiene and identify close contacts of HAV cases for post-exposure prophylaxis within 14 days ([WHO HAV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-a); [CDC HAV Care](https://www.cdc.gov/hepatitis-a/hcp/clinical-care/index.html)).

### Screening & Investigation of Choice (IOC)
- **Acute HAV IOC:** Serum **Anti-HAV IgM** (detectable 5–10 d before symptoms up to ~6 mo; total/IgG anti-HAV alone indicates past infection or vaccination) ([CDC HAV Diagnosis](https://www.cdc.gov/hepatitis-a/hcp/diagnosis-testing/index.html); [WHO HAV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-a)).
- **Acute HEV IOC:** Serum **Anti-HEV IgM** (ELISA or rapid assay) ([NVHCP Lab Guidelines](https://nvhcp.mohfw.gov.in/common_libs/national-laboratory-guidelines.pdf); [WHO HEV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-e)).
- **Molecular confirmation (reference labs):** HAV RNA or HEV RNA RT-PCR for early pre-seroconversion diagnosis, outbreaks, or chronic HEV in transplant/immunosuppressed patients ([WHO HEV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-e)).

### Other Investigations & Key Findings
- Synthetic & metabolic panel: ALT/AST, total/direct bilirubin, albumin, **PT/INR** (INR ≥1.5 + encephalopathy = ALF), blood glucose, RFT, electrolytes, CBC, and ABG/ammonia if ALF suspected ([ICMR STW Liver Failure](https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567562_liver_failure.pdf); [INASL ALF 2020](https://pmc.ncbi.nlm.nih.gov/articles/PMC7335721/)).
- Exclude endemic acute febrile jaundice mimics in India (malaria, dengue, leptospirosis, typhoid, rickettsiae); HEV in the 3rd trimester carries **20–25% maternal mortality** ([WHO HEV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-e); [ICMR STW Liver Failure](https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567562_liver_failure.pdf)).

### Treatment Tiers

#### Tier 1: Standard of Care — Uncomplicated Acute HAV & HEV
- **Supportive Care Only (No specific antiviral)**
  - **Regimen:** `Rest, adequate oral hydration (or IV 0.9% NS / RL if vomiting), and balanced small frequent meals as tolerated`
  - **Cautions:** Outpatient management suffices unless severe acute liver injury (INR >1.5), pregnancy with HEV, or ALF.
  - **Citations:** [WHO HAV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-a), [WHO HEV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-e), [CDC HAV Care](https://www.cdc.gov/hepatitis-a/hcp/clinical-care/index.html)

#### Tier 2: HAV Post-Exposure Prophylaxis (≤14 Days) & ALF Stabilization
- **HAV Post-Exposure Prophylaxis (within ≤14 days of exposure)**
  - **Regimen:** `Unvaccinated immunocompetent ≥12 mo: single-antigen inactivated HAV vaccine (1 IM dose; 2nd dose ≥6 mo). Adults >40 yr (per risk), immunocompromised, or chronic liver disease: single-antigen HAV vaccine PLUS Immune Globulin (IG) 0.1 mL/kg IM at separate sites. Infants <12 mo or vaccine-contraindicated: IG 0.1 mL/kg IM alone.`
  - **Weight-based calculator (`WeightDose`):** `0.1 mL/kg IM Immune Globulin (single dose within 14 days)`
  - **Cautions:** Defer live MMR/varicella vaccines ≥3 months after IG; do not use Twinrix (combination HepA–HepB) for PEP.
  - **Citations:** [CDC HAV Care](https://www.cdc.gov/hepatitis-a/hcp/clinical-care/index.html), [CDC ACIP 2018](https://www.cdc.gov/mmwr/volumes/67/wr/mm6743a5.htm)
- **Acute Liver Failure (ALF) Stabilization (ICMR STW)**
  - **Regimen:** `ICU/tertiary hepatology transfer: IV NS/RL + 50% dextrose if hypoglycaemic, intubation for Grade 3–4 coma, IV Pantoprazole 40 mg OD, IV Mannitol 20% 100 mL SOS for cerebral oedema, blood cultures + empiric antibiotics if sepsis suspected, urgent liver transplant evaluation`
  - **Citations:** [ICMR STW Liver Failure](https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567562_liver_failure.pdf)

### Avoid
- **Unnecessary paracetamol (acetaminophen), NSAIDs, hepatotoxic drugs (modify ATT if on TB therapy), alcohol, and unregulated herbal liver tonics:** Risk of worsening liver injury / ALF ([WHO HAV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-a); [WHO HEV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-e)).
- **Combination HepA–HepB vaccine (Twinrix) for HAV PEP:** Contains half the adult HAV antigen dose ([CDC HAV Care](https://www.cdc.gov/hepatitis-a/hcp/clinical-care/index.html)).

### Escalate / Red Flags
- **Acute Liver Failure (ALF):** INR ≥1.5 + any grade of hepatic encephalopathy (drowsiness, confusion, asterixis, coma) — emergency ICU/liver transplant referral ([ICMR STW Liver Failure](https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567562_liver_failure.pdf); [INASL ALF 2020](https://pmc.ncbi.nlm.nih.gov/articles/PMC7335721/)).
- Severe acute liver injury (INR >1.5 even without encephalopathy), hypoglycaemia, bleeding, pregnancy with HEV (2nd–3rd trimester), or underlying cirrhosis (ACLF risk) ([INASL ALF 2020](https://pmc.ncbi.nlm.nih.gov/articles/PMC7335721/); [WHO HEV](https://www.who.int/news-room/fact-sheets/detail/hepatitis-e)).

---

## 8. Hepatitis B (acute & chronic)

- **Condition ID:** `inf-hepatitis-b`
- **Population:** `Population.Infections`
- **Summary:** Parenteral, sexual, and perinatal DNA virus (HBV): screen with HBsAg, stage chronic disease with HBV DNA PCR and APRI/FibroScan, treat eligible patients under WHO 2024 / NVHCP India with TDF or Entecavir (never lamivudine monotherapy), and prevent MTCT with maternal TDF + newborn vaccine & HBIG within 12 h.
- **Search Keywords:** `hepatitis B`, `HBV`, `CHB`, `HBsAg`, `HBV DNA`, `HBeAg`, `tenofovir`, `TDF`, `entecavir`, `ETV`, `TAF`, `HBIG`, `cirrhosis`, `HCC surveillance`, `APRI`, `FibroScan`, `NVHCP`, `PMTCT`

### Clinical Photo


- **Resource name (`resName`):** `inf_hepatitis_b_virions`
- **Caption:** Transmission electron micrograph (TEM) of Hepatitis B virus (HBV) virions, also known as Dane particles (CDC PHIL #270).
- **Credit:** CDC / Dr. Erskine Palmer (CDC PHIL #270)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Hepatitis_B_virus_01.jpg](https://commons.wikimedia.org/wiki/File:Hepatitis_B_virus_01.jpg)

### First Steps
1. Confirm HBV status and acuity: **HBsAg** (first-time adult triple panel: HBsAg, anti-HBs, total anti-HBc; add **IgM anti-HBc** if acute hepatitis suspected; repeat HBsAg at 6 months to confirm chronicity) ([CDC HBV Overview](https://www.cdc.gov/hepatitis-b/hcp/clinical-overview/index.html); [WHO 2024 Ch. 5](https://www.ncbi.nlm.nih.gov/books/NBK614987/)).
2. Stage liver disease and viral load: LFTs, platelets, PT/INR, **quantitative HBV DNA PCR**, **HBeAg/anti-HBe**, abdominal USG, and **APRI** / **FibroScan** ([WHO 2024 Ch. 5](https://www.ncbi.nlm.nih.gov/books/NBK614987/); [INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)).
3. Screen co-infections (**HIV** mandatory before TDF/ETV/TAF monotherapy, **HCV**, **HDV**) and baseline renal function (creatinine, eGFR, serum phosphate, urine dipstick) ([WHO 2024 Ch. 5](https://www.ncbi.nlm.nih.gov/books/NBK614987/); [WHO 2024 Ch. 6](https://www.ncbi.nlm.nih.gov/books/NBK614996/)).
4. Screen all pregnant women for HBsAg in the 1st trimester, test/vaccinate HBsAg-negative household/sexual contacts, and advise complete alcohol/smoking abstinence ([NVHCP HBV Guidelines](https://nvhcp.mohfw.gov.in/common_libs/TechnicalGuidelines-for-Diagnosis-management-of-Hepatitis-B.pdf); [INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)).

### Screening & Investigation of Choice (IOC)
- **Screening IOC:** **HBsAg** (RDT or immunoassay; universal once in adults and in every pregnancy) ([CDC HBV Overview](https://www.cdc.gov/hepatitis-b/hcp/clinical-overview/index.html); [WHO 2024 Ch. 5](https://www.ncbi.nlm.nih.gov/books/NBK614987/)).
- **Acute HBV IOC:** **HBsAg + IgM anti-HBc** ([INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)).
- **Chronic HBV Staging IOC:** **Quantitative HBV DNA PCR**, **HBeAg/anti-HBe**, **ALT** (WHO 2024 ULN: <30 U/L men/boys, <19 U/L women/girls; INASL ULN ≤40 U/L), plus **APRI** (`>0.5` = significant fibrosis ≥F2; `>1.0` = cirrhosis F4) and/or **FibroScan** (`>7.0 kPa` = ≥F2; `>12.5 kPa` = F4) ([WHO 2024 Ch. 5](https://www.ncbi.nlm.nih.gov/books/NBK614987/); [INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)).

### Other Investigations & Key Findings
- **HCC Surveillance:** Abdominal **USG + serum AFP every 6 months** for all patients with cirrhosis, family history of HCC, or age >40 yr with HBV DNA >20,000 IU/mL — continue even if therapy is stopped ([WHO 2024 Ch. 18](https://www.ncbi.nlm.nih.gov/books/NBK614992/); [INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)).
- **WHO 2024 Treatment Eligibility (treat all adults & adolescents ≥12 yr meeting ANY 1 of 4 criteria):** (1) Significant fibrosis ≥F2 (`APRI >0.5` / `FibroScan >7.0 kPa`) or cirrhosis F4 regardless of DNA/ALT; (2) HBV DNA >2,000 IU/mL + ALT >ULN; (3) HIV/HDV/HCV co-infection, family history of cirrhosis/HCC, immunosuppression, diabetes/MASLD, or extrahepatic disease; (4) Persistently abnormal ALT (≥2 values over 6–12 mo) if HBV DNA unavailable ([WHO 2024 Ch. 5](https://www.ncbi.nlm.nih.gov/books/NBK614987/)).
- Screen HBsAg + IgG anti-HBc before chemotherapy, rituximab, biologics, or high-dose steroids ([INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)).

### Treatment Tiers

#### Tier 1: Drug of choice — First-Line High-Barrier Nucleos(t)ide Analogues (NVHCP India & WHO 2024)
- **Tenofovir disoproxil fumarate (TDF)**
  - **Regimen:** `300 mg PO once daily (OD)`
  - **Cautions:** Preferred first-line under NVHCP India and WHO 2024 (and preferred in pregnancy/childbearing age). Monitor eGFR and serum phosphate every 3–6 mo; adjust if CrCl <50 mL/min. Never use as monotherapy in untreated HIV.
  - **Citations:** [NVHCP HBV Guidelines](https://nvhcp.mohfw.gov.in/common_libs/TechnicalGuidelines-for-Diagnosis-management-of-Hepatitis-B.pdf), [WHO 2024 Ch. 6](https://www.ncbi.nlm.nih.gov/books/NBK614996/)
- **Entecavir (ETV)**
  - **Regimen:** `0.5 mg PO once daily on an empty stomach (≥2 h before/after meals) for nucleoside-naïve compensated CHB; 1 mg PO once daily for decompensated cirrhosis or lamivudine-exposed/refractory patients`
  - **Cautions:** Preferred over TDF in renal impairment or osteoporosis; adjust if CrCl <50 mL/min; avoid in pregnancy and as monotherapy in HIV co-infection.
  - **Citations:** [WHO 2024 Ch. 6](https://www.ncbi.nlm.nih.gov/books/NBK614996/), [AASLD 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5987259/)

#### Tier 2: Special Groups — Renal/Bone Risk, Pregnancy/PMTCT, HIV Co-infection, & Acute/Chemo Prophylaxis
- **Tenofovir alafenamide fumarate (TAF) (Renal/bone risk or age >60)**
  - **Regimen:** `25 mg PO once daily (no renal adjustment if eGFR ≥15 mL/min)`
  - **Cautions:** Not recommended if eGFR <15 mL/min; contraindicated with rifampicin, phenytoin, or carbamazepine.
  - **Citations:** [WHO 2024 Ch. 6](https://www.ncbi.nlm.nih.gov/books/NBK614996/), [INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)
- **Pregnancy & Perinatal PMTCT**
  - **Regimen:** `Mother: TDF 300 mg PO OD from 28 weeks gestation (INASL: 24–28 wk) if HBV DNA ≥200,000 IU/mL or HBeAg+ (continue until delivery/12 wk postpartum, or indefinitely if maternal indication). Newborn: single-antigen HBV birth-dose vaccine PLUS HBIG 0.5 mL IM at separate limbs within 12 hours of birth, then complete UIP series (6, 10, 14 wk).`
  - **Cautions:** Switch ETV to TDF in pregnancy; breastfeeding is NOT contraindicated; test infant HBsAg/anti-HBs at 9–18 months.
  - **Citations:** [WHO 2024 Ch. 7](https://www.ncbi.nlm.nih.gov/books/NBK615006/), [CDC Perinatal HBV](https://www.cdc.gov/hepatitis-b/hcp/perinatal-provider-overview/index.html), [INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)
- **HIV/HBV Co-infection, Severe Acute HBV, & Chemotherapy Prophylaxis**
  - **Regimen:** `HIV/HBV: full ART with TDF (or TAF) + 3TC/FTC + Dolutegravir. Severe acute HBV (bilirubin >5 mg/dL + INR >1.5 >4 wk) / ALF / ACLF: immediate TDF or ETV. Immunosuppression/chemo prophylaxis: ETV/TDF/TAF during therapy + ≥12 mo post-cessation (≥18 mo after rituximab).`
  - **Citations:** [WHO 2024 Ch. 6](https://www.ncbi.nlm.nih.gov/books/NBK614996/), [INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)

### Avoid
- **Lamivudine (3TC) monotherapy, adefovir, or telbivudine:** Unacceptably high antiviral resistance rates ([WHO 2024 Ch. 6](https://www.ncbi.nlm.nih.gov/books/NBK614996/)).
- **Abrupt discontinuation of nucleos(t)ide analogues (and NEVER stop in cirrhosis):** Risks severe viral rebound, acute hepatic flare, and decompensation ([WHO 2024 Ch. 19](https://www.ncbi.nlm.nih.gov/books/NBK614981/)).
- **TDF/TAF/ETV monotherapy in untreated HIV**, **TAF with rifampicin/phenytoin/carbamazepine**, and **PegIFN-α in decompensated cirrhosis or pregnancy** ([WHO 2024 Ch. 6](https://www.ncbi.nlm.nih.gov/books/NBK614996/); [INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)).

### Escalate / Red Flags
- Decompensated cirrhosis (ascites, variceal bleeding, hepatic encephalopathy, jaundice, coagulopathy) or severe HBV reactivation flare (ALT >1,000 U/L or INR ≥1.5) — start potent NA immediately and refer for hepatology/ICU/transplant care ([WHO 2024 Ch. 5](https://www.ncbi.nlm.nih.gov/books/NBK614987/); [INASL 2018](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/)).
- Focal liver lesion on 6-monthly USG or rising AFP (suspected HCC) — urgent multiphasic CT/MRI and oncology/hepatology referral ([WHO 2024 Ch. 18](https://www.ncbi.nlm.nih.gov/books/NBK614992/)).

---

## Primary Sources Reference Table (Batch 2: 33 Verified Sources)

| Source ID | Authority | Document | Title | Year | URL |
|---|---|---|---|---|---|
| `inf-dh-ncvbdc-dengue-2023` | `NCVBDC` | National Guidelines | National Guidelines for Clinical Management of Dengue Fever | 2023 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf) |
| `inf-dh-icmr-stw-dengue-2019` | `ICMR` | Standard Treatment Workflow | Dengue Fever (Paediatrics, ICD-10 A90) | 2019 | [icmr.gov.in](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952338_paediatrics_dengue_fever.pdf) |
| `inf-dh-cdc-dengue-care-2026` | `CDC` | Clinical Guidance | Clinical Care of Dengue | 2026 | [cdc.gov](https://www.cdc.gov/dengue/hcp/clinical-care/index.html) |
| `inf-dh-cdc-dengue-test-2025` | `CDC` | Diagnostic Guidance | Clinical Testing Guidance for Dengue | 2025 | [cdc.gov](https://www.cdc.gov/dengue/hcp/diagnosis-testing/index.html) |
| `inf-dh-icmr-diphtheria-2017` | `ICMR` | ICMR-NITM Policy Brief | Diphtheria: A battle not yet won (with CRI Kasauli DAT Annexure) | 2017 | [icmr.gov.in](https://www.icmr.gov.in/icmrobject/custom_data/pdf/policy-briefs/ICMR_NITM_ROY_Policy_Brief_Diptheria_Final_2017.pdf) |
| `inf-dh-who-diphtheria-2024` | `WHO` | Clinical Practice Guideline | Clinical management of diphtheria (WHO/DIPH/Clinical/2024.1) | 2024 | [iris.who.int](https://iris.who.int/server/api/core/bitstreams/6f1840f0-056e-4823-991c-19fdae2d09d3/content) |
| `inf-dh-who-diphtheria-2017` | `WHO` | Operational Protocol | Operational protocol for clinical management of Diphtheria | 2017 | [who.int](https://www.who.int/docs/default-source/documents/publications/operational-protocol-for-clinical-management-of-diphtheria.pdf?sfvrsn=70868342_1) |
| `inf-dh-cdc-diphtheria-clin-2026` | `CDC` | Clinical Guidance | Clinical Guidance for Diphtheria | 2026 | [cdc.gov](https://www.cdc.gov/diphtheria/hcp/clinical-guidance/index.html) |
| `inf-dh-cdc-diphtheria-lab-2026` | `CDC` | Laboratory Guidance | Laboratory Testing for Diphtheria | 2026 | [cdc.gov](https://www.cdc.gov/diphtheria/php/laboratories/index.html) |
| `inf-dh-icmr-amr-2023` | `ICMR` | AMRSN Annual Report | Antimicrobial Resistance Research and Surveillance Network Annual Report | 2023 | [icmr.gov.in](https://www.icmr.gov.in/icmrobject/uploads/Documents/1725536060_annual_report_2023.pdf) |
| `inf-dh-icmr-stg-2019` | `ICMR` | Clinical Guideline | Treatment Guidelines for Antimicrobial Use in Common Syndromes (2nd ed.) | 2019 | [icmr.gov.in](https://www.icmr.gov.in/icmrobject/custom_data/pdf/resource-guidelines/Treatment_Guidelines_2019_Final.pdf) |
| `inf-dh-iap-enteric-2022` | `IAP` | Standard Treatment Guideline | IAP STG 2022 — Enteric Fever (summary) | 2022 | [medicaldialogues.in](https://medicaldialogues.in/pediatrics-neonatology/guidelines/enteric-fever-indian-academy-of-pediatrics-guidelines-96198) |
| `inf-dh-cdc-typhoid-2024` | `CDC` | Clinical Guidance | Clinical Guidance for Typhoid Fever and Paratyphoid Fever | 2024 | [cdc.gov](https://www.cdc.gov/typhoid-fever/hcp/clinical-guidance/index.html) |
| `inf-dh-cdc-typhoid-yb-2025` | `CDC` | CDC Yellow Book | Typhoid and Paratyphoid Fever | 2025 | [cdc.gov](https://www.cdc.gov/yellow-book/hcp/travel-associated-infections-diseases/typhoid-and-paratyphoid-fever.html) |
| `inf-dh-cochrane-enteric-2022` | `Paper` | Cochrane Syst Rev (PMC9686137) | Treatment of Enteric Fever with Cephalosporins | 2022 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC9686137/) |
| `inf-dh-ncvbdc-elf-2024` | `NCVBDC` | National Guideline | Revised Guideline on Elimination of Lymphatic Filariasis | 2024 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/Doc/Guidelines/Fil/ELF-Guideline-2024.pdf) |
| `inf-dh-who-lf-mda-2017` | `WHO` | Clinical Guideline | Alternative Mass Drug Administration Regimens to Eliminate Lymphatic Filariasis | 2017 | [iris.who.int](https://iris.who.int/server/api/core/bitstreams/7459f928-4584-415b-af52-ebd242af9463/content) |
| `inf-dh-who-lf-2024` | `WHO` | Fact Sheet | Lymphatic Filariasis | 2024 | [who.int](https://www.who.int/news-room/fact-sheets/detail/lymphatic-filariasis) |
| `inf-dh-cdc-lf-2024` | `CDC` | Clinical Guidance | Clinical Treatment of Lymphatic Filariasis | 2024 | [cdc.gov](https://www.cdc.gov/filarial-worms/hcp/clinical-care/index.html) |
| `inf-dh-cdc-oncho-2024` | `CDC` | Clinical Guidance | Clinical Treatment of Onchocerciasis | 2024 | [cdc.gov](https://www.cdc.gov/filarial-worms/hcp/clinical-care/onchocerciasis.html) |
| `inf-dh-mohfw-ncdc-amr` | `MoHFW` | National Treatment Guidelines | National Treatment Guidelines for Antimicrobial Use in Infectious Diseases | 2023 | [clinicalestablishments.mohfw.gov.in](https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/86111.pdf) |
| `inf-dh-icmr-diarrhoea-2022` | `ICMR` | Treatment Guidelines | Chapter 5: Acute Diarrhea Treatment | 2022 | [amrtg.icmr.org.in](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html) |
| `inf-dh-cdc-giardia-care` | `CDC` | Clinical Guidance | Patient Care for Giardia Infection | 2024 | [cdc.gov](https://www.cdc.gov/giardia/hcp/clinical-care/index.html) |
| `inf-dh-cdc-giardia-dx` | `CDC` | Diagnostic Guidance | Clinical Testing and Diagnosis for Giardia Infection | 2024 | [cdc.gov](https://www.cdc.gov/giardia/hcp/diagnosis-testing/index.html) |
| `inf-dh-nih-giardia-ped` | `IDSA` | NIH/CDC/IDSA Pediatric OI | Guidelines for Prevention & Treatment of Opportunistic Infections — Giardiasis | 2019 | [clinicalinfo.hiv.gov](https://clinicalinfo.hiv.gov/en/guidelines/hiv-clinical-guidelines-pediatric-opportunistic-infections/giardiasis) |
| `inf-dh-gardner-giardia-2001` | `Paper` | Clin Microbiol Rev (PMC88965) | Treatment of Giardiasis (Gardner & Hill) | 2001 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC88965/) |
| `inf-dh-naco-sti-2024` | `MoHFW` | NACO Technical Guidelines (PMC13318318) | National Technical Guidelines on STI & RTI (2024): Summary of Updated Recommendations | 2024 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC13318318/) |
| `inf-dh-who-sti-2024` | `WHO` | Global STI Guidelines | Updated Recommendations for Treatment of *N. gonorrhoeae*, *C. trachomatis*, & Syphilis | 2024 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK605896/) |
| `inf-dh-cdc-gonorrhea-2021` | `CDC` | STI Treatment Guidelines | Gonococcal Infections Among Adolescents and Adults | 2021 | [cdc.gov](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-adults.htm) |
| `inf-dh-cdc-pid-2021` | `CDC` | STI Treatment Guidelines | Pelvic Inflammatory Disease (PID) | 2021 | [cdc.gov](https://www.cdc.gov/std/treatment-guidelines/pid.htm) |
| `inf-dh-cdc-gonorrhea-child` | `CDC` | STI Treatment Guidelines | Gonococcal Infections Among Infants and Children | 2021 | [cdc.gov](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-children.htm) |
| `inf-dh-cdc-gonorrhea-neo` | `CDC` | STI Treatment Guidelines | Gonococcal Infections Among Neonates | 2021 | [cdc.gov](https://www.cdc.gov/std/treatment-guidelines/gonorrhea-neonates.htm) |
| `inf-dh-nvhcp-lab-2018` | `MoHFW` | NVHCP Laboratory Guidelines | National Laboratory Guidelines for Testing of Viral Hepatitis | 2018 | [nvhcp.mohfw.gov.in](https://nvhcp.mohfw.gov.in/common_libs/national-laboratory-guidelines.pdf) |
| `inf-dh-nvhcp-hbv-2019` | `MoHFW` | NVHCP Technical Guidelines | Technical Guidelines for Diagnosis & Management of Hepatitis B | 2019 | [nvhcp.mohfw.gov.in](https://nvhcp.mohfw.gov.in/common_libs/TechnicalGuidelines-for-Diagnosis-management-of-Hepatitis-B.pdf) |
| `inf-dh-icmr-stw-lf-2022` | `ICMR` | Standard Treatment Workflow | Liver Failure (ICMR STW) | 2022 | [icmr.gov.in](https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567562_liver_failure.pdf) |
| `inf-dh-inasl-alf-2020` | `Paper` | INASL Consensus (PMC7335721) | INASL Consensus Statement on Acute Liver Failure (Part-1) | 2020 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC7335721/) |
| `inf-dh-inasl-hbv-2018` | `Paper` | INASL Andaman Statements (PMC5938334) | INASL Position Statements on Prevention, Diagnosis & Management of HBV in India | 2018 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC5938334/) |
| `inf-dh-who-hav-2026` | `WHO` | Fact Sheet | Hepatitis A | 2026 | [who.int](https://www.who.int/news-room/fact-sheets/detail/hepatitis-a) |
| `inf-dh-who-hev-2026` | `WHO` | Fact Sheet | Hepatitis E | 2026 | [who.int](https://www.who.int/news-room/fact-sheets/detail/hepatitis-e) |
| `inf-dh-cdc-hav-care` | `CDC` | Clinical Guidance | Clinical Care of Hepatitis A | 2026 | [cdc.gov](https://www.cdc.gov/hepatitis-a/hcp/clinical-care/index.html) |
| `inf-dh-cdc-hav-dx` | `CDC` | Diagnostic Guidance | Clinical Screening and Diagnosis for Hepatitis A | 2026 | [cdc.gov](https://www.cdc.gov/hepatitis-a/hcp/diagnosis-testing/index.html) |
| `inf-dh-cdc-acip-hav-2018` | `CDC` | ACIP MMWR Update | Use of Hepatitis A Vaccine for Postexposure Prophylaxis | 2018 | [cdc.gov](https://www.cdc.gov/mmwr/volumes/67/wr/mm6743a5.htm) |
| `inf-dh-who-hbv-2024-ch5` | `WHO` | 2024 HBV Guidelines Ch. 5 | Who to treat among people with CHB | 2024 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK614987/) |
| `inf-dh-who-hbv-2024-ch6` | `WHO` | 2024 HBV Guidelines Ch. 6 | First-line antiviral therapies for CHB | 2024 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK614996/) |
| `inf-dh-who-hbv-2024-ch7` | `WHO` | 2024 HBV Guidelines Ch. 7 | Preventing mother-to-child transmission of HBV | 2024 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK615006/) |
| `inf-dh-who-hbv-2024-ch18` | `WHO` | 2024 HBV Guidelines Ch. 18 | Surveillance for HCC among people with CHB | 2024 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK614992/) |
| `inf-dh-who-hbv-2024-ch19` | `WHO` | 2024 HBV Guidelines Ch. 19 | When to stop and restart antiviral therapy | 2024 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK614981/) |
| `inf-dh-cdc-hbv-overview` | `CDC` | Clinical Guidance | Clinical Overview of Hepatitis B | 2026 | [cdc.gov](https://www.cdc.gov/hepatitis-b/hcp/clinical-overview/index.html) |
| `inf-dh-cdc-hbv-perinatal` | `CDC` | Clinical Guidance | Clinical Overview of Perinatal Hepatitis B | 2024 | [cdc.gov](https://www.cdc.gov/hepatitis-b/hcp/perinatal-provider-overview/index.html) |
| `inf-dh-aasld-hbv-2018` | `Paper` | AASLD Guidance (PMC5987259) | AASLD Guidelines for Treatment of Chronic Hepatitis B | 2018 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC5987259/) |

---

## Claude Code Integration Spec (Drop-In Instructions)

When this document is placed in the `Medicalaid` repository and approved by the clinician:

1. **Prerequisites (from Batch 1):** Ensure `Model.kt` has `Population.Infections("Infectious diseases")`, `data class ConditionImage(...)`, and `val image: ConditionImage? = null` on `Condition`.
2. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/InfectionsDH.kt`**
   - Create `infectionsDHSources: List<Source>` (all sources from the Reference Table above using the `inf-dh-...` IDs) and `infectionsDHConditions: List<Condition>` (all 8 diseases above with exact IDs, keywords, `image`, `firstSteps`, `info`, `tiers`, `WeightDose`, `maxDose`, `avoid`, `escalate`, and `citations`).
   - Note: Confirm zero unused sources in `infectionsDHSources` (`ContentIntegrityTest` enforces 100% citation usage).
3. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/Conditions.kt`**
   - Add `Group(infectionsDHConditions, infectionsDHSources)` to `inReviewInfections` (or directly to `releasedInfections` once approved by the clinician).
4. **Offline Image Bundle (`composeApp/src/commonMain/composeResources/drawable/`)**
   - Append these 8 verified open-licence images to `scripts/fetch-infection-images.sh` (fetch at 800 px width and compress to `.webp` via `cwebp -q 80` if available):
     - `inf_dengue_rash` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Dengue_recovery_rash_(White_islands_in_red_sea).jpg?width=800`
     - `inf_diphtheria_bull_neck` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Diphtheria_bull_neck.5325_lores.jpg?width=800`
     - `inf_typhoid_rose_spots` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Salmonella_typhi_typhoid_fever_PHIL_2215_lores.jpg?width=800`
     - `inf_wuchereria_bancrofti` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Wuchereria_bancrofti_1_DPDX.JPG?width=800`
     - `inf_giardia_lamblia` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Giardia_lamblia_SEM_8698_lores.jpg?width=800`
     - `inf_neisseria_gonorrhoeae` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Neisseria_gonorrhoeae_PHIL_3693_lores.jpg?width=800`
     - `inf_scleral_icterus` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Scleral_Icterus.jpg?width=800`
     - `inf_hepatitis_b_virions` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Hepatitis_B_virus_01.jpg?width=800`
5. **Verification:** Run `./gradlew :composeApp:allTests` to confirm `ContentIntegrityTest` passes.
