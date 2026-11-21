package com.medicalaid.app.tools

/**
 * Burns: total body surface area (Lund–Browder chart, age-adjusted) and fluid resuscitation formulas.
 * Percentages per region are whole-region values. Sources (ToolSources.kt):
 * - Region values per age column and the adult anterior/posterior split: JTS Burn Care CPG 2025,
 *   Appendix B (adult: one side anterior/posterior) and Appendix C (birth–1 y, 1–4, 5–9, 10–14, 15 y).
 *   Hands are 2.5% each (1.25% per side), so every column sums to 100%.
 * - Adults: 4 and 2 mL/kg/%TBSA starting rates: ABA 2024 burn shock resuscitation guideline, Question 3
 *   (adults with burns ≥20% TBSA only).
 * - Children: 3 mL/kg/%TBSA plus D5LR maintenance by the 4-2-1 rule (JTS CPG, Considerations for pediatric patients,
 *   p. 18); Ringer's lactate 2 mL/kg/%TBSA plus N/2 DNS or Isolyte P at 4-2-1 mL/kg/h (MoHFW 2025 STG, section E, p. 32).
 * - Formula IV resuscitation above 15% TBSA in adults and from 10% in children: MoHFW 2025 STG, section E, p. 30.
 * - Half of the 24 h volume in the first 8 h, the rest over the next 16 h: MoHFW 2025 burns STG, section E;
 *   hours are counted from the injury, not from arrival (JTS CPG, Appendix D).
 * - Over 250 mL/kg in the first 24 h: high risk of compartment syndromes (JTS CPG, Acute resuscitation, p. 9).
 */
object Burns {
    /** Age columns of the Lund–Browder chart (JTS Appendix C: birth–1 yr, 1–4, 5–9, 10–14, 15 years; Appendix B: adult). */
    enum class AgeBand(val label: String) {
        Infant("<1 y"), OneToFour("1–4 y"), FiveToNine("5–9 y"), TenToFourteen("10–14 y"), Fifteen("15 y"), Adult("Adult"),
    }

    enum class Side { Front, Back }

    /** A tappable region of the body chart. [pctByAge] is the % TBSA of the WHOLE region per age band. */
    data class Region(
        val id: String,
        val label: String,
        val pctByAge: List<Double>,
        /** Which views show this region, and the share of the region each view represents (JTS Appendix B, one side anterior/posterior). */
        val shares: Map<Side, Double>,
    ) {
        fun pct(age: AgeBand): Double = pctByAge[age.ordinal]
    }

    private fun same(v: Double) = List(6) { v }
    private val bothHalves = mapOf(Side.Front to 0.5, Side.Back to 0.5)

    val regions: List<Region> = listOf(
        Region("head", "Head", listOf(19.0, 17.0, 13.0, 11.0, 9.0, 7.0), bothHalves),
        Region("neck", "Neck", same(2.0), bothHalves),
        Region("ant-trunk", "Anterior trunk", same(13.0), mapOf(Side.Front to 1.0)),
        Region("post-trunk", "Posterior trunk", same(13.0), mapOf(Side.Back to 1.0)),
        Region("r-buttock", "Right buttock", same(2.5), mapOf(Side.Back to 1.0)),
        Region("l-buttock", "Left buttock", same(2.5), mapOf(Side.Back to 1.0)),
        Region("genitalia", "Genitalia", same(1.0), mapOf(Side.Front to 1.0)),
        Region("r-upper-arm", "Right upper arm", same(4.0), bothHalves),
        Region("l-upper-arm", "Left upper arm", same(4.0), bothHalves),
        Region("r-lower-arm", "Right lower arm", same(3.0), bothHalves),
        Region("l-lower-arm", "Left lower arm", same(3.0), bothHalves),
        Region("r-hand", "Right hand", same(2.5), bothHalves),
        Region("l-hand", "Left hand", same(2.5), bothHalves),
        Region("r-thigh", "Right thigh", listOf(5.5, 6.5, 8.0, 8.5, 9.0, 9.5), bothHalves),
        Region("l-thigh", "Left thigh", listOf(5.5, 6.5, 8.0, 8.5, 9.0, 9.5), bothHalves),
        Region("r-leg", "Right lower leg", listOf(5.0, 5.0, 5.5, 6.0, 6.5, 7.0), bothHalves),
        Region("l-leg", "Left lower leg", listOf(5.0, 5.0, 5.5, 6.0, 6.5, 7.0), bothHalves),
        Region("r-foot", "Right foot", same(3.5), bothHalves),
        Region("l-foot", "Left foot", same(3.5), bothHalves),
    )

    /** Marked fraction of a region's view (0, 0.5 or 1) keyed by "regionId/side". */
    fun key(regionId: String, side: Side) = "$regionId/${side.name}"

    fun tbsa(age: AgeBand, marks: Map<String, Double>): Double = regions.sumOf { r ->
        r.shares.entries.sumOf { (side, share) -> r.pct(age) * share * (marks[key(r.id, side)] ?: 0.0) }
    }

