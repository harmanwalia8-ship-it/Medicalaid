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

// Rewritten in quick-reference style by Gemini (r1.md); converted by convert_protocols.py --rewrite.
// Clinical content checked only by the automatic number-preservation check (see the batch report).

internal val pesticideSources: List<Source> = listOf(
    Source(
        "tx-pest-eddleston-ccare-2004", Authority.Paper, "Treatment protocol (Crit Care 2004;8(6):R391–R397)",
        "Early management after self-poisoning with an organophosphorus or carbamate pesticide – a treatment protocol for junior doctors", "2004",
        "https://doi.org/10.1186/cc2953",
        status = "Eddleston M et al.; Sri Lanka; protocol based on clinical experience and the best available evidence at the time; quotes the WHO pralidoxime regimen",
    ),
    Source(
        "tx-pest-eddleston-pralidoxime-rct-2009", Authority.Paper, "RCT (PLoS Med 2009;6(6):e1000104)",
        "Pralidoxime in acute organophosphorus insecticide poisoning – a randomised controlled trial", "2009",
        "https://doi.org/10.1371/journal.pmed.1000104",
        status = "Eddleston M et al.; Sri Lanka; double-blind, placebo-controlled; 235 patients; 2 g loading then 0.5 g/h (not the WHO regimen)",
    ),
    Source(
        "tx-pest-pawar-lancet-2006", Authority.Paper, "Open RCT (Lancet 2006;368:2136–2141)",
        "Continuous pralidoxime infusion versus repeated bolus injection to treat organophosphorus pesticide poisoning: a randomised controlled trial", "2006",
        "https://doi.org/10.1016/S0140-6736(06)69862-0",
        status = "Pawar KS et al.; Maharashtra, India; single-centre, open-label RCT, 200 patients; all received a 2 g load, then 1 g/h for 48 h (study) vs 1 g over 1 h every 4 h (control); no placebo arm; authors concluded the high-dose regimen reduces morbidity and mortality, which the placebo-controlled trial (Eddleston 2009) did not replicate",
    ),
    Source(
        "tx-pest-cochrane-oximes-2011", Authority.Paper, "Cochrane review CD005085.pub2",
        "Oximes for acute organophosphate pesticide poisoning", "2011",
        "https://doi.org/10.1002/14651858.CD005085.pub2",
        status = "Buckley NA, Eddleston M, Li Y, Bevan M, Robertson J; searches to September 2009; 7 pralidoxime RCTs; evidence insufficient",
    ),
    Source(
        "tx-pest-kharel-pralidoxime-meta-2020", Authority.Paper, "Systematic review and meta-analysis (Cureus 2020;12(3):e7174)",
        "The Efficacy of Pralidoxime in the Treatment of Organophosphate Poisoning in Humans: A Systematic Review and Meta-analysis of Randomized Trials", "2020",
        "https://doi.org/10.7759/cureus.7174",
        status = "Kharel H et al.; searches to January 2019; 6 RCTs, 646 patients",
    ),
    Source(
        "tx-pest-peter-ijccm-2014", Authority.ISCCM, "Review (Indian J Crit Care Med 2014;18(11):735–745)",
        "Clinical features of organophosphate poisoning: A review of different classification systems and approaches", "2014",
        "https://doi.org/10.4103/0972-5229.144017",
        status = "Peter JV, Sudarsan TI, Moran JL; CMC Vellore authors; narrative review; delayed manifestations and intermediate syndrome",
    ),
    Source(
        "tx-pest-vijayakumar-mgso4-op-2017", Authority.Paper, "RCT (Anesth Essays Res 2017;11(1):192–196)",
        "Study of Effect of Magnesium Sulphate in Management of Acute Organophosphorous Pesticide Poisoning", "2017",
        "https://doi.org/10.4103/0259-1162.194585",
        status = "Vijayakumar HN et al.; Bengaluru; double-blind, 100 patients, single centre; no mortality difference",
    ),
    Source(
        "tx-pest-dsilva-ijccm-2019", Authority.ISCCM, "Invited review (Indian J Crit Care Med 2019;23(Suppl 4):S272–S277)",
        "Rodenticide Poisoning", "2019",
        "https://doi.org/10.5005/jp-journals-10071-23318",
        status = "D'Silva C, Krishna B; narrative review of yellow phosphorus, metal phosphides and hydroxycoumarins in the Indian setting",
    ),
    Source(
        "tx-pest-gurjar-jets-2011", Authority.Paper, "Review (J Emerg Trauma Shock 2011;4(3):378–384)",
        "Managing aluminum phosphide poisonings", "2011",
        "https://doi.org/10.4103/0974-2700.83868",
        status = "Gurjar M, Baronia AK, Azim A, Sharma K; SGPGIMS Lucknow (India); narrative review",
    ),
    Source(
        "tx-pest-farahani-ijccm-2016", Authority.ISCCM, "Review (Indian J Crit Care Med 2016;20(12):724–730)",
        "Thoughts on the current management of acute aluminum phosphide toxicity and proposed therapeutic options", "2016",
        "https://doi.org/10.4103/0972-5229.195712",
        status = "Farahani MV, Soroosh D, Marashi SM; Iranian authors; narrative review that questions conventional measures and relies largely on case reports",
    ),
    Source(
        "tx-pest-proudfoot-clintox-2009", Authority.Paper, "Review (Clin Toxicol 2009;47(2):89–100)",
        "Aluminium and zinc phosphide poisoning", "2009",
        "https://doi.org/10.1080/15563650802520675",
        status = "Proudfoot AT; abstract only was read for this app",
    ),
    Source(
        "tx-pest-faress-jnma-zinc-2025", Authority.Paper, "Review (JNMA 2025;63(286):442–448)",
        "Considerations on the Management of Zinc Phosphide Toxicity and Proposed Therapeutic Approaches", "2025",
        "https://doi.org/10.31729/jnma.9048",
        status = "Faress F et al.; Iranian authors; literature review; notes limited rigorous research on zinc phosphide",
    ),
    Source(
        "tx-pest-mohanka-jceh-2020", Authority.Paper, "Retrospective series (J Clin Exp Hepatol 2020)",
        "Acute liver failure secondary to yellow phosphorus rodenticide poisoning: Outcomes at a center with dedicated liver intensive care unit and liver transplant facility", "2020",
        "https://doi.org/10.1016/j.jceh.2020.09.010",
        status = "Mohanka R et al.; Global Hospital Mumbai; 19 patients with yellow phosphorus or metal phosphide acute liver failure, 2014–2019; single-centre protocol",
    ),
    Source(
        "tx-pest-jubin-nac-ijccm-2024", Authority.ISCCM, "Conference abstract (Indian J Crit Care Med 2024;28(Suppl 1):S509–S511)",
        "Surviving the Bait: An ICU Insight into Rodenticide Poisoning – does NAC have a Role?", "2024",
        "https://pmc.ncbi.nlm.nih.gov/articles/PMC11886211/",
        status = "Jubin TJ et al.; Bengaluru; retrospective, 19 patients; observational, hypothesis-generating",
    ),
    Source(
        "tx-pest-king-laar-tmr-2015", Authority.Paper, "Review (Transfus Med Rev 2015;29(4):250–258)",
        "Long-Acting Anticoagulant Rodenticide (Superwarfarin) Poisoning: A Review of Its Historical Development, Epidemiology, and Clinical Management", "2015",
        "https://doi.org/10.1016/j.tmrv.2015.06.002",
        status = "King N, Tran MH; review of 174 published cases plus US poison-centre data; case reports, not trials",
    ),
    Source(
        "tx-pest-fda-phytonadione-label", Authority.FDA, "Drug label (DailyMed): Phytonadione injectable emulsion (Dr. Reddy's)", "Phytonadione injectable emulsion, USP", "2019",
        "https://dailymed.nlm.nih.gov/dailymed/lookup.cfm?setid=226c0a5c-3e04-65a9-01ac-e67aad9a430a",
        status = "Label version 8, effective 2026-09-30 (the version read); US labelling, not an Indian product label",
    ),
    Source(
        "tx-pest-fda-provayblue-label", Authority.FDA, "Drug label (DailyMed): Provayblue (methylene blue) injection", "Provayblue (methylene blue) injection", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/lookup.cfm?setid=4f6848e5-35ed-4046-b13c-3032b5ba3232",
        status = "Label version 28, effective 2025-05-28; US labelling for drug-induced methaemoglobinaemia, not an Indian product label; G6PD deficiency is a contraindication (hemolytic anemia)",
    ),
)

