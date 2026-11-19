package com.medicalaid.app.tools

/**
 * ADAG regression (Nathan DM et al., Diabetes Care 2008;31:1473–1478), used by the ADA eAG calculator:
 *   eAG (mg/dL)  = 28.7 × A1C − 46.7
 *   eAG (mmol/L) = 1.59 × A1C − 2.59
 *
 * The equation relates HbA1c to the AVERAGE glucose over ~2–3 months. Inverting it for a single random
 * glucose only gives a rough indication; it is not a substitute for measuring HbA1c and must not be used
 * to diagnose diabetes.
 */
object Glycemia {
    const val MG_DL_PER_MMOL = 18.0

    fun eagMgDl(a1cPercent: Double): Double = 28.7 * a1cPercent - 46.7

    fun eagMmol(a1cPercent: Double): Double = 1.59 * a1cPercent - 2.59

    /** Inverse of the ADAG equation: HbA1c (%) that corresponds to a mean glucose in mg/dL. */
    fun a1cFromMgDl(glucoseMgDl: Double): Double = (glucoseMgDl + 46.7) / 28.7

    fun a1cFromMmol(glucoseMmol: Double): Double = (glucoseMmol + 2.59) / 1.59

    enum class A1cBand(val label: String) {
        Normal("Below the prediabetes range (<5.7%)"),
        Prediabetes("Prediabetes range (5.7–6.4%)"),
        Diabetes("Diabetes range (≥6.5%) — confirm per ADA criteria"),
    }

    /** ICMR Standard Treatment Workflow (type 2 diabetes, 2022): pharmacotherapy by HbA1c. */
    enum class IcmrTherapyBand(val label: String, val detail: String) {
        Monotherapy("HbA1c <8.5% — monotherapy", "Metformin."),
        Dual("HbA1c 8.5–10% — dual therapy", "Metformin + SU / TZD / DPP-4i / SGLT2i / AGI / GLP-1 RA."),
        Intensive("HbA1c >10% — basal insulin or triple therapy", "Basal insulin + metformin + another OAD, or a triple OAD combination."),
    }

    fun icmrTherapyBand(a1cPercent: Double): IcmrTherapyBand = when {
        a1cPercent < 8.5 -> IcmrTherapyBand.Monotherapy
        a1cPercent <= 10.0 -> IcmrTherapyBand.Dual
        else -> IcmrTherapyBand.Intensive
    }

    /** ADA diagnostic bands for a measured HbA1c (non-pregnant adults). */
    fun band(a1cPercent: Double): A1cBand = when {
        a1cPercent < 5.7 -> A1cBand.Normal
        a1cPercent < 6.5 -> A1cBand.Prediabetes
        else -> A1cBand.Diabetes
    }
}
