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
 * Sepsis and septic shock, upper GI bleeding, severe dengue and severe malaria in adults (child doses only where a step
 * says so). Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them; every point is attributed
 *   ("SSC 2026: …", "ICMR STW: …", "NCVBDC 2023: …", "NDP 2013: …", "WHO 2026: …", "Baveno VII: …").
 * - Glucose in mg/dL; mmol/L in brackets where the source uses it. Values marked "≈" are arithmetic conversions
 *   (mg/dL = mmol/L × 18), not guideline text.
 * - SSC 2026: recommendation wording, strength and certainty from the SCCM guideline page (full text is paywalled);
 *   locators are the SCCM topic identifiers. Hydrocortisone dose, vasopressin timing and the peripheral-vasopressor
 *   vein are SSC 2021 remarks (open-access full text), attributed as such.
 * - ESGE 2021, ESGE 2026 and ACG 2021 were read from their published abstracts only (status lines say so).
 * - Existing sources cited by id: em-ab-ssc-2026 (AcidBaseDisorders.kt), ssc-2021 and sepsis3-2016 (scores Template.kt),
 *   sc-nice-cg141 and sc-esge-nvugih-2021 (scores AcuteCare.kt), em-neuro-icmr-amr-2019 (NeuroEmergencies.kt).
 * - India: ICMR STW list checked November 2026 — adult STW exists for acute GI bleed (2020); sepsis and dengue STWs are
 *   paediatric only; no malaria STW. The ICMR 2022 antimicrobial guideline is published only as an online portal that
 *   could not be reached, so the 2019 PDF edition is used. The NCDC National Treatment Guidelines 2025 PDF link was dead.
 */

internal val infectionEmergencySources = listOf(
    // ---------------------------------------------------------------- Upper GI bleeding
    Source(
        "em-inf-icmr-stw-gib-a-2020", Authority.ICMR, "Standard Treatment Workflow (ICD-10 K92.2)",
        "Acute Gastrointestinal Bleed in Adults — Part A", "2020",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567564_gastrointestinal_bleed_part_a.pdf",
        status = "July 2020 (Department of Health Research, MoHFW)",
    ),
    Source(
        "em-inf-icmr-stw-gib-b-2020", Authority.ICMR, "Standard Treatment Workflow (ICD-10 K92.2)",
        "Acute Gastrointestinal Bleed in Adults — Part B (interventional management)", "2020",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567564_gastrointestinal_bleed_part_b.pdf",
        status = "July 2020 (Department of Health Research, MoHFW)",
    ),
    Source(
        "em-inf-acg-ugib-2021", Authority.ACG, "ACG Clinical Guideline — Am J Gastroenterol 2021;116(5):899–917",
        "ACG Clinical Guideline: Upper Gastrointestinal and Ulcer Bleeding", "2021",
        "https://doi.org/10.14309/ajg.0000000000001245",
        status = "Erratum Am J Gastroenterol 2021;116(11):2309; reviewed from the published abstract",
    ),
    Source(
        "em-inf-icg-nvugib-2019", Authority.Paper, "International Consensus Group guideline — Ann Intern Med 2019;171(11):805–822",
        "Management of Nonvariceal Upper Gastrointestinal Bleeding: Guideline Recommendations From the International Consensus Group",
        "2019",
        "https://doi.org/10.7326/M19-1795",
    ),
    Source(
        "em-inf-esge-pub-2026", Authority.Paper, "European Society of Gastrointestinal Endoscopy (ESGE) guideline — Endoscopy 2026;58(8):899–924",
        "Endoscopic diagnosis and management of peptic ulcer bleeding: European Society of Gastrointestinal Endoscopy (ESGE) Guideline – Update 2026",
        "2026",
        "https://doi.org/10.1055/a-2863-8314",
        status = "Updates the 2021 NVUGIH guideline (new or revised recommendations only); erratum Endoscopy 2026;58(8):C12; " +
            "reviewed from the published abstract",
    ),
    Source(
        "em-inf-baveno7-2022", Authority.Paper, "Baveno VII consensus — J Hepatol 2022;76(4):959–974",
        "Baveno VII – Renewing consensus in portal hypertension", "2022",
        "https://doi.org/10.1016/j.jhep.2021.12.022",
    ),
    Source(
        "em-inf-acg-cag-antithrombotics-2022", Authority.ACG, "ACG–CAG Clinical Practice Guideline — Am J Gastroenterol 2022;117(4):542–558",
        "American College of Gastroenterology-Canadian Association of Gastroenterology Clinical Practice Guideline: Management of " +
            "Anticoagulants and Antiplatelets During Acute Gastrointestinal Bleeding and the Periendoscopic Period",
        "2022",
        "https://doi.org/10.14309/ajg.0000000000001627",
    ),
    // ---------------------------------------------------------------- Dengue
    Source(
        "em-inf-ncvbdc-dengue-2023", Authority.MoHFW, "National guideline (NCVBDC, Dte.GHS, MoHFW)",
        "National Guidelines for Clinical Management of Dengue Fever", "2023",
        "https://ncvbdc.mohfw.gov.in/Doc/National%20Guidelines%20for%20Clinical%20Management%20of%20Dengue%20Fever%202023.pdf",
        status = "Latest edition on the NCVBDC website (checked November 2026)",
    ),
    Source(
        "em-inf-who-arbo-2025", Authority.WHO, "Guideline (ISBN 978-92-4-011111-0)",
        "WHO guidelines for clinical management of arboviral diseases: dengue, chikungunya, Zika and yellow fever", "2025",
        "https://www.who.int/publications/i/item/9789240111110",
        status = "July 2025; gives no IV fluid volumes — defers to regional and national guidelines (§5.2.1)",
    ),
    // ---------------------------------------------------------------- Malaria
    Source(
        "em-inf-ncvbdc-ndp-malaria-2013", Authority.MoHFW, "National Drug Policy (NVBDCP, Dte.GHS, MoHFW)",
        "National Drug Policy on Malaria (2013)", "2013",
        "https://ncvbdc.mohfw.gov.in/WriteReadData/l892s/National-Drug-Policy-2013.pdf",
        status = "Most recent national drug policy on the NCVBDC website (checked November 2026)",
    ),
    Source(
        "em-inf-nvbdcp-malaria-stg-2013", Authority.MoHFW, "Standard Treatment Guidelines (NVBDCP; Clinical Establishments Act portal)",
        "Diagnosis and Treatment of Malaria", "2013",
        "https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/892.pdf",
    ),
    Source(
        "em-inf-who-malaria-2026", Authority.WHO, "Consolidated guidelines (MAGICapp; doi 10.2471/B09879)",
        "WHO guidelines for malaria", "2026",
        "https://www.who.int/publications/i/item/guidelines-for-malaria",
        status = "Version of 10 September 2026; severe malaria recommendations dated 2015, unchanged in this version",
    ),
)

// Defined in other files
private const val SSC26 = "em-ab-ssc-2026"
private const val SSC21 = "ssc-2021"
private const val SEP3 = "sepsis3-2016"
private const val ICMR_AMR = "em-neuro-icmr-amr-2019"
private const val NICE_GIB = "sc-nice-cg141"
private const val ESGE21 = "sc-esge-nvugih-2021"

// Defined here
private const val STW_GIB_A = "em-inf-icmr-stw-gib-a-2020"
private const val STW_GIB_B = "em-inf-icmr-stw-gib-b-2020"
private const val ACG21 = "em-inf-acg-ugib-2021"
private const val ICG19 = "em-inf-icg-nvugib-2019"
private const val ESGE26 = "em-inf-esge-pub-2026"
private const val BAVENO = "em-inf-baveno7-2022"
private const val ACGCAG = "em-inf-acg-cag-antithrombotics-2022"
private const val DENGUE_IN = "em-inf-ncvbdc-dengue-2023"
private const val WHO_ARBO = "em-inf-who-arbo-2025"
private const val NDP13 = "em-inf-ncvbdc-ndp-malaria-2013"
private const val MAL_STG = "em-inf-nvbdcp-malaria-stg-2013"
private const val WHO_MAL = "em-inf-who-malaria-2026"

