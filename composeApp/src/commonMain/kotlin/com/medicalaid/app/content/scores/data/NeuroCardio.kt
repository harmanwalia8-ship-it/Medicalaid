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

/*
 * Neurology / stroke and cardiology / VTE scores. Criteria, points, cut-offs and risk figures were checked
 * against the derivation papers and the guidelines cited on each score (see ScoreModel / Template.kt rules).
 * Where a per-band risk figure could not be verified from a primary source it is omitted.
 */

internal val neuroCardioScoreSources = listOf(
    // --- Glasgow Coma Scale ---
    Source(
        "sc-teasdale-1974", Authority.Paper, "Lancet 1974;2(7872):81–84",
        "Assessment of coma and impaired consciousness. A practical scale", "1974",
        "https://doi.org/10.1016/S0140-6736(74)91639-0",
    ),
    Source(
        "sc-teasdale-2014", Authority.Paper, "Lancet Neurol 2014;13(8):844–854",
        "The Glasgow Coma Scale at 40 years: standing the test of time", "2014",
        "https://doi.org/10.1016/S1474-4422(14)70120-6",
    ),
    Source(
        "sc-gcs-aid", Authority.Paper, "GCS assessment aid (Institute of Neurological Sciences, Glasgow)",
        "Glasgow Coma Scale: do it this way", "2015",
        "https://www.glasgowcomascale.org/downloads/GCS-Assessment-Aid-English.pdf",
    ),
    Source(
        "sc-cdc-tbi-2014", Authority.CDC, "Report to Congress",
        "Traumatic Brain Injury in the United States: Epidemiology and Rehabilitation", "2014",
        "https://stacks.cdc.gov/view/cdc/29215",
    ),
    // --- NIHSS ---
    Source(
        "sc-brott-1989", Authority.Paper, "Stroke 1989;20(7):864–870",
        "Measurements of acute cerebral infarction: a clinical examination scale", "1989",
        "https://doi.org/10.1161/01.STR.20.7.864",
    ),
    Source(
        "sc-ninds-nihss", Authority.Paper, "NINDS scale form, Rev 10/1/2003",
        "NIH Stroke Scale", "2003",
        "https://www.ninds.nih.gov/sites/default/files/documents/NIH_Stroke_Scale_508C.pdf",
    ),
    Source(
        "sc-aha-ais-2026", Authority.AHA, "AHA/ASA guideline — Stroke 2026;57(8):e316–e436",
        "2026 Guideline for the Early Management of Patients With Acute Ischemic Stroke", "2026",
        "https://doi.org/10.1161/STR.0000000000000513",
    ),
    // --- ABCD² / ROSIER ---
    Source(
        "sc-johnston-2007", Authority.Paper, "Lancet 2007;369(9558):283–292",
        "Validation and refinement of scores to predict very early stroke risk after transient ischaemic attack", "2007",
        "https://doi.org/10.1016/S0140-6736(07)60150-0",
    ),
    Source(
        "sc-nice-ng128", Authority.NICE, "NICE guideline NG128",
        "Stroke and transient ischaemic attack in over 16s: diagnosis and initial management", "2019",
        "https://www.nice.org.uk/guidance/ng128",
        status = "Last updated 13 April 2022",
    ),
    Source(
        "sc-eso-tia-2021", Authority.ESO, "Eur Stroke J 2021;6(2):CLXIII–CLXXXVI",
        "European Stroke Organisation (ESO) guidelines on management of transient ischaemic attack", "2021",
        "https://doi.org/10.1177/2396987321992905",
    ),
    Source(
        "sc-aha-tia-2023", Authority.AHA, "AHA scientific statement — Stroke 2023;54(3):e109–e121",
        "Diagnosis, Workup, Risk Reduction of Transient Ischemic Attack in the Emergency Department Setting", "2023",
        "https://doi.org/10.1161/STR.0000000000000418",
    ),
    Source(
        "sc-nor-2005", Authority.Paper, "Lancet Neurol 2005;4(11):727–734",
        "The Recognition of Stroke in the Emergency Room (ROSIER) scale: development and validation of a stroke recognition instrument", "2005",
        "https://doi.org/10.1016/S1474-4422(05)70201-5",
    ),
    // --- Atrial fibrillation ---
    Source(
        "sc-lip-2010", Authority.Paper, "Chest 2010;137(2):263–272",
        "Refining clinical risk stratification for predicting stroke and thromboembolism in atrial fibrillation using a novel risk factor-based approach: the Euro Heart Survey on atrial fibrillation", "2010",
        "https://doi.org/10.1378/chest.09-1584",
    ),
    Source(
        "sc-pisters-2010", Authority.Paper, "Chest 2010;138(5):1093–1100",
        "A novel user-friendly score (HAS-BLED) to assess 1-year risk of major bleeding in patients with atrial fibrillation: the Euro Heart Survey", "2010",
        "https://doi.org/10.1378/chest.10-0134",
    ),
    Source(
        "sc-acc-aha-af-2023", Authority.AHA, "ACC/AHA/ACCP/HRS guideline — Circulation 2024;149(1):e1–e156",
        "2023 ACC/AHA/ACCP/HRS Guideline for the Diagnosis and Management of Atrial Fibrillation", "2023",
        "https://doi.org/10.1161/CIR.0000000000001193",
    ),
    Source(
        "sc-esc-af-2024", Authority.ESC, "ESC/EACTS guideline — Eur Heart J 2024;45(36):3314–3414",
        "2024 ESC Guidelines for the management of atrial fibrillation", "2024",
        "https://doi.org/10.1093/eurheartj/ehae176",
    ),
    Source(
        "sc-esc-af-2024-key", Authority.Paper, "Eur Heart J 2025;46(11):996–998",
        "The ‘10 commandments’ for the 2024 European Society of Cardiology guidelines on atrial fibrillation", "2025",
        "https://doi.org/10.1093/eurheartj/ehae847",
    ),
    Source(
        "sc-nice-ng196", Authority.NICE, "NICE guideline NG196",
        "Atrial fibrillation: diagnosis and management", "2021",
        "https://www.nice.org.uk/guidance/ng196",
        status = "Last updated 30 June 2021",
    ),
    // --- Venous thromboembolism ---
    Source(
        "sc-wells-2003", Authority.Paper, "N Engl J Med 2003;349(13):1227–1235",
        "Evaluation of D-dimer in the diagnosis of suspected deep-vein thrombosis", "2003",
        "https://doi.org/10.1056/NEJMoa023153",
    ),
    Source(
        "sc-wells-2000", Authority.Paper, "Thromb Haemost 2000;83(3):416–420",
        "Derivation of a simple clinical model to categorize patients probability of pulmonary embolism: increasing the models utility with the SimpliRED D-dimer", "2000",
        "https://doi.org/10.1055/s-0037-1613830",
    ),
    Source(
        "sc-kline-2004", Authority.Paper, "J Thromb Haemost 2004;2(8):1247–1255",
        "Clinical criteria to prevent unnecessary diagnostic testing in emergency department patients with suspected pulmonary embolism", "2004",
        "https://doi.org/10.1111/j.1538-7836.2004.00790.x",
    ),
    Source(
        "sc-kline-2008", Authority.Paper, "J Thromb Haemost 2008;6(5):772–780",
        "Prospective multicenter evaluation of the pulmonary embolism rule-out criteria", "2008",
        "https://doi.org/10.1111/j.1538-7836.2008.02944.x",
    ),
    Source(
        "sc-nice-ng158", Authority.NICE, "NICE guideline NG158",
        "Venous thromboembolic diseases: diagnosis, management and thrombophilia testing", "2020",
        "https://www.nice.org.uk/guidance/ng158",
        status = "Last updated 2 August 2023",
    ),
    // --- Chest pain / acute coronary syndromes ---
    Source(
        "sc-six-2008", Authority.Paper, "Neth Heart J 2008;16(6):191–196",
        "Chest pain in the emergency room: value of the HEART score", "2008",
        "https://doi.org/10.1007/BF03086144",
    ),
    Source(
        "sc-backus-2013", Authority.Paper, "Int J Cardiol 2013;168(3):2153–2158",
        "A prospective validation of the HEART score for chest pain patients at the emergency department", "2013",
        "https://doi.org/10.1016/j.ijcard.2013.01.255",
    ),
    Source(
        "sc-poldervaart-2013", Authority.Paper, "BMC Cardiovasc Disord 2013;13:77",
        "The impact of the HEART risk score in the early assessment of patients with acute chest pain: design of a stepped wedge, cluster randomised trial", "2013",
        "https://doi.org/10.1186/1471-2261-13-77",
    ),
    Source(
        "sc-aha-chestpain-2021", Authority.AHA, "AHA/ACC/ASE/CHEST/SAEM/SCCT/SCMR guideline — Circulation 2021;144(22):e368–e454",
        "2021 Guideline for the Evaluation and Diagnosis of Chest Pain", "2021",
        "https://doi.org/10.1161/CIR.0000000000001029",
    ),
    Source(
        "sc-antman-2000", Authority.Paper, "JAMA 2000;284(7):835–842",
        "The TIMI risk score for unstable angina/non-ST elevation MI: a method for prognostication and therapeutic decision making", "2000",
        "https://doi.org/10.1001/jama.284.7.835",
    ),
    Source(
        "sc-nice-ng185", Authority.NICE, "NICE guideline NG185",
        "Acute coronary syndromes", "2020",
        "https://www.nice.org.uk/guidance/ng185",
    ),
)

