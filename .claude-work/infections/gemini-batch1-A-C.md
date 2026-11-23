# Medicalaid — Infections A–Z: Batch 1 (A–C) Clinical Review & Implementation Doc

**Section:** Infectious diseases (`Population.Infections`)  
**Order:** Strict alphabetical — Batch 1 of A–Z (8 diseases)  
**Sourcing policy:** India first (ICMR, MoHFW, NCDC, NCVBDC), then WHO, CDC, IDSA/ATS, RCOG, FDA DailyMed, Cochrane. Every statement, investigation, dose, and caution carries a verified primary source URL.  
**Purpose of this document:** Complete clinician review copy (with embedded open-licence clinical photos) **and** drop-in specification for Claude Code to add directly into the `Medicalaid` repository once reviewed.

---

## Batch 1 Overview (A–C)

| # | Disease | Condition ID | Investigation of Choice (IOC) | First-Line Drug of Choice (DOC) | Open-Licence Photo |
|---|---|---|---|---|---|
| 1 | [Acute infectious diarrhoea & cholera](#1-acute-infectious-diarrhoea--cholera) | `inf-acute-diarrhoea` | Clinical (watery); stool culture/AST (dysentery); TCBS stool culture (cholera) | Reduced-osmolarity ORS / IV Ringer's lactate; Doxycycline 300 mg SD (severe cholera) or Azithromycin 1 g SD (pregnancy/children); Ciprofloxacin / Ceftriaxone (dysentery) | Yes (`inf_vibrio_cholerae`, PD) |
| 2 | [Amoebiasis (intestinal & liver abscess)](#2-amoebiasis-intestinal--liver-abscess) | `inf-amoebiasis` | Stool PCR / antigen EIA (colitis); Abdominal USG + amoebic serology (ALA) | Metronidazole (or Tinidazole) **followed by** Paromomycin or Diloxanide furoate | Yes (`inf_entamoeba_histolytica`, PD) |
| 3 | [Ascariasis & soil-transmitted helminths](#3-ascariasis--soil-transmitted-helminths) | `inf-ascariasis` | Stool ova microscopy (formalin–ethyl acetate concentration) | Albendazole 400 mg PO single dose (or Mebendazole 500 mg SD) | Yes (`inf_ascaris_egg`, PD) |
| 4 | [Bacterial meningitis (acute)](#4-bacterial-meningitis-acute) | `inf-bacterial-meningitis` | Lumbar puncture (CSF Gram stain, cell count, glucose/protein, culture + AST, PCR) | IV Dexamethasone + Ceftriaxone 2 g IV q12h + Vancomycin (+ Ampicillin if >50–60 yr / risk) within 1 h | Pending (`image = null`) |
| 5 | [Brucellosis](#5-brucellosis) | `inf-brucellosis` | Rose Bengal screen; Blood/bone marrow culture (gold standard); SAT ≥1:160 | Doxycycline 100 mg BD × 45 d + Streptomycin 1 g IM OD × 15 d (or + Rifampicin × 45 d) | Yes (`inf_brucella_spp`, PD) |
| 6 | [Chickenpox (varicella)](#6-chickenpox-varicella) | `inf-chickenpox` | Clinical diagnosis; VZV PCR from vesicular swab (atypical/severe) | Supportive (<12 yr healthy); Oral Acyclovir 800 mg 5×/d × 5–7 d within 24 h (adults / high-risk / pregnancy); IV Acyclovir if severe | Yes (`inf_chickenpox_rash`, PD) |
| 7 | [Chikungunya](#7-chikungunya) | `inf-chikungunya` | RT-PCR (Days 0–7); IgM MAC-ELISA (>Day 7) — plus simultaneous dengue screen | Paracetamol (acute phase); NSAIDs only **after** dengue is excluded | Yes (`inf_chikungunya_rash`, CC BY-SA 4.0) |
| 8 | [Community-acquired pneumonia (CAP)](#8-community-acquired-pneumonia-cap) | `inf-cap` | Chest X-ray (PA ± lateral) + CURB-65 severity score; blood/sputum cultures if inpatient | Amoxicillin 1 g TID (healthy outpatient); Co-amoxiclav + Azithromycin/Doxycycline (comorbid); IV Ceftriaxone + Azithromycin (inpatient) | Yes (`inf_lobar_pneumonia_cxr`, CC0 1.0) |

---

## 1. Acute infectious diarrhoea & cholera

- **Condition ID:** `inf-acute-diarrhoea`
- **Population:** `Population.Infections`
- **Summary:** Rehydration first (reduced-osmolarity ORS or Ringer's lactate); antibiotics only for severe cholera or bloody dysentery.
- **Search Keywords:** `diarrhoea`, `diarrhea`, `AGE`, `acute gastroenteritis`, `cholera`, `Vibrio cholerae`, `rice water stool`, `dysentery`, `Shigella`, `ORS`, `rehydration`, `loose stools`, `dehydration`

### Clinical Photo


- **Resource name (`resName`):** `inf_vibrio_cholerae`
- **Caption:** Gram-stained *Vibrio cholerae* showing characteristic curved ('comma-shaped') Gram-negative rods.
- **Credit:** CDC Public Health Image Library (PHIL #5324)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Vibrio_cholerae_gram_stain_CDC.jpg](https://commons.wikimedia.org/wiki/File:Vibrio_cholerae_gram_stain_CDC.jpg)

### First Steps
1. Assess dehydration immediately (mental status, pulse, skin pinch, sunken eyes, urine output) — rehydration is the primary life-saving therapy ([ICMR 2022](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html); [WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154450/)).
2. None to moderate hypovolaemia: start reduced-osmolarity ORS (75 mEq/L Na+, 75 mmol/L glucose) by frequent small sips ([ICMR 2022](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)).
3. Severe hypovolaemia or shock: infuse IV Ringer's lactate immediately (preferred over 0.9% saline because it replaces potassium and base) ([ICMR 2022](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html); [WHO Pocket Book](https://www.ncbi.nlm.nih.gov/books/NBK154450/)).
4. Notify suspected cholera outbreaks under IDSP and enforce strict hand hygiene and safe water precautions.

### Screening & Investigation of Choice (IOC)
- Acute watery diarrhoea (mild–moderate, non-outbreak): routine stool microbiology is not required — manage clinically with rehydration ([ICMR 2022 — Treatment](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)).
- Dysentery (bloody diarrhoea): stool microscopy and stool culture with antimicrobial susceptibility testing are mandatory to guide therapy against *Shigella* ([ICMR 2022 — Treatment](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)).
- Suspected cholera: stool culture (TCBS agar / alkaline peptone water) is the confirmatory IOC in outbreaks; do not delay rehydration awaiting lab confirmation ([WHO GTFCC 2025 — Primary therapy](https://www.gtfcc.org/wp-content/uploads/2025/02/gtfcc-technical-note-on-use-of-antibiotics-for-the-treatment-of-cholera.pdf)).

### Other Investigations & Key Findings
- Severe dehydration or shock: serum electrolytes, renal function (creatinine/urea), blood glucose, and venous blood gas for metabolic acidosis and hypokalaemia ([WHO Pocket Book §1.3](https://www.ncbi.nlm.nih.gov/books/NBK154450/)).
- Antimicrobial resistance note in India: ampicillin and TMP-SMX are no longer drugs of choice for *Shigella* due to high resistance; azithromycin and cefixime resistance is approaching 20% ([ICMR 2022 — Antibiotic Treatment for Diarrhea](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)).

### Treatment Tiers

#### Tier 1: Drug of choice — severe cholera (outbreak / severe dehydration)
> *Note:* Antibiotics are NOT indicated for routine acute watery diarrhoea in adults. Give in severe cholera to shorten illness and stool volume (ICMR; WHO GTFCC).

- **Doxycycline (non-pregnant adults)**
  - **Regimen:** `300 mg PO as a single dose`
  - **Cautions:** ICMR: not recommended in children and pregnant women (use azithromycin instead). Always pair with aggressive ORS or IV Ringer's lactate rehydration.
  - **Citations:** [ICMR 2022 — Table: V. cholerae](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html), [WHO GTFCC 2025](https://www.gtfcc.org/wp-content/uploads/2025/02/gtfcc-technical-note-on-use-of-antibiotics-for-the-treatment-of-cholera.pdf)
- **Azithromycin (preferred in pregnancy & children; alternative in adults)**
  - **Regimen:** `Adults: 1 g PO as a single dose`
  - **Cautions:** Preferred first-line in pregnant women and children where doxycycline is avoided (ICMR; WHO GTFCC).
  - **Citations:** [ICMR 2022 — Table: V. cholerae](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html), [WHO GTFCC 2025](https://www.gtfcc.org/wp-content/uploads/2025/02/gtfcc-technical-note-on-use-of-antibiotics-for-the-treatment-of-cholera.pdf)

#### Tier 2: Drug of choice — acute bloody diarrhoea (suspected *Shigella*)
> *Note:* Send stool microscopy and culture; adjust once sensitivity returns (ICMR).

- **Ciprofloxacin**
  - **Regimen:** `500 mg PO twice daily for 3 days`
  - **Cautions:** Adjust per local antibiogram; avoid in pregnancy unless no safer option.
  - **Citations:** [ICMR 2022 — Table: Shigella](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)
- **Ceftriaxone (severe dysentery / alternative)**
  - **Regimen:** `2 g IV as a single dose (ICMR); extend if bacteraemic or not improving`
  - **Citations:** [ICMR 2022 — Table: Shigella](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)
- **Azithromycin (suspected *Campylobacter*)**
  - **Regimen:** `500 mg PO once daily for 3 days`
  - **Citations:** [ICMR 2022 — Table: Campylobacter](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)

### Avoid
- **Loperamide in dysentery:** Avoid antimotility agents in bloody diarrhoea or high fever — risk of toxic megacolon and prolonged pathogen shedding ([ICMR 2022 — Treatment](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)).
- **Routine antibiotics in watery diarrhoea:** Not indicated for non-cholera acute watery diarrhoea; drives resistance without clinical benefit ([ICMR 2022 — Treatment](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)).
- **Routine probiotics or prebiotics:** Not recommended for acute infectious diarrhoea in adults except post-antibiotic-associated illness ([ICMR 2022 — Treatment](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)).
- **Plain dextrose (5% D) for severe dehydration:** Unacceptable for cholera/dehydration resuscitation — lacks sodium, potassium, and base; use Ringer's lactate ([ICMR 2022 — Rehydration](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html)).

### Escalate / Red Flags
- Shock (cold extremities, CRT >3 s, weak rapid pulse, hypotension) or lethargy/coma — immediate IV Ringer's lactate resuscitation.
- Intractable vomiting preventing oral intake, anuria/oliguria, or severe electrolyte derangement (hypokalaemia, acidosis).
- Severe acute malnutrition with dehydration — avoid rapid standard IV boluses unless in shock ([WHO Pocket Book §1.4](https://www.ncbi.nlm.nih.gov/books/NBK154450/)).

---

## 2. Amoebiasis (intestinal & liver abscess)

- **Condition ID:** `inf-amoebiasis`
- **Population:** `Population.Infections`
- **Summary:** *Entamoeba histolytica* colitis or liver abscess: nitroimidazole tissue agent followed by a luminal amoebicide to prevent relapse.
- **Search Keywords:** `amoebiasis`, `amebiasis`, `Entamoeba histolytica`, `amoebic dysentery`, `amoebic colitis`, `amoebic liver abscess`, `ALA`, `anchovy sauce`, `metronidazole`, `tinidazole`, `diloxanide`, `paromomycin`

### Clinical Photo


- **Resource name (`resName`):** `inf_entamoeba_histolytica`
- **Caption:** *Entamoeba histolytica* cyst (chlorazol black stain). Mature cysts have 4 nuclei with central karyosomes.
- **Credit:** CDC Public Health Image Library (PHIL #1474)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Entamoeba_histolytica_01.jpg](https://commons.wikimedia.org/wiki/File:Entamoeba_histolytica_01.jpg)

### First Steps
1. Distinguish invasive colitis (bloody/mucoid stools, abdominal cramps) from amoebic liver abscess (fever, RUQ pain, tender hepatomegaly) — both require a tissue agent followed by a luminal agent.
2. Collect stool samples before starting metronidazole when amoebic colitis is suspected ([BMJ FG 2024](https://fg.bmj.com/content/16/1/37)).

### Screening & Investigation of Choice (IOC)
- Intestinal amoebiasis — IOC: *E. histolytica*-specific stool PCR or stool antigen EIA (microscopy cannot distinguish pathogenic *E. histolytica* from non-pathogenic *E. dispar* unless trophozoites contain ingested RBCs) ([CDC DPDx](https://www.cdc.gov/dpdx/amebiasis/index.html); [BMJ FG 2024](https://fg.bmj.com/content/16/1/37)).
- Stool microscopy (screening when PCR/EIA unavailable): examine at least 3 consecutive fresh stool samples within 10 days to improve sensitivity ([CDC DPDx — Microscopic Detection](https://www.cdc.gov/dpdx/amebiasis/index.html)).
- Amoebic liver abscess (ALA) — IOC: abdominal ultrasonography (or CT/MRI) showing a space-occupying hepatic lesion PLUS amoebic serology (positive in ~99% of ALA, though may be negative in week 1) ([CDC DPDx](https://www.cdc.gov/dpdx/amebiasis/index.html); [BMJ FG 2024](https://fg.bmj.com/content/16/1/37)).

### Other Investigations & Key Findings
- Stool microscopy is frequently negative in amoebic liver abscess; a negative stool test does not exclude ALA.
- Routine diagnostic or therapeutic aspiration of uncomplicated ALA is NOT required — medical therapy cures most cases ([BMJ FG 2024 — Treatment](https://fg.bmj.com/content/16/1/37)).
- Consider ultrasound-guided percutaneous drainage only if no clinical response after 3–4 days of therapy, abscess >10 cm (rupture risk), or left-lobe abscess threatening pericardial rupture ([BMJ FG 2024 — Treatment](https://fg.bmj.com/content/16/1/37)).

### Treatment Tiers

#### Tier 1: Drug of choice — tissue amoebicide (colitis & liver abscess)
> *Note:* Always follow tissue therapy with a luminal agent to eradicate intestinal cysts and prevent relapse (BMJ FG 2024; Cochrane).

- **Metronidazole (intestinal amoebiasis)**
  - **Regimen:** `500 mg PO three times daily for 5 days (ICMR) — 7–10 days for severe colitis or amoebic liver abscess (BMJ FG 2024)`
  - **Cautions:** Avoid alcohol during and for 48 h after therapy (disulfiram-like reaction). ALA imaging abnormalities take months to resolve — do not extend antibiotics solely for persistent cavity on USG if clinically well (BMJ FG 2024).
  - **Citations:** [ICMR 2022 — Table: Amoebiasis](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html), [BMJ FG 2024 — Table 5](https://fg.bmj.com/content/16/1/37)
- **Tinidazole (alternative tissue agent)**
  - **Regimen:** `2 g PO once daily for 3 days (intestinal colitis) or 5–6 days (amoebic liver abscess)`
  - **Cautions:** Cochrane review: may reduce clinical failure with fewer adverse effects than metronidazole.
  - **Citations:** [Cochrane 2019 — Tinidazole vs metronidazole](https://pmc.ncbi.nlm.nih.gov/articles/PMC6326239/), [BMJ FG 2024](https://fg.bmj.com/content/16/1/37)

#### Tier 2: Mandatory follow-on — luminal amoebicide (cyst eradication)
> *Note:* Give after completing metronidazole or tinidazole (and as sole therapy for confirmed asymptomatic *E. histolytica* cyst passers) (BMJ FG 2024).

- **Paromomycin**
  - **Regimen:** `25–35 mg/kg/day PO in 3 divided doses with meals for 5–10 days (typically 7 days)`
  - **Weight-based calculator (`WeightDose`):** `25.0–35.0 mg/kg per day, in 3 divided doses with meals × 5–10 days`
  - **Citations:** [FDA DailyMed Paromomycin — Dosage and Administration](https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=333d55f0-ab49-459e-8d32-1590b2623ef3), [BMJ FG 2024 — Treatment](https://fg.bmj.com/content/16/1/37)
- **Diloxanide furoate (alternative luminal agent)**
  - **Regimen:** `500 mg PO three times daily for 10 days`
  - **Cautions:** Commonly available in India; avoid in pregnancy and children <2 years.
  - **Citations:** [BMJ FG 2024 — Treatment](https://fg.bmj.com/content/16/1/37)

### Avoid
- **Treating with metronidazole alone without a luminal agent:** Metronidazole is absorbed in the upper gut and fails to clear intraluminal cysts, leading to relapse and ongoing transmission ([BMJ FG 2024](https://fg.bmj.com/content/16/1/37); [Cochrane 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6326239/)).
- **Corticosteroids before ruling out amoebic colitis:** Starting steroids for presumed inflammatory bowel disease in undiagnosed amoebic colitis can precipitate fulminant colitis and perforation ([BMJ FG 2024 — IBD & amoebiasis](https://fg.bmj.com/content/16/1/37)).

### Escalate / Red Flags
- Amoebic liver abscess >10 cm, left-lobe abscess near pericardium, or failure to defervesce after 3–4 days of nitroimidazole — refer for ultrasound-guided percutaneous drainage ([BMJ FG 2024](https://fg.bmj.com/content/16/1/37)).
- Signs of peritonitis, bowel perforation, toxic megacolon, or pleural/pericardial rupture — emergency surgical admission.

---

## 3. Ascariasis & soil-transmitted helminths

- **Condition ID:** `inf-ascariasis`
- **Population:** `Population.Infections`
- **Summary:** *Ascaris lumbricoides* (roundworm): diagnosed by stool ova microscopy; cured by single-dose albendazole 400 mg or mebendazole.
- **Search Keywords:** `ascariasis`, `Ascaris lumbricoides`, `roundworm`, `worms`, `helminth`, `STH`, `deworming`, `albendazole`, `mebendazole`, `Loeffler syndrome`, `worm obstruction`

### Clinical Photo


- **Resource name (`resName`):** `inf_ascaris_egg`
- **Caption:** Fertilized *Ascaris lumbricoides* egg (400×) showing thick rounded shell with bile-stained mammillated outer coat.
- **Credit:** CDC Public Health Image Library (PHIL #410)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Fertilized_egg_of_Ascaris_lumbricoides_PHIL_410_lores.jpg](https://commons.wikimedia.org/wiki/File:Fertilized_egg_of_Ascaris_lumbricoides_PHIL_410_lores.jpg)

### First Steps
1. Screen for intestinal or biliary obstruction (colicky pain, bilious vomiting, abdominal distension, jaundice) before routine outpatient deworming.
2. Reinforce sanitation, handwashing before meals, and washing raw vegetables to prevent reinfection ([WHO 2023](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections)).

### Screening & Investigation of Choice (IOC)
- IOC (intestinal ascariasis): stool microscopy (wet mount after formalin–ethyl acetate concentration) demonstrating characteristic ovoid thick-shelled bile-stained mammillated eggs (45–75 µm fertile; up to 90 µm unfertilized) ([CDC DPDx — Laboratory Diagnosis & Morphologic Features](https://www.cdc.gov/dpdx/ascariasis/index.html)).
- Adult females lay ~200,000 eggs/day, making stool microscopy highly sensitive once intestinal maturation occurs (~6–8 weeks post-ingestion) ([CDC DPDx](https://www.cdc.gov/dpdx/ascariasis/index.html)).
- Macroscopic diagnosis: passage of a large adult roundworm (15–35 cm) per rectum or via mouth/nose is diagnostic ([CDC DPDx](https://www.cdc.gov/dpdx/ascariasis/index.html)).

### Other Investigations & Key Findings
- Pulmonary migration phase (Löffler syndrome): peripheral eosinophilia and transient migratory pulmonary infiltrates on chest X-ray; stool eggs are still negative during early larval migration ([CDC DPDx — Clinical Presentation](https://www.cdc.gov/dpdx/ascariasis/index.html)).
- Suspected bowel or hepatobiliary obstruction: plain abdominal X-ray (air–fluid levels, 'whirlpool' worm mass) and abdominal ultrasonography (echogenic linear tubular structures in CBD or bowel) ([CDC DPDx](https://www.cdc.gov/dpdx/ascariasis/index.html)).

### Treatment Tiers

#### Tier 1: Drug of choice (first line — WHO & India NDD)
> *Note:* Single-dose benzimidazoles achieve >95% cure rates against *A. lumbricoides* (WHO).

- **Albendazole**
  - **Regimen:** `400 mg PO as a single dose (adults and children ≥2 years; 200 mg single dose for 1–2 years per WHO/MoHFW)`
  - **Cautions:** Avoid in the first trimester of pregnancy (safe in 2nd and 3rd trimesters per WHO/MoHFW deworming policy).
  - **Citations:** [WHO STH 2023 — Treatment](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections)
- **Mebendazole (alternative first line)**
  - **Regimen:** `500 mg PO as a single dose (or 100 mg PO twice daily for 3 days)`
  - **Cautions:** Avoid in first trimester of pregnancy.
  - **Citations:** [WHO STH 2023 — Treatment](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections)

#### Tier 2: Second line / special situations
- **Ivermectin (alternative)**
  - **Regimen:** `200 mcg/kg PO as a single dose (also covers co-existing Strongyloides stercoralis)`
  - **Weight-based calculator (`WeightDose`):** `200.0 mcg/kg single PO dose`
  - **Cautions:** Avoid in pregnancy and children <15 kg.
  - **Citations:** [WHO STH 2023 — Treatment](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections)

### Avoid
- **Benzimidazoles in the first trimester of pregnancy:** Defer albendazole and mebendazole until after the first trimester because of teratogenicity concerns in early organogenesis ([WHO STH 2023](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections)).

### Escalate / Red Flags
- Suspected intestinal obstruction (bilious vomiting, obstipation, palpable worm mass) or perforation — nil by mouth, IV fluids, nasogastric decompression, and urgent surgical referral ([CDC DPDx](https://www.cdc.gov/dpdx/ascariasis/index.html)).
- Biliary colic, obstructive jaundice, cholangitis, or pancreatitis from migrating adult worm — urgent ultrasonography and gastroenterology/surgical care ([CDC DPDx](https://www.cdc.gov/dpdx/ascariasis/index.html)).

---

## 4. Bacterial meningitis (acute)

- **Condition ID:** `inf-bacterial-meningitis`
- **Population:** `Population.Infections`
- **Summary:** Medical emergency: draw blood cultures, give IV dexamethasone + ceftriaxone (+ vancomycin; + ampicillin if >50–60 yr/immunocompromised) within 1 hour.
- **Search Keywords:** `meningitis`, `acute bacterial meningitis`, `ABM`, `meningococcal`, `pneumococcal`, `neck stiffness`, `lumbar puncture`, `LP`, `CSF`, `ceftriaxone`, `dexamethasone`, `Listeria`

### Clinical Photo
- **Status:** `image = null` — intentionally left blank pending verification of a confirmed Public Domain / CC-licensed meningococcal rash or CSF Gram stain image on Wikimedia Commons / CDC PHIL.

### First Steps
1. Admit or transfer immediately as a medical emergency — start empiric IV antibiotics within the 1-hour golden window ([WHO Meningitis 2025](https://www.ncbi.nlm.nih.gov/books/NBK614844/); [ICMR ABM 2022](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html)).
2. Draw 2 sets of blood cultures and blood glucose immediately; perform lumbar puncture (LP) before antibiotics ONLY if there are no contraindications and it will not delay therapy ([WHO Meningitis 2025](https://www.ncbi.nlm.nih.gov/books/NBK614844/); [ICMR ABM 2022](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html)).
3. Give IV dexamethasone 15–20 min before or with the first antibiotic dose (do NOT use steroids in neonates or cerebral malaria) ([ICMR ABM 2022](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html); [WHO Meningitis 2025](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).
4. Do NOT delay antibiotics for cranial CT or deferred LP ([WHO Meningitis 2025](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).

### Screening & Investigation of Choice (IOC)
- IOC: Lumbar puncture with CSF Gram stain (specificity ~99%), cell count + differential, glucose (with paired blood glucose), protein, culture + AST (gold standard), and CSF PCR where available ([WHO Meningitis 2025 §A.1–A.4](https://www.ncbi.nlm.nih.gov/books/NBK614844/); [ICMR ABM 2022 — Investigations and Diagnosis](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html)).
- Classic bacterial CSF profile: turbid/cloudy fluid, neutrophilic pleocytosis, low CSF glucose (CSF:blood glucose ratio ≤0.4), elevated protein (>45 mg/dL); examine within 30–60 min at room temperature — never refrigerate CSF ([ICMR ABM 2022](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html); [WHO Meningitis 2025](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).
- When to do cranial CT BEFORE LP (or defer LP if CT unavailable): GCS <10, focal neurological signs, cranial nerve deficits, papilloedema, new-onset seizures in adults, or severe immunocompromised state ([WHO Meningitis 2025](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).

### Other Investigations & Key Findings
- Blood: 2 sets of blood cultures before antibiotics, CBC, paired blood glucose, CRP or procalcitonin, electrolytes, RFT/LFT, lactate, and coagulation profile if purpuric rash/DIC ([ICMR ABM 2022](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html); [WHO Meningitis 2025](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).
- CSF lactate (if no prior antibiotics) helps distinguish bacterial from viral meningitis ([WHO Meningitis 2025 §A.3](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).
- Pathogen-specific treatment durations ([ICMR ABM 2022 — Duration & repeat LP](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html)): *N. meningitidis* / *H. influenzae* 7 days; *S. pneumoniae* 10–14 days; unknown pathogen 10–14 days ([WHO Meningitis 2025 §B.2.3](https://www.ncbi.nlm.nih.gov/books/NBK614844/) allows stopping at 7 days if fully recovered ≥48 h); *Listeria* 3–6 weeks; Gram-negative bacilli 3 weeks.

### Treatment Tiers

#### Tier 1: Drug of choice — empiric IV therapy (age 1 month – 50 years)
> *Note:* Maximal meningeal IV doses; narrow once culture/PCR/AST returns. Stop vancomycin if isolate is cephalosporin-susceptible (ICMR 2022; WHO 2025).

- **Ceftriaxone IV (plus Vancomycin IV per ICMR)**
  - **Regimen:** `Adults: Ceftriaxone 2 g IV every 12 h + Vancomycin 15–20 mg/kg IV every 8–12 h (ICMR 2022 first-line for 1 mo–50 yr; alternative: Cefotaxime + Vancomycin)`
  - **Cautions:** WHO 2025 recommends ceftriaxone/cefotaxime monotherapy where pneumococcal cephalosporin resistance is low, adding vancomycin where resistance is high.
  - **Citations:** [ICMR ABM 2022 — Empiric therapy table](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html), [WHO Meningitis 2025 §B.2.2](https://www.ncbi.nlm.nih.gov/books/NBK614844/)
- **Dexamethasone IV (adjunctive)**
  - **Regimen:** `Adults: 10 mg IV every 6 h; children: 0.15 mg/kg IV every 6 h — give 15–20 min before or with first antibiotic dose for 48–96 h (max 4 days)`
  - **Weight-based calculator (`WeightDose`):** `0.15 mg/kg per dose IV every 6 h (max 10 mg/dose)`
  - **Cautions:** Discontinue if CSF does not support bacterial meningitis or if a pathogen other than *S. pneumoniae* / *H. influenzae* is isolated (WHO 2025). Do NOT give in neonates (<1 month) or cerebral malaria (ICMR 2022; WHO 2025).
  - **Citations:** [ICMR ABM 2022 — Dexamethasone](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html), [WHO Meningitis 2025 §B.3](https://www.ncbi.nlm.nih.gov/books/NBK614844/)

#### Tier 2: Special groups — *Listeria* risk, neonates, & contact prophylaxis
- **Add Ampicillin IV (>50 yr [ICMR] / >60 yr [WHO], pregnancy, alcoholism, immunocompromised)**
  - **Regimen:** `Ampicillin 2 g IV every 4 h added to Ceftriaxone + Vancomycin (alternative: Meropenem + Vancomycin)`
  - **Cautions:** *Listeria monocytogenes* is intrinsically resistant to third-generation cephalosporins (WHO 2025).
  - **Citations:** [ICMR ABM 2022 — Empiric therapy table](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html), [WHO Meningitis 2025 §B.2.2](https://www.ncbi.nlm.nih.gov/books/NBK614844/)
- **Neonates (<1 month)**
  - **Regimen:** `Meropenem IV (add vancomycin if MRSA risk) — alternative: Cefotaxime + Gentamicin (ICMR 2022)`
  - **Cautions:** No corticosteroids in neonates; repeat LP to document CSF sterilisation (ICMR 2022).
  - **Citations:** [ICMR ABM 2022 — Empiric therapy table](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html)
- **Meningococcal close-contact prophylaxis**
  - **Regimen:** `Single-dose parenteral ceftriaxone OR single-dose oral ciprofloxacin (rifampicin if neither usable) as soon as possible (≤14 days) (WHO 2025)`
  - **Citations:** [WHO Meningitis 2025 §B.2.5](https://www.ncbi.nlm.nih.gov/books/NBK614844/)

### Avoid
- **Delaying antibiotics for LP or CT scan:** Delayed in-hospital antimicrobial therapy (>3 h) markedly increases mortality; draw blood cultures and give IV antibiotics immediately if LP/CT is delayed ([WHO Meningitis 2025 §A.4, §B.2.1](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).
- **Routine oral glycerol or routine fluid restriction:** WHO 2025 recommends against routine oral glycerol and against routine fluid restriction in acute bacterial meningitis ([WHO Meningitis 2025 §B.4.1, §B.5](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).
- **Refrigerating CSF samples:** Fastidious meningeal pathogens (*N. meningitidis*, *H. influenzae*) die at refrigerator temperatures; keep CSF at room temperature and process within 30–60 min ([ICMR ABM 2022 — Investigations](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html)).

### Escalate / Red Flags
- ICU admission for shock/purpura fulminans, GCS <10, respiratory compromise, status epilepticus, or raised ICP/impending herniation ([WHO Meningitis 2025](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).
- No clinical improvement at 48 h — repeat LP and obtain neuroimaging to rule out subdural empyema, brain abscess, ventriculitis, or resistant organism ([ICMR ABM 2022](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html)).
- Formal audiological screening before discharge (or within 4 weeks) for all survivors ([WHO Meningitis 2025](https://www.ncbi.nlm.nih.gov/books/NBK614844/)).

---

## 5. Brucellosis

- **Condition ID:** `inf-brucellosis`
- **Population:** `Population.Infections`
- **Summary:** Zoonotic febrile illness (unpasteurised dairy/livestock contact): screen with Rose Bengal/SAT, confirm by blood culture; treat 6 weeks with dual therapy.
- **Search Keywords:** `brucellosis`, `Brucella`, `Brucella melitensis`, `Brucella abortus`, `undulant fever`, `Malta fever`, `Rose Bengal`, `SAT`, `PUO`, `FUO`, `unpasteurised milk`, `sacroiliitis`, `doxycycline`

### Clinical Photo


- **Resource name (`resName`):** `inf_brucella_spp`
- **Caption:** Gram stain of *Brucella* spp. showing tiny, poorly staining Gram-negative coccobacilli ('fine sand' appearance).
- **Credit:** CDC Public Health Image Library (PHIL #1901)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Brucella_spp.JPG](https://commons.wikimedia.org/wiki/File:Brucella_spp.JPG)

### First Steps
1. Take occupational and dietary history (unpasteurised milk/dairy, cattle/goat/sheep contact, veterinary or abattoir work — common in North Indian dairy belts) ([NCDC 2016](https://ncdc.mohfw.gov.in/uploads/pdf/BRUCELLOSIS_final_LRP.pdf); [WHO 2020](https://www.who.int/news-room/fact-sheets/detail/brucellosis)).
2. Alert the microbiology lab when *Brucella* is suspected so blood culture bottles are handled under biosafety precautions (high laboratory-acquired infection risk).
3. Always use combination antibiotic therapy for 6 weeks (45 days) — monotherapy causes high relapse rates ([WHO 2020](https://www.who.int/news-room/fact-sheets/detail/brucellosis)).

### Screening & Investigation of Choice (IOC)
- Rapid screening test: Rose Bengal Plate Test (RBPT) — high sensitivity point-of-care agglutination screen ([NCDC 2016](https://ncdc.mohfw.gov.in/uploads/pdf/BRUCELLOSIS_final_LRP.pdf); [WHO 2020](https://www.who.int/news-room/fact-sheets/detail/brucellosis)).
- Confirmatory gold standard (IOC): Blood culture (automated systems) or bone marrow culture — highest yield during the acute febrile phase ([NCDC 2016](https://ncdc.mohfw.gov.in/uploads/pdf/BRUCELLOSIS_final_LRP.pdf); [WHO 2020](https://www.who.int/news-room/fact-sheets/detail/brucellosis)).
- Serological confirmation (when culture negative/unavailable): Standard Tube Agglutination Test (SAT) titre ≥1:160 in a compatible clinical setting, or ≥4-fold rise in paired sera ([NCDC 2016](https://ncdc.mohfw.gov.in/uploads/pdf/BRUCELLOSIS_final_LRP.pdf)).

### Other Investigations & Key Findings
- Routine labs: CBC (normal or leukopenia/thrombocytopenia), mildly elevated transaminases, elevated ESR/CRP ([NCDC 2016](https://ncdc.mohfw.gov.in/uploads/pdf/BRUCELLOSIS_final_LRP.pdf)).
- Focal complications to screen for: sacroiliitis/spondylodiscitis (MRI spine if back pain), epididymo-orchitis, and *Brucella* endocarditis (echocardiography if new murmur or persistent bacteraemia) ([NCDC 2016](https://ncdc.mohfw.gov.in/uploads/pdf/BRUCELLOSIS_final_LRP.pdf); [WHO 2020](https://www.who.int/news-room/fact-sheets/detail/brucellosis)).

### Treatment Tiers

#### Tier 1: Drug of choice — uncomplicated adult brucellosis (WHO)
> *Note:* Standard duration is 45 days (6 weeks) to prevent relapse (WHO).

- **Doxycycline + Streptomycin (first-line, lowest relapse)**
  - **Regimen:** `Doxycycline 100 mg PO twice daily for 45 days PLUS Streptomycin 1 g IM once daily for 15 days`
  - **Cautions:** WHO notes Gentamicin 5 mg/kg IV/IM once daily for 7–10 days may substitute for streptomycin. Monitor renal function and auditory/vestibular toxicity with aminoglycosides.
  - **Citations:** [WHO Brucellosis 2020 — Treatment and care](https://www.who.int/news-room/fact-sheets/detail/brucellosis)
- **Doxycycline + Rifampicin (all-oral alternative)**
  - **Regimen:** `Doxycycline 100 mg PO twice daily for 45 days PLUS Rifampicin 15 mg/kg/day (600–900 mg) PO once daily for 45 days`
  - **Weight-based calculator (`WeightDose`):** `15.0 mg/kg rifampicin per day PO × 45 days (600–900 mg)`
  - **Cautions:** In India (high TB burden), exclude active tuberculosis before prescribing rifampicin monotherapy/combination. Rifampicin induces hepatic enzymes and lowers doxycycline levels.
  - **Citations:** [WHO Brucellosis 2020 — Treatment and care](https://www.who.int/news-room/fact-sheets/detail/brucellosis)

#### Tier 2: Special groups — children <8 years & pregnancy (WHO)
- **Co-trimoxazole (TMP-SMX) + Rifampicin or Aminoglycoside (children <8 years)**
  - **Regimen:** `Co-trimoxazole combined with rifampicin (or gentamicin/streptomycin) for 6 weeks; avoid doxycycline <8 years (WHO)`
  - **Cautions:** Optimal regimen in pregnancy and neonates requires specialist ID/obstetric input (typically rifampicin ± TMP-SMX avoiding near-term sulphonamides).
  - **Citations:** [WHO Brucellosis 2020 — Treatment and care](https://www.who.int/news-room/fact-sheets/detail/brucellosis)

### Avoid
- **Single-agent antibiotic therapy:** Monotherapy (e.g., doxycycline alone) carries unacceptably high relapse rates; always combine two active agents for 45 days ([WHO Brucellosis 2020](https://www.who.int/news-room/fact-sheets/detail/brucellosis)).
- **Doxycycline in pregnancy or children <8 years:** Avoid tetracyclines in pregnancy and young children due to tooth discolouration and bone effects ([WHO Brucellosis 2020](https://www.who.int/news-room/fact-sheets/detail/brucellosis)).

### Escalate / Red Flags
- Suspected *Brucella* endocarditis (new heart murmur, heart failure, persistent bacteraemia) — urgent cardiology/ID referral; requires prolonged triple therapy and often valve surgery.
- Neurobrucellosis (meningitis, cranial nerve palsy) or vertebral spondylodiscitis with neurological deficit — specialist hospital admission for triple-agent regimens and imaging.

---

## 6. Chickenpox (varicella)

- **Condition ID:** `inf-chickenpox`
- **Population:** `Population.Infections`
- **Summary:** Pruritic polymorphic vesicular rash in crops ('dewdrop on a rose petal'): clinical diagnosis; oral acyclovir within 24 h for adults, >12 yr, pregnant, or high-risk patients.
- **Search Keywords:** `chickenpox`, `varicella`, `VZV`, `varicella-zoster`, `vesicular rash`, `dewdrop on a rose petal`, `acyclovir`, `valacyclovir`, `Tzanck`, `VZIG`, `Reye syndrome`

### Clinical Photo


- **Resource name (`resName`):** `inf_chickenpox_rash`
- **Caption:** Chickenpox (day 5 of rash) showing classic polymorphic lesions — papules, vesicles, pustules, and crusted scabs simultaneously.
- **Credit:** CDC Public Health Image Library (PHIL)
- **Licence:** Public Domain (US Government)
- **Source Page:** [Wikimedia Commons — File:Chickenpox.jpg](https://commons.wikimedia.org/wiki/File:Chickenpox.jpg)

### First Steps
1. Isolate the patient immediately (airborne + contact precautions) from pregnant women, neonates, and immunocompromised people until all lesions have crusted (or 24 h with no new lesions in vaccinated cases) ([CDC 2024](https://www.cdc.gov/chickenpox/hcp/clinical-overview/index.html); [ICMR STW 2022](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf)).
2. Contagious period: 1–2 days before rash onset until all vesicles crust over; incubation period 14–16 days (range 10–21 days) ([CDC 2024](https://www.cdc.gov/chickenpox/hcp/clinical-overview/index.html)).
3. Supportive care for uncomplicated healthy children ≤12 yr: paracetamol for fever, oral antihistamines for itch, nail trimming/skin hygiene to prevent secondary bacterial infection ([ICMR STW 2022](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf); [CDC 2024](https://www.cdc.gov/chickenpox/hcp/clinical-overview/index.html)).

### Screening & Investigation of Choice (IOC)
- Clinical diagnosis is standard in typical cases: fever + pruritic centripetal rash with macules, papules, vesicles, and crusts present simultaneously ([CDC 2024 — Testing and diagnosis](https://www.cdc.gov/chickenpox/hcp/clinical-overview/index.html); [ICMR STW 2022 — Diagnosis](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf)).
- Laboratory IOC (atypical, breakthrough, or severe cases): VZV PCR from vesicular fluid / skin lesion swab — most sensitive and rapid confirmatory test ([CDC 2024](https://www.cdc.gov/chickenpox/hcp/clinical-overview/index.html); [ICMR STW 2022](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf)).
- Bedside alternative (where PCR unavailable): Tzanck smear from the base of a fresh vesicle showing multinucleated giant cells and acantholysis (cannot distinguish VZV from HSV) ([ICMR STW 2022](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf)).

### Who Needs Antiviral Therapy & Pregnancy Risk
- High-risk groups needing antivirals: adults, children >12 years, infants, pregnant women, immunosuppressed patients, chronic skin/lung disease, or long-term salicylate/steroid users ([ICMR STW 2022](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf); [CDC Severe Varicella 2024](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html)).
- Pregnancy risks: infection in first 20 weeks carries a 0.4–2.0% risk of congenital varicella syndrome; maternal rash from 5 days before to 2 days after delivery puts the neonate at high risk of severe neonatal varicella ([CDC Severe Varicella 2024](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html); [RCOG GTG 13](https://www.rcog.org.uk/media/y3ajgkda/gtg13.pdf)).
- Symptom-directed tests if complications suspected: chest X-ray (varicella pneumonia — commonest adult complication), transaminases, RFT, or brain imaging ([ICMR STW 2022](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf)).

### Treatment Tiers

#### Tier 1: Drug of choice — oral antiviral (start within 24 h of rash for maximum benefit)
> *Note:* Not routinely required for otherwise healthy immunocompetent children ≤12 yr with uncomplicated varicella (CDC 2024).

- **Acyclovir PO (adults & children >40 kg)**
  - **Regimen:** `800 mg PO five times daily for 5–7 days (ICMR STW 2022; RCOG: 7 days in pregnancy)`
  - **Cautions:** Best started within 24 h of rash onset; adjust dose in renal impairment and ensure adequate hydration. Pregnancy: RCOG GTG 13 recommends oral acyclovir 800 mg 5×/day × 7 days if presenting within 24 h at ≥20 weeks (consider <20 weeks).
  - **Citations:** [ICMR STW 2022 — Specific treatment](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf), [RCOG GTG 13](https://www.rcog.org.uk/media/y3ajgkda/gtg13.pdf)
- **Acyclovir PO (children <40 kg, if high-risk)**
  - **Regimen:** `20 mg/kg/dose PO four times daily for 7 days`
  - **Max Dose:** `800 mg per dose`
  - **Weight-based calculator (`WeightDose`):** `20.0 mg/kg per dose PO four times daily × 7 days`
  - **Citations:** [ICMR STW 2022 — Specific treatment](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf)
- **Valacyclovir PO (adult alternative)**
  - **Regimen:** `1 g PO three times daily for 5–7 days`
  - **Citations:** [ICMR STW 2022 — Specific treatment](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf), [CDC Severe Varicella 2024](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html)

#### Tier 2: Severe / complicated disease & post-exposure prophylaxis
- **Acyclovir IV (severe, disseminated, immunocompromised, or pregnant with pneumonia)**
  - **Regimen:** `10 mg/kg IV every 8 hours (infuse over 1 h with hydration)`
  - **Weight-based calculator (`WeightDose`):** `10.0 mg/kg per dose IV every 8 h`
  - **Cautions:** Indicated for varicella pneumonia, encephalitis, hepatitis, haemorrhagic varicella, neonatal varicella, and immunocompromised hosts (CDC 2024; ICMR 2022; RCOG).
  - **Citations:** [CDC Severe Varicella 2024 — Acyclovir treatment](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html), [ICMR STW 2022](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf), [RCOG GTG 13](https://www.rcog.org.uk/media/y3ajgkda/gtg13.pdf)
- **Varicella-zoster immune globulin (VZIG) — post-exposure only**
  - **Regimen:** `Give as soon as possible (within 10 days of exposure) to susceptible high-risk contacts (pregnant women, immunocompromised, exposed neonates/preterm infants) (CDC 2024)`
  - **Cautions:** No therapeutic benefit once chickenpox rash has already appeared (RCOG GTG 13).
  - **Citations:** [CDC Severe Varicella 2024 — VZIG](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html), [RCOG GTG 13](https://www.rcog.org.uk/media/y3ajgkda/gtg13.pdf)

### Avoid
- **Aspirin (salicylates) in children/adolescents with varicella:** Strictly avoid due to the risk of Reye syndrome ([ICMR STW 2022](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf)).
- **Varicella live vaccine in pregnancy:** Live attenuated varicella vaccine is contraindicated during pregnancy ([CDC Severe Varicella 2024 — Pregnant women](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html)).

### Escalate / Red Flags
- Breathlessness, tachypnoea, cough, or hypoxia (varicella pneumonia — especially in adults, smokers, and pregnant women) — immediate hospital admission for IV acyclovir ([CDC 2024](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html); [RCOG GTG 13](https://www.rcog.org.uk/media/y3ajgkda/gtg13.pdf)).
- Altered sensorium, ataxia, seizures (encephalitis/cerebellitis), haemorrhagic vesicles, new lesions continuing >7 days, or secondary cellulitis/sepsis ([CDC 2024](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html); [ICMR STW 2022](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf)).
- Maternal rash 5 days before to 2 days after delivery — urgent neonatal evaluation for VZIG and close monitoring ([CDC 2024](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html)).

---

## 7. Chikungunya

- **Condition ID:** `inf-chikungunya`
- **Population:** `Population.Infections`
- **Summary:** *Aedes*-borne acute fever with debilitating symmetric polyarthralgia: RT-PCR days 0–7, IgM MAC-ELISA after day 7; supportive care with paracetamol — avoid NSAIDs until dengue is excluded.
- **Search Keywords:** `chikungunya`, `CHIKV`, `Aedes`, `polyarthralgia`, `joint pain`, `arbovirus`, `IgM ELISA`, `RT-PCR`, `dengue differential`, `post-chikungunya arthritis`

### Clinical Photo


- **Resource name (`resName`):** `inf_chikungunya_rash`
- **Caption:** Maculopapular erythematous rash in acute chikungunya infection.
- **Credit:** EEIM (Wikimedia Commons)
- **Licence:** CC BY-SA 4.0
- **Source Page:** [Wikimedia Commons — File:Chikunguña_2_en_Col.jpg](https://commons.wikimedia.org/wiki/File:Chikungu%C3%B1a_2_en_Col.jpg)

### First Steps
1. Always screen for dengue co-infection or misdiagnosis (same *Aedes* mosquito vector and season in India) before prescribing any NSAID or aspirin ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf); [WHO 2025](https://www.who.int/news-room/fact-sheets/detail/chikungunya)).
2. Record exact day of fever onset on the lab request form so the laboratory selects RT-PCR (early) vs IgM ELISA (after day 7) ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf)).
3. Advise rest, adequate oral hydration, and mosquito-net protection during the first week of fever to prevent onward household transmission.

### Screening & Investigation of Choice (IOC)
- Days 0–7 of illness (acute viraemic phase) — IOC: Real-time RT-PCR on whole blood/serum (detects CHIKV RNA up to ≤8 days; results in 1–2 days) ([NCVBDC 2023 §3.1.3, §3.2](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf)).
- After Day 7 of illness — IOC: Chikungunya IgM MAC-ELISA (free through NCVBDC Sentinel Surveillance Hospitals / NIV Pune kits; a negative IgM before day 7 does not exclude chikungunya) ([NCVBDC 2023 §3.2–3.3](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf)).
- In neurological presentations (meningoencephalitis), CSF may also be tested by RT-PCR/IgM ([NCVBDC 2023 §3.1](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf)).

### Other Investigations & Clinical Differentiation from Dengue
- Simultaneous dengue testing (NS1 ≤5 days, IgM >5 days) plus CBC (haematocrit and platelets) is essential in Indian monsoon/post-monsoon acute febrile illness ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf)).
- Clinical clues favouring chikungunya over dengue: abrupt high fever with severe symmetric peripheral polyarthralgia/joint swelling (wrists, ankles, small joints); shock, plasma leakage, and severe thrombocytopenia are uncommon in isolated chikungunya ([WHO 2025](https://www.who.int/news-room/fact-sheets/detail/chikungunya)).

### Treatment Tiers

#### Tier 1: Drug of choice — acute phase (supportive; no specific antiviral)
> *Note:* There is no specific antiviral drug for chikungunya (WHO 2025; NCVBDC 2023).

- **Paracetamol (acetaminophen)**
  - **Regimen:** `Adults: 500–1000 mg PO every 6–8 h (max 4 g/day); children: 10–15 mg/kg/dose every 6 h`
  - **Max Dose:** `4 g/day in adults`
  - **Cautions:** First-line antipyretic and analgesic during the acute febrile phase while dengue has not been excluded (WHO 2025; NCVBDC 2023).
  - **Citations:** [WHO Chikungunya 2025 — Treatment](https://www.who.int/news-room/fact-sheets/detail/chikungunya), [NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf)

#### Tier 2: Subacute / persistent arthralgia (only AFTER dengue is ruled out)
- **NSAIDs (e.g. naproxen, ibuprofen, or diclofenac)**
  - **Regimen:** `Standard anti-inflammatory dosing once dengue is definitively excluded and platelets/renal function are normal`
  - **Cautions:** Never start NSAIDs or aspirin during the initial febrile phase until dengue is excluded (bleeding and AKI risk). Patients with chronic inflammatory polyarthritis persisting >3 months should be referred to rheumatology (may require DMARDs such as methotrexate).
  - **Citations:** [NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf), [WHO Chikungunya 2025](https://www.who.int/news-room/fact-sheets/detail/chikungunya)

### Avoid
- **Aspirin and NSAIDs before dengue is excluded:** Dengue and chikungunya co-circulate in India; early NSAIDs or aspirin increase bleeding, gastritis, and acute kidney injury risk if the patient actually has dengue ([NCVBDC 2023](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf); [WHO 2025](https://www.who.int/news-room/fact-sheets/detail/chikungunya)).
- **Empirical antibiotics or routine systemic steroids in acute uncomplicated illness:** Chikungunya is viral and self-limiting in the acute phase; avoid unnecessary antibiotics and avoid routine steroids in acute viraemia ([WHO 2025](https://www.who.int/news-room/fact-sheets/detail/chikungunya)).

### Escalate / Red Flags
- Any dengue warning sign (persistent vomiting, abdominal pain, mucosal bleeding, rising haematocrit, falling platelets, lethargy) — manage immediately as dengue.
- Atypical/severe chikungunya: neonates (perinatal transmission), elderly with comorbidities, encephalopathy/seizures, myocarditis, uveitis/retinitis, or acute kidney injury — hospital admission ([WHO 2025](https://www.who.int/news-room/fact-sheets/detail/chikungunya)).

---

## 8. Community-acquired pneumonia (CAP)

- **Condition ID:** `inf-cap`
- **Population:** `Population.Infections`
- **Summary:** Lower respiratory infection acquired outside hospital: confirm with CXR, stratify severity (CURB-65), and start beta-lactam-based empiric therapy (5–7 days).
- **Search Keywords:** `CAP`, `pneumonia`, `community-acquired pneumonia`, `lobar pneumonia`, `CURB-65`, `Streptococcus pneumoniae`, `amoxicillin`, `co-amoxiclav`, `ceftriaxone`, `azithromycin`, `doxycycline`

### Clinical Photo


- **Resource name (`resName`):** `inf_lobar_pneumonia_cxr`
- **Caption:** Posteroanterior chest radiograph demonstrating right middle lobe consolidation typical of lobar pneumonia.
- **Credit:** Mikael Häggström, M.D. (Wikimedia Commons, with patient consent)
- **Licence:** CC0 1.0 (Public Domain)
- **Source Page:** [Wikimedia Commons — File:X-ray_of_lobar_pneumonia.jpg](https://commons.wikimedia.org/wiki/File:X-ray_of_lobar_pneumonia.jpg)

### First Steps
1. Assess oxygenation (SpO2), haemodynamics, and severity immediately using CURB-65 (or PSI) plus clinical judgement to decide outpatient vs ward vs ICU care ([IDSA/ATS 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/); [ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html)).
2. Give supplemental oxygen for hypoxaemia and start empiric antibiotics promptly (within 1 hour if septic shock; do not delay for cultures) ([IDSA/ATS 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/)).
3. In India, consider *Mycobacterium tuberculosis* in subacute/protracted pneumonia or upper-lobe/cavitary disease — send sputum AFB/CBNAAT before using any fluoroquinolone ([ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html)).

### Screening & Investigation of Choice (IOC)
- IOC (confirming pneumonia): Chest X-ray (PA ± lateral) showing a new parenchymal infiltrate/consolidation (not routinely required for mild paediatric outpatients per ICMR 2022, but standard for adult diagnosis) ([ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html); [IDSA/ATS 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/)).
- Severity screening: CURB-65 score (Confusion, Urea, RR ≥30, BP <90/60, age ≥65) — 0–1 outpatient, 2 consider ward admission, ≥3 severe CAP / ICU evaluation ([IDSA/ATS 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/)).
- Microbiology (inpatients/severe CAP): 2 sets of blood cultures and sputum Gram stain + culture BEFORE antibiotics; avoid routine sputum/blood cultures in healthy outpatients ([IDSA/ATS 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/); [ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html)).

### Other Investigations & Indian Microbiology Notes
- Inpatients: CBC, serum urea/creatinine (for CURB-65), electrolytes, LFT, blood glucose, and pulse oximetry / ABG if hypoxic.
- Indian AMR warning ([ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html)): beta-lactams are preferred over macrolide monotherapy because of high *S. pneumoniae* macrolide resistance in India.
- Treatment duration: 5–7 days for uncomplicated CAP — may stop at day 5 if afebrile ≥48 h, haemodynamically stable, and no complications (lung abscess, empyema, *S. aureus*, *Legionella*, or *Pseudomonas*) ([ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html); [IDSA/ATS 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/)).

### Treatment Tiers

#### Tier 1: Drug of choice — adult outpatient CAP (CURB-65 0–1)
> *Note:* ICMR 2022 prefers a beta-lactam over macrolide monotherapy in India due to high pneumococcal macrolide resistance.

- **Amoxicillin (healthy outpatient, no comorbidities)**
  - **Regimen:** `1 g PO three times daily for 5 days (IDSA/ATS 2019; ICMR: 500 mg–1 g TID)`
  - **Citations:** [IDSA/ATS 2019 — Table 3](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/), [ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html)
- **Doxycycline (alternative for healthy outpatient)**
  - **Regimen:** `100 mg PO twice daily for 5 days`
  - **Cautions:** Avoid in pregnancy and children <8 years.
  - **Citations:** [IDSA/ATS 2019 — Table 3](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/)
- **Amoxicillin–clavulanate + Azithromycin or Doxycycline (outpatient with comorbidities)**
  - **Regimen:** `Co-amoxiclav 625 mg PO TID (or 875/125 mg BD) PLUS Azithromycin 500 mg PO day 1 then 250 mg OD (or 500 mg OD × 3–5 d) OR Doxycycline 100 mg BD for 5–7 days`
  - **Cautions:** For chronic heart/lung/liver/renal disease, diabetes, alcoholism, or prior antibiotics within 90 days (IDSA/ATS 2019; ICMR 2022).
  - **Citations:** [IDSA/ATS 2019 — Table 3](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/), [ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html)

#### Tier 2: Inpatient (ward & ICU) CAP
> *Note:* Switch IV to oral once afebrile, haemodynamically stable, and able to take oral therapy (usually 48–72 h) (IDSA/ATS 2019).

- **Ceftriaxone IV + Azithromycin (or Doxycycline)**
  - **Regimen:** `Ceftriaxone 1–2 g IV once daily (ICMR/IDSA) PLUS Azithromycin 500 mg IV/PO once daily (or Doxycycline 100 mg IV/PO BD) for 5–7 days`
  - **Cautions:** Alternative beta-lactams: Cefotaxime 1–2 g IV q8h or Co-amoxiclav 1.2 g IV q8h (ICMR 2022).
  - **Citations:** [ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html), [IDSA/ATS 2019 — Table 4](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/)
- **Severe ICU CAP / *Pseudomonas* or MRSA risk**
  - **Regimen:** `Standard ICU: Ceftriaxone 2 g IV daily + Azithromycin 500 mg IV daily. Add anti-pseudomonal beta-lactam (piperacillin–tazobactam 4.5 g IV q6h or meropenem 1 g IV q8h) ONLY if Pseudomonas risk (bronchiectasis, prior isolation/IV antibiotics); add vancomycin/linezolid ONLY if MRSA risk.`
  - **Cautions:** Avoid empiric fluoroquinolones in India if pulmonary TB has not been excluded (masks TB and induces resistance) (ICMR 2022).
  - **Citations:** [IDSA/ATS 2019 — Table 4](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/), [ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html)

### Avoid
- **Macrolide monotherapy in India:** High prevalence of macrolide-resistant *S. pneumoniae* in India makes macrolide monotherapy unreliable; use a beta-lactam backbone ([ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html); [IDSA/ATS 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/) threshold <25%).
- **Empiric fluoroquinolones without excluding TB:** Fluoroquinolones partially treat *Mycobacterium tuberculosis*, delaying TB diagnosis and promoting MDR-TB; send sputum AFB/CBNAAT first if TB is possible ([ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html)).

### Escalate / Red Flags
- CURB-65 ≥3, SpO2 <92% on room air, septic shock, need for vasopressors or mechanical ventilation, or multilobar/necrotising infiltrates — immediate ICU admission ([IDSA/ATS 2019](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/)).
- No clinical improvement at 48–72 h — reassess for parapneumonic effusion/empyema (ultrasound/thoracentesis), lung abscess, TB, resistant organism, or non-infectious mimic ([ICMR CAP 2022](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html)).

---

## Primary Sources Reference Table (All 21 Verified Sources)

| Source ID | Authority | Document | Title | Year | URL |
|---|---|---|---|---|---|
| `inf-ac-icmr-diarrhoea-2022` | `ICMR` | Treatment Guidelines for Antimicrobial Use | Chapter 5: Acute Diarrhea — Rehydration and Antibiotic Treatment | 2022 | [amrtg.icmr.org.in](https://amrtg.icmr.org.in/archive/2022/chapter5-acute-diarrhea-treatment.html) |
| `inf-ac-icmr-abm-2022` | `ICMR` | Treatment Guidelines for Antimicrobial Use | Chapter 8: Acute Bacterial Meningitis — Investigations and Empiric Therapy | 2022 | [amrtg.icmr.org.in](https://amrtg.icmr.org.in/archive/2022/chapter8-abm-investigations.html) |
| `inf-ac-ncdc-brucellosis-2016` | `NCDC` | Zoonotic Disease Guidelines | Brucellosis — Diagnosis, Surveillance and Control in India | 2016 | [ncdc.mohfw.gov.in](https://ncdc.mohfw.gov.in/uploads/pdf/BRUCELLOSIS_final_LRP.pdf) |
| `inf-ac-icmr-stw-varicella-2022` | `ICMR` | Standard Treatment Workflow | Varicella & Herpes Zoster (ICD-10 B01–B02) | 2022 | [icmr.gov.in](https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967364_varicella__herpes_zoster.pdf) |
| `inf-ac-ncvbdc-chikungunya-2023` | `NCVBDC` | National Guidelines | National Guidelines for Clinical Management of Chikungunya Fever | 2023 | [ncvbdc.mohfw.gov.in](https://ncvbdc.mohfw.gov.in/Doc/National-Guidelines-for-Clinical-Management-of-Chikungunya-Fever-2023.pdf) |
| `inf-ac-icmr-cap-2022` | `ICMR` | Treatment Guidelines for Antimicrobial Use | Chapter 4 / Section 12.5: Community Acquired Pneumonia (CAP) | 2022 | [amrtg.icmr.org.in](https://amrtg.icmr.org.in/archive/2022/chapter4-cap-treatment-guideline.html) |
| `inf-ac-who-pocket-2013` | `WHO` | Pocket Book, 2nd edition | Pocket Book of Hospital Care for Children: Triage and Emergency Conditions | 2013 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK154450/) |
| `inf-ac-gtfcc-cholera-2025` | `WHO` | GTFCC Interim Technical Note | Use of Antibiotics for the Treatment and Control of Cholera | 2025 | [gtfcc.org](https://www.gtfcc.org/wp-content/uploads/2025/02/gtfcc-technical-note-on-use-of-antibiotics-for-the-treatment-of-cholera.pdf) |
| `inf-ac-who-sth-2023` | `WHO` | Fact Sheet | Soil-transmitted helminth infections | 2023 | [who.int](https://www.who.int/news-room/fact-sheets/detail/soil-transmitted-helminth-infections) |
| `inf-ac-who-meningitis-2025` | `WHO` | WHO Guidelines | WHO Guidelines on Meningitis Diagnosis, Treatment and Care | 2025 | [ncbi.nlm.nih.gov](https://www.ncbi.nlm.nih.gov/books/NBK614844/) |
| `inf-ac-who-brucellosis-2020` | `WHO` | Fact Sheet | Brucellosis — Diagnosis, Treatment and Care | 2020 | [who.int](https://www.who.int/news-room/fact-sheets/detail/brucellosis) |
| `inf-ac-who-chikungunya-2025` | `WHO` | Fact Sheet | Chikungunya | 2025 | [who.int](https://www.who.int/news-room/fact-sheets/detail/chikungunya) |
| `inf-ac-cdc-dpdx-amebiasis` | `CDC` | DPDx Laboratory Identification | Amebiasis (*Entamoeba histolytica*) | 2019 | [cdc.gov](https://www.cdc.gov/dpdx/amebiasis/index.html) |
| `inf-ac-bmj-fg-amoebiasis-2024` | `Paper` | Frontline Gastroenterol 2025;16(1):37–45 | Recent advances in the diagnosis and management of amoebiasis | 2024 | [fg.bmj.com](https://fg.bmj.com/content/16/1/37) |
| `inf-ac-fda-paromomycin` | `FDA` | Drug label (DailyMed) | Paromomycin Sulfate Capsules — Dosage and Administration | 2017 | [dailymed.nlm.nih.gov](https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=333d55f0-ab49-459e-8d32-1590b2623ef3) |
| `inf-ac-cochrane-amoebiasis-2019` | `Paper` | Cochrane Database Syst Rev (PMC6326239) | Antiamoebic drugs for treating amoebic colitis | 2019 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC6326239/) |
| `inf-ac-cdc-dpdx-ascariasis` | `CDC` | DPDx Laboratory Identification | Ascariasis (*Ascaris lumbricoides*) | 2019 | [cdc.gov](https://www.cdc.gov/dpdx/ascariasis/index.html) |
| `inf-ac-cdc-varicella-2024` | `CDC` | Clinical Overview | Clinical Overview of Chickenpox (Varicella) | 2024 | [cdc.gov](https://www.cdc.gov/chickenpox/hcp/clinical-overview/index.html) |
| `inf-ac-cdc-varicella-risk-2024` | `CDC` | Clinical Guidance | Clinical Guidance for People at Risk for Severe Varicella | 2024 | [cdc.gov](https://www.cdc.gov/chickenpox/hcp/clinical-guidance/index.html) |
| `inf-ac-rcog-gtg13-varicella` | `RCOG` | Green-top Guideline No. 13 | Chickenpox in Pregnancy | 2015 | [rcog.org.uk](https://www.rcog.org.uk/media/y3ajgkda/gtg13.pdf) |
| `inf-ac-idsa-ats-cap-2019` | `IDSA` | ATS/IDSA Clinical Practice Guideline | Diagnosis and Treatment of Adults with Community-acquired Pneumonia | 2019 | [pmc.ncbi.nlm.nih.gov](https://pmc.ncbi.nlm.nih.gov/articles/PMC6812437/) |

---

## Claude Code Integration Spec (Drop-In Instructions)

When this document is placed in the `Medicalaid` repository and approved by the clinician, implement the following changes:

1. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/Model.kt`**
   - Add `Population.Infections("Infectious diseases")` to `enum class Population`.
   - Add `data class ConditionImage(val resName: String, val caption: String, val credit: String, val licence: String, val sourceUrl: String)`.
   - Add `val image: ConditionImage? = null` to `data class Condition`.
2. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/InfectionsAC.kt`**
   - Create `infectionsACSources: List<Source>` (all 21 entries from the Reference Table above) and `infectionsACConditions: List<Condition>` (all 8 diseases above, exact IDs, keywords, `image`, `firstSteps`, `info`, `tiers`, `WeightDose`, `maxDose`, `avoid`, `escalate`, and `citations`).
3. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/Conditions.kt`**
   - Add `releasedInfections` and `inReviewInfections` gating groups (or place `Group(infectionsACConditions, infectionsACSources)` directly in `releasedInfections` once the clinician approves release).
   - Expose `val infections: List<Condition>`, include `infections` in `Conditions.all` (so global search indexes all infectious diseases automatically), and map `Population.Infections -> infections` in `Conditions.of(population)`.
4. **`composeApp/src/commonMain/kotlin/com/medicalaid/app/content/Sources.kt`**
   - Register `Conditions.infectionSources` in `Sources.all`.
5. **`composeApp/src/commonTest/kotlin/com/medicalaid/app/content/ContentIntegrityTest.kt`**
   - Validate both released and `inReview` conditions/sources: every citation resolves, zero unused sources, zero duplicate IDs, all URLs start with `https://`, and every `ConditionImage` has non-blank `resName`, `caption`, `credit`, `licence`, and `https://` `sourceUrl`.
6. **Offline Image Bundle (`composeApp/src/commonMain/composeResources/drawable/`)**
   - Download the 7 verified open-licence images listed below at 800 px width (compress to `.webp` with `cwebp -q 80` if available, or save as `.jpg`), then wire `ConditionScreen.kt` to display `condition.image` as a collapsible/tappable reference card with caption, credit, and licence:
     - `inf_vibrio_cholerae` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Vibrio_cholerae_gram_stain_CDC.jpg?width=800`
     - `inf_entamoeba_histolytica` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Entamoeba_histolytica_01.jpg?width=800`
     - `inf_ascaris_egg` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Fertilized_egg_of_Ascaris_lumbricoides_PHIL_410_lores.jpg?width=800`
     - `inf_brucella_spp` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Brucella_spp.JPG?width=800`
     - `inf_chickenpox_rash` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Chickenpox.jpg?width=800`
     - `inf_chikungunya_rash` ← `https://commons.wikimedia.org/wiki/Special:FilePath/Chikungu%C3%B1a_2_en_Col.jpg?width=800`
     - `inf_lobar_pneumonia_cxr` ← `https://commons.wikimedia.org/wiki/Special:FilePath/X-ray_of_lobar_pneumonia.jpg?width=800`
7. **Verification:** Run `./gradlew :composeApp:allTests` to confirm `ContentIntegrityTest` passes.
