package com.medicalaid.app.content.drugs.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.drugs.Clearance
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.Facts
import com.medicalaid.app.content.drugs.Pharmacokinetics

/*
 * TEMPLATE for drug data files. Rules:
 * - One Source per label: id "fda-label-<drug id>", Authority.FDA, document "Drug label (DailyMed)",
 *   title "<generic name> <dosage form> — <manufacturer>", year = label effective year,
 *   url = DailyMed setid page, status = "Revised <Mon YYYY>".
 * - Every Facts carries cite("fda-label-<id>", "<section number and name>").
 * - Paraphrase briefly; only state what the cited section says.
 */

internal val metabolicSources = listOf(
    Source(
        "fda-label-metformin", Authority.FDA, "Drug label (DailyMed)",
        "Metformin hydrochloride tablets — Ascend Laboratories", "2026",
        "https://dailymed.nlm.nih.gov/dailymed/drugInfo.cfm?setid=0ff1ba75-3c86-4baf-a6b1-1bb883839866",
        status = "Revised Nov 2026",
    ),
)

private const val MET = "fda-label-metformin"

internal val metabolicDrugs = listOf(
    DrugMonograph(
        id = "metformin",
        name = "Metformin",
        drugClass = "Biguanide",
        uses = "Type 2 diabetes, with diet and exercise (adults and children ≥10 years).",
        clearance = setOf(Clearance.Renal),
        boxedWarning = Facts(
            listOf(
                "Lactic acidosis, including fatal cases. Risk factors: renal impairment, age ≥65, iodinated contrast, surgery, hypoxic states, excess alcohol, hepatic impairment, carbonic anhydrase inhibitors (e.g. topiramate).",
            ),
            listOf(cite(MET, "Boxed Warning; 5.1")),
        ),
        dose = Facts(
            listOf(
                "Immediate release: start 500 mg twice daily or 850 mg once daily with meals; increase by 500 mg weekly or 850 mg every 2 weeks; max 2,550 mg/day in divided doses.",
                "Extended release: start 500 mg once daily with the evening meal; increase by 500 mg weekly; max 2,000 mg once daily. Swallow whole.",
            ),
            listOf(cite(MET, "2.1 Adult Dosage")),
        ),
        beforeStarting = Facts(
            listOf("Assess renal function (eGFR) before starting."),
            listOf(cite(MET, "2.3 Renal Impairment")),
        ),
        monitoring = Facts(
            listOf(
                "eGFR periodically, more often in older patients.",
                "Haematological parameters yearly; vitamin B12 every 2–3 years.",
                "Iodinated contrast: stop at or before the procedure if eGFR 30–60, liver disease, alcoholism, heart failure or intra-arterial contrast; recheck eGFR at 48 h and restart if stable.",
            ),
            listOf(cite(MET, "2.4; 5.2; 8.5")),
        ),
        renal = Facts(
            listOf(
                "eGFR <30: contraindicated.",
                "eGFR 30–45: starting is not recommended.",
                "If eGFR falls below 45 on treatment: assess benefit and risk of continuing.",
                "If eGFR falls below 30: discontinue.",
            ),
            listOf(cite(MET, "2.3 Renal Impairment; 8.6")),
        ),
        hepatic = Facts(
            listOf("Not recommended in hepatic impairment — associated with cases of lactic acidosis."),
            listOf(cite(MET, "8.7 Hepatic Impairment")),
        ),
        commonEffects = Facts(
            listOf(
                "Diarrhoea (53% vs 12% placebo)",
                "Nausea / vomiting (26%)",
                "Flatulence (12%)",
                "Asthenia (9%)",
                "Indigestion (7%)",
                "Abdominal discomfort (6%)",
                "Headache (6%)",
            ),
            listOf(cite(MET, "6.1 Clinical Studies Experience — Table 1")),
        ),
        seriousEffects = Facts(
            listOf(
                "Lactic acidosis (boxed warning).",
                "Vitamin B12 deficiency (subnormal levels in ~7% over 29 weeks).",
                "Hypoglycaemia when combined with insulin or a sulfonylurea.",
            ),
            listOf(cite(MET, "5.1–5.3 Warnings and Precautions")),
        ),
        pk = Pharmacokinetics(
            absorption = "Bioavailability 50–60% (500 mg, fasting); food lowers and delays absorption.",
            distribution = "Negligible protein binding; partitions into red cells.",
            metabolism = "Not metabolised by the liver; no metabolites identified.",
            elimination = "Excreted unchanged in urine, mainly by tubular secretion (~90% within 24 h).",
            halfLife = "≈6.2 h in plasma (≈17.6 h in blood).",
            citations = listOf(cite(MET, "12.3 Pharmacokinetics")),
        ),
        interactions = Facts(
            listOf(
                "Carbonic anhydrase inhibitors (topiramate, acetazolamide): ↑ lactic acidosis risk — monitor more often.",
                "OCT2/MATE inhibitors (ranolazine, dolutegravir, cimetidine): ↑ metformin exposure.",
                "Alcohol: potentiates effect on lactate — avoid excess.",
                "Insulin or sulfonylureas: ↑ hypoglycaemia — may need lower doses.",
            ),
            listOf(cite(MET, "7 Drug Interactions — Table 3")),
        ),
        pregnancy = Facts(
            listOf("Published data have not shown a clear association with major birth defects or miscarriage; poorly controlled diabetes carries maternal and fetal risk."),
            listOf(cite(MET, "8.1 Pregnancy")),
        ),
        lactation = Facts(
            listOf("Present in human milk; effects on the breastfed infant not established — weigh benefits of breastfeeding against the mother's need."),
            listOf(cite(MET, "8.2 Lactation")),
        ),
    ),
)
