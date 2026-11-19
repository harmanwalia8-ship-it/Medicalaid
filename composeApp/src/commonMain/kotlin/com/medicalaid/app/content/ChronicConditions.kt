package com.medicalaid.app.content

/** Asthma, diabetes and hypothyroidism — in pregnancy and in general adults. */
internal object ChronicConditions {

    // =============================================================================== Asthma
    private val ginaSteps = listOf(cite("gina-2026", "Box 4-6 — treatment steps (adults & adolescents)"))
    private val ginaAirMart = listOf(cite("gina-2026-summary", "Table 7 — AIR and MART doses"), cite("gina-2026", "Box 4-8"))
    private val ginaAcute = listOf(cite("gina-2026", "Box 9-4 — exacerbations in primary care"), cite("gina-2026-summary", "Managing exacerbations"))
    private val icmrAsthma = listOf(cite("icmr-stw-asthma-2019", "Flowchart"))

    private val ginaDoseTable = InfoBlock(
        "ICS daily dose bands (mcg, metered) — not an equivalence table",
        listOf(
            "Budesonide DPI/pMDI: low 200–400 · medium >400–800 · high >800.",
            "Beclometasone pMDI (standard particle): low 200–500 · medium >500–1000 · high >1000.",
            "Beclometasone DPI/pMDI (extrafine): low 100–200 · medium >200–400 · high >400.",
            "Fluticasone propionate DPI/pMDI: low 100–250 · medium >250–500 · high >500.",
            "Fluticasone furoate DPI: 100 (low–medium) · 200 (medium–high).",
            "Ciclesonide pMDI: low 80–160 · medium >160–320 · high >320.",
            "Mometasone pMDI: 200–400 (low–medium) · high >400.",
        ),
        listOf(cite("gina-2026", "Box 4-2")),
    )

    private val severeFeatures = listOf(
        "Severe: unable to speak, drink or lie down; SpO2 <92% on room air; respiratory rate >30/min; silent or quiet chest; PEF <50% of best or predicted — start treatment and arrange transfer.",
        "Life-threatening: drowsy, confused or cyanosed; transfer to intensive care if drowsy, confused or silent chest.",
    )

