package com.medicalaid.app.content.scores

import com.medicalaid.app.content.scores.data.neuroCardioScoreSources
import com.medicalaid.app.content.scores.data.neuroCardioScores
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/** Worked examples (answers → total and band), band boundaries and published maxima for the neuro/cardio batch. */
class NeuroCardioScoresTest {

    private fun score(id: String): Score = Scores.byId(id) ?: error("Score '$id' is not registered")

    private fun check(id: String, answers: Map<String, Int>, total: Double, band: String) {
        val s = score(id)
        val t = ScoreEngine.total(s, answers)
        assertEquals(total, t, "$id total for $answers")
        assertEquals(band, ScoreEngine.band(s, t!!)?.title, "$id band for total $t")
    }

    /** Ticks every positive criterion and picks the highest-scoring option of every choice. */
    private fun maxAnswers(s: Score): Map<String, Int> = s.items.associate { item ->
        item.id to when (item) {
            is Criterion -> if (item.points > 0) 1 else 0
            is Choice -> item.options.indices.maxByOrNull { item.options[it].points }!!
        }
    }

    /** Ticks every negative criterion and picks the lowest-scoring option of every choice. */
    private fun minAnswers(s: Score): Map<String, Int> = s.items.associate { item ->
        item.id to when (item) {
            is Criterion -> if (item.points < 0) 1 else 0
            is Choice -> item.options.indices.minByOrNull { item.options[it].points }!!
        }
    }

