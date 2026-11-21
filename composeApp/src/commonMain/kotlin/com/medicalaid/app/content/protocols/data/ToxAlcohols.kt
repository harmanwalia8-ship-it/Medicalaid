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
 * Paracetamol overdose, alcohol intoxication and withdrawal, methanol and ethylene glycol.
 * Rules:
 * - Doses, thresholds and windows exactly as the cited document states them; nothing filled from memory.
 * - Every point is attributed ("FDA label: …", "US/Canada 2023: …", "MHRA 2012: …", "MoHFW STG: …", "EXTRIP 2015: …").
 * - US/Canada 2023 = the consensus statement of four North American toxicology societies (JAMA Netw Open 2023).
 * - Bodies without an Authority entry (MHRA, RCEM, NHSBT, MSF, IPS, EXTRIP, AACT) use Authority.Paper with the body named
 *   in the document field.
 * - EXTRIP 2015 (methanol), AACT 2002 and O'Grady 1989 were reviewed from their published abstracts only.
 * - Reuses tx-hh-anz-paracetamol-2020 (HouseholdTox.kt) and tx-cocaine-ips-2023 / tx-cocaine-who-mhgap-2016 (Cocaine.kt).
 * - Checked November 2026.
 */

internal val toxAlcoholSources = listOf(
    // Paracetamol ---------------------------------------------------------------------------------
    Source(
        "tx-pcm-fda-acetadote-label", Authority.FDA, "Prescribing information (DailyMed; Cumberland Pharmaceuticals)",
        "ACETADOTE (acetylcysteine) injection, for intravenous use", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=472f158a-5ab9-4308-8e49-1116e6ea3d39",
        status = "Label effective April 2025; dosage sections revised November 2024 (two-bag regimen, revised nomogram)",
    ),
    Source(
        "tx-pcm-fda-oral-nac-label", Authority.FDA, "Prescribing information (DailyMed; Hospira)",
        "Acetylcysteine Solution, USP 10% and 20% — for inhalation or oral administration (acetaminophen antidote)", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=5558a5f5-e821-473b-7d8a-5d33d09f0586",
        status = "Label effective May 2026; not for injection",
    ),
    Source(
        "tx-pcm-us-canada-consensus-2023", Authority.Paper,
        "Consensus statement, modified Delphi panel of four clinical toxicology societies (JAMA Netw Open 2023;6(8):e2327739)",
        "Management of Acetaminophen Poisoning in the US and Canada: A Consensus Statement", "2023",
        "https://doi.org/10.1001/jamanetworkopen.2023.27739",
        status = "Correction to Figures 2–4 published September 2023 (e2337926); corrected figures reviewed",
    ),
    Source(
        "tx-pcm-mhra-dsu-2012", Authority.Paper, "MHRA Drug Safety Update, vol 6 issue 2: A1 (UK medicines regulator)",
        "Treating paracetamol overdose with intravenous acetylcysteine: new guidance", "2012",
        "https://www.gov.uk/drug-safety-update/treating-paracetamol-overdose-with-intravenous-acetylcysteine-new-guidance",
        status = "September 2012, after a Commission on Human Medicines review",
    ),
    Source(
        "tx-pcm-uk-smpc-2025", Authority.Paper, "UK Summary of Product Characteristics (emc; Martindale Pharma, PL 12064/0026)",
        "Acetylcysteine 200 mg/ml Injection", "2025",
        "https://www.medicines.org.uk/emc/product/3447/smpc",
        status = "Text revised 28 August 2025",
    ),
    Source(
        "tx-pcm-rcem-snap-2023", Authority.Paper, "Royal College of Emergency Medicine position statement (UK)",
        "Use of the SNAP Regimen for the Treatment of Paracetamol Toxicity in adults and children", "2023",
        "https://rcem.ac.uk/wp-content/uploads/2023/05/Use-of-the-SNAP-Regimen-for-the-Treatment-of-Paracetamol-Toxicity-in-adults-and-children.pdf",
        status = "May 2023; gives no doses (refers to NPIS/TOXBASE)",
    ),
    Source(
        "tx-pcm-snap-pettie-2019", Authority.Paper, "Original paper, three UK toxicology units (EClinicalMedicine 2019;11:11–17)",
        "Safety and Efficacy of the SNAP 12-hour Acetylcysteine Regimen for the Treatment of Paracetamol Overdose", "2019",
        "https://doi.org/10.1016/j.eclinm.2019.04.005",
        status = "Before–after cohort, not a randomised trial",
    ),
    Source(
        "tx-pcm-nhsbt-pol195-2026", Authority.Paper, "NHS Blood and Transplant policy POL195/20 (UK)",
        "Liver Transplantation: Selection Criteria and Recipient Registration", "2026",
        "https://nhsbtdbe.blob.core.windows.net/umbraco-assets-corp/38481/pol195.pdf",
        status = "Effective 17 March 2026; UK and Republic of Ireland super-urgent listing",
    ),
    Source(
        "tx-pcm-kcc-ogrady-1989", Authority.Paper, "Original paper, King's College Hospital (Gastroenterology 1989;97:439–445)",
        "Early indicators of prognosis in fulminant hepatic failure", "1989",
        "https://doi.org/10.1016/0016-5085(89)90081-4",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "tx-pcm-icmr-stw-alf-2020", Authority.ICMR, "Standard Treatment Workflow (ICD-10 K72.90)",
        "Liver Failure — acute liver failure or acute on chronic liver failure", "2020",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567562_liver_failure.pdf",
        status = "July 2020. No ICMR STW specific to paracetamol poisoning was found (STW list checked November 2026)",
    ),
    // Alcohol -------------------------------------------------------------------------------------
    Source(
        "tx-alc-nice-cg100", Authority.NICE, "Guideline CG100",
        "Alcohol-use disorders: diagnosis and management of physical complications", "2010",
        "https://www.nice.org.uk/guidance/cg100",
        status = "Published June 2010; last updated April 2017; UK guidance",
    ),
    Source(
        "tx-alc-nice-cg115", Authority.NICE, "Guideline CG115",
        "Alcohol-use disorders: diagnosis, assessment and management of harmful drinking (high-risk drinking) and alcohol dependence",
        "2011",
        "https://www.nice.org.uk/guidance/cg115",
        status = "Published February 2011; last updated October 2014; UK guidance",
    ),
    Source(
        "tx-alc-mohfw-stg-2016", Authority.MoHFW, "Standard Treatment Guidelines — full background document (Ver. 17-02-16)",
        "Management of Alcohol Dependence", "2016",
        "https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/7661.pdf",
        status = "Summary STG printed August 2017 (NHSRC); the guideline excludes methanol poisoning",
    ),
    Source(
        "tx-alc-icmr-stw-aud-2019", Authority.ICMR, "Standard Treatment Workflow (ICD-10 F10)",
        "Management of Alcohol Use Disorders", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952335_psychiatry_alcohol_use_disorders.pdf",
        status = "October 2019",
    ),
    // Methanol and ethylene glycol ------------------------------------------------------------------
    Source(
        "tx-meoh-fda-fomepizole-label", Authority.FDA, "Prescribing information (DailyMed; Zydus, fomepizole injection 1 g/mL)",
        "Fomepizole Injection", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=256910fe-91f2-48f6-b0b4-55edc52dacd4",
        status = "Label effective November 2025",
    ),
    Source(
        "tx-meoh-extrip-2015", Authority.Paper, "EXTRIP workgroup consensus (Crit Care Med 2015;43(2):461–472)",
        "Recommendations for the role of extracorporeal treatments in the management of acute methanol poisoning: a systematic review and consensus statement",
        "2015",
        "https://doi.org/10.1097/CCM.0000000000000708",
        status = "Reviewed from the published abstract; recommendation grades not reproduced",
    ),
    Source(
        "tx-meoh-extrip-eg-2023", Authority.Paper, "EXTRIP workgroup recommendations (Crit Care 2023;27:56)",
        "Extracorporeal treatment for ethylene glycol poisoning: systematic review and recommendations from the EXTRIP workgroup",
        "2023",
        "https://doi.org/10.1186/s13054-022-04227-2",
        status = "GRADE; very low-quality evidence for every recommendation",
    ),
    Source(
        "tx-meoh-aact-2002", Authority.Paper, "American Academy of Clinical Toxicology practice guideline (J Toxicol Clin Toxicol 2002;40(4):415–446)",
        "American Academy of Clinical Toxicology practice guidelines on the treatment of methanol poisoning", "2002",
        "https://doi.org/10.1081/CLT-120006745",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "tx-meoh-msf-2019", Authority.Paper, "Médecins Sans Frontières methanol poisoning project — generic treatment protocol (PDF, November 2019)",
        "Methanol poisoning at a glance", "2019",
        "https://methanolpoisoning.msf.org/wp-content/themes/msf/pdf/generic_methanol_poisoning_treatment_protocol_English.pdf",
        status = "NGO field protocol for low-resource settings, not a society guideline",
    ),
    Source(
        "tx-meoh-ijccm-abuse-2019", Authority.Paper, "Invited review, Indian J Crit Care Med 2019;23(Suppl 4):S296–S304 (ISCCM journal)",
        "Poisoning with Drugs of Abuse: Identification and Management", "2019",
        "https://doi.org/10.5005/jp-journals-10071-23309",
        status = "Review article, not a society guideline; section on spurious alcohol (methanol) poisoning",
    ),
    Source(
        "tx-meoh-ahmedabad-2013", Authority.Paper, "Original paper, single centre (J Emerg Trauma Shock 2013;6(2):73–77)",
        "Study of various clinical and laboratory parameters among 178 patients affected by hooch tragedy in Ahmedabad, Gujarat (India): A single center experience",
        "2013",
        "https://doi.org/10.4103/0974-2700.110745",
        status = "Retrospective; July 2009 outbreak",
    ),
)

