package com.medicalaid.app.content.scores

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.fail

/** Worked examples for the acute-care scores: answers → expected total and band title. */
class AcuteCareScoresTest {

    private fun score(id: String): Score = assertNotNull(Scores.byId(id), "Missing score $id")

    private fun total(s: Score, answers: Map<String, Int>): Double =
        assertNotNull(ScoreEngine.total(s, answers), "${s.id}: result not available for $answers")

    private fun bandTitle(s: Score, answers: Map<String, Int>): String =
        assertNotNull(ScoreEngine.band(s, total(s, answers)), "${s.id}: no band for $answers").title

    private fun check(s: Score, answers: Map<String, Int>, expectedTotal: Double, expectedBand: String) {
        assertEquals(expectedTotal, total(s, answers), "${s.id} total for $answers")
        assertEquals(expectedBand, bandTitle(s, answers), "${s.id} band for $answers")
    }

    private val ids = listOf(
        "curb-65", "news2", "alvarado", "air", "glasgow-blatchford", "child-pugh",
        "centor-mcisaac", "bishop", "ottawa-ankle", "ottawa-foot", "canadian-ct-head",
    )

    // ---------------------------------------------------------------------------------------------

    @Test
    fun curb65() {
        val s = score("curb-65")
        check(s, emptyMap(), 0.0, "CURB-65 0–1: low risk")
        check(s, mapOf("confusion" to 1), 1.0, "CURB-65 0–1: low risk")
        check(s, mapOf("confusion" to 1, "age" to 1), 2.0, "CURB-65 2: intermediate risk")
        check(s, mapOf("rr" to 1, "bp" to 1, "age" to 1), 3.0, "CURB-65 3–5: high risk")
        check(s, mapOf("confusion" to 1, "urea" to 1, "rr" to 1, "bp" to 1, "age" to 1), 5.0, "CURB-65 3–5: high risk")
    }

    private val news2Normal = mapOf(
        "rr" to 2, "spo2Scale" to 0, "spo2s1" to 3, "o2" to 0,
        "sbp" to 3, "pulse" to 2, "consciousness" to 0, "temp" to 2,
    )

    @Test
    fun news2() {
        val s = score("news2")
        check(s, news2Normal, 0.0, "NEWS2 0")
        assertTrue(ScoreEngine.alerts(s, news2Normal).isEmpty())
        assertNull(ScoreEngine.total(s, news2Normal - "spo2s1")) // visible SpO2 choice unanswered

        // Single parameter scoring 3 (new confusion): aggregate low, but red-score alert.
        val confused = news2Normal + ("consciousness" to 1)
        check(s, confused, 3.0, "NEWS2 1–4: low risk")
        val alerts = ScoreEngine.alerts(s, confused)
        assertEquals(1, alerts.size)
        assertTrue(alerts.single().startsWith("RCP NEWS2: a score of 3 in a single parameter"))

        // Boundaries 4 vs 5 and 6 vs 7.
        val four = news2Normal + mapOf("rr" to 3, "o2" to 1) // 2 + 2
        check(s, four, 4.0, "NEWS2 1–4: low risk")
        check(s, four + ("temp" to 3), 5.0, "NEWS2 5–6: medium risk")
        val six = four + ("pulse" to 4) // + 2
        check(s, six, 6.0, "NEWS2 5–6: medium risk")
        check(s, six + ("temp" to 3), 7.0, "NEWS2 ≥7: high risk")
        assertTrue(ScoreEngine.alerts(s, six).isEmpty())

        // Maximum: 3 + 3 + 2 + 3 + 3 + 3 + 3 = 20.
        val worst = mapOf(
            "rr" to 4, "spo2Scale" to 0, "spo2s1" to 0, "o2" to 1,
            "sbp" to 0, "pulse" to 5, "consciousness" to 1, "temp" to 0,
        )
        check(s, worst, 20.0, "NEWS2 ≥7: high risk")
    }