// NIHSS shared wording (NINDS form, Rev 10/1/2003).
private const val NIHSS_UN_ALERT =
    "NIHSS form: untestable (UN) is recorded with a written explanation and adds 0 to this total."

private fun nihssArm(id: String, label: String) = Choice(
    id, label,
    listOf(
        Option("No drift; limb holds 90 (or 45) degrees for full 10 seconds", 0.0),
        Option("Drift; limb holds 90 (or 45) degrees, but drifts down before full 10 seconds; does not hit bed or other support", 1.0),
        Option("Some effort against gravity; limb cannot get to or maintain (if cued) 90 (or 45) degrees, drifts down to bed, but has some effort against gravity", 2.0),
        Option("No effort against gravity; limb falls", 3.0),
        Option("No movement", 4.0),
        Option("UN = Amputation or joint fusion", 0.0, alert = NIHSS_UN_ALERT),
    ),
    hint = "Arm extended palms down at 90° (sitting) or 45° (supine) for 10 s. Test the non-paretic arm first.",
    section = "Motor",
)

private fun nihssLeg(id: String, label: String) = Choice(
    id, label,
    listOf(
        Option("No drift; leg holds 30-degree position for full 5 seconds", 0.0),
        Option("Drift; leg falls by the end of the 5-second period but does not hit bed", 1.0),
        Option("Some effort against gravity; leg falls to bed by 5 seconds, but has some effort against gravity", 2.0),
        Option("No effort against gravity; leg falls to bed immediately", 3.0),
        Option("No movement", 4.0),
        Option("UN = Amputation or joint fusion", 0.0, alert = NIHSS_UN_ALERT),
    ),
    hint = "Leg held at 30° (always supine) for 5 s. Test the non-paretic leg first.",
    section = "Motor",
)

