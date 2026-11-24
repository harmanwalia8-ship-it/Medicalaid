package com.medicalaid.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.medicalaid.app.Destination
import com.medicalaid.app.content.Conditions
import com.medicalaid.app.content.Search
import com.medicalaid.app.content.Sources
import com.medicalaid.app.content.drugs.Drugs
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.medicines.SearchField
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.outlined.MonitorHeart
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.ui.graphics.Color
import com.medicalaid.app.ui.plural

@Composable
fun HomeScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    onNavigate: (Destination) -> Unit,
    onOpenCondition: (String) -> Unit,
    onOpenDrug: (String) -> Unit,
    onMenu: () -> Unit,
    contentPadding: PaddingValues,
    onOpenScore: (String) -> Unit = {},
    onOpenProtocol: (String) -> Unit = {},
) {
    val focus = LocalFocusManager.current
    val scoreHits = remember(query) { if (query.isBlank()) emptyList() else com.medicalaid.app.content.scores.Scores.search(query) }
    val protocolHits = remember(query) { if (query.isBlank()) emptyList() else com.medicalaid.app.content.protocols.Protocols.search(query) }
    val conditionHits = remember(query) { Search.run(query).filter { it.drug == null }.map { it.condition } }
    val drugHits = remember(query) { if (query.isBlank()) emptyList() else Drugs.search(query) }

    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item {
            Row(Modifier.padding(horizontal = Gutter).padding(top = 18.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(32.dp).clip(RoundedCornerShape(9.dp)).background(Theme.colors.accent), contentAlignment = Alignment.Center) {
                    Icon(Icons.Outlined.MonitorHeart, null, tint = Theme.colors.onAccent, modifier = Modifier.size(19.dp))
                }
                Spacer(Modifier.width(10.dp))
                Text("Medicalaid", style = Theme.type.headline, color = Theme.colors.text, modifier = Modifier.weight(1f))
                Text(
                    "Offline ready", style = Theme.type.caption, color = Theme.colors.textSecondary,
                    modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(Theme.colors.surfaceMuted).padding(horizontal = 7.dp, vertical = 3.dp),
                )
            }
        }
        item {
            SearchField(
                query, onQueryChange, onDone = { focus.clearFocus() },
                placeholder = "Search conditions, drugs, scores…",
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 12.dp),
            )
        }

        if (query.isNotBlank()) {
            if (conditionHits.isEmpty() && drugHits.isEmpty() && scoreHits.isEmpty() && protocolHits.isEmpty()) {
                item {
                    Text(
                        "Nothing matches “$query”.",
                        style = Theme.type.body, color = Theme.colors.textSecondary,
                        modifier = Modifier.padding(horizontal = Gutter).padding(top = 28.dp),
                    )
                }
            }
            if (conditionHits.isNotEmpty()) {
                item { SectionHeader("Conditions", Modifier.padding(horizontal = Gutter), trailing = "${conditionHits.size}") }
                item {
                    RowGroup(conditionHits.map { Triple(it.title, "${it.population.label} · ${it.summary}") { onOpenCondition(it.id) } })
                }
            }
            if (drugHits.isNotEmpty()) {
                item { SectionHeader("Drugs", Modifier.padding(horizontal = Gutter), trailing = "${drugHits.size}") }
                item { RowGroup(drugHits.map { Triple(it.name, it.drugClass) { onOpenDrug(it.id) } }) }
            }
            if (protocolHits.isNotEmpty()) {
                item { SectionHeader("Protocols", Modifier.padding(horizontal = Gutter), trailing = "${protocolHits.size}") }
                item { RowGroup(protocolHits.map { Triple(it.title, it.summary) { onOpenProtocol(it.id) } }) }
            }
            if (scoreHits.isNotEmpty()) {
                item { SectionHeader("Scores", Modifier.padding(horizontal = Gutter), trailing = "${scoreHits.size}") }
                item { RowGroup(scoreHits.map { Triple(it.name, it.purpose) { onOpenScore(it.id) } }) }
            }
            return@LazyColumn
        }

        item {
            val open = com.medicalaid.app.ui.LocalOpenPage.current
            Row(
                Modifier.horizontalScroll(rememberScrollState()).padding(horizontal = Gutter).padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                QUICK.forEach { (label, id) ->
                    Text(
                        label, style = Theme.type.label, color = Theme.colors.text,
                        modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(Theme.colors.surface)
                            .border(1.dp, Theme.colors.hairline, RoundedCornerShape(8.dp))
                            .clickable { open(com.medicalaid.app.ui.PageKey.protocol(id)) }.padding(horizontal = 11.dp, vertical = 8.dp),
                    )
                }
            }
        }
        val pinned = com.medicalaid.app.ui.Library.pinned
        val recent = com.medicalaid.app.ui.Library.recent.filter { it !in pinned }.take(4)
        if (pinned.isNotEmpty()) {
            item { SectionHeader("Pinned", Modifier.padding(horizontal = Gutter)) }
            item { PinnedCards(pinned) }
        }
        if (recent.isNotEmpty()) {
            item { SectionHeader("Recent", Modifier.padding(horizontal = Gutter)) }
            item { com.medicalaid.app.ui.PageRows(recent) }
        }
        item { SectionHeader("Browse", Modifier.padding(horizontal = Gutter)) }
        item {
            val c = Theme.colors
            Column(Modifier.padding(horizontal = Gutter), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(Modifier.height(androidx.compose.foundation.layout.IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Tile(Destination.Emergency, "Resus, poisoning, trauma, children", Modifier.weight(1f).fillMaxHeight(), onNavigate, c.danger, c.dangerSoft)
                    Tile(Destination.Pregnancy, "Dating, labour room, treatment", Modifier.weight(1f).fillMaxHeight(), onNavigate, c.rose, c.roseSoft)
                }
                Row(Modifier.height(androidx.compose.foundation.layout.IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Tile(Destination.Infections, "${Conditions.infections.size} diseases, A–Z", Modifier.weight(1f).fillMaxHeight(), onNavigate, c.positive, c.positiveSoft)
                    Tile(Destination.Adult, "BP, diabetes, thyroid, heart", Modifier.weight(1f).fillMaxHeight(), onNavigate, c.blue, c.blueSoft)
                }
            }
        }
        item {
            RowGroup(
                listOf(
                    Triple(Destination.Drugs.label, "${plural(Drugs.all.size, "drug")} · doses, children, kidney, pregnancy") { onNavigate(Destination.Drugs) },
                    Triple(Destination.Scores.label, "${plural(com.medicalaid.app.content.scores.Scores.all.size, "score")} · Wells, CURB-65, GCS…") { onNavigate(Destination.Scores) },
                    Triple(Destination.Calculators.label, "Burns, ABG, eGFR, HbA1c, TSH") { onNavigate(Destination.Calculators) },
                    Triple(Destination.Sources.label, "${Sources.all.size} guidelines and labels") { onNavigate(Destination.Sources) },
                    Triple(Destination.About.label, "Intended use, disclaimer, licences") { onNavigate(Destination.About) },
                ),
                Modifier.padding(top = 10.dp),
            )
        }
        item {
            Text(
                "Content reviewed ${Sources.REVIEWED} · No patient data stored",
                style = Theme.type.caption, color = Theme.colors.textTertiary,
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 20.dp),
            )
        }
    }
}