    private fun hasFractionalPoints(s: Score) = s.items.any { item ->
        when (item) {
            is Criterion -> item.points % 1.0 != 0.0
            is Choice -> item.options.any { it.points % 1.0 != 0.0 }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Batch-wide checks
    // ---------------------------------------------------------------------------------------------

    @Test
    fun batchIsRegisteredWithoutIdClashes() {
        val expected = listOf(
            "gcs", "nihss", "abcd2", "rosier", "cha2ds2-vasc", "has-bled",
            "wells-dvt", "wells-pe", "perc", "heart", "timi-ua-nstemi",
        )
        assertEquals(expected, neuroCardioScores.map { it.id })
        neuroCardioScores.forEach {
            assertTrue(Scores.byId(it.id) === it, "Score id '${it.id}' is shadowed by another batch")
        }
    }

    @Test
    fun sourcesArePrefixedUniqueHttpsAndAllCited() {
        val ids = neuroCardioScoreSources.map { it.id }
        assertEquals(ids.size, ids.toSet().size, "Duplicate source id")
        ids.forEach { assertTrue(it.startsWith("sc-") && it == it.lowercase() && !it.contains(' '), "Bad source id '$it'") }
        neuroCardioScoreSources.forEach { assertTrue(it.url.startsWith("https://"), "${it.id} is not https") }
        val cited = neuroCardioScores
            .flatMap { s -> s.citations + s.caveats.flatMap { it.citations } }
            .map { it.sourceId }
            .toSet()
        assertEquals(ids.toSet(), cited, "Every batch source is cited, and every citation resolves within the batch")
    }

    @Test
    fun publishedMaxima() {
        val expected = mapOf(
            "gcs" to 15.0, "nihss" to 42.0, "abcd2" to 7.0, "rosier" to 5.0, "cha2ds2-vasc" to 9.0,
            "has-bled" to 9.0, "wells-dvt" to 9.0, "wells-pe" to 12.5, "perc" to 8.0, "heart" to 10.0,
            "timi-ua-nstemi" to 7.0,
        )
        expected.forEach { (id, max) ->
            val s = score(id)
            assertEquals(max, ScoreEngine.total(s, maxAnswers(s)), "$id published maximum")
        }
    }

    @Test
    fun publishedMinima() {
        val expected = mapOf(
            "gcs" to 3.0, "nihss" to 0.0, "abcd2" to 0.0, "rosier" to -2.0, "cha2ds2-vasc" to 0.0,
            "has-bled" to 0.0, "wells-dvt" to -2.0, "wells-pe" to 0.0, "perc" to 0.0, "heart" to 0.0,
            "timi-ua-nstemi" to 0.0,
        )
        expected.forEach { (id, min) ->
            val s = score(id)
            assertEquals(min, ScoreEngine.total(s, minAnswers(s)), "$id minimum")
        }
    }

    @Test
    fun everyTotalInRangeFallsInExactlyOneBand() {
        neuroCardioScores.forEach { s ->
            val lo = ScoreEngine.total(s, minAnswers(s))!!
            val hi = ScoreEngine.total(s, maxAnswers(s))!!
            val step = if (hasFractionalPoints(s)) 0.5 else 1.0
            var v = lo
            while (v <= hi + 1e-9) {
                val matches = s.bands.count { v >= it.min - 1e-9 && v <= it.max + 1e-9 }
                assertEquals(1, matches, "${s.id}: total $v falls in $matches bands")
                v += step
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Glasgow Coma Scale — option indices run from best (0) to worst response.
    // ---------------------------------------------------------------------------------------------

    @Test
    fun gcs() {
        assertNull(ScoreEngine.total(score("gcs"), mapOf("eye" to 0, "verbal" to 0)), "motor unanswered")
        check("gcs", mapOf("eye" to 0, "verbal" to 0, "motor" to 0), 15.0, "GCS 13–15") // E4 V5 M6
        check("gcs", mapOf("eye" to 1, "verbal" to 1, "motor" to 0), 13.0, "GCS 13–15") // E3 V4 M6
        check("gcs", mapOf("eye" to 1, "verbal" to 2, "motor" to 0), 12.0, "GCS 9–12") // E3 V3 M6
        check("gcs", mapOf("eye" to 2, "verbal" to 3, "motor" to 1), 9.0, "GCS 9–12") // E2 V2 M5
        check("gcs", mapOf("eye" to 2, "verbal" to 3, "motor" to 2), 8.0, "GCS 3–8") // E2 V2 M4
        check("gcs", mapOf("eye" to 3, "verbal" to 4, "motor" to 5), 3.0, "GCS 3–8") // E1 V1 M1
    }

    // ---------------------------------------------------------------------------------------------
    // NIHSS — option index = points, except UN (index 5 for limbs, 3 for ataxia/dysarthria) = 0.
    // ---------------------------------------------------------------------------------------------

    private val nihssIds = listOf(
        "loc", "loc_questions", "loc_commands", "gaze", "visual", "facial",
        "arm_left", "arm_right", "leg_left", "leg_right", "ataxia", "sensory", "language", "dysarthria", "extinction",
    )

    @Test
    fun nihss() {
        val s = score("nihss")
        assertEquals(nihssIds, s.items.map { it.id }, "15 NIHSS items in form order")
        val normal = nihssIds.associateWith { 0 }
        check("nihss", normal, 0.0, "NIHSS total")
        assertNull(ScoreEngine.total(s, normal - "extinction"), "all items must be answered")

        // Left hemisphere stroke: right hemiparesis, hemianopia, aphasia.
        val leftHemisphere = normal + mapOf(
            "loc_questions" to 2, "loc_commands" to 1, "gaze" to 1, "visual" to 2, "facial" to 2,
            "arm_right" to 3, "leg_right" to 2, "sensory" to 1, "language" to 2, "dysarthria" to 1,
        )
        check("nihss", leftHemisphere, 17.0, "NIHSS total")

        // Untestable items add 0 and raise a single (deduplicated) alert.
        val untestable = normal + mapOf("arm_left" to 5, "ataxia" to 3, "dysarthria" to 3)
        check("nihss", untestable, 0.0, "NIHSS total")
        assertEquals(1, ScoreEngine.alerts(s, untestable).size)

        // Coma (1a = 3): items 8 and 9 auto-scored 2 and 3; the alert says so.
        val coma = normal + mapOf("loc" to 3, "sensory" to 2, "language" to 3)
        check("nihss", coma, 8.0, "NIHSS total")
        assertTrue(ScoreEngine.alerts(s, coma).any { it.contains("1a = 3") })
    }

    // ---------------------------------------------------------------------------------------------
    // ABCD² — clinical: 0 other, 1 speech without weakness, 2 unilateral weakness;
    //         duration: 0 <10 min, 1 10–59 min, 2 ≥60 min.
    // ---------------------------------------------------------------------------------------------

    @Test
    fun abcd2() {
        assertNull(ScoreEngine.total(score("abcd2"), mapOf("age" to 1, "clinical" to 2)), "duration unanswered")
        check("abcd2", mapOf("clinical" to 0, "duration" to 0), 0.0, "ABCD² 0–3")
        check("abcd2", mapOf("bp" to 1, "clinical" to 1, "duration" to 1), 3.0, "ABCD² 0–3")
        check("abcd2", mapOf("age" to 1, "bp" to 1, "clinical" to 1, "duration" to 1), 4.0, "ABCD² 4–5")
        check("abcd2", mapOf("age" to 1, "bp" to 1, "clinical" to 2, "duration" to 1), 5.0, "ABCD² 4–5")
        check("abcd2", mapOf("age" to 1, "bp" to 1, "clinical" to 2, "duration" to 1, "diabetes" to 1), 6.0, "ABCD² 6–7")
        check("abcd2", mapOf("age" to 1, "bp" to 1, "clinical" to 2, "duration" to 2, "diabetes" to 1), 7.0, "ABCD² 6–7")
    }

    // ---------------------------------------------------------------------------------------------
    // ROSIER — negative points; stroke likely only if total > 0.
    // ---------------------------------------------------------------------------------------------

    @Test
    fun rosier() {
        check("rosier", mapOf("loc" to 1, "seizure" to 1), -2.0, "ROSIER ≤0")
        check("rosier", emptyMap(), 0.0, "ROSIER ≤0")
        check("rosier", mapOf("seizure" to 1, "arm" to 1), 0.0, "ROSIER ≤0")
        check("rosier", mapOf("loc" to 1, "face" to 1, "speech" to 1), 1.0, "ROSIER >0")
        check("rosier", mapOf("face" to 1, "arm" to 1, "leg" to 1, "speech" to 1, "visual" to 1), 5.0, "ROSIER >0")
    }

    // ---------------------------------------------------------------------------------------------
    // CHA₂DS₂-VASc — sex: 0 male, 1 female; age: 0 <65, 1 65–74, 2 ≥75.
    // ---------------------------------------------------------------------------------------------

    @Test
    fun cha2ds2Vasc() {
        val id = "cha2ds2-vasc"
        assertNull(ScoreEngine.total(score(id), mapOf("sex" to 0)), "age unanswered")
        check(id, mapOf("sex" to 0, "age" to 0), 0.0, "CHA₂DS₂-VASc 0")
        check(id, mapOf("sex" to 1, "age" to 0), 1.0, "CHA₂DS₂-VASc 1")
        check(id, mapOf("sex" to 0, "age" to 0, "htn" to 1), 1.0, "CHA₂DS₂-VASc 1")
        check(id, mapOf("sex" to 0, "age" to 1, "htn" to 1), 2.0, "CHA₂DS₂-VASc 2")
        check(id, mapOf("sex" to 0, "age" to 0, "stroke" to 1), 2.0, "CHA₂DS₂-VASc 2")
        check(id, mapOf("sex" to 1, "age" to 2), 3.0, "CHA₂DS₂-VASc ≥3")
        check(
            id,
            mapOf("sex" to 1, "age" to 2, "chf" to 1, "htn" to 1, "diabetes" to 1, "stroke" to 1, "vascular" to 1),
            9.0, "CHA₂DS₂-VASc ≥3",
        )
    }

    // ---------------------------------------------------------------------------------------------
    // HAS-BLED
    // ---------------------------------------------------------------------------------------------

    @Test
    fun hasBled() {
        check("has-bled", emptyMap(), 0.0, "HAS-BLED 0")
        check("has-bled", mapOf("elderly" to 1), 1.0, "HAS-BLED 1–2")
        check("has-bled", mapOf("elderly" to 1, "drugs" to 1), 2.0, "HAS-BLED 1–2")
        check("has-bled", mapOf("elderly" to 1, "drugs" to 1, "htn" to 1), 3.0, "HAS-BLED ≥3")
        val all = listOf("htn", "renal", "liver", "stroke", "bleeding", "inr", "elderly", "drugs", "alcohol").associateWith { 1 }
        check("has-bled", all, 9.0, "HAS-BLED ≥3")
    }

    // ---------------------------------------------------------------------------------------------
    // Wells DVT (2-level, NICE NG158): likely ≥2, unlikely ≤1.
    // ---------------------------------------------------------------------------------------------

    @Test
    fun wellsDvt() {
        val id = "wells-dvt"
        check(id, emptyMap(), 0.0, "DVT unlikely (≤1)")
        check(id, mapOf("alternative" to 1), -2.0, "DVT unlikely (≤1)")
        check(id, mapOf("cancer" to 1), 1.0, "DVT unlikely (≤1)")
        check(id, mapOf("cancer" to 1, "swollen_leg" to 1), 2.0, "DVT likely (≥2)")
        check(id, mapOf("cancer" to 1, "swollen_leg" to 1, "alternative" to 1), 0.0, "DVT unlikely (≤1)")
        check(id, mapOf("cancer" to 1, "swollen_leg" to 1, "calf" to 1, "alternative" to 1), 1.0, "DVT unlikely (≤1)")
        val all = listOf(
            "cancer", "paralysis", "bedridden", "tenderness", "swollen_leg", "calf", "pitting", "collaterals", "prior_dvt",
        ).associateWith { 1 }
        check(id, all, 9.0, "DVT likely (≥2)")
        check(id, all + ("alternative" to 1), 7.0, "DVT likely (≥2)")
    }

    // ---------------------------------------------------------------------------------------------
    // Wells PE (2-level, NICE NG158): likely >4, unlikely ≤4.
    // ---------------------------------------------------------------------------------------------

    @Test
    fun wellsPe() {
        val id = "wells-pe"
        check(id, emptyMap(), 0.0, "PE unlikely (≤4)")
        check(id, mapOf("hr" to 1, "immobilisation" to 1, "haemoptysis" to 1), 4.0, "PE unlikely (≤4)")
        check(id, mapOf("hr" to 1, "immobilisation" to 1, "prior_vte" to 1), 4.5, "PE likely (>4)")
        check(id, mapOf("dvt_signs" to 1, "malignancy" to 1), 4.0, "PE unlikely (≤4)")
        check(id, mapOf("dvt_signs" to 1, "hr" to 1), 4.5, "PE likely (>4)")
        val all = listOf(
            "dvt_signs", "alternative", "hr", "immobilisation", "prior_vte", "haemoptysis", "malignancy",
        ).associateWith { 1 }
        check(id, all, 12.5, "PE likely (>4)")
    }

    // ---------------------------------------------------------------------------------------------
    // PERC — negative only when none of the 8 criteria is present.
    // ---------------------------------------------------------------------------------------------

    @Test
    fun perc() {
        val s = score("perc")
        assertFalse(s.showTotal, "PERC is a rule; the number is not shown")
        assertEquals(8, s.items.size)
        check("perc", emptyMap(), 0.0, "PERC negative")
        check("perc", mapOf("age" to 1), 1.0, "PERC positive")
        check("perc", mapOf("hormone" to 1, "sao2" to 1), 2.0, "PERC positive")
        check("perc", s.items.associate { it.id to 1 }, 8.0, "PERC positive")
    }

    // ---------------------------------------------------------------------------------------------
    // HEART — option index = points for every element.
    // ---------------------------------------------------------------------------------------------

    @Test
    fun heart() {
        val id = "heart"
        val zero = mapOf("history" to 0, "ecg" to 0, "age" to 0, "risk" to 0, "troponin" to 0)
        assertNull(ScoreEngine.total(score(id), zero - "troponin"), "troponin unanswered")
        check(id, zero, 0.0, "HEART 0–3")
        check(id, mapOf("history" to 1, "ecg" to 1, "age" to 1, "risk" to 0, "troponin" to 0), 3.0, "HEART 0–3")
        check(id, mapOf("history" to 1, "ecg" to 1, "age" to 1, "risk" to 1, "troponin" to 0), 4.0, "HEART 4–6")
        check(id, mapOf("history" to 2, "ecg" to 1, "age" to 2, "risk" to 1, "troponin" to 0), 6.0, "HEART 4–6")
        check(id, mapOf("history" to 2, "ecg" to 1, "age" to 2, "risk" to 1, "troponin" to 1), 7.0, "HEART 7–10")
        check(id, mapOf("history" to 2, "ecg" to 2, "age" to 2, "risk" to 2, "troponin" to 2), 10.0, "HEART 7–10")
    }

    // ---------------------------------------------------------------------------------------------
    // TIMI UA/NSTEMI — one point per criterion; Antman 2000 strata 0–1, 2, 3, 4, 5, 6–7.
    // ---------------------------------------------------------------------------------------------

    @Test
    fun timi() {
        val id = "timi-ua-nstemi"
        check(id, emptyMap(), 0.0, "TIMI 0–1")
        check(id, mapOf("age" to 1), 1.0, "TIMI 0–1")
        check(id, mapOf("age" to 1, "aspirin" to 1), 2.0, "TIMI 2")
        check(id, mapOf("age" to 1, "aspirin" to 1, "st" to 1), 3.0, "TIMI 3")
        check(id, mapOf("age" to 1, "aspirin" to 1, "st" to 1, "markers" to 1), 4.0, "TIMI 4")
        check(id, mapOf("age" to 1, "aspirin" to 1, "st" to 1, "markers" to 1, "angina" to 1), 5.0, "TIMI 5")
        check(
            id,
            mapOf("age" to 1, "aspirin" to 1, "st" to 1, "markers" to 1, "angina" to 1, "known_cad" to 1),
            6.0, "TIMI 6–7",
        )
        check(id, score(id).items.associate { it.id to 1 }, 7.0, "TIMI 6–7")
    }
}
