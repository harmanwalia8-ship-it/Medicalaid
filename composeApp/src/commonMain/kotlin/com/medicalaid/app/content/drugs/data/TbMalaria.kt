package com.medicalaid.app.content.drugs.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.drugs.Clearance
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.Facts
import com.medicalaid.app.content.drugs.Pharmacokinetics

/*
 * Antituberculosis and antimalarial drugs. Content paraphrases the cited US FDA labels only
 * (no national-programme / NTEP dosing). Older non-PLR labels are cited by section name.
 */

internal val tbMalariaSources = listOf(
    Source(
        "fda-label-isoniazid", Authority.FDA, "Drug label (DailyMed)",
        "Isoniazid tablets — Marlex Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=312cd403-b211-64ef-e063-6394a90aab88",
        status = "Revised Nov 2026",
    ),
    Source(
        "fda-label-rifampicin", Authority.FDA, "Drug label (DailyMed)",
        "Rifampin capsules — Lupin Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=b56004f0-f23d-47f6-80c7-39884fccbccc",
        status = "Revised Feb 2026",
    ),
    Source(
        "fda-label-pyrazinamide", Authority.FDA, "Drug label (DailyMed)",
        "Pyrazinamide tablets — ANI Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=262d8829-728c-48db-b89b-88d13b87e684",
        status = "Revised Mar 2026",
    ),
    Source(
        "fda-label-ethambutol", Authority.FDA, "Drug label (DailyMed)",
        "Ethambutol hydrochloride tablets — STI Pharma", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=3f6428d6-3745-4337-ad78-ebfff9f49135",
        status = "Revised May 2026",
    ),
    Source(
        "fda-label-artemether-lumefantrine", Authority.FDA, "Drug label (DailyMed)",
        "Artemether and lumefantrine tablets — Novartis Pharmaceuticals", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=7866ec19-dfac-47d4-a53f-511a12643cbf",
        status = "Revised Mar 2026",
    ),
    Source(
        "fda-label-artesunate", Authority.FDA, "Drug label (DailyMed)",
        "Artesunate for injection — Amivas", "2021",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=fe6d4a9e-4478-4603-8bcb-461191ca175b",
        status = "Revised Nov 2021",
    ),
    Source(
        "fda-label-chloroquine", Authority.FDA, "Drug label (DailyMed)",
        "Chloroquine phosphate tablets — Rising Pharma Holdings", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=06c69e2b-211b-4746-9f3a-f86d36520570",
        status = "Revised Oct 2026",
    ),
    Source(
        "fda-label-primaquine", Authority.FDA, "Drug label (DailyMed)",
        "Primaquine phosphate tablets — Unichem Pharmaceuticals (USA)", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=0c8c2bc6-428b-40b6-8114-6df80290878d",
        status = "Revised Feb 2026",
    ),
)

private const val INH = "fda-label-isoniazid"
private const val RIF = "fda-label-rifampicin"
private const val PZA = "fda-label-pyrazinamide"
private const val EMB = "fda-label-ethambutol"
private const val ART_LUM = "fda-label-artemether-lumefantrine"
private const val ARTS = "fda-label-artesunate"
private const val CQ = "fda-label-chloroquine"
private const val PQ = "fda-label-primaquine"

