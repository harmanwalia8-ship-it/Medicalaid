package com.medicalaid.app.content

/** Complaints in pregnancy. Doses are written as the cited source states them. */
internal object PregnancyConditions {

    private val pb189 = listOf(cite("acog-pb189", "Figure 1 — treatment algorithm"))

    val nausea = Condition(
        id = "nvp",
        title = "Nausea & vomiting",
        summary = "Stepwise ACOG algorithm, from ginger and B6 to hyperemesis",
        keywords = listOf("vomiting", "nausea", "emesis", "hyperemesis gravidarum", "morning sickness", "NVP", "HG", "retching"),
        firstSteps = listOf(
            "Switch the prenatal vitamin to a folic acid supplement only.",
            "Consider P6 acupressure.",
        ),
        tiers = listOf(
            Tier(
                "Step 1 · Start here", null,
                listOf(Drug("Ginger", "250 mg capsules orally four times daily.", citations = pb189)),
            ),
            Tier(
                "Step 2 · First-line drug therapy", "Choose one.",
                listOf(
                    Drug(
                        "Pyridoxine (vitamin B6) ± doxylamine",
                        "Pyridoxine 10–25 mg orally, alone or with doxylamine 12.5 mg orally, 3 or 4 times per day.",
                        citations = pb189,
                    ),
                    Drug(
                        "Doxylamine–pyridoxine 10/10 mg (delayed release)",
                        "Two tablets at bedtime initially; up to four tablets per day (one in the morning, one mid-afternoon, two at bedtime).",
                        maxDose = "4 tablets/day",
                        citations = pb189,
                    ),
                    Drug(
                        "Doxylamine–pyridoxine 20/20 mg (extended release)",
                        "One tablet at bedtime initially; up to two tablets per day.",
                        maxDose = "2 tablets/day",
                        citations = pb189,
                    ),
                ),
            ),
            Tier(
                "Step 3 · If symptoms persist", "Add one of these.",
                listOf(
                    Drug(
                        "Dimenhydrinate", "25–50 mg orally every 4–6 hours as needed.",
                        maxDose = "200 mg/day if also taking doxylamine",
                        citations = pb189,
                    ),
                    Drug("Diphenhydramine", "25–50 mg orally every 4–6 hours.", citations = pb189),
                    Drug("Prochlorperazine", "25 mg rectally every 12 hours.", citations = pb189),
                    Drug("Promethazine", "12.5–25 mg every 4–6 hours, orally or rectally.", citations = pb189),
                ),
            ),
            Tier(
                "Step 4a · No dehydration", "Add any of these.",
                listOf(
                    Drug(
                        "Metoclopramide", "5–10 mg every 6–8 hours, orally or intramuscularly.",
                        cautions = listOf("Risk of tardive dyskinesia; rarely neuroleptic malignant syndrome."),
                        citations = pb189,
                    ),
                    Drug(
                        "Ondansetron", "4 mg orally every 8 hours.",
                        cautions = listOf(
                            "Before 10 weeks, individualise use weighing risks and benefits.",
                            "QT prolongation: check electrolytes and ECG if arrhythmia risk factors; caution combining with phenothiazines.",
                        ),
                        citations = pb189,
                    ),
                    Drug("Promethazine", "12.5–25 mg every 4–6 hours, orally, rectally or intramuscularly.", citations = pb189),
                    Drug("Trimethobenzamide", "200 mg intramuscularly every 6–8 hours.", citations = pb189),
                ),
            ),
            Tier(
                "Step 4b · With dehydration", "Intravenous fluid replacement, then add any of these.",
                listOf(
                    Drug(
                        "Thiamine", "100 mg IV with the initial rehydration fluid, then 100 mg daily for the next 2–3 days (followed by IV multivitamins).",
                        cautions = listOf(
                            "For women needing IV hydration who have vomited for more than 3 weeks.",
                            "Give before dextrose to prevent Wernicke encephalopathy.",
                        ),
                        citations = pb189,
                    ),
                    Drug("Dimenhydrinate", "50 mg IV (in 50 mL saline over 20 minutes) every 4–6 hours.", citations = pb189),
                    Drug("Metoclopramide", "5–10 mg IV every 8 hours.", citations = pb189),
                    Drug(
                        "Ondansetron", "8 mg IV over 15 minutes every 12 hours.",
                        cautions = listOf("FDA: do not give more than 16 mg IV in a single dose."),
                        citations = pb189,
                    ),
                    Drug("Promethazine", "12.5–25 mg IV every 4–6 hours.", citations = pb189),
                ),
            ),
            Tier(
                "Step 5 · Refractory", null,
                listOf(
                    Drug("Chlorpromazine", "25–50 mg IV or IM every 4–6 hours, or 10–25 mg orally every 4–6 hours.", citations = pb189),
                    Drug(
                        "Methylprednisolone",
                        "16 mg every 8 hours, orally or IV, for 3 days. Taper over 2 weeks to the lowest effective dose.",
                        maxDose = "Limit total duration to 6 weeks",
                        cautions = listOf(
                            "Last-resort treatment. Avoid as a first-line agent before 10 weeks (oral clefts).",
                            "If no response within 3 days, stop.",
                        ),
                        citations = pb189,
                    ),
                ),
            ),
        ),
        escalate = listOf(
            "Admit if she cannot tolerate liquids without vomiting and has not responded to outpatient management.",
            "Admit for change in vital signs or mental status, continued weight loss, or refractory symptoms.",
            "If nutritional support is needed, enteral tube feeding is first line.",
        ),
        citations = listOf(cite("acog-pb189")),
    )

