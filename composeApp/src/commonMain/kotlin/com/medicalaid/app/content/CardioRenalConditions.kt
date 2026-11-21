package com.medicalaid.app.content

/*
 * General-adult pages: hypertension, dyslipidaemia, chronic kidney disease.
 * Every number below was checked against the cited document (reviewed November 2026).
 *
 * Notes for maintainers:
 *  - ESC content is cited only to sources whose `document` starts with "ESC", and is kept in
 *    bullets or drug lines that start with "ESC" so it can be removed cleanly.
 *  - The 2025 AHA/ACC blood-pressure and 2026 ACC/AHA dyslipidaemia full texts were not accessible
 *    for this review; their statements were checked against ACC's published summaries and an
 *    open-access ERBP commentary, which are cited alongside them.
 *  - Reuses kdigo-ckd-2024 and icmr-stw-t2dm-2022 (Sources.kt) and hc-acc-aha-lipid-2026 (HeartConditions.kt).
 */

internal val cardioRenalConditionSources: List<Source> = listOf(
    // ------------------------------------------------------------------ Hypertension
    Source(
        "gc-icmr-stw-htn-2026", Authority.ICMR, "Standard Treatment Workflow",
        "Hypertension in Adults (ICD-11 BA00)", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1778941063_hypertensioninadults_final.pdf",
        status = "May 2026; includes the IHCI-based treatment protocols",
    ),
    Source(
        "gc-ihci-glob-heart-2024", Authority.Paper, "ICMR-NIE IHCI evaluation — Glob Heart 2024;19(1):30",
        "India Hypertension Control Initiative: Blood Pressure Control Using Drug and Dose-Specific Standard Treatment Protocol at Scale in Punjab and Maharashtra, India, 2022", "2024",
        "https://doi.org/10.5334/gh.1305",
    ),
    Source(
        "gc-who-ihci-hwc-2022", Authority.WHO, "WHO India — IHCI guide for health workers",
        "Managing Hypertension at Health and Wellness Centres and Sub-centres", "2022",
        "https://cdn.who.int/media/docs/default-source/india-news/india-hypertension-control-initiative/managing-hypertension.pdf",
    ),
    Source(
        "gc-nice-ng136", Authority.NICE, "Guideline NG136",
        "Hypertension in adults: diagnosis and management", "2019",
        "https://www.nice.org.uk/guidance/ng136/chapter/Recommendations",
        status = "Last updated 26 February 2026",
    ),
    Source(
        "gc-aha-acc-hbp-2025", Authority.AHA, "AHA/ACC multisociety guideline — Circulation 2025;152(11):e114–e218",
        "2025 AHA/ACC/AANP/AAPA/ABC/ACCP/ACPM/AGS/AMA/ASPC/NMA/PCNA/SGIM Guideline for the Prevention, Detection, Evaluation and Management of High Blood Pressure in Adults", "2025",
        "https://doi.org/10.1161/CIR.0000000000001356",
        status = "Replaces the 2017 ACC/AHA guideline",
    ),
    Source(
        "gc-acc-hbp-2025-summary", Authority.Paper, "ACC Cardiology Magazine, 1 October 2025 — guideline summary",
        "New in Clinical Guidance: High Blood Pressure Focus of New ACC/AHA Guideline", "2025",
        "https://www.acc.org/latest-in-cardiology/articles/2025/10/01/01/new-in-clinical-guidance-hbp",
    ),
    Source(
        "gc-erbp-hbp-2026", Authority.Paper, "ERBP commentary — Nephrol Dial Transplant 2026;41(10):1909–1918",
        "2025 AHA/ACC guideline for the prevention, detection, evaluation and management of high blood pressure in adults: a commentary from the European Renal Best Practice (ERBP)", "2026",
        "https://doi.org/10.1093/ndt/gfag055",
    ),
    Source(
        "gc-esc-htn-2024", Authority.ESC, "ESC guideline — Eur Heart J 2024;45(38):3912–4018",
        "2024 ESC Guidelines for the management of elevated blood pressure and hypertension", "2024",
        "https://doi.org/10.1093/eurheartj/ehae178",
    ),
    // ------------------------------------------------------------------ Dyslipidaemia
    Source(
        "gc-nice-ng238", Authority.NICE, "Guideline NG238",
        "Cardiovascular disease: risk assessment and reduction, including lipid modification", "2023",
        "https://www.nice.org.uk/guidance/ng238/chapter/Recommendations",
        status = "Published 14 December 2023",
    ),
    Source(
        "gc-acc-lipid-2026-summary", Authority.Paper, "ACC Cardiology Magazine, 1 July 2026 — guideline summary",
        "Lower Sooner: How the 2026 Dyslipidemia Guideline Changes Practice", "2026",
        "https://www.acc.org/latest-in-cardiology/articles/2026/07/01/01/prioritizing-health",
    ),
    Source(
        "gc-csi-lipid-2024", Authority.Paper, "Cardiological Society of India guideline — Indian Heart J 2024;76(Suppl 1):S6–S19",
        "CSI clinical practice guidelines for dyslipidemia management: Executive summary", "2024",
        "https://doi.org/10.1016/j.ihj.2023.11.271",
    ),
    Source(
        "gc-esc-eas-lipid-2019", Authority.ESC, "ESC/EAS guideline — Eur Heart J 2020;41(1):111–188",
        "2019 ESC/EAS Guidelines for the management of dyslipidaemias: lipid modification to reduce cardiovascular risk", "2019",
        "https://doi.org/10.1093/eurheartj/ehz455",
    ),
    Source(
        "gc-esc-eas-lipid-2025", Authority.ESC, "ESC/EAS focused update — Eur Heart J 2025;46(42):4359–4378",
        "2025 Focused Update of the 2019 ESC/EAS Guidelines for the management of dyslipidaemias", "2025",
        "https://doi.org/10.1093/eurheartj/ehaf190",
    ),
    // ------------------------------------------------------------------ Chronic kidney disease
    Source(
        "gc-nice-ng203", Authority.NICE, "Guideline NG203",
        "Chronic kidney disease: assessment and management", "2021",
        "https://www.nice.org.uk/guidance/ng203/chapter/Recommendations",
        status = "Last updated 24 November 2021",
    ),
    Source(
        "gc-icmr-stw-ckd-2026", Authority.ICMR, "Standard Treatment Workflow",
        "Chronic Kidney Disease (ICD-11 GB61)", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1784807342_nephrology_all.pdf",
        status = "July 2026; page 7 of the nephrology workflow set",
    ),
)

private const val GC_ICMR_HTN = "gc-icmr-stw-htn-2026"
private const val GC_IHCI = "gc-ihci-glob-heart-2024"
private const val GC_WHO_IHCI = "gc-who-ihci-hwc-2022"
private const val GC_NG136 = "gc-nice-ng136"
private const val GC_AHA_HBP = "gc-aha-acc-hbp-2025"
private const val GC_ACC_HBP = "gc-acc-hbp-2025-summary"
private const val GC_ERBP = "gc-erbp-hbp-2026"
private const val GC_ESC_HTN = "gc-esc-htn-2024"
private const val GC_NG238 = "gc-nice-ng238"
private const val GC_ACC_LIPID = "hc-acc-aha-lipid-2026"
private const val GC_ACC_LIPID_SUM = "gc-acc-lipid-2026-summary"
private const val GC_CSI = "gc-csi-lipid-2024"
private const val GC_ESC_LIPID_19 = "gc-esc-eas-lipid-2019"
private const val GC_ESC_LIPID_25 = "gc-esc-eas-lipid-2025"
private const val GC_ICMR_T2DM = "icmr-stw-t2dm-2022"
private const val GC_NG203 = "gc-nice-ng203"
private const val GC_ICMR_CKD = "gc-icmr-stw-ckd-2026"
private const val GC_KDIGO = "kdigo-ckd-2024"

