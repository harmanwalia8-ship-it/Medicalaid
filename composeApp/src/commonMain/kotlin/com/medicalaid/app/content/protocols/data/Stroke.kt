package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.ProtocolNote
import com.medicalaid.app.content.protocols.ProtocolTool
import com.medicalaid.app.content.protocols.Step

/*
 * Acute stroke pathway. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them.
 * - Every point is attributed ("AHA/ASA 2026: …", "NICE: …", "MoHFW 2022: …", "ICMR STW: …").
 * - AHA/ASA locators are section + recommendation numbers; NICE locators are recommendation numbers.
 */

internal val strokeProtocolSources = listOf(
    Source(
        "st-aha-target-stroke", Authority.AHA, "Target: Stroke Phase III (quality programme)",
        "Target: Stroke — door-to-needle and door-to-device goals", "2019",
        "https://www.heart.org/en/professional/quality-improvement/target-stroke/introducing-target-stroke-phase-iii",
        status = "Quality-improvement goals, not guideline recommendations",
    ),
    Source(
        "st-aha-ais-2026", Authority.AHA, "Guideline (Stroke 2026;57:e316–e436)",
        "2026 Guideline for the Early Management of Patients With Acute Ischemic Stroke", "2026",
        "https://doi.org/10.1161/STR.0000000000000513",
        status = "Replaces the 2018 guideline and its 2019 update; correction published (Stroke 2026;57:e461–e467)",
    ),
    Source(
        "st-aha-ich-2022", Authority.AHA, "Guideline (Stroke 2022;53:e282–e361)",
        "2022 Guideline for the Management of Patients With Spontaneous Intracerebral Hemorrhage", "2022",
        "https://doi.org/10.1161/STR.0000000000000407",
    ),
    Source(
        "st-aha-secondary-2021", Authority.AHA, "Guideline (Stroke 2021;52:e364–e467)",
        "2021 Guideline for the Prevention of Stroke in Patients With Stroke and Transient Ischemic Attack", "2021",
        "https://doi.org/10.1161/STR.0000000000000375",
    ),
    Source(
        "st-eso-ivt-2021", Authority.ESO, "Guideline (Eur Stroke J 2021;6(1):I–LXII)",
        "European Stroke Organisation (ESO) guidelines on intravenous thrombolysis for acute ischaemic stroke", "2021",
        "https://doi.org/10.1177/2396987321989865",
        status = "Tenecteplase recommendations updated by the ESO 2023 expedited recommendation",
    ),
    Source(
        "st-eso-tnk-2023", Authority.ESO, "Expedited recommendation (Eur Stroke J 2023;8(1):8–54)",
        "European Stroke Organisation (ESO) expedited recommendation on tenecteplase for acute ischaemic stroke", "2023",
        "https://doi.org/10.1177/23969873221150022",
    ),
    Source(
        "st-eso-bp-2025", Authority.ESO, "Guideline update (Eur Stroke J 2026;11(5):aakag004)",
        "2025 update to European Stroke Organisation (ESO) guideline on blood pressure management in acute ischaemic stroke and intracerebral haemorrhage",
        "2026",
        "https://doi.org/10.1093/esj/aakag004",
    ),
    Source(
        "st-nice-ng128", Authority.NICE, "Guideline NG128",
        "Stroke and transient ischaemic attack in over 16s: diagnosis and initial management", "2019",
        "https://www.nice.org.uk/guidance/ng128",
        status = "Last updated April 2022; minor changes March 2025",
    ),
    Source(
        "st-mohfw-stroke-2022", Authority.MoHFW, "NP-NCD Guidelines (Dte.GHS)",
        "Guidelines for Prevention and Management of Stroke", "2022",
        "https://www.nhmmizoram.org/upload/Fin%20-%20Stroke%20guidelines_0-1.pdf",
        status = "PDF hosted by NHM Mizoram",
    ),
    Source(
        "st-icmr-stw-stroke-2019", Authority.ICMR, "Standard Treatment Workflow",
        "Management of Stroke (ICD-10 I63, I64)", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952342_neurology_stroke.pdf",
    ),
    Source(
        "st-icmr-stw-stroke-imaging-2024", Authority.ICMR, "Standard Treatment Workflow",
        "Image Guided Management of Stroke (ICD-10 I63.9)", "2024",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726644021_5_image_guided_management_of_stroke.pdf",
    ),
)