    val feverPain = Condition(
        id = "fever-pain",
        title = "Fever & pain",
        summary = "Paracetamol first; NSAIDs restricted from 20 weeks",
        keywords = listOf("fever", "pyrexia", "pain", "analgesic", "antipyretic", "paracetamol", "acetaminophen", "body ache", "backache", "nsaid", "ibuprofen"),
        tiers = listOf(
            Tier(
                "First choice", null,
                listOf(
                    Drug(
                        "Paracetamol (acetaminophen)",
                        "Lowest effective dose for the shortest necessary duration.",
                        maxDose = "4,000 mg in 24 hours (adults)",
                        cautions = listOf(
                            "ACOG (2025): remains the analgesic and antipyretic of choice in pregnancy; current evidence does not support a causal link with neurodevelopmental disorders and no change in practice is warranted.",
                            "FDA notice (2025): causal relationship not established; consider minimising use for routine low-grade fevers.",
                        ),
                        citations = listOf(cite("acog-pa-apap"), cite("fda-apap", "Maximum daily dose"), cite("fda-apap-notice-2025")),
                    ),
                ),
            ),
        ),
        avoid = listOf(
            Caution(
                "NSAIDs (ibuprofen, diclofenac, naproxen…)",
                "Limit between 20 and 30 weeks and avoid after 30 weeks (fetal renal dysfunction, oligohydramnios). If needed, lowest dose for the shortest time; consider amniotic fluid ultrasound if used beyond 48 hours. Does not apply to low-dose aspirin 81 mg.",
                listOf(cite("fda-nsaid-2020")),
            ),
        ),
        citations = listOf(cite("acog-pa-apap"), cite("fda-nsaid-2020")),
    )

    private val cpg3 = listOf(cite("acog-cpg3", "Acute treatment"))

    val headache = Condition(
        id = "headache",
        title = "Headache & migraine",
        summary = "Acute treatment, what to avoid, and red flags",
        keywords = listOf("headache", "migraine", "cephalgia", "head pain", "triptan", "sumatriptan"),
        tiers = listOf(
            Tier(
                "First line", null,
                listOf(
                    Drug(
                        "Paracetamol (acetaminophen)", "1,000 mg orally; may be combined with caffeine.",
                        cautions = listOf("Keep total caffeine to no more than 200 mg per day."),
                        citations = cpg3,
                    ),
                    Drug(
                        "Metoclopramide + diphenhydramine", "Metoclopramide 10 mg IV with diphenhydramine 25 mg IV (diphenhydramine counters akathisia).",
                        citations = cpg3,
                    ),
                ),
            ),
            Tier(
                "Second line", "For persistent headache.",
                listOf(
                    Drug("Sumatriptan", "Usual labelled dose. Other triptans have less data in pregnancy.", citations = cpg3),
                    Drug(
                        "NSAID — single dose", "e.g. ibuprofen — second trimester only.",
                        cautions = listOf("Avoid in the first and third trimesters."),
                        citations = cpg3 + cite("fda-nsaid-2020"),
                    ),
                ),
            ),
        ),
        avoid = listOf(
            Caution("Butalbital", "No pain benefit; medication-overuse headache; associated with fetal cardiac anomalies.", cpg3),
            Caution("Opioids", "Medication-overuse headache and neonatal opioid withdrawal syndrome.", cpg3),
            Caution("Ergots, gepants, lasmiditan", "Ergots: avoid. Gepants and lasmiditan: no human data.", cpg3),
        ),
        escalate = listOf(
            "Sudden or thunderclap onset, or a change from usual pattern.",
            "Raised blood pressure — consider pre-eclampsia from 20 weeks up to 6 weeks postpartum.",
            "Fever, focal neurological deficit or altered consciousness.",
            "Third trimester presentation.",
            "Abnormal labs: low or high platelets, raised liver enzymes or creatinine.",
            "Imaging: MRI without contrast is preferred.",
        ),
        citations = listOf(cite("acog-cpg3", "Doses as summarised from the guideline; check against full text")),
    )

