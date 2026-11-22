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
 * Cardiac emergencies: STEMI, NSTE-ACS, acute heart failure, hypertensive emergency, acute aortic dissection.
 * Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them (checked against the full
 *   texts, tables and figures, November 2026).
 * - Every point is attributed ("ICMR STW: …", "ESC 2023: …", "ACC/AHA 2025: …", "CSI 2017: …", "NICE: …").
 * - ESC locators are section, Recommendation Table, Table or Figure numbers; ACC/AHA locators are section +
 *   recommendation number or table; NICE locators are recommendation numbers; ICMR STW locators are box headings.
 * - Reused sources (defined elsewhere, cited by id): ACC/AHA 2025 ACS (Cocaine.kt), AHA/ACC chest pain 2021 and
 *   NICE NG185 (scores), ESC 2026 HF and ICMR HF STW (HeartConditions.kt), ESC 2024 hypertension, ICMR hypertension
 *   STW and AHA/ACC 2025 high blood pressure (CardioRenalConditions.kt), AHA/ASA 2026 stroke and 2022 ICH (Stroke.kt).
 * - The ACC/AHA 2025 ACS guideline was read in its JACC article-in-press version (same section and table numbers).
 */

internal val cardiacEmergencySources: List<Source> = listOf(
    // ------------------------------------------------------------------ India
    Source(
        "em-card-icmr-stw-stemi-2026", Authority.ICMR, "Standard Treatment Workflow",
        "ST Elevation Myocardial Infarction (STEMI) (ICD-11 BA41.0)", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1768823342_cardiology_1-5.pdf",
        status = "January 2026 edition; references ESC 2023 and ACC/AHA 2025 ACS guidelines",
    ),
    Source(
        "em-card-icmr-stw-nstemi-2026", Authority.ICMR, "Standard Treatment Workflow",
        "Unstable Angina/NSTEMI (ICD-11 BA40.0)", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1768823342_cardiology_1-4.pdf",
        status = "January 2026 edition",
    ),
    Source(
        "em-card-icmr-stw-aas-2024", Authority.ICMR, "Standard Treatment Workflow",
        "Acute Aortic Syndrome", "2024",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726643579_1_acute_aortic_syndrome.pdf",
        status = "July 2024 edition; references the 2022 ACC/AHA aortic disease guideline",
    ),
    Source(
        "em-card-csi-stemi-2017", Authority.CSI, "CSI position statement — Indian Heart J 2017;69(Suppl 1):S63–S97",
        "Cardiological Society of India: Position statement for the management of ST elevation myocardial infarction in India",
        "2017",
        "https://doi.org/10.1016/j.ihj.2017.03.006",
        status = "No later CSI STEMI statement found (checked November 2026); predates ESC 2023 and ACC/AHA 2025",
    ),
    Source(
        "em-card-stemi-india-2015", Authority.CSI,
        "Consensus document (STEMI INDIA, CSI and API) — Indian Heart J 2015;67(5):497–502",
        "Framework for a National STEMI Program: consensus document developed by STEMI INDIA, Cardiological Society of India and Association Physicians of India",
        "2015",
        "https://doi.org/10.1016/j.ihj.2015.05.017",
    ),
    // ------------------------------------------------------------------ International
    Source(
        "em-card-esc-acs-2023", Authority.ESC, "ESC guideline — Eur Heart J 2023;44(38):3720–3826",
        "2023 ESC Guidelines for the management of acute coronary syndromes", "2023",
        "https://doi.org/10.1093/eurheartj/ehad191",
        status = "Fibrinolytic doses and contraindications are in the online supplement, which was not reviewed",
    ),
    Source(
        "em-card-esc-council-he-2019", Authority.ESC,
        "ESC Council on Hypertension position document — Eur Heart J Cardiovasc Pharmacother 2019;5(1):37–46",
        "ESC Council on hypertension position document on the management of hypertensive emergencies", "2019",
        "https://doi.org/10.1093/ehjcvp/pvy032",
        status = "The 2024 ESC hypertension guideline refers to it for detailed hypertensive-emergency management",
    ),
    Source(
        "em-card-ish-2020", Authority.Paper, "ISH guideline — Hypertension 2020;75(6):1334–1357",
        "2020 International Society of Hypertension Global Hypertension Practice Guidelines", "2020",
        "https://doi.org/10.1161/HYPERTENSIONAHA.120.15026",
    ),
    Source(
        "em-card-acc-aha-aortic-2022", Authority.AHA, "ACC/AHA guideline — Circulation 2022;146(24):e334–e482",
        "2022 ACC/AHA Guideline for the Diagnosis and Management of Aortic Disease", "2022",
        "https://doi.org/10.1161/CIR.0000000000001106",
    ),
    Source(
        "em-card-esc-paad-2024", Authority.ESC, "ESC guideline — Eur Heart J 2024;45(36):3538–3700",
        "2024 ESC Guidelines for the management of peripheral arterial and aortic diseases", "2024",
        "https://doi.org/10.1093/eurheartj/ehae179",
    ),
)

// India
private const val ICMR_STEMI = "em-card-icmr-stw-stemi-2026"
private const val ICMR_NSTE = "em-card-icmr-stw-nstemi-2026"
private const val ICMR_AAS = "em-card-icmr-stw-aas-2024"
private const val ICMR_HF = "hc-icmr-stw-hf-2026"
private const val ICMR_HTN = "gc-icmr-stw-htn-2026"
private const val CSI = "em-card-csi-stemi-2017"
private const val STEMI_INDIA = "em-card-stemi-india-2015"

// International
private const val ESC_ACS = "em-card-esc-acs-2023"
private const val ACS25 = "tx-cocaine-acc-aha-acs-2025"
private const val CP21 = "sc-aha-chestpain-2021"
private const val NICE_ACS = "sc-nice-ng185"
private const val ESC_HF = "hc-esc-hf-2026"
private const val ESC_HTN = "gc-esc-htn-2024"
private const val AHA_HBP = "gc-aha-acc-hbp-2025"
private const val ESC_HE = "em-card-esc-council-he-2019"
private const val ISH = "em-card-ish-2020"
private const val AHA_AIS = "st-aha-ais-2026"
private const val AHA_ICH = "st-aha-ich-2022"
private const val AHA_AORTA = "em-card-acc-aha-aortic-2022"
private const val ESC_PAAD = "em-card-esc-paad-2024"

