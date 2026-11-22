package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.ProtocolCategory
import com.medicalaid.app.content.protocols.ProtocolNote
import com.medicalaid.app.content.protocols.ProtocolTool
import com.medicalaid.app.content.protocols.Step

/*
 * Acid–base disorders: metabolic acidosis, metabolic alkalosis, respiratory acidosis, respiratory alkalosis.
 * Rules:
 * - Thresholds, doses and time windows exactly as the cited document states them; kPa values are converted to
 *   mm Hg only where marked "≈".
 * - Every point is attributed ("SRLF/SFMU 2019: …", "BTS/ICS 2016: …", "ICMR STW: …", "GOLD 2027: …").
 * - Guideline locators are recommendation numbers (SRLF/SFMU R1.1…, BTS/ICS Rec 1…, BTS 2017 A1…/G6, ISCCM grades),
 *   figure/table numbers (GOLD, ICMR STW boxes) or section names; trials are cited from their published abstracts.
 * - The first step of every protocol embeds the ABG interpreter (ProtocolTool.AcidBase), which follows Berend et al.
 *   NEJM 2014 (ToolSources.kt, "tool-acid-base").
 * Existing sources cited from other files: tool-acid-base (ToolSources.kt), tx-meoh-extrip-eg-2023 (ToxAlcohols.kt),
 * tx-pest-gurjar-jets-2011 (Pesticides.kt).
 */

internal val acidBaseSources = listOf(
    // ---------------------------------------------------------------- Metabolic acidosis
    Source(
        "em-ab-srlf-sfmu-2019", Authority.Paper, "SRLF/SFMU formal expert guidelines (Ann Intensive Care 2019;9:92)",
        "Diagnosis and management of metabolic acidosis: guidelines from a French expert panel", "2019",
        "https://doi.org/10.1186/s13613-019-0563-2",
        status = "French Intensive Care Society and French Emergency Medicine Society; GRADE — 29 recommendations " +
            "(4 strong, 10 weak, 15 expert opinion)",
    ),
    Source(
        "em-ab-ssc-2026", Authority.SSC, "International guidelines (Crit Care Med 2026; Intensive Care Med 2026;52(5):863–936)",
        "Surviving Sepsis Campaign: International Guidelines for Management of Sepsis and Septic Shock 2026", "2026",
        "https://doi.org/10.1097/CCM.0000000000007075",
        status = "Replaces the 2021 adult guidelines; publisher correction Intensive Care Med 2026;52(6):1411–1413; " +
            "recommendation wording checked on the SCCM guideline page",
    ),
    Source(
        "em-ab-bicar-icu-2018", Authority.Paper, "Phase 3 RCT, 26 French ICUs (Lancet 2018;392(10141):31–40)",
        "Sodium bicarbonate therapy for patients with severe metabolic acidaemia in the intensive care unit (BICAR-ICU): " +
            "a multicentre, open-label, randomised controlled, phase 3 trial",
        "2018",
        "https://doi.org/10.1016/S0140-6736(18)31080-8",
        status = "Reviewed from the published (corrected) abstract",
    ),
    Source(
        "em-ab-bicaricu2-2025", Authority.Paper, "RCT, 43 French ICUs (JAMA 2025;334(22):2000–2010)",
        "Sodium Bicarbonate for Severe Metabolic Acidemia and Acute Kidney Injury: The BICARICU-2 Randomized Clinical Trial",
        "2025",
        "https://doi.org/10.1001/jama.2025.20231",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "em-ab-smart-2018", Authority.Paper, "Cluster-randomised multiple-crossover trial, 5 ICUs (N Engl J Med 2018;378(9):829–839)",
        "Balanced Crystalloids versus Saline in Critically Ill Adults (SMART)", "2018",
        "https://doi.org/10.1056/NEJMoa1711584",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "em-ab-plus-2022", Authority.Paper, "Double-blind RCT, 53 ICUs (N Engl J Med 2022;386(9):815–826)",
        "Balanced Multielectrolyte Solution versus Saline in Critically Ill Adults (PLUS)", "2022",
        "https://doi.org/10.1056/NEJMoa2114464",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "em-ab-ada-hgc-2024", Authority.ADA, "ADA/EASD/JBDS/AACE/DTS consensus report (Diabetes Care 2024;47(8):1257–1275)",
        "Hyperglycemic Crises in Adults With Diabetes: A Consensus Report", "2024",
        "https://doi.org/10.2337/dci24-0032",
        status = "Co-published in Diabetologia 2024;67(8):1455–1479 (open-access version reviewed)",
    ),
    Source(
        "em-ab-icmr-stw-dka-2022", Authority.ICMR, "Standard Treatment Workflow (ICD-10 E11.10)",
        "Diabetic Ketoacidosis", "2022",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567244_diabetic_ketoacidosis.pdf",
    ),
    Source(
        "em-ab-icmr-stw-aki-2026", Authority.ICMR, "Standard Treatment Workflow (ICD-11 GB60)",
        "Acute Kidney Injury", "2026",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1784807239_acutekidneyinjury-updated.pdf",
        status = "Updated July 2026",
    ),
    Source(
        "em-ab-achanti-cjasn-2023", Authority.Paper, "Review (Clin J Am Soc Nephrol 2023;18(1):102–112)",
        "Acid-Base Disorders in the Critically Ill Patient", "2023",
        "https://doi.org/10.2215/CJN.04500422",
        status = "Reviewed from the published abstract",
    ),
    // ---------------------------------------------------------------- Metabolic alkalosis
    Source(
        "em-ab-emmett-cjasn-2020", Authority.Paper, "Review (Clin J Am Soc Nephrol 2020;15(12):1848–1856)",
        "Metabolic Alkalosis: A Brief Pathophysiologic Review", "2020",
        "https://doi.org/10.2215/CJN.16041219",
    ),
    Source(
        "em-ab-do-ajkd-2022", Authority.Paper, "AJKD Core Curriculum in Nephrology (Am J Kidney Dis 2022;80(4):536–551)",
        "Metabolic Alkalosis Pathogenesis, Diagnosis, and Treatment: Core Curriculum 2022", "2022",
        "https://doi.org/10.1053/j.ajkd.2021.12.016",
        status = "Author manuscript (PMC10947768) reviewed",
    ),
    Source(
        "em-ab-tinawi-cureus-2021", Authority.Paper, "Narrative review (Cureus 2021;13(1):e12841)",
        "Pathophysiology, Evaluation, and Management of Metabolic Alkalosis", "2021",
        "https://doi.org/10.7759/cureus.12841",
        status = "Single-author narrative review; the only reviewed source giving acetazolamide and HCl practicalities",
    ),
    Source(
        "em-ab-diabolo-2016", Authority.Paper, "Double-blind RCT, 15 French ICUs (JAMA 2016;315(5):480–488)",
        "Effect of Acetazolamide vs Placebo on Duration of Invasive Mechanical Ventilation Among Patients With " +
            "Chronic Obstructive Pulmonary Disease (DIABOLO)",
        "2016",
        "https://doi.org/10.1001/jama.2016.0019",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "em-ab-fda-acetazolamide", Authority.FDA, "Prescribing information (DailyMed; Gland Pharma, 500 mg vial)",
        "Acetazolamide for Injection, USP", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=2b9fb0dd-e42e-4f9b-bf24-98b268d877e5",
        status = "Label published June 2025; acid–base disorders are not a labelled indication",
    ),
    // ---------------------------------------------------------------- Respiratory acidosis
    Source(
        "em-ab-bts-ics-ahrf-2016", Authority.BTS, "BTS/ICS guideline (Thorax 2016;71 Suppl 2:ii1–ii35)",
        "BTS/ICS guideline for the ventilatory management of acute hypercapnic respiratory failure in adults", "2016",
        "https://doi.org/10.1136/thoraxjnl-2015-208209",
        status = "British Thoracic Society and Intensive Care Society; recommendation numbers as in the summary " +
            "(BMJ Open Respir Res 2016;3:e000133); excludes cardiac failure, trauma and acute brain injury",
    ),
    Source(
        "em-ab-bts-oxygen-2017", Authority.BTS, "BTS guideline (Thorax 2017;72 Suppl 1:ii1–ii90)",
        "BTS guideline for oxygen use in adults in healthcare and emergency settings", "2017",
        "https://doi.org/10.1136/thoraxjnl-2016-209729",
        status = "Current; a BTS guideline on target oxygen saturations is in development for 2027. Recommendation " +
            "codes as in the summary (BMJ Open Respir Res 2017;4:e000170)",
    ),
    Source(
        "em-ab-ers-ats-niv-2017", Authority.Paper, "ERS/ATS clinical practice guideline (Eur Respir J 2017;50(2):1602426)",
        "Official ERS/ATS clinical practice guidelines: noninvasive ventilation for acute respiratory failure", "2017",
        "https://doi.org/10.1183/13993003.02426-2016",
        status = "European Respiratory Society / American Thoracic Society; GRADE",
    ),
    Source(
        "em-ab-gold-2027", Authority.GOLD, "GOLD Report 2027 (v1.1, 20 November 2026)",
        "Global Strategy for the Diagnosis, Management, and Prevention of Chronic Obstructive Pulmonary Disease (2027 Report)",
        "2026",
        "https://goldcopd.org/2027-gold-report-and-pocket-guide/",
        status = "Replaces the 2026 report",
    ),
    Source(
        "em-ab-isccm-niv-2020", Authority.ISCCM, "ISCCM guideline (Indian J Crit Care Med 2020;24(Suppl 1):S61–S81)",
        "ISCCM Guidelines for the Use of Non-invasive Ventilation in Acute Respiratory Failure in Adult ICUs", "2020",
        "https://doi.org/10.5005/jp-journals-10071-G23186",
    ),
    Source(
        "em-ab-icmr-stw-resp-failure-2019", Authority.ICMR, "Standard Treatment Workflow (ICD-10 J96.0)",
        "Management of Respiratory Failure", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952328_pulmonology_respiratory_failure.pdf",
    ),
    Source(
        "em-ab-icmr-stw-copd-2019", Authority.ICMR, "Standard Treatment Workflow (ICD-10 J44.9)",
        "Management of Chronic Obstructive Pulmonary Disease", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952329_pulmonology_chronic_obstructive_pulmonary_disease.pdf",
    ),
    Source(
        "em-ab-ncp-resp-2025", Authority.Paper, "Invited review (Nutr Clin Pract 2025;40(4):774–792)",
        "Breathing and balance: clinical insights and management strategies of respiratory acid–base disorders", "2025",
        "https://doi.org/10.1002/ncp.11328",
        status = "Narrative review by clinical pharmacists, not a guideline",
    ),
    // ---------------------------------------------------------------- Respiratory alkalosis
    Source(
        "em-ab-lomauro-breathe-2015", Authority.Paper, "ERS physiology masterclass (Breathe 2015;11(4):297–301)",
        "Respiratory physiology of pregnancy", "2015",
        "https://doi.org/10.1183/20734735.008615",
    ),
    Source(
        "em-ab-callaham-1989", Authority.Paper, "Case reports and volunteer study (Ann Emerg Med 1989;18(6):622–628)",
        "Hypoxic hazards of traditional paper bag rebreathing in hyperventilating patients", "1989",
        "https://doi.org/10.1016/S0196-0644(89)80515-3",
        status = "Reviewed from the published abstract",
    ),
    Source(
        "em-ab-icmr-stw-anxiety-2019", Authority.ICMR, "Standard Treatment Workflow (ICD-10 F40–F42)",
        "Management of Anxiety Disorders", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952333_psychiatry_anxiety_disorder.pdf",
    ),
)

