package com.medicalaid.app.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Block
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.rotate
import com.medicalaid.app.ui.Library
import com.medicalaid.app.ui.LocalCurrentPage
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Citation
import com.medicalaid.app.content.Sources
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

/** Standard horizontal gutter for every screen. */
val Gutter = 20.dp

@Composable
fun SurfaceCard(
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(18.dp),
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val c = Theme.colors
    Column(
        modifier
            .fillMaxWidth()
            .clip(Radius.card)
            .background(c.surface)
            .border(1.dp, c.hairline, Radius.card)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .animateContentSize()
            .padding(padding),
        content = content,
    )
}

@Composable
fun Overline(text: String, modifier: Modifier = Modifier, color: Color = Theme.colors.textTertiary) {
    Text(text.uppercase(), style = Theme.type.overline, color = color, modifier = modifier)
}

@Composable
fun SectionHeader(title: String, modifier: Modifier = Modifier, trailing: String? = null) {
    Row(
        modifier.fillMaxWidth().padding(top = 28.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Overline(title, Modifier.weight(1f))
        if (trailing != null) Text(trailing, style = Theme.type.caption, color = Theme.colors.textTertiary)
    }
}

@Composable
fun Tag(text: String, fg: Color = Theme.colors.textSecondary, bg: Color = Theme.colors.surfaceMuted) {
    Box(
        Modifier.clip(Radius.chip).background(bg).padding(horizontal = 9.dp, vertical = 3.dp),
    ) {
        Text(text, style = Theme.type.caption, color = fg, maxLines = 1)
    }
}

@Composable
fun Dot(color: Color, size: Dp = 7.dp) {
    Box(Modifier.size(size).clip(CircleShape).background(color))
}

@Composable
fun Hairline(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth().height(1.dp).background(Theme.colors.hairline))
}

@Composable
fun Bullets(items: List<String>, modifier: Modifier = Modifier, color: Color = Theme.colors.text) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEach { item ->
            Row {
                Box(Modifier.padding(top = 9.dp, end = 12.dp)) { Dot(Theme.colors.textTertiary, 4.dp) }
                Text(item, style = Theme.type.body, color = color)
            }
        }
    }
}

/** Small label + value pair used in result cards. */
@Composable
fun LabeledValue(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(label, style = Theme.type.caption, color = Theme.colors.textSecondary)
        Spacer(Modifier.height(2.dp))
        Text(value, style = Theme.type.bodyStrong, color = Theme.colors.text)
    }
}

/**
 * The "Guideline basis" footer. Each citation is a row that opens the official source document.
 * Shown under every recommendation in the app.
 */
@Composable
fun GuidelineBasis(citations: List<Citation>, modifier: Modifier = Modifier, compact: Boolean = false) {
    if (citations.isEmpty()) return
    if (compact) {
        CompactBasis(citations, modifier)
        return
    }
    val c = Theme.colors
    val uri = LocalUriHandler.current
    var open by remember(citations) { mutableStateOf(false) }
    Column(
        modifier
            .fillMaxWidth()
            .clip(Radius.inner)
            .background(c.surfaceMuted)
            .padding(vertical = 4.dp),
    ) {
        SourcesToggle(citations, open, Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) { open = !open }
        if (open) citations.distinct().forEach { citation ->
            val source = Sources.byId(citation.sourceId)
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = androidx.compose.foundation.LocalIndication.current,
                    ) { runCatching { uri.openUri(source.url) } }
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        "${sourceLabel(source)} · ${source.title} (${source.year})",
                        style = Theme.type.caption,
                        color = c.textSecondary,
                    )
                    if (citation.locator != null) {
                        Text(citation.locator, style = Theme.type.caption, color = c.textTertiary)
                    }
                }
                Spacer(Modifier.width(10.dp))
                Icon(
                    Icons.AutoMirrored.Outlined.OpenInNew,
                    contentDescription = "Open source",
                    tint = c.textTertiary,
                    modifier = Modifier.padding(top = 2.dp).size(14.dp),
                )
            }
        }
    }
}

/**
 * One line that names the bodies behind a recommendation ("Sources · NICE, MoHFW +1") and expands to the full,
 * tappable list. Sources stay one tap away without crowding the clinical text.
 */
