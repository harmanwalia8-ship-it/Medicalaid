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
 * Adult cardiac arrest and peri-arrest arrhythmias. Rules:
 * - Doses, energies, thresholds and time windows exactly as the cited document states them
 *   (re-checked against the full texts, algorithm figures and tables, 21 November 2026).
 * - Every point is attributed ("AHA 2025: …", "ERC 2025: …", "ERC–ESICM 2025: …", "IRC 2017: …", "ICMR STW: …").
 * - AHA 2025 locators are section name + recommendation number (the 2025 parts are not section-numbered) or figure/table;
 *   ERC 2025 locators are the section headings of the summary/guideline text, figure or table.
 * - India: the Indian Resuscitation Council (IRC, Indian Society of Anaesthesiologists) CCLS guideline (2017) is the latest
 *   IRC adult in-hospital guideline found. ICMR has no adult cardiac-arrest or adult tachyarrhythmia STW (the ICMR
 *   "Tachyarrhythmia" STW is paediatric); the ICMR atrial fibrillation and bradyarrhythmia STWs are cited.
 * - Existing sources reused by id: hc-icmr-stw-af-2026 (HeartConditions.kt), tx-oleander-icmr-stw-bradyarrhythmia-2025
 *   (Oleander.kt, the general ICMR bradyarrhythmia STW) and em-resus-aha-special-2025 (DrugTox.kt, AHA 2025 Part 10).
 * - AHA 2025 Part 11 (post-cardiac arrest care, Circulation 2025;152(suppl 2):S673–S718) could not be retrieved for
 *   verification and is not used; post-ROSC targets are ERC–ESICM 2025 and IRC 2017 only.
 * - Corrigenda to ERC ALS (Resuscitation 2026;229:111313), ERC Special Circumstances (2026;228:111314) and ERC–ESICM
 *   Post-resuscitation Care (2026;228:111315) exist; their text could not be retrieved and has not been reviewed.
 */

internal val resuscitationSources: List<Source> = listOf(
    Source(
        "em-resus-aha-als-2025", Authority.AHA, "AHA guideline — Circulation 2025;152(16 Suppl 2):S538–S577",
        "Part 9: Adult Advanced Life Support: 2025 American Heart Association Guidelines for Cardiopulmonary Resuscitation and Emergency Cardiovascular Care",
        "2025",
        "https://doi.org/10.1161/CIR.0000000000001376",
        status = "Published 21 October 2025; includes tachyarrhythmia, cardioversion and bradycardia algorithms (Figs 6–8) and Table 2",
    ),
    Source(
        "em-resus-aha-bls-2025", Authority.AHA, "AHA guideline — Circulation 2025;152(16 Suppl 2):S448–S478",
        "Part 7: Adult Basic Life Support: 2025 American Heart Association Guidelines for Cardiopulmonary Resuscitation and Emergency Cardiovascular Care",
        "2025",
        "https://doi.org/10.1161/CIR.0000000000001369",
    ),
    // "em-resus-aha-special-2025" (AHA 2025 Part 10) is defined in DrugTox.kt, which is released; cited here by id.
    Source(
        "em-resus-erc-als-2025", Authority.ERC, "ERC guideline — Resuscitation 2025;215(Suppl 1):110769",
        "European Resuscitation Council Guidelines 2025 Adult Advanced Life Support", "2025",
        "https://doi.org/10.1016/j.resuscitation.2025.110769",
        status = "Includes peri-arrest tachyarrhythmia and bradycardia algorithms (Figs 8–9) and Table 6; corrigendum Resuscitation 2026;229:111313 (not reviewed)",
    ),
    Source(
        "em-resus-erc-bls-2025", Authority.ERC, "ERC guideline — Resuscitation 2025;215(Suppl 1):110771",
        "European Resuscitation Council Guidelines 2025 Adult Basic Life Support", "2025",
        "https://doi.org/10.1016/j.resuscitation.2025.110771",
    ),
    Source(
        "em-resus-erc-esicm-prc-2025", Authority.ERC, "ERC–ESICM guideline — Resuscitation 2025;215(Suppl 1):110809",
        "European Resuscitation Council and European Society of Intensive Care Medicine Guidelines 2025 Post-Resuscitation Care",
        "2025",
        "https://doi.org/10.1016/j.resuscitation.2025.110809",
        status = "Also published in Intensive Care Med 2025;51(12):2213–2288; corrigendum Resuscitation 2026;228:111315 (not reviewed)",
    ),
    Source(
        "em-resus-erc-special-2025", Authority.ERC, "ERC guideline — Resuscitation 2025;215(Suppl 1):110753",
        "European Resuscitation Council Guidelines 2025 Special Circumstances in Resuscitation", "2025",
        "https://doi.org/10.1016/j.resuscitation.2025.110753",
        status = "Corrigendum Resuscitation 2026;228:111314 (not reviewed)",
    ),
    Source(
        "em-resus-irc-ccls-2017", Authority.Paper, "Indian Resuscitation Council (ISA) guideline — Indian J Anaesth 2017;61(11):883–894",
        "Comprehensive cardiopulmonary life support (CCLS) for cardiopulmonary resuscitation by trained paramedics and medics inside the hospital",
        "2017",
        "https://doi.org/10.4103/ija.IJA_664_17",
        status = "Garg R et al. for the Indian Resuscitation Council; no later IRC adult edition found (checked November 2026); predates AHA/ERC 2025",
    ),
    Source(
        "em-resus-fda-mgso4-label", Authority.FDA, "Prescribing information (DailyMed; ProPharma Distribution)",
        "Magnesium Sulfate Injection, USP 50%", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=386d55c9-aeac-5597-e063-6294a90a0afe",
        status = "US label, version effective 25 June 2025; cited only for mmol-per-mL content and dilution before IV use",
    ),
)

private const val AHA_ALS = "em-resus-aha-als-2025"
private const val AHA_BLS = "em-resus-aha-bls-2025"
private const val AHA_SPECIAL = "em-resus-aha-special-2025"
private const val ERC_ALS = "em-resus-erc-als-2025"
private const val ERC_BLS = "em-resus-erc-bls-2025"
private const val ERC_PRC = "em-resus-erc-esicm-prc-2025"
private const val ERC_SPECIAL = "em-resus-erc-special-2025"
private const val IRC = "em-resus-irc-ccls-2017"
private const val FDA_MG = "em-resus-fda-mgso4-label"

/** Defined in HeartConditions.kt — ICMR STW Atrial Fibrillation (January 2026). */
private const val ICMR_AF = "hc-icmr-stw-af-2026"

/** Defined in Oleander.kt — ICMR STW Bradyarrhythmias in Symptomatic Patients (January 2025). */
private const val ICMR_BRADY = "tx-oleander-icmr-stw-bradyarrhythmia-2025"

