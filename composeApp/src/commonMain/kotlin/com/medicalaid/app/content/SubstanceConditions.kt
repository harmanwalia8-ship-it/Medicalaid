package com.medicalaid.app.content

/*
 * General-adult page: cocaine (stimulant) use disorder — treatment of dependence, withdrawal (“crash”),
 * “cocaine bugs” (formication, delusional infestation, stimulant psychosis) and key toxicity points.
 * Every statement below was checked against the cited document (reviewed November 2026).
 *
 * Notes for maintainers:
 *  - ASAM/AAAP recommendation numbers are the same in the AAAP release (November 2023) and the
 *    J Addict Med 2024 publication cited here; locators use recommendation numbers and appendix letters.
 *  - Doses labelled “trial doses” come from ASAM/AAAP Appendix K, which says it is not a dosing guide.
 *  - No ICMR, MoHFW or Indian Psychiatric Society guideline on treating stimulant use disorder was found;
 *    the IPS 2023 guideline covers intoxication in the emergency department only.
 *  - India statements start with “India” so the guideline filter groups them.
 *  - The toxicity block points to the cocaine toxicity protocol (protocols/data/Cocaine.kt), released in 0.4.0.
 */

internal val substanceConditionSources: List<Source> = listOf(
    Source(
        "gc-sud-asam-aaap-2024", Authority.Paper, "ASAM/AAAP clinical practice guideline — J Addict Med 2024;18(1S Suppl 1):1–56",
        "The ASAM/AAAP Clinical Practice Guideline on the Management of Stimulant Use Disorder", "2024",
        "https://doi.org/10.1097/ADM.0000000000001299",
        status = "Released by ASAM and AAAP in November 2023; recommendation numbers are the same in both versions",
    ),
    Source(
        "gc-sud-samhsa-tip33", Authority.Paper, "SAMHSA Treatment Improvement Protocol (TIP) 33 — Publication No. PEP21-02-01-004",
        "Treatment for Stimulant Use Disorders", "2021",
        "https://www.govinfo.gov/content/pkg/GOVPUB-HE20_400-PURL-gpo171771/pdf/GOVPUB-HE20_400-PURL-gpo171771.pdf",
        status = "Updated 2021",
    ),
    Source(
        "gc-sud-nice-cg51", Authority.NICE, "Clinical guideline CG51",
        "Drug misuse in over 16s: psychosocial interventions", "2007",
        "https://www.nice.org.uk/guidance/cg51/chapter/Recommendations",
        status = "Published 25 July 2007; minor update March 2020",
    ),
    Source(
        "gc-sud-nice-cg120", Authority.NICE, "Clinical guideline CG120",
        "Coexisting severe mental illness (psychosis) and substance misuse: assessment and management in healthcare settings", "2011",
        "https://www.nice.org.uk/guidance/cg120/chapter/Recommendations",
        status = "Published 23 March 2011; presentational update May 2024",
    ),
    Source(
        "gc-sud-who-unodc-2020", Authority.WHO, "WHO/UNODC — E/CN.7/2020/CRP.6",
        "International Standards for the Treatment of Drug Use Disorders: revised edition incorporating results of field-testing", "2020",
        "https://www.unodc.org/documents/drug-prevention-and-treatment/UNODC-WHO_International_Treatment_Standards_March_2020.pdf",
        status = "March 2020",
    ),
    Source(
        "gc-sud-who-mhgap-2016", Authority.WHO, "mhGAP Intervention Guide, version 2.0",
        "mhGAP Intervention Guide for mental, neurological and substance use disorders in non-specialized health settings", "2016",
        "https://www.who.int/publications/i/item/9789241549790",
    ),
    Source(
        "gc-sud-ips-ed-2023", Authority.Paper, "India — Indian Psychiatric Society clinical practice guideline, Indian J Psychiatry 2023;65(2):196–211",
        "Clinical Practice Guidelines for Assessment and Management of Patients with Substance Intoxication Presenting to the Emergency Department", "2023",
        "https://doi.org/10.4103/indianjpsychiatry.indianjpsychiatry_490_22",
        status = "Authors from NDDTC, AIIMS New Delhi",
    ),
    Source(
        "gc-sud-freudenmann-2009", Authority.Paper, "Review — Clin Microbiol Rev 2009;22(4):690–732",
        "Delusional infestation", "2009",
        "https://doi.org/10.1128/CMR.00018-09",
    ),
    Source(
        "gc-sud-nddtc-survey-2019", Authority.Paper, "India — Ministry of Social Justice & Empowerment, PIB release, 18 February 2019",
        "NDDTC, AIIMS submits report “Magnitude of Substance Use in India” to the Ministry of Social Justice & Empowerment", "2019",
        "https://pib.gov.in/PressReleasePage.aspx?PRID=1565001",
        status = "National survey conducted by NDDTC, AIIMS New Delhi in 2018",
    ),
    Source(
        "gc-sud-mosje-napddr-2024", Authority.Paper, "India — Ministry of Social Justice & Empowerment, PIB release, 29 February 2024",
        "Progress under National Action Plan for Drug Demand Reduction and Nasha Mukt Bharat Abhiyaan", "2024",
        "https://pib.gov.in/PressReleasePage.aspx?PRID=2010097",
    ),
)

private const val SUD_ASAM = "gc-sud-asam-aaap-2024"
private const val SUD_TIP33 = "gc-sud-samhsa-tip33"
private const val SUD_CG51 = "gc-sud-nice-cg51"
private const val SUD_CG120 = "gc-sud-nice-cg120"
private const val SUD_WHO_STD = "gc-sud-who-unodc-2020"
private const val SUD_MHGAP = "gc-sud-who-mhgap-2016"
private const val SUD_IPS = "gc-sud-ips-ed-2023"
private const val SUD_DI = "gc-sud-freudenmann-2009"
private const val SUD_SURVEY = "gc-sud-nddtc-survey-2019"
private const val SUD_MOSJE = "gc-sud-mosje-napddr-2024"

