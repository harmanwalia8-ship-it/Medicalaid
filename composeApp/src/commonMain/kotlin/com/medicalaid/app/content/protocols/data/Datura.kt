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
 * Dhatura (Datura) and anticholinergic poisoning. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them.
 * - Every point is attributed ("Pillay & Sasidharan 2019: …", "FDA label: …", "Burns 2000 (retrospective): …").
 * - Pillay & Sasidharan 2019 (tx-oleander-pillay-ijccm-2019) and the AACT/EAPCCT 2005 charcoal paper
 *   (tx-hh-aact-sdac-2005) are defined in Oleander.kt / HouseholdTox.kt and cited here by id.
 * - Physostigmine labels are US labels (DailyMed); availability in India was not verified.
 */

internal val daturaSources: List<Source> = listOf(
    Source(
        "tx-datura-fda-physostigmine-label", Authority.FDA, "Drug label (DailyMed): Physostigmine Salicylate Injection, 1 mg/mL",
        "Physostigmine Salicylate Injection (HF Acquisition Co / HealthFirst) — prescribing information", "2023",
        "https://dailymed.nlm.nih.gov/dailymed/lookup.cfm?setid=9a448a78-356c-50f7-e053-2995a90adefa",
        status = "Label version effective 9 Jan 2023, read via openFDA; the label gives no adult poisoning dose beyond 'overdose of drugs that cause anticholinergic: 2.0 mg'",
    ),
    Source(
        "tx-datura-anticholium-label", Authority.FDA, "Drug label (DailyMed): Anticholium (physostigmine salicylate), 5 x 5 mL ampoules",
        "Anticholium — prescribing information (Provepharm)", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/lookup.cfm?setid=b90b50e8-c8f1-4f2d-be6e-4d269b8c362c",
        status = "Label version effective 11 Nov 2025, read via openFDA; includes tropane-alkaloid (datura) poisoning as a named indication",
    ),
    Source(
        "tx-datura-burns-2000", Authority.Paper, "Retrospective study, 52 patients (Ann Emerg Med 2000;35:374-381)",
        "A comparison of physostigmine and benzodiazepines for the treatment of anticholinergic poisoning", "2000",
        "https://doi.org/10.1016/S0196-0644(00)70057-6",
        status = "Authors call for a prospective controlled study; abstract read",
    ),
    Source(
        "tx-datura-arens-2018", Authority.Paper, "Retrospective cohort, 191 patients (Clin Toxicol 2018;56:101-107)",
        "Safety and effectiveness of physostigmine: a 10-year retrospective review", "2018",
        "https://doi.org/10.1080/15563650.2017.1342828",
        status = "Abstract read",
    ),
    Source(
        "tx-datura-wang-rct-2021", Authority.Paper, "Randomised blinded trial, 19 adolescents (Clin Toxicol 2021;59:698-704)",
        "A randomized trial comparing physostigmine vs lorazepam for treatment of antimuscarinic (anticholinergic) toxidrome", "2021",
        "https://doi.org/10.1080/15563650.2020.1854281",
        status = "Abstract read; 84% of ingestions were diphenhydramine",
    ),
    Source(
        "tx-datura-suchard-2003", Authority.Paper, "Review (J Emerg Med 2003;25:185-191)",
        "Assessing physostigmine's contraindication in cyclic antidepressant ingestions", "2003",
        "https://doi.org/10.1016/S0736-4679(03)00169-0",
        status = "Abstract read",
    ),
    Source(
        "tx-datura-islam-indped-2022", Authority.Paper, "Research letter, 47 children (Indian Pediatr 2022;59:652-653)",
        "Clinical Features and Adverse Prognostic Indicators in Datura Poisoning in Children", "2022",
        "https://pubmed.ncbi.nlm.nih.gov/35962661/",
        status = "Burdwan Medical College, West Bengal; PubMed abstract read, PDF text not extractable",
    ),
)

