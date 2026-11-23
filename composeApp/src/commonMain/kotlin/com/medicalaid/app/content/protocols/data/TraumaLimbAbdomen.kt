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
 * Abdominal and pelvic trauma, open fractures, compartment syndrome and crush injury. Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them (checked against the full texts of
 *   MoHFW STG Trauma 2025 (Ch. 6, Ch. 10, Table 5), WSES pelvic 2017, liver 2020, spleen 2017 and spleen follow-up 2022,
 *   the EAST REBOA guideline 2025 and open-fracture update 2011, the GAIS/SIS-E/WSIS/AAST/WSES antibiotic prophylaxis
 *   guideline 2024 (incl. Fig. 1), BOAST pelvic 2018, urological 2016, open fractures 2017, arterial injuries 2026 and
 *   compartment syndrome 2025 (and its archived 2014 version), NICE NG37 and NG39, ICMR STW Open Fractures 2024 and AKI
 *   2026, ICMR antimicrobial guidelines 2019 (Ch. 10), the European bleeding guideline 2023, AAOS 2025, Association of
 *   Anaesthetists 2021, INSARAG 2023, the ISN RDRTF authors' reviews (RMMJ 2011; Sever 2023 incl. Fig. 1) and the WHO
 *   BEC quick cards; 23 November 2026).
 * - Every point is attributed ("MoHFW 2025: …", "WSES 2017: …", "NICE NG39: …", "EAST 2025: …", "BOAST 2016: …").
 * - The ATLS manual is proprietary and is not used as a source. MoHFW 2025 refers to ATLS; only MoHFW's own statements
 *   are reported, attributed to MoHFW.
 * - Locators: MoHFW 2025 — chapter and printed page; NICE — recommendation number; European 2023 — recommendation (R)
 *   number; WSES — section and grade of recommendation (GoR); BOAST — standard number; EAST — PICO/Table 1.
 * - Existing sources reused by id: tr-mohfw-stg-trauma-2025, tr-nice-ng39, tr-euro-bleeding-2023 and tr-who-bec-2018
 *   (Trauma.kt); tr-wb-acip-tdap-2018 (WoundsBites.kt); ob-mat-cdc-tetanus-wound (MaternalCollapse.kt);
 *   em-ab-icmr-stw-aki-2026 (AcidBaseDisorders.kt); em-neuro-icmr-amr-2019 (NeuroEmergencies.kt).
 */

internal val limbAbdomenSources: List<Source> = listOf(
    Source(
        "tr-la-wses-pelvic-2017", Authority.WSES, "Classification and guidelines (World J Emerg Surg 2017;12:5)",
        "Pelvic trauma: WSES classification and guidelines", "2017",
        "https://doi.org/10.1186/s13017-017-0117-6",
        status = "Coccolini F et al.; full text reviewed; no later WSES pelvic trauma guideline found (November 2026)",
    ),
    Source(
        "tr-la-wses-liver-2020", Authority.WSES, "Guidelines (World J Emerg Surg 2020;15:24)",
        "Liver trauma: WSES 2020 guidelines", "2020",
        "https://doi.org/10.1186/s13017-020-00302-7",
        status = "Coccolini F et al.; full text reviewed",
    ),
    Source(
        "tr-la-wses-spleen-2017", Authority.WSES, "Classification and guidelines (World J Emerg Surg 2017;12:40)",
        "Splenic trauma: WSES classification and guidelines for adult and pediatric patients", "2017",
        "https://doi.org/10.1186/s13017-017-0151-4",
        status = "Coccolini F et al.; full text reviewed; follow-up during NOM updated by the WSES 2022 consensus",
    ),
    Source(
        "tr-la-wses-spleen-fu-2022", Authority.WSES, "Consensus document (World J Emerg Surg 2022;17:52)",
        "Follow-up strategies for patients with splenic trauma managed non-operatively: the 2022 World Society of Emergency Surgery consensus document",
        "2022",
        "https://doi.org/10.1186/s13017-022-00457-5",
        status = "Podda M et al.; full text reviewed",
    ),
    Source(
        "tr-la-east-reboa-2025", Authority.EAST, "Systematic review, meta-analysis and practice management guideline (Trauma Surg Acute Care Open 2025;10:e001730)",
        "Resuscitative Endovascular Balloon Occlusion of the Aorta in surgical and trauma patients: a systematic review, meta-analysis and practice management guideline from the Eastern Association for the Surgery of Trauma",
        "2025",
        "https://doi.org/10.1136/tsaco-2024-001730",
        status = "Harfouche MN et al.; GRADE methodology; full text reviewed",
    ),
    Source(
        "tr-la-gais-wses-abx-2024", Authority.WSES, "Joint GAIS, SIS-E, WSIS, AAST and WSES guideline (J Trauma Acute Care Surg 2024;96(4):674–682)",
        "Antibiotic prophylaxis in trauma: Global Alliance for Infection in Surgery, Surgical Infection Society Europe, World Surgical Infection Society, American Association for the Surgery of Trauma, and World Society of Emergency Surgery guidelines",
        "2024",
        "https://doi.org/10.1097/TA.0000000000004233",
        status = "Coccolini F et al.; published online 18 December 2023; full text reviewed (open-access copy in the University of Bologna repository)",
    ),
    Source(
        "tr-la-boast-pelvic-2018", Authority.BOA, "BOAST (BOA Audit Standards for Trauma)",
        "The Management of Patients with Pelvic Fractures", "2018",
        "https://www.boa.ac.uk/asset/E0FF512B-6364-42EF-AF23617E1894D8BD/",
        status = "January 2018; still listed as current on the BOA standards page (November 2026)",
    ),
    Source(
        "tr-la-boast-uro-2016", Authority.BOA, "BOAST (BOA and BAUS consensus)",
        "The Management of Urological Trauma Associated with Pelvic Fractures", "2016",
        "https://www.boa.ac.uk/asset/86C72EFF-26AA-4CEC-98D1E85CDA3DAC6C/",
        status = "August 2016; still listed as current on the BOA standards page (November 2026)",
    ),
    Source(
        "tr-la-nice-ng37", Authority.NICE, "Guideline NG37",
        "Fractures (complex): assessment and management", "2016",
        "https://www.nice.org.uk/guidance/ng37",
        status = "Published 17 February 2016; last updated 23 November 2022",
    ),
    Source(
        "tr-la-boast-openfx-2017", Authority.BOA, "BOAST (BOA and BAPRAS Audit Standards for Trauma)",
        "Open Fractures", "2017",
        "https://www.boa.ac.uk/asset/3B91AD0A-9081-4253-92F7D90E8DF0FB2C/",
        status = "December 2017; orthoplastic-centre definition updated November 2019; still listed as current on the BOA standards page (November 2026)",
    ),
    Source(
        "tr-la-boast-arterial-2026", Authority.BOA, "BOA Standard",
        "Diagnosis and management of arterial injuries associated with musculoskeletal trauma", "2026",
        "https://www.boa.ac.uk/asset/7E8D1CA3-7448-47C7-84980C6D3E7E96B0/",
        status = "June 2026; replaces the archived BOAST on arterial injuries associated with extremity fractures and dislocations",
    ),
    Source(
        "tr-la-icmr-stw-openfx-2024", Authority.ICMR, "Standard Treatment Workflow (Orthopaedics, Vol IV; ICD-10 S82.891B)",
        "Open Fractures", "2024",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726645314_9_open_fracture.pdf",
        status = "July 2024",
    ),
    Source(
        "tr-la-east-openfx-2011", Authority.EAST, "Practice management guideline update (J Trauma 2011;70(3):751–754)",
        "East Practice Management Guidelines Work Group: Update to Practice Management Guidelines for Prophylactic Antibiotic Use in Open Fractures",
        "2011",
        "https://doi.org/10.1097/TA.0b013e31820930e5",
        status = "Hoff WS et al.; full text reviewed; no later EAST open-fracture antibiotic guideline found (November 2026)",
    ),
    Source(
        "tr-la-boast-compartment-2025", Authority.BOA, "BOA Standard (BOAST 10, revised July 2025)",
        "Diagnosis and Management of Compartment Syndrome of the Extremities", "2025",
        "https://www.boa.ac.uk/asset/D0432D40-5D99-4B64-9952F6EB1B60C166/",
        status = "First published July 2014; revised July 2025",
    ),
    Source(
        "tr-la-boast-compartment-2014", Authority.BOA, "BOAST 10 (archived)",
        "Diagnosis and Management of Compartment Syndrome of the Limbs", "2014",
        "https://www.boa.ac.uk/asset/2A3DBE24-B921-4395-92631F3BE3160528/",
        status = "Superseded and archived July 2025; cited only to show a change of wording in the 2025 revision",
    ),
    Source(
        "tr-la-aaos-acs-2025", Authority.Paper, "AAOS evidence-based clinical practice guideline (rapid update)",
        "Management of Acute Compartment Syndrome", "2025",
        "https://www.aaos.org/globalassets/quality-and-practice-resources/acute-compartment-syndrome/2025-acs-rapid-update/acs-cpg-2025.pdf",
        status = "American Academy of Orthopaedic Surgeons; adopted 18 November 2025; adults with traumatised extremities; full text reviewed",
    ),
    Source(
        "tr-la-aoa-acs-2021", Authority.Paper, "Association of Anaesthetists guideline (Anaesthesia 2021;76:1518–1525)",
        "Regional analgesia for lower leg trauma and the risk of acute compartment syndrome: Guideline from the Association of Anaesthetists",
        "2021",
        "https://doi.org/10.1111/anae.15504",
        status = "Nathanson MH et al.; multi-professional consensus; full text reviewed",
    ),
    Source(
        "tr-la-insarag-crush-2023", Authority.Paper, "INSARAG Medical Working Group technical reference note (version 1.3)",
        "The Medical Management of the Entrapped Patient with Crush Syndrome", "2023",
        "https://insarag.org/wp-content/uploads/2023/05/Attachment-C2-MWG-The-Medical-Management-of-the-Entrapped-Patient-with-Crush-Syndrome-March-2023.pdf",
        status = "UN OCHA INSARAG network; March 2023, updated from the 2011 and 2019 versions; consensus good practice, not part of the INSARAG Guidelines",
    ),
    Source(
        "tr-la-isn-rmmj-2011", Authority.Paper, "ISN Renal Disaster Relief Task Force review (Rambam Maimonides Med J 2011;2(2):e0039)",
        "Management of Crush Syndrome Casualties after Disasters", "2011",
        "https://doi.org/10.5041/RMMJ.10039",
        status = "Sever MS, Vanholder R (ISN RDRTF local co-ordinator and chairman); full text reviewed",
    ),
    Source(
        "tr-la-isn-sever-2023", Authority.Paper, "Invited review (Acta Orthop Traumatol Turc 2023;57(6):306–314)",
        "Destructive disasters, trauma, crush syndrome, and beyond", "2023",
        "https://doi.org/10.5152/j.aott.2023.23147",
        status = "Sever MS (ISN RDRTF) et al.; its fluid algorithm (Fig. 1), dialysis criteria and hyperkalaemia advice are adapted from the ISN/ERBP 2012 " +
            "Recommendations for the Management of Crush Victims in Mass Disasters (Nephrol Dial Transplant 2012;27 Suppl 1:i1–i67), whose full text could not be accessed",
    ),
)

