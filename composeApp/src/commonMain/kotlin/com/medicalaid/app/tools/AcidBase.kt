package com.medicalaid.app.tools

/**
 * Arterial blood gas interpretation: primary disorder, expected compensation, anion gap, albumin-corrected
 * anion gap and delta ratio. Formulas are checked against the cited sources (see ToolSources.kt).
 * Units: PaCO2 mm Hg; HCO3, Na, Cl mmol/L; albumin g/dL.
 */
object AcidBase {
    const val PH_LOW = 7.35
    const val PH_HIGH = 7.45
    const val PCO2_NORMAL = 40.0
    const val HCO3_NORMAL = 24.0
    const val AG_NORMAL = 12.0
    const val ALBUMIN_NORMAL = 4.0

    enum class Primary(val label: String) {
        MetabolicAcidosis("Metabolic acidosis"),
        RespiratoryAcidosis("Respiratory acidosis"),
        MetabolicAlkalosis("Metabolic alkalosis"),
        RespiratoryAlkalosis("Respiratory alkalosis"),
        MixedAcidosis("Mixed metabolic and respiratory acidosis"),
        MixedAlkalosis("Mixed metabolic and respiratory alkalosis"),
        NormalPh("pH within 7.35–7.45"),
    }

    fun primary(ph: Double, pco2: Double, hco3: Double): Primary = when {
        ph < PH_LOW && pco2 > 45 && hco3 < 22 -> Primary.MixedAcidosis
        ph < PH_LOW && hco3 < 22 -> Primary.MetabolicAcidosis
        ph < PH_LOW -> Primary.RespiratoryAcidosis
        ph > PH_HIGH && pco2 < 35 && hco3 > 26 -> Primary.MixedAlkalosis
        ph > PH_HIGH && hco3 > 26 -> Primary.MetabolicAlkalosis
        ph > PH_HIGH -> Primary.RespiratoryAlkalosis
        else -> Primary.NormalPh
    }

    /** Expected value with a tolerance (e.g. Winter's ± 2). */
    data class Expected(val label: String, val value: Double, val tolerance: Double) {
        val low get() = value - tolerance
        val high get() = value + tolerance
    }

    /** Winter's formula: expected PaCO2 = 1.5 × HCO3 + 8 (± 2). */
    fun wintersPco2(hco3: Double) = Expected("Expected PaCO₂ (Winter's)", 1.5 * hco3 + 8, 2.0)

    /** Metabolic alkalosis: expected PaCO2 = 0.7 × HCO3 + 21 (± 2). */
    fun alkalosisPco2(hco3: Double) = Expected("Expected PaCO₂", 0.7 * hco3 + 21, 2.0)

    /** Respiratory acidosis: HCO3 rises 1 (acute) or 3.5 (chronic) per 10 mm Hg rise in PaCO2. */
    fun respAcidosisHco3(pco2: Double, chronic: Boolean): Expected {
        val per10 = if (chronic) 3.5 else 1.0
        return Expected(if (chronic) "Expected HCO₃ (chronic)" else "Expected HCO₃ (acute)", HCO3_NORMAL + per10 * (pco2 - PCO2_NORMAL) / 10, 2.0)
    }

    /** Respiratory alkalosis: HCO3 falls 2 (acute) or 5 (chronic) per 10 mm Hg fall in PaCO2. */
    fun respAlkalosisHco3(pco2: Double, chronic: Boolean): Expected {
        val per10 = if (chronic) 5.0 else 2.0
        return Expected(if (chronic) "Expected HCO₃ (chronic)" else "Expected HCO₃ (acute)", HCO3_NORMAL - per10 * (PCO2_NORMAL - pco2) / 10, 2.0)
    }

    fun anionGap(na: Double, cl: Double, hco3: Double) = na - (cl + hco3)

    /** Albumin-corrected anion gap: AG + 2.5 × (4.0 − albumin g/dL). */
    fun correctedAnionGap(ag: Double, albuminGdl: Double) = ag + 2.5 * (ALBUMIN_NORMAL - albuminGdl)

    /** Delta ratio = (AG − 12) / (24 − HCO3). Null when HCO3 ≥ 24. */
    fun deltaRatio(ag: Double, hco3: Double): Double? = if (hco3 >= HCO3_NORMAL) null else (ag - AG_NORMAL) / (HCO3_NORMAL - hco3)

    fun deltaRatioMeaning(dr: Double): String = when {
        dr < 0.4 -> "<0.4: hyperchloraemic normal anion gap acidosis"
        dr < 0.8 -> "0.4–0.8: combined high and normal anion gap acidosis"
        dr <= 2.0 -> "0.8–2: high anion gap acidosis"
        else -> ">2: high anion gap acidosis with concurrent metabolic alkalosis (or pre-existing compensated respiratory acidosis)"
    }
}
