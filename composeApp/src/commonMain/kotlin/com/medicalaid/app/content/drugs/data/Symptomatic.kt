package com.medicalaid.app.content.drugs.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.drugs.Clearance
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.Facts
import com.medicalaid.app.content.drugs.Pharmacokinetics

/*
 * Symptomatic group: antiemetics, antihistamines, analgesics, antimigraine, corticosteroid,
 * magnesium sulphate. Same rules as Metabolic.kt: one FDA label per drug, section-level citations,
 * brief paraphrase of the cited section only. Older (non-PLR) labels are cited by their
 * unnumbered section names.
 */

internal val symptomaticSources = listOf(
    Source(
        "fda-label-doxylamine-pyridoxine", Authority.FDA, "Drug label (DailyMed)",
        "Doxylamine succinate and pyridoxine hydrochloride delayed-release tablets — Par Health USA", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=de265fd1-bc22-4da1-aab5-b5fedd649c8b",
        status = "Revised Aug 2026",
    ),
    Source(
        "fda-label-ondansetron", Authority.FDA, "Drug label (DailyMed)",
        "Ondansetron tablets — Ipca Laboratories", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=7ab3210e-eed1-479a-afc0-f72b5b776883",
        status = "Revised Jul 2026",
    ),
    Source(
        "fda-label-metoclopramide", Authority.FDA, "Drug label (DailyMed)",
        "Metoclopramide tablets — Ipca Laboratories", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=c143e210-4ce7-44e5-b1e2-bf0e6bfde485",
        status = "Revised Jul 2026",
    ),
    Source(
        "fda-label-promethazine", Authority.FDA, "Drug label (DailyMed)",
        "Promethazine hydrochloride tablets — KVK-Tech", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=7a137adf-39b7-4b14-80da-c3cac9ad562a",
        status = "Revised Mar 2026",
    ),
    Source(
        "fda-label-prochlorperazine", Authority.FDA, "Drug label (DailyMed)",
        "Prochlorperazine maleate tablets — ANI Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=50ce4381-94ef-49ff-8794-cfde0ee803b4",
        status = "Revised May 2026",
    ),
    Source(
        "fda-label-diphenhydramine", Authority.FDA, "Drug label (DailyMed)",
        "Diphenhydramine hydrochloride injection — Micro Labs", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=a1110433-498a-48cb-ac79-5732fd4f8fba",
        status = "Revised Mar 2026",
    ),
    Source(
        "fda-label-chlorpromazine", Authority.FDA, "Drug label (DailyMed)",
        "Chlorpromazine hydrochloride tablets — Aurobindo Pharma", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=c2a8b8a3-187f-4000-bde3-b40bb0e5600f",
        status = "Revised May 2026",
    ),
    Source(
        "fda-label-paracetamol", Authority.FDA, "Drug label (DailyMed)",
        "Acetaminophen injection — Somerset Therapeutics", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8481d9f3-d2a5-457f-b92c-99445d063c8f",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-ibuprofen", Authority.FDA, "Drug label (DailyMed)",
        "Ibuprofen tablets — Granules India", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8804564c-9e1b-43c1-8b58-fd6ace645f00",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-sumatriptan", Authority.FDA, "Drug label (DailyMed)",
        "Sumatriptan tablets — Mylan Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=1d2cd01c-05e7-44cf-a71d-0f474e826b7e",
        status = "Revised Aug 2026",
    ),
    Source(
        "fda-label-methylprednisolone", Authority.FDA, "Drug label (DailyMed)",
        "Methylprednisolone tablets — Mylan Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=22dd75d6-f89a-44f7-8ca6-b64c4e9806dd",
        status = "Revised Jun 2026",
    ),
    Source(
        "fda-label-magnesium-sulphate", Authority.FDA, "Drug label (DailyMed)",
        "Magnesium sulfate in 5% dextrose injection — WG Critical Care", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8da9c33f-e3cd-4026-b034-2b964401b346",
        status = "Revised Sep 2026",
    ),
)

private const val DOX = "fda-label-doxylamine-pyridoxine"
private const val OND = "fda-label-ondansetron"
private const val MCP = "fda-label-metoclopramide"
private const val PMZ = "fda-label-promethazine"
private const val PCZ = "fda-label-prochlorperazine"
private const val DPH = "fda-label-diphenhydramine"
private const val CPZ = "fda-label-chlorpromazine"
private const val PCM = "fda-label-paracetamol"
private const val IBU = "fda-label-ibuprofen"
private const val SUM = "fda-label-sumatriptan"
private const val MPS = "fda-label-methylprednisolone"
private const val MGS = "fda-label-magnesium-sulphate"

