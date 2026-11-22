package com.medicalaid.app.content.scores

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.fail

/** Worked examples for the trauma decision rules: answers → expected total and band title. */
class TraumaScoresTest {

    private fun score(id: String): Score = assertNotNull(Scores.byId(id), "Missing score $id")

    private fun total(s: Score, answers: Map<String, Int>): Double =
        assertNotNull(ScoreEngine.total(s, answers), "${s.id}: result not available for $answers")

    private fun bandTitle(s: Score, answers: Map<String, Int>): String =
        assertNotNull(ScoreEngine.band(s, total(s, answers)), "${s.id}: no band for $answers").title

    private fun check(s: Score, answers: Map<String, Int>, expectedTotal: Double, expectedBand: String) {
        assertEquals(expectedTotal, total(s, answers), "${s.id} total for $answers")
        assertEquals(expectedBand, bandTitle(s, answers), "${s.id} band for $answers")
    }

    private val ids = listOf("canadian-c-spine", "nexus-c-spine")

    // Option indices of the Canadian C-spine Rule "group" question.
    private val notApplicable = 0
    private val highRisk = 1
    private val noLowRisk = 2
    private val lowRisk = 3

    @Test
    fun canadianCSpine() {
        val s = score("canadian-c-spine")
        assertFalse(s.showTotal)

        // The option order below is what the indices refer to: not applicable, high risk, no low-risk factor, low risk.
        val group = s.items.single { it.id == "group" } as Choice
        assertEquals(4, group.options.size)
        assertTrue(group.options[notApplicable].label.startsWith("Not applicable"))
        assertTrue(group.options[highRisk].label.startsWith("High-risk factor"))
        assertTrue(group.options[lowRisk].label.contains("at least one low-risk factor"))

        // Nothing answered → no result.
        assertNull(ScoreEngine.total(s, emptyMap()))

        check(s, mapOf("group" to notApplicable), 1000.0, "Rule not applicable")
        check(s, mapOf("group" to highRisk), 100.0, "Imaging indicated — high-risk factor")
        check(s, mapOf("group" to noLowRisk), 10.0, "Imaging indicated — no low-risk factor")

        // A low-risk factor opens the rotation question, which must then be answered.
        assertNull(ScoreEngine.total(s, mapOf("group" to lowRisk)))
        check(s, mapOf("group" to lowRisk, "rotate" to 0), 0.0, "C-spine imaging not required by the rule")
        check(s, mapOf("group" to lowRisk, "rotate" to 1), 1.0, "Imaging indicated — unable to rotate neck")

        // The rotation question is hidden for high-risk patients: a stale answer never counts.
        val stale = mapOf("group" to highRisk, "rotate" to 1)
        assertFalse(ScoreEngine.isVisible(s.items.single { it.id == "rotate" }, stale))
        check(s, stale, 100.0, "Imaging indicated — high-risk factor")
        check(s, mapOf("group" to notApplicable, "rotate" to 1), 1000.0, "Rule not applicable")
        check(s, mapOf("group" to noLowRisk, "rotate" to 0), 10.0, "Imaging indicated — no low-risk factor")

        assertTrue(ScoreEngine.alerts(s, mapOf("group" to lowRisk, "rotate" to 1)).isEmpty())
    }

    // Option indices of the NEXUS "assessed" question.
    private val allAssessed = 0
    private val notAssessable = 1

    @Test
    fun nexus() {
        val s = score("nexus-c-spine")
        assertFalse(s.showTotal)

        // The gating question comes first: yes (all five assessed), then no (at least one cannot be assessed).
        assertEquals("assessed", s.items.first().id)
        val assessed = s.items.single { it.id == "assessed" } as Choice
        assertEquals(2, assessed.options.size)
        assertTrue(assessed.options[allAssessed].label.startsWith("Yes"))
        assertTrue(assessed.options[notAssessable].label.startsWith("No"))

        // Nothing answered → no result: the untouched page never reads as "imaging not required".
        assertNull(ScoreEngine.total(s, emptyMap()))
        // Ticking or unticking criteria does not produce a result until the gating question is answered.
        assertNull(ScoreEngine.total(s, mapOf("tenderness" to 0, "deficit" to 0)))
        assertNull(ScoreEngine.total(s, mapOf("tenderness" to 1)))

        check(s, mapOf("assessed" to allAssessed), 0.0, "Imaging not required by NEXUS")
        check(s, mapOf("assessed" to notAssessable), 1.0, "Imaging indicated by NEXUS")
        check(s, mapOf("assessed" to allAssessed, "tenderness" to 1), 1.0, "Imaging indicated by NEXUS")
        check(s, mapOf("assessed" to allAssessed, "distracting" to 1), 1.0, "Imaging indicated by NEXUS")
        check(
            s, mapOf("assessed" to allAssessed, "tenderness" to 0, "intoxication" to 1, "alertness" to 1),
            2.0, "Imaging indicated by NEXUS",
        )
        check(s, mapOf("assessed" to notAssessable, "tenderness" to 0, "deficit" to 0), 1.0, "Imaging indicated by NEXUS")
        check(s, s.items.associate { it.id to 1 }, 6.0, "Imaging indicated by NEXUS")
    }

    // ---------------------------------------------------------------------------------------------
    // Exhaustive checks: every complete set of answers gets a result and a band.
    // ---------------------------------------------------------------------------------------------

    private fun forEachAnswerSet(items: List<ScoreItem>, i: Int, acc: MutableMap<String, Int>, f: (Map<String, Int>) -> Unit) {
        if (i == items.size) {
            f(acc)
            return
        }
        val item = items[i]
        val n = when (item) {
            is Criterion -> 2
            is Choice -> item.options.size
        }
        for (v in 0 until n) {
            acc[item.id] = v
            forEachAnswerSet(items, i + 1, acc, f)
        }
        acc.remove(item.id)
    }

    @Test
    fun everyAnswerSetHasABand() {
        val expectedMax = mapOf("canadian-c-spine" to 1000.0, "nexus-c-spine" to 6.0)
        ids.forEach { id ->
            val s = score(id)
            var max = Double.NEGATIVE_INFINITY
            forEachAnswerSet(s.items, 0, mutableMapOf()) { answers ->
                val t = ScoreEngine.total(s, answers) ?: fail("$id: no result for $answers")
                if (ScoreEngine.band(s, t) == null) fail("$id: no band for total $t ($answers)")
                if (t > max) max = t
            }
            expectedMax[id]?.let { assertEquals(it, max, "$id maximum") }
        }
    }

    @Test
    fun scoresAreRegisteredAndCited() {
        ids.forEach { id ->
            val s = score(id)
            assertEquals(ScoreCategory.Emergency, s.category, "$id category")
            assertTrue(s.citations.isNotEmpty(), "$id has no citation")
            assertTrue(s.caveats.isNotEmpty(), "$id has no caveats")
            assertEquals(s.bands.size, s.bands.map { it.title }.toSet().size, "$id has duplicate band titles")
            s.bands.forEach { assertTrue(it.min <= it.max, "$id band ${it.title}") }
            (s.citations + s.caveats.flatMap { it.citations }).forEach { c ->
                assertTrue(Scores.sources.any { it.id == c.sourceId }, "$id cites ${c.sourceId}")
            }
        }
    }
}
