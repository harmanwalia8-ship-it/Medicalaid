package com.medicalaid.app.content.drugs.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.drugs.Clearance
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.Facts
import com.medicalaid.app.content.drugs.Pharmacokinetics

/*
 * Cardiovascular drugs. Each entry paraphrases one US FDA label (DailyMed); see Metabolic.kt for the rules.
 */

internal val cardiovascularSources = listOf(
    Source(
        "fda-label-atorvastatin", Authority.FDA, "Drug label (DailyMed)",
        "Atorvastatin calcium tablets — Accord Healthcare", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=4e06b20d-cc5d-46eb-991b-5972248de3af",
        status = "Revised Nov 2026",
    ),
    Source(
        "fda-label-rosuvastatin", Authority.FDA, "Drug label (DailyMed)",
        "Rosuvastatin calcium tablets — Novadoz Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=d58f1f63-4bb6-413c-9730-ded3e8078618",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-ezetimibe", Authority.FDA, "Drug label (DailyMed)",
        "Ezetimibe tablets — Marlex Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=109a22f4-a7cb-96c6-e063-6394a90ad7f6",
        status = "Revised Nov 2026",
    ),
    Source(
        "fda-label-clopidogrel", Authority.FDA, "Drug label (DailyMed)",
        "Clopidogrel tablets — ScieGen Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=d03cfc18-c9e4-41f8-b54d-6a7564c32afb",
        status = "Revised Nov 2026",
    ),
    Source(
        "fda-label-furosemide", Authority.FDA, "Drug label (DailyMed)",
        "Furosemide tablets — Granulation Technology", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=462e8917-cbe0-f4f9-e063-6394a90ab4ff",
        status = "Revised Jun 2026",
    ),
    Source(
        "fda-label-spironolactone", Authority.FDA, "Drug label (DailyMed)",
        "Spironolactone tablets — Aurobindo Pharma", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=768563dc-a619-44dc-b97f-1a7e71fd56a0",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-sacubitril-valsartan", Authority.FDA, "Drug label (DailyMed)",
        "Sacubitril and valsartan tablets — Umedica Laboratories", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=3173e592-6f28-4864-9bf0-0ef0452da3de",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-dapagliflozin", Authority.FDA, "Drug label (DailyMed)",
        "Dapagliflozin tablets — AstraZeneca", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=72ad22ae-efe6-4cd6-a302-98aaee423d69",
        status = "Revised Jun 2026",
    ),
    Source(
        "fda-label-empagliflozin", Authority.FDA, "Drug label (DailyMed)",
        "Empagliflozin tablets — Boehringer Ingelheim", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=faf3dd6a-9cd0-39c2-0d2e-232cb3f67565",
        status = "Revised Nov 2026",
    ),
)

private const val CV_ATORVASTATIN = "fda-label-atorvastatin"
private const val CV_ROSUVASTATIN = "fda-label-rosuvastatin"
private const val CV_EZETIMIBE = "fda-label-ezetimibe"
private const val CV_CLOPIDOGREL = "fda-label-clopidogrel"
private const val CV_FUROSEMIDE = "fda-label-furosemide"
private const val CV_SPIRONOLACTONE = "fda-label-spironolactone"
private const val CV_SACUBITRIL_VALSARTAN = "fda-label-sacubitril-valsartan"
private const val CV_DAPAGLIFLOZIN = "fda-label-dapagliflozin"
private const val CV_EMPAGLIFLOZIN = "fda-label-empagliflozin"