// =================================================================================== Hypertension
private val gcIcmrDrugTable = cite(GC_ICMR_HTN, "Pharmacotherapy table")
private val gcIcmrProtocol = listOf(cite(GC_ICMR_HTN, "Suggested treatment protocols 1 and 2"), cite(GC_IHCI, "State treatment protocols"))
private val gcAhaHbp = listOf(
    cite(GC_AHA_HBP, "As summarised by ACC and ERBP"), cite(GC_ACC_HBP), cite(GC_ERBP, "Definition, staging; Therapeutic approaches"),
)

private val gcRasLabs = listOf(
    "Before starting: serum potassium and eGFR (NICE NG203, stated for CKD); electrolytes, creatinine and eGFR are baseline tests for all hypertension (NICE NG136 1.3.3).",
    "Recheck potassium and eGFR 1–2 weeks after starting and after each dose increase (NICE NG203); KDIGO: BP, creatinine and potassium within 2–4 weeks.",
    "Creatinine rise <30% (eGFR fall <25%): keep the dose, repeat in 1–2 weeks. Larger: look for volume depletion or NSAIDs, then stop or reduce (NICE NG203).",
    "Do not routinely start if potassium >5.0 mmol/L; stop if ≥6.0 mmol/L after removing other causes (NICE NG203).",
    "ICMR: contraindicated in pregnancy, women planning pregnancy, hyperkalaemia and bilateral renal artery stenosis.",
)
private val gcRasLabCitations = listOf(
    cite(GC_NG203, "1.6.14–1.6.21"), cite(GC_KDIGO, "Practice Points 3.6.2, 3.6.4"), cite(GC_NG136, "1.3.3"), gcIcmrDrugTable,
)

private val gcThiazideLabs = listOf(
    "Before starting: sodium, potassium, creatinine, eGFR and HbA1c are part of the baseline work-up (NICE NG136 1.3.3; ICMR: glucose or HbA1c).",
    "ICMR: watch for hyponatraemia, hypokalaemia, hypercalcaemia and glucose intolerance; contraindicated in pregnancy and gout.",
    "NICE NG136 and the ICMR workflow give no routine recheck interval at steps 1–3; with further diuretic at step 4, check sodium, potassium and renal function within 1 month (NICE 1.4.50).",
)
private val gcThiazideCitations = listOf(cite(GC_NG136, "1.3.3, 1.4.50"), gcIcmrDrugTable, cite(GC_ICMR_HTN, "Monitoring of hypertension"))

private val gcBetaBlockerCautions = listOf(
    "ICMR: avoid in asthma, severe bradycardia, second- or third-degree AV block and uncontrolled heart failure.",
    "ICMR side effects: fatigue, reduced exercise tolerance, hyperglycaemia, depression, sexual dysfunction.",
)