// Paracetamol
private const val PCM_FDA = "tx-pcm-fda-acetadote-label"
private const val PCM_FDA_ORAL = "tx-pcm-fda-oral-nac-label"
private const val PCM_US = "tx-pcm-us-canada-consensus-2023"
private const val PCM_MHRA = "tx-pcm-mhra-dsu-2012"
private const val PCM_SMPC = "tx-pcm-uk-smpc-2025"
private const val PCM_RCEM = "tx-pcm-rcem-snap-2023"
private const val PCM_SNAP = "tx-pcm-snap-pettie-2019"
private const val PCM_NHSBT = "tx-pcm-nhsbt-pol195-2026"
private const val PCM_KCC = "tx-pcm-kcc-ogrady-1989"
private const val PCM_ICMR = "tx-pcm-icmr-stw-alf-2020"
private const val PCM_ANZ = "tx-hh-anz-paracetamol-2020" // defined in HouseholdTox.kt

// Alcohol
private const val ALC_NICE100 = "tx-alc-nice-cg100"
private const val ALC_NICE115 = "tx-alc-nice-cg115"
private const val ALC_MOHFW = "tx-alc-mohfw-stg-2016"
private const val ALC_ICMR = "tx-alc-icmr-stw-aud-2019"
private const val ALC_IPS = "tx-cocaine-ips-2023" // defined in Cocaine.kt
private const val ALC_MHGAP = "tx-cocaine-who-mhgap-2016" // defined in Cocaine.kt

// Methanol and ethylene glycol
private const val MEOH_FDA = "tx-meoh-fda-fomepizole-label"
private const val MEOH_EXTRIP = "tx-meoh-extrip-2015"
private const val MEOH_EXTRIP_EG = "tx-meoh-extrip-eg-2023"
private const val MEOH_AACT = "tx-meoh-aact-2002"
private const val MEOH_MSF = "tx-meoh-msf-2019"
private const val MEOH_IJCCM = "tx-meoh-ijccm-abuse-2019"
private const val MEOH_AHD = "tx-meoh-ahmedabad-2013"