internal val cardiovascularDrugs = listOf(
    // ----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "atorvastatin",
        name = "Atorvastatin",
        drugClass = "HMG-CoA reductase inhibitor (statin)",
        uses = "Lowers LDL-C (primary hyperlipidaemia; HeFH/HoFH from age 10) and reduces MI, stroke and revascularisation risk in adults with CHD or multiple risk factors.",
        clearance = setOf(Clearance.Hepatic, Clearance.Biliary),
        dose = Facts(
            listOf(
                "Start 10–20 mg once daily (40 mg if LDL-C reduction >45% is needed); range 10–80 mg once daily, any time, with or without food.",
                "Max 20 mg/day with clarithromycin, itraconazole, saquinavir/ritonavir, darunavir/ritonavir, fosamprenavir, elbasvir/grazoprevir or letermovir; max 40 mg/day with nelfinavir.",
            ),
            listOf(cite(CV_ATORVASTATIN, "2.1–2.2; 2.5 Dosage Modifications")),
        ),
        beforeStarting = Facts(
            listOf("Consider liver enzyme testing before starting."),
            listOf(cite(CV_ATORVASTATIN, "5.3 Hepatic Dysfunction")),
        ),
        monitoring = Facts(
            listOf(
                "LDL-C as early as 4 weeks after starting; adjust dose if needed.",
                "Liver enzymes when clinically indicated; stop promptly if serious liver injury, hyperbilirubinaemia or jaundice.",
                "Muscle pain, tenderness or weakness; stop if CK markedly raised or myopathy suspected. Monitor renal-impairment patients for myopathy.",
                "Digoxin: monitor when co-prescribed (levels rise).",
            ),
            listOf(cite(CV_ATORVASTATIN, "2.1; 5.1; 5.3; 7.3; 8.6")),
        ),
        renal = Facts(
            listOf("No dose adjustment; renal impairment is a risk factor for myopathy — monitor for it."),
            listOf(cite(CV_ATORVASTATIN, "8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf(
                "Contraindicated in acute liver failure or decompensated cirrhosis.",
                "Chronic alcoholic liver disease: Cmax and AUC 4-fold higher in Child-Pugh A; ~16-fold and 11-fold higher in Child-Pugh B.",
                "Heavy alcohol use or a history of liver disease may increase risk of hepatic injury.",
            ),
            listOf(cite(CV_ATORVASTATIN, "4 Contraindications; 5.3; 8.7")),
        ),
        commonEffects = Facts(
            listOf(
                "Nasopharyngitis (8.3% vs 8.2% placebo)",
                "Arthralgia (6.9%)",
                "Diarrhoea (6.8%)",
                "Pain in extremity (6.0%)",
                "Urinary tract infection (5.7%)",
                "Dyspepsia (4.7%)",
                "Nausea (4.0%)",
                "Musculoskeletal pain (3.8%)",
            ),
            listOf(cite(CV_ATORVASTATIN, "6.1 Clinical Trials Experience — Table 1")),
        ),
        seriousEffects = Facts(
            listOf(
                "Myopathy and rhabdomyolysis — risk ↑ with age ≥65, uncontrolled hypothyroidism, renal impairment, interacting drugs and higher doses.",
                "Immune-mediated necrotising myopathy (rare) — stop if suspected.",
                "Hepatic dysfunction: persistent transaminases >3× ULN in ~0.7%; rare fatal and non-fatal hepatic failure.",
                "Increases in HbA1c and fasting glucose.",
                "Haemorrhagic stroke with 80 mg in patients with recent stroke/TIA (2.3% vs 1.4% placebo) — weigh risk with recent haemorrhagic stroke.",
            ),
            listOf(cite(CV_ATORVASTATIN, "5.1–5.5 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Tmax 1–2 h; absolute bioavailability ~14% (first-pass); food slightly lowers absorption without changing LDL-C effect.",
            distribution = "Vd ~381 L; ≥98% protein bound.",
            metabolism = "Extensive, mainly CYP3A4; ortho- and parahydroxylated metabolites are active (~70% of circulating activity).",
            elimination = "Mainly in bile after hepatic and/or extrahepatic metabolism; <2% in urine.",
            halfLife = "≈14 h (HMG-CoA reductase inhibitory activity 20–30 h).",
            citations = listOf(cite(CV_ATORVASTATIN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Cyclosporine, gemfibrozil, tipranavir + ritonavir, glecaprevir + pibrentasvir: not recommended (↑ myopathy/rhabdomyolysis).",
                "Clarithromycin, itraconazole and several HIV/HCV antivirals: dose caps of 20–40 mg (see dose); monitor for myopathy.",
                "Fibrates, niacin ≥1 g/day, colchicine: ↑ myopathy risk — weigh benefit; monitor during initiation and titration.",
                "Rifampin: give at the same time as atorvastatin; delayed dosing markedly lowers atorvastatin levels.",
                "Grapefruit juice >1.2 L/day: avoid.",
            ),
            listOf(cite(CV_ATORVASTATIN, "7 Drug Interactions — Tables 2–4")),
        ),
        pregnancy = Facts(
            listOf("Discontinue when pregnancy is recognised (or weigh ongoing need); may cause fetal harm by mechanism, though statin data have not shown more major malformations."),
            listOf(cite(CV_ATORVASTATIN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No data on presence in human milk; breastfeeding is not recommended during treatment."),
            listOf(cite(CV_ATORVASTATIN, "8.2 Lactation")),
        ),
    ),
    // ----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "rosuvastatin",
        name = "Rosuvastatin",
        drugClass = "HMG-CoA reductase inhibitor (statin)",
        uses = "Lowers LDL-C (hypercholesterolaemia; HeFH from age 8, HoFH from age 7), slows atherosclerosis, and reduces major CV events in adults at increased risk.",
        clearance = setOf(Clearance.Biliary, Clearance.Renal),
        dose = Facts(
            listOf(
                "Adults: 5–40 mg once daily, any time, with or without food; choose by indication, LDL-C and CV risk.",
                "Asian patients: start 5 mg once daily; weigh risks and benefits if not controlled on up to 20 mg.",
                "Max 5 mg/day with cyclosporine, darolutamide, belumosudil or vadadustat; see interactions for other caps.",
            ),
            listOf(cite(CV_ROSUVASTATIN, "2.1–2.4; 2.6 Table 1")),
        ),
        beforeStarting = Facts(
            listOf(
                "Consider liver enzyme testing before starting.",
                "On warfarin: obtain INR before starting.",
            ),
            listOf(cite(CV_ROSUVASTATIN, "5.3 Hepatic Dysfunction; 7.3")),
        ),
        monitoring = Facts(
            listOf(
                "LDL-C as early as 4 weeks after starting; adjust dose if needed.",
                "Liver enzymes when clinically indicated.",
                "Muscle symptoms; stop if CK markedly raised or myopathy suspected. Monitor renal-impairment patients for myopathy.",
                "Warfarin: INR frequently after starting, titration or stopping until stable.",
                "Unexplained persistent proteinuria/haematuria on urinalysis: consider dose reduction.",
            ),
            listOf(cite(CV_ROSUVASTATIN, "2.1; 5.1; 5.3; 5.4; 7.3; 8.6")),
        ),
        renal = Facts(
            listOf(
                "CrCl <30 mL/min/1.73 m² not on haemodialysis: start 5 mg once daily; max 10 mg once daily.",
                "Mild–moderate impairment (CrCl ≥30): no dose adjustment.",
            ),
            listOf(cite(CV_ROSUVASTATIN, "2.5; 8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf(
                "Contraindicated in acute liver failure or decompensated cirrhosis.",
                "Chronic alcoholic liver disease increases exposure; heavy alcohol use or prior liver disease may raise hepatic injury risk.",
            ),
            listOf(cite(CV_ROSUVASTATIN, "4 Contraindications; 8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache (5.5% vs 5.0% placebo)",
                "Nausea (3.4%)",
                "Myalgia (2.8%)",
                "Asthenia (2.7%)",
                "Constipation (2.4%)",
            ),
            listOf(cite(CV_ROSUVASTATIN, "6.1 Clinical Trials Experience — Table 2")),
        ),
        seriousEffects = Facts(
            listOf(
                "Myopathy and rhabdomyolysis — risk ↑ with 40 mg, age ≥65, uncontrolled hypothyroidism, renal impairment, Asian patients and interacting drugs.",
                "Immune-mediated necrotising myopathy (rare) — stop if suspected.",
                "Hepatic dysfunction: transaminases >3× ULN in 1.1% vs 0.5% placebo; rare fatal hepatic failure.",
                "Dipstick proteinuria and microscopic haematuria, more frequent at 40 mg.",
                "Increases in HbA1c and fasting glucose, sometimes above the diabetes threshold.",
            ),
            listOf(cite(CV_ROSUVASTATIN, "5.1–5.5 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Tmax 3–5 h; absolute bioavailability ~20%; food does not affect AUC.",
            distribution = "Vd ~134 L; 88% protein bound, mostly albumin.",
            metabolism = "Limited (~10%); N-desmethyl metabolite via CYP2C9 with lower activity; parent gives >90% of activity.",
            elimination = "Mainly faeces (90%); after an IV dose ~28% of clearance renal, 72% hepatic.",
            halfLife = "≈19 h.",
            citations = listOf(cite(CV_ROSUVASTATIN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Cyclosporine (exposure ↑7-fold), darolutamide, belumosudil, vadadustat: max 5 mg daily.",
                "Gemfibrozil: avoid (if unavoidable start 5 mg, max 10 mg). Fenofibrate, niacin ≥1 g/day, colchicine: ↑ myopathy — weigh benefit.",
                "Ticagrelor, febuxostat, fostamatinib: max 20 mg daily. Several HCV/HIV antivirals: start 5 mg, max 10 mg.",
                "Aluminium/magnesium hydroxide antacids: take rosuvastatin ≥2 h before (exposure ↓50%).",
                "Warfarin: INR may rise significantly — check before starting and frequently until stable.",
            ),
            listOf(cite(CV_ROSUVASTATIN, "7 Drug Interactions — Tables 5–7")),
        ),
        pregnancy = Facts(
            listOf("Discontinue when pregnancy is recognised (or weigh ongoing need); may cause fetal harm by mechanism, though statin data have not shown more major malformations."),
            listOf(cite(CV_ROSUVASTATIN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk (limited case data); breastfeeding is not recommended during treatment."),
            listOf(cite(CV_ROSUVASTATIN, "8.2 Lactation")),
        ),
    ),
    // ----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "ezetimibe",
        name = "Ezetimibe",
        drugClass = "Cholesterol absorption inhibitor",
        uses = "Adjunct to diet to lower LDL-C, with a statin or alone if other LDL-lowering therapy is not possible; also HoFH, mixed hyperlipidaemia (with fenofibrate) and sitosterolaemia.",
        clearance = setOf(Clearance.Hepatic, Clearance.Biliary),
        dose = Facts(
            listOf(
                "10 mg orally once daily, with or without food.",
                "With a bile acid sequestrant: take ≥2 h before or ≥4 h after it.",
            ),
            listOf(cite(CV_EZETIMIBE, "2 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "LDL-C as early as 4 weeks after starting.",
                "Liver enzymes as clinically indicated; consider withdrawal if ALT or AST ≥3× ULN persist.",
                "Cyclosporine levels if co-prescribed.",
            ),
            listOf(cite(CV_EZETIMIBE, "2; 5.2; 7")),
        ),
        renal = Facts(
            listOf("No dose adjustment needed in renal impairment."),
            listOf(cite(CV_EZETIMIBE, "8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("Not recommended in moderate or severe hepatic impairment (Child-Pugh B or C) — exposure increased, effects unknown."),
            listOf(cite(CV_EZETIMIBE, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Upper respiratory tract infection (4.3% vs 2.5% placebo)",
                "Diarrhoea (4.1%)",
                "Arthralgia (3.0%)",
                "Sinusitis (2.8%)",
                "Pain in extremity (2.7%)",
                "Fatigue (2.4%)",
                "Influenza (2.0%)",
            ),
            listOf(cite(CV_EZETIMIBE, "6.1 Clinical Trials Experience — Table 1 (monotherapy)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Liver enzyme rises: consecutive ≥3× ULN in 1.3% with a statin vs 0.4% with statin alone.",
                "Myopathy and rhabdomyolysis (mostly with a statin or fibrate) — stop if suspected.",
                "In combination, the warnings of the statin, fenofibrate or other LDL-C therapy also apply.",
            ),
            listOf(cite(CV_EZETIMIBE, "5.1–5.3 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Rapidly conjugated to active ezetimibe-glucuronide (Tmax 1–2 h; parent 4–12 h); food does not change extent.",
            distribution = ">90% protein bound (ezetimibe and glucuronide).",
            metabolism = "Glucuronidation in small intestine and liver; minimal oxidative metabolism; enterohepatic recycling.",
            elimination = "~78% faeces (mainly parent), ~11% urine (mainly glucuronide).",
            halfLife = "≈22 h (ezetimibe and glucuronide).",
            citations = listOf(cite(CV_EZETIMIBE, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Cyclosporine: raises levels of both drugs (more in severe renal insufficiency) — monitor cyclosporine.",
                "Fibrates other than fenofibrate: not recommended. With fenofibrate: gallbladder studies if cholelithiasis suspected.",
                "Bile acid sequestrants (cholestyramine): ↓ ezetimibe exposure — separate doses.",
            ),
            listOf(cite(CV_EZETIMIBE, "7 Drug Interactions — Table 3")),
        ),
        pregnancy = Facts(
            listOf("Insufficient human data; no adverse developmental effects in animal studies — use only if benefit justifies risk."),
            listOf(cite(CV_EZETIMIBE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No human milk data (present in rat milk); avoid while nursing unless benefit justifies infant risk."),
            listOf(cite(CV_EZETIMIBE, "8.2 Lactation")),
        ),
    ),
    // ----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "clopidogrel",
        name = "Clopidogrel",
        drugClass = "P2Y12 platelet inhibitor (thienopyridine)",
        uses = "Reduces MI and stroke in ACS (NSTE-ACS; medically managed STEMI; with aspirin) and in recent MI, recent stroke or established peripheral arterial disease.",
        clearance = setOf(Clearance.Hepatic),
        boxedWarning = Facts(
            listOf(
                "Diminished antiplatelet effect in CYP2C19 poor metabolisers (two loss-of-function alleles). Tests are available; consider another P2Y12 inhibitor in poor metabolisers.",
            ),
            listOf(cite(CV_CLOPIDOGREL, "Boxed Warning; 5.1")),
        ),
        dose = Facts(
            listOf(
                "ACS: 300 mg loading dose, then 75 mg once daily, with aspirin. Without a loading dose, antiplatelet effect is delayed by several days.",
                "Recent MI, recent stroke or PAD: 75 mg once daily, no loading dose.",
            ),
            listOf(cite(CV_CLOPIDOGREL, "1.1; 2.1–2.2")),
        ),
        monitoring = Facts(
            listOf(
                "Promptly evaluate any signs or symptoms of blood loss, especially with other antiplatelet agents.",
                "With repaglinide (if unavoidable): more frequent glucose monitoring may be needed.",
            ),
            listOf(cite(CV_CLOPIDOGREL, "7.7; 7.8")),
        ),
        hepatic = Facts(
            listOf("No dose adjustment needed in hepatic impairment."),
            listOf(cite(CV_CLOPIDOGREL, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Bleeding — the most commonly reported reaction, including life-threatening and fatal bleeding.",
                "CURE (with aspirin): major bleeding 3.7% vs 2.7% placebo; minor bleeding 5.1% vs 2.4%.",
                "Epistaxis, haematuria, bruising and haematoma more frequent than comparator.",
                "CAPRIE: GI haemorrhage 2.0% vs 2.7% with aspirin.",
                "Pruritus (more frequent than with aspirin in CAPRIE).",
            ),
            listOf(cite(CV_CLOPIDOGREL, "6.1 Clinical Trials Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Reduced effect in CYP2C19 poor metabolisers (boxed warning) or with omeprazole/esomeprazole.",
                "Bleeding, including fatal; platelet inhibition lasts the platelet lifespan (7–10 days).",
                "Premature discontinuation ↑ CV events; if possible stop 5 days before high-bleeding-risk surgery and restart once haemostasis is achieved.",
                "Thrombotic thrombocytopenic purpura, sometimes fatal, even after <2 weeks — urgent plasmapheresis.",
                "Hypersensitivity cross-reactivity among thienopyridines (rash, angioedema, haematological reactions).",
            ),
            listOf(cite(CV_CLOPIDOGREL, "Boxed Warning; 5.1–5.5")),
        ),
        pk = Pharmacokinetics(
            absorption = "Rapid; ≥50% absorbed. Food cuts active-metabolite Cmax 57% but not AUC — take with or without food.",
            metabolism = "Prodrug: ~85% hydrolysed by esterases to inactive acid; active thiol metabolite formed mainly by CYP2C19 (also 1A2, 2B6, 3A).",
            elimination = "~50% urine and ~46% faeces (radiolabel, over 5 days).",
            halfLife = "Clopidogrel ≈6 h; active metabolite ≈30 min.",
            citations = listOf(cite(CV_CLOPIDOGREL, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Omeprazole, esomeprazole: avoid — markedly reduce antiplatelet effect.",
                "Strong CYP2C19 inducers (e.g. rifampin): avoid — ↑ active metabolite and bleeding risk.",
                "Opioids: delay and reduce absorption — consider a parenteral antiplatelet in ACS needing opioids.",
                "NSAIDs, warfarin, SSRIs/SNRIs, other antiplatelets: ↑ bleeding.",
                "Repaglinide: exposure ↑3.9–5.1-fold — avoid; if unavoidable start 0.5 mg before meals, max 4 mg/day.",
            ),
            listOf(cite(CV_CLOPIDOGREL, "7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Published and postmarketing data have not identified drug-associated risks of major birth defects or miscarriage; MI and stroke carry maternal and fetal risk."),
            listOf(cite(CV_CLOPIDOGREL, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No human milk data; no infant adverse effects in a few postmarketing cases — weigh breastfeeding benefits against the mother's need."),
            listOf(cite(CV_CLOPIDOGREL, "8.2 Lactation")),
        ),
    ),
    // ----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "furosemide",
        name = "Furosemide",
        aliases = listOf("frusemide"),
        drugClass = "Loop diuretic",
        uses = "Oedema from heart failure, cirrhosis or renal disease (incl. nephrotic syndrome) in adults and children; hypertension in adults (oral).",
        clearance = setOf(Clearance.Renal),
        boxedWarning = Facts(
            listOf(
                "Potent diuretic: excess doses can cause profound diuresis with water and electrolyte depletion — careful supervision; individualise dose and schedule.",
            ),
            listOf(cite(CV_FUROSEMIDE, "WARNING (opening statement)")),
        ),
        dose = Facts(
            listOf(
                "Oedema: 20–80 mg as a single dose; repeat or raise by 20–40 mg no sooner than 6–8 h until effect, then give once or twice daily.",
                "Severe oedema: may titrate carefully up to 600 mg/day; dosing on 2–4 consecutive days each week is an option.",
                "Hypertension: 80 mg/day, usually 40 mg twice daily; reduce other antihypertensives by ≥50% when adding furosemide.",
            ),
            listOf(cite(CV_FUROSEMIDE, "Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Electrolytes (especially potassium), CO₂, creatinine and BUN frequently in the first few months, then periodically.",
                "Calcium and magnesium periodically; urine and blood glucose periodically in diabetics.",
                "Signs of fluid/electrolyte imbalance; blood pressure closely with other antihypertensives.",
                "Doses >80 mg/day for prolonged periods: careful clinical and laboratory monitoring.",
            ),
            listOf(cite(CV_FUROSEMIDE, "Precautions: General, Laboratory Tests; Dosage and Administration")),
        ),
        renal = Facts(
            listOf(
                "Contraindicated in anuria.",
                "Severe progressive renal disease: stop if azotaemia and oliguria increase.",
                "Ototoxicity risk higher with severe renal impairment. Elderly: monitoring renal function may be useful.",
            ),
            listOf(cite(CV_FUROSEMIDE, "Contraindications; Warnings; Precautions: Geriatric Use")),
        ),
        hepatic = Facts(
            listOf(
                "Cirrhosis with ascites: best started in hospital, with strict observation — sudden fluid/electrolyte shifts may precipitate hepatic coma.",
                "Hepatic coma or electrolyte depletion: do not start until improved.",
                "Potassium supplements and, if needed, an aldosterone antagonist help prevent hypokalaemia and metabolic alkalosis.",
            ),
            listOf(cite(CV_FUROSEMIDE, "Warnings")),
        ),
        commonEffects = Facts(
            listOf(
                "Electrolyte depletion: hypokalaemia, hyponatraemia, hypochloraemic alkalosis, hypomagnesaemia, hypocalcaemia.",
                "Orthostatic hypotension (aggravated by alcohol, barbiturates, narcotics).",
                "Anorexia, nausea, vomiting, diarrhoea, constipation, cramping.",
                "Dizziness, vertigo, headache, paraesthesia, blurred vision.",
                "Hyperglycaemia, glycosuria, hyperuricaemia; raised cholesterol and triglycerides.",
                "Muscle spasm, weakness.",
                "Rash, pruritus, urticaria, photosensitivity.",
                "Frequencies are not given in the label.",
            ),
            listOf(cite(CV_FUROSEMIDE, "Adverse Reactions; Precautions: General")),
        ),
        seriousEffects = Facts(
            listOf(
                "Profound diuresis: dehydration, circulatory collapse, possible thrombosis and embolism, especially in the elderly.",
                "Ototoxicity: tinnitus, reversible or irreversible hearing loss (high doses, severe renal impairment, hypoproteinaemia, other ototoxic drugs).",
                "Hepatic coma in cirrhosis with ascites.",
                "Acute urinary retention with bladder outflow obstruction; worse renal function after radiocontrast in high-risk patients.",
                "Severe skin reactions (SJS, TEN, DRESS, AGEP), anaphylaxis, blood dyscrasias, pancreatitis.",
                "Possible sulfonamide cross-allergy; may exacerbate SLE; gout rarely.",
            ),
            listOf(cite(CV_FUROSEMIDE, "WARNING; Warnings; Precautions: General; Adverse Reactions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Tablet bioavailability ~64% of IV; diuresis starts within 1 h, peaks in 1–2 h, lasts 6–8 h.",
            distribution = "91–99% protein bound, mainly albumin (unbound 2.3–4.1%).",
            metabolism = "Furosemide glucuronide is the only or major biotransformation product.",
            elimination = "Predominantly excreted unchanged in urine.",
            halfLife = "≈2 h (terminal).",
            citations = listOf(cite(CV_FUROSEMIDE, "Clinical Pharmacology")),
        ),
        interactions = Facts(
            listOf(
                "Aminoglycosides, cisplatin: ↑ ototoxicity — avoid aminoglycosides except when life-threatening. Do not combine with ethacrynic acid.",
                "Lithium: generally do not combine — high risk of lithium toxicity.",
                "ACE inhibitors or ARBs: severe hypotension and worsening renal function possible — may need dose interruption or reduction.",
                "NSAIDs, indomethacin: reduced natriuretic and antihypertensive effect; rises in BUN, creatinine and potassium reported.",
                "Sucralfate: separate by ≥2 h. High-dose salicylates: salicylate toxicity at lower doses.",
            ),
            listOf(cite(CV_FUROSEMIDE, "Precautions: Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No adequate human studies; maternal deaths and abortions in rabbits — use only if benefit justifies fetal risk, and monitor fetal growth."),
            listOf(cite(CV_FUROSEMIDE, "Precautions: Pregnancy")),
        ),
        lactation = Facts(
            listOf("Appears in breast milk — use with caution; may inhibit lactation."),
            listOf(cite(CV_FUROSEMIDE, "Precautions: Nursing Mothers")),
        ),
    ),
    // ----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "spironolactone",
        name = "Spironolactone",
        drugClass = "Aldosterone antagonist (potassium-sparing diuretic)",
        uses = "HFrEF (NYHA III–IV) to improve survival and cut hospitalisation; add-on for hypertension; oedema in cirrhosis or nephrotic syndrome; primary hyperaldosteronism.",
        clearance = setOf(Clearance.Hepatic, Clearance.Renal),
        dose = Facts(
            listOf(
                "Heart failure (K ≤5.0 mEq/L, eGFR >50): 25 mg once daily, may increase to 50 mg; if hyperkalaemia on 25 mg, reduce to 25 mg every other day.",
                "Hypertension: 25–100 mg/day in single or divided doses, titrated at 2-week intervals; >100 mg/day generally adds little.",
                "Oedema: 100 mg/day (range 25–200 mg); in cirrhosis start in hospital and titrate slowly. Hyperaldosteronism before surgery: 100–400 mg/day.",
            ),
            listOf(cite(CV_SPIRONOLACTONE, "2.2–2.5 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Serum potassium within 1 week of starting or titrating, then regularly; more often with renal impairment or other potassium-raising drugs.",
                "Volume status and renal function periodically.",
                "Electrolytes, uric acid and blood glucose periodically.",
                "Potassium when ACEi/ARB therapy changes; lithium levels if co-prescribed.",
            ),
            listOf(cite(CV_SPIRONOLACTONE, "5.1–5.3; 7.1–7.2")),
        ),
        renal = Facts(
            listOf(
                "Heart failure with eGFR 30–50 mL/min/1.73 m²: consider starting 25 mg every other day (hyperkalaemia risk).",
                "Renal impairment increases hyperkalaemia risk — monitor potassium closely.",
            ),
            listOf(cite(CV_SPIRONOLACTONE, "2.2; 8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("Cirrhosis with ascites: start in hospital at the lowest dose and titrate slowly — sudden fluid/electrolyte shifts may precipitate encephalopathy or coma."),
            listOf(cite(CV_SPIRONOLACTONE, "2.4; 8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Gynaecomastia — the most common reaction (~9% of men on mean 26 mg/day in a heart failure trial).",
                "Diarrhoea, cramping, nausea, vomiting, gastritis.",
                "Decreased libido, erectile dysfunction, irregular menses or amenorrhoea, breast and nipple pain.",
                "Leg cramps.",
                "Lethargy, confusion, ataxia, dizziness, headache, drowsiness.",
                "Rash, urticaria, pruritus, alopecia.",
                "Other frequencies are not given in the label.",
            ),
            listOf(cite(CV_SPIRONOLACTONE, "5.4; 6 Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "Hyperkalaemia — risk ↑ with renal impairment, potassium supplements/salt substitutes, ACEi/ARB. Contraindicated with hyperkalaemia, Addison's disease or eplerenone.",
                "Hypotension and worsening renal function from excess diuresis, especially with ACEi/ARB or nephrotoxic drugs.",
                "Hyponatraemia, hypomagnesaemia, hypocalcaemia, hypochloraemic alkalosis, hyperglycaemia, hyperuricaemia/gout.",
                "Gynaecomastia — dose-dependent; onset from 1–2 months to over a year; usually reversible.",
                "Also reported: agranulocytosis, SJS/TEN/DRESS, very few cases of mixed cholestatic/hepatocellular toxicity (one fatal).",
            ),
            listOf(cite(CV_SPIRONOLACTONE, "4 Contraindications; 5.1–5.4; 6")),
        ),
        pk = Pharmacokinetics(
            absorption = "Tmax 2.6 h (canrenone 4.3 h); food increases bioavailability ~95% — take consistently with respect to meals.",
            distribution = ">90% protein bound (with metabolites).",
            metabolism = "Rapid and extensive; metabolites include active canrenone, TMS and HTMS.",
            elimination = "Metabolites excreted mainly in urine, secondarily in bile.",
            halfLife = "Spironolactone 1.4 h; canrenone 16.5 h, TMS 13.8 h, HTMS 15 h.",
            citations = listOf(cite(CV_SPIRONOLACTONE, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Potassium supplements, ACEi, ARBs, NSAIDs, heparin/LMWH, trimethoprim: severe hyperkalaemia possible — usually stop potassium supplements in heart failure.",
                "Eplerenone: contraindicated together.",
                "Lithium: renal clearance ↓ — toxicity risk; monitor levels.",
                "NSAIDs or aspirin may reduce its effect. Cholestyramine: hyperkalaemic metabolic acidosis reported.",
                "Digoxin radioimmunoassays: interference — use a non-interacting assay. Abiraterone: not recommended; mitotane: avoid.",
            ),
            listOf(cite(CV_SPIRONOLACTONE, "4; 7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("May affect male sexual differentiation (anti-androgenic; animal data); avoid in pregnancy or advise of the potential risk to a male fetus."),
            listOf(cite(CV_SPIRONOLACTONE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Active metabolite canrenone found in milk in low amounts; long-term infant effects unknown — weigh breastfeeding benefits against need."),
            listOf(cite(CV_SPIRONOLACTONE, "8.2 Lactation")),
        ),
    ),
    // ----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "sacubitril-valsartan",
        name = "Sacubitril–valsartan",
        aliases = listOf("sacubitril/valsartan", "sacubitril and valsartan"),
        drugClass = "Neprilysin inhibitor + angiotensin II receptor blocker",
        uses = "Chronic heart failure in adults (benefit clearest with LVEF below normal) to reduce CV death and HF hospitalisation; symptomatic HF with systemic LV systolic dysfunction in children ≥1 year.",
        clearance = setOf(Clearance.Renal, Clearance.Biliary),
        boxedWarning = Facts(
            listOf("Fetal toxicity: drugs acting on the renin-angiotensin system can injure or kill the fetus — discontinue as soon as pregnancy is detected."),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "Boxed Warning; 5.1")),
        ),
        dose = Facts(
            listOf(
                "Adults: start 49/51 mg twice daily; double after 2–4 weeks to target 97/103 mg twice daily as tolerated.",
                "Start at half the usual starting dose if not on an ACEi/ARB or on low doses, eGFR <30, or Child-Pugh B.",
                "Contraindicated with an ACE inhibitor; allow a 36-hour washout when switching from or to an ACEi.",
            ),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "2.1–2.2; 2.6–2.8")),
        ),
        beforeStarting = Facts(
            listOf("Correct volume or salt depletion before starting, or start at a lower dose."),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "5.3 Hypotension")),
        ),
        monitoring = Facts(
            listOf(
                "Serum creatinine closely; down-titrate or interrupt if renal function falls significantly. Monitor renal function in renal artery stenosis.",
                "Serum potassium periodically, especially with severe renal impairment, diabetes, hypoaldosteronism or high-potassium diet.",
                "Signs of angioedema and hypotension.",
                "Lithium levels if co-prescribed; renal function periodically with NSAIDs.",
            ),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "5.2–5.5; 7.3–7.4")),
        ),
        renal = Facts(
            listOf(
                "eGFR <30 mL/min/1.73 m²: start at half the usual starting dose, then titrate.",
                "eGFR 30–90: no starting dose adjustment.",
                "Aliskiren: avoid if eGFR <60.",
            ),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "2.7; 7.1; 8.7 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf(
                "Child-Pugh A: no adjustment. Child-Pugh B: start at half the usual starting dose.",
                "Child-Pugh C: not recommended (not studied).",
            ),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "2.8; 8.6 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Hypotension (18% vs 12% enalapril)",
                "Hyperkalaemia (12% vs 14%)",
                "Cough (9% vs 13%)",
                "Dizziness (6% vs 5%)",
                "Renal failure / acute renal failure (5% vs 5%)",
                "Orthostasis (2.1% vs 1.1%)",
                "Falls (1.9% vs 1.3%)",
            ),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "6.1 Clinical Trials Experience — Table 3 (PARADIGM-HF)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Fetal toxicity (boxed warning).",
                "Angioedema (0.5% vs 0.2% enalapril; 2.4% in Black patients) — stop permanently. Contraindicated after ACEi/ARB angioedema; avoid in hereditary angioedema.",
                "Symptomatic hypotension, especially with volume or salt depletion.",
                "Impaired renal function (oliguria, azotaemia, rarely acute renal failure) in RAAS-dependent patients.",
                "Hyperkalaemia.",
            ),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "Boxed Warning; 4; 5.1–5.5; 6.1")),
        ),
        pk = Pharmacokinetics(
            absorption = "Tmax 0.5 h (sacubitril), 2 h (LBQ657), 1.5 h (valsartan); with or without food. Valsartan 26/51/103 mg ≈ 40/80/160 mg in other tablets.",
            distribution = "94–97% protein bound; Vd valsartan 75 L, sacubitril 103 L.",
            metabolism = "Sacubitril → active LBQ657 by esterases (little further metabolism); valsartan minimally metabolised (~20%).",
            elimination = "Sacubitril (as LBQ657) 52–68% urine, 37–48% faeces; valsartan ~13% urine, 86% faeces.",
            halfLife = "Sacubitril 1.4 h; LBQ657 11.5 h; valsartan 9.9 h.",
            citations = listOf(cite(CV_SACUBITRIL_VALSARTAN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "ACE inhibitors: contraindicated (angioedema) — not within 36 h of switching.",
                "ARBs: avoid (contains valsartan). Aliskiren: contraindicated in diabetes; avoid if eGFR <60.",
                "Potassium-sparing diuretics, potassium supplements, potassium salt substitutes: ↑ serum potassium.",
                "NSAIDs incl. COX-2 inhibitors: worsening renal function in elderly, volume-depleted or renally impaired patients — monitor.",
                "Lithium: ↑ levels and toxicity — monitor lithium.",
            ),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "4; 7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Can cause fetal harm: RAS-acting drugs in the 2nd and 3rd trimesters reduce fetal renal function and increase fetal and neonatal morbidity and death."),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No human data (present in rat milk); breastfeeding is not recommended during treatment."),
            listOf(cite(CV_SACUBITRIL_VALSARTAN, "8.2 Lactation")),
        ),
    ),
    // ----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "dapagliflozin",
        name = "Dapagliflozin",
        drugClass = "SGLT2 inhibitor",
        uses = "Adults with heart failure or CKD at risk of progression (cardiorenal outcomes); type 2 diabetes — HF-hospitalisation risk reduction and glycaemic control (from age 10).",
        clearance = setOf(Clearance.Hepatic, Clearance.Renal),
        dose = Facts(
            listOf(
                "Heart failure, CKD, or T2DM with CV disease/risk factors: 10 mg once daily.",
                "Glycaemic control in T2DM: start 5 mg once daily; may increase to 10 mg once daily.",
                "Withhold ≥3 days before surgery or procedures with prolonged fasting; resume when stable and eating.",
            ),
            listOf(cite(CV_DAPAGLIFLOZIN, "2.2–2.4")),
        ),
        beforeStarting = Facts(
            listOf(
                "Assess renal function.",
                "Assess volume status; correct volume depletion first.",
            ),
            listOf(cite(CV_DAPAGLIFLOZIN, "2.1 Testing Prior to Initiation; 5.2")),
        ),
        monitoring = Facts(
            listOf(
                "Renal function as clinically indicated, and after starting in those at risk of volume depletion; watch for hypotension.",
                "Consider ketone monitoring if at risk of ketoacidosis; assess for ketoacidosis whatever the glucose level.",
                "Genitourinary infections; genital or perineal pain, redness or swelling with fever — evaluate for Fournier's gangrene.",
                "Do not monitor glycaemia with urine glucose or 1,5-AG. Lithium levels more often at initiation and dose changes.",
            ),
            listOf(cite(CV_DAPAGLIFLOZIN, "2.1; 5.1–5.3; 7")),
        ),
        renal = Facts(
            listOf(
                "HF, CKD or CV-risk indications: initiation not recommended if eGFR <25 mL/min/1.73 m²; if eGFR falls below 25 on treatment, may continue 10 mg.",
                "Glycaemic control: not recommended if eGFR <45 (likely ineffective); eGFR ≥45 needs no adjustment.",
                "Not recommended for CKD in polycystic kidney disease or with recent immunosuppressive therapy for kidney disease.",
            ),
            listOf(cite(CV_DAPAGLIFLOZIN, "1 Limitations of Use; 2.2–2.3; 8.6")),
        ),
        hepatic = Facts(
            listOf("No dose adjustment in mild, moderate or severe impairment; assess benefit-risk individually in severe impairment (not studied)."),
            listOf(cite(CV_DAPAGLIFLOZIN, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Female genital mycotic infections (6.9% vs 1.5% placebo)",
                "Nasopharyngitis (6.3%)",
                "Urinary tract infections (4.3%)",
                "Back pain (4.2%)",
                "Increased urination (3.8%)",
                "Male genital mycotic infections (2.7%)",
                "Nausea (2.5%)",
                "Dyslipidaemia (2.5%)",
            ),
            listOf(cite(CV_DAPAGLIFLOZIN, "6.1 Clinical Trials Experience — Table 1 (10 mg)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Ketoacidosis in type 1 and type 2 diabetes, glucose possibly <250 mg/dL — stop if suspected; withhold when predisposed (surgery, fasting).",
                "Volume depletion: hypotension, acute kidney injury — risk ↑ with eGFR <60, older age, loop diuretics.",
                "Urosepsis, pyelonephritis, genital mycotic infections, Fournier's gangrene (necrotising fasciitis of the perineum).",
                "Hypoglycaemia with insulin or insulin secretagogues — consider lowering their dose.",
            ),
            listOf(cite(CV_DAPAGLIFLOZIN, "5.1–5.4 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability 78% (10 mg); Tmax ~2 h fasting; high-fat meal lowers Cmax up to 50%, AUC unchanged.",
            distribution = "~91% protein bound.",
            metabolism = "Mainly UGT1A9 to inactive 3-O-glucuronide; CYP pathways minor.",
            elimination = "Mainly renal: 75% urine (<2% unchanged), 21% faeces (~15% unchanged).",
            halfLife = "≈12.9 h (10 mg).",
            citations = listOf(cite(CV_DAPAGLIFLOZIN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Insulin or insulin secretagogues (e.g. sulfonylureas): ↑ hypoglycaemia — may need lower doses.",
                "Lithium: serum levels may fall — monitor more often at initiation and dose changes.",
                "Urine glucose tests read positive and 1,5-AG assay is unreliable — use other glycaemic monitoring.",
            ),
            listOf(cite(CV_DAPAGLIFLOZIN, "7 Drug Interactions — Table 4")),
        ),
        pregnancy = Facts(
            listOf("Not recommended in the 2nd and 3rd trimesters (renal effects in animal studies); human data too limited to assess birth-defect or miscarriage risk."),
            listOf(cite(CV_DAPAGLIFLOZIN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No human data; possible risk to the developing infant kidney — not recommended while breastfeeding."),
            listOf(cite(CV_DAPAGLIFLOZIN, "8.2 Lactation")),
        ),
    ),
    // ----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "empagliflozin",
        name = "Empagliflozin",
        drugClass = "SGLT2 inhibitor",
        uses = "Adults with heart failure or CKD at risk of progression; CV-death reduction in T2DM with established CV disease; glycaemic control in T2DM (from age 10).",
        clearance = setOf(Clearance.Renal, Clearance.Biliary),
        dose = Facts(
            listOf(
                "10 mg once daily in the morning, with or without food, for all indications.",
                "Glycaemic control: may increase to 25 mg once daily if 10 mg is tolerated.",
                "Withhold ≥3 days before surgery or procedures with prolonged fasting; resume when stable and eating.",
            ),
            listOf(cite(CV_EMPAGLIFLOZIN, "2.2–2.3")),
        ),
        beforeStarting = Facts(
            listOf(
                "Assess renal function.",
                "Assess volume status; correct volume depletion first.",
            ),
            listOf(cite(CV_EMPAGLIFLOZIN, "2.1 Testing Prior to Initiation; 5.2")),
        ),
        monitoring = Facts(
            listOf(
                "Renal function as clinically indicated, and after starting in those at risk; signs of volume depletion.",
                "Consider ketone monitoring if at risk of ketoacidosis; assess for ketoacidosis whatever the glucose level.",
                "Genitourinary infections; lower-limb infection, sores, ulcers or new pain (amputation risk).",
                "Do not monitor glycaemia with urine glucose or 1,5-AG. Lithium levels more often at initiation and dose changes.",
            ),
            listOf(cite(CV_EMPAGLIFLOZIN, "2.1; 5.1–5.3; 5.5; 7")),
        ),
        renal = Facts(
            listOf(
                "Glycaemic control: not recommended if eGFR <30 mL/min/1.73 m² (likely ineffective).",
                "Outcome trials did not enrol eGFR <20 or dialysis; enrolled patients could continue if eGFR fell below 20 or dialysis began.",
                "Not recommended for CKD in polycystic kidney disease or with recent IV immunosuppression or >45 mg prednisone for kidney disease.",
            ),
            listOf(cite(CV_EMPAGLIFLOZIN, "1 Limitations of Use; 2.1; 8.6")),
        ),
        hepatic = Facts(
            listOf("May be used in hepatic impairment."),
            listOf(cite(CV_EMPAGLIFLOZIN, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Urinary tract infection (9.3% vs 7.6% placebo)",
                "Female genital mycotic infections (5.4%)",
                "Dyslipidaemia (3.9%)",
                "Increased urination (3.4%)",
                "Male genital mycotic infections (3.1%)",
                "Arthralgia (2.4%)",
                "Nausea (2.3%)",
            ),
            listOf(cite(CV_EMPAGLIFLOZIN, "6.1 Clinical Trials Experience — Table 2 (10 mg)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Ketoacidosis in type 1 and type 2 diabetes, glucose possibly <250 mg/dL — stop if suspected; withhold when predisposed (surgery, fasting).",
                "Volume depletion: hypotension, acute kidney injury — risk ↑ with eGFR <60, older age, loop diuretics.",
                "Urosepsis, pyelonephritis, genital mycotic infections, Fournier's gangrene.",
                "Hypoglycaemia with insulin or secretagogues (in children ≥10, higher regardless of insulin).",
                "Lower limb amputation (5.0 vs 4.3 per 1,000 patient-years with placebo across outcome trials) — foot care and monitoring.",
                "Serious hypersensitivity, e.g. angioedema.",
            ),
            listOf(cite(CV_EMPAGLIFLOZIN, "5.1–5.6 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Tmax 1.5 h; high-fat meal lowers AUC ~16% — not clinically relevant.",
            distribution = "Vd ~73.8 L; 86.2% protein bound.",
            metabolism = "Glucuronidation (UGT2B7, 1A3, 1A8, 1A9); no major plasma metabolites.",
            elimination = "54.4% urine (about half unchanged), 41.2% faeces (mostly unchanged).",
            halfLife = "≈12.4 h.",
            citations = listOf(cite(CV_EMPAGLIFLOZIN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Diuretics: ↑ volume depletion risk — assess volume and renal function before starting.",
                "Insulin or insulin secretagogues: ↑ hypoglycaemia — may need lower doses.",
                "Lithium: serum levels may fall — monitor more often at initiation and dose changes.",
                "Urine glucose tests read positive and 1,5-AG assay is unreliable — use other glycaemic monitoring.",
            ),
            listOf(cite(CV_EMPAGLIFLOZIN, "7 Drug Interactions — Table 4")),
        ),
        pregnancy = Facts(
            listOf("Not recommended in the 2nd and 3rd trimesters (renal effects in animal studies); human data insufficient to assess birth-defect or miscarriage risk."),
            listOf(cite(CV_EMPAGLIFLOZIN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Limited human data; possible effect on postnatal kidney development — not recommended while breastfeeding."),
            listOf(cite(CV_EMPAGLIFLOZIN, "8.2 Lactation")),
        ),
    ),
)
