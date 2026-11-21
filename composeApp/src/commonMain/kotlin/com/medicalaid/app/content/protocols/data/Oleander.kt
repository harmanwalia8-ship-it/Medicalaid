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
 * Yellow oleander (Cascabela/Thevetia peruviana) and common oleander (Nerium oleander) poisoning. Rules:
 * - Doses, thresholds and time windows exactly as the cited document states them.
 * - Every point is attributed ("AHA 2023: …", "Eddleston et al. 2000 (Lancet RCT): …", "Pillay & Sasidharan 2019: …").
 * - Trials and reviews are cited as papers with the journal in `document`; evidence status is stated as the source states it.
 * - No oleander- or poisoning-specific workflow appears in the ICMR STW list (checked November 2026); the ICMR STW cited
 *   here is the general symptomatic-bradyarrhythmia workflow.
 */

internal val oleanderSources = listOf(
    Source(
        "tx-oleander-eddleston-fab-rct-2000", Authority.Paper, "RCT (Lancet 2000;355:967–972)",
        "Anti-digoxin Fab fragments in cardiotoxicity induced by ingestion of yellow oleander: a randomised controlled trial",
        "2000",
        "https://doi.org/10.1016/S0140-6736(00)90014-X",
        status = "Eddleston M et al.; Sri Lanka; 66 patients; outcomes arrhythmia reversal, heart rate and potassium",
    ),
    Source(
        "tx-oleander-desilva-mdac-rct-2003", Authority.Paper, "RCT (Lancet 2003;361:1935–1938)",
        "Multiple-dose activated charcoal for treatment of yellow oleander poisoning: a single-blind, randomised, placebo-controlled trial",
        "2003",
        "https://doi.org/10.1016/S0140-6736(03)13581-7",
        status = "de Silva HA et al.; Sri Lanka; 401 patients",
    ),
    Source(
        "tx-oleander-eddleston-mdac-rct-2008", Authority.Paper, "RCT (Lancet 2008;371:579–587)",
        "Multiple-dose activated charcoal in acute self-poisoning: a randomised controlled trial", "2008",
        "https://doi.org/10.1016/S0140-6736(08)60270-6",
        status = "Eddleston M et al.; Sri Lanka; open-label; 4632 patients, 1647 with yellow oleander",
    ),
    Source(
        "tx-oleander-cochrane-cardenolide-2006", Authority.Paper, "Cochrane review CD005490 (Cochrane Database Syst Rev 2006;(4))",
        "Antidotes for acute cardenolide (cardiac glycoside) poisoning", "2006",
        "https://doi.org/10.1002/14651858.CD005490.pub2",
        status = "Roberts DM, Buckley N; searches to March 2006 — lists the 2008 charcoal RCT as ongoing",
    ),
    Source(
        "tx-oleander-rajapakse-review-2009", Authority.Paper, "Review (Clin Toxicol 2009;47:206–212)",
        "Management of yellow oleander poisoning", "2009",
        "https://doi.org/10.1080/15563650902824001",
        status = "Rajapakse S; Sri Lanka",
    ),
    Source(
        "tx-oleander-eddleston-ecg-heart-2000", Authority.Paper, "Case series (Heart 2000;83:301–306)",
        "Acute yellow oleander (Thevetia peruviana) poisoning: cardiac arrhythmias, electrolyte disturbances, and serum cardiac glycoside concentrations on presentation to hospital",
        "2000",
        "https://doi.org/10.1136/heart.83.3.301",
        status = "Eddleston M et al.; Sri Lanka; 351 patients",
    ),
    Source(
        "tx-oleander-eddleston-antitoxin-cost-2003", Authority.Paper, "Health-policy analysis (Lancet 2003;362:1041–1044)",
        "Deaths due to absence of an affordable antitoxin for plant poisoning", "2003",
        "https://doi.org/10.1016/S0140-6736(03)14415-7",
        status = "Eddleston M et al.; Sri Lanka; observational before–after comparison",
    ),
    Source(
        "tx-oleander-fonseka-outcome-2002", Authority.Paper, "Observational study (Hum Exp Toxicol 2002;21:293–295)",
        "Yellow oleander poisoning in Sri Lanka: outcome in a secondary care hospital", "2002",
        "https://doi.org/10.1191/0960327102ht257oa",
        status = "Fonseka MM et al.; 168 patients",
    ),
    Source(
        "tx-oleander-roberts-pk-2006", Authority.Paper, "Pharmacokinetic study (Ther Drug Monit 2006;28:784–792)",
        "Pharmacokinetics of digoxin cross-reacting substances in patients with acute yellow oleander (Thevetia peruviana) poisoning, including the effect of activated charcoal",
        "2006",
        "https://doi.org/10.1097/FTD.0b013e31802bfd69",
        status = "Roberts DM et al.",
    ),
    Source(
        "tx-oleander-bandara-review-2010", Authority.Paper, "Review (Toxicon 2010;56:273–281)",
        "A review of the natural history, toxinology, diagnosis and clinical management of Nerium oleander (common oleander) and Thevetia peruviana (yellow oleander) poisoning",
        "2010",
        "https://doi.org/10.1016/j.toxicon.2010.03.026",
        status = "Bandara V et al.",
    ),
    Source(
        "tx-oleander-pillay-ijccm-2019", Authority.Paper, "Review (Indian J Crit Care Med 2019;23 Suppl 4:S250–S255)",
        "Oleander and Datura Poisoning: An Update", "2019",
        "https://doi.org/10.5005/jp-journals-10071-23302",
        status = "Pillay VV, Sasidharan A; Poison Control Centre, Amrita, Kochi; narrative review",
    ),
    Source(
        "tx-oleander-cmc-vellore-jfmpc-2020", Authority.Paper, "Retrospective cohort (J Family Med Prim Care 2020;9:136–140)",
        "Acute oleander poisoning: a study of clinical profile from a tertiary care center in South India", "2020",
        "https://doi.org/10.4103/jfmpc.jfmpc_632_19",
        status = "Karthik G et al.; Christian Medical College, Vellore; 30 patients",
    ),
    Source(
        "tx-oleander-icmr-stw-bradyarrhythmia-2025", Authority.ICMR, "Standard Treatment Workflow (ICD-11 MC81.1)",
        "Bradyarrhythmias in Symptomatic Patients", "2025",
        "https://www.icmr.gov.in/icmrobject/uploads/STWs/1771492063_bradyarrthymia13_2_2.pdf",
        status = "January 2025; general symptomatic-bradycardia workflow, not specific to poisoning",
    ),
    Source(
        "tx-oleander-aha-poisoning-2023", Authority.AHA, "Focused update (Circulation 2023;148:e149–e184)",
        "2023 American Heart Association Focused Update on the Management of Patients With Cardiac Arrest or Life-Threatening Toxicity Due to Poisoning",
        "2023",
        "https://doi.org/10.1161/CIR.0000000000001161",
    ),
    Source(
        "tx-oleander-fda-digifab-label", Authority.FDA, "Prescribing information (DailyMed; BTG International)",
        "DigiFab (digoxin immune Fab [ovine]) for injection", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=c05ee6a5-c98b-45f4-83fd-40781639d653",
        status = "Label version effective 29 Sep 2026; labelled indication is digoxin toxicity or overdose",
    ),
)