// ================================================================================================ Cardiac arrest
private val cardiacArrest = Protocol(
    id = "cardiac-arrest",
    category = ProtocolCategory.Cardiac,
    title = "Adult cardiac arrest (ALS)",
    summary = "High-quality CPR, shockable and non-shockable sequences, adrenaline and antiarrhythmics, airway, " +
        "reversible causes, ECPR/stopping and post-ROSC care — AHA 2025, ERC 2025 and Indian Resuscitation Council.",
    keywords = listOf(
        "cardiac arrest", "cardiopulmonary arrest", "CPR", "CPCR", "ACLS", "ALS", "CCLS", "BLS", "code blue", "collapse",
        "IHCA", "OHCA", "sudden cardiac arrest", "SCA", "sudden cardiac death", "VF", "ventricular fibrillation",
        "pulseless VT", "pVT", "asystole", "PEA", "pulseless electrical activity", "defibrillation", "DC shock",
        "adrenaline", "epinephrine", "amiodarone", "lignocaine", "lidocaine", "ROSC", "post-cardiac arrest care",
        "post-resuscitation care", "4 Hs 4 Ts", "Hs and Ts", "reversible causes", "ETCO2", "capnography", "ECPR", "ECMO",
        "termination of resuscitation", "refractory VF", "crash cart",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Recognise arrest and start high-quality CPR",
            timing = "Immediately; in hospital defibrillate shockable rhythms in <3 min (ERC 2025)",
            points = listOf(
                "ERC 2025: unresponsive with absent or abnormal breathing — start CPR 30:2, attach defibrillator/monitor, call the resuscitation team. IRC 2017: activate Code blue.",
                "AHA 2025: compress at least 5 cm while avoiding more than 6 cm, 100–120/min, full recoil, compression fraction at least 60%.",
                "ERC 2025: at least 5 cm but not more than 6 cm, 100–120/min, no leaning; on a soft bed compress deeper rather than moving the patient.",
                "IRC 2017: 120 compressions/min, depth 5–6 cm; swap compressor every 5 cycles; pulse and breathing check no longer than 10 s.",
                "AHA 2025: change compressor about every 2 min (sooner if fatigued); avoid excessive ventilation. ERC 2025: highest feasible inspired oxygen, each breath over 1 s.",
            ),
            citations = listOf(
                cite(ERC_ALS, "Fig. 2; Treatment of in-hospital cardiac arrest; Airway and ventilation"),
                cite(ERC_BLS, "High quality chest compressions"),
                cite(AHA_BLS, "Chest compression depth and rate Rec 1, 3, 4; Chest compression fraction and pauses Rec 2, 4, 6"),
                cite(AHA_ALS, "Fig. 2 (High-quality CPR)"),
                cite(IRC, "Activate Code blue; Check pulse and breath; Early high-quality cardiopulmonary resuscitation"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Rhythm check and defibrillation",
            timing = "Rhythm check every 2 min",
            points = listOf(
                "AHA and ERC 2025: VF/pulseless VT is shockable — one shock, then resume compressions immediately for 2 min. Asystole/PEA is non-shockable.",
                "ERC 2025: biphasic first shock at least 150 J (pulsed biphasic 130–150 J); escalate if the device allows; energy unknown — use the highest setting.",
                "AHA 2025: manufacturer's energy (e.g. first shock 120–200 J); if unknown, maximum; monophasic 360 J. IRC 2017: 120 J biphasic, escalating to 200 J maximum.",
                "ERC 2025: antero-lateral pads, lateral pad below the armpit in the mid-axillary line; peri-shock pause under 5 s; shock even fine VF.",
                "VF after 3 shocks: ERC 2025 — consider antero-posterior vector change, dual sequential shocks not routine; AHA 2025 — usefulness of both not established.",
                "ERC 2025: up to three stacked shocks only for witnessed, monitored VF/pVT with a defibrillator at hand (e.g. catheter laboratory); for adrenaline and amiodarone timing, the three stacked shocks count as the first shock. AHA 2025: single shocks preferred.",
            ),
            citations = listOf(
                cite(ERC_ALS, "Defibrillation strategy; Defibrillation pads and paddles; Energy levels and number of shocks; Refractory ventricular fibrillation"),
                cite(AHA_ALS, "Defibrillation indication, type and energy Rec 1–6; Vector change and double sequential defibrillation Rec 1–2; Fig. 2 (Shock energy)"),
                cite(IRC, "Early defibrillation"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Adrenaline (epinephrine)",
            timing = "Non-shockable: as soon as possible; then every 3–5 min",
            points = listOf(
                "AHA and ERC 2025: adrenaline 1 mg IV/IO, repeated every 3–5 min while ALS continues.",
                "Non-shockable rhythm: AHA 2025 — as soon as feasible; ERC 2025 — as soon as possible.",
                "Shockable rhythm: ERC 2025 — after the 3rd shock; AHA 2025 — after initial defibrillation attempts fail (its algorithm gives it after the 2nd shock).",
                "IRC 2017: 1 mg diluted to 10 mL once access is secured, irrespective of rhythm; flush with 20 mL saline and raise the limb 10–20 s.",
                "AHA 2025: vasopressin, alone or with adrenaline, offers no advantage; high-dose adrenaline is not recommended.",
                "ERC 2025: arrest with an arterial line in place — suggests 50–100 mcg increments first instead of a 1 mg bolus; if 1 mg in total has no effect, check for extravasation, then consider 1 mg every 3–5 min.",
            ),
            citations = listOf(
                cite(AHA_ALS, "Vasopressor management in cardiac arrest Rec 1–6; Fig. 2"),
                cite(ERC_ALS, "Vasopressor drugs; ALS in highly-monitored cardiac arrest"),
                cite(IRC, "Drugs including antiarrhythmics"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Antiarrhythmics for shock-refractory VF/pVT",
            timing = "ERC 2025: after the 3rd and 5th shocks",
            points = listOf(
                "ERC 2025: amiodarone 300 mg IV after 3 shocks and 150 mg after 5 shocks, whether VF/pVT is continuous or recurrent.",
                "ERC 2025: lidocaine 100 mg IV if amiodarone is unavailable or locally preferred; a further 50 mg after 5 shocks.",
                "AHA 2025 (Class 2b): amiodarone 300 mg then 150 mg, or lidocaine 1–1.5 mg/kg then 0.5–0.75 mg/kg.",
                "IRC 2017: after 2–3 failed shocks, amiodarone 300 mg slow IV bolus, then 150 mg if needed; lidocaine 1–1.5 mg/kg slow bolus as the alternative.",
                "AHA 2025: routine calcium, sodium bicarbonate or magnesium not recommended; steroids of uncertain benefit. ERC 2025: no routine calcium, bicarbonate or corticosteroids.",
            ),
            doses = listOf(
                StepDose(
                    "Lidocaine — first dose (AHA 2025)",
                    WeightDose(1.0, 1.5, "mg", per = "IV/IO bolus"),
                    note = "ERC 2025 uses a fixed 100 mg; IRC 2017 also 1–1.5 mg/kg",
                ),
                StepDose(
                    "Lidocaine — second dose (AHA 2025)",
                    WeightDose(0.5, 0.75, "mg", per = "IV/IO bolus"),
                    note = "ERC 2025 uses a fixed 50 mg after 5 shocks",
                ),
            ),
            citations = listOf(
                cite(ERC_ALS, "Antiarrhythmic drugs; Other medication"),
                cite(AHA_ALS, "Nonvasopressor medications in adult cardiac arrest Rec 1–6; Fig. 2 (Drug therapy)"),
                cite(IRC, "Drugs including antiarrhythmics; Vascular access and drugs"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Access, airway and capnography",
            points = listOf(
                "AHA and ERC 2025: IV access first; IO if IV fails (ERC: not achieved within two attempts). IRC 2017: IO after 3 failed IV attempts.",
                "AHA 2025: the endotracheal drug route has been removed. IRC 2017 still allows it as a last resort at 2–2.5 times the IV dose.",
                "ERC 2025: stepwise airway; intubate only if success >95% within two attempts, pause under 5 s; i-gel preferred SGA. AHA 2025: defer if it interrupts compressions.",
                "AHA and ERC 2025: continuous waveform capnography to confirm and monitor tube position; ERC: a sustained ETCO2 trace excludes oesophageal placement.",
                "Advanced airway in place: 10 breaths/min with continuous compressions (AHA, ERC 2025). ERC 2025: if an SGA leaks, return to 30:2.",
                "ERC 2025: physiology-guided CPR — diastolic BP ≥30 mm Hg and ETCO2 ≥25 mm Hg (3.3 kPa). IRC 2017: ETCO2 <10 mm Hg signals inadequate CPR.",
            ),
            citations = listOf(
                cite(AHA_ALS, "Vascular access in cardiac arrest management Rec 1–3 and Synopsis; Advanced airway placement Rec 2, 3, 9"),
                cite(ERC_ALS, "Vascular access; Airway and ventilation; ALS in highly-monitored cardiac arrest; Waveform capnography"),
                cite(IRC, "Venous access; Airway management; Quality assurance of CCLS conduct"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Reversible causes",
            timing = "Throughout CPR",
            points = listOf(
                "AHA and ERC 2025: search for and treat reversible causes during CPR. IRC 2017: do not move the patient for imaging during compressions.",
                "ERC 2025: suspected or confirmed pulmonary embolism — consider immediate thrombolysis, then consider continuing CPR for 60–90 min in select patients.",
                "ERC 2025: give fluids only if hypovolaemia caused the arrest (isotonic saline or balanced crystalloid).",
                "ERC 2025: POCUS only by skilled operators without extra pauses; RV dilatation alone does not diagnose PE. AHA 2025: usefulness not well established.",
                "Hyperkalaemic arrest: ERC 2025 — 10 mL 10% calcium chloride plus 50 mmol sodium bicarbonate, through separate lines or with a flush in between; AHA 2025 — calcium, bicarbonate and insulin-glucose not well established.",
                "IRC 2017 mnemonic 'HIT THE TARGET' also lists acute coronary syndrome, raised intracranial pressure and hypo-/hyperglycaemia.",
            ),
            checklistTitle = "4 Hs and 4 Ts (ERC 2025; AHA 2025 adds hydrogen ion)",
            checklist = listOf(
                "Hypoxia",
                "Hypovolaemia",
                "Hypo-/hyperkalaemia and other metabolic causes",
                "Hydrogen ion — acidosis (AHA)",
                "Hypothermia (ERC adds hyperthermia)",
                "Thrombosis — coronary or pulmonary",
                "Tension pneumothorax",
                "Tamponade (cardiac)",
                "Toxins",
            ),
            citations = listOf(
                cite(ERC_ALS, "Fig. 2 (reversible causes); Thrombolytic drugs; Fluids; Use of ultrasound imaging during advanced life support"),
                cite(AHA_ALS, "Fig. 2 (Reversible causes); Adjuncts to CPR Rec 2"),
                cite(ERC_SPECIAL, "Hyper/hypokalaemia and other electrolyte disorders — Hyperkalaemia"),
                cite(AHA_SPECIAL, "Hyperkalemia Rec 1–4"),
                cite(IRC, "Assess and manage the reversible causes; Reversible causes"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Refractory arrest: ECPR and stopping",
            timing = "Refractory = 3 failed shocks or 10 min of non-shockable rhythm (ERC 2025)",
            points = listOf(
                "ERC 2025: ECPR may be considered for selected adults when conventional CPR fails, where it can be delivered; recognise refractory arrest early to activate the team.",
                "AHA 2025: intubated patient failing to reach ETCO2 >10 mm Hg after 20 min of ALS — may be one component of a multimodal decision to stop.",
                "AHA 2025 (Class 3: Harm): do not use any ETCO2 cut-off to decide to stop CPR in non-intubated patients. ERC 2025: never use low ETCO2 alone; TOR rules are not the sole basis in hospital.",
                "IRC 2017: with high-quality CPR, asystole >20 min and ETCO2 <10 mm Hg may be a sign to stop; VF/pVT should prompt continued resuscitation.",
                "ERC 2025: do not use POCUS assessment of contractility as the sole reason to stop CPR.",
            ),
            checklistTitle = "ECPR selection criteria in current use (ERC 2025)",
            checklist = listOf(
                "Younger patient (upper age limit 50–75 years), no perceived frailty, no major comorbidity",
                "Witnessed arrest with immediate CPR (no-flow ≤5 min)",
                "Commonly also: initial shockable or PEA rhythm",
                "Commonly also: CPR-to-ECPR (low-flow) time ideally within 45–60 min",
                "Commonly also: known or suspected treatable cause",
                "Commonly also: ROSC before cannulation, signs of life during CPR, ETCO2 >10 mm Hg (1.3 kPa)",
            ),
            citations = listOf(
                cite(ERC_ALS, "Extracorporeal CPR; Waveform capnography; Use of ultrasound imaging; Treatment of in-hospital cardiac arrest"),
                cite(AHA_ALS, "Termination of resuscitation Rec 4–5"),
                cite(IRC, "Duration of cardiopulmonary resuscitation"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Immediately after ROSC",
            timing = "First minutes to hours after ROSC",
            points = listOf(
                "ERC 2025: ABCDE approach, 12-lead ECG, aim systolic BP >100 mm Hg, identify and treat the cause, temperature control.",
                "ERC–ESICM 2025: maximum oxygen until SpO2 or PaO2 is reliable, then SpO2 94–98% (PaO2 75–100 mm Hg); avoid PaO2 <60 mm Hg and hyperoxaemia.",
                "ERC–ESICM 2025: PaCO2 35–45 mm Hg; tidal volume 6–8 mL/kg ideal body weight; pulse oximetry can overestimate SpO2 in darker skin.",
                "ERC–ESICM 2025: arterial line; MAP >60–65 mm Hg with fluids, noradrenaline and/or dobutamine; early echocardiography; no routine steroids; avoid hypo-/hyperkalaemia.",
                "IRC 2017: titrate FiO2 to SpO2 95% or more, ETCO2 35–40 mm Hg, MAP >65 mm Hg; shift to a high-dependency or intensive care unit.",
            ),
            citations = listOf(
                cite(ERC_ALS, "Fig. 2 (Immediately after ROSC)"),
                cite(ERC_PRC, "Control of oxygenation; Control of ventilation; Haemodynamic monitoring and management; Fig. 3"),
                cite(IRC, "Transfer; Post-resuscitation care"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "Coronary angiography, temperature and brain",
            timing = "Comatose: prevent fever for 36–72 h (ERC–ESICM 2025)",
            points = listOf(
                "ERC–ESICM 2025: persistent ST-elevation after ROSC of suspected cardiac origin — emergent catheter laboratory evaluation and primary PCI if required.",
                "ERC–ESICM 2025: OHCA without ST-elevation — delay catheterisation unless acute coronary occlusion is likely; head-to-pelvis CT with CTPA if angiography finds no cause.",
                "ERC–ESICM 2025: comatose after ROSC — actively prevent fever (≤37.5 °C); do not rewarm mild hypothermia (32–36 °C); no prehospital cold IV fluid boluses.",
                "ERC–ESICM 2025: no seizure prophylaxis; treat seizures with levetiracetam or sodium valproate plus sedation; EEG to diagnose.",
                "ERC–ESICM 2025: multimodal prognostication at 72 h or later if GCS motor score <6, after excluding residual sedation.",
                "IRC 2017: early specialist consultation for coronary intervention; without cooling equipment keep temperature not above 36 °C; avoid active warming.",
            ),
            scoreIds = listOf("gcs"),
            citations = listOf(
                cite(ERC_PRC, "Coronary reperfusion; Diagnosis of cause and complications of cardiac arrest; Temperature control; Control of seizures; Predicting neurological outcome"),
                cite(IRC, "Post-resuscitation care"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Adrenaline in shockable rhythms differs: ERC 2025 — after the 3rd shock; AHA 2025 — after initial shocks fail (2nd shock in its algorithm); IRC 2017 — as soon as access is secured, any rhythm.",
            listOf(cite(ERC_ALS, "Vasopressor drugs"), cite(AHA_ALS, "Vasopressor management Rec 4; Fig. 2"), cite(IRC, "Drugs including antiarrhythmics")),
        ),
        ProtocolNote(
            "First-shock energy differs: ERC 2025 — at least 150 J biphasic; AHA 2025 — manufacturer's setting (e.g. 120–200 J) or maximum; IRC 2017 — 120 J, escalating to 200 J.",
            listOf(cite(ERC_ALS, "Energy levels and number of shocks"), cite(AHA_ALS, "Defibrillation Rec 4–6; Fig. 2"), cite(IRC, "Early defibrillation")),
        ),
        ProtocolNote(
            "Lidocaine: AHA 2025 and IRC 2017 dose by weight (1–1.5 mg/kg); ERC 2025 uses fixed 100 mg then 50 mg. AHA rates amiodarone and lidocaine Class 2b (may be considered).",
            listOf(cite(AHA_ALS, "Nonvasopressor medications Rec 1; Fig. 2"), cite(ERC_ALS, "Antiarrhythmic drugs"), cite(IRC, "Vascular access and drugs")),
        ),
        ProtocolNote(
            "Refractory VF is an evidence controversy: ERC 2025 suggests vector change to antero-posterior pads and does not recommend routine dual sequential defibrillation; AHA 2025 calls the usefulness of both unestablished.",
            listOf(cite(ERC_ALS, "Refractory ventricular fibrillation"), cite(AHA_ALS, "Vector change and double sequential defibrillation Rec 1–2")),
        ),
        ProtocolNote(
            "Hyperkalaemic arrest differs: ERC 2025 gives calcium chloride plus sodium bicarbonate; AHA 2025 calls calcium, bicarbonate and insulin-glucose not well established and inhaled β2-agonists of no benefit.",
            listOf(cite(ERC_SPECIAL, "Hyperkalaemia"), cite(AHA_SPECIAL, "Hyperkalemia Rec 1–4")),
        ),
        ProtocolNote(
            "Interrupting compressions for causes differs: ERC 2025 prioritises treating reversible causes where appropriate even if compressions pause briefly; IRC 2017 says compressions must not be interrupted for this.",
            listOf(cite(ERC_SPECIAL, "Concise guidelines for clinical practice — General recommendations"), cite(IRC, "Assess and manage the reversible causes")),
        ),
        ProtocolNote(
            "India: IRC CCLS (2017) predates both 2025 updates — it keeps the endotracheal drug route, allows 3 IV attempts before IO and targets post-ROSC SpO2 95% or more (ERC–ESICM 2025: 94–98%).",
            listOf(cite(IRC, "Venous access; Post-resuscitation care"), cite(AHA_ALS, "Vascular access — Synopsis"), cite(ERC_PRC, "Control of oxygenation")),
        ),
        ProtocolNote(
            "ERC 2025: ALS may need adapting to local resources, with a two-tier approach of basic and advanced interventions; IRC 2017 notes mechanical CPR devices and cooling equipment are not widely available in India.",
            listOf(cite(ERC_ALS, "ALS in low-resource settings"), cite(IRC, "Discussion; Post-resuscitation care")),
        ),
        ProtocolNote(
            "Scope: post-ROSC targets here are ERC–ESICM 2025 and IRC 2017 only; AHA 2025 Part 11 (post-cardiac arrest care) was not available for review. Published corrigenda to the ERC chapters have not been reviewed.",
            listOf(cite(ERC_PRC), cite(ERC_ALS), cite(IRC, "Post-resuscitation care")),
        ),
    ),
    citations = listOf(
        cite(AHA_ALS), cite(AHA_BLS), cite(AHA_SPECIAL), cite(ERC_ALS), cite(ERC_BLS), cite(ERC_PRC), cite(ERC_SPECIAL), cite(IRC),
    ),
)

// ================================================================================================ Tachycardia
private val tachycardiaWithPulse = Protocol(
    id = "tachycardia-with-pulse",
    category = ProtocolCategory.Cardiac,
    title = "Tachycardia with a pulse",
    summary = "Adverse features, synchronised cardioversion energies, SVT (vagal manoeuvres, adenosine), fast AF, " +
        "broad-complex tachycardia and torsades — AHA 2025, ERC 2025 and ICMR STW.",
    keywords = listOf(
        "tachycardia", "tachyarrhythmia", "palpitations", "SVT", "PSVT", "supraventricular tachycardia", "AVNRT", "AVRT",
        "narrow complex tachycardia", "narrow QRS", "broad complex tachycardia", "broad QRS", "wide complex tachycardia",
        "WCT", "VT", "VT with pulse", "ventricular tachycardia", "monomorphic VT", "polymorphic VT", "torsades de pointes",
        "TdP", "AF", "atrial fibrillation", "fast AF", "AF with RVR", "atrial flutter", "WPW", "pre-excitation",
        "adenosine", "modified Valsalva", "vagal manoeuvre", "cardioversion", "DC cardioversion", "synchronised shock",
        "amiodarone", "diltiazem", "verapamil", "metoprolol", "esmolol", "digoxin", "procainamide", "magnesium",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Assess: is the tachycardia causing instability?",
            timing = "At first contact",
            points = listOf(
                "AHA 2025: rate typically ≥150/min if a tachyarrhythmia; immediate cardioversion is generally not needed at ≤150/min.",
                "ERC 2025: ABCDE; monitor SpO2, ECG and BP; oxygen if SpO2 <94%; IV access; record a 12-lead ECG; treat reversible causes.",
                "AHA 2025: decide whether the arrhythmia causes the instability or is a manifestation of another illness such as sepsis.",
                "ERC 2025: sinus tachycardia (e.g. shock) — treat the cause; do not normalise the rate with antiarrhythmics or cardioversion.",
                "ICMR STW (AF): immediate-intervention indicators — systolic BP 90 mm Hg (sign not printed), HR >150 or <50/min, ongoing angina, CHF, TIA or stroke.",
            ),
            checklistTitle = "Life-threatening (adverse) features",
            checklist = listOf(
                "Shock — hypotension (e.g. SBP <90 mm Hg) with poor perfusion (ERC); signs of shock (AHA)",
                "Syncope with severe or ongoing hypotension (ERC) or acutely altered mental status (AHA)",
                "Myocardial ischaemia — ischaemic chest pain or ECG ischaemia",
                "Severe or acute heart failure — pulmonary oedema, raised JVP",
                "Arrhythmia immediately after ROSC (ERC)",
            ),
            citations = listOf(
                cite(AHA_ALS, "Fig. 6; Fig. 7; Regular narrow-complex tachycardia — Synopsis"),
                cite(ERC_ALS, "Peri-arrest arrhythmias; Fig. 8"),
                cite(ICMR_AF, "Look for immediate intervention indicators"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Unstable: synchronised cardioversion",
            timing = "Immediately",
            points = listOf(
                "AHA and ERC 2025: synchronised cardioversion for unstable tachyarrhythmia; sedation or anaesthesia if conscious. ERC: up to 3 attempts.",
                "ERC 2025: AF — start at maximum output; flutter or SVT 70–120 J, then stepwise; VT with a pulse 120–150 J, then stepwise.",
                "AHA 2025 (Fig. 7): AF 200 J; flutter 200 J; narrow-complex 100 J; monomorphic VT 100 J; polymorphic VT — unsynchronised high-energy shock.",
                "ERC 2025: cardioversion fails — amiodarone 300 mg IV over 10–20 min or procainamide 10–15 mg/kg (max 1 g) over 20 min, re-shock; amiodarone 900 mg/24 h.",
                "AHA 2025: if synchronisation delays a critical patient, give unsynchronised shocks; resynchronise after each shock. ICMR STW (district hospital): immediate cardioversion after heparinisation if haemodynamically unstable.",
            ),
            doses = listOf(
                StepDose(
                    "Procainamide (ERC 2025)",
                    WeightDose(10.0, 15.0, "mg", per = "IV over 20 min"),
                    note = "After failed cardioversion, or stable broad-complex tachycardia with high sedation risk",
                    maxTotal = 1000.0,
                ),
            ),
            citations = listOf(
                cite(ERC_ALS, "Tachyarrhythmias; Cardioversion; Fig. 8"),
                cite(AHA_ALS, "Fig. 6; Fig. 7; Electrical therapies for AF/flutter Rec 1, 3, 5; Wide-complex tachycardia Rec 1"),
                cite(ICMR_AF, "Management at district hospital"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Regular narrow-complex (SVT): vagal manoeuvres, adenosine",
            points = listOf(
                "AHA 2025 (Class 1): vagal manoeuvres, then adenosine. ERC 2025: blow into a 10 mL syringe hard enough to move the plunger, preferably supine with legs raised.",
                "AHA 2025: standard Valsalva terminates 20–30%, modified techniques up to 50%; carotid massage can cause stroke with carotid atherosclerosis.",
                "AHA 2025: adenosine 6 mg rapid IV push with saline flush, then 12 mg. ERC 2025: 6 mg, then 12 mg, then consider 18 mg.",
                "AHA 2025: adenosine contraindicated in asthma; 1 mg may suffice via a central line or after heart transplant. ERC 2025: only without pre-excitation.",
                "Adenosine fails: AHA 2025 — IV diltiazem, verapamil or β-blocker; ERC 2025 — verapamil or β-blocker (metoprolol 2.5–15 mg in 2.5 mg boluses); then synchronised shock.",
                "ERC 2025: avoid verapamil and diltiazem with haemodynamic instability or LVEF <40%. AHA 2025: best avoided if systolic heart failure is suspected.",
            ),
            doses = listOf(
                StepDose(
                    "Verapamil (ERC Table 6)",
                    WeightDose(0.075, 0.15, "mg", per = "IV over 2 min"),
                    note = "ERC: average 5–10 mg; AHA Table 2 gives the same dose for AF/flutter rate control",
                ),
                StepDose(
                    "Diltiazem (ERC Table 6)",
                    WeightDose(0.25, 0.25, "mg", per = "IV over 2 min"),
                    note = "ERC: average 20 mg; AHA Table 2 gives the same dose for AF/flutter rate control",
                ),
            ),
            citations = listOf(
                cite(AHA_ALS, "Regular narrow-complex tachycardia Rec 1–6 and supportive text; Fig. 6; Table 2 (AF/flutter rate control: same verapamil and diltiazem doses)"),
                cite(ERC_ALS, "Table 6; Fig. 8"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Irregular narrow-complex: fast AF or flutter",
            points = listOf(
                "AHA 2025 (Class 1): IV β-blocker or non-dihydropyridine CCB to slow the rate if no pre-excitation; IV amiodarone useful when critically ill (2a).",
                "ICMR STW: metoprolol, diltiazem or verapamil if HR >110/min without CHF; β-blocker plus digoxin in heart failure; aim HR <110/min.",
                "ERC 2025: EF >40% — β-blocker, verapamil, diltiazem or digoxin; EF <40% — smallest β-blocker dose for HR <110/min, adding digoxin if necessary (Fig. 8: β-blocker or digoxin); unstable, severe LV dysfunction — amiodarone.",
                "AHA 2025: avoid CCBs and IV β-blockers in decompensated HFrEF. Metoprolol 2.5–5 mg over 2 min, up to 3 doses.",
                "AHA 2025: amiodarone 300 mg IV over 1 h, then 10–50 mg/h over 24 h; digoxin 0.25 mg IV, repeated to maximum 1.5 mg in 24 h, usually as an adjunct (caution in renal impairment).",
                "ERC 2025: anticoagulate if AF has lasted >24 h. AHA 2025: cardioverting AF >48 h without anticoagulation risks thromboembolism.",
            ),
            scoreIds = listOf("cha2ds2-vasc", "has-bled"),
            doses = listOf(
                StepDose(
                    "Diltiazem — bolus (AHA 2025)",
                    WeightDose(0.25, 0.25, "mg", per = "IV over 2 min"),
                    note = "Then 5–10 mg/h; avoid in hypotension, heart failure, cardiomyopathy, ACS",
                ),
                StepDose(
                    "Esmolol — bolus (AHA 2025; optional in ERC)",
                    WeightDose(500.0, 500.0, "mcg", per = "IV over 1 min"),
                    note = "Avoid in decompensated heart failure",
                ),
                StepDose(
                    "Esmolol — infusion (AHA/ERC 2025)",
                    WeightDose(50.0, 300.0, "mcg", per = "per minute (IV infusion)"),
                ),
            ),
            citations = listOf(
                cite(AHA_ALS, "Medical therapies for AF/flutter Rec 1–4; Table 2; Electrical therapies for AF/flutter — Synopsis"),
                cite(ERC_ALS, "Tachyarrhythmias; Peri-arrest arrhythmias (AF); Table 6; Fig. 8"),
                cite(ICMR_AF, "Heart rate control; Management at PHC/CHC; Management algorithm"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Regular broad-complex tachycardia (treat as VT)",
            points = listOf(
                "AHA 2025 (Class 1): synchronised cardioversion if unstable, or if stable and vagal manoeuvres or drugs fail or are contraindicated.",
                "ERC 2025: monomorphic VT with structural heart disease or unknown substrate — synchronised cardioversion even if stable; drugs if sedation risk is high.",
                "AHA 2025: adenosine only if stable, regular and monomorphic (diagnostic and therapeutic); verapamil and diltiazem cause harm in wide-complex tachycardia.",
                "ERC 2025: procainamide 10–15 mg/kg over 20 min; if unavailable or contraindicated (severe HF, acute MI, end-stage renal disease), amiodarone 300 mg over 10–60 min then 900 mg/24 h.",
                "AHA 2025: amiodarone 150 mg over 10 min, repeated if VT recurs, then 1 mg/min for 6 h; procainamide 20–50 mg/min to maximum 17 mg/kg.",
                "AHA 2025: stop procainamide at suppression, hypotension or QRS widening >50%; avoid with long QT or CHF. Do not combine antiarrhythmics; keep a defibrillator ready.",
            ),
            doses = listOf(
                StepDose(
                    "Procainamide (ERC 2025)",
                    WeightDose(10.0, 15.0, "mg", per = "IV over 20 min"),
                    maxTotal = 1000.0,
                ),
                StepDose(
                    "Procainamide — maximum cumulative dose (AHA 2025)",
                    WeightDose(17.0, 17.0, "mg", per = "total, infused at 20–50 mg/min"),
                    note = "Maintenance 1–4 mg/min",
                ),
            ),
            citations = listOf(
                cite(AHA_ALS, "Wide-complex tachycardia Rec 1–6 and supportive text; Fig. 6 (Doses/Details)"),
                cite(ERC_ALS, "Tachyarrhythmias; Fig. 8; Table 6; Peri-arrest arrhythmias"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Irregular broad-complex: polymorphic VT, torsades, pre-excited AF",
            timing = "Sustained polymorphic VT: shock immediately",
            points = listOf(
                "AHA 2025: sustained polymorphic VT is unstable — immediate unsynchronised high-energy (defibrillation) shock.",
                "ERC 2025: polymorphic VT with QT prolongation — magnesium 8 mmol IV over 10 min (repeat once if needed); isoprenaline or temporary pacing to raise HR.",
                "US label: 50% magnesium sulfate has 2.03 mmol (500 mg) per mL, so 8 mmol ≈ 4 mL ≈ 2 g; dilute to 20% or less before IV use.",
                "AHA 2025: correct hypokalaemia; acute β-blockers can precipitate torsades; pause-dependent torsades — expert help for overdrive pacing or isoprenaline.",
                "AHA 2025: polymorphic VT with normal QT — lidocaine, amiodarone and treat ischaemia; routine magnesium not recommended.",
                "Pre-excited AF (fast, broad, irregular): ERC 2025 — procainamide or cardioversion; AHA 2025 — cardioversion, avoiding AV-nodal blockers.",
            ),
            checklistTitle = "Do not give",
            checklist = listOf(
                "Verapamil or diltiazem in wide-complex tachycardia (AHA 2025: harm)",
                "Adenosine in unstable, irregular or polymorphic wide-complex tachycardia (AHA 2025: harm)",
                "Digoxin, CCB, β-blocker or IV amiodarone in pre-excited AF/flutter (AHA 2025: harm; ERC: amiodarone contraindicated)",
                "Amiodarone in polymorphic VT with QT prolongation (ERC 2025)",
                "Combinations of antiarrhythmics — may be proarrhythmic (AHA 2025)",
            ),
            citations = listOf(
                cite(AHA_ALS, "Polymorphic ventricular tachycardia Rec 1–4 and supportive text; Wide-complex tachycardia Rec 5–6; Medical therapies for AF/flutter Rec 3"),
                cite(ERC_ALS, "Fig. 8; Table 6; Peri-arrest arrhythmias"),
                cite(FDA_MG, "Description; Dosage and administration"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Triggers, refractory cases and expert help",
            points = listOf(
                "ERC 2025: look for triggers — acute ischaemia, electrolyte imbalance, fever or hypothermia, hormonal factors, sepsis, starvation, decompensated heart failure.",
                "ERC 2025: seek expert help if the arrhythmia or life-threatening features persist; pursue expert help for peri-arrest wide-QRS arrhythmias.",
                "AHA 2025: refractory — reconsider the cause, higher energy for the next cardioversion, add an antiarrhythmic, expert consultation.",
                "ERC 2025: recurrent VT despite treatment — consider ICD reprogramming, β-blockers or sedation, mechanical circulatory support and referral for ablation.",
            ),
            citations = listOf(
                cite(ERC_ALS, "Peri-arrest arrhythmias; Fig. 8"),
                cite(AHA_ALS, "Fig. 6 (If refractory)"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Cardioversion energy differs: AHA 2025 — AF and flutter 200 J, narrow-complex and monomorphic VT 100 J (device settings first); ERC 2025 — AF maximum output, flutter/SVT 70–120 J, VT 120–150 J.",
            listOf(cite(AHA_ALS, "Fig. 6; Fig. 7; Electrical therapies for AF/flutter Rec 3, 5"), cite(ERC_ALS, "Tachyarrhythmias; Cardioversion")),
        ),
        ProtocolNote(
            "Adenosine: ERC 2025 adds a third dose of 18 mg; AHA 2025 stops at 12 mg.",
            listOf(cite(ERC_ALS, "Table 6; Fig. 8"), cite(AHA_ALS, "Fig. 6 (Doses/Details)")),
        ),
        ProtocolNote(
            "Stable broad-complex drugs differ: ERC 2025 puts procainamide first, amiodarone if unavailable; AHA 2025 lists amiodarone, procainamide or sotalol as Class 2b. ERC notes IV procainamide is unavailable or unlicensed in many countries.",
            listOf(cite(ERC_ALS, "Fig. 8; Peri-arrest arrhythmias"), cite(AHA_ALS, "Wide-complex tachycardia Rec 3")),
        ),
        ProtocolNote(
            "Amiodarone for stable VT: AHA 2025 — 150 mg over 10 min then 1 mg/min for 6 h; ERC 2025 — 300 mg over 10–60 min then 900 mg over 24 h.",
            listOf(cite(AHA_ALS, "Fig. 6 (Doses/Details)"), cite(ERC_ALS, "Table 6; Fig. 8")),
        ),
        ProtocolNote(
            "Lidocaine for wide-QRS tachycardia: ERC 2025 Table 6 lists 50–200 mg bolus then 2–4 mg/min (reduce in shock); AHA 2025 says lidocaine is less effective for tolerated VT and may not beat placebo.",
            listOf(cite(ERC_ALS, "Table 6"), cite(AHA_ALS, "Wide-complex tachycardia Rec 3 supportive text")),
        ),
        ProtocolNote(
            "India: ICMR has no adult tachyarrhythmia STW (its 'Tachyarrhythmia' STW is paediatric). The ICMR AF STW aims for a ventricular rate below 110/min in AF; ERC 2025 gives the same <110/min target for stable patients with LVEF <40%.",
            listOf(cite(ICMR_AF, "Heart rate control"), cite(ERC_ALS, "Tachyarrhythmias")),
        ),
    ),
    citations = listOf(cite(AHA_ALS), cite(ERC_ALS), cite(ICMR_AF), cite(FDA_MG)),
)

// ================================================================================================ Bradycardia
private val bradycardia = Protocol(
    id = "bradycardia",
    category = ProtocolCategory.Cardiac,
    title = "Bradycardia with a pulse",
    summary = "Adverse features, atropine (AHA and ERC doses differ), adrenaline/dopamine/isoprenaline, pacing and " +
        "treatable causes (drugs, hyperkalaemia, ischaemia) — AHA 2025, ERC 2025 and ICMR STW.",
    keywords = listOf(
        "bradycardia", "bradyarrhythmia", "slow heart rate", "low heart rate", "heart block", "complete heart block", "CHB",
        "third degree AV block", "Mobitz II", "AV block", "sick sinus syndrome", "sinus node dysfunction", "sinus pause",
        "atropine", "isoprenaline", "isoproterenol", "dopamine", "adrenaline infusion", "pacing", "transcutaneous pacing",
        "TCP", "temporary pacemaker", "TPI", "TPM", "transvenous pacing", "fist pacing", "beta-blocker overdose",
        "calcium channel blocker overdose", "digoxin toxicity", "hyperkalaemia", "MI", "glucagon", "syncope",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Recognise and assess",
            timing = "At first contact",
            points = listOf(
                "AHA 2025: bradyarrhythmia typically means HR <50/min; judge whether it drives the presentation. ICMR STW: symptoms with a persistent pulse <50/min.",
                "ERC 2025: ABCDE; monitor SpO2, ECG and BP; oxygen if SpO2 <94%; IV access; 12-lead ECG; treat reversible causes such as MI or electrolytes.",
                "ICMR STW: tests — 12-lead ECG, urea, creatinine, electrolytes, glucose, TSH; ask about β-blockers, CCBs, digoxin, hypothyroidism, kidney disease, devices.",
                "AHA 2025: no cardiopulmonary compromise — identify and treat causes, support ABCs, obtain a 12-lead ECG and observe.",
            ),
            checklistTitle = "Life-threatening features — treat now",
            checklist = listOf(
                "Shock or hypotension (ICMR STW: SBP <90 mm Hg)",
                "Syncope (ERC) or acutely altered mental status (AHA)",
                "Myocardial ischaemia — ischaemic chest pain",
                "Severe or acute heart failure",
                "Immediately after ROSC (ERC)",
                "ICMR STW urgent referral: recurrent syncope, headache ± neurological deficit, pre-existing device, CHB, pauses >3 s",
            ),
            citations = listOf(
                cite(AHA_ALS, "Initial management of bradycardia — Synopsis; Fig. 8"),
                cite(ERC_ALS, "Fig. 9"),
                cite(ICMR_BRADY, "When to suspect; Basic evaluation; Indications for urgent treatment/referral"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Atropine first",
            timing = "Repeat every 3–5 min to a total of 3 mg",
            points = listOf(
                "AHA 2025 (Class 2a): atropine 1 mg IV bolus, repeated every 3–5 min, maximum 3 mg.",
                "ERC 2025: atropine 500 mcg IV/IO, repeated every 3–5 min to a total of 3 mg; glycopyrrolate may be used instead.",
                "ICMR STW gives no atropine dose; it advises IV dopamine or isoprenaline until temporary pacing can be placed.",
                "ERC 2025: bradycardia after spinal cord injury — consider aminophylline 100–200 mg slow IV.",
            ),
            checklistTitle = "Do not give atropine (ERC 2025)",
            checklist = listOf(
                "High-degree AV block with wide QRS — do not give: ineffective and may worsen the block",
                "Heart transplant — do not give: can cause high-degree AV block or sinus arrest; use aminophylline 100–200 mg slow IV",
            ),
            citations = listOf(
                cite(AHA_ALS, "Initial management of bradycardia Rec 2; Fig. 8 (Doses/Details)"),
                cite(ERC_ALS, "Bradycardia; Fig. 9"),
                cite(ICMR_BRADY, "Evaluation and treatment of unstable patients"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Atropine ineffective: infusions",
            timing = "While pacing is arranged",
            points = listOf(
                "AHA 2025 (Class 2b): adrenaline or dopamine infusion, or transcutaneous pacing, while transvenous pacing is prepared.",
                "AHA 2025: adrenaline 2–10 mcg/min; dopamine 5–20 mcg/kg/min — titrate to response, taper slowly.",
                "ERC 2025: isoprenaline 5 mcg/min starting dose or adrenaline 2–10 mcg/min; alternatives aminophylline, dopamine, glucagon.",
                "ICMR STW: IV dopamine or isoprenaline may be given until a temporary pacemaker can be inserted.",
                "AHA 2025: push-dose adrenaline lacks bradycardia data; dosing errors and adverse haemodynamic effects are reported.",
            ),
            doses = listOf(
                StepDose(
                    "Dopamine infusion (AHA 2025)",
                    WeightDose(5.0, 20.0, "mcg", per = "per minute (IV infusion)"),
                    note = "Titrate to response; taper slowly",
                ),
            ),
            citations = listOf(
                cite(AHA_ALS, "Initial management of bradycardia Rec 4 and supportive text; Fig. 8 (Doses/Details)"),
                cite(ERC_ALS, "Bradycardia; Fig. 9"),
                cite(ICMR_BRADY, "Temporary pacemaker insertion"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Pacing",
            timing = "Early transvenous pacing if unstable (ERC 2025)",
            points = listOf(
                "ERC 2025: establish transvenous pacing early in unstable symptomatic bradycardia; transcutaneous pacing as a bridge or when transvenous is unavailable.",
                "AHA 2025 (2a): temporary transvenous pacing for unstable bradycardia refractory to drugs; immediate pacing if no IV/IO access in high-degree AV block (2b).",
                "ERC 2025: analgesia or sedation for transcutaneous pacing. AHA 2025: electrical and mechanical capture can be hard to achieve and maintain.",
                "Fist pacing: ERC 2025 — at 50–70/min only while awaiting equipment, effectiveness unproven; AHA 2025 — only witnessed, monitored in-hospital onset, never routine in arrest.",
                "ERC 2025: P-wave asystole may respond to pacing; do not pace asystole without P waves.",
            ),
            checklistTitle = "Risk of asystole — arrange transvenous pacing (ERC 2025)",
            checklist = listOf(
                "Recent asystole",
                "Mobitz II AV block",
                "Complete heart block with broad QRS",
                "Ventricular pause >3 s",
            ),
            citations = listOf(
                cite(ERC_ALS, "Bradycardias and pacing; Bradycardia; Fig. 9"),
                cite(AHA_ALS, "Initial management of bradycardia Rec 3, 5 and supportive text"),
                cite(AHA_SPECIAL, "Percussion pacing Rec 1–2"),
                cite(ICMR_BRADY, "Temporary pacemaker insertion"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Drug and toxin causes",
            points = listOf(
                "ICMR STW: suspected β-blocker or CCB overdose — withhold the drug; also consider clonidine and lithium.",
                "Glucagon (β-blocker/CCB): ERC 2025 — 3–10 mg IV over 1–2 min, then 2–5 mg/h if effective; AHA 2025 — 2–10 mg, then 1–15 mg/h; anticipate vomiting.",
                "AHA 2025 (Class 1): vasopressors (e.g. noradrenaline, adrenaline) for hypotension in β-blocker and CCB poisoning.",
                "AHA 2025 (Class 1): high-dose insulin for hypotension in CCB poisoning, and for vasopressor-refractory hypotension in β-blocker poisoning.",
                "AHA 2025: calcium reasonable in CCB, may be reasonable in β-blocker poisoning — calcium chloride 2000 mg (through a central line) or calcium gluconate 6000 mg initially, then titrate to BP; do not exceed ionised calcium 1.5–2 times the upper limit of normal.",
                "AHA 2025 digoxin Fab: acute — 1 vial per 0.5 mg ingested; amount unknown, critically ill — 10–20 vials; chronic — vials = level (ng/mL) × kg ÷ 100.",
                "AHA 2025 Table 4: yellow oleander — Fab 1200 mg (30 vials); organophosphates — atropine 1–2 mg, doubled every 5 min (see oleander and organophosphate protocols).",
            ),
            doses = listOf(
                StepDose(
                    "Regular insulin — high-dose bolus (AHA 2025)",
                    WeightDose(1.0, 1.0, "units", per = "IV bolus"),
                    note = "β-blocker or CCB poisoning; monitor for hypoglycaemia, hypokalaemia and volume overload",
                ),
                StepDose(
                    "Regular insulin — infusion (AHA 2025)",
                    WeightDose(1.0, 10.0, "units", per = "per hour (IV infusion)"),
                ),
            ),
            citations = listOf(
                cite(ICMR_BRADY, "Treatment of associated conditions; General approach to patients with symptomatic bradycardia"),
                cite(ERC_ALS, "Peri-arrest arrhythmias (bradycardia)"),
                cite(AHA_SPECIAL, "Toxicology: β-blockers Rec 1–3, 9 and supportive text; Toxicology: calcium channel blockers Rec 1–2, 5–6; Toxicology: digoxin and related cardiac glycosides Rec 1–2; Table 4"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Hyperkalaemia, ischaemia and other causes",
            timing = "ERC 2025: IV calcium for severe hyperkalaemia with ECG changes",
            points = listOf(
                "ICMR STW: hyperkalaemia (K+ >6 mmol/L or ECG changes) — stop K+ supplements, K-sparing diuretics, NSAIDs and RAAS inhibitors.",
                "ICMR STW: 10 mL 10% calcium gluconate over 10 min; IV insulin with glucose; IV sodium bicarbonate; salbutamol, loop diuretics or potassium binders.",
                "ERC 2025: 10 mL 10% calcium chloride for severe hyperkalaemia with ECG changes; insulin 10 units with glucose 25 g, then 10% glucose 50 mL/h for 5 h if pre-treatment glucose <7 mmol/L (hypoglycaemia in up to 28%); nebulised salbutamol 10–20 mg.",
                "AHA and ERC 2025 list myocardial ischaemia/infarction as a cause; ERC 2025: immediate pacing especially when block is at or below the His–Purkinje level.",
                "AHA 2025: other causes — hypoxaemia, hypothyroidism, infection, structural heart disease, increased vagal tone, metabolic derangement, medications or toxins.",
            ),
            citations = listOf(
                cite(ICMR_BRADY, "Treatment of associated conditions (hyperkalaemia)"),
                cite(ERC_SPECIAL, "Hyper/hypokalaemia and other electrolyte disorders — Hyperkalaemia"),
                cite(AHA_ALS, "Initial management of bradycardia Rec 1 supportive text; Fig. 8 (Possible causes)"),
                cite(ERC_ALS, "Bradycardias and pacing; Fig. 9"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Stable patients and permanent pacing",
            points = listOf(
                "ERC 2025: no life-threatening features and no risk of asystole — observe.",
                "ICMR STW: stable — 12-lead ECG for AV block, sinus node dysfunction, bundle branch or trifascicular block, AF with CHB; transthoracic echocardiography.",
                "ICMR STW permanent pacing: CHB, advanced or Mobitz II AV block; symptomatic sinus pauses >3 s or awake HR <40/min with symptom correlation.",
                "ICMR STW permanent pacing: unexplained syncope with bundle branch block or transient high-grade AV block; alternating BBB; infiltrative or neuromuscular disease.",
            ),
            citations = listOf(
                cite(ERC_ALS, "Fig. 9"),
                cite(ICMR_BRADY, "Evaluation and management of stable patients; Indications for permanent pacing"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Atropine dose differs: AHA 2025 — 1 mg every 3–5 min, maximum 3 mg; ERC 2025 — 500 mcg every 3–5 min to 3 mg. The ICMR STW gives no atropine dose.",
            listOf(cite(AHA_ALS, "Fig. 8"), cite(ERC_ALS, "Bradycardia; Fig. 9"), cite(ICMR_BRADY, "Evaluation and treatment of unstable patients")),
        ),
        ProtocolNote(
            "Second-line drugs differ: AHA 2025 — adrenaline or dopamine; ERC 2025 — isoprenaline or adrenaline, dopamine as an alternative; ICMR STW — dopamine or isoprenaline until temporary pacing.",
            listOf(cite(AHA_ALS, "Initial management of bradycardia Rec 4"), cite(ERC_ALS, "Fig. 9"), cite(ICMR_BRADY, "Temporary pacemaker insertion")),
        ),
        ProtocolNote(
            "Pacing sequence differs: ERC 2025 favours early transvenous pacing with transcutaneous pacing as a bridge; AHA 2025 offers transcutaneous pacing or infusions while transvenous pacing is prepared.",
            listOf(cite(ERC_ALS, "Bradycardias and pacing"), cite(AHA_ALS, "Initial management of bradycardia Rec 3–4")),
        ),
        ProtocolNote(
            "Glucagon differs: ERC 2025 — 3–10 mg then 2–5 mg/h; AHA 2025 — 2–10 mg then 1–15 mg/h. AHA 2025 calls glucagon reasonable in β-blocker and uncertain in CCB poisoning.",
            listOf(cite(ERC_ALS, "Peri-arrest arrhythmias (bradycardia)"), cite(AHA_SPECIAL, "Table 4; Toxicology: β-blockers Rec 3; calcium channel blockers Rec 6")),
        ),
        ProtocolNote(
            "Hyperkalaemia: ICMR STW uses 10 mL 10% calcium gluconate over 10 min; ERC 2025 uses 10 mL 10% calcium chloride. These are not equivalent: 10 mL 10% calcium chloride contains about three times the calcium of 10 mL 10% calcium gluconate (AHA 2025 Table 4: 2000 mg chloride and 6000 mg gluconate each ≈ 28 mEq Ca2+).",
            listOf(cite(ICMR_BRADY, "Treatment of associated conditions"), cite(ERC_SPECIAL, "Hyperkalaemia"), cite(AHA_SPECIAL, "Table 4")),
        ),
        ProtocolNote(
            "Sodium bicarbonate differs: the ICMR STW lists IV sodium bicarbonate for hyperkalaemia; ERC 2025 recommends against its routine use outside cardiac arrest (potassium falls by only about 0.1 mmol/L).",
            listOf(cite(ICMR_BRADY, "Treatment of associated conditions"), cite(ERC_SPECIAL, "Hyperkalaemia — Sodium bicarbonate")),
        ),
        ProtocolNote(
            "Definition: AHA 2025 follows the 2018 ACC/AHA/HRS threshold of HR <50/min (historically <60/min); the ICMR STW also uses <50/min.",
            listOf(cite(AHA_ALS, "Initial management of bradycardia — Synopsis"), cite(ICMR_BRADY, "When to suspect")),
        ),
    ),
    citations = listOf(cite(AHA_ALS), cite(AHA_SPECIAL), cite(ERC_ALS), cite(ERC_SPECIAL), cite(ICMR_BRADY)),
)

internal val resuscitationProtocols: List<Protocol> = listOf(cardiacArrest, tachycardiaWithPulse, bradycardia)
