package com.medicalaid.app.content.drugs.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.drugs.Clearance
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.Facts
import com.medicalaid.app.content.drugs.Pharmacokinetics

/*
 * Antihypertensives — oral tablets/capsules. Each monograph paraphrases one US FDA label (DailyMed).
 * Older (non-PLR) labels have no numbered sections, so their citations name the label heading instead.
 */

internal val antihypertensiveSources = listOf(
    Source(
        "fda-label-labetalol", Authority.FDA, "Drug label (DailyMed)",
        "Labetalol hydrochloride tablets — Innogenix", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=82fc2d64-0572-4a9b-94fc-f6eb8d930c54",
        status = "Revised Dec 2025",
    ),
    Source(
        "fda-label-nifedipine", Authority.FDA, "Drug label (DailyMed)",
        "Nifedipine extended-release tablets — Pfizer", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8ebcb33c-f43b-4b36-9f94-9774b2a59e06",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-methyldopa", Authority.FDA, "Drug label (DailyMed)",
        "Methyldopa tablets — Rising Pharma Holdings", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=9e22e1d8-04ec-4b67-92ea-b09c706f2703",
        status = "Revised Mar 2025",
    ),
    Source(
        "fda-label-hydralazine", Authority.FDA, "Drug label (DailyMed)",
        "Hydralazine hydrochloride tablets — Heritage Pharmaceuticals (Avet)", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=669532c9-ba15-4f4b-95a8-13782deb3521",
        status = "Revised Apr 2026",
    ),
    Source(
        "fda-label-hydrochlorothiazide", Authority.FDA, "Drug label (DailyMed)",
        "Hydrochlorothiazide tablets — Chartwell RX", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=4e43dd27-3f4b-4205-a26e-6ecaa8281347",
        status = "Revised Nov 2026",
    ),
    Source(
        "fda-label-chlorthalidone", Authority.FDA, "Drug label (DailyMed)",
        "Chlorthalidone tablets — Ingenus Pharmaceuticals", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=3f0f3ee0-f5a6-4bda-8160-a77744e9fa46",
        status = "Revised Mar 2025",
    ),
    Source(
        "fda-label-amlodipine", Authority.FDA, "Drug label (DailyMed)",
        "Amlodipine besylate tablets — China Resources Saike Pharmaceutical", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=3518853a-4c5b-4161-a178-8da7f656f009",
        status = "Revised Jun 2026",
    ),
    Source(
        "fda-label-telmisartan", Authority.FDA, "Drug label (DailyMed)",
        "Telmisartan tablets — Alembic Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=4be6575f-ea8d-4cd8-a49d-3d1c93f2621a",
        status = "Revised May 2026",
    ),
    Source(
        "fda-label-losartan", Authority.FDA, "Drug label (DailyMed)",
        "Losartan potassium tablets — Novadoz Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=5edb843a-66ed-4950-9ed0-393e6a5d7c99",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-enalapril", Authority.FDA, "Drug label (DailyMed)",
        "Enalapril maleate tablets — Amici Pharma", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=a88902bd-742f-4ea6-b625-cc9df1709efc",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-ramipril", Authority.FDA, "Drug label (DailyMed)",
        "Ramipril capsules — Rising Pharma Holdings", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=d5c58fb5-b415-4567-9790-55631705c5cd",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-metoprolol", Authority.FDA, "Drug label (DailyMed)",
        "Metoprolol succinate extended-release tablets — Granules Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=ff180ca2-7490-a571-e053-6394a90a97b1",
        status = "Revised Oct 2026",
    ),
)

private const val SRC_LABETALOL = "fda-label-labetalol"
private const val SRC_NIFEDIPINE = "fda-label-nifedipine"
private const val SRC_METHYLDOPA = "fda-label-methyldopa"
private const val SRC_HYDRALAZINE = "fda-label-hydralazine"
private const val SRC_HCTZ = "fda-label-hydrochlorothiazide"
private const val SRC_CHLORTHALIDONE = "fda-label-chlorthalidone"
private const val SRC_AMLODIPINE = "fda-label-amlodipine"
private const val SRC_TELMISARTAN = "fda-label-telmisartan"
private const val SRC_LOSARTAN = "fda-label-losartan"
private const val SRC_ENALAPRIL = "fda-label-enalapril"
private const val SRC_RAMIPRIL = "fda-label-ramipril"
private const val SRC_METOPROLOL = "fda-label-metoprolol"

