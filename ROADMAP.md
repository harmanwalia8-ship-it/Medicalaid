# Roadmap — queued work

**Start this queue once version 0.5.0 is released**, meaning the medical-emergency, obstetric and trauma
protocols have passed review. (0.4.0 = poisoning + calculators, already released.) The user agreed on
22 Nov 2026 to go straight on after 0.5.0, in the order below.

Run **one workflow at a time** so usage is spread out instead of spent in minutes.
Each item uses the same process as before. One writer works from primary sources only, India first.
Two independent reviewers then check it: one for every number, one for safety and attribution.
A fixer re-checks each finding before editing. Groups stay hidden (`inReview`) until that cycle is complete.

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

## 2. Infections and antibiotics (one bundle)

These three requests overlap heavily, so they are built together and share their sources.

- **Antibiotics in the Drugs section.** Every commonly used antibiotic: uses, contraindications, kidney and liver
  dosing, monitoring, key interactions. Sources: FDA labels, ICMR antimicrobial treatment guidelines, NLEM 2022.
- **Infections A–Z.** Type "measles" and get a short summary of the disease, the investigation of choice (IOC),
  other investigations, the drug of choice (DOC) and second-line options, each attributed. Indian programmes
  first (NCDC, NCVBDC, NTEP, ICMR), then WHO, CDC and IDSA. Includes the earlier Phase 1 infection pages:
  malaria, dengue, typhoid, scrub typhus, TB under NTEP (weight bands, monitoring), community-acquired
  pneumonia, acute diarrhoea (ORS, zinc).
- **Content model:** an infection page reuses the condition page (summary, info blocks for IOC/other tests,
  tiers for DOC/second line, cautions, sources) so it looks and searches like the rest of Treatment.

## 3. Fever work-up

A guided list of differential diagnoses built on top of Infections A–Z. The doctor ticks associated features
(cough, headache, neck stiffness, joint pain, rash, burning micturition, rigors and chills, relative bradycardia,
duration, onset and pattern, travel/season/exposure) and gets the differentials that cited sources list for that
pattern, each linking to its infection page and the next investigations. It suggests what to consider and does not
make a diagnosis; red flags that need urgent care (e.g. meningitis, sepsis, severe malaria, dengue warning signs)
are shown first. Sources: ICMR STW on acute febrile illness / undifferentiated fever, NCVBDC, WHO.

## 4. Steroid misuse

- Patients on corticosteroid injections bought from a chemist: Cushing's features, adrenal suppression,
  safe tapering, sick-day rules, infection and glucose risks.
- Anabolic–androgenic steroid use for bodybuilding: harms, monitoring (haematocrit, lipids, liver,
  BP), and hypogonadism and dependence after stopping (Endocrine Society scientific statement and other
  authoritative sources).

## 5. Children's doses in the Drugs section

Add paediatric dosing to drug profiles: an age band plus a weight-based dose, a maximum and a citation.
Sources are FDA label paediatric sections, the WHO Model Formulary for Children, IAP and NLEM 2022.
Start with the drugs used in the child emergencies, then common outpatient medicines
(paracetamol, ibuprofen, amoxicillin, co-amoxiclav, azithromycin, cefixime, ORS/zinc, salbutamol,
prednisolone, ondansetron, cetirizine, iron, albendazole).

## 6. Earlier drug plan — remaining Treatment pages

**Phase 1, still to do (non-infection):** hyperthyroidism; COPD (long-term care); GERD/peptic ulcer/H. pylori.
Phase 1 cardiometabolic is done: hypertension, dyslipidaemia, CKD, heart failure, AF and chronic coronary disease.

**Phase 2:** gout (allopurinol, HLA-B\*58:01), osteoarthritis, rheumatoid arthritis (methotrexate
baseline tests), osteoporosis; epilepsy (valproate, carbamazepine HLA-B\*15:02), migraine,
depression and anxiety, stroke secondary prevention; tinea, scabies, acne, urticaria;
contraception (WHO MEC), PCOS, menopause, breastfeeding safety.

**Phase 3 (pregnancy additions):** epilepsy and depression in pregnancy, VTE prophylaxis
(weight-based LMWH), preterm labour (antenatal steroids), and the remaining minor ailments
(constipation, piles, cold/allergy). PPH is covered by its emergency protocol.

## 7. Smaller items

- CURB-65: let urea be entered in mg/dL (Indian labs) as well as mmol/L
- Poisoning batch 2: tricyclics, beta-blocker/calcium-channel blocker, iron, lithium, cyanide,
  methaemoglobinaemia
- Review the AHA/ASA 2026 stroke guideline correction
- Skin conditions with licensed photos
- iOS build on GitHub macOS runners
- Before sharing beyond friends: a colleague's clinical review, a proper signing key, CDSCO check (README).
