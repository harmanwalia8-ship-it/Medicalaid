package com.medicalaid.app.ui.drugs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Citation
import com.medicalaid.app.content.drugs.DrugMonograph
import com.medicalaid.app.content.drugs.ExternalRefs
import com.medicalaid.app.content.drugs.Facts
import com.medicalaid.app.ui.components.Bullets
import com.medicalaid.app.ui.components.Dot
import com.medicalaid.app.ui.components.GuidelineBasis
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.components.Overline
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.components.Tag
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme
import kotlinx.coroutines.launch

private data class Section(val key: String, val chip: String, val content: @Composable () -> Unit)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DrugScreen(drug: DrugMonograph, onBack: () -> Unit, contentPadding: PaddingValues, onOpenRenalCalculator: () -> Unit = {}) {
    val c = Theme.colors
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val sections = buildList {
        drug.dose?.let { add(Section("dose", "Dose") { FactsCard("Dose", it) }) }
        if (drug.children != null || drug.childDoses.isNotEmpty()) add(Section("children", "Children") { ChildrenCard(drug) })
        if (drug.beforeStarting != null || drug.monitoring != null) {
            add(
                Section("tests", "Tests") {
                    SurfaceCard {
                        drug.beforeStarting?.let { FactsBlock("Before starting", it) }
                        if (drug.beforeStarting != null && drug.monitoring != null) Spacer(Modifier.height(16.dp))
                        drug.monitoring?.let { FactsBlock("Monitoring", it) }
                    }
                },
            )
        }
        if (drug.renal != null || drug.hepatic != null) {
            add(
                Section("organ", "Kidney & liver") {
                    SurfaceCard {
                        drug.renal?.let {
                            FactsBlock("Kidney", it)
                            Spacer(Modifier.height(10.dp))
                            Text(
                                "eGFR / CrCl calculator ›",
                                style = Theme.type.label,
                                color = c.accent,
                                modifier = Modifier.clip(Radius.chip).background(c.accentSoft)
                                    .clickable(onClick = onOpenRenalCalculator).padding(horizontal = 12.dp, vertical = 7.dp),
                            )
                        }
                        if (drug.renal != null && drug.hepatic != null) Spacer(Modifier.height(16.dp))
                        drug.hepatic?.let { FactsBlock("Liver", it) }
                    }
                },
            )
        }
        add(
            Section("effects", "Side effects") {
                SurfaceCard {
                    EffectsBlock("Common", drug.commonEffects, c.textTertiary)
                    Spacer(Modifier.height(16.dp))
                    EffectsBlock("Serious · less common", drug.seriousEffects, c.danger)
                }
            },
        )
        add(Section("pk", "Kinetics") { PkCard(drug) })
        drug.interactions?.let { add(Section("interactions", "Interactions") { FactsCard("Key interactions", it) }) }
        if (drug.pregnancy != null || drug.lactation != null) {
            add(
                Section("preg", "Pregnancy") {
                    SurfaceCard {
                        drug.pregnancy?.let { FactsBlock("Pregnancy", it) }
                        if (drug.pregnancy != null && drug.lactation != null) Spacer(Modifier.height(16.dp))
                        drug.lactation?.let { FactsBlock("Breastfeeding", it) }
                    }
                },
            )
        }
        add(Section("refs", "Look up") { ExternalRefsCard(drug) })
    }
    // Item indices: 0 title, 1 chips row, 2 boxed warning (optional), then sections.
    val firstSectionIndex = if (drug.boxedWarning != null) 3 else 2

    LazyColumn(Modifier.fillMaxSize(), state = listState, contentPadding = contentPadding) {
        item {
            Column {
                ScreenTitle(drug.name, drug.drugClass, onBack = onBack, label = "Drug")
                Text(drug.uses, style = Theme.type.body, color = c.text, modifier = Modifier.padding(horizontal = Gutter).padding(top = 4.dp))
                val labelForm = drug.allCitations().firstOrNull()?.let { com.medicalaid.app.content.Sources.byId(it.sourceId).title.substringBefore(" — ") }
                if (labelForm != null) {
                    Text(
                        "US label: $labelForm",
                        style = Theme.type.caption, color = c.textSecondary,
                        modifier = Modifier.padding(horizontal = Gutter).padding(top = 6.dp),
                    )
                }
                if (drug.aliases.isNotEmpty()) {
                    Text(
                        "Also: " + drug.aliases.joinToString(", "),
                        style = Theme.type.caption, color = c.textTertiary,
                        modifier = Modifier.padding(horizontal = Gutter).padding(top = 4.dp),
                    )
                }
                FlowRow(
                    Modifier.padding(horizontal = Gutter).padding(top = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    drug.clearance.forEach { Tag("Cleared: ${it.label.lowercase()}") }
                    if (drug.renal != null) Tag("Kidney guidance", fg = c.accent, bg = c.accentSoft)
                    if (drug.hepatic != null) Tag("Liver guidance", fg = c.caution, bg = c.cautionSoft)
                }
            }
        }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = Gutter),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
            ) {
                items(sections.size) { i ->
                    Box(
                        Modifier.clip(Radius.chip).background(c.surface).border(1.dp, c.hairline, Radius.chip)
                            .clickable { scope.launch { listState.animateScrollToItem(firstSectionIndex + i) } }
                            .padding(horizontal = 14.dp, vertical = 7.dp),
                    ) { Text(sections[i].chip, style = Theme.type.label, color = c.textSecondary) }
                }
            }
        }
        drug.boxedWarning?.let { bw ->
            item {
                Column(
                    Modifier.padding(horizontal = Gutter).padding(top = 12.dp).fillMaxWidth()
                        .clip(Radius.card).background(c.dangerSoft).padding(18.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Outlined.WarningAmber, null, tint = c.danger, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Boxed warning", style = Theme.type.bodyStrong, color = c.danger)
                    }
                    Spacer(Modifier.height(8.dp))
                    Bullets(bw.items)
                    Spacer(Modifier.height(12.dp))
                    GuidelineBasis(bw.citations, compact = true)
                }
            }
        }
        sections.forEach { section ->
            item(key = section.key) {
                Box(Modifier.padding(horizontal = Gutter).padding(top = 12.dp)) { section.content() }
            }
        }
        item {
            Column(Modifier.padding(horizontal = Gutter).padding(top = 28.dp)) {
                Text("Sources", style = Theme.type.label, color = c.textSecondary)
                Spacer(Modifier.height(8.dp))
                GuidelineBasis(drug.allCitations().map { it.sourceId }.distinct().map { Citation(it) })
            }
        }
    }
}