    val asthmaGeneral = Condition(
        id = "asthma-general",
        title = "Asthma",
        summary = "GINA 2026 steps, inhaler doses and acute exacerbations",
        keywords = listOf("asthma", "wheeze", "wheezing", "bronchial asthma", "breathlessness", "inhaler", "budesonide", "formoterol", "salbutamol", "albuterol", "levosalbutamol", "MART", "AIR", "exacerbation", "ICS"),
        population = Population.General,
        info = listOf(
            InfoBlock(
                "Key principle",
                listOf(
                    "GINA does not recommend SABA-only treatment for adults or adolescents; they should receive ICS-containing treatment.",
                    "If SABA is the only reliever available, take a low dose of ICS immediately after each SABA use.",
                ),
                listOf(cite("gina-2026", "Section 4 key points"), cite("gina-2026-summary")),
            ),
            ginaDoseTable,
        ),
        tiers = listOf(
            Tier(
                "Track 1 · preferred (ICS-formoterol reliever)",
                "Budesonide-formoterol 160/4.5 mcg delivered (200/6 metered), or beclometasone-formoterol 100/6 (≥18 years).",
                listOf(
                    Drug("Steps 1–2 · AIR-only", "Low-dose ICS-formoterol: 1 inhalation as needed.", maxDose = "Seek care if >12 inhalations in 24 h", citations = ginaAirMart),
                    Drug("Step 3 · MART", "1 inhalation twice (or once) daily, plus 1 as needed.", maxDose = "Seek care if >12 inhalations in 24 h", citations = ginaAirMart),
                    Drug("Step 4 · MART", "2 inhalations twice daily (medium-dose maintenance), plus 1 as needed.", maxDose = "Seek care if >12 inhalations in 24 h", citations = ginaAirMart),
                    Drug("Step 5", "Consider add-on LAMA; refer for phenotypic assessment; consider a trial of high-dose maintenance ICS-formoterol; consider biologics (anti-IgE, anti-IL5/5R, anti-IL4Rα, anti-TSLP).", citations = ginaSteps),
                ),
            ),
            Tier(
                "Track 2 · alternative",
                "Reliever: as-needed low-dose ICS-SABA (or SABA if not available, with close attention to ICS adherence).",
                listOf(
                    Drug("Step 1", "As-needed low-dose ICS + SABA (combination or separate inhalers).", citations = ginaSteps),
                    Drug("Step 2", "Low-dose maintenance ICS.", citations = ginaSteps),
                    Drug("Step 3", "Low-dose maintenance ICS-LABA.", citations = ginaSteps),
                    Drug("Step 4", "Medium-dose maintenance ICS-LABA.", citations = ginaSteps),
                    Drug("Step 5", "As Track 1, with high-dose maintenance ICS-LABA.", citations = ginaSteps),
                ),
            ),
            Tier(
                "Acute exacerbation · primary care", null,
                listOf(
                    Drug(
                        "Mild — salbutamol or budesonide-formoterol",
                        "Salbutamol 4 puffs by pMDI + spacer, or budesonide-formoterol 160/4.5 mcg 2 puffs. Repeat once after 30–60 min if needed.",
                        citations = ginaAcute,
                    ),
                    Drug(
                        "Moderate — salbutamol ± ipratropium",
                        "Salbutamol 4–6 puffs by pMDI + spacer (or 2.5 mg nebulised), up to 3 times, 20–30 min apart. Add ipratropium 4 puffs if needed. Start oral corticosteroid.",
                        citations = ginaAcute,
                    ),
                    Drug(
                        "Severe or life-threatening",
                        "Arrange transfer and start treatment immediately: salbutamol 6–10 puffs by pMDI + spacer (or 5 mg nebulised), repeat every 20–30 min if needed; oxygen to SpO2 92–95%; systemic corticosteroids. If anaphylaxis, give epinephrine first.",
                        citations = ginaAcute,
                    ),
                    Drug(
                        "Prednisolone",
                        "Adults: 40–50 mg each morning for 5–7 days (1 mg/kg/day, maximum 50 mg/day).",
                        maxDose = "50 mg/day",
                        citations = listOf(cite("gina-2026-summary", "Oral corticosteroids"), cite("gina-2026", "p.180")),
                    ),
                ),
            ),
            Tier(
                "India · ICMR workflow (2019)",
                "Differs from GINA 2026: allows SABA alone at step 1, budesonide-formoterol 2 puffs BD, and prednisolone 0.5 mg/kg.",
                listOf(
                    Drug("Intermittent symptoms", "Levosalbutamol 50 mcg or salbutamol 100 mcg, 2 puffs as needed.", citations = icmrAsthma),
                    Drug("Regular symptoms (<2/week, >2/month)", "Budesonide 100 mcg 2 puffs twice daily, plus levosalbutamol 50 mcg 2 puffs as needed.", citations = icmrAsthma),
                    Drug("Symptoms >2/week or exacerbation in past year", "Formoterol/budesonide 6/200 mcg single inhaler, 2 puffs twice daily and as needed.", citations = icmrAsthma),
                    Drug("Next steps", "Add tiotropium 9 mcg 2 puffs once daily and low-dose oral methylxanthine; then low-dose oral corticosteroids and refer. Reassess at 3–4 weeks.", citations = icmrAsthma),
                    Drug(
                        "Severe acute asthma (admit)",
                        "Oxygen to SpO2 92–95%; nebulised levosalbutamol/ipratropium 1.25 mg/0.5 mg, three doses 20 minutes apart; hydrocortisone 200 mg IV, then prednisolone 0.5 mg/kg daily for 5 days.",
                        citations = icmrAsthma,
                    ),
                    Drug("Non-severe exacerbation", "Prednisolone 0.5 mg/kg daily for 5 days; follow up at one week.", citations = icmrAsthma),
                ),
            ),
        ),
        escalate = severeFeatures + listOf(
            "Discharge when SpO2 ≥92% on room air and PEF >70%; preferably start Step 4 ICS-formoterol MART; review within 2–7 days.",
        ),
        citations = listOf(cite("gina-2026", "Box 9-4"), cite("gina-2026-summary")),
    )

