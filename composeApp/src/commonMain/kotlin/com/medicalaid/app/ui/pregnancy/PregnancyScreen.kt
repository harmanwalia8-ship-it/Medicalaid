package com.medicalaid.app.ui.pregnancy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Schedule
import com.medicalaid.app.content.ScheduleItem
import com.medicalaid.app.content.cite
import com.medicalaid.app.pregnancy.Dating
import com.medicalaid.app.pregnancy.DatingBasis
import com.medicalaid.app.pregnancy.DatingOutcome
import com.medicalaid.app.pregnancy.DatingResult
import com.medicalaid.app.pregnancy.TermCategory
import com.medicalaid.app.ui.components.GuidelineBasis
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.components.LabeledValue
import com.medicalaid.app.ui.components.Overline
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.components.Segmented
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.components.Tag
import com.medicalaid.app.ui.dayMonth
import com.medicalaid.app.ui.pickerMillisToDate
import com.medicalaid.app.ui.pretty
import com.medicalaid.app.ui.prettyWithDay
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme
import com.medicalaid.app.ui.toPickerMillis
import kotlinx.datetime.LocalDate
import kotlinx.datetime.daysUntil

private enum class PickerTarget { Lmp, Scan }

@Composable
fun PregnancyScreen(state: PregnancyState, today: LocalDate, contentPadding: PaddingValues, onMenu: (() -> Unit)? = null, onBack: (() -> Unit)? = null) {
    var picker by remember { mutableStateOf<PickerTarget?>(null) }
    val outcome = state.outcome(today)
    val result = (outcome as? DatingOutcome.Ok)?.result

    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle("Dating & antenatal schedule", "EDD, gestational age, scans, tests and vaccines", onMenu = onMenu, onBack = onBack) }

        item {
            InputCard(
                state = state,
                onPickLmp = { picker = PickerTarget.Lmp },
                onPickScan = { picker = PickerTarget.Scan },
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 12.dp),
            )
        }

        if (outcome is DatingOutcome.Invalid) {
            item { Notice(outcome.message, Modifier.padding(horizontal = Gutter).padding(top = 12.dp)) }
        }

        if (result != null) {
            item { ResultCard(result, today, Modifier.padding(horizontal = Gutter).padding(top = 12.dp)) }
        }

        scheduleSection(state, result, today)
    }

    picker?.let { target ->
        DateDialog(
            title = if (target == PickerTarget.Lmp) "First day of last menstrual period" else "Date of dating ultrasound",
            initial = if (target == PickerTarget.Lmp) state.lmp else state.scanDate,
            today = today,
            onDismiss = { picker = null },
            onPick = {
                if (target == PickerTarget.Lmp) state.lmp = it else state.scanDate = it
                picker = null
            },
        )
    }
}

// ---------------------------------------------------------------------------------------------
// Input
// ---------------------------------------------------------------------------------------------

@Composable
private fun InputCard(
    state: PregnancyState,
    onPickLmp: () -> Unit,
    onPickScan: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val c = Theme.colors
    SurfaceCard(modifier, padding = PaddingValues(0.dp)) {
        DateRow("LMP", state.lmp, "Select date", onPickLmp)
        Hairline(Modifier.padding(start = 18.dp))
        Row(
            Modifier.fillMaxWidth().clickable { state.useScan = !state.useScan }.padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text("Ultrasound dating", style = Theme.type.bodyStrong, color = c.text)
                Text(
                    if (state.useScan) "Compared with LMP using ACOG redating limits" else "Optional — add a dating scan",
                    style = Theme.type.caption,
                    color = c.textSecondary,
                )
            }
            Icon(
                if (state.useScan) Icons.Outlined.Close else Icons.Outlined.Add,
                contentDescription = if (state.useScan) "Remove ultrasound" else "Add ultrasound",
                tint = c.textSecondary,
                modifier = Modifier.size(20.dp),
            )
        }
        AnimatedVisibility(state.useScan, enter = expandVertically() + fadeIn(), exit = shrinkVertically() + fadeOut()) {
            Column {
                Hairline(Modifier.padding(start = 18.dp))
                DateRow("Scan date", state.scanDate, "Select date", onPickScan)
                Hairline(Modifier.padding(start = 18.dp))
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(Modifier.weight(1f)) {
                        Text("GA on scan", style = Theme.type.bodyStrong, color = c.text)
                        Text("By CRL / biometry", style = Theme.type.caption, color = c.textSecondary)
                    }
                    Stepper(state.scanWeeks, "w", 5..42) { state.scanWeeks = it }
                    Spacer(Modifier.width(10.dp))
                    Stepper(state.scanDays, "d", 0..6) { state.scanDays = it }
                }
            }
        }
        if (state.lmp != null || state.useScan) {
            Hairline()
            Text(
                "Clear",
                style = Theme.type.label,
                color = c.textSecondary,
                modifier = Modifier.fillMaxWidth().clickable { state.reset() }.padding(vertical = 13.dp, horizontal = 18.dp),
            )
        }
    }
}

