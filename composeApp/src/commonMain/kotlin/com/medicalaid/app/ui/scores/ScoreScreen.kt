package com.medicalaid.app.ui.scores

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonChecked
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material.icons.outlined.WarningAmber
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.scores.Choice
import com.medicalaid.app.content.scores.Criterion
import com.medicalaid.app.content.scores.Level
import com.medicalaid.app.content.scores.Score
import com.medicalaid.app.content.scores.ScoreEngine
import com.medicalaid.app.ui.components.GuidelineBasis
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.components.Tag
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

private fun pointsLabel(p: Double): String {
    val s = ScoreEngine.format(kotlin.math.abs(p))
    return when {
        p > 0 -> "+$s"
        p < 0 -> "−$s"
        else -> "0"
    }
}

@Composable
fun ScoreScreen(
    score: Score,
    onBack: () -> Unit,
    contentPadding: PaddingValues,
    initialAnswers: Map<String, Int> = emptyMap(),
) {
    val c = Theme.colors
    var answers by remember(score.id) { mutableStateOf(initialAnswers) }
    val total = ScoreEngine.total(score, answers)
    val band = total?.let { ScoreEngine.band(score, it) }
    val alerts = ScoreEngine.alerts(score, answers)
    val missing = ScoreEngine.unanswered(score, answers).size

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding() + 170.dp,
            ),
        ) {
            item {
                Column {
                    ScreenTitle(score.name, score.purpose, onBack = onBack)
                    Row(Modifier.padding(horizontal = Gutter).padding(top = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Tag(score.category.label, fg = c.accent, bg = c.accentSoft)
                        Spacer(Modifier.weight(1f))
                        if (answers.isNotEmpty()) {
                            Row(
                                Modifier.clip(Radius.chip).clickable { answers = emptyMap() }.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(Icons.Outlined.RestartAlt, null, tint = c.textSecondary, modifier = Modifier.size(16.dp))
                                Spacer(Modifier.width(4.dp))
                                Text("Reset", style = Theme.type.label, color = c.textSecondary)
                            }
                        }
                    }
                }
            }
            if (score.caveats.isNotEmpty()) {
                item {
                    Column(
                        Modifier.padding(horizontal = Gutter).padding(top = 14.dp).fillMaxWidth()
                            .clip(Radius.card).background(c.cautionSoft).padding(16.dp),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Outlined.WarningAmber, null, tint = c.caution, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(8.dp))
                            Text("Before you use it", style = Theme.type.bodyStrong, color = c.caution)
                        }
                        score.caveats.forEach { cv ->
                            Spacer(Modifier.height(8.dp))
                            Text(cv.text, style = Theme.type.caption, color = c.text)
                            Spacer(Modifier.height(4.dp))
                            GuidelineBasis(cv.citations, compact = true)
                        }
                    }
                }
            }

            // Items, grouped into cards by section.
            val visible = score.items.filter { ScoreEngine.isVisible(it, answers) }
            val groups = mutableListOf<Pair<String?, MutableList<com.medicalaid.app.content.scores.ScoreItem>>>()
            visible.forEach { item ->
                if (groups.isEmpty() || (item.section != null && item.section != groups.last().first)) {
                    groups += item.section to mutableListOf(item)
                } else groups.last().second += item
            }
            groups.forEachIndexed { gi, (section, items) ->
                item(key = "sec-$gi-${section ?: ""}") {
                    if (section != null) SectionHeader(section, Modifier.padding(horizontal = Gutter))
                    else Spacer(Modifier.height(16.dp))
                }
                item(key = "grp-$gi-${items.first().id}") {
                    Column(
                        Modifier.padding(horizontal = Gutter).fillMaxWidth().clip(Radius.card)
                            .background(c.surface).border(1.dp, c.hairline, Radius.card),
                    ) {
                        items.forEachIndexed { i, item ->
                            if (i > 0) Hairline(Modifier.padding(start = 18.dp))
                            when (item) {
                                is Criterion -> CriterionRow(item, answers[item.id] == 1) {
                                    answers = answers + (item.id to if (answers[item.id] == 1) 0 else 1)
                                }
                                is Choice -> ChoiceBlock(item, answers[item.id]) { idx -> answers = answers + (item.id to idx) }
                            }
                        }
                    }
                }
            }
            item {
                Column(Modifier.padding(horizontal = Gutter).padding(top = 24.dp)) {
                    Text("Sources", style = Theme.type.label, color = c.textSecondary)
                    Spacer(Modifier.height(8.dp))
                    GuidelineBasis(
                        (score.citations + score.caveats.flatMap { it.citations }).distinctBy { it.sourceId to it.locator },
                    )
                }
            }
        }

        // Sticky result panel.
        Column(
            Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                .padding(horizontal = 12.dp)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(bottom = 10.dp)
                .clip(Radius.card)
                .background(if (c.isDark) c.surfaceMuted else c.text)
                .padding(horizontal = 18.dp, vertical = 14.dp),
        ) {
            val onDark = if (c.isDark) c.text else c.background
            val sub = if (c.isDark) c.textSecondary else c.textTertiary
            AnimatedContent(targetState = Triple(total, band, missing), transitionSpec = { fadeIn() togetherWith fadeOut() }, label = "result") { (t, b, m) ->
                if (t == null) {
                    Column {
                        Text("Answer $m more item${if (m == 1) "" else "s"}", style = Theme.type.bodyStrong, color = onDark)
                        Text("Unticked criteria count as absent.", style = Theme.type.caption, color = sub)
                    }
                } else {
                    Row(verticalAlignment = Alignment.Top) {
                        if (score.showTotal) {
                            Text(ScoreEngine.format(t), style = Theme.type.title, color = onDark)
                            Spacer(Modifier.width(14.dp))
                        }
                        Column(Modifier.weight(1f).padding(top = if (score.showTotal) 2.dp else 0.dp)) {
                            if (b != null) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(Modifier.size(8.dp).clip(CircleShape).background(levelColor(b.level)))
                                    Spacer(Modifier.width(8.dp))
                                    Text(b.title, style = Theme.type.bodyStrong, color = onDark)
                                }
                                Spacer(Modifier.height(2.dp))
                                Text(b.meaning, style = Theme.type.caption, color = sub, maxLines = 4)
                            }
                        }
                    }
                }
            }
            alerts.forEach { a ->
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.Top) {
                    Icon(Icons.Outlined.WarningAmber, null, tint = c.caution, modifier = Modifier.padding(top = 1.dp).size(14.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(a, style = Theme.type.caption, color = onDark)
                }
            }
        }
    }
}

