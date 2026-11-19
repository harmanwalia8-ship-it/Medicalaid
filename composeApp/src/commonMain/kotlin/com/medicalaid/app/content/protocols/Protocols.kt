package com.medicalaid.app.content.protocols

import com.medicalaid.app.content.Source
import com.medicalaid.app.content.protocols.data.strokeProtocolSources
import com.medicalaid.app.content.protocols.data.strokeProtocols

object Protocols {
    val all: List<Protocol> = strokeProtocols
    val sources: List<Source> = strokeProtocolSources

    private val index = all.associateBy { it.id }

    fun byId(id: String): Protocol? = index[id]

    fun search(query: String): List<Protocol> {
        val q = query.lowercase().trim()
        if (q.isEmpty()) return all
        return all.filter { p -> (listOf(p.title, p.summary) + p.keywords).any { it.lowercase().contains(q) } }
    }
}