    @Test
    fun news2Scale2() {
        val s = score("news2")
        // Scale 2, 95% on air scores 0; a stale Scale 1 answer is hidden and ignored.
        val onAir = news2Normal + mapOf("spo2Scale" to 1, "spo2s2" to 4, "spo2s1" to 0)
        check(s, onAir, 0.0, "NEWS2 0")
        assertTrue(ScoreEngine.alerts(s, onAir).isEmpty())
        // Scale 2, 88–92% on oxygen: SpO2 0 + oxygen 2.
        check(s, news2Normal + mapOf("spo2Scale" to 1, "spo2s2" to 3, "o2" to 1), 2.0, "NEWS2 1–4: low risk")
        // Scale 2, ≥97% on oxygen: 3 + 2 = 5, with red-score alert.
        val over = news2Normal + mapOf("spo2Scale" to 1, "spo2s2" to 7, "o2" to 1)
        check(s, over, 5.0, "NEWS2 5–6: medium risk")
        assertEquals(1, ScoreEngine.alerts(s, over).size)
        // Scale 2 selected but its SpO2 question unanswered → no result.
        assertNull(ScoreEngine.total(s, news2Normal + ("spo2Scale" to 1)))
    }

    @Test
    fun alvarado() {
        val s = score("alvarado")
        check(s, emptyMap(), 0.0, "Alvarado 0–4: low")
        check(s, mapOf("tenderness" to 1, "wbc" to 1), 4.0, "Alvarado 0–4: low")
        check(s, mapOf("tenderness" to 1, "wbc" to 1, "migration" to 1), 5.0, "Alvarado 5–6: compatible")
        check(s, mapOf("tenderness" to 1, "wbc" to 1, "migration" to 1, "anorexia" to 1), 6.0, "Alvarado 5–6: compatible")
        check(
            s, mapOf("tenderness" to 1, "wbc" to 1, "migration" to 1, "anorexia" to 1, "nausea" to 1),
            7.0, "Alvarado 7–8: probable",
        )
        val all = s.items.associate { it.id to 1 }
        check(s, all, 10.0, "Alvarado 9–10: very probable")
        check(s, all - "shift", 9.0, "Alvarado 9–10: very probable")
    }

    private val airZero = mapOf("rebound" to 0, "pmn" to 0, "wbc" to 0, "crp" to 0)

    @Test
    fun air() {
        val s = score("air")
        check(s, airZero, 0.0, "AIR 0–4: low probability")
        assertNull(ScoreEngine.total(s, airZero - "crp"))
        // 4 vs 5: light vs medium rebound.
        val four = airZero + mapOf("vomiting" to 1, "rif" to 1, "temp" to 1, "rebound" to 1)
        check(s, four, 4.0, "AIR 0–4: low probability")
        check(s, four + ("rebound" to 2), 5.0, "AIR 5–8: indeterminate")
        // 8 vs 9: CRP 10–49 vs ≥50.
        val eight = mapOf("vomiting" to 1, "rif" to 1, "rebound" to 3, "pmn" to 1, "wbc" to 1, "crp" to 1)
        check(s, eight, 8.0, "AIR 5–8: indeterminate")
        check(s, eight + ("crp" to 2), 9.0, "AIR 9–12: high probability")
        // Maximum 12.
        val max = mapOf("vomiting" to 1, "rif" to 1, "temp" to 1, "rebound" to 3, "pmn" to 2, "wbc" to 2, "crp" to 2)
        check(s, max, 12.0, "AIR 9–12: high probability")
    }

    private val gbsZeroMale = mapOf("urea" to 0, "sex" to 0, "hbMale" to 0, "sbp" to 0)