private const val PILLAY = "tx-oleander-pillay-ijccm-2019"
private const val SDAC = "tx-hh-aact-sdac-2005"
private const val FDA_PHY = "tx-datura-fda-physostigmine-label"
private const val ANTICH = "tx-datura-anticholium-label"
private const val BURNS = "tx-datura-burns-2000"
private const val ARENS = "tx-datura-arens-2018"
private const val WANG = "tx-datura-wang-rct-2021"
private const val SUCHARD = "tx-datura-suchard-2003"
private const val ISLAM = "tx-datura-islam-indped-2022"

internal val daturaProtocols: List<Protocol> = listOf(
    Protocol(
        id = "datura",
        category = ProtocolCategory.Poisoning,
        title = "Dhatura (Datura) and anticholinergic poisoning",
        summary = "Anticholinergic toxidrome, differential, supportive care, benzodiazepines, charcoal and physostigmine " +
            "as stated by an Indian review, US drug labels and published studies.",
        keywords = listOf(
            "dhatura", "datura", "dhatoora", "dhatura seeds", "thorn apple", "jimsonweed", "jimson weed", "datura stramonium",
            "datura metel", "devil's trumpet", "anticholinergic", "antimuscarinic", "atropine poisoning", "hyoscine",
            "scopolamine", "hyoscyamine", "belladonna", "food spiked", "drink spiked", "laced prasad", "spiked tea",
            "mad as a hatter", "hallucination", "dilated pupils", "physostigmine", "anticholium",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise the anticholinergic toxidrome",
                timing = "Signs usually appear within 30-60 minutes (Pillay & Sasidharan 2019)",
                points = listOf(
                    "Pillay & Sasidharan 2019: Datura toxins are belladonna alkaloids, mainly hyoscine (scopolamine) and hyoscyamine; alkaloid content is highest in flowers, then stem, fruit/seeds, leaves, roots.",
                    "Pillay & Sasidharan 2019: dry mouth, thirst, dilated pupils unreactive to light, dry hot flushed skin, hyperpyrexia, sinus tachycardia, reduced bowel sounds, urinary retention.",
                    "Pillay & Sasidharan 2019: CNS signs include ataxia, delirium, agitation, visual and auditory hallucinations, myoclonus and convulsions; severe cases progress to depression, coma, circulatory and respiratory failure.",
                    "Pillay & Sasidharan 2019: 1% pilocarpine, 2-3 drops in the eye, with no pupil constriction in 15-30 minutes suggests anticholinergic syndrome; plasma levels correlate poorly with dose or effect.",
                    "FDA label: central signs - anxiety, delirium, disorientation, hallucinations, hyperactivity, seizures; peripheral - tachycardia, hyperpyrexia, mydriasis, vasodilation, urinary retention, reduced gut motility, dry secretions.",
                ),
                checklistTitle = "Toxidrome checklist (sources: Pillay & Sasidharan 2019; FDA label)",
                checklist = listOf(
                    "Mydriasis, pupils sluggish or unreactive to light",
                    "Dry, hot, flushed skin; no sweating",
                    "Dry mouth, thirst, slurred speech or dysphagia",
                    "Sinus tachycardia; temperature raised",
                    "Reduced or absent bowel sounds",
                    "Urinary retention, distended bladder",
                    "Delirium, agitation, hallucinations, disorientation",
                    "Myoclonus or seizures; coma in severe cases",
                ),
                citations = listOf(
                    cite(PILLAY, "Datura poisoning - toxic principles, clinical features, diagnosis"),
                    cite(FDA_PHY, "Clinical Pharmacology"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Exposure history, differential and co-ingestants",
                points = listOf(
                    "FDA label: the anticholinergic syndrome is also produced by drugs such as tricyclic antidepressants, benztropine, hyoscine, atropine and by plants incl. jimson weed.",
                    "Anticholium label: tropane alkaloids (datura, atropa belladonna), tricyclics, phenothiazines, diphenhydramine and other antihistamines, antipsychotics and benzodiazepines are listed as causes of anticholinergic intoxication.",
                    "Pillay & Sasidharan 2019: begin supportive care in a quiet setting after excluding other causes of the picture; seeds may stay in the stomach, recovered 16-36 hours after ingestion.",
                    "Pillay & Sasidharan 2019: 1 g of seeds (about 125 seeds) contains about 2.9 mg atropine and 0.5 mg scopolamine; fatal dose about 50-100 seeds; recovery recorded after more than 500 mg atropine.",
                    "Arens 2018: among 191 physostigmine-treated patients the presumed causes were anticholinergic plants (67), diphenhydramine (56), other antihistamines, antipsychotics, tricyclics and combination products.",
                ),
                citations = listOf(
                    cite(FDA_PHY, "How Supplied - drugs and plants that produce the anticholinergic syndrome"),
                    cite(ANTICH, "Indications"),
                    cite(PILLAY, "Datura poisoning - exposure and management"),
                    cite(ARENS, "Abstract - results by primary ingestant"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Supportive care: monitoring, fluids, bladder, temperature",
                points = listOf(
                    "Pillay & Sasidharan 2019: continuous ECG, pulse and temperature monitoring in a dark, quiet setting.",
                    "Pillay & Sasidharan 2019: sinus tachycardia usually needs no treatment unless there is haemodynamic compromise; esmolol is preferred if treatment is needed.",
                    "Pillay & Sasidharan 2019: IV fluids, monitor intake, output and renal function; bladder catheterisation is usually needed for retention.",
                    "Pillay & Sasidharan 2019: conventional cooling for mild-moderate hyperthermia; above 39 degrees C consider rectal core temperature monitoring and urgent cooling per local protocol.",
                    "Pillay & Sasidharan 2019: in hyperpyrexia monitor renal function and creatine kinase; dantrolene may be considered for muscular hyperactivity (1 mg/kg IV, maximum 10 mg/kg).",
                    "Pillay & Sasidharan 2019: haemodialysis or haemoperfusion does not remove Datura alkaloids; pilocarpine is ineffective as treatment.",
                ),
                citations = listOf(
                    cite(PILLAY, "Datura poisoning - management"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Agitation, delirium and seizures: benzodiazepines",
                points = listOf(
                    "Pillay & Sasidharan 2019: agitation - adults IV diazepam 0.1-0.3 mg/kg; where sedation is safe, 10-20 mg in adults and 0.25 mg/kg in children.",
                    "Pillay & Sasidharan 2019: in children sedation is not recommended immediately; exclude other causes first.",
                    "Burns 2000 (retrospective, 52 patients): benzodiazepines controlled agitation in 24% and did not reverse delirium; physostigmine controlled agitation in 96% and reversed delirium in 87%.",
                    "Wang 2021 (RCT, ages 10-17, mostly diphenhydramine): lorazepam 0.05 mg/kg bolus then 4-hour saline infusion; delirium persisted in 100% at bolus and at 4 hours versus 44% and 22% with physostigmine.",
                    "Source gives no separate anticonvulsant regimen for Datura seizures beyond the benzodiazepine doses above (Pillay & Sasidharan 2019).",
                ),
                doses = listOf(
                    StepDose(
                        label = "Diazepam IV for agitation - adults (Pillay & Sasidharan 2019)",
                        dose = WeightDose(minPerKg = 0.1, maxPerKg = 0.3, unit = "mg", per = "IV, per dose"),
                        note = "Same source also gives 10-20 mg for sedation in adults where safe.",
                    ),
                    StepDose(
                        label = "Diazepam - children, when sedation is judged safe (Pillay & Sasidharan 2019)",
                        dose = WeightDose(minPerKg = 0.25, maxPerKg = 0.25, unit = "mg", per = "per dose"),
                        note = "Source: not recommended immediately in children; exclude other causes first.",
                    ),
                ),
                citations = listOf(
                    cite(PILLAY, "Datura poisoning - management (agitation, sedation)"),
                    cite(BURNS, "Abstract - results"),
                    cite(WANG, "Abstract - methods and results"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Gut decontamination and activated charcoal timing",
                timing = "Charcoal: AACT/EAPCCT - consider only if within 1 hour of a toxic ingestion",
                points = listOf(
                    "Pillay & Sasidharan 2019: lavage followed by activated charcoal; reduced GI motility means decontamination may help several hours after ingestion.",
                    "AACT/EAPCCT 2005: single-dose charcoal not to be given routinely; may be considered for a potentially toxic amount ingested up to one hour previously; no evidence it improves clinical outcome.",
                    "AACT/EAPCCT 2005: unless the airway is intact or protected, charcoal is contraindicated - relevant to agitated or drowsy patients.",
                    "Pillay & Sasidharan 2019 give charcoal doses (50 g adults, 1 g/kg children, within 4 h) in the oleander section only, not for Datura.",
                    "Anticholium label: measures to prevent absorption (lavage, medicinal charcoal, laxatives) are started immediately in intoxication.",
                ),
                citations = listOf(
                    cite(PILLAY, "Datura poisoning - management (decontamination); oleander section for charcoal doses"),
                    cite(SDAC, "Abstract and conclusions"),
                    cite(ANTICH, "Overdosage"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Physostigmine: indications, doses, contraindications",
                points = listOf(
                    "Pillay & Sasidharan 2019: indicated in serious poisoning with at least one of arrhythmias, hallucinations, severe hypertension, convulsions or coma; some investigators suggest use only as a diagnostic agent.",
                    "Pillay & Sasidharan 2019: adults 2 mg slow IV, repeatable after 20 minutes; children 0.5 mg slow IV every 5 minutes to a maximum of 2 mg; effect lasts about 20-60 minutes.",
                    "FDA label: IV at no more than 1 mg per minute; rapid injection can cause bradycardia, hypersalivation, respiratory difficulty and convulsions; overdose causes cholinergic crisis, antidote atropine - keep atropine at hand.",
                    "FDA label contraindications: asthma, gangrene, diabetes, cardiovascular disease, mechanical intestinal or urogenital obstruction, vagotonic states, choline esters or depolarising blockers (succinylcholine).",
                    "Anticholium label: adults 0.04 mg/kg (2 mg) slow IV then 1-4 mg every 20 minutes if symptoms recur; contraindicated in coronary heart disease, asthma, closed traumatic brain injury, organophosphate or barbiturate intoxication.",
                    "Suchard 2003: tricyclic ingestion or QRS prolongation is commonly cited as a contraindication, but the review found little evidence to support it; Pillay & Sasidharan 2019 list tricyclics among drugs to avoid.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Physostigmine - children (FDA label)",
                        dose = WeightDose(minPerKg = 0.02, maxPerKg = 0.02, unit = "mg", per = "IM or slow IV; repeat every 5-10 min if no cholinergic signs"),
                        note = "Label: no faster than 0.5 mg per minute; maximum total 2 mg. Adults: 2 mg slow IV (Pillay & Sasidharan 2019).",
                        maxTotal = 2.0,
                    ),
                    StepDose(
                        label = "Physostigmine - adults by weight (Anticholium label)",
                        dose = WeightDose(minPerKg = 0.04, maxPerKg = 0.04, unit = "mg", per = "slow IV, about 1 mg/min"),
                        note = "Label: 0.04 mg/kg (2 mg); then 1-4 mg every 20 minutes; may be given as a brief infusion in 50 mL saline over 10-15 minutes.",
                        maxTotal = 2.0,
                    ),
                    StepDose(
                        label = "Physostigmine - adolescents 10-17 y, RCT regimen (Wang 2021)",
                        dose = WeightDose(minPerKg = 0.02, maxPerKg = 0.02, unit = "mg", per = "bolus, then 0.02 mg/kg/h for 4 hours"),
                        note = "Small trial (9 physostigmine patients); no seizures, bradycardia or arrhythmias reported.",
                    ),
                ),
                citations = listOf(
                    cite(PILLAY, "Datura poisoning - physostigmine"),
                    cite(FDA_PHY, "Dosage and Administration; Contraindications; Warnings; Overdosage"),
                    cite(ANTICH, "Dosage and Administration; Contraindications"),
                    cite(SUCHARD, "Abstract"),
                    cite(WANG, "Abstract - methods"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Why neostigmine does not replace physostigmine",
                points = listOf(
                    "Pillay & Sasidharan 2019: neostigmine and pyridostigmine are quaternary ammonium compounds that do not cross the blood-brain barrier, so they are not effective against atropine-type CNS toxicity.",
                    "FDA label: physostigmine is a tertiary amine that crosses the blood-brain barrier and can reverse both central and peripheral anticholinergia; neostigmine, a quaternary ion, cannot cross.",
                    "Pillay & Sasidharan 2019: pilocarpine is ineffective as treatment, although it is described as a bedside diagnostic drop test.",
                ),
                citations = listOf(
                    cite(PILLAY, "Datura poisoning - neostigmine and pyridostigmine"),
                    cite(FDA_PHY, "Clinical Pharmacology"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Observation, relapse and prognosis",
                timing = "Effects can last 24-48 hours (Pillay & Sasidharan 2019)",
                points = listOf(
                    "Pillay & Sasidharan 2019: signs can persist 24-48 hours because alkaloids delay GI motility; physostigmine effect lasts only 20-60 minutes, so lasting reversal is generally not achieved.",
                    "Anticholium label: repeat the effective dose if intoxication symptoms recur; adequate dosing is judged by recovery of mental abilities and responsiveness.",
                    "Islam 2022 (47 children): 31.9% needed intensive care and 1 died; adverse prognostic features were more than 3.15 h from ingestion to treatment (RR 9.4), age under 9.5 years (RR 3.5) and seizure (RR 2.8).",
                    "Arens 2018: after physostigmine 57.6% were admitted to ICU and 18.8% discharged from the ED; response was partial in 68.7% with anticholinergic plants; 1 death 6 hours after physostigmine.",
                    "No source reviewed gives a fixed minimum observation period for Datura; monitor until toxidrome resolves (Pillay & Sasidharan 2019).",
                ),
                citations = listOf(
                    cite(PILLAY, "Datura poisoning - onset, duration, physostigmine"),
                    cite(ANTICH, "Dosage and Administration"),
                    cite(ISLAM, "Abstract"),
                    cite(ARENS, "Abstract - results and discussion"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                text = "Physostigmine versus benzodiazepines is contested. Burns 2000 is retrospective (52 patients) and calls for a prospective trial; the only RCT found (Wang 2021) had 19 adolescents, mostly diphenhydramine. Arens 2018 found only partial response in 68.7% of plant cases, and Pillay & Sasidharan 2019 note some investigators use it only diagnostically.",
                citations = listOf(cite(BURNS, "Abstract"), cite(WANG, "Abstract"), cite(ARENS, "Abstract"), cite(PILLAY, "Physostigmine")),
            ),
            ProtocolNote(
                text = "Tricyclic co-ingestion and QRS widening: FDA-format and Indian review sources advise caution or avoidance, whereas Suchard 2003 found little evidence supporting the contraindication; the Anticholium label even lists tricyclic antidepressants among indications. The sources differ; none sets a QRS cut-off.",
                citations = listOf(cite(SUCHARD, "Abstract"), cite(ANTICH, "Indications"), cite(FDA_PHY, "Contraindications"), cite(PILLAY, "Datura - drugs to avoid")),
            ),
            ProtocolNote(
                text = "India versus international: no ICMR Standard Treatment Workflow for Datura or anticholinergic poisoning was found in searches. The Indian review (Pillay & Sasidharan 2019) gives diazepam doses; US labels give physostigmine doses. Availability of physostigmine in Indian hospitals was not verified.",
                citations = listOf(cite(PILLAY, "Datura poisoning - management"), cite(FDA_PHY, "Dosage and Administration")),
            ),
            ProtocolNote(
                text = "Adult physostigmine doses differ between sources: 2 mg slow IV repeated after 20 minutes (Pillay), 2 mg then 1-4 mg every 20 minutes (Anticholium label), 0.5-1.0 mg with repeats every 10-30 minutes in the US label's post-anaesthesia section.",
                citations = listOf(cite(PILLAY, "Physostigmine"), cite(ANTICH, "Dosage and Administration"), cite(FDA_PHY, "Dosage and Administration")),
            ),
        ),
        citations = listOf(
            cite(PILLAY, "Datura poisoning"),
            cite(FDA_PHY, "Prescribing information"),
            cite(ANTICH, "Prescribing information"),
            cite(BURNS, "Abstract"),
            cite(ARENS, "Abstract"),
            cite(WANG, "Abstract"),
            cite(SUCHARD, "Abstract"),
            cite(ISLAM, "Abstract"),
            cite(SDAC, "Position paper"),
        ),
    ),
)