private val gcHypertension = Condition(
    id = "hypertension-general",
    title = "Hypertension",
    summary = "IHCI/ICMR protocol, targets, and tests before and after each drug class",
    keywords = listOf(
        "hypertension", "HTN", "high blood pressure", "blood pressure", "BP", "raised BP", "high BP", "essential hypertension",
        "antihypertensive", "IHCI", "India Hypertension Control Initiative", "amlodipine", "telmisartan", "chlorthalidone",
        "ACE inhibitor", "ARB", "thiazide", "ABPM", "home BP", "resistant hypertension", "hypertensive urgency", "spironolactone",
    ),
    population = Population.General,
    info = listOf(
        InfoBlock(
            "Measuring and diagnosing",
            listOf(
                "ICMR: hypertension = SBP ≥140 and/or DBP ≥90 mmHg on two clinic visits; a population-screening reading may count as the first.",
                "ICMR: SBP ≥160 and/or DBP ≥100 at one visit is enough. Measure BP in every adult ≥18 at every clinic visit.",
                "IHCI: rest 5 minutes; no exercise, tea/coffee or tobacco for 30 minutes; back, arm and feet supported; correct cuff; no talking.",
                "NICE: if clinic BP ≥140/90, take a second reading (third if very different) and record the lower of the last two.",
                "NICE: clinic BP 140/90 to 180/120 — confirm with ABPM (or HBPM if unsuitable); diagnose if the daytime or home average is ≥135/85.",
                "NICE: measure both arms; if the difference stays >15 mmHg, use the arm with the higher reading.",
                "ESC 2024: confirm 140–159/90–99 with ABPM/HBPM (or repeat office visits); confirm 160–179/100–109 within about 1 month.",
            ),
            listOf(
                cite(GC_ICMR_HTN, "Diagnosis of hypertension"), cite(GC_WHO_IHCI, "BP measurement checklist"),
                cite(GC_NG136, "1.2.1–1.2.8"), cite(GC_ESC_HTN, "Key recommendations, Section 7"),
            ),
        ),
        InfoBlock(
            "BP categories — the guidelines differ",
            listOf(
                "ICMR: normal <120/<80; elevated 120–139/80–89; grade 1 140–159/90–99; grade 2 160–179/100–109; grade 3 ≥180/≥110. Use the higher category.",
                "NICE: stage 1 clinic 140/90–159/99 (ABPM/HBPM 135/85–149/94); stage 2 ≥160/100 (≥150/95); severe ≥180/120.",
                "ESC 2024: non-elevated <120/70; elevated 120–139/70–89; hypertension ≥140/90.",
                "AHA/ACC 2025: SBP 120–129 is elevated; hypertension starts at 130/80 — stage 1 130–139/80–89, stage 2 ≥140/90.",
            ),
            listOf(
                cite(GC_ICMR_HTN, "Classification"), cite(GC_NG136, "Terms used in this guideline"),
                cite(GC_ESC_HTN, "Section 6, Table 5"),
            ) + gcAhaHbp,
        ),
        InfoBlock(
            "BP targets",
            listOf(
                "ICMR: <140/90 for all adults; <130/80 (optimal, if tolerated) with diabetes, CKD or established CVD; <150/90 at ≥80 years.",
                "IHCI: systolic <140 and diastolic <90 mmHg.",
                "NICE: clinic <140/90 under 80 years (ABPM/HBPM <135/85); <150/90 at ≥80 (<145/85). Same targets with or without CVD.",
                "NICE: CKD with ACR ≥70 mg/mmol — <130/80.",
                "ESC 2024: treated SBP 120–129 if tolerated, otherwise as low as reasonably achievable; DBP 70–79 (IIb).",
                "AHA/ACC 2025: <130/80 for all adults, with encouragement toward SBP <120.",
                "Postural BP: ICMR — every visit at ≥65. NICE — with type 2 diabetes, postural symptoms or age ≥80; treat to standing BP if it drops.",
            ),
            listOf(
                cite(GC_ICMR_HTN, "Blood pressure control targets"), cite(GC_WHO_IHCI, "Hypertension treatment"),
                cite(GC_NG136, "1.4.16, 1.4.20–1.4.23; Tables 1–2"), cite(GC_ESC_HTN, "Section 8 targets"),
            ) + gcAhaHbp,
        ),
        InfoBlock(
            "When to start drugs",
            listOf(
                "ICMR: grade 1 or above (≥140/90) — drugs plus lifestyle; elevated BP — lifestyle. IHCI: almost all patients need medicines.",
                "NICE: stage 2 at any age — offer. Stage 1 under 80 with organ damage, CVD, renal disease, diabetes or QRISK ≥10% — discuss.",
                "NICE: stage 1 under 60 with 10-year risk <10% — consider; over 80 — consider if clinic BP >150/90.",
                "ESC 2024: confirmed ≥140/90 — start promptly whatever the risk; elevated BP ≥130/80 with high risk (incl. SCORE2 ≥10%) — after 3 months' lifestyle.",
                "ESC 2024: defer drugs until >140/90 at ≥85 years, moderate–severe frailty, symptomatic orthostatic hypotension or limited life expectancy.",
                "AHA/ACC 2025: ≥130/80 with CVD, diabetes, CKD or PREVENT 10-year risk ≥7.5% — start drugs; lower risk — 3–6 months' lifestyle first.",
            ),
            listOf(
                cite(GC_ICMR_HTN, "Classification — action"), cite(GC_WHO_IHCI, "Hypertension treatment"),
                cite(GC_NG136, "1.4.9–1.4.13"), cite(GC_ESC_HTN, "Section 8 recommendations; Section 8.3.6"),
            ) + gcAhaHbp,
        ),
        InfoBlock(
            "Tests at diagnosis — before any drug",
            listOf(
                "ICMR: fasting glucose or HbA1c; yearly serum creatinine, urine albumin, sugar and creatinine, lipid profile and fundoscopy; ECG/echo if required.",
                "NICE 1.3.3: urine ACR and dipstick for haematuria; HbA1c, electrolytes, creatinine, eGFR, total and HDL cholesterol; fundoscopy; 12-lead ECG.",
                "ESC 2024 (Table 8): fasting glucose, lipids, sodium, potassium, haemoglobin, calcium, TSH, creatinine/eGFR, urinalysis, urine ACR, 12-lead ECG.",
                "AHA/ACC 2025: urine albumin-to-creatinine ratio for everyone with newly diagnosed hypertension (Class 1).",
            ),
            listOf(
                cite(GC_ICMR_HTN, "Monitoring of hypertension"), cite(GC_NG136, "1.3.3"), cite(GC_ESC_HTN, "Table 8"),
            ) + gcAhaHbp,
        ),
        InfoBlock(
            "Lifestyle",
            listOf(
                "ICMR: salt <5 g/day; no tobacco or alcohol; 30 minutes' activity daily; BMI <23 kg/m²; waist <90 cm (men), <80 cm (women).",
                "NICE: do not offer calcium, magnesium or potassium supplements to lower BP.",
            ),
            listOf(cite(GC_ICMR_HTN, "Lifestyle modification"), cite(GC_NG136, "1.4.6")),
        ),
        InfoBlock(
            "Where the guidelines differ",
            listOf(
                "First drug: IHCI/ICMR protocols start amlodipine (telmisartan in CKD); NICE chooses by age and diabetes; AHA/ACC start most on a single-pill combination.",
                "Threshold and target: ICMR, IHCI and NICE use 140/90; AHA/ACC use 130/80; ICMR uses <130/80 only with diabetes, CKD or CVD.",
            ),
            listOf(cite(GC_ICMR_HTN), cite(GC_IHCI, "Methods"), cite(GC_NG136, "1.4.32, 1.4.35")) + gcAhaHbp,
        ),
    ),
    tiers = listOf(
        Tier(
            "India · IHCI protocol (ICMR STW 2026)",
            "Step up after 4 weeks if BP is still ≥140/90, checking adherence at every visit. Protocol 1 (ATTACC) or Protocol 2 (AATTCC); state protocols vary.",
            listOf(
                Drug(
                    "Amlodipine — step 1",
                    "5 mg once daily. Protocol 1 then adds telmisartan; Protocol 2 first increases amlodipine to 10 mg (step 2).",
                    maxDose = "10 mg/day (ICMR range 2.5–10 mg OD)",
                    cautions = listOf(
                        "ICMR: contraindicated in heart failure; side effects pedal oedema and constipation.",
                        "NICE: if a CCB is not tolerated (for example oedema), offer a thiazide-like diuretic (1.4.36).",
                    ),
                    citations = gcIcmrProtocol + listOf(gcIcmrDrugTable, cite(GC_NG136, "1.4.36")),
                ),
                Drug(
                    "Telmisartan — steps 2–4",
                    "Add 40 mg once daily, then increase to 80 mg. IHCI protocols start telmisartan instead of amlodipine in people with CKD.",
                    maxDose = "80 mg/day (ICMR range 40–80 mg OD)",
                    cautions = gcRasLabs,
                    citations = gcIcmrProtocol + gcRasLabCitations + cite(GC_IHCI, "CKD exception to amlodipine first"),
                ),
                Drug(
                    "Chlorthalidone — step 5",
                    "Add 12.5 mg once daily. ICMR alternative: hydrochlorothiazide 12.5 mg once daily.",
                    maxDose = "25 mg/day (ICMR range 12.5–25 mg OD)",
                    cautions = gcThiazideLabs,
                    citations = gcIcmrProtocol + gcThiazideCitations,
                ),
                Drug(
                    "Spironolactone — step 6",
                    "ICMR: if still ≥140/90, consider another class, preferably an MRA such as spironolactone 25 mg once daily.",
                    cautions = listOf(
                        "Before starting: potassium ≤4.5 mmol/L; extra caution with reduced eGFR — higher hyperkalaemia risk (NICE 1.4.49).",
                        "Check sodium, potassium and renal function within 1 month, then as needed (NICE 1.4.50).",
                        "ICMR: contraindicated in hyperkalaemia; mastalgia, gynaecomastia, sexual dysfunction.",
                    ),
                    citations = gcIcmrProtocol + listOf(gcIcmrDrugTable, cite(GC_NG136, "1.4.49–1.4.50")),
                ),
            ),
        ),
        Tier(
            "India · other drugs in the ICMR workflow",
            "ICMR: two drugs from different classes could be the default start for most; single-pill combinations preferred; monotherapy for low-risk grade 1, ≥80 years or frailty.",
            listOf(
                Drug(
                    "Enalapril", "5–40 mg once or twice daily (ICMR).",
                    cautions = gcRasLabs + "ICMR: dry cough, angioedema; avoid after ACE-inhibitor angioedema. NICE: switch to an ARB if not tolerated, e.g. cough (1.4.33).",
                    citations = gcRasLabCitations + listOf(cite(GC_ICMR_HTN, "Pharmacological strategy"), cite(GC_NG136, "1.4.33")),
                ),
                Drug(
                    "Ramipril", "2.5–10 mg once or twice daily (ICMR).",
                    cautions = gcRasLabs + "ICMR: dry cough, angioedema; avoid after ACE-inhibitor angioedema. NICE: switch to an ARB if not tolerated, e.g. cough (1.4.33).",
                    citations = gcRasLabCitations + listOf(cite(GC_ICMR_HTN, "Pharmacological strategy"), cite(GC_NG136, "1.4.33")),
                ),
                Drug(
                    "Losartan", "50–100 mg once daily (ICMR).",
                    cautions = gcRasLabs,
                    citations = gcRasLabCitations,
                ),
                Drug(
                    "Hydrochlorothiazide",
                    "12.5 mg once daily (ICMR). NICE prefers a thiazide-like diuretic (e.g. indapamide) when starting or changing; continue HCTZ if BP is stable and controlled.",
                    cautions = gcThiazideLabs,
                    citations = gcThiazideCitations + cite(GC_NG136, "1.4.38–1.4.39"),
                ),
                Drug(
                    "Metoprolol",
                    "50–100 mg once or twice daily (ICMR). ICMR: suited to angina, post-MI or heart failure. NICE: an option at step 4 if potassium >4.5 mmol/L.",
                    cautions = gcBetaBlockerCautions,
                    citations = listOf(gcIcmrDrugTable, cite(GC_ICMR_HTN, "Pharmacological strategy"), cite(GC_NG136, "1.4.51")),
                ),
                Drug(
                    "Labetalol", "100–400 mg twice daily (ICMR).",
                    cautions = gcBetaBlockerCautions,
                    citations = listOf(gcIcmrDrugTable),
                ),
            ),
        ),
        Tier(
            "NICE NG136 · stepwise (with or without type 2 diabetes)", null,
            listOf(
                Drug(
                    "Step 1 — ACE inhibitor or ARB",
                    "Type 2 diabetes at any age, or age <55 and not of Black African or African–Caribbean family origin. ARB if an ACE inhibitor is not tolerated.",
                    citations = listOf(cite(GC_NG136, "1.4.32–1.4.33")),
                ),
                Drug(
                    "Step 1 — calcium-channel blocker",
                    "Age ≥55 without type 2 diabetes, or Black African or African–Caribbean origin without diabetes. If not tolerated, a thiazide-like diuretic.",
                    citations = listOf(cite(GC_NG136, "1.4.35–1.4.36")),
                ),
                Drug(
                    "Step 2",
                    "Check adherence first. On ACEi/ARB: add a CCB or thiazide-like diuretic. On a CCB: add an ACEi, ARB or thiazide-like diuretic.",
                    citations = listOf(cite(GC_NG136, "1.4.40–1.4.42")),
                ),
                Drug(
                    "Step 3",
                    "After reviewing doses and adherence: ACEi or ARB + CCB + thiazide-like diuretic.",
                    citations = listOf(cite(GC_NG136, "1.4.44–1.4.45")),
                ),
                Drug(
                    "Step 4 — resistant hypertension",
                    "Confirm with ABPM/HBPM, check postural BP and adherence. Potassium ≤4.5: consider low-dose spironolactone; >4.5: alpha- or beta-blocker. Uncontrolled on 4 drugs: specialist advice.",
                    citations = listOf(cite(GC_NG136, "1.4.46–1.4.52")),
                ),
            ),
        ),
        Tier(
            "ESC 2024 · combination first", null,
            listOf(
                Drug(
                    "ESC: two-drug single-pill combination",
                    "For most with confirmed BP ≥140/90: low-dose RAS blocker + dihydropyridine CCB or diuretic, as one pill. Monotherapy at ≥85 years, frailty, orthostatic hypotension or elevated BP.",
                    citations = listOf(cite(GC_ESC_HTN, "Section 8.3.4; key recommendations")),
                ),
                Drug(
                    "ESC: three-drug combination",
                    "If not controlled: RAS blocker + dihydropyridine CCB + thiazide or thiazide-like diuretic, preferably as one pill.",
                    citations = listOf(cite(GC_ESC_HTN, "Section 8.3.4; key recommendations")),
                ),
                Drug(
                    "ESC: resistant hypertension",
                    "Spironolactone 25–50 mg daily first, only if eGFR ≥30 and potassium ≤4.5 mmol/L; check electrolytes and kidney function soon after starting and often thereafter.",
                    cautions = listOf("ESC 2024: if not tolerated, eplerenone or a beta-blocker; refer to an expert centre."),
                    citations = listOf(cite(GC_ESC_HTN, "Sections 8.3.4, 9.13.3")),
                ),
            ),
        ),
        Tier(
            "AHA/ACC 2025", null,
            listOf(
                Drug(
                    "Single-pill combination",
                    "Start most patients on a once-daily single-pill combination (not with frailty or symptomatic orthostatic hypotension). Stage 1: one first-line drug with close follow-up is an option.",
                    citations = gcAhaHbp,
                ),
                Drug(
                    "First-line classes",
                    "Thiazide-type diuretic, long-acting dihydropyridine CCB, ACE inhibitor or ARB; ACE inhibitor or ARB if CKD or type 2 diabetes.",
                    citations = gcAhaHbp,
                ),
            ),
        ),
    ),
    avoid = listOf(
        Caution(
            "ACE inhibitor + ARB together",
            "Do not combine two RAS blockers — more adverse effects, including acute kidney injury (ICMR; NICE 1.4.34).",
            listOf(cite(GC_ICMR_HTN, "Pharmacological strategy"), cite(GC_NG136, "1.4.34")),
        ),
        Caution(
            "ACE inhibitors, ARBs or thiazides in pregnancy or when planning pregnancy",
            "ICMR: contraindicated. NICE: avoid ACEi/ARB in pregnancy, breastfeeding or planned pregnancy unless absolutely necessary.",
            listOf(gcIcmrDrugTable, cite(GC_NG136, "Choosing antihypertensive drug treatment — note")),
        ),
        Caution(
            "Potassium-based salt substitutes",
            "Not for older people, people with diabetes or kidney disease, or those on ACE inhibitors or ARBs (NICE 1.4.5).",
            listOf(cite(GC_NG136, "1.4.5")),
        ),
        Caution(
            "IV drugs for hypertensive urgency",
            "ICMR: BP ≥180/120 without acute organ damage — start oral drugs and lower BP gradually over 24–48 hours.",
            listOf(cite(GC_ICMR_HTN, "Hypertension urgency")),
        ),
    ),
    escalate = listOf(
        "ICMR red flags: BP ≥180/120 with visual disturbance, confusion, headache, chest pain, breathlessness or sudden weakness — stabilise ABC and refer to emergency care.",
        "ICMR urgency (≥180/120, no acute organ damage): oral drugs, refer or admit if organ damage is suspected, review within 1 week.",
        "NICE: same-day specialist review if ≥180/120 with retinal haemorrhage, papilloedema, new confusion, chest pain, heart failure or AKI; or suspected phaeochromocytoma.",
        "NICE: ≥180/120 without these — test for organ damage now; if present, start treatment immediately; if absent, repeat clinic BP within 7 days.",
        "NICE: under 40 — consider specialist evaluation for secondary causes. Uncontrolled on optimal doses of 4 drugs — seek specialist advice.",
        "AHA/ACC 2025: screen for primary aldosteronism in resistant hypertension, even without hypokalaemia.",
    ),
    citations = listOf(
        cite(GC_ICMR_HTN), cite(GC_IHCI), cite(GC_WHO_IHCI), cite(GC_NG136, "1.5.1–1.5.3, 1.4.14, 1.4.52"), cite(GC_ESC_HTN),
    ) + gcAhaHbp,
)

