package com.medicalaid.app.ui

import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

private val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

private fun DayOfWeek.short(): String = when (this) {
    DayOfWeek.MONDAY -> "Mon"
    DayOfWeek.TUESDAY -> "Tue"
    DayOfWeek.WEDNESDAY -> "Wed"
    DayOfWeek.THURSDAY -> "Thu"
    DayOfWeek.FRIDAY -> "Fri"
    DayOfWeek.SATURDAY -> "Sat"
    DayOfWeek.SUNDAY -> "Sun"
    else -> ""
}

/** "12 Mar 2027" */
fun LocalDate.pretty(): String = "$dayOfMonth ${months[monthNumber - 1]} $year"

/** "Fri, 12 Mar 2027" */
fun LocalDate.prettyWithDay(): String = "${dayOfWeek.short()}, ${pretty()}"

/** "12 Mar" (year omitted) */
fun LocalDate.dayMonth(): String = "$dayOfMonth ${months[monthNumber - 1]}"

fun today(): LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

/** Material date pickers work in UTC milliseconds. */
fun LocalDate.toPickerMillis(): Long = atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()

fun Long.pickerMillisToDate(): LocalDate = Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC).date

fun formatNumber(value: Double): String {
    val rounded = kotlin.math.round(value).toLong()
    return if (kotlin.math.abs(value - rounded) < 0.05) rounded.toString()
    else ((kotlin.math.round(value * 10)) / 10.0).toString()
}

/** "1 drug", "12 drugs". */
fun plural(n: Int, word: String): String = "$n ${if (n == 1) word else word + "s"}"

/** Fixed decimals without platform formatting, e.g. formatDecimals(1.8183, 2) = "1.82". */
fun formatDecimals(value: Double, decimals: Int): String {
    var factor = 1L
    repeat(decimals) { factor *= 10 }
    val n = kotlin.math.round(kotlin.math.abs(value) * factor).toLong()
    val sign = if (value < 0 && n != 0L) "-" else ""
    if (decimals == 0) return sign + n
    return sign + (n / factor) + "." + (n % factor).toString().padStart(decimals, '0')
}
