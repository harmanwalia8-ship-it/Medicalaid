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
 * Early pregnancy emergencies: ectopic pregnancy; miscarriage, incomplete and septic abortion; hyperemesis gravidarum.
 * Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them (checked against the full text,
 *   November 2026). Nothing is filled from memory; what could not be verified is left out.
 * - Every point is attributed ("NICE NG126: …", "WHO MCPC 2017: …", "MoHFW STG 2017: …", "MoHFW 2009: …").
 * - NICE locators are recommendation numbers; WHO MCPC locators are its S-pages and tables; MoHFW locators are
 *   printed page numbers; FDA label locators are section numbers.
 * - WHO 2024 locators are recommendation numbers of the Abortion care guideline, 2nd edition; MoHFW CAC 2023 locators
 *   are chapter and printed page.
 * - RCOG GTG 69 (2024) was read in full from the publisher PDF (BJOG, DOI 10.1111/1471-0528.17739); its locators are
 *   section, table and appendix numbers. Its Appendix IIa prints the PUQE-24 mild band as "Mild 6", so only the
 *   moderate and severe bands are quoted. LMWH doses are from the RCOG September 2026 position statement on GTG 37a,
 *   which replaces the 2015 GTG 37a dose table.
 * - RCOG/AEPU GTG 21 (Wiley) and ACOG PB 189, PB 193 and PB 200 (Obstet Gynecol) full texts could not be retrieved; they
 *   are cited only for what the issuing bodies' own pages state (scope, status, abstract). The FOGSI ectopic GCPR PDF
 *   blocks automated access and is not summarised. ICMR has no STW on ectopic pregnancy, miscarriage or hyperemesis
 *   (list checked November 2026; its D&C STW is not summarised).
 * - Reuses released sources: ob-ecl-mohfw-stg-obg (MoHFW Standard Treatment Guidelines, Obstetrics & Gynaecology —
 *   the same PDF also holds the Ectopic pregnancy chapter, pp. 48–53), ob-ecl-who-mcpc-2017 (Eclampsia.kt),
 *   tr-wb-who-tetanus-2017 (WoundsBites.kt), mohfw-mo-handbook-2009 and rcog-gtg69 (Sources.kt — moved there because
 *   the released Nausea & vomiting page also cites RCOG GTG 69).
 */

internal val earlyPregnancySources = listOf(
    Source(
        "ob-ep-nice-ng126", Authority.NICE, "Guideline NG126",
        "Ectopic pregnancy and miscarriage: diagnosis and initial management", "2019",
        "https://www.nice.org.uk/guidance/ng126",
        status = "Last updated 17 June 2026 (new anti-D recommendations); medical management of miscarriage updated August 2023",
    ),
    Source(
        "ob-ep-rcog-gtg21", Authority.RCOG, "Green-top Guideline No. 21 (BJOG 2016;123:e15–e55)",
        "Diagnosis and Management of Ectopic Pregnancy", "2016",
        "https://www.rcog.org.uk/guidance/browse-all-guidance/green-top-guidelines/diagnosis-and-management-of-ectopic-pregnancy-green-top-guideline-no-21/",
        status = "First edition (with AEPU), reviewed 2019 — update in development; full text could not be retrieved, " +
            "cited only from the RCOG summary page",
    ),
    Source(
        "ob-ep-acog-pb193", Authority.ACOG, "Practice Bulletin No. 193 (Obstet Gynecol 2018;131:e91–e103)",
        "Tubal Ectopic Pregnancy", "2018",
        "https://www.acog.org/clinical/guidance/practice-bulletin/2018/03/tubal-ectopic-pregnancy",
        status = "Reaffirmed 2025; interim update on hCG after uterine aspiration in pregnancy of unknown location; " +
            "full text paywalled — cited only from the ACOG abstract",
    ),
    Source(
        "ob-ep-fda-methotrexate", Authority.FDA, "Drug label (DailyMed)",
        "Methotrexate injection — Hospira", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=0d63ba29-b692-41b4-87e8-351265c8273f",
        status = "Label version effective October 2026; ectopic pregnancy is not a labelled indication",
    ),
    // ---------------------------------------------------------------- Miscarriage and septic abortion
    Source(
        "ob-ep-mohfw-cac-2023", Authority.MoHFW, "Training and Service Delivery Guidelines, 3rd edition (Maternal Health Division)",
        "Comprehensive Abortion Care", "2023",
        "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/CAC_Training_&_Service_Guidelines_2023.pdf",
        status = "Third edition (2023), aligned with the MTP (Amendment) Act 2021 and Rules 2021; replaces the 2018 edition",
    ),
    Source(
        "ob-ep-who-abortion-2024", Authority.WHO, "Guideline, 2nd edition (ISBN 978-92-4-010420-4)",
        "Abortion care guideline", "2024",
        "https://www.who.int/publications/i/item/9789240104204",
        status = "Published 24 August 2025; the second edition changes only the remarks of Recommendation 22 — " +
            "clinical recommendations unchanged from the 2022 first edition",
    ),
    Source(
        "ob-ep-acog-pb200", Authority.ACOG, "Practice Bulletin No. 200 (Obstet Gynecol 2018;132:e197–e207)",
        "Early Pregnancy Loss", "2018",
        "https://www.acog.org/clinical/guidance/practice-bulletin/2018/11/early-pregnancy-loss",
        status = "Reaffirmed 2025; Rh guidance revised by a 2024 Clinical Practice Update (Obstet Gynecol 2024;144:e140–e143); " +
            "full texts (PB 200 is free access on ACOG) could not be retrieved automatically — cited only from the ACOG abstract",
    ),
    // ---------------------------------------------------------------- Hyperemesis gravidarum
    // RCOG GTG 69 is defined in Sources.kt (rcog-gtg69): the released Nausea & vomiting page cites it too.
    Source(
        "ob-ep-rcog-gtg37a-ps-2026", Authority.RCOG, "Position statement on Green-top Guideline No. 37a",
        "Reducing the Risk of Venous Thromboembolism during Pregnancy and the Puerperium — position statement", "2026",
        "https://www.rcog.org.uk/media/bzkdicre/position-statement-gtg37a-final-version.pdf",
        status = "September 2026; amends GTG 37a (3rd edition, 2015) pending its full update — replaces the GTG 37a LMWH dose table",
    ),
)

// Defined here
private const val NG126 = "ob-ep-nice-ng126"
private const val GTG21 = "ob-ep-rcog-gtg21"
private const val PB193 = "ob-ep-acog-pb193"
private const val MTX_LABEL = "ob-ep-fda-methotrexate"
private const val CAC23 = "ob-ep-mohfw-cac-2023"
private const val WHO_AC = "ob-ep-who-abortion-2024"
private const val PB200 = "ob-ep-acog-pb200"
private const val VTE_PS = "ob-ep-rcog-gtg37a-ps-2026"

