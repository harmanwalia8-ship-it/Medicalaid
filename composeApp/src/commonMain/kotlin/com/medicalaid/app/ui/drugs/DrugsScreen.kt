package com.medicalaid.app.ui.drugs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.drugs.Clearance
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.Drugs
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.components.Tag
import com.medicalaid.app.ui.medicines.SearchField
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme
import com.medicalaid.app.ui.plural

@Composable
fun DrugsScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    onOpen: (String) -> Unit,
    onMenu: (() -> Unit)?,
    contentPadding: PaddingValues,
) {
    val results = remember(query) { Drugs.search(query) }
    val focus = LocalFocusManager.current

    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item {
            ScreenTitle(
                "Drugs",
                "${plural(Drugs.all.size, "drug")} · side effects, kinetics, kidney and liver — from official labels",
                onMenu = onMenu,
            )
        }
        item {
            SearchField(
                query, onQueryChange, onDone = { focus.clearFocus() },
                placeholder = "Search drug or class…",
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 12.dp),
            )
        }
        if (results.isEmpty()) {
            item {
                Column(Modifier.padding(horizontal = Gutter).padding(top = 36.dp)) {
                    Text("No drugs match “$query”", style = Theme.type.bodyStrong, color = Theme.colors.text)
                    Spacer(Modifier.height(4.dp))
                    Text("Search by generic name (paracetamol, rifampicin) or class (statin, ARB).", style = Theme.type.caption, color = Theme.colors.textSecondary)
                }
            }
        } else if (query.isNotBlank()) {
            item { SectionHeader("Results", Modifier.padding(horizontal = Gutter), trailing = "${results.size}") }
            item { DrugGroup(results, onOpen) }
        } else {
            results.groupBy { it.name.first().uppercaseChar() }.forEach { (letter, drugs) ->
                item(key = "h-$letter") { SectionHeader(letter.toString(), Modifier.padding(horizontal = Gutter)) }
                item(key = "g-$letter") { DrugGroup(drugs, onOpen) }
            }
        }
        item {
            Text(
                "Summaries of US FDA prescribing information (DailyMed). Check the linked label and local product information.",
                style = Theme.type.caption,
                color = Theme.colors.textTertiary,
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 24.dp),
            )
        }
    }
}

@Composable
private fun DrugGroup(drugs: List<DrugMonograph>, onOpen: (String) -> Unit) {
    val c = Theme.colors
    Column(
        Modifier.padding(horizontal = Gutter).fillMaxWidth().clip(Radius.card)
            .background(c.surface).border(1.dp, c.hairline, Radius.card),
    ) {
        drugs.forEachIndexed { i, d ->
            if (i > 0) Hairline(Modifier.padding(start = 18.dp))
            Row(
                Modifier.fillMaxWidth().clickable { onOpen(d.id) }.padding(start = 18.dp, end = 12.dp, top = 13.dp, bottom = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(d.name, style = Theme.type.bodyStrong, color = c.text)
                    Text(d.drugClass, style = Theme.type.caption, color = c.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    if (d.boxedWarning != null) Tag("Boxed", fg = c.danger, bg = c.dangerSoft)
                    if (d.hasRenalAdjustment || Clearance.Renal in d.clearance) Tag("Kidney")
                }
                Spacer(Modifier.width(6.dp))
                Icon(Icons.Outlined.ChevronRight, null, tint = c.textTertiary, modifier = Modifier.size(20.dp))
            }
        }
    }
}