@Composable
private fun FactsCard(title: String, facts: Facts) {
    SurfaceCard { FactsBlock(title, facts) }
}

@Composable
private fun FactsBlock(title: String, facts: Facts) {
    Text(title, style = Theme.type.headline, color = Theme.colors.text)
    Spacer(Modifier.height(10.dp))
    Bullets(facts.items)
    Spacer(Modifier.height(12.dp))
    GuidelineBasis(facts.citations, compact = true)
}

@Composable
private fun EffectsBlock(title: String, facts: Facts, dot: Color) {
    val c = Theme.colors
    Row(verticalAlignment = Alignment.CenterVertically) {
        Dot(dot)
        Spacer(Modifier.width(8.dp))
        Text(title, style = Theme.type.headline, color = c.text)
    }
    Spacer(Modifier.height(10.dp))
    Bullets(facts.items)
    Spacer(Modifier.height(12.dp))
    GuidelineBasis(facts.citations, compact = true)
}

@Composable
private fun PkCard(drug: DrugMonograph) {
    val c = Theme.colors
    val pk = drug.pk
    val tiles = listOfNotNull(
        pk.absorption?.let { "Absorption" to it },
        pk.distribution?.let { "Distribution" to it },
        pk.metabolism?.let { "Metabolism" to it },
        pk.elimination?.let { "Elimination" to it },
        pk.halfLife?.let { "Half-life" to it },
    )
    SurfaceCard {
        Text("Pharmacokinetics", style = Theme.type.headline, color = c.text)
        Spacer(Modifier.height(12.dp))
        if (tiles.isEmpty()) {
            Text("The cited label does not state pharmacokinetic values.", style = Theme.type.body, color = c.textSecondary)
            Spacer(Modifier.height(12.dp))
        }
        tiles.chunked(2).forEach { row ->
            Row(Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { (label, value) ->
                    Column(
                        Modifier.weight(1f).clip(Radius.inner).background(c.surfaceMuted).padding(12.dp),
                    ) {
                        Overline(label)
                        Spacer(Modifier.height(4.dp))
                        Text(value, style = Theme.type.caption, color = c.text)
                    }
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
        Spacer(Modifier.height(4.dp))
        GuidelineBasis(pk.citations, compact = true)
    }
}

@Composable
private fun ExternalRefsCard(drug: DrugMonograph) {
    val c = Theme.colors
    val uri = LocalUriHandler.current
    SurfaceCard(padding = PaddingValues(0.dp)) {
        Column(Modifier.padding(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 6.dp)) {
            Text("Look up elsewhere", style = Theme.type.headline, color = c.text)
            Spacer(Modifier.height(2.dp))
            Text("Opens the publisher's website. Not used as the basis for this page.", style = Theme.type.caption, color = c.textSecondary)
        }
        ExternalRefs.forDrug(drug).forEachIndexed { i, ref ->
            if (i > 0) Hairline(Modifier.padding(start = 18.dp))
            Row(
                Modifier.fillMaxWidth().clickable { runCatching { uri.openUri(ref.url) } }
                    .padding(horizontal = 18.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(ref.name, style = Theme.type.bodyStrong, color = c.text)
                    Text(ref.note, style = Theme.type.caption, color = c.textSecondary)
                }
                Icon(Icons.AutoMirrored.Outlined.OpenInNew, "Open", tint = c.textTertiary, modifier = Modifier.size(16.dp))
            }
        }
        Spacer(Modifier.height(6.dp))
    }
}

/** Children's dosing: the age-band lines, then a weight panel driving the mg/kg calculators. */
@Composable
private fun ChildrenCard(drug: DrugMonograph) {
    var weightText by remember(drug.id) { mutableStateOf("") }
    val weightKg = weightText.replace(',', '.').toDoubleOrNull()?.takeIf { it in 2.0..250.0 }
    SurfaceCard {
        drug.children?.let { FactsBlock("Children", it) }
        if (drug.childDoses.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            com.medicalaid.app.ui.components.WeightPanel(weightText, { weightText = it }, hint = "Calculates the children's doses below")
            drug.childDoses.forEach {
                Spacer(Modifier.height(8.dp))
                com.medicalaid.app.ui.components.StepDoseCard(it, weightKg)
            }
        }
    }
}