    val asthmaPregnancy = Condition(
        id = "asthma-pregnancy",
        title = "Asthma",
        summary = "Keep ICS going; preferred agents and exacerbations",
        keywords = listOf("asthma", "wheeze", "wheezing", "bronchial asthma", "inhaler", "budesonide", "salbutamol", "albuterol", "ICS", "exacerbation"),
        population = Population.Pregnancy,
        info = listOf(
            InfoBlock(
                "Principles",
                listOf(
                    "The advantages of actively treating asthma in pregnancy markedly outweigh any potential risks of usual asthma medicines.",
                    "ICS, beta2-agonists, montelukast and theophylline are not associated with an increased incidence of fetal abnormalities.",
                    "Do not stop ICS-containing therapy — stopping is a significant risk factor for exacerbations. Give low priority to stepping down until after delivery.",
                    "Review every 4–6 weeks.",
                ),
                listOf(cite("gina-2026", "Section 6 — pregnancy")),
            ),
            InfoBlock(
                "Preferred agents (NAEPP)",
                listOf(
                    "Budesonide is the preferred ICS (most reassuring data); no data indicate other ICS are unsafe, and they may be continued if asthma was well controlled on them before pregnancy.",
                    "Albuterol (salbutamol) is the preferred SABA.",
                ),
                listOf(cite("naepp-pregnancy-2004")),
            ),
            ginaDoseTable,
        ),
        tiers = listOf(
            Tier(
                "Controller", "Continue the pre-pregnancy regimen if well controlled.",
                listOf(
                    Drug(
                        "Budesonide (inhaled)", "Dose by step: low 200–400 mcg/day, medium >400–800, high >800 (metered).",
                        citations = listOf(cite("naepp-pregnancy-2004"), cite("gina-2026", "Box 4-2")),
                    ),
                ),
            ),
            Tier(
                "Reliever", null,
                listOf(Drug("Salbutamol (albuterol)", "As needed.", citations = listOf(cite("naepp-pregnancy-2004")))),
            ),
            Tier(
                "Exacerbation", "Manage aggressively.",
                listOf(
                    Drug(
                        "SABA, oxygen and early systemic corticosteroid",
                        "Treat with SABA, oxygen and early systemic corticosteroids; dosing as for non-pregnant adults (see Asthma · General adult).",
                        cautions = listOf("NAEPP (emergency care): give oxygen to keep SpO2 ≥95% and assess the fetus."),
                        citations = listOf(cite("gina-2026", "Section 6 — pregnancy"), cite("naepp-pregnancy-2004", "Emergency management")),
                    ),
                ),
            ),
            Tier(
                "Labour and delivery", null,
                listOf(
                    Drug(
                        "Usual maintenance medicines", "Continue usual maintenance, with reliever if needed.",
                        cautions = listOf("If high-dose SABA was given in labour, monitor the baby's blood glucose for the first 24 hours (especially if preterm)."),
                        citations = listOf(cite("gina-2026", "Section 6 — labour and delivery")),
                    ),
                ),
            ),
        ),
        escalate = severeFeatures,
        citations = listOf(cite("gina-2026"), cite("naepp-pregnancy-2004")),
    )

    // ========================================================================= Hypothyroidism
    private val mohfwThyroid = listOf(cite("mohfw-thyroid-2014", "Treatment"))

    private val ata26 = "ata-2026-pregnancy"

