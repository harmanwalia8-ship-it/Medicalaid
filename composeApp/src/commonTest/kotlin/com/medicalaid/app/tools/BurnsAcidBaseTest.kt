package com.medicalaid.app.tools

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
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
        close(120.0, p.mlPerKg24h)
        assertFalse(p.exceedsJtsHighRisk)
        // JTS p. 9: over 250 mL/kg in 24 h (Parkland above 62.5% TBSA).
        assertTrue(Burns.fluids(Burns.PARKLAND_ML, 70.0, 70.0).exceedsJtsHighRisk)
        assertFalse(Burns.fluids(Burns.PARKLAND_ML, 70.0, 62.5).exceedsJtsHighRisk)
    }

    @Test
    fun paediatricFormulasFollowJtsAndMohfw() {
        // JTS p. 18: 3 × kg × %TBSA, half in 8 h → starting rate %TBSA × kg × 1.5 / 8 (30 kg, 50% → 4,500 mL, 281 mL/h).
        val j = Burns.fluids(Burns.JTS_CHILD_ML, 30.0, 50.0)
        close(4500.0, j.total24hMl)
        close(50.0 * 30.0 * 1.5 / 8, j.first8hRateMlPerH)
        // MoHFW p. 32: Ringer's lactate 2 mL/kg/%TBSA.
        close(400.0, Burns.fluids(Burns.MOHFW_CHILD_RL_ML, 10.0, 20.0).total24hMl)
        // 4-2-1 rule: 4 mL/kg/h first 10 kg, 2 mL/kg/h next 10 kg, 1 mL/kg/h above 20 kg.
        close(20.0, Burns.maintenance421MlPerH(5.0))
        close(40.0, Burns.maintenance421MlPerH(10.0))
        close(50.0, Burns.maintenance421MlPerH(15.0))
        close(60.0, Burns.maintenance421MlPerH(20.0))
        close(65.0, Burns.maintenance421MlPerH(25.0))
    }

    @Test
    fun regimensDependOnAgeAndBurnSize() {
        val adult = Burns.AgeBand.Adult
        // MoHFW section E: IV formula above 15% in adults; ABA 2024 only from 20%.
        assertTrue(Burns.regimens(adult, 15.0).isEmpty())
        assertEquals(listOf(Burns.PARKLAND_ML), Burns.regimens(adult, 18.0).map { it.mlPerKgPerPct })
        assertEquals(listOf(Burns.PARKLAND_ML, Burns.ABLS_ADULT_ML), Burns.regimens(adult, 20.0).map { it.mlPerKgPerPct })
        // Children (every non-adult column): from 10%, JTS 3 and MoHFW 2 mL/kg/%TBSA; never Parkland or ABA 2024.
        Burns.AgeBand.entries.filter { it != adult }.forEach { age ->
            assertTrue(Burns.isChild(age))
            assertTrue(Burns.regimens(age, 9.5).isEmpty())
            val r = Burns.regimens(age, 40.0)
            assertEquals(listOf(Burns.JTS_CHILD_ML, Burns.MOHFW_CHILD_RL_ML), r.map { it.mlPerKgPerPct })
            assertTrue(r.none { it.name.contains("Parkland") || it.name.contains("ABA") })
        }
        assertEquals(2, Burns.regimens(Burns.AgeBand.Infant, 10.0).size)
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
    fun primaryDisorderUsesUsualReferenceRanges() {
        // StatPearls normal ranges: pH 7.35–7.45, PaCO2 35–45 mm Hg, HCO3 22–26 mmol/L (HCO3 as in Berend Table 1).
        assertEquals(7.35, AcidBase.PH_LOW)
        assertEquals(7.45, AcidBase.PH_HIGH)
        assertEquals(35.0, AcidBase.PCO2_LOW)
        assertEquals(45.0, AcidBase.PCO2_HIGH)
        assertEquals(22.0, AcidBase.HCO3_LOW)
        assertEquals(26.0, AcidBase.HCO3_HIGH)
        // Compensation baselines stay on Berend's reference values.
        assertEquals(40.0, AcidBase.PCO2_NORMAL)
        assertEquals(24.0, AcidBase.HCO3_NORMAL)

        assertEquals(AcidBase.Primary.MetabolicAcidosis, AcidBase.primary(7.20, 25.0, 10.0))
        assertEquals(AcidBase.Primary.RespiratoryAcidosis, AcidBase.primary(7.25, 60.0, 26.0))
        assertEquals(AcidBase.Primary.MixedAcidosis, AcidBase.primary(7.10, 55.0, 16.0))
        assertEquals(AcidBase.Primary.MetabolicAlkalosis, AcidBase.primary(7.50, 48.0, 36.0))
        assertEquals(AcidBase.Primary.RespiratoryAlkalosis, AcidBase.primary(7.50, 28.0, 21.0))
        assertEquals(AcidBase.Primary.MixedAlkalosis, AcidBase.primary(7.55, 34.0, 30.0))
        assertEquals(AcidBase.Primary.NormalPh, AcidBase.primary(7.40, 40.0, 24.0))
        assertEquals(AcidBase.Primary.NotClassified, AcidBase.primary(7.30, 40.0, 24.0))
        // pH 7.37 was acidaemia under Berend's 7.38 cut-off; it is now within 7.35–7.45.
        assertEquals(AcidBase.Primary.NormalPh, AcidBase.primary(7.37, 33.0, 19.0))
        // PaCO2 43 was a respiratory component under Berend's 42; it is now within 35–45, so HCO3 21 alone is metabolic.
        assertEquals(AcidBase.Primary.MetabolicAcidosis, AcidBase.primary(7.30, 43.0, 21.0))
        assertEquals(AcidBase.Primary.MixedAcidosis, AcidBase.primary(7.30, 46.0, 21.0))
    }

    @Test
    fun primaryDisorderBoundaries() {
        fun p(ph: Double, pco2: Double, hco3: Double) = AcidBase.primary(ph, pco2, hco3)
        // pH: 7.35 and 7.45 are normal; acidaemia below 7.35, alkalaemia above 7.45.
        assertEquals(AcidBase.Primary.NormalPh, p(7.36, 40.0, 24.0))
        assertEquals(AcidBase.Primary.NormalPh, p(7.35, 40.0, 24.0))
        assertEquals(AcidBase.Primary.NormalPh, p(7.44, 40.0, 24.0))
        assertEquals(AcidBase.Primary.NormalPh, p(7.45, 40.0, 24.0))
        assertEquals(AcidBase.Primary.MetabolicAcidosis, p(7.34, 40.0, 18.0))
        assertEquals(AcidBase.Primary.RespiratoryAcidosis, p(7.34, 50.0, 24.0))
        assertEquals(AcidBase.Primary.MetabolicAlkalosis, p(7.46, 40.0, 30.0))
        assertEquals(AcidBase.Primary.RespiratoryAlkalosis, p(7.46, 30.0, 24.0))
        assertEquals(AcidBase.Primary.NotClassified, p(7.34, 40.0, 24.0))
        assertEquals(AcidBase.Primary.NotClassified, p(7.46, 40.0, 24.0))
        // Abnormal PaCO2 or HCO3 with pH inside 7.35–7.45 is not a primary disorder here (see the NormalPh label).
        assertEquals(AcidBase.Primary.NormalPh, p(7.36, 32.0, 18.0))
        assertEquals(AcidBase.Primary.NormalPh, p(7.44, 50.0, 33.0))
        // PaCO2: 45 and 35 are normal; above 45 / below 35 is a respiratory component.
        assertEquals(AcidBase.Primary.NotClassified, p(7.30, 45.0, 24.0))
        assertEquals(AcidBase.Primary.RespiratoryAcidosis, p(7.30, 45.5, 24.0))
        assertEquals(AcidBase.Primary.NotClassified, p(7.50, 35.0, 24.0))
        assertEquals(AcidBase.Primary.RespiratoryAlkalosis, p(7.50, 34.5, 24.0))
        // HCO3: 22 and 26 are normal; below 22 / above 26 is a metabolic component.
        assertEquals(AcidBase.Primary.NotClassified, p(7.30, 40.0, 22.0))
        assertEquals(AcidBase.Primary.MetabolicAcidosis, p(7.30, 40.0, 21.5))
        assertEquals(AcidBase.Primary.NotClassified, p(7.50, 40.0, 26.0))
        assertEquals(AcidBase.Primary.MetabolicAlkalosis, p(7.50, 40.0, 26.5))
        // Mixed at the edges.
        assertEquals(AcidBase.Primary.MixedAcidosis, p(7.34, 45.5, 21.5))
        assertEquals(AcidBase.Primary.MixedAlkalosis, p(7.46, 34.5, 26.5))
        assertTrue(AcidBase.Primary.NormalPh.label.contains("7.35–7.45"))
        assertTrue(AcidBase.Primary.NormalPh.label.contains("StatPearls"))
    }

    @Test
    fun secondaryResponsesFollowBerendTable1() {
        val w = AcidBase.wintersPco2(10.0)
        close(23.0, w.value); close(21.0, w.low); close(25.0, w.high)
        // Metabolic alkalosis: 0.7 × (HCO3 − 24) + 40 ± 2
        val m = AcidBase.alkalosisPco2(36.0)
        close(48.4, m.value); close(46.4, m.low); close(50.4, m.high)
        // Respiratory acidosis: +1 (acute) or +4–5 (chronic) per 10 mm Hg, no added tolerance
        val aa = AcidBase.respAcidosisHco3(60.0, chronic = false)
        close(26.0, aa.value); close(26.0, aa.low); close(26.0, aa.high)
        val ca = AcidBase.respAcidosisHco3(60.0, chronic = true)
        close(32.0, ca.low); close(34.0, ca.high); close(33.0, ca.value)
        // Respiratory alkalosis: −2 (acute) or −4–5 (chronic) per 10 mm Hg, no added tolerance
        val al = AcidBase.respAlkalosisHco3(30.0, chronic = false)
        close(22.0, al.value); close(22.0, al.low); close(22.0, al.high)
        val cl = AcidBase.respAlkalosisHco3(30.0, chronic = true)
        close(19.0, cl.low); close(20.0, cl.high); close(19.5, cl.value)
        val cl20 = AcidBase.respAlkalosisHco3(20.0, chronic = true)
        close(14.0, cl20.low); close(16.0, cl20.high)
    }

    @Test
    fun respiratoryResponsesUseBerendFigureBands() {
        fun acid(pco2: Double, hco3: Double) = AcidBase.respAcidosisResponse(pco2, hco3)
        fun alk(pco2: Double, hco3: Double) = AcidBase.respAlkalosisResponse(pco2, hco3)
        // Figure 1, per 10 mm Hg rise: <1 metabolic acidosis, 1 acute, 4–5 chronic, >5 metabolic alkalosis.
        assertEquals(AcidBase.RespResponse.AdditionalMetabolicAcidosis, acid(50.0, 23.0))
        assertEquals(AcidBase.RespResponse.Acute, acid(60.0, 26.0))
        assertEquals(AcidBase.RespResponse.Acute, acid(47.0, 24.7))
        assertEquals(AcidBase.RespResponse.BetweenAcuteAndChronic, acid(60.0, 30.0))
        assertEquals(AcidBase.RespResponse.Chronic, acid(60.0, 32.0))
        assertEquals(AcidBase.RespResponse.Chronic, acid(60.0, 34.0))
        assertEquals(AcidBase.RespResponse.AdditionalMetabolicAlkalosis, acid(60.0, 35.5))
        close(-1.0, AcidBase.respAcidosisRisePer10(50.0, 23.0))
        // Figure 2, per 10 mm Hg fall: <2 metabolic alkalosis, 2 acute, 4–5 chronic, >5 metabolic acidosis.
        assertEquals(AcidBase.RespResponse.AdditionalMetabolicAlkalosis, alk(30.0, 23.0))
        assertEquals(AcidBase.RespResponse.Acute, alk(30.0, 22.0))
        assertEquals(AcidBase.RespResponse.BetweenAcuteAndChronic, alk(20.0, 18.0))
        assertEquals(AcidBase.RespResponse.Chronic, alk(20.0, 15.0))
        assertEquals(AcidBase.RespResponse.AdditionalMetabolicAcidosis, alk(20.0, 13.0))
        close(5.5, AcidBase.respAlkalosisFallPer10(20.0, 13.0))
        assertTrue(AcidBase.respResponseText(AcidBase.RespResponse.AdditionalMetabolicAcidosis, acidosis = true).startsWith("less than 1"))
        assertTrue(AcidBase.respResponseText(AcidBase.RespResponse.AdditionalMetabolicAcidosis, acidosis = false).startsWith("more than 5"))
    }

    @Test
    fun anionGapAndDelta() {
        val ag = AcidBase.anionGap(140.0, 100.0, 10.0)
        close(30.0, ag)
        close(35.0, AcidBase.correctedAnionGap(ag, 2.0))
        // Berend describes a correction for albumin decreases only: none at or above 4.0 g/dL.
        close(30.0, AcidBase.correctedAnionGap(ag, 4.0))
        close(30.0, AcidBase.correctedAnionGap(ag, 5.0))
        val dd = AcidBase.deltaDelta(30.0, 10.0)!!
        close(18.0, dd.deltaAg); close(14.0, dd.deltaHco3)
        close(4.0, dd.keto); close(0.6 * 18.0 - 14.0, dd.lactic)
        // AG 40, HCO3 4: ketoacidosis difference +8 (with metabolic alkalosis), lactic −3.2 (alone).
        val d2 = AcidBase.deltaDelta(40.0, 4.0)!!
        close(8.0, d2.keto); close(-3.2, d2.lactic, 1e-9)
        assertTrue(AcidBase.deltaDeltaMeaning(d2.keto).startsWith("above +5"))
        assertTrue(AcidBase.deltaDeltaMeaning(d2.lactic).startsWith("−5 to +5"))
        assertTrue(AcidBase.deltaDeltaMeaning(5.0).startsWith("−5 to +5"))
        assertTrue(AcidBase.deltaDeltaMeaning(-5.0).startsWith("−5 to +5"))
        assertTrue(AcidBase.deltaDeltaMeaning(-5.1).startsWith("below −5"))
        // Δ–Δ is for a high anion gap acidosis: not with HCO3 ≥ 24 or a normal anion gap.
        assertNull(AcidBase.deltaDelta(14.0, 24.0))
        assertNull(AcidBase.deltaDelta(12.0, 15.0))
        // A raised anion gap is still flagged when HCO3 is not low.
        assertTrue(AcidBase.highAnionGapNotes(12.0, 24.0).isEmpty())
        val masked = AcidBase.highAnionGapNotes(24.0, 26.0)
        assertTrue(masked.any { it.contains("organic acidosis") })
        assertTrue(masked.any { it.contains("Δ–Δ is not calculated") })
        assertTrue(AcidBase.highAnionGapNotes(14.0, 15.0).any { it.contains("minor increase") })
    }
}
