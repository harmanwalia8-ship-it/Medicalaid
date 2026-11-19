package com.medicalaid.app.content.protocols

import com.medicalaid.app.content.Citation

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
    val citations: List<Citation>,
)

data class ProtocolNote(val text: String, val citations: List<Citation>)

data class Protocol(
    val id: String,
    val title: String,
    val summary: String,
    val keywords: List<String> = emptyList(),
    val steps: List<Step>,
    /** Where guidelines differ, or scope notes. */
    val notes: List<ProtocolNote> = emptyList(),
    val citations: List<Citation>,
)
