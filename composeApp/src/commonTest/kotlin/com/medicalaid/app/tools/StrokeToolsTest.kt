package com.medicalaid.app.tools

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StrokeToolsTest {
    private fun close(e: Double, a: Double) = assertTrue(abs(e - a) < 1e-6, "expected $e but was $a")

    @Test
    fun alteplaseAndTenecteplase() {
        val t = StrokeTools.thrombolysis(70.0)
        close(63.0, t.alteplaseTotalMg)
        close(6.3, t.alteplaseBolusMg)
        close(56.7, t.alteplaseInfusionMg)
        close(17.5, t.tenecteplaseMg)
        // Caps
        val heavy = StrokeTools.thrombolysis(120.0)
        close(90.0, heavy.alteplaseTotalMg)
        close(25.0, heavy.tenecteplaseMg)
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
