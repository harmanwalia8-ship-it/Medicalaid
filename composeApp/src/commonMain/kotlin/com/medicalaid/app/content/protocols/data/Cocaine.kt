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
 * Cocaine toxicity pathway. Rules:
 * - Doses, thresholds and classes of recommendation exactly as the cited document states them.
 * - Every point is attributed ("AHA 2023: …", "ACC/AHA 2014: …", "IPS 2023: …", "ISCCM 2024: …").
 * - AHA/ACC locators are section + recommendation numbers (COR, LOE); ISCCM locators are question numbers.
 * - The 2008 AHA scientific statement could not be accessed in full and is cited only in a scope note.
 */

internal val cocaineSources = listOf(
    Source(
        "tx-cocaine-aha-tox-2023", Authority.AHA, "Focused update (Circulation 2023;148:e149–e184)",
        "2023 American Heart Association Focused Update on the Management of Patients With Cardiac Arrest or Life-Threatening Toxicity Due to Poisoning",
        "2023",
        "https://doi.org/10.1161/CIR.0000000000001161",
        status = "Update to the AHA Guidelines for CPR and Emergency Cardiovascular Care",
    ),
    Source(
        "tx-cocaine-acc-aha-nsteacs-2014", Authority.AHA, "Guideline (Circulation 2014;130:e344–e426)",
        "2014 AHA/ACC Guideline for the Management of Patients With Non–ST-Elevation Acute Coronary Syndromes", "2014",
        "https://doi.org/10.1161/CIR.0000000000000134",
        status = "The 2025 ACC/AHA ACS guideline incorporates later evidence but makes no cocaine-specific recommendation",
    ),
    Source(
        "tx-cocaine-aha-acc-chestpain-2021", Authority.AHA, "Guideline (Circulation 2021;144:e368–e454)",
        "2021 AHA/ACC/ASE/CHEST/SAEM/SCCT/SCMR Guideline for the Evaluation and Diagnosis of Chest Pain", "2021",
        "https://doi.org/10.1161/CIR.0000000000001029",
        status = "Co-published in J Am Coll Cardiol 2021;78:e187–e285",
    ),
    Source(
        "tx-cocaine-acc-aha-acs-2025", Authority.AHA, "Guideline (Circulation 2025;151:e771–e862)",
        "2025 ACC/AHA/ACEP/NAEMSP/SCAI Guideline for the Management of Patients With Acute Coronary Syndromes", "2025",
        "https://doi.org/10.1161/CIR.0000000000001309",
    ),
    Source(
        "tx-cocaine-aha-2008", Authority.AHA, "Scientific statement (Circulation 2008;117:1897–1907)",
        "Management of Cocaine-Associated Chest Pain and Myocardial Infarction", "2008",
        "https://doi.org/10.1161/CIRCULATIONAHA.107.188950",
        status = "Full text not reviewed for this protocol",
    ),
    Source(
        "tx-cocaine-ips-2023", Authority.Paper,
        "Indian Psychiatric Society clinical practice guideline (Indian J Psychiatry 2023;65(2):196–211)",
        "Clinical Practice Guidelines for Assessment and Management of Patients with Substance Intoxication Presenting to the Emergency Department",
        "2023",
        "https://doi.org/10.4103/indianjpsychiatry.indianjpsychiatry_490_22",
        status = "Authors from the National Drug Dependence Treatment Centre (NDDTC), AIIMS New Delhi",
    ),
    Source(
        "tx-cocaine-isccm-2024", Authority.Paper,
        "ISCCM position statement (Indian J Crit Care Med 2024;28(Suppl 2):S217–S232)",
        "Indian Society of Critical Care Medicine Position Statement: Approach to a Patient with Poisoning in the Emergency Room and Intensive Care Unit",
        "2024",
        "https://doi.org/10.5005/jp-journals-10071-24697",
    ),
    Source(
        "tx-cocaine-who-mhgap-2016", Authority.WHO, "mhGAP Intervention Guide version 2.0 — SUB module",
        "mhGAP Intervention Guide for mental, neurological and substance use disorders in non-specialized health settings",
        "2016",
        "https://www.who.int/publications/i/item/9789241549790",
    ),
    Source(
        "tx-cocaine-dae-2025", Authority.Paper, "Review, Deutsches Ärzteblatt International (Dtsch Arztebl Int 2025;122(26):715–721)",
        "The Diagnosis and Treatment of Stimulant-Related Emergencies", "2025",
        "https://doi.org/10.3238/arztebl.m2025.0158",
    ),
    Source(
        "tx-cocaine-stuffers-2019", Authority.Paper, "Review with management algorithm (Arh Hig Rada Toksikol 2019;70(2):90–96)",
        "Management recommendations for body stuffers at emergency units", "2019",
        "https://doi.org/10.2478/aiht-2019-70-3199",
    ),
    Source(
        "tx-cocaine-hillingdon-2009", Authority.Paper, "Case series and protocol (Can J Surg 2009;52(5):417–421)",
        "Clinical management of cocaine body packers: the Hillingdon experience", "2009",
        "https://pmc.ncbi.nlm.nih.gov/articles/PMC2769129/",
        status = "Beckley et al.; single UK centre near Heathrow airport, 61 admitted packers",
    ),
    Source(
        "tx-cocaine-bodypacking-imaging-2014", Authority.Paper, "Review (Br J Radiol 2014;87(1036):20130500)",
        "Radiological and practical aspects of body packing", "2014",
        "https://doi.org/10.1259/bjr.20130500",
    ),
    Source(
        "tx-cocaine-rhabdo-meta-2023", Authority.Paper, "Systematic review and meta-analysis (Sci Rep 2023;13:17693)",
        "Incidence of rhabdomyolysis occurrence in psychoactive substances intoxication: a systematic review and meta-analysis",
        "2023",
        "https://doi.org/10.1038/s41598-023-45031-4",
    ),
    Source(
        "tx-cocaine-aki-review-2024", Authority.Paper, "Review (Medicina 2024;60(8):1302)",
        "Poisoning-Induced Acute Kidney Injury: A Review", "2024",
        "https://doi.org/10.3390/medicina60081302",
    ),
    Source(
        "tx-cocaine-psychosis-review-2021", Authority.Paper, "Review (Front Psychiatry 2021;12:694863)",
        "Substance-Induced Psychoses: An Updated Literature Review", "2021",
        "https://doi.org/10.3389/fpsyt.2021.694863",
    ),
    Source(
        "tx-cocaine-formication-2018", Authority.Paper, "Case report (Rev Esp Sanid Penit 2018;20(2):70–72)",
        "Prickling or Formication after the use of cocaine", "2018",
        "https://pmc.ncbi.nlm.nih.gov/articles/PMC6279189/",
        status = "Single case; Juan Juan C, Saiz de la Hoya Zamácola P",
    ),
    Source(
        "tx-cocaine-cdc-mmwr-2009", Authority.CDC, "MMWR 2009;58(49):1381–1385",
        "Agranulocytosis Associated with Cocaine Use — Four States, March 2008–November 2009", "2009",
        "https://www.cdc.gov/mmwr/preview/mmwrhtml/mm5849a3.htm",
    ),
    Source(
        "tx-cocaine-levamisole-anca-2011", Authority.Paper, "Case series (Clin J Am Soc Nephrol 2011;6(12):2799–2805)",
        "Contaminated Cocaine and Antineutrophil Cytoplasmic Antibody-Associated Disease", "2011",
        "https://doi.org/10.2215/CJN.03440411",
    ),
    Source(
        "tx-cocaine-acmt-2023", Authority.Paper,
        "American College of Medical Toxicology position statement (J Med Toxicol 2023;19(3):310–312)",
        "ACMT Position Statement: End the Use of the Term \"Excited Delirium\"", "2023",
        "https://doi.org/10.1007/s13181-023-00944-4",
    ),
)

