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
    fun acidBase() {
        assertEquals(AcidBase.Primary.MetabolicAcidosis, AcidBase.primary(7.20, 25.0, 10.0))
        assertEquals(AcidBase.Primary.RespiratoryAcidosis, AcidBase.primary(7.25, 60.0, 26.0))
        assertEquals(AcidBase.Primary.MixedAcidosis, AcidBase.primary(7.10, 55.0, 16.0))
        assertEquals(AcidBase.Primary.MetabolicAlkalosis, AcidBase.primary(7.50, 48.0, 36.0))
        assertEquals(AcidBase.Primary.RespiratoryAlkalosis, AcidBase.primary(7.50, 28.0, 21.0))
        close(23.0, AcidBase.wintersPco2(10.0).value)
        close(46.2, AcidBase.alkalosisPco2(36.0).value, 1e-9)
        close(26.0, AcidBase.respAcidosisHco3(60.0, chronic = false).value)
        close(31.0, AcidBase.respAcidosisHco3(60.0, chronic = true).value)
        close(22.0, AcidBase.respAlkalosisHco3(30.0, chronic = false).value)
        close(19.0, AcidBase.respAlkalosisHco3(30.0, chronic = true).value)
        val ag = AcidBase.anionGap(140.0, 100.0, 10.0)
        close(30.0, ag)
        close(35.0, AcidBase.correctedAnionGap(ag, 2.0))
        close((30.0 - 12.0) / 14.0, AcidBase.deltaRatio(30.0, 10.0)!!)
        assertNull(AcidBase.deltaRatio(14.0, 24.0))
    }
}
