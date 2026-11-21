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
 * Snakebite and scorpion sting pathways. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them.
 * - Every point is attributed ("MoHFW STG 2017: …", "NAPSE 2024: …", "WHO 2016: …", "Bawaskar 2011: …").
 * - MoHFW STG / NAPSE locators are section + printed page; WHO SEARO 2016 locators are section + printed page.
 * - No MoHFW or ICMR guideline exists for scorpion sting (checked November 2026): that pathway rests on
 *   Indian trials, protocols and reviews, each named in the point.
 */

internal val envenomationSources = listOf(
    // Snakebite ------------------------------------------------------------------------------------
    Source(
        "tx-sb-mohfw-stg-2017", Authority.MoHFW, "Standard Treatment Guidelines (MoHFW, August 2017; ISBN 978-93-82655-21-3)",
        "Management of Snake Bite", "2017",
        "https://ncdc.mohfw.gov.in/uploads/pdf/Standard-Treatment-Guidelines-for-Management-of-Snake-Bite.pdf",
        status = "National clinical guideline listed by NCDC's snakebite programme (checked November 2026)",
    ),
    Source(
        "tx-sb-ncdc-napse-2024", Authority.MoHFW, "National Action Plan (NCDC, Centre for One Health)",
        "National Action Plan for Prevention and Control of Snakebite Envenoming (NAPSE)", "2024",
        "https://ncdc.mohfw.gov.in/uploads/pdf/NATIONAL-ACTION-PLAN-FOR-PREVENTION-AND-CONTROL-OF-SNAKEBITE-ENVENOMING-NAPSE.pdf",
        status = "Clinical content in Ch. 11 (pre-hospital care and diagnosis) and Ch. 13 (anti-snake venom)",
    ),
    Source(
        "tx-sb-ncdc-phc-2024", Authority.MoHFW, "NCDC flowchart for PHC/CHC",
        "Protocol for Initial Management of Snakebite at Health Facilities (PHC/CHC)", "2024",
        "https://ncdc.mohfw.gov.in/uploads/pdf/PEP-Protocol.pdf",
        status = "One-page flowchart; refers to the Standard Treatment Guidelines for details",
    ),
    Source(
        "tx-sb-icmr-stw-paralysis-2019", Authority.ICMR, "Standard Treatment Workflow (Neurology)",
        "Acute Paralysis", "2019",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1725952346_neurology_acute_paralysis.pdf",
        status = "ICMR has no snakebite- or scorpion-specific STW (checked November 2026)",
    ),
    Source(
        "tx-sb-who-searo-2016", Authority.WHO, "WHO SEARO guideline, 2nd edition (ISBN 978-92-9022-530-0)",
        "Guidelines for the management of snakebites", "2016",
        "https://iris.who.int/handle/10665/249547",
    ),
    // Scorpion -------------------------------------------------------------------------------------
    Source(
        "tx-sc-bawaskar-bmj-2011", Authority.Paper,
        "Randomised open-label trial, BMJ 2011;342:c7136 (Bawaskar HS, Bawaskar PH; Mahad, Maharashtra)",
        "Efficacy and safety of scorpion antivenom plus prazosin compared with prazosin alone for venomous scorpion (Mesobuthus tamulus) sting",
        "2011",
        "https://doi.org/10.1136/bmj.c7136",
    ),
    Source(
        "tx-sc-bawaskar-ip-2015", Authority.Paper,
        "Editorial, Indian Pediatrics 2015;52:289–290 (journal of the Indian Academy of Pediatrics)",
        "Scorpion envenoming – a step ahead (Bawaskar HS, Bawaskar PH)", "2015",
        "https://indianpediatrics.net/apr2015/289.pdf",
    ),
    Source(
        "tx-sc-jipmer-ip-2015", Authority.Paper,
        "Prospective observational study, Indian Pediatrics 2015;52:297–301 (JIPMER, Puducherry)",
        "Predictors of myocardial dysfunction in children with Indian red scorpion (Mesobuthus tamulus) sting envenomation",
        "2015",
        "https://doi.org/10.1007/s13312-015-0627-9",
        status = "Methods section sets out the departmental treatment protocol",
    ),
    Source(
        "tx-sc-jipmer-ip-2018", Authority.Paper,
        "Prospective study, Indian Pediatrics 2018;55:315–318 (JIPMER; CTRI/2015/03/005652)",
        "Second dose of scorpion antivenom in children with Indian red scorpion (Mesobuthus tamulus) sting envenomation",
        "2018",
        "https://doi.org/10.1007/s13312-018-1275-7",
        status = "Exploratory; no comparison arm",
    ),
    Source(
        "tx-sc-rodrigo-sr-2017", Authority.Paper, "Systematic review and meta-analysis, Syst Rev 2017;6:74",
        "Management of scorpion envenoming: a systematic review and meta-analysis of controlled clinical trials", "2017",
        "https://doi.org/10.1186/s13643-017-0469-8",
    ),
    Source(
        "tx-sc-kumar-jfmpc-2022", Authority.Paper, "Review, J Family Med Prim Care 2022;11(9):4932–4935",
        "An update on epidemiology and management practices of scorpion envenomation in India", "2022",
        "https://doi.org/10.4103/jfmpc.jfmpc_2300_21",
    ),
    Source(
        "tx-sc-fda-prazosin", Authority.FDA, "FDA-approved label (DailyMed; prazosin hydrochloride capsules, Aurobindo)",
        "Prazosin hydrochloride capsules — prescribing information", "2023",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=08114092-fa12-4f58-b08e-03cadf9f726b",
        status = "Labelled for hypertension only; use in scorpion envenoming is off-label",
    ),
)