internal val toxAlcoholProtocols = listOf(
    // =============================================================================================
    // Paracetamol
    // =============================================================================================
    Protocol(
        id = "paracetamol-overdose",
        category = ProtocolCategory.Poisoning,
        title = "Paracetamol (acetaminophen) overdose",
        summary = "Timed level and treatment line (US 150, UK 100), staggered and late ingestions, acetylcysteine regimens, " +
            "monitoring and liver-transplant criteria — attributed to FDA, US/Canada consensus, MHRA, ANZ, NHSBT and ICMR.",
        keywords = listOf(
            "paracetamol", "acetaminophen", "APAP", "PCM", "Crocin", "Dolo", "Dolo 650", "Calpol", "bukhar ki goli",
            "NAC", "N-acetylcysteine", "acetylcysteine", "Acetadote", "Parvolex", "Rumack-Matthew", "nomogram",
            "SNAP regimen", "staggered overdose", "liver failure", "King's College criteria",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Assess the ingestion",
                timing = "At first contact; level from 4 h after ingestion",
                points = listOf(
                    "US/Canada 2023: 'acute' covers any ingestion within a 0–24 h period; repeated supratherapeutic ingestion (RSI) spans more than 24 h.",
                    "ANZ 2020: acute ingestion ≥10 g or ≥200 mg/kg (whichever is less) risks liver injury; after deliberate overdose measure a level whatever the stated dose.",
                    "FDA label: the reported amount is often inaccurate. Take the level at least 4 h after ingestion — earlier levels may not reflect the peak.",
                    "FDA label baseline tests: AST, ALT, bilirubin, INR, creatinine, BUN, glucose and electrolytes.",
                    "US/Canada 2023: a non-detectable level 2–4 h after ingestion typically excludes significant ingestion.",
                    "FDA label: protection is greatest when acetylcysteine starts within 0–8 h; efficacy falls later, but it should not be withheld at 15–24 h.",
                ),
                citations = listOf(
                    cite(PCM_US, "Figure 1; Management of suspected acute ingestion"),
                    cite(PCM_ANZ, "Initial approach; Box 1"),
                    cite(PCM_FDA, "§2.1; §2.2"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Activated charcoal",
                timing = "Within 4 h (US/Canada 2023); within 2 h, or 4 h if >30 g (ANZ 2020)",
                points = listOf(
                    "US/Canada 2023: consider single-dose activated charcoal within 4 h of acute ingestion — 50–100 g in adults, 25–50 g in children.",
                    "ANZ 2020: 50 g for a cooperative, awake adult within 2 h of a toxic immediate-release dose, or within 4 h if more than 30 g.",
                    "US/Canada 2023: after high-risk ingestion, or with extended-release products, charcoal may be useful beyond 4 h.",
                    "ANZ 2020: modified-release ≥10 g or ≥200 mg/kg — charcoal up to 4 h; massive ingestions (≥30 g or ≥500 mg/kg) may benefit later.",
                    "US/Canada 2023: gastrointestinal decontamination is not recommended for repeated supratherapeutic ingestion.",
                ),
                citations = listOf(
                    cite(PCM_US, "Figure 1; Figure 4; High-risk ingestion; Extended-release products"),
                    cite(PCM_ANZ, "Immediate release ingestion; Modified release ingestions; Boxes 3–4"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Plot the timed level: which treatment line",
                timing = "Single acute ingestion, level drawn 4–24 h after",
                points = listOf(
                    "FDA label (2025) and US/Canada 2023: treat on or above the line starting at 150 µg/mL at 4 h; the high-risk line starts at 300 µg/mL.",
                    "MHRA 2012 and UK SmPC: treat on or above one line from 100 mg/L at 4 h to 15 mg/L at 15 h, regardless of risk factors.",
                    "ANZ 2020: the nomogram line starts at 150 mg/L (1000 µmol/L) at 4 h; check whether your laboratory reports mg/L or µmol/L.",
                    "FDA label: below the line with a known time and a level taken ≥4 h after ingestion — do not give acetylcysteine; minimal hepatotoxicity risk.",
                    "FDA label: the nomogram may underestimate risk with chronic alcoholism, malnutrition or CYP2E1 inducers such as isoniazid — consider treating even below the line.",
                    "US/Canada 2023: level below the line but AST/ALT raised — reconsider the accuracy of the history.",
                ),
                checklistTitle = "Nomogram not used as drawn",
                checklist = listOf(
                    "Time of ingestion unknown or unreliable (FDA label; US/Canada 2023)",
                    "Staggered overdose over 1 h or more (MHRA 2012) — ANZ 2020 instead plots staggered ingestion from the earliest dose",
                    "Repeated supratherapeutic ingestion over more than 24 h (US/Canada 2023; FDA label)",
                    "Level drawn before 4 h after ingestion (US/Canada 2023)",
                    "Modified-release paracetamol (ANZ 2020)",
                    "Presentation more than 24 h after ingestion (US/Canada 2023)",
                ),
                citations = listOf(
                    cite(PCM_FDA, "§2.2; Figure 1"),
                    cite(PCM_US, "Figure 1; Figure 2"),
                    cite(PCM_MHRA, "Advice for healthcare professionals"),
                    cite(PCM_SMPC, "§4.1"),
                    cite(PCM_ANZ, "Box 2; Immediate release ingestion; Multiple or staggered ingestions"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Staggered, unknown-time, late and repeated ingestions",
                timing = "Start acetylcysteine while awaiting the level if ≥30 g or the result will return after 8 h (US/Canada 2023)",
                points = listOf(
                    "FDA label: unknown time, presentation after 8 h, or no level within 8 h — give the loading dose now; the level decides whether to continue.",
                    "MHRA 2012 and UK SmPC: staggered overdose (over ≥1 h) or any doubt over timing — give acetylcysteine without delay, whatever the level.",
                    "ANZ 2020: staggered over >2 h — plot from the earliest ingestion; start acetylcysteine if >8 h since the first dose or no level within 8 h.",
                    "US/Canada 2023: unknown time or >24 h — acetylcysteine if level >10 µg/mL or AST/ALT abnormal; none if ≤10 µg/mL with normal AST/ALT.",
                    "RSI — US/Canada 2023: treat if level ≥20 µg/mL or AST/ALT abnormal. ANZ 2020: treat if >20 mg/L or ALT >50 U/L; repeat bloods at 8 h.",
                    "Extended-release — FDA label: 4-h level below the line, repeat at 8–10 h. ANZ 2020: modified-release ≥10 g or ≥200 mg/kg — full 20-h course regardless of level.",
                ),
                citations = listOf(
                    cite(PCM_FDA, "§2.1; §2.2; §2.6"),
                    cite(PCM_MHRA, "Advice for healthcare professionals"),
                    cite(PCM_SMPC, "§4.1"),
                    cite(PCM_ANZ, "Multiple or staggered ingestions; Modified release ingestions; Repeated supratherapeutic ingestion; Boxes 4–5"),
                    cite(PCM_US, "Figure 1; Figure 4"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "IV acetylcysteine: 21-hour three-bag regimen",
                timing = "Most protective within 8 h of ingestion (FDA label)",
                points = listOf(
                    "FDA label: 300 mg/kg IV in three bags over 21 h; patients 40 kg or less should receive the three-bag regimen.",
                    "FDA label: 100 kg or more — fixed doses of 15,000 mg, 5,000 mg and 10,000 mg. US/Canada 2023: cap the dose calculation at 100 kg.",
                    "UK SmPC: 150 mg/kg in 200 mL over 1 h, 50 mg/kg in 500 mL over 4 h, 100 mg/kg in 1 L over 16 h; ceiling weight 110 kg.",
                    "MHRA 2012: first infusion over 60 min to reduce dose-related reactions. US/Canada 2023: infuse a 150 mg/kg load over at least 1 h.",
                    "FDA label: hyperosmolar — always dilute; 0.45% saline preferred. UK SmPC: glucose 5% preferred, 0.9% saline if glucose is unsuitable.",
                    "FDA label: reduce volumes under 40 kg or with fluid restriction — fluid overload can cause hyponatraemia, seizures and death.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Acetylcysteine bag 1 — loading (FDA three-bag)",
                        dose = WeightDose(minPerKg = 150.0, maxPerKg = 150.0, unit = "mg", per = "IV over 1 h"),
                        note = "FDA Table 2 diluent: 3 mL/kg (5–20 kg), 100 mL (21–40 kg), 200 mL (41–99 kg). 100 kg or more: 15,000 mg.",
                        maxTotal = 15000.0,
                    ),
                    StepDose(
                        label = "Acetylcysteine bag 2 (FDA three-bag)",
                        dose = WeightDose(minPerKg = 50.0, maxPerKg = 50.0, unit = "mg", per = "IV over 4 h"),
                        note = "Diluent 7 mL/kg, 250 mL or 500 mL in the same weight bands. 100 kg or more: 5,000 mg.",
                        maxTotal = 5000.0,
                    ),
                    StepDose(
                        label = "Acetylcysteine bag 3 (FDA three-bag)",
                        dose = WeightDose(minPerKg = 100.0, maxPerKg = 100.0, unit = "mg", per = "IV over 16 h"),
                        note = "Diluent 14 mL/kg, 500 mL or 1,000 mL in the same weight bands. 100 kg or more: 10,000 mg.",
                        maxTotal = 10000.0,
                    ),
                ),
                citations = listOf(
                    cite(PCM_FDA, "§2.3–2.5; Table 2; §5.2"),
                    cite(PCM_US, "Figure 3; Patients weighing more than 100 kg"),
                    cite(PCM_SMPC, "§4.2"),
                    cite(PCM_MHRA, "Advice for healthcare professionals"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Alternative acetylcysteine regimens",
                points = listOf(
                    "FDA label (41 kg or more): two-bag — 200 mg/kg over 4 h, then 100 mg/kg over 16 h; equivalence to three-bag unknown; may cause fewer hypersensitivity reactions.",
                    "FDA label: the three-bag regimen may be preferred with early signs of severe liver injury or a large ingestion.",
                    "ANZ 2020: two-bag is standard (maximum 22 g, then 11 g); adults ≥14 y dosed on actual weight rounded up to the nearest 10 kg, ceiling 110 kg.",
                    "RCEM 2023 (UK): the 12-h SNAP regimen should be default emergency-department practice. Pettie 2019: 100 mg/kg over 2 h, then 200 mg/kg over 10 h.",
                    "Pettie 2019: stop SNAP at 12 h if INR ≤1.3, ALT <100 U/L and not doubled, and paracetamol <20 mg/L; otherwise 200 mg/kg over a further 10 h.",
                    "FDA oral label: 140 mg/kg, then 70 mg/kg every 4 h for 17 doses, diluted to 5%; repeat a dose vomited within 1 h. US/Canada 2023: oral or IV acceptable.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Two-bag regimen — bag 1 (FDA label, 41 kg or more)",
                        dose = WeightDose(minPerKg = 200.0, maxPerKg = 200.0, unit = "mg", per = "IV over 4 h"),
                        note = "FDA: in 1,000 mL; 100 kg or more: 20,000 mg. ANZ 2020: in 500 mL glucose 5% or 0.9% saline, maximum 22 g (ceiling 110 kg).",
                        maxTotal = 20000.0,
                    ),
                    StepDose(
                        label = "Two-bag regimen — bag 2 (FDA label, 41 kg or more)",
                        dose = WeightDose(minPerKg = 100.0, maxPerKg = 100.0, unit = "mg", per = "IV over 16 h"),
                        note = "FDA: in 500 mL; 100 kg or more: 10,000 mg. ANZ 2020: in 1,000 mL, maximum 11 g.",
                        maxTotal = 10000.0,
                    ),
                    StepDose(
                        label = "SNAP regimen — bag 1 (Pettie 2019)",
                        dose = WeightDose(minPerKg = 100.0, maxPerKg = 100.0, unit = "mg", per = "IV over 2 h"),
                        note = "Pettie 2019 states no weight cap; the UK SmPC ceiling for the licensed 21-h regimen is 110 kg.",
                    ),
                    StepDose(
                        label = "SNAP regimen — bag 2 (Pettie 2019)",
                        dose = WeightDose(minPerKg = 200.0, maxPerKg = 200.0, unit = "mg", per = "IV over 10 h"),
                        note = "Bloods 2 h before the end; if stop criteria are not met, a further 200 mg/kg over 10 h.",
                    ),
                    StepDose(
                        label = "Oral acetylcysteine — loading (FDA oral label)",
                        dose = WeightDose(minPerKg = 140.0, maxPerKg = 140.0, unit = "mg", per = "orally, once"),
                        note = "Dilute the 20% solution to 5% (3 mL diluent per mL). Not for injection.",
                    ),
                    StepDose(
                        label = "Oral acetylcysteine — maintenance (FDA oral label)",
                        dose = WeightDose(minPerKg = 70.0, maxPerKg = 70.0, unit = "mg", per = "orally every 4 h, 17 doses"),
                        note = "First maintenance dose 4 h after the loading dose.",
                    ),
                ),
                citations = listOf(
                    cite(PCM_FDA, "§2.4; §2.5 Table 3"),
                    cite(PCM_ANZ, "Acetylcysteine infusions; Box 7"),
                    cite(PCM_RCEM, "Position statement"),
                    cite(PCM_SNAP, "Methods — SNAP regimen and stopping criteria"),
                    cite(PCM_FDA_ORAL, "Dosage and administration — acetaminophen overdose"),
                    cite(PCM_US, "Administration of acetylcysteine; Figure 3"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Monitoring, stopping and reactions",
                timing = "Bloods about 2 h before the infusion ends (ANZ 2020)",
                points = listOf(
                    "US/Canada 2023: before the infusion ends check paracetamol, AST/ALT and PT/INR; continue until stopping criteria are met, re-testing every 12–24 h.",
                    "US/Canada 2023 stopping criteria: level <10 µg/mL, INR <2.0, AST/ALT normal or 25–50% below peak, and clinically well.",
                    "ANZ 2020: continue if level >10 mg/L or ALT >50 U/L and rising; beyond 20 h, stop when ALT/AST are falling, INR <2.0 and the patient is well.",
                    "FDA label: paracetamol still detectable, ALT/AST rising or INR elevated after the last bag — continue acetylcysteine beyond 300 mg/kg.",
                    "FDA label: flushing at 30–60 min often settles; otherwise pause and give an antihistamine; stop for severe reactions and restart carefully once settled.",
                    "MHRA 2012 and US/Canada 2023: a previous reaction to acetylcysteine is not a contraindication. FDA label lists prior hypersensitivity as a contraindication.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Continued acetylcysteine (US/Canada 2023)",
                        dose = WeightDose(minPerKg = 6.25, maxPerKg = 6.25, unit = "mg", per = "per hour IV (at least), until stopping criteria are met"),
                        note = "ANZ 2020: continue at the second-bag rate, e.g. 100 mg/kg over 16 h.",
                    ),
                ),
                citations = listOf(
                    cite(PCM_US, "Figure 3"),
                    cite(PCM_ANZ, "Immediate release ingestion; Cessation of acetylcysteine; Box 8"),
                    cite(PCM_FDA, "§2.2 Continued therapy; §4; §5.1"),
                    cite(PCM_MHRA, "Review findings"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "High-risk or massive ingestion",
                timing = "Discuss with a clinical toxicologist or poisons centre",
                points = listOf(
                    "US/Canada 2023: high-risk means ≥30 g or a level above the high-risk line (300 µg/mL at 4 h); a higher acetylcysteine dose may be warranted with toxicologist advice.",
                    "ANZ 2020: level above double the nomogram line — double the second bag to 200 mg/kg over 16 h; triple or more — discuss with a toxicologist.",
                    "ANZ 2020: modified-release ≥30 g or ≥500 mg/kg also receives the doubled second bag; continue if the level is still ≥10 mg/L near the end.",
                    "US/Canada 2023: if signs of mitochondrial dysfunction develop (e.g. altered consciousness, metabolic acidosis, raised lactate), further laboratory testing is indicated.",
                    "US/Canada 2023: haemodialysis plus acetylcysteine for a level ≥900 µg/mL with acidosis or altered consciousness; acetylcysteine at least 12.5 mg/kg/h during dialysis.",
                    "ANZ 2020: seek poisons-centre advice for ingestions of 50 g or more, or 1 g/kg (whichever is less).",
                ),
                doses = listOf(
                    StepDose(
                        label = "Increased second bag — level above double the line (ANZ 2020)",
                        dose = WeightDose(minPerKg = 200.0, maxPerKg = 200.0, unit = "mg", per = "IV over 16 h"),
                        note = "Maximum 22 g, in 1,000 mL glucose 5% or 0.9% saline (child 14 mL/kg); pulse oximetry for the first 2 h.",
                        maxTotal = 22000.0,
                    ),
                    StepDose(
                        label = "Acetylcysteine during haemodialysis (US/Canada 2023)",
                        dose = WeightDose(minPerKg = 12.5, maxPerKg = 12.5, unit = "mg", per = "per hour IV (at least), during haemodialysis"),
                    ),
                ),
                citations = listOf(
                    cite(PCM_US, "High-risk acetaminophen ingestion; Use of enhanced elimination techniques; Figure 2"),
                    cite(PCM_ANZ, "Immediate release ingestion; Modified release ingestions; Box 7; Seeking advice from a Poisons Information Centre"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Liver failure and transplant referral",
                points = listOf(
                    "ANZ 2020: consult a liver transplant unit for INR >3.0 at 48 h or >4.5 at any time, oliguria or creatinine >200 µmol/L, or pH <7.3 or lactate >3 mmol/L.",
                    "ANZ 2020 also: SBP <80 mm Hg despite resuscitation; hypoglycaemia, severe thrombocytopenia, any encephalopathy, or GCS <15 without sedatives. No clotting factors unless bleeding or agreed with the unit.",
                    "King's College (O'Grady 1989): pH <7.30, prothrombin time >100 s and creatinine >300 µmol/L indicated a poor prognosis in paracetamol-induced liver failure.",
                    "US/Canada 2023: consider consulting a liver transplant team, including for patients with encephalopathy.",
                    "ICMR STW (liver failure): drug-induced ALF — acetylcysteine 150 mg/kg over 1 h, 50 mg/kg over 4 h, then 100 mg/kg over 16 h daily; refer urgently.",
                    "ICMR STW: intubate for grade 3–4 coma; 20% mannitol 100 mL for cerebral oedema if no renal failure; intensive care at a higher centre.",
                ),
                checklistTitle = "UK super-urgent liver listing — paracetamol (NHSBT 2026)",
                checklist = listOf(
                    "Category 1: pH <7.25 more than 24 h after overdose and after fluid resuscitation",
                    "Category 2: PT >100 s or INR >6.5, plus creatinine >300 µmol/L or anuria, plus grade 3–4 encephalopathy",
                    "Category 3: liver injury and coagulopathy, other causes excluded; lactate >5 mmol/L on admission and >4 mmol/L 24 h later after fluids, with encephalopathy",
                    "Category 4: two of the three category 2 criteria with deterioration (raised ICP, FiO₂ >50%, rising inotropes) and no sepsis",
                ),
                citations = listOf(
                    cite(PCM_ANZ, "Hepatotoxicity and subsequent liver failure"),
                    cite(PCM_KCC, "Abstract"),
                    cite(PCM_US, "Consultation with liver transplant team"),
                    cite(PCM_ICMR, "Primary treatment/stabilisation; Management at higher centre"),
                    cite(PCM_NHSBT, "§3.5.2 Categories 1–4"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Treatment lines differ: the FDA label (2025), US/Canada 2023 and ANZ 2020 start at 150 at 4 h; UK guidance (MHRA 2012) uses 100 mg/L at 4 h for everyone, so it treats some patients the other lines would not.",
                listOf(cite(PCM_FDA, "§2.2 Figure 1"), cite(PCM_US, "Figure 2"), cite(PCM_MHRA, "Advice for healthcare professionals"), cite(PCM_ANZ, "Box 2")),
            ),
            ProtocolNote(
                "Risk factors: the FDA label advises considering treatment below the line with chronic alcoholism, malnutrition or CYP2E1 inducers (e.g. isoniazid); MHRA 2012 dropped risk-factor assessment as the evidence was poor and inconsistent.",
                listOf(cite(PCM_FDA, "§2.2"), cite(PCM_MHRA, "Review findings")),
            ),
            ProtocolNote(
                "Regimens: the FDA labels three-bag (21 h) and two-bag (20 h) regimens and states their comparability is unknown; ANZ 2020 uses two-bag; RCEM 2023 makes SNAP (12 h) the UK default. US/Canada 2023: over 15 regimens exist, none compared.",
                listOf(cite(PCM_FDA, "§2.4"), cite(PCM_ANZ, "Box 7"), cite(PCM_RCEM, "Position statement"), cite(PCM_US, "Administration of acetylcysteine")),
            ),
            ProtocolNote(
                "Weight caps: FDA label fixed doses from 100 kg; US/Canada 2023 caps at 100 kg; UK SmPC and ANZ 2020 use a 110 kg ceiling (ANZ rounds adult weight up to the nearest 10 kg).",
                listOf(cite(PCM_FDA, "§2.5 Tables 2–3"), cite(PCM_US, "Patients weighing more than 100 kg"), cite(PCM_SMPC, "§4.2"), cite(PCM_ANZ, "Box 7")),
            ),
            ProtocolNote(
                "Stopping criteria differ: US/Canada 2023 — level <10 µg/mL, INR <2.0, AST/ALT normal or 25–50% below peak; ANZ 2020 — ALT/AST falling, INR <2.0 (plus level <10 mg/L after MR or >double-line ingestions); SNAP at 12 h — INR ≤1.3, ALT <100 U/L, level <20 mg/L.",
                listOf(cite(PCM_US, "Figure 3"), cite(PCM_ANZ, "Box 8"), cite(PCM_SNAP, "Methods")),
            ),
            ProtocolNote(
                "Staggered overdose is defined differently: over 1 h or more in the UK (MHRA 2012, SmPC), over more than 2 h in ANZ 2020; US/Canada 2023 counts any ingestion within a 24 h period as acute.",
                listOf(cite(PCM_MHRA, "Advice for healthcare professionals"), cite(PCM_SMPC, "§4.1"), cite(PCM_ANZ, "Box 1"), cite(PCM_US, "Figure 1")),
            ),
            ProtocolNote(
                "Units: US sources use µg/mL and UK/ANZ sources mg/L (numerically the same); ANZ 2020 warns that many laboratories report µmol/L (150 mg/L = 1000 µmol/L on its nomogram).",
                listOf(cite(PCM_ANZ, "Immediate release ingestion; Box 2"), cite(PCM_US, "Figure 2")),
            ),
            ProtocolNote(
                "India: no ICMR Standard Treatment Workflow for paracetamol poisoning was found (STW list checked November 2026); the ICMR liver-failure STW lists acetylcysteine for drug-induced acute liver failure.",
                listOf(cite(PCM_ICMR, "Primary treatment/stabilisation")),
            ),
            ProtocolNote(
                "Pregnancy: US/Canada 2023 manages it the same way and finds no data that oral acetylcysteine is less effective; the FDA label notes that delaying treatment may increase maternal and fetal risk.",
                listOf(cite(PCM_US, "Pregnancy"), cite(PCM_FDA, "§8.1")),
            ),
        ),
        citations = listOf(
            cite(PCM_FDA), cite(PCM_FDA_ORAL), cite(PCM_US), cite(PCM_MHRA), cite(PCM_SMPC), cite(PCM_ANZ),
            cite(PCM_RCEM), cite(PCM_SNAP), cite(PCM_NHSBT), cite(PCM_KCC), cite(PCM_ICMR),
        ),
    ),

    // =============================================================================================
    // Alcohol
    // =============================================================================================
    Protocol(
        id = "alcohol",
        category = ProtocolCategory.Poisoning,
        title = "Alcohol intoxication and withdrawal",
        summary = "Intoxication care, withdrawal timeline and severity, benzodiazepine regimens, thiamine for Wernicke's, " +
            "seizures, delirium tremens and admission — attributed to NICE, MoHFW, ICMR, Indian Psychiatric Society and WHO.",
        keywords = listOf(
            "alcohol", "ethanol", "daru", "desi daru", "sharab", "tharra", "liquor", "drunk", "intoxication", "withdrawal",
            "DTs", "delirium tremens", "rum fits", "withdrawal seizures", "Wernicke", "Wernicke's encephalopathy", "thiamine",
            "CIWA", "CIWA-Ar", "chlordiazepoxide", "Librium", "diazepam", "lorazepam", "Ativan", "detox",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Acute intoxication: supportive care",
                timing = "At first contact",
                points = listOf(
                    "IPS 2023: care is largely supportive; the first priority is protecting the airway and breathing, as respiratory depression can be fatal.",
                    "WHO mhGAP: check ABC, give oxygen, lay the person on their side; no oral fluids while sedated; observe until fully recovered.",
                    "IPS 2023: check glucose — oral glucose if conscious, otherwise IV 5% or 10% dextrose; give thiamine before glucose.",
                    "IPS 2023: IV fluids for dehydration; ECG in heavy intoxication ('holiday heart' arrhythmias); repeat glucose, electrolytes and blood gases while sensorium is altered.",
                    "IPS 2023: severe intoxication — admit to high-dependency or intensive care. Agitation or violence: consider haloperidol 5 mg with promethazine 50 mg.",
                    "WHO mhGAP: sedated with normal pupils — opioid overdose less likely; consider alcohol, other sedatives, head injury, infection or hypoglycaemia.",
                ),
                checklistTitle = "Look for (IPS 2023)",
                checklist = listOf(
                    "Hypoglycaemia, electrolyte imbalance, diabetic ketoacidosis, metabolic acidosis",
                    "Occult head injury — check for papilloedema; CT head if altered mental status is unexplained",
                    "Methanol — papilloedema without raised intracranial pressure",
                    "Other toxins — lithium, barbiturates, benzodiazepines, isoniazid",
                    "Wernicke's — confusion, gait abnormality, ophthalmoplegia",
                    "Suicidality — psychiatric evaluation",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(ALC_IPS, "Alcohol intoxication — assessment and management; Figure 3"),
                    cite(ALC_MHGAP, "SUB — Emergency presentations (pp. 107–109)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise withdrawal and its timeline",
                timing = "Onset 6–8 h after the last drink; seizures 12–24 h; delirium tremens 2–5 days (MoHFW STG)",
                points = listOf(
                    "MoHFW STG: withdrawal starts 6–8 h after stopping; tremor is earliest; perceptual symptoms at 8–12 h; peaks on day 2–3; markedly less by day 4–5.",
                    "MoHFW STG: seizures typically at 12–24 h, generalised tonic–clonic, often repeated within 3–6 h; status epilepticus is relatively rare.",
                    "MoHFW STG: delirium tremens at 2–5 days, up to a week; complicated withdrawal (seizures or delirium) affects 3–5% of dependent users.",
                    "MoHFW STG: exclude other causes of delirium — fluid and electrolyte disturbance, hepatic dysfunction, head injury.",
                    "ICMR STW: CBC, liver function, blood sugar and electrolytes; CT head with seizure or delirium tremens.",
                ),
                checklistTitle = "Withdrawal features (MoHFW STG; WHO mhGAP)",
                checklist = listOf(
                    "Tremor of hands, tongue or eyelids",
                    "Sweating, nausea, retching or vomiting",
                    "Tachycardia or hypertension",
                    "Agitation, anxiety, insomnia, headache",
                    "Transient visual, tactile or auditory hallucinations or illusions",
                    "Generalised seizures; confusion in severe cases",
                ),
                citations = listOf(
                    cite(ALC_MOHFW, "Diagnosis — alcohol withdrawal (p. 12); Complicated alcohol withdrawal (p. 24)"),
                    cite(ALC_ICMR, "Investigations"),
                    cite(ALC_MHGAP, "SUB — Emergency presentations (p. 107)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Assess severity and risk",
                points = listOf(
                    "NICE CG100: assess immediately on admission; consider the CIWA-Ar scale as an adjunct to clinical judgement.",
                    "NICE CG115: use a formal measure such as CIWA-Ar, especially in severe dependence or with a symptom-triggered regimen.",
                    "MoHFW STG: CIWA-Ar has 10 items (nausea/vomiting, tremor, sweats, anxiety, agitation, tactile, auditory and visual disturbance, headache, orientation), each 0–7; orientation 0–4.",
                    "MoHFW STG: 0–9 absent or minimal, 10–19 mild to moderate, greater than 20 severe withdrawal.",
                    "WHO mhGAP high risk: past severe withdrawal, seizures or delirium; serious medical or psychiatric illness; marked features within 6 h of the last drink; failed outpatient attempts; no support.",
                    "NICE CG115: consider inpatient withdrawal for >30 units/day, SADQ >30, epilepsy or past withdrawal seizures or delirium tremens, or concurrent benzodiazepine withdrawal.",
                ),
                citations = listOf(
                    cite(ALC_NICE100, "Rec 1.1.2.2–1.1.2.3"),
                    cite(ALC_NICE115, "Rec 1.3.4.5, 1.3.5.8"),
                    cite(ALC_MOHFW, "Treatment regimen (p. 26)"),
                    cite(ALC_MHGAP, "SUB 2 Protocol 3 — clinical tip (pp. 120–121)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Benzodiazepine regimens",
                points = listOf(
                    "NICE CG115 and MoHFW STG: preferred drugs are long-acting chlordiazepoxide or diazepam. NICE CG100 also allows carbamazepine; clomethiazole only in inpatients.",
                    "MoHFW STG fixed dose: chlordiazepoxide 15–50 mg four times daily (diazepam 10–25 mg three times daily); hold 2 days, then cut 20% daily or 25% alternate days.",
                    "MoHFW STG symptom-triggered: 10–20 mg diazepam-equivalent per dose while symptoms persist; front-loading starts with 30–40 mg. NICE CG100: symptom-triggered in hospital.",
                    "ICMR STW: diazepam 20–40 mg/day in divided doses by severity, tapered 10–20% a day within 7–10 days. WHO mhGAP: in hospital up to 120 mg/day for 3 days.",
                    "Liver impairment or elderly — MoHFW: lorazepam or oxazepam, doses about 50% lower. NICE CG115: lorazepam; avoid benzodiazepines in severe liver impairment.",
                    "MoHFW STG equivalents: chlordiazepoxide 25 mg ≈ diazepam 10 mg ≈ lorazepam 2 mg ≈ oxazepam 30 mg.",
                ),
                citations = listOf(
                    cite(ALC_NICE115, "Rec 1.3.5.1–1.3.5.3, 1.3.5.9–1.3.5.10"),
                    cite(ALC_NICE100, "Rec 1.1.3.1, 1.1.3.4"),
                    cite(ALC_MOHFW, "Medications; Table 6; Treatment regimen (pp. 25–27)"),
                    cite(ALC_ICMR, "Withdrawal management"),
                    cite(ALC_MHGAP, "SUB 2 Protocol 3 (p. 120)"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Thiamine: prevent and treat Wernicke's",
                timing = "Before any glucose",
                points = listOf(
                    "NICE CG100: oral thiamine for harmful or dependent drinkers who are malnourished, have decompensated liver disease, are in acute withdrawal, or before planned withdrawal.",
                    "NICE CG100: parenteral then oral thiamine if malnourished or with decompensated liver disease and attending ED or admitted; doses toward the upper end of the BNF range.",
                    "NICE CG100: suspected Wernicke's — parenteral thiamine for at least 5 days, then oral; keep a high suspicion, particularly if intoxicated.",
                    "MoHFW STG: everyone in withdrawal — at least 250 mg parenteral thiamine daily for 3–5 days; suspected Wernicke's — 500 mg/day for 3–5 days; oral for ≥3 months.",
                    "IPS 2023: at-risk patients ≥250 mg IM daily for 3–5 days, then 100 mg orally daily. WHO mhGAP: Wernicke's — 100–500 mg IV/IM 2–3 times daily for 3–5 days.",
                    "MoHFW STG, IPS 2023 and WHO mhGAP: thiamine before glucose; no parenteral glucose without thiamine. ICMR STW: thiamine 100 mg once daily in withdrawal.",
                ),
                checklistTitle = "Suspect Wernicke's encephalopathy",
                checklist = listOf(
                    "Confusion (MoHFW STG; WHO mhGAP)",
                    "Ataxia (MoHFW STG; WHO mhGAP)",
                    "Nystagmus (MoHFW STG; WHO mhGAP)",
                    "Ophthalmoplegia — may occur in severe cases (MoHFW STG)",
                    "Intoxicated patient — keep a high suspicion (NICE CG100)",
                ),
                citations = listOf(
                    cite(ALC_NICE100, "Rec 1.2.1.1–1.2.1.4"),
                    cite(ALC_MOHFW, "Thiamine (pp. 27–28)"),
                    cite(ALC_IPS, "Management of alcohol intoxication"),
                    cite(ALC_MHGAP, "SUB — Emergency presentations (pp. 110, 113); SUB 2 Protocol 3 (pp. 120–121)"),
                    cite(ALC_ICMR, "Withdrawal management"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Withdrawal seizures",
                timing = "Typically 12–24 h after the last drink (MoHFW STG)",
                points = listOf(
                    "NICE CG100: consider a quick-acting benzodiazepine such as lorazepam to reduce further seizures; review the withdrawal regimen; do not use phenytoin.",
                    "MoHFW STG: lorazepam 2 mg IV prophylaxis for seizures in this withdrawal or a past withdrawal seizure; taper from a high dose once controlled.",
                    "MoHFW STG: give diazepam parenterally only IV (erratic IM absorption); no phenytoin prophylaxis unless there is a separate seizure disorder.",
                    "WHO mhGAP: seizures — diazepam 10–20 mg orally, IV or rectally first, then transfer to hospital.",
                    "MoHFW STG: evaluate for other causes of seizures; symptom-triggered dosing is not recommended after previous withdrawal seizures. ICMR STW: CT head.",
                ),
                citations = listOf(
                    cite(ALC_NICE100, "Rec 1.1.5.1–1.1.5.3"),
                    cite(ALC_MOHFW, "Management of alcohol withdrawal seizures (p. 28); Treatment regimen (p. 27)"),
                    cite(ALC_MHGAP, "SUB — Emergency presentations (p. 110)"),
                    cite(ALC_ICMR, "Investigations; Secondary care"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Delirium tremens",
                timing = "2–5 days after the last drink, up to a week (MoHFW STG)",
                points = listOf(
                    "NICE CG100: oral lorazepam first-line; parenteral lorazepam or haloperidol if symptoms persist or oral medication is declined; review the withdrawal regimen.",
                    "MoHFW STG: inpatient care; diazepam 10 mg IV, repeated every 5–20 min; 20 mg boluses if the first two fail; aim for a calm, sedated patient.",
                    "MoHFW STG: benzodiazepines reduce mortality more than antipsychotics; watch for lucid intervals and recurrence; then taper oral benzodiazepines.",
                    "WHO mhGAP: diazepam 10–20 mg first; if severe and unresponsive, haloperidol 1–2.5 mg orally or IM.",
                    "ICMR STW: tertiary care — rule out head injury, hepatic encephalopathy, Wernicke's and other causes of delirium; ICU when indicated.",
                    "MoHFW STG: maintain fluids, electrolytes and nutrition; keep airway equipment at the bedside when injectable benzodiazepines are used.",
                ),
                citations = listOf(
                    cite(ALC_NICE100, "Rec 1.1.4.1–1.1.4.2"),
                    cite(ALC_MOHFW, "Management of delirium tremens; General nursing care (pp. 28–29)"),
                    cite(ALC_MHGAP, "SUB — Emergency presentations (p. 110)"),
                    cite(ALC_ICMR, "Tertiary care"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Admission and referral",
                points = listOf(
                    "NICE CG100: admit for medically assisted withdrawal with, or at high risk of, seizures or delirium tremens; admit under-16s; lower threshold if frail or cognitively impaired.",
                    "MoHFW STG inpatient indicators: >30 units/day, severe or complicated withdrawal now or before, significant physical or psychiatric illness, other substance dependence, pregnancy, children, elderly.",
                    "ICMR STW admission: past withdrawal seizures or delirium tremens, failed outpatient treatment, significant medical or psychiatric comorbidity, polysubstance use.",
                    "IPS 2023: admit intoxicated patients with severe intoxication, Wernicke's, alcoholic hepatitis, arrhythmia, convulsions, persistent disorientation or abnormal cardiopulmonary findings.",
                    "NICE CG100: decompensated liver disease — advice from a liver specialist. Not admitted — advise against a sudden reduction in drinking.",
                ),
                citations = listOf(
                    cite(ALC_NICE100, "Rec 1.1.1.1–1.1.1.4, 1.1.3.2"),
                    cite(ALC_MOHFW, "Setting (p. 23)"),
                    cite(ALC_ICMR, "Indications for admission"),
                    cite(ALC_IPS, "Management of alcohol intoxication — in-patient admission"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Thiamine doses differ: NICE CG100 gives no figure (upper end of the BNF range); MoHFW STG ≥250 mg/day parenteral in all withdrawal and 500 mg/day for suspected Wernicke's; WHO mhGAP and ICMR STW 100 mg daily for prevention.",
                listOf(cite(ALC_NICE100, "Rec 1.2.1.1"), cite(ALC_MOHFW, "Thiamine (pp. 27–28)"), cite(ALC_MHGAP, "SUB 2 Protocol 3 (pp. 120–121)"), cite(ALC_ICMR, "Withdrawal management")),
            ),
            ProtocolNote(
                "Benzodiazepine equivalence differs between Indian sources: ICMR STW states lorazepam 2 mg equals diazepam 5 mg; the MoHFW STG table gives lorazepam 2 mg ≈ diazepam 10 mg.",
                listOf(cite(ALC_ICMR, "Withdrawal management"), cite(ALC_MOHFW, "Table 6 (p. 25)")),
            ),
            ProtocolNote(
                "Delirium tremens: NICE CG100 starts with oral lorazepam; MoHFW STG uses repeated IV diazepam boluses; WHO mhGAP adds haloperidol when diazepam fails.",
                listOf(cite(ALC_NICE100, "Rec 1.1.4.1"), cite(ALC_MOHFW, "Management of delirium tremens (pp. 28–29)"), cite(ALC_MHGAP, "SUB — Emergency presentations (p. 110)")),
            ),
            ProtocolNote(
                "IPS 2023 lists metadoxine (single 300–600 mg IV/IM dose) to speed alcohol elimination in adults; NICE, MoHFW, ICMR and WHO mhGAP do not mention it.",
                listOf(cite(ALC_IPS, "Management of alcohol intoxication — symptomatic management")),
            ),
            ProtocolNote(
                "Scope: methanol ('hooch', spurious liquor) poisoning is covered in the methanol protocol; the MoHFW alcohol-dependence STG explicitly excludes it.",
                listOf(cite(ALC_MOHFW, "Scope — groups not covered")),
            ),
        ),
        citations = listOf(
            cite(ALC_NICE100), cite(ALC_NICE115), cite(ALC_MOHFW), cite(ALC_ICMR), cite(ALC_IPS), cite(ALC_MHGAP),
        ),
    ),

    // =============================================================================================
    // Methanol and ethylene glycol
    // =============================================================================================
    Protocol(
        id = "methanol",
        category = ProtocolCategory.Poisoning,
        title = "Methanol and ethylene glycol (toxic alcohols)",
        summary = "Spurious liquor (hooch) and antifreeze: recognition, anion and osmolar gaps, fomepizole or ethanol, bicarbonate, " +
            "folate and haemodialysis — attributed to FDA, EXTRIP, AACT, MSF and Indian reports.",
        keywords = listOf(
            "methanol", "methyl alcohol", "wood alcohol", "hooch", "hooch tragedy", "illicit liquor", "kachi daru",
            "spurious liquor", "zehrili sharab", "country liquor", "desi daru", "khopadi", "ethylene glycol", "antifreeze",
            "coolant", "toxic alcohol", "fomepizole", "formic acid", "blindness", "anion gap", "osmolar gap",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Suspect toxic alcohol poisoning",
                timing = "Symptoms typically after a 12–24 h latent period (AACT 2002; MSF)",
                points = listOf(
                    "MSF protocol: suspect after illicit, bootleg or spurious liquor, or when others are ill, blind or dead — 'where there is one there are usually many'.",
                    "AACT 2002: early nausea, vomiting, abdominal pain and mild CNS depression; after 12–24 h, metabolic acidosis and visual impairment up to blindness.",
                    "MSF protocol: hyperventilation (RR >20–25/min), visual disturbance, vomiting or abdominal pain, chest pain, a severe 'hangover' 1–2 days after drinking.",
                    "IJCCM 2019 (India): clusters of patients smelling of alcohol; afferent pupillary defect, scotomas, disc hyperaemia; even advanced acidosis may lack Kussmaul breathing.",
                    "MSF protocol: a shorter interval from drinking to symptoms makes methanol unlikely; always consider diabetic ketoacidosis, renal failure and sepsis.",
                    "FDA fomepizole label: metabolites cause acidosis, seizures, coma, calcium oxaluria, acute tubular necrosis, blindness and death; renal failure with ethylene glycol, visual disturbance with methanol.",
                ),
                checklistTitle = "Clues",
                checklist = listOf(
                    "Recent illicit or spurious liquor; several patients at once (MSF; IJCCM 2019)",
                    "Blurred vision or blindness (Ahmedabad 2009: 73% had visual symptoms)",
                    "Afferent pupillary defect, floaters, scotomas (IJCCM 2019)",
                    "Optic disc hyperaemia, pallor or papilloedema (Ahmedabad 2009)",
                    "Hyperventilation or breathlessness (MSF)",
                    "Coma or seizures — late, poor prognosis (MSF; IJCCM 2019)",
                ),
                citations = listOf(
                    cite(MEOH_MSF, "§1 Diagnosis"),
                    cite(MEOH_AACT, "Abstract — Features"),
                    cite(MEOH_IJCCM, "Spurious alcohol poisoning"),
                    cite(MEOH_FDA, "Dosage and administration — treatment guidelines; Precautions"),
                    cite(MEOH_AHD, "Results"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Blood gas, anion gap and osmolar gap",
                timing = "Repeat frequently (FDA label)",
                points = listOf(
                    "FDA label: parent alcohol levels fall as toxic metabolites form; monitor methanol or ethylene glycol levels and the anion gap or arterial gases frequently.",
                    "EXTRIP 2015: pH ≤7.15 or anion gap >24 mmol/L from methanol marks severe poisoning; without a methanol level, the osmolal gap may be informative.",
                    "MSF protocol: the osmolal gap needs a freezing-point osmometer and methanol assays are rarely available; with blood gases, use base deficit or bicarbonate.",
                    "IJCCM 2019: confirming methanol by GC-MS is not timely and not required; monitor electrolytes, renal function and ECG.",
                    "FDA label: also check electrolytes, BUN, creatinine, urinalysis and ECG; in ethylene glycol poisoning, serum and urine levels and urinary oxalate crystals.",
                    "MSF protocol (HCO₃ >10 mmol/L, no visual disturbance): 1–2 L saline with thiamine and glucose within 1 h; improvement suggests alcoholic or diabetic ketoacidosis.",
                ),
                tool = ProtocolTool.AcidBase,
                citations = listOf(
                    cite(MEOH_FDA, "Dosage and administration — treatment guidelines; Precautions — laboratory tests"),
                    cite(MEOH_EXTRIP, "Abstract — Results"),
                    cite(MEOH_MSF, "§1 Diagnosis; §2 Management; Flow-chart 2"),
                    cite(MEOH_IJCCM, "Spurious alcohol poisoning"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Block alcohol dehydrogenase: fomepizole",
                timing = "Start immediately on suspicion (FDA label)",
                points = listOf(
                    "FDA label: start on history, anion-gap acidosis, raised osmolar gap, visual disturbance, oxalate crystals, or a methanol or ethylene glycol level >20 mg/dL.",
                    "FDA label: 15 mg/kg, then 10 mg/kg every 12 h for 4 doses, then 15 mg/kg every 12 h; each in ≥100 mL saline or D5W over 30 min.",
                    "FDA label: stop when the level is undetectable or <20 mg/dL and the patient is asymptomatic with a normal pH.",
                    "FDA label: dialysable — dose every 4 h during haemodialysis. MSF protocol: every 8 h during continuous renal replacement therapy.",
                    "AACT 2002: fomepizole is preferred; ethanol needs complex dosing and more monitoring and causes more adverse effects. IJCCM 2019: fomepizole is not available in India.",
                    "MSF protocol: if supplies are limited, give fomepizole for the first 24 h (2–3 doses), then continue with ethanol.",
                ),
                checklistTitle = "Fomepizole around haemodialysis (FDA label)",
                checklist = listOf(
                    "Start of dialysis: <6 h since last dose — no dose; ≥6 h — give the next scheduled dose",
                    "During dialysis: dose every 4 h",
                    "End of dialysis: last dose <1 h before — none; 1–3 h — half the next dose; >3 h — next scheduled dose",
                    "Off dialysis: next dose 12 h after the last one",
                ),
                doses = listOf(
                    StepDose(
                        label = "Fomepizole — loading (FDA label)",
                        dose = WeightDose(minPerKg = 15.0, maxPerKg = 15.0, unit = "mg", per = "IV over 30 min"),
                        note = "Dilute in at least 100 mL 0.9% saline or 5% dextrose; never as a bolus. No polycarbonate syringes or needles.",
                    ),
                    StepDose(
                        label = "Fomepizole — next 4 doses (FDA label)",
                        dose = WeightDose(minPerKg = 10.0, maxPerKg = 10.0, unit = "mg", per = "IV over 30 min every 12 h, 4 doses"),
                    ),
                    StepDose(
                        label = "Fomepizole — thereafter (FDA label)",
                        dose = WeightDose(minPerKg = 15.0, maxPerKg = 15.0, unit = "mg", per = "IV over 30 min every 12 h"),
                        note = "Until the level is undetectable or <20 mg/dL and the patient is asymptomatic with normal pH.",
                    ),
                ),
                citations = listOf(
                    cite(MEOH_FDA, "Indications and usage; Dosage and administration — dosing; dosage with renal dialysis; Precautions"),
                    cite(MEOH_AACT, "Abstract — Management; Conclusion"),
                    cite(MEOH_IJCCM, "Spurious alcohol poisoning"),
                    cite(MEOH_MSF, "§7 Suggested dosing — fomepizole"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Ethanol if fomepizole is unavailable",
                timing = "Without delay; continue 5–7 days if no dialysis (MSF)",
                points = listOf(
                    "MSF protocol: oral and IV ethanol doses are the same; expect individual variation and frequent under-dosing. Spirits are 40–45% ethanol; dilute stronger liquids.",
                    "MSF protocol, 40% ethanol: load 2 mL/kg, then 0.25 mL/kg/h (not a regular drinker) or 0.5 mL/kg/h (regular drinker); higher rates during haemodialysis.",
                    "IJCCM 2019 (India): oral or nasogastric liquor as a last resort, targeting serum ethanol 100 mg/dL; load about 1.8 mL/kg of 42.8% liquor, diluted with fruit juice.",
                    "IJCCM 2019: watch for hypoglycaemia, dehydration, sedation and vomiting; protect the airway.",
                    "MSF protocol: unconscious with normal or slow breathing — be careful with ethanol, as this may be ethanol intoxication rather than methanol.",
                    "FDA fomepizole label: fomepizole slows ethanol elimination by about 40% and ethanol slows fomepizole elimination by about 50%.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Ethanol 40% (spirits) — loading (MSF)",
                        dose = WeightDose(minPerKg = 2.0, maxPerKg = 2.0, unit = "mL", per = "of 40% ethanol, oral, nasogastric or IV"),
                        note = "MSF table equivalents: 20% ethanol 4 mL/kg; 10% ethanol 7.5 mL/kg; 5% ethanol 15 mL/kg.",
                    ),
                    StepDose(
                        label = "Ethanol 40% — maintenance, not a regular drinker (MSF)",
                        dose = WeightDose(minPerKg = 0.25, maxPerKg = 0.25, unit = "mL", per = "of 40% ethanol per hour"),
                        note = "During haemodialysis 0.5 mL/kg/h. As 10% ethanol: 1 mL/kg/h (2 mL/kg/h during dialysis).",
                    ),
                    StepDose(
                        label = "Ethanol 40% — maintenance, regular drinker (MSF)",
                        dose = WeightDose(minPerKg = 0.5, maxPerKg = 0.5, unit = "mL", per = "of 40% ethanol per hour"),
                        note = "During haemodialysis 0.8 mL/kg/h. As 10% ethanol: 2 mL/kg/h (3 mL/kg/h during dialysis).",
                    ),
                ),
                citations = listOf(
                    cite(MEOH_MSF, "§7 Suggested dosing — ethanol table (p. 6); §2 Management"),
                    cite(MEOH_IJCCM, "Spurious alcohol poisoning"),
                    cite(MEOH_FDA, "Precautions — drug interactions"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Bicarbonate, folate and supportive care",
                points = listOf(
                    "AACT 2002: correct acidosis with IV sodium bicarbonate, block metabolism with fomepizole or ethanol, and give IV folinic acid to speed formate metabolism.",
                    "MSF protocol: bicarbonate (mmol) = 0.3 × weight (kg) × base deficit. Without blood gases: 8.4% — 7–15 ampoules of 20 mL over 1–2 h until RR <20/min.",
                    "MSF protocol: folic or folinic acid 50 mg IV or orally every 6 h for 24–48 h.",
                    "FDA label: supportive care includes fluids, bicarbonate, potassium and calcium supplementation and oxygen; watch for ARDS, renal failure and hypocalcaemia.",
                    "MSF protocol: if intubated, hyperventilate (RR >25/min) until the metabolic acidosis is corrected.",
                    "EXTRIP 2015: continue alcohol dehydrogenase blockers and folic or folinic acid during extracorporeal treatment.",
                ),
                citations = listOf(
                    cite(MEOH_AACT, "Abstract — Management"),
                    cite(MEOH_MSF, "§3 Treatment; §7 Suggested dosing — bicarbonate, folic acid"),
                    cite(MEOH_FDA, "Precautions — laboratory tests"),
                    cite(MEOH_EXTRIP, "Abstract — Results"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Haemodialysis for methanol",
                timing = "Urgently in severe poisoning (EXTRIP 2015)",
                points = listOf(
                    "EXTRIP 2015: intermittent haemodialysis is the modality of choice; continuous modalities are acceptable alternatives.",
                    "EXTRIP 2015: stop when methanol is <200 mg/L (6.2 mmol/L) with clinical improvement; avoid systemic anticoagulation (risk of intracerebral haemorrhage).",
                    "EXTRIP 2015: raised methanol without severe features and with adequate blockade — dialysis is not immediately required.",
                    "FDA label: consider dialysis with fomepizole for renal failure, significant or worsening metabolic acidosis, or a methanol or ethylene glycol level ≥50 mg/dL.",
                    "MSF protocol: intermittent dialysis 6–8 h or continuous ≥18 h; peritoneal dialysis ≥48 h (little data); with few dialysers, rotate them between patients.",
                    "IJCCM 2019 (India): haemodialysis is the definitive treatment; early dialysis for all acidotic patients prevents blindness and other complications.",
                ),
                checklistTitle = "EXTRIP 2015: dialyse methanol poisoning with any of",
                checklist = listOf(
                    "Coma",
                    "Seizures",
                    "New vision deficits",
                    "Metabolic acidosis with blood pH ≤7.15",
                    "Persistent acidosis despite adequate supportive measures and antidotes",
                    "Anion gap >24 mmol/L",
                    "Methanol >700 mg/L (21.8 mmol/L) on fomepizole",
                    "Methanol >600 mg/L (18.7 mmol/L) on ethanol",
                    "Methanol >500 mg/L (15.6 mmol/L) with no alcohol dehydrogenase blocker",
                    "Impaired kidney function",
                ),
                citations = listOf(
                    cite(MEOH_EXTRIP, "Abstract — Results"),
                    cite(MEOH_FDA, "Dosage and administration — hemodialysis"),
                    cite(MEOH_MSF, "§3 Treatment — dialysis"),
                    cite(MEOH_IJCCM, "Spurious alcohol poisoning"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Ethylene glycol (antifreeze)",
                points = listOf(
                    "FDA label: the same fomepizole regimen applies; monitor serum and urine ethylene glycol, urinary oxalate crystals and renal function; manage hypocalcaemia.",
                    "EXTRIP 2023: do not dialyse on the reported dose alone; prefer intermittent haemodialysis, or continuous kidney replacement therapy if it is unavailable.",
                    "EXTRIP 2023: stop when the anion gap is <18 mmol/L (recommended), or ethylene glycol <4 mmol/L (25 mg/dL) or acid–base is corrected (suggested).",
                    "EXTRIP 2023: anion gap = Na⁺ + K⁺ − Cl⁻ − HCO₃⁻; osmol gap adjusted for ethanol; antidote doses need adjusting during dialysis.",
                ),
                checklistTitle = "EXTRIP 2023: dialysis for ethylene glycol",
                checklist = listOf(
                    "Recommended: coma or seizures",
                    "Recommended: acute kidney injury, KDIGO stage 2–3",
                    "Recommended: glycolate >12 mmol/L or anion gap >27 mmol/L",
                    "Recommended (on ethanol): ethylene glycol >50 mmol/L (>310 mg/dL) or osmol gap >50",
                    "Recommended (no antidote): ethylene glycol >10 mmol/L (>62 mg/dL) or osmol gap >10",
                    "Suggested (on fomepizole): ethylene glycol >50 mmol/L or osmol gap >50",
                    "Suggested: glycolate 8–12 mmol/L or anion gap 23–27 mmol/L",
                    "Suggested (on ethanol): ethylene glycol 20–50 mmol/L or osmol gap 20–50",
                    "Suggested: chronic kidney disease with eGFR <45 mL/min/1.73 m²",
                ),
                citations = listOf(
                    cite(MEOH_FDA, "Indications and usage; Precautions — laboratory tests"),
                    cite(MEOH_EXTRIP_EG, "Table 8; Abstract"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Outbreaks, prognosis and observation",
                points = listOf(
                    "MSF protocol: call the referral hospital early — to start treatment and to warn the public; where there is one there are usually many.",
                    "MSF protocol: observe suspected asymptomatic patients for up to 24 h by level of suspicion, reassessing every 1–4 h; after treatment ends, observe at least 24 h.",
                    "MSF protocol: poor prognosis — coma, pH <6.9–7.0 with HCO₃ <10 or base deficit >20, and no hyperventilation despite acidosis.",
                    "Ahmedabad 2009 outbreak (178 patients): those who died had lower pH (6.94 vs 7.23) and bicarbonate (7.5 vs 13.3 mmol/L); mean presentation 30 h after drinking.",
                    "EXTRIP 2015: when need exceeds resources in an epidemic, how to rank dialysis indications for triage is unknown.",
                    "IJCCM 2019: spurious alcohol is India's most prevalent alcohol-related poisoning; a 2015 BMJ report counted at least 700 hooch deaths over 15 years.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(MEOH_MSF, "When to call for assistance; §2 Management; Flow-charts 1–2; Prognostic aspects"),
                    cite(MEOH_AHD, "Results; Discussion"),
                    cite(MEOH_EXTRIP, "Abstract — General considerations"),
                    cite(MEOH_IJCCM, "Spurious alcohol poisoning"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Dialysis thresholds differ: the FDA label uses ≥50 mg/dL; EXTRIP 2015 uses 700, 600 or 500 mg/L (70, 60 or 50 mg/dL) depending on the antidote given. Check the unit your laboratory reports.",
                listOf(cite(MEOH_FDA, "Dosage and administration — hemodialysis"), cite(MEOH_EXTRIP, "Abstract — Results")),
            ),
            ProtocolNote(
                "Antidote: AACT 2002 prefers fomepizole; the IJCCM 2019 review reported it unavailable in India, so ethanol — often oral liquor — is used. Current Indian availability was not verified.",
                listOf(cite(MEOH_AACT, "Abstract — Conclusion"), cite(MEOH_IJCCM, "Spurious alcohol poisoning")),
            ),
            ProtocolNote(
                "Ethanol maintenance differs: MSF gives 0.25 mL/kg/h (not a regular drinker) or 0.5 mL/kg/h (regular drinker) of 40% ethanol; the IJCCM 2019 review gives 0.83 mL/kg/h of 42.8% liquor, targeting 100 mg/dL.",
                listOf(cite(MEOH_MSF, "§7 Suggested dosing — ethanol table"), cite(MEOH_IJCCM, "Spurious alcohol poisoning")),
            ),
            ProtocolNote(
                "Folate: AACT 2002 names IV folinic acid; the MSF protocol accepts folic or folinic acid, 50 mg every 6 h.",
                listOf(cite(MEOH_AACT, "Abstract — Management"), cite(MEOH_MSF, "§3 Treatment")),
            ),
            ProtocolNote(
                "Evidence: EXTRIP 2015 and AACT 2002 were reviewed from their abstracts only; EXTRIP 2023 grades every ethylene glycol recommendation as very-low-quality evidence. The MSF protocol is an NGO field protocol.",
                listOf(cite(MEOH_EXTRIP), cite(MEOH_AACT), cite(MEOH_EXTRIP_EG, "Abstract"), cite(MEOH_MSF)),
            ),
        ),
        citations = listOf(
            cite(MEOH_FDA), cite(MEOH_EXTRIP), cite(MEOH_EXTRIP_EG), cite(MEOH_AACT), cite(MEOH_MSF), cite(MEOH_IJCCM), cite(MEOH_AHD),
        ),
    ),
)