private const val AHA23 = "tx-cocaine-aha-tox-2023"
private const val NSTE14 = "tx-cocaine-acc-aha-nsteacs-2014"
private const val CP21 = "tx-cocaine-aha-acc-chestpain-2021"
private const val ACS25 = "tx-cocaine-acc-aha-acs-2025"
private const val AHA08 = "tx-cocaine-aha-2008"
private const val IPS = "tx-cocaine-ips-2023"
private const val ISCCM = "tx-cocaine-isccm-2024"
private const val MHGAP = "tx-cocaine-who-mhgap-2016"
private const val DAE = "tx-cocaine-dae-2025"
private const val STUFF = "tx-cocaine-stuffers-2019"
private const val HILL = "tx-cocaine-hillingdon-2009"
private const val PINTO = "tx-cocaine-bodypacking-imaging-2014"
private const val RHABDO = "tx-cocaine-rhabdo-meta-2023"
private const val AKI = "tx-cocaine-aki-review-2024"
private const val PSY = "tx-cocaine-psychosis-review-2021"
private const val FORM = "tx-cocaine-formication-2018"
private const val CDC = "tx-cocaine-cdc-mmwr-2009"
private const val ANCA = "tx-cocaine-levamisole-anca-2011"
private const val ACMT = "tx-cocaine-acmt-2023"

