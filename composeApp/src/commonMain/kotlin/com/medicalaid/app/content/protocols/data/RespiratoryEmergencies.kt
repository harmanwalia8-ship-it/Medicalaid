package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.WeightDose
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.ProtocolCategory
import com.medicalaid.app.content.protocols.ProtocolNote
import com.medicalaid.app.content.protocols.ProtocolTool
import com.medicalaid.app.content.protocols.Step
import com.medicalaid.app.content.protocols.StepDose

/*
 * Respiratory emergencies and anaphylaxis: acute severe asthma, COPD exacerbation, pulmonary embolism, anaphylaxis.
 * Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them (checked against the documents,
 *   November 2026); kPa values are converted to mm Hg only where marked "≈" or where the source gives both.
 * - Every point is attributed ("BTS/SIGN 158: …", "GINA 2026: …", "ICMR STW: …", "ESC 2019: …", "RCUK 2021: …").
 * - Locators: BTS/SIGN 158 section/table/annex; GINA 2026 box and page; GOLD 2027 chapter 4 figure/heading and page;
 *   NICE recommendation numbers; ESC 2019 recommendation table titles and table numbers (read from the official ESC
 *   slide set, which reproduces them); RCUK 2021 section numbers; AHA 2025 recommendation number and page.
 * - The 2026 AHA/ACC acute PE guideline (Circulation 2026;153(12):e977–e1051) could not be retrieved in full text; only
 *   the official AHA/ACC news release summary is cited for it. ESC 2019 remains the current ESC PE guideline.
 * - Existing sources reused by id: gina-2026, icmr-stw-asthma-2019 (Sources.kt); em-ab-gold-2027, em-ab-bts-oxygen-2017,
 *   em-ab-bts-ics-ahrf-2016, em-ab-isccm-niv-2020, em-ab-icmr-stw-copd-2019 (AcidBaseDisorders.kt);
 *   em-resus-aha-special-2025 (DrugTox.kt); em-resus-erc-special-2025 (Resuscitation.kt); sc-nice-ng158
 *   (scores/data/NeuroCardio.kt); fda-label-apixaban, fda-label-rivaroxaban, fda-label-dabigatran
 *   (drugs/data/AnticoagulantsEndocrine.kt).
 */

internal val respiratoryEmergencySources: List<Source> = listOf(
    // ---------------------------------------------------------------- Asthma
    Source(
        "em-resp-bts-sign158-2024", Authority.BTS, "BTS/SIGN guideline SIGN 158 (November 2024 edition, amended May 2025)",
        "British guideline on the management of asthma", "2024",
        "https://www.sign.ac.uk/sign-158-british-guideline-on-the-management-of-asthma",
        status = "Acute asthma (section 9) is retained here — the 2024 BTS/NICE/SIGN guideline (NG245/SIGN 245) does not " +
            "cover acute asthma; no new evidence review in 2024; May 2025 amendment to the life-threatening criteria",
    ),
    Source(
        "em-resp-ics-nccp-asthma-2015", Authority.Paper, "ICS/NCCP(I) joint guideline — Lung India 2015;32(Suppl 1):S3–S42",
        "Guidelines for diagnosis and management of bronchial asthma: Joint ICS/NCCP (I) recommendations", "2015",
        "https://doi.org/10.4103/0970-2113.154517",
        status = "Indian Chest Society and National College of Chest Physicians (India); cited by the ICMR asthma STW",
    ),
    // ---------------------------------------------------------------- COPD
    Source(
        "em-resp-ics-nccp-copd-2013", Authority.Paper, "ICS/NCCP(I) joint guideline — Lung India 2013;30(3):228–267",
        "Guidelines for diagnosis and management of chronic obstructive pulmonary disease: Joint ICS/NCCP (I) recommendations",
        "2013",
        "https://doi.org/10.4103/0970-2113.116248",
        status = "Indian Chest Society and National College of Chest Physicians (India); cited by the ICMR COPD STW",
    ),
    Source(
        "em-resp-nice-ng115", Authority.NICE, "Guideline NG115",
        "Chronic obstructive pulmonary disease in over 16s: diagnosis and management", "2018",
        "https://www.nice.org.uk/guidance/ng115",
        status = "Last updated 26 July 2019",
    ),
    Source(
        "em-resp-nice-ng114", Authority.NICE, "Guideline NG114",
        "Chronic obstructive pulmonary disease (acute exacerbation): antimicrobial prescribing", "2018",
        "https://www.nice.org.uk/guidance/ng114",
        status = "Fluoroquinolone wording reflects MHRA January 2024 advice",
    ),
    // ---------------------------------------------------------------- Pulmonary embolism
    Source(
        "em-resp-esc-pe-2019", Authority.ESC, "ESC guideline — Eur Heart J 2020;41(4):543–603",
        "2019 ESC Guidelines for the diagnosis and management of acute pulmonary embolism developed in collaboration " +
            "with the European Respiratory Society (ERS)",
        "2019",
        "https://doi.org/10.1093/eurheartj/ehz405",
        status = "Current ESC PE guideline (checked November 2026). Recommendation tables and Tables 4 and 9–13 reviewed " +
            "from the official ESC slide set; supplementary data not reviewed",
    ),
    Source(
        "em-resp-aha-acc-pe-2026-news", Authority.AHA, "AHA/ACC news release, 19 February 2026",
        "First AHA/ACC acute pulmonary embolism guideline: prompt diagnosis and treatment are key", "2026",
        "https://newsroom.heart.org/news/first-ahaacc-acute-pulmonary-embolism-guideline-prompt-diagnosis-and-treatment-are-key",
        status = "Official summary of the 2026 AHA/ACC/ACCP/ACEP/CHEST/SCAI/SHM/SIR/SVM/SVN guideline (Circulation " +
            "2026;153(12):e977–e1051, doi:10.1161/CIR.0000000000001415; erratum 2026;154(2):e24), whose full text " +
            "could not be retrieved for review",
    ),
    Source(
        "em-resp-nams-vte-2024", Authority.Paper,
        "NAMS (India) task force report — Ann Natl Acad Med Sci (India) 2024;60(1):34–70",
        "NAMS task force report on Venous thromboembolism", "2024",
        "https://doi.org/10.25259/ANAMS_TFR_01_2024",
        status = "National Academy of Medical Sciences (India); report approved by DGHS and MoHFW, Government of India. " +
            "No ICMR STW for pulmonary embolism found (checked November 2026)",
    ),
    Source(
        "em-resp-raschke-1993", Authority.Paper, "RCT, two US teaching hospitals (Ann Intern Med 1993;119(9):874–881)",
        "The weight-based heparin dosing nomogram compared with a \"standard care\" nomogram: a randomized controlled trial",
        "1993",
        "https://doi.org/10.7326/0003-4819-119-9-199311010-00002",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "em-resp-fda-edoxaban", Authority.FDA, "Prescribing information (DailyMed; Daiichi Sankyo)",
        "SAVAYSA (edoxaban) tablets", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=e77d3400-56ad-11e3-949a-0800200c9a66",
        status = "US label, version effective 10 July 2025",
    ),
    Source(
        "em-resp-fda-alteplase", Authority.FDA, "Prescribing information (DailyMed; Genentech)",
        "ACTIVASE (alteplase) for injection", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=c669f77c-fa48-478b-a14b-80b20a0139c2",
        status = "US label, version effective 20 January 2026",
    ),
    // ---------------------------------------------------------------- Anaphylaxis
    Source(
        "em-resp-rcuk-anaphylaxis-2021", Authority.RCUK, "Guideline (Resuscitation Council UK)",
        "Emergency treatment of anaphylaxis: Guidelines for healthcare providers", "2021",
        "https://www.resus.org.uk/library/additional-guidance/guidance-anaphylaxis/emergency-treatment-anaphylactic-reactions",
        status = "May 2021; still the current RCUK version (checked November 2026); includes the refractory anaphylaxis algorithm",
    ),
    Source(
        "em-resp-wao-2020", Authority.WAO, "WAO position paper — World Allergy Organ J 2020;13(10):100472",
        "World Allergy Organization Anaphylaxis Guidance 2020", "2020",
        "https://doi.org/10.1016/j.waojou.2020.100472",
    ),
    Source(
        "em-resp-eaaci-2021", Authority.Paper, "EAACI guideline — Allergy 2022;77(2):357–377",
        "EAACI guidelines: Anaphylaxis (2021 update)", "2021",
        "https://doi.org/10.1111/all.15032",
        status = "European Academy of Allergy and Clinical Immunology; GRADE. An update was planned for 2026 but none was " +
            "found (checked November 2026); accepted manuscript reviewed",
    ),
    Source(
        "em-resp-nice-ng258", Authority.NICE, "Guideline NG258",
        "Anaphylaxis: assessment and referral after emergency treatment", "2026",
        "https://www.nice.org.uk/guidance/ng258",
        status = "Published 27 May 2026; replaces CG134; observation periods adapted from RCUK 2021",
    ),
    Source(
        "em-resp-icmr-stw-urticaria-2022", Authority.ICMR, "Standard Treatment Workflow (ICD-10 L50.9)",
        "Urticaria and Angioedema", "2022",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725967365_urticaria_and_angioedema.pdf",
        status = "July 2022; ICMR has no separate anaphylaxis STW (STW list checked November 2026)",
    ),
)

// Asthma
private const val RE_SIGN = "em-resp-bts-sign158-2024"
private const val RE_GINA = "gina-2026"
private const val RE_ICMR_ASTHMA = "icmr-stw-asthma-2019"
private const val RE_ICS_ASTHMA = "em-resp-ics-nccp-asthma-2015"

// COPD
private const val RE_GOLD = "em-ab-gold-2027"
private const val RE_NG115 = "em-resp-nice-ng115"
private const val RE_NG114 = "em-resp-nice-ng114"
private const val RE_ICMR_COPD = "em-ab-icmr-stw-copd-2019"
private const val RE_ICS_COPD = "em-resp-ics-nccp-copd-2013"
private const val RE_ISCCM_NIV = "em-ab-isccm-niv-2020"

// Shared respiratory
private const val RE_BTS_O2 = "em-ab-bts-oxygen-2017"
private const val RE_BTS_ICS = "em-ab-bts-ics-ahrf-2016"