internal val antihypertensiveDrugs = listOf(
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "labetalol",
        name = "Labetalol",
        aliases = listOf("labetalol hydrochloride"),
        drugClass = "Alpha-1 and non-selective beta-blocker",
        uses = "Hypertension, alone or with other antihypertensives (especially thiazide and loop diuretics).",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Start 100 mg twice daily (alone or added to a diuretic); increase by 100 mg twice daily every 2–3 days. Usual maintenance 200–400 mg twice daily.",
                "Severe hypertension: may need 1,200–2,400 mg/day; titrate in steps not exceeding 200 mg twice daily.",
                "Elderly: start 100 mg twice daily; many need only 100–200 mg twice daily.",
            ),
            listOf(cite(SRC_LABETALOL, "2.1–2.3 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Symptomatic postural hypotension and syncope after the first dose and each dose increase (more likely in the elderly).",
                "Heart rate and rhythm (bradycardia, heart block).",
                "After stopping: watch for new or worsening angina — do not stop abruptly.",
                "Signs or symptoms of liver injury: investigate; do not restart without another explanation.",
            ),
            listOf(cite(SRC_LABETALOL, "5.1; 5.2; 5.4; 5.8")),
        ),
        commonEffects = Facts(
            listOf(
                "Dizziness (11% vs 3% placebo)",
                "Nausea (6%)",
                "Fatigue (5%)",
                "Dyspepsia (3%)",
                "Nasal stuffiness (3%)",
                "Headache (2%)",
                "Dyspnoea (2%)",
                "Vertigo (2%)",
            ),
            listOf(cite(SRC_LABETALOL, "6.1 Clinical Trials Experience — Table 1")),
        ),
        seriousEffects = Facts(
            listOf(
                "Symptomatic postural hypotension and syncope.",
                "Bradycardia, sinus pause, heart block, cardiac arrest. Contraindicated in severe bradycardia, >1st-degree heart block, cardiogenic shock.",
                "Worsening heart failure — avoid in overt failure; contraindicated in decompensated failure.",
                "Abrupt withdrawal: angina exacerbation and myocardial infarction.",
                "Bronchospasm — contraindicated in bronchial asthma or obstructive airway disease.",
                "Masked, severe or prolonged hypoglycaemia, especially with diabetes or fasting.",
                "Hepatic injury, usually reversible; hepatic necrosis and death reported.",
                "Paradoxical hypertension in phaeochromocytoma; poor response to epinephrine in anaphylaxis; floppy iris syndrome at cataract surgery.",
            ),
            listOf(cite(SRC_LABETALOL, "4 Contraindications; 5.1–5.11 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Peak 1–2 h; absolute bioavailability 25% (extensive first-pass), increased by food.",
            distribution = "About 50% protein bound; crosses the placenta.",
            metabolism = "Mainly conjugation to glucuronide metabolites.",
            elimination = "55–60% in urine (conjugates or unchanged) within 24 h; metabolites also via bile into faeces. Dialysis removes <1%.",
            halfLife = "6–8 h (oral); not altered in renal or hepatic impairment, though bioavailability rises in hepatic impairment.",
            citations = listOf(cite(SRC_LABETALOL, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Non-dihydropyridine calcium-channel blockers (e.g. verapamil): contraindicated. Digitalis and other negative chronotropes: ↑ bradycardia or hypotension.",
                "Beta-agonist bronchodilators: bronchodilator effect antagonised.",
                "Nitroglycerin: additive BP lowering, blunted reflex tachycardia — monitor BP.",
                "Halothane: synergistic hypotension — do not use concentrations of 3% or above.",
                "Lab tests: falsely high urinary catecholamines, metanephrines and VMA (fluorimetric/photometric); false-positive amphetamine screen.",
            ),
            listOf(cite(SRC_LABETALOL, "7.1–7.5 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Extensive experience has not identified a drug-associated risk of major birth defects, miscarriage or adverse outcomes; exposed neonates may have hypotension, bradycardia, hypoglycaemia or respiratory depression."),
            listOf(cite(SRC_LABETALOL, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk at low levels; no data on effects on the breastfed infant or on milk production."),
            listOf(cite(SRC_LABETALOL, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "nifedipine",
        name = "Nifedipine",
        drugClass = "Calcium channel blocker (dihydropyridine)",
        uses = "Hypertension; vasospastic angina; chronic stable angina.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Extended-release tablets: start 30 or 60 mg once daily (hypertension or angina); titrate over 7–14 days. Doses above 120 mg are not recommended.",
                "Swallow whole — do not bite or divide. Angina: doses above 90 mg only with caution.",
                "From capsules: switch at the nearest equivalent total daily dose (e.g. 10 mg three times daily → 30 mg once daily).",
            ),
            listOf(cite(SRC_NIFEDIPINE, "Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Blood pressure during initiation and titration, especially with other BP-lowering drugs.",
                "Digoxin levels when starting, adjusting or stopping nifedipine.",
                "In heart failure, distinguish dependent oedema from worsening left ventricular dysfunction.",
            ),
            listOf(cite(SRC_NIFEDIPINE, "Precautions — General, Drug Interactions")),
        ),
        commonEffects = Facts(
            listOf(
                "Peripheral oedema, dose-related (~10% to ~30% at 180 mg)",
                "Headache (15.8% vs 9.8% placebo)",
                "Fatigue (5.9% vs 4.1%)",
                "Dizziness (4.1% vs 4.5%)",
                "Constipation (3.3% vs 2.3%)",
                "Nausea (3.3% vs 1.9%)",
            ),
            listOf(cite(SRC_NIFEDIPINE, "Adverse Reactions; Precautions — Peripheral Edema")),
        ),
        seriousEffects = Facts(
            listOf(
                "Excessive hypotension, mainly during titration or with beta-blockers; before high-dose fentanyl surgery allow ≥36 h washout if possible.",
                "Increased angina or acute MI on starting or increasing the dose (rare), especially with severe obstructive coronary disease.",
                "Beta-blocker withdrawal syndrome — taper beta-blockers rather than stopping abruptly before starting nifedipine.",
                "Heart failure (rare), usually with a beta-blocker; higher risk with tight aortic stenosis.",
                "GI obstruction or bezoars needing surgery (rare), even without known GI disease; GI ulceration from tablet adherence.",
                "Rare raised liver enzymes, cholestasis, allergic hepatitis; rare reversible BUN/creatinine rises in chronic renal insufficiency.",
            ),
            listOf(cite(SRC_NIFEDIPINE, "Warnings; Precautions — Laboratory Tests")),
        ),
        pk = Pharmacokinetics(
            absorption = "Completely absorbed; plateau ≈6 h after the first dose; bioavailability 86% of capsules; food does not affect extent.",
            distribution = "Protein binding 92–98%; may be much lower in renal or hepatic impairment.",
            metabolism = "Extensive hepatic biotransformation (CYP3A4) to inactive metabolites.",
            elimination = "60–80% as inactive metabolites in urine (<0.1% unchanged); rest in faeces, likely via bile. Renal impairment does not significantly alter kinetics.",
            halfLife = "≈2 h; longer (with higher bioavailability) in cirrhosis.",
            citations = listOf(
                cite(SRC_NIFEDIPINE, "Clinical Pharmacology — Pharmacokinetics and Metabolism"),
                cite(SRC_NIFEDIPINE, "Precautions — Drug Interactions"),
            ),
        ),
        interactions = Facts(
            listOf(
                "CYP3A4 inducers (e.g. phenytoin, ↓ exposure ~70%): avoid; consider another antihypertensive.",
                "CYP3A inhibitors (azoles, clarithromycin, erythromycin, fluoxetine, nefazodone, protease inhibitors) and cimetidine: ↑ exposure — monitor; consider lowest starting dose.",
                "Grapefruit juice: about doubles AUC and Cmax — avoid.",
                "Beta-blockers: occasional heart failure, severe hypotension or worse angina.",
                "Digoxin: levels may rise (mean 45% in one study) — monitor.",
            ),
            listOf(cite(SRC_NIFEDIPINE, "Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Teratogenic and embryo/fetotoxic in animals; no adequate studies in pregnant women — use only if the potential benefit justifies the potential risk."),
            listOf(cite(SRC_NIFEDIPINE, "Precautions — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Transferred into breast milk; use while breastfeeding only if the potential benefit justifies the potential risk."),
            listOf(cite(SRC_NIFEDIPINE, "Precautions — Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "methyldopa",
        name = "Methyldopa",
        aliases = listOf("alpha-methyldopa"),
        drugClass = "Centrally acting antihypertensive",
        uses = "Hypertension.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Start 250 mg two or three times daily for the first 48 h; adjust at intervals of ≥2 days. Make increases in the evening to limit sedation.",
                "Maintenance 500 mg–2 g/day in 2–4 doses; maximum 3 g/day.",
                "With antihypertensives other than thiazides: limit the initial dose to 500 mg/day in divided doses.",
            ),
            listOf(cite(SRC_METHYLDOPA, "Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf("Blood count (haematocrit, haemoglobin or red cell count), direct Coombs test and liver function tests."),
            listOf(cite(SRC_METHYLDOPA, "Warnings; Precautions — Laboratory Tests")),
        ),
        monitoring = Facts(
            listOf(
                "Blood counts periodically to detect haemolytic anaemia; direct Coombs test at 6 and 12 months may be useful.",
                "Liver function periodically, especially in the first 6–12 weeks or with unexplained fever; stop if fever, abnormal LFTs or jaundice.",
                "Before transfusion: both direct and indirect Coombs tests.",
                "Elderly: monitoring renal function may be useful.",
            ),
            listOf(cite(SRC_METHYLDOPA, "Warnings; Precautions — Laboratory Tests, Geriatric Use")),
        ),
        renal = Facts(
            listOf(
                "Largely excreted by the kidney — patients with impaired renal function may respond to smaller doses.",
                "Removed by dialysis; hypertension has occasionally recurred after dialysis.",
            ),
            listOf(cite(SRC_METHYLDOPA, "Dosage and Administration; Precautions — General")),
        ),
        hepatic = Facts(
            listOf(
                "Contraindicated in active hepatic disease (acute hepatitis, active cirrhosis) and in liver disorders previously linked to methyldopa.",
                "Use with caution with a history of liver disease or dysfunction.",
            ),
            listOf(cite(SRC_METHYLDOPA, "Contraindications; Precautions — General")),
        ),
        commonEffects = Facts(
            listOf(
                "Sedation, usually transient (start of therapy or after dose increases)",
                "Headache (early, transient)",
                "Asthenia or weakness (early, transient)",
            ),
            listOf(cite(SRC_METHYLDOPA, "Adverse Reactions — frequencies not stated")),
        ),
        seriousEffects = Facts(
            listOf(
                "Positive direct Coombs test in 10–20% on prolonged therapy (usually at 6–12 months); rarely haemolytic anaemia, potentially fatal — stop and do not restart.",
                "Liver disorders: fever, hepatitis, cholestatic jaundice (usually in the first 2–3 months); rarely fatal hepatic necrosis — do not restart.",
                "Granulocytopenia and thrombocytopenia (rare, reversible).",
                "Oedema or weight gain — stop if oedema progresses or heart failure appears.",
                "Involuntary choreoathetotic movements in severe bilateral cerebrovascular disease — stop therapy.",
            ),
            listOf(cite(SRC_METHYLDOPA, "Warnings; Precautions — General")),
        ),
        pk = Pharmacokinetics(
            distribution = "Crosses the placenta; appears in cord blood and breast milk.",
            metabolism = "Extensively metabolised (e.g. mono-O-sulfate, α-methyldopamine, conjugates); effect probably via α-methylnorepinephrine.",
            elimination = "≈70% of absorbed drug excreted in urine as methyldopa and its mono-O-sulfate; renal clearance ≈130 mL/min, reduced in renal insufficiency.",
            halfLife = "105 minutes (plasma); excretion essentially complete in 36 h.",
            citations = listOf(cite(SRC_METHYLDOPA, "Clinical Pharmacology — Pharmacokinetics and Metabolism")),
        ),
        interactions = Facts(
            listOf(
                "MAO inhibitors: contraindicated.",
                "Lithium: monitor for lithium toxicity.",
                "Ferrous sulfate or gluconate: ↓ methyldopa bioavailability — co-administration not recommended.",
                "Other antihypertensives: potentiation. Anaesthetics: reduced doses may be needed.",
                "Lab tests: falsely high urinary catecholamines (confounds phaeochromocytoma work-up); interferes with uric acid, creatinine (alkaline picrate) and SGOT (colorimetric) assays.",
            ),
            listOf(cite(SRC_METHYLDOPA, "Contraindications; Precautions — Drug Interactions, Drug/Laboratory Test Interactions")),
        ),
        pregnancy = Facts(
            listOf("Published reports across all trimesters indicate the possibility of fetal harm appears remote; no adequate first-trimester studies — use only if clearly needed."),
            listOf(cite(SRC_METHYLDOPA, "Precautions — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Appears in breast milk — use with caution in nursing women."),
            listOf(cite(SRC_METHYLDOPA, "Precautions — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "hydralazine",
        name = "Hydralazine",
        aliases = listOf("hydralazine hydrochloride"),
        drugClass = "Direct-acting vasodilator",
        uses = "Essential hypertension, alone or as an adjunct.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Start 10 mg four times daily for 2–4 days, then 25 mg four times daily for the rest of week 1; from week 2, 50 mg four times daily.",
                "Maintenance: lowest effective dose. Resistant patients may need up to 300 mg/day; toxic reactions (especially LE cell syndrome) are more frequent at high doses.",
            ),
            listOf(cite(SRC_HYDRALAZINE, "Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf("Complete blood count and antinuclear antibody (ANA) titre."),
            listOf(cite(SRC_HYDRALAZINE, "Precautions — Laboratory Tests")),
        ),
        monitoring = Facts(
            listOf(
                "CBC and ANA titre periodically during prolonged therapy, even if asymptomatic.",
                "Repeat if arthralgia, fever, chest pain, continued malaise or other unexplained symptoms develop.",
                "Stop if blood dyscrasias develop.",
            ),
            listOf(cite(SRC_HYDRALAZINE, "Precautions — Laboratory Tests")),
        ),
        renal = Facts(
            listOf("Use with caution in advanced renal damage."),
            listOf(cite(SRC_HYDRALAZINE, "Precautions — General")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache",
                "Anorexia",
                "Nausea",
                "Vomiting",
                "Diarrhoea",
                "Palpitations",
                "Tachycardia",
                "Angina pectoris",
            ),
            listOf(cite(SRC_HYDRALAZINE, "Adverse Reactions — listed as common; frequencies not stated")),
        ),
        seriousEffects = Facts(
            listOf(
                "Lupus-like syndrome, including glomerulonephritis — discontinue unless benefit outweighs risk; residua may persist and long-term steroids may be needed.",
                "Angina, ECG ischaemia; implicated in myocardial infarction — contraindicated in coronary artery disease.",
                "May raise pulmonary artery pressure in mitral valve disease — contraindicated in mitral valvular rheumatic heart disease.",
                "Blood dyscrasias (↓ haemoglobin, leukopenia, agranulocytosis, purpura) — discontinue.",
                "Peripheral neuritis (paraesthesia, numbness, tingling) — add pyridoxine if symptoms develop.",
                "Postural hypotension; use with caution after cerebrovascular accident.",
            ),
            listOf(cite(SRC_HYDRALAZINE, "Contraindications; Warnings; Precautions — General, Laboratory Tests")),
        ),
        pk = Pharmacokinetics(
            absorption = "Rapidly absorbed; peak 1–2 h; food increases plasma levels.",
            distribution = "87% protein bound.",
            metabolism = "Extensive hepatic metabolism; polymorphic acetylation — slow acetylators have higher levels and need lower doses.",
            elimination = "Mainly as metabolites in urine.",
            halfLife = "3–7 h.",
            citations = listOf(
                cite(SRC_HYDRALAZINE, "Clinical Pharmacology"),
                cite(SRC_HYDRALAZINE, "Precautions — Drug/Food Interactions"),
            ),
        ),
        interactions = Facts(
            listOf(
                "Diazoxide or other potent parenteral antihypertensives: profound hypotension — observe continuously for several hours.",
                "MAO inhibitors: use with caution.",
                "Epinephrine: pressor response may be reduced.",
                "Food: higher hydralazine plasma levels.",
            ),
            listOf(cite(SRC_HYDRALAZINE, "Precautions — General, Drug/Drug and Drug/Food Interactions")),
        ),
        pregnancy = Facts(
            listOf("Teratogenic in mice (possibly rabbits) at high doses; no adequate human studies — use only if the expected benefit justifies the potential fetal risk."),
            listOf(cite(SRC_HYDRALAZINE, "Precautions — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Excreted in breast milk."),
            listOf(cite(SRC_HYDRALAZINE, "Precautions — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "hydrochlorothiazide",
        name = "Hydrochlorothiazide",
        aliases = listOf("HCTZ"),
        drugClass = "Thiazide diuretic",
        uses = "Hypertension (alone or with other antihypertensives); adjunct for oedema in heart failure, cirrhosis, corticosteroid/oestrogen therapy and renal dysfunction.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Hypertension: start 25 mg once daily; may increase to 50 mg/day as one or two doses. Doses above 50 mg often cause marked potassium falls.",
                "Oedema: 25–100 mg daily as a single or divided dose; intermittent dosing (alternate days or 3–5 days a week) is often effective.",
            ),
            listOf(cite(SRC_HCTZ, "Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Serum electrolytes periodically (hyponatraemia, hypochloraemic alkalosis, hypokalaemia); serum and urine electrolytes especially with vomiting or parenteral fluids.",
                "Diabetes: hyperglycaemia may occur; insulin or oral agent doses may need adjustment.",
                "If progressive renal impairment appears, consider withholding or stopping.",
                "Advise sun protection and regular skin cancer screening.",
            ),
            listOf(cite(SRC_HCTZ, "Precautions — General, Laboratory Tests, Information for Patients")),
        ),
        renal = Facts(
            listOf(
                "Contraindicated in anuria.",
                "Severe renal disease: use with caution — may precipitate azotaemia; cumulative effects with impaired renal function.",
            ),
            listOf(cite(SRC_HCTZ, "Contraindications; Warnings")),
        ),
        hepatic = Facts(
            listOf("Use with caution in impaired hepatic function or progressive liver disease — minor fluid/electrolyte shifts may precipitate hepatic coma."),
            listOf(cite(SRC_HCTZ, "Warnings")),
        ),
        commonEffects = Facts(
            listOf(
                "Electrolyte imbalance (hypokalaemia, hyponatraemia, hypochloraemic alkalosis)",
                "Hyperglycaemia, glycosuria, hyperuricaemia",
                "Hypotension, including orthostatic",
                "Dizziness, vertigo, headache, paraesthesia",
                "Nausea, vomiting, diarrhoea, cramping, anorexia",
                "Weakness, muscle spasm",
                "Photosensitivity, rash, urticaria",
                "Impotence",
            ),
            listOf(cite(SRC_HCTZ, "Adverse Reactions — frequencies not stated")),
        ),
        seriousEffects = Facts(
            listOf(
                "Non-melanoma skin cancer, mainly squamous cell, with large cumulative doses (white patients ≥50,000 mg: ~1 extra case per 6,700 patients/year).",
                "Acute myopia and secondary angle-closure glaucoma within hours to weeks — stop the drug promptly.",
                "Hypokalaemia (arrhythmia, ↑ digitalis toxicity), hyponatraemia, hypomagnesaemia, slight hypercalcaemia.",
                "Hyperuricaemia or gout; hyperglycaemia (latent diabetes may appear); raised cholesterol and triglycerides.",
                "Azotaemia in renal disease; hepatic coma in liver disease.",
                "Sensitivity reactions; exacerbation or activation of SLE.",
                "Reported: aplastic anaemia, agranulocytosis, pancreatitis, Stevens-Johnson syndrome, toxic epidermal necrolysis.",
            ),
            listOf(cite(SRC_HCTZ, "Warnings; Precautions — General; Adverse Reactions incl. Postmarketing")),
        ),
        pk = Pharmacokinetics(
            distribution = "Crosses the placenta but not the blood–brain barrier; excreted in breast milk.",
            metabolism = "Not metabolised.",
            elimination = "Rapidly eliminated by the kidney; ≥61% of an oral dose unchanged within 24 h.",
            halfLife = "5.6–14.8 h.",
            citations = listOf(cite(SRC_HCTZ, "Clinical Pharmacology — Pharmacokinetics and Metabolism")),
        ),
        interactions = Facts(
            listOf(
                "Lithium: generally should not be given — reduced renal clearance, high risk of lithium toxicity.",
                "NSAIDs: may reduce diuretic, natriuretic and antihypertensive effects — observe closely.",
                "Cholestyramine / colestipol: reduce absorption by up to 85% / 43%.",
                "Corticosteroids, ACTH: intensified electrolyte depletion, especially hypokalaemia.",
                "Antidiabetic drugs: dose adjustment may be needed. Alcohol, barbiturates, narcotics: ↑ orthostatic hypotension.",
            ),
            listOf(cite(SRC_HCTZ, "Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No adequate human studies — use only if clearly needed; crosses the placenta (risk of fetal/neonatal jaundice, thrombocytopenia). Routine use in normal pregnancy is inappropriate."),
            listOf(cite(SRC_HCTZ, "Precautions — Pregnancy; Indications — Use in Pregnancy")),
        ),
        lactation = Facts(
            listOf("Excreted in breast milk; because of potential serious infant reactions, discontinue nursing or the drug."),
            listOf(cite(SRC_HCTZ, "Precautions — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "chlorthalidone",
        name = "Chlorthalidone",
        aliases = listOf("chlortalidone"),
        drugClass = "Thiazide-like diuretic",
        uses = "Hypertension in adults, alone or with other antihypertensives.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Start 12.5 mg or 25 mg once daily with food.",
                "Double every 2–4 weeks as needed; maximum 100 mg once daily (higher doses usually add no benefit).",
            ),
            listOf(cite(SRC_CHLORTHALIDONE, "2.1 Recommended Dosage")),
        ),
        beforeStarting = Facts(
            listOf("Check and correct serum electrolytes."),
            listOf(cite(SRC_CHLORTHALIDONE, "5.2 Electrolyte Abnormalities")),
        ),
        monitoring = Facts(
            listOf(
                "Kidney function periodically; withhold or stop if it falls significantly.",
                "Serum electrolytes periodically.",
                "Blood glucose and lipids; calcium in patients with hypercalcaemia.",
                "Serum lithium if co-prescribed.",
                "Hepatic impairment: fluid and electrolyte balance.",
            ),
            listOf(cite(SRC_CHLORTHALIDONE, "5.1–5.3; 7; 8.6")),
        ),
        renal = Facts(
            listOf(
                "Contraindicated in anuria.",
                "CKD, heart failure or volume depletion: higher risk of acute kidney injury.",
                "Substantially renally excreted — take care with dose selection in renal impairment.",
            ),
            listOf(cite(SRC_CHLORTHALIDONE, "4 Contraindications; 5.1; 8.5")),
        ),
        hepatic = Facts(
            listOf("Minor fluid/electrolyte shifts may precipitate hepatic coma in hepatic impairment or progressive liver disease — monitor."),
            listOf(cite(SRC_CHLORTHALIDONE, "8.6 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Electrolyte abnormalities (most frequently expected)",
                "Metabolic disturbances — hyperglycaemia, hyperuricaemia, raised lipids (most frequently expected)",
                "Anorexia, gastric irritation, nausea, vomiting, cramping, diarrhoea, constipation",
                "Dizziness, paraesthesia, headache",
                "Orthostatic hypotension",
                "Photosensitivity, rash, urticaria",
                "Muscle spasm, weakness, restlessness, impotence",
            ),
            listOf(cite(SRC_CHLORTHALIDONE, "6 Adverse Reactions — frequencies not stated")),
        ),
        seriousEffects = Facts(
            listOf(
                "Acute kidney injury from hypovolaemia; higher risk with CKD, heart failure or volume depletion.",
                "Hypokalaemia (dose-dependent), hyponatraemia, hypochloraemic alkalosis, hypomagnesaemia (can make hypokalaemia hard to correct).",
                "Hyperglycaemia, raised cholesterol/triglycerides, hyperuricaemia or gout, raised serum calcium.",
                "Possible SLE exacerbation or activation (reported with thiazides, not with chlorthalidone).",
                "Rare: agranulocytosis, aplastic anaemia, cholestatic jaundice, pancreatitis, toxic epidermal necrolysis, necrotising vasculitis.",
            ),
            listOf(cite(SRC_CHLORTHALIDONE, "5.1–5.4 Warnings and Precautions; 6")),
        ),
        pk = Pharmacokinetics(
            distribution = "≈75% protein bound (58% to albumin); high affinity for red-cell carbonic anhydrase.",
            metabolism = "Not characterised (no data on unchanged vs metabolite fractions).",
            elimination = "Mainly unchanged in urine; non-renal routes not clarified.",
            halfLife = "≈40–60 h.",
            citations = listOf(cite(SRC_CHLORTHALIDONE, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Lithium: renal clearance reduced → toxicity risk; monitor serum lithium.",
                "Insulin / oral hypoglycaemics: requirements may change; higher oral agent doses may be needed.",
                "Tubocurarine: responsiveness may increase.",
                "Norepinephrine: arterial responsiveness may decrease (not enough to preclude use).",
            ),
            listOf(cite(SRC_CHLORTHALIDONE, "7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No identified risk of major birth defects, but fetal/neonatal jaundice, thrombocytopenia, hypoglycaemia and electrolyte abnormalities are reported with thiazides; not first-line for hypertension in pregnancy."),
            listOf(cite(SRC_CHLORTHALIDONE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk; breastfeeding not recommended because of possible accumulation and serious infant reactions."),
            listOf(cite(SRC_CHLORTHALIDONE, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "amlodipine",
        name = "Amlodipine",
        aliases = listOf("amlodipine besylate"),
        drugClass = "Calcium channel blocker (dihydropyridine)",
        uses = "Hypertension; chronic stable and vasospastic angina; angiographically documented CAD without heart failure or EF <40%.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Hypertension: 5 mg once daily; maximum 10 mg once daily. Start 2.5 mg if small, fragile, elderly, hepatic insufficiency, or added to other antihypertensives.",
                "Titrate every 7–14 days (faster if warranted, with frequent assessment).",
                "Angina or CAD: 5–10 mg once daily (lower dose in elderly or hepatic insufficiency); most need 10 mg.",
            ),
            listOf(cite(SRC_AMLODIPINE, "2.1 Adults")),
        ),
        monitoring = Facts(
            listOf(
                "With CYP3A inhibitors: watch for hypotension and oedema. With CYP3A inducers: monitor BP closely.",
                "With cyclosporine or tacrolimus: frequent trough levels.",
                "With sildenafil: monitor for hypotension.",
            ),
            listOf(cite(SRC_AMLODIPINE, "7.1–7.2 Drug Interactions")),
        ),
        renal = Facts(
            listOf("Renal failure: usual initial dose — pharmacokinetics not significantly affected by renal impairment."),
            listOf(cite(SRC_AMLODIPINE, "12.3 Pharmacokinetics")),
        ),
        hepatic = Facts(
            listOf(
                "Hepatic insufficiency: consider starting 2.5 mg; clearance reduced (AUC ↑40–60%).",
                "Severe hepatic impairment: half-life 56 h — titrate slowly.",
            ),
            listOf(cite(SRC_AMLODIPINE, "2.1; 5.3; 12.3")),
        ),
        commonEffects = Facts(
            listOf(
                "Oedema, dose-related (1.8% at 2.5 mg, 3.0% at 5 mg, 10.8% at 10 mg vs 0.6% placebo); more in women",
                "Fatigue (4.5% vs 2.8%)",
                "Palpitation (up to 4.5% at 10 mg)",
                "Dizziness (up to 3.4%)",
                "Nausea (2.9% vs 1.9%)",
                "Flushing (up to 2.6% at 10 mg)",
                "Abdominal pain (1.6% vs 0.3%)",
                "Somnolence (1.4% vs 0.6%)",
            ),
            listOf(cite(SRC_AMLODIPINE, "6.1 Clinical Trials Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Symptomatic hypotension, particularly with severe aortic stenosis (acute hypotension unlikely).",
                "Worsening angina or acute MI after starting or increasing the dose, especially with severe obstructive CAD.",
                "Slower elimination in severe hepatic impairment — titrate slowly.",
            ),
            listOf(cite(SRC_AMLODIPINE, "5.1–5.3 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Peak 6–12 h; absolute bioavailability 64–90%; not affected by food.",
            distribution = "≈93% protein bound.",
            metabolism = "≈90% converted to inactive metabolites by hepatic metabolism.",
            elimination = "10% of parent drug and 60% of metabolites excreted in urine.",
            halfLife = "30–50 h (56 h in hepatic impairment); steady state after 7–8 days.",
            citations = listOf(cite(SRC_AMLODIPINE, "12.3 Pharmacokinetics"), cite(SRC_AMLODIPINE, "5.3")),
        ),
        interactions = Facts(
            listOf(
                "Moderate/strong CYP3A inhibitors (diltiazem ↑ exposure 60%; itraconazole, clarithromycin): may need dose reduction.",
                "CYP3A inducers: monitor BP closely.",
                "Simvastatin: limit to 20 mg/day (exposure ↑77% with amlodipine 10 mg).",
                "Cyclosporine, tacrolimus: exposure may rise — monitor trough levels, adjust dose.",
                "Sildenafil: monitor for hypotension.",
            ),
            listOf(cite(SRC_AMLODIPINE, "7.1–7.2 Drug Interactions; 12.3")),
        ),
        pregnancy = Facts(
            listOf("Limited post-marketing data are insufficient to inform risk of major birth defects or miscarriage; poorly controlled hypertension carries maternal and fetal risk."),
            listOf(cite(SRC_AMLODIPINE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk (median relative infant dose 4.2%); no adverse effects in breastfed infants observed."),
            listOf(cite(SRC_AMLODIPINE, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "telmisartan",
        name = "Telmisartan",
        drugClass = "Angiotensin II receptor blocker (ARB)",
        uses = "Hypertension.",
        clearance = setOf(Clearance.Biliary),
        boxedWarning = Facts(
            listOf("Fetal toxicity: drugs acting on the renin-angiotensin system can cause fetal injury and death — discontinue as soon as pregnancy is detected."),
            listOf(cite(SRC_TELMISARTAN, "Boxed Warning; 5.1")),
        ),
        dose = Facts(
            listOf(
                "Start 40 mg once daily; range 40–80 mg once daily, with or without food.",
                "Most effect within 2 weeks, maximal by 4 weeks; if more lowering is needed beyond 80 mg, add a diuretic.",
            ),
            listOf(cite(SRC_TELMISARTAN, "2.1 Hypertension")),
        ),
        beforeStarting = Facts(
            listOf("Correct volume or salt depletion (e.g. high-dose diuretics) first, or start a reduced dose under close supervision."),
            listOf(cite(SRC_TELMISARTAN, "5.2 Hypotension")),
        ),
        monitoring = Facts(
            listOf(
                "Consider periodic serum electrolytes (potassium), especially with advanced renal impairment, heart failure, renal replacement therapy or potassium-raising drugs.",
                "Anticipate renal function changes in RAAS-dependent patients (severe heart failure, renal dysfunction, renal artery stenosis).",
                "With other RAS agents: closely monitor BP, renal function and electrolytes.",
                "Dialysis: monitor BP closely (orthostatic hypotension).",
                "Digoxin and lithium levels; renal function with NSAIDs.",
            ),
            listOf(cite(SRC_TELMISARTAN, "2.1; 5.3; 5.5; 5.6; 7")),
        ),
        renal = Facts(
            listOf("No initial dose adjustment needed, including haemodialysis (not removed by haemofiltration)."),
            listOf(cite(SRC_TELMISARTAN, "2.1; 12.3")),
        ),
        hepatic = Facts(
            listOf("Biliary obstruction or hepatic insufficiency: reduced clearance (bioavailability approaches 100%) — start low, titrate slowly, monitor carefully."),
            listOf(cite(SRC_TELMISARTAN, "5.4; 8.6; 12.3")),
        ),
        commonEffects = Facts(
            listOf(
                "Upper respiratory tract infection (7% vs 6% placebo)",
                "Back pain (3% vs 1%)",
                "Sinusitis (3% vs 2%)",
                "Diarrhoea (3% vs 2%)",
                "Pharyngitis (1% vs 0%)",
            ),
            listOf(cite(SRC_TELMISARTAN, "6.1 Clinical Trials Experience — Table 1")),
        ),
        seriousEffects = Facts(
            listOf(
                "Fetal toxicity (boxed warning).",
                "Symptomatic hypotension at initiation in volume- or salt-depleted patients.",
                "Hyperkalaemia, especially with advanced renal impairment, heart failure, dialysis or potassium-raising drugs.",
                "Oliguria, progressive azotaemia, rarely acute renal failure or death in RAAS-dependent patients.",
                "Dual RAS blockade: more hypotension, hyperkalaemia and renal failure without added benefit (ONTARGET).",
                "Angioedema (post-marketing, including fatal); contraindicated after hypersensitivity or angioedema to telmisartan.",
            ),
            listOf(cite(SRC_TELMISARTAN, "Boxed Warning; 4; 5.1–5.6; 6.2")),
        ),
        pk = Pharmacokinetics(
            absorption = "Peak 0.5–1 h; bioavailability dose-dependent (42% at 40 mg, 58% at 160 mg); food slightly lowers AUC.",
            distribution = ">99.5% protein bound; Vd ≈500 L.",
            metabolism = "Conjugated to an inactive acyl glucuronide; CYP450 not involved.",
            elimination = ">97% unchanged in faeces via biliary excretion; <1% in urine.",
            halfLife = "≈24 h.",
            citations = listOf(cite(SRC_TELMISARTAN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Aliskiren: do not combine in diabetes; avoid if GFR <60 mL/min.",
                "ACE inhibitors (dual RAS blockade): generally avoid — telmisartan plus ramipril increased renal dysfunction.",
                "Potassium supplements, potassium-sparing diuretics, salt substitutes: ↑ hyperkalaemia.",
                "Digoxin: peak ↑49%, trough ↑20% — monitor levels when starting, adjusting or stopping.",
                "Lithium: ↑ levels and toxicity — monitor. NSAIDs/COX-2 inhibitors: renal deterioration, blunted BP effect — monitor renal function.",
            ),
            listOf(cite(SRC_TELMISARTAN, "5.3; 5.6; 7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Can cause fetal harm: RAS drugs in the 2nd/3rd trimester reduce fetal renal function and increase fetal and neonatal morbidity and death — stop when pregnancy is detected."),
            listOf(cite(SRC_TELMISARTAN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No human data (present in rat milk); advise not to breastfeed — risk of infant hypotension, hyperkalaemia and renal impairment."),
            listOf(cite(SRC_TELMISARTAN, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "losartan",
        name = "Losartan",
        aliases = listOf("losartan potassium"),
        drugClass = "Angiotensin II receptor blocker (ARB)",
        uses = "Hypertension (adults, children ≥6 years); stroke risk reduction in hypertension with LVH (may not apply to Black patients); diabetic nephropathy in type 2 diabetes with hypertension.",
        clearance = setOf(Clearance.Hepatic, Clearance.Biliary),
        boxedWarning = Facts(
            listOf("Fetal toxicity: drugs acting on the renin-angiotensin system can cause fetal injury and death — discontinue as soon as pregnancy is detected."),
            listOf(cite(SRC_LOSARTAN, "Boxed Warning; 5.1")),
        ),
        dose = Facts(
            listOf(
                "Hypertension: 50 mg once daily; maximum 100 mg once daily. Start 25 mg if possible intravascular depletion (e.g. on diuretics).",
                "Hypertension with LVH: 50 mg; add hydrochlorothiazide 12.5 mg and/or increase to 100 mg, then hydrochlorothiazide 25 mg, by BP response.",
                "Diabetic nephropathy: 50 mg once daily, increased to 100 mg by BP response.",
            ),
            listOf(cite(SRC_LOSARTAN, "2.1–2.3 Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf("Correct volume or salt depletion before starting."),
            listOf(cite(SRC_LOSARTAN, "5.2 Hypotension in Volume- or Salt-Depleted Patients")),
        ),
        monitoring = Facts(
            listOf(
                "Renal function periodically if at risk (renal artery stenosis, CKD, severe heart failure, volume depletion); consider withholding if it falls significantly.",
                "Serum potassium periodically; dose reduction or discontinuation may be needed.",
                "With other RAS agents: closely monitor BP, renal function and electrolytes.",
                "Serum lithium if co-prescribed; renal function with NSAIDs.",
            ),
            listOf(cite(SRC_LOSARTAN, "5.3; 5.4; 7.1–7.4")),
        ),
        renal = Facts(
            listOf("No dose adjustment needed unless also volume depleted (plasma levels of losartan and its active metabolite are higher)."),
            listOf(cite(SRC_LOSARTAN, "8.7 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("Mild-to-moderate impairment: start 25 mg once daily (losartan levels 5×, active metabolite 1.7×). Not studied in severe impairment."),
            listOf(cite(SRC_LOSARTAN, "2.4; 8.8 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Upper respiratory infection (8% vs 7% placebo)",
                "Dizziness (3% vs 2%)",
                "Nasal congestion (2% vs 1%)",
                "Back pain (2% vs 1%)",
                "Diabetic nephropathy trial (≥4%, ≥2% over placebo): asthenia/fatigue, hypotension, diarrhoea, anaemia, hyperkalaemia, hypoglycaemia, UTI",
            ),
            listOf(cite(SRC_LOSARTAN, "6.1 Clinical Trials Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Fetal toxicity (boxed warning).",
                "Symptomatic hypotension after starting in volume- or salt-depleted patients.",
                "Renal function deterioration, including acute renal failure (renal artery stenosis, CKD, severe heart failure, volume depletion).",
                "Hyperkalaemia.",
                "Angioedema (larynx, face, intestine), some after prior ACE-inhibitor angioedema; anaphylaxis (post-marketing).",
            ),
            listOf(cite(SRC_LOSARTAN, "Boxed Warning; 5.1–5.4; 6.2")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability ≈33% (first-pass); peak 1 h, active metabolite 3–4 h; food has minor effect on AUC.",
            distribution = "Highly protein bound (free fraction 1.3%; metabolite 0.2%); Vd ≈34 L (metabolite 12 L).",
            metabolism = "CYP2C9 and CYP3A4; ≈14% converted to the active carboxylic acid metabolite, which gives most of the effect.",
            elimination = "≈35% in urine, ≈60% in faeces (biliary excretion contributes); 4% unchanged and 6% as active metabolite in urine.",
            halfLife = "≈2 h (losartan); 6–9 h (active metabolite).",
            citations = listOf(cite(SRC_LOSARTAN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Potassium-raising drugs: hyperkalaemia — monitor potassium.",
                "Lithium: ↑ levels and toxicity — monitor serum lithium.",
                "NSAIDs incl. COX-2 inhibitors: renal deterioration (elderly, volume-depleted, renal impairment) and reduced BP effect — monitor renal function.",
                "Dual RAS blockade (ACE inhibitor, ARB, aliskiren): ↑ hypotension, syncope, hyperkalaemia, renal failure — generally avoid.",
                "Aliskiren: contraindicated in diabetes; avoid if GFR <60 mL/min.",
            ),
            listOf(cite(SRC_LOSARTAN, "4; 7.1–7.4 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Can cause fetal harm: RAS drugs in the 2nd/3rd trimester reduce fetal renal function and increase fetal and neonatal morbidity and death — stop when pregnancy is detected."),
            listOf(cite(SRC_LOSARTAN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Unknown whether excreted in human milk (significant levels in rat milk); decide whether to stop nursing or the drug."),
            listOf(cite(SRC_LOSARTAN, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "enalapril",
        name = "Enalapril",
        aliases = listOf("enalapril maleate"),
        drugClass = "ACE inhibitor",
        uses = "Hypertension; symptomatic heart failure; asymptomatic left ventricular dysfunction (EF ≤35%).",
        clearance = setOf(Clearance.Renal),
        boxedWarning = Facts(
            listOf("Fetal toxicity: drugs acting on the renin-angiotensin system can cause fetal injury and death — discontinue as soon as pregnancy is detected."),
            listOf(cite(SRC_ENALAPRIL, "Boxed Warning; Warnings — Fetal Toxicity")),
        ),
        dose = Facts(
            listOf(
                "Hypertension: 5 mg once daily if not on a diuretic; usual 10–40 mg/day as one or two doses.",
                "On a diuretic: stop it 2–3 days before if possible; otherwise start 2.5 mg under supervision for ≥2 h and until BP is stable ≥1 h more.",
                "Heart failure: start 2.5 mg; usual 2.5–20 mg twice daily, titrated over days to weeks; maximum 40 mg/day in divided doses.",
            ),
            listOf(cite(SRC_ENALAPRIL, "Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf(
                "Assess renal function in all patients with hypertension or heart failure.",
                "At risk of excessive hypotension (heart failure, hyponatraemia, high-dose diuretics, dialysis, volume/salt depletion): consider stopping or reducing the diuretic, or cautiously increasing salt intake.",
            ),
            listOf(cite(SRC_ENALAPRIL, "Precautions — Impaired Renal Function; Warnings — Hypotension")),
        ),
        monitoring = Facts(
            listOf(
                "Renal artery stenosis: renal function during the first few weeks (BUN/creatinine rose in 20%).",
                "At-risk patients: follow closely for the first 2 weeks and after any enalapril or diuretic dose increase.",
                "Serum potassium frequently if potassium-sparing diuretics, supplements or salt substitutes are used.",
                "Consider periodic WBC counts with collagen vascular disease plus renal disease.",
                "With other RAS agents: closely monitor BP, renal function and electrolytes; serum lithium frequently.",
            ),
            listOf(cite(SRC_ENALAPRIL, "Warnings — Hypotension, Neutropenia/Agranulocytosis; Precautions — Impaired Renal Function, Drug Interactions")),
        ),
        renal = Facts(
            listOf(
                "CrCl >30 mL/min: usual dose (start 5 mg).",
                "CrCl ≤30 mL/min (serum creatinine ≥3 mg/dL): start 2.5 mg once daily; titrate to a maximum of 40 mg/day.",
                "Dialysis: 2.5 mg on dialysis days; adjust non-dialysis days to BP. Enalaprilat is dialysable.",
                "Heart failure with serum creatinine >1.6 mg/dL or sodium <130 mEq/L: start 2.5 mg daily under close supervision.",
            ),
            listOf(cite(SRC_ENALAPRIL, "Dosage and Administration — Renal Impairment; Clinical Pharmacology")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache (5.2% vs 9.1% placebo)",
                "Dizziness (4.3% vs 4.3%)",
                "Fatigue (3.0% vs 2.6%)",
                "Rash (1.4% vs 0.4%)",
                "Diarrhoea (1.4%) and nausea (1.4%)",
                "Cough (1.3% vs 0.9%)",
                "Orthostatic effects (1.2% vs 0%)",
                "Heart failure patients: hypotension 6.7%, syncope 2.2%",
            ),
            listOf(cite(SRC_ENALAPRIL, "Adverse Reactions — Hypertension and Heart Failure")),
        ),
        seriousEffects = Facts(
            listOf(
                "Fetal toxicity (boxed warning).",
                "Angioedema (face, tongue, larynx — may be fatal; or intestinal) at any time; contraindicated after prior ACE-inhibitor, hereditary or idiopathic angioedema.",
                "Anaphylactoid reactions during hymenoptera desensitisation, high-flux dialysis or LDL apheresis.",
                "Excessive hypotension in at-risk patients, sometimes with oliguria, azotaemia or acute renal failure.",
                "Neutropenia/agranulocytosis cannot be excluded; risk higher with renal impairment and collagen vascular disease.",
                "Cholestatic jaundice progressing to fulminant hepatic necrosis (rare) — stop if jaundice or marked enzyme rise.",
                "Hyperkalaemia (>5.7 mEq/L in ~1% with hypertension, 3.8% with heart failure); renal function changes.",
                "Persistent non-productive cough (resolves on stopping).",
            ),
            listOf(cite(SRC_ENALAPRIL, "Boxed Warning; Contraindications; Warnings; Precautions — Impaired Renal Function, Hyperkalemia, Cough")),
        ),
        pk = Pharmacokinetics(
            absorption = "≈60% absorbed; peak 1 h (enalaprilat 3–4 h); food has no effect.",
            metabolism = "Prodrug hydrolysed to the active enalaprilat; no other metabolites.",
            elimination = "Primarily renal; ≈94% recovered in urine and faeces as enalaprilat or enalapril.",
            halfLife = "Enalaprilat effective accumulation half-life 11 h; prolonged when GFR ≤30 mL/min.",
            citations = listOf(cite(SRC_ENALAPRIL, "Clinical Pharmacology — Pharmacokinetics and Metabolism")),
        ),
        interactions = Facts(
            listOf(
                "Neprilysin inhibitors (sacubitril): contraindicated — allow 36 h when switching. mTOR inhibitors: ↑ angioedema.",
                "Dual RAS blockade: avoid; aliskiren contraindicated in diabetes and avoided if GFR <60 mL/min.",
                "Potassium-sparing diuretics, potassium supplements, salt substitutes: significant hyperkalaemia — use cautiously with frequent potassium checks.",
                "Lithium: toxicity reported — monitor lithium frequently.",
                "NSAIDs incl. COX-2 inhibitors: renal deterioration if elderly, volume-depleted or renal impairment; may blunt BP effect.",
            ),
            listOf(cite(SRC_ENALAPRIL, "Contraindications; Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("RAS drugs in the 2nd/3rd trimester reduce fetal renal function and increase fetal and neonatal morbidity and death — stop as soon as pregnancy is detected."),
            listOf(cite(SRC_ENALAPRIL, "Warnings — Fetal Toxicity")),
        ),
        lactation = Facts(
            listOf("Enalapril and enalaprilat are detected in human milk; decide whether to stop nursing or the drug."),
            listOf(cite(SRC_ENALAPRIL, "Precautions — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "ramipril",
        name = "Ramipril",
        drugClass = "ACE inhibitor",
        uses = "Hypertension; reduction of MI, stroke and CV death in patients ≥55 years at high CV risk; heart failure after myocardial infarction.",
        clearance = setOf(Clearance.Renal),
        boxedWarning = Facts(
            listOf("Fetal toxicity: drugs acting on the renin-angiotensin system can cause fetal injury and death — discontinue as soon as pregnancy is detected."),
            listOf(cite(SRC_RAMIPRIL, "Boxed Warning; 5.6")),
        ),
        dose = Facts(
            listOf(
                "Hypertension: start 2.5 mg once daily (not on a diuretic); usual 2.5–20 mg/day as one dose or two equal doses.",
                "CV risk reduction: 2.5 mg once daily for 1 week, 5 mg for 3 weeks, then 10 mg once daily as tolerated.",
                "Heart failure post-MI: 2.5 mg twice daily (1.25 mg twice daily if hypotensive); target 5 mg twice daily, increasing about every 3 weeks.",
            ),
            listOf(cite(SRC_RAMIPRIL, "2.1–2.3 Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf(
                "Establish baseline renal function.",
                "Correct volume and salt depletion; on diuretics, reduce or stop the diuretic or increase salt intake first — otherwise use a lower starting dose.",
            ),
            listOf(cite(SRC_RAMIPRIL, "2.5; 5.5; 7.1")),
        ),
        monitoring = Facts(
            listOf(
                "Renal artery stenosis: renal function during the first few weeks.",
                "Serum potassium with renal insufficiency, diabetes or potassium-raising drugs.",
                "Heart failure: follow closely for the first 2 weeks and after any ramipril or diuretic increase; observe ≥2 h after the first post-MI dose.",
                "Consider WBC counts in collagen-vascular disease, especially with renal impairment.",
                "With other RAS agents: monitor BP, renal function and electrolytes; serum lithium frequently; renal function with NSAIDs.",
            ),
            listOf(cite(SRC_RAMIPRIL, "2.3; 5.3–5.5; 5.7; 5.8; 7")),
        ),
        renal = Facts(
            listOf(
                "CrCl >40 mL/min: usual regimens. Worse impairment: 25% of the usual dose gives full ramiprilat levels.",
                "Hypertension with renal impairment: start 1.25 mg once daily; maximum 5 mg/day.",
                "Heart failure post-MI with renal impairment: start 1.25 mg once daily; maximum 2.5 mg twice daily.",
                "Suspected volume depletion or renal artery stenosis: start 1.25 mg once daily.",
            ),
            listOf(cite(SRC_RAMIPRIL, "2.5 Dosage Adjustment; 8.6")),
        ),
        hepatic = Facts(
            listOf("Hepatic impairment slows activation; ramipril levels may be markedly raised (≈3-fold), ramiprilat peaks unchanged. Not formally studied in hypertensive patients."),
            listOf(cite(SRC_RAMIPRIL, "5.2; 12.3")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache (5.4%) and dizziness (2.2%) — not more frequent than placebo",
                "Fatigue / asthenia (2% vs 1% placebo)",
                "Cough (almost 12% in a 1-year study; ~4% stopped)",
                "Heart failure post-MI: hypotension (11% vs 5%), cough (8% vs 4%), dizziness (4% vs 3%), angina (3% vs 2%)",
            ),
            listOf(cite(SRC_RAMIPRIL, "6.1 Clinical Trials Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Fetal toxicity (boxed warning).",
                "Angioedema (head/neck, may be fatal; or intestinal) — contraindicated after prior ACE-inhibitor, hereditary or idiopathic angioedema.",
                "Anaphylactoid reactions with hymenoptera desensitisation, high-flux dialysis or LDL apheresis.",
                "Cholestatic jaundice progressing to fulminant hepatic necrosis (rare) — stop if jaundice or marked enzyme rise.",
                "Oliguria, azotaemia, rarely acute renal failure (severe heart failure); ↑ BUN/creatinine with renal artery stenosis.",
                "Neutropenia, agranulocytosis (rare), more likely with collagen-vascular disease and renal impairment.",
                "Symptomatic hypotension (volume/salt depletion, heart failure); hyperkalaemia (>5.7 mEq/L in ~1%).",
                "Persistent non-productive cough.",
            ),
            listOf(cite(SRC_RAMIPRIL, "Boxed Warning; 4; 5.1–5.9 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Peak 1 h (ramiprilat 2–4 h); absorption ≥50–60%; food slows the rate but not the extent.",
            distribution = "Protein binding ≈73% (ramipril), ≈56% (ramiprilat).",
            metabolism = "Ester cleavage, mainly in the liver, to active ramiprilat (≈6× more active); other metabolites inactive.",
            elimination = "≈60% in urine, ≈40% in faeces; <2% unchanged in urine.",
            halfLife = "Ramiprilat 13–17 h on repeated dosing (terminal phase >50 h from ACE binding).",
            citations = listOf(cite(SRC_RAMIPRIL, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Neprilysin inhibitors (sacubitril): contraindicated — 36 h gap when switching. mTOR inhibitors (e.g. temsirolimus): ↑ angioedema.",
                "Dual RAS blockade: avoid (telmisartan combination not recommended); aliskiren contraindicated in diabetes, avoid if GFR <60.",
                "Potassium-raising drugs: hyperkalaemia — monitor potassium.",
                "Lithium: toxicity — monitor serum lithium frequently (higher risk with a diuretic).",
                "NSAIDs incl. COX-2 inhibitors: renal deterioration and reduced BP effect — monitor renal function.",
            ),
            listOf(cite(SRC_RAMIPRIL, "4; 5.7; 7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("RAS drugs in the 2nd/3rd trimester reduce fetal renal function and increase fetal and neonatal morbidity and death — discontinue when pregnancy is detected."),
            listOf(cite(SRC_RAMIPRIL, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Undetectable in milk after a single 10 mg dose, but repeated doses may give unpredictable low levels — do not use in nursing mothers."),
            listOf(cite(SRC_RAMIPRIL, "8.3 Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "metoprolol",
        name = "Metoprolol",
        aliases = listOf("metoprolol succinate"),
        drugClass = "Beta-1-selective beta-blocker",
        uses = "Hypertension; angina pectoris; heart failure (to reduce cardiovascular death and heart-failure hospitalisation).",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Succinate extended-release, once daily. Hypertension: 25–100 mg; adjust at weekly or longer intervals; doses >400 mg/day not studied.",
                "Angina: start 100 mg, increase weekly (>400 mg/day not studied). Heart failure: 25 mg (NYHA II) or 12.5 mg (more severe), doubling every 2 weeks up to 200 mg.",
                "Scored tablets may be halved but not crushed or chewed. Switching from immediate-release: same total daily dose.",
            ),
            listOf(cite(SRC_METOPROLOL, "2.1–2.4 Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf(
                "Heart failure: stabilise doses of other heart-failure drugs first.",
                "Phaeochromocytoma: use only after an alpha blocker has been started.",
            ),
            listOf(cite(SRC_METOPROLOL, "2.3; 5.5")),
        ),
        monitoring = Facts(
            listOf(
                "Heart rate (bradycardia, heart block); reduce or stop if severe bradycardia.",
                "Heart failure: close monitoring during up-titration for worsening failure or symptomatic bradycardia.",
                "When stopping: taper over 1–2 weeks and monitor, especially with ischaemic heart disease.",
                "With CYP2D6 inhibitors or catecholamine depleters: monitor closely (hypotension, marked bradycardia).",
                "Hepatic impairment: monitor closely for adverse events.",
            ),
            listOf(cite(SRC_METOPROLOL, "2.3; 5.1; 5.2; 5.4; 7.1–7.2; 8.6")),
        ),
        renal = Facts(
            listOf("No dose reduction needed in chronic renal failure."),
            listOf(cite(SRC_METOPROLOL, "8.7 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("Not studied; blood levels likely to rise substantially — start below usual doses and increase gradually."),
            listOf(cite(SRC_METOPROLOL, "8.6 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Tiredness",
                "Dizziness",
                "Depression",
                "Diarrhoea",
                "Shortness of breath",
                "Bradycardia",
                "Rash",
                "Heart failure (MERIT-HF): dizziness/vertigo 1.8% vs 1.0%, bradycardia 1.5% vs 0.4%",
            ),
            listOf(cite(SRC_METOPROLOL, "6.1 Clinical Trials Experience — most common (>2%) in hypertension and angina")),
        ),
        seriousEffects = Facts(
            listOf(
                "Abrupt cessation: angina exacerbation and MI — taper over 1–2 weeks.",
                "Worsening heart failure during up-titration; contraindicated in decompensated heart failure and cardiogenic shock.",
                "Bradycardia, sinus pause, heart block, cardiac arrest; contraindicated in severe bradycardia, >1st-degree block, sick sinus without a pacemaker.",
                "Bronchospasm — generally avoid in bronchospastic disease; if used, lowest dose with a bronchodilator available.",
                "Phaeochromocytoma: paradoxical BP rise if given without an alpha blocker.",
                "Non-cardiac surgery: starting high-dose ER metoprolol linked to bradycardia, hypotension, stroke and death; do not routinely stop chronic therapy.",
                "Severe or prolonged hypoglycaemia with masked warning signs; masks thyrotoxicosis (abrupt withdrawal may cause thyroid storm).",
                "Aggravates peripheral arterial insufficiency; anaphylaxis may not respond to usual epinephrine doses.",
            ),
            listOf(cite(SRC_METOPROLOL, "4 Contraindications; 5.1–5.10 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability 77% of conventional metoprolol; peaks ¼–½ of immediate-release; food has no significant effect.",
            distribution = "≈12% bound to albumin; crosses the blood–brain barrier (CSF ≈78% of plasma).",
            metabolism = "Mainly CYP2D6 (stereoselective, phenotype-dependent).",
            elimination = "Mainly hepatic biotransformation; <5% unchanged in urine, the rest as inactive metabolites.",
            halfLife = "≈3–7 h.",
            citations = listOf(cite(SRC_METOPROLOL, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Strong CYP2D6 inhibitors (quinidine, fluoxetine, paroxetine, propafenone): about double metoprolol levels, less cardioselectivity — monitor closely.",
                "Digitalis, diltiazem, verapamil, clonidine: ↑ bradycardia risk.",
                "Clonidine: withdraw the beta-blocker several days before gradually stopping clonidine (rebound hypertension).",
                "Catecholamine depleters (reserpine, MAO inhibitors): additive — watch for hypotension or marked bradycardia.",
            ),
            listOf(cite(SRC_METOPROLOL, "7.1–7.3 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Observational data show no drug-associated risk of major birth defects or miscarriage; inconsistent reports of growth restriction and preterm birth; observe neonates for bradycardia, hypoglycaemia, hypotension."),
            listOf(cite(SRC_METOPROLOL, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in milk (relative infant dose 0.5–2%); no infant adverse reactions identified — monitor the infant for bradycardia and listlessness."),
            listOf(cite(SRC_METOPROLOL, "8.2 Lactation")),
        ),
    ),
)