private const val WSES_PELVIC = "tr-la-wses-pelvic-2017"
private const val WSES_LIVER = "tr-la-wses-liver-2020"
private const val WSES_SPLEEN = "tr-la-wses-spleen-2017"
private const val WSES_SPLEEN_FU = "tr-la-wses-spleen-fu-2022"
private const val EAST_REBOA = "tr-la-east-reboa-2025"
private const val ABX_2024 = "tr-la-gais-wses-abx-2024"
private const val BOAST_PELVIC = "tr-la-boast-pelvic-2018"
private const val BOAST_URO = "tr-la-boast-uro-2016"
private const val NG37 = "tr-la-nice-ng37"
private const val BOAST_OPENFX = "tr-la-boast-openfx-2017"
private const val BOAST_ARTERIAL = "tr-la-boast-arterial-2026"
private const val ICMR_OPENFX = "tr-la-icmr-stw-openfx-2024"
private const val EAST_OPENFX = "tr-la-east-openfx-2011"
private const val BOAST_CS = "tr-la-boast-compartment-2025"
private const val BOAST_CS_2014 = "tr-la-boast-compartment-2014"
private const val AAOS_ACS = "tr-la-aaos-acs-2025"
private const val AOA_ACS = "tr-la-aoa-acs-2021"
private const val INSARAG = "tr-la-insarag-crush-2023"
private const val ISN_2011 = "tr-la-isn-rmmj-2011"
private const val ISN_2023 = "tr-la-isn-sever-2023"

/** Defined in Trauma.kt — MoHFW (Dte.GHS) Standard Treatment Guidelines for Management of Trauma, 2025. */
private const val LA_MOH = "tr-mohfw-stg-trauma-2025"

/** Defined in Trauma.kt — NICE NG39 Major trauma: assessment and initial management. */
private const val LA_NG39 = "tr-nice-ng39"

/** Defined in Trauma.kt — European guideline on major bleeding and coagulopathy following trauma, 6th edition (2023). */
private const val LA_EU = "tr-euro-bleeding-2023"

/** Defined in Trauma.kt — WHO/ICRC Basic Emergency Care (2018). */
private const val LA_WHO = "tr-who-bec-2018"

/** Defined in WoundsBites.kt — ACIP 2018 pertussis, tetanus and diphtheria vaccine recommendations. */
private const val LA_ACIP = "tr-wb-acip-tdap-2018"

/** Defined in MaternalCollapse.kt — CDC clinical guidance for wound management to prevent tetanus. */
private const val LA_CDC_TT = "ob-mat-cdc-tetanus-wound"

/** Defined in AcidBaseDisorders.kt — ICMR Standard Treatment Workflow, Acute Kidney Injury (updated July 2026). */
private const val LA_ICMR_AKI = "em-ab-icmr-stw-aki-2026"

/** Defined in NeuroEmergencies.kt — ICMR Treatment Guidelines for Antimicrobial Use in Common Syndromes, 2nd edition (2019). */
private const val ICMR_AMR = "em-neuro-icmr-amr-2019"