    val thyroidPregnancy = Condition(
        id = "thyroid-pregnancy",
        title = "Hypothyroidism",
        summary = "TSH by trimester — ATA 2026 and MoHFW side by side",
        keywords = listOf("hypothyroidism", "thyroid", "TSH", "levothyroxine", "thyroxine", "eltroxin", "subclinical hypothyroidism", "goitre", "TPO", "hypothyroxinemia"),
        population = Population.Pregnancy,
        tool = ToolKind.TshPregnancy,
        info = listOf(
            InfoBlock(
                "Who to test",
                listOf(
                    "ATA 2026: assess every newly pregnant woman for risk factors; offer TSH on a positive pregnancy test if at increased risk — history of thyroid dysfunction or postpartum thyroiditis, known thyroid antibodies, symptoms or goitre, thyroid-affecting medicines, autoimmune disease, ≥2 miscarriages, infertility, Down or Turner syndrome, head/neck irradiation or radioiodine, prior thyroid surgery, family history of autoimmune thyroid disease.",
                    "MoHFW: screen high-risk women — iodine-deficient area, BMI ≥30, prior thyroid disease or goitre, family history, autoimmune disease, recurrent miscarriage, preterm birth or pre-eclampsia history, infertility, amiodarone or lithium.",
                    "ACOG: universal screening is not recommended; TSH is the first-line test when indicated.",
                ),
                listOf(cite(ata26, "Recommendations Table 2; Table 1"), cite("mohfw-thyroid-2014", "Who to screen"), cite("acog-pb223")),
            ),
            InfoBlock(
                "Reference ranges",
                listOf(
                    "ATA 2026: lab- and trimester-specific ranges preferred. If unavailable, TSH 0.1–4.0 mU/L in the 1st and 2nd trimesters; the non-pregnant range will likely suffice in the 3rd.",
                    "MoHFW 2014: 1st trimester 0.1–2.5, 2nd 0.2–3, 3rd 0.3–3 mIU/L.",
                    "Use the same assay for follow-up during the pregnancy (ATA).",
                ),
                listOf(cite(ata26, "Recommendations Table 1"), cite("mohfw-thyroid-2014", "Reference ranges")),
            ),
            InfoBlock(
                "Where the guidelines differ",
                listOf(
                    "MoHFW starts levothyroxine whenever TSH exceeds 2.5 (1st trimester) or 3 (later), at any gestation.",
                    "ATA 2026 treats mild subclinical hypothyroidism only as an option in the 1st trimester, and advises against starting it after the 1st trimester (Strong, High).",
                ),
                listOf(cite(ata26, "Recommendations Table 10"), cite("mohfw-thyroid-2014")),
            ),
            InfoBlock(
                "Already on levothyroxine",
                listOf(
                    "ATA 2026: most need ~25% more by week 12 and ~50% by week 20 — increase the dose by about 25% on a positive pregnancy test (raise the daily dose, or add two extra daily doses per week).",
                    "Overtreatment is more likely if pre-pregnancy TSH <1.5, dose >100 mcg/day, or the increase is two tablets per week.",
                    "TSH on confirmation of pregnancy, about every 4 weeks in the first half, at least once in the 3rd trimester, and 4–6 weeks after any dose change. A TSH within range but below 2.5 may be targeted.",
                    "MoHFW: continue treatment during pregnancy with the same target range.",
                ),
                listOf(cite(ata26, "Recommendations Tables 2, 10; Table 1 notes"), cite("mohfw-thyroid-2014")),
            ),
        ),
        tiers = listOf(
            Tier(
                "ATA 2026 · new diagnosis in pregnancy", null,
                listOf(
                    Drug(
                        "Overt hypothyroidism (low FT4), TSH ≥6",
                        "Start levothyroxine (Strong, Moderate): estimate from full replacement 1.5–1.7 mcg/kg/day plus 20–30% for gestation. If TSH <6, a confirmatory test within 3 weeks may be done first.",
                        weightDose = WeightDose(1.8, 2.21, unit = "mcg", per = "per day (1.5–1.7 mcg/kg + 20–30%)"),
                        citations = listOf(cite(ata26, "Recommendations Table 9; text")),
                    ),
                    Drug(
                        "Subclinical, TSH >10",
                        "Treat with levothyroxine (Good Practice Statement). Low-dose start of 25–75 mcg/day depending on TSH and weight.",
                        citations = listOf(cite(ata26, "Recommendations Table 10; text")),
                    ),
                    Drug(
                        "Mild subclinical — 1st trimester",
                        "Levothyroxine may be considered (Conditional, Low) after shared decision-making; 25–75 mcg/day. A repeat test within ~3 weeks can be done first — many normalise.",
                        citations = listOf(cite(ata26, "Recommendations Table 10; Flowchart 2")),
                    ),
                    Drug(
                        "Mild subclinical — after 1st trimester",
                        "Do not offer levothyroxine (Strong, High). Recheck TSH after 4–6 weeks.",
                        citations = listOf(cite(ata26, "Recommendations Table 10")),
                    ),
                    Drug(
                        "Monitoring on treatment",
                        "TSH every 4 weeks until mid-gestation and at least once around 30 weeks; TSH within range but <2.5 may be targeted.",
                        citations = listOf(cite(ata26, "Recommendations Table 10")),
                    ),
                ),
            ),
            Tier(
                "MoHFW India · levothyroxine by TSH", "Trimester ranges: 1st 0.1–2.5 · 2nd 0.2–3 · 3rd 0.3–3 mIU/L.",
                listOf(
                    Drug("Levothyroxine — TSH above trimester limit up to 10", "25 mcg once daily.", citations = mohfwThyroid),
                    Drug("Levothyroxine — TSH above 10", "50 mcg once daily.", citations = mohfwThyroid),
                    Drug(
                        "Monitoring", "Repeat TSH after 4–6 weeks. Target TSH <2.5 in the 1st trimester and <3 in the 2nd and 3rd; avoid TSH <0.1.",
                        citations = mohfwThyroid,
                    ),
                ),
            ),
            Tier(
                "After delivery", null,
                listOf(
                    Drug(
                        "ATA 2026",
                        "Return to the pre-pregnancy dose at delivery and test thyroid function after about 6 weeks. If treated only for subclinical hypothyroidism, aim for a shared-decision cessation trial.",
                        citations = listOf(cite(ata26, "Recommendations Table 2; subclinical hypothyroidism text")),
                    ),
                    Drug(
                        "MoHFW", "Initial TSH >10: continue. Initial TSH below 10: stop after delivery. Repeat TSH at 6 weeks postpartum.",
                        citations = mohfwThyroid,
                    ),
                ),
            ),
        ),
        avoid = listOf(
            Caution("Levothyroxine for euthyroid TPOAb/TgAb-positive women", "Should not be offered (Strong, High); nor selenium, glucocorticoids or IVIG.", listOf(cite(ata26, "Recommendations Table 11"))),
            Caution("Levothyroxine for isolated hypothyroxinaemia", "Do not offer (1st trimester: Conditional, Low; later: Strong, High).", listOf(cite(ata26, "Recommendations Table 12"))),
            Caution("Liothyronine (LT3) or desiccated thyroid", "Use levothyroxine monotherapy only in pregnancy (Strong, Low).", listOf(cite(ata26, "Recommendations Table 9"))),
        ),
        citations = listOf(cite(ata26), cite("mohfw-thyroid-2014"), cite("acog-pb223")),
    )