@Composable
private fun SourcesToggle(citations: List<Citation>, open: Boolean, modifier: Modifier, onToggle: () -> Unit) {
    val c = Theme.colors
    val bodies = citations.map { Sources.byId(it.sourceId).authority.short }.distinct()
    val shown = bodies.take(2).joinToString(", ") + if (bodies.size > 2) " +${bodies.size - 2}" else ""
    Row(
        Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).clickable(onClick = onToggle).then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.Outlined.Info, null, tint = c.textTertiary, modifier = Modifier.size(13.dp))
        Spacer(Modifier.width(6.dp))
        Text("Sources · $shown", style = Theme.type.caption, color = c.textTertiary, modifier = Modifier.weight(1f),
            maxLines = 1, overflow = TextOverflow.Ellipsis)
        Icon(
            Icons.Outlined.ExpandMore, if (open) "Hide sources" else "Show sources", tint = c.textTertiary,
            modifier = Modifier.size(16.dp).rotate(if (open) 180f else 0f),
        )
    }
}

/** "NICE Guideline NG201", without repeating the body when the document name already includes it. */
fun sourceLabel(source: com.medicalaid.app.content.Source): String = when {
    // Journal articles: the citation itself ("Review (J Emerg Trauma Shock 2011;4:378–384)") says what it is.
    source.authority == com.medicalaid.app.content.Authority.Paper -> source.document
    source.document.startsWith(source.authority.short, ignoreCase = true) -> source.document
    else -> "${source.authority.short} ${source.document}"
}

/** Small source lines at the foot of a card: "ⓘ ACOG Practice Bulletin No. 189 · Figure 1 ↗". */
@Composable
private fun CompactBasis(citations: List<Citation>, modifier: Modifier = Modifier) {
    val c = Theme.colors
    val uri = LocalUriHandler.current
    var open by remember(citations) { mutableStateOf(false) }
    Column(modifier.fillMaxWidth()) {
        Hairline()
        Spacer(Modifier.height(6.dp))
        SourcesToggle(citations, open, Modifier.padding(vertical = 2.dp)) { open = !open }
        if (open) citations.distinct().forEach { citation ->
            val source = Sources.byId(citation.sourceId)
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { runCatching { uri.openUri(source.url) } }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Icon(Icons.Outlined.Info, null, tint = c.accent, modifier = Modifier.padding(top = 2.dp).size(13.dp))
                Spacer(Modifier.width(7.dp))
                Text(
                    buildString {
                        append(sourceLabel(source))
                        if (citation.locator != null) append(" · ").append(citation.locator)
                    },
                    style = Theme.type.caption,
                    color = c.textSecondary,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(8.dp))
                Icon(
                    Icons.AutoMirrored.Outlined.OpenInNew, "Open source", tint = c.textTertiary,
                    modifier = Modifier.padding(top = 2.dp).size(13.dp),
                )
            }
        }
    }
}