// ================================================================================================ Abdominal and pelvic trauma
private val abdominalPelvicTrauma = Protocol(
    id = "abdominal-pelvic-trauma",
    category = ProtocolCategory.Trauma,
    title = "Abdominal and pelvic trauma",
    summary = "Blunt and penetrating abdominal injury, eFAST and CT, pelvic binder, laparotomy, packing, angioembolisation and " +
        "REBOA, non-operative management of liver and spleen injury, urethral injury and antibiotics — attributed to MoHFW 2025, " +
        "WSES, EAST, NICE NG39, the European guideline 2023, BOAST and WHO BEC.",
    keywords = listOf(
        "abdominal trauma", "blunt abdomen", "blunt abdominal trauma", "BAT", "blunt trauma abdomen", "BTA",
        "penetrating abdominal trauma", "stab injury", "stab wound", "stab abdomen", "gunshot abdomen", "GSW",
        "FAST", "eFAST", "E-FAST", "DPL", "diagnostic peritoneal lavage", "haemoperitoneum", "hemoperitoneum",
        "pelvic fracture", "pelvic ring injury", "open book pelvis", "pelvic binder", "SAM pelvic sling", "T-POD",
        "sheet wrap", "preperitoneal packing", "PPP", "angioembolisation", "angioembolization", "REBOA",
        "laparotomy", "exploratory laparotomy", "damage control surgery", "liver injury", "liver laceration",
        "spleen injury", "splenic injury", "ruptured spleen", "splenic rupture", "NOM", "non-operative management",
        "urethral injury", "blood at meatus", "retrograde urethrogram", "RUG", "bladder rupture", "evisceration",
        "impaled object", "seat belt sign", "pet mein chot", "pet ki chot", "pet vich satt", "chaku", "chaaku ka vaar",
        "goli lagna", "kulhe ki haddi tootna", "tilli phatna", "jigar ki chot",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Recognise: blunt versus penetrating",
            timing = "‘C’ phase of the primary survey (MoHFW 2025)",
            points = listOf(
                "MoHFW 2025: examine the abdomen in the ‘C’ phase — identify and stop the source of bleeding; activate the massive transfusion protocol early if indicated.",
                "MoHFW 2025: blunt abdominal injuries are often managed conservatively; most significant penetrating injuries need laparotomy; gunshot and stab wounds are managed differently.",
                "MoHFW 2025: pelvic fractures can bleed massively, especially when open; do a pelvic compression test. WSES 2017: spring the pelvis cautiously — it can increase bleeding.",
                "NICE NG37: do not log roll a suspected pelvic fracture before pelvic imaging unless occult penetrating injury is suspected with instability, or to clear the airway.",
                "MoHFW 2025: look for seat-belt marks and Cullen's or Grey-Turner's signs; rectal and vaginal examination for blood, sphincter tone and tears; leg-length discrepancy or malrotation.",
                "WSES 2020: haemodynamic instability — SBP <90 mm Hg with signs of shock, or >90 mm Hg needing boluses, transfusion or vasopressors, or >4 units of red cells in 8 h.",
            ),
            citations = listOf(
                cite(LA_MOH, "Ch. 6 Recognise; Primary survey; Secondary survey (pp. 29–30)"),
                cite(WSES_PELVIC, "Recommendations for diagnostic tools — rationale"),
                cite(NG37, "Rec 1.2.19"),
                cite(WSES_LIVER, "Definitions — haemodynamic instability (adults)"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "eFAST, X-rays and DPL: role and limits",
            timing = "Bedside, during resuscitation — must not delay life-saving manoeuvres (WSES 2017)",
            points = listOf(
                "MoHFW 2025: bedside AP chest and pelvis X-ray and eFAST are mandatory for major trauma and for all yellow-area patients; chest X-ray for air under the diaphragm.",
                "WSES 2020: eFAST is rapid in detecting free fluid but may be falsely negative (clotted blood, poor views); in children its sensitivity is 42–52%.",
                "NICE NG39: a negative FAST does not exclude intraperitoneal or retroperitoneal haemorrhage. Do not use FAST before immediate CT or to decide who needs CT.",
                "European 2023: POCUS including FAST in thoracoabdominal injury (1C); early contrast whole-body CT to find the injury and the bleeding source (1B).",
                "WSES 2017 (citing EAST): eFAST is not sensitive enough to exclude pelvic bleeding but may exclude the need for laparotomy in unstable patients.",
                "NICE NG39: unstable and not responding to volume — limit imaging (chest and pelvis X-ray, FAST) to the minimum needed to direct intervention.",
                "WSES 2020: diagnostic peritoneal lavage where CT or ultrasound is not promptly available (low-resource settings); complications up to 2%.",
            ),
            citations = listOf(
                cite(LA_MOH, "Table 1 (p. 11); Yellow area (p. 12); Ch. 6 Investigation (p. 30)"),
                cite(WSES_LIVER, "Diagnosis (GoR 1A) and rationale"),
                cite(LA_NG39, "Rec 1.5.29–1.5.33"),
                cite(WSES_PELVIC, "Recommendations for diagnostic tools (GoR 1B) and rationale"),
                cite(LA_EU, "R8"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "CT in the haemodynamically stable patient",
            timing = "Once stable, stabilised or responding to resuscitation",
            points = listOf(
                "MoHFW 2025: contrast CT abdomen and pelvis if haemodynamically stable and there is abdominal tenderness, altered sensorium, or distracting or adjacent injuries.",
                "WSES 2017 and 2020: IV-contrast CT is the gold standard in haemodynamically stable or stabilised patients and must precede any non-operative management.",
                "NICE NG39: consider immediate CT if responding to resuscitation or haemodynamically normal; whole-body CT for adults with blunt major trauma and suspected multiple injuries.",
                "NICE NG39: do not routinely use whole-body CT in children under 16 — limit CT to the areas needing assessment.",
                "WSES 2017: arterial contrast extravasation and pelvic haematoma predict the need for angioembolisation; haematoma ≥500 cm³ suggests arterial injury even without a blush.",
                "NICE NG37: CT is first-line imaging for suspected high-energy pelvic fracture in adults. BOAST 2018: contrast CT of head, chest, abdomen and pelvis with a head-to-toe scanogram.",
            ),
            citations = listOf(
                cite(LA_MOH, "Ch. 6 Investigation (p. 30)"),
                cite(WSES_PELVIC, "Recommendations for diagnostic tools (GoR 1B) and rationale"),
                cite(WSES_LIVER, "Diagnosis (GoR 1A); Non-operative management (GoR 2A)"),
                cite(LA_NG39, "Rec 1.5.31, 1.5.34, 1.5.36"),
                cite(NG37, "Rec 1.2.14"),
                cite(BOAST_PELVIC, "Standard 4"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Pelvic binder",
            timing = "Early — before intubation (MoHFW 2025)",
            points = listOf(
                "NICE NG39: suspected active bleeding from a pelvic fracture after blunt high-energy trauma — purpose-made binder; improvised only if one does not fit. European 2023: apply pre-hospital.",
                "Level — MoHFW 2025: centred over the greater trochanters, not the iliac crests, legs internally rotated. WSES 2017: around the greater trochanters and symphysis pubis.",
                "MoHFW 2025: apply before intubation, as neuromuscular blockade may let pelvic volume expand; a sheet can be used (Fig. 10).",
                "WSES 2017: commercial binders control bleeding better than sheets, but a sheet is effective and safe where none is available; use cautiously in pregnant and elderly patients.",
                "Removal — NICE NG37: all binders within 24 h of application, agreeing first with a pelvic surgeon how to manage an unstable fracture. WSES 2017: not beyond 24–48 h.",
                "BOAST 2018: keep it on during damage-control laparotomy; post-binder pelvic X-ray once stable — a well-applied binder can mask a major pelvic ring injury.",
            ),
            citations = listOf(
                cite(LA_NG39, "Rec 1.5.3"),
                cite(LA_EU, "R20"),
                cite(LA_MOH, "Ch. 6 Resuscitate — pelvic stabilisation; Fig. 10 (p. 30)"),
                cite(WSES_PELVIC, "Role of pelvic binder (GoR 1A, 1B, 1C, 2A) and rationale"),
                cite(NG37, "Rec 1.2.17–1.2.18"),
                cite(BOAST_PELVIC, "Standards 1, 5, 7, 8"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Unstable: laparotomy and damage control",
            timing = "Minimise time from arrival to bleeding control (WSES 2017; European 2023)",
            points = listOf(
                "European 2023: obvious bleeding source, or shock in extremis with a suspected source — immediate bleeding control procedure.",
                "NICE NG39: damage control surgery if unstable and not responding to volume; definitive surgery if haemodynamically normal.",
                "WSES 2020 (liver) and 2017 (spleen): haemodynamically unstable non-responders need operative management. WSES 2020: control haemorrhage and bile leak; avoid major hepatic resection at first.",
                "MoHFW 2025: damage control surgery limits itself to haemorrhage and contamination control until physiology allows definitive repair.",
                "BOAST 2018: tranexamic acid IV as soon as possible, ideally within 1 h. Doses, blood products and reversal: see the Traumatic haemorrhage protocol.",
                "MoHFW 2025: if expertise or resources, including angioembolisation, are unavailable, transfer to a higher level of care.",
            ),
            checklistTitle = "MoHFW 2025 — indications for emergency laparotomy",
            checklist = listOf(
                "Peritonism or evisceration",
                "Penetrating abdominal trauma with hypotension",
                "Blunt abdominal trauma with hypotension and a positive FAST, positive DPL or peritonism",
                "Left thoracoabdominal wound (risk of diaphragmatic injury)",
                "Bleeding from stomach, rectum or genitourinary tract after penetrating trauma",
                "Gunshot wound with peritoneal penetration",
                "DPL: GI contents, vegetable fibres or bile; or ≥10 mL blood in a haemodynamically abnormal patient",
            ),
            citations = listOf(
                cite(LA_EU, "R6"),
                cite(LA_NG39, "Rec 1.5.37–1.5.39"),
                cite(WSES_LIVER, "Operative management (GoR 2A, 2B)"),
                cite(WSES_SPLEEN, "Table 4 — Operative management (GoR 2A)"),
                cite(LA_MOH, "Ch. 6 Indication for emergency laparotomy; Damage control surgery; Refer (pp. 31–32)"),
                cite(BOAST_PELVIC, "Standard 3"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Unstable pelvic fracture: packing, angioembolisation, REBOA",
            timing = "Angioembolisation after pelvic stabilisation, haemostatic resuscitation and exclusion of extra-pelvic bleeding (WSES 2017)",
            points = listOf(
                "MoHFW 2025 (Fig. 12): alert orthopaedics, theatre and interventional radiology; FAST/DPL — haemoperitoneum: laparotomy, preperitoneal packing, ex-fix if amenable, then angiography.",
                "MoHFW 2025 (Fig. 12): no significant haemoperitoneum — angiography if immediately available; otherwise ex-fix and preperitoneal packing, then angiography.",
                "NICE NG37: active arterial pelvic bleeding — interventional radiology first if no emergency laparotomy is needed, pelvic packing if it is. NICE NG39: consider IR for solid-organ arterial bleeding.",
                "European 2023: extraperitoneal packing when bleeding continues or angioembolisation cannot be done in time. WSES 2017: consider packing especially where angiography is unavailable.",
                "WSES 2017: external fixation is an adjunct to early haemorrhage control and gives counterpressure for packing; C-clamp contraindicated in comminuted or transforaminal sacral, iliac-wing and LC-type injuries.",
                "REBOA — sources differ: WSES 2017 zone III as a bridge (2B); European 2023 may be considered (2C); EAST 2025 conditionally against in unstable pelvic or subdiaphragmatic bleeding.",
            ),
            citations = listOf(
                cite(LA_MOH, "Ch. 6 Fig. 11 (p. 31); Fig. 12 (p. 33)"),
                cite(NG37, "Rec 1.2.16"),
                cite(LA_NG39, "Rec 1.5.40–1.5.41"),
                cite(LA_EU, "R21 and rationale"),
                cite(WSES_PELVIC, "Role of pre-peritoneal pelvic packing (GoR 1B, 1C); external fixation (GoR 1A, 2A, 2B); angioembolization (GoR 1A, 2A); REBOA (GoR 2B)"),
                cite(EAST_REBOA, "Table 1 — PICO 1 and 2"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Liver and spleen injury: non-operative management",
            timing = "Splenic NOM failure risk is highest in the first 24–72 h (WSES 2022)",
            points = listOf(
                "WSES 2020 and 2017: NOM is the treatment of choice when haemodynamically stable without other injury needing surgery, irrespective of grade; CT with IV contrast first.",
                "WSES: NOM of moderate or severe injury only with intensive monitoring, angioembolisation, an immediately available theatre and blood — or a rapid transfer system.",
                "WSES 2020: in low-resource settings, NOM may be considered if stable without associated injuries, with negative serial examinations, imaging and blood tests.",
                "WSES 2020 and 2017: angioembolisation may be first-line in stable patients with an arterial blush on CT; in children a blush is not an absolute indication.",
                "WSES 2020: serial examinations and haemoglobin during NOM. WSES 2022 (spleen): high-grade injuries — continuous monitoring, Hb every 8 h, CEUS or CT at 48–72 h.",
                "WSES 2017 (spleen): age >55, high ISS and moderate–severe injury predict NOM failure; with severe TBI, NOM only if theatre or angioembolisation is rapidly available.",
            ),
            checklistTitle = "NOM not appropriate (WSES)",
            checklist = listOf(
                "Haemodynamic instability unresponsive to resuscitation",
                "Peritonitis, bowel evisceration or impalement",
                "Another internal injury that needs surgery",
                "Penetrating trauma with CT free air, free fluid without solid-organ injury, bowel-wall thickening, or a bullet tract near bowel with haematoma (WSES 2020)",
                "High-energy penetrating trauma (WSES 2020)",
                "Moderate–severe injury where monitoring, angioembolisation and theatre are not available and rapid transfer is not possible",
            ),
            citations = listOf(
                cite(WSES_LIVER, "Non-operative management (GoR 2A, 2B, 2C) and rationale"),
                cite(WSES_SPLEEN, "Table 4 — Non-operative management; Angiography/angioembolization (GoR 1A, 1C, 2A, 2B)"),
                cite(WSES_SPLEEN_FU, "Research questions 3, 4 and 6 — statements and recommendations"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Urethral and bladder injury: before the catheter",
            timing = "Before any urethral catheterisation",
            points = listOf(
                "MoHFW 2025: no urethral catheter with blood at the meatus or perineal ecchymosis; retrograde urethrogram if unable to void, needing a binder, or with meatal blood, scrotal haematoma or perineal ecchymosis.",
                "WSES 2017: urethral injury signs include a high-riding or non-palpable prostate and an unstable pelvic fracture; positive urethrogram or high suspicion — suprapubic catheter.",
                "BOAST 2016: after all high-energy trauma, examine and record perineum, genitalia and rectum.",
                "BOAST 2016: one gentle attempt by an experienced doctor (adult 16F silicone) is permissible even if urethral injury is suspected; if it fails or drains only blood, do not inflate — urethrogram.",
                "BOAST 2016: blood-stained urine via the catheter — retrograde cystogram; intraperitoneal bladder rupture needs emergency laparotomy and repair.",
                "BOAST 2016: suprapubic catheter in the midline, 3–4 finger-breadths above the symphysis, by ultrasound-guided Seldinger technique by an experienced doctor.",
            ),
            checklistTitle = "Signs of urethral injury (MoHFW 2025; WSES 2017)",
            checklist = listOf(
                "Blood at the urethral meatus",
                "Scrotal or perineal haematoma or ecchymosis",
                "High-riding or non-palpable prostate on rectal examination",
                "Inability to void",
                "Unstable or displaced pelvic fracture",
            ),
            citations = listOf(
                cite(LA_MOH, "Ch. 2 Adjuncts — urinary catheters (p. 16); Ch. 6 Secondary survey (p. 30)"),
                cite(WSES_PELVIC, "Recommendations for diagnostic tools (GoR 1B) and rationale"),
                cite(BOAST_URO, "Standards 2–5, 7, 8, 11"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "Penetrating injury, antibiotics and tetanus",
            points = listOf(
                "WHO BEC: leave a penetrating object in place and stabilise it to prevent further injury. WSES 2017: impalement is an indication for operative management.",
                "WHO BEC: open abdominal wound — IV fluids, nothing by mouth, cover visible bowel with sterile gauze soaked in sterile saline, give antibiotics.",
                "WSES 2020 (liver): stable low-energy stab or gunshot wounds may have NOM first in experienced centres; about 90% of high-energy gunshot wounds need surgery.",
                "GAIS/WSES 2024: prophylaxis is indicated in penetrating abdominal trauma, especially with laparotomy or laparoscopy; not in blunt trauma managed non-operatively.",
                "GAIS/WSES 2024: broad aerobic and anaerobic cover, avoiding aminoglycosides where possible; no more than 24 h without hollow-viscus injury; contaminated hollow-viscus injury needs therapy, not prophylaxis.",
                "CDC: penetrating or puncture wounds are tetanus-prone. WHO BEC: injured patients with wounds need tetanus vaccination. See the Tetanus prophylaxis protocol.",
            ),
            citations = listOf(
                cite(LA_WHO, "Quick cards — Management of specific conditions"),
                cite(WSES_SPLEEN, "Table 4 — Operative management (GoR 2A)"),
                cite(WSES_LIVER, "Non-operative management — penetrating trauma (rationale)"),
                cite(ABX_2024, "Abdominal trauma — recommendations and rationale"),
                cite(LA_CDC_TT, "Wound assessment — dirty or major wounds"),
                cite(LA_ACIP, "Table 6 footnotes"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "REBOA is contested: WSES 2017 (pelvic) suggests zone III REBOA as a bridge in unstable suspected pelvic bleeding (GoR 2B) and WSES 2020 (liver) as a bridge (2B); " +
                "European 2023 suggests considering it for noncompressible bleeding (2C) but finds survival evidence conflicting; EAST 2025 conditionally recommends against it in " +
                "unstable patients with suspected subdiaphragmatic bleeding or pelvic fracture, and conditionally for it over resuscitative thoracotomy in arrest from subdiaphragmatic bleeding.",
            listOf(
                cite(WSES_PELVIC, "Role of REBOA"), cite(WSES_LIVER, "Operative management (GoR 2B)"),
                cite(LA_EU, "R21 and rationale"), cite(EAST_REBOA, "Table 1"),
            ),
        ),
        ProtocolNote(
            "Urethral catheterisation differs: MoHFW 2025 treats blood at the meatus or perineal ecchymosis as a contraindication and asks for a retrograde urethrogram; " +
                "WSES 2017 recommends a urethrogram before catheterisation when signs are present; BOAST 2016 (BOA/BAUS) permits one gentle attempt by an experienced doctor.",
            listOf(cite(LA_MOH, "Ch. 2 (p. 16); Ch. 6 (p. 30)"), cite(WSES_PELVIC, "Recommendations for diagnostic tools"), cite(BOAST_URO, "Standards 3, 5")),
        ),
        ProtocolNote(
            "Pelvic examination differs: MoHFW 2025 asks for a pelvic compression test; WSES 2017 warns that springing the pelvis can increase bleeding and should be done cautiously.",
            listOf(cite(LA_MOH, "Ch. 6 Primary survey (p. 29)"), cite(WSES_PELVIC, "Recommendations for diagnostic tools — rationale")),
        ),
        ProtocolNote(
            "FAST differs: MoHFW 2025 makes eFAST mandatory for major trauma and for all yellow-area patients; NICE NG39 advises against FAST before immediate CT or as a screen for CT; " +
                "European 2023 recommends POCUS including FAST in thoracoabdominal injury, with early contrast whole-body CT.",
            listOf(cite(LA_MOH, "Table 1 (p. 11); Yellow area (p. 12)"), cite(LA_NG39, "Rec 1.5.32–1.5.33"), cite(LA_EU, "R8")),
        ),
        ProtocolNote(
            "Resource setting: WSES 2020 accepts non-operative liver management in low-resource settings only with stability, no associated injuries and negative serial checks, and DPL where CT or ultrasound is unavailable; " +
                "WSES 2017 advises operative management of moderate–severe splenic injury where monitoring or angioembolisation is not rapidly available; MoHFW 2025 advises transfer when angioembolisation is unavailable.",
            listOf(cite(WSES_LIVER, "Non-operative management (GoR 2C); Diagnosis — rationale"), cite(WSES_SPLEEN, "Table 4 — Operative management"), cite(LA_MOH, "Ch. 6 Refer (p. 32)")),
        ),
        ProtocolNote(
            "Scope: the ATLS manual is proprietary and is not used as a source. MoHFW 2025 describes a ‘coordinated team based ATLS approach’; its statements are reported as MoHFW publishes them. " +
                "Pregnancy, tranexamic acid dosing and transfusion are covered in the Trauma in pregnancy and Traumatic haemorrhage protocols.",
            listOf(cite(LA_MOH, "Ch. 6 Resuscitate (p. 30)"), cite(LA_NG39)),
        ),
    ),
    citations = listOf(
        cite(LA_MOH), cite(WSES_PELVIC), cite(WSES_LIVER), cite(WSES_SPLEEN), cite(WSES_SPLEEN_FU), cite(EAST_REBOA),
        cite(ABX_2024), cite(BOAST_PELVIC), cite(BOAST_URO), cite(LA_NG39), cite(NG37), cite(LA_EU), cite(LA_WHO), cite(LA_CDC_TT), cite(LA_ACIP),
    ),
)

// ================================================================================================ Open fractures
private val openFractures = Protocol(
    id = "open-fractures",
    category = ProtocolCategory.Trauma,
    title = "Open fractures",
    summary = "Gustilo–Anderson grading, antibiotic timing, agents, penicillin-allergy alternatives and duration, wound care, " +
        "splinting, tetanus, bleeding control, neurovascular checks, arterial injury, debridement and soft-tissue cover — attributed to BOAST, " +
        "NICE NG37, ICMR STW 2024, MoHFW 2025, EAST 2011 and GAIS/WSES 2024.",
    keywords = listOf(
        "open fracture", "compound fracture", "open long bone fracture", "open tibia fracture", "open femur fracture",
        "open joint injury", "Gustilo", "Gustilo-Anderson", "GA type", "type IIIB", "orthoplastic", "debridement",
        "wound excision", "wound wash", "external fixator", "ex-fix", "flap cover", "fracture antibiotics", "cefazolin",
        "Reflin", "cefuroxime", "Supacef", "gentamicin", "Genticyn", "clindamycin", "Dalacin C",
        "piperacillin-tazobactam", "pip-taz", "metronidazole", "Metrogyl", "khuli haddi", "haddi bahar",
        "haddi bahar nikalna", "haddi tootna", "haddi tutt gayi", "zakhm wali haddi",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Recognise and grade",
            timing = "At first contact, after the primary survey",
            points = listOf(
                "ICMR STW 2024: open when the fracture or its haematoma communicates with the external environment; preserve life first, then limb, then function.",
                "MoHFW 2025: open long-bone fractures and any pelvic fracture are red-triage criteria; manage ABCD first and immobilise life-threatening fractures in the primary survey.",
                "ICMR STW 2024: expose the entire limb — wound size, muscle crush or loss, periosteal stripping or bone loss, contamination and neurovascular status.",
                "MoHFW 2025: open fractures and open joint injuries are limb-threatening injuries, managed together.",
                "EAST 2011 (Gustilo): type III includes segmental fractures and traumatic amputation; gunshot and farm-injury open fractures are special type III categories.",
            ),
            checklistTitle = "Gustilo–Anderson grade (ICMR STW 2024)",
            checklist = listOf(
                "I — wound <1 cm, low energy, no contamination, simple fracture",
                "II — wound 1–10 cm, moderate energy and soft-tissue injury, low contamination",
                "IIIA — wound >10 cm, high energy, extensive soft-tissue injury, coverage possible",
                "IIIB — high energy, extensive soft-tissue injury, no soft-tissue coverage",
                "IIIC — vascular injury (EAST 2011: needing repair for limb preservation)",
            ),
            citations = listOf(
                cite(ICMR_OPENFX, "Definition; Clinical examination; Goals of treatment; Gustilo–Anderson classification"),
                cite(LA_MOH, "Ch. 1 Triage criteria (pp. 6–7); Ch. 10 Primary survey (p. 48); Limb threatening injuries (p. 49)"),
                cite(EAST_OPENFX, "Table 1"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Antibiotics: give early",
            timing = "Ideally within 1 h of injury (BOAST 2017; NICE NG37; ICMR STW 2024); MoHFW 2025 — within 3 h",
            points = listOf(
                "BOAST 2017: IV prophylactic antibiotics as soon as possible, ideally within 1 h of injury. NICE NG37: give immediately in the emergency department if not already given.",
                "ICMR STW 2024: early antibiotics are the single most important factor in reducing infection. MoHFW 2025: IV antibiotics within 3 h of injury.",
                "EAST 2011: gram-positive cover as soon as possible; add gram-negative cover for type III; add high-dose penicillin for faecal or possible clostridial (farm) contamination.",
                "EAST 2011: fluoroquinolones offer no advantage over cephalosporin/aminoglycoside regimens and may impair fracture healing and raise infection rates in type III fractures.",
                "GAIS/WSES 2024: prophylaxis effectively reduces wound infection in open fractures; 7–10-day courses do not lower the infection rate.",
                "BOAST 2017: each network should have a published open-fracture antibiotic guideline — follow the local policy where one exists.",
            ),
            citations = listOf(
                cite(BOAST_OPENFX, "Standards 2–3"),
                cite(NG37, "Rec 1.1.10, 1.2.22"),
                cite(ICMR_OPENFX, "Antibiotic — which, when and for how long?"),
                cite(LA_MOH, "Ch. 10 Open fracture and open joint injuries (p. 49)"),
                cite(EAST_OPENFX, "Recommendations — Level I"),
                cite(ABX_2024, "Open fractures — recommendations"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Antibiotic regimens as sourced",
            points = listOf(
                "ICMR STW 2024: cefuroxime 1.5 g IV, 3 doses 8 h apart; type III — add gentamicin 5 mg/kg every 24 h; possible soil contamination — add metronidazole 500 mg IV 8-hourly.",
                "GAIS/WSES 2024, Gustilo I–II: cefazolin 2 g IV at once, then 2 g 8-hourly for 2 more doses (3 g if >120 kg); penicillin allergy — clindamycin 900 mg IV 8-hourly.",
                "GAIS/WSES 2024, Gustilo III: cefazolin as above plus gentamicin 5 mg/kg IV every 24 h; penicillin allergy — clindamycin 900 mg IV 8-hourly plus gentamicin.",
                "MoHFW 2025: cefazolin 8-hourly — 1 g (<50 kg), 2 g (50–100 kg), 3 g (>100 kg); anaphylactic penicillin allergy — clindamycin 600 mg (<80 kg) or 900 mg (>80 kg) 8-hourly.",
                "MoHFW 2025: severe soft-tissue damage and substantial contamination with associated vascular injury — add a gentamicin loading dose in the emergency department (below).",
                "MoHFW 2025: farmyard, soil or standing-water contamination, whatever the wound size — piperacillin–tazobactam 3.375 g 6-hourly (<100 kg) or 4.5 g 6-hourly (>100 kg).",
                "Gentamicin cautions — INSARAG 2023: avoid aminoglycosides in crush injury (see Compartment syndrome and crush injury). GAIS/WSES 2024 (abdominal trauma): avoid where possible; adjust doses in haemorrhagic shock with AKI.",
                "ICMR 2019, gentamicin and renal function: CrCl 40–60 mL/min — 5 mg/kg every 36 h; <40 — 4 mg/kg, next dose preferably guided by drug level and only if definitely needed.",
            ),
            doses = listOf(
                StepDose(
                    "Gentamicin — Gustilo III (GAIS/WSES 2024; ICMR STW 2024)",
                    WeightDose(5.0, 5.0, unit = "mg", per = "IV every 24 h"),
                    note = "Added to cefazolin (GAIS/WSES 2024) or cefuroxime (ICMR STW 2024). Avoid in crush injury (INSARAG 2023); renal impairment or shock — see the cautions above",
                ),
                StepDose(
                    "Gentamicin — ED loading dose, adult (MoHFW 2025)",
                    WeightDose(5.0, 5.0, unit = "mg", per = "IV loading dose"),
                    note = "MoHFW Table 5 example: 150-lb patient = 340 mg. Avoid in crush injury (INSARAG 2023); renal impairment or shock — see the cautions above",
                ),
                StepDose(
                    "Gentamicin — ED loading dose, child or <50 kg (MoHFW 2025)",
                    WeightDose(2.5, 2.5, unit = "mg", per = "IV loading dose"),
                    note = "Printed as ‘2.5 m/kg for child (or <50 kg)’ in MoHFW Table 5. Avoid in crush injury (INSARAG 2023); renal impairment or shock — see the cautions above",
                ),
            ),
            citations = listOf(
                cite(ICMR_OPENFX, "Antibiotic — which, when and for how long?"),
                cite(ABX_2024, "Fig. 1 — Antibiotic prophylaxis decision algorithm in open fractures; Abdominal trauma — recommendations"),
                cite(LA_MOH, "Table 5 Treatment by antibiotics (p. 50)"),
                cite(INSARAG, "§5 Medications — antibiotics"),
                cite(ICMR_AMR, "Ch. 14 Table 14.1 — gentamicin (dose modification with CrCl)"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Antibiotic duration: sources differ",
            points = listOf(
                "GAIS/WSES 2024: cefazolin as 3 doses in total (Fig. 1); prophylaxis beyond 24 h is not indicated in gunshot-related fractures.",
                "EAST 2011: type III — continue for 72 h after injury or no more than 24 h after soft-tissue coverage; once-daily aminoglycoside is safe for types II and III.",
                "EAST 2011, citing its original guideline: types I and II — stop 24 h after successful wound closure.",
                "ICMR STW 2024: ‘Duration — 3 days after wound closure’, listed with cefuroxime 3 doses 8 h apart.",
                "ICMR 2019 (surgical prophylaxis in general): no longer than 24 h after surgery; beyond 48 h is ineffective and increases resistance and C. difficile infection.",
            ),
            citations = listOf(
                cite(ABX_2024, "Fig. 1; Open fractures — recommendations"),
                cite(EAST_OPENFX, "Recommendations — Level II; Scientific foundation"),
                cite(ICMR_OPENFX, "Antibiotic — which, when and for how long?"),
                cite(ICMR_AMR, "Ch. 10 §10.3.10 Continue no longer than 24 hours postoperatively"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Wound care, splinting and tetanus",
            timing = "Before formal debridement",
            points = listOf(
                "BOAST 2017 and ICMR STW 2024: handle the wound only to remove gross contamination and to photograph it; no ‘mini-washouts’ outside theatre. ICMR: do not debride or suture in the ED.",
                "BOAST 2017 and NICE NG37: saline-soaked gauze covered with an occlusive layer. NICE NG37: do not irrigate long-bone, hindfoot or midfoot open fractures before wound excision.",
                "MoHFW 2025: remove gross contamination by irrigating with normal saline. ICMR STW 2024: gentle saline wash. WHO BEC: irrigate well, dress and splint.",
                "BOAST 2017 and NICE NG37: photograph the wound when first exposed and before debridement; keep photographs in the patient's records.",
                "BOAST 2017: realign and splint the limb. NICE NG37 (pre-hospital): traction splint or the other leg above the knee; vacuum splint for other leg long-bone fractures.",
                "MoHFW 2025 and ICMR STW 2024: give tetanus prophylaxis. CDC: compound fractures are tetanus-prone wounds. See the Tetanus prophylaxis protocol.",
            ),
            citations = listOf(
                cite(BOAST_OPENFX, "Standards 5, 8, 10"),
                cite(ICMR_OPENFX, "Initial emergency treatment; Surgical wound decontamination"),
                cite(NG37, "Rec 1.1.8–1.1.9, 1.1.11, 1.2.20–1.2.21, 1.3.5–1.3.6"),
                cite(LA_MOH, "Ch. 10 Open fracture and open joint injuries (p. 49)"),
                cite(LA_WHO, "Quick cards — Management of specific conditions"),
                cite(LA_CDC_TT, "Wound assessment — dirty or major wounds"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Bleeding, neurovascular status and arterial injury",
            timing = "Control haemorrhage immediately; revascularisation started within 1 h of arrival (BOAST 2026)",
            points = listOf(
                "Bleeding — BOAST 2026: control at once by direct pressure, wound packing or a tourniquet applied as distally as possible; no blind clamping; unstable with uncontrolled bleeding — resuscitate and operate together.",
                "MoHFW 2025, stepwise: manual pressure, pressure dressing, occlude the proximal artery, manual tourniquet, then realign, reduce and immobilise. ICMR STW 2024: local pressure; if it does not stop, pack the wound. Tourniquets: see the <C> step of Major trauma.",
                "BOAST 2017: document vascular and neurological status; repeat after reduction or splinting. NICE NG37: record both limbs — nerves, MRC motor grade, pulses — and time each check.",
                "NICE NG37: diagnose vascular injury by hard signs; do not rely on capillary return or a Doppler signal to exclude it.",
                "MoHFW 2025: absent or feeble distal pulse suggests vascular compromise, as does ankle–brachial index <0.9; immobilise gently, reduce, consult a surgeon immediately.",
                "BOAST 2026: realign and splint a pulseless deformed limb urgently and re-examine; if the pulse still differs from the other side, suspect arterial injury — consultant input is mandatory.",
                "BOAST 2026: CT angiogram concurrently with whole-body CT; start revascularisation within 1 h of arrival; temporary shunt recommended. NICE NG37: do not delay it for angiography.",
                "NICE NG37: hard signs persisting after realignment — immediate surgical exploration. BOAST 2026: always consider fasciotomy after revascularisation (see Compartment syndrome).",
            ),
            checklistTitle = "Hard signs of vascular injury (NICE NG37)",
            checklist = listOf(
                "No palpable pulse",
                "Continued blood loss",
                "Expanding haematoma",
            ),
            citations = listOf(
                cite(BOAST_OPENFX, "Standards 4, 6"),
                cite(NG37, "Rec 1.2.1–1.2.5, 1.3.7"),
                cite(LA_MOH, "Ch. 10 Major arterial haemorrhage (p. 48); Physical examination; Vascular injuries (p. 49)"),
                cite(BOAST_ARTERIAL, "Standards 2–5, 7, 8, 10, 11, 15; footnote ii"),
                cite(ICMR_OPENFX, "Initial emergency treatment — obvious bleed"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Referral, debridement and soft-tissue cover",
            timing = "Debridement immediately if highly contaminated or vascular compromise; otherwise within 12–24 h (BOAST 2017; NICE NG37)",
            points = listOf(
                "BOAST 2017 and NICE NG37: long-bone, hindfoot or midfoot open fractures go to an orthoplastic centre; orthopaedic and plastic consultants plan debridement, fixation and cover together.",
                "ICMR STW 2024: after splintage, analgesia and IV fluids at the primary centre — refer Gustilo I–IIIA to a secondary centre and IIIB or IIIC to a tertiary centre.",
                "BOAST 2017: debride immediately for agricultural, aquatic or sewage contamination or vascular compromise; within 12 h for other solitary high-energy and 24 h for low-energy fractures.",
                "NICE NG37: within 12 h for high-energy (likely IIIA or IIIB) fractures that are not highly contaminated; within 24 h for all other open fractures.",
                "BOAST 2017 and NICE NG37: definitive soft-tissue cover within 72 h if not done at debridement; internal fixation only with immediate definitive cover.",
                "ICMR STW 2024 (surgical decontamination): low-to-medium pressure saline irrigation — 3 L type I, 6 L type II, 9 L type III; send cultures; definitive cover aimed within 72 h.",
            ),
            citations = listOf(
                cite(BOAST_OPENFX, "Standards 1, 11, 12, 14, 15"),
                cite(NG37, "Rec 1.1.12, 1.2.27–1.2.30"),
                cite(ICMR_OPENFX, "Referral; Surgical wound decontamination; Wound closure"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Washing the wound differs: NICE NG37 advises against irrigating long-bone, hindfoot or midfoot open fractures before wound excision, and BOAST 2017 limits handling to removing gross contamination; " +
                "MoHFW 2025 advises irrigating with normal saline, ICMR STW 2024 a gentle saline wash, and WHO BEC to irrigate well.",
            listOf(
                cite(NG37, "Rec 1.1.8, 1.2.20"), cite(BOAST_OPENFX, "Standard 8"), cite(LA_MOH, "Ch. 10 (p. 49)"),
                cite(ICMR_OPENFX, "Initial emergency treatment"), cite(LA_WHO, "Quick cards — Management of specific conditions"),
            ),
        ),
        ProtocolNote(
            "Agents differ: ICMR STW 2024 uses cefuroxime; MoHFW 2025 and GAIS/WSES 2024 use cefazolin. For heavy contamination ICMR STW adds metronidazole, MoHFW 2025 uses piperacillin–tazobactam, and EAST 2011 adds high-dose penicillin.",
            listOf(cite(ICMR_OPENFX, "Antibiotic box"), cite(LA_MOH, "Table 5 (p. 50)"), cite(ABX_2024, "Fig. 1"), cite(EAST_OPENFX, "Recommendations — Level I")),
        ),
        ProtocolNote(
            "ICMR 2019 classes open, fresh, accidental wounds as contaminated and lists cefuroxime + metronidazole, or metronidazole + an aminoglycoside or fluoroquinolone; " +
                "EAST 2011 reports that fluoroquinolones may impair fracture healing and raise infection rates in type III open fractures.",
            listOf(cite(ICMR_AMR, "Table 10.1 — Class III/Contaminated"), cite(EAST_OPENFX, "Recommendations — Level I")),
        ),
        ProtocolNote(
            "Timing differs: BOAST 2017, NICE NG37 and ICMR STW 2024 aim for antibiotics within 1 h of injury; MoHFW 2025 says within 3 h, ‘as per ATLS protocol’. The ATLS manual is not used as a source here.",
            listOf(cite(BOAST_OPENFX, "Standard 2"), cite(NG37, "Rec 1.1.10"), cite(ICMR_OPENFX, "Antibiotic box"), cite(LA_MOH, "Ch. 10 (p. 49)")),
        ),
        ProtocolNote(
            "Children: GAIS/WSES 2024 gives adult doses only; MoHFW 2025 gives weight bands (cefazolin 1 g <50 kg) and a gentamicin loading dose of 2.5 mg/kg for children or <50 kg. " +
                "None of these sources gives a per-kg paediatric cefazolin, cefuroxime or clindamycin dose.",
            listOf(cite(ABX_2024, "Fig. 1"), cite(LA_MOH, "Table 5 (p. 50)"), cite(ICMR_OPENFX, "Antibiotic box")),
        ),
        ProtocolNote(
            "Amputation: NICE NG37 — emergency amputation for uncontrollable life-threatening bleeding, unacceptable risk to life or an unsalvageable limb; not decided on an injury-severity score; " +
                "delayed primary amputation within 72 h. BOAST 2026: an early amputation decision must be made by two consultants and documented.",
            listOf(cite(NG37, "Rec 1.2.23–1.2.26"), cite(BOAST_OPENFX, "Standards 16–17"), cite(BOAST_ARTERIAL, "Standard 14")),
        ),
    ),
    citations = listOf(
        cite(BOAST_OPENFX), cite(NG37), cite(ICMR_OPENFX), cite(LA_MOH), cite(EAST_OPENFX), cite(ABX_2024), cite(ICMR_AMR),
        cite(BOAST_ARTERIAL), cite(LA_WHO), cite(LA_CDC_TT), cite(INSARAG),
    ),
)

// ================================================================================================ Compartment syndrome and crush
private val compartmentSyndromeCrush = Protocol(
    id = "compartment-syndrome-crush",
    category = ProtocolCategory.Trauma,
    title = "Compartment syndrome and crush injury",
    summary = "Acute compartment syndrome — early versus late signs, immediate actions, pressure thresholds and fasciotomy — and crush " +
        "syndrome after entrapment: fluids before and after extrication, potassium, rhabdomyolysis, dialysis — attributed to BOAST 2025, " +
        "AAOS 2025, the Association of Anaesthetists, MoHFW 2025, ICMR, INSARAG 2023 and ISN Renal Disaster Relief Task Force authors.",
    keywords = listOf(
        "compartment syndrome", "acute compartment syndrome", "fasciotomy", "compartment pressure", "delta pressure",
        "tight plaster", "tight cast", "plaster tight", "POP", "Volkmann", "5 Ps", "crush injury", "crush syndrome",
        "traumatic rhabdomyolysis", "rhabdomyolysis", "myoglobinuria", "dark urine", "cola urine", "CK", "CPK", "CK-MM",
        "hyperkalaemia", "hyperkalemia", "reperfusion", "building collapse", "collapsed structure", "earthquake", "landslide",
        "entrapment", "extrication", "trapped under rubble", "USAR", "NDRF", "bhukamp", "imarat girna", "malbe mein dabna",
        "malbe vich dabb gaya", "dabb jaana", "plaster kasa hai",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Who is at risk",
            timing = "Assess on presentation, then hourly while at risk (BOAST 2025)",
            points = listOf(
                "BOAST 2025: assess for compartment syndrome routinely after limb injury, extremity surgery or prolonged procedures; hourly with documented findings while at risk.",
                "BOAST 2025: record times, mechanism, consciousness, neurovascular status, pain and response to analgesia, and the dose and rate of opioids.",
                "NICE NG37: tibial fractures — stay alert for 48 h after injury or fixation; consider continuous pressure monitoring if signs cannot be assessed (unconscious, nerve block).",
                "Regional anaesthesia — Association of Anaesthetists 2021 (lower leg trauma): avoid neuraxial or peripheral blocks that are dense and last well beyond surgery; lower-concentration peripheral nerve blocks without adjuncts are not linked to delayed diagnosis if surveillance is effective.",
                "BOAST 2025: regional anaesthesia by joint, documented decision (patient, anaesthetist, surgeon) with an agreed policy on who monitors after surgery — invasive monitoring or review by trained staff. AAOS 2025: after neuraxial anaesthesia, examine frequently and/or monitor pressure.",
                "INSARAG 2023: anyone entrapped in a structural collapse has some crush element until proven otherwise; crush syndrome may follow 1 h of compression, even by body weight alone.",
            ),
            citations = listOf(
                cite(BOAST_CS, "Standards 2–6 and footnote **"),
                cite(NG37, "Rec 1.2.7"),
                cite(AAOS_ACS, "Pain management effects on diagnosis (consensus)"),
                cite(AOA_ACS, "Recommendations; Pain relief after lower leg trauma"),
                cite(INSARAG, "§3 Clinical presentation; §4 General management strategy"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Recognise: early versus late features",
            timing = "Diagnose before muscle ischaemia — late signs mean established damage (Association of Anaesthetists 2021)",
            points = listOf(
                "BOAST 2025: key findings are pain out of proportion to the injury and pain on passive movement of the muscles of the involved compartment.",
                "Association of Anaesthetists 2021: individual signs have low sensitivity but high negative predictive value; severe pain is not always present.",
                "MoHFW 2025: suspect with any of — disproportionate pain, pain on passive stretch, tense swelling of the compartment, paraesthesia distal to it.",
                "ICMR STW 2024 (open fractures) lists pain on passive stretching, pallor, paraesthesia, paralysis and pulselessness, with urgent referral for fasciotomy.",
                "Association of Anaesthetists 2021: in children, look for anxiety, agitation and a rising analgesic requirement (‘three As’).",
            ),
            checklistTitle = "Association of Anaesthetists 2021",
            checklist = listOf(
                "Early: pain out of proportion to the injury (often the earliest sign)",
                "Early: pain on passive stretch of the compartment's muscles",
                "Early: swollen, increasingly firm compartment",
                "Late: loss of pulse, paralysis, pallor, coolness — significant vascular and viability compromise",
            ),
            citations = listOf(
                cite(BOAST_CS, "Background and justification"),
                cite(AOA_ACS, "Diagnosis; Special circumstances: children; Pain relief after lower leg trauma"),
                cite(LA_MOH, "Ch. 10 Compartment syndrome (pp. 49–50)"),
                cite(ICMR_OPENFX, "Look for compartment syndrome"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Immediate actions",
            timing = "Re-evaluate within 30 min (BOAST 2025)",
            points = listOf(
                "BOAST 2025: symptoms or signs — release circumferential dressings to expose the skin, elevate the limb and re-evaluate within 30 min.",
                "MoHFW 2025: remove all constrictive dressings, casts or splints and immediately obtain a surgical consultation for fasciotomy.",
                "ICMR STW 2024: if suspected, refer urgently to a higher centre for immediate fasciotomy.",
                "BOAST 2025: inconclusive or incomplete assessment — hourly examination, consider pressure measurement of all suspected compartments with blood pressure, senior surgical review.",
                "BOAST 2026: after revascularisation of an arterial injury, always consider fasciotomy; document any decision not to.",
            ),
            citations = listOf(
                cite(BOAST_CS, "Standards 7–8"),
                cite(LA_MOH, "Ch. 10 Compartment syndrome — management (pp. 49–50)"),
                cite(ICMR_OPENFX, "Look for compartment syndrome"),
                cite(BOAST_ARTERIAL, "Standard 15"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Compartment pressure: thresholds as sourced",
            points = listOf(
                "BOAST 2025: diastolic BP minus compartment pressure <30 mm Hg indicates increased risk; a consultant orthopaedic surgeon decides between decompression and monitoring.",
                "BOAST 2025: absolute compartment pressure >40 mm Hg — urgent surgical decompression should be considered.",
                "AAOS 2025 (moderate): pressure monitoring assists diagnosis; repeated or continuous monitoring with diastolic minus compartment pressure >30 mm Hg helps rule it out.",
                "AAOS 2025 (consensus): without a dependable examination, e.g. obtunded, repeat or continuously measure pressure until ACS is diagnosed or excluded.",
                "Association of Anaesthetists 2021: absolute ≥30 mm Hg alone gives up to 29% fasciotomies after tibial surgery; usually decompress within 10–30 mm Hg of diastolic plus another sign.",
                "Association of Anaesthetists 2021: children's normal leg pressures are higher (13–16 vs 0–10 mm Hg); the thresholds used are usually the same as in adults.",
            ),
            citations = listOf(
                cite(BOAST_CS, "Standard 8 footnote (***)"),
                cite(AAOS_ACS, "Pressure methods A–B (moderate); Physical exam — obtunded (consensus)"),
                cite(AOA_ACS, "Pressure threshold for fasciotomy; Special circumstances: children"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Fasciotomy",
            timing = "Immediately after diagnosis — NCEPOD category 1 (BOAST 2025)",
            points = listOf(
                "BOAST 2025: immediate surgical decompression — open all involved compartments and debride non-viable muscle, documenting each compartment.",
                "BOAST 2025: lower limb — two-incision, four-compartment decompression. AAOS 2025: complete decompression matters more than the technique.",
                "BOAST 2025: discuss with a plastic surgeon within 24 h; re-explore within 72 h or earlier. AAOS 2025 (limited): negative-pressure dressings may speed closure.",
                "BOAST 2025: delayed presentation or diagnosis carries high surgical risk — two consultants decide; non-operative care with renal assessment and protection is an option.",
                "AAOS 2025 (consensus): no fasciotomy with irreversible neuromuscular damage; stabilise any fracture without violating the compartment (external fixation or cast).",
                "AAOS 2025 (limited): long-bone fracture with ACS needing fasciotomy — operative fixation (external or internal) for initial stabilisation.",
            ),
            citations = listOf(
                cite(BOAST_CS, "Standards 9–12"),
                cite(AAOS_ACS, "Fasciotomy methods; Fasciotomy for late/missed ACS; Associated fracture; Wound management"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Crush: before and during extrication",
            timing = "Start fluids before the compressive force is released (INSARAG 2023; ISN RDRTF)",
            points = listOf(
                "INSARAG 2023: treat as soon as the patient is reached; plan release of the compressive force with rescuers, without unnecessary delay — deterioration can be rapid.",
                "ISN RDRTF (Sever 2023, from the 2012 ISN recommendations): IV access in any limb while trapped; isotonic saline 1 L/h, continued during extrication; if extrication >2 h, 0.5 L/h or less.",
                "INSARAG 2023: 1–1.5 L/h of warmed, potassium-free isotonic fluid for the first 2 h in adults; prepare boluses for release; less with low muscle mass or heart failure.",
                "Sever 2023: no IV access — intraosseous; last resort, subcutaneous fluid about 1 mL/min (up to 3 L/day); oral fluids if alert without abdominal injury.",
                "INSARAG 2023: literature and USAR experience support 8.4% sodium bicarbonate (e.g. 50 mL IV, average adult) before release; avoid NSAIDs; suxamethonium is contraindicated.",
                "INSARAG 2023 and Sever 2023: no tourniquet to prevent crush syndrome — only for otherwise uncontrollable life-threatening bleeding.",
            ),
            doses = listOf(
                StepDose(
                    "Children and infants — initial fluid bolus (INSARAG 2023)",
                    WeightDose(15.0, 20.0, unit = "mL", per = "IV bolus, warmed isotonic potassium-free fluid"),
                    note = "INSARAG gives this as an example (‘e.g. initial bolus 15–20 ml/kg’); no maximum stated, and no adult bolus volume is given " +
                        "(adults: 1–1.5 L/h for the first 2 h). Fluid infusions need constant re-evaluation",
                ),
            ),
            citations = listOf(
                cite(INSARAG, "§4 General management strategy; §5 Fluid therapy, Medications, Tourniquets"),
                cite(ISN_2023, "Interventions at the disaster field — fluid resuscitation; rescue of the victim; Fig. 1A"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Crush: after extrication and in hospital",
            timing = "Monitor for 6 h after starting fluids, then adjust (ISN RDRTF, Sever 2023 Fig. 1)",
            points = listOf(
                "ISN RDRTF (RMMJ 2011): if no fluid was given before rescue, isotonic saline 1,000 mL/h in adults (15–20 mL/kg/h in children).",
                "ISN RDRTF (Sever 2023, Fig. 1): about 3–6 L in total, reassess at 6 h; urine flowing — 3–6 L/day, >6 L/day if closely monitored; anuria — 0.5–1 L/day plus the previous day's presumed losses.",
                "MoHFW 2025: suspect crush syndrome with extensive limb damage plus AKI or amber urine; CK-MM >10,000 U/L; normal saline only, aiming for urine 200–300 mL/h.",
                "ISN RDRTF (RMMJ 2011): elderly — 4–6 L/day to avoid overload; third-spacing into crushed muscle means urine output can lag far behind fluid given.",
                "Sever 2023: on arrival check prior fluids and stop potassium-containing ones immediately; catheterise to measure urine output unless urethral injury is suspected.",
                "ICMR STW AKI 2026: measure CPK in suspected rhabdomyolysis. WHO BEC: crush injury — IV fluids, monitor urine output and watch for compartment syndrome.",
            ),
            doses = listOf(
                StepDose(
                    "Children — isotonic saline after extrication, none given before (ISN RDRTF)",
                    WeightDose(15.0, 20.0, unit = "mL", per = "per hour, IV isotonic saline"),
                    note = "No maximum stated. Adults 1,000 mL/h (RMMJ 2011) — above 50 kg, 20 mL/kg/h exceeds the adult rate; treating that rate as a ceiling " +
                        "is an inference, not an RMMJ statement. RMMJ: once the victim has received 6 L in total (no children's figure given), evaluate urine output and volume status",
                ),
            ),
            citations = listOf(
                cite(ISN_2011, "Prophylaxis and treatment"),
                cite(ISN_2023, "Fig. 1B; Meeting and evaluating the patient; Maintaining fluid–electrolyte and acid–base balance"),
                cite(LA_MOH, "Ch. 10 Crush syndrome (p. 48)"),
                cite(LA_ICMR_AKI, "Laboratory investigation for specific cause"),
                cite(LA_WHO, "Quick cards — Management of specific conditions"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Hyperkalaemia, acidosis and dialysis",
            timing = "Potassium and ECG early — hyperkalaemia can occur without AKI (Sever 2023)",
            points = listOf(
                "Sever 2023: hyperkalaemia is the most critical and frequent laboratory finding in crush victims — monitor potassium and ECG. INSARAG 2023: peaked T, wide QRS, bradycardia.",
                "Sever 2023 (ISN): potassium >7 mmol/L is an emergency; a rapid rise needs treatment at lower levels; calcium, insulin–glucose, bicarbonate and β2-agonists act briefly — dialysis works best.",
                "ICMR STW AKI 2026: 10 mL 10% calcium gluconate slow IV over 2–5 min under ECG (up to 3 times), inhaled salbutamol 10 mg, insulin 10 U in 50 mL of 50% or 100 mL of 25% dextrose. See the Hyperkalaemia protocol.",
                "Sever 2023 (ISN): IV bicarbonate only if serum bicarbonate <10 mEq/L — alkali can trigger hypocalcaemia and tetany; for more severe acidosis, bicarbonate plus dialysis.",
                "INSARAG 2023: avoid nephrotoxic drugs (NSAIDs, aminoglycosides); furosemide is discouraged; evidence for mannitol is inconclusive.",
            ),
            checklistTitle = "Dialysis indications in crush (ISN, as given by Sever 2023)",
            checklist = listOf(
                "Potassium ≥6.5 mmol/L",
                "Blood pH ≤7.1",
                "BUN ≥100 mg/dL or serum creatinine ≥8 mg/dL",
                "Volume overload, pericarditis, bleeding or unexplained altered mental status",
                "Consider more liberal indications in crush, anticipating complications",
            ),
            citations = listOf(
                cite(ISN_2023, "Meeting and evaluating the patient; Treatment of hyperkalemia; Maintaining fluid–electrolyte and acid–base balance; Application of dialysis"),
                cite(INSARAG, "§5 Hyperkalaemia indicators; Medications; Notes regarding medications"),
                cite(LA_ICMR_AKI, "Treatment of hyperkalemia; Indications for dialysis"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "Crush in disasters: fasciotomy, amputation, infection",
            points = listOf(
                "INSARAG 2023: fasciotomy in the austere collapsed-structure environment is discouraged because of bleeding and sepsis risk.",
                "Sever 2023: not routine in disasters — only when clearly indicated (absent distal pulses from compression, disproportionate pain, radical debridement) or compartment pressures of 30–40 mm Hg, especially if not falling over 6 h.",
                "Sever 2023: if indicated, operate as soon as possible — less harm within 12 h of muscle swelling; mannitol is never an alternative to fasciotomy.",
                "Sever 2023: on-site amputation to free a trapped limb only on very strict criteria — as distal as possible, guillotine, with a tourniquet above.",
                "INSARAG 2023: broad-spectrum antibiotics with anaerobic cover for contaminated crush wounds, avoiding aminoglycosides. Tetanus: see the Tetanus prophylaxis protocol.",
            ),
            citations = listOf(
                cite(INSARAG, "§5 Compartment syndrome; Medications — antibiotics"),
                cite(ISN_2023, "Fasciotomies; Amputations; Rescue of the victim — early interventions"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Limb position: BOAST 2025 says to release circumferential dressings and elevate the limb; the archived 2014 BOAST specified elevation to heart level and keeping blood pressure normal. " +
                "The 2025 revision does not state a level.",
            listOf(cite(BOAST_CS, "Standard 7"), cite(BOAST_CS_2014, "Standard 6")),
        ),
        ProtocolNote(
            "Pressure thresholds differ: BOAST 2025 — diastolic minus compartment pressure <30 mm Hg means increased risk and absolute >40 mm Hg prompts urgent decompression; " +
                "AAOS 2025 — a difference >30 mm Hg helps rule ACS out; Sever 2023 (disaster crush) — 30–40 mm Hg, especially without a falling trend over 6 h.",
            listOf(cite(BOAST_CS, "Standard 8 footnote"), cite(AAOS_ACS, "Pressure methods B"), cite(ISN_2023, "Fasciotomies")),
        ),
        ProtocolNote(
            "Crush fluids differ: MoHFW 2025 — normal saline only to urine 200–300 mL/h; ISN RDRTF — isotonic saline 1 L/h before and during extrication, then 3–6 L/day or more by urine output; " +
                "INSARAG 2023 — 1–1.5 L/h of warmed potassium-free fluid for the first 2 h. INSARAG notes that no fluid regimen is supported by strong evidence and that over-hydration is possible.",
            listOf(cite(LA_MOH, "Ch. 10 Crush syndrome (p. 48)"), cite(ISN_2023, "Fig. 1"), cite(INSARAG, "§5 Fluid therapy")),
        ),
        ProtocolNote(
            "Alkalinisation and mannitol are contested: INSARAG 2023 suggests 8.4% bicarbonate before release and calls the evidence for mannitol inconclusive; the ISN authors' 2011 review describes alkaline " +
                "solutions and a mannitol test dose (not in anuria), while Sever 2023 restricts IV bicarbonate to serum bicarbonate <10 mEq/L. Rhabdomyolysis from electrical injury: see Electrical injury and lightning.",
            listOf(cite(INSARAG, "§5 Medications"), cite(ISN_2011, "Prophylaxis and treatment"), cite(ISN_2023, "Maintaining fluid–electrolyte and acid–base balance")),
        ),
        ProtocolNote(
            "Fasciotomy after crush is contested: BOAST 2025 and MoHFW 2025 call for immediate decompression once compartment syndrome is diagnosed after injury; INSARAG 2023 discourages fasciotomy " +
                "in the collapsed-structure environment, and Sever 2023 reports more sepsis after fasciotomy in Marmara earthquake victims and advises objective criteria.",
            listOf(cite(BOAST_CS, "Standard 9"), cite(LA_MOH, "Ch. 10 (pp. 49–50)"), cite(INSARAG, "§5 Compartment syndrome"), cite(ISN_2023, "Fasciotomies")),
        ),
        ProtocolNote(
            "Sources: the ISN/ERBP 2012 crush recommendations (Nephrol Dial Transplant 2012;27 Suppl 1) could not be accessed; their fluid algorithm and dialysis criteria are reported as reproduced by " +
                "ISN Renal Disaster Relief Task Force authors (Sever 2023, Fig. 1 adapted with permission; RMMJ 2011). The ATLS manual is not used as a source.",
            listOf(cite(ISN_2023, "Fig. 1 legend; reference 2"), cite(ISN_2011)),
        ),
    ),
    citations = listOf(
        cite(BOAST_CS), cite(AAOS_ACS), cite(AOA_ACS), cite(NG37), cite(LA_MOH), cite(ICMR_OPENFX), cite(INSARAG), cite(ISN_2011),
        cite(ISN_2023), cite(LA_ICMR_AKI), cite(BOAST_ARTERIAL), cite(LA_WHO),
    ),
)

internal val limbAbdomenProtocols: List<Protocol> = listOf(
    abdominalPelvicTrauma,
    openFractures,
    compartmentSyndromeCrush,
)
