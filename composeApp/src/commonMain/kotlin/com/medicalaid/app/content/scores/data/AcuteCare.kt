package com.medicalaid.app.content.scores.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.scores.Band
import com.medicalaid.app.content.scores.Caveat
import com.medicalaid.app.content.scores.Choice
import com.medicalaid.app.content.scores.Criterion
import com.medicalaid.app.content.scores.Level
import com.medicalaid.app.content.scores.Option
import com.medicalaid.app.content.scores.Score
import com.medicalaid.app.content.scores.ScoreCategory
import com.medicalaid.app.content.scores.ShowIf

/*
 * Acute-care scores. Criteria, points and cut-offs were checked against the derivation paper and the
 * endorsing guideline cited on each score (checked November 2026). Band.meaning is always attributed.
 * Where a per-band risk figure could not be verified from a primary source it is omitted.
 */

/** RCP NEWS2 Chart 2 / Chart 4: a score of 3 in any single parameter ("red score"). */
private const val NEWS2_RED =
    "RCP NEWS2: a score of 3 in a single parameter (red score) is low–medium clinical risk — urgent ward-based response: the nurse informs the medical team, who review and decide on escalation; observations at least hourly."

internal val acuteCareScoreSources = listOf(
    // --- CURB-65 ---
    Source(
        "sc-lim-2003", Authority.Paper, "Thorax 2003;58(5):377–382",
        "Defining community acquired pneumonia severity on presentation to hospital: an international derivation and validation study", "2003",
        "https://doi.org/10.1136/thorax.58.5.377",
    ),
    Source(
        "sc-nice-ng250", Authority.NICE, "Guideline NG250",
        "Pneumonia: diagnosis and management", "2025",
        "https://www.nice.org.uk/guidance/ng250",
        status = "Replaces CG191, NG138 and NG139",
    ),
    // --- NEWS2 ---
    Source(
        "sc-rcp-news2", Authority.Paper, "Royal College of Physicians (London) report",
        "National Early Warning Score (NEWS) 2: Standardising the assessment of acute-illness severity in the NHS", "2017",
        "https://www.rcp.ac.uk/resources/national-early-warning-score-news-2/",
    ),
    // --- Appendicitis (Alvarado, AIR) ---
    Source(
        "sc-alvarado-1986", Authority.Paper, "Ann Emerg Med 1986;15(5):557–564",
        "A practical score for the early diagnosis of acute appendicitis", "1986",
        "https://doi.org/10.1016/S0196-0644(86)80993-3",
    ),
    Source(
        "sc-alvarado-2016", Authority.Paper, "World J Emerg Surg 2016;11:16",
        "How to improve the clinical diagnosis of acute appendicitis in resource limited settings", "2016",
        "https://doi.org/10.1186/s13017-016-0071-8",
    ),
    Source(
        "sc-andersson-2008", Authority.Paper, "World J Surg 2008;32(8):1843–1849",
        "The appendicitis inflammatory response score: a tool for the diagnosis of acute appendicitis that outperforms the Alvarado score", "2008",
        "https://doi.org/10.1007/s00268-008-9649-y",
    ),
    Source(
        "sc-andersson-2021", Authority.Paper, "World J Surg 2021;45(7):2081–2091",
        "Validation of the Appendicitis Inflammatory Response (AIR) Score", "2021",
        "https://doi.org/10.1007/s00268-021-06042-2",
    ),
    Source(
        "sc-wses-appendicitis-2020", Authority.Paper, "World Society of Emergency Surgery (WSES) guideline — World J Emerg Surg 2020;15:27",
        "Diagnosis and treatment of acute appendicitis: 2020 update of the WSES Jerusalem guidelines", "2020",
        "https://doi.org/10.1186/s13017-020-00306-3",
        status = "Superseded by the 2025 edition",
    ),
    Source(
        "sc-wses-appendicitis-2025", Authority.Paper, "World Society of Emergency Surgery (WSES) guideline — JAMA Surg 2026;161(3):283–295",
        "Diagnosis and Treatment of Acute Appendicitis: 2025 Edition of the World Society of Emergency Surgery Jerusalem Guidelines", "2025",
        "https://doi.org/10.1001/jamasurg.2025.6218",
    ),
    // --- Glasgow-Blatchford ---
    Source(
        "sc-blatchford-2000", Authority.Paper, "Lancet 2000;356(9238):1318–1321",
        "A risk score to predict need for treatment for upper-gastrointestinal haemorrhage", "2000",
        "https://doi.org/10.1016/S0140-6736(00)02816-6",
    ),
    Source(
        "sc-nice-cg141", Authority.NICE, "Clinical guideline CG141",
        "Acute upper gastrointestinal bleeding in over 16s: management", "2012",
        "https://www.nice.org.uk/guidance/cg141",
        status = "Last updated 2016",
    ),
    Source(
        "sc-esge-nvugih-2021", Authority.Paper, "European Society of Gastrointestinal Endoscopy (ESGE) guideline — Endoscopy 2021;53(3):300–332",
        "Endoscopic diagnosis and management of nonvariceal upper gastrointestinal hemorrhage (NVUGIH): ESGE Guideline – Update 2021", "2021",
        "https://doi.org/10.1055/a-1369-5274",
        status = "Risk-stratification advice not among the revised recommendations of the 2026 peptic ulcer bleeding update",
    ),
    // --- Child-Pugh ---
    Source(
        "sc-pugh-1973", Authority.Paper, "Br J Surg 1973;60(8):646–649",
        "Transection of the oesophagus for bleeding oesophageal varices", "1973",
        "https://doi.org/10.1002/bjs.1800600817",
    ),
    Source(
        "sc-fda-hepatic-pk", Authority.FDA, "Guidance for Industry (draft)",
        "Pharmacokinetics in Patients with Impaired Hepatic Function: Study Design, Data Analysis, and Impact on Dosage", "2026",
        "https://www.fda.gov/media/71311/download",
        status = "Draft guidance, September 2026",
    ),
    // --- Centor / McIsaac ---
    Source(
        "sc-centor-1981", Authority.Paper, "Med Decis Making 1981;1(3):239–246",
        "The diagnosis of strep throat in adults in the emergency room", "1981",
        "https://doi.org/10.1177/0272989X8100100304",
    ),
    Source(
        "sc-mcisaac-1998", Authority.Paper, "CMAJ 1998;158(1):75–83",
        "A clinical score to reduce unnecessary antibiotic use in patients with sore throat", "1998",
        "https://pmc.ncbi.nlm.nih.gov/articles/PMC1228750/",
    ),
    Source(
        "sc-mcisaac-2000", Authority.Paper, "CMAJ 2000;163(7):811–815",
        "The validity of a sore throat score in family practice", "2000",
        "https://pmc.ncbi.nlm.nih.gov/articles/PMC80502/",
    ),
    Source(
        "sc-nice-ng84", Authority.NICE, "Guideline NG84",
        "Sore throat (acute): antimicrobial prescribing", "2018",
        "https://www.nice.org.uk/guidance/ng84",
    ),
    // --- Bishop ---
    Source(
        "sc-bishop-1964", Authority.Paper, "Obstet Gynecol 1964;24:266–268",
        "Pelvic scoring for elective induction", "1964",
        "https://pubmed.ncbi.nlm.nih.gov/14199536/",
    ),
    Source(
        "sc-acog-cpg9", Authority.ACOG, "Clinical Practice Guideline No. 9",
        "Cervical Ripening in Pregnancy", "2025",
        "https://doi.org/10.1097/AOG.0000000000005951",
        status = "Replaces Practice Bulletin No. 107 (2009)",
    ),
    Source(
        "sc-nice-ng207", Authority.NICE, "Guideline NG207",
        "Inducing labour", "2021",
        "https://www.nice.org.uk/guidance/ng207",
    ),
    // --- Ottawa ankle / foot ---
    Source(
        "sc-stiell-1993", Authority.Paper, "JAMA 1993;269(9):1127–1132",
        "Decision rules for the use of radiography in acute ankle injuries. Refinement and prospective validation", "1993",
        "https://doi.org/10.1001/jama.1993.03500090063034",
    ),
    Source(
        "sc-bachmann-2003", Authority.Paper, "BMJ 2003;326(7386):417",
        "Accuracy of Ottawa ankle rules to exclude fractures of the ankle and mid-foot: systematic review", "2003",
        "https://doi.org/10.1136/bmj.326.7386.417",
    ),
    Source(
        "sc-acr-ankle-2020", Authority.Paper, "American College of Radiology Appropriateness Criteria — J Am Coll Radiol 2020;17(11S):S355–S366",
        "ACR Appropriateness Criteria® Acute Trauma to the Ankle", "2020",
        "https://doi.org/10.1016/j.jacr.2020.09.014",
    ),
    Source(
        "sc-acr-foot-2020", Authority.Paper, "American College of Radiology Appropriateness Criteria — J Am Coll Radiol 2020;17(5S):S2–S11",
        "ACR Appropriateness Criteria® Acute Trauma to the Foot", "2020",
        "https://doi.org/10.1016/j.jacr.2020.01.019",
    ),
    // --- Canadian CT Head Rule ---
    Source(
        "sc-stiell-2001", Authority.Paper, "Lancet 2001;357(9266):1391–1396",
        "The Canadian CT Head Rule for patients with minor head injury", "2001",
        "https://doi.org/10.1016/S0140-6736(00)04561-X",
    ),
    Source(
        "sc-stiell-2010", Authority.Paper, "CMAJ 2010;182(14):1527–1532",
        "A prospective cluster-randomized trial to implement the Canadian CT Head Rule in emergency departments", "2010",
        "https://doi.org/10.1503/cmaj.091974",
    ),
    Source(
        "sc-nice-ng232", Authority.NICE, "Guideline NG232",
        "Head injury: assessment and early management", "2023",
        "https://www.nice.org.uk/guidance/ng232",
    ),
)

