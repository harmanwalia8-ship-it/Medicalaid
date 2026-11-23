package com.medicalaid.app.ui.medicines

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Conditions
import com.medicalaid.app.content.Population
import com.medicalaid.app.content.Search
import com.medicalaid.app.content.SearchHit
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.components.Segmented
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

@Composable
fun MedicinesScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    population: Population,
    onPopulationChange: (Population) -> Unit,
    onOpen: (conditionId: String, drugName: String?) -> Unit,
    contentPadding: PaddingValues,
    onMenu: (() -> Unit)? = null,
    title: String = "Treatment",
    subtitle: String = "By complaint — doses as the guideline states them, with the source behind each",
    /** Hubs (Infections, Adult medicine) show one population, without the tabs, and search only within it. */
    fixedPopulation: Boolean = false,
) {
    val hits = remember(query, population, fixedPopulation) {
        Search.run(query).filter { !fixedPopulation || it.condition.population == population }
    }
    val focus = LocalFocusManager.current

    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle(title, subtitle, onMenu = onMenu) }
        item {
            SearchField(
                query, onQueryChange, onDone = { focus.clearFocus() },
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 12.dp),
            )
        }

        if (query.isBlank()) {
            if (!fixedPopulation) item {
                Segmented(
                    Population.entries.map { it to it.label }, population, onPopulationChange,
                    modifier = Modifier.padding(horizontal = Gutter).padding(top = 16.dp),
                )
            }
            val list = Conditions.of(population)
            item { SectionHeader(if (population == Population.Infections) "A–Z" else "Browse", Modifier.padding(horizontal = Gutter), trailing = "${list.size}") }
            item {
                GroupedList(
                    rows = list.map { Row(it.title, it.summary) { onOpen(it.id, null) } },
                    modifier = Modifier.padding(horizontal = Gutter),
                )
            }
        } else if (hits.isEmpty()) {
            item {
                Column(Modifier.padding(horizontal = Gutter).padding(top = 36.dp)) {
                    Text("No matches for “$query”", style = Theme.type.bodyStrong, color = Theme.colors.text)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Try a complaint (vomiting, itching, heartburn) or a drug name (ondansetron, labetalol).",
                        style = Theme.type.caption,
                        color = Theme.colors.textSecondary,
                    )
                }
            }
        } else {
            val conditionHits = hits.filter { it.drug == null }
            val drugHits = hits.filter { it.drug != null }
            if (conditionHits.isNotEmpty()) {
                item { SectionHeader("Complaints", Modifier.padding(horizontal = Gutter)) }
                item {
                    GroupedList(
                        conditionHits.map { h -> Row(h.condition.title, "${h.condition.population.label} · ${h.condition.summary}") { onOpen(h.condition.id, null) } },
                        Modifier.padding(horizontal = Gutter),
                    )
                }
            }
            if (drugHits.isNotEmpty()) {
                item { SectionHeader("Medicines", Modifier.padding(horizontal = Gutter)) }
                item {
                    GroupedList(
                        drugHits.map { h: SearchHit ->
                            Row(h.drug!!.name, "${h.condition.title} · ${h.condition.population.label}") { onOpen(h.condition.id, h.drug.name) }
                        },
                        Modifier.padding(horizontal = Gutter),
                    )
                }
            }
        }

        item {
            Text(
                "Decision support for clinicians. Check doses against the linked guideline and the patient's clinical context.",
                style = Theme.type.caption,
                color = Theme.colors.textTertiary,
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 24.dp),
            )
        }
    }
}

private data class Row(val title: String, val subtitle: String, val onClick: () -> Unit)

@Composable
private fun GroupedList(rows: List<Row>, modifier: Modifier = Modifier) {
    val c = Theme.colors
    Column(
        modifier.fillMaxWidth().clip(Radius.card).background(c.surface).border(1.dp, c.hairline, Radius.card),
    ) {
        rows.forEachIndexed { index, row ->
            if (index > 0) Hairline(Modifier.padding(start = 18.dp))
            androidx.compose.foundation.layout.Row(
                Modifier.fillMaxWidth().clickable(onClick = row.onClick).padding(horizontal = 18.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(row.title, style = Theme.type.bodyStrong, color = c.text)
                    Spacer(Modifier.height(1.dp))
                    Text(row.subtitle, style = Theme.type.caption, color = c.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Spacer(Modifier.width(10.dp))
                Icon(Icons.Outlined.ChevronRight, null, tint = c.textTertiary, modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
fun SearchField(
    value: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search vomiting, itching, labetalol…",
) {
    val c = Theme.colors
    androidx.compose.foundation.layout.Row(
        modifier.fillMaxWidth().clip(Radius.chip).background(c.surface).border(1.dp, c.hairline, Radius.chip)
            .padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.Outlined.Search, null, tint = c.textTertiary, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(10.dp))
        Box(Modifier.weight(1f).padding(vertical = 14.dp)) {
            if (value.isEmpty()) {
                Text(placeholder, style = Theme.type.body, color = c.textTertiary)
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = Theme.type.body.copy(color = c.text),
                cursorBrush = SolidColor(c.accent),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onDone() }),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        if (value.isNotEmpty()) {
            Box(
                Modifier.size(32.dp).clip(Radius.chip).clickable { onValueChange("") },
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Outlined.Close, "Clear search", tint = c.textSecondary, modifier = Modifier.size(18.dp))
            }
        }
    }
}