    /** Fluid plan for the first 24 h from the time of the burn. */
    data class FluidPlan(
        val mlPerKgPerPct: Double,
        val total24hMl: Double,
        val first8hMl: Double,
        val next16hMl: Double,
        /** Rate that delivers [first8hMl] by 8 h after the burn only if the infusion starts at the time of the burn. */
        val first8hRateMlPerH: Double,
        val next16hRateMlPerH: Double,
        /** 24 h resuscitation volume per kg (= rate × %TBSA). */
        val mlPerKg24h: Double,
    ) {
        /** JTS CPG p. 9: over 250 mL/kg in the first 24 h → increased risk of abdominal and extremity compartment syndromes. */
        val exceedsJtsHighRisk: Boolean get() = mlPerKg24h > Burns.JTS_HIGH_RISK_ML_PER_KG_24H
    }

    /** total = rate × weight × %TBSA; half in the first 8 h from the burn, half over the next 16 h. */
    fun fluids(mlPerKgPerPct: Double, weightKg: Double, tbsaPct: Double): FluidPlan {
        val total = mlPerKgPerPct * weightKg * tbsaPct
        val half = total / 2
        return FluidPlan(mlPerKgPerPct, total, half, half, half / 8, half / 16, mlPerKgPerPct * tbsaPct)
    }

    /** Parkland (Baxter) starting rate: 4 mL/kg/%TBSA (ABA 2024, Question 3 comparator). Adults only. */
    const val PARKLAND_ML = 4.0
    /** ABA 2024 recommended starting rate for adults with burns ≥20% TBSA: 2 mL/kg/%TBSA (Question 3). */
    const val ABLS_ADULT_ML = 2.0
    /** ABA 2024 population: adults with burns ≥20% TBSA. */
    const val ABA_MIN_TBSA_PCT = 20.0
    /** Children: 3 × weight (kg) × %TBSA in the first 24 h, half in the first 8 h (JTS CPG, p. 18). */
    const val JTS_CHILD_ML = 3.0
    /** Children: Ringer's lactate 2 mL/kg/%TBSA in the first 24 h, with dextrose maintenance (MoHFW 2025, section E, p. 32). */
    const val MOHFW_CHILD_RL_ML = 2.0
    /** MoHFW 2025 section E (p. 30): formula IV resuscitation for burns MORE than 15% TBSA in adults. */
    const val MOHFW_ADULT_IV_ABOVE_PCT = 15.0
    /** MoHFW 2025 section E (p. 30): formula IV resuscitation for burns of 10% TBSA OR MORE in children. */
    const val MOHFW_CHILD_IV_FROM_PCT = 10.0
    /** JTS CPG p. 9: 24 h volume above which compartment-syndrome risk is high (all infused fluids count, worksheet p. 32). */
    const val JTS_HIGH_RISK_ML_PER_KG_24H = 250.0
    /** JTS CPG p. 18: children under 10% TBSA may be given 1.5 × the 4-2-1 maintenance rate instead of a calculated resuscitation. */
    const val JTS_SMALL_BURN_CHILD_MAINTENANCE_FACTOR = 1.5

    /** Every Lund–Browder column except the adult one uses the paediatric fluid plans. */
    fun isChild(age: AgeBand): Boolean = age != AgeBand.Adult

    /** MoHFW 2025 section E: IV formula above 15% TBSA in adults, ≥10% in children. */
    fun formulaIndicated(age: AgeBand, tbsaPct: Double): Boolean =
        if (isChild(age)) tbsaPct >= MOHFW_CHILD_IV_FROM_PCT else tbsaPct > MOHFW_ADULT_IV_ABOVE_PCT

    /**
     * Maintenance by the 4-2-1 rule (mL/h): 4 mL/kg/h for the first 10 kg + 2 mL/kg/h for the next 10 kg + 1 mL/kg/h
     * for every kg above 20 kg (JTS CPG p. 18: D5LR for children <13 y, not titrated; MoHFW 2025 p. 32: N/2 DNS or Isolyte P).
     */
    fun maintenance421MlPerH(weightKg: Double): Double =
        4.0 * minOf(weightKg, 10.0) +
            2.0 * (minOf(weightKg, 20.0) - 10.0).coerceAtLeast(0.0) +
            1.0 * (weightKg - 20.0).coerceAtLeast(0.0)

    /** A starting resuscitation regimen shown as a card. */
    data class Regimen(val name: String, val mlPerKgPerPct: Double)

    /**
     * Regimens to show for this age column and burn size: none below the MoHFW threshold; JTS and MoHFW paediatric
     * formulas for every child column; Parkland for adults, plus ABA 2024 2 mL/kg/%TBSA from 20% TBSA.
     */
    fun regimens(age: AgeBand, tbsaPct: Double): List<Regimen> = when {
        !formulaIndicated(age, tbsaPct) -> emptyList()
        isChild(age) -> listOf(
            Regimen("Children · JTS 2025", JTS_CHILD_ML),
            Regimen("Children · MoHFW 2025", MOHFW_CHILD_RL_ML),
        )
        else -> listOfNotNull(
            Regimen("Adults only · Parkland", PARKLAND_ML),
            if (tbsaPct >= ABA_MIN_TBSA_PCT) Regimen("Adults only · ABA 2024 start (≥20% TBSA)", ABLS_ADULT_ML) else null,
        )
    }
}
