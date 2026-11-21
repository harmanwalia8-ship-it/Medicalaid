# Roadmap — queued work

**Do not start this queue until all current work is finished**, meaning the poisoning, medical-emergency,
obstetric and trauma protocol workflows are done and released (0.4.0 = poisoning + calculators;
the emergency, obstetric and trauma protocols follow in 0.5.0).
After that, run **one workflow at a time** so usage is spread out instead of spent in minutes.
Each item uses the same process as before. One writer works from primary sources only, India first.
Two independent reviewers then check it: one for every number, one for safety and attribution.
A fixer re-checks each finding before editing.

## 1. Child emergencies (Protocols, new "Paediatric" category)

Doses are weight-based with maxima (StepDose). Sources come India first: IAP (Indian Academy of Pediatrics),
MoHFW (IMNCI, facility-based newborn care, SAM guidelines), and the ICMR STWs. Then the WHO Pocket Book of Hospital
Care for Children, ERC/AHA 2025 paediatric life support, NICE and ISPAD.

- Paediatric cardiac arrest and the seriously ill child (ABCDE, weight estimation, drug doses)
- Neonatal resuscitation (IAP NRP / NSSK)
- Paediatric sepsis and septic shock (fluids by weight, antibiotics)
- Status epilepticus and febrile seizures in children
- DKA in children (ISPAD 2022: fluid deficit, insulin, cerebral oedema)
- Acute asthma, bronchiolitis and croup
- Dehydration and diarrhoea (WHO plans A/B/C, ORS, zinc)
- Severe acute malnutrition with complications (F-75/F-100, hypoglycaemia, hypothermia)
- Anaphylaxis, dengue and severe malaria in children: child-specific doses and fluids

## 2. Children's doses in the Drugs section

Add paediatric dosing to drug profiles: an age band plus a weight-based dose, a maximum and a citation.
Sources are FDA label paediatric sections, the WHO Model Formulary for Children, IAP and NLEM 2022.
Start with the drugs used in the child emergencies, then common outpatient medicines
(paracetamol, ibuprofen, amoxicillin, co-amoxiclav, azithromycin, cefixime, ORS/zinc, salbutamol,
prednisolone, ondansetron, cetirizine, iron, albendazole).

## 3. Earlier drug plan — Treatment pages with drug profiles

**Phase 1, still to do:** hyperthyroidism; malaria (NCVBDC); dengue (NCVBDC); typhoid; scrub typhus
(ICMR); TB under NTEP (weight bands, monitoring); COPD; community-acquired pneumonia;
GERD/peptic ulcer/H. pylori; acute diarrhoea (ORS, zinc). Phase 1 cardiometabolic is done:
hypertension, dyslipidaemia, CKD, heart failure, AF and chronic coronary disease.

**Phase 2:** gout (allopurinol, HLA-B\*58:01), osteoarthritis, rheumatoid arthritis (methotrexate
baseline tests), osteoporosis; epilepsy (valproate, carbamazepine HLA-B\*15:02), migraine,
depression and anxiety, stroke secondary prevention; tinea, scabies, acne, urticaria;
contraception (WHO MEC), PCOS, menopause, breastfeeding safety.

**Phase 3 (pregnancy additions):** epilepsy and depression in pregnancy, VTE prophylaxis
(weight-based LMWH), preterm labour (antenatal steroids), and the remaining minor ailments
(constipation, piles, cold/allergy). PPH is now covered by its emergency protocol.

## 4. Smaller items

- CURB-65: let urea be entered in mg/dL (Indian labs) as well as mmol/L
- Poisoning batch 2: tricyclics, beta-blocker/calcium-channel blocker, iron, lithium, cyanide,
  methaemoglobinaemia
- Review the AHA/ASA 2026 stroke guideline correction
- Skin conditions with licensed photos
- iOS build on GitHub macOS runners

## 5. To discuss after 0.4: not queued, no work starts until we agree the scope

- **Antibiotics in the Drugs section.** Every commonly used antibiotic, with its uses, contraindications,
  kidney and liver dosing and monitoring. Sources: FDA labels, the ICMR antimicrobial treatment guidelines and NLEM.
- **Infections A–Z.** Type "measles" and get a few lines on the disease, the investigation of choice (IOC),
  other investigations, the drug of choice (DOC) and second-line options. The same layout for other infections,
  following Indian programmes first (NCDC, NCVBDC, NTEP, ICMR), then WHO, CDC and IDSA.
- **Fever work-up.** A guided list of differential diagnoses. The doctor ticks associated features
  (cough, headache, neck stiffness, joint pain, rash, burning micturition, rigors and chills, relative
  bradycardia, onset and pattern) and gets the differentials the sources list for that pattern, with the
  next investigations. It suggests what to consider and does not make a diagnosis.
- **Steroid misuse.**
  - Patients on corticosteroid injections bought from a chemist: Cushing's features, adrenal suppression,
    safe tapering, sick-day rules, infection and glucose risks.
  - Anabolic–androgenic steroid use for bodybuilding: harms, monitoring (haematocrit, lipids, liver,
    BP), and hypogonadism and dependence after stopping (Endocrine Society scientific statement and other
    authoritative sources).
