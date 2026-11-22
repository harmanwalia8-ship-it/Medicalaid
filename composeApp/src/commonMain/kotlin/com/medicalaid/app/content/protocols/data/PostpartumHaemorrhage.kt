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
 * Postpartum haemorrhage. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them.
 * - Every point is attributed ("WHO 2025: …", "ICMR STW 2025: …", "MoHFW 2015: …", "NICE NG235: …").
 * - WHO locators are recommendation numbers of the 2025 consolidated guideline (with FIGO and ICM);
 *   NICE locators are recommendation numbers; ICMR STW locators name the box of the one-page workflow;
 *   MoHFW locators give section and page of the 2015 Guidance Note or the session of the MO handbook.
 * - RCOG GTG 52 and ACOG PB 183 full texts could not be retrieved; they are cited only for their
 *   definitions and status, checked on the issuing bodies' own pages. FOGSI's 2022 PPH document and
 *   2025 PPH protocol could not be retrieved (site blocks automated access) and are not summarised.
 */

internal val pphSources = listOf(
    Source(
        "ob-pph-who-2025", Authority.WHO, "Consolidated guidelines (with FIGO and ICM)",
        "Consolidated guidelines for the prevention, diagnosis and treatment of postpartum haemorrhage", "2025",
        "https://www.who.int/publications/i/item/9789240115637",
        status = "First edition, 5 October 2025; integrates earlier WHO PPH recommendations, including the 2017 tranexamic acid and 2023 blood-loss/treatment-bundle recommendations",
    ),
    Source(
        "ob-pph-figo-2022", Authority.FIGO, "Recommendations (Int J Gynecol Obstet 2022;157 Suppl 1:3–50)",
        "FIGO recommendations on the management of postpartum hemorrhage 2022", "2022",
        "https://doi.org/10.1002/ijgo.14116",
    ),
    Source(
        "ob-pph-nice-ng235", Authority.NICE, "Guideline NG235",
        "Intrapartum care", "2023",
        "https://www.nice.org.uk/guidance/ng235",
        status = "Last updated June 2026; Table 12 (uterotonics for PPH) amended 2024",
    ),
    Source(
        "ob-pph-nice-ng194", Authority.NICE, "Guideline NG194",
        "Postnatal care", "2021",
        "https://www.nice.org.uk/guidance/ng194",
        status = "Last updated June 2026",
    ),
    Source(
        "ob-pph-rcog-gtg52", Authority.RCOG, "Green-top Guideline No. 52 (BJOG 2017;124:e106–e149)",
        "Prevention and Management of Postpartum Haemorrhage", "2017",
        "https://www.rcog.org.uk/guidance/browse-all-guidance/green-top-guidelines/prevention-and-management-of-postpartum-haemorrhage-green-top-guideline-no-52/",
        status = "Second edition; reviewed 2023 — update in development as GTG 52a (prevention) and 52b (management)",
    ),
    Source(
        "ob-pph-acog-pb183", Authority.ACOG, "Practice Bulletin No. 183",
        "Postpartum Hemorrhage", "2017",
        "https://www.acog.org/clinical/guidance/practice-bulletin/2017/10/postpartum-hemorrhage",
        status = "Reaffirmed 2024; device content updated by a Clinical Practice Update (Obstet Gynecol 2025;146:569–571)",
    ),
    Source(
        "ob-pph-icmr-stw-2025", Authority.ICMR, "Standard Treatment Workflow (ICD-11 JA43)",
        "Postpartum Haemorrhage", "2025",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1768382528_updatedpostpartumhaemorrhage.pdf",
        status = "Updated December 2025 (ICMR and Department of Health Research, MoHFW)",
    ),
    Source(
        "ob-pph-mohfw-2015", Authority.MoHFW, "Guidance Note (Maternal Health Division)",
        "Guidance Note on Prevention and Management of Postpartum Haemorrhage", "2015",
        "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/Guidance_Note_on_Prevention_&_Management_of_Postpartum_Haemorrhage.pdf",
        status = "December 2015; developed with FOGSI and development partners",
    ),
    Source(
        "ob-pph-woman-2017", Authority.Paper, "Lancet 2017;389:2105–2116",
        "Effect of early tranexamic acid administration on mortality, hysterectomy, and other morbidities in women with post-partum haemorrhage (WOMAN)",
        "2017",
        "https://doi.org/10.1016/S0140-6736(17)30638-4",
    ),
    Source(
        "ob-pph-woman2-2024", Authority.Paper, "Lancet 2024;404:1645–1656",
        "The effect of tranexamic acid on postpartum bleeding in women with moderate and severe anaemia (WOMAN-2)",
        "2024",
        "https://doi.org/10.1016/S0140-6736(24)01749-5",
    ),
    Source(
        "ob-pph-emotive-2023", Authority.Paper, "N Engl J Med 2023;389:11–21",
        "Randomized Trial of Early Detection and Treatment of Postpartum Hemorrhage (E-MOTIVE)", "2023",
        "https://doi.org/10.1056/NEJMoa2303966",
    ),
    Source(
        "ob-pph-fda-carboprost", Authority.FDA, "Drug label (DailyMed)",
        "Hemabate (carboprost tromethamine) injection — Pharmacia & Upjohn", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=3d282998-ff0c-480a-bd80-155606ef0a65",
        status = "Label version effective March 2026",
    ),
    Source(
        "ob-pph-fda-methylergonovine", Authority.FDA, "Drug label (DailyMed)",
        "Methylergonovine maleate injection — Breckenridge", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8bccff44-2a39-400a-b8a4-2eb5249fdbc8",
        status = "Label version effective October 2026",
    ),
)

