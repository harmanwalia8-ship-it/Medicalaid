package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.WeightDose
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.ProtocolCategory
import com.medicalaid.app.content.protocols.ProtocolNote
import com.medicalaid.app.content.protocols.ProtocolTool
import com.medicalaid.app.content.protocols.Step
import com.medicalaid.app.content.protocols.StepDose

/*
 * Burns and environmental emergencies: burns, heat stroke, electrical injury and lightning. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them; every point is attributed
 *   ("MoHFW 2025: …", "ISBI 2016: …", "AHA 2025: …").
 * - Documents that already have a Source elsewhere are cited by their existing id (not duplicated):
 *   tool-mohfw-burns-2025 (MoHFW Standard Treatment Guidelines for Management of Burns 2025),
 *   tool-burns-fluids (ABA 2024 burn shock resuscitation guideline — abstract only reviewed here),
 *   tool-lund-browder (JTS Burn Care CPG, 10 June 2025 — the whole CPG is cited, not only its appendices),
 *   em-resus-aha-special-2025 (AHA 2025 Part 10), em-resus-erc-special-2025 (ERC 2025 Special Circumstances),
 *   mohfw-td (MoHFW UIP Td operational guidelines).
 * - No ICMR Standard Treatment Workflow for burns, heat illness or electrical injury was found on the ICMR STW portal.
 */

internal val environmentalSources: List<Source> = listOf(
    // Burns ------------------------------------------------------------------------------------
    Source(
        "em-env-isbi-2016", Authority.ISBI, "ISBI practice guidelines — Burns 2016;42(5):953–1021",
        "ISBI Practice Guidelines for Burn Care", "2016",
        "https://doi.org/10.1016/j.burns.2016.05.013",
        status = "Written for resource-limited and resource-abundant settings; Part 2 (Burns 2018;44(7):1617–1706) not reviewed",
    ),
    Source(
        "em-env-aba-referral-2022", Authority.ABA, "ABA guideline (one-page referral criteria)",
        "Guidelines for Burn Patient Referral: Advice on Transfer and Consultation", "2022",
        "https://ameriburn.org/burnreferral",
        status = "Copyright 2022; PDF linked from the ABA burn referral page",
    ),
    Source(
        "em-env-bba-first-aid-2018", Authority.Paper,
        "British Burn Association clinical practice guideline (Pre-Hospital Special Interest Group)",
        "First Aid Clinical Practice Guidelines", "2018",
        "https://www.britishburnassociation.org/wp-content/uploads/2017/06/BBA-First-Aid-Guideline-24.7.18.pdf",
        status = "Compiled April 2018; the BBA site refused automated download, so the text was checked against an NHS-hosted copy",
    ),
    // Heat ---------------------------------------------------------------------------------------
    Source(
        "em-env-ncdc-cooling-2024", Authority.MoHFW, "NPCCHH guideline (NCDC, Dte.GHS, MoHFW)",
        "Emergency Cooling for Severe Heat Related Illnesses", "2024",
        "https://ncdc.mohfw.gov.in/uploads/pdf/heat11.pdf",
        status = "March 2024; supplements the National Action Plan on Heat Related Illnesses (2021)",
    ),
    Source(
        "em-env-ncdc-nap-hri-2021", Authority.MoHFW, "National action plan (NPCCHH, NCDC, MoHFW)",
        "National Action Plan on Heat Related Illnesses", "2021",
        "https://ncdc.mohfw.gov.in/uploads/pdf/heat12.pdf",
    ),
    Source(
        "em-env-sccm-heat-2025", Authority.Paper, "SCCM guideline — Crit Care Med 2025;53(2):e490–e500",
        "Society of Critical Care Medicine Guidelines for the Treatment of Heat Stroke", "2025",
        "https://doi.org/10.1097/CCM.0000000000006551",
        status = "Executive summary Crit Care Med 2025;53(2):e483–e489. Full text not accessible; recommendations checked " +
            "against the PubMed abstract and SCCM's official guideline infographic (sccm.org)",
    ),
    // Electrical injury --------------------------------------------------------------------------
    Source(
        "em-env-pilecky-2019", Authority.Paper, "Retrospective cohort, 480 patients (Clin Res Cardiol 2019;108(8):901–908)",
        "Risk of cardiac arrhythmias after electrical accident: a single-center study of 480 patients", "2019",
        "https://doi.org/10.1007/s00392-019-01420-2",
    ),
    Source(
        "em-env-elec-review-2026", Authority.Paper, "Narrative review (Cureus 2026;18(4):e107162)",
        "Assessment and Management of Electrical Injuries in Adults in the Emergency Department", "2026",
        "https://doi.org/10.7759/cureus.107162",
        status = "Narrative review, literature to July 2025; states that no widely accepted guideline exists for electrical injury",
    ),
    Source(
        "em-env-icar-mci-2018", Authority.Paper, "ICAR MEDCOM evidence-based guideline (High Alt Med Biol 2018;19(2):131–140)",
        "Management of Multi-Casualty Incidents in Mountain Rescue: Evidence-Based Guidelines of the International " +
            "Commission for Mountain Emergency Medicine (ICAR MEDCOM)",
        "2018",
        "https://doi.org/10.1089/ham.2017.0143",
    ),
)

private const val ENV_MOHFW = "tool-mohfw-burns-2025"
private const val ENV_ABA24 = "tool-burns-fluids"
private const val ENV_JTS = "tool-lund-browder"
private const val ENV_ISBI = "em-env-isbi-2016"
private const val ENV_ABA_REF = "em-env-aba-referral-2022"
private const val ENV_BBA = "em-env-bba-first-aid-2018"
private const val ENV_TD = "mohfw-td"
private const val ENV_NCDC = "em-env-ncdc-cooling-2024"
private const val ENV_NAP = "em-env-ncdc-nap-hri-2021"
private const val ENV_SCCM = "em-env-sccm-heat-2025"
private const val ENV_AHA = "em-resus-aha-special-2025"
private const val ENV_ERC = "em-resus-erc-special-2025"
private const val ENV_PILECKY = "em-env-pilecky-2019"
private const val ENV_REVIEW = "em-env-elec-review-2026"
private const val ENV_ICAR = "em-env-icar-mci-2018"

