package com.medicalaid.app.content

/*
 * General-adult pages: heart failure, atrial fibrillation, chronic coronary disease.
 * Every number below was checked against the cited document (reviewed November 2026).
 * The AF pages also cite the AF guideline sources already defined for the CHA₂DS₂-VASc and
 * HAS-BLED scores (sc-acc-aha-af-2023, sc-esc-af-2024, sc-nice-ng196).
 */

internal val heartConditionSources: List<Source> = listOf(
    // ------------------------------------------------------------------ Heart failure
    Source(
        "hc-esc-hf-2026", Authority.ESC, "ESC guideline — Eur Heart J 2026;47(38):5346–5457",
        "2026 ESC Guidelines for the management of heart failure", "2026",
        "https://doi.org/10.1093/eurheartj/ehag100",
        status = "Replaces the 2021 ESC heart failure guideline",
    ),
    Source(
        "hc-acc-aha-hf-2022", Authority.AHA, "AHA/ACC/HFSA guideline — Circulation 2022;145(18):e895–e1032",
        "2022 AHA/ACC/HFSA Guideline for the Management of Heart Failure", "2022",
        "https://doi.org/10.1161/CIR.0000000000001063",
    ),
    Source(
        "hc-nice-ng106", Authority.NICE, "Guideline NG106",
        "Chronic heart failure in adults: diagnosis and management", "2018",
        "https://www.nice.org.uk/guidance/ng106/chapter/Recommendations",
        status = "Last updated 3 September 2025",
    ),
    Source(
        "hc-icmr-stw-hf-2026", Authority.ICMR, "Standard Treatment Workflow",
        "Heart Failure: A Breathless Patient (ICD-11 BD10–BD1Z)", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1768823345_cardiology_1-3.pdf",
    ),
    // ------------------------------------------------------------------ Atrial fibrillation
    Source(
        "hc-ehra-noac-2021", Authority.Paper, "EHRA practical guide — Europace 2021;23(10):1612–1676",
        "2021 European Heart Rhythm Association Practical Guide on the Use of Non-Vitamin K Antagonist Oral Anticoagulants in Patients with Atrial Fibrillation", "2021",
        "https://doi.org/10.1093/europace/euab065",
    ),
    Source(
        "hc-icmr-stw-af-2026", Authority.ICMR, "Standard Treatment Workflow",
        "Atrial Fibrillation (ICD-11 BC81.31)", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1768823343_cardiology_1-1.pdf",
    ),
    // ------------------------------------------------------------------ Chronic coronary disease
    Source(
        "hc-esc-ccs-2024", Authority.ESC, "ESC guideline — Eur Heart J 2024;45(36):3415–3537",
        "2024 ESC Guidelines for the management of chronic coronary syndromes", "2024",
        "https://doi.org/10.1093/eurheartj/ehae177",
    ),
    Source(
        "hc-acc-aha-ccd-2023", Authority.AHA, "AHA/ACC/ACCP/ASPC/NLA/PCNA guideline — Circulation 2023;148:e9–e119",
        "2023 AHA/ACC/ACCP/ASPC/NLA/PCNA Guideline for the Management of Patients With Chronic Coronary Disease", "2023",
        "https://doi.org/10.1161/CIR.0000000000001168",
    ),
    Source(
        "hc-acc-aha-lipid-2026", Authority.AHA, "ACC/AHA multisociety guideline — Circulation 2026;153(17):e1154–e1276",
        "2026 ACC/AHA/AACVPR/ABC/ACPM/ADA/AGS/APhA/ASPC/NLA/PCNA Guideline on the Management of Dyslipidemia", "2026",
        "https://doi.org/10.1161/CIR.0000000000001423",
        status = "Replaces the 2018 AHA/ACC blood cholesterol guideline",
    ),
    Source(
        "hc-icmr-stw-angina-2026", Authority.ICMR, "Standard Treatment Workflow",
        "Stable Angina (ICD-11 BA40.1)", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1768823342_cardiology_1-6.pdf",
    ),
)

// =================================================================================== Heart failure
private const val HC_ESC_HF = "hc-esc-hf-2026"
private const val HC_ACC_HF = "hc-acc-aha-hf-2022"
private const val HC_NICE_HF = "hc-nice-ng106"
private const val HC_ICMR_HF = "hc-icmr-stw-hf-2026"

