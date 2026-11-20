package com.medicalaid.app.content

import com.medicalaid.app.ui.medicines.GuidelineGroup
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GuidelineFilterTest {
    @Test
    fun prefixes() {
        assertEquals(GuidelineGroup.India, GuidelineGroup.of("ICMR: hypertension = SBP ≥140"))
        assertEquals(GuidelineGroup.India, GuidelineGroup.of("India · IHCI protocol (ICMR STW 2026)"))
        assertEquals(GuidelineGroup.Nice, GuidelineGroup.of("NICE NG136 · stepwise"))
        assertEquals(GuidelineGroup.AccAha, GuidelineGroup.of("AHA/ACC 2025: ≥130/80"))
        assertEquals(GuidelineGroup.AccAha, GuidelineGroup.of("ACC/AHA 2023: anticoagulate at ≥2"))
        assertEquals(GuidelineGroup.Esc, GuidelineGroup.of("ESC/EAS 2025 · when to start"))
        assertEquals(GuidelineGroup.Kdigo, GuidelineGroup.of("KDIGO: SBP <120"))
        assertNull(GuidelineGroup.of("ACE inhibitor or ARB"))
        assertNull(GuidelineGroup.of("Statin doses and intensity"))
    }

    @Test
    fun showsUnprefixedAlways() {
        assertTrue(GuidelineGroup.shows("Before starting: check potassium", GuidelineGroup.Nice))
        assertFalse(GuidelineGroup.shows("ESC 2024: target 120–129", GuidelineGroup.India))
        assertTrue(GuidelineGroup.shows("ESC 2024: target 120–129", null))
    }
}