// Released sources defined elsewhere
private const val MOH_STG = "ob-ecl-mohfw-stg-obg" // Eclampsia.kt — MoHFW STG Obstetrics & Gynaecology (2017)
private const val WHO_MCPC = "ob-ecl-who-mcpc-2017" // Eclampsia.kt — WHO Managing complications (2017)
private const val MOH09 = "mohfw-mo-handbook-2009" // Sources.kt — MoHFW MO trainees' handbook
private const val SSC26 = "em-ab-ssc-2026" // AcidBaseDisorders.kt — Surviving Sepsis Campaign 2026
private const val PB189 = "acog-pb189" // Sources.kt — ACOG PB 189, Nausea and Vomiting of Pregnancy
private const val SBA = "mohfw-anc-2010" // Sources.kt — MoHFW SBA guidelines for ANMs/LHVs/SNs
private const val ICMR_ANC = "icmr-stw-anc-2025" // Sources.kt — ICMR STW Ante-natal management
private const val OND_LABEL = "fda-label-ondansetron" // drugs/data/Symptomatic.kt
private const val MCP_LABEL = "fda-label-metoclopramide" // drugs/data/Symptomatic.kt
private const val GTG69 = "rcog-gtg69" // Sources.kt — RCOG GTG 69 (2024), Nausea and vomiting in pregnancy and HG
private const val WHO_TT = "tr-wb-who-tetanus-2017" // WoundsBites.kt — WHO tetanus vaccines position paper (2017)

