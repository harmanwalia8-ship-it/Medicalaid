package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.WeightDose
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.ProtocolCategory
import com.medicalaid.app.content.protocols.ProtocolNote
import com.medicalaid.app.content.protocols.ProtocolTool
import com.medicalaid.app.content.protocols.Step
import com.medicalaid.app.content.protocols.StepDose

// Rewritten in quick-reference style by Gemini (r1.md); converted by convert_protocols.py --rewrite.
// Clinical content checked only by the automatic number-preservation check (see the batch report).

internal val envenomationSources: List<Source> = listOf(
    Source(
        "tx-sb-mohfw-stg-2017", Authority.MoHFW, "Standard Treatment Guidelines (MoHFW, August 2017; ISBN 978-93-82655-21-3)",
        "Management of Snake Bite", "2017",
        "https://ncdc.mohfw.gov.in/uploads/pdf/Standard-Treatment-Guidelines-for-Management-of-Snake-Bite.pdf",
        status = "National clinical guideline listed by NCDC's snakebite programme (checked November 2026)",
    ),
    Source(
        "tx-sb-ncdc-napse-2024", Authority.MoHFW, "National Action Plan (NCDC, Centre for One Health)",
        "National Action Plan for Prevention and Control of Snakebite Envenoming (NAPSE)", "2024",
        "https://ncdc.mohfw.gov.in/uploads/pdf/NATIONAL-ACTION-PLAN-FOR-PREVENTION-AND-CONTROL-OF-SNAKEBITE-ENVENOMING-NAPSE.pdf",
        status = "Clinical content in Ch. 11 (pre-hospital care and diagnosis) and Ch. 13 (anti-snake venom)",
    ),
    Source(
        "tx-sb-ncdc-phc-2024", Authority.MoHFW, "NCDC flowchart for PHC/CHC",
        "Protocol for Initial Management of Snakebite at Health Facilities (PHC/CHC)", "2024",
        "https://ncdc.mohfw.gov.in/uploads/pdf/PEP-Protocol.pdf",
        status = "One-page flowchart; refers to the Standard Treatment Guidelines for details",
    ),
    Source(
        "tx-sb-icmr-stw-paralysis-2019", Authority.ICMR, "Standard Treatment Workflow (Neurology)",
        "Acute Paralysis", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952346_neurology_acute_paralysis.pdf",
        status = "ICMR has no snakebite- or scorpion-specific STW (checked November 2026)",
    ),
    Source(
        "tx-sb-who-searo-2016", Authority.WHO, "WHO SEARO guideline, 2nd edition (ISBN 978-92-9022-530-0)",
        "Guidelines for the management of snakebites", "2016",
        "https://iris.who.int/handle/10665/249547",
    ),
    Source(
        "tx-sc-bawaskar-bmj-2011", Authority.Paper,
        "Randomised open-label trial, BMJ 2011;342:c7136 (Bawaskar HS, Bawaskar PH; Mahad, Maharashtra)",
        "Efficacy and safety of scorpion antivenom plus prazosin compared with prazosin alone for venomous scorpion (Mesobuthus tamulus) sting",
        "2011",
        "https://doi.org/10.1136/bmj.c7136",
    ),
    Source(
        "tx-sc-bawaskar-ip-2015", Authority.Paper,
        "Editorial, Indian Pediatrics 2015;52:289–290 (journal of the Indian Academy of Pediatrics)",
        "Scorpion envenoming – a step ahead (Bawaskar HS, Bawaskar PH)", "2015",
        "https://indianpediatrics.net/apr2015/289.pdf",
    ),
    Source(
        "tx-sc-jipmer-ip-2015", Authority.Paper,
        "Prospective observational study, Indian Pediatrics 2015;52:297–301 (JIPMER, Puducherry)",
        "Predictors of myocardial dysfunction in children with Indian red scorpion (Mesobuthus tamulus) sting envenomation",
        "2015",
        "https://doi.org/10.1007/s13312-015-0627-9",
        status = "Methods section sets out the departmental treatment protocol",
    ),
    Source(
        "tx-sc-jipmer-ip-2018", Authority.Paper,
        "Prospective study, Indian Pediatrics 2018;55:315–318 (JIPMER; CTRI/2015/03/005652)",
        "Second dose of scorpion antivenom in children with Indian red scorpion (Mesobuthus tamulus) sting envenomation",
        "2018",
        "https://doi.org/10.1007/s13312-018-1275-7",
        status = "Exploratory; no comparison arm",
    ),
    Source(
        "tx-sc-rodrigo-sr-2017", Authority.Paper, "Systematic review and meta-analysis, Syst Rev 2017;6:74",
        "Management of scorpion envenoming: a systematic review and meta-analysis of controlled clinical trials", "2017",
        "https://doi.org/10.1186/s13643-017-0469-8",
    ),
    Source(
        "tx-sc-kumar-jfmpc-2022", Authority.Paper, "Review, J Family Med Prim Care 2022;11(9):4932–4935",
        "An update on epidemiology and management practices of scorpion envenomation in India", "2022",
        "https://doi.org/10.4103/jfmpc.jfmpc_2300_21",
    ),
    Source(
        "tx-sc-fda-prazosin", Authority.FDA, "FDA-approved label (DailyMed; prazosin hydrochloride capsules, Aurobindo)",
        "Prazosin hydrochloride capsules — prescribing information", "2023",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=08114092-fa12-4f58-b08e-03cadf9f726b",
        status = "Labelled for hypertension only; use in scorpion envenoming is off-label",
    ),
)

