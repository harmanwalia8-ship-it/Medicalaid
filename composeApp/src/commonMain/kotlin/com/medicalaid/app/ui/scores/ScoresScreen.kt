package com.medicalaid.app.ui.scores

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.medicalaid.app.content.protocols.ProtocolCategory
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.protocols.Protocol
import com.medicalaid.app.content.protocols.Protocols
import com.medicalaid.app.content.scores.Scores
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.medicines.SearchField
import com.medicalaid.app.ui.plural
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

@Composable
fun ScoresScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    onOpen: (String) -> Unit,
    onMenu: () -> Unit,
    contentPadding: PaddingValues,
) {
    val results = remember(query) { Scores.search(query) }
    val focus = LocalFocusManager.current
    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle("Scores", "${plural(Scores.all.size, "validated score")} and rules — each with its source and caveats", onMenu = onMenu) }
        item {
            SearchField(
                query, onQueryChange, onDone = { focus.clearFocus() },
                placeholder = "Search Wells, CURB-65, stroke…",
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 12.dp),
            )
        }
        if (results.isEmpty()) {
            item {
                Text("No scores match “$query”.", style = Theme.type.body, color = Theme.colors.textSecondary, modifier = Modifier.padding(horizontal = Gutter).padding(top = 28.dp))
            }
        }
        results.groupBy { it.category }.toSortedMap(compareBy { it.ordinal }).forEach { (cat, scores) ->
            item(key = "h-${cat.name}") { SectionHeader(cat.label, Modifier.padding(horizontal = Gutter), trailing = "${scores.size}") }
            item(key = "g-${cat.name}") { Rows(scores.map { Triple(it.name, it.purpose) { onOpen(it.id) } }) }
        }
    }
}

/**
 * Emergency hub: protocols by category (obstetric ones live under Pregnancy & labour), with category chips and a
 * row of bedside tools (scores and calculators used in the same situations).
 */
@Composable
fun ProtocolsScreen(
    onOpen: (String) -> Unit,
    onMenu: () -> Unit,
    contentPadding: PaddingValues,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    title: String = "Emergency",
    subtitle: String = "Step-by-step protocols for the casualty and ICU",
    exclude: Set<ProtocolCategory> = setOf(ProtocolCategory.Obstetric),
    tools: List<Pair<String, () -> Unit>> = emptyList(),
) {
    val focus = LocalFocusManager.current
    var category by remember { mutableStateOf<ProtocolCategory?>(null) }
    val inScope = remember(query) { Protocols.search(query).filter { it.category !in exclude } }
    val categories = remember(inScope) { inScope.map { it.category }.distinct().sortedBy { it.ordinal } }
    val results = inScope.filter { category == null || it.category == category }
    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle(title, subtitle, onMenu = onMenu) }
        item {
            SearchField(
                query, onQueryChange, onDone = { focus.clearFocus() },
                placeholder = "Search DKA, burns, snakebite…",
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 12.dp),
            )
        }
        if (tools.isNotEmpty() && query.isBlank()) {
            item {
                Column(Modifier.padding(top = 14.dp)) {
                    Text("Bedside tools", style = Theme.type.label, color = Theme.colors.textSecondary, modifier = Modifier.padding(horizontal = Gutter))
                    Row(
                        Modifier.horizontalScroll(rememberScrollState()).padding(horizontal = Gutter).padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        tools.forEach { (label, onClick) ->
                            Text(
                                label, style = Theme.type.label, color = Theme.colors.accent,
                                modifier = Modifier.clip(Radius.chip).background(Theme.colors.accentSoft)
                                    .clickable(onClick = onClick).padding(horizontal = 12.dp, vertical = 8.dp),
                            )
                        }
                    }
                }
            }
        }
        if (categories.size >= 2) {
            item {
                com.medicalaid.app.ui.components.FilterTabs(
                    listOf<Pair<ProtocolCategory?, String>>(null to "All") + categories.map { it to it.short },
                    category, { category = it }, Modifier.padding(top = 14.dp),
                )
            }
        }
        if (results.isEmpty()) {
            item { Text("No protocols match “$query”.", style = Theme.type.body, color = Theme.colors.textSecondary, modifier = Modifier.padding(horizontal = Gutter).padding(top = 28.dp)) }
        }
        results.groupBy { it.category }.toSortedMap(compareBy { it.ordinal }).forEach { (cat, list) ->
            item(key = "h-${cat.name}") { SectionHeader(cat.label, Modifier.padding(horizontal = Gutter), trailing = "${list.size}") }
            item(key = "g-${cat.name}") { Rows(list.sortedBy { it.title }.map { p: Protocol -> Triple(p.title, p.summary) { onOpen(p.id) } }) }
        }
    }
}

@Composable
private fun Rows(rows: List<Triple<String, String, () -> Unit>>) {
    val c = Theme.colors
    Column(
        Modifier.padding(horizontal = Gutter).fillMaxWidth().clip(Radius.card)
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