private val hcHeartFailure = Condition(
    id = "heart-failure",
    title = "Heart failure",
    summary = "ESC 2026 phenotypes, four foundational drugs with start/target doses, labs before and after",
    keywords = listOf(
        "heart failure", "HF", "CHF", "congestive heart failure", "cardiac failure", "LVF", "left ventricular failure",
        "HFrEF", "HFpEF", "HFmrEF", "HFimpEF", "LVEF", "ejection fraction", "cardiomyopathy", "LV dysfunction",
        "breathlessness", "dyspnoea", "oedema", "edema", "GDMT", "foundational therapy", "ARNI", "ACE inhibitor", "ARB",
        "MRA", "SGLT2", "beta-blocker", "diuretic", "sacubitril", "valsartan", "spironolactone", "dapagliflozin",
        "empagliflozin", "furosemide", "frusemide", "potassium", "hyperkalaemia",
    ),
    population = Population.General,
    tool = ToolKind.Renal,
    info = listOf(
        InfoBlock(
            "Classification by LVEF",
            listOf(
                "ESC 2026: HFrEF = LVEF <50% with symptoms and/or signs of HF. The 2021 mildly reduced category (LVEF 41–49%) has been removed.",
                "ESC 2026: HFpEF = LVEF ≥50% with symptoms/signs, objective LV diastolic dysfunction or raised filling pressures, supported by raised natriuretic peptides.",
                "ACC/AHA 2022 and NICE: HFrEF ≤40%, HFmrEF 41–49%, HFpEF ≥50%.",
                "ACC/AHA 2022: HF with improved EF = previous LVEF ≤40%, now >40% — continue HFrEF treatment.",
                "ESC 2026: improved LVEF means an absolute rise of ≥10 points to above 40%; keep foundational therapy at the highest tolerated doses (I, C).",
                "ICMR (India): echocardiographic LVEF <35% confirms HFrEF at district hospital level.",
            ),
            listOf(
                cite(HC_ESC_HF, "Section 3.3.1; Recommendation Table 5"), cite(HC_ACC_HF, "Table 4; Figure 3"),
                cite(HC_NICE_HF, "Terms used in this guideline"), cite(HC_ICMR_HF, "Management at district hospital"),
            ),
        ),
        InfoBlock(
            "Tests at diagnosis",
            listOf(
                "ESC 2026 (I, C): full blood count, eGFR and urine albumin–creatinine ratio, electrolytes, liver and thyroid function, HbA1c, lipids, iron status (TSAT, ferritin).",
                "ESC 2026 (I, C): natriuretic peptide, 12-lead ECG, chest X-ray and transthoracic echocardiogram in suspected HF.",
                "ICMR basic: haemogram, ESR, blood sugar, urine, urea/creatinine, sodium/potassium, ECG, chest X-ray. Desirable: echo, BNP/NT-proBNP, troponin, lipids, thyroid, iron profile.",
            ),
            listOf(cite(HC_ESC_HF, "Recommendation Table 3"), cite(HC_ICMR_HF, "Investigations")),
        ),
        InfoBlock(
            "Kidney function and potassium on ACE-I, ARB, ARNI or MRA",
            listOf(
                "Before starting: check creatinine/eGFR and electrolytes (ESC 2026; NICE 1.7.3).",
                "ESC 2026: recheck urea, creatinine and K⁺ 1–2 weeks after starting and 1–2 weeks after the final dose step, then every 4–6 months.",
                "NICE: 1–2 weeks after starting and after each increment, every 3–6 months once at maximum tolerated dose, and whenever renal function may be compromised.",
                "ESC 2026: some rise in creatinine and K⁺ is expected with ACE-I/ARB/ARNI; an eGFR fall <30% and K⁺ up to 5.5 mmol/L are acceptable.",
                "NICE: if creatinine rises >50% or K⁺ exceeds 5.5 mmol/L, follow local guidelines rather than stopping automatically.",
                "ESC 2026: misread kidney-function changes are a leading cause of under-treatment. AKI with eGFR <15 may need a temporary stop; K⁺ >5.5–6.0 calls for dose reduction or a pause.",
            ),
            listOf(cite(HC_ESC_HF, "Section 6.1.6; Suppl. Tables S4, S5, S8"), cite(HC_NICE_HF, "1.7.3–1.7.5 and rationale")),
        ),
        InfoBlock(
            "Where the guidelines differ",
            listOf(
                "SGLT2 inhibitor and MRA: ESC 2026 class I for symptomatic HF at any LVEF; ACC/AHA 2022 in HFpEF — SGLT2i 2a, MRA 2b.",
                "First RAAS drug in HFrEF: ACC/AHA 2022 prefers ARNI (1, A); ESC 2026 ACE-I or ARNI (I, A); NICE ACE-I, switching to ARNI if still symptomatic.",
                "LVEF 41–49%: ESC 2026 treats as HFrEF; ACC/AHA 2022 gives SGLT2i 2a and the other classes 2b; NICE considers all four classes.",
                "Starting an MRA: ACC/AHA 2022 requires eGFR >30 and K⁺ <5.0; ESC 2026 advises caution if K⁺ >5.0 or eGFR <30 (finerenone <25).",
            ),
            listOf(
                cite(HC_ESC_HF, "Recommendation Table 5; Suppl. Table S8"), cite(HC_ACC_HF, "Sections 7.3.1, 7.3.3, 7.6.1, 7.7.1"),
                cite(HC_NICE_HF, "1.4.1–1.4.2, 1.5.1, 1.5.5"),
            ),
        ),
    ),
    tiers = listOf(
        Tier(
            "HFrEF · foundational therapy",
            "ESC 2026: the four classes can be started together once HFrEF is diagnosed (no preferred order); uptitrate at least every 1–2 weeks to target or highest tolerated dose (I, C).",
            listOf(
                Drug(
                    "Sacubitril–valsartan (ARNI)",
                    "Start 49/51 mg twice daily; 24/26 mg if prior symptomatic hypotension, ACE-I-naive or eGFR 30–60. Target 97/103 mg twice daily (ESC 2026). ICMR: 50–200 mg twice daily.",
                    maxDose = "97/103 mg twice daily (target)",
                    cautions = listOf(
                        "Before starting: creatinine/eGFR and K⁺ (ESC 2026), and BP (NICE). Seek advice if K⁺ >5.2 mmol/L or eGFR <30 (ESC 2026).",
                        "Do not use with any history of angio-oedema, SBP <90 mmHg or symptomatic hypotension, or within 36 h of an ACE-I dose (ESC 2026; ACC/AHA 2022: harm).",
                        "Monitor: urea, creatinine, K⁺ 1–2 weeks after starting and after final titration, then every 4–6 months (ESC 2026).",
                        "Larger rises than eGFR −30% or K⁺ 5.5 despite adjusting other drugs: halve the dose, recheck in 1–2 weeks (ESC 2026).",
                        "ICMR: start low only if BP >100 mmHg, electrolytes normal and creatinine <2.5 mg/dL; check BP and electrolytes before each increment.",
                    ),
                    citations = listOf(
                        cite(HC_ESC_HF, "Recommendation Table 5; Table 11; Suppl. Table S5"), cite(HC_ACC_HF, "Section 7.3.1; Table 14"),
                        cite(HC_NICE_HF, "1.7.3, 1.7.7"), cite(HC_ICMR_HF, "Common drugs and dosage"),
                    ),
                ),
                Drug(
                    "Enalapril (ACE inhibitor)",
                    "Start 2.5 mg twice daily; target 10–20 mg twice daily (ESC 2026; ACC/AHA 2022). NICE offers an ACE inhibitor first, with the other three classes.",
                    maxDose = "20 mg twice daily (target)",
                    cautions = listOf(
                        "Before starting: creatinine/eGFR and K⁺. Seek advice if K⁺ >5.2 mmol/L, eGFR <30 or SBP <90 mmHg (ESC 2026).",
                        "Do not use with angio-oedema history, bilateral renal artery stenosis, or pregnancy (ESC 2026).",
                        "Monitor: urea, creatinine, K⁺ 1–2 weeks after starting and after final titration, then every 4–6 months (ESC 2026).",
                        "Stop and seek advice if K⁺ >5.5 mmol/L, creatinine rises >100% or eGFR falls below 20 (ESC 2026).",
                        "Without close monitoring, double the dose no sooner than every 2 weeks (ESC 2026).",
                    ),
                    citations = listOf(
                        cite(HC_ESC_HF, "Table 11; Suppl. Table S4"), cite(HC_ACC_HF, "Table 14"), cite(HC_NICE_HF, "1.4.1"),
                    ),
                ),
                Drug(
                    "Ramipril (ACE inhibitor)",
                    "ESC 2026: start 1.25–2.5 mg twice daily, target 5 mg twice daily. ACC/AHA 2022: 1.25–2.5 mg once daily, target 10 mg once daily.",
                    cautions = listOf("Before starting and monitoring: same checks and thresholds as enalapril (ESC 2026)."),
                    citations = listOf(cite(HC_ESC_HF, "Table 11; Suppl. Table S4"), cite(HC_ACC_HF, "Table 14")),
                ),
                Drug(
                    "Losartan (ARB) — if ACE-I and ARNI not tolerated",
                    "Start (12.5) 25–50 mg once daily; target 150 mg once daily. Alternatives: candesartan 4 → 32 mg once daily; valsartan 40 → 160 mg twice daily (ESC 2026).",
                    maxDose = "150 mg once daily (target)",
                    cautions = listOf(
                        "Before starting and monitoring: as for ACE inhibitors (ESC 2026).",
                        "ACC/AHA 2022: caution with low BP, renal insufficiency or K⁺ >5.0 mEq/L, and after ACE-I angio-oedema.",
                        "NICE: after ACE-I angio-oedema or ARNI intolerance, offer beta-blocker, MRA and SGLT2i and consider an ARB.",
                    ),
                    citations = listOf(
                        cite(HC_ESC_HF, "Recommendation Table 5; Table 11; Suppl. Table S4"), cite(HC_ACC_HF, "Section 7.3.1; Table 14"),
                        cite(HC_NICE_HF, "1.4.4"),
                    ),
                ),
                Drug(
                    "Metoprolol succinate (CR/XL), bisoprolol or carvedilol",
                    "Metoprolol succinate 12.5–25 → 200 mg once daily; bisoprolol 1.25 → 10 mg once daily; carvedilol 3.125 → 25 mg twice daily (50 mg twice daily if >85 kg) (ESC 2026). ACC/AHA 2022: use one of these three mortality-proven beta-blockers (1, A).",
                    cautions = listOf(
                        "Before starting: 12-lead ECG; do not start with 2nd/3rd-degree heart block without a pacemaker, or HR <50/min (NICE).",
                        "Start when euvolaemic and stable; seek advice if NYHA IV, exacerbation within 4 weeks, or persisting congestion (ESC 2026).",
                        "Monitor: heart rate, BP, symptoms and weight after each step; without close monitoring, double the dose no sooner than every 2 weeks (ESC 2026).",
                        "Do not stop suddenly — risk of rebound ischaemia, infarction and arrhythmia (ESC 2026).",
                        "ICMR: start after decongestion if BP >100 mmHg and HR >60/min; carvedilol 3.125–25 mg BD, metoprolol 12.5–50 mg BD, bisoprolol 1.25–10 mg OD.",
                    ),
                    citations = listOf(
                        cite(HC_ESC_HF, "Table 11; Suppl. Table S6"), cite(HC_ACC_HF, "Section 7.3.2"),
                        cite(HC_NICE_HF, "1.7.10–1.7.12"), cite(HC_ICMR_HF, "Common drugs and dosage"),
                    ),
                ),
                Drug(
                    "Spironolactone (MRA)",
                    "ESC 2026: start 12.5–25 mg once daily, target 50 mg once daily (eplerenone 25 → 50 mg). ACC/AHA 2022: 25 mg daily, 50 mg after a month; halve if eGFR 31–49. ICMR: 25–50 mg once daily.",
                    maxDose = "50 mg once daily (target)",
                    cautions = listOf(
                        "Before starting: K⁺ and eGFR. ACC/AHA 2022: do not start if eGFR ≤30 or K⁺ ≥5.0. ESC 2026: seek advice if K⁺ >5.0 or eGFR <30.",
                        "Monitor (ESC 2026): urea, creatinine, K⁺ 1–2 weeks after starting and after final titration, then every 4–6 months.",
                        "Monitor (ACC/AHA 2022): about 1 week, then 4 weeks, then every 6 months after starting or increasing. ICMR: K⁺ and creatinine every 2–4 weeks.",
                        "K⁺ >5.5 mmol/L: halve the dose; K⁺ >6.0: stop at once and seek advice (ESC 2026). ACC/AHA: stop if K⁺ cannot be kept <5.5.",
                        "eGFR falls by >30%: halve the dose and monitor closely (ESC 2026).",
                        "Avoid K⁺ supplements, K⁺-sparing diuretics, NSAIDs, trimethoprim and K⁺-rich salt substitutes (ESC 2026).",
                    ),
                    citations = listOf(
                        cite(HC_ESC_HF, "Recommendation Table 5; Table 11; Suppl. Table S8"), cite(HC_ACC_HF, "Section 7.3.3; Table 14"),
                        cite(HC_ICMR_HF, "Common drugs and dosage"),
                    ),
                ),
                Drug(
                    "Dapagliflozin (SGLT2 inhibitor)",
                    "10 mg once daily — starting and target dose (ESC 2026; ACC/AHA 2022). Recommended with or without type 2 diabetes (ACC/AHA 2022: 1, A).",
                    cautions = listOf(
                        "Before starting: check eGFR; do not use if eGFR <20 (ESC 2026). Seek advice if symptomatic hypotension with SBP <95 mmHg.",
                        "Monitor: renal function regularly — a mild early eGFR dip is expected; review fluid status and diuretic dose (ESC 2026).",
                        "Ketoacidosis (glucose may be <300 mg/dL) and genital infections; not for type 1 diabetes; review insulin or sulfonylurea (ESC 2026).",
                    ),
                    citations = listOf(cite(HC_ESC_HF, "Table 11; Suppl. Table S9"), cite(HC_ACC_HF, "Section 7.3.4; Table 14")),
                ),
                Drug(
                    "Empagliflozin (SGLT2 inhibitor)",
                    "10 mg once daily — starting and target dose (ESC 2026; ACC/AHA 2022).",
                    cautions = listOf("Before starting and monitoring: as for dapagliflozin (ESC 2026)."),
                    citations = listOf(cite(HC_ESC_HF, "Table 11; Suppl. Table S9"), cite(HC_ACC_HF, "Table 14")),
                ),
            ),
        ),
        Tier(
            "Congestion · loop diuretic (any LVEF)",
            "ESC 2026: dose dynamically to volume status (I, A), using the lowest dose that keeps the patient euvolaemic.",
            listOf(
                Drug(
                    "Furosemide",
                    "ESC 2026: start 20–40 mg, usual 40–240 mg/day. ACC/AHA 2022: 20–40 mg once or twice daily. ICMR: 20–80 mg/day orally; 10–80 mg IV as needed when acute.",
                    maxDose = "600 mg/day (ACC/AHA 2022)",
                    cautions = listOf(
                        "Before starting: creatinine/eGFR and electrolytes. Seek advice if K⁺ ≤3.5 mmol/L, eGFR <30 or SBP <90 mmHg (ESC 2026).",
                        "Monitor: urea, creatinine, K⁺ and Na⁺ 1–2 weeks after starting and after each increase (ESC 2026). ICMR also checks uric acid.",
                        "Not indicated if there have never been symptoms or signs of congestion; avoid long-term thiazides in stable patients (ESC 2026).",
                        "ACC/AHA 2022: add a thiazide (e.g. metolazone) only if moderate- or high-dose loop diuretic fails (1, B-NR).",
                    ),
                    citations = listOf(
                        cite(HC_ESC_HF, "Recommendation Table 5; Suppl. Table S7"), cite(HC_ACC_HF, "Section 7.2; Table 12"),
                        cite(HC_ICMR_HF, "Common drugs and dosage"),
                    ),
                ),
                Drug(
                    "Torasemide (torsemide)",
                    "ESC 2026: start 5–10 mg, usual 10–20 mg/day. ACC/AHA 2022: 10–20 mg once daily.",
                    maxDose = "200 mg/day (ACC/AHA 2022)",
                    cautions = listOf("Before starting and monitoring: as for furosemide (ESC 2026)."),
                    citations = listOf(cite(HC_ESC_HF, "Suppl. Table S7"), cite(HC_ACC_HF, "Table 12")),
                ),
            ),
        ),
        Tier(
            "HFpEF (LVEF ≥50%)",
            "ESC 2026: foundational therapy is an SGLT2 inhibitor plus an MRA, with diuretics for congestion.",
            listOf(
                Drug(
                    "Dapagliflozin or empagliflozin",
                    "10 mg once daily. ESC 2026: class I at any LVEF. ACC/AHA 2022: can be beneficial in HFpEF (2a, B-R). NICE: consider with an MRA.",
                    cautions = listOf("Before starting and monitoring: as in HFrEF (eGFR, BP, volume status) (ESC 2026)."),
                    citations = listOf(
                        cite(HC_ESC_HF, "Recommendation Table 5; Suppl. Table S9"), cite(HC_ACC_HF, "Section 7.7.1"), cite(HC_NICE_HF, "1.5.5"),
                    ),
                ),
                Drug(
                    "Spironolactone or finerenone (MRA)",
                    "ESC 2026: steroidal or non-steroidal MRA, class I. Finerenone: eGFR ≤60 start 10 → 20 mg once daily; eGFR >60 start 20 → 40 mg. ACC/AHA 2022: may be considered (2b).",
                    cautions = listOf(
                        "Before starting and monitoring: K⁺ and eGFR as for spironolactone in HFrEF; finerenone — seek advice if eGFR <25 (ESC 2026).",
                    ),
                    citations = listOf(
                        cite(HC_ESC_HF, "Recommendation Table 5; Suppl. Tables S3, S8"), cite(HC_ACC_HF, "Section 7.7.1"),
                        cite(HC_NICE_HF, "1.5.5–1.5.6"),
                    ),
                ),
                Drug(
                    "Other measures",
                    "ACC/AHA 2022: titrate antihypertensives to guideline BP targets (1, C-LD). ESC 2026: ACE-I/ARB/ARNI may be considered (IIb, C); semaglutide or tirzepatide if LVEF ≥45% and BMI ≥30 (IIa, B1).",
                    citations = listOf(cite(HC_ACC_HF, "Section 7.7.1"), cite(HC_ESC_HF, "Recommendation Table 5; Section 10.1")),
                ),
            ),
        ),
        Tier(
            "India · ICMR workflow (2026)",
            "Lists ARNI (not ACE-I) as the RAAS drug and uses LVEF <35% to confirm HFrEF.",
            listOf(
                Drug(
                    "Primary health centre",
                    "Rule out a respiratory cause; if likely heart failure, decongest with furosemide; refer if any red flag (see below).",
                    citations = listOf(cite(HC_ICMR_HF, "Management at PHC")),
                ),
                Drug(
                    "Community health centre",
                    "Admit and stabilise; IV furosemide; oxygen if SpO2 <90%; start oral ARNI and spironolactone; beta-blocker after decongestion; SGLT2 inhibitor.",
                    citations = listOf(cite(HC_ICMR_HF, "Management at CHC")),
                ),
                Drug(
                    "Tertiary hospital",
                    "Optimise furosemide, ARNI, beta-blocker and spironolactone; consider ivabradine, vericiguat or digoxin; pneumococcal and influenza vaccines; CRT if NYHA II–III, EF <35%, sinus rhythm, QRS >150 ms with LBBB, after >3 months of optimal therapy.",
                    citations = listOf(cite(HC_ICMR_HF, "Management at tertiary hospital")),
                ),
            ),
        ),
    ),
    avoid = listOf(
        Caution(
            "Diltiazem or verapamil in HFrEF",
            "Potentially harmful (negative inotropic action); stop other calcium-channel blockers unless absolutely necessary (ESC 2026).",
            listOf(cite(HC_ESC_HF, "Suppl. Tables S4 and S6, footnotes")),
        ),
        Caution(
            "NSAIDs",
            "Avoid unless essential: worsen kidney function and K⁺ with RAAS drugs and cause diuretic resistance (ESC 2026).",
            listOf(cite(HC_ESC_HF, "Suppl. Tables S4, S7, S8")),
        ),
        Caution(
            "ACE-I + ARB + MRA together",
            "Not recommended — higher risk of hyperkalaemia and renal dysfunction than an MRA with a single RAAS blocker (ESC 2026).",
            listOf(cite(HC_ESC_HF, "Suppl. Table S8")),
        ),
        Caution(
            "ARNI with an ACE inhibitor",
            "Never together or within 36 hours of the last ACE-I dose, and not with any history of angio-oedema (ACC/AHA 2022: 3, Harm).",
            listOf(cite(HC_ACC_HF, "Section 7.3.1, recommendations 7–8"), cite(HC_ESC_HF, "Suppl. Table S5")),
        ),
        Caution(
            "Potassium-containing salt substitutes",
            "NICE: advise people with heart failure to avoid them.",
            listOf(cite(HC_NICE_HF, "1.9.11")),
        ),
    ),
    escalate = listOf(
        "ICMR (PHC): refer if BP <90 or >200 mmHg, HR <50 or >120/min, RR >30/min or cyanosis, oliguria, or altered sensorium.",
        "ICMR: refer to tertiary care if HF uncontrolled, haemodynamics unstable, ongoing ischaemia suspected, abnormal electrolytes or renal function, structural disease, or unclear cause.",
        "ESC 2026: seek specialist advice before starting ACE-I, ARNI or beta-blocker in NYHA IV, advanced HF, or a current/recent exacerbation.",
        "NICE: if eGFR <30, the specialist heart failure team should consider liaising with a renal physician.",
    ),
    citations = listOf(
        cite(HC_ESC_HF, "Suppl. Tables S4–S6"), cite(HC_ACC_HF), cite(HC_NICE_HF, "1.6.2"), cite(HC_ICMR_HF, "Refer if following"),
    ),
)