// Pulmonary embolism
private const val RE_ESC_PE = "em-resp-esc-pe-2019"
private const val RE_AHA_PE = "em-resp-aha-acc-pe-2026-news"
private const val RE_NG158 = "sc-nice-ng158"
private const val RE_NAMS = "em-resp-nams-vte-2024"
private const val RE_RASCHKE = "em-resp-raschke-1993"
private const val RE_APIXABAN = "fda-label-apixaban"
private const val RE_RIVAROXABAN = "fda-label-rivaroxaban"
private const val RE_DABIGATRAN = "fda-label-dabigatran"
private const val RE_EDOXABAN = "em-resp-fda-edoxaban"
private const val RE_ALTEPLASE = "em-resp-fda-alteplase"

// Anaphylaxis
private const val RE_RCUK = "em-resp-rcuk-anaphylaxis-2021"
private const val RE_WAO = "em-resp-wao-2020"
private const val RE_EAACI = "em-resp-eaaci-2021"
private const val RE_NG258 = "em-resp-nice-ng258"
private const val RE_ICMR_URT = "em-resp-icmr-stw-urticaria-2022"

// Resuscitation guidelines (special circumstances)
private const val RE_AHA_SPECIAL = "em-resus-aha-special-2025"
private const val RE_ERC_SPECIAL = "em-resus-erc-special-2025"

