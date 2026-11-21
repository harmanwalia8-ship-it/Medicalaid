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
 * Household and general poisoning: first steps, corrosives, carbon monoxide, hydrocarbons.
 * Rules:
 * - Doses, thresholds and windows exactly as the cited document states them; nothing filled from memory.
 * - Every point is attributed ("WHO (children): …", "AACT/EAPCCT 2013: …", "WSES 2019: …", "IJCCM review: …").
 * - IJCCM = Indian Journal of Critical Care Medicine (ISCCM journal); these are invited reviews, not society guidelines.
 * - CTRC 2026 and ISG 2025 were reviewed from their published abstracts only (full text paywalled).
 * - Checked November 2026.
 */

internal val householdToxSources = listOf(
    // General approach and decontamination ------------------------------------------------------
    Source(
        "tx-hh-who-pocketbook-2013", Authority.WHO, "Pocket book, 2nd edition (ISBN 978-92-4-154837-3)",
        "Pocket book of hospital care for children: guidelines for the management of common childhood illnesses", "2013",
        "https://www.who.int/publications/i/item/978-92-4-154837-3",
        status = "Paediatric, first-referral-level hospitals; poisoning in §1.6 (pp. 26–33)",
    ),
    Source(
        "tx-hh-aact-sdac-2005", Authority.Paper, "AACT/EAPCCT position paper (Clin Toxicol 2005;43:61–87)",
        "Position paper: single-dose activated charcoal", "2005",
        "https://doi.org/10.1081/CLT-200051867",
    ),
    Source(
        "tx-hh-aact-lavage-2013", Authority.Paper, "AACT/EAPCCT position paper update (Clin Toxicol 2013;51:140–146)",
        "Position paper update: gastric lavage for gastrointestinal decontamination", "2013",
        "https://doi.org/10.3109/15563650.2013.770154",
    ),
    Source(
        "tx-hh-aact-ipecac-2013", Authority.Paper, "AACT/EAPCCT position paper update (Clin Toxicol 2013;51:134–139)",
        "Position paper update: ipecac syrup for gastrointestinal decontamination", "2013",
        "https://doi.org/10.3109/15563650.2013.770153",
    ),
    Source(
        "tx-hh-aact-mdac-1999", Authority.Paper, "AACT/EAPCCT position statement (J Toxicol Clin Toxicol 1999;37:731–751)",
        "Position statement and practice guidelines on the use of multi-dose activated charcoal in the treatment of acute poisoning",
        "1999",
        "https://doi.org/10.1081/CLT-100102451",
    ),
    Source(
        "tx-hh-aact-wbi-2015", Authority.Paper, "AACT/EAPCCT position paper update (Clin Toxicol 2015;53:5–12)",
        "Position paper update: whole bowel irrigation for gastrointestinal decontamination of overdose patients", "2015",
        "https://doi.org/10.3109/15563650.2014.989326",
    ),
    Source(
        "tx-hh-ctrc-charcoal-2026", Authority.Paper,
        "Clinical Toxicology Recommendations Collaborative (Clin Toxicol 2026;64(6):419–475)",
        "Recommendations from the Clinical Toxicology Recommendations Collaborative on the administration of activated charcoal in acute oral overdose",
        "2026",
        "https://doi.org/10.1080/15563650.2025.2609807",
        status = "Reviewed from the published abstract; agent-specific time limits are in the full text",
    ),
    Source(
        "tx-hh-ijccm-initial-2019", Authority.Paper, "Invited review, Indian J Crit Care Med 2019;23(Suppl 4):S234–S240 (ISCCM journal)",
        "Initial management of poisoned patient", "2019",
        "https://doi.org/10.5005/jp-journals-10071-23307",
        status = "Review article, not a society guideline",
    ),
    Source(
        "tx-hh-anz-paracetamol-2020", Authority.Paper,
        "Guideline of the Australian and New Zealand poisons information centres (Med J Aust 2020;212:175–183)",
        "Updated guidelines for the management of paracetamol poisoning in Australia and New Zealand", "2020",
        "https://doi.org/10.5694/mja2.50428",
    ),
    Source(
        "tx-hh-ncdc-chem-2025", Authority.MoHFW, "IDSP-NCDC training module (Dte.GHS, MoHFW)",
        "Medical Management of Chemical Emergencies", "2025",
        "https://ncdc.mohfw.gov.in/uploads/glimpse_pdfs/1770271099_CE-3%20(1).pdf",
        status = "Training module for chemical emergencies, not a clinical treatment guideline",
    ),
    Source(
        "tx-hh-aiims-npic", Authority.MoHFW, "AIIMS New Delhi (institute under MoHFW) — web page",
        "National Poisons Information Centre (NPIC), Department of Pharmacology, AIIMS New Delhi", "2026",
        "https://aiims.edu/index.php/en/npic_intro",
        status = "Contact numbers as shown on the AIIMS website, checked 21 November 2026",
    ),
    // Self-harm ----------------------------------------------------------------------------------
    Source(
        "tx-hh-nice-ng225", Authority.NICE, "Guideline NG225",
        "Self-harm: assessment, management and preventing recurrence", "2022",
        "https://www.nice.org.uk/guidance/ng225",
        status = "Published 7 September 2022; UK guidance",
    ),
    Source(
        "tx-hh-mhca-2017", Authority.MoHFW, "Act No. 10 of 2017",
        "The Mental Healthcare Act, 2017", "2017",
        "https://qps.nhsrcindia.org/sites/default/files/2022-01/THE%20MENTAL%20HEALTHCARE%20ACT%2C%202017.pdf",
        status = "Official text; PDF hosted by NHSRC (MoHFW)",
    ),
    Source(
        "tx-hh-telemanas-2022", Authority.MoHFW, "PIB press release (MoHFW), October 2022",
        "Tele Mental Health Assistance and Networking Across States (Tele-MANAS) initiative launched", "2022",
        "https://www.pib.gov.in/PressReleasePage.aspx?PRID=1866498",
    ),
    // Corrosives ---------------------------------------------------------------------------------
    Source(
        "tx-hh-wses-caustic-2015", Authority.WSES, "Consensus conference (World J Emerg Surg 2015;10:44)",
        "Foregut caustic injuries: results of the World Society of Emergency Surgery consensus conference", "2015",
        "https://doi.org/10.1186/s13017-015-0039-0",
        status = "Oxford 2011 levels of evidence",
    ),
    Source(
        "tx-hh-wses-esophageal-2019", Authority.WSES, "Guidelines (World J Emerg Surg 2019;14:26)",
        "Esophageal emergencies: WSES guidelines", "2019",
        "https://doi.org/10.1186/s13017-019-0245-2",
        status = "Corrosive-ingestion section; GRADE-style grades",
    ),
    Source(
        "tx-hh-isg-caustic-2025", Authority.Paper,
        "Indian Society of Gastroenterology practice guideline (Indian J Gastroenterol 2025;44(5):646–674)",
        "Short-term and long-term management of caustic-induced gastrointestinal injury: an evidence-based practice guidelines",
        "2025",
        "https://doi.org/10.1007/s12664-024-01692-1",
        status = "41 modified-Delphi statements; reviewed from the published abstract only",
    ),
    Source(
        "tx-hh-zargar-1991", Authority.Paper, "Original paper, PGIMER Chandigarh (Gastrointest Endosc 1991;37:165–169)",
        "The role of fiberoptic endoscopy in the management of corrosive ingestion and modified endoscopic classification of burns",
        "1991",
        "https://doi.org/10.1016/S0016-5107(91)70678-0",
    ),
    Source(
        "tx-hh-ijccm-corrosive-2019", Authority.Paper, "Invited review, Indian J Crit Care Med 2019;23(Suppl 4):S282–S286 (JIPMER authors)",
        "Corrosive ingestion", "2019",
        "https://doi.org/10.5005/jp-journals-10071-23305",
        status = "Review article, not a society guideline",
    ),
    // Carbon monoxide ----------------------------------------------------------------------------
    Source(
        "tx-hh-acep-co-2025", Authority.ACEP, "Clinical policy (Ann Emerg Med 2025;85:e45–e59)",
        "A critical issue in the management of adult patients presenting to the emergency department with acute carbon monoxide poisoning",
        "2025",
        "https://doi.org/10.1016/j.annemergmed.2024.12.005",
        status = "Approved by the ACEP Board 22 January 2025; revisits only the hyperbaric-oxygen question of the 2017 policy",
    ),
    Source(
        "tx-hh-acep-co-2017", Authority.ACEP, "Clinical policy (Ann Emerg Med 2017;69:98–107)",
        "Clinical policy: critical issues in the evaluation and management of adult patients presenting to the emergency department with acute carbon monoxide poisoning",
        "2017",
        "https://doi.org/10.1016/j.annemergmed.2016.11.003",
        status = "ACEP 2025 states its noninvasive-COHb and cardiac-testing recommendations are being reaffirmed separately",
    ),
    Source(
        "tx-hh-echm-2016", Authority.Paper,
        "European Committee for Hyperbaric Medicine, 10th Consensus Conference, April 2016 (Diving Hyperb Med 2017;47:24–32)",
        "Tenth European Consensus Conference on Hyperbaric Medicine: recommendations for accepted and non-accepted clinical indications and practice of hyperbaric oxygen treatment",
        "2017",
        "https://doi.org/10.28920/dhm47.1.24-32",
    ),
    Source(
        "tx-hh-cdc-co-guidance", Authority.CDC, "Clinical guidance (web page)",
        "Clinical Guidance for Carbon Monoxide Poisoning Following Disasters and Severe Weather", "2026",
        "https://www.cdc.gov/carbon-monoxide/hcp/clinical-guidance/index.html",
        status = "Page reviewed 21 August 2026",
    ),
    Source(
        "tx-hh-cdc-han-00476", Authority.CDC, "Health Alert Network advisory HAN-00476",
        "Hurricanes Fiona and Ian — clinical guidance for carbon monoxide (CO) poisoning", "2022",
        "https://archive.cdc.gov/www_cdc_gov/han/2022/han00476.html",
        status = "Archived advisory",
    ),
    Source(
        "tx-hh-fda-cyanokit", Authority.FDA, "Prescribing information (DailyMed)",
        "CYANOKIT (hydroxocobalamin for injection)", "2023",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=d56fcc8d-bd64-46ab-b0c0-2124bd745a6b",
        status = "Label effective April 2023",
    ),
    Source(
        "tx-hh-fda-nithiodote", Authority.FDA, "Prescribing information (DailyMed)",
        "NITHIODOTE (sodium nitrite and sodium thiosulfate injection)", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=ff4941b3-9901-4aab-adcf-c5327bede34e",
        status = "Label effective October 2025",
    ),
    // Hydrocarbons -------------------------------------------------------------------------------
    Source(
        "tx-hh-ijccm-kerosene-2019", Authority.Paper, "Invited review, Indian J Crit Care Med 2019;23(Suppl 4):S278–S281 (PGIMER authors)",
        "Kerosene, camphor, and naphthalene poisoning in children", "2019",
        "https://doi.org/10.5005/jp-journals-10071-23316",
        status = "Review article, not a society guideline",
    ),
    Source(
        "tx-hh-anas-1981", Authority.Paper, "Original paper (JAMA 1981;246:840–843)",
        "Criteria for hospitalizing children who have ingested products containing hydrocarbons", "1981",
        "https://pubmed.ncbi.nlm.nih.gov/7253158/",
    ),
)