internal val envenomationProtocols: List<Protocol> = listOf(
    Protocol(
        id = "snakebite",
        title = "Snakebite",
        summary = "First aid, 20WBCT, neurotoxic vs haemotoxic syndromes, polyvalent ASV, anaphylaxis, neostigmine, ventilation, AKI, and referral.",
        keywords = listOf("snake", "snakebite", "saanp", "krait", "cobra", "naag", "Russell's viper", "saw-scaled viper", "Echis", "phoorsa", "ASV", "anti-snake venom", "20WBCT", "neurotoxic", "haemotoxic", "ptosis", "neostigmine"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "First aid, arrival and observation",
                timing = "Immediate; admit and observe ≥24 h",
                points = listOf(
                    "Reassure (~70% non-venomous), splint limb like a fracture, left-lateral recovery position, NPO, remove rings/tight items, transport via 102/108.",
                    "Do not remove tourniquets in the field; release slowly (proximal last) only after ASV starts with atropine–neostigmine ready.",
                    "Resuscitate ABCs, start large-bore IV saline, type/cross-match first blood sample (venom/ASV interfere later), and observe ≥24 h.",
                    "Re-examine asymptomatic bites at 2, 6, 12, 24 h; GCS and fixed pupils cannot diagnose brain death in neurotoxic paralysis.",
                ),
                checklistTitle = "First-Aid Don'ts",
                checklist = listOf(
                    "Tourniquet or tight ligature",
                    "Cutting, suction, rubbing, tattooing, massage, or vigorous wound washing",
                    "Herbs, chemicals, ice, cautery, electric shock, or \"black stone\"",
                    "Injecting or applying ASV at the bite site",
                    "Delay for traditional healers or trying to catch/kill the snake",
                ),
                citations = listOf(cite("tx-sb-mohfw-stg-2017", "pp. 7–9"), cite("tx-sb-ncdc-napse-2024", "pp. 65–66"), cite("tx-sb-mohfw-stg-2017", "p. 10"), cite("tx-sb-mohfw-stg-2017", "pp. 10, 18–22"), cite("tx-sb-ncdc-napse-2024", "p. 71"), cite("tx-sb-mohfw-stg-2017", "p. 19"), cite("tx-sb-who-searo-2016", "§6.4.2.3"), cite("tx-sb-mohfw-stg-2017", "pp. 7–10, 18–22"), cite("tx-sb-ncdc-napse-2024", "Ch. 11, pp. 65–74"), cite("tx-sb-who-searo-2016", "§6.2–6.4")),
            ),
            Step(
                title = "20-minute whole blood clotting test (20WBCT)",
                timing = "Baseline, hourly × 3 h, then 6-hourly to 24 h",
                points = listOf(
                    "Place 2 mL fresh venous blood in a new clean dry glass tube undisturbed for 20 min, then tilt once.",
                    "Liquid blood = \"not clotted\" (coagulopathy); report clotted/not clotted, never positive/negative; plastic/wet tubes give false non-clotting.",
                    "Do not delay ASV for spontaneous systemic bleeding (20WBCT sensitivity ~84%); repeat 6 h after ASV loading dose.",
                ),
                citations = listOf(cite("tx-sb-mohfw-stg-2017", "p. 21"), cite("tx-sb-who-searo-2016", "§6.6.1, pp. 123–125"), cite("tx-sb-who-searo-2016", "§6.6.1, p. 124"), cite("tx-sb-ncdc-napse-2024", "p. 68"), cite("tx-sb-mohfw-stg-2017", "pp. 19, 21"), cite("tx-sb-mohfw-stg-2017", "pp. 19, 21, 68"), cite("tx-sb-ncdc-napse-2024", "pp. 68, 76–77")),
            ),
            Step(
                title = "Recognise envenoming syndrome",
                timing = "Cobra 30 min–6 h; krait 6–12 h (up to 16–36 h)",
                points = listOf(
                    "Neuroparalytic (cobra/krait): 5 Ds + 2 Ps (dyspnoea, dysphonia, dysarthria, diplopia, dysphagia; ptosis, descending paralysis); krait often lacks bite marks.",
                    "Vasculotoxic (Russell's/saw-scaled viper): painful swelling, blisters, bleeding, non-clotting 20WBCT, shock, AKI (Russell's: capillary leak, parotid swelling, chemosis).",
                    "Anxiety starts within minutes; true envenoming usually ≥30 min; intubate early for impending respiratory failure.",
                ),
                checklistTitle = "Impending Respiratory Failure",
                checklist = listOf(
                    "Single breath count ≤30 (normal >30) or breath-holding time ≤45 s",
                    "Cannot complete one sentence in one breath",
                    "Cannot lift head (\"broken neck\" sign)",
                    "Pooling of saliva or husky/weak cry in a child",
                    "Paradoxical (abdominal) breathing or SpO2 <90%",
                ),
                citations = listOf(cite("tx-sb-mohfw-stg-2017", "pp. 6, 10–17"), cite("tx-sb-mohfw-stg-2017", "pp. 10–17"), cite("tx-sb-ncdc-napse-2024", "pp. 67–68"), cite("tx-sb-icmr-stw-paralysis-2019", "Algorithm"), cite("tx-sb-mohfw-stg-2017", "pp. 6, 10–17, 51")),
            ),
            Step(
                title = "Anti-snake venom (ASV): indications and dose",
                timing = "Full dose immediately once envenoming evident",
                points = listOf(
                    "Start polyvalent ASV 10 vials IV over 30 min (NAPSE: 30–90 min); never IM, local, or test dose.",
                    "Neurotoxic: give second 10 vials after 1 h if unimproved (max 20 vials).",
                    "Vasculotoxic: repeat q6h until clotted — high-dose 10→6 vials (low-dose 10 [6 Echis]→2 vials × 3 d; reconsider >30 vials).",
                    "Children and pregnant women receive the exact same vial dose as adults (dilute in 5–10 mL/kg saline for children).",
                ),
                checklistTitle = "ASV Indications",
                checklist = listOf(
                    "Spontaneous systemic bleeding",
                    "Non-clotting 20WBCT, INR >1.2, or platelets <100,000/mm³",
                    "Neurotoxic signs: ptosis, ophthalmoplegia, or paralysis",
                    "Shock, hypotension, arrhythmia, or abnormal ECG",
                    "Oliguria/anuria, rising creatinine, or dark brown urine",
                    "Swelling >half limb within 48 h, rapidly spreading swelling, digital bite, or tender regional nodes",
                ),
                citations = listOf(cite("tx-sb-mohfw-stg-2017", "pp. 27–28"), cite("tx-sb-ncdc-napse-2024", "pp. 83–84"), cite("tx-sb-mohfw-stg-2017", "p. 31"), cite("tx-sb-mohfw-stg-2017", "pp. 28, 30–31"), cite("tx-sb-mohfw-stg-2017", "p. 30"), cite("tx-sb-who-searo-2016", "§6.7.8"), cite("tx-sb-mohfw-stg-2017", "pp. 27–31"), cite("tx-sb-ncdc-napse-2024", "pp. 71, 83–84"), cite("tx-sb-ncdc-phc-2024", "ASV box"), cite("tx-sb-who-searo-2016", "§6.7.2, §6.7.7–6.7.10")),
            ),
            Step(
                title = "ASV reactions",
                timing = "Monitor q5min × 30 min, then q15min × 2 h",
                points = listOf(
                    "High risk (past equine ASV, anti-tetanus or anti-rabies serum; severe atopy or asthma): give ASV only for systemic envenoming, with premedication — MoHFW: hydrocortisone 200 mg; WHO: adrenaline 0.25 mg SC, then IV chlorphenamine.",
                    "Keep doctor at bedside with two adrenaline syringes ready; give prophylactic adrenaline 0.25 mg SC unless BP >140/90 or CVD/stroke.",
                    "At first sign (itch, urticaria, cough, vomiting, hypotension): stop ASV and give IM adrenaline 0.5 mg (1:1000) every 5–10 min.",
                    "Add IV chlorphenamine 10 mg and hydrocortisone 100 mg (acts late); restart ASV slowly once recovered to finish dose.",
                    "Serum sickness (1–12 d): chlorphenamine 2 mg q6h×5 d; add prednisolone 5 mg q6h×5 d if unimproved at 24–48 h.",
                ),
                doses = listOf(
                    StepDose("Adrenaline for ASV reaction — child", WeightDose(0.01, 0.01, "mg", "per dose IM (1:1000)"), note = "Adults 0.5 mg IM; repeat every 5–10 min if reaction persists", maxTotal = 0.5),
                    StepDose("Prophylactic adrenaline before ASV — child", WeightDose(0.005, 0.005, "mg", "once SC just before ASV"), note = "Adults 0.25 mg SC; avoid if BP >140/90 or cardiovascular/cerebrovascular disease", maxTotal = 0.25),
                    StepDose("Chlorphenamine — child", WeightDose(0.2, 0.2, "mg", "per dose IV"), note = "Adults 10 mg IV", maxTotal = 10.0),
                    StepDose("Hydrocortisone — child", WeightDose(2.0, 2.0, "mg", "per dose IV"), note = "Adults 100 mg IV; acts after several hours", maxTotal = 100.0),
                ),
                citations = listOf(cite("tx-sb-mohfw-stg-2017", "pp. 31–33"), cite("tx-sb-who-searo-2016", "§6.7.5.2"), cite("tx-sb-mohfw-stg-2017", "pp. 31–32"), cite("tx-sb-who-searo-2016", "§6.7.5.3, p. 134"), cite("tx-sb-mohfw-stg-2017", "p. 32"), cite("tx-sb-who-searo-2016", "§6.7.5.4, p. 135"), cite("tx-sb-mohfw-stg-2017", "pp. 32–33"), cite("tx-sb-who-searo-2016", "§6.7.5.4"), cite("tx-sb-mohfw-stg-2017", "p. 33"), cite("tx-sb-mohfw-stg-2017", "pp. 29, 31–33"), cite("tx-sb-who-searo-2016", "§6.7.5, pp. 132–135"), cite("tx-sb-ncdc-phc-2024", "Adverse reaction")),
            ),
            Step(
                title = "Neurotoxic envenoming: neostigmine and ventilation",
                timing = "Give 1 atropine–neostigmine dose before transfer",
                points = listOf(
                    "Support ventilation early (intubation/LMA/ventilator) — never rely on ASV alone in bulbar or respiratory paralysis.",
                    "India: atropine 0.6 mg + neostigmine 1.5 mg IV, then 0.5 mg q30min×5 (taper 1/2/6/12 h); stop if krait/no response×3.",
                    "WHO: trial in all neurotoxic bites — atropine 0.6 mg IV + neostigmine 0.02 mg/kg IM (max 10 mg/24 h).",
                    "No response after 3 doses (krait): calcium gluconate 10 mL IV (children 1–2 mL/kg, 1:1 dilution) over 5–10 min q6h.",
                    "MoHFW: avoid sedatives; WHO: sedate ventilated patients (fentanyl 2–40 µg/h+midazolam 2–4 mg/h, or morphine 1–5+lorazepam 1–2 mg/h).",
                ),
                doses = listOf(
                    StepDose("Atropine before neostigmine — child", WeightDose(0.05, 0.05, "mg", "IV before each neostigmine dose"), note = "Adults 0.6 mg IV", maxTotal = 0.6),
                    StepDose("Neostigmine first dose — child", WeightDose(0.04, 0.04, "mg", "once IV/IM after atropine"), note = "Adults: 1.5 mg IV (MoHFW) or 0.02 mg/kg IM (WHO)", maxTotal = 1.5),
                    StepDose("Neostigmine repeat — child (MoHFW)", WeightDose(0.01, 0.01, "mg", "IV with atropine q30min × 5"), note = "Adults 0.5 mg; stop if no ≥50% ptosis recovery after 3 doses", maxTotal = 0.5),
                    StepDose("Neostigmine trial — adult (WHO)", WeightDose(0.02, 0.02, "mg", "once IM after atropine 0.6 mg IV"), note = "Observe 30–60 min; responders 0.5–2.5 mg q1–3h (max 10 mg/24 h)"),
                    StepDose("Neostigmine maintenance — child (WHO)", WeightDose(0.01, 0.04, "mg", "every 2–4 h IM/IV/SC with atropine"), note = "Only after convincing trial response", maxTotal = 2.5),
                ),
                citations = listOf(cite("tx-sb-mohfw-stg-2017", "p. 35"), cite("tx-sb-who-searo-2016", "§6.9.1, p. 144"), cite("tx-sb-mohfw-stg-2017", "pp. 35–36"), cite("tx-sb-who-searo-2016", "§6.9.3, pp. 152–153"), cite("tx-sb-mohfw-stg-2017", "p. 36"), cite("tx-sb-mohfw-stg-2017", "p. 45"), cite("tx-sb-who-searo-2016", "§6.9.2, p. 152"), cite("tx-sb-mohfw-stg-2017", "pp. 35–36, 45, 51"), cite("tx-sb-who-searo-2016", "§6.9.1–6.9.3, pp. 144, 152–153"), cite("tx-sb-ncdc-phc-2024", "ASV box")),
            ),
            Step(
                title = "Haemotoxic complications: bleeding, shock, AKI",
                timing = "Forced alkaline diuresis within 24 h only",
                points = listOf(
                    "Strict bed rest, no IM injections or arterial/central punctures, target urine output 1 mL/kg/h; never give heparin or antifibrinolytics.",
                    "Persistent bleeding after ASV or PCV <30%: transfuse blood or FFP 10–15 mL/kg (aim INR <2.0 at 6 h).",
                    "Shock: crystalloid boluses up to 20 mL/kg; start dopamine/dobutamine if crepitations or hepatomegaly develop.",
                    "Oliguria <24 h: forced alkaline diuresis (MoHFW) or furosemide 1–1.5 mg/kg IV (WHO; <100 mL/h over 2 h predicts AKI).",
                    "Dialyse for creatinine >4 mg/dL, urea >130 mg/dL, K+ >7 mmol/L or ECG changes, fluid overload, encephalopathy, or pericarditis.",
                ),
                doses = listOf(
                    StepDose("Fresh frozen plasma — persistent coagulopathy after ASV", WeightDose(10.0, 15.0, "mL", "IV over 30–60 min"), note = "Give within 4 h of ASV; target INR <2.0 at 6 h"),
                    StepDose("Crystalloid bolus — snakebite shock", WeightDose(20.0, 20.0, "mL", "per bolus IV"), note = "Over 5–10 min (children 30 min); stop if crepitations/hepatomegaly"),
                    StepDose("Furosemide stress test — snakebite oliguria", WeightDose(1.0, 1.5, "mg", "once slow IV (4–5 mg/min)"), note = "After adequate fluids; <100 mL/h over 2 h predicts AKI progression"),
                ),
                citations = listOf(cite("tx-sb-mohfw-stg-2017", "pp. 37–40"), cite("tx-sb-who-searo-2016", "§6.9.4"), cite("tx-sb-mohfw-stg-2017", "pp. 37–38"), cite("tx-sb-mohfw-stg-2017", "p. 40"), cite("tx-sb-mohfw-stg-2017", "p. 38"), cite("tx-sb-who-searo-2016", "§6.9.5, pp. 156–159"), cite("tx-sb-mohfw-stg-2017", "p. 39"), cite("tx-sb-who-searo-2016", "§6.9.5"), cite("tx-sb-who-searo-2016", "§6.9.4–6.9.5, §6.10"), cite("tx-sb-ncdc-phc-2024", "Fluid bolus")),
            ),
            Step(
                title = "Wound care, tetanus, discharge and referral",
                timing = "Discharge ≥24 h (no envenoming) or ≥48 h (after ASV)",
                points = listOf(
                    "Clean bite with povidone-iodine (no dressing, leave blisters); give deltoid tetanus booster ONLY after coagulation normalises.",
                    "Analgesia: paracetamol (adults 500–1000 mg q4–6h, max 4 g/d; tramadol 50 mg if severe); NEVER aspirin or NSAIDs.",
                    "Fasciotomy ONLY for confirmed compartment syndrome (pressure >40 mmHg) after coagulopathy is corrected; avoid steroids for local swelling.",
                    "Refer (phone ahead) if no ASV, non-clotting post-10 vials, bleeding, AKI, or progressive paralysis; warn serum sickness (5–10 d).",
                ),
                doses = listOf(
                    StepDose("Paracetamol — child analgesia", WeightDose(10.0, 15.0, "mg", "per dose PO every 4–6 h"), note = "Max 100 mg/kg/day (adults max 4 g/24 h); avoid aspirin/NSAIDs", maxTotal = 1000.0),
                ),
                citations = listOf(cite("tx-sb-mohfw-stg-2017", "pp. 45–47"), cite("tx-sb-ncdc-phc-2024", "Tetanus"), cite("tx-sb-mohfw-stg-2017", "p. 45, 68"), cite("tx-sb-who-searo-2016", "§6.15B"), cite("tx-sb-mohfw-stg-2017", "p. 47"), cite("tx-sb-who-searo-2016", "§6.11–6.13"), cite("tx-sb-mohfw-stg-2017", "pp. 49, 51–53"), cite("tx-sb-mohfw-stg-2017", "pp. 45–53, 68"), cite("tx-sb-who-searo-2016", "§6.11–6.13, §6.15B"), cite("tx-sb-ncdc-phc-2024", "Tetanus; Referral")),
            ),
        ),
        notes = listOf(
            ProtocolNote("Indian polyvalent ASV covers only the \"big four\" (cobra, krait, Russell's, saw-scaled viper) — not hump-nosed, Malabar, or green pit vipers, or sea snakes.", listOf(cite("tx-sb-mohfw-stg-2017", "pp. 28, 30"), cite("tx-sb-who-searo-2016", "§6.7.8, p. 140"))),
            ProtocolNote("First aid differs: WHO advises pressure-pad immobilisation unless elapid excluded; MoHFW restricts crepe bandage to trained staff (transfer 30 min–3 h); NAPSE advises splinting.", listOf(cite("tx-sb-who-searo-2016", "§6.2.3"), cite("tx-sb-mohfw-stg-2017", "p. 9"), cite("tx-sb-ncdc-napse-2024", "pp. 73–74"))),
            ProtocolNote("Neostigmine, sedation, and AKI differ: MoHFW uses IV neostigmine (none in krait), avoids sedation, and uses forced alkaline diuresis; WHO uses IM neostigmine, sedates ventilated patients, and uses furosemide stress.", listOf(cite("tx-sb-mohfw-stg-2017", "pp. 36, 38, 45"), cite("tx-sb-who-searo-2016", "§6.9.2–6.9.5"))),
        ),
        citations = listOf(cite("tx-sb-mohfw-stg-2017"), cite("tx-sb-ncdc-napse-2024"), cite("tx-sb-who-searo-2016"), cite("tx-sb-icmr-stw-paralysis-2019"), cite("tx-sb-ncdc-phc-2024")),
    ),
    Protocol(
        id = "scorpion-sting",
        title = "Scorpion sting",
        summary = "Grading Indian red scorpion envenoming, oral prazosin, scorpion antivenom, pulmonary oedema/myocarditis ICU care, and drugs to avoid.",
        keywords = listOf("scorpion", "scorpion sting", "Mesobuthus tamulus", "Hottentotta tamulus", "red scorpion", "bichhu", "thuhan", "prazosin", "Minipress", "Prazopress", "SAV", "scorpion antivenom", "AScVS", "autonomic storm", "priapism"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "Recognise and grade",
                timing = "Grade on arrival; delay >4–6 h carries higher mortality",
                points = listOf(
                    "Grade 1: severe local pain radiating along dermatomes, mild oedema, no systemic signs; observe 24 h.",
                    "Grade 2 (autonomic storm): vomiting, profuse sweating, ropy salivation, priapism, HR >120 or bradycardia, BP >140/90 or hypotension, cold extremities.",
                    "Grade 3: pulmonary oedema (RR >24, crackles), myocardial dysfunction (inotrope ≥5 µg/kg/min), cold shock; Grade 4: warm shock/MODS.",
                    "For Grade 1 pain, use local anaesthetic (digital block for digits) or topical 5% lidocaine plus paracetamol.",
                ),
                checklistTitle = "Ominous Early Signs",
                checklist = listOf(
                    "Vomiting",
                    "Profuse sweating",
                    "Cold extremities",
                    "Priapism in males",
                ),
                citations = listOf(cite("tx-sc-bawaskar-bmj-2011", "Methods"), cite("tx-sc-jipmer-ip-2015", "Methods"), cite("tx-sc-jipmer-ip-2018", "Methods"), cite("tx-sc-kumar-jfmpc-2022", "Management"), cite("tx-sc-rodrigo-sr-2017", "Pain relief"), cite("tx-sc-jipmer-ip-2015", "Methods; Results")),
            ),
            Step(
                title = "Prazosin (grade 2 and above)",
                timing = "Start at first autonomic sign; repeat every 3 h",
                points = listOf(
                    "Start oral prazosin immediately: child 30 µg/kg (JIPMER) or 250 µg (<18 y)/500 µg (adult) q3h until extremities warm.",
                    "Keep patient lying flat to prevent first-dose postural syncope (peaks 30–90 min; worsened by beta-blockers).",
                    "Re-dose promptly if vomited; recovery on oral prazosin takes 12–48 h with ICU monitoring.",
                ),
                doses = listOf(
                    StepDose("Prazosin — child (JIPMER protocol)", WeightDose(30.0, 30.0, "mcg", "per dose PO every 3 h"), note = "Continue until extremities are warm; lay flat (first-dose syncope); adults 500 mcg q3h", maxTotal = 1000.0),
                ),
                citations = listOf(cite("tx-sc-jipmer-ip-2015", "Methods"), cite("tx-sc-bawaskar-bmj-2011", "Study treatments"), cite("tx-sc-fda-prazosin", "Warnings"), cite("tx-sc-bawaskar-bmj-2011", "Results"), cite("tx-sc-bawaskar-ip-2015", "p. 289"), cite("tx-sc-bawaskar-bmj-2011", "Study treatments; Results")),
            ),
            Step(
                title = "Scorpion antivenom (SAV)",
                timing = "Most effective within 4–6 h of sting",
                points = listOf(
                    "Give Haffkine monovalent SAV 30 mL in 100 mL saline IV over 30–60 min regardless of age alongside prazosin.",
                    "SAV plus prazosin hastens recovery (8 vs 17.7 h) in early Grade 2, but does not reverse established severe envenoming.",
                    "Repeat 30 mL SAV if autonomic storm persists/worsens ≤6 h; stop SAV and give adrenaline for allergic reactions (8%).",
                ),
                citations = listOf(cite("tx-sc-bawaskar-bmj-2011", "Study treatments"), cite("tx-sc-jipmer-ip-2018", "Methods"), cite("tx-sc-bawaskar-bmj-2011", "Results"), cite("tx-sc-bawaskar-ip-2015", "pp. 289–290"), cite("tx-sc-jipmer-ip-2018", "Methods; Results"), cite("tx-sc-bawaskar-bmj-2011", "Study treatments; Results")),
            ),
            Step(
                title = "Pulmonary oedema, myocardial dysfunction and shock",
                timing = "Grades 3–4: ICU support for 24–96 h",
                points = listOf(
                    "Myocardial dysfunction or warm shock: oxygen, non-invasive/mechanical ventilation, and IV dobutamine 6–20 µg/kg/min.",
                    "Pulmonary oedema: oxygen, ventilation, and IV nitroglycerine 2–6 µg/kg/min (light-protected) or sodium nitroprusside 0.3–5 µg/kg/min.",
                    "Correct dehydration cautiously with IV crystalloid (avoid fluid overload); NEVER give diuretics.",
                ),
                doses = listOf(
                    StepDose("Dobutamine — scorpion myocardial dysfunction (child)", WeightDose(6.0, 20.0, "mcg", "per minute IV infusion"), note = "Give with oxygen in ICU"),
                    StepDose("Nitroglycerine — scorpion pulmonary oedema (child)", WeightDose(2.0, 6.0, "mcg", "per minute IV infusion"), note = "Freshly prepared, protected from sunlight"),
                    StepDose("Sodium nitroprusside — scorpion pulmonary oedema (child)", WeightDose(0.3, 5.0, "mcg", "per minute IV infusion"), note = "Alternative to nitroglycerine"),
                ),
                citations = listOf(cite("tx-sc-jipmer-ip-2015", "Methods"), cite("tx-sc-bawaskar-ip-2015", "pp. 289–290"), cite("tx-sc-bawaskar-bmj-2011", "Study treatments"), cite("tx-sc-jipmer-ip-2018", "Results"), cite("tx-sc-bawaskar-ip-2015", "p. 290")),
            ),
            Step(
                title = "Drugs to avoid, monitoring and discharge",
                timing = "Vitals at 0, 30 min, 1, 2, 4, 6, 8, 10, 14, 18, 24 h",
                points = listOf(
                    "Record BP, HR, RR, SpO2 on schedule; obtain ECG at 0 and 6 h, echo and CPK-MB within 24 h.",
                    "Give first prazosin dose at peripheral centres before referring promptly to an ICU with oxygen and SAV.",
                    "Observe ≥24 h after SAV (or ≥24 h stable off drugs after complications); give tetanus immunisation before discharge.",
                ),
                checklistTitle = "Drugs to Avoid in Scorpion Envenoming",
                checklist = listOf(
                    "Corticosteroids (worsen catecholamine myocardial injury; no benefit)",
                    "Antihistamines as envenoming treatment (QT prolongation; sudden death)",
                    "Calcium channel blockers (e.g. nifedipine — depress myocardium)",
                    "Diuretics (worsen pulmonary oedema and shock)",
                    "Atropine, beta-blockers, and lytic cocktail (pethidine + promethazine + chlorpromazine)",
                ),
                citations = listOf(cite("tx-sc-bawaskar-bmj-2011", "Methods"), cite("tx-sc-jipmer-ip-2015", "Methods"), cite("tx-sc-kumar-jfmpc-2022", "Management"), cite("tx-sc-bawaskar-bmj-2011", "Study treatments"), cite("tx-sc-bawaskar-bmj-2011", "Introduction; Methods; Study treatments"), cite("tx-sc-bawaskar-ip-2015", "pp. 289–290")),
            ),
        ),
        notes = listOf(
            ProtocolNote("Prazosin dosing differs: JIPMER uses 30 µg/kg (capped at 1 mg) in children; Bawaskar uses fixed 250 µg (<18 y) or 500 µg (adults); off-label for scorpion sting.", listOf(cite("tx-sc-jipmer-ip-2015", "Methods"), cite("tx-sc-bawaskar-bmj-2011", "Study treatments"), cite("tx-sc-fda-prazosin", "Indications"))),
            ProtocolNote("Antihistamines conflict: Bawaskar warns against them (QT prolongation), while JIPMER 2018 premedicated SAV with IV chlorphenamine and ranitidine.", listOf(cite("tx-sc-bawaskar-ip-2015", "p. 290"), cite("tx-sc-jipmer-ip-2018", "Results"))),
        ),
        citations = listOf(cite("tx-sc-bawaskar-bmj-2011"), cite("tx-sc-jipmer-ip-2015"), cite("tx-sc-jipmer-ip-2018"), cite("tx-sc-kumar-jfmpc-2022"), cite("tx-sc-rodrigo-sr-2017"), cite("tx-sc-fda-prazosin"), cite("tx-sc-bawaskar-ip-2015")),
    ),
)
