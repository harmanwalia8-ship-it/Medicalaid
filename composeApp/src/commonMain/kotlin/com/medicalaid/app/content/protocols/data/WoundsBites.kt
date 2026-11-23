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
 * Animal bites (rabies post-exposure prophylaxis) and tetanus prophylaxis in wounds. Rules:
 * - Doses, schedules, thresholds and time windows exactly as the cited document states them (checked against the full
 *   texts of the NCDC National Guidelines for Rabies Prophylaxis 2019 (scanned; read by OCR), the NCDC training module
 *   for medical officers, the WHO 2018 rabies position paper, WHO TRS 1012 and the CDC rabies PEP page; IDSA 2014
 *   against its recommendations and Table 5; the monoclonal antibody trials against their abstracts and methods; the
 *   Rabishield manufacturer's product page; November 2026).
 * - Every point is attributed ("NCDC 2019: …", "NCDC MO module: …", "WHO 2018: …", "WHO TRS 1012: …", "IDSA 2014: …",
 *   "CDC: …").
 * - Locators: NCDC 2019 — section and printed page; NCDC MO module — section and printed page; WHO position paper —
 *   heading and printed WER page; WHO TRS 1012 — section and printed page; IDSA 2014 — question number and table.
 * - India's national guidelines (2019) have not incorporated rabies monoclonal antibodies; their doses are reported from
 *   the published trials and are product-specific.
 */

internal val woundsBitesSources: List<Source> = listOf(
    // Rabies -----------------------------------------------------------------------------------------------------
    Source(
        "tr-wb-ncdc-ngrp-2019", Authority.MoHFW, "National Rabies Control Programme — NCDC, Dte.GHS",
        "National Guidelines for Rabies Prophylaxis, 2019", "2019",
        "https://ncdc.mohfw.gov.in/uploads/resource/1769332447_National-Guidelines-for-Rabies-Prophylaxis.pdf",
        status = "Current national edition (revised after WHO TRS 1012); listed on the NCDC technical guidelines page; scanned PDF read by OCR, November 2026",
    ),
    Source(
        "tr-wb-ncdc-mo-module", Authority.MoHFW, "NCDC training module (National Rabies Control Programme)",
        "Training Module on Rabies for Medical Officers", "2023",
        "https://ncdc.mohfw.gov.in/uploads/resource/1769335160_TRAINING-MODULE-FOR-MEDICAL-OFFICERS.pdf",
        status = "Undated; PDF created August 2023; follows the 2019 national guidelines and adds operational detail",
    ),
    Source(
        "tr-wb-who-rabies-2018", Authority.WHO, "WHO position paper (Wkly Epidemiol Rec 2018;93(16):201–220)",
        "Rabies vaccines: WHO position paper – April 2018", "2018",
        "https://www.who.int/publications/i/item/who-wer9316",
        status = "Replaces the 2010 position paper; no later WHO rabies position paper found (November 2026)",
    ),
    Source(
        "tr-wb-who-trs1012-2018", Authority.WHO, "WHO Technical Report Series No. 1012 (ISBN 978-92-4-121021-8)",
        "WHO Expert Consultation on Rabies: third report", "2018",
        "https://iris.who.int/handle/10665/272364",
    ),
    Source(
        "tr-wb-cdc-rabies-pep", Authority.CDC, "Clinical guidance (web page)",
        "Rabies Post-exposure Prophylaxis Guidance", "2026",
        "https://www.cdc.gov/rabies/hcp/clinical-care/post-exposure-prophylaxis.html",
        status = "US guidance; reviewed 10 September 2026, updated 17 September 2026",
    ),
    Source(
        "tr-wb-idsa-ssti-2014", Authority.IDSA, "Practice guideline (Clin Infect Dis 2014;59(2):147–159; full text e10–e52)",
        "Practice Guidelines for the Diagnosis and Management of Skin and Soft Tissue Infections: 2014 Update by the Infectious Diseases Society of America",
        "2014",
        "https://doi.org/10.1093/cid/ciu296",
        status = "Stevens DL et al.; no later IDSA skin and soft-tissue guideline found (November 2026)",
    ),
    Source(
        "tr-wb-rmab-phase4-2025", Authority.Paper, "Phase 4 randomised trial (Lancet 2025;406(10503):627–635)",
        "Post-exposure prophylaxis regimen of rabies monoclonal antibody and vaccine in category 3 potential exposure patients: a phase 4, open-label, randomised, active-controlled trial",
        "2025",
        "https://doi.org/10.1016/S0140-6736(25)00735-4",
        status = "Kulkarni PS et al.; 15 Indian hospitals; funded by Serum Institute of India (manufacturer); CTRI/2019/06/019622; abstract reviewed",
    ),
    Source(
        "tr-wb-sii-rabishield", Authority.Paper, "Manufacturer product page (Serum Institute of India)",
        "RABISHIELD — Rabies Human Monoclonal Antibody", "2018",
        "https://seruminstitute.com/product_ind_rabishield.php",
        status = "Manufacturer information (product text © 2018), checked November 2026; no package insert or CDSCO approval text found online",
    ),
    Source(
        "tr-wb-twinrab-phase3-2021", Authority.Paper, "Phase 3 randomised trial (Clin Infect Dis 2021;73(9):e2722–e2728)",
        "A Phase 3, Randomized, Open-label, Noninferiority Trial Evaluating Anti-Rabies Monoclonal Antibody Cocktail (Twinrab) Against Human Rabies Immunoglobulin (HRIG)",
        "2021",
        "https://doi.org/10.1093/cid/ciaa779",
        status = "Kansagra K et al.; most authors from Cadila Healthcare (Zydus, manufacturer); CTRI/2017/07/009038; abstract and methods reviewed",
    ),
    Source(
        "tr-wb-twinrab-inuse-2025", Authority.Paper, "Laboratory study (Cureus 2025;17(12):e98418)",
        "In-Use Stability and Functional Efficacy of a Monoclonal Antibody Cocktail (TwinRab) for Rabies Post-exposure Prophylaxis",
        "2025",
        "https://doi.org/10.7759/cureus.98418",
        status = "Kalita P et al.; authors from Zydus Lifesciences (manufacturer); cited only for the approved dose and product strengths",
    ),
    // Tetanus ----------------------------------------------------------------------------------------------------
    Source(
        "tr-wb-who-tetanus-2017", Authority.WHO, "WHO position paper (Wkly Epidemiol Rec 2017;92(6):53–76)",
        "Tetanus vaccines: WHO position paper – February 2017", "2017",
        "https://www.who.int/publications/i/item/who-wer9206",
        status = "Replaces the 2006 position paper; no later WHO tetanus position paper found (November 2026)",
    ),
    Source(
        "tr-wb-who-tetanus-tx-2010", Authority.WHO, "WHO technical note (WHO/HSE/GAR/DCE/2010.2)",
        "Current recommendations for treatment of tetanus during humanitarian emergencies", "2010",
        "https://iris.who.int/handle/10665/70219",
        status = "Written for emergency-affected populations; cited for treatment doses only",
    ),
    Source(
        "tr-wb-acip-tdap-2018", Authority.CDC, "ACIP recommendations (MMWR Recomm Rep 2018;67(No. RR-2):1–44)",
        "Prevention of Pertussis, Tetanus, and Diphtheria with Vaccines in the United States: Recommendations of the Advisory Committee on Immunization Practices (ACIP)",
        "2018",
        "https://www.cdc.gov/mmwr/volumes/67/rr/rr6702a1.htm",
        status = "Liang JL et al.; Td or Tdap choice updated by ACIP in 2019 (MMWR 2020;69:77–83)",
    ),
    Source(
        "tr-wb-acip-tdap-2019", Authority.CDC, "ACIP update (MMWR Morb Mortal Wkly Rep 2020;69(3):77–83)",
        "Use of Tetanus Toxoid, Reduced Diphtheria Toxoid, and Acellular Pertussis Vaccines: Updated Recommendations of the Advisory Committee on Immunization Practices — United States, 2019",
        "2020",
        "https://www.cdc.gov/mmwr/volumes/69/wr/mm6903a5.htm",
        status = "Havers FP et al.",
    ),
    Source(
        "tr-wb-cdc-tetanus-care", Authority.CDC, "Clinical guidance (web page)",
        "Clinical Care of Tetanus", "2026",
        "https://www.cdc.gov/tetanus/hcp/clinical-care/index.html",
        status = "Published 15 May 2024; updated and reviewed 8 September 2026",
    ),
)

private const val WB_NGRP = "tr-wb-ncdc-ngrp-2019"
private const val WB_MO = "tr-wb-ncdc-mo-module"
private const val WB_WHO_RAB = "tr-wb-who-rabies-2018"
private const val WB_TRS = "tr-wb-who-trs1012-2018"
private const val WB_CDC_RAB = "tr-wb-cdc-rabies-pep"
private const val WB_IDSA = "tr-wb-idsa-ssti-2014"
private const val WB_RMAB = "tr-wb-rmab-phase4-2025"
private const val WB_RMAB_SII = "tr-wb-sii-rabishield"
private const val WB_TWINRAB = "tr-wb-twinrab-phase3-2021"
private const val WB_TWINRAB_INUSE = "tr-wb-twinrab-inuse-2025"

private const val WB_WHO_TT = "tr-wb-who-tetanus-2017"
private const val WB_WHO_TT_TX = "tr-wb-who-tetanus-tx-2010"
private const val WB_ACIP18 = "tr-wb-acip-tdap-2018"
private const val WB_ACIP19 = "tr-wb-acip-tdap-2019"
private const val WB_CDC_TT_CARE = "tr-wb-cdc-tetanus-care"

/** Defined in MaternalCollapse.kt — CDC Clinical Guidance for Wound Management to Prevent Tetanus. */
private const val WB_CDC_TT_WOUND = "ob-mat-cdc-tetanus-wound"

/** Defined in Sources.kt — MoHFW UIP Td vaccine operational guidelines (2018). */
private const val WB_MOH_TD = "mohfw-td"

/** Defined in Trauma.kt — MoHFW Standard Treatment Guidelines for Management of Trauma (2025). */
private const val WB_MOH_STG = "tr-mohfw-stg-trauma-2025"

// ================================================================================================ Animal bites / rabies
private val animalBiteRabies = Protocol(
    id = "animal-bite-rabies",
    category = ProtocolCategory.Trauma,
    title = "Animal bites: rabies post-exposure prophylaxis",
    summary = "Exposure category, wound washing, closure, antibiotics, rabies vaccine schedules, RIG and monoclonal " +
        "antibodies, the 10-day observation rule and re-exposure — attributed to NCDC 2019 (NRCP), WHO 2018, IDSA 2014 and CDC.",
    keywords = listOf(
        "dog bite", "monkey bite", "cat bite", "animal bite", "rabies", "ARV", "anti-rabies vaccine", "rabies vaccine",
        "RIG", "ERIG", "HRIG", "ARS", "anti-rabies serum", "rabies immunoglobulin", "rabies monoclonal antibody", "RmAb",
        "Rabishield", "Twinrab", "docaravimab", "miromavimab", "PEP", "post-exposure prophylaxis", "IDRV",
        "intradermal rabies vaccine", "Thai Red Cross", "TRC", "Essen", "category III bite", "NRCP", "anti-rabies clinic",
        "Rabipur", "Verorab", "Abhayrab", "Indirab", "Vaxirab N", "Rabivax-S", "Equirab", "stray dog",
        "kutte ka kaatna", "kutta kaat gaya", "kutte ne vaddeya", "bandar ka kaatna", "billi ka kaatna", "pagal kutta",
        "halkaya kutta", "rabies ka injection", "14 injection",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Decide to treat: classify the exposure",
            timing = "At first contact — start PEP immediately, however late the patient presents (NCDC 2019)",
            points = listOf(
                "NCDC 2019: in India every animal bite is treated as potentially rabid; consider PEP for bites by all warm-blooded animals and start it immediately.",
                "NCDC 2019: wild-animal bites and all bites in forest areas are category III. Domestic rodents, hares and rabbits do not ordinarily need PEP; forest rodent bites do.",
                "NCDC 2019 (FAQ 13): if the history is unreliable — a mole, mongoose or other look-alike may have been taken for a rat — always offer PEP. " +
                    "NCDC MO module: bat or rodent bites in unusual circumstances may be vaccinated after consulting a rabies expert.",
                "Monkey bites — NCDC MO module: monkeys occasionally transmit rabies in India. NCDC 2019: all wild-animal exposures are category III. " +
                    "WHO TRS 1012 counts monkeys among wild animals. The 10-day observation does not apply (NCDC 2019).",
                "NCDC 2019: give PEP whatever the biting animal's vaccination status, and even if the bite was provoked.",
                "NCDC 2019: patients presenting months or years after exposure are evaluated and treated as if it were recent. WHO 2018: always vaccinate when a category III " +
                    "exposure is recognised, even months or years later; rabies after 12 months is rare, so only if vaccine supply is limited may it be reserved for exposures within 12 months.",
                "WHO 2018: category I — no PEP; category II — immediate vaccination; category III — immediate vaccination plus RIG unless previously immunised.",
            ),
            checklistTitle = "Exposure category and action (NCDC 2019 Table 1)",
            checklist = listOf(
                "Category I — touching or feeding animals, licks on intact skin, intact skin in contact with secretions: wash exposed skin; no PEP if the history is reliable",
                "Category II — nibbling of uncovered skin, minor scratches or abrasions without bleeding: wound washing + rabies vaccine",
                "Category III — single or multiple transdermal bites or scratches, licks on broken skin, saliva on mucous membranes: wound washing + RIG + rabies vaccine",
                "Wild-animal bite or any bite in a forest area: manage as category III",
                "Immunocompromised with a category II exposure: RIG as well (NCDC 2019)",
            ),
            citations = listOf(
                cite(WB_NGRP, "§2.1–2.5, Table 1 (pp. 3–5); §2.7.1 (p. 8); FAQ 13 (p. 35)"),
                cite(WB_MO, "§1.5 (p. 17); §1.7 Table 1 and note (p. 18)"),
                cite(WB_TRS, "§8.3.2 (p. 65)"),
                cite(WB_WHO_RAB, "Post-exposure prophylaxis; Table 1 (pp. 214–215)"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Wound washing and first aid",
            timing = "Immediately — and still if the patient reports late (NCDC 2019)",
            points = listOf(
                "NCDC 2019: wash every wound with soap under running tap water for 15 minutes (the NCDC PEP protocol chart says at least 15 minutes), as soon as possible. WHO TRS 1012: approximately 15 minutes.",
                "NCDC 2019: after washing and drying, apply povidone-iodine or another virucidal antiseptic such as alcohol.",
                "NCDC 2019: if soap is unavailable, wash thoroughly with water; rinse exposed eyes and mucosa with water.",
                "NCDC 2019: if irritants were applied, wash gently with soap or detergent, then flush with copious water.",
                "NCDC 2019: a bleeding wound at any site indicates severe exposure and should be infiltrated with RIG.",
            ),
            checklistTitle = "Don'ts (NCDC 2019; NCDC MO module)",
            checklist = listOf(
                "Do not touch the wound with bare hands",
                "No irritants — soil, chillies, oil, lime, turmeric, herbs, chalk, betel leaves",
                "Do not cauterise the wound (MO module)",
                "Avoid bandaging or covering the wound where practicable; open dressing (MO module)",
                "No primary suturing without RIG infiltration first",
            ),
            citations = listOf(
                cite(WB_NGRP, "Table 1 (p. 5); §2.6.1.1, Table 2 (pp. 6–7); PEP protocol chart (p. 38)"),
                cite(WB_MO, "Session 2 §2.1.2 Wound management; Dos and don'ts (pp. 24–25)"),
                cite(WB_TRS, "§8.3 (p. 60)"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Wound closure, antibiotics and tetanus",
            points = listOf(
                "NCDC 2019: severe bites — daily dressing, then secondary suturing. If suturing is unavoidable, infiltrate RIG first and delay several hours; minimal sutures.",
                "WHO 2018: loose sutures if unavoidable. IDSA 2014: no primary closure except facial wounds (copious irrigation, cautious debridement, preemptive antibiotics).",
                "NCDC 2019: a suitable antibiotic course may be prescribed to prevent wound sepsis. WHO 2018: antibiotics, analgesics and tetanus vaccination as the wound requires.",
                "IDSA 2014: preemptive antibiotics for 3–5 days after dog or cat bites in the patients listed below.",
                "IDSA 2014: infected bite — an agent active against aerobes and anaerobes, such as amoxicillin–clavulanate 875/125 mg orally twice daily (misses MRSA).",
                "IDSA 2014 alternatives: cefuroxime or another second- or third-generation cephalosporin, plus clindamycin or metronidazole if anaerobic cover is needed; " +
                    "or a carbapenem, moxifloxacin or doxycycline. With co-trimoxazole or levofloxacin, add clindamycin or metronidazole.",
                "IDSA 2014: avoid macrolides unless no alternative is available (variable activity against Pasteurella multocida and fusobacteria). " +
                    "Pregnancy: tetracyclines and fluoroquinolones are relative contraindications; co-trimoxazole may be used except in the third trimester.",
                "NCDC 2019: tetanus prophylaxis as per national guidelines; NCDC MO module: TT or Td 0.5 mL IM. CDC: bites are dirty wounds. See the Tetanus prophylaxis protocol.",
            ),
            checklistTitle = "IDSA 2014: preemptive antibiotics for 3–5 days if",
            checklist = listOf(
                "Immunocompromised",
                "Asplenia",
                "Advanced liver disease",
                "Pre-existing or resulting oedema of the affected area",
                "Moderate to severe injury, especially to the hand or face",
                "Injury that may have penetrated the periosteum or joint capsule",
            ),
            citations = listOf(
                cite(WB_NGRP, "§2.6.1.1–2.6.1.3 (pp. 7–8)"),
                cite(WB_WHO_RAB, "Post-exposure prophylaxis (p. 214); Administration of RIG (p. 216)"),
                cite(WB_IDSA, "Q XI (preemptive therapy); Q XII (recommendation and evidence summary); Q XIV; Table 5"),
                cite(WB_MO, "§2.3 Mode of administration of RIG (p. 31)"),
                cite(WB_CDC_TT_WOUND, "Wound assessment and care — dirty or major wounds"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Rabies vaccine: schedules",
            timing = "Day 0 = first dose; doses of days 0, 3 and 7 completed within 10 days at most (NCDC 2019)",
            points = listOf(
                "NCDC 2019: categories II and III need the same number of injections and dose per injection at every age and body weight.",
                "NCDC 2019, updated Thai Red Cross (ID): 0.1 mL at each of 2 sites, one over each deltoid, on days 0, 3, 7 and 28.",
                "NCDC 2019, Essen (IM): one full vial on days 0, 3, 7, 14 and 28 — deltoid; anterolateral thigh in infants and young children; never gluteal.",
                "NCDC MO module: these are the only DCGI-approved ID and IM regimens in India. NCDC 2019: give ID only with vaccines DCGI-approved for ID use.",
                "WHO 2018 also recommends 1-week ID (2 sites, days 0, 3, 7); IM days 0, 3, 7 and 14–28; or IM 2 sites day 0, then days 7 and 21.",
                "NCDC 2019: a delay of 1–2 days needs no restart. Avoid switching route or brand; if unavoidable, continue on the new schedule without restarting.",
            ),
            checklistTitle = "Intradermal route (NCDC 2019 §4.1)",
            checklist = listOf(
                "Not preferred if immunocompromised or on chloroquine, hydroxychloroquine, long-term steroids or other immunosuppressants — use IM",
                "Use only within 6 h of reconstitution, vial kept at 2–8 °C",
                "1 mL insulin syringe with a fixed 28G needle; draw 0.2 mL and expel air",
                "Bevel up, almost parallel to the skin, over the deltoid an inch above its insertion; inject 0.1 mL",
                "A 3–4 mm peau d'orange bleb confirms intradermal placement",
                "No bleb (too deep): withdraw and repeat at an adjacent site",
                "If the deltoid cannot be used: suprascapular area or anterolateral thigh",
            ),
            citations = listOf(
                cite(WB_NGRP, "§2.5 (p. 4); §3.1 (p. 11); §4.1–4.2 (pp. 14–17); §8 (p. 22); §10 (p. 24)"),
                cite(WB_MO, "§2.2.3 Route of administration (pp. 26, 28)"),
                cite(WB_WHO_RAB, "Administration of rabies vaccines (p. 213); Table 1 (p. 215)"),
                cite(WB_TRS, "§8.3.4, Table 9 (pp. 66–67)"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Rabies immunoglobulin: indications and dose",
            timing = "Once, at or as soon as possible after the first vaccine dose (day 0); not beyond day 7 counted from the first vaccine dose, not from the bite (NCDC 2019, WHO 2018)",
            points = listOf(
                "NCDC 2019: RIG for every category III exposure, and for category II and III in immunocompromised patients — not after complete PrEP or PEP.",
                "NCDC MO module: RIG can be given at any time if no vaccine has been received — a patient presenting weeks or months late with no vaccine dose still gets RIG at the bite site.",
                "CDC: if HRIG was not given on day 0 when vaccination began, it can be given up to and including day 7 of the PEP series.",
                "NCDC 2019 and WHO 2018: maximum dose ERIG 40 IU/kg, HRIG 20 IU/kg, given once.",
                "WHO 2018: never withhold vaccine because RIG is unavailable.",
                "WHO 2018: if RIG is scarce, prioritise multiple or deep bites, bites to the head, neck or hands, severe immunodeficiency, and proven or probable rabid animals.",
                "ERIG skin test — NCDC 2019: not required, though some manufacturers still recommend it. WHO 2018: should not be done. Be ready to treat anaphylaxis.",
                "NCDC 2019: bring RIG to room temperature (25–30 °C); observe 15–20 minutes afterwards; no admission needed. Never give RIG intravenously.",
            ),
            doses = listOf(
                StepDose(
                    "Equine rabies immunoglobulin (ERIG) — NCDC 2019, WHO 2018",
                    WeightDose(40.0, 40.0, unit = "IU", per = "once (maximum dose), infiltrated into and around all wounds", ceilingPerKg = 40.0),
                    note = "Strengths differ (NCDC 2019 Annexure II lists 300 IU/mL and 1000 IU/5 mL products) — check the vial. Never IV.",
                ),
                StepDose(
                    "Human rabies immunoglobulin (HRIG) — NCDC 2019, WHO 2018",
                    WeightDose(20.0, 20.0, unit = "IU", per = "once (maximum dose), infiltrated into and around all wounds", ceilingPerKg = 20.0),
                    note = "NCDC 2019 Annexure III lists 150 IU/mL products. Never IV.",
                ),
            ),
            citations = listOf(
                cite(WB_NGRP, "§2.7–2.10 (pp. 8–10); PEP protocol chart (p. 38); Annexures II–III"),
                cite(WB_MO, "§2.3 Mode of administration of the full dose of RIG (p. 30); Special situations (p. 36)"),
                cite(WB_WHO_RAB, "Post-exposure prophylaxis (p. 214); Administration of RIG (pp. 215–216)"),
                cite(WB_TRS, "§8.4 (pp. 68–69)"),
                cite(WB_CDC_RAB, "Common errors when administering rabies biologics — HRIG was not administered"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Rabies immunoglobulin: infiltration technique",
            points = listOf(
                "NCDC 2019: infiltrate the whole dose, or as much as anatomically possible, into and as close as possible to every wound, avoiding compartment syndrome.",
                "NCDC MO module: infiltrate the edges and base until RIG oozes out; use separate needles for different wounds; miss no wound.",
                "WHO 2018 and NCDC MO module: for large or multiple wounds, dilute with normal saline to cover all wounds — without exceeding the maximum dose.",
                "NCDC 2019: fingertips, toes, ear lobes, nose, around the eye and genitalia can be infiltrated if excessive pressure is avoided.",
                "NCDC 2019 and NCDC MO module: an infected bite wound is not a contraindication to RIG.",
                "Remainder — WHO 2018: no longer injected IM away from the wound. NCDC 2019, WHO TRS 1012: IM near the presumed site only if unnoticed wounds are likely.",
                "NCDC 2019: never in the same syringe or at the same site as the vaccine. Mucosal exposure without a wound: rinsing with RIG can be considered.",
            ),
            citations = listOf(
                cite(WB_NGRP, "§2.6.1.2 (p. 8); §2.9 (pp. 9–10)"),
                cite(WB_MO, "§2.3.1 Indications for RIG (p. 30); §2.3 Mode of administration of the full dose of RIG (pp. 30–31)"),
                cite(WB_WHO_RAB, "Administration of RIG (p. 216)"),
                cite(WB_TRS, "§8.4 (p. 69)"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Rabies monoclonal antibodies (licensed in India)",
            points = listOf(
                "WHO 2018: if available, monoclonal antibody products instead of RIG are encouraged; a registry of their clinical use should be kept.",
                "WHO TRS 1012 (restated in NCDC 2019 §9 and the MO module): WHO recommends mAb cocktails of at least two antibodies as RIG alternatives. " +
                    "The Serum Institute product (Rabishield) is a single mAb (TRS 1012); Twinrab is a two-antibody cocktail (Kansagra 2021).",
                "NCDC 2019 and MO module: their role replacing RIG in category III needs multicentre Indian study before inclusion in the national guidelines.",
                "Lancet 2025 (manufacturer-funded phase 4, 15 Indian hospitals): Rabishield 3.33 IU/kg infiltrated into and around the wounds on day 0 only, with ID or IM vaccine.",
                "Kansagra 2021 (manufacturer phase 3): Twinrab (docaravimab + miromavimab) 40 IU/kg was non-inferior to HRIG 20 IU/kg, with Essen IM vaccine.",
                "Trial populations — Lancet 2025: patients aged 2 years or older, enrolled within 72 h of exposure (24 h for face, neck, hand or finger exposures). " +
                    "Kansagra 2021: healthy adults, Essen IM vaccine only.",
                "Rabishield remainder — manufacturer: any remaining volume IM at a site distant from the vaccine. WHO 2018 no longer recommends IM injection of the remainder; " +
                    "the Lancet 2025 trial infiltrated the wounds only.",
                "Doses are product-specific (3.33 versus 40 IU/kg) — follow the package insert of the product in hand.",
            ),
            doses = listOf(
                StepDose(
                    "Rabies human monoclonal antibody, Serum Institute (Rabishield) — Lancet 2025; manufacturer",
                    WeightDose(3.33, 3.33, unit = "IU", per = "once on day 0, infiltrated into and around the wounds"),
                    note = "Licensed in India in 2016 (Lancet 2025) or 2017 (WHO 2018, TRS 1012); not in the NCDC 2019 national guidelines. " +
                        "Manufacturer: vials of 100 IU/2.5 mL (40 IU/mL) and 250 IU/2.5 mL (100 IU/mL) — check the vial.",
                ),
                StepDose(
                    "Docaravimab + miromavimab (Twinrab) — Kansagra 2021; Kalita 2025",
                    WeightDose(40.0, 40.0, unit = "IU", per = "once on day 0, at the wound site"),
                    note = "Strengths 300 IU/mL and 600 IU/mL (Kalita 2025); not in the NCDC 2019 national guidelines",
                ),
            ),
            citations = listOf(
                cite(WB_WHO_RAB, "Rabies immunoglobulins (p. 212); Administration of RIG (p. 216)"),
                cite(WB_TRS, "§7.8 (p. 53); §8.4 (p. 69); Annex 5 (p. 152)"),
                cite(WB_NGRP, "§9 (p. 23)"),
                cite(WB_MO, "§2.11 (p. 35)"),
                cite(WB_RMAB, "Abstract — Background, Methods"),
                cite(WB_RMAB_SII, "Route of administration; Dosage; Presentation"),
                cite(WB_TWINRAB, "Abstract — Conclusions; Methods (dose)"),
                cite(WB_TWINRAB_INUSE, "Discussion (approved dosage); product strengths"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "The biting animal: 10-day observation",
            points = listOf(
                "NCDC 2019: start PEP immediately and observe the biting dog or cat for 10 days where possible — never observe without starting PEP.",
                "NCDC 2019: the 10-day rule applies to dogs and cats only, not to other mammals; this includes monkeys.",
                "NCDC 2019: dog or cat healthy at day 10 — Essen IM becomes PrEP: skip day 14, give day 28. ID route: complete the course regardless.",
                "WHO 2018: PEP can be stopped if a dog, cat or ferret stays healthy for 10 days after the bite, or tests free of rabies.",
                "NCDC 2019 (IDSP case definition): suspect rabid animal — unprovoked aggression, hypersalivation, paralysis, lethargy, abnormal vocalisation, or daytime activity of a nocturnal species.",
            ),
            citations = listOf(
                cite(WB_NGRP, "§2.2 (p. 3); FAQ 9 (p. 34); Annexure VII"),
                cite(WB_MO, "§2.6.1 (p. 33)"),
                cite(WB_WHO_RAB, "Table 1 notes — discontinuation of PEP (p. 215)"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "Previously vaccinated, immunocompromised and pregnant patients",
            points = listOf(
                "NCDC 2019: documented complete PrEP or PEP — wound care, no RIG; ID one site, or IM one vial, on days 0 and 3. " +
                    "NCDC 2019 PEP protocol chart: if direct nerve exposure is suspected, the treating physician may consider RIG infiltration even in a previously vaccinated patient.",
                "NCDC 2019 and WHO 2018: re-exposure within 3 months of a completed course — wound washing only. NCDC 2019: nerve-tissue vaccine or undocumented course — full PEP.",
                "WHO 2018: RIG not indicated after documented PrEP or at least 2 PEP doses; another option is ID at 4 sites on day 0 only.",
                "Immunocompromised — NCDC 2019: RIG for category II and III, full IM course, antibody titre 14 days after it if available. WHO 2018: RIG even if previously immunised.",
                "NCDC 2019: no contraindication to PEP; pregnancy, lactation, infancy, old age and concurrent illness are not contraindications. WHO 2018: never withhold PEP in pregnancy.",
                "NCDC 2019: observe 15–20 minutes after vaccination. A previous reaction to a vaccine component contraindicates that same vaccine — " +
                    "change to a different vaccine product and continue PEP (WHO TRS 1012; NCDC MO module), with adrenaline ready (MO module).",
            ),
            citations = listOf(
                cite(WB_NGRP, "§2.5 (p. 4); §5 (p. 19); §6 (p. 20); PEP protocol chart (p. 38)"),
                cite(WB_MO, "§2.4 (p. 32); §2.9 (p. 34); Special situations (p. 36)"),
                cite(WB_WHO_RAB, "Post-exposure prophylaxis (p. 214); Table 1 (p. 215); Vaccination of special groups (p. 218)"),
                cite(WB_TRS, "§8.3.6 (p. 68); §8.5 (pp. 69–70)"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Schedules differ: India approves only Essen IM (days 0, 3, 7, 14, 28) and updated Thai Red Cross ID (days 0, 3, 7, 28); WHO 2018 prefers 1-week ID (days 0, 3, 7) and also lists 2-week IM and Zagreb; CDC uses 4 IM doses (days 0, 3, 7, 14), adding day 28 if immunocompromised.",
            listOf(
                cite(WB_MO, "§2.2.3 (pp. 26, 28)"),
                cite(WB_NGRP, "§10 (p. 24)"),
                cite(WB_WHO_RAB, "Table 1 (p. 215)"),
                cite(WB_CDC_RAB, "PEP for individuals without previous rabies vaccination — Vaccine; Patients who are immunocompromised"),
            ),
        ),
        ProtocolNote(
            "Remaining RIG volume: WHO 2018 no longer recommends IM injection away from the wound (spare RIG may be fractionated for other patients); NCDC 2019 and WHO TRS 1012 allow IM near the presumed site if unnoticed wounds are likely; CDC, and the Rabishield manufacturer for its product, give any remainder IM at a site distant from the vaccine.",
            listOf(
                cite(WB_WHO_RAB, "Administration of RIG (p. 216)"),
                cite(WB_NGRP, "§2.9 (p. 9)"),
                cite(WB_TRS, "§8.4 (p. 69)"),
                cite(WB_CDC_RAB, "PEP for individuals without previous rabies vaccination — HRIG"),
                cite(WB_RMAB_SII, "Route of administration"),
            ),
        ),
        ProtocolNote(
            "Monoclonal antibodies: WHO 2018 encourages them instead of RIG where available, but India's national guidelines (2019, restated in the NCDC MO module) have not adopted them pending Indian multicentre data. The dose evidence comes from manufacturer-sponsored trials.",
            listOf(
                cite(WB_WHO_RAB, "Administration of RIG (p. 216)"),
                cite(WB_NGRP, "§9 (p. 23)"),
                cite(WB_MO, "§2.11 (p. 35)"),
                cite(WB_RMAB, "Funding"),
                cite(WB_TWINRAB, "Author affiliations"),
            ),
        ),
        ProtocolNote(
            "ERIG skin test: NCDC 2019 says it is not required but notes some manufacturers still recommend it; WHO 2018 says it should not be done because it does not predict reactions.",
            listOf(cite(WB_NGRP, "§2.7.2 (pp. 8–9)"), cite(WB_WHO_RAB, "Administration of RIG (p. 216)")),
        ),
        ProtocolNote(
            "Chloroquine or hydroxychloroquine: NCDC 2019 advises the IM route; WHO 2018 says either ID or IM can be used.",
            listOf(cite(WB_NGRP, "§2.5 (p. 4); §4.1 (p. 14)"), cite(WB_WHO_RAB, "Contraindications and precautions (p. 217)")),
        ),
        ProtocolNote(
            "Bats: NCDC 2019 states bat rabies is not conclusively proven in India and bat exposure does not currently warrant PEP; the NCDC MO module adds that bat or rodent bites in unusual circumstances may be vaccinated after consulting a rabies expert; WHO 2018 classes direct contact with bats as category III.",
            listOf(
                cite(WB_NGRP, "§2.1 (p. 3)"),
                cite(WB_MO, "§1.7 Table 1 note (p. 18)"),
                cite(WB_WHO_RAB, "Exposure to infection (p. 203)"),
            ),
        ),
        ProtocolNote(
            "Previously immunised: WHO 2018 counts documented PrEP or at least 2 PEP doses; NCDC 2019 requires documented complete PrEP or PEP, otherwise full PEP.",
            listOf(cite(WB_WHO_RAB, "Post-exposure prophylaxis (p. 214)"), cite(WB_NGRP, "§5 (p. 19)")),
        ),
        ProtocolNote(
            "Antibiotics: NCDC 2019 says a suitable course may be prescribed; IDSA 2014 limits preemptive antibiotics to higher-risk bites and hosts. Tetanus after bites: IDSA 2014 recommends toxoid if none within 10 years (Tdap preferred if never given), and its supporting text adds a booster for dirty wounds if >5 years since the last dose; CDC classes bites as dirty wounds — booster if 5 or more years.",
            listOf(
                cite(WB_NGRP, "§2.6.1.3 (p. 8)"),
                cite(WB_IDSA, "Q XI; Q XIII (recommendation and evidence summary)"),
                cite(WB_CDC_TT_WOUND, "Wound assessment; tetanus vaccination status evaluation"),
            ),
        ),
    ),
    citations = listOf(
        cite(WB_NGRP), cite(WB_MO), cite(WB_WHO_RAB), cite(WB_TRS), cite(WB_IDSA), cite(WB_CDC_RAB),
        cite(WB_RMAB), cite(WB_RMAB_SII), cite(WB_TWINRAB), cite(WB_TWINRAB_INUSE),
    ),
)

// ================================================================================================ Tetanus prophylaxis
private val tetanusProphylaxis = Protocol(
    id = "tetanus-prophylaxis",
    category = ProtocolCategory.Trauma,
    title = "Tetanus prophylaxis",
    summary = "Clean versus tetanus-prone wounds, Td (TT) and tetanus immunoglobulin by vaccination history, TIG doses, " +
        "pregnancy and wound care — attributed to MoHFW (UIP Td, trauma STG 2025), NCDC, WHO 2017 and CDC/ACIP.",
    keywords = listOf(
        "tetanus", "TT injection", "TT", "tetanus toxoid", "Td", "Td vaccine", "Tdap", "DTaP", "TIG", "HTIG",
        "tetanus immunoglobulin", "tetanus immune globulin", "ATS", "anti-tetanus serum", "rusty nail", "nail prick",
        "wound", "tetanus-prone wound", "dirty wound", "puncture wound", "crush injury", "lockjaw", "trismus",
        "TT ka injection", "tetanus ka teeka", "dhanurvaat", "dhanustambh", "jung lagi keel", "zakhm", "ghaav", "chot",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Assess the wound and the vaccination history",
            timing = "At wound care",
            points = listOf(
                "WHO 2017: the prophylaxis needed depends on the nature of the wound and the patient's tetanus immunisation history.",
                "CDC: clean and minor wounds do not pose a major risk; dirty or major wounds pose an increased risk.",
                "ACIP 2018: try to establish whether the 3-dose primary series is complete; treat an unknown or uncertain history as no previous doses.",
                "ACIP 2018: HIV infection or severe immunodeficiency with a contaminated wound — TIG whatever the vaccination history.",
            ),
            checklistTitle = "Dirty or major (tetanus-prone) wounds — CDC; ACIP 2018",
            checklist = listOf(
                "Penetrating or puncture wounds",
                "Wounds containing dirt, soil, faeces or saliva — including animal and human bites",
                "Devitalised tissue: burns, compound fractures, crush injuries, frostbite",
                "Necrotic or gangrenous wounds",
                "Avulsions and missile wounds (ACIP 2018)",
            ),
            citations = listOf(
                cite(WB_WHO_TT, "Wound management for prevention of tetanus (p. 59)"),
                cite(WB_CDC_TT_WOUND, "Wound assessment and care — assessment"),
                cite(WB_ACIP18, "Tetanus prophylaxis for wound management; Table 6 footnotes"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Wound care",
            timing = "Promptly (WHO 2017)",
            points = listOf(
                "WHO 2017: clean and debride all wounds promptly and appropriately.",
                "CDC: clean all wounds, remove dirt and foreign material, and remove or debride necrotic tissue.",
                "CDC: topical or systemic antibiotics are not recommended during wound care to protect against tetanus.",
                "MoHFW STG 2025: most green-triaged trauma patients need minor wound care — ensure analgesia, TT and wound care.",
                "Animal bites: wound washing, RIG and closure are covered in the Animal bites: rabies post-exposure prophylaxis protocol.",
            ),
            citations = listOf(
                cite(WB_WHO_TT, "Wound management for prevention of tetanus (p. 59)"),
                cite(WB_CDC_TT_WOUND, "Wound assessment and care — care; antibiotic prophylaxis"),
                cite(WB_MOH_STG, "Ch. 1 Management of green triaged patients (p. 12)"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Vaccine and TIG by vaccination history",
            points = listOf(
                "CDC: unknown history, never vaccinated or incomplete primary series — vaccinate for any wound; add TIG for dirty or major wounds.",
                "CDC: primary series complete — vaccinate if the last dose was 10 or more years ago (clean, minor) or 5 or more years ago (dirty, major).",
                "CDC: primary series complete and last dose under 5 years ago — no vaccine. TIG is never indicated for clean, minor wounds.",
                "WHO 2017: a dose, preferably Td, if the injury is severe or the history unreliable; TIG, preferably human, for dirty wounds if incompletely immunised.",
                "ACIP 2018: after an Arthus reaction to a tetanus or diphtheria toxoid vaccine, no tetanus toxoid vaccine within 10 years — even for a contaminated wound.",
                "MoHFW STG 2025 and NCDC MO module: TT (or Td) 0.5 mL IM if indicated — no national vaccination-history table was found.",
                "Infants — WHO 2017: the primary series starts from 6 weeks of age; ACIP 2018: the first DTaP dose can be given as early as 6 weeks; MoHFW UIP: Pentavalent-1 at 6 weeks. " +
                    "ACIP 2018 Table 6 sets no age limit for TIG (unknown or <3 doses + all other wounds: TIG YES).",
            ),
            checklistTitle = "ACIP 2018 Table 6 — routine wound management",
            checklist = listOf(
                "Unknown or <3 doses + clean minor wound: vaccine YES, TIG NO",
                "Unknown or <3 doses + all other wounds: vaccine YES, TIG YES",
                "≥3 doses + clean minor wound: vaccine only if >10 years since the last dose; TIG NO",
                "≥3 doses + all other wounds: vaccine if ≥5 years since the last dose; TIG NO (YES if HIV or severe immunodeficiency)",
            ),
            citations = listOf(
                cite(WB_CDC_TT_WOUND, "Tetanus vaccination status evaluation; TIG assessment"),
                cite(WB_ACIP18, "Table 6; Tetanus prophylaxis for wound management; Arthus reactions; Persons aged 2 months–6 years — Guidance for use"),
                cite(WB_WHO_TT, "Primary vaccination and booster doses in children (p. 71); Immunization in the case of injury (p. 74)"),
                cite(WB_MOH_STG, "Ch. 1 Yellow area (p. 12)"),
                cite(WB_MO, "§2.3 Mode of administration of RIG (p. 31)"),
                cite(WB_MOH_TD, "UIP schedule after introduction of Td"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Which vaccine: Td in India",
            points = listOf(
                "MoHFW UIP: Td has replaced TT for all age groups, including pregnant women — 0.5 mL IM in the upper arm.",
                "MoHFW UIP: earlier TT doses remain valid; continue with Td without restarting the series. Existing TT stock is used before Td.",
                "WHO 2017: move from single-antigen TT to diphtheria-containing combinations (DT or Td). MoHFW STG 2025 still names tetanus toxoid 0.5 mL IM.",
                "ACIP 2018 and 2019: DTaP under 7 years; at 11 years or older Tdap preferred if never given, otherwise Td or Tdap; Tdap in pregnancy.",
            ),
            citations = listOf(
                cite(WB_MOH_TD, "Replacement of TT with Td; dose, route, site; cold chain and stock management; points to remember"),
                cite(WB_WHO_TT, "Choice of combination vaccines (p. 74)"),
                cite(WB_MOH_STG, "Ch. 1 Yellow area (p. 12)"),
                cite(WB_ACIP18, "Table 6 footnote †; Tetanus prophylaxis for wound management"),
                cite(WB_ACIP19, "Tetanus prophylaxis for wound management recommendations"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Tetanus immunoglobulin (TIG) for prophylaxis",
            points = listOf(
                "CDC: when indicated, tetanus immune globulin 250 IU intramuscularly.",
                "WHO 2017: TIG, preferably of human origin, for dirty wounds in incompletely vaccinated people or those with an uncertain vaccination history.",
                "ACIP 2018: base the TIG decision on the primary vaccination history, not on the time since the last dose.",
                "ACIP 2018: when both are indicated, give TIG and vaccine with separate syringes at different anatomical sites.",
                "WHO 2017: TIG is essential for prophylaxis and treatment and should be readily available in all countries.",
            ),
            citations = listOf(
                cite(WB_CDC_TT_WOUND, "TIG assessment"),
                cite(WB_WHO_TT, "Wound management for prevention of tetanus (p. 59); Immunization in the case of injury (p. 74)"),
                cite(WB_ACIP18, "Tetanus prophylaxis for wound management"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Complete the primary series",
            points = listOf(
                "WHO 2017: complete the schedule as soon as possible for anyone who has not received all doses.",
                "WHO 2017 (catch-up from 1 year of age, adolescents and adults): first and second doses at least 4 weeks apart, second and third at least 6 months apart.",
                "WHO 2017 (infants): 3 primary doses from 6 weeks of age, at least 4 weeks apart, the third by 6 months of age if possible. MoHFW UIP: Pentavalent at 6, 10 and 14 weeks.",
                "WHO 2017: starting in adolescence or adulthood, 5 appropriately spaced doses give lifelong protection; from infancy, 6 doses (3 primary plus 3 boosters).",
                "ACIP 2018: adults never vaccinated — Tdap, then Td at least 4 weeks later and again 6–12 months later (ACIP 2019: Td or Tdap).",
                "MoHFW UIP: DPT boosters at 16–24 months and 5–6 years, then Td at 10 and 16 years.",
            ),
            citations = listOf(
                cite(WB_WHO_TT, "WHO position (p. 70); Primary vaccination and booster doses in children (p. 71); Primary and catch-up schedule (pp. 72–73); Immunization in the case of injury (p. 74)"),
                cite(WB_ACIP18, "Persons aged >18 years"),
                cite(WB_ACIP19, "Catch-up immunization recommendations"),
                cite(WB_MOH_TD, "UIP schedule after introduction of Td"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Pregnancy",
            points = listOf(
                "MoHFW UIP: Td-1 early in pregnancy, Td-2 four weeks later; one Td booster if pregnant within 3 years of a pregnancy with 2 Td doses.",
                "WHO 2017: review every pregnant woman's tetanus history at the first antenatal contact and give any missing doses.",
                "ACIP 2018 and 2019: if a tetanus toxoid–containing vaccine is indicated for a wound in pregnancy, use Tdap.",
                "MoHFW STG 2025 (trauma in pregnancy): 'administer tetanus immunoglobulin' is listed under definitive care, without criteria.",
            ),
            citations = listOf(
                cite(WB_MOH_TD, "UIP schedule after introduction of Td — pregnant women"),
                cite(WB_WHO_TT, "WHO position (p. 71); Vaccination of pregnant women (p. 73)"),
                cite(WB_ACIP18, "Tetanus prophylaxis for wound management"),
                cite(WB_ACIP19, "Tetanus prophylaxis for wound management recommendations"),
                cite(WB_MOH_STG, "Ch. 13 Trauma in pregnancy — Definitive care (p. 59)"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Suspected clinical tetanus",
            timing = "TIG as a single dose as soon as possible (WHO 2017)",
            points = listOf(
                "WHO 2017: human TIG as a single IM dose as soon as possible; it removes unbound toxin but is unlikely to change existing disease.",
                "CDC: TIG 500 IU IM as a single dose for treatment. WHO 2010: human TIG 500 units IM or IV, depending on the preparation.",
                "WHO 2010: also give tetanus toxoid–containing vaccine 0.5 mL IM at a separate site — the disease does not induce immunity; complete the series.",
                "WHO 2010: metronidazole preferred, 500 mg 6-hourly IV or orally (no paediatric or neonatal dose given); penicillin G is an alternative.",
                "WHO 2017: if TIG is unavailable, IVIG may be used; equine antitoxin only as a single large dose after hypersensitivity testing.",
                "WHO 2010: clean and debride wounds; quiet, shaded area; benzodiazepines for spasms; mechanical ventilation if spasms threaten breathing.",
                "Neonatal tetanus — WHO 2017 definition: normal suck and cry in the first 2 days of life, lost between days 3 and 28, with rigidity or spasms. " +
                    "WHO 2017: a dark, quiet environment, and nasogastric feeding for newborn infants.",
            ),
            doses = listOf(
                StepDose(
                    "Penicillin G — alternative to metronidazole (WHO 2010)",
                    WeightDose(100000.0, 200000.0, unit = "IU", per = "per day IV, in 2–4 divided doses"),
                ),
                StepDose(
                    "Immune globulin IV (IGIV) if TIG is unavailable — CDC",
                    WeightDose(200.0, 400.0, unit = "mg", per = "IV"),
                    note = "CDC: not FDA-approved for this use",
                ),
            ),
            citations = listOf(
                cite(WB_WHO_TT, "Diagnosis; Management of tetanus cases (pp. 58–59)"),
                cite(WB_CDC_TT_CARE, "Tetanus immune globulin; IGIV; vaccination"),
                cite(WB_WHO_TT_TX, "Treatment of tetanus disease (p. 5)"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "India vs CDC: no MoHFW, NCDC or ICMR tetanus wound-management table was found (November 2026). Indian documents say TT or Td 0.5 mL IM if indicated; CDC/ACIP set explicit 5- and 10-year thresholds and TIG criteria by primary-series history.",
            listOf(
                cite(WB_MOH_TD, "Points to remember"),
                cite(WB_MOH_STG, "Ch. 1 Yellow area (p. 12)"),
                cite(WB_MO, "§2.3 (p. 31)"),
                cite(WB_CDC_TT_WOUND, "Tetanus vaccination status evaluation; TIG assessment"),
                cite(WB_ACIP18, "Table 6"),
            ),
        ),
        ProtocolNote(
            "Vaccine product: India's UIP uses Td for all ages and in pregnancy; MoHFW STG 2025 and the NCDC MO module still name TT. ACIP prefers Tdap at 11 years or older if never given and in pregnancy; Tdap is not in the UIP schedule.",
            listOf(
                cite(WB_MOH_TD, "UIP schedule after introduction of Td"),
                cite(WB_MOH_STG, "Ch. 1 Yellow area (p. 12)"),
                cite(WB_MO, "§2.3 (p. 31)"),
                cite(WB_ACIP19, "Tetanus prophylaxis for wound management recommendations"),
            ),
        ),
        ProtocolNote(
            "Bites: IDSA 2014 recommends tetanus toxoid if none within 10 years (Tdap preferred if never given), and its supporting text gives a booster for dirty wounds if >5 years since the last dose; CDC classes saliva-contaminated wounds, including bites, as dirty — vaccine if the last dose was 5 or more years ago.",
            listOf(cite(WB_IDSA, "Q XIII (recommendation and evidence summary)"), cite(WB_CDC_TT_WOUND, "Wound assessment; tetanus vaccination status evaluation")),
        ),
        ProtocolNote(
            "TIG in trauma in pregnancy: MoHFW STG 2025 lists 'administer tetanus immunoglobulin' without criteria; CDC, ACIP 2018 and WHO 2017 limit TIG to dirty wounds with an incomplete or unknown primary series, or HIV or severe immunodeficiency.",
            listOf(
                cite(WB_MOH_STG, "Ch. 13 Trauma in pregnancy — Definitive care (p. 59)"),
                cite(WB_CDC_TT_WOUND, "TIG assessment"),
                cite(WB_ACIP18, "Table 6"),
                cite(WB_WHO_TT, "Immunization in the case of injury (p. 74)"),
            ),
        ),
        ProtocolNote(
            "TIG treatment dose: CDC — a single 500 IU IM dose, about as effective as 3,000–6,000 IU; US TIG is not licensed for intrathecal or IV use. WHO 2010 — 500 units IM or IV depending on the preparation.",
            listOf(cite(WB_CDC_TT_CARE, "Tetanus immune globulin"), cite(WB_WHO_TT_TX, "Treatment of tetanus disease (p. 5)")),
        ),
        ProtocolNote(
            "Threshold wording differs slightly: the CDC page says 10 or more years (clean, minor) and 5 or more years (dirty, major); ACIP 2018 Table 6 says more than 10 years and 5 years or more.",
            listOf(cite(WB_CDC_TT_WOUND, "Tetanus vaccination status evaluation"), cite(WB_ACIP18, "Table 6 footnotes")),
        ),
    ),
    citations = listOf(
        cite(WB_MOH_TD), cite(WB_MOH_STG), cite(WB_MO), cite(WB_WHO_TT), cite(WB_WHO_TT_TX),
        cite(WB_CDC_TT_WOUND), cite(WB_ACIP18), cite(WB_ACIP19), cite(WB_CDC_TT_CARE),
    ),
)

internal val woundsBitesProtocols: List<Protocol> = listOf(animalBiteRabies, tetanusProphylaxis)
