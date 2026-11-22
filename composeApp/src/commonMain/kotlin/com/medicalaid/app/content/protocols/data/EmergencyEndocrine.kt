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

/*
 * Endocrine emergencies in adults: DKA, HHS, hypoglycaemia, adrenal crisis, thyroid storm, myxoedema coma. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them; every point is attributed
 *   ("ADA/EASD 2024: …", "JBDS 2023: …", "ICMR STW: …", "ATA 2016: …", "JTA 2016: …").
 * - Glucose in mg/dL; where the source uses mmol/L the mmol/L value is given in brackets (mg/dL = mmol/L × 18).
 * - ADA/EASD/JBDS/AACE/DTS 2024 consensus: read from the open-access Diabetologia co-publication (identical text).
 *   Its Figures 2 and 4 are images and were not read; the diagnostic criteria are taken from ADA Standards 2026
 *   Table 16.1, which reproduces them.
 * - Cited by id from Sources.kt: ADA Standards 2026 Section 6 ("ada-s6"), ATA 2014 hypothyroidism ("ata-2014"),
 *   ICMR STW Hypothyroidism 2022 ("icmr-stw-hypothyroid-2022").
 * - Bodies without an Authority entry (Society for Endocrinology, Japan Thyroid Association) use Authority.Paper
 *   with the body named in the document field.
 * - Abstract-only sources say so in their status line. FDA labels are US labels; availability in India not verified.
 * - No ICMR STW or MoHFW guidance was found for HHS, adrenal crisis, thyroid storm or myxoedema coma
 *   (ICMR STW list checked November 2026).
 */

internal val endocrineEmergencySources: List<Source> = listOf(
    // Hyperglycaemic crises ---------------------------------------------------------------------
    Source(
        "em-endo-ada-hgc-2024", Authority.ADA,
        "ADA/EASD/JBDS/AACE/DTS consensus report — Diabetes Care 2024;47(8):1257–1275",
        "Hyperglycemic Crises in Adults With Diabetes: A Consensus Report", "2024",
        "https://doi.org/10.2337/dci24-0032",
        status = "Co-published in Diabetologia 2024;67(8):1455–1479 (open access); reviewed from that text",
    ),
    Source(
        "em-endo-ada-s16-2026", Authority.ADA, "Standards of Care 2026 · Section 16 — Diabetes Care 2026;49(Suppl 1):S339–S355",
        "16. Diabetes Care in the Hospital", "2026",
        "https://doi.org/10.2337/dc26-S016",
    ),
    Source(
        "em-endo-jbds-dka-2023", Authority.JBDS, "JBDS 02 guideline (revised March 2023)",
        "The Management of Diabetic Ketoacidosis in Adults", "2023",
        "https://abcd.care/sites/default/files/site_uploads/JBDS_Guidelines_Current/JBDS_02_DKA_Guideline_with_QR_code_March_2023.pdf",
        status = "Summary published as Diabet Med 2022;39(6):e14788",
    ),
    Source(
        "em-endo-jbds-hhs-2022", Authority.JBDS, "JBDS 06 guideline (February 2022)",
        "The Management of Hyperosmolar Hyperglycaemic State (HHS) in Adults", "2022",
        "https://abcd.care/sites/default/files/site_uploads/JBDS_Guidelines_Current/JBDS_06_The_Management_of_Hyperosmolar_Hyperglycaemic_State_HHS_%20in_Adults_FINAL_0.pdf",
        status = "Published as Diabet Med 2023;40(3):e15005",
    ),
    Source(
        "em-endo-icmr-stw-dka-2022", Authority.ICMR, "Standard Treatment Workflow (ICD-10 E11.10)",
        "Diabetic Ketoacidosis", "2022",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567244_diabetic_ketoacidosis.pdf",
        status = "July 2022 (Department of Health Research, MoHFW)",
    ),
    // Hypoglycaemia -----------------------------------------------------------------------------
    Source(
        "em-endo-jbds-hypo-2023", Authority.JBDS, "JBDS 01 guideline (revised January 2023)",
        "The Hospital Management of Hypoglycaemia in Adults with Diabetes Mellitus", "2023",
        "https://abcd.care/sites/default/files/site_uploads/JBDS_Guidelines_Current/JBDS_01_Hypo_Guideline_with_QR_code_January_2023.pdf",
    ),
    Source(
        "em-endo-jbds-front-door-2023", Authority.JBDS, "JBDS 16 guideline (May 2023)",
        "Diabetes at the Front Door — a guideline for dealing with glucose related emergencies at the time of acute hospital admission",
        "2023",
        "https://abcd.care/sites/default/files/site_uploads/JBDS_Guidelines_Current/JBDS_16_Diabetes_at_the_Front_Door_Guideline_with_QR_code_May_2023.pdf",
    ),
    Source(
        "em-endo-icmr-stw-t1dm-2022", Authority.ICMR, "Standard Treatment Workflow (ICD-10 E10)",
        "Diabetes Mellitus Type 1", "2022",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567249_diabetes_mellitus_type_1.pdf",
        status = "July 2022; its hypoglycaemia box does not separate adults and children",
    ),
    Source(
        "em-endo-fda-glucagon-inj", Authority.FDA, "Prescribing information (DailyMed)",
        "Glucagon for Injection 1 mg — Amphastar Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8cc40354-b5ac-45b6-a211-93b44444f2a3",
        status = "Label effective August 2026",
    ),
    Source(
        "em-endo-fda-glucagon-nasal", Authority.FDA, "Prescribing information (DailyMed)",
        "Baqsimi (glucagon) nasal powder 3 mg — Amphastar Pharmaceuticals", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=f1f5df9b-872f-44e5-a18f-f0b68e7e9254",
        status = "Label effective August 2025; availability in India not verified",
    ),
    Source(
        "em-endo-octreotide-rct-2008", Authority.Paper,
        "Randomised double-blind placebo-controlled trial, Ann Emerg Med 2008;51(4):400–406 (Fasano et al.)",
        "Comparison of octreotide and standard therapy versus standard therapy alone for the treatment of sulfonylurea-induced hypoglycemia",
        "2008",
        "https://doi.org/10.1016/j.annemergmed.2007.06.493",
        status = "Single emergency department, 40 patients; reviewed from the abstract",
    ),
    Source(
        "em-endo-octreotide-review-2010", Authority.Paper,
        "Review, J Med Toxicol 2010;6(2):199–206 (Dougherty & Klein-Schwartz, Maryland Poison Center)",
        "Octreotide's role in the management of sulfonylurea-induced hypoglycemia", "2010",
        "https://doi.org/10.1007/s13181-010-0064-z",
        status = "Reviewed from the abstract only; dosing section not read",
    ),
    // Adrenal crisis ----------------------------------------------------------------------------
    Source(
        "em-endo-endosoc-pai-2016", Authority.EndoSoc, "Clinical Practice Guideline — J Clin Endocrinol Metab 2016;101(2):364–389",
        "Diagnosis and Treatment of Primary Adrenal Insufficiency", "2016",
        "https://doi.org/10.1210/jc.2015-1710",
    ),
    Source(
        "em-endo-sfe-adrenal-crisis-2016", Authority.Paper,
        "Society for Endocrinology (UK) emergency guidance — Endocr Connect 2016;5(5):G1–G3",
        "Emergency management of acute adrenal insufficiency (adrenal crisis) in adult patients", "2016",
        "https://doi.org/10.1530/EC-16-0054",
    ),
    Source(
        "em-endo-ese-gc-ai-2024", Authority.ESE,
        "ESE and Endocrine Society joint guideline — Eur J Endocrinol 2024;190(5):G25–G51",
        "Diagnosis and therapy of glucocorticoid-induced adrenal insufficiency", "2024",
        "https://doi.org/10.1093/ejendo/lvae029",
    ),
    // Thyroid emergencies -----------------------------------------------------------------------
    Source(
        "em-endo-ata-hyperthyroid-2016", Authority.ATA, "Guideline — Thyroid 2016;26(10):1343–1421",
        "2016 American Thyroid Association Guidelines for Diagnosis and Management of Hyperthyroidism and Other Causes of Thyrotoxicosis",
        "2016",
        "https://doi.org/10.1089/thy.2016.0229",
        status = "No newer ATA hyperthyroidism guideline found (checked November 2026)",
    ),
    Source(
        "em-endo-jta-thyroid-storm-2016", Authority.Paper,
        "Japan Thyroid Association and Japan Endocrine Society guideline — Endocr J 2016;63(12):1025–1064",
        "2016 Guidelines for the management of thyroid storm (First edition)", "2016",
        "https://doi.org/10.1507/endocrj.EJ16-0336",
    ),
    Source(
        "em-endo-myxoedema-score-2014", Authority.Paper, "Original paper, Endocr Pract 2014;20(8):808–817 (Popoveniuc et al.)",
        "A diagnostic scoring system for myxedema coma", "2014",
        "https://doi.org/10.4158/EP13460.OR",
        status = "Derivation in 21 patients plus 22 published cases; reviewed from the abstract",
    ),
)

private const val CONS = "em-endo-ada-hgc-2024"
private const val S16 = "em-endo-ada-s16-2026"
private const val S6 = "ada-s6"
private const val JBDS_DKA = "em-endo-jbds-dka-2023"
private const val JBDS_HHS = "em-endo-jbds-hhs-2022"
private const val JBDS_HYPO = "em-endo-jbds-hypo-2023"
private const val JBDS_FD = "em-endo-jbds-front-door-2023"
private const val ICMR_DKA = "em-endo-icmr-stw-dka-2022"
private const val ICMR_T1 = "em-endo-icmr-stw-t1dm-2022"
private const val ICMR_HYPOTHY = "icmr-stw-hypothyroid-2022"
private const val FDA_GLUCAGON = "em-endo-fda-glucagon-inj"
private const val FDA_NASAL = "em-endo-fda-glucagon-nasal"
private const val OCT_RCT = "em-endo-octreotide-rct-2008"
private const val OCT_REVIEW = "em-endo-octreotide-review-2010"
private const val ES_PAI = "em-endo-endosoc-pai-2016"
private const val SFE = "em-endo-sfe-adrenal-crisis-2016"
private const val ESE = "em-endo-ese-gc-ai-2024"
private const val ATA16 = "em-endo-ata-hyperthyroid-2016"
private const val JTA = "em-endo-jta-thyroid-storm-2016"
private const val ATA14 = "ata-2014"
private const val MC_SCORE = "em-endo-myxoedema-score-2014"

