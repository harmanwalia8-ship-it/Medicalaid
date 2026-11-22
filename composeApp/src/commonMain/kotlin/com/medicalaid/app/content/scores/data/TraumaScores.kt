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
 * Trauma decision rules: Canadian C-spine Rule and NEXUS low-risk criteria (checked 22 November 2026).
 * - Rule wording from the derivation/validation papers (PubMed abstracts of Stiell 2001, Stiell 2003 and Hoffman 2000);
 *   the Canadian rule card is Figure 1 of Stiell 2009 (BMJ, open access), checked against the figure itself.
 * - Band.meaning is attributed; the app reports what the rule and guidelines say, it does not instruct.
 * - Canadian C-spine Rule: one question (applicability + risk group) gates the rotation question, so the neck-rotation
 *   question is never shown for a high-risk patient. Values are priority-ordered (1000 / 100 / 10 / 1 / 0).
 * - NEXUS: the five criteria are named exactly as in Hoffman 2000 (abstract); their detailed operational definitions
 *   (full text, not accessible) are deliberately not paraphrased here. A required first question (all five criteria
 *   assessed?) gates the result, as the Canadian rule's first question does, so no "imaging not required" result shows
 *   before the clinician has confirmed the assessment; "cannot be assessed" scores as a criterion not met.
 * - Score sources must be released sources, so the MoHFW 2025 trauma STG has its own id here (sc-tr-mohfw-stg-trauma-2025);
 *   the same document is tr-mohfw-stg-trauma-2025 in protocols/data/Trauma.kt, which is still in review. The
 *   head and spinal injury protocols (TraumaHeadSpine.kt) cite sc-tr-nice-ng41 and sc-tr-stiell-2003 from this file.
 */

internal val traumaScoreSources = listOf(
    Source(
        "sc-tr-stiell-2001", Authority.Paper, "JAMA 2001;286(15):1841–1848",
        "The Canadian C-spine rule for radiography in alert and stable trauma patients", "2001",
        "https://doi.org/10.1001/jama.286.15.1841",
    ),
    Source(
        "sc-tr-stiell-2009", Authority.Paper, "BMJ 2009;339:b4146",
        "Implementation of the Canadian C-Spine Rule: prospective 12 centre cluster randomised trial", "2009",
        "https://doi.org/10.1136/bmj.b4146",
        status = "Figure 1 is the published rule card",
    ),
    Source(
        "sc-tr-stiell-2003", Authority.Paper, "N Engl J Med 2003;349(26):2510–2518",
        "The Canadian C-spine rule versus the NEXUS low-risk criteria in patients with trauma", "2003",
        "https://doi.org/10.1056/NEJMoa031375",
    ),
    Source(
        "sc-tr-hoffman-2000", Authority.Paper, "N Engl J Med 2000;343(2):94–99",
        "Validity of a set of clinical criteria to rule out injury to the cervical spine in patients with blunt trauma (NEXUS)", "2000",
        "https://doi.org/10.1056/NEJM200007133430203",
    ),
    Source(
        "sc-tr-nice-ng41", Authority.NICE, "Guideline NG41",
        "Spinal injury: assessment and initial management", "2016",
        "https://www.nice.org.uk/guidance/ng41",
        status = "Minor update May 2021 (rec 1.5.7)",
    ),
    Source(
        "sc-tr-mohfw-stg-trauma-2025", Authority.MoHFW, "Standard Treatment Guidelines (Dte.GHS, Trauma and Burn Division)",
        "Standard Treatment Guidelines for Management of Trauma — A Guidance Document for Clinical Management of Trauma in India", "2025",
        "https://www.dghs.mohfw.gov.in/uploads/assets/SwQ7pJMiJepMtqfUQNUJrGOROd584XHIgipqf7gI.pdf",
        status = "Message from MoS (Health) dated 26 March 2025",
    ),
)

private const val STIELL_2001 = "sc-tr-stiell-2001"
private const val STIELL_2009 = "sc-tr-stiell-2009"
private const val STIELL_2003 = "sc-tr-stiell-2003"
private const val HOFFMAN = "sc-tr-hoffman-2000"
private const val NG41 = "sc-tr-nice-ng41"
private const val MOHFW = "sc-tr-mohfw-stg-trauma-2025"
private const val NG232 = "sc-nice-ng232"

