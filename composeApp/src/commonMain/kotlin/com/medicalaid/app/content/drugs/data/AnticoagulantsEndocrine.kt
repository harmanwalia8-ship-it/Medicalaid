package com.medicalaid.app.content.drugs.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.drugs.Clearance
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.Facts
import com.medicalaid.app.content.drugs.Pharmacokinetics

/*
 * Anticoagulants and endocrine drugs. Each monograph paraphrases the cited US FDA label (DailyMed);
 * see Metabolic.kt for the template rules.
 */

internal val anticoagEndoSources = listOf(
    Source(
        "fda-label-warfarin", Authority.FDA, "Drug label (DailyMed)",
        "Warfarin sodium tablets — Upsher-Smith Laboratories", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=19a69a72-ac5d-45d5-a94d-a5aaecbe4730",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-apixaban", Authority.FDA, "Drug label (DailyMed)",
        "Apixaban tablets — E.R. Squibb & Sons", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=e9481622-7cc6-418a-acb6-c5450daae9b0",
        status = "Revised Aug 2026",
    ),
    Source(
        "fda-label-rivaroxaban", Authority.FDA, "Drug label (DailyMed)",
        "Rivaroxaban tablets — Janssen Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=10db92f9-2300-4a80-836b-673e1ae91610",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-dabigatran", Authority.FDA, "Drug label (DailyMed)",
        "Dabigatran etexilate capsules — Ascend Laboratories", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=3a8ec82a-c367-40dc-9302-e1896fcb05ea",
        status = "Revised Aug 2026",
    ),
    Source(
        "fda-label-glimepiride", Authority.FDA, "Drug label (DailyMed)",
        "Glimepiride tablets — Aurobindo Pharma", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=28eefc95-d92e-4555-b6c5-2933860b0610",
        status = "Revised Mar 2026",
    ),
    Source(
        "fda-label-sitagliptin", Authority.FDA, "Drug label (DailyMed)",
        "Sitagliptin tablets — Zydus Lifesciences", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=e5ac97e8-3a0b-4750-8e3f-337412570746",
        status = "Revised Nov 2026",
    ),
    Source(
        "fda-label-insulin-glargine", Authority.FDA, "Drug label (DailyMed)",
        "Insulin glargine injection 100 units/mL — Eli Lilly and Company", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=0ad21db3-2b1c-4ed9-a687-bdd6a74d0aae",
        status = "Revised Jun 2026",
    ),
    Source(
        "fda-label-regular-human-insulin", Authority.FDA, "Drug label (DailyMed)",
        "Insulin human (regular) injection U-100 — Eli Lilly and Company", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=b519bd83-038c-4ec5-a231-a51ec5cc291f",
        status = "Revised Jun 2026",
    ),
    Source(
        "fda-label-premixed-human-insulin-70-30", Authority.FDA, "Drug label (DailyMed)",
        "Insulin isophane human and insulin human 70/30 injectable suspension — Eli Lilly and Company", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=e245e0c5-b2d6-418b-baa4-1c3324292885",
        status = "Revised Jun 2026",
    ),
    Source(
        "fda-label-levothyroxine", Authority.FDA, "Drug label (DailyMed)",
        "Levothyroxine sodium tablets — Alvogen", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=4ddcaec1-a58f-340b-9bc1-bbe048b8c885",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-ursodeoxycholic-acid", Authority.FDA, "Drug label (DailyMed)",
        "Ursodiol tablets — Par Health USA", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=1a3a8942-94d4-4292-bf74-0a67d92acb90",
        status = "Revised Jun 2026",
    ),
)

private const val AE_WARFARIN = "fda-label-warfarin"
private const val AE_APIXABAN = "fda-label-apixaban"
private const val AE_RIVAROXABAN = "fda-label-rivaroxaban"
private const val AE_DABIGATRAN = "fda-label-dabigatran"
private const val AE_GLIMEPIRIDE = "fda-label-glimepiride"
private const val AE_SITAGLIPTIN = "fda-label-sitagliptin"
private const val AE_GLARGINE = "fda-label-insulin-glargine"
private const val AE_REGULAR_INSULIN = "fda-label-regular-human-insulin"
private const val AE_PREMIX_70_30 = "fda-label-premixed-human-insulin-70-30"
private const val AE_LEVOTHYROXINE = "fda-label-levothyroxine"
private const val AE_UDCA = "fda-label-ursodeoxycholic-acid"

