package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.WeightDose
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.ProtocolCategory
import com.medicalaid.app.content.protocols.ProtocolNote
import com.medicalaid.app.content.protocols.Step
import com.medicalaid.app.content.protocols.StepDose

/*
 * Neurological emergencies: status epilepticus, acute migraine in the ED, bacterial meningitis, aneurysmal SAH.
 * Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them (checked against the documents,
 *   November 2026). Adults unless a point says otherwise.
 * - Every point is attributed ("ICMR STW: …", "AES 2016: …", "NICE NG217: …", "WHO 2025: …", "AHA/ASA 2023: …").
 * - Locators: NICE recommendation numbers; AES 2016 Figure 1 phase; NCS 2012 table / summary recommendation;
 *   ESCMID key question / table; WHO 2025 section / table; ICMR STW box headings; ICMR 2019 antimicrobial
 *   guideline section / table and page; AHA/ASA 2023 section and recommendation number.
 * - Only the abstracts of ILAE 2015, AHS 2016, AHS 2025, SNNOOP10 and Perry 2013 could be read; nothing beyond
 *   the abstract is taken from them. ESETT: abstract and full text (PMC7098487) read; trial doses are from its Methods
 *   (trial treatments). ICMR first-line benzodiazepine doses without a stated maximum are given as text, not as
 *   calculator doses.
 * - Existing source reused by id: st-icmr-stw-stroke-2019 (Stroke.kt).
 */

internal val neuroEmergencySources = listOf(
    // --- Status epilepticus ---------------------------------------------------------------------
    Source(
        "em-neuro-icmr-stw-epilepsy-2019", Authority.ICMR, "Standard Treatment Workflow (ICD-10 G40)",
        "Management of Epilepsy (includes status epilepticus)", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952345_neurology_epilepsy.pdf",
        status = "October 2019",
    ),
    Source(
        "em-neuro-ilae-se-2015", Authority.ILAE, "Task force report (Epilepsia 2015;56(10):1515–1523)",
        "A definition and classification of status epilepticus — Report of the ILAE Task Force on Classification of Status Epilepticus",
        "2015",
        "https://doi.org/10.1111/epi.13121",
        status = "Abstract reviewed; full text not accessible",
    ),
    Source(
        "em-neuro-aes-cse-2016", Authority.AES, "Evidence-based guideline (Epilepsy Curr 2016;16(1):48–61)",
        "Evidence-Based Guideline: Treatment of Convulsive Status Epilepticus in Children and Adults", "2016",
        "https://doi.org/10.5698/1535-7597-16.1.48",
    ),
    Source(
        "em-neuro-ncs-se-2012", Authority.Paper, "Neurocritical Care Society guideline (Neurocrit Care 2012;17(1):3–23)",
        "Guidelines for the Evaluation and Management of Status Epilepticus", "2012",
        "https://doi.org/10.1007/s12028-012-9695-z",
    ),
    Source(
        "em-neuro-nice-ng217", Authority.NICE, "Guideline NG217",
        "Epilepsies in children, young people and adults", "2022",
        "https://www.nice.org.uk/guidance/ng217",
        status = "Last updated 5 August 2026; gives no doses (refers to the BNF)",
    ),
    Source(
        "em-neuro-acep-seizure-2024", Authority.ACEP, "Clinical policy (Ann Emerg Med 2024;84(1):e1–e12)",
        "Critical Issues in the Management of Adult Patients Presenting to the Emergency Department With Seizures", "2024",
        "https://doi.org/10.1016/j.annemergmed.2024.02.018",
        status = "Replaces the 2014 ACEP seizure policy; recommendation summary reviewed",
    ),
    Source(
        "em-neuro-esett-2019", Authority.Paper, "Randomised trial (N Engl J Med 2019;381(22):2103–2113)",
        "Randomized Trial of Three Anticonvulsant Medications for Status Epilepticus (ESETT)", "2019",
        "https://doi.org/10.1056/NEJMoa1905795",
        status = "Full text (PMC7098487) reviewed: abstract and Methods (trial treatments)",
    ),
    // --- Migraine ---------------------------------------------------------------------------------
    Source(
        "em-neuro-icmr-stw-headache-2019", Authority.ICMR, "Standard Treatment Workflow (ICD-10 G43–G44)",
        "Management of Headache", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952344_neurology_headache.pdf",
        status = "October 2019",
    ),
    Source(
        "em-neuro-aian-headache-2011", Authority.Paper, "Indian guideline (Ann Indian Acad Neurol 2011;14 Suppl 1:S40–S59)",
        "Guidelines on the diagnosis and the current management of headache and related disorders", "2011",
        "https://doi.org/10.4103/0972-2327.83100",
    ),
    Source(
        "em-neuro-ahs-ed-2025", Authority.AHS, "Guideline update (Headache 2026;66(1):53–76)",
        "2025 guideline update to acute treatment of migraine for adults in the emergency department: the American Headache Society evidence assessment of parenteral pharmacotherapies",
        "2025",
        "https://doi.org/10.1111/head.70016",
        status = "Published online 1 December 2025; updates the 2016 assessment. Abstract reviewed; full text (doses) not accessible",
    ),
    Source(
        "em-neuro-ahs-ed-2016", Authority.AHS, "Evidence assessment (Headache 2016;56(6):911–940)",
        "Management of Adults With Acute Migraine in the Emergency Department: The American Headache Society Evidence Assessment of Parenteral Pharmacotherapies",
        "2016",
        "https://doi.org/10.1111/head.12835",
        status = "Updated by the 2025 AHS guideline; abstract reviewed",
    ),
    Source(
        "em-neuro-ahs-choosing-wisely-2013", Authority.AHS, "Choosing Wisely list (Headache 2013;53:1651–1659)",
        "Choosing Wisely in Headache Medicine: The American Headache Society's List of Five Things Physicians and Patients Should Question",
        "2013",
        "https://doi.org/10.1111/head.12233",
    ),
    Source(
        "em-neuro-nice-cg150", Authority.NICE, "Clinical guideline CG150",
        "Headaches in over 12s: diagnosis and management", "2012",
        "https://www.nice.org.uk/guidance/cg150",
        status = "Last updated 3 June 2025",
    ),
    Source(
        "em-neuro-snnoop10-2019", Authority.Paper, "Review (Neurology 2019;92(3):134–144)",
        "Red and orange flags for secondary headaches in clinical practice: SNNOOP10 list", "2019",
        "https://doi.org/10.1212/WNL.0000000000006697",
    ),
    Source(
        "em-neuro-fda-sumatriptan-inj", Authority.FDA, "Prescribing information (DailyMed)",
        "Sumatriptan injection, USP — Strides Pharma Science", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=1909558d-8342-4af0-9487-eecdaac70070",
        status = "Revised January 2026 (DailyMed SPL v4, effective 15 April 2026)",
    ),
    Source(
        "em-neuro-fda-prochlorperazine-inj", Authority.FDA, "Prescribing information (DailyMed)",
        "Prochlorperazine edisylate injection, USP — Heritage Pharmaceuticals (Avet)", "2024",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=0338b221-db43-45c9-a829-d970fe6643ab",
        status = "Labelled for severe nausea and vomiting and psychiatric use, not for migraine",
    ),
    // --- Bacterial meningitis -----------------------------------------------------------------------
    Source(
        "em-neuro-icmr-stw-neuroinfections-2019", Authority.ICMR, "Standard Treatment Workflow (ICD-10 G03.9)",
        "Management of Neuroinfections", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952343_neurology_neuroinfections.pdf",
        status = "October 2019",
    ),
    Source(
        "em-neuro-icmr-amr-2019", Authority.ICMR, "Treatment guidelines, 2nd edition",
        "Treatment Guidelines for Antimicrobial Use in Common Syndromes", "2019",
        "https://www.icmr.gov.in/icmrobject/custom_data/pdf/resource-guidelines/Treatment_Guidelines_2019_Final.pdf",
        status = "Chapter 8 (CNS infections) reviewed. ICMR lists a 2022 edition that could not be retrieved",
    ),
    Source(
        "em-neuro-who-meningitis-2025", Authority.WHO, "Guideline (ISBN 978-92-4-010804-2)",
        "WHO guidelines on meningitis diagnosis, treatment and care", "2025",
        "https://www.who.int/publications/i/item/9789240108042",
        status = "April 2025; gives no doses (refers to the WHO AWaRe antibiotic book)",
    ),
    Source(
        "em-neuro-nice-ng240", Authority.NICE, "Guideline NG240",
        "Meningitis (bacterial) and meningococcal disease: recognition, diagnosis and management", "2024",
        "https://www.nice.org.uk/guidance/ng240",
        status = "Published 19 March 2024; gives no doses (refers to the BNF)",
    ),
    Source(
        "em-neuro-escmid-abm-2016", Authority.ESCMID, "Guideline (Clin Microbiol Infect 2016;22 Suppl 3:S37–S62)",
        "ESCMID guideline: diagnosis and treatment of acute bacterial meningitis", "2016",
        "https://doi.org/10.1016/j.cmi.2016.01.007",
    ),
    Source(
        "em-neuro-idsa-meningitis-2004", Authority.IDSA, "Practice guideline (Clin Infect Dis 2004;39(9):1267–1284)",
        "Practice Guidelines for the Management of Bacterial Meningitis", "2004",
        "https://doi.org/10.1086/425368",
        status = "Tables 2, 4 and 6 reviewed",
    ),
    // --- Subarachnoid haemorrhage ---------------------------------------------------------------------
    Source(
        "em-neuro-aha-asah-2023", Authority.AHA, "Guideline (Stroke 2023;54(7):e314–e370)",
        "2023 Guideline for the Management of Patients With Aneurysmal Subarachnoid Hemorrhage", "2023",
        "https://doi.org/10.1161/STR.0000000000000436",
    ),
    Source(
        "em-neuro-nice-ng228", Authority.NICE, "Guideline NG228",
        "Subarachnoid haemorrhage caused by a ruptured aneurysm: diagnosis and management", "2022",
        "https://www.nice.org.uk/guidance/ng228",
        status = "Published 23 November 2022",
    ),
    Source(
        "em-neuro-ottawa-sah-2013", Authority.Paper, "Cohort study (JAMA 2013;310(12):1248–1255)",
        "Clinical decision rules to rule out subarachnoid hemorrhage for acute headache (Ottawa SAH Rule)", "2013",
        "https://doi.org/10.1001/jama.2013.278018",
        status = "Abstract reviewed",
    ),
    Source(
        "em-neuro-fda-nimodipine", Authority.FDA, "Prescribing information (DailyMed)",
        "Nimodipine capsules, liquid filled — Bionpharma", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=e65d5108-22a2-4d87-a78d-15606f3cc9e2",
        status = "Label effective December 2025",
    ),
)

