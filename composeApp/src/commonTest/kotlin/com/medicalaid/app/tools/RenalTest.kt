package com.medicalaid.app.tools

import com.medicalaid.app.tools.Renal.GfrCategory
import com.medicalaid.app.tools.Renal.Sex
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RenalTest {
    private fun close(expected: Double, actual: Double, tol: Double) =
        assertTrue(abs(expected - actual) <= tol, "expected $expected but was $actual")

    @Test
    fun ckdEpi2021() {
        // Hand-computed from the NKF formula.
        close(68.6, Renal.ckdEpi2021(1.0, 50, Sex.Female), 0.3)
        close(91.7, Renal.ckdEpi2021(1.0, 50, Sex.Male), 0.3)
        // Below kappa the alpha term applies: female 40y, Scr 0.6 → ratio 0.857.
        val expected = 142.0 * Math.pow(0.6 / 0.7, -0.241) * Math.pow(0.9938, 40.0) * 1.012
        close(expected, Renal.ckdEpi2021(0.6, 40, Sex.Female), 1e-9)
        // Higher creatinine and age lower eGFR.
        assertTrue(Renal.ckdEpi2021(2.0, 70, Sex.Male) < Renal.ckdEpi2021(1.2, 70, Sex.Male))
    }

    @Test
    fun cockcroftGault() {
        close(77.8, Renal.cockcroftGault(1.0, 60, 70.0, Sex.Male), 0.1)
        close(66.1, Renal.cockcroftGault(1.0, 60, 70.0, Sex.Female), 0.1)
    }

    @Test
    fun bsaAndNonIndexed() {
        val bsa = Renal.bsaMosteller(170.0, 70.0)
        close(1.818, bsa, 0.001)
        close(90.0 * 1.818 / 1.73, Renal.nonIndexedEgfr(90.0, bsa), 0.01)
    }

    @Test
    fun kdigoCategories() {
        assertEquals(GfrCategory.G1, Renal.category(90.0))
        assertEquals(GfrCategory.G2, Renal.category(89.9))
        assertEquals(GfrCategory.G3a, Renal.category(45.0))
        assertEquals(GfrCategory.G3b, Renal.category(44.9))
        assertEquals(GfrCategory.G4, Renal.category(15.0))
        assertEquals(GfrCategory.G5, Renal.category(14.9))
    }
}
