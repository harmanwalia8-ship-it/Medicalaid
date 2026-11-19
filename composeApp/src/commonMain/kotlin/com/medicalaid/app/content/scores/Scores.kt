package com.medicalaid.app.content.scores

import com.medicalaid.app.content.Source
import com.medicalaid.app.content.scores.data.acuteCareScoreSources
import com.medicalaid.app.content.scores.data.acuteCareScores
import com.medicalaid.app.content.scores.data.neuroCardioScoreSources
import com.medicalaid.app.content.scores.data.neuroCardioScores
import com.medicalaid.app.content.scores.data.templateScoreSources
import com.medicalaid.app.content.scores.data.templateScores

object Scores {
    val all: List<Score> = (templateScores + neuroCardioScores + acuteCareScores).sortedBy { it.name.lowercase() }

    val sources: List<Source> = templateScoreSources + neuroCardioScoreSources + acuteCareScoreSources

    private val index = all.associateBy { it.id }

    fun byId(id: String): Score? = index[id]

    private fun norm(s: String) = s.lowercase().replace(Regex("[^a-z0-9₂²]+"), " ").replace('₂', '2').replace('²', '2').trim()

    fun search(query: String): List<Score> {
        val tokens = norm(query).split(' ').filter { it.isNotBlank() }
        if (tokens.isEmpty()) return all
        return all.filter { s ->
            val words = (listOf(s.name, s.purpose, s.category.label) + s.keywords).flatMap { norm(it).split(' ') }
            tokens.all { t -> words.any { it.startsWith(t) } }
        }
    }
}
