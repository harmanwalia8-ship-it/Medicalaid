package com.medicalaid.app.tools

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BurnsAcidBaseTest {
    private fun close(e: Double, a: Double, tol: Double = 1e-9) = assertTrue(abs(e - a) <= tol, "expected $e but was $a")

    @Test
    fun lundBrowderColumnsSumTo100() {
        Burns.AgeBand.entries.forEach { age ->
            close(100.0, Burns.regions.sumOf { it.pct(age) })
        }
    }

    @Test
    fun everyRegionIsFullyCoveredByItsViews() {
        Burns.regions.forEach { r -> close(1.0, r.shares.values.sum()) }
    }

    @Test
    fun tbsaFromMarks() {
        val adult = Burns.AgeBand.Adult
        // Whole anterior trunk + front half of both thighs (full) = 13 + 9.5
        val marks = mapOf(
            Burns.key("ant-trunk", Burns.Side.Front) to 1.0,
            Burns.key("r-thigh", Burns.Side.Front) to 1.0,
            Burns.key("l-thigh", Burns.Side.Front) to 1.0,
        )
        close(22.5, Burns.tbsa(adult, marks))
        // Half-marked head front in an infant: 19 × 0.5 × 0.5
        close(4.75, Burns.tbsa(Burns.AgeBand.Infant, mapOf(Burns.key("head", Burns.Side.Front) to 0.5)))
    }

    @Test
    fun parkland() {
        val p = Burns.fluids(Burns.PARKLAND_ML, 70.0, 30.0)
        close(8400.0, p.total24hMl)
        close(4200.0, p.first8hMl)
        close(525.0, p.first8hRateMlPerH)
        close(262.5, p.next16hRateMlPerH)
    }

    @Test
    fun lundBrowderValuesMatchJtsAppendicesBandC() {
        fun r(id: String) = Burns.regions.first { it.id == id }
        assertEquals(listOf(19.0, 17.0, 13.0, 11.0, 9.0, 7.0), r("head").pctByAge)
        assertEquals(listOf(5.5, 6.5, 8.0, 8.5, 9.0, 9.5), r("r-thigh").pctByAge)
        assertEquals(listOf(5.0, 5.0, 5.5, 6.0, 6.5, 7.0), r("l-leg").pctByAge)
        // Hands 2.5% each (1.25% per side), not 1.5% per side.
        close(1.25, r("r-hand").pct(Burns.AgeBand.Adult) * r("r-hand").shares.getValue(Burns.Side.Front))
        // Adult one-side totals (JTS Appendix B): anterior 48, posterior 52.
        val adult = Burns.AgeBand.Adult
        close(48.0, Burns.regions.sumOf { (it.shares[Burns.Side.Front] ?: 0.0) * it.pct(adult) })
        close(52.0, Burns.regions.sumOf { (it.shares[Burns.Side.Back] ?: 0.0) * it.pct(adult) })
    }

    @Test
    fun abaStartingRate() {
        val p = Burns.fluids(Burns.ABLS_ADULT_ML, 70.0, 30.0)
        close(4200.0, p.total24hMl)
        close(2100.0, p.first8hMl)
        close(262.5, p.first8hRateMlPerH)
        close(131.25, p.next16hRateMlPerH)
    }

    @Test
    fun primaryDisorderUsesBerendTable1Criteria() {
        assertEquals(AcidBase.Primary.MetabolicAcidosis, AcidBase.primary(7.20, 25.0, 10.0))
        assertEquals(AcidBase.Primary.RespiratoryAcidosis, AcidBase.primary(7.25, 60.0, 26.0))
        assertEquals(AcidBase.Primary.MixedAcidosis, AcidBase.primary(7.10, 55.0, 16.0))
        assertEquals(AcidBase.Primary.MetabolicAlkalosis, AcidBase.primary(7.50, 48.0, 36.0))
        assertEquals(AcidBase.Primary.RespiratoryAlkalosis, AcidBase.primary(7.50, 28.0, 21.0))
        assertEquals(AcidBase.Primary.MixedAlkalosis, AcidBase.primary(7.55, 34.0, 30.0))
        // pH < 7.38 is acidaemia in Berend (not 7.35).
        assertEquals(AcidBase.Primary.MetabolicAcidosis, AcidBase.primary(7.37, 33.0, 19.0))
        assertEquals(AcidBase.Primary.NormalPh, AcidBase.primary(7.40, 40.0, 24.0))
        assertEquals(AcidBase.Primary.NotClassified, AcidBase.primary(7.30, 40.0, 24.0))
        // PaCO2 43 (> 42) with HCO3 < 22 counts as a respiratory component.
        assertEquals(AcidBase.Primary.MixedAcidosis, AcidBase.primary(7.30, 43.0, 21.0))
    }

    @Test
    fun secondaryResponsesFollowBerendTable1() {
        val w = AcidBase.wintersPco2(10.0)
        close(23.0, w.value); close(21.0, w.low); close(25.0, w.high)
        // Metabolic alkalosis: 0.7 × (HCO3 − 24) + 40 ± 2
        val m = AcidBase.alkalosisPco2(36.0)
        close(48.4, m.value); close(46.4, m.low); close(50.4, m.high)
        // Respiratory acidosis: +1 (acute) or +4–5 (chronic) per 10 mm Hg, ±2
        val aa = AcidBase.respAcidosisHco3(60.0, chronic = false)
        close(26.0, aa.value); close(24.0, aa.low); close(28.0, aa.high)
        val ca = AcidBase.respAcidosisHco3(60.0, chronic = true)
        close(30.0, ca.low); close(36.0, ca.high); close(33.0, ca.value)
        // Respiratory alkalosis: −2 (acute) or −4–5 (chronic) per 10 mm Hg, ±2
        val al = AcidBase.respAlkalosisHco3(30.0, chronic = false)
        close(22.0, al.value); close(20.0, al.low); close(24.0, al.high)
        val cl = AcidBase.respAlkalosisHco3(30.0, chronic = true)
        close(17.0, cl.low); close(22.0, cl.high); close(19.5, cl.value)
    }

    @Test
    fun anionGapAndDelta() {
        val ag = AcidBase.anionGap(140.0, 100.0, 10.0)
        close(30.0, ag)
        close(35.0, AcidBase.correctedAnionGap(ag, 2.0))
        close((30.0 - 12.0) / 14.0, AcidBase.deltaRatio(30.0, 10.0)!!)
        assertNull(AcidBase.deltaRatio(14.0, 24.0))
        // Normal anion gap: Δ–Δ does not apply.
        assertNull(AcidBase.deltaRatio(12.0, 15.0))
        assertTrue(AcidBase.deltaRatioMeaning(0.8).startsWith("below 1"))
        assertTrue(AcidBase.deltaRatioMeaning(1.0).startsWith("between 1"))
        assertTrue(AcidBase.deltaRatioMeaning(1.6).startsWith("between 1"))
        assertTrue(AcidBase.deltaRatioMeaning(1.7).startsWith("above about 1.7"))
    }
}
