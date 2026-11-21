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
 * Drugs of misuse: opioids, gabapentinoids, benzodiazepines and cannabis. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them.
 * - Every point is attributed ("AHA 2025: …", "FDA label: …", "WHO 2014: …", "IPS 2023: …", "MoHFW 2026: …").
 * - AHA locators are section + recommendation number (COR) + page of the 2025 Part 10 guideline.
 * - Defined in other files and cited here by id: IPS 2023, ISCCM 2024, WHO mhGAP 2016 and AHA 2023 (Cocaine.kt),
 *   AHA 2025 Part 10 (Resuscitation.kt), AACT/EAPCCT 2013 lavage update (HouseholdTox.kt).
 * - FDA labels are US labels (DailyMed); availability of each product in India was not verified.
 * - Abstract-only sources say so in their status line.
 */

internal val drugToxSources: List<Source> = listOf(
    // Opioids ------------------------------------------------------------------------------------
    Source(
        "tx-dt-fda-naloxone-inj", Authority.FDA, "Prescribing information (DailyMed)",
        "Naloxone Hydrochloride Injection, USP (Fresenius Kabi)", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=201fdedf-1736-4e52-9d7d-de14292547fd",
        status = "Label effective July 2025; 0.4 mg/mL and 1 mg/mL presentations",
    ),
    Source(
        "tx-dt-fda-narcan-nasal", Authority.FDA, "Prescribing information (DailyMed)",
        "NARCAN (naloxone hydrochloride) Nasal Spray 4 mg", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=724df050-5332-4d0a-9a5f-17bf08a547e1",
        status = "Label effective December 2025",
    ),
    Source(
        "tx-dt-who-opioid-overdose-2014", Authority.WHO, "Guideline (ISBN 978 92 4 154881 6)",
        "Community management of opioid overdose", "2014",
        "https://www.who.int/publications/i/item/9789241548816",
        status = "All four recommendations rest on very low-quality evidence (GRADE)",
    ),
    Source(
        "tx-dt-fda-tramadol", Authority.FDA, "Prescribing information (DailyMed)",
        "Tramadol Hydrochloride Tablets USP, 50 mg (Unichem)", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=007bf37f-0e46-426a-ac8c-be63d4b7414c",
        status = "Label effective April 2026",
    ),
    Source(
        "tx-dt-fda-suboxone", Authority.FDA, "Prescribing information (DailyMed)",
        "SUBOXONE (buprenorphine and naloxone) sublingual film", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8a5edcf9-828c-4f97-b671-268ab13a8ecd",
        status = "Label effective December 2025",
    ),
    // Benzodiazepines ----------------------------------------------------------------------------
    Source(
        "tx-dt-fda-flumazenil", Authority.FDA, "Prescribing information (DailyMed)",
        "Flumazenil Injection, USP (Hikma)", "2024",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=436f5616-9626-4b6a-9deb-2986444179d0",
        status = "Label effective June 2024; boxed warning on seizures",
    ),
    // Gabapentinoids -----------------------------------------------------------------------------
    Source(
        "tx-dt-fda-gabapentinoid-dsc-2019", Authority.FDA, "Drug Safety Communication, 19 December 2019 (MedWatch safety alert)",
        "Neurontin, Gralise, Horizant (gabapentin) and Lyrica, Lyrica CR (pregabalin): Drug Safety Communication — Serious Breathing Problems",
        "2019",
        "https://www.fda.gov/safety/medical-product-safety-information/neurontin-gralise-horizant-gabapentin-and-lyrica-lyrica-cr-pregabalin-drug-safety-communication",
        status = "MedWatch summary page; the full Drug Safety Communication page did not load when checked (November 2026)",
    ),
    Source(
        "tx-dt-fda-lyrica", Authority.FDA, "Prescribing information (DailyMed)",
        "LYRICA (pregabalin)", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=d4734e7d-5079-455e-8ff5-8f4539c998a9",
        status = "Label effective April 2025",
    ),
    Source(
        "tx-dt-fda-neurontin", Authority.FDA, "Prescribing information (DailyMed)",
        "NEURONTIN (gabapentin)", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=97935fd9-1d4a-43b6-a5d9-de994591187b",
        status = "Label effective May 2026",
    ),
    Source(
        "tx-dt-extrip-gabapentinoids-2022", Authority.Paper,
        "EXTRIP workgroup systematic review and recommendations (Am J Kidney Dis 2022;79(1):88–104)",
        "Extracorporeal Treatment for Gabapentin and Pregabalin Poisoning: Systematic Review and Recommendations From the EXTRIP Workgroup",
        "2022",
        "https://doi.org/10.1053/j.ajkd.2021.06.027",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "tx-dt-pregabalin-punjab-2026", Authority.Paper,
        "Multicentre cross-sectional study (Subst Abuse Treat Prev Policy 2026;21:41)",
        "A hospital-based multi-centric observational study to explore the prevalence and correlates of non-medical use and dependence of pregabalin among the patients with substance use disorder in Punjab & Chandigarh",
        "2026",
        "https://doi.org/10.1186/s13011-026-00730-9",
        status = "Aneja et al.; 974 patients treated for substance use disorders at Bathinda, Chandigarh, Amritsar, Patiala and Ludhiana, September 2023–August 2024",
    ),
    Source(
        "tx-dt-pregabalin-western-punjab-2023", Authority.Paper,
        "Letter to the editor (Indian J Psychol Med 2023;45(2):206–207)",
        "Pregabalin Use/Misuse: A Source of Consternation in Western Punjab", "2023",
        "https://doi.org/10.1177/02537176221122389",
        status = "Two case descriptions from a tertiary hospital serving western Punjab; published online September 2022",
    ),
    Source(
        "tx-dt-mohfw-pregabalin-h1-2026", Authority.MoHFW, "PIB press release (MoHFW), 22 May 2026",
        "Union Health Ministry Brings Pregabalin Under Schedule H1 to Strengthen Regulation and Prevent Misuse", "2026",
        "https://www.pib.gov.in/PressReleasePage.aspx?PRID=2263975",
        status = "Announces Gazette notification G.S.R. 377(E) of 13 May 2026; the gazette text itself was not reviewed",
    ),
    // Referral (India) ---------------------------------------------------------------------------
    Source(
        "tx-dt-mohfw-ddap-2026", Authority.MoHFW, "PIB press release (MoHFW), 24 March 2026 — Rajya Sabha written reply",
        "Steps Taken to Prevent Substance Addiction Among Youth", "2026",
        "https://www.pib.gov.in/PressReleasePage.aspx?PRID=2244520",
    ),
    Source(
        "tx-dt-mohfw-ddap-training-2026", Authority.MoHFW, "PIB press release (MoHFW), 26 October 2026",
        "Union Health Minister Shri J.P. Nadda Launches Capacity Building Programme for Medical and Allied Healthcare Professionals Engaged in Drug De-addiction Services",
        "2026",
        "https://www.pib.gov.in/PressReleasePage.aspx?PRID=2328634",
    ),
    // Cannabis -----------------------------------------------------------------------------------
    Source(
        "tx-dt-rcem-chs-2023", Authority.Paper, "Royal College of Emergency Medicine Best Practice Guideline (June 2023)",
        "Suspected Cannabinoid Hyperemesis Syndrome in Emergency Departments", "2023",
        "https://res.cloudinary.com/studio-republic/images/v1688459209/RCEM_Cannabinoid_Hyperemesis_Syndrome_FINAL_June_2023/RCEM_Cannabinoid_Hyperemesis_Syndrome_FINAL_June_2023.pdf?_i=AA",
        status = "PDF on RCEM's document host; summary also published in Emerg Med J 2024;41(5):328–331 (doi:10.1136/emermed-2024-213886)",
    ),
    Source(
        "tx-dt-aga-chs-2024", Authority.Paper, "AGA Institute Clinical Practice Update (Gastroenterology 2024;166(5):930–934.e1)",
        "AGA Clinical Practice Update on Diagnosis and Management of Cannabinoid Hyperemesis Syndrome: Commentary", "2024",
        "https://doi.org/10.1053/j.gastro.2024.01.040",
        status = "Full text not accessible for this review; diagnostic criteria checked as reproduced on AGA's website (gastro.org, 29 April 2024)",
    ),
    Source(
        "tx-dt-havoc-2021", Authority.Paper, "Randomised controlled trial (Ann Emerg Med 2021;77(6):613–619)",
        "Intravenous Haloperidol Versus Ondansetron for Cannabis Hyperemesis Syndrome (HaVOC): A Randomized, Controlled Trial",
        "2021",
        "https://doi.org/10.1016/j.annemergmed.2020.08.021",
        status = "Reviewed from the published abstract; 30 treated patients, Canada",
    ),
    Source(
        "tx-dt-saem-grace-chs-2024", Authority.Paper, "SAEM GRACE systematic review (Acad Emerg Med 2024;31(5):493–503)",
        "SAEM GRACE: Dopamine antagonists and topical capsaicin for cannabis hyperemesis syndrome in the emergency department: A systematic review of direct evidence",
        "2024",
        "https://doi.org/10.1111/acem.14770",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "tx-dt-peds-edible-cannabis-2023", Authority.Paper, "US National Poison Data System analysis (Pediatrics 2023;151(2):e2022057761)",
        "Pediatric Edible Cannabis Exposures and Acute Toxicity: 2017–2021", "2023",
        "https://doi.org/10.1542/peds.2022-057761",
        status = "Reviewed from the published abstract; children younger than 6 years, United States",
    ),
    Source(
        "tx-dt-cdc-synthetic-cannabinoids-2015", Authority.CDC, "MMWR 2015;64(22):618–619 (Notes from the Field)",
        "Increase in Reported Adverse Health Effects Related to Synthetic Cannabinoid Use — United States, January–May 2015",
        "2015",
        "https://www.cdc.gov/mmwr/preview/mmwrhtml/mm6422a5.htm",
        status = "US poison-centre calls",
    ),
)

