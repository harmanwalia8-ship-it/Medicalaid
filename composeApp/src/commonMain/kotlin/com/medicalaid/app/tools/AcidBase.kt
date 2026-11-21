package com.medicalaid.app.tools

/**
 * Arterial blood gas interpretation: primary disorder, expected secondary response, anion gap, albumin-corrected
 * anion gap and delta ratio. Every threshold and formula follows Berend, de Vries & Gans, N Engl J Med
 * 2014;371:1434–1445 (ToolSources.kt, "tool-acid-base"): Table 1, Figure 1 and the anion gap and Δ–Δ sections.
 * Units: PaCO2 mm Hg; HCO3, Na, Cl mmol/L; albumin g/dL.
 */
object AcidBase {
    /** Berend Table 1: acidaemia pH < 7.38, alkalaemia pH > 7.42 (reference 7.40 ± 0.02). */
    const val PH_LOW = 7.38
    const val PH_HIGH = 7.42
    /** Berend Table 1: respiratory acidosis PaCO2 > 42, respiratory alkalosis PaCO2 < 38 mm Hg (reference 40 ± 2). */
    const val PCO2_HIGH = 42.0
    const val PCO2_LOW = 38.0
    /** Berend Table 1: metabolic acidosis HCO3 < 22, metabolic alkalosis HCO3 > 26 mmol/L (reference 24 ± 2). */
    const val HCO3_LOW = 22.0
    const val HCO3_HIGH = 26.0
    const val PCO2_NORMAL = 40.0
    const val HCO3_NORMAL = 24.0
    /** Upper reference value of the anion gap used for ΔAG (Berend: "e.g., 12 mmol per liter"; analyser-specific). */
    const val AG_NORMAL = 12.0
    /**
     * Normal albumin for the correction. Berend gives only the factor (2.5 mmol/L per 1 g/dL decrease);
     * 4.0 g/dL is this app's assumption and is shown to the user.
     */
    const val ALBUMIN_NORMAL = 4.0
    /** Berend Figure 1: anion gap rises 2.5 mmol/L for every 1 g/dL fall in albumin (text: approximately 2.3–2.5). */
    const val ALBUMIN_AG_FACTOR = 2.5
    /** Berend: in lactic acidosis the fall in HCO3 is 0.6 times the rise in the anion gap (1:1 in ketoacidosis). */
    const val LACTIC_HCO3_PER_AG = 0.6
    /**
     * Tolerance on the respiratory-disorder HCO3 predictions. Berend gives the slopes without a range; this app
     * allows ±2 mmol/L for the HCO3 reference of 24 ± 2 (Berend Table 1 footnote). Shown to the user.
     */
    const val RESP_HCO3_TOLERANCE = 2.0

    enum class Primary(val label: String) {
        MetabolicAcidosis("Metabolic acidosis"),
        RespiratoryAcidosis("Respiratory acidosis"),
        MetabolicAlkalosis("Metabolic alkalosis"),
        RespiratoryAlkalosis("Respiratory alkalosis"),
        MixedAcidosis("Mixed metabolic and respiratory acidosis"),
        MixedAlkalosis("Mixed metabolic and respiratory alkalosis"),
        NormalPh("pH 7.38–7.42 — normal values do not exclude a mixed disorder"),
        NotClassified("pH abnormal but PaCO₂ and HCO₃ do not meet the criteria for a primary disorder — recheck the values"),
    }

    /** Primary disorder by Berend Table 1 criteria. */
    fun primary(ph: Double, pco2: Double, hco3: Double): Primary = when {
        ph < PH_LOW && pco2 > PCO2_HIGH && hco3 < HCO3_LOW -> Primary.MixedAcidosis
        ph < PH_LOW && hco3 < HCO3_LOW -> Primary.MetabolicAcidosis
        ph < PH_LOW && pco2 > PCO2_HIGH -> Primary.RespiratoryAcidosis
        ph > PH_HIGH && pco2 < PCO2_LOW && hco3 > HCO3_HIGH -> Primary.MixedAlkalosis
        ph > PH_HIGH && hco3 > HCO3_HIGH -> Primary.MetabolicAlkalosis
        ph > PH_HIGH && pco2 < PCO2_LOW -> Primary.RespiratoryAlkalosis
        ph < PH_LOW || ph > PH_HIGH -> Primary.NotClassified
        else -> Primary.NormalPh
    }

    /** Expected range for the secondary response; [value] is its midpoint. */
    data class Expected(val label: String, val low: Double, val high: Double) {
        val value: Double get() = (low + high) / 2
    }

    /** Metabolic acidosis (Winters formula, Berend Table 1): expected PaCO2 = 1.5 × HCO3 + 8 ± 2. */
    fun wintersPco2(hco3: Double): Expected {
        val v = 1.5 * hco3 + 8
        return Expected("Expected PaCO₂ (Winters: 1.5 × HCO₃ + 8 ± 2)", v - 2, v + 2)
    }