    private val pb203 = listOf(cite("acog-pb203", "Table 2 — oral agents"))
    private val acute = listOf(cite("acog-pb203", "Boxes 4–6 — order sets"), cite("acog-pb222", "Table 3"))

    val hypertension = Condition(
        id = "hypertension",
        title = "High blood pressure & pre-eclampsia",
        summary = "Oral agents, severe-range order sets, magnesium, aspirin",
        keywords = listOf("hypertension", "blood pressure", "BP", "preeclampsia", "pre-eclampsia", "PIH", "eclampsia", "toxaemia", "labetalol", "nifedipine", "methyldopa", "hydralazine", "magnesium sulphate", "MgSO4", "aspirin"),
        info = listOf(
            InfoBlock(
                "Thresholds",
                listOf(
                    "Chronic hypertension: start or titrate treatment at 140/90 (CHAP).",
                    "Severe range: systolic ≥160 or diastolic ≥110 persisting 15 minutes or more — treat as soon as possible, within 30–60 minutes.",
                ),
                listOf(cite("acog-pa-chap"), cite("acog-pb222")),
            ),
            InfoBlock(
                "India (MoHFW Dakshata)",
                listOf(
                    "Give antihypertensives if diastolic BP >100 mm Hg; methyldopa or labetalol tablets; keep diastolic 90–100.",
                    "Severe pre-eclampsia: nifedipine tablets or injectable labetalol for initial BP control.",
                ),
                listOf(cite("mohfw-dakshata-2015")),
            ),
        ),
        tiers = listOf(
            Tier(
                "Chronic hypertension · oral", "Labetalol or nifedipine are preferred over all other agents.",
                listOf(
                    Drug(
                        "Labetalol", "200–2,400 mg/day orally in 2–3 divided doses. Commonly started at 100–200 mg twice daily.",
                        cautions = listOf("Avoid in asthma, heart block or bradycardia."),
                        citations = pb203,
                    ),
                    Drug(
                        "Nifedipine (extended release)", "30–120 mg/day orally. Commonly started at 30–60 mg once daily.",
                        cautions = listOf("Do not use the sublingual form."),
                        citations = pb203,
                    ),
                    Drug("Methyldopa", "500–3,000 mg/day orally in 2–4 divided doses. Commonly started at 250 mg two or three times daily.", citations = pb203),
                    Drug("Hydrochlorothiazide", "12.5–50 mg daily. Second- or third-line agent.", citations = pb203),
                ),
            ),
            Tier(
                "Acute severe hypertension", "Recheck BP at each step; escalate to emergency consultation if targets not reached.",
                listOf(
                    Drug(
                        "Labetalol IV (order set)",
                        "20 mg IV over 2 min → recheck in 10 min → 40 mg IV → 10 min → 80 mg IV → 10 min → hydralazine 10 mg IV → 20 min → emergency consultation.",
                        maxDose = "300 mg cumulative; or infusion 1–2 mg/min",
                        cautions = listOf("After target BP: monitor every 10 min for 1 h, every 15 min for 1 h, every 30 min for 1 h, then hourly for 4 h."),
                        citations = acute,
                    ),
                    Drug(
                        "Hydralazine IV (order set)",
                        "5 or 10 mg IV over 2 min → recheck in 20 min → 10 mg IV → 20 min → labetalol 20 mg IV → 10 min → labetalol 40 mg IV and emergency consultation.",
                        maxDose = "20 mg cumulative",
                        citations = acute,
                    ),
                    Drug(
                        "Nifedipine immediate release, oral (order set)",
                        "10 mg orally → recheck in 20 min → 20 mg → 20 min → 20 mg → 20 min → labetalol 20 mg IV and emergency consultation.",
                        maxDose = "180 mg/day",
                        cautions = listOf("Swallow capsules; do not puncture or give sublingually."),
                        citations = acute,
                    ),
                    Drug(
                        "Labetalol oral (no IV access)",
                        "200 mg orally if immediate-release nifedipine is not available; may repeat in 30 minutes.",
                        citations = listOf(cite("acog-pb203", "Boxes 4–6")),
                    ),
                ),
            ),
            Tier(
                "Seizure prophylaxis & eclampsia", "For severe features or eclampsia.",
                listOf(
                    Drug(
                        "Magnesium sulfate — IV (ACOG)",
                        "4–6 g IV loading dose over 20–30 min, then 1–2 g/hour. Continue for 24 hours after delivery.",
                        cautions = listOf(
                            "Renal impairment (creatinine 1.0–1.5 mg/dL or oliguria): loading dose, then 1 g/hour only.",
                            "Reflexes are lost at 9 mg/dL; respiratory depression at 12 mg/dL.",
                        ),
                        citations = listOf(cite("acog-pb222")),
                    ),
                    Drug(
                        "Magnesium sulfate — IM (ACOG)",
                        "10 g IM loading dose (5 g in each buttock), then 5 g every 4 hours.",
                        citations = listOf(cite("acog-pb222")),
                    ),
                    Drug(
                        "Magnesium sulfate — Pritchard (MoHFW)",
                        "Loading 14 g: 4 g IV (20 mL of 20% over 5 min) + 10 g IM (5 g of 50% in each buttock with 1 mL 2% lignocaine). Maintenance: 5 g IM in alternate buttocks every 4 hours until 24 hours after the last convulsion or delivery, whichever is later. Recurrent convulsion: 2 g (20%) IV over 5 min.",
                        cautions = listOf(
                            "Before each dose: knee jerk present, respiratory rate >16/min, urine output >100 mL in the previous 4 hours.",
                            "Antidote: calcium gluconate 10%, 10 mL IV.",
                        ),
                        citations = listOf(cite("mohfw-mo-handbook-2009", "Magnesium sulphate regimen")),
                    ),
                ),
            ),
            Tier(
                "Prevention of pre-eclampsia", null,
                listOf(
                    Drug(
                        "Aspirin, low dose",
                        "81 mg daily, started between 12 and 28 weeks (optimally before 16 weeks) and continued until delivery.",
                        cautions = listOf(
                            "Recommend if any high-risk factor: previous pre-eclampsia, multifetal gestation, chronic hypertension, type 1 or 2 diabetes, kidney disease, autoimmune disease (SLE, antiphospholipid syndrome).",
                            "Recommend if more than one moderate-risk factor: nulliparity, BMI >30, family history of pre-eclampsia, age ≥35, low income, IVF, previous low birth weight/SGA or adverse outcome, interval >10 years.",
                            "No MoHFW recommendation on aspirin was found.",
                        ),
                        citations = listOf(cite("acog-co743"), cite("acog-pa-aspirin", "Risk factors")),
                    ),
                ),
            ),
        ),
        avoid = listOf(
            Caution("ACE inhibitors, ARBs, renin inhibitors, mineralocorticoid antagonists", "Generally not recommended in pregnancy.", pb203),
            Caution("Atenolol", "Not recommended.", pb203),
        ),
        citations = listOf(cite("acog-pb203"), cite("acog-pb222"), cite("acog-pa-chap"), cite("mohfw-mo-handbook-2009")),
    )