// ================================================================================== Dyslipidaemia
private val gcAccLipid = listOf(cite(GC_ACC_LIPID, "As summarised by ACC"), cite(GC_ACC_LIPID_SUM))

private val gcStatinCautions = listOf(
    "Before starting: full lipid profile, renal function, ALT or AST, diabetes status; TSH if thyroid symptoms; CK only with unexplained muscle symptoms (NICE).",
    "Recheck lipids and ALT/AST 2–3 months after starting or changing; ALT/AST at 12 months, then only if indicated; no CK without symptoms (NICE).",
    "Stop if pregnancy is possible and 3 months before trying to conceive; do not restart until breastfeeding ends (NICE).",
    "CSI: observational data suggest Indians may respond to lower doses and be more prone to adverse effects.",
)
private val gcStatinCitations = listOf(
    cite(GC_NG238, "1.5.5, 1.5.7, 1.5.9–1.5.10, 1.11.1–1.11.5; Terms used"), cite(GC_CSI, "Table 2; Section 5.1"),
)

private val gcDyslipidaemia = Condition(
    id = "dyslipidaemia",
    title = "Dyslipidaemia (high cholesterol)",
    summary = "Risk tools, LDL-C targets, statin doses, tests before and on treatment",
    keywords = listOf(
        "dyslipidaemia", "dyslipidemia", "cholesterol", "high cholesterol", "hypercholesterolaemia", "hypercholesterolemia",
        "hyperlipidaemia", "hyperlipidemia", "lipids", "lipid profile", "LDL", "LDL-C", "non-HDL", "triglycerides", "TG",
        "statin", "atorvastatin", "rosuvastatin", "ezetimibe", "QRISK", "PREVENT", "SCORE2", "Lp(a)", "lipoprotein(a)",
        "familial hypercholesterolaemia", "FH", "CK", "ALT",
    ),
    population = Population.General,
    info = listOf(
        InfoBlock(
            "Assessing risk",
            listOf(
                "NICE: QRISK3 for people aged 25–84 without CVD, including type 2 diabetes; consider lifetime risk if 10-year risk <10%, or age <40 with risk factors.",
                "NICE: no risk tool — already high risk — with type 1 diabetes, eGFR <60 and/or albuminuria, or familial hypercholesterolaemia.",
                "ACC/AHA 2026: PREVENT 10-year ASCVD risk — borderline 3–5%, intermediate 5–10%, high ≥10%.",
                "CSI (India): low — no risk factor; moderate — one of smoking, hypertension, diabetes, premature family CAD (men <55, women <60).",
                "CSI: high — ≥2 risk factors, CKD, diabetes >10 years or heterozygous FH; very high — ASCVD, diabetes >20 years, FH with ASCVD.",
                "ESC/EAS 2025: SCORE2 (<70 years) or SCORE2-OP (≥70) in apparently healthy people; risk modifiers or CAC score near treatment thresholds.",
                "ESC/EAS 2025: very high risk includes ASCVD, eGFR <30, diabetes with organ damage, SCORE2 ≥20%; high includes eGFR 30–59, LDL-C >190 mg/dL, SCORE2 10–<20%.",
            ),
            listOf(
                cite(GC_NG238, "1.1.7–1.1.9, 1.1.16"), cite(GC_CSI, "Section 4"), cite(GC_ESC_LIPID_25, "Table 3; Recommendation Table 1"),
            ) + gcAccLipid,
        ),
        InfoBlock(
            "LDL-C targets",
            listOf(
                "ACC/AHA 2026: PREVENT ≥10% — LDL-C <70 and non-HDL <100 mg/dL; borderline or intermediate — LDL-C <100 and non-HDL <130 mg/dL.",
                "NICE: primary prevention — >40% fall in non-HDL; secondary — LDL-C ≤2.0 mmol/L (≈77 mg/dL) or non-HDL ≤2.6 mmol/L (≈100 mg/dL).",
                "CSI (India): LDL-C <100 (low and moderate risk), <70 (high), <55 mg/dL (very high); non-HDL <130, <130, <100, <85.",
                "ICMR (diabetes workflow): LDL-C <100 mg/dL; <70 mg/dL with coronary artery disease.",
                "ESC/EAS 2019: very high risk <55 mg/dL and ≥50% reduction; high <70 and ≥50%; moderate <100; low <116 mg/dL.",
                "ESC/EAS 2019: second vascular event within 2 years on maximal statin — LDL-C <40 mg/dL may be considered.",
            ),
            listOf(
                cite(GC_NG238, "1.6.1, 1.7.1"), cite(GC_CSI, "Table 1"), cite(GC_ICMR_T2DM, "Targets"),
                cite(GC_ESC_LIPID_19, "Section 5 recommendations"),
            ) + gcAccLipid,
        ),
        InfoBlock(
            "Tests before starting",
            listOf(
                "NICE: full lipid profile (fasting not needed), diabetes status, renal function, ALT or AST; TSH if thyroid symptoms; smoking, alcohol, BP, BMI.",
                "NICE: treat secondary causes first — e.g. excess alcohol, uncontrolled diabetes, hypothyroidism, liver disease, nephrotic syndrome.",
                "NICE: transaminases raised but <3× upper limit of normal are not a reason to withhold a statin.",
                "NICE: CK only with persistent unexplained muscle symptoms — >5× ULN: recheck in 7 days, do not start if still >5×; <5×: start a lower dose.",
                "CSI: non-fasting lipid profile is recommended; routine baseline or follow-up liver and muscle enzyme tests are not recommended.",
                "ACC/AHA 2026: measure Lp(a) at least once in every adult; >125 nmol/L and >250 nmol/L carry about 1.4-fold and ≥2-fold risk.",
                "ESC/EAS 2019: two lipid measurements 1–12 weeks apart before treatment (except ACS or very high risk); ALT and CK before starting.",
                "ESC/EAS 2025: Lp(a) at least once in adulthood (IIa); >50 mg/dL (105 nmol/L) is a risk-enhancing factor.",
            ),
            listOf(
                cite(GC_NG238, "1.4.3, 1.5.5–1.5.7, 1.6.6"), cite(GC_CSI, "Sections 3, 5.1"),
                cite(GC_ESC_LIPID_19, "Table 13"), cite(GC_ESC_LIPID_25, "Recommendation Table 4"),
            ) + gcAccLipid,
        ),
        InfoBlock(
            "Monitoring on treatment",
            listOf(
                "NICE: lipid profile and ALT/AST 2–3 months after starting or changing treatment; ALT/AST at 12 months, then only if clinically indicated.",
                "NICE: yearly lipid profile — offer in secondary prevention, consider in primary prevention — with an annual medication review.",
                "NICE: no CK in people without symptoms; measure it if unexplained muscle pain, tenderness or weakness develops.",
                "ESC/EAS 2019: lipids 8 (±4) weeks after starting or adjusting until at goal, then yearly; ALT once at 8–12 weeks, not routinely after.",
                "ESC/EAS 2019: baseline CK >4× ULN — do not start, recheck; CK >10× ULN on treatment — stop, check renal function.",
            ),
            listOf(cite(GC_NG238, "1.11.1–1.11.10"), cite(GC_ESC_LIPID_19, "Table 13")),
        ),
        InfoBlock(
            "Where the guidelines differ",
            listOf(
                "Targets: NICE uses a % fall (primary) and ≤2.0 mmol/L (secondary); ACC/AHA, ICMR and CSI use fixed mg/dL goals.",
                "High-intensity doses: NICE atorvastatin 20–80 mg, rosuvastatin 10–40 mg; CSI atorvastatin 40–80 mg, rosuvastatin 20–40 mg.",
                "Baseline enzymes: NICE checks ALT/AST and CK only with symptoms; CSI advises no routine enzyme tests.",
            ),
            listOf(cite(GC_NG238, "1.5.5–1.5.7, 1.6.1, 1.7.1; Terms used"), cite(GC_CSI, "Tables 1–2; Section 5.1"), cite(GC_ICMR_T2DM)) + gcAccLipid,
        ),
    ),
    tiers = listOf(
        Tier(
            "Who gets a statin · NICE NG238", "Discuss benefits and risks first; in primary prevention, optimise lifestyle and treat secondary causes before starting.",
            listOf(
                Drug(
                    "Atorvastatin 20 mg — primary prevention",
                    "Once daily if 10-year QRISK3 ≥10% when lifestyle change is ineffective or inappropriate; not ruled out below 10% if preferred. Consider at ≥85 years.",
                    cautions = gcStatinCautions,
                    citations = listOf(cite(GC_NG238, "1.6.2–1.6.9")) + gcStatinCitations,
                ),
                Drug(
                    "Atorvastatin 20 mg — CKD or type 1 diabetes",
                    "CKD (primary or secondary prevention); increase if target not met and eGFR ≥30. Type 1 diabetes: age >40, diabetes >10 years, nephropathy or other risk factors.",
                    cautions = gcStatinCautions,
                    citations = listOf(cite(GC_NG238, "1.6.10–1.6.12, 1.8.1–1.8.3")) + gcStatinCitations,
                ),
                Drug(
                    "Atorvastatin 80 mg — established CVD",
                    "Whatever the cholesterol; lower dose if interactions, high adverse-effect risk or preference. ACS: start without delay; lipid profile on admission and at 2–3 months.",
                    cautions = gcStatinCautions,
                    citations = listOf(cite(GC_NG238, "1.7.2–1.7.5")) + gcStatinCitations,
                ),
            ),
        ),
        Tier(
            "Statin doses and intensity", null,
            listOf(
                Drug(
                    "Atorvastatin",
                    "NICE: high intensity 20–80 mg, medium 10 mg once daily. CSI (India): high 40–80 mg, moderate 10–20 mg, low 5 mg.",
                    maxDose = "80 mg/day",
                    cautions = gcStatinCautions,
                    citations = gcStatinCitations,
                ),
                Drug(
                    "Rosuvastatin",
                    "NICE: high intensity 10–40 mg, medium 5 mg once daily; try rosuvastatin if atorvastatin causes adverse effects. CSI: high 20–40 mg, moderate 5–10 mg.",
                    maxDose = "40 mg/day",
                    cautions = gcStatinCautions,
                    citations = gcStatinCitations + cite(GC_NG238, "1.9.2"),
                ),
            ),
        ),
        Tier(
            "Adding to or replacing a statin", null,
            listOf(
                Drug(
                    "Ezetimibe",
                    "10 mg once daily (CSI). NICE: in secondary prevention, consider adding to the maximum tolerated statin even if at target; offer instead of a statin if statins cannot be used.",
                    cautions = listOf("CSI: not recommended in moderate to severe hepatic insufficiency."),
                    citations = listOf(cite(GC_NG238, "1.7.10, 1.10.1, 1.10.3"), cite(GC_CSI, "Table 3")),
                ),
                Drug(
                    "Ezetimibe — CSI (India), ACS",
                    "Add at the start of treatment in ACS to reach LDL-C <55 mg/dL early; if not <55 at 4 weeks, add bempedoic acid, a PCSK9 inhibitor or inclisiran.",
                    citations = listOf(cite(GC_CSI, "Section 4.1")),
                ),
                Drug(
                    "PCSK9 inhibitor, inclisiran or bempedoic acid",
                    "NICE: if the secondary-prevention target is not met on the maximum tolerated statin (or on ezetimibe), consider these per NICE technology appraisals.",
                    citations = listOf(cite(GC_NG238, "1.7.9, 1.10.2, 1.10.4")),
                ),
                Drug(
                    "Ezetimibe — ESC/EAS",
                    "2019: add if the LDL-C goal is not reached on the maximum tolerated statin. 2025: consider statin + ezetimibe from the ACS admission if treatment-naive and unlikely to reach goal (IIa).",
                    citations = listOf(cite(GC_ESC_LIPID_19, "Section 9 recommendations"), cite(GC_ESC_LIPID_25, "Recommendation Table 3")),
                ),
            ),
        ),
        Tier(
            "ESC/EAS 2025 · when to start drugs (primary prevention)", null,
            listOf(
                Drug(
                    "ESC: very high or high risk",
                    "Drug therapy recommended despite lifestyle if LDL-C ≥70 (very high) or ≥100 mg/dL (high) (I, A); consider at 55–69 or 70–99 mg/dL (IIa).",
                    citations = listOf(cite(GC_ESC_LIPID_25, "Recommendation Table 1")),
                ),
                Drug(
                    "ESC: moderate or low risk",
                    "Consider drugs despite lifestyle if LDL-C 100–189 (moderate) or 116–189 mg/dL (low) (IIa).",
                    citations = listOf(cite(GC_ESC_LIPID_25, "Recommendation Table 1")),
                ),
            ),
        ),
    ),
    avoid = listOf(
        Caution(
            "Statins in pregnancy or breastfeeding",
            "Contraindicated in pregnancy; stop 3 months before trying to conceive (NICE). CSI: not for women considering pregnancy or without adequate contraception.",
            listOf(cite(GC_NG238, "1.5.9–1.5.10"), cite(GC_CSI, "Section 5.1")),
        ),
        Caution(
            "Fibrates, niacin, bile acid sequestrants or omega-3 to prevent CVD",
            "NICE: do not offer niacin, bile acid sequestrants or omega-3 (except icosapent ethyl, TA805); fibrates not routinely; do not combine any of these with a statin.",
            listOf(cite(GC_NG238, "1.12.2–1.12.7")),
        ),
        Caution(
            "Stopping a statin because glucose or HbA1c rises",
            "Not a reason to stop (NICE 1.11.6).",
            listOf(cite(GC_NG238, "1.11.6")),
        ),
    ),
    escalate = listOf(
        "NICE: specialist assessment if total cholesterol >9.0 mmol/L (≈348 mg/dL) or non-HDL >7.5 mmol/L (≈290 mg/dL), even without a family history.",
        "NICE: urgent specialist review if triglycerides >20 mmol/L (≈1,770 mg/dL) not due to alcohol or poor glycaemic control.",
        "NICE: triglycerides 10–20 mmol/L — repeat fasting within 5–14 days, review secondary causes, seek advice if still >10 mmol/L.",
        "NICE: CKD with eGFR <30 — agree atorvastatin doses above 20 mg with a renal specialist.",
        "ESC/EAS 2019: CK >10× ULN on a statin — stop, check renal function and monitor CK every 2 weeks.",
    ),
    citations = listOf(
        cite(GC_NG238, "1.4.5–1.4.7, 1.8.3"), cite(GC_CSI), cite(GC_ICMR_T2DM), cite(GC_ESC_LIPID_19, "Table 13"), cite(GC_ESC_LIPID_25),
    ) + gcAccLipid,
)

