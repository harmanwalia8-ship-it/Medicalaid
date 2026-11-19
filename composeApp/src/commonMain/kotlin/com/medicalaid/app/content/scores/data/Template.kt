package com.medicalaid.app.content.scores.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source
import com.medicalaid.app.content.cite
import com.medicalaid.app.content.scores.Band
import com.medicalaid.app.content.scores.Caveat
import com.medicalaid.app.content.scores.Criterion
import com.medicalaid.app.content.scores.Level
import com.medicalaid.app.content.scores.Score
import com.medicalaid.app.content.scores.ScoreCategory

/*
 * TEMPLATE for score data files. Rules:
 * - Criteria, points and cut-offs exactly as the derivation paper / endorsing guideline state them.
 * - Band.meaning is attributed ("Sepsis-3: …", "NICE: …") — the app reports, it does not instruct.
 * - Caveats carry guideline positions on the score itself (e.g. "do not use alone to …").
 * - Sources: original paper (Authority.Paper, DOI url) + guideline(s) with their official URL.
 */

internal val templateScoreSources = listOf(
    Source(
        "sepsis3-2016", Authority.Paper, "JAMA 2016;315(8):801–810",
        "The Third International Consensus Definitions for Sepsis and Septic Shock (Sepsis-3)", "2016",
        "https://doi.org/10.1001/jama.2016.0287",
    ),
    Source(
        "ssc-2021", Authority.SSC, "International Guidelines (Crit Care Med 2021)",
        "Surviving Sepsis Campaign: Management of Sepsis and Septic Shock", "2021",
        "https://doi.org/10.1097/CCM.0000000000005337",
    ),
)

internal val templateScores = listOf(
    Score(
        id = "qsofa",
        name = "qSOFA",
        purpose = "Bedside prompt for adults with suspected infection who are likely to have a poor outcome.",
        category = ScoreCategory.Sepsis,
        keywords = listOf("quick SOFA", "sepsis", "septic shock", "infection"),
        items = listOf(
            Criterion("rr", "Respiratory rate ≥22/min", 1.0),
            Criterion("mentation", "Altered mentation", 1.0, hint = "GCS <15"),
            Criterion("sbp", "Systolic BP ≤100 mm Hg", 1.0),
        ),
        bands = listOf(
            Band(0.0, 1.0, "qSOFA 0–1", "Does not exclude sepsis; reassess if the clinical picture changes.", Level.Low),
            Band(
                2.0, 3.0, "qSOFA ≥2",
                "Sepsis-3: suspected infection with qSOFA ≥2 identifies patients likely to have poor outcomes — assess for organ dysfunction (SOFA), start or escalate therapy and consider critical care referral.",
                Level.High,
            ),
        ),
        caveats = listOf(
            Caveat(
                "Surviving Sepsis Campaign 2021 recommends against using qSOFA, compared with SIRS, NEWS or MEWS, as a single screening tool for sepsis or septic shock.",
                listOf(cite("ssc-2021", "Screening — Recommendation 2")),
            ),
        ),
        citations = listOf(cite("sepsis3-2016", "qSOFA criteria"), cite("ssc-2021")),
    ),
)