@Composable
private fun DateRow(label: String, value: LocalDate?, placeholder: String, onClick: () -> Unit) {
    val c = Theme.colors
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 18.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, style = Theme.type.bodyStrong, color = c.text, modifier = Modifier.weight(1f))
        Text(
            value?.prettyWithDay() ?: placeholder,
            style = Theme.type.body,
            color = if (value != null) c.text else c.accent,
        )
        Spacer(Modifier.width(10.dp))
        Icon(Icons.Outlined.CalendarMonth, null, tint = c.textTertiary, modifier = Modifier.size(18.dp))
    }
}

@Composable
private fun Stepper(value: Int, unit: String, range: IntRange, onChange: (Int) -> Unit) {
    val c = Theme.colors
    Row(
        Modifier.clip(Radius.chip).background(c.surfaceMuted).padding(3.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StepButton(Icons.Outlined.Remove, "Decrease", enabled = value > range.first) { onChange(value - 1) }
        Text(
            "$value$unit",
            style = Theme.type.bodyStrong,
            color = c.text,
            modifier = Modifier.width(38.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
        StepButton(Icons.Outlined.Add, "Increase", enabled = value < range.last) { onChange(value + 1) }
    }
}

@Composable
private fun StepButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, enabled: Boolean, onClick: () -> Unit) {
    val c = Theme.colors
    Box(
        Modifier.size(30.dp).clip(CircleShape).background(c.surface).clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, label, tint = if (enabled) c.text else c.textTertiary, modifier = Modifier.size(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateDialog(
    title: String,
    initial: LocalDate?,
    today: LocalDate,
    onDismiss: () -> Unit,
    onPick: (LocalDate) -> Unit,
) {
    val todayMillis = today.toPickerMillis()
    val pickerState = rememberDatePickerState(
        initialSelectedDateMillis = initial?.toPickerMillis(),
        initialDisplayedMonthMillis = (initial ?: today).toPickerMillis(),
        yearRange = (today.year - 1)..today.year,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long) = utcTimeMillis <= todayMillis
            override fun isSelectableYear(year: Int) = year <= today.year
        },
    )
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = { pickerState.selectedDateMillis?.let { onPick(it.pickerMillisToDate()) } },
                enabled = pickerState.selectedDateMillis != null,
            ) { Text("Done") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    ) {
        DatePicker(
            state = pickerState,
            title = {
                Text(
                    title,
                    style = Theme.type.label,
                    color = Theme.colors.textSecondary,
                    modifier = Modifier.padding(start = 24.dp, end = 12.dp, top = 16.dp),
                )
            },
            showModeToggle = true,
        )
    }
}

// ---------------------------------------------------------------------------------------------
// Result
// ---------------------------------------------------------------------------------------------

@Composable
private fun ResultCard(result: DatingResult, today: LocalDate, modifier: Modifier = Modifier) {
    val c = Theme.colors
    val ga = result.gaToday
    SurfaceCard(modifier, padding = PaddingValues(20.dp)) {
        Overline("Gestational age today")
        Spacer(Modifier.height(6.dp))
        Row {
            Text("${ga.weeks}", style = Theme.type.display, color = c.text, modifier = Modifier.alignByBaseline())
            Text("w", style = Theme.type.headline, color = c.textSecondary, modifier = Modifier.alignByBaseline().padding(start = 3.dp, end = 12.dp))
            Text("${ga.days}", style = Theme.type.display, color = c.text, modifier = Modifier.alignByBaseline())
            Text("d", style = Theme.type.headline, color = c.textSecondary, modifier = Modifier.alignByBaseline().padding(start = 3.dp))
        }
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Tag(result.trimester.label, fg = c.accent, bg = c.accentSoft)
            if (result.termCategory != TermCategory.Preterm) Tag(result.termCategory.label)
        }
        Spacer(Modifier.height(18.dp))
        PregnancyProgress(ga.totalDays)
        Spacer(Modifier.height(20.dp))
        Row {
            LabeledValue("Due date (EDD)", result.edd.prettyWithDay(), Modifier.weight(1.4f))
            val remaining = today.daysUntil(result.edd)
            LabeledValue(
                if (remaining >= 0) "Days to EDD" else "Days past EDD",
                "${kotlin.math.abs(remaining)}",
                Modifier.weight(0.8f),
            )
        }
        Spacer(Modifier.height(14.dp))
        LabeledValue(
            "Dated by",
            when (result.basis) {
                DatingBasis.Lmp -> "LMP — Naegele's rule (LMP + 280 days)"
                DatingBasis.Ultrasound -> if (result.redating != null) "Ultrasound — redated" else "Ultrasound"
            },
        )

        result.redating?.let { r ->
            Spacer(Modifier.height(14.dp))
            Column(
                Modifier.fillMaxWidth().clip(Radius.inner)
                    .background(if (r.redate) c.cautionSoft else c.surfaceMuted)
                    .padding(14.dp),
            ) {
                Text(
                    if (r.redate) "Meets ACOG criteria for redating" else "Within ACOG limits — LMP dating retained",
                    style = Theme.type.bodyStrong,
                    color = if (r.redate) c.caution else c.text,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Scan ${r.ultrasoundGa.label} vs LMP ${r.lmpGaAtScan.label} on the scan date — " +
                        "${r.discrepancyDays} day${if (r.discrepancyDays == 1) "" else "s"} apart. " +
                        "ACOG: redate if the difference is more than ${r.thresholdDays} days at ${r.bandLabel} (LMP gestational age).",
                    style = Theme.type.caption,
                    color = c.textSecondary,
                )
                if (result.lmpEdd != null && result.ultrasoundEdd != null) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "EDD by LMP ${result.lmpEdd.pretty()}  ·  by scan ${result.ultrasoundEdd.pretty()}",
                        style = Theme.type.caption,
                        color = c.textTertiary,
                    )
                }
            }
        }

        result.warnings.forEach { warning ->
            Spacer(Modifier.height(10.dp))
            Notice(warning)
        }

        Spacer(Modifier.height(16.dp))
        GuidelineBasis(
            listOfNotNull(
                cite("acog-co700", if (result.redating != null) "Table 1 — redating thresholds" else "EDD from LMP and ultrasound"),
                if (result.termCategory != TermCategory.Preterm) cite("acog-co579") else null,
            ),
        )
    }
}