private val sudPsychostimulantOnly =
    "ASAM/AAAP (Recs 15–16): psychostimulant options are for addiction-medicine or addiction-psychiatry specialists, or physicians with equivalent training and close monitoring."

private val sudCocaineUseDisorder = Condition(
    id = "cocaine-use-disorder",
    title = "Cocaine (stimulant) use disorder",
    summary = "Contingency management first, off-label medicines, the “crash” and “cocaine bugs”",
    keywords = listOf(
        "cocaine", "coke", "crack", "stimulant use disorder", "addiction", "dependence", "de-addiction", "withdrawal",
        "crash", "cocaine bugs", "formication", "contingency management", "cocaine use disorder", "stimulant",
        "delusional parasitosis", "delusional infestation", "tactile hallucinations", "stimulant psychosis",
        "cocaine psychosis", "levamisole", "CBT", "community reinforcement approach", "Matrix Model", "bupropion",
        "topiramate", "modafinil", "Nasha Mukt Bharat", "NDDTC", "14446",
    ),
    population = Population.General,
    info = listOf(
        InfoBlock(
            "Diagnosis and assessment",
            listOf(
                "ASAM/AAAP: first identify urgent problems — intoxication, overdose, psychosis, suicidality — and treat or refer; do not delay this for a full assessment.",
                "DSM-5 (as summarised in SAMHSA TIP 33): stimulant use disorder needs at least 2 of 11 criteria within a 12-month period.",
                "Criteria (TIP 33): using more than intended; failed efforts to cut down; much time spent; craving; neglected obligations; continued use despite social problems; activities given up;",
                "…use in physically hazardous situations; use despite a physical or psychological problem; tolerance; withdrawal or use to avoid it.",
                "DSM-5-TR severity (ASAM/AAAP): mild 2–3 criteria, moderate 4–5, severe 6 or more.",
                "ASAM/AAAP: stimulant-focused history and examination, mental status (psychosis, ADHD, mood, cognition, risk to self or others) and a full biopsychosocial assessment.",
                "NICE CG51 1.2.1.2: consider asking about drug use in a young person with acute chest pain, or with acute psychosis, mood or sleep disorders.",
                "ASAM/AAAP: include stimulant misuse when screening adults for risky substance use; consider a brief motivational-interviewing intervention for risky use.",
                "NICE CG51 1.2.2.3: use urine or oral-fluid testing within a full assessment, never as the sole basis for diagnosis.",
                "ASAM/AAAP: heavy or long-term use — high suspicion for cardiac and renal disease; low threshold for ECG and for CK (rhabdomyolysis).",
                "ASAM/AAAP: assess suicide risk with a validated tool (e.g., C-SSRS) and screen for psychological trauma.",
            ),
            listOf(
                cite(SUD_ASAM, "Recs 1–4, 74, 79; Assessment; Safety assessment"), cite(SUD_TIP33, "Exhibit 1.1, p. 3"),
                cite(SUD_CG51, "1.2.1.2, 1.2.2.3"),
            ),
        ),
        InfoBlock(
            "Tests, infections and harm reduction",
            listOf(
                "ASAM/AAAP baseline tests: CBC; metabolic panel with renal and liver tests; HIV and HCV for all; HBV if at risk; syphilis, gonorrhoea, chlamydia; pregnancy test.",
                "ASAM/AAAP: consider TB and hepatitis A testing if at risk; offer hepatitis A and B vaccination to those not immune.",
                "ASAM/AAAP: levamisole, a common cocaine adulterant, can cause neutropenia and small-vessel vasculitis — check the neutrophil count.",
                "NICE CG51 1.3.1.1: advise on reducing sexual and injecting risk behaviours; consider offering blood-borne virus testing.",
                "NICE CG51 1.4.2.1: consider small incentives to encourage hepatitis B/C and HIV testing, hepatitis B immunisation and TB testing.",
                "ASAM/AAAP: offer HIV PrEP with risky sex or injecting; with risky sexual behaviour, STI testing at least every 3–6 months, more often if risk is higher.",
                "ASAM/AAAP: provide naloxone (or say where to get it) when stimulants come from non-medical sources; give safer-injection education and supplies.",
                "ASAM/AAAP: encourage oral hygiene, dental care and nutritious food — stimulant use carries high risk of caries, abscesses and malnutrition.",
            ),
            listOf(cite(SUD_ASAM, "Appendix I; Comprehensive Assessment; Recs 50, 85, 88–92"), cite(SUD_CG51, "1.3.1.1, 1.4.2.1")),
        ),
        InfoBlock(
            "Withdrawal (“crash”) — features",
            listOf(
                "ASAM/AAAP: after abrupt stopping, many patients have 12–24 hours of somnolence and irritability.",
                "SAMHSA TIP 33: cocaine withdrawal may start 1–2 days after the last dose; the crash lasts several days, withdrawal 1–2 weeks, with waxing and waning craving.",
                "TIP 33: after a 2–3-day binge, people are dysphoric and exhausted and sleep excessively for 24–48 hours.",
                "TIP 33 features: fatigue, hunger, insomnia then hypersomnia, anxiety, irritability, anhedonia, intense craving, vivid drug-related dreams.",
                "TIP 33: dysphoria may deepen into depression with suicidal ideation; harm to self is the greatest risk in withdrawal.",
                "WHO/UNODC: less well defined than depressant withdrawal; depression is prominent, with malaise, inertia and mood instability.",
                "WHO mhGAP: cocaine and other stimulant withdrawal — fatigue, increased appetite, depressed or irritable mood; watch for suicidal thoughts.",
                "ASAM/AAAP: post-acute depression, anxiety, insomnia and paranoia can last weeks to months — assess and treat to reduce relapse.",
            ),
            listOf(
                cite(SUD_ASAM, "Stimulant Withdrawal; Post-acute symptoms"), cite(SUD_TIP33, "Ch. 3, pp. 47–49; Exhibit 3.5"),
                cite(SUD_WHO_STD, "Para 226"), cite(SUD_MHGAP, "SUB 1, Box 1"),
            ),
        ),
        InfoBlock(
            "Withdrawal — management",
            listOf(
                "WHO (mhGAP, quoted in WHO/UNODC Standards): manage cocaine withdrawal in a supportive environment; no specific medication is recommended.",
                "WHO: symptomatic medicines may relieve agitation or sleep disturbance; if depression or psychosis occurs, monitor closely and seek specialist advice.",
                "WHO/UNODC: prescribe psychoactive medicines cautiously and for short periods only, watching for tolerance and misuse.",
                "ASAM/AAAP: standard care treats presenting symptoms and minimises risk in a calm setting; a 2009 Cochrane review of amphetamine withdrawal (4 RCTs, 125 participants) found no effective medicine for general stimulant withdrawal.",
                "ASAM/AAAP: post-acute insomnia — sleep hygiene may suffice; severe or persistent insomnia may need medicine; use sedative-hypnotics cautiously, reassessing regularly.",
                "SAMHSA TIP 33: cocaine-induced depression usually dissipates within hours to days.",
                "ASAM/AAAP: watch for breakthrough psychosis and suicidality, which may rise as intoxication wanes and acute withdrawal begins.",
            ),
            listOf(
                cite(SUD_WHO_STD, "Para 226 and mhGAP 2012 box; para 244"), cite(SUD_ASAM, "Stimulant Withdrawal; Post-Acute Symptoms of Withdrawal; Rec 62"),
                cite(SUD_TIP33, "Ch. 3, p. 49"),
            ),
        ),
        InfoBlock(
            "“Cocaine bugs” — formication, delusional infestation, psychosis",
            listOf(
                "Freudenmann & Lepping (review): tactile symptoms in regular cocaine users were described historically and are now called “cocaine bugs”.",
                "SAMHSA TIP 33: a tactile hallucination is feeling sensations that are not real, e.g., bugs crawling under the skin.",
                "Freudenmann & Lepping: cocaine and amphetamines can cause secondary (substance-induced) delusional infestation, classically a young man with sudden, transient symptoms.",
                "Freudenmann & Lepping: in intoxication, onset is acute and symptoms last hours, days or weeks.",
                "TIP 33: stimulant psychosis usually brings auditory and tactile hallucinations, paranoid thoughts and ideas of reference.",
                "TIP 33: clinicians report cocaine psychosis usually clears in 1–3 days, versus up to 2–3 weeks after methamphetamine.",
                "Freudenmann & Lepping: itching, scratching and skin damage maintain the belief — lesions are reinterpreted as proof of infestation.",
                "ASAM/AAAP: skin complications of stimulant use include picking, neurodermatitis, cellulitis, abscesses and other soft-tissue infections.",
                "TIP 33 (DSM-5): consider a primary psychotic disorder if psychosis preceded use, exceeds expected effects, lasts ≥1 month after intoxication or withdrawal has ended, or earlier episodes were non-substance-induced.",
            ),
            listOf(
                cite(SUD_DI, "Inset 2; Inset 10; Course and duration of illness; Cleaning, self-therapy, and injuries"),
                cite(SUD_TIP33, "Exhibit 3.1, p. 35; Ch. 3, pp. 62–65"), cite(SUD_ASAM, "Appendix M"),
            ),
        ),
        InfoBlock(
            "“Cocaine bugs” — assessment and care",
            listOf(
                "Freudenmann & Lepping: first exclude real infestation — skin examination with magnification, mineral-oil scrapings, biopsy if indicated; examine any specimens brought.",
                "Freudenmann & Lepping: urine drug testing is obligatory, in particular for cocaine, amphetamines and cannabinoids.",
                "Freudenmann & Lepping work-up: CBC, ESR, CRP, creatinine, electrolytes, liver tests, TSH, fasting glucose, urinalysis, pregnancy test.",
                "Freudenmann & Lepping: drug-induced infestation — mainstay is abstinence plus an antipsychotic for a limited period; no generally accepted guideline exists.",
                "Freudenmann & Lepping: antipsychotics are for recurring or persistent psychotic symptoms; simple formication is not an indication.",
                "Freudenmann & Lepping: with atypical antipsychotics, secondary infestation reached maximum response at a median of 3 weeks (range 0.5–8); treat for at least 3 weeks before switching for poor response.",
                "Freudenmann & Lepping: treat skin lesions topically (light dressings if needed); consider non-sedating antihistamines (e.g., loratadine, levocetirizine) for itch.",
                "Freudenmann & Lepping: acknowledge the suffering, do not argue about the belief, and ask about suicidal ideation and risk to others.",
                "ASAM/AAAP: treat or refer skin and soft-tissue infections; serious skin infections are listed among acute complications.",
            ),
            listOf(
                cite(SUD_DI, "Diagnostic work-up; Multimodal therapy; Etiology-specific therapy; Insets 17–18"),
                cite(SUD_ASAM, "Appendices L–M"),
            ),
        ),
        InfoBlock(
            "Acute cocaine toxicity — key points (full pathway: Protocols › Cocaine toxicity)",
            listOf(
                "ASAM/AAAP: benzodiazepines can be considered first line for stimulant-induced agitation (Rec 57a, high certainty, conditional) and the hyperadrenergic state (Rec 64: treat with GABAergic agents — benzodiazepines, phenobarbital, propofol; low certainty, strong).",
                "India (IPS 2023): quiet room; lorazepam 2 mg orally, IM or IV, repeated as necessary; IV fluids for dehydration.",
                "WHO mhGAP: diazepam 5–10 mg orally, IV or rectally, titrated until calm and lightly sedated.",
                "ASAM/AAAP chest pain: treat intoxication with GABAergic drugs, evaluate for ACS; calcium-channel blockers or vasodilators preferred for ischaemia.",
                "India (IPS 2023): aspirin and nitroglycerine are given for cocaine-related chest pain.",
                "SAMHSA TIP 33: give aspirin unless contraindicated; nitroglycerin is indicated for cocaine-induced ischaemia and chest pain.",
                "ASAM/AAAP: QRS widening — sodium bicarbonate. Seizures — benzodiazepines; phenobarbital or propofol if refractory.",
                "ASAM/AAAP hypertensive emergency: short-acting nitroprusside, phentolamine or dihydropyridine CCB; avoid long-acting agents; nitroglycerin if ischaemia.",
                "ASAM/AAAP hyperthermia (manage by best practice): severe (generally >40.5 °C) — immersion in a cooling water bath, which may be combined with sedatives or neuromuscular blockers; less severe — evaporative cooling (mist, fan).",
                "SAMHSA TIP 33: in a stimulant overdose where opioid involvement (including fentanyl) is suspected, giving naloxone is critical.",
                "ASAM/AAAP tests as indicated: CBC, metabolic panel, liver tests, CK and lactate, troponin.",
                "ASAM/AAAP: intoxication can be managed in lower-acuity settings if cooperative, at most mildly hyperadrenergic, and vital signs and suicidality can be monitored.",
                "India (IPS 2023): cocaine or stimulant intoxication settles over hours to within a day.",
            ),
            listOf(
                cite(SUD_ASAM, "Recs 49, 54, 57, 64, 66–70, 72; Hyperthermia"), cite(SUD_IPS, "Cocaine and other stimulants"),
                cite(SUD_MHGAP, "SUB — emergency presentations, p. 111"), cite(SUD_TIP33, "Ch. 3, p. 47"),
            ),
        ),
        InfoBlock(
            "India context",
            listOf(
                "India (NDDTC, AIIMS survey for MoSJE, 2019): cocaine current use 0.10% — among the lowest-prevalence substance categories in India.",
                "India (same survey): treatment access is grossly inadequate — about 1 in 20 people dependent on illicit drugs has ever had inpatient treatment.",
                "India (MoSJE): toll-free de-addiction helpline 14446 provides primary counselling and immediate referral.",
                "India (MoSJE, NAPDDR): funds addiction treatment facilities in government hospitals, rehabilitation centres, drop-in centres and district de-addiction centres; all geo-tagged.",
                "India (MoSJE): Nasha Mukt Bharat Abhiyaan, launched 15 August 2020, now covers all districts — awareness, identification, counselling and treatment links.",
                "India (IPS 2023): after cocaine intoxication resolves, refer for treatment of cocaine or stimulant use disorder if present.",
            ),
            listOf(
                cite(SUD_SURVEY, "Findings (e); Access to treatment services"), cite(SUD_MOSJE, "NAPDDR activities; NMBA"),
                cite(SUD_IPS, "Cocaine and other stimulants"),
            ),
        ),
        InfoBlock(
            "Where the guidelines differ",
            listOf(
                "CBT: ASAM/AAAP — preferred alongside CM (strong). NICE CG51 — drug-focused CBT should not be offered routinely for stimulant misuse.",
                "Medicines: ASAM/AAAP — several off-label options, psychostimulants by specialists. WHO/UNODC — none consistently efficacious; mhGAP — no dexamphetamine in non-specialist settings.",
                "Mood symptoms: ASAM/AAAP — consider medication even if stimulant-induced. WHO mhGAP — assess comorbidity ideally after 2–3 weeks' abstinence.",
                "Beta-blockers in intoxication: ASAM/AAAP — CCBs or vasodilators preferred; if a beta-blocker is used, choose one with alpha-1 blockade (carvedilol, labetalol). " +
                    "SAMHSA TIP 33 — beta-blockers generally discouraged (unresolved); propranolol should not be used (may enhance vasospasm); labetalol has shown safety in cocaine-induced hypertension and tachycardia.",
                "Stimulant psychosis: ASAM/AAAP — treat psychotic symptoms with an antipsychotic (Rec 59). WHO mhGAP and SAMHSA TIP 33 — benzodiazepine first; add an antipsychotic if symptoms do not respond to it.",
            ),
            listOf(
                cite(SUD_ASAM, "Recs 6, 9–19, 24, 59, 68"), cite(SUD_CG51, "1.4.6.1"), cite(SUD_WHO_STD, "Para 243; mhGAP 2.0 box"),
                cite(SUD_MHGAP, "SUB 2, Protocol 2; SUB — emergency presentations, p. 111"), cite(SUD_TIP33, "Ch. 3, pp. 47, 65"),
            ),
        ),
    ),
    tiers = listOf(
        Tier(
            "Psychosocial (first line)",
            "ASAM/AAAP: contingency management (CM) is the current standard of care; combine it with CRA, CBT or the Matrix Model.",
            listOf(
                Drug(
                    "Contingency management (CM)",
                    "ASAM/AAAP: tangible rewards (vouchers, prizes) for verified goals such as drug-negative tests or attendance; usually needs drug-testing capacity and at least twice-weekly clinical contact.",
                    cautions = listOf(
                        "ASAM/AAAP (Rec 5, high certainty, strong): CM should be a primary component, with other psychosocial treatment.",
                        "ASAM/AAAP: incentive values that are too low are unlikely to work.",
                        "NICE CG51 1.4.1.4: vouchers starting around £2, rising with each continuous period of abstinence.",
                        "NICE CG51 1.4.1.4: test 3 times a week for 3 weeks, twice a week for 3 weeks, then weekly until stable; urine preferred, oral fluid acceptable.",
                        "NICE CG51 1.4.1.3: agree the target with the patient; give incentives promptly and consistently.",
                        "WHO/UNODC: adapt CM to the local culture and population, with patient input.",
                        "NICE CG120 1.4.22: do not exclude people with psychosis from CM programmes.",
                    ),
                    citations = listOf(
                        cite(SUD_ASAM, "Rec 5; Contingency Management"), cite(SUD_CG51, "1.4.1.2–1.4.1.4"),
                        cite(SUD_WHO_STD, "Paras 210–211"), cite(SUD_CG120, "1.4.22"),
                    ),
                ),
                Drug(
                    "Community reinforcement approach (CRA)",
                    "WHO/UNODC: build enjoyable drug-free activities (family, social, work) and skills — communication, problem solving, drug refusal, relapse prevention — involving family.",
                    cautions = listOf(
                        "ASAM/AAAP (Rec 6a, low certainty, conditional): preferred alongside CM; the evidence comes from cocaine use disorder.",
                        "ASAM/AAAP: CM plus CRA was the most efficacious combination in a meta-analysis, especially for cocaine.",
                        "ASAM/AAAP: costly and labour-intensive; few trained staff; not widely implemented outside research settings.",
                    ),
                    citations = listOf(cite(SUD_ASAM, "Rec 6a; Community Reinforcement Approach"), cite(SUD_WHO_STD, "Para 212")),
                ),
                Drug(
                    "Cognitive behavioural therapy (CBT)",
                    "ASAM/AAAP: challenges unhelpful thinking and builds skills for high-risk situations; can be delivered in groups using a manual (e.g., NIDA cocaine CBT manual).",
                    cautions = listOf(
                        "ASAM/AAAP (Rec 6b, moderate certainty, strong): preferred alongside CM.",
                        "NICE CG51 1.4.6.1: CBT focused on drug misuse should not be offered routinely for stimulant misuse.",
                        "NICE CG51 1.4.6.2: consider CBT for comorbid depression or anxiety.",
                    ),
                    citations = listOf(cite(SUD_ASAM, "Rec 6b; Cognitive Behavioral Therapy"), cite(SUD_CG51, "1.4.6.1–1.4.6.2")),
                ),
                Drug(
                    "Matrix Model",
                    "ASAM/AAAP: 16-week structured programme — individual counselling, CBT, family education, social-support groups and encouragement of mutual-help groups.",
                    cautions = listOf("ASAM/AAAP (Rec 6c, moderate certainty, conditional): preferred alongside CM; trials were mainly in methamphetamine use."),
                    citations = listOf(cite(SUD_ASAM, "Rec 6c; Matrix Model")),
                ),
                Drug(
                    "Brief intervention / motivational interviewing",
                    "NICE CG51: 2 sessions of 10–45 minutes exploring ambivalence, with non-judgemental feedback. WHO (mhGAP 2012): one 5–30-minute session with feedback and follow-up.",
                    cautions = listOf(
                        "ASAM/AAAP (Recs 81–82): refer with linkage support, including warm handoffs; use MI or MET if ambivalent about referral.",
                        "WHO/UNODC: consider specialist referral if problems continue after brief intervention.",
                    ),
                    citations = listOf(
                        cite(SUD_CG51, "1.3.1.3–1.3.1.4"), cite(SUD_WHO_STD, "Brief intervention box (mhGAP 2012)"),
                        cite(SUD_ASAM, "Recs 79–82"),
                    ),
                ),
                Drug(
                    "Family and couples therapy",
                    "NICE CG51 1.4.4.1: behavioural couples therapy, at least 12 weekly sessions focused on drug use, when in close contact with a non-using partner.",
                    cautions = listOf("WHO mhGAP 2.0 (quoted in WHO/UNODC Standards): CM, CBT and family therapy can be offered for psychostimulant dependence."),
                    citations = listOf(cite(SUD_CG51, "1.4.4.1"), cite(SUD_WHO_STD, "Para 216; mhGAP 2.0 box")),
                ),
                Drug(
                    "Mutual-help groups",
                    "NICE CG51: routinely give information on 12-step groups (e.g., Narcotics Anonymous, Cocaine Anonymous); consider helping with the first contact.",
                    citations = listOf(cite(SUD_CG51, "1.3.2.1–1.3.2.2"), cite(SUD_WHO_STD, "Para 219")),
                ),
                Drug(
                    "Digital tools, telemedicine and continuing care",
                    "ASAM/AAAP: digital or web-based therapy only as an add-on, not standalone; consider telemedicine when access is difficult; continue CM through later treatment phases.",
                    citations = listOf(cite(SUD_ASAM, "Recs 7–8; Continuing Care")),
                ),
            ),
        ),
        Tier(
            "Medicines (off-label; none approved)",
            "Off-label only (ASAM/AAAP); consider alongside psychosocial treatment.",
            listOf(
                Drug(
                    "No approved medicine",
                    "ASAM/AAAP: no FDA-approved medicine for stimulant use disorder; evidence is relatively low quality. WHO/UNODC: no medicine has proved consistently efficacious.",
                    cautions = listOf(
                        "WHO/UNODC: medicines in use mainly treat co-occurring psychiatric disorders and withdrawal symptoms.",
                        "ASAM/AAAP: monitor symptoms, function, adherence and non-medical use, more closely for higher-risk medicines.",
                        "ASAM/AAAP (Rec 37): in pregnancy, weigh risk versus benefit to the fetus or infant before using any medicine for stimulant use disorder, intoxication or withdrawal.",
                    ),
                    citations = listOf(cite(SUD_ASAM, "Pharmacotherapy; Rec 37"), cite(SUD_WHO_STD, "Para 243")),
                ),
                Drug(
                    "Bupropion",
                    "ASAM/AAAP (Rec 9, low certainty, conditional): can be considered to promote cocaine abstinence; extra consideration with tobacco use disorder or depression.",
                    cautions = listOf(
                        "ASAM/AAAP: contraindicated with a history of seizures, anorexia or bulimia nervosa; caution with raised seizure risk.",
                        "Trial doses (ASAM/AAAP Appendix K, not a dosing guide): 200–300 mg/day in cocaine trials, e.g., 150 mg twice daily.",
                    ),
                    citations = listOf(cite(SUD_ASAM, "Rec 9; Appendix K, Table 2")),
                ),
                Drug(
                    "Topiramate",
                    "ASAM/AAAP (Rec 10, low certainty, conditional): can be considered to reduce cocaine use; extra consideration with alcohol use disorder.",
                    cautions = listOf(
                        "ASAM/AAAP: cognitive effects, paraesthesia and variable tolerability — titrate slowly; appetite suppression matters if underweight.",
                        "Trial doses (ASAM/AAAP Appendix K, not a dosing guide): 200–300 mg/day in cocaine trials.",
                    ),
                    citations = listOf(cite(SUD_ASAM, "Rec 10; Appendix K, Table 2")),
                ),
                Drug(
                    "Modafinil — specialist only",
                    "ASAM/AAAP (Rec 17, low certainty, conditional): can be considered to reduce cocaine use and improve retention when there is no co-occurring alcohol use disorder.",
                    cautions = listOf(
                        sudPsychostimulantOnly,
                        "ASAM/AAAP: reduces hormonal contraceptive effectiveness — advise another method; generally avoid with a history of psychosis.",
                        "Trial doses (ASAM/AAAP Appendix K, not a dosing guide): mostly 200–400 mg/day.",
                    ),
                    citations = listOf(cite(SUD_ASAM, "Recs 15–17; Appendix K, Table 1")),
                ),
                Drug(
                    "Topiramate + extended-release mixed amphetamine salts — specialist only",
                    "ASAM/AAAP (Rec 18, moderate certainty, conditional): can be considered to reduce cocaine use and craving; extra consideration with alcohol use disorder or ADHD.",
                    cautions = listOf(
                        sudPsychostimulantOnly,
                        "ASAM/AAAP: MAS-ER is an amphetamine — consider baseline cardiovascular screening (e.g., ECG), especially with risk factors; monitor blood pressure and cardiac symptoms early in treatment.",
                        "ASAM/AAAP (topiramate): cognitive effects, paraesthesia and variable tolerability — titrate slowly; appetite suppression matters if underweight.",
                        "Trial doses (ASAM/AAAP Appendix K, not a dosing guide): topiramate up to 100–150 mg twice daily plus MAS-ER up to 60 mg/day.",
                        "ASAM/AAAP: monitor with pill counts, drug testing, frequent contact and prescription-monitoring checks.",
                    ),
                    citations = listOf(
                        cite(SUD_ASAM, "Recs 10, 15–16, 18; Topiramate and Extended-Release Mixed Amphetamine Salts; Amphetamine Formulations; Appendix K, Table 1"),
                    ),
                ),
                Drug(
                    "Long-acting amphetamine — specialist only",
                    "ASAM/AAAP (Rec 19, low certainty, conditional): can be considered to promote cocaine abstinence; dosing at or above the FDA maximum for ADHD may be considered.",
                    cautions = listOf(
                        sudPsychostimulantOnly,
                        "ASAM/AAAP: consider baseline cardiovascular screening (e.g., ECG); monitor blood pressure and cardiac symptoms early in treatment.",
                        "ASAM/AAAP: generally avoid with a history of psychosis or stimulant-induced mood disorder.",
                        "WHO mhGAP 2.0 (quoted in WHO/UNODC Standards): dexamphetamine should not be offered for stimulant use disorders in non-specialised settings.",
                        "Trial doses (ASAM/AAAP Appendix K, not a dosing guide): MAS-ER 60–80 mg/day; lisdexamfetamine 70 mg/day; sustained-release dexamphetamine up to 60 mg/day.",
                    ),
                    citations = listOf(cite(SUD_ASAM, "Recs 15–16, 19; Appendix K, Table 1"), cite(SUD_WHO_STD, "mhGAP 2.0 box after para 245")),
                ),
            ),
        ),
        Tier(
            "Co-occurring conditions",
            "ASAM/AAAP (Recs 21–22): treat stimulant use disorder and co-occurring disorders at the same time, ideally with integrated behavioural treatment.",
            listOf(
                Drug(
                    "Depression, anxiety, insomnia",
                    "ASAM/AAAP (Rec 24): consider medication by severity and duration even if stimulant-induced, checking whether symptoms fit the expected course of use or withdrawal.",
                    cautions = listOf(
                        "WHO mhGAP: assess and treat comorbidity ideally after 2–3 weeks of abstinence, as some problems resolve.",
                        "NICE CG51 1.4.6.2: consider CBT for comorbid depression and anxiety.",
                    ),
                    citations = listOf(cite(SUD_ASAM, "Rec 24"), cite(SUD_MHGAP, "SUB 2, Protocol 2"), cite(SUD_CG51, "1.4.6.2")),
                ),
                Drug(
                    "ADHD",
                    "ASAM/AAAP (Recs 26–27): treat ADHD as part of care — stimulant medicine when benefits outweigh risks (preferably extended-release), otherwise non-stimulants; add behavioural approaches.",
                    cautions = listOf("ASAM/AAAP: monitor with pill counts, drug testing, frequent contact and prescription-monitoring checks."),
                    citations = listOf(cite(SUD_ASAM, "Recs 26–27")),
                ),
                Drug(
                    "Alcohol or tobacco use",
                    "ASAM/AAAP: topiramate gets extra consideration with alcohol use disorder; bupropion with tobacco use disorder; modafinil only without alcohol use disorder.",
                    citations = listOf(cite(SUD_ASAM, "Recs 9–10, 17")),
                ),
                Drug(
                    "Existing psychiatric treatment",
                    "ASAM/AAAP (Rec 25): review the current plan with existing providers; continue medicines where appropriate, considering safety with ongoing stimulant use.",
                    citations = listOf(cite(SUD_ASAM, "Recs 21–22, 25")),
                ),
                Drug(
                    "Psychosis with coexisting substance misuse",
                    "NICE CG120 1.4.23: use antipsychotics as in NICE psychosis guidance — no antipsychotic has shown differential benefit with coexisting substance misuse.",
                    cautions = listOf(
                        "NICE CG120 1.4.3–1.4.4: do not exclude from mental health or substance services because of either condition.",
                        "NICE CG120 1.4.25: warn about interactions between substances of misuse and prescribed medicines.",
                    ),
                    citations = listOf(cite(SUD_CG120, "1.4.3–1.4.4, 1.4.23, 1.4.25")),
                ),
            ),
        ),
        Tier(
            "Cocaine bugs and stimulant psychosis",
            "ASAM/AAAP (Rec 55): look for causes other than stimulants and treat all of them.",
            listOf(
                Drug(
                    "De-escalation and a calm setting",
                    "SAMHSA TIP 33: quiet room, moderate lighting, subdued speech, no sudden movements, reassurance; avoid complete sensory deprivation. ASAM/AAAP: verbal and non-verbal de-escalation.",
                    cautions = listOf("ASAM/AAAP (Rec 58): de-escalation should not delay medication when there is imminent risk of severe complications."),
                    citations = listOf(cite(SUD_TIP33, "Ch. 3, p. 65"), cite(SUD_ASAM, "Recs 55–56, 58")),
                ),
                Drug(
                    "Benzodiazepine (agitation)",
                    "ASAM/AAAP (Rec 57a, high certainty, conditional): can be considered first line for stimulant-induced agitation or confusion. WHO mhGAP: diazepam 5–10 mg orally, IV or rectally, titrated until calm and lightly sedated.",
                    cautions = listOf(
                        "ASAM/AAAP Appendix N examples: lorazepam 1–2 mg IV; diazepam 5–10 mg orally for less severe symptoms; midazolam 5 mg IM or 0.01–0.05 mg/kg IV for acute agitation in adults.",
                        "ASAM/AAAP Appendix N: lorazepam has very slow IM onset (15–30 min); midazolam has a relatively fast IM onset; IM allows dosing in agitated patients without IV access.",
                        "India (IPS 2023): lorazepam 2 mg orally, IM or IV, repeated as necessary.",
                    ),
                    citations = listOf(
                        cite(SUD_ASAM, "Rec 57; Appendix N"), cite(SUD_MHGAP, "SUB — emergency presentations, p. 111"),
                        cite(SUD_IPS, "Cocaine and other stimulants"),
                    ),
                ),
                Drug(
                    "Antipsychotic (psychotic symptoms)",
                    "ASAM/AAAP (Rec 59): treat stimulant-induced psychosis with an antipsychotic; urgency, formulation and duration depend on cause and symptoms. Taper gradually after remission (Rec 23a).",
                    cautions = listOf(
                        "ASAM/AAAP Appendix N examples: olanzapine 5 mg orally; quetiapine 50–100 mg at night; haloperidol or droperidol 5 mg IM for agitation with psychosis.",
                        "WHO mhGAP: haloperidol 1–2.5 mg orally or IM if psychotic symptoms do not respond to diazepam.",
                        "India (IPS 2023): acute agitation and paranoia may need a short-term injectable antipsychotic; antipsychotics are not required unless there is a psychotic disorder (primary, or stimulant/cocaine-induced).",
                        "SAMHSA TIP 33: olanzapine, haloperidol, aripiprazole, risperidone or quetiapine may help; choose by each patient's risk–benefit profile.",
                        "ASAM/AAAP: avoid chlorpromazine and clozapine (seizure risk); consider QT prolongation.",
                        "SAMHSA TIP 33: haloperidol may precipitate or worsen acute dystonic reactions after recent cocaine use.",
                        "Freudenmann & Lepping: drug-induced infestation — antipsychotic for a limited period, at least 3 weeks.",
                    ),
                    citations = listOf(
                        cite(SUD_ASAM, "Recs 23, 59; Appendix N"), cite(SUD_MHGAP, "SUB — emergency presentations, p. 111"),
                        cite(SUD_IPS, "Cocaine and other stimulants"), cite(SUD_TIP33, "Ch. 3, pp. 47, 66"),
                        cite(SUD_DI, "Etiology-specific therapy; Inset 18"),
                    ),
                ),
                Drug(
                    "Skin care",
                    "Freudenmann & Lepping: topical treatment of lesions, light dressings if needed, non-sedating antihistamines for itch; stop itch-causing medicines where possible.",
                    cautions = listOf(
                        "Freudenmann & Lepping: do not give antibiotics or other anti-infectives without a real infection — this reinforces the delusion.",
                        "ASAM/AAAP: check neutrophils — levamisole-adulterated cocaine can cause neutropenia and small-vessel vasculitis.",
                    ),
                    citations = listOf(cite(SUD_DI, "Multimodal therapy; Inset 17"), cite(SUD_ASAM, "Rec 50")),
                ),
            ),
        ),
    ),
    avoid = listOf(
        Caution(
            "Chlorpromazine or clozapine for stimulant psychosis",
            "ASAM/AAAP: avoid — may increase seizure risk. SAMHSA TIP 33: phenothiazines, especially chlorpromazine, are contraindicated as they may lower the seizure threshold.",
            listOf(cite(SUD_ASAM, "Rec 59b"), cite(SUD_TIP33, "Ch. 3, p. 47")),
        ),
        Caution(
            "Psychostimulants or modafinil after psychosis or stimulant-induced mood disorder",
            "ASAM/AAAP: generally avoid these for stimulant use disorder in patients with a history of psychosis (substance-induced or not) or stimulant-induced mood disorder.",
            listOf(cite(SUD_ASAM, "Psychostimulant Medications; Co-occurring Disorders")),
        ),
        Caution(
            "Disulfiram, naltrexone, naltrexone + N-acetylcysteine, mirtazapine or bupropion + naltrexone for cocaine",
            "ASAM/AAAP: evidence judged insufficient or not supportive; these are not among its recommendations.",
            listOf(cite(SUD_ASAM, "Appendix F")),
        ),
        Caution(
            "Unopposed beta-blockers during cocaine intoxication",
            "ASAM/AAAP: CCBs or vasodilators preferred for ischaemia; if a beta-blocker is used, choose one with alpha-1 blockade (carvedilol, labetalol) and consider a coronary vasodilator. " +
                "SAMHSA TIP 33: beta-blockers such as propranolol should not be used for cocaine chest pain — they may enhance vasospasm.",
            listOf(cite(SUD_ASAM, "Rec 68"), cite(SUD_TIP33, "Ch. 3, p. 47")),
        ),
        Caution(
            "Antibiotics or antiparasitics for “bugs” without proven infection",
            "Freudenmann & Lepping: prescribing anti-infectives without a real infection reinforces the delusion.",
            listOf(cite(SUD_DI, "Inset 17")),
        ),
        Caution(
            "Prolonged sedative-hypnotics for withdrawal insomnia",
            "ASAM/AAAP: use cautiously and reassess regularly. WHO/UNODC: short periods only, monitoring response — prolonged use risks tolerance and misuse.",
            listOf(cite(SUD_ASAM, "Post-Acute Symptoms of Withdrawal"), cite(SUD_WHO_STD, "Para 244")),
        ),
        Caution(
            "Excluding people with psychosis from services or CM",
            "NICE CG120: do not exclude people with psychosis and substance misuse from mental health care, substance services or contingency management.",
            listOf(cite(SUD_CG120, "1.4.3–1.4.4, 1.4.22")),
        ),
    ),
    escalate = listOf(
        "ASAM/AAAP: first identify overdose and complications needing a higher level of care, assessing hyperadrenergic signs (tachycardia, hypertension, hyperthermia, agitation); " +
            "severe concerns or complications need acute care — lower-acuity care suits only at most mild hyperadrenergic symptoms, or symptoms responding to medicines manageable there.",
        "WHO mhGAP: chest pain, tachyarrhythmia or other neurological signs during stimulant intoxication — transfer to hospital.",
        "ASAM/AAAP acute complications: hyperthermia, arrhythmia, hypertensive emergency, seizures, stroke, rhabdomyolysis, GI perforation, endocarditis and other serious infections.",
        "ASAM/AAAP: moderate–severe or escalating agitation or psychosis — evaluate for life-threatening medical causes and danger to self or others; refer for emergency workup or psychiatric assessment.",
        "ASAM/AAAP: if agitation or psychosis does not respond, transfer to more intensive care, starting treatment while transport is arranged.",
        "ASAM/AAAP and WHO mhGAP: suicidality may rise as intoxication wears off and in early withdrawal — assess and manage per suicide-risk best practice.",
        "NICE CG120 1.3.1: refer anyone with psychosis or suspected psychosis, including with substance misuse, to secondary mental health services.",
        "SAMHSA TIP 33: hallucinations or paranoia persisting after abstinence and sleep may need longitudinal psychiatric care and evaluation for schizophrenia-spectrum illness.",
        "ASAM/AAAP: body stuffing or packing causes severe, prolonged intoxication — manage in an acute care setting.",
        "ASAM/AAAP: pregnancy — refer for prenatal care and coordinate it with stimulant use disorder treatment.",
    ),
    citations = listOf(
        cite(SUD_ASAM, "Recs 1, 33–34, 45, 53–54, 60–62; Appendix L; Body Stuffing or Packing"),
        cite(SUD_MHGAP, "SUB — emergency presentations, p. 111"), cite(SUD_CG120, "1.3.1"),
        cite(SUD_TIP33, "Ch. 3, pp. 63–66"), cite(SUD_CG51), cite(SUD_WHO_STD), cite(SUD_DI), cite(SUD_IPS),
    ),
)

internal val substanceConditions: List<Condition> = listOf(sudCocaineUseDisorder)