// Sources defined in this file
private const val NALOX_INJ = "tx-dt-fda-naloxone-inj"
private const val NARCAN = "tx-dt-fda-narcan-nasal"
private const val WHO14 = "tx-dt-who-opioid-overdose-2014"
private const val TRAM = "tx-dt-fda-tramadol"
private const val SUBOX = "tx-dt-fda-suboxone"
private const val FLUM = "tx-dt-fda-flumazenil"
private const val GABA_DSC = "tx-dt-fda-gabapentinoid-dsc-2019"
private const val LYRICA = "tx-dt-fda-lyrica"
private const val NEURONTIN = "tx-dt-fda-neurontin"
private const val EXTRIP = "tx-dt-extrip-gabapentinoids-2022"
private const val ANEJA = "tx-dt-pregabalin-punjab-2026"
private const val IJPM = "tx-dt-pregabalin-western-punjab-2023"
private const val H1 = "tx-dt-mohfw-pregabalin-h1-2026"
private const val DDAP = "tx-dt-mohfw-ddap-2026"
private const val DDAP_TRAIN = "tx-dt-mohfw-ddap-training-2026"
private const val RCEM = "tx-dt-rcem-chs-2023"
private const val AGA = "tx-dt-aga-chs-2024"
private const val HAVOC = "tx-dt-havoc-2021"
private const val GRACE = "tx-dt-saem-grace-chs-2024"
private const val TWEET = "tx-dt-peds-edible-cannabis-2023"
private const val CDC_SC = "tx-dt-cdc-synthetic-cannabinoids-2015"

// Sources defined in other files
private const val DT_IPS = "tx-cocaine-ips-2023"
private const val DT_ISCCM = "tx-cocaine-isccm-2024"
private const val DT_MHGAP = "tx-cocaine-who-mhgap-2016"
private const val DT_AHA23 = "tx-cocaine-aha-tox-2023"
private const val DT_AHA25 = "em-resus-aha-special-2025"
private const val DT_LAVAGE = "tx-hh-aact-lavage-2013"