@Composable
private fun PregnancyProgress(gaDays: Int) {
    val c = Theme.colors
    val fraction = (gaDays / 280f).coerceIn(0f, 1f)
    Column {
        Box(Modifier.fillMaxWidth().height(6.dp).clip(Radius.chip).background(c.surfaceMuted)) {
            Box(Modifier.fillMaxWidth(fraction).fillMaxHeight().clip(Radius.chip).background(c.accent))
            // Trimester boundaries at 14 and 28 weeks.
            Row(Modifier.fillMaxSize()) {
                Spacer(Modifier.weight(14f))
                Box(Modifier.width(2.dp).fillMaxHeight().background(c.surface))
                Spacer(Modifier.weight(14f))
                Box(Modifier.width(2.dp).fillMaxHeight().background(c.surface))
                Spacer(Modifier.weight(12f))
            }
        }
        Spacer(Modifier.height(6.dp))
        Row(Modifier.fillMaxWidth()) {
            Text("T1", style = Theme.type.caption, color = c.textTertiary, modifier = Modifier.weight(14f))
            Text("T2", style = Theme.type.caption, color = c.textTertiary, modifier = Modifier.weight(14f))
            Row(Modifier.weight(12f)) {
                Text("T3", style = Theme.type.caption, color = c.textTertiary, modifier = Modifier.weight(1f))
                Text("40w", style = Theme.type.caption, color = c.textTertiary)
            }
        }
    }
}

