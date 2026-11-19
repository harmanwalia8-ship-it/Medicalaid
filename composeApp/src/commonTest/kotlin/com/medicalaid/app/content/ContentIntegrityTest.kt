package com.medicalaid.app.content

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

/** Guards the core rule of the app: no recommendation without a resolvable, official source. */
class ContentIntegrityTest {

    /** Source ids cited directly by UI components (dating card, calculators). */
    private val UI_CITED = listOf(
        "acog-co700", "acog-co579", "ada-eag", "ada-s2", "ada-s9", "icmr-stw-t2dm-2022",
        "mohfw-thyroid-2014", "ata-2026-pregnancy", "nice-ng145", "ata-2014", "ata-aace-2012", "icmr-stw-hypothyroid-2022",
    )

    @Test
    fun uiCitedSourcesExist() {
        UI_CITED.forEach { assertTrue(Sources.exists(it), "UI cites unknown source '$it'") }
    }

    private fun checkCitations(where: String, citations: List<Citation>) {
        if (citations.isEmpty()) fail("$where has no citation")
        citations.forEach { if (!Sources.exists(it.sourceId)) fail("$where cites unknown source '${it.sourceId}'") }
    }

    @Test
    fun everyDrugCautionInfoAndConditionIsCited() {
        Conditions.all.forEach { c ->
            checkCitations("Condition ${c.id}", c.citations)
            c.info.forEach { checkCitations("${c.id} info '${it.title}'", it.citations) }
            c.avoid.forEach { checkCitations("${c.id} avoid '${it.name}'", it.citations) }
            c.tiers.forEach { t ->
                assertTrue(t.drugs.isNotEmpty(), "${c.id} tier '${t.label}' is empty")
                t.drugs.forEach { checkCitations("${c.id} / ${t.label} / ${it.name}", it.citations) }
            }
        }
    }

    @Test
    fun everyScheduleItemIsCitedAndWindowsAreValid() {
        Schedule.items.forEach { item ->
            checkCitations("Schedule ${item.id}", item.citations)
            item.window?.let { w ->
                assertTrue(w.startDays in 0..300 && w.endDays in w.startDays..300, "Bad window for ${item.id}: $w")
            }
        }
    }

    @Test
    fun idsAreUnique() {
        assertEquals(Conditions.all.size, Conditions.all.map { it.id }.toSet().size, "Duplicate condition id")
        assertEquals(Schedule.items.size, Schedule.items.map { it.id }.toSet().size, "Duplicate schedule id")
        assertEquals(Sources.all.size, Sources.all.map { it.id }.toSet().size, "Duplicate source id")
    }

    @Test
    fun sourcesUseHttps() {
        Sources.all.forEach { assertTrue(it.url.startsWith("https://"), "${it.id} is not https") }
    }

    @Test
    fun everySourceIsUsed() {
        val used = buildSet {
            Conditions.all.forEach { c ->
                c.citations.forEach { add(it.sourceId) }
                c.info.forEach { b -> b.citations.forEach { add(it.sourceId) } }
                c.avoid.forEach { a -> a.citations.forEach { add(it.sourceId) } }
                c.tiers.forEach { t -> t.drugs.forEach { d -> d.citations.forEach { add(it.sourceId) } } }
            }
            Schedule.items.forEach { i -> i.citations.forEach { add(it.sourceId) } }
            // Cited directly by UI components (dating card, calculators).
            addAll(UI_CITED)
        }
        val unused = Sources.all.map { it.id }.filterNot { it in used }
        assertTrue(unused.isEmpty(), "Unused sources: $unused")
    }
}