internal val drugToxProtocols: List<Protocol> = listOf(
    // =============================================================================================
    // 1. Opioid overdose
    // =============================================================================================
    Protocol(
        id = "opioid-overdose",
        title = "Opioid overdose",
        summary = "Opioid toxidrome, airway and ventilation first, naloxone dosing and infusion, tramadol and buprenorphine, " +
            "precipitated withdrawal, observation and referral — attributed to AHA, WHO, FDA labels, IPS, ISCCM and MoHFW.",
        keywords = listOf(
            "opioid overdose", "opioid poisoning", "opiate", "heroin", "smack", "chitta", "brown sugar", "tramadol",
            "morphine", "codeine", "cough syrup", "buprenorphine", "Suboxone", "pentazocine", "methadone", "fentanyl",
            "opium", "afeem", "poppy husk", "bhukki", "doda", "naloxone", "Narcan", "overdose", "pinpoint pupils",
            "respiratory depression",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise opioid overdose",
                timing = "At first contact",
                points = listOf(
                    "WHO 2014: opioid overdose is characterised by apnoea, miosis and stupor; cardiac arrest is a late complication, secondary to respiratory arrest.",
                    "IPS 2023 (AIIMS NDDTC authors): overdose — pinpoint pupils, unconsciousness and respiratory depression; severe intoxication and overdose may be clinically indistinguishable.",
                    "FDA tramadol label: marked mydriasis rather than miosis may be seen with hypoxia. IPS 2023: pupils are sometimes dilated from severe anoxia.",
                    "IPS 2023: ask friends or family; look for pills or injection paraphernalia; ask about sedative-hypnotics taken with the opioid.",
                    "IPS 2023 differentials: head injury, meningitis or encephalitis, sepsis, hepatic or metabolic encephalopathy, DKA or hypoglycaemia, electrolyte disturbance, hypoxia or hypercapnia.",
                    "ISCCM 2024: urine opiate immunoassays detect morphine, codeine and heroin; methadone, oxycodone, fentanyl and tramadol are frequently undetected.",
                ),
                checklistTitle = "Opioid toxidrome (ISCCM 2024 Table A1; IPS 2023 Table 4)",
                checklist = listOf(
                    "CNS depression, stupor or coma",
                    "Miosis — pinpoint pupils",
                    "Slow breathing (bradypnoea) or apnoea",
                    "Hyporeflexia",
                    "Pulmonary oedema",
                    "Needle (track) marks",
                    "Slurred speech, psychomotor retardation",
                ),
                citations = listOf(
                    cite(WHO14, "Introduction — Opioid overdose (p. 1)"),
                    cite(DT_IPS, "Opioid intoxication in the emergency setting; Table 4; Assessment for opioid intoxication"),
                    cite(TRAM, "§10 Overdosage"),
                    cite(DT_ISCCM, "Annexure 4 Table A1 (toxidromes); Annexure 8 (urine drug screens)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Airway and breathing first",
                timing = "Immediately — ventilation is the priority (WHO 2014)",
                points = listOf(
                    "AHA 2025 (COR 1): suspected opioid overdose with respiratory depression or arrest and a definite pulse — give breaths or bag-mask ventilation.",
                    "WHO 2014 (strong): first responders focus on airway management, assisting ventilation and giving naloxone; turn the person on their side if vomiting or seizing.",
                    "IPS 2023: oxygen or bag-mask ventilation if SpO₂ <93% or respiratory rate <8/min; intubate if the airway or saturation cannot be maintained.",
                    "AHA 2025 (COR 1): do not delay standard resuscitation or calling for help while giving naloxone or awaiting its effect.",
                    "AHA 2025: cardiac arrest — high-quality CPR with ventilation takes priority; naloxone may be reasonable (2b) only if it does not interfere with CPR.",
                    "IPS 2023: check glucose in the unconscious patient as per local protocol.",
                ),
                citations = listOf(
                    cite(DT_AHA25, "Toxicology: Opioid Overdose — Rec 1, 4, 5, 6 (S651) and supportive text (S652–S653)"),
                    cite(WHO14, "Rec 3 and remarks (p. 14)"),
                    cite(DT_IPS, "Management of opioid intoxication in the emergency setting; Figure 4"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Naloxone: dose and route",
                timing = "Improvement expected within 2–3 min (IPS 2023)",
                points = listOf(
                    "AHA 2025 (COR 1): give naloxone in respiratory arrest with a pulse. Table 4: 0.2–2 mg IV/IO/IM or 2–4 mg intranasal, every 2–3 min as needed.",
                    "FDA injection label: 0.4–2 mg IV, repeat every 2–3 min; if no response after 10 mg, question the diagnosis. IM or SC if IV unavailable.",
                    "IPS 2023: 0.2–0.4 mg, or 1–2 mg with apnoea or cardiorespiratory arrest; repeat every 2–3 min to a maximum of 10 mg.",
                    "WHO 2014: initial 0.4–2 mg; 0.4–0.8 mg is usually effective; doses above 0.8 mg IM/IV/SC are more likely to precipitate significant withdrawal.",
                    "FDA Narcan label: one 4 mg spray in one nostril; repeat every 2–3 min with a new device, alternating nostrils. AHA 2025: IN/IM may need repeat doses.",
                    "AHA 2025 Table 4: titrate to reversal of respiratory depression and protective airway reflexes. AHA 2025: non-opioid sedatives are typically not reversed.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Naloxone — child, initial (FDA injection label)",
                        dose = WeightDose(0.01, 0.01, unit = "mg", per = "IV; IM or SC in divided doses if IV unavailable"),
                        note = "FDA label: if no adequate response, a subsequent dose of 0.1 mg/kg may be given. Neonates: 0.01 mg/kg IV, IM or SC.",
                    ),
                    StepDose(
                        label = "Naloxone — child, subsequent dose (FDA injection label)",
                        dose = WeightDose(0.1, 0.1, unit = "mg", per = "IV, if 0.01 mg/kg gave no adequate response"),
                    ),
                    StepDose(
                        label = "Naloxone — child (AHA 2025 Table 4)",
                        dose = WeightDose(0.1, 0.1, unit = "mg", per = "IV/IO initial dose, repeated as needed"),
                        note = "AHA 2025 Table 4: the paediatric dose should not exceed the adult dose (adult 0.2–2 mg IV/IO/IM). " +
                            "Newborn infants are outside the scope of the AHA guideline.",
                        maxTotal = 2.0,
                    ),
                ),
                citations = listOf(
                    cite(DT_AHA25, "Toxicology: Opioid Overdose — Rec 3 (S651) and supportive text (S652–S653); Table 4 (S633)"),
                    cite(NALOX_INJ, "Dosage and Administration — Usage in Adults, Usage in Children, Usage in Neonates"),
                    cite(DT_IPS, "Management of opioid intoxication in the emergency setting"),
                    cite(WHO14, "Rec 2 remarks — Dosage (p. 12)"),
                    cite(NARCAN, "§2.1–2.2"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Re-sedation: repeat doses and infusion",
                timing = "Naloxone acts for about 60–90 min (IPS 2023)",
                points = listOf(
                    "FDA injection label: some opioids outlast naloxone (adult half-life 30–81 min); keep under continued surveillance and repeat doses as necessary.",
                    "AHA 2025 (2a): repeated naloxone doses can be beneficial for recurrent respiratory depression; an IV infusion is another option.",
                    "AHA 2025 Table 4: infusion of two-thirds of the waking (reversal) dose per hour.",
                    "IPS 2023: consider an infusion for longer-acting opioids such as buprenorphine. Example: reversal dose 1.2 mg → 0.8 mg/h, half over the first 15 min.",
                    "FDA injection label: 2 mg in 500 mL normal saline or 5% dextrose gives 0.004 mg/mL; titrate the rate to response; use within 24 h.",
                    "IPS 2023: once reversal is achieved, avoid higher doses — naloxone may cause vomiting.",
                ),
                citations = listOf(
                    cite(NALOX_INJ, "Dosage and Administration — Intravenous Infusion; Warnings — Repeat Administration; Pharmacokinetics"),
                    cite(DT_AHA25, "Following successful response to opioid antagonists — Rec 3–4 and supportive text (S653); Table 4 (S633)"),
                    cite(DT_IPS, "Management of opioid intoxication in the emergency setting"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Tramadol: seizures and serotonin toxicity",
                points = listOf(
                    "FDA tramadol label: overdose causes respiratory depression, coma, seizures, QT prolongation, hypotension and death; fatal risk rises with alcohol or other CNS depressants.",
                    "FDA tramadol label: reversal agents reverse some but not all effects and increase seizure risk; in animals, benzodiazepines suppressed tramadol convulsions, naloxone increased them.",
                    "IPS 2023: tramadol and dextropropoxyphene lower the seizure threshold. ISCCM 2024 (moderate): benzodiazepines are the first antiseizure drug in poisoning, not phenytoin.",
                    "FDA tramadol label: serotonin syndrome may occur, particularly with SSRIs, SNRIs, tricyclics, MAO inhibitors, linezolid or IV methylene blue — stop tramadol if suspected.",
                    "FDA tramadol label: haemodialysis removes less than 7% of a dose in 4 h and is not expected to help.",
                    "FDA tramadol label: hypoglycaemia and hyponatraemia (often severe, sometimes SIADH) are reported with tramadol.",
                ),
                checklistTitle = "Serotonin syndrome features (FDA tramadol label §5.9)",
                checklist = listOf(
                    "Mental status change — agitation, hallucinations, coma",
                    "Autonomic instability — tachycardia, labile blood pressure, hyperthermia",
                    "Neuromuscular — hyperreflexia, incoordination, rigidity",
                    "Gastrointestinal — nausea, vomiting, diarrhoea",
                ),
                citations = listOf(
                    cite(TRAM, "§10 Overdosage; §5.9 Serotonin syndrome; §5.10 Seizure; §5.20 Hyponatremia; §5.21 Hypoglycemia"),
                    cite(DT_IPS, "Opioid intoxication in the emergency setting"),
                    cite(DT_ISCCM, "Q19 (moderate)"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Buprenorphine and mixed overdoses",
                points = listOf(
                    "FDA Suboxone label: overdose causes pinpoint pupils, sedation, hypotension, hypoglycaemia and respiratory depression; re-establishing ventilation is the primary management.",
                    "FDA Suboxone label: naloxone may help, but higher than normal and repeated doses may be needed; monitor for the long duration of action.",
                    "FDA Narcan label: buprenorphine reversal is gradual in onset and may be incomplete; larger or repeat naloxone doses may be required.",
                    "FDA Suboxone label: accidental exposure can cause severe, possibly fatal, respiratory depression in children; deaths reported in opioid-naïve adults after 2 mg.",
                    "AHA 2025 (2a): suspected combined opioid and benzodiazepine poisoning — give naloxone first. FDA Suboxone label lists gabapentinoids among CNS depressants raising overdose risk.",
                    "IPS 2023: at the same dose, fentanyl is likely to act more intensely and buprenorphine much longer than heroin.",
                ),
                citations = listOf(
                    cite(SUBOX, "§10 Overdosage; §5.3; §5.4; §5.11"),
                    cite(NARCAN, "§5.2"),
                    cite(DT_AHA25, "Toxicology: Benzodiazepines — adult Rec 1 (S634)"),
                    cite(DT_IPS, "Assessment for opioid intoxication"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Precipitated withdrawal and other complications",
                points = listOf(
                    "FDA injection label: in opioid dependence, abrupt complete reversal precipitates withdrawal within minutes, subsiding in about 2 h; severity relates to naloxone dose.",
                    "WHO 2014: tailor the dose to avoid marked withdrawal; the panel judged doses above 2 mg a potential harm (prolonged withdrawal, seizures).",
                    "AHA 2023: naloxone can cause sudden pulmonary oedema, which may be severe but responds readily to positive-pressure ventilation.",
                    "FDA Narcan label: abrupt postoperative reversal has caused hypertension, VT/VF, pulmonary oedema and cardiac arrest, mainly with pre-existing cardiovascular disease.",
                    "AHA 2023: alternatives to naloxone are observation (if breathing normally, whatever the CNS depression) and ventilatory support.",
                    "FDA Narcan label: neonatal withdrawal may be life-threatening; where abrupt withdrawal should be avoided, prefer a weight-dosed, titratable naloxone product.",
                ),
                checklistTitle = "Opioid withdrawal signs (FDA naloxone labels)",
                checklist = listOf(
                    "Body aches, abdominal cramps",
                    "Diarrhoea, nausea or vomiting",
                    "Tachycardia, raised blood pressure, fever",
                    "Runny nose, sneezing, yawning, sweating, piloerection",
                    "Restlessness, irritability, shivering or trembling",
                    "Neonates — convulsions, excessive crying, hyperactive reflexes",
                ),
                citations = listOf(
                    cite(NALOX_INJ, "Warnings — Drug Dependence; Clinical Pharmacology"),
                    cite(WHO14, "Rec 2 remarks (p. 12); Evidence to recommendation — Benefits and harms (p. 11)"),
                    cite(DT_AHA23, "§11 Opioids — Introduction"),
                    cite(NARCAN, "§5.3; §8.4"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Observation before discharge",
                timing = "At least 2 h after the last naloxone dose for short-acting opioids (WHO 2014)",
                points = listOf(
                    "AHA 2025 (COR 1): after response, observe in a health-care setting until low risk of recurrence, normal consciousness and normal vital signs.",
                    "AHA 2025: abbreviated observation may suffice for fentanyl, morphine or heroin; long-acting or sustained-release opioids may need longer.",
                    "WHO 2014 (strong): at least 2 h after short-acting opioids such as heroin, longer for long-acting; 'fully recovered' = baseline consciousness 2 h after last dose.",
                    "IPS 2023: observe 4–6 h after a naloxone infusion is stopped. WHO mhGAP 2016: after response, observe 1–2 h and repeat naloxone as needed.",
                    "FDA naloxone labels: monitor an opioid-intoxicated child for at least 24 h — relapse may occur as naloxone is metabolised.",
                    "ISCCM 2024 (strong): observe all poisonings at least 24 h after symptoms resolve; neuropsychiatric assessment before discharge if intentional (moderate).",
                ),
                citations = listOf(
                    cite(DT_AHA25, "Following successful response to opioid antagonists — Rec 1–2 and supportive text (S653)"),
                    cite(WHO14, "Rec 4 and remarks (p. 16)"),
                    cite(DT_IPS, "Management of opioid intoxication in the emergency setting"),
                    cite(DT_MHGAP, "SUB emergency algorithm — suspected sedative intoxication (pp. 108–109)"),
                    cite(NALOX_INJ, "Pediatric Use"),
                    cite(NARCAN, "§8.4"),
                    cite(DT_ISCCM, "Q22 (moderate); Q23 (strong)"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Prevent the next overdose: referral",
                points = listOf(
                    "AHA 2025 (COR 1): adults treated for opioid overdose should receive naloxone and teaching at discharge; for children, their caregivers as appropriate.",
                    "WHO 2014 (strong): people likely to witness an overdose should have naloxone and training; recovery is a 'teachable moment' to discuss treatment.",
                    "IPS 2023: one overdose carries a high risk of another; offer treatment for dependence — buprenorphine or methadone substitution lowers overdose mortality.",
                    "MoHFW 2026: Drug De-addiction Programme — 6 treatment centres in central government hospitals, 25 Drug Treatment Clinics; MoSJE-funded Addiction Treatment Facilities in 154 government hospitals.",
                    "MoHFW 2026: DDAP institutions include NDDTC AIIMS New Delhi and PGIMER Chandigarh. Toll-free helpline 14446 gives counselling and referral.",
                    "ISCCM 2024: discharge high-risk patients to a responsible caretaker; offer social support and rehabilitation for substance use.",
                ),
                citations = listOf(
                    cite(DT_AHA25, "Opioid antagonist education and distribution — Rec 1–2 (S653)"),
                    cite(WHO14, "Rec 1 (p. 9); Rec 4 remarks (p. 16)"),
                    cite(DT_IPS, "Management of opioid intoxication in the emergency setting"),
                    cite(DDAP, "Drug De-addiction Programme; NAPDDR; helpline"),
                    cite(DDAP_TRAIN, "About Drug De-Addiction Programme (DDAP)"),
                    cite(DT_ISCCM, "Q22 rationale"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Initial naloxone doses differ: FDA injection label 0.4–2 mg IV; AHA 2025 0.2–2 mg IV/IO/IM or 2–4 mg intranasal; IPS 2023 0.2–0.4 mg (1–2 mg in apnoea or arrest); " +
                    "WHO 2014 and WHO mhGAP 2016 0.4–2 mg, WHO 2014 adding that 0.4–0.8 mg usually suffices.",
                listOf(
                    cite(NALOX_INJ, "Dosage and Administration"),
                    cite(DT_AHA25, "Table 4 (S633)"),
                    cite(DT_IPS, "Management of opioid intoxication"),
                    cite(WHO14, "Rec 2 remarks (p. 12)"),
                    cite(DT_MHGAP, "SUB Table 1 (p. 126)"),
                ),
            ),
            ProtocolNote(
                "Observation differs: WHO 2014 — at least 2 h (short-acting opioids); earlier WHO guidance cited there — 4 h; WHO mhGAP 2016 — 1–2 h; IPS 2023 — 4–6 h after infusion; " +
                    "AHA 2025 — until risk is low and vital signs normal; FDA labels — children at least 24 h; ISCCM 2024 — 24 h after symptoms resolve (all poisonings).",
                listOf(
                    cite(WHO14, "Rec 4 remarks (p. 16); Existing relevant guidelines (p. 3)"),
                    cite(DT_MHGAP, "SUB emergency algorithm (p. 109)"),
                    cite(DT_IPS, "Management of opioid intoxication"),
                    cite(DT_AHA25, "Following successful response — Rec 1–2 (S653)"),
                    cite(NARCAN, "§8.4"),
                    cite(DT_ISCCM, "Q23"),
                ),
            ),
            ProtocolNote(
                "Paediatric naloxone differs: FDA injection label 0.01 mg/kg IV, then 0.1 mg/kg if no response; AHA 2025 Table 4 0.1 mg/kg, not exceeding the adult dose.",
                listOf(cite(NALOX_INJ, "Usage in Children"), cite(DT_AHA25, "Table 4 (S633)")),
            ),
            ProtocolNote(
                "Cardiac arrest: AHA 2025 puts CPR with ventilation before an opioid antagonist (naloxone 2b in arrest) and states this differs from the naloxone and nalmefene prescribing information.",
                listOf(cite(DT_AHA25, "Toxicology: Opioid Overdose — Rec 5–6 and supportive text (S651–S652)"), cite(NARCAN, "§2.1")),
            ),
            ProtocolNote(
                "Intranasal naloxone: the FDA-labelled US product is a 4 mg single-dose spray; WHO 2014 called intranasal use off-label and described 1 mg/mL solution sprayed with an atomiser. " +
                    "Availability of a nasal product in India was not verified.",
                listOf(cite(NARCAN, "§3 Dosage forms and strengths"), cite(WHO14, "Rec 2 remarks (p. 12)")),
            ),
            ProtocolNote(
                "Scope: AHA recommendations follow the 2025 Part 10 guideline; the 2023 focused update is cited only for background statements not repeated in 2025.",
                listOf(cite(DT_AHA25, "Toxicology: Opioid Overdose — Introduction (S651)"), cite(DT_AHA23, "§11 Opioids — Introduction")),
            ),
        ),
        citations = listOf(
            cite(DT_AHA25), cite(WHO14), cite(NALOX_INJ), cite(NARCAN), cite(TRAM), cite(SUBOX), cite(DT_IPS), cite(DT_ISCCM),
            cite(DT_MHGAP), cite(DT_AHA23), cite(DDAP), cite(DDAP_TRAIN),
        ),
    ),

    // =============================================================================================
    // 2. Pregabalin and gabapentin
    // =============================================================================================
    Protocol(
        id = "pregabalin-gabapentinoids",
        title = "Pregabalin and gabapentin (gabapentinoids)",
        summary = "Misuse in Punjab, overdose features, respiratory depression with opioids, supportive care, haemodialysis, withdrawal " +
            "and Schedule H1 — attributed to FDA, EXTRIP, AHA, ISCCM, IPS, MoHFW and Indian studies.",
        keywords = listOf(
            "pregabalin", "gabapentin", "gabapentinoid", "Lyrica", "Lyrica CR", "Neurontin", "Gralise", "Horizant",
            "signature capsule", "signature", "pregaba", "capsule", "Schedule H1", "nerve pain medicine",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise misuse (Punjab data)",
                points = listOf(
                    "Aneja 2026 (974 patients with substance use disorders, Punjab and Chandigarh): 44.6% used pregabalin non-medically; 24.8% met ICD-10 dependence criteria.",
                    "Aneja 2026: about one-third co-used opioids, mainly heroin; opioid use disorder raised the odds of pregabalin use (aOR 5.3).",
                    "Aneja 2026: 63.6% took 300–2400 mg/day and about 12% more than 2400 mg/day. FDA Lyrica label maximum dose: 600 mg/day.",
                    "Indian J Psychol Med 2023 (western Punjab): 'Signature' capsules is a colloquial name for pregabalin; two users escalated from 300 to 1500–1800 mg/day for a 'high'.",
                    "Aneja 2026: 73.5% bought pregabalin from a licensed chemist without a legible prescription; 7.8% reported a seizure linked to its use.",
                    "MoHFW 2026: pregabalin is misused for sedative, euphoric and dissociative effects, particularly among youth.",
                ),
                citations = listOf(
                    cite(ANEJA, "Abstract; Results — pattern and clinical correlates"),
                    cite(IJPM, "Cases 1–2; discussion"),
                    cite(LYRICA, "§2 Dosage and Administration"),
                    cite(H1, "Press release"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Overdose features",
                points = listOf(
                    "FDA Lyrica label: overdose — reduced consciousness, depression or anxiety, confusion, agitation and restlessness; seizures and heart block also reported.",
                    "FDA Lyrica label: deaths reported after pregabalin overdose alone and with other CNS depressants; there is no specific antidote.",
                    "FDA Neurontin label: gabapentin overdose — double vision, tremor, slurred speech, drowsiness, altered mental status, dizziness, lethargy, diarrhoea; fatal respiratory depression reported.",
                    "FDA Lyrica label: peak level within 1.5 h; about 90% excreted unchanged in urine; elimination half-life about 6 h.",
                    "WHO mhGAP 2016: in sedative intoxication, pinpoint pupils suggest opioid overdose; normal pupils make it less likely — see Opioid overdose.",
                ),
                citations = listOf(
                    cite(LYRICA, "§10 Overdosage; §12.3 Pharmacokinetics"),
                    cite(NEURONTIN, "§10 Overdosage"),
                    cite(DT_MHGAP, "SUB emergency algorithm — suspected sedative intoxication (pp. 108–109)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Respiratory depression with opioids and other depressants",
                points = listOf(
                    "FDA 2019 safety communication: serious breathing difficulties with gabapentin or pregabalin in patients with respiratory risk factors; the elderly are at higher risk.",
                    "FDA Lyrica and Neurontin labels: serious, life-threatening or fatal respiratory depression with CNS depressants including opioids, or with underlying respiratory impairment.",
                    "FDA labels: management may include close observation, supportive measures and reduction or withdrawal of CNS depressants, including the gabapentinoid.",
                    "FDA 2019: warning signs — confusion, unusual dizziness, extreme sleepiness, slowed or shallow breathing, unresponsiveness, bluish lips, fingers or toes.",
                    "FDA 2019: abuse-potential trials with opioids were required because misuse of these products together is increasing.",
                ),
                checklistTitle = "Higher-risk patients (FDA 2019; FDA labels)",
                checklist = listOf(
                    "Taking opioids",
                    "Taking benzodiazepines or other CNS depressants",
                    "COPD or other conditions that reduce lung function",
                    "Older age",
                ),
                citations = listOf(
                    cite(GABA_DSC, "Issue; Recommendation"),
                    cite(LYRICA, "§5.5 Respiratory Depression"),
                    cite(NEURONTIN, "§5.8 Respiratory Depression"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Supportive management",
                timing = "No specific antidote (FDA Lyrica label)",
                points = listOf(
                    "FDA Lyrica label: general supportive care with vital-sign monitoring and observation of clinical status; contact a poison centre.",
                    "AHA 2025: opioid antagonists restore breathing in opioid overdose, but non-opioid sedatives contributing to respiratory depression are typically not reversed.",
                    "IPS 2023 (polysubstance): serial ECG, lateral position against aspiration, ventilatory support when required; observation for at least 24–48 h may be advised.",
                    "ISCCM 2024 (strong): activated charcoal within 1 h of ingestion; contraindicated with depressed consciousness without airway protection.",
                    "ISCCM 2024 (moderate): benzodiazepines are the first antiseizure drug in poisoning; recommends against phenytoin first.",
                    "FDA Lyrica label mentions emesis or gastric lavage; AACT/EAPCCT 2013: lavage should not be performed routinely, if at all. ISCCM 2024: no emetics.",
                ),
                citations = listOf(
                    cite(LYRICA, "§10 Overdosage"),
                    cite(DT_AHA25, "Toxicology: Opioid Overdose — supportive text (S652)"),
                    cite(DT_IPS, "Polysubstance use — management"),
                    cite(DT_ISCCM, "Q12a (strong); Q17 (moderate); Q19 (moderate); Annexure 10"),
                    cite(DT_LAVAGE, "Conclusions"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Haemodialysis",
                points = listOf(
                    "FDA Lyrica label: pregabalin is removed by haemodialysis — standard sessions clear about 50% in 4 h. FDA Neurontin label: gabapentin can be removed by haemodialysis.",
                    "EXTRIP 2022: both are 'dialyzable' with decreased kidney function; haemodialysis gave clearance >150 mL/min and half-life <5 h.",
                    "EXTRIP 2022: normal kidney function — suggests against extracorporeal treatment (weak recommendation, very low-quality evidence).",
                    "EXTRIP 2022: decreased kidney function with coma requiring mechanical ventilation — suggests extracorporeal treatment (weak recommendation, very low-quality evidence).",
                    "EXTRIP 2022: no clear clinical benefit identified; severe toxicity and death were rare with or without extracorporeal treatment.",
                    "ISCCM 2024: does not recommend routine extracorporeal therapy in poisoning.",
                ),
                citations = listOf(
                    cite(LYRICA, "§10 Overdosage"),
                    cite(NEURONTIN, "§10 Overdosage"),
                    cite(EXTRIP, "Abstract"),
                    cite(DT_ISCCM, "Q21"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Withdrawal",
                points = listOf(
                    "FDA Lyrica label: abrupt or rapid discontinuation — insomnia, nausea, headache, anxiety, hyperhidrosis and diarrhoea; taper over at least 1 week.",
                    "FDA Lyrica label (postmarketing): withdrawal reports also include seizures, depression, suicidal ideation, agitation, confusion, psychotic symptoms, sweating and tremor.",
                    "FDA Neurontin label: withdrawal usually starts within 48 h of stopping; seizures, agitation, confusion, psychosis, anxiety, insomnia, sweating and tremor reported.",
                    "FDA Lyrica label: in seizure disorders, withdraw gradually to minimise increased seizure frequency.",
                    "Indian J Psychol Med 2023: a western Punjab user described yawning, lethargy and sadness on withdrawal.",
                ),
                checklistTitle = "Withdrawal features (FDA Lyrica and Neurontin labels §9.3)",
                checklist = listOf(
                    "Insomnia, anxiety, agitation",
                    "Nausea, diarrhoea",
                    "Headache, pain",
                    "Sweating, tremor",
                    "Seizures",
                    "Confusion, disorientation, psychotic symptoms",
                    "Depression, suicidal ideation or behaviour",
                ),
                citations = listOf(
                    cite(LYRICA, "§5.4 Abrupt or rapid discontinuation; §9.3 Dependence"),
                    cite(NEURONTIN, "§9.3 Dependence"),
                    cite(IJPM, "Case 2"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Disposition, regulation and referral",
                points = listOf(
                    "ISCCM 2024 (strong): observe at least 24 h after symptoms resolve; neuropsychiatric assessment before discharge if poisoning may be intentional (moderate).",
                    "MoHFW 2026: pregabalin moved to Schedule H1 (G.S.R. 377(E)) — sale only on a registered medical practitioner's prescription, with a separate sales register.",
                    "FDA Lyrica label: pregabalin is US Schedule V; watch for tolerance, dose escalation and drug-seeking. FDA Neurontin label: gabapentin is not a controlled substance.",
                    "Aneja 2026: calls for screening patients with substance use disorders for pregabalin non-medical use and dependence.",
                    "MoHFW 2026: de-addiction care through DDAP centres, Drug Treatment Clinics and Addiction Treatment Facilities; toll-free helpline 14446.",
                ),
                citations = listOf(
                    cite(DT_ISCCM, "Q22 (moderate); Q23 (strong)"),
                    cite(H1, "Press release"),
                    cite(LYRICA, "§9.1 Controlled Substance"),
                    cite(NEURONTIN, "§9.1 Controlled Substance"),
                    cite(ANEJA, "Abstract — Conclusion"),
                    cite(DDAP, "Drug De-addiction Programme; NAPDDR; helpline"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Haemodialysis differs: the FDA labels state both drugs are removed by haemodialysis; EXTRIP 2022 suggests against it with normal kidney function and suggests it only with decreased kidney function plus coma needing ventilation.",
                listOf(cite(LYRICA, "§10"), cite(NEURONTIN, "§10"), cite(EXTRIP, "Abstract")),
            ),
            ProtocolNote(
                "Decontamination wording differs: the Lyrica label still mentions emesis or gastric lavage; AACT/EAPCCT 2013 advises against routine lavage and ISCCM 2024 recommends against emetics.",
                listOf(cite(LYRICA, "§10"), cite(DT_LAVAGE, "Conclusions"), cite(DT_ISCCM, "Q17")),
            ),
            ProtocolNote(
                "Regulation differs: India — Schedule H1 since 2026 (MoHFW); United States — pregabalin Schedule V, gabapentin not controlled (FDA labels). " +
                    "In October 2021 the Mansa district administration (Punjab) prohibited sale of pregabalin 300 mg without a prescription (Indian J Psychol Med 2023; Aneja 2026).",
                listOf(cite(H1, "Press release"), cite(LYRICA, "§9.1"), cite(NEURONTIN, "§9.1"), cite(IJPM, "Discussion"), cite(ANEJA, "Introduction")),
            ),
            ProtocolNote(
                "Punjab figures come from patients already in treatment for substance use disorders at tertiary centres (Aneja 2026) and two case descriptions (Indian J Psychol Med 2023); they are not population prevalence.",
                listOf(cite(ANEJA, "Materials and methods"), cite(IJPM, "Letter")),
            ),
            ProtocolNote(
                "No guideline-level regimen for treating gabapentinoid withdrawal was found; the FDA labels give tapering advice only.",
                listOf(cite(LYRICA, "§5.4; §9.3"), cite(NEURONTIN, "§9.3")),
            ),
        ),
        citations = listOf(
            cite(LYRICA), cite(NEURONTIN), cite(GABA_DSC), cite(EXTRIP), cite(ANEJA), cite(IJPM), cite(H1), cite(DT_ISCCM),
            cite(DT_IPS), cite(DT_AHA25), cite(DT_MHGAP), cite(DT_LAVAGE), cite(DDAP),
        ),
    ),

    // =============================================================================================
    // 3. Benzodiazepines
    // =============================================================================================
    Protocol(
        id = "benzodiazepines",
        title = "Benzodiazepine overdose",
        summary = "Sedative-hypnotic toxidrome, airway and supportive care, naloxone first in mixed overdose, flumazenil indications, " +
            "contraindications and dosing, observation — attributed to AHA, FDA label, IPS, ISCCM and WHO.",
        keywords = listOf(
            "benzodiazepine", "benzo", "alprazolam", "Alprax", "clonazepam", "Rivotril", "lorazepam", "Ativan", "diazepam",
            "Valium", "Calmpose", "nitrazepam", "etizolam", "midazolam", "sleeping pills", "neend ki goli", "sedative",
            "flumazenil", "zolpidem",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise benzodiazepine poisoning",
                points = listOf(
                    "IPS 2023: short-acting — etizolam, alprazolam, lorazepam; long-acting — diazepam, nitrazepam, clonazepam. Children (accidental) and the elderly are most vulnerable.",
                    "IPS 2023: large doses — slurred speech, incoordination, slowed breathing; higher doses — confusion, coma and death. Paradoxical agitation or aggression can occur.",
                    "AHA 2025: isolated benzodiazepine poisoning rarely causes life-threatening hypoventilation or instability; overdoses usually involve opioids, ethanol or other CNS depressants.",
                    "IPS 2023: ask about alcohol, opioids, barbiturates, tricyclics, anticonvulsants, sedating antipsychotics, antihistamines and cannabis taken with it.",
                    "IPS 2023: rule out head injury, encephalitis, hypoglycaemia, hypernatraemia, infection, acute cardiac event and stroke.",
                ),
                checklistTitle = "Sedative-hypnotic toxidrome (ISCCM 2024 Table A1; IPS 2023)",
                checklist = listOf(
                    "CNS depression, confusion, stupor or coma",
                    "Pupils variable",
                    "Vital signs often normal",
                    "Or hypothermia, bradycardia, hypotension, bradypnoea, apnoea",
                    "Hyporeflexia",
                    "Ataxia, slurred speech",
                ),
                citations = listOf(
                    cite(DT_IPS, "Benzodiazepine intoxication in the emergency setting; Table 5; Assessment"),
                    cite(DT_AHA25, "Toxicology: Benzodiazepines — supportive text 1 (S634)"),
                    cite(DT_ISCCM, "Annexure 4 Table A1"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Airway and supportive care",
                timing = "Address respiratory compromise first (IPS 2023)",
                points = listOf(
                    "IPS 2023: treatment is mainly supportive; address respiratory distress first — mechanical ventilation may be required.",
                    "AHA 2023: immediate treatment is an open airway and bag-mask ventilation, then endotracheal intubation when appropriate.",
                    "IPS 2023: prevent aspiration (lateral position, suction); volume expansion for hypotension; correct hypothermia; repeat neurological and respiratory checks.",
                    "IPS 2023: check glucose; ECG — abnormal QRS or QTc suggests a cardiotoxic co-ingestant; chest X-ray if comatose or respiratory compromise (aspiration).",
                ),
                citations = listOf(
                    cite(DT_IPS, "Management of benzodiazepine intoxication — General management; Investigations"),
                    cite(DT_AHA23, "§3 Benzodiazepines — Introduction"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Decontamination and drug screens",
                points = listOf(
                    "IPS 2023: consider activated charcoal via nasogastric tube for heavy intentional ingestion or antidepressant co-ingestion within 1 h; avoid emesis, lavage and bowel irrigation.",
                    "ISCCM 2024: charcoal is contraindicated with depressed consciousness without airway protection.",
                    "IPS 2023: urine toxicology screen for benzodiazepines and other psychoactive substances.",
                    "ISCCM 2024: immunoassays detect diazepam metabolites; lorazepam, clonazepam and others are often undetected. ISCCM (weak): no routine qualitative screening.",
                ),
                citations = listOf(
                    cite(DT_IPS, "Prevention of absorption of benzodiazepines; Investigations"),
                    cite(DT_ISCCM, "Q10 (weak); Annexure 8; Annexure 10"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Mixed overdose: naloxone first",
                points = listOf(
                    "AHA 2025 (2a, adults and children): suspected combined opioid and benzodiazepine poisoning with respiratory depression or arrest — give naloxone before other antidotes.",
                    "AHA 2025: flumazenil only partly restores breathing in combined benzodiazepine–opioid depression; naloxone reverses opioid poisoning, which is commoner and depresses breathing more.",
                    "FDA flumazenil label: its effect on ventilation after a benzodiazepine plus an opioid is inconsistent and not adequately studied.",
                    "IPS 2023 (polysubstance): use naloxone and flumazenil with caution to avoid unmasking effects of substances with opposing actions.",
                ),
                citations = listOf(
                    cite(DT_AHA25, "Toxicology: Benzodiazepines — adult Rec 1 and supportive text (S634); paediatric Rec 2 (S635)"),
                    cite(FLUM, "Warnings — Hypoventilation"),
                    cite(DT_IPS, "Polysubstance use — management"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Flumazenil: when, and when not",
                points = listOf(
                    "AHA 2025 (2a): flumazenil can be effective in select patients with respiratory depression from pure benzodiazepine poisoning without contraindications.",
                    "AHA 2025: harm (3: Harm) in patients at increased risk of seizures or arrhythmias; no role in cardiac arrest (3: No Benefit).",
                    "AHA 2025: risks likely exceed benefit in undifferentiated coma; safe in some low-risk settings such as iatrogenic overdose during procedural sedation.",
                    "ISCCM 2024: not a routine 'coma cocktail'; may help confirmed benzodiazepine ingestion without pro-convulsant co-ingestant or seizure, possibly avoiding intubation.",
                    "IPS 2023: expensive with limited availability in India, so not recommended routinely; avoid with seizure disorder, benzodiazepine dependence or head injury.",
                    "FDA label (boxed warning): seizures, most often after long-term benzodiazepine use or with serious cyclic antidepressant overdose; be prepared to manage seizures.",
                ),
                checklistTitle = "Do not give flumazenil (FDA label; AHA 2025; IPS 2023; ISCCM 2024)",
                checklist = listOf(
                    "Benzodiazepine given for a life-threatening condition, e.g. status epilepticus or raised intracranial pressure (FDA contraindication)",
                    "Signs of serious cyclic antidepressant overdose — wide QRS, arrhythmia, twitching or rigidity, anticholinergic signs (FDA contraindication)",
                    "Hypersensitivity to flumazenil or benzodiazepines (FDA contraindication)",
                    "Chronic benzodiazepine use or dependence",
                    "Seizure disorder, or seizures or myoclonic jerks before flumazenil",
                    "Pro-convulsant co-ingestant, e.g. tricyclic antidepressant or bupropion",
                    "Increased risk of arrhythmia",
                    "Head injury (IPS: avoid; FDA: caution)",
                    "Cardiac arrest",
                    "Undifferentiated coma with unknown co-ingestants",
                ),
                citations = listOf(
                    cite(DT_AHA25, "Toxicology: Benzodiazepines — adult Rec 2–4 and supportive text (S634–S635); paediatric Rec 1, 3–4 (S635)"),
                    cite(DT_ISCCM, "Annexure 5 Antidotal therapy"),
                    cite(DT_IPS, "Antidote administration"),
                    cite(FLUM, "Boxed warning; Contraindications; Warnings — Risk of Seizures; Precautions — Head Injury"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Flumazenil dosing and resedation",
                timing = "Do not rush — secure airway and IV access first (FDA label)",
                points = listOf(
                    "FDA label (adult overdose): 0.2 mg IV over 30 s; if needed 0.3 mg 30 s later; then 0.5 mg over 30 s at 1-min intervals to a cumulative 3 mg.",
                    "FDA label: most respond to 1–3 mg; rarely up to 5 mg. No response 5 min after 5 mg — sedation likely not from benzodiazepines.",
                    "FDA label: resedation — repeat at 20-min intervals; at most 1 mg at a time (given at 0.5 mg/min) and 3 mg in any hour.",
                    "AHA 2025 Table 4: adult 0.2 mg titrated up to 1 mg; paediatric 0.01 mg/kg; 'many contraindications'. IPS 2023: maximum 1–2 mg.",
                    "IPS 2023: arousal 30–60 s after IV dose, peak 5–10 min, lasts 1–2 h; continuous infusion may be needed to prevent resedation.",
                    "FDA label: monitor overdose cases for resedation until stable — flumazenil (1 mg or less) may wear off before many benzodiazepines.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Flumazenil — child ≥1 year (FDA label; AHA 2025 Table 4)",
                        dose = WeightDose(0.01, 0.01, unit = "mg", per = "IV over 15 s; may repeat every 60 s up to 4 more times"),
                        note = "FDA label: each dose up to 0.2 mg; total maximum 0.05 mg/kg or 1 mg, whichever is lower. " +
                            "The FDA paediatric indication is reversal of conscious sedation, not overdose; AHA 2025 Table 4 lists 0.01 mg/kg for poisoning.",
                        maxTotal = 0.2,
                    ),
                ),
                citations = listOf(
                    cite(FLUM, "Dosage and Administration — Management of Suspected Benzodiazepine Overdose; Pediatric Patients; Warnings — Hypoventilation"),
                    cite(DT_AHA25, "Table 4 (S633)"),
                    cite(DT_IPS, "Antidote administration"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Observation, withdrawal and referral",
                points = listOf(
                    "IPS 2023: most effects wear off within a few hours for short-acting and 24–48 h for long-acting benzodiazepines.",
                    "ISCCM 2024 (strong): observe at least 24 h after symptoms resolve; neuropsychiatric assessment before discharge if intentional (moderate).",
                    "FDA flumazenil label: can precipitate benzodiazepine withdrawal; use with caution in alcohol and other drug dependence.",
                    "WHO mhGAP 2016: alcohol or benzodiazepine withdrawal with tremor, sweating or vital-sign changes — diazepam 10–20 mg orally, repeat as needed; transfer if possible.",
                    "IPS 2023: involve addiction psychiatry once conscious — an opportunity for intervention and long-term engagement with treatment.",
                ),
                citations = listOf(
                    cite(DT_IPS, "Management of benzodiazepine intoxication; Polysubstance use"),
                    cite(DT_ISCCM, "Q22 (moderate); Q23 (strong)"),
                    cite(FLUM, "Precautions — Use in Drug and Alcohol Dependent Patients"),
                    cite(DT_MHGAP, "SUB Step 3A — alcohol or benzodiazepine withdrawal (p. 110)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Flumazenil positions differ: FDA label titrates to 3 mg (rarely 5 mg) in overdose; AHA 2025 Table 4 lists up to 1 mg and restricts use to select pure benzodiazepine poisoning; " +
                    "IPS 2023 caps at 1–2 mg and does not recommend routine use in India (cost, availability); ISCCM 2024 advises judicious use only.",
                listOf(
                    cite(FLUM, "Dosage and Administration"),
                    cite(DT_AHA25, "Table 4 (S633); Benzodiazepines Rec 2–4 (S634)"),
                    cite(DT_IPS, "Antidote administration"),
                    cite(DT_ISCCM, "Annexure 5"),
                ),
            ),
            ProtocolNote(
                "Urine screening differs: IPS 2023 advises a urine toxicology screen; ISCCM 2024 recommends against routine qualitative screening (weak) and notes many benzodiazepines are missed.",
                listOf(cite(DT_IPS, "Investigations"), cite(DT_ISCCM, "Q10; Annexure 8")),
            ),
            ProtocolNote(
                "Children: AHA 2025 gives flumazenil 2a (B-R) for select children with pure benzodiazepine poisoning; the FDA label's paediatric indication covers only reversal of conscious sedation.",
                listOf(cite(DT_AHA25, "Benzodiazepines — paediatric Rec 1 (S635)"), cite(FLUM, "Indications and Usage")),
            ),
        ),
        citations = listOf(
            cite(DT_AHA25), cite(FLUM), cite(DT_IPS), cite(DT_ISCCM), cite(DT_AHA23), cite(DT_MHGAP),
        ),
    ),

    // =============================================================================================
    // 4. Cannabis
    // =============================================================================================
    Protocol(
        id = "cannabis",
        title = "Cannabis: intoxication and hyperemesis",
        summary = "Acute intoxication, synthetic cannabinoids, children who eat cannabis and cannabinoid hyperemesis syndrome — " +
            "attributed to IPS, ISCCM, CDC, RCEM, AGA, MoHFW and published trials.",
        keywords = listOf(
            "cannabis", "bhang", "charas", "ganja", "marijuana", "hashish", "hash", "weed", "joint", "THC", "edible",
            "synthetic cannabinoid", "spice", "K2", "cannabinoid hyperemesis syndrome", "CHS", "cyclical vomiting",
            "hot shower vomiting",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise acute intoxication",
                points = listOf(
                    "IPS 2023: cannabis is the most common illicit substance in India; severe intoxication usually presents in first-time users or with a higher-potency product.",
                    "IPS 2023: cognitive and psychomotor effects may appear up to 3 h after consumption, so novices take more; effects may last 12–24 h.",
                    "IPS 2023: bilateral conjunctival injection without itch or pain suggests cannabis; patients may withhold history fearing legal or social consequences.",
                    "IPS 2023: rarely arrhythmia, angina or myocardial infarction; deep inhalation or breath-holding may cause pneumomediastinum or pneumothorax.",
                ),
                checklistTitle = "Features of cannabis intoxication (IPS 2023 Table 3)",
                checklist = listOf(
                    "Tachycardia",
                    "Raised blood pressure, or rarely orthostatic hypotension",
                    "Red eyes (conjunctival injection)",
                    "Dry mouth, increased appetite",
                    "Nystagmus, increased respiratory rate",
                    "Euphoria, dysphoria or anxiety; distorted time perception",
                    "Hallucinations, depersonalisation, paranoid thinking",
                    "Impaired attention, slowed reaction time, poor coordination",
                ),
                citations = listOf(
                    cite(DT_IPS, "Cannabis intoxication in the emergency setting; Table 3; Assessment of patients with cannabis intoxication"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Assessment and differential diagnosis",
                points = listOf(
                    "IPS 2023: urine ELISA detects cannabis for at least 3 days in infrequent users, longer in regular users; false positives with efavirenz, ibuprofen or naproxen.",
                    "ISCCM 2024: the urine marijuana screen detects the THC metabolite; synthetic cannabinoids such as 'spice' and 'K2' are not detected by most immunoassays.",
                    "IPS 2023 differentials: cocaine, LSD, MDMA, amphetamines or synthetic cannabinoids; exacerbation of schizophrenia, acute psychosis or panic disorder.",
                    "IPS 2023: chest pain — evaluate for MI, angina, arrhythmia, pneumothorax, pneumomediastinum or asthma with ECG or X-ray and specialist referral.",
                    "ISCCM 2024 (weak): against routine qualitative toxicology screening — results support, not confirm, the clinical diagnosis.",
                ),
                citations = listOf(
                    cite(DT_IPS, "Assessment of patients with cannabis intoxication; Management of cannabis intoxication"),
                    cite(DT_ISCCM, "Q10 (weak); Annexure 8"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Manage acute intoxication",
                points = listOf(
                    "IPS 2023: a dimly lit space, reassurance and reduced stimulation; in most cases intoxication fades within a few hours.",
                    "IPS 2023: if the patient accepts oral medication — clonazepam 0.5 mg or lorazepam 1 mg.",
                    "IPS 2023: agitation or violence — haloperidol 5 mg with promethazine 25 mg IV or IM, or cautious, limited use of restraint.",
                    "IPS 2023: after recovery, debrief and counsel on harms; refer if cannabis use disorder (harmful use or dependence) is identified.",
                ),
                citations = listOf(
                    cite(DT_IPS, "Management of cannabis intoxication in the emergency setting"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Synthetic cannabinoids",
                points = listOf(
                    "CDC MMWR 2015: sold as 'synthetic marijuana', spice, K2 or black mamba — chemicals sprayed on plant material, smoked or ingested; formulations change often.",
                    "CDC MMWR 2015 (3,572 US poison-centre calls): agitation 35%, tachycardia 29%, drowsiness or lethargy 26%, vomiting 16%; 15 deaths.",
                    "CDC MMWR 2015: major (life-threatening or disabling) effects in 11% of calls with a known outcome.",
                    "ISCCM 2024: synthetic cannabinoids are not detected by most urine enzyme immunoassays.",
                    "IPS 2023: new psychoactive substances may evade urine and blood assays; history is of utmost importance; involve psychiatric and, if needed, critical care services.",
                ),
                citations = listOf(
                    cite(CDC_SC, "Notes from the Field"),
                    cite(DT_ISCCM, "Annexure 8"),
                    cite(DT_IPS, "New psychoactive substances"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Children who eat cannabis",
                timing = "Observe at least 24 h after stabilisation (IPS 2023)",
                points = listOf(
                    "Tweet 2023 (US poison centres, children <6 years): edible exposures rose from 207 in 2017 to 3,054 in 2021; 97.7% occurred at home.",
                    "Tweet 2023: CNS depression in 70% of cases with a known outcome; 22.7% admitted, with ICU admissions increasing.",
                    "IPS 2023: children show adult-type intoxication after much smaller amounts, with higher mortality risk; estimate the dose per kg as accurately as possible.",
                    "IPS 2023: generally avoid emetics, gastric lavage and activated charcoal in children; avoid forced diuresis.",
                    "RCEM 2023: cannabinoid hyperemesis syndrome is described in adolescents; exposure may be through passive smoking.",
                ),
                citations = listOf(
                    cite(TWEET, "Abstract — Results"),
                    cite(DT_IPS, "Children and adolescents; Table 6"),
                    cite(RCEM, "Special populations — Paediatrics"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise cannabinoid hyperemesis syndrome",
                points = listOf(
                    "AGA 2024: CHS is a subtype of cyclic vomiting syndrome, seen mainly in males using cannabis daily or near-daily for years.",
                    "RCEM 2023: consider CHS in cyclical nausea and vomiting with cannabis use; reassure that disclosure is confidential, without stigma.",
                    "RCEM 2023 (Sorensen data): compulsive hot showers or baths with relief 92.3%; abdominal pain 85.1%; at least weekly cannabis use 97.4%.",
                    "RCEM 2023: CHS cannot be confirmed in the ED; exclude CNS, intra-abdominal and infective causes, diabetic gastroparesis and cyclical vomiting syndrome.",
                    "RCEM 2023: check glucose, kidney function, electrolytes and acid–base; weight loss, mass, anaemia or abnormal LFTs suggest another diagnosis.",
                    "RCEM 2023: CHS can be fatal; complications include electrolyte abnormalities, hypoglycaemia and renal failure.",
                ),
                checklistTitle = "AGA 2024 clinical diagnostic criteria for CHS",
                checklist = listOf(
                    "Stereotypical episodic vomiting resembling cyclic vomiting syndrome, 3 or more times a year",
                    "Cannabis use for more than 1 year before onset, on average more than 4 times a week",
                    "Resolution after at least 6 months' abstinence, or at least 3 typical vomiting cycles",
                ),
                citations = listOf(
                    cite(AGA, "Clinical diagnostic criteria (as reproduced by AGA, 29 April 2024)"),
                    cite(RCEM, "Summary of recommendations 1–2; Background; Recognition, Table 1; Investigation"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Treat cannabinoid hyperemesis syndrome",
                timing = "If standard antiemetics fail (RCEM 2023)",
                points = listOf(
                    "RCEM 2023: CHS often resists cyclizine, metoclopramide, ondansetron, prochlorperazine and other antiemetics; opioid analgesia is frequently unsuccessful.",
                    "RCEM 2023: refractory symptoms — haloperidol 0.05 mg/kg IM (max 5 mg), or capsaicin 0.1% cream, 5 g applied to the abdomen.",
                    "RCEM 2023: ECG before haloperidol; do not give if QTc is prolonged or with Parkinson's disease or Lewy body dementia; monitor for acute dystonia.",
                    "HaVOC RCT 2021 (30 patients): IV haloperidol 0.05 or 0.1 mg/kg beat ondansetron 8 mg; two dystonia return visits, both at the higher dose.",
                    "SAEM GRACE 2024: limited direct evidence — mixed for capsaicin, potentially beneficial for dopamine antagonists.",
                    "RCEM 2023: if admitted, offer a hot shower or bath; benzodiazepines, droperidol, tricyclics and olanzapine have extremely limited evidence.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Haloperidol — refractory CHS (RCEM 2023)",
                        dose = WeightDose(0.05, 0.05, unit = "mg", per = "IM, single dose"),
                        note = "RCEM Table 2: maximum 5 mg; ECG first. HaVOC used 0.05 or 0.1 mg/kg IV; dystonia returns occurred only at 0.1 mg/kg.",
                        maxTotal = 5.0,
                    ),
                ),
                citations = listOf(
                    cite(RCEM, "Summary recommendation 3; Symptom management — Acute episode; Refractory symptoms, Table 2"),
                    cite(HAVOC, "Abstract"),
                    cite(GRACE, "Abstract — Results and Conclusions"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Disposition and referral",
                points = listOf(
                    "RCEM 2023: abstinence is the only treatment identified to prevent CHS; give written information and sources of support.",
                    "RCEM 2023: do not discharge with a haloperidol prescription unless part of a specialty management plan.",
                    "RCEM 2023: cessation can be challenging; signposting or referral to drug services may improve success.",
                    "IPS 2023: if cannabis use disorder is identified, refer for substance use treatment.",
                    "MoHFW 2026: Drug Treatment Clinics and DDAP centres (including PGIMER Chandigarh); toll-free helpline 14446 for counselling and referral.",
                ),
                citations = listOf(
                    cite(RCEM, "Summary recommendation 4; Communication; Refractory symptoms; Long-term management"),
                    cite(DT_IPS, "Management of cannabis intoxication in the emergency setting"),
                    cite(DDAP, "Drug De-addiction Programme; helpline"),
                    cite(DDAP_TRAIN, "About Drug De-Addiction Programme (DDAP)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "CHS diagnosis: the AGA criteria need at least 6 months of abstinence, so RCEM 2023 notes CHS is unlikely to be definitively diagnosed in the emergency department.",
                listOf(cite(AGA, "Clinical diagnostic criteria"), cite(RCEM, "Scope; Recognition")),
            ),
            ProtocolNote(
                "Haloperidol route differs: RCEM 2023 suggests IM because there is no UK intravenous licence; the HaVOC trial gave it IV. " +
                    "RCEM notes 0.1% capsaicin cream is unlicensed in the UK; availability in India was not verified.",
                listOf(cite(RCEM, "Table 2 footnotes"), cite(HAVOC, "Abstract — Methods")),
            ),
            ProtocolNote(
                "Evidence is thin: SAEM GRACE 2024 found 7 studies (2 RCTs, 492 patients) and called for rigorous trials. The AGA 2024 full text was not available; its management advice is not summarised here.",
                listOf(cite(GRACE, "Abstract"), cite(AGA, "Not reviewed in full")),
            ),
            ProtocolNote(
                "Setting: the paediatric edible data (Tweet 2023) and synthetic cannabinoid data (CDC 2015) are from US poison centres; IPS 2023 is the Indian source for acute intoxication.",
                listOf(cite(TWEET, "Methods"), cite(CDC_SC, "Notes from the Field"), cite(DT_IPS, "Cannabis intoxication in the emergency setting")),
            ),
        ),
        citations = listOf(
            cite(DT_IPS), cite(DT_ISCCM), cite(RCEM), cite(AGA), cite(HAVOC), cite(GRACE), cite(TWEET), cite(CDC_SC),
            cite(DDAP), cite(DDAP_TRAIN),
        ),
    ),
)
