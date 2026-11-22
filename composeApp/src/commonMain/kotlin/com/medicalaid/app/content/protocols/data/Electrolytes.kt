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
 * Electrolyte emergencies in adults: hyperkalaemia, hypokalaemia, hyponatraemia. Rules:
 * - Doses, thresholds, rates and time windows exactly as the cited document states them (checked against the full
 *   texts, algorithms and labels, 22 November 2026). Potassium and sodium amounts that US labels and ICMR STWs give in
 *   mEq are written as mmol (numerically equal for monovalent ions). Glucose in mg/dL with mmol/L in brackets.
 * - Every point is attributed ("UKKA 2026: …", "ERC 2025: …", "AHA 2025: …", "ESE 2014: …", "US 2013: …", "ICMR STW: …").
 * - UKKA locators are guideline-statement numbers, tables and appendices; ERC 2025 locators are section headings and
 *   figures; ESE 2014 locators are recommendation numbers (Eur J Endocrinol version); US 2013 locators are the boxed
 *   expert-panel recommendations, or the narrative section and page where a statement is only in the narrative.
 * - Existing sources reused by id: em-resus-erc-special-2025 and em-resus-fda-mgso4-label (Resuscitation.kt),
 *   em-resus-aha-special-2025 (DrugTox.kt), em-ab-icmr-stw-aki-2026 (AcidBaseDisorders.kt),
 *   tx-sb-icmr-stw-paralysis-2019 (Envenomation.kt), em-endo-icmr-stw-dka-2022 (EmergencyEndocrine.kt) and
 *   kdigo-ckd-2024 (Sources.kt).
 * - India: ICMR has STWs for AKI, haemodialysis, CKD (all July 2026) and hyponatraemia (July 2022); no ICMR STW for
 *   hyperkalaemia or hypokalaemia as separate topics was found (checked November 2026). No Indian society
 *   (ISN, ISCCM) guideline on these disorders was found.
 * - Not used: the ERC 2025 Special Circumstances corrigendum (Resuscitation 2026;228:111314) could not be retrieved;
 *   CDSCO committee minutes could not be retrieved (site unavailable), so Indian SZC availability rests on the
 *   company's stock-exchange disclosure; patiromer availability in India could not be confirmed and is not stated.
 */

internal val electrolyteSources: List<Source> = listOf(
    // ---------------------------------------------------------------- Potassium
    Source(
        "em-lyte-ukka-hyperk-2026", Authority.UKKA, "UKKA Clinical Practice Guideline (July 2026 update)",
        "Treatment of Acute Hyperkalaemia in Adults (Management of Hyperkalaemia in Adults)", "2026",
        "https://www.ukkidney.org/sites/default/files/documents/FINAL%20VERSION%20-%20UKKA%20CLINICAL%20PRACTICE%20GUIDELINE%20-%20MANAGEMENT%20OF%20HYPERKALAEMIA%20IN%20ADULTS%20-%20UPDATED%20JULY%202026_0.pdf",
        status = "Original July 2020; updated October 2023 and July 2026 (IV calcium downgraded after ILCOR 2025); review date October 2026; hospital and arrest algorithms dated 10 October 2023",
    ),
    Source(
        "em-lyte-icmr-stw-hd-2026", Authority.ICMR, "Standard Treatment Workflow (Nephrology)",
        "Hemodialysis", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1784807343_hemodialysis-new2.pdf",
        status = "July 2026 (ICMR and Department of Health Research, MoHFW)",
    ),
    Source(
        "em-lyte-icmr-stw-ckd-2026", Authority.ICMR, "Standard Treatment Workflow (ICD-11 GB61)",
        "Chronic Kidney Disease (CKD)", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1784807344_chronickidneydisease-updated.pdf",
        status = "July 2026 (ICMR and Department of Health Research, MoHFW)",
    ),
    Source(
        "em-lyte-fda-lokelma", Authority.FDA, "Prescribing information (DailyMed; AstraZeneca)",
        "LOKELMA (sodium zirconium cyclosilicate) for oral suspension", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=90bf8e28-748d-4e4b-a19f-9cf483370eff",
        status = "US label, version effective 11 November 2026",
    ),
    Source(
        "em-lyte-fda-veltassa", Authority.FDA, "Prescribing information (DailyMed; Vifor Pharma)",
        "VELTASSA (patiromer) for oral suspension", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=bf002984-d6c9-46df-aecb-a07733f763c1",
        status = "US label, version effective 31 January 2025",
    ),
    Source(
        "em-lyte-fda-sps", Authority.FDA, "Prescribing information (DailyMed; Marlex Pharmaceuticals)",
        "Sodium polystyrene sulfonate for suspension, for oral or rectal use", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=3423d014-4c35-441a-8528-2a7bc050f809",
        status = "US label, version effective 12 January 2026",
    ),
    Source(
        "em-lyte-az-india-szc-2025", Authority.Paper,
        "Company disclosure to BSE/NSE under SEBI LODR Regulation 30 — AstraZeneca Pharma India Ltd, 15 October 2025",
        "Launch of Sodium Zirconium Cyclosilicate powder for oral suspension 5g (Lokelma)", "2025",
        "https://nsearchives.nseindia.com/corporate/ASTRAZEN_15102025143531_Intimation_to_Stock_Exchanges_Lokelma_launch.pdf",
        status = "Company filing, not a regulator document; refers to CDSCO import and marketing permission (Form CT-20) for 5 g and 10 g, disclosed 6 March 2025; current stock not independently confirmed",
    ),
    Source(
        "em-lyte-fda-kcl-concentrate", Authority.FDA, "Prescribing information (DailyMed; Hospira)",
        "Potassium Chloride for Injection Concentrate, USP", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=559a0a8c-a8fe-40a5-b196-21f9308780ab",
        status = "US label, revised January 2026; version effective 1 July 2026",
    ),
    Source(
        "em-lyte-fda-kcl-premixed", Authority.FDA, "Prescribing information (DailyMed; ICU Medical)",
        "Potassium Chloride Injection (premixed, highly concentrated, 100–400 mEq/L)", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=eb56a807-ea94-4edb-9811-a04b19568468",
        status = "US label, version effective 29 October 2025",
    ),
    Source(
        "em-lyte-fda-kcl-er-tablets", Authority.FDA, "Prescribing information (DailyMed; Upsher-Smith)",
        "Potassium chloride extended-release tablets, 10 mEq and 20 mEq", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=02707146-331f-4511-a187-0152fc8bca85",
        status = "US label, version effective 24 July 2026",
    ),
    Source(
        "em-lyte-kardalas-hypokalaemia-2018", Authority.Paper, "Review (Endocr Connect 2018;7(4):R135–R146)",
        "Hypokalemia: a clinical update", "2018",
        "https://doi.org/10.1530/EC-18-0109",
        status = "Kardalas E et al.; narrative review — used for oral replacement, causes and urinary work-up where guidelines are silent",
    ),
    // ---------------------------------------------------------------- Sodium
    Source(
        "em-lyte-ese-hyponatraemia-2014", Authority.ESE, "ESE/ESICM/ERBP guideline — Eur J Endocrinol 2014;170(3):G1–G47",
        "Clinical practice guideline on diagnosis and treatment of hyponatraemia", "2014",
        "https://doi.org/10.1530/EJE-13-1020",
        status = "Joint ESE, ESICM and ERBP (ERA-EDTA) guideline; also Nephrol Dial Transplant 2014;29 Suppl 2:i1–i39 and Intensive Care Med 2014;40:320–331; no newer edition found (November 2026)",
    ),
    Source(
        "em-lyte-us-panel-hyponatraemia-2013", Authority.Paper, "US expert panel recommendations — Am J Med 2013;126(10 Suppl 1):S1–S42",
        "Diagnosis, evaluation, and treatment of hyponatremia: expert panel recommendations", "2013",
        "https://doi.org/10.1016/j.amjmed.2013.07.006",
        status = "Verbalis JG et al.; CME activity supported by an unrestricted educational grant from Otsuka America (tolvaptan manufacturer)",
    ),
    Source(
        "em-lyte-icmr-stw-hyponatraemia-2022", Authority.ICMR, "Standard Treatment Workflow (ICD-10 E87.1)",
        "Approach to Hyponatremia", "2022",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567243_approach_to_hyponatremia.pdf",
        status = "July 2022 (Department of Health Research, MoHFW)",
    ),
    Source(
        "em-lyte-fda-samsca", Authority.FDA, "Prescribing information (DailyMed; Otsuka)",
        "SAMSCA (tolvaptan) tablets", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=5526617c-c7b9-4556-886d-729bbabbc566",
        status = "US label, version effective 7 May 2026",
    ),
    Source(
        "em-lyte-salsa-2021", Authority.Paper, "RCT, 3 Korean hospitals (JAMA Intern Med 2021;181(1):81–92)",
        "Risk of Overcorrection in Rapid Intermittent Bolus vs Slow Continuous Infusion Therapies of Hypertonic Saline for Patients With Symptomatic Hyponatremia: The SALSA Randomized Clinical Trial",
        "2021",
        "https://doi.org/10.1001/jamainternmed.2020.5519",
    ),
)

