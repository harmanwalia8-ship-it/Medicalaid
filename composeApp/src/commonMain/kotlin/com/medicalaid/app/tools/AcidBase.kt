package com.medicalaid.app.tools

import kotlin.math.round

/**
 * Arterial blood gas interpretation: primary disorder, secondary response, anion gap, albumin-corrected
 * anion gap and Δ–Δ.
 * - Normal ranges (the primary-disorder thresholds [PH_LOW]…[HCO3_HIGH]): the usual clinical reference ranges from
 *   StatPearls, Castro & Zubair, "Arterial Blood Gas Analysis: Fundamentals, Interpretation, and Clinical Utility",
 *   updated 5 July 2026 (ToolSources.kt, "tool-abg-reference"), section "Results, Reporting, and Critical Findings".
 *   Berend's narrower Table 1 bands (pH 7.38–7.42, PaCO2 38–42 mm Hg) are not used. The same StatPearls section
 *   (Romanski method) gives the pH 7.40 cut-off within the normal range ([PH_CUTOFF], [sideOfCutoff]) and the comparison
 *   of PaCO2 with 40 mm Hg that [primary] uses when neither PaCO2 nor HCO3 is outside its range in the matching direction.
 * - Everything else (structure of the primary-disorder criteria, secondary-response formulas, respiratory bands, anion gap,
 *   albumin correction, Δ–Δ) follows Berend, de Vries & Gans, N Engl J Med 2014;371:1434–1445 (ToolSources.kt,
 *   "tool-acid-base"): Table 1, Figures 1–2 and the anion gap and Δ–Δ sections.
 * Arterial samples only (Berend Table 1 footnote gives different venous reference values).
 * Units: PaCO2 mm Hg; HCO3, Na, Cl mmol/L; albumin g/dL.
 */
object AcidBase {
    /**
     * StatPearls (Results, Reporting, and Critical Findings): "pH (7.35-7.45)"; Romanski method: "acidemia (pH <7.35)
     * or alkalemia (pH >7.45)". So pH 7.35 and 7.45 themselves are within the normal range.
     */
    const val PH_LOW = 7.35
    const val PH_HIGH = 7.45
    /**
     * StatPearls (Romanski method): "If the pH is within the normal range (7.35-7.45), a pH of 7.40 should be used as
     * the cutoff; in other words, clinicians should categorize a pH of 7.37 as acidosis and 7.42 as alkalemia."
     * Used by [sideOfCutoff].
     */
    const val PH_CUTOFF = 7.40
    /**
     * StatPearls (Results, Reporting, and Critical Findings): "PaCO2 (35-45 mm Hg)". This app applies Berend's Table 1
     * structure with these limits: respiratory acidosis needs PaCO2 > 45, respiratory alkalosis PaCO2 < 35 mm Hg.
     * (StatPearls' Romanski step instead compares PaCO2 with 40 mm Hg; [primary] uses that only when neither PaCO2 nor
     * HCO3 is outside its range in the direction that matches the pH.)
     */
    const val PCO2_HIGH = 45.0
    const val PCO2_LOW = 35.0
    /**
     * StatPearls (Results, Reporting, and Critical Findings): "HCO3 (22-26 mEq/L)" (= mmol/L; the same as Berend
     * Table 1, 24 ± 2). Metabolic acidosis needs HCO3 < 22, metabolic alkalosis HCO3 > 26 mmol/L.
     */
    const val HCO3_LOW = 22.0
    const val HCO3_HIGH = 26.0
    /** Berend Table 1 reference PaCO2 (40 mm Hg), the baseline for the secondary-response formulas and bands. */
    const val PCO2_NORMAL = 40.0
    /** Berend Table 1 reference HCO3 (24 mmol/L), the baseline for the secondary-response formulas, bands and ΔHCO3. */
    const val HCO3_NORMAL = 24.0
    /** Upper reference value of the anion gap used for ΔAG (Berend: "e.g., 12 mmol per liter"; analyser-specific). */
    const val AG_NORMAL = 12.0
    /**
     * Normal albumin for the correction. Berend gives only the factor (2.5 mmol/L per 1 g/dL decrease);
     * 4.0 g/dL is this app's assumption and is shown to the user.
     */
    const val ALBUMIN_NORMAL = 4.0
    /**
     * Berend Figure 1: for every 1 g/dL decrease in albumin, increase the calculated anion gap by 2.5 mmol/L
     * (text: approximately 2.3–2.5); hypoalbuminaemia lowers the measured gap. Berend describes decreases only,
     * so no correction is applied above [ALBUMIN_NORMAL].
     */
    const val ALBUMIN_AG_FACTOR = 2.5
    /** Berend: in lactic acidosis the fall in HCO3 is 0.6 times the rise in the anion gap (1:1 in ketoacidosis). */
    const val LACTIC_HCO3_PER_AG = 0.6
    /** Berend Δ–Δ: −5 to +5 mmol/L = high anion gap acidosis alone; above +5 = with metabolic alkalosis; below −5 = with normal anion gap acidosis. */
    const val DELTA_DELTA_LIMIT = 5.0

