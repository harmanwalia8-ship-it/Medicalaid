package com.medicalaid.app.tools

import com.medicalaid.app.pregnancy.Trimester

object Thyroid {
    data class Range(val low: Double, val high: Double)

    /** MoHFW 2014 trimester-specific TSH reference ranges (mIU/L). */
    fun mohfwRange(trimester: Trimester): Range = when (trimester) {
        Trimester.First -> Range(0.1, 2.5)
        Trimester.Second -> Range(0.2, 3.0)
        Trimester.Third -> Range(0.3, 3.0)
    }

    enum class PregnancyAction {
        /** Below the trimester range — outside the scope of the MoHFW hypothyroidism guideline. */
        BelowRange,
        WithinRange,
        /** TSH above the trimester upper limit up to 10 → levothyroxine 25 mcg/day. */
        Start25,
        /** TSH above 10 → levothyroxine 50 mcg/day. */
        Start50,
    }

    // ------------------------------------------------------------------ non-pregnant adults

    enum class Ft4 { Low, Normal, Unknown }

    enum class GeneralFinding {
        BelowRange,
        WithinRange,
        /** Raised TSH with low FT4. */
        Overt,
        /** Raised TSH, normal FT4, TSH ≥10 mIU/L. */
        SubclinicalTenOrMore,
        /** Raised TSH, normal FT4, TSH above the reference range but <10. */
        SubclinicalBelowTen,
        /** Raised TSH, FT4 not known. */
        NeedFt4,
    }

    /** ATA 2014 uses 0.4–4.0 mIU/L as the example reference range / therapeutic target. */
    val DEFAULT_RANGE = Range(0.4, 4.0)

    fun general(tsh: Double, ft4: Ft4, range: Range = DEFAULT_RANGE): GeneralFinding = when {
        tsh < range.low -> GeneralFinding.BelowRange
        tsh <= range.high -> GeneralFinding.WithinRange
        ft4 == Ft4.Low -> GeneralFinding.Overt
        ft4 == Ft4.Unknown -> GeneralFinding.NeedFt4
        tsh >= 10.0 -> GeneralFinding.SubclinicalTenOrMore
        else -> GeneralFinding.SubclinicalBelowTen
    }

    /** NICE NG145 1.3.6: 1.6 mcg/kg/day rounded to the nearest 25 mcg (adults <65, no cardiovascular disease). */
    fun niceFullReplacementMcg(weightKg: Double): Int {
        val raw = 1.6 * weightKg
        return (kotlin.math.round(raw / 25.0) * 25).toInt()
    }

    // ------------------------------------------------------------------ ATA 2026 (pregnancy)

    /** ATA 2026: if no lab- and trimester-specific interval, TSH 0.1–4.0 mU/L in the 1st and 2nd trimesters. */
    const val ATA_PREGNANCY_UPPER = 4.0
    const val ATA_PREGNANCY_LOWER = 0.1

    enum class AtaPregnancyAction {
        BelowRange,
        /** Euthyroid by TSH — levothyroxine not indicated (also when TPOAb/TgAb positive). */
        WithinRange,
        /** Overt hypothyroidism with TSH ≥6, or persisting after retest: treat (Strong, Moderate). */
        TreatOvert,
        /** Overt hypothyroidism with TSH <6: confirmatory test within 3 weeks may be done (Conditional, Low). */
        ConfirmOvert,
        /** Subclinical with TSH >10: treat (Good Practice Statement). */
        TreatProfoundSubclinical,
        /** Mild subclinical in 1st trimester: levothyroxine may be considered (Conditional, Low). */
        ConsiderInFirstTrimester,
        /** Mild subclinical after 1st trimester: do not offer; recheck TSH in 4–6 weeks (Strong, High). */
        DoNotTreatRecheck,
        /** TSH raised, FT4 not known (and ≤10). */
        NeedFt4,
    }

    fun ata2026Pregnancy(
        tsh: Double,
        ft4: Ft4,
        trimester: Trimester,
        upper: Double = ATA_PREGNANCY_UPPER,
    ): AtaPregnancyAction = when {
        tsh < ATA_PREGNANCY_LOWER -> AtaPregnancyAction.BelowRange
        tsh <= upper -> AtaPregnancyAction.WithinRange
        ft4 == Ft4.Low -> if (tsh >= 6.0) AtaPregnancyAction.TreatOvert else AtaPregnancyAction.ConfirmOvert
        tsh > 10.0 -> AtaPregnancyAction.TreatProfoundSubclinical
        ft4 == Ft4.Unknown -> AtaPregnancyAction.NeedFt4
        trimester == Trimester.First -> AtaPregnancyAction.ConsiderInFirstTrimester
        else -> AtaPregnancyAction.DoNotTreatRecheck
    }

    /** ATA 2026: full replacement 1.5–1.7 mcg/kg/day plus 20–30% for gestation → total mcg/day range. */
    fun ata2026OvertDoseRange(weightKg: Double): Pair<Double, Double> =
        1.5 * weightKg * 1.2 to 1.7 * weightKg * 1.3

    fun mohfwPregnancy(tsh: Double, trimester: Trimester): PregnancyAction {
        val r = mohfwRange(trimester)
        return when {
            tsh < r.low -> PregnancyAction.BelowRange
            tsh <= r.high -> PregnancyAction.WithinRange
            tsh <= 10.0 -> PregnancyAction.Start25
            else -> PregnancyAction.Start50
        }
    }
}