    val pruritus = Condition(
        id = "pruritus",
        title = "Itching & cholestasis (ICP)",
        summary = "Bile-acid thresholds, symptom relief, UDCA and timing of birth",
        keywords = listOf("pruritus", "itching", "itch", "cholestasis", "ICP", "obstetric cholestasis", "bile acids", "UDCA", "ursodeoxycholic", "ursodiol"),
        info = listOf(
            InfoBlock(
                "Diagnosis",
                listOf(
                    "RCOG: itching on normal-appearing skin with peak random total bile acids ≥19 µmol/L. Mild 19–39, moderate 40–99, severe ≥100 µmol/L.",
                    "SMFM: total bile acids >10 µmol/L are often used to diagnose ICP; random levels are acceptable.",
                ),
                listOf(cite("rcog-gtg43"), cite("smfm-cs53")),
            ),
            InfoBlock(
                "Timing of birth",
                listOf(
                    "RCOG: 19–39 µmol/L — consider planned birth by 40 weeks or ongoing care; 40–99 — consider 38–39 weeks; ≥100 — consider 35–36 weeks.",
                    "SMFM: ≥100 µmol/L — offer delivery at 36 0/7 weeks; <100 — 36 0/7 to 39 0/7 weeks (later end if <40).",
                    "Give antenatal corticosteroids if delivering before 37 0/7 weeks (SMFM).",
                ),
                listOf(cite("rcog-gtg43", "Timing of birth"), cite("smfm-cs53")),
            ),
        ),
        tiers = listOf(
            Tier(
                "Symptom relief", "RCOG: treatments for itching are of limited benefit and none improve pregnancy outcome.",
                listOf(
                    Drug(
                        "Topical emollient", "Aqueous cream, with or without menthol.",
                        citations = listOf(cite("rcog-gtg43", "Symptom relief")),
                    ),
                    Drug(
                        "Chlorphenamine", "Particularly at night; effect uncertain and likely due to sedation.",
                        citations = listOf(cite("rcog-gtg43", "Symptom relief")),
                    ),
                ),
            ),
            Tier(
                "Ursodeoxycholic acid (UDCA)", "SMFM: first-line for maternal symptoms. RCOG: do not offer routinely to improve perinatal outcomes.",
                listOf(
                    Drug(
                        "Ursodeoxycholic acid",
                        "Start 10–15 mg/kg/day in 2–3 divided doses (typically 300 mg twice or three times daily, or 500 mg twice daily). Titrate if itching persists.",
                        maxDose = "21 mg/kg/day",
                        weightDose = WeightDose(10.0, 15.0, per = "per day, in 2–3 divided doses", ceilingPerKg = 21.0),
                        cautions = listOf("RCOG: itch reduction is small (~5 mm on a 100 mm scale)."),
                        citations = listOf(cite("smfm-cs53", "Treatment"), cite("rcog-gtg43", "UDCA")),
                    ),
                ),
            ),
            Tier(
                "Vitamin K", "Only if fat malabsorption (steatorrhoea) or abnormal prothrombin time.",
                listOf(
                    Drug(
                        "Menadiol sodium phosphate (water-soluble vitamin K)", "10 mg daily.",
                        citations = listOf(cite("rcog-gtg43", "Vitamin K")),
                    ),
                ),
            ),
        ),
        citations = listOf(cite("rcog-gtg43"), cite("smfm-cs53")),
    )

