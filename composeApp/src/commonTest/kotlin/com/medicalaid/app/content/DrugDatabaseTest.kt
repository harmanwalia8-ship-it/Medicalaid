package com.medicalaid.app.content

import com.medicalaid.app.content.drugs.Drugs
import com.medicalaid.app.content.drugs.ExternalRefs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.fail

class DrugDatabaseTest {

    @Test
    fun everySectionCitesAKnownSource() {
        Drugs.all.forEach { d ->
            val cites = d.allCitations()
            assertTrue(cites.isNotEmpty(), "${d.id} has no citations")
            cites.forEach { if (!Sources.exists(it.sourceId)) fail("${d.id} cites unknown source ${it.sourceId}") }
            assertTrue(d.pk.citations.isNotEmpty(), "${d.id} pharmacokinetics uncited")
        }
    }

    @Test
    fun requiredContentPresent() {
        Drugs.all.forEach { d ->
            assertTrue(d.commonEffects.items.isNotEmpty(), "${d.id}: no common effects")
            assertTrue(d.seriousEffects.items.isNotEmpty(), "${d.id}: no serious effects")
            assertTrue(d.clearance.isNotEmpty(), "${d.id}: clearance not set")
            val pk = d.pk
            assertTrue(listOfNotNull(pk.absorption, pk.distribution, pk.metabolism, pk.elimination, pk.halfLife).isNotEmpty(), "${d.id}: empty PK")
            assertTrue(d.uses.isNotBlank() && d.drugClass.isNotBlank(), "${d.id}: missing uses/class")
        }
    }

    @Test
    fun idsUniqueAndWellFormed() {
        assertEquals(Drugs.all.size, Drugs.all.map { it.id }.toSet().size, "Duplicate drug id")
        Drugs.all.forEach { assertTrue(Regex("^[a-z0-9]+(-[a-z0-9]+)*$").matches(it.id), "Bad id ${it.id}") }
        Drugs.sources.forEach { assertTrue(it.url.startsWith("https://dailymed.nlm.nih.gov/") || it.url.startsWith("https://"), "Bad url ${it.id}") }
    }

    @Test
    fun searchAndConditionLinks() {
        assertEquals("metformin", Drugs.search("metf").first().id)
        assertTrue(Drugs.search("biguanide").any { it.id == "metformin" })
        assertNotNull(Drugs.forConditionDrug("Metformin"))
        assertEquals(null, Drugs.forConditionDrug("Metformin and glyburide")?.id?.takeIf { it != "metformin" })
    }

    @Test
    fun externalLinksAreEncoded() {
        val d = Drugs.byId("metformin")!!
        ExternalRefs.forDrug(d).forEach { assertTrue(it.url.startsWith("https://") && ' ' !in it.url, "Bad link ${it.url}") }
    }
}
