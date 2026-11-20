package com.medicalaid.app.content.protocols

import com.medicalaid.app.content.Citation
import com.medicalaid.app.content.WeightDose

enum class ProtocolCategory(val label: String) {
    Emergency("Emergencies"),
    Poisoning("Poisoning & envenomation"),
}

/** A weight-based dose shown with a calculator when the clinician enters a weight. */
data class StepDose(
    /** e.g. "Pralidoxime — loading dose". */
    val label: String,
    val dose: WeightDose,
    /** Route / rate / dilution as the source states it. */
    val note: String? = null,
    /** Absolute cap, in the same unit as [dose], if the source gives one. */
    val maxTotal: Double? = null,
) {
    /** Lowest applicable cap for [weightKg] (absolute max or per-kg ceiling), or null. */
    fun cap(weightKg: Double): Double? = listOfNotNull(maxTotal, dose.ceilingPerKg?.let { it * weightKg }).minOrNull()

    /** (low, high) total for [weightKg], each limited by [cap]. */
    fun range(weightKg: Double): Pair<Double, Double> {
        val c = cap(weightKg)
        fun lim(x: Double) = if (c != null) minOf(x, c) else x
        return lim(dose.minPerKg * weightKg) to lim(dose.maxPerKg * weightKg)
    }
}

/** Interactive helpers that a protocol step can embed. */
enum class ProtocolTool {
    /** Time since last known well → which treatment windows are still open. */
    OnsetClock,
    /** Weight → alteplase and tenecteplase doses for acute ischaemic stroke. */
    ThrombolysisDose,
}

/** One step of a time-critical pathway. Every statement is attributed through [citations]. */
data class Step(
    val title: String,
    /** Time target for the step, e.g. "Door-to-needle ≤60 min". */
    val timing: String? = null,
    val points: List<String> = emptyList(),
    /** Tappable checklist (e.g. thrombolysis exclusions). */
    val checklistTitle: String? = null,
    val checklist: List<String> = emptyList(),
    val tool: ProtocolTool? = null,
    /** Ids of scores to link from this step (e.g. "nihss"). */
    val scoreIds: List<String> = emptyList(),
    /** Weight-based antidote/drug doses for this step. */
    val doses: List<StepDose> = emptyList(),
    val citations: List<Citation>,
)

data class ProtocolNote(val text: String, val citations: List<Citation>)

data class Protocol(
    val id: String,
    val title: String,
    val summary: String,
    val keywords: List<String> = emptyList(),
    val category: ProtocolCategory = ProtocolCategory.Emergency,
    val steps: List<Step>,
    /** Where guidelines differ, or scope notes. */
    val notes: List<ProtocolNote> = emptyList(),
    val citations: List<Citation>,
)