    private val ng109 = listOf(cite("nice-ng109", "Table 2 — pregnant women"))

    val uti = Condition(
        id = "uti",
        title = "Urinary tract infection",
        summary = "Cystitis, asymptomatic bacteriuria and pyelonephritis",
        keywords = listOf("UTI", "urinary tract infection", "cystitis", "dysuria", "burning micturition", "bacteriuria", "ASB", "pyelonephritis", "nitrofurantoin", "cefalexin", "cephalexin"),
        firstSteps = listOf("Send a midstream urine for culture before starting antibiotics; switch to a narrow-spectrum agent if culture shows resistance."),
        info = listOf(
            InfoBlock(
                "Asymptomatic bacteriuria",
                listOf(
                    "Treat in pregnancy — risk factor for pyelonephritis and premature delivery.",
                    "Choose nitrofurantoin, amoxicillin or cefalexin based on culture and susceptibility.",
                    "IDSA suggests 4–7 days of treatment rather than a shorter course.",
                ),
                listOf(cite("nice-ng109", "1.2.1–1.2.2"), cite("idsa-asb-2019")),
            ),
        ),
        tiers = listOf(
            Tier(
                "Lower UTI · first choice", null,
                listOf(
                    Drug(
                        "Nitrofurantoin", "100 mg modified-release twice a day (or 50 mg four times a day) for 7 days, if eGFR ≥45 mL/min.",
                        cautions = listOf("Avoid at term — may cause neonatal haemolysis."),
                        citations = ng109,
                    ),
                ),
            ),
            Tier(
                "Lower UTI · second choice", "No improvement after 48 hours, or first choice unsuitable.",
                listOf(
                    Drug("Amoxicillin", "500 mg three times a day for 7 days — only if culture-susceptible.", citations = ng109),
                    Drug("Cefalexin", "500 mg twice a day for 7 days.", citations = ng109),
                ),
            ),
            Tier(
                "Pyelonephritis", "Consider referral or specialist advice in pregnancy.",
                listOf(
                    Drug(
                        "Cefalexin (oral)", "500 mg two or three times a day (up to 1–1.5 g three or four times a day for severe infection) for 7–10 days.",
                        citations = listOf(cite("nice-ng111", "Table 2 — pregnant")),
                    ),
                    Drug(
                        "Cefuroxime (IV)", "750 mg–1.5 g three or four times a day if vomiting, unable to take oral antibiotics, or severely unwell. Review IV therapy by 48 hours.",
                        citations = listOf(cite("nice-ng111", "Table 2 — pregnant")),
                    ),
                ),
            ),
        ),
        escalate = listOf(
            "Pyelonephritis in pregnancy: consider referral or specialist advice.",
            "Signs of sepsis or serious illness: refer to hospital.",
        ),
        citations = listOf(cite("nice-ng109"), cite("nice-ng111"), cite("idsa-asb-2019")),
    )