internal val neuroCardioScores = listOf(

    // -----------------------------------------------------------------------------------------
    // Glasgow Coma Scale
    // -----------------------------------------------------------------------------------------
    Score(
        id = "gcs",
        name = "Glasgow Coma Scale (GCS)",
        purpose = "Level of consciousness from eye, verbal and motor responses (total 3–15).",
        category = ScoreCategory.Neuro,
        keywords = listOf("GCS", "Glasgow", "coma", "consciousness", "head injury", "TBI", "traumatic brain injury"),
        items = listOf(
            Choice(
                "eye", "Eye opening",
                listOf(
                    Option("Spontaneous — open before stimulus", 4.0),
                    Option("To sound — after spoken or shouted request", 3.0),
                    Option("To pressure — after fingertip stimulus", 2.0),
                    Option("None — no opening, no interfering factor", 1.0),
                ),
                hint = "Eyes closed by a local factor (e.g. swelling) are non-testable — see caveats",
            ),
            Choice(
                "verbal", "Verbal response",
                listOf(
                    Option("Orientated — correctly gives name, place and date", 5.0),
                    Option("Confused — not orientated but communicates coherently", 4.0),
                    Option("Words — intelligible single words", 3.0),
                    Option("Sounds — only moans or groans", 2.0),
                    Option("None — no audible response, no interfering factor", 1.0),
                ),
                hint = "A factor interfering with communication (e.g. intubation) is non-testable — see caveats",
            ),
            Choice(
                "motor", "Best motor response",
                listOf(
                    Option("Obeys commands — 2-part request", 6.0),
                    Option("Localising — brings hand above clavicle to stimulus on head/neck", 5.0),
                    Option("Normal flexion — bends arm at elbow rapidly, features not predominantly abnormal", 4.0),
                    Option("Abnormal flexion — bends arm at elbow, features clearly predominantly abnormal", 3.0),
                    Option("Extension — extends arm at elbow", 2.0),
                    Option("None — no movement in arms/legs, no interfering factor", 1.0),
                ),
                hint = "Rate the highest response observed. Physical stimulus: fingertip, trapezius or supraorbital notch pressure",
            ),
        ),
        bands = listOf(
            Band(
                3.0, 8.0, "GCS 3–8",
                "CDC TBI Report to Congress (Table 2): GCS 3–8 (best available score in 24 h) is the severe traumatic brain injury range.",
                Level.High,
            ),
            Band(
                9.0, 12.0, "GCS 9–12",
                "CDC TBI Report to Congress (Table 2): GCS 9–12 (best available score in 24 h) is the moderate traumatic brain injury range.",
                Level.Moderate,
            ),
            Band(
                13.0, 15.0, "GCS 13–15",
                "CDC TBI Report to Congress (Table 2): GCS 13–15 (best available score in 24 h) is the mild traumatic brain injury range.",
                Level.Low,
            ),
        ),
        caveats = listOf(
            Caveat(
                "A component that cannot be assessed is recorded as NT (non-testable), not scored 1: eyes closed by a local factor, a factor interfering with communication, or paralysis or another limiting factor. This calculator cannot total NT components; Teasdale 2014 advises that individual patients are best described by the three components (E, V, M) rather than the total.",
                listOf(cite("sc-gcs-aid", "Non testable (NT) rows"), cite("sc-teasdale-2014", "Abstract")),
            ),
            Caveat(
                "CDC: TBI severity can be misclassified when the GCS is used alone — sedation, alcohol or drug intoxication and organ system failure can lower the score independently of the injury. The severity bands shown apply to traumatic brain injury.",
                listOf(cite("sc-cdc-tbi-2014", "Injury Severity Classification of TBI, Tables 1–2")),
            ),
        ),
        citations = listOf(
            cite("sc-teasdale-1974"),
            cite("sc-gcs-aid", "Criteria and ratings"),
            cite("sc-cdc-tbi-2014", "Table 2"),
        ),
    ),

    // -----------------------------------------------------------------------------------------
    // NIH Stroke Scale
    // -----------------------------------------------------------------------------------------
    Score(
        id = "nihss",
        name = "NIH Stroke Scale (NIHSS)",
        purpose = "Standardised measure of neurological deficit in acute stroke (total 0–42).",
        category = ScoreCategory.Neuro,
        keywords = listOf("NIHSS", "NIH stroke scale", "stroke severity", "acute stroke", "thrombolysis", "thrombectomy"),
        items = listOf(
            Choice(
                "loc", "1a. Level of consciousness",
                listOf(
                    Option("Alert; keenly responsive", 0.0),
                    Option("Not alert; but arousable by minor stimulation to obey, answer, or respond", 1.0),
                    Option("Not alert; requires repeated stimulation to attend, or is obtunded and requires strong or painful stimulation to make movements (not stereotyped)", 2.0),
                    Option(
                        "Responds only with reflex motor or autonomic effects or totally unresponsive, flaccid, and areflexic", 3.0,
                        alert = "NIHSS form: with 1a = 3 (coma), item 8 (sensory) is scored 2 and item 9 (language) is scored 3 automatically.",
                    ),
                ),
                hint = "Choose a response even if an ET tube, language barrier or orotracheal trauma limits testing. Score 3 only if no movement (other than reflexive posturing) to noxious stimulation.",
                section = "Level of consciousness",
            ),
            Choice(
                "loc_questions", "1b. LOC questions",
                listOf(
                    Option("Answers both questions correctly", 0.0),
                    Option("Answers one question correctly", 1.0),
                    Option("Answers neither question correctly", 2.0),
                ),
                hint = "Month and age; no partial credit. Aphasic or stuporous and not comprehending: 2. Unable to speak (intubation, orotracheal trauma, severe dysarthria, language barrier) and not aphasic: 1.",
                section = "Level of consciousness",
            ),
            Choice(
                "loc_commands", "1c. LOC commands",
                listOf(
                    Option("Performs both tasks correctly", 0.0),
                    Option("Performs one task correctly", 1.0),
                    Option("Performs neither task correctly", 2.0),
                ),
                hint = "Open and close eyes; grip and release the non-paretic hand (or another one-step command). Credit an unequivocal attempt. Only the first attempt is scored.",
                section = "Level of consciousness",
            ),
            Choice(
                "gaze", "2. Best gaze",
                listOf(
                    Option("Normal", 0.0),
                    Option("Partial gaze palsy; gaze is abnormal in one or both eyes, but forced deviation or total gaze paresis is not present", 1.0),
                    Option("Forced deviation, or total gaze paresis not overcome by the oculocephalic maneuver", 2.0),
                ),
                hint = "Horizontal eye movements only (voluntary or oculocephalic). Conjugate deviation overcome by voluntary or reflexive activity, or isolated CN III, IV or VI paresis: 1.",
                section = "Gaze, vision and face",
            ),
            Choice(
                "visual", "3. Visual fields",
                listOf(
                    Option("No visual loss", 0.0),
                    Option("Partial hemianopia", 1.0),
                    Option("Complete hemianopia", 2.0),
                    Option("Bilateral hemianopia (blind including cortical blindness)", 3.0),
                ),
                hint = "Confrontation, upper and lower quadrants. Score 1 only for clear-cut asymmetry (incl. quadrantanopia). Blind from any cause: 3. Extinction on double simultaneous stimulation scores 1 here and is used for item 11.",
                section = "Gaze, vision and face",
            ),
            Choice(
                "facial", "4. Facial palsy",
                listOf(
                    Option("Normal symmetrical movements", 0.0),
                    Option("Minor paralysis (flattened nasolabial fold, asymmetry on smiling)", 1.0),
                    Option("Partial paralysis (total or near-total paralysis of lower face)", 2.0),
                    Option("Complete paralysis of one or both sides (absence of facial movement in the upper and lower face)", 3.0),
                ),
                hint = "Show teeth or raise eyebrows and close eyes. In the poorly responsive patient, score symmetry of grimace to noxious stimuli.",
                section = "Gaze, vision and face",
            ),
            nihssArm("arm_left", "5a. Motor — left arm"),
            nihssArm("arm_right", "5b. Motor — right arm"),
            nihssLeg("leg_left", "6a. Motor — left leg"),
            nihssLeg("leg_right", "6b. Motor — right leg"),
            Choice(
                "ataxia", "7. Limb ataxia",
                listOf(
                    Option("Absent", 0.0),
                    Option("Present in one limb", 1.0),
                    Option("Present in two limbs", 2.0),
                    Option("UN = Amputation or joint fusion", 0.0, alert = NIHSS_UN_ALERT),
                ),
                hint = "Finger-nose-finger and heel-shin, eyes open. Score only if out of proportion to weakness. Absent in the patient who cannot understand or is paralysed.",
                section = "Ataxia and sensation",
            ),
            Choice(
                "sensory", "8. Sensory",
                listOf(
                    Option("Normal; no sensory loss", 0.0),
                    Option("Mild-to-moderate sensory loss; patient feels pinprick is less sharp or is dull on the affected side; or there is a loss of superficial pain with pinprick, but patient is aware of being touched", 1.0),
                    Option("Severe to total sensory loss; patient is not aware of being touched in the face, arm, and leg", 2.0),
                ),
                hint = "Pinprick, or grimace/withdrawal if obtunded or aphasic; only stroke-related loss. Bilateral brainstem loss, or unresponsive and quadriplegic: 2. Coma (1a = 3): 2.",
                section = "Ataxia and sensation",
            ),
            Choice(
                "language", "9. Best language",
                listOf(
                    Option("No aphasia; normal", 0.0),
                    Option("Mild-to-moderate aphasia; some obvious loss of fluency or facility of comprehension, without significant limitation on ideas expressed or form of expression", 1.0),
                    Option("Severe aphasia; all communication is through fragmentary expression; great need for inference, questioning, and guessing by the listener", 2.0),
                    Option("Mute, global aphasia; no usable speech or auditory comprehension", 3.0),
                ),
                hint = "Describe the picture, name the items, read the sentences; intubated patients write. Coma (1a = 3): 3. Score 3 only if mute and follows no one-step commands.",
                section = "Language, speech and attention",
            ),
            Choice(
                "dysarthria", "10. Dysarthria",
                listOf(
                    Option("Normal", 0.0),
                    Option("Mild-to-moderate dysarthria; patient slurs at least some words and, at worst, can be understood with some difficulty", 1.0),
                    Option("Severe dysarthria; patient's speech is so slurred as to be unintelligible in the absence of or out of proportion to any dysphasia, or is mute/anarthric", 2.0),
                    Option("UN = Intubated or other physical barrier", 0.0, alert = NIHSS_UN_ALERT),
                ),
                hint = "Read or repeat words from the list. UN only if intubated or another physical barrier to producing speech.",
                section = "Language, speech and attention",
            ),
            Choice(
                "extinction", "11. Extinction and inattention",
                listOf(
                    Option("No abnormality", 0.0),
                    Option("Visual, tactile, auditory, spatial, or personal inattention or extinction to bilateral simultaneous stimulation in one of the sensory modalities", 1.0),
                    Option("Profound hemi-inattention or extinction to more than one modality; does not recognize own hand or orients to only one side of space", 2.0),
                ),
                hint = "Formerly neglect. Visual-spatial neglect or anosognosia counts as abnormal. Never untestable.",
                section = "Language, speech and attention",
            ),
        ),
        bands = listOf(
            Band(
                0.0, 42.0, "NIHSS total",
                "NINDS NIH Stroke Scale total (0–42); higher totals reflect a greater neurological deficit. AHA/ASA 2026: a stroke severity rating scale, preferably the NIHSS, is recommended to measure deficits at baseline and after reperfusion therapies.",
                Level.Info,
            ),
        ),
        caveats = listOf(
            Caveat(
                "NINDS instructions: administer items in the listed order, record each score immediately and do not go back and change scores. Scores should reflect what the patient does, not what the clinician thinks the patient can do.",
                listOf(cite("sc-ninds-nihss", "General instructions")),
            ),
            Caveat(
                "Untestable (UN) is allowed only for limb motor and ataxia (amputation or joint fusion) and dysarthria (intubation or other physical barrier), with a written explanation; UN adds 0 to this total. Item 11 is never untestable.",
                listOf(cite("sc-ninds-nihss", "Items 5, 6, 7, 10, 11")),
            ),
            Caveat(
                "AHA/ASA 2026 (section 4.8, rec 12, class 1): in minor (NIHSS ≤3) noncardioembolic ischaemic stroke or high-risk TIA (ABCD² ≥4) without IV thrombolysis, start aspirin plus clopidogrel (with clopidogrel loading) within 24 hours and continue for 21 days, then single antiplatelet therapy.",
                listOf(cite("sc-aha-ais-2026", "Section 4.8, recommendation 12")),
            ),
        ),
        citations = listOf(
            cite("sc-brott-1989"),
            cite("sc-ninds-nihss", "Items 1a–11"),
            cite("sc-aha-ais-2026", "Section 3.1, recommendation 1"),
        ),
    ),

    // -----------------------------------------------------------------------------------------
    // ABCD²
    // -----------------------------------------------------------------------------------------
    Score(
        id = "abcd2",
        name = "ABCD² score",
        purpose = "Estimated 2-day stroke risk after a transient ischaemic attack (TIA).",
        category = ScoreCategory.Neuro,
        keywords = listOf("ABCD2", "TIA", "transient ischaemic attack", "transient ischemic attack", "stroke risk"),
        items = listOf(
            Criterion("age", "Age ≥60 years", 1.0),
            Criterion("bp", "Blood pressure ≥140/90 mm Hg", 1.0, hint = "Systolic ≥140 or diastolic ≥90 mm Hg"),
            Choice(
                "clinical", "Clinical features",
                listOf(
                    Option("Other symptoms (no weakness or speech disturbance)", 0.0),
                    Option("Speech disturbance without weakness", 1.0),
                    Option("Unilateral weakness", 2.0),
                ),
            ),
            Choice(
                "duration", "Duration of symptoms",
                listOf(
                    Option("<10 minutes", 0.0),
                    Option("10–59 minutes", 1.0),
                    Option("≥60 minutes", 2.0),
                ),
            ),
            Criterion("diabetes", "Diabetes", 1.0),
        ),
        bands = listOf(
            Band(
                0.0, 3.0, "ABCD² 0–3",
                "Johnston 2007: 2-day stroke risk 1.0% (34% of patients). ESO 2021: scores of 3 or less may include significant numbers of patients at risk of recurrent stroke who need early assessment.",
                Level.Low,
            ),
            Band(
                4.0, 5.0, "ABCD² 4–5",
                "Johnston 2007: 2-day stroke risk 4.1% (45% of patients). AHA/ASA 2026 uses ABCD² ≥4 to define high-risk TIA for early dual antiplatelet therapy.",
                Level.Moderate,
            ),
            Band(
                6.0, 7.0, "ABCD² 6–7",
                "Johnston 2007: 2-day stroke risk 8.1% (21% of patients). AHA/ASA 2026 uses ABCD² ≥4 to define high-risk TIA for early dual antiplatelet therapy.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "NICE NG128 (2019): “Refer immediately people who have had a suspected TIA for specialist assessment and investigation, to be seen within 24 hours of onset of symptoms.” “Do not use scoring systems, such as ABCD2, to assess risk of subsequent stroke or to inform urgency of referral for people who have had a suspected or confirmed TIA.”",
                listOf(cite("sc-nice-ng128", "Recommendations 1.1.5 and 1.1.6")),
            ),
            Caveat(
                "ESO 2021 recommends against using prediction tools (e.g. ABCD2) alone to identify high-risk patients or to make triage and treatment decisions in suspected TIA, because of their limited sensitivity.",
                listOf(cite("sc-eso-tia-2021", "Abstract / prediction tools")),
            ),
            Caveat(
                "AHA position differs from NICE: the 2023 AHA scientific statement identifies high-risk patients through protocols combining acute brain and cerebrovascular imaging, thoughtful use of risk stratification scales and ancillary testing; the AHA/ASA 2026 guideline gives aspirin plus clopidogrel for 21 days, started within 24 hours, in high-risk TIA (ABCD² ≥4) or minor stroke (NIHSS ≤3) without IV thrombolysis (class 1).",
                listOf(cite("sc-aha-tia-2023", "Abstract"), cite("sc-aha-ais-2026", "Section 4.8, recommendation 12")),
            ),
        ),
        citations = listOf(
            cite("sc-johnston-2007", "ABCD² points and 2-day risks"),
            cite("sc-nice-ng128", "Recommendations 1.1.5–1.1.6"),
        ),
    ),

    // -----------------------------------------------------------------------------------------
    // ROSIER
    // -----------------------------------------------------------------------------------------
    Score(
        id = "rosier",
        name = "ROSIER scale",
        purpose = "Recognition of Stroke in the Emergency Room: separates stroke from stroke mimics in the ED.",
        category = ScoreCategory.Neuro,
        keywords = listOf("ROSIER", "stroke recognition", "stroke mimic", "emergency room", "emergency department"),
        items = listOf(
            Criterion("loc", "Loss of consciousness or syncope", -1.0, section = "History"),
            Criterion("seizure", "Seizure activity", -1.0, section = "History"),
            Criterion("face", "Asymmetric facial weakness", 1.0, section = "New acute onset (or on waking from sleep)"),
            Criterion("arm", "Asymmetric arm weakness", 1.0, section = "New acute onset (or on waking from sleep)"),
            Criterion("leg", "Asymmetric leg weakness", 1.0, section = "New acute onset (or on waking from sleep)"),
            Criterion("speech", "Speech disturbance", 1.0, section = "New acute onset (or on waking from sleep)"),
            Criterion("visual", "Visual field defect", 1.0, section = "New acute onset (or on waking from sleep)"),
        ),
        bands = listOf(
            Band(
                -2.0, 0.0, "ROSIER ≤0",
                "Nor 2005: a score of 0 or less makes stroke unlikely but does not exclude it.",
                Level.Low,
            ),
            Band(
                1.0, 5.0, "ROSIER >0",
                "Nor 2005: stroke likely (score >0); at this cut-off, prospective validation showed sensitivity 93% and specificity 83%.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "NICE NG128: exclude hypoglycaemia as the cause of sudden-onset neurological symptoms; in people admitted to the emergency department with suspected stroke or TIA, establish the diagnosis rapidly using a validated tool, such as ROSIER.",
                listOf(cite("sc-nice-ng128", "Recommendations 1.1.2 and 1.1.3")),
            ),
            Caveat(
                "Developed and validated in ED patients with suspected stroke; patients with suspected TIA who had no symptoms or signs when assessed in the ED were excluded.",
                listOf(cite("sc-nor-2005", "Methods")),
            ),
        ),
        citations = listOf(
            cite("sc-nor-2005", "ROSIER items, cut-off >0"),
            cite("sc-nice-ng128", "Recommendation 1.1.3"),
        ),
    ),

    // -----------------------------------------------------------------------------------------
    // CHA₂DS₂-VASc
    // -----------------------------------------------------------------------------------------
    Score(
        id = "cha2ds2-vasc",
        name = "CHA₂DS₂-VASc",
        purpose = "Stroke and thromboembolism risk in atrial fibrillation, used in anticoagulation decisions.",
        category = ScoreCategory.Cardio,
        keywords = listOf("CHA2DS2-VASc", "CHA2DS2-VA", "CHADS2", "atrial fibrillation", "AF", "stroke risk", "anticoagulation"),
        items = listOf(
            Criterion(
                "chf", "Heart failure", 1.0,
                hint = "Signs/symptoms of right or left heart failure, with objective evidence of cardiac dysfunction",
            ),
            Criterion(
                "htn", "Hypertension", 1.0,
                hint = "Resting BP >140 systolic and/or >90 diastolic on ≥2 occasions, or on antihypertensive treatment",
            ),
            Choice(
                "age", "Age",
                listOf(
                    Option("<65 years", 0.0),
                    Option("65–74 years", 1.0),
                    Option("≥75 years", 2.0),
                ),
            ),
            Criterion(
                "diabetes", "Diabetes", 1.0,
                hint = "Fasting glucose ≥7.0 mmol/L (126 mg/dL) or on glucose-lowering drugs or insulin",
            ),
            Criterion(
                "stroke", "Stroke, TIA or thromboembolism", 2.0,
                hint = "Ischaemic stroke, TIA, peripheral embolism or pulmonary embolism (original definitions)",
            ),
            Criterion(
                "vascular", "Vascular disease", 1.0,
                hint = "Prior MI, angina, PCI or CABG; or peripheral vascular disease (e.g. claudication, aortic or leg revascularisation)",
            ),
            Choice(
                "sex", "Sex category",
                listOf(
                    Option("Male", 0.0),
                    Option("Female", 1.0),
                ),
                hint = "Female sex scores 1. ESC 2024 CHA₂DS₂-VA omits this item",
            ),
        ),
        bands = listOf(
            Band(
                0.0, 0.0, "CHA₂DS₂-VASc 0",
                "NICE NG196: do not offer anticoagulation to people under 65 with no risk factors other than sex. ACC/AHA 2023: aspirin monotherapy is of no benefit in AF without stroke risk factors.",
                Level.Low,
            ),
            Band(
                1.0, 1.0, "CHA₂DS₂-VASc 1",
                "Men: anticoagulation is reasonable (ACC/AHA 2023, class 2a) and NICE NG196 says consider a DOAC. Women scoring 1 for sex alone: NICE says do not offer anticoagulation.",
                Level.Moderate,
            ),
            Band(
                2.0, 2.0, "CHA₂DS₂-VASc 2",
                "Men: anticoagulation recommended (ACC/AHA 2023, class 1); women (sex plus one factor): reasonable (class 2a). NICE NG196 offers a DOAC to anyone scoring 2 or above, taking bleeding risk into account.",
                Level.High,
            ),
            Band(
                3.0, 9.0, "CHA₂DS₂-VASc ≥3",
                "ACC/AHA 2023: anticoagulation recommended in men and women at this score (class 1). NICE NG196: offer a DOAC, taking bleeding risk into account.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "ACC/AHA/ACCP/HRS 2023: anticoagulation is recommended for an estimated annual thromboembolic risk ≥2% per year (e.g. CHA₂DS₂-VASc ≥2 in men and ≥3 in women; class 1, LOE A) and is reasonable at ≥1% but <2% per year (score 1 in men and 2 in women; class 2a, LOE A).",
                listOf(cite("sc-acc-aha-af-2023", "Section 6.3.1, recommendations 1 and 3")),
            ),
            Caveat(
                "ESC 2024 uses CHA₂DS₂-VA (no sex criterion; female sex is treated as an age-dependent risk modifier rather than a risk factor): a score ≥2 is a class I indicator of elevated risk for starting oral anticoagulation, and a score of 1 should be considered (class IIa). CHA₂DS₂-VA equals this total minus the sex point.",
                listOf(cite("sc-esc-af-2024", "Section 6.1, Recommendation Table 6")),
            ),
            Caveat(
                "NICE NG196: offer a DOAC at CHA₂DS₂-VASc ≥2 and consider one for men scoring 1, taking bleeding risk into account; do not withhold anticoagulation solely because of age or falls risk.",
                listOf(cite("sc-nice-ng196", "Recommendations 1.6.3, 1.6.4, 1.6.8")),
            ),
            Caveat(
                "No annual stroke rate is shown per score: ACC/AHA 2023 notes that absolute risk at a given score varies widely between populations (at a score of 2, annual stroke rates were <1% in 4 of 15 cohorts, 1–2% in 6 and >2% in 5).",
                listOf(cite("sc-acc-aha-af-2023", "Section 6.1, supportive text 1")),
            ),
            Caveat(
                "ESC 2024 recommends oral anticoagulation in AF with hypertrophic cardiomyopathy or cardiac amyloidosis regardless of the CHA₂DS₂-VA score.",
                listOf(cite("sc-esc-af-2024")),
            ),
        ),
        citations = listOf(
            cite("sc-lip-2010", "Derivation (Birmingham 2009 schema)"),
            cite("sc-acc-aha-af-2023", "Table 10 — definitions as in the original article"),
            cite("sc-nice-ng196", "Recommendations 1.2.1 and 1.6.7"),
        ),
    ),

    // -----------------------------------------------------------------------------------------
    // HAS-BLED
    // -----------------------------------------------------------------------------------------
    Score(
        id = "has-bled",
        name = "HAS-BLED",
        purpose = "1-year major bleeding risk in atrial fibrillation; flags modifiable bleeding risk factors.",
        category = ScoreCategory.Cardio,
        keywords = listOf("HAS-BLED", "HASBLED", "bleeding risk", "atrial fibrillation", "AF", "anticoagulation"),
        items = listOf(
            Criterion("htn", "Uncontrolled hypertension", 1.0, hint = "Systolic BP >160 mm Hg"),
            Criterion(
                "renal", "Abnormal renal function", 1.0,
                hint = "Dialysis, kidney transplant or serum creatinine >2.26 mg/dL (≈200 µmol/L)",
            ),
            Criterion(
                "liver", "Abnormal liver function", 1.0,
                hint = "Cirrhosis, bilirubin >2× ULN, or AST/ALT/ALP >3× ULN",
            ),
            Criterion("stroke", "Prior stroke", 1.0, hint = "Haemorrhagic or ischaemic"),
            Criterion("bleeding", "Bleeding history or predisposition", 1.0),
            Criterion("inr", "Labile INR", 1.0, hint = "On a vitamin K antagonist with time in therapeutic range <60%"),
            Criterion("elderly", "Elderly (>65 years)", 1.0, hint = "Pisters 2010: age >65; ACC/AHA 2023 lists age ≥65"),
            Criterion("drugs", "Antiplatelet agents or NSAIDs", 1.0, hint = "Concomitant drugs"),
            Criterion("alcohol", "Excess alcohol", 1.0, hint = "≥8 units per week"),
        ),
        bands = listOf(
            Band(0.0, 0.0, "HAS-BLED 0", "ACC/AHA 2023 (Figure 11 footnote): low bleeding risk.", Level.Low),
            Band(1.0, 2.0, "HAS-BLED 1–2", "ACC/AHA 2023 (Figure 11 footnote): moderate bleeding risk.", Level.Moderate),
            Band(
                3.0, 9.0, "HAS-BLED ≥3",
                "ACC/AHA 2023 (Figure 11 footnote): high bleeding risk. ACC/AHA: bleeding risk scores should not be used in isolation to decide eligibility for anticoagulation, but to identify and modify bleeding risk factors.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "ACC/AHA/ACCP/HRS 2023 (3: No Benefit, B-NR): “In patients who are deemed at high risk for stroke, bleeding risk scores should not be used in isolation to determine eligibility for oral anticoagulation but instead to identify and modify bleeding risk factors and to inform medical decision-making.”",
                listOf(cite("sc-acc-aha-af-2023", "Section 6.1, recommendation 4")),
            ),
            Caveat(
                "ESC 2024 key message: “Do not use bleeding risk scores to decide on starting or withdrawing anticoagulants; instead, address all modifiable bleeding risk factors to improve safety.”",
                listOf(cite("sc-esc-af-2024-key", "Commandment 3"), cite("sc-esc-af-2024")),
            ),
            Caveat(
                "NICE NG196 prefers ORBIT: “Use the ORBIT bleeding risk score because evidence shows that it has a higher accuracy in predicting absolute bleeding risk than other bleeding risk tools.” NICE adds that bleeding risk scores should typically prompt management of modifiable risk factors rather than be a reason for not offering anticoagulation, and that anticoagulation should not be withheld solely because of age or falls risk.",
                listOf(cite("sc-nice-ng196", "Recommendations 1.2.2, 1.6.8 and rationale")),
            ),
            Caveat(
                "Modifiable bleeding risk factors listed by NICE NG196: uncontrolled hypertension, poor INR control on a vitamin K antagonist, concurrent antiplatelets, SSRIs or NSAIDs, harmful alcohol consumption and reversible causes of anaemia.",
                listOf(cite("sc-nice-ng196", "Recommendation 1.2.3")),
            ),
        ),
        citations = listOf(
            cite("sc-pisters-2010", "Derivation"),
            cite("sc-acc-aha-af-2023", "Figure 11 footnote — HAS-BLED definitions and risk categories"),
        ),
    ),

    // -----------------------------------------------------------------------------------------
    // Wells score for DVT (2-level)
    // -----------------------------------------------------------------------------------------
    Score(
        id = "wells-dvt",
        name = "Wells score for DVT",
        purpose = "Two-level clinical probability of lower-limb deep vein thrombosis (likely vs unlikely).",
        category = ScoreCategory.Cardio,
        keywords = listOf("Wells", "DVT", "deep vein thrombosis", "VTE", "venous thromboembolism", "D-dimer", "leg swelling"),
        items = listOf(
            Criterion("cancer", "Active cancer", 1.0, hint = "Treatment ongoing, within 6 months, or palliative"),
            Criterion(
                "paralysis", "Paralysis, paresis or recent leg cast", 1.0,
                hint = "Paralysis, paresis or recent plaster immobilisation of the lower extremities",
            ),
            Criterion(
                "bedridden", "Bedridden ≥3 days or major surgery", 1.0,
                hint = "Recently bedridden for 3 days or more, or major surgery within 12 weeks requiring general or regional anaesthesia",
            ),
            Criterion(
                "tenderness", "Tenderness along deep veins", 1.0,
                hint = "Localised tenderness along the distribution of the deep venous system",
            ),
            Criterion("swollen_leg", "Entire leg swollen", 1.0),
            Criterion("calf", "Calf swelling ≥3 cm", 1.0, hint = "At least 3 cm larger than the asymptomatic side"),
            Criterion("pitting", "Pitting oedema in symptomatic leg", 1.0, hint = "Confined to the symptomatic leg"),
            Criterion("collaterals", "Collateral superficial veins (non-varicose)", 1.0),
            Criterion("prior_dvt", "Previously documented DVT", 1.0),
            Criterion(
                "alternative", "Alternative diagnosis at least as likely", -2.0,
                hint = "An alternative diagnosis is at least as likely as DVT",
            ),
        ),
        bands = listOf(
            Band(
                -2.0, 1.0, "DVT unlikely (≤1)",
                "NICE NG158 (1.1.8–1.1.10): offer a D-dimer test with the result within 4 hours, or interim therapeutic anticoagulation while awaiting it. If positive, offer a proximal leg vein ultrasound (result within 4 hours if possible, otherwise interim anticoagulation and a scan within 24 hours).",
                Level.Low,
            ),
            Band(
                2.0, 9.0, "DVT likely (≥2)",
                "NICE NG158 (1.1.3–1.1.4): offer a proximal leg vein ultrasound with the result within 4 hours if possible, and a D-dimer test if the scan is negative. If the scan cannot be done within 4 hours, offer a D-dimer test, interim therapeutic anticoagulation and a scan with the result within 24 hours.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Wells 2003 (outpatients with suspected lower-limb DVT): DVT can be ruled out in a patient judged clinically unlikely to have DVT who has a negative D-dimer test.",
                listOf(cite("sc-wells-2003", "Conclusions")),
            ),
            Caveat(
                "NICE NG158: with a negative proximal ultrasound but a positive D-dimer, offer a repeat proximal leg vein ultrasound 6 to 8 days later.",
                listOf(cite("sc-nice-ng158", "Recommendation 1.1.6")),
            ),
        ),
        citations = listOf(
            cite("sc-wells-2003", "Clinical model (likely/unlikely)"),
            cite("sc-nice-ng158", "Recommendation 1.1.2, Table 1"),
        ),
    ),

    // -----------------------------------------------------------------------------------------
    // Wells score for PE (2-level)
    // -----------------------------------------------------------------------------------------
    Score(
        id = "wells-pe",
        name = "Wells score for PE",
        purpose = "Two-level clinical probability of pulmonary embolism (likely vs unlikely).",
        category = ScoreCategory.Cardio,
        keywords = listOf("Wells", "PE", "pulmonary embolism", "VTE", "venous thromboembolism", "D-dimer", "CTPA"),
        items = listOf(
            Criterion(
                "dvt_signs", "Clinical signs and symptoms of DVT", 3.0,
                hint = "Minimum of leg swelling and pain with palpation of the deep veins",
            ),
            Criterion(
                "alternative", "Alternative diagnosis less likely than PE", 3.0,
                hint = "An alternative diagnosis is less likely than PE",
            ),
            Criterion("hr", "Heart rate >100/min", 1.5),
            Criterion(
                "immobilisation", "Immobilisation >3 days or recent surgery", 1.5,
                hint = "Immobilisation for more than 3 days or surgery in the previous 4 weeks",
            ),
            Criterion("prior_vte", "Previous DVT or PE", 1.5),
            Criterion("haemoptysis", "Haemoptysis", 1.0),
            Criterion("malignancy", "Malignancy", 1.0, hint = "On treatment, treated in the last 6 months, or palliative"),
        ),
        bands = listOf(
            Band(
                0.0, 4.0, "PE unlikely (≤4)",
                "NICE NG158 (1.1.21): offer a D-dimer test with the result within 4 hours if possible, or interim therapeutic anticoagulation while awaiting it. If positive, manage as PE likely (imaging); if negative, NICE advises considering alternative diagnoses.",
                Level.Low,
            ),
            Band(
                4.5, 12.5, "PE likely (>4)",
                "NICE NG158 (1.1.18): offer a CTPA immediately if possible (V/Q SPECT or planar V/Q if contrast allergy, creatinine clearance <30 ml/min or high irradiation risk). If imaging cannot be done immediately, offer interim therapeutic anticoagulation.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Wells 2000 also derived a 3-level version (low <2, moderate 2–6, high >6). In the 2-level model, PE unlikely (≤4) with a negative SimpliRED D-dimer had a PE rate of 2.2% in the derivation set and 1.7% in the validation set.",
                listOf(cite("sc-wells-2000", "Abstract")),
            ),
            Caveat(
                "NICE NG158: assess history, examine and offer a chest X-ray to exclude other causes; if clinical suspicion of PE is low and other diagnoses are feasible, consider the PERC rule before the Wells score.",
                listOf(cite("sc-nice-ng158", "Recommendations 1.1.15–1.1.16")),
            ),
        ),
        citations = listOf(
            cite("sc-wells-2000", "Clinical prediction rule, ≤4 vs >4"),
            cite("sc-nice-ng158", "Recommendation 1.1.17, Table 2"),
        ),
    ),

    // -----------------------------------------------------------------------------------------
    // PERC rule
    // -----------------------------------------------------------------------------------------
    Score(
        id = "perc",
        name = "PERC rule",
        purpose = "Pulmonary embolism rule-out criteria for patients whose clinical suspicion of PE is already low.",
        category = ScoreCategory.Cardio,
        keywords = listOf("PERC", "pulmonary embolism rule-out criteria", "PE", "pulmonary embolism", "D-dimer"),
        items = listOf(
            Criterion("age", "Age ≥50 years", 1.0),
            Criterion("hr", "Heart rate ≥100/min", 1.0),
            Criterion("sao2", "SaO₂ <95%", 1.0),
            Criterion("leg", "Unilateral leg swelling", 1.0),
            Criterion("haemoptysis", "Haemoptysis", 1.0),
            Criterion(
                "surgery", "Recent surgery or trauma", 1.0,
                hint = "Within 4 weeks, requiring hospitalisation (Kline 2008)",
            ),
            Criterion("prior_vte", "Prior DVT or PE", 1.0),
            Criterion("hormone", "Oestrogen or hormone use", 1.0, hint = "Exogenous oestrogen (e.g. oral contraceptive or HRT)"),
        ),
        bands = listOf(
            Band(
                0.0, 0.0, "PERC negative",
                "Kline 2008: low gestalt suspicion (<15%) plus PERC negative had a 1.0% rate of VTE or death at 45 days. NICE NG158 (1.1.16): PERC helps decide whether any further investigation for PE is needed when suspicion is low.",
                Level.Low,
            ),
            Band(
                1.0, 8.0, "PERC positive",
                "Kline 2004/2008: the rule is met only when all 8 criteria are absent. NICE NG158 (1.1.17): if PE is suspected, use the 2-level PE Wells score.",
                Level.Moderate,
            ),
        ),
        caveats = listOf(
            Caveat(
                "PERC was validated only in patients whose clinical suspicion of PE was already low: Kline 2008 required a gestalt pretest probability <15%; NICE NG158 defines low suspicion from the overall clinical impression (history, examination and initial tests such as ECG or chest X-ray) with other diagnoses feasible.",
                listOf(cite("sc-kline-2008", "Methods"), cite("sc-nice-ng158", "Recommendation 1.1.16 and rationale")),
            ),
            Caveat(
                "NICE NG158: be aware that the PERC rule has not been validated in people with COVID-19.",
                listOf(cite("sc-nice-ng158", "Recommendation 1.1.16")),
            ),
        ),
        citations = listOf(
            cite("sc-kline-2004", "Derivation"),
            cite("sc-kline-2008", "Prospective validation; criteria definitions"),
            cite("sc-nice-ng158", "Recommendation 1.1.16"),
        ),
        showTotal = false,
    ),

    // -----------------------------------------------------------------------------------------
    // HEART score
    // -----------------------------------------------------------------------------------------
    Score(
        id = "heart",
        name = "HEART score",
        purpose = "Short-term major adverse cardiac event risk in ED patients with chest pain.",
        category = ScoreCategory.Cardio,
        keywords = listOf("HEART", "chest pain", "acute coronary syndrome", "ACS", "MACE", "troponin", "emergency department"),
        items = listOf(
            Choice(
                "history", "History",
                listOf(
                    Option("Slightly or non-suspicious", 0.0),
                    Option("Moderately suspicious", 1.0),
                    Option("Highly suspicious", 2.0),
                ),
                hint = "Pain pattern, onset and duration, relation to exercise, stress or cold, localisation, associated symptoms, response to sublingual nitrates",
            ),
            Choice(
                "ecg", "ECG",
                listOf(
                    Option("Normal", 0.0),
                    Option("Nonspecific repolarisation disturbance", 1.0),
                    Option("Significant ST depression", 2.0),
                ),
            ),
            Choice(
                "age", "Age",
                listOf(
                    Option("≤45 years", 0.0),
                    Option(">45 to <65 years", 1.0),
                    Option("≥65 years", 2.0),
                ),
            ),
            Choice(
                "risk", "Risk factors",
                listOf(
                    Option("No risk factors known", 0.0),
                    Option("1 or 2 risk factors", 1.0),
                    Option("≥3 risk factors, or history of atherosclerotic disease", 2.0),
                ),
                hint = "Treated diabetes, current or recent smoker, diagnosed/treated hypertension, hypercholesterolaemia, family history of CAD, obesity (BMI >30). Atherosclerotic disease: coronary revascularisation, MI, stroke or PAD.",
            ),
            Choice(
                "troponin", "Troponin",
                listOf(
                    Option("≤ normal limit", 0.0),
                    Option(">1 to <3× normal limit", 1.0),
                    Option("≥3× normal limit", 2.0),
                ),
            ),
        ),
        bands = listOf(
            Band(
                0.0, 3.0, "HEART 0–3",
                "Backus 2013 (n=2440): MACE within 6 weeks 1.7%. Six 2008 derivation (2.5%): ‘supports an immediate discharge’.",
                Level.Low,
            ),
            Band(
                4.0, 6.0, "HEART 4–6",
                "Backus 2013: MACE within 6 weeks 16.6%. Six 2008 derivation (20.3%): ‘implies admission for clinical observation’.",
                Level.Moderate,
            ),
            Band(
                7.0, 10.0, "HEART 7–10",
                "Backus 2013: MACE within 6 weeks 50.1%. Six 2008 derivation (72.7%): ‘supports early invasive strategies’.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Backus 2013 scored HEART as soon as the first ECG and laboratory results were available, in unselected ED chest pain patients. The 2008 derivation table used troponin >2× / 1–2× the normal limit; the cut-offs here (≥3× / >1 to <3×) are those of the authors’ HEART impact-trial protocol.",
                listOf(cite("sc-backus-2013", "Methods"), cite("sc-six-2008", "Table 1"), cite("sc-poldervaart-2013", "Table 1")),
            ),
            Caveat(
                "2021 AHA/ACC chest pain guideline: clinical decision pathways should be used routinely in the ED and outpatient settings; in acute chest pain at low risk (30-day risk of death or MACE <1%), no additional urgent cardiac testing may be needed.",
                listOf(cite("sc-aha-chestpain-2021", "Acute chest pain pathways")),
            ),
        ),
        citations = listOf(
            cite("sc-six-2008", "Derivation"),
            cite("sc-backus-2013", "Prospective validation, MACE by category"),
            cite("sc-poldervaart-2013", "Table 1 — HEART elements"),
        ),
    ),

    // -----------------------------------------------------------------------------------------
    // TIMI risk score for UA/NSTEMI
    // -----------------------------------------------------------------------------------------
    Score(
        id = "timi-ua-nstemi",
        name = "TIMI risk score (UA/NSTEMI)",
        purpose = "14-day risk of death, MI or urgent revascularisation in unstable angina/NSTEMI.",
        category = ScoreCategory.Cardio,
        keywords = listOf("TIMI", "unstable angina", "NSTEMI", "NSTE-ACS", "acute coronary syndrome", "ACS"),
        items = listOf(
            Criterion("age", "Age ≥65 years", 1.0),
            Criterion(
                "risk_factors", "≥3 risk factors for CAD", 1.0,
                hint = "Family history of CAD, hypertension, hypercholesterolaemia, diabetes, current smoker",
            ),
            Criterion("known_cad", "Known CAD (stenosis ≥50%)", 1.0, hint = "Prior coronary stenosis of 50% or more"),
            Criterion("aspirin", "Aspirin use in past 7 days", 1.0),
            Criterion("angina", "Severe angina (≥2 episodes in 24 h)", 1.0, hint = "At least 2 anginal events in the prior 24 hours"),
            Criterion("st", "ST deviation ≥0.5 mm", 1.0, hint = "On the presenting ECG"),
            Criterion("markers", "Elevated cardiac markers", 1.0, hint = "Elevated serum cardiac markers (CK-MB or troponin)"),
        ),
        bands = listOf(
            Band(
                0.0, 1.0, "TIMI 0–1",
                "Antman 2000 (TIMI 11B derivation cohort): 4.7% had death, new or recurrent MI, or severe recurrent ischaemia requiring urgent revascularisation by day 14.",
                Level.Low,
            ),
            Band(2.0, 2.0, "TIMI 2", "Antman 2000: 14-day rate of death, MI or urgent revascularisation 8.3%.", Level.Low),
            Band(3.0, 3.0, "TIMI 3", "Antman 2000: 14-day rate of death, MI or urgent revascularisation 13.2%.", Level.Moderate),
            Band(4.0, 4.0, "TIMI 4", "Antman 2000: 14-day rate of death, MI or urgent revascularisation 19.9%.", Level.Moderate),
            Band(5.0, 5.0, "TIMI 5", "Antman 2000: 14-day rate of death, MI or urgent revascularisation 26.2%.", Level.High),
            Band(6.0, 7.0, "TIMI 6–7", "Antman 2000: 14-day rate of death, MI or urgent revascularisation 40.9%.", Level.High),
        ),
        caveats = listOf(
            Caveat(
                "Derived and validated in trial patients with established UA/NSTEMI (TIMI 11B, ESSENCE), not in undifferentiated ED chest pain.",
                listOf(cite("sc-antman-2000", "Design, setting and patients")),
            ),
            Caveat(
                "NICE NG185: once UA/NSTEMI is diagnosed, formally assess risk “using an established risk scoring system that predicts 6-month mortality (for example, Global Registry of Acute Cardiac Events [GRACE])”. TIMI predicts a 14-day composite outcome.",
                listOf(cite("sc-nice-ng185", "Recommendation 1.2.7"), cite("sc-antman-2000", "Main outcome measures")),
            ),
        ),
        citations = listOf(
            cite("sc-antman-2000", "Predictor variables and 14-day event rates"),
            cite("sc-nice-ng185", "Recommendation 1.2.7"),
        ),
    ),
)