    private val nice145 = cite("nice-ng145")

    val thyroidGeneral = Condition(
        id = "thyroid-general",
        title = "Hypothyroidism",
        summary = "Interpret TSH, start levothyroxine, monitor",
        keywords = listOf("hypothyroidism", "thyroid", "TSH", "levothyroxine", "thyroxine", "eltroxin", "subclinical hypothyroidism", "FT4", "free T4"),
        population = Population.General,
        tool = ToolKind.TshGeneral,
        info = listOf(
            InfoBlock(
                "Definitions",
                listOf(
                    "Overt hypothyroidism: raised TSH with low free T4.",
                    "Subclinical hypothyroidism: TSH above the reference range with free T4 within range.",
                ),
                listOf(cite("icmr-stw-hypothyroid-2022"), cite("ata-aace-2012"), cite("nice-ng145", "Terms used")),
            ),
            InfoBlock(
                "Targets and monitoring",
                listOf(
                    "Keep TSH within the reference range; avoid doses that suppress TSH (NICE 1.4.1).",
                    "Check TSH 4–6 weeks after any dose change (ATA). NICE: every 3 months until stable (two similar values 3 months apart), then yearly.",
                    "If TSH was very high, it can take up to 6 months to return to range (NICE 1.4.2).",
                    "Older adults: higher TSH targets may be appropriate (ATA).",
                ),
                listOf(cite("nice-ng145", "1.4.1–1.4.3"), cite("ata-2014", "Recs 4b, 6a")),
            ),
        ),
        tiers = listOf(
            Tier(
                "Overt hypothyroidism · starting dose", null,
                listOf(
                    Drug(
                        "Levothyroxine — adults <65 without cardiovascular disease",
                        "1.6 mcg/kg/day, rounded to the nearest 25 mcg (NICE). ICMR: 1.6–1.8 mcg/kg/day.",
                        weightDose = WeightDose(1.6, 1.8, unit = "mcg", per = "per day, once daily"),
                        citations = listOf(cite("nice-ng145", "1.3.6"), cite("icmr-stw-hypothyroid-2022")),
                    ),
                    Drug(
                        "Levothyroxine — age ≥65 or cardiovascular disease",
                        "25–50 mcg/day with titration (NICE). Known coronary artery disease: 12.5–25 mcg/day (ATA). ICMR: 12.5–25 mcg/day, increasing by 12.5–25 mcg every 3–4 weeks.",
                        citations = listOf(cite("nice-ng145", "1.3.7"), cite("ata-2014"), cite("icmr-stw-hypothyroid-2022")),
                    ),
                ),
            ),
            Tier(
                "Subclinical hypothyroidism", null,
                listOf(
                    Drug(
                        "TSH ≥10 mIU/L",
                        "Consider levothyroxine if TSH is 10 or higher on 2 separate occasions 3 months apart. ATA/AACE: 25–75 mcg daily should be considered.",
                        citations = listOf(cite("nice-ng145", "1.5.3"), cite("ata-aace-2012", "R15, R22.8")),
                    ),
                    Drug(
                        "TSH above range but <10",
                        "Age <65 with symptoms and TSH raised on 2 occasions 3 months apart: consider a 6-month trial of levothyroxine; stop if symptoms persist once TSH is in range. If untreated: TSH and FT4 yearly if features of thyroid disease, otherwise every 2–3 years.",
                        cautions = listOf("ICMR: treat if large goitre, positive TPO antibody, ASCVD, heart failure, dyslipidaemia, infertility, depression, refractory anaemia, or autoimmune history."),
                        citations = listOf(cite("nice-ng145", "1.5.4–1.5.7"), cite("icmr-stw-hypothyroid-2022")),
                    ),
                ),
            ),
            Tier(
                "How to take it", null,
                listOf(
                    Drug(
                        "Levothyroxine administration",
                        "Take consistently, 60 minutes before breakfast or at bedtime (ATA); ICMR: single fasting dose with nothing by mouth for 1 hour. Separate from calcium carbonate and ferrous sulfate.",
                        citations = listOf(cite("ata-2014", "Recs 3a–3b"), cite("icmr-stw-hypothyroid-2022")),
                    ),
                ),
            ),
        ),
        citations = listOf(nice145, cite("ata-2014"), cite("icmr-stw-hypothyroid-2022")),
    )