private const val OL_FAB_RCT = "tx-oleander-eddleston-fab-rct-2000"
private const val OL_DESILVA = "tx-oleander-desilva-mdac-rct-2003"
private const val OL_MDAC_2008 = "tx-oleander-eddleston-mdac-rct-2008"
private const val OL_COCHRANE = "tx-oleander-cochrane-cardenolide-2006"
private const val OL_RAJAPAKSE = "tx-oleander-rajapakse-review-2009"
private const val OL_HEART = "tx-oleander-eddleston-ecg-heart-2000"
private const val OL_COST = "tx-oleander-eddleston-antitoxin-cost-2003"
private const val OL_FONSEKA = "tx-oleander-fonseka-outcome-2002"
private const val OL_PK = "tx-oleander-roberts-pk-2006"
private const val OL_BANDARA = "tx-oleander-bandara-review-2010"
private const val OL_PILLAY = "tx-oleander-pillay-ijccm-2019"
private const val OL_CMC = "tx-oleander-cmc-vellore-jfmpc-2020"
private const val OL_ICMR = "tx-oleander-icmr-stw-bradyarrhythmia-2025"
private const val OL_AHA = "tx-oleander-aha-poisoning-2023"
private const val OL_FDA = "tx-oleander-fda-digifab-label"

internal val oleanderProtocols = listOf(
    Protocol(
        id = "oleander",
        title = "Oleander (kaner) poisoning",
        summary = "Yellow oleander (pila kaner) and common oleander cardiac glycoside poisoning — ECG and potassium " +
            "monitoring, atropine, pacing, anti-digoxin Fab and charcoal trial evidence, each step attributed.",
        keywords = listOf(
            "oleander", "kaner", "pila kaner", "peeli kaner", "yellow oleander", "Thevetia", "Thevetia peruviana",
            "Cascabela thevetia", "Nerium", "Nerium oleander", "common oleander", "pink oleander", "white oleander",
            "safed kaner", "lal kaner", "kaner seeds", "kaner ke beej", "manjal arali", "arali", "pachaganneru", "kolke",
            "kaneru", "lucky nut", "be-still tree", "cardiac glycoside", "cardenolide", "digoxin-like toxicity",
            "anti-digoxin Fab", "digoxin immune Fab", "DigiFab", "Digibind", "plant poisoning", "seed poisoning",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise cardiac glycoside toxicity",
                points = listOf(
                    "Bandara et al. 2010 (review): all parts of yellow and common oleander are toxic; ingestion causes nausea, vomiting, abdominal pain, diarrhoea, dysrhythmias and hyperkalaemia.",
                    "Eddleston et al. 2000 (Heart, 351 patients): most symptomatic patients had sinus-node and/or AV-node conduction defects; tachyarrhythmias and ectopics typical of digoxin poisoning were relatively few.",
                    "Eddleston et al. 2000 (Heart): serious arrhythmias were associated with higher serum glycoside concentrations and hyperkalaemia, but not with magnesium disturbances.",
                    "Pillay & Sasidharan 2019 (IJCCM): also dizziness, lethargy and hypotension; mydriasis; seizures and coma in severe cases; mouth numbness or burning after chewing seeds.",
                    "CMC Vellore 2020 (30 patients): vomiting in 80%, metabolic acidosis in 29; second-degree AV block the commonest ECG abnormality; seed number (2–30) not associated with mortality.",
                    "Pillay & Sasidharan 2019: oleander glycosides cross-react with digoxin immunoassays — an elevated level may help confirm suspected poisoning.",
                ),
                checklistTitle = "Toxidrome signs",
                checklist = listOf(
                    "Nausea, vomiting, abdominal pain, diarrhoea",
                    "Sinus bradycardia or sinus arrest",
                    "AV block — first, second or third degree",
                    "Junctional or other escape rhythms",
                    "Ventricular ectopics, VT or VF",
                    "Hyperkalaemia",
                    "Hypotension, dizziness, lethargy",
                    "Numbness or burning of the mouth after chewing seeds",
                ),
                citations = listOf(
                    cite(OL_BANDARA, "Abstract"),
                    cite(OL_HEART, "Abstract — Results, Conclusions"),
                    cite(OL_PILLAY, "Pink oleander; Yellow oleander"),
                    cite(OL_CMC, "Results; Tables 1–2; Discussion"),
                    cite(OL_AHA, "§8 Introduction"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Monitoring and investigations",
                timing = "Continuous ECG for at least 24 h; longer if severe (Rajapakse 2009)",
                points = listOf(
                    "Rajapakse 2009 (Clin Toxicol review): continuous ECG monitoring for at least 24 h to detect arrhythmias, longer in severe poisoning; no definite risk-stratification criteria exist.",
                    "Pillay & Sasidharan 2019: baseline ECG, electrolytes and 24-hour ICU observation; repeat 12-lead ECGs, especially if symptomatic — check rhythm, QRS duration and QT.",
                    "Pillay & Sasidharan 2019: urea, electrolytes, magnesium and creatinine in all patients; serum potassium is among the most important tests, correlating with cardiotoxicity.",
                    "Eddleston et al. 2008 (Lancet RCT): 87% of oleander deaths occurred within 24 h of admission, most of them after 12 h.",
                    "Roberts et al. 2006 (pharmacokinetics): erratic, prolonged absorption of Thevetia glycosides; apparent terminal half-life highly variable, median 42.9 h.",
                ),
                citations = listOf(
                    cite(OL_RAJAPAKSE, "Abstract — Assessment and initial management"),
                    cite(OL_PILLAY, "Yellow oleander — management"),
                    cite(OL_MDAC_2008, "Discussion"),
                    cite(OL_PK, "Abstract"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Gut decontamination and charcoal",
                timing = "Single-dose charcoal if within 4 h of ingestion (Pillay & Sasidharan 2019)",
                points = listOf(
                    "Pillay & Sasidharan 2019: activated charcoal 50 g in adults or 1 g/kg in children presenting within 4 h of ingestion.",
                    "de Silva et al. 2003 (Lancet RCT, 401 patients): one dose, then 50 g 6-hourly for 3 days — deaths 2.5% vs 8% with placebo.",
                    "Eddleston et al. 2008 (Lancet RCT, 4632 patients, 1647 oleander): 50 g 4-hourly for six doses — no mortality benefit; authors: MDAC not indicated for oleander.",
                    "Rajapakse 2009: no firm recommendation for or against multiple-dose charcoal; single-dose charcoal probably beneficial; activated charcoal is clearly safe.",
                    "Cochrane 2006: some evidence that MDAC may be effective in yellow oleander poisoning — searches ran before the 2008 trial reported.",
                    "Pillay & Sasidharan 2019: gastric lavage is of limited benefit (seeds are large relative to the tube) and may worsen bradycardia through vagal stimulation.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Activated charcoal — children (Pillay & Sasidharan 2019)",
                        dose = WeightDose(minPerKg = 1.0, maxPerKg = 1.0, unit = "g", per = "single oral dose, if within 4 h of ingestion"),
                        note = "Adults: 50 g (same source). Multiple-dose regimens differed between the two RCTs — see notes.",
                    ),
                ),
                citations = listOf(
                    cite(OL_PILLAY, "Yellow oleander — management (decontamination)"),
                    cite(OL_DESILVA, "Abstract"),
                    cite(OL_MDAC_2008, "Abstract; Discussion"),
                    cite(OL_RAJAPAKSE, "Abstract — Gastric decontamination"),
                    cite(OL_COCHRANE, "Main results; Authors' conclusions"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Potassium and electrolytes",
                timing = "Recheck potassium frequently for several hours after Fab (FDA label)",
                points = listOf(
                    "Rajapakse 2009: hypokalaemia worsens glycoside toxicity and hyperkalaemia is life-threatening — both must be corrected; hypomagnesaemia should also be corrected.",
                    "Rajapakse 2009: hyperkalaemia reflects extracellular potassium shift rather than raised total-body potassium, and is best treated with insulin–dextrose infusion.",
                    "Pillay & Sasidharan 2019: adults — 10 units short-acting insulin with 50 mL 50% dextrose (or 100 mL 20%) IV over 5 min while Fab is obtained.",
                    "Pillay & Sasidharan 2019: K⁺ >6.5 mEq/L is normally an indication for Fab. FDA DigiFab label (digoxin): K⁺ >5.5 mEq/L in adults with rapidly progressive toxicity.",
                    "FDA DigiFab label: potassium may fall rapidly after Fab — monitor frequently over the first several hours; give cautious supplementation when necessary.",
                    "Rajapakse 2009: IV calcium increases arrhythmia risk and is not recommended for hyperkalaemia. AHA 2023 (mostly digoxin data): calcium appears neither harmful nor beneficial.",
                ),
                citations = listOf(
                    cite(OL_RAJAPAKSE, "Abstract — Electrolytes"),
                    cite(OL_PILLAY, "Yellow oleander — hyperkalaemia"),
                    cite(OL_FDA, "§1 Indications; §5.1; §5.4"),
                    cite(OL_AHA, "§8 Introduction"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Bradyarrhythmias: atropine",
                timing = "Symptomatic bradycardia or AV block",
                points = listOf(
                    "AHA 2023: atropine may be reasonable for bradydysrhythmias from digoxin and other cardiac glycosides (COR 2b, LOE C-LD); no cohort studies or RCTs published.",
                    "AHA 2023 Table 2 (listed for digoxin): atropine 0.5–1.0 mg IV every 3–5 min up to 3 mg; children 0.02 mg/kg.",
                    "Pillay & Sasidharan 2019: atropine 1 mg IV for sinus bradycardia and AV block; 3 mg (0.04 mg/kg) is generally considered fully vagolytic in most adults.",
                    "Fonseka et al. 2002 (Sri Lanka, 168 patients): IV atropine boluses and isoprenaline infusions — 80 of 90 treated received only these; 10 also needed pacing.",
                    "Rajapakse 2009: atropine and isoprenaline lack trial evidence of survival benefit; raising the heart rate theoretically increases tachyarrhythmia risk.",
                    "ICMR STW (bradyarrhythmia, 2025): look for digoxin as a reversible cause; in unstable patients, IV dopamine or isoprenaline may be given until temporary pacing is placed.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Atropine — children (AHA 2023 Table 2, listed for digoxin)",
                        dose = WeightDose(minPerKg = 0.02, maxPerKg = 0.02, unit = "mg", per = "IV/IO initial dose"),
                        note = "Adults: 0.5–1.0 mg every 3–5 min up to 3 mg. AHA 2023: maximum paediatric dose should not exceed the adult dose.",
                        maxTotal = 1.0,
                    ),
                ),
                citations = listOf(
                    cite(OL_AHA, "§8 Rec 4 and supportive text; Table 2"),
                    cite(OL_PILLAY, "Yellow oleander — atropine"),
                    cite(OL_FONSEKA, "Abstract"),
                    cite(OL_RAJAPAKSE, "Abstract — Arrhythmia management"),
                    cite(OL_ICMR, "Basic evaluation; Evaluation and treatment of unstable patients"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Temporary cardiac pacing",
                timing = "Bradycardia not responding to atropine (Pillay & Sasidharan 2019)",
                points = listOf(
                    "Pillay & Sasidharan 2019: consider pacing for severe bradycardia or slow ventricular rate from second-degree AV block not responding to atropine.",
                    "ICMR STW (bradyarrhythmia, 2025): urgent treatment or referral for SBP <90 mm Hg, impaired consciousness, complete heart block, sinus pauses >3 s or HR <50.",
                    "AHA 2023: pacing may be reasonable for glycoside bradydysrhythmias (COR 2b, LOE C-LD), including as a bridge while Fab is delayed or taking effect.",
                    "AHA 2023: transvenous pacing complications are common (36% in one series); some patients needed higher-than-normal currents or could not be re-paced after interruption.",
                    "CMC Vellore 2020: 15 of 30 patients were paced; pacing prolonged hospital stay; authors note no guidelines define pacing indications in yellow oleander poisoning.",
                ),
                citations = listOf(
                    cite(OL_PILLAY, "Yellow oleander — pacemaker"),
                    cite(OL_ICMR, "Indications for urgent treatment/referral"),
                    cite(OL_AHA, "§8 Rec 5 and supportive text"),
                    cite(OL_CMC, "Results; Table 3; Discussion"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Anti-digoxin Fab: evidence and indications",
                timing = "Effect usually within 15–30 min (Pillay & Sasidharan 2019)",
                points = listOf(
                    "Eddleston et al. 2000 (Lancet RCT, 66 patients with serious arrhythmia): Fab 1200 mg vs placebo — arrhythmia resolved by 2 h in 15/34 vs 2/32.",
                    "Eddleston et al. 2000: sinus rhythm at 8 h in 24 vs 5; with Fab, heart rate 49.1→66.8/min and mean potassium 4.9→4.1 mmol/L at 2 h.",
                    "Eddleston et al. 2000: outcomes were arrhythmia reversal, heart rate and potassium; authors state further study is required to confirm a reduction in deaths.",
                    "AHA 2023: Fab reasonable for yellow oleander (COR 2a, LOE C-LD); may be reasonable for other cardiac glycosides, e.g. common oleander (COR 2b; case reports only).",
                    "Pillay & Sasidharan 2019: Fab is the treatment of choice for atropine-unresponsive bradyarrhythmia with hypotension, life-threatening ventricular arrhythmia, or K⁺ >6.5 mEq/L.",
                    "Cochrane 2006: adverse effects in 13% of Fab-treated trial patients (bronchospasm in 2, mild angioedema in 1); all responded promptly to standard treatment.",
                ),
                citations = listOf(
                    cite(OL_FAB_RCT, "Abstract — Methods, Findings, Interpretation"),
                    cite(OL_AHA, "§8 Rec 2–3 and supportive text"),
                    cite(OL_PILLAY, "Yellow oleander — digoxin-specific antibodies"),
                    cite(OL_COCHRANE, "Results — Eddleston 2000"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Anti-digoxin Fab: dose, product and availability",
                points = listOf(
                    "AHA 2023 Table 2: yellow oleander — initial adult dose 1200 mg (30 vials), the dose used in the RCT; paediatric dose unknown.",
                    "Pillay & Sasidharan 2019: plant poisoning likely needs far larger Fab doses than digoxin poisoning — 10–20 vials (400–800 mg) in severe cases, repeated if necessary.",
                    "FDA DigiFab label: 40 mg Fab per vial, binding about 0.5 mg digoxin; IV infusion over at least 30 min, or bolus if cardiac arrest is imminent.",
                    "FDA DigiFab label: anaphylaxis possible, especially with papain, papaya, bromelain or sheep-protein allergy; draw digoxin levels before Fab if at all possible — immunoassays may mislead afterwards.",
                    "Eddleston et al. 2003 (Lancet): Sri Lankan Fab stocks ran out in 2002 owing to cost (US\$2650 per course); deaths then rose three-fold.",
                    "Rajapakse 2009 and CMC Vellore 2020: high cost and lack of availability limit or preclude Fab use in developing countries.",
                ),
                citations = listOf(
                    cite(OL_AHA, "Table 2"),
                    cite(OL_PILLAY, "Yellow oleander — digoxin-specific antibodies"),
                    cite(OL_FDA, "§2.1; §2.2; §5.2; §5.4; §11"),
                    cite(OL_COST, "Abstract"),
                    cite(OL_RAJAPAKSE, "Abstract — Digoxin-specific antibody fragments"),
                    cite(OL_CMC, "Discussion"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Ventricular arrhythmias, cardiac arrest and cautions",
                timing = "Cardiac arrest: CPR for at least 1 h (Pillay & Sasidharan 2019)",
                points = listOf(
                    "AHA 2023: lidocaine, phenytoin or bretylium may be reasonable for glycoside ventricular dysrhythmias until Fab can be given (COR 2b); bretylium is not currently manufactured.",
                    "Rajapakse 2009: tachyarrhythmias carry a poor prognosis and are harder to treat; lidocaine is the preferred antiarrhythmic; the role of IV magnesium is uncertain.",
                    "AHA 2023: follow standard ALS defibrillation guidance, adding Fab; case reports in arrest show variable response but no new dysrhythmia.",
                    "Pillay & Sasidharan 2019: start CPR immediately and continue for at least 1 hour — good outcomes are reported after prolonged resuscitation.",
                    "AHA 2023: haemodialysis, haemofiltration, haemoperfusion or plasmapheresis not recommended for digoxin poisoning (COR 3: No Benefit, LOE B-NR).",
                    "Pillay & Sasidharan 2019: haemodialysis or haemoperfusion unlikely to remove cardiac glycosides, but may help severe hyperkalaemia or acidosis with renal failure.",
                ),
                checklistTitle = "Sources advise against",
                checklist = listOf(
                    "IV calcium for hyperkalaemia — Rajapakse 2009 (contested; see notes)",
                    "Sodium polystyrene sulfonate with Fab — hypokalaemia (Rajapakse 2009)",
                    "Glucose–insulin–bicarbonate alongside Fab — steep potassium fall; use only if Fab unavailable (Pillay & Sasidharan 2019)",
                    "Gastric lavage — may worsen bradycardia (Pillay & Sasidharan 2019)",
                    "Extracorporeal removal to eliminate glycoside — no benefit for digoxin (AHA 2023)",
                    "Epinephrine for Fab anaphylaxis without weighing its risk in digitalis toxicity (FDA label)",
                ),
                citations = listOf(
                    cite(OL_AHA, "§8 Introduction; Rec 6–7 and supportive text"),
                    cite(OL_RAJAPAKSE, "Abstract — Arrhythmia management; Electrolytes"),
                    cite(OL_PILLAY, "Yellow oleander — resuscitation, hyperkalaemia, elimination"),
                    cite(OL_FDA, "§5.2"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Charcoal RCTs conflict. de Silva 2003 (single-blind, 401 patients, 50 g 6-hourly for 3 days): deaths 2.5% vs 8% with placebo. " +
                    "Eddleston 2008 (open-label, 1647 oleander patients, 50 g 4-hourly for six doses): oleander deaths 4.3% with MDAC vs 4.7% with a single dose, not significant.",
                listOf(cite(OL_DESILVA, "Abstract"), cite(OL_MDAC_2008, "Abstract; Discussion")),
            ),
            ProtocolNote(
                "Eddleston 2008 authors: 87% of oleander deaths occurred within 24 h, so de Silva's longer regimen is unlikely to explain the difference; " +
                    "the combined evidence does not suggest a major effect of charcoal. Rajapakse 2009: no firm recommendation for or against MDAC. " +
                    "Cochrane 2006 (searched before the 2008 trial): some evidence MDAC may be effective.",
                listOf(cite(OL_MDAC_2008, "Discussion"), cite(OL_RAJAPAKSE, "Abstract — Gastric decontamination"), cite(OL_COCHRANE, "Authors' conclusions")),
            ),
            ProtocolNote(
                "Fab evidence: the RCT showed arrhythmia reversal, not mortality; AHA 2023 describes it as in haemodynamically stable patients. " +
                    "Mortality evidence is observational — Sri Lankan deaths rose three-fold when stocks ran out (Eddleston 2003); " +
                    "Rajapakse 2009 calls Fab the only proven therapy.",
                listOf(
                    cite(OL_FAB_RCT, "Abstract — Interpretation"),
                    cite(OL_AHA, "§8 Rec 2 supportive text"),
                    cite(OL_COST, "Abstract"),
                    cite(OL_RAJAPAKSE, "Abstract — Conclusions"),
                ),
            ),
            ProtocolNote(
                "Fab dose differs: AHA 2023 — 1200 mg (30 vials), the RCT dose; Pillay & Sasidharan 2019 — 10–20 vials (400–800 mg); " +
                    "FDA label for acute digoxin ingestion of unknown amount — 20 vials (10, then 10 more if needed).",
                listOf(cite(OL_AHA, "Table 2"), cite(OL_PILLAY, "Yellow oleander — digoxin-specific antibodies"), cite(OL_FDA, "§2 Dosing table")),
            ),
            ProtocolNote(
                "Calcium for hyperkalaemia differs: Pillay & Sasidharan 2019 (IJCCM) list IV calcium with insulin–dextrose, and the ICMR bradyarrhythmia STW " +
                    "(not poisoning-specific) lists 10 mL 10% calcium gluconate; Rajapakse 2009 advises against IV calcium; " +
                    "AHA 2023 — harm from calcium in digoxin poisoning not quantified, and no evidence of benefit.",
                listOf(
                    cite(OL_PILLAY, "Yellow oleander — hyperkalaemia"),
                    cite(OL_ICMR, "Evaluation and treatment of unstable patients"),
                    cite(OL_RAJAPAKSE, "Abstract — Electrolytes"),
                    cite(OL_AHA, "§8 Introduction"),
                ),
            ),
            ProtocolNote(
                "Isoprenaline differs: the ICMR bradyarrhythmia STW allows IV dopamine or isoprenaline until pacing, and Sri Lankan practice used isoprenaline " +
                    "(Fonseka 2002); Rajapakse 2009 notes that beta-agonists theoretically raise tachyarrhythmia risk and their risks have not been adequately evaluated.",
                listOf(
                    cite(OL_ICMR, "Evaluation and treatment of unstable patients"),
                    cite(OL_FONSEKA, "Abstract"),
                    cite(OL_RAJAPAKSE, "Abstract — Arrhythmia management"),
                ),
            ),
            ProtocolNote(
                "Both RCTs studied yellow oleander. Cochrane 2006: results cannot be readily translated to other cardenolides. AHA 2023 grades Fab lower " +
                    "for glycosides other than digoxin, digitoxin, Bufo venom and yellow oleander (COR 2b, case reports). Bandara 2010: Fab effective for either oleander.",
                listOf(cite(OL_COCHRANE, "Authors' conclusions"), cite(OL_AHA, "§8 Rec 3"), cite(OL_BANDARA, "Abstract")),
            ),
            ProtocolNote(
                "India: these sources do not establish whether anti-digoxin Fab is registered or stocked in India. CMC Vellore 2020 managed patients " +
                    "supportively, with temporary pacing in half, and states Fab use in developing nations is precluded by non-availability and high cost.",
                listOf(cite(OL_CMC, "Patient management; Discussion")),
            ),
            ProtocolNote(
                "Practice vs sources: in the CMC Vellore 2020 cohort, 73% had gastric lavage at the referring hospital and 80% on arrival; " +
                    "Pillay & Sasidharan 2019 state lavage is of limited benefit and may worsen bradycardia.",
                listOf(cite(OL_CMC, "Results — baseline characteristics"), cite(OL_PILLAY, "Yellow oleander — management")),
            ),
            ProtocolNote(
                "Scope: the ICMR STW cited covers symptomatic bradyarrhythmia in general, not poisoning; Indian oleander-specific content comes from " +
                    "peer-reviewed sources (IJCCM 2019 review, CMC Vellore 2020 cohort). Sri Lankan trials supply the randomised evidence.",
                listOf(cite(OL_ICMR, "Scope"), cite(OL_PILLAY), cite(OL_CMC), cite(OL_FAB_RCT), cite(OL_DESILVA), cite(OL_MDAC_2008)),
            ),
        ),
        citations = listOf(
            cite(OL_PILLAY), cite(OL_CMC), cite(OL_ICMR), cite(OL_FAB_RCT), cite(OL_DESILVA), cite(OL_MDAC_2008),
            cite(OL_COCHRANE), cite(OL_RAJAPAKSE), cite(OL_HEART), cite(OL_COST), cite(OL_FONSEKA), cite(OL_PK),
            cite(OL_BANDARA), cite(OL_AHA), cite(OL_FDA),
        ),
    ),
)