@Composable
private fun levelColor(level: Level): Color {
    val c = Theme.colors
    return when (level) {
        Level.Low -> c.positive
        Level.Moderate -> c.caution
        Level.High -> c.danger
        Level.Info -> c.accent
    }
}

@Composable
private fun CriterionRow(item: Criterion, checked: Boolean, onToggle: () -> Unit) {
    val c = Theme.colors
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onToggle)
            .background(if (checked) c.accentSoft else c.surface)
            .padding(horizontal = 16.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier.size(22.dp).clip(CircleShape)
                .then(if (checked) Modifier.background(c.accent) else Modifier.border(1.5.dp, c.textTertiary, CircleShape)),
            contentAlignment = Alignment.Center,
        ) {
            if (checked) Icon(Icons.Outlined.CheckCircle, null, tint = c.onAccent, modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(item.label, style = Theme.type.bodyStrong, color = c.text)
            if (item.hint != null) Text(item.hint, style = Theme.type.caption, color = c.textSecondary)
        }
        Spacer(Modifier.width(10.dp))
        PointsBadge(item.points, checked)
    }
}

@Composable
private fun ChoiceBlock(item: Choice, selected: Int?, onSelect: (Int) -> Unit) {
    val c = Theme.colors
    Column(Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            Text(item.label, style = Theme.type.bodyStrong, color = c.text)
            if (item.hint != null) Text(item.hint, style = Theme.type.caption, color = c.textSecondary)
        }
        Spacer(Modifier.height(4.dp))
        item.options.forEachIndexed { idx, opt ->
            val active = selected == idx
            Row(
                Modifier.fillMaxWidth().padding(horizontal = 8.dp).clip(RoundedCornerShape(12.dp))
                    .background(if (active) c.accentSoft else c.surface)
                    .clickable { onSelect(idx) }
                    .padding(horizontal = 8.dp, vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    if (active) Icons.Outlined.RadioButtonChecked else Icons.Outlined.RadioButtonUnchecked,
                    null, tint = if (active) c.accent else c.textTertiary, modifier = Modifier.size(20.dp),
                )
                Spacer(Modifier.width(10.dp))
                Text(opt.label, style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
                Spacer(Modifier.width(8.dp))
                PointsBadge(opt.points, active)
            }
        }
    }
}

@Composable
private fun PointsBadge(points: Double, active: Boolean) {
    val c = Theme.colors
    Text(
        pointsLabel(points),
        style = Theme.type.label,
        color = if (active) c.accent else c.textSecondary,
        modifier = Modifier.clip(Radius.chip).background(if (active) c.surface else c.surfaceMuted)
            .padding(horizontal = 8.dp, vertical = 3.dp),
    )
}
