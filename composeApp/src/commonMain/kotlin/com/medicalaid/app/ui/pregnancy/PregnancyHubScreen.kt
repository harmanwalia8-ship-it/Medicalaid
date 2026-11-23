package com.medicalaid.app.ui.pregnancy

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Conditions
import com.medicalaid.app.content.Search
import com.medicalaid.app.content.protocols.ProtocolCategory
import com.medicalaid.app.content.protocols.Protocols
import com.medicalaid.app.ui.NavRows
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.medicines.SearchField
import com.medicalaid.app.ui.theme.Theme

/** Most-needed first; anything not listed follows alphabetically. */
private val EMERGENCY_ORDER = listOf(
    "pph", "eclampsia", "severe-pre-eclampsia", "antepartum-haemorrhage", "maternal-collapse", "preterm-labour",
    "ectopic-pregnancy", "miscarriage-septic-abortion", "trauma-in-pregnancy", "hyperemesis-gravidarum",
)

/** Pregnancy & labour hub: dating and antenatal schedule, obstetric emergencies, treatment in pregnancy. */
@Composable
fun PregnancyHubScreen(
    query: String,
    onQueryChange: (String) -> Unit,
    onOpenDating: () -> Unit,
    onOpenProtocol: (String) -> Unit,
    onOpenCondition: (String) -> Unit,
    onMenu: () -> Unit,
    contentPadding: PaddingValues,
) {
    val focus = LocalFocusManager.current
    val emergencies = remember(query) {
        Protocols.search(query).filter { it.category == ProtocolCategory.Obstetric }
            .sortedWith(compareBy({ EMERGENCY_ORDER.indexOf(it.id).let { i -> if (i < 0) Int.MAX_VALUE else i } }, { it.title }))
    }
    val treatment = remember(query) {
        if (query.isBlank()) Conditions.pregnancy
        else Search.run(query).filter { it.drug == null && it.condition in Conditions.pregnancy }.map { it.condition }
    }
    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle("Pregnancy & labour", "Dating, labour-room emergencies and treatment in pregnancy", onMenu = onMenu) }
        item {
            SearchField(
                query, onQueryChange, onDone = { focus.clearFocus() },
                placeholder = "Search PPH, eclampsia, vomiting…",
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 12.dp),
            )
        }
        if (query.isBlank()) {
            item { SectionHeader("Dating", Modifier.padding(horizontal = Gutter)) }
            item { NavRows(listOf(Triple("Dating & antenatal schedule", "EDD, gestational age, scans, tests, vaccines", onOpenDating))) }
        }
        if (emergencies.isNotEmpty()) {
            item { SectionHeader("Obstetric emergencies", Modifier.padding(horizontal = Gutter), trailing = "${emergencies.size}") }
            item { NavRows(emergencies.map { p -> Triple(p.title, p.summary) { onOpenProtocol(p.id) } }) }
        }
        if (treatment.isNotEmpty()) {
            item { SectionHeader("Treatment in pregnancy", Modifier.padding(horizontal = Gutter), trailing = "${treatment.size}") }
            item { NavRows(treatment.map { c -> Triple(c.title, c.summary) { onOpenCondition(c.id) } }) }
        }
        if (query.isNotBlank() && emergencies.isEmpty() && treatment.isEmpty()) {
            item {
                Text(
                    "Nothing in Pregnancy & labour matches “$query”. Try the search on Home.",
                    style = Theme.type.body, color = Theme.colors.textSecondary,
                    modifier = Modifier.padding(horizontal = Gutter).padding(top = 28.dp),
                )
            }
        }
    }
}