    enum class Primary(val label: String) {
        MetabolicAcidosis("Metabolic acidosis"),
        RespiratoryAcidosis("Respiratory acidosis"),
        MetabolicAlkalosis("Metabolic alkalosis"),
        RespiratoryAlkalosis("Respiratory alkalosis"),
        MixedAcidosis("Mixed metabolic and respiratory acidosis"),
        MixedAlkalosis("Mixed metabolic and respiratory alkalosis"),
        NormalPh(
            "pH within the normal range 7.35–7.45 (StatPearls). Within this range StatPearls uses 7.40 as the cut-off " +
                "(pH 7.37 counts as acidosis, 7.42 as alkalaemia); when PaCO₂ or HCO₃ is abnormal, the compensation check uses " +
                "that cut-off. Also consider a mixed or chronic respiratory disorder (Berend: pH may be normal in chronic " +
                "respiratory acidosis, and compensation may normalise pH in chronic respiratory alkalosis). If all values are " +
                "normal, a raised anion gap may still reveal a mixed disorder (Berend).",
        ),
        NotClassified(
            "pH outside 7.35–7.45, but neither HCO₃ (22–26 mmol/L) nor PaCO₂ (compared with 40 mm Hg) is on the side that " +
                "explains it (StatPearls: PaCO₂ greater than 40 mm Hg with pH below 7.40 indicates a respiratory acidosis; " +
                "PaCO₂ less than 40 with pH above 7.40, a respiratory alkalosis). Recheck the values.",
        ),
    }

    /**
     * Primary disorder: Berend Table 1 criteria (pH outside the normal range plus the matching abnormal PaCO2 and/or
     * HCO3), applied with the StatPearls normal ranges ([PH_LOW]–[PH_HIGH], [PCO2_LOW]–[PCO2_HIGH], [HCO3_LOW]–[HCO3_HIGH])
     * instead of Berend's narrower bands. Range limits count as normal. When the pH is abnormal but neither PaCO2 nor HCO3
     * is outside its range in the matching direction (values near the limits can together give an abnormal pH, e.g.
     * pH 7.33, PaCO2 45, HCO3 23), PaCO2 is compared with [PCO2_NORMAL] as in StatPearls: "PaCO2 greater than 40 mm Hg
     * with a pH less than 7.4 indicates a respiratory acidosis, whereas PaCO2 less than 40 mm Hg and pH greater than 7.4
     * indicates a respiratory alkalosis."
     * Only a pH that neither rule explains is [Primary.NotClassified].
     */
    fun primary(ph: Double, pco2: Double, hco3: Double): Primary = when {
        ph < PH_LOW && pco2 > PCO2_HIGH && hco3 < HCO3_LOW -> Primary.MixedAcidosis
        ph < PH_LOW && hco3 < HCO3_LOW -> Primary.MetabolicAcidosis
        ph < PH_LOW && pco2 > PCO2_HIGH -> Primary.RespiratoryAcidosis
        ph > PH_HIGH && pco2 < PCO2_LOW && hco3 > HCO3_HIGH -> Primary.MixedAlkalosis
        ph > PH_HIGH && hco3 > HCO3_HIGH -> Primary.MetabolicAlkalosis
        ph > PH_HIGH && pco2 < PCO2_LOW -> Primary.RespiratoryAlkalosis
        ph < PH_LOW && pco2 > PCO2_NORMAL -> Primary.RespiratoryAcidosis
        ph > PH_HIGH && pco2 < PCO2_NORMAL -> Primary.RespiratoryAlkalosis
        ph < PH_LOW || ph > PH_HIGH -> Primary.NotClassified
        else -> Primary.NormalPh
    }