private const val SRLF = "em-ab-srlf-sfmu-2019"
private const val SSC26 = "em-ab-ssc-2026"
private const val BICAR1 = "em-ab-bicar-icu-2018"
private const val BICAR2 = "em-ab-bicaricu2-2025"
private const val SMART = "em-ab-smart-2018"
private const val PLUS = "em-ab-plus-2022"
private const val ADA = "em-ab-ada-hgc-2024"
private const val ICMR_DKA = "em-ab-icmr-stw-dka-2022"
private const val ICMR_AKI = "em-ab-icmr-stw-aki-2026"
private const val ACHANTI = "em-ab-achanti-cjasn-2023"
private const val EMMETT = "em-ab-emmett-cjasn-2020"
private const val DO_AJKD = "em-ab-do-ajkd-2022"
private const val TINAWI = "em-ab-tinawi-cureus-2021"
private const val DIABOLO = "em-ab-diabolo-2016"
private const val FDA_ACZ = "em-ab-fda-acetazolamide"
private const val BTS_ICS = "em-ab-bts-ics-ahrf-2016"
private const val BTS_O2 = "em-ab-bts-oxygen-2017"
private const val ERS_ATS = "em-ab-ers-ats-niv-2017"
private const val GOLD = "em-ab-gold-2027"
private const val ISCCM = "em-ab-isccm-niv-2020"
private const val ICMR_RF = "em-ab-icmr-stw-resp-failure-2019"
private const val ICMR_COPD = "em-ab-icmr-stw-copd-2019"
private const val NCP = "em-ab-ncp-resp-2025"
private const val BREATHE = "em-ab-lomauro-breathe-2015"
private const val CALLAHAM = "em-ab-callaham-1989"
private const val ICMR_ANX = "em-ab-icmr-stw-anxiety-2019"

// Defined in other files.
private const val TOOL = "tool-acid-base" // ToolSources.kt — Berend et al., NEJM 2014 (ABG interpreter)
private const val EXTRIP_EG = "tx-meoh-extrip-eg-2023" // ToxAlcohols.kt
private const val GURJAR = "tx-pest-gurjar-jets-2011" // Pesticides.kt

