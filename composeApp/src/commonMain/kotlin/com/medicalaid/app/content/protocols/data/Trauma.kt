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
 * Major trauma, traumatic haemorrhage, chest trauma and traumatic cardiac arrest. Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them (checked against the full texts of
 *   MoHFW 2025, ICMR STW 2024, WHO BEC 2018, NICE NG39, the European guideline 2023, ERC 2025 Special Circumstances,
 *   RCUK/ERC 2025 First Aid, EAST 2012 and 2015, the WTA 2024 algorithm and the Kcentra, ketorolac and naloxone labels;
 *   EAST 2016/2017/2021/2022/2023 and CRASH-2 against their published abstracts; 22 November 2026).
 * - Every point is attributed ("MoHFW 2025: …", "ICMR STW 2024: …", "WHO BEC: …", "NICE NG39: …", "European 2023: …",
 *   "ERC 2025: …", "RCUK/ERC 2025: …", "EAST 2015: …", "WTA 2024: …").
 * - The ATLS manual is proprietary and is not used as a source. MoHFW 2025 Table 9 summarises ATLS 11th-edition changes;
 *   only its non-numerical statements are reported, attributed to MoHFW.
 * - Locators: MoHFW 2025 — chapter and printed page; NICE — recommendation number; European 2023 — recommendation (R)
 *   number; ERC 2025 — section heading or figure; EAST — recommendation level or abstract section.
 * - Existing sources reused by id: em-resus-erc-special-2025 (Resuscitation.kt), em-resus-aha-special-2025 and
 *   tx-dt-fda-naloxone-inj (DrugTox.kt), ob-mat-east-2025 (MaternalCollapse.kt) and mohfw-td (Sources.kt).
 */

internal val traumaSources: List<Source> = listOf(
    Source(
        "tr-mohfw-stg-trauma-2025", Authority.MoHFW, "Dte.GHS Trauma and Burn Division — Standard Treatment Guidelines",
        "Standard Treatment Guidelines for Management of Trauma: A Guidance Document for Clinical Management of Trauma in India",
        "2025",
        "https://www.dghs.mohfw.gov.in/uploads/assets/SwQ7pJMiJepMtqfUQNUJrGOROd584XHIgipqf7gI.pdf",
        status = "Ministerial message dated 26 March 2025; contributors include AIIMS JPNATC faculty; listed on the DGHS technical guidelines page",
    ),
    Source(
        "tr-icmr-stw-chest-2024", Authority.ICMR, "Standard Treatment Workflow (CTVS, Vol IV)",
        "Chest Trauma (ICD-10 S29.9)", "2024",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726643578_3_chest_trauma.pdf",
        status = "July 2024",
    ),
    Source(
        "tr-who-bec-2018", Authority.WHO, "WHO/ICRC training course — participant workbook and quick cards",
        "Basic emergency care: approach to the acutely ill and injured", "2018",
        "https://www.who.int/publications/i/item/basic-emergency-care-approach-to-the-acutely-ill-and-injured",
        status = "Co-published with the ICRC; ISBN 978-92-4-151308-1; written for first-contact providers in low-resource settings",
    ),
    Source(
        "tr-nice-ng39", Authority.NICE, "Guideline NG39",
        "Major trauma: assessment and initial management", "2016",
        "https://www.nice.org.uk/guidance/ng39",
        status = "Published 17 February 2016; minor changes November 2020, August 2023 and May 2025",
    ),
    Source(
        "tr-euro-bleeding-2023", Authority.Paper,
        "Pan-European guideline, Task Force for Advanced Bleeding Care in Trauma (Crit Care 2023;27:80)",
        "The European guideline on management of major bleeding and coagulopathy following trauma: sixth edition", "2023",
        "https://doi.org/10.1186/s13054-023-04327-7",
        status = "Rossaint R et al.; no later edition found (November 2026)",
    ),
    Source(
        "tr-crash2-timing-2011", Authority.Paper, "Exploratory analysis of an RCT (Lancet 2011;377(9771):1096–1101)",
        "The importance of early treatment with tranexamic acid in bleeding trauma patients: an exploratory analysis of the CRASH-2 randomised controlled trial",
        "2011",
        "https://doi.org/10.1016/S0140-6736(11)60278-X",
        status = "CRASH-2 collaborators; abstract reviewed",
    ),
    Source(
        "tr-rcuk-first-aid-2025", Authority.RCUK, "2025 Resuscitation Guidelines — First aid",
        "First Aid Guidelines", "2025",
        "https://www.resus.org.uk/library/2025-resuscitation-guidelines/first-aid-guidelines",
        status = "UK publication of the ERC Guidelines 2025 First Aid (Resuscitation 2025;215 Suppl 1:110752); published 27 October 2025",
    ),
    Source(
        "tr-east-edt-2015", Authority.EAST, "Practice management guideline (J Trauma Acute Care Surg 2015;79(1):159–173)",
        "An evidence-based approach to patient selection for emergency department thoracotomy: A practice management guideline from the Eastern Association for the Surgery of Trauma",
        "2015",
        "https://doi.org/10.1097/TA.0000000000000648",
    ),
    Source(
        "tr-wta-ert-2024", Authority.Paper, "Western Trauma Association clinical decisions algorithm (J Trauma Acute Care Surg 2024;97(6):961–968)",
        "Adult emergency resuscitative thoracotomy: A Western Trauma Association clinical decisions algorithm", "2024",
        "https://doi.org/10.1097/TA.0000000000004462",
        status = "Tesoriero R et al.; published online 25 October 2024; replaces the 2012 WTA resuscitative thoracotomy algorithm; full text, algorithm (Fig. 1) and procedure guide (Fig. 2) reviewed",
    ),
    Source(
        "tr-east-bci-2012", Authority.EAST, "Practice management guideline (J Trauma Acute Care Surg 2012;73(5 Suppl 4):S301–S306)",
        "Screening for blunt cardiac injury: an Eastern Association for the Surgery of Trauma practice management guideline", "2012",
        "https://doi.org/10.1097/TA.0b013e318270193a",
    ),
    Source(
        "tr-east-pain-2016", Authority.EAST, "EAST/Trauma Anesthesiology Society guideline (J Trauma Acute Care Surg 2016;81(5):936–951)",
        "Pain management for blunt thoracic trauma: A joint practice management guideline from the Eastern Association for the Surgery of Trauma and Trauma Anesthesiology Society",
        "2016",
        "https://doi.org/10.1097/TA.0000000000001209",
        status = "Abstract reviewed",
    ),
    Source(
        "tr-east-cwis-ribs-2023", Authority.EAST, "EAST/Chest Wall Injury Society guideline (J Trauma Acute Care Surg 2023;94(3):398–407)",
        "Non-surgical management and analgesia strategies for older adults with multiple rib fractures: A systematic review, meta-analysis, and joint practice management guideline from the Eastern Association for the Surgery of Trauma and the Chest Wall Injury Society",
        "2023",
        "https://doi.org/10.1097/TA.0000000000003830",
        status = "Erratum J Trauma Acute Care Surg 2023;95(3):e23; abstract reviewed",
    ),
    Source(
        "tr-east-ribfix-2017", Authority.EAST, "Practice management guideline (J Trauma Acute Care Surg 2017;82(3):618–626)",
        "Operative fixation of rib fractures after blunt trauma: A practice management guideline from the Eastern Association for the Surgery of Trauma",
        "2017",
        "https://doi.org/10.1097/TA.0000000000001350",
        status = "Abstract reviewed",
    ),
    Source(
        "tr-east-htx-2021", Authority.EAST, "Practice management guideline (Am J Surg 2021;221(5):873–884)",
        "Management of simple and retained hemothorax: A practice management guideline from the Eastern Association for the Surgery of Trauma",
        "2021",
        "https://doi.org/10.1016/j.amjsurg.2020.11.032",
        status = "Abstract reviewed",
    ),
    Source(
        "tr-east-tt-abx-2022", Authority.EAST, "Practice management guideline (Trauma Surg Acute Care Open 2022;7(1):e000886)",
        "Antibiotic prophylaxis for tube thoracostomy placement in trauma: a practice management guideline from the Eastern Association for the Surgery of Trauma",
        "2022",
        "https://doi.org/10.1136/tsaco-2022-000886",
        status = "Abstract reviewed",
    ),
    Source(
        "tr-fda-kcentra", Authority.FDA, "Prescribing information (DailyMed)",
        "Kcentra (prothrombin complex concentrate, human)", "2023",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=eee1afb8-324c-42e4-8bf0-f0c9da5e6d42",
        status = "US label, version published 17 May 2023; cited for INR-banded dose caps, rate, repeat dosing, contraindications and the boxed warning only",
    ),
    Source(
        "tr-fda-ketorolac-inj", Authority.FDA, "Prescribing information (DailyMed)",
        "Ketorolac tromethamine injection, solution (Anthea Pharma)", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=7a7c8579-d423-4699-9435-69c4d127c654",
        status = "Label revised February 2026; cited for the boxed warning (bleeding and renal risk) and dosage only",
    ),
    Source(
        "tr-fda-andexxa-2025", Authority.FDA, "FDA Safety Communication",
        "Update on the Safety of Andexxa by AstraZeneca", "2025",
        "https://www.fda.gov/safety/medical-product-safety-information/update-safety-andexxa-astrazeneca-fda-safety-communication",
        status = "Issued 18 December 2025; US commercial sales ended 22 December 2025",
    ),
)

private const val MOH = "tr-mohfw-stg-trauma-2025"
private const val ICMR = "tr-icmr-stw-chest-2024"
private const val WHO = "tr-who-bec-2018"
private const val NICE = "tr-nice-ng39"
private const val EU = "tr-euro-bleeding-2023"
private const val CRASH2 = "tr-crash2-timing-2011"
private const val RCUK_FA = "tr-rcuk-first-aid-2025"
private const val EAST_EDT = "tr-east-edt-2015"
private const val WTA = "tr-wta-ert-2024"
private const val EAST_BCI = "tr-east-bci-2012"
private const val EAST_PAIN = "tr-east-pain-2016"
private const val EAST_RIBS_OLD = "tr-east-cwis-ribs-2023"
private const val EAST_RIBFIX = "tr-east-ribfix-2017"
private const val EAST_HTX = "tr-east-htx-2021"
private const val EAST_ABX = "tr-east-tt-abx-2022"
private const val KCENTRA = "tr-fda-kcentra"
private const val ANDEXXA = "tr-fda-andexxa-2025"
private const val KETOROLAC = "tr-fda-ketorolac-inj"