internal val symptomaticDrugs = listOf(
    // 1. Doxylamine–pyridoxine -------------------------------------------------------------------
    DrugMonograph(
        id = "doxylamine-pyridoxine",
        name = "Doxylamine–pyridoxine",
        aliases = listOf("doxylamine succinate and pyridoxine hydrochloride"),
        drugClass = "Antihistamine + vitamin B6 analogue (fixed-dose combination)",
        uses = "Nausea and vomiting of pregnancy not responding to conservative management (not studied in hyperemesis gravidarum).",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Delayed-release 10 mg/10 mg tablets: start 2 tablets at bedtime (Day 1).",
                "If symptoms persist into the afternoon of Day 2: 3 tablets from Day 3 (1 morning, 2 bedtime); if still uncontrolled, 4 tablets from Day 4 (1 morning, 1 mid-afternoon, 2 bedtime). Max 4 tablets/day.",
                "Take daily (not as needed) on an empty stomach with water; swallow whole — do not crush, chew or split.",
            ),
            listOf(cite(DOX, "2.1 Dosage Information")),
        ),
        monitoring = Facts(
            listOf(
                "Reassess the continued need for treatment as pregnancy progresses.",
                "Positive urine immunoassay for methadone, opiates or PCP: confirm with GC-MS (false positives occur).",
            ),
            listOf(cite(DOX, "2.1; 7.3")),
        ),
        commonEffects = Facts(
            listOf("Somnolence (14.3% vs 11.7% placebo) — the only reaction ≥5% and above placebo"),
            listOf(cite(DOX, "6.1 Clinical Trial Experience — Table 1")),
        ),
        seriousEffects = Facts(
            listOf(
                "Somnolence: avoid driving or machinery until cleared; with alcohol or other CNS depressants, severe drowsiness can cause falls or accidents.",
                "Anticholinergic effects: caution in asthma, raised intraocular pressure, narrow-angle glaucoma, stenosing peptic ulcer, pyloroduodenal or bladder-neck obstruction.",
                "False-positive urine screens for methadone, opiates and PCP.",
            ),
            listOf(cite(DOX, "5.1–5.3 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Absorbed mainly in the jejunum; Cmax within 7.5 h (doxylamine) and 5.5 h (pyridoxine). Food delays absorption and lowers pyridoxine Cmax/AUC by ~50%.",
            distribution = "Pyridoxine highly protein bound (albumin); its active metabolite PLP is ≥60% of circulating vitamin B6.",
            metabolism = "Doxylamine: hepatic N-dealkylation to N-desmethyl and N,N-didesmethyl metabolites. Pyridoxine: prodrug metabolised in the liver.",
            elimination = "Doxylamine metabolites excreted by the kidney.",
            halfLife = "Doxylamine ≈12.5 h; pyridoxine ≈0.5 h.",
            citations = listOf(cite(DOX, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "MAO inhibitors: contraindicated — intensify and prolong anticholinergic and CNS effects.",
                "Alcohol, hypnotics, sedatives, tranquilisers: concurrent use not recommended — severe drowsiness.",
                "Food: further delays onset and may reduce absorption — take on an empty stomach.",
            ),
            listOf(cite(DOX, "4 Contraindications; 7.1–7.2 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Intended for use in pregnancy; epidemiologic studies and meta-analyses of first-trimester exposure have not shown an increased risk of congenital malformations."),
            listOf(cite(DOX, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Do not breastfeed while taking it: doxylamine is expected in milk; excitement, irritability and sedation reported in infants, and infants with apnoea may be especially vulnerable."),
            listOf(cite(DOX, "8.3 Lactation")),
        ),
    ),

    // 2. Ondansetron -----------------------------------------------------------------------------
    DrugMonograph(
        id = "ondansetron",
        name = "Ondansetron",
        drugClass = "5-HT3 receptor antagonist",
        uses = "Prevention of nausea and vomiting from highly or moderately emetogenic chemotherapy, radiotherapy, and surgery (postoperative).",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Highly emetogenic chemotherapy: single 24 mg dose 30 min before chemotherapy (24 mg/day is the maximum recommended oral dose).",
                "Moderately emetogenic chemotherapy: 8 mg 30 min before, 8 mg 8 h later, then 8 mg every 12 h for 1–2 days after chemotherapy.",
                "Postoperative: 16 mg 1 h before induction of anaesthesia. Radiotherapy: 8 mg 1–2 h before each fraction (repeat every 8 h for abdominal regimens).",
            ),
            listOf(cite(OND, "2.1 Recommended Dosage — Table 1; 8.1")),
        ),
        monitoring = Facts(
            listOf(
                "ECG if hypokalaemia or hypomagnesaemia, heart failure, bradyarrhythmias, or other QT-prolonging drugs.",
                "Serotonin syndrome, especially with other serotonergic drugs.",
                "Signs of myocardial ischaemia after dosing.",
                "Bowel activity after abdominal surgery or in CINV if at risk of GI obstruction (may mask ileus).",
            ),
            listOf(cite(OND, "5.2–5.5 Warnings and Precautions")),
        ),
        renal = Facts(
            listOf("No dosage adjustment for any degree of renal impairment."),
            listOf(cite(OND, "8.7 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf(
                "Severe impairment (Child-Pugh ≥10): do not exceed 8 mg total daily dose.",
                "Mild or moderate impairment: no adjustment needed.",
            ),
            listOf(cite(OND, "2.2 Dosage in Hepatic Impairment; 8.6")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache (24% vs 13% placebo, moderately emetogenic chemotherapy; 11% with 24 mg)",
                "Malaise / fatigue (13%)",
                "Constipation (9%)",
                "Diarrhoea (6%)",
                "Postoperative (16 mg): hypoxia (9%), pyrexia (8%), dizziness (7%)",
            ),
            listOf(cite(OND, "6.1 Clinical Trials Experience — Tables 3–4")),
        ),
        seriousEffects = Facts(
            listOf(
                "Hypersensitivity, including anaphylaxis and bronchospasm — discontinue.",
                "QT prolongation and torsade de pointes — avoid in congenital long QT syndrome.",
                "Serotonin syndrome, mainly with serotonergic drugs; some fatal.",
                "Myocardial ischaemia (coronary artery spasm).",
                "May mask progressive ileus or gastric distension; not a substitute for nasogastric suction.",
            ),
            listOf(cite(OND, "5.1–5.5 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability ≈56% (8 mg tablet) after some first-pass metabolism; Tmax ≈1.6–2.2 h; food slightly increases bioavailability.",
            distribution = "Protein binding 70–76%; distributes into erythrocytes.",
            metabolism = "Extensively metabolised (hydroxylation, then glucuronide/sulfate conjugation) by hepatic CYP3A4 (predominant), CYP2D6 and CYP1A2.",
            elimination = "Metabolites in urine; ~5% recovered unchanged in urine.",
            halfLife = "3.1–6.2 h (8 mg; longer in >75 years); ≈11.6 h in mild–moderate and ≈20 h in severe hepatic impairment.",
            citations = listOf(cite(OND, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Apomorphine: contraindicated — profound hypotension and loss of consciousness.",
                "Serotonergic drugs (SSRIs, SNRIs, MAOIs, mirtazapine, fentanyl, lithium, tramadol, IV methylene blue): serotonin syndrome — monitor.",
                "Strong CYP3A4 inducers (phenytoin, carbamazepine, rifampin): ↑ ondansetron clearance; no dose adjustment recommended.",
                "Tramadol: may increase tramadol requirement — check pain control.",
                "Other QT-prolonging drugs: ECG monitoring recommended.",
            ),
            listOf(cite(OND, "4 Contraindications; 5.2; 7.1–7.3 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Epidemiological studies on birth defects are inconsistent and methodologically limited, precluding conclusions; postmarketing data show no drug-associated risk of miscarriage or adverse maternal outcomes."),
            listOf(cite(OND, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present at low levels in milk (relative infant dose 3.7% after IV doses up to 4 mg/day); no adverse effects reported in breastfed infants."),
            listOf(cite(OND, "8.2 Lactation")),
        ),
    ),

    // 3. Metoclopramide --------------------------------------------------------------------------
    DrugMonograph(
        id = "metoclopramide",
        name = "Metoclopramide",
        drugClass = "Dopamine-2 receptor antagonist",
        uses = "Adults: 4–12 weeks for symptomatic, documented GERD failing conventional therapy; acute and recurrent diabetic gastroparesis. Not recommended in children.",
        clearance = setOf(Clearance.Renal, Clearance.Hepatic),
        boxedWarning = Facts(
            listOf(
                "Tardive dyskinesia, potentially irreversible; risk rises with duration and cumulative dose. Contraindicated with prior TD. Use the shortest duration, reassess need, stop at first signs. GERD: max 12 weeks; gastroparesis: avoid >12 weeks.",
            ),
            listOf(cite(MCP, "Boxed Warning; 5.1")),
        ),
        dose = Facts(
            listOf(
                "GERD: 10–15 mg four times daily, 30 min before meals and at bedtime; max 60 mg/day for 4–12 weeks. Intermittent: single doses up to 20 mg before a provoking situation.",
                "Diabetic gastroparesis: 10 mg four times daily, 30 min before meals and at bedtime; max 40 mg/day.",
                "Elderly: consider starting at 5 mg four times daily. CYP2D6 poor metabolisers: as for moderate–severe hepatic impairment.",
            ),
            listOf(cite(MCP, "2.1–2.2 Recommended Dosage — Tables 1–2")),
        ),
        monitoring = Facts(
            listOf(
                "Signs of TD and other extrapyramidal symptoms; routinely monitor if gastroparesis treatment beyond 12 weeks is unavoidable.",
                "Insulin users: blood glucose (faster food delivery to the intestine).",
                "Digoxin, cyclosporine, tacrolimus, sirolimus: monitor drug concentrations (altered absorption).",
                "Neonates exposed during delivery: extrapyramidal signs.",
            ),
            listOf(cite(MCP, "5.1; 7.2 — Table 4; 8.1")),
        ),
        renal = Facts(
            listOf(
                "CrCl ≤60 mL/min — GERD: 5 mg four times daily or 10 mg three times daily (max 30 mg/day); gastroparesis: 5 mg four times daily (max 20 mg/day).",
                "ESRD, including haemodialysis and CAPD — GERD: 5 mg four times daily or 10 mg twice daily (max 20 mg/day); gastroparesis: 5 mg twice daily (max 10 mg/day).",
            ),
            listOf(cite(MCP, "2.1–2.2 — Tables 1–2; 8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf(
                "Child-Pugh B or C — GERD: 5 mg four times daily or 10 mg three times daily (max 30 mg/day); gastroparesis: 5 mg four times daily (max 20 mg/day).",
                "Child-Pugh A: no adjustment.",
            ),
            listOf(cite(MCP, "2.1–2.2 — Tables 1–2; 8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Restlessness (~10% at 10 mg four times daily)",
                "Drowsiness (~10%)",
                "Fatigue (~10%)",
                "Lassitude (~10%)",
                "Less often: insomnia, headache, confusion, dizziness",
            ),
            listOf(cite(MCP, "6 Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "Tardive dyskinesia (boxed warning).",
                "Other EPS: acute dystonia (can start in the first 24–48 h; more in adults <30 y), parkinsonism, motor restlessness — avoid in Parkinson's disease.",
                "Neuroleptic malignant syndrome, potentially fatal.",
                "Depression, including suicidal ideation and suicide — avoid with history of depression.",
                "Hypertension via catecholamine release — avoid in hypertension or with MAOIs.",
                "Fluid retention in cirrhosis or heart failure.",
                "Hyperprolactinaemia (galactorrhoea, amenorrhoea, gynaecomastia, impotence).",
                "Impaired ability to drive or operate machinery.",
            ),
            listOf(cite(MCP, "Boxed Warning; 5.1–5.8 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Oral bioavailability ≈80%; peak 1–2 h.",
            distribution = "~30% protein bound; Vd ≈3.5 L/kg.",
            metabolism = "Hepatic oxidation and glucuronide/sulfate conjugation; CYP2D6 forms monodeethylmetoclopramide.",
            elimination = "~85% of an oral dose in urine within 72 h (18–22% as free drug); AUC ~2× in moderate–severe renal impairment, ~3.5× in ESRD.",
            halfLife = "5–6 h with normal renal function.",
            citations = listOf(cite(MCP, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Antipsychotics: additive TD, EPS and NMS — avoid.",
                "Strong CYP2D6 inhibitors (quinidine, bupropion, fluoxetine, paroxetine): ↑ metoclopramide levels — reduce dose.",
                "MAO inhibitors: ↑ risk of hypertension — avoid.",
                "CNS depressants (alcohol, sedatives, hypnotics, opiates, anxiolytics): ↑ CNS depression.",
                "Dopamine agonists and levodopa: opposing effects — avoid.",
            ),
            listOf(cite(MCP, "7.1–7.2 Drug Interactions — Tables 3–4")),
        ),
        pregnancy = Facts(
            listOf("Published studies do not report an increased risk of adverse pregnancy outcomes; exposure during delivery may cause extrapyramidal signs and methaemoglobinaemia in the neonate."),
            listOf(cite(MCP, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in milk in variable amounts; breastfed infants have had GI reactions (intestinal discomfort, increased gas) — weigh benefits of breastfeeding against the mother's need."),
            listOf(cite(MCP, "8.2 Lactation")),
        ),
    ),

    // 4. Promethazine ----------------------------------------------------------------------------
    DrugMonograph(
        id = "promethazine",
        name = "Promethazine",
        drugClass = "Phenothiazine H1-antihistamine (sedative, antiemetic)",
        uses = "Allergic conditions, motion sickness, nausea and vomiting with anaesthesia and surgery, pre-/postoperative and obstetric sedation, adjunct to analgesics.",
        clearance = setOf(Clearance.Hepatic),
        boxedWarning = Facts(
            listOf(
                "Do not use in children <2 years — fatal respiratory depression reported. In children ≥2 years use the lowest effective dose and avoid other respiratory depressants.",
            ),
            listOf(cite(PMZ, "Boxed Warning (Warnings)")),
        ),
        dose = Facts(
            listOf(
                "Nausea and vomiting: 25 mg; 12.5–25 mg may be repeated every 4–6 h as needed.",
                "Allergy: 25 mg at bedtime, or 12.5 mg before meals and at bedtime; 6.25–12.5 mg three times daily usually suffices.",
                "Motion sickness: 25 mg twice daily, first dose ½–1 h before travel. Sedation: 25–50 mg at night. No maximum daily dose stated.",
            ),
            listOf(cite(PMZ, "Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf("Elderly: start low and observe closely for confusion and over-sedation."),
            listOf(cite(PMZ, "Precautions — Geriatric Use")),
        ),
        hepatic = Facts(
            listOf("Use cautiously in impaired liver function."),
            listOf(cite(PMZ, "Precautions — General")),
        ),
        commonEffects = Facts(
            listOf(
                "Drowsiness — the most prominent CNS effect",
                "Sedation, somnolence, dizziness",
                "Blurred vision",
                "Confusion, disorientation",
                "Dry mouth",
                "Nausea, vomiting",
                "Raised or lowered blood pressure, tachycardia, bradycardia",
            ),
            listOf(cite(PMZ, "Adverse Reactions (frequencies not stated)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Fatal respiratory depression in children <2 years (boxed warning).",
                "CNS depression, amplified by alcohol, sedatives, opioids, TCAs.",
                "Respiratory depression — avoid in COPD or sleep apnoea.",
                "Lowered seizure threshold.",
                "Bone-marrow depression: leukopenia, agranulocytosis.",
                "Neuroleptic malignant syndrome.",
                "Paradoxical reactions (hyperexcitability, abnormal movements) and cholestatic jaundice.",
            ),
            listOf(cite(PMZ, "Warnings; Adverse Reactions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Well absorbed orally; effects within 20 min, lasting 4–6 h (up to 12 h).",
            metabolism = "Hepatic, to sulfoxides and N-demethylpromethazine (predominant urinary metabolites).",
            elimination = "Metabolites appear in urine.",
            citations = listOf(cite(PMZ, "Clinical Pharmacology")),
        ),
        interactions = Facts(
            listOf(
                "CNS depressants: avoid or reduce dose — barbiturates by at least ½, narcotics by ¼–½.",
                "Epinephrine: do not use to treat promethazine-associated hypotension (vasopressor effect may be reversed).",
                "Anticholinergic drugs: use with caution.",
                "MAO inhibitors: increased extrapyramidal effects reported with phenothiazines.",
            ),
            listOf(cite(PMZ, "Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No adequate human studies — use only if benefit justifies risk; given within 2 weeks of delivery it may inhibit platelet aggregation in the newborn."),
            listOf(cite(PMZ, "Precautions — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Excretion in milk unknown; decide whether to stop nursing or the drug, considering its importance to the mother."),
            listOf(cite(PMZ, "Precautions — Nursing Mothers")),
        ),
    ),

    // 5. Prochlorperazine ------------------------------------------------------------------------
    DrugMonograph(
        id = "prochlorperazine",
        name = "Prochlorperazine",
        drugClass = "Phenothiazine (antiemetic, antipsychotic)",
        uses = "Severe nausea and vomiting; schizophrenia; short-term non-psychotic anxiety (not first choice).",
        clearance = emptySet(),
        boxedWarning = Facts(
            listOf(
                "Increased mortality in elderly patients with dementia-related psychosis (≈1.6–1.7× placebo); not approved for this use.",
            ),
            listOf(cite(PCZ, "Boxed Warning")),
        ),
        dose = Facts(
            listOf(
                "Severe nausea and vomiting: usually 5 or 10 mg three or four times daily; >40 mg/day only in resistant cases.",
                "Non-psychotic anxiety: 5 mg three or four times daily; not more than 20 mg/day or longer than 12 weeks.",
                "Schizophrenia: 5–10 mg three or four times daily, increased gradually; severe cases usually 100–150 mg daily. Elderly: lower range, titrate gradually.",
            ),
            listOf(cite(PCZ, "Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf("Complete a fall-risk assessment if diseases, conditions or medicines could worsen somnolence, postural hypotension or instability."),
            listOf(cite(PCZ, "Warnings — Falls")),
        ),
        monitoring = Facts(
            listOf(
                "Pre-existing low WBC or prior drug-induced leukopenia: CBC frequently in the first few months; stop at first decline in WBC; stop if ANC <1000/mm³.",
                "Fever with grippe-like symptoms: check liver tests; stop if abnormal.",
                "With lithium: watch closely for early neurotoxicity.",
                "Long-term use: reassess dose periodically; repeat fall-risk assessments.",
            ),
            listOf(cite(PCZ, "Precautions; Warnings; Adverse Reactions")),
        ),
        commonEffects = Facts(
            listOf(
                "Drowsiness",
                "Dizziness",
                "Blurred vision",
                "Hypotension",
                "Amenorrhoea",
                "Skin reactions",
                "Extrapyramidal reactions: motor restlessness, dystonia, pseudo-parkinsonism",
            ),
            listOf(cite(PCZ, "Adverse Reactions (frequencies not stated)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Increased mortality in elderly with dementia-related psychosis (boxed warning).",
                "Tardive dyskinesia, potentially irreversible.",
                "Neuroleptic malignant syndrome.",
                "Leukopenia, neutropenia, agranulocytosis.",
                "Cholestatic jaundice.",
                "Hypotension — use norepinephrine or phenylephrine; epinephrine may lower BP further.",
                "Falls from somnolence, postural hypotension, instability.",
                "In children, EPS may mimic Reye's syndrome or encephalopathy; avoid if Reye's suspected.",
            ),
            listOf(cite(PCZ, "Boxed Warning; Warnings; Precautions; Adverse Reactions")),
        ),
        pk = Pharmacokinetics(
            citations = listOf(cite(PCZ, "No pharmacokinetics section in this label")),
        ),
        interactions = Facts(
            listOf(
                "Lithium: encephalopathic syndrome, sometimes irreversible brain damage — monitor for neurotoxicity.",
                "Alcohol, anaesthetics, narcotics and other CNS depressants: intensified or prolonged effect.",
                "Phenytoin: metabolism may be impaired, precipitating toxicity; anticonvulsant doses may need adjustment.",
                "Oral anticoagulants: effect may be diminished.",
                "Propranolol: increased levels of both drugs; thiazides may worsen orthostatic hypotension.",
            ),
            listOf(cite(PCZ, "Warnings; Precautions")),
        ),
        pregnancy = Facts(
            listOf("Safety not established; not recommended except for severe, intractable nausea and vomiting; third-trimester exposure risks neonatal extrapyramidal and withdrawal symptoms."),
            listOf(cite(PCZ, "Warnings — Usage in Pregnancy")),
        ),
        lactation = Facts(
            listOf("Phenothiazines are excreted in breast milk; use with caution in nursing women."),
            listOf(cite(PCZ, "Warnings — Nursing Mothers")),
        ),
    ),

    // 6. Diphenhydramine -------------------------------------------------------------------------
    DrugMonograph(
        id = "diphenhydramine",
        name = "Diphenhydramine",
        drugClass = "Antihistamine (sedating, anticholinergic)",
        uses = "Injection when oral therapy is impractical: allergic reactions (adjunct to epinephrine in anaphylaxis), motion sickness, parkinsonism.",
        clearance = setOf(Clearance.Hepatic, Clearance.Renal),
        dose = Facts(
            listOf(
                "Adults: 10–50 mg IV (rate not exceeding 25 mg/min) or deep IM; 100 mg if required. Max 400 mg/day.",
                "For IV or IM use only — not subcutaneous, intradermal or as a local anaesthetic.",
            ),
            listOf(cite(DPH, "Dosage and Administration; Contraindications")),
        ),
        commonEffects = Facts(
            listOf(
                "Sedation",
                "Sleepiness",
                "Dizziness",
                "Disturbed coordination",
                "Epigastric distress",
                "Thickening of bronchial secretions",
            ),
            listOf(cite(DPH, "Adverse Reactions — most frequent (underscored)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Contraindicated in neonates and premature infants.",
                "Anticholinergic: caution in narrow-angle glaucoma, stenosing peptic ulcer, pyloroduodenal or bladder-neck obstruction, symptomatic prostatic hypertrophy.",
                "Local necrosis with subcutaneous or intradermal use.",
                "Children: overdose may cause hallucinations, convulsions or death; young children may become excited.",
                "Elderly (≈60+): more dizziness, sedation and hypotension.",
                "Caution in asthma or lower respiratory disease, raised intraocular pressure, hyperthyroidism, cardiovascular disease, hypertension.",
            ),
            listOf(cite(DPH, "Contraindications; Warnings; Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Rapid onset after injection.",
            distribution = "Widely distributed, including the CNS.",
            metabolism = "Partly metabolised in the liver.",
            elimination = "A portion excreted unchanged in urine. Detailed PK not available.",
            citations = listOf(cite(DPH, "Clinical Pharmacology")),
        ),
        interactions = Facts(
            listOf(
                "Alcohol and other CNS depressants (hypnotics, sedatives, tranquilisers): additive effects.",
                "MAO inhibitors: prolong and intensify anticholinergic (drying) effects.",
            ),
            listOf(cite(DPH, "Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Category B: no fetal harm in rat and rabbit studies, but no adequate human studies — use only if clearly needed."),
            listOf(cite(DPH, "Precautions — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Contraindicated in nursing mothers because of the higher risk of antihistamines for infants, especially newborns and prematures."),
            listOf(cite(DPH, "Contraindications")),
        ),
    ),

    // 7. Chlorpromazine --------------------------------------------------------------------------
    DrugMonograph(
        id = "chlorpromazine",
        name = "Chlorpromazine",
        drugClass = "Phenothiazine antipsychotic (also antiemetic)",
        uses = "Psychotic disorders including schizophrenia and mania; nausea and vomiting; intractable hiccups; presurgical apprehension; acute intermittent porphyria; adjunct in tetanus.",
        clearance = emptySet(),
        boxedWarning = Facts(
            listOf(
                "Increased mortality in elderly patients with dementia-related psychosis (≈1.6–1.7× placebo); not approved for this use.",
            ),
            listOf(cite(CPZ, "Boxed Warning")),
        ),
        dose = Facts(
            listOf(
                "Nausea and vomiting: 10–25 mg every 4–6 h as needed, increased if necessary.",
                "Psychiatric outpatients: 10 mg three or four times daily or 25 mg two or three times daily; increase gradually. Hospitalised: 500 mg/day generally suffices; little gain from >1,000 mg/day for extended periods.",
                "Intractable hiccups: 25–50 mg three or four times daily. Elderly, emaciated or debilitated: lower doses, increase more gradually.",
            ),
            listOf(cite(CPZ, "Dosage and Administration — Adults")),
        ),
        beforeStarting = Facts(
            listOf("Complete a fall-risk assessment if diseases, conditions or medicines could worsen somnolence, postural hypotension or instability."),
            listOf(cite(CPZ, "Warnings — Falls")),
        ),
        monitoring = Facts(
            listOf(
                "Pre-existing low WBC or prior drug-induced leukopenia: CBC frequently in the first months; stop if ANC <1000/mm³. Agranulocytosis mostly in weeks 4–10 — watch closely.",
                "Fever with grippe-like symptoms: liver tests; stop if abnormal.",
                "Long-term moderate–high doses: periodic ocular examinations.",
                "With lithium: watch for early neurotoxicity. Long-term: reassess maintenance dose and repeat fall-risk assessments.",
            ),
            listOf(cite(CPZ, "Precautions; Warnings; Adverse Reactions")),
        ),
        renal = Facts(
            listOf("Administer cautiously in renal disease."),
            listOf(cite(CPZ, "Precautions — General")),
        ),
        hepatic = Facts(
            listOf("Administer cautiously in liver disease; prior hepatic encephalopathy from cirrhosis increases sensitivity to CNS effects."),
            listOf(cite(CPZ, "Precautions — General")),
        ),
        commonEffects = Facts(
            listOf(
                "Drowsiness, mostly in the first 1–2 weeks",
                "Extrapyramidal reactions (dose-related): dystonia, motor restlessness, pseudo-parkinsonism",
                "Dry mouth, nasal congestion, constipation (occasional)",
                "Amenorrhoea, gynaecomastia; breast engorgement and lactation on large doses",
                "Increased appetite and weight",
            ),
            listOf(cite(CPZ, "Adverse Reactions (frequencies not stated)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Increased mortality in elderly with dementia-related psychosis (boxed warning).",
                "Tardive dyskinesia, potentially irreversible.",
                "Neuroleptic malignant syndrome.",
                "Agranulocytosis and other blood dyscrasias.",
                "Cholestatic jaundice, usually weeks 2–4.",
                "Postural hypotension, occasionally shock-like — use norepinephrine or phenylephrine, not epinephrine.",
                "Falls; sudden death reported (cardiac arrest or failed cough reflex).",
                "Long-term high doses: lens/corneal deposits and skin pigmentation.",
            ),
            listOf(cite(CPZ, "Boxed Warning; Warnings; Precautions; Adverse Reactions")),
        ),
        pk = Pharmacokinetics(
            citations = listOf(cite(CPZ, "Clinical Pharmacology (no pharmacokinetic data in this label)")),
        ),
        interactions = Facts(
            listOf(
                "CNS depressants (anaesthetics, barbiturates, narcotics): prolonged/intensified — about ¼–½ of their usual dose needed. Do not reduce anticonvulsant barbiturates.",
                "Lithium: encephalopathic syndrome — monitor for neurotoxicity.",
                "Phenytoin: may precipitate phenytoin toxicity; may lower seizure threshold.",
                "Oral anticoagulants: effect diminished.",
                "Propranolol: increased levels of both drugs; guanethidine effect counteracted.",
            ),
            listOf(cite(CPZ, "Warnings; Precautions")),
        ),
        pregnancy = Facts(
            listOf("Safety not established; give only when essential; third-trimester exposure risks neonatal extrapyramidal and withdrawal symptoms."),
            listOf(cite(CPZ, "Warnings — Usage in Pregnancy")),
        ),
        lactation = Facts(
            listOf("Excreted in breast milk; decide whether to stop nursing or the drug, considering its importance to the mother."),
            listOf(cite(CPZ, "Warnings — Nursing Mothers")),
        ),
    ),

    // 8. Paracetamol (IV label) ------------------------------------------------------------------
    DrugMonograph(
        id = "paracetamol",
        name = "Paracetamol",
        aliases = listOf("acetaminophen"),
        drugClass = "Non-opioid analgesic and antipyretic",
        uses = "IV: mild–moderate pain, moderate–severe pain with opioids (age ≥2 years), and fever.",
        clearance = setOf(Clearance.Hepatic),
        boxedWarning = Facts(
            listOf(
                "Medication errors and hepatotoxicity: avoid mg/mL confusion, dose by weight if <50 kg, program pumps correctly, and keep total daily acetaminophen from all sources within limits. Acute liver failure (transplant, death) mostly with excess doses or multiple products.",
            ),
            listOf(cite(PCM, "Boxed Warning; 5.1; 5.3")),
        ),
        dose = Facts(
            listOf(
                "Dosing below is for the IV injection, given only as a 15-minute IV infusion.",
                "≥50 kg: 1,000 mg every 6 h or 650 mg every 4 h; max single dose 1,000 mg; max 4,000 mg/day from all routes and products; minimum interval 4 h.",
                "<50 kg: 15 mg/kg every 6 h or 12.5 mg/kg every 4 h; max 75 mg/kg/day (up to 3,750 mg).",
            ),
            listOf(cite(PCM, "2.1–2.2 Dosage — Table 1")),
        ),
        monitoring = Facts(
            listOf(
                "Total daily acetaminophen from all routes and products.",
                "Warfarin: more frequent INR may be appropriate.",
            ),
            listOf(cite(PCM, "2.1; 7.2")),
        ),
        renal = Facts(
            listOf("Severe impairment (CrCl ≤30 mL/min): longer dosing intervals and a reduced total daily dose may be warranted."),
            listOf(cite(PCM, "5.1; 8.7 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf(
                "Contraindicated in severe hepatic impairment or severe active liver disease.",
                "Caution in hepatic impairment or active liver disease, alcoholism, chronic malnutrition, severe hypovolaemia; a reduced total daily dose may be warranted.",
            ),
            listOf(cite(PCM, "4 Contraindications; 5.1; 8.6 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Nausea (34% vs 31% placebo)",
                "Vomiting (15% vs 11%)",
                "Headache (10% vs 9%)",
                "Insomnia (7% vs 5%)",
                "Children: nausea, vomiting, constipation, pruritus",
            ),
            listOf(cite(PCM, "6.1 Clinical Trial Experience — Table 4")),
        ),
        seriousEffects = Facts(
            listOf(
                "Hepatic injury, including liver failure and death, above recommended doses (boxed warning).",
                "Medication errors leading to accidental overdose (boxed warning).",
                "Serious skin reactions: AGEP, SJS, TEN — stop at first rash.",
                "Hypersensitivity and anaphylaxis — stop immediately.",
            ),
            listOf(cite(PCM, "Boxed Warning; 5.1–5.4 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "IV: Cmax at end of the 15-min infusion, up to 70% higher than the same oral dose; AUC similar.",
            distribution = "Protein binding 10–25%; Vss ≈0.8 L/kg in adults; distributed to most tissues except fat.",
            metabolism = "Liver: glucuronidation, sulfation, and CYP2E1 oxidation to NAPQI, which is conjugated with glutathione.",
            elimination = "Metabolites mainly in urine; <5% unchanged; >90% of the dose excreted within 24 h.",
            halfLife = "≈2.4 h in adults (≈3 h in children).",
            citations = listOf(cite(PCM, "12.3 Pharmacokinetics — Table 5")),
        ),
        interactions = Facts(
            listOf(
                "CYP2E1 inducers or regulators: may increase hepatotoxic potential; alcohol effects are complex.",
                "Warfarin: chronic oral acetaminophen 4,000 mg/day has raised INR — consider more frequent INR checks.",
            ),
            listOf(cite(PCM, "7.1–7.2 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Epidemiological studies of oral acetaminophen have not shown a clear association with birth defects, miscarriage, or adverse maternal or fetal outcomes."),
            listOf(cite(PCM, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No data for the injection; published studies show acetaminophen passes rapidly into milk at levels similar to plasma (infant dose 1–2% of maternal); one report of infant rash."),
            listOf(cite(PCM, "8.2 Lactation")),
        ),
    ),

    // 9. Ibuprofen -------------------------------------------------------------------------------
    DrugMonograph(
        id = "ibuprofen",
        name = "Ibuprofen",
        drugClass = "NSAID (propionic acid derivative)",
        uses = "Rheumatoid arthritis and osteoarthritis, mild to moderate pain, primary dysmenorrhoea.",
        clearance = setOf(Clearance.Renal),
        boxedWarning = Facts(
            listOf(
                "Cardiovascular thrombotic events (MI, stroke), possibly early and rising with duration; contraindicated for CABG surgery.",
                "Serious GI bleeding, ulceration and perforation, possibly without warning; elderly at greater risk.",
            ),
            listOf(cite(IBU, "Boxed Warning")),
        ),
        dose = Facts(
            listOf(
                "Mild to moderate pain: 400 mg every 4–6 h as needed (doses >400 mg were no more effective).",
                "RA/OA: 1,200–3,200 mg/day as 300 mg four times daily or 400, 600 or 800 mg three or four times daily. Dysmenorrhoea: 400 mg every 4 h as needed.",
                "Max 3,200 mg/day. Use the lowest effective dose for the shortest duration; take with meals or milk if GI upset.",
            ),
            listOf(cite(IBU, "Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Blood pressure closely at start and throughout.",
                "Signs of GI bleeding; CBC and chemistry profile periodically on long-term therapy.",
                "Stop if signs of liver or renal disease, systemic features (eosinophilia, rash), or persistent/worsening abnormal liver tests.",
                "Pregnancy 20–30 weeks: consider amniotic fluid ultrasound if use exceeds 48 h.",
            ),
            listOf(cite(IBU, "Warnings — Hypertension, Fetal Toxicity; Precautions — Laboratory Tests")),
        ),
        renal = Facts(
            listOf("Advanced renal disease: not recommended; if it must be started, monitor renal function closely."),
            listOf(cite(IBU, "Warnings — Advanced Renal Disease")),
        ),
        commonEffects = Facts(
            listOf(
                "GI complaints overall (4–16%)",
                "Nausea, epigastric pain, heartburn (3–9%)",
                "Dizziness (3–9%)",
                "Rash (3–9%)",
                "Diarrhoea, indigestion, constipation, abdominal cramps, bloating (1–3%)",
                "Headache, nervousness (1–3%)",
                "Tinnitus (1–3%)",
                "Oedema, fluid retention (1–3%)",
            ),
            listOf(cite(IBU, "Adverse Reactions — table")),
        ),
        seriousEffects = Facts(
            listOf(
                "Cardiovascular thrombotic events (boxed warning).",
                "GI bleeding, ulceration, perforation (boxed warning).",
                "New or worsening hypertension; heart failure and oedema — avoid in severe heart failure.",
                "Renal papillary necrosis and renal decompensation (risk: renal or liver dysfunction, heart failure, diuretics/ACE inhibitors, elderly).",
                "Anaphylactoid reactions; avoid in aspirin-sensitive asthma.",
                "Serious skin reactions (exfoliative dermatitis, SJS, TEN) and DRESS.",
                "Fetal toxicity: premature ductus closure (≥30 weeks); oligohydramnios/neonatal renal impairment (≥20 weeks).",
                "Severe hepatic reactions (rare); ALT/AST ≥3× ULN in ~1%.",
            ),
            listOf(cite(IBU, "Boxed Warning; Warnings; Precautions — Hepatic Effects")),
        ),
        pk = Pharmacokinetics(
            absorption = "Rapidly absorbed; peak 1–2 h. Taken after a meal: slower rate, no appreciable loss of extent.",
            metabolism = "Rapidly metabolised; main urinary metabolites A (25%) and B (37%).",
            elimination = "Urine: 45–79% of dose within 24 h, mostly as metabolites (~1% free, ~14% conjugated ibuprofen); virtually complete 24 h after last dose.",
            halfLife = "1.8–2.0 h.",
            citations = listOf(cite(IBU, "Clinical Pharmacology — Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Low-dose aspirin: ibuprofen interferes with its antiplatelet effect; co-administration not generally recommended.",
                "Warfarin-type anticoagulants: synergistic risk of serious GI bleeding.",
                "Lithium: plasma levels ↑ ~15%, renal clearance ↓ ~19% — watch for toxicity.",
                "Methotrexate: possible enhanced toxicity — use caution.",
                "ACE inhibitors, furosemide, thiazides: reduced antihypertensive/natriuretic effect — watch for renal failure.",
            ),
            listOf(cite(IBU, "Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Limit dose and duration between about 20 and 30 weeks (fetal renal dysfunction, oligohydramnios) and avoid from about 30 weeks (premature closure of the ductus arteriosus)."),
            listOf(cite(IBU, "Precautions — Pregnancy; Warnings — Fetal Toxicity")),
        ),
        lactation = Facts(
            listOf("Excretion in milk unknown; decide whether to stop nursing or the drug, considering its importance to the mother."),
            listOf(cite(IBU, "Precautions — Nursing Mothers")),
        ),
    ),

    // 10. Sumatriptan ----------------------------------------------------------------------------
    DrugMonograph(
        id = "sumatriptan",
        name = "Sumatriptan",
        drugClass = "Triptan (5-HT1B/1D receptor agonist)",
        uses = "Acute treatment of migraine with or without aura in adults; not for prevention or cluster headache.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "25, 50 or 100 mg as a single dose (100 mg may not add benefit over 50 mg).",
                "A second dose only if there was some response; at least 2 h after the first. Max 200 mg in 24 h.",
            ),
            listOf(cite(SUM, "2.1 Dosing Information")),
        ),
        beforeStarting = Facts(
            listOf(
                "Triptan-naive patients with multiple cardiovascular risk factors (incl. elderly with risk factors): cardiovascular evaluation first.",
                "If evaluation negative: consider the first dose under medical supervision with an ECG immediately afterwards.",
            ),
            listOf(cite(SUM, "5.1; 8.5 Geriatric Use")),
        ),
        monitoring = Facts(
            listOf(
                "Blood pressure.",
                "Intermittent long-term users with cardiovascular risk factors: periodic cardiovascular evaluation.",
            ),
            listOf(cite(SUM, "5.1; 5.8")),
        ),
        hepatic = Facts(
            listOf(
                "Mild to moderate impairment: max single dose 50 mg.",
                "Severe impairment: contraindicated.",
            ),
            listOf(cite(SUM, "2.2; 4; 8.6 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Pain and other pressure sensations (6–8% vs 4% placebo)",
                "Atypical sensations (5–6% vs 4%)",
                "Paraesthesia (3–5% vs 2%)",
                "Warm/cold sensation (2–3% vs 2%)",
                "Neck/throat/jaw pain or tightness (up to 3%)",
                "Chest pain/tightness/pressure (1–2%)",
                "Malaise/fatigue (2–3% vs <1%)",
                "Vertigo (2% at 100 mg)",
            ),
            listOf(cite(SUM, "6.1 Clinical Trials Experience — Table 1")),
        ),
        seriousEffects = Facts(
            listOf(
                "Myocardial ischaemia/infarction and Prinzmetal's angina — contraindicated in CAD.",
                "Life-threatening arrhythmias (VT, VF).",
                "Chest/throat/neck/jaw tightness — usually non-cardiac; evaluate if high cardiac risk.",
                "Cerebral or subarachnoid haemorrhage and stroke.",
                "Other vasospasm: peripheral or GI ischaemia, splenic infarction, Raynaud's.",
                "Serotonin syndrome, mainly with SSRIs, SNRIs, TCAs, MAOIs.",
                "Raised blood pressure, rarely hypertensive crisis.",
                "Medication-overuse headache; anaphylaxis; seizures.",
            ),
            listOf(cite(SUM, "5.1–5.10 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability ≈15% (presystemic metabolism, incomplete absorption); Tmax ≈2 h (≈2.5 h during an attack); high-fat meal ↑ Cmax 15%, AUC 12%.",
            distribution = "Protein binding 14–21%; Vd 2.7 L/kg.",
            metabolism = "MAO, mainly MAO-A, to inactive indole acetic acid and its glucuronide; liver important in presystemic clearance.",
            elimination = "~60% urine (mostly inactive metabolite), ~40% faeces; 3% as unchanged drug.",
            halfLife = "≈2.5 h.",
            citations = listOf(cite(SUM, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Ergotamine or ergot-type drugs (dihydroergotamine, methysergide) within 24 h: contraindicated.",
                "MAO-A inhibitors (current or within 2 weeks): contraindicated — 7-fold ↑ exposure.",
                "Other 5-HT1 agonists (triptans) within 24 h: contraindicated.",
                "SSRIs, SNRIs, TCAs, MAOIs: serotonin syndrome.",
            ),
            listOf(cite(SUM, "4 Contraindications; 7.1–7.4 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Registry and epidemiological data have not shown increased or consistent birth defects; animal studies showed embryolethality and fetal abnormalities."),
            listOf(cite(SUM, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Excreted in milk after subcutaneous dosing; infant exposure can be minimised by avoiding breastfeeding for 12 h after a dose."),
            listOf(cite(SUM, "8.2 Lactation")),
        ),
    ),

    // 11. Methylprednisolone ---------------------------------------------------------------------
    DrugMonograph(
        id = "methylprednisolone",
        name = "Methylprednisolone",
        drugClass = "Glucocorticoid",
        uses = "Endocrine, rheumatic, collagen, dermatologic, allergic, ophthalmic, respiratory, haematologic, neoplastic, oedematous and GI disorders; acute exacerbations of multiple sclerosis.",
        clearance = emptySet(),
        dose = Facts(
            listOf(
                "Initial 4–48 mg/day depending on the disease; once response is seen, reduce in small decrements to the lowest effective maintenance dose. No maximum stated.",
                "After long-term therapy, withdraw gradually rather than abruptly; stress may need a temporary dose increase.",
                "MS exacerbations: prednisolone 200 mg/day for 1 week then 80 mg every other day for 1 month (4 mg methylprednisolone = 5 mg prednisolone).",
            ),
            listOf(cite(MPS, "Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf(
                "Screen for hepatitis B before immunosuppressive (e.g. prolonged) treatment.",
                "Rule out latent or active amoebiasis in people who have been in the tropics or have unexplained diarrhoea.",
            ),
            listOf(cite(MPS, "Warnings — Hepatitis B Virus Reactivation; Amebiasis")),
        ),
        monitoring = Facts(
            listOf(
                "Infections; latent TB or tuberculin reactivity: watch for reactivation and give chemoprophylaxis during prolonged therapy.",
                "Oral anticoagulants: monitor coagulation indices.",
                "Children on prolonged therapy: growth and development.",
                "Patients at high risk of tumour lysis syndrome: monitor closely.",
            ),
            listOf(cite(MPS, "Warnings; Precautions")),
        ),
        renal = Facts(
            listOf("Use with caution in renal insufficiency."),
            listOf(cite(MPS, "Precautions — General")),
        ),
        hepatic = Facts(
            listOf("Corticosteroid effect is enhanced in cirrhosis."),
            listOf(cite(MPS, "Precautions — General")),
        ),
        commonEffects = Facts(
            listOf(
                "Sodium and fluid retention, hypertension, potassium loss",
                "Muscle weakness, steroid myopathy, osteoporosis",
                "Cushingoid state, decreased carbohydrate tolerance",
                "Impaired wound healing, thin fragile skin, petechiae and ecchymoses",
                "Menstrual irregularities",
                "Peptic ulcer, abdominal distension",
                "Posterior subcapsular cataract, raised intraocular pressure",
                "Headache, vertigo",
            ),
            listOf(cite(MPS, "Adverse Reactions (frequencies not stated)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Immunosuppression and infection, incl. TB reactivation, severe varicella/measles, hepatitis B reactivation, fungal infections, Strongyloides hyperinfection.",
                "Secondary adrenocortical insufficiency on withdrawal, may persist for months — reinstitute in stress.",
                "Cataracts and glaucoma with prolonged use.",
                "Hypertension, salt and water retention, hypokalaemia.",
                "Psychic derangements, from euphoria to psychosis.",
                "Live vaccines contraindicated at immunosuppressive doses.",
                "Scleroderma renal crisis in systemic sclerosis; tumour lysis syndrome in malignancy.",
                "Kaposi's sarcoma; avoid in cerebral malaria.",
            ),
            listOf(cite(MPS, "Warnings; Precautions — General")),
        ),
        pk = Pharmacokinetics(
            absorption = "Glucocorticoids are readily absorbed from the GI tract.",
            citations = listOf(cite(MPS, "Description (no pharmacokinetics section in this label)")),
        ),
        interactions = Facts(
            listOf(
                "Cyclosporine: mutual inhibition of metabolism; convulsions reported.",
                "Enzyme inducers (phenobarbital, phenytoin, rifampin): ↑ methylprednisolone clearance — may need a higher dose.",
                "Ketoconazole, troleandomycin: ↓ clearance — titrate to avoid steroid toxicity.",
                "High-dose aspirin: ↑ salicylate clearance; risk of salicylate toxicity on steroid withdrawal.",
                "Oral anticoagulants: variable effect — monitor coagulation indices.",
            ),
            listOf(cite(MPS, "Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No adequate human studies — weigh benefits against hazards; observe infants of mothers on substantial doses for hypoadrenalism."),
            listOf(cite(MPS, "Warnings — Usage in Pregnancy")),
        ),
        lactation = Facts(
            listOf("Use in nursing mothers requires weighing possible benefits against potential hazards."),
            listOf(cite(MPS, "Warnings — Usage in Pregnancy")),
        ),
    ),

    // 12. Magnesium sulphate ---------------------------------------------------------------------
    DrugMonograph(
        id = "magnesium-sulphate",
        name = "Magnesium sulphate",
        aliases = listOf("magnesium sulfate"),
        drugClass = "Magnesium salt (parenteral)",
        uses = "Prevention of eclampsia in pre-eclampsia; treatment of eclamptic seizures and prevention of recurrence (label: premixed IV infusion in 5% dextrose).",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "IV by infusion pump: loading 4–6 g over 15 min, then 1–2 g/h. Max 30–40 g per 24 h; not beyond 5–7 days.",
                "Eclampsia: consider targeting serum magnesium 3–6 mg/100 mL (2.5–5 mEq/L); for recurrent eclampsia consider an extra 2 g IV bolus; continue until seizures cease.",
                "Adjust dose by serum magnesium and clinical status.",
            ),
            listOf(cite(MGS, "2.1–2.2 Recommended Dosage")),
        ),
        monitoring = Facts(
            listOf(
                "Serum magnesium and signs of toxicity (facial oedema, diminished deep tendon reflexes, respiratory depression).",
                "Safe dosing: patellar reflex present and respirations ≥ about 16/min. Reflexes fall above 4 mEq/L; absent at 10 mEq/L (respiratory paralysis risk).",
                "Keep injectable calcium immediately available; maintain urine output 100 mL per 4 h.",
                "With neuromuscular blockers: monitor respiration and blockade (train-of-four).",
            ),
            listOf(cite(MGS, "5.2 Risk of Magnesium Toxicity; 7 Drug Interactions")),
        ),
        renal = Facts(
            listOf(
                "Severe renal impairment and/or urine output <0.5 mL/kg/h: 4 g loading dose, then 1 g/h; max 20 g over 48 h.",
                "Titrate using frequent magnesium levels and toxicity signs; urine output <100 mL per 4 h raises toxicity risk.",
            ),
            listOf(cite(MGS, "2.3 Severe Renal Impairment and/or Oliguria; 8.6")),
        ),
        commonEffects = Facts(
            listOf(
                "Flushing",
                "Sweating",
                "Hypotension",
                "Depressed reflexes, flaccid paralysis",
                "Hypothermia",
                "Circulatory collapse, cardiac depression",
                "CNS depression progressing to respiratory paralysis",
                "Hypocalcaemia",
            ),
            listOf(cite(MGS, "6 Adverse Reactions — most common (frequencies not stated)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Fetal–neonatal toxicity beyond 5–7 days: fetal hypocalcaemia, bone demineralisation, neonatal fractures; neonatal magnesium toxicity.",
                "Magnesium toxicity: respiratory depression, acute renal failure, rarely pulmonary oedema.",
                "Raised blood glucose (dextrose solution) — caution in prediabetes or diabetes.",
                "Unapproved tocolytics (terbutaline, nifedipine): pulmonary oedema and hypotension — do not combine.",
                "Aluminium toxicity with prolonged use in renal impairment.",
                "Myasthenic crisis — contraindicated in myasthenia gravis.",
            ),
            listOf(cite(MGS, "5.1–5.6 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            distribution = "1–2% of body magnesium in extracellular fluid; 30% bound to albumin.",
            elimination = "Excreted solely by the kidney, in proportion to serum concentration and GFR.",
            halfLife = "≈4–5 h in pre-eclamptic women (clearance 4–5 L/h).",
            citations = listOf(cite(MGS, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Neuromuscular blockers (succinylcholine, rocuronium, vecuronium etc.): potentiated, prolonged blockade — adjust dose.",
                "Opioids, propofol: ↑ analgesia and CNS depression — monitor depth.",
                "Dihydropyridine calcium channel blockers (e.g. nifedipine): exaggerated hypotension.",
                "Drugs causing magnesium loss (loop/thiazide diuretics, aminoglycosides, amphotericin B, cisplatin, cyclosporine, digitalis, alcohol): monitor levels.",
                "Same IV line: incompatible with salicylates and alkali carbonates.",
            ),
            listOf(cite(MGS, "2.4; 7 Drug Interactions — Table")),
        ),
        pregnancy = Facts(
            listOf("Indicated in pregnancy for pre-eclampsia/eclampsia; not approved for preterm labour; use beyond 5–7 days may cause fetal hypocalcaemia and bone abnormalities."),
            listOf(cite(MGS, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("IV magnesium raises milk magnesium only slightly and infant oral absorption is poor; weigh breastfeeding benefits against maternal need."),
            listOf(cite(MGS, "8.2 Lactation")),
        ),
    ),
)