    /**
     * For a [Primary.NormalPh] gas: the disorder on the side of the StatPearls [PH_CUTOFF] (7.40), judged by the same
     * PaCO2 and HCO3 limits as [primary]. Null when the pH is exactly 7.40 or PaCO2 and HCO3 give no matching
     * abnormality. StatPearls example 1 (pH 7.39, PaCO2 51, HCO3 30) and example 2 (pH 7.45, PaCO2 32, HCO3 23) come out
     * as respiratory acidosis and respiratory alkalosis.
     */
    fun sideOfCutoff(ph: Double, pco2: Double, hco3: Double): Primary? = when {
        ph < PH_CUTOFF && pco2 > PCO2_HIGH && hco3 < HCO3_LOW -> Primary.MixedAcidosis
        ph < PH_CUTOFF && hco3 < HCO3_LOW -> Primary.MetabolicAcidosis
        ph < PH_CUTOFF && pco2 > PCO2_HIGH -> Primary.RespiratoryAcidosis
        ph > PH_CUTOFF && pco2 < PCO2_LOW && hco3 > HCO3_HIGH -> Primary.MixedAlkalosis
        ph > PH_CUTOFF && hco3 > HCO3_HIGH -> Primary.MetabolicAlkalosis
        ph > PH_CUTOFF && pco2 < PCO2_LOW -> Primary.RespiratoryAlkalosis
        else -> null
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
     * 10 mm Hg rise in PaCO2 above 40. No tolerance is added: Berend classifies by the bands in [respAcidosisResponse].
     */
    fun respAcidosisHco3(pco2: Double, chronic: Boolean): Expected {
        val tens = (pco2 - PCO2_NORMAL) / 10
        val a = HCO3_NORMAL + (if (chronic) 4.0 else 1.0) * tens
        val b = HCO3_NORMAL + (if (chronic) 5.0 else 1.0) * tens
        val label = if (chronic) "Expected HCO₃ (chronic: +4–5 per 10 mm Hg)" else "Expected HCO₃ (acute: +1 per 10 mm Hg)"
        return Expected(label, minOf(a, b), maxOf(a, b))
    }

    /**
     * Respiratory alkalosis (Berend Table 1): HCO3 falls 2 mmol/L (acute) or 4–5 mmol/L (chronic) for each
     * 10 mm Hg fall in PaCO2 below 40. No tolerance is added: Berend classifies by the bands in [respAlkalosisResponse].
     */
    fun respAlkalosisHco3(pco2: Double, chronic: Boolean): Expected {
        val tens = (PCO2_NORMAL - pco2) / 10
        val a = HCO3_NORMAL - (if (chronic) 4.0 else 2.0) * tens
        val b = HCO3_NORMAL - (if (chronic) 5.0 else 2.0) * tens
        val label = if (chronic) "Expected HCO₃ (chronic: −4–5 per 10 mm Hg)" else "Expected HCO₃ (acute: −2 per 10 mm Hg)"
        return Expected(label, minOf(a, b), maxOf(a, b))
    }

    /** Berend Figures 1 and 2: the metabolic response to a primary respiratory disorder, judged per 10 mm Hg change in PaCO2. */
    enum class RespResponse {
        Acute,
        /** Between the acute and chronic values: Berend's figures give no category. */
        BetweenAcuteAndChronic,
        Chronic,
        AdditionalMetabolicAcidosis,
        AdditionalMetabolicAlkalosis,
    }

    private fun round1(x: Double) = round(x * 10) / 10

    /** Rise in HCO3 (mmol/L, from 24) per 10 mm Hg rise in PaCO2 above 40, to 0.1. Only for PaCO2 > 40. */
    fun respAcidosisRisePer10(pco2: Double, hco3: Double): Double = round1((hco3 - HCO3_NORMAL) / ((pco2 - PCO2_NORMAL) / 10))

    /** Fall in HCO3 (mmol/L, from 24) per 10 mm Hg fall in PaCO2 below 40, to 0.1. Only for PaCO2 < 40. */
    fun respAlkalosisFallPer10(pco2: Double, hco3: Double): Double = round1((HCO3_NORMAL - hco3) / ((PCO2_NORMAL - pco2) / 10))

    /**
     * Berend Figure 1, respiratory acidosis, per 10 mm Hg PaCO2 rise: 1 = acute; < 1 = additional metabolic acidosis;
     * 4–5 = chronic; > 5 = additional metabolic alkalosis.
     */
    fun respAcidosisResponse(pco2: Double, hco3: Double): RespResponse {
        val r = respAcidosisRisePer10(pco2, hco3)
        return when {
            r < 1.0 -> RespResponse.AdditionalMetabolicAcidosis
            r == 1.0 -> RespResponse.Acute
            r < 4.0 -> RespResponse.BetweenAcuteAndChronic
            r <= 5.0 -> RespResponse.Chronic
            else -> RespResponse.AdditionalMetabolicAlkalosis
        }
    }

    /**
     * Berend Figure 2, respiratory alkalosis, per 10 mm Hg PaCO2 fall: 2 = acute; < 2 = additional metabolic alkalosis;
     * 4–5 = chronic; > 5 = additional metabolic acidosis.
     */
    fun respAlkalosisResponse(pco2: Double, hco3: Double): RespResponse {
        val f = respAlkalosisFallPer10(pco2, hco3)
        return when {
            f < 2.0 -> RespResponse.AdditionalMetabolicAlkalosis
            f == 2.0 -> RespResponse.Acute
            f < 4.0 -> RespResponse.BetweenAcuteAndChronic
            f <= 5.0 -> RespResponse.Chronic
            else -> RespResponse.AdditionalMetabolicAcidosis
        }
    }

    /** Verdict text for [respAcidosisResponse] / [respAlkalosisResponse]; [acidosis] selects the Figure 1 or Figure 2 wording. */
    fun respResponseText(r: RespResponse, acidosis: Boolean): String = when (r) {
        RespResponse.Acute -> if (acidosis) "1: acute respiratory acidosis" else "2: acute respiratory alkalosis"
        RespResponse.Chronic -> if (acidosis) "4–5: chronic respiratory acidosis" else "4–5: chronic respiratory alkalosis"
        RespResponse.BetweenAcuteAndChronic ->
            (if (acidosis) "between 1 (acute) and 4–5 (chronic)" else "between 2 (acute) and 4–5 (chronic)") +
                ": not a Berend category; the metabolic response takes 2–5 days to complete"
        RespResponse.AdditionalMetabolicAcidosis ->
            if (acidosis) "less than 1: additional metabolic acidosis" else "more than 5: additional metabolic acidosis"
        RespResponse.AdditionalMetabolicAlkalosis ->
            if (acidosis) "more than 5: additional metabolic alkalosis" else "less than 2: additional metabolic alkalosis"
    }

    /** Anion gap = Na − Cl − HCO3, without potassium (Berend). */
    fun anionGap(na: Double, cl: Double, hco3: Double) = na - (cl + hco3)

    /**
     * Albumin-corrected anion gap: AG + 2.5 × (4.0 − albumin g/dL) when albumin is below 4.0 g/dL (Berend Figure 1
     * factor; 4.0 g/dL assumed normal). Berend describes a correction for decreases only, so AG is unchanged at ≥ 4.0.
     */
    fun correctedAnionGap(ag: Double, albuminGdl: Double) =
        if (albuminGdl < ALBUMIN_NORMAL) ag + ALBUMIN_AG_FACTOR * (ALBUMIN_NORMAL - albuminGdl) else ag

    /**
     * Berend Δ–Δ (Figure 1; text p. 1441): ΔAG = AG − 12 (upper reference value) and ΔHCO3 = 24 − HCO3.
     * [keto] = ΔAG − ΔHCO3 (ketoacidosis, 1:1); [lactic] = 0.6 × ΔAG − ΔHCO3 (lactic acidosis).
     */
    data class DeltaDelta(val deltaAg: Double, val deltaHco3: Double) {
        val keto: Double get() = deltaAg - deltaHco3
        val lactic: Double get() = AcidBase.LACTIC_HCO3_PER_AG * deltaAg - deltaHco3
    }

    /**
     * Δ–Δ for a high anion gap acidosis (AG > 12 and HCO3 < 24), where Berend applies it. Null otherwise: with HCO3 ≥ 24
     * the differences reduce to the rise in the anion gap, which metabolic alkalosis alone can raise by about
     * 4–6 mmol/L (Berend, patient 3); [highAnionGapNotes] covers that case.
     */
    fun deltaDelta(ag: Double, hco3: Double): DeltaDelta? =
        if (hco3 >= HCO3_NORMAL || ag <= AG_NORMAL) null else DeltaDelta(ag - AG_NORMAL, HCO3_NORMAL - hco3)

    /** Berend Figure 1: −5 to +5 = high anion gap acidosis alone; > +5 = with metabolic alkalosis; < −5 = with normal anion gap acidosis. */
    fun deltaDeltaMeaning(d: Double): String = when {
        d > DELTA_DELTA_LIMIT -> "above +5: high anion gap acidosis with metabolic alkalosis"
        d < -DELTA_DELTA_LIMIT -> "below −5: high anion gap acidosis with normal anion gap acidosis"
        else -> "−5 to +5: high anion gap acidosis alone"
    }

    /** Berend's notes on a raised anion gap (AG above 12); empty when AG ≤ 12. */
    fun highAnionGapNotes(ag: Double, hco3: Double): List<String> {
        if (ag <= AG_NORMAL) return emptyList()
        val rise = ag - AG_NORMAL
        val notes = mutableListOf<String>()
        if (rise > 10) notes += "More than 10 mmol/L above the upper reference value: highly suggestive of organic acidosis (Berend, Table 2)."
        if (rise > 5) {
            notes += "Berend: when pH, PaCO₂ and HCO₃ are normal, an anion gap increase of more than 5 mmol/L may be the only clue to a mixed acid–base disorder."
        } else {
            notes += "Berend: a minor increase in the anion gap is less helpful in diagnosing metabolic acidosis."
        }
        if (hco3 >= HCO3_NORMAL) {
            notes += "HCO₃ is not low, so Δ–Δ is not calculated: a high anion gap acidosis may be masked by metabolic alkalosis, " +
                "but metabolic alkalosis alone can raise the anion gap by about 4–6 mmol/L (Berend, patient 3)."
        }
        return notes
    }
}