@Composable
private fun Notice(text: String, modifier: Modifier = Modifier) {
    val c = Theme.colors
    Row(
        modifier.fillMaxWidth().clip(Radius.inner).background(c.cautionSoft).padding(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Icon(Icons.Outlined.WarningAmber, null, tint = c.caution, modifier = Modifier.size(16.dp).padding(top = 1.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, style = Theme.type.caption, color = c.caution)
    }
}

// ---------------------------------------------------------------------------------------------
// Schedule
// ---------------------------------------------------------------------------------------------

private enum class ItemStatus { DueNow, Upcoming, Passed, FirstVisit }

private fun statusOf(item: ScheduleItem, gaDays: Int?): ItemStatus {
    val w = item.window ?: return ItemStatus.FirstVisit
    if (gaDays == null) return ItemStatus.Upcoming
    return when {
        gaDays < w.startDays -> ItemStatus.Upcoming
        gaDays > w.endDays -> ItemStatus.Passed
        else -> ItemStatus.DueNow
    }
}

private fun LazyListScope.scheduleSection(state: PregnancyState, result: DatingResult?, today: LocalDate) {
    val filtered = Schedule.items.filter {
        when (state.filter) {
            GuidelineFilter.All -> true
            GuidelineFilter.India -> it.authority.india
            GuidelineFilter.International -> !it.authority.india
        }
    }
    val gaDays = result?.gaToday?.totalDays
    val byStatus = filtered.groupBy { statusOf(it, gaDays) }
    fun sorted(list: List<ScheduleItem>?) = list.orEmpty().sortedWith(compareBy({ it.window?.startDays ?: -1 }, { it.window?.endDays ?: -1 }))

    item {
        Column(Modifier.padding(horizontal = Gutter).padding(top = 28.dp)) {
            Text("Antenatal schedule", style = Theme.type.headline, color = Theme.colors.text)
            Spacer(Modifier.height(4.dp))
            Text(
                if (result == null) "Tests, scans, vaccines and supplements by gestational age. Enter an LMP to see dates."
                else "Tests, scans, vaccines and supplements with dates for this pregnancy.",
                style = Theme.type.caption,
                color = Theme.colors.textSecondary,
            )
            Spacer(Modifier.height(14.dp))
            Segmented(
                options = GuidelineFilter.entries.map { it to it.label },
                selected = state.filter,
                onSelect = { state.filter = it },
            )
        }
    }

    fun section(title: String, list: List<ScheduleItem>, status: ItemStatus?, collapsible: Boolean = false) {
        if (list.isEmpty()) return
        item(key = "h-$title") {
            if (collapsible) {
                Row(
                    Modifier.fillMaxWidth().clickable { state.showPast = !state.showPast }.padding(horizontal = Gutter),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    SectionHeader(title, Modifier.weight(1f), trailing = "${list.size}")
                    Icon(
                        Icons.Outlined.ExpandMore, null, tint = Theme.colors.textTertiary,
                        modifier = Modifier.padding(top = 18.dp, start = 6.dp).size(18.dp).rotate(if (state.showPast) 180f else 0f),
                    )
                }
            } else {
                SectionHeader(title, Modifier.padding(horizontal = Gutter), trailing = "${list.size}")
            }
        }
        if (!collapsible || state.showPast) {
            item(key = "g-$title") { ScheduleGroup(list, result, status, today) }
        }
    }

    if (result != null) {
        section("Due now", sorted(byStatus[ItemStatus.DueNow]), ItemStatus.DueNow)
        section("Coming up", sorted(byStatus[ItemStatus.Upcoming]), ItemStatus.Upcoming)
        section("At the first visit", sorted(byStatus[ItemStatus.FirstVisit]), ItemStatus.FirstVisit)
        section("Window passed", sorted(byStatus[ItemStatus.Passed]), ItemStatus.Passed, collapsible = true)
    } else {
        section("At the first visit", sorted(byStatus[ItemStatus.FirstVisit]), ItemStatus.FirstVisit)
        section("By gestational age", sorted(byStatus[ItemStatus.Upcoming]), null)
    }

    item {
        Text(
            "Schedule items show what each guideline recommends. Tick-off and record-keeping are not stored by the app.",
            style = Theme.type.caption,
            color = Theme.colors.textTertiary,
            modifier = Modifier.padding(horizontal = Gutter).padding(top = 20.dp),
        )
    }
}

@Composable
private fun ScheduleGroup(items: List<ScheduleItem>, result: DatingResult?, status: ItemStatus?, today: LocalDate) {
    val c = Theme.colors
    Column(
        Modifier.padding(horizontal = Gutter).fillMaxWidth().clip(Radius.card)
            .background(c.surface).border(1.dp, c.hairline, Radius.card),
    ) {
        items.forEachIndexed { i, item ->
            if (i > 0) Hairline(Modifier.padding(start = 38.dp))
            ScheduleRow(item, result, status, today)
        }
    }
}

@Composable
private fun ScheduleRow(item: ScheduleItem, result: DatingResult?, status: ItemStatus?, today: LocalDate) {
    val c = Theme.colors
    var expanded by remember { mutableStateOf(false) }
    val dot: Color = when (status) {
        ItemStatus.DueNow -> c.positive
        ItemStatus.Upcoming -> c.accent
        ItemStatus.FirstVisit -> c.textSecondary
        ItemStatus.Passed, null -> c.textTertiary
    }
    Column(
        Modifier.fillMaxWidth().clickable { expanded = !expanded }.animateContentSize()
            .padding(start = 16.dp, end = 14.dp, top = 14.dp, bottom = 14.dp),
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Box(Modifier.padding(top = 7.dp, end = 14.dp).size(8.dp).clip(CircleShape).background(dot))
            Column(Modifier.weight(1f)) {
                Text(item.title, style = Theme.type.bodyStrong, color = if (status == ItemStatus.Passed) c.textSecondary else c.text)
                Spacer(Modifier.height(2.dp))
                Text(windowText(item, result, status, today), style = Theme.type.caption, color = c.textSecondary)
                Spacer(Modifier.height(7.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Tag(item.kind.label)
                    Tag(item.authority.short, fg = if (item.authority.india) c.positive else c.accent, bg = if (item.authority.india) c.positiveSoft else c.accentSoft)
                    if (item.appliesTo != null) Tag("Selected", fg = c.caution, bg = c.cautionSoft)
                }
            }
            Icon(
                Icons.Outlined.ExpandMore, if (expanded) "Collapse" else "Expand", tint = c.textTertiary,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp).size(20.dp).rotate(if (expanded) 180f else 0f),
            )
        }
        if (expanded) {
            Column(Modifier.padding(start = 22.dp, top = 12.dp)) {
                if (item.appliesTo != null) {
                    Text(item.appliesTo, style = Theme.type.label, color = c.caution)
                    Spacer(Modifier.height(6.dp))
                }
                Text(item.detail, style = Theme.type.body, color = c.text)
                Spacer(Modifier.height(12.dp))
                GuidelineBasis(item.citations, compact = true)
            }
        }
    }
}

private fun windowText(item: ScheduleItem, result: DatingResult?, status: ItemStatus?, today: LocalDate): String {
    val w = item.window ?: return "First antenatal contact"
    if (result == null) return w.label
    val start = Dating.dateAtGa(result.edd, w.startDays)
    val end = Dating.dateAtGa(result.edd, w.endDays)
    val range = "${start.dayMonth()} – ${end.pretty()}"
    return when (status) {
        ItemStatus.Upcoming -> {
            val days = today.daysUntil(start)
            val inText = if (days >= 14) "in ${days / 7} weeks" else "in $days day${if (days == 1) "" else "s"}"
            "${w.label} · $range · $inText"
        }
        ItemStatus.DueNow -> "${w.label} · until ${end.pretty()}"
        else -> "${w.label} · $range"
    }
}