internal val environmentalProtocols: List<Protocol> = listOf(
    // =============================================================================================
    // 1. Burns
    // =============================================================================================
    Protocol(
        id = "burns",
        category = ProtocolCategory.Environmental,
        title = "Burns",
        summary = "First aid cooling, primary survey, inhalation injury, %TBSA, fluid resuscitation and urine-output targets, " +
            "escharotomy, analgesia and burns-unit referral — attributed to MoHFW 2025, ISBI, ABA, JTS and BBA.",
        keywords = listOf(
            "burns", "burn", "scald", "fire", "flame burn", "thermal burn", "TBSA", "%TBSA", "BSA", "Parkland",
            "Parkland formula", "modified Brooke", "Safdarjung formula", "rule of nines", "rule of nine", "Lund Browder",
            "Lund-Browder", "palm method", "rule of hand", "burn shock", "fluid resuscitation", "inhalation injury",
            "smoke inhalation", "airway burn", "escharotomy", "fasciotomy", "kerosene burn", "stove burst", "cylinder blast",
            "burn unit", "NPPMBI", "jalna", "jale hue",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "First aid: stop the burning and cool",
                timing = "Immediately; cooling within 3 h of injury (BBA 2018)",
                points = listOf(
                    "MoHFW 2025: stop the burning (stop, drop and roll; smother with heavy cotton cloth, then remove it); remove burnt clothes, ornaments and rings.",
                    "MoHFW 2025: cool under running tap water — continuous cooling for the first 10 minutes reduces pain, depth and oedema; no high-pressure stream on the face.",
                    "BBA 2018: cool running tap water for 20 minutes, started within 3 hours of injury; cooling beyond this may cause hypothermia.",
                    "MoHFW 2025: prolonged cooling risks hypothermia in infants, young children and burns >25% TBSA — do not immerse. BBA 2018: cool the burn, warm the patient.",
                    "BBA 2018: cover with loose longitudinal strips of cling film or a clean non-adherent cloth. MoHFW 2025: wrap in a clean dry sheet.",
                ),
                checklistTitle = "Don'ts (MoHFW 2025; BBA 2018)",
                checklist = listOf(
                    "Ice or ice-cold water",
                    "Ghee, butter, toothpaste, ointments, creams, powders, gentian violet or calamine on the wound",
                    "Breaking blisters — leave to the burn specialist",
                    "Immersing infants, young children or burns >25% TBSA",
                    "Cling film wrapped circumferentially, or on the face",
                ),
                citations = listOf(
                    cite(ENV_MOHFW, "Ch. 2 Pre-hospital care — thermal burns (pp. 11–14)"),
                    cite(ENV_BBA, "Thermal burns — Stop, Remove, Cool, Warm, Cover"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Primary survey (ABCDE)",
                timing = "On arrival",
                points = listOf(
                    "ISBI 2016 and MoHFW 2025: evaluate systematically for the greatest threats to life first, as in any trauma; protect the cervical spine.",
                    "MoHFW 2025: humidified high-flow oxygen; two wide-bore IV cannulas through unburnt skin where possible, upper limbs preferred; check glucose, pupils and consciousness.",
                    "MoHFW 2025: deaths in the first hour are usually from other injuries, severe inhalation injury with CO poisoning, or electrocution — not the burn itself.",
                    "MoHFW 2025: burns >15% TBSA in adults, >10% in children, or at extremes of age are serious.",
                    "JTS 2025: avoid being distracted by the burn — look for other injuries; keep the patient warm, as >20% TBSA carries high hypothermia risk.",
                    "MoHFW 2025: record time and mechanism of injury, closed-space fire, first aid given, and an accurate weight before dressing.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(ENV_ISBI, "§3 Rec 1"),
                    cite(ENV_MOHFW, "Ch. 3 §A Primary survey (pp. 19–20); Ch. 4 §B Secondary survey (p. 23); §E Intravenous access (p. 30)"),
                    cite(ENV_JTS, "Initial burn survey"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Airway and inhalation injury",
                timing = "Before upper-airway oedema develops",
                points = listOf(
                    "ISBI 2016: normal oxygenation or chest X-ray does not exclude inhalation injury; hoarseness, carbonaceous sputum, wheeze and dyspnoea strongly suggest it.",
                    "MoHFW 2025: nurse propped up; humidified oxygen if SpO₂ <95%, titrated to SpO₂ 92–95% and PaO₂ >60 mm Hg. ISBI 2016: pulse oximetry and PaO₂ do not detect CO hypoxaemia, so with suspected CO give oxygen at once by non-rebreathing mask (8–15 L/min) whatever the SpO₂.",
                    "MoHFW 2025: intubate for failure to maintain airway, severe CO or cyanide toxicity, stridor, pharyngeal or laryngeal oedema, or facial with circumferential neck burns.",
                    "JTS 2025: intubate for GCS <8, symptomatic inhalation injury, deep facial or neck burns, or ≥40% TBSA (JTS pearls: when the patient cannot be closely monitored in an ICU or will be transferred); size 8 tube or larger in adults.",
                    "MoHFW 2025 and ISBI 2016: CO — high-flow oxygen for at least 6 h; cyanide — hydroxocobalamin immediately if available. See the Carbon monoxide poisoning protocol.",
                    "ISBI 2016: prophylactic antibiotics and corticosteroids are not indicated for smoke inhalation; use lung-protective ventilation.",
                ),
                checklistTitle = "Suspect inhalation injury (MoHFW 2025; ISBI 2016)",
                checklist = listOf(
                    "Fire or smoke exposure in an enclosed space",
                    "Reduced consciousness",
                    "Facial burns or singed facial or nasal hair",
                    "Soot in the mouth or carbonaceous sputum",
                    "Hoarse voice or stridor",
                    "Wheeze or dyspnoea",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(ENV_ISBI, "§4 Rec 2–5; §4.2.1; §4.3.1"),
                    cite(ENV_MOHFW, "Ch. 4 §D (pp. 29–30); Ch. 5 Airway management, CO and cyanide poisoning (pp. 38–42)"),
                    cite(ENV_JTS, "Clinical pearls (p. 5); Initial burn survey; Inhalation injury"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Estimate burn size (%TBSA)",
                points = listOf(
                    "ABA 2022 and JTS 2025: superficial (first-degree) burns — red, dry, blanching, no blisters, like sunburn — are NOT counted in %TBSA.",
                    "MoHFW 2025: Wallace rule of nines for adults; Lund–Browder chart for children.",
                    "MoHFW 2025: small or patchy burns — the patient's palm with fingers closed is about 1%. ABA 2022: the entire palmar surface is about 1%.",
                    "ISBI 2016: in infants and children the head and lower limbs are a different share of body surface — use the Lund–Browder chart.",
                    "JTS 2025: rule of nines first, then recalculate with Lund–Browder once wounds are cleaned; re-map after each transfer, as burns may deepen.",
                ),
                tool = ProtocolTool.BurnsChart,
                citations = listOf(
                    cite(ENV_ABA_REF, "Burn severity determination"),
                    cite(ENV_JTS, "Acute resuscitation — assessment; Appendices B–C; Appendix D item 5"),
                    cite(ENV_MOHFW, "Ch. 4 §B Figs 4–6, Table 1 (pp. 23–26)"),
                    cite(ENV_ISBI, "§3 Rec 2 (§3.4)"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Formal fluid resuscitation: who and how much",
                timing = "Hours counted from the time of burn, not arrival (JTS 2025)",
                points = listOf(
                    "MoHFW 2025: IV formula resuscitation for burns >15% TBSA in adults and ≥10% in children; below 15%, oral fluids of at least 2 L.",
                    "ISBI 2016: formal resuscitation for adults >20% TBSA and children >10%. JTS 2025: ≥20% TBSA; at 10–20%, maintenance IV fluid with close monitoring.",
                    "ISBI 2016: Ringer's lactate 2–4 mL/kg/%TBSA in the first 24 h. Parkland: 4 mL/kg/%TBSA (Review 2026). ABA 2024 (adults ≥20%): start at 2 mL/kg/%TBSA.",
                    "MoHFW 2025 (Safdarjung / modified Brooke): Ringer's lactate 2 mL/kg/%TBSA plus DNS maintenance; or modified Parkland, Ringer's lactate 3 mL/kg/%TBSA.",
                    "MoHFW 2025: half the 24-h volume in the first 8 h, the rest over 16 h. ISBI 2016: after delayed arrival, give the missed volume faster.",
                    "Children — MoHFW 2025: RL 2 mL/kg/%TBSA plus N/2 DNS or Isolyte P by 4-2-1 rule. JTS 2025: 3 mL/kg/%TBSA plus untitrated D5LR maintenance (<13 y).",
                ),
                doses = listOf(
                    StepDose(
                        label = "DNS maintenance, adults — MoHFW 2025 Safdarjung formula",
                        dose = WeightDose(50.0, 50.0, unit = "mL", per = "per day, first and second 24 h"),
                        note = "With Ringer's lactate 2 mL/kg/%TBSA in the first 24 h and 1 mL/kg/%TBSA in the second 24 h; the STG text also gives 2500 mL DNS",
                    ),
                    StepDose(
                        label = "Oral resuscitation when IV fluid is not practical — ISBI 2016",
                        dose = WeightDose(150.0, 150.0, unit = "mL", per = "per 24 h, for 2 days"),
                        note = "15% of body weight in local-diet liquids every 24 h, with 5 g table salt (one level teaspoon) per litre; frequent small sips (≤50 mL). " +
                            "ISBI 2016: burns <30% TBSA are candidates for oral resuscitation; in larger burns oral intake only offsets IV volume. " +
                            "JTS 2025: by mouth only if awake and alert; avoid with abdominal injury, vomiting or burns >40% TBSA.",
                    ),
                ),
                citations = listOf(
                    cite(ENV_MOHFW, "Ch. 4 §E Fluid resuscitation (pp. 30–32)"),
                    cite(ENV_ISBI, "§3 Rec 3 (§3.5); §5 Rec 1–3 and FAQs (pp. 968–969)"),
                    cite(ENV_REVIEW, "Table 3"),
                    cite(ENV_ABA24, "Abstract"),
                    cite(ENV_JTS, "Acute resuscitation; Alternative strategy: enteral resuscitation (p. 10); Considerations for pediatric patients; Appendix D item 7"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Titrate to urine output; avoid over-resuscitation",
                timing = "Hourly urine output",
                points = listOf(
                    "Urine targets — MoHFW 2025: 0.5–1 mL/kg/h adults, 1 mL/kg/h children. ISBI 2016: 0.3–0.5 mL/kg/h adults, 1 mL/kg/h children. JTS 2025: 30–50 mL/h adults.",
                    "ISBI 2016: formulas are only a guide; urine output may approach anuria in the first 3 h whatever the infusion rate.",
                    "JTS 2025: if output stays above 50 or below 30 mL/h, change the rate by 20% and reassess after 2 h; no boluses unless MAP <65 mm Hg.",
                    "JTS 2025: >250 mL/kg in the first 24 h raises the risk of abdominal and extremity compartment syndromes; in intubated patients, measure bladder pressure every 4 h if the first-24-h volume exceeds 200 mL/kg.",
                    "ABA 2024: consider human albumin, especially in larger burns, to lower volumes; monitor intra-abdominal and intraocular pressure selectively.",
                    "MoHFW 2025: lactate, where available, guides adequacy; delayed presentation, inhalation injury and electrical burns need more fluid.",
                ),
                citations = listOf(
                    cite(ENV_MOHFW, "Ch. 4 §E Planning of fluid resuscitation; urine output targets (pp. 31–32)"),
                    cite(ENV_ISBI, "§5 Rec 4 and FAQs (pp. 969–970)"),
                    cite(ENV_JTS, "Acute resuscitation; Titration and goals of fluid resuscitation; bladder pressure"),
                    cite(ENV_ABA24, "Abstract"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Circumferential burns: escharotomy",
                timing = "Need usually appears within 48 h (JTS 2025)",
                points = listOf(
                    "ISBI 2016: escharotomy when circumferential eschar compromises limb circulation, or chest or neck eschar compromises breathing; abdominal escharotomy for intra-abdominal hypertension.",
                    "JTS 2025: check pulses hourly, by Doppler if available; exclude hypovolaemia before escharotomy; elevate burned limbs above heart level.",
                    "MoHFW 2025: falling Doppler signal despite adequate BP, or tissue pressure >25 mm Hg, means escharotomy or fasciotomy is needed; remove constricting objects.",
                    "ISBI 2016: longitudinal mid-axial (medial and lateral) incisions, near but not over the neurovascular bundles (dorsum of hands and feet), normal skin to normal skin (or joint above to joint below), down to healthy tissue.",
                    "ISBI 2016: inexperienced staff should never attempt escharotomy. MoHFW 2025: surgical burn management, escharotomy included, preferably in a tertiary centre with a dedicated burn unit.",
                    "JTS 2025: escharotomy is painful — moderate sedation and IV opioids are often needed; check pulses hourly for at least 12–24 h afterwards.",
                    "ISBI 2016: primary fasciotomy is rarely indicated except in high-voltage electrical injury.",
                ),
                checklistTitle = "Warning signs (JTS 2025)",
                checklist = listOf(
                    "Pulses or Doppler signals diminishing on hourly checks",
                    "Circumferential deep partial- or full-thickness limb burn",
                    "Circumferential full-thickness chest burn with rapid shallow breathing or poor chest excursion",
                    "Difficulty ventilating an intubated patient",
                ),
                citations = listOf(
                    cite(ENV_ISBI, "§6 Rec 1–4 (pp. 970–974); Rec 3 (§6.4, §6.4.2)"),
                    cite(ENV_JTS, "Circumferential burns, escharotomy & extremity compartment syndrome"),
                    cite(ENV_MOHFW, "Ch. 5 §G Surgical management; escharotomy and fasciotomy (p. 42)"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Pain, tetanus, wound cover and antibiotics",
                points = listOf(
                    "MoHFW 2025: IV opioids for severe background and procedural pain in the acute phase; avoid IM or SC injections during the shock phase.",
                    "MoHFW 2025: tetanus toxoid 0.5 mL IM if not already given. ISBI 2016: unknown or inadequate immunisation — tetanus toxoid plus tetanus immune globulin (TIG); TIG may be less available in resource-limited settings, and IV immune globulin may be used if TIG is unavailable.",
                    "MoHFW 2025 and JTS 2025: no routine prophylactic systemic antibiotics; MoHFW exceptions — surgery such as escharotomy or fasciotomy, gangrene, associated trauma.",
                    "JTS 2025: do not debride blisters until at a facility with surgical capability; meanwhile cover with loose dry gauze or a clean sheet.",
                    "MoHFW 2025: debride large, ruptured or joint blisters; small (<5 cm) may stay; non-adherent paraffin gauze, then 1% silver sulfadiazine or a silver dressing.",
                ),
                citations = listOf(
                    cite(ENV_MOHFW, "Ch. 3 (TT 0.5 mL); Ch. 4 §F and Other measures (pp. 33–36); §H Table 3; §I Analgesia (p. 47)"),
                    cite(ENV_ISBI, "§3 Rec 4 (§3.6, §3.6.3)"),
                    cite(ENV_JTS, "Initial burn survey (blisters); Antimicrobial prophylaxis"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Refer to a burns unit",
                timing = "After stabilisation; airway cases without delay",
                points = listOf(
                    "MoHFW 2025: a secondary hospital without surgeon, anaesthetist and theatre should start fluids and refer; resuscitation continues in the ambulance.",
                    "MoHFW 2025: patients needing emergent airway management go to a tertiary centre without delay.",
                    "ABA 2022 (consult, consider transfer): full-thickness or ≥10% partial-thickness burns, deep burns of key areas, comorbidity, trauma, poor pain control, inhalation, chemical, high-voltage or lightning injury.",
                    "ABA 2022: all children (≤14 years or <30 kg) may benefit from burns-centre referral.",
                    "ISBI 2016: refer partial-thickness >10% TBSA; burns of face, hands, feet, genitals, perineum or major joints; and full-thickness burns of any size.",
                ),
                checklistTitle = "Tertiary referral after stabilisation (MoHFW 2025)",
                checklist = listOf(
                    "Deep partial-thickness burns >40% TBSA",
                    "Full-thickness burns >10% TBSA",
                    "Extensive electrical burn with vascular compromise",
                    "Burns of hands, feet, face, genitalia, perineum or any joint",
                    "Suspected inhalation injury",
                    "Chemical burns with eye damage",
                    "Burns with polytrauma",
                ),
                citations = listOf(
                    cite(ENV_MOHFW, "Ch. 4 Other measures — referral to tertiary care (p. 36)"),
                    cite(ENV_ABA_REF, "Immediate consultation with consideration for transfer"),
                    cite(ENV_ISBI, "§3 Rec 2 (§3.4)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Cooling time differs: ISBI 2016 about 3–5 minutes of cool, not cold, water; MoHFW 2025 continuous cooling for the first 10 minutes; " +
                    "BBA 2018 20 minutes of cool running water within 3 hours of injury.",
                listOf(
                    cite(ENV_ISBI, "§3.2.5 Exposure"), cite(ENV_MOHFW, "Ch. 2 Cooling the burn wound (p. 13)"),
                    cite(ENV_BBA, "Thermal burns — Cool"),
                ),
            ),
            ProtocolNote(
                "Threshold for formal resuscitation differs: MoHFW 2025 >15% TBSA in adults and ≥10% in children; ISBI 2016 >20% and >10%; JTS 2025 ≥20%. The burns chart follows MoHFW.",
                listOf(
                    cite(ENV_MOHFW, "Ch. 4 §E (p. 30)"), cite(ENV_ISBI, "§5 Rec 1"),
                    cite(ENV_JTS, "Acute resuscitation — assessment"),
                ),
            ),
            ProtocolNote(
                "Starting volumes differ: Parkland 4 mL/kg/%TBSA; ABA 2024 2 mL/kg/%TBSA (adults ≥20%); MoHFW 2025 Safdarjung 2 mL plus DNS, or modified Parkland 3 mL; " +
                    "JTS 2025 adults use the Rule of Tens (10 mL/h × %TBSA, plus 100 mL/h per 10 kg above 80 kg).",
                listOf(
                    cite(ENV_REVIEW, "Table 3"), cite(ENV_ABA24, "Abstract"),
                    cite(ENV_MOHFW, "Ch. 4 §E (pp. 31–32)"), cite(ENV_JTS, "Acute resuscitation"),
                ),
            ),
            ProtocolNote(
                "Adult urine-output targets differ: ISBI 2016 0.3–0.5 mL/kg/h (the panel acknowledges these are on the low side); MoHFW 2025 0.5–1 mL/kg/h; JTS 2025 30–50 mL/h.",
                listOf(cite(ENV_ISBI, "§5 Rec 4 and FAQs"), cite(ENV_MOHFW, "Ch. 4 §E (p. 32)"), cite(ENV_JTS, "Titration and goals")),
            ),
            ProtocolNote(
                "Colloid is debated: ABA 2024 recommends considering albumin; ISBI 2016 calls the value of colloids unresolved and costly; MoHFW 2025 considers colloid " +
                    "0.3–0.5 mL/kg/%TBSA in the second 24 h if urine output stays low; JTS 2025 uses colloid rescue from 8–12 h.",
                listOf(
                    cite(ENV_ABA24, "Abstract"), cite(ENV_ISBI, "§5 Rec 2 (§5.2)"),
                    cite(ENV_MOHFW, "Ch. 4 §E (p. 32)"), cite(ENV_JTS, "Colloid rescue recommendations"),
                ),
            ),
            ProtocolNote(
                "ABA 2024 could make no recommendation for or against high-dose vitamin C, fresh frozen plasma, early CRRT or vasopressors as adjuncts in burn shock.",
                listOf(cite(ENV_ABA24, "Abstract")),
            ),
            ProtocolNote(
                "Escharotomy analgesia differs: MoHFW 2025 states escharotomy is performed without anaesthesia; JTS 2025 calls it painful and often needing sedation and IV opioids.",
                listOf(cite(ENV_MOHFW, "Ch. 5 §G (p. 42)"), cite(ENV_JTS, "Escharotomy")),
            ),
            ProtocolNote(
                "The MoHFW 2025 STG names tetanus toxoid (TT); under the Universal Immunization Programme, MoHFW has replaced TT with Td vaccine.",
                listOf(cite(ENV_MOHFW, "Ch. 3; Ch. 4 Other measures"), cite(ENV_TD, "Key messages for ANMs")),
            ),
            ProtocolNote(
                "Scope: ABA 2024 was reviewed from its abstract only; ISBI Part 2 (2018) was not reviewed. Adults unless a point says children.",
                listOf(cite(ENV_ABA24, "Abstract"), cite(ENV_ISBI)),
            ),
        ),
        citations = listOf(
            cite(ENV_MOHFW), cite(ENV_ISBI), cite(ENV_ABA24), cite(ENV_ABA_REF), cite(ENV_JTS), cite(ENV_BBA), cite(ENV_REVIEW),
        ),
    ),

    // =============================================================================================
    // 2. Heat stroke
    // =============================================================================================
    Protocol(
        id = "heat-stroke",
        category = ProtocolCategory.Environmental,
        title = "Heat stroke",
        summary = "Recognition (classic vs exertional), rapid cooling ranked by cooling rate, when to stop, what not to give, " +
            "supportive care and complications — attributed to NCDC/MoHFW, AHA 2025, ERC 2025 and SCCM 2025.",
        keywords = listOf(
            "heat stroke", "heatstroke", "sunstroke", "loo", "lu", "loo lagna", "heat wave", "heatwave", "hyperthermia",
            "exertional heat stroke", "EHS", "classic heat stroke", "CHS", "non-exertional heat stroke", "heat exhaustion",
            "heat-related illness", "HRI", "cold water immersion", "CWI", "ice water immersion", "body bag cooling",
            "NPCCHH", "garmi",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise heat stroke",
                timing = "Recognise and start treatment within 5 min (NCDC 2024)",
                points = listOf(
                    "ERC 2025: triad of core temperature >40 °C, neurological symptoms, and recent passive heat exposure (classic) or excessive exercise (exertional).",
                    "SCCM 2025: core temperature >40 °C with central nervous system symptoms. NAP 2021 (MoHFW): ≥40 °C with altered mental status, seizures or coma.",
                    "NCDC 2024: exertional — sporadic, active people, rapid rise, usually sweating. Classic — heat waves, elderly, children, chronically ill, slower rise, skin may be dry.",
                    "NCDC 2024: the distinction matters little for starting treatment — immediate cooling and organ support are the goal in both.",
                    "ERC 2025: mortality about 10%, approaching 33% with hypotension; outcome worsens if core temperature stays above 40.5 °C.",
                ),
                checklistTitle = "Heat alert in the ED (NCDC 2024)",
                checklist = listOf(
                    "Heat-wave season, heat advisory or high heat index",
                    "Temperature >40 °C",
                    "Altered mental status, confusion, unresponsiveness or seizure",
                    "Recent environmental heat exposure or strenuous exertion",
                    "Tachycardia, tachypnoea ± hypotension",
                    "Flushed or warm skin, with or without sweating",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(ENV_ERC, "Hyperthermia; Table 3 (heat stroke)"),
                    cite(ENV_SCCM, "Definition (abstract; SCCM infographic)"),
                    cite(ENV_NAP, "Ch. 2 Clinical manifestations of HRI (pp. 2–3)"),
                    cite(ENV_NCDC, "Ch. 1 Types of heat stroke (p. 2); Guiding principles (p. 3); Annexure 1 (p. 27)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Core temperature and other causes",
                timing = "In parallel with cooling",
                points = listOf(
                    "ERC 2025: measure core temperature centrally (tympanic, rectal or oesophageal) to guide treatment. NCDC 2024: flexible rectal probe inserted at least 10 cm.",
                    "NCDC 2024: monitor, place large-bore IVs, send labs, ECG and chest X-ray — actions run simultaneously, not before cooling.",
                    "NCDC 2024: consider neuroleptic malignant syndrome, malignant hyperthermia, serotonin syndrome, hyperthyroidism, sepsis, drug overdose and hypoglycaemia.",
                    "NCDC 2024: suspected exertional heat stroke with rectal temperature below 40.5 °C — keep cooling with ice-wet towels and check point-of-care sodium and glucose.",
                    "AHA 2025: life-threatening hyperthermia also follows drug or medication overdose; malignant hyperthermia and sepsis are outside its recommendations.",
                ),
                citations = listOf(
                    cite(ENV_ERC, "Hyperthermia (core temperature measurement)"),
                    cite(ENV_NCDC, "Ch. 1 Rectal temperature measurement (p. 4); Onset of heatstroke (p. 7); Annexure 1 (p. 27)"),
                    cite(ENV_AHA, "Hyperthermia — Introduction (S609)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Cool first: fastest method available",
                timing = "Start cooling within 10 min; reach target within 30 min (NCDC 2024; SCCM 2025)",
                points = listOf(
                    "AHA 2025: start active cooling alongside resuscitation; ice-water immersion (1–5 °C) preferred; otherwise tepid-water (8–17 °C) immersion or tarp-assisted ice-water cooling.",
                    "SCCM 2025 (strong recommendation, very low certainty): active over passive cooling. Good practice statements: prioritise ice- or cold-water immersion; aim ≥0.155 °C/min and reach target temperature within 30 min of recognition.",
                    "AHA 2025: cool at least 0.15 °C/min — slower cooling was associated with higher mortality. ERC 2025: neck-down immersion achieves 0.2–0.35 °C/min.",
                    "NCDC 2024 ED order: (1) cold-water immersion or body-bag cooling; (2) ice packs or chilled sheets and towels; (3) mist water with a direct fan.",
                    "NCDC 2024: 'cool first, transport second'; do not delay cooling to undress; keep immersion water below 15 °C and stir it continuously.",
                    "SCCM 2025 ranking, fastest first: ice-, colder- and cold-water immersion; tarp; cold shower; ice sheets; vests; fans; cold IV saline; ice packs; misting and fanning.",
                ),
                citations = listOf(
                    cite(ENV_AHA, "Hyperthermia — adult Rec 1–4 (S610)"),
                    cite(ENV_SCCM, "Recommendation 1 and good practice statements; cooling-rate figure (SCCM infographic)"),
                    cite(ENV_ERC, "Table 3 (heat stroke)"),
                    cite(ENV_NCDC, "Ch. 1 Guiding principles and primary goal (pp. 3–4); Ch. 2A Ice/cold water immersion (pp. 9–10); Annexure 1 (p. 27)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Classic heat stroke, elderly and comorbid patients",
                points = listOf(
                    "NCDC 2024: immersion can hamper cardiac monitoring and resuscitation; in elderly or comorbid classic heat stroke, prefer evaporative plus convective cooling, adding ice.",
                    "NCDC 2024: cool the whole body, front and back; ice packs to neck, axilla and groin add little and are supplementary only.",
                    "NCDC 2024: body-bag cooling (ice and cold water up to the neck) keeps the chest dry for monitoring; emerging evidence in classic heat stroke.",
                    "SCCM 2025: use similar cooling strategies for classic and exertional heat stroke. AHA 2025 prefers ice-water immersion for exertional, environmental or drug-induced hyperthermia, without separating classic from exertional heat stroke.",
                    "NCDC 2024: air-conditioned rooms alone cool at 0.03–0.06 °C/min — unacceptable as the only method.",
                ),
                citations = listOf(
                    cite(ENV_NCDC, "Ch. 1 Challenges with rapid cooling (pp. 5–6); Classic heat stroke (p. 8); Cooling rates (p. 5); Ch. 2 Body bag cooling (p. 12)"),
                    cite(ENV_SCCM, "Good practice statements (SCCM infographic)"),
                    cite(ENV_AHA, "Hyperthermia — Introduction (S609); adult Rec 2"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "When to stop cooling",
                timing = "Continuous core temperature monitoring",
                points = listOf(
                    "AHA 2025: stop active cooling at core temperature 38.6 °C; in a trial of 10 healthy volunteers, removal at 38.6 °C rather than 37.5 °C lowered the risk of overcooling, which could cause coagulopathy and haemodynamic effects.",
                    "ERC 2025: cool to below 39 °C, preferably 38.5–38.0 °C; continue immersion until symptoms resolve or for a reasonable time, e.g. 15 min.",
                    "NCDC 2024: end point 38–39 °C; without rectal thermometry, immerse 10–15 min (CWI protocol) or 15–20 min (exertional principles), or until shivering.",
                    "NAP 2021 (MoHFW): temperature every 15–30 min; do not overcorrect below 38 °C; watch for hypothermia.",
                    "NCDC 2024: treat intense shivering with short-acting benzodiazepines; consider deep sedation followed by neuromuscular blockade to cut heat production. NAP 2021: paralyse only if the patient is intubated.",
                ),
                citations = listOf(
                    cite(ENV_AHA, "Hyperthermia — adult Rec 5 and supportive text (S610)"),
                    cite(ENV_ERC, "Table 3 (heat stroke)"),
                    cite(ENV_NCDC, "Ch. 1 Primary goal (p. 4); Challenges — shivering (pp. 5–6); EHS (p. 7); Ch. 2A end point (p. 10); Annexure 1"),
                    cite(ENV_NAP, "Ch. 4B Emergency department workflow (p. 7)"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "What not to use",
                points = listOf(
                    "SCCM 2025 (strong recommendation, very low certainty): do not use dantrolene. AHA 2025: dantrolene is not useful for hyperthermia other than malignant hyperthermia.",
                    "SCCM 2025: avoid routine paracetamol, NSAIDs and salicylates. NCDC 2024: antipyretics are not effective. ERC 2025: no medication lowers core temperature.",
                    "SCCM 2025: prophylactic antibiotics or antiseizure drugs only within research.",
                ),
                checklistTitle = "Don'ts (SCCM 2025; NCDC 2024)",
                checklist = listOf(
                    "Antipyretics — paracetamol, NSAIDs, aspirin (also NAP 2021)",
                    "Dantrolene",
                    "Delaying cooling for tests, transport or undressing",
                    "Air-conditioned room alone as the only cooling",
                    "Ice packs to neck, axilla and groin as the primary method",
                    "Prophylactic antibiotics or antiseizure drugs outside research",
                ),
                citations = listOf(
                    cite(ENV_SCCM, "Recommendation 2 and good practice statements (SCCM infographic)"),
                    cite(ENV_AHA, "Hyperthermia — adult Rec 6"),
                    cite(ENV_NCDC, "Ch. 1 Primary goal (pp. 4–5); Cooling rates (p. 5)"),
                    cite(ENV_ERC, "Table 3 (heat stroke)"),
                    cite(ENV_NAP, "Ch. 4B (avoid paracetamol or NSAIDs)"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Supportive care and fluids",
                points = listOf(
                    "NAP 2021 (MoHFW): secure the airway if needed; give IV fluids judiciously, considering premorbid status, and watch for pulmonary oedema.",
                    "ERC 2025: isotonic IV fluids; if Na ≤130 mmol/L, hypertonic saline (e.g. up to 3 × 100 mL 3% NaCl, 10 min apart); large volumes may be needed.",
                    "NAP 2021: treat seizures with benzodiazepines; monitor ECG, GCS, coagulation and ABG for metabolic acidosis.",
                    "NCDC 2024: internal cooling (chilled IV fluids, cold bladder irrigation) supplements external cooling, it does not replace it.",
                    "ERC 2025: critically ill patients need ICU care and may need external or internal temperature-management devices.",
                ),
                citations = listOf(
                    cite(ENV_NAP, "Ch. 4B Emergency department workflow (p. 7)"),
                    cite(ENV_ERC, "Table 3 (heat stroke)"),
                    cite(ENV_NCDC, "Annexure 1 (internal cooling, supplementary)"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Look for complications",
                points = listOf(
                    "NCDC 2024: exertional — rhabdomyolysis frequent, renal failure common, marked liver injury and DIC, hypoglycaemia, hyperkalaemia, hypocalcaemia. Classic — respiratory alkalosis, milder organ injury.",
                    "NAP 2021: ABG, glucose, sodium, potassium, calcium, liver tests, coagulation, blood count, renal function, CK, ECG and chest X-ray.",
                    "NAP 2021: AST and ALT may reach thousands, peaking around 48 h; any coagulation derangement signals a poor prognosis.",
                    "NAP 2021: AKI from volume depletion, rhabdomyolysis or direct thermal injury; CT head once stable if neurology does not improve.",
                    "NCDC 2024: admit, usually to ICU; consider liver transplantation referral in acute liver failure.",
                ),
                checklistTitle = "Complications to screen for",
                checklist = listOf(
                    "Rhabdomyolysis (CK, dark urine)",
                    "Acute kidney injury",
                    "DIC and coagulopathy",
                    "Hypoglycaemia",
                    "Acute liver injury",
                    "Potassium, calcium and sodium disorders",
                    "Arrhythmia",
                    "Pulmonary oedema from fluids; hypothermia from overcooling",
                ),
                citations = listOf(
                    cite(ENV_NCDC, "Ch. 1 Types of heat stroke (p. 2); Annexure 1 (p. 27)"),
                    cite(ENV_NAP, "Ch. 3 Heatstroke workup (pp. 4–5); Ch. 4B (p. 7)"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "After recovery",
                points = listOf(
                    "NCDC 2024: review 7 days after discharge with repeat tests of affected organs; no exercise for at least 7 days.",
                    "NCDC 2024: once cleared, acclimatise gradually over 2–4 weeks; if symptoms recur after exertional heat stroke, consider a heat tolerance test.",
                    "NCDC 2024: avoid peak heat, keep hydrated, avoid alcohol and caffeine if at risk, and review drugs causing bradycardia, diuresis or reduced sweating.",
                ),
                citations = listOf(
                    cite(ENV_NCDC, "Ch. 1 Follow-up of heat stroke treatment (p. 6)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Cooling method differs: the NAP 2021 (MoHFW) emergency workflow uses wet sheets, ice packs, mist fans and tepid sponging; NCDC 2024, AHA 2025, ERC 2025 " +
                    "and SCCM 2025 prioritise ice- or cold-water immersion.",
                listOf(
                    cite(ENV_NAP, "Ch. 4B (p. 7)"), cite(ENV_NCDC, "Annexure 1"), cite(ENV_AHA, "Hyperthermia — adult Rec 2"),
                    cite(ENV_ERC, "Table 3"), cite(ENV_SCCM, "Recommendation 1"),
                ),
            ),
            ProtocolNote(
                "Classic heat stroke is debated: NCDC 2024 prefers evaporative–convective cooling in elderly or comorbid patients; SCCM 2025 advises similar strategies for " +
                    "both types; AHA 2025 prefers ice-water immersion.",
                listOf(cite(ENV_NCDC, "Classic heat stroke (p. 8)"), cite(ENV_SCCM, "Good practice statements"), cite(ENV_AHA, "Hyperthermia — adult Rec 2")),
            ),
            ProtocolNote(
                "Stopping temperature differs: AHA 2025 38.6 °C; ERC 2025 below 39 °C, preferably 38.5–38.0 °C; NCDC 2024 38–39 °C; NAP 2021 not below 38 °C.",
                listOf(cite(ENV_AHA, "Hyperthermia — adult Rec 5"), cite(ENV_ERC, "Table 3"), cite(ENV_NCDC, "Primary goal (p. 4)"), cite(ENV_NAP, "Ch. 4B")),
            ),
            ProtocolNote(
                "NAP 2021 tables advise aggressive cooling to 39 °C if there is no response in 30 minutes; NCDC 2024, which supplements it, and the international guidelines " +
                    "call for immediate cooling that reaches target within 30 minutes.",
                listOf(cite(ENV_NAP, "Ch. 2 Summary of spectrum of HRI (p. 3)"), cite(ENV_NCDC, "Guiding principles (p. 3); Annexure 1"), cite(ENV_SCCM, "Good practice statements")),
            ),
            ProtocolNote(
                "Cold IV saline: NAP 2021 suggests a cold saline drip at PHC level before referral; SCCM 2025 ranks cold IV saline among the slower methods, and NCDC 2024 treats internal cooling as supplementary.",
                listOf(cite(ENV_NAP, "Ch. 4A PHC workflow (p. 6)"), cite(ENV_SCCM, "Cooling-rate figure (SCCM infographic)"), cite(ENV_NCDC, "Annexure 1")),
            ),
            ProtocolNote(
                "Scope: SCCM 2025 was checked against its abstract and SCCM's official infographic only. Adults unless a point says otherwise.",
                listOf(cite(ENV_SCCM)),
            ),
        ),
        citations = listOf(cite(ENV_NCDC), cite(ENV_NAP), cite(ENV_AHA), cite(ENV_ERC), cite(ENV_SCCM)),
    ),

    // =============================================================================================
    // 3. Electrical injury and lightning
    // =============================================================================================
    Protocol(
        id = "electrical-injury",
        category = ProtocolCategory.Environmental,
        title = "Electrical injury and lightning",
        summary = "Scene safety, low vs high voltage and lightning, ECG and who needs monitoring, hidden deep tissue injury, compartment syndrome, " +
            "rhabdomyolysis and urine-output targets — attributed to MoHFW 2025, JTS, ABA, AHA 2025, ICAR and reviews.",
        keywords = listOf(
            "electrical injury", "electric shock", "electrocution", "current", "current lagna", "karant lagna", "high voltage",
            "low voltage", "HVEI", "LVEI", "electrical burn", "electric burn", "transformer", "power line", "lightning",
            "lightning strike", "bijli girna", "reverse triage", "myoglobinuria", "pigmenturia", "rhabdomyolysis",
            "compartment syndrome", "fasciotomy",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Scene safety, resuscitation and first aid",
                timing = "Immediately",
                points = listOf(
                    "MoHFW 2025: switch off the supply first; move the victim with a dry non-conducting object such as a wooden stick or chair; rescuers must not become victims.",
                    "AHA 2025: cardiac arrest from electrical injury — standard BLS and ALS with rapid defibrillation; good outcomes are reported after prolonged CPR (up to 70 min).",
                    "MoHFW 2025: look for head, chest, abdominal and limb injuries, especially after a fall from a pole or rooftop.",
                    "BBA 2018: once the source is controlled, cool burns with cool running water for 20 minutes within 3 hours.",
                    "MoHFW 2025: arrange direct transfer to a burn centre or burns ICU.",
                ),
                citations = listOf(
                    cite(ENV_MOHFW, "Ch. 2 Pre-hospital care — electrical burns (pp. 15–16)"),
                    cite(ENV_AHA, "Electrocution — Rec 1 and supportive text (S600)"),
                    cite(ENV_BBA, "Electrical burns"),
                    cite(ENV_JTS, "Electric injury, items 1–2"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Classify: low voltage, high voltage, lightning",
                points = listOf(
                    "ABA 2022: high voltage is ≥1000 V (JTS 2025: >1000 V). Review 2026: most household appliances operate below 240 V.",
                    "Review 2026: high-voltage and lightning injuries more often cause cardiac or respiratory arrest, compartment syndrome, AKI and seizures than low-voltage injuries.",
                    "JTS 2025: in high-voltage injury, small skin contact wounds can hide extensive deep soft-tissue damage.",
                    "Review 2026: lightning usually burns skin less (brief surface current); Lichtenberg figures fade within 24 h; tympanic membrane rupture is characteristic.",
                    "MoHFW 2025: CT or MRI may be indicated after a fall from height or loss of consciousness.",
                ),
                scoreIds = listOf("gcs"),
                citations = listOf(
                    cite(ENV_ABA_REF, "Electrical injuries"),
                    cite(ENV_JTS, "Electric injury — special considerations"),
                    cite(ENV_REVIEW, "Introduction; Neurologic and sensory; Cutaneous; Disposition"),
                    cite(ENV_MOHFW, "Ch. 6 Electric burns (p. 53)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "ECG and cardiac monitoring",
                timing = "ECG on arrival",
                points = listOf(
                    "MoHFW 2025: ECG after every electrical contact, low or high voltage; continue monitoring after loss of consciousness, an abnormal ECG, or CPR.",
                    "MoHFW 2025: low-voltage contact burns without these features may be discharged if nothing else needs admission. BBA 2018: no prolonged monitoring without LOC, arrest or ECG abnormality.",
                    "Review 2026: telemetry for at least 24 h for arrhythmia on the first ECG, loss of consciousness, significant cardiac disease, or high-voltage or lightning injury.",
                    "Pilecky 2019 (480 patients, 96% low voltage): most arrhythmias were evident on the admission ECG; no late malignant arrhythmias; troponin I and CK-MB did not help.",
                    "Review 2026: troponin only for chest pain, haemodynamic instability or ECG changes suggesting ACS. MoHFW 2025: cardiac markers if a physician recommends.",
                ),
                checklistTitle = "Monitor — do not discharge (MoHFW 2025; Review 2026)",
                checklist = listOf(
                    "Loss of consciousness",
                    "Abnormal ECG or arrhythmia",
                    "CPR or cardiac arrest after the injury",
                    "High-voltage or lightning injury",
                    "Significant known cardiac disease",
                ),
                citations = listOf(
                    cite(ENV_MOHFW, "Ch. 6 Electric burns (p. 52)"),
                    cite(ENV_BBA, "Electrical burns"),
                    cite(ENV_REVIEW, "Cardiovascular; Table 1"),
                    cite(ENV_PILECKY, "Abstract — Results and Conclusions"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Deep tissue injury and compartment syndrome",
                timing = "Limb checks every 1–2 h for the first 24–48 h (Review 2026)",
                points = listOf(
                    "JTS 2025: watch for compartment syndrome, especially above 1000 V; it typically needs fasciotomy — escharotomy does not relieve pressure from myonecrosis.",
                    "MoHFW 2025: consider early fasciotomy for electrical burns of the extremities. ISBI 2016: high-voltage injury is the main indication for primary fasciotomy.",
                    "Review 2026: pain out of proportion or on passive stretch; if examination is unreliable, compartment pressure >30 mm Hg warrants immediate surgical consultation, " +
                        "and a delta pressure (diastolic BP − compartment pressure) <30 mm Hg is the review's preferred threshold for fasciotomy.",
                    "MoHFW 2025: decreasing Doppler signal with adequate BP, or tissue pressure >25 mm Hg, means escharotomy or fasciotomy is needed.",
                    "Review 2026: MRI after admission may be needed to define deep soft-tissue injury in high-voltage cases.",
                ),
                citations = listOf(
                    cite(ENV_JTS, "Electric injury — compartment syndrome; fasciotomy vs escharotomy"),
                    cite(ENV_MOHFW, "Ch. 5 §G Escharotomy and fasciotomy (p. 42)"),
                    cite(ENV_ISBI, "§6 Rec 4"),
                    cite(ENV_REVIEW, "Musculoskeletal; Table 2"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Fluids: burn formulas underestimate",
                timing = "Hourly urine output",
                points = listOf(
                    "MoHFW 2025: standard burn formulas may be insufficient because the contact area can be very small; adjust IV fluid to clinical state and urine output.",
                    "Review 2026: %TBSA underestimates electrical injury; for TBSA >20% and/or high-voltage or lightning injury, start Ringer's lactate by Parkland or modified Brooke, then titrate.",
                    "ISBI 2016: electrical burns need additional fluid. JTS 2025: requirements exceed a similar-sized thermal burn.",
                    "JTS 2025: with pigmenturia, titrate isotonic fluid to urine 75–100 mL/h in adults until urine is clear or light yellow.",
                    "MoHFW 2025: with myoglobinuria, keep adult urine output at 2–5 mL/kg/h. Review 2026: about 1 mL/kg/h for electrical injury.",
                    "JTS 2025: urine dipstick haem stays positive longer — do not use it to decide when to stop. Review 2026: watch for over-resuscitation.",
                ),
                citations = listOf(
                    cite(ENV_MOHFW, "Ch. 6 Electric burns (p. 52)"),
                    cite(ENV_REVIEW, "Renal; Table 3"),
                    cite(ENV_ISBI, "§3 Rec 3 (§3.5)"),
                    cite(ENV_JTS, "Electric injury — increased fluid goals"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Rhabdomyolysis and acute kidney injury",
                points = listOf(
                    "Review 2026: CK on arrival and trend for 24–48 h even if normal — CK peaks at 1–3 days; decide on context, not a single CK cut-off.",
                    "JTS 2025: visibly red or brown urine means clinically significant pigmenturia; check CK every 6 h if available.",
                    "MoHFW 2025 only: with myoglobinuria, mannitol 50–200 g over 24 h and sodium bicarbonate 50–150 mEq in 1 L 5% dextrose over 1 h to alkalinise urine; " +
                        "JTS 2025 and Review 2026 titrate isotonic fluid to urine output and use neither.",
                    "Review 2026: creatinine every 24 h, or every 12 h at higher AKI risk, with continuous urine-output monitoring.",
                    "MoHFW 2025: once urine is clear, return to the usual burn resuscitation goals.",
                ),
                citations = listOf(
                    cite(ENV_REVIEW, "Musculoskeletal; Renal"),
                    cite(ENV_JTS, "Electric injury — rhabdomyolysis; increased fluid goals (pp. 14–15)"),
                    cite(ENV_MOHFW, "Ch. 6 Electric burns (p. 52)"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Lightning: triage and specific checks",
                timing = "At the scene",
                points = listOf(
                    "ICAR MEDCOM 2018 (grade 1B): with several victims, resuscitate those not breathing without obvious lethal injury before those with vital signs ('reverse triage').",
                    "ICAR MEDCOM 2018: asystole often resolves, but respiratory arrest may persist for hours — continue ventilation until spontaneous breathing returns.",
                    "Review 2026 (summarising WMS): ECG; head CT after loss of consciousness or a direct head strike; eye and bilateral ear examination.",
                    "Review 2026 (electrical and lightning injury): corneal erosions early, cataract later; early ophthalmology or ENT review if symptomatic.",
                    "ABA 2022: all lightning injuries — immediate burn-centre consultation with consideration for transfer.",
                ),
                citations = listOf(
                    cite(ENV_ICAR, "Specific situations — Lightning injuries (recommendation, grade 1B)"),
                    cite(ENV_REVIEW, "Discussion — Management of Electrical Burn Complications in the ED; Neurologic and sensory; Table 5"),
                    cite(ENV_ABA_REF, "Electrical injuries"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Admission, referral and discharge",
                points = listOf(
                    "ABA 2022: high-voltage (≥1000 V) and lightning — immediate consultation with consideration for transfer; low voltage — consult and consider follow-up for delayed symptoms and vision problems.",
                    "MoHFW 2025: refer all electrical burns, including lightning, to secondary care; extensive electrical burns with vascular compromise to a tertiary centre.",
                    "Review 2026: admit for arrest, abnormal ECG, LOC, instability, inhalation injury, CK >5× normal, AKI, fluid or IV-analgesia needs, partial/full-thickness or circumferential burns, compartment signs.",
                    "Review 2026: low-voltage injury with normal ECG, no LOC or arrest, no burns needing fluids, normal CK and creatinine — can usually be discharged with follow-up.",
                ),
                citations = listOf(
                    cite(ENV_ABA_REF, "Electrical injuries"),
                    cite(ENV_MOHFW, "Ch. 3 Referral to secondary care (pp. 20–21); Ch. 4 Referral to tertiary care (p. 36)"),
                    cite(ENV_REVIEW, "Disposition"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Urine target with myoglobinuria differs: MoHFW 2025 2–5 mL/kg/h with mannitol and bicarbonate; JTS 2025 75–100 mL/h with isotonic fluid. " +
                    "Review 2026 gives about 1 mL/kg/h for adult electrical injury in general and no separate target for pigmenturia. " +
                    "JTS 2025 and Review 2026 use no mannitol or bicarbonate.",
                listOf(cite(ENV_MOHFW, "Ch. 6 (p. 52)"), cite(ENV_JTS, "Electric injury"), cite(ENV_REVIEW, "Renal; Table 3")),
            ),
            ProtocolNote(
                "Evidence for monitoring is weak: Pilecky 2019 (retrospective) found routine ECG monitoring unnecessary in a mainly low-voltage cohort; the 2026 review notes " +
                    "that no widely accepted guideline exists for electrical injury.",
                listOf(cite(ENV_PILECKY, "Conclusions"), cite(ENV_REVIEW, "Discussion — Management of Electrical Burn Complications in the ED")),
            ),
            ProtocolNote(
                "Compartment pressure thresholds differ: MoHFW 2025 tissue pressure >25 mm Hg for escharotomy or fasciotomy; Review 2026 >30 mm Hg for urgent surgical consultation, " +
                    "with delta pressure <30 mm Hg as its preferred fasciotomy threshold.",
                listOf(cite(ENV_MOHFW, "Ch. 5 §G (p. 42)"), cite(ENV_REVIEW, "Musculoskeletal; Table 2")),
            ),
            ProtocolNote(
                "AHA 2025 addresses only cardiac arrest from electrocution; burns, cardiac injury and trauma from electricity are outside its scope.",
                listOf(cite(ENV_AHA, "Electrocution — synopsis (S600)")),
            ),
        ),
        citations = listOf(
            cite(ENV_MOHFW), cite(ENV_JTS), cite(ENV_ABA_REF), cite(ENV_AHA), cite(ENV_ISBI), cite(ENV_BBA),
            cite(ENV_REVIEW), cite(ENV_PILECKY), cite(ENV_ICAR),
        ),
    ),
)