    /**
     * Metabolic alkalosis (Berend Table 1): expected PaCO2 = 0.7 × (HCO3 − 24) + 40 ± 2.
     * Berend notes the respiratory response to metabolic alkalosis is difficult to predict.
     */
    fun alkalosisPco2(hco3: Double): Expected {
        val v = 0.7 * (hco3 - HCO3_NORMAL) + PCO2_NORMAL
        return Expected("Expected PaCO₂ (0.7 × (HCO₃ − 24) + 40 ± 2; less predictable)", v - 2, v + 2)
    }

    /**
     * Respiratory acidosis (Berend Table 1): HCO3 rises 1 mmol/L (acute) or 4–5 mmol/L (chronic) for each
     * 10 mm Hg rise in PaCO2 above 40; ± [RESP_HCO3_TOLERANCE].
     */
    fun respAcidosisHco3(pco2: Double, chronic: Boolean): Expected {
        val tens = (pco2 - PCO2_NORMAL) / 10
        val a = HCO3_NORMAL + (if (chronic) 4.0 else 1.0) * tens
        val b = HCO3_NORMAL + (if (chronic) 5.0 else 1.0) * tens
        val label = if (chronic) "Expected HCO₃ (chronic: +4–5 per 10 mm Hg, ±2)" else "Expected HCO₃ (acute: +1 per 10 mm Hg, ±2)"
        return Expected(label, minOf(a, b) - RESP_HCO3_TOLERANCE, maxOf(a, b) + RESP_HCO3_TOLERANCE)
    }

    /**
     * Respiratory alkalosis (Berend Table 1): HCO3 falls 2 mmol/L (acute) or 4–5 mmol/L (chronic) for each
     * 10 mm Hg fall in PaCO2 below 40; ± [RESP_HCO3_TOLERANCE].
     */
    fun respAlkalosisHco3(pco2: Double, chronic: Boolean): Expected {
        val tens = (PCO2_NORMAL - pco2) / 10
        val a = HCO3_NORMAL - (if (chronic) 4.0 else 2.0) * tens
        val b = HCO3_NORMAL - (if (chronic) 5.0 else 2.0) * tens
        val label = if (chronic) "Expected HCO₃ (chronic: −4–5 per 10 mm Hg, ±2)" else "Expected HCO₃ (acute: −2 per 10 mm Hg, ±2)"
        return Expected(label, minOf(a, b) - RESP_HCO3_TOLERANCE, maxOf(a, b) + RESP_HCO3_TOLERANCE)
    }

    /** Anion gap = Na − Cl − HCO3, without potassium (Berend). */
    fun anionGap(na: Double, cl: Double, hco3: Double) = na - (cl + hco3)

    /** Albumin-corrected anion gap: AG + 2.5 × (4.0 − albumin g/dL) (Berend Figure 1 factor; 4.0 g/dL assumed normal). */
    fun correctedAnionGap(ag: Double, albuminGdl: Double) = ag + ALBUMIN_AG_FACTOR * (ALBUMIN_NORMAL - albuminGdl)

    /**
     * Delta ratio ΔAG/ΔHCO3 = (AG − 12) / (24 − HCO3), the reference values Berend uses for Δ–Δ.
     * Null unless there is a high anion gap acidosis (AG > 12 and HCO3 < 24), where Berend applies Δ–Δ.
     */
    fun deltaRatio(ag: Double, hco3: Double): Double? =
        if (hco3 >= HCO3_NORMAL || ag <= AG_NORMAL) null else (ag - AG_NORMAL) / (HCO3_NORMAL - hco3)

    /**
     * Meaning of the delta ratio against Berend's two reference relationships: 1:1 in ketoacidosis and
     * ΔHCO3 = 0.6 × ΔAG (ratio about 1.7) in lactic acidosis. Berend's decision rule uses differences, which the
     * text states: ΔAG − ΔHCO3 (ketoacidosis) or 0.6 × ΔAG − ΔHCO3 (lactic acidosis) within ±5 mmol/L = high anion
     * gap acidosis alone; above +5 = with metabolic alkalosis; below −5 = with normal anion gap acidosis.
     */
    fun deltaRatioMeaning(dr: Double): String = when {
        dr < 1.0 -> "below 1: HCO₃ has fallen more than the anion gap has risen. Berend: a concurrent normal anion gap " +
            "acidosis is diagnosed if ΔAG − ΔHCO₃ (ketoacidosis) or 0.6 × ΔAG − ΔHCO₃ (lactic acidosis) is below −5 mmol/L"
        dr <= 1.0 / LACTIC_HCO3_PER_AG -> "between 1 (ketoacidosis, ΔHCO₃ ≈ ΔAG) and about 1.7 (lactic acidosis, ΔHCO₃ ≈ 0.6 × ΔAG). " +
            "Berend: high anion gap acidosis alone if ΔAG − ΔHCO₃ (ketoacidosis) or 0.6 × ΔAG − ΔHCO₃ (lactic acidosis) is within ±5 mmol/L"
        else -> "above about 1.7: the anion gap has risen more than HCO₃ has fallen. Berend: a concurrent metabolic alkalosis " +
            "is diagnosed if ΔAG − ΔHCO₃ (ketoacidosis) or 0.6 × ΔAG − ΔHCO₃ (lactic acidosis) is above +5 mmol/L"
    }
}
