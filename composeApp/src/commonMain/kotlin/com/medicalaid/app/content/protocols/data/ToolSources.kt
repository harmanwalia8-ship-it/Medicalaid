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
 * - ABG normal ranges used as the primary-disorder thresholds (pH 7.35–7.45, PaCO2 35–45 mm Hg, HCO3 22–26 mmol/L),
 *   the pH 7.40 cut-off within the normal range and the comparison of PaCO2 with 40 mm Hg (Romanski method):
 *   StatPearls "Arterial Blood Gas Analysis", section "Results, Reporting, and Critical Findings".
 * - ABG primary-disorder criteria (structure), venous reference values, secondary-response formulas, respiratory bands,
 *   anion gap, albumin correction, Δ–Δ: Berend et al. NEJM 2014.
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
    Source(
        "tool-abg-reference", Authority.Paper, "StatPearls review chapter (NCBI Bookshelf NBK536919; PMID 30725604)",
        "Arterial Blood Gas Analysis: Fundamentals, Interpretation, and Clinical Utility (Castro, Zubair)", "2026",
        "https://www.ncbi.nlm.nih.gov/books/NBK536919/",
        status = "Last updated 5 July 2026; StatPearls Publishing, Treasure Island (FL). Normal ranges and the Romanski-method " +
            "steps (pH 7.40 cut-off, PaCO2 compared with 40 mm Hg) only; the chapter notes that values may vary slightly " +
            "among laboratories and across age groups",
    ),
)
