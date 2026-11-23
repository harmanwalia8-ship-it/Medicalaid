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

internal val householdToxSources: List<Source> = listOf(
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

internal val householdToxProtocols: List<Protocol> = listOf(
    Protocol(
        id = "poisoning-general",
        title = "Poisoned patient: first steps",
        summary = "Resuscitation, toxidrome exam, bedside tests, charcoal, lavage don'ts, enhanced elimination, and psychosocial care for acute poisoning.",
        keywords = listOf("poisoning", "poisoned patient", "overdose", "OD", "self-poisoning", "deliberate self-harm", "DSH", "suicide attempt", "zeher", "zehar", "unknown poison", "toxidrome", "activated charcoal", "gastric lavage", "stomach wash", "ipecac", "decontamination", "whole bowel irrigation", "urine alkalinisation", "NPIC", "1800 116 117"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "Resuscitate first",
                timing = "At first contact",
                points = listOf(
                    "Prioritise airway, breathing, circulation, and staff safety; secure airway by rapid sequence intubation if reflexes lost or respiratory failure.",
                    "Check blood glucose immediately; if unmeasurable and consciousness is reduced, treat empirically as hypoglycaemia.",
                    "Keep unconscious children in the recovery position.",
                ),
                citations = listOf(cite("tx-hh-ijccm-initial-2019", "Airway"), cite("tx-hh-who-pocketbook-2013", "§1.6.1, pp. 27–28"), cite("tx-hh-ijccm-initial-2019", "Initial assessment; Airway")),
            ),
            Step(
                title = "History and poison-centre advice",
                points = listOf(
                    "Record agent, amount, and ingestion time; inspect containers, ask about traditional medicines, and check if other children were exposed.",
                    "After self-harm, establish the means used and discuss removing any remaining accessible lethal means.",
                    "Call AIIMS National Poisons Information Centre (24×7 toll-free 1800 116 117; landlines 011-26589391 / 26593677) for agent-specific guidance.",
                ),
                citations = listOf(cite("tx-hh-who-pocketbook-2013", "§1.6, p. 26"), cite("tx-hh-nice-ng225", "Rec 1.7.1"), cite("tx-hh-aiims-npic", "Home page")),
            ),
            Step(
                title = "Recognise the toxidrome",
                points = listOf(
                    "Assess vital signs, mental status, pupils, mucous membranes, lungs (wheeze/crackles), and skin (burns/moisture/colour).",
                    "Confirm unreliable history with toxidrome examination and tests; patients may not show every classic feature.",
                ),
                checklistTitle = "Toxidromes",
                checklist = listOf(
                    "Cholinergic — confusion/coma, miosis, salivation, lacrimation, sweating, vomiting, diarrhoea, bronchoconstriction, fasciculations, weakness, seizures (OPs, carbamates)",
                    "Anticholinergic — delirium, hallucinations, mydriasis, hyperthermia, tachycardia, dry flushed skin/mouth, ileus, urinary retention (antihistamines, TCA, atropine)",
                    "Opioid — CNS depression/coma, miosis, slow breathing/apnoea, hyporeflexia, pulmonary oedema, needle marks (morphine, heroin, methadone)",
                    "Sympathomimetic — agitation, paranoia, hallucinations, mydriasis, hyperthermia, tachycardia, hypertension, sweating, tremor, hyperreflexia, seizures (cocaine, amphetamines)",
                    "Sedative-hypnotic — confusion/stupor/coma, variable pupils, hypothermia, bradycardia, hypotension, slow breathing (benzodiazepines, barbiturates, alcohols)",
                    "Serotonin syndrome — agitation, mydriasis, hyperthermia, tachycardia, tremor, myoclonus, clonus, hyperreflexia, diarrhoea (SSRIs, MAOIs, TCA)",
                ),
                citations = listOf(cite("tx-hh-ncdc-chem-2025", "Toxidromes"), cite("tx-hh-ijccm-initial-2019", "Risk assessment; Table 1")),
            ),
            Step(
                title = "Bedside and laboratory tests",
                points = listOf(
                    "Check glucose, CBC, electrolytes, urea, creatinine, LFTs, lactate, ABG, 12-lead ECG (QRS/rate), and urine pregnancy test.",
                    "Measure serum paracetamol in all deliberate self-poisonings regardless of stated dose (nomogram applies to acute immediate-release ingestion only).",
                    "Order targeted levels where management changes (salicylate, iron, lithium, digoxin, toxic alcohols, COHb, methaemoglobin); avoid routine urine drug screens.",
                ),
                citations = listOf(cite("tx-hh-who-pocketbook-2013", "§1.6.1, p. 27"), cite("tx-hh-ijccm-initial-2019", "Diagnostic testing; Table 2–3"), cite("tx-hh-anz-paracetamol-2020", "Initial approach"), cite("tx-hh-ijccm-initial-2019", "Diagnostic testing")),
            ),
            Step(
                title = "Activated charcoal",
                timing = "Within 1 h (AACT/EAPCCT); up to 6 h for many poisons (CTRC)",
                points = listOf(
                    "Not routine; consider after risk assessment for charcoal-adsorbed poisons (AACT: ≤1 h; CTRC: up to 6 h, later if modified-release/bezoar).",
                    "Contraindicated without an intact or protected airway; do not intubate or insert an NG tube solely for charcoal.",
                    "Single dose in 8–10 volumes water: ≤1 y 1 g/kg; 1–12 y 25–50 g; adolescents/adults 25–100 g.",
                ),
                checklistTitle = "Charcoal not indicated",
                checklist = listOf(
                    "Airway not intact or protected",
                    "Corrosives (acids, alkalis, bleach)",
                    "Kerosene, petrol, and other petroleum products",
                    "Iron, lithium, ethanol, methanol, ethylene glycol, arsenic, lead, copper, caesium, metformin, metals",
                ),
                doses = listOf(
                    StepDose("Activated charcoal — child ≤1 year", WeightDose(1.0, 1.0, "g", "per single dose PO"), note = "Mix in 8–10 volumes water (e.g. 5 g in 40 mL); 1–12 y 25–50 g; ≥12 y 25–100 g"),
                ),
                citations = listOf(cite("tx-hh-aact-sdac-2005", "Abstract"), cite("tx-hh-ctrc-charcoal-2026", "Abstract"), cite("tx-hh-who-pocketbook-2013", "Table 5, pp. 27–28"), cite("tx-hh-who-pocketbook-2013", "§1.6.1, Table 5"), cite("tx-hh-ijccm-initial-2019", "Decontamination")),
            ),
            Step(
                title = "Gastric lavage and induced vomiting",
                points = listOf(
                    "Do not perform routine gastric lavage or give ipecac; consider lavage only for life-threatening ingestions <1 h by experienced staff.",
                    "Never use salt-water emetics — they can be fatal.",
                ),
                checklistTitle = "Do not",
                checklist = listOf(
                    "Salt-water emetic (can be fatal)",
                    "Induce vomiting after corrosive or kerosene/petrol ingestion",
                    "Gastric lavage after corrosives or petroleum products",
                    "Lavage or charcoal in an unconscious patient without a protected airway",
                ),
                citations = listOf(cite("tx-hh-aact-lavage-2013", "Abstract"), cite("tx-hh-aact-ipecac-2013", "Abstract"), cite("tx-hh-who-pocketbook-2013", "§1.6.1, pp. 27–28"), cite("tx-hh-who-pocketbook-2013", "§1.6.1, p. 27"), cite("tx-hh-ijccm-initial-2019", "Decontamination")),
            ),
            Step(
                title = "Enhanced elimination",
                points = listOf(
                    "Multi-dose charcoal (no cathartics): carbamazepine, dapsone, phenobarbital, theophylline (AACT adds quinine; CTRC adds cardiac glycosides, colchicine, phenytoin, thallium).",
                    "Consider whole-bowel irrigation for sustained-release/enteric-coated drugs, iron, lithium, potassium, or drug packets; avoid in obstruction, ileus, perforation, shock, unprotected airway.",
                    "Urine alkalinisation (salicylate, phenobarbital): NaHCO3 1–2 mEq/kg IV bolus, then 100 mEq/L D5W at 250 mL/h (urine pH 7.5–8.5).",
                    "Correct hypokalaemia before alkalinisation; contraindicated in fluid overload or renal impairment; dialyse toxins with Vd <1 L/kg.",
                ),
                doses = listOf(
                    StepDose("Multiple-dose activated charcoal — repeat dose", WeightDose(0.5, 0.5, "g", "per repeat dose PO"), note = "After initial standard dose; intact/protected airway only; no cathartics"),
                    StepDose("Sodium bicarbonate — urine alkalinisation bolus", WeightDose(1.0, 2.0, "mEq", "per IV bolus"), note = "Then 100 mEq in 1 L D5W at 250 mL/h (±20 mEq KCl); check urine pH q15–30 min (target 7.5–8.5)"),
                ),
                citations = listOf(cite("tx-hh-aact-mdac-1999", "Abstract"), cite("tx-hh-ctrc-charcoal-2026", "Abstract"), cite("tx-hh-aact-wbi-2015", "Abstract"), cite("tx-hh-ijccm-initial-2019", "Elimination")),
            ),
            Step(
                title = "Self-poisoning: psychosocial assessment and disposition",
                timing = "Alongside medical treatment, not after it",
                points = listOf(
                    "Refer to liaison psychiatry (crisis team for children) on arrival; assess alongside medical care without using suicide risk-prediction scales.",
                    "Admit deliberate paediatric iron, pesticide, paracetamol, aspirin, narcotic, or antidepressant ingestions; observe 4–24 h depending on poison.",
                    "Under MHCA 2017 s.115, suicide attempt is not punishable; provide care and share Tele-MANAS 24×7 toll-free helpline 14416.",
                ),
                citations = listOf(cite("tx-hh-nice-ng225", "Rec 1.5.2, 1.6.1–1.6.4, 1.7.3"), cite("tx-hh-who-pocketbook-2013", "§1.6.1, p. 28"), cite("tx-hh-mhca-2017", "§115"), cite("tx-hh-telemanas-2022", "Press release"), cite("tx-hh-nice-ng225", "Rec 1.5.2, 1.6.1–1.6.4, 1.7.3, 1.9.1"), cite("tx-hh-who-pocketbook-2013", "§1.6, §1.6.1")),
            ),
        ),
        notes = listOf(
            ProtocolNote("Charcoal window and route differ: AACT limits to ≤1 h; CTRC allows up to 6 h and advises against unintubated NG placement (WHO allows NG with verified placement).", listOf(cite("tx-hh-aact-sdac-2005", "Abstract"), cite("tx-hh-ctrc-charcoal-2026", "Abstract"), cite("tx-hh-who-pocketbook-2013", "§1.6.1, p. 27"))),
            ProtocolNote("NCDC 2025 module mentions 1–4 h lavage, salt emetics, and charcoal for alcohol, contradicting WHO (salt emetic fatal) and CTRC (no charcoal for ethanol).", listOf(cite("tx-hh-ncdc-chem-2025", "Fig. 16"), cite("tx-hh-who-pocketbook-2013", "§1.6.1, p. 27"), cite("tx-hh-ctrc-charcoal-2026", "Abstract"))),
        ),
        citations = listOf(cite("tx-hh-ijccm-initial-2019"), cite("tx-hh-who-pocketbook-2013"), cite("tx-hh-nice-ng225"), cite("tx-hh-aiims-npic"), cite("tx-hh-ncdc-chem-2025"), cite("tx-hh-anz-paracetamol-2020"), cite("tx-hh-aact-sdac-2005"), cite("tx-hh-ctrc-charcoal-2026"), cite("tx-hh-aact-lavage-2013"), cite("tx-hh-aact-ipecac-2013"), cite("tx-hh-aact-mdac-1999"), cite("tx-hh-aact-wbi-2015"), cite("tx-hh-mhca-2017"), cite("tx-hh-telemanas-2022")),
    ),
    Protocol(
        id = "corrosives",
        title = "Corrosive ingestion (acids, alkalis, toilet cleaners)",
        summary = "Airway protection, decontamination don'ts, perforation red flags, CT vs endoscopy grading, supportive care, and stricture follow-up.",
        keywords = listOf("corrosive", "caustic", "acid", "alkali", "Harpic", "toilet cleaner", "bathroom cleaner", "phenyl", "lye", "tezab", "tezaab", "caustic soda", "sodium hydroxide", "hydrochloric acid", "sulphuric acid", "battery acid", "drain cleaner", "bleach", "oesophageal burn", "oesophageal stricture", "Zargar"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "First contact: what not to do",
                timing = "Pre-hospital and on arrival",
                points = listOf(
                    "Identify agent/container (pH <2 or >10 high risk), intent, co-ingestions, time, volume (sip 30–50 mL; gulp 60–90 mL).",
                    "Prevent vomiting and aspiration using antiemetics (metoclopramide) and 45° seated transport; supportive care is mainstay.",
                ),
                checklistTitle = "Do not",
                checklist = listOf(
                    "Induce vomiting or perform gastric lavage",
                    "Neutralise with weak acid or alkali (exothermic reaction)",
                    "Give activated charcoal",
                    "Insert blind NG tube to prevent vomiting or stricture (perforation risk)",
                    "Give diluents (milk/water) — WSES advises against; WHO advises giving (see Note)",
                ),
                citations = listOf(cite("tx-hh-wses-caustic-2015", "Pre-hospital"), cite("tx-hh-wses-caustic-2015", "Pre-hospital; ED"), cite("tx-hh-who-pocketbook-2013", "§1.6.4, p. 29")),
            ),
            Step(
                title = "Airway and resuscitation",
                timing = "Immediate",
                points = listOf(
                    "Secure airway and haemodynamics; prefer fibreoptic laryngoscopy over blind intubation, or perform tracheotomy if necessary.",
                    "Call an anaesthetist urgently for oral burns, stridor, or vapour aspiration (ammonia, formaldehyde); give adequate analgesia.",
                ),
                citations = listOf(cite("tx-hh-wses-caustic-2015", "ED management"), cite("tx-hh-who-pocketbook-2013", "§1.6, p. 26"), cite("tx-hh-wses-esophageal-2019", "Etiologies"), cite("tx-hh-who-pocketbook-2013", "§1.6, §1.6.1")),
            ),
            Step(
                title = "Laboratory assessment and perforation red flags",
                timing = "Immediate surgical review if red flags present",
                points = listOf(
                    "Send CBC, Na+/K+/Cl-/Mg2+/Ca2+, urea, creatinine, LFTs, pH, lactate, alcohol, β-hCG; call AIIMS NPIC 1800 116 117.",
                    "Acidosis, high lactate, deranged LFTs, leukocytosis, high CRP, AKI, or thrombocytopenia predict necrosis; watch hypocalcaemia (phosphoric/hydrofluoric acid).",
                    "Absence of pain or oral burns does NOT exclude severe injury; operate immediately for peritonitis with shock or transmural necrosis.",
                    "Perforation can occur anytime in the first 2 weeks (peak days 3–12); repeat CT immediately if pain worsens.",
                ),
                checklistTitle = "Perforation & Necrosis Red Flags",
                checklist = listOf(
                    "Peritonitis, rebound tenderness, or shock/haemodynamic instability",
                    "Increasing abdominal pain or new chest pain",
                    "Free air on neck, chest, or abdominal X-ray",
                    "Need for ventilatory support",
                    "Rising acidosis, renal failure, or leukocytosis",
                    "Dysphagia, odynophagia, drooling, or haemorrhage after deliberate ingestion",
                ),
                citations = listOf(cite("tx-hh-wses-esophageal-2019", "Investigations"), cite("tx-hh-aiims-npic", "Home page"), cite("tx-hh-wses-caustic-2015", "Algorithm"), cite("tx-hh-wses-esophageal-2019", "Surgical treatment"), cite("tx-hh-ijccm-corrosive-2019", "Clinical features"), cite("tx-hh-wses-esophageal-2019", "Non-operative"), cite("tx-hh-wses-esophageal-2019", "Investigations; Surgical treatment")),
            ),
            Step(
                title = "Contrast CT and endoscopy (Zargar grading)",
                timing = "CT 3–6 h; endoscopy 3–6 h (or ≤48 h; avoid days 5–15)",
                points = listOf(
                    "WSES: contrast CT neck/chest/abdomen at 3–6 h (no wall enhancement = grade III necrosis → surgery); ISG: endoscopy first.",
                    "CT grade I: feed immediately, discharge 24–48 h; IIa stricture risk <20%; IIb >80%; III surgery.",
                    "Perform endoscopy if CT unavailable/uncertain or in children; do within 48 h and avoid days 5–15 (perforation risk).",
                    "Zargar ≤2a: early oral feeding and discharge; ≥2b: admit (71.4% of 2b and all grade 3 scar); 3b consider surgery.",
                ),
                checklistTitle = "Zargar Endoscopic Grades",
                checklist = listOf(
                    "I — erythema and oedema only",
                    "IIa — haemorrhage, erosions, blisters, ulcers with exudate",
                    "IIb — circumferential oesophageal ulceration",
                    "IIIa — scattered deep ulcers with brown, black, or grey discolouration",
                    "IIIb — extensive deep ulcers with brown, black, or grey discolouration",
                    "IV — perforation",
                ),
                citations = listOf(cite("tx-hh-wses-esophageal-2019", "Investigations; Fig. 2"), cite("tx-hh-isg-caustic-2025", "Abstract"), cite("tx-hh-wses-esophageal-2019", "Fig. 2; Non-operative"), cite("tx-hh-wses-esophageal-2019", "Endoscopy"), cite("tx-hh-ijccm-corrosive-2019", "Evaluation"), cite("tx-hh-zargar-1991", "Abstract"), cite("tx-hh-wses-caustic-2015", "Algorithm"), cite("tx-hh-wses-esophageal-2019", "Investigations; Endoscopy; Fig. 2")),
            ),
            Step(
                title = "Supportive care, strictures and psychiatric review",
                timing = "Dilation 3–6 weeks after ingestion",
                points = listOf(
                    "Do not give routine steroids to prevent strictures (airway symptoms only) or routine antibiotics (only grade 3 with steroids/lung involvement).",
                    "PPI benefit is unproven though often given in higher grades; resume oral intake early in low-grade injury.",
                    "For severe burns unable to eat, use jejunostomy or nasojejunal feeding (avoid gastrostomy due to gastric outlet obstruction).",
                    "Strictures (≤4 mo): dilate <3 short (<5 cm) at 3–6 weeks; bypass ≥6 months; review grade II at 4–6 months.",
                    "Mandatory psychiatric review before discharge after self-harm (gastric outlet obstruction can appear days to 6 years later).",
                ),
                citations = listOf(cite("tx-hh-wses-caustic-2015", "ED management"), cite("tx-hh-wses-caustic-2015", "ED; Nutrition"), cite("tx-hh-ijccm-corrosive-2019", "Resuscitation"), cite("tx-hh-wses-caustic-2015", "Nutrition"), cite("tx-hh-wses-esophageal-2019", "Endoscopy"), cite("tx-hh-wses-caustic-2015", "Stricture; Reconstruction"), cite("tx-hh-wses-esophageal-2019", "Non-operative"), cite("tx-hh-nice-ng225", "Rec 1.7.13"), cite("tx-hh-wses-caustic-2015", "GOO"), cite("tx-hh-wses-caustic-2015", "ED; Nutrition; Stricture; GOO; Reconstruction"), cite("tx-hh-wses-esophageal-2019", "Non-operative; Endoscopy")),
            ),
        ),
        notes = listOf(
            ProtocolNote("Dilution differs: WHO advises milk or water promptly; WSES advises against diluents; IJCCM notes injury occurs within minutes.", listOf(cite("tx-hh-who-pocketbook-2013", "§1.6.4, p. 29"), cite("tx-hh-wses-caustic-2015", "Pre-hospital"), cite("tx-hh-ijccm-corrosive-2019", "Resuscitation"))),
            ProtocolNote("Initial imaging differs: WSES relies on contrast CT at 3–6 h first (endoscopy in children); ISG prefers endoscopy first.", listOf(cite("tx-hh-wses-esophageal-2019", "Investigations"), cite("tx-hh-isg-caustic-2025", "Abstract"))),
            ProtocolNote("NG tubes: WSES 2015 advises against prophylactic NG placement; WSES 2019 allows NG or jejunostomy for enteral feeding when unable to eat.", listOf(cite("tx-hh-wses-caustic-2015", "ED management"), cite("tx-hh-wses-esophageal-2019", "Non-operative"))),
        ),
        citations = listOf(cite("tx-hh-wses-caustic-2015"), cite("tx-hh-who-pocketbook-2013"), cite("tx-hh-wses-esophageal-2019"), cite("tx-hh-aiims-npic"), cite("tx-hh-ijccm-corrosive-2019"), cite("tx-hh-isg-caustic-2025"), cite("tx-hh-zargar-1991"), cite("tx-hh-nice-ng225")),
    ),
    Protocol(
        id = "carbon-monoxide",
        title = "Carbon monoxide poisoning",
        summary = "Immediate 100% oxygen, blood COHb confirmation, cardiac workup, hyperbaric oxygen criteria, pregnancy, fire-smoke cyanide, and delayed sequelae.",
        keywords = listOf("carbon monoxide", "CO", "CO poisoning", "angithi", "sigri", "coal stove", "room heater", "smoke inhalation", "fire", "generator", "gas geyser", "COHb", "hyperbaric oxygen", "HBO", "HBOT", "cyanide", "hydroxocobalamin", "Cyanokit"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "Suspect CO and give 100% oxygen immediately",
                timing = "Immediately, before lab confirmation",
                points = listOf(
                    "Suspect CO with headache, dizziness, nausea, chest pain, or altered mentation without fever; test others exposed in same household.",
                    "Give 100% high-flow oxygen immediately until symptom-free (usually 4–5 h; minor cases up to 12 h) or CO ruled out.",
                    "COHb half-life drops from ~5 h on air to 85 min on high-flow non-rebreather and 20 min with hyperbaric oxygen.",
                ),
                checklistTitle = "Clues to CO Poisoning",
                checklist = listOf(
                    "No fever with flu-like or neurological symptoms",
                    "Exposure to generator, angithi, coal stove, gas geyser, or room heater",
                    "Multiple household members with similar complaints",
                ),
                citations = listOf(cite("tx-hh-cdc-co-guidance", "Recognition"), cite("tx-hh-cdc-han-00476", "Recommendations"), cite("tx-hh-echm-2016", "Table 2"), cite("tx-hh-cdc-co-guidance", "Management"), cite("tx-hh-who-pocketbook-2013", "§1.6.4, p. 33"), cite("tx-hh-acep-co-2025", "Introduction"), cite("tx-hh-cdc-co-guidance", "Recognition; Management")),
            ),
            Step(
                title = "Confirm COHb and screen heart, brain, pregnancy",
                points = listOf(
                    "Measure blood COHb by venous or arterial CO-oximetry; never rely on conventional pulse oximetry or noninvasive pulse CO-oximetry.",
                    "COHb >2% (non-smokers) or >9% (smokers) supports diagnosis, though levels fall over time and correlate poorly with clinical severity.",
                    "Obtain ECG and cardiac biomarkers in moderate–severe poisoning (myocardial injury increases 10-year mortality) and pregnancy test in childbearing women.",
                    "Perform serial neurological exams; obtain CXR and head CT/MRI in loss of consciousness or cardiopulmonary signs.",
                ),
                citations = listOf(cite("tx-hh-cdc-co-guidance", "Diagnosis"), cite("tx-hh-acep-co-2017", "Critical Q1"), cite("tx-hh-who-pocketbook-2013", "§1.6.4, p. 33"), cite("tx-hh-cdc-co-guidance", "Confirmation"), cite("tx-hh-acep-co-2017", "Critical Q3"), cite("tx-hh-cdc-co-guidance", "Diagnosis; Management"), cite("tx-hh-cdc-co-guidance", "Diagnosis; Confirmation; Management"), cite("tx-hh-acep-co-2017", "Critical Q1, Q3")),
            ),
            Step(
                title = "Hyperbaric oxygen (HBOT) and pregnancy",
                timing = "Weigh severity against transfer distance and time",
                points = listOf(
                    "Consider HBOT with a toxicologist/hyperbaric centre for severe symptoms, weighing transport risks, barotrauma, and oxygen-toxicity seizures.",
                    "In pregnancy, HBOT is recommended regardless of COHb or milder symptoms.",
                    "Do not give HBOT to asymptomatic patients presenting >24 hours after exposure ended.",
                ),
                checklistTitle = "HBOT Indications",
                checklist = listOf(
                    "Altered or lost consciousness (transient or prolonged)",
                    "Neurological, respiratory, or psychological signs; abnormal neuropsychiatric testing",
                    "Cardiac involvement or severe acidosis",
                    "COHb >25–30%",
                    "Pregnancy (regardless of presentation or COHb)",
                ),
                citations = listOf(cite("tx-hh-acep-co-2025", "Critical Q1"), cite("tx-hh-cdc-co-guidance", "Management"), cite("tx-hh-echm-2016", "Table 2")),
            ),
            Step(
                title = "Fire smoke: think cyanide",
                timing = "Do not delay antidote awaiting lactate",
                points = listOf(
                    "Suspect cyanide in enclosed-space fire with soot around mouth/nose/oropharynx and altered mentation; lactate ≥10 mmol/L strongly supports cyanide.",
                    "Hydroxocobalamin 5 g IV/15 min (separate line); repeat 5 g over 15 min–2 h (max 10 g; child 70 mg/kg).",
                    "Monitor BP during infusion and renal function for 7 days; red colour interferes with colorimetric labs and dialysis alarms.",
                    "Avoid sodium nitrite in smoke inhalation/CO poisoning — causes hypotension and methaemoglobinaemia, worsening hypoxia.",
                ),
                citations = listOf(cite("tx-hh-fda-cyanokit", "§2.1"), cite("tx-hh-fda-cyanokit", "§2.2, §8.4"), cite("tx-hh-fda-cyanokit", "§5.3–5.5"), cite("tx-hh-fda-nithiodote", "Boxed warning; §5.4"), cite("tx-hh-fda-cyanokit", "§2.1–2.2, §5.3–5.5, §8.4")),
            ),
            Step(
                title = "Disposition and delayed neurological sequelae",
                points = listOf(
                    "Delayed neuropsychiatric sequelae can emerge 2–40 days post-exposure (risks: age ≥36 y, COHb ≥25%, exposure ≥24 h, LOC, abnormal CT).",
                    "Warn every discharged patient and arrange repeat medical and neurological examination at 2 weeks.",
                    "Notify fire/emergency services to inspect the source before return; forbid indoor generators, stoves, or charcoal heaters.",
                ),
                citations = listOf(cite("tx-hh-acep-co-2025", "Introduction"), cite("tx-hh-cdc-co-guidance", "Common questions")),
            ),
        ),
        notes = listOf(
            ProtocolNote("HBOT strength differs: ACEP 2025 gives only a conditional option weighing transfer harms, whereas ECHM strongly recommends HBOT for neurological/cardiac signs or pregnancy.", listOf(cite("tx-hh-acep-co-2025", "Critical Q1"), cite("tx-hh-echm-2016", "Table 2"))),
            ProtocolNote("CDC lists COHb >25–30% as an HBOT consideration, while ECHM bases HBOT on clinical signs regardless of COHb.", listOf(cite("tx-hh-cdc-co-guidance", "Management"), cite("tx-hh-echm-2016", "Table 2"))),
        ),
        citations = listOf(cite("tx-hh-cdc-co-guidance"), cite("tx-hh-cdc-han-00476"), cite("tx-hh-echm-2016"), cite("tx-hh-who-pocketbook-2013"), cite("tx-hh-acep-co-2025"), cite("tx-hh-acep-co-2017"), cite("tx-hh-fda-cyanokit"), cite("tx-hh-fda-nithiodote")),
    ),
    Protocol(
        id = "hydrocarbons",
        title = "Hydrocarbon ingestion (kerosene, petrol)",
        summary = "Aspiration pneumonitis recognition, decontamination don'ts, respiratory support, chest X-ray timing, 6-hour observation, and avoiding routine steroids/antibiotics.",
        keywords = listOf("kerosene", "mitti ka tel", "mitti da tel", "petrol", "diesel", "gasoline", "turpentine", "thinner", "paraffin oil", "lamp oil", "hydrocarbon", "petroleum", "aspiration pneumonitis", "chemical pneumonitis"),
        category = ProtocolCategory.Poisoning,
        steps = listOf(
            Step(
                title = "Recognise aspiration risk and decontamination don'ts",
                timing = "At presentation",
                points = listOf(
                    "Aspiration of even <1 mL causes severe chemical pneumonitis and delayed pulmonary oedema; ingestion can also cause encephalopathy.",
                    "Remove soiled clothing and wash contaminated skin or eyes with soap and water.",
                    "Do NOT induce vomiting, lavage, or give charcoal (aspiration risk exceeds systemic absorption; same for petrol-solvent pesticides).",
                ),
                checklistTitle = "Signs of Aspiration & Strict Don'ts",
                checklist = listOf(
                    "Signs: coughing/choking/gagging, vomiting, tachypnoea, retractions, grunting, wheeze, cyanosis, low SpO2, drowsiness/seizures",
                    "Do NOT induce vomiting or use salt-water emetic",
                    "Do NOT perform gastric lavage",
                    "Do NOT give activated charcoal",
                ),
                citations = listOf(cite("tx-hh-ijccm-kerosene-2019", "Pathophysiology"), cite("tx-hh-who-pocketbook-2013", "§1.6.4, p. 30"), cite("tx-hh-ijccm-kerosene-2019", "Treatment"), cite("tx-hh-who-pocketbook-2013", "§1.6.2, p. 29"), cite("tx-hh-who-pocketbook-2013", "§1.6.1, §1.6.4"), cite("tx-hh-ijccm-kerosene-2019", "Pathophysiology; Treatment"), cite("tx-hh-who-pocketbook-2013", "§1.6.1–1.6.2, §1.6.4")),
            ),
            Step(
                title = "Oxygen, respiratory support and chest X-ray",
                timing = "CXR changes usually appear within 6 h (range 20 min–24 h)",
                points = listOf(
                    "Give humidified oxygen for respiratory distress/cyanosis, nebulised β2-agonists for bronchospasm, and restrict fluids to maintenance.",
                    "Intubate early with a cuffed tube for refractory hypoxaemia or severe CNS depression; ventilate lung-protectively (6 mL/kg, plateau <30).",
                    "Obtain chest X-ray for all symptomatic cases (bibasal/perihilar infiltrates usually appear within 6 h; pneumatoceles at 2–3 weeks).",
                ),
                citations = listOf(cite("tx-hh-who-pocketbook-2013", "§1.6.4, p. 30"), cite("tx-hh-ijccm-kerosene-2019", "Treatment"), cite("tx-hh-ijccm-kerosene-2019", "Investigations"), cite("tx-hh-ijccm-kerosene-2019", "Treatment; Investigations")),
            ),
            Step(
                title = "Observe at least 6 hours",
                timing = "Minimum 6 h observation before discharge",
                points = listOf(
                    "Observe every child for at least 6 hours; symptoms almost always start within 6 h and peak at 24–48 h.",
                    "Discharge after 6–8 hours only if completely asymptomatic with normal saturation and return precautions; admit if any symptoms develop.",
                ),
                citations = listOf(cite("tx-hh-who-pocketbook-2013", "§1.6, pp. 26–27"), cite("tx-hh-ijccm-kerosene-2019", "Disposition"), cite("tx-hh-anas-1981", "Abstract")),
            ),
            Step(
                title = "No routine steroids or antibiotics; prevention",
                points = listOf(
                    "Do not give routine corticosteroids or prophylactic antibiotics; early fever (≤30%) usually settles within 24 h.",
                    "Suspect bacterial superinfection and start antibiotics only if fever persists beyond 48–72 h or definite infection signs appear.",
                    "Counsel against storing kerosene in beverage bottles; arrange psychosocial assessment for deliberate ingestions.",
                ),
                citations = listOf(cite("tx-hh-ijccm-kerosene-2019", "Treatment"), cite("tx-hh-ijccm-kerosene-2019", "Clinical manifestations; Treatment"), cite("tx-hh-ijccm-kerosene-2019", "Prevention"), cite("tx-hh-who-pocketbook-2013", "§1.6.5, p. 33"), cite("tx-hh-nice-ng225", "Rec 1.7.3"), cite("tx-hh-ijccm-kerosene-2019", "Clinical manifestations; Treatment; Prevention")),
            ),
        ),
        notes = listOf(
            ProtocolNote("WHO suggests giving water or milk orally after petroleum ingestion, whereas IJCCM omits oral dilution because vomiting increases aspiration risk.", listOf(cite("tx-hh-who-pocketbook-2013", "§1.6.1, p. 27"), cite("tx-hh-ijccm-kerosene-2019", "Treatment"))),
        ),
        citations = listOf(cite("tx-hh-ijccm-kerosene-2019"), cite("tx-hh-who-pocketbook-2013"), cite("tx-hh-anas-1981"), cite("tx-hh-nice-ng225")),
    ),
)
