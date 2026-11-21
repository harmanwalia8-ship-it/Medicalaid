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
import com.medicalaid.app.content.protocols.data.toxAlcoholProtocols
import com.medicalaid.app.content.protocols.data.toxAlcoholSources

object Protocols {
    val all: List<Protocol> =
        strokeProtocols + householdToxProtocols + pesticideProtocols + envenomationProtocols + drugToxProtocols + toxAlcoholProtocols +
            paraquatProtocols + daturaProtocols + oleanderProtocols + cocaineProtocols

    val sources: List<Source> =
        strokeProtocolSources + householdToxSources + pesticideSources + envenomationSources + drugToxSources + toxAlcoholSources +
            paraquatSources + daturaSources + oleanderSources + cocaineSources

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