// Status epilepticus
private const val SE_ICMR = "em-neuro-icmr-stw-epilepsy-2019"
private const val SE_ILAE = "em-neuro-ilae-se-2015"
private const val SE_AES = "em-neuro-aes-cse-2016"
private const val SE_NCS = "em-neuro-ncs-se-2012"
private const val SE_NICE = "em-neuro-nice-ng217"
private const val SE_ACEP = "em-neuro-acep-seizure-2024"
private const val SE_ESETT = "em-neuro-esett-2019"

// Migraine
private const val MG_ICMR = "em-neuro-icmr-stw-headache-2019"
private const val MG_AIAN = "em-neuro-aian-headache-2011"
private const val MG_AHS25 = "em-neuro-ahs-ed-2025"
private const val MG_AHS16 = "em-neuro-ahs-ed-2016"
private const val MG_CW = "em-neuro-ahs-choosing-wisely-2013"
private const val MG_NICE = "em-neuro-nice-cg150"
private const val MG_SNOOP = "em-neuro-snnoop10-2019"
private const val MG_SUMA = "em-neuro-fda-sumatriptan-inj"
private const val MG_PCZ = "em-neuro-fda-prochlorperazine-inj"

// Bacterial meningitis
private const val BM_STW = "em-neuro-icmr-stw-neuroinfections-2019"
private const val BM_ICMR = "em-neuro-icmr-amr-2019"
private const val BM_WHO = "em-neuro-who-meningitis-2025"
private const val BM_NICE = "em-neuro-nice-ng240"
private const val BM_ESCMID = "em-neuro-escmid-abm-2016"
private const val BM_IDSA = "em-neuro-idsa-meningitis-2004"

// Subarachnoid haemorrhage
private const val SAH_AHA = "em-neuro-aha-asah-2023"
private const val SAH_NICE = "em-neuro-nice-ng228"
private const val SAH_OTTAWA = "em-neuro-ottawa-sah-2013"
private const val SAH_NIMO = "em-neuro-fda-nimodipine"
private const val SAH_ICMR_STROKE = "st-icmr-stw-stroke-2019"

