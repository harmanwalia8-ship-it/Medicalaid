package com.medicalaid.app.ui.protocols

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Citation
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.ProtocolTool
import com.medicalaid.app.content.protocols.Step
import com.medicalaid.app.content.scores.Scores
import com.medicalaid.app.tools.StrokeTools
import com.medicalaid.app.ui.components.Bullets
import com.medicalaid.app.ui.components.GuidelineBasis
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.formatNumber
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme
import com.medicalaid.app.ui.tools.NumberField
import com.medicalaid.app.ui.tools.toDecimalOrNull

@Composable
fun ProtocolScreen(
    protocol: Protocol,
    onBack: () -> Unit,
    onOpenScore: (String) -> Unit,
    contentPadding: PaddingValues,
) {
    val c = Theme.colors
    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle(protocol.title, protocol.summary, onBack = onBack) }
        if (protocol.notes.isNotEmpty()) {
            item { NotesCard(protocol) }
        }
        item { Spacer(Modifier.height(16.dp)) }
        itemsIndexed(protocol.steps, key = { i, s -> "$i-${s.title}" }) { index, step ->
            StepRow(index + 1, step, isLast = index == protocol.steps.lastIndex, onOpenScore = onOpenScore)
        }
        item {
            Column(Modifier.padding(horizontal = Gutter).padding(top = 20.dp)) {
                Text("Sources", style = Theme.type.label, color = c.textSecondary)
                Spacer(Modifier.height(8.dp))
                val all = (protocol.citations + protocol.steps.flatMap { it.citations } + protocol.notes.flatMap { it.citations })
                    .map { it.sourceId }.distinct().map { Citation(it) }
                GuidelineBasis(all)
            }
        }
    }
}

@Composable
private fun NotesCard(protocol: Protocol) {
    val c = Theme.colors
    var open by remember(protocol.id) { mutableStateOf(false) }
    Column(
        Modifier.padding(horizontal = Gutter).padding(top = 12.dp).fillMaxWidth()
            .clip(Radius.card).background(c.cautionSoft),
    ) {
        Row(
            Modifier.fillMaxWidth().clickable { open = !open }.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text("Where guidelines differ", style = Theme.type.bodyStrong, color = c.caution)
                Text("${protocol.notes.size} notes · India and international", style = Theme.type.caption, color = c.textSecondary)
            }
            Icon(
                Icons.Outlined.ExpandMore, if (open) "Collapse" else "Expand", tint = c.caution,
                modifier = Modifier.size(20.dp).rotate(if (open) 180f else 0f),
            )
        }
        if (open) {
            Column(Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                protocol.notes.forEachIndexed { i, n ->
                    if (i > 0) Spacer(Modifier.height(12.dp))
                    Text(n.text, style = Theme.type.caption, color = c.text)
                    Spacer(Modifier.height(4.dp))
                    GuidelineBasis(n.citations, compact = true)
                }
            }
        }
    }
}

@Composable
private fun StepRow(number: Int, step: Step, isLast: Boolean, onOpenScore: (String) -> Unit) {
    val c = Theme.colors
    Row(Modifier.padding(horizontal = Gutter).height(IntrinsicSize.Min)) {
        // Timeline rail
        Column(Modifier.width(32.dp).fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                Modifier.size(28.dp).clip(CircleShape).background(c.accent),
                contentAlignment = Alignment.Center,
            ) { Text("$number", style = Theme.type.label, color = c.onAccent, textAlign = TextAlign.Center) }
            if (!isLast) Box(Modifier.width(2.dp).weight(1f).background(c.hairline))
        }
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f).padding(bottom = 14.dp)) {
            SurfaceCard {
                Text(step.title, style = Theme.type.headline, color = c.text)
                if (step.timing != null) {
                    Spacer(Modifier.height(6.dp))
                    Row(
                        Modifier.clip(Radius.pill).background(c.accentSoft).padding(horizontal = 10.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(Icons.Outlined.AccessTime, null, tint = c.accent, modifier = Modifier.size(14.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(step.timing, style = Theme.type.label, color = c.accent)
                    }
                }
                if (step.points.isNotEmpty()) {
                    Spacer(Modifier.height(10.dp))
                    Bullets(step.points)
                }
                if (step.checklist.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Checklist(step.checklistTitle ?: "Checklist", step.checklist)
                }
                when (step.tool) {
                    ProtocolTool.OnsetClock -> { Spacer(Modifier.height(12.dp)); OnsetClock() }
                    ProtocolTool.ThrombolysisDose -> { Spacer(Modifier.height(12.dp)); ThrombolysisDose() }
                    null -> {}
                }
                val scores = step.scoreIds.mapNotNull { Scores.byId(it) }
                if (scores.isNotEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        scores.forEach { s ->
                            Text(
                                "${s.name} ›",
                                style = Theme.type.label, color = c.accent,
                                modifier = Modifier.clip(Radius.pill).background(c.accentSoft)
                                    .clickable { onOpenScore(s.id) }.padding(horizontal = 12.dp, vertical = 7.dp),
                            )
                        }
                    }
                }
                Spacer(Modifier.height(12.dp))
                GuidelineBasis(step.citations, compact = true)
            }
        }
    }
}

