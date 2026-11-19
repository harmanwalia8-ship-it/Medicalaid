package com.medicalaid.app.tools

import com.medicalaid.app.pregnancy.Trimester
import com.medicalaid.app.tools.Thyroid.Ft4
import com.medicalaid.app.tools.Thyroid.GeneralFinding
import com.medicalaid.app.tools.Thyroid.PregnancyAction
import kotlin.test.Test
import kotlin.test.assertEquals

class ThyroidTest {
    @Test
    fun mohfwPregnancyThresholds() {
        assertEquals(PregnancyAction.WithinRange, Thyroid.mohfwPregnancy(2.5, Trimester.First))
        assertEquals(PregnancyAction.Start25, Thyroid.mohfwPregnancy(2.6, Trimester.First))
        // 2nd/3rd trimester upper limit is 3.0
        assertEquals(PregnancyAction.WithinRange, Thyroid.mohfwPregnancy(2.8, Trimester.Second))
        assertEquals(PregnancyAction.Start25, Thyroid.mohfwPregnancy(3.1, Trimester.Third))
        assertEquals(PregnancyAction.Start25, Thyroid.mohfwPregnancy(10.0, Trimester.Second))
        assertEquals(PregnancyAction.Start50, Thyroid.mohfwPregnancy(10.1, Trimester.First))
        assertEquals(PregnancyAction.BelowRange, Thyroid.mohfwPregnancy(0.05, Trimester.First))
        assertEquals(PregnancyAction.BelowRange, Thyroid.mohfwPregnancy(0.15, Trimester.Second))
    }

    @Test
    fun generalInterpretation() {
        assertEquals(GeneralFinding.WithinRange, Thyroid.general(3.9, Ft4.Unknown))
        assertEquals(GeneralFinding.BelowRange, Thyroid.general(0.2, Ft4.Normal))
        assertEquals(GeneralFinding.Overt, Thyroid.general(25.0, Ft4.Low))
        assertEquals(GeneralFinding.SubclinicalTenOrMore, Thyroid.general(10.0, Ft4.Normal))
        assertEquals(GeneralFinding.SubclinicalBelowTen, Thyroid.general(6.5, Ft4.Normal))
        assertEquals(GeneralFinding.NeedFt4, Thyroid.general(6.5, Ft4.Unknown))
        // Custom lab range
        assertEquals(GeneralFinding.WithinRange, Thyroid.general(4.5, Ft4.Normal, Thyroid.Range(0.5, 5.0)))
    }

    @Test
    fun ata2026PregnancyPathways() {
        // Same TSH 3.5: MoHFW treats (above 2.5/3), ATA 2026 considers it within 0.1–4.0.
        assertEquals(PregnancyAction.Start25, Thyroid.mohfwPregnancy(3.5, Trimester.Second))
        assertEquals(Thyroid.AtaPregnancyAction.WithinRange, Thyroid.ata2026Pregnancy(3.5, Ft4.Normal, Trimester.Second))

        assertEquals(Thyroid.AtaPregnancyAction.ConsiderInFirstTrimester, Thyroid.ata2026Pregnancy(6.0, Ft4.Normal, Trimester.First))
        assertEquals(Thyroid.AtaPregnancyAction.DoNotTreatRecheck, Thyroid.ata2026Pregnancy(6.0, Ft4.Normal, Trimester.Second))
        assertEquals(Thyroid.AtaPregnancyAction.TreatProfoundSubclinical, Thyroid.ata2026Pregnancy(10.5, Ft4.Normal, Trimester.Third))
        assertEquals(Thyroid.AtaPregnancyAction.TreatProfoundSubclinical, Thyroid.ata2026Pregnancy(12.0, Ft4.Unknown, Trimester.Second))
        assertEquals(Thyroid.AtaPregnancyAction.TreatOvert, Thyroid.ata2026Pregnancy(6.0, Ft4.Low, Trimester.Second))
        assertEquals(Thyroid.AtaPregnancyAction.ConfirmOvert, Thyroid.ata2026Pregnancy(5.0, Ft4.Low, Trimester.First))
        assertEquals(Thyroid.AtaPregnancyAction.NeedFt4, Thyroid.ata2026Pregnancy(5.0, Ft4.Unknown, Trimester.First))
        assertEquals(Thyroid.AtaPregnancyAction.BelowRange, Thyroid.ata2026Pregnancy(0.05, Ft4.Normal, Trimester.First))
    }

    @Test
    fun ata2026OvertDose() {
        val (lo, hi) = Thyroid.ata2026OvertDoseRange(60.0)
        assertEquals(108.0, lo, 0.01) // 1.5 × 60 × 1.2
        assertEquals(132.6, hi, 0.01) // 1.7 × 60 × 1.3
    }

    @Test
    fun niceDoseRoundsToNearest25() {
        assertEquals(100, Thyroid.niceFullReplacementMcg(60.0)) // 96 -> 100
        assertEquals(75, Thyroid.niceFullReplacementMcg(50.0))  // 80 -> 75
        assertEquals(125, Thyroid.niceFullReplacementMcg(80.0)) // 128 -> 125
    }
}
