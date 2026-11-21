package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.ProtocolCategory
import com.medicalaid.app.content.protocols.ProtocolNote
import com.medicalaid.app.content.protocols.Step

/*
 * Paraquat poisoning. Rules:
 * - Doses, thresholds and windows exactly as the cited document states them; every point is attributed.
 * - No ICMR STW or MoHFW document specific to paraquat was found (ICMR STW list checked); Indian sources
 *   used are ISCCM (IJCCM) papers.
 * - Paraquat has no specific antidote; the app reports what the sources say, it does not instruct.
 */

internal val paraquatSources: List<Source> = listOf(
    Source(
        "tx-paraquat-sukumar-ijccm-2019", Authority.ISCCM, "Review (Indian J Crit Care Med 2019;23(Suppl 4):S263–S266)",
        "Paraquat: The Poison Potion", "2019",
        "https://doi.org/10.5005/jp-journals-10071-23306",
        status = "Narrative review, Indian authors; severity table, urine dithionite, decontamination, immunosuppression, haemoperfusion",
    ),
    Source(
        "tx-paraquat-gawarammana-bjcp-2011", Authority.Paper, "Review (Br J Clin Pharmacol 2011;72(5):745–757)",
        "Medical management of paraquat ingestion", "2011",
        "https://doi.org/10.1111/j.1365-2125.2011.04026.x",
        status = "Narrative review; authors disclose industry-funded investigator roles with the paraquat manufacturer",
    ),
    Source(
        "tx-paraquat-cochrane-cd008084-2021", Authority.Paper, "Cochrane review CD008084.pub5",
        "Glucocorticoid with cyclophosphamide for oral paraquat poisoning", "2021",
        "https://doi.org/10.1002/14651858.CD008084.pub5",
        status = "Li LR et al.; Cochrane Database Syst Rev 2021, Issue 6; searches to Sept 2020; 4 RCTs, 463 participants; GRADE low",
    ),
    Source(
        "tx-paraquat-gawarammana-rct-2018", Authority.Paper, "RCT (Clin Toxicol 2018;56(7):633–639)",
        "High-dose immunosuppression to prevent death after paraquat self-poisoning – a randomised controlled trial", "2018",
        "https://doi.org/10.1080/15563650.2017.1394465",
        status = "Gawarammana IB et al.; Sri Lanka; 299 patients, placebo-controlled; partly funded by the paraquat manufacturer",
    ),
    Source(
        "tx-paraquat-eddleston-qjm-2003", Authority.Paper, "Systematic review (QJM 2003;96(11):809–824)",
        "Prospects for treatment of paraquat-induced lung fibrosis with immunosuppressive drugs and the need for better prediction of outcome: a systematic review",
        "2003",
        "https://doi.org/10.1093/qjmed/hcg137",
        status = "Eddleston M, Wilks M, Buckley N; one author employed by the paraquat manufacturer (disclosed)",
    ),
    Source(
        "tx-paraquat-senarathna-qjm-2009", Authority.Paper, "Prospective cohort (QJM 2009;102(4):251–259)",
        "Prediction of outcome after paraquat poisoning by measurement of the plasma paraquat concentration", "2009",
        "https://doi.org/10.1093/qjmed/hcp006",
        status = "Senarathna L et al.; Sri Lanka; 451 patients; compared five published prediction methods",
    ),
    Source(
        "tx-paraquat-koh-toxrep-2014", Authority.Paper, "Non-randomised multicentre study (Toxicol Rep 2014;1:490–495)",
        "Survival predictors in paraquat intoxification and role of immunosuppression", "2014",
        "https://doi.org/10.1016/j.toxrep.2014.06.010",
        status = "Koh K-H et al.; Malaysia; 85 treated vs 83 historical controls",
    ),
    Source(
        "tx-paraquat-goyal-ijccm-2024", Authority.ISCCM, "Retrospective study (Indian J Crit Care Med 2024;28(8):741–747)",
        "A Study of Paraquat Poisoning Presentation, Severity, Management and Outcome in a Tertiary Care Hospital: Is There a Silver Lining in the Dark Clouds?",
        "2024",
        "https://doi.org/10.5005/jp-journals-10071-24764",
        status = "Goyal P et al.; Ludhiana, Punjab; 109 patients 2022–2023; retrospective, small survivor group",
    ),
    Source(
        "tx-paraquat-isfahani-hp-2019", Authority.Paper, "Systematic review with meta-analysis (J Res Med Sci 2019;24:2)",
        "Does hemoperfusion in combination with other treatments reduce the mortality of patients with paraquat poisoning more than hemoperfusion alone: A systematic review with meta-analysis",
        "2019",
        "https://doi.org/10.4103/jrms.JRMS_478_18",
        status = "Nasr Isfahani S et al.; 12 studies",
    ),
    Source(
        "tx-paraquat-yang-annmed-2026", Authority.Paper, "Narrative review (Ann Med 2026;58(1):2621498)",
        "Extracorporeal therapies in the management of paraquat poisoning: a comprehensive review of current evidence", "2026",
        "https://doi.org/10.1080/07853890.2026.2621498",
        status = "Yang B et al.; overall certainty described as low",
    ),
)

