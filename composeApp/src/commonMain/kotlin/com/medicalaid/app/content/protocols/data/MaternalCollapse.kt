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
 * Maternal collapse / cardiac arrest in pregnancy, and trauma in pregnancy. Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them (checked November 2026).
 * - Every point is attributed ("AHA 2025: …", "ERC 2025: …", "AOA India 2022: …", "SOGC 2015: …").
 * - AHA locators are section, recommendation number and journal page (Circulation 2025;152 Suppl 2);
 *   ERC locators are section headings, tables and figures; SOGC locators are recommendation numbers.
 * - Reuses sources defined elsewhere: em-resus-aha-special-2025 (DrugTox.kt), em-resus-erc-special-2025
 *   (Resuscitation.kt), ob-ecl-mohfw-stg-obg, ob-ecl-mohfw-hdu-2016 and ob-ecl-who-mcpc-2017 (Eclampsia.kt),
 *   acog-pb181 and mohfw-td (Sources.kt).
 * - Not verified, so not summarised: RCOG GTG 56 full text (publisher site not reachable); SOGC 2015 and EAST 2025
 *   full texts (recommendations taken from their published abstracts, which list them); ACOG CO 723 full text
 *   (abstract only — no radiation-dose thresholds reproduced). No MoHFW, ICMR STW or FOGSI document on maternal
 *   cardiac arrest or trauma in pregnancy was found.
 */

internal val maternalCollapseSources = listOf(
    Source(
        "ob-mat-smfm-afe-2016", Authority.SMFM, "Clinical Guidelines No. 9 (Am J Obstet Gynecol 2016;215(2):B16–B24)",
        "Amniotic fluid embolism: diagnosis and management", "2016",
        "https://doi.org/10.1016/j.ajog.2016.03.012",
        status = "Reaffirmed 2024 (SMFM publications page)",
    ),
    Source(
        "ob-mat-smfm-afe-checklist-2026", Authority.SMFM, "Special Statement (Pregnancy 2026;2:e70364)",
        "Checklist for initial management of amniotic fluid embolism — Updated 2026", "2026",
        "https://doi.org/10.1002/pmf2.70364",
        status = "Replaces the 2021 SMFM checklist; correction 28 August 2026 (Figure 1 dose units changed to mcg/kg/min)",
    ),
    Source(
        "ob-mat-aoa-india-mca-2022", Authority.Paper,
        "Association of Obstetric Anesthesiologists (AOA), India — expert consensus (J Obstet Anaesth Crit Care 2022;12(2):85–93)",
        "An expert committee consensus statement and recommendations for the management of maternal cardiac arrest", "2022",
        "https://doi.org/10.4103/JOACC.JOACC_44_22",
        status = "Pandya ST et al.; Indian professional-society consensus, not a MoHFW or ICMR document",
    ),
    Source(
        "ob-mat-qrh-last-2023", Authority.Paper, "Association of Anaesthetists Quick Reference Handbook 3-10 (v.2)",
        "Local anaesthetic toxicity", "2023",
        "https://anaesthetists.org/Portals/0/PDFs/QRH/QRH_3-10_Local_anaesthetic_toxicity_v2_June%202023.pdf",
        status = "June 2023; UK and Ireland",
    ),
    Source(
        "ob-mat-rcog-gtg56", Authority.RCOG, "Green-top Guideline No. 56 (BJOG 2020;127:e14–e52)",
        "Maternal Collapse in Pregnancy and the Puerperium", "2019",
        "https://www.rcog.org.uk/guidance/browse-all-guidance/green-top-guidelines/maternal-collapse-in-pregnancy-and-the-puerperium-green-top-guideline-no-56/",
        status = "Second edition 2019; reviewed 2024 with no update required; next review due 2026. Full text not reviewed for this page",
    ),
    Source(
        "ob-mat-sogc-trauma-2015", Authority.SOGC, "Clinical Practice Guideline No. 325 (J Obstet Gynaecol Can 2015;37(6):553–574)",
        "Guidelines for the Management of a Pregnant Trauma Patient", "2015",
        "https://doi.org/10.1016/S1701-2163(15)30232-2",
        status = "Recommendations 1–31 checked against the published abstract (PubMed 26334607); no later SOGC edition found (November 2026)",
    ),
    Source(
        "ob-mat-east-2025", Authority.EAST, "Practice management guideline (J Trauma Acute Care Surg 2025;99(2):298–309)",
        "Trauma in pregnancy: a systematic review, meta-analysis, and practice management guideline from the Eastern Association for the Surgery of Trauma",
        "2025",
        "https://doi.org/10.1097/TA.0000000000004661",
        status = "Recommendations checked against the published abstract; full text not reviewed",
    ),
    Source(
        "ob-mat-acog-co723", Authority.ACOG, "Committee Opinion No. 723 (Obstet Gynecol 2017;130:e210–e216)",
        "Guidelines for Diagnostic Imaging During Pregnancy and Lactation", "2017",
        "https://www.acog.org/clinical/clinical-guidance/committee-opinion/articles/2017/10/guidelines-for-diagnostic-imaging-during-pregnancy-and-lactation",
        status = "Reaffirmed 2026; interim update on MRI and gadolinium. Abstract reviewed",
    ),
    Source(
        "ob-mat-cdc-tetanus-wound", Authority.CDC, "Clinical guidance (web page)",
        "Clinical Guidance for Wound Management to Prevent Tetanus", "2026",
        "https://www.cdc.gov/tetanus/hcp/clinical-guidance/index.html",
        status = "Reviewed 8 September 2026; not pregnancy-specific",
    ),
)