    // =============================================================================== Diabetes
    val diabetesGeneral = Condition(
        id = "diabetes-general",
        title = "Diabetes (type 2)",
        summary = "Diagnosis, HbA1c targets, therapy by HbA1c, glucose ↔ HbA1c",
        keywords = listOf("diabetes", "diabetes mellitus", "DM", "T2DM", "type 2", "sugar", "hyperglycaemia", "hyperglycemia", "HbA1c", "A1c", "RBS", "FBS", "PPBS", "metformin", "insulin", "SGLT2", "GLP-1", "prediabetes", "eAG"),
        population = Population.General,
        tool = ToolKind.Glycemia,
        info = listOf(
            InfoBlock(
                "Diagnosis",
                listOf(
                    "Diabetes: HbA1c ≥6.5%, fasting plasma glucose ≥126 mg/dL (8 h fast), 2-h glucose ≥200 mg/dL on a 75 g OGTT, or random glucose ≥200 mg/dL with classic symptoms or hyperglycaemic crisis.",
                    "Without unequivocal hyperglycaemia, confirm with two abnormal results — two different tests (can be the same sample) or the same test at two time points.",
                    "Prediabetes: HbA1c 5.7–6.4%, fasting 100–125 mg/dL, or 2-h OGTT 140–199 mg/dL.",
                    "Use plasma glucose, not HbA1c, when the HbA1c–glucose relationship is altered: some haemoglobin variants, pregnancy, G6PD deficiency, HIV, or altered red-cell turnover.",
                ),
                listOf(cite("ada-s2", "Tables 2.1–2.2; Recs 2.1b, 2.4"), cite("icmr-stw-t2dm-2022", "Diagnosis")),
            ),
            InfoBlock(
                "Targets",
                listOf(
                    "HbA1c <7% for many non-pregnant adults without significant hypoglycaemia (ADA 6.3a; ICMR STW ≤7.0% except elderly or significant comorbidity).",
                    "Lower (<6.5%) may suit healthy adults at low treatment risk; less stringent (up to ~8%) with frailty, severe comorbidity or limited life expectancy.",
                    "Capillary glucose: pre-meal 80–130 mg/dL; peak post-meal (1–2 h) <180 mg/dL.",
                ),
                listOf(cite("ada-s6", "Recs 6.3a–6.5; Table 6.3"), cite("icmr-stw-t2dm-2022", "Targets")),
            ),
        ),
        tiers = listOf(
            Tier(
                "Therapy by HbA1c · India (ICMR workflow)", null,
                listOf(
                    Drug("HbA1c <8.5%", "Monotherapy: metformin.", citations = listOf(cite("icmr-stw-t2dm-2022", "Pharmacotherapy"))),
                    Drug("HbA1c 8.5–10%", "Dual therapy: metformin + SU / TZD / DPP-4 inhibitor / SGLT2 inhibitor / AGI / GLP-1 RA.", citations = listOf(cite("icmr-stw-t2dm-2022", "Pharmacotherapy"))),
                    Drug("HbA1c >10%", "Basal insulin + metformin + another oral agent, or a triple oral combination.", citations = listOf(cite("icmr-stw-t2dm-2022", "Pharmacotherapy"))),
                ),
            ),
            Tier(
                "Choosing agents · ADA 2026", "Organ-protective agents are indicated irrespective of HbA1c.",
                listOf(
                    Drug(
                        "Initial therapy",
                        "Metformin or another agent (including combination therapy) that achieves and maintains the glycaemic goal; consider initial combination therapy to reach goal sooner.",
                        citations = listOf(cite("ada-s9", "Figure 9.4; Rec 9.6")),
                    ),
                    Drug("Established or high risk of ASCVD", "Include a GLP-1 RA and/or SGLT2 inhibitor with proven cardiovascular benefit.", citations = listOf(cite("ada-s9", "Rec 9.7"))),
                    Drug("Heart failure (reduced or preserved EF)", "SGLT2 inhibitor.", citations = listOf(cite("ada-s9", "Rec 9.8"))),
                    Drug(
                        "Chronic kidney disease (eGFR 20–60 and/or albuminuria)",
                        "SGLT2 inhibitor or GLP-1 RA with proven benefit. Glycaemic effect of SGLT2 inhibitors is reduced at eGFR <45.",
                        citations = listOf(cite("ada-s9", "Rec 9.10")),
                    ),
                ),
            ),
            Tier(
                "Metformin", null,
                listOf(
                    Drug(
                        "Metformin",
                        "Start 500 mg daily after meals; increase by 500 mg every two weeks to goal.",
                        maxDose = "2,500 mg/day (ICMR)",
                        cautions = listOf(
                            "eGFR <45: do not start; reduce (ICMR: halve) the dose. eGFR <30: stop.",
                            "Check vitamin B12 periodically.",
                        ),
                        citations = listOf(cite("icmr-t2dm-2018", "7.1.1 — metformin"), cite("ada-s9", "Metformin and kidney function")),
                    ),
                ),
            ),
            Tier(
                "Insulin", null,
                listOf(
                    Drug(
                        "When to start",
                        "Consider insulin regardless of other therapy if there are symptoms of hyperglycaemia, HbA1c >10%, or glucose ≥300 mg/dL — especially with catabolic features (weight loss, ketosis).",
                        citations = listOf(cite("ada-s9", "Rec 9.20"), cite("icmr-t2dm-2018", "Insulin")),
                    ),
                    Drug(
                        "Basal insulin",
                        "Start 10 units/day or 0.1–0.2 units/kg/day (ICMR: 0.2 units/kg/day). Increase by 2 units every 3 days to the fasting goal. Unexplained hypoglycaemia: reduce by 10–20%.",
                        weightDose = WeightDose(0.1, 0.2, unit = "units", per = "per day"),
                        citations = listOf(cite("ada-s9", "Figure 9.5"), cite("icmr-t2dm-2018", "Insulin")),
                    ),
                ),
            ),
        ),
        citations = listOf(cite("ada-s2"), cite("ada-s6"), cite("ada-s9"), cite("icmr-stw-t2dm-2022"), cite("icmr-t2dm-2018")),
    )

