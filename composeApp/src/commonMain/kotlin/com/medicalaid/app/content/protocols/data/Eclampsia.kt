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
 * Eclampsia and severe pre-eclampsia / HELLP pathways. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them (checked against the full text,
 *   November 2026).
 * - Every point is attributed ("MoHFW Dakshata 2015: …", "WHO MCPC 2017: …", "NICE NG133: …", "ACOG PB 222: …").
 * - MoHFW locators are page numbers as printed in the PDF; WHO MCPC locators are its S-pages, boxes and tables;
 *   NICE locators are recommendation numbers; ACOG locators are section headings, boxes and tables.
 * - Reuses app-wide sources: mohfw-mo-handbook-2009, mohfw-dakshata-2015, mohfw-anc-2010 (SBA guidelines),
 *   icmr-stw-anc-2025, acog-pb222, acog-pb203, fda-label-magnesium-sulphate.
 */

internal val eclampsiaSources = listOf(
    Source(
        "ob-ecl-fogsi-hdp-2026", Authority.FOGSI, "FOGSI-GESTOSIS-ICOG Good Clinical Practice Recommendations, 3rd edition",
        "Hypertensive Disorders in Pregnancy (HDP)", "2026",
        "https://www.fogsi.org/",
        status = "3rd edition 2026 (replaces 2019); read from the published PDF supplied by the user, Jan 2026 print",
    ),
    Source(
        "ob-ecl-mohfw-stg-obg", Authority.MoHFW, "Standard Treatment Guidelines (Clinical Establishments Act series)",
        "Standard Treatment Guidelines: Obstetrics & Gynaecology — Hypertension in pregnancy/Preeclampsia; Eclampsia", "2017",
        "https://clinicalestablishments.mohfw.gov.in/sites/default/files/standard-treatment-guidelines/4571.pdf",
        status = "Undated document; year taken from the PDF (created October 2017)",
    ),
    Source(
        "ob-ecl-mohfw-hdu-2016", Authority.MoHFW, "Guidelines (Maternal Health Division)",
        "Guidelines for Obstetric HDU and ICU", "2016",
        "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/Guidelines_for_Obstetric_HDU_and_ICU.pdf",
    ),
    Source(
        "ob-ecl-who-pe-2011", Authority.WHO, "Guideline",
        "WHO recommendations for prevention and treatment of pre-eclampsia and eclampsia", "2011",
        "https://www.who.int/publications/i/item/9789241548335",
        status = "Recommendations 9–10 revalidated by WHO in 2018 (drug treatment for severe hypertension)",
    ),
    Source(
        "ob-ecl-who-severe-htn-2018", Authority.WHO, "Recommendations",
        "WHO recommendations: drug treatment for severe hypertension in pregnancy", "2018",
        "https://www.who.int/publications/i/item/9789241550437",
        status = "Revalidates the 2011 recommendations on antihypertensive drugs for severe hypertension",
    ),
    Source(
        "ob-ecl-who-mcpc-2017", Authority.WHO, "IMPAC manual, 2nd edition",
        "Managing complications in pregnancy and childbirth: a guide for midwives and doctors", "2017",
        "https://www.who.int/publications/i/item/9789241565493",
    ),
    Source(
        "ob-ecl-nice-ng133", Authority.NICE, "Guideline NG133",
        "Hypertension in pregnancy: diagnosis and management", "2019",
        "https://www.nice.org.uk/guidance/ng133",
        status = "Last updated 17 April 2023",
    ),
    Source(
        "ob-ecl-nice-ng25", Authority.NICE, "Guideline NG25",
        "Preterm labour and birth", "2015",
        "https://www.nice.org.uk/guidance/ng25",
        status = "Last updated 10 June 2022",
    ),
)

private const val MOH_MO = "mohfw-mo-handbook-2009"
private const val MOH_DAK = "mohfw-dakshata-2015"
private const val MOH_SBA = "mohfw-anc-2010"
private const val MOH_STG = "ob-ecl-mohfw-stg-obg"
private const val MOH_HDU = "ob-ecl-mohfw-hdu-2016"
private const val ICMR_ANC = "icmr-stw-anc-2025"
private const val WHO_PE = "ob-ecl-who-pe-2011"
private const val WHO_HTN = "ob-ecl-who-severe-htn-2018"
private const val WHO_MCPC = "ob-ecl-who-mcpc-2017"
private const val NICE_HTN = "ob-ecl-nice-ng133"
private const val NICE_PTB = "ob-ecl-nice-ng25"
private const val ACOG_222 = "acog-pb222"
private const val ACOG_203 = "acog-pb203"
private const val FDA_MG = "fda-label-magnesium-sulphate"
private const val FOGSI = "ob-ecl-fogsi-hdp-2026"

