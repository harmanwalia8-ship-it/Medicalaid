package com.medicalaid.app.pregnancy

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.daysUntil
import kotlinx.datetime.minus
import kotlinx.datetime.plus

/** Gestational age in completed days. Displayed clinically as weeks + days (e.g. 24w 3d). */
data class GestationalAge(val totalDays: Int) : Comparable<GestationalAge> {
    val weeks: Int get() = totalDays / 7
    val days: Int get() = totalDays % 7
    val label: String get() = "${weeks}w ${days}d"

    override fun compareTo(other: GestationalAge): Int = totalDays.compareTo(other.totalDays)

    companion object {
        fun of(weeks: Int, days: Int = 0) = GestationalAge(weeks * 7 + days)
    }
}

/** ACOG trimester boundaries: 1st to 13 6/7 weeks, 2nd 14 0/7–27 6/7, 3rd from 28 0/7. */
enum class Trimester(val label: String) {
    First("First trimester"),
    Second("Second trimester"),
    Third("Third trimester");

    companion object {
        fun of(ga: GestationalAge): Trimester = when {
            ga.totalDays < 14 * 7 -> First
            ga.totalDays < 28 * 7 -> Second
            else -> Third
        }
    }
}

/** ACOG Committee Opinion No. 579 (Definition of Term Pregnancy). */
enum class TermCategory(val label: String) {
    Preterm("Preterm"),
    EarlyTerm("Early term"),
    FullTerm("Full term"),
    LateTerm("Late term"),
    PostTerm("Postterm");

    companion object {
        fun of(ga: GestationalAge): TermCategory = when {
            ga.totalDays < 37 * 7 -> Preterm
            ga.totalDays < 39 * 7 -> EarlyTerm
            ga.totalDays < 41 * 7 -> FullTerm
            ga.totalDays < 42 * 7 -> LateTerm
            else -> PostTerm
        }
    }
}

data class UltrasoundScan(val scanDate: LocalDate, val gaAtScan: GestationalAge)

enum class DatingBasis { Lmp, Ultrasound }

/**
 * Outcome of comparing ultrasound dating with LMP dating (ACOG Committee Opinion No. 700, Table 1).
 * [redate] is true when the discrepancy is strictly greater than the threshold for the
 * gestational-age band of the ultrasound examination.
 */
data class RedatingCheck(
    val ultrasoundGa: GestationalAge,
    val lmpGaAtScan: GestationalAge,
    val discrepancyDays: Int,
    val thresholdDays: Int,
    val bandLabel: String,
    val redate: Boolean,
)

data class DatingResult(
    val edd: LocalDate,
    val basis: DatingBasis,
    /** Gestational age on the reference date ("today"). May exceed 42 weeks if the input is wrong. */
    val gaToday: GestationalAge,
    val lmpEdd: LocalDate?,
    val ultrasoundEdd: LocalDate?,
    val redating: RedatingCheck?,
    val warnings: List<String>,
) {
    val trimester: Trimester get() = Trimester.of(gaToday)
    val termCategory: TermCategory get() = TermCategory.of(gaToday)
}

sealed interface DatingOutcome {
    data class Ok(val result: DatingResult) : DatingOutcome
    data class Invalid(val message: String) : DatingOutcome
    data object Empty : DatingOutcome
}

object Dating {
    /** Naegele's rule: EDD = LMP + 280 days (ACOG CO 700). */
    const val PREGNANCY_DAYS = 280

    private const val MIN_SCAN_GA_DAYS = 5 * 7
    private const val MAX_SCAN_GA_DAYS = 42 * 7
    private const val IMPLAUSIBLE_GA_DAYS = 44 * 7

    fun eddFromLmp(lmp: LocalDate): LocalDate = lmp.plus(PREGNANCY_DAYS, DateTimeUnit.DAY)

    fun eddFromUltrasound(scan: UltrasoundScan): LocalDate =
        scan.scanDate.plus(PREGNANCY_DAYS - scan.gaAtScan.totalDays, DateTimeUnit.DAY)

    /** Gestational age (in days) on [date] for a pregnancy with due date [edd]. Can be negative. */
    fun gaDaysOn(date: LocalDate, edd: LocalDate): Int = PREGNANCY_DAYS - date.daysUntil(edd)