internal val neuroEmergencyProtocols = listOf(
    // =========================================================================================
    // Status epilepticus
    // =========================================================================================
    Protocol(
        id = "status-epilepticus",
        category = ProtocolCategory.Neuro,
        title = "Status epilepticus",
        summary = "Timed pathway for convulsive status epilepticus — stabilisation and glucose, benzodiazepines, " +
            "second-line drugs, anaesthetic infusions, non-convulsive status and EEG — attributed to ICMR, AES, NICE, " +
            "the Neurocritical Care Society, ACEP and ILAE.",
        keywords = listOf(
            "status epilepticus", "status epilepsy", "SE", "CSE", "GCSE", "convulsive status epilepticus",
            "fits", "fit not stopping", "seizure not stopping", "seizures", "convulsions", "epilepsy", "mirgi",
            "daura", "jhatke", "refractory status epilepticus", "RSE", "super-refractory", "NCSE",
            "non-convulsive status", "nonconvulsive status", "lorazepam", "midazolam", "diazepam", "levetiracetam",
            "phenytoin", "fosphenytoin", "valproate", "phenobarbitone", "phenobarbital", "Eptoin", "Valparin",
            "Levipil", "ESETT", "buccal midazolam", "continuous EEG", "cEEG",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise status epilepticus",
                timing = "Convulsive seizure ≥5 min (ILAE t1)",
                points = listOf(
                    "ILAE 2015: in convulsive (tonic–clonic) SE, treat after t1 = 5 min of continuous seizure; risk of long-term consequences after t2 = 30 min.",
                    "NICE NG217: convulsive status epilepticus is a seizure lasting 5 minutes or more — provide resuscitation and immediate emergency treatment.",
                    "NCS 2012: SE is ≥5 min of continuous clinical and/or electrographic seizure activity, or recurrent seizures without recovery in between.",
                    "ICMR STW: a motor convulsion >5 min with loss of consciousness is SE — a medical emergency; do ABCs simultaneously with medication.",
                    "NICE NG217: repeated or cluster seizures (typically ≥3 self-terminating seizures in 24 h) and seizures >2 min longer than usual are also emergencies.",
                    "NICE NG217: dissociative (non-epileptic) seizures can resemble convulsive SE. ESETT: 10% of enrolled patients had psychogenic seizures.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(SE_ILAE, "Abstract (operational time points)"),
                    cite(SE_NICE, "Rec 7.1.1, 7.1.6, 7.2.1, 7.3.1"),
                    cite(SE_NCS, "Summary recommendations for SE definition, 1"),
                    cite(SE_ICMR, "Clinical features; SE timeline"),
                    cite(SE_ESETT, "Abstract — results"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Stabilise and check glucose",
                timing = "0–5 min (AES 2016)",
                points = listOf(
                    "AES 2016: stabilise airway, breathing, circulation and disability; time the seizure; monitor vital signs; oxygen, considering intubation if needed; ECG monitoring.",
                    "AES 2016: finger-stick glucose — if <60 mg/dL, adults: thiamine 100 mg IV, then 50 mL of 50% dextrose IV.",
                    "NCS 2012: nutrient resuscitation with thiamine given before dextrose. ICMR STW: thiamine and glucose if alcoholic; check blood sugar and give IV glucose if diabetic.",
                    "AES 2016: IV access; electrolytes, haematology, toxicology screen and anticonvulsant levels as appropriate. ICMR STW adds ECG, ABG and oximetry.",
                    "NICE NG217: look for causes needing their own treatment — hypoglycaemia, eclampsia, alcohol withdrawal — and non-adherence to antiseizure medication.",
                    "NCS 2012: definitive control of SE should be established within 60 minutes of onset.",
                ),
                citations = listOf(
                    cite(SE_AES, "Figure 1 — 0–5 min stabilization phase"),
                    cite(SE_NCS, "Table 5; Treatment of SE"),
                    cite(SE_ICMR, "Emergency work-up (footer of SE algorithm)"),
                    cite(SE_NICE, "Rec 7.1.4–7.1.5"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "First line: a benzodiazepine",
                timing = "5–20 min (AES 2016)",
                points = listOf(
                    "AES 2016: a benzodiazepine is the initial therapy of choice (Level A) — one of IM midazolam, IV lorazepam or IV diazepam.",
                    "AES 2016: IM midazolam 10 mg if >40 kg, 5 mg if 13–40 kg, once; in adults without IV access it outperforms IV lorazepam (Level A).",
                    "NICE NG217: IV lorazepam if IV access and resuscitation facilities are available, otherwise buccal midazolam or rectal diazepam; second dose if not stopped 5–10 min after the first.",
                    "ICMR STW (emergency room): IV lorazepam up to 0.1 mg/kg at 2 mg/min, or IV midazolam 0.1–0.2 mg/kg bolus, or IV diazepam up to 0.25–0.4 mg/kg over 2–3 min.",
                    "ICMR STW: out of hospital, buccal or intranasal midazolam 0.3–0.5 mg/kg; the ICMR neuroinfection STW gives 0.2 mg/kg; neither states a maximum.",
                    "For comparison: NCS 2012 caps IM midazolam at 10 mg (AES 2016: single 10 mg if >40 kg) and lists intranasal 0.2 mg/kg and buccal 0.5 mg/kg only as paediatric doses.",
                    "AES 2016: respiratory depression is less frequent with benzodiazepines than with placebo — untreated SE itself causes respiratory compromise (Level A).",
                ),
                doses = listOf(
                    StepDose(
                        "Lorazepam IV — AES 2016",
                        WeightDose(0.1, 0.1, unit = "mg", per = "IV per dose; may repeat once"),
                        note = "NCS 2012: up to 2 mg/min; repeat in 5–10 min",
                        maxTotal = 4.0,
                    ),
                    StepDose(
                        "Diazepam IV — AES 2016",
                        WeightDose(0.15, 0.2, unit = "mg", per = "IV per dose; may repeat once"),
                        note = "NCS 2012: up to 5 mg/min",
                        maxTotal = 10.0,
                    ),
                    StepDose(
                        "Midazolam IM — NCS 2012",
                        WeightDose(0.2, 0.2, unit = "mg", per = "IM single dose"),
                        note = "AES 2016 weight bands: 10 mg if >40 kg; 5 mg if 13–40 kg",
                        maxTotal = 10.0,
                    ),
                    StepDose(
                        "Diazepam rectal — AES 2016 (if IM/IV options unavailable)",
                        WeightDose(0.2, 0.5, unit = "mg", per = "rectal single dose"),
                        maxTotal = 20.0,
                    ),
                ),
                citations = listOf(
                    cite(SE_AES, "Figure 1 — 5–20 min initial therapy phase; Abstract (results)"),
                    cite(SE_NICE, "Rec 7.1.3, 7.1.8"),
                    cite(SE_NCS, "Table 7"),
                    cite(SE_ICMR, "SE algorithm — out of hospital; emergency room"),
                    cite(BM_STW, "Primary care — if seizing"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Second line: IV antiseizure drug",
                timing = "20–40 min (AES 2016)",
                points = listOf(
                    "AES 2016: no evidence-based preferred second therapy (Level U) — fosphenytoin, valproic acid or levetiracetam as a single dose; phenobarbital if none is available.",
                    "ESETT 2019: levetiracetam, fosphenytoin and valproate each stopped benzodiazepine-refractory SE with improved consciousness at 60 min in about half (47%, 45%, 46%).",
                    "ACEP 2024: treat seizures refractory to appropriately dosed benzodiazepines with a second-line agent; fosphenytoin, levetiracetam or valproate have similar efficacy (Level A).",
                    "NICE NG217: after 2 benzodiazepine doses give IV levetiracetam, phenytoin or sodium valproate; levetiracetam may be quicker to give with fewer adverse effects.",
                    "ICMR STW: phenytoin 20 mg/kg at 50 mg/min (+10 mg/kg after 15–20 min if needed); if uncontrolled or contraindicated, valproate 25–40 mg/kg; phenobarbitone 20 mg/kg if both contraindicated.",
                    "ICMR STW alternatives: levetiracetam 20–30 mg/kg IV at 5 mg/kg/min (max 3 g), or lacosamide 200–400 mg IV at 40–80 mg/min.",
                ),
                checklistTitle = "Cautions",
                checklist = listOf(
                    "Phenytoin/fosphenytoin: infuse with cardiac monitoring — QT prolongation and arrhythmias (NCS 2012)",
                    "Phenytoin: hypotension; compatible only with saline; purple glove syndrome (NCS 2012 Table 7)",
                    "Valproate: hyperammonaemia, pancreatitis, thrombocytopenia, hepatotoxicity; use with caution in traumatic head injury (NCS 2012 Table 7)",
                    "Lacosamide: PR prolongation and hypotension (NCS 2012 Table 7)",
                    "Valproate in women of childbearing potential: follow MHRA safety measures (NICE NG217); ICMR STW: avoid in women of childbearing age unless other drugs fail",
                    "Phenobarbital: hypotension and respiratory depression (NCS 2012); be prepared to intubate and ventilate (ICMR STW)",
                ),
                doses = listOf(
                    StepDose(
                        "Levetiracetam IV — AES 2016",
                        WeightDose(60.0, 60.0, unit = "mg", per = "IV single dose"),
                        note = "Same dose and maximum as the ESETT trial arm",
                        maxTotal = 4500.0,
                    ),
                    StepDose(
                        "Fosphenytoin IV — AES 2016",
                        WeightDose(20.0, 20.0, unit = "mg PE", per = "IV single dose"),
                        note = "NCS 2012: up to 150 mg PE/min; cardiac monitoring",
                        maxTotal = 1500.0,
                    ),
                    StepDose(
                        "Valproate IV — AES 2016",
                        WeightDose(40.0, 40.0, unit = "mg", per = "IV single dose"),
                        note = "NCS 2012 and ICMR STW: 3–6 mg/kg/min. ICMR STW dose: 25–40 mg/kg, no maximum stated",
                        maxTotal = 3000.0,
                    ),
                    StepDose(
                        "Phenytoin IV — NCS 2012; ICMR STW",
                        WeightDose(20.0, 20.0, unit = "mg", per = "IV loading dose"),
                        note = "Up to 50 mg/min. NCS: may give 5–10 mg/kg more; ICMR: +10 mg/kg if seizures persist 15–20 min. No maximum stated",
                    ),
                    StepDose(
                        "Phenobarbital IV — AES 2016",
                        WeightDose(15.0, 15.0, unit = "mg", per = "IV single dose"),
                        note = "NCS 2012 and ICMR STW: 20 mg/kg; NCS rate 50–100 mg/min",
                    ),
                    StepDose(
                        "Levetiracetam IV — ICMR STW",
                        WeightDose(20.0, 30.0, unit = "mg", per = "IV at 5 mg/kg/min"),
                        maxTotal = 3000.0,
                    ),
                ),
                citations = listOf(
                    cite(SE_AES, "Figure 1 — 20–40 min second therapy phase"),
                    cite(SE_ESETT, "Abstract — results; Methods — trial treatments"),
                    cite(SE_ACEP, "Refractory seizures — Level A"),
                    cite(SE_NICE, "Rec 7.1.9"),
                    cite(SE_ICMR, "SE algorithm — emergency room; AED table"),
                    cite(SE_NCS, "Table 7; Emergent initial therapy (cardiac monitoring)"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Refractory status: anaesthetic infusions in ICU",
                timing = "40–60 min (AES 2016); refractory at 60 min (ICMR STW)",
                points = listOf(
                    "AES 2016: no clear evidence for the third phase — repeat second-line therapy or anaesthetic doses of thiopental, midazolam, pentobarbital or propofol, all with continuous EEG.",
                    "NICE NG217: if no response, consider an alternative second-line drug under expert guidance, then phenobarbital or general anaesthesia.",
                    "NCS 2012: refractory SE fails a benzodiazepine and another AED; give continuous infusions titrated to cessation of electrographic seizures or burst suppression.",
                    "NCS 2012: early intubation is advisable when continuous IV AEDs are needed; keep 24–48 h of electrographic control before slow withdrawal.",
                    "ICMR STW (ICU): midazolam 0.2 mg/kg then 0.05–0.5 mg/kg/h (up to 2); thiopental 5–7 mg/kg then 3–5 mg/kg/h for only 48 h; propofol 2–5 mg/kg then 1–15 mg/kg/h.",
                    "ICMR STW: super-refractory SE is no control after >24 h; pentobarbital and ketamine are also listed, with EEG monitoring.",
                ),
                doses = listOf(
                    StepDose(
                        "Midazolam load — NCS 2012",
                        WeightDose(0.2, 0.2, unit = "mg", per = "IV at 2 mg/min"),
                    ),
                    StepDose(
                        "Midazolam infusion — NCS 2012",
                        WeightDose(0.05, 2.0, unit = "mg", per = "per hour IV, titrated to EEG"),
                        note = "Breakthrough: 0.1–0.2 mg/kg bolus and raise by 0.05–0.1 mg/kg/h every 3–4 h",
                    ),
                    StepDose(
                        "Propofol load — NCS 2012",
                        WeightDose(1.0, 2.0, unit = "mg", per = "IV loading dose"),
                        note = "Start the infusion at 20 mcg/kg/min; requires mechanical ventilation",
                    ),
                    StepDose(
                        "Propofol infusion — NCS 2012",
                        WeightDose(30.0, 200.0, unit = "mcg", per = "per minute IV, titrated to EEG"),
                        note = "Caution with >80 mcg/kg/min for >48 h (propofol infusion syndrome)",
                    ),
                    StepDose(
                        "Thiopental load — NCS 2012",
                        WeightDose(2.0, 7.0, unit = "mg", per = "IV at ≤50 mg/min"),
                        note = "Requires mechanical ventilation",
                    ),
                    StepDose(
                        "Thiopental infusion — NCS 2012",
                        WeightDose(0.5, 5.0, unit = "mg", per = "per hour IV, titrated to EEG"),
                        note = "Breakthrough: 1–2 mg/kg bolus and raise by 0.5–1 mg/kg/h every 12 h",
                    ),
                ),
                citations = listOf(
                    cite(SE_AES, "Figure 1 — 40–60 min third therapy phase"),
                    cite(SE_NICE, "Rec 7.1.10–7.1.11"),
                    cite(SE_NCS, "Summary recommendations for SE definition, 3; summary of treatment recommendations c–f; Table 8; Treatment of SE"),
                    cite(SE_ICMR, "SE algorithm — ICU; super-refractory"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Non-convulsive status and EEG",
                timing = "cEEG within 1 h if ongoing seizures are suspected (NCS 2012)",
                points = listOf(
                    "NCS 2012: non-convulsive SE is seizure activity on EEG without the clinical findings of convulsive SE.",
                    "NCS 2012: no return to baseline >10 min after a clinical seizure or SE — 18–50% have ongoing non-convulsive status despite cessation of motor activity.",
                    "NCS 2012: start continuous EEG within 1 h of SE onset if ongoing seizures are suspected; monitor comatose patients for at least 48 h.",
                    "NCS 2012: cEEG is also indicated in coma (including after cardiac arrest), intracranial haemorrhage (TBI, SAH, ICH) and suspected seizures with altered mental status.",
                    "NCS 2012: bolus valproate, levetiracetam or (fos)phenytoin may be considered in NCSE if haemodynamically stable and not intubated.",
                    "ICMR STW: taper ICU infusions gradually after seizures stop, preferably as evidenced by EEG.",
                ),
                citations = listOf(
                    cite(SE_NCS, "Summary recommendations for SE definition, 2; Table 10; Summary of cEEG recommendations 1–3; Treatment of refractory SE"),
                    cite(SE_ICMR, "SE algorithm — ICU"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Special situations",
                points = listOf(
                    "NICE NG217: eclampsia can cause status epilepticus. NCS 2012: in eclampsia, magnesium sulfate is superior to antiseizure drugs such as phenytoin — see the Eclampsia protocol.",
                    "NCS 2012: in pregnancy, delivery is the best therapy for eclampsia; lorazepam and fosphenytoin are recommended for SE, and levetiracetam should be considered.",
                    "NICE NG217: consider hypoglycaemia, alcohol withdrawal and non-adherence. NCS 2012 also lists drug toxicity, withdrawal, CNS infection, stroke and head trauma.",
                    "NCS 2012: toxins that often cause seizures include isoniazid, tricyclic antidepressants, theophylline, cocaine, sympathomimetics, alcohol and organophosphates.",
                    "ICMR neuroinfection STW: altered sensorium with fever or seizures — consider meningoencephalitis and cerebral malaria (see Bacterial meningitis).",
                ),
                citations = listOf(
                    cite(SE_NICE, "Rec 7.1.4–7.1.5"),
                    cite(SE_NCS, "Special circumstances (pregnancy); Table 2; Table 3"),
                    cite(BM_STW, "Differential diagnosis; secondary care"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "After the seizures stop",
                points = listOf(
                    "NCS 2012: all patients need urgent-control (second-line) and maintenance AED therapy even if SE stops, unless a cause such as hypoglycaemia is definitively corrected.",
                    "NCS 2012 work-up: glucose, CBC, metabolic panel, calcium, magnesium, AED levels, head CT for most and continuous EEG; LP if CNS infection is suspected.",
                    "ICMR STW: refer status epilepticus after stabilisation; inform the consultant on call; keep a high threshold for invasive procedures.",
                    "NICE NG217: after convulsive SE, agree an individualised emergency management plan if SE may recur.",
                ),
                citations = listOf(
                    cite(SE_NCS, "Treatment of SE; Urgent control therapy; Table 3"),
                    cite(SE_ICMR, "Reasons for referral; SE algorithm footer"),
                    cite(SE_NICE, "Rec 7.1.12"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "ICMR STW IV diazepam is higher than AES 2016 (0.25–0.4 mg/kg vs 0.15–0.2 mg/kg, max 10 mg). ICMR IV lorazepam (up to 0.1 mg/kg) matches AES (0.1 mg/kg) but has no 4 mg cap. ICMR buccal/intranasal midazolam is 0.3–0.5 mg/kg; AES lists these routes (Level B) without a dose. ICMR states no maxima, so these are shown as text, not calculator doses.",
                listOf(cite(SE_ICMR, "SE algorithm"), cite(SE_AES, "Figure 1")),
            ),
            ProtocolNote(
                "Levetiracetam differs: ICMR STW 20–30 mg/kg (max 3 g) as an alternative after phenytoin, valproate and phenobarbitone; AES 2016 and ESETT 60 mg/kg (max 4500 mg) with no preferred second-line drug.",
                listOf(cite(SE_ICMR, "SE algorithm"), cite(SE_AES, "Figure 1"), cite(SE_ESETT, "Methods — trial treatments")),
            ),
            ProtocolNote(
                "Propofol differs: ICMR STW load 2–5 mg/kg and infusion 1–15 mg/kg/h (up to 250 mcg/kg/min). NCS 2012: load 1–2 mg/kg, infusion 30–200 mcg/kg/min (1.8–12 mg/kg/h), caution above 80 mcg/kg/min (4.8 mg/kg/h) for >48 h (propofol infusion syndrome), and hypotension especially with the loading dose in critically ill patients.",
                listOf(cite(SE_ICMR, "SE algorithm — ICU"), cite(SE_NCS, "Table 8")),
            ),
            ProtocolNote(
                "Second-line evidence: ESETT found no drug superior. AES 2016: fosphenytoin preferred over phenytoin for tolerability when both are available; in adults each later therapy is less effective (Level A).",
                listOf(cite(SE_ESETT, "Abstract — conclusions"), cite(SE_AES, "Abstract — results")),
            ),
            ProtocolNote(
                "Timelines differ: AES 2016 phases at 5, 20, 40 and 60 min; ICMR STW calls SE 'impending' at 5 min, 'established' at 30 min and 'refractory' at 60 min after a benzodiazepine and an IV AED fail.",
                listOf(cite(SE_AES, "Figure 1"), cite(SE_ICMR, "SE timeline")),
            ),
            ProtocolNote(
                "Scope: adult doses. NICE NG217 gives no doses; doses here come from AES 2016, NCS 2012 and ICMR STW. ILAE time points are used for convulsive SE only.",
                listOf(cite(SE_NICE, "Rec 7.1.3, 7.1.9"), cite(SE_ILAE, "Abstract")),
            ),
        ),
        citations = listOf(
            cite(SE_ICMR), cite(SE_AES), cite(SE_NICE), cite(SE_NCS), cite(SE_ACEP), cite(SE_ESETT), cite(SE_ILAE),
        ),
    ),

    // =========================================================================================
    // Acute migraine in the emergency department
    // =========================================================================================
    Protocol(
        id = "migraine-acute",
        category = ProtocolCategory.Neuro,
        title = "Acute migraine in the ED",
        summary = "Red flags for secondary headache, parenteral options graded by the AHS 2025 update, SC sumatriptan " +
            "dose and contraindications, what to avoid, recurrence and disposition — attributed to AHS, NICE, ICMR and " +
            "Indian guidance.",
        keywords = listOf(
            "migraine", "acute migraine", "migraine attack", "headache", "severe headache", "sir dard", "aadhasisi",
            "status migrainosus", "red flags headache", "secondary headache", "SNNOOP10", "prochlorperazine",
            "Stemetil", "metoclopramide", "Perinorm", "sumatriptan", "Suminat", "triptan", "ketorolac",
            "dexketoprofen", "dexamethasone", "chlorpromazine", "occipital nerve block", "GONB", "nerve block",
            "opioids", "hydromorphone",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Screen for a secondary headache",
                timing = "At triage",
                points = listOf(
                    "NICE CG150: evaluate and consider investigation for worsening headache with fever, onset peaking within 5 min, new neurological deficit, cognitive or personality change, or impaired consciousness.",
                    "NICE CG150 also lists head trauma within 3 months, cough-, exercise- or posture-related headache, giant cell arteritis and acute narrow-angle glaucoma.",
                    "ICMR STW red flags: first or worst headache, thunderclap onset, fever with neck stiffness, altered sensorium, focal signs, new severe headache in pregnancy or postpartum.",
                    "ICMR STW: headaches with danger signs should be referred immediately and investigated for potentially dangerous conditions.",
                    "SNNOOP10 (Neurology 2019): a systematic red-flag list for screening new headache patients for a secondary cause.",
                ),
                checklistTitle = "SNNOOP10 red flags",
                checklist = listOf(
                    "Systemic symptoms, including fever",
                    "Neoplasm history",
                    "Neurological deficit, including decreased consciousness",
                    "Onset sudden or abrupt",
                    "Older age — onset after 65 years",
                    "Pattern change or recent onset of new headache",
                    "Positional headache",
                    "Precipitated by sneezing, coughing or exercise",
                    "Papilloedema",
                    "Progressive headache and atypical presentations",
                    "Pregnancy or puerperium",
                    "Painful eye with autonomic features",
                    "Post-traumatic onset",
                    "Pathology of the immune system, such as HIV",
                    "Painkiller overuse or a new drug at onset",
                ),
                citations = listOf(
                    cite(MG_NICE, "Rec 1.1.1–1.1.2"),
                    cite(MG_ICMR, "Red flag signs; Reasons for referral"),
                    cite(MG_SNOOP, "Abstract (list of red flags)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Confirm migraine",
                points = listOf(
                    "NICE CG150: migraine — unilateral or bilateral pulsating pain, moderate or severe, aggravated by routine activity, with light/sound sensitivity or nausea/vomiting.",
                    "NICE CG150: typical aura is fully reversible, develops over at least 5 min and lasts 5–60 min (visual, sensory or speech symptoms).",
                    "NICE CG150: investigate or refer atypical aura — motor weakness, double vision, one-eye visual symptoms, poor balance or decreased consciousness.",
                    "ICMR STW: atypical migraine (hemiplegia, visual impairment, vertigo) — refer to a centre with imaging; CT/MRI with angiography if required.",
                    "AHS Choosing Wisely 2013: don't image stable headaches that meet migraine criteria. NICE CG150: no neuroimaging solely for reassurance.",
                ),
                citations = listOf(
                    cite(MG_NICE, "Rec 1.2.1 (Table 1), 1.2.2–1.2.4, 1.3.3"),
                    cite(MG_ICMR, "Atypical migraine"),
                    cite(MG_CW, "Recommendation 1"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Parenteral treatment",
                points = listOf(
                    "AHS 2025: must offer (Level A) — IV prochlorperazine and greater occipital nerve block, in eligible adults without contraindications.",
                    "AHS 2025: should offer (Level B) — IV dexketoprofen, IV ketorolac, IV metoclopramide, SC sumatriptan and supraorbital nerve block.",
                    "AHS 2025: may offer (Level C) — IV chlorpromazine, IV dexamethasone, IV valproate. Eptinezumab: no recommendation for an ED population.",
                    "Valproate in women of childbearing potential or pregnancy: follow MHRA safety measures (NICE NG217, which notes they apply to IV valproate).",
                    "NICE CG150: consider an antiemetic even without nausea; if oral treatment fails, non-oral metoclopramide or prochlorperazine, adding a non-oral NSAID or triptan.",
                    "NICE CG150: note the SPC special warnings and precautions for metoclopramide and prochlorperazine, and discuss benefits and risks.",
                    "Prochlorperazine injection label (nausea/vomiting): IV 2.5–10 mg slowly at ≤5 mg/min, not as a bolus; single dose ≤10 mg, ≤40 mg/day; hypotension possible.",
                    "ICMR STW (primary care): paracetamol 650 mg or indomethacin 50 mg, with prochlorperazine 10 mg as antiemetic.",
                ),
                checklistTitle = "Prochlorperazine injection cautions (label)",
                checklist = listOf(
                    "Do not use in comatose states, with large amounts of CNS depressants (alcohol, barbiturates, opioids) or with phenothiazine hypersensitivity",
                    "Keep the patient lying down and observe for at least 30 min after injection (hypotension)",
                    "If a vasopressor is needed use noradrenaline or phenylephrine, not adrenaline (further fall in BP)",
                    "Extrapyramidal reactions (dystonia, akathisia) can occur: treat with injectable diphenhydramine, a barbiturate or an antiparkinsonism drug (not levodopa)",
                ),
                citations = listOf(
                    cite(MG_AHS25, "Abstract — conclusions"),
                    cite(SE_NICE, "Rec 7.1.9 and rationale"),
                    cite(MG_NICE, "Rec 1.3.13–1.3.14"),
                    cite(MG_PCZ, "Dosage and administration — severe nausea and vomiting; Contraindications; Precautions; Adverse reactions; Overdosage"),
                    cite(MG_ICMR, "Migraine — acute management"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Subcutaneous sumatriptan",
                points = listOf(
                    "Sumatriptan injection label: 6 mg SC (4 mg if side effects are dose-limiting); maximum 12 mg in 24 h, as two 6-mg doses at least 1 h apart.",
                    "Label: consider a second dose only if the first gave some response; subcutaneous use only. No response to the first treated attack — reconsider the diagnosis.",
                    "AIAN 2011: SC sumatriptan 6 mg (½ mL) for severe attacks with vomiting; injection and nasal sumatriptan are available in India.",
                    "AHS 2025: SC sumatriptan — should offer (Level B), as in AHS 2016.",
                ),
                checklistTitle = "Contraindications and warnings (label)",
                checklist = listOf(
                    "No prior migraine diagnosis or atypical symptoms: exclude other serious neurological conditions first (label §5.4)",
                    "Triptan-naive with multiple CV risk factors (age, diabetes, hypertension, smoking, obesity, family history of CAD): cardiovascular evaluation first; if negative, consider giving the first dose under supervision with an ECG straight after (label §5.1)",
                    "Serotonin syndrome risk with SSRIs, SNRIs, TCAs or MAO inhibitors — stop if suspected (label §5.7)",
                    "Ischaemic coronary artery disease or coronary vasospasm, including Prinzmetal's angina",
                    "Wolff–Parkinson–White or other accessory-pathway arrhythmias",
                    "History of stroke or TIA; hemiplegic or basilar migraine",
                    "Peripheral vascular disease or ischaemic bowel disease",
                    "Uncontrolled hypertension",
                    "Ergotamine-type drug or another triptan within 24 h",
                    "MAO-A inhibitor now or within 2 weeks",
                    "Severe hepatic impairment",
                    "Hypersensitivity to sumatriptan (angioedema, anaphylaxis)",
                ),
                citations = listOf(
                    cite(MG_SUMA, "§1 Limitations of use; §2.1; §4; §5.1, §5.4, §5.7"),
                    cite(MG_AIAN, "Migraine — specific medications for acute treatment (triptans)"),
                    cite(MG_AHS25, "Abstract — conclusions"),
                    cite(MG_AHS16, "Abstract — recommendations"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "What to avoid",
                points = listOf(
                    "AHS 2025: IV hydromorphone must not be offered (Level A); IV paracetamol should not be offered for migraine pain (Level C).",
                    "AHS 2016: injectable morphine and hydromorphone are best avoided as first-line therapy (Level C).",
                    "NICE CG150: do not offer ergots or opioids for the acute treatment of migraine.",
                    "AHS Choosing Wisely 2013: don't prescribe opioid- or butalbital-containing medicines as first-line treatment for recurrent headache disorders.",
                    "AIAN 2011: parenteral opiates only rarely as rescue therapy; butalbital and opioid analgesics are not easily available in India.",
                    "NICE CG150: suspect medication-overuse headache with triptans, opioids, ergots or combination analgesics ≥10 days/month, or simple analgesics ≥15 days/month, for ≥3 months.",
                ),
                citations = listOf(
                    cite(MG_AHS25, "Abstract — conclusions"),
                    cite(MG_AHS16, "Abstract — recommendations"),
                    cite(MG_NICE, "Rec 1.3.16, 1.2.7"),
                    cite(MG_CW, "Recommendation 4"),
                    cite(MG_AIAN, "Migraine — opiate analgesics; combination analgesics"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Recurrence and disposition",
                points = listOf(
                    "AHS 2016: dexamethasone should be offered to prevent headache recurrence (Level B). AHS 2025 abstract lists IV dexamethasone as 'may offer' (Level C).",
                    "AIAN 2011: corticosteroids (dexamethasone or hydrocortisone) are options for rescue therapy in status migrainosus.",
                    "ICMR STW admit: unrelenting headache; immunosuppressed with continuous headache; worsening first-ever headache; progressive headache with systemic disease; severe primary headache.",
                    "ICMR STW: a primary headache that has improved symptomatically can be discharged.",
                    "ICMR STW: migraine >2 attacks/month — 2-month prophylaxis trial with propranolol 40 mg or flunarizine 10 mg once daily, plus lifestyle advice.",
                ),
                citations = listOf(
                    cite(MG_AHS16, "Abstract — recommendations"),
                    cite(MG_AHS25, "Abstract — conclusions"),
                    cite(MG_AIAN, "Migraine — other medications"),
                    cite(MG_ICMR, "Indications for admission; Criteria for discharge; Migraine"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "AHS 2025 (Headache 2026) updates the 2016 ED assessment: IV prochlorperazine rises to Level A, nerve blocks are added and hydromorphone becomes 'must not offer'. Only the abstract was reviewed; its doses are not shown.",
                listOf(cite(MG_AHS25, "Abstract"), cite(MG_AHS16, "Abstract")),
            ),
            ProtocolNote(
                "Dexamethasone: AHS 2016 'should offer' to prevent recurrence (Level B); the AHS 2025 abstract grades IV dexamethasone Level C — check the full text for the recurrence recommendation.",
                listOf(cite(MG_AHS16, "Abstract — recommendations"), cite(MG_AHS25, "Abstract — conclusions")),
            ),
            ProtocolNote(
                "Paracetamol: ICMR STW lists oral paracetamol 650 mg for acute migraine in primary care; AHS 2025 advises against IV paracetamol for migraine pain in the ED (Level C).",
                listOf(cite(MG_ICMR, "Migraine — acute management"), cite(MG_AHS25, "Abstract — conclusions")),
            ),
            ProtocolNote(
                "Licensing (UK, June 2025, per NICE CG150): only buccal prochlorperazine is licensed for migraine; other prochlorperazine and metoclopramide forms are licensed for nausea and vomiting.",
                listOf(cite(MG_NICE, "Rec 1.3.14 (footnote)")),
            ),
            ProtocolNote(
                "Pregnancy or puerperium is a red flag (SNNOOP10; ICMR STW). For headache in pregnancy see the 'Headache & migraine' page under Pregnancy.",
                listOf(cite(MG_SNOOP, "Abstract"), cite(MG_ICMR, "Red flag signs")),
            ),
        ),
        citations = listOf(
            cite(MG_ICMR), cite(MG_AIAN), cite(MG_AHS25), cite(MG_AHS16), cite(MG_NICE), cite(MG_SNOOP), cite(MG_CW),
            cite(MG_SUMA), cite(MG_PCZ), cite(SE_NICE),
        ),
    ),

    // =========================================================================================
    // Bacterial meningitis
    // =========================================================================================
    Protocol(
        id = "bacterial-meningitis",
        category = ProtocolCategory.Neuro,
        title = "Bacterial meningitis",
        summary = "Suspicion, cultures and antibiotics within the hour, when CT should precede LP, empirical antibiotics " +
            "with Listeria cover, adjunctive dexamethasone, duration and contact prophylaxis — attributed to ICMR, " +
            "WHO 2025, NICE NG240, ESCMID and IDSA.",
        keywords = listOf(
            "meningitis", "bacterial meningitis", "pyogenic meningitis", "ABM", "acute bacterial meningitis",
            "meningococcal", "meningococcal disease", "meningococcaemia", "purpuric rash", "non-blanching rash",
            "brain fever", "dimagi bukhar", "neck stiffness", "neck rigidity", "meningism", "lumbar puncture", "LP",
            "CSF", "ceftriaxone", "vancomycin", "ampicillin", "Listeria", "dexamethasone", "meningoencephalitis",
            "acute febrile encephalopathy", "AFE", "acute encephalitis syndrome", "chemoprophylaxis", "ciprofloxacin",
            "contacts", "aciclovir", "acyclovir",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Suspect meningitis",
                timing = "At first contact",
                points = listOf(
                    "NICE NG240: strongly suspect bacterial meningitis with fever, headache, neck stiffness and altered consciousness — it can still be strongly suspected without all four.",
                    "NICE NG240: strongly suspect meningococcal disease with purpura (>2 mm), a spreading non-blanching rash, or meningitis signs plus such a rash; no rash does not exclude it.",
                    "ICMR 2019: classic triad — fever, neck stiffness, altered sensorium. ICMR STW differential: stroke, infective (including protozoal) meningoencephalitis or abscess, metabolic encephalopathy, postictal state, intoxication.",
                    "WHO 2025: admit immediately or transfer urgently to a facility able to do lumbar puncture and manage severe illness.",
                    "NICE NG240: don't delay transfer for antibiotics; if transfer will be significantly delayed, give IV or IM ceftriaxone or benzylpenicillin first — but give no antibiotics before hospital if there is severe allergy to either.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(BM_NICE, "Rec 1.1.4–1.1.5, 1.1.9–1.1.10, 1.2.3–1.2.4, 1.2.6"),
                    cite(BM_ICMR, "§8.2.1 (p. 78)"),
                    cite(BM_STW, "Differential diagnosis"),
                    cite(BM_WHO, "§B.1"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Cultures, then antibiotics without delay",
                timing = "Antibiotics within 1 h of arrival (NICE, ESCMID); within 30 min (ICMR STW)",
                points = listOf(
                    "NICE NG240: a senior decision-maker ensures antibiotics start within 1 hour of arrival; bloods and LP first only if safe and without significant delay.",
                    "ESCMID 2016: antibiotics within 1 hour; if LP is delayed (e.g. for CT), take blood cultures and start empirical treatment immediately (Grade A).",
                    "WHO 2025: empirical IV antibiotics as early as possible — the '1-hour window' is the golden period; tests must not delay treatment.",
                    "ICMR STW: empirical antibiotics within 30 minutes of arrival. ICMR 2019: two sets of blood cultures; if LP cannot be done at once, culture and treat.",
                    "NICE NG240: blood culture, white cell count, CRP (or PCT), glucose, meningococcal/pneumococcal PCR, HIV test, and throat swab for meningococcal culture.",
                    "NICE NG240: a normal CRP, PCT or white cell count does not rule out bacterial meningitis.",
                ),
                citations = listOf(
                    cite(BM_NICE, "Rec 1.4.1, 1.4.3–1.4.5"),
                    cite(BM_ESCMID, "Key question 4 — recommendations"),
                    cite(BM_WHO, "§B.2.1"),
                    cite(BM_STW, "Tertiary care — empirical antibiotic"),
                    cite(BM_ICMR, "§8.2 investigations and diagnosis (p. 79)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "CT before lumbar puncture?",
                timing = "Never delay antibiotics for imaging",
                points = listOf(
                    "NICE NG240 and WHO 2025: do not routinely image before lumbar puncture.",
                    "NICE NG240: image and defer LP for new focal signs (including seizures or posturing), abnormal pupils, GCS ≤9, falling consciousness, or risk of a space-occupying lesion.",
                    "WHO 2025: where imaging is not readily accessible, defer LP while these features persist — treatment must not wait.",
                    "ICMR 2019: image before LP with focal deficit, papilloedema, immunocompromise or raised ICP; avoid LP with platelets <30,000, local infection or cardiorespiratory compromise.",
                    "NICE NG240: take bloods, give antibiotics and stabilise before imaging; no LP with extensive or spreading purpura or infection at the site.",
                ),
                checklistTitle = "Image before LP if any (WHO 2025, ESCMID 2016)",
                checklist = listOf(
                    "GCS below 10 (NICE NG240: 9 or less, or falling)",
                    "Focal neurological signs (ESCMID excludes cranial nerve palsies)",
                    "Cranial nerve deficit (WHO 2025)",
                    "Papilloedema (WHO 2025; IDSA 2004)",
                    "New-onset seizures",
                    "Severe immunocompromise",
                    "Abnormal pupillary reactions (NICE NG240)",
                    "History of CNS disease — mass lesion, stroke, focal infection (IDSA 2004)",
                ),
                citations = listOf(
                    cite(BM_NICE, "Rec 1.4.6–1.4.8, 1.4.12"),
                    cite(BM_WHO, "§A.4"),
                    cite(BM_ESCMID, "Key question 4 — cranial imaging"),
                    cite(BM_ICMR, "§8.1 algorithm footnote (p. 78)"),
                    cite(BM_IDSA, "Table 2"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Lumbar puncture and CSF",
                points = listOf(
                    "NICE NG240: first stabilise airway, breathing, shock, seizures and bleeding risk; measure blood glucose just before LP for the CSF:blood ratio.",
                    "NICE NG240: CSF cell count and differential, protein, glucose, Gram stain, culture and PCR; store spare CSF; results within 4 hours.",
                    "ICMR 2019: examine CSF within 30–60 min; if delayed keep at room temperature, never refrigerate; multiplex PCR panels if available.",
                    "WHO 2025: in adults >5 leukocytes/µL is abnormal; CSF:blood glucose ≤0.4 is low; protein >45 mg/dL is raised; bacterial CSF is usually turbid.",
                    "NICE NG240: if antibiotics came first, do LP as soon as safe; with abnormal CSF also consider viral, mycobacterial, fungal and non-infectious causes.",
                ),
                citations = listOf(
                    cite(BM_NICE, "Rec 1.4.10–1.4.11, 1.4.13–1.4.16, 1.4.19"),
                    cite(BM_ICMR, "§8.2 investigations and diagnosis (p. 79)"),
                    cite(BM_WHO, "§A.2.1–A.2.2"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Empirical antibiotics",
                timing = "Immediately after cultures",
                points = listOf(
                    "WHO 2025: IV ceftriaxone or cefotaxime; add IV ampicillin or amoxicillin for Listeria risk; add vancomycin where pneumococcal penicillin or cephalosporin resistance is common.",
                    "ICMR 2019: 1 month–50 y — ceftriaxone plus vancomycin; >50 y, alcoholism or impaired cell-mediated immunity — add ampicillin; alternative meropenem plus vancomycin.",
                    "ICMR 2019: pneumococcal cephalosporin resistance in Indian CSF isolates (Vellore) rose from 4.7% in 2008 to 28.5% in 2016.",
                    "Adult doses — ICMR 2019: ceftriaxone 2 g 12-hourly; meropenem 2 g 8-hourly. ESCMID 2016: ceftriaxone 2 g q12h or 4 g q24h; ampicillin 2 g q4h.",
                    "ICMR STW first dose: ceftriaxone 2 g + vancomycin 500 mg, + ampicillin 2 g if >50 y or immunocompromised, + dexamethasone 8 mg IV.",
                    "NICE NG240: no routine aciclovir unless HSV encephalitis is strongly suspected. ICMR 2019: treat confirmed HSV for 14–21 days.",
                ),
                checklistTitle = "Listeria risk — add ampicillin or amoxicillin (WHO 2025)",
                checklist = listOf(
                    "Age over 60 (ICMR 2019, ESCMID 2016 and IDSA 2004: over 50)",
                    "Pregnancy",
                    "Immunosuppressive therapy or organ transplantation",
                    "Malignancy",
                    "Advanced HIV disease",
                    "Diabetes mellitus",
                    "End-stage kidney disease",
                    "Liver cirrhosis",
                    "Alcohol use disease",
                ),
                doses = listOf(
                    StepDose(
                        "Vancomycin — ICMR 2019",
                        WeightDose(15.0, 15.0, unit = "mg", per = "IV 8-hourly"),
                        note = "Maximum 2 g per dose",
                        maxTotal = 2000.0,
                    ),
                    StepDose(
                        "Vancomycin — ESCMID 2016",
                        WeightDose(10.0, 20.0, unit = "mg", per = "IV every 8–12 h"),
                        note = "Target serum trough 15–20 µg/mL",
                    ),
                    StepDose(
                        "Aciclovir — ICMR 2019 (if HSV encephalitis is suspected)",
                        WeightDose(10.0, 10.0, unit = "mg", per = "IV 8-hourly"),
                    ),
                ),
                citations = listOf(
                    cite(BM_WHO, "§B.2.2"),
                    cite(BM_ICMR, "§8.2.2; Table 8.3 (p. 80); Table 8.6 (pp. 83–84); §8.1 footnote (HSV)"),
                    cite(BM_ESCMID, "Table 4.1"),
                    cite(BM_STW, "Secondary care; tertiary care — empirical antibiotic"),
                    cite(BM_NICE, "Rec 1.6.5–1.6.7"),
                    cite(BM_IDSA, "Table 4"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Adjunctive dexamethasone",
                timing = "With or just before the first antibiotic dose",
                points = listOf(
                    "ESCMID 2016: adults — dexamethasone 10 mg 6-hourly for 4 days, starting with the first antibiotic dose (Grade A), or up to 4 h after it (Grade C).",
                    "ICMR 2019: give 15–20 min before the first antibiotic; 0.15 mg/kg 6-hourly (10 mg in adults) for 48–96 h; at least one dose is acceptable pending CSF.",
                    "ICMR STW: dexamethasone 8 mg IV with the first antibiotic dose; stop after 48 h unless another indication (adrenal insufficiency, TB meningitis).",
                    "WHO 2025: start with the first antibiotic dose; stop if CSF is not bacterial; at most 4 days; may stop if the pathogen is not pneumococcus or Hib.",
                    "NICE NG240: if delayed <12 h after antibiotics, give as soon as possible; if >12 h, seek specialist advice. Continue for pneumococcus or Hib; stop for other organisms.",
                    "WHO 2025: no corticosteroids in cerebral malaria; no proven benefit in advanced HIV disease. NICE NG240: not routinely in meningococcal disease.",
                ),
                citations = listOf(
                    cite(BM_ESCMID, "Key question 6 — recommendations"),
                    cite(BM_ICMR, "§8.2 (p. 80)"),
                    cite(BM_STW, "Tertiary care — empirical antibiotic"),
                    cite(BM_WHO, "§B.3"),
                    cite(BM_NICE, "Rec 1.8.1, 1.8.3, 1.8.5–1.8.6"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Supportive care",
                points = listOf(
                    "ICMR STW: intubate if GCS <8, impaired airway reflexes, abnormal breathing, signs of raised ICP, SpO₂ <92% on high-flow oxygen, or fluid-refractory shock.",
                    "ICMR STW: thiamine 100 mg IV; treat hypoglycaemia with 100 mL of 25% dextrose IV; seizures — lorazepam 0.1 mg/kg, then phenytoin 20 mg/kg at 50 mg/min.",
                    "NICE NG240: do not routinely restrict fluids below maintenance; no glycerol; no routine mannitol or hypertonic saline.",
                    "NICE NG240: with raised ICP and concern about herniation, consider an osmotic agent as a temporary measure and get urgent critical care advice.",
                    "ICMR STW: mannitol and steroids are listed as not recommended at primary care — refer urgently to a centre with intensive care.",
                    "ICMR STW: if cerebral malaria is possible, start IV artesunate 2.4 mg/kg (or quinine 20 mg/kg).",
                ),
                doses = listOf(
                    StepDose(
                        "Artesunate — ICMR 2019 (if cerebral malaria is possible)",
                        WeightDose(2.4, 2.4, unit = "mg", per = "IV at 0, 12 and 24 h, then every 24 h"),
                    ),
                ),
                citations = listOf(
                    cite(BM_STW, "Primary care; secondary care"),
                    cite(BM_NICE, "Rec 1.9.1, 1.9.3–1.9.5"),
                    cite(BM_ICMR, "Table 8.6 (p. 83)"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Targeted therapy, duration and contacts",
                points = listOf(
                    "WHO 2025: pneumococcus 10–14 days, meningococcus 5–7, H. influenzae 7–10, Listeria 21. ICMR 2019: meningococcus and H. influenzae 7, Listeria 3–6 weeks.",
                    "ICMR 2019: stop vancomycin if the isolate is cephalosporin-susceptible; repeat CSF if no response at 48 h. NICE NG240: CSF-positive, culture/PCR-negative — 10 days.",
                    "WHO 2025: single-dose ceftriaxone or oral ciprofloxacin for close contacts of meningococcal disease, as soon as possible (little benefit after 14 days); rifampicin if neither can be given.",
                    "WHO 2025 close contacts: <1 m prolonged exposure (e.g. household) or oral-secretion exposure (kissing, mouth-to-mouth, intubation), 7 days before onset to 24 h after antibiotics.",
                    "ESCMID 2016 adult prophylaxis: ciprofloxacin 500 mg orally once (not in pregnancy), ceftriaxone 250 mg IM once, or rifampicin 600 mg twice daily for 2 days.",
                    "NICE NG240: review adults 4–6 weeks after discharge, including audiology. ESCMID 2016: pneumococcal vaccine after pneumococcal meningitis (Grade B).",
                ),
                citations = listOf(
                    cite(BM_WHO, "Table 3.2; §B.2.5"),
                    cite(BM_ICMR, "§8.2 (pp. 80–81)"),
                    cite(BM_NICE, "Rec 1.6.9, 1.13.2"),
                    cite(BM_ESCMID, "Table 4.2; Table 4.3; Key question 8 — recommendations"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Antibiotic timing: ICMR STW within 30 min of arrival; NICE NG240 and ESCMID 2016 within 1 h; WHO 2025 as early as possible.",
                listOf(cite(BM_STW, "Tertiary care"), cite(BM_NICE, "Rec 1.4.1"), cite(BM_ESCMID, "Key question 4"), cite(BM_WHO, "§B.2.1")),
            ),
            ProtocolNote(
                "Vancomycin differs: ICMR STW first dose 500 mg; ICMR 2019 15 mg/kg (max 2 g) 8-hourly; ESCMID 10–20 mg/kg q8–12h to a trough of 15–20 µg/mL; IDSA 2004 30–45 mg/kg/day in 2–3 doses.",
                listOf(cite(BM_STW, "Secondary care"), cite(BM_ICMR, "Table 8.6"), cite(BM_ESCMID, "Table 4.1"), cite(BM_IDSA, "Table 6")),
            ),
            ProtocolNote(
                "Dexamethasone differs: ICMR STW 8 mg, stop at 48 h; ICMR 2019 10 mg 6-hourly for 48–96 h; ESCMID 10 mg 6-hourly for 4 days; WHO 2025 at most 4 days.",
                listOf(cite(BM_STW, "Tertiary care"), cite(BM_ICMR, "§8.2 (p. 80)"), cite(BM_ESCMID, "Key question 6"), cite(BM_WHO, "§B.3")),
            ),
            ProtocolNote(
                "Steroid evidence in low-resource settings is contested: ESCMID found no benefit in studies from low-income countries; WHO 2025 found no mortality difference by income group but less effect on neurological sequelae in LMICs.",
                listOf(cite(BM_ESCMID, "Key question 6 — conclusions"), cite(BM_WHO, "§B.3 — justification")),
            ),
            ProtocolNote(
                "Listeria cover from age over 60 (WHO 2025) or over 50 (ICMR 2019, ESCMID 2016, IDSA 2004). Imaging thresholds: NICE NG240 GCS ≤9; WHO and ESCMID GCS <10.",
                listOf(cite(BM_WHO, "§B.2.2; §A.4"), cite(BM_ICMR, "Table 8.3"), cite(BM_ESCMID, "Table 4.1"), cite(BM_IDSA, "Table 4"), cite(BM_NICE, "Rec 1.4.7")),
            ),
            ProtocolNote(
                "Scope: community-acquired bacterial meningitis in adults. Tuberculous meningitis (listed by ICMR 2019 among causes of acute febrile encephalopathy) and healthcare-associated meningitis are not covered.",
                listOf(cite(BM_ICMR, "§8.1; §8.3")),
            ),
        ),
        citations = listOf(
            cite(BM_STW), cite(BM_ICMR), cite(BM_WHO), cite(BM_NICE), cite(BM_ESCMID), cite(BM_IDSA),
        ),
    ),

    // =========================================================================================
    // Subarachnoid haemorrhage
    // =========================================================================================
    Protocol(
        id = "subarachnoid-haemorrhage",
        category = ProtocolCategory.Neuro,
        title = "Subarachnoid haemorrhage",
        summary = "Thunderclap headache, the Ottawa SAH Rule, CT and LP timing, urgent neurosurgical referral, " +
            "rebleeding prevention, nimodipine, seizures and hydrocephalus — attributed to AHA/ASA 2023, NICE NG228 " +
            "and ICMR.",
        keywords = listOf(
            "subarachnoid haemorrhage", "subarachnoid hemorrhage", "SAH", "aSAH", "aneurysm", "ruptured aneurysm",
            "berry aneurysm", "thunderclap headache", "worst headache of life", "sudden severe headache",
            "brain haemorrhage", "brain hemorrhage", "brain bleed", "nimodipine", "xanthochromia", "lumbar puncture",
            "CT angiography", "CTA", "DSA", "coiling", "clipping", "vasospasm", "DCI", "delayed cerebral ischaemia",
            "Ottawa SAH rule", "hydrocephalus", "EVD",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise thunderclap headache",
                timing = "Urgent senior review (NICE NG228)",
                points = listOf(
                    "NICE NG228: thunderclap headache — sudden, severe, peaking within 1–5 min — is a red flag for SAH, though most such patients do not have SAH.",
                    "NICE NG228: other features — neck pain or stiffness, photophobia, nausea and vomiting, reduced consciousness, seizure, focal deficit, painful neck flexion.",
                    "AHA/ASA 2023: a sentinel headache precedes 10–43% of presentations; prompt work-up of acute severe headache is recommended (COR 1).",
                    "ICMR STW: sudden severe or first/worst headache of life — CT with CT angiography, or MRI with MR angiography, at a tertiary centre.",
                    "AIAN 2011: all thunderclap headaches must be referred urgently to specialised units for full work-up and treatment.",
                    "NICE NG228: give effective analgesia, including opioids if needed; allow for their sedating and pupillary effects in neurological checks.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(SAH_NICE, "Rec 1.1.2–1.1.3, 1.1.6, 1.1.8–1.1.9"),
                    cite(SAH_AHA, "§4 Synopsis; Rec 1"),
                    cite(MG_ICMR, "Treatment of major catastrophic headaches at tertiary centre"),
                    cite(MG_AIAN, "Primary thunderclap headache — treatment"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Ottawa SAH Rule",
                points = listOf(
                    "AHA/ASA 2023 (COR 2b): in acute severe headache without a new neurological deficit, the Ottawa SAH Rule may be reasonable to identify high-risk patients.",
                    "AHA/ASA 2023: applies to alert patients >15 y with new severe non-traumatic headache peaking within 1 h; any criterion means further investigation.",
                    "Perry 2013 (JAMA): 100% sensitivity and 15.3% specificity in 2131 ED patients with a normal neurological examination.",
                    "AHA/ASA 2023: later validations were 100% sensitive but 13.6% and 7.6% specific — it identifies only a small subset unlikely to have aSAH.",
                ),
                checklistTitle = "Ottawa SAH Rule — investigate if any",
                checklist = listOf(
                    "Age ≥40 years",
                    "Neck pain or stiffness",
                    "Witnessed loss of consciousness",
                    "Onset during exertion",
                    "Thunderclap headache (instantly peaking pain)",
                    "Limited neck flexion on examination",
                ),
                citations = listOf(
                    cite(SAH_AHA, "§4 Rec 4; Table 3"),
                    cite(SAH_OTTAWA, "Abstract — results"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "CT, then LP if needed",
                timing = "Urgent non-contrast CT; LP ≥12 h after onset (NICE NG228)",
                points = listOf(
                    "NICE NG228: urgent non-contrast CT head; accuracy is highest within 6 h of onset. Blood in the subarachnoid space confirms SAH.",
                    "NICE NG228: radiologist-reported CT within 6 h shows no SAH — do not routinely do LP; consider other diagnoses and seek specialist advice.",
                    "AHA/ASA 2023 (COR 2a): <6 h and no new deficit — a negative CT on a high-quality scanner read by a neuroradiologist can exclude aSAH (sensitivity 98.7%).",
                    "AHA/ASA 2023 (COR 1): >6 h from onset or new deficit — CT, then LP if CT is negative. ICMR STW: LP if SAH suspected and CT negative.",
                    "NICE NG228: CT >6 h negative — consider LP at least 12 h after onset; xanthochromia on spectrophotometry confirms SAH.",
                    "AHA/ASA 2023: CSF spectrophotometry for xanthochromia after a negative CT — sensitivity 100%, specificity 95.2%.",
                ),
                citations = listOf(
                    cite(SAH_NICE, "Rec 1.1.7, 1.1.10–1.1.15"),
                    cite(SAH_AHA, "§4 Rec 2–3 and supportive text"),
                    cite(SAH_ICMR_STROKE, "Recommended diagnostic studies — selected patients"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Find the aneurysm and refer",
                timing = "Secure the aneurysm as early as feasible, preferably within 24 h (AHA/ASA 2023)",
                points = listOf(
                    "NICE NG228: CT angiography without delay once SAH is confirmed; DSA or MRA if CTA is negative but an aneurysm is suspected. AHA/ASA 2023: DSA (COR 1).",
                    "NICE NG228: urgently discuss transfer with a neurosurgical centre; rebleeding risk is highest within 24 h; don't decide on a severity score alone.",
                    "AHA/ASA 2023 (COR 1): timely transfer from low-volume to high-volume centres with neurocritical care and cerebrovascular and endovascular expertise.",
                    "AHA/ASA 2023 (COR 1): treat the ruptured aneurysm surgically or endovascularly as early as feasible, preferably within 24 h. NICE NG228: coiling, or clipping if coiling is unsuitable.",
                    "ICMR STW: SAH — angiography and clipping or coiling of the aneurysm to prevent rebleeding, at a tertiary centre.",
                ),
                citations = listOf(
                    cite(SAH_NICE, "Rec 1.1.16–1.1.18, 1.1.21, 1.2.6, 1.2.8"),
                    cite(SAH_AHA, "§4 Rec 5; §5 Rec 1; §7 Rec 1"),
                    cite(MG_ICMR, "Treatment of major catastrophic headaches at tertiary centre"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Prevent rebleeding",
                timing = "Until the aneurysm is secured",
                points = listOf(
                    "AHA/ASA 2023 (COR 1): frequent BP monitoring and short-acting agents to avoid severe hypotension, hypertension and BP variability while the aneurysm is unsecured.",
                    "AHA/ASA 2023: lower BP gradually if severely hypertensive (>180–200 mm Hg); avoid MAP <65 mm Hg; evidence is insufficient for a specific target.",
                    "AHA/ASA 2023: earlier guidelines suggested systolic <160 or <180 mm Hg; a meta-analysis found more rebleeding above 160 mm Hg.",
                    "AHA/ASA 2023 (COR 1): emergency anticoagulant reversal with appropriate agents, as for life-threatening bleeding — see the ICH step of 'Acute stroke'.",
                    "AHA/ASA 2023 (COR 3: no benefit): routine antifibrinolytics such as tranexamic acid do not improve functional outcome (ULTRA trial).",
                ),
                citations = listOf(
                    cite(SAH_AHA, "§6 Rec 1–3 and supportive text"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Nimodipine and DCI prevention",
                timing = "Start within 96 h of SAH (label)",
                points = listOf(
                    "AHA/ASA 2023 (COR 1): early enteral nimodipine prevents delayed cerebral ischaemia and improves outcome; 60 mg six times a day.",
                    "Nimodipine label: 60 mg orally every 4 h for 21 days, starting within 96 h; 1 h before or 2 h after meals; avoid grapefruit juice.",
                    "Label: if unable to swallow, draw capsule contents into an oral syringe labelled 'Not for IV use'; give via NG tube and flush with 30 mL saline.",
                    "Label: cirrhosis — 30 mg every 4 h with BP and heart-rate monitoring. AHA/ASA 2023: continue through manageable hypotension; pause if BP variability is significant.",
                    "NICE NG228: consider enteral nimodipine; use IV nimodipine only in a specialist setting when enteral treatment is unsuitable.",
                    "AHA/ASA 2023: maintain euvolaemia (COR 2a); prophylactic haemodynamic augmentation is harmful (COR 3); routine statins or IV magnesium not recommended.",
                ),
                checklistTitle = "Nimodipine safety (label)",
                checklist = listOf(
                    "Never give capsule contents IV or by any parenteral route (boxed warning)",
                    "Contraindicated with strong CYP3A4 inhibitors — e.g. clarithromycin, ketoconazole, itraconazole, voriconazole, ritonavir",
                    "Strong CYP3A4 inducers should generally not be co-administered",
                    "Nimodipine-induced hypotension — monitor BP (AHA/ASA 2023); in cirrhosis also heart rate (label)",
                ),
                citations = listOf(
                    cite(SAH_AHA, "§8.3 Rec 1–2, 6–8 and supportive text"),
                    cite(SAH_NIMO, "Boxed warning; Contraindications; Dosage and administration"),
                    cite(SAH_NICE, "Rec 1.2.1–1.2.2"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Seizures, hydrocephalus and deterioration",
                points = listOf(
                    "AHA/ASA 2023: seizures at presentation — antiseizure medication for ≤7 days is reasonable (COR 2a); longer treatment does not reduce later SAH seizures.",
                    "AHA/ASA 2023: no prophylaxis without high-risk features (COR 3: no benefit); phenytoin prophylaxis is associated with excess morbidity and mortality (COR 3: harm).",
                    "AHA/ASA 2023: high risk = ruptured MCA aneurysm, high-grade SAH, ICH, hydrocephalus, cortical infarction — prophylaxis may be reasonable (2b); cEEG reasonable if consciousness depressed or fluctuating (2a).",
                    "AHA/ASA 2023 (COR 1): urgent CSF diversion (EVD and/or lumbar drain) for acute symptomatic hydrocephalus. NICE NG228: consider drainage for deterioration from hydrocephalus.",
                    "NICE NG228: unexplained neurological deterioration — non-contrast CT head first. Manage VTE risk in line with NICE VTE guidance.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(SAH_AHA, "§8.5 Rec 1–6; §8.4 Rec 1"),
                    cite(SAH_NICE, "Rec 1.2.3, 1.3.2, 1.3.4"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "BP target before securing the aneurysm is unsettled: AHA/ASA 2023 sets none (earlier guidelines <160 or <180 mm Hg systolic); NICE NG228 makes no BP recommendation.",
                listOf(cite(SAH_AHA, "§6 supportive text"), cite(SAH_NICE, "Recommendations 1.1–1.3")),
            ),
            ProtocolNote(
                "AIAN 2011 (Indian) describes 'triple H' therapy (hydration, hypertension, haemodilution) with nimodipine for vasospasm; AHA/ASA 2023 advises euvolaemia and calls prophylactic haemodynamic augmentation harmful.",
                listOf(cite(MG_AIAN, "Headache attributed to subarachnoid hemorrhage — treatment"), cite(SAH_AHA, "§8.3 Rec 2, 8")),
            ),
            ProtocolNote(
                "Early negative CT: NICE NG228 accepts a radiologist-reported CT within 6 h without routine LP; AHA/ASA 2023 specifies a high-quality scanner and neuroradiologist, and not for atypical presentations (neck pain, syncope, seizure, new deficit).",
                listOf(cite(SAH_NICE, "Rec 1.1.11"), cite(SAH_AHA, "§4 Rec 3 supportive text")),
            ),
            ProtocolNote(
                "Nimodipine route: NICE NG228 allows IV nimodipine only in specialist settings; the US capsule label forbids parenteral use of capsule contents.",
                listOf(cite(SAH_NICE, "Rec 1.2.2"), cite(SAH_NIMO, "Boxed warning")),
            ),
            ProtocolNote(
                "The Ottawa SAH Rule is highly sensitive but poorly specific; Perry 2013 states it applies only to alert patients with headache peaking within 1 h and a normal neurological examination.",
                listOf(cite(SAH_OTTAWA, "Abstract — conclusions"), cite(SAH_AHA, "§4 Rec 4 supportive text")),
            ),
        ),
        citations = listOf(
            cite(SAH_AHA), cite(SAH_NICE), cite(SAH_OTTAWA), cite(SAH_NIMO), cite(SAH_ICMR_STROKE), cite(MG_ICMR), cite(MG_AIAN),
        ),
    ),
)
