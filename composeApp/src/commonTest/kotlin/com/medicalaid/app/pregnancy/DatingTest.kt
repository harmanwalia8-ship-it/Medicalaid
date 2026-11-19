package com.medicalaid.app.pregnancy

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

class DatingTest {

    private fun ok(outcome: DatingOutcome): DatingResult {
        assertIs<DatingOutcome.Ok>(outcome)
        return outcome.result
    }

    @Test
    fun eddIsLmpPlus280Days() {
        assertEquals(LocalDate(2026, 10, 8), Dating.eddFromLmp(LocalDate(2026, 1, 1)))
        // Leap year: 2024-02-29 is counted.
        assertEquals(LocalDate(2024, 10, 7), Dating.eddFromLmp(LocalDate(2024, 1, 1)))
    }

    @Test
    fun gestationalAgeTodayFromLmp() {
        val lmp = LocalDate(2026, 6, 1)
        val today = LocalDate(2026, 11, 19) // 171 days later
        val r = ok(Dating.calculate(lmp, null, today))
        assertEquals(171, r.gaToday.totalDays)
        assertEquals("24w 3d", r.gaToday.label)
        assertEquals(Trimester.Second, r.trimester)
        assertEquals(DatingBasis.Lmp, r.basis)
    }

    @Test
    fun trimesterBoundariesFollowAcog() {
        assertEquals(Trimester.First, Trimester.of(GestationalAge.of(13, 6)))
        assertEquals(Trimester.Second, Trimester.of(GestationalAge.of(14, 0)))
        assertEquals(Trimester.Second, Trimester.of(GestationalAge.of(27, 6)))
        assertEquals(Trimester.Third, Trimester.of(GestationalAge.of(28, 0)))
    }

    @Test
    fun termCategories() {
        assertEquals(TermCategory.Preterm, TermCategory.of(GestationalAge.of(36, 6)))
        assertEquals(TermCategory.EarlyTerm, TermCategory.of(GestationalAge.of(37, 0)))
        assertEquals(TermCategory.FullTerm, TermCategory.of(GestationalAge.of(39, 0)))
        assertEquals(TermCategory.LateTerm, TermCategory.of(GestationalAge.of(41, 0)))
        assertEquals(TermCategory.PostTerm, TermCategory.of(GestationalAge.of(42, 0)))
    }

    @Test
    fun redatingThresholdBands() {
        assertEquals(5, Dating.redatingThreshold(GestationalAge.of(8, 6)).first)
        assertEquals(7, Dating.redatingThreshold(GestationalAge.of(9, 0)).first)
        assertEquals(7, Dating.redatingThreshold(GestationalAge.of(13, 6)).first)
        assertEquals(7, Dating.redatingThreshold(GestationalAge.of(15, 6)).first)
        assertEquals(10, Dating.redatingThreshold(GestationalAge.of(16, 0)).first)
        assertEquals(10, Dating.redatingThreshold(GestationalAge.of(21, 6)).first)
        assertEquals(14, Dating.redatingThreshold(GestationalAge.of(22, 0)).first)
        assertEquals(21, Dating.redatingThreshold(GestationalAge.of(28, 0)).first)
    }

    @Test
    fun firstTrimesterScanWithin7DaysKeepsLmpDating() {
        val lmp = LocalDate(2026, 6, 1)
        // On 2026-08-10 LMP GA = 70 days = 10w0d. Scan says 10w6d -> 6 days difference.
        val scan = UltrasoundScan(LocalDate(2026, 8, 10), GestationalAge.of(10, 6))
        val r = ok(Dating.calculate(lmp, scan, LocalDate(2026, 11, 19)))
        assertEquals(6, r.redating!!.discrepancyDays)
        assertFalse(r.redating!!.redate)
        assertEquals(DatingBasis.Lmp, r.basis)
        assertEquals(Dating.eddFromLmp(lmp), r.edd)
    }

    @Test
    fun bandIsChosenByLmpGestationalAgeNotScanGestationalAge() {
        val lmp = LocalDate(2026, 6, 1)
        // On 2026-08-03 LMP GA = 63 days = 9w0d (band 9w0d–13w6d, threshold 7).
        // Scan says 8w1d (57 d) -> 6 days apart. Using the scan's own band (≤8w6d, threshold 5)
        // would wrongly redate; CO 700 bands are by LMP, so LMP dating is kept.
        val scan = UltrasoundScan(LocalDate(2026, 8, 3), GestationalAge.of(8, 1))
        val r = ok(Dating.calculate(lmp, scan, LocalDate(2026, 11, 19)))
        assertEquals(6, r.redating!!.discrepancyDays)
        assertEquals(7, r.redating!!.thresholdDays)
        assertFalse(r.redating!!.redate)
    }