internal val earlyPregnancyProtocols = listOf(
    // =========================================================================================================
    // Ectopic pregnancy
    // =========================================================================================================
    Protocol(
        id = "ectopic-pregnancy",
        category = ProtocolCategory.Obstetric,
        title = "Ectopic pregnancy",
        summary = "Suspicion, the unstable patient, ultrasound and hCG in pregnancy of unknown location, expectant, " +
            "methotrexate or surgery, follow-up and anti-D — attributed to NICE, WHO, MoHFW and ACOG.",
        keywords = listOf(
            "ectopic", "ectopic pregnancy", "tubal pregnancy", "ruptured ectopic", "PUL", "pregnancy of unknown location",
            "methotrexate", "MTX", "beta hCG", "β-hCG", "hCG", "salpingectomy", "salpingotomy", "salpingostomy",
            "haemoperitoneum", "hemoperitoneum", "culdocentesis", "heterotopic", "adnexal mass", "garbhashay ke bahar garbh", "nali mein garbh", "Biotrexate", "Imutrex", "Folitrax",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Suspect ectopic pregnancy",
                timing = "At first contact",
                points = listOf(
                    "NICE NG126: any woman of reproductive age may be pregnant — offer a pregnancy test even with non-specific symptoms; ectopic can mimic GI or urinary illness.",
                    "NICE NG126: atypical presentation is common; about a third of women with an ectopic pregnancy have no known risk factors.",
                    "WHO MCPC 2017: consider ectopic in any woman with anaemia, PID, threatened abortion or unusual abdominal pain; examine bimanually gently — early ectopics rupture easily.",
                    "MoHFW STG 2017: risk factors — PID, endometriosis, IUCD, progestogen-only pill, pregnancy after tubal ligation or tubal surgery, ovulation induction and ART.",
                    "NICE NG126: positive test with abdominal, pelvic or cervical motion tenderness — refer immediately to an early pregnancy or out-of-hours gynaecology service.",
                ),
                checklistTitle = "Symptoms and signs (NICE NG126)",
                checklist = listOf(
                    "Abdominal or pelvic pain",
                    "Amenorrhoea or missed period",
                    "Vaginal bleeding with or without clots",
                    "Dizziness, fainting or syncope",
                    "Shoulder tip pain",
                    "Rectal pressure or pain on defecation",
                    "Pelvic, adnexal or cervical motion tenderness",
                    "Pulse >100/min or BP <100/60 mmHg",
                    "Shock or collapse",
                ),
                citations = listOf(
                    cite(NG126, "Rec 1.4.2–1.4.5, 1.4.7–1.4.8"),
                    cite(WHO_MCPC, "Diagnosis (p. S-8)"),
                    cite(MOH_STG, "Ectopic pregnancy — risk factors (p. 48)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Unstable or ruptured: resuscitate and operate",
                timing = "Immediately — do not wait for blood before surgery (WHO MCPC 2017)",
                points = listOf(
                    "ACOG PB 193: tubal ectopic pregnancy in an unstable patient is a medical emergency requiring prompt surgical intervention.",
                    "WHO MCPC 2017: shock in early pregnancy — consider ruptured ectopic; cross-match and arrange immediate laparotomy without waiting for blood.",
                    "MoHFW 2009: rapid IV fluids, blood for Hb and cross-match, oxygen, keep warm, elevate legs; monitor pulse, BP and urine output; refer to FRU for laparotomy.",
                    "WHO MCPC 2017: salpingectomy is the treatment of choice in most cases; salpingostomy only if little tubal damage and fertility is very important.",
                    "WHO MCPC 2017: significant haemorrhage — autotransfuse fresh, uninfected haemoperitoneum; without an anticoagulant bag add sodium citrate 10 mL per 90 mL of blood.",
                    "NICE NG126: refer unstable women directly to the emergency department. Cardiac arrest: see the Maternal collapse and cardiac arrest in pregnancy protocol.",
                ),
                citations = listOf(
                    cite(PB193, "Abstract"),
                    cite(WHO_MCPC, "Immediate management (pp. S-7, S-16); Autotransfusion (p. S-17)"),
                    cite(MOH09, "Case study 33 (p. 147)"),
                    cite(NG126, "Rec 1.4.1"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Stable: transvaginal ultrasound",
                points = listOf(
                    "NICE NG126: transvaginal scan for location, fetal pole and heartbeat; transabdominal if the uterus is enlarged, there is pelvic pathology, or TVS is unacceptable.",
                    "NICE NG126: ectopic — adnexal mass moving separately from the ovary ('sliding sign') containing a gestational sac with yolk sac or fetal pole.",
                    "NICE NG126: high probability — adnexal mass with an empty sac ('tubal ring' or 'bagel sign') or a complex inhomogeneous adnexal mass; interpret with clinical picture and hCG.",
                    "NICE NG126: possible ectopic — empty uterus or pseudo-sac; moderate–large free fluid may be haemoperitoneum. Scan both uterus and adnexae for heterotopic pregnancy.",
                    "MoHFW STG 2017: positive urine test; scan for absent intrauterine sac, pseudosac, cul-de-sac fluid or adnexal mass; culdocentesis if no ultrasound. Cross-match and reserve blood.",
                    "WHO MCPC 2017: non-clotting blood on culdocentesis — begin immediate management; culdocentesis is less useful than serum pregnancy test plus ultrasound.",
                ),
                citations = listOf(
                    cite(NG126, "Rec 1.5.1–1.5.3, 1.7.1–1.7.5"),
                    cite(MOH_STG, "Ectopic pregnancy — investigations (p. 49)"),
                    cite(WHO_MCPC, "Ectopic pregnancy (p. S-15)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Pregnancy of unknown location: serial hCG",
                timing = "Two serum hCG as near as possible to 48 h apart, no earlier (NICE)",
                points = listOf(
                    "NICE NG126: treat a pregnancy of unknown location as possibly ectopic; do not use hCG to locate the pregnancy; weigh symptoms above hCG results.",
                    "NICE NG126: rise >63% in 48 h — likely intrauterine (ectopic not excluded); TVS in 7–14 days, earlier if hCG ≥1,500 IU/L; if a viable intrauterine pregnancy is not confirmed, immediate review by a senior gynaecologist.",
                    "NICE NG126: fall >50% in 48 h — pregnancy unlikely to continue; urine pregnancy test 14 days later; if positive, review within 24 h.",
                    "NICE NG126: fall <50% or rise <63% — clinical review in the early pregnancy service within 24 h.",
                    "NICE NG126: further hCG only after senior review; do not use serum progesterone to diagnose viable intrauterine or ectopic pregnancy.",
                    "NICE NG126: whatever the hCG, give written advice to return with new or worsening symptoms and 24-hour emergency contact details.",
                ),
                citations = listOf(cite(NG126, "Rec 1.8.1–1.8.10")),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Stable tubal ectopic: choose management",
                points = listOf(
                    "NICE NG126: offer expectant management if stable, pain-free, mass <35 mm without heartbeat, hCG ≤1,000 IU/L and able to return; consider it at >1,000 to <1,500 IU/L.",
                    "NICE NG126: offer methotrexate if no significant pain, unruptured, mass <35 mm, no heartbeat, hCG <1,500 IU/L, no intrauterine pregnancy and able to return.",
                    "NICE NG126: hCG 1,500 to <5,000 IU/L with these criteria — offer the choice of methotrexate or surgery; methotrexate carries more risk of further intervention.",
                    "NICE NG126: methotrexate on a first visit only with a definitive ectopic diagnosis and a viable intrauterine pregnancy excluded.",
                    "NICE NG126: laparoscopy whenever possible; salpingectomy unless other infertility risk factors; salpingotomy if contralateral tube damaged — up to 1 in 5 need further treatment.",
                    "MoHFW STG 2017: refer if hCG monitoring, medical management or laparoscopy is unavailable, or for heterotopic pregnancy; secondary hospitals — laparotomy and salpingectomy.",
                    "Scope: these NICE NG126 criteria are for tubal ectopic pregnancy only; cervical, caesarean scar, interstitial and cornual pregnancies are covered by RCOG GTG 21 and are not summarised here.",
                ),
                checklistTitle = "Surgery first line (NICE NG126)",
                checklist = listOf(
                    "Significant pain",
                    "Adnexal mass ≥35 mm",
                    "Fetal heartbeat visible on ultrasound",
                    "Serum hCG ≥5,000 IU/L",
                    "Unable to return for follow-up after methotrexate",
                ),
                citations = listOf(
                    cite(NG126, "Rec 1.14.1–1.14.2, 1.15.1–1.15.3, 1.16.1, 1.17.1–1.17.3"),
                    cite(MOH_STG, "Ectopic pregnancy — treatment and referral (pp. 49–51)"),
                    cite(GTG21, "Summary (RCOG page)"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Methotrexate: regimen, cautions, follow-up",
                timing = "Serum hCG on days 4 and 7, then weekly",
                points = listOf(
                    "MoHFW STG 2017: single IM dose calculated from body surface area — 50 mg/m² (the STG also lists 1 mg/kg). Before treatment: CBC, liver and renal function tests.",
                    "MoHFW STG 2017: hCG on days 4 and 7; further dose if hCG fails to fall by more than 15% from day 4 to day 7; maximum 4 doses.",
                    "NICE NG126: hCG on days 4 and 7, then weekly until negative; if hCG plateaus or rises, reassess for further treatment.",
                    "MoHFW STG 2017: about 14% need more than one dose and under 10% need surgery; complete resolution takes 4–6 weeks.",
                    "FDA label: no breastfeeding during treatment and for 1 week after; effective contraception for 6 months after the last dose; avoid nitrous oxide anaesthesia.",
                    "FDA label: PPIs, NSAIDs, penicillins and sulfonamides can raise methotrexate levels; folic acid or its derivatives reduce its effectiveness (stated for neoplastic disease).",
                ),
                checklistTitle = "Do not give methotrexate",
                checklist = listOf(
                    "Haemodynamic instability, rupture or significant pain (NICE; MoHFW STG)",
                    "Intrauterine pregnancy not excluded (NICE)",
                    "Unable to return for follow-up (NICE)",
                    "Breastfeeding (MoHFW STG: absolute contraindication; FDA label §8.2: do not breastfeed during treatment and for 1 week after)",
                    "Immunodeficiency, alcoholism or blood dyscrasia (MoHFW STG)",
                    "Active pulmonary or peptic ulcer disease (MoHFW STG)",
                    "Hepatic, renal or haematological disorder (MoHFW STG)",
                    "Severe hypersensitivity to methotrexate (FDA label)",
                    "Relative (MoHFW STG): sac larger than 3.5 cm or embryonic cardiac activity",
                ),
                citations = listOf(
                    cite(MOH_STG, "Ectopic pregnancy — medical management (pp. 51–52)"),
                    cite(NG126, "Rec 1.15.1, 1.15.4"),
                    cite(MTX_LABEL, "§4, §7.1, §8.2, §8.3"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Expectant management and follow-up after surgery",
                timing = "Expectant: hCG on days 2, 4 and 7 (NICE)",
                points = listOf(
                    "NICE NG126: expectant — hCG days 2, 4 and 7; if each falls ≥15%, weekly until <20 IU/L; otherwise review and seek senior advice.",
                    "MoHFW STG 2017: expectant — hCG twice weekly and TVS weekly, ideally hCG <50% of initial within 7 days, then weekly until <20 IU/L; plateau or rise — treat actively.",
                    "NICE NG126: limited evidence shows no difference between expectant and medical management in resolution, tubal rupture or need for further treatment.",
                    "NICE NG126: after salpingotomy — hCG at 7 days, then weekly until negative; after salpingectomy — urine pregnancy test at 3 weeks.",
                    "WHO MCPC 2017: before discharge, counsel on fertility and future ectopic risk, offer contraception, treat anaemia with oral iron for 3 months; follow up at 4 weeks.",
                    "NICE NG126 and MoHFW STG 2017: in future pregnancies she should self-refer or report early.",
                ),
                citations = listOf(
                    cite(NG126, "Rec 1.13.2, 1.14.3–1.14.4, 1.17.4–1.17.5"),
                    cite(MOH_STG, "Ectopic pregnancy — expectant management (p. 52); p. 50"),
                    cite(WHO_MCPC, "Subsequent management (p. S-17)"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Anti-D for RhD-negative women",
                points = listOf(
                    "NICE NG126 (2026): do not offer anti-D for ectopic pregnancy or miscarriage up to 11+6 weeks; if dates disagree, use the ultrasound gestation.",
                    "NICE NG126 (2026): RhD-negative at 12+0 to 12+6 weeks having medical or surgical management — offer anti-D at least 250 IU (50 micrograms).",
                    "NICE NG126: do not use a Kleihauer test to quantify feto-maternal haemorrhage.",
                    "WHO MCPC 2017: where anti-D is routinely given, give it when managing ectopic, abortion or molar pregnancy; under 12 weeks the dose may be reduced to 50 mcg.",
                    "MoHFW STG 2017: after surgery, give anti-D if the woman is Rh negative and her husband Rh positive.",
                ),
                citations = listOf(
                    cite(NG126, "Rec 1.18.1–1.18.5"),
                    cite(WHO_MCPC, "Anti-D immunoglobulin (p. S-19)"),
                    cite(MOH_STG, "Ectopic pregnancy — treatment (p. 50)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Anti-D differs: NICE NG126 (2026) gives none up to 11+6 weeks; WHO MCPC 2017 gives 50 mcg under 12 weeks where anti-D is routine; MoHFW STG 2017 gives it to Rh-negative women with Rh-positive husbands.",
                listOf(cite(NG126, "Rec 1.18.1–1.18.2"), cite(WHO_MCPC, "p. S-19"), cite(MOH_STG, "p. 50")),
            ),
            ProtocolNote(
                "MoHFW STG 2017 writes '1 mg/kg or 50 mg/m²' with leucovorin 0.1 mg/kg IM in the same sentence as its single-dose regimen, without separating single-dose and multidose schedules — confirm the regimen in the local protocol.",
                listOf(cite(MOH_STG, "Ectopic pregnancy — medical management (p. 52)")),
            ),
            ProtocolNote(
                "The methotrexate label lists pregnancy (non-neoplastic disease) as a contraindication and ectopic pregnancy is not a labelled use; NICE noted the use was off label in 2019 and requires a viable intrauterine pregnancy to be excluded first.",
                listOf(cite(MTX_LABEL, "§4"), cite(NG126, "Rec 1.15.1 and note")),
            ),
            ProtocolNote(
                "Surgical route differs by setting: NICE NG126 advises laparoscopy whenever possible; MoHFW STG 2017 describes laparotomy with salpingectomy as the standard at secondary hospitals with limited resources.",
                listOf(cite(NG126, "Rec 1.16.1"), cite(MOH_STG, "Situation 1 (p. 49)")),
            ),
            ProtocolNote(
                "Scope: NICE NG126 criteria are for tubal ectopic pregnancy. RCOG GTG 21 also covers cervical, caesarean scar, interstitial, cornual, ovarian, abdominal and heterotopic pregnancies; its full text could not be reviewed here.",
                listOf(cite(NG126, "Rec 1.14–1.17"), cite(GTG21, "Summary (RCOG page)")),
            ),
            ProtocolNote(
                "ACOG PB 193 (reaffirmed 2025) was reviewed from its abstract only; its methotrexate protocols, contraindication box and hCG thresholds are not summarised here.",
                listOf(cite(PB193, "Abstract")),
            ),
        ),
        citations = listOf(
            cite(NG126), cite(WHO_MCPC), cite(MOH_STG), cite(MOH09), cite(PB193), cite(GTG21), cite(MTX_LABEL),
        ),
    ),
    // =========================================================================================================
    // Miscarriage, incomplete and septic abortion
    // =========================================================================================================
    Protocol(
        id = "miscarriage-septic-abortion",
        category = ProtocolCategory.Obstetric,
        title = "Miscarriage, incomplete and septic abortion",
        summary = "Heavy bleeding in early pregnancy, types of miscarriage, expectant, misoprostol or vacuum aspiration, " +
            "septic and unsafe abortion, anti-D and aftercare — attributed to MoHFW, WHO, NICE and ACOG.",
        keywords = listOf(
            "miscarriage", "abortion", "spontaneous abortion", "incomplete abortion", "inevitable abortion",
            "threatened abortion", "missed abortion", "missed miscarriage", "early pregnancy loss", "complete abortion",
            "septic abortion", "unsafe abortion", "post-abortion care", "PAC", "MVA", "manual vacuum aspiration", "EVA",
            "vacuum aspiration", "D&C", "D&E", "ERPC", "RPOC", "retained products of conception", "evacuation",
            "misoprostol", "mifepristone", "MTP", "CAC", "garbhpaat", "garbh girna", "safai", "Cytolog", "Mifegest",
            "MTP kit",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Heavy bleeding: resuscitate first",
                timing = "Immediately",
                points = listOf(
                    "WHO MCPC 2017: rapid check of vital signs, consciousness and blood loss; treat shock at once; start IV fluids after sending Hb and type and screen.",
                    "WHO MCPC 2017: heavy bleeding — a clean pad or cloth soaked in under 5 minutes, or large clots; in shock, consider ruptured ectopic (see the Ectopic pregnancy protocol).",
                    "MoHFW CAC 2023: shock — open airway, oxygen 6–8 L/min, RL or isotonic fluid 1 L in 15–20 min via 16–18 G cannula, keep warm, IV antibiotics, transfuse if needed.",
                    "WHO MCPC 2017 and MoHFW 2009: remove products of conception visible at the os with sponge, ring or ovum forceps; heavy bleeding or shock — stabilise and refer to FRU.",
                    "MoHFW CAC 2023 (uterine bleeding at vacuum aspiration): misoprostol 400 µg sublingual or 600 µg oral, uterine massage; if continuing, oxytocin 10 units in 500 mL RL/NS at 40–60 drops/min.",
                    "MoHFW CAC 2023: evacuate retained products with vacuum aspiration if suspected, or if bleeding continues.",
                ),
                checklistTitle = "Shock (MoHFW CAC 2023)",
                checklist = listOf(
                    "Fast, weak pulse ≥110/min",
                    "Systolic BP <90 or diastolic <60 mmHg",
                    "Pallor",
                    "Respiratory rate ≥30/min",
                    "Anxious, confused or unconscious",
                    "Profuse sweating",
                ),
                citations = listOf(
                    cite(WHO_MCPC, "Immediate management (p. S-7); Table S-1 footnotes (p. S-9); Box S-2 (p. S-12)"),
                    cite(CAC23, "Ch. 6 — Haemorrhage (p. 49); Shock (p. 50)"),
                    cite(MOH09, "Case study 30 (p. 146)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Identify the type of miscarriage",
                points = listOf(
                    "NICE NG126 (TVS): no heartbeat with CRL <7.0 mm — rescan ≥7 days later; CRL ≥7.0 mm — second opinion and/or rescan ≥7 days later.",
                    "NICE NG126 (TVS): no fetal pole with mean sac diameter <25.0 mm — rescan ≥7 days later; ≥25.0 mm — second opinion and/or rescan. Transabdominal only: rescan ≥14 days.",
                    "NICE NG126: do not use gestation by LMP alone to decide whether a heartbeat should be visible; one scan cannot be guaranteed 100% accurate.",
                    "NICE NG126: 'complete miscarriage' without a previous scan confirming intrauterine pregnancy — keep pregnancy of unknown location in mind and follow up to a definitive diagnosis.",
                    "WHO 2024: incomplete abortion — open os and bleeding, with products not all expelled or inconsistent with gestation; managed the same after induced or spontaneous abortion.",
                ),
                checklistTitle = "Clinical types (WHO MCPC 2017 Table S-1)",
                checklist = listOf(
                    "Threatened — light bleeding, closed cervix, uterus matches dates",
                    "Inevitable — heavy bleeding, dilated cervix, uterus matches dates",
                    "Incomplete — heavy bleeding, dilated cervix, uterus smaller than dates (± partial expulsion)",
                    "Complete — light bleeding, closed cervix, uterus smaller than dates (± history of expulsion)",
                    "Molar — heavy bleeding, dilated cervix, uterus larger than dates, grape-like tissue",
                    "Ectopic — light bleeding, abdominal pain, closed cervix (± fainting, tender adnexal mass)",
                ),
                citations = listOf(
                    cite(NG126, "Rec 1.6.1–1.6.10, 1.6.13"),
                    cite(WHO_AC, "§3.5.2"),
                    cite(WHO_MCPC, "Table S-1 (pp. S-8–S-9)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Threatened miscarriage",
                points = listOf(
                    "NICE NG126: intrauterine pregnancy with heartbeat and bleeding, no previous miscarriage — return if bleeding worsens or persists beyond 14 days; otherwise routine antenatal care.",
                    "NICE NG126 (2021): bleeding, intrauterine pregnancy confirmed on scan and a previous miscarriage — offer vaginal micronised progesterone 400 mg twice daily.",
                    "NICE NG126: if a fetal heartbeat is confirmed, continue progesterone until 16 completed weeks.",
                    "WHO MCPC 2017: medical treatment usually unnecessary; avoid strenuous activity and intercourse, bed rest not needed; do not give oestrogens, progestins or tocolytics.",
                    "MoHFW 2009: threatened abortion — restrict activities; no medication required.",
                    "NICE NG126 (2026): RhD-negative at 12+0 to 12+6 weeks with heavy or recurrent bleeding — consider anti-D at least 250 IU (50 micrograms).",
                ),
                citations = listOf(
                    cite(NG126, "Rec 1.9.1–1.9.3, 1.18.3"),
                    cite(WHO_MCPC, "Threatened abortion (p. S-11)"),
                    cite(MOH09, "Session 8a (p. 98)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Incomplete or missed miscarriage: choose the method",
                points = listOf(
                    "WHO 2024: incomplete abortion <14 weeks — vacuum aspiration or medical management; expectant can be as effective as misoprostol but expulsion takes longer and is more often incomplete.",
                    "NICE NG126: confirmed miscarriage — expectant management for 7–14 days first line; offer medical management if expectant is not acceptable.",
                    "WHO MCPC 2017: decide by haemodynamic stability, staff skills, facility and the woman's preference; medical only if stable and ectopic not suspected.",
                    "ACOG PB 200: offer the full range of options — expectant, medical and surgical.",
                    "MoHFW 2009: inevitable or incomplete up to 12 weeks — MVA; after 12 weeks — oxytocin drip 10–20 units; heavy bleeding — refer to FRU.",
                ),
                checklistTitle = "Consider other than expectant (NICE NG126)",
                checklist = listOf(
                    "Increased risk of haemorrhage (e.g. late first trimester)",
                    "Previous adverse or traumatic pregnancy experience",
                    "Coagulopathy or unable to have a blood transfusion",
                    "Evidence of infection",
                ),
                citations = listOf(
                    cite(WHO_AC, "Rec 35 (§3.5.2)"),
                    cite(NG126, "Rec 1.10.1–1.10.2"),
                    cite(WHO_MCPC, "Box S-2 (p. S-12)"),
                    cite(PB200, "Abstract"),
                    cite(MOH09, "Session 8a (p. 98)"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Medical management: misoprostol ± mifepristone",
                points = listOf(
                    "WHO 2024: incomplete abortion, uterus <14 weeks — misoprostol 600 µg orally or 400 µg sublingually.",
                    "WHO 2024: uterus ≥14 weeks — misoprostol 400 µg sublingual, vaginal or buccal every 3 h, repeated as needed; caution with a previous uterine incision.",
                    "NICE NG126: incomplete miscarriage — single misoprostol 600 µg (vaginal, oral or sublingual), or 800 µg to align protocols; do not give mifepristone.",
                    "NICE NG126 and WHO 2024: missed miscarriage — mifepristone 200 mg orally, then misoprostol 800 µg (NICE: 48 h later, vaginal, oral or sublingual; WHO, <14 weeks: buccal, sublingual or vaginal, at least 24 h later).",
                    "MoHFW CAC 2023: incomplete abortion — misoprostol 400 µg sublingual or 600 µg oral and watch the bleeding, or vacuum aspiration under antibiotic cover.",
                    "NICE NG126: offer analgesia and antiemetics; no bleeding within 48 h of misoprostol — contact the service; urine pregnancy test at 3 weeks.",
                ),
                checklistTitle = "Before mifepristone or misoprostol (MoHFW CAC 2023, listed for medical methods of abortion)",
                checklist = listOf(
                    "Do not use: confirmed or suspected ectopic pregnancy or undiagnosed adnexal mass",
                    "Do not use: Hb <8 g/dL",
                    "Do not use: uncontrolled hypertension, BP >160/100 mmHg",
                    "Do not use: chronic adrenal failure or inherited porphyria",
                    "Do not use: severe renal, liver or respiratory disease; uncontrolled seizures",
                    "Do not use: glaucoma; allergy to mifepristone, misoprostol or other prostaglandins",
                    "Do not use: no access to emergency care",
                    "Caution: IUCD in situ — remove it before giving the drugs",
                    "Caution: uterine scar (caesarean section, hysterotomy or myomectomy)",
                    "Caution: long-term systemic corticosteroids; coagulopathy or anticoagulants",
                    "Caution: heart disease or cardiovascular risk factors",
                ),
                citations = listOf(
                    cite(WHO_AC, "Rec 31, 36a–36b"),
                    cite(NG126, "Rec 1.11.1–1.11.9"),
                    cite(CAC23, "Ch. 6 — Delayed complications: incomplete abortion (p. 51); Ch. 7 — Contraindications; Special precautions (pp. 55–56)"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Surgical evacuation: vacuum aspiration",
                points = listOf(
                    "WHO 2024: surgical management <14 weeks — vacuum aspiration; recommend against sharp curettage (D&C), including sharp curette checks after aspiration. ≥14 weeks — D&E.",
                    "MoHFW CAC 2023: vacuum aspiration (MVA or EVA) for incomplete abortion up to 12 weeks uterine size; discourage D&C — major complications 2–3 times more frequent.",
                    "NICE NG126: offer a choice of MVA under local anaesthetic in a clinic, or surgical management in theatre under general anaesthetic.",
                    "WHO 2024: surgical abortion — offer NSAIDs routinely and, under 14 weeks, a paracervical block; recommend against routine general anaesthesia.",
                    "WHO 2024: prophylactic antibiotics for surgical (not medical) abortion. MoHFW CAC 2023: single oral ampicillin or azithromycin 1 g plus metronidazole 800 mg.",
                    "MoHFW CAC 2023: vacuum aspiration contraindicated if ectopic is suspected; acute pelvic infection — only under antibiotic cover; suspected perforation — see its management.",
                    "WHO MCPC 2017: molar pregnancy — cross-match, evacuate by vacuum aspiration (high perforation risk with a metal curette); once evacuation is under way, oxytocin 20 units in 1 L NS or RL at 60 drops/min.",
                ),
                citations = listOf(
                    cite(WHO_AC, "Rec 9, 11–12, 23, 25"),
                    cite(CAC23, "Ch. 6 (pp. 37–38, 40)"),
                    cite(NG126, "Rec 1.12.1"),
                    cite(WHO_MCPC, "Molar pregnancy — immediate management (pp. S-17–S-18)"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Septic abortion: antibiotics, then evacuate",
                timing = "Antibiotics as soon as possible, before evacuation (WHO MCPC 2017)",
                points = listOf(
                    "WHO MCPC 2017: begin antibiotics before MVA — clindamycin 600 mg IV 6–8-hourly plus gentamicin 5 mg/kg IV 24-hourly; or ampicillin 2 g IV 6-hourly plus gentamicin.",
                    "MoHFW 2009: septic abortion with peritonitis — first dose of IV ampicillin, gentamicin and metronidazole, then refer to FRU.",
                    "MoHFW CAC 2023: ampicillin or azithromycin 1 g plus metronidazole 400 mg; injectable antibiotics and IV fluids if severe infection.",
                    "WHO MCPC 2017: sepsis is more likely with retained products and delayed evacuation. MoHFW CAC 2023: evacuate by vacuum aspiration cautiously — an infected uterus perforates more easily.",
                    "SSC 2026 (strong): septic shock, or probable or definite sepsis without shock — antimicrobials immediately, ideally within 1 h of recognition.",
                    "SSC 2026 (conditional): early source control (here, evacuation), ideally within 6 h. Cultures, lactate, fluids, vasopressors: see the Sepsis and septic shock protocol.",
                ),
                checklistTitle = "Signs of infection or sepsis (WHO MCPC 2017 Table S-2)",
                checklist = listOf(
                    "Fever or chills",
                    "Foul-smelling vaginal discharge",
                    "Purulent cervical discharge",
                    "Lower abdominal pain, rebound tenderness",
                    "Tender uterus, cervical motion tenderness",
                    "Prolonged bleeding",
                    "Malaise",
                ),
                scoreIds = listOf("qsofa"),
                doses = listOf(
                    StepDose(
                        label = "Gentamicin — septic abortion (WHO MCPC 2017)",
                        dose = WeightDose(5.0, 5.0, unit = "mg", per = "IV every 24 h"),
                        note = "With clindamycin 600 mg IV every 6–8 h, or with ampicillin 2 g IV every 6 h if clindamycin is unavailable. No maximum stated.",
                    ),
                ),
                citations = listOf(
                    cite(WHO_MCPC, "Table S-2 and footnote (pp. S-9–S-10); Box S-1 (p. S-10)"),
                    cite(MOH09, "Case study 32 (p. 147)"),
                    cite(CAC23, "Ch. 6 — Delayed complications: infection/sepsis (p. 51)"),
                    cite(SSC26, "Recommendations: Antibiotic initiation; Source control"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Unsafe abortion: injury and tetanus",
                points = listOf(
                    "WHO MCPC 2017: suspected unsafe abortion — examine for infection and uterine, vaginal or bowel injury; irrigate the vagina to remove herbs, local medications or caustics.",
                    "WHO MCPC 2017: uterine, vaginal or bowel injury — laparotomy to repair it, with manual vacuum aspiration at the same time; seek further help as needed.",
                    "MoHFW CAC 2023: suspected perforation — stop, remove instruments, IV fluids; refer up if unstable, still bleeding or cannula ≥8 mm; bowel or omentum seen — IV antibiotics too.",
                    "WHO MCPC 2017 (unsafe abortion): immunised — tetanus toxoid booster 0.5 mL IM; not immunised — antitetanus serum 1500 units IM and toxoid 0.5 mL IM after 4 weeks.",
                    "WHO 2017 tetanus position paper (later): after an injury, a vaccine dose, preferably Td; dirty wounds in incompletely immunised women — TIG, preferably of human origin. See the Tetanus prophylaxis protocol.",
                ),
                checklistTitle = "Injury (WHO MCPC 2017 Table S-2)",
                checklist = listOf(
                    "Cramping or abdominal pain",
                    "Rebound tenderness",
                    "Abdominal distension or rigid abdomen",
                    "Shoulder pain",
                    "Nausea or vomiting",
                    "Fever",
                ),
                citations = listOf(
                    cite(WHO_MCPC, "Specific management (p. S-10); Table S-2 (p. S-10); Box S-6 (p. S-68)"),
                    cite(CAC23, "Ch. 6 — Uterine perforation (p. 49)"),
                    cite(WHO_TT, "Immunization in the case of injury (p. 74)"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Anti-D, follow-up and contraception",
                points = listOf(
                    "NICE NG126 (2026): no anti-D up to 11+6 weeks; RhD-negative at 12+0–12+6 weeks with medical or surgical management — offer at least 250 IU.",
                    "WHO 2024: medical or surgical abortion <12 weeks — recommend against anti-D; standard care applies from 12 weeks. Rh testing is not a prerequisite for providing abortion care.",
                    "MoHFW CAC 2023: anti-D 50 µg to Rh-negative women after vacuum aspiration and with mifepristone; 300 µg for terminations beyond 12 weeks.",
                    "WHO 2024: no routine follow-up after uncomplicated abortion; return for prolonged heavy bleeding, no bleeding after medical treatment, unrelieved pain or fever.",
                    "NICE NG126: positive urine pregnancy test at 3 weeks — review for retained, molar or ectopic pregnancy; negative but heavy bleeding, pain or fever — assess.",
                    "WHO MCPC 2017: offer contraception — hormonal methods and IUD immediately; delay IUD if infection or Hb <7 g/dL. MoHFW CAC 2023: IFA for at least 3 months.",
                    "WHO MCPC 2017 (molar pregnancy): hormonal contraception for at least 1 year; urine pregnancy test every 8 weeks for at least 1 year — refer urgently to a tertiary centre if not negative after 8 weeks or positive again.",
                ),
                citations = listOf(
                    cite(NG126, "Rec 1.11.7–1.11.9, 1.18.1–1.18.2"),
                    cite(WHO_AC, "Rec 8, 34"),
                    cite(CAC23, "Ch. 6 — Before discharge (p. 47); Ch. 7 Day 1 (p. 57); Ch. 8 (p. 68)"),
                    cite(WHO_MCPC, "Table S-6 (p. S-15); Molar pregnancy — subsequent management (p. S-18)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Anti-D before 12 weeks differs: NICE NG126 (2026) and WHO 2024 advise against it; MoHFW CAC 2023 gives 50 µg after vacuum aspiration or medical abortion; WHO MCPC 2017 gives 50 mcg where anti-D is routine.",
                listOf(cite(NG126, "Rec 1.18.1"), cite(WHO_AC, "Rec 8"), cite(CAC23, "pp. 47, 57"), cite(WHO_MCPC, "p. S-19")),
            ),
            ProtocolNote(
                "ACOG revised its Rh testing and anti-D guidance for abortion or pregnancy loss before 12 weeks in a 2024 Clinical Practice Update; its full text could not be retrieved and is not summarised here.",
                listOf(cite(PB200, "Supplemental: December 2024 Clinical Practice Update")),
            ),
            ProtocolNote(
                "Progesterone in threatened miscarriage is contested: NICE NG126 (2021) offers vaginal micronised progesterone 400 mg twice daily after a previous miscarriage; WHO MCPC 2017 advises against hormones; MoHFW 2009 gives no medication.",
                listOf(cite(NG126, "Rec 1.9.2"), cite(WHO_MCPC, "p. S-11"), cite(MOH09, "p. 98")),
            ),
            ProtocolNote(
                "WHO MCPC 2017 (incomplete abortion >12–14 weeks: misoprostol 200 µg vaginally 4-hourly, maximum 800 µg, or oxytocin 40 units in 1 L) differs from the later WHO 2024 Recommendation 36b (400 µg every 3 h).",
                listOf(cite(WHO_MCPC, "Table S-4 (p. S-13)"), cite(WHO_AC, "Rec 36b")),
            ),
            ProtocolNote(
                "Antibiotics for septic abortion differ by source: WHO MCPC 2017 — clindamycin or ampicillin with gentamicin; MoHFW 2009 — ampicillin, gentamicin and metronidazole; MoHFW CAC 2023 — ampicillin or azithromycin with metronidazole.",
                listOf(cite(WHO_MCPC, "Table S-2 footnote"), cite(MOH09, "p. 147"), cite(CAC23, "p. 51")),
            ),
            ProtocolNote(
                "India — MoHFW CAC 2023: the MTP (Amendment) Act 2021 and Rules 2021 allow termination up to 20 weeks on extended indications for any woman, up to 24 weeks for special categories, and at any gestation for substantial fetal abnormality approved by a Medical Board.",
                listOf(cite(CAC23, "Ch. 2 (p. 5)")),
            ),
            ProtocolNote(
                "India — MoHFW CAC 2023: for a minor, POCSO Act section 19(1) requires reporting to the local or Special Juvenile Police (or the hospital medico-legal authority); providers need not file an FIR, and care must not be held up.",
                listOf(cite(CAC23, "Ch. 2 (p. 5)")),
            ),
        ),
        citations = listOf(
            cite(CAC23), cite(WHO_AC), cite(NG126), cite(WHO_MCPC), cite(MOH09), cite(PB200), cite(SSC26),
        ),
    ),
    // =========================================================================================================
    // Hyperemesis gravidarum
    // =========================================================================================================
    Protocol(
        id = "hyperemesis-gravidarum",
        category = ProtocolCategory.Obstetric,
        title = "Hyperemesis gravidarum",
        summary = "Severity (PUQE, HELP), admission, saline with potassium and no dextrose before thiamine, antiemetic " +
            "escalation, Wernicke's encephalopathy, steroids and VTE prophylaxis — attributed to RCOG, MoHFW and ACOG.",
        keywords = listOf(
            "hyperemesis", "hyperemesis gravidarum", "HG", "severe vomiting in pregnancy", "NVP", "morning sickness",
            "Wernicke", "Wernicke's encephalopathy", "PUQE", "HELP score", "thiamine", "ketonuria", "dehydration",
            "ondansetron", "metoclopramide", "prochlorperazine", "promethazine", "doxylamine", "Doxinate", "Emeset",
            "Vomikind", "Perinorm", "Stemetil", "Phenergan", "Avomine", "ulti", "jee machlana",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise hyperemesis and grade severity",
                timing = "At presentation",
                points = listOf(
                    "RCOG GTG 69: NVP starts before 16 weeks with other causes excluded; a first onset after 16 weeks needs investigation for other causes.",
                    "RCOG GTG 69 (Windsor definition): HG — severe nausea and/or vomiting from early pregnancy, unable to eat and drink normally, strongly limiting daily life; dehydration supports it.",
                    "RCOG GTG 69: PUQE-24 (3 questions, 1–5 points each) — moderate 7–12, severe 13–15. HELP score — mild ≤19, moderate 20–32, severe 33–60.",
                    "RCOG GTG 69: PUQE tracks response in mild–moderate NVP but is not valid for severe NVP or HG; use HELP to follow severe cases.",
                    "RCOG GTG 69: ketonuria is not an indicator of dehydration and should not be used to assess severity.",
                    "ACOG PB 189: distinguish NVP from other causes; early treatment may prevent complications including hospitalisation.",
                ),
                checklistTitle = "Look for other causes (RCOG GTG 69 §4, Table 1)",
                checklist = listOf(
                    "Abdominal pain",
                    "Urinary symptoms",
                    "Infection",
                    "Prescription or recreational drugs",
                    "Chronic Helicobacter pylori infection",
                    "Onset after 16 weeks",
                ),
                citations = listOf(
                    cite(GTG69, "§4; Appendix IIa–IIb; Table 1"),
                    cite(PB189, "Abstract"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Where to treat: community, day care or admission",
                points = listOf(
                    "RCOG GTG 69: mild NVP — community care with antiemetics; unable to tolerate oral antiemetics or fluids — ambulatory day care with IV fluids, thiamine and parenteral antiemetics.",
                    "MoHFW SBA 2010: excessive vomiting with dry tongue, poor skin turgor, low urine output or tachycardia — start IV Ringer lactate 500 mL and refer to the MO at a 24-hour PHC or FRU.",
                    "MoHFW 2009: severe cases need admission, IV fluids and antiemetics. ICMR STW 2025: persistent vomiting is a danger signal for reporting to a facility.",
                    "RCOG GTG 69: recurrent or ongoing NVP/HG despite day care — admit, because of electrolyte imbalance and nutritional deficiency.",
                    "RCOG GTG 69: inpatients — schedule ultrasound for viability, gestation, multiple pregnancy or trophoblastic disease (next available slot unless urgent).",
                ),
                checklistTitle = "Consider admission if any (RCOG GTG 69)",
                checklist = listOf(
                    "Continued vomiting and unable to keep down oral antiemetics",
                    "Continued vomiting with clinical dehydration or weight loss >5% despite oral antiemetics",
                    "Confirmed or suspected comorbidity, e.g. UTI with oral antibiotics not tolerated",
                    "Epilepsy, diabetes, HIV, hypoadrenalism or psychiatric illness where missed oral intake or medication is risky",
                ),
                citations = listOf(
                    cite(GTG69, "§5.1"),
                    cite(SBA, "Table 2, row C (p. 37)"),
                    cite(MOH09, "Hyperemesis gravidarum notes (p. 92)"),
                    cite(ICMR_ANC, "Danger signals"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Examine and investigate",
                points = listOf(
                    "RCOG GTG 69: pulse, BP, respiratory rate, SpO₂, temperature, weight; signs of dehydration; weight loss ≥5%; confusion, nystagmus or ataxia (Wernicke's).",
                    "RCOG GTG 69: urea and electrolytes (sodium, potassium, kidney injury), full blood count, blood glucose (exclude DKA in diabetes), urinalysis for nitrites and ultrasound.",
                    "RCOG GTG 69: refractory or previous admissions — thyroid and liver function, calcium and phosphate, amylase and a venous blood gas.",
                    "RCOG GTG 69: HG causes hyponatraemia, hypokalaemia and hypochloraemic alkalosis; up to two-thirds have raised free T4 or low TSH that resolves — antithyroid drugs are unnecessary.",
                    "RCOG GTG 69: liver tests are abnormal in up to 40% of HG (mainly transaminases) and improve as HG resolves.",
                ),
                citations = listOf(cite(GTG69, "Table 1; §4")),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "IV fluids: saline with potassium, no dextrose before thiamine",
                timing = "Urea and electrolytes daily while on IV fluids (RCOG)",
                points = listOf(
                    "RCOG GTG 69: 0.9% sodium chloride with potassium chloride in each bag, guided by daily electrolytes, is the most appropriate IV hydration.",
                    "RCOG GTG 69: dextrose infusions are not recommended — dextrose can precipitate Wernicke's encephalopathy when thiamine-deficient; give high (e.g. 100 mg) parenteral thiamine.",
                    "RCOG GTG 69: dextrose-containing fluids are appropriate for nausea and vomiting in the third trimester (starvation ketosis).",
                    "RCOG GTG 69: monitor fluids and electrolytes daily to detect and treat hyponatraemia and hypokalaemia — see the Hyponatraemia and Hypokalaemia protocols.",
                    "MoHFW 2009: admit, IV Ringer lactate or DNS, urine ketones 4-hourly until negative, antiemetics and multivitamins; discharge 24 h after vomiting stops and dehydration is corrected.",
                ),
                citations = listOf(
                    cite(GTG69, "§5.2.3; §6.1"),
                    cite(MOH09, "Case study 25 (p. 143)"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Thiamine and Wernicke's encephalopathy",
                timing = "On admission, before any dextrose or parenteral nutrition",
                points = listOf(
                    "RCOG GTG 69: thiamine for all women admitted with vomiting or severely reduced intake — oral 100 mg three times daily or IV as vitamin B complex.",
                    "RCOG GTG 69: thiamine stores can deplete within 1 week without intake, causing tachycardia, weakness and reduced deep tendon reflexes.",
                    "RCOG GTG 69: Wernicke's in HG tends to be episodic and of slow onset; it is a potentially fatal but preventable emergency.",
                    "RCOG GTG 69: in a review of 177 cases — chronic cognitive disorders 65.4%, pregnancy loss 50%, maternal death 5%.",
                    "MoHFW 2009: complications of hyperemesis include Wernicke's encephalopathy, peripheral neuritis, stress ulcers, oesophageal tears or rupture and jaundice.",
                ),
                checklistTitle = "Suspect Wernicke's encephalopathy (RCOG GTG 69)",
                checklist = listOf(
                    "Blurred vision",
                    "Unsteadiness; gait or finger–nose ataxia",
                    "Confusion, memory problems or drowsiness",
                    "Nystagmus or ophthalmoplegia",
                    "Reduced or absent reflexes",
                ),
                citations = listOf(
                    cite(GTG69, "Key recommendations; §5.1; §5.2.3"),
                    cite(MOH09, "Case study 25 (p. 143)"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Antiemetics: step up and combine",
                points = listOf(
                    "RCOG GTG 69: if one drug fails, combine drugs from different classes; persistent vomiting may need sublingual, IV, IM, SC, rectal or transdermal routes.",
                    "RCOG GTG 69 first line: doxylamine/pyridoxine 20/20 mg at night (add 10/10 mg morning and lunchtime); cyclizine 50 mg 8-hourly PO/IM/IV; promethazine 12.5–25 mg 4–8-hourly PO/IM/IV.",
                    "RCOG GTG 69 first line: prochlorperazine 5–10 mg 6–8-hourly PO, 12.5 mg 8-hourly IM/IV or 25 mg PR daily; chlorpromazine 10–25 mg 4–6-hourly PO/IM/IV.",
                    "RCOG GTG 69 second line: metoclopramide 5–10 mg 8-hourly PO/IV/IM/SC, IV by slow bolus over ≥3 min; domperidone 10 mg 8-hourly PO or 30 mg 12-hourly PR.",
                    "RCOG GTG 69 second line: ondansetron 4 mg 8-hourly or 8 mg 12-hourly PO, 8 mg IV over 15 min 12-hourly, or 16 mg PR daily; may need laxatives.",
                    "FDA labels: ondansetron — ECG monitoring if hypokalaemia or hypomagnesaemia, avoid in congenital long QT; metoclopramide — boxed warning for tardive dyskinesia, shortest duration.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Metoclopramide — maximum in 24 h (EMA, quoted by RCOG GTG 69)",
                        dose = WeightDose(0.5, 0.5, unit = "mg", per = "in 24 h (maximum, whichever is lower)"),
                        note = "EMA: maximum 30 mg or 0.5 mg/kg in 24 h, for up to 5 days. RCOG GTG 69 authors: may continue beyond 5 days if it helps.",
                        maxTotal = 30.0,
                    ),
                ),
                citations = listOf(
                    cite(GTG69, "§5.2.1; §5.2.2; Appendix III"),
                    cite(OND_LABEL, "§5.2"),
                    cite(MCP_LABEL, "Boxed warning"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Refractory: steroids, nutrition and the team",
                points = listOf(
                    "RCOG GTG 69: corticosteroids only after IV fluids and regular antiemetics fail, added to antiemetics — hydrocortisone 100 mg IV twice daily.",
                    "RCOG GTG 69: once improving, prednisolone 40–50 mg daily orally, tapered by 5–10 mg a week to the lowest dose that controls symptoms; monitor BP and screen for diabetes.",
                    "RCOG GTG 69: H2 blockers or PPIs for reflux, oesophagitis or gastritis; endoscopy is safe and indicated for haematemesis or severe epigastric pain.",
                    "RCOG GTG 69: when all medical therapy fails — enteral tube feeding or parenteral nutrition with gastroenterology and the multidisciplinary team; watch for refeeding syndrome.",
                    "RCOG GTG 69: offer every therapeutic option before considering termination; the decision is multidisciplinary, documented, with counselling and psychiatric input if needed.",
                ),
                citations = listOf(cite(GTG69, "§5.2.1; §5.2.3; §7.1–7.3; Appendix III")),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "VTE prophylaxis and discharge",
                points = listOf(
                    "RCOG GTG 69: offer LMWH thromboprophylaxis to women admitted with HG; graduated stockings if LMWH is contraindicated.",
                    "RCOG 2026 position statement: hyperemesis scores 3 and immobility or dehydration 4 for VTE risk; admitted with HG — LMWH while the risk lasts and 7 days after.",
                    "RCOG 2026 position statement: enoxaparin <100 kg 40 mg daily; 100–129 kg 60 mg; 130–169 kg 80 mg; ≥170 kg 0.6 mg/kg/day (dalteparin 5000, 7500, 10 000 units).",
                    "RCOG GTG 69: discharge once antiemetics, oral food and fluids are tolerated and other conditions managed; continue antiemetics — almost a third are readmitted.",
                    "RCOG GTG 69: iron may worsen symptoms — consider avoiding it or another route; ask about constipation. Symptoms into late second or third trimester — serial growth scans.",
                ),
                citations = listOf(
                    cite(GTG69, "§5.2.3; §6"),
                    cite(VTE_PS, "Hyperemesis; Table 1; Appendix I"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Fluids and ketones differ: MoHFW 2009 uses Ringer lactate or DNS with 4-hourly urine ketones; RCOG GTG 69 (2024) uses 0.9% saline with potassium, avoids dextrose before thiamine and says ketonuria should not guide care.",
                listOf(cite(MOH09, "Case study 25 (p. 143)"), cite(GTG69, "§4; §6; §6.1")),
            ),
            ProtocolNote(
                "Thromboprophylaxis duration differs: RCOG GTG 69 allows stopping LMWH at discharge; the RCOG 2026 position statement continues it until the transient risk resolves and for a further 7 days.",
                listOf(cite(GTG69, "§5.2.3"), cite(VTE_PS, "Hyperemesis and other causes of prolonged immobility")),
            ),
            ProtocolNote(
                "RCOG GTG 69 gives no treatment dose for established Wernicke's encephalopathy. Treatment-dose regimens for alcohol-related Wernicke's are in the Alcohol intoxication and withdrawal protocol.",
                listOf(cite(GTG69, "§5.2.3")),
            ),
            ProtocolNote(
                "Ondansetron in the first trimester is debated: the EMA advised avoiding it; the MHRA did not; RCOG GTG 69 keeps it second line — orofacial clefts 14 vs 11 per 10 000 births.",
                listOf(cite(GTG69, "§5.2.2")),
            ),
            ProtocolNote(
                "Corticosteroids: RCOG GTG 69 (2024) reports no increase in orofacial clefts, cardiac defects or hypospadias after first-trimester use (about 3500 exposures); the app's Nausea & vomiting page carries an older oral-cleft caution.",
                listOf(cite(GTG69, "§5.2.2")),
            ),
            ProtocolNote(
                "Ginger: RCOG GTG 69 advises clinicians not to suggest ginger for severe NVP or HG — a survey of 512 women found little or no benefit and unpleasant effects or worse symptoms in 54%.",
                listOf(cite(GTG69, "§5.2.1")),
            ),
            ProtocolNote(
                "ACOG PB 189 (reaffirmed 2024) was reviewed from its abstract only; its algorithm and doses are on the app's Nausea & vomiting page and are not repeated here.",
                listOf(cite(PB189, "Abstract")),
            ),
        ),
        citations = listOf(
            cite(GTG69), cite(VTE_PS), cite(MOH09), cite(SBA), cite(ICMR_ANC), cite(PB189), cite(OND_LABEL), cite(MCP_LABEL),
        ),
    ),
)
