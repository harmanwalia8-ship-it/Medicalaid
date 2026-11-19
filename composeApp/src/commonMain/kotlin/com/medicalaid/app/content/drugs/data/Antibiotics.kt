package com.medicalaid.app.content.drugs.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.drugs.Clearance
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.Facts
import com.medicalaid.app.content.drugs.Pharmacokinetics

/*
 * Antibiotics. Each monograph paraphrases one US FDA label (DailyMed); see Metabolic.kt for the rules.
 * Older (non-PLR) labels have no numbered sections, so citations name the section heading instead.
 */

internal val antibioticSources = listOf(
    Source(
        "fda-label-nitrofurantoin", Authority.FDA, "Drug label (DailyMed)",
        "Nitrofurantoin (monohydrate/macrocrystals) capsules — Lifestar Pharma", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=35c0a6d8-ff85-4d9b-9ad3-ae20808119dd",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-amoxicillin", Authority.FDA, "Drug label (DailyMed)",
        "Amoxicillin capsules — Hikma Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=4fb7837e-964c-4d38-a3a5-b384b1bd6e08",
        status = "Revised Mar 2026",
    ),
    Source(
        "fda-label-amoxicillin-clavulanate", Authority.FDA, "Drug label (DailyMed)",
        "Amoxicillin and clavulanate potassium tablets — USAntibiotics", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=c4a6deb5-0bcd-4fa3-b19b-a82fa1dd14f3",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-cefalexin", Authority.FDA, "Drug label (DailyMed)",
        "Cephalexin capsules — Aurobindo Pharma", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8e28f049-6110-4473-830f-c494191a7197",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-cefuroxime", Authority.FDA, "Drug label (DailyMed)",
        "Cefuroxime axetil tablets — Ascend Laboratories", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=dd11e4b9-7730-4d3a-81ca-16f15de09759",
        status = "Revised Oct 2025",
    ),
    Source(
        "fda-label-ceftriaxone", Authority.FDA, "Drug label (DailyMed)",
        "Ceftriaxone for injection (IM/IV) — Hikma Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8351aa37-552d-471d-b293-c564dcb6ec29",
        status = "Revised Sep 2026",
    ),
    Source(
        "fda-label-azithromycin", Authority.FDA, "Drug label (DailyMed)",
        "Azithromycin tablets — Teva Pharmaceuticals USA", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=f6a52c0b-45db-422e-8c22-e867a11b4439",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-doxycycline", Authority.FDA, "Drug label (DailyMed)",
        "Doxycycline hyclate tablets — Ajanta Pharma USA", "2025",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=9f00b2c9-e628-4265-8530-3258dd3cdcd5",
        status = "Revised Nov 2025",
    ),
    Source(
        "fda-label-metronidazole", Authority.FDA, "Drug label (DailyMed)",
        "Metronidazole tablets — Kanchan Healthcare", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=8836334e-6db4-44be-bc9e-099b26322cf4",
        status = "Revised Jul 2026",
    ),
    Source(
        "fda-label-clindamycin", Authority.FDA, "Drug label (DailyMed)",
        "Clindamycin hydrochloride capsules — Micro Labs", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=9b9f898f-6a91-44f8-9508-f9ddfaad27bc",
        status = "Revised Nov 2026",
    ),
)

private const val ABX_NITRO = "fda-label-nitrofurantoin"
private const val ABX_AMOX = "fda-label-amoxicillin"
private const val ABX_AMOCLAV = "fda-label-amoxicillin-clavulanate"
private const val ABX_CEFALEX = "fda-label-cefalexin"
private const val ABX_CEFUROX = "fda-label-cefuroxime"
private const val ABX_CEFTRI = "fda-label-ceftriaxone"
private const val ABX_AZITH = "fda-label-azithromycin"
private const val ABX_DOXY = "fda-label-doxycycline"
private const val ABX_METRO = "fda-label-metronidazole"
private const val ABX_CLINDA = "fda-label-clindamycin"