    @Test
    fun thirdTrimesterRedatingCarriesWarning() {
        val lmp = LocalDate(2026, 1, 1)
        // On 2026-07-23 LMP GA = 203 days = 29w0d (threshold 21). Scan says 25w6d (181 d) -> 22 days.
        val scan = UltrasoundScan(LocalDate(2026, 7, 23), GestationalAge.of(25, 6))
        val r = ok(Dating.calculate(lmp, scan, LocalDate(2026, 7, 23)))
        assertEquals(22, r.redating!!.discrepancyDays)
        assertTrue(r.redating!!.redate)
        assertTrue(r.warnings.any { it.contains("growth restricted") })
    }

    @Test
    fun firstTrimesterScanOver7DaysRedates() {
        val lmp = LocalDate(2026, 6, 1)
        // LMP GA at scan 10w0d (band 9w0d–13w6d, threshold 7); scan 8w6d -> 8 days smaller -> redate.
        val scan = UltrasoundScan(LocalDate(2026, 8, 10), GestationalAge.of(8, 6))
        val r = ok(Dating.calculate(lmp, scan, LocalDate(2026, 11, 19)))
        assertEquals(8, r.redating!!.discrepancyDays)
        assertTrue(r.redating!!.redate)
        assertEquals(DatingBasis.Ultrasound, r.basis)
        // EDD = scan date + (280 - 62) days
        assertEquals(LocalDate(2027, 3, 16), r.edd)
    }

    @Test
    fun exactlyAtThresholdDoesNotRedate() {
        val lmp = LocalDate(2026, 6, 1)
        // Scan at LMP GA 18w0d (126 d) on 2026-10-05; scan says 19w3d (+10 d). Threshold 10 -> keep LMP.
        val scan = UltrasoundScan(LocalDate(2026, 10, 5), GestationalAge.of(19, 3))
        val r = ok(Dating.calculate(lmp, scan, LocalDate(2026, 11, 19)))
        assertEquals(10, r.redating!!.discrepancyDays)
        assertFalse(r.redating!!.redate)
    }

    @Test
    fun ultrasoundOnlyDating() {
        val scan = UltrasoundScan(LocalDate(2026, 9, 1), GestationalAge.of(12, 0))
        val r = ok(Dating.calculate(null, scan, LocalDate(2026, 9, 1)))
        assertEquals(DatingBasis.Ultrasound, r.basis)
        assertEquals(84, r.gaToday.totalDays)
        assertEquals(LocalDate(2027, 3, 16), r.edd) // 2026-09-01 + 196 days
    }

    @Test
    fun lateFirstUltrasoundIsFlaggedAsSuboptimal() {
        val scan = UltrasoundScan(LocalDate(2026, 9, 1), GestationalAge.of(24, 0))
        val r = ok(Dating.calculate(null, scan, LocalDate(2026, 9, 1)))
        assertTrue(r.warnings.any { it.contains("suboptimally dated") })
    }

    @Test
    fun invalidInputs() {
        val today = LocalDate(2026, 11, 19)
        assertIs<DatingOutcome.Empty>(Dating.calculate(null, null, today))
        assertIs<DatingOutcome.Invalid>(Dating.calculate(LocalDate(2026, 11, 20), null, today))
        assertIs<DatingOutcome.Invalid>(
            Dating.calculate(
                LocalDate(2026, 6, 1),
                UltrasoundScan(LocalDate(2026, 5, 1), GestationalAge.of(8, 0)),
                today,
            ),
        )
        assertIs<DatingOutcome.Invalid>(
            Dating.calculate(null, UltrasoundScan(today, GestationalAge.of(3, 0)), today),
        )
    }

    @Test
    fun dateAtGaRoundTrips() {
        val edd = LocalDate(2027, 3, 8)
        val d = Dating.dateAtGa(edd, 28 * 7)
        assertEquals(28 * 7, Dating.gaDaysOn(d, edd))
        assertEquals(edd, Dating.dateAtGa(edd, 280))
    }
}