private const val AHA = "em-resus-aha-special-2025"
private const val ERC = "em-resus-erc-special-2025"
private const val SMFM16 = "ob-mat-smfm-afe-2016"
private const val SMFM26 = "ob-mat-smfm-afe-checklist-2026"
private const val AOA = "ob-mat-aoa-india-mca-2022"
private const val QRH = "ob-mat-qrh-last-2023"
private const val GTG56 = "ob-mat-rcog-gtg56"
private const val SOGC = "ob-mat-sogc-trauma-2015"
private const val EAST = "ob-mat-east-2025"
private const val CO723 = "ob-mat-acog-co723"
private const val CDC_TET = "ob-mat-cdc-tetanus-wound"

// Defined in other files.
private const val MOH_STG = "ob-ecl-mohfw-stg-obg"
private const val MOH_HDU = "ob-ecl-mohfw-hdu-2016"
private const val WHO_MCPC = "ob-ecl-who-mcpc-2017"
private const val PB181 = "acog-pb181"
private const val MOH_TD = "mohfw-td"

internal val maternalCollapseProtocols = listOf(
    // =============================================================================================
    // 1. Maternal collapse and cardiac arrest in pregnancy
    // =============================================================================================
    Protocol(
        id = "maternal-collapse",
        category = ProtocolCategory.Obstetric,
        title = "Maternal collapse and cardiac arrest in pregnancy",
        summary = "Team call, uterine displacement, airway and CPR changes, resuscitative hysterotomy, obstetric causes, " +
            "magnesium and local anaesthetic toxicity, amniotic fluid embolism and post-arrest care — attributed to " +
            "AHA 2025, ERC 2025, SMFM, AOA India and MoHFW.",
        keywords = listOf(
            "maternal collapse", "maternal arrest", "maternal cardiac arrest", "cardiac arrest in pregnancy",
            "CPR in pregnancy", "pregnancy CPR", "perimortem caesarean", "perimortem cesarean", "PMCS", "PMCD",
            "resuscitative hysterotomy", "resuscitative delivery", "resuscitative uterine interventions", "RUI",
            "left uterine displacement", "LUD", "MLUD", "aortocaval compression", "amniotic fluid embolism", "AFE",
            "LAST", "local anaesthetic toxicity", "local anesthetic toxicity", "bupivacaine toxicity", "lipid emulsion",
            "Intralipid", "lipid rescue", "magnesium toxicity", "MgSO4 toxicity", "calcium gluconate", "high spinal",
            "maternal code blue", "code blue", "4Ps", "behoshi", "behosh", "dil ka daura", "saans rukna",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise and call the maternal arrest team",
                timing = "At collapse",
                points = listOf(
                    "ERC 2025: consider pregnancy in any collapsed woman of childbearing age; maternal cardiac arrest includes up to six weeks after birth.",
                    "ERC 2025: call a maternal cardiac arrest team including an obstetrician and a neonatologist. AHA 2025: activate the cardiac arrest in pregnancy team.",
                    "AOA India 2022: activate the 'maternal code blue' team as soon as unresponsiveness is established; without obstetric or neonatal services, designate trained providers.",
                    "AHA 2025: the team should have the people and equipment to perform a resuscitative delivery at the diagnosis of cardiac arrest.",
                    "SMFM 2026: designate a timekeeper to call out times at 1-minute intervals.",
                    "ERC 2025 (prevention): ABCDE assessment of any deteriorating pregnant woman; obstetric-specific early warning systems aid early recognition.",
                ),
                citations = listOf(
                    cite(ERC, "Cardiac arrest in pregnancy — introduction; Prevention; Table 12"),
                    cite(AHA, "Pregnancy — Team preparation, Rec 1–3; Figure 5 (pp. S621–S622)"),
                    cite(AOA, "Basic life support"),
                    cite(SMFM26, "Figure 1 (checklist)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Uterine displacement and chest compressions",
                timing = "Throughout CPR until delivery",
                points = listOf(
                    "AHA 2025: continuous manual left lateral uterine displacement with chest compressions when fundal height is at or above the umbilicus.",
                    "ERC 2025: manual left uterine displacement is suggested for practicality — one rescuer displaces, another compresses. AOA India 2022: if >20 weeks or fundus above umbilicus.",
                    "ERC 2025, AOA India 2022: from her right, push the uterus up and left; from her left, cup and lift it up and left — no downward force.",
                    "ERC 2025: about 30° left tilt partly relieves caval compression but compressions may be less effective. AOA India 2022: 30° tilt not recommended.",
                    "ERC 2025: compression quality and 30:2 ratio unchanged; no strong evidence for a different hand position. AOA India 2022: lower half of sternum, firm backboard.",
                ),
                citations = listOf(
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 4; Figure 6 (p. S624)"),
                    cite(ERC, "Aortocaval compression; Fig. 19; Table 12; Chest compressions"),
                    cite(AOA, "Patient positioning; Chest compressions — Recommendations and Not recommended"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Airway, defibrillation, access and drugs",
                timing = "Early — airway is a priority (AHA 2025)",
                points = listOf(
                    "AHA 2025: prioritise the airway — higher oxygen demand and lower FRC cause rapid hypoxaemia; aspiration and failed intubation are more likely.",
                    "AHA 2025: most experienced operator, smaller tracheal tube, supraglottic airway after 2 failed attempts. ERC 2025: video laryngoscope as standard; ramped position.",
                    "AOA India 2022: cuffed tube 6–7 mm ID; second-generation supraglottic airway after two optimised attempts; front-of-neck access if ventilation fails; waveform capnography.",
                    "ERC 2025: shock energy and pad position unchanged — pads beneath, not over, breast tissue. Fetal monitors: see checklist.",
                    "AHA 2025: IV/IO in the upper limbs (Figure 5: IV above the diaphragm). ERC 2025 and AOA India 2022: IV/IO above the diaphragm where possible.",
                    "ERC 2025: resuscitation drug doses as for non-pregnant adults. AOA India 2022: adrenaline 1 mg IV every 3–5 min; amiodarone 300 mg, then 150 mg.",
                ),
                checklistTitle = "Fetal monitors during arrest",
                checklist = listOf(
                    "AHA 2025: remove fetal monitors; do not monitor the fetus during maternal arrest",
                    "ERC 2025: remove internal and external fetal monitors before defibrillation",
                    "AOA India 2022: never delay a shock to remove them",
                ),
                citations = listOf(
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 5, 7, 9 and supportive text; Figure 5 (pp. S623–S625)"),
                    cite(ERC, "Table 12; Airway management; Defibrillation; Medications"),
                    cite(AOA, "Defibrillation; Advanced life support; Medications — Recommendations"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Resuscitative hysterotomy (perimortem caesarean)",
                timing = "Prepare from recognition of arrest; AHA 2025 goal — delivery complete by 5 min",
                points = listOf(
                    "AHA 2025: indicated when fundal height is at or above the umbilicus and standard resuscitation has not achieved ROSC; assisted vaginal birth if the cervix is fully dilated.",
                    "AHA 2025: begin preparing at recognition of arrest, aiming to complete delivery by 5 min; perform at the site of in-hospital arrest — do not move to theatre.",
                    "ERC 2025: >20 weeks or fundus above umbilicus; as soon as possible at the arrest site by a skilled team. The fixed 4-min start/5-min delivery rule is de-emphasised.",
                    "ERC 2025: not futile beyond 5 min, but benefit declines with delay. Prehospital — only with access and a trained clinician; otherwise time-critical transport.",
                    "SMFM 2026: no ROSC in 4 min — deliver immediately; splash prep only; do not wait for antibiotics; theatre only if reachable in under 2 min.",
                    "AOA India 2022: consider after 5 min of CPR without ROSC if >20 weeks; start with a scalpel; continue CPR and uterine displacement until delivery.",
                ),
                checklistTitle = "Kit (ERC 2025 Table 14; AOA India 2022)",
                checklist = listOf(
                    "Minimum on every resuscitation trolley: a scalpel and 3 cord clamps (ERC)",
                    "Sterile non-latex gloves, gowns, masks; skin preparation (SMFM 2026: povidone-iodine)",
                    "3 scalpels, dissection scissors, laparotomy sponges, 4 haemostats, retractor, gauze",
                    "Vascular clamps — placenta accreta spectrum (AOA India)",
                    "For the baby: 3 cord clamps, hat, 4 towels, heated incubator, neonatal resuscitation equipment",
                ),
                citations = listOf(
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 1–3 and supportive text (pp. S623–S624)"),
                    cite(ERC, "Resuscitative hysterotomy; Table 12; Table 14"),
                    cite(SMFM26, "§2; Figure 1"),
                    cite(AOA, "Resuscitative uterine interventions — Recommendations"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Find the cause: 4 Hs, 4 Ts and obstetric causes",
                timing = "During CPR",
                points = listOf(
                    "ERC 2025: address the 4Hs and 4Ts and seek the 4Ps — pre-eclampsia/eclampsia, puerperal sepsis, placental and uterine complications, peripartum cardiomyopathy.",
                    "AHA 2025 (A–H): Anaesthetic complications, Bleeding, Cardiovascular, Drugs, Embolic (amniotic fluid or pulmonary), Fever, General (Hs and Ts), Hypertension.",
                    "AOA India 2022 (ABCDE-UPS) adds uterine causes (accreta spectrum, abruption, rupture), pulmonary causes and sepsis.",
                    "ERC 2025: point-of-care ultrasound helps when concealed bleeding versus thromboembolism is unclear; consider ruptured ectopic pregnancy.",
                    "ERC 2025: high-risk PE causing arrest — consider thrombolysis or surgical embolectomy (expert opinion); after thrombolytics, continue CPR for at least 60–90 min. Peripartum cardiomyopathy — treat as acute heart failure; early echocardiography.",
                    "ERC 2025: puerperal sepsis — blood cultures and early broad-spectrum antibiotics; seek chorioamnionitis, retained products, endometritis, wound infection.",
                ),
                checklistTitle = "Reversible causes (ERC 2025 Table 13; AHA 2025; AOA India 2022)",
                checklist = listOf(
                    "Hypoxia — aspiration, difficult airway",
                    "Hypovolaemia — haemorrhage (PPH, abruption, praevia, rupture, ectopic), DIC; high regional block, anaphylaxis",
                    "Hypo-/hyperkalaemia and other electrolytes — check magnesium",
                    "Hypo-/hyperthermia",
                    "Thrombosis — pulmonary embolism, myocardial infarction, coronary or aortic dissection; amniotic fluid embolism",
                    "Tamponade; tension pneumothorax",
                    "Toxins — magnesium sulfate, local anaesthetic, opioids, medication error",
                    "Pre-eclampsia / eclampsia",
                    "Puerperal sepsis",
                    "Placental and uterine — abruption, praevia, accreta, atony, rupture, inversion",
                    "Peripartum cardiomyopathy",
                ),
                citations = listOf(
                    cite(ERC, "Reversible causes; Table 13; Cardiovascular disease; Pulmonary embolism — thrombolysis"),
                    cite(AHA, "Figure 5 — Etiologies of cardiac arrest (p. S622)"),
                    cite(AOA, "Advanced life support — ABCDE-UPS mnemonic"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Specific causes: magnesium, local anaesthetic, bleeding, eclampsia",
                timing = "As soon as suspected",
                points = listOf(
                    "AHA 2025: arrest on IV magnesium — stop it and give calcium (reasonable). ERC 2025: calcium chloride 10%, 10 mL IV, for magnesium overdose.",
                    "MoHFW STG: magnesium-toxicity arrest — CPR, stop MgSO4, IV calcium gluconate, intubate and ventilate; if antenatal, immediate delivery. AOA India 2022: 10 mL of 10% calcium gluconate or chloride, slow IV.",
                    "AHA 2025: local anaesthetic toxicity — give 20% lipid emulsion; benzodiazepines for seizures; bicarbonate for wide-complex tachycardia; ECLS for refractory shock.",
                    "QRH 2023: stop injecting; lipid 1.5 mL/kg over 2–3 min, repeated at 5 and 10 min if unstable.",
                    "QRH 2023: in arrest — adrenaline ≤1 µg/kg, avoid vasopressin; recovery may take >1 hour — consider cardiopulmonary bypass if available. Afterwards exclude pancreatitis (daily amylase or lipase).",
                    "ERC 2025: haemorrhage — activate the major obstetric haemorrhage protocol, prompt transfusion, tranexamic acid 1 g IV. See the Postpartum haemorrhage protocol.",
                    "ERC 2025: eclampsia — treat severe hypertension (160/110 mm Hg); magnesium sulfate 4 g IV over 5–15 min, then 1 g/h for 24 h. See Eclampsia protocol.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Lipid emulsion 20% — bolus (AHA 2025, weight <70 kg)",
                        dose = WeightDose(1.5, 1.5, unit = "mL", per = "IV bolus"),
                        note = "AHA 2025 Table 4: >70 kg — 100 mL over 2–3 min (cap shown). QRH 2023: 1.5 mL/kg over 2–3 min. Propofol is not a substitute.",
                        maxTotal = 100.0,
                    ),
                    StepDose(
                        label = "Lipid emulsion 20% — infusion (AHA 2025, weight <70 kg)",
                        dose = WeightDose(0.25, 0.25, unit = "mL", per = "per minute, for up to 30 min"),
                        note = "AHA 2025 Table 4: >70 kg — do not use the per-kg rate; give 200–250 mL over 15–20 min. Cap shown is the 70 kg rate (17.5 mL/min).",
                        maxTotal = 17.5,
                    ),
                    StepDose(
                        label = "Lipid emulsion 20% — infusion (QRH 2023)",
                        dose = WeightDose(15.0, 15.0, unit = "mL", per = "per hour"),
                        note = "QRH 2023: double to 30 mL/kg/h at any time after 5 min if circulation is not restored or deteriorates.",
                    ),
                    StepDose(
                        label = "Lipid emulsion 20% — maximum cumulative dose (QRH 2023)",
                        dose = WeightDose(12.0, 12.0, unit = "mL", per = "total of all boluses and infusion"),
                        note = "QRH 2023: do not exceed 12 mL/kg (70 kg: 840 mL). AOA India 2022: not exceeding 12 mL/kg.",
                    ),
                ),
                citations = listOf(
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 8; Figure 5; Toxicology: local anesthetics, Rec 1–4 (pp. S647–S648); Table 4 (p. S633)"),
                    cite(ERC, "Table 12; Table 2 (hypermagnesaemia); Pre-eclampsia and eclampsia; Haemorrhage"),
                    cite(MOH_STG, "Eclampsia — Magnesium toxicity (p. 93)"),
                    cite(AOA, "Drugs — lipid rescue; calcium gluconate/chloride"),
                    cite(QRH, "Steps 1–7; Box A; Box C"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Amniotic fluid embolism: recognise",
                timing = "Usually during labour or within 30 min of placental delivery (SMFM 2026); rarely in the first or second trimester, at termination or amniocentesis (SMFM 2016)",
                points = listOf(
                    "SMFM 2016: consider AFE in sudden cardiorespiratory collapse in a labouring or recently delivered woman (GRADE 1C); it remains a clinical diagnosis — no confirmatory laboratory test.",
                    "SMFM 2026: clinically diagnosed by arrest or hypotension with respiratory compromise, overt DIC and absence of fever.",
                    "SMFM 2016: typical triad — sudden hypoxia and hypotension, followed in many cases by coagulopathy. AHA 2025: DIC with haemorrhage is a hallmark.",
                    "SMFM 2016: differentials — myocardial infarction, pulmonary or air embolism, anaesthetic complications, anaphylaxis, eclampsia, sepsis. SMFM 2026 adds high spinal block.",
                    "SMFM 2016: do not diagnose AFE from haemorrhage due to persistent atony with secondary coagulopathy alone — a common diagnostic error.",
                ),
                citations = listOf(
                    cite(SMFM16, "Recommendations 1–2; timing of onset (p. B18); differential diagnoses; coagulopathy section (pp. B16–B22)"),
                    cite(SMFM26, "§1 Introduction; §2"),
                    cite(AHA, "Amniotic fluid embolism — synopsis (p. S626)"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Amniotic fluid embolism: manage",
                timing = "Immediately — supportive care",
                points = listOf(
                    "SMFM 2016: high-quality CPR with standard BLS/ACLS; after arrest, immediate delivery if fetus ≥23 weeks (GRADE 2C). SMFM 2026: deliver if no ROSC in 4 min.",
                    "AHA 2025: massive transfusion protocol with balanced transfusion, and tranexamic acid (both class 1). SMFM 2026: tranexamic acid 1 g IV over 10 min if DIC or haemorrhage.",
                    "SMFM 2026: cryoprecipitate preferred over FFP to limit volume overload; consider thromboelastometry; anticipate atony — oxytocin plus other uterotonics as needed.",
                    "SMFM 2016: avoid excessive fluid (GRADE 1C). SMFM 2026: e.g. 500 mL boluses and reassess; echocardiography; wean FiO₂ to SpO₂ 94–98%.",
                    "SMFM 2026: norepinephrine if needed; dobutamine or milrinone as inotropes; pulmonary vasodilator — inhaled nitric oxide 5–40 ppm, epoprostenol, or sildenafil 20 mg orally if awake.",
                    "SMFM 2016 Table 2: with inhaled nitric oxide, check methaemoglobin every 6 h and avoid abrupt discontinuation.",
                    "AHA 2025: VA-ECMO and inhaled pulmonary vasodilators reasonable; atropine without bradycardia not recommended. SMFM 2026: atropine–ondansetron–ketorolac evidence is case reports only.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Norepinephrine (SMFM 2026)",
                        dose = WeightDose(0.05, 3.3, unit = "mcg", per = "per minute, IV infusion"),
                        note = "Vasopressor if needed for hypotension; same range in SMFM 2016 Table 2.",
                    ),
                    StepDose(
                        label = "Dobutamine (SMFM 2026)",
                        dose = WeightDose(2.5, 5.0, unit = "mcg", per = "per minute, IV infusion"),
                        note = "SMFM 2016: higher doses may compromise right ventricular filling time through tachycardia.",
                    ),
                    StepDose(
                        label = "Milrinone (SMFM 2026)",
                        dose = WeightDose(0.25, 0.75, unit = "mcg", per = "per minute, IV infusion"),
                        note = "SMFM 2016: most common side effect is systemic hypotension.",
                    ),
                    StepDose(
                        label = "Epoprostenol, inhaled (SMFM 2026)",
                        dose = WeightDose(10.0, 50.0, unit = "ng", per = "per minute"),
                    ),
                    StepDose(
                        label = "Epoprostenol, IV (SMFM 2026)",
                        dose = WeightDose(1.0, 2.0, unit = "ng", per = "per minute, via central line"),
                        note = "SMFM 2016: start at 1–2 ng/kg/min and titrate; side effects include systemic hypotension.",
                    ),
                ),
                citations = listOf(
                    cite(SMFM16, "Recommendations 3, 5–7; Table 2 (p. B21)"),
                    cite(SMFM26, "Figure 1 (checklist); §2"),
                    cite(AHA, "Amniotic fluid embolism, Rec 1–5 (pp. S625–S627)"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Refractory arrest, ROSC and after",
                timing = "From ROSC — or if no ROSC with standard resuscitation",
                points = listOf(
                    "AHA 2025: ECPR is reasonable if unresponsive to standard resuscitation; ELSO suggests summoning ECPR resources after 10–15 min without ROSC.",
                    "AOA India 2022: ECMO can worsen thrombosis or coagulopathy — use caution with obstetric haemorrhage or DIC. ERC 2025: in exceptional cases ECPR may start immediately, with uterine evacuation postponed because of anticoagulation-related bleeding risk.",
                    "ERC 2025: post-resuscitation care as for other adults; stabilising the mother stabilises the fetus. Prepare for major obstetric haemorrhage after hysterotomy or thrombolysis.",
                    "AOA India 2022: if still undelivered, maintain uterine displacement; if ROSC before delivery, move to theatre with a team ready for caesarean.",
                    "AOA India 2022: after ROSC — surgical repair, haemostasis, antibiotics and intensive care. ERC 2025: involve obstetrics, anaesthesia, critical care and neonatology.",
                    "AOA India 2022: oxytocin is a vasodilator — high bolus doses (5–10 U) may cause cardiovascular collapse and re-arrest after ROSC; use it judiciously.",
                    "ERC 2025: hypothermic temperature control has been reported in isolated early-pregnancy cases with fetal heart rate monitoring, with no evidence of harm and favourable maternal and neonatal outcomes. AOA India 2022: recommends it, with caution in haemorrhage or coagulopathy.",
                    "ERC 2025 and AOA India 2022: debrief the team; offer psychological support to the woman, family and staff; document in real time.",
                ),
                citations = listOf(
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 6 and supportive text (p. S625)"),
                    cite(ERC, "Extracorporeal life support; Resuscitative hysterotomy; Maternal considerations for post resuscitation care; Table 12"),
                    cite(AOA, "Post cardiac arrest care — Recommendations; Drugs — uterotonic drugs; Important considerations"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Hysterotomy timing differs: AHA 2025 — prepare at arrest, delivery complete by 5 min; ERC 2025 — as soon as possible, fixed timing de-emphasised; " +
                    "SMFM 2026 and SOGC 2015 — 4 min; AOA India 2022 — after 5 min of CPR (abstract: 4–5 min).",
                listOf(
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 2"),
                    cite(ERC, "Resuscitative hysterotomy"),
                    cite(SMFM26, "Figure 1"),
                    cite(SOGC, "Rec 31"),
                    cite(AOA, "Abstract; Resuscitative uterine interventions"),
                ),
            ),
            ProtocolNote(
                "Gestation criterion differs: AHA 2025 — fundal height at or above the umbilicus; ERC 2025 — >20 weeks or fundus above umbilicus; AOA India 2022 — >20 weeks; " +
                    "SMFM 2016 — ≥23 weeks in AFE, noting no evidence that previable delivery improves AFE outcome.",
                listOf(
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 1 (p. S623)"), cite(ERC, "Table 12"),
                    cite(AOA, "Resuscitative uterine interventions"), cite(SMFM16, "Recommendation 5"),
                ),
            ),
            ProtocolNote(
                "Lipid emulsion regimens differ: AHA 2025 — <70 kg 1.5 mL/kg then 0.25 mL/kg/min up to 30 min; QRH 2023 — 1.5 mL/kg over 2–3 min, 15 mL/kg/h, max 12 mL/kg; " +
                    "AOA India 2022 — 1.5 mL/kg over 1 min, 15 mL/kg/h, repeat twice at 5-min intervals, max 12 mL/kg.",
                listOf(cite(AHA, "Table 4"), cite(QRH, "Box A"), cite(AOA, "Drugs — lipid rescue")),
            ),
            ProtocolNote(
                "Calcium for magnesium toxicity: AHA 2025 gives no dose; ERC 2025 — calcium chloride 10% 10 mL IV (Table 2: gluconate 10–30 mL or chloride 5–10 mL, repeat if needed); " +
                    "MoHFW STG — calcium gluconate 10 mL slow IV for respiratory arrest (strength not stated); for cardiac arrest, IV calcium gluconate with no dose given. " +
                    "AOA India 2022 — 10 mL of 10% calcium gluconate or chloride, slow IV.",
                listOf(
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 8 (p. S623)"), cite(ERC, "Table 12; Table 2"),
                    cite(MOH_STG, "Magnesium toxicity (p. 93)"), cite(AOA, "Drugs — calcium gluconate/chloride"),
                ),
            ),
            ProtocolNote(
                "WHO MCPC 2017 (lidocaine toxicity with cardiac arrest): hyperventilate with oxygen, cardiac massage, immediate caesarean if undelivered, adrenaline 0.5 mL of 1:10 000 IV — it does not mention lipid emulsion.",
                listOf(cite(WHO_MCPC, "Management of lidocaine toxicity — Cardiac arrest (p. C-61)")),
            ),
            ProtocolNote(
                "SMFM 2016: recombinant factor VIIa only as a last resort when bleeding persists despite massive replacement and surgery; no evidence supports routine steroids in AFE.",
                listOf(cite(SMFM16, "Management of coagulopathy; haemodynamic management")),
            ),
            ProtocolNote(
                "India: no MoHFW, ICMR STW or FOGSI guideline on maternal cardiac arrest was found (November 2026). AOA India 2022 is a professional-society consensus; MoHFW STG covers magnesium-toxicity arrest.",
                listOf(cite(AOA, "Introduction"), cite(MOH_STG, "Magnesium toxicity (p. 93)")),
            ),
            ProtocolNote(
                "RCOG Green-top Guideline 56 (second edition 2019; reviewed 2024, next review 2026) could not be retrieved in full for this page; its recommendations are not summarised here.",
                listOf(cite(GTG56, "RCOG guideline page — version history")),
            ),
        ),
        citations = listOf(
            cite(AHA), cite(ERC), cite(SMFM16), cite(SMFM26), cite(AOA), cite(QRH), cite(MOH_STG), cite(WHO_MCPC), cite(GTG56),
        ),
    ),

    // =============================================================================================
    // 2. Trauma in pregnancy
    // =============================================================================================
    Protocol(
        id = "trauma-in-pregnancy",
        category = ProtocolCategory.Obstetric,
        title = "Trauma in pregnancy",
        summary = "Primary-survey changes, uterine displacement, imaging, fetal monitoring duration, abruption and rupture, " +
            "anti-D and Kleihauer–Betke, tetanus and resuscitative hysterotomy — attributed to SOGC, EAST 2025, ACOG, " +
            "AHA/ERC 2025, WHO, MoHFW and CDC.",
        keywords = listOf(
            "trauma in pregnancy", "pregnant trauma", "injury in pregnancy", "RTA", "road traffic accident", "road accident",
            "fall in pregnancy", "assault", "domestic violence", "intimate partner violence", "blunt abdominal trauma",
            "abdominal injury", "placental abruption", "abruptio placentae", "accidental haemorrhage", "uterine rupture",
            "Kleihauer", "Kleihauer-Betke", "KB test", "fetomaternal haemorrhage", "anti-D", "Rh negative", "Rhoclone",
            "tetanus", "Td", "TT injection", "FAST", "CT in pregnancy", "radiation in pregnancy", "fetal monitoring",
            "CTG", "perimortem caesarean", "resuscitative hysterotomy", "chot", "satt", "durghatna", "accident", "maar-peet",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Primary survey: pregnancy changes",
                timing = "On arrival",
                points = listOf(
                    "SOGC 2015: consider every woman of reproductive age with significant injuries pregnant until a definitive pregnancy test or ultrasound proves otherwise.",
                    "EAST 2025: the best early treatment of the fetus is optimal resuscitation of the mother.",
                    "SOGC 2015: oxygen to keep maternal SpO₂ >95%; nasogastric tube in a semiconscious or unconscious woman to prevent aspiration.",
                    "AHA 2025: pregnancy raises oxygen consumption and lowers FRC, so hypoxaemia comes faster; airway oedema and aspiration risk make intubation harder.",
                    "SOGC 2015: if needed, insert a chest drain 1–2 intercostal spaces higher than usual.",
                    "SOGC 2015: major injury — trauma unit or emergency department at any gestation; injury neither life- nor limb-threatening with fetus ≥23 weeks — labour and delivery triage; " +
                        "if injury severity or gestational age is uncertain — trauma unit or emergency department first, to rule out major injury.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(SOGC, "Rec 1–4, 10–11"),
                    cite(EAST, "Abstract — Conclusion"),
                    cite(AHA, "Pregnant patients in cardiac arrest — synopsis; Rec 5 supportive text (pp. S623–S625)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Relieve aortocaval compression",
                timing = "From about 20 weeks (ERC 2025)",
                points = listOf(
                    "ERC 2025: aortocaval compression usually appears by 20 weeks; supine, it can reduce venous return and cardiac output, and may precipitate arrest in hypotensive women.",
                    "SOGC 2015: after mid-pregnancy, move the uterus off the IVC by manual displacement or left lateral tilt; protect the spine when tilting.",
                    "ERC 2025: about 30° tilt is needed to partly relieve IVC compression — on a tilting table, or a wedge under the right hip.",
                    "MoHFW HDU 2016: a 15° left lateral tilt avoids supine hypotension; with oxygen by mask it improves uteroplacental oxygen delivery.",
                    "AHA 2025: in cardiac arrest use manual left uterine displacement when fundal height is at or above the umbilicus.",
                ),
                citations = listOf(
                    cite(ERC, "Cardiac arrest in pregnancy — Aortocaval compression; Fig. 19"),
                    cite(SOGC, "Rec 7"),
                    cite(MOH_HDU, "Care for fetus in obstetric HDU/ICU (p. 22)"),
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 4"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Circulation: access, blood and clotting",
                timing = "Primary survey",
                points = listOf(
                    "SOGC 2015: two large-bore (14–16 gauge) IV lines in a seriously injured pregnant woman.",
                    "SOGC 2015: vasopressors only for intractable hypotension unresponsive to fluid resuscitation, because they reduce uteroplacental perfusion.",
                    "SOGC 2015: transfuse O-negative blood until cross-matched blood is available, to avoid Rh(D) alloimmunisation.",
                    "SOGC 2015: do not inflate the abdominal section of military anti-shock trousers.",
                    "SOGC 2015: add a coagulation panel including fibrinogen to routine bloods.",
                    "WHO MCPC 2017: bedside clotting test — no clot after 7 min, or a soft clot that breaks down easily, suggests coagulopathy.",
                ),
                citations = listOf(
                    cite(SOGC, "Rec 5, 6, 8, 9, 17"),
                    cite(WHO_MCPC, "Abruptio placentae (p. S-23)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Imaging and investigations",
                timing = "Do not delay indicated imaging",
                points = listOf(
                    "ACOG CO 723: with few exceptions, radiography, CT and nuclear medicine doses are far below those linked to fetal harm; if needed, do not withhold them.",
                    "ACOG CO 723: ultrasound and MRI are not associated with risk and are preferred — used when they answer a clinical question.",
                    "SOGC 2015: imaging for maternal evaluation, including abdominal CT, should not be deferred because of fetal radiation concerns.",
                    "EAST 2025: possible effects of ionising radiation should not prevent medically indicated diagnostic imaging.",
                    "SOGC 2015: consider FAST for intraperitoneal bleeding; abdominal CT may replace peritoneal lavage. Gadolinium only if maternal benefit outweighs fetal risk.",
                    "EAST 2025: cannot recommend for or against pregnancy-specific laboratory tests or non-ionising imaging in viable pregnancies.",
                ),
                citations = listOf(
                    cite(CO723, "Abstract"),
                    cite(SOGC, "Rec 15–16, 18–19"),
                    cite(EAST, "Abstract — Results"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Fetal assessment and monitoring",
                timing = "After maternal stabilisation; monitor ≥4 h (SOGC 2015) or 4–6 h (EAST 2025)",
                points = listOf(
                    "EAST 2025 (conditional): assess the fetus at the end of the primary survey, after a rapid maternal evaluation.",
                    "SOGC 2015: once the mother is stabilised, if ≥23 weeks start fetal heart auscultation and monitoring and obtain obstetric consultation.",
                    "SOGC 2015: electronic fetal monitoring for at least 4 h in every viable pregnancy (≥23 weeks). EAST 2025 (conditional): observe for at least 4–6 h.",
                    "SOGC 2015: admit for 24 h observation if any adverse factor in the checklist.",
                    "SOGC 2015: obstetric ultrasound before discharge if monitored >4 h; urgent ultrasound if gestation is unknown and delivery may be needed.",
                    "MoHFW HDU 2016: pregnancy with trauma is listed for obstetric HDU care, with continuous electronic fetal monitoring.",
                ),
                checklistTitle = "Admit for 24 h observation (SOGC 2015, ≥23 weeks) if",
                checklist = listOf(
                    "Uterine tenderness",
                    "Significant abdominal pain",
                    "Vaginal bleeding",
                    "Sustained contractions (>1 per 10 min)",
                    "Rupture of membranes",
                    "Atypical or abnormal fetal heart rate pattern",
                    "High-risk mechanism of injury",
                    "Serum fibrinogen <200 mg/dL",
                ),
                citations = listOf(
                    cite(EAST, "Abstract — Results"),
                    cite(SOGC, "Rec 12, 20–21, 24–25"),
                    cite(MOH_HDU, "Scope of obstetric HDU (p. 8); Care for fetus (p. 22)"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Placental abruption and uterine rupture",
                timing = "Suspect early; do not wait for ultrasound",
                points = listOf(
                    "SOGC 2015: do not delay management of suspected abruption awaiting ultrasound — it is not a sensitive test for abruption.",
                    "WHO MCPC 2017: abruption — bleeding after 22 weeks (may be concealed) with abdominal pain; tense or tender uterus, shock, fetal distress or absent heart sounds.",
                    "WHO MCPC 2017: rupture — intra-abdominal or vaginal bleeding, severe pain; abnormal uterine contour, easily felt fetal parts, absent fetal heart, rapid pulse.",
                    "WHO MCPC 2017: heavy bleeding from abruption — deliver as soon as possible and prepare for PPH. Rupture — restore volume, then laparotomy.",
                    "SOGC 2015: urgent obstetric consultation for contractions, suspected abruption or rupture at ≥23 weeks; with vaginal bleeding at or after 23 weeks, " +
                        "defer speculum or digital examination until praevia is excluded by a prior or current ultrasound.",
                ),
                citations = listOf(
                    cite(SOGC, "Rec 13–14, 27"),
                    cite(WHO_MCPC, "Table S-9 (p. S-22); Abruptio placentae (p. S-23); Ruptured uterus (pp. S-24–S-25)"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Rh(D)-negative: anti-D and Kleihauer–Betke",
                timing = "Ideally within 72 h (ACOG PB 181); later doses may still protect",
                points = listOf(
                    "SOGC 2015 and ACOG PB 181: give anti-D immunoglobulin to every Rh(D)-negative pregnant woman after (abdominal) trauma.",
                    "SOGC 2015 and ACOG PB 181: quantify fetomaternal haemorrhage, e.g. Kleihauer–Betke, to decide whether additional anti-D doses are needed.",
                    "EAST 2025: Kleihauer–Betke testing in Rh-negative patients to determine an appropriate anti-D dose.",
                    "ACOG PB 181: 300 micrograms of anti-D covers up to 30 mL of Rh(D)-positive fetal whole blood (15 mL fetal red cells).",
                    "ACOG PB 181: the ideal time to give anti-D is within 72 h of a potentially sensitising event.",
                    "ACOG PB 181: if 72 h have passed, it may still protect — partial to complete protection was seen when given as late as 13 days after exposure; some benefit has been suggested as late as 28 days postpartum.",
                ),
                citations = listOf(
                    cite(SOGC, "Rec 22–23"),
                    cite(PB181, "Box 1; Anti-D immune globulin; abdominal trauma question; missed-dose question (pp. e65–e66); Summary of recommendations"),
                    cite(EAST, "Abstract — Results"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Tetanus, violence and documentation",
                points = listOf(
                    "SOGC 2015: tetanus vaccination is safe in pregnancy and should be given when indicated.",
                    "CDC: vaccinate for any wound if history unknown or incomplete; complete series — clean minor wound if last dose ≥10 years, dirty or major ≥5 years.",
                    "CDC: tetanus immune globulin 250 IU IM for dirty or major wounds with unknown, absent or incomplete vaccination, HIV or severe immunodeficiency; never for clean minor wounds.",
                    "MoHFW Td: Td has replaced TT, including in pregnancy — 0.5 mL IM, upper arm; earlier TT doses remain valid.",
                    "SOGC 2015: ask every injured woman specifically about domestic or intimate partner violence.",
                    "SOGC 2015: document fetal well-being carefully in cases involving violence, especially for legal purposes.",
                ),
                citations = listOf(
                    cite(SOGC, "Rec 26, 28–29"),
                    cite(CDC_TET, "Wound assessment and care — vaccination and TIG"),
                    cite(MOH_TD, "UIP schedule after Td introduction; key messages"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Traumatic cardiac arrest: resuscitative hysterotomy",
                timing = "As soon as possible (EAST 2025); no later than 4 min when possible (SOGC 2015)",
                points = listOf(
                    "EAST 2025 (conditional): ≥20 weeks and undergoing resuscitative thoracotomy for traumatic arrest — resuscitative hysterotomy as soon as possible.",
                    "SOGC 2015: viable pregnancy (≥23 weeks) — caesarean no later than 4 min after maternal arrest, when possible, to aid resuscitation and fetal salvage.",
                    "AHA 2025: resuscitative delivery when fundal height is at or above the umbilicus without ROSC; aim to complete by 5 min, at the arrest site.",
                    "ERC 2025: prehospital — only with access to the patient and a trained clinician; otherwise prioritise time-critical transport to hospital.",
                    "Uterine displacement, airway and causes: see the Maternal collapse and cardiac arrest in pregnancy protocol (AHA 2025, ERC 2025).",
                ),
                citations = listOf(
                    cite(EAST, "Abstract — Results"),
                    cite(SOGC, "Rec 31"),
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 1–3; Figure 5"),
                    cite(ERC, "Resuscitative hysterotomy"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Gestation thresholds differ: SOGC 2015 uses 23 weeks (viability) for monitoring and perimortem caesarean; EAST 2025 uses ≥20 weeks for resuscitative hysterotomy; AHA 2025 uses fundal height.",
                listOf(cite(SOGC, "Rec 20, 31"), cite(EAST, "Abstract"), cite(AHA, "Pregnant patients in cardiac arrest, Rec 1 (p. S623)")),
            ),
            ProtocolNote(
                "Monitoring duration differs: SOGC 2015 — at least 4 h, 24 h if adverse factors; EAST 2025 — at least 4–6 h (conditional recommendation).",
                listOf(cite(SOGC, "Rec 20–21"), cite(EAST, "Abstract")),
            ),
            ProtocolNote(
                "Positioning: SOGC 2015 accepts manual displacement or left tilt; ERC 2025 notes about 30° is needed for partial relief; MoHFW HDU 2016 uses 15° tilt outside arrest; in arrest, manual displacement is preferred.",
                listOf(
                    cite(SOGC, "Rec 7"), cite(ERC, "Aortocaval compression"), cite(MOH_HDU, "p. 22"),
                    cite(AHA, "Pregnant patients in cardiac arrest, Rec 4 (p. S623)"),
                ),
            ),
            ProtocolNote(
                "India: no MoHFW, ICMR STW or FOGSI guideline on trauma in pregnancy was found (November 2026). Under the UIP, pregnant women receive Td, not Tdap.",
                listOf(cite(MOH_HDU, "Scope of obstetric HDU (p. 8)"), cite(MOH_TD, "UIP schedule after Td introduction")),
            ),
            ProtocolNote(
                "SOGC 2015 (prevention): emphasise wearing seatbelts properly at all times at antenatal visits; insufficient evidence to support disabling air bags.",
                listOf(cite(SOGC, "Rec 30; Summary statement")),
            ),
        ),
        citations = listOf(
            cite(SOGC), cite(EAST), cite(CO723), cite(PB181), cite(AHA), cite(ERC), cite(WHO_MCPC), cite(MOH_HDU), cite(MOH_TD), cite(CDC_TET),
        ),
    ),
)
