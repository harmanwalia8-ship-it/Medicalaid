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
 * Pesticide and rodenticide poisoning: organophosphate/carbamate, aluminium phosphide, zinc phosphide,
 * yellow phosphorus, superwarfarins. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them; every point is attributed.
 * - The ICMR Standard Treatment Workflows list was checked: no STW for organophosphate, phosphide or rodenticide
 *   poisoning is listed. Indian sources used are ISCCM (Indian J Crit Care Med) papers and Indian centre series.
 * - Phosphide and yellow phosphorus poisoning have no antidote; the app reports what the sources say, it does not instruct.
 * - Locators are section or table names of the cited paper (no page numbers were verified).
 */

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

private const val EDD04 = "tx-pest-eddleston-ccare-2004"
private const val EDD09 = "tx-pest-eddleston-pralidoxime-rct-2009"
private const val PAWAR = "tx-pest-pawar-lancet-2006"
private const val COCH = "tx-pest-cochrane-oximes-2011"
private const val KHAREL = "tx-pest-kharel-pralidoxime-meta-2020"
private const val PETER = "tx-pest-peter-ijccm-2014"
private const val MGOP = "tx-pest-vijayakumar-mgso4-op-2017"
private const val DSILVA = "tx-pest-dsilva-ijccm-2019"
private const val GURJAR = "tx-pest-gurjar-jets-2011"
private const val FARAHANI = "tx-pest-farahani-ijccm-2016"
private const val PROUD = "tx-pest-proudfoot-clintox-2009"
private const val ZNP = "tx-pest-faress-jnma-zinc-2025"
private const val MOHANKA = "tx-pest-mohanka-jceh-2020"
private const val JUBIN = "tx-pest-jubin-nac-ijccm-2024"
private const val KING = "tx-pest-king-laar-tmr-2015"
private const val FDAK = "tx-pest-fda-phytonadione-label"
private const val FDAMB = "tx-pest-fda-provayblue-label"

