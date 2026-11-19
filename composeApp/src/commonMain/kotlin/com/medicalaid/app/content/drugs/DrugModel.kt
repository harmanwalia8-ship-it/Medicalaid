package com.medicalaid.app.content.drugs

import com.medicalaid.app.content.Citation

/** Main route(s) by which the drug and its active metabolites leave the body. */
enum class Clearance(val label: String) {
    Renal("Kidney"),
    Hepatic("Liver"),
    Biliary("Bile / faeces"),
}

/** A short list of statements, each traceable to the cited section(s). */
data class Facts(val items: List<String>, val citations: List<Citation>)

/** Brief pharmacokinetics. Each field is one short line; null when the source does not state it. */
data class Pharmacokinetics(
    /** Bioavailability, time to peak, food effect. */
    val absorption: String? = null,
    /** Protein binding, volume of distribution. */
    val distribution: String? = null,
    /** Main pathway / enzymes (e.g. CYP3A4), active metabolites. */
    val metabolism: String? = null,
    /** Route and proportion (urine / faeces), unchanged fraction. */
    val elimination: String? = null,
    val halfLife: String? = null,
    val citations: List<Citation>,
)

/**
 * One entry in the drug database. Content is a concise paraphrase of the cited official label
 * (or other freely reusable source); every section carries its own section-level citation.
 */
data class DrugMonograph(
    /** Lower-case, hyphenated, e.g. "metformin". */
    val id: String,
    /** Name as commonly used in India, e.g. "Paracetamol". */
    val name: String,
    /** Other generic names used for search, e.g. "acetaminophen". */
    val aliases: List<String> = emptyList(),
    val drugClass: String,
    /** One line: main uses per the label. */
    val uses: String,
    val clearance: Set<Clearance>,
    val boxedWarning: Facts? = null,
    /** Usual adult dosing in brief. */
    val dose: Facts? = null,
    /** Tests or checks the source says to do before starting. */
    val beforeStarting: Facts? = null,
    val monitoring: Facts? = null,
    val renal: Facts? = null,
    val hepatic: Facts? = null,
    val commonEffects: Facts,
    val seriousEffects: Facts,
    val pk: Pharmacokinetics,
    val interactions: Facts? = null,
    val pregnancy: Facts? = null,
    val lactation: Facts? = null,
) {
    val hasRenalAdjustment: Boolean get() = renal != null
    val hasHepaticCaution: Boolean get() = hepatic != null

    fun allCitations(): List<Citation> = listOfNotNull(
        boxedWarning, dose, beforeStarting, monitoring, renal, hepatic, commonEffects, seriousEffects,
        interactions, pregnancy, lactation,
    ).flatMap { it.citations } + pk.citations
}
