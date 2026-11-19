package com.medicalaid.app.content

import com.medicalaid.app.content.GaWindow.Companion.weeks

/** Antenatal tests, scans, vaccines, supplements and visits, by guideline. */
object Schedule {
    private const val TERM_END = 40 * 7 + 6

    val items: List<ScheduleItem> = listOf(
        // ============================================================ India — MoHFW / ICMR
        ScheduleItem(
            "in-anc1", "ANC visit 1 (registration)", ItemKind.Visit, Authority.MoHFW,
            GaWindow(0, 12 * 7 + 6, "Within 12 weeks"),
            "At least four ANC visits for every pregnant woman. First visit within 12 weeks — preferably as soon as pregnancy is suspected. Haemoglobin and urine sugar and protein are tested at every visit.",
            citations = listOf(cite("mohfw-anc-2010", "ANC visit schedule")),
        ),
        ScheduleItem(
            "in-anc2", "ANC visit 2", ItemKind.Visit, Authority.MoHFW,
            weeks(14, 26, label = "14–26 weeks"),
            "Second ANC visit. Haemoglobin and urine sugar and protein at every visit.",
            citations = listOf(cite("mohfw-anc-2010", "ANC visit schedule")),
        ),
        ScheduleItem(
            "in-anc3", "ANC visit 3", ItemKind.Visit, Authority.MoHFW,
            weeks(28, 34, label = "28–34 weeks"),
            "Third ANC visit. If not already done, get the blood group including Rh factor tested at this visit.",
            citations = listOf(cite("mohfw-anc-2010", "ANC visit schedule; third visit")),
        ),
        ScheduleItem(
            "in-anc4", "ANC visit 4", ItemKind.Visit, Authority.MoHFW,
            GaWindow(36 * 7, TERM_END, "36 weeks – term"),
            "Fourth ANC visit, between 36 weeks and term.",
            citations = listOf(cite("mohfw-anc-2010", "ANC visit schedule")),
        ),
        ScheduleItem(
            "in-pmsma", "PMSMA check-up by a doctor", ItemKind.Visit, Authority.MoHFW,
            GaWindow(14 * 7, TERM_END, "2nd or 3rd trimester"),
            "A minimum package of antenatal care on the 9th of every month (next working day if a holiday), so that every pregnant woman gets at least one check-up by a doctor in the 2nd or 3rd trimester. High-risk pregnancies get up to three additional visits under extended PMSMA.",
            citations = listOf(cite("mohfw-pmsma-2016")),
        ),
        ScheduleItem(
            "in-usg", "Ultrasound — at least one", ItemKind.Scan, Authority.MoHFW,
            GaWindow(14 * 7, TERM_END, "2nd or 3rd trimester"),
            "“One ultrasound is recommended for all pregnant women during the 2nd/3rd trimester of pregnancy.”",
            citations = listOf(cite("mohfw-pmsma-2016", "Services under PMSMA")),
        ),
        ScheduleItem(
            "in-usg-level2", "Level II ultrasound (anomaly scan)", ItemKind.Scan, Authority.ICMR,
            weeks(18, 20, label = "18–20 weeks"),
            "Second-trimester ultrasound (Level II) between 18 and 20 weeks.",
            citations = listOf(cite("icmr-stw-anc-2025", "Second trimester")),
        ),
        ScheduleItem(
            "in-hb", "Haemoglobin", ItemKind.Test, Authority.MoHFW, null,
            "Test haemoglobin at every ANC contact (at least four times; eight times for high-risk pregnancies). Anaemia in pregnancy: Hb <11.0 g/dL in the 1st and 3rd trimesters, <10.5 g/dL in the 2nd.",
            citations = listOf(cite("mohfw-anc-2010"), cite("mohfw-amb-2026", "Table 1 — Hb testing; Hb cut-offs")),
        ),
        ScheduleItem(
            "in-urine", "Urine albumin and sugar", ItemKind.Test, Authority.MoHFW, null,
            "Test urine for sugar and protein at every visit.",
            citations = listOf(cite("mohfw-anc-2010")),
        ),
        ScheduleItem(
            "in-bloodgroup", "Blood group and Rh", ItemKind.Test, Authority.ICMR, null,
            "ABO and Rh typing at the first visit (essential investigation). The MoHFW 2010 ANM/SBA guideline asks for it by the third visit (28–34 weeks) if not done earlier.",
            citations = listOf(cite("icmr-stw-anc-2025", "First visit — essential tests"), cite("mohfw-anc-2010", "Third visit")),
        ),
        ScheduleItem(
            "in-infections", "HIV, syphilis (VDRL/RPR) and HBsAg", ItemKind.Test, Authority.MoHFW, null,
            "HIV testing, VDRL/RPR and HBsAg are listed among the investigations at PHC/CHC/FRU level; the ICMR workflow lists them at the first visit.",
            citations = listOf(cite("mohfw-anc-2010", "Investigations at PHC/CHC/FRU"), cite("icmr-stw-anc-2025", "First visit — desirable tests")),
        ),
        ScheduleItem(
            "in-gdm1", "GDM test — 75 g OGTT (first contact)", ItemKind.Test, Authority.MoHFW, null,
            "Test all pregnant women at the first antenatal contact: 75 g glucose in ~300 mL water, whether fasting or not and irrespective of the last meal. Plasma glucose at 2 hours ≥140 mg/dL = GDM (plasma-calibrated glucometer). If she vomits within 30 minutes, repeat the next day.",
            citations = listOf(cite("mohfw-gdm-2018", "Screening and diagnosis")),
        ),
        ScheduleItem(
            "in-gdm2", "GDM test — repeat 75 g OGTT", ItemKind.Test, Authority.MoHFW,
            weeks(24, 28, label = "24–28 weeks"),
            "Second test at 24–28 weeks if the first test was negative, at least 4 weeks after the first. A woman presenting after 28 weeks gets only one test.",
            appliesTo = "If the first-contact test was negative",
            citations = listOf(cite("mohfw-gdm-2018", "Screening and diagnosis")),
        ),
        ScheduleItem(
            "in-tsh", "TSH — high-risk women", ItemKind.Test, Authority.MoHFW, null,
            "Screening for hypothyroidism is recommended in high-risk pregnant women, with the other ANC investigations: residence in an iodine-deficient area, BMI ≥30, prior thyroid disease or goitre, family history, autoimmune disease, history of recurrent miscarriage, preterm birth or pre-eclampsia, infertility, or use of amiodarone or lithium.",
            appliesTo = "High-risk women only",
            citations = listOf(cite("mohfw-thyroid-2014", "Who to screen")),
        ),
        ScheduleItem(
            "in-td", "Td vaccine (Td-1, Td-2 or booster)", ItemKind.Vaccine, Authority.MoHFW, null,
            "Td-1 early in pregnancy; Td-2 four weeks after Td-1. Td booster (single dose) if she received 2 TT/Td doses in a pregnancy within the last 3 years. 0.5 mL IM, upper arm. Previous TT doses remain valid.",
            citations = listOf(cite("mohfw-td"), cite("mohfw-nis")),
        ),
        ScheduleItem(
            "in-folic", "Folic acid 400 mcg daily", ItemKind.Supplement, Authority.MoHFW,
            GaWindow(0, 13 * 7 + 6, "Up to end of 1st trimester"),
            "400 mcg folic acid daily from before pregnancy up to the end of the first trimester.",
            citations = listOf(cite("mohfw-amb-2026", "Folic acid")),
        ),
        ScheduleItem(
            "in-ifa", "IFA — 1 tablet daily for 180 days", ItemKind.Supplement, Authority.MoHFW,
            GaWindow(14 * 7, TERM_END, "From 2nd trimester"),
            "One Red IFA tablet (60 mg elemental iron + 500 mcg folic acid) daily for 180 days starting from the second trimester; continue for 180 days after delivery. Take IFA two hours after a meal and not together with calcium.",
            citations = listOf(cite("mohfw-amb-2026", "IFA prophylaxis"), cite("mohfw-calcium-2014", "IFA and calcium timing")),
        ),
        ScheduleItem(
            "in-calcium", "Calcium 500 mg + vitamin D3 twice daily", ItemKind.Supplement, Authority.MoHFW,
            GaWindow(14 * 7, TERM_END, "From 14 weeks"),
            "One tablet of 500 mg elemental calcium with 250 IU vitamin D3 twice a day (1 g calcium/day) from 14 weeks of pregnancy up to six months postpartum. Take with meals, not both tablets together, and not with IFA.",
            citations = listOf(cite("mohfw-calcium-2014")),
        ),
        ScheduleItem(
            "in-albendazole", "Albendazole 400 mg, single dose", ItemKind.Supplement, Authority.MoHFW,
            weeks(14, 27, label = "After 1st trimester (preferably 2nd)"),
            "A single dose of albendazole 400 mg once after the first trimester, preferably in the second trimester. Do not use in the first trimester.",
            citations = listOf(cite("mohfw-deworming-2014"), cite("mohfw-amb-2026", "Deworming")),
        ),

        // ============================================================ ACOG / CDC / USPSTF
        ScheduleItem(
            "us-type-screen", "Blood group, Rh(D) and antibody screen", ItemKind.Test, Authority.ACOG, null,
            "Test all pregnant women at the first prenatal visit for ABO blood group and Rh D type and screen for erythrocyte antibodies.",
            citations = listOf(cite("acog-pb181")),
        ),
        ScheduleItem(
            "us-cbc", "Complete blood count", ItemKind.Test, Authority.ACOG, null,
            "CBC in the first trimester, repeated at 24–28 weeks.",
            citations = listOf(cite("acog-pb233", "Screening")),
        ),
        ScheduleItem(
            "us-hiv", "HIV (opt-out)", ItemKind.Test, Authority.ACOG, null,
            "Opt-out HIV testing as early in pregnancy as possible.",
            citations = listOf(cite("acog-co752")),
        ),
        ScheduleItem(
            "us-syphilis", "Syphilis serology", ItemKind.Test, Authority.ACOG, null,
            "Screen all pregnant individuals for syphilis at the first prenatal visit, then rescreen universally in the third trimester and at birth.",
            citations = listOf(cite("acog-pa-syphilis")),
        ),
        ScheduleItem(
            "us-hep", "HBsAg and hepatitis C antibody", ItemKind.Test, Authority.ACOG, null,
            "Screen for hepatitis B surface antigen early in pregnancy, and for hepatitis C antibody in each pregnancy.",
            citations = listOf(cite("acog-cpg6")),
        ),
        ScheduleItem(
            "us-urine-culture", "Urine culture (asymptomatic bacteriuria)", ItemKind.Test, Authority.USPSTF, null,
            "Midstream, clean-catch urine culture at the first prenatal visit or at 12–16 weeks, whichever is earlier.",
            citations = listOf(cite("uspstf-asb-2019")),
        ),
        ScheduleItem(
            "us-early-dm", "Screen for pre-existing diabetes", ItemKind.Test, Authority.ACOG, null,
            "ACOG does not recommend routine GDM screening before 24 weeks, but recommends screening for pregestational diabetes before 24 weeks — preferably at the start of prenatal care — in patients with risk factors.",
            appliesTo = "Patients with risk factors",
            citations = listOf(cite("acog-pb190", "with 2024 Clinical Practice Update on screening")),
        ),
        ScheduleItem(
            "us-thyroid", "TSH only if indicated", ItemKind.Test, Authority.ACOG, null,
            "Universal screening for thyroid disease in pregnancy is not recommended. When testing is indicated, TSH is the first-line test.",
            appliesTo = "Clinical indication",
            citations = listOf(cite("acog-pb223")),
        ),
        ScheduleItem(
            "us-cfdna", "Aneuploidy screening — cfDNA", ItemKind.Test, Authority.ACOG,
            GaWindow(10 * 7, TERM_END, "From 9–10 weeks"),
            "cfDNA screening for trisomies 21, 18 and 13 should be routinely available to all patients, from 9–10 weeks to term. Sex-chromosome screening is opt-in; routine microdeletion screening is not recommended.",
            citations = listOf(cite("acog-pa-aneuploidy", "Endorses SMFM Consult Series #74; Table 1")),
        ),
        ScheduleItem(
            "us-fts", "First-trimester screen (NT + serum)", ItemKind.Test, Authority.ACOG,
            weeks(10, 13, label = "10w 0d – 13w 6d"),
            "Nuchal translucency with PAPP-A and hCG (alternative to cfDNA). Laboratory and CRL windows vary.",
            citations = listOf(cite("acog-pa-aneuploidy", "Table 1")),
        ),
        ScheduleItem(
            "us-quad", "Quad screen", ItemKind.Test, Authority.ACOG,
            weeks(15, 22, label = "15–22 weeks"),
            "Second-trimester serum screening (alternative when first-trimester screening was not done).",
            citations = listOf(cite("acog-pa-aneuploidy", "Table 1")),
        ),
        ScheduleItem(
            "us-anatomy", "Anatomy ultrasound", ItemKind.Scan, Authority.ACOG,
            weeks(18, 22, label = "18–22 weeks"),
            "An anatomic survey is recommended for all patients, regardless of the aneuploidy screening method — ideally at 18–22 weeks.",
            citations = listOf(cite("acog-pa-aneuploidy"), cite("acog-pb175")),
        ),
        ScheduleItem(
            "us-aspirin", "Low-dose aspirin 81 mg daily", ItemKind.Supplement, Authority.ACOG,
            GaWindow(12 * 7, 28 * 7, "Start 12–28 weeks (ideally <16)"),
            "When recommended, start between 12 and 28 weeks (optimally before 16 weeks) and continue daily until delivery. See High blood pressure → Prevention for the risk factors.",
            appliesTo = "High risk for pre-eclampsia",
            citations = listOf(cite("acog-co743"), cite("acog-pa-aspirin")),
        ),
        ScheduleItem(
            "us-gdm", "Gestational diabetes screening", ItemKind.Test, Authority.ACOG,
            weeks(24, 28, label = "24–28 weeks"),
            "Screen for gestational diabetes at 24–28 weeks.",
            citations = listOf(cite("acog-pb190")),
        ),
        ScheduleItem(
            "us-cbc2", "Repeat CBC", ItemKind.Test, Authority.ACOG,
            weeks(24, 28, label = "24–28 weeks"),
            "Repeat complete blood count at 24 0/7–28 6/7 weeks.",
            citations = listOf(cite("acog-pb233", "Screening")),
        ),
        ScheduleItem(
            "us-tdap", "Tdap vaccine", ItemKind.Vaccine, Authority.ACOG,
            weeks(27, 36, label = "27–36 weeks (early in window)"),
            "One dose of Tdap during each pregnancy, preferably during the early part of weeks 27 through 36, irrespective of prior Tdap history.",
            citations = listOf(cite("acog-co718"), cite("acog-cs26", "2026 Maternal Immunization Schedule")),
        ),
        ScheduleItem(
            "us-anti-d", "Antibody screen and anti-D 300 mcg", ItemKind.Test, Authority.ACOG,
            weeks(28, 28, label = "28 weeks"),
            "Offer prophylactic anti-D immune globulin (single antenatal dose of 300 micrograms) at 28 weeks to unsensitized Rh D-negative women. A repeat antibody screen before administration is advised.",
            appliesTo = "Rh(D)-negative, unsensitized",
            citations = listOf(cite("acog-pb181")),
        ),
        ScheduleItem(
            "us-syphilis-t3", "Syphilis rescreen", ItemKind.Test, Authority.ACOG,
            GaWindow(28 * 7, TERM_END, "Third trimester"),
            "Universal rescreening for syphilis during the third trimester, and again at birth.",
            citations = listOf(cite("acog-pa-syphilis")),
        ),
        ScheduleItem(
            "us-hiv-t3", "Repeat HIV test", ItemKind.Test, Authority.ACOG,
            weeks(28, 35, label = "3rd trimester, before 36 weeks"),
            "Repeat HIV testing in the third trimester, preferably before 36 weeks, for patients at high risk, in high-incidence settings, incarcerated, or with symptoms.",
            appliesTo = "High risk or high-incidence setting",
            citations = listOf(cite("acog-co752")),
        ),
        ScheduleItem(
            "us-rsv", "RSV vaccine (RSVpreF, Abrysvo)", ItemKind.Vaccine, Authority.ACOG,
            weeks(32, 36, label = "32w 0d – 36w 6d"),
            "Single dose at 32 0/7–36 6/7 weeks, seasonally (ACOG: September 1 – March 1 in the US), if no delivery is planned within 2 weeks, not vaccinated in a previous pregnancy, and the infant will not receive nirsevimab or clesrovimab. Only Abrysvo is approved in pregnancy.",
            appliesTo = "Seasonal; alternative is infant monoclonal antibody",
            citations = listOf(cite("acog-pa-rsv")),
        ),
        ScheduleItem(
            "us-gbs", "Group B streptococcus culture", ItemKind.Test, Authority.ACOG,
            weeks(36, 37, label = "36w 0d – 37w 6d"),
            "Universal GBS screening between 36 0/7 and 37 6/7 weeks.",
            citations = listOf(cite("acog-co797")),
        ),
        ScheduleItem(
            "us-flu", "Influenza vaccine", ItemKind.Vaccine, Authority.ACOG, null,
            "A seasonally updated influenza vaccine at any time during pregnancy. Use only inactivated (IIV) or recombinant (RIV) vaccine; live intranasal vaccine is not approved in pregnancy.",
            appliesTo = "During influenza season",
            citations = listOf(cite("acog-pa-flu")),
        ),
        ScheduleItem(
            "us-covid", "COVID-19 vaccine", ItemKind.Vaccine, Authority.ACOG, null,
            "ACOG recommends routine administration of updated COVID-19 vaccines in pregnancy, in any trimester, as soon as possible. (US federal guidance changed in 2025 and remains contested; ACOG's position is shown.)",
            citations = listOf(cite("acog-pa-covid")),
        ),
        ScheduleItem(
            "us-hepb-vax", "Hepatitis B vaccine", ItemKind.Vaccine, Authority.ACOG, null,
            "Recommended during pregnancy for those not already vaccinated or with risk factors.",
            appliesTo = "Unvaccinated or at risk",
            citations = listOf(cite("acog-cs26", "2026 Maternal Immunization Schedule"), cite("acog-cpg6")),
        ),
        ScheduleItem(
            "us-live", "MMR and varicella — do not give", ItemKind.Vaccine, Authority.ACOG, null,
            "Live vaccines (MMR, varicella) are contraindicated in pregnancy; give postpartum if non-immune.",
            appliesTo = "Contraindicated in pregnancy",
            citations = listOf(cite("acog-cs26", "2026 Maternal Immunization Schedule")),
        ),
    )
}
