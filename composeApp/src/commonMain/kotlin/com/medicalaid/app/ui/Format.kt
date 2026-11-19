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