private const val WHO25 = "ob-pph-who-2025"
private const val FIGO22 = "ob-pph-figo-2022"
private const val NG235 = "ob-pph-nice-ng235"
private const val NG194 = "ob-pph-nice-ng194"
private const val GTG52 = "ob-pph-rcog-gtg52"
private const val PB183 = "ob-pph-acog-pb183"
private const val STW = "ob-pph-icmr-stw-2025"
private const val MOHFW15 = "ob-pph-mohfw-2015"
private const val WOMAN = "ob-pph-woman-2017"
private const val WOMAN2 = "ob-pph-woman2-2024"
private const val EMOTIVE = "ob-pph-emotive-2023"
private const val HEMABATE = "ob-pph-fda-carboprost"
private const val METHERGINE = "ob-pph-fda-methylergonovine"

// Existing MoHFW sources (Sources.kt).
private const val SBA = "mohfw-anc-2010"
private const val MOH09 = "mohfw-mo-handbook-2009"

internal val pphProtocols = listOf(
    Protocol(
        id = "pph",
        category = ProtocolCategory.Obstetric,
        title = "Postpartum haemorrhage",
        summary = "Trigger criteria, the first-response bundle, uterotonics, tranexamic acid, the 4 Ts, transfusion, " +
            "refractory PPH, prevention and secondary PPH — attributed to WHO 2025, ICMR, MoHFW, FIGO, NICE, RCOG and ACOG.",
        keywords = listOf(
            "PPH", "postpartum haemorrhage", "postpartum hemorrhage", "post-partum haemorrhage", "postpartum bleeding",
            "primary PPH", "secondary PPH", "delayed PPH", "atonic PPH", "uterine atony", "traumatic PPH",
            "retained placenta", "MRP", "manual removal of placenta", "obstetric haemorrhage",
            "massive obstetric haemorrhage", "oxytocin", "Pitocin", "Syntocinon", "methylergometrine",
            "methylergonovine", "methergine", "ergometrine", "Syntometrine", "carboprost", "Prostodin", "Hemabate",
            "PGF2 alpha", "15-methyl PGF2a", "misoprostol", "Cytolog", "carbetocin", "heat-stable carbetocin",
            "Duratocin", "TXA", "tranexamic acid", "tranexamic", "Pause injection", "WOMAN trial", "E-MOTIVE",
            "MOTIVE bundle", "PPH bundle", "calibrated drape", "blood collection drape", "B-Lynch",
            "compression suture", "NASG", "non-pneumatic anti-shock garment", "balloon tamponade", "UBT",
            "condom catheter", "condom tamponade", "Bakri", "bimanual compression", "aortic compression", "AMTSL",
            "active management of third stage", "shock index", "massive transfusion", "MTP", "4 Ts",
            "tone trauma tissue thrombin", "delivery ke baad khoon", "delivery ke baad bleeding",
            "zyada khoon jaana", "prasav ke baad raktsrav", "प्रसव के बाद रक्तस्राव", "प्रसवोत्तर रक्तस्राव",
            "ਜਣੇਪੇ ਤੋਂ ਬਾਅਦ ਖੂਨ", "ਜਣੇਪੇ ਤੋਂ ਬਾਅਦ ਖੂਨ ਵਗਣਾ",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise PPH — trigger criteria",
                timing = "Within 24 h of birth; most events in the first 2 h (WHO 2025)",
                points = listOf(
                    "WHO 2025: start first-response treatment at measured loss ≥300 mL with any abnormal haemodynamic sign, or ≥500 mL — whichever comes first, within 24 h.",
                    "WHO 2025: abnormal signs — pulse >100/min, systolic BP <100 mm Hg, diastolic BP <60 mm Hg, or shock index (pulse ÷ systolic BP) >1.",
                    "WHO 2025: measure blood loss objectively at every birth, e.g. calibrated drape after vaginal birth; palpate uterine tone, ideally every 15 min in the first hour.",
                    "WHO 2025: the criteria are meant for all births, though caesarean evidence is limited; higher operative blood loss should not be normalised — it delays treatment.",
                    "ICMR STW 2025: PPH is >500 mL or any bleeding that deranges vitals. MoHFW 2015: ≥1000 mL in 24 h is severe; with anaemia, smaller losses can harm.",
                    "ACOG PB 183: ≥1000 mL, or loss with signs of hypovolaemia, within 24 h. RCOG GTG 52: ≥500 mL; major >1000 mL; severe >2000 mL.",
                ),
                checklistTitle = "Red flags (ICMR STW 2025)",
                checklist = listOf(
                    "Pulse >120/min",
                    "Systolic BP <100 mm Hg",
                    "Tachypnoea",
                    "SpO₂ <95%",
                    "Shock index >1",
                    "Deteriorating sensorium",
                ),
                citations = listOf(
                    cite(WHO25, "§3.4; Rec 21–23 with remarks"),
                    cite(STW, "Definition; red flag signs"),
                    cite(MOHFW15, "Background (p. 1)"),
                    cite(PB183, "Abstract (definition)"),
                    cite(GTG52, "Summary (definitions)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "First-response bundle",
                timing = "Start every element within 15 min of diagnosis (WHO 2025)",
                points = listOf(
                    "WHO 2025: one bundle — uterine massage, oxytocic, tranexamic acid, IV crystalloids, genital-tract examination and escalation — started together, not one after another.",
                    "WHO 2025: if some elements are unavailable, start the rest promptly. Bundle evidence comes from vaginal births; each element is also recommended at caesarean.",
                    "E-MOTIVE (NEJM 2023; 80 hospitals, four African countries): drape plus bundle cut severe PPH, laparotomy or death from bleeding — 1.6% vs 4.3% (RR 0.40).",
                    "ICMR STW 2025: call for help; two 16–18 G cannulas; group and cross-match; oxygen 6–8 L/min by mask; indwelling catheter; vitals and loss at least 15-minutely.",
                    "NICE NG235: call for help, empty the bladder, massage, uterotonics, IV fluids, CCT if the placenta is undelivered; consider oxygen from 15 L/min to SpO₂ 94–98%.",
                    "WHO 2025: therapeutic massage starts at diagnosis and is repeated per response; the initial rubbing-up of the uterus and expression of clots alone do not count.",
                ),
                checklistTitle = "Bundle elements (WHO 2025 Rec 29)",
                checklist = listOf(
                    "Uterine massage",
                    "Oxytocic — IV oxytocin first line",
                    "Tranexamic acid 1 g IV within 3 h of birth",
                    "Isotonic crystalloids",
                    "Examine vagina, perineum, cervix, placenta and membranes",
                    "Escalate if bleeding continues",
                ),
                citations = listOf(
                    cite(WHO25, "§3.5; Rec 26 and 29 with remarks"),
                    cite(EMOTIVE, "Abstract — methods and results"),
                    cite(STW, "Initial management box"),
                    cite(NG235, "Rec 1.10.33"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Uterotonics for atony",
                timing = "Immediately, with the bundle",
                points = listOf(
                    "WHO 2025: IV oxytocin is first line, even after oxytocin prophylaxis — 10 IU diluted, slowly over 1–2 min, or in a small volume over 5–10 min.",
                    "WHO 2025: maintenance 10–20 IU in crystalloid over 4 h may be needed, titrated to response. ICMR STW 2025: 20 IU in 500 mL RL/NS at 40–60 drops/min.",
                    "ICMR STW 2025: oxytocin 10 IU IM if not given after delivery; never an IV bolus. Bleeding not controlled — infusion 10–20 units/h with bimanual compression.",
                    "WHO 2025: no IV oxytocin or no response — IV ergometrine, oxytocin–ergometrine, or a prostaglandin incl. misoprostol 800 µg sublingual (ICMR STW, NICE: or rectal).",
                    "ICMR STW 2025: methylergometrine 0.2 mg IM/slow IV, repeat at 15 min, max 5 doses (1 mg); carboprost 250 µg IM 20-minutely, max 8 doses (2 mg).",
                    "WHO 2025: carbetocin is for prevention only, not treatment; adding misoprostol to oxytocin gives no benefit. NICE NG235 Table 12 chooses second line by the prophylaxis given.",
                ),
                checklistTitle = "Contraindications and cautions",
                checklist = listOf(
                    "Methylergometrine: hypertension, pre-eclampsia, heart disease, severe anaemia (ICMR STW; MoHFW MO handbook)",
                    "Oxytocin–ergometrine: severe hypertension, pre-eclampsia, eclampsia, severe cardiac, hepatic or renal disease (NICE NG235)",
                    "Methylergometrine: no strong or moderate CYP3A4 inhibitors — HIV protease inhibitors, clarithromycin, azole antifungals (US label)",
                    "Methylergometrine IV only as a life-saving measure, over ≥60 s with BP monitoring (US label)",
                    "Ergometrine and PGE2 analogues: avoid while the placenta is retained (WHO 2025); ergometrine contraindicated (MoHFW MO handbook)",
                    "Carboprost: asthma (ICMR STW contraindication; US label caution); active cardiac, pulmonary, renal or hepatic disease (US label)",
                    "Misoprostol 800 µg: GDG concern over hyperpyrexia (WHO 2025)",
                    "Oxytocin limits: total ≤100 IU in 24 h (ICMR STW); ≤3 L of oxytocin-containing fluid (MoHFW MO handbook)",
                ),
                citations = listOf(
                    cite(WHO25, "Rec 24–25 with remarks; Rec 7.2 and 30 remarks"),
                    cite(STW, "Initial management; management of atonic PPH — pharmacotherapy; bleeding not controlled"),
                    cite(NG235, "Rec 1.10.9, 1.10.34; Table 12"),
                    cite(MOH09, "Session 3b — retained placenta; drugs for PPH management (pp. 35–36)"),
                    cite(HEMABATE, "Contraindications; Precautions; Dosage and administration §2"),
                    cite(METHERGINE, "Contraindications; Warnings; Dosage and administration"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Tranexamic acid",
                timing = "As soon as possible — within 3 h of birth",
                points = listOf(
                    "WHO 2025: tranexamic acid 1 g (100 mg/mL) IV at 1 mL/min (over 10 min) for every PPH, whatever the cause, within 3 h of birth.",
                    "WHO 2025: second 1 g IV if bleeding continues after 30 min, or restarts within 24 h of completing the first dose.",
                    "WHO 2025: the 3-h window runs from birth (best estimate if unknown); beyond 3 h there is no benefit and estimates point toward harm — not supported.",
                    "WOMAN trial: death from bleeding 1.5% vs 1.9% (RR 0.81); given within 3 h of birth 1.2% vs 1.7% (RR 0.69); thromboembolic events not increased.",
                    "ICMR STW 2025: TXA 1 g slow IV routinely, as early as possible whatever the cause, definitely within 3 h of delivery; repeat at 30 min if bleeding persists.",
                    "NICE NG235: TXA 1 g IV over 10 min alongside uterotonics; repeat after at least 30 min. MoHFW 2015: only if uterotonics fail or trauma contributes.",
                ),
                checklistTitle = "Cautions (WHO 2025)",
                checklist = listOf(
                    "Clear contraindication to antifibrinolytics, e.g. a thromboembolic event in this pregnancy",
                    "More than 3 h after birth — not supported",
                    "IV route only; other routes are a research question",
                    "Not for routine prevention at vaginal or caesarean birth",
                    "Store apart from bupivacaine — fatal intrathecal TXA errors reported with look-alike ampoules",
                ),
                citations = listOf(
                    cite(WHO25, "Rec 14–15 with remarks; Rec 27 with remarks"),
                    cite(WOMAN, "Abstract — findings"),
                    cite(STW, "Tranexamic acid box"),
                    cite(NG235, "Rec 1.10.35"),
                    cite(MOHFW15, "2. Treatment of PPH at facility level (pp. 13–15)"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Trauma, retained tissue, coagulopathy",
                timing = "Examine during the bundle",
                points = listOf(
                    "WHO 2025: most PPH comes from tone, trauma, tissue or thrombin — examine vagina, perineum and cervix, check uterine tone, inspect placenta and membranes.",
                    "ICMR STW 2025: uterus contracted but bleeding — repair cervical, vaginal or perineal tears; if it persists, suspect poor repair, rupture or scar dehiscence — explore in OT.",
                    "ICMR STW 2025: placenta incomplete — explore the uterus and evacuate bits, membranes or clots. Bleeding with no apparent cause — check for coagulopathy; give blood components.",
                    "WHO 2025: retained placenta with PPH — full bundle with a uterotonic. Without PPH — CCT, bladder emptying, mobilising after 15 min; prepare manual removal at 30 min.",
                    "WHO 2025: antibiotic prophylaxis for manual removal — ampicillin or a first-generation cephalosporin, or single IV amoxicillin 1 g with clavulanic acid 200 mg.",
                    "MoHFW MO handbook 2009: before MRP, ampicillin 1 g, gentamicin 80 mg and metronidazole 500 mg IV, with pethidine–diazepam sedation. NICE NG235: never without anaesthetic.",
                ),
                checklistTitle = "4 Ts",
                checklist = listOf(
                    "Tone — soft, boggy uterus (atony, the commonest cause)",
                    "Trauma — tears, haematoma, uterine rupture or inversion",
                    "Tissue — retained placenta, fragments or membranes",
                    "Thrombin — coagulation abnormality",
                ),
                doses = listOf(
                    StepDose(
                        label = "Pethidine — sedation for manual removal (MoHFW MO handbook 2009)",
                        dose = WeightDose(1.0, 1.0, "mg", per = "single dose, IM or slow IV"),
                        note = "With diazepam 10 mg IM/IV in a separate syringe. NICE NG235: do not perform manual removal without an anaesthetic.",
                    ),
                ),
                citations = listOf(
                    cite(WHO25, "§3.5 introduction; Rec 29–31 with remarks"),
                    cite(STW, "Placenta not delivered; traumatic PPH; coagulopathy branches"),
                    cite(SBA, "Module II — C.1 immediate PPH: causes"),
                    cite(MOH09, "Session 3b — management of retained placenta (p. 35)"),
                    cite(NG235, "Rec 1.10.20–1.10.28"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Fluids, blood and massive transfusion",
                timing = "From diagnosis; arrange blood early",
                points = listOf(
                    "WHO 2025: isotonic crystalloids (Ringer’s lactate or normal saline) rather than colloids; avoid overload, particularly in pre-eclampsia or heart disease.",
                    "ICMR STW 2025: 1 L RL/NS over 15–20 min; about 3 mL crystalloid per mL lost early on. FIGO 2022: >4 L of fluid linked to further bleeding.",
                    "WHO 2025: transfuse on the clinical picture — unstable, loss ≥1000 mL and ongoing; a single Hb can mislead. Red-cell goal often Hb >70 g/L.",
                    "WHO 2025: crossmatched red cells preferred; otherwise ABO/Rh-compatible uncrossmatched, or O RhD-negative in an emergency. Verify blood group on labour-ward admission.",
                    "WHO 2025: keep fibrinogen ≥2 g/L (3–4 g ≈ 8–10 units cryoprecipitate). FFP after results and 4 RBC units; if results are delayed, 1 FFP : 2 RBC.",
                    "FIGO 2022: haemostatic resuscitation — limit crystalloid, give products early in high ratios (1:1:1 RBC:FFP:platelets); fibrinogen at least 150–200 mg/dL.",
                ),
                checklistTitle = "Massive PPH (WHO 2025)",
                checklist = listOf(
                    "Massive transfusion: 4 RBC units in 1 h with ongoing need, ≥10 units in 24 h, or >50% blood volume in 3 h",
                    "Step 1: tranexamic acid 1 g IV over 10 min",
                    "Step 2: fibrinogen (concentrate or cryoprecipitate) when coagulopathy is detected or highly suspected, e.g. amniotic fluid embolism",
                    "Step 3: FFP for documented factor deficiency — target PT/aPTT ratio <1.5, INR ≤1.5",
                    "Step 4: platelets if <50 × 10⁹/L with microvascular bleeding",
                    "Have a plan for women who decline blood products",
                ),
                doses = listOf(
                    StepDose(
                        label = "Platelets (WHO 2025)",
                        dose = WeightDose(5.0, 10.0, "mL", per = "transfusion"),
                        note = "If platelets <50 × 10⁹/L with clinical microvascular bleeding.",
                    ),
                ),
                citations = listOf(
                    cite(WHO25, "Rec 28; Rec 41 with remarks"),
                    cite(STW, "Initial management; fluid replacement footnote"),
                    cite(FIGO22, "§11.2.5–11.2.8"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Refractory PPH — temporise, tamponade, surgery",
                timing = "Bleeding continues despite the full bundle",
                points = listOf(
                    "WHO 2025: bleeding despite the full bundle (10–20% of PPH) — escalate promptly to a senior clinician or higher-level facility. NICE NG235: obstetric-led care; examination under anaesthesia.",
                    "WHO 2025: temporise with bimanual compression or external aortic compression — just above the umbilicus, slightly left; less bleeding and a weak femoral pulse show effect.",
                    "WHO 2025: NASG while awaiting transfer, in transit, or awaiting blood or surgery. ICMR STW 2025: refer early; transport with oxytocin running, tamponade in situ, NASG if available.",
                    "ICMR STW 2025: then intrauterine balloon tamponade with a condom catheter. MoHFW MO handbook: condom tied over a Foley 18F, filled with 300–500 mL saline.",
                    "WHO 2025: balloon tamponade for atony after vaginal birth only when its preconditions are met; uterine packing with gauze is not recommended.",
                    "WHO 2025: conservative surgery first — compression sutures, then uterine, utero-ovarian and internal iliac ligation, then subtotal or total hysterectomy; embolisation where available.",
                ),
                checklistTitle = "Balloon tamponade preconditions (WHO 2025 Rec 36)",
                checklist = listOf(
                    "Immediate access to surgery and blood products",
                    "First-line protocol (uterotonics, tranexamic acid, IV fluids) in routine use",
                    "Retained tissue and trauma reasonably excluded",
                    "Operator trained in PPH management and balloon tamponade",
                    "Regular maternal monitoring to detect deterioration",
                ),
                citations = listOf(
                    cite(WHO25, "§3.6; Rec 29 remarks; Rec 33–39"),
                    cite(STW, "Bleeding not controlled; referral and temporising measures"),
                    cite(MOH09, "Session 3b — bimanual and aortic compression; uterine tamponade (pp. 32–33)"),
                    cite(NG235, "Rec 1.10.4, 1.10.37"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Prevention — third stage of labour",
                timing = "Uterotonic immediately after birth, preferably within 1 min (WHO 2025)",
                points = listOf(
                    "WHO 2025: one quality-assured uterotonic for every birth — oxytocin 10 IU IM/IV is the agent of choice; oxytocin needs storage at 2–8 °C.",
                    "WHO 2025: IV line already in place at vaginal birth — 10 IU diluted, slowly over 1–2 min, preferred to IM. At caesarean, avoid a rapid IV bolus.",
                    "WHO 2025: alternatives — carbetocin 100 µg IM/IV (heat-stable where the oxytocin cold chain is unreliable) or oral misoprostol 400 or 600 µg.",
                    "WHO 2025: not for prevention — ergometrine/methylergometrine, oxytocin–ergometrine, carboprost, tranexamic acid; no sustained massage after prophylactic oxytocin.",
                    "SBA 2010 and MoHFW 2015: oxytocin 10 IU IM for AMTSL; oral misoprostol 600 µg if oxytocin unavailable — ASHAs may pre-distribute it for home births.",
                    "NICE NG235: oxytocin 10 IU IM, or 5 IU slow IV over 3–5 min after oxytocin in labour; oxytocin–ergometrine if risk factors; carbetocin at caesarean.",
                ),
                checklistTitle = "Risk factors — plan birth in an obstetric unit (NICE NG235)",
                checklist = listOf(
                    "Previous PPH >1000 mL or needing transfusion",
                    "Placenta accreta spectrum",
                    "Pre-eclampsia",
                    "Hb <85 g/L at onset of labour",
                    "BMI >35 kg/m²",
                    "Parity 4 or more",
                    "Antepartum haemorrhage or placental abruption",
                    "Overdistended uterus — multiple pregnancy, polyhydramnios",
                    "Fibroids or other uterine abnormality",
                    "Low-lying placenta",
                ),
                citations = listOf(
                    cite(WHO25, "Rec 7–15 and 20, with remarks"),
                    cite(SBA, "Module I — third stage of labour: AMTSL"),
                    cite(MOHFW15, "Community distribution of misoprostol (p. 9); 1. Prevention of PPH: AMTSL (pp. 11–12)"),
                    cite(NG235, "Rec 1.10.9–1.10.13, 1.10.29"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "After bleeding stops; secondary PPH",
                timing = "Close monitoring for 24 h; secondary PPH from 24 h to 6 weeks",
                points = listOf(
                    "ICMR STW 2025: once controlled — massage 15-minutely for 2 h; vitals every 10 min for 30 min, 15 min for 30 min, then 30-minutely for 3–6 h.",
                    "ICMR STW 2025: continue oxytocin infusion at 5–10 IU/h; total oxytocin not to exceed 100 IU in 24 h.",
                    "WHO 2025: oral iron ± folic acid for 6–12 weeks where anaemia is prevalent; IV iron if oral fails or severe anaemia (Hb ≤80 g/L) needs rapid correction.",
                    "SBA 2010: secondary PPH — bleeding from 24 h up to 6 weeks after birth, from retained clots or placental fragments, or uterine infection.",
                    "SBA 2010: oxytocin 10 IU IM, then 20 IU in 500 mL RL/NS at 40–60 drops/min; if fever or foul discharge, first antibiotic doses; refer to FRU.",
                    "NICE NG194: seek review for sudden, heavy or increasing bleeding, clots or tissue, or infection signs; anaemia and booking weight <50 kg worsen consequences.",
                ),
                checklistTitle = "SBA 2010 — first antibiotic doses before referral",
                checklist = listOf(
                    "Ampicillin 1 g orally",
                    "Metronidazole 400 mg orally",
                    "Gentamicin 80 mg IM",
                ),
                doses = listOf(
                    StepDose(
                        label = "Ferric carboxymaltose — IV iron after PPH (regimen in the studies WHO 2025 reviewed)",
                        dose = WeightDose(20.0, 20.0, "mg", per = "single IV infusion"),
                        note = "WHO 2025: IV iron only with staff trained to manage anaphylaxis; monitor during and 30 min after the infusion; avoid iron dextran.",
                        maxTotal = 1000.0,
                    ),
                ),
                citations = listOf(
                    cite(STW, "Bleeding controlled — monitoring"),
                    cite(WHO25, "§3.7; Rec 42–43 with remarks"),
                    cite(SBA, "Module II — C.2 delayed/secondary PPH"),
                    cite(NG194, "Rec 1.2.13–1.2.14"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Thresholds differ: WHO 2025 — ≥300 mL with an abnormal sign, or ≥500 mL; ICMR STW 2025 — >500 mL or deranged vitals; RCOG GTG 52 — ≥500 mL; ACOG PB 183 — ≥1000 mL or hypovolaemia.",
                listOf(cite(WHO25, "Rec 22"), cite(STW, "Definition"), cite(GTG52, "Summary (definitions)"), cite(PB183, "Abstract (definition)")),
            ),
            ProtocolNote(
                "WHO 2025 calls its criteria a therapeutic definition that triggers the bundle, not advanced or surgical treatment; implementers may intervene earlier where referral is slow.",
                listOf(cite(WHO25, "Rec 22 remarks")),
            ),
            ProtocolNote(
                "Shock index cut-off: WHO 2025 and ICMR STW 2025 use >1; FIGO 2022 uses >0.9 as the alert threshold (normal obstetric range 0.7–0.9).",
                listOf(cite(WHO25, "Rec 22 remarks"), cite(STW, "Red flag signs"), cite(FIGO22, "§5.1; Box 2")),
            ),
            ProtocolNote(
                "Measuring blood loss: MoHFW 2015 lists drapes, calibrated jugs or pails, cholera beds and Kanga cloth, but found insufficient evidence to prefer quantification; WHO 2025 recommends routine objective measurement and says trays and jars lack evidence of accuracy.",
                listOf(cite(MOHFW15, "Diagnosis of PPH (pp. 10–11)"), cite(WHO25, "Rec 21 with remarks")),
            ),
            ProtocolNote(
                "Tranexamic acid: MoHFW 2015 limits it to bleeding that persists after uterotonics or is partly traumatic; ICMR STW 2025, WHO 2025, FIGO 2022 and NICE NG235 give it early in every PPH.",
                listOf(
                    cite(MOHFW15, "2. Treatment of PPH at facility level (pp. 13–15)"),
                    cite(STW, "Tranexamic acid box"),
                    cite(WHO25, "Rec 27"),
                    cite(FIGO22, "§2.2"),
                    cite(NG235, "Rec 1.10.35"),
                ),
            ),
            ProtocolNote(
                "Prophylactic TXA: WOMAN-2 (Hb <100 g/L, vaginal birth) found no reduction in PPH — 7.0% vs 6.6% (RR 1.05). WHO 2025 advises against routine prophylaxis; at caesarean it lets clinicians consider TXA in certain high-risk women before PPH criteria are met.",
                listOf(cite(WOMAN2, "Abstract — findings"), cite(WHO25, "Rec 14–15 with remarks")),
            ),
            ProtocolNote(
                "IV oxytocin differs: ICMR STW 2025 and MoHFW MO handbook — never as an IV bolus; WHO 2025 — 10 IU diluted, slowly over 1–2 min; NICE NG235 — 5 IU slow IV over 3–5 min for prophylaxis.",
                listOf(
                    cite(STW, "Initial management"),
                    cite(MOH09, "Session 3b — drugs for PPH management (p. 36)"),
                    cite(WHO25, "Rec 8 and 24 remarks"),
                    cite(NG235, "Rec 1.10.11–1.10.12"),
                ),
            ),
            ProtocolNote(
                "Oxytocin maintenance differs: WHO 2025 gives 10–20 IU over 4 h after initial control; ICMR STW 2025 gives 10–20 units/h while bleeding persists, then 5–10 units/h, capped at 100 IU in 24 h.",
                listOf(cite(WHO25, "Rec 24 remarks"), cite(STW, "Bleeding not controlled; bleeding controlled")),
            ),
            ProtocolNote(
                "Ergometrine prophylaxis is contested: WHO 2025 no longer recommends ergometrine or oxytocin–ergometrine for prevention; FIGO 2022 allows 200 µg if hypertension is excluded; NICE NG235 advises oxytocin–ergometrine with risk factors; MoHFW 2015 urges caution in unscreened women.",
                listOf(
                    cite(WHO25, "Rec 9.1–9.2"),
                    cite(FIGO22, "§2.1"),
                    cite(NG235, "Rec 1.10.9"),
                    cite(MOHFW15, "1. Prevention of PPH: AMTSL (p. 12)"),
                ),
            ),
            ProtocolNote(
                "Ergometrine route for treatment: WHO 2025 names IV ergometrine; FIGO 2022 IM; ICMR STW 2025 IM or slow IV; the US methylergonovine label says not IV routinely, only over ≥60 s when life-saving.",
                listOf(cite(WHO25, "Rec 25"), cite(FIGO22, "§2.2"), cite(STW, "Pharmacotherapy"), cite(METHERGINE, "Warnings")),
            ),
            ProtocolNote(
                "Repeat intervals differ: carboprost — ICMR STW 2025 every 20 min, MoHFW MO handbook every 15 min, NICE NG235 at least 15 min, US label 15–90 min (max 8 doses, 2 mg). Methylergometrine — ICMR 15 min; MO handbook 15 min then 4-hourly; US label 2–4 h.",
                listOf(
                    cite(STW, "Management of atonic PPH — pharmacotherapy"),
                    cite(MOH09, "Session 3b — drugs for PPH management (p. 36)"),
                    cite(NG235, "Table 12"),
                    cite(HEMABATE, "Dosage and administration §2"),
                    cite(METHERGINE, "Dosage and administration"),
                ),
            ),
            ProtocolNote(
                "Balloon tamponade: WHO 2025 restricts it to settings with immediate surgery and blood; FIGO 2022 recommends it when uterotonics fail or are unavailable; MoHFW 2015 allows trained condom-catheter tamponade at PHCs with a Medical Officer; NICE NG235 — consider before surgery.",
                listOf(
                    cite(WHO25, "Rec 36 with remarks"),
                    cite(FIGO22, "§2.2"),
                    cite(MOHFW15, "2. Treatment of PPH at facility level (p. 14)"),
                    cite(NG235, "Rec 1.10.37"),
                ),
            ),
            ProtocolNote(
                "Transfusion ratios are debated: WHO 2025 prefers laboratory-guided FFP (empirical 1 FFP : 2 RBC) and early fibrinogen; FIGO 2022 describes trauma-derived 1:1:1 high-ratio protocols.",
                listOf(cite(WHO25, "Rec 41 remarks"), cite(FIGO22, "§11.2.6–11.2.9")),
            ),
            ProtocolNote(
                "After misoprostol prophylaxis, WHO 2025 and FIGO 2022 find no evidence to guide a further 800 µg treatment dose; WHO advises weighing toxicity.",
                listOf(cite(WHO25, "Rec 25 remarks"), cite(FIGO22, "§2.2")),
            ),
            ProtocolNote(
                "Uterine surgery: MoHFW 2015 names modified B-Lynch for bleeding unresponsive to uterotonics; NICE NG235 recommends no particular surgical procedure over another.",
                listOf(cite(MOHFW15, "2. Treatment of PPH at facility level (pp. 14–15)"), cite(NG235, "Rec 1.10.38")),
            ),
            ProtocolNote(
                "Scope: WHO 2025, co-published with FIGO and ICM, consolidates WHO's earlier PPH, tranexamic acid, uterotonic, balloon tamponade and 2023 bundle recommendations. RCOG GTG 52 is being revised as GTG 52a/52b.",
                listOf(cite(WHO25, "Foreword; summary of recommendations (integration footnotes)"), cite(GTG52, "Version history")),
            ),
            ProtocolNote(
                "Not summarised here: ACOG's 2025 Clinical Practice Update on nonsurgical hemorrhage-control devices, and FOGSI's own PPH documents. MoHFW 2015 was developed with FOGSI.",
                listOf(cite(PB183, "Status"), cite(MOHFW15, "List of contributors; p. 4")),
            ),
        ),
        citations = listOf(
            cite(WHO25), cite(STW), cite(MOHFW15), cite(SBA), cite(MOH09), cite(FIGO22), cite(NG235), cite(NG194),
            cite(GTG52), cite(PB183), cite(WOMAN), cite(WOMAN2), cite(EMOTIVE), cite(HEMABATE), cite(METHERGINE),
        ),
    ),
)