private const val UKKA = "em-lyte-ukka-hyperk-2026"
private const val ICMR_HD = "em-lyte-icmr-stw-hd-2026"
private const val ICMR_CKD = "em-lyte-icmr-stw-ckd-2026"
private const val FDA_SZC = "em-lyte-fda-lokelma"
private const val FDA_PATIROMER = "em-lyte-fda-veltassa"
private const val FDA_SPS = "em-lyte-fda-sps"
private const val AZ_INDIA_SZC = "em-lyte-az-india-szc-2025"
private const val FDA_KCL_CONC = "em-lyte-fda-kcl-concentrate"
private const val FDA_KCL_PREMIX = "em-lyte-fda-kcl-premixed"
private const val FDA_KCL_ORAL = "em-lyte-fda-kcl-er-tablets"
private const val KARDALAS = "em-lyte-kardalas-hypokalaemia-2018"
private const val ESE = "em-lyte-ese-hyponatraemia-2014"
private const val US_PANEL = "em-lyte-us-panel-hyponatraemia-2013"
private const val ICMR_NA = "em-lyte-icmr-stw-hyponatraemia-2022"
private const val FDA_TOLVAPTAN = "em-lyte-fda-samsca"
private const val SALSA = "em-lyte-salsa-2021"

/** Defined in Resuscitation.kt — ERC Guidelines 2025 Special Circumstances in Resuscitation. */
private const val ERC_SPECIAL = "em-resus-erc-special-2025"

/**
 * Defined in Resuscitation.kt — US label, magnesium sulfate 50%. Its status line there ("cited only for mmol-per-mL
 * content and dilution") predates this file, which also cites the label's renal-insufficiency cautions.
 */
private const val FDA_MG = "em-resus-fda-mgso4-label"

/** Defined in DrugTox.kt — AHA 2025 Part 10, Special Circumstances of Resuscitation. */
private const val AHA_SPECIAL = "em-resus-aha-special-2025"

/** Defined in AcidBaseDisorders.kt — ICMR STW Acute Kidney Injury (July 2026). */
private const val ICMR_AKI = "em-ab-icmr-stw-aki-2026"

/** Defined in Envenomation.kt — ICMR STW Approach to Acute Paralysis (2019). */
private const val ICMR_PARALYSIS = "tx-sb-icmr-stw-paralysis-2019"

/** Defined in EmergencyEndocrine.kt — ICMR STW Diabetic Ketoacidosis (2022). */
private const val ICMR_DKA = "em-endo-icmr-stw-dka-2022"

/** Defined in Sources.kt — KDIGO 2024 CKD guideline. */
private const val KDIGO_CKD = "kdigo-ckd-2024"