internal val pesticideProtocols: List<Protocol> = listOf(
    // =========================================================================================
    Protocol(
        id = "organophosphate",
        category = ProtocolCategory.Poisoning,
        title = "Organophosphate and carbamate poisoning",
        summary = "Cholinergic toxidrome, atropine titration, the pralidoxime controversy, intermediate syndrome and ventilation — as stated by Eddleston et al., Cochrane, ISCCM authors and RCTs.",
        keywords = listOf(
            "OP", "OPC", "organophosphorus", "organophosphorous", "insecticide", "pesticide", "carbamate", "chlorpyrifos",
            "monocrotophos", "dichlorvos", "DDVP", "malathion", "parathion", "dimethoate", "fenthion", "profenofos",
            "Baygon", "propoxur", "cholinesterase", "atropine", "pralidoxime", "PAM", "2-PAM", "intermediate syndrome",
            "pinpoint pupils", "miosis", "bronchorrhoea", "SLUDGE", "kitnashak", "keet nashak", "spray poisoning",
        ),
        steps = listOf(
            Step(
                title = "Recognise the cholinergic toxidrome",
                timing = "At first contact",
                points = listOf(
                    "Eddleston et al. 2004: five signs used routinely — miosis, excessive sweating, poor air entry from bronchorrhoea and bronchospasm, bradycardia, hypotension.",
                    "Eddleston et al. 2004: pinpoint pupils plus sweating, in a region where these pesticides are common, suggest OP or carbamate poisoning needing atropine.",
                    "Eddleston et al. 2004: heart rate may be normal or fast; if no signs are present there is no clinical cholinergic poisoning yet, but signs may appear later.",
                    "Eddleston et al. 2004: pro-poison (thion) OPs convert to the active form later, and fat-soluble OPs such as fenthion leach out of fat, so observe closely.",
                    "Peter et al. 2014: muscarinic effects (salivation, bronchorrhoea, bradycardia) are reversed by atropine; nicotinic muscle weakness is not.",
                ),
                checklistTitle = "Cholinergic signs (Eddleston et al. 2004 and Peter et al. 2014)",
                checklist = listOf(
                    "Pinpoint pupils (symmetrical)",
                    "Sweating, drenched skin",
                    "Bronchorrhoea, wheeze, crepitations",
                    "Bradycardia (may be absent)",
                    "Hypotension",
                    "Salivation, vomiting, diarrhoea, incontinence",
                    "Fasciculations, muscle weakness",
                    "Agitation, confusion, convulsions, coma",
                    "Garlic or kerosene-like smell of pesticide",
                ),
                citations = listOf(cite(EDD04, "Recognition of OP/carbamate poisoning"), cite(PETER, "Introduction; Acute onset symptoms")),
            ),
            Step(
                title = "Airway, oxygen and seizures",
                timing = "Before decontamination",
                points = listOf(
                    "Eddleston et al. 2004: check airway, breathing, circulation; high-flow oxygen if available; left lateral position, head-down, to reduce aspiration.",
                    "Eddleston et al. 2004: treat convulsions immediately with IV diazepam; record a baseline Glasgow Coma Score and pulse oximetry.",
                    "Eddleston et al. 2004: do not delay atropine if oxygen is unavailable; there is no good evidence that atropine in a cyanosed patient causes harm.",
                    "Eddleston et al. 2004: if unconscious, place an endotracheal tube even if a Guedel airway works well, to minimise aspiration and aid respiratory care.",
                ),
                citations = listOf(cite(EDD04, "Initial assessment of the unconscious patient; Care of the airway")),
            ),
            Step(
                title = "Atropine loading to atropinisation",
                timing = "Minutes: titrate every 3–5 min",
                points = listOf(
                    "Eddleston et al. 2004: unconscious patient — atropine 1.8–3 mg (three to five 0.6 mg vials) rapidly IV into a fast-flowing drip, with 500–1000 ml saline.",
                    "Eddleston et al. 2004: if presentation unclear, atropine 0.6–1 mg; a rise in heart rate over 20–25/min with flushing suggests no significant cholinergic poisoning.",
                    "Eddleston et al. 2004: reassess at 3–5 min; if no consistent improvement, double the dose and keep doubling. Do not repeat the same dose.",
                    "Eddleston et al. 2004: aim to clear the chest. Atropinised = no wheeze, heart rate over 80/min, systolic BP over 80 mmHg, dry axillae, pupils no longer pinpoint.",
                    "Eddleston et al. 2004: a heart rate of 120–140/min is not the target and suggests atropine toxicity. Pupils may dilate late; wait for other end-points.",
                ),
                checklistTitle = "Atropinisation end-points (Eddleston et al. 2004, Table 2)",
                checklist = listOf(
                    "Chest clear, no wheeze",
                    "Heart rate over 80 beats/min",
                    "Systolic BP over 80 mmHg",
                    "Pupils no longer pinpoint",
                    "Axillae dry",
                ),
                citations = listOf(cite(EDD04, "Loading with atropine and IV fluids; Table 2; Continuation of bolus atropine loading")),
            ),
            Step(
                title = "Atropine infusion and toxicity",
                timing = "Review every 15 min at first, then hourly for 6 h",
                points = listOf(
                    "Eddleston et al. 2004: once atropinised, infuse 10–20% of the total loading dose per hour; use less if more than 30 mg was needed to load.",
                    "Eddleston et al. 2004: infusion rates above 3–5 mg/h are rarely necessary and need frequent review; larger doses may be needed if oximes are unavailable.",
                    "Eddleston et al. 2004: if cholinergic signs return, give bolus atropine until they clear and raise the infusion rate.",
                    "Eddleston et al. 2004: toxicity is suggested by confusion, fever and absent bowel sounds together; stop the infusion, recheck at 30 min, restart at 70–80% of the previous rate.",
                    "Eddleston et al. 2004: control fever early with fan and wet towels; agitation — diazepam 10 mg slow IV, up to 30–40 mg per 24 h; avoid restraint alone.",
                ),
                citations = listOf(cite(EDD04, "Atropine treatment after atropinization; Atropine toxicity; Active cooling and sedation")),
            ),
            Step(
                title = "Pralidoxime: regimen and controversy",
                timing = "Evidence disputed; regimens differ",
                points = listOf(
                    "Eddleston et al. 2004: WHO guidelines recommend pralidoxime 30 mg/kg over 10–20 min, then 8–10 mg/kg per hour until recovery or 7 days; oximes not recommended for carbamates.",
                    "Eddleston et al. 2004: too-rapid administration causes vomiting, tachycardia and hypertension. Oxime benefit depends on OP type, poison load, delay and dose.",
                    "Cochrane 2011: evidence insufficient to say whether oximes harm or help; the WHO regimen is not supported. One WHO-dose RCT showed no benefit and a trend to harm.",
                    "Eddleston et al. 2009 RCT (2 g then 0.5 g/h, placebo-controlled, n=235): mortality 24.8% vs 15.8%, not significant; no benefit on intubation.",
                    "Pawar et al. 2006 (India, open RCT, n=200, comparing two oxime regimens): 2 g load then 1 g/h for 48 h reduced atropine need and ventilation; open-label, no placebo.",
                    "Kharel et al. 2020 meta-analysis (6 RCTs, n=646): no significant effect on mortality or ventilation; intermediate syndrome more frequent with pralidoxime (RR 1.63).",
                ),
                doses = listOf(
                    StepDose(
                        label = "Pralidoxime — WHO loading dose (as quoted by Eddleston et al. 2004)",
                        dose = WeightDose(30.0, 30.0, "mg", "once, over 10–20 min"),
                        note = "Disputed: Cochrane 2011 states this regimen is not supported by trial evidence",
                    ),
                    StepDose(
                        label = "Pralidoxime — WHO maintenance infusion (as quoted by Eddleston et al. 2004)",
                        dose = WeightDose(8.0, 10.0, "mg", "per hour, continuous IV"),
                        note = "Until clinical recovery (12–24 h after atropine no longer needed or extubated) or 7 days, whichever is later",
                    ),
                ),
                citations = listOf(
                    cite(EDD04, "Oximes and other therapies"),
                    cite(COCH, "Authors' conclusions"),
                    cite(EDD09, "Abstract: Methods and findings"),
                    cite(PAWAR, "Abstract: Findings"),
                    cite(KHAREL, "Abstract: Results"),
                ),
            ),
            Step(
                title = "Decontamination and supportive measures",
                timing = "Only after stabilisation",
                points = listOf(
                    "Eddleston et al. 2004: do not perform gastric decontamination until the patient is stable and, if needed, intubated. Ipecac is contraindicated.",
                    "Eddleston et al. 2004: lavage only for a highly toxic pesticide within 1–2 h, in consenting calm or intubated patients; efficacy of lavage and charcoal is unknown.",
                    "Eddleston et al. 2004: no evidence that single or multiple-dose activated charcoal gives clinical benefit after pesticide poisoning.",
                    "Eddleston et al. 2004: aspiration initially causes chemical pneumonitis, not pneumonia; pneumonia if fever persists beyond 48 h or focal consolidation on X-ray.",
                    "Vijayakumar et al. 2017 (Bengaluru RCT, n=100): 4 g MgSO4 in 30 min lowered atropine need and intubation; no mortality difference. Single-centre.",
                ),
                citations = listOf(cite(EDD04, "Should gastric decontamination be performed?; Oximes and other therapies"), cite(MGOP, "Abstract")),
            ),
            Step(
                title = "Intermediate syndrome and respiratory failure",
                timing = "24–96 h after poisoning; watch from day 1",
                points = listOf(
                    "Peter et al. 2014: intermediate syndrome — proximal limb, neck flexor, cranial-nerve and respiratory muscle weakness 24–96 h after poisoning, after cholinergic phase settled.",
                    "Peter et al. 2014: weakness may last up to 18 days; delayed onset reported at 114 h with lipophilic methamidophos; laryngeal paralysis can present as failed extubation.",
                    "Eddleston et al. 2004: early sign is weak neck flexion (cannot lift head); then accessory muscle use, cranial nerve palsies; most deaths are from respiratory failure.",
                    "Eddleston et al. 2004: measure tidal or minute volume and blood gases; a locally agreed trigger should prompt sedation, intubation and ventilation.",
                ),
                checklistTitle = "Early intermediate-syndrome signs (Eddleston et al. 2004)",
                checklist = listOf(
                    "Cannot lift head off pillow (neck flexion weak)",
                    "Accessory muscles, nasal flaring, tachypnoea",
                    "Cranial nerve palsies",
                    "Proximal limb weakness, distal strength retained",
                ),
                citations = listOf(cite(PETER, "Delayed onset symptoms"), cite(EDD04, "Observation for impending respiratory failure")),
            ),
            Step(
                title = "Monitoring and late problems",
                timing = "First 6 h hourly; then every 2–3 h; days to weeks",
                points = listOf(
                    "Eddleston et al. 2004: patients are unstable and need regular observation; atropine requirement and general state can change quickly.",
                    "Eddleston et al. 2004: recurrent cholinergic crises occur with fat-soluble OPs such as fenthion, leaking from fat over days to weeks.",
                    "Peter et al. 2014: delayed encephalopathy or coma at about 4 days, cerebellar ataxia at 8 days and extrapyramidal signs at 5–15 days have been reported.",
                    "Eddleston et al. 2004: cholinesterase assays (butyrylcholinesterase, red-cell) confirm exposure but cannot be done on the ward; emergency therapy follows clinical features.",
                ),
                citations = listOf(cite(EDD04, "Care after the first few hours; Confirmation of exposure"), cite(PETER, "Delayed onset symptoms")),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Pralidoxime: WHO-recommended 30 mg/kg + 8–10 mg/kg/h (quoted by Eddleston 2004) is questioned by Cochrane 2011, whose review found the regimen unsupported. Indian open RCT (Pawar 2006) reported benefit with a 1 g/h regimen; placebo-controlled RCT (Eddleston 2009) did not.",
                listOf(cite(EDD04, "Oximes and other therapies"), cite(COCH, "Authors' conclusions"), cite(PAWAR, "Abstract"), cite(EDD09, "Abstract")),
            ),
            ProtocolNote(
                "No ICMR Standard Treatment Workflow for organophosphate poisoning was found on the ICMR STW list. The atropine titration here is from a Sri Lankan protocol; the app has not verified an Indian national protocol or a WHO primary document.",
                listOf(cite(EDD04, "Introduction")),
            ),
            ProtocolNote(
                "Evidence for adjuncts (magnesium sulphate) comes from small single-centre trials without mortality benefit.",
                listOf(cite(MGOP, "Abstract")),
            ),
        ),
        citations = listOf(cite(EDD04), cite(COCH), cite(PETER)),
    ),

    // =========================================================================================
    Protocol(
        id = "aluminium-phosphide",
        category = ProtocolCategory.Poisoning,
        title = "Aluminium phosphide poisoning",
        summary = "Phosphine toxicity: shock, metabolic acidosis, arrhythmia. No antidote; each decontamination and supportive measure is reported with its evidence status.",
        keywords = listOf(
            "Celphos", "ALP", "AlP", "aluminium phosphide", "aluminum phosphide", "wheat tablet", "wheat pill", "rice tablet",
            "sulfas", "sulphas", "phosphine", "grain fumigant", "Quickphos", "Alphos", "Phostoxin", "metal phosphide",
            "fumigant tablet", "garlic odour", "refractory shock", "gehu ki goli",
        ),
        steps = listOf(
            Step(
                title = "Recognise and protect staff",
                timing = "At first contact; toxicity within minutes",
                points = listOf(
                    "Gurjar et al. 2011: tablets are dark grey, 3 g, sold as Celphos, Alphos, Quickphos and others; they release phosphine on contact with gastric acid or moisture.",
                    "Gurjar et al. 2011: after ingestion toxic features usually develop within a few minutes; garlic or decaying-fish breath odour.",
                    "D'Silva and Krishna 2019: phosphine in vomitus and faeces can harm the treating team; handle and discard with precautions.",
                    "Gurjar et al. 2011: staff should wear full face mask and rubber gloves during decontamination; spontaneous ignition is rare but possible.",
                    "Gurjar et al. 2011: some local 'rice tablets' are herbal, not aluminium phosphide; management differs, so confirm the product.",
                ),
                citations = listOf(cite(GURJAR, "Physical and chemical properties; Clinical features; Initial evaluation"), cite(DSILVA, "Aluminium and Zinc Phosphide Poisoning: Clinical Features")),
            ),
            Step(
                title = "Clinical features and early assessment",
                timing = "Hours 0–12 are critical",
                points = listOf(
                    "Gurjar et al. 2011: mild — nausea, vomiting, abdominal pain, tachycardia. Severe — refractory hypotension, arrhythmia, ECG changes, myocarditis, pulmonary oedema, ARDS, later hepatic and renal failure, DIC.",
                    "Gurjar et al. 2011: ECG course — sinus tachycardia in first 3–6 h, ST-T and conduction changes at 6–12 h, later arrhythmias; hypotension in 76–100%.",
                    "Farahani et al. 2016: refractory hypotension and metabolic acidosis appear within the first hours; some methaemoglobinaemia is usual; haemolysis reported in G6PD deficiency.",
                    "Gurjar et al. 2011: initial tests — ECG, chest X-ray, glucose, ABG, electrolytes with magnesium, LFT, RFT; repeat ECG and echocardiography.",
                    "Gurjar et al. 2011: silver nitrate paper on gastric aspirate (turns black) can confirm phosphine; start management on suspicion without waiting.",
                ),
                checklistTitle = "Features pointing to phosphide toxicity (Gurjar et al. 2011)",
                checklist = listOf(
                    "History of Celphos / rice tablet / wheat tablet ingestion",
                    "Garlic or fish-like odour on breath or vomitus",
                    "Early vomiting, epigastric pain",
                    "Refractory hypotension",
                    "Metabolic acidosis",
                    "ST-T changes, arrhythmia, conduction defects",
                ),
                citations = listOf(cite(GURJAR, "Clinical features; Diagnosis; Management"), cite(FARAHANI, "Clinical Manifestations")),
            ),
            Step(
                title = "Decontamination: what sources say",
                timing = "Benefit only if within about 1–2 h (Gurjar et al. 2011)",
                points = listOf(
                    "Gurjar et al. 2011: gut decontamination is useful within 1–2 h and should not be done with an unprotected airway; potassium permanganate 1:10,000 lavage described as oxidising phosphine.",
                    "D'Silva and Krishna 2019: gastric lavage is controversial (may speed disintegration); permanganate and coconut oil — evidence to back their use is lacking or insufficient.",
                    "Farahani et al. 2016: permanganate and charcoal criticised on chemical grounds, with haemolysis and methaemoglobinaemia reported after permanganate; vegetable oil or liquid paraffin favoured.",
                    "Gurjar et al. 2011: in vitro, vegetable oils and liquid paraffin inhibit phosphine release; coconut oil effect is from a single case report.",
                    "Gurjar et al. 2011: insufficient data for routine activated charcoal; D'Silva and Krishna 2019 state charcoal is beneficial. Sources disagree.",
                ),
                citations = listOf(
                    cite(GURJAR, "Decrease the exposure of poison"),
                    cite(DSILVA, "Treatment: Decontamination"),
                    cite(FARAHANI, "Gastric Decontamination"),
                ),
            ),
            Step(
                title = "Shock and haemodynamic support",
                timing = "Continuous invasive monitoring",
                points = listOf(
                    "Gurjar et al. 2011: severe poisoning needs invasive haemodynamic monitoring and early fluid plus vasoactive therapy, guided by CVP or PAWP.",
                    "Gurjar et al. 2011: norepinephrine or phenylephrine for refractory hypotension; dopamine and dobutamine cautiously as they provoke arrhythmias.",
                    "Gurjar et al. 2011: keep anti-arrhythmics, DC cardioversion and a temporary pacemaker ready at the bedside.",
                    "Farahani et al. 2016: shock often does not respond to large crystalloid volumes; hydroxyethyl starch proposed from a case report, with kidney and coagulation concerns.",
                    "Farahani et al. 2016: VA-ECMO series for refractory cardiogenic shock reported; Gurjar et al. 2011: IABP described in case reports.",
                ),
                citations = listOf(cite(GURJAR, "Hemodynamic support"), cite(FARAHANI, "Management of Severe Hypotension; Role of ECMO")),
            ),
            Step(
                title = "Acidosis, methaemoglobinaemia and organ support",
                points = listOf(
                    "Gurjar et al. 2011: IV sodium bicarbonate for mild to moderate acidosis or as rescue before dialysis; an 'aggressive correction of acidosis' bicarbonate protocol was reported to improve outcome (30% vs 55%).",
                    "Farahani et al. 2016: the same protocol is described as 55% survival, with no pH or base-excess difference between survivors and non-survivors and an overall very poor prognosis; Marashi and colleagues, as cited, limit bicarbonate to pH below 7.",
                    "Gurjar et al. 2011: if cyanosis does not respond to oxygen, check methaemoglobin by co-oximetry; symptomatic cases — methylene blue 1% 2 mg/kg over 5 min, repeatable.",
                    "Farahani et al. 2016: some cases of intravascular haemolysis were reported in G6PD-deficient phosphide patients. Provayblue label: G6PD deficiency is a contraindication to methylene blue because of the risk of haemolytic anaemia.",
                    "Gurjar et al. 2011: haemodialysis is probably not effective for phosphine but helps renal failure, severe acidosis or fluid overload; correct hypoglycaemia.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Methylene blue 1% — symptomatic methaemoglobinaemia (Gurjar et al. 2011)",
                        dose = WeightDose(2.0, 2.0, "mg", "IV over 5 min, repeatable if cyanosis persists"),
                        note = "Rule out methaemoglobinaemia first by co-oximetry or plasma level. Haemolysis is reported in G6PD-deficient phosphide patients (Farahani et al. 2016); methylene blue can cause haemolysis in G6PD deficiency (see product labelling), so check G6PD status or haemolysis where possible and do not repeat blindly.",
                    ),
                ),
                citations = listOf(
                    cite(GURJAR, "Early identification and managing other organ failures"),
                    cite(FARAHANI, "Management of Severe Metabolic Acidosis"),
                    cite(FDAMB, "Contraindications; Warnings and precautions: hemolytic anemia"),
                ),
            ),
            Step(
                title = "Specific and adjunct measures: evidence status",
                points = listOf(
                    "Gurjar et al. 2011: magnesium sulphate studies reported up to 50% lower mortality; regimens varied (e.g. 3 g over 3 h then 6 g/24 h for 3–5 days); routine use called questionable.",
                    "Gurjar et al. 2011: a meta-analysis by Siwach et al. found normal magnesium levels and no significant mortality difference. Farahani et al. 2016: Chugh and Siwach findings conflict.",
                    "Farahani et al. 2016: trimetazidine, digoxin and high-dose insulin are supported mainly by case reports; N-acetylcysteine and coenzyme Q10 by animal data.",
                    "Gurjar et al. 2011: antioxidants such as N-acetylcysteine need human trials before routine use. D'Silva and Krishna 2019: benefit on mortality not yet proven.",
                    "Proudfoot 2009: there is no antidote to phosphine or metal phosphide poisoning; supportive measures only, and many patients die despite intensive care.",
                ),
                citations = listOf(
                    cite(GURJAR, "Other supportive therapy"),
                    cite(FARAHANI, "Management of Cardiac Dysfunction; Antioxidant Therapy"),
                    cite(DSILVA, "Treatment: Supportive Treatment"),
                    cite(PROUD, "Abstract: Management"),
                ),
            ),
            Step(
                title = "Prognostic markers and monitoring",
                timing = "Most deaths within 24 h",
                points = listOf(
                    "Gurjar et al. 2011: mortality 30–100%; survival unlikely if more than 1.5 g ingested, though survival after 9 g or more is reported.",
                    "Gurjar et al. 2011: unexposed (freshly opened) tablets carry greater risk; D'Silva and Krishna 2019: danger greatest from a freshly opened container.",
                    "Gurjar et al. 2011: in 471 patients, arterial pH, serum bicarbonate and ECG abnormalities were poor prognostic factors.",
                    "Gurjar et al. 2011: other poor factors — shock, altered sensorium, high APACHE II, AKI, need for ventilation, absence of vomiting, hyperglycaemia.",
                    "Gurjar et al. 2011: early deaths mainly from arrhythmia; later from refractory shock, acidosis and ARDS. Reversible myocardial dysfunction normalised by day 5 in survivors.",
                    "Gurjar et al. 2011: survivors with swallowing difficulty need barium swallow or endoscopy for oesophageal complications; offer psychosocial counselling.",
                ),
                citations = listOf(cite(GURJAR, "Prognostic indices; Other supportive therapy"), cite(DSILVA, "Mode of Poisoning; Clinical Features")),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Evidence quality: no randomised trial of any treatment was read for this app. Gurjar 2011 and Farahani 2016 are narrative reviews resting on case reports and small studies; the two disagree on charcoal and on permanganate.",
                listOf(cite(GURJAR, "Management"), cite(FARAHANI, "Aluminum Phosphide Poisoning Therapy")),
            ),
            ProtocolNote(
                "Indian practice versus critique: potassium permanganate and coconut oil lavage are widely used in Indian hospitals; the cited reviews state evidence is lacking or from single case reports. A 2019 ISCCM review says permanganate evidence is lacking.",
                listOf(cite(DSILVA, "Treatment: Decontamination"), cite(FARAHANI, "Gastric Decontamination")),
            ),
            ProtocolNote(
                "No ICMR STW for aluminium phosphide was found on the ICMR list. Not verified: WHO IPCS Poison Information Monograph; AIIMS NPIC guidance; trimetazidine RCT results.",
                listOf(cite(GURJAR, "References")),
            ),
        ),
        citations = listOf(cite(GURJAR), cite(FARAHANI), cite(DSILVA)),
    ),

    // =========================================================================================
    Protocol(
        id = "zinc-phosphide",
        category = ProtocolCategory.Poisoning,
        title = "Zinc phosphide poisoning",
        summary = "Rodenticide that releases phosphine: delayed phases, liver failure, radiopaque stomach contents. Kept distinct from aluminium phosphide and yellow phosphorus.",
        keywords = listOf(
            "zinc phosphide", "ZnP", "Zn3P2", "Rat kill", "ratkill", "rat poison", "rodenticide", "rat powder",
            "grey rodenticide powder", "metal phosphide", "phosphine", "rat bait",
        ),
        steps = listOf(
            Step(
                title = "Identify the product",
                points = listOf(
                    "D'Silva and Krishna 2019: zinc phosphide is a grey crystalline powder; aluminium phosphide is dark yellow or dark grey crystals; yellow phosphorus is a waxy paste.",
                    "D'Silva and Krishna 2019: clinical features and treatment differ between rodenticides, so identify the compound; contact the AIIMS National Poison Information Centre.",
                    "D'Silva and Krishna 2019: metal phosphides react with moisture and acid to release phosphine; phosphine in vomit or stool can endanger staff.",
                    "Faress et al. 2025: mortality figures of 37–100% derive from aluminium phosphide studies; reported zinc phosphide mortality was 1.3% and 7% in two series.",
                ),
                citations = listOf(cite(DSILVA, "General Approach to Rodenticide Poisoning; Aluminium and Zinc Phosphide Poisoning"), cite(ZNP, "Introduction")),
            ),
            Step(
                title = "Expected course and features",
                timing = "Phases over hours to 6 days",
                points = listOf(
                    "Faress et al. 2025: 30–60% may be asymptomatic on arrival; at 2–6 h burning in mouth, throat and epigastrium, nausea, vomiting, diarrhoea.",
                    "Faress et al. 2025: a symptom-free interval at 12–24 h may mislead; transaminases, CRP and metabolic acidosis can already be abnormal, and patients may be discharged unaware.",
                    "Faress et al. 2025: acute liver failure appears 3–6 days after ingestion; cardiomyopathy, renal failure, pulmonary oedema and rhabdomyolysis may follow.",
                    "D'Silva and Krishna 2019: manifestations may not be seen at presentation and can develop over 48–72 h; manage in an ICU.",
                ),
                citations = listOf(cite(ZNP, "Clinical manifestations"), cite(DSILVA, "General Approach to Rodenticide Poisoning")),
            ),
            Step(
                title = "Investigations",
                points = listOf(
                    "Faress et al. 2025: radiopaque material on abdominal X-ray is most useful; a negative film does not exclude toxicity, though 90% of patients with a negative film stay asymptomatic.",
                    "Faress et al. 2025: check ALT, AST, INR, anion-gap acidosis; hyperglycaemia is associated with poor prognosis.",
                    "Faress et al. 2025: most patients have normal laboratory results at first presentation, so serial testing is needed.",
                ),
                citations = listOf(cite(ZNP, "Diagnostic testing; Clinical manifestations")),
            ),
            Step(
                title = "Decontamination: what sources say",
                timing = "Delayed onset allows time to act (Faress et al. 2025)",
                points = listOf(
                    "Faress et al. 2025: gastric emptying may help if X-ray shows much toxin still in the stomach; not recommended for delayed presentation.",
                    "Faress et al. 2025: coconut oil with bicarbonate lavage did not prevent poisoning in one case report; charcoal benefit is from a rat study and probably ineffective chemically.",
                    "Faress et al. 2025: no clinical outcome studies of whole-bowel irrigation; a cardiac arrest at 19 h after PEG-ELS is reported; castor oil cathartic suggested by Sanaei-Zadeh.",
                    "D'Silva and Krishna 2019: caution with gastric lavage in metal phosphide poisoning, as it may liberate more phosphine endangering staff.",
                ),
                citations = listOf(cite(ZNP, "Management strategies"), cite(DSILVA, "General Approach to Rodenticide Poisoning")),
            ),
            Step(
                title = "Supportive care and liver failure",
                points = listOf(
                    "Proudfoot 2009: no antidote to phosphine or metal phosphides; supportive measures only.",
                    "Faress et al. 2025: with systemic symptoms and metabolic acidosis, treatment is similar to aluminium phosphide poisoning; acute liver failure and coagulopathy are distinct problems.",
                    "Mohanka et al. 2020 (Mumbai, 19 patients): cardiotoxicity from yellow phosphorus or metal phosphides was rapidly progressive and fatal; liver transplant was therapeutic for liver failure.",
                    "Faress et al. 2025: reports studies in which antioxidants (NAC, L-carnitine, vitamin E, coenzyme Q10) were associated with lower mortality in metal phosphide poisoning, and a meta-analysis of IV NAC in aluminium phosphide poisoning with a significant mortality reduction; the authors state randomised trials are needed to confirm efficacy (evidence is from small or aluminium phosphide studies, not zinc phosphide trials).",
                ),
                citations = listOf(cite(PROUD, "Abstract: Management"), cite(ZNP, "Management strategies"), cite(MOHANKA, "Abstract")),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Evidence for zinc phosphide is thin: reviews call rigorous research limited, and many recommendations are extrapolated from aluminium phosphide. Reported mortality figures vary widely (1.3% to 37–100%).",
                listOf(cite(ZNP, "Introduction"), cite(DSILVA, "Aluminium and Zinc Phosphide Poisoning")),
            ),
            ProtocolNote(
                "No ICMR STW or Indian national protocol specific to zinc phosphide was found.",
                listOf(cite(DSILVA, "General Approach to Rodenticide Poisoning")),
            ),
        ),
        citations = listOf(cite(ZNP), cite(DSILVA), cite(PROUD)),
    ),

    // =========================================================================================
    Protocol(
        id = "yellow-phosphorus",
        category = ProtocolCategory.Poisoning,
        title = "Yellow phosphorus rodenticide poisoning",
        summary = "Rat paste: delayed hepatotoxicity over days, no antidote. Time course, liver failure care and transplant listing as in Indian sources.",
        keywords = listOf(
            "Ratol", "rat paste", "rodenticide paste", "yellow phosphorus", "white phosphorus", "yellow phosphorous",
            "rat killer", "rat killer paste", "phosphorus paste", "firecracker poisoning", "acute liver failure", "luminescent vomitus",
        ),
        steps = listOf(
            Step(
                title = "Recognise the product and exposure",
                points = listOf(
                    "D'Silva and Krishna 2019: Ratol is a popular Indian brand; pastes contain 2–5% yellow phosphorus; children mistake paste for toothpaste or eat smeared bread bait.",
                    "D'Silva and Krishna 2019: garlic-like odour; luminescent faeces or vomit described. Lethal dose about 1 mg/kg; fulminant poisoning above 1–2 g.",
                    "D'Silva and Krishna 2019: it is a protoplasmic toxin affecting liver, gut, heart and kidney; hypocalcaemia can occur.",
                    "D'Silva and Krishna 2019: no specific diagnostic test; serum phosphorus does not estimate the amount taken.",
                ),
                citations = listOf(cite(DSILVA, "Yellow Phosphorous Poisoning")),
            ),
            Step(
                title = "Time course of toxicity",
                timing = "Day 0–1, day 1–3, after 72 h",
                points = listOf(
                    "D'Silva and Krishna 2019 (Rubitsky and Myerson stages): first 24 h — nausea and vomiting from gut irritation.",
                    "D'Silva and Krishna 2019: next 24–72 h — may be asymptomatic; bilirubin and liver enzymes may start to rise.",
                    "D'Silva and Krishna 2019: after 72 h — acute liver failure, coagulopathy, hypotension, arrhythmias, acute kidney injury; confusion, psychosis, coma.",
                    "Mohanka et al. 2020: patients with yellow phosphorus or metal phosphide liver failure reached a Mumbai centre at a median of 3 days (range 0–10) after poisoning.",
                    "D'Silva and Krishna 2019: death usually from cardiovascular collapse or complications of acute liver failure; hollow-viscus perforation reported.",
                ),
                citations = listOf(cite(DSILVA, "Yellow Phosphorous Poisoning: Mechanism of Action"), cite(MOHANKA, "Results")),
            ),
            Step(
                title = "Decontamination and initial care",
                timing = "Presentation within 24 h (Mohanka et al. protocol)",
                points = listOf(
                    "D'Silva and Krishna 2019: no known antidote; decontamination and supportive care. Gastric lavage with 1:1000 potassium permanganate is described as oxidising phosphorus; saline lavage can also be done.",
                    "Mohanka et al. 2020: centre protocol uses gastric lavage without additives for patients presenting within 24 h (only 1 of 19 patients presented within 24 h).",
                    "Mohanka et al. 2020: patients presenting within a few hours may benefit from cautious gastric decontamination; additives such as potassium permanganate, charcoal and copper sulfate may hasten disintegration and increase toxicity and are not advised. Sources differ: D'Silva and Krishna describe permanganate, Mohanka advises plain saline only.",
                    "D'Silva and Krishna 2019: monitor in ICU with daily liver and renal function tests.",
                ),
                citations = listOf(cite(DSILVA, "Yellow Phosphorous Poisoning: Treatment"), cite(MOHANKA, "Table 1")),
            ),
            Step(
                title = "N-acetylcysteine and other agents: evidence",
                points = listOf(
                    "D'Silva and Krishna 2019: case reports and series suggest NAC may be useful in rodenticide-induced liver failure, but further evidence is needed.",
                    "Mohanka et al. 2020: centre protocol gives NAC 100 mg/kg IV over 24 h for 5 days. Jubin et al. 2024: 19-patient series, extended NAC in 3 of 6 yellow phosphorus cases; observational only.",
                    "D'Silva and Krishna 2019: a small study found corticosteroids not useful and possibly harmful; some benefit was seen with exchange transfusion in grade IV hepatic encephalopathy, but the study was not sufficiently powered to establish it.",
                ),
                citations = listOf(cite(DSILVA, "Yellow Phosphorous Poisoning: Treatment"), cite(MOHANKA, "Table 1"), cite(JUBIN, "Abstract")),
            ),
            Step(
                title = "Acute liver failure care",
                timing = "Monitor 3–5 days after ingestion (Mohanka et al.)",
                points = listOf(
                    "Mohanka et al. 2020: nurse head-up 30 degrees; hourly assessment of encephalopathy and pupils; Table 1 protocol electively sedates and intubates patients with HE > grade 3; the discussion text states ≥ grade 2 HE should be electively ventilated (the paper is internally inconsistent).",
                    "Mohanka et al. 2020: INR above 2 — vitamin K 10 mg IV for 3 days; FFP, cryoprecipitate, platelets and tranexamic acid only for bleeding.",
                    "Mohanka et al. 2020: CVVHDF for renal indications, severe lactic acidosis or ammonia above 150 micromol/L twice or above 200 once; plasmapheresis for systemic inflammation.",
                    "Mohanka et al. 2020: check glucose, electrolytes, lactate and ammonia regularly; lactulose and rifaximin for encephalopathy; daily SOFA score.",
                ),
                citations = listOf(cite(MOHANKA, "Table 1: LICU management protocol; Management")),
            ),
            Step(
                title = "Prognosis and transplant referral",
                timing = "Evaluate for transplant from admission (Mohanka et al.)",
                points = listOf(
                    "Mohanka et al. 2020: spontaneous survivors had ingested less than 17.5 g, no cardiotoxicity, below grade 3 encephalopathy, peak lactate below 5.8, peak SOFA below 14.5.",
                    "Mohanka et al. 2020: overall mortality 36.8% (7 of 19); 5 transplanted; 15 of 19 met King's College criteria.",
                    "Mohanka et al. 2020: patients meeting King's College or NHSBT criteria were listed super-urgent with the zonal transplant committee; transplant only after worsening despite medical therapy.",
                    "Mohanka et al. 2020: transplant required recovery from marrow suppression and cardiotoxicity; no irreversible neurological damage or systemic infection.",
                ),
                citations = listOf(cite(MOHANKA, "Results; Table 3; Table 1")),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Yellow phosphorus is a particular Indian problem (Ratol paste); the evidence is small Indian case series and single-centre protocols. Sample sizes are 19 patients, so thresholds are hypothesis-generating.",
                listOf(cite(MOHANKA, "Abstract"), cite(DSILVA, "Introduction")),
            ),
            ProtocolNote(
                "Sources disagree on gastric lavage additives: D'Silva and Krishna describe 1:1000 potassium permanganate lavage, while Mohanka et al. advise against permanganate, charcoal and copper sulfate and use plain saline only.",
                listOf(cite(MOHANKA, "Discussion"), cite(DSILVA, "Yellow Phosphorous Poisoning: Treatment")),
            ),
            ProtocolNote(
                "Mohanka's series combines yellow phosphorus and metal phosphide patients, so its cut-offs are not specific to yellow phosphorus. No ICMR STW for rodenticides was found; ICMR 'Liver failure' STW exists but was not reviewed.",
                listOf(cite(MOHANKA, "Introduction")),
            ),
        ),
        citations = listOf(cite(DSILVA), cite(MOHANKA)),
    ),

    // =========================================================================================
    Protocol(
        id = "superwarfarin",
        category = ProtocolCategory.Poisoning,
        title = "Superwarfarin (bromadiolone, brodifacoum) poisoning",
        summary = "Long-acting anticoagulant rodenticides: delayed coagulopathy, vitamin K1 for weeks to months, plasma or PCC for bleeding.",
        keywords = listOf(
            "superwarfarin", "bromadiolone", "brodifacoum", "difenacoum", "flocoumafen", "coumarin", "hydroxycoumarin",
            "anticoagulant rodenticide", "LAAR", "rat poison", "vitamin K", "high INR", "prolonged PT", "bleeding", "rodenticide",
        ),
        steps = listOf(
            Step(
                title = "Recognise: delayed bleeding after rodenticide exposure",
                points = listOf(
                    "D'Silva and Krishna 2019: coagulopathy appears 24–48 h after ingestion, once factors fall below 30% of normal; may be delayed by hours to weeks.",
                    "D'Silva and Krishna 2019: mucocutaneous, gastrointestinal, genitourinary and intracranial bleeding; epistaxis, haematuria, ecchymoses; intracranial bleeds carry high mortality.",
                    "King and Tran 2015: haematuria and mucocutaneous bleeding most frequent; deaths mostly from intracranial haemorrhage; most patients present with coagulation values beyond measurable limits.",
                    "D'Silva and Krishna 2019: suspect in prolonged multisite bleeding with raised PT and aPTT, no DIC and no factor inhibitor; children are often misdiagnosed as haemophilia.",
                ),
                checklistTitle = "Suggests long-acting anticoagulant rodenticide (D'Silva and Krishna 2019)",
                checklist = listOf(
                    "Unexplained prolonged PT/INR and aPTT",
                    "Multisite bleeding without DIC",
                    "No inhibitor of clotting factors",
                    "Rodenticide access, even if initially asymptomatic",
                ),
                citations = listOf(cite(DSILVA, "Coumarins: Clinical Manifestations; Diagnosis"), cite(KING, "Abstract")),
            ),
            Step(
                title = "Coagulation testing and timing",
                timing = "INR rises about 48 h after exposure",
                points = listOf(
                    "D'Silva and Krishna 2019: PT/INR derangement usually occurs about 48 h after exposure and may last days; PT and aPTT are universally prolonged by presentation.",
                    "D'Silva and Krishna 2019: monitor closely for bleeding with daily PT/INR and aPTT after exposure.",
                    "D'Silva and Krishna 2019: factor assays (II, VII, IX, X, V) support diagnosis; HPLC-MS/MS blood levels confirm but are rarely available.",
                    "D'Silva and Krishna 2019: factor half-lives — VII 6.2 h, IX 13.9 h, X 16.5 h, II 41 h; brodifacoum half-life 16–34 days.",
                ),
                citations = listOf(cite(DSILVA, "Coumarins: Diagnosis; Table 2")),
            ),
            Step(
                title = "Vitamin K1: dose and duration as stated",
                timing = "Duration weeks to months",
                points = listOf(
                    "D'Silva and Krishna 2019: treatment is mainly vitamin K; clotting factors begin to rise 6–8 h after treatment; no clear consensus on dose; given from 28 to 730 days.",
                    "D'Silva and Krishna 2019: initial doses of 0.1 to 3 mg/kg/day have been used; doses as high as 500–800 mg/day have been reported.",
                    "King and Tran 2015: acute haemorrhage often needed IV vitamin K1 above 50–100 mg; maintenance most often 100 mg oral daily; average course 168 days.",
                    "FDA phytonadione label (anticoagulant-induced hypoprothrombinemia): 2.5 mg to 10 mg or more; up to 25 mg to 50 mg may be given as a single dose; evaluate INR after 6 to 8 hours and repeat if still prolonged. Written for warfarin-type deficiency, not superwarfarin.",
                    "FDA phytonadione label, boxed warning: severe hypersensitivity reactions including fatalities with IV and IM use; avoid IV and IM unless the subcutaneous route is not feasible and the serious risk is justified; IV not above 1 mg per minute.",
                    "King and Tran 2015's 50–100 mg IV doses exceed the label's 25–50 mg single dose and come from case reports.",
                ),
                citations = listOf(
                    cite(DSILVA, "Coumarins: Treatment"),
                    cite(KING, "Abstract"),
                    cite(FDAK, "Boxed warning; Dosage and administration"),
                ),
            ),
            Step(
                title = "Bleeding: plasma, PCC and rebound",
                points = listOf(
                    "D'Silva and Krishna 2019: fresh frozen plasma or prothrombin complex concentrate are needed for active bleeding.",
                    "King and Tran 2015: rebound coagulopathy after initial treatment; adjunct recombinant factor VIIa and PCC reported; phenobarbital used to speed metabolism.",
                    "King and Tran 2015: paradoxical thrombosis and thrombotic complications with haemostatic therapy have been observed.",
                    "D'Silva and Krishna 2019: plasma exchange studied (brodifacoum highly protein-bound) including in children after vitamin K anaphylaxis.",
                ),
                citations = listOf(cite(DSILVA, "Coumarins: Treatment"), cite(KING, "Abstract")),
            ),
            Step(
                title = "Follow-up",
                points = listOf(
                    "D'Silva and Krishna 2019: brodifacoum persists 16–270 days; hepatic tissue may keep inhibiting vitamin K epoxide reductase even with low serum levels.",
                    "D'Silva and Krishna 2019: anaphylactic reactions can follow vitamin K administration.",
                    "King and Tran 2015: long-term high-dose vitamin K1 is needed because of rebound coagulopathy.",
                ),
                citations = listOf(cite(DSILVA, "Coumarins: Clinical Manifestations; Treatment"), cite(KING, "Abstract")),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "No consensus dose: D'Silva 2019 reports vitamin K1 from 0.1 mg/kg/day to 500–800 mg/day; King 2015 (case reports) reports 50–100 mg IV acutely and 100 mg oral daily. The FDA label doses are for warfarin-type deficiency. Evidence is case-report level.",
                listOf(cite(DSILVA, "Coumarins: Treatment"), cite(KING, "Abstract"), cite(FDAK, "Dosage and administration")),
            ),
            ProtocolNote(
                "No ICMR STW or Indian guideline for superwarfarin poisoning was found. Not verified: an INR threshold at which vitamin K1 should be started in the asymptomatic patient.",
                listOf(cite(DSILVA, "Introduction")),
            ),
        ),
        citations = listOf(cite(DSILVA), cite(KING)),
    ),
)