internal val infectionEmergencyProtocols = listOf(
    // =========================================================================================================
    // Sepsis and septic shock
    // =========================================================================================================
    Protocol(
        id = "sepsis",
        category = ProtocolCategory.Infection,
        title = "Sepsis and septic shock",
        summary = "Screening, cultures and lactate, antibiotic timing, empirical choices in India, fluids, MAP and vasopressors, " +
            "steroids and source control — attributed to SSC 2026, ICMR and Sepsis-3.",
        keywords = listOf(
            "sepsis", "septic shock", "SIRS", "qSOFA", "SOFA", "Sepsis-3", "severe sepsis", "septicaemia", "septicemia",
            "bacteraemia", "golden hour", "hour-1 bundle", "sepsis bundle", "NEWS2", "lactate", "blood culture",
            "noradrenaline", "norepinephrine", "vasopressor", "vasopressin", "hydrocortisone", "meropenem", "imipenem",
            "source control", "MODS", "fever with hypotension", "SSC",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise and screen",
                timing = "At triage — treatment and resuscitation begin immediately (SSC 2026)",
                points = listOf(
                    "SSC 2026 (strong): in hospital, screen with NEWS, NEWS2, MEWS or SIRS rather than qSOFA as a single tool.",
                    "SSC 2026 (good practice): sepsis is a clinical diagnosis — do not rule it in or out with a single biomarker or test.",
                    "ICMR AMR 2019 (Sepsis-3): sepsis is life-threatening organ dysfunction from infection — an acute SOFA rise of ≥2 points.",
                    "ICMR AMR 2019: qSOFA ≥2 of RR ≥22/min, altered mentation, SBP ≤100 mm Hg flags infected adults at risk of poor outcome.",
                    "ICMR AMR 2019 (Sepsis-3): septic shock — vasopressor needed for MAP ≥65 mm Hg and lactate >2 mmol/L (>18 mg/dL) without hypovolaemia.",
                    "ICMR AMR 2019: unexplained acute organ dysfunction should raise the possibility of an underlying infection.",
                ),
                scoreIds = listOf("news2", "qsofa"),
                citations = listOf(
                    cite(SSC26, "Recommendations: Screening; Biomarkers; Fluid resuscitation (good practice)"),
                    cite(ICMR_AMR, "Ch. 3 §3.2–3.3"),
                    cite(SEP3, "Definitions of sepsis and septic shock; qSOFA"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Cultures and lactate",
                timing = "Blood cultures before antimicrobials, without delaying them",
                points = listOf(
                    "SSC 2026 (strong): possible, probable or definite sepsis — blood cultures as soon as possible, ideally before antimicrobials.",
                    "ICMR AMR 2019: two or more blood culture sets, plus cultures from all potential sites — respiratory secretions, urine, CSF, wounds, fluids.",
                    "ICMR AMR 2019: if cultures cannot be obtained promptly (45 minutes), give the antimicrobials.",
                    "SSC 2026 (conditional): measure blood lactate. ICMR AMR 2019 (hour-1 bundle): re-measure if the first lactate is >2 mmol/L.",
                    "SSC 2026 (conditional): raised lactate or shock — serial lactate guides resuscitation; do not continue fluids until lactate normalises.",
                    "SSC 2026 (good practice): rapidly evaluate for an anatomical source that needs emergent source control.",
                ),
                citations = listOf(
                    cite(SSC26, "Recommendations: Blood culture; Blood lactate; Serial lactate; Source control (good practice)"),
                    cite(ICMR_AMR, "Ch. 3 §3.4; Box 1 and footnotes"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "When to give antimicrobials",
                timing = "Shock or probable sepsis: immediately, ideally ≤1 h; possible sepsis: ≤3 h (SSC 2026)",
                points = listOf(
                    "SSC 2026 (strong): possible, probable or definite septic shock — antimicrobials immediately, ideally within 1 h of recognition.",
                    "SSC 2026 (strong): probable or definite sepsis without shock — antimicrobials immediately, ideally within 1 h.",
                    "SSC 2026 (conditional): possible sepsis without shock — time-limited rapid investigation; if concern persists, antimicrobials within 3 h of first suspicion.",
                    "SSC 2026 (conditional): low likelihood of infection and no shock — defer antimicrobials and keep monitoring closely.",
                    "SSC 2026 (conditional): decide to start on clinical evaluation alone, not procalcitonin. ICMR AMR 2019: IV antimicrobials as soon as possible.",
                    "SSC 2026 (conditional): probable sepsis with hypotension and >60 min to hospital evaluation — antimicrobials in the ambulance, if screening is in place.",
                ),
                citations = listOf(
                    cite(SSC26, "Recommendations: Antibiotic initiation"),
                    cite(ICMR_AMR, "Ch. 3 §3.6"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Empirical antimicrobials",
                points = listOf(
                    "ICMR AMR 2019 (source unclear; rule out common tropical infections, Ch. 1): imipenem–cilastatin 500 mg IV 6-hourly or 1 g 8-hourly, ± amikacin.",
                    "ICMR AMR 2019 alternatives: meropenem 1 g IV 8-hourly or cefoperazone–sulbactam 3 g IV 12-hourly, ± amikacin.",
                    "ICMR AMR 2019: with risk factors add vancomycin or teicoplanin (MRSA), colistin or polymyxin B (CR-GNB), or an echinocandin; ± doxycycline 100 mg IV 12-hourly.",
                    "ICMR AMR 2019: septic shock — at least two antibiotics of different classes; avoid piperacillin–tazobactam until cephalosporin-resistant bacteraemia is excluded (MERINO).",
                    "SSC 2026 (conditional): MDR cover only with high risk (colonisation, prior MDR infection, prolonged broad-spectrum antibiotics or hospitalisation); no routine empirical antifungal.",
                    "SSC 2026 (strong): prolonged beta-lactam infusion after a loading dose; de-escalate once pathogen and susceptibility are known.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Amikacin (ICMR AMR 2019 Table 3.2)",
                        dose = WeightDose(15.0, 15.0, unit = "mg", per = "IV once every 24 h"),
                        note = "Added ± to the carbapenem or cefoperazone–sulbactam regimen. No maximum stated.",
                    ),
                    StepDose(
                        label = "Vancomycin — if MRSA risk factors (ICMR AMR 2019 Table 3.2)",
                        dose = WeightDose(15.0, 15.0, unit = "mg", per = "IV every 8–12 h"),
                        note = "ICMR AMR 2019 alternative: teicoplanin 400 mg IV 12-hourly for 3 doses, then 400 mg every 24 h. No maximum stated.",
                    ),
                ),
                citations = listOf(
                    cite(ICMR_AMR, "Ch. 3 Table 3.1 (preferred, alternative, remarks); Table 3.2"),
                    cite(SSC26, "Recommendations: Multidrug resistance; Antifungal coverage; Anaerobic coverage; Beta-lactam antibiotics; Antimicrobial therapy"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Initial fluids",
                timing = "First 3 h (SSC 2026)",
                points = listOf(
                    "SSC 2026 (conditional, low certainty): sepsis-induced hypoperfusion or septic shock — at least 30 mL/kg IV crystalloid in the first 3 h, individualised.",
                    "SSC 2026: use actual body weight, or adjusted or ideal weight if BMI >30 kg/m²; reassess often to avoid under- or over-resuscitation.",
                    "SSC 2026: crystalloids first-line (strong); balanced crystalloids over 0.9% saline (conditional), but 0.9% saline with traumatic brain injury.",
                    "SSC 2026: no starches (strong) or gelatin; crystalloids alone over added albumin — albumin may suit large crystalloid volumes or cirrhosis; avoid it in traumatic brain injury.",
                    "SSC 2026: hypotension — crystalloid bolus first, vasopressors if it persists; unstable shock may need vasopressors with fluids from the start.",
                    "SSC 2026: after 30 mL/kg, liberal or restrictive fluids by patient and system factors; guide with dynamic measures and capillary refill.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Initial crystalloid — sepsis-induced hypoperfusion or septic shock (SSC 2026)",
                        dose = WeightDose(30.0, 30.0, unit = "mL", per = "IV crystalloid (at least) within the first 3 h"),
                        note = "SSC 2026: at least 30 mL/kg, individualised; actual body weight, or adjusted/ideal weight if BMI >30 kg/m². " +
                            "ICMR AMR 2019 hour-1 bundle: 30 mL/kg for hypotension or lactate ≥4 mmol/L.",
                    ),
                ),
                citations = listOf(
                    cite(SSC26, "Recommendations: Fluid resuscitation; Fluid type; Resuscitation; Capillary refill"),
                    cite(ICMR_AMR, "Ch. 3 Box 1"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "MAP target and vasopressors",
                timing = "Do not wait for central access to start vasopressors (SSC 2026)",
                points = listOf(
                    "SSC 2026 (strong): initial MAP target 65 mm Hg, kept within about ±5 mm Hg. Age ≥65 years: 60–65 mm Hg suggested.",
                    "SSC 2026: norepinephrine first-line over dopamine, epinephrine or selepressin (strong), and over vasopressin or angiotensin II (conditional). ICMR AMR 2019: norepinephrine first choice.",
                    "SSC 2026 (conditional): start vasopressors peripherally rather than delay for central access; data insufficient on duration, dose or line.",
                    "SSC 2021 remark: peripheral vasopressors only for a short period, in a vein in or proximal to the antecubital fossa.",
                    "SSC 2026 (conditional): add vasopressin on escalating norepinephrine. SSC 2021 remark: usually started at norepinephrine 0.25–0.5 µg/kg/min.",
                    "SSC 2026: MAP inadequate on both — add epinephrine; without vasopressin, epinephrine can be added to norepinephrine. Terlipressin suggested against.",
                ),
                citations = listOf(
                    cite(SSC26, "Recommendations: Mean arterial pressure; Vasopressor administration; Vasopressors"),
                    cite(SSC21, "Rec 38 remark; Rec 44 remark"),
                    cite(ICMR_AMR, "Ch. 3 Box 1 footnote"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Corticosteroids",
                points = listOf(
                    "SSC 2026 (conditional, low certainty — moderate in 2021): IV corticosteroids suggested in septic shock.",
                    "SSC 2021 remark: IV hydrocortisone 200 mg/day, as 50 mg 6-hourly or a continuous infusion.",
                    "SSC 2021 remark: start at norepinephrine or epinephrine ≥0.25 µg/kg/min at least 4 h after vasopressors began.",
                    "ICMR AMR 2019: IV hydrocortisone can be used if hypotension persists after adequate fluids and vasopressors.",
                    "SSC 2026: IV immunoglobulin, vitamin C, vitamin D and blood purification, including polymyxin B haemoperfusion, suggested against.",
                ),
                citations = listOf(
                    cite(SSC26, "Recommendations: IV Corticosteroids; IV Immunoglobulin; IV Vitamin C; Vitamin D; Blood purification"),
                    cite(SSC21, "Rec 58 and remark"),
                    cite(ICMR_AMR, "Ch. 3 §3.5"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Source control, admission and duration",
                timing = "Source control ideally ≤6 h; ICU admission ≤6 h (SSC 2026)",
                points = listOf(
                    "SSC 2026 (conditional): early source control, ideally within 6 h of diagnosing sepsis that needs it.",
                    "SSC 2026 (conditional): if ICU care is needed, admit within 6 h.",
                    "SSC 2026 (good practice): stop empirical antimicrobials if an alternative cause is shown or strongly suspected.",
                    "ICMR AMR 2019: consider de-escalation daily; 7–10 days suffices for most — longer with slow response, undrainable foci, S. aureus bacteraemia.",
                    "SSC 2026 (conditional): shorter courses with adequate source control; procalcitonin plus clinical review to decide stopping when duration is unclear.",
                ),
                citations = listOf(
                    cite(SSC26, "Recommendations: Source control; ICU admission; Antimicrobial therapy"),
                    cite(ICMR_AMR, "Ch. 3 Table 3.1 remarks and footnotes"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Ongoing care",
                points = listOf(
                    "SSC 2026 (strong): start insulin at glucose ≥180 mg/dL (10 mmol/L). SSC 2021 remark: typical target 144–180 mg/dL (8–10 mmol/L) once insulin is started.",
                    "SSC 2026 (strong): restrictive red-cell transfusion; pharmacological VTE prophylaxis unless contraindicated, LMWH over unfractionated heparin.",
                    "SSC 2026 (conditional): bicarbonate for pH ≤7.2 with AKI (AKIN 2–3); not to improve haemodynamics in lactic acidaemia.",
                    "SSC 2026 (conditional): after the acute resuscitation phase, active fluid removal — diuretics, then ultrafiltration if needed.",
                    "SSC 2026 (conditional): PPI stress-ulcer prophylaxis if GI bleeding risk factors; antipyretics not to improve outcomes.",
                    "SSC 2026 (conditional): address goals of care early, within 72 h.",
                ),
                citations = listOf(
                    cite(SSC26, "Recommendations: Insulin therapy; Blood transfusion; VTE prophylaxis; Sodium bicarbonate; Active fluid removal; Stress ulcer prophylaxis; Antipyretics; Goals of care"),
                    cite(SSC21, "Rec 69 remark"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Screening differs: ICMR AMR 2019 presents qSOFA as a screening tool for out-of-hospital, ED and ward use; SSC 2026 " +
                    "recommends NEWS, NEWS2, MEWS or SIRS over qSOFA as a single screening tool.",
                listOf(cite(ICMR_AMR, "Ch. 3 §3.3"), cite(SSC26, "Recommendations: Screening")),
            ),
            ProtocolNote(
                "Empirical breadth differs: ICMR AMR 2019 starts with a carbapenem and requires two classes in septic shock; SSC 2026 " +
                    "suggests MDR cover only for patients at high risk of a specific MDR pathogen.",
                listOf(cite(ICMR_AMR, "Ch. 3 Table 3.1"), cite(SSC26, "Recommendations: Multidrug resistance")),
            ),
            ProtocolNote(
                "ICMR AMR 2019 reproduces the SSC hour-1 bundle (30 mL/kg for hypotension or lactate ≥4 mmol/L). SSC 2026 suggests at " +
                    "least 30 mL/kg within 3 h for hypoperfusion or shock — a conditional, low-certainty recommendation.",
                listOf(cite(ICMR_AMR, "Ch. 3 Box 1"), cite(SSC26, "Recommendations: Fluid resuscitation")),
            ),
            ProtocolNote(
                "Fluid strategy differs by cause: in severe malaria WHO 2026 contraindicates rapid bolus colloid or crystalloid, and " +
                    "NCVBDC 2023 gives its own dengue shock regimens — see the severe malaria and severe dengue protocols.",
                listOf(cite(WHO_MAL, "§5.2.2 — Additional aspects of management: fluid therapy"), cite(DENGUE_IN, "§7.3.2")),
            ),
            ProtocolNote(
                "Steroid evidence is contested: SSC 2026 keeps the conditional suggestion but lowered certainty from moderate (2021) " +
                    "to low. The 2026 full text (dose remarks) was not reviewed; the hydrocortisone dose shown is the SSC 2021 remark.",
                listOf(cite(SSC26, "Recommendations: IV Corticosteroids"), cite(SSC21, "Rec 58 and remark")),
            ),
            ProtocolNote(
                "Scope: SSC 2026 replaces the 2021 adult guideline. ICMR lists a 2022 edition of its antimicrobial guideline (online " +
                    "only, not retrievable); this protocol uses the 2019 PDF edition.",
                listOf(cite(SSC26), cite(ICMR_AMR)),
            ),
        ),
        citations = listOf(cite(SSC26), cite(SSC21), cite(SEP3), cite(ICMR_AMR)),
    ),

    // =========================================================================================================
    // Upper GI bleeding
    // =========================================================================================================
    Protocol(
        id = "upper-gi-bleed",
        category = ProtocolCategory.Infection,
        title = "Upper GI bleeding",
        summary = "Risk scores, resuscitation and transfusion thresholds, PPI, endoscopy timing, variceal bleeding (vasoactive drugs, " +
            "antibiotics, erythromycin) and antithrombotics — ICMR STW, NICE, ESGE, ACG, International Consensus and Baveno VII.",
        keywords = listOf(
            "upper GI bleed", "UGIB", "GI bleed", "haematemesis", "hematemesis", "melaena", "melena", "blood vomiting",
            "coffee-ground vomit", "variceal bleed", "oesophageal varices", "esophageal varices", "EVL", "banding",
            "peptic ulcer bleed", "NVUGIB", "terlipressin", "octreotide", "somatostatin", "pantoprazole", "PPI",
            "Glasgow-Blatchford", "GBS", "Child-Pugh", "cirrhosis", "portal hypertension", "TIPS", "erythromycin", "Forrest",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise and identify high risk",
                timing = "At first contact",
                points = listOf(
                    "ICMR STW: acute GI bleed — haematemesis, melaena, haematochezia or blood in the nasogastric aspirate.",
                    "ICMR STW: alcohol, jaundice, ascites or splenomegaly point to a variceal bleed; epigastric pain, NSAIDs or antiplatelets to ulcer.",
                    "ICMR STW: Hb, platelets, TLC, INR, grouping and cross-match; desirable — LFT, urea, creatinine, HBsAg, anti-HCV, abdominal ultrasound.",
                    "ICMR STW: refer all high-risk patients after initial resuscitation; ICU care may be needed.",
                ),
                checklistTitle = "ICMR STW: high risk if any",
                checklist = listOf(
                    "Pulse >100/min",
                    "Systolic BP <90 mm Hg",
                    "History of syncope",
                    "SpO₂ <90%",
                    "Altered sensorium",
                    "Age >60 years and/or significant comorbidity",
                ),
                citations = listOf(
                    cite(STW_GIB_A, "Diagnose; Assess for high risk; Clinical evaluation; Investigations; Management"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Risk stratification",
                points = listOf(
                    "ESGE 2021 (strong): Glasgow-Blatchford score before endoscopy; GBS ≤1 — very low risk, can be managed as an outpatient.",
                    "ACG 2021 and International Consensus 2019: GBS 0–1 identifies very-low-risk patients who may not need admission or inpatient endoscopy.",
                    "NICE CG141: Blatchford at first assessment, full Rockall after endoscopy; consider early discharge if pre-endoscopy Blatchford is 0.",
                    "International Consensus 2019: suggests against AIMS65 to identify very-low-risk patients.",
                    "Baveno VII: in variceal bleeding, Child-Pugh C, MELD and failure of primary haemostasis best predict 6-week mortality.",
                ),
                scoreIds = listOf("glasgow-blatchford", "child-pugh"),
                citations = listOf(
                    cite(ESGE21, "Main recommendation 1 (abstract)"),
                    cite(ACG21, "Abstract"),
                    cite(ICG19, "Statements A2a–A2b"),
                    cite(NICE_GIB, "Rec 1.1.1–1.1.2"),
                    cite(BAVENO, "Statements 6.15–6.16"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Resuscitation and transfusion",
                timing = "Immediately",
                points = listOf(
                    "ICMR STW: at least one IV cannula of 18 G or larger bore, crystalloid (Ringer's lactate or saline), oxygen 2 L/min if high risk or shocked; target pulse <100, SBP >90, SpO₂ >90%.",
                    "ICMR STW: packed RBC or whole blood if Hb <7 g/dL (<9 g/dL with pre-existing heart disease). ACG 2021: transfuse at 7 g/dL.",
                    "International Consensus 2019: transfuse at Hb <8 g/dL without cardiovascular disease, higher with it. Baveno VII (variceal): target Hb 7–8 g/dL.",
                    "NICE CG141: base transfusion on the full clinical picture; massive bleeding — blood, platelets and clotting factors per local protocol.",
                    "NICE CG141: actively bleeding — platelets if <50 × 10⁹/L; FFP if PT/INR or aPTT >1.5× normal. Baveno VII: no FFP in variceal bleeding.",
                    "Baveno VII: intubate before endoscopy if consciousness is altered or the patient is actively vomiting blood.",
                ),
                citations = listOf(
                    cite(STW_GIB_A, "Resuscitate; Targets; Management — blood transfusion"),
                    cite(ACG21, "Abstract"),
                    cite(ICG19, "Statements A1, A4–A5"),
                    cite(BAVENO, "Statements 6.2–6.3, 6.36"),
                    cite(NICE_GIB, "Rec 1.2.1–1.2.4"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Acid suppression",
                points = listOf(
                    "ICMR STW: all patients — pantoprazole or esomeprazole 80 mg IV stat, then 40 mg 12-hourly (oral if no IV); stop once a variceal bleed is documented.",
                    "ESGE 2026: consider high-dose IV PPI before endoscopy without delaying it. International Consensus 2019: may be considered to downstage lesions.",
                    "NICE CG141: no PPI or H2 blocker before endoscopy for suspected non-variceal bleeding; offer PPI if stigmata of recent haemorrhage.",
                    "ESGE 2021 (strong): after endoscopic haemostasis or an untreated adherent clot — high-dose PPI, e.g. 80 mg IV then 8 mg/h for 72 h.",
                    "ACG 2021: high-dose PPI for 3 days after haemostasis, then oral twice daily for 2 weeks. ICMR STW: 40 mg twice daily for 4 weeks.",
                    "Baveno VII: in variceal bleeding, stop a PPI started before endoscopy immediately afterwards unless strictly indicated.",
                ),
                citations = listOf(
                    cite(STW_GIB_A, "Pharmacotherapy — PPIs"),
                    cite(STW_GIB_B, "Gastric or duodenal ulcer — further management; treatment at discharge"),
                    cite(ESGE26, "Recommendation 3 (abstract)"),
                    cite(ICG19, "Statements A9, C3–C4"),
                    cite(NICE_GIB, "Rec 1.4.3–1.4.4"),
                    cite(ESGE21, "Main recommendation 9 (abstract)"),
                    cite(ACG21, "Abstract"),
                    cite(BAVENO, "Statement 6.12"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Endoscopy timing and erythromycin",
                timing = "Suspected variceal: ≤12 h (Baveno VII); others: ≤24 h",
                points = listOf(
                    "ICMR STW: endoscopy within 24 h of initial stabilisation for all acute GI bleeds; earlier if active ongoing bleeding.",
                    "ESGE 2021 (strong) and ACG 2021: within 24 h. ESGE 2026: not emergent (≤6 h) or urgent (≤12 h) unless unstable despite resuscitation.",
                    "NICE CG141: unstable severe bleeding — endoscopy immediately after resuscitation; everyone else within 24 h of admission.",
                    "Baveno VII: suspected variceal bleed — after haemodynamic resuscitation, endoscopy within 12 h of presentation; as soon as safely possible if unstable.",
                    "Baveno VII: erythromycin 250 mg IV 30–120 min before endoscopy unless QT prolongation. ACG 2021: erythromycin infusion suggested.",
                    "ESGE 2026: if IV erythromycin is unavailable, IV metoclopramide in selected severe or ongoing bleeding. International Consensus 2019: do not delay endoscopy for anticoagulants.",
                ),
                citations = listOf(
                    cite(STW_GIB_A, "Endoscopy within 24 hours"),
                    cite(ESGE21, "Main recommendations 3–4 (abstract)"),
                    cite(ESGE26, "Recommendations 2, 4 (abstract)"),
                    cite(ACG21, "Abstract"),
                    cite(NICE_GIB, "Rec 1.3.1–1.3.2"),
                    cite(BAVENO, "Statements 6.17, 6.19"),
                    cite(ICG19, "Statement A6"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Suspected variceal bleed: drugs and antibiotics",
                timing = "Vasoactive drug and antibiotic at presentation",
                points = listOf(
                    "Baveno VII: start terlipressin, somatostatin or octreotide as soon as possible and continue 2–5 days. NICE CG141: terlipressin at presentation.",
                    "ICMR STW: terlipressin 2 mg IV stat, then 1 mg 6-hourly for 3–5 days; avoid if heart or peripheral vascular disease is suspected.",
                    "ICMR STW alternatives: somatostatin 250 µg IV stat then 250 µg/h, or octreotide 50 µg IV stat then 50 µg/h, for 3–5 days.",
                    "Baveno VII: monitor sodium on terlipressin (hyponatraemia). ICMR STW: examine regularly for peripheral or cardiac ischaemia.",
                    "Baveno VII: antibiotics from admission; ceftriaxone 1 g/24 h in advanced cirrhosis. ICMR STW: ceftriaxone 1 g IV 12-hourly or cefotaxime 1 g 8-hourly, 3–5 days.",
                    "Baveno VII: no tranexamic acid or recombinant factor VIIa; lactulose (oral or enema) to clear blood and prevent encephalopathy.",
                ),
                checklistTitle = "ICMR STW: continue the vasoactive drug for 5 days if",
                checklist = listOf(
                    "Active bleeding at endoscopy",
                    "Child-Pugh class C",
                    "More than 4 packed red cell transfusions",
                ),
                scoreIds = listOf("child-pugh"),
                citations = listOf(
                    cite(BAVENO, "Statements 6.5–6.9, 6.33, 6.38"),
                    cite(STW_GIB_A, "Pharmacotherapy — vasoconstrictors; antibiotics"),
                    cite(STW_GIB_B, "Portal hypertension — further management"),
                    cite(NICE_GIB, "Rec 1.5.1–1.5.2"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Endoscopic therapy and rebleeding",
                points = listOf(
                    "Baveno VII, NICE and ICMR STW: band ligation for oesophageal varices (ICMR: sclerotherapy if not feasible); cyanoacrylate for gastric varices.",
                    "Baveno VII: pre-emptive covered TIPS within 72 h (ideally <24 h) for Child-Pugh C <14, or B >7 with active bleeding, or HVPG >20 mm Hg.",
                    "Baveno VII: refractory variceal bleeding — balloon tamponade or metal stent as a bridge to TIPS. ICMR STW: covered metal stent or TIPS.",
                    "ESGE 2021 (strong): actively bleeding ulcer — adrenaline injection plus a second modality. ICMR STW: 1:10,000 adrenaline plus clips or thermal device.",
                    "ESGE 2026: OTS clip monotherapy is an alternative first line for FIa/FIb ulcers; recurrent bleeding — OTS clip, then embolisation, then surgery.",
                    "ICMR STW: relook endoscopy at 24 h if the first showed active bleeding with inadequate haemostasis; rebleeding — repeat endotherapy, then refer for surgery.",
                ),
                scoreIds = listOf("child-pugh"),
                citations = listOf(
                    cite(BAVENO, "Statements 6.21–6.22, 6.27, 6.29"),
                    cite(NICE_GIB, "Rec 1.4.1–1.4.2, 1.4.5–1.4.7, 1.5.3–1.5.6"),
                    cite(STW_GIB_B, "Interventional management; rebleeding; failure to control bleeding"),
                    cite(ESGE21, "Main recommendation 5 (abstract)"),
                    cite(ESGE26, "Recommendations 7, 13, 16 (abstract)"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Anticoagulants and antiplatelets",
                points = listOf(
                    "ICMR STW: stop antiplatelets and anticoagulants at presentation; after recent MI or a stent, consult a cardiologist.",
                    "ICMR STW: once bleeding is controlled, resume low-dose aspirin on day 1 and other antiplatelets or anticoagulants by day 3–5.",
                    "ESGE 2021 (strong): do not interrupt low-dose aspirin for secondary prevention; if stopped, restart within 3–5 days. NICE: continue once haemostasis is achieved.",
                    "ACG–CAG 2022: do not hold cardiac aspirin; if held, resume the day haemostasis is confirmed. No platelet transfusion for antiplatelet users.",
                    "ACG–CAG 2022: warfarin — against FFP and vitamin K; PCC over FFP if needed. NICE CG141: PCC if on warfarin and actively bleeding.",
                    "ACG–CAG 2022 (conditional, very low certainty): DOACs — no routine PCC, idarucizumab or andexanet; selective use may be considered for life-threatening bleeding with a dose in the past 24 h. ESGE 2026: resume anticoagulation as soon as indicated by thromboembolic risk.",
                ),
                citations = listOf(
                    cite(STW_GIB_A, "Resuscitate — stop antiplatelets and anticoagulants"),
                    cite(STW_GIB_B, "Restarting of antiplatelets/anticoagulants"),
                    cite(ESGE21, "Main recommendations 2, 10 (abstract)"),
                    cite(ESGE26, "Recommendation 17 (abstract)"),
                    cite(ACGCAG, "Recommendations 1–10 (acute GI bleeding)"),
                    cite(NICE_GIB, "Rec 1.2.5, 1.6.1, 1.6.3"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Discharge and secondary prevention",
                points = listOf(
                    "ICMR STW discharge: haemodynamically stable, heart rate <90/min, conscious, no bleeding for ≥72 h (no further fall in Hb).",
                    "ICMR STW varices: propranolol 20 mg 12-hourly, increased to a heart rate of 60/min unless contraindicated; repeat ligation at 2 weeks, then 2–3-weekly until eradicated.",
                    "ICMR STW ulcer: PPI 40 mg twice daily for 4 weeks; reconsider NSAIDs; repeat endoscopy and H. pylori testing at 4–6 weeks.",
                    "NICE CG141: stop other NSAIDs, including COX-2 inhibitors, during the acute phase.",
                    "International Consensus 2019: previous ulcer bleeding with continuing antiplatelets or anticoagulants — PPI suggested.",
                    "ICMR STW: in elderly patients with GI bleeding, ensure malignancy has been ruled out.",
                ),
                citations = listOf(
                    cite(STW_GIB_B, "Treatment at discharge; Discharge criteria"),
                    cite(NICE_GIB, "Rec 1.6.2"),
                    cite(ICG19, "Statements E4–E5"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Pre-endoscopy PPI differs: ICMR STW gives IV PPI to all; ESGE 2026 suggests considering it; International Consensus " +
                    "2019 says it may be considered; NICE CG141 says do not offer it for suspected non-variceal bleeding.",
                listOf(cite(STW_GIB_A, "Pharmacotherapy"), cite(ESGE26, "Recommendation 3 (abstract)"), cite(ICG19, "Statement A9"), cite(NICE_GIB, "Rec 1.4.3")),
            ),
            ProtocolNote(
                "Aspirin differs: ICMR STW stops antiplatelets at presentation and resumes aspirin on day 1 after control; ESGE 2021, " +
                    "ACG–CAG 2022 and NICE advise not interrupting low-dose aspirin for secondary prevention.",
                listOf(cite(STW_GIB_A, "Resuscitate"), cite(STW_GIB_B, "Restarting"), cite(ESGE21, "Main recommendation 2 (abstract)"), cite(ACGCAG, "Recommendations 9–10"), cite(NICE_GIB, "Rec 1.6.1")),
            ),
            ProtocolNote(
                "Transfusion thresholds differ: ICMR STW Hb <7 g/dL (<9 g/dL with heart disease); ACG 2021 7 g/dL; International " +
                    "Consensus 2019 <8 g/dL without cardiovascular disease; Baveno VII target 7–8 g/dL in variceal bleeding.",
                listOf(cite(STW_GIB_A, "Management"), cite(ACG21, "Abstract"), cite(ICG19, "Statements A4–A5"), cite(BAVENO, "Statement 6.2")),
            ),
            ProtocolNote(
                "Coagulopathy: NICE CG141 offers FFP and platelets in active bleeding by laboratory thresholds and ICMR STW gives platelets " +
                    "below 50,000/mm³ in variceal bleeding; Baveno VII advises against FFP and says platelet count does not predict control.",
                listOf(cite(NICE_GIB, "Rec 1.2.4"), cite(STW_GIB_B, "Recommendations for transfusion"), cite(BAVENO, "Statements 6.34–6.37")),
            ),
            ProtocolNote(
                "Variceal regimens differ: ICMR STW gives ceftriaxone 1 g 12-hourly and terlipressin 1 mg 6-hourly after 2 mg; Baveno VII " +
                    "gives ceftriaxone 1 g/24 h and no terlipressin dose. NICE: stop terlipressin after haemostasis or 5 days.",
                listOf(cite(STW_GIB_A, "Pharmacotherapy"), cite(BAVENO, "Statements 6.5, 6.9"), cite(NICE_GIB, "Rec 1.5.1")),
            ),
            ProtocolNote(
                "Nasogastric tube: ICMR STW includes NG tube placement and lavage; Baveno VII advises caution with airway manipulation, " +
                    "including NG tubes, in variceal bleeding because of pulmonary infection risk.",
                listOf(cite(STW_GIB_A, "Resuscitate"), cite(BAVENO, "Statement 6.11")),
            ),
            ProtocolNote(
                "Prokinetics: ACG 2021 and Baveno VII support erythromycin before endoscopy and ESGE 2026 metoclopramide if it is " +
                    "unavailable; International Consensus 2019 says promotility agents should not be used routinely.",
                listOf(cite(ACG21, "Abstract"), cite(BAVENO, "Statement 6.19"), cite(ESGE26, "Recommendation 2 (abstract)"), cite(ICG19, "Statement A7")),
            ),
            ProtocolNote(
                "DOAC reversal: ACG–CAG 2022 suggests against routine andexanet alfa but says it could be considered in life-threatening " +
                    "GI bleeding within 24 h of apixaban or rivaroxaban; NICE CG141 cites TA697 (2025), which recommends andexanet as an " +
                    "option for life-threatening or uncontrolled GI bleeding on apixaban or rivaroxaban.",
                listOf(cite(ACGCAG, "Recommendation 6 and discussion"), cite(NICE_GIB, "Rec 1.2.8")),
            ),
            ProtocolNote(
                "Very early endoscopy in high-risk patients is unresolved: International Consensus 2019 could not recommend for or " +
                    "against ≤12 h; ESGE 2026 advises against ≤12 h unless unstable despite resuscitation. Blatchford discharge: NICE uses 0, others ≤1.",
                listOf(cite(ICG19, "No Recommendation B"), cite(ESGE26, "Recommendation 4 (abstract)"), cite(NICE_GIB, "Rec 1.1.2")),
            ),
        ),
        citations = listOf(
            cite(STW_GIB_A), cite(STW_GIB_B), cite(NICE_GIB), cite(ESGE21), cite(ESGE26), cite(ACG21), cite(ICG19),
            cite(BAVENO), cite(ACGCAG),
        ),
    ),

    // =========================================================================================================
    // Severe dengue
    // =========================================================================================================
    Protocol(
        id = "dengue-severe",
        category = ProtocolCategory.Infection,
        title = "Dengue with warning signs and severe dengue",
        summary = "Warning signs, severe dengue, NCVBDC fluid regimens for warning signs, compensated and hypotensive shock, " +
            "haematocrit-guided titration, transfusion, platelets and drugs to avoid — NCVBDC 2023 and WHO 2025.",
        keywords = listOf(
            "dengue", "severe dengue", "DHF", "dengue haemorrhagic fever", "dengue hemorrhagic fever", "DSS", "dengue shock syndrome",
            "warning signs", "plasma leakage", "capillary leak", "haematocrit", "hematocrit", "PCV", "HCT", "thrombocytopenia",
            "platelet count", "platelet transfusion", "NS1", "dengue IgM", "breakbone fever", "NCVBDC", "NVBDCP", "Aedes",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Phases and warning signs",
                timing = "Critical phase usually from day 3–4 of fever, lasting 24–48 h (NCVBDC 2023)",
                points = listOf(
                    "NCVBDC 2023: fever of 2–7 days with ≥2 of headache, retro-orbital pain, myalgia, arthralgia, rash, bleeding, low platelets or WBC, or warning signs.",
                    "NCVBDC 2023: 5–10% enter the critical (leakage) phase, more often with secondary infection, comorbidity or co-infection.",
                    "NCVBDC 2023: haematocrit 10% above baseline is an early objective sign of plasma leakage; a rapid platelet fall suggests progression.",
                    "NCVBDC 2023: CBC with haematocrit and random blood sugar for everyone with warning signs or fever >3 days.",
                    "NCVBDC 2023: probable — non-ELISA NS1/IgM positive; confirmed — NS1 or IgM ELISA, PCR, virus isolation or IgG seroconversion.",
                ),
                checklistTitle = "NCVBDC 2023 warning signs (Box 2)",
                checklist = listOf(
                    "Persistent vomiting",
                    "Abdominal pain and tenderness",
                    "Lethargy, restlessness or sudden behavioural change",
                    "Bleeding — epistaxis, melaena, haematemesis, heavy menstrual bleeding, haematuria",
                    "Syncope or giddiness",
                    "Clinical fluid accumulation — ascites, pleural effusion",
                    "Liver enlarged >2 cm",
                    "Rising haematocrit with a rapid fall in platelet count",
                ),
                citations = listOf(
                    cite(DENGUE_IN, "§4.1 (Box 1, Box 2); §7.1; §7.3"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Severe dengue and admission",
                points = listOf(
                    "NCVBDC 2023 severe dengue: leakage with shock or respiratory distress, severe bleeding, severe organ dysfunction, AST or ALT ≥1000 U/L, or GCS <9.",
                    "NCVBDC 2023 compensated shock: normal sensorium, capillary refill >2 s, cold extremities, tachycardia, pulse pressure <20 mm Hg, postural hypotension.",
                    "NCVBDC 2023 hypotensive shock: restless or lethargic, mottled cold clammy skin, feeble pulses, profound or unrecordable BP, acidotic breathing, oliguria.",
                    "NCVBDC 2023 high risk: age >65, obesity, pregnancy, haemoglobinopathy, peptic ulcer, diabetes, hypertension, chronic organ disease, long-term steroids or NSAIDs.",
                    "WHO 2025: also consider admission for pregnancy and prolonged capillary refill; by setting, extremes of age or high-risk comorbidity.",
                    "NCVBDC 2023: stabilise severe dengue and admit to a hospital with blood transfusion facilities.",
                ),
                checklistTitle = "NCVBDC 2023: admit if (Ch. 12)",
                checklist = listOf(
                    "Any warning sign",
                    "Severe dengue",
                    "Cannot tolerate oral fluids",
                    "Dyspnoea",
                    "Hypotension or narrow pulse pressure",
                    "Acute renal failure",
                    "Coagulopathy",
                    "Lives alone or far from care without reliable transport",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(DENGUE_IN, "§4.4; Table 2; Box 5; §7.3.2; Ch. 12"),
                    cite(WHO_ARBO, "§2.7"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Monitoring and what guides fluids",
                points = listOf(
                    "NCVBDC 2023: haematocrit before fluids if available at once — never delay fluids for it; repeat after, not during, a bolus.",
                    "NCVBDC 2023: vitals 2–4-hourly without shock, 1–2-hourly in shock, every 5–30 min in severe dengue; haematocrit 4–6-hourly when stable.",
                    "NCVBDC 2023: aim for urine output about 0.5 mL/kg/h; use ideal body weight for fluid calculations if overweight or obese.",
                    "NCVBDC 2023 (adults, shock or complications): glucose, blood gas with lactate, electrolytes, renal and liver tests, coagulation, chest X-ray, blood group.",
                    "WHO 2025 (strong): use capillary refill time, and lactate once IV resuscitation has started in severe disease, to guide IV fluids — " +
                        "lactate is not useful with liver failure; (conditional) passive leg raise in shock when unsure.",
                    "WHO 2025 (conditional): crystalloid rather than colloid; it gives no volumes and defers to regional and national guidelines.",
                ),
                citations = listOf(
                    cite(DENGUE_IN, "§7.3 (monitoring; additional investigations); §7.3.2 principles"),
                    cite(WHO_ARBO, "§5.1; §5.2.1–5.2.4"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Warning signs (Group B): IV fluids",
                timing = "Reassess every hour",
                points = listOf(
                    "NCVBDC 2023: admit; encourage oral fluids; if not tolerated, IV 0.9% saline or Ringer's lactate.",
                    "NCVBDC 2023: 7–10 mL/kg crystalloid over 1 h; reassess BP, pulse pressure, haematocrit, urine output and capillary refill hourly.",
                    "NCVBDC 2023 text: improving — reduce to 5–7 mL/kg/h for 2–4 h, 3–5 mL/kg/h for 2–4 h, then 2–4 mL/kg/h for 2–4 h.",
                    "NCVBDC 2023: no improvement and haematocrit >45% — second bolus 10 mL/kg over 1 h; still no improvement — manage as severe dengue.",
                    "NCVBDC 2023: no improvement and haematocrit <45% — suspect severe overt bleeding; check Hb and plan transfusion: whole blood 10 mL/kg or packed red cells 5 mL/kg.",
                    "NCVBDC 2023: stop IV fluids at 24–48 h by clinical status; discharge in 12–24 h is possible if recovering fast and not in the critical phase.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Warning signs — initial crystalloid (NCVBDC 2023)",
                        dose = WeightDose(7.0, 10.0, unit = "mL", per = "IV over 1 h"),
                        note = "0.9% saline or Ringer's lactate; ideal body weight if overweight or obese.",
                    ),
                    StepDose(
                        label = "Warning signs — second bolus if haematocrit >45% (NCVBDC 2023)",
                        dose = WeightDose(10.0, 10.0, unit = "mL", per = "IV over 1 h"),
                    ),
                    StepDose(
                        label = "Whole blood — no improvement after the first bolus and haematocrit <45% (NCVBDC 2023)",
                        dose = WeightDose(10.0, 10.0, unit = "mL", per = "per transfusion"),
                        note = "Or packed red cells 5 mL/kg.",
                    ),
                ),
                citations = listOf(
                    cite(DENGUE_IN, "§7.3.1; Figure 11"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Compensated shock",
                timing = "Start resuscitation at once; reassess after the first hour",
                points = listOf(
                    "NCVBDC 2023: isotonic crystalloid 10–20 mL/kg/h over 1 h, then reassess vitals, capillary refill, haematocrit and urine output.",
                    "NCVBDC 2023: improving — 7–10 then 5–7 mL/kg/h for 1–2 h each, 3–5 mL/kg/h for 2–4 h, 2–3 mL/kg/h for 2–3 h.",
                    "NCVBDC 2023: then adjust to haemodynamic status; fluids can be maintained for up to 24–48 h.",
                    "NCVBDC 2023: shock persists with haematocrit high (>45%) — second crystalloid bolus over 1 h, then taper from 7–10 mL/kg/h.",
                    "NCVBDC 2023: haematocrit below reference (<40% women, <45% men) — bleeding; cross-match, whole blood 10 mL/kg or packed cells 5 mL/kg.",
                    "NCVBDC 2023: refractory hypotension — consider inotropes and manage as decompensated shock.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Compensated shock — first crystalloid bolus (NCVBDC 2023)",
                        dose = WeightDose(10.0, 20.0, unit = "mL", per = "IV per hour, for 1 h"),
                    ),
                    StepDose(
                        label = "Compensated shock — first taper rate (NCVBDC 2023)",
                        dose = WeightDose(7.0, 10.0, unit = "mL", per = "per hour for 1–2 h"),
                        note = "Then 5–7 mL/kg/h for 1–2 h, 3–5 mL/kg/h for 2–4 h, 2–3 mL/kg/h for 2–3 h.",
                    ),
                    StepDose(
                        label = "Compensated shock — second bolus if haematocrit >45% (NCVBDC 2023)",
                        dose = WeightDose(10.0, 20.0, unit = "mL", per = "IV over 1 h"),
                        note = "Text: 10 mL/kg/h for 1 h; Figure 12: 10–20 mL/kg over 1 h.",
                    ),
                    StepDose(
                        label = "Packed red cells if bleeding (NCVBDC 2023)",
                        dose = WeightDose(5.0, 5.0, unit = "mL", per = "per transfusion"),
                        note = "Or whole blood 10 mL/kg.",
                    ),
                ),
                citations = listOf(
                    cite(DENGUE_IN, "§7.3.2 — management of compensated shock; Figure 12"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Hypotensive (decompensated) shock",
                timing = "First bolus over 15 min (NCVBDC 2023 text)",
                points = listOf(
                    "NCVBDC 2023 text: crystalloid, or colloid if available, 20 mL/kg bolus over 15 min. Figure 13: 10–20 mL/kg over 15–30 min, maximum 500 mL.",
                    "NCVBDC 2023: improving — 10 mL/kg/h for 1 h, then 5–7 mL/kg/h for 1–2 h, 3–5 mL/kg/h for 2–4 h, then 2–3 mL/kg/h or less.",
                    "NCVBDC 2023: shock persists with haematocrit >45% — repeat 10–20 mL/kg over 15–30 min; up to three rapid boluses.",
                    "NCVBDC 2023: falling haematocrit (<40% women, <45% men) — bleeding; cross-match and transfuse as soon as possible.",
                    "NCVBDC 2023: refractory shock — consider inotropes; look for cardiogenic or septic shock, acidosis, metabolic causes and hidden bleeding; preferably ICU.",
                    "NCVBDC 2023: restoring blood pressure is critical — if it cannot be achieved quickly, the prognosis is extremely grave.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Hypotensive shock — first bolus, §7.3.2 text (NCVBDC 2023)",
                        dose = WeightDose(20.0, 20.0, unit = "mL", per = "IV bolus over 15 min"),
                        note = "Crystalloid, or colloid if available. The text gives no cap.",
                    ),
                    StepDose(
                        label = "Hypotensive shock — rapid bolus, Figure 13 (NCVBDC 2023)",
                        dose = WeightDose(10.0, 20.0, unit = "mL", per = "IV bolus over 15–30 min"),
                        note = "Isotonic crystalloid; Figure 13 caps each bolus at 500 mL. Up to three rapid boluses.",
                        maxTotal = 500.0,
                    ),
                    StepDose(
                        label = "Hypotensive shock — after improvement (NCVBDC 2023)",
                        dose = WeightDose(10.0, 10.0, unit = "mL", per = "per hour for 1 h"),
                        note = "Then 5–7 mL/kg/h for 1–2 h, 3–5 mL/kg/h for 2–4 h, then 2–3 mL/kg/h or less, for up to 24–48 h.",
                    ),
                ),
                citations = listOf(
                    cite(DENGUE_IN, "§7.3.2 — management of decompensated shock; IV fluid therapy during the critical period; Figure 13"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Bleeding, transfusion and platelets",
                points = listOf(
                    "NCVBDC 2023: transfuse for established severe bleeding, or suspected bleeding (falling haematocrit) with unexplained hypotension.",
                    "NCVBDC 2023: find and stop the source (nasal packing, endoscopy); unquantified loss — aliquots of 10 mL/kg fresh whole blood or 5 mL/kg packed cells.",
                    "NCVBDC 2023: no prophylactic platelets for thrombocytopenia; transfuse platelets only if bleeding.",
                    "NCVBDC 2023 exceptions: may be considered below 10,000/mm³ without bleeding, before emergency surgery, or in adults with hypertension below 10,000/mm³.",
                    "WHO 2025 (conditional): against prophylactic platelets at <50,000/µL without active bleeding; surgery, invasive procedures or anticoagulation may differ.",
                    "NCVBDC 2023: H2 blockers or PPIs may be used for GI bleeding (unproven); antiplatelets may be stopped if platelets <1 lakh or bleeding with leakage.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Fresh whole blood aliquot — severe haemorrhage (NCVBDC 2023)",
                        dose = WeightDose(10.0, 10.0, unit = "mL", per = "per aliquot"),
                        note = "Or freshly packed red cells 5 mL/kg per aliquot, when loss cannot be quantified.",
                    ),
                ),
                citations = listOf(
                    cite(DENGUE_IN, "§7.3.2 principles; §7.4; Box 8; §8.1.3(b)"),
                    cite(WHO_ARBO, "§5.5"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Drugs and procedures to avoid",
                points = listOf(
                    "NCVBDC 2023: paracetamol 10 mg/kg per dose, not more often than 6-hourly; adult maximum 4 g/day. WHO 2025: caution with liver " +
                        "disease or transaminitis; its paracetamol and metamizole suggestions are for non-severe disease (metamizole has limited availability and approval).",
                    "WHO 2025 (strong): no NSAIDs at any severity. NCVBDC 2023: avoid aspirin and NSAIDs.",
                    "WHO 2025 (conditional): no systemic corticosteroids in severe or non-severe dengue.",
                    "NCVBDC 2023 exception: encephalopathy — dexamethasone 0.15 mg/kg IV 6–8-hourly may be considered to lower intracranial pressure.",
                    "ICMR AMR 2019: antibiotics are not needed; IM injections are contraindicated (citing WHO); glucose-containing fluids may worsen stress hyperglycaemia.",
                ),
                checklistTitle = "Avoid (as sourced)",
                checklist = listOf(
                    "Aspirin and other NSAIDs — NCVBDC 2023; WHO 2025",
                    "Intramuscular injections — ICMR AMR 2019; NCVBDC 2023 (children chapter)",
                    "Systemic corticosteroids — WHO 2025; NCVBDC 2023: no therapeutic utility (children chapter)",
                    "Routine pleural or ascitic tapping — NCVBDC 2023 (children chapter); adults: only for severe respiratory distress after other measures fail, with extreme caution",
                    "Diuretics during the leakage phase — NCVBDC 2023 (children chapter)",
                    "Hypotonic fluids (except infants <6 months) — NCVBDC 2023",
                    "Routine antibiotics — ICMR AMR 2019",
                ),
                doses = listOf(
                    StepDose(
                        label = "Paracetamol (NCVBDC 2023)",
                        dose = WeightDose(10.0, 10.0, unit = "mg", per = "per dose, not more often than 6-hourly"),
                        note = "NCVBDC 2023: adult maximum 4 g/day. WHO 2025: use with caution with liver disease or transaminitis.",
                    ),
                    StepDose(
                        label = "Dexamethasone — encephalopathy with raised ICP only (NCVBDC 2023)",
                        dose = WeightDose(0.15, 0.15, unit = "mg", per = "IV per dose, every 6–8 h"),
                        note = "NCVBDC 2023: 'may consider'. WHO 2025 suggests against systemic corticosteroids in dengue.",
                    ),
                ),
                citations = listOf(
                    cite(DENGUE_IN, "§7.2 (Group A); §5.5–5.7; §7.3.2; §7.6.1; §7.6.2"),
                    cite(WHO_ARBO, "§4.2.1–4.2.3; §4.3; §5.3"),
                    cite(ICMR_AMR, "Ch. 2 §2.3.7"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Stopping fluids, fluid overload and discharge",
                points = listOf(
                    "NCVBDC 2023 (Box 7): reduce or stop IV fluids with normal BP, pulse and perfusion, falling haematocrit, afebrile 24–48 h, settling abdomen, improving urine.",
                    "NCVBDC 2023: IV fluids beyond 24–48 h in shock (60–72 h without shock) risk pulmonary oedema.",
                    "NCVBDC 2023: overload — puffy eyelids, ascites, dyspnoea, then respiratory distress; check acidosis, bleeding, calcium, sugar; stop hypotonic fluids.",
                    "NCVBDC 2023: early overload — switch to colloid boluses, e.g. dextran 40 10 mL/kg (maximum 30 mL/kg/day).",
                    "NCVBDC 2023: overload with shock — dextran 10 mL/kg/h; once BP is stable, furosemide 1 mg/kg IV; reduce fluids to as low as 1 mL/kg/h.",
                    "NCVBDC 2023 discharge: afebrile ≥24 h without antipyretics, 2–3 days after shock recovery, no respiratory distress, platelets >50,000/mm³.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Dextran 40 bolus — fluid overload (NCVBDC 2023)",
                        dose = WeightDose(10.0, 10.0, unit = "mL", per = "IV bolus"),
                        note = "Restricted to 30 mL/kg/day (renal effects).",
                    ),
                    StepDose(
                        label = "Furosemide — overload with shock, after BP stabilises on dextran (NCVBDC 2023)",
                        dose = WeightDose(1.0, 1.0, unit = "mg", per = "IV per dose"),
                        note = "Given during the dextran infusion; monitor vital signs every 15 min for 1 h. NCVBDC 2023: hypokalaemia may " +
                            "follow diuresis — check and correct potassium.",
                    ),
                ),
                citations = listOf(
                    cite(DENGUE_IN, "Box 7; §7.3.2; §7.5; §7.6.1; §5.6; Ch. 13"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Platelet thresholds differ: NCVBDC 2023 allows prophylactic platelets below 10,000/mm³ (or before emergency surgery); " +
                    "WHO 2025 suggests against prophylactic platelets at counts below 50,000/µL without active bleeding.",
                listOf(cite(DENGUE_IN, "§7.3.2; Box 8"), cite(WHO_ARBO, "§5.5")),
            ),
            ProtocolNote(
                "Colloids differ: NCVBDC 2023 allows hyperoncotic colloids (dextran 40, starch) for massive leakage or non-response to " +
                    "maximum crystalloid; WHO 2025 suggests crystalloid rather than colloid.",
                listOf(cite(DENGUE_IN, "§7.3.2 — IV fluid therapy during the critical period"), cite(WHO_ARBO, "§5.1")),
            ),
            ProtocolNote(
                "NCVBDC 2023 is internally inconsistent: hypotensive shock text gives 20 mL/kg over 15 min, Figure 13 10–20 mL/kg over " +
                    "15–30 min (maximum 500 mL); Group B taper text and Figure 11 give different durations and rates.",
                listOf(cite(DENGUE_IN, "§7.3.1; §7.3.2; Figures 11 and 13")),
            ),
            ProtocolNote(
                "ICMR AMR 2019 reproduces older WHO rates — warning signs start at 5–7 mL/kg/h for 1–2 h — lower than NCVBDC 2023 " +
                    "(7–10 mL/kg over 1 h). NCVBDC 2023 is the current national guideline.",
                listOf(cite(ICMR_AMR, "Ch. 2 §2.3.7.2"), cite(DENGUE_IN, "§7.3.1")),
            ),
            ProtocolNote(
                "WHO 2025 makes no IV fluid volume recommendations; it defers to regional and national guidelines. For non-severe " +
                    "disease it suggests metamizole as well as paracetamol (metamizole: limited availability and regulatory approval); " +
                    "NCVBDC 2023 names paracetamol only.",
                listOf(cite(WHO_ARBO, "§4.2.1–4.2.2; §5.2.1"), cite(DENGUE_IN, "§7.2")),
            ),
        ),
        citations = listOf(cite(DENGUE_IN), cite(WHO_ARBO), cite(ICMR_AMR)),
    ),

    // =========================================================================================================
    // Severe malaria
    // =========================================================================================================
    Protocol(
        id = "severe-malaria",
        category = ProtocolCategory.Infection,
        title = "Severe malaria",
        summary = "WHO criteria, IV/IM artesunate (adult and small-child doses), follow-on ACT, artemether and quinine alternatives, " +
            "hypoglycaemia and supportive care — NCVBDC/NVBDCP, ICMR and WHO 2026.",
        keywords = listOf(
            "severe malaria", "complicated malaria", "cerebral malaria", "falciparum", "P. falciparum", "Pf", "vivax", "Pv",
            "artesunate", "artemether", "arteether", "quinine", "ACT", "ACT-SP", "ACT-AL", "primaquine", "hyperparasitaemia",
            "blackwater fever", "malarial AKI", "RDT", "MP smear", "peripheral smear", "NCVBDC", "NVBDCP", "NDP 2013",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise severe malaria",
                timing = "Start parenteral treatment without delay (WHO 2026)",
                points = listOf(
                    "WHO 2026: unable to take oral medicines reliably, vital organ dysfunction or a high parasite count means increased risk of death.",
                    "WHO 2026: do not worry unduly about definitions — if severe malaria is possible, start parenteral antimalarials without delay.",
                    "NVBDCP 2013: complications can develop within 12–24 h; prostration with confusion or drowsiness is typical; rarely, P. vivax is severe too.",
                    "WHO 2026: severe vivax malaria uses the same criteria without parasite density thresholds.",
                    "ICMR AMR 2019: suspect malaria in all acute undifferentiated fever; adults may also have dark or reduced urine.",
                ),
                checklistTitle = "WHO 2026: severe falciparum malaria (≥1, with asexual parasitaemia, no other cause)",
                checklist = listOf(
                    "Impaired consciousness — GCS <11 in adults",
                    "Prostration — cannot sit, stand or walk unaided",
                    "More than two convulsions in 24 h",
                    "Acidosis — base deficit >8 mEq/L, bicarbonate <15 mmol/L or venous lactate ≥5 mmol/L",
                    "Hypoglycaemia — glucose <40 mg/dL (<2.2 mmol/L)",
                    "Severe anaemia (adults) — Hb <7 g/dL or haematocrit <20% with parasites >10,000/µL",
                    "Renal impairment — creatinine >3 mg/dL (>265 µmol/L) or urea >20 mmol/L",
                    "Jaundice — bilirubin >3 mg/dL (>50 µmol/L) with parasites >100,000/µL",
                    "Pulmonary oedema — on X-ray, or SpO₂ <92% on air with RR >30/min",
                    "Significant bleeding — recurrent nose, gum or venepuncture bleeding; haematemesis or melaena",
                    "Shock — capillary refill ≥3 s without hypotension, or SBP <80 mm Hg (adults) with poor perfusion",
                    "Hyperparasitaemia — P. falciparum >10%",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(WHO_MAL, "§5.2.2 Treating severe malaria — Definitions"),
                    cite(MAL_STG, "Severe and complicated malaria"),
                    cite(ICMR_AMR, "Ch. 2 §2.1; §2.2.5"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Immediate assessment",
                timing = "On arrival",
                points = listOf(
                    "WHO 2026: secure the airway if unconscious; weigh or estimate weight; IV cannula; immediate glucose, haematocrit or Hb, parasitaemia and, in adults, renal function.",
                    "WHO 2026: record a coma score; lumbar puncture in unconscious patients to exclude bacterial meningitis.",
                    "WHO 2026: bicarbonate or venous lactate; cross-match, FBC, platelets, clotting, blood culture and biochemistry.",
                    "NDP 2013: before admitting or referring, do an RDT and blood smear, give a parenteral artemisinin (or quinine) and send treatment details.",
                    "WHO 2026: septicaemia, pneumonia and severe malaria overlap — keep a low threshold for antibiotics; with shock, take cultures and give broad-spectrum antimicrobials.",
                    "NVBDCP 2013: treat where parenteral antimalarials, IV fluids, blood transfusion, laboratory and oxygen exist; otherwise give emergency treatment and refer at once.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(WHO_MAL, "§5.2.2 — Clinical assessment; Concomitant use of antibiotics; Management of complications (shock)"),
                    cite(NDP13, "Treatment of severe malaria cases"),
                    cite(MAL_STG, "Requirements for management of complications"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Parenteral artesunate",
                timing = "Doses at 0, 12 and 24 h, then once daily (NDP 2013; ICMR AMR 2019)",
                points = listOf(
                    "WHO 2026 (strong, high certainty): IV or IM artesunate for all severe malaria — adults, children, infants, pregnancy in all trimesters, lactation.",
                    "WHO 2026: for at least 24 h and until oral medication is tolerated. NDP 2013: once started, give parenteral treatment for at least 24 h.",
                    "NDP 2013 and ICMR AMR 2019: 2.4 mg/kg IV or IM on admission, at 12 h and at 24 h, then once a day.",
                    "WHO 2026 and ICMR AMR 2019: children <20 kg — 3 mg/kg per dose for equivalent exposure. NDP 2013 gives no weight-specific dose.",
                    "WHO 2026: dissolve in 5% sodium bicarbonate, dilute in about 5 mL 5% dextrose; IV injection or IM anterior thigh; prepare fresh for each dose.",
                    "WHO 2026: artemisinin doses need no adjustment for kidney or liver dysfunction.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Artesunate — adults and children ≥20 kg (WHO 2026; NDP 2013)",
                        dose = WeightDose(2.4, 2.4, unit = "mg", per = "IV or IM per dose — 0, 12 and 24 h, then once daily"),
                        note = "At least 24 h and until oral medication is tolerated, then a full 3-day ACT. No maximum stated.",
                    ),
                    StepDose(
                        label = "Artesunate — children <20 kg (WHO 2026; ICMR AMR 2019)",
                        dose = WeightDose(3.0, 3.0, unit = "mg", per = "IV or IM per dose — same schedule"),
                        note = "Child dose. NDP 2013 does not distinguish by weight.",
                    ),
                ),
                citations = listOf(
                    cite(WHO_MAL, "§5.2.2.1 Artesunate (recommendations; practical info); §5.2.2 — Adjustment of parenteral dosing"),
                    cite(NDP13, "Chemotherapy of severe and complicated malaria (table and note)"),
                    cite(ICMR_AMR, "Ch. 2 §2.2.6"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Follow-on oral treatment",
                timing = "After ≥24 h of parenteral therapy, once oral medicines are tolerated",
                points = listOf(
                    "WHO 2026: complete a full 3-day ACT — artesunate–amodiaquine, artemether–lumefantrine or dihydroartemisinin–piperaquine; no mefloquine ACT after impaired consciousness.",
                    "NDP 2013: area-specific 3-day ACT — ACT-AL in North-Eastern states, ACT-SP elsewhere — plus single-dose primaquine on day 2.",
                    "NDP 2013 ACT-SP: artesunate 4 mg/kg daily for 3 days, sulfadoxine 25 mg/kg with pyrimethamine 1.25 mg/kg on day 1; primaquine 0.75 mg/kg day 2.",
                    "NDP 2013: primaquine is contraindicated in pregnancy, infants and G6PD deficiency; stop it if dark or red urine or cyanosis appears.",
                    "Pregnancy, first trimester: NDP 2013 — ACT not to be given (first-trimester P. falciparum: quinine); WHO 2026 — " +
                        "artemether–lumefantrine; SP-containing ACTs are contraindicated in the first trimester. No primaquine in pregnancy.",
                    "NDP 2013: after parenteral quinine — oral quinine 10 mg/kg three times daily to complete 7 days, plus doxycycline or clindamycin for 7 days.",
                    "NDP 2013: doxycycline is contraindicated in pregnancy and children <8 years — use clindamycin.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Artesunate — ACT-SP component (NDP 2013)",
                        dose = WeightDose(4.0, 4.0, unit = "mg", per = "orally once daily for 3 days"),
                        note = "NDP 2013 chart adult dose (age ≥15 years): 200 mg/day.",
                        maxTotal = 200.0,
                    ),
                    StepDose(
                        label = "Sulfadoxine — ACT-SP component (NDP 2013)",
                        dose = WeightDose(25.0, 25.0, unit = "mg", per = "orally on day 1 only"),
                        note = "With pyrimethamine 1.25 mg/kg (adult 75 mg) on day 1; NDP 2013 chart adult dose AS 200 mg/day, SP 1500/75 mg.",
                        maxTotal = 1500.0,
                    ),
                    StepDose(
                        label = "Primaquine single dose (NDP 2013)",
                        dose = WeightDose(0.75, 0.75, unit = "mg", per = "orally on day 2"),
                        note = "Not in pregnancy, infants <1 year or G6PD deficiency (NDP 2013). NDP 2013 dosage chart: 45 mg " +
                            "(6 × 7.5 mg base) for age ≥15 years. WHO 2026: 0.25 mg/kg in low-transmission areas.",
                        maxTotal = 45.0,
                    ),
                    StepDose(
                        label = "Quinine — oral follow-on (NDP 2013)",
                        dose = WeightDose(10.0, 10.0, unit = "mg", per = "orally three times daily, to complete 7 days"),
                    ),
                    StepDose(
                        label = "Doxycycline with quinine (NDP 2013)",
                        dose = WeightDose(3.0, 3.0, unit = "mg", per = "orally once daily for 7 days"),
                        note = "NDP 2013 table and Note: 100 mg once a day (text: 3 mg/kg). Not in pregnancy or children <8 years.",
                        maxTotal = 100.0,
                    ),
                    StepDose(
                        label = "Clindamycin with quinine (NDP 2013)",
                        dose = WeightDose(10.0, 10.0, unit = "mg", per = "orally 12-hourly for 7 days"),
                    ),
                ),
                citations = listOf(
                    cite(WHO_MAL, "§5.2.2 — Follow-on treatment; §5.2.1.4.1 (first trimester)"),
                    cite(
                        NDP13,
                        "Treatment of uncomplicated malaria (points 3, 7); Treatment of vivax malaria; Treatment of falciparum malaria " +
                            "(ACT-SP dosage chart; pregnancy); Chemotherapy of severe and complicated malaria — follow-up; Note",
                    ),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "If artesunate is not available",
                points = listOf(
                    "WHO 2026 (conditional): artemether in preference to quinine — 3.2 mg/kg IM into the anterior thigh on admission, then 1.6 mg/kg IM daily.",
                    "WHO 2026 and NDP 2013: quinine loading 20 mg salt/kg, then 10 mg salt/kg 8-hourly from 8 h; NDP: no loading dose if quinine already given.",
                    "WHO 2026: each quinine dose as a slow rate-controlled infusion, usually in 5% dextrose over 4 h, never >5 mg salt/kg/h, never IV bolus.",
                    "WHO 2026: persisting AKI or no improvement by 48 h — reduce quinine to 10 mg salt/kg 12-hourly; no change on dialysis or haemofiltration.",
                    "NDP 2013: arteether 150 mg IM daily for 3 days, adults only (not for children).",
                    "WHO 2026 pre-referral: single IM artesunate where full treatment is impossible; children <6 years without IM — rectal artesunate 10 mg/kg, then refer.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Artemether IM — first dose (WHO 2026; NDP 2013)",
                        dose = WeightDose(3.2, 3.2, unit = "mg", per = "IM on admission"),
                    ),
                    StepDose(
                        label = "Artemether IM — maintenance (WHO 2026; NDP 2013)",
                        dose = WeightDose(1.6, 1.6, unit = "mg", per = "IM once daily"),
                    ),
                    StepDose(
                        label = "Quinine dihydrochloride — loading (WHO 2026; NDP 2013)",
                        dose = WeightDose(20.0, 20.0, unit = "mg", per = "salt, IV infusion over 4 h (or divided IM)"),
                        note = "Not if quinine already received (NDP 2013). Infusion rate ≤5 mg salt/kg/h. IM only if infusion is not " +
                            "possible: 10 mg/kg into each anterior thigh, buffered or diluted to 60–100 mg/mL, never into the buttock (WHO 2026).",
                    ),
                    StepDose(
                        label = "Quinine dihydrochloride — maintenance (WHO 2026; NDP 2013)",
                        dose = WeightDose(10.0, 10.0, unit = "mg", per = "salt, IV infusion every 8 h"),
                        note = "WHO 2026: every 12 h if AKI persists or no improvement by 48 h.",
                    ),
                    StepDose(
                        label = "Rectal artesunate — pre-referral, children <6 years (WHO 2026)",
                        dose = WeightDose(10.0, 10.0, unit = "mg", per = "single rectal dose, then refer immediately"),
                        note = "Child dose; only where IM artesunate is not available.",
                    ),
                ),
                citations = listOf(
                    cite(WHO_MAL, "§5.2.2.2 (artemether; quinine practical info); §5.2.2.3 Pre-referral treatment options; §5.2.2 — Adjustment of parenteral dosing"),
                    cite(NDP13, "Chemotherapy of severe and complicated malaria"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Glucose and monitoring",
                timing = "Blood glucose every 4 h (WHO 2026)",
                points = listOf(
                    "WHO 2026: check blood glucose every 4 h if possible, especially if unconscious; frequent vital signs, coma score and urine output.",
                    "WHO 2026: correct hypoglycaemia and maintain with a glucose-containing infusion; treat below 40 mg/dL (2.2 mmol/L) in adults and older children.",
                    "WHO 2026: children <5 years — intervene below 3 mmol/L (≈54 mg/dL).",
                    "WHO 2026 and NDP 2013: quinine causes hypoglycaemia, recurrent in pregnancy; artemisinins do not aggravate it.",
                    "WHO 2026: convulsions — check glucose; IV or rectal benzodiazepine; prophylactic anticonvulsants are not recommended.",
                    "WHO 2026: intensive nursing care, preferably in an ICU where possible.",
                ),
                citations = listOf(
                    cite(WHO_MAL, "§5.2.2 — Continuing supportive care; Management of complications; Use of anticonvulsants; Treatment during pregnancy"),
                    cite(NDP13, "Note (pregnant women with severe malaria)"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Fluids, transfusion and organ support",
                points = listOf(
                    "WHO 2026: assess fluid needs individually; adults are very vulnerable to fluid overload; rapid bolus colloid or crystalloid is contraindicated.",
                    "WHO 2026: watch JVP, perfusion, venous filling, skin turgor and urine output — over-hydration causes pulmonary oedema, under-hydration shock and AKI.",
                    "WHO 2026 (expert opinion): low-transmission settings — transfuse at Hb 7 g/dL (Hct 20%). ICMR AMR 2019: transmission is low " +
                        "in most of India but intense in the north-eastern states and large areas of Odisha, Chhattisgarh, Jharkhand and Madhya Pradesh.",
                    "WHO 2026: AKI — exclude pre-renal causes; established renal failure or refractory acidosis — early haemofiltration or haemodialysis (peritoneal if unavailable).",
                    "WHO 2026: pulmonary oedema — prop up at 45°, oxygen, diuretic, stop IV fluids; intubate with PEEP or CPAP if life-threatening.",
                    "WHO 2026: bleeding — screened fresh blood, FFP, cryoprecipitate or platelets if available, vitamin K. Exchange transfusion: no recommendation possible.",
                ),
                citations = listOf(
                    cite(WHO_MAL, "§5.2.2 — Additional aspects of management (fluid therapy, blood transfusion, exchange transfusion); Management of complications"),
                    cite(ICMR_AMR, "Ch. 2 §2.2.5.1"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Treatments to avoid",
                points = listOf(
                    "NDP 2013: do not use corticosteroids, IV mannitol or heparin as anticoagulant; do not give adrenaline or overhydrate.",
                    "WHO 2026: no adjunctive treatment has proved effective and many are harmful; corticosteroids increase GI bleeding and seizures and prolong coma.",
                    "WHO 2026: phenobarbital 20 mg/kg increased mortality in children — not without respiratory support.",
                    "NDP 2013: avoid mefloquine in cerebral malaria (neuropsychiatric complications).",
                ),
                checklistTitle = "Not recommended (NDP 2013; WHO 2026)",
                checklist = listOf(
                    "Corticosteroids",
                    "IV mannitol",
                    "Heparin",
                    "Adrenaline as an adjunctive treatment",
                    "Overhydration or rapid fluid boluses",
                    "Quinine by IV bolus (lethal hypotension)",
                    "Prophylactic anticonvulsants",
                    "Aspirin, pentoxifylline, low-molecular-mass dextran, N-acetylcysteine, bolus albumin, anti-TNF antibody, desferrioxamine",
                    "Mefloquine in cerebral malaria",
                ),
                citations = listOf(
                    cite(NDP13, "Some don'ts in severe malaria case management; Note (mefloquine)"),
                    cite(WHO_MAL, "§5.2.2 — Treatments that are not recommended; Use of anticonvulsants; §5.2.2.2 quinine practical info"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Pregnancy, vivax and after treatment",
                points = listOf(
                    "WHO 2026: pregnancy — full-dose parenteral artesunate in any trimester without delay; IM artemether if unavailable, then quinine.",
                    "WHO 2026: seek obstetric advice early and check glucose often; severe malaria may present just after delivery.",
                    "NDP 2013: severe malaria in any trimester can be treated with artemisinin derivatives, which, unlike quinine, do not worsen hypoglycaemia.",
                    "WHO 2026: severe P. vivax — manage as severe falciparum; complete with ACT or chloroquine, then primaquine radical cure after recovery, guided by G6PD status.",
                    "WHO 2026: delayed haemolysis can start >1 week after artesunate, especially with hyperparasitaemia — follow up for late anaemia.",
                    "WHO 2026: unexplained deterioration after starting antimalarials may be a supervening bacterial infection.",
                ),
                citations = listOf(
                    cite(WHO_MAL, "§5.2.2 — Treatment during pregnancy; Treatment of severe P. vivax malaria; Concomitant use of antibiotics; §5.2.2.1 — post-treatment haemolysis; §5.2.1.6 (G6PD testing)"),
                    cite(NDP13, "Note (pregnant women with severe malaria)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Small-child artesunate dose differs: WHO 2026 and ICMR AMR 2019 give 3 mg/kg for children <20 kg; NDP 2013 gives " +
                    "2.4 mg/kg without a weight distinction.",
                listOf(cite(WHO_MAL, "§5.2.2.1"), cite(ICMR_AMR, "Ch. 2 §2.2.6"), cite(NDP13, "Chemotherapy of severe and complicated malaria")),
            ),
            ProtocolNote(
                "Parenteral duration: WHO 2026 says at least 24 h; NDP 2013 says 'at least 48 hours' in its table heading and one note, " +
                    "and 'minimum of 24 hours' in another note.",
                listOf(cite(WHO_MAL, "§5.2.2 — Follow-on treatment"), cite(NDP13, "Chemotherapy of severe and complicated malaria (table and notes)")),
            ),
            ProtocolNote(
                "ICMR AMR 2019 Table 2.2 prints IV artesunate as 4.2 mg/kg; its own text (§2.2.6), NDP 2013 and WHO 2026 give 2.4 mg/kg.",
                listOf(cite(ICMR_AMR, "Ch. 2 Table 2.2; §2.2.6"), cite(NDP13, "Chemotherapy of severe and complicated malaria"), cite(WHO_MAL, "§5.2.2.1")),
            ),
            ProtocolNote(
                "Single-dose primaquine differs: NDP 2013 gives 0.75 mg/kg on day 2 with ACT; ICMR AMR 2019 (§2.2.2) and WHO 2026 " +
                    "give 0.25 mg/kg in low-transmission areas — WHO does not recommend it in moderate-to-high transmission.",
                listOf(cite(NDP13, "Treatment of falciparum malaria"), cite(WHO_MAL, "§5.2.1.3"), cite(ICMR_AMR, "Ch. 2 §2.2.2")),
            ),
            ProtocolNote(
                "Options differ: NDP 2013 lists IM arteether (adults) and ACT-SP follow-on outside the North-Eastern states; WHO 2026 " +
                    "names artesunate, artemether and quinine or quinidine for parenteral use, and AS-AQ, AL or DHA-PPQ for follow-on.",
                listOf(cite(NDP13, "Chemotherapy of severe and complicated malaria"), cite(WHO_MAL, "§5.2.2 — Treatment of severe malaria; Follow-on treatment")),
            ),
            ProtocolNote(
                "Currency: NDP 2013 is the most recent national drug policy on the NCVBDC website; WHO's severe malaria recommendations " +
                    "date from 2015 and are unchanged in the September 2026 consolidated version.",
                listOf(cite(NDP13), cite(WHO_MAL, "§5.2.2")),
            ),
        ),
        citations = listOf(cite(WHO_MAL), cite(NDP13), cite(MAL_STG), cite(ICMR_AMR)),
    ),
)
