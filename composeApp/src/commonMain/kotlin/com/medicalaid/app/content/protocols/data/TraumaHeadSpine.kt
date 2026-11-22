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
 * Head injury (traumatic brain injury) and spinal injury. Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them (checked 22 November 2026 against the
 *   full texts of MoHFW STG Trauma 2025 Ch. 2, 4, 7, 9, 11 and 12; the ICMR STWs Head Injury and Spinal Injury (July
 *   2024, read from the rendered flowcharts); NICE NG232, NG41, NG39 and NG24 (rec 1.12.3); the BTF 4th-edition
 *   executive summary; BTF Prehospital 3rd edition; CRASH-3; SIBICC 2019 (Figs 1–2); European guideline 2023;
 *   AANS/CNS 2013 chapters 7–8; AO Spine 2017 (methylprednisolone) and 2024 (haemodynamics; surgical timing);
 *   CSCM 2008 recommendation summary; CRASH 2004 against its abstract; the US FDA labels for phenytoin sodium injection,
 *   fosphenytoin (CEREBYX) and methylprednisolone sodium succinate (SOLU-MEDROL), read via openFDA, for infusion rates,
 *   monitoring and diluent only).
 * - Every point is attributed ("MoHFW 2025: …", "ICMR STW 2024: …", "NICE NG232: …", "BTF 4th edition: …",
 *   "BTF prehospital 2023: …", "European 2023: …", "AANS/CNS 2013: …", "AO Spine 2024: …", "CSCM 2008: …",
 *   "FDA label: …").
 * - The ATLS manual is proprietary and is not used as a source. The MoHFW and ICMR documents are ATLS-based; their
 *   statements are reported as those bodies publish them.
 * - Locators: MoHFW 2025 — chapter and printed page; NICE — recommendation number; BTF 4th edition — table and topic;
 *   BTF prehospital — section and recommendation letter; European 2023 — recommendation (R) number; ICMR STW — panel.
 * - Existing sources reused by id: tr-mohfw-stg-trauma-2025, tr-nice-ng39, tr-euro-bleeding-2023 and
 *   tr-rcuk-first-aid-2025 (Trauma.kt); sc-nice-ng232 (scores/data/AcuteCare.kt); sc-tr-nice-ng41 and
 *   sc-tr-stiell-2003 (scores/data/TraumaScores.kt).
 * - Not used: the Neurotrauma Society of India national TBI guidelines (2022) could not be obtained for checking.
 */

internal val headSpineSources: List<Source> = listOf(
    Source(
        "tr-hs-icmr-stw-head-2024", Authority.ICMR, "Standard Treatment Workflow (Neurosurgery)",
        "Head Injury (ICD S09.90XA)", "2024",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726645069_2_head_injury.pdf",
        status = "July 2024; Department of Health Research, MoHFW",
    ),
    Source(
        "tr-hs-icmr-stw-spine-2024", Authority.ICMR, "Standard Treatment Workflow (Neurosurgery)",
        "Spinal Injury (ICD S14.109A)", "2024",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726645068_3_spinal_injury.pdf",
        status = "July 2024; Department of Health Research, MoHFW",
    ),
    Source(
        "tr-hs-btf-2016", Authority.BTF, "Guideline executive summary (Neurosurgery 2017;80(1):6–15)",
        "Guidelines for the Management of Severe Traumatic Brain Injury, Fourth Edition", "2016",
        "https://doi.org/10.1227/NEU.0000000000001432",
        status = "Carney N et al.; no fifth edition found (November 2026); the 2020 decompressive craniectomy update is not used here",
    ),
    Source(
        "tr-hs-btf-prehospital-2023", Authority.BTF, "Guideline (Prehosp Emerg Care 2023;27(5):507–538)",
        "Prehospital Guidelines for the Management of Traumatic Brain Injury – 3rd Edition", "2023",
        "https://doi.org/10.1080/10903127.2023.2187905",
        status = "Lulla A et al.",
    ),
    Source(
        "tr-hs-crash3-2019", Authority.Paper, "RCT (Lancet 2019;394(10210):1713–1723)",
        "Effects of tranexamic acid on death, disability, vascular occlusive events and other morbidities in patients with acute traumatic brain injury (CRASH-3): a randomised, placebo-controlled trial",
        "2019",
        "https://doi.org/10.1016/S0140-6736(19)32233-0",
        status = "CRASH-3 trial collaborators; open access (PMC6853170)",
    ),
    Source(
        "tr-hs-crash-2004", Authority.Paper, "RCT (Lancet 2004;364(9442):1321–1328)",
        "Effect of intravenous corticosteroids on death within 14 days in 10008 adults with clinically significant head injury (MRC CRASH trial): randomised placebo-controlled trial",
        "2004",
        "https://doi.org/10.1016/S0140-6736(04)17188-2",
        status = "Abstract reviewed; 6-month results in Lancet 2005;365(9475):1957–1959",
    ),
    Source(
        "tr-hs-sibicc-2019", Authority.Paper, "International consensus (Intensive Care Med 2019;45(12):1783–1794)",
        "A management algorithm for patients with intracranial pressure monitoring: the Seattle International Severe Traumatic Brain Injury Consensus Conference (SIBICC)",
        "2019",
        "https://doi.org/10.1007/s00134-019-05805-9",
        status = "Hawryluk GWJ et al.; consensus for patients with an ICP monitor; open access (PMC6863785)",
    ),
    Source(
        "tr-hs-nice-ng24", Authority.NICE, "Guideline NG24",
        "Blood transfusion", "2015",
        "https://www.nice.org.uk/guidance/ng24/chapter/Prothrombin-complex-concentrate-transfusion",
        status = "Last updated 26 February 2026; cited for rec 1.12.3 only",
    ),
    Source(
        "tr-hs-aans-cardiopulmonary-2013", Authority.Paper, "AANS/CNS guideline chapter (Neurosurgery 2013;72 Suppl 2:84–92)",
        "The acute cardiopulmonary management of patients with cervical spinal cord injuries", "2013",
        "https://doi.org/10.1227/NEU.0b013e318276ee16",
        status = "Ryken TC et al.; Guidelines for the management of acute cervical spine and spinal cord injuries: 2013 update",
    ),
    Source(
        "tr-hs-aans-pharmacological-2013", Authority.Paper, "AANS/CNS guideline chapter (Neurosurgery 2013;72 Suppl 2:93–105)",
        "Pharmacological therapy for acute spinal cord injury", "2013",
        "https://doi.org/10.1227/NEU.0b013e31827765c6",
        status = "Hurlbert RJ et al.; Guidelines for the management of acute cervical spine and spinal cord injuries: 2013 update",
    ),
    Source(
        "tr-hs-aospine-mpss-2017", Authority.Paper, "AO Spine / AANS-CNS guideline (Global Spine J 2017;7(3 Suppl):203S–211S)",
        "A Clinical Practice Guideline for the Management of Patients With Acute Spinal Cord Injury: Recommendations on the Use of Methylprednisolone Sodium Succinate",
        "2017",
        "https://doi.org/10.1177/2192568217703085",
        status = "Fehlings MG et al.; open access (PMC5686915)",
    ),
    Source(
        "tr-hs-aospine-haemodynamic-2024", Authority.Paper, "AO Spine guideline (Global Spine J 2024;14(3 Suppl):187S–211S)",
        "A Clinical Practice Guideline for the Management of Patients With Acute Spinal Cord Injury: Recommendations on Hemodynamic Management",
        "2024",
        "https://doi.org/10.1177/21925682231202348",
        status = "Kwon BK et al.; restated as a conditional recommendation by AO Spine in 2026 (Global Spine J 2026;16(4):1700–1706)",
    ),
    Source(
        "tr-hs-aospine-timing-2024", Authority.Paper, "AO Spine guideline (Global Spine J 2024;14(3 Suppl):174S–186S)",
        "An Update of a Clinical Practice Guideline for the Management of Patients With Acute Spinal Cord Injury: Recommendations on the Role and Timing of Decompressive Surgery",
        "2024",
        "https://doi.org/10.1177/21925682231181883",
        status = "Fehlings MG et al.; open access (PMC10964895)",
    ),
    Source(
        "tr-hs-cscm-2008", Authority.Paper, "Consortium for Spinal Cord Medicine guideline (J Spinal Cord Med 2008;31(4):403–479)",
        "Early Acute Management in Adults with Spinal Cord Injury: A Clinical Practice Guideline for Health-Care Professionals",
        "2008",
        "https://pmc.ncbi.nlm.nih.gov/articles/PMC2582434/",
        status = "Paralyzed Veterans of America; no later edition found (November 2026)",
    ),
    Source(
        "tr-hs-fda-phenytoin", Authority.FDA, "Prescribing information (DailyMed; Hikma)",
        "Phenytoin Sodium Injection, USP", "2024",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=035a8d4e-2063-4240-83cb-d7eebcabe301",
        status = "Label effective April 2024, read via openFDA; cited for infusion rate, monitoring and diluent",
    ),
    Source(
        "tr-hs-fda-fosphenytoin", Authority.FDA, "Prescribing information (DailyMed; Pfizer)",
        "CEREBYX (fosphenytoin sodium) injection", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=d4c36fad-0ba2-4cd4-9c5e-dcf843f38a5a",
        status = "Label effective August 2025, read via openFDA; cited for dose units (mg PE) and infusion rate",
    ),
    Source(
        "tr-hs-fda-methylprednisolone", Authority.FDA, "Prescribing information (DailyMed; Pharmacia & Upjohn)",
        "SOLU-MEDROL (methylprednisolone sodium succinate for injection)", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=7271310c-7764-4812-aa30-a5e90987c7a9",
        status = "Label effective June 2026, read via openFDA; cited for the high-dose infusion rate and rapid-infusion warnings",
    ),
)