private const val TARGET = "st-aha-target-stroke"
private const val AHA = "st-aha-ais-2026"
private const val ICH = "st-aha-ich-2022"
private const val SP = "st-aha-secondary-2021"
private const val ESO_IVT = "st-eso-ivt-2021"
private const val ESO_TNK = "st-eso-tnk-2023"
private const val ESO_BP = "st-eso-bp-2025"
private const val NICE = "st-nice-ng128"
private const val MOHFW = "st-mohfw-stroke-2022"
private const val ICMR = "st-icmr-stw-stroke-2019"
private const val ICMR_IMG = "st-icmr-stw-stroke-imaging-2024"

internal val strokeProtocols = listOf(
    Protocol(
        id = "acute-stroke",
        title = "Acute stroke",
        summary = "Recognition, imaging, IV thrombolysis, thrombectomy, early care, ICH and TIA — " +
            "each step attributed to AHA/ASA, ESO, NICE, MoHFW or ICMR.",
        keywords = listOf(
            "stroke", "CVA", "brain attack", "ischaemic stroke", "ischemic stroke", "thrombolysis", "IVT",
            "alteplase", "tenecteplase", "tPA", "rtPA", "TNK", "thrombectomy", "EVT", "large vessel occlusion",
            "LVO", "TIA", "transient ischaemic attack", "intracerebral haemorrhage", "ICH", "BE-FAST", "FAST",
            "hemiplegia", "paralysis", "last known well",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise and time onset",
                timing = "At first contact",
                points = listOf(
                    "ICMR STW: BE-FAST warning signs — Balance, Eyes, Face, Arm, Speech, Time; also sudden new headache or loss of consciousness, or giddiness, vomiting and imbalance.",
                    "NICE: use a validated tool such as FAST outside hospital. MoHFW 2022: Hindi mnemonic 'BACHAV' — Baazu, Aawaz, Chehra, Vakt.",
                    "ICMR STW: determine time of symptom onset or last known normal; obtain family contact details, preferably a mobile number.",
                    "NICE and ICMR STW: check blood glucose — exclude hypoglycaemia as the cause of sudden neurological symptoms.",
                    "ICMR STW: rapid transfer to the nearest hospital with CT or a stroke centre able to thrombolyse. AHA/ASA 2026: pre-notify the receiving hospital.",
                ),
                tool = ProtocolTool.OnsetClock,
                citations = listOf(
                    cite(ICMR, "Warning signs (BEFAST); Preliminary management"),
                    cite(NICE, "Rec 1.1.1–1.1.2"),
                    cite(MOHFW, "Ch. 4 Early identification of stroke/TIA"),
                    cite(AHA, "§2.3 Rec 2–3"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Immediate assessment",
                timing = "Brain imaging as rapidly as possible, e.g. within 25 min (AHA/ASA 2026)",
                points = listOf(
                    "ICMR STW: assess ABCs, start cardiac monitoring, establish IV access, keep SpO₂ >94%. NICE: give oxygen only if saturation is below 95%.",
                    "AHA/ASA 2026: NIHSS is the preferred scale for deficits at baseline and after reperfusion. NICE: ROSIER to establish the diagnosis in the emergency department.",
                    "AHA/ASA 2026: measure blood glucose before IVT and urgently treat severe hypo- or hyperglycaemia, which may mimic stroke.",
                    "AHA/ASA 2026: baseline ECG and troponin are recommended but should not delay IVT or thrombectomy.",
                    "AHA/ASA 2026: IVT need not wait for haematology or coagulation results if there is no reason to suspect an abnormal result.",
                    "AHA/ASA 2026: protocols should allow emergent brain imaging as rapidly as possible (e.g. within 25 minutes).",
                ),
                scoreIds = listOf("nihss", "rosier"),
                citations = listOf(
                    cite(ICMR, "Preliminary management"),
                    cite(NICE, "Rec 1.1.3, 1.5.1"),
                    cite(AHA, "§3.1 Rec 1; §3.2 Rec 2; §3.3 Rec 1–2; §4.1 Rec 2; §4.6.1 Rec 5, 10"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Imaging",
                timing = "Immediately if reperfusion is possible (NICE)",
                points = listOf(
                    "AHA/ASA 2026: emergent non-contrast CT or MRI to exclude haemorrhage and assess ischaemic burden (e.g. ASPECTS) before any reperfusion treatment.",
                    "AHA/ASA 2026: within 4.5 h, start IVT without waiting for CTA/MRA or CT/MR perfusion.",
                    "AHA/ASA 2026: suspected LVO within 24 h — CT/CTA or MRI/MRA of cervical and intracranial vessels; do not delay CTA for serum creatinine.",
                    "AHA/ASA 2026: at 6–24 h, CT perfusion or MRI (DWI-FLAIR or DWI-PWI) can help thrombectomy selection if immediately available.",
                    "NICE: CT angiography after non-enhanced CT if thrombectomy might be indicated; add CT perfusion (or MR equivalent) beyond 6 h.",
                    "ESO 2021: wake-up stroke with MRI DWI-FLAIR mismatch and no planned thrombectomy — alteplase recommended. AHA/ASA 2026: such mismatch can guide selection.",
                ),
                citations = listOf(
                    cite(AHA, "§3.2 Rec 1, 3, 6, 8, 9; §4.6.1 Rec 2"),
                    cite(NICE, "Rec 1.3.2"),
                    cite(ESO_IVT, "PICO 4.1"),
                    cite(ICMR_IMG, "Stroke patient flow (NCCT + CTA)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "IV thrombolysis",
                timing = "Within 4.5 h of onset or last known well",
                points = listOf(
                    "AHA Target: Stroke (quality goal, not a guideline recommendation): door-to-needle within 60 minutes in ≥85% of patients treated with IV thrombolytics.",
                    "AHA/ASA 2026: within 4.5 h of onset or last known well — tenecteplase 0.25 mg/kg (max 25 mg) or alteplase 0.9 mg/kg (max 90 mg).",
                    "AHA/ASA 2026: alteplase over 60 min with 10% of the dose as a bolus over 1 min; tenecteplase as a single bolus. Tenecteplase 0.4 mg/kg not recommended.",
                    "ESO 2023: tenecteplase 0.25 mg/kg is a safe, effective alternative to alteplase 0.9 mg/kg; recommended over alteplase for LVO within 4.5 h.",
                    "MoHFW 2022: tenecteplase 0.2–0.25 mg/kg (max 20 mg) or alteplase at 0–3 h; alteplase only at 3–4.5 h. Cites CDSCO approval of 0.2 mg/kg within 3 h.",
                    "AHA/ASA 2026: lower BP to <185/110 mm Hg before IVT (NICE: 185/110 or lower). AHA/ASA 2026: IVT not recommended for mild non-disabling deficits.",
                    "AHA/ASA 2026: 4.5–9 h from last known well, or waking within 9 h of mid-sleep, with salvageable penumbra on automated perfusion imaging — IVT may be reasonable.",
                ),
                checklistTitle = "Key exclusions",
                checklist = listOf(
                    "Acute intracranial haemorrhage on CT",
                    "Extensive clear hypodensity on CT explaining the symptoms",
                    "Moderate–severe traumatic brain injury within 14 days",
                    "Intracranial or spinal surgery within 14 days",
                    "Acute spinal cord injury within 3 months",
                    "Intra-axial intracranial neoplasm",
                    "Symptoms consistent with infective endocarditis",
                    "Platelets <100,000/mm³, INR >1.7, aPTT >40 s or PT >15 s",
                    "Known or suspected aortic arch dissection",
                    "Amyloid immunotherapy or ARIA",
                    "BP not lowered to <185/110 mm Hg",
                    "Mild non-disabling deficit",
                ),
                tool = ProtocolTool.ThrombolysisDose,
                citations = listOf(
                    cite(TARGET, "Phase III goals"),
                    cite(AHA, "§4.6.2 Rec 1–2; Table 7"),
                    cite(AHA, "Table 8 (absolute contraindications); §4.3 Rec 5; §4.6.1 Rec 8; §4.6.3 Rec 2"),
                    cite(ESO_TNK, "Recommendations (tenecteplase 0.25 mg/kg; 0.40 mg/kg; LVO)"),
                    cite(MOHFW, "Annexure VI"),
                    cite(NICE, "Rec 1.4.1, 1.5.10"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Mechanical thrombectomy",
                timing = "Up to 24 h from last known well in selected patients",
                points = listOf(
                    "AHA Target: Stroke (quality goal): door-to-device within 90 min for direct arrivals and 60 min for transfers, in ≥50% of patients treated.",
                    "AHA/ASA 2026: ICA or M1 occlusion within 6 h, NIHSS ≥6, pre-stroke mRS 0–1, ASPECTS 3–10 — thrombectomy recommended.",
                    "AHA/ASA 2026: ICA or M1 at 6–24 h, NIHSS ≥6, mRS 0–1 — recommended with ASPECTS ≥6, or ASPECTS 3–5 if age <80 without significant mass effect.",
                    "AHA/ASA 2026: basilar occlusion with mRS 0–1, NIHSS ≥10 and PC-ASPECTS ≥6 — thrombectomy within 24 h recommended.",
                    "AHA/ASA 2026: if eligible for both, give IVT without pausing to observe response. Not recommended for non-dominant M2, distal MCA, ACA or PCA occlusions.",
                    "NICE: within 6 h together with IVT; 6–24 h if imaging shows salvageable tissue; select pre-stroke mRS <3 and NIHSS >5.",
                    "MoHFW 2022: ICA or proximal MCA occlusion with disabling deficit — refer where arterial puncture can begin within 24 h. ICMR STW 2024: DAWN/DEFUSE criteria beyond 6 h.",
                ),
                citations = listOf(
                    cite(TARGET, "Phase III goals"),
                    cite(AHA, "§4.7.1 Rec 1–2; §4.7.2 Rec 1–3, 8; §4.7.3 Rec 1"),
                    cite(NICE, "Rec 1.4.5–1.4.8"),
                    cite(MOHFW, "§5.4(a)"),
                    cite(ICMR_IMG, "Stroke patient flow (4.5–24 hours)"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "After thrombolysis",
                timing = "First 24 h after IVT",
                points = listOf(
                    "AHA/ASA 2026 and ESO 2025: BP <180/105 mm Hg for the first 24 h after IVT. MoHFW 2022: goal <180/105 mm Hg during and after IVT.",
                    "AHA/ASA 2026: BP and neurological checks every 15 min for 2 h, every 30 min for 6 h, then hourly until 24 h.",
                    "AHA/ASA 2026: severe headache, acute hypertension, nausea, vomiting or worsening — stop alteplase infusion and obtain emergency head CT.",
                    "AHA/ASA 2026: follow-up CT or MRI at 24 h before starting antiplatelets or anticoagulants; no IV aspirin within 90 min of starting IVT.",
                    "MoHFW 2022: delay aspirin until 24 h after thrombolysis; routine non-contrast CT at 24 h.",
                    "AHA/ASA 2026: intensive SBP target <140 mm Hg after IVT does not improve outcome; after successful thrombectomy, <140 mm Hg for 72 h is harmful.",
                ),
                citations = listOf(
                    cite(AHA, "§4.3 Rec 7–8, 10; Table 7; §4.8 Rec 2, 17"),
                    cite(ESO_BP, "Abstract — reperfusion therapy"),
                    cite(MOHFW, "§5.4(a); §5.7(c); Annexure VI"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Not thrombolysed: antiplatelets",
                timing = "After haemorrhage is excluded",
                points = listOf(
                    "AHA/ASA 2026: aspirin within 48 h of onset. NICE: aspirin 300 mg within 24 h (rectal or enteral if dysphagic), daily until 2 weeks.",
                    "AHA/ASA 2026: minor (NIHSS ≤3) non-cardioembolic stroke or high-risk TIA (ABCD2 ≥4) — aspirin plus clopidogrel with loading dose, within 24 h, for 21 days.",
                    "AHA/ASA 2026 (Table 9, CHANCE): clopidogrel 300 mg load then 75 mg/day plus aspirin 75 mg for 21 days, then clopidogrel alone.",
                    "AHA/ASA 2026: NIHSS ≤5 at 24–72 h, or NIHSS 4–5 within 24 h, with presumed atherosclerotic cause — DAPT for 21 days is reasonable.",
                    "MoHFW 2022: at least 300 mg aspirin plus 300 mg clopidogrel once haemorrhage is excluded; DAPT for 3 weeks in non-cardioembolic stroke.",
                    "AHA/ASA 2026: aspirin is not a substitute for IVT or thrombectomy in eligible patients.",
                ),
                citations = listOf(
                    cite(AHA, "§4.8 Rec 1, 12, 14, 16; Table 9"),
                    cite(NICE, "Rec 1.4.9"),
                    cite(MOHFW, "§5.4(a)"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "General care",
                timing = "From admission",
                points = listOf(
                    "AHA/ASA 2026: treat glucose <60 mg/dL; persistent hyperglycaemia to 140–180 mg/dL (MoHFW 2022: same range); IV insulin to 80–130 mg/dL not recommended. NICE: 4–11 mmol/L.",
                    "AHA/ASA 2026: target normothermia in hyperthermia and treat its source; no induced hypothermia. MoHFW 2022: fever >99.5 °F — paracetamol and search for infection.",
                    "AHA/ASA 2026, NICE and MoHFW 2022: swallow screen before any oral food, fluid or medication.",
                    "AHA/ASA 2026: intermittent pneumatic compression if immobile; prophylactic heparin reasonable; elastic stockings harmful. MoHFW 2022: UFH 5000 units SC twice daily or LMWH.",
                    "AHA/ASA 2026: high-dose, very early mobilisation within 24 h not recommended. NICE: no high-intensity mobilisation in the first 24 h if help is needed.",
                    "AHA/ASA 2026: no reperfusion and BP <220/120 — starting antihypertensives within 48–72 h does not prevent death or dependency. MoHFW 2022: withhold unless >220/120 or MAP >120.",
                ),
                citations = listOf(
                    cite(AHA, "§4.3 Rec 3–4; §4.4 Rec 1–3; §4.5 Rec 1–3; §5.2 Rec 1; §5.4 Rec 1–5; §5.7 Rec 3"),
                    cite(NICE, "Rec 1.5.2, 1.5.9, 1.6.1, 1.7.3"),
                    cite(MOHFW, "§5.7(b)–(d), (g)"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Intracerebral haemorrhage",
                timing = "BP treatment within 2 h of onset, target within 1 h (AHA/ASA 2022)",
                points = listOf(
                    "AHA/ASA 2022: mild–moderate ICH with SBP 150–220 mm Hg — lowering to a target of 140 mm Hg (range 130–150) is safe and may be reasonable.",
                    "AHA/ASA 2022: titrate smoothly, avoiding peaks and variability; lowering SBP to <130 mm Hg is potentially harmful.",
                    "NICE: within 6 h, SBP 150–220 — aim ≤140 mm Hg, fall ≤60 mm Hg in 1 h. Not if GCS <6, structural cause, early surgery or massive haematoma.",
                    "MoHFW 2022: target SBP 140–150 mm Hg for at least 7 days. ESO 2025: early SBP <140 mm Hg in small-to-moderate haematomas (expert consensus).",
                    "AHA/ASA 2022: stop the anticoagulant and reverse as soon as possible. Warfarin with INR ≥2.0 — 4-factor PCC over FFP, then IV vitamin K.",
                    "AHA/ASA 2022: factor Xa inhibitor — andexanet alfa reasonable, or 4F-PCC/aPCC may be considered; dabigatran — idarucizumab; unfractionated heparin — protamine.",
                ),
                citations = listOf(
                    cite(ICH, "§5.1 Rec 1–3, 5"),
                    cite(ICH, "§5.2.1 Rec 1–3, 5–7, 11"),
                    cite(NICE, "Rec 1.5.4–1.5.7"),
                    cite(MOHFW, "§5.7(c)(ii)"),
                    cite(ESO_BP, "Abstract — acute ICH"),
                ),
            ),
            // 10 -----------------------------------------------------------------------------------
            Step(
                title = "Transient ischaemic attack",
                timing = "Specialist assessment within 24 h (NICE)",
                points = listOf(
                    "NICE: suspected TIA — aspirin 300 mg daily started immediately unless contraindicated; refer for specialist assessment within 24 h of symptom onset.",
                    "NICE: do not use ABCD2 or similar scores to assess risk or decide urgency of referral.",
                    "AHA/ASA 2026: high-risk TIA (ABCD2 ≥4) — aspirin plus clopidogrel with loading dose within 24 h for 21 days, then single antiplatelet.",
                    "MoHFW 2022: aspirin 150 mg daily plus clopidogrel 300 mg load then 75 mg daily for 3 weeks, then aspirin 75 mg or clopidogrel 75 mg daily.",
                    "MoHFW 2022: crescendo TIA (two or more in a week) — assess within 24 h; presenting over a week after symptoms — specialist review within one week.",
                    "NICE: no CT unless an alternative diagnosis is suspected; consider same-day MRI after specialist assessment.",
                ),
                citations = listOf(
                    cite(NICE, "Rec 1.1.4–1.1.7, 1.2.1–1.2.2"),
                    cite(AHA, "§4.8 Rec 12"),
                    cite(MOHFW, "Ch. 4; §6.5(a)"),
                ),
            ),
            // 11 -----------------------------------------------------------------------------------
            Step(
                title = "Secondary prevention essentials",
                points = listOf(
                    "AHA/ASA 2021: office BP goal <130/80 mm Hg for most patients after stroke or TIA. MoHFW 2022: 130/80 mm Hg after the acute period.",
                    "AHA/ASA 2021: atorvastatin 80 mg if LDL-C >100 mg/dL (no CHD or major cardiac embolic source); atherosclerotic disease — LDL-C goal <70 mg/dL, statin ± ezetimibe.",
                    "NICE: do not start a statin immediately in acute stroke; continue it if already taking one.",
                    "AHA/ASA 2021: AF — oral anticoagulation; DOAC preferred over warfarin unless moderate–severe mitral stenosis or a mechanical heart valve.",
                    "AHA/ASA 2021: in AF, start 2–14 days if low haemorrhagic-conversion risk, beyond 14 days if high. AHA/ASA 2026: early start reasonable in selected milder strokes.",
                    "MoHFW 2022: anticoagulate within 14 days, not before 7–14 days after disabling stroke. NICE: disabling stroke with AF — aspirin 300 mg for 2 weeks first.",
                ),
                citations = listOf(
                    cite(SP, "§4.2 Rec 2; §4.3.1 Rec 1–2; §5.4.1 Rec 1, 3, 6, 9"),
                    cite(AHA, "§4.9 Rec 1"),
                    cite(NICE, "Rec 1.4.17, 1.4.21–1.4.22"),
                    cite(MOHFW, "§6.5(b), (c), (h)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "AHA/ASA published a correction to its 2026 guideline (Stroke 2026;57:e461–e467) that has not yet been reviewed here — check the current guideline text.",
                listOf(cite(AHA, "Correction, July 2026")),
            ),
            ProtocolNote(
                "Tenecteplase differs: AHA/ASA 2026 and ESO 2023 — 0.25 mg/kg (max 25 mg) within 4.5 h. MoHFW 2022 — 0.2–0.25 mg/kg (max 20 mg) at 0–3 h only, alteplase at 3–4.5 h.",
                listOf(cite(AHA, "§4.6.2 Rec 1; Table 7"), cite(ESO_TNK, "Recommendations"), cite(MOHFW, "Annexure VI")),
            ),
            ProtocolNote(
                "MoHFW 2022 adds criteria for alteplase at 3–4.5 h: age <80; not both diabetes and previous stroke; not on anticoagulation; NIHSS <25.",
                listOf(cite(MOHFW, "Annexure VI")),
            ),
            ProtocolNote(
                "ICMR STW 2024 states BP maintained below 180/110 mm Hg with alteplase; AHA/ASA 2026, ESO 2025 and MoHFW 2022 give <185/110 before and <180/105 after IVT.",
                listOf(
                    cite(ICMR_IMG, "Management — stroke onset <4.5 h"),
                    cite(AHA, "§4.3 Rec 5, 7"),
                    cite(ESO_BP, "Abstract — reperfusion therapy"),
                    cite(MOHFW, "§5.7(c)(i)"),
                ),
            ),
            ProtocolNote(
                "Early antiplatelets differ: MoHFW 2022 gives aspirin plus clopidogrel after any ischaemic stroke; AHA/ASA 2026 limits DAPT to minor stroke or high-risk TIA; NICE uses aspirin 300 mg alone.",
                listOf(cite(MOHFW, "§5.4(a)"), cite(AHA, "§4.8 Rec 1, 12, 14"), cite(NICE, "Rec 1.4.9")),
            ),
            ProtocolNote(
                "ICH BP targets differ: AHA/ASA 2022 — 140 mm Hg (130–150), avoid <130; NICE — ≤140 mm Hg with ≤60 mm Hg fall in 1 h; MoHFW 2022 — 140–150 mm Hg for ≥7 days.",
                listOf(cite(ICH, "§5.1 Rec 3, 5"), cite(NICE, "Rec 1.5.6"), cite(MOHFW, "§5.7(c)(ii)")),
            ),
            ProtocolNote(
                "Prior ischaemic stroke within 3 months, prior ICH and DOAC within 48 h are relative contraindications in AHA/ASA 2026 Table 8; MoHFW 2022 lists stroke within 3 months as a contraindication.",
                listOf(cite(AHA, "Table 8 (relative contraindications)"), cite(MOHFW, "Annexure VI")),
            ),
            ProtocolNote(
                "Availability: ICMR STW offers IVT or endovascular treatment 'according to eligibility and availability'; MoHFW 2022 advises referral to a tertiary centre for thrombectomy.",
                listOf(cite(ICMR, "Management"), cite(MOHFW, "§5.4(a)")),
            ),
            ProtocolNote(
                "Scope: follows the 2026 AHA/ASA acute ischaemic stroke guideline, which replaces the 2018 guideline and its 2019 update.",
                listOf(cite(AHA, "Abstract (Aim)")),
            ),
        ),
        citations = listOf(
            cite(AHA), cite(ICH), cite(SP), cite(ESO_IVT), cite(ESO_TNK), cite(ESO_BP),
            cite(NICE), cite(MOHFW), cite(ICMR), cite(ICMR_IMG),
        ),
    ),
)