private const val SUK = "tx-paraquat-sukumar-ijccm-2019"
private const val GB = "tx-paraquat-gawarammana-bjcp-2011"
private const val COCH = "tx-paraquat-cochrane-cd008084-2021"
private const val RCT = "tx-paraquat-gawarammana-rct-2018"
private const val EDD = "tx-paraquat-eddleston-qjm-2003"
private const val SEN = "tx-paraquat-senarathna-qjm-2009"
private const val KOH = "tx-paraquat-koh-toxrep-2014"
private const val GOY = "tx-paraquat-goyal-ijccm-2024"
private const val HPMA = "tx-paraquat-isfahani-hp-2019"
private const val YANG = "tx-paraquat-yang-annmed-2026"

internal val paraquatProtocols: List<Protocol> = listOf(
    Protocol(
        id = "paraquat",
        category = ProtocolCategory.Poisoning,
        title = "Paraquat poisoning",
        summary = "Severity by amount ingested, urine dithionite test, plasma level and time prognosis, decontamination, " +
            "no specific antidote, immunosuppression and extracorporeal evidence, palliative care — as reported by ISCCM-published " +
            "reviews, a Cochrane review and trials.",
        keywords = listOf(
            "paraquat", "Gramoxone", "weedicide", "herbicide", "grass killer", "grass poison", "weed killer", "kharpatvar nashak",
            "dipyridyl", "bipyridyl herbicide", "paraquat tongue", "lung fibrosis", "pulmonary fibrosis", "dithionite",
            "urine dithionite test", "Proudfoot nomogram", "haemoperfusion", "hemoperfusion", "Fuller's earth",
            "cyclophosphamide methylprednisolone", "poisoning", "self-poisoning", "pesticide",
        ),
        steps = listOf(
            // 1 ------------------------------------------------------------------------------------
            Step(
                title = "Recognise severity by amount ingested",
                timing = "At first contact; ask amount, time of ingestion and product",
                points = listOf(
                    "ISCCM review (Sukumar 2019): mild/subacute poisoning (<20 mg/kg) is asymptomatic or mild GI upset, with complete recovery.",
                    "Sukumar 2019: moderate to severe acute poisoning (20–40 mg/kg) — vomiting, then diarrhoea, abdominal pain, oral ulcers; renal and hepatic failure over days; alveolitis and fibrosis over weeks; death may occur within 2–3 weeks.",
                    "Sukumar 2019: fulminant poisoning (>40 mg/kg) — GI ulceration, pancreatitis, myocarditis, refractory hypotension or coma; death within 1–4 days from multi-organ failure.",
                    "Gawarammana & Buckley 2011: gastrointestinal injury occurs in all concentrate ingesters; smaller ingestions cause renal and lung injury over 2–6 days, with fibrosis and late death.",
                    "Goyal 2024 (Ludhiana, Punjab): mortality 88% (96/109); estimated ingested amount was not significantly associated with death.",
                ),
                citations = listOf(
                    cite(SUK, "Clinical presentation, Table 1"),
                    cite(GB, "Clinical course by dose"),
                    cite(GOY, "Results: mortality and ingested amount"),
                ),
            ),
            // 2 ------------------------------------------------------------------------------------
            Step(
                title = "Local corrosive injury",
                timing = "Within days of ingestion",
                points = listOf(
                    "Gawarammana & Buckley 2011: oral and tongue lesions ('paraquat tongue') appear within days and are of little prognostic significance.",
                    "Gawarammana & Buckley 2011: pharyngeal, oesophageal and gastric mucosal lesions are very common and 'much more sinister'; they can perforate.",
                    "Sukumar 2019: GI ulceration, pancreatitis and myocarditis feature in fulminant poisoning.",
                    "Gawarammana & Buckley 2011: gastric lavage is not recommended because it is contraindicated in caustic injury.",
                ),
                checklistTitle = "Signs reported (tick as found)",
                checklist = listOf(
                    "Oral or tongue ulceration (paraquat tongue)",
                    "Painful swallowing, retrosternal or abdominal pain",
                    "Persistent vomiting",
                    "Burning skin sensation (reported as predicting poor outcome, Gawarammana & Buckley 2011)",
                ),
                citations = listOf(
                    cite(GB, "Local corrosive effects; Decontamination"),
                    cite(SUK, "Clinical presentation, Table 1"),
                ),
            ),
            // 3 ------------------------------------------------------------------------------------
            Step(
                title = "Confirm exposure — urine sodium dithionite test",
                timing = "Bedside, on arrival",
                points = listOf(
                    "Sukumar 2019: in alkaline medium dithionite reduces paraquat to a blue radical when urine paraquat exceeds 1 mg/L (or plasma exceeds 2 mg/L); a qualitative bedside test.",
                    "Gawarammana & Buckley 2011: a blue colour indicates systemic toxicity and a very poor prognosis; if negative, repeat within 24 h and confirm with a plasma level.",
                    "Eddleston 2003 (systematic review): validity of the urine test for prognosis is questionable because urine concentration depends on renal function.",
                ),
                citations = listOf(
                    cite(SUK, "Diagnosis"),
                    cite(GB, "Urine dithionite test; Table 1"),
                    cite(EDD, "Discussion: urine dithionite test"),
                ),
            ),
            // 4 ------------------------------------------------------------------------------------
            Step(
                title = "Prognosis — plasma level and time, adverse signs",
                timing = "Plasma level reported against time since ingestion (4–24 h for the Proudfoot method)",
                points = listOf(
                    "Eddleston 2003: Proudfoot nomogram — survivors had plasma paraquat not exceeding 2.0, 0.6, 0.3, 0.16 and 0.1 µg/mL at 4, 6, 10, 16 and 24 h after ingestion; requires a plasma assay.",
                    "Senarathna 2009 (451 Sri Lankan patients): five published plasma-concentration methods performed similarly within their ranges; all predicted death better than survival.",
                    "Gawarammana & Buckley 2011: adverse signs include early hypotension, hypoxia, acidosis, low GCS, renal failure, chest radiograph changes and GI lesions.",
                    "Sukumar 2019: SIPP score = time from ingestion to intensive treatment × admission plasma paraquat; no cut-offs given in the review.",
                    "Goyal 2024: delay to presentation had the strongest association with death (median 14 h in survivors vs 48 h in non-survivors; OR 1.08 per hour); acidosis, renal failure and hepatic dysfunction at presentation were also associated with death.",
                ),
                citations = listOf(
                    cite(EDD, "Discussion: Proudfoot nomogram"),
                    cite(SEN, "Abstract: results"),
                    cite(GB, "Prognostic markers"),
                    cite(SUK, "Diagnosis: SIPP score"),
                    cite(GOY, "Results: time to presentation"),
                ),
            ),
            // 5 ------------------------------------------------------------------------------------
            Step(
                title = "Decontamination",
                timing = "Activated charcoal or Fuller's earth within 2–4 h (Gawarammana & Buckley 2011)",
                points = listOf(
                    "Sukumar 2019: if conscious and not vomiting, consider activated charcoal or Fuller's earth at 1–2 g/kg; lavage without an adsorbent should be avoided; cathartics have no definite indication.",
                    "Gawarammana & Buckley 2011: activated charcoal or Fuller's earth for consenting patients with a protected airway, within 2–4 h; gastric lavage not recommended.",
                    "Eddleston 2003: measures to reduce absorption (lavage, Fuller's earth, bentonite, charcoal) lacked proof of improved survival and rested on case reports or small series.",
                    "Sukumar 2019: forced diuresis is not recommended.",
                ),
                citations = listOf(
                    cite(SUK, "Treatment: Initial management in ICU, Decontamination; Haemodialysis"),
                    cite(GB, "Decontamination; Table 1"),
                    cite(EDD, "Discussion: decontamination and elimination"),
                ),
            ),
            // 6 ------------------------------------------------------------------------------------
            Step(
                title = "No specific antidote — oxygen, fluids and organ support",
                timing = "Renal failure usually develops within the first 24 h (Gawarammana & Buckley 2011)",
                points = listOf(
                    "Sukumar 2019 and Goyal 2024: there is no antidote; management is supportive.",
                    "Gawarammana & Buckley 2011: mild to moderate hypoxia should not be treated routinely with oxygen because it worsens oxidative stress; oxygen is reserved for palliative use.",
                    "Sukumar 2019: supplemental oxygen is not advocated because oxygen free-radical injury may worsen alveolar infiltrates; the review notes no clinical data supporting limited FiO₂ specifically.",
                    "Gawarammana & Buckley 2011: for hypotension, boluses of 15–20 mL/kg; daily fluid balance; creatinine and cystatin C in the first day to identify the renal-failure group.",
                    "Sukumar 2019: rehydration with IV fluids with monitoring for overload and electrolyte imbalance.",
                ),
                citations = listOf(
                    cite(SUK, "Initial management in ICU: Oxygen; Haemodialysis"),
                    cite(GB, "Oxygen; Renal function and fluids; Resuscitation"),
                    cite(GOY, "Conclusions"),
                ),
            ),
            // 7 ------------------------------------------------------------------------------------
            Step(
                title = "Immunosuppression — evidence status",
                points = listOf(
                    "Cochrane 2021 (4 RCTs, 463 participants; searches to Sept 2020): glucocorticoid + cyclophosphamide may slightly reduce in-hospital death, RR 0.82 (95% CI 0.68–0.99; 2 studies, 322 participants; I² 77%; GRADE low).",
                    "Cochrane 2021: death at 3 months after discharge RR 0.98 (0.85–1.13; 1 study, 293 participants; low); infection evidence very low certainty.",
                    "Gawarammana 2018 (placebo-controlled, 299 patients): in-hospital mortality 53% with immunosuppression vs 62% with placebo, not significant; 3-month mortality 69% vs 71%; no evidence of survival benefit.",
                    "Sukumar 2019, describing the same trial: cyclophosphamide 750–1,000 mg over 2 days plus methylprednisolone 1 g over 3 days, then oral dexamethasone 8 mg three times daily for 14 days.",
                    "Koh 2014 (non-randomised, historical controls): overall survival 52% vs 46%, not significant; a subgroup with eGFR <50 and leucocytosis had survival 35.3% vs 5.6%.",
                    "Goyal 2024 (Ludhiana): steroids, cyclophosphamide, N-acetylcysteine and vitamin C showed no significant association with mortality.",
                ),
                citations = listOf(
                    cite(COCH, "Abstract: Main results; Authors' conclusions"),
                    cite(RCT, "Abstract: results and conclusions"),
                    cite(SUK, "Treatment: Immunosuppressants"),
                    cite(KOH, "Abstract: results"),
                    cite(GOY, "Results: adjuvants"),
                ),
            ),
            // 8 ------------------------------------------------------------------------------------
            Step(
                title = "Haemoperfusion and haemodialysis — evidence status",
                timing = "Haemoperfusion described as ideally within 4 h (Sukumar 2019)",
                points = listOf(
                    "Sukumar 2019: haemoperfusion is the earliest removal modality, ideally within 4 h; one proposal is up to seven sessions of 6–8 h until plasma paraquat <0.2 mg/L; not useful after a lethal dose.",
                    "Nasr Isfahani 2019 (meta-analysis, 12 studies): haemoperfusion vs conventional therapy OR 0.20 (0.11–0.40) for death; haemoperfusion with other treatments gave lower mortality than haemoperfusion alone.",
                    "Yang 2026 (narrative review): overall certainty low; some studies found no survival link for haemoperfusion within 5 h; haemodialysis meta-analysis OR 2.84 (1.22–6.64) for death, with confounding by indication.",
                    "Gawarammana & Buckley 2011: tissue levels stay high despite high clearance; case fatality remains above 50% in centres using these techniques; dialysis may be considered for symptomatic renal failure but is unlikely to change outcome.",
                    "Goyal 2024 (Ludhiana): of 12 patients presenting within 4 h, 4 survived with haemoperfusion; not significant in regression (p = 0.094); renal replacement therapy was not associated with mortality.",
                ),
                citations = listOf(
                    cite(SUK, "Treatment: Haemoperfusion and haemodialysis"),
                    cite(HPMA, "Abstract: results and conclusions"),
                    cite(YANG, "Key conclusions: HP, CRRT, HD"),
                    cite(GB, "Haemoperfusion and haemodialysis"),
                    cite(GOY, "Results: haemoperfusion"),
                ),
            ),
            // 9 ------------------------------------------------------------------------------------
            Step(
                title = "Supportive and palliative care",
                points = listOf(
                    "Gawarammana & Buckley 2011: patients in extremis have no realistic hope of recovery, and care should be palliative once the diagnosis is made; intubation and ventilation are considered futile in severe cases.",
                    "Gawarammana & Buckley 2011: two management philosophies exist — minimal intervention, or aggressive treatment with haemoperfusion/dialysis and immunosuppression.",
                    "Goyal 2024: mortality 88% in a Punjab tertiary centre; the authors call for early treatment and attention to the mental health of at-risk patients.",
                ),
                citations = listOf(
                    cite(GB, "Supportive care and palliation; Conclusions"),
                    cite(GOY, "Conclusions"),
                ),
            ),
        ),
        notes = listOf(
            ProtocolNote(
                "Immunosuppression evidence conflicts: the Cochrane review (low certainty) reports a possible slight reduction in in-hospital death, while the largest placebo-controlled trial (Sri Lanka, 299 patients) found no survival benefit. The Cochrane main analysis excluded studies without baseline plasma paraquat testing and had high heterogeneity.",
                listOf(
                    cite(COCH, "Abstract: Main results"),
                    cite(RCT, "Abstract: results"),
                ),
            ),
            ProtocolNote(
                "The Sri Lankan trial was partly funded by the paraquat manufacturer, and some review authors disclose manufacturer ties; readers may wish to weigh these disclosures.",
                listOf(
                    cite(RCT, "Source status"),
                    cite(GB, "Competing interests"),
                ),
            ),
            ProtocolNote(
                "Dexamethasone dosing is reported differently: Sukumar 2019 gives 8 mg three times daily for 14 days for the Sri Lankan trial, whereas the Cochrane review lists 8 mg daily for 14 days. Consult the trial protocol before relying on either.",
                listOf(
                    cite(SUK, "Treatment: Immunosuppressants"),
                    cite(COCH, "Abstract: Regimens"),
                ),
            ),
            ProtocolNote(
                "India vs international: no ICMR Standard Treatment Workflow or MoHFW document specific to paraquat was identified. Indian guidance here comes from ISCCM-published reviews and a Punjab hospital series; the plasma-level nomograms and trials come from Sri Lanka, Taiwan, Iran, Malaysia and China, where plasma assays are available.",
                listOf(
                    cite(SUK, "Clinical presentation; Treatment"),
                    cite(GOY, "Design and conclusions"),
                    cite(SEN, "Abstract"),
                ),
            ),
            ProtocolNote(
                "Plasma-level prognostic methods: Eddleston 2003 states none had been prospectively validated in a large cohort; Senarathna 2009 later found five methods performed comparably within their ranges.",
                listOf(
                    cite(EDD, "Discussion: prognostic indicators"),
                    cite(SEN, "Abstract: results"),
                ),
            ),
        ),
        citations = listOf(
            cite(SUK, "Whole review"),
            cite(GB, "Whole review"),
            cite(COCH, "Whole review"),
        ),
    ),
)
