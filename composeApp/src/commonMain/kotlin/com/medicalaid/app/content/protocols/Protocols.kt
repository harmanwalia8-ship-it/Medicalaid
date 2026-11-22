package com.medicalaid.app.content.protocols

import com.medicalaid.app.content.Source
import com.medicalaid.app.content.protocols.data.*

object Protocols {
    /** A protocol file's protocols and the sources they cite. */
    private class Group(val protocols: List<Protocol>, val sources: List<Source>)

    /**
     * Groups that have been through the two-reviewer check (numbers; safety and attribution) with
     * confirmed fixes applied. Only these are shown in the app.
     */
    private val released = listOf(
        Group(strokeProtocols, strokeProtocolSources + toolSources),
        // Poisoning & envenomation — reviewed for 0.4.0
        Group(householdToxProtocols, householdToxSources),
        Group(pesticideProtocols, pesticideSources),
        Group(envenomationProtocols, envenomationSources),
        Group(drugToxProtocols, drugToxSources),
        Group(toxAlcoholProtocols, toxAlcoholSources),
        Group(paraquatProtocols, paraquatSources),
        Group(daturaProtocols, daturaSources),
        Group(oleanderProtocols, oleanderSources),
        Group(cocaineProtocols, cocaineSources),
    )

    /**
     * Written but not yet fully reviewed. They compile and are checked by the integrity tests, but are
     * never shown. Move a group to [released] once its review and fixes are complete.
     */
    private val inReview = listOf(
        // Medical emergencies
        Group(endocrineEmergencyProtocols, endocrineEmergencySources),
        Group(acidBaseProtocols, acidBaseSources),
        Group(electrolyteProtocols, electrolyteSources),
        Group(cardiacEmergencyProtocols, cardiacEmergencySources),
        Group(resuscitationProtocols, resuscitationSources),
        Group(respiratoryEmergencyProtocols, respiratoryEmergencySources),
        Group(neuroEmergencyProtocols, neuroEmergencySources),
        Group(environmentalProtocols, environmentalSources),
        Group(infectionEmergencyProtocols, infectionEmergencySources),
        // Obstetric emergencies and trauma
        Group(eclampsiaProtocols, eclampsiaSources),
        Group(pphProtocols, pphSources),
        Group(maternalCollapseProtocols, maternalCollapseSources),
        Group(earlyPregnancyProtocols, earlyPregnancySources),
        Group(antepartumProtocols, antepartumSources),
        Group(labourEmergencyProtocols, labourEmergencySources),
        Group(maternalMedicalProtocols, maternalMedicalSources),
        Group(traumaProtocols, traumaSources),
        Group(headSpineProtocols, headSpineSources),
        Group(limbAbdomenProtocols, limbAbdomenSources),
        Group(woundsBitesProtocols, woundsBitesSources),
    )

    val all: List<Protocol> = released.flatMap { it.protocols }
    val sources: List<Source> = released.flatMap { it.sources }

    /** In-review content, for tests only. */
    internal val reviewProtocols: List<Protocol> = inReview.flatMap { it.protocols }
    internal val reviewSources: List<Source> = inReview.flatMap { it.sources }

    private val index = all.associateBy { it.id }

    fun byId(id: String): Protocol? = index[id]

    fun search(query: String): List<Protocol> {
        val q = query.lowercase().trim()
        if (q.isEmpty()) return all
        val tokens = q.split(Regex("\\s+"))
        return all.filter { p ->
            val hay = (listOf(p.title, p.summary) + p.keywords).joinToString(" ").lowercase()
            tokens.all { hay.contains(it) }
        }
    }
}
