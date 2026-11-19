package com.medicalaid.app.content

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchTest {
    @Test
    fun vomitingFindsNauseaFirst() {
        val hits = Search.run("vomiting")
        assertEquals("nvp", hits.first().condition.id)
    }

    @Test
    fun synonymsAndCase() {
        assertEquals("nvp", Search.run("Hyperemesis").first().condition.id)
        assertEquals("pruritus", Search.run("itching").first().condition.id)
        assertEquals("hypertension", Search.run("pre-eclampsia").first().condition.id)
    }

    @Test
    fun drugNameFindsDrugHit() {
        val hits = Search.run("ondansetron")
        assertTrue(hits.any { it.drug?.name == "Ondansetron" && it.condition.id == "nvp" })
    }

    @Test
    fun prefixMatching() {
        assertTrue(Search.run("labet").any { it.drug?.name?.startsWith("Labetalol") == true })
    }

    @Test
    fun asthmaAppearsForBothPopulations() {
        val ids = Search.run("asthma").filter { it.drug == null }.map { it.condition.id }
        assertTrue("asthma-pregnancy" in ids && "asthma-general" in ids)
    }

    @Test
    fun blankQueryIsEmpty() {
        assertTrue(Search.run("  ").isEmpty())
    }
}