internal val tbMalariaDrugs = listOf(
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "isoniazid",
        name = "Isoniazid",
        drugClass = "Antituberculosis agent",
        uses = "All forms of tuberculosis (with other antituberculosis drugs); preventive therapy of tuberculous infection in defined high-risk groups.",
        clearance = setOf(Clearance.Hepatic),
        boxedWarning = Facts(
            listOf(
                "Severe and sometimes fatal hepatitis; may develop even after many months, usually in the first 3 months.",
                "Risk is age-related: <1/1,000 (<20 y), 3/1,000 (20–34), 12/1,000 (35–49), 23/1,000 (50–64), 8/1,000 (>65); raised by daily alcohol.",
                "Other risk factors: chronic liver disease, injection drug use; possibly women (particularly Black and Hispanic) and the post-partum period.",
                "Interview all patients monthly; age 35 and older: AST/ALT before starting and periodically.",
                "Strongly consider stopping if liver tests exceed 3–5× ULN; stop promptly if symptoms or signs of liver damage. Defer preventive therapy in acute liver disease.",
            ),
            listOf(cite(INH, "Boxed Warning")),
        ),
        dose = Facts(
            listOf(
                "Tuberculosis (with other agents): adults 5 mg/kg up to 300 mg once daily, or 15 mg/kg up to 900 mg/day two or three times weekly.",
                "Preventive therapy: adults over 30 kg, 300 mg once daily.",
                "Give pyridoxine (B6) to the malnourished and those predisposed to neuropathy (e.g. alcoholics, diabetics).",
            ),
            listOf(cite(INH, "DOSAGE AND ADMINISTRATION")),
        ),
        beforeStarting = Facts(
            listOf(
                "Transaminases before starting if age >35, daily alcohol, chronic liver disease, injection drug use, or minority women (especially post-partum).",
                "Drug susceptibility testing on initial isolates from all newly diagnosed tuberculosis.",
                "Before preventive therapy, exclude bacteriologically positive or radiographically progressive tuberculosis.",
            ),
            listOf(cite(INH, "Boxed Warning; PRECAUTIONS — Laboratory Tests; DOSAGE AND ADMINISTRATION")),
        ),
        monitoring = Facts(
            listOf(
                "Clinical review for hepatitis symptoms at monthly intervals in all patients.",
                "Higher-risk groups: transaminases monthly during preventive therapy, or more often as needed.",
                "If any transaminase exceeds 3–5× ULN: stop temporarily and consider restarting.",
            ),
            listOf(cite(INH, "Boxed Warning; PRECAUTIONS — Laboratory Tests")),
        ),
        renal = Facts(
            listOf("Severe renal dysfunction: use with careful monitoring."),
            listOf(cite(INH, "PRECAUTIONS — General")),
        ),
        hepatic = Facts(
            listOf(
                "Contraindicated in acute liver disease of any aetiology and after previous isoniazid-associated hepatic injury.",
                "Active chronic liver disease: use with careful monitoring (higher hepatitis risk).",
            ),
            listOf(cite(INH, "CONTRAINDICATIONS; PRECAUTIONS — General")),
        ),
        commonEffects = Facts(
            listOf(
                "Peripheral neuropathy — the most common toxic effect; dose-related, usually preceded by paraesthesias of feet and hands",
                "Mild, transient transaminase elevation (10–20%), usually in the first 1–3 months",
                "Nausea, vomiting, epigastric distress",
            ),
            listOf(cite(INH, "ADVERSE REACTIONS")),
        ),
        seriousEffects = Facts(
            listOf(
                "Severe, sometimes fatal hepatitis (boxed warning); progressive liver damage in up to 2.3% of those over 50.",
                "Neurotoxicity (uncommon at usual doses): convulsions, toxic encephalopathy, optic neuritis and atrophy, toxic psychosis.",
                "Hypersensitivity reactions — stop all drugs at the first sign; restart only in small, increasing doses.",
                "Agranulocytosis; haemolytic, sideroblastic or aplastic anaemia; thrombocytopenia.",
                "Pyridoxine deficiency, pellagra, hyperglycaemia, metabolic acidosis.",
                "Lupus-like syndrome; rheumatic syndrome.",
            ),
            listOf(cite(INH, "Boxed Warning; PRECAUTIONS — General; ADVERSE REACTIONS")),
        ),
        pk = Pharmacokinetics(
            absorption = "Peak blood levels 1–2 h after oral dose, falling to ≤50% within 6 h; food significantly reduces bioavailability.",
            distribution = "Diffuses readily into all body fluids (CSF, pleural, ascitic) and tissues; crosses the placenta and enters milk.",
            metabolism = "Acetylation and dehydrazination; acetylation rate is genetically determined — slow inactivators have higher levels and more toxicity.",
            elimination = "50–70% of a dose excreted in urine within 24 h.",
            halfLife = null,
            citations = listOf(cite(INH, "CLINICAL PHARMACOLOGY; PRECAUTIONS — Drug Interactions (Food)")),
        ),
        interactions = Facts(
            listOf(
                "Food: lowers bioavailability — do not take with food. Avoid tyramine (cheese, red wine) and histamine-rich foods (e.g. tuna).",
                "Phenytoin, carbamazepine: levels raised — measure carbamazepine level before co-administration; adjust anticonvulsant dose.",
                "Theophylline, valproate: levels may rise — monitor serum levels and adjust dose.",
                "Acetaminophen: severe toxicity reported; isoniazid induces CYP2E1, which generates toxic metabolites.",
                "Ketoconazole: AUC decreased by up to 88% with isoniazid plus rifampin.",
            ),
            listOf(cite(INH, "PRECAUTIONS — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Use for active tuberculosis in pregnancy as benefit justifies risk; preventive therapy is generally started after delivery; observe exposed neonates for adverse effects."),
            listOf(cite(INH, "PRECAUTIONS — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Small amounts in milk do not cause toxicity — breastfeeding should not be discouraged; levels are too low for infant prophylaxis or therapy."),
            listOf(cite(INH, "PRECAUTIONS — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "rifampicin",
        name = "Rifampicin",
        aliases = listOf("rifampin"),
        drugClass = "Rifamycin antibacterial",
        uses = "All forms of tuberculosis (with other drugs); short-term treatment of asymptomatic meningococcal carriers (not meningococcal disease).",
        clearance = setOf(Clearance.Biliary, Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Tuberculosis: adults 10 mg/kg once daily, not to exceed 600 mg/day, with other antituberculosis drugs.",
                "Meningococcal carriers: adults 600 mg twice daily for 2 days.",
                "Take 1 hour before or 2 hours after a meal with a full glass of water.",
            ),
            listOf(cite(RIF, "DOSAGE AND ADMINISTRATION")),
        ),
        beforeStarting = Facts(
            listOf(
                "Adults with tuberculosis: baseline hepatic enzymes, bilirubin, serum creatinine, complete blood count and platelet count.",
                "Bacteriologic cultures before starting to confirm susceptibility.",
            ),
            listOf(cite(RIF, "PRECAUTIONS — Laboratory Tests; INDICATIONS AND USAGE")),
        ),
        monitoring = Facts(
            listOf(
                "See patients at least monthly and ask about adverse-reaction symptoms; routine lab monitoring is generally unnecessary if baseline is normal.",
                "Repeat cultures during therapy; susceptibility tests if cultures stay positive.",
                "Watch for liver injury, especially with prolonged treatment or other hepatotoxic drugs (e.g. isoniazid).",
                "Prothrombin time and coagulation tests if at risk of vitamin K deficiency (liver disease, poor nutrition, prolonged antibacterials or anticoagulants).",
            ),
            listOf(cite(RIF, "PRECAUTIONS — Laboratory Tests; INDICATIONS AND USAGE; WARNINGS")),
        ),
        renal = Facts(
            listOf("No dose adjustment needed in renal failure at doses not exceeding 600 mg/day (half-life unchanged)."),
            listOf(cite(RIF, "CLINICAL PHARMACOLOGY")),
        ),
        hepatic = Facts(
            listOf(
                "Impaired liver function: use only if necessary, under strict supervision; liver tests before therapy and every 2–4 weeks.",
                "Discontinue if signs of hepatic damage occur or worsen.",
            ),
            listOf(cite(RIF, "WARNINGS")),
        ),
        commonEffects = Facts(
            listOf(
                "Heartburn, epigastric distress, anorexia, nausea, vomiting, cramps, diarrhoea",
                "Flushing and itching with or without rash (mild, self-limiting)",
                "Headache, drowsiness, fatigue, dizziness",
                "Transient liver test abnormalities (bilirubin, alkaline phosphatase, transaminases)",
                "Yellow-orange-red-brown discoloration of teeth, urine, sweat, sputum and tears; soft contact lenses may be permanently stained",
                "Menstrual disturbances",
            ),
            listOf(cite(RIF, "ADVERSE REACTIONS; PRECAUTIONS — Information for Patients")),
        ),
        seriousEffects = Facts(
            listOf(
                "Hepatotoxicity, from enzyme rises to fulminant liver failure and death.",
                "Systemic hypersensitivity (fever, rash, hypotension, bronchospasm, thrombocytopenia, flu-like syndrome) — discontinue.",
                "Severe cutaneous reactions: SJS, TEN, AGEP, DRESS — discontinue immediately.",
                "Vitamin K–dependent coagulation disorders and bleeding.",
                "Pulmonary toxicity (interstitial lung disease), may be fatal.",
                "Thrombotic microangiopathy (TTP, HUS), some fatal.",
                "Thrombocytopenia, mainly with high-dose intermittent therapy; continuing after purpura has caused cerebral haemorrhage and deaths.",
                "Intermittent or interrupted dosing: 'flu syndrome', shock and renal hypersensitivity reactions (including acute renal failure).",
            ),
            listOf(cite(RIF, "WARNINGS; PRECAUTIONS — General; ADVERSE REACTIONS")),
        ),
        pk = Pharmacokinetics(
            absorption = "Readily absorbed; 600 mg gives mean peak ≈7 mcg/mL (4–32); food reduces absorption by about 30%.",
            distribution = "Widely distributed, including CSF; about 80% protein bound.",
            metabolism = "Progressive deacetylation during enterohepatic circulation; the deacetylated metabolite has antibacterial activity.",
            elimination = "Rapidly eliminated in bile with enterohepatic circulation; up to 30% of a dose in urine, about half unchanged.",
            halfLife = "≈3.35 h after 600 mg (up to 5.08 h after 900 mg); about 2–3 h with repeated dosing.",
            citations = listOf(cite(RIF, "CLINICAL PHARMACOLOGY")),
        ),
        interactions = Facts(
            listOf(
                "Contraindicated: ritonavir-boosted saquinavir (severe hepatotoxicity); atazanavir, darunavir, fosamprenavir, tipranavir, cabotegravir, fostemsavir, lenacapavir for HIV-1 treatment; praziquantel; lurasidone.",
                "Potent inducer of CYP1A2, 2B6, 2C8, 2C9, 2C19, 3A4, UGT and P-gp — lowers exposure of many drugs; adjust per their labels.",
                "Warfarin: check prothrombin time daily or as needed. Digoxin: measure levels before starting; dose may need ≈20–40% increase.",
                "Hormonal contraceptives: reduced exposure — change to non-hormonal birth control during therapy.",
                "Hepatotoxic drugs: avoid halothane; monitor closely for hepatotoxicity with isoniazid.",
            ),
            listOf(cite(RIF, "CONTRAINDICATIONS; PRECAUTIONS — Drug Interactions (Table 1)")),
        ),
        pregnancy = Facts(
            listOf("Decades of data have not established an association with major birth defects or miscarriage; late-pregnancy use can cause postnatal haemorrhage in mother and infant (vitamin K may be indicated)."),
            listOf(cite(RIF, "PRECAUTIONS — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Because of tumorigenicity in animal studies, decide whether to stop nursing or the drug, considering its importance to the mother."),
            listOf(cite(RIF, "PRECAUTIONS — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "pyrazinamide",
        name = "Pyrazinamide",
        drugClass = "Antituberculosis agent",
        uses = "Initial treatment of active tuberculosis in adults and children with other agents; also after failure of other primary drugs.",
        clearance = setOf(Clearance.Hepatic, Clearance.Renal),
        dose = Facts(
            listOf(
                "15–30 mg/kg orally once daily with other antituberculosis drugs, for the initial 2 months of a 6-month or longer regimen.",
                "Do not exceed 3 g/day (CDC daily-regimen maximum 2 g/day).",
                "Alternative: 50–70 mg/kg twice weekly (based on lean body weight).",
            ),
            listOf(cite(PZA, "DOSAGE AND ADMINISTRATION")),
        ),
        beforeStarting = Facts(
            listOf("Baseline liver function (especially ALT, AST) and serum uric acid."),
            listOf(cite(PZA, "WARNINGS; PRECAUTIONS — Laboratory Tests")),
        ),
        monitoring = Facts(
            listOf(
                "Repeat laboratory tests at periodic intervals and if clinical signs or symptoms occur.",
                "Follow closely if pre-existing liver disease or increased hepatitis risk (e.g. alcohol abuse).",
                "Discontinue permanently if hepatocellular damage, or hyperuricaemia with acute gouty arthritis, appears.",
            ),
            listOf(cite(PZA, "WARNINGS; PRECAUTIONS — Laboratory Tests")),
        ),
        renal = Facts(
            listOf("Impaired renal function does not appear to require dose reduction; selecting doses at the low end of the range may be prudent."),
            listOf(cite(PZA, "PRECAUTIONS — Geriatric Use")),
        ),
        hepatic = Facts(
            listOf(
                "Contraindicated in severe hepatic damage.",
                "Pre-existing liver disease: follow closely; half-life may be prolonged in hepatic impairment.",
            ),
            listOf(cite(PZA, "CONTRAINDICATIONS; WARNINGS; CLINICAL PHARMACOLOGY")),
        ),
        commonEffects = Facts(
            listOf(
                "Hyperuricaemia, frequent and usually asymptomatic",
                "Mild arthralgia and myalgia (reported frequently)",
                "Nausea, vomiting, anorexia",
                "Rash, urticaria, pruritus",
            ),
            listOf(cite(PZA, "ADVERSE REACTIONS; PRECAUTIONS — General")),
        ),
        seriousEffects = Facts(
            listOf(
                "Hepatotoxicity — the principal adverse effect; dose-related, may appear at any time.",
                "Acute gout — contraindicated in acute gout; discontinue if gouty arthritis develops.",
                "Thrombocytopenia and sideroblastic anaemia (rare); blood clotting effects (rare).",
                "Diabetes management may become more difficult.",
                "Rare: porphyria, interstitial nephritis, photosensitivity.",
            ),
            listOf(cite(PZA, "CONTRAINDICATIONS; WARNINGS; PRECAUTIONS — General; ADVERSE REACTIONS")),
        ),
        pk = Pharmacokinetics(
            absorption = "Well absorbed; peak plasma levels within 2 h (30–50 mcg/mL at 20–25 mg/kg).",
            distribution = "Wide, including liver, lungs and CSF (≈ plasma level with inflamed meninges); about 10% protein bound.",
            metabolism = "Hydrolysed in the liver to active pyrazinoic acid, then hydroxylated to 5-hydroxypyrazinoic acid.",
            elimination = "About 70% of an oral dose in urine within 24 h, mainly by glomerular filtration.",
            halfLife = "9–10 h; may be prolonged in renal or hepatic impairment.",
            citations = listOf(cite(PZA, "CLINICAL PHARMACOLOGY")),
        ),
        interactions = Facts(
            listOf("Interferes with Acetest and Ketostix urine ketone tests (pink-brown colour)."),
            listOf(cite(PZA, "PRECAUTIONS — Drug/Laboratory Test Interactions")),
        ),
        pregnancy = Facts(
            listOf("No animal reproduction studies; unknown whether it causes fetal harm — give only if clearly needed."),
            listOf(cite(PZA, "PRECAUTIONS — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Found in small amounts in breast milk; use with caution, weighing risk and benefit."),
            listOf(cite(PZA, "PRECAUTIONS — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "ethambutol",
        name = "Ethambutol",
        drugClass = "Antituberculosis agent",
        uses = "Pulmonary tuberculosis, always with at least one other antituberculosis drug.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Initial treatment: 15 mg/kg orally once every 24 hours.",
                "Retreatment: 25 mg/kg once every 24 hours with at least one other active drug; after 60 days reduce to 15 mg/kg.",
                "Weight-dose table tops out at 1,500 mg (15 mg/kg) and 2,500 mg (25 mg/kg). Not recommended under 13 years.",
            ),
            listOf(cite(EMB, "DOSAGE AND ADMINISTRATION")),
        ),
        beforeStarting = Facts(
            listOf(
                "Visual acuity before starting — each eye separately and both together (Snellen chart); wear usual corrective glasses.",
                "Eye examination including ophthalmoscopy, finger perimetry and colour discrimination.",
                "Baseline hepatic, renal and haematopoietic function.",
                "Contraindicated in known optic neuritis (unless judged usable) and in patients unable to report visual changes (e.g. young children, unconscious).",
            ),
            listOf(cite(EMB, "CONTRAINDICATIONS; WARNINGS; PRECAUTIONS; ADVERSE REACTIONS")),
        ),
        monitoring = Facts(
            listOf(
                "Visual acuity periodically; monthly when the dose is more than 15 mg/kg/day.",
                "Ask periodically about blurred vision and other eye symptoms.",
                "Significant fall in Snellen lines: retest; if confirmed with no other cause, discontinue and re-evaluate frequently.",
                "Periodic hepatic, renal and haematopoietic function.",
            ),
            listOf(cite(EMB, "WARNINGS; PRECAUTIONS; ADVERSE REACTIONS; DOSAGE AND ADMINISTRATION")),
        ),
        renal = Facts(
            listOf("Decreased renal function: reduce dose as determined by serum ethambutol levels (main excretion is renal; marked accumulation in renal insufficiency)."),
            listOf(cite(EMB, "PRECAUTIONS; CLINICAL PHARMACOLOGY")),
        ),
        commonEffects = Facts(
            listOf(
                "Anorexia, nausea, vomiting, GI upset, abdominal pain",
                "Fever, malaise, headache, dizziness",
                "Dermatitis, pruritus, joint pain",
                "Elevated serum uric acid",
                "Numbness and tingling of extremities (peripheral neuritis)",
            ),
            listOf(cite(EMB, "ADVERSE REACTIONS")),
        ),
        seriousEffects = Facts(
            listOf(
                "Optic neuritis: decreased visual acuity, scotoma, colour blindness; usually reversible if stopped promptly, but irreversible blindness reported.",
                "Liver toxicity, including fatalities.",
                "Hypersensitivity syndrome (rash or exfoliative dermatitis, eosinophilia, hepatitis, pneumonitis, nephritis, myocarditis, pericarditis); anaphylaxis.",
                "Thrombocytopenia, leucopenia, neutropenia.",
                "Precipitation of acute gout.",
                "Pulmonary infiltrates, with or without eosinophilia.",
                "Mental confusion, disorientation, possible hallucinations.",
            ),
            listOf(cite(EMB, "WARNINGS; ADVERSE REACTIONS")),
        ),
        pk = Pharmacokinetics(
            absorption = "Peak 2–5 mcg/mL at 2–4 h after 25 mg/kg; absorption not significantly altered by food.",
            distribution = "Erythrocyte concentrations about twice plasma levels.",
            metabolism = "Oxidation to an aldehyde intermediate, then a dicarboxylic acid; 8–15% of a dose appears as metabolites.",
            elimination = "About 50% unchanged in urine within 24 h; 20–22% unchanged in faeces.",
            halfLife = null,
            citations = listOf(cite(EMB, "CLINICAL PHARMACOLOGY; DOSAGE AND ADMINISTRATION")),
        ),
        interactions = Facts(
            listOf("Aluminium hydroxide antacids reduce absorption — avoid them for at least 4 hours after ethambutol."),
            listOf(cite(EMB, "PRECAUTIONS — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("No adequate human studies; ophthalmic abnormalities reported in infants of mothers on regimens including ethambutol — use only if benefit justifies risk."),
            listOf(cite(EMB, "PRECAUTIONS — Pregnancy")),
        ),
        lactation = Facts(
            listOf("Excreted into breast milk; use only if expected benefit to the mother outweighs potential risk to the infant."),
            listOf(cite(EMB, "PRECAUTIONS — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "artemether-lumefantrine",
        name = "Artemether–lumefantrine",
        aliases = listOf("artemether and lumefantrine"),
        drugClass = "Antimalarial combination (artemether + lumefantrine)",
        uses = "Acute uncomplicated P. falciparum malaria in patients ≥2 months and ≥5 kg. Not for severe malaria or prevention.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Adults ≥35 kg: 4 tablets (20 mg/120 mg each) at 0 h and 8 h, then twice daily for 2 days — 6 doses, 24 tablets total.",
                "Children by weight per dose: 5–<15 kg 1 tablet; 15–<25 kg 2; 25–<35 kg 3.",
                "Take with food. If vomited within 1–2 h, repeat the dose; if vomited again, use another antimalarial.",
            ),
            listOf(cite(ART_LUM, "2.1–2.3 Dosage and Administration")),
        ),
        monitoring = Facts(
            listOf(
                "ECG monitoring if QT-prolonging drugs are medically required after treatment (lumefantrine half-life 3–6 days).",
                "Patients who remain averse to food: monitor closely — risk of recrudescence may be greater.",
                "After mefloquine: monitor for decreased efficacy and encourage food.",
                "With CYP2D6 substrates: monitor for adverse reactions and QT prolongation.",
            ),
            listOf(cite(ART_LUM, "5.2; 5.5; 7 Drug Interactions")),
        ),
        renal = Facts(
            listOf("No dose adjustment advised (no significant renal excretion); not studied in severe renal impairment — use caution."),
            listOf(cite(ART_LUM, "2.4; 5.6; 8.6")),
        ),
        hepatic = Facts(
            listOf("No dose adjustment in mild–moderate hepatic impairment; not studied in severe impairment — use caution."),
            listOf(cite(ART_LUM, "2.4; 5.6; 8.6")),
        ),
        commonEffects = Facts(
            listOf(
                "Headache (56%, adults)",
                "Anorexia (40%)",
                "Dizziness (39%)",
                "Asthenia (38%)",
                "Arthralgia (34%)",
                "Myalgia (32%)",
                "Nausea (26%)",
                "Children (>12%): pyrexia, cough, vomiting, anorexia, headache",
            ),
            listOf(cite(ART_LUM, "6.1 Clinical Trials Experience — Tables 1–2")),
        ),
        seriousEffects = Facts(
            listOf(
                "QT prolongation — avoid in known or congenital long QT, family history of sudden death, hypokalaemia/hypomagnesaemia, or with QT-prolonging drugs.",
                "Halofantrine: not within 1 month; other antimalarials not concomitantly unless no other option.",
                "CYP3A4 interactions: inducers cause loss of efficacy; inhibitors may potentiate QT prolongation.",
                "CYP2D6 substrates: higher levels; avoid QT-prolonging ones (flecainide, imipramine, amitriptyline, clomipramine).",
                "Recrudescence, especially with poor food intake — retreat with a different antimalarial.",
                "P. vivax: additional treatment needed for radical cure (hypnozoites).",
                "Hypersensitivity: anaphylaxis, urticaria, angioedema, bullous eruptions (postmarketing).",
                "Delayed haemolytic anaemia, mostly after IV artesunate for severe malaria (postmarketing).",
            ),
            listOf(cite(ART_LUM, "5.1–5.7 Warnings and Precautions; 6.2 Postmarketing Experience")),
        ),
        pk = Pharmacokinetics(
            absorption = "Artemether peaks ~2 h; lumefantrine peaks 6–8 h. High-fat meal raises bioavailability 2–3-fold (artemether) and 16-fold (lumefantrine).",
            distribution = "Protein binding 95.4% (artemether), 99.7% (lumefantrine), 47–76% (DHA).",
            metabolism = "Artemether via CYP3A4/5 (less 2B6, 2C9, 2C19) to active DHA; lumefantrine via CYP3A4 to desbutyl-lumefantrine. Lumefantrine inhibits CYP2D6.",
            elimination = "No artemether or lumefantrine found in urine; urinary DHA <0.01% of the artemether dose.",
            halfLife = "Artemether and DHA ≈2 h; lumefantrine 3–6 days.",
            citations = listOf(cite(ART_LUM, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Strong CYP3A4 inducers (rifampin, carbamazepine, phenytoin, St John's wort): contraindicated — rifampin cut artemether, DHA, lumefantrine exposure by 89%, 85%, 68%.",
                "CYP3A4 inhibitors (e.g. ketoconazole, grapefruit juice): raise exposure — use cautiously (QT risk).",
                "Antiretrovirals (protease inhibitors, NNRTIs): variable effects — use cautiously (QT prolongation or loss of efficacy).",
                "Hormonal contraceptives: may be less effective — use a non-hormonal method or add a barrier method.",
                "Quinine, quinidine and other QT-prolonging drugs after treatment: use cautiously; ECG monitoring if required.",
            ),
            listOf(cite(ART_LUM, "4 Contraindications; 7 Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Clinical and pharmacovigilance data have not established an association with major birth defects, miscarriage or adverse maternal or fetal outcomes; malaria itself increases pregnancy risks."),
            listOf(cite(ART_LUM, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("No human milk data (both transfer into rat milk); weigh breastfeeding benefits against the mother's need and possible infant effects."),
            listOf(cite(ART_LUM, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "artesunate",
        name = "Artesunate",
        drugClass = "Artemisinin antimalarial (intravenous)",
        uses = "Initial treatment of severe malaria in adults and children, always followed by a complete oral antimalarial course.",
        // Label: converted by blood esterases to DHA, then glucuronidated; excretion route not known.
        clearance = emptySet(),
        dose = Facts(
            listOf(
                "2.4 mg/kg IV at 0, 12 and 24 hours, then once daily until oral antimalarial therapy is tolerated.",
                "Give as a slow IV bolus over 1–2 minutes; do NOT give as a continuous infusion.",
                "Follow with a complete oral antimalarial course; add an 8-aminoquinoline for P. vivax or P. ovale.",
            ),
            listOf(cite(ARTS, "1 Indications and Usage; 2.1 Recommended Dosage")),
        ),
        monitoring = Facts(
            listOf(
                "Monitor for 4 weeks after treatment for haemolytic anaemia (delayed haemolysis occurs ≥7 days after starting).",
                "If delayed haemolysis: consider a direct antiglobulin test to decide on therapy (e.g. corticosteroids).",
            ),
            listOf(cite(ARTS, "5.1 Post-Treatment Hemolysis")),
        ),
        renal = Facts(
            listOf("No specific dosage adjustment needed in renal impairment."),
            listOf(cite(ARTS, "8.6 Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("No specific dose adjustment needed in hepatic impairment."),
            listOf(cite(ARTS, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Acute renal failure requiring dialysis (8.9% vs 7.3% quinine; severe malaria)",
                "Haemoglobinuria (6.7% vs 4.5%)",
                "Jaundice (2.3% vs 1.9%)",
                "Anaemia (65%, US open-label study)",
                "Transaminase increase (27%)",
                "Thrombocytopenia (18%)",
                "Hyperbilirubinaemia (14%)",
                "Acute renal failure (10%)",
            ),
            listOf(cite(ARTS, "6.1 Clinical Trials Experience — Table 1; Trial 3")),
        ),
        seriousEffects = Facts(
            listOf(
                "Post-treatment (delayed) haemolysis, sometimes severe enough to need transfusion.",
                "Hypersensitivity including anaphylaxis — consider stopping if hypotension, dyspnoea, urticaria or generalised rash.",
                "Embryo-fetal toxicity in animals (clinical relevance uncertain).",
                "Significant neurologic impairment at discharge: 1% vs 0.4% with quinine.",
            ),
            listOf(cite(ARTS, "5.1–5.3 Warnings and Precautions; 6.1")),
        ),
        pk = Pharmacokinetics(
            absorption = "Given IV; median Cmax 3.3 mcg/mL (artesunate) and 3.1 mcg/mL (DHA) in severe malaria.",
            distribution = "Median Vd 68.5 L (artesunate), 59.7 L (DHA); protein binding about 93%.",
            metabolism = "Rapidly converted by blood esterases to active DHA; DHA glucuronidated (UGT1A9 or UGT2B7).",
            elimination = "Urinary excretion unknown; clearance 180 L/h (artesunate), 32.3 L/h (DHA).",
            halfLife = "Median 0.3 h (artesunate); 1.3 h (DHA).",
            citations = listOf(cite(ARTS, "12.3 Pharmacokinetics; 12.4 Microbiology")),
        ),
        interactions = Facts(
            listOf(
                "Ritonavir, nevirapine, strong UGT inducers (rifampin, carbamazepine, phenytoin): lower DHA exposure — monitor for reduced efficacy.",
                "Strong UGT inhibitors (e.g. axitinib, vandetanib, imatinib, diclofenac): may raise DHA — monitor for adverse reactions.",
            ),
            listOf(cite(ARTS, "7.1 Effect of Other Drugs on Artesunate and DHA")),
        ),
        pregnancy = Facts(
            listOf("Experience with artemisinins has not identified a drug-associated risk of birth defects or miscarriage; animal data show embryolethality — do not delay treating severe malaria because of pregnancy."),
            listOf(cite(ARTS, "5.3; 8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("DHA is present in human milk; effects on the infant unknown — weigh breastfeeding benefits against the mother's need."),
            listOf(cite(ARTS, "8.2 Lactation")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "chloroquine",
        name = "Chloroquine",
        drugClass = "4-aminoquinoline antimalarial",
        uses = "Uncomplicated malaria due to susceptible P. falciparum, malariae, ovale and vivax; prophylaxis where no chloroquine resistance; extraintestinal amoebiasis.",
        clearance = setOf(Clearance.Renal),
        dose = Facts(
            listOf(
                "Malaria (adults): 1 g salt (600 mg base), then 500 mg (300 mg base) at 6–8 h and on each of 2 more days — total 2.5 g salt (1.5 g base).",
                "Prophylaxis (adults): 500 mg salt (300 mg base) once weekly on the same day; start 2 weeks before exposure, continue 8 weeks after leaving.",
                "Extraintestinal amoebiasis: 1 g salt (600 mg base) daily for 2 days, then 500 mg (300 mg base) daily for at least 2–3 weeks.",
            ),
            listOf(cite(CQ, "DOSAGE AND ADMINISTRATION")),
        ),
        beforeStarting = Facts(
            listOf(
                "Baseline eye exam within the first year: best corrected visual acuity, automated central 10° visual field, SD-OCT (central 24° field if Asian descent).",
                "Before prophylaxis, confirm chloroquine is appropriate for the region (resistance).",
            ),
            listOf(cite(CQ, "WARNINGS — Retinopathy; Chloroquine-Resistant Malaria")),
        ),
        monitoring = Facts(
            listOf(
                "Annual eye exams (BCVA, VF, SD-OCT) if risk factors; without risk factors usually deferrable until 5 years of use.",
                "Complete blood counts periodically on prolonged therapy.",
                "Long-term therapy: assess muscle strength and deep tendon reflexes periodically.",
                "Watch for cardiotoxicity; check blood glucose if hypoglycaemia symptoms; liver tests promptly if symptoms of liver injury.",
            ),
            listOf(cite(CQ, "WARNINGS; PRECAUTIONS — Hematological Effects/Laboratory Tests")),
        ),
        renal = Facts(
            listOf(
                "Substantially excreted by the kidney; toxicity risk may be greater in renal impairment — careful dose selection in the elderly; consider monitoring renal function.",
                "Subnormal glomerular filtration is a risk factor for retinopathy (annual eye exams).",
            ),
            listOf(cite(CQ, "PRECAUTIONS — Geriatric Use; WARNINGS — Retinopathy")),
        ),
        hepatic = Facts(
            listOf("Concentrates in the liver — use with caution in hepatic disease, alcoholism or with hepatotoxic drugs."),
            listOf(cite(CQ, "PRECAUTIONS — Use in Patients with Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Anorexia, nausea, vomiting, diarrhoea, abdominal cramps",
                "Mild, transient headache",
                "Blurred vision, difficulty focusing or accommodating",
                "Pruritus, pleomorphic skin eruptions, skin and mucosal pigment changes",
                "Photosensitivity; hair loss or bleaching",
            ),
            listOf(cite(CQ, "ADVERSE REACTIONS")),
        ),
        seriousEffects = Facts(
            listOf(
                "Cardiotoxicity: cardiomyopathy, conduction block, QT prolongation, torsades — caution in heart disease, hypokalaemia/hypomagnesaemia, bradycardia <50 bpm.",
                "Irreversible retinopathy (risk: >2.3 mg/kg/day salt, >5 years, low GFR, tamoxifen, macular disease).",
                "Skeletal muscle myopathy and neuropathy.",
                "Severe hypoglycaemia, with or without antidiabetic drugs.",
                "Neuropsychiatric reactions including suicidality (usually first month); extrapyramidal disorders; convulsions.",
                "Haemolysis in G6PD deficiency; pancytopenia, aplastic anaemia, agranulocytosis.",
                "Renal toxicity (proteinuria); may precipitate severe psoriasis; exacerbates porphyria.",
                "Fatal accidental ingestion in children (e.g. 0.75–1 g salt in a 3-year-old).",
            ),
            listOf(cite(CQ, "WARNINGS; PRECAUTIONS; ADVERSE REACTIONS")),
        ),
        pk = Pharmacokinetics(
            absorption = "Rapidly and almost completely absorbed from the GI tract.",
            distribution = "About 55% bound to plasma constituents; extensive tissue deposition (liver, spleen, kidney, lung 200–700× plasma in animals).",
            metabolism = "Main metabolite desethylchloroquine (also antimalarial); bisdesethylchloroquine and others in small amounts.",
            elimination = "Slow urinary excretion, increased by acidifying urine; slightly over half of urinary drug products are unchanged chloroquine.",
            halfLife = null,
            citations = listOf(cite(CQ, "CLINICAL PHARMACOLOGY; PRECAUTIONS — Drug Interactions")),
        ),
        interactions = Facts(
            listOf(
                "Arrhythmogenic drugs (e.g. amiodarone, moxifloxacin): increased risk of ventricular arrhythmias.",
                "Insulin and other antidiabetics: hypoglycaemic effect may be enhanced — doses may need lowering.",
                "Antacids, kaolin: separate by at least 4 h. Ampicillin: separate by at least 2 h.",
                "Cimetidine raises chloroquine levels — avoid. Cyclosporine levels may rise suddenly — monitor closely.",
                "Mefloquine: increased convulsion risk. Tamoxifen: not recommended (retinal toxicity).",
            ),
            listOf(cite(CQ, "PRECAUTIONS — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("At recommended malaria doses, observational studies and a meta-analysis showed no increase in birth defects or spontaneous abortion; review individual benefit–risk."),
            listOf(cite(CQ, "WARNINGS — Usage in Pregnancy")),
        ),
        lactation = Facts(
            listOf("Excreted in milk (infant receives ~0.7% of the maternal dose); decide whether to stop nursing or the drug; infant needs separate chemoprophylaxis."),
            listOf(cite(CQ, "PRECAUTIONS — Nursing Mothers")),
        ),
    ),
    // ---------------------------------------------------------------------------------------------
    DrugMonograph(
        id = "primaquine",
        name = "Primaquine",
        drugClass = "8-aminoquinoline antimalarial",
        uses = "Radical cure (prevention of relapse) of vivax malaria.",
        clearance = setOf(Clearance.Hepatic),
        dose = Facts(
            listOf(
                "Adults: 1 tablet (15 mg base; 26.3 mg primaquine phosphate) once daily for 14 days, alongside chloroquine for the acute attack.",
                "With or without food; food may reduce GI symptoms.",
            ),
            listOf(cite(PQ, "DOSAGE AND ADMINISTRATION; DESCRIPTION")),
        ),
        beforeStarting = Facts(
            listOf(
                "G6PD testing must be performed before use; contraindicated in severe G6PD deficiency.",
                "Baseline haemoglobin and haematocrit; if severely anaemic, postpone G6PD test and treatment until recovery.",
                "G6PD status unknown and test unavailable: weigh risks; assess risk factors for G6PD deficiency or favism.",
                "Pregnancy test in sexually active females of reproductive potential.",
                "Breastfeeding: check the infant's G6PD status first.",
            ),
            listOf(cite(PQ, "CONTRAINDICATIONS; WARNINGS — Hemolytic Anemia; Use in Females and Males of Reproductive Potential; Nursing Mothers")),
        ),
        monitoring = Facts(
            listOf(
                "Mild–moderate G6PD deficiency or unknown status: close haematological monitoring (e.g. day 3 and day 8).",
                "G6PD-normal: routine blood counts and haemoglobin during therapy.",
                "ECG if cardiac disease, long QT, ventricular arrhythmia history, uncorrected hypokalaemia/hypomagnesaemia, bradycardia <50 bpm, or QT-prolonging drugs.",
                "Methaemoglobin closely if NADH methaemoglobin reductase deficiency or with dapsone or sulfonamides.",
                "More monitoring for relapse in CYP2D6 poor or intermediate metabolisers or with strong CYP2D6 inhibitors.",
            ),
            listOf(cite(PQ, "WARNINGS — Hemolytic Anemia; PRECAUTIONS")),
        ),
        renal = Facts(
            listOf("Not assessed after repeated dosing; no specific dose adjustment — monitor efficacy and adverse reactions, especially in severe renal impairment."),
            listOf(cite(PQ, "PRECAUTIONS — Renal Impairment")),
        ),
        hepatic = Facts(
            listOf("Not assessed after repeated dosing; no specific dose adjustment — monitor efficacy and adverse reactions, especially in severe hepatic impairment."),
            listOf(cite(PQ, "PRECAUTIONS — Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Nausea, vomiting, epigastric distress, abdominal cramps",
                "Transient methaemoglobin rise (usually <10%)",
                "Dizziness",
                "Rash, pruritus",
            ),
            listOf(cite(PQ, "ADVERSE REACTIONS; PRECAUTIONS — Methemoglobinemia")),
        ),
        seriousEffects = Facts(
            listOf(
                "Haemolytic anaemia in G6PD deficiency — may be severe or fatal with severe deficiency; stop if dark urine, pallor, dyspnoea.",
                "Methaemoglobinaemia — may be severe with NADH methaemoglobin reductase deficiency or methaemoglobin-inducing drugs.",
                "Leukopenia in patients with granulocytopenia (e.g. rheumatoid arthritis, lupus); avoid bone-marrow depressants.",
                "QT prolongation and cardiac arrhythmia.",
                "Treatment failure in CYP2D6 poor or intermediate metabolisers.",
                "Genotoxicity and embryo-fetal toxicity (contraindicated in pregnancy).",
            ),
            listOf(cite(PQ, "WARNINGS; PRECAUTIONS; ADVERSE REACTIONS")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability >70%; Tmax ≈2.3 h; food raises AUC 14% and Cmax 26%.",
            distribution = "74% protein bound (mainly alpha-1 acid glycoprotein); Vd ≈243 L.",
            metabolism = "MAO-A oxidative deamination, CYP2D6 ring hydroxylation (activity likely depends on CYP2D6 metabolites), direct conjugation; carboxyprimaquine main circulating metabolite.",
            elimination = "Mainly by metabolism; 64% of dose in urine (3.6% as primaquine).",
            halfLife = "≈5.6 h (carboxyprimaquine ≈22 h).",
            citations = listOf(cite(PQ, "CLINICAL PHARMACOLOGY — Pharmacokinetics; Pharmacogenomics")),
        ),
        interactions = Facts(
            listOf(
                "Quinacrine: contraindicated, concurrently or if received recently — increased toxicity.",
                "Haemolytic or methaemoglobin-inducing drugs: avoid; if unavoidable, close blood monitoring.",
                "QT-prolonging drugs: close and frequent ECG monitoring.",
                "Strong CYP2D6 inhibitors: may reduce efficacy — prefer alternatives or monitor for relapse. Defer MAO-A inhibitors if CYP2D6 activity is reduced.",
                "Inhibits CYP1A2, CYP3A4 and P-gp: monitor substrates (e.g. theophylline, tizanidine, pimozide, digoxin, dabigatran).",
            ),
            listOf(cite(PQ, "CONTRAINDICATIONS; PRECAUTIONS — Drug Interactions")),
        ),
        pregnancy = Facts(
            listOf("Contraindicated — may cause haemolytic anaemia in a G6PD-deficient fetus; women should use effective contraception during and for 6 months after treatment (men: condoms for 3 months)."),
            listOf(cite(PQ, "CONTRAINDICATIONS; WARNINGS — Pregnancy; Use in Females and Males of Reproductive Potential")),
        ),
        lactation = Facts(
            listOf("Contraindicated if the infant is G6PD deficient or of unknown status; in G6PD-normal pairs, estimated infant intake was <1% of a 0.5 mg/kg/day dose."),
            listOf(cite(PQ, "CONTRAINDICATIONS; WARNINGS — Nursing Mothers")),
        ),
    ),
)
