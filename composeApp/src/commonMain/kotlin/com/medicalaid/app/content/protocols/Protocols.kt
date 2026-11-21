package com.medicalaid.app.content.protocols

import com.medicalaid.app.content.Source
import com.medicalaid.app.content.protocols.data.drugToxSources
import com.medicalaid.app.content.protocols.data.drugToxProtocols
import com.medicalaid.app.content.protocols.data.envenomationSources
import com.medicalaid.app.content.protocols.data.envenomationProtocols
import com.medicalaid.app.content.protocols.data.householdToxSources
import com.medicalaid.app.content.protocols.data.householdToxProtocols
import com.medicalaid.app.content.protocols.data.pesticideSources
import com.medicalaid.app.content.protocols.data.pesticideProtocols
import com.medicalaid.app.content.protocols.data.strokeProtocolSources
import com.medicalaid.app.content.protocols.data.paraquatProtocols
import com.medicalaid.app.content.protocols.data.paraquatSources
import com.medicalaid.app.content.protocols.data.daturaProtocols
import com.medicalaid.app.content.protocols.data.daturaSources
import com.medicalaid.app.content.protocols.data.oleanderProtocols
import com.medicalaid.app.content.protocols.data.oleanderSources
import com.medicalaid.app.content.protocols.data.cocaineProtocols
import com.medicalaid.app.content.protocols.data.cocaineSources
import com.medicalaid.app.content.protocols.data.strokeProtocols
import com.medicalaid.app.content.protocols.data.toolSources
import com.medicalaid.app.content.protocols.data.endocrineEmergencySources
import com.medicalaid.app.content.protocols.data.endocrineEmergencyProtocols
import com.medicalaid.app.content.protocols.data.acidBaseSources
import com.medicalaid.app.content.protocols.data.acidBaseProtocols
import com.medicalaid.app.content.protocols.data.electrolyteSources
import com.medicalaid.app.content.protocols.data.electrolyteProtocols
import com.medicalaid.app.content.protocols.data.cardiacEmergencySources
import com.medicalaid.app.content.protocols.data.cardiacEmergencyProtocols
import com.medicalaid.app.content.protocols.data.resuscitationSources
import com.medicalaid.app.content.protocols.data.resuscitationProtocols
import com.medicalaid.app.content.protocols.data.respiratoryEmergencySources
import com.medicalaid.app.content.protocols.data.respiratoryEmergencyProtocols
import com.medicalaid.app.content.protocols.data.neuroEmergencySources
import com.medicalaid.app.content.protocols.data.neuroEmergencyProtocols
import com.medicalaid.app.content.protocols.data.environmentalSources
import com.medicalaid.app.content.protocols.data.environmentalProtocols
import com.medicalaid.app.content.protocols.data.infectionEmergencySources
import com.medicalaid.app.content.protocols.data.infectionEmergencyProtocols
import com.medicalaid.app.content.protocols.data.toxAlcoholProtocols
import com.medicalaid.app.content.protocols.data.toxAlcoholSources

object Protocols {
    val all: List<Protocol> =
        strokeProtocols + householdToxProtocols + pesticideProtocols + envenomationProtocols + drugToxProtocols + toxAlcoholProtocols +
            paraquatProtocols + daturaProtocols + oleanderProtocols + cocaineProtocols +
            endocrineEmergencyProtocols + acidBaseProtocols + electrolyteProtocols + cardiacEmergencyProtocols + resuscitationProtocols + respiratoryEmergencyProtocols + neuroEmergencyProtocols + environmentalProtocols + infectionEmergencyProtocols

    val sources: List<Source> =
        strokeProtocolSources + householdToxSources + pesticideSources + envenomationSources + drugToxSources + toxAlcoholSources +
            paraquatSources + daturaSources + oleanderSources + cocaineSources + toolSources +
            endocrineEmergencySources + acidBaseSources + electrolyteSources + cardiacEmergencySources + resuscitationSources + respiratoryEmergencySources + neuroEmergencySources + environmentalSources + infectionEmergencySources

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