@Composable
private fun Tile(destination: Destination, caption: String, modifier: Modifier, onNavigate: (Destination) -> Unit, fg: Color = Theme.colors.accent, bg: Color = Theme.colors.accentSoft) {
    val c = Theme.colors
    Column(
        modifier.clip(Radius.card).background(c.surface).border(1.dp, c.hairline, Radius.card)
            .clickable { onNavigate(destination) }.padding(16.dp),
    ) {
        IconBadge(destination.icon, fg = fg, bg = bg)
        Spacer(Modifier.height(18.dp))
        Text(destination.label, style = Theme.type.headline, color = c.text)
        Spacer(Modifier.height(2.dp))
        Text(caption, style = Theme.type.caption, color = c.textSecondary, maxLines = 2, minLines = 2, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
fun IconBadge(icon: ImageVector, size: Int = 40, fg: Color = Theme.colors.accent, bg: Color = Theme.colors.accentSoft) {
    val c = Theme.colors
    Box(
        Modifier.size(size.dp).clip(RoundedCornerShape((size / 4).dp)).background(bg),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, null, tint = fg, modifier = Modifier.size((size * 0.52).dp))
    }
}

@Composable
private fun RowGroup(rows: List<Triple<String, String, () -> Unit>>, modifier: Modifier = Modifier) {
    val c = Theme.colors
    Column(
        modifier.padding(horizontal = Gutter).fillMaxWidth().clip(Radius.card)
            .background(c.surface).border(1.dp, c.hairline, Radius.card),
    ) {
        rows.forEachIndexed { i, (title, subtitle, onClick) ->
            if (i > 0) Hairline(Modifier.padding(start = 18.dp))
            Row(
                Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 18.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(title, style = Theme.type.bodyStrong, color = c.text)
                    Text(subtitle, style = Theme.type.caption, color = c.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Spacer(Modifier.width(8.dp))
                Icon(Icons.Outlined.ChevronRight, null, tint = c.textTertiary, modifier = Modifier.size(20.dp))
            }
        }
    }
}

/** Quick chips on Home: the protocols most often needed in a hurry. */
private val QUICK = listOf(
    "PPH" to "pph", "Snakebite" to "snakebite", "DKA" to "dka", "Sepsis" to "sepsis",
    "Eclampsia" to "eclampsia", "Status epilepticus" to "status-epilepticus", "Organophosphate" to "organophosphate",
)

/** Pinned pages as cards in a horizontal row, coloured by section. */
@Composable
private fun PinnedCards(pages: List<com.medicalaid.app.ui.PageKey>) {
    val c = Theme.colors
    val open = com.medicalaid.app.ui.LocalOpenPage.current
    Row(
        Modifier.horizontalScroll(rememberScrollState()).padding(horizontal = Gutter),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        pages.forEach { k ->
            val (title, sub) = k.describe() ?: return@forEach
            val accent = when (k.type) {
                'c' -> com.medicalaid.app.content.Conditions.byId(k.id)?.let { com.medicalaid.app.ui.components.populationAccent(it.population) }
                'p' -> com.medicalaid.app.content.protocols.Protocols.byId(k.id)?.let { com.medicalaid.app.ui.components.categoryAccent(it.category) }
                else -> null
            } ?: com.medicalaid.app.ui.components.Accent(c.accent, c.accentSoft)
            Column(
                Modifier.width(150.dp).clip(Radius.card).background(c.surface).border(1.dp, c.hairline, Radius.card)
                    .clickable { open(k) }.padding(12.dp),
            ) {
                Box(Modifier.size(28.dp).clip(RoundedCornerShape(7.dp)).background(accent.bg), contentAlignment = Alignment.Center) {
                    Icon(Icons.Outlined.PushPin, null, tint = accent.fg, modifier = Modifier.size(15.dp))
                }
                Spacer(Modifier.height(8.dp))
                Text(title, style = Theme.type.bodyStrong, color = c.text, maxLines = 2, overflow = TextOverflow.Ellipsis, minLines = 2)
                Text(sub.substringBefore(" ·"), style = Theme.type.caption, color = c.textSecondary, maxLines = 1)
            }
        }
    }
}