internal val cardiacEmergencyProtocols: List<Protocol> = listOf(
    // =============================================================================================== STEMI
    Protocol(
        id = "stemi",
        category = ProtocolCategory.Cardiac,
        title = "Acute STEMI",
        summary = "ECG criteria, time goals, antiplatelet and anticoagulant loading, primary PCI or fibrinolysis, " +
            "fibrinolytic doses and contraindications, pharmaco-invasive and rescue PCI — ICMR, CSI, ESC, ACC/AHA, NICE.",
        keywords = listOf(
            "MI", "AMI", "heart attack", "STEMI", "ST elevation", "ST elevation MI", "acute MI", "IWMI", "AWMI",
            "inferior wall MI", "anterior wall MI", "posterior MI", "RV infarct", "LBBB", "chest pain", "thrombolysis",
            "fibrinolysis", "streptokinase", "STK", "tenecteplase", "TNK", "reteplase", "alteplase", "tPA",
            "primary PCI", "PPCI", "angioplasty", "pharmaco-invasive", "rescue PCI", "door to balloon", "door to needle",
            "aspirin", "clopidogrel", "ticagrelor", "prasugrel", "heparin", "enoxaparin",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise STEMI on the ECG",
                timing = "12-lead ECG within 10 min of first medical contact",
                points = listOf(
                    "ESC 2023 and ACC/AHA 2025: record and interpret a 12-lead ECG within 10 min of first medical contact (FMC). ICMR STW: diagnosis ideally within 10 min of FMC.",
                    "ESC 2023: in inferior STEMI record V3R–V4R; record V7–V9 if symptoms continue and the ECG is inconclusive. ST depression in V1–V3 suggests posterior occlusion.",
                    "ESC 2023: LBBB, RBBB or paced rhythm with high suspicion of ongoing ischaemia — manage as STEMI, whether or not the block was known before.",
                    "ACC/AHA 2025: new or presumed new LBBB alone is not diagnostic of MI, nor a STEMI equivalent if asymptomatic. CSI 2017 lists new LBBB as STEMI.",
                    "ESC 2023: ST depression ≥1 mm in ≥6 leads with ST elevation in aVR and/or V1 suggests left main or multivessel ischaemia.",
                    "ESC 2023 and ACC/AHA 2025: do not wait for troponin results before reperfusion.",
                ),
                checklistTitle = "New ST elevation at the J-point in ≥2 contiguous leads (ESC 2023; ACC/AHA 2025)",
                checklist = listOf(
                    "V2–V3: ≥2.5 mm in men under 40",
                    "V2–V3: ≥2 mm in men 40 and over",
                    "V2–V3: ≥1.5 mm in women of any age",
                    "All other leads: ≥1 mm (ESC: without LV hypertrophy or LBBB)",
                    "Posterior: ST elevation in V7–V9 (CSI 2017: >1 mm)",
                    "Right ventricle: ST elevation in V3R–V4R (ESC 2023)",
                ),
                citations = listOf(
                    cite(ESC_ACS, "§3.2.1; Rec Table 1 (ECG)"),
                    cite(ACS25, "Table 3; §3.1.1 Rec 1; §3.1.2 Rec 1, 3 and supportive text"),
                    cite(ICMR_STEMI, "STEMI diagnosis (flowchart footnote)"),
                    cite(CSI, "Tables 3 and 5"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "System time goals",
                timing = "Primary PCI only if achievable within 120 min; otherwise fibrinolysis",
                points = listOf(
                    "ESC 2023, from STEMI diagnosis: wire crossing <60 min at a PCI centre, <90 min if transferred; lytic bolus <10 min. Door-in to door-out ≤30 min.",
                    "ESC 2023: primary PCI is preferred over fibrinolysis if the anticipated diagnosis-to-PCI time is <120 min.",
                    "ACC/AHA 2025: FMC-to-device ≤90 min, or ≤120 min with transfer. FMC = first assessment by someone able to obtain and interpret an ECG.",
                    "ICMR STW: primary-PCI centre — PCI preferably <60 min (flowchart; text box: within 90 min). Elsewhere, transfer if PCI possible <120 min, preferably <90 min.",
                    "ICMR STW: if not, fibrinolyse — text: start preferably within 10 min; flowchart: preferably within 30 min. CSI 2017 (rural pharmaco-invasive): FMC-to-drug ≤30 min.",
                    "NICE NG185: primary PCI if presenting within 12 h and deliverable within 120 min of the time fibrinolysis could have been given.",
                ),
                citations = listOf(
                    cite(ESC_ACS, "Fig. 7; §4.1.2; §5.2.1; Rec Table 4"),
                    cite(ACS25, "§3.1.1 Rec 3 and footnote; §5.2.1 Rec 1; §5.3 Rec 1–2"),
                    cite(ICMR_STEMI, "PCI capable hospital; PCI incapable centre; STEMI diagnosis flowchart"),
                    cite(CSI, "The rural and urban disparity"),
                    cite(NICE_ACS, "Rec 1.1.6, 1.1.19"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Immediate care",
                timing = "On arrival",
                points = listOf(
                    "ICMR STW: ICU bed with continuous ECG monitoring and defibrillator; serial troponin; echocardiography, especially for mechanical complications.",
                    "Oxygen only if SpO₂ <90% (ESC 2023; ACC/AHA 2025; ICMR STW). ACC/AHA 2025: routine oxygen at SpO₂ ≥90% gives no benefit.",
                    "Morphine: ICMR STW 2–4 mg IV every 15 min; ACC/AHA 2025 2–4 mg, repeat every 5–15 min, up to 10 mg; ESC 2023 e.g. 5–10 mg IV.",
                    "ACC/AHA 2025: sublingual nitroglycerin 0.3–0.4 mg every 5 min, up to 3 doses, if SBP ≥90 mm Hg; IV from 10 mcg/min for persistent pain.",
                    "ESC 2023: IV metoprolol should be considered before primary PCI if no acute heart failure and SBP >120 mm Hg. CSI 2017: no routine IV beta-blockers.",
                ),
                checklistTitle = "Avoid",
                checklist = listOf(
                    "Nitrates if SBP <90 mm Hg or >30 mm Hg below baseline (ACC/AHA 2025)",
                    "Nitrates in RV infarction, severe aortic stenosis, marked bradycardia or tachycardia (ESC 2023)",
                    "Nitrates within 12 h of avanafil, 24 h of sildenafil/vardenafil, 48 h of tadalafil (ACC/AHA 2025)",
                    "Morphine in inferior MI with suspected RV infarction — profound hypotension (CSI 2017)",
                    "Non-aspirin NSAIDs (ACC/AHA 2025; CSI 2017)",
                    "Routine oxygen when SpO₂ is ≥90% (ACC/AHA 2025)",
                ),
                citations = listOf(
                    cite(ICMR_STEMI, "General measures; Drugs & dosage"),
                    cite(ESC_ACS, "§4.2.2.1–4.2.2.4; Rec Table 3"),
                    cite(ACS25, "§4.1 Rec 1–2; §4.2; Table 6"),
                    cite(CSI, "Oxygen; Analgesia; Nitrates; Beta blockers"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Antiplatelet loading",
                timing = "As soon as STEMI is diagnosed",
                points = listOf(
                    "Aspirin loading: ICMR STW 325 mg; ESC 2023 150–300 mg oral (75–250 mg IV); ACC/AHA 2025 162–325 mg chewed, non-enteric-coated; NICE 300 mg.",
                    "ICMR STW general measures: aspirin 325 mg, clopidogrel 300 mg and atorvastatin 80 mg (or rosuvastatin 20–40 mg).",
                    "Primary PCI — ESC 2023 and ACC/AHA 2025: prasugrel or ticagrelor; clopidogrel only if these are unavailable, not tolerated or contraindicated. ESC: pretreatment may be considered.",
                    "Loading doses (ESC Table 6; ACC/AHA Table 7): prasugrel 60 mg; ticagrelor 180 mg; clopidogrel 300–600 mg. CSI 2017: clopidogrel 600 mg before primary PCI.",
                    "Prasugrel: not after stroke (ESC) or stroke/TIA (ACC/AHA — harm); maintenance 5 mg if <60 kg or ≥75 years. NICE: prasugrel preferred; weigh bleeding at ≥75.",
                    "With fibrinolysis: clopidogrel 300 mg, or 75 mg if over 75 years (ESC 2023; ACC/AHA 2025). CSI 2017: no prasugrel or ticagrelor after thrombolysis.",
                ),
                citations = listOf(
                    cite(ICMR_STEMI, "General measures; Drugs & dosage — anti-platelets"),
                    cite(ESC_ACS, "Table 6; Rec Table 5; Rec Table 7 (antiplatelet co-therapy)"),
                    cite(ACS25, "§4.3.1 Rec 1; §4.3.2 Rec 2, 7–9; Table 7"),
                    cite(CSI, "Anti-Platelets; Recommendation of antiplatelets in STEMI"),
                    cite(NICE_ACS, "Rec 1.1.4, 1.1.11"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Choose the reperfusion strategy",
                timing = "Symptoms ≤12 h: reperfuse now",
                points = listOf(
                    "ESC 2023 and ACC/AHA 2025: symptoms ≤12 h — primary PCI if timely; otherwise fibrinolysis if there is no contraindication.",
                    "ACC/AHA 2025 and CSI 2017: if fibrinolysis is contraindicated, transfer for primary PCI regardless of delay.",
                    "ACC/AHA 2025: shock or instability — emergency revascularisation whatever the time from onset. ESC 2023: in shock without mechanical complications, consider lysis if PCI >120 min.",
                    "ESC 2023: >12 h with ongoing ischaemia, instability or life-threatening arrhythmia — primary PCI; 12–48 h — routine primary PCI should be considered. ACC/AHA 2025: 12–24 h reasonable.",
                    "ICMR STW at 12–24 h: transfer to a PCI centre immediately; if pain is ongoing, thrombolyse and transfer. After 24 h: angiography only for the indications below.",
                    "No routine PCI of an occluded artery in stable patients: ESC 2023 after 48 h without symptoms; ACC/AHA 2025 after 24 h without ischaemia, heart failure or arrhythmia.",
                ),
                checklistTitle = "ICMR STW: after 24 h, angiography with a view to PCI only if",
                checklist = listOf(
                    "Recurrent angina not controlled by medical therapy",
                    "Cardiogenic shock",
                    "Acute LVF",
                    "Mechanical complication",
                    "Dynamic ST-T changes",
                    "Life-threatening ventricular arrhythmia",
                ),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 4; §5.2.1; §5.2.1.1; Rec Table 9"),
                    cite(ACS25, "§5.2.1 Rec 1–5; §5.3 Rec 1–3"),
                    cite(ICMR_STEMI, "Patient with STEMI within 12 hours; 12–24 hours; after 24 hours"),
                    cite(CSI, "Table 13"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Fibrinolysis: agents and doses",
                timing = "Within 12 h of symptom onset when PCI cannot be done in time",
                points = listOf(
                    "ESC 2023 and ICMR STW: prefer a fibrin-specific agent — tenecteplase, alteplase or reteplase. ICMR: streptokinase if fibrin-specific agents are unavailable.",
                    "Tenecteplase, single IV bolus (ACC/AHA 2025 Table 13; CSI 2017): <60 kg 30 mg; 60–69 kg 35 mg; 70–79 kg 40 mg; 80–89 kg 45 mg; ≥90 kg 50 mg.",
                    "ESC 2023: a half-dose of tenecteplase should be considered in patients over 75 years.",
                    "Reteplase: two 10-unit IV boluses 30 min apart, each over 2 min (ACC/AHA 2025; CSI 2017).",
                    "Alteplase (ACC/AHA 2025): ≥67 kg — 15 mg IV bolus, 50 mg over 30 min, then 35 mg over 60 min; <67 kg — 15 mg bolus, then weight-based doses below.",
                    "Streptokinase 1.5 million units IV over 60 min (ICMR STW) or 30–60 min (CSI 2017). CSI: may cause hypotension or allergy; avoid re-exposure within 6 months.",
                ),
                doses = listOf(
                    StepDose(
                        "Alteplase <67 kg — after 15 mg bolus (ACC/AHA 2025)",
                        WeightDose(0.75, 0.75, "mg", per = "IV over 30 min"),
                        note = "Then 0.5 mg/kg over the next 60 min",
                        maxTotal = 50.0,
                    ),
                    StepDose(
                        "Alteplase <67 kg — final infusion (ACC/AHA 2025)",
                        WeightDose(0.5, 0.5, "mg", per = "IV over 60 min"),
                        maxTotal = 35.0,
                    ),
                ),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 7; §5.3.1"),
                    cite(ACS25, "§5.3.1; Table 13"),
                    cite(ICMR_STEMI, "PCI incapable centre; Drugs & dosage — thrombolytic therapy"),
                    cite(CSI, "Table 10; Thrombolysis"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Before fibrinolysis: contraindications",
                timing = "Check before the bolus",
                points = listOf(
                    "ACC/AHA 2025 relative: chronic poorly controlled hypertension; SBP >180 or DBP >110 at presentation; ischaemic stroke over 3 months ago; dementia; other intracranial pathology.",
                    "ACC/AHA 2025 relative: traumatic or prolonged (>10 min) CPR; major surgery <3 weeks; internal bleeding within 2–4 weeks; non-compressible vascular punctures.",
                    "ACC/AHA 2025 relative: pregnancy; active peptic ulcer; oral anticoagulant. CSI 2017 adds: within 1 week post partum; advanced liver disease; infective endocarditis.",
                    "CSI 2017: bring BP below 180/100 mm Hg before thrombolysis, preferably with IV nitrate, without undue delay.",
                    "ACC/AHA 2025: its contraindication table is advisory and may not be all-inclusive.",
                ),
                checklistTitle = "Absolute contraindications",
                checklist = listOf(
                    "Any prior intracranial haemorrhage (ACC/AHA); ICH or stroke of unknown origin at any time (ICMR; CSI)",
                    "Ischaemic stroke within 3 months, except acute ischaemic stroke within 4.5 h (ACC/AHA); within 6 months (ICMR; CSI)",
                    "Structural cerebral vascular lesion (e.g. AVM) or intracranial neoplasm",
                    "Suspected aortic dissection",
                    "Active bleeding or bleeding diathesis, excluding menses",
                    "Closed-head or facial trauma within 3 months; intracranial or intraspinal surgery within 2 months (ACC/AHA)",
                    "Major trauma, surgery or head injury within 1 month (ICMR; CSI: 3 weeks); GI bleeding within 1 month (ICMR; CSI)",
                    "Severe uncontrolled hypertension (ICMR); unresponsive to therapy, SBP >180 or DBP >110 mm Hg (ACC/AHA)",
                    "Non-compressible punctures in the past 24 h, e.g. liver biopsy, lumbar puncture (CSI)",
                    "ST depression only, unless true posterior STEMI is suspected — fibrinolysis is harmful (ACC/AHA)",
                ),
                citations = listOf(
                    cite(ACS25, "Table 14; §5.3 Rec 4"),
                    cite(ICMR_STEMI, "Absolute contra-indications to thrombolytic therapy"),
                    cite(CSI, "Table 11; Thrombolysis"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Anticoagulation",
                timing = "With either reperfusion strategy",
                points = listOf(
                    "With fibrinolysis — ESC 2023: enoxaparin IV then SC is the preferred anticoagulant; weight-adjusted UFH if enoxaparin is unavailable. ICMR STW: enoxaparin preferred over UFH.",
                    "Continue until revascularisation, or for the hospital stay up to 8 days (ESC 2023; ACC/AHA 2025). ICMR STW: until PCI or discharge.",
                    "ESC 2023: with streptokinase, consider fondaparinux IV then SC 24 h later. ACC/AHA 2025 with any lytic: fondaparinux 2.5 mg IV, then 2.5 mg SC daily; not if CrCl <30.",
                    "Primary PCI — ESC 2023: UFH bolus 70–100 U/kg; enoxaparin or bivalirudin are alternatives. Fondaparinux must not support PCI (ESC 2023; ACC/AHA 2025 — harm).",
                    "ICMR STW: UFH 60 U/kg (max 5000 U), then 12 U/kg/h to aPTT 50–70 s; or enoxaparin 1 mg/kg SC 12-hourly.",
                    "ACC/AHA 2025 enoxaparin with fibrinolysis: <75 years — 30 mg IV, then after 15 min 1 mg/kg SC 12-hourly; ≥75 — no bolus, 0.75 mg/kg. CrCl <30: 1 mg/kg daily.",
                ),
                doses = listOf(
                    StepDose(
                        "UFH with fibrinolysis — bolus (ACC/AHA 2025)",
                        WeightDose(60.0, 60.0, "units", per = "IV bolus"),
                        maxTotal = 4000.0,
                    ),
                    StepDose(
                        "UFH with fibrinolysis — infusion (ACC/AHA 2025)",
                        WeightDose(12.0, 12.0, "units", per = "per hour IV"),
                        note = "Adjust to the therapeutic aPTT range",
                        maxTotal = 1000.0,
                    ),
                    StepDose(
                        "UFH — bolus (ICMR STW)",
                        WeightDose(60.0, 60.0, "units", per = "IV bolus"),
                        note = "Then 12 units/kg/h to keep aPTT 50–70 s",
                        maxTotal = 5000.0,
                    ),
                    StepDose(
                        "Enoxaparin with fibrinolysis, age <75 (ACC/AHA 2025)",
                        WeightDose(1.0, 1.0, "mg", per = "SC every 12 h"),
                        note = "After a 30 mg IV bolus; 100 mg cap applies to the first 2 doses; CrCl <30: 1 mg/kg every 24 h",
                        maxTotal = 100.0,
                    ),
                    StepDose(
                        "Enoxaparin with fibrinolysis, age ≥75 (ACC/AHA 2025)",
                        WeightDose(0.75, 0.75, "mg", per = "SC every 12 h"),
                        note = "No IV bolus; 75 mg cap applies to the first 2 doses",
                        maxTotal = 75.0,
                    ),
                    StepDose(
                        "UFH during primary PCI (ESC 2023)",
                        WeightDose(70.0, 100.0, "units", per = "IV bolus"),
                        note = "ACC/AHA 2025: same range if no prior anticoagulant, to ACT 250–300 s",
                    ),
                ),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 5 (patients with STEMI); Rec Table 7; Table 6"),
                    cite(ACS25, "§4.4 Rec 4–5, 8–11; Table 10"),
                    cite(ICMR_STEMI, "Drugs & dosage — anti thrombotics; Post thrombolysis"),
                    cite(CSI, "Anti coagulation"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "After fibrinolysis: transfer, rescue and pharmaco-invasive PCI",
                timing = "ECG at 60–90 min; angiography 2–24 h (ESC; ACC/AHA) or 3–24 h (ICMR; CSI)",
                points = listOf(
                    "ESC 2023 and ACC/AHA 2025: transfer to a PCI-capable centre immediately after fibrinolysis. ICMR STW and NICE: repeat ECG 60–90 min after starting fibrinolysis.",
                    "Failure — ESC 2023: ST resolution <50% at 60–90 min, instability, worsening ischaemia or persistent pain. ACC/AHA 2025: <50% resolution anterior, <70% inferior.",
                    "Failed fibrinolysis: immediate angiography and rescue PCI (ESC; ACC/AHA; ICMR; NICE). ESC 2023 and NICE: do not repeat fibrinolysis.",
                    "Successful: angiography, PCI if needed, 2–24 h after lysis (ESC 2023; ACC/AHA 2025) or 3–24 h (ICMR STW; CSI 2017; STEMI INDIA 2015).",
                    "ESC 2023: new or persistent heart failure or shock after fibrinolysis — emergency angiography and PCI.",
                    "ICMR STW: success = >50% ST resolution with pain relief. CSI 2017: absent reperfusion arrhythmias also point to failure.",
                ),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 4 (transfer/interventions after fibrinolysis); §5.3.1.2"),
                    cite(ACS25, "§5.3.2 Rec 1–3 and synopsis"),
                    cite(ICMR_STEMI, "Post thrombolysis; STEMI diagnosis flowchart"),
                    cite(CSI, "Pharmaco-invasive (PI) strategy; Thrombolysed patient presenting <24 h; Table 13"),
                    cite(STEMI_INDIA, "§4 Structure of the STEMI system of care"),
                    cite(NICE_ACS, "Rec 1.1.21–1.1.23"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Time goals start at different points: ESC 2023 counts from STEMI diagnosis on the ECG; ACC/AHA 2025 counts from first medical contact; ICMR STW relates all delays to FMC, with diagnosis ideally within 10 min.",
                listOf(cite(ESC_ACS, "Table 3; Fig. 7"), cite(ACS25, "§3.1.1 footnote; §5.2.1 Rec 1"), cite(ICMR_STEMI, "STEMI diagnosis flowchart")),
            ),
            ProtocolNote(
                "Pharmaco-invasive window: ESC 2023 and ACC/AHA 2025 — 2–24 h after fibrinolysis; ICMR STW, CSI 2017 and STEMI INDIA 2015 — 3–24 h.",
                listOf(cite(ESC_ACS, "Rec Table 4"), cite(ACS25, "§5.3.2 Rec 3"), cite(ICMR_STEMI, "Post thrombolysis"), cite(CSI, "Table 13"), cite(STEMI_INDIA, "§4")),
            ),
            ProtocolNote(
                "Agent choice: ESC, ACC/AHA and ICMR prefer fibrin-specific agents; CSI 2017 notes streptokinase is cheap, widely available and the most used thrombolytic in India. ACC/AHA 2025: streptokinase is no longer available in the US.",
                listOf(cite(ESC_ACS, "Rec Table 7"), cite(ACS25, "Table 13"), cite(ICMR_STEMI, "PCI incapable centre"), cite(CSI, "Thrombolysis; Table 13")),
            ),
            ProtocolNote(
                "ICMR STW tenecteplase bands list only 35 mg (60–70 kg), 40 mg (70–80 kg) and 45 mg (>80 kg); ACC/AHA 2025 and CSI 2017 also give 30 mg below 60 kg and 50 mg at ≥90 kg.",
                listOf(cite(ICMR_STEMI, "Drugs & dosage — tenecteplase"), cite(ACS25, "Table 13"), cite(CSI, "Table 10")),
            ),
            ProtocolNote(
                "ICMR STW prints reteplase as '10 mg IV bolus, repeat after 30 min'; ACC/AHA 2025 and CSI 2017 dose reteplase in units — 10 units plus 10 units, 30 min apart.",
                listOf(cite(ICMR_STEMI, "Drugs & dosage — reteplase"), cite(ACS25, "Table 13"), cite(CSI, "Table 10")),
            ),
            ProtocolNote(
                "Late presenters: ICMR STW advises thrombolysis before transfer at 12–24 h if pain is ongoing; ESC 2023 recommends fibrinolysis within 12 h and primary PCI later; ACC/AHA 2025 says lysis may be considered >12 h with a large area at risk or instability if PCI is unavailable.",
                listOf(cite(ICMR_STEMI, "Patient with STEMI in 12–24 hours"), cite(ESC_ACS, "Rec Table 4"), cite(ACS25, "§5.3.1 synopsis")),
            ),
            ProtocolNote(
                "UFH dosing differs: ICMR STW 60 U/kg (max 5000 U), aPTT 50–70 s; ACC/AHA 2025 60 IU/kg (max 4000 IU) then 12 IU/kg/h (max 1000 IU/h); ESC 2023 70–100 U/kg.",
                listOf(cite(ICMR_STEMI, "Drugs & dosage — anti thrombotics"), cite(ACS25, "Table 10"), cite(ESC_ACS, "Table 6")),
            ),
            ProtocolNote(
                "Early beta-blockers differ: ESC 2023 — IV metoprolol may be considered before primary PCI; CSI 2017 — no routine IV beta-blockers; ICMR STW lists an oral beta-blocker if LVEF <40%.",
                listOf(cite(ESC_ACS, "Rec Table 3"), cite(CSI, "Beta blockers"), cite(ICMR_STEMI, "Drugs & dosage — beta-blocker")),
            ),
            ProtocolNote(
                "Review scope: ESC 2023 fibrinolytic doses sit in its online supplement, not reviewed — doses here come from ACC/AHA 2025, ICMR STW and CSI 2017. Three ACC/AHA 2025 errata were not reviewed.",
                listOf(cite(ESC_ACS, "§5.3.1"), cite(ACS25, "Table 13")),
            ),
        ),
        citations = listOf(
            cite(ICMR_STEMI), cite(CSI), cite(STEMI_INDIA), cite(ESC_ACS), cite(ACS25), cite(NICE_ACS),
        ),
    ),
    // =============================================================================================== NSTE-ACS
    Protocol(
        id = "nste-acs",
        category = ProtocolCategory.Cardiac,
        title = "NSTE-ACS (NSTEMI and unstable angina)",
        summary = "High-sensitivity troponin pathways, risk scores, antiplatelet and anticoagulant therapy and timing of " +
            "angiography — ICMR STW, ESC 2023, ACC/AHA 2025 and NICE.",
        keywords = listOf(
            "NSTEMI", "NSTE-ACS", "non ST elevation MI", "unstable angina", "UA", "ACS", "acute coronary syndrome",
            "chest pain", "angina", "troponin", "hs-troponin", "hs-cTn", "trop I", "trop T", "0/1 h", "0/2 h", "rule out MI",
            "GRACE", "TIMI", "HEART score", "fondaparinux", "enoxaparin", "UFH", "heparin", "ticagrelor", "clopidogrel",
            "prasugrel", "CAG", "coronary angiography",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Initial assessment and ECG",
                timing = "ECG within 10 min",
                points = listOf(
                    "ESC 2023 and ACC/AHA 2025: 12-lead ECG within 10 min; repeat ECGs if non-diagnostic, especially with persistent or recurrent symptoms.",
                    "ACC/AHA 2025: ischaemia = new ST depression ≥0.5 mm or T inversion >1 mm in ≥2 contiguous leads, or transient ST elevation. A normal ECG does not exclude ACS.",
                    "ACC/AHA 2025: ST depression in V1–V3 — record V7–V9 to look for posterior STEMI.",
                    "ICMR STW: suspect ACS with rest angina or pain >20 min, crescendo or new-onset angina, or post-infarction angina; consider atypical presentations.",
                    "ICMR STW red flags: pain >20 min, recurrent or rest pain, breathlessness, sweating, syncope, instability — refer as emergency to a PCI or thrombolysis-capable centre.",
                    "ICMR STW: look for aortic dissection (unequal pulses), pneumothorax, embolism and pericarditis. Oxygen only if SpO₂ <90% (ESC 2023).",
                ),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 1; §3.2.2; Rec Table 3"),
                    cite(ACS25, "§3.1.2 Rec 1–2; Table 3"),
                    cite(ICMR_NSTE, "Consider angina if; Red flag signs; Look for other causes"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "High-sensitivity troponin pathway",
                timing = "0 h, then 1 h or 2 h",
                points = listOf(
                    "ESC 2023: hs-cTn at presentation with the result within 60 min; use the 0 h/1 h algorithm (best) or 0 h/2 h (second best). Cut-offs are assay-specific.",
                    "ESC 2023 rule-out: very low 0 h hs-cTn (only if pain began >3 h earlier), or low 0 h with no 1–2 h rise. Rule-in: high 0 h or a clear rise.",
                    "ESC 2023: observe zone — repeat hs-cTn at 3 h ± echocardiography; mortality is comparable to rule-in patients.",
                    "ACC/AHA 2025: repeat hs-cTn 1–2 h after time zero, conventional cTn at 3–6 h; single-sample strategies need the sample ≥3 h after symptom onset.",
                    "AHA/ACC chest pain 2021: low risk includes HEART score ≤3 with initial and serial troponin below the assay 99th percentile.",
                    "ESC 2023: always combine with clinical assessment and ECG; resample if chest pain recurs.",
                ),
                scoreIds = listOf("heart"),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 1 (blood sampling); §3.3.4; Fig. 6"),
                    cite(ACS25, "§3.1.2 Rec 3–4 and supportive text"),
                    cite(CP21, "Table 8"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Risk stratify",
                points = listOf(
                    "ESC 2023 high risk: confirmed NSTEMI on hs-cTn algorithm, dynamic ST/T changes, transient ST elevation, or GRACE >140. Very high risk: see checklist.",
                    "ICMR STW: high risk GRACE >140 or TIMI >4; intermediate GRACE 109–140 or TIMI 2–3; low GRACE <108 or TIMI 0–1.",
                    "NICE NG185: estimate predicted 6-month mortality (e.g. GRACE); above 3.0% is intermediate or higher risk.",
                    "ACC/AHA 2025: GRACE and TIMI scores are well validated for prognosis but are not diagnostic tools.",
                ),
                checklistTitle = "Very high risk (ESC 2023; ICMR STW)",
                checklist = listOf(
                    "Haemodynamic instability or cardiogenic shock",
                    "Recurrent or refractory chest pain despite medical treatment",
                    "Life-threatening arrhythmias or cardiac arrest",
                    "Mechanical complications of MI",
                    "Acute heart failure presumed due to ongoing ischaemia",
                    "Recurrent dynamic ST/T changes, especially intermittent ST elevation",
                    "Severe mitral regurgitation (ICMR STW)",
                ),
                scoreIds = listOf("timi-ua-nstemi", "heart"),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 4; Fig. 8"),
                    cite(ICMR_NSTE, "UA/NSTEMI risk categorization; GRACE score; TIMI score"),
                    cite(NICE_ACS, "Rec 1.2.7, 1.2.11"),
                    cite(ACS25, "§3.1.3; Table 5"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Antiplatelet therapy",
                timing = "Aspirin at once; P2Y12 timing depends on the plan",
                points = listOf(
                    "Aspirin loading: ICMR STW 325 mg; ESC 2023 150–300 mg oral (75–250 mg IV); ACC/AHA 2025 162–325 mg chewed; NICE 300 mg.",
                    "ESC 2023: no routine P2Y12 pretreatment if anatomy is unknown and angiography <24 h is planned; may be considered if no early angiography and no high bleeding risk.",
                    "ACC/AHA 2025: if angiography is expected >24 h, upstream clopidogrel or ticagrelor may be considered. NICE: no dual antiplatelets before NSTEMI or UA is diagnosed.",
                    "PCI: prasugrel or ticagrelor (ACC/AHA 2025; ESC 2023 favours prasugrel). No planned invasive evaluation: ticagrelor (ACC/AHA 2025; NICE unless high bleeding risk).",
                    "Loading: prasugrel 60 mg; ticagrelor 180 mg; clopidogrel 300–600 mg (ESC; ACC/AHA) or 300 mg (ICMR). Prasugrel: not after stroke (ESC) or stroke/TIA (ACC/AHA).",
                    "ICMR STW at PHC/CHC: start aspirin, loading-dose clopidogrel, heparin or LMWH, high-dose statin and metoprolol; refer high or intermediate risk to a PCI centre.",
                ),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 5; Table 6"),
                    cite(ACS25, "§4.3.1; §4.3.2 Rec 2–6; Table 7"),
                    cite(NICE_ACS, "Rec 1.2.2, 1.2.6, 1.2.21–1.2.22"),
                    cite(ICMR_NSTE, "Management — PHC/CHC level; Drugs & dosage"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Anticoagulation",
                timing = "At diagnosis",
                points = listOf(
                    "ESC 2023: parenteral anticoagulation for all ACS at diagnosis. ACC/AHA 2025: IV UFH is useful; continue anticoagulation until revascularisation.",
                    "No early angiography planned: fondaparinux (ESC 2023); enoxaparin or fondaparinux as alternatives to UFH (ACC/AHA 2025). ESC: early angiography planned — enoxaparin may replace UFH.",
                    "NICE NG185: fondaparinux if no high bleeding risk, unless immediate angiography; consider UFH if creatinine >265 µmol/L.",
                    "Fondaparinux 2.5 mg SC daily (ESC; ACC/AHA); never as the only anticoagulant for PCI — give UFH at angiography (ACC/AHA 2025 harm; NICE). ESC: avoid if CrCl <20.",
                    "Enoxaparin 1 mg/kg SC 12-hourly; 1 mg/kg once daily if CrCl <30 mL/min (ESC 2023; ACC/AHA 2025).",
                    "ICMR STW: enoxaparin 1 mg/kg SC 12-hourly, or UFH 60 U/kg (max 5000 U) then 12 U/kg/h to aPTT 50–70 s.",
                ),
                doses = listOf(
                    StepDose(
                        "UFH — initial bolus (ACC/AHA 2025)",
                        WeightDose(60.0, 60.0, "units", per = "IV bolus"),
                        maxTotal = 4000.0,
                    ),
                    StepDose(
                        "UFH — initial infusion (ACC/AHA 2025)",
                        WeightDose(12.0, 12.0, "units", per = "per hour IV"),
                        note = "Adjust to aPTT 60–80 s",
                        maxTotal = 1000.0,
                    ),
                    StepDose(
                        "UFH — initial bolus (ESC 2023)",
                        WeightDose(70.0, 100.0, "units", per = "IV bolus"),
                        note = "Then infusion titrated to aPTT 60–80 s",
                    ),
                    StepDose(
                        "UFH — bolus (ICMR STW)",
                        WeightDose(60.0, 60.0, "units", per = "IV bolus"),
                        note = "Then 12 units/kg/h to keep aPTT 50–70 s",
                        maxTotal = 5000.0,
                    ),
                    StepDose(
                        "Enoxaparin (ESC 2023; ACC/AHA 2025; ICMR STW)",
                        WeightDose(1.0, 1.0, "mg", per = "SC every 12 h"),
                        note = "CrCl <30 mL/min: 1 mg/kg once daily",
                    ),
                ),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 5 (anticoagulant therapy; patients with NSTE-ACS); Table 6"),
                    cite(ACS25, "§4.4 Rec 1–4, 8; Table 10"),
                    cite(NICE_ACS, "Rec 1.2.3–1.2.4"),
                    cite(ICMR_NSTE, "Drugs & dosage — anti thrombotics"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Timing of angiography",
                timing = "Immediate (<2 h) if very high risk; within 24 h if high risk",
                points = listOf(
                    "ESC 2023: very high risk — immediate invasive strategy; high risk — inpatient invasive strategy, within 24 h should be considered.",
                    "ESC 2023: no high-risk features and low suspicion — selective invasive approach after non-invasive testing.",
                    "ACC/AHA 2025: refractory angina or haemodynamic/electrical instability — immediate (<2 h from admission); high risk — within 24 h is reasonable; others — before discharge.",
                    "ICMR STW: very high risk <2 h; high 2–24 h; intermediate 25–72 h; low — medical therapy with CT coronary angiography or stress test within a week.",
                    "ICMR STW at a non-PCI hospital: very high risk — immediate transfer; high — same-day transfer; intermediate — transfer for PCI within 72 h.",
                    "NICE NG185: unstable — immediate angiography; predicted 6-month mortality >3.0% — consider angiography within 72 h of admission.",
                ),
                citations = listOf(
                    cite(ESC_ACS, "Rec Table 4; Fig. 8; Table 3"),
                    cite(ACS25, "§6.1 Rec 1–5 and supportive text"),
                    cite(ICMR_NSTE, "UA/NSTEMI diagnosis flowchart; Risk category management"),
                    cite(NICE_ACS, "Rec 1.2.12–1.2.14"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Angiography timing differs: ESC 2023 and ACC/AHA 2025 — within 24 h for high risk; ICMR STW — 2–24 h for high and 25–72 h for intermediate risk; NICE — within 72 h if 6-month mortality >3%.",
                listOf(cite(ESC_ACS, "Rec Table 4"), cite(ACS25, "§6.1 Rec 4"), cite(ICMR_NSTE, "UA/NSTEMI diagnosis flowchart"), cite(NICE_ACS, "Rec 1.2.13")),
            ),
            ProtocolNote(
                "P2Y12 pretreatment is contested: ESC 2023 advises against routine pretreatment when early angiography is planned; ACC/AHA 2025 allows upstream clopidogrel or ticagrelor if angiography is >24 h away; ICMR STW loads clopidogrel at PHC/CHC level.",
                listOf(cite(ESC_ACS, "Rec Table 5"), cite(ACS25, "§4.3.2 Rec 6"), cite(ICMR_NSTE, "Management — PHC/CHC level")),
            ),
            ProtocolNote(
                "First-choice anticoagulant differs: NICE and ESC favour fondaparinux when early angiography is not planned; ACC/AHA 2025 lists UFH first; ICMR STW lists enoxaparin and UFH.",
                listOf(cite(NICE_ACS, "Rec 1.2.3"), cite(ESC_ACS, "Rec Table 5"), cite(ACS25, "§4.4 Rec 1–2"), cite(ICMR_NSTE, "Drugs & dosage")),
            ),
            ProtocolNote(
                "ICMR STW bands as printed: high TIMI >4, intermediate TIMI 2–3 (TIMI 4 is not assigned); low risk is GRACE <108 in the text and GRACE <109 in the flowchart.",
                listOf(cite(ICMR_NSTE, "UA/NSTEMI risk categorization; flowchart")),
            ),
            ProtocolNote(
                "ESC 0 h/1 h and 0 h/2 h cut-offs are assay-specific (ESC online Table S4, not reproduced here); use the thresholds validated for the local assay.",
                listOf(cite(ESC_ACS, "§3.3.4.1")),
            ),
        ),
        citations = listOf(cite(ICMR_NSTE), cite(ESC_ACS), cite(ACS25), cite(CP21), cite(NICE_ACS)),
    ),
    // =============================================================================================== Acute heart failure
    Protocol(
        id = "acute-heart-failure",
        category = ProtocolCategory.Cardiac,
        title = "Acute heart failure and cardiogenic shock",
        summary = "Phenotype and causes (CHAMPIT), oxygen and NIV thresholds, IV loop diuretics, vasodilators by blood " +
            "pressure, opiates, inotropes and vasopressors — ESC 2026, ICMR STW and ESC/ACC/AHA ACS guidance.",
        keywords = listOf(
            "acute heart failure", "AHF", "ADHF", "decompensated heart failure", "LVF", "acute LVF", "pulmonary oedema",
            "pulmonary edema", "APO", "flash pulmonary oedema", "cardiogenic shock", "CHF", "CCF", "breathlessness",
            "orthopnoea", "PND", "furosemide", "frusemide", "Lasix", "NIV", "CPAP", "BiPAP", "nitroglycerin", "NTG",
            "nitroprusside", "dobutamine", "noradrenaline", "norepinephrine", "milrinone", "levosimendan", "CHAMPIT",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise the phenotype and the cause",
                timing = "On arrival",
                points = listOf(
                    "ESC 2026: identify the phenotype — cardiogenic shock, acute pulmonary oedema, or decompensated left- or right-sided HF — and treat acute causes at once.",
                    "ESC 2026: pulmonary oedema — respiratory distress, orthopnoea, hypoxaemia ± hypercapnia, RR >25/min; often fluid redistribution rather than total overload.",
                    "ESC 2026: cardiogenic shock — end-organ hypoperfusion from cardiac dysfunction; lactate >2 mmol/L; no fixed BP cut-off; SCAI stages A–E.",
                    "ICMR STW: ECG to exclude STEMI and tachyarrhythmia; chest X-ray to exclude a respiratory cause; electrolytes, creatinine; echo, BNP/NT-proBNP where available.",
                    "ICMR STW: refer up if BP <90 or >200 mm Hg, HR <50 or >120/min, RR >30/min or cyanosis, oliguria, or altered sensorium.",
                ),
                checklistTitle = "Acute causes — CHAMPIT (ESC 2026 Fig. 13)",
                checklist = listOf(
                    "C — acute Coronary syndrome",
                    "H — Hypertensive emergency",
                    "A — Arrhythmias",
                    "M — Mechanical causes",
                    "P — Pulmonary embolism",
                    "I — Infections and inflammation",
                    "T — Tamponade",
                ),
                citations = listOf(
                    cite(ESC_HF, "§7.3.3–7.3.4; Fig. 13; Table 13"),
                    cite(ICMR_HF, "Management at PHC; Management at CHC; Investigations"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Oxygen and ventilation",
                timing = "Immediately if SpO₂ <90%",
                points = listOf(
                    "ESC 2026: oxygen if SpO₂ <90% or PaO₂ <60 mm Hg; indiscriminate oxygen in non-hypoxaemic patients may harm. ICMR STW: oxygen if SpO₂ <90%.",
                    "ESC 2026: NIV for respiratory distress (RR >25/min, SpO₂ <90%), started as soon as possible to ease distress and avoid intubation.",
                    "ESC 2026: NIV lowers preload and BP — use with caution in hypotension or low preload reserve, e.g. decompensated right-sided HF.",
                    "ICMR STW (district hospital): consider NIV if marked respiratory distress and SpO₂ <90%.",
                ),
                checklistTitle = "Intubate (ESC 2026 Rec Table 9)",
                checklist = listOf(
                    "Cardiac or respiratory arrest",
                    "Progressive worsening of mental status",
                    "Worsening respiratory failure despite NIV (PaO₂ <60, PaCO₂ >50 mm Hg, pH <7.35) or NIV intolerance",
                    "Persistent haemodynamic instability",
                    "Need for airway protection",
                ),
                citations = listOf(
                    cite(ESC_HF, "§7.5.1–7.5.2; Rec Table 9"),
                    cite(ICMR_HF, "Management at CHC; Management at district hospital"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "IV loop diuretic",
                timing = "Start early; check the response at 2–6 h",
                points = listOf(
                    "ESC 2026: IV loop diuretic for every patient with fluid overload — furosemide 40 mg IV if diuretic-naive, or twice the usual oral dose.",
                    "ESC 2026: adequate response — spot urine sodium ≥70 mmol/L at 2 h, or urine output ≥100 mL/h over the first 6 h.",
                    "ESC 2026: poor response — double the dose and/or add IV acetazolamide 500 mg or eGFR-based hydrochlorothiazide; furosemide maximum usually 600 mg/day.",
                    "ESC 2026: pulmonary oedema usually needs vasodilators more than high-dose diuretics. Check creatinine and electrolytes at least every 24 h.",
                    "ICMR STW: IV furosemide 10–80 mg as needed in the acute stage; change to oral when symptoms subside; monitor electrolytes and creatinine.",
                ),
                citations = listOf(
                    cite(ESC_HF, "§7.3.3; §7.5.3; Fig. 15; Rec Table 9; Suppl. Table S13"),
                    cite(ICMR_HF, "Common drugs and dosage — furosemide"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Vasodilators, blood pressure and opiates",
                timing = "Vasodilator if SBP >110 mm Hg",
                points = listOf(
                    "ESC 2026: IV vasodilators may be considered as initial therapy if SBP >110 mm Hg, to relieve symptoms and congestion; monitor BP closely.",
                    "ESC 2026 supplement: nitroglycerin from 10–20 mcg/min up to 200 mcg/min; 1–2 mg boluses in severely hypertensive pulmonary oedema.",
                    "ESC 2026 supplement: nitroprusside 0.3 up to 5 mcg/kg/min. Use vasodilators with extreme caution in LV hypertrophy or severe aortic stenosis.",
                    "ESC Council 2019: hypertensive pulmonary oedema — SBP <140 mm Hg immediately, with nitroprusside or nitroglycerin plus a loop diuretic.",
                    "ESC 2026: routine opiates are not recommended — only for severe or intractable pain or anxiety, given the risk of respiratory failure.",
                ),
                doses = listOf(
                    StepDose(
                        "Nitroprusside (ESC 2026 Suppl. Table S14)",
                        WeightDose(0.3, 5.0, "mcg", per = "per minute IV infusion"),
                        note = "Start at 0.3 and titrate; watch for hypotension and isocyanate toxicity",
                    ),
                ),
                citations = listOf(
                    cite(ESC_HF, "§7.5.7; §7.5.10; Rec Table 9; Fig. 13; Suppl. §4.3, Table S14"),
                    cite(ESC_HE, "Table 3; acute cardiogenic pulmonary oedema"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Cardiogenic shock: drugs and devices",
                timing = "SBP <90 mm Hg with hypoperfusion",
                points = listOf(
                    "ESC 2026: inotropes may be considered if SBP <90 mm Hg with hypoperfusion despite standard treatment, including a fluid challenge; not routine — arrhythmia and ischaemia risk.",
                    "ESC 2026: a vasopressor, preferably norepinephrine, may be considered to raise BP; dopamine caused more arrhythmias than norepinephrine.",
                    "ESC 2026: levosimendan or a PDE-III inhibitor may be preferred over dobutamine in patients taking beta-blockers.",
                    "ESC 2026: Shock Team for possible MCS; microaxial flow pump in selected STEMI shock without hypoxic brain injury; no routine IABP.",
                    "ACS with shock — ESC 2023: immediate angiography and PCI of the infarct artery; ACC/AHA 2025: emergency revascularisation regardless of time from onset.",
                ),
                doses = listOf(
                    StepDose(
                        "Dobutamine (ESC 2026 Suppl. Table S16)",
                        WeightDose(2.0, 20.0, "mcg", per = "per minute IV infusion"),
                    ),
                    StepDose(
                        "Norepinephrine (ESC 2026 Suppl. Table S16)",
                        WeightDose(0.2, 1.0, "mcg", per = "per minute IV infusion"),
                    ),
                    StepDose(
                        "Milrinone (ESC 2026 Suppl. Table S15)",
                        WeightDose(0.375, 0.75, "mcg", per = "per minute IV infusion"),
                    ),
                ),
                citations = listOf(
                    cite(ESC_HF, "§7.5.8–7.5.9; §7.5.11; Rec Tables 9–10; Suppl. Tables S15–S16"),
                    cite(ESC_ACS, "Rec Table 9"),
                    cite(ACS25, "§5.2.1 Rec 2"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "After stabilisation",
                points = listOf(
                    "ESC 2026: continue or rapidly restart foundational HF therapy unless there is hypoperfusion; start an SGLT2 inhibitor in hospital after initial stabilisation.",
                    "ESC 2026: switch IV to oral diuretic once decongested; check for residual congestion before discharge.",
                    "ICMR STW: start ARNI and spironolactone; beta-blocker after decongestion if BP >100 mm Hg and HR >60/min; SGLT2 inhibitor.",
                    "ESC 2026: rapid uptitration of therapy with frequent visits in the first 6 weeks after an HF admission.",
                ),
                citations = listOf(
                    cite(ESC_HF, "§7.4.1–7.4.3; §7.5.3; Rec Tables 8–9"),
                    cite(ICMR_HF, "Management at CHC; Common drugs and dosage"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Opiates: ESC 2026 advises against routine use in decompensated HF; ESC 2023 ACS says IV opioids should be considered for ischaemic chest pain — weigh both in ACS with pulmonary oedema.",
                listOf(cite(ESC_HF, "Rec Table 9 (opiates)"), cite(ESC_ACS, "Rec Table 3")),
            ),
            ProtocolNote(
                "Diuretic dose: ICMR STW lists IV furosemide 10–80 mg; ESC 2026 starts at 40 mg IV (or twice the oral dose) and escalates by urine sodium and output.",
                listOf(cite(ICMR_HF, "Common drugs and dosage"), cite(ESC_HF, "§7.5.3; Fig. 15")),
            ),
            ProtocolNote(
                "ESC 2026 sets no BP cut-off for cardiogenic shock and states the target mean BP is not well defined.",
                listOf(cite(ESC_HF, "§7.3.4; §7.5.9")),
            ),
            ProtocolNote(
                "The ICMR HF STW (2026) cites the 2021 ESC HF guideline; the 2026 ESC guideline used here replaces it.",
                listOf(cite(ICMR_HF, "References"), cite(ESC_HF)),
            ),
        ),
        citations = listOf(cite(ESC_HF), cite(ICMR_HF), cite(ESC_HE), cite(ESC_ACS), cite(ACS25)),
    ),
    // =============================================================================================== Hypertensive emergency
    Protocol(
        id = "hypertensive-emergency",
        category = ProtocolCategory.Cardiac,
        title = "Hypertensive emergency",
        summary = "Emergency versus urgency, how fast and how far to lower BP, compelling conditions and IV agents — " +
            "ICMR STW, ESC 2024, ESC Council 2019, ISH 2020 and AHA/ASA stroke guidance.",
        keywords = listOf(
            "hypertensive emergency", "hypertensive crisis", "hypertensive urgency", "malignant hypertension",
            "accelerated hypertension", "severe hypertension", "high BP", "BP 180/120", "hypertensive encephalopathy",
            "PRES", "TMA", "labetalol", "nicardipine", "esmolol", "nitroprusside", "SNP", "nitroglycerin", "NTG",
            "metoprolol", "nifedipine", "pre-eclampsia", "eclampsia",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Emergency or urgency?",
                timing = "At triage",
                points = listOf(
                    "ESC 2024: emergency = BP ≥180/110 mm Hg with acute hypertension-mediated organ damage, often symptomatic; needs immediate careful lowering, often IV.",
                    "ICMR STW: BP ≥180/120 with symptoms of target-organ damage is a red flag — stabilise ABC and refer to emergency care at a higher centre.",
                    "ISH 2020 and ESC Council 2019: no specific BP threshold defines an emergency; the rate of rise matters more than the absolute value.",
                    "ESC 2024: urgency (no acute organ damage) — oral treatment, usually no admission. Pain or distress can raise BP that settles once relieved.",
                    "ICMR STW urgency (≥180/120, no organ damage): oral drugs, no IV medicines, lower gradually over 24–48 h, review within 1 week.",
                    "ESC Council 2019: avoid short-acting nifedipine (rapid falls); after an oral dose, observe for at least 2 h.",
                ),
                checklistTitle = "Acute organ damage (ESC 2024; ISH 2020; ICMR STW)",
                checklist = listOf(
                    "Retinal haemorrhages, cotton-wool spots or papilloedema (malignant hypertension)",
                    "Encephalopathy — somnolence, confusion, seizures, cortical blindness",
                    "Haemolysis and thrombocytopenia (thrombotic microangiopathy)",
                    "Focal deficit, speech or swallowing difficulty — stroke or haemorrhage",
                    "Chest pain — ACS or aortic dissection",
                    "Breathlessness — acute pulmonary oedema or heart failure",
                    "Acute kidney injury",
                    "Severe pre-eclampsia or eclampsia",
                ),
                citations = listOf(
                    cite(ESC_HTN, "§10.1.1"),
                    cite(ICMR_HTN, "Hypertension urgency; Red flags signs"),
                    cite(ISH, "§10.4 Definition and clinical presentation"),
                    cite(ESC_HE, "Clinical presentation; Acute management of hypertensive emergencies"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Work-up",
                points = listOf(
                    "ESC Council 2019: haemoglobin, platelets, creatinine, sodium, potassium, LDH, haptoglobin; urinalysis for protein and sediment; ECG; fundoscopy.",
                    "ESC Council 2019, as indicated: troponin, smear for schistocytes, chest X-ray, echocardiography, CT or MRI brain, CT angiography of the aorta, renal ultrasound.",
                    "ESC Council 2019 and ISH 2020: ask about non-adherence and drugs — NSAIDs, steroids, sympathomimetics, cocaine, anti-angiogenic therapy.",
                    "ISH 2020: secondary causes in 20–40% of malignant hypertension; ESC 2024: screen survivors for secondary hypertension. ISH: monthly follow-up until target BP.",
                ),
                citations = listOf(
                    cite(ESC_HE, "Table 2; Clinical presentation"),
                    cite(ISH, "§10.4 Diagnostic tests; Follow-up"),
                    cite(ESC_HTN, "§10.1.3"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "How fast and how far",
                timing = "Over hours — faster only for aorta, heart and eclampsia",
                points = listOf(
                    "Malignant hypertension ± microangiopathy or AKI: lower MAP by 20–25% over several hours (ESC Council 2019; ISH 2020).",
                    "Hypertensive encephalopathy: lower MAP by 20–25% immediately (ESC Council 2019; ISH 2020).",
                    "ESC 2024: rapid, uncontrolled or excessive lowering is not recommended; use short half-life IV drugs with continuous monitoring in a high-dependency area.",
                    "ESC 2024: low-dose oral ACE inhibitor, ARB or beta-blocker (e.g. captopril, metoprolol) can also work, given in hospital.",
                    "ESC Council 2019: MAP falls of more than 50% have been associated with ischaemic stroke and death.",
                    "ISH 2020: labetalol and nicardipine are generally safe in all hypertensive emergencies and should be available wherever these are managed.",
                ),
                citations = listOf(
                    cite(ESC_HE, "Table 3; Malignant hypertension"),
                    cite(ISH, "Table 12; §10.4 Acute therapeutic management"),
                    cite(ESC_HTN, "§10.1.2"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Compelling conditions: aorta, heart, pregnancy, drugs",
                timing = "Immediate lowering",
                points = listOf(
                    "Aortic dissection: SBP <120 mm Hg and HR <60/min immediately (ESC Council 2019; ISH 2020); ACC/AHA 2022: HR 60–80. Beta-blocker first — see aortic dissection.",
                    "Acute pulmonary oedema: SBP <140 mm Hg immediately — nitroprusside or nitroglycerin with a loop diuretic (ESC Council 2019; ISH 2020).",
                    "Acute coronary syndrome: SBP <140 mm Hg immediately — nitroglycerin first line, labetalol an alternative (ESC Council 2019; ISH 2020).",
                    "Severe pre-eclampsia or eclampsia: SBP <160 and DBP <105 mm Hg with IV labetalol or nicardipine plus magnesium (ESC 2024; ESC Council). ESC 2024: within 150–180 min.",
                    "ESC Council 2019 in pregnancy: labetalol ≤800 mg in 24 h (fetal bradycardia); nitroprusside contraindicated (fetal cyanide toxicity).",
                    "ISH 2020: cocaine or amphetamines — benzodiazepines first. Phaeochromocytoma — phentolamine; beta-blocker only after alpha-blockade.",
                ),
                citations = listOf(
                    cite(ESC_HE, "Table 3; acute aortic disease; acute coronary event; eclampsia and severe pre-eclampsia"),
                    cite(ISH, "Table 12; Specific situations"),
                    cite(AHA_AORTA, "Acute medical management of AAS Rec 2"),
                    cite(ESC_HTN, "§10.4.1; Rec Table 33"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Stroke and intracerebral haemorrhage",
                timing = "Before and for 24 h after reperfusion",
                points = listOf(
                    "Ischaemic stroke for thrombolysis: <185/110 before IVT and <180/105 after (AHA/ASA 2026; ESC 2024). ESC 2024: <180/105 for ≥24 h after IVT or thrombectomy.",
                    "Ischaemic stroke without reperfusion and BP ≥220/110: ESC 2024 — lower by about 15% in the first 24 h. ESC Council: >220/120 — MAP −15% in 1 h.",
                    "Intracerebral haemorrhage, ESC 2024: within 6 h, SBP target 140–160 mm Hg; if SBP ≥220, avoid a fall of more than 70 mm Hg within 1 h.",
                    "Intracerebral haemorrhage, AHA/ASA 2022: SBP 150–220 — target 140 (range 130–150) may be reasonable; below 130 is potentially harmful.",
                    "ESC Council 2019 and ISH 2020: labetalol or nicardipine first line for BP lowering in ischaemic and haemorrhagic stroke.",
                ),
                citations = listOf(
                    cite(AHA_AIS, "§4.3 Rec 5, 7"),
                    cite(ESC_HTN, "§10.2–10.3; Rec Table 32"),
                    cite(ESC_HE, "Table 3"),
                    cite(AHA_ICH, "§5.1 Rec 3, 5"),
                    cite(ISH, "Table 12"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "IV agents and doses",
                points = listOf(
                    "Labetalol: 0.25–0.5 mg/kg IV bolus, then 2–4 mg/min until goal BP, then 5–20 mg/h (ESC Council 2019).",
                    "Nicardipine: start 5 mg/h, increase by 2.5 mg/h every 15–30 min to max 15 mg/h; then reduce to 3 mg/h (ESC Council 2019).",
                    "Nitroglycerin 5–200 mcg/min, increasing 5 mcg/min every 5 min. Nitroprusside 0.3–10 mcg/kg/min, increasing 0.5 every 5 min (ESC Council 2019).",
                    "Esmolol 0.5–1 mg/kg IV bolus, then 50–300 mcg/kg/min. Metoprolol 2.5–5 mg IV over 2 min, repeat every 5 min, max 15 mg (ESC Council 2019).",
                    "ISH 2020: nitroglycerin and nitroprusside are specifically useful when the heart or aorta is involved.",
                ),
                checklistTitle = "Cautions (ESC Council 2019 Table 4)",
                checklist = listOf(
                    "Esmolol, labetalol, metoprolol: 2nd or 3rd-degree AV block, systolic heart failure, asthma, bradycardia",
                    "Labetalol: bronchoconstriction; fetal bradycardia",
                    "Nicardipine: liver failure; headache, reflex tachycardia",
                    "Nitroprusside: cyanide toxicity; relative contraindication in liver or kidney failure",
                    "Nitroglycerin: headache, reflex tachycardia",
                ),
                doses = listOf(
                    StepDose(
                        "Labetalol — bolus (ESC Council 2019)",
                        WeightDose(0.25, 0.5, "mg", per = "IV bolus"),
                        note = "Then 2–4 mg/min until goal BP, then 5–20 mg/h",
                    ),
                    StepDose(
                        "Esmolol — bolus (ESC Council 2019)",
                        WeightDose(0.5, 1.0, "mg", per = "IV bolus"),
                    ),
                    StepDose(
                        "Esmolol — infusion (ESC Council 2019)",
                        WeightDose(50.0, 300.0, "mcg", per = "per minute IV infusion"),
                    ),
                    StepDose(
                        "Nitroprusside (ESC Council 2019)",
                        WeightDose(0.3, 10.0, "mcg", per = "per minute IV infusion"),
                        note = "Increase by 0.5 mcg/kg/min every 5 min to goal BP",
                    ),
                ),
                citations = listOf(
                    cite(ESC_HE, "Table 4"),
                    cite(ISH, "§10.4 Acute therapeutic management"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Threshold differs: ICMR STW uses ≥180/120 mm Hg; ESC 2024 defines an emergency as ≥180/110 with acute organ damage; ISH 2020 and ESC Council 2019 set no threshold.",
                listOf(cite(ICMR_HTN, "Red flags signs"), cite(ESC_HTN, "§10.1.1"), cite(ISH, "§10.4"), cite(ESC_HE, "Clinical presentation")),
            ),
            ProtocolNote(
                "Hydralazine in pregnancy: ESC 2024 lists IV hydralazine as a second-line option; ESC Council 2019 does not recommend it because of adverse perinatal outcomes.",
                listOf(cite(ESC_HTN, "Rec Table 33"), cite(ESC_HE, "Eclampsia and severe pre-eclampsia")),
            ),
            ProtocolNote(
                "ESC 2024 magnesium sulfate for eclampsia or severe pre-eclampsia: 4 g IV over 5 min then 1 g/h, or 5 g IM into each buttock then 5 g IM every 4 h. Follow obstetric protocols for delivery.",
                listOf(cite(ESC_HTN, "§10.4.1")),
            ),
            ProtocolNote(
                "Most targets are expert consensus: ISH 2020 and ESC Council 2019 note a lack of randomised trials, except for BP lowering in acute stroke.",
                listOf(cite(ISH, "§10.4 Acute therapeutic management"), cite(ESC_HE, "Acute management of hypertensive emergencies")),
            ),
            ProtocolNote(
                "The 2025 AHA/ACC high blood pressure guideline (which replaced the 2017 ACC/AHA guideline) could not be accessed for this review; its hypertensive-emergency recommendations are not summarised here.",
                listOf(cite(AHA_HBP, "Not reviewed — full text inaccessible")),
            ),
        ),
        citations = listOf(
            cite(ICMR_HTN), cite(ESC_HTN), cite(ESC_HE), cite(ISH), cite(AHA_AIS), cite(AHA_ICH), cite(AHA_AORTA),
        ),
    ),
    // =============================================================================================== Aortic dissection
    Protocol(
        id = "aortic-dissection",
        category = ProtocolCategory.Cardiac,
        title = "Acute aortic dissection",
        summary = "ADD-RS and D-dimer, CT imaging, anti-impulse therapy (beta-blocker before vasodilator, HR and SBP " +
            "targets), type A surgery, type B TEVAR and what to avoid — ICMR STW, ACC/AHA 2022, ESC 2024.",
        keywords = listOf(
            "aortic dissection", "acute aortic syndrome", "AAS", "dissecting aneurysm", "type A", "type B", "Stanford",
            "TAAD", "TBAD", "IMH", "intramural haematoma", "PAU", "penetrating aortic ulcer", "tearing chest pain",
            "back pain", "pulse deficit", "ADD-RS", "D-dimer", "CT aortogram", "CTA", "widened mediastinum", "TEVAR",
            "esmolol", "labetalol", "anti-impulse",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Suspect it — ADD-RS",
                timing = "At first contact with chest, back or abdominal pain",
                points = listOf(
                    "ACC/AHA 2022: abrupt, severe, sharp or stabbing chest, back or abdominal pain, maximal at onset; 'tearing' is classic but less common. ICMR STW: pain may migrate.",
                    "ACC/AHA 2022: measure BP in both arms and both legs; listen for aortic regurgitation. ESC 2024: about 6.4% of type A dissections are painless.",
                    "ADD-RS: one point for each category with any feature present — condition, pain, examination; total 0–3 (ACC/AHA 2022; ESC 2024).",
                    "ACC/AHA 2022: ADD-RS 0 low, 1 moderate, 2–3 high risk. ESC 2024: ≥2 high risk, <2 low risk.",
                    "ICMR STW: may present as MI (coronary malperfusion), stroke, syncope, paraplegia, limb, renal or mesenteric ischaemia, shock or tamponade.",
                ),
                checklistTitle = "ADD-RS features (ACC/AHA 2022 Table 24; ESC 2024 Fig. 30)",
                checklist = listOf(
                    "Condition: Marfan syndrome or other connective tissue disease",
                    "Condition: family history of aortic disease; known aortic valve disease",
                    "Condition: recent aortic manipulation; known aortic aneurysm",
                    "Pain: chest, back or abdominal — abrupt onset, severe, or ripping/tearing",
                    "Exam: pulse deficit or systolic BP differential",
                    "Exam: focal neurological deficit with pain; new aortic regurgitation murmur",
                    "Exam (ESC 2024): haemodynamic instability — hypotension or shock",
                ),
                citations = listOf(
                    cite(AHA_AORTA, "AAS presentation; Tables 22 and 24"),
                    cite(ESC_PAAD, "§9.3.1.2; Fig. 30"),
                    cite(ICMR_AAS, "Clinical presentation and complications; MI vs acute type A dissection"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Rule in or out: D-dimer and imaging",
                timing = "CT without delay if ADD-RS ≥2 (ESC 2024)",
                points = listOf(
                    "ESC 2024: use the ADD-RS-based algorithm. ADD-RS ≥2 — CT neck to pelvis without delay, plus focused TTE and ECG.",
                    "ESC 2024: ADD-RS <2 — chest X-ray, labs, point-of-care ultrasound and D-dimer; CT if any is positive. D-dimer <500 ng/mL makes dissection unlikely.",
                    "ACC/AHA 2022: low pretest probability with D-dimer <500 ng/mL makes AAS unlikely. ICMR STW: intramural haematoma or ulcer may still be present.",
                    "ESC 2024: ECG-gated CT neck to pelvis is first line; ACC/AHA 2022: CT first. Unstable — TOE (ESC 2024) or bedside echo (ACC/AHA); MRI if CT is unavailable.",
                    "ACC/AHA 2022: chest X-ray is not sensitive or specific enough to diagnose AAS, though a widened mediastinum may raise suspicion.",
                ),
                citations = listOf(
                    cite(ESC_PAAD, "Rec Table 44; Fig. 30; §9.3.1.3"),
                    cite(AHA_AORTA, "AAS diagnostic evaluation Rec 1–2 and synopsis; Table 23"),
                    cite(ICMR_AAS, "Imaging and diagnostic algorithm"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Anti-impulse therapy",
                timing = "Immediately, before transfer or surgery",
                points = listOf(
                    "Targets: ESC 2024 — SBP <120 mm Hg and HR ≤60/min; ACC/AHA 2022 — SBP <120 or lowest BP keeping organ perfusion, HR 60–80; ICMR STW — SBP 100–120, HR 60–70.",
                    "Beta-blocker first: IV labetalol or esmolol (ESC 2024); ICMR STW also lists metoprolol. If contraindicated: IV diltiazem or verapamil (ACC/AHA 2022; ESC 2024).",
                    "Then a vasodilator — nicardipine, nitroprusside or a nitrate — only if SBP stays high after rate control (ACC/AHA 2022; ESC 2024).",
                    "Arterial line, continuous ECG and ICU admission (ESC 2024; ACC/AHA 2022). IV opioid analgesia helps reach targets.",
                    "ICMR STW: significant aortic regurgitation — MAP 70–80 mm Hg and HR near 80/min. ESC 2024: tolerate higher BP with malperfusion, spinal or brain ischaemia.",
                    "ICMR STW: labetalol 20 mg IV over 2 min, then 1–2 mg/min; metoprolol 5 mg IV over 2 min, up to 3 doses; nicardipine 2.5–15 mg/h; morphine 2–10 mg IV.",
                ),
                doses = listOf(
                    StepDose(
                        "Esmolol — loading (ICMR STW)",
                        WeightDose(250.0, 500.0, "mcg", per = "IV over 1 min"),
                    ),
                    StepDose(
                        "Esmolol — maintenance (ICMR STW)",
                        WeightDose(50.0, 100.0, "mcg", per = "per minute IV infusion"),
                        note = "Up to 300 mcg/kg/min",
                    ),
                    StepDose(
                        "Diltiazem — loading, if beta-blocker contraindicated (ICMR STW)",
                        WeightDose(0.25, 0.25, "mg", per = "IV over 2–5 min"),
                        note = "Then 5 mg/h IV infusion",
                    ),
                    StepDose(
                        "Nitroprusside — after beta-blocker (ICMR STW)",
                        WeightDose(0.3, 0.5, "mcg", per = "per minute IV infusion"),
                        note = "Maximum up to 10 mcg/kg/min",
                    ),
                ),
                citations = listOf(
                    cite(ESC_PAAD, "§9.3.1.4.1; Rec Table 45; Fig. 31"),
                    cite(AHA_AORTA, "Acute medical management of AAS Rec 1–5 and supportive text"),
                    cite(ICMR_AAS, "Treatment of acute dissection; Antihypertensive drugs; Flow chart for medical management"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Type A: emergency surgery",
                timing = "Immediate surgical consultation",
                points = listOf(
                    "ACC/AHA 2022 and ESC 2024: suspected or confirmed acute type A — emergency surgical consultation and immediate surgery.",
                    "ESC 2024: medically treated mortality about 50% within 48 h (1–2% per hour). ACC/AHA 2022: unoperated mortality about 1% per hour.",
                    "Transfer to a high-volume aortic centre if this does not significantly delay surgery (ESC 2024; ACC/AHA 2022).",
                    "ACC/AHA 2022: renal, mesenteric or limb malperfusion — immediate ascending aortic repair; non-haemorrhagic stroke complicating type A — surgery is reasonable over medical therapy.",
                    "ESC 2024: if tamponade threatens arrest, emergency pericardial puncture may be considered as a temporary measure before theatre.",
                    "ICMR STW: refer to a specialised centre as soon as medical therapy starts. Exceptions to surgery: moribund, deeply comatose, advanced mesenteric ischaemia with gangrene or sepsis.",
                ),
                citations = listOf(
                    cite(AHA_AORTA, "Initial surgical considerations in acute type A Rec 1–3; Management of malperfusion Rec 1"),
                    cite(ESC_PAAD, "§9.3.1.4.2; Rec Table 46"),
                    cite(ICMR_AAS, "Surgery for acute type A dissection; Treatment of acute dissection"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Type B: medical first, TEVAR if complicated",
                points = listOf(
                    "Uncomplicated acute type B: medical therapy with pain relief and BP control (ACC/AHA 2022; ESC 2024); beta-blocker first line (ESC 2024).",
                    "Complicated type B: emergency intervention, TEVAR first line (ESC 2024). ACC/AHA 2022: endovascular repair recommended for rupture, reasonable for other complications.",
                    "High-risk uncomplicated type B: TEVAR may be considered (ACC/AHA 2022); ESC 2024 — should be considered in selected patients in the subacute phase (14–90 days).",
                    "ICMR STW: intramural haematoma and penetrating ulcer are radiological diagnoses managed like acute dissection.",
                ),
                checklistTitle = "Complicated type B (ACC/AHA 2022 Table 27; ICMR STW)",
                checklist = listOf(
                    "Rupture or leak — haemothorax, enlarging periaortic or mediastinal haematoma",
                    "Branch occlusion or malperfusion — visceral, renal, limb",
                    "Extension of the dissection, including retrograde type A",
                    "Rapid aortic enlargement (ICMR: aneurysmal dilatation >5.5 cm)",
                    "Intractable pain",
                    "Uncontrolled hypertension",
                    "Haemodynamic instability (ICMR)",
                ),
                citations = listOf(
                    cite(AHA_AORTA, "Management of acute type B aortic dissection Rec 1–3; Tables 27–28"),
                    cite(ESC_PAAD, "§9.3.1.4.3; Rec Table 49"),
                    cite(ICMR_AAS, "Management of type B dissection; Type B dissection requiring intervention; Management of IMH and PAU"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "What to avoid",
                points = listOf(
                    "ICMR STW: dissection can mimic MI through coronary malperfusion — differentiate first; dissection with ischaemia is managed as dissection.",
                    "ESC 2024: the initial work-up includes an ECG to exclude STEMI.",
                ),
                checklistTitle = "Don't",
                checklist = listOf(
                    "Fibrinolysis — suspected aortic dissection is an absolute contraindication (ACC/AHA 2025; ICMR STW)",
                    "Thrombolysis for ischaemia caused by dissection (ICMR STW)",
                    "A vasodilator before the beta-blocker or calcium-channel blocker — reflex tachycardia (ACC/AHA 2022; ESC 2024)",
                    "IV NSAIDs such as ketorolac — may raise BP and harm the kidneys (ACC/AHA 2022)",
                    "Beta-blockers without caution in acute aortic regurgitation, heart block or bradycardia (ACC/AHA 2022)",
                    "Catheter coronary angiography or aortography for diagnosis — not needed (ICMR STW)",
                ),
                citations = listOf(
                    cite(ACS25, "Table 14"),
                    cite(ICMR_STEMI, "Absolute contra-indications to thrombolytic therapy"),
                    cite(ICMR_AAS, "MI vs acute type A dissection; Imaging and diagnostic algorithm"),
                    cite(AHA_AORTA, "Acute medical management of AAS supportive text"),
                    cite(ESC_PAAD, "§9.3.1.4.1; Fig. 30"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Heart-rate targets differ: ESC 2024 ≤60/min; ACC/AHA 2022 60–80/min; ICMR STW 60–70/min (flowchart) or near 60/min (text), near 80/min with significant aortic regurgitation.",
                listOf(cite(ESC_PAAD, "Rec Table 45"), cite(AHA_AORTA, "Acute medical management of AAS Rec 2"), cite(ICMR_AAS, "Flow chart for medical management; Treatment of acute dissection")),
            ),
            ProtocolNote(
                "Esmolol loading differs: ICMR STW 250–500 mcg/kg over 1 min; ESC Council 2019 (hypertensive emergencies) 0.5–1 mg/kg bolus.",
                listOf(cite(ICMR_AAS, "Antihypertensive drugs"), cite(ESC_HE, "Table 4")),
            ),
            ProtocolNote(
                "D-dimer rule-out is only for low clinical probability: ESC 2024 and ACC/AHA 2022 combine it with the ADD-RS; ACC/AHA notes risk scores are not uniformly adopted.",
                listOf(cite(ESC_PAAD, "§9.3.1.3; Fig. 30"), cite(AHA_AORTA, "AAS diagnostic evaluation synopsis")),
            ),
            ProtocolNote(
                "First-line beta-blocker: ESC 2024 prefers labetalol (alpha and beta blockade) or esmolol; ICMR STW lists labetalol, esmolol or metoprolol.",
                listOf(cite(ESC_PAAD, "§9.3.1.4.1"), cite(ICMR_AAS, "Antihypertensive drugs")),
            ),
        ),
        citations = listOf(cite(ICMR_AAS), cite(AHA_AORTA), cite(ESC_PAAD), cite(ACS25), cite(ICMR_STEMI)),
    ),
)