@Composable
fun ScreenTitle(
    title: String,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    onMenu: (() -> Unit)? = null,
    /** Small coloured category label above the title, e.g. "Obstetric emergency". */
    label: String? = null,
    accent: Accent? = null,
) {
    val c = Theme.colors
    val page = if (onBack != null) LocalCurrentPage.current else null
    Column(modifier.fillMaxWidth()) {
        if (onBack != null) {
            // Compact app bar: back arrow and (on topic pages) the pin, no circles.
            Row(Modifier.fillMaxWidth().padding(horizontal = 6.dp).height(52.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(44.dp).clip(RoundedCornerShape(10.dp)).clickable(onClick = onBack),
                    contentAlignment = Alignment.Center,
                ) { Icon(Icons.AutoMirrored.Outlined.ArrowBack, "Back", tint = c.text, modifier = Modifier.size(22.dp)) }
                Spacer(Modifier.weight(1f))
                if (page != null) {
                    val pinned = Library.isPinned(page)
                    Box(
                        Modifier.size(44.dp).clip(RoundedCornerShape(10.dp)).clickable { Library.togglePin(page) },
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            if (pinned) Icons.Filled.PushPin else Icons.Outlined.PushPin,
                            if (pinned) "Unpin" else "Pin to Home",
                            tint = if (pinned) c.accent else c.textSecondary, modifier = Modifier.size(20.dp),
                        )
                    }
                }
            }
        }
        Column(Modifier.padding(horizontal = Gutter).padding(top = if (onBack != null) 0.dp else 18.dp, bottom = 8.dp)) {
            if (label != null) {
                val a = accent ?: Accent(c.accent, c.accentSoft)
                Text(
                    label.uppercase(), style = Theme.type.overline, color = a.fg, maxLines = 1,
                    modifier = Modifier.padding(bottom = 8.dp).clip(RoundedCornerShape(5.dp)).background(a.bg)
                        .padding(horizontal = 6.dp, vertical = 3.dp),
                )
            }
            Text(
                title,
                style = if (onBack != null) Theme.type.title.copy(fontSize = 26.sp, lineHeight = 31.sp) else Theme.type.title,
                color = c.text,
            )
            if (subtitle != null) {
                Spacer(Modifier.height(4.dp))
                Text(subtitle, style = Theme.type.body, color = c.textSecondary, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

/** A section colour pair: foreground (text, icons) and soft background. */
data class Accent(val fg: Color, val bg: Color)

@Composable
fun categoryAccent(category: com.medicalaid.app.content.protocols.ProtocolCategory): Accent = when (category) {
    com.medicalaid.app.content.protocols.ProtocolCategory.Obstetric -> Accent(Theme.colors.rose, Theme.colors.roseSoft)
    else -> Accent(Theme.colors.danger, Theme.colors.dangerSoft)
}

@Composable
fun populationAccent(population: com.medicalaid.app.content.Population): Accent = when (population) {
    com.medicalaid.app.content.Population.Pregnancy -> Accent(Theme.colors.rose, Theme.colors.roseSoft)
    com.medicalaid.app.content.Population.Infections -> Accent(Theme.colors.positive, Theme.colors.positiveSoft)
    com.medicalaid.app.content.Population.General -> Accent(Theme.colors.blue, Theme.colors.blueSoft)
}

/** Lines that tell the clinician what NOT to do; shown as red callouts. */
fun isDont(text: String): Boolean =
    Regex("^(do not|don['’]t|never|avoid|contraindicated|stop all)\\b", RegexOption.IGNORE_CASE).containsMatchIn(text.trim())

/** Red "Do not" or amber caution callout. */
@Composable
fun Callout(text: String, danger: Boolean = true, modifier: Modifier = Modifier) {
    val c = Theme.colors
    Row(
        modifier.fillMaxWidth().clip(Radius.inner).background(if (danger) c.dangerSoft else c.cautionSoft)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Icon(
            if (danger) Icons.Outlined.Block else Icons.Outlined.WarningAmber, null,
            tint = if (danger) c.danger else c.caution, modifier = Modifier.padding(top = 1.dp).size(17.dp),
        )
        Spacer(Modifier.width(10.dp))
        Text(text, style = Theme.type.body.copy(fontSize = 14.sp, lineHeight = 19.sp), color = c.text)
    }
}

/**
 * Teal weight panel: large value, editable, with −/+ buttons. Drives every weight-based calculator below it.
 * Accepts [min]..[max] kg; the value is kept as text so the clinician can type freely.
 */
@Composable
fun WeightPanel(weightText: String, onChange: (String) -> Unit, modifier: Modifier = Modifier, hint: String = "Doses below are calculated for this weight") {
    val c = Theme.colors
    val onPanel = if (c.isDark) c.background else Color.White
    fun step(by: Int) {
        val now = weightText.replace(',', '.').toDoubleOrNull() ?: 0.0
        val next = (now + by).coerceIn(0.0, 250.0)
        onChange(if (next == next.toLong().toDouble()) next.toLong().toString() else next.toString())
    }
    Row(
        modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(c.accent).padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text("Weight", style = Theme.type.label, color = onPanel.copy(alpha = 0.85f))
            Row(verticalAlignment = Alignment.Bottom) {
                androidx.compose.foundation.text.BasicTextField(
                    weightText, { v -> onChange(v.filter { it.isDigit() || it == '.' || it == ',' }.take(5)) },
                    singleLine = true,
                    textStyle = Theme.type.title.copy(color = onPanel, fontSize = 32.sp, lineHeight = 38.sp),
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Decimal),
                    cursorBrush = androidx.compose.ui.graphics.SolidColor(onPanel),
                    modifier = Modifier.width((if (weightText.isEmpty()) 30 else 20 + 19 * weightText.length).dp),
                    decorationBox = { inner ->
                        Box { if (weightText.isEmpty()) Text("—", style = Theme.type.title.copy(fontSize = 32.sp), color = onPanel.copy(alpha = 0.6f)); inner() }
                    },
                )
                Text("kg", style = Theme.type.headline, color = onPanel, modifier = Modifier.padding(bottom = 6.dp))
            }
            Text(hint, style = Theme.type.caption, color = onPanel.copy(alpha = 0.8f), maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        listOf(-1 to "Decrease weight", 1 to "Increase weight").forEach { (by, desc) ->
            Spacer(Modifier.width(8.dp))
            Box(
                Modifier.size(44.dp).clip(RoundedCornerShape(12.dp)).background(onPanel.copy(alpha = 0.14f)).clickable { step(by) },
                contentAlignment = Alignment.Center,
            ) { Text(if (by < 0) "−" else "+", style = Theme.type.headline.copy(fontSize = 22.sp), color = onPanel, modifier = Modifier.semantics { contentDescription = desc }) }
        }
    }
}

/** A weight-based dose as a card: label, then the calculated dose in large figures, then the rule and any cap. */
@Composable
fun StepDoseCard(d: com.medicalaid.app.content.protocols.StepDose, weightKg: Double?) {
    val c = Theme.colors
    val w = d.dose
    val perKg = if (w.minPerKg == w.maxPerKg) "${com.medicalaid.app.ui.formatNumber(w.minPerKg)} ${w.unit}/kg"
    else "${com.medicalaid.app.ui.formatNumber(w.minPerKg)}–${com.medicalaid.app.ui.formatNumber(w.maxPerKg)} ${w.unit}/kg"
    Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.accentSoft.copy(alpha = if (c.isDark) 1f else 0.6f)).padding(horizontal = 12.dp, vertical = 10.dp)) {
        Text(d.label, style = Theme.type.caption, color = c.textSecondary)
        if (weightKg == null) {
            Text("$perKg ${w.per}", style = Theme.type.bodyStrong, color = c.text)
            Text("Enter weight to calculate" + (d.maxTotal?.let { " · max ${com.medicalaid.app.ui.formatNumber(it)} ${w.unit}" } ?: ""), style = Theme.type.caption, color = c.textTertiary)
        } else {
            val cap = d.cap(weightKg)
            val (lo, hi) = d.range(weightKg)
            val value = if (lo == hi) com.medicalaid.app.ui.formatNumber(lo) else "${com.medicalaid.app.ui.formatNumber(lo)}–${com.medicalaid.app.ui.formatNumber(hi)}"
            Text(
                "$value ${w.unit}", color = c.text,
                style = Theme.type.title.copy(fontSize = 21.sp, lineHeight = 27.sp, fontFeatureSettings = "tnum"),
            )
            Text(w.per, style = Theme.type.caption, color = c.text)
            Text(
                "$perKg × ${com.medicalaid.app.ui.formatNumber(weightKg)} kg" + (cap?.let { " · capped at ${com.medicalaid.app.ui.formatNumber(it)} ${w.unit}" } ?: ""),
                style = Theme.type.caption, color = if (cap != null && hi >= cap) c.caution else c.textTertiary,
            )
        }
        if (d.note != null) Text(d.note, style = Theme.type.caption, color = c.textSecondary, modifier = Modifier.padding(top = 2.dp))
    }
}

/** Segmented control. */
@Composable
fun <T> Segmented(options: List<Pair<T, String>>, selected: T, onSelect: (T) -> Unit, modifier: Modifier = Modifier) {
    val c = Theme.colors
    Row(
        modifier.clip(Radius.chip).background(c.surfaceMuted).padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        options.forEach { (value, label) ->
            val active = value == selected
            Box(
                Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(if (active) c.surface else Color.Transparent)
                    .then(if (active) Modifier.border(1.dp, c.hairline, RoundedCornerShape(4.dp)) else Modifier)
                    .clickable { onSelect(value) }
                    .padding(horizontal = 14.dp, vertical = 7.dp),
            ) {
                Text(label, style = Theme.type.label, color = if (active) c.text else c.textSecondary)
            }
        }
    }
}

/**
 * Text tabs with an underline under the selected one, scrolling sideways when they do not fit.
 * Used for guideline filters ("All · India · ESC · NICE …").
 */
@Composable
fun <T> FilterTabs(options: List<Pair<T, String>>, selected: T, onSelect: (T) -> Unit, modifier: Modifier = Modifier) {
    val c = Theme.colors
    Column(modifier.fillMaxWidth()) {
        Row(
            Modifier.horizontalScroll(androidx.compose.foundation.rememberScrollState()).padding(horizontal = Gutter),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
        ) {
            options.forEach { (value, label) ->
                val active = value == selected
                Column(
                    Modifier.width(androidx.compose.foundation.layout.IntrinsicSize.Max)
                        .clickable { onSelect(value) }.padding(top = 6.dp),
                ) {
                    Text(
                        label, style = if (active) Theme.type.bodyStrong else Theme.type.body,
                        color = if (active) c.text else c.textSecondary, maxLines = 1,
                    )
                    Spacer(Modifier.height(8.dp))
                    Box(Modifier.fillMaxWidth().height(2.dp).background(if (active) c.accent else Color.Transparent))
                }
            }
        }
        Hairline(Modifier.padding(horizontal = Gutter))
    }
}