    /** Calendar date on which the pregnancy reaches [gaDays]. */
    fun dateAtGa(edd: LocalDate, gaDays: Int): LocalDate =
        edd.minus(PREGNANCY_DAYS - gaDays, DateTimeUnit.DAY)

    /**
     * ACOG CO 700 Table 1 — discrepancy (days) between ultrasound and LMP dating that supports
     * changing the EDD. The table's gestational-age bands are footnoted "based on LMP", so the band
     * is chosen from the LMP gestational age on the day of the scan.
     */
    fun redatingThreshold(lmpGaAtScan: GestationalAge): Pair<Int, String> {
        val d = lmpGaAtScan.totalDays
        return when {
            d <= 8 * 7 + 6 -> 5 to "≤8w 6d (CRL)"
            d <= 13 * 7 + 6 -> 7 to "9w 0d – 13w 6d (CRL)"
            d <= 15 * 7 + 6 -> 7 to "14w 0d – 15w 6d"
            d <= 21 * 7 + 6 -> 10 to "16w 0d – 21w 6d"
            d <= 27 * 7 + 6 -> 14 to "22w 0d – 27w 6d"
            else -> 21 to "≥28w 0d"
        }
    }

    fun calculate(lmp: LocalDate?, scan: UltrasoundScan?, today: LocalDate): DatingOutcome {
        if (lmp == null && scan == null) return DatingOutcome.Empty

        if (lmp != null && lmp > today) return DatingOutcome.Invalid("LMP cannot be in the future.")
        if (scan != null) {
            if (scan.scanDate > today) return DatingOutcome.Invalid("Scan date cannot be in the future.")
            if (scan.gaAtScan.totalDays !in MIN_SCAN_GA_DAYS..MAX_SCAN_GA_DAYS) {
                return DatingOutcome.Invalid("Gestational age at scan should be between 5 and 42 weeks.")
            }
            if (lmp != null && scan.scanDate < lmp) {
                return DatingOutcome.Invalid("Scan date is before the LMP.")
            }
        }

        val warnings = mutableListOf<String>()
        val lmpEdd = lmp?.let(::eddFromLmp)
        val usEdd = scan?.let(::eddFromUltrasound)

        var redating: RedatingCheck? = null
        val (edd, basis) = when {
            lmpEdd != null && usEdd != null && scan != null -> {
                val lmpGaAtScan = GestationalAge(gaDaysOn(scan.scanDate, lmpEdd))
                val discrepancy = kotlin.math.abs(scan.gaAtScan.totalDays - lmpGaAtScan.totalDays)
                val (threshold, band) = redatingThreshold(lmpGaAtScan)
                val redate = discrepancy > threshold
                redating = RedatingCheck(scan.gaAtScan, lmpGaAtScan, discrepancy, threshold, band, redate)
                if (redate) usEdd to DatingBasis.Ultrasound else lmpEdd to DatingBasis.Lmp
            }
            usEdd != null -> usEdd to DatingBasis.Ultrasound
            else -> lmpEdd!! to DatingBasis.Lmp
        }

        if (scan != null) {
            val scanGaDays = maxOf(scan.gaAtScan.totalDays, redating?.lmpGaAtScan?.totalDays ?: 0)
            if (scanGaDays >= 22 * 7) {
                warnings += "Scan at 22 weeks or later: if there was no ultrasound before 22w 0d, " +
                    "ACOG considers this pregnancy suboptimally dated."
            }
            if (redating?.redate == true && redating.lmpGaAtScan.totalDays >= 28 * 7) {
                warnings += "Redating on a third-trimester scan alone is problematic — a small fetus may be growth restricted."
            }
        }

        val gaToday = gaDaysOn(today, edd)
        if (gaToday > IMPLAUSIBLE_GA_DAYS) {
            warnings += "Gestational age is beyond 44 weeks — check the dates entered."
        } else if (gaToday >= 42 * 7) {
            warnings += "Beyond 42 weeks (postterm)."
        }

        return DatingOutcome.Ok(
            DatingResult(
                edd = edd,
                basis = basis,
                gaToday = GestationalAge(gaToday.coerceAtLeast(0)),
                lmpEdd = lmpEdd,
                ultrasoundEdd = usEdd,
                redating = redating,
                warnings = warnings,
            ),
        )
    }
}
