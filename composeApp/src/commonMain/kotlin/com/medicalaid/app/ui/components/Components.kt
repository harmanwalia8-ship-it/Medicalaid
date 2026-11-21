package com.medicalaid.app.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
        Modifier.clip(Radius.pill).background(bg).padding(horizontal = 9.dp, vertical = 3.dp),
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
    Column(
        modifier
            .fillMaxWidth()
            .clip(Radius.inner)
            .background(c.surfaceMuted)
            .padding(vertical = 6.dp),
    ) {
        Row(
            Modifier.padding(start = 14.dp, end = 14.dp, top = 6.dp, bottom = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Outlined.Info, null, tint = c.accent, modifier = Modifier.size(14.dp))
            Spacer(Modifier.width(6.dp))
            Text("Guideline basis", style = Theme.type.label, color = c.accent)
        }
        citations.distinct().forEach { citation ->
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
    Column(modifier.fillMaxWidth()) {
        Hairline()
        Spacer(Modifier.height(8.dp))
        citations.distinct().forEach { citation ->
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
) {
    Column(modifier.fillMaxWidth().padding(horizontal = Gutter).padding(top = 12.dp, bottom = 8.dp)) {
        val action = onBack ?: onMenu
        if (action != null) {
            Box(
                Modifier
                    .padding(bottom = 14.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Theme.colors.surface)
                    .border(1.dp, Theme.colors.hairline, CircleShape)
                    .clickable(onClick = action),
                contentAlignment = Alignment.Center,
            ) {
                if (onBack != null) {
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack, "Back", tint = Theme.colors.text, modifier = Modifier.size(20.dp))
                } else {
                    Icon(Icons.Outlined.Menu, "Menu", tint = Theme.colors.text, modifier = Modifier.size(20.dp))
                }
            }
        }
        Text(title, style = Theme.type.title, color = Theme.colors.text)
        if (subtitle != null) {
            Spacer(Modifier.height(4.dp))
            Text(subtitle, style = Theme.type.body, color = Theme.colors.textSecondary, maxLines = 3, overflow = TextOverflow.Ellipsis)
        }
    }
}

/** Segmented pill control. */
@Composable
fun <T> Segmented(options: List<Pair<T, String>>, selected: T, onSelect: (T) -> Unit, modifier: Modifier = Modifier) {
    val c = Theme.colors
    Row(
        modifier.clip(Radius.pill).background(c.surfaceMuted).padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        options.forEach { (value, label) ->
            val active = value == selected
            Box(
                Modifier
                    .clip(RoundedCornerShape(50))
                    .background(if (active) c.surface else Color.Transparent)
                    .then(if (active) Modifier.border(1.dp, c.hairline, RoundedCornerShape(50)) else Modifier)
                    .clickable { onSelect(value) }
                    .padding(horizontal = 14.dp, vertical = 7.dp),
            ) {
                Text(label, style = Theme.type.label, color = if (active) c.text else c.textSecondary)
            }
        }
    }
}