internal val traumaScores = listOf(
    // ---------------------------------------------------------------------------------------------
    // Canadian C-spine Rule (Stiell 2001; rule card Stiell 2009 Figure 1).
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "canadian-c-spine",
        name = "Canadian C-spine Rule",
        purpose = "Whether alert, stable adults after blunt trauma need cervical spine imaging.",
        category = ScoreCategory.Emergency,
        keywords = listOf(
            "CCR", "Canadian C-spine", "cervical spine", "C-spine", "neck injury", "neck pain", "whiplash",
            "collar", "spinal immobilisation", "C-spine clearance", "gardan ki chot", "NICE NG41",
        ),
        items = listOf(
            Choice(
                "group", "Applicability, high-risk and low-risk factors — pick the first row that applies",
                listOf(
                    Option(
                        "Not applicable — non-trauma, GCS <15, unstable vital signs, age <16, acute paralysis, known vertebral disease or previous C-spine surgery",
                        1000.0,
                    ),
                    Option("High-risk factor — age ≥65 years, dangerous mechanism or paraesthesias in extremities", 100.0),
                    Option("No high-risk factor and no low-risk factor", 10.0),
                    Option(
                        "No high-risk factor; at least one low-risk factor — simple rear-end collision, sitting in ED, walking at any time, delayed neck pain or no midline tenderness",
                        0.0,
                    ),
                ),
                hint = "Dangerous mechanism: fall from ≥0.9 m (3 ft) or 5 stairs; axial load to head (e.g. diving); motor vehicle collision at high speed (>100 km/h), rollover or ejection; motorised recreational vehicle; bicycle struck or collision. Simple rear-end collision excludes: pushed into oncoming traffic, hit by bus or large truck, rollover, hit by high-speed vehicle. Delayed neck pain: not immediate onset.",
                section = "Alert (GCS 15), stable trauma patient where C-spine injury is a concern",
            ),
            Choice(
                "rotate", "Able to actively rotate the neck 45° left and right?",
                listOf(Option("Able", 0.0), Option("Unable", 1.0)),
                hint = "Assessed only when there is no high-risk factor and at least one low-risk factor",
                section = "Active neck rotation",
                showIf = ShowIf("group", 3),
            ),
        ),
        bands = listOf(
            Band(
                0.0, 0.0, "C-spine imaging not required by the rule",
                "Canadian C-spine Rule (Stiell 2001): a low-risk factor is present and the patient can actively rotate the neck 45° left and right — no radiography. NICE NG41: no immobilisation or imaging if also pain free and none of the thoracic or lumbosacral risk factors in rec 1.1.7 is present.",
                Level.Low,
            ),
            Band(
                1.0, 1.0, "Imaging indicated — unable to rotate neck",
                "Canadian C-spine Rule (Stiell 2001): unable to actively rotate the neck 45° left and right — radiography indicated. NICE NG41: keep full in-line immobilisation and request imaging; CT in adults.",
                Level.Moderate,
            ),
            Band(
                10.0, 10.0, "Imaging indicated — no low-risk factor",
                "Canadian C-spine Rule (Stiell 2001): no low-risk factor, so range of motion cannot be assessed safely — radiography indicated. NICE NG41 advises CT for adults when the rule indicates imaging.",
                Level.Moderate,
            ),
            Band(
                100.0, 100.0, "Imaging indicated — high-risk factor",
                "Canadian C-spine Rule (Stiell 2001): a high-risk factor mandates radiography; range of motion is not assessed. NICE NG41: full in-line immobilisation and imaging; CT in adults.",
                Level.High,
            ),
            Band(
                1000.0, 1000.0, "Rule not applicable",
                "Canadian C-spine Rule card (Stiell 2009): the rule does not apply to this patient, so it cannot be used to clear the spine. NICE NG41: keep full in-line immobilisation if consciousness is reduced, a hand or foot is weak, there are past spinal problems, or the assessment cannot be done. MoHFW 2025: image the cervical spine in every patient with altered sensorium.",
                Level.Info,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Population: alert (GCS 15), stable adults with blunt head or neck trauma — in Stiell 2009, those with neck pain, or without neck pain but with a visible injury above the clavicles, not walking and a dangerous mechanism, injured within 48 hours. Penetrating trauma was excluded.",
                listOf(cite(STIELL_2009, "Methods — study population"), cite(STIELL_2001, "Abstract")),
            ),
            Caveat(
                "Performance: derivation sensitivity 100% (95% CI 98–100%) and specificity 42.5%. Stiell 2003 (alert, stable patients): sensitivity 99.4% vs 90.7% and specificity 45.1% vs 36.8% compared with NEXUS.",
                listOf(cite(STIELL_2001, "Abstract (Results)"), cite(STIELL_2003, "Abstract (Results)")),
            ),
            Caveat(
                "NICE NG41 applies the rule with a fall from more than 1 metre or 5 steps and adds horse-riding accidents to the dangerous mechanisms; it notes the rule is difficult to apply to children (consider developmental stage).",
                listOf(cite(NG41, "Rec 1.1.5–1.1.6")),
            ),
            Caveat(
                "The rule specifies radiography; NICE NG41 advises CT in adults when imaging is indicated by the rule, and MRI after CT if a neurological abnormality could be due to cord injury.",
                listOf(cite(NG41, "Rec 1.5.6–1.5.7")),
            ),
            Caveat(
                "Head injury (16 and over): NICE NG232 advises CT cervical spine within 1 hour for GCS ≤12 on initial assessment, intubation, an urgent need for a definitive diagnosis, blunt polytrauma involving the head and chest, abdomen or pelvis in an alert, stable person, or clinical suspicion of cervical spine injury with age ≥65, a dangerous mechanism, focal peripheral neurological deficit or limb paraesthesia. With neck pain or tenderness but none of these, it also advises CT within 1 hour if neck movement cannot be assessed safely, the person cannot actively rotate the neck 45° left and right, or has a predisposing condition (e.g. axial spondyloarthritis).",
                listOf(cite(NG232, "Rec 1.6.2–1.6.3")),
            ),
            Caveat(
                "MoHFW STG 2025 (Figure 14) shows only three low-risk factors (simple rear-end collision, ambulatory at any time, delayed neck pain); the published rule also includes sitting in the ED and absence of midline tenderness.",
                listOf(cite(MOHFW, "Ch. 9 Spine trauma, Figure 14"), cite(STIELL_2009, "Figure 1")),
            ),
        ),
        citations = listOf(
            cite(STIELL_2001, "Abstract (final rule)"),
            cite(STIELL_2009, "Figure 1 (rule card)"),
            cite(NG41, "Rec 1.1.3, 1.1.5, 1.1.7, 1.4.4, 1.4.7–1.4.8"),
            cite(MOHFW, "Ch. 9 Spine trauma — screening (p. 45)"),
        ),
        showTotal = false,
    ),

    // ---------------------------------------------------------------------------------------------
    // NEXUS low-risk criteria (Hoffman 2000). Any criterion not met → not low risk.
    // A required first question (all five assessed?) keeps the result hidden until it is answered, so the
    // untouched page never reads as "imaging not required"; "cannot be assessed" counts as a criterion not met.
    // ---------------------------------------------------------------------------------------------
    Score(
        id = "nexus-c-spine",
        name = "NEXUS low-risk criteria (C-spine)",
        purpose = "Whether cervical spine imaging can be omitted after blunt trauma.",
        category = ScoreCategory.Emergency,
        keywords = listOf(
            "NEXUS", "NLC", "National Emergency X-Radiography Utilization Study", "cervical spine", "C-spine",
            "neck injury", "C-spine clearance", "collar", "gardan ki chot",
        ),
        items = listOf(
            Choice(
                "assessed", "Could all five criteria be assessed?",
                listOf(
                    Option("Yes — tick below any that is present", 0.0),
                    Option("No — at least one cannot be assessed reliably", 1.0),
                ),
                hint = "NEXUS (Hoffman 2000) classes a patient as low risk only when all five criteria are met. NICE NG41: full in-line immobilisation if its spinal injury assessment cannot be done.",
                section = "Before you start",
            ),
            Criterion("tenderness", "Midline cervical tenderness", 1.0, section = "Any of these present (or cannot be assessed)?"),
            Criterion("deficit", "Focal neurological deficit", 1.0),
            Criterion("alertness", "Altered alertness (not normal alertness)", 1.0),
            Criterion("intoxication", "Intoxication", 1.0),
            Criterion("distracting", "Painful, distracting injury", 1.0),
        ),
        bands = listOf(
            Band(
                0.0, 0.0, "Imaging not required by NEXUS",
                "NEXUS (Hoffman 2000): all five low-risk criteria met — low probability of cervical spine injury; imaging not required by the criteria (sensitivity 99.0%, negative predictive value 99.8%).",
                Level.Low,
            ),
            Band(
                1.0, 6.0, "Imaging indicated by NEXUS",
                "NEXUS (Hoffman 2000): at least one low-risk criterion is not met or could not be assessed — the criteria cannot rule out cervical spine injury; imaging indicated.",
                Level.Moderate,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Validated in 34,069 patients having cervical spine radiography after blunt trauma at 21 US centres: 8 of 818 injuries missed; specificity only 12.9%.",
                listOf(cite(HOFFMAN, "Abstract")),
            ),
            Caveat(
                "Stiell 2003 (alert, stable patients): NEXUS was less sensitive (90.7% vs 99.4%) and less specific (36.8% vs 45.1%) than the Canadian C-spine Rule, missing 16 important injuries against 1.",
                listOf(cite(STIELL_2003, "Abstract (Results)")),
            ),
            Caveat(
                "NICE NG41 uses the Canadian C-spine Rule, not NEXUS; MoHFW STG 2025 lists both NEXUS and the Canadian C-spine Rule for clinical clearance.",
                listOf(cite(NG41, "Rec 1.1.5, 1.4.5"), cite(MOHFW, "Ch. 9 Spine trauma — screening; Figure 14")),
            ),
        ),
        citations = listOf(cite(HOFFMAN, "Abstract (decision instrument)"), cite(STIELL_2003, "Abstract"), cite(NG41, "Rec 1.1.3–1.1.4, 1.4.4")),
        showTotal = false,
    ),
)