internal val acidBaseProtocols = listOf(
    // =============================================================================================
    // Metabolic acidosis
    // =============================================================================================
    Protocol(
        id = "metabolic-acidosis",
        category = ProtocolCategory.Metabolic,
        title = "Metabolic acidosis",
        summary = "Stepwise blood-gas reading, anion and osmolar gaps, lactate and ketones, treating the cause, saline " +
            "hyperchloraemia and when IV sodium bicarbonate is supported — attributed to SRLF/SFMU, SSC 2026, ICMR STWs and trials.",
        keywords = listOf(
            "metabolic acidosis", "acidosis", "acidaemia", "acidemia", "low bicarbonate", "low HCO3", "base deficit",
            "anion gap", "AG", "high anion gap", "HAGMA", "normal anion gap", "NAGMA", "non-gap acidosis",
            "hyperchloraemic acidosis", "hyperchloremic acidosis", "delta ratio", "delta gap", "osmolar gap", "osmolal gap",
            "lactic acidosis", "lactate", "ketoacidosis", "DKA", "euglycaemic DKA", "Kussmaul breathing", "sodium bicarbonate",
            "soda bicarb", "NaHCO3", "BICAR-ICU", "ABG", "VBG", "blood gas", "Winters formula", "urinary anion gap", "RTA",
            "normal saline", "balanced crystalloid", "Ringer lactate", "RL", "Plasma-Lyte", "dialysis",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Read the gas",
                points = listOf(
                    "SRLF/SFMU 2019: when plasma bicarbonate is low, do an arterial gas to exclude respiratory alkalosis, confirm metabolic acidosis and detect a mixed disorder.",
                    "SRLF/SFMU 2019: expected PaCO₂ = 1.5 × HCO₃ + 8 ± 2 mm Hg; a measured PaCO₂ above this means an added respiratory acidosis.",
                    "SRLF/SFMU 2019: venous and arterial pH agree closely (mean difference −0.03), but venous PCO₂ does not (limits of agreement about −20 to +26 mm Hg).",
                    "SRLF/SFMU 2019 (Grade 2+): AG = Na⁺ − (Cl⁻ + HCO₃⁻), 12 ± 4 mmol/L; prefer the albumin-corrected AG = AG + 0.25 × (40 − albumin g/L).",
                    "NCP 2025 review: with a raised gap, delta ratio = (AG − 12) ÷ (24 − HCO₃): 0.8–1.2 pure; <0.8 plus normal-gap acidosis; >1.2 plus metabolic alkalosis.",
                    "SRLF/SFMU 2019: respiratory compensation in acute metabolic acidosis cannot bring the pH above 7.40.",
                ),
                tool = ProtocolTool.AcidBase,
                citations = listOf(
                    cite(SRLF, "R1.1 and rationale; R1.3 and rationale; R1.5 rationale"),
                    cite(NCP, "Tables 3 and 5"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "High or normal anion gap?",
                points = listOf(
                    "SRLF/SFMU 2019: a raised gap means acid with an anion other than chloride — lactate, acetoacetate, β-hydroxybutyrate, oxalate, glycolate, formate, salicylate or sulphate.",
                    "SRLF/SFMU 2019: hypoalbuminaemia lowers the gap and can hide lactate or ketoacids — common in ICU, malnutrition, liver disease and albuminuria.",
                    "CJASN 2023 review: normal-gap acidosis — tubular H⁺ transport defects, low renal ammonia excretion, GI or renal bicarbonate loss, IV-fluid dilution, or added HCl.",
                    "SRLF/SFMU 2019 (expert opinion): urinary anion gap only for normal-gap acidosis without obvious cause — negative suggests GI loss; zero or positive, tubular acidosis or hypoaldosteronism; its value in the ED or ICU is questionable.",
                    "AJKD 2022 review: acetazolamide wastes bicarbonate, causing a non-gap (hyperchloraemic) acidosis with hypokalaemia.",
                    "ADA 2024 consensus: about one-third of DKA presentations are mixed acid–base disorders (vomiting, osmotic diuresis, Kussmaul hyperventilation).",
                ),
                checklistTitle = "High anion gap: what to look for",
                checklist = listOf(
                    "Lactate — see step 4 (SRLF/SFMU Table 3)",
                    "Ketones — diabetic (including euglycaemic), alcoholic or starvation ketoacidosis (ADA 2024)",
                    "Methanol (formate) or ethylene glycol (glycolate, oxalate) — see step 3 (SRLF/SFMU)",
                    "Salicylate — often with respiratory alkalosis (SRLF/SFMU)",
                ),
                citations = listOf(
                    cite(SRLF, "R1.3 rationale; R1.4 rationale; R1.6 and rationale"),
                    cite(ACHANTI, "Abstract"),
                    cite(DO_AJKD, "Diuretic-induced metabolic alkalosis (carbonic anhydrase inhibitors)"),
                    cite(ADA, "Diagnosis — acid–base status; differential diagnosis of ketosis"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Osmolar gap and toxic alcohols",
                points = listOf(
                    "SRLF/SFMU 2019: some analysers misread glycolate from ethylene glycol as lactate — an apparent lactic acidosis can be a toxic alcohol.",
                    "EXTRIP 2023: osmol gap = measured osmolality − calculated osmolarity, adjusted for ethanol; it is a poor screening test, especially at low values.",
                    "EXTRIP 2023: many conditions and other alcohols raise the osmol gap; it may be normal or even negative once ethylene glycol is metabolised.",
                    "SRLF/SFMU 2019 (expert opinion): methanol or ethylene glycol — renal replacement therapy if anion gap >20 mEq/L, renal insufficiency or visual impairment.",
                    "EXTRIP 2023 (ethylene glycol) uses higher thresholds: anion gap (Na⁺ + K⁺ − Cl⁻ − HCO₃⁻) >27 mmol/L recommended, 23–27 suggested — follow the toxic alcohols protocol.",
                    "Suspected hooch (spurious liquor) or antifreeze ingestion: follow the 'Methanol and ethylene glycol (toxic alcohols)' protocol for fomepizole or ethanol and dialysis.",
                ),
                citations = listOf(
                    cite(SRLF, "R1.5 rationale; R3.12"),
                    cite(EXTRIP_EG, "Osmol gap — recommendation and rationale; Table 8 — indications (anion gap)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Lactate and ketones",
                timing = "Lactate at presentation; repeat over the first hours (SRLF/SFMU R2.3)",
                points = listOf(
                    "SRLF/SFMU 2019 (Grade 1+): any hyperlactataemia marks severity — manage rapidly; monitor lactate over the first hours to judge response (Grade 2+).",
                    "SSC 2026: measure lactate in possible sepsis; with raised lactate or shock, use serial lactate to guide resuscitation, not fluids until it normalises.",
                    "SRLF/SFMU 2019: a normal venous lactate excludes hyperlactataemia; confirm a raised venous value arterially (Grade 2+); never use capillary lactate (Grade 1−).",
                    "SRLF/SFMU 2019 Table 3: type A — shock, severe anaemia, CO poisoning, ischaemia, seizures; type B — sepsis, liver failure, severe malaria, metformin, cyanide, isoniazid, thiamine deficiency.",
                    "SRLF/SFMU 2019 (Grade 1+): measure capillary blood ketones rather than urine ketones; above 3 mmol/L with hyperglycaemia supports DKA.",
                    "ADA 2024 consensus: about 10% of DKA is euglycaemic (glucose <200 mg/dL [11.1 mmol/L]) — SGLT2 inhibitors, pregnancy, alcohol, reduced intake, liver failure.",
                ),
                scoreIds = listOf("qsofa"),
                citations = listOf(
                    cite(SRLF, "R1.8–R1.11; R2.2–R2.3; Table 3"),
                    cite(SSC26, "Recommendations: Blood lactate; Serial lactate"),
                    cite(ADA, "Diagnosis — euglycaemic DKA"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Treat the cause",
                points = listOf(
                    "CJASN 2023 review: treat high-gap acidosis by treating the underlying disease or removing the toxin; normalising the pH itself is controversial.",
                    "ICMR STW DKA 2022: IV fluids first, then IV insulin; give potassium before insulin if K⁺ <3.3 mEq/L (or ECG changes) — see the 'Diabetic ketoacidosis (DKA) — adult' protocol. Severity by pH and HCO₃ — mild 7.25–7.3/15–18, moderate 7.0–7.25/10–15, severe <7.0/<10.",
                    "SRLF/SFMU 2019: pH alone should probably not be used to identify critically ill patients (Grade 2−).",
                    "Gurjar 2011 (India): in aluminium phosphide (Celphos) poisoning, acidosis and shock are poor prognostic factors — see the 'Aluminium phosphide poisoning' protocol.",
                    "SRLF/SFMU 2019 (expert opinion): suspected metformin lactic acidosis — early renal replacement if organ dysfunction or no improvement in the first hours.",
                ),
                citations = listOf(
                    cite(ACHANTI, "Abstract"),
                    cite(ICMR_DKA, "Assess severity of DKA; Management — treatment; Common errors/pitfalls"),
                    cite(SRLF, "R2.1; R3.11"),
                    cite(GURJAR, "Abstract; Prognostic factors"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Saline and hyperchloraemic acidosis",
                points = listOf(
                    "SRLF/SFMU 2019: large volumes of chloride-rich fluid in circulatory failure cause hyperchloraemic acidosis, which can coexist with hyperlactataemia.",
                    "SRLF/SFMU 2019: the Stewart (strong ion) approach explains saline-related hyperchloraemic acidosis better than the anion gap method.",
                    "ADA 2024 consensus: normal-gap hyperchloraemic acidosis is common after DKA treatment with saline — judge resolution by ketones and pH or HCO₃, not the anion gap.",
                    "SSC 2026 (conditional, moderate certainty): balanced crystalloids over 0.9% saline for initial sepsis resuscitation; 0.9% saline if there is traumatic brain injury.",
                    "SMART 2018 (15,802 ICU adults): balanced crystalloids lowered 30-day major adverse kidney events versus saline (14.3% vs 15.4%).",
                    "PLUS 2022 (5037 ICU patients): Plasma-Lyte 148 versus saline — no difference in 90-day mortality (21.8% vs 22.0%) or new dialysis.",
                ),
                citations = listOf(
                    cite(SRLF, "R1.4 rationale; R1.5 rationale"),
                    cite(ADA, "Resolution of DKA; Table 3 (normal anion gap metabolic acidosis)"),
                    cite(SSC26, "Recommendation: Fluid type"),
                    cite(SMART, "Abstract — Results"),
                    cite(PLUS, "Abstract — Results"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "IV sodium bicarbonate: what the evidence supports",
                points = listOf(
                    "SRLF/SFMU 2019 (Grade 2+): bicarbonate should probably be given to ICU patients with pH ≤7.20, PaCO₂ <45 mm Hg and moderate to severe acute kidney injury.",
                    "SSC 2026: septic shock with pH ≤7.2 and AKIN stage 2–3 — bicarbonate suggested (very low certainty); not to improve haemodynamics in lactic acidaemia.",
                    "BICAR-ICU 2018 (389 ICU patients, pH ≤7.20): bicarbonate did not reduce the primary composite outcome; day-28 survival improved in the AKIN 2–3 stratum.",
                    "BICAR-ICU protocol: 4.2% sodium bicarbonate 125–250 mL over 30 min per infusion, maximum 1000 mL in 24 h, to keep arterial pH above 7.30.",
                    "BICARICU-2 2025 (627 patients, pH ≤7.20 with AKI): no difference in 90-day mortality (62.1% vs 61.7%); dialysis used less (35% vs 50%).",
                    "SRLF/SFMU 2019 and BICAR-ICU: bicarbonate causes hypokalaemia, hypernatraemia, hypocalcaemia, rebound alkalaemia and fluid overload — monitor.",
                ),
                checklistTitle = "SRLF/SFMU 2019: other situations",
                checklist = listOf(
                    "GI or renal bicarbonate loss with poor tolerance — consider (expert opinion)",
                    "Salicylate poisoning — give whatever the pH (expert opinion)",
                    "Diabetic ketoacidosis — should probably not be given (Grade 2−); ICMR STW and ADA 2024 thresholds in notes",
                    "Cardiac arrest — not routinely, except pre-existing hyperkalaemia or membrane-stabiliser poisoning (Grade 1−)",
                ),
                citations = listOf(
                    cite(SRLF, "R3.5–R3.9 and rationale"),
                    cite(SSC26, "Recommendations: Sodium bicarbonate"),
                    cite(BICAR1, "Abstract — Methods, Findings"),
                    cite(BICAR2, "Abstract — Results"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Dialysis and ventilation",
                timing = "Shock or AKI with pH ≤7.15 despite treatment, without severe respiratory acidosis (SRLF/SFMU R3.10)",
                points = listOf(
                    "SRLF/SFMU 2019 (expert opinion): with shock or AKI, start renal replacement if pH ≤7.15 despite appropriate treatment and without severe respiratory acidosis.",
                    "ICMR STW AKI 2026: refractory acidosis and intoxications are dialysis indications; a dialysis indication is a red flag for urgent referral.",
                    "SRLF/SFMU 2019 (expert opinion): salicylate — renal replacement if neurological involvement, level >6.5 mmol/L (90 mg/dL) or pH ≤7.20.",
                    "SRLF/SFMU 2019 (expert opinion): if ventilated, raise rate (max 35/min) and/or tidal volume (up to 8 mL/kg) without intrinsic PEEP, monitoring plateau pressure; aim pH ≥7.15, not normal.",
                    "SRLF/SFMU 2019: ventilatory compensation is only symptomatic and temporary — treat the acidosis and its cause at the same time.",
                ),
                citations = listOf(
                    cite(SRLF, "R3.10; R3.13; R3.14"),
                    cite(ICMR_AKI, "Indications for dialysis; Red flags for urgent referral"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Bicarbonate is contested: SRLF/SFMU 2019 and SSC 2026 base their suggestions on the BICAR-ICU AKI stratum; the " +
                    "confirmatory BICARICU-2 trial (2025) found no mortality benefit, though less dialysis.",
                listOf(cite(SRLF, "R3.6 rationale"), cite(SSC26, "Recommendations: Sodium bicarbonate"), cite(BICAR1, "Abstract"), cite(BICAR2, "Abstract")),
            ),
            ProtocolNote(
                "DKA bicarbonate differs. ICMR STW 2022: pH <7.0 — 50 mmol in 200 mL sterile water over 2 h; the same box says bicarbonate only if " +
                    "pH <6.9, if pH <7.1 with hypotension, or if hyperkalaemia is present. ADA 2024: routine use not recommended; consider if pH <7.0 — " +
                    "100 mmol (8.4%) in 400 mL sterile water every 2 h until pH >7.0; reported harms include hypokalaemia and cerebral oedema. " +
                    "SRLF/SFMU 2019: probably not (Grade 2−).",
                listOf(cite(ICMR_DKA, "Management — treatment"), cite(ADA, "Bicarbonate"), cite(SRLF, "R3.8")),
            ),
            ProtocolNote(
                "Fluid choice is a real controversy: SSC 2026 suggests balanced crystalloids and SMART found fewer kidney events, but the " +
                    "double-blind PLUS trial found no difference in death or new dialysis.",
                listOf(cite(SSC26, "Recommendation: Fluid type"), cite(SMART, "Abstract"), cite(PLUS, "Abstract")),
            ),
            ProtocolNote(
                "Venous gases: ICMR STW DKA uses a VBG for pH, bicarbonate and anion gap; SRLF/SFMU 2019 finds venous pH reliable but " +
                    "venous PCO₂ not — check compensation on an arterial sample.",
                listOf(cite(ICMR_DKA, "Investigations; Monitoring"), cite(SRLF, "R1.1 rationale")),
            ),
            ProtocolNote(
                "The interpreter in step 1 is built on Berend et al. (NEJM 2014) for arterial samples and reports Δ–Δ as a difference " +
                    "(±5 mmol/L); NCP 2025 expresses the same comparison as a ratio (0.8–1.2). Its albumin correction (2.5 mmol/L per g/dL " +
                    "below 4.0 g/dL) matches SRLF/SFMU (0.25 per g/L below 40 g/L).",
                listOf(cite(TOOL, "Anion gap and Δ–Δ sections"), cite(NCP, "Table 5"), cite(SRLF, "R1.3")),
            ),
        ),
        citations = listOf(
            cite(SRLF), cite(SSC26), cite(BICAR1), cite(BICAR2), cite(SMART), cite(PLUS), cite(ADA),
            cite(ICMR_DKA), cite(ICMR_AKI), cite(ACHANTI), cite(EXTRIP_EG), cite(TOOL),
        ),
    ),

    // =============================================================================================
    // Metabolic alkalosis
    // =============================================================================================
    Protocol(
        id = "metabolic-alkalosis",
        category = ProtocolCategory.Metabolic,
        title = "Metabolic alkalosis",
        summary = "Blood-gas reading, causes, urine chloride to separate chloride-responsive from chloride-resistant alkalosis, " +
            "volume, potassium and magnesium repletion, and the narrow roles of acetazolamide, HCl and dialysis.",
        keywords = listOf(
            "metabolic alkalosis", "alkalosis", "alkalaemia", "alkalemia", "high bicarbonate", "high HCO3", "hypochloraemia",
            "hypochloremia", "hypokalaemia", "hypokalemia", "vomiting", "nasogastric suction", "Ryle's tube aspiration",
            "gastric outlet obstruction", "diuretic", "furosemide", "Lasix", "urine chloride", "chloride-responsive",
            "chloride-resistant", "saline-responsive", "contraction alkalosis", "post-hypercapnic alkalosis", "milk-alkali",
            "calcium-alkali", "hyperaldosteronism", "Conn syndrome", "Bartter", "Gitelman", "licorice", "mulethi",
            "acetazolamide", "Diamox", "hydrochloric acid", "massive transfusion", "citrate",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Read the gas",
                points = listOf(
                    "AJKD 2022 review: metabolic alkalosis is a pH above 7.44 with a raised HCO₃; an ABG, or at least a VBG, is needed if in doubt.",
                    "Cureus 2021 review: a high HCO₃ can be metabolic alkalosis or compensation for respiratory acidosis — the blood gas decides.",
                    "NCP 2025 review: expected PaCO₂ rise ≈ 0.6 × the rise in HCO₃ above 24 mmol/L.",
                    "CJASN 2020 review: compensation lowers the pH, which generally stays above 7.45; with HCO₃ above 55 mmol/L, PCO₂ may rise markedly — likely respiratory muscle weakness from severe hypokalaemia, so respiratory acidosis often complicates extreme alkalosis.",
                    "BTS 2017: raised PCO₂ with pH ≥7.35 and/or HCO₃ >28 mmol/L probably means long-standing hypercapnia.",
                    "Cureus 2021 review: HCO₃ up to 40 mmol/L is tolerated; above 45 mmol/L can cause tetany, seizures, arrhythmias and delirium.",
                ),
                tool = ProtocolTool.AcidBase,
                citations = listOf(
                    cite(DO_AJKD, "Workup and treatment of metabolic alkalosis"),
                    cite(TINAWI, "Evaluation; Manifestations of metabolic alkalosis"),
                    cite(NCP, "Table 4"),
                    cite(EMMETT, "Respiratory compensation; Figure 1 legend"),
                    cite(BTS_O2, "Good practice points — COPD and other causes of hypercapnic respiratory failure"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "History, volume status and bloods",
                points = listOf(
                    "AJKD 2022 review: ask about vomiting, diuretics, laxatives, alkali (antacids, bicarbonate), licorice; assess volume; check Na, K, Cl, Mg, urea, creatinine.",
                    "CJASN 2023 review: in ICU, metabolic alkalosis is the commonest acid–base disorder, mostly from treatment of shock, acidaemia, overload, coagulopathy, respiratory failure and AKI.",
                    "Cureus 2021 review: citrate in blood products is an alkali source with massive transfusion or large-volume plasma exchange.",
                    "CJASN 2020 review: also severe potassium deficiency, milk-alkali syndrome, bicarbonate loads with markedly reduced GFR, and refeeding after fasting.",
                    "AJKD 2022 review: calcium-alkali syndrome — over-the-counter calcium with absorbable alkali causes hypercalcaemia, alkalosis and kidney failure; volume expansion comes first.",
                ),
                citations = listOf(
                    cite(DO_AJKD, "Workup and treatment; Case 2 (calcium-alkali syndrome)"),
                    cite(ACHANTI, "Abstract"),
                    cite(TINAWI, "Generation of metabolic alkalosis"),
                    cite(EMMETT, "Table 2"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Urine chloride: chloride-responsive or resistant?",
                points = listOf(
                    "CJASN 2020, AJKD 2022 and Cureus 2021 reviews: spot urine chloride <20 mmol/L means reduced (effective) volume — chloride-responsive alkalosis.",
                    "Same reviews: urine chloride >20 mmol/L (Cureus: ≥20) suggests an expanded state with mineralocorticoid excess, or ongoing renal chloride loss.",
                    "CJASN 2020 review: low volume with urine chloride >20 mmol/L — diuretics still acting, Bartter or Gitelman syndrome.",
                    "CJASN 2020 review: urine chloride cycles high and low with diuretic action; widely varying values suggest diuretic use, sometimes denied.",
                    "Cureus 2021 review: a random spot urine sample is adequate; a 24-hour collection is usually unnecessary.",
                    "AJKD 2022 review: use blood pressure with urine electrolytes; suspected primary aldosteronism needs renin and aldosterone.",
                ),
                checklistTitle = "CJASN 2020 Table 2: the metabolic alkaloses",
                checklist = listOf(
                    "Urine Cl <20, volume low: vomiting or nasogastric suction, chloride-rich diarrhoea, after diuretics wear off, post-chronic hypercapnia, cystic fibrosis with sweating",
                    "Urine Cl >20, volume low: active thiazide or loop diuretics, Bartter, Gitelman",
                    "Urine Cl >20, volume expanded: primary hyperaldosteronism, Cushing (especially ectopic ACTH), exogenous mineralocorticoids, licorice, renin-secreting tumour, Liddle",
                ),
                citations = listOf(
                    cite(EMMETT, "Diagnostic approach; Table 2"),
                    cite(DO_AJKD, "Workup and treatment; Figure 6"),
                    cite(TINAWI, "Evaluation of metabolic alkalosis"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Chloride-responsive: volume, chloride, potassium, magnesium",
                points = listOf(
                    "AJKD 2022 review: chloride-based IV fluid restores volume and GFR so bicarbonate can be excreted; rising urine chloride shows adequate expansion.",
                    "Cureus 2021 review: give isotonic (0.9%) saline and replace potassium as potassium chloride — oral, IV or both; for IV KCl dilution, maximum rates and monitoring see the 'Hypokalaemia' protocol.",
                    "Cureus 2021 review: replace magnesium if low — hypomagnesaemia causes recalcitrant hypokalaemia.",
                    "CJASN 2020 review: stop the gastric acid loss; if losses must continue, an H₂ blocker or proton pump inhibitor can help.",
                    "Cureus 2021 review: stop or reduce diuretics; if they must continue (heart failure, cirrhosis), adding a potassium-sparing diuretic may help.",
                ),
                citations = listOf(
                    cite(DO_AJKD, "Chloride-responsive alkalosis"),
                    cite(TINAWI, "Management of metabolic alkalosis"),
                    cite(EMMETT, "Gastric alkalosis — resolution"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Chloride-resistant: treat the source",
                points = listOf(
                    "AJKD 2022 review: correct hypokalaemia; remove the mineralocorticoid source, or block it — spironolactone, eplerenone or amiloride in primary aldosteronism.",
                    "AJKD 2022 review: stop licorice or carbenoxolone; dietary sodium restriction with added potassium can help.",
                    "CJASN 2020 review: stopping the diuretic halts bicarbonate generation; reversing potassium depletion is also important.",
                    "Cureus 2021 review: Bartter and Gitelman — potassium repletion, potassium-sparing diuretics and NSAIDs; magnesium too in Gitelman.",
                ),
                citations = listOf(
                    cite(DO_AJKD, "Chloride-resistant alkalosis"),
                    cite(EMMETT, "Thiazide and/or loop diuretics — recovery phase"),
                    cite(TINAWI, "Management of metabolic alkalosis"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Acetazolamide, HCl and dialysis",
                points = listOf(
                    "CJASN 2020 review: if diuretics must continue despite severe alkalosis, adding potassium-sparing or acetazolamide diuretics can help; acetazolamide often causes marked potassium wasting.",
                    "Cureus 2021 review: acetazolamide 250–500 mg orally or IV two to three times daily, by experienced clinicians, with aggressive potassium monitoring.",
                    "AJKD 2022 review: acetazolamide works less in volume contraction and is relatively contraindicated in kidney failure (neurotoxicity).",
                    "DIABOLO 2016 (382 ventilated COPD patients with metabolic alkalosis): IV acetazolamide lowered HCO₃ but did not significantly shorten ventilation.",
                    "Cureus 2021 review: dilute HCl (0.1 N) is rarely used — glass container, central venous line only, risk of haemolysis and thrombosis; stop near pH 7.50.",
                    "Cureus 2021 review: haemodialysis or PD with low-bicarbonate dialysate helps in advanced CKD; CRRT is particularly helpful in severe alkalosis.",
                ),
                checklistTitle = "FDA label: acetazolamide contraindications and cautions",
                checklist = listOf(
                    "Low serum sodium and/or potassium",
                    "Marked kidney or liver disease or dysfunction; cirrhosis (hepatic encephalopathy risk)",
                    "Adrenal (suprarenal) failure",
                    "Hyperchloraemic acidosis",
                    "Hypersensitivity to acetazolamide or sulfonamides",
                    "Caution — impaired alveolar ventilation (pulmonary obstruction, emphysema): may precipitate or aggravate acidosis; monitor electrolytes (Precautions)",
                ),
                citations = listOf(
                    cite(EMMETT, "Thiazide and/or loop diuretics — recovery phase"),
                    cite(TINAWI, "Management of metabolic alkalosis"),
                    cite(DO_AJKD, "Case 2 — Question 5"),
                    cite(DIABOLO, "Abstract"),
                    cite(FDA_ACZ, "Contraindications; Precautions"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Scope: no Indian (ICMR, MoHFW) or international guideline grades the treatment of metabolic alkalosis; this protocol " +
                    "summarises nephrology reviews and one RCT. The urine chloride cut-off is 20 mmol/L in all three reviews.",
                listOf(cite(EMMETT, "Diagnostic approach"), cite(DO_AJKD, "Workup and treatment"), cite(TINAWI, "Evaluation"), cite(DIABOLO, "Abstract")),
            ),
            ProtocolNote(
                "Acetazolamide in hypercapnic COPD: BTS/ICS 2016 — carbonic anhydrase inhibitors should not be used routinely in " +
                    "acute hypercapnic respiratory failure (grade C); DIABOLO found no significant reduction in ventilation time.",
                listOf(cite(BTS_ICS, "Rec 16"), cite(DIABOLO, "Abstract")),
            ),
            ProtocolNote(
                "Expected PaCO₂ differs: NCP 2025 and Cureus 2021 use 0.6 × the HCO₃ rise; the interpreter (Berend et al.) uses a " +
                    "slightly steeper slope and flags the response as less predictable; CJASN 2020 calls it very variable.",
                listOf(cite(NCP, "Table 4"), cite(TINAWI, "Respiratory compensation"), cite(TOOL, "Table 1"), cite(EMMETT, "Respiratory compensation")),
            ),
        ),
        citations = listOf(
            cite(EMMETT), cite(DO_AJKD), cite(TINAWI), cite(DIABOLO), cite(FDA_ACZ), cite(ACHANTI), cite(NCP), cite(BTS_O2),
        ),
    ),

    // =============================================================================================
    // Respiratory acidosis
    // =============================================================================================
    Protocol(
        id = "respiratory-acidosis",
        category = ProtocolCategory.Metabolic,
        title = "Respiratory acidosis (hypercapnia)",
        summary = "Acute versus chronic hypercapnia, causes, controlled oxygen, NIV criteria and intubation triggers, drug " +
            "reversal and avoiding excess oxygen — attributed to BTS/ICS, BTS, ERS/ATS, GOLD 2027, ISCCM and ICMR STWs.",
        keywords = listOf(
            "respiratory acidosis", "hypercapnia", "hypercarbia", "CO2 retention", "CO2 narcosis", "type 2 respiratory failure",
            "type II respiratory failure", "T2RF", "AHRF", "hypercapnic respiratory failure", "COPD exacerbation", "AECOPD",
            "NIV", "BiPAP", "BIPAP", "non-invasive ventilation", "intubation", "mechanical ventilation", "obesity hypoventilation",
            "OHS", "Pickwickian", "neuromuscular", "Guillain-Barré", "GBS", "myasthenic crisis", "kyphoscoliosis", "opioid",
            "naloxone", "controlled oxygen", "Venturi mask", "88-92%", "asterixis", "flap", "post-hypercapnic alkalosis",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Read the gas",
                timing = "Gas on arrival; recheck at 30–60 min in patients at risk (BTS 2017)",
                points = listOf(
                    "BTS/ICS 2016: pH <7.35 with PCO₂ >6.5 kPa (≈49 mm Hg) conventionally defines acute hypercapnic respiratory failure.",
                    "BTS 2017: raised PCO₂ with pH ≥7.35 and/or HCO₃ >28 mmol/L probably means long-standing (chronic) hypercapnia.",
                    "NCP 2025 review: HCO₃ rises about 0.1 × the PaCO₂ rise (acute) or 0.4 × (chronic); a much larger gap suggests a mixed disorder.",
                    "ERS/ATS 2017: many COPD patients are hypercapnic at baseline; acidosis marks acute-on-chronic hypercapnic failure.",
                    "BTS/ICS 2016: arterial gas before and after starting NIV. SRLF/SFMU 2019: venous PCO₂ agrees poorly with arterial PCO₂.",
                    "ICMR STW: look for signs of hypercapnia — headache, bounding pulse, tremor or flap, papilloedema, coma.",
                ),
                tool = ProtocolTool.AcidBase,
                citations = listOf(
                    cite(BTS_ICS, "Introduction; Starting NIV in COPD — good practice points"),
                    cite(BTS_O2, "Good practice points — COPD and other causes of hypercapnic respiratory failure"),
                    cite(NCP, "Tables 3 and 4"),
                    cite(ERS_ATS, "Question 1 — background"),
                    cite(SRLF, "R1.1 rationale"),
                    cite(ICMR_RF, "Signs"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Find the cause",
                points = listOf(
                    "NCP 2025 review: lung and airway — COPD or asthma exacerbation, pneumonia, pulmonary oedema, ARDS, aspiration, airway obstruction, pneumothorax.",
                    "NCP 2025 review: depressed drive — opioids, benzodiazepines, barbiturates, sedatives, anaesthetics; brain injury, stroke, meningitis; oxygen in chronic hypercapnia.",
                    "NCP 2025 review: pump failure — Guillain–Barré, myasthenic crisis, severe hypokalaemia or hypophosphataemia, kyphoscoliosis, extreme obesity, spinal cord injury.",
                    "BTS/ICS 2016: in neuromuscular and chest wall disease, including morbid obesity, failure is often insidious but may be acute and unexpected.",
                    "BTS/ICS 2016: hypercapnic failure complicates about 20% of COPD exacerbations and signals a high risk of future hospitalisation and limited prognosis.",
                ),
                checklistTitle = "ICMR STW COPD: red flags in an exacerbation",
                checklist = listOf(
                    "Altered sensorium",
                    "SpO₂ <88% despite therapy",
                    "Heart rate >110/min",
                    "Systolic BP <90 mm Hg",
                    "High-risk comorbidity — arrhythmia, heart failure, poorly controlled diabetes, renal or liver failure",
                ),
                citations = listOf(
                    cite(NCP, "Table 7; Respiratory drive inhibition"),
                    cite(BTS_ICS, "Introduction"),
                    cite(ICMR_COPD, "Red flag signs for people having exacerbation"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Controlled oxygen — avoid excess",
                timing = "From first contact, before gases are back (BTS 2017)",
                points = listOf(
                    "BTS 2017: target 88–92% for COPD or other hypercapnia risk (morbid obesity, cystic fibrosis, chest wall or neuromuscular disease, bronchiectasis) pending gases.",
                    "BTS 2017: before gases, 24% Venturi at 2–3 L/min (or 28% at 4 L/min, or nasal cannulae 1–2 L/min); recheck gases at 30–60 min.",
                    "BTS 2017: acidosis risk rises if excess oxygen pushes PaO₂ above 10.0 kPa (≈75 mm Hg); step down to 88–92% — never stop abruptly (rebound hypoxaemia).",
                    "BTS 2017: BMI >40 kg/m² — titrate oxygen to 88–92% even without known sleep apnoea. BTS/ICS 2016: 88–92% during NIV (grade A).",
                    "ICMR STW: start oxygen at SpO₂ <90%; in AECOPD keep a lower target of 88–92%. ICMR COPD STW: severe exacerbation — target SpO₂ 92%.",
                    "BTS 2017: adjust track-and-trigger scores (e.g. NEWS) so saturations within a prescribed 88–92% range score no points.",
                ),
                scoreIds = listOf("news2"),
                citations = listOf(
                    cite(BTS_O2, "A3/G1; G2; G5; good practice points — COPD; hypercapnia due to excessive oxygen; track and trigger"),
                    cite(BTS_ICS, "Rec 4; Rec 23"),
                    cite(ICMR_RF, "Treatment — oxygen"),
                    cite(ICMR_COPD, "Severe exacerbation"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Non-invasive ventilation: who and when",
                timing = "If acidosis persists >30 min after initial treatment (BTS 2017)",
                points = listOf(
                    "BTS/ICS 2016 (grade A): start NIV when pH <7.35 and PCO₂ >6.5 kPa persist or develop despite optimal medical therapy.",
                    "BTS 2017: PCO₂ >6 kPa (45 mm Hg) with pH <7.35 persisting over 30 min after standard treatment — start NIV with targeted oxygen.",
                    "ERS/ATS 2017 (strong): bilevel NIV for acute or acute-on-chronic respiratory acidosis (pH ≤7.35) in COPD; not for hypercapnia without acidosis (conditional).",
                    "GOLD 2027: NIV if PaCO₂ ≥45 mm Hg with pH ≤7.35, severe dyspnoea with respiratory muscle fatigue, or persistent hypoxaemia despite oxygen.",
                    "ISCCM 2020: NIV for COPD with pH 7.25–7.35 (1A); try NIV before intubation even at pH <7.25 unless intubation is immediately needed (2A).",
                    "BTS/ICS 2016: OHS — same NIV criteria as COPD; neuromuscular or chest wall disease — trial NIV without waiting for acidosis.",
                ),
                checklistTitle = "Contraindications and cautions (ISCCM 2020 Table 3; BTS/ICS 2016)",
                checklist = listOf(
                    "Cannot protect the airway — though hypercapnic coma alone is not a contraindication (ISCCM; ERS/ATS)",
                    "Haemodynamic instability — uncontrolled arrhythmia, high-dose vasopressors",
                    "Mask cannot be fitted — facial trauma, burns or deformity",
                    "Severe vomiting, bowel obstruction, haematemesis, massive haemoptysis or copious secretions",
                    "After cardiac arrest",
                    "Acute asthma with hypercapnic failure — BTS/ICS: NIV should not be used (grade C)",
                ),
                citations = listOf(
                    cite(BTS_ICS, "Rec 25; Rec 39; Rec 50; Rec 57"),
                    cite(BTS_O2, "Good practice points — COPD; G6"),
                    cite(ERS_ATS, "Questions 1a and 1b — recommendations"),
                    cite(GOLD, "Ch. 4, Figure 4.9"),
                    cite(ISCCM, "A1 Acute exacerbation of COPD — recommendations; Table 3"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Escalate to intubation",
                timing = "Responders usually improve within 1–4 h of NIV (ERS/ATS 2017)",
                points = listOf(
                    "BTS/ICS 2016 (grade A): consider invasive ventilation for persistent or worsening acidosis despite optimised NIV; NIV must not delay intubation (grade C).",
                    "BTS/ICS 2016: intubate in respiratory arrest or peri-arrest, or when a mask cannot be fitted or used (grade D).",
                    "ERS/ATS 2017: improving pH or respiratory rate predicts NIV success; responders almost always improve within 1–4 h.",
                    "ISCCM 2020: immediate intubation for arrest, delirium needing sedation, HR <60/min or MAP <60 mm Hg; pH <7.25, PaCO₂ ≥75 mm Hg at 2 h predict NIV failure.",
                    "GOLD 2027: also life-threatening hypoxaemia or intolerance on HFNT/NIV, reduced consciousness or uncontrolled agitation, massive aspiration, unclearable secretions, refractory shock, severe arrhythmias.",
                    "BTS/ICS 2016: ventilated severe airflow obstruction — permissive hypercapnia (pH 7.2–7.25) may be needed to limit airway pressures (grade D).",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(BTS_ICS, "Rec 15; Rec 27; Rec 32–34"),
                    cite(ERS_ATS, "Question 1b — bilevel NIV to prevent intubation"),
                    cite(ISCCM, "NIV in AECOPD with severe acidosis; Predictors of NIV failure"),
                    cite(GOLD, "Ch. 4, Figure 4.10"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Reverse drugs and treat the cause",
                points = listOf(
                    "BTS 2017: respiratory-depressant drugs make hypoxaemia more likely — give an antidote if available (naloxone for opiates) and check gases for hypercapnia.",
                    "Suspected opioid toxidrome: see the 'Opioid overdose' protocol for naloxone dosing and repeat doses.",
                    "NCP 2025 review: treat the cause — antimicrobials, stop offending drugs, reversal agents, bronchodilators, corticosteroids; reduce calories if overfeeding.",
                    "GOLD 2027: respiratory stimulants are not recommended for acute respiratory failure. BTS/ICS 2016: no routine carbonic anhydrase inhibitors (grade C).",
                    "BTS/ICS 2016: OHS — fluid overload commonly contributes and is easily underestimated; forced diuresis may be useful.",
                ),
                citations = listOf(
                    cite(BTS_O2, "Table 3 — poisonings and drug overdoses"),
                    cite(NCP, "Clinical presentation and treatment of respiratory acidosis"),
                    cite(GOLD, "Ventilatory support"),
                    cite(BTS_ICS, "Rec 16; NIV failure in OHS — good practice points"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Bicarbonate, weaning and after correction",
                points = listOf(
                    "CJASN 2023 review: bicarbonate is not recommended to correct the acidaemia; permissive hypercapnia to prevent barotrauma is standard care.",
                    "NCP 2025 review: bicarbonate generates CO₂ and can worsen respiratory acidosis if ventilation cannot rise; no consensus even at pH <7.2.",
                    "CJASN 2020 and AJKD 2022 reviews: rapid reversal of chronic hypercapnia leaves a post-hypercapnic, chloride-responsive metabolic alkalosis.",
                    "BTS/ICS 2016: stop NIV once pH and PCO₂ normalise and the patient improves; taper daytime use over 2–3 days before stopping overnight.",
                    "BTS/ICS 2016: after an episode needing ventilatory support, discuss future episodes — recurrence risk is high (grade B).",
                ),
                citations = listOf(
                    cite(ACHANTI, "Abstract"),
                    cite(NCP, "Clinical presentation and treatment of respiratory acidosis"),
                    cite(EMMETT, "Table 2"),
                    cite(DO_AJKD, "Box 1"),
                    cite(BTS_ICS, "Rec 31; Rec 38; Duration of NIV in COPD — good practice points"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "NIV thresholds differ slightly: BTS/ICS 2016 — pH <7.35 and PCO₂ >6.5 kPa despite optimal therapy; BTS 2017 — PCO₂ >6 kPa " +
                    "(45 mm Hg) and pH <7.35 persisting >30 min; GOLD 2027 — PaCO₂ ≥45 mm Hg and pH ≤7.35; ERS/ATS 2017 — pH ≤7.35.",
                listOf(cite(BTS_ICS, "Rec 25"), cite(BTS_O2, "Good practice points — COPD"), cite(GOLD, "Figure 4.9"), cite(ERS_ATS, "Question 1b")),
            ),
            ProtocolNote(
                "Severe acidosis: BTS/ICS 2016 — severe acidosis alone does not preclude an NIV trial where safe intubation is at hand (grade B); " +
                    "ERS/ATS 2017 — no lower pH limit for a trial, but failure rises as pH falls; ISCCM 2020 — try NIV before intubation at pH <7.25 (2A).",
                listOf(cite(BTS_ICS, "Rec 26"), cite(ERS_ATS, "Implementation considerations"), cite(ISCCM, "A1 recommendations")),
            ),
            ProtocolNote(
                "Oxygen targets in India: ICMR STW respiratory failure — 88–92% in AECOPD; ICMR STW COPD — target SpO₂ 92% in severe " +
                    "exacerbation; BTS 2017 — 88–92% for all patients at risk of hypercapnia until gases are known.",
                listOf(cite(ICMR_RF, "Treatment — oxygen"), cite(ICMR_COPD, "Severe exacerbation"), cite(BTS_O2, "A3/G1")),
            ),
            ProtocolNote(
                "Referral in India: ICMR STWs advise stabilising and transferring to a higher centre when there is no relief, mechanical " +
                    "ventilation is needed or features are life-threatening, with supplemental oxygen and nebulisation continued during transfer.",
                listOf(cite(ICMR_RF, "Referral"), cite(ICMR_COPD, "Severe exacerbation")),
            ),
            ProtocolNote(
                "High-flow nasal oxygen: GOLD 2027 calls HFNT the first mode for acute hypoxaemic failure and NIV for hypercapnic failure; " +
                    "in one RCT about 30% of hypercapnic patients on HFNT switched to NIV by 6 h. ERS guidelines, as cited by GOLD, prefer NIV in hypercapnia.",
                listOf(cite(GOLD, "Figure 4.7; High-flow nasal therapy")),
            ),
        ),
        citations = listOf(
            cite(BTS_ICS), cite(BTS_O2), cite(ERS_ATS), cite(GOLD), cite(ISCCM), cite(ICMR_RF), cite(ICMR_COPD), cite(NCP),
            cite(ACHANTI), cite(SRLF),
        ),
    ),

    // =============================================================================================
    // Respiratory alkalosis
    // =============================================================================================
    Protocol(
        id = "respiratory-alkalosis",
        category = ProtocolCategory.Metabolic,
        title = "Respiratory alkalosis (hyperventilation)",
        summary = "Hypocapnia is a sign, not a diagnosis: blood-gas reading, dangerous causes to exclude first (hypoxaemia, " +
            "PE, sepsis, salicylate, liver failure), pregnancy norms, anxiety only after exclusion, and why sources advise against paper-bag rebreathing.",
        keywords = listOf(
            "respiratory alkalosis", "hypocapnia", "hypocarbia", "low CO2", "low PCO2", "hyperventilation", "tachypnoea",
            "tachypnea", "panic attack", "anxiety", "ghabrahat", "hyperventilation syndrome", "dysfunctional breathing",
            "carpopedal spasm", "tetany", "tingling", "paraesthesia", "paper bag", "rebreathing", "salicylate", "aspirin poisoning",
            "pulmonary embolism", "PE", "sepsis", "pregnancy", "liver failure", "cirrhosis", "high altitude",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Read the gas",
                points = listOf(
                    "SRLF/SFMU 2019: a low bicarbonate may be compensation for respiratory alkalosis — pH >7.42 with PaCO₂ <38 mm Hg indicates respiratory alkalaemia.",
                    "NCP 2025 review: HCO₃ falls about 0.2 × the PaCO₂ fall (acute; usually not below 18 mmol/L) or 0.4 × (chronic; usually not below 14).",
                    "NCP 2025 review: a value far from the expected compensation suggests a mixed acid–base disorder.",
                    "SRLF/SFMU 2019: salicylate poisoning combines early respiratory alkalosis with a high anion gap metabolic acidosis.",
                    "Breathe 2015 (ERS): in pregnancy arterial PCO₂ falls to about 32 mm Hg (interpret against about 30) with pH 7.40–7.47.",
                ),
                tool = ProtocolTool.AcidBase,
                citations = listOf(
                    cite(SRLF, "R1.1 rationale; R1.5 rationale"),
                    cite(NCP, "Tables 3 and 4"),
                    cite(BREATHE, "Oxygen consumption, arterial blood gases and acid–base status; table — mechanical ventilation"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Exclude dangerous causes first",
                timing = "Before attributing symptoms to anxiety",
                points = listOf(
                    "BTS 2017: organic illness must be excluded before a diagnosis of hyperventilation is made.",
                    "NCP 2025 review: hypoxaemia, pneumonia, pulmonary oedema, pulmonary embolism, asthma, heart failure, anaemia, pneumothorax and high altitude cause hyperventilation.",
                    "NCP 2025 review: also sepsis, fever, salicylate overdose, cirrhosis, stroke, brain injury, meningitis, pain, thyrotoxicosis and drugs such as methylxanthines and β-agonists.",
                    "NCP 2025 review: over-ventilation on a fixed mechanical ventilator rate is another cause.",
                ),
                checklistTitle = "Not anxiety until excluded",
                checklist = listOf(
                    "Low SpO₂ or PaO₂ — hypoxaemia (NCP 2025)",
                    "Chest pain or ECG changes — myocardial ischaemia (Callaham 1989)",
                    "Suspected pulmonary embolism (NCP 2025) — see Wells PE and PERC",
                    "Fever, hypotension or a source of infection — sepsis (NCP 2025)",
                    "Possible aspirin or salicylate ingestion, especially with a high anion gap (SRLF/SFMU 2019)",
                    "Liver disease, encephalopathy or tense ascites (NCP 2025)",
                    "Hypoglycaemia, thyrotoxicosis, phaeochromocytoma, arrhythmia or seizure — mimics of panic (ICMR STW)",
                ),
                scoreIds = listOf("wells-pe", "perc", "qsofa"),
                citations = listOf(
                    cite(BTS_O2, "Good practice points — suspected hyperventilation"),
                    cite(NCP, "Table 8; Clinical presentation and treatment of respiratory alkalosis"),
                    cite(CALLAHAM, "Abstract"),
                    cite(SRLF, "R1.5 rationale"),
                    cite(ICMR_ANX, "Assessment; Management — tertiary care"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Hypoxaemia and pulmonary embolism",
                points = listOf(
                    "BTS 2017: target saturation 94–98% for most acutely ill patients; 88–92% if at risk of hypercapnic respiratory failure.",
                    "BTS 2017: pulmonary embolism — aim 94–98% (88–92% if hypercapnia risk); most patients with minor PE are not hypoxaemic.",
                    "BTS 2017: oxygen treats hypoxaemia but not its cause, which must be diagnosed and treated urgently.",
                    "BTS 2017: confirmed hyperventilation with normal or high SpO₂ does not need oxygen; keep monitoring saturation.",
                ),
                scoreIds = listOf("wells-pe", "perc"),
                citations = listOf(
                    cite(BTS_O2, "A2; F8; Table 2 (pulmonary embolism); good practice points — suspected hyperventilation; introduction"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Salicylate, sepsis, liver disease, pregnancy",
                points = listOf(
                    "SRLF/SFMU 2019 (expert opinion): salicylate poisoning — bicarbonate whatever the pH, with close monitoring for hypokalaemia, hypernatraemia, hypocalcaemia, alveolar hypoventilation and fluid overload; renal replacement if neurological signs, level >90 mg/dL (6.5 mmol/L) or pH ≤7.20.",
                    "NCP 2025 review: in salicylate overdose, respiratory muscle fatigue can end the hyperventilation, leaving metabolic acidosis dominant.",
                    "NCP 2025 review: sepsis drives breathing through inflammatory mediators; give antibiotics if sepsis or infection is suspected.",
                    "NCP 2025 review: liver causes — hepatic encephalopathy, hepatopulmonary syndrome, portopulmonary hypertension, hepatic hydrothorax and large-volume ascites.",
                    "NCP 2025 review: pregnancy lowers PaCO₂ (to about 28 mm Hg in the third trimester) without needing treatment; it normalises after delivery.",
                ),
                citations = listOf(
                    cite(SRLF, "R3.9 and rationale; R3.13"),
                    cite(NCP, "Miscellaneous; Clinical presentation and treatment of respiratory alkalosis"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Anxiety and dysfunctional breathing — only after exclusion",
                points = listOf(
                    "ICMR STW: assess for thyrotoxicosis, phaeochromocytoma and hypoglycaemia; in panic, exclude hyperparathyroidism, vestibular disease, seizures and arrhythmias.",
                    "ICMR STW: reassure and explain that anxiety symptoms mimic physical illness (e.g. heart attack); avoid excessive investigation, though ECG or echo may be needed in some.",
                    "NCP 2025 review: treatment addresses the cause — anxiety or panic support, anxiolytics, pain control, stopping causative drugs.",
                    "ICMR STW: persistent anxiety disorder — escitalopram 5 mg at night, increased to 10 mg after a week, if no improvement over a few weeks.",
                ),
                citations = listOf(
                    cite(ICMR_ANX, "Assessment; Psychoeducation; Pharmacological treatment"),
                    cite(NCP, "Clinical presentation and treatment of respiratory alkalosis"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Paper-bag rebreathing: what sources say",
                points = listOf(
                    "BTS 2017: rebreathing from a paper bag can be dangerous and is not advised as a treatment for hyperventilation; it may cause hypoxaemia.",
                    "Callaham 1989 (20 volunteers): bag rebreathing lowered PO₂ by a mean maximum of 26 mm Hg, while many never regained a CO₂ of 40 mm Hg.",
                    "Callaham 1989: three deaths occurred when bag rebreathing was used in patients who were hypoxaemic or had myocardial ischaemia.",
                ),
                checklistTitle = "Don'ts",
                checklist = listOf(
                    "Paper-bag rebreathing (BTS 2017)",
                    "Labelling anxiety before hypoxaemia, PE, sepsis, ischaemia and salicylate are excluded (BTS 2017; Callaham 1989)",
                ),
                citations = listOf(
                    cite(BTS_O2, "Good practice points — suspected hyperventilation; Table 3"),
                    cite(CALLAHAM, "Abstract"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Pregnancy: the interpreter uses non-pregnant reference ranges (Berend et al.), so a normal pregnancy gas may read as " +
                    "respiratory alkalosis. Breathe 2015 gives a PCO₂ plateau of about 32 mm Hg; NCP 2025 cites about 28 mm Hg in the third trimester.",
                listOf(cite(TOOL, "Table 1"), cite(BREATHE, "Arterial blood gases and acid–base status"), cite(NCP, "Miscellaneous")),
            ),
            ProtocolNote(
                "Rebreathing: no reviewed source supports paper-bag rebreathing; BTS 2017 advises against it. ICMR STW on anxiety " +
                    "disorders does not address it.",
                listOf(cite(BTS_O2, "Good practice points — suspected hyperventilation"), cite(CALLAHAM, "Abstract"), cite(ICMR_ANX, "Management")),
            ),
            ProtocolNote(
                "Evidence base: causes and treatment of respiratory alkalosis rest on a narrative review (NCP 2025) and physiology; " +
                    "there is no graded guideline for respiratory alkalosis itself.",
                listOf(cite(NCP, "Respiratory alkalosis")),
            ),
        ),
        citations = listOf(
            cite(BTS_O2), cite(NCP), cite(SRLF), cite(BREATHE), cite(CALLAHAM), cite(ICMR_ANX), cite(TOOL),
        ),
    ),
)