    @Test
    fun glasgowBlatchford() {
        val s = score("glasgow-blatchford")
        check(s, gbsZeroMale, 0.0, "GBS 0")
        check(s, gbsZeroMale + ("pulse" to 1), 1.0, "GBS 1")
        check(s, gbsZeroMale + ("urea" to 1), 2.0, "GBS ≥2: not low risk")
        assertNull(ScoreEngine.total(s, gbsZeroMale - "hbMale")) // sex chosen, Hb not answered

        // Same Hb (110 g/L) scores 3 in men but 1 in women; the other sex's Hb answer is ignored.
        check(s, gbsZeroMale + ("hbMale" to 2), 3.0, "GBS ≥2: not low risk")
        val woman110 = mapOf("urea" to 0, "sex" to 1, "hbFemale" to 1, "hbMale" to 2, "sbp" to 0)
        check(s, woman110, 1.0, "GBS 1")

        // Maximum 23 (6 + 6 + 3 + 1 + 1 + 2 + 2 + 2), for either sex.
        val others = mapOf("pulse" to 1, "melaena" to 1, "syncope" to 1, "hepatic" to 1, "cardiac" to 1)
        check(s, mapOf("urea" to 4, "sex" to 0, "hbMale" to 3, "sbp" to 3) + others, 23.0, "GBS ≥2: not low risk")
        check(s, mapOf("urea" to 4, "sex" to 1, "hbFemale" to 2, "sbp" to 3) + others, 23.0, "GBS ≥2: not low risk")
    }

    @Test
    fun childPugh() {
        val s = score("child-pugh")
        val best = mapOf("bilirubin" to 0, "albumin" to 0, "inr" to 0, "ascites" to 0, "encephalopathy" to 0)
        check(s, best, 5.0, "Child-Pugh A (5–6)")
        check(s, best + ("bilirubin" to 1), 6.0, "Child-Pugh A (5–6)")
        check(s, best + mapOf("bilirubin" to 1, "albumin" to 1), 7.0, "Child-Pugh B (7–9)")
        check(s, best + mapOf("bilirubin" to 2, "albumin" to 2), 9.0, "Child-Pugh B (7–9)")
        check(s, best + mapOf("bilirubin" to 2, "albumin" to 2, "inr" to 1), 10.0, "Child-Pugh C (10–15)")
        check(s, best.mapValues { 2 }, 15.0, "Child-Pugh C (10–15)")
        assertNull(ScoreEngine.total(s, best - "ascites"))
    }

    @Test
    fun centorMcIsaac() {
        val s = score("centor-mcisaac")
        val centor = mapOf("version" to 0)
        check(s, centor, 0.0, "Score ≤1")
        // Age is hidden (and ignored) for the plain Centor score.
        check(s, centor + ("age" to 0), 0.0, "Score ≤1")
        assertTrue(ScoreEngine.alerts(s, centor).isEmpty())
        // NICE boundary: Centor 2 vs 3.
        check(s, centor + mapOf("exudate" to 1, "nodes" to 1), 2.0, "Score 2")
        check(s, centor + mapOf("exudate" to 1, "nodes" to 1, "fever" to 1), 3.0, "Score 3")
        val allFour = mapOf("exudate" to 1, "nodes" to 1, "fever" to 1, "noCough" to 1)
        check(s, centor + allFour, 4.0, "Score ≥4")

        // McIsaac: age question must be answered; +1 for 3–14, −1 for ≥45.
        val mcIsaac = mapOf("version" to 1)
        assertNull(ScoreEngine.total(s, mcIsaac))
        check(s, mcIsaac + allFour + ("age" to 0), 5.0, "Score ≥4")
        check(s, mcIsaac + allFour + ("age" to 2), 3.0, "Score 3")
        check(s, mcIsaac + ("age" to 2), -1.0, "Score ≤1")
        check(s, mcIsaac + mapOf("fever" to 1, "age" to 1), 1.0, "Score ≤1")
        assertEquals(1, ScoreEngine.alerts(s, mcIsaac + ("age" to 1)).size)
    }

    @Test
    fun bishop() {
        val s = score("bishop")
        val zero = mapOf("dilation" to 0, "effacement" to 0, "station" to 0, "consistency" to 0, "position" to 0)
        check(s, zero, 0.0, "Bishop 0–6")
        val six = mapOf("dilation" to 2, "effacement" to 2, "station" to 1, "consistency" to 1, "position" to 0)
        check(s, six, 6.0, "Bishop 0–6")
        check(s, six + ("position" to 1), 7.0, "Bishop 7–13")
        check(s, mapOf("dilation" to 3, "effacement" to 3, "station" to 3, "consistency" to 2, "position" to 2), 13.0, "Bishop 7–13")
    }