internal val eclampsiaProtocols = listOf(
    // =========================================================================================
    // Eclampsia
    // =========================================================================================
    Protocol(
        id = "eclampsia",
        category = ProtocolCategory.Obstetric,
        title = "Eclampsia",
        summary = "Care during the fit, magnesium sulfate (MoHFW Pritchard regimen; WHO, NICE and ACOG IV regimens), toxicity checks, " +
            "calcium gluconate, referral, delivery and postpartum fits — attributed to MoHFW, WHO, NICE and ACOG.",
        keywords = listOf(
            "eclampsia", "fits in pregnancy", "convulsion in pregnancy", "seizure in pregnancy", "postpartum fits",
            "postpartum eclampsia", "imminent eclampsia", "impending eclampsia", "MgSO4", "magnesium sulphate",
            "magnesium sulfate", "magsulf", "Pritchard", "Zuspan", "toxaemia", "toxemia", "PIH", "calcium gluconate",
            "eclampsia kit", "daura", "dora", "jhatke", "dandal", "mirgi", "behoshi",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "During the fit: immediate care",
                timing = "At once — call for help",
                points = listOf(
                    "WHO MCPC 2017: shout for help; left side; protect from injury without restraint; never leave her alone; oxygen 4–6 L/min, or ventilate if not breathing.",
                    "MoHFW MO handbook 2009: semi-prone, head to one side; soft mouth gag, oropharyngeal suction, oxygen 4–6 L/min. MoHFW SBA 2010: no gag during the convulsion.",
                    "ACOG PB 222: supportive care first — help, lateral position, prevent aspiration, oxygen, SpO₂. Most fits are self-limited; magnesium sulfate prevents recurrence rather than stopping the fit.",
                    "Diazepam — NICE NG133: not an alternative to magnesium sulfate. WHO 2011: magnesium sulfate in preference to other anticonvulsants. ACOG: only if MgSO4 is contraindicated or unavailable.",
                    "MoHFW SBA 2010 and WHO MCPC 2017: treat any convulsion in pregnancy or after birth as eclampsia until proved otherwise, while seeking other causes.",
                    "MoHFW STG: after the fit, 16–18 G IV line and bloods; ECG, BP and pulse oximetry; indwelling catheter with an intake–output chart.",
                ),
                checklistTitle = "During the fit",
                checklist = listOf(
                    "Help called; never left alone",
                    "Left lateral or semi-prone; not restrained",
                    "Mouth gag only after the fit; suction ready",
                    "Oxygen 4–6 L/min; SpO₂ monitored",
                    "Magnesium sulfate drawn up",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(WHO_MCPC, "General management (pp. S-49–S-50)"),
                    cite(MOH_MO, "Session 5b key points (p. 77); Case study 21 answers (p. 139)"),
                    cite(MOH_SBA, "Module II — Convulsions–Eclampsia (p. 79)"),
                    cite(ACOG_222, "What is the optimal treatment for eclampsia?; Seizure prophylaxis"),
                    cite(NICE_HTN, "Rec 1.8.5"),
                    cite(WHO_PE, "Recs 12–13"),
                    cite(MOH_STG, "Eclampsia — General measures (p. 92)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Magnesium sulfate: loading dose",
                timing = "As soon as airway and oxygenation are secured",
                points = listOf(
                    "MoHFW MO handbook 2009 (Pritchard), 14 g: 4 g IV as 20 mL of 20% over 5 min, then 10 g IM — 5 g of 50% deep into each buttock with 1 mL 2% lignocaine.",
                    "MoHFW Dakshata 2015: 50% ampoule = 1 g in 2 mL. For the IV dose, 8 mL (4 g) of 50% plus 12 mL normal saline, slowly over 5–10 min.",
                    "MoHFW STG: 4 g IV over 10–15 min (8 mL of 50% in 12 mL saline); maintenance IM with 1 mL 2% lidocaine 4-hourly, alternate buttocks.",
                    "WHO MCPC 2017: IM regimen as MoHFW, or IV regimen — 4 g IV, then 1 g/h, preferably by pump. WHO 2011 (Rec 14, strong): use a full regimen.",
                    "NICE NG133 (Collaborative Eclampsia Trial regimen): 4 g IV over 5–15 min, then an infusion of 1 g/h.",
                    "ACOG PB 222: 4–6 g IV over 20–30 min, then 1–2 g/h. If IV access is difficult: 10 g IM (5 g each buttock), then 5 g IM 4-hourly.",
                    "Contraindications — FDA label: myasthenia gravis (can precipitate myasthenic crisis), heart block or myocardial damage, diabetic coma. ACOG PB 222: where MgSO4 is contraindicated (myasthenia gravis, hypocalcaemia, moderate–severe renal failure, cardiac ischaemia, heart block, myocarditis), a benzodiazepine or phenytoin is justified (step 7).",
                ),
                citations = listOf(
                    cite(MOH_MO, "Session 5b key points (p. 77)"),
                    cite(MOH_DAK, "Presentation 1.10, slides 8–9 (p. 56)"),
                    cite(MOH_STG, "Treatment and prophylaxis of seizures (pp. 92–93)"),
                    cite(WHO_MCPC, "Box S-4 (p. S-59)"),
                    cite(WHO_PE, "Rec 14"),
                    cite(NICE_HTN, "Rec 1.8.4"),
                    cite(ACOG_222, "Seizure prophylaxis"),
                    cite(FDA_MG, "4 Contraindications; 5.6 Exacerbation of Myasthenia Gravis"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Peripheral facility: loading dose, then refer",
                timing = "Reach the FRU within 2 h of the first dose (MoHFW)",
                points = listOf(
                    "MoHFW SBA 2010 (ANM, LHV, staff nurse): first dose 10 g IM — 10 mL (5 g) of 50% deep into each buttock; do not repeat it.",
                    "MoHFW SBA 2010: reach the FRU preferably within 2 h of the first dose. MoHFW Dakshata 2015: write the dose given on the referral slip.",
                    "MoHFW SBA 2010: if birth is imminent, deliver after the first dose, then refer mother and baby. Never leave her alone; record vital signs.",
                    "MoHFW MO handbook 2009: full 14 g loading dose; not in labour or in early labour — refer to FRU; late labour — deliver, then refer.",
                    "MoHFW MO handbook 2009: phone ahead; accompany her semi-prone with gag, IV Ringer lactate, oxygen and spare MgSO4. MoHFW STG: transfer only once stabilised.",
                    "WHO 2011 (Rec 15, weak): where the full regimen cannot be given, give the loading dose, then transfer immediately to a higher-level facility.",
                ),
                citations = listOf(
                    cite(MOH_SBA, "Module II — first dose of MgSO4 (p. 80)"),
                    cite(MOH_DAK, "Session 1.10 key messages (p. 53); Presentation 1.10, slide 8 (p. 56)"),
                    cite(MOH_MO, "Session 5b key points (p. 77); Case study 21 answers (p. 139)"),
                    cite(MOH_STG, "Hypertension in pregnancy — Situation 1 (p. 88); Eclampsia (p. 92)"),
                    cite(WHO_PE, "Rec 15 and remark d"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Maintenance, duration and recurrent fits",
                timing = "Until 24 h after birth or the last fit, whichever is later",
                points = listOf(
                    "MoHFW: 5 g of 50% IM into alternate buttocks every 4 h, each dose only after checking knee jerk, respiratory rate and urine output.",
                    "MoHFW and WHO MCPC 2017: continue 24 h after delivery or the last fit, whichever is later. WHO: if started after birth, 24 h after the last fit.",
                    "NICE NG133: 1 g/h for 24 h, or 24 h after the last fit. ACOG PB 222: 1–2 g/h for 24 h after birth; for caesarean, start before and continue during surgery, though it prolongs non-depolarising muscle relaxants (FDA label: monitor the blockade, e.g. train-of-four).",
                    "Recurrent fit — MoHFW MO handbook 2009: 2 g (20%) IV over 5 min. WHO MCPC 2017: fits recurring after 15 min — 2 g of 50% IV over 5 min.",
                    "Recurrent fit — NICE NG133: 2–4 g IV over 5–15 min. ACOG PB 222 (proposed): 2–4 g IV over 5 min. MoHFW STG: 2 g (<70 kg) or 4 g (>70 kg) over 5–10 min.",
                    "FDA label (US IV premix in 5% dextrose): maximum 30–40 g per 24 h. The MoHFW/WHO MCPC Pritchard doses (14 g load, then 5 g 4-hourly) add up to 39 g in the first 24 h, more with a recurrent-fit bolus.",
                    "Duration — FDA label: not beyond 5–7 days. NICE NG133 (MHRA): use beyond 5–7 days linked to neonatal skeletal effects and hypocalcaemia.",
                ),
                citations = listOf(
                    cite(MOH_MO, "Session 5b key points (p. 77)"),
                    cite(MOH_DAK, "Presentation 1.10, slides 10 and 12 (p. 57)"),
                    cite(WHO_MCPC, "Box S-4 (p. S-59); Management after childbirth (p. S-70)"),
                    cite(NICE_HTN, "Rec 1.8.4 and footnote"),
                    cite(ACOG_222, "Seizure prophylaxis; Anesthesia considerations; What is the optimal treatment for eclampsia?"),
                    cite(MOH_STG, "Treatment and prophylaxis of seizures (p. 93)"),
                    cite(FDA_MG, "2.2 Recommended Dosage; 5.1 Fetal-Neonatal Toxicity; 7 Drug Interactions"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Monitoring, toxicity and antidote",
                timing = "Before every repeat dose; vital signs and reflexes hourly (WHO)",
                points = listOf(
                    "MoHFW MO handbook 2009: before each repeat dose — knee jerk present, respiratory rate >16/min, urine output >100 mL in the previous 4 h.",
                    "WHO MCPC 2017: withhold or delay if respiratory rate <16/min, patellar reflexes absent, or urine <30 mL/h over 4 h. Check vital signs and reflexes hourly.",
                    "MoHFW Dakshata 2015: with any toxicity sign, skipping the next dose is generally enough; the antidote is needed only for respiratory toxicity, which is rare.",
                    "ACOG PB 222: tendon reflexes are lost at 9 mg/dL (7 mEq/L), respiratory depression at 12 mg/dL (10 mEq/L), cardiac arrest at 30 mg/dL (25 mEq/L).",
                    "Calcium gluconate 10%, 10 mL (1 g) IV: WHO MCPC — over 3 min, with assisted ventilation, for respiratory arrest; ACOG — over 3 min plus IV furosemide; MoHFW Dakshata — over 10 min.",
                    "MoHFW STG: absent reflexes — stop until they return; respiratory arrest — stop, calcium gluconate 10 mL slow IV, intubate, ventilate; cardiac arrest — CPR, deliver if antenatal.",
                    "WHO MCPC 2017: keep the antidote ready (calcium gluconate 10%; bag and mask for respiratory arrest). WHO 2011: facilities using MgSO4 should have calcium gluconate available.",
                ),
                checklistTitle = "Withhold or delay the next dose if",
                checklist = listOf(
                    "Patellar (knee) reflex absent",
                    "Respiratory rate below 16/min",
                    "Urine below 30 mL/h over 4 h (WHO) or below 100 mL in 4 h (MoHFW STG)",
                ),
                citations = listOf(
                    cite(MOH_MO, "Session 5b key points (p. 77)"),
                    cite(WHO_MCPC, "General management (p. S-58); Box S-4 notes (p. S-59); Box S-5 (p. S-60)"),
                    cite(MOH_DAK, "Presentation 1.10, slides 11 and 14 (pp. 57–58)"),
                    cite(ACOG_222, "Seizure prophylaxis; Table 2"),
                    cite(MOH_STG, "Magnesium toxicity (p. 93)"),
                    cite(WHO_PE, "Applicability issues 1–3"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Renal impairment, oliguria and fluids",
                points = listOf(
                    "ACOG PB 222: creatinine 1.0–1.5 mg/dL or urine <30 mL/h for >4 h — give the 4–6 g load, then only 1 g/h; serum magnesium every 4 h.",
                    "ACOG PB 222: serum magnesium >9.6 mg/dL (8 mEq/L) — stop the infusion, recheck 2-hourly, restart at a lower rate below 8.4 mg/dL (7 mEq/L).",
                    "FDA label: severe renal impairment or urine <0.5 mL/kg/h — 4 g load, then 1 g/h titrated to serum levels; maximum 20 g in 48 h.",
                    "WHO MCPC 2017: urine <30 mL/h — withhold magnesium sulfate, infuse normal saline or Ringer lactate 1 L over 8 h, and watch for pulmonary oedema.",
                    "MoHFW STG: maintenance crystalloid 85 mL/h (or last hour's urine + 30 mL), counting the MgSO4 volume. MoHFW Dakshata 2015: Ringer lactate 30 mL/h with MgSO4.",
                    "NICE NG133 (severe pre-eclampsia): limit maintenance fluids to 80 mL/h unless ongoing losses. WHO MCPC 2017: crackles — withhold fluids, furosemide 40 mg IV once.",
                ),
                citations = listOf(
                    cite(ACOG_222, "Seizure prophylaxis (renal dysfunction)"),
                    cite(FDA_MG, "2.3 Severe Renal Impairment and/or Oliguria"),
                    cite(WHO_MCPC, "General management (p. S-58)"),
                    cite(MOH_STG, "Treatment and prophylaxis of seizures (p. 93); Fluid therapy (p. 94)"),
                    cite(MOH_DAK, "Presentation 1.10, slides 8–14 (pp. 56–58)"),
                    cite(NICE_HTN, "Rec 1.8.12"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "If fits continue: other anticonvulsants and imaging",
                points = listOf(
                    "WHO 2011 (Recs 12–13, strong): magnesium sulfate in preference to other anticonvulsants. NICE NG133: do not use diazepam, phenytoin or others as an alternative.",
                    "ACOG PB 222: benzodiazepines or phenytoin only if MgSO4 is contraindicated or unavailable — myasthenia gravis, hypocalcaemia, moderate–severe renal failure, cardiac ischaemia, heart block, myocarditis.",
                    "ACOG PB 222: for extreme agitation only, IV clonazepam 1 mg, diazepam 10 mg or midazolam — cautiously, as they risk aspiration and apnoea.",
                    "ACOG PB 222, refractory (seizing 20 min after a bolus, or >2 recurrences): sodium amobarbital 250 mg IV over 3 min, thiopental, or phenytoin 1,250 mg IV at 50 mg/min.",
                    "MoHFW STG: fits despite a further MgSO4 bolus — diazepam 10 mg IV (1 mg/min) or thiopentone 50 mg IV; intubation and ventilation may be needed.",
                    "ACOG PB 222: intubate and ventilate in ICU; consider head imaging, often abnormal in refractory cases. MoHFW STG: CT or MRI if focal deficit or prolonged coma.",
                ),
                checklistTitle = "Look for another cause if",
                checklist = listOf(
                    "New seizure more than 48–72 h after birth (ACOG)",
                    "Seizure during magnesium sulfate therapy (ACOG)",
                    "Refractory or repeated seizures (ACOG)",
                    "Focal neurological deficit or prolonged coma (MoHFW STG)",
                    "Coma lasting more than 24 h after a convulsion — consider tertiary referral (WHO MCPC)",
                ),
                doses = listOf(
                    StepDose(
                        label = "Phenytoin — MoHFW STG (other anticonvulsant)",
                        dose = WeightDose(20.0, 20.0, unit = "mg", per = "IV loading dose"),
                        note = "MoHFW STG: in 100 mL saline at no more than 50 mg/min — its '15–20 min' holds only up to 50 kg (20 mg/kg at 50 mg/min takes ≥0.4 min/kg: ≥24 min at 60 kg, ≥28 min at 70 kg); then 100 mg IV 8-hourly; ECG monitoring. WHO MCPC 2017: never exceed 50 mg/min. NICE NG133: not an alternative to MgSO4.",
                    ),
                ),
                citations = listOf(
                    cite(WHO_PE, "Recs 12–13"),
                    cite(NICE_HTN, "Rec 1.8.5"),
                    cite(ACOG_222, "Seizure prophylaxis; Eclampsia; What is the optimal treatment for eclampsia?"),
                    cite(MOH_STG, "Treatment and prophylaxis of seizures; Other anticonvulsant drugs (p. 93); Laboratory workup (p. 94)"),
                    cite(WHO_MCPC, "Referral for tertiary level care (p. S-66); Epilepsy (p. S-69)"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Blood pressure, then delivery after stabilisation",
                timing = "Birth within 12 h of onset of convulsions (WHO MCPC)",
                points = listOf(
                    "WHO MCPC 2017 and ACOG PB 222: treat ≥160 systolic or ≥110 diastolic (see Severe pre-eclampsia). MoHFW STG: BP every 10 min; reduce diastolic to 90–100.",
                    "WHO MCPC 2017: birth within 12 h of onset of convulsions. MoHFW Dakshata 2015: terminate within 12 h in all eclampsia; if already in labour, let it progress.",
                    "ACOG PB 222 and MoHFW STG: deliver only after the mother is stabilised, even with fetal distress; the fetal heart rate usually recovers after maternal resuscitation (ACOG).",
                    "ACOG PB 222: eclampsia alone is not an indication for caesarean; below 30 weeks, not in labour, with an unfavourable Bishop score, caesarean may be preferable.",
                    "WHO MCPC 2017 (term): caesarean if vaginal birth is not expected within 12 h; exclude coagulopathy first; do not use local anaesthesia or ketamine.",
                    "WHO MCPC 2017: do not give ergometrine in pre-eclampsia or eclampsia — it raises BP and stroke or convulsion risk. MoHFW STG: Methergine contraindicated for the third stage.",
                ),
                scoreIds = listOf("bishop"),
                citations = listOf(
                    cite(WHO_MCPC, "p. S-55; Severe pre-eclampsia and eclampsia (p. S-57); Antihypertensive medications (p. S-60); Optimal timing for birth (pp. S-62, S-65–S-66)"),
                    cite(ACOG_222, "Antihypertensive approach; What is the optimal treatment for eclampsia?"),
                    cite(MOH_STG, "Intrapartum care (p. 91); Treatment of hypertension; Monitoring (pp. 93–94); Delivery (p. 95)"),
                    cite(MOH_DAK, "Presentation 1.10, slides 15–16 (pp. 58–59)"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Postpartum eclampsia and care after birth",
                timing = "Fits can begin after delivery",
                points = listOf(
                    "MoHFW MO handbook 2009: eclampsia occurs in pregnancy, labour or within 7 days of delivery. MoHFW STG: seizures may occur up to 3 weeks postpartum.",
                    "WHO MCPC 2017: about 25% of eclamptic convulsions occur after childbirth; some women first present after birth — monitor BP in all postpartum women.",
                    "ACOG PB 222: new seizures after 48–72 h postpartum, or during magnesium therapy, are more likely to have another cause — investigate.",
                    "WHO MCPC 2017: continue antihypertensives started before birth; start if ≥160/110 after birth. MoHFW STG: high-dependency care for at least 24 h after delivery.",
                    "ACOG PB 222: most postpartum eclampsia or stroke follows hours or days of symptoms. WHO MCPC 2017: BP may rise on days 3–7; review 1–2 weeks after discharge.",
                    "WHO MCPC 2017: tertiary referral if oliguria persists 48 h after birth, HELLP or uncontrolled hypertension; counsel on recurrence, contraception and cardiovascular risk.",
                ),
                citations = listOf(
                    cite(MOH_MO, "Session 5b notes (p. 76)"),
                    cite(MOH_STG, "Eclampsia — incidence (p. 92); Delivery and post partum period (p. 95)"),
                    cite(WHO_MCPC, "p. S-55; Referral for tertiary level care (p. S-66); Postpartum care (pp. S-70–S-71)"),
                    cite(ACOG_222, "Eclampsia; Postpartum hypertension and postpartum headache"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "FOGSI 2026 (India): Pritchard regimen preferred, Zuspan acceptable; 50% w/v ampoules (1 g/2 mL). Pritchard maintenance " +
                    "5 g deep IM 4-hourly only if respiratory rate >16/min, patellar reflexes present and urine >100 mL in 4 h; Zuspan 1 g/h IV " +
                    "with urine >30 mL/h. Fit not controlled after 15 min: 2 g slow IV over 10 min. Urine <100 mL in 4 h with no other toxicity " +
                    "signs: reduce the next IM dose to 2.5 g or the infusion to 0.5 g/h. Respiratory depression: stop MgSO4, calcium gluconate " +
                    "10 mL of 10% over 10 min. Continue 24 h after delivery or the last fit, whichever is later; routine serum levels not required.",
                listOf(cite(FOGSI, "Section 10.4, Magnesium sulphate (pp. 25–26)")),
            ),
            ProtocolNote(
                "FOGSI 2026 (India): recurrent fits despite MgSO4 — IV thiopentone 0.5 g in 20 mL 5% dextrose by the anaesthetist, then " +
                    "intubation and ventilation if needed; alternatively IV levetiracetam 20–60 mg/kg over 15 min (max 3,000–4,500 mg), " +
                    "maintenance 500–1,500 mg 12-hourly, continuing MgSO4 and involving a neurologist. Before referral: Pritchard loading dose " +
                    "(noted in writing), nifedipine 20 mg slow release or labetalol 200 mg orally (no sublingual nifedipine), first corticosteroid " +
                    "dose below 34 weeks. After delivery: BP every 4–6 h for 48 h; avoid NSAIDs; treat persistent BP above 140/90.",
                listOf(
                    cite(FOGSI, "Section 10.5, recurrent convulsions (pp. 26–27)"),
                    cite(FOGSI, "Sections 10.9 and 10.13, post-delivery care and referral checklist (pp. 27–28)"),
                ),
            ),
            ProtocolNote(
                "Regimens differ: MoHFW and WHO use the Pritchard IM regimen after an IV load; WHO and NICE also give 4 g IV then 1 g/h; ACOG gives 4–6 g IV then 1–2 g/h. All continue for 24 h.",
                listOf(cite(MOH_MO, "p. 77"), cite(WHO_MCPC, "Box S-4"), cite(NICE_HTN, "Rec 1.8.4"), cite(ACOG_222, "Seizure prophylaxis")),
            ),
            ProtocolNote(
                "Urine thresholds before a repeat dose differ: MoHFW MO handbook >100 mL in 4 h; MoHFW STG >25 mL/h (withhold if <100 mL in 4 h); WHO ≥30 mL/h over 4 h; MoHFW Dakshata lists <25–30 mL/h as toxicity.",
                listOf(cite(MOH_MO, "p. 77"), cite(MOH_STG, "p. 93"), cite(WHO_MCPC, "Box S-5"), cite(MOH_DAK, "Slide 11 (p. 57)")),
            ),
            ProtocolNote(
                "Calcium gluconate: all give 10 mL of 10% (1 g) IV; WHO MCPC and ACOG over 3 min, MoHFW Dakshata over 10 min ('10-10-10').",
                listOf(cite(WHO_MCPC, "Box S-5"), cite(ACOG_222, "Seizure prophylaxis"), cite(MOH_DAK, "Slides 11, 14 (pp. 57–58)")),
            ),
            ProtocolNote(
                "Recurrent-fit dose: 2 g (MoHFW MO handbook, WHO MCPC) versus 2–4 g (NICE, ACOG); MoHFW STG uses 2 g below 70 kg and 4 g above.",
                listOf(cite(MOH_MO, "p. 77"), cite(WHO_MCPC, "Box S-4"), cite(NICE_HTN, "Rec 1.8.4"), cite(ACOG_222, "What is the optimal treatment for eclampsia?"), cite(MOH_STG, "p. 93")),
            ),
            ProtocolNote(
                "Diazepam: NICE NG133 — never an alternative to MgSO4; ACOG — only if MgSO4 is contraindicated or unavailable; MoHFW STG — diazepam 10 mg or thiopentone 50 mg IV if fits persist despite MgSO4.",
                listOf(cite(NICE_HTN, "Rec 1.8.5"), cite(ACOG_222, "Seizure prophylaxis"), cite(MOH_STG, "p. 93")),
            ),
            ProtocolNote(
                "Evidence (WHO 2011): versus diazepam in eclampsia, magnesium sulfate reduced maternal death (RR 0.59) and recurrent convulsions (RR 0.43) across 7 trials (1,396 women).",
                listOf(cite(WHO_PE, "Evidence for Recs 12–15 — magnesium sulfate versus diazepam")),
            ),
            ProtocolNote(
                "MoHFW MO handbook 2009 advises nifedipine 5 mg sublingually for BP >160/110 during referral; ACOG PB 203 says nifedipine capsules must be swallowed, not punctured or given sublingually.",
                listOf(cite(MOH_MO, "p. 77; Case study 21 answers (p. 139)"), cite(ACOG_203, "Box 4 footnote")),
            ),
            ProtocolNote(
                "Fluid rates differ: MoHFW MO handbook — Ringer lactate 60 mL/h; MoHFW Dakshata — 30 mL/h with MgSO4; MoHFW STG — 85 mL/h; NICE NG133 — limit to 80 mL/h in severe pre-eclampsia.",
                listOf(cite(MOH_MO, "p. 77"), cite(MOH_DAK, "Slides 8–14"), cite(MOH_STG, "p. 94"), cite(NICE_HTN, "Rec 1.8.12")),
            ),
            ProtocolNote(
                "MoHFW STG lists ampicillin 500 mg IV 6-hourly 'to prevent infection' in eclampsia; the WHO MCPC, NICE NG133 and ACOG PB 222 eclampsia sections do not include routine antibiotics.",
                listOf(cite(MOH_STG, "Antibiotics (p. 94)"), cite(WHO_MCPC, "pp. S-57–S-60"), cite(NICE_HTN, "Section 1.8"), cite(ACOG_222, "What is the optimal treatment for eclampsia?")),
            ),
            ProtocolNote(
                "Evidence gap (ACOG PB 222): the effective serum magnesium level is not established; with BMI >35, levels may stay subtherapeutic up to 18 h on 4.5 g then 1.8 g/h; rates >2 g/h were linked to perinatal mortality in tocolysis trials.",
                listOf(cite(ACOG_222, "Seizure prophylaxis")),
            ),
            ProtocolNote(
                "Shorter regimens: WHO 2011 reviewed trials of short versus 24-hour postpartum regimens (398 women, no eclampsia in either arm) but recommends full regimens with 24-hour maintenance.",
                listOf(cite(WHO_PE, "Evidence for Recs 12–15; remark c")),
            ),
            ProtocolNote(
                "Place of care: MoHFW obstetric HDU guidelines list eclampsia, severe pre-eclampsia and HELLP for HDU care, with observations at least hourly in the acute phase; NICE NG133 — level 2 care for eclampsia.",
                listOf(cite(MOH_HDU, "Scope of obstetric HDU (p. 8); Maternal observation (p. 21)"), cite(NICE_HTN, "Table 4")),
            ),
            ProtocolNote(
                "Scope: MoHFW documents used here date from 2009 to about 2017; international sources are WHO 2011 and 2017, NICE NG133 (updated 2023) and ACOG PB 222 (2020).",
                listOf(cite(MOH_MO), cite(MOH_DAK), cite(MOH_SBA), cite(MOH_STG), cite(MOH_HDU), cite(WHO_PE), cite(WHO_MCPC), cite(NICE_HTN), cite(ACOG_222)),
            ),
        ),
        citations = listOf(
            cite(MOH_MO), cite(MOH_DAK), cite(MOH_SBA), cite(MOH_STG), cite(MOH_HDU), cite(WHO_PE), cite(WHO_MCPC),
            cite(NICE_HTN), cite(ACOG_222), cite(FDA_MG),
        ),
    ),

    // =========================================================================================
    // Severe pre-eclampsia & HELLP
    // =========================================================================================
    Protocol(
        id = "severe-pre-eclampsia",
        category = ProtocolCategory.Obstetric,
        title = "Severe pre-eclampsia & HELLP",
        summary = "Severe features and HELLP criteria, severe-range BP treatment (labetalol, nifedipine, hydralazine), " +
            "magnesium prophylaxis, fluids, timing of birth, steroids and postpartum care — attributed to MoHFW, WHO, NICE and ACOG.",
        keywords = listOf(
            "severe pre-eclampsia", "severe preeclampsia", "preeclampsia with severe features", "pre-eclampsia", "HELLP",
            "hypertensive emergency in pregnancy", "severe hypertension in pregnancy", "hypertensive crisis", "high BP in pregnancy",
            "PIH", "toxaemia", "labetalol", "nifedipine", "hydralazine", "MgSO4", "magnesium sulphate",
            "antenatal corticosteroids", "dexamethasone", "betamethasone", "Lobet", "Depin", "Calcigard", "Nicardia",
            "Dexona", "Betnesol", "uchch raktchaap", "soojan",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise severe features and HELLP",
                timing = "At diagnosis",
                points = listOf(
                    "ACOG PB 222: severe features are listed below; severe-range BP may be confirmed within minutes rather than 4 h so that treatment is not delayed.",
                    "WHO MCPC 2017: severe if ≥160/110 with 2+ proteinuria, or any one sign — severe headache, visual change, oliguria <400 mL/24 h, upper abdominal pain, breathlessness, vomiting, hyperreflexia.",
                    "NICE NG133: severe hypertension not responding to treatment, or with severe headache, scotomata, vomiting, epigastric pain, oliguria, worsening blood tests, or poor fetal growth.",
                    "MoHFW Dakshata 2015: ≥160/110 with 3+–4+ proteinuria; or 140/90 to <160/110 with trace–2+ plus headache, blurred vision, epigastric pain, oliguria, pulmonary or abnormal oedema.",
                    "MoHFW STG: also platelets <100,000/mm³, raised LDH, SGOT >70 IU/L, creatinine >1.2 mg/dL, urine <400 mL/24 h, abruption or IUGR.",
                    "ACOG PB 222, HELLP: LDH ≥600 IU/L, AST and ALT >2× upper limit, platelets <100 × 10⁹/L; up to 15% lack hypertension or proteinuria; in 30% it is first expressed or progresses postpartum.",
                ),
                checklistTitle = "Severe features — ACOG PB 222 Box 3",
                checklist = listOf(
                    "Systolic ≥160 or diastolic ≥110 mm Hg (two readings 4 h apart, unless treatment starts sooner)",
                    "Platelets <100,000/µL (100 × 10⁹/L)",
                    "Liver enzymes >2× upper limit, or severe persistent RUQ/epigastric pain unresponsive to medication",
                    "Creatinine >1.1 mg/dL or doubled, without other renal disease",
                    "Pulmonary oedema",
                    "New headache unresponsive to medication and not otherwise explained",
                    "Visual disturbances",
                ),
                citations = listOf(
                    cite(ACOG_222, "Box 3; Gestational hypertension; HELLP syndrome"),
                    cite(WHO_MCPC, "Table S-12 and footnote a (pp. S-52–S-54)"),
                    cite(NICE_HTN, "Terms used in this guideline"),
                    cite(MOH_DAK, "Presentation 1.10, slide 4 (p. 55)"),
                    cite(MOH_STG, "Diagnosis of severe preeclampsia (p. 89)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Severe-range BP: confirm and treat fast",
                timing = "Within 30–60 min of confirmed severe BP (ACOG)",
                points = listOf(
                    "ACOG PB 222: systolic ≥160 or diastolic ≥110 persisting ≥15 min — treat as soon as reasonably possible; the literature suggests within 30–60 min.",
                    "NICE NG133: severe hypertension — admit, treat all, BP every 15–30 min until <160/110; in critical care treat immediately with labetalol, oral nifedipine or IV hydralazine.",
                    "WHO 2018 (revalidating WHO 2011): treat severe hypertension; choose the drug by clinician experience, cost and local availability. WHO MCPC 2017: start at ≥160 and/or ≥110.",
                    "MoHFW Dakshata 2015: antihypertensives if diastolic >100; in severe pre-eclampsia, tablet nifedipine or injection labetalol for initial control; keep diastolic 90–100.",
                    "Targets once treated — NICE NG133: 135/85 or less. MoHFW STG: diastolic 80–100 and systolic below 150. MoHFW MO handbook (eclampsia): diastolic below 90.",
                    "ACOG PB 203: avoid overcorrection — diastolic below 80 mm Hg may cause fetal heart rate abnormalities. WHO MCPC 2017: keep BP above the lower limits of normal.",
                ),
                citations = listOf(
                    cite(ACOG_222, "Antihypertensive approach; Summary of recommendations (Level B)"),
                    cite(NICE_HTN, "Table 2; Rec 1.8.6"),
                    cite(WHO_HTN, "Table 1, Recs 1–2"),
                    cite(WHO_MCPC, "General management (p. S-58); Antihypertensive medications (p. S-60)"),
                    cite(MOH_DAK, "Presentation 1.10, slide 6 (p. 55)"),
                    cite(MOH_STG, "Antihypertensive therapy (p. 90)"),
                    cite(MOH_MO, "Case study 21 answers (p. 139)"),
                    cite(ACOG_203, "Acute treatment — overzealous correction"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "IV labetalol",
                timing = "Recheck BP 10 min after each dose (ACOG order set)",
                points = listOf(
                    "ACOG PB 203 order set: 20 mg IV over 2 min, then 40 mg, then 80 mg at 10-min BP checks; then hydralazine 10 mg IV; then emergency consultation.",
                    "ACOG PB 222 Table 3: 10–20 mg IV, then 20–80 mg every 10–30 min to 300 mg cumulative; or infusion 1–2 mg/min. ACOG PB 203: first bolus no more than 20 mg.",
                    "WHO MCPC 2017: 10 mg IV; if inadequate after 10 min, 20 mg; then 40 mg and 80 mg at 10-min intervals; maximum 300 mg, then switch to oral.",
                    "MoHFW STG: 20 mg IV; if diastolic >110 after 20 min, 40 mg, then 80 mg and 80 mg to a total of 220 mg; no response — senior and anaesthetic review.",
                    "ACOG PB 203: once controlled, BP every 10 min for 1 h, every 15 min for 1 h, every 30 min for 1 h, then hourly for 4 h.",
                    "WHO MCPC 2017, no IV route: oral labetalol 200 mg, repeated after 1 h until goal; maximum 1,200 mg/24 h. ACOG PB 222: oral maintenance up to 2,400 mg/day.",
                ),
                checklistTitle = "Avoid labetalol in",
                checklist = listOf(
                    "Asthma or predisposition to bronchospasm (ACOG; WHO)",
                    "Heart block or bradycardia (ACOG)",
                    "Pre-existing myocardial disease or decompensated cardiac function (ACOG); congestive heart failure (WHO)",
                    "Hypovolaemic shock (WHO)",
                ),
                citations = listOf(
                    cite(ACOG_203, "Box 6; Labetalol"),
                    cite(ACOG_222, "Table 3; Antihypertensive approach"),
                    cite(WHO_MCPC, "Table S-13 (p. S-61)"),
                    cite(MOH_STG, "Antihypertensive therapy (p. 90); Treatment of hypertension (p. 94)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Oral nifedipine (immediate-release) and IV hydralazine",
                timing = "Recheck BP 20 min after each dose (ACOG order sets)",
                points = listOf(
                    "ACOG PB 203 order set: nifedipine IR 10 mg orally; at 20 min 20 mg; at 20 min 20 mg; at 20 min labetalol 20 mg IV and emergency consultation.",
                    "ACOG PB 203: swallow capsules — never puncture or give sublingually. ACOG PB 222 Table 3: 10–20 mg, repeat in 20 min, then 10–20 mg 2–6-hourly; maximum 180 mg/day.",
                    "WHO MCPC 2017: nifedipine IR 5–10 mg orally, repeat after 30 min if needed; maximum 30 mg acutely; use another option if not lowered within 90 min.",
                    "ACOG PB 203 order set: hydralazine 5 or 10 mg IV over 2 min; at 20 min 10 mg; at 20 min labetalol 20 mg; at 10 min labetalol 40 mg and consultation.",
                    "Hydralazine — ACOG PB 222: 5 mg IV or IM, then 5–10 mg IV every 20–40 min to 20 mg cumulative (or 0.5–10 mg/h infusion). WHO MCPC 2017: 5 mg IV slowly every 5 min, or 12.5 mg IM 2-hourly; maximum 20 mg/24 h.",
                    "Nifedipine with MgSO4 — ACOG PB 203: monitor maternal BP and heart rate as in the order set. FDA magnesium label: exaggerated hypotension possible with dihydropyridine calcium-channel blockers. WHO 2018: no evidence of an adverse interaction.",
                    "Refractory — ACOG PB 203: switch class at an agent's maximum; urgent specialist consultation; nicardipine or esmolol infusion second-line. MoHFW STG: nitroglycerine infusion 5–100 µg/min.",
                ),
                citations = listOf(
                    cite(ACOG_203, "Boxes 4–5 (Box 4 footnote †); Labetalol; Hydralazine; Nifedipine"),
                    cite(ACOG_222, "Table 3"),
                    cite(WHO_MCPC, "Table S-13 and footnote a (p. S-61)"),
                    cite(MOH_STG, "Treatment of hypertension (p. 94)"),
                    cite(FDA_MG, "7 Drug Interactions"),
                    cite(WHO_HTN, "Table 1, Rec 2 remarks"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Magnesium sulfate prophylaxis",
                timing = "With severe features; continue 24 h after birth",
                points = listOf(
                    "ACOG PB 222 (Level A): magnesium sulfate for gestational hypertension or pre-eclampsia with severe features, and eclampsia; without severe features, the physician or institution decides.",
                    "WHO 2011 (Rec 12, strong): magnesium sulfate for severe pre-eclampsia in preference to other anticonvulsants; the guideline group was divided on mild pre-eclampsia.",
                    "NICE NG133: consider IV MgSO4 in severe pre-eclampsia if birth is planned within 24 h, or with severe headache, scotomata, vomiting, epigastric pain, oliguria with severe hypertension, or worsening bloods.",
                    "MoHFW Dakshata 2015: MgSO4 for severe pre-eclampsia and eclampsia, not for pre-eclampsia without severe features. MoHFW SBA 2010: diastolic ≥110 with 3+ proteinuria — 10 g IM, refer.",
                    "WHO MCPC 2017 (expectant care): stop magnesium sulfate after 24 h if not worsening, with no premonitory signs and no persistent severe signs.",
                    "Doses, monitoring and antidote: as in the Eclampsia protocol (MoHFW Pritchard regimen; WHO, NICE and ACOG IV regimens).",
                ),
                citations = listOf(
                    cite(ACOG_222, "Seizure prophylaxis; Summary of recommendations (Level A)"),
                    cite(WHO_PE, "Rec 12, remark b"),
                    cite(NICE_HTN, "Recs 1.8.2–1.8.3"),
                    cite(MOH_DAK, "Presentation 1.10, slide 7 (p. 56)"),
                    cite(MOH_SBA, "Table 2, row E2 (p. 37)"),
                    cite(WHO_MCPC, "Box S-4 (p. S-59); Gestation 24–34 and 34–36+6 weeks (pp. S-64–S-65)"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Fluids and pulmonary oedema",
                points = listOf(
                    "NICE NG133: limit maintenance fluids to 80 mL/h unless ongoing losses; no volume expansion unless hydralazine is the antenatal antihypertensive.",
                    "NICE NG133: consider up to 500 mL crystalloid before or with the first antenatal IV hydralazine dose; do not preload before low-dose epidural.",
                    "MoHFW STG: maintenance crystalloid 85 mL/h or last hour's urine + 30 mL, with a strict intake–output chart; diuretics if pulmonary oedema is suspected.",
                    "ACOG PB 222: vigorous fluid therapy is ineffective and dangerous — capillary leak and low oncotic pressure raise wedge pressure and pulmonary oedema risk.",
                    "WHO MCPC 2017: catheterise and keep a fluid balance chart; urine <30 mL/h — withhold MgSO4 and give 1 L normal saline or Ringer lactate over 8 h.",
                    "WHO MCPC 2017: auscultate lung bases hourly; crackles — withhold fluids and give furosemide 40 mg IV once; otherwise do not give diuretics.",
                ),
                checklistTitle = "Pulmonary oedema warning signs",
                checklist = listOf(
                    "Rising respiratory rate or work of breathing (WHO MCPC)",
                    "Crackles (rales) at the lung bases (WHO MCPC)",
                    "SpO₂ below 90% — a NICE threshold for considering early birth",
                ),
                citations = listOf(
                    cite(NICE_HTN, "Recs 1.5.7, 1.7.6, 1.8.8, 1.8.11–1.8.12"),
                    cite(MOH_STG, "Intrapartum care (pp. 90–91)"),
                    cite(ACOG_222, "Vascular changes"),
                    cite(WHO_MCPC, "Mild pre-eclampsia (p. S-57); General management (p. S-58)"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Timing and mode of birth",
                timing = "≥34+0 weeks: deliver after stabilisation (ACOG)",
                points = listOf(
                    "ACOG PB 222: severe features at ≥34+0 weeks — deliver after maternal stabilisation or with labour or ruptured membranes; do not delay for late-preterm steroids.",
                    "ACOG PB 222: <34+0 weeks, stable mother and fetus — expectant management only with maternal and neonatal intensive care; not if neonatal survival is not expected.",
                    "NICE NG133: before 37 weeks, continue surveillance unless thresholds for birth are met (34–36+6: weigh neonatal beds and risks); from 37 weeks, initiate birth within 24–48 h.",
                    "WHO 2011 (Recs 17–20): previable — induce; viable <34 weeks — expectant if monitorable; 34–36+6 — expectant may be recommended; at term — early delivery.",
                    "MoHFW Dakshata 2015: unstable at 24–34 weeks — steroids, birth within 24 h; unstable at 35–36 weeks — no steroids, birth within 24 h; >37 weeks — deliver.",
                    "MoHFW STG: terminate severe pre-eclampsia at 34 weeks, earlier if refractory hypertension or worsening. Mode — ACOG and NICE: by obstetric factors (NICE: and her preference).",
                ),
                checklistTitle = "Expedite birth after stabilisation — ACOG PB 222 Box 4",
                checklist = listOf(
                    "Uncontrolled severe BP despite antihypertensives",
                    "Persistent headache, or epigastric/RUQ pain unresponsive to analgesics",
                    "Visual disturbance, motor deficit, altered sensorium or stroke",
                    "Eclampsia, HELLP syndrome or pulmonary oedema",
                    "New or worsening renal dysfunction (creatinine >1.1 mg/dL or twice baseline)",
                    "Myocardial infarction",
                    "Suspected abruption, or bleeding without placenta praevia",
                    "Abnormal fetal testing, fetal death, or persistent reversed end-diastolic flow",
                    "Fetus not expected to survive (lethal anomaly, extreme prematurity)",
                ),
                scoreIds = listOf("bishop"),
                citations = listOf(
                    cite(ACOG_222, "Expectant management and timing of delivery; Box 4; Mode of delivery"),
                    cite(NICE_HTN, "Recs 1.5.7, 1.5.12 (Table 3), 1.8.13"),
                    cite(WHO_PE, "Recs 17–20"),
                    cite(MOH_DAK, "Presentation 1.10, slide 15 (p. 58)"),
                    cite(MOH_STG, "Termination of pregnancy (p. 90)"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Corticosteroids for fetal lung maturity",
                timing = "24–34 weeks (WHO; MoHFW Dakshata); offer at 24+0–33+6 (NICE)",
                points = listOf(
                    "ACOG PB 222: if delivery is indicated before 34+0 weeks, corticosteroids are recommended, but maternal or fetal deterioration may preclude completing the course.",
                    "NICE NG25: offer at 24+0–33+6 weeks for planned preterm birth; consider at 34+0–35+6; discuss at 22+0–23+6. NICE NG133: give if birth is likely within 7 days.",
                    "WHO MCPC 2017: 24–34 weeks — betamethasone 12 mg IM, 2 doses 24 h apart, or dexamethasone 6 mg IM, 4 doses 12 h apart; not after 34 completed weeks.",
                    "WHO MCPC 2017: if the mother or fetus deteriorates rapidly, expedite birth after the first dose — do not wait to complete the course.",
                    "MoHFW Dakshata 2015: dexamethasone preferred — 6 mg IM 12 h apart, 4 doses, at 24–34 weeks. MoHFW STG: betamethasone 12 mg IM, 2 doses 24 h apart, at 24–36 weeks.",
                    "NICE NG133 and WHO 2011 (Rec 16): do not use corticosteroids to treat HELLP syndrome itself.",
                ),
                checklistTitle = "WHO conditions for antenatal corticosteroids",
                checklist = listOf(
                    "Gestational age can be assessed accurately",
                    "No clinical evidence of maternal infection",
                    "Adequate childbirth care and preterm newborn care available",
                ),
                citations = listOf(
                    cite(ACOG_222, "Expectant management and timing of delivery"),
                    cite(NICE_PTB, "Recs 1.9.1–1.9.3"),
                    cite(NICE_HTN, "Recs 1.8.9–1.8.10"),
                    cite(WHO_MCPC, "Gestation between 24 and 34 weeks (pp. S-63–S-64); Gestation 34 to 36 6/7 weeks (p. S-64)"),
                    cite(MOH_DAK, "Session 1.9 key messages (p. 46)"),
                    cite(MOH_STG, "Corticosteroid for lung maturity (p. 90)"),
                    cite(WHO_PE, "Rec 16"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "After birth: monitoring and follow-up",
                timing = "BP at least 4 times a day while an inpatient (NICE)",
                points = listOf(
                    "NICE NG133: BP at least 4 times daily as an inpatient and once on days 3–5; if on treatment, every 1–2 days for up to 2 weeks after transfer home.",
                    "NICE NG133: untreated — start treatment at ≥150/100; treated — consider reducing below 140/90, reduce below 130/80; stop methyldopa within 2 days of birth.",
                    "WHO MCPC 2017: continue antihypertensives; start if ≥160/110 after birth; BP may rise on days 3–7 — re-evaluate 1–2 weeks after discharge.",
                    "NICE NG133: ask about headache and epigastric pain at each BP check; platelets, transaminases and creatinine 48–72 h after birth; medical review at 6–8 weeks.",
                    "ACOG PB 222, HELLP: labs at least 12-hourly; platelets reach their lowest about 23 h after delivery; disease may peak in the first 2 days postpartum.",
                    "ACOG PB 222: NSAIDs remain preferred over opioids after birth, including in pre-eclampsia. MoHFW STG: methylergometrine (Methergine) is contraindicated for the third stage.",
                ),
                citations = listOf(
                    cite(NICE_HTN, "Recs 1.5.13–1.5.18, 1.5.22–1.5.23"),
                    cite(WHO_MCPC, "Postpartum care (p. S-70)"),
                    cite(ACOG_222, "HELLP management; Postpartum hypertension and postpartum headache; Summary of recommendations (Level A)"),
                    cite(MOH_STG, "Intrapartum care (p. 91)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "FOGSI 2026 (India): treat severe hypertension (≥160/110) within one hour in a monitored setting; first-line IV labetalol, " +
                    "oral nifedipine, IV hydralazine or IV nicardipine. Labetalol 20 mg slow IV, doubling every 20–30 min up to 80 mg, total " +
                    "maximum 300 mg (or infusion 1–2 mg/min). Nifedipine 10–30 mg orally, never sublingual, repeat within 30–45 min, maximum " +
                    "120 mg. Hydralazine 5 mg IV/IM, then 5–10 mg every 20–40 min; change drug if not controlled with 20 mg IV or 30 mg IM. " +
                    "Nicardipine 1.5 mg/h, up to 6 mg/h. Target ≤140/90 in severe hypertension and 135/85 for non-severe; fluids restricted to " +
                    "80 mL/h or 1 mL/kg/h in total; MgSO4 loading for all severe pre-eclampsia. Antenatal steroids: dexamethasone 6 mg IM " +
                    "12-hourly × 4 doses (Government of India).",
                listOf(
                    cite(FOGSI, "Section 8.3, severe hypertension (pp. 20–21)"),
                    cite(FOGSI, "Sections 8.1, 8.5 and 8.6, targets, fluids and antenatal corticosteroids (pp. 19–22)"),
                ),
            ),
            ProtocolNote(
                "Severe hypertension threshold: ACOG and WHO use ≥160 systolic or ≥110 diastolic; NICE NG133 defines it as over 160 systolic or over 110 diastolic.",
                listOf(cite(ACOG_222, "Box 2"), cite(WHO_MCPC, "p. S-51"), cite(NICE_HTN, "Terms used in this guideline")),
            ),
            ProtocolNote(
                "BP targets differ: NICE ≤135/85; MoHFW Dakshata diastolic 90–100; MoHFW STG diastolic 80–100 with systolic <150; MoHFW MO handbook diastolic <90; ACOG PB 203 warns diastolic <80 may affect the fetus.",
                listOf(cite(NICE_HTN, "Table 2"), cite(MOH_DAK, "Slide 6"), cite(MOH_STG, "p. 90"), cite(MOH_MO, "p. 139"), cite(ACOG_203, "Acute treatment")),
            ),
            ProtocolNote(
                "IV labetalol differs: ACOG and WHO escalate at about 10-min intervals to 300 mg in total; MoHFW STG uses 20-min intervals to 220 mg.",
                listOf(cite(ACOG_203, "Box 6"), cite(ACOG_222, "Table 3"), cite(WHO_MCPC, "Table S-13"), cite(MOH_STG, "pp. 90, 94")),
            ),
            ProtocolNote(
                "Nifedipine: ACOG starts at 10 mg (maximum 180 mg/day) and rules out sublingual use; WHO starts at 5–10 mg (maximum 30 mg acutely); MoHFW MO handbook 2009 advises 5 mg sublingually.",
                listOf(cite(ACOG_203, "Box 4"), cite(ACOG_222, "Table 3"), cite(WHO_MCPC, "Table S-13"), cite(MOH_MO, "p. 77; p. 139")),
            ),
            ProtocolNote(
                "Hydralazine repeat interval differs: WHO MCPC repeats 5 mg IV every 5 min; ACOG PB 222 every 20–40 min, and ACOG PB 203 notes onset takes 10–20 min, with an unpredictable hypotensive overshoot.",
                listOf(cite(WHO_MCPC, "Table S-13"), cite(ACOG_222, "Table 3"), cite(ACOG_203, "Hydralazine")),
            ),
            ProtocolNote(
                "Steroid windows differ: ACOG before 34+0; NICE offer 24+0–33+6, consider 34+0–35+6; WHO and MoHFW Dakshata 24–34 weeks; MoHFW STG 24–36 weeks.",
                listOf(cite(ACOG_222, "Timing of delivery"), cite(NICE_PTB, "Recs 1.9.2–1.9.3"), cite(WHO_MCPC, "pp. S-63–S-64"), cite(MOH_DAK, "p. 46"), cite(MOH_STG, "p. 90")),
            ),
            ProtocolNote(
                "Birth at 34–37 weeks differs: ACOG delivers at ≥34+0 weeks; NICE and WHO allow continued surveillance until 37 weeks if stable; MoHFW STG terminates at 34 weeks.",
                listOf(cite(ACOG_222, "Timing of delivery"), cite(NICE_HTN, "Table 3"), cite(WHO_PE, "Rec 19"), cite(MOH_STG, "p. 90")),
            ),
            ProtocolNote(
                "ACOG PB 222 notes that a Latin American trial found no neonatal benefit from expectant management at 28–34 weeks, possibly reflecting limited neonatal intensive care in low-resource settings.",
                listOf(cite(ACOG_222, "Expectant management")),
            ),
            ProtocolNote(
                "Regional anaesthesia platelet threshold: ACOG ≥70 × 10⁹/L if stable with no other coagulopathy or antithrombotic drug; MoHFW STG >50,000/mm³ for epidural.",
                listOf(cite(ACOG_222, "Anesthesia considerations"), cite(MOH_STG, "Analgesia & anesthesia issues (p. 91)")),
            ),
            ProtocolNote(
                "Postpartum treatment threshold: NICE NG133 and MoHFW STG start treatment at ≥150/100 in women not already treated; WHO MCPC starts at ≥160/110.",
                listOf(cite(NICE_HTN, "Rec 1.5.14"), cite(MOH_STG, "Postpartum care (p. 91)"), cite(WHO_MCPC, "p. S-70")),
            ),
            ProtocolNote(
                "Place of care: ICMR STW 2025 — BP >140/90 twice 6 h apart is high risk; deliver at a district hospital or medical college. MoHFW STG — refer severe pre-eclampsia after stabilisation.",
                listOf(cite(ICMR_ANC, "High risk pregnancy; Danger signals"), cite(MOH_STG, "Referral criteria (p. 89)")),
            ),
            ProtocolNote(
                "HDU care: MoHFW obstetric HDU guidelines list severe pre-eclampsia/hypertensive crisis and HELLP for HDU care; NICE NG133 — level 2 care for HELLP or IV antihypertensives.",
                listOf(cite(MOH_HDU, "Scope of obstetric HDU (p. 8)"), cite(NICE_HTN, "Table 4")),
            ),
            ProtocolNote(
                "WHO MCPC 2017: if maternal and fetal well-being cannot be monitored, transfer to a tertiary hospital; if transfer is impossible, manage severe pre-eclampsia as eclampsia.",
                listOf(cite(WHO_MCPC, "Gestation between 24 and 34 weeks (p. S-63)")),
            ),
            ProtocolNote(
                "Magnesium without severe features: ACOG leaves it to the physician or institution; the WHO 2011 group was divided; MoHFW Dakshata does not give it.",
                listOf(cite(ACOG_222, "Seizure prophylaxis"), cite(WHO_PE, "Rec 12, remark b"), cite(MOH_DAK, "Slide 7")),
            ),
        ),
        citations = listOf(
            cite(ACOG_222), cite(ACOG_203), cite(NICE_HTN), cite(NICE_PTB), cite(WHO_PE), cite(WHO_HTN), cite(WHO_MCPC),
            cite(MOH_DAK), cite(MOH_STG), cite(MOH_SBA), cite(MOH_MO), cite(MOH_HDU), cite(ICMR_ANC), cite(FDA_MG),
        ),
    ),
)