private const val HH_WHO = "tx-hh-who-pocketbook-2013"
private const val HH_SDAC = "tx-hh-aact-sdac-2005"
private const val HH_LAVAGE = "tx-hh-aact-lavage-2013"
private const val HH_IPECAC = "tx-hh-aact-ipecac-2013"
private const val HH_MDAC = "tx-hh-aact-mdac-1999"
private const val HH_WBI = "tx-hh-aact-wbi-2015"
private const val HH_CTRC = "tx-hh-ctrc-charcoal-2026"
private const val HH_IJCCM = "tx-hh-ijccm-initial-2019"
private const val HH_ANZ = "tx-hh-anz-paracetamol-2020"
private const val HH_NCDC = "tx-hh-ncdc-chem-2025"
private const val HH_NPIC = "tx-hh-aiims-npic"
private const val HH_NICE = "tx-hh-nice-ng225"
private const val HH_MHCA = "tx-hh-mhca-2017"
private const val HH_TELEMANAS = "tx-hh-telemanas-2022"
private const val HH_WSES15 = "tx-hh-wses-caustic-2015"
private const val HH_WSES19 = "tx-hh-wses-esophageal-2019"
private const val HH_ISG = "tx-hh-isg-caustic-2025"
private const val HH_ZARGAR = "tx-hh-zargar-1991"
private const val HH_CORR = "tx-hh-ijccm-corrosive-2019"
private const val HH_ACEP25 = "tx-hh-acep-co-2025"
private const val HH_ACEP17 = "tx-hh-acep-co-2017"
private const val HH_ECHM = "tx-hh-echm-2016"
private const val HH_CDC = "tx-hh-cdc-co-guidance"
private const val HH_HAN = "tx-hh-cdc-han-00476"
private const val HH_CYANOKIT = "tx-hh-fda-cyanokit"
private const val HH_NITHIODOTE = "tx-hh-fda-nithiodote"
private const val HH_KERO = "tx-hh-ijccm-kerosene-2019"
private const val HH_ANAS = "tx-hh-anas-1981"