internal val antibioticDrugs = listOf(
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "nitrofurantoin",
        name = "Nitrofurantoin",
        aliases = listOf("nitrofurantoin monohydrate/macrocrystals"),
        drugClass = "Nitrofuran urinary antibacterial",
        uses = "Acute uncomplicated UTI (acute cystitis) due to susceptible E. coli or S. saprophyticus; not for pyelonephritis or perinephric abscess.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf("Adults and children >12 years: 100 mg every 12 hours for 7 days, taken with food."),
            listOf(cite(ABX_NITRO, "Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf("Obtain urine for culture and susceptibility before treatment."),
            listOf(cite(ABX_NITRO, "Indications and Usage")),
        ),
        monitoring = Facts(
            listOf(
                "Repeat urine culture after completing therapy; if bacteriuria persists or recurs, use an agent with broader tissue distribution.",
                "Long-term therapy: close monitoring of pulmonary condition, periodic liver biochemistry and periodic renal function.",
                "Elderly: monitoring renal function may be useful.",
            ),
            listOf(cite(ABX_NITRO, "Indications and Usage; Warnings; Precautions — Geriatric Use")),
        ),
        renal = Facts(
            listOf(
                "Contraindicated in anuria, oliguria or significant renal impairment (CrCl <60 mL/min or clinically significant raised serum creatinine) — increased toxicity.",
                "Renal impairment (CrCl <60 mL/min) increases the risk of peripheral neuropathy.",
            ),
            listOf(cite(ABX_NITRO, "Contraindications; Warnings — Neuropathy")),
        ),
        hepatic = Facts(
            listOf("Contraindicated with a history of cholestatic jaundice/hepatic dysfunction associated with nitrofurantoin."),
            listOf(cite(ABX_NITRO, "Contraindications")),
        ),
        commonEffects = Facts(
            listOf(
                "Nausea (8%)",
                "Headache (6%)",
                "Flatulence (1.5%)",
                "Laboratory changes (1–5%): eosinophilia, raised AST/ALT, decreased haemoglobin, raised serum phosphorus",
            ),
            listOf(cite(ABX_NITRO, "Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "Pulmonary reactions — acute, subacute or chronic (interstitial pneumonitis, fibrosis), mostly with ≥6 months' therapy; deaths reported. Stop if they occur.",
                "Hepatotoxicity — hepatitis, cholestatic jaundice, chronic active hepatitis, hepatic necrosis; fatalities reported. Withdraw immediately if hepatitis occurs.",
                "Peripheral neuropathy, may be severe or irreversible; risk raised by renal impairment, anaemia, diabetes, electrolyte imbalance, vitamin B deficiency.",
                "Optic neuritis (rare, postmarketing).",
                "Haemolytic anaemia in G6PD deficiency — stop the drug.",
                "C. difficile-associated diarrhoea, up to 2 months after use.",
            ),
            listOf(cite(ABX_NITRO, "Warnings")),
        ),
        pk = Pharmacokinetics(
            absorption = "25% macrocrystals (slow dissolution) + 75% monohydrate (gel matrix release); food increases bioavailability by ~40%.",
            distribution = "Plasma levels low (peak usually <1 mcg/mL); highly soluble in urine.",
            elimination = "20–25% of a dose recovered unchanged in urine over 24 h; may colour urine brown.",
            citations = listOf(cite(ABX_NITRO, "Clinical Pharmacology")),
        ),
        interactions = Facts(
            listOf(
                "Magnesium trisilicate antacids: reduce rate and extent of absorption — avoid together.",
                "Probenecid, sulfinpyrazone: inhibit tubular secretion — higher serum levels (toxicity) and lower urinary efficacy.",
                "False-positive urine glucose with Benedict's or Fehling's solution (not enzymatic tests).",
            ),
            listOf(cite(ABX_NITRO, "Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Use only if clearly needed; contraindicated at term (38–42 weeks), in labour and delivery, or when labour is imminent (risk of neonatal haemolytic anaemia)."),
            listOf(cite(ABX_NITRO, "Contraindications; Precautions — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Trace amounts in breast milk; because of serious risk to infants under 1 month, decide whether to stop nursing or the drug."),
            listOf(cite(ABX_NITRO, "Precautions — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "amoxicillin",
        name = "Amoxicillin",
        drugClass = "Penicillin-class antibacterial",
        uses = "Ear/nose/throat, genitourinary, skin and lower respiratory infections (β-lactamase–negative isolates); H. pylori eradication with clarithromycin/lansoprazole in adults.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "ENT, skin, genitourinary: 500 mg every 12 h or 250 mg every 8 h; severe infection 875 mg every 12 h or 500 mg every 8 h.",
                "Lower respiratory tract: 875 mg every 12 h or 500 mg every 8 h. Take at the start of a meal.",
                "H. pylori: 1 g + clarithromycin 500 mg + lansoprazole 30 mg, all twice daily for 14 days (dual: 1 g + lansoprazole 30 mg three times daily).",
            ),
            listOf(cite(ABX_AMOX, "2.1–2.4 Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf("Ask about previous hypersensitivity reactions to penicillins, cephalosporins or other allergens."),
            listOf(cite(ABX_AMOX, "5.1 Anaphylactic Reactions")),
        ),
        monitoring = Facts(
            listOf(
                "Prothrombin time/INR with oral anticoagulants; anticoagulant dose may need adjusting.",
                "Skin rash: monitor closely and stop if lesions progress.",
                "Elderly: monitoring renal function may be useful.",
            ),
            listOf(cite(ABX_AMOX, "5.2; 7.2; 8.5")),
        ),
        renal = Facts(
            listOf(
                "No reduction generally needed unless impairment is severe; GFR <30 mL/min: do not use the 875 mg dose.",
                "GFR 10–30 mL/min: 500 mg or 250 mg every 12 h.",
                "GFR <10 mL/min: 500 mg or 250 mg every 24 h.",
                "Haemodialysis: 500 mg or 250 mg every 24 h, plus an extra dose during and at the end of dialysis.",
            ),
            listOf(cite(ABX_AMOX, "2.5 Renal Impairment — Table 2; 8.6")),
        ),
        commonEffects = Facts(
            listOf(
                "Diarrhoea (>1%)",
                "Rash (>1%)",
                "Vomiting (>1%)",
                "Nausea (>1%)",
                "H. pylori triple therapy: diarrhoea (7%), headache (6%), taste perversion (5%)",
            ),
            listOf(cite(ABX_AMOX, "6.1 Clinical Trials Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Anaphylaxis — serious, occasionally fatal; more likely with penicillin allergy or multiple allergies. Stop if it occurs.",
                "Severe cutaneous reactions (SJS, TEN, DRESS, AGEP).",
                "Drug-induced enterocolitis syndrome — protracted vomiting 1–4 h after dose, mostly in patients ≤18 years.",
                "C. difficile-associated diarrhoea, up to 2 months after use.",
                "Erythematous rash in most patients with mononucleosis — do not use.",
            ),
            listOf(cite(ABX_AMOX, "5.1–5.6 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Acid-stable, rapidly absorbed; peak 1–2 h (3.5–5.0 mcg/mL after 250 mg, 5.5–7.5 mcg/mL after 500 mg capsules).",
            distribution = "~20% protein bound; reaches most tissues except brain and CSF unless meninges are inflamed.",
            elimination = "~60% of an oral dose excreted unchanged in urine within 6–8 h; probenecid delays excretion.",
            halfLife = "61.3 minutes.",
            citations = listOf(cite(ABX_AMOX, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Probenecid: raises and prolongs amoxicillin levels — co-administration not recommended.",
                "Oral anticoagulants: prolonged prothrombin time (raised INR) — monitor, adjust dose.",
                "Allopurinol: increased incidence of rash.",
                "Combined oral contraceptives: may reduce efficacy.",
                "Urine glucose: false positives with copper-reduction tests — use glucose oxidase tests.",
            ),
            listOf(cite(ABX_AMOX, "7.1–7.6 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No fetal harm in animal studies but no adequate human studies; use only if clearly needed."),
            listOf(cite(ABX_AMOX, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Penicillins are excreted in milk and may sensitise the infant; use with caution."),
            listOf(cite(ABX_AMOX, "8.3 Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "amoxicillin-clavulanate",
        name = "Amoxicillin–clavulanate",
        aliases = listOf("co-amoxiclav", "amoxicillin clavulanic acid"),
        drugClass = "Penicillin-class antibacterial + β-lactamase inhibitor",
        uses = "Lower respiratory, otitis media, sinusitis, skin and urinary infections caused by β-lactamase–producing isolates; not when amoxicillin alone suffices.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Severe and respiratory infections: 875 mg every 12 h or 500 mg every 8 h (doses as amoxicillin).",
                "Less severe infections: 500 mg every 12 h or 250 mg every 8 h. Take at the start of a meal.",
                "Two 250 mg/125 mg tablets do not equal one 500 mg/125 mg tablet (same clavulanate content); not interchangeable with chewables.",
            ),
            listOf(cite(ABX_AMOCLAV, "2.1; 2.2 Table 1; 2.6")),
        ),
        beforeStarting = Facts(
            listOf("Ask about previous hypersensitivity reactions to penicillins, cephalosporins or other allergens."),
            listOf(cite(ABX_AMOCLAV, "5.1 Hypersensitivity Reactions")),
        ),
        monitoring = Facts(
            listOf(
                "Hepatic function at regular intervals in patients with hepatic impairment.",
                "Prothrombin time/INR with oral anticoagulants; anticoagulant dose may need adjusting.",
                "Elderly: monitoring renal function may be useful.",
            ),
            listOf(cite(ABX_AMOCLAV, "5.4; 7.2; 8.5")),
        ),
        renal = Facts(
            listOf(
                "No reduction generally needed unless impairment is severe; GFR <30 mL/min: do not use the 875 mg dose.",
                "GFR 10–30 mL/min: 500 mg or 250 mg every 12 h.",
                "GFR <10 mL/min: 500 mg or 250 mg every 24 h.",
                "Haemodialysis: 500 mg or 250 mg every 24 h, plus an extra dose during and at the end of dialysis.",
            ),
            listOf(cite(ABX_AMOCLAV, "2.4 Renal Impairment — Table 4; 8.6")),
        ),
        hepatic = Facts(
            listOf(
                "Contraindicated with a history of cholestatic jaundice/hepatic dysfunction associated with this drug.",
                "Hepatic impairment: monitor hepatic function at regular intervals.",
            ),
            listOf(cite(ABX_AMOCLAV, "4.2; 5.4 Hepatic Dysfunction")),
        ),
        commonEffects = Facts(
            listOf(
                "Diarrhoea/loose stools (9%)",
                "Nausea (3%)",
                "Skin rashes and urticaria (3%)",
                "Vomiting (1%)",
                "Vaginitis (1%)",
            ),
            listOf(cite(ABX_AMOCLAV, "6.1 Clinical Trial Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Serious, occasionally fatal hypersensitivity (anaphylaxis).",
                "Severe cutaneous reactions (SJS, TEN, DRESS, AGEP).",
                "Drug-induced enterocolitis syndrome — protracted vomiting 1–4 h after dose, mostly in patients ≤18 years.",
                "Hepatic dysfunction incl. hepatitis and cholestatic jaundice — usually reversible, deaths reported; stop if hepatitis signs occur.",
                "C. difficile-associated diarrhoea, up to 2 months after use.",
                "Rash in mononucleosis — avoid use.",
                "Fungal or bacterial superinfection.",
            ),
            listOf(cite(ABX_AMOCLAV, "5.1–5.7 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Peak ~1.5 h (tablets); food has minimal effect on amoxicillin but enhances clavulanate absorption.",
            distribution = "Low protein binding: amoxicillin ~18%, clavulanic acid ~25%.",
            elimination = "Unchanged in urine in first 6 h: amoxicillin 50–70%, clavulanic acid 25–40%.",
            halfLife = "Amoxicillin 1.3 h; clavulanic acid 1 h.",
            citations = listOf(cite(ABX_AMOCLAV, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Probenecid: raises and prolongs amoxicillin levels — co-administration not recommended.",
                "Oral anticoagulants: prolonged prothrombin time (raised INR) — monitor, adjust dose.",
                "Allopurinol: increased incidence of rash.",
                "Combined oral contraceptives: may reduce efficacy.",
                "Urine glucose: false positives with copper-reduction tests — use glucose oxidase tests.",
            ),
            listOf(cite(ABX_AMOCLAV, "7.1–7.5 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Category B: no fetal harm in rat and mouse studies but no adequate human studies; use only if clearly needed."),
            listOf(cite(ABX_AMOCLAV, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Amoxicillin is excreted in milk and may sensitise the infant; use with caution."),
            listOf(cite(ABX_AMOCLAV, "8.3 Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "cefalexin",
        name = "Cefalexin",
        aliases = listOf("cephalexin"),
        drugClass = "Cephalosporin antibacterial",
        uses = "Respiratory tract, otitis media, skin, bone and genitourinary (incl. acute prostatitis) infections due to susceptible bacteria.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Adults and patients ≥15 years: 250 mg every 6 h, or 500 mg every 12 h, for 7–14 days.",
                "More severe infections: up to 4 g daily in 2–4 equally divided doses.",
            ),
            listOf(cite(ABX_CEFALEX, "2.1 Adult Dosage")),
        ),
        beforeStarting = Facts(
            listOf("Ask about previous hypersensitivity to cefalexin, cephalosporins, penicillins or other drugs."),
            listOf(cite(ABX_CEFALEX, "5.1 Hypersensitivity Reactions")),
        ),
        monitoring = Facts(
            listOf(
                "Prothrombin time in at-risk patients (renal/hepatic impairment, poor nutrition, protracted therapy, anticoagulants).",
                "CrCl <30 mL/min: careful clinical observation and laboratory monitoring incl. renal function; monitor longer for toxicity and interactions.",
                "If anaemia develops, evaluate for drug-induced haemolytic anaemia.",
            ),
            listOf(cite(ABX_CEFALEX, "5.3; 5.5; 8.6")),
        ),
        renal = Facts(
            listOf(
                "CrCl ≥60 mL/min: no adjustment.",
                "CrCl 30–59: no adjustment; maximum 1 g/day.",
                "CrCl 15–29: 250 mg every 8 or 12 h.",
                "CrCl 5–14 (not on dialysis): 250 mg every 24 h.",
                "CrCl 1–4 (not on dialysis): 250 mg every 48 or 60 h. Haemodialysis: insufficient information.",
            ),
            listOf(cite(ABX_CEFALEX, "2.3 Renal Impairment — Table 1; 8.6")),
        ),
        commonEffects = Facts(
            listOf(
                "Diarrhoea (most frequent)",
                "Nausea",
                "Vomiting",
                "Dyspepsia",
                "Abdominal pain",
            ),
            listOf(cite(ABX_CEFALEX, "6 Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "Hypersensitivity incl. anaphylaxis, SJS, TEN; cross-reactivity in up to 10% of penicillin-allergic patients.",
                "C. difficile-associated diarrhoea, up to 2 months after use.",
                "Positive direct Coombs' test; acute intravascular haemolysis reported.",
                "Seizures, particularly in renal impairment without dose reduction.",
                "Prolonged prothrombin time.",
                "Overgrowth of non-susceptible organisms with prolonged use.",
            ),
            listOf(cite(ABX_CEFALEX, "5.1–5.6 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Acid-stable; may be given without regard to meals; peak ~1 h (~18 mcg/mL after 500 mg).",
            distribution = "10–15% bound to plasma proteins.",
            elimination = ">90% excreted unchanged in urine within 8 h (glomerular filtration and tubular secretion).",
            citations = listOf(cite(ABX_CEFALEX, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Metformin: higher metformin levels and lower renal clearance — monitor and adjust metformin dose.",
                "Probenecid: inhibits renal excretion of cefalexin — co-administration not recommended.",
                "False-positive urine glucose with Benedict's or Fehling's solution.",
            ),
            listOf(cite(ABX_CEFALEX, "7.1–7.3 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Decades of published data have not established a drug-associated risk of major birth defects, miscarriage or adverse maternal/fetal outcomes."),
            listOf(cite(ABX_CEFALEX, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk (relative infant dose <1%); weigh breastfeeding benefits against the mother's need and possible infant effects."),
            listOf(cite(ABX_CEFALEX, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "cefuroxime",
        name = "Cefuroxime",
        aliases = listOf("cefuroxime axetil"),
        drugClass = "Cephalosporin antibacterial",
        uses = "Pharyngitis/tonsillitis, otitis media, sinusitis, bronchitis exacerbations, uncomplicated skin infections and UTI, uncomplicated gonorrhoea, early Lyme disease.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Adults/adolescents ≥13 y: 250 mg every 12 h (pharyngitis, sinusitis, UTI) or 250–500 mg every 12 h (bronchitis exacerbation, skin) for 7–10 days.",
                "Uncomplicated gonorrhoea: 1,000 mg single dose. Early Lyme disease: 500 mg every 12 h for 20 days.",
                "Tablets and oral suspension are not bioequivalent — not substitutable mg-for-mg. Tablets with or without food.",
            ),
            listOf(cite(ABX_CEFUROX, "2.1–2.2 Dosage — Table 1")),
        ),
        beforeStarting = Facts(
            listOf("Ask about previous hypersensitivity reactions to penicillins, cephalosporins or other allergens."),
            listOf(cite(ABX_CEFUROX, "5.1 Anaphylactic Reactions")),
        ),
        monitoring = Facts(
            listOf(
                "Elderly: monitoring renal function may be useful.",
                "Blood glucose: use glucose oxidase or hexokinase methods (ferricyanide test may be falsely negative).",
            ),
            listOf(cite(ABX_CEFUROX, "7.3; 8.5")),
        ),
        renal = Facts(
            listOf(
                "CrCl ≥30 mL/min: no adjustment.",
                "CrCl 10 to <30: standard dose every 24 h.",
                "CrCl <10 (no haemodialysis): standard dose every 48 h.",
                "Haemodialysis: one additional standard dose at the end of each dialysis.",
            ),
            listOf(cite(ABX_CEFUROX, "2.5 Renal Impairment — Table 4; 8.6")),
        ),
        commonEffects = Facts(
            listOf(
                "Diarrhoea (4%)",
                "Nausea/vomiting (3%)",
                "Transient AST or ALT rise (2% each)",
                "Eosinophilia (1%)",
                "Early Lyme disease (20 days): diarrhoea (10.6%), Jarisch–Herxheimer reaction (5.6%), vaginitis (5.4%)",
                "Single 1 g dose: nausea/vomiting (7%), diarrhoea (4%)",
            ),
            listOf(cite(ABX_CEFUROX, "6.1 Clinical Trials Experience — Tables 5–6")),
        ),
        seriousEffects = Facts(
            listOf(
                "Anaphylaxis — serious, occasionally fatal; contraindicated with known β-lactam hypersensitivity.",
                "C. difficile-associated diarrhoea, up to 2 months after use.",
                "Fungal or bacterial superinfection.",
            ),
            listOf(cite(ABX_CEFUROX, "4; 5.1–5.3 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Prodrug hydrolysed to cefuroxime; bioavailability 37% fasting, 52% after food; peak 2.2–3 h.",
            distribution = "~50% protein bound; distributed throughout extracellular fluids.",
            metabolism = "Axetil moiety metabolised to acetaldehyde and acetic acid.",
            elimination = "Excreted unchanged in urine (~50% of dose within 12 h).",
            halfLife = "1.2–1.3 h; ~3.5 h in elderly (mean CrCl 34.9 mL/min).",
            citations = listOf(cite(ABX_CEFUROX, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Antacids: give cefuroxime ≥1 h before or 2 h after; avoid H2 antagonists and proton pump inhibitors (lower bioavailability).",
                "Probenecid: increases cefuroxime exposure — co-administration not recommended.",
                "False-positive urine glucose with copper-reduction tests; false-negative ferricyanide blood glucose test.",
            ),
            listOf(cite(ABX_CEFUROX, "7.1–7.3 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Decades of cephalosporin data have not established a drug-associated risk of major birth defects, miscarriage or adverse outcomes."),
            listOf(cite(ABX_CEFUROX, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk (estimated infant dose <1% of adult dose); weigh breastfeeding benefits against the mother's need."),
            listOf(cite(ABX_CEFUROX, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "ceftriaxone",
        name = "Ceftriaxone",
        aliases = listOf("ceftriaxone sodium"),
        drugClass = "Cephalosporin antibacterial (parenteral)",
        uses = "Lower respiratory, otitis media, skin, urinary, bone/joint and intra-abdominal infections, gonorrhoea, PID, septicaemia, meningitis; surgical prophylaxis.",
        clearance = setOf(Clearance.Renal, Clearance.Biliary),
        dose = Facts(
            listOf(
                "Adults: 1–2 g once daily (or in two divided doses); total daily dose must not exceed 4 g. Usually 4–14 days.",
                "Uncomplicated gonorrhoea: single 250 mg IM dose. Surgical prophylaxis: single 1 g IV, ½–2 h before surgery.",
                "IV infusion over 30 minutes. Never give simultaneously with calcium-containing IV solutions, even via separate lines at Y-site.",
            ),
            listOf(cite(ABX_CEFTRI, "Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf(
                "Ask about previous hypersensitivity to cephalosporins, penicillins, other β-lactams or other drugs.",
                "Obtain specimens for culture and susceptibility; treatment may start before results.",
            ),
            listOf(cite(ABX_CEFTRI, "Indications and Usage; Warnings — Hypersensitivity")),
        ),
        monitoring = Facts(
            listOf(
                "Prothrombin time if vitamin K synthesis impaired or stores low (e.g. chronic liver disease, malnutrition); vitamin K 10 mg weekly may be needed.",
                "With vitamin K antagonists: monitor coagulation frequently during and after treatment.",
                "Ensure adequate hydration; stop if signs of gallbladder disease, urolithiasis, oliguria or renal failure.",
            ),
            listOf(cite(ABX_CEFTRI, "Precautions")),
        ),
        renal = Facts(
            listOf(
                "Usual doses need no adjustment in renal failure; not removed by haemo- or peritoneal dialysis — no supplementary dose.",
                "Combined hepatic dysfunction and significant renal disease: do not exceed 2 g daily; monitor closely.",
                "Encephalopathy and seizures reported, some in severe renal impairment without dose adjustment; label advises appropriate adjustment in severe impairment.",
            ),
            listOf(cite(ABX_CEFTRI, "Dosage and Administration; Warnings — Neurological; Precautions")),
        ),
        hepatic = Facts(
            listOf("No adjustment for hepatic dysfunction alone; with significant renal disease as well, do not exceed 2 g daily."),
            listOf(cite(ABX_CEFTRI, "Precautions — Renal or Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Eosinophilia (6%)",
                "Thrombocytosis (5.1%)",
                "Raised ALT (3.3%) / AST (3.1%)",
                "Diarrhoea/loose stools (2.7%)",
                "Leukopenia (2.1%)",
                "Rash (1.7%)",
                "Raised BUN (1.2%)",
                "Local pain, induration, tenderness (1%)",
            ),
            listOf(cite(ABX_CEFTRI, "Adverse Reactions")),
        ),
        seriousEffects = Facts(
            listOf(
                "Anaphylaxis — serious, occasionally fatal; caution in penicillin/β-lactam allergy.",
                "Ceftriaxone–calcium precipitation; fatal lung/kidney deposits in neonates. Contraindicated in premature and hyperbilirubinaemic neonates.",
                "Encephalopathy, seizures, myoclonus, non-convulsive status epilepticus — reversible on stopping.",
                "C. difficile-associated diarrhoea, up to 2 months after use.",
                "Immune haemolytic anaemia, including fatal cases.",
                "Prolonged prothrombin time.",
                "Gallbladder pseudolithiasis and urolithiasis/post-renal acute renal failure (mostly children).",
                "Pancreatitis, possibly from biliary obstruction.",
            ),
            listOf(cite(ABX_CEFTRI, "Contraindications; Warnings; Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Completely absorbed after IM injection; peak 2–3 h.",
            distribution = "Protein binding 95% (<25 mcg/mL) to 85% (300 mcg/mL); Vd 5.8–13.5 L; crosses placenta, enters inflamed meninges.",
            elimination = "33–67% unchanged in urine; remainder secreted in bile, appearing in faeces as inactive compounds.",
            halfLife = "5.8–8.7 h in healthy adults; 11.4–15.7 h in renal impairment.",
            citations = listOf(cite(ABX_CEFTRI, "Clinical Pharmacology")),
        ),
        interactions = Facts(
            listOf(
                "Calcium-containing IV solutions: precipitation — never simultaneously; contraindicated in neonates ≤28 days needing them.",
                "Vitamin K antagonists: increased bleeding risk — monitor coagulation, adjust anticoagulant.",
                "Lidocaine: IV use of ceftriaxone solutions containing lidocaine is contraindicated.",
                "Chloramphenicol: antagonism observed in vitro.",
                "Some blood glucose monitors may read falsely low; test urine glucose enzymatically.",
            ),
            listOf(cite(ABX_CEFTRI, "Contraindications; Warnings; Precautions; Clinical Pharmacology — Microbiology")),
        ),
        pregnancy = Facts(
            listOf("No embryotoxicity or teratogenicity in animal studies, but no adequate human studies; use only if clearly needed."),
            listOf(cite(ABX_CEFTRI, "Precautions — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Low concentrations are excreted in human milk; use with caution."),
            listOf(cite(ABX_CEFTRI, "Precautions — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "azithromycin",
        name = "Azithromycin",
        drugClass = "Macrolide antibacterial",
        uses = "Mild–moderate CAP, pharyngitis, sinusitis, bronchitis exacerbations, skin infections, urethritis/cervicitis, chancroid; otitis media and CAP in children.",
        clearance = setOf(Clearance.Biliary),
        dose = Facts(
            listOf(
                "CAP, pharyngitis (second-line), uncomplicated skin: 500 mg on day 1, then 250 mg once daily on days 2–5.",
                "Acute bacterial sinusitis: 500 mg once daily for 3 days (bronchitis exacerbation: either regimen).",
                "Chancroid, non-gonococcal urethritis/cervicitis: single 1 g; gonococcal urethritis/cervicitis: single 2 g. With or without food.",
            ),
            listOf(cite(ABX_AZITH, "2.1 Adult Dosage — Table 1")),
        ),
        beforeStarting = Facts(
            listOf("Sexually transmitted urethritis/cervicitis: test for syphilis (serology) and gonorrhoea at diagnosis — azithromycin does not reliably treat syphilis."),
            listOf(cite(ABX_AZITH, "5.8")),
        ),
        monitoring = Facts(
            listOf(
                "Prothrombin time with warfarin or other oral anticoagulants.",
                "With nelfinavir: monitor for liver enzyme abnormalities and hearing impairment.",
                "With digoxin, colchicine or phenytoin: careful monitoring.",
            ),
            listOf(cite(ABX_AZITH, "7.1–7.3 Drug Interactions")),
        ),
        hepatic = Facts(
            listOf(
                "Contraindicated with a history of cholestatic jaundice/hepatic dysfunction from prior azithromycin.",
                "Pharmacokinetics in hepatic impairment have not been established.",
            ),
            listOf(cite(ABX_AZITH, "4.2; 12.3")),
        ),
        commonEffects = Facts(
            listOf(
                "Diarrhoea (5–14%)",
                "Nausea (3–18%)",
                "Abdominal pain (3–7%)",
                "Vomiting (2–7%)",
                "Single 2 g dose: nausea (18%), diarrhoea (14%)",
            ),
            listOf(cite(ABX_AZITH, "6.1 Clinical Trials Experience")),
        ),
        seriousEffects = Facts(
            listOf(
                "Serious allergic and skin reactions (anaphylaxis, AGEP, SJS, TEN, DRESS); may recur after symptomatic treatment stops.",
                "Hepatotoxicity, incl. hepatic failure, sometimes fatal — stop if hepatitis signs occur.",
                "Infantile hypertrophic pyloric stenosis after use in neonates (≤42 days).",
                "QT prolongation and torsades de pointes; elderly more susceptible.",
                "~Two-fold increased short-term cardiovascular death risk vs other antibacterials in observational studies.",
                "C. difficile-associated diarrhoea, up to 2 months after use.",
                "Exacerbation of myasthenia gravis.",
            ),
            listOf(cite(ABX_AZITH, "5.1–5.7 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability 38% (capsules); Tmax ~2.2 h; food raises Cmax 23% with no change in AUC (tablets).",
            distribution = "Protein binding 51% to 7% (concentration-dependent); extensive tissue uptake; very low CSF levels.",
            metabolism = "Not studied.",
            elimination = "Mainly biliary, as unchanged drug; ~6% of dose unchanged in urine over a week.",
            halfLife = "Terminal ~68 h.",
            citations = listOf(cite(ABX_AZITH, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Nelfinavir: raises azithromycin levels — no dose change, but monitor for liver enzyme abnormalities and hearing impairment.",
                "Warfarin: may potentiate anticoagulation — monitor prothrombin time.",
                "Digoxin, colchicine, phenytoin: macrolide interactions seen with other agents — monitor carefully.",
                "Class IA (quinidine, procainamide) or III (dofetilide, amiodarone, sotalol) antiarrhythmics and other QT-prolonging drugs: torsades risk.",
            ),
            listOf(cite(ABX_AZITH, "5.4; 7.1–7.3")),
        ),
        pregnancy = Facts(
            listOf("Decades of published data have not identified a drug-associated risk of major birth defects, miscarriage or adverse maternal/fetal outcomes."),
            listOf(cite(ABX_AZITH, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk with non-serious infant reactions reported; monitor the breastfed infant for diarrhoea, vomiting or rash."),
            listOf(cite(ABX_AZITH, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "doxycycline",
        name = "Doxycycline",
        aliases = listOf("doxycycline hyclate"),
        drugClass = "Tetracycline-class antibacterial",
        uses = "Rickettsial, sexually transmitted, respiratory and other specified bacterial infections; post-exposure anthrax; malaria prophylaxis; adjunct for amoebiasis and severe acne.",
        clearance = setOf(Clearance.Renal, Clearance.Biliary),
        dose = Facts(
            listOf(
                "200 mg on day 1 (100 mg every 12 h), then 100 mg daily (once, or 50 mg every 12 h); severe infections 100 mg every 12 h.",
                "Malaria prophylaxis: 100 mg daily, from 1–2 days before travel until 4 weeks after leaving the area.",
                "Take with plenty of fluid; may take with food or milk if gastric irritation occurs.",
            ),
            listOf(cite(ABX_DOXY, "2.1; 2.2; 2.4 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "Long-term therapy: periodic haematological, renal and hepatic laboratory tests.",
                "Visual disturbance (intracranial hypertension): prompt ophthalmological evaluation; monitor until stable.",
            ),
            listOf(cite(ABX_DOXY, "5.6; 5.11")),
        ),
        commonEffects = Facts(
            listOf(
                "Anorexia",
                "Nausea",
                "Vomiting",
                "Diarrhoea",
                "Rash",
                "Photosensitivity",
                "Urticaria",
                "Oesophagitis/oesophageal ulcer, mostly when taken just before bed",
            ),
            listOf(cite(ABX_DOXY, "6 Adverse Reactions (no frequencies given)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Permanent tooth discoloration and enamel hypoplasia if used during tooth development (second half of pregnancy, children ≤8 years).",
                "Reversible inhibition of bone growth (2nd/3rd trimester, infancy, childhood ≤8 years).",
                "C. difficile-associated diarrhoea, up to 2 months after use.",
                "Photosensitivity (exaggerated sunburn) — stop at first skin erythema.",
                "Severe skin reactions (SJS, TEN, DRESS, exfoliative dermatitis, fixed drug eruption).",
                "Intracranial hypertension — risk higher in overweight women of childbearing age; avoid isotretinoin; permanent visual loss possible.",
                "Incomplete suppression of malaria; does not suppress P. falciparum gametocytes.",
            ),
            listOf(cite(ABX_DOXY, "5.1–5.8 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "300 mg dose: peak 3.0 mcg/mL at 3 h; high-fat meal lowers Cmax 24% and AUC 15%.",
            elimination = "Concentrated in bile; excreted in urine and faeces. Renal ~40% per 72 h (CrCl ~75), 1–5% if CrCl <10 mL/min.",
            halfLife = "18–22 h; not changed by severe renal impairment or haemodialysis.",
            citations = listOf(cite(ABX_DOXY, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Antacids (aluminium, calcium, magnesium), bismuth subsalicylate, iron: impair absorption.",
                "Anticoagulants: depressed prothrombin activity — anticoagulant dose may need reduction.",
                "Penicillin: avoid combining (bacteriostatic interference).",
                "Oral contraceptives: may be less effective.",
                "Barbiturates, carbamazepine, phenytoin: shorten doxycycline half-life.",
            ),
            listOf(cite(ABX_DOXY, "7.1–7.5 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Use in 2nd/3rd trimester may discolour teeth and inhibit bone growth; first-trimester data show no increased major birth defect risk."),
            listOf(cite(ABX_DOXY, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in milk; breastfeeding not recommended during treatment and for 5 days after the last dose."),
            listOf(cite(ABX_DOXY, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "metronidazole",
        name = "Metronidazole",
        drugClass = "Nitroimidazole antimicrobial",
        uses = "Trichomoniasis, amoebiasis (intestinal and liver abscess) and serious anaerobic bacterial infections.",
        clearance = setOf(Clearance.Hepatic, Clearance.Renal),
        boxedWarning = Facts(
            listOf("Carcinogenic in mice and rats. Avoid unnecessary use; reserve for the labelled indications."),
            listOf(cite(ABX_METRO, "Boxed Warning")),
        ),
        dose = Facts(
            listOf(
                "Trichomoniasis: 2 g as one dose (or 1 g twice in one day), or 250 mg three times daily for 7 days.",
                "Amoebiasis: 750 mg three times daily for 5–10 days (liver abscess 500 or 750 mg three times daily).",
                "Anaerobic infections: 7.5 mg/kg every 6 h (~500 mg for 70 kg), usually 7–10 days; maximum 4 g per 24 h.",
            ),
            listOf(cite(ABX_METRO, "Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf(
                "Total and differential leucocyte counts before (and after) therapy.",
                "Trichomoniasis: confirm the organism by wet smear and/or culture.",
            ),
            listOf(cite(ABX_METRO, "Indications and Usage; Precautions — Blood Dyscrasias")),
        ),
        monitoring = Facts(
            listOf(
                "Leucocyte counts after therapy and before/after any repeat course.",
                "Monitor for adverse events in ESRD, mild–moderate hepatic impairment and the elderly.",
                "Neurological signs: promptly reassess continuing therapy.",
            ),
            listOf(cite(ABX_METRO, "Dosage and Administration; Warnings; Precautions")),
        ),
        renal = Facts(
            listOf(
                "Reduced renal function does not alter single-dose kinetics; in ESRD metabolites accumulate — monitor for adverse events.",
                "Haemodialysis removes 40–65% of a dose: if dosing cannot be separated from dialysis, consider a supplementary dose afterwards.",
                "CAPD: no dose adjustment needed.",
            ),
            listOf(cite(ABX_METRO, "Dosage and Administration; Precautions; Clinical Pharmacology")),
        ),
        hepatic = Facts(
            listOf(
                "Severe hepatic impairment (Child-Pugh C): reduce dose by 50%.",
                "Mild–moderate (Child-Pugh A–B): no adjustment; monitor for adverse events.",
            ),
            listOf(cite(ABX_METRO, "Dosage and Administration; Precautions; Clinical Pharmacology")),
        ),
        commonEffects = Facts(
            listOf(
                "Nausea, sometimes with headache or anorexia (GI effects most common)",
                "Vomiting (occasional)",
                "Diarrhoea",
                "Epigastric distress, abdominal cramps",
                "Constipation",
                "Sharp metallic taste",
                "Furry tongue, glossitis, stomatitis (Candida overgrowth)",
            ),
            listOf(cite(ABX_METRO, "Adverse Reactions (no frequencies given)")),
        ),
        seriousEffects = Facts(
            listOf(
                "Carcinogenicity in rodents (boxed warning).",
                "Severe cutaneous reactions (TEN, SJS, DRESS, AGEP) — stop immediately.",
                "Encephalopathy with cerebellar signs, peripheral and optic neuropathy, seizures.",
                "Aseptic meningitis, possibly within hours of a dose.",
                "Severe, sometimes fatal hepatotoxicity in Cockayne syndrome — contraindicated.",
                "QT prolongation, mainly with other QT-prolonging drugs.",
                "Leucopenia — caution in blood dyscrasias.",
                "Candidiasis may worsen; treat with a candidacidal agent.",
            ),
            listOf(cite(ABX_METRO, "Boxed Warning; Contraindications; Warnings; Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Well absorbed; peak 1–2 h (~12 mcg/mL after 500 mg).",
            distribution = "<20% protein bound; CSF, saliva and breast milk levels similar to plasma.",
            metabolism = "Side-chain oxidation and glucuronidation; hydroxy metabolite is active.",
            elimination = "Urine 60–80% of dose (~20% as unchanged drug); faeces 6–15%.",
            halfLife = "~8 h.",
            citations = listOf(cite(ABX_METRO, "Clinical Pharmacology")),
        ),
        interactions = Facts(
            listOf(
                "Disulfiram: psychotic reactions — do not give if disulfiram taken within the last 2 weeks.",
                "Alcohol or propylene glycol: disulfiram-like reaction — avoid during and for ≥3 days after therapy.",
                "Warfarin and other coumarins: prolonged prothrombin time — monitor PT/INR.",
                "Lithium: raised lithium levels — check serum lithium and creatinine several days after starting.",
                "Busulfan: raised busulfan levels — avoid unless essential; then monitor busulfan levels.",
            ),
            listOf(cite(ABX_METRO, "Contraindications; Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Contraindicated in the first trimester for trichomoniasis; crosses the placenta, though data from >5,000 pregnancies mostly show no increased fetal risk."),
            listOf(cite(ABX_METRO, "Contraindications; Precautions — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Milk levels similar to maternal serum; may pump and discard during therapy and for 48 h after the last dose."),
            listOf(cite(ABX_METRO, "Precautions — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "clindamycin",
        name = "Clindamycin",
        aliases = listOf("clindamycin hydrochloride"),
        drugClass = "Lincosamide antibacterial",
        uses = "Serious anaerobic, streptococcal, pneumococcal and staphylococcal infections, reserved for penicillin-allergic patients or when a penicillin is inappropriate.",
        clearance = setOf(Clearance.Hepatic),
        boxedWarning = Facts(
            listOf(
                "C. difficile infection, from mild diarrhoea to fatal colitis, possibly >2 months after use. Reserve for serious infections where less toxic agents are inappropriate.",
                "Do not use for non-bacterial infections such as most upper respiratory infections.",
            ),
            listOf(cite(ABX_CLINDA, "Boxed Warning")),
        ),
        dose = Facts(
            listOf(
                "Adults: serious infections 150–300 mg every 6 h; more severe infections 300–450 mg every 6 h.",
                "Take with a full glass of water, ≥30 minutes before lying down. Stop if significant diarrhoea occurs.",
                "β-haemolytic streptococcal infections: treat for at least 10 days.",
            ),
            listOf(cite(ABX_CLINDA, "Dosage and Administration")),
        ),
        beforeStarting = Facts(
            listOf("Ask about previous sensitivities to drugs and other allergens."),
            listOf(cite(ABX_CLINDA, "Warnings — Hypersensitivity")),
        ),
        monitoring = Facts(
            listOf(
                "Prolonged therapy: periodic liver and kidney function tests and blood counts.",
                "Renal function, especially with pre-existing renal dysfunction or nephrotoxic co-medication.",
                "Severe liver disease: periodic liver enzymes.",
                "Older, severely ill patients: watch bowel frequency and for diarrhoea.",
            ),
            listOf(cite(ABX_CLINDA, "Warnings — Nephrotoxicity; Precautions")),
        ),
        renal = Facts(
            listOf("No dose modification needed in renal disease; not removed by haemodialysis or peritoneal dialysis."),
            listOf(cite(ABX_CLINDA, "Precautions; Clinical Pharmacology")),
        ),
        hepatic = Facts(
            listOf(
                "Moderate–severe liver disease prolongs half-life; with 8-hourly dosing, dose change may not be necessary.",
                "Severe liver disease: periodic liver enzyme determinations.",
            ),
            listOf(cite(ABX_CLINDA, "Precautions — General")),
        ),
        commonEffects = Facts(
            listOf(
                "Mild–moderate maculopapular rash (most frequently reported)",
                "Diarrhoea",
                "Nausea",
                "Vomiting",
                "Abdominal pain",
                "Unpleasant or metallic taste",
            ),
            listOf(cite(ABX_CLINDA, "Adverse Reactions (no frequencies given)")),
        ),
        seriousEffects = Facts(
            listOf(
                "C. difficile infection/pseudomembranous colitis (boxed warning).",
                "Anaphylaxis and severe hypersensitivity incl. myocardial ischaemia, TEN, DRESS, SJS — some fatal; stop permanently.",
                "Nephrotoxicity — acute kidney injury; stop if no other cause found.",
                "Oesophagitis and oesophageal ulcer, especially if taken lying down or with little water.",
                "Not for meningitis — inadequate CSF penetration.",
            ),
            listOf(cite(ABX_CLINDA, "Boxed Warning; Warnings; Adverse Reactions")),
        ),
        pk = Pharmacokinetics(
            absorption = "~90% absorbed; peak 45 minutes; food does not appreciably change serum levels.",
            distribution = "Widely distributed incl. bone; no significant CSF levels even with inflamed meninges.",
            metabolism = "Mainly CYP3A4 (minor CYP3A5) to clindamycin sulfoxide and N-desmethylclindamycin.",
            elimination = "~10% of bioactivity in urine, 3.6% in faeces; the rest as inactive metabolites.",
            halfLife = "~2.4 h (~4 h in elderly after oral dosing).",
            citations = listOf(cite(ABX_CLINDA, "Clinical Pharmacology")),
        ),
        interactions = Facts(
            listOf(
                "Neuromuscular blockers: action may be enhanced — use with caution.",
                "Strong CYP3A4 inhibitors: higher clindamycin levels — monitor for adverse reactions.",
                "Strong CYP3A4 inducers (e.g. rifampicin): lower levels — monitor for loss of effect.",
            ),
            listOf(cite(ABX_CLINDA, "Precautions — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("2nd/3rd-trimester use not associated with more congenital abnormalities; use in the first trimester only if clearly needed."),
            listOf(cite(ABX_CLINDA, "Precautions — Pregnancy")),
        ),
        lactation = Facts(
            listOf("In breast milk (<0.5–3.8 mcg/mL); no reason to stop breastfeeding, but an alternative may be preferred — watch infant for diarrhoea, candidiasis, bloody stools."),
            listOf(cite(ABX_CLINDA, "Precautions — Nursing Mothers")),
        ),
    ),
)