// ================================================================================================ Hyperkalaemia
private val hyperkalaemia = Protocol(
    id = "hyperkalaemia",
    category = ProtocolCategory.Metabolic,
    title = "Hyperkalaemia",
    summary = "Severity bands and ECG signs, IV calcium, insulin–glucose with glucose monitoring, salbutamol, potassium " +
        "binders, the limited role of bicarbonate, dialysis and hyperkalaemic arrest — UKKA 2026, ERC/AHA 2025 and ICMR STWs.",
    keywords = listOf(
        "hyperkalaemia", "hyperkalemia", "high potassium", "raised potassium", "serum potassium", "K+", "potassium 6.5",
        "peaked T waves", "tall tented T waves", "broad QRS", "sine wave", "BRASH", "pseudohyperkalaemia", "haemolysed sample",
        "calcium gluconate", "calcium chloride", "insulin dextrose", "insulin glucose", "salbutamol nebulisation", "respules",
        "potassium binder", "sodium zirconium cyclosilicate", "SZC", "Lokelma", "patiromer", "Veltassa",
        "calcium polystyrene sulfonate", "sodium polystyrene sulfonate", "calcium resonium", "Kayexalate", "resin",
        "sodium bicarbonate", "dialysis", "haemodialysis", "AEIOU", "AKI", "acute kidney injury", "CKD", "renal failure",
        "ACE inhibitor", "ARB", "spironolactone", "hyperkalaemic cardiac arrest", "DKA",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Confirm, grade severity and read the ECG",
            timing = "At first contact",
            points = listOf(
                "UKKA 2026 and ERC 2025: mild 5.5–5.9, moderate 6.0–6.4, severe ≥6.5 mmol/L; ERC adds 'extreme' ≥9.0. AHA 2025: adult hyperkalaemia is >5.0.",
                "UKKA 2026: assess with ABCDE and an early warning score; 12-lead ECG and rhythm monitoring if K⁺ ≥6.0 (ERC 2025 algorithm: ≥6.5).",
                "UKKA 2026 and ERC 2025: confirm with point-of-care (blood-gas) K⁺ while the laboratory result is awaited. ERC: treatment can start on ECG evidence alone.",
                "UKKA 2026: about two in three with K⁺ ≥6.5 have ECG changes, but reports vary — some find none in at least half; in one series 66% at 6.5–6.9, rising to 100% at ≥8.0. A normal ECG does not exclude severe hyperkalaemia.",
                "UKKA 2026: QRS prolongation, bradycardia <50/min and junctional rhythm correlate best with adverse events; such bradycardia may resist atropine and pacing.",
                "ERC 2025: limb weakness, flaccid paralysis or paraesthesia may indicate severe hyperkalaemia; arrhythmia or arrest occurred in 15% within 6 h.",
            ),
            checklistTitle = "ECG signs of hyperkalaemia (ERC 2025)",
            checklist = listOf(
                "Tall, peaked (tented) T waves — T larger than R in more than one lead",
                "First-degree heart block (PR >0.2 s)",
                "Flattened or absent P waves",
                "Widened QRS (>0.12 s)",
                "Sine wave",
                "Ventricular tachycardia",
                "Bradycardia",
                "Cardiac arrest (PEA, VF/VT, asystole)",
            ),
            scoreIds = listOf("news2"),
            citations = listOf(
                cite(UKKA, "Introduction (severity bands); Guidelines 13.2, 14.1–14.2, 15.2 and rationale; Guideline 16.2a rationale (Table 13); Guidelines 26.1–26.2 rationale (p. 158); Appendix 7"),
                cite(ERC_SPECIAL, "Hyperkalaemia – Non-cardiac arrest; Diagnosis of hyperkalaemia; Fig. 2"),
                cite(AHA_SPECIAL, "Hyperkalemia — Synopsis"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Exclude a spurious result and look for the cause",
            points = listOf(
                "UKKA 2026: suspect pseudohyperkalaemia with normal renal function, normal ECG or a blood disorder; send paired clotted and lithium-heparin samples from a large vein.",
                "UKKA 2026: serum K⁺ exceeding plasma K⁺ by >0.4 mmol/L confirms it — delayed transport, difficult venepuncture, haemolysis, high platelets, EDTA contamination, drip arm.",
                "KDIGO 2024: potassium results vary with time of day, season, plasma versus serum sampling and medications.",
                "UKKA 2026: full drug history — most in-patient episodes involve AKI or potassium-raising drugs, often two or more together.",
                "ICMR STW AKI 2026: in AKI check CPK for rhabdomyolysis, LDH and bilirubin for haemolysis; ask about envenomation, poisoning, heat stroke and indigenous medicines.",
                "UKKA 2026: BRASH — bradycardia, renal failure, AV-nodal blockers, shock and hyperkalaemia; over half had K⁺ <6.5 mmol/L.",
            ),
            checklistTitle = "Drugs that raise potassium (UKKA 2026 Table 32)",
            checklist = listOf(
                "ACE inhibitors, ARBs",
                "MRAs — spironolactone, eplerenone, finerenone",
                "Amiloride, triamterene; trimethoprim/co-trimoxazole; pentamidine",
                "NSAIDs, heparins, calcineurin inhibitors, azole antifungals",
                "β-blockers, digoxin, suxamethonium",
                "Potassium supplements, salt substitutes, herbal medicines, stored red cells",
            ),
            citations = listOf(
                cite(UKKA, "Guideline 15.3 and rationale; Guidelines 14.1–14.2 rationale (BRASH syndrome); Guidelines 21.1–21.4 rationale; Table 32"),
                cite(KDIGO_CKD, "Practice Point 3.11.1.1"),
                cite(ICMR_AKI, "AKI risk factors; Desirable actions/investigations"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Protect the heart: IV calcium",
            timing = "Immediately if ECG changes — acts within 3 min, lasts 30–60 min (UKKA 2026)",
            points = listOf(
                "UKKA 2026 and ERC 2025: hyperkalaemia with ECG changes — IV calcium 6.8 mmol: 30 mL 10% calcium gluconate over 10 min.",
                "UKKA 2026: 10 mL 10% calcium chloride over 5 min is preferred in cardiac arrest or peri-arrest; it holds 3 times the calcium of gluconate.",
                "UKKA 2026: repeat the ECG; consider a further dose after 5 min if changes persist, or once the effect wears off (~60 min) in refractory hyperkalaemia.",
                "ICMR STW AKI 2026: 10 mL 10% calcium gluconate slow IV over 2–5 min under ECG monitoring, repeatable up to 3 times.",
                "UKKA 2026: give for any ECG change, including isolated peaked T waves; not with a normal ECG. Calcium does not lower K⁺ — start lowering treatment.",
                "UKKA 2026: use a large vein — extravasation causes tissue necrosis (more with chloride); caution with digoxin, though harm is reported only in case reports.",
            ),
            citations = listOf(
                cite(UKKA, "Guidelines 16.2a–16.2b and rationale; Table 12; Appendix 4A; Appendix 7"),
                cite(ERC_SPECIAL, "Fig. 2; Intravenous calcium salts"),
                cite(ICMR_AKI, "Treatment of hyperkalemia"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Shift potassium into cells: insulin–glucose and salbutamol",
            timing = "Start promptly; insulin–glucose peaks at 30–60 min (UKKA 2026)",
            points = listOf(
                "UKKA 2026 and ERC 2025: moderate or severe — 10 units soluble insulin with 25 g glucose IV over 15–30 min; check glucose first.",
                "UKKA 2026: 25 g glucose = 50 mL 50%, 125 mL 20% or 250 mL 10%. ICMR STW AKI: 10 units in 50 mL 50% or 100 mL 25% dextrose.",
                "UKKA 2026 and ERC 2025: pre-treatment glucose <126 mg/dL (7.0 mmol/L) — then 10% glucose 50 mL/h for 5 h; UKKA target 72–126 mg/dL (4.0–7.0 mmol/L).",
                "UKKA 2026 and ERC 2025: nebulised salbutamol 10–20 mg as an adjunct (ICMR: 10 mg as 4 respules of 2.5 mL salbutamol). UKKA: consider only 10 mg in ischaemic heart disease.",
                "UKKA 2026: never salbutamol alone in severe hyperkalaemia — 20–40% have a fall <0.5 mmol/L. ERC 2025: less effective with non-selective β-blockers.",
                "UKKA 2026: if K⁺ is not <6.0 mmol/L within 2 h of insulin–glucose, consider a further infusion — repeat doses raise hypoglycaemia risk.",
                "UKKA 2026: in DKA, high K⁺ is a shift out of cells and total-body K⁺ may be low; it falls with fluids and insulin — follow the DKA protocol and monitor K⁺ and glucose closely.",
            ),
            checklistTitle = "Higher hypoglycaemia risk after insulin–glucose (UKKA 2026 Table 21)",
            checklist = listOf(
                "Low pre-treatment blood glucose",
                "Renal impairment — AKI, CKD 4–5, dialysis",
                "Low body weight",
                "Older age",
                "No diabetes",
                "Insulin ≥10 units or glucose ≤25 g",
            ),
            citations = listOf(
                cite(UKKA, "Guidelines 16.3.1–16.3.3, 16.4.1–16.4.3 and rationale; Tables 21–23; Appendix 4B–4C; Appendix 7"),
                cite(ERC_SPECIAL, "Fig. 2; Insulin and glucose; Salbutamol"),
                cite(ICMR_AKI, "Treatment of hyperkalemia"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Remove potassium: binders, and what not to rely on",
            points = listOf(
                "UKKA 2026: sodium zirconium cyclosilicate (SZC) 10 g three times daily for up to 72 h — recommended if severe, consider if moderate; acts within 1 h.",
                "ERC 2025: SZC 10 g or patiromer 8.4 g alongside insulin–glucose. UKKA 2026: patiromer 8.4 g once daily (onset 4–7 h) is an option at K⁺ ≥6.0.",
                "US labels (SZC, patiromer, sodium polystyrene): not for emergency treatment of life-threatening hyperkalaemia — delayed onset. US SZC dose: 10 g three times daily up to 48 h.",
                "UKKA 2026: calcium resonium no longer routine in acute hyperkalaemia — onset >4 h, unpredictable, intestinal necrosis reported. US label: avoid sorbitol with sodium polystyrene.",
                "India: SZC 5 g and 10 g received DCGI import and marketing permission (Form CT-20; disclosed March 2025), but only the 5 g sachet was announced for launch (November 2025) — a 10 g dose may need two 5 g sachets; check local stock (company disclosure).",
                "ICMR STW AKI 2026: binders if dialysis is unavailable.",
                "UKKA 2026 (2C) and ERC 2025: IV sodium bicarbonate is not routine outside cardiac arrest. ERC: ILCOR found a fall of only 0.1 mmol/L within 60 min.",
            ),
            checklistTitle = "Binder cautions",
            checklist = listOf(
                "SZC and patiromer: avoid in severe constipation, bowel obstruction or impaction, or post-operative dysmotility; sodium polystyrene: contraindicated in obstructive bowel disease (US labels)",
                "SZC: about 400 mg sodium per 5 g — watch for oedema in heart failure or CKD (US label)",
                "SZC: give 2 h apart from oral drugs with pH-dependent absorption (UKKA Appendix 4E)",
                "Patiromer: hypomagnesaemia; give other oral drugs 3 h apart (US label)",
                "Sodium polystyrene: 60 mmol sodium per 15 g; intestinal necrosis, mostly with sorbitol (US label)",
                "Calcium resonium: not in hypercalcaemia or obstructive bowel disease (UKKA Appendix 4F)",
            ),
            citations = listOf(
                cite(UKKA, "Guidelines 16.5, 16.6.1a–16.6.2, 16.2 (cation-exchange resin) and rationale; Guideline 17.1 rationale; Appendix 4D–4F; Appendix 7"),
                cite(ERC_SPECIAL, "Fig. 2; Sodium bicarbonate; Potassium binders"),
                cite(FDA_SZC, "1 Indications and usage; 2.1; 5.1–5.2"),
                cite(FDA_PATIROMER, "1 Indications and usage; 2.1–2.2; 5.1–5.2; 7; 12.2"),
                cite(FDA_SPS, "1 Indications and usage; 4 Contraindications; 5.1; 5.3; 7.3"),
                cite(AZ_INDIA_SZC, "Disclosure of 15 October 2025 (refers to the CT-20 permission disclosed 6 March 2025)"),
                cite(ICMR_AKI, "Treatment of hyperkalemia"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Monitor potassium and glucose",
            timing = "K⁺ at 1, 2, 4, 6 and 24 h; glucose to 6 h (UKKA 2026)",
            points = listOf(
                "UKKA 2026: aim for K⁺ <6.0 mmol/L within 2 h; check K⁺ at least 1, 2, 4, 6 and 24 h after treating moderate or severe hyperkalaemia.",
                "UKKA 2026 and ERC 2025: insulin–glucose and salbutamol last 4–6 h — watch for rebound at 4–6 h.",
                "UKKA 2026: glucose at 0, 30, 60, 90, 120, 180, 240, 300 and 360 min after insulin–glucose; hypoglycaemia is <72 mg/dL (4.0 mmol/L).",
                "UKKA 2026: continuous cardiac monitoring for K⁺ ≥6.5, ECG changes, or 6.0–6.4 if unwell or rising — ideally in a higher-dependency area.",
                "UKKA 2026 algorithm: stop the oral binder when K⁺ ≤5.0 mmol/L and watch for rebound after stopping.",
            ),
            citations = listOf(
                cite(UKKA, "Guidelines 14.2, 17.1.1–17.2 and rationale; Table 27; Appendix 7"),
                cite(ERC_SPECIAL, "Salbutamol (duration of effect)"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Dialysis and escalation",
            timing = "Seek expert help early in severe hyperkalaemia",
            points = listOf(
                "ERC 2025: dialysis for severe life-threatening hyperkalaemia, resistance to medical treatment, end-stage kidney disease, oliguric AKI (<400 mL/day) or marked tissue breakdown.",
                "UKKA 2026: consider dialysis if K⁺ ≥6.5 persists despite medical therapy; a nephrologist or intensivist decides timing and modality urgently.",
                "UKKA 2026: haemodialysis patients with K⁺ ≥6.5 — urgent dialysis, and IV calcium for toxic ECG changes even when dialysis is immediately available.",
                "ICMR STW haemodialysis 2026: refractory hyperkalaemia or ECG changes — IV calcium, insulin–dextrose, salbutamol, then dialyse (AEIOU indication).",
                "ICMR STW haemodialysis 2026: HD unavailable and patient cannot be moved — stabilise medically and refer; consider acute peritoneal dialysis as a bridge.",
                "ICMR STW AKI 2026: urgent dialysis if life-threatening; a dialysis indication is a red flag for urgent referral. ERC 2025: rebound can follow dialysis.",
            ),
            citations = listOf(
                cite(ERC_SPECIAL, "Indications for dialysis"),
                cite(UKKA, "Guidelines 18.1–18.3, 19.1–19.6; Appendix 7"),
                cite(ICMR_HD, "When to start HD — AEIOU; Red flags — stabilise and refer; HD in acute/limited settings"),
                cite(ICMR_AKI, "Treatment of hyperkalemia; Indications for dialysis; Red flags for urgent referral"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Hyperkalaemic cardiac arrest",
            timing = "During ALS — first 15 min (UKKA 2026 algorithm)",
            points = listOf(
                "UKKA 2026: consider hyperkalaemia in every arrest (4 Hs and 4 Ts) and follow standard ALS, including in dialysis patients.",
                "ERC 2025: IV 10 mL 10% calcium chloride plus 50 mmol sodium bicarbonate, via separate lines or flushed between. UKKA: consider repeat calcium if no ROSC by 5–10 min.",
                "UKKA 2026: 10 units soluble insulin with 25 g glucose, then 10% glucose if glucose <126 mg/dL (7.0 mmol/L). AHA 2025 Table 3: insulin over 15–30 min, glucose 50 g.",
                "AHA 2025: effectiveness of IV calcium, bicarbonate and insulin–glucose in arrest not well established (2b); inhaled β2-agonists not recommended.",
                "UKKA 2026: no ROSC — seek expert help and consider dialysis or ECMO during CPR; disconnect dialysis equipment before defibrillation unless defibrillator-proof.",
            ),
            citations = listOf(
                cite(UKKA, "Guidelines 23.1–25.4; Appendix 8"),
                cite(ERC_SPECIAL, "Summary — Hyperkalaemia; Resuscitation of hyperkalaemic cardiac arrest"),
                cite(AHA_SPECIAL, "Hyperkalemia Rec 1–4; Table 3"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "Prevent recurrence",
            points = listOf(
                "UKKA 2026: review potassium-raising drugs, weigh recurrence risk before restarting them, and arrange community blood tests after discharge.",
                "UKKA 2026 (acute): withhold RAAS inhibitors during acute illness (sepsis, hypovolaemia, AKI) at any hyperkalaemia severity; stop them at K⁺ ≥6.0 unless SZC/patiromer criteria are met.",
                "KDIGO 2024 (chronic CKD care): hyperkalaemia on RAS inhibitors can often be managed by lowering potassium rather than reducing or stopping the RAS inhibitor.",
                "ERC 2025 (longer term): down-titrating or stopping RAAS inhibitors after hyperkalaemia is associated with worse outcomes; binders can support cardio-renal therapy.",
                "UKKA 2026 (July 2026 update): SZC for persistent K⁺ ≥5.5 mmol/L in CKD 3b–5 (not on dialysis) or heart failure on suboptimal RAAS inhibitor doses.",
                "ICMR STW CKD 2026: restrict potassium and salt substitutes; avoid fruit juices, coconut water, carbonated drinks. Lower-potassium: apple, papaya, pear, watermelon, cabbage, cauliflower, brinjal, cucumber.",
                "UKKA 2026: dietary advice after non-dietary causes (constipation, acidosis, poor diabetes control) are addressed. ICMR STW: in AKI withhold ACEi/ARB; in CKD caution if K⁺ >5.5.",
            ),
            citations = listOf(
                cite(UKKA, "Guidelines 2.6–2.7, 10.1, 21.1–21.4; Summary of key changes July 2026"),
                cite(KDIGO_CKD, "Practice Points 3.6.3, 3.11.5.1–3.11.5.2"),
                cite(ERC_SPECIAL, "Hyperkalaemia – Non-cardiac arrest"),
                cite(ICMR_CKD, "Nutrition; Low potassium fruits/vegetables; BP control (ACE/ARB footnote)"),
                cite(ICMR_AKI, "Treatment of hyperkalemia"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "IV calcium dosing differs: UKKA 2026 and ERC 2025 give one 6.8 mmol dose (30 mL 10% gluconate over 10 min or 10 mL 10% chloride over 5 min); " +
                "ICMR STW AKI 2026 gives 10 mL 10% gluconate over 2–5 min, repeated up to 3 times. UKKA cites a study in which 97% needed three 10 mL doses.",
            listOf(cite(UKKA, "Guideline 16.2a rationale"), cite(ERC_SPECIAL, "Fig. 2"), cite(ICMR_AKI, "Treatment of hyperkalemia")),
        ),
        ProtocolNote(
            "The evidence for IV calcium is weak: the 2025 ILCOR review found none for or against it. UKKA downgraded its recommendation to 2C in July 2026; " +
                "ERC 2025 keeps calcium for severe hyperkalaemia with ECG changes; AHA 2025 calls its effectiveness in arrest not well established.",
            listOf(
                cite(UKKA, "Purpose of guideline update — July 2026; Guideline 16.2a"),
                cite(ERC_SPECIAL, "Intravenous calcium salts"),
                cite(AHA_SPECIAL, "Hyperkalemia Rec 1"),
            ),
        ),
        ProtocolNote(
            "Binders in emergencies: US labels for SZC, patiromer and sodium polystyrene say they should not be used as emergency treatment because of delayed onset; " +
                "UKKA 2026 and ERC 2025 give SZC (onset within 1 h) alongside insulin–glucose. SZC acute course: up to 72 h (UKKA) versus up to 48 h (US label).",
            listOf(
                cite(FDA_SZC, "1 Indications and usage; 2.1"),
                cite(FDA_PATIROMER, "1 Indications and usage"),
                cite(FDA_SPS, "1 Indications and usage"),
                cite(UKKA, "Guidelines 16.6.1a–16.6.2; Appendix 4E"),
                cite(ERC_SPECIAL, "Potassium binders"),
            ),
        ),
        ProtocolNote(
            "Sodium bicarbonate: not routine outside arrest (UKKA 2026, ERC 2025). In hyperkalaemic arrest ERC 2025 gives 50 mmol with calcium, " +
                "UKKA suggests it (2C), and AHA 2025 rates its effectiveness as not well established.",
            listOf(
                cite(UKKA, "Guidelines 16.5, 25.3"),
                cite(ERC_SPECIAL, "Sodium bicarbonate; Summary — Hyperkalaemia"),
                cite(AHA_SPECIAL, "Hyperkalemia Rec 2"),
            ),
        ),
        ProtocolNote(
            "Thresholds differ: UKKA 2026 asks for a 12-lead ECG and rhythm monitoring from K⁺ 6.0 mmol/L, the ERC 2025 algorithm from 6.5; " +
                "AHA 2025 defines adult hyperkalaemia as >5.0 rather than UKKA/ERC's 5.5.",
            listOf(cite(UKKA, "Guidelines 14.1–14.2; Appendix 7"), cite(ERC_SPECIAL, "Fig. 2"), cite(AHA_SPECIAL, "Hyperkalemia — Synopsis")),
        ),
        ProtocolNote(
            "India: ICMR STWs (AKI and haemodialysis, 2026) rely on calcium gluconate, salbutamol and insulin–dextrose, reserve binders for when dialysis is unavailable, " +
                "and use acute peritoneal dialysis as a bridge where haemodialysis cannot be reached.",
            listOf(cite(ICMR_AKI, "Treatment of hyperkalemia"), cite(ICMR_HD, "HD in acute/limited settings")),
        ),
        ProtocolNote(
            "UKKA's guideline was due for review in October 2026 — check for a newer version.",
            listOf(cite(UKKA, "Title page (review date)")),
        ),
    ),
    citations = listOf(
        cite(UKKA), cite(ERC_SPECIAL), cite(AHA_SPECIAL), cite(ICMR_AKI), cite(ICMR_HD), cite(ICMR_CKD), cite(KDIGO_CKD),
        cite(FDA_SZC), cite(FDA_PATIROMER), cite(FDA_SPS), cite(AZ_INDIA_SZC),
    ),
)

// ================================================================================================ Hypokalaemia
private val hypokalaemia = Protocol(
    id = "hypokalaemia",
    category = ProtocolCategory.Metabolic,
    title = "Hypokalaemia",
    summary = "Severity, ECG and muscle signs, oral versus IV potassium, IV concentration and rate limits, magnesium co-repletion, " +
        "monitoring and causes — ERC 2025, US product labels and ICMR STWs.",
    keywords = listOf(
        "hypokalaemia", "hypokalemia", "low potassium", "serum potassium", "K+", "potassium chloride", "KCl", "IV potassium",
        "potassium replacement", "potassium infusion", "U waves", "periodic paralysis", "hypokalaemic periodic paralysis",
        "thyrotoxic periodic paralysis", "acute flaccid paralysis", "quadriparesis", "muscle weakness", "rhabdomyolysis",
        "hypomagnesaemia", "hypomagnesemia", "magnesium sulphate", "magnesium sulfate", "diuretic", "thiazide", "furosemide",
        "diarrhoea", "vomiting", "renal tubular acidosis", "RTA", "Gitelman", "Bartter", "primary aldosteronism", "Conn",
        "digoxin", "DKA",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Confirm, grade severity and look for danger signs",
            timing = "At first contact",
            points = listOf(
                "ERC 2025: hypokalaemia is K⁺ <3.5 mmol/L — mild 3.0–3.4, moderate 2.5–2.9, severe <2.5 or symptomatic (algorithm: ≤2.4).",
                "ERC 2025: ABCDE; 12-lead ECG and rhythm monitoring if K⁺ <3.0 mmol/L. Risk is higher with digoxin, heart disease or STEMI undergoing PCI.",
                "ERC 2025: ECG — U waves, prolonged PR, T-wave flattening or inversion, ST depression, arrhythmias. Muscle — paralysis, rhabdomyolysis, respiratory difficulty.",
                "Endocr Connect 2018 review: symptoms usually appear below 3.0 mmol/L unless the fall is rapid; ileus and ascending paralysis can occur.",
                "ICMR STW acute paralysis: acute proximal weakness, no upper motor neuron signs, absent reflexes — GBS or hypokalaemic periodic paralysis; check serum K⁺.",
                "ICMR STW acute paralysis: intubate if unable to complete sentences, with low single-breath count and breath-holding time and poor chest expansion.",
            ),
            checklistTitle = "Red flags (ERC 2025 Fig. 3)",
            checklist = listOf(
                "K⁺ ≤2.4 mmol/L — emergency treatment indicated",
                "K⁺ 2.5–2.9 with symptoms, or unable to take oral replacement — IV potassium",
                "ECG changes or arrhythmia",
                "Paralysis, rhabdomyolysis or respiratory difficulty",
                "Taking digoxin",
                "Magnesium <0.6 mmol/L",
            ),
            citations = listOf(
                cite(ERC_SPECIAL, "Hypokalaemia; Fig. 3"),
                cite(KARDALAS, "Signs and symptoms; Table 2"),
                cite(ICMR_PARALYSIS, "Proximal weakness, reflexes absent — management; Admission criteria"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Cardiac arrest or peri-arrest",
            timing = "Immediately",
            points = listOf(
                "ERC 2025: hypokalaemic arrest — start ALS, give adrenaline, confirm hypokalaemia; IV potassium 20 mmol over 2–3 min, then 10 mmol over 2 min.",
                "ERC 2025: after arrest doses, start an infusion and continue until K⁺ ≥4.0 mmol/L; magnesium sulphate 2 g (8 mmol) over 2 min if required.",
                "ERC 2025: peri-arrest — 20 mmol over 10 min, then 10 mmol over 10 min; then start an infusion and tailor the rate.",
                "ERC 2025: rapid IV replacement in an emergency is preferably via a central line.",
            ),
            citations = listOf(
                cite(ERC_SPECIAL, "Summary — Hypokalaemia; Treatment of hypokalaemia; Fig. 3"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Choose oral or IV replacement",
            points = listOf(
                "ERC 2025: slow replacement is preferable; rapid IV replacement only in an emergency. Target K⁺ 4.0 mmol/L.",
                "Endocr Connect 2018 review: K⁺ 2.5–3.5 mmol/L may need only oral potassium; IV below 2.5, or with severe nausea, vomiting or abdominal distress.",
                "US label (KCl extended-release tablets): treatment 40–100 mmol/day in divided doses, no more than 20 mmol per dose, with meals and a full glass of water. Contraindicated with amiloride or triamterene; swallow whole, do not crush or chew.",
                "Endocr Connect 2018 review: each 1 mmol/L fall in K⁺ reflects roughly 200–400 mmol total deficit — an imprecise estimate.",
                "ERC 2025 and review: severe renal impairment or dialysis — seek expert or renal advice before replacing potassium.",
            ),
            citations = listOf(
                cite(ERC_SPECIAL, "Treatment of hypokalaemia; Fig. 3"),
                cite(KARDALAS, "Treatment of hypokalemia"),
                cite(FDA_KCL_ORAL, "2.1–2.2; 4 Contraindications; 7.1"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "IV potassium: dilution, rate and route",
            timing = "Rate guided by clinical urgency (ERC 2025)",
            points = listOf(
                "ERC 2025: 40 mmol KCl in 1000 mL 0.9% NaCl — standard rate 10 mmol/h; maximum 20 mmol/h in HDU/ICU.",
                "US label (KCl concentrate): K⁺ >2.5 — up to 10 mmol/h at up to 40 mmol/L, no more than 200 mmol in 24 h.",
                "US label: K⁺ <2.0 with ECG changes or paralysis — up to 40 mmol/h with continuous cardiac monitoring, up to 400 mmol in 24 h.",
                "US labels: central route preferred whenever possible (pain, extravasation); 300–400 mmol/L premixed solutions by central line only.",
                "US label and review: dilute in 0.9% saline rather than dextrose, which can lower K⁺. Concentrate must be diluted — direct injection may be instantly fatal.",
            ),
            checklistTitle = "Do not",
            checklist = listOf(
                "Inject KCl concentrate undiluted (US label: may be instantly fatal), or give ERC's 20 mmol over 2–3 min outside hypokalaemic cardiac arrest (peri-arrest: 20 mmol over 10 min)",
                "Run 300–400 mmol/L solutions through a peripheral cannula (US label: central only)",
                "Infuse fast without continuous ECG monitoring (US label)",
                "Use dextrose as the diluent in severe hypokalaemia (US label: saline preferred)",
                "Replace in severe renal impairment without expert advice (ERC 2025)",
            ),
            citations = listOf(
                cite(ERC_SPECIAL, "Summary — Hypokalaemia; Fig. 3; Treatment of hypokalaemia"),
                cite(FDA_KCL_CONC, "Warnings (undiluted administration); Dosage and administration"),
                cite(FDA_KCL_PREMIX, "Warnings; Dosage and administration"),
                cite(KARDALAS, "Treatment of hypokalemia"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Correct magnesium at the same time",
            points = listOf(
                "ERC 2025: magnesium deficiency (<0.6 mmol/L) worsens hypokalaemia; correct it concurrently — repletion speeds potassium correction.",
                "ERC 2025: 50% magnesium sulphate (1 g = 4 mmol) — standard 2 g (8 mmol) over 4 h.",
                "ERC 2025: medical emergency — 2 g (8 mmol) over 10–15 min, may be repeated once; cardiac arrest — 2 g over 2 min.",
                "US label: magnesium is cleared only by the kidneys — renal insufficiency risks magnesium toxicity; in severe renal impairment do not exceed 20 g in 48 h and monitor serum Mg.",
                "US label: dilute 50% magnesium sulphate to 20% or less before IV infusion; it contains 2.03 mmol per mL.",
                "Endocr Connect 2018 review: if hypomagnesaemic, 8 mmol (4 mL 50%) diluted in 10 mL 0.9% saline over 20 min, then the first 40 mmol KCl infusion.",
            ),
            citations = listOf(
                cite(ERC_SPECIAL, "Treatment of hypokalaemia; Fig. 3"),
                cite(FDA_MG, "Description; Clinical pharmacology; Warnings; Precautions; Dosage and administration"),
                cite(KARDALAS, "Table 3"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Find and treat the cause",
            points = listOf(
                "Endocr Connect 2018 review: most causes are clear from history — vomiting, diarrhoea, diuretics; treat the cause and stop the loss.",
                "Review: urine K⁺ >15 mmol/day, or spot K⁺:creatinine >13 mEq/g (1.5 mmol/mmol), indicates renal loss; then assess acid–base status.",
                "Review: renal loss with alkalosis — diuretics, vomiting, Gitelman or Bartter (urine chloride helps); with hypertension, screen for primary aldosteronism.",
                "Review: low urinary K⁺ with acidosis suggests lower GI loss (laxatives, villous adenoma); renal loss with acidosis suggests DKA or renal tubular acidosis.",
                "Review: check magnesium, calcium and phosphate, digoxin level if on digoxin, and TSH with tachycardia or suspected periodic paralysis.",
                "ICMR STW DKA: give potassium before insulin if K⁺ <3.3 mmol/L or ECG changes; 10–20 mmol/h with insulin if K⁺ <5.5.",
            ),
            checklistTitle = "Causes (Endocr Connect 2018 Table 1)",
            checklist = listOf(
                "GI loss — chronic diarrhoea, laxative abuse",
                "Diuretics — thiazide, loop, osmotic",
                "Shift into cells — insulin, β2-agonists, thyrotoxicosis, familial periodic paralysis",
                "Hypomagnesaemia",
                "Mineralocorticoid excess — primary aldosteronism, Cushing's, liquorice (glycyrrhizin)",
                "Renal tubular acidosis; Bartter, Gitelman, Liddle syndromes",
                "Drugs — amphotericin B, high-dose penicillin, theophylline",
            ),
            citations = listOf(
                cite(KARDALAS, "Causes of hypokalemia; Table 1; Diagnosis; Assessment of urinary potassium excretion; Assessment of acid–base status; Treatment"),
                cite(ICMR_DKA, "Management — potassium"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Monitor and avoid overshoot",
            points = listOf(
                "ERC 2025: monitor serum K⁺ and blood glucose; adjust the infusion rate to the K⁺ level, target 4.0 mmol/L.",
                "Endocr Connect 2018 review: IV replacement below 2.5 mmol/L needs close follow-up, continuous ECG monitoring and serial K⁺; on oral replacement check K⁺ daily.",
                "US label: KCl is contraindicated in hyperkalaemia, renal failure and potassium retention; renal insufficiency risks life-threatening hyperkalaemia.",
                "US label: if hyperkalaemia occurs, stop the infusion immediately and treat; stop potassium-containing foods and medicines.",
            ),
            citations = listOf(
                cite(ERC_SPECIAL, "Fig. 3"),
                cite(KARDALAS, "Treatment of hypokalemia; Table 3"),
                cite(FDA_KCL_PREMIX, "Contraindications; Warnings; Overdosage"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "IV rate limits differ: ERC 2025 — standard 10 mmol/h, maximum 20 mmol/h in HDU/ICU, peri-arrest 20 mmol over 10 min; " +
                "cardiac arrest 20 mmol over 2–3 min then 10 mmol over 2 min (the US labels give no arrest regimen); US KCl labels — up to 10 mmol/h (≤40 mmol/L) " +
                "if K⁺ >2.5, and up to 40 mmol/h with continuous ECG only if K⁺ <2.0 with ECG changes or paralysis.",
            listOf(
                cite(ERC_SPECIAL, "Summary — Hypokalaemia; Fig. 3"),
                cite(FDA_KCL_CONC, "Dosage and administration"),
                cite(FDA_KCL_PREMIX, "Dosage and administration"),
            ),
        ),
        ProtocolNote(
            "Peripheral versus central: ERC 2025 prefers a central line for rapid emergency replacement; US labels prefer central whenever possible and require it for 300–400 mmol/L solutions. " +
                "The US 40 mmol/L, 10 mmol/h limit is not route-specific — follow local policy for peripheral concentrations.",
            listOf(cite(ERC_SPECIAL, "Treatment of hypokalaemia"), cite(FDA_KCL_PREMIX, "Warnings"), cite(FDA_KCL_CONC, "Dosage and administration")),
        ),
        ProtocolNote(
            "Severe hypokalaemia is defined in the ERC 2025 text as <2.5 mmol/L or symptomatic, but as ≤2.4 mmol/L in its algorithm (Fig. 3).",
            listOf(cite(ERC_SPECIAL, "Hypokalaemia; Fig. 3")),
        ),
        ProtocolNote(
            "DKA potassium thresholds are protocol-specific: ICMR STW gives potassium before insulin below 3.3 mmol/L — see the DKA protocol for ADA and JBDS thresholds.",
            listOf(cite(ICMR_DKA, "Management — potassium")),
        ),
    ),
    citations = listOf(
        cite(ERC_SPECIAL), cite(FDA_KCL_CONC), cite(FDA_KCL_PREMIX), cite(FDA_KCL_ORAL), cite(FDA_MG), cite(KARDALAS),
        cite(ICMR_PARALYSIS), cite(ICMR_DKA),
    ),
)

// ================================================================================================ Hyponatraemia
private val hyponatraemia = Protocol(
    id = "hyponatraemia",
    category = ProtocolCategory.Metabolic,
    title = "Hyponatraemia",
    summary = "Acute versus chronic, severe symptoms and 3% saline boluses, daily correction limits and osmotic demyelination risk, " +
        "rescuing overcorrection, and the osmolality-based work-up — ESE/ESICM/ERBP 2014, US expert panel 2013 and ICMR STW.",
    keywords = listOf(
        "hyponatraemia", "hyponatremia", "low sodium", "serum sodium", "Na", "3% saline", "hypertonic saline", "3% NaCl",
        "SIADH", "SIAD", "osmotic demyelination", "ODS", "central pontine myelinolysis", "CPM", "overcorrection",
        "desmopressin", "DDAVP", "tolvaptan", "vaptan", "fluid restriction", "urea", "cerebral salt wasting", "thiazide",
        "water intoxication", "psychogenic polydipsia", "beer potomania", "hyponatraemic seizure", "hyponatraemic encephalopathy",
        "urine osmolality", "urine sodium", "serum osmolality", "pseudohyponatraemia", "corrected sodium", "hypothyroidism",
        "adrenal insufficiency", "cirrhosis", "heart failure",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Confirm hypotonic hyponatraemia and classify",
            timing = "At first contact",
            points = listOf(
                "ESE 2014: Na <135 mmol/L — mild 130–135, moderate 125–129, profound <125. ICMR STW: severe if <125 mmol/L.",
                "ESE 2014: exclude hyperglycaemia — add 2.4 mmol/L to Na for every 100 mg/dL (5.5 mmol/L) glucose above 100 mg/dL.",
                "ESE 2014: measured osmolality <275 mOsm/kg is always hypotonic. Non-hypotonic: mannitol, glycine, contrast; pseudohyponatraemia from lipids, protein, IVIG or paraproteins.",
                "ESE 2014: acute is documented <48 h, chronic ≥48 h; if unclassifiable, treat as chronic unless there is clinical or anamnestic evidence otherwise.",
                "ESE 2014: treatment follows symptom severity (Table 5) and duration. Re-measure by the same method — direct and indirect electrodes can differ by 5–10%.",
            ),
            citations = listOf(
                cite(ESE, "Rec 6.1.1–6.1.3, 6.2.1.1–6.2.1.3; Tables 5, 9–10"),
                cite(ICMR_NA, "Is hyponatremia severe? (<125 mmol/L)"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Severe symptoms: hypertonic saline now",
            timing = "First hour (ESE 2014) — do not wait for the diagnosis",
            points = listOf(
                "ESE 2014: 150 mL 3% saline IV over 20 min; check Na at 20 min while repeating; up to three boluses or until Na rises 5 mmol/L.",
                "ESE 2014: consider weight-based 2 mL/kg instead of 150 mL when body composition is obviously deviant.",
                "US 2013 — acute, or seizures/coma at any duration: 100 mL 3% NaCl IV over 10 min, up to 3 times as needed; urgent goal +4–6 mmol/L.",
                "ICMR STW: acute (<48 h) severe — 3% saline 1–2 mL/kg/h to a total 4–6 mmol/L rise, enough to relieve severe symptoms.",
                "ICMR STW: severe symptoms, chronic or unknown duration — 3% saline 0.5–1 mL/kg/h until symptoms resolve or a safe Na target (≤8–10 mmol/L in the first 24 h). ESE 2014: check Na every 4 h while 3% saline runs.",
                "ESE 2014: manage where close biochemical and clinical monitoring is possible; correcting coexisting hypokalaemia also raises Na.",
            ),
            checklistTitle = "Severe symptoms (ESE 2014 Table 5)",
            checklist = listOf(
                "Vomiting",
                "Cardiorespiratory distress",
                "Abnormal and deep somnolence",
                "Seizures",
                "Coma (GCS ≤8)",
                "ICMR STW also lists obtundation and unstable gait or falls",
            ),
            scoreIds = listOf("gcs"),
            doses = listOf(
                StepDose(
                    label = "3% saline bolus — weight-based alternative (ESE 2014)",
                    dose = WeightDose(2.0, 2.0, unit = "mL", per = "IV over 20 min"),
                    note = "ESE: instead of the fixed 150 mL when body composition is obviously deviant; check Na at 20 min, repeat up to 3 boluses or until +5 mmol/L.",
                ),
                StepDose(
                    label = "3% saline — acute severe hyponatraemia (ICMR STW)",
                    dose = WeightDose(1.0, 2.0, unit = "mL", per = "per hour, IV infusion"),
                    note = "ICMR STW: until a safe Na target, total rise 4–6 mmol/L; the STW states 1 mL/kg/h raises Na by about 1 mmol/L per hour. " +
                        "ICMR gives no Na-check interval for this regimen; ESE 2014: Na every 4 h while 3% runs.",
                ),
                StepDose(
                    label = "3% saline — severe symptoms, chronic or unknown duration (ICMR STW)",
                    dose = WeightDose(0.5, 1.0, unit = "mL", per = "per hour, IV infusion"),
                    note = "ICMR STW: until symptoms resolve or a safe Na target; correction not more than 8–10 mmol/L in the first 24 h. " +
                        "ESE 2014: Na every 4 h while 3% runs (ICMR's 6–8-hourly checks are listed with tolvaptan); at 1 mL/kg/h ≈ 1 mmol/L/h, 8 h can use the whole daily limit.",
                ),
            ),
            citations = listOf(
                cite(ESE, "Rec 7.1.1.1–7.1.1.4, 7.1.3.4; Advice for clinical practice (7.1); Table 5"),
                cite(US_PANEL, "Expert panel recommendation: treatment of symptomatic acute hyponatremia"),
                cite(ICMR_NA, "Severe hyponatremia treatment — acute <48 hours; chronic/unknown (>48 hours)"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "After the first hour, and moderately severe symptoms",
            timing = "Na every 4 h while 3% runs; at 6 and 12 h, then daily (ESE 2014)",
            points = listOf(
                "ESE 2014 — symptoms improve after +5 mmol/L: stop 3% saline, keep the line open with minimal 0.9% saline, start cause-specific treatment.",
                "ESE 2014 — no improvement: continue 3% aiming +1 mmol/L/h; stop when symptoms improve, Na has risen 10 mmol/L in total, or reaches 130.",
                "ESE 2014: if symptoms persist despite the rise, look for another cause of the symptoms.",
                "ESE 2014 — moderately severe symptoms: one 150 mL 3% bolus over 20 min; aim +5 mmol/L per 24 h; check Na at 1, 6 and 12 h.",
                "ESE 2014 — acute fall >10 mmol/L without severe symptoms: one 150 mL 3% bolus over 20 min; recheck Na at 4 h by the same method.",
                "US 2013 (acute) — mild–moderate symptoms with low herniation risk: 3% NaCl 0.5–2 mL/kg/h.",
            ),
            checklistTitle = "Moderately severe symptoms (ESE 2014 Table 5)",
            checklist = listOf("Nausea without vomiting", "Confusion", "Headache"),
            doses = listOf(
                StepDose(
                    label = "3% saline — continuous, mild–moderate symptoms (US 2013)",
                    dose = WeightDose(0.5, 2.0, unit = "mL", per = "per hour, IV infusion"),
                    note = "US panel: acute hyponatraemia with low risk of herniation; if chronicity is uncertain, keep within chronic correction limits.",
                ),
            ),
            citations = listOf(
                cite(ESE, "Rec 7.1.2.1–7.1.2.5, 7.1.3.1–7.1.3.4, 7.2.1.1–7.2.1.9, 7.3.1.5–7.3.1.6; Table 5"),
                cite(US_PANEL, "Expert panel recommendation: treatment of symptomatic acute hyponatremia"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Correction limits and osmotic demyelination risk",
            timing = "Count each 24 h from the first sodium",
            points = listOf(
                "ESE 2014: limit the rise to 10 mmol/L in the first 24 h and 8 mmol/L in every later 24 h, until Na reaches 130 mmol/L.",
                "US 2013 (chronic): minimum 4–8 mmol/L/day (4–6 if high ODS risk); limits — normal risk 10–12 per 24 h and 18 per 48 h; high risk 8 per 24 h.",
                "ICMR STW: not more than 8–10 mmol/L in the first 24 h — rapid correction may cause central pontine myelinolysis.",
                "US 2013: 'six a day makes sense for safety' — with severe symptoms, achieve the day's rise in the first 6 h, then stop.",
                "US 2013: volume depletion, cortisol deficiency, desmopressin or thiazides can autocorrect by water diuresis, raising Na >2 mmol/L/h — check Na every 4–6 h and urine volume.",
                "ESE 2014: a sudden rise in urine output to >100 mL/h (e.g. after volume is restored) signals a too-rapid Na rise — check Na every 2 h until stable.",
                "US 2013: true acute water intoxication need not be rate-limited; if acute versus chronic is uncertain, use chronic limits.",
            ),
            checklistTitle = "High risk of osmotic demyelination (US 2013 Table 3)",
            checklist = listOf(
                "Serum Na ≤105 mmol/L",
                "Hypokalaemia",
                "Alcoholism",
                "Malnutrition",
                "Advanced liver disease",
            ),
            citations = listOf(
                cite(ESE, "Rec 7.1.2.4, 7.2.1.6, 7.4.1.4; Advice for clinical practice (7.4)"),
                cite(
                    US_PANEL,
                    "Current recommendations for rate of correction (narrative, pp. S20–S21); " +
                        "Expert panel recommendations: treatment of symptomatic acute hyponatremia; avoiding ODS; Table 3",
                ),
                cite(ICMR_NA, "Chronic/unknown (>48 hours); footnote on rapid correction"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Overcorrection: stop and re-lower",
            timing = "As soon as a limit is exceeded",
            points = listOf(
                "ESE 2014: Na up >10 mmol/L in the first 24 h or >8 mmol/L in any later 24 h — intervene promptly and stop active treatment.",
                "ESE 2014: after expert consultation, consider 10 mL/kg electrolyte-free water (e.g. glucose solution) over 1 h with strict urine output and fluid balance monitoring.",
                "ESE 2014: after expert consultation, consider desmopressin 2 µg IV, not more often than every 8 h.",
                "US 2013 (starting Na <120): replace water losses or give desmopressin after a 6–8 mmol/L rise in 24 h; withhold the next vaptan dose if >8.",
                "US 2013 re-lowering: desmopressin 2–4 µg parenterally every 8 h plus water orally or 5% dextrose 3 mL/kg/h; recheck Na hourly until at goal.",
                "US 2013: starting Na ≥120 mmol/L — intervention probably unnecessary.",
            ),
            doses = listOf(
                StepDose(
                    label = "Electrolyte-free water — re-lowering (ESE 2014)",
                    dose = WeightDose(10.0, 10.0, unit = "mL", per = "IV over 1 h (e.g. glucose solution)"),
                    note = "ESE: after consulting an expert; strict monitoring of urine output and fluid balance.",
                ),
                StepDose(
                    label = "5% dextrose — re-lowering (US 2013)",
                    dose = WeightDose(3.0, 3.0, unit = "mL", per = "per hour, IV"),
                    note = "US panel: with desmopressin 2–4 µg every 8 h parenterally; recheck Na hourly until reduced to goal.",
                ),
            ),
            citations = listOf(
                cite(ESE, "Rec 7.5.1.1–7.5.1.4"),
                cite(US_PANEL, "Expert panel recommendation: managing excessive correction of chronic hyponatremia; Figure 3"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Work-up: urine osmolality, urine sodium and volume status",
            points = listOf(
                "ESE 2014: interpret spot urine osmolality first; ≤100 mOsm/kg — accept relative excess water intake as the cause.",
                "ESE 2014: urine osmolality >100 — spot urine Na ≤30 mmol/L suggests low effective arterial volume; >30 — assess ECF status and diuretic use.",
                "ICMR STW: hypovolaemic with urine Na >20 — renal loss (diuretics, mineralocorticoid deficiency, cerebral salt wasting); <20 — vomiting, diarrhoea, burns, pancreatitis.",
                "ICMR STW: euvolaemic — SIADH, drugs, hypothyroidism, glucocorticoid deficiency, stress; hypervolaemic — heart failure, cirrhosis, nephrotic (Na <20) or renal failure (>20).",
                "US 2013: evaluate every euvolaemic patient for glucocorticoid deficiency before diagnosing SIADH; blame hypothyroidism only if severe (myxoedema or TSH >50 mIU/mL).",
                "ESE 2014: do not measure vasopressin to confirm SIAD.",
            ),
            checklistTitle = "SIAD essential criteria (ESE 2014 Table 6)",
            checklist = listOf(
                "Effective serum osmolality <275 mOsm/kg",
                "Urine osmolality >100 mOsm/kg at some level of decreased effective osmolality",
                "Clinical euvolaemia",
                "Urine Na >30 mmol/L with normal dietary salt and water intake",
                "No adrenal, thyroid, pituitary or renal insufficiency",
                "No recent diuretic use",
            ),
            citations = listOf(
                cite(ESE, "Rec 6.3.1.1–6.3.1.6; Table 6"),
                cite(ICMR_NA, "Assessment of volume status"),
                cite(US_PANEL, "Expert panel recommendations: glucocorticoid deficiency; hypothyroidism"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "No severe symptoms: cause-specific treatment",
            points = listOf(
                "ESE 2014: stop non-essential fluids and provoking drugs; mild chronic hyponatraemia — do not treat solely to raise Na. Check Na every 6 h if moderate or profound.",
                "ESE 2014 — hypovolaemic: 0.9% saline or balanced crystalloid 0.5–1.0 mL/kg/h; haemodynamic instability overrides correction-rate concerns.",
                "ESE 2014 — SIAD, moderate or profound: fluid restriction first; second line urea 0.25–0.50 g/kg/day or low-dose loop diuretic with oral NaCl.",
                "US 2013: 0.9% saline does not correct SIADH and may worsen it when urine Na + K exceeds serum Na.",
                "ESE 2014 — expanded ECF (heart failure, cirrhosis): fluid restriction; against vasopressin receptor antagonists and demeclocycline.",
                "ICMR STW: non-severe — urgent intervention unnecessary; assess ECF volume and restrict fluid intake to 500–1000 mL/24 h.",
            ),
            doses = listOf(
                StepDose(
                    label = "0.9% saline or balanced crystalloid — hypovolaemic (ESE 2014)",
                    dose = WeightDose(0.5, 1.0, unit = "mL", per = "per hour, IV"),
                    note = "ESE: restore extracellular volume; manage haemodynamic instability where close monitoring is possible.",
                ),
                StepDose(
                    label = "Urea — SIAD second line (ESE 2014)",
                    dose = WeightDose(0.25, 0.5, unit = "g", per = "per day, oral"),
                    note = "ESE: equal second-line option with low-dose loop diuretic plus oral sodium chloride.",
                ),
            ),
            citations = listOf(
                cite(ESE, "Rec 7.4.1.1–7.4.1.6, 7.4.2.1–7.4.2.4, 7.4.3.1–7.4.3.2, 7.4.4.1–7.4.4.3"),
                cite(US_PANEL, "Expert panel recommendations: hyponatremia in patients with SIADH"),
                cite(ICMR_NA, "Urgent intervention unnecessary"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Tolvaptan and other vaptans",
            points = listOf(
                "ESE 2014 — SIAD: vaptans not recommended in moderate and recommended against in profound hyponatraemia; against lithium or demeclocycline.",
                "US 2013: vaptans are an option — exclude hypovolaemia; not with or straight after 3% saline; Na every 6–8 h for 24–48 h; no fluid restriction then.",
                "ICMR STW (chronic severe): tolvaptan 15 mg on day 1, then 30–60 mg/day if Na stays <135 or rises <5 mmol/L per 24 h; Na every 6–8 h.",
                "US label: start and restart only in hospital with close Na monitoring; not for urgent correction of serious neurological symptoms; maximum 60 mg/day.",
                "US label: limit to 30 days (liver injury); avoid in liver disease including cirrhosis; contraindicated in hypovolaemia, anuria, inability to sense thirst, strong CYP3A inhibitors.",
            ),
            citations = listOf(
                cite(ESE, "Rec 7.4.2.3, 7.4.3.3–7.4.3.5"),
                cite(US_PANEL, "Expert panel recommendations: cautions for using vaptans"),
                cite(ICMR_NA, "Chronic/unknown (>48 hours)"),
                cite(FDA_TOLVAPTAN, "Boxed warning; 1 Indications and usage; 2.1; 4 Contraindications; 5.2"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Hypertonic saline and limits differ: ESE 2014 — 150 mL 3% over 20 min (≤3 times), limit 10 mmol/L in the first 24 h then 8 per 24 h; " +
                "US 2013 — 100 mL over 10 min (≤3 times), limits 10–12 per 24 h and 18 per 48 h (8 per 24 h if high risk); ICMR STW — weight-based infusions, ≤8–10 mmol/L in 24 h.",
            listOf(
                cite(ESE, "Rec 7.1.1.1–7.1.1.3, 7.1.2.4"),
                cite(US_PANEL, "Expert panel recommendations: acute hyponatremia; avoiding ODS"),
                cite(ICMR_NA, "Severe hyponatremia treatment"),
            ),
        ),
        ProtocolNote(
            "Vaptans are contested: ESE 2014 advises against them in profound SIAD and expanded ECF; the US 2013 panel lists them as an option; ICMR STW includes tolvaptan for chronic severe " +
                "hyponatraemia. The US 2013 CME activity was supported by a grant from Otsuka, tolvaptan's manufacturer. " +
                "The ICMR STW lists tolvaptan in the same box as the 3% saline infusion for severe symptoms; the US panel says not to use vaptans with, or straight after, " +
                "3% saline, and the US label excludes tolvaptan for urgent correction — do not run both together.",
            listOf(
                cite(ESE, "Rec 7.4.2.3, 7.4.3.4–7.4.3.5"),
                cite(US_PANEL, "Expert panel recommendations: cautions for using vaptans; CME information"),
                cite(ICMR_NA, "Chronic/unknown (>48 hours)"),
                cite(FDA_TOLVAPTAN, "1 Indications and usage (limitations of use)"),
            ),
        ),
        ProtocolNote(
            "Re-lowering differs: ESE 2014 — 10 mL/kg electrolyte-free water over 1 h and desmopressin 2 µg IV (≤8-hourly) after expert advice; US 2013 — desmopressin 2–4 µg every 8 h " +
                "with 5% dextrose 3 mL/kg/h and hourly Na. US 2013 notes re-lowering has not been validated in controlled trials.",
            listOf(cite(ESE, "Rec 7.5.1.3–7.5.1.4"), cite(US_PANEL, "Managing excessive correction of chronic hyponatremia")),
        ),
        ProtocolNote(
            "Bolus versus infusion: in the SALSA RCT (178 patients), rapid intermittent 3% boluses and slow continuous infusion had similar overcorrection, " +
                "defined as a rise >12 mmol/L in 24 h or >18 mmol/L in 48 h — looser than the ESE limits above (17.2% vs 24.2%, not significant); " +
                "boluses needed re-lowering less often (41.4% vs 57.1%).",
            listOf(cite(SALSA, "Abstract — Main outcome and measures; Results")),
        ),
        ProtocolNote(
            "Classification differs: ESE 2014 grades biochemistry (profound <125 mmol/L) separately from symptoms and lets symptoms drive urgency; ICMR STW calls <125 mmol/L 'severe' " +
                "and then asks about severe symptoms and duration.",
            listOf(cite(ESE, "Rec 6.1.1–6.1.3"), cite(ICMR_NA, "Is hyponatremia severe? (<125 mmol/L)")),
        ),
        ProtocolNote(
            "Scope: adults. ESE 2014 and the US 2013 panel remain the current editions found (November 2026).",
            listOf(cite(ESE), cite(US_PANEL)),
        ),
    ),
    citations = listOf(
        cite(ESE), cite(US_PANEL), cite(ICMR_NA), cite(FDA_TOLVAPTAN), cite(SALSA),
    ),
)

internal val electrolyteProtocols: List<Protocol> = listOf(hyperkalaemia, hypokalaemia, hyponatraemia)