    private val gdm18 = listOf(cite("mohfw-gdm-2018", "Management"))

    val diabetesPregnancy = Condition(
        id = "diabetes-pregnancy",
        title = "Diabetes (GDM & pre-existing)",
        summary = "MoHFW GDM protocol, glucose and HbA1c targets, insulin first",
        keywords = listOf("diabetes", "GDM", "gestational diabetes", "sugar", "OGTT", "DIPSI", "PPBS", "insulin", "metformin", "pregestational diabetes", "HbA1c", "hyperglycaemia"),
        population = Population.Pregnancy,
        info = listOf(
            InfoBlock(
                "Diagnosis (India)",
                listOf(
                    "MoHFW: 75 g oral glucose regardless of fasting state at the first contact, repeated at 24–28 weeks if negative; 2-h plasma glucose ≥140 mg/dL = GDM. See the Pregnancy tab for dates.",
                    "ICMR workflow: fasting ≥126 mg/dL and/or HbA1c ≥6.5% suggests pre-existing diabetes; fasting 92–125 mg/dL suggests GDM.",
                ),
                listOf(cite("mohfw-gdm-2018", "Screening and diagnosis"), cite("icmr-stw-t2dm-2022", "Pregnancy")),
            ),
            InfoBlock(
                "Targets",
                listOf(
                    "ADA: fasting <95 mg/dL and either 1-h post-meal <140 or 2-h <120 mg/dL. (ICMR 2018: fasting <90.)",
                    "HbA1c in pregnancy: ideally <6% if achievable without significant hypoglycaemia; may relax to <7%.",
                    "Before conception: ideally HbA1c <6.5%.",
                    "Do not use estimated-HbA1c calculations in pregnancy (ADA 15.13) — the glucose ↔ HbA1c calculator is on the general adult page only.",
                ),
                listOf(cite("ada-s15", "Recs 15.3, 15.8, 15.9, 15.13"), cite("icmr-t2dm-2018", "Table 4.3")),
            ),
        ),
        tiers = listOf(
            Tier(
                "GDM · MoHFW protocol", null,
                listOf(
                    Drug("Medical nutrition therapy + exercise", "For 2 weeks, then check 2-h post-prandial blood sugar (PPBS). If PPBS ≥120 mg/dL, start medical management.", citations = gdm18),
                    Drug(
                        "Insulin", "First drug of choice. If PPBS >200 mg/dL at diagnosis, start 8 units of premixed insulin.",
                        cautions = listOf("Refer if more than 20 units/day are needed."),
                        citations = gdm18,
                    ),
                    Drug(
                        "Metformin", "Can be considered after 20 weeks: 500 mg twice daily.",
                        maxDose = "2 g/day — refer if more is needed",
                        citations = gdm18,
                    ),
                ),
            ),
            Tier(
                "Agent choice · ADA 2026", null,
                listOf(
                    Drug(
                        "Insulin",
                        "Required for type 1 diabetes; preferred agent for GDM and type 2 diabetes in pregnancy.",
                        citations = listOf(cite("ada-s15", "Rec 15.17")),
                    ),
                    Drug(
                        "Metformin and glyburide",
                        "Not first-line — both cross the placenta and may not reach goals. Avoid metformin with hypertension, pre-eclampsia or risk of growth restriction. Other non-insulin agents are not recommended.",
                        citations = listOf(cite("ada-s15", "Rec 15.21")),
                    ),
                    Drug(
                        "Low-dose aspirin (type 1 or type 2 diabetes)",
                        "100–150 mg/day from 12–16 weeks to lower pre-eclampsia risk (162 mg/day may be acceptable).",
                        citations = listOf(cite("ada-s15", "Rec 15.23")),
                    ),
                ),
            ),
        ),
        escalate = listOf(
            "MoHFW: refer if insulin >20 units/day or metformin >2 g/day is needed.",
            "Routine delivery before 39 weeks is not recommended for GDM (MoHFW).",
            "After GDM: 75 g OGTT (fasting and 2-h) at 6 weeks postpartum — <140 normal, 140–199 IGT, ≥200 diabetes; if normal, screen annually.",
        ),
        citations = listOf(cite("mohfw-gdm-2018"), cite("ada-s15")),
    )

    val pregnancy: List<Condition> = listOf(diabetesPregnancy, thyroidPregnancy, asthmaPregnancy)
    val general: List<Condition> = listOf(diabetesGeneral, thyroidGeneral, asthmaGeneral)
}
