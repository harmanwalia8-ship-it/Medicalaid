package com.medicalaid.app.content.scores

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

/** Pattern for score tests: answers → expected total and band title. */
class TemplateScoreTest {
    private val qsofa = Scores.byId("qsofa")!!

    @Test
    fun qsofaExamples() {
        assertEquals(0.0, ScoreEngine.total(qsofa, emptyMap()))
        assertEquals("qSOFA 0–1", ScoreEngine.band(qsofa, 1.0)!!.title)
        val two = mapOf("rr" to 1, "sbp" to 1)
        assertEquals(2.0, ScoreEngine.total(qsofa, two))
        assertEquals("qSOFA ≥2", ScoreEngine.band(qsofa, ScoreEngine.total(qsofa, two)!!)!!.title)
    }

    @Test
    fun engineChoicesAndVisibility() {
        val s = Score(
            "t", "T", "test", ScoreCategory.Sepsis,
            items = listOf(
                Choice("c", "C", listOf(Option("a", 0.0), Option("b", 2.5, alert = "red"))),
                Criterion("x", "X", 1.0, showIf = ShowIf("c", 1)),
            ),
            bands = listOf(Band(0.0, 10.0, "any", "m", Level.Info)),
            citations = emptyList(),
        )
        assertNull(ScoreEngine.total(s, emptyMap()))           // choice unanswered
        assertEquals(0.0, ScoreEngine.total(s, mapOf("c" to 0, "x" to 1))) // x hidden
        assertEquals(3.5, ScoreEngine.total(s, mapOf("c" to 1, "x" to 1)))
        assertEquals(listOf("red"), ScoreEngine.alerts(s, mapOf("c" to 1)))
        assertEquals("3.5", ScoreEngine.format(3.5))
        assertEquals("3", ScoreEngine.format(3.0))
    }
}