// Sources defined in this file.
private const val ICMR_HEAD = "tr-hs-icmr-stw-head-2024"
private const val ICMR_SPINE = "tr-hs-icmr-stw-spine-2024"
private const val BTF = "tr-hs-btf-2016"
private const val BTF_PRE = "tr-hs-btf-prehospital-2023"
private const val CRASH3 = "tr-hs-crash3-2019"
private const val CRASH = "tr-hs-crash-2004"
private const val SIBICC = "tr-hs-sibicc-2019"
private const val NG24 = "tr-hs-nice-ng24"
private const val AANS_CP = "tr-hs-aans-cardiopulmonary-2013"
private const val AANS_PH = "tr-hs-aans-pharmacological-2013"
private const val AO_MPSS = "tr-hs-aospine-mpss-2017"
private const val AO_MAP = "tr-hs-aospine-haemodynamic-2024"
private const val AO_SURG = "tr-hs-aospine-timing-2024"
private const val CSCM = "tr-hs-cscm-2008"
private const val FDA_PHT = "tr-hs-fda-phenytoin"
private const val FDA_FOS = "tr-hs-fda-fosphenytoin"
private const val FDA_MPSS = "tr-hs-fda-methylprednisolone"

/** Defined in Trauma.kt — MoHFW (Dte.GHS) Standard Treatment Guidelines for Management of Trauma, 2025. */
private const val MOH = "tr-mohfw-stg-trauma-2025"

/** Defined in Trauma.kt — NICE NG39 Major trauma: assessment and initial management. */
private const val NG39 = "tr-nice-ng39"

/** Defined in Trauma.kt — European guideline on management of major bleeding and coagulopathy following trauma, 6th edition. */
private const val EU = "tr-euro-bleeding-2023"

/** Defined in Trauma.kt — RCUK publication of the ERC Guidelines 2025 First Aid. */
private const val RCUK_FA = "tr-rcuk-first-aid-2025"

/** Defined in scores/data/AcuteCare.kt — NICE NG232 Head injury: assessment and early management (2023). */
private const val NG232 = "sc-nice-ng232"

/** Defined in scores/data/TraumaScores.kt — NICE NG41 Spinal injury: assessment and initial management. */
private const val NG41 = "sc-tr-nice-ng41"

/** Defined in scores/data/TraumaScores.kt — Stiell 2003, Canadian C-spine rule versus NEXUS (NEJM). */
private const val CCR_VS_NEXUS = "sc-tr-stiell-2003"

