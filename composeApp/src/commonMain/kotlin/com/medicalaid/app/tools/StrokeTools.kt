package com.medicalaid.app.tools

/**
 * Acute ischaemic stroke helpers. Values as stated by:
 * - AHA/ASA 2026 AIS guideline §4.6.2 Rec 1 and Table 7 (alteplase; tenecteplase weight bands)
 * - MoHFW 2022 NP-NCD stroke guidelines, Annexure VI (India: tenecteplase 0.2–0.25 mg/kg, max 20 mg, 0–3 h)
 * Shown in the UI with those citations.
 */
object StrokeTools {
    const val ALTEPLASE_MG_PER_KG = 0.9
    const val ALTEPLASE_MAX_MG = 90.0
    const val ALTEPLASE_BOLUS_FRACTION = 0.10
    const val ALTEPLASE_INFUSION_MINUTES = 60

    data class Alteplase(val totalMg: Double, val bolusMg: Double, val infusionMg: Double)

    fun alteplase(weightKg: Double): Alteplase {
        val total = minOf(ALTEPLASE_MG_PER_KG * weightKg, ALTEPLASE_MAX_MG)
        val bolus = total * ALTEPLASE_BOLUS_FRACTION
        return Alteplase(total, bolus, total - bolus)
    }

    /** AHA/ASA 2026 Table 7: tenecteplase 0.25 mg/kg (max 25 mg) given by weight band. */
    fun tenecteplaseAhaBandMg(weightKg: Double): Double = when {
        weightKg < 60 -> 15.0
        weightKg < 70 -> 17.5
        weightKg < 80 -> 20.0
        weightKg < 90 -> 22.5
        else -> 25.0
    }

    /** MoHFW 2022: 0.2–0.25 mg/kg, maximum 20 mg. Returns the (low, high) range in mg. */
    fun tenecteplaseIndiaRangeMg(weightKg: Double): Pair<Double, Double> =
        minOf(0.2 * weightKg, 20.0) to minOf(0.25 * weightKg, 20.0)

    /** A treatment window measured from last known well, with its attributed note. */
    data class Window(val label: String, val fromHours: Double, val toHours: Double, val note: String)

    val windows = listOf(
        Window("IV thrombolysis", 0.0, 4.5, "AHA/ASA 2026, NICE: within 4.5 h. MoHFW: tenecteplase 0–3 h; alteplase up to 4.5 h."),
        Window("Extended IV thrombolysis", 4.5, 9.0, "AHA/ASA 2026: 4.5–9 h may be reasonable with salvageable tissue on automated perfusion imaging."),
        Window("Thrombectomy", 0.0, 6.0, "AHA/ASA 2026: ICA/M1 occlusion, NIHSS ≥6, pre-stroke mRS 0–1, ASPECTS 3–10."),
        Window("Thrombectomy (extended)", 6.0, 24.0, "AHA/ASA 2026, NICE: selected by imaging (e.g. ASPECTS ≥6; salvageable tissue)."),
    )

    enum class WindowState { Open, Closed, NotYet }

    fun state(window: Window, hoursSinceLkw: Double): WindowState = when {
        hoursSinceLkw < window.fromHours -> WindowState.NotYet
        hoursSinceLkw <= window.toHours -> WindowState.Open
        else -> WindowState.Closed
    }
}
