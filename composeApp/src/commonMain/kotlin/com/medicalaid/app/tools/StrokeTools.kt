package com.medicalaid.app.tools

/**
 * Acute ischaemic stroke helpers. Constants are the values stated by the cited guideline
 * (see the acute-stroke protocol sources); they are shown with that citation in the UI.
 */
object StrokeTools {
    data class Thrombolysis(
        val alteplaseTotalMg: Double,
        val alteplaseBolusMg: Double,
        val alteplaseInfusionMg: Double,
        val tenecteplaseMg: Double,
    )

    const val ALTEPLASE_MG_PER_KG = 0.9
    const val ALTEPLASE_MAX_MG = 90.0
    const val ALTEPLASE_BOLUS_FRACTION = 0.10
    const val ALTEPLASE_INFUSION_MINUTES = 60
    const val TENECTEPLASE_MG_PER_KG = 0.25
    const val TENECTEPLASE_MAX_MG = 25.0

    fun thrombolysis(weightKg: Double): Thrombolysis {
        val total = minOf(ALTEPLASE_MG_PER_KG * weightKg, ALTEPLASE_MAX_MG)
        val bolus = total * ALTEPLASE_BOLUS_FRACTION
        return Thrombolysis(
            alteplaseTotalMg = total,
            alteplaseBolusMg = bolus,
            alteplaseInfusionMg = total - bolus,
            tenecteplaseMg = minOf(TENECTEPLASE_MG_PER_KG * weightKg, TENECTEPLASE_MAX_MG),
        )
    }

    /** A treatment window measured from last known well. */
    data class Window(val label: String, val fromHours: Double, val toHours: Double, val note: String)

    val windows = listOf(
        Window("IV thrombolysis", 0.0, 4.5, "Within 4.5 h of last known well, if eligible."),
        Window("Thrombectomy (standard window)", 0.0, 6.0, "Large-vessel occlusion, selected patients."),
        Window("Thrombectomy (extended window)", 6.0, 24.0, "Selected by advanced imaging criteria."),
    )

    enum class WindowState { Open, Closed, NotYet }

    fun state(window: Window, hoursSinceLkw: Double): WindowState = when {
        hoursSinceLkw < window.fromHours -> WindowState.NotYet
        hoursSinceLkw <= window.toHours -> WindowState.Open
        else -> WindowState.Closed
    }
}