// ========================================================================= Chronic kidney disease
private val gcCkdRasCautions = listOf(
    "Before starting: serum potassium and eGFR; do not routinely start if potassium >5.0 mmol/L (NICE). ICMR: caution if potassium >5.5 mEq/L.",
    "Recheck potassium and eGFR 1–2 weeks after starting and after each dose increase (NICE); KDIGO: BP, creatinine and potassium within 2–4 weeks.",
    "Creatinine rise <30% (eGFR fall <25%): keep the dose, repeat in 1–2 weeks (NICE). KDIGO: continue unless creatinine rises >30% within 4 weeks.",
    "Larger rise: look for volume depletion or NSAIDs; if none, stop or return to a lower tolerated dose (NICE).",
    "Hyperkalaemia: KDIGO — usually lower potassium rather than stop; NICE — stop if potassium ≥6.0 mmol/L after removing other causes.",
    "Reduce or stop for symptomatic hypotension, uncontrolled hyperkalaemia, or to ease uraemic symptoms at eGFR <15 (KDIGO).",
)

private val gcCkd = Condition(
    id = "ckd",
    title = "Chronic kidney disease",
    summary = "Staging, monitoring, ACEi/ARB and SGLT2i with lab checks, referral",
    keywords = listOf(
        "CKD", "chronic kidney disease", "renal failure", "chronic renal failure", "CRF", "kidney disease", "renal impairment",
        "kidney failure", "eGFR", "GFR", "creatinine", "albuminuria", "proteinuria", "microalbuminuria", "ACR", "UACR",
        "diabetic kidney disease", "nephropathy", "KDIGO", "SGLT2", "dapagliflozin", "empagliflozin", "dialysis",
    ),
    population = Population.General,
    tool = ToolKind.Renal,
    info = listOf(
        InfoBlock(
            "Definition and confirmation",
            listOf(
                "KDIGO and ICMR: abnormal kidney structure or function present >3 months, with implications for health.",
                "KDIGO markers: eGFR <60, ACR ≥30 mg/g (≥3 mg/mmol), urine sediment abnormality or persistent haematuria, tubular disorder, abnormal imaging or histology, transplant.",
                "KDIGO: do not assume chronicity from a single abnormal eGFR or ACR — it may be acute kidney injury. NICE: repeat a first eGFR <60 within 2 weeks.",
                "NICE: confirm ACR 3–70 mg/mmol on an early-morning sample (no repeat if ≥70). ICMR: confirm the diagnosis by repeating tests after 3 months.",
                "NICE: test eGFR and ACR in diabetes, hypertension, past AKI, CVD, renal stones or obstruction, SLE, gout, family history of kidney failure.",
            ),
            listOf(
                cite(GC_KDIGO, "Table 1; Practice Points 1.1.3.1–1.1.3.2, 1.3.1.2"), cite(GC_NG203, "1.1.8, 1.1.12, 1.1.21"),
                cite(GC_ICMR_CKD, "What is CKD?; Initial assessment"),
            ),
        ),
        InfoBlock(
            "Staging — GFR × albuminuria",
            listOf(
                "G1 ≥90 · G2 60–89 · G3a 45–59 · G3b 30–44 · G4 15–29 · G5 <15 mL/min/1.73 m² (kidney failure).",
                "A1 <30 mg/g (<3 mg/mmol) · A2 30–300 mg/g (3–30 mg/mmol) · A3 >300 mg/g (>30 mg/mmol).",
                "G1 or G2 without another marker of kidney damage is not CKD.",
                "Lower GFR and higher ACR each raise risk; together they multiply it (NICE 1.2.1).",
            ),
            listOf(cite(GC_KDIGO, "Tables 2–3"), cite(GC_NG203, "1.2.1; Table 1")),
        ),
        InfoBlock(
            "Initial work-up (ICMR)",
            listOf(
                "Serum creatinine, electrolytes, bicarbonate; eGFR by CKD-EPI; urinalysis with sediment and protein quantitation; ultrasound of kidneys and urinary tract.",
                "Calcium, phosphate, alkaline phosphatase, albumin; CBC with peripheral smear; iron studies (iron, TIBC, TSAT).",
                "Find reversible factors: uncontrolled BP, volume loss, obstruction, infection, nephrotoxic drugs.",
                "NICE: renal ultrasound if accelerated progression, haematuria, obstructive symptoms, family history of polycystic kidney disease (age >20) or GFR <30.",
            ),
            listOf(cite(GC_ICMR_CKD, "Evaluation of newly diagnosed patient; Initial assessment"), cite(GC_NG203, "1.2.5")),
        ),
        InfoBlock(
            "Monitoring eGFR and ACR (times per year)",
            listOf(
                "KDIGO: at least yearly in everyone with CKD; more often if higher risk and the result would change treatment.",
                "KDIGO, by A1/A2/A3 — G1–G2: 1, 1, 3 · G3a: 1, 2, 3 · G3b: 2, 3, 3 · G4: 3, 3, 4+ · G5: 4+.",
                "NICE minimum eGFR checks, by A1/A2/A3 — G1–G2: 0–1, 1, ≥1 · G3a: 1, 1, 2 · G3b: 1–2, 2, ≥2 · G4: 2, 2, 3 · G5: 4, ≥4, ≥4.",
                "KDIGO: eGFR change >20% (>30% after starting haemodynamically active drugs) or a doubled ACR warrants evaluation.",
                "NICE: accelerated progression — sustained fall ≥25% with a change in category within 12 months, or ≥15 mL/min/1.73 m² per year.",
            ),
            listOf(
                cite(GC_KDIGO, "Practice Points 2.1.1–2.1.5; Figure 13"), cite(GC_NG203, "1.3.4–1.3.5; Table 2"),
            ),
        ),
        InfoBlock(
            "BP targets in CKD",
            listOf(
                "KDIGO: SBP <120 when tolerated, by standardised office measurement; less intensive with frailty, falls risk, limited life expectancy or postural hypotension.",
                "NICE: ACR <70 mg/mmol — <140/90 (SBP range 120–139); ACR ≥70 — <130/80 (SBP range 120–129).",
                "ICMR CKD workflow: <120/80, ACE inhibitor/ARB preferred. ICMR hypertension and diabetes workflows: <130/80 in CKD.",
            ),
            listOf(
                cite(GC_KDIGO, "Recommendation 3.4.1; Practice Point 3.4.1"), cite(GC_NG203, "1.6.1–1.6.2"),
                cite(GC_ICMR_CKD, "BP control"), cite(GC_ICMR_HTN, "Blood pressure control targets"), cite(GC_ICMR_T2DM, "Targets"),
            ),
        ),
        InfoBlock(
            "Drug dosing and nephrotoxins",
            listOf(
                "KDIGO: CKD increases susceptibility to nephrotoxic drugs — weigh benefit and harm; monitor eGFR, electrolytes and drug levels where relevant.",
                "KDIGO: consider GFR when dosing renally cleared drugs; creatinine-based eGFR suits most; non-indexed eGFR at extremes of body weight.",
                "KDIGO: consider pausing metformin, ACEi, ARB and SGLT2i 48–72 hours before elective surgery or during acute adverse effects — and plan the restart.",
                "NICE: check GFR at least yearly in people taking lithium, calcineurin inhibitors or long-term NSAIDs.",
                "ICMR: stop nephrotoxic agents; avoid NSAIDs, unproven supplements and untested indigenous medicines.",
            ),
            listOf(
                cite(GC_KDIGO, "Practice Points 4.1.1–4.1.3, 4.2.1–4.2.4, 4.3.2–4.3.3"), cite(GC_NG203, "1.1.20"),
                cite(GC_ICMR_CKD, "Lifestyle measures; Primary care"),
            ),
        ),
        InfoBlock(
            "Lifestyle",
            listOf(
                "KDIGO: protein 0.8 g/kg/day in G3–G5 (avoid >1.3); sodium <2 g/day (<5 g salt); ≥150 minutes/week of moderate activity.",
                "ICMR: salt <5 g/day; protein 0.8 g/kg/day, not restricted unless intake is high; stop tobacco and alcohol; restrict potassium if hyperkalaemic.",
                "NICE: do not offer low-protein diets (<0.6–0.8 g/kg/day). KDIGO: very-low-protein diets only under close supervision.",
            ),
            listOf(
                cite(GC_KDIGO, "Recommendations 3.2.2.1, 3.3.1.1, 3.3.2.1; Practice Points 3.3.1.1–3.3.1.2"),
                cite(GC_ICMR_CKD, "Nutrition; Lifestyle measures"), cite(GC_NG203, "1.4.9"),
            ),
        ),
    ),
    tiers = listOf(
        Tier(
            "ACE inhibitor or ARB", null,
            listOf(
                Drug(
                    "When to start — KDIGO",
                    "CKD G1–G4 without diabetes: A3 (1B) or A2 (2C). With diabetes: A2–A3 (1B). A1: for specific indications such as hypertension.",
                    citations = listOf(cite(GC_KDIGO, "Recommendations 3.6.1–3.6.3; Practice Point 3.6.6")),
                ),
                Drug(
                    "When to start — NICE",
                    "Diabetes and ACR ≥3 mg/mmol; no diabetes and ACR ≥70 (also refer); hypertension with ACR >30 mg/mmol.",
                    citations = listOf(cite(GC_NG203, "1.6.4, 1.6.7–1.6.8")),
                ),
                Drug(
                    "Telmisartan, losartan, enalapril or ramipril",
                    "Titrate to the highest approved dose that is tolerated (KDIGO; NICE). Continue even when eGFR falls below 30 (KDIGO).",
                    cautions = gcCkdRasCautions,
                    citations = listOf(
                        cite(GC_KDIGO, "Practice Points 3.6.1–3.6.5, 3.6.7"), cite(GC_NG203, "1.6.4, 1.6.14–1.6.21"),
                        cite(GC_ICMR_CKD, "BP control"),
                    ),
                ),
            ),
        ),
        Tier(
            "SGLT2 inhibitor", null,
            listOf(
                Drug(
                    "Dapagliflozin or empagliflozin",
                    "KDIGO: type 2 diabetes + CKD with eGFR ≥20 (1A); eGFR ≥20 with ACR ≥200 mg/g (≥20 mg/mmol), or heart failure (1A); eGFR 20–45 with lower ACR (2B).",
                    cautions = listOf(
                        "A reversible eGFR dip on starting is generally not a reason to stop; no change in monitoring frequency is needed (KDIGO).",
                        "Continue even if eGFR falls below 20, unless not tolerated or dialysis starts (KDIGO).",
                        "Withhold during prolonged fasting, surgery or critical illness — risk of ketosis (KDIGO).",
                    ),
                    citations = listOf(cite(GC_KDIGO, "Recommendations 3.7.1–3.7.3; Practice Points 3.7.1–3.7.3")),
                ),
                Drug(
                    "Dapagliflozin or empagliflozin — NICE and ICMR",
                    "NICE: add-on to an optimised ACEi/ARB if eGFR 20–<45, or 45–90 with ACR ≥22.6 mg/mmol or type 2 diabetes. ICMR: SGLT2 inhibitor if eGFR >20.",
                    citations = listOf(cite(GC_NG203, "1.6.9"), cite(GC_ICMR_CKD, "Diabetes control")),
                ),
            ),
        ),
        Tier(
            "Statin", null,
            listOf(
                Drug(
                    "Atorvastatin 20 mg",
                    "NICE: for primary or secondary prevention in CKD; increase if the lipid target is not met and eGFR ≥30; eGFR <30 — agree higher doses with a renal specialist.",
                    citations = listOf(cite(GC_NG238, "1.8.1–1.8.3")),
                ),
                Drug(
                    "Statin — KDIGO, age ≥50",
                    "eGFR <60, not on dialysis or transplant: statin or statin + ezetimibe (1A). eGFR ≥60 with CKD: statin (1B).",
                    citations = listOf(cite(GC_KDIGO, "Recommendations 3.15.1.1–3.15.1.2")),
                ),
                Drug(
                    "Statin — KDIGO, age 18–49",
                    "Suggested with coronary disease, diabetes, prior ischaemic stroke or estimated 10-year coronary risk >10% (2A).",
                    citations = listOf(cite(GC_KDIGO, "Recommendation 3.15.1.3")),
                ),
            ),
        ),
        Tier(
            "Other drugs", null,
            listOf(
                Drug(
                    "Finerenone (type 2 diabetes)",
                    "KDIGO: eGFR >25, normal potassium and ACR >30 mg/g despite maximum RASi (2A); start 10 mg (eGFR 25–59) or 20 mg (≥60) if potassium ≤4.8.",
                    cautions = listOf(
                        "Check potassium at 1 month, then every 4 months; hold if >5.5 mmol/L (KDIGO).",
                        "NICE: CKD stage 3–4 with type 2 diabetes, ACR ≥3 mg/mmol, eGFR ≥25, on optimised ACEi/ARB and SGLT2 inhibitor.",
                    ),
                    citations = listOf(cite(GC_KDIGO, "Recommendation 3.8.1; Figure 26"), cite(GC_NG203, "1.6.10")),
                ),
                Drug(
                    "Metformin",
                    "ICMR: do not use if eGFR <30.",
                    citations = listOf(cite(GC_ICMR_CKD, "Diabetes control")),
                ),
            ),
        ),
        Tier(
            "Complications · ICMR STW", null,
            listOf(
                Drug(
                    "Anaemia — iron, then ESA",
                    "Make iron replete (oral iron; IV iron on dialysis or if oral not tolerated); then start an ESA, titrated to Hb 10–11 g/dL. NICE: investigate at Hb ≤110 g/L.",
                    citations = listOf(cite(GC_ICMR_CKD, "Anemia management"), cite(GC_NG203, "1.7.1")),
                ),
                Drug(
                    "Phosphate binders (PO4 >5.5)",
                    "Restrict dietary phosphate; start calcium-containing binders; non-calcium binders if calcium >9 mg/dL, vascular calcification or low iPTH.",
                    citations = listOf(cite(GC_ICMR_CKD, "Management of hyperphosphatemia")),
                ),
                Drug(
                    "Sodium bicarbonate (oral)",
                    "If venous bicarbonate <18 mEq/L.",
                    citations = listOf(cite(GC_ICMR_CKD, "Mineral bone disease")),
                ),
                Drug(
                    "Hepatitis B vaccine",
                    "If HBV-negative: 20 µg IM in each deltoid at 0, 1, 2 and 6 months. Also pneumococcal, shingles and yearly influenza vaccines.",
                    citations = listOf(cite(GC_ICMR_CKD, "Vaccination schedule")),
                ),
            ),
        ),
    ),
    avoid = listOf(
        Caution(
            "NSAIDs",
            "ICMR: do not use. NICE: chronic use may speed progression — use caution over long periods and monitor GFR.",
            listOf(cite(GC_ICMR_CKD, "Lifestyle measures"), cite(GC_NG203, "1.3.9–1.3.10")),
        ),
        Caution(
            "ACE inhibitor + ARB (or direct renin inhibitor)",
            "Avoid any combination (KDIGO 1B; NICE).",
            listOf(cite(GC_KDIGO, "Recommendation 3.6.4"), cite(GC_NG203, "1.6.12")),
        ),
        Caution(
            "Unproven supplements, herbal or untested indigenous medicines",
            "ICMR: avoid. KDIGO: review and limit over-the-counter, dietary and herbal remedies.",
            listOf(cite(GC_ICMR_CKD, "Lifestyle measures"), cite(GC_KDIGO, "Practice Point 4.1.3")),
        ),
        Caution(
            "Potassium-based salt substitutes",
            "ICMR: restrict with hyperkalaemia. NICE: not for people with kidney disease.",
            listOf(cite(GC_ICMR_CKD, "Nutrition"), cite(GC_NG136, "1.4.5")),
        ),
    ),
    escalate = listOf(
        "KDIGO: refer if eGFR <30, 5-year kidney-failure risk >3–5% (validated equation), or sustained GFR fall >20% (>30% after starting haemodynamically active drugs).",
        "KDIGO: refer if ACR ≥300 mg/g (≥30 mg/mmol) with haematuria, ACR >700 mg/g (>70 mg/mmol), ACR doubling, red-cell casts or unexplained RBC >20/HPF.",
        "KDIGO: refer for uncertain or hereditary cause, recurrent extensive stones, hypertension on ≥4 drugs, persistent potassium abnormality, acidosis, anaemia, bone disease, malnutrition.",
        "NICE: refer if 5-year KFRE risk >5%, ACR ≥70 mg/mmol (unless diabetic and treated), ACR >30 with haematuria, rapid eGFR fall, or suspected renal artery stenosis.",
        "ICMR: refer every newly diagnosed patient for initial evaluation, and for rapid progression, a new complication or discussion of dialysis or transplant.",
        "ICMR: eGFR <30 — preserve veins in the non-dominant arm for an AV fistula and discuss kidney replacement options.",
    ),
    citations = listOf(
        cite(GC_KDIGO, "Figure 48; Practice Point 5.1.1"), cite(GC_NG203, "1.5.5"),
        cite(GC_ICMR_CKD, "Indications for referral; Preparation for RRT"),
    ),
)

internal val cardioRenalConditions: List<Condition> = listOf(gcHypertension, gcDyslipidaemia, gcCkd)
