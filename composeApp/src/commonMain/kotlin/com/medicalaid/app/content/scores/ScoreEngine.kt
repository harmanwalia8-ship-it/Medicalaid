package com.medicalaid.app.content.scores

/**
 * Pure scoring logic. Answers: criterion id → 1 (yes) / 0 (no); choice id → option index.
 * Hidden items (showIf not met) never count.
 */
object ScoreEngine {

    fun isVisible(item: ScoreItem, answers: Map<String, Int>): Boolean {
        val cond = item.showIf ?: return true
        return answers[cond.itemId] == cond.answer
    }

    /** Visible choices without an answer. A result is shown only when this is empty. */
    fun unanswered(score: Score, answers: Map<String, Int>): List<Choice> =
        score.items.filterIsInstance<Choice>().filter { isVisible(it, answers) && answers[it.id] == null }

    /** Total, or null while a visible choice is unanswered. */
    fun total(score: Score, answers: Map<String, Int>): Double? {
        if (unanswered(score, answers).isNotEmpty()) return null
        return score.items.filter { isVisible(it, answers) }.sumOf { item ->
            when (item) {
                is Criterion -> if (answers[item.id] == 1) item.points else 0.0
                is Choice -> item.options.getOrNull(answers[item.id] ?: -1)?.points ?: 0.0
            }
        }
    }

    /** Alerts triggered by the current answers (visible items only). */
    fun alerts(score: Score, answers: Map<String, Int>): List<String> =
        score.items.filter { isVisible(it, answers) }.mapNotNull { item ->
            when (item) {
                is Criterion -> item.alert?.takeIf { answers[item.id] == 1 }
                is Choice -> item.options.getOrNull(answers[item.id] ?: -1)?.alert
            }
        }.distinct()

    fun band(score: Score, total: Double): Band? =
        score.bands.firstOrNull { total >= it.min - 1e-9 && total <= it.max + 1e-9 }

    /** "4", "4.5", "-1" */
    fun format(total: Double): String {
        val r = kotlin.math.round(total * 10) / 10.0
        return if (r == kotlin.math.floor(r)) r.toLong().toString() else r.toString()
    }
}