    @Test
    fun ottawaAnkle() {
        val s = score("ottawa-ankle")
        assertFalse(s.showTotal)
        val no = "Ankle X-ray not required by the rule"
        val yes = "Ankle X-ray indicated"
        check(s, emptyMap(), 0.0, no)
        check(s, mapOf("malleolarPain" to 1), 0.0, no) // pain alone
        check(s, mapOf("lateral" to 1, "weightBearing" to 1), 0.0, no) // findings without malleolar pain
        check(s, mapOf("malleolarPain" to 1, "medial" to 1), 1.0, yes)
        check(s, mapOf("malleolarPain" to 1, "weightBearing" to 1), 1.0, yes)
        check(s, mapOf("malleolarPain" to 1, "lateral" to 1, "medial" to 1, "weightBearing" to 1), 3.0, yes)
    }

    @Test
    fun ottawaFoot() {
        val s = score("ottawa-foot")
        assertFalse(s.showTotal)
        val no = "Foot X-ray not required by the rule"
        val yes = "Foot X-ray indicated"
        check(s, emptyMap(), 0.0, no)
        check(s, mapOf("midfootPain" to 1), 0.0, no)
        check(s, mapOf("navicular" to 1, "fifthMetatarsal" to 1), 0.0, no)
        check(s, mapOf("midfootPain" to 1, "fifthMetatarsal" to 1), 1.0, yes)
        check(s, mapOf("midfootPain" to 1, "navicular" to 1, "fifthMetatarsal" to 1, "weightBearing" to 1), 3.0, yes)
    }

    @Test
    fun canadianCtHead() {
        val s = score("canadian-ct-head")
        assertFalse(s.showTotal)
        check(s, emptyMap(), 0.0, "CT not required by the rule")
        check(s, mapOf("amnesia" to 1), 1.0, "CT recommended (medium risk)")
        check(s, mapOf("amnesia" to 1, "mechanism" to 1), 2.0, "CT recommended (medium risk)")
        check(s, mapOf("age65" to 1), 10.0, "CT recommended (high risk)")
        check(s, mapOf("vomiting" to 1, "mechanism" to 1), 11.0, "CT recommended (high risk)")
        check(s, s.items.associate { it.id to 1 }, 52.0, "CT recommended (high risk)")
    }

    // ---------------------------------------------------------------------------------------------
    // Exhaustive checks: every complete set of answers gets a result and a band; published maxima.
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
    fun everyAnswerSetHasABandAndMaximaMatchPublishedValues() {
        val publishedMax = mapOf(
            "curb-65" to 5.0, "news2" to 20.0, "alvarado" to 10.0, "air" to 12.0, "glasgow-blatchford" to 23.0,
            "child-pugh" to 15.0, "bishop" to 13.0, "centor-mcisaac" to 5.0,
        )
        ids.forEach { id ->
            val s = score(id)
            var max = Double.NEGATIVE_INFINITY
            forEachAnswerSet(s.items, 0, mutableMapOf()) { answers ->
                // Messages are built only on failure: NEWS2 alone has 192,000 combinations.
                val t = ScoreEngine.total(s, answers) ?: fail("$id: no result for $answers")
                if (ScoreEngine.band(s, t) == null) fail("$id: no band for total $t ($answers)")
                if (t > max) max = t
            }
            publishedMax[id]?.let { assertEquals(it, max, "$id maximum") }
        }
    }

    @Test
    fun scoresAreRegisteredAndCited() {
        ids.forEach { id ->
            val s = score(id)
            assertTrue(s.citations.isNotEmpty(), "$id has no citation")
            assertEquals(s.bands.size, s.bands.map { it.title }.toSet().size, "$id has duplicate band titles")
            s.bands.forEach { assertTrue(it.min <= it.max, "$id band ${it.title}") }
        }
    }
}
