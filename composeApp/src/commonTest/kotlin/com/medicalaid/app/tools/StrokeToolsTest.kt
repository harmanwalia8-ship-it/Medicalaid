package com.medicalaid.app.tools

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StrokeToolsTest {
    private fun close(e: Double, a: Double) = assertTrue(abs(e - a) < 1e-6, "expected $e but was $a")

    @Test
    fun alteplase() {
        val a = StrokeTools.alteplase(70.0)
        close(63.0, a.totalMg); close(6.3, a.bolusMg); close(56.7, a.infusionMg)
        close(90.0, StrokeTools.alteplase(120.0).totalMg)
    }

    @Test
    fun tenecteplaseAhaBands() {
        assertEquals(15.0, StrokeTools.tenecteplaseAhaBandMg(55.0))
        assertEquals(17.5, StrokeTools.tenecteplaseAhaBandMg(60.0))
        assertEquals(20.0, StrokeTools.tenecteplaseAhaBandMg(79.9))
        assertEquals(22.5, StrokeTools.tenecteplaseAhaBandMg(80.0))
        assertEquals(25.0, StrokeTools.tenecteplaseAhaBandMg(110.0))
    }

    @Test
    fun tenecteplaseIndia() {
        val (lo, hi) = StrokeTools.tenecteplaseIndiaRangeMg(60.0)
        close(12.0, lo); close(15.0, hi)
        val (lo2, hi2) = StrokeTools.tenecteplaseIndiaRangeMg(100.0)
        close(20.0, lo2); close(20.0, hi2) // capped at 20 mg
    }

    @Test
    fun windows() {
        val ivt = StrokeTools.windows.first()
        assertEquals(StrokeTools.WindowState.Open, StrokeTools.state(ivt, 4.5))
        assertEquals(StrokeTools.WindowState.Closed, StrokeTools.state(ivt, 4.6))
        val ext = StrokeTools.windows.last()
        assertEquals(StrokeTools.WindowState.NotYet, StrokeTools.state(ext, 3.0))
        assertEquals(StrokeTools.WindowState.Open, StrokeTools.state(ext, 12.0))
    }
}
