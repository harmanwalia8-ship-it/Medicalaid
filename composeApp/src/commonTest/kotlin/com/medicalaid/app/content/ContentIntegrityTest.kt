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
        "nkf-ckd-epi-2021", "kdigo-ckd-2024", "fda-renal-guidance-2024", "cockcroft-gault-1976",
        "st-aha-ais-2026", "st-nice-ng128", "st-mohfw-stroke-2022",
        "tool-lund-browder", "tool-burns-fluids", "tool-mohfw-burns-2025", "tool-acid-base", "tool-abg-reference",
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
    fun scoresAndProtocolsAreCited() {
        com.medicalaid.app.content.scores.Scores.all.forEach { sc ->
            checkCitations("Score ${sc.id}", sc.citations)
            sc.caveats.forEach { checkCitations("Score ${sc.id} caveat", it.citations) }
            assertTrue(sc.items.isNotEmpty() && sc.bands.isNotEmpty(), "Score ${sc.id} incomplete")
        }
        com.medicalaid.app.content.protocols.Protocols.all.forEach { p ->
            checkCitations("Protocol ${p.id}", p.citations)
            p.steps.forEach { checkCitations("Protocol ${p.id} step '${it.title}'", it.citations) }
            p.notes.forEach { checkCitations("Protocol ${p.id} note", it.citations) }
            p.steps.flatMap { it.scoreIds }.forEach {
                assertTrue(com.medicalaid.app.content.scores.Scores.byId(it) != null, "Protocol ${p.id} links unknown score $it")
            }
        }
    }

    @Test
    fun idsAreUnique() {
        assertEquals(Conditions.all.size, Conditions.all.map { it.id }.toSet().size, "Duplicate condition id")
        assertEquals(Schedule.items.size, Schedule.items.map { it.id }.toSet().size, "Duplicate schedule id")
        assertEquals(Sources.all.size, Sources.all.map { it.id }.toSet().size, "Duplicate source id")
        val scoreIds = com.medicalaid.app.content.scores.Scores.all.map { it.id }
        assertEquals(scoreIds.size, scoreIds.toSet().size, "Duplicate score id")
        val protocolIds = com.medicalaid.app.content.protocols.Protocols.all.map { it.id }
        assertEquals(protocolIds.size, protocolIds.toSet().size, "Duplicate protocol id")
    }

    /** Protocols still in review are hidden from the app but must already meet the same rules. */
    @Test
    fun inReviewProtocolsMeetTheSameRules() {
        val known = Sources.withReview.map { it.id }.toSet()
        fun check(where: String, citations: List<Citation>) {
            if (citations.isEmpty()) fail("$where has no citation")
            citations.forEach { if (it.sourceId !in known) fail("$where cites unknown source '${it.sourceId}'") }
        }
        val review = com.medicalaid.app.content.protocols.Protocols.reviewProtocols
        review.forEach { p ->
            check("Protocol ${p.id}", p.citations)
            p.steps.forEach { check("Protocol ${p.id} step '${it.title}'", it.citations) }
            p.notes.forEach { check("Protocol ${p.id} note", it.citations) }
            p.steps.flatMap { it.scoreIds }.forEach {
                assertTrue(com.medicalaid.app.content.scores.Scores.byId(it) != null, "Protocol ${p.id} links unknown score $it")
            }
        }
        val ids = (com.medicalaid.app.content.protocols.Protocols.all + review).map { it.id }
        assertEquals(ids.size, ids.toSet().size, "Duplicate protocol id (released + in review)")
        val sourceIds = Sources.withReview.map { it.id }
        assertEquals(sourceIds.size, sourceIds.toSet().size, "Duplicate source id (released + in review)")
        val used = buildSet {
            (com.medicalaid.app.content.protocols.Protocols.all + review).forEach { p ->
                p.citations.forEach { add(it.sourceId) }
                p.notes.forEach { n -> n.citations.forEach { add(it.sourceId) } }
                p.steps.forEach { st -> st.citations.forEach { add(it.sourceId) } }
            }
        }
        val unused = com.medicalaid.app.content.protocols.Protocols.reviewSources.map { it.id }.filterNot { it in used }
        assertTrue(unused.isEmpty(), "Unused in-review sources: $unused")
        com.medicalaid.app.content.protocols.Protocols.reviewSources.forEach {
            assertTrue(it.url.startsWith("https://"), "${it.id} is not https")
        }
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
            com.medicalaid.app.content.drugs.Drugs.all.forEach { d -> d.allCitations().forEach { add(it.sourceId) } }
            com.medicalaid.app.content.scores.Scores.all.forEach { sc ->
                sc.citations.forEach { add(it.sourceId) }
                sc.caveats.forEach { cv -> cv.citations.forEach { add(it.sourceId) } }
            }
            com.medicalaid.app.content.protocols.Protocols.all.forEach { p ->
                p.citations.forEach { add(it.sourceId) }
                p.notes.forEach { n -> n.citations.forEach { add(it.sourceId) } }
                p.steps.forEach { st -> st.citations.forEach { add(it.sourceId) } }
            }
            // Cited directly by UI components (dating card, calculators).
            addAll(UI_CITED)
        }
        val unused = Sources.all.map { it.id }.filterNot { it in used }
        assertTrue(unused.isEmpty(), "Unused sources: $unused")
    }
}
