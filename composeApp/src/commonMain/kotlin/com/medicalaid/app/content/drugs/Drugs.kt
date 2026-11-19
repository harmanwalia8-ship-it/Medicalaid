package com.medicalaid.app.content.drugs

import com.medicalaid.app.content.Source
import com.medicalaid.app.content.drugs.data.anticoagEndoDrugs
import com.medicalaid.app.content.drugs.data.anticoagEndoSources
import com.medicalaid.app.content.drugs.data.antibioticDrugs
import com.medicalaid.app.content.drugs.data.antibioticSources
import com.medicalaid.app.content.drugs.data.antihypertensiveDrugs
import com.medicalaid.app.content.drugs.data.antihypertensiveSources
import com.medicalaid.app.content.drugs.data.cardiovascularDrugs
import com.medicalaid.app.content.drugs.data.cardiovascularSources
import com.medicalaid.app.content.drugs.data.metabolicDrugs
import com.medicalaid.app.content.drugs.data.metabolicSources
import com.medicalaid.app.content.drugs.data.respiratoryGiDrugs
import com.medicalaid.app.content.drugs.data.respiratoryGiSources
import com.medicalaid.app.content.drugs.data.symptomaticDrugs
import com.medicalaid.app.content.drugs.data.symptomaticSources
import com.medicalaid.app.content.drugs.data.tbMalariaDrugs
import com.medicalaid.app.content.drugs.data.tbMalariaSources

/** The drug database: monographs plus the label sources they cite. */
object Drugs {
    val all: List<DrugMonograph> = (
        metabolicDrugs + symptomaticDrugs + antihypertensiveDrugs + cardiovascularDrugs +
            anticoagEndoDrugs + antibioticDrugs + tbMalariaDrugs + respiratoryGiDrugs
        ).sortedBy { it.name.lowercase() }

    val sources: List<Source> =
        metabolicSources + symptomaticSources + antihypertensiveSources + cardiovascularSources +
            anticoagEndoSources + antibioticSources + tbMalariaSources + respiratoryGiSources

    private val index = all.associateBy { it.id }

    fun byId(id: String): DrugMonograph? = index[id]

    private fun norm(s: String) = s.lowercase().replace(Regex("[^a-z0-9]+"), " ").trim()

    /** Prefix match on any word of the name, aliases or class. */
    fun search(query: String): List<DrugMonograph> {
        val tokens = norm(query).split(' ').filter { it.isNotBlank() }
        if (tokens.isEmpty()) return all
        return all.mapNotNull { d ->
            val nameWords = (listOf(d.name) + d.aliases).flatMap { norm(it).split(' ') }
            val classWords = norm(d.drugClass).split(' ')
            when {
                tokens.all { t -> nameWords.any { it.startsWith(t) } } -> 0 to d
                tokens.all { t -> (nameWords + classWords).any { it.startsWith(t) } } -> 1 to d
                else -> null
            }
        }.sortedBy { it.first }.map { it.second }
    }

    /**
     * Finds the monograph for a drug line on a condition page, e.g. "Metoclopramide + diphenhydramine"
     * or "Labetalol IV (order set)". Matches when a monograph name/alias is the first word(s).
     */
    fun forConditionDrug(drugName: String): DrugMonograph? {
        val n = norm(drugName)
        return all.firstOrNull { d ->
            (listOf(d.name) + d.aliases).any { alias ->
                val a = norm(alias)
                a.isNotEmpty() && (n == a || n.startsWith("$a "))
            }
        }
    }
}
