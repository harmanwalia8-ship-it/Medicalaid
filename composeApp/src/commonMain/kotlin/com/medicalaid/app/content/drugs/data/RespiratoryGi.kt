package com.medicalaid.app.content.drugs.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.drugs.Clearance
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.Facts
import com.medicalaid.app.content.drugs.Pharmacokinetics

/*
 * Respiratory and GI drugs (plus IV iron). Each monograph paraphrases one official US FDA label
 * (DailyMed, via openFDA); see the template rules in Metabolic.kt.
 */

internal val respiratoryGiSources = listOf(
    Source(
        "fda-label-budesonide", Authority.FDA, "Drug label (DailyMed)",
        "Budesonide inhalation powder — Rubicon Holdings", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=ae1105cd-69fc-4c15-937f-c535304341c2",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-budesonide-formoterol", Authority.FDA, "Drug label (DailyMed)",
        "Budesonide and formoterol fumarate dihydrate inhalation aerosol — Mylan Pharmaceuticals", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=dc529fc4-bddb-4673-8202-dc401f86166f",
        status = "Revised Nov 2025",
    ),
    Source(
        "fda-label-salbutamol", Authority.FDA, "Drug label (DailyMed)",
        "Albuterol sulfate inhalation aerosol — Armstrong Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=17858afa-dcdf-4612-b475-3b6baadfb639",
        status = "Revised Aug 2026",
    ),
    Source(
        "fda-label-levosalbutamol", Authority.FDA, "Drug label (DailyMed)",
        "Levalbuterol inhalation solution — Ritedose Pharmaceuticals", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=d6998949-e1cb-4c86-a480-c0dfd9cb75b5",
        status = "Revised Dec 2025",
    ),
    Source(
        "fda-label-ipratropium", Authority.FDA, "Drug label (DailyMed)",
        "Ipratropium bromide inhalation aerosol — Boehringer Ingelheim Pharmaceuticals", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=170e98ef-5560-4068-be7d-e649068eb884",
        status = "Revised Nov 2025",
    ),
    Source(
        "fda-label-tiotropium", Authority.FDA, "Drug label (DailyMed)",
        "Tiotropium bromide inhalation powder — Lupin Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=496b38ee-45b6-4bb2-a35e-9023c3b73719",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-montelukast", Authority.FDA, "Drug label (DailyMed)",
        "Montelukast sodium tablets and chewable tablets — Unichem Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=9e375c07-70db-458c-9336-22a7037b0a5f",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-prednisolone", Authority.FDA, "Drug label (DailyMed)",
        "Prednisolone sodium phosphate orally disintegrating tablets — Advanz Pharma", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=1e379543-c4cf-4e72-953b-db15b7f0c2a1",
        status = "Revised Jun 2025",
    ),
    Source(
        "fda-label-omeprazole", Authority.FDA, "Drug label (DailyMed)",
        "Omeprazole delayed-release capsules — Dr. Reddy's Laboratories", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=603d2d06-c5e8-4378-a422-8dca1a410af7",
        status = "Revised Mar 2026",
    ),
    Source(
        "fda-label-pantoprazole", Authority.FDA, "Drug label (DailyMed)",
        "Pantoprazole sodium delayed-release tablets — Torrent Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=9c983da1-a8e0-479e-8816-5d5668ea242e",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-iron-sucrose", Authority.FDA, "Drug label (DailyMed)",
        "Iron sucrose injection — International Medication Systems", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=6f19c80e-22e9-4bab-aa53-4d2d958119ed",
        status = "Revised Jun 2026",
    ),
    Source(
        "fda-label-ferric-carboxymaltose", Authority.FDA, "Drug label (DailyMed)",
        "Ferric carboxymaltose injection — Mylan Institutional", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8e8e95f4-8d3a-4658-9f6c-09edf2210341",
        status = "Revised Sep 2026",
    ),
)

private const val SRC_BUDESONIDE = "fda-label-budesonide"
private const val SRC_BUD_FORM = "fda-label-budesonide-formoterol"
private const val SRC_SALBUTAMOL = "fda-label-salbutamol"
private const val SRC_LEVOSALBUTAMOL = "fda-label-levosalbutamol"
private const val SRC_IPRATROPIUM = "fda-label-ipratropium"
private const val SRC_TIOTROPIUM = "fda-label-tiotropium"
private const val SRC_MONTELUKAST = "fda-label-montelukast"
private const val SRC_PREDNISOLONE = "fda-label-prednisolone"
private const val SRC_OMEPRAZOLE = "fda-label-omeprazole"
private const val SRC_PANTOPRAZOLE = "fda-label-pantoprazole"
private const val SRC_IRON_SUCROSE = "fda-label-iron-sucrose"
private const val SRC_FCM = "fda-label-ferric-carboxymaltose"