// ============================================================================ Atrial fibrillation
private const val HC_ACC_AF = "sc-acc-aha-af-2023"
private const val HC_ESC_AF = "sc-esc-af-2024"
private const val HC_NICE_AF = "sc-nice-ng196"
private const val HC_EHRA = "hc-ehra-noac-2021"
private const val HC_ICMR_AF = "hc-icmr-stw-af-2026"

private const val HC_DOAC_BEFORE = "Before starting: blood count, creatinine with Cockcroft–Gault CrCl, and liver function (EHRA 2021)."
private const val HC_DOAC_MONITOR = "Monitor: blood count, renal and liver function yearly; 4-monthly if ≥75 years or frail; if CrCl ≤60, every CrCl/10 months (EHRA 2021)."

private val hcAtrialFibrillation = Condition(
    id = "atrial-fibrillation",
    title = "Atrial fibrillation",
    summary = "CHA₂DS₂-VASc vs CHA₂DS₂-VA, DOAC doses by CrCl, INR, rate control, lab monitoring",
    keywords = listOf(
        "atrial fibrillation", "AF", "AFib", "A-fib", "irregular pulse", "irregularly irregular", "palpitations",
        "anticoagulation", "anticoagulant", "blood thinner", "DOAC", "NOAC", "OAC", "warfarin", "VKA", "INR",
        "apixaban", "rivaroxaban", "dabigatran", "edoxaban", "CHA2DS2-VASc", "CHA2DS2-VA", "HAS-BLED", "ORBIT",
        "stroke prevention", "rate control", "digoxin", "creatinine clearance", "CrCl",
    ),
    population = Population.General,
    tool = ToolKind.Renal,
    info = listOf(
        InfoBlock(
            "Stroke risk — when to anticoagulate",
            listOf(
                "ACC/AHA 2023: anticoagulate if annual risk ≥2% — e.g. CHA₂DS₂-VASc ≥2 in men or ≥3 in women (1, A).",
                "ACC/AHA 2023: anticoagulation is reasonable at 1–<2% per year — score 1 in men, 2 in women (2a, A).",
                "ESC 2024 uses CHA₂DS₂-VA (no sex point): score ≥2 — anticoagulation recommended (I, C); score 1 — should be considered (IIa, C).",
                "ESC 2024: anticoagulate AF with hypertrophic cardiomyopathy or cardiac amyloidosis whatever the score (I, B).",
                "NICE: offer a DOAC at CHA₂DS₂-VASc ≥2; consider for men scoring 1; not under 65 with no risk factor other than sex.",
                "ICMR: oral anticoagulant if CHA₂DS₂-VASc >1.",
                "Do not decide by AF pattern — paroxysmal, persistent or permanent (ESC 2024: III, B).",
                "Calculator: CHA₂DS₂-VASc in Scores; CHA₂DS₂-VA is the same total without the sex point.",
            ),
            listOf(
                cite(HC_ACC_AF, "Section 6.3.1, recommendations 1 and 3"), cite(HC_ESC_AF, "Recommendation Table 6; Table 10"),
                cite(HC_NICE_AF, "1.6.3, 1.6.4, 1.6.7"), cite(HC_ICMR_AF, "Stroke risk score"),
            ),
        ),
        InfoBlock(
            "Bleeding risk",
            listOf(
                "ESC 2024: assess and manage modifiable bleeding risk factors in everyone eligible for anticoagulation (I, B).",
                "Bleeding scores should not decide anticoagulation (ESC 2024: III, B) or be used alone for eligibility (ACC/AHA 2023: 3, No Benefit); use them to fix modifiable factors.",
                "NICE prefers the ORBIT score; modify uncontrolled BP, poor INR control, antiplatelets/SSRIs/NSAIDs, harmful alcohol and reversible anaemia.",
                "NICE: do not withhold anticoagulation solely because of age or risk of falls.",
                "ICMR: HAS-BLED >3 = high risk; keep SBP <140 mmHg, avoid aspirin/antiplatelets, NSAIDs and alcohol, correct anaemia.",
                "Calculator: HAS-BLED in Scores.",
            ),
            listOf(
                cite(HC_ESC_AF, "Recommendation Table 12"), cite(HC_ACC_AF, "Section 6.1, recommendation 4"),
                cite(HC_NICE_AF, "1.2.2, 1.2.3, 1.6.8"), cite(HC_ICMR_AF, "Bleeding risk score"),
            ),
        ),
        InfoBlock(
            "Tests before starting an anticoagulant",
            listOf(
                "ESC 2024: kidney function, electrolytes, liver function, full blood count, glucose/HbA1c and thyroid tests at diagnosis.",
                "ESC 2024: transthoracic echo where it will guide management; limited access to echo should not delay anticoagulation.",
                "ACC/AHA 2023: complete blood count, metabolic panel and thyroid function, with echocardiography, in newly diagnosed AF (1, B-NR).",
                "EHRA 2021: know kidney and liver function before a DOAC; estimate CrCl by Cockcroft–Gault, not MDRD or CKD-EPI; record a baseline blood count.",
                "ACC/AHA 2023: dose DOACs by age, renal function, weight and interacting drugs. Child-Pugh B: apixaban, dabigatran or edoxaban (2a); Child-Pugh C: avoid DOACs.",
                "ICMR: haemogram, blood sugar, creatinine, electrolytes, ECG; desirable: chest X-ray, thyroid, liver function, troponin, PT/INR, echo.",
            ),
            listOf(
                cite(HC_ESC_AF, "Section 3.4; Table 8"), cite(HC_ACC_AF, "Section 4.2.1, recommendation 1; Section 10.16; Table 13"),
                cite(HC_EHRA, "Practical considerations for initiation and follow-up"), cite(HC_ICMR_AF, "Investigations"),
            ),
        ),
        InfoBlock(
            "Monitoring on anticoagulation",
            listOf(
                "EHRA 2021: blood count, renal and liver function yearly; every 4 months if ≥75 years (especially on dabigatran) or frail.",
                "EHRA 2021: if CrCl ≤60 mL/min, recheck at least every CrCl/10 months (CrCl 30 → 3-monthly); sooner with infection, dehydration or NSAIDs.",
                "NICE: review the need for and quality of anticoagulation at least annually, including renal function in renal impairment.",
                "Warfarin: target INR 2.0–3.0 (ESC 2024: I, B; ACC/AHA 2023: 1, B-R); keep time in therapeutic range >70% (ESC: IIa, A).",
                "ESC 2024: switch to a DOAC if time in therapeutic range is <70% (I, B); reassess stroke risk periodically (I, B).",
            ),
            listOf(
                cite(HC_EHRA, "Table 3, item 6"), cite(HC_NICE_AF, "1.6.17"), cite(HC_ESC_AF, "Recommendation Tables 6 and 7"),
                cite(HC_ACC_AF, "Section 6.3.1.1, recommendation 2"),
            ),
        ),
        InfoBlock(
            "Rate control targets",
            listOf(
                "ESC 2024: lenient control — resting HR <110 bpm as the initial target; stricter if AF symptoms continue (IIa, B).",
                "ACC/AHA 2023 (no heart failure): resting HR generally <100–110 bpm, guided by symptoms (2a, B-R).",
                "ICMR: aim for HR <110/min.",
            ),
            listOf(
                cite(HC_ESC_AF, "Recommendation Table 14"), cite(HC_ACC_AF, "Section 7.1, recommendation 2"),
                cite(HC_ICMR_AF, "Heart rate control"),
            ),
        ),
    ),
    tiers = listOf(
        Tier(
            "Anticoagulant choice",
            "DOAC preferred over warfarin except with a mechanical heart valve or moderate–severe mitral stenosis (ESC 2024: I, A; ACC/AHA 2023: 1, A). Use the full dose unless reduction criteria are met.",
            listOf(
                Drug(
                    "Apixaban",
                    "5 mg twice daily. 2.5 mg twice daily if ≥2 of: age ≥80 years, weight ≤60 kg, serum creatinine ≥1.5 mg/dL (≥133 µmol/L) (ESC 2024; ACC/AHA 2023).",
                    cautions = listOf(
                        HC_DOAC_BEFORE,
                        HC_DOAC_MONITOR,
                        "Strong CYP3A4 + P-gp inhibitors (ketoconazole, itraconazole, ritonavir): reduce 5 to 2.5 mg twice daily; avoid if already on 2.5 mg (ACC/AHA 2023).",
                        "Avoid with inducers — rifampicin, carbamazepine, phenytoin, St John's wort (ACC/AHA 2023).",
                        "ACC/AHA 2023: CrCl <15 or dialysis — warfarin or an evidence-based apixaban dose might be reasonable (2b, B-NR).",
                    ),
                    citations = listOf(
                        cite(HC_ESC_AF, "Table 11"), cite(HC_ACC_AF, "Table 13; Section 6.8.4"), cite(HC_EHRA, "Table 3"),
                    ),
                ),
                Drug(
                    "Rivaroxaban",
                    "20 mg once daily with the largest meal; 15 mg once daily if CrCl 15–50 mL/min (ACC/AHA 2023) or 15–49 mL/min (ESC 2024).",
                    cautions = listOf(
                        HC_DOAC_BEFORE,
                        HC_DOAC_MONITOR,
                        "Avoid with strong CYP3A4 + P-gp inhibitors (systemic ketoconazole, ritonavir) and with inducers such as rifampicin (ACC/AHA 2023).",
                        "Child-Pugh B: contraindicated (ACC/AHA 2023: 3, Harm); Child-Pugh C: avoid (ACC/AHA 2023 Table 13).",
                    ),
                    citations = listOf(cite(HC_ESC_AF, "Table 11"), cite(HC_ACC_AF, "Table 13; Section 10.16, recommendation 3"), cite(HC_EHRA, "Table 3")),
                ),
                Drug(
                    "Dabigatran",
                    "150 mg twice daily; 110 mg twice daily if age ≥80 or on verapamil; consider 110 mg if 75–80 years, CrCl 30–50, gastritis/oesophagitis/reflux, or high bleeding risk (ESC 2024).",
                    cautions = listOf(
                        HC_DOAC_BEFORE,
                        HC_DOAC_MONITOR,
                        "ESC 2024: contraindicated if eGFR <30 — the most renally cleared DOAC (80% renal excretion, ACC/AHA 2023).",
                        "ACC/AHA 2023 (US label): 75 mg twice daily if CrCl 15–30, or CrCl 30–50 with dronedarone or ketoconazole.",
                    ),
                    citations = listOf(cite(HC_ESC_AF, "Table 11; Section 6.2.1"), cite(HC_ACC_AF, "Table 13"), cite(HC_EHRA, "Table 3")),
                ),
                Drug(
                    "Edoxaban",
                    "60 mg once daily; 30 mg once daily if CrCl 15–50 mL/min, weight ≤60 kg, or on ciclosporin, dronedarone, erythromycin or ketoconazole (ESC 2024).",
                    cautions = listOf(
                        HC_DOAC_BEFORE,
                        HC_DOAC_MONITOR,
                        "ACC/AHA 2023 (US label): 60 mg if CrCl >50 to ≤95 mL/min; 30 mg if CrCl 15–50.",
                    ),
                    citations = listOf(cite(HC_ESC_AF, "Table 11"), cite(HC_ACC_AF, "Table 13"), cite(HC_EHRA, "Table 3")),
                ),
                Drug(
                    "Warfarin",
                    "Target INR 2.0–3.0 (ESC 2024: I, B; ACC/AHA 2023: 1, B-R). The anticoagulant for mechanical valves or moderate–severe mitral stenosis; ICMR: VKA only for severe MS or a metallic valve.",
                    cautions = listOf(
                        "Before starting: baseline PT/INR (ICMR), with blood count, kidney and liver function (ESC 2024).",
                        "Monitor: routine INR checks; keep time in range >70% (ESC 2024: IIa, A); switch to a DOAC if below 70% (I, B).",
                        "Interactions: reduce dose with amiodarone, metronidazole or sulphonamides; increase with carbamazepine (ESC 2024 Figure 9).",
                        "ESC 2024: if ≥75 years, stable on warfarin with polypharmacy, staying on it may be considered (IIb, B).",
                    ),
                    citations = listOf(
                        cite(HC_ESC_AF, "Recommendation Table 7; Figure 9; Section 3.4"), cite(HC_ACC_AF, "Section 6.3.1.1"),
                        cite(HC_ICMR_AF, "Choice of anti-coagulation; Investigations"),
                    ),
                ),
            ),
        ),
        Tier(
            "Rate control",
            "ESC 2024: LVEF >40% — beta-blocker, diltiazem, verapamil or digoxin (I, B); LVEF ≤40% — beta-blocker and/or digoxin (I, B).",
            listOf(
                Drug(
                    "Metoprolol, bisoprolol or carvedilol",
                    "Oral maintenance (ESC 2024): metoprolol tartrate 25–100 mg twice daily or succinate 50–200 mg once daily; bisoprolol 1.25–20 mg once daily; carvedilol 3.125–50 mg twice daily.",
                    cautions = listOf(
                        "NICE: a standard beta-blocker (not sotalol) or a rate-limiting calcium-channel blocker is first-line monotherapy.",
                        "ICMR: start metoprolol, diltiazem or verapamil if HR >110/min without heart failure; beta-blocker plus digoxin in heart failure.",
                    ),
                    citations = listOf(
                        cite(HC_ESC_AF, "Table 12; Recommendation Table 14"), cite(HC_NICE_AF, "1.7.2"),
                        cite(HC_ICMR_AF, "Management at PHC/CHC; Heart rate control"),
                    ),
                ),
                Drug(
                    "Diltiazem or verapamil (LVEF >40% only)",
                    "Diltiazem 60 mg three times daily up to 360 mg extended-release once daily; verapamil 40 mg twice daily up to 480 mg extended-release once daily (ESC 2024).",
                    cautions = listOf(
                        "Contraindicated if LVEF ≤40% (ESC 2024); ACC/AHA 2023: not with LVEF <40% (3: Harm).",
                        "Combine with a beta-blocker only in secondary care with 24-h ECG checks for bradycardia (ESC 2024).",
                        "Adapt doses in hepatic and renal impairment (ESC 2024).",
                    ),
                    citations = listOf(cite(HC_ESC_AF, "Table 12; Section 7.1.3"), cite(HC_ACC_AF, "Section 7.2.2, recommendation 4")),
                ),
                Drug(
                    "Digoxin",
                    "0.0625–0.25 mg once daily (ESC 2024). NICE: consider as monotherapy in non-paroxysmal AF if sedentary. ACC/AHA 2023: if levels are measured, target <1.2 ng/mL (2a).",
                    cautions = listOf(
                        "Before starting: check renal function; adapt the dose in CKD (ESC 2024).",
                        "Monitor: serum levels help avoid toxicity, especially with older age, renal dysfunction or interacting drugs (ESC 2024).",
                    ),
                    citations = listOf(
                        cite(HC_ESC_AF, "Table 12; Section 7.1.3"), cite(HC_NICE_AF, "1.7.4"), cite(HC_ACC_AF, "Section 7.2.2, recommendation 2"),
                    ),
                ),
                Drug(
                    "If one drug is not enough",
                    "Combination rate control should be considered, avoiding bradycardia (ESC 2024: IIa, C). NICE: any two of a beta-blocker, diltiazem and digoxin.",
                    citations = listOf(cite(HC_ESC_AF, "Recommendation Table 14"), cite(HC_NICE_AF, "1.7.5")),
                ),
            ),
        ),
    ),
    avoid = listOf(
        Caution(
            "Aspirin, or aspirin + clopidogrel, for stroke prevention",
            "Not an alternative to anticoagulation (ESC 2024: III, A; ACC/AHA 2023: 3, Harm). NICE: no aspirin monotherapy for stroke prevention.",
            listOf(cite(HC_ESC_AF, "Recommendation Table 6"), cite(HC_ACC_AF, "Section 6.3.1, recommendation 4"), cite(HC_NICE_AF, "1.6.14")),
        ),
        Caution(
            "DOACs with a mechanical valve or moderate–severe mitral stenosis",
            "Use a vitamin K antagonist instead (ESC 2024; ACC/AHA 2023).",
            listOf(cite(HC_ESC_AF, "Recommendation Table 7; Section 6.2.1"), cite(HC_ACC_AF, "Section 6.3.1, recommendation 2")),
        ),
        Caution(
            "Reduced DOAC doses without the criteria",
            "Under-dosing raises stroke risk without reducing bleeding (ESC 2024: III, B; ACC/AHA 2023: 3, Harm).",
            listOf(cite(HC_ESC_AF, "Recommendation Table 7; Section 6.2.1"), cite(HC_ACC_AF, "Section 6.3.1.1, recommendation 3")),
        ),
        Caution(
            "Diltiazem or verapamil with reduced LVEF",
            "May worsen heart failure: contraindicated at LVEF ≤40% (ESC 2024); not with LVEF <40% (ACC/AHA 2023: 3, Harm).",
            listOf(cite(HC_ESC_AF, "Table 12"), cite(HC_ACC_AF, "Section 7.2.2, recommendation 4")),
        ),
        Caution(
            "Amiodarone for long-term rate control",
            "NICE: do not offer.",
            listOf(cite(HC_NICE_AF, "1.7.6")),
        ),
    ),
    escalate = listOf(
        "ICMR: immediate intervention for haemodynamic instability (low systolic BP), HR >150 or <50/min, ongoing angina, heart failure, TIA/stroke, or major bleed on anticoagulant.",
        "ICMR (district): if haemodynamically unstable, immediate cardioversion after heparin; refer if HR uncontrolled, heart failure or angina.",
        "Active bleeding on anticoagulant: interrupt it and find the cause (ESC 2024: I, C); life-threatening bleed — PCC for warfarin, specific antidote for a DOAC (IIa).",
        "NICE: refer within 4 weeks if treatment fails to control symptoms or AF recurs after cardioversion.",
    ),
    citations = listOf(
        cite(HC_ACC_AF), cite(HC_ESC_AF, "Recommendation Table 13"), cite(HC_NICE_AF, "1.5.1"),
        cite(HC_ICMR_AF, "Immediate intervention indicators; Management"), cite(HC_EHRA),
    ),
)