    val vaginal = Condition(
        id = "vaginal",
        title = "Vaginal infections",
        summary = "Thrush, bacterial vaginosis and trichomoniasis",
        keywords = listOf("vaginal discharge", "candidiasis", "thrush", "yeast", "VVC", "bacterial vaginosis", "BV", "trichomonas", "trichomoniasis", "itching vulva", "clotrimazole", "metronidazole"),
        tiers = listOf(
            Tier(
                "Candidiasis (thrush)", "Only topical azoles, applied for 7 days.",
                listOf(
                    Drug("Clotrimazole 1% cream", "5 g intravaginally daily for 7–14 days.", citations = listOf(cite("cdc-sti-vvc"))),
                    Drug("Miconazole 2% cream", "5 g intravaginally daily for 7 days.", citations = listOf(cite("cdc-sti-vvc"))),
                    Drug("Miconazole 100 mg vaginal suppository", "One suppository daily for 7 days.", citations = listOf(cite("cdc-sti-vvc"))),
                    Drug("Terconazole 0.4% cream", "5 g intravaginally daily for 7 days.", citations = listOf(cite("cdc-sti-vvc"))),
                ),
            ),
            Tier(
                "Bacterial vaginosis", "Treat all symptomatic pregnant women.",
                listOf(
                    Drug("Metronidazole (oral)", "500 mg orally twice a day for 7 days.", citations = listOf(cite("cdc-sti-bv"))),
                    Drug("Metronidazole gel 0.75%", "One full applicator (5 g) intravaginally once daily for 5 days.", citations = listOf(cite("cdc-sti-bv"))),
                    Drug("Clindamycin cream 2%", "One full applicator (5 g) intravaginally at bedtime for 7 days.", citations = listOf(cite("cdc-sti-bv"))),
                    Drug("Clindamycin (oral)", "300 mg orally twice a day for 7 days.", citations = listOf(cite("cdc-sti-bv"))),
                    Drug("Clindamycin ovules", "100 mg intravaginally at bedtime for 3 days.", citations = listOf(cite("cdc-sti-bv"))),
                ),
            ),
            Tier(
                "Trichomoniasis", "Test and treat symptomatic women at any stage of pregnancy.",
                listOf(
                    Drug("Metronidazole (oral)", "500 mg orally twice a day for 7 days.", citations = listOf(cite("cdc-sti-trich"))),
                ),
            ),
        ),
        avoid = listOf(
            Caution("Oral fluconazole", "A single 150 mg dose might be associated with spontaneous abortion and congenital anomalies — should not be used.", listOf(cite("cdc-sti-vvc"))),
            Caution("Tinidazole and secnidazole", "Avoid in pregnancy.", listOf(cite("cdc-sti-bv"), cite("cdc-sti-trich"))),
        ),
        citations = listOf(cite("cdc-sti-vvc"), cite("cdc-sti-bv"), cite("cdc-sti-trich")),
    )