@Composable
private fun Checklist(title: String, items: List<String>) {
    val c = Theme.colors
    var checked by remember(items) { mutableStateOf(setOf<Int>()) }
    Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(vertical = 6.dp)) {
        Row(Modifier.padding(horizontal = 12.dp, vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(title.uppercase(), style = Theme.type.overline, color = c.textTertiary, modifier = Modifier.weight(1f))
            if (checked.isNotEmpty()) Text("${checked.size} ticked", style = Theme.type.caption, color = c.danger)
        }
        items.forEachIndexed { i, text ->
            val on = i in checked
            Row(
                Modifier.fillMaxWidth().clickable { checked = if (on) checked - i else checked + i }
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Icon(
                    if (on) Icons.Outlined.CheckBox else Icons.Outlined.CheckBoxOutlineBlank, null,
                    tint = if (on) c.danger else c.textTertiary, modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text(text, style = Theme.type.caption, color = c.text)
            }
        }
    }
}

@Composable
private fun OnsetClock() {
    val c = Theme.colors
    var hText by remember { mutableStateOf("") }
    var mText by remember { mutableStateOf("") }
    val hours = (hText.toDecimalOrNull() ?: 0.0) + (mText.toDecimalOrNull() ?: 0.0) / 60.0
    val entered = hText.isNotEmpty() || mText.isNotEmpty()
    Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(12.dp)) {
        Text("TIME SINCE LAST KNOWN WELL", style = Theme.type.overline, color = c.textTertiary)
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            NumberField(hText, { hText = it }, placeholder = "h", suffix = "h", modifier = Modifier.width(90.dp))
            Spacer(Modifier.width(8.dp))
            NumberField(mText, { mText = it }, placeholder = "min", suffix = "min", modifier = Modifier.width(100.dp))
        }
        if (entered) {
            Spacer(Modifier.height(10.dp))
            StrokeTools.windows.forEach { w ->
                val state = StrokeTools.state(w, hours)
                val (label, color) = when (state) {
                    StrokeTools.WindowState.Open -> "Open" to c.positive
                    StrokeTools.WindowState.Closed -> "Passed" to c.textTertiary
                    StrokeTools.WindowState.NotYet -> "Later" to c.textTertiary
                }
                Row(Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
                    Text(label, style = Theme.type.label, color = color, modifier = Modifier.width(56.dp))
                    Column(Modifier.weight(1f)) {
                        Text(w.label, style = Theme.type.bodyStrong, color = if (state == StrokeTools.WindowState.Open) c.text else c.textSecondary)
                        Text(w.note, style = Theme.type.caption, color = c.textSecondary)
                    }
                }
            }
            Spacer(Modifier.height(6.dp))
            Text("Windows are eligibility limits, not decisions — imaging and criteria apply.", style = Theme.type.caption, color = c.textTertiary)
        }
        Spacer(Modifier.height(8.dp))
        GuidelineBasis(
            listOf(Citation("st-aha-ais-2026", "§4.6.2–4.6.3; §4.7.2"), Citation("st-nice-ng128", "1.4.1; 1.4.5–1.4.8"), Citation("st-mohfw-stroke-2022")),
            compact = true,
        )
    }
}

@Composable
private fun ThrombolysisDose() {
    val c = Theme.colors
    var wText by remember { mutableStateOf("") }
    val weight = wText.toDecimalOrNull()?.takeIf { it in 30.0..250.0 }
    Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(12.dp)) {
        Text("DOSE BY WEIGHT", style = Theme.type.overline, color = c.textTertiary)
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Weight", style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
            NumberField(wText, { wText = it }, placeholder = "kg", suffix = "kg", modifier = Modifier.width(110.dp))
        }
        if (weight != null) {
            val a = StrokeTools.alteplase(weight)
            Spacer(Modifier.height(10.dp))
            DoseLine(
                "Alteplase ${formatNumber(a.totalMg)} mg",
                "Bolus ${formatNumber(a.bolusMg)} mg over 1 min, then ${formatNumber(a.infusionMg)} mg over ${StrokeTools.ALTEPLASE_INFUSION_MINUTES} min. " +
                    "AHA/ASA 2026 and MoHFW: 0.9 mg/kg, max 90 mg.",
            )
            Spacer(Modifier.height(8.dp))
            DoseLine(
                "Tenecteplase ${formatNumber(StrokeTools.tenecteplaseAhaBandMg(weight))} mg",
                "AHA/ASA 2026 Table 7 weight band (0.25 mg/kg, max 25 mg), single IV bolus.",
            )
            Spacer(Modifier.height(8.dp))
            val (lo, hi) = StrokeTools.tenecteplaseIndiaRangeMg(weight)
            DoseLine(
                if (lo == hi) "Tenecteplase ${formatNumber(lo)} mg · India" else "Tenecteplase ${formatNumber(lo)}–${formatNumber(hi)} mg · India",
                "MoHFW 2022: 0.2–0.25 mg/kg, max 20 mg, within 0–3 h (CDSCO-approved 0.2 mg/kg).",
            )
            Spacer(Modifier.height(6.dp))
            Text("Check product strength, local protocol and eligibility before giving.", style = Theme.type.caption, color = c.textTertiary)
        }
        Spacer(Modifier.height(8.dp))
        GuidelineBasis(listOf(Citation("st-aha-ais-2026", "§4.6.2 Rec 1; Table 7"), Citation("st-mohfw-stroke-2022", "Annexure VI")), compact = true)
    }
}

@Composable
private fun DoseLine(title: String, detail: String) {
    val c = Theme.colors
    Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surface).border(1.dp, c.hairline, Radius.inner).padding(10.dp)) {
        Text(title, style = Theme.type.bodyStrong, color = c.text)
        Text(detail, style = Theme.type.caption, color = c.textSecondary)
    }
}
