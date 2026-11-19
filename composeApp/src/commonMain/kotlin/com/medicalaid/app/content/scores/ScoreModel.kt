package com.medicalaid.app.content.scores

import com.medicalaid.app.content.Citation

enum class ScoreCategory(val label: String) {
    Neuro("Neurology & stroke"),
    Cardio("Cardiology & VTE"),
    Sepsis("Sepsis & respiratory"),
    Gi("Gastro & liver"),
    Infection("Infection"),
    Obstetric("Obstetrics"),
    Emergency("Trauma & emergency"),
}

/** Show an item only when another item has a given answer (criterion: 1 = yes; choice: option index). */
data class ShowIf(val itemId: String, val answer: Int)

sealed interface ScoreItem {
    val id: String
    val label: String
    /** Short clarification under the label, e.g. "Heart rate >100/min". */
    val hint: String?
    /** Optional group heading shown above this item (when it differs from the previous item's). */
    val section: String?
    val showIf: ShowIf?
}

/** Yes/no criterion; contributes [points] when ticked. Points can be negative or fractional. */
data class Criterion(
    override val id: String,
    override val label: String,
    val points: Double,
    override val hint: String? = null,
    override val section: String? = null,
    override val showIf: ShowIf? = null,
    /** Shown when ticked, regardless of the total (e.g. a stand-alone red flag). */
    val alert: String? = null,
) : ScoreItem

/** [alert] is shown when this option is selected, regardless of the total (e.g. NEWS2 "3 in one parameter"). */
data class Option(val label: String, val points: Double, val alert: String? = null)

/** Pick exactly one option. Must be answered before a result is shown. */
data class Choice(
    override val id: String,
    override val label: String,
    val options: List<Option>,
    override val hint: String? = null,
    override val section: String? = null,
    override val showIf: ShowIf? = null,
) : ScoreItem

enum class Level { Low, Moderate, High, Info }

/** Result band for totals in [min, max] (inclusive). */
data class Band(
    val min: Double,
    val max: Double,
    val title: String,
    /** What the band means / what the cited guideline says to do, attributed. */
    val meaning: String,
    val level: Level,
)

data class Caveat(val text: String, val citations: List<Citation>)

data class Score(
    val id: String,
    val name: String,
    /** One line: what it estimates and in whom. */
    val purpose: String,
    val category: ScoreCategory,
    val keywords: List<String> = emptyList(),
    val items: List<ScoreItem>,
    val bands: List<Band>,
    /** "When not to use" and guideline positions on the score itself. */
    val caveats: List<Caveat> = emptyList(),
    /** Derivation/validation paper and the guideline(s) that use it. */
    val citations: List<Citation>,
    /** False for decision rules where the number itself is meaningless (e.g. Ottawa rules). */
    val showTotal: Boolean = true,
)