/** Defined in DrugTox.kt — US naloxone hydrochloride injection label (Fresenius Kabi). */
private const val NALOX_INJ = "tx-dt-fda-naloxone-inj"

/** Defined in Resuscitation.kt — ERC Guidelines 2025 Special Circumstances in Resuscitation. */
private const val ERC = "em-resus-erc-special-2025"

/** Defined in DrugTox.kt — AHA 2025 Part 10, Adult and Pediatric Special Circumstances of Resuscitation. */
private const val AHA_SPECIAL = "em-resus-aha-special-2025"

/** Defined in MaternalCollapse.kt — EAST 2025 trauma in pregnancy guideline. */
private const val EAST_PREG = "ob-mat-east-2025"

/** Defined in Sources.kt — MoHFW UIP Td vaccine operational guidelines (2018). */
private const val MOH_TD = "mohfw-td"

// ================================================================================================ Major trauma
private val majorTrauma = Protocol(
    id = "major-trauma",
    category = ProtocolCategory.Trauma,
    title = "Major trauma: initial assessment",
    summary = "Triage, <C>ABCDE primary survey with simultaneous resuscitation, imaging, analgesia, secondary survey and " +
        "transfer — attributed to MoHFW 2025, ICMR STW, WHO BEC, NICE NG39, the European guideline 2023 and RCUK/ERC 2025.",
    keywords = listOf(
        "major trauma", "polytrauma", "multiple injuries", "RTA", "road traffic accident", "RTI", "road traffic injury",
        "accident", "accident case", "MLC", "medico-legal case", "fall from height", "assault", "gunshot", "stab",
        "primary survey", "secondary survey", "ABCDE", "cABCDE", "<C>ABCDE", "xABCDE", "eFAST", "E-FAST", "FAST",
        "trauma call", "trauma team", "golden hour", "red area", "triage", "ATLS", "whole-body CT", "WBCT", "pan scan",
        "tourniquet", "haemostatic dressing", "QuikClot", "Celox", "pelvic binder", "log roll", "AMPLE",
        "durghatna", "sadak durghatna", "haadsa", "hadsa", "chot", "gambhir chot", "satt",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Prepare, triage and activate the team",
            timing = "On arrival",
            points = listOf(
                "NICE NG39: a senior nurse or trauma team leader receives the structured pre-alert and sets the trauma team response by written local guidelines.",
                "MoHFW 2025: receive and shift the patient maintaining cervical spine stabilisation; triage to red, yellow or green areas.",
                "MoHFW 2025: red — immediate care; yellow within 30 min and green within 1 h of arrival; red patients to be admitted or moved within 1 h.",
                "MoHFW 2025: Level-2 and Level-3 facilities with one doctor resuscitate sequentially, A→B→C→D→E.",
                "WHO BEC: high-risk mechanisms include pedestrians or cyclists hit by a vehicle, motorcycle or unrestrained crashes, falls >3 m, gunshot or stabbing.",
                "MoHFW 2025: medico-legal documentation (MLC), sample preservation and handover are part of every red-area resuscitation.",
            ),
            checklistTitle = "MoHFW 2025 — red triage (any one)",
            checklist = listOf(
                "Compromised airway, gurgling sounds or laboured breathing",
                "SpO₂ <90%",
                "Systolic BP <90 mm Hg",
                "Pulse >100/min",
                "Respiratory rate >24/min",
                "GCS ≤12",
                "Gunshot; penetrating injury to thorax, abdomen or neck",
                "Major crush or vascular injury",
                "Pregnancy",
                "Open long-bone fracture or pelvic fracture",
                "Flail chest, sucking chest wound, subcutaneous emphysema or pneumothorax",
                "Dangerous mechanism of injury",
                "Traumatic amputation above knee or elbow",
            ),
            citations = listOf(
                cite(NICE, "Rec 1.8.3–1.8.5"),
                cite(MOH, "Ch. 1 Patient flow and triage criteria (pp. 6–7); disposition (p. 12); vertical resuscitation (p. 10)"),
                cite(WHO, "Quick cards — High-risk mechanisms and injuries"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "<C> Catastrophic haemorrhage",
            timing = "First — before airway in exsanguinating bleeding (<C>ABCDE)",
            points = listOf(
                "NICE NG39 records catastrophic haemorrhage before airway (<C>ABCDE). European 2023: minimise the time from injury to bleeding control.",
                "NICE NG39: simple dressings with direct pressure. RCUK/ERC 2025 first aid: a standard or ideally haemostatic dressing, packed into the wound if needed.",
                "NICE NG39: major limb trauma — tourniquet if direct pressure fails to control life-threatening bleeding. European 2023: adjunct tourniquet before surgery.",
                "RCUK/ERC 2025: tourniquet 5–7 cm above the injury, not over a joint; tighten until bleeding stops; record the time; add a second above if needed.",
                "WHO BEC: tourniquet only for life-threatening bleeding; leave penetrating objects in place and stabilise them. MoHFW 2025: note the tourniquet time.",
                "NICE NG39: suspected pelvic bleeding after blunt high-energy trauma — purpose-made pelvic binder. See the Traumatic haemorrhage protocol.",
            ),
            citations = listOf(
                cite(NICE, "Rec 1.5.1–1.5.3, 1.8.1"),
                cite(EU, "R1–R2; R5 rationale (<C>ABCDE)"),
                cite(RCUK_FA, "Control of life-threatening bleeding"),
                cite(WHO, "ABCDE module — Severe bleeding; Quick cards — Management of specific conditions"),
                cite(MOH, "Ch. 15 Patient transfer — Circulation (p. 67)"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Airway with cervical spine protection",
            timing = "Primary survey",
            points = listOf(
                "MoHFW 2025: jaw thrust or chin lift with manual in-line stabilisation; suction blood and secretions; oropharyngeal airway if unconscious without a gag reflex.",
                "MoHFW 2025: no nasopharyngeal airway if a cribriform plate fracture is possible; pass gastric tubes orally rather than nasally.",
                "European 2023: intubate or manage the airway without delay for obstruction, GCS ≤8, hypoventilation or hypoxaemia; avoid hypoxaemia and normoventilate.",
                "NICE NG39: drug-assisted RSI is the definitive airway; if it fails, basic manoeuvres, adjuncts or a supraglottic device until a surgical airway.",
                "MoHFW 2025: preoxygenate with 100% oxygen; confirm the tube by auscultation and exhaled CO₂; have a failed-airway plan, involving anaesthesia early.",
                "Cervical spine clearance, collars and spinal motion restriction: see the spinal injury protocol.",
            ),
            checklistTitle = "Surgical airway indications (MoHFW 2025)",
            checklist = listOf(
                "Glottic oedema",
                "Laryngeal fracture",
                "Severe oropharyngeal haemorrhage",
                "Inability to place an endotracheal tube",
            ),
            citations = listOf(
                cite(MOH, "Ch. 2 Airway (p. 14); adjuncts — gastric catheters (p. 16); Ch. 3 Airway management (pp. 19–21)"),
                cite(EU, "R3"),
                cite(NICE, "Rec 1.2.1–1.2.2"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Breathing: decompress, seal, drain",
            timing = "Primary survey",
            points = listOf(
                "MoHFW 2025: check neck veins, trachea and chest excursion; a simple pneumothorax can tension on positive-pressure ventilation unless drained.",
                "NICE NG39: decompress tension pneumothorax before imaging only with haemodynamic instability or severe respiratory compromise; in hospital — open thoracostomy, then chest drain.",
                "Needle site differs: WHO BEC — 2nd intercostal space, midclavicular line (14–16G); ICMR STW 2024 — 4th/5th space, anterior axillary line; then a chest tube.",
                "Open (sucking) wound: NICE NG39 — simple occlusive dressing, watch for tension; WHO BEC, ICMR and MoHFW 2025 — dressing taped on three sides, then drain.",
                "European 2023: avoid hyperoxaemia except with imminent exsanguination.",
                "Massive haemothorax, flail chest and tamponade: see the Chest trauma protocol.",
            ),
            citations = listOf(
                cite(MOH, "Ch. 2 Breathing (p. 14); Ch. 5 Thoracic trauma (p. 26)"),
                cite(NICE, "Rec 1.3.7, 1.4.1–1.4.2"),
                cite(WHO, "Quick cards — Approach to the patient with trauma (B); Skills — emergency needle decompression; open pneumothorax"),
                cite(ICMR, "Lethal six — tension and open pneumothorax panels"),
                cite(EU, "R3"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Circulation: access, bloods, tranexamic acid",
            timing = "Primary survey; tranexamic acid within 3 h of injury",
            points = listOf(
                "MoHFW 2025: hypotension after injury is blood loss until proven otherwise; an injured patient who is cool and tachycardic is in hypovolaemic shock until proven otherwise.",
                "MoHFW 2025: two 16G cannulas; Hb, platelets, PT/INR, aPTT, group and cross-match, blood gas, urea, creatinine; pregnancy test in women 15–49 years.",
                "NICE NG39: peripheral IV access, intra-osseous if it fails; in children consider intra-osseous first-line if peripheral access is expected to be difficult.",
                "Adults — European 2023 and MoHFW 2025: tranexamic acid 1 g IV within 3 h of injury, then 1 g over 8 h. NICE NG39: as soon as possible.",
                "Children: none of these sources gives a paediatric tranexamic acid dose — European 2023 has no paediatric-specific recommendations and CRASH-2 enrolled adults.",
                "MoHFW 2025: look for bleeding in chest, abdomen, pelvis, long bones and face. Fluids, blood and reversal: see the Traumatic haemorrhage protocol.",
            ),
            citations = listOf(
                cite(MOH, "Ch. 2 Circulation with haemorrhage control, Fig. 4 (pp. 14–15); Table 1 (p. 11); Ch. 4 (p. 22)"),
                cite(NICE, "Rec 1.5.4, 1.5.15–1.5.17"),
                cite(EU, "R23; Discussion — limitations"),
                cite(CRASH2, "Abstract — Methods"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Disability and exposure",
            timing = "Primary survey",
            points = listOf(
                "MoHFW 2025: GCS, pupil size and reaction, and the level of any spinal cord injury; presume altered consciousness is from CNS injury until proven otherwise.",
                "MoHFW 2025: bedside finger-prick glucose (RBS) is a mandatory investigation. WHO BEC: check glucose in suspected brain injury.",
                "MoHFW 2025: repeat the neurological assessment once perfusion and oxygenation are restored. Head injury care: see the head injury protocol.",
                "MoHFW 2025: undress fully while keeping privacy; prevent hypothermia with a radiant warmer, warming blanket and pre-warmed IV fluids.",
                "NICE NG39: minimise ongoing heat loss. European 2023: early measures to reduce heat loss and rewarm the hypothermic patient to normothermia.",
            ),
            scoreIds = listOf("gcs"),
            citations = listOf(
                cite(MOH, "Ch. 2 Disability (pp. 15–16); Table 1 (p. 11); Ch. 4 Disability and exposure (p. 24)"),
                cite(WHO, "Quick cards — Approach to the patient with trauma (D)"),
                cite(NICE, "Rec 1.6.1"),
                cite(EU, "R18"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Adjuncts and imaging",
            timing = "Urgent; images interpreted immediately (NICE NG39)",
            points = listOf(
                "MoHFW 2025: for red patients — bedside chest and pelvis X-ray (AP), eFAST and NCCT head; ECG, contrast CT and viscoelastic tests as indicated.",
                "NICE NG39: unstable and not responding to volume resuscitation — limit imaging (chest and pelvis X-ray, FAST) to what directs intervention.",
                "NICE NG39: a negative FAST does not exclude intraperitoneal or retroperitoneal haemorrhage; a negative chest eFAST does not exclude pneumothorax.",
                "NICE NG39: adults with blunt major trauma and suspected multiple injuries — whole-body CT (vertex-to-toes scanogram, then vertex to mid-thigh); not routinely in children.",
                "NICE NG39: do not use FAST before immediate CT or as a screen for CT. European 2023: POCUS including FAST in thoracoabdominal injury; early contrast whole-body CT.",
                "MoHFW 2025: no urinary catheter with blood at the meatus or perineal bruising — refer for retrograde urethrogram.",
            ),
            citations = listOf(
                cite(MOH, "Table 1 (p. 11); Ch. 2 Adjuncts to the primary survey (p. 16)"),
                cite(NICE, "Rec 1.3.3, 1.5.28–1.5.36"),
                cite(EU, "R8"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Analgesia",
            timing = "Pain assessed within 20 min of arrival (MoHFW 2025)",
            points = listOf(
                "NICE NG39: assess pain regularly with a scale suited to age, development and cognition; IV morphine first-line, adjusted to effect.",
                "NICE NG39: no IV access — consider intranasal diamorphine or ketamine; ketamine in analgesic doses is second-line.",
                "MoHFW 2025 adult pain ladder: severe pain (7–10) — IV fentanyl or morphine, reassess within 30 min; moderate (4–6) — IV NSAIDs such as diclofenac or ketorolac.",
                "MoHFW 2025 drug table lists ketorolac only for age <65 years: 15–30 mg 6-hourly, max 120 mg/day (<50 kg: 15 mg 6-hourly, max 60 mg/day), for up to 5 days.",
                "Ketorolac US label: contraindicated with suspected or confirmed cerebrovascular bleeding, haemorrhagic diathesis, incomplete haemostasis or high bleeding risk, and with risk of renal failure from volume depletion; correct hypovolaemia first.",
                "MoHFW 2025: morphine 2 mg IV loading, then 2–4 mg every 1–2 h; fentanyl 1–2 mcg/kg IV loading, then 1–3 mcg/kg/h infusion.",
                "MoHFW 2025: stop the opioid infusion if BP <100 mm Hg (give IV fluids) or RR <10 or SpO₂ <94% (naloxone 2 mg IV stat).",
                "Naloxone US label: for opioid depression after surgery, smaller doses — 0.1–0.2 mg IV every 2–3 min to adequate ventilation and alertness without significant pain; abrupt reversal can cause VT/VF, pulmonary oedema and cardiac arrest.",
                "MoHFW 2025: splints, collars and dressings relieve pain; ultrasound-guided nerve blocks are to be performed by an anaesthesiologist.",
            ),
            doses = listOf(
                StepDose(
                    "Fentanyl — loading dose (MoHFW 2025)",
                    WeightDose(1.0, 2.0, unit = "mcg", per = "IV loading dose"),
                    note = "Monitor BP and respiratory rate",
                ),
                StepDose(
                    "Fentanyl — maintenance infusion (MoHFW 2025)",
                    WeightDose(1.0, 3.0, unit = "mcg", per = "per hour, IV infusion"),
                    note = "Stop if BP <100 mm Hg, RR <10 or SpO₂ <94%",
                ),
            ),
            citations = listOf(
                cite(NICE, "Rec 1.7.2–1.7.6"),
                cite(MOH, "Ch. 14 Pain ladder, Fig. 21 (p. 62); drug and dose table, Table 7 (p. 65); nerve blocks (p. 66)"),
                cite(KETOROLAC, "Boxed warning — Renal risk, Risk of bleeding; Dosage and administration"),
                cite(NALOX_INJ, "Dosage and administration — Usage in adults, Postoperative opioid depression; Precautions — General"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "Secondary survey, tetanus and transfer",
            timing = "Once the primary survey is complete and vital signs start to normalise (MoHFW 2025)",
            points = listOf(
                "MoHFW 2025: AMPLE history; head-to-toe examination with log roll, back, perineum, rectal and vaginal examination; keep resuscitating and reassessing ABCDE.",
                "MoHFW 2025: monitor urine output — adults 0.5 mL/kg/h, children 1 mL/kg/h.",
                "MoHFW 2025: tetanus toxoid 0.5 mL IM if indicated; MoHFW UIP has replaced TT with Td for all age groups. See the tetanus prophylaxis protocol.",
                "NICE NG39: the optimal destination is usually a major trauma centre. European 2023: take severely injured patients directly to an appropriate trauma facility.",
                "MoHFW 2025: transfer early when needs exceed capability — airway, chest drain, bleeding control, splints first; do not delay for CT; speak doctor-to-doctor.",
                "Injured pregnant woman: MoHFW 2025 — pelvic (vaginal) examination only after ultrasound localisation of the placenta. See the Trauma in pregnancy protocol.",
            ),
            citations = listOf(
                cite(MOH, "Ch. 2 Secondary survey and re-evaluation (pp. 17–18); Ch. 1 Yellow area (p. 12); Ch. 13 Trauma in pregnancy — Secondary survey (p. 58); Ch. 15 Patient transfer (pp. 67–68)"),
                cite(MOH_TD, "Key messages; points to remember"),
                cite(NICE, "Rec 1.1.1"),
                cite(EU, "R1"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Open chest wound dressings differ: NICE NG39 — simple occlusive dressing; WHO BEC, ICMR STW 2024 and MoHFW 2025 — dressing taped on three sides; RCUK/ERC 2025 first aid — leave exposed or use a vented non-occlusive dressing.",
            listOf(cite(NICE, "Rec 1.3.7"), cite(WHO, "Breathing skill station — open pneumothorax"), cite(ICMR, "Open pneumothorax panel"), cite(MOH, "Ch. 5 (p. 26)"), cite(RCUK_FA, "Open chest wounds")),
        ),
        ProtocolNote(
            "Early fluids differ: WHO BEC — 1 L crystalloid over <30 min, a second 1 L if still in shock, and if still in shock after 2 L reassess for ongoing blood loss and call an advanced provider; MoHFW 2025 — 1 L warmed Ringer's lactate before blood; NICE NG39 — no crystalloid in hospital for active bleeding; European 2023 — restricted volume, avoid Ringer's lactate in severe head trauma.",
            listOf(cite(WHO, "Skills — IV fluid administration"), cite(MOH, "Ch. 2 (p. 15); Ch. 4 (p. 23)"), cite(NICE, "Rec 1.5.23"), cite(EU, "R13, R15")),
        ),
        ProtocolNote(
            "Imaging differs: MoHFW 2025 makes chest and pelvis X-ray, eFAST and NCCT head mandatory for red patients; NICE NG39 advises against FAST before immediate CT and uses whole-body CT for adults with suspected multiple injuries.",
            listOf(cite(MOH, "Table 1 (p. 11)"), cite(NICE, "Rec 1.5.32–1.5.34")),
        ),
        ProtocolNote(
            "Intubation thresholds: European 2023 — GCS ≤8; MoHFW 2025 — definitive airway if airway integrity is in doubt or GCS ≤10 (Ch. 2), and endotracheal intubation in severe TBI with GCS <8 (Ch. 7).",
            listOf(cite(EU, "R3"), cite(MOH, "Ch. 2 Airway (p. 14); Ch. 7 Head trauma (p. 35)")),
        ),
        ProtocolNote(
            "Tetanus: MoHFW 2025 still lists tetanus toxoid 0.5 mL IM; under the MoHFW Universal Immunization Programme, Td has replaced TT for all age groups and earlier TT doses remain valid.",
            listOf(cite(MOH, "Ch. 1 Yellow area (p. 12)"), cite(MOH_TD, "Points to remember")),
        ),
        ProtocolNote(
            "Tourniquet release differs: WHO BEC — release every 2 h for at least 10 min while holding direct pressure, re-applying only for continued active bleeding; RCUK/ERC 2025 first aid — the first aider does not release it; it should only be released by a healthcare professional.",
            listOf(cite(WHO, "Skills — Tourniquet technique for uncontrolled external bleeding"), cite(RCUK_FA, "Control of life-threatening bleeding")),
        ),
        ProtocolNote(
            "Scope: the ATLS manual is proprietary and is not used as a source here. MoHFW 2025 (Dte.GHS, with AIIMS JPNATC contributors) and ICMR STW 2024 are the Indian references; NICE NG39 and the European guideline 2023 are the international ones. Both Indian documents are ATLS-based: the ICMR STW lists ATLS 9th edition (2013) as its only reference, and MoHFW 2025 refers to ATLS throughout. Their statements are reported as ICMR and MoHFW publish them.",
            listOf(cite(MOH, "List of contributors (p. 5); Ch. 3 (p. 19); Ch. 13 (p. 57); Table 9 (p. 69)"), cite(ICMR, "Footer; Reference"), cite(NICE), cite(EU)),
        ),
    ),
    citations = listOf(cite(MOH), cite(ICMR), cite(WHO), cite(NICE), cite(EU), cite(RCUK_FA), cite(MOH_TD), cite(CRASH2), cite(KETOROLAC), cite(NALOX_INJ)),
)

// ================================================================================================ Traumatic haemorrhage
private val traumaticHaemorrhage = Protocol(
    id = "traumatic-haemorrhage",
    category = ProtocolCategory.Trauma,
    title = "Traumatic haemorrhage and shock",
    summary = "Recognition, bleeding control, tranexamic acid, permissive hypotension, blood components, calcium, " +
        "damage control and anticoagulant reversal — attributed to the European guideline 2023, NICE NG39, CRASH-2, ERC 2025, WHO BEC and MoHFW 2025.",
    keywords = listOf(
        "haemorrhagic shock", "hemorrhagic shock", "hypovolaemic shock", "traumatic bleeding", "bleeding trauma patient",
        "massive transfusion", "MTP", "major haemorrhage protocol", "MHP", "massive haemorrhage protocol", "TXA",
        "tranexamic acid", "Pause injection", "CRASH-2", "permissive hypotension", "hypotensive resuscitation",
        "damage control resuscitation", "DCR", "damage control surgery", "shock index", "lethal triad",
        "trauma-induced coagulopathy", "TIC", "FFP", "PRBC", "packed cells", "cryoprecipitate", "cryo", "fibrinogen",
        "platelets", "calcium chloride", "hypocalcaemia", "ROTEM", "TEG", "pelvic binder", "REBOA",
        "warfarin reversal", "anticoagulant reversal", "PCC", "4F-PCC", "Kcentra", "Beriplex", "vitamin K",
        "phytomenadione", "idarucizumab", "Praxbind", "andexanet", "Andexxa", "DOAC", "dabigatran", "apixaban",
        "rivaroxaban", "edoxaban", "antiplatelet", "khoon behna", "khoon vagna", "zyada khoon",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Recognise haemorrhagic shock",
            timing = "Primary survey, and repeatedly",
            points = listOf(
                "WHO BEC: early shock may show only tachycardia or tachypnoea; suspect it even with SBP >90 mm Hg if bleeding is severe or perfusion poor.",
                "European 2023: judge blood loss from physiology, injury pattern, mechanism and response to initial resuscitation; use shock index and/or pulse pressure.",
                "European 2023: shock index (HR ÷ SBP) is normally 0.5–0.7; ≥0.9–1.0 was associated with massive transfusion, as was pulse pressure <30 mm Hg.",
                "MoHFW 2025: over 65 years, SBP <110 mm Hg is considered shock; look for occult shock when vital signs seem stable.",
                "European 2023: repeat Hb or haematocrit — a normal first value may mask early bleeding; lactate, or base deficit, estimates bleeding and hypoperfusion.",
                "MoHFW 2025: search chest, abdomen, pelvis, long bones and maxillofacial injuries; log roll if no source is found.",
            ),
            citations = listOf(
                cite(WHO, "Trauma module — Circulation conditions (hypovolaemic shock)"),
                cite(EU, "R5 and rationale; R9–R10"),
                cite(MOH, "Ch. 2 Fig. 4 (p. 15); Ch. 4 (pp. 22–23); Ch. 12 Geriatric trauma (p. 56)"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Stop the bleeding",
            timing = "Immediately — minimise time from injury to bleeding control (European 2023)",
            points = listOf(
                "European 2023: obvious source, or shock in extremis with a suspected source — immediate bleeding control; otherwise immediate investigation to find the source.",
                "External bleeding — pressure, haemostatic dressing, tourniquet: see the <C> step of Major trauma (NICE NG39, European 2023, RCUK/ERC 2025).",
                "NICE NG39: purpose-made pelvic binder for suspected pelvic bleeding after blunt high-energy trauma. European 2023: close and stabilise the pelvic ring early in shock.",
                "MoHFW 2025: centre the binder over the greater trochanters with legs internally rotated; apply it before intubation, as paralysis may let the pelvis expand.",
                "European 2023: extraperitoneal packing if bleeding continues or angioembolisation is delayed. NICE NG39: interventional radiology for active arterial pelvic bleeding.",
                "European 2023: REBOA may be considered as a bridge in noncompressible bleeding; ERC 2025 does not recommend REBOA outside clinical trials.",
            ),
            citations = listOf(
                cite(EU, "R1, R6–R7, R20–R21"),
                cite(NICE, "Rec 1.5.3, 1.5.40–1.5.42"),
                cite(MOH, "Ch. 6 Pelvic stabilisation (p. 30)"),
                cite(ERC, "Treatment of the reversible causes — Hypovolaemia"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Tranexamic acid",
            timing = "As soon as possible; not more than 3 h after injury (NICE NG39)",
            points = listOf(
                "Adults — European 2023 (Grade 1A): bleeding or at risk of it — tranexamic acid 1 g IV over 10 min, then 1 g over 8 h, within 3 h of injury.",
                "European 2023: give it as soon as possible, en route to hospital if feasible, and do not wait for viscoelastic results.",
                "NICE NG39: IV tranexamic acid as soon as possible in active or suspected bleeding; not more than 3 h after injury unless hyperfibrinolysis is evident.",
                "Adults — MoHFW 2025: severely injured — tranexamic acid 1 g within 3 h of injury, plus 1 g infusion over 8 h.",
                "Children: none of these sources gives a paediatric tranexamic acid dose — European 2023 has no paediatric-specific recommendations and CRASH-2 enrolled adults.",
                "CRASH-2 (exploratory analysis): death from bleeding fell with treatment ≤1 h (RR 0.68) and at 1–3 h (RR 0.79); treatment after 3 h seemed to increase it (RR 1.44) and could be harmful.",
            ),
            citations = listOf(
                cite(EU, "R23; Discussion — limitations"),
                cite(NICE, "Rec 1.5.4–1.5.5"),
                cite(MOH, "Ch. 2 Circulation with haemorrhage control (p. 15)"),
                cite(CRASH2, "Abstract — Methods, Findings, Interpretation"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Permissive hypotension and fluids",
            timing = "Until major bleeding is controlled",
            points = listOf(
                "European 2023: restricted volume replacement to SBP 80–90 mm Hg (MAP 50–60 mm Hg) until major bleeding stops, if there is no brain injury.",
                "European 2023: severe TBI (GCS ≤8) — MAP ≥80 mm Hg. NICE NG39: if TBI dominates, use less restrictive resuscitation to maintain cerebral perfusion.",
                "MoHFW 2025: penetrating torso trauma — SBP 85–90 mm Hg. For TBI it gives two targets: SBP 90–95 mm Hg under permissive hypotension (Ch. 4) and SBP >100–110 mm Hg in its head trauma chapter (Ch. 7).",
                "NICE NG39: before hospital, titrate to a palpable central (carotid or femoral) pulse. ERC 2025: hypotensive resuscitation should not exceed 60 min.",
                "NICE NG39: in hospital, no crystalloids for active bleeding. European 2023: 0.9% saline or balanced crystalloid if hypotensive; avoid Ringer's lactate in severe head trauma.",
                "European 2023: restrict colloids; if targets are not met, add noradrenaline to fluids; dobutamine for myocardial dysfunction.",
            ),
            citations = listOf(
                cite(EU, "R13–R15"),
                cite(NICE, "Rec 1.5.18–1.5.23"),
                cite(MOH, "Ch. 4 Permissive hypotension (p. 23); Ch. 7 Head trauma — Circulation goals (p. 35)"),
                cite(ERC, "Post resuscitation care after traumatic cardiac arrest"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Blood components and major haemorrhage protocol",
            timing = "Activate early",
            points = listOf(
                "NICE NG39: activate the major haemorrhage protocol on physiology and response to volume resuscitation, not a risk tool at a single time point.",
                "NICE NG39: adults — plasma to red cells 1:1; children — 1:1, volume by weight. Start fixed-ratio, then switch to laboratory-guided as soon as possible.",
                "European 2023: expected massive haemorrhage — fibrinogen concentrate or cryoprecipitate with red cells, or FFP:RBC at least 1:2; a high platelet:RBC ratio is suggested.",
                "MoHFW 2025: class III–IV shock — massive haemorrhage protocol; if cross-matched blood is unavailable, group O red cells (O-negative in women of reproductive age) and AB-negative plasma.",
                "MoHFW 2025: every hospital receiving trauma must have a massive haemorrhage protocol; one without a blood bank needs blood storage with round-the-clock supply.",
                "MoHFW 2025: children <40 kg — 20 mL/kg warmed crystalloid bolus, repeated once or twice; 10 mL/kg packed red cells if bleeding continues.",
            ),
            doses = listOf(
                StepDose(
                    "Children <40 kg — crystalloid bolus (MoHFW 2025)",
                    WeightDose(20.0, 20.0, unit = "mL", per = "IV bolus of pre-warmed isotonic crystalloid"),
                    note = "Assess response; may repeat once or twice (MoHFW 2025)",
                ),
                StepDose(
                    "Children — packed red cells if bleeding continues (MoHFW 2025)",
                    WeightDose(10.0, 10.0, unit = "mL", per = "transfusion"),
                ),
            ),
            citations = listOf(
                cite(NICE, "Rec 1.5.13–1.5.14, 1.5.24–1.5.27"),
                cite(EU, "R25–R27"),
                cite(MOH, "Ch. 2 (p. 15); Ch. 4 (pp. 22–23); Ch. 11 Paediatric trauma — Circulation (p. 53)"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Coagulation targets and calcium",
            timing = "Monitor from admission and during transfusion",
            points = listOf(
                "European 2023: early, repeated PT/INR, Clauss fibrinogen and platelets and/or viscoelastic tests; continue goal-directed by these results.",
                "European 2023: fibrinogen ≤1.5 g/L or a viscoelastic deficit — initially 3–4 g fibrinogen concentrate or 15–20 single-donor units of cryoprecipitate.",
                "European 2023: platelets above 50 × 10⁹/L in ongoing bleeding and above 100 × 10⁹/L with TBI; red cells to Hb 70–90 g/L.",
                "European 2023: keep ionised calcium normal (1.1–1.3 mmol/L), especially in massive transfusion; correct hypocalcaemia with calcium chloride.",
                "European 2023: correct transfusion-induced ionised calcium <0.9 mmol/L promptly; <0.8 mmol/L is associated with dysrhythmias. 10 mL 10% CaCl₂ has 270 mg calcium, gluconate 90 mg.",
                "European 2023: no recombinant factor VIIa first-line; consider off-label only if bleeding and coagulopathy persist despite all other measures.",
            ),
            citations = listOf(
                cite(EU, "R11, R16, R26, R29–R32; R31 rationale"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Damage control and the lethal triad",
            timing = "Decide early",
            points = listOf(
                "ERC 2025: damage control resuscitation combines permissive hypotension, haemostatic resuscitation and damage control surgery; hypothermia worsens coagulopathy and acidosis.",
                "European 2023: damage control surgery for shock, ongoing bleeding, coagulopathy, hypothermia, acidosis, inaccessible injury or time-consuming procedures.",
                "NICE NG39: damage control surgery if unstable and not responding to volume resuscitation; consider definitive surgery if unstable but responding; definitive surgery if haemodynamically normal.",
                "European 2023 and ERC 2025: early active warming — warmed fluids and external warming devices — to reach and keep normothermia.",
                "MoHFW 2025: move non-responders and transient responders to theatre or interventional radiology; transfer if expertise or angioembolisation is unavailable.",
                "MoHFW 2025: urine output goals — adults 0.5 mL/kg/h, children 1 mL/kg/h, under 1 year 2 mL/kg/h.",
            ),
            citations = listOf(
                cite(ERC, "Post resuscitation care after traumatic cardiac arrest"),
                cite(EU, "R18–R19"),
                cite(NICE, "Rec 1.5.37–1.5.39, 1.6.1"),
                cite(MOH, "Ch. 4 (pp. 23, 25); Ch. 6 Refer (p. 32)"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Reverse anticoagulants",
            timing = "Immediately in active bleeding",
            points = listOf(
                "NICE NG39: rapidly reverse anticoagulation in major trauma with bleeding; do not reverse without active or suspected bleeding.",
                "European 2023 (1A): vitamin K antagonist — early 4-factor PCC plus phytomenadione 5–10 mg IV. NICE NG39: PCC immediately; do not use plasma for this.",
                "European 2023: PCC 25 U/kg at INR 2–4, 35 U/kg at INR 4–6, 50 U/kg above 6. Kcentra label: dose capped at 100 kg; repeat dosing not recommended.",
                "European 2023: life-threatening apixaban or rivaroxaban bleeding — andexanet alfa; if unavailable, or edoxaban, PCC 25–50 U/kg. FDA 2025: andexanet risks outweigh benefits.",
                "Kcentra label: contraindicated in DIC, known heparin-induced thrombocytopenia (it contains heparin) and prior anaphylaxis to its components; boxed warning — arterial and venous thromboembolism; may not suit patients with a thromboembolic event in the prior 3 months.",
                "European 2023 (1C): life-threatening bleeding on dabigatran — idarucizumab 5 g IV. NICE NG39: consult a haematologist for reversal of non-VKA anticoagulants.",
                "European 2023: avoid routine platelet transfusion in bleeding patients on antiplatelet agents.",
            ),
            doses = listOf(
                StepDose(
                    "4F-PCC — warfarin, INR 2 to <4",
                    WeightDose(25.0, 25.0, unit = "IU", per = "single dose (factor IX units), with IV vitamin K"),
                    note = "Kcentra label: max 2500 IU; ~3 IU/kg/min, not above ~210 IU/min",
                    maxTotal = 2500.0,
                ),
                StepDose(
                    "4F-PCC — warfarin, INR 4–6",
                    WeightDose(35.0, 35.0, unit = "IU", per = "single dose (factor IX units), with IV vitamin K"),
                    note = "Kcentra label: max 3500 IU; ~3 IU/kg/min, not above ~210 IU/min",
                    maxTotal = 3500.0,
                ),
                StepDose(
                    "4F-PCC — warfarin, INR >6",
                    WeightDose(50.0, 50.0, unit = "IU", per = "single dose (factor IX units), with IV vitamin K"),
                    note = "Kcentra label: max 5000 IU; ~3 IU/kg/min, not above ~210 IU/min",
                    maxTotal = 5000.0,
                ),
                StepDose(
                    "PCC — factor Xa inhibitor (edoxaban, or andexanet unavailable)",
                    WeightDose(25.0, 50.0, unit = "IU", per = "dose (European 2023)"),
                    note = "European 2023 suggestion (Grade 2C); no cap stated",
                ),
            ),
            citations = listOf(
                cite(NICE, "Rec 1.5.6–1.5.12"),
                cite(EU, "R33–R36; R33 rationale (INR-based PCC dosing)"),
                cite(KCENTRA, "Dosage and administration — Table 1; §2.1, §2.3; Boxed warning; §4 Contraindications; §5.2"),
                cite(ANDEXXA, "Safety communication, 18 December 2025"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "After bleeding control",
            timing = "Pharmacological prophylaxis within 24 h of bleeding control (European 2023)",
            points = listOf(
                "European 2023: intermittent pneumatic compression early while the patient is immobile and at bleeding risk.",
                "European 2023: add pharmacological prophylaxis within 24 h after bleeding is controlled, until mobile; no graduated compression stockings; no routine IVC filters.",
                "European 2023: PCC raises the risk of venous and arterial thrombosis — consider thromboprophylaxis as early as possible once bleeding is controlled.",
                "European 2023: implement local bleeding-management guidelines and track measures of bleeding control and outcome.",
            ),
            citations = listOf(
                cite(EU, "R37–R39; R33 rationale"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Blood pressure targets differ: European 2023 — SBP 80–90 mm Hg without brain injury, MAP ≥80 mm Hg in severe TBI; MoHFW 2025 — SBP 85–90 mm Hg (penetrating torso), and for TBI 90–95 mm Hg (Ch. 4) but >100–110 mm Hg (Ch. 7, head trauma); NICE NG39 — palpable central pulse; ERC 2025 — no more than 60 min of hypotensive resuscitation.",
            listOf(cite(EU, "R13"), cite(MOH, "Ch. 4 (p. 23); Ch. 7 Head trauma — Circulation goals (p. 35)"), cite(NICE, "Rec 1.5.19–1.5.21"), cite(ERC, "Post resuscitation care after traumatic cardiac arrest")),
        ),
        ProtocolNote(
            "Crystalloid differs: MoHFW 2025 — 1 L (children <40 kg: 20 mL/kg) before blood; WHO BEC — 1 L over <30 min, a second 1 L if still in shock, and if still in shock after 2 L reassess for ongoing blood loss and call an advanced provider; NICE NG39 — no crystalloid in hospital for active bleeding; European 2023 — start crystalloid if hypotensive but avoid Ringer's lactate in severe head trauma.",
            listOf(cite(MOH, "Ch. 4 (pp. 22–23)"), cite(WHO, "Skills — IV fluid administration"), cite(NICE, "Rec 1.5.22–1.5.23"), cite(EU, "R15")),
        ),
        ProtocolNote(
            "Plasma ratios differ: NICE NG39 — plasma:red cells 1:1; European 2023 — FFP:RBC at least 1:2, or a fibrinogen/factor-concentrate strategy, then goal-directed by laboratory or viscoelastic results.",
            listOf(cite(NICE, "Rec 1.5.24, 1.5.27"), cite(EU, "R25–R28")),
        ),
        ProtocolNote(
            "Andexanet alfa: European 2023 suggests it for life-threatening apixaban or rivaroxaban bleeding; in December 2025 the US FDA judged its risks to outweigh its benefits (ANNEXA-I thrombosis 14.6% vs 6.9%) and US sales ended.",
            listOf(cite(EU, "R34"), cite(ANDEXXA, "Safety communication, 18 December 2025")),
        ),
        ProtocolNote(
            "Tranexamic acid in head injury: European 2023 reports that in CRASH-3, treatment within 3 h reduced head-injury death in mild and moderate but not severe TBI. See the head injury protocol.",
            listOf(cite(EU, "R23 rationale")),
        ),
        ProtocolNote(
            "Universal donor products: MoHFW 2025 advises group O red cells (O-negative for women of reproductive age, O-positive for others) and AB-negative plasma for exsanguinating haemorrhage until cross-matched blood is available.",
            listOf(cite(MOH, "Ch. 4 (p. 23)")),
        ),
    ),
    citations = listOf(cite(EU), cite(NICE), cite(CRASH2), cite(ERC), cite(WHO), cite(MOH), cite(KCENTRA), cite(ANDEXXA)),
)

// ================================================================================================ Chest trauma
private val chestTrauma = Protocol(
    id = "chest-trauma",
    category = ProtocolCategory.Trauma,
    title = "Chest trauma",
    summary = "Lethal and hidden chest injuries — tension and open pneumothorax, haemothorax, tamponade, flail chest and rib " +
        "fractures, blunt cardiac and aortic injury — attributed to ICMR STW 2024, MoHFW 2025, WHO BEC, NICE NG39, EAST and ERC 2025.",
    keywords = listOf(
        "chest trauma", "chest injury", "thoracic trauma", "blunt chest trauma", "penetrating chest injury", "stab chest",
        "lethal six", "hidden seven", "tension pneumothorax", "pneumothorax", "open pneumothorax", "sucking chest wound",
        "haemothorax", "hemothorax", "massive haemothorax", "haemopneumothorax", "ICD", "intercostal drain", "ICD tube",
        "chest tube", "UWSD", "underwater seal drain", "pigtail", "needle decompression", "needle thoracostomy",
        "finger thoracostomy", "thoracostomy", "cardiac tamponade", "pericardiocentesis", "flail chest", "rib fracture",
        "rib fractures", "pulmonary contusion", "lung contusion", "blunt cardiac injury", "BCI", "cardiac contusion",
        "myocardial contusion", "aortic injury", "aortic rupture", "diaphragm injury", "oesophageal rupture",
        "serratus anterior block", "erector spinae block", "ESP block", "thoracic epidural", "rib fixation", "SSRF",
        "seene ki chot", "chhati di satt", "pasli tootna", "pasli fracture",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Recognise immediately life-threatening injuries",
            timing = "Primary survey",
            points = listOf(
                "ICMR STW 2024: the primary survey finds immediately life-threatening injuries largely by clinical examination; portable AP chest X-ray is the adjunct.",
                "MoHFW 2025: assess neck veins, tracheal position and chest excursion; palpate clavicles, ribs and sternum; auscultate for pneumothorax and tamponade.",
                "ICMR STW 2024: look also for muffled heart sounds, peripheral pulses, first-rib or chest tenderness and subcutaneous emphysema.",
                "NICE NG39: before hospital, diagnose pneumothorax clinically; eFAST only if a specialist team with ultrasound is present and transfer is not delayed.",
                "ICMR STW 2024: keep a high threshold for invasive procedures.",
            ),
            checklistTitle = "ICMR STW 2024 — lethal six",
            checklist = listOf(
                "Airway obstruction",
                "Tension pneumothorax",
                "Massive haemothorax",
                "Open pneumothorax",
                "Tracheobronchial injury",
                "Cardiac tamponade",
            ),
            citations = listOf(
                cite(ICMR, "Initial assessment and primary survey; Lethal six panel; footer"),
                cite(MOH, "Ch. 2 Breathing (p. 14); Secondary survey — Chest (p. 17)"),
                cite(NICE, "Rec 1.3.1–1.3.3"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Tension pneumothorax",
            timing = "Decompress immediately if unstable",
            points = listOf(
                "WHO BEC and MoHFW 2025: hypotension, absent breath sounds, hyperresonance, distended neck veins, tracheal shift; may appear after starting ventilation.",
                "NICE NG39: decompress before imaging only with haemodynamic instability or severe respiratory compromise; in hospital, open thoracostomy followed by chest drain.",
                "NICE NG39: before hospital, open thoracostomy instead of needle decompression if expertise allows; drain through it if breathing spontaneously; watch for recurrence.",
                "WHO BEC: needle decompression at the 2nd intercostal space, midclavicular line, with a 14–16G cannula; then a chest tube.",
                "ICMR STW 2024: wide-bore needle, 4th/5th space, anterior axillary line, then chest tube.",
                "MoHFW 2025: wide-bore needle, 5th space midaxillary line, then drain (Ch. 4); its Thoracic trauma chapter gives needle decompression followed by chest tube in the 5th space, anterior axillary line (Ch. 5).",
                "MoHFW 2025, children: 14–18G needle, 2nd intercostal space midclavicular line; tube thoracostomy 5th space anterior to the midaxillary line.",
            ),
            citations = listOf(
                cite(WHO, "Trauma module — Breathing conditions; Skills — emergency needle decompression"),
                cite(MOH, "Ch. 2 (p. 14); Ch. 4 Obstructive shock (p. 25); Ch. 5 (p. 26); Ch. 11 Breathing (p. 52)"),
                cite(NICE, "Rec 1.3.4–1.3.6, 1.4.1–1.4.2"),
                cite(ICMR, "Tension pneumothorax panel"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Open pneumothorax and airway injury",
            timing = "Primary survey",
            points = listOf(
                "NICE NG39: cover an open pneumothorax with a simple occlusive dressing and observe for tension pneumothorax.",
                "WHO BEC: non-adhesive dressing taped on three sides as a flap valve; remove if breathing or perfusion worsens; do not use without continuous observation.",
                "ICMR STW 2024: sterile dressing secured on 3 sides, then chest tube (≥28 Fr), then secure on all 4 sides. WHO BEC: no chest tube through the wound.",
                "RCUK/ERC 2025 first aid: leave the wound exposed; trained responders may use a vented non-occlusive dressing, watching for clot blocking air outflow.",
                "MoHFW 2025: tracheobronchial injury — haemoptysis, cervical emphysema; anticipate difficult intubation, involve anaesthesia early. ICMR: massive air leak — ICD plus surgery.",
            ),
            citations = listOf(
                cite(NICE, "Rec 1.3.7"),
                cite(WHO, "Breathing skill station — open pneumothorax; Trauma module — sucking chest wound"),
                cite(ICMR, "Open pneumothorax and tracheobronchial injury panels"),
                cite(RCUK_FA, "Open chest wounds"),
                cite(MOH, "Ch. 5 Airway problems (p. 26)"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Haemothorax and chest drains",
            timing = "Massive haemothorax in the primary survey",
            points = listOf(
                "MoHFW 2025: massive haemothorax — dull note, decreased breath sounds, flat neck veins; 28–32 Fr chest tube, 5th space midaxillary line. ICMR: resuscitate shock, insert tube.",
                "MoHFW 2025: immediate return >1500 mL, or >200 mL/h for 2–4 h — consider thoracotomy; involve the trauma or general surgeon early.",
                "EAST 2021 (conditional): haemodynamically stable traumatic haemothorax — pigtail catheter.",
                "EAST 2021: retained haemothorax — VATS rather than thrombolytics, performed early (≤4 days).",
                "EAST 2022 (conditional): antibiotic prophylaxis at chest tube insertion reduces empyema.",
                "ICMR STW 2024: simple pneumothorax — chest tube (>28 Fr), 4th/5th space just anterior to the midaxillary line.",
            ),
            citations = listOf(
                cite(MOH, "Ch. 5 Circulation problems; Secondary survey (pp. 26–27)"),
                cite(ICMR, "Massive haemothorax and simple pneumothorax panels"),
                cite(EAST_HTX, "Abstract — Conclusions"),
                cite(EAST_ABX, "Abstract — Discussion"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Cardiac tamponade and penetrating injury",
            timing = "Call the surgeon at once",
            points = listOf(
                "MoHFW 2025: muffled heart sounds, distended neck veins, Kussmaul sign, pericardial fluid on eFAST. ICMR STW 2024: muffled heart sounds, hypotension, pericardial fluid on FAST.",
                "MoHFW 2025: thoracotomy or sternotomy; subxiphoid pericardiocentesis, ideally ultrasound-guided, only as a temporising measure; involve trauma or CTVS surgeon early.",
                "ERC 2025: needle pericardiocentesis keeps a limited role in non-trauma centres where definitive surgery is not immediately available.",
                "MoHFW 2025: penetrating thorax, neck or abdomen injury is red triage; left thoracoabdominal wounds indicate laparotomy for diaphragm injury risk.",
                "WHO BEC: leave penetrating objects in place and stabilise them for transfer to a surgical team.",
                "EAST 2015 (strong): pulseless with signs of life after penetrating thoracic injury — ED thoracotomy; its evidence is mostly from major urban trauma centres and may not apply to community or rural centres.",
                "ERC 2025: resuscitative thoracotomy needs expertise, equipment, environment and ≤15 min elapsed since loss of vital signs. Arrest after penetrating chest injury: see Traumatic cardiac arrest.",
            ),
            citations = listOf(
                cite(MOH, "Ch. 5 Cardiac tamponade (p. 27); Ch. 4 (p. 25); Ch. 1 triage (p. 7); Ch. 6 laparotomy indications (p. 31)"),
                cite(ICMR, "Cardiac tamponade panel"),
                cite(ERC, "Cardiac tamponade and resuscitative thoracotomy"),
                cite(WHO, "Trauma module — Circulation; Quick cards — Management of specific conditions"),
                cite(EAST_EDT, "Abstract — Conclusion; Using these guidelines in clinical practice"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Imaging",
            timing = "Urgent; images interpreted immediately (NICE NG39)",
            points = listOf(
                "NICE NG39: adults with severe respiratory compromise — consider immediate chest X-ray and/or eFAST in the primary survey.",
                "NICE NG39: adults without severe compromise who respond to resuscitation or are haemodynamically normal — consider immediate CT.",
                "NICE NG39: children — chest X-ray and/or ultrasound first; do not routinely use CT first-line.",
                "NICE NG39: a negative chest eFAST does not exclude pneumothorax.",
                "ICMR STW 2024: portable AP chest X-ray for intrathoracic injury and tube positions; CT and special studies after the secondary survey.",
            ),
            citations = listOf(
                cite(NICE, "Rec 1.3.3, 1.4.3–1.4.7"),
                cite(ICMR, "Adjunct to primary survey; flow chart"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Flail chest and rib fractures",
            timing = "Secondary survey; analgesia early",
            points = listOf(
                "MoHFW 2025: humidified oxygen, adequate ventilation, judicious fluids and analgesia; intubate if SaO₂ <90%. ICMR: chest tube for associated pneumo- or haemothorax.",
                "MoHFW 2025: two or more rib fractures — thoracic epidural, serratus anterior or erector spinae plane block, performed by an anaesthesiologist.",
                "EAST 2016 (conditional, very low-quality evidence): epidural and multimodal analgesia as options in blunt thoracic trauma.",
                "EAST/CWIS 2023, older adults with multiple rib fractures: incentive spirometry to inform ICU need; NIPPV if no airway risk; no recommendation on ketamine, epidural or blocks.",
                "EAST 2017 (conditional): adult flail chest — operative rib fixation, to lower mortality, ventilation days, pneumonia and tracheostomy.",
                "MoHFW 2025: low threshold to admit older patients with rib fractures; ICU for significant chest wall injury. ICMR: consider transfer to an appropriate facility.",
            ),
            citations = listOf(
                cite(MOH, "Ch. 5 Flail chest (p. 27); Ch. 14 nerve blocks (p. 66); Ch. 12 Geriatric trauma (p. 56)"),
                cite(ICMR, "Flail chest and pulmonary contusion panels"),
                cite(EAST_PAIN, "Abstract — Conclusion"),
                cite(EAST_RIBS_OLD, "Abstract — Conclusion"),
                cite(EAST_RIBFIX, "Abstract — Conclusion"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Blunt cardiac injury",
            timing = "ECG on arrival when suspected",
            points = listOf(
                "EAST 2012 (Level 1): admission ECG for every patient with suspected blunt cardiac injury.",
                "EAST 2012: new ECG abnormality — arrhythmia, ST change, ischaemia, heart block — admit for continuous ECG monitoring.",
                "EAST 2012: normal ECG and normal troponin I rule out BCI; normal ECG but raised troponin I — monitored setting.",
                "EAST 2012: echocardiography for haemodynamic instability or persistent new arrhythmia (TOE if transthoracic is inadequate); sternal fracture alone does not predict BCI.",
                "EAST 2012: do not use CK-MB. MoHFW 2025: monitor for dysrhythmias for the first 24 h; involve a cardiologist, or physician if none.",
            ),
            citations = listOf(
                cite(EAST_BCI, "Recommendations — Level 1 and Level 2 (1–5)"),
                cite(MOH, "Ch. 5 Blunt cardiac injury (pp. 27–28)"),
                cite(ICMR, "Cardiac contusion panel"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "Aortic, diaphragm and oesophageal injury",
            timing = "Secondary survey",
            points = listOf(
                "ICMR STW 2024: thoracic aortic rupture is suspected on chest X-ray and confirmed on CT angiography; stenting or open surgery.",
                "NICE NG39: endovascular stent graft for blunt thoracic aortic injury.",
                "MoHFW 2025: aortic injury — esmolol to HR <80/min and MAP 60–70 mm Hg, nicardipine if β-blockade is insufficient; involve the trauma or CTVS surgeon.",
                "MoHFW 2025: diaphragm injury — chest X-ray after nasogastric tube, CT or contrast study; direct repair. ICMR: ruptured diaphragm — surgery.",
                "MoHFW 2025: oesophageal rupture — retrosternal pain, emphysema, pain or shock out of proportion. ICMR: food particles in the drain or dye leak; surgery.",
            ),
            checklistTitle = "ICMR STW 2024 — hidden seven",
            checklist = listOf(
                "Oesophageal injury",
                "Flail chest",
                "Pulmonary contusion",
                "Ruptured thoracic aorta",
                "Cardiac contusion",
                "Simple pneumothorax",
                "Ruptured diaphragm",
            ),
            citations = listOf(
                cite(ICMR, "Hidden seven panels"),
                cite(NICE, "Rec 1.5.43"),
                cite(MOH, "Ch. 5 Secondary survey items 5–7 (p. 28)"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "Needle decompression site differs: WHO BEC — 2nd space, midclavicular line; ICMR STW 2024 — 4th/5th space, anterior axillary line; MoHFW 2025 (adults) — 5th space, midaxillary line (Ch. 4) or anterior axillary line (Ch. 5). NICE NG39 and ERC 2025 prefer open thoracostomy when expertise is available.",
            listOf(cite(WHO, "Skills — emergency needle decompression"), cite(ICMR, "Tension pneumothorax panel"), cite(MOH, "Ch. 4 (p. 25); Ch. 5 (p. 26)"), cite(NICE, "Rec 1.3.5, 1.4.2"), cite(ERC, "Tension pneumothorax")),
        ),
        ProtocolNote(
            "Open chest wound: three-sided dressing (WHO BEC, ICMR STW 2024, MoHFW 2025) versus a simple occlusive dressing (NICE NG39) versus leaving it exposed or a vented dressing (RCUK/ERC 2025 first aid).",
            listOf(cite(WHO, "Breathing skill station — open pneumothorax"), cite(ICMR, "Open pneumothorax panel"), cite(MOH, "Ch. 5 (p. 26)"), cite(NICE, "Rec 1.3.7"), cite(RCUK_FA, "Open chest wounds")),
        ),
        ProtocolNote(
            "Massive haemothorax: MoHFW 2025 gives >1500 mL immediately or >200 mL/h for 2–4 h as thoracotomy triggers; its Table 9 notes that ATLS 11th edition removed strict volume criteria in favour of clinical and physiological assessment.",
            listOf(cite(MOH, "Ch. 5 (p. 27); Table 9 (p. 69)")),
        ),
        ProtocolNote(
            "Drain size differs: ICMR STW 2024 — ≥28 Fr; MoHFW 2025 — 28–32 Fr for massive haemothorax; EAST 2021 conditionally recommends pigtail catheters for haemodynamically stable haemothorax.",
            listOf(cite(ICMR, "Open pneumothorax and simple pneumothorax panels"), cite(MOH, "Ch. 5 (p. 26)"), cite(EAST_HTX, "Abstract — Conclusions")),
        ),
        ProtocolNote(
            "Rib fracture analgesia evidence is weak: EAST 2016 makes only conditional recommendations on very low-quality evidence; EAST/CWIS 2023 makes no recommendation on epidural or regional blocks in older adults; MoHFW 2025 lists blocks for two or more fractured ribs.",
            listOf(cite(EAST_PAIN, "Abstract"), cite(EAST_RIBS_OLD, "Abstract"), cite(MOH, "Ch. 14 (p. 66)")),
        ),
    ),
    citations = listOf(
        cite(ICMR), cite(MOH), cite(WHO), cite(NICE), cite(ERC), cite(RCUK_FA), cite(EAST_BCI), cite(EAST_PAIN),
        cite(EAST_RIBS_OLD), cite(EAST_RIBFIX), cite(EAST_HTX), cite(EAST_ABX), cite(EAST_EDT),
    ),
)

// ================================================================================================ Traumatic cardiac arrest
private val traumaticCardiacArrest = Protocol(
    id = "traumatic-cardiac-arrest",
    category = ProtocolCategory.Trauma,
    title = "Traumatic cardiac arrest",
    summary = "ERC 2025 traumatic arrest and peri-arrest algorithm — simultaneous treatment of hypovolaemia, hypoxia, tension " +
        "pneumothorax and tamponade, resuscitative thoracotomy selection (EAST 2015, WTA 2024), withholding and termination.",
    keywords = listOf(
        "traumatic cardiac arrest", "TCA", "traumatic arrest", "trauma arrest", "peri-arrest trauma", "PEA trauma",
        "pseudo-PEA", "resuscitative thoracotomy", "emergency department thoracotomy", "EDT", "ED thoracotomy",
        "clamshell thoracotomy", "bilateral thoracostomy", "finger thoracostomy", "simple thoracostomy",
        "aortic cross-clamp", "REBOA", "impact brain apnoea", "termination of resuscitation", "four Es",
        "resuscitative hysterotomy", "perimortem caesarean", "code red", "dil band", "saans band",
    ),
    steps = listOf(
        // 1 ----------------------------------------------------------------------------------------------
        Step(
            title = "Is this a traumatic arrest?",
            timing = "At once",
            points = listOf(
                "ERC 2025: traumatic arrest needs plausible signs of injury or a consistent mechanism; otherwise follow standard ALS (see Adult cardiac arrest).",
                "ERC 2025: the algorithm also applies to peri-arrest trauma patients with impending circulatory collapse.",
                "ERC 2025: ultrasound helps separate peri-arrest from arrest, find reversible causes and guide interventions.",
                "ERC 2025: causes — uncontrolled haemorrhage 48%, tension pneumothorax 13%, asphyxia 13%, tamponade 10%; rhythms mostly PEA (66%) or asystole (30%).",
                "ERC 2025: pseudo-PEA — weak cardiac contraction on ultrasound without an effective pulse.",
            ),
            citations = listOf(
                cite(ERC, "Traumatic cardiac arrest — Preventable deaths; Fig. 8; Table 8"),
            ),
        ),
        // 2 ----------------------------------------------------------------------------------------------
        Step(
            title = "Withholding resuscitation",
            timing = "On arrival at the patient",
            points = listOf(
                "ERC 2025 (expert consensus): consider withholding resuscitation if either checklist condition is present.",
                "ERC 2025: ACS and NAEMSP advise withholding with apnoea, pulselessness and no organised ECG activity, but neurologically intact survivors from this state are reported.",
                "ERC 2025: overall mortality is very high (96.2% in a review of 51,722 patients); a prehospital physician was associated with better survival.",
            ),
            checklistTitle = "Consider withholding (ERC 2025)",
            checklist = listOf(
                "No signs of life within the preceding 15 min",
                "Massive trauma incompatible with survival — decapitation, extensive cardiac destruction, massive head injury with loss of brain tissue",
            ),
            citations = listOf(
                cite(ERC, "Withholding resuscitation; Traumatic cardiac arrest introduction"),
            ),
        ),
        // 3 ----------------------------------------------------------------------------------------------
        Step(
            title = "Treat reversible causes simultaneously",
            timing = "Start CPR; reversible causes are treated alongside and take priority over chest compressions (ERC 2025)",
            points = listOf(
                "ERC 2025 Fig. 8: start CPR, and alongside it address reversible causes simultaneously, prioritising them over chest compressions; act by clinical priority, not in sequence.",
                "ERC 2025: in hypovolaemia, tamponade or tension pneumothorax, compressions may not work and must not delay treating the cause.",
                "ERC 2025: in exsanguination with SBP <90 mm Hg, circulation before intubation (CAB) was linked to lower 30-day mortality (17.5% vs 72.0%).",
                "ERC 2025: providers not trained to treat reversible causes follow standard ALS and prioritise rapid transport.",
            ),
            checklistTitle = "ERC 2025 algorithm — in parallel",
            checklist = listOf(
                "Start CPR — alongside, without delaying treatment of reversible causes",
                "Control external catastrophic haemorrhage",
                "Secure the airway and maximise oxygenation",
                "Bilateral chest decompression (thoracostomies)",
                "Relieve tamponade (penetrating chest injury)",
                "Proximal vascular control (manual aortic compression)",
                "Pelvic splint",
                "Blood products / massive haemorrhage protocol",
            ),
            citations = listOf(
                cite(ERC, "Fig. 8; Effectiveness of chest compressions; In-hospital care"),
            ),
        ),
        // 4 ----------------------------------------------------------------------------------------------
        Step(
            title = "Hypovolaemia",
            timing = "Immediately",
            points = listOf(
                "ERC 2025: compressible bleeding — elevation, direct or indirect pressure, pressure dressings, tourniquets and topical haemostatic agents.",
                "ERC 2025: non-compressible bleeding — pelvic splint, blood products, IV fluids and tranexamic acid while moving to surgical control.",
                "ERC 2025: immediate restoration of circulating volume with blood products is mandatory; prehospital plasma and red cells may improve survival.",
                "ERC 2025: aortic occlusion (thoracotomy and cross-clamp, or REBOA) is a last resort for infradiaphragmatic exsanguination; REBOA not recommended outside trials.",
                "ERC 2025: pre-alert the trauma centre ('Code Red') to activate massive transfusion and surgery; avoid long prehospital times. See Traumatic haemorrhage.",
            ),
            citations = listOf(
                cite(ERC, "Treatment of the reversible causes — Hypovolaemia; Pre-hospital care"),
            ),
        ),
        // 5 ----------------------------------------------------------------------------------------------
        Step(
            title = "Hypoxia and ventilation",
            timing = "In parallel",
            points = listOf(
                "ERC 2025: hypoxia comes from airway obstruction, traumatic asphyxia or impact brain apnoea; airway management and oxygenation can reverse hypoxic arrest.",
                "ERC 2025: impact brain apnoea is not necessarily unsurvivable — UK registry data: 15% survival, 90% of survivors with favourable outcome.",
                "ERC 2025: positive-pressure ventilation can cut venous return, worsen tamponade and create tension pneumothorax — use low tidal volumes and minimal PEEP.",
                "ERC 2025: monitor ventilation with capnography and aim for normocapnia.",
            ),
            citations = listOf(
                cite(ERC, "Treatment of the reversible causes — Hypoxia"),
            ),
        ),
        // 6 ----------------------------------------------------------------------------------------------
        Step(
            title = "Tension pneumothorax: bilateral thoracostomy",
            timing = "Immediately when suspected in arrest or severe hypotension",
            points = listOf(
                "ERC 2025: suspected tension pneumothorax with arrest or severe hypotension — immediate open thoracostomy.",
                "ERC 2025: bilateral thoracostomies in the 4th intercostal space, midaxillary line, extendable to a clamshell thoracotomy.",
                "ERC 2025: needle thoracocentesis if the provider cannot perform thoracostomy or instruments are not immediately available.",
                "ERC 2025: diagnose clinically or by POCUS — hypotension or arrest with absent unilateral breath sounds, emphysema, tracheal deviation, distended neck veins.",
            ),
            citations = listOf(
                cite(ERC, "Treatment of the reversible causes — Tension pneumothorax"),
            ),
        ),
        // 7 ----------------------------------------------------------------------------------------------
        Step(
            title = "Tamponade and resuscitative thoracotomy",
            timing = "Within 15 min of loss of vital signs (ERC 2025)",
            points = listOf(
                "ERC 2025: tamponade after penetrating chest injury — immediate resuscitative thoracotomy (clamshell or left anterolateral) by those with competence and equipment.",
                "EAST 2015 (strong): pulseless with signs of life after penetrating thoracic injury — emergency department thoracotomy (survival 21.3%).",
                "EAST 2015 (conditional for): penetrating thoracic without signs of life; penetrating extrathoracic with or without; blunt with signs of life. Conditional against: blunt without signs of life.",
                "EAST 2015: signs of life — spontaneous breathing, palpable pulse, measurable BP, movement, cardiac electrical activity or pupillary reactivity.",
                "EAST 2015: its evidence is mostly from major urban trauma centres and may not apply to community or rural centres; it informs, not replaces, clinical judgement.",
                "WTA 2024, no signs of life: pronounce dead after prehospital CPR >10 min (blunt) or >15 min (penetrating truncal); within these limits, cardiac ultrasound — organized wall motion → select by injury; tamponade → thoracotomy; neither → pronounce dead.",
                "WTA 2024: cardiac electrical activity as the only sign of life is also judged by cardiac ultrasound in this way. Profound shock = SBP <60 mm Hg; ROSC = organized cardiac activity with sustained SBP >70 mm Hg.",
                "WTA 2024: other signs of life, wall motion or profound shock without ROSC after resuscitation — by injury: thoracic → thoracotomy; abdominal or pelvic → thoracotomy if tamponade, otherwise REBOA may be considered where expertise exists, or thoracotomy.",
                "WTA 2024: isolated head, neck or extremity injury — continue resuscitation and external haemorrhage control; the former <5 min CPR limit for thoracotomy in neck and extremity injury was removed; pronounce dead if there is no response.",
                "ERC 2025: if any of the four Es is unmet, thoracotomy is futile and exposes the team to unnecessary risk.",
            ),
            checklistTitle = "ERC 2025 — the four Es",
            checklist = listOf(
                "Expertise — led by a trained, competent practitioner under governance",
                "Equipment — for thoracotomy and the intrathoracic findings",
                "Environment — adequate access; ideally an operating room",
                "Elapsed time — ≤15 min from loss of vital signs",
            ),
            citations = listOf(
                cite(ERC, "Cardiac tamponade and resuscitative thoracotomy"),
                cite(EAST_EDT, "Abstract — Results, Conclusion; Fig. 7; Using these guidelines in clinical practice"),
                cite(WTA, "Fig. 1 algorithm and notes; Sections A–F; Summary and conclusion"),
            ),
        ),
        // 8 ----------------------------------------------------------------------------------------------
        Step(
            title = "Drugs and stopping",
            timing = "Reassess after reversible causes are addressed",
            points = listOf(
                "ERC 2025: the role of adrenaline and vasopressors in traumatic arrest is unclear; at most a bridge — treating reversible causes takes preference.",
                "ERC 2025 (suggestion): stop if there is no ROSC 20 min after reversible causes are addressed, or no cardiac activity on ultrasound in PEA at 20 min.",
                "WTA 2024 procedure guide: after thoracotomy, organized cardiac activity with SBP <70 mm Hg → clamp the descending aorta and continue resuscitation; move to theatre if ROSC (organized rhythm, SBP >70 mm Hg).",
                "WTA 2024: no organized cardiac activity, tamponade, cardiac injury or air embolism → terminate; a brief period of open cardiac massage and aortic occlusion may be considered first. No ROSC after adequate aortic occlusion → terminate.",
                "ERC 2025: in one prehospital cohort, no one survived thoracotomy beyond 15 min of tamponade arrest or 5 min after exsanguination arrest.",
            ),
            checklistTitle = "Consider termination (ERC 2025)",
            checklist = listOf(
                "No ROSC 20 min after reversible causes have been addressed",
                "No ultrasound cardiac activity in PEA 20 min after reversible causes have been addressed",
            ),
            citations = listOf(
                cite(ERC, "The role of inotropes and vasopressors; Withholding resuscitation; Cardiac tamponade and resuscitative thoracotomy"),
                cite(WTA, "Fig. 2 procedure guide and notes; Section E"),
            ),
        ),
        // 9 ----------------------------------------------------------------------------------------------
        Step(
            title = "After ROSC and pregnancy",
            timing = "Immediately after ROSC",
            points = listOf(
                "ERC 2025: prehospital — immediate transport to an appropriate hospital; in hospital — damage control surgery and resuscitation.",
                "ERC 2025: early whole-body CT; blood pressure no higher than needed for a radial pulse (caution in TBI), for no more than 60 min.",
                "ERC 2025: active warming early; blood products, tranexamic acid and point-of-care coagulation monitoring for trauma-induced coagulopathy.",
                "EAST 2025 (conditional): ≥20 weeks' gestation undergoing resuscitative thoracotomy — resuscitative hysterotomy as soon as possible. See Trauma in pregnancy.",
                "MoHFW 2025: consider perimortem caesarean in non-hypovolaemic arrest beyond 20 weeks if there is no ROSC within 4 min of CPR.",
            ),
            citations = listOf(
                cite(ERC, "Fig. 8; Post resuscitation care after traumatic cardiac arrest"),
                cite(EAST_PREG, "Abstract — Results"),
                cite(MOH, "Ch. 13 Perimortem caesarean section (p. 59)"),
            ),
        ),
    ),
    notes = listOf(
        ProtocolNote(
            "AHA 2025: the special-circumstances guideline (Part 10) has no traumatic cardiac arrest section; this page follows ERC 2025, EAST 2015 and WTA 2024.",
            listOf(cite(AHA_SPECIAL, "Scope — topics covered"), cite(ERC, "Traumatic cardiac arrest")),
        ),
        ProtocolNote(
            "Thoracotomy selection differs: EAST 2015 uses mechanism and signs of life; WTA 2024 uses signs of life, prehospital CPR duration and cardiac ultrasound when signs of life are absent, then injury pattern; ERC 2025 uses the four Es, including ≤15 min elapsed since loss of vital signs.",
            listOf(cite(EAST_EDT, "Abstract — Conclusion"), cite(WTA, "Fig. 1; Summary and conclusion"), cite(ERC, "Cardiac tamponade and resuscitative thoracotomy")),
        ),
        ProtocolNote(
            "WTA 2024 replaced its 2012 algorithm: cardiac electrical activity as the only sign of life is now judged by cardiac ultrasound, REBOA is an alternative for extrathoracic injury, and for penetrating neck and extremity injury the <5 min CPR limit was removed and thoracotomy de-emphasised.",
            listOf(cite(WTA, "Summary and conclusion; Section F")),
        ),
        ProtocolNote(
            "REBOA: the European guideline 2023 suggests considering it for noncompressible bleeding as a bridge; ERC 2025 does not recommend it outside clinical trials.",
            listOf(cite(EU, "R21"), cite(ERC, "Treatment of the reversible causes — Hypovolaemia")),
        ),
        ProtocolNote(
            "India: no MoHFW or ICMR traumatic cardiac arrest algorithm was found (November 2026); the MoHFW 2025 trauma guideline addresses only perimortem caesarean in pregnancy.",
            listOf(cite(MOH, "Ch. 13 (p. 59)")),
        ),
    ),
    citations = listOf(cite(ERC), cite(EAST_EDT), cite(WTA), cite(EAST_PREG), cite(MOH), cite(AHA_SPECIAL), cite(EU)),
)

internal val traumaProtocols: List<Protocol> = listOf(majorTrauma, traumaticHaemorrhage, chestTrauma, traumaticCardiacArrest)
