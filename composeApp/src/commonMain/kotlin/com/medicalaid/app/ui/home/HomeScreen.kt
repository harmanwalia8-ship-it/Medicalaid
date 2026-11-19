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
) {
    val focus = LocalFocusManager.current
    val conditionHits = remember(query) { Search.run(query).filter { it.drug == null }.map { it.condition } }
    val drugHits = remember(query) { if (query.isBlank()) emptyList() else Drugs.search(query) }

    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle("Medicalaid", "Guidelines and drug labels — sourced, offline, a few taps away", onMenu = onMenu) }
        item {
            SearchField(
                query, onQueryChange, onDone = { focus.clearFocus() },
                placeholder = "Search conditions and drugs…",
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 12.dp),
            )
        }

        if (query.isNotBlank()) {
            if (conditionHits.isEmpty() && drugHits.isEmpty()) {
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
            return@LazyColumn
        }

        item {
            Column(Modifier.padding(horizontal = Gutter).padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Tile(Destination.Pregnancy, "EDD, dating scan, antenatal schedule", Modifier.weight(1f), onNavigate)
                    Tile(Destination.Treatment, "${plural(Conditions.all.size, "topic")} by complaint", Modifier.weight(1f), onNavigate)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Tile(Destination.Drugs, "${plural(Drugs.all.size, "drug")} · side effects, kinetics", Modifier.weight(1f), onNavigate)
                    Tile(Destination.Calculators, "HbA1c, TSH, due date", Modifier.weight(1f), onNavigate)
                }
            }
        }
        item {
            RowGroup(
                listOf(
                    Triple(Destination.Sources.label, "${Sources.all.size} guidelines and labels") { onNavigate(Destination.Sources) },
                    Triple(Destination.About.label, "Intended use, disclaimer, licences") { onNavigate(Destination.About) },
                ),
                Modifier.padding(top = 16.dp),
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
private fun Tile(destination: Destination, caption: String, modifier: Modifier, onNavigate: (Destination) -> Unit) {
    val c = Theme.colors
    Column(
        modifier.clip(Radius.card).background(c.surface).border(1.dp, c.hairline, Radius.card)
            .clickable { onNavigate(destination) }.padding(16.dp),
    ) {
        IconBadge(destination.icon)
        Spacer(Modifier.height(18.dp))
        Text(destination.label, style = Theme.type.headline, color = c.text)
        Spacer(Modifier.height(2.dp))
        Text(caption, style = Theme.type.caption, color = c.textSecondary, maxLines = 2, minLines = 2, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
fun IconBadge(icon: ImageVector, size: Int = 40) {
    val c = Theme.colors
    Box(
        Modifier.size(size.dp).clip(Radius.inner).background(c.accentSoft),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, null, tint = c.accent, modifier = Modifier.size((size * 0.52).dp))
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
