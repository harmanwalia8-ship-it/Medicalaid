package com.medicalaid.app.tools

import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

/** Kidney function estimates for adults. Serum creatinine in mg/dL (standardised, IDMS-traceable). */
object Renal {
    /** NKF: µmol/L ÷ 88.4 = mg/dL. */
    const val UMOL_PER_MG_DL = 88.4

    enum class Sex { Female, Male }

    /**
     * CKD-EPI creatinine equation (2021, without race), per the National Kidney Foundation:
     * eGFR = 142 × min(Scr/κ, 1)^α × max(Scr/κ, 1)^−1.200 × 0.9938^Age × 1.012 [if female]
     * κ = 0.7 (F) / 0.9 (M); α = −0.241 (F) / −0.302 (M). Result in mL/min/1.73 m².
     */
    fun ckdEpi2021(scrMgDl: Double, age: Int, sex: Sex): Double {
        val kappa = if (sex == Sex.Female) 0.7 else 0.9
        val alpha = if (sex == Sex.Female) -0.241 else -0.302
        val ratio = scrMgDl / kappa
        val female = if (sex == Sex.Female) 1.012 else 1.0
        return 142.0 * min(ratio, 1.0).pow(alpha) * max(ratio, 1.0).pow(-1.200) * 0.9938.pow(age) * female
    }

    /** Cockcroft–Gault creatinine clearance (mL/min): (140 − age) × weight / (72 × Scr), × 0.85 if female. */
    fun cockcroftGault(scrMgDl: Double, age: Int, weightKg: Double, sex: Sex): Double {
        val base = (140 - age) * weightKg / (72.0 * scrMgDl)
        return if (sex == Sex.Female) base * 0.85 else base
    }

    /** Mosteller body surface area (m²) = √(height cm × weight kg / 3600). */
    fun bsaMosteller(heightCm: Double, weightKg: Double): Double = sqrt(heightCm * weightKg / 3600.0)

    /** FDA (2024): for dosing, convert eGFR to mL/min — multiply by the patient's BSA and divide by 1.73. */
    fun nonIndexedEgfr(egfr: Double, bsa: Double): Double = egfr * bsa / 1.73

    /** KDIGO GFR categories (mL/min/1.73 m²). */
    enum class GfrCategory(val code: String, val label: String) {
        G1("G1", "Normal or high (≥90)"),
        G2("G2", "Mildly decreased (60–89)"),
        G3a("G3a", "Mildly to moderately decreased (45–59)"),
        G3b("G3b", "Moderately to severely decreased (30–44)"),
        G4("G4", "Severely decreased (15–29)"),
        G5("G5", "Kidney failure (<15)"),
    }

    fun category(egfr: Double): GfrCategory = when {
        egfr >= 90 -> GfrCategory.G1
        egfr >= 60 -> GfrCategory.G2
        egfr >= 45 -> GfrCategory.G3a
        egfr >= 30 -> GfrCategory.G3b
        egfr >= 15 -> GfrCategory.G4
        else -> GfrCategory.G5
    }
}