internal val endocrineEmergencyProtocols: List<Protocol> = listOf(
    // =============================================================================================
    // 1. Diabetic ketoacidosis
    // =============================================================================================
    Protocol(
        id = "dka",
        category = ProtocolCategory.Metabolic,
        title = "Diabetic ketoacidosis (DKA) — adult",
        summary = "Diagnosis and severity, fluids, potassium, fixed-rate insulin, dextrose, bicarbonate and phosphate, " +
            "SGLT2-inhibitor euglycaemic DKA, resolution and switch to SC insulin — ADA/EASD 2024, JBDS-IP and ICMR STW.",
        keywords = listOf(
            "DKA", "diabetic ketoacidosis", "ketoacidosis", "ketones", "ketonuria", "ketonaemia", "ketonemia",
            "beta-hydroxybutyrate", "BOHB", "euglycaemic DKA", "euglycemic DKA", "SGLT2", "SGLT2 inhibitor", "gliflozin",
            "dapagliflozin", "empagliflozin", "canagliflozin", "insulin infusion", "FRIII", "fixed rate insulin",
            "Kussmaul", "type 1 diabetes", "T1DM", "high sugar", "uncontrolled diabetes", "hyperglycaemic crisis",
            "hyperglycemic crisis", "metabolic acidosis", "anion gap", "insulin omission",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise and confirm DKA",
                timing = "At presentation",
                points = listOf(
                    "ADA/EASD 2024 (all three): glucose ≥200 mg/dL (11.1 mmol/L) or known diabetes; β-hydroxybutyrate ≥3.0 mmol/L or urine ketones ≥2+; pH <7.3 and/or bicarbonate <18 mmol/L.",
                    "JBDS 2023 (all three): glucose >198 mg/dL (11.0 mmol/L) or known diabetes; blood ketones >3.0 mmol/L or ketonuria ≥2+; bicarbonate <15 mmol/L and/or venous pH <7.3.",
                    "ICMR STW: suspect DKA with abdominal pain, recurrent vomiting, rapid laboured breathing or altered sensorium; it may be the first presentation of type 1 diabetes.",
                    "ADA/EASD 2024: send glucose, electrolytes, venous gas, blood count and ketones; do an ECG. Prefer blood β-hydroxybutyrate — urine nitroprusside tests do not detect it.",
                    "ADA/EASD 2024: about 10% present with glucose <200 mg/dL (euglycaemic DKA) — SGLT2 inhibitors, pregnancy, alcohol, liver failure or reduced food intake.",
                    "ADA/EASD 2024: consider other ketoacidoses — starvation ketosis, alcoholic ketoacidosis, ketosis of pregnancy and hyperemesis.",
                ),
                tool = ProtocolTool.AcidBase,
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(CONS, "Section 3 — Diagnostic criteria for DKA; Clinical presentation"),
                    cite(S16, "Table 16.1"),
                    cite(JBDS_DKA, "Diagnosis of DKA; Differential diagnosis"),
                    cite(ICMR_DKA, "When to suspect DKA; Investigations"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Severity, level of care and precipitants",
                timing = "Within the first hour",
                points = listOf(
                    "ADA/EASD 2024 Table 2 — mild: pH 7.25–7.30 or HCO₃⁻ 15–18, alert; moderate: pH 7.0–7.25, HCO₃⁻ 10–<15, alert/drowsy; severe: pH <7.0, HCO₃⁻ <10, ketones >6.0, stupor/coma.",
                    "ICMR STW uses the same pH, bicarbonate and sensorium bands; severe DKA — ICU. ADA/EASD 2024: severe DKA, critical illness as precipitant or altered mental status — ICU.",
                    "JBDS 2023: any severity marker — consultant review and consider HDU/level 2. A drowsy patient needs critical-care assessment; consider an NG tube with airway protection.",
                    "ADA/EASD 2024: uncomplicated mild–moderate DKA may be treated with SC rapid-acting analogue every 1–2 h in the ED or step-down unit; not for severe DKA or HHS.",
                    "ADA/EASD 2024: commonest precipitants — infection (14–58%), insulin omission, new diabetes; also SGLT2 inhibitors, glucocorticoids, antipsychotics, checkpoint inhibitors, MI, stroke, pancreatitis, alcohol.",
                    "ICMR STW: ask about skipped insulin, fever, cough, loose stools, burning micturition. Temperature is unreliable; leucocytosis suggests infection only above 15 × 10⁹/L.",
                ),
                checklistTitle = "JBDS 2023 — any one may indicate severe DKA",
                checklist = listOf(
                    "Blood ketones >6.0 mmol/L",
                    "Bicarbonate <5.0 mmol/L",
                    "Venous or arterial pH <7.0",
                    "Potassium <3.5 mmol/L on admission",
                    "GCS <12 or abnormal AVPU",
                    "SpO₂ <92% on air (normal baseline respiratory function)",
                    "Systolic BP <90 mmHg",
                    "Pulse >100 or <60 bpm",
                    "Anion gap >16 [(Na⁺ + K⁺) − (Cl⁻ + HCO₃⁻)]",
                ),
                scoreIds = listOf("gcs", "news2"),
                citations = listOf(
                    cite(CONS, "Table 2; Section 4 (level of care; Insulin); Section 1 — Risk factors, Table 1"),
                    cite(ICMR_DKA, "Assess severity of DKA; Look & address precipitating factors; Common errors/pitfalls"),
                    cite(JBDS_DKA, "Assessment of severity; 0 to 60 minutes, Action 1"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "IV fluids first",
                timing = "T = 0 — start before insulin",
                points = listOf(
                    "JBDS 2023: SBP <90 mmHg — 0.9% saline 500 mL over 10–15 min, repeat if still <90; most need 500–1000 mL rapidly. Consider sepsis or heart failure.",
                    "JBDS 2023 (SBP ≥90; 70 kg guide): 0.9% saline 1 L over the first hour, then 1 L with KCl over 2 h, 2 h, 4 h, 4 h and 6 h; reassess at 12 h.",
                    "ADA/EASD 2024: without renal or cardiac compromise, isotonic saline or balanced crystalloid 500–1000 mL/h for 2–4 h; correct deficits over 24–48 h.",
                    "ICMR STW: 0.9% saline 1 L over the first hour, then 250–500 mL/h. Starting insulin before IV fluids is listed as an error.",
                    "ADA/EASD 2024: older adults, heart failure or dialysis — 250 mL boluses with frequent review. JBDS 2023: caution at 18–25 years, older age, pregnancy, heart or kidney failure.",
                    "ADA/EASD 2024: balanced crystalloids may resolve DKA faster with less hyperchloraemic acidosis. JBDS 2023 keeps 0.9% saline as the ward default (premixed potassium).",
                ),
                citations = listOf(
                    cite(JBDS_DKA, "0 to 60 minutes, Action 2; Controversial areas 3–4"),
                    cite(CONS, "Section 4 — Fluid therapy"),
                    cite(ICMR_DKA, "Treatment; Common errors/pitfalls"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Potassium — before and during insulin",
                timing = "Before insulin; recheck 2 h after starting it",
                points = listOf(
                    "ADA/EASD 2024: K⁺ <3.5 mmol/L — give potassium at 10 mmol/h and delay insulin until K⁺ >3.5 (risk of arrhythmia and respiratory muscle weakness).",
                    "ADA/EASD 2024: start replacement once K⁺ <5.0 mmol/L; 20–30 mmol per litre of fluid usually keeps K⁺ at 4–5 mmol/L.",
                    "JBDS 2023 (first 24 h): K⁺ >5.5 — none; 3.5–5.5 — 40 mmol/L in the infusion if passing urine; <3.5 — senior review, additional potassium needed.",
                    "ICMR STW: replace potassium before insulin if K⁺ <3.3 mmol/L (or ECG changes); give 10–20 mmol/h with the insulin infusion if K⁺ <5.5.",
                    "ADA/EASD 2024: check K⁺ 2 h after starting insulin, then every 4 h. K⁺ ≤2.5 mmol/L during treatment was associated with higher mortality.",
                    "JBDS 2023: venous K⁺ at 60 min, 2 h, then 2-hourly; if outside 4.0–5.5, recheck hourly. Concentrated potassium may need level 2/3 care.",
                ),
                citations = listOf(
                    cite(CONS, "Section 4 — Potassium; Table 3 (hypokalaemia)"),
                    cite(JBDS_DKA, "0 to 60 minutes, Action 3; 60 minutes to 6 hours, Action 2; Complications 1"),
                    cite(ICMR_DKA, "Treatment"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Fixed-rate IV insulin",
                timing = "Once IV fluids are running (JBDS)",
                points = listOf(
                    "ADA/EASD 2024 and JBDS 2023: fixed-rate IV insulin infusion (FRIII) 0.1 units/kg/h. JBDS: 50 units soluble human insulin made up to 50 mL 0.9% saline (1 unit/mL).",
                    "JBDS 2023: no priming bolus — IM 0.1 unit/kg only if FRIII set-up is delayed. ADA/EASD 2024: 0.1 U/kg IV or IM only if a delay in venous access is expected.",
                    "ICMR STW: regular insulin 0.1 IU/kg IV bolus, then 0.1 IU/kg/h; double the rate if glucose falls by less than 10% after 1 hour.",
                    "JBDS 2023 targets: ketones fall ≥0.5 mmol/L/h (or bicarbonate rise ≥3 mmol/L/h, or glucose fall ≥54 mg/dL/h). If not met, check the pump, then add 1 unit/h hourly.",
                    "ADA/EASD 2024 and JBDS 2023: continue the usual long-acting basal insulin at the usual dose and time during the infusion.",
                    "ADA/EASD 2024: capillary glucose every 1–2 h; electrolytes, phosphate, creatinine, β-hydroxybutyrate and venous pH every 4 h. ICMR STW: hourly glucose, vitals and urine output.",
                ),
                checklistTitle = "Don'ts (as attributed)",
                checklist = listOf(
                    "Insulin before IV fluids have started (ICMR STW; JBDS 2023)",
                    "Insulin while K⁺ <3.5 mmol/L (ADA/EASD 2024; ICMR STW: <3.3)",
                    "Stopping the insulin infusion when glucose falls — add dextrose instead (ADA/EASD 2024; JBDS 2023)",
                    "Urine ketones to judge resolution (ADA/EASD 2024; JBDS 2023)",
                    "Stopping IV insulin before SC insulin has been given (ICMR STW; JBDS 2023)",
                ),
                doses = listOf(
                    StepDose(
                        label = "Insulin FRIII — initial rate (ADA/EASD 2024; JBDS 2023)",
                        dose = WeightDose(0.1, 0.1, unit = "units", per = "per hour, fixed-rate IV infusion"),
                        note = "JBDS 2023 weight table stops at 15 units/h (>150 kg); higher rates only on diabetes specialist advice. " +
                            "ADA/EASD 2024 gives no cap. JBDS: estimate the weight if it is not known.",
                        maxTotal = 15.0,
                    ),
                    StepDose(
                        label = "Insulin bolus (see attribution)",
                        dose = WeightDose(0.1, 0.1, unit = "units", per = "single dose"),
                        note = "ICMR STW: IV bolus before the 0.1 IU/kg/h infusion. JBDS 2023: IM, only if setting up the FRIII is delayed. " +
                            "ADA/EASD 2024: IV or IM, only if a delay in obtaining venous access is anticipated.",
                    ),
                ),
                citations = listOf(
                    cite(CONS, "Section 4 — Insulin; Section 4 (monitoring)"),
                    cite(JBDS_DKA, "Insulin therapy (dose tables); Metabolic treatment targets; Controversial areas (no bolus); 0 to 60 minutes, Action 4; 60 minutes to 6 hours, Action 2"),
                    cite(ICMR_DKA, "Treatment; Monitoring; Common errors/pitfalls"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Add dextrose; watch glucose, potassium and the brain",
                timing = "When glucose <250 mg/dL (ADA, ICMR) or <252 mg/dL (14 mmol/L, JBDS)",
                points = listOf(
                    "ADA/EASD 2024: glucose <250 mg/dL (13.9 mmol/L) — add 5–10% dextrose, reduce insulin to 0.05 U/kg/h and keep glucose near 200 mg/dL until ketoacidosis resolves.",
                    "JBDS 2023: glucose <252 mg/dL (14 mmol/L) — 10% glucose 125 mL/h alongside 0.9% saline; consider insulin 0.05 units/kg/h. Do not stop the FRIII.",
                    "ICMR STW: glucose <250 mg/dL — add 5% dextrose at 50 mL/h.",
                    "ADA/EASD 2024: hypoglycaemia (<70 mg/dL) in 16–28% and hypokalaemia (<3.5 mmol/L) in about 55% during treatment; glucose <40 mg/dL was associated with higher mortality.",
                    "ADA/EASD 2024: cerebral oedema is rare in adults (<0.1%) but about 30% fatal; low threshold for brain imaging if mental status changes; mannitol and ventilation suggested.",
                    "JBDS 2023: higher risk in young or physically slight adults. A falling GCS needs urgent mannitol or hypertonic saline, not delayed while awaiting imaging.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Insulin FRIII — reduced rate (ADA/EASD 2024; JBDS 2023)",
                        dose = WeightDose(0.05, 0.05, unit = "units", per = "per hour, fixed-rate IV infusion"),
                        note = "ADA/EASD 2024: when glucose <250 mg/dL. JBDS 2023: consider when glucose <14 mmol/L (252 mg/dL); " +
                            "its weight table stops at 7.5 units/h (>150 kg).",
                        maxTotal = 7.5,
                    ),
                ),
                citations = listOf(
                    cite(CONS, "Section 4 — Fluid therapy, Insulin; Table 3 (hypoglycaemia, hypokalaemia, cerebral oedema)"),
                    cite(JBDS_DKA, "Intravenous glucose concentration; Controversial areas 1; Complications 2–3; 60 minutes to 6 hours, Action 2"),
                    cite(ICMR_DKA, "Treatment"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Bicarbonate, phosphate and supportive care",
                timing = "Only on the stated criteria",
                points = listOf(
                    "ADA/EASD 2024: routine bicarbonate not recommended; consider if pH <7.0 — 100 mmol sodium bicarbonate (8.4%) in 400 mL sterile water every 2 h until pH >7.0.",
                    "JBDS 2023: bicarbonate not recommended routinely; intensive care teams occasionally use it if pH stays low and inotropes are needed.",
                    "ICMR STW: pH <7.0 — 50 mmol bicarbonate in 200 mL sterile water over 2 h; it also limits bicarbonate to pH <6.9, or <7.1 with hypotension or hyperkalaemia.",
                    "ADA/EASD 2024: phosphate only with muscle weakness, respiratory or cardiac compromise and phosphate <1.0 mmol/L — add 20–30 mmol potassium phosphate to fluids; rapid replacement can cause hypocalcaemia.",
                    "JBDS 2023: no routine phosphate; consider replacement if respiratory failure, arrhythmia or rhabdomyolysis.",
                    "JBDS 2023: prophylactic LMWH; urine output ≥0.5 mL/kg/h, catheterise if no urine by 60 min. ADA/EASD 2024: prophylactic LMWH unless thrombosis is suspected.",
                ),
                citations = listOf(
                    cite(CONS, "Section 4 — Bicarbonate, Phosphate; Table 3 (thrombosis)"),
                    cite(JBDS_DKA, "Controversial areas 5–6; 60 minutes to 6 hours, Action 1"),
                    cite(ICMR_DKA, "Treatment"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "SGLT2 inhibitors, euglycaemic DKA and pregnancy",
                timing = "At presentation",
                points = listOf(
                    "ADA/EASD 2024: SGLT2-inhibitor DKA may present with glucose <200 mg/dL; risks include insulin reduction or omission, fasting, very-low-carbohydrate diet, dehydration, alcohol and acute illness.",
                    "ADA/EASD 2024: stop the SGLT2 inhibitor on admission; in euglycaemic DKA give 5–10% dextrose with, or at the same time as, 0.9% saline.",
                    "JBDS 2023: start 10% glucose 125 mL/h straight away with FRIII 0.1 units/kg/h; reduce to 0.05 if glucose keeps falling despite the 10% glucose.",
                    "ADA/EASD 2024: SGLT2 inhibitors not recommended in type 1; after DKA in type 2, restarting is not routinely recommended. JBDS 2023: decide any restart with the diabetes team.",
                    "ADA Standards 2026: avoid SGLT2 inhibitors in severe illness, ketonaemia or ketonuria, prolonged fasting and around surgery; stop 3 days before surgery (4 for ertugliflozin).",
                    "ADA/EASD 2024 and JBDS 2023: DKA in pregnancy can occur at modest glucose levels and needs immediate senior medical and obstetric care (delivery suite or HDU).",
                ),
                citations = listOf(
                    cite(CONS, "Section 1 — Risk factors; Section 3; Table 4 (SGLT2 inhibitor; pregnancy)"),
                    cite(JBDS_DKA, "Euglycaemic DKA; DKA in pregnancy"),
                    cite(S16, "SGLT2 inhibitors; perioperative care"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Resolution and switch to subcutaneous insulin",
                timing = "Most resolve by 24 h (JBDS)",
                points = listOf(
                    "ADA/EASD 2024: resolved when ketones <0.6 mmol/L and venous pH ≥7.3 or bicarbonate ≥18 mmol/L (ideally glucose <200 mg/dL). Do not use anion gap or urine ketones.",
                    "JBDS 2023: resolution is ketones <0.6 mmol/L and venous pH >7.3 — not bicarbonate (saline-induced hyperchloraemia). Not resolved by 24 h — urgent senior specialist input.",
                    "ICMR STW: stop the infusion when eating, glucose consistently <250 mg/dL, anion gap normal and acidosis corrected; give SC insulin at least 30 min before stopping.",
                    "ADA/EASD 2024: start basal insulin 1–2 h before stopping IV insulin (ADA Standards 2026: 2–4 h). JBDS 2023: SC fast-acting insulin with a meal, stop IV 30–60 min later.",
                    "ADA/EASD 2024: insulin-naive — total daily dose 0.5–0.6 units/kg (about 0.3 with kidney failure or frailty); basal-bolus preferred. JBDS 2023: new type 1 — basal 0.25 units/kg daily.",
                    "JBDS 2023 and ADA/EASD 2024: diabetes team review; address the precipitant, insulin supply and technique, sick-day rules, ketone testing and follow-up before discharge.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Estimated total daily SC insulin — insulin-naive (ADA/EASD 2024)",
                        dose = WeightDose(0.5, 0.6, unit = "units", per = "per day, as basal-bolus"),
                        note = "ADA/EASD 2024: about 0.3 units/kg/day with risk factors for hypoglycaemia such as kidney failure or frailty. " +
                            "People already on insulin: consider the pre-admission dose.",
                    ),
                    StepDose(
                        label = "Basal insulin — newly diagnosed (ADA/EASD 2024)",
                        dose = WeightDose(0.15, 0.3, unit = "units", per = "SC once daily, or split twice daily"),
                    ),
                    StepDose(
                        label = "Basal insulin — newly diagnosed type 1 (JBDS 2023)",
                        dose = WeightDose(0.25, 0.25, unit = "units", per = "SC once daily"),
                        note = "JBDS 2023: give during the FRIII to reduce rebound ketosis when the infusion stops.",
                    ),
                ),
                citations = listOf(
                    cite(CONS, "Section 4 — Criteria for resolution; Transition to maintenance insulin; Section 7"),
                    cite(JBDS_DKA, "6 to 12 hours and 12 to 24 hours, Action 2; E. Conversion to subcutaneous insulin; 60 minutes to 6 hours, Action 4; Other considerations"),
                    cite(ICMR_DKA, "When to stop insulin infusion?"),
                    cite(S16, "Transition to subcutaneous insulin"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Insulin bolus differs: ICMR STW gives 0.1 IU/kg IV before the infusion; ADA/EASD 2024 and JBDS 2023 start the infusion without a bolus unless its set-up or venous access is delayed.",
                listOf(cite(ICMR_DKA, "Treatment"), cite(CONS, "Section 4 — Insulin"), cite(JBDS_DKA, "Controversial areas; 0 to 60 minutes, Action 4")),
            ),
            ProtocolNote(
                "Diagnostic cut-offs differ: bicarbonate <18 mmol/L (ADA/EASD 2024) vs <15 mmol/L (JBDS 2023); glucose ≥200 mg/dL vs >11.0 mmol/L — both accept known diabetes at any glucose.",
                listOf(cite(S16, "Table 16.1"), cite(JBDS_DKA, "Diagnosis of DKA")),
            ),
            ProtocolNote(
                "Potassium thresholds differ: ADA/EASD 2024 delays insulin below 3.5 mmol/L and replaces below 5.0; ICMR STW replaces before insulin below 3.3 and with insulin below 5.5; JBDS adds 40 mmol/L at 3.5–5.5.",
                listOf(cite(CONS, "Section 4 — Potassium"), cite(ICMR_DKA, "Treatment"), cite(JBDS_DKA, "0 to 60 minutes, Action 3")),
            ),
            ProtocolNote(
                "Dextrose differs: ADA/EASD 2024 — 5–10% and insulin cut to 0.05 U/kg/h below 250 mg/dL; JBDS 2023 — 10% at 125 mL/h below 14 mmol/L; ICMR STW — 5% at 50 mL/h below 250 mg/dL.",
                listOf(cite(CONS, "Section 4 — Insulin"), cite(JBDS_DKA, "Intravenous glucose concentration"), cite(ICMR_DKA, "Treatment")),
            ),
            ProtocolNote(
                "Bicarbonate differs: ADA/EASD 2024 considers 100 mmol every 2 h below pH 7.0; ICMR STW gives 50 mmol over 2 h below pH 7.0 (and also states pH <6.9, or <7.1 with hypotension or hyperkalaemia); JBDS 2023 does not recommend it routinely.",
                listOf(cite(CONS, "Section 4 — Bicarbonate"), cite(ICMR_DKA, "Treatment"), cite(JBDS_DKA, "Controversial areas 5")),
            ),
            ProtocolNote(
                "Resolution differs: ICMR STW uses glucose <250 mg/dL and a normal anion gap; ADA/EASD 2024 advises against the anion gap (ketones <0.6 mmol/L with pH ≥7.3 or bicarbonate ≥18); JBDS uses ketones and pH only.",
                listOf(cite(ICMR_DKA, "When to stop insulin infusion?"), cite(CONS, "Section 4 — Criteria for resolution"), cite(JBDS_DKA, "6 to 12 hours, Action 2")),
            ),
            ProtocolNote(
                "Fluid choice is debated: ADA/EASD 2024 cites faster resolution with balanced crystalloids; JBDS 2023 notes two trials showed no outcome superiority and keeps 0.9% saline with premixed potassium on wards.",
                listOf(cite(CONS, "Section 4 — Fluid therapy"), cite(JBDS_DKA, "Controversial areas 3")),
            ),
            ProtocolNote(
                "Overlap before stopping IV insulin differs: basal 1–2 h before (ADA/EASD 2024), 2–4 h before (ADA Standards 2026); IV stopped 30–60 min after a meal-time SC dose (JBDS 2023); SC at least 30 min before (ICMR STW).",
                listOf(cite(CONS, "Transition to maintenance insulin"), cite(S16, "Transition to subcutaneous insulin"), cite(JBDS_DKA, "12 to 24 hours"), cite(ICMR_DKA, "When to stop insulin infusion?")),
            ),
            ProtocolNote(
                "Scope: adults. JBDS 2023 applies to 16–18-year-olds only when managed by adult teams; children follow paediatric guidance, not reviewed here.",
                listOf(cite(JBDS_DKA, "DKA pathway of care")),
            ),
        ),
        citations = listOf(cite(CONS), cite(S16), cite(JBDS_DKA), cite(ICMR_DKA)),
    ),

    // =============================================================================================
    // 2. Hyperosmolar hyperglycaemic state
    // =============================================================================================
    Protocol(
        id = "hhs",
        category = ProtocolCategory.Metabolic,
        title = "Hyperosmolar hyperglycaemic state (HHS) — adult",
        summary = "Criteria and osmolality, fluid-first treatment, safe rates of fall of glucose, sodium and osmolality, " +
            "when insulin starts, potassium, VTE prophylaxis and foot protection — JBDS-IP 2022 and ADA/EASD 2024.",
        keywords = listOf(
            "HHS", "hyperosmolar hyperglycaemic state", "hyperosmolar hyperglycemic state", "HONK", "HONC",
            "hyperosmolar non-ketotic", "hyperosmolar coma", "osmolality", "osmolarity", "very high sugar",
            "severe hyperglycaemia", "dehydration", "type 2 diabetes", "T2DM", "mixed DKA HHS", "elderly diabetic",
            "hypernatraemia", "corrected sodium",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise and confirm HHS",
                timing = "At presentation",
                points = listOf(
                    "ADA/EASD 2024 (all four): glucose ≥600 mg/dL (33.3 mmol/L); effective osmolality >300 mOsm/kg [2×Na + glucose] or total >320 [+ urea]; β-hydroxybutyrate <3.0 mmol/L or urine ketones <2+; pH ≥7.3 and HCO₃⁻ ≥15 mmol/L.",
                    "JBDS 2022: hypovolaemia; osmolality usually ≥320 mOsm/kg; glucose ≥540 mg/dL (30 mmol/L); ketones ≤3.0 mmol/L; pH ≥7.3 and bicarbonate ≥15 mmol/L. Not by biochemistry alone.",
                    "JBDS 2022: osmolality = 2×Na + glucose + urea, all in mmol/L (glucose mg/dL ÷ 18; urea mg/dL ÷ 6; BUN mg/dL ÷ 2.8). Use 2×Na + glucose until urea is back.",
                    "JBDS 2022: develops over days; patients may not look as dry as they are; fluid loss 100–220 mL/kg. Cognitive change is commoner above 330 mOsm/kg.",
                    "ADA/EASD 2024: over one-third overlap with DKA; mixed DKA/HHS had the highest inpatient mortality. Check ketones and venous gas in everyone.",
                    "ADA/EASD 2024: infection (30–60%; UTI, pneumonia) is the main precipitant; also stroke, MI, surgery, pancreatitis, glucocorticoids, sympathomimetics and antipsychotics.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(S16, "Table 16.1"),
                    cite(CONS, "Section 3 — Diagnostic criteria for HHS; Section 1 — Morbidity and mortality, Risk factors"),
                    cite(JBDS_HHS, "Definition and Diagnosis; Initial Assessment, Table 1; Biochemical; Investigations"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Severity, level of care and monitoring",
                timing = "Within the first hour",
                points = listOf(
                    "JBDS 2022: any one marker below should prompt discussion of HDU/level 2 care and immediate senior review.",
                    "ADA/EASD 2024: HHS, severe DKA, critical illness as precipitant or altered mental status — treat in the ICU.",
                    "JBDS 2022: hourly glucose, Na⁺, K⁺, urea and calculated osmolality for 6 h, then 2-hourly if osmolality falls 3.0–8.0 mOsm/kg/h; hourly ketones until resolution.",
                    "ADA/EASD 2024: capillary glucose every 1–2 h; glucose, creatinine, electrolytes and serum osmolality every 4 h.",
                    "JBDS 2022: chart osmolality, glucose and sodium together to see the rate of change; continuous pulse oximetry; consider cardiac monitoring.",
                ),
                checklistTitle = "JBDS 2022 — any one indicates need for HDU/level 2",
                checklist = listOf(
                    "Osmolality >350 mOsm/kg",
                    "Sodium >160 mmol/L",
                    "Venous or arterial pH <7.1",
                    "K⁺ <3.5 or >6.0 mmol/L on admission",
                    "GCS <12 or abnormal AVPU",
                    "SpO₂ <92% on air (normal baseline respiratory function)",
                    "Systolic BP <90 mmHg",
                    "Pulse >100 or <60 bpm",
                    "Urine output <0.5 mL/kg/h",
                    "Creatinine >200 µmol/L (≈2.3 mg/dL) and/or AKI",
                    "Hypothermia",
                    "Macrovascular event (MI, stroke)",
                    "Other serious co-morbidity",
                ),
                scoreIds = listOf("gcs", "news2"),
                citations = listOf(
                    cite(JBDS_HHS, "Markers of severity indicating the need for HDU/level 2 care; Monitoring (0 to 60 minutes)"),
                    cite(CONS, "Section 4 (level of care; monitoring)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Fluids first",
                timing = "T = 0: 1 L 0.9% saline over the first hour (JBDS)",
                points = listOf(
                    "JBDS 2022: 0.9% saline 1 L over 1 h; faster if SBP <90 mmHg. Caution in older people — too fast risks heart failure, too slow fails to reverse AKI.",
                    "JBDS 2022: then 0.5–1 L/h; aim for positive balance of 2–3 L by 6 h and 3–6 L by 12 h, the rest over the next 12 h.",
                    "ADA/EASD 2024: without renal or cardiac compromise, isotonic saline or balanced crystalloid 500–1000 mL/h for 2–4 h; 250 mL boluses in older adults, heart failure or dialysis.",
                    "JBDS 2022: fluid alone lowers glucose; IV insulin before adequate fluid replacement can cause circulatory collapse.",
                    "JBDS 2022 and ADA/EASD 2024: an early rise in sodium as glucose falls is expected and is not itself a reason for hypotonic fluid.",
                    "JBDS 2022 and ADA/EASD 2024: use 0.45% saline only if osmolality is not falling despite adequate positive fluid balance.",
                ),
                citations = listOf(
                    cite(JBDS_HHS, "Management timeline 0 to 60 minutes and 60 minutes to 6 hours; Isotonic versus hypotonic fluid replacement; Insulin dose and timing"),
                    cite(CONS, "Section 4 — Fluid therapy"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Safe rates of fall",
                timing = "Check hourly for the first 6 h",
                points = listOf(
                    "JBDS 2022 and ADA/EASD 2024: osmolality should fall 3.0–8.0 mOsm/kg/h. JBDS: faster than 8 (or >3 if high cerebral-oedema risk) — slow fluids and/or insulin.",
                    "JBDS 2022: glucose fall ≤90 mg/dL/h (5 mmol/L/h). ADA/EASD 2024: ≤90–120 mg/dL/h (5–6.7 mmol/L/h); hyperglycaemia usually resolves in 8–10 h.",
                    "JBDS 2022 and ADA/EASD 2024: sodium should fall by no more than 10 mmol/L in 24 h.",
                    "JBDS 2022: Na⁺ and osmolality both rising (or osmolality falling <3/h) — check balance and increase 0.9% saline; if balance is adequate, consider 0.45% at the same rate.",
                    "JBDS 2022: keep glucose 180–270 mg/dL (10–15 mmol/L) for 24 h; below 252 mg/dL (14 mmol/L) add 5% or 10% glucose 125 mL/h and continue 0.9% saline.",
                    "ADA/EASD 2024: slow correction limits cerebral oedema; osmotic demyelination may complicate HHS when relative hyponatraemia is corrected quickly.",
                ),
                citations = listOf(
                    cite(JBDS_HHS, "Executive summary; Osmolality, sodium and glucose; Clinical scenarios; 60 minutes to 6 hours"),
                    cite(CONS, "Section 4 — Fluid therapy; Table 3 (cerebral oedema, osmotic demyelination)"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Insulin — when and at what rate",
                timing = "JBDS: only once glucose stops falling on fluids, unless ketotic",
                points = listOf(
                    "JBDS 2022: no IV insulin at first unless significant ketonaemia; start FRIII 0.05 units/kg/h once glucose stops falling and positive fluid balance is adequate.",
                    "JBDS 2022: ketones >1.0–≤3.0 mmol/L, not acidotic (pH >7.3, HCO₃⁻ >15) — start 0.05 units/kg/h at once; ketones >3.0 with pH <7.3 and HCO₃⁻ <15 — DKA pathway at 0.1.",
                    "ADA/EASD 2024: no or mild–moderate ketonaemia (1.0–<3.0 mmol/L) without acidosis — FRIII 0.05 U/kg/h; ketones ≥3.0, pH <7.30 or HCO₃⁻ <18 (mixed DKA/HHS) — 0.1 U/kg/h.",
                    "JBDS 2022: glucose falling <90 mg/dL/h with adequate balance — start 0.05 or increase to 0.1 units/kg/h; later adjust by 1 unit/h to keep 180–270 mg/dL.",
                    "ADA/EASD 2024: continue usual basal insulin; SC rapid-acting analogue regimens are not recommended for HHS.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Insulin FRIII — HHS (JBDS 2022; ADA/EASD 2024)",
                        dose = WeightDose(0.05, 0.05, unit = "units", per = "per hour, fixed-rate IV infusion"),
                        note = "JBDS 2022: once glucose has stopped falling on fluids alone, or at once if ketones >1.0–≤3.0 mmol/L without acidosis.",
                    ),
                    StepDose(
                        label = "Insulin FRIII — mixed DKA/HHS",
                        dose = WeightDose(0.1, 0.1, unit = "units", per = "per hour, fixed-rate IV infusion"),
                        note = "ADA/EASD 2024: ketones ≥3.0 mmol/L, pH <7.30 or bicarbonate <18. JBDS 2022: ketones >3.0 with pH <7.3 and bicarbonate <15 — follow the DKA guideline.",
                    ),
                ),
                citations = listOf(
                    cite(JBDS_HHS, "Insulin dose and timing; Clinical scenarios; 12 to 24 hours"),
                    cite(CONS, "Section 4 — Insulin"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Potassium, VTE prophylaxis and infection",
                points = listOf(
                    "JBDS 2022 (first 24 h): K⁺ >5.5 — none; 3.5–5.5 — 40 mmol/L in the infusion; <3.5 — senior review, extra potassium via central line in HDU. K⁺ >6.0 also needs senior review.",
                    "JBDS 2022: everyone with HHS should receive prophylactic LMWH for the whole admission unless contraindicated; therapeutic dosing only if thrombosis or ACS is suspected.",
                    "ADA/EASD 2024: HHS especially is prothrombotic — prophylactic LMWH unless thrombosis is suspected.",
                    "JBDS 2022: low phosphate and magnesium are common; there are no data supporting routine replacement. ADA/EASD 2024: phosphate as for DKA.",
                    "JBDS 2022: sepsis may be afebrile; give antibiotics when there is clinical, laboratory or radiological evidence of infection.",
                ),
                citations = listOf(
                    cite(JBDS_HHS, "Potassium, Table 2; Anticoagulation; Other electrolyte imbalances; Antibiotic therapy"),
                    cite(CONS, "Table 3 (thrombosis); Section 4 — Phosphate"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Feet and treatment complications",
                timing = "On admission and daily",
                points = listOf(
                    "JBDS 2022: high risk of pressure-related foot ulceration — assess the feet on admission and daily.",
                    "JBDS 2022: heel protectors and a suitable mattress if immobile, neuropathy, peripheral vascular disease or deformity; assume high risk if too confused or sleepy to cooperate.",
                    "JBDS 2022: every 1–2 h look for fluid overload, cerebral oedema or osmotic demyelination (falling conscious level).",
                    "JBDS 2022: assess cognition daily against the pre-morbid state.",
                ),
                checklistTitle = "Foot protection (JBDS 2022)",
                checklist = listOf(
                    "Foot risk assessment on admission",
                    "Heels off-loaded",
                    "Daily foot checks",
                    "Pressure-relieving mattress if at risk",
                ),
                citations = listOf(
                    cite(JBDS_HHS, "Foot protection; 0 to 60 minutes (Assessment); Executive summary; Changes in cognitive performance"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Resolution and recovery",
                timing = "Full correction may take up to 72 h (JBDS)",
                points = listOf(
                    "JBDS 2022: resolved when osmolality <300 mOsm/kg, urine output ≥0.5 mL/kg/h, cognition back to pre-morbid state and glucose <270 mg/dL (15 mmol/L).",
                    "ADA/EASD 2024: osmolality <300 mOsm/kg, hyperglycaemia corrected, urine output >0.5 mL/kg/h, cognition improved and glucose <250 mg/dL.",
                    "JBDS 2022: continue IV fluids until eating and drinking; variable-rate insulin if not eating; early mobilisation; continue LMWH until discharge.",
                    "JBDS 2022: most go home on SC insulin; if previously undiagnosed or well controlled on oral agents, consider switching back after a period of stability.",
                    "JBDS 2022 and ADA Standards 2026: diabetes education and follow-up to prevent recurrence.",
                ),
                citations = listOf(
                    cite(JBDS_HHS, "Definition of resolution of HHS; Recovery phase; 24 hours to day 3; After care"),
                    cite(CONS, "Section 4 — Criteria for resolution of DKA and HHS"),
                    cite(S16, "Rec 16.17"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Insulin timing is the main difference: JBDS 2022 gives fluids alone first and starts 0.05 units/kg/h only when glucose stops falling (unless ketotic); ADA/EASD 2024 starts 0.05 U/kg/h when ketones are <3.0 without acidosis, noting some withhold it until glucose plateaus.",
                listOf(cite(JBDS_HHS, "Insulin dose and timing"), cite(CONS, "Section 4 — Insulin; Fluid therapy")),
            ),
            ProtocolNote(
                "Criteria differ: ADA/EASD 2024 — glucose ≥600 mg/dL with effective osmolality >300 (or total >320); JBDS 2022 — glucose ≥540 mg/dL (30 mmol/L) with osmolality ≥320 including urea, plus hypovolaemia.",
                listOf(cite(S16, "Table 16.1"), cite(JBDS_HHS, "Definition and Diagnosis")),
            ),
            ProtocolNote(
                "Mixed DKA/HHS cut-offs differ: ADA/EASD 2024 uses 0.1 U/kg/h if ketones ≥3.0, pH <7.30 or bicarbonate <18; JBDS 2022 if ketones >3.0 with pH <7.3 and bicarbonate <15.",
                listOf(cite(CONS, "Section 4 — Insulin"), cite(JBDS_HHS, "Insulin dose and timing")),
            ),
            ProtocolNote(
                "Expected sodium rise as glucose falls: JBDS 2022 — 2.4 mmol/L per 5.5 mmol/L (≈100 mg/dL) fall in glucose; ADA/EASD 2024 — 1.6 mmol/L per 100 mg/dL.",
                listOf(cite(JBDS_HHS, "Isotonic versus hypotonic fluid replacement"), cite(CONS, "Section 4 — Fluid therapy")),
            ),
            ProtocolNote(
                "Glucose limits differ: fall ≤5 mmol/L/h (JBDS 2022; it also calls 4–6 mmol/L/h safe) vs ≤90–120 mg/dL/h (ADA/EASD 2024); resolution glucose <15 mmol/L (JBDS) vs <250 mg/dL (ADA).",
                listOf(cite(JBDS_HHS, "Isotonic versus hypotonic fluid replacement; Definition of resolution"), cite(CONS, "Section 4 — Fluid therapy; Criteria for resolution")),
            ),
            ProtocolNote(
                "India: no ICMR STW or MoHFW guidance specific to HHS was found; the ICMR DKA STW advises searching for another cause of obtundation if osmolality is <320 mOsm/kg.",
                listOf(cite(ICMR_DKA, "Common errors/pitfalls")),
            ),
        ),
        citations = listOf(cite(JBDS_HHS), cite(CONS), cite(S16)),
    ),

    // =============================================================================================
    // 3. Hypoglycaemia
    // =============================================================================================
    Protocol(
        id = "hypoglycaemia",
        category = ProtocolCategory.Metabolic,
        title = "Hypoglycaemia — adult",
        summary = "Levels 1–3, oral carbohydrate, IV glucose and glucagon, recheck intervals, sulfonylurea-induced prolonged " +
            "hypoglycaemia and admission — ADA Standards 2026, JBDS-IP 2023, ICMR STW and FDA labels.",
        keywords = listOf(
            "hypoglycaemia", "hypoglycemia", "hypo", "low sugar", "low blood sugar", "low glucose", "neuroglycopenia",
            "insulin overdose", "sulfonylurea", "sulphonylurea", "glimepiride", "gliclazide", "glibenclamide", "glipizide",
            "glucagon", "nasal glucagon", "dextrose", "25% dextrose", "D25", "D10", "D20", "glucose gel", "octreotide",
            "unconscious diabetic", "seizure diabetic",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise and classify",
                timing = "Check capillary glucose immediately",
                points = listOf(
                    "ADA Standards 2026 Table 6.4: level 1 <70 and ≥54 mg/dL (3.9/3.0 mmol/L); level 2 <54 mg/dL; level 3 — severe event with altered mental and/or physical status needing assistance.",
                    "JBDS 2023: in hospital, treat any glucose <72 mg/dL (4.0 mmol/L) — '4 is the floor'; symptomatic above this — a carbohydrate snack.",
                    "ICMR STW: sweating, hunger, tremor, irritability, weakness, drowsiness; seizures or unconsciousness late. Severe — coma, seizures or unable to self-treat.",
                    "JBDS 2023: commonest with insulin and sulfonylureas; metformin, pioglitazone, DPP-4 inhibitors, SGLT2 inhibitors or GLP-1 analogues alone are unlikely to cause it.",
                    "JBDS 2023: if an IV insulin infusion is running, stop it immediately; restart after treatment with a dose review.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(S6, "Table 6.4"),
                    cite(JBDS_HYPO, "Treatment of hypoglycaemia algorithms; Definition; Hypoglycaemia in adults with diabetes"),
                    cite(ICMR_T1, "Hypoglycaemia"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Conscious and able to swallow",
                timing = "Recheck after 10–15 min (JBDS) / 15 min (ADA)",
                points = listOf(
                    "ADA Standards 2026 Rec 6.15: glucose is preferred; most need 15 g carbohydrate; avoid high-fat or high-protein food first; repeat if still low at 15 min.",
                    "JBDS 2023: 15–20 g rapid-acting carbohydrate — e.g. 150–200 mL pure fruit juice (not on a low-potassium diet) or 3–4 heaped teaspoons of sugar in water (not with acarbose).",
                    "ICMR STW: glucose <70 mg/dL — 3 teaspoons of glucose powder or sugar.",
                    "JBDS 2023: repeat up to 3 times; still <72 mg/dL after 30–45 min or 3 cycles — call for help; IV glucose or glucagon 1 mg IM.",
                    "JBDS 2023: able to swallow but confused or uncooperative — 2 tubes of 40% glucose gel between teeth and gums, or glucagon 1 mg IM if ineffective.",
                    "JBDS 2023: once >72 mg/dL and recovered — 20 g long-acting carbohydrate (e.g. 2 biscuits, 1 slice of bread, 200–300 mL milk); 40 g after glucagon.",
                ),
                citations = listOf(
                    cite(S6, "Rec 6.15; Hypoglycemia treatment"),
                    cite(JBDS_HYPO, "Algorithm A, steps 3–7; Algorithm B, steps 4–8"),
                    cite(ICMR_T1, "Hypoglycaemia — treatment"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Unconscious, fitting or unable to swallow",
                timing = "Immediately; recheck at 10 min (JBDS)",
                points = listOf(
                    "JBDS 2023: ABCDE and call for help; IV glucose 100 mL of 20% over 15 min (400 mL/h) or 200 mL of 10% over 15 min (800 mL/h). Smallest volume in renal or cardiac failure.",
                    "JBDS 2023: 10% or 20% glucose is preferred — 50% has caused extravasation injury and more post-treatment hyperglycaemia.",
                    "ICMR STW: severe — caregiver glucagon 1 mg SC/IM, or take to hospital for IV 25% dextrose 1–2 mL/kg.",
                    "JBDS 2023: no IV access — glucagon 1 mg IM (up to 15 min to act); keep trying for IV access, as a second glucagon dose is less likely to work.",
                    "JBDS 2023: recheck at 10 min and repeat IV glucose if <72 mg/dL; then long-acting carbohydrate, or 10% glucose 100 mL/h if nil by mouth.",
                    "JBDS 2023: on IV insulin — check every 15 min until >72 mg/dL, restart after dose review, consider 10% glucose 100 mL/h alongside.",
                ),
                doses = listOf(
                    StepDose(
                        label = "25% dextrose IV (ICMR STW)",
                        dose = WeightDose(1.0, 2.0, unit = "mL", per = "of 25% dextrose IV"),
                        note = "ICMR STW type 1 diabetes workflow (does not separate adults and children). " +
                            "JBDS 2023 instead uses fixed adult volumes: 100 mL of 20% or 200 mL of 10% glucose over 15 min.",
                    ),
                ),
                citations = listOf(
                    cite(JBDS_HYPO, "Algorithm C, steps 1–12; Algorithm D; Introduction (choice of IV glucose)"),
                    cite(ICMR_T1, "Hypoglycaemia — treatment"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Glucagon",
                points = listOf(
                    "FDA label (glucagon injection): adults 1 mg SC, IM or IV; if no response after 15 min, a further 1 mg while awaiting emergency help.",
                    "FDA label (nasal glucagon): 3 mg into one nostril; a second 3 mg dose after 15 min if no response. Give oral carbohydrate once the patient responds.",
                    "ADA Standards 2026 Rec 6.16: prescribe glucagon for everyone taking insulin or at high risk of hypoglycaemia; ready-to-use preparations preferred.",
                    "FDA label: works only with adequate liver glycogen — starvation, adrenal insufficiency or chronic hypoglycaemia need glucose. JBDS 2023: less effective with sulfonylureas, alcohol excess or chronic liver disease.",
                    "FDA label: contraindicated in phaeochromocytoma; in insulinoma it may provoke hypoglycaemia.",
                ),
                citations = listOf(
                    cite(FDA_GLUCAGON, "§2 Dosage and administration; §4 Contraindications; §5.1, 5.2, 5.4"),
                    cite(FDA_NASAL, "§2 Dosage and administration"),
                    cite(S6, "Rec 6.16; Glucagon"),
                    cite(JBDS_HYPO, "Algorithm A step 5; Algorithm C step 6; Glucagon"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Sulfonylurea-induced and prolonged hypoglycaemia",
                timing = "Monitor for at least 24–48 h (JBDS)",
                points = listOf(
                    "JBDS 2023: after sulfonylurea or long-acting insulin, hypoglycaemia risk may persist 24–36 h after the last dose, especially with renal impairment.",
                    "JBDS 2023: continue regular capillary glucose monitoring for at least 24–48 h after an episode.",
                    "JBDS 2023 (Front Door): sulfonylurea-treated type 2 — especially frail, older, living alone, injured or with worsening co-morbidity — admit; recurrence risk over 48 h.",
                    "Fasano 2008 RCT (40 ED patients): one dose of octreotide 75 µg SC plus standard care raised glucose for 8 h and reduced recurrent hypoglycaemia vs placebo.",
                    "Dougherty 2010 review (abstract): evidence supports octreotide with supplemental dextrose for sulfonylurea-induced hypoglycaemia. No guideline reviewed here gives an octreotide dose.",
                    "ADA Standards 2026: some antimicrobials markedly increase sulfonylurea effect; consider reducing or stopping the sulfonylurea while they are given.",
                ),
                citations = listOf(
                    cite(JBDS_HYPO, "Algorithm A steps 10–11; Algorithm C step 13"),
                    cite(JBDS_FD, "1. Hypoglycaemia — Recommendations"),
                    cite(OCT_RCT, "Abstract"),
                    cite(OCT_REVIEW, "Abstract"),
                    cite(S6, "Intercurrent illness"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Admission, discharge and prevention",
                timing = "Before leaving the ED",
                points = listOf(
                    "JBDS 2023 (Front Door): not everyone needs admission; usually well, self-managing insulin users can usually go home after dose advice, education and a leaflet.",
                    "JBDS 2023 (Front Door): find the cause; check HbA1c if none within 3 months; recurrent episodes — diabetes team review.",
                    "JBDS 2023: do not omit the next insulin dose, but review the regimen.",
                    "ADA Standards 2026 Rec 6.19: one or more level 2 or 3 episodes should prompt re-evaluation of the treatment plan.",
                    "ICMR STW: prevention — identify mismatch between food, exercise and insulin.",
                ),
                checklistTitle = "Admit or refer (JBDS 2023, Front Door)",
                checklist = listOf(
                    "Sulfonylurea-treated, especially frail, older or living alone — admit",
                    "Injury from the episode (e.g. fracture) or worsening co-morbidity — admit",
                    "Pregnant, insulin pump or IV insulin — always refer to diabetes team (example pathway)",
                    "Hypoglycaemia unawareness or prolonged hypoglycaemia — consider referral",
                    "Care-home resident or repeated ED attendances — consider referral",
                ),
                citations = listOf(
                    cite(JBDS_FD, "1. Hypoglycaemia — Recommendations; Appendix 1"),
                    cite(JBDS_HYPO, "Algorithm A step 9"),
                    cite(S6, "Rec 6.19"),
                    cite(ICMR_T1, "Hypoglycaemia — prevention"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Treatment threshold: ADA Standards 2026 and ICMR STW use <70 mg/dL; JBDS 2023 treats any glucose <72 mg/dL (4.0 mmol/L) in hospital.",
                listOf(cite(S6, "Table 6.4; Rec 6.15"), cite(ICMR_T1, "Hypoglycaemia"), cite(JBDS_HYPO, "Definition")),
            ),
            ProtocolNote(
                "Oral carbohydrate: 15 g (ADA Standards 2026), 15–20 g (JBDS 2023), 3 teaspoons of glucose powder or sugar (ICMR STW).",
                listOf(cite(S6, "Hypoglycemia treatment"), cite(JBDS_HYPO, "Algorithm A"), cite(ICMR_T1, "Hypoglycaemia")),
            ),
            ProtocolNote(
                "IV glucose: ICMR STW gives 1–2 mL/kg of 25% dextrose; JBDS 2023 gives fixed 100 mL of 20% or 200 mL of 10% and prefers these to 50% glucose.",
                listOf(cite(ICMR_T1, "Hypoglycaemia"), cite(JBDS_HYPO, "Algorithm C; Introduction")),
            ),
            ProtocolNote(
                "Octreotide rests on one 40-patient RCT (single 75 µg SC dose) and a toxicology review read only as an abstract; none of ADA, JBDS or ICMR STW gives an octreotide dose.",
                listOf(cite(OCT_RCT, "Abstract"), cite(OCT_REVIEW, "Abstract")),
            ),
            ProtocolNote(
                "Glucagon products cited are US-labelled; JBDS 2023 noted nasal glucagon was not then available in the UK. Availability in India was not verified.",
                listOf(cite(FDA_NASAL), cite(JBDS_HYPO, "Glucagon")),
            ),
        ),
        citations = listOf(cite(S6), cite(JBDS_HYPO), cite(JBDS_FD), cite(ICMR_T1), cite(FDA_GLUCAGON), cite(FDA_NASAL)),
    ),

    // =============================================================================================
    // 4. Adrenal crisis
    // =============================================================================================
    Protocol(
        id = "adrenal-crisis",
        category = ProtocolCategory.Metabolic,
        title = "Adrenal crisis — adult",
        summary = "Recognition, immediate hydrocortisone without waiting for tests, fluids, triggers and stress dosing in " +
            "steroid-dependent patients — Endocrine Society 2016, Society for Endocrinology 2016, ESE/Endocrine Society 2024.",
        keywords = listOf(
            "adrenal crisis", "Addisonian crisis", "acute adrenal insufficiency", "adrenal insufficiency", "Addison's disease",
            "hydrocortisone", "steroid dependent", "steroid withdrawal", "glucocorticoid", "prednisolone", "hypopituitarism",
            "sick day rules", "steroid emergency card", "stress dose steroids", "hypotension hyponatraemia hyperkalaemia",
            "refractory shock",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise — and who is at risk",
                timing = "At first contact",
                points = listOf(
                    "SfE 2016: fatigue, postural hypotension (≥20 mmHg drop), collapse or shock; abdominal pain, nausea, vomiting; fever; confusion, delirium or coma; back and leg cramps.",
                    "SfE 2016: primary — skin hyperpigmentation (palmar creases, scars, buccal mucosa); secondary — pale skin and other pituitary deficits.",
                    "SfE 2016 labs: hyponatraemia; hyperkalaemia (primary); pre-renal AKI; normochromic anaemia, lymphocytosis, eosinophilia; hypoglycaemia mainly in children.",
                    "SfE 2016: consider it in any acutely ill patient with suggestive features and take a glucocorticoid drug history.",
                    "ESE 2024 R3.2: current or recent glucocorticoid users with haemodynamic instability, vomiting or diarrhoea — consider adrenal crisis whatever the steroid type, route or dose.",
                ),
                checklistTitle = "At risk (as attributed)",
                checklist = listOf(
                    "Known primary or secondary adrenal insufficiency, adrenalectomy or congenital adrenal hyperplasia (SfE 2016)",
                    "Prednisolone ≥5 mg/day equivalent for >4 weeks (SfE 2016)",
                    "Above hydrocortisone 15–25 mg/day equivalent (prednisolone 4–6 mg) for ≥3–4 weeks (ESE 2024)",
                    "High-dose or >1 year inhaled/topical steroid, or multiple steroid formulations (ESE 2024 R2.9)",
                    "Intra-articular steroid injection within 2 months (ESE 2024 R2.9)",
                    "Strong CYP3A4 inhibitor taken with a glucocorticoid (ESE 2024 R2.9)",
                ),
                citations = listOf(
                    cite(SFE, "Underlying conditions; Clinical presentation; Lab findings; Investigations"),
                    cite(ESE, "R 2.9; R 3.2; Section 3.4; Table 1"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Hydrocortisone now — do not wait for tests",
                timing = "Immediately on suspicion",
                points = listOf(
                    "Endocrine Society 2016 Rec 1.3: severe symptoms or crisis — IV hydrocortisone immediately, before diagnostic results; do not wait for a cosyntropin test.",
                    "Endocrine Society 2016 Rec 4.1 and SfE 2016: hydrocortisone 100 mg IV or IM bolus at once, followed by fluid resuscitation.",
                    "SfE 2016 and Endocrine Society 2016: then hydrocortisone 200 mg/24 h by continuous IV infusion, or 50 mg IV/IM every 6 h. ESE 2024: same for steroid-treated patients.",
                    "SfE 2016: if feasible, draw paired cortisol and ACTH (with U&E, FBC, TSH, fT4) before the dose — but never delay treatment.",
                    "SfE 2016: short Synacthen test only if haemodynamically stable; otherwise confirm the diagnosis after recovery.",
                    "Endocrine Society 2016 Rec 4.2: if hydrocortisone is unavailable, prednisolone; dexamethasone is the least-preferred option, only if nothing else is available.",
                ),
                citations = listOf(
                    cite(ES_PAI, "Rec 1.3; Rec 4.1; Rec 4.2"),
                    cite(SFE, "Management of adrenal crisis; Investigations"),
                    cite(ESE, "Adrenal crisis management; Table 8"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Fluids and supportive care",
                timing = "1 L in the first hour (SfE)",
                points = listOf(
                    "SfE 2016: isotonic saline 1000 mL rapidly within the first hour, then further IV fluid as needed — usually 4–6 L in 24 h.",
                    "SfE 2016: watch for fluid overload in renal impairment and older patients.",
                    "SfE 2016: urgent endocrinology review — taper plan and cause (primary vs secondary).",
                    "SfE 2016: check TSH and fT4 — hyperthyroidism can trigger crisis; TSH may rise in crisis, so do not start thyroxine if TSH ≤10 mU/L.",
                ),
                citations = listOf(
                    cite(SFE, "Management of adrenal crisis; Investigations"),
                    cite(ES_PAI, "Rec 4.1"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Find the trigger",
                points = listOf(
                    "SfE 2016: commonest — sudden stopping of long-term glucocorticoid; not doubling the oral dose during febrile illness (sick-day rule 1).",
                    "SfE 2016: not giving parenteral glucocorticoid for prolonged vomiting or diarrhoea, colonoscopy preparation, trauma or surgery under general anaesthesia (sick-day rule 2).",
                    "SfE 2016: in more than half of patients, adrenal insufficiency is diagnosed only after presenting in crisis.",
                    "SfE 2016: established adrenal insufficiency and supraphysiological glucocorticoid treatment carry a permanent risk of crisis.",
                ),
                citations = listOf(cite(SFE, "Precipitating factors")),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Steroid-dependent patients — stress dosing",
                points = listOf(
                    "ESE 2024 R3.1: current or recent glucocorticoid users not tested for adrenal insufficiency should receive stress-dose cover when exposed to stress.",
                    "ESE 2024 R3.1A–B: oral for minor stress without instability; parenteral for moderate–major stress, general or regional anaesthesia, no oral intake, instability or prolonged vomiting/diarrhoea.",
                    "ESE 2024 Table 8: moderate–major stress — hydrocortisone 100 mg IV/IM, then 200 mg/24 h infusion or 50 mg IV every 6 h.",
                    "SfE 2016: sick-day rule 1 — double the daily oral dose for illness with fever needing bed rest or antibiotics. Endocrine Society 2016: double above 38 °C, triple above 39 °C, usually 2–3 days.",
                ),
                citations = listOf(
                    cite(ESE, "R 3.1, R 3.1A, R 3.1B; Table 8"),
                    cite(SFE, "After emergency care"),
                    cite(ES_PAI, "Rec 4.3; Table 3"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Recovery, taper and prevention",
                timing = "After clinical recovery",
                points = listOf(
                    "SfE 2016: taper hydrocortisone after recovery with endocrinology guidance; primary AI — start fludrocortisone 100 µg daily once hydrocortisone is below 50 mg/24 h.",
                    "Endocrine Society 2016 Rec 4.4–4.6: educate on stress dosing; steroid emergency card, medical alert ID and a glucocorticoid injection kit for every patient.",
                    "SfE 2016: hydrocortisone 100 mg emergency injection kit with training for patient and family; extra tablets to double the dose for at least 7 days.",
                    "ESE 2024: provide patient-held prompts for healthcare staff, such as the ESE steroid emergency card.",
                ),
                citations = listOf(
                    cite(SFE, "Management of adrenal crisis; After emergency care"),
                    cite(ES_PAI, "Rec 4.4, 4.5, 4.6"),
                    cite(ESE, "Patient education"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "At-risk steroid exposure differs: SfE 2016 — ≥5 mg prednisolone equivalent for >4 weeks; ESE/Endocrine Society 2024 — above 15–25 mg hydrocortisone equivalent (4–6 mg prednisolone) for about 3–4 weeks or longer.",
                listOf(cite(SFE, "Underlying conditions"), cite(ESE, "Section 3.4")),
            ),
            ProtocolNote(
                "Sick-day rule wording differs: SfE 2016 doubles the oral dose for febrile illness needing bed rest or antibiotics; Endocrine Society 2016 Table 3 doubles above 38 °C and triples above 39 °C.",
                listOf(cite(SFE, "After emergency care"), cite(ES_PAI, "Table 3")),
            ),
            ProtocolNote(
                "India: no ICMR STW or MoHFW guideline on adrenal crisis was found (checked November 2026). The ICMR hypothyroidism STW warns that thyroxine before hydrocortisone can precipitate adrenal crisis in central hypothyroidism.",
                listOf(cite(ICMR_HYPOTHY, "Initiating therapy — central (secondary) hypothyroidism")),
            ),
        ),
        citations = listOf(cite(ES_PAI), cite(SFE), cite(ESE)),
    ),

    // =============================================================================================
    // 5. Thyroid storm
    // =============================================================================================
    Protocol(
        id = "thyroid-storm",
        category = ProtocolCategory.Metabolic,
        title = "Thyroid storm — adult",
        summary = "Burch–Wartofsky and JTA criteria, beta-blockade, thionamide, iodine timing, glucocorticoid, cooling and " +
            "supportive care, what to avoid — ATA 2016 and JTA/JES 2016.",
        keywords = listOf(
            "thyroid storm", "thyrotoxic crisis", "thyrotoxicosis", "hyperthyroidism", "Graves", "Graves disease",
            "Burch-Wartofsky", "BWPS", "JTA criteria", "PTU", "propylthiouracil", "methimazole", "thiamazole", "carbimazole",
            "Lugol's iodine", "SSKI", "potassium iodide", "propranolol", "esmolol", "landiolol", "plasmapheresis",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise and score",
                timing = "At presentation",
                points = listOf(
                    "ATA 2016 Rec 34: a clinical diagnosis in severe thyrotoxicosis with systemic decompensation; use BWPS or JTA criteria as adjuncts.",
                    "ATA 2016: BWPS ≥45 consistent with storm, 25–44 impending, <25 unlikely. BWPS ≥45 or JTA TS1/TS2 with decompensation — aggressive therapy; 25–44 — clinical judgement and close watch.",
                    "JTA 2016 prerequisite: raised FT3 or FT4. Features: CNS (restlessness to coma, GCS ≤14), fever ≥38 °C, HR ≥130, heart failure, GI/hepatic (nausea, vomiting, diarrhoea, bilirubin ≥3 mg/dL).",
                    "JTA 2016: TS1 (definite) — CNS plus ≥1 other feature, or ≥3 non-CNS features; TS2 (suspected) — 2 non-CNS features, or a TS1 picture without FT3/FT4 results.",
                    "ATA 2016: both systems overdiagnose — every feature except severe hyperpyrexia can occur in other major illness. Mortality 8–25% in modern series.",
                ),
                checklistTitle = "BWPS points (ATA 2016 Table 6)",
                checklist = listOf(
                    "Temperature °C: 37.2–37.7 (5), 37.8–38.3 (10), 38.3–38.8 (15), 38.9–39.4 (20), 39.4–39.9 (25), ≥40 (30)",
                    "Heart rate: 100–109 (5), 110–119 (10), 120–129 (15), 130–139 (20), ≥140 (25)",
                    "Atrial fibrillation present (10)",
                    "Heart failure: mild (5), moderate (10), severe (20)",
                    "CNS: agitation (10); delirium, psychosis, extreme lethargy (20); seizure or coma (30)",
                    "GI–hepatic: diarrhoea, abdominal pain, nausea/vomiting (10); jaundice (20)",
                    "Precipitant history: 10 points for one category — see note on the printed table",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(ATA16, "Section [H], Rec 34; Table 6"),
                    cite(JTA, "Table 3 (JTA diagnostic criteria)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Beta-blockade",
                timing = "Start early",
                points = listOf(
                    "ATA 2016 Table 7: propranolol 60–80 mg every 4 h (blocks T4-to-T3 conversion at high doses); consider invasive monitoring in heart failure; alternative esmolol infusion.",
                    "JTA 2016 Rec 8: beta1-selective agents first — landiolol or esmolol IV, or bisoprolol 2.5–5 mg/day orally; propranolol is not contraindicated but not recommended.",
                    "JTA 2016: HR ≥150 with Killip ≤III — landiolol or esmolol; Killip IV — may be considered. Target HR ≤130.",
                    "JTA 2016: stop if HR <80, SBP <80 mmHg or cardiac index ≤2.2; caution in asthma/COPD — switch to verapamil or diltiazem if bronchospasm.",
                    "JTA 2016: atrial fibrillation — digitalis if no severe renal dysfunction; cardioversion if haemodynamics deteriorate and left atrial thrombus is excluded; anticoagulate by CHADS2.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Esmolol — loading (JTA 2016)",
                        dose = WeightDose(1.0, 1.0, unit = "mg", per = "IV over 30 s"),
                        note = "JTA 2016: then titrate the infusion to heart rate (about 150 µg/kg/min); target HR ≤130. Use carefully in asthma or COPD.",
                    ),
                    StepDose(
                        label = "Landiolol infusion (JTA 2016)",
                        dose = WeightDose(1.0, 10.0, unit = "mcg", per = "per minute, IV infusion"),
                        note = "JTA 2016: start at 1 µg/kg/min and titrate within 1–10 µg/kg/min to heart rate. Availability in India not verified.",
                    ),
                ),
                citations = listOf(
                    cite(ATA16, "Table 7; Section [H]"),
                    cite(JTA, "Recommendation 8"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Thionamide",
                timing = "As soon as the diagnosis is made",
                points = listOf(
                    "ATA 2016 Table 7: propylthiouracil 500–1000 mg load, then 250 mg every 4 h; or methimazole 60–80 mg/day.",
                    "ATA 2016: PTU is preferred in storm because it also blocks T4-to-T3 conversion — T3 fell about 45% in 24 h on PTU vs 10–15% on methimazole.",
                    "JTA 2016 Rec 2: methimazole or PTU; Japanese surveys found no difference in severity or mortality. JTA doses: oral methimazole 60 mg/day or PTU 600 mg/day.",
                    "JTA 2016: impaired consciousness or GI dysfunction — IV methimazole 30 mg/day where available; rectal antithyroid drugs have been reported.",
                    "JTA 2016: storm from destructive thyroiditis (subacute, drug-induced) — antithyroid drugs are contraindicated (ineffective).",
                    "JTA 2016: monitor for rash, agranulocytosis and liver dysfunction; cholestyramine 4 g 3–4 times daily is an adjunct.",
                ),
                citations = listOf(
                    cite(ATA16, "Table 7; Section [H] (PTU vs MMI)"),
                    cite(JTA, "Recommendation 2 and comments"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Inorganic iodine — timing matters",
                timing = "ATA: ≥1 h after the thionamide; JTA: with it",
                points = listOf(
                    "ATA 2016 Table 7: SSKI 5 drops (0.25 mL or 250 mg) orally every 6 h; do not start until 1 h after the antithyroid drug; alternative Lugol's solution.",
                    "JTA 2016 Rec 3: give inorganic iodide at the same time as the antithyroid drug in storm due to hyperthyroidism; about 200 mg/day potassium iodide.",
                    "JTA 2016: avoid potassium iodide in known iodine allergy; confirm the iodide concentration of hospital-prepared SSKI or Lugol's solution.",
                    "JTA 2016: lithium may be used if allergic to antithyroid drugs or iodide, with level monitoring.",
                    "JTA 2016: as thyrotoxicosis improves, reduce iodide before tapering the antithyroid drug.",
                ),
                citations = listOf(
                    cite(ATA16, "Table 7"),
                    cite(JTA, "Recommendation 3 and comments"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Glucocorticoid",
                timing = "Start early",
                points = listOf(
                    "ATA 2016 Table 7: hydrocortisone 300 mg IV load, then 100 mg every 8 h; alternative dexamethasone.",
                    "JTA 2016 Rec 4: hydrocortisone 300 mg/day (100 mg IV every 8 h) or dexamethasone 8 mg/day, whatever the cause of storm.",
                    "ATA 2016 and JTA 2016: covers relative adrenal insufficiency and may reduce T4-to-T3 conversion.",
                    "JTA 2016: taper and stop after recovery once fasting cortisol confirms adrenal recovery; watch glucose, peptic ulcer and infection.",
                ),
                citations = listOf(
                    cite(ATA16, "Table 7; Section [H]"),
                    cite(JTA, "Recommendation 4 and comments"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Cooling, supportive care and triggers",
                timing = "ICU-level monitoring as appropriate",
                points = listOf(
                    "ATA 2016 Rec 35: multimodal care — beta-blocker, antithyroid drug, iodide, corticosteroid, cooling with paracetamol and cooling blankets, volume resuscitation, nutrition, respiratory care, ICU monitoring.",
                    "JTA 2016 Rec 5: paracetamol (e.g. 500 mg three times daily orally or as suppository) with ice packs or cooling blankets; search for and treat infection.",
                    "ATA 2016: triggers — abrupt stopping of antithyroid drugs, thyroid or other surgery with untreated thyrotoxicosis, acute illness, occasionally radioiodine. JTA 2016: infection is the second commonest.",
                    "JTA 2016: symptoms usually improve within 12–24 h; consider plasmapheresis if no improvement within 24–48 h. ATA 2016: plasma exchange or emergency surgery for poor responders.",
                    "ATA 2016: prevent recurrence — avoid abrupt antithyroid drug withdrawal; be euthyroid before elective surgery, labour or other stressors; plan definitive therapy.",
                ),
                checklistTitle = "Avoid (as attributed)",
                checklist = listOf(
                    "Aspirin and NSAIDs for fever — may raise free thyroid hormone (JTA 2016)",
                    "Iodine before the thionamide (ATA 2016: wait at least 1 h)",
                    "Antithyroid drugs in destructive thyroiditis (JTA 2016)",
                    "Propranolol as first choice, especially in Killip III–IV heart failure — JTA prefers beta1-selective agents",
                    "Potassium iodide in known iodine allergy (JTA 2016)",
                ),
                citations = listOf(
                    cite(ATA16, "Rec 35; Section [H]"),
                    cite(JTA, "Recommendations 5, 6 and 8 with comments"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Thionamide choice: ATA 2016 prefers PTU (it also blocks T4-to-T3 conversion); JTA 2016 accepts methimazole or PTU, citing no difference in severity or mortality in Japanese surveys.",
                listOf(cite(ATA16, "Section [H]"), cite(JTA, "Recommendation 2")),
            ),
            ProtocolNote(
                "Iodine timing: ATA 2016 — not until 1 h after the antithyroid drug; JTA 2016 — at the same time, based on two Japanese prospective trials in Graves' disease.",
                listOf(cite(ATA16, "Table 7"), cite(JTA, "Recommendation 3")),
            ),
            ProtocolNote(
                "Beta-blocker: ATA 2016 lists propranolol 60–80 mg every 4 h; JTA 2016 prefers beta1-selective agents — in its survey, Killip ≥III deaths on beta-blockers were all on non-selective agents.",
                listOf(cite(ATA16, "Table 7"), cite(JTA, "Recommendation 8")),
            ),
            ProtocolNote(
                "Glucocorticoid: ATA 2016 — 300 mg IV load then 100 mg every 8 h; JTA 2016 — 300 mg/day (100 mg every 8 h) or dexamethasone 8 mg/day.",
                listOf(cite(ATA16, "Table 7"), cite(JTA, "Recommendation 4")),
            ),
            ProtocolNote(
                "ATA 2016 Table 6 prints the precipitant row as 'positive 0 / negative 10', while its text lists an identified precipitant as a criterion. The original Burch–Wartofsky paper was not reviewed — check before scoring.",
                listOf(cite(ATA16, "Table 6; Section [H]")),
            ),
            ProtocolNote(
                "Diagnostic systems: BWPS ≥45 identified more clinically diagnosed storms than JTA TS1/TS2; both overdiagnosed in compensated thyrotoxicosis (ATA 2016).",
                listOf(cite(ATA16, "Section [H]")),
            ),
            ProtocolNote(
                "India: no ICMR STW or MoHFW guideline on thyroid storm was found. Availability of SSKI/Lugol's iodine, IV methimazole and landiolol in India was not verified; carbimazole doses were not reviewed.",
                listOf(cite(ATA16), cite(JTA)),
            ),
        ),
        citations = listOf(cite(ATA16), cite(JTA)),
    ),

    // =============================================================================================
    // 6. Myxoedema coma
    // =============================================================================================
    Protocol(
        id = "myxoedema-coma",
        category = ProtocolCategory.Metabolic,
        title = "Myxoedema coma — adult",
        summary = "Recognition, glucocorticoid before thyroid hormone, IV levothyroxine loading and maintenance, the place " +
            "of liothyronine, and supportive care — ATA 2014, with ICMR STW context.",
        keywords = listOf(
            "myxoedema coma", "myxedema coma", "myxoedema", "severe hypothyroidism", "hypothyroid coma",
            "decompensated hypothyroidism", "hypothermia hypothyroid", "levothyroxine", "IV levothyroxine", "thyroxine",
            "T4", "liothyronine", "T3", "hypothyroidism", "hyponatraemia hypothermia",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise",
                timing = "At presentation",
                points = listOf(
                    "Popoveniuc 2014 score: thermoregulatory, CNS, cardiovascular, GI and metabolic changes plus a precipitating event; ≥60 potentially diagnostic, 45–59 at risk.",
                    "ATA 2014: the problems to treat are hypothermia, hypoventilation, volume depletion, hypoglycaemia and hyponatraemia.",
                    "ICMR STW (hypothyroidism): dry coarse skin, cool extremities, puffy face, hands and feet, bradycardia, delayed tendon-reflex relaxation, serous effusions.",
                    "ICMR STW: central hypothyroidism — TSH normal or low with low FT4 or T4, so a normal TSH does not exclude it.",
                    "ATA 2014: measure serum cortisol, ideally before treatment starts.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(MC_SCORE, "Abstract"),
                    cite(ATA14, "Myxedema coma (Questions 21a–21d)"),
                    cite(ICMR_HYPOTHY, "When to suspect hypothyroidism — signs; Interpretation (central)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Glucocorticoid first",
                timing = "Before levothyroxine",
                points = listOf(
                    "ATA 2014 Rec 21b (strong, low-quality evidence): empiric IV glucocorticoid at stress doses as part of initial therapy, before levothyroxine.",
                    "ICMR STW: in central hypothyroidism, start levothyroxine only after treating coexisting adrenal insufficiency with hydrocortisone — risk of precipitating adrenal crisis.",
                    "ATA 2014 gives no dose. The Endocrine Society 2016 adrenal-crisis dose is hydrocortisone 100 mg IV, then 200 mg/24 h.",
                ),
                citations = listOf(
                    cite(ATA14, "Recommendation 21b"),
                    cite(ICMR_HYPOTHY, "Initiating therapy — central (secondary) hypothyroidism"),
                    cite(ES_PAI, "Rec 4.1"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "IV levothyroxine",
                timing = "After the glucocorticoid",
                points = listOf(
                    "ATA 2014 Rec 21a (strong, low-quality evidence): initial thyroid hormone replacement should be levothyroxine given IV.",
                    "ATA 2014: loading dose 200–400 µg IV; lower for smaller or older patients and those with coronary disease or arrhythmia.",
                    "ATA 2014: then 1.6 µg/kg/day reduced to 75% while given IV; oral or enteral therapy once the patient improves clinically.",
                    "ATA 2014 Rec 21d: endpoints — improved mental status, cardiac and pulmonary function; measure thyroid hormones every 1–2 days.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Levothyroxine IV — daily maintenance (ATA 2014)",
                        dose = WeightDose(1.2, 1.2, unit = "mcg", per = "IV once daily (1.6 mcg/kg × 75%)"),
                        note = "ATA 2014: after a 200–400 µg IV loading dose (lower for smaller or older patients, coronary disease or arrhythmia). " +
                            "Availability of IV levothyroxine in India not verified.",
                    ),
                ),
                citations = listOf(cite(ATA14, "Recommendations 21a, 21d")),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Liothyronine (T3) — optional",
                points = listOf(
                    "ATA 2014 Rec 21c (weak, low-quality evidence): IV liothyronine may be added, as T4-to-T3 conversion may be reduced.",
                    "ATA 2014: loading 5–20 µg, then 2.5–10 µg every 8 h; lower doses for smaller or older patients and those with coronary disease or arrhythmia.",
                    "ATA 2014: avoid high doses — high serum T3 during treatment has been associated with mortality.",
                ),
                citations = listOf(cite(ATA14, "Recommendation 21c")),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Rewarming and supportive care",
                timing = "ICU-level care as needed",
                points = listOf(
                    "ATA 2014: supportive measures directed at hypothermia, hypoventilation and hyponatraemia are widely accepted as important.",
                    "ATA 2014: also correct volume depletion and hypoglycaemia.",
                    "ATA 2014: give empiric broad-spectrum antibiotics if sepsis is in the differential diagnosis.",
                ),
                citations = listOf(cite(ATA14, "Myxedema coma — discussion")),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Liothyronine is a weak ATA 2014 recommendation on low-quality evidence; high serum T3 during treatment has been associated with mortality.",
                listOf(cite(ATA14, "Recommendation 21c")),
            ),
            ProtocolNote(
                "ATA 2014 names no glucocorticoid or dose for myxoedema coma; the hydrocortisone dose shown is the Endocrine Society 2016 adrenal-crisis dose.",
                listOf(cite(ATA14, "Recommendation 21b"), cite(ES_PAI, "Rec 4.1")),
            ),
            ProtocolNote(
                "The Popoveniuc 2014 score was derived in 21 patients plus 22 published cases and is not part of the ATA guideline. ATA 2014 does not specify a rewarming method.",
                listOf(cite(MC_SCORE, "Abstract"), cite(ATA14, "Myxedema coma — discussion")),
            ),
            ProtocolNote(
                "India: the ICMR hypothyroidism STW covers outpatient levothyroxine only; no ICMR or MoHFW guidance on myxoedema coma was found. IV levothyroxine and liothyronine availability was not verified.",
                listOf(cite(ICMR_HYPOTHY)),
            ),
        ),
        citations = listOf(cite(ATA14), cite(ICMR_HYPOTHY), cite(MC_SCORE)),
    ),
)
