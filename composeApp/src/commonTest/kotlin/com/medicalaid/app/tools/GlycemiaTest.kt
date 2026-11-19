package com.medicalaid.app.tools

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GlycemiaTest {
    private fun close(expected: Double, actual: Double, tol: Double = 0.05) =
        assertTrue(abs(expected - actual) <= tol, "expected $expected but was $actual")

    @Test
    fun adagTableValues() {
        // ADA eAG table: 6% ≈ 126 mg/dL (7.0 mmol/L), 7% ≈ 154 (8.6), 8% ≈ 183 (10.2), 10% ≈ 240 (13.4)
        close(125.5, Glycemia.eagMgDl(6.0))
        close(154.2, Glycemia.eagMgDl(7.0))
        close(182.9, Glycemia.eagMgDl(8.0))
        close(240.3, Glycemia.eagMgDl(10.0))
        close(6.95, Glycemia.eagMmol(6.0))
        close(13.31, Glycemia.eagMmol(10.0))
    }

    @Test
    fun inverseRoundTrips() {
        for (a1c in listOf(5.0, 6.5, 7.0, 9.0, 12.0)) {
            close(a1c, Glycemia.a1cFromMgDl(Glycemia.eagMgDl(a1c)), 1e-9)
            close(a1c, Glycemia.a1cFromMmol(Glycemia.eagMmol(a1c)), 1e-9)
        }
        close(7.0, Glycemia.a1cFromMgDl(154.2))
    }

    @Test
    fun icmrTherapyBands() {
        assertEquals(Glycemia.IcmrTherapyBand.Monotherapy, Glycemia.icmrTherapyBand(8.4))
        assertEquals(Glycemia.IcmrTherapyBand.Dual, Glycemia.icmrTherapyBand(8.5))
        assertEquals(Glycemia.IcmrTherapyBand.Dual, Glycemia.icmrTherapyBand(10.0))
        assertEquals(Glycemia.IcmrTherapyBand.Intensive, Glycemia.icmrTherapyBand(10.1))
    }

    @Test
    fun adaBands() {
        assertEquals(Glycemia.A1cBand.Normal, Glycemia.band(5.6))
        assertEquals(Glycemia.A1cBand.Prediabetes, Glycemia.band(5.7))
        assertEquals(Glycemia.A1cBand.Prediabetes, Glycemia.band(6.4))
        assertEquals(Glycemia.A1cBand.Diabetes, Glycemia.band(6.5))
    }
}