internal val respiratoryGiDrugs = listOf(
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "budesonide",
        name = "Budesonide",
        drugClass = "Inhaled corticosteroid",
        uses = "Maintenance (prophylactic) treatment of asthma in patients ≥6 years; not for relief of acute bronchospasm.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Adults ≥18 y: start 360 mcg twice daily (180 mcg twice daily may suffice); max 720 mcg twice daily.",
                "Inhale deeply and forcefully; rinse mouth with water without swallowing after each use. Titrate to the lowest effective dose once stable.",
            ),
            listOf(cite(SRC_BUDESONIDE, "2 Dosage and Administration; 2.1 Asthma")),
        ),
        monitoring = Facts(
            listOf(
                "Oral cavity periodically for Candida infection.",
                "Bone mineral density in patients with major risk factors for low bone mass.",
                "Growth in children.",
                "Close eye monitoring if vision changes or history of raised intraocular pressure, glaucoma or cataracts.",
            ),
            listOf(cite(SRC_BUDESONIDE, "5.1; 5.8–5.10")),
        ),
        hepatic = Facts(
            listOf("Not formally studied; mainly cleared by hepatic metabolism, so may accumulate — monitor patients with hepatic disease closely."),
            listOf(cite(SRC_BUDESONIDE, "8.6 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Nasopharyngitis (9.3% vs 8.3% placebo)",
                "Nasal congestion (2.7%)",
                "Pharyngitis (2.7%)",
                "Allergic rhinitis (2.2%)",
                "Viral upper respiratory tract infection (2.2%)",
                "Nausea (1.8%)",
                "Viral gastroenteritis (1.8%)",
                "Oral candidiasis (1.3%)",
            ),
            listOf(cite(SRC_BUDESONIDE, "6.1 Clinical Trials Experience — Table 1")),
        ),
        seriousEffects = Facts(
            listOf(
                "Oropharyngeal Candida infection — treat; therapy may need interruption.",
                "Not a bronchodilator: do not use for acute symptoms; rapidly worsening asthma needs urgent re-evaluation.",
                "Hypersensitivity incl. anaphylaxis, angioedema, urticaria, bronchospasm — discontinue.",
                "Immunosuppression: worsening of TB, fungal, bacterial, viral or parasitic infection; serious or fatal chickenpox/measles in susceptible patients.",
                "Adrenal insufficiency when switching from systemic steroids (taper slowly); hypercorticism/adrenal suppression at high doses.",
                "Long-term: reduced bone mineral density, slowed growth in children, glaucoma, raised intraocular pressure, cataracts.",
                "Paradoxical bronchospasm — stop and start alternative therapy.",
                "Systemic eosinophilia / Churg-Strauss-type vasculitis, usually with oral steroid reduction or withdrawal.",
            ),
            listOf(cite(SRC_BUDESONIDE, "5.1–5.12 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Peak ≈10 min after inhalation (adults); most lung-deposited drug is absorbed; oral systemic availability 6–13%.",
            distribution = "Vd ≈3 L/kg; 85–90% protein bound.",
            metabolism = "Rapid, extensive CYP3A4 metabolism to 16α-hydroxyprednisolone and 6β-hydroxybudesonide (<1% of parent activity).",
            elimination = "As metabolites in urine and faeces (≈60% of an IV dose in urine); no unchanged drug in urine.",
            halfLife = "2–3 h (terminal).",
            citations = listOf(cite(SRC_BUDESONIDE, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf("Strong CYP3A4 inhibitors (ketoconazole, ritonavir, atazanavir, clarithromycin, itraconazole, etc.): ↑ systemic budesonide — use with caution."),
            listOf(cite(SRC_BUDESONIDE, "7.1 Inhibitors of Cytochrome P450 3A4")),
        ),
        pregnancy = Facts(
            listOf("No adequate controlled studies of this product, but studies of pregnant women have not shown that inhaled budesonide increases the risk of abnormalities."),
            listOf(cite(SRC_BUDESONIDE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk (infant dose ≈0.3–1% of the maternal inhaled dose); no data on the breastfed child — weigh benefits against maternal need."),
            listOf(cite(SRC_BUDESONIDE, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "budesonide-formoterol",
        name = "Budesonide–formoterol",
        drugClass = "Inhaled corticosteroid + long-acting beta2-agonist (ICS/LABA)",
        uses = "Asthma (≥6 years) not controlled on a long-term controller or needing ICS + LABA; COPD maintenance and exacerbation reduction (160/4.5 mcg). Not for acute bronchospasm.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Asthma, ≥12 y: 2 inhalations of 80/4.5 or 160/4.5 mcg twice daily, by severity; max 160/4.5 mcg, 2 inhalations twice daily.",
                "COPD: 160/4.5 mcg, 2 inhalations twice daily.",
                "Rinse mouth after use. Do not exceed 2 inhalations twice daily or add another LABA.",
            ),
            listOf(cite(SRC_BUD_FORM, "2.1–2.3 Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf("COPD: bone mineral density assessment is recommended before starting."),
            listOf(cite(SRC_BUD_FORM, "5.13 Reduction in Bone Mineral Density")),
        ),
        monitoring = Facts(
            listOf(
                "Oral cavity periodically for Candida infection.",
                "COPD: signs and symptoms of pneumonia and other lower respiratory tract infections.",
                "Bone mineral density periodically; growth in children.",
                "Close eye monitoring for glaucoma and cataracts.",
            ),
            listOf(cite(SRC_BUD_FORM, "5.4; 5.5; 5.13–5.15")),
        ),
        hepatic = Facts(
            listOf("Not formally studied; both components are mainly cleared by the liver and may accumulate — monitor patients with hepatic disease closely."),
            listOf(cite(SRC_BUD_FORM, "8.6 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache (6.5–11.3%, asthma)",
                "Upper respiratory tract infection (7.6–10.5%, asthma)",
                "Nasopharyngitis (9.7–10.5% asthma; 7.3% COPD)",
                "Pharyngolaryngeal pain (6.1–8.9%, asthma)",
                "Stomach discomfort (1.1–6.5%, asthma)",
                "Sinusitis (4.8–5.8% asthma; 3.5% COPD)",
                "Oral candidiasis (1.4–3.2% asthma; 6.0% COPD)",
                "Bronchitis (5.4%, COPD)",
            ),
            listOf(cite(SRC_BUD_FORM, "6.1–6.2 Clinical Trials Experience — Tables 2 and 3")),
        ),
        seriousEffects = Facts(
            listOf(
                "LABA monotherapy ↑ asthma-related death and hospitalisation; ICS/LABA trials showed no significant increase in serious asthma events vs ICS alone.",
                "Not for acute symptoms or acutely deteriorating asthma/COPD; never combine with another LABA (overdose risk).",
                "Oral candidiasis; increased risk of pneumonia in COPD.",
                "Immunosuppression: worsening of TB, fungal, viral or parasitic infection; serious or fatal chickenpox/measles in susceptible patients.",
                "Adrenal insufficiency on transfer from oral steroids (taper slowly); hypercorticism/adrenal suppression at high doses.",
                "Paradoxical bronchospasm (discontinue) and immediate hypersensitivity (urticaria, angioedema, rash, bronchospasm).",
                "Beta-agonist effects: tachycardia, arrhythmia, BP change, seizures; hypokalaemia, hyperglycaemia — caution in cardiac or convulsive disorders, thyrotoxicosis, diabetes.",
                "Long-term: reduced bone mineral density, slowed growth in children, glaucoma and cataracts.",
            ),
            listOf(cite(SRC_BUD_FORM, "5.1–5.18 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Budesonide peaks ≈20 min after inhalation; formoterol 5–10 min (much inhaled formoterol is likely swallowed and absorbed from the gut).",
            distribution = "Budesonide Vd ≈3 L/kg, 85–90% protein bound; formoterol 46–58% protein bound.",
            metabolism = "Budesonide: CYP3A4 to metabolites with <1% activity. Formoterol: direct glucuronidation and O-demethylation (CYP2D6, CYP2C).",
            elimination = "Budesonide as metabolites in urine and faeces. Formoterol: 62% urine, 24% faeces (radiolabel); ≈8% of dose unchanged in urine.",
            halfLife = "Budesonide 4.7 h with this inhaler (terminal 2–3 h in other data); formoterol not stated.",
            citations = listOf(cite(SRC_BUD_FORM, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Strong CYP3A4 inhibitors (e.g. ritonavir, ketoconazole): ↑ systemic corticosteroid effects — use with caution.",
                "MAOIs and tricyclic antidepressants: may potentiate formoterol's vascular effects — extreme caution.",
                "Beta-blockers: may block bronchodilation and cause severe bronchospasm — use with caution.",
                "Non-potassium-sparing diuretics: ECG changes and hypokalaemia may worsen — use with caution.",
            ),
            listOf(cite(SRC_BUD_FORM, "7.1–7.4 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No adequate studies of the combination or of formoterol in pregnant women; studies of inhaled budesonide alone have not shown an increased risk of abnormalities."),
            listOf(cite(SRC_BUD_FORM, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Budesonide is present in human milk; no data on formoterol in milk or on effects on the breastfed child — weigh benefits against maternal need."),
            listOf(cite(SRC_BUD_FORM, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "salbutamol",
        name = "Salbutamol",
        aliases = listOf("albuterol"),
        drugClass = "Short-acting beta2-agonist (SABA)",
        uses = "Treatment or prevention of bronchospasm in reversible obstructive airway disease and prevention of exercise-induced bronchospasm (≥4 years).",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Bronchospasm: 2 inhalations (90 mcg base each) every 4–6 h; 1 inhalation every 4 h may suffice. More frequent use or more inhalations is not recommended.",
                "Exercise-induced bronchospasm: 2 inhalations 15–30 min before exercise.",
            ),
            listOf(cite(SRC_SALBUTAMOL, "2.1–2.2; 3 Dosage Forms and Strengths")),
        ),
        monitoring = Facts(
            listOf(
                "Needing more doses than usual signals destabilising asthma — re-evaluate and consider anti-inflammatory therapy.",
                "With non-potassium-sparing diuretics consider monitoring potassium; with digoxin consider monitoring digoxin levels.",
            ),
            listOf(cite(SRC_SALBUTAMOL, "5.2; 7.2–7.3")),
        ),
        renal = Facts(
            listOf(
                "CrCl 7–53 mL/min: clearance fell 67% (half-life unchanged) — use caution with high doses.",
                "Substantially renally excreted; in older patients choose doses carefully and consider monitoring renal function.",
            ),
            listOf(cite(SRC_SALBUTAMOL, "12.2 Pharmacokinetics; 8.5 Geriatric Use")),
        ),
        commonEffects = Facts(
            listOf(
                "Pharyngitis (14% vs 9% placebo)",
                "Headache (7% vs 2%)",
                "Rhinitis (5% vs 2%)",
                "Tachycardia (3% vs 0%)",
                "Pain (3% vs 0%)",
                "Dizziness (3% vs 0%)",
            ),
            listOf(cite(SRC_SALBUTAMOL, "6.1 Clinical Trials Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Paradoxical bronchospasm, may be life-threatening (often with a new canister) — stop immediately.",
                "Deteriorating asthma: increasing use signals destabilisation; not a substitute for corticosteroids.",
                "Cardiovascular effects: pulse and BP changes; ECG changes (T-wave flattening, QTc prolongation, ST depression).",
                "Excessive use may be fatal — do not exceed the recommended dose.",
                "Immediate hypersensitivity: urticaria, angioedema, rash, bronchospasm, anaphylaxis, oropharyngeal oedema.",
                "Caution in coronary insufficiency, arrhythmias, hypertension, convulsive disorders, hyperthyroidism, diabetes.",
                "Hypokalaemia (usually transient); changes in blood glucose.",
            ),
            listOf(cite(SRC_SALBUTAMOL, "5.1–5.8 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Low systemic levels after inhalation of recommended doses.",
            metabolism = "Mainly by sulfotransferase (published data); the (R)-enantiomer is preferentially metabolised in the gut.",
            elimination = "Renal 80–100% (parent or main metabolite); <20% in faeces.",
            halfLife = "≈6 h in adults (high cumulative dose); ≈166 min in children 4–11 y.",
            citations = listOf(cite(SRC_SALBUTAMOL, "12.2 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Other short-acting sympathomimetic aerosols: do not use together; other adrenergic drugs — caution (cardiovascular effects).",
                "Beta-blockers: block the effect and may cause severe bronchospasm; if unavoidable, consider cardioselective agents with caution.",
                "Non-potassium-sparing (loop, thiazide) diuretics: ECG changes/hypokalaemia may worsen — consider monitoring potassium.",
                "Digoxin: serum levels fell 16–22% after single salbutamol doses — consider monitoring digoxin levels.",
                "MAOIs or tricyclic antidepressants: potentiate cardiovascular effects — consider alternative therapy.",
            ),
            listOf(cite(SRC_SALBUTAMOL, "7 Drug Interactions; 7.1–7.4")),
        ),
        pregnancy = Facts(
            listOf("No randomised studies; published epidemiological data and case reports do not consistently show a risk of major birth defects or miscarriage."),
            listOf(cite(SRC_SALBUTAMOL, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No data on presence in milk; plasma levels after inhalation are low and oral bioavailability is low — weigh benefits against maternal need."),
            listOf(cite(SRC_SALBUTAMOL, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "levosalbutamol",
        name = "Levosalbutamol",
        aliases = listOf("levalbuterol"),
        drugClass = "Short-acting beta2-agonist (SABA); (R)-enantiomer of salbutamol",
        uses = "Treatment or prevention of bronchospasm in reversible obstructive airway disease (≥6 years), by nebuliser.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "≥12 y: 0.63 mg three times daily (every 6–8 h) by jet nebuliser; 1.25 mg three times daily if severe or inadequate response (maximum).",
                "Children 6–11 y: 0.31 mg three times daily; routine dosing should not exceed 0.63 mg three times daily.",
            ),
            listOf(cite(SRC_LEVOSALBUTAMOL, "2 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "On 1.25 mg three times daily: monitor closely for systemic adverse effects.",
                "Loss of the usual response signals destabilising asthma — re-evaluate and consider corticosteroids.",
                "With non-potassium-sparing diuretics consider monitoring potassium; with digoxin evaluate digoxin levels.",
            ),
            listOf(cite(SRC_LEVOSALBUTAMOL, "2; 5.2; 7.3–7.4")),
        ),
        renal = Facts(
            listOf(
                "CrCl 7–53 mL/min: racemic albuterol clearance fell 67% (half-life unchanged) — use caution with high doses.",
                "Substantially renally excreted; toxicity risk may be greater — consider monitoring renal function in older patients.",
            ),
            listOf(cite(SRC_LEVOSALBUTAMOL, "8.6 Renal Impairment; 12.3")),
        ),
        commonEffects = Facts(
            listOf(
                "Nervousness (9.6% at 1.25 mg; 2.8% at 0.63 mg; 0% placebo)",
                "Tremor (6.8% at 1.25 mg; 0% placebo)",
                "Rhinitis (11.1% at 0.63 mg vs 2.7% placebo)",
                "Flu syndrome (4.2% at 0.63 mg vs 0%)",
                "Tachycardia (2.7–2.8% vs 0%)",
                "Dizziness (2.7% at 1.25 mg vs 1.3%)",
                "Also listed among the most common: palpitations, chest pain, headache",
            ),
            listOf(cite(SRC_LEVOSALBUTAMOL, "6 Adverse Reactions; 6.1 — Table 1 (≥12 y)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Paradoxical bronchospasm, may be life-threatening — stop immediately.",
                "Deteriorating asthma if more doses are needed; not a substitute for corticosteroids.",
                "Cardiovascular effects (pulse, BP, ECG changes) — consider stopping; caution in cardiovascular disorders.",
                "Excessive use may be fatal — do not exceed the recommended dose.",
                "Immediate hypersensitivity (urticaria, angioedema, rash, bronchospasm, anaphylaxis, oropharyngeal oedema) — stop immediately.",
                "Caution in convulsive disorders, hyperthyroidism, diabetes; blood glucose may change.",
                "Hypokalaemia (usually transient).",
            ),
            listOf(cite(SRC_LEVOSALBUTAMOL, "5.1–5.8 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Median Tmax ≈0.2 h after nebulisation; 1.25 mg gives ≈2× the (R)-albuterol AUC of racemic albuterol 2.5 mg.",
            metabolism = "Mainly SULT1A3 (sulfotransferase), per published data; the (R)-enantiomer is preferentially metabolised in the gut.",
            elimination = "Renal 80–100% (parent or main metabolite); <20% in faeces.",
            halfLife = "≈3.3 h (single 1.25 mg dose); ≈4 h after cumulative dosing.",
            citations = listOf(cite(SRC_LEVOSALBUTAMOL, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Other short-acting sympathomimetic bronchodilators or epinephrine: avoid; other adrenergic drugs — caution.",
                "Beta-blockers: block the effect and may cause severe bronchospasm; if unavoidable, consider cardioselective agents with caution.",
                "Non-potassium-sparing diuretics: ECG changes/hypokalaemia may worsen — consider monitoring potassium.",
                "Digoxin: serum levels may fall — evaluate digoxin levels.",
                "MAOIs or tricyclic antidepressants (or within 2 weeks of stopping): extreme caution — consider alternative therapy.",
            ),
            listOf(cite(SRC_LEVOSALBUTAMOL, "7.1–7.5 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No adequate well-controlled studies in pregnant women; racemic albuterol was teratogenic in mice and rabbits at doses slightly above the human therapeutic range."),
            listOf(cite(SRC_LEVOSALBUTAMOL, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No data on presence in human milk or effects on the breastfed child — weigh benefits of breastfeeding against maternal need."),
            listOf(cite(SRC_LEVOSALBUTAMOL, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "ipratropium",
        name = "Ipratropium",
        drugClass = "Anticholinergic bronchodilator",
        uses = "Maintenance treatment of bronchospasm in COPD (chronic bronchitis, emphysema); not for initial treatment of acute bronchospasm.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "2 inhalations (17 mcg each) four times daily; extra inhalations as required, max 12 inhalations in 24 h.",
                "Prime with 2 sprays before first use or if unused >3 days; no shaking needed; avoid spraying into the eyes.",
            ),
            listOf(cite(SRC_IPRATROPIUM, "2 Dosage and Administration; 3")),
        ),
        monitoring = Facts(
            listOf("Patients should seek care at once for eye pain, blurred vision, halos or red eyes (narrow-angle glaucoma) or signs of urinary retention."),
            listOf(cite(SRC_IPRATROPIUM, "5.4–5.5")),
        ),
        commonEffects = Facts(
            listOf(
                "Bronchitis (10% vs 6% placebo)",
                "Dyspnoea (8% vs 4%)",
                "COPD exacerbation (8%; placebo 13%)",
                "Headache (6%; placebo 8%)",
                "Dry mouth (4% vs 2%)",
                "Nausea (4% vs 2%)",
                "Influenza-like symptoms (4% vs 2%)",
                "Dizziness (3% vs 2%)",
            ),
            listOf(cite(SRC_IPRATROPIUM, "6.1 Clinical Trials Experience — Table 1 (12-week studies)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Not for initial treatment of acute bronchospasm needing rapid rescue.",
                "Hypersensitivity incl. anaphylaxis, angioedema, urticaria, laryngospasm — stop at once.",
                "Paradoxical bronchospasm, can be life-threatening — stop.",
                "May raise intraocular pressure and precipitate or worsen narrow-angle glaucoma.",
                "Urinary retention — caution in prostatic hyperplasia or bladder-neck obstruction.",
            ),
            listOf(cite(SRC_IPRATROPIUM, "5.1–5.5 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Quaternary amine: not readily absorbed from lung or gut (most of the dose deposits in the GI tract).",
            distribution = "Minimal protein binding (0–9% in vitro).",
            metabolism = "Partly metabolised to inactive ester hydrolysis products.",
            elimination = "After IV dosing, about half is excreted unchanged in urine.",
            halfLife = "≈2 h.",
            citations = listOf(cite(SRC_IPRATROPIUM, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf("Other anticholinergic drugs: additive anticholinergic effects — avoid coadministration."),
            listOf(cite(SRC_IPRATROPIUM, "7.1 Anticholinergic Agents")),
        ),
        pregnancy = Facts(
            listOf("Negligible systemic absorption, so fetal exposure is not expected; decades of published data have not identified a drug-associated risk of birth defects or miscarriage."),
            listOf(cite(SRC_IPRATROPIUM, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No data; plasma levels after inhalation are low, so milk levels are expected to be low — weigh benefits against maternal need."),
            listOf(cite(SRC_IPRATROPIUM, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "tiotropium",
        name = "Tiotropium",
        drugClass = "Anticholinergic bronchodilator (once daily)",
        uses = "Long-term once-daily maintenance treatment of bronchospasm in COPD and reduction of COPD exacerbations.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf("One 18 mcg capsule once daily: inhale its powder in 2 inhalations using the supplied inhaler; max one dose in 24 h. Do not swallow capsules."),
            listOf(cite(SRC_TIOTROPIUM, "2 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "CrCl <60 mL/min: monitor closely for anticholinergic side effects.",
                "Patients should report eye pain, blurred vision, halos or red eyes (narrow-angle glaucoma) or difficulty passing urine immediately.",
            ),
            listOf(cite(SRC_TIOTROPIUM, "5.4–5.6")),
        ),
        renal = Facts(
            listOf(
                "No dose adjustment required.",
                "CrCl <60 mL/min: monitor closely for anticholinergic effects (AUC ↑54–57% at CrCl 30–<60).",
            ),
            listOf(cite(SRC_TIOTROPIUM, "2; 8.6 Renal Impairment; 12.3")),
        ),
        hepatic = Facts(
            listOf("No dose adjustment required."),
            listOf(cite(SRC_TIOTROPIUM, "2 Dosage and Administration")),
        ),
        commonEffects = Facts(
            listOf(
                "Upper respiratory tract infection (41% vs 37% placebo)",
                "Dry mouth (16% vs 3%)",
                "Sinusitis (11% vs 9%)",
                "Pharyngitis (9% vs 7%)",
                "Non-specific chest pain (7% vs 5%)",
                "Urinary tract infection (7% vs 5%)",
                "Dyspepsia (6% vs 5%)",
                "Rhinitis (6% vs 5%)",
            ),
            listOf(cite(SRC_TIOTROPIUM, "6.1 Clinical Trials Experience — 1-year placebo-controlled trials")),
        ),
        seriousEffects = Facts(
            listOf(
                "Not a rescue medication for acute bronchospasm.",
                "Immediate hypersensitivity incl. angioedema, urticaria, anaphylaxis — stop; caution with severe milk-protein hypersensitivity.",
                "Paradoxical bronchospasm — stop and consider other treatment.",
                "Worsening of narrow-angle glaucoma.",
                "Worsening of urinary retention — caution in prostatic hyperplasia or bladder-neck obstruction.",
                "Renal impairment (CrCl <60 mL/min): risk of anticholinergic side effects.",
            ),
            listOf(cite(SRC_TIOTROPIUM, "5.1–5.6 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Absolute bioavailability 19.5% after dry-powder inhalation (oral solution 2–3%); peak at 7 min; food not expected to matter.",
            distribution = "72% protein bound; Vd 32 L/kg.",
            metabolism = "Small: non-enzymatic ester cleavage to inactive products; minor CYP2D6/CYP3A4 oxidation.",
            elimination = "IV dose: 74% unchanged in urine (active secretion); after inhalation 7% of dose unchanged in urine over 24 h.",
            halfLife = "≈25 h in COPD (once-daily inhalation); steady state by day 7.",
            citations = listOf(cite(SRC_TIOTROPIUM, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Other anticholinergic drugs: additive effects — avoid coadministration.",
                "Beta-agonists, methylxanthines and oral/inhaled steroids have been used together without more adverse reactions.",
            ),
            listOf(cite(SRC_TIOTROPIUM, "7.1–7.2 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Limited human data are insufficient to inform drug-associated risk; no structural abnormalities in rats or rabbits at 790 and 8 times the maximum recommended human dose."),
            listOf(cite(SRC_TIOTROPIUM, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No human data; present in rat milk — weigh benefits of breastfeeding against maternal need."),
            listOf(cite(SRC_TIOTROPIUM, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "montelukast",
        name = "Montelukast",
        drugClass = "Leukotriene receptor antagonist",
        uses = "Prophylaxis and chronic treatment of asthma (≥2 years); prevention of exercise-induced bronchoconstriction (≥6 years); allergic rhinitis only if alternatives fail or are not tolerated.",
        clearance = setOf(Clearance.Hepatic, Clearance.Biliary),
        boxedWarning = Facts(
            listOf(
                "Serious neuropsychiatric events (agitation, aggression, depression, sleep disturbance, suicidal thoughts/behaviour incl. suicide); in allergic rhinitis reserve for failure of or intolerance to alternatives.",
                "Discuss risks; stop and contact a provider if behaviour changes, new neuropsychiatric symptoms or suicidal thoughts occur.",
            ),
            listOf(cite(SRC_MONTELUKAST, "Boxed Warning; 5.1")),
        ),
        dose = Facts(
            listOf(
                "Asthma (≥15 y): 10 mg once daily in the evening, with or without food.",
                "Exercise-induced bronchoconstriction: 10 mg at least 2 h before exercise; no extra dose within 24 h or if already taking it daily.",
                "Allergic rhinitis: 10 mg once daily; asthma plus rhinitis — one dose daily in the evening.",
            ),
            listOf(cite(SRC_MONTELUKAST, "2.1–2.4 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Neuropsychiatric symptoms and behaviour change throughout treatment; some events occurred after stopping — monitor until resolved.",
                "Be alert to eosinophilia, vasculitic rash, worsening lung symptoms, cardiac complications or neuropathy (esp. when oral steroids are reduced).",
            ),
            listOf(cite(SRC_MONTELUKAST, "Boxed Warning; 5.1; 5.5")),
        ),
        renal = Facts(
            listOf("No dosage adjustment recommended."),
            listOf(cite(SRC_MONTELUKAST, "8.7 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf(
                "Mild–moderate hepatic insufficiency: no dosage adjustment (AUC ≈41% higher).",
                "Severe hepatic impairment or hepatitis: not evaluated.",
            ),
            listOf(cite(SRC_MONTELUKAST, "8.6 Hepatic Impairment; 12.3")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache (18.4% vs 18.1% placebo)",
                "Influenza (4.2% vs 3.9%)",
                "Abdominal pain (2.9% vs 2.5%)",
                "Cough (2.7% vs 2.4%)",
                "Dyspepsia (2.1% vs 1.1%)",
                "Raised ALT (2.1%) or AST (1.6%)",
                "Dizziness (1.9% vs 1.4%)",
                "All ages (≥5%, > placebo): upper respiratory infection, fever, headache, pharyngitis, cough, abdominal pain, diarrhoea, otitis media",
            ),
            listOf(cite(SRC_MONTELUKAST, "6.1 Clinical Trials Experience — Table 5 (adults ≥15 y)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Serious neuropsychiatric events incl. suicidal thoughts and behaviour (boxed warning).",
                "Not for acute asthma attacks; keep rescue medication available.",
                "Do not abruptly substitute for inhaled or oral corticosteroids.",
                "Aspirin-sensitive patients must continue to avoid aspirin and NSAIDs.",
                "Systemic eosinophilia / Churg-Strauss-type vasculitis, sometimes with oral steroid reduction.",
                "Chewable tablets contain phenylalanine — consider in phenylketonuria.",
            ),
            listOf(cite(SRC_MONTELUKAST, "Boxed Warning; 5.1–5.6")),
        ),
        pk = Pharmacokinetics(
            absorption = "Oral bioavailability 64% (10 mg tablet); Tmax 3–4 h; a standard morning meal has no effect.",
            distribution = ">99% protein bound; Vd 8–11 L.",
            metabolism = "Extensive: CYP2C8 (major), CYP3A4 and CYP2C9; metabolites undetectable at steady state.",
            elimination = "Almost exclusively via bile: 86% in faeces, <0.2% in urine.",
            halfLife = "2.7–5.5 h (healthy young adults).",
            citations = listOf(cite(SRC_MONTELUKAST, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf("No dose adjustment needed with theophylline, prednisone, prednisolone, oral contraceptives, digoxin, warfarin, gemfibrozil, itraconazole, thyroid hormones, NSAIDs or CYP inducers."),
            listOf(cite(SRC_MONTELUKAST, "7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Published cohort studies over decades have not established a drug-associated risk of major birth defects."),
            listOf(cite(SRC_MONTELUKAST, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk; available data do not suggest a significant risk to the infant — weigh benefits against maternal need."),
            listOf(cite(SRC_MONTELUKAST, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "prednisolone",
        name = "Prednisolone",
        drugClass = "Corticosteroid (glucocorticoid)",
        uses = "Anti-inflammatory or immunosuppressive use in allergic, respiratory (asthma, COPD exacerbations), rheumatic, skin, GI, blood, renal, eye and nervous system disorders; some endocrine and neoplastic conditions.",
        clearance = setOf(Clearance.Hepatic, Clearance.Renal),
        dose = Facts(
            listOf(
                "Initial 10–60 mg/day (prednisolone base), individualised to disease and response; then reduce in small steps to the lowest effective maintenance dose.",
                "Take with food. Withdraw gradually after long-term or high-dose use. Orally disintegrating tablets must not be split — use another formulation for doses below 10 mg.",
                "Multiple sclerosis exacerbation: 200 mg/day for 1 week, then 80 mg every other day for 1 month.",
            ),
            listOf(cite(SRC_PREDNISOLONE, "2 Dosage and Administration; 2.1")),
        ),
        beforeStarting = Facts(
            listOf(
                "Screen for hepatitis B before immunosuppressive (e.g. prolonged) treatment.",
                "Rule out latent or active amoebiasis in patients who have been in the tropics or have unexplained diarrhoea.",
                "Consider osteoporosis risk (e.g. postmenopausal women) before starting.",
            ),
            listOf(cite(SRC_PREDNISOLONE, "5.2; 5.6")),
        ),
        monitoring = Facts(
            listOf(
                "Prolonged therapy: blood pressure, body weight, serum potassium and fasting glucose at regular intervals.",
                "Diagnostic studies if peptic ulcer is known or suspected or latent TB may reactivate; monitor latent TB closely (chemoprophylaxis if prolonged).",
                "Intraocular pressure if therapy exceeds 6 weeks; bone density on long-term therapy; growth in children.",
                "HPA-axis suppression, Cushing's syndrome and hyperglycaemia with chronic use.",
            ),
            listOf(cite(SRC_PREDNISOLONE, "2.2 Recommended Monitoring; 5.1–5.9")),
        ),
        commonEffects = Facts(
            listOf(
                "Fluid retention",
                "Altered glucose tolerance",
                "Raised blood pressure",
                "Behavioural and mood changes",
                "Increased appetite and weight gain",
            ),
            listOf(cite(SRC_PREDNISOLONE, "6 Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "HPA-axis suppression (adrenal insufficiency after withdrawal — taper), Cushing's syndrome, hyperglycaemia.",
                "Immunosuppression: new, disseminated or reactivated infection (TB, hepatitis B, fungal, amoebiasis, Strongyloides), signs may be masked; serious varicella/measles; avoid in cerebral malaria.",
                "Raised BP, salt and water retention, hypokalaemia — caution in hypertension, heart failure, renal insufficiency.",
                "GI perforation risk in certain GI disorders; signs may be masked.",
                "Mood and behavioural disturbance, from euphoria and insomnia to severe depression and psychosis.",
                "Long-term: osteoporosis, growth suppression in children, cataracts, glaucoma, secondary eye infections.",
                "Embryo-fetal toxicity: small, inconsistent increase in orofacial clefts with first-trimester use.",
                "Live vaccines contraindicated at immunosuppressive doses; acute myopathy with high doses.",
            ),
            listOf(cite(SRC_PREDNISOLONE, "5.1–5.11 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "30 mg orally disintegrating tablet gives AUC and Cmax comparable to prednisolone oral solution (fasting).",
            distribution = "70–90% protein bound; Vd 0.22–0.7 L/kg.",
            metabolism = "Mainly hepatic.",
            elimination = "In urine as sulfate and glucuronide conjugates.",
            halfLife = "≈2.6 h (plasma).",
            citations = listOf(cite(SRC_PREDNISOLONE, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "CYP3A4 inducers (rifampin, phenytoin, carbamazepine, barbiturates): ↑ metabolism — dose may need increasing; inhibitors (ketoconazole, macrolides): ↑ steroid effects.",
                "Warfarin: response usually inhibited — monitor coagulation indices frequently.",
                "Antidiabetic agents: blood glucose may rise — dose adjustment may be needed.",
                "Aspirin/NSAIDs: ↑ GI adverse effects.",
                "Potassium-depleting drugs (diuretics, amphotericin B): watch for hypokalaemia; digitalis — arrhythmia risk from hypokalaemia.",
            ),
            listOf(cite(SRC_PREDNISOLONE, "7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Can cause fetal harm: small but inconsistent increase in orofacial clefts with first-trimester use; growth restriction and lower birth weight reported."),
            listOf(cite(SRC_PREDNISOLONE, "8.1 Pregnancy; 5.10")),
        ),
        lactation = Facts(
            listOf("Present in milk (infant dose <1% of maternal dose) with no reported infant adverse effects; high long-term doses could affect infant growth and adrenal function."),
            listOf(cite(SRC_PREDNISOLONE, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "omeprazole",
        name = "Omeprazole",
        drugClass = "Proton pump inhibitor (PPI)",
        uses = "Duodenal and benign gastric ulcer, H. pylori eradication (with antibiotics), GERD and erosive oesophagitis (treatment and maintenance), pathological hypersecretory conditions.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Duodenal ulcer: 20 mg once daily for 4 weeks (+4 weeks if needed). Benign gastric ulcer: 40 mg once daily for 4–8 weeks.",
                "GERD: 20 mg once daily up to 4 weeks; erosive oesophagitis 20 mg daily for 4–8 weeks, maintenance 20 mg daily (10 mg if Asian or hepatic impairment).",
                "H. pylori: 20 mg + amoxicillin 1000 mg + clarithromycin 500 mg, all twice daily for 10 days. Hypersecretory states: start 60 mg daily; up to 120 mg three times daily used.",
            ),
            listOf(cite(SRC_OMEPRAZOLE, "2.1 Recommended Adult Dosage — Table 1")),
        ),
        beforeStarting = Facts(
            listOf("Consider serum magnesium if prolonged use is expected or with digoxin/diuretics; magnesium and calcium if at risk of hypocalcaemia (e.g. hypoparathyroidism)."),
            listOf(cite(SRC_OMEPRAZOLE, "5.9 Hypomagnesemia and Mineral Metabolism")),
        ),
        monitoring = Facts(
            listOf(
                "Magnesium (and calcium if at risk) periodically in the patients above.",
                "Suboptimal response or early relapse: consider further testing for gastric malignancy (endoscopy in older patients).",
                "Long-term (>3 years) daily use: consider vitamin B12 deficiency if symptoms arise.",
                "Stop at least 14 days before chromogranin A testing.",
            ),
            listOf(cite(SRC_OMEPRAZOLE, "5.1; 5.8; 5.9; 5.11")),
        ),
        hepatic = Facts(
            listOf("Child-Pugh A, B or C: exposure substantially increased — reduce to 10 mg once daily for maintenance of healing of erosive oesophagitis."),
            listOf(cite(SRC_OMEPRAZOLE, "2.1; 8.6 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache (7%)",
                "Abdominal pain (5%)",
                "Nausea (4%)",
                "Diarrhoea (4%)",
                "Vomiting (3%)",
                "Flatulence (3%)",
                "Acid regurgitation (2%)",
                "Constipation, dizziness, rash, upper respiratory infection (2% each)",
            ),
            listOf(cite(SRC_OMEPRAZOLE, "6.1 Clinical Trials Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Symptom response does not exclude gastric malignancy.",
                "Acute tubulointerstitial nephritis — stop and evaluate.",
                "Clostridium difficile-associated diarrhoea.",
                "Osteoporosis-related hip, wrist or spine fractures with long-term or multiple daily dosing.",
                "Severe cutaneous adverse reactions; cutaneous or systemic lupus — discontinue.",
                "Vitamin B12 deficiency with daily use >3 years.",
                "Hypomagnesaemia (tetany, arrhythmias, seizures) after ≥3 months, mostly after a year; may cause hypocalcaemia or hypokalaemia.",
                "Fundic gland polyps with long-term use (esp. >1 year) — use the shortest suitable duration.",
            ),
            listOf(cite(SRC_OMEPRAZOLE, "5.1–5.13 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Enteric-coated; peak 0.5–3.5 h; bioavailability 30–40% (20–40 mg), rising slightly with repeated doses.",
            distribution = "≈95% protein bound.",
            metabolism = "Extensive: mainly CYP2C19 (polymorphic) to hydroxyomeprazole, CYP3A4 to sulphone; metabolites have little or no activity. Time-dependent CYP2C19 inhibitor.",
            elimination = "≈77% as metabolites in urine, rest in faeces (biliary); little unchanged drug in urine.",
            halfLife = "0.5–1 h (≈3 h in hepatic impairment).",
            citations = listOf(cite(SRC_OMEPRAZOLE, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Clopidogrel: less active metabolite and platelet inhibition — avoid; consider alternative antiplatelet therapy.",
                "Rilpivirine: contraindicated; atazanavir, nelfinavir: avoid (reduced antiviral exposure).",
                "Warfarin: ↑ INR and prothrombin time, bleeding risk — monitor INR.",
                "High-dose methotrexate: ↑ levels and toxicity — consider temporarily stopping omeprazole.",
                "Rifampin, St John's Wort (CYP2C19/3A4 inducers): ↓ omeprazole levels — avoid.",
            ),
            listOf(cite(SRC_OMEPRAZOLE, "4; 5.10; 7 Drug Interactions — Table 3")),
        ),
        pregnancy = Facts(
            listOf("No adequate studies; epidemiologic data fail to show an increased risk of major malformations or other adverse outcomes with first-trimester use."),
            listOf(cite(SRC_OMEPRAZOLE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Limited data suggest presence in milk; no data on infant effects or milk production — weigh benefits against maternal need."),
            listOf(cite(SRC_OMEPRAZOLE, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "pantoprazole",
        name = "Pantoprazole",
        drugClass = "Proton pump inhibitor (PPI)",
        uses = "Erosive oesophagitis with GERD (short-term treatment ≥5 years; maintenance of healing in adults) and pathological hypersecretory conditions incl. Zollinger-Ellison syndrome.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Erosive oesophagitis (GERD): 40 mg once daily for up to 8 weeks; a further 8-week course may be considered if not healed.",
                "Maintenance of healing: 40 mg once daily (studied up to 12 months).",
                "Zollinger-Ellison/hypersecretory states: 40 mg twice daily, adjusted to need; up to 240 mg/day has been given. Swallow whole, with or without food.",
            ),
            listOf(cite(SRC_PANTOPRAZOLE, "2.1–2.2 Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf("Consider serum magnesium if prolonged use is expected or with digoxin/diuretics; magnesium and calcium if at risk of hypocalcaemia (e.g. hypoparathyroidism)."),
            listOf(cite(SRC_PANTOPRAZOLE, "5.8 Hypomagnesemia and Mineral Metabolism")),
        ),
        monitoring = Facts(
            listOf(
                "Magnesium (and calcium if at risk) periodically in the patients above.",
                "Suboptimal response or early relapse: consider further testing for gastric malignancy (endoscopy in older patients).",
                "Long-term (>3 years) daily use: consider vitamin B12 deficiency if symptoms arise.",
                "Stop at least 14 days before chromogranin A testing.",
            ),
            listOf(cite(SRC_PANTOPRAZOLE, "5.1; 5.7; 5.8; 5.11")),
        ),
        hepatic = Facts(
            listOf("Child-Pugh A–C: AUC ↑5–7-fold and half-life 7–9 h, with minimal accumulation once daily; doses >40 mg/day not studied."),
            listOf(cite(SRC_PANTOPRAZOLE, "12.3 Pharmacokinetics — Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache (12.2% vs 8.5% placebo)",
                "Diarrhoea (8.8% vs 4.9%)",
                "Nausea (7.0% vs 9.8%)",
                "Abdominal pain (6.2% vs 6.1%)",
                "Vomiting (4.3% vs 2.4%)",
                "Flatulence (3.9% vs 3.7%)",
                "Dizziness (3.0% vs 1.2%)",
                "Arthralgia (2.8% vs 1.2%)",
            ),
            listOf(cite(SRC_PANTOPRAZOLE, "6.1 Clinical Trials Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Symptom response does not exclude gastric malignancy.",
                "Acute tubulointerstitial nephritis — stop and evaluate.",
                "Clostridium difficile-associated diarrhoea.",
                "Osteoporosis-related hip, wrist or spine fractures with long-term or multiple daily dosing.",
                "Severe cutaneous adverse reactions; cutaneous or systemic lupus — discontinue.",
                "Vitamin B12 deficiency with daily use >3 years.",
                "Hypomagnesaemia (tetany, arrhythmias, seizures) after ≥3 months, mostly after a year; may cause hypocalcaemia or hypokalaemia.",
                "Fundic gland polyps with long-term use (esp. >1 year) — use the shortest suitable duration.",
            ),
            listOf(cite(SRC_PANTOPRAZOLE, "5.1–5.10 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability ≈77%; Tmax ≈2.5 h (40 mg); food may delay absorption but does not change its extent.",
            distribution = "≈98% protein bound (mainly albumin); Vd 11–23.6 L.",
            metabolism = "Extensive hepatic: CYP2C19 demethylation then sulfation; CYP3A4 oxidation; metabolites not significantly active.",
            elimination = "≈71% in urine and 18% in faeces (biliary) as metabolites; no unchanged drug in urine.",
            halfLife = "≈1 h.",
            citations = listOf(cite(SRC_PANTOPRAZOLE, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Rilpivirine: contraindicated; nelfinavir: avoid; atazanavir, saquinavir: see their labels (altered exposure).",
                "Warfarin: ↑ INR and prothrombin time, bleeding risk — monitor INR.",
                "High-dose methotrexate: ↑ levels and toxicity — consider temporarily stopping pantoprazole.",
                "Drugs needing gastric acid (iron salts, erlotinib, dasatinib, nilotinib, ketoconazole/itraconazole, mycophenolate): reduced absorption.",
                "Clopidogrel: no clinically important effect — no dose adjustment needed.",
            ),
            listOf(cite(SRC_PANTOPRAZOLE, "4; 7 Drug Interactions — Table 4")),
        ),
        pregnancy = Facts(
            listOf("Observational studies show no association with major malformations; a rat study showed bone changes in exposed pups — advise of potential fetal risk."),
            listOf(cite(SRC_PANTOPRAZOLE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Detected in breast milk after a single 40 mg dose with no effects on the breastfed infant; no data on milk production."),
            listOf(cite(SRC_PANTOPRAZOLE, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "iron-sucrose",
        name = "Iron sucrose",
        drugClass = "Parenteral iron replacement",
        uses = "Iron deficiency anaemia in chronic kidney disease (adults; iron maintenance in children ≥2 years).",
        // Iron goes to body stores (liver, spleen, marrow); ≈5% of iron in urine per 24 h — no main clearance route (12.3).
        clearance = emptySet(),
        dose = Facts(
            listOf(
                "Haemodialysis CKD: 100 mg IV (undiluted over 2–5 min or infusion ≥15 min) per dialysis session; usual course 1000 mg.",
                "Non-dialysis CKD: 200 mg IV over 2–5 min (or 15-min infusion) on 5 occasions within 14 days.",
                "Peritoneal dialysis CKD: 300 mg over 1.5 h, repeated after 14 days, then 400 mg over 2.5 h 14 days later. Courses may be repeated if deficiency recurs.",
            ),
            listOf(cite(SRC_IRON_SUCROSE, "2.2–2.4 Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf(
                "Give only where staff and treatment for serious hypersensitivity reactions are immediately available.",
                "Do not give if there is evidence of iron overload.",
            ),
            listOf(cite(SRC_IRON_SUCROSE, "5.1; 5.3")),
        ),
        monitoring = Facts(
            listOf(
                "Observe for hypersensitivity during and for at least 30 min after each dose, until clinically stable.",
                "Watch for hypotension during and after each dose (may relate to rate and total dose).",
                "Periodically check haemoglobin, haematocrit, serum ferritin and transferrin saturation; do not measure serum iron within 48 h of a dose.",
            ),
            listOf(cite(SRC_IRON_SUCROSE, "5.1–5.3 Warnings and Precautions")),
        ),
        commonEffects = Facts(
            listOf(
                "Hypotension (39.4% in haemodialysis patients)",
                "Muscle cramps (29.4% in haemodialysis patients)",
                "Nausea (5.3–14.7%)",
                "Headache (2.9–12.6%)",
                "Vomiting (5.0–9.1%)",
                "Diarrhoea (5.2–8.0%)",
                "Dizziness (1.3–6.5%)",
                "Peripheral oedema (2.6–7.2%); chest pain (up to 6.1%)",
            ),
            listOf(cite(SRC_IRON_SUCROSE, "6.1 Adverse Reactions in Clinical Trials — Table 1 (adults with CKD)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Serious hypersensitivity incl. life-threatening or fatal anaphylactic-type reactions (shock, hypotension, collapse) — stop immediately.",
                "Clinically significant hypotension, possibly related to infusion rate and total dose.",
                "Iron overload / iatrogenic haemosiderosis with excessive therapy.",
            ),
            listOf(cite(SRC_IRON_SUCROSE, "5.1–5.3 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            distribution = "Vd ≈7.9 L (steady state); iron is taken up mainly by liver, spleen and bone marrow.",
            metabolism = "Dissociates into iron and sucrose after IV dosing.",
            elimination = "Sucrose mainly in urine (75.4% in 24 h); ≈5% of iron in urine in 24 h. Not removed by dialysis.",
            halfLife = "≈6 h (iron component, healthy adults).",
            citations = listOf(cite(SRC_IRON_SUCROSE, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf("May reduce absorption of concomitant oral iron."),
            listOf(cite(SRC_IRON_SUCROSE, "7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Studies after the first trimester show no adverse maternal or fetal outcomes (first-trimester data insufficient); maternal hypersensitivity or hypotension may cause fetal bradycardia."),
            listOf(cite(SRC_IRON_SUCROSE, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk; published reports after 100–300 mg IV have not reported adverse reactions in breastfed infants."),
            listOf(cite(SRC_IRON_SUCROSE, "8.2 Lactation")),
        ),
    ),
    // -----------------------------------------------------------------------------------------
    DrugMonograph(
        id = "ferric-carboxymaltose",
        name = "Ferric carboxymaltose",
        drugClass = "Parenteral iron replacement",
        uses = "Iron deficiency anaemia with oral-iron intolerance or poor response (≥1 year) or non-dialysis CKD (adults); iron deficiency in NYHA II/III heart failure.",
        // Iron is rapidly cleared from plasma to body stores; renal elimination negligible (12.3).
        clearance = emptySet(),
        boxedWarning = Facts(
            listOf(
                "Severe, prolonged symptomatic hypophosphataemia with hospitalisation, osteomalacia and fractures — also in patients with normal baseline phosphate and no risk factors.",
                "Check phosphate before a repeat course if at risk or if repeating within 3 months; correct hypophosphataemia first; patients to report fatigue, muscle/bone pain, fractures.",
            ),
            listOf(cite(SRC_FCM, "Boxed Warning; 5.1")),
        ),
        dose = Facts(
            listOf(
                "≥50 kg: 750 mg IV × 2 doses ≥7 days apart (1,500 mg per course); adults may instead receive one dose of 15 mg/kg, max 1,000 mg per course.",
                "<50 kg: 15 mg/kg IV × 2 doses ≥7 days apart per course. Courses may be repeated if iron deficiency recurs.",
                "Heart failure: 500–1,000 mg on day 1 ± week-6 dose by weight and Hb; then 500 mg at weeks 12, 24, 36 if ferritin <100 ng/mL or 100–300 ng/mL with TSAT <20%.",
            ),
            listOf(cite(SRC_FCM, "2.2–2.3 Dosage and Administration — Table 1")),
        ),
        beforeStarting = Facts(
            listOf(
                "Correct pre-existing hypophosphataemia before giving.",
                "Check serum phosphate before a repeat course in patients at risk of low phosphate or if repeating within 3 months.",
                "Give only where staff and treatment for serious hypersensitivity reactions are immediately available.",
            ),
            listOf(cite(SRC_FCM, "2.1 Laboratory Testing; 5.1; 5.2")),
        ),
        monitoring = Facts(
            listOf(
                "Observe for hypersensitivity during and for at least 30 min after each dose, until clinically stable.",
                "Watch for hypertension after each dose (transient systolic rises usually resolve within 30 min).",
                "Serum phosphate in patients at risk of chronic low phosphate; consider permanent discontinuation for severe or persistent hypophosphataemia.",
                "Serum iron and transferrin-bound iron may be overestimated for 24 h after a dose. Watch for extravasation (long-lasting brown staining).",
            ),
            listOf(cite(SRC_FCM, "2.4; 5.1–5.4")),
        ),
        commonEffects = Facts(
            listOf(
                "Nausea (7.2%)",
                "Hypertension (4%)",
                "Flushing (4%)",
                "Injection-site reactions (3%)",
                "Erythema (3%)",
                "Hypophosphataemia (2.1%)",
                "Dizziness (2.1%)",
                "Vomiting (2%)",
            ),
            listOf(cite(SRC_FCM, "6.1 Clinical Trials Experience — Table 2")),
        ),
        seriousEffects = Facts(
            listOf(
                "Symptomatic hypophosphataemia with osteomalacia and fractures (boxed); risk factors include malabsorption, IBD, hyperparathyroidism, vitamin D deficiency, malnutrition, HHT, drugs affecting renal tubules.",
                "Serious hypersensitivity incl. life-threatening or fatal anaphylactic-type reactions (serious anaphylaxis 0.1% in trials).",
                "Hypertension (4%); transient systolic rises with flushing, dizziness or nausea (6%).",
                "Laboratory interference: serum iron overestimated for 24 h after dosing.",
            ),
            listOf(cite(SRC_FCM, "Boxed Warning; 5.1–5.4 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "IV; peak iron levels 15 min–1.21 h after 100–1,000 mg.",
            distribution = "Vd ≈3 L.",
            elimination = "Rapidly cleared from plasma; renal elimination of iron negligible.",
            halfLife = "7–12 h (adults); ≈9.7 h in children.",
            citations = listOf(cite(SRC_FCM, "12.3 Pharmacokinetics")),
        ),
        pregnancy = Facts(
            listOf("Hypersensitivity reactions may have serious fetal consequences such as bradycardia; data are insufficient to assess birth-defect or miscarriage risk."),
            listOf(cite(SRC_FCM, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Iron is present in breast milk; infant adverse reactions reported (constipation, diarrhoea) were not considered related to exposure."),
            listOf(cite(SRC_FCM, "8.2 Lactation")),
        ),
    ),
)