    private val amb = listOf(cite("mohfw-amb-2026", "Treatment protocol — pregnancy"))

    val anaemia = Condition(
        id = "anaemia",
        title = "Anaemia",
        summary = "Anemia Mukt Bharat cut-offs, oral IFA and IV iron",
        keywords = listOf("anemia", "anaemia", "haemoglobin", "hemoglobin", "Hb", "iron", "IFA", "iron sucrose", "FCM", "ferric carboxymaltose", "iron deficiency"),
        info = listOf(
            InfoBlock(
                "Hb cut-offs in pregnancy (g/dL)",
                listOf(
                    "Non-anaemic: ≥11.0 (1st & 3rd trimester), ≥10.5 (2nd).",
                    "Mild: 10.0–10.9 (1st & 3rd), 9.5–10.4 (2nd).",
                    "Moderate: 7.0–9.9 (1st & 3rd), 7.0–9.4 (2nd).",
                    "Severe: <7.0.",
                ),
                listOf(cite("mohfw-amb-2026", "Hb cut-offs (WHO 2024)")),
            ),
        ),
        tiers = listOf(
            Tier(
                "Prevention", null,
                listOf(
                    Drug(
                        "IFA (Red) tablet", "One tablet daily (60 mg elemental iron + 500 mcg folic acid) for 180 days from the second trimester; continue for 180 days after delivery.",
                        citations = listOf(cite("mohfw-amb-2026", "IFA prophylaxis")),
                    ),
                ),
            ),
            Tier(
                "Mild to moderate anaemia", null,
                listOf(
                    Drug(
                        "IFA (Red) tablet — treatment", "Two tablets daily for 1 month, then recheck Hb. Improved but not at target: continue another month. Target reached: return to the prophylactic dose. No improvement: refer to FRU/DH.",
                        citations = amb,
                    ),
                ),
            ),
            Tier(
                "Moderate anaemia — IV iron", "As advised by the treating physician based on gestation and clinical evaluation; after 13 weeks.",
                listOf(
                    Drug(
                        "Iron sucrose IV", "5 doses.",
                        cautions = listOf("Per-dose amount: see the MoHFW operational guideline on IV iron for pregnant and lactating women."),
                        citations = amb,
                    ),
                    Drug(
                        "Ferric carboxymaltose (FCM) IV", "2 doses.",
                        cautions = listOf("Per-dose amount: see the MoHFW operational guideline on IV iron for pregnant and lactating women."),
                        citations = amb,
                    ),
                ),
            ),
        ),
        escalate = listOf(
            "Moderate anaemia after 28 weeks: refer to FRU/DH.",
            "Severe anaemia (Hb <7): refer to FRU/DH. Hb 5–6.9 g/dL at 13–34 weeks: IV iron. Hb <5 g/dL: blood transfusion and further management.",
        ),
        citations = listOf(cite("mohfw-amb-2026"), cite("acog-pb233")),
    )

    val heartburn = Condition(
        id = "heartburn",
        title = "Heartburn & indigestion",
        summary = "Lifestyle first, then a trial of antacid or alginate",
        keywords = listOf("heartburn", "acidity", "reflux", "GERD", "GORD", "dyspepsia", "indigestion", "antacid", "alginate"),
        firstSteps = listOf("Give information on lifestyle and dietary changes (NICE refers to the common elements of care in its adult GORD and dyspepsia guideline)."),
        tiers = listOf(
            Tier(
                "If symptoms persist", null,
                listOf(
                    Drug(
                        "Antacid or alginate", "Consider a trial. NICE gives no specific dose — use the product's labelled dose.",
                        cautions = listOf("NICE made no recommendation on PPIs or H2-receptor antagonists in pregnancy because of very low-quality or absent evidence."),
                        citations = listOf(cite("nice-ng201", "1.4.8–1.4.9")),
                    ),
                ),
            ),
        ),
        citations = listOf(cite("nice-ng201")),
    )

    val all: List<Condition> = listOf(nausea, feverPain, headache, heartburn, hypertension, pruritus, uti, vaginal, anaemia)
}