// ======================================================================= Chronic coronary disease
private const val HC_ESC_CCS = "hc-esc-ccs-2024"
private const val HC_ACC_CCD = "hc-acc-aha-ccd-2023"
private const val HC_ACC_LIPID = "hc-acc-aha-lipid-2026"
private const val HC_ICMR_ANGINA = "hc-icmr-stw-angina-2026"

private const val HC_STATIN_MONITOR = "Monitor: fasting lipids 4–12 weeks after starting or changing the dose, then every 3–12 months as needed (ACC/AHA 2023: 1, A)."

private val hcChronicCoronaryDisease = Condition(
    id = "chronic-coronary-disease",
    title = "Chronic coronary disease",
    summary = "Antiplatelets, statins, LDL and BP targets, anti-anginals — ESC 2024 and ACC/AHA 2023",
    keywords = listOf(
        "chronic coronary disease", "CCD", "chronic coronary syndrome", "CCS", "coronary artery disease", "CAD",
        "ischaemic heart disease", "ischemic heart disease", "IHD", "angina", "stable angina", "chest pain", "post-MI",
        "myocardial infarction", "heart attack", "secondary prevention", "PCI", "stent", "angioplasty", "CABG", "bypass",
        "aspirin", "clopidogrel", "statin", "atorvastatin", "rosuvastatin", "LDL", "cholesterol", "nitrate", "nitroglycerin",
        "GTN", "beta-blocker", "amlodipine", "ranolazine",
    ),
    population = Population.General,
    info = listOf(
        InfoBlock(
            "Targets",
            listOf(
                "ESC 2024: LDL-C goal <1.4 mmol/L (55 mg/dL) plus ≥50% reduction from baseline (I, A).",
                "ESC 2024: LDL-C <1.0 mmol/L (40 mg/dL) may be considered after a recurrent event on maximal statin (IIb, B).",
                "ACC/AHA 2023: high-intensity statin for ≥50% LDL-C reduction (1, A); moderate intensity for 30–49% if high intensity not tolerated (1, A).",
                "ACC/AHA 2023 lipid advice was adapted from the 2018 cholesterol guideline, now replaced by the 2026 ACC/AHA dyslipidaemia guideline (not summarised here).",
                "BP — ESC 2024: treated systolic 120–129 mmHg if tolerated; more lenient (e.g. 140/90) at ≥85 years or with symptomatic orthostatic hypotension.",
                "BP — ACC/AHA 2023: <130/<80 mmHg in CCD with hypertension (1, B-R).",
                "Diabetes — ESC 2024: HbA1c <7.0% (53 mmol/mol).",
            ),
            listOf(
                cite(HC_ESC_CCS, "Recommendation Table 18; Table 7; Section 5.3.5"), cite(HC_ACC_CCD, "Sections 4.2.6–4.2.7"),
                cite(HC_ACC_LIPID, "Abstract"),
            ),
        ),
        InfoBlock(
            "Tests",
            listOf(
                "ESC 2024, initial assessment (class I): lipid profile with LDL-C, full blood count, creatinine with eGFR, HbA1c and/or fasting glucose; thyroid function once.",
                "ACC/AHA 2023: fasting lipids 4–12 weeks after starting or changing a statin, then every 3–12 months as needed (1, A).",
                "ICMR essential: haemogram, urea, creatinine, electrolytes, sugar, lipids, ECG. Others: echo, treadmill test, thyroid, iron profile, uric acid, HbA1c.",
                "ACC/AHA 2023: symptoms or capacity changing despite treatment — stress imaging (PET/SPECT, CMR or stress echo) or invasive angiography (1).",
            ),
            listOf(
                cite(HC_ESC_CCS, "Recommendation Table 2"), cite(HC_ACC_CCD, "Section 3.1, recommendations 1–2; Section 4.2.6, recommendation 3"),
                cite(HC_ICMR_ANGINA, "Investigations"),
            ),
        ),
    ),
    tiers = listOf(
        Tier(
            "Antiplatelet therapy", null,
            listOf(
                Drug(
                    "Aspirin",
                    "75–100 mg once daily, lifelong, after MI, PCI or CABG (ESC 2024: I, A) and with significant obstructive CAD without MI or revascularisation (I, B). ACC/AHA 2023: 81 mg (75–100) (1, A). ICMR: 75 mg OD.",
                    citations = listOf(
                        cite(HC_ESC_CCS, "Recommendation Table 17"), cite(HC_ACC_CCD, "Section 4.3.1, recommendation 1"),
                        cite(HC_ICMR_ANGINA, "Drugs and dosage"),
                    ),
                ),
                Drug(
                    "Clopidogrel",
                    "75 mg once daily. ESC 2024: a safe and effective alternative to aspirin after MI or remote PCI (I, A). ACC/AHA 2023 and ICMR: if aspirin is not tolerated.",
                    citations = listOf(
                        cite(HC_ESC_CCS, "Recommendation Table 17"), cite(HC_ACC_CCD, "Section 4.3.1, supportive text 1"),
                        cite(HC_ICMR_ANGINA, "Drugs and dosage"),
                    ),
                ),
                Drug(
                    "After PCI (no anticoagulant)",
                    "Aspirin 75–100 mg + clopidogrel 75 mg for up to 6 months, then single antiplatelet (ESC 2024: I, A; ACC/AHA 2023: 1, A). High bleeding risk: stop DAPT at 1–3 months (ESC: I, A).",
                    citations = listOf(cite(HC_ESC_CCS, "Recommendation Table 17"), cite(HC_ACC_CCD, "Section 4.3.1, recommendation 2")),
                ),
                Drug(
                    "If an anticoagulant is also needed (e.g. AF)",
                    "ESC 2024: long term, an AF-dose anticoagulant alone, preferably a DOAC (I, B). ACC/AHA 2023: DOAC monotherapy may be considered without an acute antiplatelet indication (2b, C-LD).",
                    citations = listOf(cite(HC_ESC_CCS, "Recommendation Table 17"), cite(HC_ACC_CCD, "Section 4.3.1, recommendation 14")),
                ),
            ),
        ),
        Tier(
            "Lipid lowering",
            "ESC 2024: high-intensity statin up to the highest tolerated dose for all (I, A); high intensity = atorvastatin ≥40 mg or rosuvastatin ≥20 mg daily.",
            listOf(
                Drug(
                    "Atorvastatin",
                    "High intensity: 40–80 mg once daily (ACC/AHA 2023; ICMR 40–80 mg OD).",
                    cautions = listOf(HC_STATIN_MONITOR, "Not when pregnancy is planned, during pregnancy or breastfeeding (ESC 2024)."),
                    citations = listOf(
                        cite(HC_ESC_CCS, "Recommendation Table 18; Section 4.3.2"), cite(HC_ACC_CCD, "Section 4.2.6; Table 11"),
                        cite(HC_ICMR_ANGINA, "Drugs and dosage"),
                    ),
                ),
                Drug(
                    "Rosuvastatin",
                    "High intensity: 20–40 mg once daily (ACC/AHA 2023; ICMR 20–40 mg OD).",
                    cautions = listOf(HC_STATIN_MONITOR, "Not when pregnancy is planned, during pregnancy or breastfeeding (ESC 2024)."),
                    citations = listOf(
                        cite(HC_ESC_CCS, "Recommendation Table 18; Section 4.3.2"), cite(HC_ACC_CCD, "Section 4.2.6; Table 11"),
                        cite(HC_ICMR_ANGINA, "Drugs and dosage"),
                    ),
                ),
                Drug(
                    "Ezetimibe (add-on)",
                    "ESC 2024: add if the LDL-C goal is not reached on the maximum tolerated statin (I, B).",
                    citations = listOf(cite(HC_ESC_CCS, "Recommendation Table 18")),
                ),
                Drug(
                    "PCSK9 inhibitor or bempedoic acid",
                    "ESC 2024: PCSK9 inhibitor if not at goal on statin + ezetimibe (I, A); bempedoic acid if statin-intolerant and not at goal on ezetimibe (I, B).",
                    citations = listOf(cite(HC_ESC_CCS, "Recommendation Table 18")),
                ),
            ),
        ),
        Tier(
            "Angina relief",
            "ESC 2024: tailor drugs to heart rate, BP, LV function and comorbidities (I, C); start with a beta-blocker and/or calcium-channel blocker for most (I, B).",
            listOf(
                Drug(
                    "Sublingual nitroglycerin (GTN)",
                    "Tablet or spray for immediate relief of angina (ESC 2024: I, B; ACC/AHA 2023: 1, B-NR).",
                    citations = listOf(cite(HC_ESC_CCS, "Recommendation Table 16"), cite(HC_ACC_CCD, "Section 4.3.6, recommendation 4")),
                ),
                Drug(
                    "Metoprolol",
                    "ICMR: short-acting 25–100 mg twice daily or long-acting 25–100 mg once daily. ESC 2024: for angina, aim for resting HR 55–60/min.",
                    cautions = listOf("Not with sick sinus syndrome or AV conduction disorders; caution in PAD and COPD (ESC 2024)."),
                    citations = listOf(cite(HC_ICMR_ANGINA, "Drugs and dosage"), cite(HC_ESC_CCS, "Sections 4.2.2–4.2.3")),
                ),
                Drug(
                    "Amlodipine",
                    "5–10 mg once daily (ICMR). ESC 2024: add a dihydropyridine CCB to a beta-blocker if angina persists (IIa, B).",
                    citations = listOf(cite(HC_ICMR_ANGINA, "Drugs and dosage"), cite(HC_ESC_CCS, "Recommendation Table 16")),
                ),
                Drug(
                    "Diltiazem or verapamil",
                    "ICMR: diltiazem 30–90 mg three times daily; verapamil 40–80 mg three times daily.",
                    cautions = listOf(
                        "Avoid with significant LV dysfunction; caution with beta-blockers — bradycardia, LV dysfunction (ACC/AHA 2023).",
                        "Do not combine with ivabradine (ESC 2024: III, B).",
                    ),
                    citations = listOf(
                        cite(HC_ICMR_ANGINA, "Drugs and dosage"), cite(HC_ACC_CCD, "Section 4.3.6, supportive text"),
                        cite(HC_ESC_CCS, "Recommendation Table 16"),
                    ),
                ),
                Drug(
                    "Long-acting nitrate or ranolazine (add-on)",
                    "ESC 2024: if not controlled on beta-blocker and/or CCB (IIa, B). ACC/AHA 2023: ranolazine if still symptomatic (1, B-R). ICMR: isosorbide mononitrate 20–60 mg in 2 divided doses; ranolazine 500–1000 mg BD.",
                    citations = listOf(
                        cite(HC_ESC_CCS, "Recommendation Table 16"), cite(HC_ACC_CCD, "Section 4.3.6, recommendations 2–3"),
                        cite(HC_ICMR_ANGINA, "Drugs and dosage"),
                    ),
                ),
                Drug(
                    "Nicorandil or trimetazidine",
                    "ICMR (tertiary, if uncontrolled): nicorandil 5–10 mg BD; trimetazidine 20 mg TDS. ESC 2024: options when beta-blocker/CCB are not tolerated or not enough.",
                    citations = listOf(cite(HC_ICMR_ANGINA, "Management at tertiary level; Drugs and dosage"), cite(HC_ESC_CCS, "Section 4.2.3")),
                ),
            ),
        ),
        Tier(
            "Other secondary prevention", null,
            listOf(
                Drug(
                    "Metoprolol succinate, carvedilol or bisoprolol (LVEF <50%)",
                    "ACC/AHA 2023: beta-blocker recommended with LVEF ≤40% (1, A); with LVEF <50%, use one of these three titrated to target (1, A).",
                    cautions = listOf(
                        "After MI without LVEF ≤50%, angina, arrhythmia or uncontrolled BP: reassess need beyond 1 year (ACC/AHA 2023: 2b).",
                        "Without prior MI or LVEF ≤50%: no MACE benefit unless another indication (ACC/AHA 2023: 3, No Benefit).",
                    ),
                    citations = listOf(cite(HC_ACC_CCD, "Section 4.3.2")),
                ),
                Drug(
                    "Ramipril (ACE inhibitor)",
                    "ESC 2024: ACE-I (or ARB) with hypertension, diabetes or heart failure (I, A). ICMR: ramipril 2.5–10 mg OD or enalapril 2.5–10 mg BD.",
                    cautions = listOf("ESC 2024: not if contraindicated, e.g. severe renal impairment or hyperkalaemia."),
                    citations = listOf(cite(HC_ESC_CCS, "Section 4.3.3; Table 10"), cite(HC_ICMR_ANGINA, "Drugs and dosage")),
                ),
                Drug(
                    "SGLT2 inhibitor or GLP-1 RA (type 2 diabetes)",
                    "ESC 2024: an agent with proven cardiovascular benefit, regardless of HbA1c or other glucose-lowering drugs (I, A).",
                    citations = listOf(cite(HC_ESC_CCS, "Recommendation Table 19")),
                ),
            ),
        ),
    ),
    avoid = listOf(
        Caution(
            "Chronic NSAIDs",
            "Increase cardiovascular and bleeding complications (ACC/AHA 2023: 3, Harm).",
            listOf(cite(HC_ACC_CCD, "Section 4.3.1, recommendation 10")),
        ),
        Caution(
            "Nitrates with PDE-5 inhibitors (sildenafil, tadalafil)",
            "Risk of severe hypotension (ACC/AHA 2023: 3, Harm; ESC 2024: III, B).",
            listOf(cite(HC_ACC_CCD, "Section 4.2.5, recommendation 3"), cite(HC_ESC_CCS, "Recommendation Table 16")),
        ),
        Caution(
            "Ivabradine add-on with LVEF >40% and no heart failure",
            "Not recommended (ESC 2024: III, B); potentially harmful with normal LV function (ACC/AHA 2023: 3, Harm).",
            listOf(cite(HC_ESC_CCS, "Recommendation Table 16"), cite(HC_ACC_CCD, "Section 4.3.6, recommendation 5")),
        ),
        Caution(
            "Adding clopidogrel to aspirin without recent ACS or PCI",
            "Does not reduce MACE (ACC/AHA 2023: 3, No Benefit).",
            listOf(cite(HC_ACC_CCD, "Section 4.3.1, recommendation 7")),
        ),
    ),
    escalate = listOf(
        "Manage as acute coronary syndrome: rest angina or pain >20 min, crescendo angina, new effort angina <1 month (CCS II–III), post-infarction angina (ICMR).",
        "ICMR: refer to tertiary care if angina uncontrolled on optimal therapy, abnormal echo, unable to exercise or ECG uninterpretable, or high-risk stress test.",
        "ICMR high risk — consider angiography: angina uncontrolled on optimal therapy, high-risk non-invasive test, cardiac arrest survivor or documented VT.",
        "ACC/AHA 2023: persistent change in symptoms or capacity despite treatment — stress imaging or invasive angiography (1).",
    ),
    citations = listOf(
        cite(HC_ESC_CCS), cite(HC_ACC_CCD, "Section 3.1"), cite(HC_ICMR_ANGINA, "Categorize angina; Risk categorization; Referral"),
    ),
)

internal val heartConditions: List<Condition> = listOf(hcHeartFailure, hcAtrialFibrillation, hcChronicCoronaryDisease)
