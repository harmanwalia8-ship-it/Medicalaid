package com.medicalaid.app.content.protocols

import com.medicalaid.app.content.WeightDose
import kotlin.test.Test
import kotlin.test.assertEquals

class StepDoseTest {
    @Test
    fun rangeAndCaps() {
        val plain = StepDose("x", WeightDose(30.0, 30.0, unit = "mg", per = "IV over 30 min"))
        assertEquals(1800.0 to 1800.0, plain.range(60.0))

        val capped = StepDose("y", WeightDose(150.0, 150.0, unit = "mg", per = "over 1 h"), maxTotal = 16500.0)
        assertEquals(16500.0 to 16500.0, capped.range(120.0)) // 150 × 120 = 18000 → capped

        val ceiling = StepDose("z", WeightDose(10.0, 15.0, unit = "mg", per = "per day", ceilingPerKg = 12.0))
        assertEquals(500.0 to 600.0, ceiling.range(50.0)) // high limited by 12 mg/kg
    }
}