internal val anticoagEndoDrugs = listOf(
    // ---------------------------------------------------------------------------------------------
    // Warfarin
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "warfarin",
        name = "Warfarin",
        drugClass = "Vitamin K antagonist (anticoagulant)",
        uses = "Prevention and treatment of venous thrombosis/PE; thromboembolism with AF or heart-valve replacement; after MI to reduce death, re-infarction and stroke.",
        clearance = setOf(Clearance.Hepatic),
        boxedWarning = Facts(
            listOf(
                "Bleeding risk: can cause major or fatal bleeding.",
                "Monitor INR regularly in all patients; drugs, diet changes and other factors alter INR.",
                "Teach patients how to prevent bleeding and to report signs and symptoms of bleeding.",
            ),
            listOf(cite(AE_WARFARIN, "Boxed Warning; 5.1")),
        ),
        dose = Facts(
            listOf(
                "Individualise to INR. Genotype unknown: usually start 2–5 mg once daily; typical maintenance 2–10 mg once daily. Routine loading doses not recommended.",
                "Target INR 2.0–3.0 for VTE, non-valvular AF, post-MI and aortic bileaflet/tilting-disk valves (sinus rhythm); 2.5–3.5 for mitral mechanical or caged-ball/disk valves. INR >4.0: more bleeding, no benefit.",
                "Consider lower starting and maintenance doses in elderly, debilitated and Asian patients.",
            ),
            listOf(cite(AE_WARFARIN, "2.2–2.3 Target INR and Dosing")),
        ),
        beforeStarting = Facts(
            listOf("Verify pregnancy status in females of reproductive potential (contraindicated in pregnancy except with mechanical heart valves)."),
            listOf(cite(AE_WARFARIN, "4; 8.3 Females of Reproductive Potential")),
        ),
        monitoring = Facts(
            listOf(
                "INR daily after the first dose until stable in range, then usually every 1–4 weeks.",
                "Extra INR checks when other drugs (incl. antibiotics, antifungals, botanicals) are started, stopped or changed, or warfarin products are switched.",
                "INR immediately before dental or surgical procedures.",
                "More frequent INR in renal impairment; closer bleeding checks in hepatic impairment and the elderly.",
            ),
            listOf(cite(AE_WARFARIN, "2.4; 2.7; 5.1; 7; 8.5–8.7")),
        ),
        renal = Facts(
            listOf("No dose adjustment needed; monitor INR more frequently in renal impairment."),
            listOf(cite(AE_WARFARIN, "2.5 Renal Impairment; 8.6")),
        ),
        hepatic = Facts(
            listOf("Hepatic impairment can potentiate response (less clotting-factor synthesis, slower metabolism); moderate–severe impairment raises risk — monitor for bleeding more often."),
            listOf(cite(AE_WARFARIN, "5.8; 8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Bleeding from any tissue or organ, fatal or non-fatal (most common)",
                "Nausea, vomiting, diarrhoea, abdominal pain, flatulence, bloating (frequency not stated)",
                "Taste disturbance",
                "Rash, dermatitis, pruritus, alopecia",
                "Raised liver enzymes, hepatitis",
                "Hypersensitivity (urticaria, anaphylaxis)",
            ),
            listOf(cite(AE_WARFARIN, "6 Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "Major or fatal haemorrhage (boxed) — more likely in the first month, INR >4.0, age ≥65, prior GI bleed, renal impairment.",
                "Skin/tissue necrosis or gangrene (<0.1%), usually within days of starting.",
                "Calciphylaxis, including fatal cases.",
                "Acute kidney injury with excessive anticoagulation and haematuria.",
                "Systemic atheroemboli and cholesterol microemboli ('purple toes').",
                "Limb ischaemia/gangrene if used as initial therapy in HIT/HITTS — start only after platelets normalise.",
                "Fetal harm: embryopathy, fetal haemorrhage, miscarriage.",
            ),
            listOf(cite(AE_WARFARIN, "Boxed Warning; 5.1–5.7")),
        ),
        pk = Pharmacokinetics(
            absorption = "Essentially completely absorbed; peak within about 4 h.",
            distribution = "≈99% protein bound; Vd ≈0.14 L/kg.",
            metabolism = "Almost entirely hepatic CYP450 (S-warfarin mainly CYP2C9; R-warfarin CYP1A2, 3A4) to inactive metabolites.",
            elimination = "Up to 92% of a dose recovered in urine, mostly as metabolites; very little unchanged.",
            halfLife = "Effective 20–60 h (mean ≈40 h); terminal ≈1 week after a single dose.",
            citations = listOf(cite(AE_WARFARIN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "CYP2C9/1A2/3A4 inhibitors (e.g. amiodarone, fluconazole, metronidazole, co-trimoxazole) raise INR; inducers (rifampin, carbamazepine, phenytoin) lower it — monitor INR closely.",
                "Antiplatelets (aspirin, clopidogrel), other anticoagulants, NSAIDs, SSRIs/SNRIs: ↑ bleeding risk.",
                "Antibiotics and antifungals: INR changes reported — monitor INR when starting or stopping.",
                "Botanicals: garlic, ginkgo may ↑ bleeding; St John's wort, ginseng, coenzyme Q10 may ↓ effect.",
                "Dietary vitamin K: keep intake consistent; avoid drastic changes such as large amounts of green leafy vegetables.",
            ),
            listOf(cite(AE_WARFARIN, "7.2–7.5 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Contraindicated except in women with mechanical heart valves at high thromboembolic risk; causes embryopathy (~5% after first-trimester exposure), fetal haemorrhage and miscarriage."),
            listOf(cite(AE_WARFARIN, "4; 8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Not detected in milk in a limited published study; monitor breastfed infants for bruising or bleeding."),
            listOf(cite(AE_WARFARIN, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Apixaban
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "apixaban",
        name = "Apixaban",
        drugClass = "Factor Xa inhibitor (anticoagulant)",
        uses = "Stroke prevention in non-valvular AF; DVT prophylaxis after hip/knee replacement; treatment of DVT/PE and reduction of recurrence; paediatric VTE.",
        clearance = setOf(Clearance.Renal, Clearance.Hepatic, Clearance.Biliary),
        boxedWarning = Facts(
            listOf(
                "Premature discontinuation increases thrombotic risk; if stopped other than for pathological bleeding or end of course, consider another anticoagulant.",
                "Spinal/epidural haematoma with neuraxial anaesthesia or spinal puncture, possibly causing permanent paralysis; risk ↑ with indwelling catheters, NSAIDs, antiplatelets, other anticoagulants.",
            ),
            listOf(cite(AE_APIXABAN, "Boxed Warning; 5.1; 5.3")),
        ),
        dose = Facts(
            listOf(
                "Non-valvular AF: 5 mg twice daily; 2.5 mg twice daily if ≥2 of age ≥80 years, weight ≤60 kg, serum creatinine ≥1.5 mg/dL.",
                "DVT/PE treatment: 10 mg twice daily for 7 days, then 5 mg twice daily; after ≥6 months of treatment, 2.5 mg twice daily to reduce recurrence.",
                "After hip/knee replacement: 2.5 mg twice daily, first dose 12–24 h post-op; 35 days (hip) or 12 days (knee).",
            ),
            listOf(cite(AE_APIXABAN, "2.1 Recommended Dose in Adult Patients")),
        ),
        beforeStarting = Facts(
            listOf(
                "Switching from warfarin: stop warfarin and start apixaban when INR is below 2.0.",
                "Assess risk of significant uterine bleeding in females of reproductive potential or with abnormal uterine bleeding.",
            ),
            listOf(cite(AE_APIXABAN, "2.5 Converting; 8.3")),
        ),
        monitoring = Facts(
            listOf(
                "Promptly evaluate signs of blood loss; stop if active pathological bleeding.",
                "After neuraxial anaesthesia or spinal puncture: check often for neurological impairment (leg numbness/weakness, bowel or bladder dysfunction).",
                "Remove epidural/intrathecal catheters ≥24 h after the last dose; next dose ≥5 h after removal.",
            ),
            listOf(cite(AE_APIXABAN, "5.2–5.3")),
        ),
        renal = Facts(
            listOf(
                "AF: 2.5 mg twice daily if ≥2 of age ≥80, weight ≤60 kg, serum creatinine ≥1.5 mg/dL.",
                "AF with ESRD on haemodialysis: usual dose gives exposure similar to trials; outcomes not studied.",
                "VTE prophylaxis, treatment and recurrence reduction: no dose adjustment, including ESRD on dialysis (CrCl <15 mL/min not studied clinically).",
            ),
            listOf(cite(AE_APIXABAN, "2.1; 8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf(
                "Child-Pugh A: no adjustment.",
                "Child-Pugh B: no dosing recommendation (limited experience; possible intrinsic coagulopathy).",
                "Child-Pugh C: not recommended.",
            ),
            listOf(cite(AE_APIXABAN, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Bleeding-related reactions are the most common (>1%)",
                "Epistaxis (2.9%, DVT/PE treatment)",
                "Contusion (1.8%)",
                "Haematuria (1.7%)",
                "Menorrhagia (1.4%)",
                "Haematoma (1.3%); haemoptysis (1.2%)",
                "Nausea (2.6%) and anaemia (2.6%) after hip/knee replacement",
                "Major bleeding in AF 2.13 per 100 patient-years (warfarin 3.09)",
            ),
            listOf(cite(AE_APIXABAN, "6.1 Clinical Trials Experience — Tables 2, 5, 7")),
        ),
        seriousEffects = Facts(
            listOf(
                "Thrombotic events, including stroke, after premature discontinuation (boxed).",
                "Spinal/epidural haematoma with neuraxial procedures (boxed).",
                "Serious, potentially fatal bleeding; effect persists ≥24 h after the last dose.",
                "Prosthetic heart valves: not recommended (not studied).",
                "Haemodynamically unstable PE or candidates for thrombolysis/embolectomy: not a substitute for unfractionated heparin.",
                "Triple-positive antiphospholipid syndrome: not recommended — more recurrent thrombosis than with VKA.",
            ),
            listOf(cite(AE_APIXABAN, "Boxed Warning; 5.1–5.6")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability ≈50% (doses ≤10 mg); Tmax 3–4 h; food has no effect.",
            distribution = "Protein binding ≈87%; Vss ≈21 L.",
            metabolism = "Mainly CYP3A4 (minor 1A2, 2C8, 2C9, 2C19, 2J2); no active circulating metabolites; P-gp and BCRP substrate.",
            elimination = "Urine and faeces; renal excretion ≈27% of total clearance, plus biliary and direct intestinal excretion.",
            halfLife = "≈12 h after oral dosing.",
            citations = listOf(cite(AE_APIXABAN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Combined P-gp + strong CYP3A4 inhibitors (ketoconazole, itraconazole, ritonavir): halve 5 or 10 mg twice-daily doses; avoid if on 2.5 mg twice daily.",
                "Clarithromycin: no dose adjustment needed.",
                "Combined P-gp + strong CYP3A4 inducers (rifampin, carbamazepine, phenytoin, St John's wort): avoid — ↓ exposure.",
                "Aspirin, antiplatelets, fibrinolytics, heparin, chronic NSAIDs: ↑ bleeding (aspirin raised AF bleeding risk from 1.8% to 3.4% per year).",
                "SSRIs, SNRIs and other anticoagulants: ↑ bleeding risk.",
            ),
            listOf(cite(AE_APIXABAN, "2.6; 5.2; 7.1–7.3")),
        ),
        pregnancy = Facts(
            listOf("Not recommended; human data are insufficient and treatment may increase bleeding during pregnancy and delivery."),
            listOf(cite(AE_APIXABAN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No human data (present in rat milk); breastfeeding is not recommended during treatment."),
            listOf(cite(AE_APIXABAN, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Rivaroxaban
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "rivaroxaban",
        name = "Rivaroxaban",
        drugClass = "Factor Xa inhibitor (anticoagulant)",
        uses = "Stroke prevention in non-valvular AF; DVT/PE treatment and recurrence reduction; VTE prophylaxis (hip/knee replacement, acutely ill medical patients); CAD/PAD with aspirin; paediatric VTE.",
        clearance = setOf(Clearance.Renal, Clearance.Hepatic),
        boxedWarning = Facts(
            listOf(
                "Premature discontinuation increases thrombotic risk; if stopped other than for pathological bleeding or end of course, consider another anticoagulant.",
                "Spinal/epidural haematoma with neuraxial anaesthesia or spinal puncture, possibly causing permanent paralysis; risk ↑ with indwelling catheters, NSAIDs, antiplatelets, other anticoagulants.",
            ),
            listOf(cite(AE_RIVAROXABAN, "Boxed Warning; 5.1–5.3")),
        ),
        dose = Facts(
            listOf(
                "Non-valvular AF: 20 mg once daily with the evening meal (CrCl >50 mL/min); 15 mg once daily if CrCl ≤50 mL/min.",
                "DVT/PE treatment: 15 mg twice daily with food for 21 days, then 20 mg once daily; after ≥6 months, 10 mg once daily to reduce recurrence.",
                "Prophylaxis 10 mg once daily: 35 days after hip, 12 days after knee replacement; 31–39 days in acutely ill medical patients. CAD/PAD: 2.5 mg twice daily plus aspirin 75–100 mg.",
            ),
            listOf(cite(AE_RIVAROXABAN, "2.1 Recommended Dosage in Adults — Table 1")),
        ),
        beforeStarting = Facts(
            listOf(
                "Calculate CrCl using actual body weight — dose and suitability depend on it.",
                "Switching from warfarin: stop warfarin and start rivaroxaban once INR is below 3.0 (adults).",
                "Assess risk of significant uterine bleeding in females of reproductive potential or with abnormal uterine bleeding.",
            ),
            listOf(cite(AE_RIVAROXABAN, "2.1 Table 1; 2.3; 8.3")),
        ),
        monitoring = Facts(
            listOf(
                "AF: assess renal function periodically, more often if it may decline; adjust dose. Stop or adjust if acute renal failure develops.",
                "Promptly evaluate signs of blood loss; observe closely if CrCl 15–<30 mL/min. PT/INR, aPTT or anti-FXa monitoring not recommended.",
                "After neuraxial procedures: check often for back pain, leg numbness/weakness, bowel or bladder dysfunction. Next dose ≥6 h after catheter removal.",
            ),
            listOf(cite(AE_RIVAROXABAN, "5.2–5.4")),
        ),
        renal = Facts(
            listOf(
                "AF: CrCl >50 mL/min 20 mg daily; CrCl ≤50 mL/min 15 mg daily (at CrCl <30 and in ESRD on haemodialysis, 15 mg gives similar exposure; outcomes unknown).",
                "DVT/PE treatment, recurrence reduction and VTE prophylaxis: avoid if CrCl <15 mL/min; observe closely at CrCl 15–<30.",
                "CAD/PAD: no dose adjustment based on CrCl.",
            ),
            listOf(cite(AE_RIVAROXABAN, "2.1 Table 1; 5.4; 8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("Avoid in Child-Pugh B or C, or any hepatic disease with coagulopathy (AUC ↑127% in Child-Pugh B)."),
            listOf(cite(AE_RIVAROXABAN, "5.5; 8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Bleeding — the most common adverse reaction (>5%)",
                "Major bleeding in AF 3.6% per year (warfarin 3.5%)",
                "Back pain (2.9%, DVT treatment)",
                "Abdominal pain (2.7%)",
                "Dizziness (2.2%)",
                "Pruritus (2.1–2.2%)",
                "Wound secretion (2.8%) after hip/knee replacement",
                "Insomnia (1.6%); muscle spasm (1.2–1.3%)",
            ),
            listOf(cite(AE_RIVAROXABAN, "6.1 Clinical Trials Experience — Tables 5, 12, 13")),
        ),
        seriousEffects = Facts(
            listOf(
                "Thrombotic events, including stroke, after premature discontinuation (boxed).",
                "Spinal/epidural haematoma with neuraxial procedures (boxed).",
                "Serious or fatal bleeding; label states no specific reversal agent is available; not dialysable.",
                "Pregnancy-related haemorrhage and emergent delivery.",
                "Prosthetic heart valves: not recommended (more deaths and bleeding after TAVR).",
                "Haemodynamically unstable PE or candidates for thrombolysis/embolectomy: not a substitute for unfractionated heparin.",
                "Triple-positive antiphospholipid syndrome: not recommended.",
            ),
            listOf(cite(AE_RIVAROXABAN, "Boxed Warning; 5.1–5.10")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability 80–100% (2.5/10 mg); 20 mg ≈66% fasting, ↑ with food — take 15/20 mg with food. Tmax 2–4 h.",
            distribution = "Protein binding 92–95% (mainly albumin); Vss ≈50 L.",
            metabolism = "CYP3A4/5, CYP2J2 and hydrolysis; no major or active circulating metabolites; P-gp and BCRP substrate.",
            elimination = "≈36% unchanged in urine (mainly active tubular secretion), 7% unchanged in faeces; inactive metabolites in urine (30%) and faeces (21%).",
            halfLife = "5–9 h in healthy subjects aged 20–45 years.",
            citations = listOf(cite(AE_RIVAROXABAN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Combined P-gp + strong CYP3A inhibitors (ketoconazole, ritonavir): avoid. Clarithromycin: no precautions needed.",
                "Combined P-gp + moderate CYP3A inhibitors (e.g. erythromycin) with CrCl 15–<80 mL/min: avoid unless benefit justifies risk.",
                "Combined P-gp + strong CYP3A inducers (carbamazepine, phenytoin, rifampin, St John's wort): avoid — ↓ exposure.",
                "Other anticoagulants: avoid unless benefit outweighs risk. Aspirin, clopidogrel, chronic NSAIDs: ↑ bleeding.",
                "SSRIs and SNRIs: ↑ bleeding risk.",
            ),
            listOf(cite(AE_RIVAROXABAN, "5.2; 7.2–7.4")),
        ),
        pregnancy = Facts(
            listOf("Data insufficient; use with caution — risk of pregnancy-related haemorrhage and emergent delivery, and effect cannot be reliably monitored with standard tests."),
            listOf(cite(AE_RIVAROXABAN, "5.7; 8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Detected in human milk; effects on the breastfed child unknown — weigh breastfeeding benefits against the mother's need."),
            listOf(cite(AE_RIVAROXABAN, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Dabigatran
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "dabigatran",
        name = "Dabigatran",
        aliases = listOf("dabigatran etexilate"),
        drugClass = "Direct thrombin inhibitor (anticoagulant)",
        uses = "Stroke prevention in non-valvular AF; DVT/PE treatment (after 5–10 days of parenteral anticoagulant) and recurrence reduction; prophylaxis after hip replacement; paediatric VTE (8–<18 years).",
        clearance = setOf(Clearance.Renal),
        boxedWarning = Facts(
            listOf(
                "Premature discontinuation increases thrombotic risk; if stopped other than for pathological bleeding or end of course, consider another anticoagulant.",
                "Spinal/epidural haematoma with neuraxial anaesthesia or spinal puncture, possibly causing permanent paralysis; risk ↑ with indwelling catheters, NSAIDs, antiplatelets, other anticoagulants.",
            ),
            listOf(cite(AE_DABIGATRAN, "Boxed Warning; 5.1; 5.3")),
        ),
        dose = Facts(
            listOf(
                "Non-valvular AF: 150 mg twice daily (CrCl >30 mL/min).",
                "DVT/PE treatment (after 5–10 days parenteral anticoagulation) and recurrence reduction: 150 mg twice daily (CrCl >30 mL/min).",
                "After hip replacement: 110 mg 1–4 h post-op once haemostasis achieved, then 220 mg once daily for 28–35 days. Swallow capsules whole; do not open.",
            ),
            listOf(cite(AE_DABIGATRAN, "2.2 Adult Dosage; 2.5 Administration")),
        ),
        beforeStarting = Facts(
            listOf(
                "Assess renal function before starting.",
                "Switching from warfarin: stop warfarin and start dabigatran when INR is below 2.0.",
                "Assess risk of significant uterine bleeding in females of reproductive potential or with abnormal uterine bleeding.",
            ),
            listOf(cite(AE_DABIGATRAN, "2.4; 2.6; 8.3")),
        ),
        monitoring = Facts(
            listOf(
                "Renal function periodically, more often when it may decline; stop if acute renal failure develops.",
                "Anticoagulant level usually need not be measured; if needed use aPTT or ECT, not INR.",
                "Promptly evaluate signs of blood loss (fall in haemoglobin/haematocrit, hypotension).",
                "After neuraxial procedures: check often for back pain, leg numbness/weakness, bowel or bladder dysfunction.",
            ),
            listOf(cite(AE_DABIGATRAN, "2.4; 5.2; 5.3")),
        ),
        renal = Facts(
            listOf(
                "AF: CrCl >30 mL/min 150 mg twice daily; CrCl 15–30 mL/min 75 mg twice daily; CrCl <15 or dialysis — no recommendation.",
                "DVT/PE treatment, recurrence reduction and hip prophylaxis: no dosing recommendation if CrCl ≤30 mL/min or on dialysis.",
                "With P-gp inhibitors: AF CrCl 30–50 — 75 mg twice daily with dronedarone or systemic ketoconazole; avoid at CrCl 15–30. VTE/hip indications: avoid if CrCl <50.",
                "Before procedures: stop 1–2 days (CrCl ≥50 mL/min) or 3–5 days (CrCl <50 mL/min) beforehand.",
            ),
            listOf(cite(AE_DABIGATRAN, "2.2; 2.4; 2.8; 8.6 Renal Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Gastrointestinal reactions (35% vs 24% with warfarin in AF)",
                "Dyspepsia: upper abdominal pain, abdominal or epigastric discomfort",
                "Gastritis-like symptoms: GERD, oesophagitis, erosive gastritis, GI ulcer",
                "Any GI bleeding (6.6% vs 4.2% with warfarin)",
                "Major bleeding in AF 3.47% per year (warfarin 3.58%)",
            ),
            listOf(cite(AE_DABIGATRAN, "6.1 Clinical Trials Experience (RE-LY)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Thrombotic events after premature discontinuation (boxed).",
                "Spinal/epidural haematoma with neuraxial procedures (boxed).",
                "Significant, sometimes fatal, bleeding; idarucizumab reverses the effect in adults; haemodialysis removes dabigatran (limited experience).",
                "Mechanical prosthetic valves: contraindicated (more thromboembolism and bleeding than warfarin); bioprosthetic valves: not recommended.",
                "P-gp inducers ↓ exposure; P-gp inhibitors, especially with renal impairment, ↑ exposure.",
                "Triple-positive antiphospholipid syndrome: not recommended.",
            ),
            listOf(cite(AE_DABIGATRAN, "Boxed Warning; 4; 5.1–5.6")),
        ),
        pk = Pharmacokinetics(
            absorption = "Prodrug; bioavailability 3–7%; Tmax ≈1 h (delayed ~2 h by high-fat meal, extent unchanged). Opening the capsule ↑ bioavailability by 75%.",
            distribution = "≈35% protein bound; Vd 50–70 L.",
            metabolism = "Esterase hydrolysis to active dabigatran, then active acyl glucuronides; not a CYP450 substrate; prodrug is a P-gp substrate.",
            elimination = "Mainly urine — renal clearance is 80% of total clearance (IV).",
            halfLife = "12–17 h in healthy adults; ≈27 h at CrCl 15–30 mL/min.",
            citations = listOf(cite(AE_DABIGATRAN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "P-gp inducers (e.g. rifampin): generally avoid — ↓ exposure.",
                "AF with CrCl 30–50 mL/min: 75 mg twice daily with dronedarone or systemic ketoconazole; avoid P-gp inhibitors if CrCl 15–30.",
                "Verapamil, amiodarone, quinidine, clarithromycin, ticagrelor: no AF dose adjustment needed.",
                "DVT/PE or hip-replacement use: avoid P-gp inhibitors if CrCl <50 mL/min.",
                "Antiplatelets, heparin, fibrinolytics, chronic NSAIDs: ↑ bleeding risk.",
            ),
            listOf(cite(AE_DABIGATRAN, "5.2; 7.1–7.3")),
        ),
        pregnancy = Facts(
            listOf("Data insufficient; anticoagulants may cause maternal, fetal and neonatal bleeding — monitor neonates; consider stopping or a shorter-acting agent as delivery approaches."),
            listOf(cite(AE_DABIGATRAN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Insufficient human data (present in rat milk); breastfeeding is not recommended during treatment."),
            listOf(cite(AE_DABIGATRAN, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Glimepiride
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "glimepiride",
        name = "Glimepiride",
        drugClass = "Sulfonylurea",
        uses = "Type 2 diabetes in adults, with diet and exercise (not for type 1 diabetes or DKA).",
        clearance = setOf(Clearance.Renal, Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Start 1 or 2 mg once daily with breakfast or the first main meal; increase by 1–2 mg no more often than every 1–2 weeks; max 8 mg once daily.",
                "Start 1 mg and titrate conservatively if at higher hypoglycaemia risk (elderly, renal impairment).",
            ),
            listOf(cite(AE_GLIMEPIRIDE, "2.1 Recommended Dosing")),
        ),
        monitoring = Facts(
            listOf(
                "Monitor closely for hypoglycaemia in the elderly and in renal impairment, and when interacting drugs are added or withdrawn.",
                "Switching from longer half-life sulfonylureas (e.g. chlorpropamide): watch for hypoglycaemia for 1–2 weeks (overlapping effect).",
            ),
            listOf(cite(AE_GLIMEPIRIDE, "2.1; 7.1; 8.5–8.6")),
        ),
        renal = Facts(
            listOf("Start 1 mg once daily in renal impairment; titrate cautiously and monitor for hypoglycaemia (metabolite elimination is reduced)."),
            listOf(cite(AE_GLIMEPIRIDE, "2.1; 8.6 Renal Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Hypoglycaemia (possible hypoglycaemia 19.7% vs 3.2% placebo in a 22-week trial)",
                "Headache (8.2% vs 7.8%)",
                "Accidental injury (5.8%)",
                "Flu syndrome (5.4%)",
                "Nausea (5%)",
                "Dizziness (5%)",
                "Weight gain",
                "ALT >2× upper limit (1.9% vs 0.8%)",
            ),
            listOf(cite(AE_GLIMEPIRIDE, "6.1 Clinical Trials Experience — Table 1")),
        ),
        seriousEffects = Facts(
            listOf(
                "Severe hypoglycaemia — may cause unconsciousness, seizures, brain injury or death; higher risk in elderly, renal or hepatic impairment, with other antidiabetics.",
                "Hypersensitivity: anaphylaxis, angioedema, Stevens-Johnson syndrome — stop promptly.",
                "Haemolytic anaemia, especially with G6PD deficiency — consider a non-sulfonylurea.",
                "Possible increased cardiovascular mortality (sulfonylurea class warning, UGDP study).",
            ),
            listOf(cite(AE_GLIMEPIRIDE, "5.1–5.4 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Tmax 2–3 h; taking with meals lowers Cmax and AUC by only 8–9%.",
            distribution = "Protein binding >99.5%; Vd 8.8 L.",
            metabolism = "Completely metabolised; CYP2C9 forms M1 (about one-third activity in animals), then inactive M2.",
            elimination = "≈60% in urine and ≈40% in faeces, as metabolites; no unchanged drug recovered.",
            citations = listOf(cite(AE_GLIMEPIRIDE, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "↑ hypoglycaemia: insulin, other antidiabetics, ACE inhibitors, fluconazole, clarithromycin, quinolones, NSAIDs, salicylates, sulfonamides — monitor closely.",
                "↓ effect: corticosteroids, thiazides, rifampin, atypical antipsychotics, thyroid hormones, oral contraceptives, sympathomimetics — monitor glycaemic control.",
                "Oral miconazole: severe hypoglycaemia reported.",
                "Beta-blockers, clonidine, reserpine, alcohol: unpredictable effect; sympatholytics may mask hypoglycaemia.",
                "Colesevelam ↓ absorption — give glimepiride at least 4 h before.",
            ),
            listOf(cite(AE_GLIMEPIRIDE, "7.1–7.4 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No drug-associated risk of major birth defects identified, but crosses the placenta and may cause neonatal hypoglycaemia — stop at least 2 weeks before expected delivery."),
            listOf(cite(AE_GLIMEPIRIDE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Unknown whether excreted in human milk (present in rat milk); monitor breastfed infants for hypoglycaemia."),
            listOf(cite(AE_GLIMEPIRIDE, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Sitagliptin
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "sitagliptin",
        name = "Sitagliptin",
        drugClass = "DPP-4 inhibitor",
        uses = "Type 2 diabetes in adults, with diet and exercise (not for type 1 diabetes).",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "100 mg once daily, with or without food; reduce in eGFR <45 (see renal).",
                "With insulin or a sulfonylurea, a lower dose of those drugs may be needed to limit hypoglycaemia.",
            ),
            listOf(cite(AE_SITAGLIPTIN, "2.1 Recommended Dosage; 5.4")),
        ),
        beforeStarting = Facts(
            listOf("Assess renal function before starting."),
            listOf(cite(AE_SITAGLIPTIN, "2.2; 5.3")),
        ),
        monitoring = Facts(
            listOf(
                "Renal function periodically; more often in the elderly.",
                "Observe for pancreatitis after starting; stop promptly if suspected.",
                "Patients at risk of heart failure: watch for signs and symptoms.",
            ),
            listOf(cite(AE_SITAGLIPTIN, "2.2; 5.1–5.3; 8.5")),
        ),
        renal = Facts(
            listOf(
                "eGFR ≥45 to <90 mL/min/1.73 m²: no adjustment.",
                "eGFR ≥30 to <45: 50 mg once daily.",
                "eGFR <30 or ESRD on haemodialysis/peritoneal dialysis: 25 mg once daily, regardless of dialysis timing.",
            ),
            listOf(cite(AE_SITAGLIPTIN, "2.2 Renal Impairment; 8.6")),
        ),
        commonEffects = Facts(
            listOf(
                "Nasopharyngitis (5.2% vs 3.3% placebo, monotherapy)",
                "Upper respiratory tract infection (6.3%, with pioglitazone)",
                "Headache (5.1–5.9%)",
                "Hypoglycaemia with a sulfonylurea (12.2% vs 1.8%) or insulin (15.5% vs 7.8%)",
                "Peripheral oedema (8.3% vs 5.2%, with metformin + rosiglitazone at 54 weeks)",
            ),
            listOf(cite(AE_SITAGLIPTIN, "6.1 Clinical Trials Experience — Tables 1, 3")),
        ),
        seriousEffects = Facts(
            listOf(
                "Acute pancreatitis, including fatal haemorrhagic or necrotising — stop if suspected.",
                "Heart failure (seen with two other DPP-4 inhibitors) — weigh risk if prior heart failure or renal impairment.",
                "Acute renal failure, sometimes requiring dialysis.",
                "Hypoglycaemia with insulin or insulin secretagogues.",
                "Serious hypersensitivity: anaphylaxis, angioedema, Stevens-Johnson syndrome (mostly within 3 months).",
                "Severe, disabling arthralgia.",
                "Bullous pemphigoid.",
            ),
            listOf(cite(AE_SITAGLIPTIN, "5.1–5.7 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability ≈87%; Tmax 1–4 h; high-fat meal has no effect.",
            distribution = "Protein binding 38%; Vss ≈198 L.",
            metabolism = "Minor (≈16% as metabolites), mainly CYP3A4 with CYP2C8; trace metabolites not expected to contribute to activity.",
            elimination = "≈79% excreted unchanged in urine (active tubular secretion); overall 87% urine, 13% faeces.",
            halfLife = "≈12.4 h.",
            citations = listOf(cite(AE_SITAGLIPTIN, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf("Insulin or insulin secretagogues (e.g. sulfonylureas): lower doses may be needed to reduce hypoglycaemia."),
            listOf(cite(AE_SITAGLIPTIN, "7.1 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Limited data are insufficient to inform drug-associated risk; poorly controlled diabetes carries maternal and fetal risks."),
            listOf(cite(AE_SITAGLIPTIN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No human data (present in rat milk); weigh breastfeeding benefits against the mother's need."),
            listOf(cite(AE_SITAGLIPTIN, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Insulin glargine
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "insulin-glargine",
        name = "Insulin glargine",
        drugClass = "Long-acting insulin analogue",
        uses = "Glycaemic control in type 1 diabetes (adults and children) and type 2 diabetes (adults); not for diabetic ketoacidosis.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Subcutaneous once daily at the same time each day; in type 1 diabetes always with short-acting insulin.",
                "Start: type 1 ≈⅓ of total daily insulin; type 2 0.2 units/kg or up to 10 units once daily. Titrate to glucose monitoring.",
                "From twice-daily NPH or glargine 300 units/mL: start at 80% of that dose. Pen gives max 80 units per injection.",
            ),
            listOf(cite(AE_GLARGINE, "2.2–2.4 Dosing")),
        ),
        monitoring = Facts(
            listOf(
                "Blood glucose — more often with changes in regimen, dose, meals, activity or other drugs, and in renal/hepatic impairment or hypoglycaemia unawareness.",
                "Potassium in patients at risk of hypokalaemia.",
                "Signs of heart failure if combined with a thiazolidinedione.",
            ),
            listOf(cite(AE_GLARGINE, "2.1; 5.2–5.7")),
        ),
        renal = Facts(
            listOf("Not studied; frequent glucose monitoring and dose adjustment may be needed."),
            listOf(cite(AE_GLARGINE, "8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("Not studied; more frequent glucose monitoring and dose adjustment may be needed."),
            listOf(cite(AE_GLARGINE, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Hypoglycaemia (most common); severe in 4% (type 1, 52 weeks) and 1% (type 2, 24 weeks)",
                "Infection (24% type 1; 17% type 2)",
                "Nasopharyngitis (16% type 1; 6% type 2)",
                "Upper respiratory tract infection (8% type 1; 5% type 2)",
                "Injection-site reactions, allergic reactions, pruritus, rash (≥5% with insulin glargine products)",
                "Lipodystrophy",
                "Oedema and weight gain",
            ),
            listOf(cite(AE_GLARGINE, "6 Adverse Reactions; 6.1 — Tables 1–2")),
        ),
        seriousEffects = Facts(
            listOf(
                "Severe hypoglycaemia — seizures or death; the long action may delay recovery.",
                "Hyper- or hypoglycaemia with regimen changes or injections into lipodystrophy/cutaneous amyloidosis.",
                "Medication errors from mix-ups with other insulins — check the label before each injection.",
                "Severe generalised allergy, including anaphylaxis.",
                "Hypokalaemia — may be life-threatening.",
                "Fluid retention and heart failure with thiazolidinediones.",
                "Never share pens, even with a new needle (blood-borne infection).",
            ),
            listOf(cite(AE_GLARGINE, "5.1–5.7 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Slow, prolonged absorption with no pronounced peak; median Tmax ≈12 h.",
            metabolism = "Cleaved at the B-chain carboxyl terminus to active metabolites M1 and M2 (activity similar to insulin).",
            halfLife = "Duration: sustained glucose lowering over 24 h; insulin levels return to baseline by ≈24 h.",
            citations = listOf(cite(AE_GLARGINE, "12.2 Pharmacodynamics; 12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "↑ hypoglycaemia: other antidiabetics, ACE inhibitors, ARBs, fluoxetine, MAOIs, salicylates, sulfonamide antibiotics, octreotide — dose reduction may be needed.",
                "↓ effect: corticosteroids, diuretics, atypical antipsychotics, oestrogens/oral contraceptives, protease inhibitors, sympathomimetics, thyroid hormones — dose increase may be needed.",
                "Alcohol, beta-blockers, clonidine, lithium: variable effect; pentamidine may cause hypoglycaemia then hyperglycaemia.",
                "Beta-blockers, clonidine, guanethidine, reserpine may blunt hypoglycaemia symptoms.",
                "Thiazolidinediones: fluid retention and heart failure.",
            ),
            listOf(cite(AE_GLARGINE, "7 Drug Interactions — Table 6; 5.7")),
        ),
        pregnancy = Facts(
            listOf("Published studies show no clear association with adverse developmental outcomes; poorly controlled diabetes carries maternal and fetal risks."),
            listOf(cite(AE_GLARGINE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No data on presence in milk (endogenous insulin is present in human milk); weigh breastfeeding benefits against the mother's need."),
            listOf(cite(AE_GLARGINE, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Regular human insulin
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "regular-human-insulin",
        name = "Regular human insulin",
        aliases = listOf("soluble insulin", "insulin regular"),
        drugClass = "Short-acting human insulin",
        uses = "Glycaemic control in adults and children with diabetes mellitus (subcutaneous or intravenous).",
        clearance = setOf(Clearance.Renal, Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Individualise by route, metabolic need and glucose monitoring (no fixed dose stated). SC: about 30 min before meals, usually with an intermediate- or long-acting insulin.",
                "IV only under medical supervision: 0.1–1 unit/mL in 0.9% sodium chloride, with close glucose and potassium monitoring.",
                "Mix only with the manufacturer's NPH (isophane) insulin; draw regular insulin first and inject immediately.",
            ),
            listOf(cite(AE_REGULAR_INSULIN, "2.2–2.5 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Blood glucose — more often with changes in regimen, dose, meals, activity or other drugs, and in renal/hepatic impairment or hypoglycaemia unawareness.",
                "IV use: close monitoring of blood glucose and potassium.",
                "Potassium in patients at risk of hypokalaemia; signs of heart failure if on a thiazolidinedione.",
            ),
            listOf(cite(AE_REGULAR_INSULIN, "2.2; 5.2–5.7")),
        ),
        renal = Facts(
            listOf("Not studied; higher hypoglycaemia risk — may need more frequent dose adjustment and glucose monitoring."),
            listOf(cite(AE_REGULAR_INSULIN, "8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("Not studied; higher hypoglycaemia risk — may need more frequent dose adjustment and glucose monitoring."),
            listOf(cite(AE_REGULAR_INSULIN, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Hypoglycaemia (most common; frequency not stated)",
                "Injection-site reactions (haematoma, pain, erythema, swelling, pruritus)",
                "Lipodystrophy; localised cutaneous amyloidosis",
                "Weight gain",
                "Oedema (sodium retention), especially when control is intensified",
                "Allergic reactions, pruritus, rash",
                "With rapid glucose improvement: transient refraction change, worsening retinopathy, acute painful neuropathy",
            ),
            listOf(cite(AE_REGULAR_INSULIN, "6 Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "Severe hypoglycaemia — seizures or death.",
                "Hyper- or hypoglycaemia with regimen changes or injections into lipodystrophy/cutaneous amyloidosis.",
                "Medication errors from mix-ups with other insulins — check the label before each injection.",
                "Severe generalised allergy, including anaphylaxis.",
                "Hypokalaemia — may be life-threatening.",
                "Fluid retention and heart failure with thiazolidinediones.",
                "Never share needles or syringes.",
            ),
            listOf(cite(AE_REGULAR_INSULIN, "5.1–5.7 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "SC bioavailability 48–89%; peak serum level ≈2 h (20 min–6 h) in type 1 diabetes.",
            distribution = "Vd 0.32–0.67 L/kg (IV).",
            metabolism = "Taken up and degraded in liver (main organ of clearance), kidney, muscle and fat.",
            halfLife = "SC: onset ≈30 min, peak ≈3 h, duration ≈8 h (3–14 h); apparent t½ ≈1.5 h. IV: onset 10–15 min, effect ends ≈4 h.",
            citations = listOf(cite(AE_REGULAR_INSULIN, "12.2 Pharmacodynamics; 12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "↑ hypoglycaemia: other antidiabetics, ACE inhibitors, ARBs, fluoxetine, MAOIs, salicylates, sulfonamide antibiotics, octreotide — dose adjustment may be needed.",
                "↓ effect: corticosteroids, diuretics, atypical antipsychotics, oestrogens/oral contraceptives, protease inhibitors, sympathomimetics, thyroid hormones.",
                "Alcohol, beta-blockers, clonidine, lithium: variable effect; pentamidine may cause hypoglycaemia then hyperglycaemia.",
                "Beta-blockers, clonidine, guanethidine, reserpine may blunt hypoglycaemia symptoms.",
                "Thiazolidinediones: fluid retention and heart failure.",
            ),
            listOf(cite(AE_REGULAR_INSULIN, "7 Drug Interactions — Table 1; 5.7")),
        ),
        pregnancy = Facts(
            listOf("Decades of published data have not linked human insulin with major birth defects, miscarriage or adverse outcomes; poorly controlled diabetes carries risks."),
            listOf(cite(AE_REGULAR_INSULIN, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Exogenous human insulin passes into human milk; no adverse effects reported in breastfed infants."),
            listOf(cite(AE_REGULAR_INSULIN, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Premixed human insulin 70/30 (isophane + regular)
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "premixed-human-insulin-70-30",
        name = "Premixed human insulin 70/30",
        aliases = listOf("NPH/regular 70/30"),
        drugClass = "Premixed human insulin (70% NPH / 30% regular)",
        uses = "Glycaemic control in adults with diabetes mellitus (fixed-ratio premix).",
        clearance = setOf(Clearance.Renal, Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Subcutaneous only, about 30–45 min before a meal; individualise by glucose monitoring (no fixed dose stated).",
                "Fixed 70% isophane / 30% regular ratio — basal and prandial doses cannot be adjusted independently.",
                "Do not give IV or by pump; do not mix with other insulins or diluents. Should look uniformly cloudy after mixing.",
            ),
            listOf(cite(AE_PREMIX_70_30, "2.1–2.2 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Blood glucose — more often with changes in regimen, dose, meals, activity or other drugs, and in renal/hepatic impairment or hypoglycaemia unawareness.",
                "Potassium in patients at risk of hypokalaemia; signs of heart failure if on a thiazolidinedione.",
            ),
            listOf(cite(AE_PREMIX_70_30, "2.2; 5.2–5.7")),
        ),
        renal = Facts(
            listOf("Not studied; higher hypoglycaemia risk — may need more frequent dose adjustment and glucose monitoring."),
            listOf(cite(AE_PREMIX_70_30, "8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("Not studied; higher hypoglycaemia risk — may need more frequent dose adjustment and glucose monitoring."),
            listOf(cite(AE_PREMIX_70_30, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Hypoglycaemia (most common; frequency not stated)",
                "Injection-site erythema, swelling, pruritus (usually self-limiting)",
                "Lipodystrophy; localised cutaneous amyloidosis",
                "Weight gain",
                "Peripheral oedema, especially when control is intensified",
                "Allergic reactions, pruritus, rash",
            ),
            listOf(cite(AE_PREMIX_70_30, "5.3; 6 Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "Severe hypoglycaemia — seizures or death.",
                "Hyper- or hypoglycaemia with regimen changes or injections into lipodystrophy/cutaneous amyloidosis.",
                "Medication errors from mix-ups with other insulins — check the label before each injection.",
                "Severe generalised allergy, including anaphylaxis.",
                "Hypokalaemia — may be life-threatening.",
                "Fluid retention and heart failure with thiazolidinediones.",
                "Never share pens or syringes, even with a new needle.",
            ),
            listOf(cite(AE_PREMIX_70_30, "5.1–5.7 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Mean peak serum concentration 2.2 h (1–5 h) after SC dosing.",
            metabolism = "Degraded mainly in the liver (main organ of clearance), also kidney, muscle and fat.",
            halfLife = "Onset ≈50 min (30–90 min), peak ≈3.5 h (1.5–6.5 h), duration ≈23 h (18–24 h); true t½ cannot be estimated.",
            citations = listOf(cite(AE_PREMIX_70_30, "12.2 Pharmacodynamics; 12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "↑ hypoglycaemia: other antidiabetics, ACE inhibitors, ARBs, fluoxetine, MAOIs, salicylates, sulfonamide antibiotics, octreotide — dose adjustment may be needed.",
                "↓ effect: corticosteroids, diuretics, atypical antipsychotics, oestrogens/oral contraceptives, protease inhibitors, sympathomimetics, thyroid hormones.",
                "Alcohol, beta-blockers, clonidine, lithium: variable effect; pentamidine may cause hypoglycaemia then hyperglycaemia.",
                "Beta-blockers, clonidine, guanethidine, reserpine may blunt hypoglycaemia symptoms.",
                "Thiazolidinediones: fluid retention and heart failure.",
            ),
            listOf(cite(AE_PREMIX_70_30, "7 Drug Interactions — Table 1; 5.7")),
        ),
        pregnancy = Facts(
            listOf("Decades of published data have not linked human insulin with major birth defects, miscarriage or adverse outcomes; poorly controlled diabetes carries risks."),
            listOf(cite(AE_PREMIX_70_30, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Exogenous human insulin passes into human milk; no adverse effects reported in breastfed infants."),
            listOf(cite(AE_PREMIX_70_30, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Levothyroxine
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "levothyroxine",
        name = "Levothyroxine",
        aliases = listOf("thyroxine", "L-thyroxine"),
        drugClass = "Thyroid hormone (synthetic T4)",
        uses = "Replacement in primary, secondary or tertiary hypothyroidism (all ages); TSH suppression with surgery/radioiodine in differentiated thyroid cancer.",
        clearance = setOf(Clearance.Renal, Clearance.Hepatic),
        boxedWarning = Facts(
            listOf("Not for obesity or weight loss: ineffective at replacement doses in euthyroid patients; larger doses can cause serious or life-threatening toxicity, especially with sympathomimetic amines."),
            listOf(cite(AE_LEVOTHYROXINE, "Boxed Warning")),
        ),
        dose = Facts(
            listOf(
                "Once daily on an empty stomach, ½–1 h before breakfast; at least 4 h apart from drugs that impair absorption.",
                "Adults: full replacement 1.6 mcg/kg/day; titrate by 12.5–25 mcg every 4–6 weeks. Elderly: start lower. Cardiac disease or AF risk: start lower, titrate every 6–8 weeks.",
                "Doses >200 mcg/day seldom needed; poor response above 300 mcg/day is rare (check compliance, malabsorption, interactions). Full effect may take 4–6 weeks.",
            ),
            listOf(cite(AE_LEVOTHYROXINE, "2.1–2.3 Dosage")),
        ),
        beforeStarting = Facts(
            listOf("Adrenal insufficiency: correct with glucocorticoids before starting (uncorrected adrenal insufficiency is a contraindication)."),
            listOf(cite(AE_LEVOTHYROXINE, "4; 5.4")),
        ),
        monitoring = Facts(
            listOf(
                "Primary hypothyroidism: TSH 6–8 weeks after any dose change; when stable, clinical and biochemical review every 6–12 months.",
                "Secondary/tertiary hypothyroidism: use free T4 (keep in upper half of normal), not TSH.",
                "Pregnancy: TSH and free T4 when pregnancy is confirmed and at least each trimester; TSH every 4 weeks while adjusting.",
                "Diabetes: glycaemic control after starting, changing or stopping. Oral anticoagulants: coagulation tests.",
            ),
            listOf(cite(AE_LEVOTHYROXINE, "2.2–2.4; 5.5; 7.3")),
        ),
        commonEffects = Facts(
            listOf(
                "Mainly features of over-replacement (hyperthyroidism); frequencies not stated",
                "Palpitations, tachycardia, arrhythmias",
                "Nervousness, irritability, insomnia, tremor, headache",
                "Weight loss, increased appetite, heat intolerance, sweating",
                "Diarrhoea, vomiting, abdominal cramps",
                "Hair loss, flushing, rash",
                "Menstrual irregularities",
                "Muscle weakness or spasm, dyspnoea",
            ),
            listOf(cite(AE_LEVOTHYROXINE, "6 Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "Toxicity if used for weight loss (boxed).",
                "Over- or under-treatment (narrow therapeutic index) harms heart, bone, growth and cognition — titrate carefully.",
                "Angina and arrhythmias (incl. AF) in the elderly and those with heart disease — start low.",
                "Myxoedema coma: oral products not recommended — use IV thyroid hormone.",
                "Acute adrenal crisis if started before treating adrenal insufficiency.",
                "Worsened glycaemic control in diabetes.",
                "Reduced bone mineral density with over-replacement, especially post-menopausal women.",
            ),
            listOf(cite(AE_LEVOTHYROXINE, "Boxed Warning; 5.1–5.6")),
        ),
        pk = Pharmacokinetics(
            absorption = "40–80% absorbed (jejunum, upper ileum); ↑ by fasting; ↓ by soy, dietary fibre, malabsorption and age.",
            distribution = "Protein binding 99.96% (TBG, TBPA, albumin); does not readily cross the placenta.",
            metabolism = "Sequential deiodination, mainly in the liver (also kidney); ~80% of circulating T3 comes from peripheral T4; conjugates undergo enterohepatic recirculation.",
            elimination = "Primarily renal; ≈20% of T4 eliminated in stool.",
            halfLife = "6–7 days (3–4 days in hyperthyroidism, 9–10 days in hypothyroidism).",
            citations = listOf(cite(AE_LEVOTHYROXINE, "12.3 Pharmacokinetics — Table 10")),
        ),
        interactions = Facts(
            listOf(
                "Calcium carbonate, ferrous sulfate, phosphate binders, bile-acid sequestrants, ion-exchange resins: ↓ absorption — separate by at least 4 h.",
                "PPIs, sucralfate, antacids: may ↓ absorption — monitor.",
                "Phenobarbital, rifampin ↑ T4 metabolism — higher dose may be needed; phenytoin, carbamazepine may lower total/free T4 by 20–40% (TSH usually normal).",
                "Oral anticoagulants: response ↑ — anticoagulant dose may need lowering; monitor coagulation.",
                "Tricyclic antidepressants, sympathomimetics, ketamine: ↑ cardiac/toxic effects; antidiabetic requirements may rise.",
            ),
            listOf(cite(AE_LEVOTHYROXINE, "7.1–7.7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No increase in birth defects or miscarriage reported; do not stop — requirements may rise, so monitor TSH and adjust; return to pre-pregnancy dose after delivery."),
            listOf(cite(AE_LEVOTHYROXINE, "2.3; 8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk; no adverse effects reported in breastfed infants."),
            listOf(cite(AE_LEVOTHYROXINE, "8.2 Lactation")),
        ),
    ),

    // ---------------------------------------------------------------------------------------------
    // Ursodeoxycholic acid
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "ursodeoxycholic-acid",
        name = "Ursodeoxycholic acid",
        aliases = listOf("ursodiol", "UDCA"),
        drugClass = "Bile acid",
        uses = "Primary biliary cholangitis (PBC) — indication of the 250/500 mg tablet label cited here.",
        clearance = setOf(Clearance.Hepatic, Clearance.Biliary),
        dose = Facts(
            listOf(
                "PBC: 13–15 mg/kg/day in 2–4 divided doses with food; adjust to the patient's need.",
                "500 mg scored tablet may be halved; swallow segments unchewed with water (bitter taste).",
            ),
            listOf(cite(AE_UDCA, "2.1; 2.3")),
        ),
        monitoring = Facts(
            listOf(
                "Liver tests (γ-GT, ALP, AST, ALT) and bilirubin monthly for 3 months after starting, then every 6 months; consider stopping if a clinically significant rise.",
                "Watch for obstructive GI symptoms in patients at risk of intestinal stenosis or stasis.",
            ),
            listOf(cite(AE_UDCA, "2.2; 5.1–5.2")),
        ),
        commonEffects = Facts(
            listOf(
                "Abdominal discomfort or pain (≥1%)",
                "Diarrhoea (≥1%)",
                "Nausea (≥1%)",
                "Pruritus (≥1%)",
                "Rash (≥1%)",
                "Alopecia (≥1%)",
                "Leukopenia (2.6% vs 0 placebo at 24 months)",
            ),
            listOf(cite(AE_UDCA, "6 Adverse Reactions; 6.1")),
        ),
        seriousEffects = Facts(
            listOf(
                "Abnormal liver tests — consider stopping if a clinically significant rise; maintain bile flow.",
                "Enteroliths (bezoars) causing obstruction in patients with intestinal stenosis or stasis — hold and evaluate.",
            ),
            listOf(cite(AE_UDCA, "5.1–5.2 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Incomplete absorption by passive diffusion; ≈50% hepatic extraction (less in severe liver disease).",
            distribution = "≥70% of unconjugated drug protein bound; Vd small (mainly bile and small intestine).",
            metabolism = "Conjugated with glycine or taurine in the liver, secreted into bile and recycled; colonic bacteria convert it to lithocholic acid.",
            elimination = "Primarily in faeces; urinary excretion <1% except in severe cholestatic liver disease.",
            citations = listOf(cite(AE_UDCA, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Bile acid sequestrants (cholestyramine, colestipol): ↓ absorption.",
                "Aluminium-based antacids: may adsorb bile acids and ↓ effect.",
                "Oestrogens, oral contraceptives, clofibrate (perhaps other lipid-lowering drugs): may counteract effectiveness.",
            ),
            listOf(cite(AE_UDCA, "7.1–7.3 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Decades of published data have not identified a drug-associated risk of birth defects, miscarriage or other adverse outcomes."),
            listOf(cite(AE_UDCA, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Naturally present in human milk; no adverse effects reported, though reports are very limited."),
            listOf(cite(AE_UDCA, "8.2 Lactation")),
        ),
    ),
)