private const val SB_STG = "tx-sb-mohfw-stg-2017"
private const val SB_NAPSE = "tx-sb-ncdc-napse-2024"
private const val SB_PHC = "tx-sb-ncdc-phc-2024"
private const val SB_ICMR = "tx-sb-icmr-stw-paralysis-2019"
private const val SB_WHO = "tx-sb-who-searo-2016"
private const val SC_BMJ = "tx-sc-bawaskar-bmj-2011"
private const val SC_ED = "tx-sc-bawaskar-ip-2015"
private const val SC_J15 = "tx-sc-jipmer-ip-2015"
private const val SC_J18 = "tx-sc-jipmer-ip-2018"
private const val SC_SR = "tx-sc-rodrigo-sr-2017"
private const val SC_REV = "tx-sc-kumar-jfmpc-2022"
private const val SC_FDA = "tx-sc-fda-prazosin"

internal val envenomationProtocols = listOf(
    // =============================================================================================
    // SNAKEBITE
    // =============================================================================================
    Protocol(
        id = "snakebite",
        title = "Snakebite",
        summary = "First aid, 20WBCT, neurotoxic vs haemotoxic envenoming, anti-snake venom, reactions, " +
            "neostigmine, ventilation, AKI and referral — MoHFW STG 2017 and NAPSE 2024 first, WHO SEARO 2016 alongside.",
        keywords = listOf(
            "snake", "snakebite", "snake bite", "saanp", "saap", "sapp", "krait", "common krait", "karait",
            "cobra", "naag", "nag", "Russell's viper", "Russells viper", "Daboia", "saw-scaled viper", "Echis",
            "phoorsa", "viper", "ASV", "anti-snake venom", "anti snake venom", "antivenom", "polyvalent ASV",
            "20WBCT", "WBCT", "20 minute whole blood clotting test", "neurotoxic", "neuroparalytic", "vasculotoxic",
            "haemotoxic", "hemotoxic", "ptosis", "neostigmine", "Bharat Serums", "VINS", "Premium Serums", "Haffkine",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "First aid and transport",
                timing = "Immediately after the bite",
                points = listOf(
                    "MoHFW STG 2017: reassure (about 70% of bites are non-venomous); splint the whole limb like a fracture; recovery position, prone on the left side.",
                    "MoHFW STG 2017: nil by mouth; remove rings, watches, shoes and tight clothing; leave blisters alone. The victim must not run or drive.",
                    "MoHFW STG 2017: move immediately to a facility with ASV — ambulance 102/108; a motorbike is acceptable if a third person sits behind the patient.",
                    "WHO 2016: pressure-pad immobilisation unless an elapid bite can be excluded. MoHFW STG 2017: crepe bandage only by trained staff when transfer takes 30 min–3 h.",
                    "NAPSE 2024: do not remove tourniquets in the field — remove slowly once IV access and resuscitation are in place.",
                ),
                checklistTitle = "First-aid don'ts (MoHFW STG 2017; NAPSE 2024; WHO 2016)",
                checklist = listOf(
                    "Tourniquet or tight ligature",
                    "Cutting, suction, rubbing, tattooing or massage of the bite",
                    "Washing or vigorous cleaning of the wound (STG, NAPSE)",
                    "Herbs, chemicals, ice, cautery, electric shock or 'black stone'",
                    "ASV applied or injected at the bite site",
                    "Delay for traditional healers or remedies",
                    "Trying to kill or catch the snake",
                ),
                citations = listOf(
                    cite(SB_STG, "Recommendations — First aid measures; Important don'ts (p.7–9)"),
                    cite(SB_NAPSE, "Ch. 11 Pre-hospital care (p.65–66); first-aid table (p.72–74)"),
                    cite(SB_WHO, "§6.2.1–6.3 (p.115–117)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Arrival: resuscitate, admit, observe",
                timing = "On arrival; observe at least 24 h",
                points = listOf(
                    "MoHFW STG 2017: airway, breathing, circulation first; large-bore IV line with slow normal saline. Admit every confirmed or suspected bite and observe for 24 h.",
                    "MoHFW STG 2017: check the distal pulse; release tourniquets only after the ASV loading dose has started with atropine–neostigmine ready — the most proximal one last.",
                    "WHO 2016 early clues to severe envenoming: rapidly spreading swelling, tender regional nodes, early vomiting or collapse, ptosis, spontaneous bleeding, dark urine or no urine.",
                    "MoHFW STG 2017: the Glasgow Coma Scale is invalid in neurotoxic paralysis. WHO 2016: fixed dilated pupils and no response may be paralysis, not brain death.",
                    "NAPSE 2024: if no envenoming at presentation, re-examine at 2, 6, 12 and 24 h. Do not delay emergency treatment to identify the snake.",
                    "MoHFW STG 2017: type and cross-match the first blood sample — venom and ASV can interfere with later cross-matching.",
                ),
                citations = listOf(
                    cite(SB_STG, "At a health care facility (p.10); Assessment (p.18–20); Lab investigations (p.22)"),
                    cite(SB_WHO, "§6.4.1–6.4.2.3 (p.118–122)"),
                    cite(SB_NAPSE, "Ch. 11 recommendations box (p.71); species identification (p.69, 75)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "20-minute whole blood clotting test (20WBCT)",
                timing = "Baseline, then hourly for 3 h and 6-hourly to 24 h (MoHFW STG 2017)",
                points = listOf(
                    "MoHFW STG 2017 and WHO 2016: 2 mL fresh venous blood in a new, clean, dry glass tube; leave undisturbed 20 min at room temperature; tilt once.",
                    "Blood still liquid = 'not clotted': venom-induced consumption coagulopathy. STG: report clotted/not clotted, never positive/negative. WHO 2016: in South-East Asia this indicates a viper.",
                    "WHO 2016: plastic, detergent-washed or wet tubes give false 'not clotted' results; if the result doesn't fit the clinical picture, repeat in duplicate with a healthy control.",
                    "NAPSE 2024: about 84% sensitive; misses milder coagulopathy. WHO 2016 and STG: do not delay ASV for spontaneous systemic bleeding distant from the bite.",
                    "MoHFW STG 2017: repeat hourly for 3 h, then 6-hourly to 24 h. NAPSE 2024: if baseline clotted and no envenoming, at 6, 12 and 24 h after the bite.",
                    "MoHFW STG 2017: after ASV, repeat 6 h after the loading dose; in neurotoxic envenoming, repeat after 6 h.",
                ),
                citations = listOf(
                    cite(SB_STG, "Lab investigations — bedside 20WBCT (p.21); Assessment (p.19); Annexure 2 (p.67)"),
                    cite(SB_WHO, "§6.6.1 and Recommendation (p.123–125)"),
                    cite(SB_NAPSE, "Ch. 11 Diagnostic aspects (p.68); laboratory recommendations (p.76–77)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise the envenoming syndrome",
                timing = "Cobra 30 min–6 h; krait often after 6–12 h (MoHFW STG 2017)",
                points = listOf(
                    "MoHFW STG 2017 neuroparalytic (cobra, krait): '5 Ds and 2 Ps' — dyspnoea, dysphonia, dysarthria, diplopia, dysphagia; ptosis, paralysis. Ptosis usually comes first.",
                    "MoHFW STG 2017 krait: often no bite mark; early-morning abdominal pain and vomiting after sleeping on the floor, then descending paralysis (Guillain–Barré is ascending).",
                    "MoHFW STG 2017 vasculotoxic (Russell's, saw-scaled viper): painful progressive swelling, blisters, bleeding, non-clotting 20WBCT, shock; AKI (not with saw-scaled viper).",
                    "MoHFW STG 2017: Russell's viper capillary leak — parotid swelling, chemosis, haemoconcentration; later pituitary failure. In some regions Russell's viper also causes paralysis.",
                    "MoHFW STG 2017: anxiety mimics envenoming (palpitations, sweating, paraesthesia). NAPSE 2024: anxiety starts within minutes; envenoming usually at least 30 min after.",
                    "ICMR STW: fatigable weakness or affected eye movements — likely myasthenia gravis or snake bite; intubate if unable to complete sentences or single breath count low.",
                ),
                checklistTitle = "Impending respiratory failure (MoHFW STG 2017)",
                checklist = listOf(
                    "Single breath count ≤30 (normal >30)",
                    "Breath-holding time ≤45 s (normal >45 s)",
                    "Cannot complete one sentence in one breath",
                    "Cannot lift the head ('broken neck' sign)",
                    "Pooling of saliva; husky or weak cry in a child",
                    "Paradoxical (abdominal) breathing",
                    "SpO₂ <90%",
                ),
                citations = listOf(
                    cite(SB_STG, "Fig. 1 (p.6); Sign & symptoms (p.10–17); Referral criteria (p.51)"),
                    cite(SB_NAPSE, "Ch. 11 Recognizing envenoming (p.67–68)"),
                    cite(SB_ICMR, "Weakness algorithm — fatigable weakness; management"),
                    cite(SB_WHO, "§6.4.2.3 (p.121–122)"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Anti-snake venom: indications and dose",
                timing = "As soon as envenoming is evident — full dose, no waiting for reports (MoHFW STG 2017)",
                points = listOf(
                    "MoHFW STG 2017: give ASV for signs of envenoming; spontaneous bleeding >20 min after a bite — start without waiting for 20WBCT. Purely local or hours-old swelling is not an indication.",
                    "MoHFW STG 2017 and NCDC 2024: polyvalent ASV 10 vials IV over 30 min (NAPSE 2024: 30–90 min). Never IM or at the bite site; no test dose.",
                    "MoHFW STG 2017 neurotoxic: second 10 vials after 1 h if no improvement; maximum 20 vials. WHO 2016: repeat if neurotoxic or cardiovascular signs worsen after 1 h.",
                    "MoHFW STG 2017 vasculotoxic: 20WBCT 6-hourly, ASV 6-hourly until clotting; low-dose option 10 vials (6 for saw-scaled viper) then 2 vials 6-hourly. Reconsider after 30 vials.",
                    "WHO 2016: repeat the dose if blood is still incoagulable at 6 h, or within 1–2 h if bleeding briskly.",
                    "MoHFW STG 2017, NAPSE 2024, WHO 2016: children and pregnant women get the same dose as adults. STG: in children dilute in 5–10 mL/kg saline.",
                ),
                checklistTitle = "ASV indications (WHO 2016 §6.7.2; NAPSE 2024)",
                checklist = listOf(
                    "Spontaneous systemic bleeding",
                    "Non-clotting 20WBCT, INR >1.2 or platelets <100,000/mm³",
                    "Neurotoxic signs: ptosis, ophthalmoplegia, paralysis",
                    "Hypotension, shock, arrhythmia or abnormal ECG",
                    "Oliguria/anuria or rising creatinine",
                    "Dark brown urine (haemoglobin or myoglobin)",
                    "Swelling of more than half the bitten limb within 48 h",
                    "Rapidly extending swelling, digital bite, or tender enlarged regional node",
                ),
                citations = listOf(
                    cite(SB_STG, "ASV therapy (p.27); ASV dose (p.28); pregnancy, children, repeat dose (p.30–31)"),
                    cite(SB_PHC, "ASV dosage box"),
                    cite(SB_NAPSE, "Ch. 11 recommendations (p.71); Ch. 13 ASV dosage table (p.83–84)"),
                    cite(SB_WHO, "§6.7.2 (p.129–130); §6.7.7–6.7.8 (p.136–142); §6.7.10 (p.142–143)"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "ASV reactions",
                timing = "Watch every 5 min for 30 min, then every 15 min for 2 h (MoHFW STG 2017)",
                points = listOf(
                    "MoHFW STG 2017: no test dose — skin or conjunctival tests don't predict reactions. Doctor at the bedside; adrenaline drawn up in two syringes before starting.",
                    "MoHFW STG 2017 and WHO 2016: prophylactic adrenaline 0.25 mg SC before ASV. STG excludes BP >140/90 or cardiovascular disease; WHO excludes cerebrovascular disease.",
                    "MoHFW STG 2017 and WHO 2016: antihistamines, corticosteroids and infusion rate (10–120 min) do not change early reaction rates.",
                    "First sign (itch, urticaria, cough, vomiting, tachycardia, hypotension): stop ASV; adrenaline 0.5 mg IM of 1:1000 in adults (STG, WHO). WHO: repeat every 5–10 min if needed.",
                    "MoHFW STG 2017: add chlorphenamine 10 mg IV; hydrocortisone acts only after hours. After recovery, restart ASV slowly for 10–15 min and complete the full dose.",
                    "MoHFW STG 2017: serum sickness 1–12 days later — chlorphenamine 2 mg 6-hourly for 5 days; prednisolone 5 mg 6-hourly for 5 days if no response in 24–48 h.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Adrenaline for ASV reaction — child (MoHFW STG 2017; WHO 2016; NCDC 2024)",
                        dose = WeightDose(minPerKg = 0.01, maxPerKg = 0.01, unit = "mg", per = "per dose IM (1 mg/mL, 1:1000)"),
                        note = "Adults 0.5 mg IM. WHO 2016: repeat every 5–10 min if the reaction persists; NCDC 2024: repeat after 5 min.",
                    ),
                    StepDose(
                        label = "Prophylactic adrenaline before ASV — child (MoHFW STG 2017; WHO 2016)",
                        dose = WeightDose(minPerKg = 0.005, maxPerKg = 0.005, unit = "mg", per = "once SC (0.1% solution) just before ASV"),
                        note = "Adults 0.25 mg SC.",
                    ),
                    StepDose(
                        label = "Chlorphenamine — child (MoHFW STG 2017; WHO 2016)",
                        dose = WeightDose(minPerKg = 0.2, maxPerKg = 0.2, unit = "mg", per = "per dose IV"),
                        note = "Adults 10 mg IV.",
                    ),
                    StepDose(
                        label = "Hydrocortisone — child (WHO 2016; NCDC 2024)",
                        dose = WeightDose(minPerKg = 2.0, maxPerKg = 2.0, unit = "mg", per = "per dose IV"),
                        note = "WHO 2016 adults 100 mg IV; unlikely to act for several hours.",
                    ),
                ),
                citations = listOf(
                    cite(SB_STG, "Monitoring during ASV (p.29); ASV reaction, premedication, treatment of early and late reactions (p.31–33)"),
                    cite(SB_WHO, "§6.7.5.1–6.7.5.4 (p.132–135); §6.7.7 (p.136)"),
                    cite(SB_PHC, "Treatment for adverse reaction"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Neurotoxic envenoming: neostigmine and ventilation",
                timing = "Give one atropine–neostigmine dose before transfer (MoHFW STG 2017)",
                points = listOf(
                    "MoHFW STG 2017 and WHO 2016: ASV alone cannot be relied on in bulbar or respiratory paralysis — oxygen, intubation or LMA, and bag or mechanical ventilation.",
                    "MoHFW STG 2017: atropine 0.6 mg then neostigmine 1.5 mg IV; then neostigmine 0.5 mg with atropine every 30 min for 5 doses, tapering at 1, 2, 6, 12 h.",
                    "MoHFW STG 2017: response = ≥50% recovery of ptosis in 1 h. Stop if no response after 3 doses, fasciculations or bradycardia; no AN in confirmed krait bite.",
                    "WHO 2016: trial in every neurotoxic patient without delaying ASV or intubation — atropine 0.6 mg IV, neostigmine 0.02 mg/kg IM; maintenance up to 10 mg/24 h.",
                    "MoHFW STG 2017: no response after 3 doses suggests krait — calcium gluconate 10 mL IV (children 1–2 mL/kg, 1:1 dilution) over 5–10 min 6-hourly.",
                    "MoHFW STG 2017: avoid sedatives, morphine, pethidine and neuromuscular blockers. Cannot lift head or SpO₂ <90% — transfer with ventilatory support.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Atropine before neostigmine — child (MoHFW STG 2017; WHO 2016)",
                        dose = WeightDose(minPerKg = 0.05, maxPerKg = 0.05, unit = "mg", per = "IV, given before each neostigmine dose"),
                        note = "Adults 0.6 mg IV.",
                    ),
                    StepDose(
                        label = "Neostigmine first dose — child (MoHFW STG 2017 IV; WHO 2016 IM)",
                        dose = WeightDose(minPerKg = 0.04, maxPerKg = 0.04, unit = "mg", per = "once, after atropine"),
                        note = "Adults: 1.5 mg IV (MoHFW STG 2017) or 0.02 mg/kg IM (WHO 2016).",
                    ),
                    StepDose(
                        label = "Neostigmine repeat — child (MoHFW STG 2017)",
                        dose = WeightDose(minPerKg = 0.01, maxPerKg = 0.01, unit = "mg", per = "IV with atropine every 30 min for 5 doses"),
                        note = "Adults 0.5 mg. Stop if no improvement after 3 doses.",
                    ),
                    StepDose(
                        label = "Neostigmine trial — adult (WHO 2016)",
                        dose = WeightDose(minPerKg = 0.02, maxPerKg = 0.02, unit = "mg", per = "once IM, after atropine 0.6 mg IV"),
                        note = "Observe 30–60 min. Responders: 0.5–2.5 mg every 1–3 h, maximum 10 mg/24 h, with atropine.",
                        maxTotal = null,
                    ),
                    StepDose(
                        label = "Neostigmine maintenance — child (WHO 2016)",
                        dose = WeightDose(minPerKg = 0.01, maxPerKg = 0.04, unit = "mg", per = "every 2–4 h IM, IV or SC, with atropine"),
                        note = "Only if the trial gave a convincing response.",
                    ),
                ),
                citations = listOf(
                    cite(SB_STG, "Management neurotoxic envenomation; AN dosage schedule (p.35–36); Other measures (p.45); Referral criteria (p.51)"),
                    cite(SB_WHO, "§6.9.1 (p.144); §6.9.3 Trial of anticholinesterase (p.152–153)"),
                    cite(SB_PHC, "ASV dosage box — neostigmine and atropine"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Haemotoxic complications: bleeding, shock, kidney injury",
                timing = "Forced alkaline diuresis only within 24 h of the bite (MoHFW STG 2017)",
                points = listOf(
                    "MoHFW STG 2017: strict bed rest; no IM injections; urine dipstick; keep urine output 1 mL/kg/h. WHO 2016: no arterial puncture; avoid jugular, subclavian and femoral lines.",
                    "MoHFW STG 2017: bleeding despite full ASV, or PCV <30%, needs blood or FFP. STG and WHO 2016: never heparin or antifibrinolytics.",
                    "MoHFW STG 2017: shock — crystalloid boluses up to 20 mL/kg titrated to perfusion; if crepitations or hepatomegaly develop, start dopamine or dobutamine.",
                    "MoHFW STG 2017: oliguria or dipstick blood within 24 h — trial of forced alkaline diuresis; no response to furosemide — refer for dialysis. WHO 2016: furosemide stress test.",
                    "Dialysis (MoHFW STG 2017; WHO 2016): creatinine >4 mg/dL, urea >130 mg/dL, K⁺ >7 mmol/L or ECG changes, fluid overload, uraemic encephalopathy or pericarditis.",
                    "WHO 2016: biochemistry alone is not enough to start dialysis. Russell's viper AKI may need it for up to 3 weeks; oliguria beyond 4 weeks — refer to nephrology.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Fresh frozen plasma (MoHFW STG 2017)",
                        dose = WeightDose(minPerKg = 10.0, maxPerKg = 15.0, unit = "mL", per = "over 30–60 min, within 4 h of ASV"),
                        note = "If coagulopathy persists after ASV; aim INR <2.0 at 6 h after ASV was started.",
                    ),
                    StepDose(
                        label = "Crystalloid bolus for shock (MoHFW STG 2017; NCDC 2024 for children)",
                        dose = WeightDose(minPerKg = 20.0, maxPerKg = 20.0, unit = "mL", per = "up to, per bolus"),
                        note = "STG: over 5–10 min (children over 30 min), titrated to perfusion. NCDC 2024 adults: 500–1000 mL.",
                    ),
                    StepDose(
                        label = "Furosemide stress test (WHO 2016)",
                        dose = WeightDose(minPerKg = 1.0, maxPerKg = 1.5, unit = "mg", per = "once, slow IV (4–5 mg/min) after adequate fluid"),
                        note = "Monitor urine for 2 h; under 100 mL/h predicts progression of kidney injury.",
                    ),
                ),
                citations = listOf(
                    cite(SB_STG, "Management of vasculotoxic snakebite: volume replacement, FAD, hyperkalaemia, dialysis, shock (p.37–40)"),
                    cite(SB_WHO, "§6.9.4–6.9.5 (p.154–160); §6.10 (p.160–161)"),
                    cite(SB_PHC, "Treatment for adverse reaction — fluid bolus"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Wound care, tetanus, discharge and referral",
                timing = "Discharge after 24 h if no envenoming; 48 h if ASV was given (MoHFW STG 2017)",
                points = listOf(
                    "MoHFW STG 2017: clean the bite site with povidone-iodine, no dressing; leave blisters alone. WHO 2016: aspirate bullae only if about to rupture; debride frank necrosis.",
                    "MoHFW STG 2017: tetanus toxoid booster once coagulation is restored. NCDC 2024: IM into the deltoid, not the buttock; postpone until bleeding stops.",
                    "MoHFW STG 2017: paracetamol for pain; no aspirin or NSAIDs; tramadol 50 mg for severe pain in adults.",
                    "MoHFW STG 2017 and WHO 2016: fasciotomy only with clinical compartment syndrome, pressure >40 mmHg and coagulopathy corrected. Corticosteroids don't help local effects.",
                    "MoHFW STG 2017 refer: no ASV; still not clotted after 10 vials; continued bleeding; kidney injury; progressive paralysis (with ventilation). Phone the receiving centre first.",
                    "MoHFW STG 2017: warn about serum sickness (fever, joint pain) after 5–10 days; return for bleeding, worse swelling, breathlessness or urine change.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Paracetamol — child (MoHFW STG 2017)",
                        dose = WeightDose(minPerKg = 10.0, maxPerKg = 15.0, unit = "mg", per = "per dose orally, every 4–6 h"),
                        note = "Adults 500–1000 mg every 4–6 h.",
                    ),
                ),
                citations = listOf(
                    cite(SB_STG, "Other measures; surgical procedures (p.45–47); Discharge, follow-up (p.49); Referral criteria (p.51–53)"),
                    cite(SB_WHO, "§6.11–6.13 (p.161–163)"),
                    cite(SB_PHC, "ASV dosage box — tetanus toxoid; Indications for referral"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "First aid differs: WHO 2016 recommends pressure-pad immobilisation unless an elapid bite is excluded; MoHFW STG 2017 allows crepe bandaging only by trained staff; NAPSE 2024 recommends splinting where resources are poor.",
                listOf(cite(SB_WHO, "§6.2.3 (p.116–117)"), cite(SB_STG, "p.9"), cite(SB_NAPSE, "First-aid table (p.73–74)")),
            ),
            ProtocolNote(
                "Neostigmine differs: MoHFW STG 2017 gives a fixed 1.5 mg IV, repeats 0.5 mg every 30 min and withholds it in confirmed krait bites; WHO 2016 uses 0.02 mg/kg IM and tries it in every neurotoxic patient.",
                listOf(cite(SB_STG, "AN dosage schedule (p.36)"), cite(SB_WHO, "§6.9.3 (p.153)")),
            ),
            ProtocolNote(
                "Calcium gluconate for suspected krait bite (no AN response) appears in MoHFW STG 2017 but not in WHO 2016. NAPSE 2024: krait paralysis may begin up to 16 h after the bite.",
                listOf(cite(SB_STG, "p.36"), cite(SB_WHO, "§6.9.1–6.9.3"), cite(SB_NAPSE, "Ch. 11 (p.67)")),
            ),
            ProtocolNote(
                "Initial dose: India uses 10 vials for all species. WHO 2016 Table 1 suggests Indian polyvalent 100 mL for krait and Russell's viper, 50 mL for cobra and saw-scaled viper, and calls Indian dose evidence weak.",
                listOf(cite(SB_STG, "ASV dose (p.28)"), cite(SB_WHO, "§6.7.8, Table 1 (p.139–141)")),
            ),
            ProtocolNote(
                "Repeat ASV in neurotoxic bites: MoHFW STG 2017 main text — after 1 h if no improvement; its annexure — if worse 2 h after the first dose. WHO 2016: no proven value once the patient is paralysed and ventilated.",
                listOf(cite(SB_STG, "Repeat dose (p.31); Annexure 2 (p.70)"), cite(SB_WHO, "§6.7.10 (p.143)")),
            ),
            ProtocolNote(
                "Infusion time: MoHFW STG 2017 dose section and NCDC 2024 say 30 min, the STG monitoring section about 1 h, NAPSE 2024 30–90 min; WHO 2016 30–60 min or slow IV push ≤2 mL/min.",
                listOf(
                    cite(SB_STG, "p.28–29"), cite(SB_PHC, "ASV dosage box"), cite(SB_NAPSE, "Ch. 13 (p.83–84)"),
                    cite(SB_WHO, "§6.7.7 (p.136)"),
                ),
            ),
            ProtocolNote(
                "Prophylactic adrenaline: recommended routinely by MoHFW STG 2017 and WHO 2016 (exclusions differ); the 2024 NCDC PHC/CHC flowchart does not mention premedication.",
                listOf(cite(SB_STG, "p.32"), cite(SB_WHO, "§6.7.5.3 (p.134)"), cite(SB_PHC, "ASV dosage box")),
            ),
            ProtocolNote(
                "Kidney injury: MoHFW STG 2017 recommends forced alkaline diuresis cycles (furosemide, bicarbonate, potassium, mannitol) within 24 h; WHO 2016 uses a cautious fluid challenge and furosemide stress test, alkalinising only for haemoglobin- or myoglobinuria.",
                listOf(cite(SB_STG, "Forced alkaline diuresis (p.38)"), cite(SB_WHO, "§6.9.5.1, §6.9.5.3 (p.156–159)")),
            ),
            ProtocolNote(
                "Antibiotics: MoHFW STG 2017 gives amoxicillin–clavulanate (or ceftriaxone) plus metronidazole in severe local envenoming, yet calls prophylaxis unnecessary elsewhere; WHO 2016 — prophylaxis failed in a trial, justified after wound incision.",
                listOf(cite(SB_STG, "Severe local envenoming (p.41); Recovery phase (p.43)"), cite(SB_WHO, "§6.11.2 (p.161)")),
            ),
            ProtocolNote(
                "Indian polyvalent ASV covers the 'big four'. Hump-nosed and Malabar pit vipers, green pit vipers and sea snakes are not neutralised. WHO 2016: the larger northern saw-scaled viper (E. c. sochureki) needs larger doses.",
                listOf(cite(SB_STG, "p.28, 30"), cite(SB_NAPSE, "Ch. 11 (p.69, 75)"), cite(SB_WHO, "§6.7.8 (p.140)")),
            ),
            ProtocolNote(
                "Scope: ICMR has no snakebite Standard Treatment Workflow (checked November 2026); NAPSE 2024 refers clinicians to the MoHFW Standard Treatment Guidelines and WHO SEARO 2016.",
                listOf(cite(SB_NAPSE, "Ch. 11 (p.77)"), cite(SB_STG)),
            ),
        ),
        citations = listOf(cite(SB_STG), cite(SB_NAPSE), cite(SB_PHC), cite(SB_ICMR), cite(SB_WHO)),
    ),
    // =============================================================================================
    // SCORPION STING
    // =============================================================================================
    Protocol(
        id = "scorpion-sting",
        title = "Scorpion sting",
        summary = "Indian red scorpion (Mesobuthus tamulus): grading, prazosin, scorpion antivenom, pulmonary oedema " +
            "and drugs to avoid — from Indian trials, protocols and reviews (no national guideline exists).",
        keywords = listOf(
            "scorpion", "scorpion sting", "scorpion bite", "Mesobuthus", "Mesobuthus tamulus", "Hottentotta tamulus",
            "red scorpion", "Indian red scorpion", "bichhu", "bichhoo", "bicchu", "thuhan", "prazosin", "Minipress",
            "Prazopress", "SAV", "scorpion antivenom", "anti-scorpion venom serum", "AScVS", "Haffkine",
            "autonomic storm", "priapism", "scorpion pulmonary oedema", "scorpion myocarditis",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise and grade",
                timing = "Grade on arrival; late presentation (>4–6 h) carries higher risk",
                points = listOf(
                    "Bawaskar 2011: grade 1 — severe local pain radiating along dermatomes, mild local oedema and sweating; no systemic involvement.",
                    "Bawaskar 2011: grade 2 — autonomic storm: vomiting, profuse sweating, ropy salivation, priapism, bradycardia or tachycardia >120, BP >140/90, cold extremities.",
                    "Bawaskar 2011: grade 3 — cold extremities, tachycardia, hypo- or hypertension with pulmonary oedema (RR >24, basal crackles). Grade 4 — hypotension with warm extremities.",
                    "JIPMER 2018: grade 3 also covers myocardial dysfunction (heart failure, cardiomegaly, inotrope ≥5 µg/kg/min); grade 4 also covers multi-organ dysfunction.",
                    "Kumar 2022: adults usually have local pain only; envenoming is more severe in children. Higher mortality mainly with presentation over 6 h after the sting.",
                    "JIPMER 2015: hypotension at admission and >4 h from sting to treatment independently predicted myocardial dysfunction.",
                ),
                checklistTitle = "Ominous early signs (Bawaskar 2015)",
                checklist = listOf(
                    "Vomiting",
                    "Profuse sweating",
                    "Cold extremities",
                    "Priapism in males",
                ),
                citations = listOf(
                    cite(SC_BMJ, "Methods — Evaluation of clinical grade (box)"),
                    cite(SC_J18, "Methods — grading"),
                    cite(SC_REV, "Clinical manifestations; Management"),
                    cite(SC_J15, "Results; Conclusion"),
                    cite(SC_ED, "p.289"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Grade 1: local pain",
                timing = "Observe 24 h for deterioration (JIPMER 2015)",
                points = listOf(
                    "JIPMER 2015 protocol: local anaesthetic injection for acute pain; paracetamol for mild persistent pain; observe 24 h for deterioration.",
                    "Kumar 2022: local anaesthetic is the most effective analgesia — as a digital block for finger and toe stings.",
                    "Systematic review 2017 (Turkish RCT, mild stings, adults): topical 5% lidocaine relieved pain better than IV paracetamol 1 g or ice.",
                ),
                citations = listOf(
                    cite(SC_J15, "Methods — management protocol"),
                    cite(SC_REV, "Management"),
                    cite(SC_SR, "Results — pain relief; Conclusions"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Prazosin (grade 2 and above)",
                timing = "Start at the first autonomic signs; repeat every 3 h",
                points = listOf(
                    "Bawaskar 2015: prazosin, an α1-blocker, is the 'physiological and pharmacological antidote' to venom-released catecholamines; it does not act on the venom itself.",
                    "JIPMER 2015 and 2018 (children ≤12–13 years): oral prazosin 30 µg/kg per dose every 3 h until the extremities are warm.",
                    "Bawaskar 2011 trial (age >6 months): oral prazosin 250 µg (under 18 years) or 500 µg (adults), repeated every 3 h.",
                    "Bawaskar 2011: all 9 patients who worsened had vomited their first oral dose, delaying it by 60 min; injectable prazosin is not available in India.",
                    "FDA label: first-dose syncope from postural hypotension, usually within 30–90 min — lay the patient down; hypotension is more likely with beta-blockers.",
                    "Bawaskar 2015: on oral prazosin, recovery takes 12–48 h, with close monitoring in intensive care.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Prazosin — child (JIPMER protocol)",
                        dose = WeightDose(minPerKg = 30.0, maxPerKg = 30.0, unit = "mcg", per = "per dose orally, every 3 h until extremities are warm"),
                        note = "Children ≤12–13 years in the JIPMER studies. Bawaskar 2011 used a fixed 250 µg (under 18) or 500 µg (adults).",
                    ),
                ),
                citations = listOf(
                    cite(SC_ED, "p.289"),
                    cite(SC_J15, "Methods — management protocol"),
                    cite(SC_J18, "Methods"),
                    cite(SC_BMJ, "Study treatments; Results; Discussion"),
                    cite(SC_FDA, "Warnings (syncope)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Scorpion antivenom (SAV)",
                timing = "Most useful within 4 h of the sting (JIPMER 2015; Bawaskar 2015)",
                points = listOf(
                    "Bawaskar 2011: Haffkine monovalent F(ab')₂ SAV, 30 mL in 100 mL saline IV over 30 min regardless of age (JIPMER 2018: over 1 h).",
                    "Bawaskar 2011 RCT (grade 2, within 6 h): SAV plus prazosin resolved 91% within 10 h vs 23% on prazosin alone; mean recovery 8 vs 17.7 h.",
                    "Systematic review 2017: SAV plus prazosin gives faster resolution than prazosin alone (3 Indian trials, 173 patients) in adults and children over 6 months.",
                    "Bawaskar 2015: SAV won't help once severe envenoming has developed and parasympathetic effects are over. Bawaskar 2011: SAV hastens recovery but is 'not mandatory'.",
                    "JIPMER 2018 (exploratory, children): second 30 mL dose if the autonomic storm persists or worsens within 6 h; abnormal echocardiogram at admission predicted need.",
                    "JIPMER 2018: 8% had allergic reactions (itch, rash; 2 with hypotension) — managed by stopping SAV, fluid bolus, adrenaline, hydrocortisone and antihistamine.",
                ),
                citations = listOf(
                    cite(SC_BMJ, "Study treatments; Results"),
                    cite(SC_J18, "Methods; Results"),
                    cite(SC_SR, "Results; Conclusions"),
                    cite(SC_J15, "Conclusion"),
                    cite(SC_ED, "p.289"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Pulmonary oedema, myocardial dysfunction and shock",
                timing = "Grades 3–4: intensive care; support may be needed for 24–96 h (Bawaskar 2015)",
                points = listOf(
                    "Bawaskar 2015: despite prazosin, about 10% of children develop tachycardia, hypotension, pulmonary oedema and warm shock needing dobutamine, nitroglycerine and ventilation.",
                    "JIPMER 2015 protocol: myocardial dysfunction — oxygen and IV dobutamine 6–20 µg/kg/min.",
                    "JIPMER 2015 protocol: pulmonary oedema — oxygen, mechanical ventilation, sodium nitroprusside 0.3–5 µg/kg/min or light-protected nitroglycerine 2–6 µg/kg/min.",
                    "Bawaskar 2011: grade 3 patients recovered with nasal oxygen, IV aminophylline and dobutamine; one warm-shock patient needed dobutamine up to 20 µg/kg/min and non-invasive ventilation.",
                    "Bawaskar 2011: correct dehydration from vomiting and sweating with IV crystalloid. JIPMER 2018: one child needed ventilation after excessive fluids at another hospital.",
                    "Bawaskar 2015: diuretics worsen pulmonary oedema and shock — avoid them.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Dobutamine — child (JIPMER protocol)",
                        dose = WeightDose(minPerKg = 6.0, maxPerKg = 20.0, unit = "mcg", per = "per minute, IV infusion"),
                        note = "For myocardial dysfunction, with oxygen.",
                    ),
                    StepDose(
                        label = "Nitroglycerine — child (JIPMER protocol)",
                        dose = WeightDose(minPerKg = 2.0, maxPerKg = 6.0, unit = "mcg", per = "per minute, IV infusion"),
                        note = "Freshly prepared, protected from sunlight; for pulmonary oedema.",
                    ),
                    StepDose(
                        label = "Sodium nitroprusside — child (JIPMER protocol)",
                        dose = WeightDose(minPerKg = 0.3, maxPerKg = 5.0, unit = "mcg", per = "per minute, IV infusion"),
                        note = "Alternative to nitroglycerine for pulmonary oedema.",
                    ),
                ),
                citations = listOf(
                    cite(SC_ED, "p.289–290"),
                    cite(SC_J15, "Methods — management protocol"),
                    cite(SC_BMJ, "Study treatments; Results"),
                    cite(SC_J18, "Results"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "What to avoid",
                points = listOf(
                    "Bawaskar 2011: lytic cocktail, insulin, atropine, beta-blockers, nifedipine and captopril failed to reduce morbidity or mortality.",
                    "Bawaskar 2015: steroids worsen catecholamine myocardial damage; antihistamines prolong QT (sudden death); calcium channel blockers depress the myocardium; diuretics worsen oedema.",
                    "Systematic review 2017 (Tunisian RCT, 600 patients): steroids gave no benefit. Kumar 2022: higher mortality seen in children given steroids and antihistamines.",
                ),
                checklistTitle = "Avoid (Bawaskar 2011, 2015; Kumar 2022)",
                checklist = listOf(
                    "Corticosteroids",
                    "Antihistamines as treatment for envenoming",
                    "Calcium channel blockers (e.g. nifedipine)",
                    "Diuretics",
                    "Atropine",
                    "Beta-blockers",
                    "Lytic cocktail (pethidine + promethazine + chlorpromazine)",
                ),
                citations = listOf(
                    cite(SC_BMJ, "Introduction"),
                    cite(SC_ED, "p.289–290"),
                    cite(SC_SR, "Steroids vs placebo; Conclusions"),
                    cite(SC_REV, "Management"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Monitoring, referral and discharge",
                timing = "Vital signs at 0, 30 min, 1, 2, 4, 6, 8, 10, 14, 18 and 24 h (Bawaskar 2011; JIPMER 2015)",
                points = listOf(
                    "Bawaskar 2011 and JIPMER 2015: BP, heart rate, respiratory rate and SpO₂ at 0, 30 min, 1, 2, 4, 6, 8, 10, 14, 18 and 24 h.",
                    "JIPMER 2015: ECG at admission and 6 h; echocardiography and CPK-MB within 24 h; repeat echo before discharge in grades 3–4.",
                    "Kumar 2022: after initial assessment and prazosin at a peripheral facility, refer promptly to a hospital with ICU beds, oxygen and antivenom.",
                    "JIPMER 2015: observe at least 24 h after SAV; with complications, discharge only after 24 h stable off drugs.",
                    "Bawaskar 2011: tetanus immunisation before discharge. WHO 2016: a raised bed and well tucked-in mosquito net protect against snakes and scorpions.",
                ),
                citations = listOf(
                    cite(SC_BMJ, "Methods; Study treatments"),
                    cite(SC_J15, "Methods"),
                    cite(SC_REV, "Management"),
                    cite(SB_WHO, "§2.1 Reducing the risk of snakebites (p.20)"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Scope: no MoHFW, NCDC or ICMR guideline for scorpion sting was found (checked November 2026). This pathway rests on Indian trials, protocols and reviews; Bawaskar 2011 notes that opinions on treatment differ.",
                listOf(cite(SC_BMJ, "Introduction"), cite(SC_SR, "Discussion")),
            ),
            ProtocolNote(
                "Prazosin dose differs: JIPMER uses 30 µg/kg per dose in children; Bawaskar 2011 used fixed 250 µg (under 18) or 500 µg (adults). Neither source states a maximum dose.",
                listOf(cite(SC_J15, "Methods"), cite(SC_J18, "Methods"), cite(SC_BMJ, "Study treatments")),
            ),
            ProtocolNote(
                "Antihistamines conflict: Bawaskar 2015 advises avoiding them (QT prolongation), but JIPMER 2018 premedicated SAV with IV chlorphenamine and ranitidine and reported fewer major reactions afterwards.",
                listOf(cite(SC_ED, "p.290"), cite(SC_J18, "Results")),
            ),
            ProtocolNote(
                "Antivenom evidence is species-specific: the systematic review found Haffkine M. tamulus SAV effective in India, while a bivalent North African antivenom was no better than placebo. Results don't transfer between species.",
                listOf(cite(SC_SR, "Conclusions")),
            ),
            ProtocolNote(
                "Supply: Bawaskar 2011 — SAV has been available in India since 2002 but is often short, while prazosin is freely available; with prazosin, mortality fell to under 1%.",
                listOf(cite(SC_BMJ, "Discussion")),
            ),
            ProtocolNote(
                "Bawaskar 2011 lists M. tamulus as common in western Maharashtra, Saurashtra, Kerala, Andhra Pradesh, Tamil Nadu and Karnataka; the Haffkine antivenom is monovalent for this species.",
                listOf(cite(SC_BMJ, "Introduction; Study treatments")),
            ),
            ProtocolNote(
                "Prazosin is FDA-labelled for hypertension only; its use in scorpion envenoming is off-label.",
                listOf(cite(SC_FDA, "Indications and usage")),
            ),
        ),
        citations = listOf(
            cite(SC_BMJ), cite(SC_ED), cite(SC_J15), cite(SC_J18), cite(SC_SR), cite(SC_REV), cite(SC_FDA), cite(SB_WHO),
        ),
    ),
)