internal val cocaineProtocols = listOf(
    Protocol(
        id = "cocaine-toxicity",
        title = "Cocaine toxicity",
        summary = "Sympathomimetic toxidrome, agitation and hyperthermia, chest pain, wide-QRS dysrhythmias, rhabdomyolysis, " +
            "body packing, formication and psychosis — attributed to AHA, ACC/AHA, IPS, ISCCM, WHO and published reviews.",
        keywords = listOf(
            "cocaine", "coke", "crack", "snow", "stimulant", "sympathomimetic", "body packer", "body stuffer",
            "cocaine bugs", "formication", "excited delirium", "levamisole", "hyperactive delirium",
            "cocaine chest pain", "stimulant psychosis", "drug mule",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise cocaine toxicity",
                timing = "At first contact",
                points = listOf(
                    "AHA 2023: cocaine causes a sympathomimetic toxidrome — tachycardia, hypertension, hyperthermia, diaphoresis, psychomotor agitation, seizures — plus sodium-channel (local anaesthetic) cardiac effects.",
                    "IPS 2023 (AIIMS NDDTC authors): cocaine use, traditionally uncommon in India, is rising in bigger cities; expect euphoria, severe agitation, paranoia, hallucinations and skin picking.",
                    "ISCCM 2024: urine immunoassay detects benzoylecgonine (PPV 100%); passive inhalation, unless prolonged and heavy, is not detected. AHA/ACC 2021: positive 1–4 h after use, for 2–4 days.",
                    "IPS 2023: suspect polysubstance use — stimulant paranoia may be accentuated by cannabis. Dtsch Arztebl 2025: ECG, blood gas, ethanol, possibly paracetamol and salicylate, pregnancy test.",
                    "WHO mhGAP 2016: chest pain, tachyarrhythmia or other neurological signs after stimulant use — transfer to hospital.",
                ),
                checklistTitle = "Sympathomimetic toxidrome (ISCCM 2024, IPS 2023, AHA 2023)",
                checklist = listOf(
                    "Tachycardia, hypertension, widened pulse pressure",
                    "Hyperthermia, diaphoresis",
                    "Mydriasis",
                    "Agitation, hypervigilance, paranoia, hallucinations",
                    "Tremor, hyperreflexia, seizures",
                    "Chest pain, arrhythmia",
                    "Skin picking (formication)",
                    "Wide QRS or prolonged QT on ECG",
                ),
                citations = listOf(
                    cite(AHA23, "§6 Cocaine — Introduction (e160)"),
                    cite(IPS, "Table 1; Challenges (polysubstance use); Cocaine and other stimulants"),
                    cite(ISCCM, "Annexure 4 Table A1 (toxidromes); Annexure 8 (urine drug screen)"),
                    cite(CP21, "§4.1.6 Recommendation-specific supportive text"),
                    cite(DAE, "Table 2, step 6"),
                    cite(MHGAP, "SUB Emergency presentations, Step 2B (p. 111)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Agitation, seizures and hyperthermia",
                timing = "Immediately — hyperthermia can be rapidly life-threatening (AHA 2023)",
                points = listOf(
                    "AHA 2023: benzodiazepines remain the mainstay of initial management of blood pressure and psychomotor agitation in acute cocaine poisoning.",
                    "IPS 2023: quiet room; lorazepam 2 mg PO, IM or IV, repeated as necessary. WHO mhGAP 2016: diazepam 5–10 mg PO, IV or PR, titrated until calm and lightly sedated.",
                    "Dtsch Arztebl 2025: diazepam 5–10 mg, lorazepam 1–2.5 mg or midazolam 5–10 mg, repeated every 10 min (IV/IN) or 45 min (PO/SL/IM); high doses are typically required.",
                    "ISCCM 2024: benzodiazepines are the first antiseizure drug in poisoning; recommends against phenytoin as the first drug.",
                    "AHA 2023 (COR 1): rapid external cooling for life-threatening hyperthermia — evaporative or immersion cooling works faster than cooling blankets, cold packs or endovascular devices.",
                    "Dtsch Arztebl 2025: restraint only if unavoidable, with about 5 staff; risks asphyxia, stress cardiomyopathy, arrhythmia. AHA 2023 (sympathomimetics): prolonged restraint without sedation is potentially harmful (3: Harm).",
                ),
                citations = listOf(
                    cite(AHA23, "§6 Introduction; §6 Rec 1 (COR 1, LOE C-LD) and supportive text; §14 Introduction and Rec 5 (3: Harm, C-LD)"),
                    cite(IPS, "Cocaine and other stimulants"),
                    cite(MHGAP, "SUB Emergency presentations, Step 2B and Step 3C (pp. 111–112)"),
                    cite(DAE, "Table 2, steps 4–5"),
                    cite(ISCCM, "Q19"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Chest pain and acute coronary syndrome",
                points = listOf(
                    "AHA/ACC 2021 (2a, B-NR): consider cocaine or methamphetamine use as a cause of acute chest pain; ACS occurs in <10% in most studies and death is rare.",
                    "IPS 2023: ECG or troponin T to look for ischaemic changes; aspirin and nitroglycerine are given for cocaine-related chest pain.",
                    "ACC/AHA 2014 (Class I): NSTE-ACS after recent cocaine use — treat as other NSTE-ACS, except beta-blockers during acute intoxication unless on coronary vasodilator therapy.",
                    "ACC/AHA 2014 (IIa): benzodiazepines alone or with nitroglycerin for hypertension and tachycardia in acute intoxication. AHA 2023 (2a): nitrates, phentolamine or calcium-channel blockers for vasospasm.",
                    "ACC/AHA 2014 (III: Harm): no beta-blockers in ACS with signs of acute cocaine intoxication (euphoria, tachycardia, hypertension) — risk of coronary spasm. See note.",
                    "AHA/ACC 2021: cocaine may provoke aortic dissection even without other risk factors. The 2025 ACC/AHA ACS guideline makes no cocaine-specific recommendation.",
                ),
                citations = listOf(
                    cite(CP21, "§4.1.6 Rec 1 (2a, B-NR) and supportive text; §4.2.1"),
                    cite(IPS, "Cocaine and other stimulants"),
                    cite(NSTE14, "§7.10 Class I, Class IIa, Class III: Harm (all LOE C)"),
                    cite(AHA23, "§6 Rec 4 (2a, C-LD)"),
                    cite(ACS25, "Full text — no cocaine-specific recommendation"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Wide-QRS dysrhythmias and hypertensive emergency",
                timing = "Life-threatening dysrhythmia: standard ALS plus sodium bicarbonate (AHA 2023)",
                points = listOf(
                    "AHA 2023: cocaine blocks cardiac sodium channels (QRS widening, wide-complex tachycardia, as with class Ia/Ic drugs) and potassium channels (QT prolongation).",
                    "AHA 2023 (2a, C-LD): sodium bicarbonate for wide-complex tachycardia or cardiac arrest from cocaine — adults 50–150 mEq IV; watch sodium, pH, potassium and chloride.",
                    "AHA 2023 (2a, C-LD): lidocaine for wide-complex tachycardia from cocaine; its safety has been shown in cocaine-associated myocardial infarction.",
                    "AHA 2023 (2a): vasodilators (nitrates, phentolamine, calcium-channel blockers) for hypertensive emergency. Arh Hig Rada Toksikol 2019: phentolamine 1–5 mg if benzodiazepines fail.",
                    "ISCCM 2024: benzodiazepine for hypertension in an agitated poisoned patient; labetalol for poisoning-related hypertension. Dtsch Arztebl 2025: avoid beta- and combined alpha/beta-blockers — see note.",
                    "Dtsch Arztebl 2025: cocaine use roughly doubles the odds of haemorrhagic (OR 2.33) and ischaemic stroke (OR 2.03) — see the Acute stroke protocol.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Sodium bicarbonate — bolus, paediatric (AHA 2023)",
                        dose = WeightDose(1.0, 3.0, unit = "mEq", per = "IV bolus"),
                        note = "AHA 2023 Table 2: adults 50–150 mEq. Paediatric dose should not exceed the adult dose (capped at 150 mEq). " +
                            "Children usually 0.5 mEq/mL, adults 1 mEq/mL — both hypertonic.",
                        maxTotal = 150.0,
                    ),
                    StepDose(
                        label = "Sodium bicarbonate — maintenance infusion (AHA 2023)",
                        dose = WeightDose(1.0, 3.0, unit = "mL", per = "per hour of a 150 mEq/L solution"),
                        note = "AHA 2023 Table 2. Watch for hypernatraemia, alkalaemia, hypokalaemia and hypochloraemia.",
                    ),
                ),
                citations = listOf(
                    cite(AHA23, "§6 Introduction; §6 Rec 2–4 (2a, C-LD) and supportive text; Table 2 (e155); Top 10 take-home message 4 (e150)"),
                    cite(STUFF, "Standard treatment"),
                    cite(ISCCM, "Q20"),
                    cite(DAE, "Table 2, step 5; Primary management"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Rhabdomyolysis and acute kidney injury",
                points = listOf(
                    "Sci Rep 2023 meta-analysis: pooled incidence of rhabdomyolysis 26.6% (95% CI 11.1–42.1) in hospitalised cocaine intoxication.",
                    "AHA 2023 (sympathomimetics): sedation controls the psychomotor agitation that produces heat and rhabdomyolysis. Dtsch Arztebl 2025: fluid replacement with balanced electrolyte solutions.",
                    "Dtsch Arztebl 2025: stimulants can cause rhabdomyolysis and renal failure; creatine kinase is an optional test in the work-up.",
                    "Medicina 2024 review: cocaine AKI arises from rhabdomyolysis, renal infarction, thrombotic microangiopathy, vasculitis, interstitial nephritis and malignant hypertension.",
                    "Medicina 2024: correct hypovolaemia with isotonic crystalloids, avoiding overload; emergent RRT for K⁺ >6.5 mmol/L, pH <7.1, uraemic symptoms or fluid overload.",
                    "IPS 2023: IV fluids for dehydration in cocaine or stimulant intoxication.",
                ),
                citations = listOf(
                    cite(RHABDO, "Results; Table 3"),
                    cite(AHA23, "§14 Rec 1 supportive text"),
                    cite(DAE, "Table 1; Table 2, steps 5–6"),
                    cite(AKI, "§6 (cocaine); §7 Management"),
                    cite(IPS, "Cocaine and other stimulants"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Body packers and body stuffers",
                timing = "Surgery if cocaine toxicity persists or the bowel obstructs (Beckley 2009)",
                points = listOf(
                    "ISCCM 2024: plain abdominal radiograph for ingested drug packets. Br J Radiol 2014: radiograph sensitivity only 40–90%; a negative film does not exclude packing — non-contrast abdominopelvic CT.",
                    "Arh Hig Rada Toksikol 2019: non-contrast abdominopelvic CT in every suspected stuffer; observe until packages pass, confirmed by repeat negative CT; 6-h observation has been criticised.",
                    "ISCCM 2024 (weak): WBI not routine but may help with drug packets — adults 1500–2000 mL/h until effluent clear. Beckley 2009: PEG 1.5 L/h oral or NG until all packets pass.",
                    "Beckley 2009: liquid paraffin contraindicated (degrades latex packets); no WBI with obstruction, perforation, GI bleeding, haemodynamic instability or unprotected airway.",
                    "Arh Hig Rada Toksikol 2019: WBI may be contraindicated in unstable or asymptomatic stuffers (poorly wrapped packets); single-dose activated charcoal if leakage.",
                    "Surgery — Beckley 2009: persisting cocaine toxicity or bowel obstruction. Arh Hig Rada Toksikol 2019: obstruction, rupture, or toxicity unresponsive to drug treatment.",
                ),
                checklistTitle = "Packer discharge criteria — all required (Beckley 2009)",
                checklist = listOf(
                    "Asymptomatic",
                    "Reported number of packets passed",
                    "At least 2 packet-free stools",
                    "Negative plain abdominal radiograph (contrast films if equivocal)",
                    "Advice obtained from the regional poisons unit",
                ),
                citations = listOf(
                    cite(ISCCM, "Q16 (weak, LOE C); Q18; Annexure 13"),
                    cite(PINTO, "Imaging; Conclusion"),
                    cite(STUFF, "Abstract; Observation; Diagnostic modalities; Standard treatment"),
                    cite(HILL, "Discussion; Box 1 (Hillingdon protocol)"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Formication ('cocaine bugs') and cocaine-induced psychosis",
                points = listOf(
                    "IPS 2023: cocaine intoxication can cause auditory hallucinations, paranoid ideation and picking of the skin (formication).",
                    "Case report 2018: sensation of insects crawling under the skin; scratching caused ulcerated, scabbed and scarred arm lesions. New lesions stopped with abstinence and low-dose neuroleptic.",
                    "Front Psychiatry 2021 review: psychotic features in 29–86.5% of cocaine users (one series: paranoid delusions 90%, hallucinations 96%); symptoms may vanish with withdrawal.",
                    "WHO mhGAP 2016: if psychotic symptoms do not respond to diazepam, consider haloperidol 1–2.5 mg PO or IM; if they persist, manage as psychosis.",
                    "IPS 2023: short-term injectable antipsychotic for acute agitation and paranoia; not needed without a psychotic disorder. Dtsch Arztebl 2025: olanzapine or haloperidol 5–10 mg added to benzodiazepines.",
                    "Dtsch Arztebl 2025: stimulant psychosis can appear days after stopping — inpatient observation may be needed. WHO mhGAP 2016: watch for suicidal thoughts after intoxication.",
                ),
                citations = listOf(
                    cite(IPS, "Cocaine and other stimulants"),
                    cite(FORM, "Case description and discussion"),
                    cite(PSY, "Cocaine section"),
                    cite(MHGAP, "SUB Emergency presentations, Step 2B and Step 3C (pp. 111–112)"),
                    cite(DAE, "Table 2, steps 5 and 8"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Disposition and referral",
                points = listOf(
                    "ISCCM 2024 (strong): observe every poisoning for at least 24 h after symptoms resolve; neuropsychiatric assessment before discharge if poisoning may be intentional.",
                    "IPS 2023: cocaine intoxication usually resolves over hours to within a day; afterwards refer for treatment of cocaine or stimulant use disorder.",
                    "Dtsch Arztebl 2025: mild agitation with improved vital signs — discharge to outpatient aftercare; assess danger to self and others; inform the patient they are unfit to drive.",
                    "Dtsch Arztebl 2025 (Germany): dependence care is weeks of withdrawal treatment then months of rehabilitation; no approved drug; CBT, contingency management and exercise support abstinence.",
                    "Dependence treatment is covered on the app's 'Cocaine (stimulant) use disorder' page.",
                ),
                citations = listOf(
                    cite(ISCCM, "Q22 (moderate); Q23 (strong)"),
                    cite(IPS, "Cocaine and other stimulants"),
                    cite(DAE, "Summary; Table 2, step 8"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Beta-blockers are contested. ACC/AHA 2014: not in ACS with signs of acute cocaine intoxication (III: Harm). AHA 2023: safety is 'controversial, with studies showing both benefit and harm'. " +
                    "Dtsch Arztebl 2025 and Arh Hig Rada Toksikol 2019: avoid (Dtsch Arztebl also combined alpha/beta-blockers). ISCCM 2024: labetalol for poisoning-related hypertension generally.",
                listOf(
                    cite(NSTE14, "§7.10 Class III: Harm"),
                    cite(AHA23, "§6 Rec 4 supportive text"),
                    cite(DAE, "Table 2, step 5"),
                    cite(STUFF, "Standard treatment"),
                    cite(ISCCM, "Q20"),
                ),
            ),
            ProtocolNote(
                "Levamisole adulteration. CDC MMWR 2009: agranulocytosis in cocaine users linked to levamisole, found in 69% of cocaine lots seized entering the US (2009); consider it in unexplained agranulocytosis. " +
                    "McGrath 2011: ANCA (all anti-MPO) disease with arthralgia, skin lesions, AKI or pulmonary haemorrhage. Both data sets are North American.",
                listOf(cite(CDC, "Report and editorial note"), cite(ANCA, "Abstract (Results)")),
            ),
            ProtocolNote(
                "Benzodiazepine regimens differ: IPS 2023 lorazepam 2 mg repeated; WHO mhGAP 2016 diazepam 5–10 mg titrated (its medication table gives 10–20 mg every 2 h); " +
                    "Dtsch Arztebl 2025 repeats every 10–45 min; Arh Hig Rada Toksikol 2019 diazepam 5–10 mg IV every 3–5 min until agitation and hypertension resolve.",
                listOf(
                    cite(IPS, "Cocaine and other stimulants"),
                    cite(MHGAP, "SUB Emergency presentations (p. 111); SUB 2 Table 1 (p. 126)"),
                    cite(DAE, "Table 2, step 5"),
                    cite(STUFF, "Standard treatment"),
                ),
            ),
            ProtocolNote(
                "Observation differs: ISCCM 2024 — at least 24 h after symptoms resolve (all poisonings); IPS 2023 — cocaine intoxication resolves within hours to a day; " +
                    "Dtsch Arztebl 2025 — discharge mild cases once vital signs improve. Body stuffers: Arh Hig Rada Toksikol 2019 criticises 6-h observation.",
                listOf(cite(ISCCM, "Q23"), cite(IPS, "Cocaine and other stimulants"), cite(DAE, "Table 2, step 8"), cite(STUFF, "Observation")),
            ),
            ProtocolNote(
                "Whole bowel irrigation differs: Beckley 2009 calls PEG irrigation the current treatment of choice for packers; Arh Hig Rada Toksikol 2019 says it may be contraindicated in unstable or asymptomatic stuffers.",
                listOf(cite(HILL, "Discussion"), cite(STUFF, "Standard treatment")),
            ),
            ProtocolNote(
                "Terminology: ACMT 2023 asks clinicians to stop using 'excited delirium' and prefers 'hyperactive delirium with agitation'; it links unexpected deaths to restraint or prone positioning and advises minimising restraint.",
                listOf(cite(ACMT, "Position statement")),
            ),
            ProtocolNote(
                "Scope: the 2008 AHA scientific statement on cocaine-associated chest pain and myocardial infarction was not reviewed here; chest-pain content comes from ACC/AHA 2014, AHA/ACC 2021 and AHA 2023. " +
                    "AHA 2023 §14 (sympathomimetics) covers amphetamines and cathinones; cocaine is handled in its §6.",
                listOf(cite(AHA08, "Not reviewed"), cite(AHA23, "§6; §14 Introduction")),
            ),
        ),
        citations = listOf(
            cite(AHA23), cite(NSTE14), cite(CP21), cite(IPS), cite(ISCCM), cite(MHGAP), cite(DAE), cite(STUFF), cite(HILL),
        ),
    ),
)