internal val householdToxProtocols = listOf(
    // =============================================================================================
    // 1. Poisoned patient: first steps
    // =============================================================================================
    Protocol(
        id = "poisoning-general",
        title = "Poisoned patient: first steps",
        summary = "Resuscitation, history, toxidromes, bedside tests, charcoal, lavage and emesis, enhanced elimination, " +
            "poison-centre advice and psychosocial care — attributed to WHO, AACT/EAPCCT, CTRC, NICE and Indian sources.",
        keywords = listOf(
            "poisoning", "poisoned patient", "overdose", "OD", "self-poisoning", "deliberate self-harm", "DSH",
            "suicide attempt", "zeher", "zehar", "unknown poison", "unknown ingestion", "toxidrome", "activated charcoal",
            "charcoal", "gastric lavage", "stomach wash", "ipecac", "induced vomiting", "decontamination",
            "whole bowel irrigation", "urine alkalinisation", "poison centre", "poison information", "NPIC",
            "AIIMS poison helpline", "1800 116 117",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Resuscitate first",
                timing = "At first contact",
                points = listOf(
                    "IJCCM review: airway, breathing and circulation take priority; evaluation and treatment often run together; protect staff safety.",
                    "WHO (children): assess emergency signs — airway, breathing, circulation, consciousness — as some poisons depress breathing, cause shock or induce coma.",
                    "WHO (children): check for hypoglycaemia; if glucose cannot be measured and consciousness is reduced, treat as hypoglycaemia.",
                    "IJCCM review: lost airway reflexes or respiratory failure — secure the airway; rapid sequence intubation is preferred unless the patient is moribund.",
                    "WHO (children): keep unconscious children in the recovery position.",
                ),
                citations = listOf(
                    cite(HH_IJCCM, "Initial assessment; Supportive care — airway management"),
                    cite(HH_WHO, "§1.6.1 Principles for ingested poisons (pp. 27–28)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "History and poison-centre advice",
                points = listOf(
                    "WHO (children): record the agent, amount and time of ingestion; ask to see the container; check whether other children were involved.",
                    "WHO (children): suspect poisoning in any unexplained illness in a previously healthy child; traditional medicines can be a source.",
                    "NICE NG225: after self-harm, establish the means used and, if still accessible, discuss removing it with the person.",
                    "AIIMS National Poisons Information Centre, New Delhi: toll-free 1800 116 117, 24 hours a day, every day; landlines (011) 26589391 / 26593677.",
                    "WHO (children): for specific poisons, consult local poisoning expertise such as a poison centre.",
                ),
                citations = listOf(
                    cite(HH_WHO, "§1.6 Common poisoning — Diagnosis (p. 26)"),
                    cite(HH_NICE, "Rec 1.7.1"),
                    cite(HH_NPIC, "NPIC home page"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise the toxidrome",
                points = listOf(
                    "NCDC 2025 module: key observations — vital signs, mental status, pupil size, mucous membranes, lung exam (wheeze, crackles) and skin (burns, moisture, colour).",
                    "IJCCM review: a toxidrome-oriented examination suggests the class of toxin, but a patient may not show every feature.",
                    "IJCCM review: history may be inaccurate — confirm with examination and tests.",
                ),
                checklistTitle = "Toxidromes (IJCCM review, Table 1)",
                checklist = listOf(
                    "Cholinergic — confusion or coma, miosis, salivation, lacrimation, sweating, vomiting, diarrhoea, incontinence, bronchoconstriction, fasciculations, weakness, seizures (organophosphates, carbamates)",
                    "Anticholinergic — agitation, delirium, hallucinations, mydriasis, hyperthermia, tachycardia, dry flushed skin, dry mouth, reduced bowel sounds, urinary retention (antihistamines, TCA, atropine)",
                    "Opioid — CNS depression or coma, miosis, slow breathing or apnoea, hyporeflexia, pulmonary oedema, needle marks (morphine, heroin, methadone)",
                    "Sympathomimetic — agitation, paranoia, hallucinations, mydriasis, hyperthermia, tachycardia, hypertension, sweating, tremor, hyperreflexia, seizures (cocaine, amphetamines)",
                    "Sedative-hypnotic — confusion, stupor or coma, variable pupils; vitals often normal, or hypothermia, bradycardia, hypotension, slow breathing (benzodiazepines, barbiturates, alcohols)",
                    "Serotonin syndrome — agitation, mydriasis, hyperthermia, tachycardia, tremor, myoclonus, clonus, hyperreflexia, diarrhoea (SSRIs, MAOIs, TCA)",
                ),
                citations = listOf(
                    cite(HH_IJCCM, "Risk assessment; Diagnostic testing; Table 1"),
                    cite(HH_NCDC, "Toxidromes — key data points"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Bedside and laboratory tests",
                points = listOf(
                    "WHO (children): check blood glucose. IJCCM review: CBC, electrolytes, urea, creatinine, liver tests, lactate and arterial blood gas.",
                    "IJCCM review: ECG in every poisoned patient — tachy- or bradycardia with narrow or wide QRS points to drug groups and severity.",
                    "IJCCM review: urine pregnancy test in all women of childbearing age.",
                    "ANZ 2020: deliberate paracetamol self-poisoning — measure serum paracetamol regardless of stated dose; the nomogram applies only to acute immediate-release ingestion with known time.",
                    "IJCCM review: drug levels guide care for a few agents, e.g. paracetamol, salicylate, iron, lithium, digoxin, toxic alcohols, carboxyhaemoglobin, methaemoglobin.",
                    "IJCCM review: urine drug screens seldom change management; a negative screen does not exclude exposure.",
                ),
                citations = listOf(
                    cite(HH_WHO, "§1.6.1 (p. 27)"),
                    cite(HH_IJCCM, "Diagnostic testing; Table 2; Circulation and hemodynamics (ECG, Table 3)"),
                    cite(HH_ANZ, "Initial approach — risk assessment"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Activated charcoal",
                timing = "Within 1 h (AACT/EAPCCT 2005); up to 6 h for many poisons (CTRC 2026)",
                points = listOf(
                    "AACT/EAPCCT 2005: not routine. Consider if a potentially toxic amount of a charcoal-adsorbed poison was taken within 1 h; later benefit not excluded.",
                    "AACT/EAPCCT 2005: no evidence that charcoal improves clinical outcome; contraindicated unless the airway is intact or protected.",
                    "CTRC 2026: after individual risk assessment, charcoal is appropriate up to 6 h for many poisons; later if absorption continues (bezoar, modified-release).",
                    "CTRC 2026: do not intubate solely to give charcoal unless significant toxicity is expected; no nasogastric tube without intubation solely for charcoal.",
                    "WHO (children) single dose: ≤1 year 1 g/kg; 1–12 years 25–50 g; adolescents and adults 25–100 g; mix in 8–10 volumes of water.",
                ),
                checklistTitle = "Charcoal not indicated",
                checklist = listOf(
                    "Airway not intact or protected (AACT/EAPCCT 2005)",
                    "Corrosives — acids, alkalis, bleach (WHO)",
                    "Kerosene, petrol and other petroleum products (WHO)",
                    "No role (CTRC 2026): iron, lithium, ethanol, methanol, ethylene glycol, arsenic, lead, copper, caesium, metformin",
                    "Metals, corrosives and alcohols are poorly adsorbed (IJCCM review)",
                ),
                doses = listOf(
                    StepDose(
                        label = "Activated charcoal — child ≤1 year (WHO)",
                        dose = WeightDose(minPerKg = 1.0, maxPerKg = 1.0, unit = "g", per = "single dose"),
                        note = "WHO Table 5: by mouth or nasogastric tube (WHO: make sure the tube is in the stomach, not the airway or lungs), " +
                            "mixed in 8–10 volumes of water (e.g. 5 g in 40 mL). CTRC 2026: no NG/OG tube without intubation solely to give charcoal. " +
                            "1–12 years: 25–50 g; adolescents and adults: 25–100 g.",
                    ),
                ),
                citations = listOf(
                    cite(HH_SDAC, "Abstract (summary statement)"),
                    cite(HH_CTRC, "Abstract — Results and good practice statements"),
                    cite(HH_WHO, "§1.6.1; Table 5 (pp. 27–28)"),
                    cite(HH_IJCCM, "Decontamination — activated charcoal"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Gastric lavage and induced vomiting",
                points = listOf(
                    "AACT/EAPCCT 2013: gastric lavage should not be performed routinely, if at all; when rarely indicated, only by trained, experienced staff.",
                    "AACT/EAPCCT 2013: avoid routine ipecac at home or in the emergency department; it may delay or weaken charcoal, oral antidotes and bowel irrigation.",
                    "IJCCM review: GI decontamination is no longer routine — consider only very early (<1 h) or for a very large, dangerous overdose without an antidote.",
                    "WHO (children): lavage only if staff are experienced, ingestion was <1 h ago and life-threatening, and not corrosive or petroleum; protect the airway.",
                    "WHO (children): never use salt as an emetic — it can be fatal.",
                ),
                checklistTitle = "Do not",
                checklist = listOf(
                    "Salt-water emetic (WHO: can be fatal)",
                    "Induce vomiting after corrosive or kerosene/petrol ingestion (WHO)",
                    "Lavage after corrosives or petroleum products (WHO)",
                    "Lavage or charcoal in an unconscious patient without a protected airway (WHO; AACT/EAPCCT)",
                ),
                citations = listOf(
                    cite(HH_LAVAGE, "Abstract (conclusions)"),
                    cite(HH_IPECAC, "Abstract (conclusions)"),
                    cite(HH_IJCCM, "Decontamination — gastrointestinal decontamination"),
                    cite(HH_WHO, "§1.6.1 Principles for ingested poisons; Gastric lavage (pp. 27–28)"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Enhanced elimination",
                points = listOf(
                    "AACT/EAPCCT 1999: multiple-dose charcoal only for life-threatening carbamazepine, dapsone, phenobarbital, quinine or theophylline; no cathartics, especially in young children.",
                    "CTRC 2026: multiple-dose charcoal appropriate for carbamazepine, cardiac glycosides, colchicine, dapsone, phenobarbital, phenytoin, thallium and theophylline.",
                    "AACT/EAPCCT 2015: whole bowel irrigation can be considered for sustained-release or enteric-coated drugs, iron, lithium, potassium, or drug packets.",
                    "AACT/EAPCCT 2015: no bowel irrigation with obstruction, perforation, ileus, haemodynamic instability or a compromised unprotected airway.",
                    "IJCCM review: urine alkalinisation for weak acids such as salicylate and phenobarbital — target urine pH 7.5–8.5; " +
                        "contraindicated with pre-existing fluid overload, renal impairment or hypokalaemia (correct hypokalaemia first).",
                    "IJCCM review: extracorporeal removal suits toxins with low volume of distribution (<1 L/kg), low molecular weight and low protein binding.",
                ),
                doses = listOf(
                    StepDose(
                        label = "Multiple-dose activated charcoal — each repeat dose (IJCCM review)",
                        dose = WeightDose(minPerKg = 0.5, maxPerKg = 0.5, unit = "g", per = "per repeat dose, after the initial standard dose"),
                        note = "Repeat interval not stated in the source. Only for the agents listed in this step. " +
                            "Contraindicated unless the airway is intact or protected, and with intestinal obstruction; no cathartics (AACT/EAPCCT 1999).",
                    ),
                    StepDose(
                        label = "Sodium bicarbonate — urine alkalinisation bolus (IJCCM review)",
                        dose = WeightDose(minPerKg = 1.0, maxPerKg = 2.0, unit = "mEq", per = "IV bolus"),
                        note = "Contraindicated with pre-existing fluid overload or renal impairment; correct hypokalaemia first. " +
                            "Then 100 mEq in 1 L 5% dextrose at 250 mL/h (20 mEq KCl may be added); " +
                            "check urine pH every 15–30 min, target 7.5–8.5.",
                    ),
                ),
                citations = listOf(
                    cite(HH_MDAC, "Abstract (summary statement)"),
                    cite(HH_CTRC, "Abstract — Results"),
                    cite(HH_WBI, "Abstract (results)"),
                    cite(HH_IJCCM, "Elimination — MDAC, urine alkalinization, extracorporeal removal"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Self-poisoning: psychosocial assessment and disposition",
                timing = "Alongside medical treatment, not after it (NICE NG225)",
                points = listOf(
                    "NICE NG225: refer to liaison psychiatry (crisis service for children) soon after arrival; assess alongside physical care, not after it or awaiting alcohol levels.",
                    "NICE NG225: do not use risk tools, scales or low/medium/high stratification to predict suicide or to decide treatment or discharge.",
                    "NICE NG225: if the person wants to leave before full assessment, assess safety and mental health first; consider admission if too distressed or intoxicated to engage.",
                    "WHO (children): admit deliberate iron, pesticide, paracetamol, aspirin, narcotic or antidepressant ingestions and suspected poisoning by others; observe 4–24 h by poison.",
                    "MHCA 2017 s.115: a suicide attempt is presumed due to severe stress and is not to be tried or punished; government must provide care and rehabilitation.",
                    "Tele-MANAS (MoHFW): national 24×7 toll-free tele-mental health helpline 14416.",
                ),
                citations = listOf(
                    cite(HH_NICE, "Rec 1.5.2, 1.5.4, 1.5.14, 1.6.1–1.6.4, 1.7.3, 1.7.13, 1.9.1"),
                    cite(HH_WHO, "§1.6 Diagnosis (p. 26); §1.6.1 (p. 28)"),
                    cite(HH_MHCA, "Section 115"),
                    cite(HH_TELEMANAS, "Press release, October 2022"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Charcoal timing differs: AACT/EAPCCT 2005 — consider within 1 h of ingestion; CTRC 2026 — up to 6 h for many poisons after individual risk assessment. " +
                    "CTRC 2026 was reviewed from its abstract; agent-specific time limits are in the full text.",
                listOf(cite(HH_SDAC, "Abstract"), cite(HH_CTRC, "Abstract — Results")),
            ),
            ProtocolNote(
                "Charcoal route differs: the WHO pocket book allows a nasogastric tube, taking care that it is in the stomach and not the airway or lungs; " +
                    "CTRC 2026 advises against nasogastric or orogastric tube insertion without endotracheal intubation solely to give charcoal.",
                listOf(cite(HH_WHO, "§1.6.1 (p. 27)"), cite(HH_CTRC, "Abstract — good practice statements")),
            ),
            ProtocolNote(
                "Emesis and lavage: AACT/EAPCCT 2013 and the IJCCM review advise against routine use; the WHO pocket book (2013) still allows paediatric ipecac " +
                    "in a conscious child when charcoal is unavailable, and lavage in selected life-threatening ingestions.",
                listOf(cite(HH_LAVAGE, "Abstract"), cite(HH_IPECAC, "Abstract"), cite(HH_IJCCM, "Decontamination"), cite(HH_WHO, "§1.6.1 (p. 28)")),
            ),
            ProtocolNote(
                "India: the NCDC/MoHFW 2025 chemical-emergency training module lists gastric lavage as useful within 1–4 h, emetics including salt water, and charcoal as " +
                    "most effective for alcohol. WHO warns that salt emetics can be fatal; CTRC 2026 finds no role for charcoal in ethanol poisoning.",
                listOf(
                    cite(HH_NCDC, "Fig. 16 Specific decontamination process"),
                    cite(HH_WHO, "§1.6.1 (p. 27)"),
                    cite(HH_CTRC, "Abstract — Results"),
                ),
            ),
            ProtocolNote(
                "Multiple-dose charcoal lists differ: AACT/EAPCCT 1999 — carbamazepine, dapsone, phenobarbital, quinine, theophylline; CTRC 2026 adds cardiac glycosides, " +
                    "colchicine, phenytoin and thallium and does not list quinine for multiple dosing.",
                listOf(cite(HH_MDAC, "Abstract"), cite(HH_CTRC, "Abstract — Results")),
            ),
            ProtocolNote(
                "NICE NG225 is UK guidance and points clinicians to UK poison services (TOXBASE, NPIS); in India, poison information is available from the AIIMS NPIC.",
                listOf(cite(HH_NICE, "Rec 1.7.4"), cite(HH_NPIC, "NPIC home page")),
            ),
        ),
        citations = listOf(
            cite(HH_WHO), cite(HH_SDAC), cite(HH_LAVAGE), cite(HH_IPECAC), cite(HH_MDAC), cite(HH_WBI), cite(HH_CTRC),
            cite(HH_IJCCM), cite(HH_ANZ), cite(HH_NCDC), cite(HH_NPIC), cite(HH_NICE), cite(HH_MHCA), cite(HH_TELEMANAS),
        ),
    ),

    // =============================================================================================
    // 2. Corrosive ingestion
    // =============================================================================================
    Protocol(
        id = "corrosives",
        title = "Corrosive ingestion (acids, alkalis, toilet cleaners)",
        summary = "First-contact don'ts, airway, laboratory red flags, CT and endoscopy with Zargar grading, steroid and antibiotic positions, " +
            "perforation signs and late strictures — attributed to WSES, ISG, WHO and Indian reviews.",
        keywords = listOf(
            "corrosive", "caustic", "acid", "alkali", "Harpic", "toilet cleaner", "bathroom cleaner", "phenyl", "lye",
            "tezab", "tezaab", "caustic soda", "sodium hydroxide", "hydrochloric acid", "sulphuric acid", "sulfuric acid",
            "battery acid", "drain cleaner", "bleach", "oesophageal burn", "esophageal burn", "oesophageal stricture",
            "esophageal stricture", "Zargar",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "First contact: what not to do",
                timing = "Pre-hospital and on arrival",
                points = listOf(
                    "WSES 2015: identify the agent and bring the container; if unclear, check pH (<2 or >10), though some agents injure independent of pH.",
                    "WSES 2015: establish intent, alcohol or drug co-ingestion, quantity (adult sip 30–50 mL vs gulp 60–90 mL) and time since ingestion.",
                    "WSES 2015: prevent vomiting and aspiration — antiemetics (metoclopramide) and a seated 45° position during transport.",
                    "WSES 2015: supportive care, not antidotes, is the mainstay of treatment.",
                ),
                checklistTitle = "Do not (as sources state)",
                checklist = listOf(
                    "Induce vomiting (WHO; WSES 2015)",
                    "Gastric lavage (WSES 2015)",
                    "Neutralise with a weak acid or alkali — exothermic reaction (WSES 2015)",
                    "Activated charcoal (WHO)",
                    "Nasogastric tube to prevent vomiting or stricture (WSES 2015: no proven benefit; reported to increase perforation, reflux and pneumonia)",
                    "Diluents such as milk or water (WSES 2015 — WHO advises the opposite; see notes)",
                ),
                citations = listOf(
                    cite(HH_WSES15, "Pre-hospital management (Level 4–5); Emergency department management (Level 5)"),
                    cite(HH_WHO, "§1.6.4 Corrosive compounds (p. 29)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Airway and resuscitation",
                timing = "Immediate",
                points = listOf(
                    "WSES 2015: secure airway and haemodynamics; if airway support is needed, prefer fibreoptic laryngoscopy over blind intubation; tracheotomy if necessary.",
                    "WHO (children): burns in or around the mouth or stridor suggest corrosive ingestion; call an anaesthetist to assess the airway.",
                    "WSES 2019: vapour aspiration (ammonia, formaldehyde) may cause airway burns; solids mainly injure the mouth and pharynx.",
                    "WSES 2015: give adequate pain relief while injury severity is evaluated.",
                ),
                citations = listOf(
                    cite(HH_WSES15, "Emergency department management (Level 5)"),
                    cite(HH_WHO, "§1.6 Diagnosis (p. 26); §1.6.1 (p. 27)"),
                    cite(HH_WSES19, "Corrosive ingestion — etiologies"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Laboratory assessment and expert advice",
                points = listOf(
                    "WSES 2019: CBC, sodium, potassium, chloride, magnesium, calcium, urea, creatinine, liver tests, pH, lactate, blood alcohol; β-hCG in young women.",
                    "WSES 2019: acidosis, high lactate, deranged liver tests, leukocytosis, high CRP, renal failure or thrombocytopenia predict transmural necrosis; normal initial values do not exclude it.",
                    "WSES 2019: watch for hypocalcaemia (phosphoric, hydrofluoric acids), hyponatraemia, hypokalaemia and acidosis.",
                    "WSES 2019: strong acids and alkalis cause most severe injuries; bleach usually causes mild injury, though severe damage has been reported.",
                    "WSES 2019: contact a poison control centre about systemic toxicity. AIIMS NPIC: 1800 116 117 (24 h).",
                ),
                citations = listOf(
                    cite(HH_WSES19, "Corrosive ingestion — etiologies (Grade 2A, 2B); investigations (Grade 2A)"),
                    cite(HH_NPIC, "NPIC home page"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Red flags: perforation and necrosis",
                timing = "Immediate surgical review if present",
                points = listOf(
                    "WSES 2015: peritonitis with haemodynamic instability requires immediate surgical exploration.",
                    "WSES 2019: transmural necrosis — operate as soon as possible (Grade 1C) and resect all obvious necrosis at the first operation (Grade 2A).",
                    "WSES 2019: absence of pain or oral lesions does not rule out life-threatening injury; neck, chest and abdominal X-rays may show free air.",
                    "WSES 2019: deterioration during non-operative care — repeat CT and consider surgery (Grade 2A).",
                    "IJCCM review: gastric or oesophageal perforation can occur any time in the first 2 weeks (most often days 3–12); " +
                        "worsening abdominal pain or new chest pain needs immediate radiological evaluation.",
                ),
                checklistTitle = "Red flags (WSES)",
                checklist = listOf(
                    "Peritonitis or rebound tenderness",
                    "Shock or haemodynamic instability",
                    "Increasing abdominal pain",
                    "Free air on neck, chest or abdominal X-ray",
                    "Need for ventilatory support",
                    "Rising acidosis, renal failure or leukocytosis",
                    "Chest pain, dysphagia, odynophagia, drooling or haemorrhage after deliberate ingestion (linked to severe injury)",
                ),
                citations = listOf(
                    cite(HH_WSES15, "Management algorithm (Level 4)"),
                    cite(HH_WSES19, "Investigations (Grade 3A); non-operative management (Grade 2A); surgical treatment (Grade 1C, 2A)"),
                    cite(HH_CORR, "Clinical features"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Imaging: contrast CT",
                timing = "CT 3–6 h after ingestion (WSES 2019)",
                points = listOf(
                    "WSES 2019: emergency management can rely on contrast CT of neck, thorax and abdomen 3–6 h after ingestion (Grade 2A).",
                    "WSES 2019: absent post-contrast wall enhancement indicates transmural necrosis and is an indication for emergency surgery.",
                    "WSES 2019 CT grades: I normal enhancement; IIa enhancing mucosa with thick hypodense wall ('target'); IIb only a thin outer rim enhances; III no enhancement.",
                    "WSES 2019: CT grade I — feed immediately, discharge in 24–48 h; IIa stricture risk <20%; IIb >80%.",
                    "ISG 2025: endoscopy is the preferred initial evaluation; contrast CT is reserved for specific situations.",
                ),
                citations = listOf(
                    cite(HH_WSES19, "Investigations (Grade 2A); Fig. 2; non-operative management"),
                    cite(HH_WSES15, "Management algorithm (Level 3)"),
                    cite(HH_ISG, "Abstract"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Endoscopy and Zargar grading",
                timing = "WSES 2015: usually 3–6 h; IJCCM review: within 48 h, avoid days 5–15",
                points = listOf(
                    "WSES 2019: endoscopy if CT is unavailable or contraindicated, CT findings are uncertain, or in children (Grade 2A).",
                    "WSES 2015: endoscopy usually 3–6 h after ingestion; Zargar ≤3a managed non-operatively, 3b considered for surgery.",
                    "IJCCM review (JIPMER): early endoscopy within 48 h; avoid days 5–15 because of perforation risk.",
                    "ISG 2025: Zargar ≤2a — early oral feeding and discharge; ≥2b — admit, as risk of short- and long-term complications is higher.",
                    "Zargar 1991 (PGIMER): grades 0, 1 and 2a recovered without sequelae; 71.4% of 2b and all grade 3 survivors developed scarring.",
                    "WSES 2019: endoscopy cannot reliably predict transmural necrosis; CT outperforms Zargar grading for stricture prediction.",
                ),
                checklistTitle = "Zargar grades (as summarised in the IJCCM review)",
                checklist = listOf(
                    "I — erythema and oedema only",
                    "IIa — haemorrhage, erosions, blisters, ulcers with exudate",
                    "IIb — circumferential oesophageal ulceration",
                    "IIIa — scattered deep ulcers with brown, black or grey discolouration",
                    "IIIb — extensive deep ulcers with brown, black or grey discolouration",
                    "IV — perforation",
                ),
                citations = listOf(
                    cite(HH_WSES19, "Role of endoscopy (Grade 2A)"),
                    cite(HH_WSES15, "Management algorithm (Level 4)"),
                    cite(HH_CORR, "Evaluation of the grade of injury"),
                    cite(HH_ISG, "Abstract"),
                    cite(HH_ZARGAR, "Abstract"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Steroids, antibiotics, acid suppression and feeding",
                points = listOf(
                    "WSES 2015: systemic steroids not recommended to prevent strictures; reserve for airway symptoms (Level 3). IJCCM review: steroids do not prevent strictures.",
                    "WSES 2015: no systematic broad-spectrum antibiotics; advised in grade 3 injury if steroids are started or the lungs are involved.",
                    "WSES 2015: benefit of PPIs and H2 blockers unproven. IJCCM review: IV PPIs are usually given in higher-grade injury despite unproven efficacy.",
                    "WSES 2015: use the gut early — low-grade injury resumes oral intake as soon as the patient can swallow.",
                    "WSES 2015: severe burns — jejunostomy or nasojejunal feeding rather than gastrostomy (possible hidden gastric outlet obstruction). ISG 2025: nutritional support throughout.",
                    "WSES 2019: oral feeding as soon as patients swallow normally; enteral feeding by nasogastric tube or jejunostomy if unable to eat.",
                ),
                citations = listOf(
                    cite(HH_WSES15, "Emergency department management (Level 3, 5); Nutritional approach (Level 5)"),
                    cite(HH_WSES19, "Non-operative management"),
                    cite(HH_CORR, "Initial resuscitation"),
                    cite(HH_ISG, "Abstract"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Strictures, follow-up and psychiatric review",
                timing = "Dilation 3–6 weeks after ingestion (WSES 2019)",
                points = listOf(
                    "WSES 2019: strictures usually form within 4 months; dysphagia or regurgitation needs immediate upper-GI evaluation.",
                    "WSES 2019: endoscopic dilation 3–6 weeks after ingestion for few (<3), short (<5 cm) strictures; reconstructive surgery after repeated failure (Grade 2A).",
                    "WSES 2019: review CT grade II injuries at 4–6 months. WSES 2015: gastric outlet obstruction can appear from days up to 6 years later.",
                    "WSES 2015: oesophageal bypass at least 6 months after ingestion or emergency surgery.",
                    "WSES 2019: psychiatric evaluation is mandatory for all patients before discharge (Grade 2C). NICE NG225: refer to liaison psychiatry after self-harm.",
                ),
                citations = listOf(
                    cite(HH_WSES19, "Role of endoscopy (Grade 2A); non-operative management (Grade 2C)"),
                    cite(HH_WSES15, "Endoscopic treatment of esophageal stricture; Gastric outlet obstruction; Esophageal reconstruction"),
                    cite(HH_NICE, "Rec 1.7.13"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Dilution differs: the WHO pocket book advises giving milk or water as soon as possible; WSES 2015 advises avoiding diluents; " +
                    "the IJCCM review says dilution has little role because injury occurs within minutes.",
                listOf(
                    cite(HH_WHO, "§1.6.4 Corrosive compounds (p. 29)"),
                    cite(HH_WSES15, "Pre-hospital management (Level 5)"),
                    cite(HH_CORR, "Initial resuscitation"),
                ),
            ),
            ProtocolNote(
                "CT versus endoscopy: WSES 2019 relies on contrast CT 3–6 h after ingestion, with endoscopy as back-up and first-line in children; " +
                    "ISG 2025 prefers endoscopy first and reserves CT for specific situations.",
                listOf(cite(HH_WSES19, "Investigations; role of endoscopy (Grade 2A)"), cite(HH_ISG, "Abstract")),
            ),
            ProtocolNote(
                "Endoscopy timing differs: WSES 2015 — usually 3–6 h after ingestion; IJCCM review — within 48 h, avoiding days 5–15.",
                listOf(cite(HH_WSES15, "Management algorithm"), cite(HH_CORR, "Evaluation of the grade of injury")),
            ),
            ProtocolNote(
                "Nasogastric tubes differ: WSES 2015 advises avoiding them, as their value in preventing vomiting or stricture is unproven; " +
                    "WSES 2019 recommends enteral feeding by nasogastric tube or jejunostomy in patients unable to eat; " +
                    "the IJCCM review calls early placement an option, decided case by case.",
                listOf(
                    cite(HH_WSES15, "Emergency department management (Level 5)"),
                    cite(HH_WSES19, "Non-operative management"),
                    cite(HH_CORR, "Prevention of stricture formation"),
                ),
            ),
            ProtocolNote(
                "ISG 2025 (41 modified-Delphi statements, Indian Society of Gastroenterology) was reviewed from its abstract only; consult the full guideline for statement-level detail.",
                listOf(cite(HH_ISG, "Abstract")),
            ),
        ),
        citations = listOf(
            cite(HH_WSES15), cite(HH_WSES19), cite(HH_ISG), cite(HH_ZARGAR), cite(HH_CORR), cite(HH_WHO), cite(HH_NPIC), cite(HH_NICE),
        ),
    ),

    // =============================================================================================
    // 3. Carbon monoxide
    // =============================================================================================
    Protocol(
        id = "carbon-monoxide",
        title = "Carbon monoxide poisoning",
        summary = "Recognition, 100% oxygen, COHb and pulse-oximetry pitfalls, cardiac testing, hyperbaric oxygen, pregnancy, " +
            "cyanide in fire smoke and delayed neurological sequelae — attributed to ACEP, ECHM, CDC, WHO and FDA labels.",
        keywords = listOf(
            "carbon monoxide", "CO", "CO poisoning", "angithi", "sigri", "coal stove", "heater", "room heater", "smoke",
            "smoke inhalation", "fire", "generator", "gas geyser", "charcoal burning", "carboxyhaemoglobin",
            "carboxyhemoglobin", "COHb", "hyperbaric oxygen", "HBO", "HBOT", "cyanide", "hydroxocobalamin", "Cyanokit",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Suspect CO",
                timing = "At triage",
                points = listOf(
                    "CDC: commonest symptoms — headache, dizziness, weakness, nausea, vomiting, chest pain and altered mental status.",
                    "ACEP 2025: ranges from flu-like illness to chest pain, dyspnoea, myocardial infarction, dysrhythmia, confusion and coma; flu-like cases in cold weather confound diagnosis.",
                    "CDC: also look for smoke inhalation, trauma, medical illness or intoxication.",
                    "CDC: others in the same environment may still be exposed — evaluate and test them.",
                ),
                checklistTitle = "Clues (CDC)",
                checklist = listOf(
                    "No fever with the symptoms",
                    "History of exposure — generator, stove, or other fuel- or charcoal-burning device",
                    "Several patients (e.g. one household) with similar complaints",
                ),
                citations = listOf(
                    cite(HH_CDC, "How to Recognize CO Poisoning"),
                    cite(HH_ACEP25, "Introduction"),
                    cite(HH_HAN, "Recommendations for clinicians"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "100% oxygen immediately",
                timing = "Immediately, before confirmation",
                points = listOf(
                    "ECHM 2016: give 100% oxygen immediately to anyone with CO poisoning as first aid (Type 1, Level C).",
                    "CDC: 100% oxygen until symptom-free (usually about 4–5 h) or until CO poisoning is ruled out.",
                    "ACEP 2025: COHb elimination half-life about 5 h on air, 85 min with a high-flow non-rebreather mask, 20 min with hyperbaric oxygen.",
                    "ECHM 2016: minor CO poisoning — 12 h of normobaric oxygen or HBOT is reasonable (Type 3, Level B).",
                    "WHO (children): give 100% oxygen until signs of hypoxia disappear.",
                ),
                citations = listOf(
                    cite(HH_ECHM, "Accepted indications — CO poisoning (Table 2)"),
                    cite(HH_CDC, "Management"),
                    cite(HH_HAN, "Recommendations for clinicians"),
                    cite(HH_ACEP25, "Introduction"),
                    cite(HH_WHO, "§1.6.4 Carbon monoxide (p. 33)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Confirm with COHb, not the pulse oximeter",
                points = listOf(
                    "CDC: measure COHb on venous or arterial blood by CO-oximetry whenever CO poisoning is suspected.",
                    "CDC: a conventional two-wavelength pulse oximeter is inaccurate when COHb is present. WHO: readings may be falsely high; the patient can look pink yet be hypoxaemic.",
                    "ACEP 2017 (Level B): do not use noninvasive pulse CO-oximetry to diagnose CO toxicity in suspected acute poisoning.",
                    "CDC: an elevated COHb of 2% in non-smokers or >9% in smokers strongly supports CO poisoning; interpret with time since exposure, as levels fall.",
                    "CDC: COHb correlates poorly with severity, outcome or response to treatment; sickle cell disease can raise COHb.",
                ),
                citations = listOf(
                    cite(HH_HAN, "Recommendations for clinicians"),
                    cite(HH_CDC, "Diagnosis; Confirmation of Diagnosis"),
                    cite(HH_ACEP17, "Critical question 1 (Level B)"),
                    cite(HH_WHO, "§1.6.4 Carbon monoxide (p. 33)"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Heart, brain and pregnancy test",
                points = listOf(
                    "ACEP 2017 (Level B): moderate–severe CO poisoning — ECG and cardiac biomarkers to identify myocardial injury, which predicts poor outcome.",
                    "CDC: cardiac injury during poisoning increases the risk of death within 10 years.",
                    "CDC: serial neurological examinations to assess progress and detect developing cerebral oedema.",
                    "CDC: pregnancy test in all women of childbearing age with suspected CO poisoning.",
                    "CDC: seriously poisoned patients, especially with loss of consciousness or cardiopulmonary signs — chest X-ray; head CT or brain MRI.",
                ),
                citations = listOf(
                    cite(HH_ACEP17, "Critical question 3 (Level B)"),
                    cite(HH_CDC, "Diagnosis; Management; Common questions"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Hyperbaric oxygen: who",
                timing = "Weigh severity and availability — distance and time (ACEP 2025)",
                points = listOf(
                    "ACEP 2025 (Level C only): in symptomatic CO poisoning, selected patients may benefit from HBO2, depending on severity and availability (distance, time).",
                    "ACEP 2025 harms: middle-ear barotrauma, oxygen-toxicity seizures, claustrophobia, deterioration during transport and long transfers.",
                    "ECHM 2016 (Type 1, Level B): HBOT for altered consciousness or any neurological, cardiac, respiratory or psychological signs, whatever the COHb.",
                    "CDC: consider HBO for COHb >25–30%, cardiac involvement, severe acidosis, any unconsciousness, neurological impairment or abnormal neuropsychiatric testing.",
                    "ECHM 2016: no HBOT for asymptomatic patients seen more than 24 h after exposure ended (Type 1, Level C).",
                    "CDC: decide on HBO with a toxicologist, hyperbaric facility or poison centre.",
                ),
                checklistTitle = "HBO criteria cited (ECHM, CDC)",
                checklist = listOf(
                    "Altered or lost consciousness, transient or prolonged",
                    "Neurological signs or impairment; abnormal neuropsychiatric testing",
                    "Cardiac involvement",
                    "Respiratory or psychological signs (ECHM)",
                    "Severe acidosis (CDC)",
                    "COHb >25–30% (CDC)",
                    "Pregnancy (ECHM, CDC)",
                ),
                citations = listOf(
                    cite(HH_ACEP25, "Critical question 1 — Level C; potential harms"),
                    cite(HH_ECHM, "Accepted indications — CO poisoning (Table 2)"),
                    cite(HH_CDC, "Management"),
                    cite(HH_HAN, "Recommendations for clinicians"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "Pregnancy",
                points = listOf(
                    "ECHM 2016 (Type 1, Level B): HBOT for CO-poisoned pregnant women whatever the clinical presentation or COHb.",
                    "CDC: hyperbaric oxygen is the treatment of choice in pregnancy, even if less severely poisoned.",
                    "ACEP 2025: pregnant patients and fetal exposures are outside the policy's scope.",
                ),
                citations = listOf(
                    cite(HH_ECHM, "Accepted indications — CO poisoning (Table 2)"),
                    cite(HH_CDC, "Management"),
                    cite(HH_ACEP25, "Scope — exclusion criteria"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Fire smoke: think cyanide",
                timing = "Do not delay the antidote to obtain lactate (FDA label)",
                points = listOf(
                    "FDA Cyanokit label: in smoke inhalation, assess for an enclosed-space fire, soot around mouth, nose or oropharynx, and altered mental status.",
                    "FDA Cyanokit label: lactate ≥10 mmol/L suggests cyanide in smoke inhalation; hypotension is suggestive but uncommon.",
                    "FDA Cyanokit label: adults 5 g IV over 15 min via a separate line; may repeat 5 g (total 10 g) over 15 min–2 h by severity.",
                    "FDA Cyanokit label: paediatric safety not established (70 mg/kg used outside the US); monitor blood pressure during treatment and renal function for 7 days.",
                    "FDA Cyanokit label: its deep red colour interferes with colorimetric lab tests and may shut down haemodialysis machines (false 'blood leak' alarm).",
                    "FDA Nithiodote label (boxed warning): sodium nitrite can cause life-threatening hypotension and methaemoglobin formation; consider alternative therapy in smoke-inhalation victims.",
                    "FDA Nithiodote label §5.4: use with caution in smoke inhalation or CO poisoning — methaemoglobin may worsen hypoxia.",
                ),
                citations = listOf(
                    cite(HH_CYANOKIT, "§2.1 Identifying patients (smoke inhalation); §2.2; §2.4; §5.3–5.5; §8.4"),
                    cite(HH_NITHIODOTE, "Boxed warning; §5.4 Smoke inhalation injury"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Disposition and delayed neurological sequelae",
                points = listOf(
                    "ACEP 2025: new neurological or psychiatric findings can appear 2–40 days after exposure (delayed neurological sequelae).",
                    "ACEP 2025: risk factors — age ≥36 years, COHb ≥25%, exposure ≥24 h, loss of consciousness, low GCS or MMSE, abnormal brain CT.",
                    "CDC: warn every discharged patient about delayed neurological complications; repeat medical and neurological examination at 2 weeks.",
                    "CDC: notify emergency services, fire department or police to investigate the source and advise when it is safe to return.",
                    "CDC: never use generators, stoves or charcoal-burning devices inside an enclosed space, or outside near an open window.",
                ),
                citations = listOf(
                    cite(HH_ACEP25, "Introduction"),
                    cite(HH_CDC, "Common questions"),
                    cite(HH_HAN, "Recommendations for clinicians"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Strength of HBO recommendations differs: ACEP 2025 makes only a Level C recommendation (no Level A or B); ECHM 2016 gives Type 1, Level B recommendations " +
                    "for HBOT in CO poisoning with altered consciousness or other clinical signs.",
                listOf(cite(HH_ACEP25, "Critical question 1"), cite(HH_ECHM, "Accepted indications — CO poisoning")),
            ),
            ProtocolNote(
                "CDC uses a COHb level (>25–30%) among its HBO considerations; ECHM recommends HBOT on clinical signs whatever the COHb, and CDC notes COHb correlates poorly with severity.",
                listOf(cite(HH_CDC, "Management; Diagnosis"), cite(HH_ECHM, "Accepted indications — CO poisoning")),
            ),
            ProtocolNote(
                "ACEP 2025 revisited only the hyperbaric question; its 2017 recommendations on pulse CO-oximetry and cardiac testing were to be reaffirmed in separate documents, not reviewed here.",
                listOf(cite(HH_ACEP25, "Introduction"), cite(HH_ACEP17, "Critical questions 1 and 3")),
            ),
        ),
        citations = listOf(
            cite(HH_ACEP25), cite(HH_ACEP17), cite(HH_ECHM), cite(HH_CDC), cite(HH_HAN), cite(HH_WHO), cite(HH_CYANOKIT), cite(HH_NITHIODOTE),
        ),
    ),

    // =============================================================================================
    // 4. Hydrocarbons
    // =============================================================================================
    Protocol(
        id = "hydrocarbons",
        title = "Hydrocarbon ingestion (kerosene, petrol)",
        summary = "Aspiration risk, no emesis, lavage or charcoal, oxygen, chest X-ray timing, at least 6 h observation, " +
            "and no routine steroids or antibiotics — attributed to WHO and Indian (PGIMER) sources.",
        keywords = listOf(
            "kerosene", "mitti ka tel", "mitti da tel", "petrol", "diesel", "gasoline", "turpentine", "thinner",
            "paraffin oil", "lamp oil", "hydrocarbon", "petroleum", "fuel siphoning", "aspiration pneumonitis",
            "chemical pneumonitis",
        ),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise aspiration risk",
                timing = "At presentation",
                points = listOf(
                    "IJCCM review (PGIMER): aspiration pneumonitis is the main toxicity — low viscosity, high volatility, low surface tension; even <1 mL aspirated can be serious.",
                    "IJCCM review: aspiration occurs with coughing or gagging at ingestion, or with vomiting; ingestion without aspiration does not cause chemical pneumonitis.",
                    "WHO (children): aspirated petroleum products can cause pulmonary oedema that takes hours to develop; ingestion can cause encephalopathy.",
                    "WHO (children): most pesticides are in petrol-based solvents — do not induce vomiting after pesticide ingestion either.",
                ),
                checklistTitle = "Signs of aspiration or toxicity (IJCCM review)",
                checklist = listOf(
                    "Coughing, choking or gagging at ingestion",
                    "Vomiting",
                    "Fast breathing, retractions, grunting",
                    "Wheeze or bronchospasm",
                    "Cyanosis or low SpO₂",
                    "Drowsiness, ataxia, convulsions or coma",
                ),
                citations = listOf(
                    cite(HH_KERO, "Kerosene — pathophysiology; clinical manifestations"),
                    cite(HH_WHO, "§1.6 (pp. 26–27); §1.6.1 (p. 27); §1.6.4 Petroleum compounds; Organophosphorus and carbamate compounds (p. 30)"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Decontamination: what not to do",
                timing = "First aid",
                points = listOf(
                    "IJCCM review: remove soiled clothing; wash contaminated skin or eyes. WHO (children): use soap and water for oily substances.",
                    "WHO (children): after kerosene or petrol ingestion, do not induce vomiting; give water or, if available, milk orally; call an anaesthetist for the airway.",
                    "IJCCM review: emesis and lavage are contraindicated — aspiration risk exceeds systemic absorption; charcoal does not bind kerosene and may induce vomiting.",
                    "WHO (children): petroleum ingestion is a contraindication to gastric decontamination; do not give charcoal.",
                ),
                checklistTitle = "Do not",
                checklist = listOf(
                    "Induce vomiting (WHO; IJCCM review)",
                    "Gastric lavage (WHO; IJCCM review)",
                    "Activated charcoal (WHO; IJCCM review)",
                    "Salt-water emetic (WHO: can be fatal)",
                ),
                citations = listOf(
                    cite(HH_KERO, "Treatment"),
                    cite(HH_WHO, "§1.6.1 (pp. 27–28); §1.6.2 Skin contamination (p. 29); §1.6.4 Petroleum compounds (p. 30)"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Oxygen and respiratory support",
                timing = "Immediately if symptomatic",
                points = listOf(
                    "WHO (children): oxygen therapy if there is respiratory distress.",
                    "IJCCM review: humidified oxygen and blood gas for grunting, tachypnoea or cyanosis; children with early respiratory compromise can deteriorate rapidly.",
                    "IJCCM review: nebulised β2-agonists for bronchospasm; restrict fluids to maintenance to limit pulmonary oedema.",
                    "IJCCM review: intubate early for hypoxaemia unresponsive to oxygen or severe CNS involvement; cuffed tube; lung-protective ventilation (6 mL/kg, plateau <30).",
                ),
                citations = listOf(
                    cite(HH_WHO, "§1.6.4 Petroleum compounds (p. 30)"),
                    cite(HH_KERO, "Treatment"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Chest X-ray",
                timing = "Changes usually within 6 h in symptomatic cases (IJCCM review)",
                points = listOf(
                    "IJCCM review: chest X-ray for every child with kerosene ingestion and respiratory symptoms.",
                    "IJCCM review: films may be normal at first; most symptomatic cases show changes within 6 h (range 20 min to 24 h).",
                    "IJCCM review: typical findings — increased bronchovascular markings, bibasal and perihilar infiltrates; pneumatoceles may appear at 2–3 weeks.",
                    "Anas 1981 (950 children): 800 who stayed asymptomatic through 6–8 h observation all had normal chest films and were treated as outpatients.",
                ),
                citations = listOf(
                    cite(HH_KERO, "Laboratory investigations"),
                    cite(HH_ANAS, "Abstract"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Observe at least 6 hours",
                timing = "Minimum 6 h observation (WHO; IJCCM review)",
                points = listOf(
                    "WHO (children): children who ingested petroleum products should not be sent home without at least 6 h observation.",
                    "IJCCM review: respiratory symptoms usually start within 1 h and almost always within 6 h; may worsen until 24–48 h, then improve.",
                    "IJCCM review: small ingestion with no aspiration symptoms (choking, coughing, vomiting, respiratory distress), normal saturation and no other features — " +
                        "discharge after 6 h observation with advice to return if symptoms develop.",
                    "IJCCM review: admit if symptomatic at presentation or within 6 h; intensive care for respiratory compromise or hypoxaemia.",
                    "Anas 1981: only children symptomatic at presentation or during 6–8 h observation needed hospital admission.",
                ),
                citations = listOf(
                    cite(HH_WHO, "§1.6 Diagnosis (pp. 26–27)"),
                    cite(HH_KERO, "Clinical manifestations; Disposition"),
                    cite(HH_ANAS, "Abstract"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "No routine steroids or antibiotics",
                points = listOf(
                    "IJCCM review: no clear role for corticosteroids or prophylactic antibiotics.",
                    "IJCCM review: antibiotics only with definite evidence of infection.",
                    "IJCCM review: fever occurs in up to 30% and settles within 24 h in three-quarters; fever beyond 48–72 h suggests bacterial infection.",
                ),
                citations = listOf(
                    cite(HH_KERO, "Clinical manifestations; Treatment"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Prevention and deliberate ingestion",
                points = listOf(
                    "IJCCM review: store kerosene out of children's reach, never in soft-drink or beverage bottles; label containers 'poison'.",
                    "WHO (children): teach parents to keep poisons in proper containers out of reach, and not to induce vomiting after kerosene or petrol.",
                    "NICE NG225: after deliberate ingestion, offer psychosocial assessment alongside physical care.",
                ),
                citations = listOf(
                    cite(HH_KERO, "Prevention"),
                    cite(HH_WHO, "§1.6.5 Prevention of poisoning (p. 33)"),
                    cite(HH_NICE, "Rec 1.7.3, 1.7.13"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "The sources here address children (WHO pocket book, IJCCM review from PGIMER Chandigarh, Anas 1981); no adult-specific guidance was identified.",
                listOf(cite(HH_WHO, "§1.6"), cite(HH_KERO, "Kerosene poisoning"), cite(HH_ANAS, "Abstract")),
            ),
            ProtocolNote(
                "The WHO pocket book suggests water or milk by mouth after kerosene or petrol ingestion; the IJCCM review does not mention oral dilution and notes that vomiting increases aspiration risk.",
                listOf(cite(HH_WHO, "§1.6.1 (p. 27)"), cite(HH_KERO, "Clinical manifestations; Treatment")),
            ),
        ),
        citations = listOf(cite(HH_WHO), cite(HH_KERO), cite(HH_ANAS), cite(HH_NICE)),
    ),
)