internal val pesticideProtocols: List<Protocol> = listOf(
    Protocol(
        id = "organophosphate",
        title = "Organophosphate and carbamate poisoning",
        summary = "Cholinergic toxidrome recognition, rapid doubling atropine titration, pralidoxime controversy, intermediate syndrome monitoring, and ventilation.",
        keywords = listOf("OP", "OPC", "organophosphorus", "insecticide", "pesticide", "carbamate", "chlorpyrifos", "monocrotophos", "dichlorvos", "DDVP", "malathion", "parathion", "dimethoate", "fenthion", "profenofos", "Baygon", "propoxur", "atropine", "pralidoxime", "PAM", "2-PAM", "intermediate syndrome", "miosis", "bronchorrhoea", "SLUDGE", "kitnashak"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "Recognise cholinergic toxidrome & secure airway",
                timing = "At first contact — before decontamination",
                points = listOf(
                    "Key signs: pinpoint pupils, profuse sweating, bronchorrhoea/bronchospasm, bradycardia (may be normal/fast), and hypotension.",
                    "Atropine reverses muscarinic signs (secretions, bradycardia), not nicotinic muscle weakness; fat-soluble OPs (fenthion) cause delayed/recurrent crises.",
                    "Secure airway (intubate if unconscious), give high-flow oxygen, place left lateral head-down, record GCS/SpO2, and treat seizures with IV diazepam.",
                    "Never delay atropine even if oxygen is unavailable.",
                ),
                checklistTitle = "Cholinergic Signs",
                checklist = listOf(
                    "Symmetrical pinpoint pupils and drenched skin",
                    "Bronchorrhoea, wheeze, or lung crepitations",
                    "Bradycardia (may be absent) and hypotension",
                    "Salivation, vomiting, diarrhoea, incontinence",
                    "Fasciculations, muscle weakness, agitation, convulsions, or coma",
                    "Garlic or kerosene-like pesticide odour",
                ),
                citations = listOf(cite("tx-pest-eddleston-ccare-2004", "Recognition"), cite("tx-pest-peter-ijccm-2014", "Acute symptoms"), cite("tx-pest-eddleston-ccare-2004", "Initial assessment; Airway"), cite("tx-pest-eddleston-ccare-2004", "Care of airway"), cite("tx-pest-eddleston-ccare-2004", "Recognition; Initial assessment; Airway")),
            ),
            Step(
                title = "Rapid atropine loading to atropinisation",
                timing = "Reassess and double every 3–5 min",
                points = listOf(
                    "Unconscious/severe: give atropine 1.8–3 mg (three to five 0.6 mg vials) rapid IV with 500–1000 mL saline.",
                    "Unclear presentation: give 0.6–1 mg IV (HR rise >20–25/min with flushing suggests no significant cholinergic poisoning).",
                    "Reassess every 3–5 min; if no consistent improvement, DOUBLE the previous dose — never repeat the same dose.",
                    "Target clear chest, HR >80/min, SBP >80 mmHg, dry axillae, and non-pinpoint pupils (HR 120–140/min indicates toxicity).",
                ),
                checklistTitle = "Atropinisation End-Points",
                checklist = listOf(
                    "Chest clear, no wheeze",
                    "Heart rate >80 beats/min",
                    "Systolic BP >80 mmHg",
                    "Pupils no longer pinpoint (may dilate late)",
                    "Axillae dry",
                ),
                citations = listOf(cite("tx-pest-eddleston-ccare-2004", "Loading"), cite("tx-pest-eddleston-ccare-2004", "Continuation"), cite("tx-pest-eddleston-ccare-2004", "Table 2"), cite("tx-pest-eddleston-ccare-2004", "Loading; Table 2; Continuation")),
            ),
            Step(
                title = "Atropine infusion and toxicity",
                timing = "Review every 15 min initially, then hourly for 6 h",
                points = listOf(
                    "Start infusion at 10–20% of total loading dose per hour (less if >30 mg loaded; >3–5 mg/h rarely needed).",
                    "If cholinergic signs recur, give bolus atropine until clear and increase the infusion rate.",
                    "Atropine toxicity (confusion + fever + absent bowel sounds): stop infusion, recheck at 30 min, restart at 70–80% rate.",
                    "Cool fever early (fan/wet towels); treat agitation with diazepam 10 mg slow IV (max 30–40 mg/24 h).",
                ),
                citations = listOf(cite("tx-pest-eddleston-ccare-2004", "After atropinization"), cite("tx-pest-eddleston-ccare-2004", "Atropine toxicity"), cite("tx-pest-eddleston-ccare-2004", "Active cooling"), cite("tx-pest-eddleston-ccare-2004", "After atropinization; Toxicity; Cooling")),
            ),
            Step(
                title = "Pralidoxime and decontamination",
                timing = "Decontaminate only after stabilisation",
                points = listOf(
                    "WHO regimen (disputed): pralidoxime 30 mg/kg IV over 10–20 min, then 8–10 mg/kg/h until recovery/7 days; avoid in carbamates.",
                    "Avoid rapid pralidoxime (vomiting, tachycardia, hypertension); Indian open protocol: 2 g load then 1 g/h × 48 h.",
                    "Ipecac is contraindicated; consider lavage only for highly toxic pesticide <1–2 h in calm/intubated patient; no proven charcoal benefit.",
                    "Aspiration causes chemical pneumonitis (suspect infection if fever >48 h/focal consolidation); MgSO4 4 g IV lacks mortality benefit.",
                ),
                doses = listOf(
                    StepDose("Pralidoxime — WHO loading dose (disputed)", WeightDose(30.0, 30.0, "mg", "once IV over 10–20 min"), note = "Not for carbamates; rapid infusion causes vomiting, tachycardia, hypertension"),
                    StepDose("Pralidoxime — WHO maintenance infusion (disputed)", WeightDose(8.0, 10.0, "mg", "per hour continuous IV"), note = "Until clinical recovery (12–24 h off atropine/extubated) or 7 days"),
                ),
                citations = listOf(cite("tx-pest-eddleston-ccare-2004", "Oximes"), cite("tx-pest-pawar-lancet-2006", "Abstract"), cite("tx-pest-eddleston-ccare-2004", "Decontamination"), cite("tx-pest-vijayakumar-mgso4-op-2017", "Abstract"), cite("tx-pest-eddleston-ccare-2004", "Oximes; Decontamination"), cite("tx-pest-cochrane-oximes-2011", "Conclusions")),
            ),
            Step(
                title = "Intermediate syndrome and late monitoring",
                timing = "24–96 h after poisoning (weakness up to 18 days)",
                points = listOf(
                    "Watch from day 1 for intermediate syndrome (24–96 h; up to 114 h with methamidophos): neck-flexor, proximal, cranial-nerve, respiratory weakness.",
                    "Intubate and ventilate early for respiratory failure; monitor hourly for 6 h then every 2–3 h (watch recurrent fenthion crises).",
                    "Late encephalopathy (~4 d), cerebellar ataxia (8 d), extrapyramidal signs (5–15 d) occur; treat clinically without awaiting cholinesterase.",
                ),
                checklistTitle = "Early Intermediate-Syndrome Signs",
                checklist = listOf(
                    "Cannot lift head off pillow (weak neck flexion)",
                    "Accessory muscle use, nasal flaring, or tachypnoea",
                    "Cranial nerve palsies or failed extubation (laryngeal paralysis)",
                    "Proximal limb weakness with preserved distal strength",
                ),
                citations = listOf(cite("tx-pest-peter-ijccm-2014", "Delayed symptoms"), cite("tx-pest-eddleston-ccare-2004", "Respiratory failure"), cite("tx-pest-eddleston-ccare-2004", "Observation; Care"), cite("tx-pest-eddleston-ccare-2004", "Confirmation"), cite("tx-pest-eddleston-ccare-2004", "Observation; Care; Confirmation")),
            ),
        ),
        notes = listOf(
            ProtocolNote("Pralidoxime is disputed: Cochrane 2011 and placebo-controlled trials found no mortality or ventilation benefit for WHO/standard regimens, while an Indian open trial reported benefit at 1 g/h.", listOf(cite("tx-pest-cochrane-oximes-2011", "Conclusions"), cite("tx-pest-eddleston-pralidoxime-rct-2009", "Abstract"), cite("tx-pest-pawar-lancet-2006", "Abstract"), cite("tx-pest-kharel-pralidoxime-meta-2020", "Abstract"))),
        ),
        citations = listOf(cite("tx-pest-eddleston-ccare-2004"), cite("tx-pest-peter-ijccm-2014"), cite("tx-pest-pawar-lancet-2006"), cite("tx-pest-vijayakumar-mgso4-op-2017"), cite("tx-pest-cochrane-oximes-2011")),
    ),
    Protocol(
        id = "aluminium-phosphide",
        title = "Aluminium phosphide poisoning",
        summary = "Staff protection, refractory shock, metabolic acidosis, arrhythmia, and organ support for phosphine poisoning — no specific antidote exists.",
        keywords = listOf("Celphos", "ALP", "AlP", "aluminium phosphide", "aluminum phosphide", "wheat tablet", "rice tablet", "sulfas", "sulphas", "phosphine", "Quickphos", "Alphos", "Phostoxin", "metal phosphide", "garlic odour", "gehu ki goli"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "Recognise, protect staff and initial workup",
                timing = "Toxicity develops within minutes; hours 0–12 critical",
                points = listOf(
                    "Confirm product (dark-grey 3 g tablets release phosphine on acid/moisture contact; some herbal \"rice tablets\" are not AlP).",
                    "Protect staff with full face mask and rubber gloves — phosphine in vomit/stool is hazardous and can rarely ignite.",
                    "Order ECG (tachycardia 3–6 h, ST-T/conduction changes 6–12 h), CXR, glucose, ABG, electrolytes + Mg2+, LFT, RFT, and echo.",
                    "Treat on clinical suspicion without waiting for black silver-nitrate paper test on gastric aspirate.",
                ),
                checklistTitle = "Features of Phosphide Toxicity",
                checklist = listOf(
                    "History of Celphos, wheat tablet, or rice tablet ingestion (especially freshly opened pack)",
                    "Garlic or decaying-fish breath or vomitus odour",
                    "Early vomiting and epigastric pain",
                    "Refractory hypotension (76–100%) and metabolic acidosis",
                    "ST-T changes, conduction defects, or arrhythmias",
                ),
                citations = listOf(cite("tx-pest-gurjar-jets-2011", "Properties; Evaluation"), cite("tx-pest-gurjar-jets-2011", "Evaluation"), cite("tx-pest-dsilva-ijccm-2019", "Clinical Features"), cite("tx-pest-gurjar-jets-2011", "Clinical features; Management"), cite("tx-pest-gurjar-jets-2011", "Diagnosis"), cite("tx-pest-gurjar-jets-2011", "Properties; Clinical features; Diagnosis; Management")),
            ),
            Step(
                title = "Decontamination (within 1–2 h only)",
                timing = "Potential benefit only within 1–2 h",
                points = listOf(
                    "Never perform gut decontamination with an unprotected airway; no specific antidote exists.",
                    "Lavage controversial (speeds disintegration): Gurjar uses 1:10,000 KMnO4; Farahani favours vegetable oil/paraffin (warns KMnO4 causes haemolysis/methaemoglobinaemia).",
                ),
                citations = listOf(cite("tx-pest-gurjar-jets-2011", "Exposure"), cite("tx-pest-proudfoot-clintox-2009", "Abstract"), cite("tx-pest-dsilva-ijccm-2019", "Decontamination"), cite("tx-pest-farahani-ijccm-2016", "Decontamination")),
            ),
            Step(
                title = "Shock and haemodynamic support",
                timing = "Continuous invasive ICU monitoring",
                points = listOf(
                    "Start invasive monitoring (CVP/PAWP) and early vasoactives: norepinephrine or phenylephrine preferred (dopamine/dobutamine provoke arrhythmias).",
                    "Keep anti-arrhythmics, DC cardioversion, and temporary pacing at bedside; shock often resists large crystalloid volumes.",
                    "Consider VA-ECMO or IABP where available for refractory cardiogenic shock (myocardial dysfunction reverses by day 5 in survivors).",
                ),
                citations = listOf(cite("tx-pest-gurjar-jets-2011", "Hemodynamic support"), cite("tx-pest-farahani-ijccm-2016", "Hypotension"), cite("tx-pest-farahani-ijccm-2016", "ECMO"), cite("tx-pest-gurjar-jets-2011", "Hemodynamic; Prognostic"), cite("tx-pest-gurjar-jets-2011", "Hemodynamic support; Prognostic"), cite("tx-pest-farahani-ijccm-2016", "Hypotension; ECMO")),
            ),
            Step(
                title = "Acidosis, methaemoglobinaemia and organ support",
                points = listOf(
                    "Give IV sodium bicarbonate for severe acidosis (Farahani: pH <7 only); dialyse for AKI, severe acidosis, or fluid overload.",
                    "Oxygen-refractory cyanosis: confirm methaemoglobin (co-oximetry) and give 1% methylene blue 2 mg/kg IV over 5 min (avoid in G6PD deficiency).",
                    "Correct hypoglycaemia; routine MgSO4 (3 g/3 h then 6 g/24 h × 3–5 d) and NAC lack mortality benefit.",
                    "Most deaths ≤24 h (mortality 30–100%, >1.5 g usually fatal); check dysphagic survivors for oesophageal strictures and offer counselling.",
                ),
                doses = listOf(
                    StepDose("Methylene blue 1% — symptomatic methaemoglobinaemia", WeightDose(2.0, 2.0, "mg", "per dose IV over 5 min"), note = "Confirm by co-oximetry; contraindicated in G6PD deficiency (haemolysis risk); repeatable if cyanosis persists"),
                ),
                citations = listOf(cite("tx-pest-gurjar-jets-2011", "Organ failures"), cite("tx-pest-farahani-ijccm-2016", "Acidosis"), cite("tx-pest-fda-provayblue-label", "Contraindications"), cite("tx-pest-gurjar-jets-2011", "Organ failures; Supportive"), cite("tx-pest-dsilva-ijccm-2019", "Supportive"), cite("tx-pest-gurjar-jets-2011", "Prognostic; Supportive"), cite("tx-pest-gurjar-jets-2011", "Organ failures; Supportive; Prognostic"), cite("tx-pest-farahani-ijccm-2016", "Acidosis; Cardiac; Antioxidant"), cite("tx-pest-fda-provayblue-label", "Contraindications; Warnings")),
            ),
        ),
        notes = listOf(
            ProtocolNote("Decontamination conflicts: KMnO4 and coconut oil lavage are widely practised in India, but reviews report lacking evidence and potential KMnO4 haemolysis/methaemoglobinaemia; sources also disagree on charcoal.", listOf(cite("tx-pest-dsilva-ijccm-2019", "Decontamination"), cite("tx-pest-farahani-ijccm-2016", "Decontamination"), cite("tx-pest-gurjar-jets-2011", "Exposure"))),
            ProtocolNote("Evidence for MgSO4, NAC, trimetazidine, and high-dose insulin is conflicting or limited to small studies/case reports; no antidote exists.", listOf(cite("tx-pest-gurjar-jets-2011", "Supportive"), cite("tx-pest-farahani-ijccm-2016", "Cardiac; Antioxidant"), cite("tx-pest-proudfoot-clintox-2009", "Abstract"))),
        ),
        citations = listOf(cite("tx-pest-gurjar-jets-2011"), cite("tx-pest-dsilva-ijccm-2019"), cite("tx-pest-proudfoot-clintox-2009"), cite("tx-pest-farahani-ijccm-2016"), cite("tx-pest-fda-provayblue-label")),
    ),
    Protocol(
        id = "zinc-phosphide",
        title = "Zinc phosphide poisoning",
        summary = "Delayed-onset metal phosphide rodenticide causing abdominal X-ray radiopacity, misleading symptom-free intervals, and acute liver failure at 3–6 days.",
        keywords = listOf("zinc phosphide", "ZnP", "Zn3P2", "Rat kill", "ratkill", "rat poison", "rodenticide", "rat powder", "metal phosphide", "phosphine", "rat bait"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "Identify product and protect staff",
                points = listOf(
                    "Identify agent (zinc phosphide: grey powder; AlP: dark crystals; yellow phosphorus: waxy paste) and call AIIMS NPIC.",
                    "Protect staff from phosphine gas released in vomit and stool on contact with gastric acid.",
                ),
                citations = listOf(cite("tx-pest-dsilva-ijccm-2019", "General Approach"), cite("tx-pest-dsilva-ijccm-2019", "Aluminium and Zinc Phosphide"), cite("tx-pest-dsilva-ijccm-2019", "General Approach; Aluminium and Zinc Phosphide")),
            ),
            Step(
                title = "Expected delayed course and serial tests",
                timing = "Phases over hours to 6 days — do not discharge early",
                points = listOf(
                    "30–60% are initially asymptomatic; GI burning/vomiting starts at 2–6 h, followed by a misleading symptom-free interval at 12–24 h.",
                    "Acute liver failure appears at 3–6 days (plus cardiomyopathy, AKI, pulmonary oedema, rhabdomyolysis); observe in ICU for 48–72 h.",
                    "Obtain abdominal X-ray for radiopaque stomach contents (negative film does not exclude toxicity) and serial ALT, AST, INR, ABG, glucose.",
                ),
                citations = listOf(cite("tx-pest-faress-jnma-zinc-2025", "Clinical manifestations"), cite("tx-pest-dsilva-ijccm-2019", "General Approach"), cite("tx-pest-faress-jnma-zinc-2025", "Diagnostic testing"), cite("tx-pest-faress-jnma-zinc-2025", "Clinical manifestations; Diagnostic testing")),
            ),
            Step(
                title = "Decontamination cautions",
                points = listOf(
                    "Consider gastric emptying only if early and X-ray shows abundant gastric toxin; avoid in delayed presentation.",
                    "Exercise extreme caution with gastric lavage (liberates phosphine endangering staff); charcoal, coconut oil, and PEG-ELS lack proven outcome benefit.",
                ),
                citations = listOf(cite("tx-pest-faress-jnma-zinc-2025", "Management"), cite("tx-pest-dsilva-ijccm-2019", "General Approach")),
            ),
            Step(
                title = "Supportive care and liver failure",
                points = listOf(
                    "No specific antidote exists; manage shock and metabolic acidosis as in Aluminium phosphide poisoning.",
                    "Monitor closely for acute liver failure, coagulopathy, and cardiotoxicity; refer early for liver transplant if liver failure progresses.",
                    "Antioxidants (NAC, L-carnitine, vitamin E, CoQ10) lack randomised zinc-phosphide trials.",
                ),
                citations = listOf(cite("tx-pest-proudfoot-clintox-2009", "Abstract"), cite("tx-pest-faress-jnma-zinc-2025", "Management"), cite("tx-pest-mohanka-jceh-2020", "Abstract")),
            ),
        ),
        notes = listOf(
            ProtocolNote("Zinc phosphide mortality varies widely across series (1.3–7% vs 37–100% extrapolated from aluminium phosphide); rigorous trials are lacking.", listOf(cite("tx-pest-faress-jnma-zinc-2025", "Introduction"), cite("tx-pest-dsilva-ijccm-2019", "Aluminium and Zinc Phosphide"))),
        ),
        citations = listOf(cite("tx-pest-dsilva-ijccm-2019"), cite("tx-pest-faress-jnma-zinc-2025"), cite("tx-pest-proudfoot-clintox-2009"), cite("tx-pest-mohanka-jceh-2020")),
    ),
    Protocol(
        id = "yellow-phosphorus",
        title = "Yellow phosphorus rodenticide poisoning",
        summary = "Rat-paste (Ratol) poisoning: 3-stage delayed hepatotoxicity over 72 h, no antidote, ICU liver-failure care, and early transplant evaluation.",
        keywords = listOf("Ratol", "rat paste", "rodenticide paste", "yellow phosphorus", "white phosphorus", "rat killer", "phosphorus paste", "firecracker poisoning", "acute liver failure", "luminescent vomitus"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "Recognise exposure and 3-stage course",
                timing = "Day 0–1 GI; day 1–3 silent; >72 h acute liver failure",
                points = listOf(
                    "Pastes (e.g. Ratol) contain 2–5% yellow phosphorus; lethal dose ~1 mg/kg (fulminant >1–2 g); garlic odour, luminescent vomit/stool.",
                    "0–24 h: GI irritation; 24–72 h: silent LFT/bilirubin rise; >72 h: ALF, coagulopathy, shock, arrhythmias, AKI, coma.",
                    "Serum phosphorus does not estimate ingested dose; check calcium (hypocalcaemia) and admit to ICU for daily LFTs and RFTs.",
                ),
                citations = listOf(cite("tx-pest-dsilva-ijccm-2019", "Yellow Phosphorus"), cite("tx-pest-dsilva-ijccm-2019", "Mechanism"), cite("tx-pest-dsilva-ijccm-2019", "Yellow Phosphorus; Treatment"), cite("tx-pest-dsilva-ijccm-2019", "Yellow Phosphorus; Mechanism; Treatment")),
            ),
            Step(
                title = "Early decontamination and N-acetylcysteine",
                timing = "Within 24 h (best within a few hours)",
                points = listOf(
                    "No specific antidote exists; Mohanka uses plain saline lavage if ≤24 h (D'Silva describes 1:1000 KMnO4 lavage).",
                    "Indian liver-ICU protocol gives IV NAC 100 mg/kg over 24 h for 5 days (observational evidence only).",
                    "Avoid corticosteroids (not useful, possibly harmful).",
                ),
                citations = listOf(cite("tx-pest-dsilva-ijccm-2019", "Treatment"), cite("tx-pest-mohanka-jceh-2020", "Table 1"), cite("tx-pest-jubin-nac-ijccm-2024", "Abstract")),
            ),
            Step(
                title = "Acute liver failure care",
                timing = "Monitor closely days 3–5 post-ingestion",
                points = listOf(
                    "Nurse 30° head-up; check encephalopathy/pupils hourly; electively intubate for severe hepatic encephalopathy (grade ≥2–3).",
                    "INR >2: give vitamin K 10 mg IV × 3 days; reserve FFP, cryoprecipitate, platelets, tranexamic acid for active bleeding.",
                    "Use CVVHDF for AKI, severe lactic acidosis, or ammonia >150 µmol/L twice (>200 once); give lactulose and rifaximin.",
                    "Monitor glucose, electrolytes, lactate, ammonia, and daily SOFA score; consider plasmapheresis for systemic inflammation.",
                ),
                citations = listOf(cite("tx-pest-mohanka-jceh-2020", "Table 1; Management"), cite("tx-pest-mohanka-jceh-2020", "Table 1")),
            ),
            Step(
                title = "Prognosis and transplant referral",
                timing = "Evaluate for liver transplant from admission",
                points = listOf(
                    "Refer early to a liver-transplant centre; list super-urgent if King's College or NHSBT criteria met and worsening despite medical care.",
                    "Spontaneous survival associated with <17.5 g ingested, no cardiotoxicity, HE <grade 3, peak lactate <5.8, and peak SOFA <14.5.",
                    "Transplant requires recovery from marrow suppression and cardiotoxicity, without irreversible brain injury or systemic infection.",
                ),
                citations = listOf(cite("tx-pest-mohanka-jceh-2020", "Table 1; Results"), cite("tx-pest-mohanka-jceh-2020", "Results; Table 3"), cite("tx-pest-mohanka-jceh-2020", "Table 1"), cite("tx-pest-mohanka-jceh-2020", "Results; Table 1; Table 3")),
            ),
        ),
        notes = listOf(
            ProtocolNote("Lavage additives differ: D'Silva describes 1:1000 KMnO4, whereas Mohanka advises against KMnO4, charcoal, and copper sulfate (plain saline only).", listOf(cite("tx-pest-dsilva-ijccm-2019", "Treatment"), cite("tx-pest-mohanka-jceh-2020", "Table 1"))),
        ),
        citations = listOf(cite("tx-pest-dsilva-ijccm-2019"), cite("tx-pest-mohanka-jceh-2020"), cite("tx-pest-jubin-nac-ijccm-2024")),
    ),
    Protocol(
        id = "superwarfarin",
        title = "Superwarfarin (bromadiolone, brodifacoum) poisoning",
        summary = "Delayed coagulopathy from long-acting anticoagulant rodenticides requiring prolonged high-dose vitamin K1 and plasma/PCC for active bleeding.",
        keywords = listOf("superwarfarin", "bromadiolone", "brodifacoum", "difenacoum", "flocoumafen", "coumarin", "hydroxycoumarin", "anticoagulant rodenticide", "LAAR", "rat poison", "vitamin K", "high INR", "prolonged PT"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "Recognise delayed bleeding and test coagulation",
                timing = "INR rises ~48 h post-exposure; may be delayed days to weeks",
                points = listOf(
                    "Coagulopathy appears 24–48 h post-ingestion once clotting factors fall <30% (brodifacoum half-life 16–34 days; persists 16–270 days).",
                    "Suspect in unexplained prolonged PT/INR and aPTT with multisite bleeding (haematuria, epistaxis, ecchymoses, intracranial) without DIC.",
                    "Monitor daily PT/INR and aPTT after exposure; factor assays (II, VII, IX, X, V) support diagnosis.",
                ),
                checklistTitle = "Clues to Superwarfarin Poisoning",
                checklist = listOf(
                    "Unexplained prolonged PT/INR and aPTT",
                    "Multisite bleeding without DIC",
                    "No clotting-factor inhibitor present",
                    "Rodenticide access (even if initially asymptomatic)",
                ),
                citations = listOf(cite("tx-pest-dsilva-ijccm-2019", "Coumarins; Table 2"), cite("tx-pest-dsilva-ijccm-2019", "Coumarins"), cite("tx-pest-king-laar-tmr-2015", "Abstract"), cite("tx-pest-dsilva-ijccm-2019", "Diagnosis"), cite("tx-pest-dsilva-ijccm-2019", "Coumarins; Diagnosis; Table 2")),
            ),
            Step(
                title = "Vitamin K1 therapy",
                timing = "Factors rise at 6–8 h; maintain weeks to months (28–730 days)",
                points = listOf(
                    "Clotting factors rise 6–8 h after vitamin K1; check INR at 6–8 h and repeat if prolonged.",
                    "No consensus dose: 0.1–3 mg/kg/d (max 500–800 mg/d); bleeding 50–100 mg IV, then ~100 mg PO/d (mean 168 d).",
                    "Avoid IV/IM vitamin K1 unless SC/PO unfeasible due to fatal anaphylaxis risk; never infuse IV faster than 1 mg/min.",
                ),
                citations = listOf(cite("tx-pest-dsilva-ijccm-2019", "Treatment"), cite("tx-pest-fda-phytonadione-label", "Dosage"), cite("tx-pest-king-laar-tmr-2015", "Abstract"), cite("tx-pest-fda-phytonadione-label", "Boxed warning"), cite("tx-pest-fda-phytonadione-label", "Boxed warning; Dosage")),
            ),
            Step(
                title = "Active bleeding: plasma and PCC",
                points = listOf(
                    "Give fresh frozen plasma (FFP) or prothrombin complex concentrate (PCC) immediately for active or life-threatening bleeding.",
                    "Watch for paradoxical thrombosis with haemostatic products (PCC/rFVIIa); consider plasma exchange if vitamin K anaphylaxis occurs.",
                ),
                citations = listOf(cite("tx-pest-dsilva-ijccm-2019", "Treatment"), cite("tx-pest-king-laar-tmr-2015", "Abstract")),
            ),
            Step(
                title = "Follow-up and rebound coagulopathy",
                points = listOf(
                    "Continue long-term high-dose oral vitamin K1 and serial INR checks because rebound coagulopathy occurs as toxin persists in liver.",
                    "Phenobarbital has been used as an adjunct to speed metabolism; monitor closely for anaphylaxis with vitamin K1.",
                ),
                citations = listOf(cite("tx-pest-king-laar-tmr-2015", "Abstract"), cite("tx-pest-dsilva-ijccm-2019", "Treatment")),
            ),
        ),
        notes = listOf(
            ProtocolNote("Case-report superwarfarin doses (50–100 mg IV acutely; 100 mg PO daily) exceed the FDA warfarin-reversal label limit (2.5–10 mg, up to 25–50 mg single dose).", listOf(cite("tx-pest-king-laar-tmr-2015", "Abstract"), cite("tx-pest-fda-phytonadione-label", "Dosage"))),
        ),
        citations = listOf(cite("tx-pest-dsilva-ijccm-2019"), cite("tx-pest-king-laar-tmr-2015"), cite("tx-pest-fda-phytonadione-label")),
    ),
)