internal val acuteCareScores = listOf(

    // ---------------------------------------------------------------------------------------------
    // CURB-65 — Lim 2003 (Figure 2); NICE NG250 Box 2 and rec 1.2.7–1.2.9.
    // Neither Lim 2003 nor NICE gives a BUN (mg/dL) equivalent for urea, so none is shown.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "curb-65",
        name = "CURB-65",
        purpose = "30-day mortality risk in adults with community-acquired pneumonia assessed in hospital.",
        category = ScoreCategory.Sepsis,
        keywords = listOf("CURB65", "CRB-65", "CRB65", "pneumonia", "CAP", "community-acquired pneumonia", "severity"),
        items = listOf(
            Criterion("confusion", "Confusion", 1.0, hint = "Abbreviated Mental Test score ≤8, or new disorientation in person, place or time"),
            Criterion("urea", "Urea >7 mmol/L", 1.0, hint = "Serum urea in mmol/L. The derivation paper and NICE give no mg/dL (BUN) equivalent — convert units before scoring."),
            Criterion("rr", "Respiratory rate ≥30/min", 1.0),
            Criterion("bp", "Low blood pressure", 1.0, hint = "Systolic <90 mm Hg or diastolic ≤60 mm Hg"),
            Criterion("age", "Age ≥65 years", 1.0),
        ),
        bands = listOf(
            Band(
                0.0, 1.0, "CURB-65 0–1: low risk",
                "NICE NG250: low risk of death (<3%); consider discharge home with primary care follow-up and safety-netting. Lim 2003: 30-day mortality 1.5%, likely suitable for home treatment.",
                Level.Low,
            ),
            Band(
                2.0, 2.0, "CURB-65 2: intermediate risk",
                "NICE NG250: intermediate risk (3–15%); consider a virtual ward, same-day emergency care, hospital at home or inpatient care. Lim 2003: mortality 9.2%; consider hospital-supervised treatment.",
                Level.Moderate,
            ),
            Band(
                3.0, 5.0, "CURB-65 3–5: high risk",
                "NICE NG250: high risk (>15%); consider inpatient care with referral to critical care if appropriate. Lim 2003: mortality 22%; manage as severe pneumonia and assess for ICU, especially at 4–5.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "NICE NG250: use clinical judgement together with the score — it can be affected by other factors, for example comorbidities or pregnancy — and make shared decisions about place of care.",
                listOf(cite("sc-nice-ng250", "Recommendations 1.2.8–1.2.9, 1.2.11")),
            ),
            Caveat(
                "In the community (no urea result) use CRB-65: the same criteria without urea, scored 0–4. NICE NG250: 0 low risk (<1% mortality), 1–2 intermediate (1–10%), 3–4 high (>10%); consider hospital referral at 2 or more.",
                listOf(cite("sc-nice-ng250", "Box 1, recommendation 1.2.3"), cite("sc-lim-2003", "Figure 3")),
            ),
        ),
        citations = listOf(cite("sc-lim-2003", "Figure 2"), cite("sc-nice-ng250", "Box 2, recommendations 1.2.7–1.2.9")),
    ),

    // ---------------------------------------------------------------------------------------------
    // NEWS2 — RCP 2017, Chart 1 (scoring), Chart 2 (thresholds), Chart 4 (clinical response).
    // The SpO2 scale is chosen first; only the matching SpO2 question is shown and scored.
    // Every option worth 3 carries the "red score" alert (single-parameter trigger).
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "news2",
        name = "NEWS2",
        purpose = "Acute-illness severity and deterioration in adults (16+) from routine physiological observations.",
        category = ScoreCategory.Sepsis,
        keywords = listOf("National Early Warning Score", "NEWS", "early warning score", "deterioration", "track and trigger", "sepsis", "ACVPU"),
        items = listOf(
            Choice(
                "rr", "Respiration rate (/min)",
                listOf(
                    Option("≤8", 3.0, NEWS2_RED), Option("9–11", 1.0), Option("12–20", 0.0),
                    Option("21–24", 2.0), Option("≥25", 3.0, NEWS2_RED),
                ),
            ),
            Choice(
                "spo2Scale", "SpO₂ scale",
                listOf(Option("Scale 1 (standard)", 0.0), Option("Scale 2 (hypercapnic respiratory failure)", 0.0)),
                hint = "RCP: Scale 2 only for hypercapnic respiratory failure confirmed on blood gas, with a prescribed target of 88–92%, decided by a competent clinical decision maker.",
            ),
            Choice(
                "spo2s1", "SpO₂ (%) — Scale 1",
                listOf(Option("≤91", 3.0, NEWS2_RED), Option("92–93", 2.0), Option("94–95", 1.0), Option("≥96", 0.0)),
                showIf = ShowIf("spo2Scale", 0),
            ),
            Choice(
                "spo2s2", "SpO₂ (%) — Scale 2",
                listOf(
                    Option("≤83", 3.0, NEWS2_RED), Option("84–85", 2.0), Option("86–87", 1.0), Option("88–92", 0.0),
                    Option("≥93 on air", 0.0), Option("93–94 on oxygen", 1.0), Option("95–96 on oxygen", 2.0),
                    Option("≥97 on oxygen", 3.0, NEWS2_RED),
                ),
                hint = "At 93% or above, the score depends on whether the patient is on air or oxygen.",
                showIf = ShowIf("spo2Scale", 1),
            ),
            Choice("o2", "Air or oxygen?", listOf(Option("Air", 0.0), Option("Oxygen", 2.0))),
            Choice(
                "sbp", "Systolic BP (mm Hg)",
                listOf(
                    Option("≤90", 3.0, NEWS2_RED), Option("91–100", 2.0), Option("101–110", 1.0),
                    Option("111–219", 0.0), Option("≥220", 3.0, NEWS2_RED),
                ),
            ),
            Choice(
                "pulse", "Pulse (/min)",
                listOf(
                    Option("≤40", 3.0, NEWS2_RED), Option("41–50", 1.0), Option("51–90", 0.0),
                    Option("91–110", 1.0), Option("111–130", 2.0), Option("≥131", 3.0, NEWS2_RED),
                ),
            ),
            Choice(
                "consciousness", "Consciousness (ACVPU)",
                listOf(Option("Alert", 0.0), Option("New confusion, Voice, Pain or Unresponsive", 3.0, NEWS2_RED)),
                hint = "New confusion includes disorientation, delirium or any acute fall in GCS. RCP: if unsure, assume the confusion is new.",
            ),
            Choice(
                "temp", "Temperature (°C)",
                listOf(
                    Option("≤35.0", 3.0, NEWS2_RED), Option("35.1–36.0", 1.0), Option("36.1–38.0", 0.0),
                    Option("38.1–39.0", 1.0), Option("≥39.1", 2.0),
                ),
            ),
        ),
        bands = listOf(
            Band(
                0.0, 0.0, "NEWS2 0",
                "RCP: low clinical risk — continue routine NEWS monitoring, at least 12-hourly.",
                Level.Low,
            ),
            Band(
                1.0, 4.0, "NEWS2 1–4: low risk",
                "RCP: low clinical risk — a registered nurse assesses the patient and decides on monitoring frequency (at least 4–6-hourly) and escalation.",
                Level.Low,
            ),
            Band(
                5.0, 6.0, "NEWS2 5–6: medium risk",
                "RCP: medium clinical risk, the key threshold for urgent response — urgent assessment by a clinician or team competent in acute illness, in a monitored setting; observations at least hourly.",
                Level.Moderate,
            ),
            Band(
                7.0, 20.0, "NEWS2 ≥7: high risk",
                "RCP: high clinical risk — emergency assessment by a team with critical-care competencies including airway skills, continuous monitoring, and consider transfer to level 2 or 3 care.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "RCP: NEWS2 should not be used in children (under 16) or in pregnancy, because the physiological response to acute illness is modified.",
                listOf(cite("sc-rcp-news2", "Recommendation 2")),
            ),
            Caveat(
                "RCP: NEWS2 may be unreliable in spinal cord injury (especially tetraplegia or high-level paraplegia); use with caution.",
                listOf(cite("sc-rcp-news2", "Recommendation 3")),
            ),
            Caveat(
                "RCP: use SpO₂ Scale 2 only for patients with hypercapnic respiratory failure confirmed on blood gas who need supplemental oxygen (target 88–92%); in all other circumstances use Scale 1.",
                listOf(cite("sc-rcp-news2", "Recommendations 25–27")),
            ),
        ),
        citations = listOf(cite("sc-rcp-news2", "Charts 1, 2 and 4")),
    ),

    // ---------------------------------------------------------------------------------------------
    // Alvarado (MANTRELS) — items and weights as described by Alvarado (1986; restated 2016).
    // Band wording: Alvarado's own interpretation (5–6 compatible, 7–8 probable, 9–10 very probable);
    // rule-out at <5 per WSES 2020.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "alvarado",
        name = "Alvarado score (MANTRELS)",
        purpose = "Likelihood of acute appendicitis in patients with suspected appendicitis.",
        category = ScoreCategory.Gi,
        keywords = listOf("MANTRELS", "appendicitis", "appendix", "right iliac fossa", "RIF pain", "abdominal pain"),
        items = listOf(
            Criterion("migration", "Migration of pain to right lower quadrant", 1.0, section = "Symptoms"),
            Criterion("anorexia", "Anorexia (or acetone in urine)", 1.0),
            Criterion("nausea", "Nausea or vomiting", 1.0),
            Criterion("tenderness", "Tenderness in right lower quadrant", 2.0, section = "Signs"),
            Criterion("rebound", "Rebound pain", 1.0, hint = "Alvarado 2016: Rovsing, cough (Dunphy) or heel-drop signs may substitute"),
            Criterion("temp", "Temperature ≥37.3 °C", 1.0, hint = "Oral temperature"),
            Criterion("wbc", "Leukocytosis (WBC >10 ×10⁹/L)", 2.0, section = "Laboratory", hint = ">10,000/mm³"),
            Criterion("shift", "Shift to the left", 1.0, hint = "Neutrophils >75% (Alvarado 2016: or band forms >5%)"),
        ),
        bands = listOf(
            Band(
                0.0, 4.0, "Alvarado 0–4: low",
                "WSES 2020: a score below 5 is sensitive enough to exclude appendicitis (sensitivity 99%); low-risk adults could be discharged with safety-netting.",
                Level.Low,
            ),
            Band(
                5.0, 6.0, "Alvarado 5–6: compatible",
                "Alvarado: compatible with appendicitis — a grey zone; observe and re-score every 4–6 h and consider imaging. WSES 2020: intermediate-risk patients are likely to benefit from imaging.",
                Level.Moderate,
            ),
            Band(
                7.0, 8.0, "Alvarado 7–8: probable",
                "Alvarado: probable appendicitis. WSES 2020 suggests against using the Alvarado score to positively confirm appendicitis in adults (low specificity).",
                Level.High,
            ),
            Band(
                9.0, 10.0, "Alvarado 9–10: very probable",
                "Alvarado: very probable appendicitis. WSES 2020 suggests against using the Alvarado score alone to confirm appendicitis in adults.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "WSES 2020: the Alvarado score is not specific enough to diagnose appendicitis in adults, is unreliable for telling complicated from uncomplicated appendicitis in older patients, and is less sensitive in HIV.",
                listOf(cite("sc-wses-appendicitis-2020", "Statement and recommendation 1.3")),
            ),
            Caveat(
                "WSES 2025 (Rec 1) suggests the AIR, Adult Appendicitis (AAS) or Pediatric Appendicitis (PAS) scores to guide the initial work-up — to rule out appendicitis in low-risk patients and select intermediate-risk patients for imaging or active observation; in unclear presentations CT remains the diagnostic standard.",
                listOf(cite("sc-wses-appendicitis-2025", "Recommendation 1")),
            ),
            Caveat(
                "Children: WSES 2020 suggests against making the diagnosis on clinical scores alone.",
                listOf(cite("sc-wses-appendicitis-2020", "Recommendation 1.5")),
            ),
        ),
        citations = listOf(
            cite("sc-alvarado-1986"),
            cite("sc-alvarado-2016", "Score items and interpretation"),
            cite("sc-wses-appendicitis-2020", "Statements 1.2–1.3"),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // AIR — items per Andersson 2008, as tabulated by the same authors (Andersson 2021, Table 1).
    // Bands are the original 2008 cut-offs (<5, 5–8, >8); the 2021 revision proposal is a caveat.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "air",
        name = "Appendicitis Inflammatory Response (AIR) score",
        purpose = "Probability of acute appendicitis, especially advanced appendicitis, in suspected appendicitis.",
        category = ScoreCategory.Gi,
        keywords = listOf("AIR score", "appendicitis", "appendix", "right iliac fossa", "RIF pain", "CRP"),
        items = listOf(
            Criterion("vomiting", "Vomiting", 1.0, section = "Clinical"),
            Criterion("rif", "Pain in right iliac fossa", 1.0),
            Choice(
                "rebound", "Rebound tenderness or guarding",
                listOf(Option("None", 0.0), Option("Light", 1.0), Option("Medium", 2.0), Option("Strong", 3.0)),
                hint = "Rebound tenderness or muscular defence, graded by intensity",
            ),
            Criterion("temp", "Temperature ≥38.5 °C", 1.0),
            Choice(
                "pmn", "Neutrophils (% of white cells)",
                listOf(Option("<70%", 0.0), Option("70–84%", 1.0), Option("≥85%", 2.0)),
                section = "Laboratory",
                hint = "Proportion of polymorphonuclear leucocytes",
            ),
            Choice(
                "wbc", "White cell count (×10⁹/L)",
                listOf(Option("<10.0", 0.0), Option("10.0–14.9", 1.0), Option("≥15.0", 2.0)),
            ),
            Choice(
                "crp", "CRP (mg/L)",
                listOf(Option("<10", 0.0), Option("10–49", 1.0), Option("≥50", 2.0)),
            ),
        ),
        bands = listOf(
            Band(
                0.0, 4.0, "AIR 0–4: low probability",
                "Andersson: low probability — outpatient management with planned re-examination within 24 h. WSES 2025 suggests risk scores such as AIR to rule out appendicitis in low-risk patients.",
                Level.Low,
            ),
            Band(
                5.0, 8.0, "AIR 5–8: indeterminate",
                "Andersson: medium probability — imaging, or in-hospital observation with re-scoring. WSES 2025: intermediate-risk patients need imaging or active observation.",
                Level.Moderate,
            ),
            Band(
                9.0, 12.0, "AIR 9–12: high probability",
                "Andersson: high probability — immediate surgical evaluation for possible abdominal exploration.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Andersson 2021 (external validation by the score's authors): at the original cut-off (<5) sensitivity for complicated appendicitis was 96%; they proposed a low-probability band of 0–3 and medium of 4–8. Bands shown here are the original 2008 cut-offs.",
                listOf(cite("sc-andersson-2021", "Discussion; Table 1 footnote")),
            ),
            Caveat(
                "WSES 2020 recommends the AIR and Adult Appendicitis Score as clinical predictors of appendicitis (strong recommendation); WSES 2025 suggests AIR, AAS or PAS for the initial work-up, with CT remaining the diagnostic standard in unclear presentations.",
                listOf(
                    cite("sc-wses-appendicitis-2020", "Recommendation 1.4"),
                    cite("sc-wses-appendicitis-2025", "Recommendation 1"),
                ),
            ),
        ),
        citations = listOf(
            cite("sc-andersson-2008"),
            cite("sc-andersson-2021", "Table 1 and management algorithm"),
            cite("sc-wses-appendicitis-2025", "Recommendation 1"),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Glasgow-Blatchford — Blatchford 2000, as tabulated in NICE CG141 full guideline Table 9.
    // Haemoglobin bands depend on sex: the Sex choice reveals the matching Hb question.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "glasgow-blatchford",
        name = "Glasgow-Blatchford score",
        purpose = "Pre-endoscopy risk in acute upper GI bleeding of needing transfusion or intervention, rebleeding or death.",
        category = ScoreCategory.Gi,
        keywords = listOf("GBS", "Blatchford", "upper GI bleed", "UGIB", "haematemesis", "melaena", "gastrointestinal haemorrhage"),
        items = listOf(
            Choice(
                "urea", "Blood urea (mmol/L)",
                listOf(
                    Option("<6.5", 0.0), Option("6.5 to <8.0", 2.0), Option("8.0 to <10.0", 3.0),
                    Option("10.0 to <25", 4.0), Option("≥25", 6.0),
                ),
                section = "Laboratory",
                hint = "Urea in mmol/L (not BUN in mg/dL)",
            ),
            Choice("sex", "Sex", listOf(Option("Male", 0.0), Option("Female", 0.0)), hint = "Haemoglobin bands differ by sex"),
            Choice(
                "hbMale", "Haemoglobin — men",
                listOf(
                    Option("≥130 g/L (≥13 g/dL)", 0.0), Option("120 to <130 g/L (12 to <13 g/dL)", 1.0),
                    Option("100 to <120 g/L (10 to <12 g/dL)", 3.0), Option("<100 g/L (<10 g/dL)", 6.0),
                ),
                showIf = ShowIf("sex", 0),
            ),
            Choice(
                "hbFemale", "Haemoglobin — women",
                listOf(
                    Option("≥120 g/L (≥12 g/dL)", 0.0), Option("100 to <120 g/L (10 to <12 g/dL)", 1.0),
                    Option("<100 g/L (<10 g/dL)", 6.0),
                ),
                showIf = ShowIf("sex", 1),
            ),
            Choice(
                "sbp", "Systolic BP (mm Hg)",
                listOf(Option("≥110", 0.0), Option("100–109", 1.0), Option("90–99", 2.0), Option("<90", 3.0)),
                section = "Haemodynamics",
            ),
            Criterion("pulse", "Pulse ≥100/min", 1.0),
            Criterion("melaena", "Presentation with melaena", 1.0, section = "Other markers"),
            Criterion("syncope", "Presentation with syncope", 2.0),
            Criterion("hepatic", "Hepatic disease", 2.0),
            Criterion("cardiac", "Cardiac failure", 2.0),
        ),
        bands = listOf(
            Band(
                0.0, 0.0, "GBS 0",
                "NICE CG141: consider early discharge for a pre-endoscopy Blatchford score of 0. ESGE 2021: GBS ≤1 is very low risk and can be managed as an outpatient with outpatient endoscopy.",
                Level.Low,
            ),
            Band(
                1.0, 1.0, "GBS 1",
                "ESGE 2021: GBS ≤1 is very low risk of rebleeding, 30-day mortality or hospital-based intervention, and can be managed as an outpatient with outpatient endoscopy. NICE CG141 sets its early-discharge threshold at 0.",
                Level.Low,
            ),
            Band(
                2.0, 23.0, "GBS ≥2: not low risk",
                "Above the low-risk thresholds of NICE CG141 (0) and ESGE 2021 (≤1). Blatchford 2000: higher scores indicate a greater risk of needing transfusion or intervention, rebleeding or death.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Guidelines differ on the outpatient threshold: NICE CG141 (2012) uses a score of 0; ESGE 2021 uses ≤1 (strong recommendation, moderate-quality evidence).",
                listOf(cite("sc-nice-cg141", "Recommendation 1.1.2"), cite("sc-esge-nvugih-2021", "Recommendation 4")),
            ),
            Caveat(
                "NICE CG141: use the Blatchford score at first assessment and the full Rockall score after endoscopy.",
                listOf(cite("sc-nice-cg141", "Recommendation 1.1.1")),
            ),
        ),
        citations = listOf(
            cite("sc-blatchford-2000"),
            cite("sc-nice-cg141", "Recommendations 1.1.1–1.1.2; full guideline Table 9"),
            cite("sc-esge-nvugih-2021", "Recommendation 4"),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Child-Pugh — Pugh 1973; INR alternative and class labels as in FDA hepatic-impairment guidance Table 1.
    // µmol/L and g/L figures are unit conversions of the mg/dL and g/dL cut-offs.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "child-pugh",
        name = "Child-Pugh score",
        purpose = "Severity of liver disease in cirrhosis, graded as class A, B or C.",
        category = ScoreCategory.Gi,
        keywords = listOf("Child-Turcotte-Pugh", "CTP", "Child class", "cirrhosis", "liver", "hepatic impairment"),
        items = listOf(
            Choice(
                "bilirubin", "Total bilirubin",
                listOf(
                    Option("<2 mg/dL (<34 µmol/L)", 1.0), Option("2–3 mg/dL (34–51 µmol/L)", 2.0),
                    Option(">3 mg/dL (>51 µmol/L)", 3.0),
                ),
            ),
            Choice(
                "albumin", "Serum albumin",
                listOf(
                    Option(">3.5 g/dL (>35 g/L)", 1.0), Option("2.8–3.5 g/dL (28–35 g/L)", 2.0),
                    Option("<2.8 g/dL (<28 g/L)", 3.0),
                ),
            ),
            Choice(
                "inr", "INR (or PT prolongation)",
                listOf(
                    Option("INR <1.7 (PT prolonged <4 s)", 1.0), Option("INR 1.7–2.3 (PT prolonged 4–6 s)", 2.0),
                    Option("INR >2.3 (PT prolonged >6 s)", 3.0),
                ),
                hint = "Use INR or prothrombin-time prolongation, not both",
            ),
            Choice("ascites", "Ascites", listOf(Option("Absent", 1.0), Option("Slight", 2.0), Option("Moderate or more", 3.0))),
            Choice(
                "encephalopathy", "Hepatic encephalopathy",
                listOf(Option("None", 1.0), Option("Grade 1–2", 2.0), Option("Grade 3–4", 3.0)),
            ),
        ),
        bands = listOf(
            Band(5.0, 6.0, "Child-Pugh A (5–6)", "Pugh 1973 grade A. FDA: Child-Pugh A corresponds to mild hepatic impairment.", Level.Low),
            Band(7.0, 9.0, "Child-Pugh B (7–9)", "Pugh 1973 grade B. FDA: Child-Pugh B corresponds to moderate hepatic impairment.", Level.Moderate),
            Band(10.0, 15.0, "Child-Pugh C (10–15)", "Pugh 1973 grade C. FDA: Child-Pugh C corresponds to severe hepatic impairment.", Level.High),
        ),
        caveats = listOf(
            Caveat(
                "FDA: components can change for reasons other than liver function — vitamin K deficiency or anticoagulants raise INR, Gilbert's syndrome raises bilirubin, and IV fluids dilute albumin. Scoring during or shortly after an acute event (GI bleed, infection, surgery, transfusion) may overstate the baseline.",
                listOf(cite("sc-fda-hepatic-pk", "Section IV.A.1 Child-Pugh Classification")),
            ),
            Caveat(
                "FDA notes that ascites assessment is subjective; record how ascites and encephalopathy were graded (for example, ascites with or without diuretic use).",
                listOf(cite("sc-fda-hepatic-pk", "Section IV.A.1 Child-Pugh Classification")),
            ),
        ),
        citations = listOf(cite("sc-pugh-1973"), cite("sc-fda-hepatic-pk", "Table 1")),
    ),

    // ---------------------------------------------------------------------------------------------
    // Centor / McIsaac — Centor 1981 criteria (wording per NICE NG84), McIsaac age modification.
    // The "Score" choice reveals the age question only for McIsaac. Each band quotes NICE for the
    // plain Centor total and McIsaac 2000 for the age-adjusted total, so the user reads the line that
    // matches the version selected.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "centor-mcisaac",
        name = "Centor / McIsaac score",
        purpose = "Likelihood of group A streptococcal infection in acute sore throat, to guide antibiotic use.",
        category = ScoreCategory.Infection,
        keywords = listOf("Centor criteria", "McIsaac", "modified Centor", "sore throat", "pharyngitis", "tonsillitis", "strep throat", "GAS"),
        items = listOf(
            Criterion("exudate", "Tonsillar exudate", 1.0, section = "Centor criteria", hint = "McIsaac: tonsillar swelling or exudate"),
            Criterion("nodes", "Tender anterior cervical nodes", 1.0, hint = "Lymphadenopathy or lymphadenitis"),
            Criterion("fever", "History of fever (over 38 °C)", 1.0),
            Criterion("noCough", "Absence of cough", 1.0),
            Choice(
                "version", "Score version",
                listOf(
                    Option("Centor (as used by NICE NG84)", 0.0),
                    Option(
                        "McIsaac (adds age points)", 0.0,
                        alert = "NICE NG84 thresholds apply to the Centor score without age points; McIsaac thresholds apply to the age-adjusted total.",
                    ),
                ),
                section = "Version",
            ),
            Choice(
                "age", "Age",
                listOf(Option("3–14 years (+1)", 1.0), Option("15–44 years (0)", 0.0), Option("45 years or older (−1)", -1.0)),
                showIf = ShowIf("version", 1),
            ),
        ),
        bands = listOf(
            Band(
                -1.0, 1.0, "Score ≤1",
                "Centor 0–1 — NICE NG84: unlikely to benefit from an antibiotic; do not offer one. McIsaac ≤1 — McIsaac 2000: neither throat culture nor antibiotic (GAS 1–10%).",
                Level.Low,
            ),
            Band(
                2.0, 2.0, "Score 2",
                "Centor 2 — NICE NG84: unlikely to benefit from an antibiotic; do not offer one. McIsaac 2 — McIsaac 2000: throat culture, antibiotic only if positive (GAS 17%).",
                Level.Low,
            ),
            Band(
                3.0, 3.0, "Score 3",
                "Centor 3 — NICE NG84: most likely to benefit; consider an immediate or back-up antibiotic. McIsaac 3 — McIsaac 2000: throat culture, antibiotic only if positive (GAS 35%).",
                Level.Moderate,
            ),
            Band(
                4.0, 5.0, "Score ≥4",
                "Centor 4 — NICE NG84: most likely to benefit; consider an immediate or back-up antibiotic. McIsaac ≥4 — McIsaac 2000: start an antibiotic or take a throat culture (GAS 51%).",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "NICE NG84: offer an immediate antibiotic to people who are systemically very unwell, have symptoms or signs of a more serious illness, or are at high risk of complications — whatever the score.",
                listOf(cite("sc-nice-ng84", "Recommendation 1.1.12")),
            ),
            Caveat(
                "NICE NG84 uses FeverPAIN or the unmodified Centor score; Centor 0–2 is associated with a 3–17% and 3–4 with a 32–56% likelihood of isolating streptococcus. NICE does not recommend rapid strep A tests for routine adoption, whereas McIsaac's actions rely on throat culture.",
                listOf(cite("sc-nice-ng84", "Terms used in the guideline; recommendation 1.1.5"), cite("sc-mcisaac-2000")),
            ),
            Caveat(
                "Centor was derived in adults in an emergency room; McIsaac added age points and validated the score in family practice patients aged 3 years and over.",
                listOf(cite("sc-centor-1981"), cite("sc-mcisaac-1998")),
            ),
        ),
        citations = listOf(
            cite("sc-centor-1981"),
            cite("sc-mcisaac-1998"),
            cite("sc-mcisaac-2000", "Score-based recommendations"),
            cite("sc-nice-ng84", "Recommendations 1.1.3–1.1.10"),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Bishop — Bishop 1964 as reproduced in ACOG CPG No. 9 Table 1 (2025, replaces PB 107).
    // Bands follow the NICE NG207 threshold (≤6 vs >6); ACOG sets no single threshold.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "bishop",
        name = "Bishop score",
        purpose = "Readiness of the cervix for labour, used to choose the method of induction.",
        category = ScoreCategory.Obstetric,
        keywords = listOf("Bishop", "cervical ripening", "induction of labour", "induction of labor", "IOL", "unfavourable cervix"),
        items = listOf(
            Choice("dilation", "Dilation", listOf(Option("Closed", 0.0), Option("1–2 cm", 1.0), Option("3–4 cm", 2.0), Option("5–6 cm", 3.0))),
            Choice("effacement", "Effacement", listOf(Option("0–30%", 0.0), Option("40–50%", 1.0), Option("60–70%", 2.0), Option("≥80%", 3.0))),
            Choice(
                "station", "Station",
                listOf(Option("−3", 0.0), Option("−2", 1.0), Option("−1 or 0", 2.0), Option("+1 or +2", 3.0)),
                hint = "On a −3 to +3 scale",
            ),
            Choice("consistency", "Cervical consistency", listOf(Option("Firm", 0.0), Option("Medium", 1.0), Option("Soft", 2.0))),
            Choice("position", "Cervical position", listOf(Option("Posterior", 0.0), Option("Mid-position", 1.0), Option("Anterior", 2.0))),
        ),
        bands = listOf(
            Band(
                0.0, 6.0, "Bishop 0–6",
                "NICE NG207: for a Bishop score of 6 or less, offer vaginal dinoprostone or low-dose (25 microgram) oral misoprostol, or consider a mechanical method such as a balloon catheter if pharmacological methods are unsuitable or the woman chooses it.",
                Level.Info,
            ),
            Band(
                7.0, 13.0, "Bishop 7–13",
                "NICE NG207: for a Bishop score of more than 6, offer induction with amniotomy and an intravenous oxytocin infusion. NICE: a score of 8 or more generally indicates the cervix is ready to dilate.",
                Level.Info,
            ),
        ),
        caveats = listOf(
            Caveat(
                "ACOG (2025): there is no universally accepted definition of an unfavourable cervix; Bishop thresholds from 3 to 8 have been used to define who benefits from cervical ripening before oxytocin. Parity and gestational age also affect induction outcomes.",
                listOf(cite("sc-acog-cpg9", "Clinical overview")),
            ),
            Caveat(
                "ACOG Practice Bulletin No. 107 (Induction of labor, 2009) has been replaced by Clinical Practice Guideline No. 9 (2025).",
                listOf(cite("sc-acog-cpg9")),
            ),
        ),
        citations = listOf(
            cite("sc-bishop-1964"),
            cite("sc-acog-cpg9", "Table 1"),
            cite("sc-nice-ng207", "Recommendations 1.3.7–1.3.9; terms used"),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Ottawa ankle rule — Stiell 1993 refined rule (figure as reproduced in Bachmann 2003).
    // Model: malleolar-zone pain is a 0-point gating item; the three findings are shown and counted
    // only when it is ticked (ShowIf). So total ≥1 ⇔ malleolar pain AND ≥1 finding ⇔ "X-ray indicated";
    // total 0 covers both "no malleolar pain" and "pain without any finding" ⇔ "not required".
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "ottawa-ankle",
        name = "Ottawa ankle rule",
        purpose = "Whether an ankle X-ray series is needed after an acute ankle injury.",
        category = ScoreCategory.Emergency,
        keywords = listOf("Ottawa ankle rules", "ankle injury", "ankle sprain", "ankle fracture", "malleolus", "X-ray", "radiography"),
        items = listOf(
            Criterion(
                "malleolarPain", "Pain in the malleolar zone", 0.0,
                hint = "The findings below count only when there is malleolar-zone pain",
            ),
            Criterion(
                "lateral", "Bone tenderness: lateral malleolus", 1.0,
                hint = "Posterior edge or tip, distal 6 cm",
                showIf = ShowIf("malleolarPain", 1),
            ),
            Criterion(
                "medial", "Bone tenderness: medial malleolus", 1.0,
                hint = "Posterior edge or tip, distal 6 cm",
                showIf = ShowIf("malleolarPain", 1),
            ),
            Criterion(
                "weightBearing", "Unable to bear weight", 1.0,
                hint = "Both immediately after the injury and in the emergency department (4 steps)",
                showIf = ShowIf("malleolarPain", 1),
            ),
        ),
        bands = listOf(
            Band(
                0.0, 0.0, "Ankle X-ray not required by the rule",
                "Ottawa ankle rule (Stiell 1993): no malleolar-zone pain, or pain without any listed finding — an ankle X-ray series is not required. Bachmann 2003: pooled sensitivity for fracture 97.6%.",
                Level.Low,
            ),
            Band(
                1.0, 3.0, "Ankle X-ray indicated",
                "Ottawa ankle rule (Stiell 1993): malleolar-zone pain with at least one listed finding — an ankle X-ray series is required. ACR 2020: radiography is usually appropriate when the rule is positive.",
                Level.Moderate,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Derived and validated in adults. ACR 2020: validated for adults and children over 5 years; not for children under 5 or people with a neurological abnormality reducing sensation in the leg (e.g. diabetic neuropathy), altered sensorium or inability to communicate; take care in pregnancy, penetrating trauma or with recent outside X-rays.",
                listOf(cite("sc-stiell-1993"), cite("sc-acr-ankle-2020", "Exclusionary criteria")),
            ),
            Caveat(
                "Bachmann 2003 (systematic review): sensitivity close to 100% with modest specificity; evidence in children is sparse.",
                listOf(cite("sc-bachmann-2003")),
            ),
            Caveat(
                "Midfoot pain is assessed with the separate Ottawa foot rule.",
                listOf(cite("sc-stiell-1993")),
            ),
        ),
        citations = listOf(
            cite("sc-stiell-1993", "Refined ankle rule"),
            cite("sc-bachmann-2003", "Figure 1"),
            cite("sc-acr-ankle-2020", "Variants 1–2"),
        ),
        showTotal = false,
    ),

    // ---------------------------------------------------------------------------------------------
    // Ottawa foot rule — same gating model as the ankle rule, for the midfoot zone.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "ottawa-foot",
        name = "Ottawa foot rule",
        purpose = "Whether a foot X-ray series is needed after an acute ankle or midfoot injury.",
        category = ScoreCategory.Emergency,
        keywords = listOf("Ottawa ankle rules", "Ottawa foot rules", "midfoot", "foot injury", "fifth metatarsal", "navicular", "X-ray"),
        items = listOf(
            Criterion(
                "midfootPain", "Pain in the midfoot zone", 0.0,
                hint = "The findings below count only when there is midfoot-zone pain",
            ),
            Criterion(
                "fifthMetatarsal", "Bone tenderness: base of 5th metatarsal", 1.0,
                showIf = ShowIf("midfootPain", 1),
            ),
            Criterion(
                "navicular", "Bone tenderness: navicular", 1.0,
                showIf = ShowIf("midfootPain", 1),
            ),
            Criterion(
                "weightBearing", "Unable to bear weight", 1.0,
                hint = "Both immediately after the injury and in the emergency department (4 steps)",
                showIf = ShowIf("midfootPain", 1),
            ),
        ),
        bands = listOf(
            Band(
                0.0, 0.0, "Foot X-ray not required by the rule",
                "Ottawa foot rule (Stiell 1993): no midfoot-zone pain, or pain without any listed finding — a foot X-ray series is not required. Bachmann 2003: pooled sensitivity for fracture 97.6%.",
                Level.Low,
            ),
            Band(
                1.0, 3.0, "Foot X-ray indicated",
                "Ottawa foot rule (Stiell 1993): midfoot-zone pain with at least one listed finding — a foot X-ray series is required. ACR 2020: patients meeting the Ottawa rules should have radiographs.",
                Level.Moderate,
            ),
        ),
        caveats = listOf(
            Caveat(
                "ACR 2020: do not use, or use with great caution, in penetrating trauma, pregnancy, any skin wound, transfer with X-rays already taken, more than 10 days after injury, a return visit, polytrauma, altered sensorium, neurological abnormality of the foot, or underlying bone disease. Validated in adults and children over 5.",
                listOf(cite("sc-acr-foot-2020", "Exclusionary criteria")),
            ),
            Caveat(
                "The rule covers the midfoot only; injuries of the metatarsal heads and toes are not addressed (ACR 2020).",
                listOf(cite("sc-acr-foot-2020", "Exclusionary criteria")),
            ),
        ),
        citations = listOf(
            cite("sc-stiell-1993", "Refined foot rule"),
            cite("sc-bachmann-2003", "Figure 1"),
            cite("sc-acr-foot-2020", "Variants 1–2"),
        ),
        showTotal = false,
    ),

    // ---------------------------------------------------------------------------------------------
    // Canadian CT Head Rule — Stiell 2001; wording and thresholds from the rule card published by the
    // same group (Stiell 2010 CMAJ, Figure 1): vomiting ≥2, age ≥65, amnesia ≥30 min, fall ≥3 ft/5 stairs.
    // (The 2001 abstract words these as >2, >65 and >30 min; the card's ≥ form is the more inclusive one.)
    // Weighting: high-risk factors 10 points, medium-risk 1 point, so the band reads directly:
    // 0 = none, 1–2 = medium only, ≥10 = any high-risk factor. The number itself is not shown.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "canadian-ct-head",
        name = "Canadian CT Head Rule",
        purpose = "Whether adults with minor head injury (GCS 13–15) need a CT head scan.",
        category = ScoreCategory.Emergency,
        keywords = listOf("CCHR", "Canadian CT head", "head injury", "minor head injury", "mild traumatic brain injury", "CT head", "TBI"),
        items = listOf(
            Criterion("gcs2h", "GCS <15 at 2 hours after injury", 10.0, section = "High risk (neurosurgical intervention)"),
            Criterion("openFracture", "Suspected open or depressed skull fracture", 10.0),
            Criterion(
                "basalFracture", "Any sign of basal skull fracture", 10.0,
                hint = "Haemotympanum, 'racoon' eyes, CSF otorrhoea or rhinorrhoea, Battle's sign",
            ),
            Criterion("vomiting", "Vomiting ≥2 episodes", 10.0),
            Criterion("age65", "Age ≥65 years", 10.0),
            Criterion("amnesia", "Amnesia before impact ≥30 min", 1.0, section = "Medium risk (brain injury on CT)", hint = "Retrograde amnesia"),
            Criterion(
                "mechanism", "Dangerous mechanism", 1.0,
                hint = "Pedestrian struck by a motor vehicle, occupant ejected from a motor vehicle, or fall from ≥3 feet or 5 stairs",
            ),
        ),
        bands = listOf(
            Band(
                0.0, 0.0, "CT not required by the rule",
                "Canadian CT Head Rule: no high- or medium-risk factor — CT head is not required by the rule (only in patients who meet its inclusion criteria).",
                Level.Low,
            ),
            Band(
                1.0, 2.0, "CT recommended (medium risk)",
                "Canadian CT Head Rule: medium-risk factor present — CT head is required (risk of clinically important brain injury on CT).",
                Level.Moderate,
            ),
            Band(
                10.0, 52.0, "CT recommended (high risk)",
                "Canadian CT Head Rule: high-risk factor present — CT head is required (risk of needing neurosurgical intervention).",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Applies only to minor head injury: GCS 13–15 after witnessed loss of consciousness, definite amnesia or witnessed disorientation (confusion).",
                listOf(cite("sc-stiell-2001"), cite("sc-stiell-2010", "Figure 1")),
            ),
            Caveat(
                "Rule not applicable if: non-trauma case, GCS <13, age <16 years, warfarin (anticoagulant) or bleeding disorder, or obvious open skull fracture.",
                listOf(cite("sc-stiell-2010", "Figure 1"), cite("sc-stiell-2001")),
            ),
            Caveat(
                "NICE NG232 (2023) uses its own criteria, which differ: for example CT within 1 hour for GCS ≤12 on initial assessment, post-traumatic seizure, focal neurological deficit or more than 1 episode of vomiting; within 8 hours, if there was any loss of consciousness or amnesia, for age ≥65, a bleeding or clotting disorder, a dangerous mechanism (fall >1 m or 5 stairs) or >30 min retrograde amnesia; and consider CT for people on anticoagulants or antiplatelets (except aspirin alone).",
                listOf(cite("sc-nice-ng232", "Recommendations 1.5.8, 1.5.9 and 1.5.13")),
            ),
        ),
        citations = listOf(cite("sc-stiell-2001"), cite("sc-stiell-2010", "Figure 1 (rule card)")),
        showTotal = false,
    ),
)