// ================================================================================================ Acute severe asthma
private val reAsthma = Protocol(
    id = "acute-severe-asthma",
    category = ProtocolCategory.Respiratory,
    title = "Acute severe asthma",
    summary = "Severity grading, oxygen, bronchodilators, steroids, IV magnesium, the place of aminophylline, ICU triggers " +
        "and discharge — attributed to BTS/SIGN 158, GINA 2026, ICS/NCCP, ICMR STW and AHA 2025.",
    keywords = listOf(
        "acute severe asthma", "status asthmaticus", "acute asthma", "asthma attack", "asthma exacerbation",
        "acute exacerbation of bronchial asthma", "AEBA", "bronchial asthma", "wheeze", "wheezing", "bronchospasm",
        "life-threatening asthma", "near-fatal asthma", "silent chest", "PEF", "PEFR", "peak flow", "salbutamol",
        "albuterol", "levosalbutamol", "levalbuterol", "SABA", "ipratropium", "SAMA", "nebulisation", "nebulization",
        "MDI spacer", "magnesium sulphate", "magnesium sulfate", "MgSO4", "aminophylline", "theophylline",
        "hydrocortisone", "prednisolone", "steroids", "hypercapnia",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Grade the severity",
            timing = "On arrival — measure PEF and SpO₂ (BTS/SIGN 158)",
            points = listOf(
                "BTS/SIGN 158: moderate — increasing symptoms, PEF >50–75% best or predicted, no acute severe features. Use % predicted only if best PEF (within 2 years) is unknown.",
                "BTS/SIGN 158: acute severe — any one of PEF 33–50%, respiratory rate ≥25/min, heart rate ≥110/min, or inability to complete sentences in one breath.",
                "GINA 2026: severe — any of: can't speak, drink or lie down; SpO₂ <92% on air; RR >30/min; accessory muscles; silent or quiet chest; PEF/FEV₁ <50%.",
                "ICMR STW: severe (admit) — incomplete sentences, agitation, accessory muscles, RR >30, HR >110, pulsus paradoxus >25 mm Hg, silent chest and/or SpO₂ <92% on air.",
                "ICMR STW: life-threatening — altered sensorium, orthopnoea, cyanosis, paradoxical breathing, hypotension or HR <60/min: refer at once to a centre with ICU.",
                "GINA 2026: consider other causes — anaphylaxis, pneumothorax, inducible laryngeal obstruction, pneumonia, acute heart failure, pulmonary embolism.",
            ),
            checklistTitle = "BTS/SIGN 158 (May 2025): life-threatening and near-fatal asthma",
            checklist = listOf(
                "Life-threatening = SpO₂ <92% PLUS any one of the following in a patient with severe asthma",
                "Altered conscious level, exhaustion or poor respiratory effort",
                "Arrhythmia or hypotension",
                "Cyanosis or silent chest",
                "PEF <33% best or predicted",
                "PaO₂ <8 kPa (60 mm Hg)",
                "'Normal' PaCO₂ 4.6–6.0 kPa (≈35–45 mm Hg)",
                "Near-fatal = raised PaCO₂ and/or needing mechanical ventilation with raised inflation pressures",
            ),
            citations = listOf(
                cite(RE_SIGN, "§9.2.1; Table 15 (p.36), 2025 amendment; Annex 4"),
                cite(RE_GINA, "Box 9-6 (p.186)"),
                cite(RE_ICMR_ASTHMA, "Disease exacerbation — severe acute asthma; life-threatening exacerbation"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Measure, monitor and investigate",
            timing = "Blood gas if SpO₂ <92% or any life-threatening feature (BTS/SIGN 158)",
            points = listOf(
                "BTS/SIGN 158: arterial gas if SpO₂ <92% (on air or oxygen) or any life-threatening feature — oximetry does not detect hypercapnia.",
                "GINA 2026: consider gases if PEF or FEV₁ <50% or not responding; PaO₂ <60 mm Hg with normal or raised PaCO₂ (especially >45 mm Hg) means respiratory failure.",
                "BTS/SIGN 158: record PEF 15–30 min after starting treatment; repeat gases within 1 h if PaO₂ <8 kPa (unless SpO₂ >92%), PaCO₂ normal or raised, or worsening.",
                "BTS/SIGN 158: measure potassium and glucose — β₂ agonists and steroids can cause hypokalaemia, which must be corrected.",
                "BTS/SIGN 158: chest X-ray not routine — only for suspected pneumothorax, pneumomediastinum or consolidation, life-threatening asthma, poor response or ventilation. GINA 2026: likewise.",
                "BTS/SIGN 158: pulsus paradoxus is an inadequate severity indicator and should not be used. GINA 2026: oximeters may overestimate SpO₂ in darker skin.",
            ),
            citations = listOf(
                cite(RE_SIGN, "Table 16; §9.3.10; §9.4; Annex 4"),
                cite(RE_GINA, "Objective assessments (pp.184–185)"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Oxygen",
            timing = "Immediately if hypoxaemic",
            points = listOf(
                "BTS/SIGN 158: controlled oxygen for hypoxaemia, titrated to SpO₂ 94–98%; do not delay it if no oximeter. BTS 2017: 94–98% in acute asthma.",
                "GINA 2026: oxygen if SpO₂ <92%; in severe adult attacks controlled oxygen to 93–95% gives better physiological outcomes than 100%; never withhold for lack of oximetry.",
                "GINA 2026 Box 9-6: life-threatening — call for help; IV access, 100% oxygen by non-rebreather mask, IV magnesium, chest X-ray, continuous monitoring.",
                "ICS/NCCP 2015: oxygen only if hypoxaemic, SpO₂ 93–95% (>95% in pregnancy); watch PaCO₂ if >8 L/min is needed. ICMR STW: SpO₂ 92–95%.",
                "BTS/SIGN 158: oxygen-driven nebulisers preferred. BTS 2017: drive them at >6 L/min; if the cylinder cannot, use an air nebuliser plus nasal oxygen 2–6 L/min.",
            ),
            citations = listOf(
                cite(RE_SIGN, "§9.3.1; §9.3.2"),
                cite(RE_BTS_O2, "F1; R1"),
                cite(RE_GINA, "Treatment in acute care settings — oxygen (p.185); Box 9-6"),
                cite(RE_ICS_ASTHMA, "Recommendation 21"),
                cite(RE_ICMR_ASTHMA, "Severe acute asthma"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Inhaled bronchodilators",
            timing = "At once; reassess at 15–20 min and 1 h",
            points = listOf(
                "BTS/SIGN 158: acute severe or life-threatening — salbutamol 5 mg by oxygen-driven nebuliser; repeat every 15–30 min, or continuous 5–10 mg/h if poor response.",
                "BTS/SIGN 158: add nebulised ipratropium 0.5 mg 4–6 hourly in acute severe or life-threatening asthma, or after a poor initial β₂ response.",
                "GINA 2026 (severe): salbutamol 6–10 puffs plus ipratropium 4 puffs by pMDI-spacer, or salbutamol 5 mg plus ipratropium 0.25 mg nebulised; up to 3 times, 20–30 min apart.",
                "ICMR STW: nebulised levosalbutamol/ipratropium 1.25 mg/0.5 mg, three doses 20 min apart, then 4–6 hourly. ICS/NCCP 2015: ipratropium 500 µg once, then 250 µg 4–6 hourly.",
                "BTS/SIGN 158: moderate — β₂ agonist by spacer, one puff every 60 s up to 10 puffs. Higher boluses (e.g. 10 mg salbutamol) are unlikely to help.",
                "BTS/SIGN 158: IV β₂ agonists only if inhaled therapy cannot be used reliably; consider lactate. GINA 2026: watch for SABA toxicity — tachycardia, hypokalaemia, lactic acidosis.",
            ),
            citations = listOf(
                cite(RE_SIGN, "§9.3.2; §9.3.4; Annex 4"),
                cite(RE_GINA, "Box 9-6; Inhaled SABA, SABA toxicity and ipratropium (p.185)"),
                cite(RE_ICMR_ASTHMA, "Severe acute asthma"),
                cite(RE_ICS_ASTHMA, "Recommendation 22"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Systemic corticosteroids",
            timing = "Within 1 h of presentation (GINA 2026)",
            points = listOf(
                "BTS/SIGN 158: steroids for every acute attack — prednisolone 40–50 mg daily, or IV hydrocortisone 100 mg 6-hourly (400 mg/day) if oral is not possible.",
                "BTS/SIGN 158: continue prednisolone 40–50 mg daily for at least 5 days or until recovery; stop without tapering if on inhaled steroids (unless on maintenance steroids).",
                "GINA 2026: prednisolone 50 mg as a morning dose or hydrocortisone 200 mg/day in divided doses; oral as effective as IV; 5–7 days as effective as 10–14.",
                "ICMR STW: IV hydrocortisone 200 mg, then oral prednisolone 0.5 mg/kg daily for 5 days. ICS/NCCP 2015: prednisolone 30–40 mg/day (or equivalent) for 5–7 days.",
                "BTS/SIGN 158: do not stop inhaled corticosteroids during the oral course. GINA 2026: IM steroids show no benefit over oral.",
            ),
            doses = listOf(
                StepDose(
                    "Prednisolone — ICMR STW",
                    WeightDose(0.5, 0.5, unit = "mg", per = "orally once daily for 5 days"),
                    note = "ICMR STW: after IV hydrocortisone 200 mg",
                ),
            ),
            citations = listOf(
                cite(RE_SIGN, "§9.3.3"),
                cite(RE_GINA, "Systemic corticosteroids (p.187)"),
                cite(RE_ICMR_ASTHMA, "Severe acute asthma"),
                cite(RE_ICS_ASTHMA, "Recommendation 23"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Second line: IV magnesium; aminophylline",
            timing = "Poor response to initial nebulised treatment",
            points = listOf(
                "BTS/SIGN 158: consider a single IV magnesium sulfate 1.2–2 g over 20 min in acute severe asthma (PEF <50%) responding poorly — after senior consultation.",
                "GINA 2026: IV magnesium 2 g over 20 min if failing initial treatment with persistent hypoxaemia; not routine. BTS/SIGN 158 and GINA: nebulised magnesium not recommended.",
                "ICS/NCCP 2015: no routine role for IV or inhaled magnesium; 2 g IV over 20 min only in exceptional cases unresponsive to SABA, SAMA and steroids.",
                "BTS/SIGN 158: repeated IV magnesium is untested and may cause hypermagnesaemia with weakness and respiratory fatigue.",
                "BTS/SIGN 158: IV aminophylline only after senior consultation, for rare near-fatal or life-threatening poor responders; check levels if already on theophylline.",
                "GINA 2026: IV aminophylline and theophylline should not be used. ICS/NCCP 2015: widely used in India, but evidence does not favour it.",
            ),
            checklistTitle = "Do not (GINA 2026; BTS/SIGN 158)",
            checklist = listOf(
                "Sedatives of any kind — linked to avoidable asthma deaths",
                "Routine antibiotics — infection is usually viral",
                "Nebulised magnesium",
                "Heliox outside a trial",
                "Leukotriene receptor antagonists — evidence does not support use",
                "Nebulised furosemide — no shown benefit",
            ),
            doses = listOf(
                StepDose(
                    "Aminophylline loading — BTS/SIGN 158",
                    WeightDose(5.0, 5.0, unit = "mg", per = "IV over 20 min"),
                    note = "Omit the loading dose if on maintenance oral theophylline or aminophylline",
                ),
                StepDose(
                    "Aminophylline infusion — BTS/SIGN 158",
                    WeightDose(0.5, 0.7, unit = "mg", per = "per hour IV"),
                    note = "Check levels daily; if continued >24 h aim for 10–20 mg/L",
                ),
            ),
            citations = listOf(
                cite(RE_SIGN, "§9.3.5–9.3.9; §9.3.11; §9.4; Annex 5"),
                cite(RE_GINA, "Other treatments; aminophylline; sedatives (pp.187–188)"),
                cite(RE_ICS_ASTHMA, "Recommendations 22, 24–26, 28; aminophylline evidence review"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "ICU, NIV and intubation",
            timing = "Call anaesthesia/ICU early if not improving (BTS/SIGN 158)",
            points = listOf(
                "BTS/SIGN 158: alert anaesthetists and intensivists early if no improvement; a doctor able to intubate must accompany any transfer to ICU.",
                "BTS/SIGN 158: worsening hypoxia or hypercapnia, drowsiness, unconsciousness or respiratory arrest need intubation by an anaesthetist or ICU consultant.",
                "NIV — BTS/ICS 2016: not for acute asthma with hypercapnic failure. BTS/SIGN 158: only in ICU. GINA 2026: no recommendation; never sedate to allow NIV.",
                "AHA 2025: sudden rise in peak inspiratory pressure or difficult ventilation should prompt evaluation for tension pneumothorax.",
                "AHA 2025: ventilate with low rate and tidal volume; ECLS is reasonable and volatile anaesthetics may be considered if refractory. BTS/SIGN 158: ECMO where available.",
            ),
            checklistTitle = "BTS/SIGN 158: ICU/HDU if failing treatment with",
            checklist = listOf(
                "Deteriorating PEF",
                "Persisting or worsening hypoxia",
                "Hypercapnia",
                "Falling pH (rising H⁺) on blood gas",
                "Exhaustion, feeble respiration",
                "Drowsiness, confusion, altered consciousness",
                "Respiratory arrest",
            ),
            citations = listOf(
                cite(RE_SIGN, "§9.3.12; §9.3.13"),
                cite(RE_BTS_ICS, "Rec 39"),
                cite(RE_GINA, "Non-invasive ventilation (p.187)"),
                cite(RE_AHA_SPECIAL, "Asthma — Rec 1–3, 5 (S586)"),
                cite(RE_ICS_ASTHMA, "Recommendation 27"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Admit or discharge",
            points = listOf(
                "BTS/SIGN 158: admit any life-threatening or near-fatal feature, or acute severe features persisting after initial treatment.",
                "BTS/SIGN 158: PEF >75% 1 h after treatment — may go home unless ongoing symptoms, adherence or social concerns, prior near-fatal attack, night presentation or pregnancy.",
                "GINA 2026: discharge when 'mild' criteria hold 1–2 h after last salbutamol, SpO₂ ≥92% on air, PEF/FEV₁ >70%; admit if SABA needed more than every 1–2 h.",
                "ICMR STW: discharge when wheeze is absent or much reduced, HR <100, RR <30 and SpO₂ >94% on air; outpatient review at one week.",
                "GINA 2026: prednisolone 40–50 mg/day for 5–7 days; start or step up ICS-containing treatment, preferably ICS-formoterol MART; follow-up within 2–7 days.",
                "BTS/SIGN 158: GP review within 2 working days and respiratory review at about 1 month; check inhaler technique and give a written action plan.",
            ),
            checklistTitle = "BTS/SIGN 158: PEF >75% at 1 h, but admission may be appropriate if",
            checklist = listOf(
                "Still significant symptoms",
                "Concerns about adherence",
                "Living alone or socially isolated",
                "Psychological problems, physical disability or learning difficulties",
                "Previous near-fatal attack",
                "Attack despite an adequate dose of oral steroid before presentation",
                "Presentation at night",
                "Pregnancy",
            ),
            citations = listOf(
                cite(RE_SIGN, "§9.2.6; §9.6; Annex 4"),
                cite(RE_GINA, "Box 9-6; Discharge planning (p.189); Box 9-7 (p.191)"),
                cite(RE_ICMR_ASTHMA, "Severe acute asthma — discharge"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Life-threatening definition: the May 2025 BTS/SIGN 158 amendment requires SpO₂ <92% plus another feature, but its " +
                "ED algorithm (Annex 4) still lists SpO₂ <92% as a stand-alone feature. GINA and ICMR STW use clinical features.",
            listOf(cite(RE_SIGN, "Table 15; Annex 4"), cite(RE_GINA, "Box 9-6"), cite(RE_ICMR_ASTHMA, "Life-threatening exacerbation")),
        ),
        ProtocolNote(
            "Oxygen targets differ: BTS/SIGN 158 94–98%; GINA 2026 92–95% (93–95% in severe adult exacerbations); ICS/NCCP 2015 93–95%; ICMR STW 92–95%.",
            listOf(
                cite(RE_SIGN, "§9.3.1"), cite(RE_GINA, "p.185; Box 9-6"),
                cite(RE_ICS_ASTHMA, "Recommendation 21"), cite(RE_ICMR_ASTHMA, "Severe acute asthma"),
            ),
        ),
        ProtocolNote(
            "Nebulised ipratropium dose differs: BTS/SIGN 158 and ICMR STW 0.5 mg; GINA 2026 0.25 mg; ICS/NCCP 2015 500 µg first, then 250 µg.",
            listOf(
                cite(RE_SIGN, "§9.3.4"), cite(RE_GINA, "Box 9-6"),
                cite(RE_ICMR_ASTHMA, "Severe acute asthma"), cite(RE_ICS_ASTHMA, "Recommendation 22"),
            ),
        ),
        ProtocolNote(
            "Steroid doses differ: BTS/SIGN 158 prednisolone 40–50 mg; GINA 2026 50 mg (40–50 mg at discharge); ICS/NCCP 2015 " +
                "30–40 mg; ICMR STW 0.5 mg/kg after IV hydrocortisone 200 mg.",
            listOf(
                cite(RE_SIGN, "§9.3.3"), cite(RE_GINA, "p.187; Box 9-7"),
                cite(RE_ICS_ASTHMA, "Recommendation 23"), cite(RE_ICMR_ASTHMA, "Severe acute asthma"),
            ),
        ),
        ProtocolNote(
            "Aminophylline is a real point of disagreement: BTS/SIGN 158 allows it after senior consultation in rare poor " +
                "responders; GINA 2026 says it should not be used; ICS/NCCP 2015 notes its wide use in India without supporting evidence.",
            listOf(cite(RE_SIGN, "§9.3.6"), cite(RE_GINA, "p.188"), cite(RE_ICS_ASTHMA, "Aminophylline evidence review")),
        ),
        ProtocolNote(
            "Pulsus paradoxus: ICMR STW lists >25 mm Hg as a severity feature; BTS/SIGN 158 says it is inadequate and should not be used.",
            listOf(cite(RE_ICMR_ASTHMA, "Severe acute asthma"), cite(RE_SIGN, "Table 16")),
        ),
        ProtocolNote(
            "Scope: BTS/SIGN 158 carried its acute-asthma section into the 2024 edition without a new evidence review; the " +
                "2024 BTS/NICE/SIGN guideline (NG245/SIGN 245) does not cover acute asthma.",
            listOf(cite(RE_SIGN, "§1.1.2; §1.2.4")),
        ),
    ),
    citations = listOf(
        cite(RE_SIGN), cite(RE_GINA), cite(RE_ICMR_ASTHMA), cite(RE_ICS_ASTHMA), cite(RE_BTS_O2), cite(RE_BTS_ICS),
        cite(RE_AHA_SPECIAL),
    ),
)

// ================================================================================================ COPD exacerbation
private val reCopd = Protocol(
    id = "copd-exacerbation",
    category = ProtocolCategory.Respiratory,
    title = "COPD exacerbation",
    summary = "Severity and setting, controlled oxygen, bronchodilators, prednisolone, antibiotics, NIV and escalation — " +
        "attributed to GOLD 2027, NICE, BTS, ICS/NCCP, ISCCM and ICMR STW.",
    keywords = listOf(
        "COPD exacerbation", "AECOPD", "acute exacerbation of COPD", "COPD", "COAD", "chronic obstructive airway disease",
        "chronic bronchitis", "emphysema", "type 2 respiratory failure", "type II respiratory failure", "hypercapnia",
        "CO2 retention", "CO2 narcosis", "NIV", "BiPAP", "NIPPV", "HFNC", "HFNT", "high-flow nasal oxygen",
        "controlled oxygen", "Venturi mask", "88-92%", "ipratropium", "salbutamol", "levosalbutamol", "prednisolone",
        "antibiotics", "purulent sputum", "Rome classification", "cor pulmonale", "biomass smoke",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Confirm the exacerbation and grade severity",
            timing = "At first contact",
            points = listOf(
                "GOLD 2027: worsening dyspnoea, cough and sputum over ≤14 days; exclude mimics — pneumonia, pulmonary embolism, acute heart failure, arrhythmia or MI, pneumothorax.",
                "GOLD 2027 (Rome): moderate if ≥3 of dyspnoea VAS ≥5, RR ≥24/min, HR ≥95/min, SaO₂ <92% or a fall >3%, CRP ≥10 mg/L.",
                "GOLD 2027 (Rome): severe — as moderate, with PaO₂ ≤60 mm Hg and/or PaCO₂ >45 mm Hg with pH <7.35 on blood gas.",
                "ICMR STW: severe — cyanosis, RR >30/min, HR >110/min, SBP <90 mm Hg, SpO₂ <90%, paradoxical breathing, altered sensorium, asterixis or severe comorbidity.",
                "NICE NG115: in hospital — chest X-ray, arterial gas with FiO₂ recorded, ECG, FBC, urea and electrolytes, theophylline level if taking it, sputum culture if purulent.",
                "GOLD 2027: measuring troponin and natriuretic peptide may be advisable (weak evidence) — cardiovascular risk rises during and after exacerbations.",
            ),
            checklistTitle = "Admit or escalate (GOLD 2027 Fig 4.4; ICMR STW)",
            checklist = listOf(
                "Signs of respiratory failure — high RR, accessory muscles, worsening hypoxaemia or hypercapnia, mental change",
                "No response to initial medical management",
                "Serious comorbidity — heart failure, new arrhythmia",
                "Insufficient home support",
                "ICU: hypoxaemia/hypercapnia despite ventilatory support, confusion or coma, need for IMV or vasopressors",
                "ICMR STW red flags: altered sensorium, SpO₂ <88% despite therapy, HR >110, SBP <90 mm Hg",
            ),
            citations = listOf(
                cite(RE_GOLD, "Ch. 4 — Definition; Conditions to be considered; Figure 4.2; Figure 4.4 (p.108); Cardiopulmonary interactions"),
                cite(RE_ICMR_COPD, "Features of severe exacerbation; Red flag signs; Admission criteria"),
                cite(RE_NG115, "Rec 1.3.1 (Table 7); 1.3.3"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Controlled oxygen",
            timing = "From first contact; recheck gases at 30–60 min (BTS 2017)",
            points = listOf(
                "BTS 2017: known COPD or other hypercapnia risk — target SpO₂ 88–92% pending blood gases (grade A for COPD).",
                "BTS 2017: before gases, 24% Venturi at 2–3 L/min, 28% Venturi at 4 L/min or nasal cannulae 1–2 L/min; recheck gases at 30–60 min.",
                "BTS 2017: if PCO₂ and pH are normal, aim 94–98% unless prior hypercapnic failure needing NIV or IPPV, or usual SpO₂ <94%.",
                "ICS/NCCP 2013: oxygen for hypoxaemia to SpO₂ 88–92%, preferably by Venturi mask. ICMR STW: severe exacerbation — target SpO₂ 92% if monitoring is available.",
                "NICE NG115: oxygen to an individualised target; arterial gases for every hospital arrival. GOLD 2027: HFNT for hypoxaemic failure; NIV if hypercapnic.",
                "BTS 2017: score SpO₂ on the NEWS chart section for hypercapnia-risk targets; never stop oxygen abruptly — rebound hypoxaemia can be life-threatening.",
            ),
            scoreIds = listOf("news2"),
            citations = listOf(
                cite(RE_BTS_O2, "A3; G2; B4; good practice points — COPD; excessive oxygen therapy"),
                cite(RE_ICS_COPD, "Oxygen in AECOPD"),
                cite(RE_ICMR_COPD, "Severe exacerbation"),
                cite(RE_NG115, "Rec 1.3.27, 1.3.29"),
                cite(RE_GOLD, "Figure 4.7 (p.113)"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Bronchodilators",
            timing = "Immediately",
            points = listOf(
                "GOLD 2027: inhaled SABA, with or without a short-acting anticholinergic, first line; pMDI (± spacer) and nebuliser are similarly effective.",
                "GOLD 2027: nebuliser hourly for 2–3 doses, or pMDI 1–2 puffs hourly for 2–3 doses, then every 2–4 h by response; avoid high SABA doses.",
                "GOLD 2027: air-driven nebulisation preferred. NICE NG115 and BTS 2017: if hypercapnic or acidotic, drive with compressed air and give oxygen by nasal cannulae alongside.",
                "ICS/NCCP 2013: ipratropium 500 µg nebulised (or 20 µg × 2–4 puffs) plus salbutamol 2.5 mg nebulised (or 100 µg × 2–4 puffs) every 4–6 h.",
                "ICMR STW: nebulised levosalbutamol/ipratropium 1.25 mg/0.5 mg, repeated at 20-minute intervals as needed.",
                "GOLD 2027: IV methylxanthines not recommended. NICE NG115: IV theophylline only as an adjunct if nebulised bronchodilators fail; level within 24 h.",
            ),
            citations = listOf(
                cite(RE_GOLD, "Ch. 4 — Bronchodilators (p.114)"),
                cite(RE_NG115, "Rec 1.3.8–1.3.11, 1.3.22–1.3.24"),
                cite(RE_BTS_O2, "R2"),
                cite(RE_ICS_COPD, "Bronchodilators in AECOPD"),
                cite(RE_ICMR_COPD, "Mild exacerbation"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Systemic corticosteroids",
            points = listOf(
                "GOLD 2027: prednisone-equivalent 40 mg daily for 5 days; oral prednisolone is as effective as IV.",
                "NICE NG115: prednisolone 30 mg daily for 5 days for everyone admitted, unless significantly contraindicated.",
                "ICMR STW: oral prednisolone 30 mg daily for 5 days. ICS/NCCP 2013: 30–40 mg/day for 5–10 days; IV if ventilated or unable to take orally.",
                "GOLD 2027: even short courses raise pneumonia, sepsis and death risk — reserve for significant exacerbations; eosinophil-guided omission is unvalidated in hospital.",
            ),
            citations = listOf(
                cite(RE_GOLD, "Ch. 4 — Glucocorticoids (p.114)"),
                cite(RE_NG115, "Rec 1.3.13–1.3.16"),
                cite(RE_ICMR_COPD, "Mild exacerbation"),
                cite(RE_ICS_COPD, "Glucocorticoids in AECOPD"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Antibiotics",
            points = listOf(
                "GOLD 2027: give if ≥2 of more dyspnoea, fever, sputum volume or purulence (purulence must be one), prior positive sputum culture, or any mechanical ventilation.",
                "GOLD 2027: aminopenicillin-clavulanate, macrolide or tetracycline (quinolone in selected patients) by local resistance; 5–7 days, ≤5 days for outpatients.",
                "NICE NG114: amoxicillin 500 mg TDS, doxycycline 200 mg then 100 mg daily, or clarithromycin 500 mg BD — each for 5 days in total.",
                "NICE NG114: IV if unable to take oral or severely unwell (e.g. co-amoxiclav 1.2 g TDS), reviewed by 48 h; oral levofloxacin only with specialist advice.",
                "ICMR STW: amoxicillin 500 mg TDS, azithromycin 500 mg OD or doxycycline 100 mg OD (BD on day 1) for 5 days.",
                "ICS/NCCP 2013: antibiotics for all exacerbations; second line (e.g. co-amoxiclav 625 mg TDS, cefixime 200 mg BD) if admitted or ventilated; no routine fluoroquinolones.",
            ),
            citations = listOf(
                cite(RE_GOLD, "Ch. 4 — Antibiotics (pp.115–116); Figure 4.7"),
                cite(RE_NG114, "Rec 1.1.2, 1.2.1–1.2.3; Table 1"),
                cite(RE_ICMR_COPD, "Mild exacerbation"),
                cite(RE_ICS_COPD, "Antibiotics in AECOPD"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Non-invasive ventilation",
            timing = "If acidosis persists >30 min after standard treatment (BTS 2017)",
            points = listOf(
                "GOLD 2027: NIV is the initial mode for acute respiratory failure in admitted exacerbations; it reduces intubation and mortality (success 80–85%).",
                "GOLD 2027: NIV if PaCO₂ ≥6.0 kPa (45 mm Hg) with pH ≤7.35, severe dyspnoea with respiratory muscle fatigue, or persistent hypoxaemia despite oxygen.",
                "BTS/ICS 2016: start NIV when pH <7.35 and PCO₂ >6.5 kPa persist despite optimal medical therapy (grade A); severe acidosis alone does not preclude a trial.",
                "ISCCM 2020: NIV for pH 7.25–7.35 (1A); try it before invasive ventilation even at pH <7.25 with PaCO₂ ≥45 unless immediate intubation is needed (2A).",
                "NICE NG115: deliver NIV in a dedicated setting with trained staff, a plan for deterioration and an agreed ceiling. ICS/NCCP 2013: use NIV early, even without routine ABG.",
                "GOLD 2027: once at least 4 h of unassisted breathing is tolerated, NIV can be stopped without weaning.",
            ),
            tool = ProtocolTool.AcidBase,
            citations = listOf(
                cite(RE_GOLD, "Ch. 4 — Noninvasive mechanical ventilation; Figure 4.9 (p.118)"),
                cite(RE_BTS_ICS, "Rec 24–26"),
                cite(RE_BTS_O2, "Good practice points — COPD"),
                cite(RE_ISCCM_NIV, "A1 Acute exacerbation of COPD — recommendations"),
                cite(RE_NG115, "Rec 1.3.30–1.3.32"),
                cite(RE_ICS_COPD, "NIV in AECOPD"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Escalate to invasive ventilation",
            timing = "NIV must not delay intubation (BTS/ICS 2016)",
            points = listOf(
                "BTS/ICS 2016: consider invasive ventilation for persistent or worsening acidosis despite optimal NIV; intubate in respiratory arrest or peri-arrest.",
                "NICE NG115: judge suitability on functional status, BMI, oxygen need when stable, comorbidities and previous ICU admissions — never on age or FEV₁ alone.",
                "GOLD 2027: patients who could survive are often denied ICU through unwarranted prognostic pessimism; an advance directive helps these decisions.",
                "GOLD 2027: respiratory stimulants are not recommended. NICE NG115: doxapram only when NIV is unavailable or inappropriate.",
            ),
            checklistTitle = "GOLD 2027 Fig 4.10: indications for invasive ventilation",
            checklist = listOf(
                "Persistent life-threatening hypoxaemia despite HFNT or NIV",
                "Unable to tolerate HFNT and/or NIV",
                "After respiratory or cardiac arrest",
                "Diminished consciousness, or agitation not controlled by sedation",
                "Massive aspiration or persistent vomiting",
                "Persistent inability to clear secretions",
                "Severe haemodynamic instability unresponsive to fluids and vasoactive drugs",
                "Severe ventricular or supraventricular arrhythmias",
            ),
            citations = listOf(
                cite(RE_BTS_ICS, "Rec 27, 32–33"),
                cite(RE_NG115, "Rec 1.3.25, 1.3.33–1.3.34"),
                cite(RE_GOLD, "Ch. 4 — Ventilatory support; Invasive mechanical ventilation; Figure 4.10 (p.119)"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Adjuncts, discharge and follow-up",
            points = listOf(
                "GOLD 2027: consider SC heparin or LMWH thromboprophylaxis; monitor fluid balance; treat heart failure, arrhythmia or pulmonary embolism.",
                "NICE NG115: before discharge — spirometry, maintenance bronchodilators re-established, satisfactory oximetry or gases after respiratory failure, follow-up arranged.",
                "GOLD 2027: start long-acting bronchodilators before discharge; add ICS if ≥1 moderate/severe exacerbation and raised eosinophils; review within 1 month and at 3 months.",
                "ICMR STW: discharge when clinical and laboratory data are back to baseline, maintenance therapy can be followed and comorbidities are controlled; review at one week.",
                "GOLD 2027: smoking cessation, influenza, pneumococcal and RSV vaccination and early pulmonary rehabilitation reduce further exacerbations.",
            ),
            citations = listOf(
                cite(RE_GOLD, "Figure 4.6 (p.113); Key points; Hospital discharge and follow-up (pp.119–120); Prevention of further exacerbations"),
                cite(RE_NG115, "Rec 1.3.41–1.3.47"),
                cite(RE_ICMR_COPD, "Discharge criteria"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Oxygen targets: BTS 2017 and ICS/NCCP 2013 — 88–92%; ICMR COPD STW — SpO₂ 92% in a severe exacerbation; the " +
                "GOLD 2027 report gives no numeric SpO₂ target for exacerbations.",
            listOf(cite(RE_BTS_O2, "A3"), cite(RE_ICS_COPD, "Oxygen in AECOPD"), cite(RE_ICMR_COPD, "Severe exacerbation"), cite(RE_GOLD, "Ch. 4")),
        ),
        ProtocolNote(
            "Prednisolone dose differs: GOLD 2027 40 mg for 5 days; NICE NG115 and ICMR STW 30 mg for 5 days; ICS/NCCP 2013 " +
                "30–40 mg for 5–10 days. Eosinophil-guided steroid use is promising but unvalidated in inpatients (GOLD 2027).",
            listOf(
                cite(RE_GOLD, "Glucocorticoids (p.114)"), cite(RE_NG115, "Rec 1.3.16"),
                cite(RE_ICMR_COPD, "Mild exacerbation"), cite(RE_ICS_COPD, "Glucocorticoids in AECOPD"),
            ),
        ),
        ProtocolNote(
            "Antibiotics: ICS/NCCP 2013 advises them for all exacerbations and ICMR STW lists them in every regimen; GOLD 2027 " +
                "limits them to purulent sputum, a prior positive culture or ventilation; NICE NG114 says consider, weighing severity and resistance.",
            listOf(
                cite(RE_ICS_COPD, "Antibiotics in AECOPD"), cite(RE_ICMR_COPD, "Mild exacerbation"),
                cite(RE_GOLD, "Antibiotics (p.116)"), cite(RE_NG114, "Rec 1.1.2"),
            ),
        ),
        ProtocolNote(
            "HFNT versus NIV: GOLD 2027 calls HFNT the first mode for hypoxaemic failure, but notes that ERS guidance prefers " +
                "NIV over HFNT in hypercapnic exacerbations and that trials in hypercapnia conflict.",
            listOf(cite(RE_GOLD, "High-flow nasal therapy; Figure 4.7")),
        ),
        ProtocolNote(
            "Methylxanthines and stimulants: GOLD 2027 advises against IV methylxanthines and respiratory stimulants; NICE NG115 " +
                "still allows IV theophylline as an adjunct and doxapram when NIV is unavailable.",
            listOf(cite(RE_GOLD, "Bronchodilators; Ventilatory support"), cite(RE_NG115, "Rec 1.3.22, 1.3.25")),
        ),
    ),
    citations = listOf(
        cite(RE_GOLD), cite(RE_NG115), cite(RE_NG114), cite(RE_BTS_O2), cite(RE_BTS_ICS), cite(RE_ISCCM_NIV),
        cite(RE_ICMR_COPD), cite(RE_ICS_COPD),
    ),
)

// ================================================================================================ Pulmonary embolism
private val rePe = Protocol(
    id = "pulmonary-embolism",
    category = ProtocolCategory.Respiratory,
    title = "Pulmonary embolism",
    summary = "Clinical probability, D-dimer and imaging, risk stratification, anticoagulation, thrombolysis and rescue " +
        "options — attributed to ESC 2019, NICE NG158, AHA/ACC 2026 (summary), AHA 2025 and NAMS India 2024.",
    keywords = listOf(
        "pulmonary embolism", "PE", "PTE", "pulmonary thromboembolism", "VTE", "venous thromboembolism", "DVT",
        "massive PE", "submassive PE", "high-risk PE", "intermediate-risk PE", "saddle embolus", "obstructive shock",
        "RV strain", "RV dysfunction", "D-dimer", "age-adjusted D-dimer", "CTPA", "CT pulmonary angiogram", "V/Q scan",
        "Wells score", "PERC", "PESI", "sPESI", "Hestia", "thrombolysis", "alteplase", "rtPA", "streptokinase",
        "urokinase", "heparin", "UFH", "LMWH", "enoxaparin", "fondaparinux", "DOAC", "NOAC", "apixaban", "rivaroxaban",
        "dabigatran", "edoxaban", "warfarin", "embolectomy", "catheter-directed thrombolysis", "CDT", "PERT", "ECMO",
        "IVC filter",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Haemodynamically unstable? Treat as high-risk PE",
            timing = "Immediately",
            points = listOf(
                "ESC 2019: suspected high-risk PE — bedside echocardiography or emergency CTPA, depending on availability and clinical circumstances (I C).",
                "ESC 2019 Fig 3: no RV dysfunction on echo — look for other causes of shock; RV dysfunction and CTPA not immediately feasible — treat as high-risk PE.",
                "ESC 2019: start IV unfractionated heparin with a weight-adjusted bolus without delay (I C). NICE NG158: confirmed PE with instability — continuous UFH infusion.",
                "Raschke 1993 RCT: weight-based UFH (80 units/kg bolus, then 18 units/kg/h, aPTT every 6 h) reached the therapeutic range faster than fixed dosing.",
            ),
            checklistTitle = "ESC 2019 Table 4: haemodynamic instability",
            checklist = listOf(
                "Cardiac arrest — needing CPR",
                "Obstructive shock — SBP <90 mm Hg or vasopressors needed for BP ≥90 despite adequate filling, AND end-organ hypoperfusion",
                "Persistent hypotension — SBP <90 mm Hg or a fall ≥40 mm Hg for >15 min, not from new arrhythmia, hypovolaemia or sepsis",
            ),
            doses = listOf(
                StepDose(
                    "Unfractionated heparin — bolus (Raschke nomogram)",
                    WeightDose(80.0, 80.0, unit = "units", per = "IV bolus"),
                    note = "Follow the local aPTT or anti-Xa nomogram",
                ),
                StepDose(
                    "Unfractionated heparin — infusion (Raschke nomogram)",
                    WeightDose(18.0, 18.0, unit = "units", per = "per hour IV"),
                    note = "Raschke 1993: aPTT every 6 h; therapeutic range 1.5–2.3 × control",
                ),
            ),
            citations = listOf(
                cite(RE_ESC_PE, "Recommendations for diagnosis (1); Table 4; Figure 3"),
                cite(RE_NG158, "Rec 1.3.12"),
                cite(RE_RASCHKE, "Abstract"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Stable: estimate clinical probability",
            points = listOf(
                "ESC 2019: base the strategy on clinical probability by judgement or a validated rule (I A). NICE NG158: two-level Wells — PE likely if >4 points.",
                "NICE NG158: low suspicion with other diagnoses feasible — consider PERC to decide whether any further PE testing is needed; not validated in COVID-19.",
                "ESC 2019: anticoagulate while tests are pending if probability is high or intermediate (I C). NICE NG158: also if D-dimer cannot be resulted within 4 h.",
                "NICE NG158: before interim anticoagulation take FBC, renal and liver function, PT and aPTT — do not wait for results; review within 24 h.",
                "NICE NG158: take a history, examine and offer a chest X-ray to exclude other causes. AHA/ACC 2026: ask about surgery, trauma, immobility, pregnancy, oestrogen, cancer.",
            ),
            scoreIds = listOf("wells-pe", "perc"),
            citations = listOf(
                cite(RE_ESC_PE, "Recommendations for diagnosis (2)"),
                cite(RE_NG158, "Rec 1.1.15–1.1.17, 1.1.21, 1.3.4"),
                cite(RE_AHA_PE, "Guideline highlights — risk factors"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "D-dimer and imaging",
            points = listOf(
                "ESC 2019: D-dimer (preferably highly sensitive) for low/intermediate probability or PE-unlikely (I A); not in high probability — a normal result does not exclude PE (III A).",
                "ESC 2019: consider an age-adjusted cut-off — age × 10 µg/L above 50 years (IIa B). NICE NG158: consider an age-adjusted threshold over 50.",
                "NICE NG158: Wells >4 or positive D-dimer — CTPA immediately; V/Q SPECT (or planar V/Q) if contrast allergy, CrCl <30 mL/min or high radiation risk.",
                "ESC 2019: normal CTPA excludes PE at low/intermediate probability (I A); a segmental or more proximal defect confirms it at intermediate/high probability (I B).",
                "AHA/ACC 2026: D-dimer when probability is below 50%; imaging if D-dimer is raised or probability is above 50%; CTPA is the standard test.",
            ),
            citations = listOf(
                cite(RE_ESC_PE, "Recommendations for diagnosis (3)–(5)"),
                cite(RE_NG158, "Rec 1.1.14, 1.1.18, 1.1.21"),
                cite(RE_AHA_PE, "Guideline highlights — diagnosis"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Risk-stratify confirmed PE",
            timing = "As soon as PE is confirmed",
            points = listOf(
                "ESC 2019: haemodynamic instability defines high risk (I B); stratify stable patients into intermediate and low risk (I B).",
                "ESC 2019: use PESI or sPESI (IIa B); assess the RV by imaging or biomarkers even when PESI is low or sPESI negative (IIa B).",
                "AHA/ACC 2026: new categories A–E; A–B (no or mild symptoms, low risk) often discharged from ED; C–E admitted; D–E may need advanced therapy.",
                "NAMS 2024 (India): outpatient treatment for low-risk PE — Hestia 0, no RV dysfunction and normal cardiac biomarkers.",
            ),
            checklistTitle = "ESC 2019 Table 9: early mortality risk",
            checklist = listOf(
                "High — haemodynamic instability with RV dysfunction (PESI and troponin not needed)",
                "Intermediate–high — stable, PESI III–V or sPESI ≥1, RV dysfunction on TTE/CTPA AND raised troponin",
                "Intermediate–low — stable, PESI III–V or sPESI ≥1, with one or neither of RV dysfunction / raised troponin",
                "Low — stable, PESI I–II or sPESI 0, no RV dysfunction; troponin optional, negative if measured",
            ),
            citations = listOf(
                cite(RE_ESC_PE, "Table 9; Recommendations for prognostic assessment (1)–(2)"),
                cite(RE_AHA_PE, "Guideline highlights — clinical categories"),
                cite(RE_NAMS, "Annexure 2 — Table I"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Anticoagulation (intermediate and low risk)",
            points = listOf(
                "ESC 2019: if parenteral, LMWH or fondaparinux over UFH for most patients (I A); a NOAC is preferred to a VKA when eligible (I A).",
                "NICE NG158: offer apixaban or rivaroxaban; otherwise LMWH ≥5 days then dabigatran or edoxaban, or LMWH with a VKA until INR ≥2.0 on 2 readings.",
                "FDA labels: apixaban 10 mg BD for 7 days then 5 mg BD; rivaroxaban 15 mg BD with food for 21 days then 20 mg OD.",
                "FDA labels, after 5–10 days of parenteral anticoagulant: dabigatran 150 mg BD if CrCl >30; edoxaban 60 mg OD (30 mg if CrCl 15–50, ≤60 kg, P-gp inhibitors).",
                "ESC 2019: NOACs not recommended in severe renal impairment, pregnancy, lactation or antiphospholipid syndrome (III C). Fondaparinux 5/7.5/10 mg OD for <50/50–100/>100 kg.",
                "NAMS 2024 (India): DOAC preferred; VKA in moderate–severe liver disease, eGFR <30, APLA, interacting drugs, breastfeeding or financial constraints; UFH if eGFR <15.",
            ),
            doses = listOf(
                StepDose(
                    "Enoxaparin — twice daily (ESC 2019 Table 11)",
                    WeightDose(1.0, 1.0, unit = "mg", per = "SC every 12 h"),
                ),
                StepDose(
                    "Enoxaparin — once daily (ESC 2019 Table 11)",
                    WeightDose(1.5, 1.5, unit = "mg", per = "SC once daily"),
                ),
            ),
            citations = listOf(
                cite(RE_ESC_PE, "Recommendations for acute-phase treatment of intermediate- or low-risk PE (1)–(2); Table 11"),
                cite(RE_NG158, "Rec 1.3.8, 1.3.13–1.3.14"),
                cite(RE_APIXABAN, "§2.1"),
                cite(RE_RIVAROXABAN, "§2.1"),
                cite(RE_DABIGATRAN, "§2.2"),
                cite(RE_EDOXABAN, "§2.2"),
                cite(RE_NAMS, "Annexure 2 — Table II"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Systemic thrombolysis for high-risk PE",
            timing = "High-risk PE, or haemodynamic deterioration on anticoagulation",
            points = listOf(
                "ESC 2019: systemic thrombolysis for high-risk PE (I B) and as rescue for deterioration on anticoagulation (I B). NICE NG158: consider if haemodynamically unstable.",
                "ESC 2019 (III B) and NICE NG158: no routine primary systemic thrombolysis in stable PE, even with RV dysfunction.",
                "ESC 2019: alteplase 100 mg over 2 h, or 0.6 mg/kg over 15 min (max 50 mg). FDA label: resume anticoagulation once aPTT is ≤2× normal.",
                "ESC 2019: streptokinase 250,000 IU over 30 min then 100,000 IU/h for 12–24 h, or accelerated 1.5 million IU over 2 h.",
                "ESC 2019: urokinase 4400 IU/kg over 10 min then 4400 IU/kg/h for 12–24 h, or accelerated 3 million IU over 2 h.",
                "NAMS 2024 (India): systemic thrombolysis for PE with instability — SBP <90 mm Hg, a fall >40 mm Hg from baseline, or cardiac arrest.",
            ),
            checklistTitle = "ESC 2019 Table 12: contraindications to fibrinolysis",
            checklist = listOf(
                "Absolute: haemorrhagic stroke or stroke of unknown origin, at any time",
                "Absolute: ischaemic stroke in the previous 6 months",
                "Absolute: central nervous system neoplasm",
                "Absolute: major trauma, surgery or head injury in the previous 3 weeks",
                "Absolute: bleeding diathesis or active bleeding",
                "Relative: TIA in the previous 6 months; oral anticoagulation",
                "Relative: pregnancy or first postpartum week; non-compressible puncture sites; traumatic resuscitation",
                "Relative: refractory hypertension (SBP >180 mm Hg); advanced liver disease; infective endocarditis; active peptic ulcer",
            ),
            doses = listOf(
                StepDose(
                    "Alteplase — accelerated (ESC 2019 Table 12)",
                    WeightDose(0.6, 0.6, unit = "mg", per = "IV over 15 min"),
                    note = "Standard regimen: 100 mg over 2 h",
                    maxTotal = 50.0,
                ),
                StepDose(
                    "Urokinase — loading (ESC 2019 Table 12)",
                    WeightDose(4400.0, 4400.0, unit = "units", per = "IV over 10 min"),
                ),
                StepDose(
                    "Urokinase — infusion (ESC 2019 Table 12)",
                    WeightDose(4400.0, 4400.0, unit = "units", per = "per hour IV for 12–24 h"),
                ),
            ),
            citations = listOf(
                cite(RE_ESC_PE, "Recommendations for acute-phase treatment of high-risk PE (1); of intermediate- or low-risk PE (3); Table 12"),
                cite(RE_NG158, "Rec 1.6.2–1.6.3"),
                cite(RE_ALTEPLASE, "§2.3"),
                cite(RE_NAMS, "Annexure 2 — Figure 1"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Rescue and support in high-risk PE",
            points = listOf(
                "ESC 2019: surgical embolectomy if thrombolysis is contraindicated or has failed (I C); percutaneous catheter-directed treatment should be considered (IIa C).",
                "ESC 2019: norepinephrine and/or dobutamine should be considered (IIa C); cautious fluid — up to 500 mL over 15–30 min only if CVP is normal-to-low.",
                "ESC 2019: ECMO may be considered with embolectomy or catheter therapy in refractory collapse or arrest (IIb C); a PE response team should be considered (IIa C).",
                "AHA 2025: arrest from confirmed PE — fibrinolysis or surgical or percutaneous embolectomy are reasonable; consider fibrinolysis if PE is suspected; ECLS is reasonable.",
                "NAMS 2024 (India): catheter-directed thrombolysis as an alternative in intermediate bleeding risk or failed thrombolysis; embolectomy if thrombolysis is contraindicated or failed.",
                "ESC 2019: IVC filter if anticoagulation is absolutely contraindicated or PE recurs despite therapeutic anticoagulation (IIa C); not routinely (III A).",
            ),
            doses = listOf(
                StepDose(
                    "Norepinephrine (ESC 2019 Table 10)",
                    WeightDose(0.2, 1.0, unit = "mcg", per = "per minute IV infusion"),
                    note = "Excessive vasoconstriction may worsen tissue perfusion",
                ),
                StepDose(
                    "Dobutamine (ESC 2019 Table 10)",
                    WeightDose(2.0, 20.0, unit = "mcg", per = "per minute IV infusion"),
                    note = "May worsen hypotension if used without a vasopressor; may trigger arrhythmias",
                ),
            ),
            citations = listOf(
                cite(RE_ESC_PE, "Recommendations for acute-phase treatment of high-risk PE (1)–(2); Table 10; multidisciplinary PE teams; IVC filters"),
                cite(RE_AHA_SPECIAL, "Pulmonary embolism — Rec 1–4 (S628)"),
                cite(RE_NAMS, "Annexure 2 — Figure 1"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Disposition and follow-up",
            points = listOf(
                "ESC 2019: carefully selected low-risk PE may go home early if proper outpatient care and anticoagulation can be provided (IIa A).",
                "NICE NG158: consider outpatient treatment of low-risk PE using a validated tool; give written information, a direct contact and out-of-hours advice.",
                "NICE NG158: anticoagulate confirmed PE for at least 3 months. NAMS 2024: unprovoked or persistent-risk VTE — indefinite, with periodic review.",
                "AHA/ACC 2026: contact or visit within 1 week of discharge, clinic review by 3 months, and screen for chronic thromboembolic disease at every visit for ≥1 year.",
            ),
            checklistTitle = "ESC 2019 Table 13 (Hestia): any 'yes' rules out early discharge",
            checklist = listOf(
                "Haemodynamically unstable",
                "Thrombolysis or embolectomy needed",
                "Active bleeding or high risk of bleeding",
                "More than 24 h of oxygen to keep SpO₂ >90%",
                "PE diagnosed during anticoagulant treatment",
                "Severe pain needing IV analgesia for more than 24 h",
                "Medical or social reason for >24 h in hospital (infection, malignancy, no support)",
                "CrCl <30 mL/min, severe liver impairment, pregnancy, or documented heparin-induced thrombocytopenia",
            ),
            citations = listOf(
                cite(RE_ESC_PE, "Recommendations for early discharge, home treatment; Table 13"),
                cite(RE_NG158, "Rec 1.2.1–1.2.4, 1.3.5"),
                cite(RE_NAMS, "Annexure 2 — Table III"),
                cite(RE_AHA_PE, "Guideline highlights — follow-up"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Thrombolysis beyond high-risk PE: NAMS 2024 (India, Fig 1) also lists systemic thrombolysis for PE with RV dysfunction, " +
                "raised biomarkers, severe hypoxaemia, extensive clot or right-heart thrombus; ESC 2019 (III B) and NICE NG158 " +
                "advise against routine primary thrombolysis without haemodynamic instability.",
            listOf(
                cite(RE_NAMS, "Annexure 2 — Figure 1"),
                cite(RE_ESC_PE, "Recommendations for acute-phase treatment of intermediate- or low-risk PE (3)"),
                cite(RE_NG158, "Rec 1.6.3"),
            ),
        ),
        ProtocolNote(
            "UFH dose: ESC 2019 requires a weight-adjusted bolus; the 80 units/kg and 18 units/kg/h figures come from the " +
                "Raschke 1993 nomogram trial, not from the ESC tables reviewed. Follow the local aPTT or anti-Xa nomogram.",
            listOf(cite(RE_ESC_PE, "Recommendations for acute-phase treatment of high-risk PE (1)"), cite(RE_RASCHKE, "Abstract")),
        ),
        ProtocolNote(
            "Editions: ESC 2019 remains the current ESC PE guideline. The 2026 AHA/ACC guideline introduces clinical categories " +
                "A–E; its full text was not reviewed here, so statements are limited to the official AHA/ACC summary.",
            listOf(cite(RE_ESC_PE), cite(RE_AHA_PE, "Guideline highlights")),
        ),
        ProtocolNote(
            "Pregnancy: NOACs are not recommended (ESC 2019 III C; AHA/ACC 2026); LMWH or UFH can be used (AHA/ACC 2026); " +
                "NAMS 2024 prefers LMWH over UFH.",
            listOf(
                cite(RE_ESC_PE, "Recommendations for acute-phase treatment of intermediate- or low-risk PE (2)"),
                cite(RE_AHA_PE, "Guideline highlights — anticoagulants"),
                cite(RE_NAMS, "Annexure 2 — Table IV"),
            ),
        ),
        ProtocolNote(
            "ESC 2019 Table 12 lists alteplase, streptokinase and urokinase regimens only; tenecteplase dosing for PE is not covered here.",
            listOf(cite(RE_ESC_PE, "Table 12")),
        ),
    ),
    citations = listOf(
        cite(RE_ESC_PE), cite(RE_NG158), cite(RE_AHA_PE), cite(RE_NAMS), cite(RE_RASCHKE), cite(RE_AHA_SPECIAL),
        cite(RE_APIXABAN), cite(RE_RIVAROXABAN), cite(RE_DABIGATRAN), cite(RE_EDOXABAN), cite(RE_ALTEPLASE),
    ),
)

// ================================================================================================ Anaphylaxis
private val reAnaphylaxis = Protocol(
    id = "anaphylaxis",
    category = ProtocolCategory.Respiratory,
    title = "Anaphylaxis",
    summary = "Recognition, IM adrenaline, positioning, fluids, refractory anaphylaxis, adjuncts, observation for " +
        "biphasic reactions and discharge — attributed to RCUK 2021, WAO 2020, EAACI 2021, ERC/AHA 2025, NICE NG258 and ICMR STW.",
    keywords = listOf(
        "anaphylaxis", "anaphylactic shock", "anaphylactic reaction", "severe allergic reaction", "allergy",
        "drug allergy", "drug reaction", "food allergy", "bee sting", "wasp sting", "insect sting", "angioedema",
        "urticaria", "hives", "stridor", "laryngeal oedema", "laryngeal edema", "adrenaline", "epinephrine",
        "IM adrenaline", "1:1000", "adrenaline infusion", "autoinjector", "auto-injector", "AAI",
        "refractory anaphylaxis", "biphasic reaction", "tryptase", "glucagon", "beta-blocker", "pheniramine",
        "cetirizine", "hydrocortisone",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Recognise anaphylaxis",
            timing = "At first suspicion",
            points = listOf(
                "WAO 2020: the diagnosis is clinical — highly likely when either criterion below is met, with onset over minutes to several hours.",
                "RCUK 2021: sudden airway, breathing and/or circulation problems, usually with skin or mucosal changes — absent in up to 20%. If in doubt, give IM adrenaline.",
                "ERC 2025: may present with respiratory or cardiovascular involvement only; under anaesthesia, rising airway pressures with falling BP may be the only signs.",
                "WAO 2020: differentials include acute asthma, syncope, anxiety or panic, generalised urticaria, foreign-body aspiration, MI, pulmonary embolism, seizure.",
            ),
            checklistTitle = "WAO 2020: anaphylaxis is highly likely if either",
            checklist = listOf(
                "1. Acute skin/mucosal involvement (hives, itch, flushing, swollen lips-tongue-uvula) PLUS respiratory compromise, low BP or end-organ dysfunction, or severe GI symptoms",
                "2. After a known or highly probable allergen: acute hypotension, bronchospasm or laryngeal involvement — even without skin signs",
                "Adult hypotension: SBP <90 mm Hg or a fall >30% from baseline",
            ),
            citations = listOf(
                cite(RE_WAO, "Table 2; Table 3"),
                cite(RE_RCUK, "Summary of key recommendations"),
                cite(RE_ERC_SPECIAL, "Anaphylaxis — recognition of anaphylaxis"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "IM adrenaline — first line",
            timing = "Immediately; repeat after 5 min if no improvement (RCUK 2021)",
            points = listOf(
                "WAO 2020: 0.01 mg/kg of 1 mg/mL (1:1000) adrenaline IM, maximum 0.5 mg per dose; repeat every 5–15 min if symptoms persist.",
                "RCUK 2021 and ERC 2025: adults 0.5 mg (0.5 mL of 1 mg/mL) IM; RCUK repeats after 5 min, ERC after 3–5 min, if no improvement.",
                "RCUK 2021: inject into the anterolateral middle third of the thigh with a 21G or 23G needle; subcutaneous and inhaled routes are less effective.",
                "ICMR STW: IM adrenaline 1:1000 (1 mg in 1 mL), 0.2–0.5 mg, every 5–15 min for choking, respiratory distress or shock.",
                "EAACI 2021: early adrenaline appears to reduce biphasic reactions; patients on beta-blockers may respond poorly.",
                "RCUK 2021: IV adrenaline boluses are not recommended outside cardiac arrest, except by specialists experienced in titrating vasopressors.",
            ),
            doses = listOf(
                StepDose(
                    "Adrenaline 1 mg/mL (1:1000) — IM (WAO 2020 Table 6)",
                    WeightDose(0.01, 0.01, unit = "mg", per = "IM into the anterolateral thigh (0.01 mL/kg)"),
                    note = "Adults 0.5 mg (RCUK 2021, ERC 2025); repeat if no improvement",
                    maxTotal = 0.5,
                ),
            ),
            citations = listOf(
                cite(RE_WAO, "Management — Table 6"),
                cite(RE_RCUK, "§5.1; summary of key recommendations"),
                cite(RE_ERC_SPECIAL, "Anaphylaxis — give adrenaline"),
                cite(RE_ICMR_URT, "Treatment at primary care level"),
                cite(RE_EAACI, "Emergency management — route of administration"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Position, oxygen, airway",
            timing = "Alongside the first adrenaline dose",
            points = listOf(
                "RCUK 2021: do not let the patient stand, walk or sit up suddenly — deaths occur within minutes; lie flat (± legs raised) if hypotensive.",
                "RCUK 2021: airway or breathing problems — semi-recumbent; unconscious but breathing — recovery position; pregnant — left side.",
                "ERC 2025: stop or remove the trigger if possible without delaying treatment; give 15 L/min oxygen and titrate to SpO₂ 94–98%.",
                "RCUK 2021: stridor — nebulised adrenaline 5 mL of 1 mg/mL; bronchospasm — nebulised salbutamol and ipratropium with oxygen. EAACI 2021: supplements, never substitutes.",
                "RCUK 2021: monitor heart rate, BP, oximetry and ECG for arrhythmia; call for expert help early.",
            ),
            citations = listOf(
                cite(RE_RCUK, "§4.4; refractory anaphylaxis algorithm (A, B)"),
                cite(RE_ERC_SPECIAL, "Anaphylaxis — initial approach; give oxygen"),
                cite(RE_EAACI, "Other interventions"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "IV fluids",
            timing = "Early if hypotensive or poor response to the first adrenaline dose (RCUK 2021)",
            points = listOf(
                "RCUK 2021: hypotension or poor response to the first adrenaline dose — rapid IV crystalloid bolus 500–1000 mL in adults, repeated as needed.",
                "RCUK 2021: severe shock may need 3–5 L in adults; prefer Hartmann's or Plasma-Lyte to 0.9% saline for large volumes; use IO if IV is delayed.",
                "ERC 2025: crystalloid 10–20 mL/kg boluses, repeated by response. WAO 2020: 20 mL/kg for cardiovascular instability. EAACI 2021: 500 mL boluses in adults.",
            ),
            doses = listOf(
                StepDose(
                    "Crystalloid bolus (ERC 2025)",
                    WeightDose(10.0, 20.0, unit = "mL", per = "IV or IO bolus, repeat according to response"),
                ),
            ),
            citations = listOf(
                cite(RE_RCUK, "§5.3; §6.7"),
                cite(RE_ERC_SPECIAL, "Anaphylaxis — secure vascular access, give intravenous fluids"),
                cite(RE_WAO, "Management (text with Table 6)"),
                cite(RE_EAACI, "Fluid support"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Refractory anaphylaxis",
            timing = "No improvement after 2 IM doses (RCUK 2021)",
            points = listOf(
                "RCUK 2021: no respiratory or cardiovascular improvement after 2 appropriate IM doses — get critical care help and start an IV adrenaline infusion.",
                "RCUK 2021: 1 mg adrenaline in 100 mL 0.9% saline on a dedicated line; start 0.5–1.0 mL/kg/h and titrate; give IM doses every 5 min until it runs.",
                "RCUK 2021: tachycardia, tremor or pallor with normal or high BP suggests excess adrenaline — reduce the rate; wean over 30 min, 1 h after resolution.",
                "RCUK 2021 and ERC 2025: refractory to the infusion — add noradrenaline or vasopressin (RCUK: or metaraminol).",
                "RCUK 2021: on beta-blockers, consider glucagon 1 mg IV, repeated or as a 1–2 mg/h infusion. AHA 2025: 1–5 mg IV in adults (case reports).",
                "AHA 2025: arrest from anaphylaxis — isotonic fluids and ECPR if refractory are reasonable. RCUK 2021: consider prolonged CPR.",
            ),
            doses = listOf(
                StepDose(
                    "Adrenaline infusion, 1 mg in 100 mL (10 mcg/mL) — RCUK 2021",
                    WeightDose(0.5, 1.0, unit = "mL", per = "per hour IV — starting rate, then titrate"),
                    note = "0.5 mL/kg/h ≈ 0.1 mcg/kg/min (moderate); 1 mL/kg/h if hypotensive or hypoxic",
                ),
            ),
            citations = listOf(
                cite(RE_RCUK, "§6.6; §6.7; Appendix 3; refractory anaphylaxis algorithm"),
                cite(RE_ERC_SPECIAL, "Anaphylaxis — other medications; considerations for cardiac arrest"),
                cite(RE_AHA_SPECIAL, "Anaphylaxis — Rec 1–3 (S585); supportive text 3 (S586)"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Antihistamines and corticosteroids",
            timing = "Only after adrenaline, never instead of it",
            points = listOf(
                "RCUK 2021: antihistamines are third line — no role for airway, breathing or circulation; once stable, non-sedating oral (e.g. cetirizine 10–20 mg) for skin symptoms.",
                "RCUK 2021 and ERC 2025: routine corticosteroids no longer advised; RCUK: hydrocortisone may be considered for refractory reactions or asthma after resuscitation.",
                "WAO 2020: glucocorticoids, antihistamines and β₂ agonists are second line; evidence increasingly suggests steroids give no acute benefit and may harm.",
                "EAACI 2021: antihistamines relieve only skin symptoms; glucocorticoids have limited evidence and may be deleterious in children.",
                "ICMR STW: injectable hydrocortisone and pheniramine may be required; oral prednisolone may be started to cover a biphasic response.",
                "ERC 2025: rapid IV first-generation H1 antihistamines may cause or worsen hypotension.",
            ),
            citations = listOf(
                cite(RE_RCUK, "§5.4; §5.5; §6.7.4; Table 4"),
                cite(RE_ERC_SPECIAL, "Anaphylaxis — role of steroids and antihistamines"),
                cite(RE_WAO, "Management"),
                cite(RE_EAACI, "Other interventions"),
                cite(RE_ICMR_URT, "Treatment at primary care level"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Tryptase and observation",
            timing = "Tryptase within 1–2 h of onset, no later than 4 h (NICE NG258)",
            points = listOf(
                "NICE NG258: age ≥16 — mast cell tryptase as soon as possible after treatment starts, and again ideally within 1–2 h (no later than 4 h) of onset.",
                "RCUK 2021: biphasic reactions occur in about 5%, median around 12 h; more likely after severe reactions, >1 adrenaline dose or adrenaline delayed >30–60 min.",
                "NICE NG258: consider discharge 2 h after resolution if good response to one IM dose within 30 min, full resolution, 2 in-date auto-injectors and supervision.",
                "NICE NG258: observe at least 6 h after resolution if 2 IM doses were needed or there is a history of biphasic reaction.",
                "EAACI 2021: monitor 6–8 h after respiratory compromise and at least 12–24 h after hypotension.",
            ),
            checklistTitle = "NICE NG258: observe ≥12 h after resolution if",
            checklist = listOf(
                "Severe reaction needing more than 2 doses of adrenaline",
                "Severe asthma, or severe respiratory compromise",
                "Possible continuing allergen absorption, e.g. slow-release medicines",
                "Presents out of hours",
                "May be unable to respond to deterioration",
                "Going home to an area where emergency care is hard to reach",
            ),
            citations = listOf(
                cite(RE_NG258, "Rec 1.1.4, 1.1.7–1.1.9"),
                cite(RE_RCUK, "§8.2"),
                cite(RE_EAACI, "Monitoring and discharge arrangements; Table 4"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Discharge and referral",
            points = listOf(
                "NICE NG258: offer referral to a specialist allergy service after emergency treatment for suspected anaphylaxis.",
                "NICE NG258: ensure 2 in-date adrenaline auto-injectors and training, unless the cause was an easily avoided drug; carry them at all times.",
                "NICE NG258: after using the injector, lie down (or sit if breathless) and do not stand suddenly; explain biphasic risk, trigger avoidance and when to call.",
                "EAACI 2021: auto-injector at least 0.3 mg for adolescents and adults; 0.5 mg may be considered if overweight or after a life-threatening episode.",
                "WAO 2020: limited availability and affordability of auto-injectors remain a major problem in many countries; give a personalised management plan.",
                "RCUK 2021: before discharge, check for dizziness on standing; eating at least 1 h before leaving may be advisable.",
            ),
            citations = listOf(
                cite(RE_NG258, "Rec 1.1.12–1.1.16"),
                cite(RE_EAACI, "Dose of adrenaline"),
                cite(RE_WAO, "Abstract; Long-term management of anaphylaxis"),
                cite(RE_RCUK, "§8.1; §8.3"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Repeat interval for IM adrenaline differs: RCUK 2021 5 min; ERC 2025 3–5 min; WAO 2020 and ICMR STW 5–15 min.",
            listOf(
                cite(RE_RCUK, "§5.1"), cite(RE_ERC_SPECIAL, "Anaphylaxis — give adrenaline"),
                cite(RE_WAO, "Management"), cite(RE_ICMR_URT, "Treatment at primary care level"),
            ),
        ),
        ProtocolNote(
            "India vs UK/Europe: the ICMR STW (urticaria and angioedema) includes injectable hydrocortisone and pheniramine and " +
                "oral prednisolone for biphasic cover; RCUK 2021 and ERC 2025 advise against routine steroids and make antihistamines third line.",
            listOf(
                cite(RE_ICMR_URT, "Treatment at primary care level"), cite(RE_RCUK, "§5.4–5.5"),
                cite(RE_ERC_SPECIAL, "Anaphylaxis — role of steroids and antihistamines"),
            ),
        ),
        ProtocolNote(
            "Fluid volumes differ: RCUK 2021 500–1000 mL bolus in adults; ERC 2025 10–20 mL/kg; WAO 2020 20 mL/kg; EAACI 2021 500 mL.",
            listOf(
                cite(RE_RCUK, "§5.3"), cite(RE_ERC_SPECIAL, "Anaphylaxis — give intravenous fluids"),
                cite(RE_WAO, "Management"), cite(RE_EAACI, "Fluid support"),
            ),
        ),
        ProtocolNote(
            "Observation differs: NICE NG258 and RCUK 2021 are risk-stratified (2, 6 or 12 h after resolution); EAACI 2021 " +
                "suggests 6–8 h after respiratory compromise and 12–24 h after hypotension.",
            listOf(cite(RE_NG258, "Rec 1.1.7–1.1.9"), cite(RE_RCUK, "§8.2"), cite(RE_EAACI, "Monitoring and discharge arrangements")),
        ),
        ProtocolNote(
            "Glucagon dose for beta-blocked patients differs: RCUK 2021 1 mg IV (repeat or 1–2 mg/h); AHA 2025 supportive text 1–5 mg IV in adults.",
            listOf(cite(RE_RCUK, "Appendix 3"), cite(RE_AHA_SPECIAL, "Anaphylaxis — supportive text 3 (S586)")),
        ),
        ProtocolNote(
            "Snake antivenom: EAACI 2021 suggests subcutaneous adrenaline premedication when antivenom is given (very low certainty evidence).",
            listOf(cite(RE_EAACI, "Table 2 — long-term management")),
        ),
    ),
    citations = listOf(
        cite(RE_RCUK), cite(RE_WAO), cite(RE_EAACI), cite(RE_NG258), cite(RE_ICMR_URT), cite(RE_ERC_SPECIAL),
        cite(RE_AHA_SPECIAL),
    ),
)

internal val respiratoryEmergencyProtocols: List<Protocol> = listOf(reAsthma, reCopd, rePe, reAnaphylaxis)
