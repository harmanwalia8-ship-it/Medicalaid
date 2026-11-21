package com.medicalaid.app.tools

/**
 * Burns: total body surface area (Lund–Browder chart, age-adjusted) and fluid resuscitation formulas.
 * Percentages per region are whole-region values; the chart splits each region into front and back halves.
 * Constants are checked against the cited sources (see ToolSources.kt).
 */
object Burns {
    /** Age columns of the Lund–Browder chart. */
    enum class AgeBand(val label: String) {
        Infant("<1 y"), OneToFour("1–4 y"), FiveToNine("5–9 y"), TenToFourteen("10–14 y"), Fifteen("15 y"), Adult("Adult"),
    }

    enum class Side { Front, Back }

    /** A tappable region of the body chart. [pctByAge] is the % TBSA of the WHOLE region per age band. */
    data class Region(
        val id: String,
        val label: String,
        val pctByAge: List<Double>,
        /** Which views show this region, and the share of the region each view represents. */
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
        val first8hRateMlPerH: Double,
        val next16hRateMlPerH: Double,
    )

    /** total = rate × weight × %TBSA; half in the first 8 h from the burn, half over the next 16 h. */
    fun fluids(mlPerKgPerPct: Double, weightKg: Double, tbsaPct: Double): FluidPlan {
        val total = mlPerKgPerPct * weightKg * tbsaPct
        val half = total / 2
        return FluidPlan(mlPerKgPerPct, total, half, half, half / 8, half / 16)
    }

    /** Parkland formula: 4 mL/kg/%TBSA. */
    const val PARKLAND_ML = 4.0
    /** ABA consensus / ABLS starting rate for adults with thermal burns: 2 mL/kg/%TBSA. */
    const val ABLS_ADULT_ML = 2.0
}
