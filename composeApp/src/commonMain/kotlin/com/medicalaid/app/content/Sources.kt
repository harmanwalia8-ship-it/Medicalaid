package com.medicalaid.app.content

/**
 * Every document the app cites. Status lines reflect what the issuing body's own page showed
 * when the content was last reviewed (see [REVIEWED]).
 */
object Sources {
    const val REVIEWED = "November 2026"

    private val guidelines: List<Source> = listOf(
        // ---------------------------------------------------------------- Government of India
        Source(
            "mohfw-anc-2010", Authority.MoHFW, "Guidelines",
            "Antenatal Care and Skilled Attendance at Birth by ANMs/LHVs/SNs", "2010",
            "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/sba_guidelines_for_skilled_attendance_at_birth.pdf",
        ),
        Source(
            "mohfw-pmsma-2016", Authority.MoHFW, "Guidelines",
            "Pradhan Mantri Surakshit Matritva Abhiyan (PMSMA)", "2016",
            "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/Pradhan_Mantri_Surakshit_Matritva_Abhiyan.pdf",
        ),
        Source(
            "mohfw-td", Authority.MoHFW, "UIP Operational Guidelines",
            "Tetanus and adult diphtheria (Td) vaccine", "2018",
            "https://nhm.gov.in/New_Updates_2018/NHM_Components/Immunization/Guildelines_for_immunization/Td_vaccine_operational_guidelines.pdf",
        ),
        Source(
            "mohfw-nis", Authority.MoHFW, "UIP",
            "National Immunization Schedule for Infants, Children and Pregnant Women", "2020",
            "https://nhm.gov.in/New_Updates_2018/NHM_Components/Immunization/report/National_%20Immunization_Schedule.pdf",
        ),
        Source(
            "mohfw-amb-2026", Authority.MoHFW, "Anemia Mukt Bharat",
            "Abhiyaan Operational Guidelines", "2026",
            "https://www.nhm.gov.in/New-Update-2025-26/Nutrition/AMB-guidelines/AMB-Abhiyaan-Guidelines.pdf",
            status = "Supersedes the 2018 AMB guidelines",
        ),
        Source(
            "mohfw-calcium-2014", Authority.MoHFW, "National Guidelines",
            "Calcium Supplementation During Pregnancy and Lactation", "2014",
            "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/National_Guidelines_for_Calcium_Supplementation_During_Pregnancy_and_Lactation.pdf",
        ),
        Source(
            "mohfw-deworming-2014", Authority.MoHFW, "National Guidelines",
            "Deworming in Pregnancy", "2014",
            "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/National_Guidelines_for_Deworming_in_Pregnancy.pdf",
        ),
        Source(
            "mohfw-gdm-2018", Authority.MoHFW, "Technical & Operational Guidelines",
            "Diagnosis & Management of Gestational Diabetes Mellitus", "2018",
            "https://nhm.gov.in/New_Updates_2018/NHM_Components/RMNCH_MH_Guidelines/Gestational-Diabetes-Mellitus.pdf",
        ),
        Source(
            "mohfw-thyroid-2014", Authority.MoHFW, "National Guidelines",
            "Screening of Hypothyroidism during Pregnancy", "2014",
            "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/National_Guidelines_for_Screening_of_Hypothyroidism_during_Pregnancy.pdf",
        ),
        Source(
            "mohfw-mo-handbook-2009", Authority.MoHFW, "Trainees' Handbook (Medical Officers)",
            "Pregnancy Care and Management of Common Obstetric Complications", "2009",
            "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/trainees_handbook_for_training_of_mos_in_preg_care.pdf",
        ),
        Source(
            "mohfw-dakshata-2015", Authority.MoHFW, "Dakshata",
            "Facilitators' Guide — strengthening intra-partum care", "2015",
            "https://nhm.gov.in/images/pdf/programmes/maternal-health/guidelines/Dakshata-Facilitators_Guidelines.pdf",
        ),
        Source(
            "icmr-stw-anc-2025", Authority.ICMR, "Standard Treatment Workflow",
            "Ante-natal Management of Normal Pregnancy", "2025",
            "https://www.icmr.gov.in/icmrobject/uploads/STWs/1771568619_ante-natalmanagementofnormalpregnancy-7.pdf",
        ),

        // ---------------------------------------------------------------- ACOG
        Source(
            "acog-co700", Authority.ACOG, "Committee Opinion No. 700",
            "Methods for Estimating the Due Date", "2017",
            "https://www.acog.org/clinical/guidance/committee-opinion/2017/05/methods-for-estimating-the-due-date",
            status = "Reaffirmed 2025",
        ),
        Source(
            "acog-co579", Authority.ACOG, "Committee Opinion No. 579",
            "Definition of Term Pregnancy", "2013",
            "https://www.acog.org/clinical/guidance/committee-opinion/2013/11/definition-of-term-pregnancy",
            status = "Reaffirmed 2025",
        ),
        Source(
            "acog-pb189", Authority.ACOG, "Practice Bulletin No. 189",
            "Nausea and Vomiting of Pregnancy", "2018",
            "https://www.acog.org/clinical/guidance/practice-bulletin/2018/01/nausea-and-vomiting-of-pregnancy",
            status = "Reaffirmed 2024",
        ),
        Source(
            "acog-cpg3", Authority.ACOG, "Clinical Practice Guideline No. 3",
            "Headaches in Pregnancy and Postpartum", "2022",
            "https://www.acog.org/clinical/guidance/clinical-practice-guideline/2022/05/headaches-in-pregnancy-and-postpartum",
            status = "Reaffirmed 2024",
        ),
        Source(
            "acog-pa-apap", Authority.ACOG, "Practice Advisory",
            "Acetaminophen Use in Pregnancy and Neurodevelopmental Outcomes", "2025",
            "https://www.acog.org/clinical/guidance/practice-advisory/2025/09/acetaminophen-use-in-pregnancy-and-neurodevelopmental-outcomes",
        ),
        Source(
            "acog-pb203", Authority.ACOG, "Practice Bulletin No. 203",
            "Chronic Hypertension in Pregnancy", "2019",
            "https://www.acog.org/clinical/guidance/practice-bulletin/2019/01/chronic-hypertension-in-pregnancy",
            status = "Reaffirmed 2024",
        ),
        Source(
            "acog-pa-chap", Authority.ACOG, "Practice Advisory",
            "Clinical Guidance for the Integration of the Findings of the CHAP Study", "2022",
            "https://www.acog.org/clinical/guidance/practice-advisory/2022/04/clinical-guidance-for-the-integration-of-the-findings-of-the-chronic-hypertension-and-pregnancy-chap-study",
            status = "Reaffirmed 2025",
        ),
        Source(
            "acog-pb222", Authority.ACOG, "Practice Bulletin No. 222",
            "Gestational Hypertension and Preeclampsia", "2020",
            "https://www.acog.org/clinical/guidance/practice-bulletin/2020/06/gestational-hypertension-and-preeclampsia",
            status = "Reaffirmed 2026",
        ),
        Source(
            "acog-co743", Authority.ACOG, "Committee Opinion No. 743",
            "Low-Dose Aspirin Use During Pregnancy", "2018",
            "https://www.acog.org/clinical/guidance/committee-opinion/2018/07/low-dose-aspirin-use-during-pregnancy",
            status = "Reaffirmed 2023",
        ),
        Source(
            "acog-pa-aspirin", Authority.ACOG, "Practice Advisory (with SMFM)",
            "Low-Dose Aspirin Use for the Prevention of Preeclampsia and Related Morbidity and Mortality", "2021",
            "https://www.acog.org/clinical/guidance/practice-advisory/2021/12/low-dose-aspirin-use-for-the-prevention-of-preeclampsia-and-related-morbidity-and-mortality",
            status = "Reaffirmed 2022",
        ),
        Source(
            "acog-cs26", Authority.ACOG, "Committee Statement No. 26",
            "Maternal Immunizations", "2026",
            "https://www.acog.org/clinical/guidance/committee-statement/2026/02/maternal-immunizations",
        ),
        Source(
            "acog-co718", Authority.ACOG, "Committee Opinion No. 718",
            "Update on Immunization and Pregnancy: Tetanus, Diphtheria, and Pertussis Vaccination", "2017",
            "https://www.acog.org/clinical/guidance/committee-opinion/2017/09/update-on-immunization-and-pregnancy-tetanus-diphtheria-and-pertussis-vaccination",
            status = "Reaffirmed 2022",
        ),
        Source(
            "acog-pa-flu", Authority.ACOG, "Practice Advisory",
            "Influenza in Pregnancy: Prevention and Treatment", "2025",
            "https://www.acog.org/clinical/guidance/practice-advisory/2025/08/influenza-in-pregnancy-prevention-and-treatment",
            status = "Updated September 2026",
        ),
        Source(
            "acog-pa-covid", Authority.ACOG, "Practice Advisory",
            "COVID-19 Vaccination Considerations for Obstetric–Gynecologic Care", "2020",
            "https://www.acog.org/clinical/guidance/practice-advisory/2020/12/covid-19-vaccination-considerations-for-obstetric-gynecologic-care",
            status = "Updated August 2026",
        ),
        Source(
            "acog-pa-rsv", Authority.ACOG, "Practice Advisory",
            "Maternal Respiratory Syncytial Virus Vaccination", "2023",
            "https://www.acog.org/clinical/guidance/practice-advisory/2023/09/maternal-respiratory-syncytial-virus-vaccination",
            status = "Updated August 2026",
        ),
        Source(
            "acog-pa-aneuploidy", Authority.ACOG, "Practice Advisory",
            "Screening for Fetal Chromosomal Abnormalities", "2026",
            "https://www.acog.org/clinical/guidance/practice-advisory/2026/01/screening-for-fetal-chromosomal-abnormalities",
            status = "Replaces Practice Bulletin No. 226",
        ),
        Source(
            "acog-pb175", Authority.ACOG, "Practice Bulletin No. 175",
            "Ultrasound in Pregnancy", "2016",
            "https://www.acog.org/clinical/guidance/practice-bulletin/2016/12/ultrasound-in-pregnancy",
            status = "Reaffirmed 2025",
        ),
        Source(
            "acog-pb190", Authority.ACOG, "Practice Bulletin No. 190",
            "Gestational Diabetes Mellitus", "2018",
            "https://www.acog.org/clinical/guidance/practice-bulletin/2018/02/gestational-diabetes-mellitus",
            status = "Reaffirmed 2026",
        ),
        Source(
            "acog-pb181", Authority.ACOG, "Practice Bulletin No. 181",
            "Prevention of Rh D Alloimmunization", "2017",
            "https://www.acog.org/clinical/guidance/practice-bulletin/2017/08/prevention-of-rh-d-alloimmunization",
            status = "Reaffirmed 2026",
        ),
        Source(
            "acog-co797", Authority.ACOG, "Committee Opinion No. 797",
            "Prevention of Group B Streptococcal Early-Onset Disease in Newborns", "2020",
            "https://www.acog.org/clinical/guidance/committee-opinion/2020/02/prevention-of-group-b-streptococcal-early-onset-disease-in-newborns",
            status = "Reaffirmed 2025",
        ),
        Source(
            "acog-pa-syphilis", Authority.ACOG, "Practice Advisory",
            "Screening for Syphilis in Pregnancy", "2024",
            "https://www.acog.org/clinical/guidance/practice-advisory/2024/04/screening-for-syphilis-in-pregnancy",
            status = "Reaffirmed 2025",
        ),
        Source(
            "acog-co752", Authority.ACOG, "Committee Opinion No. 752",
            "Prenatal and Perinatal Human Immunodeficiency Virus Testing", "2018",
            "https://www.acog.org/clinical/guidance/committee-opinion/2018/09/prenatal-and-perinatal-human-immunodeficiency-virus-testing",
            status = "Reaffirmed 2024",
        ),
        Source(
            "acog-cpg6", Authority.ACOG, "Clinical Practice Guideline No. 6",
            "Viral Hepatitis in Pregnancy", "2023",
            "https://www.acog.org/clinical/guidance/clinical-practice-guideline/2023/09/viral-hepatitis-in-pregnancy",
        ),
        Source(
            "acog-pb233", Authority.ACOG, "Practice Bulletin No. 233",
            "Anemia in Pregnancy", "2021",
            "https://www.acog.org/clinical/guidance/practice-bulletin/2021/08/anemia-in-pregnancy",
            status = "Reaffirmed 2024",
        ),
        Source(
            "acog-pb223", Authority.ACOG, "Practice Bulletin No. 223",
            "Thyroid Disease in Pregnancy", "2020",
            "https://www.acog.org/clinical/guidance/practice-bulletin/2020/06/thyroid-disease-in-pregnancy",
            status = "Reaffirmed 2023",
        ),

        // ---------------------------------------------------------------- Other bodies
        Source(
            "uspstf-asb-2019", Authority.USPSTF, "Recommendation Statement",
            "Asymptomatic Bacteriuria in Adults: Screening", "2019",
            "https://www.uspreventiveservicestaskforce.org/uspstf/recommendation/asymptomatic-bacteriuria-in-adults-screening",
        ),
        Source(
            "fda-nsaid-2020", Authority.FDA, "Drug Safety Communication",
            "Avoid NSAIDs in pregnancy at 20 weeks or later (low amniotic fluid)", "2020",
            "https://www.fda.gov/drugs/drug-safety-and-availability/fda-recommends-avoiding-use-nsaids-pregnancy-20-weeks-or-later-because-they-can-result-low-amniotic",
        ),
        Source(
            "fda-apap", Authority.FDA, "Consumer information",
            "Acetaminophen — maximum daily dose", "2023",
            "https://www.fda.gov/drugs/safe-use-over-counter-pain-relievers-and-fever-reducers/acetaminophen",
        ),
        Source(
            "fda-apap-notice-2025", Authority.FDA, "Notice to Physicians",
            "Use of Acetaminophen During Pregnancy", "2025",
            "https://www.fda.gov/media/188843/download",
        ),
        Source(
            "cdc-sti-vvc", Authority.CDC, "STI Treatment Guidelines",
            "Vulvovaginal Candidiasis", "2021",
            "https://www.cdc.gov/std/treatment-guidelines/candidiasis.htm",
        ),
        Source(
            "cdc-sti-bv", Authority.CDC, "STI Treatment Guidelines",
            "Bacterial Vaginosis", "2021",
            "https://www.cdc.gov/std/treatment-guidelines/bv.htm",
        ),
        Source(
            "cdc-sti-trich", Authority.CDC, "STI Treatment Guidelines",
            "Trichomoniasis", "2021",
            "https://www.cdc.gov/std/treatment-guidelines/trichomoniasis.htm",
        ),
        Source(
            "idsa-asb-2019", Authority.IDSA, "Clinical Practice Guideline",
            "Management of Asymptomatic Bacteriuria: 2019 Update", "2019",
            "https://www.idsociety.org/practice-guideline/asymptomatic-bacteriuria/",
        ),
        Source(
            "nice-ng201", Authority.NICE, "Guideline NG201",
            "Antenatal care", "2021",
            "https://www.nice.org.uk/guidance/ng201/chapter/Recommendations",
        ),
        Source(
            "nice-ng109", Authority.NICE, "Guideline NG109",
            "Urinary tract infection (lower): antimicrobial prescribing", "2018",
            "https://www.nice.org.uk/guidance/ng109/chapter/Recommendations",
        ),
        Source(
            "nice-ng111", Authority.NICE, "Guideline NG111",
            "Pyelonephritis (acute): antimicrobial prescribing", "2018",
            "https://www.nice.org.uk/guidance/ng111/chapter/Recommendations",
        ),
        Source(
            "rcog-gtg43", Authority.RCOG, "Green-top Guideline No. 43",
            "Intrahepatic Cholestasis of Pregnancy", "2022",
            "https://www.rcog.org.uk/guidance/browse-all-guidance/green-top-guidelines/intrahepatic-cholestasis-of-pregnancy-green-top-guideline-no-43/",
        ),
        Source(
            "ada-s2", Authority.ADA, "Standards of Care 2026 · Section 2",
            "Diagnosis and Classification of Diabetes", "2026",
            "https://diabetesjournals.org/care/article/49/Supplement_1/S27/163926/2-Diagnosis-and-Classification-of-Diabetes",
        ),
        Source(
            "ada-s6", Authority.ADA, "Standards of Care 2026 · Section 6",
            "Glycemic Goals, Hypoglycemia, and Hyperglycemic Crises", "2026",
            "https://diabetesjournals.org/care/article/49/Supplement_1/S132/163927/6-Glycemic-Goals-Hypoglycemia-and-Hyperglycemic",
        ),
        Source(
            "ada-s9", Authority.ADA, "Standards of Care 2026 · Section 9",
            "Pharmacologic Approaches to Glycemic Treatment", "2026",
            "https://diabetesjournals.org/care/article/49/Supplement_1/S183/163934/9-Pharmacologic-Approaches-to-Glycemic-Treatment",
        ),
        Source(
            "ada-s15", Authority.ADA, "Standards of Care 2026 · Section 15",
            "Management of Diabetes in Pregnancy", "2026",
            "https://diabetesjournals.org/care/article/49/Supplement_1/S321/163918/15-Management-of-Diabetes-in-Pregnancy-Standards",
        ),
        Source(
            "ada-eag", Authority.ADA, "eAG calculator (ADAG study, Diabetes Care 2008)",
            "Translating the A1C Assay Into Estimated Average Glucose Values", "2008",
            "https://professional.diabetes.org/glucose_calc",
        ),
        Source(
            "icmr-t2dm-2018", Authority.ICMR, "Guidelines",
            "Management of Type 2 Diabetes", "2018",
            "https://www.icmr.gov.in/icmrobject/custom_data/pdf/resource-guidelines/ICMR_GuidelinesType2diabetes2018_0.pdf",
        ),
        Source(
            "icmr-stw-t2dm-2022", Authority.ICMR, "Standard Treatment Workflow",
            "Diabetes Mellitus Type 2 (ICD-10 E11)", "2022",
            "https://icmr.gov.in/icmrobject/uploads/STWs/1726567245_diabetes_mellitus_type_2.pdf",
        ),
        Source(
            "nice-ng145", Authority.NICE, "Guideline NG145",
            "Thyroid disease: assessment and management", "2019",
            "https://www.nice.org.uk/guidance/ng145/chapter/Recommendations",
            status = "Updated 2023; reviewed 2025",
        ),
        Source(
            "ata-2014", Authority.ATA, "Guideline",
            "Guidelines for the Treatment of Hypothyroidism", "2014",
            "https://doi.org/10.1089/thy.2014.0028",
        ),
        Source(
            "ata-aace-2012", Authority.ATA, "Guideline (with AACE)",
            "Clinical Practice Guidelines for Hypothyroidism in Adults", "2012",
            "https://doi.org/10.1089/thy.2012.0205",
        ),
        Source(
            "ata-2026-pregnancy", Authority.ATA, "Guideline",
            "2026 Guidelines for Thyroid Disease in Preconception, Pregnancy, and Postpartum", "2026",
            "https://doi.org/10.1177/10507256261445624",
            status = "Replaces the 2017 ATA pregnancy guideline",
        ),
        Source(
            "icmr-stw-hypothyroid-2022", Authority.ICMR, "Standard Treatment Workflow",
            "Hypothyroidism (ICD-10 E03.9)", "2022",
            "https://www.icmr.gov.in/icmrobject/uploads/STWs/1726567243_hypothyroidism.pdf",
        ),
        Source(
            "nkf-ckd-epi-2021", Authority.NKF, "Equation",
            "CKD-EPI Creatinine Equation", "2021",
            "https://www.kidney.org/ckd-epi-creatinine-equation-2021",
        ),
        Source(
            "kdigo-ckd-2024", Authority.KDIGO, "Clinical Practice Guideline",
            "Evaluation and Management of Chronic Kidney Disease", "2024",
            "https://kdigo.org/guidelines/ckd-evaluation-and-management/",
        ),
        Source(
            "fda-renal-guidance-2024", Authority.FDA, "Guidance for Industry",
            "Pharmacokinetics in Patients with Impaired Renal Function — Impact on Dosing", "2024",
            "https://www.fda.gov/regulatory-information/search-fda-guidance-documents/pharmacokinetics-patients-impaired-renal-function-study-design-data-analysis-and-impact-dosing",
        ),
        Source(
            "cockcroft-gault-1976", Authority.Paper, "Nephron 1976;16:31–41",
            "Prediction of Creatinine Clearance from Serum Creatinine (Cockcroft & Gault)", "1976",
            "https://doi.org/10.1159/000180580",
        ),
        Source(
            "gina-2026", Authority.GINA, "Strategy Report",
            "Global Strategy for Asthma Management and Prevention", "2026",
            "https://ginasthma.org/2026-gina-strategy-report/",
        ),
        Source(
            "gina-2026-summary", Authority.GINA, "Summary Guide",
            "Asthma Management and Prevention — for Health Professionals", "2026",
            "https://ginasthma.org/wp-content/uploads/2026/07/GINA-Summary-Guide-2026-WEB-WMS.pdf",
        ),
        Source(
            "naepp-pregnancy-2004", Authority.NHLBI, "NAEPP Working Group Report",
            "Managing Asthma During Pregnancy: Recommendations for Pharmacologic Treatment — Update 2004", "2004",
            "https://www.nhlbi.nih.gov/files/docs/astpreg_qr.pdf",
        ),
        Source(
            "icmr-stw-asthma-2019", Authority.ICMR, "Standard Treatment Workflow",
            "Management of Asthma (ICD-10 J45)", "2019",
            "https://icmr.gov.in/icmrobject/uploads/STWs/1725952329_pulmonology_asthma.pdf",
        ),
        Source(
            "smfm-cs53", Authority.SMFM, "Consult Series #53",
            "Intrahepatic Cholestasis of Pregnancy", "2021",
            "https://publications.smfm.org/publications/374-society-for-maternal-fetal-medicine-consult-series-53/",
            status = "Reaffirmed 2024",
        ),
    )

    /** Guideline documents plus the drug labels cited by the drug database. */
    val all: List<Source> = guidelines + cardioRenalConditionSources + heartConditionSources +
        com.medicalaid.app.content.drugs.Drugs.sources +
        com.medicalaid.app.content.scores.Scores.sources + com.medicalaid.app.content.protocols.Protocols.sources

    private val index = all.associateBy { it.id }

    fun byId(id: String): Source = index[id] ?: error("Unknown source id: $id")

    fun exists(id: String) = id in index
}