// ================================================================================================ Head injury
private val headInjury = Protocol(
    id = "head-injury",
    category = ProtocolCategory.Trauma,
    title = "Head injury (traumatic brain injury)",
    summary = "Severity grading, airway and blood-pressure targets, CT head and cervical spine criteria, anticoagulants and " +
        "tranexamic acid, raised ICP, seizures, neurosurgical referral and safe discharge — attributed to MoHFW 2025, " +
        "ICMR STW 2024, NICE NG232, the Brain Trauma Foundation, CRASH-3 and the European guideline 2023.",
    keywords = listOf(
        "head injury", "head trauma", "TBI", "traumatic brain injury", "brain injury", "concussion", "mild head injury",
        "minor head injury", "severe head injury", "RTA head injury", "road accident head injury", "fall head injury",
        "GCS", "Glasgow", "CT head", "NCCT head", "CT brain", "skull fracture", "depressed fracture", "basal skull fracture",
        "base of skull fracture", "Battle sign", "raccoon eyes", "CSF leak", "CSF rhinorrhoea", "EDH", "extradural haematoma",
        "epidural hematoma", "SDH", "subdural haematoma", "contusion", "diffuse axonal injury", "raised ICP", "ICP",
        "intracranial pressure", "herniation", "coning", "Cushing triad", "mannitol", "hypertonic saline", "3% saline",
        "phenytoin", "Eptoin", "levetiracetam", "Levipil", "post-traumatic seizure", "tranexamic acid", "TXA", "Pause",
        "CRASH-3", "anticoagulant head injury", "warfarin head injury", "BTF", "Brain Trauma Foundation",
        "sir ki chot", "sar ki chot", "sir mein chot", "sir te satt", "behosh", "behoshi",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Assess quickly and grade severity",
            timing = "Within 15 min of arrival (NICE NG232)",
            points = listOf(
                "NICE NG232: a trained clinician assesses every head injury within 15 min of arrival, and anyone with GCS <15 immediately.",
                "MoHFW 2025 grades TBI by GCS: mild 13–15, moderate 9–12, severe 3–8. MoHFW triage: GCS ≤12 goes to the red area.",
                "NICE NG232: record eye, verbal and motor scores with the total out of 15; establish the pre-injury baseline (e.g. dementia, learning disability).",
                "MoHFW 2025: neurological examination is unreliable during hypotension — record GCS, pupils and focal deficits after normotension; follow the motor response.",
                "NICE NG232: blame low consciousness on intoxication only after important brain injury is excluded. MoHFW 2025: no doll's-eye testing until the C-spine is cleared.",
                "MoHFW 2025: AMPLE history, especially anticoagulant use; presume cervical spine injury in every head injury until proven otherwise.",
            ),
            scoreIds = listOf("gcs"),
            citations = listOf(
                cite(NG232, "Rec 1.3.1–1.3.5, 1.4.2, 1.4.4, 1.4.7"),
                cite(MOH, "Ch. 7 Head trauma (pp. 34–35); Ch. 1 Triage criteria (pp. 6–7); Ch. 2 Secondary survey — cervical spine (p. 17)"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Airway, oxygenation and ventilation",
            timing = "Primary survey",
            points = listOf(
                "MoHFW 2025: 100% oxygen by face mask; intubate severe TBI (GCS <8 in its head-trauma primary survey; all with GCS 3–8 in its severe TBI section); its general primary survey advises a definitive airway if airway integrity is in doubt or GCS ≤10.",
                "European 2023 (1B): intubate without delay for airway obstruction, GCS ≤8, hypoventilation or hypoxaemia; avoid hypoxaemia (1A) and hyperoxaemia unless exsanguination is imminent (2B); normoventilate (1B).",
                "MoHFW 2025 intubation: preoxygenate with 100% oxygen for 3–5 min; remove the front of the collar and keep manual in-line stabilisation; drugs as below.",
                "Targets: NICE NG232 — PaO₂ >13 kPa, PaCO₂ 4.5–5.0 kPa; MoHFW 2025 — SpO₂ >98%, PaO₂ >100 mm Hg, PaCO₂ around 35 mm Hg.",
                "BTF prehospital 2023: correct SpO₂ <90% at once; ventilate to EtCO₂ 35–45 mm Hg (about 10 breaths/min) and avoid EtCO₂ <35 mm Hg.",
                "MoHFW 2025: after intubation, fentanyl 1 mcg/kg/h infusion or intermittent boluses, with sedation at RASS −2.",
            ),
            checklistTitle = "Intubate and ventilate immediately (NICE NG232)",
            checklist = listOf(
                "Coma — GCS ≤8",
                "Loss of protective laryngeal reflexes",
                "Hypoxaemia — PaO₂ <13 kPa on oxygen",
                "Hypercarbia — PaCO₂ >6 kPa",
                "Irregular respirations",
                "Before transfer also: motor score falling by ≥1 point, unstable facial fractures, copious bleeding into the mouth, or seizures",
            ),
            doses = listOf(
                StepDose(
                    "Fentanyl — pre-treatment before intubation (MoHFW 2025)",
                    WeightDose(1.0, 2.0, unit = "mcg", per = "IV"),
                ),
                StepDose(
                    "Etomidate — induction (MoHFW 2025)",
                    WeightDose(0.3, 0.3, unit = "mg", per = "IV"),
                ),
                StepDose(
                    "Rocuronium — paralysis (MoHFW 2025)",
                    WeightDose(1.0, 1.0, unit = "mg", per = "IV"),
                ),
                StepDose(
                    "Fentanyl — after intubation (MoHFW 2025)",
                    WeightDose(1.0, 1.0, unit = "mcg", per = "per hour, IV infusion (or intermittent bolus)"),
                    note = "Sedation target RASS −2",
                ),
            ),
            citations = listOf(
                cite(MOH, "Ch. 2 Airway (p. 14); Ch. 7 Head trauma — airway and breathing (pp. 34–35); severe TBI (pp. 36–37)"),
                cite(EU, "R3"),
                cite(NG232, "Rec 1.4.6, 1.8.7–1.8.10"),
                cite(BTF_PRE, "Treatment: airway, oxygenation and ventilation — Rec B, F"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Prevent hypotension and secondary injury",
            timing = "From first contact",
            points = listOf(
                "BTF 4th edition (Level III): keep SBP ≥100 mm Hg at 50–69 years and ≥110 mm Hg at 15–49 or over 70 years.",
                "MoHFW 2025: SBP >100–110 mm Hg. BTF prehospital 2023: adults ≥110 mm Hg. European 2023 (1C): MAP ≥80 mm Hg in severe TBI (GCS ≤8).",
                "MoHFW 2025: isolated TBI is unlikely to cause hypotension — search for haemorrhagic shock; restore euvolaemia with blood products and isotonic fluid; check sodium often.",
                "NICE NG39: shock with TBI — restrictive resuscitation if haemorrhage dominates, less restrictive if TBI dominates. European 2023 (1B): avoid Ringer's lactate in severe head trauma.",
                "BTF prehospital 2023, children (weak): SBP at or above the 75th centile — >70 (≤28 days), >84 (1–12 months), >90 (1–5 years), >100 mm Hg (≥6 years).",
                "European 2023 (2C): platelets to keep the count above 100 × 10⁹/L in TBI (above 50 × 10⁹/L in other ongoing bleeding).",
            ),
            checklistTitle = "MoHFW 2025 — goals of treatment in severe TBI",
            checklist = listOf(
                "SBP >100–110 mm Hg",
                "Temperature 36–38 °C",
                "Oxygen saturation >95%; EtCO₂ and PaCO₂ around 35 mm Hg",
                "Random blood sugar 80–180 mg/dL",
                "Haemoglobin >8 g/dL; platelets >75,000; INR <1.4",
                "Sodium 135–145 mEq/L; pH 7.35–7.45",
            ),
            citations = listOf(
                cite(BTF, "Table 3 — Blood pressure thresholds"),
                cite(MOH, "Ch. 7 Head trauma — circulation (p. 35); goals of treatment (p. 37)"),
                cite(BTF_PRE, "Assessment: oxygenation, blood pressure and temperature — Rec A"),
                cite(EU, "R13, R15, R30"),
                cite(NG39, "Rec 1.5.21"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "CT head and cervical spine imaging",
            timing = "CT within 1 h of a high-risk factor (NICE NG232)",
            points = listOf(
                "NICE NG232: loss of consciousness or amnesia plus age ≥65, a bleeding or clotting disorder, dangerous mechanism or >30 min retrograde amnesia — CT head within 8 h.",
                "MoHFW 2025 mild TBI — NCCT head with C-spine for: age >65, vomiting >2 episodes, LOC >5 min, retrograde amnesia >30 min, dangerous mechanism, anticoagulant use.",
                "MoHFW 2025 also: GCS <15 at 2 h, signs of open, depressed or basal skull fracture; NCCT in all moderate–severe TBI. ICMR STW 2024: NCCT head with C-spine for all.",
                "NICE NG232: CT cervical spine within 1 h for GCS ≤12, intubation, blunt polytrauma of head and torso, or neck concern with age ≥65, dangerous mechanism, paraesthesia or focal deficit.",
                "Children: NICE NG232 has separate criteria (e.g. suspected non-accidental injury, GCS <14, a head bruise, swelling or cut >5 cm under 1 year). MoHFW 2025 uses the PECARN rule.",
                "NICE NG232: no CT on site — transfer; do not use skull X-rays to diagnose brain injury; provisional CT report within 1 h.",
            ),
            checklistTitle = "NICE NG232 — CT head within 1 h (age 16 and over)",
            checklist = listOf(
                "GCS ≤12 on initial assessment in the emergency department",
                "GCS <15 at 2 h after the injury",
                "Suspected open or depressed skull fracture",
                "Any sign of basal skull fracture — haemotympanum, panda eyes, CSF from ear or nose, Battle's sign",
                "Post-traumatic seizure",
                "Focal neurological deficit",
                "More than 1 episode of vomiting",
            ),
            scoreIds = listOf("canadian-ct-head", "canadian-c-spine"),
            citations = listOf(
                cite(NG232, "Rec 1.5.4–1.5.11, 1.5.14, 1.6.1–1.6.3"),
                cite(MOH, "Ch. 7 Head trauma — mild, moderate and severe TBI (pp. 35–36); Ch. 11 Paediatric trauma — head trauma, Figure 18 (pp. 54–55)"),
                cite(ICMR_HEAD, "Investigations — essential"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Anticoagulants and tranexamic acid",
            timing = "Tranexamic acid within 2 h (NICE NG232) or 3 h (CRASH-3) of injury",
            points = listOf(
                "NICE NG232: on anticoagulants or antiplatelets (except aspirin alone) with no other CT indication — consider CT head within 8 h of injury.",
                "NICE NG24: immediate prothrombin complex concentrate to reverse warfarin with head injury and suspected intracerebral haemorrhage. MoHFW 2025: reverse anticoagulation with an intracranial bleed.",
                "Reversal agents and doses (PCC, vitamin K, idarucizumab): see the Traumatic haemorrhage and shock protocol — Reverse anticoagulants.",
                "NICE NG232: GCS ≤12 and no suspected active extracranial bleeding — consider tranexamic acid 2 g IV bolus (16 and over) within 2 h, before imaging.",
                "CRASH-3 (adults within 3 h; GCS ≤12 or CT bleed; no major extracranial bleeding): 1 g over 10 min, then 1 g over 8 h. Head-injury death 18.5% vs 19.8% (RR 0.94, 95% CI 0.86–1.02); by severity, fewer deaths in mild–moderate (RR 0.78) but not severe TBI (RR 0.99).",
                "BTF prehospital 2023: prehospital tranexamic acid is not generally indicated for suspected intracranial bleeding. Extracranial bleeding: see Traumatic haemorrhage and shock.",
            ),
            doses = listOf(
                StepDose(
                    "Tranexamic acid — under 16 years (NICE NG232)",
                    WeightDose(15.0, 30.0, unit = "mg", per = "IV bolus, as soon as possible within 2 h of injury"),
                    note = "GCS ≤12 without suspected active extracranial bleeding; off-label in March 2023 (NICE)",
                    maxTotal = 2000.0,
                ),
            ),
            citations = listOf(
                cite(NG232, "Rec 1.3.17–1.3.18, 1.5.13"),
                cite(NG24, "Rec 1.12.3"),
                cite(MOH, "Ch. 7 Head trauma — circulation (p. 35); Ch. 12 Geriatric trauma (p. 56)"),
                cite(CRASH3, "Abstract — Methods and Findings"),
                cite(EU, "R23 rationale"),
                cite(BTF_PRE, "Treatment: hyperventilation and hyperosmolar therapy — Rec E"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Raised intracranial pressure and herniation",
            timing = "Immediately on signs of herniation",
            points = listOf(
                "MoHFW 2025, normotensive: mannitol 1 g/kg IV over 5 min, then 20 g (100 mL) three times daily; osmolality <320 mOsm/L; not in extradural haematoma.",
                "MoHFW 2025, hypotensive: 3% saline 200 mL over 10 min, sodium <155 mEq/L. ICMR STW 2024: mannitol 0.25–1 g/kg bolus or 3% saline 3 mL/kg 8-hourly.",
                "BTF (third-edition text, not evidence-graded): mannitol 0.25–1 g/kg, avoiding SBP <90 mm Hg; before ICP monitoring only for herniation or progressive deterioration.",
                "Hyperventilation only for herniation: European 2023 (2C); BTF prehospital 2023 — EtCO₂ 30–35 mm Hg. BTF (IIB): no prolonged prophylactic PaCO₂ ≤25 mm Hg; its third-edition text: avoid in the first 24 h.",
                "SIBICC 2019: head of bed 30–45°, head midline, cervical collar not too tight. ICMR STW 2024: elevate the head end and sedate.",
                "No corticosteroids: MoHFW 2025 — contraindicated; BTF (Level I) — not recommended; CRASH — methylprednisolone raised 2-week mortality (21.1% vs 17.9%).",
            ),
            checklistTitle = "Signs of raised ICP or herniation",
            checklist = listOf(
                "Deteriorating GCS (MoHFW 2025); progressive neurological deterioration (BTF prehospital)",
                "Focal neurological deficit (MoHFW 2025); posturing or lateralising signs (BTF prehospital)",
                "Pupil dilatation (MoHFW 2025); one or both pupils fixed and dilated (BTF prehospital)",
                "Cushing's triad — hypertension, bradycardia, irregular breathing (BTF prehospital)",
                "Persistent headache, vomiting or restlessness (ICMR STW 2024)",
            ),
            doses = listOf(
                StepDose(
                    "Mannitol — normotensive patient (MoHFW 2025)",
                    WeightDose(1.0, 1.0, unit = "g", per = "IV over 5 min"),
                    note = "Then 20 g (100 mL) three times daily; keep serum osmolality <320 mOsm/L. MoHFW: do not give in extradural haematoma",
                ),
                StepDose(
                    "Mannitol — bolus (ICMR STW 2024; BTF third-edition text)",
                    WeightDose(0.25, 1.0, unit = "g", per = "IV bolus"),
                    note = "BTF: avoid SBP <90 mm Hg",
                ),
                StepDose(
                    "3% hypertonic saline (ICMR STW 2024)",
                    WeightDose(3.0, 3.0, unit = "mL", per = "IV, 8-hourly"),
                    note = "MoHFW 2025 (hypotensive patients): 200 mL over 10 min; keep serum sodium <155 mEq/L",
                ),
            ),
            citations = listOf(
                cite(MOH, "Ch. 7 Head trauma — management of raised ICP (p. 37)"),
                cite(ICMR_HEAD, "Medical management; Severe head injury pathway; Complications to watch for"),
                cite(BTF, "Table 1 — Hyperosmolar therapy, Ventilation therapies, Steroids"),
                cite(BTF_PRE, "Treatment: hyperventilation and hyperosmolar therapy — Rec A, C"),
                cite(EU, "R3"),
                cite(SIBICC, "Fig. 1 Tier zero"),
                cite(CRASH, "Abstract — Findings"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Seizures and intensive care",
            timing = "Early post-traumatic seizures: within 7 days of injury (BTF 4th edition)",
            points = listOf(
                "BTF 4th edition (IIA): phenytoin reduces early seizures (within 7 days) but not late seizures; early seizures have not been linked to worse outcomes.",
                "BTF: insufficient evidence to prefer levetiracetam over phenytoin. SIBICC 2019: consider anti-seizure medicine for 1 week only, unless there is an indication to continue.",
                "MoHFW 2025, seizures: phenytoin or fosphenytoin 15–20 mg/kg slowly over 30 min; adults 1 g loading, then 100 mg 8-hourly. Continuing seizures: see Status epilepticus.",
                "ICMR STW 2024: phenytoin 20 mg/kg IV over 30 min, then 5 mg/kg in 2–3 divided doses, or levetiracetam 500 mg twice daily for 1–3 months.",
                "FDA labels: phenytoin no faster than 50 mg/min in adults (children 1–3 mg/kg/min or 50 mg/min, whichever is slower), with continuous ECG and BP monitoring; dilute in normal saline, not dextrose. Fosphenytoin is dosed in mg PE, up to 150 mg PE/min in adults.",
                "SIBICC 2019 basic care: ICU, analgesia and sedation, treat temperature >38 °C, CPP ≥60 mm Hg, Hb >7 g/dL, SpO₂ ≥94%, avoid hyponatraemia.",
                "NICE NG232: treat pain, splint fractures and catheterise a full bladder, as these raise ICP. BTF (IIB): treat ICP >22 mm Hg; CPP 60–70 mm Hg.",
            ),
            doses = listOf(
                StepDose(
                    "Phenytoin — loading (MoHFW 2025: 15–20 mg/kg; ICMR STW 2024: 20 mg/kg)",
                    WeightDose(15.0, 20.0, unit = "mg", per = "IV slowly, no faster than 50 mg/min (FDA label)"),
                    note = "MoHFW and ICMR: over 30 min — at 50 mg/min, doses above 1.5 g take longer. FDA label: children 1–3 mg/kg/min or 50 mg/min, whichever is slower; continuous ECG and BP monitoring; dilute in normal saline only. MoHFW: in adults, 1 g loading then 100 mg 8-hourly",
                ),
                StepDose(
                    "Phenytoin — maintenance (ICMR STW 2024)",
                    WeightDose(5.0, 5.0, unit = "mg", per = "in 2–3 divided doses, as written in the STW"),
                ),
            ),
            citations = listOf(
                cite(BTF, "Table 1 — Seizure prophylaxis; Table 3 — ICP and CPP thresholds"),
                cite(SIBICC, "Fig. 1 Tier zero; Fig. 2 Tier 1"),
                cite(MOH, "Ch. 7 Head trauma — management of seizures (p. 37)"),
                cite(ICMR_HEAD, "Medical management — antiepileptic drugs"),
                cite(FDA_PHT, "Boxed warning; Dosage and administration 2.1–2.2"),
                cite(FDA_FOS, "Boxed warning; Dosage and administration 2.1; Warnings 5.1 Dosing errors"),
                cite(NG232, "Rec 1.3.11, 1.4.11"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Neurosurgical referral, transfer and specific injuries",
            points = listOf(
                "NICE NG232: discuss new surgically significant imaging findings with a neurosurgeon. ICMR STW 2024: surgery for extradural haematoma >30 mL, or subdural >10 mm with shift >5 mm and deterioration.",
                "MoHFW 2025: admit or transfer moderate TBI to a facility with definitive neurosurgical care; if none is readily available, a trained surgeon operates on mass lesions. ICMR STW 2024: evacuation or decompressive craniectomy by a neurosurgeon only.",
                "NICE NG232: transfer benefits anyone with GCS ≤8; resuscitate, stabilise and monitor first; do not move a persistently hypotensive patient until the cause is found and treated.",
                "MoHFW 2025, penetrating: leave the object in place; NCCT head with C-spine; ceftriaxone 1 g IV, amikacin 500 mg IV twice daily, metronidazole 500 mg IV three times daily, or local policy.",
                "MoHFW 2025: scalp wounds bleed profusely — direct pressure, clean and suture; look for a skull fracture. Depressed fracture: NCCT with C-spine and neurosurgical consult.",
            ),
            checklistTitle = "NICE NG232 — discuss with a neurosurgeon, regardless of imaging",
            checklist = listOf(
                "Persisting coma (GCS ≤8) after initial resuscitation",
                "Unexplained confusion lasting more than 4 h",
                "Deterioration in GCS after admission (watch the motor response)",
                "Progressive focal neurological signs",
                "Seizure without full recovery",
                "Definite or suspected penetrating injury",
                "Cerebrospinal fluid leak",
            ),
            citations = listOf(
                cite(NG232, "Rec 1.4.15–1.4.16, 1.8.1, 1.8.6"),
                cite(ICMR_HEAD, "Surgical management in head injury"),
                cite(MOH, "Ch. 7 Head trauma — moderate TBI (p. 36); ED management of head injury (pp. 37–38)"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "Mild head injury: observe, admit or discharge",
            timing = "GCS 15: half-hourly for 2 h, hourly for 4 h, then 2-hourly (NICE NG232)",
            points = listOf(
                "NICE NG232 admit: new important CT abnormality, GCS not back to 15 or baseline, CT indicated but not possible, persistent vomiting, severe headache or seizures, other concerns.",
                "MoHFW 2025 admit: abnormal CT, penetrating injury, deteriorating consciousness, intoxication, GCS <15, no reliable companion at home, or persistent functional neurological disorder.",
                "NICE NG232 observations: GCS, pupils, limb movements, RR, HR, BP, temperature and SpO₂ — half-hourly until GCS 15, then on the schedule above.",
                "NICE NG232: urgent review for agitation, a sustained 1-point GCS drop, worsening headache or vomiting, or new pupil or limb asymmetry; consider immediate CT.",
                "Discharge (NICE NG232, MoHFW 2025): GCS 15, no other reason to admit and a responsible adult at home; NICE — someone stays for the first 24 h.",
                "MoHFW 2025 — return at once for: drowsiness (check 2-hourly), vomiting, seizures, ear or nose bleeding or watery discharge, severe headache, limb weakness, facial deviation, abnormal breathing.",
            ),
            citations = listOf(
                cite(NG232, "Rec 1.9.1, 1.9.10–1.9.15, 1.10.1–1.10.9"),
                cite(MOH, "Ch. 7 Head trauma — disposition and discharge of mild TBI (pp. 35–36)"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Blood pressure targets differ: BTF 4th edition — SBP ≥100 mm Hg (50–69 years) or ≥110 mm Hg (15–49 and over 70); MoHFW 2025 — SBP >100–110 mm Hg in its head trauma chapter but 90–95 mm Hg for TBI under permissive hypotension (Ch. 4); BTF prehospital — ≥110 mm Hg; European 2023 — MAP ≥80 mm Hg in severe TBI; NICE NG232 — MAP ≥80 mm Hg during transfer.",
            listOf(
                cite(BTF, "Table 3"), cite(MOH, "Ch. 4 (p. 23); Ch. 7 (pp. 35, 37)"), cite(BTF_PRE, "Assessment — Rec A"),
                cite(EU, "R13"), cite(NG232, "Rec 1.8.10"),
            ),
        ),
        ProtocolNote(
            "Carbon dioxide targets differ: ICMR STW 2024 — mild to moderate hyperventilation (PaCO₂ 30–35 mm Hg) for high ICP and PaCO₂ 30–32 mm Hg in ICU care of severe injury; MoHFW 2025 — PaCO₂ around 35 mm Hg, avoid hyperventilation; NICE NG232 — 4.5–5.0 kPa unless ICP is raised; European 2023 — normoventilation; BTF — no prolonged prophylactic PaCO₂ ≤25 mm Hg (IIB), and its third-edition text (not evidence-graded): hyperventilation as a temporising measure for raised ICP, avoided in the first 24 h after injury.",
            listOf(cite(ICMR_HEAD, "Medical management; Severe head injury pathway"), cite(MOH, "Ch. 7 (pp. 35, 37)"), cite(NG232, "Rec 1.8.10"), cite(EU, "R3"), cite(BTF, "Table 1 — Ventilation therapies")),
        ),
        ProtocolNote(
            "Imaging thresholds differ: ICMR STW 2024 advises NCCT head with cervical spine in all head injuries (and skull and cervical spine X-rays in mild injury); MoHFW 2025 uses criteria close to the Canadian CT Head Rule plus LOC >5 min and anticoagulant use; NICE NG232 uses its own criteria and advises against skull X-rays to diagnose brain injury.",
            listOf(cite(ICMR_HEAD, "Investigations; Mild head injury pathway"), cite(MOH, "Ch. 7 (p. 35)"), cite(NG232, "Rec 1.5.4, 1.5.8–1.5.9")),
        ),
        ProtocolNote(
            "Prehospital mannitol differs: ICMR STW 2024 — with red flags (unconscious, irregular respiration, unequal pupils) give 200 mL of 20% mannitol IV over 30 min if SBP >90 mm Hg, then refer; BTF prehospital 2023 (weak) — no prophylactic mannitol or hypertonic saline before hospital. MoHFW 2025 advises no mannitol in extradural haematoma.",
            listOf(cite(ICMR_HEAD, "Red flag signs; Prehospital care of head injury"), cite(BTF_PRE, "Treatment: hyperventilation and hyperosmolar therapy — Rec D"), cite(MOH, "Ch. 7 (p. 37)")),
        ),
        ProtocolNote(
            "Tranexamic acid regimens differ: NICE NG232 — 2 g IV bolus within 2 h for GCS ≤12; CRASH-3 — 1 g over 10 min then 1 g over 8 h within 3 h, primary outcome RR 0.94 (95% CI 0.86–1.02); by severity, benefit in mild–moderate TBI (RR 0.78) but not severe TBI (RR 0.99); BTF prehospital — not generally indicated before hospital. MoHFW 2025 gives tranexamic acid only for the severely injured, not specifically for TBI.",
            listOf(cite(NG232, "Rec 1.3.17"), cite(CRASH3, "Abstract — Findings"), cite(EU, "R23 rationale"), cite(BTF_PRE, "Rec E (hyperventilation and hyperosmolar therapy)"), cite(MOH, "Ch. 2 Circulation with haemorrhage control (p. 15)")),
        ),
        ProtocolNote(
            "Seizure prophylaxis duration differs: BTF 4th edition and SIBICC 2019 limit it to the first week; ICMR STW 2024 lists levetiracetam for 1–3 months.",
            listOf(cite(BTF, "Table 1 — Seizure prophylaxis"), cite(SIBICC, "Fig. 1; Fig. 2"), cite(ICMR_HEAD, "Medical management")),
        ),
        ProtocolNote(
            "Admission of mild injury differs: ICMR STW 2024 admits for amnesia, any history of loss of consciousness, moderate–severe headache, intoxication, skull fracture, associated injury or abnormal CT; NICE NG232's admission criteria do not include loss of consciousness or amnesia alone.",
            listOf(cite(ICMR_HEAD, "Mild head injury pathway — admit"), cite(NG232, "Rec 1.9.1")),
        ),
        ProtocolNote(
            "Scope: the ATLS manual is proprietary and is not used as a source. BTF guidance comes from high-income settings; the BTF 4th edition notes that technology-based monitoring is often unavailable in low- and middle-income countries and decisions may rest on clinical assessment alone.",
            listOf(cite(BTF, "Monitoring recommendations — discussion"), cite(MOH), cite(ICMR_HEAD)),
        ),
    ),
    citations = listOf(
        cite(MOH), cite(ICMR_HEAD), cite(NG232), cite(BTF), cite(BTF_PRE), cite(CRASH3), cite(CRASH), cite(SIBICC),
        cite(EU), cite(NG39), cite(NG24), cite(FDA_PHT), cite(FDA_FOS),
    ),
)

// ================================================================================================ Spinal injury
private val spinalInjury = Protocol(
    id = "spinal-injury",
    category = ProtocolCategory.Trauma,
    title = "Spinal injury",
    summary = "Suspecting and protecting the spine, clinical clearance (Canadian C-spine rule, NEXUS), spinal motion " +
        "restriction, imaging, cord-injury airway, neurogenic shock and MAP targets, the methylprednisolone controversy, " +
        "early ward care and transfer — attributed to MoHFW 2025, ICMR STW 2024, NICE NG41, AANS/CNS 2013, AO Spine and CSCM 2008.",
    keywords = listOf(
        "spinal injury", "spine injury", "spinal trauma", "spinal cord injury", "SCI", "cervical spine", "C-spine",
        "cervical spine injury", "neck injury", "neck trauma", "whiplash", "thoracolumbar fracture", "back injury",
        "collar", "cervical collar", "hard collar", "Philadelphia collar", "spine board", "long board", "log roll",
        "spinal motion restriction", "SMR", "spinal immobilisation", "C-spine clearance", "Canadian C-spine rule",
        "NEXUS", "paraplegia", "quadriplegia", "tetraplegia", "neurogenic shock", "spinal shock", "priapism",
        "methylprednisolone", "MPSS", "NASCIS", "Solu-Medrol", "ASIA", "Frankel", "TLICS", "SLIC", "SCIWORA",
        "diving injury", "fall from height", "gardan ki chot", "reedh ki haddi", "reedh ki haddi ki chot", "kamar ki chot",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Suspect spinal injury and protect the spine",
            timing = "From first contact",
            points = listOf(
                "NICE NG41: <C>ABCDE with manual in-line stabilisation of the cervical spine, especially during airway interventions; avoid moving the rest of the spine.",
                "RCUK/ERC 2025 first aid: opening the airway has priority over in-line immobilisation; a jaw thrust keeps the neck neutral.",
                "ICMR STW 2024: always rule out spine injury in polytrauma, especially if unconscious; ask about neck, back or limb pain; rapid sensorimotor examination; look for priapism.",
                "MoHFW 2025: presume cervical spine injury with any head or maxillofacial injury until proven otherwise; weakness of any limb suggests it.",
                "MoHFW 2025: suspect thoracolumbar injury with paraplegia or a sensory level on the chest or abdomen.",
                "ICMR STW 2024 signs: bradycardia with hypotension, sensorimotor deficit in arms or legs, laboured breathing, priapism, inability to void.",
            ),
            checklistTitle = "NICE NG41 — full in-line immobilisation if any is present or cannot be assessed",
            checklist = listOf(
                "Significant distracting injury",
                "Under the influence of drugs or alcohol",
                "Confused or uncooperative",
                "Reduced level of consciousness",
                "Any spinal pain",
                "Hand or foot weakness",
                "Altered or absent sensation in the hands or feet",
                "Priapism (unconscious or exposed male)",
                "Past spinal problems, previous spinal surgery, or conditions predisposing to spinal instability",
            ),
            citations = listOf(
                cite(NG41, "Rec 1.1.1–1.1.4, 1.4.2–1.4.4"),
                cite(RCUK_FA, "Cervical spinal motion restriction"),
                cite(ICMR_SPINE, "When to suspect; Clinical presentation"),
                cite(MOH, "Ch. 2 Secondary survey — cervical spine (p. 17); Ch. 9 Spine trauma — screening (p. 45)"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Clear the cervical spine clinically",
            timing = "Alert (GCS 15) and stable patients",
            points = listOf(
                "NICE NG41: assess suspected cervical injury with the Canadian C-spine rule. MoHFW 2025: use NEXUS and the Canadian rule to clear the spine clinically.",
                "NICE NG41: test neck rotation only with no high-risk factor and at least one low-risk factor. MoHFW 2025: never force the patient's neck.",
                "NICE NG41: no immobilisation or imaging with a low-risk factor, no pain and active rotation of 45° to the left and right, and none of the thoracic or lumbosacral factors below (rec 1.1.7).",
                "Stiell 2003 (alert, stable patients): the Canadian rule was more sensitive (99.4% vs 90.7%) and more specific (45.1% vs 36.8%) than NEXUS.",
                "MoHFW 2025: image the cervical spine in every patient with altered sensorium. NICE NG41: the Canadian rule is hard to apply in children — consider developmental stage.",
            ),
            checklistTitle = "NICE NG41 — thoracic or lumbosacral spine: immobilise and image if any",
            checklist = listOf(
                "Age 65 or over with thoracic or lumbosacral pain",
                "Dangerous mechanism — fall >3 m, axial load to head or base of spine, high-speed collision, rollover, lap belt only, ejection, motorised recreational vehicle, bicycle or horse-riding accident",
                "Pre-existing spinal pathology, or known or at risk of osteoporosis (e.g. steroid use)",
                "Suspected spinal fracture in another region of the spine",
                "Paraesthesia, weakness or numbness; motor or sensory deficit on examination",
                "New deformity, or bony midline tenderness on palpation or percussion",
                "Midline or spinal pain on coughing",
                "Pain or abnormal neurological symptoms on mobilisation (sit, stand, step, walk) — stop if this occurs",
            ),
            scoreIds = listOf("canadian-c-spine", "nexus-c-spine"),
            citations = listOf(
                cite(NG41, "Rec 1.1.5–1.1.10, 1.4.5–1.4.8"),
                cite(MOH, "Ch. 9 Spine trauma — screening; Figure 14 (pp. 45–46)"),
                cite(CCR_VS_NEXUS, "Abstract — Results"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Spinal motion restriction",
            timing = "From the scene to definitive care (CSCM 2008)",
            points = listOf(
                "NICE NG41, adults: manual stabilisation, sized semi-rigid collar (not with a compromised airway or deformity such as ankylosing spondylitis), scoop stretcher, head blocks and tape, ideally a vacuum mattress.",
                "NICE NG41: devices can be counterproductive; let agitated or distressed people, including children, find a comfortable position with manual in-line stabilisation.",
                "RCUK/ERC 2025 first aid: an alert person can hold the neck in a comfortable, stable position; trained responders may use collars selectively.",
                "MoHFW 2025: semi-rigid collar in all patients until cervical injury is ruled out; do not reduce an obvious deformity; log roll with at least four people.",
                "NICE NG41: no transport on a longboard — extrication only. MoHFW 2025: spine board for ≤2 h. CSCM 2008: move onto a padded surface early in the ED.",
                "CSCM 2008: with confirmed spinal or cord injury, keep the spine immobilised until definitive treatment; log roll as a unit.",
            ),
            citations = listOf(
                cite(NG41, "Rec 1.1.11–1.1.13, 1.1.19"),
                cite(RCUK_FA, "Cervical spinal motion restriction"),
                cite(MOH, "Ch. 2 Secondary survey (p. 17); Ch. 9 Spine trauma — spinal motion restriction, Figures 13 and 15 (pp. 45, 47)"),
                cite(CSCM, "Recommendations 7–8, 11"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Imaging",
            timing = "Urgent, and interpreted immediately (NICE NG41)",
            points = listOf(
                "NICE NG41, adults: CT when the Canadian rule indicates cervical imaging, or for strongly suspected thoracolumbar injury with neurological signs or symptoms.",
                "NICE NG41: MRI after CT for any neurological abnormality that may be cord injury, even if CT is normal. CSCM 2008: cord injury can occur without bony abnormality (SCIWORA).",
                "NICE NG41, under 16: MRI for strong suspicion of cervical cord or column injury; consider plain X-rays if suspicion persists. With head injury, NICE NG232 applies — see Head injury.",
                "NICE NG41: thoracolumbar injury without neurological signs — X-ray first, CT if abnormal or clinically suspicious; image the whole spine after a new fracture.",
                "MoHFW 2025: CT is best (occiput to T1 for the neck); lateral X-ray must show skull base to T1, plus open-mouth odontoid, AP and lateral thoracolumbar views.",
                "CSCM 2008: stiff spine (ankylosing spondylitis, DISH) with midline tenderness — suspect a fracture; consider CT or MRI if the X-ray is negative.",
            ),
            citations = listOf(
                cite(NG41, "Rec 1.5.1–1.5.11"),
                cite(NG232, "Rec 1.6.1–1.6.9"),
                cite(MOH, "Ch. 9 Spine trauma — radiographic evaluation (pp. 45–46)"),
                cite(CSCM, "Recommendations 25–28"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Airway and breathing in cervical cord injury",
            timing = "Primary survey, then reassess repeatedly",
            points = listOf(
                "MoHFW 2025: keep a low threshold to intubate when the fracture is at C5 or above.",
                "MoHFW 2025: single breath count <20 — move to the red area; <15 — intubate immediately, whatever the saturation.",
                "CSCM 2008: anticipate bradycardia and hypotension during intubation of tetraplegic patients; avoid succinylcholine after the first 48 h.",
                "NICE NG41: suspected cord injury (over 4 years) — complete an ASIA chart early in the ED; record vital capacity (over 7 years) and ability to cough.",
                "AANS/CNS 2013 (Level III): manage acute cervical cord injury in an ICU or monitored setting with cardiac, haemodynamic and respiratory monitoring.",
                "ICMR STW 2024: consider early tracheostomy (<7 days) in high cervical (C1–C5) injury.",
            ),
            citations = listOf(
                cite(MOH, "Ch. 9 Spine trauma — supportive management, airway and breathing (p. 47)"),
                cite(CSCM, "Recommendations 15, 43–44"),
                cite(NG41, "Rec 1.9.9"),
                cite(AANS_CP, "Recommendations (Level III)"),
                cite(ICMR_SPINE, "Ancillary procedures"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Neurogenic shock and blood pressure targets",
            timing = "Correct SBP <90 mm Hg as soon as possible (AANS/CNS 2013)",
            points = listOf(
                "MoHFW 2025: suspect neurogenic shock with persistent hypotension without active bleeding, or bradycardia with hypotension. CSCM 2008: exclude other injuries first — see Traumatic haemorrhage and shock.",
                "MoHFW 2025: fluid challenge of 1 L at most; if there is no improvement, start vasopressor(s).",
                "AANS/CNS 2013 (Level III): correct SBP <90 mm Hg as soon as possible; keep MAP 85–90 mm Hg for the first 7 days.",
                "AO Spine 2024 (weak, very low-quality evidence): raise MAP to at least 75–80 mm Hg but do not actively augment it beyond 90–95 mm Hg (do not lower a MAP that is higher without support), for 3–7 days.",
                "ICMR STW 2024: goal MAP ≥85 mm Hg in blunt or incomplete penetrating injury, ≥65 mm Hg in complete penetrating injury; noradrenaline 0.1–0.5 mcg/kg/min.",
                "CSCM 2008: monitor and treat symptomatic bradycardia; monitor and regulate temperature.",
            ),
            doses = listOf(
                StepDose(
                    "Noradrenaline infusion (ICMR STW 2024)",
                    WeightDose(0.1, 0.5, unit = "mcg", per = "per minute, IV infusion"),
                    note = "Titrated to the MAP goal",
                ),
            ),
            citations = listOf(
                cite(MOH, "Ch. 9 Spine trauma — circulation (p. 47)"),
                cite(CSCM, "Recommendations 16–21"),
                cite(AANS_CP, "Recommendations (Level III)"),
                cite(AO_MAP, "Recommendations on MAP target and duration of MAP augmentation"),
                cite(ICMR_SPINE, "Ancillary procedures"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Steroids and neuroprotective drugs",
            timing = "AO Spine 2017 and ICMR STW 2024 consider methylprednisolone only within 8 h of injury",
            points = listOf(
                "NICE NG41: do not use methylprednisolone, nimodipine or naloxone for neuroprotection after acute traumatic spinal cord injury. MoHFW 2025: do not give steroids.",
                "AANS/CNS 2013 (Level I): methylprednisolone is not recommended — no Class I or II evidence of benefit; high-dose steroids cause harm, including death.",
                "AO Spine 2017 (weak): a 24-h high-dose methylprednisolone infusion may be offered within 8 h of injury; not after 8 h; no 48-h infusion.",
                "ICMR STW 2024: MPSS 30 mg/kg bolus (FDA label: over at least 30 min), then 5.4 mg/kg/h for 23 h — consider within 8 h with incomplete injury, aware of high-dose steroid complications.",
                "ICMR STW 2024: no role after 8 h or in complete injury; contraindicated with thoracic or abdominal visceral injury. CSCM 2008: stop early if neurology is normal or recovered.",
            ),
            doses = listOf(
                StepDose(
                    "Methylprednisolone sodium succinate — bolus (ICMR STW 2024)",
                    WeightDose(30.0, 30.0, unit = "mg", per = "IV over at least 30 min (FDA label), not as a push"),
                    note = "ICMR: consider only within 8 h with incomplete injury. FDA label: arrhythmias and cardiac arrest reported after >0.5 g in <10 min; bradycardia with large doses, possibly unrelated to the rate. NICE NG41, MoHFW 2025 and AANS/CNS 2013 advise against",
                ),
                StepDose(
                    "Methylprednisolone sodium succinate — infusion (ICMR STW 2024)",
                    WeightDose(5.4, 5.4, unit = "mg", per = "per hour for 23 h, IV infusion"),
                    note = "Follows the bolus; same restrictions",
                ),
            ),
            citations = listOf(
                cite(NG41, "Rec 1.7.3"),
                cite(MOH, "Ch. 9 Spine trauma — medications (p. 47)"),
                cite(AANS_PH, "Recommendations (Level I)"),
                cite(AO_MPSS, "Recommendations 1–3"),
                cite(ICMR_SPINE, "Appendix IV: MPSS guidelines"),
                cite(FDA_MPSS, "Dosage and administration"),
                cite(CSCM, "Recommendations 22–23"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Analgesia, bladder, skin and VTE",
            timing = "DVT prophylaxis within 72 h (ICMR STW 2024)",
            points = listOf(
                "NICE NG41: IV morphine first-line, titrated; no IV access — consider intranasal diamorphine or ketamine; ketamine in analgesic doses second-line. ICMR STW 2024: morphine or pethidine.",
                "CSCM 2008: indwelling urinary catheter at the initial assessment unless contraindicated (then suprapubic); priapism is no reason to avoid a urethral catheter.",
                "CSCM 2008: pressure-reducing mattress; turn or reposition at least every 2 h with spinal precautions; keep skin clean and dry; check under splints.",
                "ICMR STW 2024: non-ambulatory patients with deficits need DVT prophylaxis within 72 h — LMWH or fixed low-dose unfractionated heparin, for 8–12 weeks by risk.",
                "CSCM 2008: mechanical compression early; LMWH or unfractionated heparin once primary haemostasis is evident — intracranial bleeding, perispinal haematoma or haemothorax are potential contraindications until bleeding has stabilised; start stress-ulcer prophylaxis.",
                "With head injury, BTF 4th edition (Level III): LMWH or low-dose heparin risks expansion of intracranial haemorrhage — consider it once the brain injury is stable.",
            ),
            citations = listOf(
                cite(NG41, "Rec 1.2.4–1.2.7"),
                cite(ICMR_SPINE, "Primary care management; Appendix V: DVT prophylaxis"),
                cite(CSCM, "Recommendations 51–56, 62–65"),
                cite(BTF, "Table 1 — Deep vein thrombosis prophylaxis"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "Specialist care and transfer",
            timing = "Spinal cord injury centre contacted within 4 h of diagnosis (NICE NG41)",
            points = listOf(
                "NICE NG41: take suspected cord injury, immobilised, to a major trauma centre — not straight from the scene to a spinal cord injury centre.",
                "NICE NG41: the trauma team leader calls the on-call spinal surgeon immediately; the surgeon contacts the linked spinal cord injury centre within 4 h.",
                "AO Spine 2024 (strong): offer early surgery (≤24 h after injury) to adults with acute spinal cord injury at any level. ICMR STW 2024: early decompression (<72 h).",
                "ICMR STW 2024 primary care: identify, immobilise (collar, spine board, sandbags), IV Ringer's lactate, group and cross-match, catheterise, analgesia, then transfer. (European 2023 (1B): avoid Ringer's lactate in severe head trauma — see Head injury.)",
                "ICMR STW 2024: document the Frankel or ASIA grade; score TLICS or SLIC — <4 non-operative, 4 equivocal, >4 operative (its management panel says >5 for surgery).",
            ),
            citations = listOf(
                cite(NG41, "Rec 1.3.2–1.3.5, 1.6.1–1.6.4"),
                cite(AO_SURG, "Key question 1 recommendation"),
                cite(ICMR_SPINE, "Primary, secondary and tertiary care; Appendices I–III; Ancillary procedures"),
                cite(EU, "R15"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Methylprednisolone is a real controversy: NICE NG41, MoHFW 2025 and AANS/CNS 2013 (Level I) advise against it; AO Spine 2017 suggests a 24-h infusion within 8 h as an option (weak recommendation, small motor benefit); ICMR STW 2024 lists it for incomplete injury within 8 h; CSCM 2008 finds no evidence for any neuroprotective drug.",
            listOf(cite(NG41, "Rec 1.7.3"), cite(MOH, "Ch. 9 (p. 47)"), cite(AANS_PH, "Recommendations"), cite(AO_MPSS, "Recommendations 1–3"), cite(ICMR_SPINE, "Appendix IV"), cite(CSCM, "Recommendation 22")),
        ),
        ProtocolNote(
            "Collars differ: MoHFW 2025 applies a semi-rigid collar to all patients until cervical injury is ruled out; NICE NG41 immobilises selectively by risk factors and the Canadian rule and warns that devices can be counterproductive; RCUK/ERC 2025 first aid leaves collars to trained responders.",
            listOf(cite(MOH, "Ch. 2 (p. 17)"), cite(NG41, "Rec 1.1.9–1.1.13"), cite(RCUK_FA, "Cervical spinal motion restriction")),
        ),
        ProtocolNote(
            "MAP targets differ: AANS/CNS 2013 — 85–90 mm Hg for 7 days; AO Spine 2024 — augment to at least 75–80 mm Hg and no higher than 90–95 mm Hg (a higher unsupported MAP is not lowered), for 3–7 days; ICMR STW 2024 — ≥85 mm Hg (blunt or incomplete penetrating) or ≥65 mm Hg (complete penetrating).",
            listOf(cite(AANS_CP, "Recommendations"), cite(AO_MAP, "MAP target and duration"), cite(ICMR_SPINE, "Ancillary procedures")),
        ),
        ProtocolNote(
            "Decision rules: NICE NG41 uses the Canadian C-spine rule only; MoHFW 2025 uses both NEXUS and the Canadian rule, and its Figure 14 shows three of the five Canadian low-risk factors.",
            listOf(cite(NG41, "Rec 1.1.5, 1.4.5"), cite(MOH, "Ch. 9 — Figure 14 (p. 46)")),
        ),
        ProtocolNote(
            "Timing of decompression differs: AO Spine 2024 — early surgery within 24 h (strong recommendation, moderate evidence; no recommendation on ultra-early surgery); ICMR STW 2024 — early decompression within 72 h.",
            listOf(cite(AO_SURG, "Key questions 1–2"), cite(ICMR_SPINE, "Ancillary procedures")),
        ),
        ProtocolNote(
            "Scope: the ATLS manual is proprietary and is not used as a source; ICMR STW 2024 and MoHFW 2025 build on ATLS and are reported as published. CSCM 2008 is the latest edition of the Consortium guideline found (November 2026).",
            listOf(cite(ICMR_SPINE, "Primary care management"), cite(MOH), cite(CSCM)),
        ),
    ),
    citations = listOf(
        cite(MOH), cite(ICMR_SPINE), cite(NG41), cite(AANS_CP), cite(AANS_PH), cite(AO_MPSS), cite(AO_MAP), cite(AO_SURG),
        cite(CSCM), cite(RCUK_FA), cite(CCR_VS_NEXUS), cite(FDA_MPSS), cite(BTF), cite(EU), cite(NG232),
    ),
)

internal val headSpineProtocols: List<Protocol> = listOf(headInjury, spinalInjury)
