package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source

/**
 * Sources for the burns and acid–base calculators (cited directly by the UI in EmergencyTools.kt).
 * Ids are stable; every constant in tools/Burns.kt and tools/AcidBase.kt is checked against these documents.
 * - Lund–Browder values and front/back split: JTS Burn Care CPG, Appendices B–C (the 1944 original is not online).
 * - JTS Burn Care CPG also: Ringer's lactate or Plasma-Lyte, ≥20% TBSA and the 250 mL/kg risk (pp. 8–9), urine output
 *   targets (p. 10), paediatric formula and D5LR 4-2-1 maintenance (p. 18), hours counted from injury (Appendix D).
 * - Starting rates 2 and 4 mL/kg/%TBSA: ABA 2024 guideline, Question 3 (adults with burns ≥20% TBSA).
 * - IV thresholds, half of the 24 h volume in the first 8 h, paediatric formula, urine output targets: MoHFW 2025 STG, section E.
 * - ABG thresholds, venous reference values, respiratory bands, anion gap, albumin correction, Δ–Δ: Berend et al. NEJM 2014.
 */
internal val toolSources = listOf(
    Source(
        "tool-lund-browder", Authority.JTS, "Clinical practice guideline (US DoD)",
        "Burn Care (CPG ID: 12) — resuscitation, pediatric considerations, and Appendices B–C: Lund–Browder burn estimate " +
            "(after Lund & Browder, Surg Gynecol Obstet 1944;79:352–358)",
        "2025",
        "https://jts.health.mil/assets/docs/cpgs/Burn_Care_CPG_10_June_2025_ID12_v1.3.pdf",
        status = "Published 10 June 2025; supersedes the 11 May 2016 CPG",
    ),
    Source(
        "tool-burns-fluids", Authority.ABA, "Clinical practice guideline — J Burn Care Res 2024;45(3):565–589",
        "American Burn Association Clinical Practice Guidelines on Burn Shock Resuscitation", "2024",
        "https://doi.org/10.1093/jbcr/irad125",
        status = "Published online 5 December 2023; adults with burns ≥20% TBSA, first 48 hours",
    ),
    Source(
        "tool-mohfw-burns-2025", Authority.MoHFW, "Standard Treatment Guidelines (Dte.GHS, Trauma and Burn Division)",
        "Standard Treatment Guidelines for Management of Burns", "2025",
        "https://www.dghs.mohfw.gov.in/uploads/assets/S1Ipab4025VceQpFs7Qa6THwh07NLmggStsg1cDD.pdf",
    ),
    Source(
        "tool-acid-base", Authority.Paper, "N Engl J Med 2014;371:1434–1445 (review)",
        "Physiological approach to assessment of acid–base disturbances (Berend, de Vries, Gans)", "2014",
        "https://doi.org/10.1056/NEJMra1003327",
        status = "Updated online 16 October 2014",
    ),
)
