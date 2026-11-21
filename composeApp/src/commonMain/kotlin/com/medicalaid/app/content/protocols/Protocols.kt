package com.medicalaid.app.content.protocols

import com.medicalaid.app.content.Source
import com.medicalaid.app.content.protocols.data.*

object Protocols {
    val all: List<Protocol> = listOf(
        strokeProtocols,
        // Poisoning & envenomation
        householdToxProtocols, pesticideProtocols, envenomationProtocols, drugToxProtocols, toxAlcoholProtocols,
        paraquatProtocols, daturaProtocols, oleanderProtocols, cocaineProtocols,
        // Medical emergencies
        endocrineEmergencyProtocols, acidBaseProtocols, electrolyteProtocols, cardiacEmergencyProtocols,
        resuscitationProtocols, respiratoryEmergencyProtocols, neuroEmergencyProtocols, environmentalProtocols,
        infectionEmergencyProtocols,
        // Obstetric emergencies and trauma
        eclampsiaProtocols, pphProtocols, maternalCollapseProtocols, traumaProtocols, headSpineProtocols, limbAbdomenProtocols,
        woundsBitesProtocols,
    ).flatten()

    val sources: List<Source> = listOf(
        strokeProtocolSources, toolSources,
        householdToxSources, pesticideSources, envenomationSources, drugToxSources, toxAlcoholSources,
        paraquatSources, daturaSources, oleanderSources, cocaineSources,
        endocrineEmergencySources, acidBaseSources, electrolyteSources, cardiacEmergencySources,
        resuscitationSources, respiratoryEmergencySources, neuroEmergencySources, environmentalSources,
        infectionEmergencySources,
        eclampsiaSources, pphSources, maternalCollapseSources, traumaSources, headSpineSources, limbAbdomenSources,
        woundsBitesSources,
    ).flatten()

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
