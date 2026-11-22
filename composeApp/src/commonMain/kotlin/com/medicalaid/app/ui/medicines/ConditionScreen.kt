package com.medicalaid.app.ui.medicines

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Caution
import com.medicalaid.app.content.Condition
import com.medicalaid.app.content.Drug
import com.medicalaid.app.content.InfoBlock
import com.medicalaid.app.content.Population
import com.medicalaid.app.content.ToolKind
import com.medicalaid.app.content.drugs.Drugs
import com.medicalaid.app.pregnancy.Trimester
import com.medicalaid.app.ui.components.Bullets
import com.medicalaid.app.ui.components.Dot
import com.medicalaid.app.ui.components.GuidelineBasis
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.components.Tag
import com.medicalaid.app.ui.formatNumber
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme
import com.medicalaid.app.ui.tools.GlycemiaTool
import com.medicalaid.app.ui.tools.NumberField
import com.medicalaid.app.ui.tools.TshGeneralTool
import com.medicalaid.app.ui.tools.TshPregnancyTool

@Composable
fun ConditionScreen(
    condition: Condition,
    highlightDrug: String?,
    trimester: Trimester?,
    onBack: () -> Unit,
    contentPadding: PaddingValues,
    onOpenDrug: (String) -> Unit = {},
    initialGuideline: GuidelineGroup? = null,
) {
    var weightText by remember(condition.id) { mutableStateOf("") }
    var filter by remember(condition.id) { mutableStateOf(initialGuideline) }
    val groupsOnPage = remember(condition.id) {
        (condition.info.flatMap { it.points } + condition.tiers.map { it.label } +
            condition.tiers.flatMap { t -> t.drugs.flatMap { it.cautions } } + condition.escalate)
            .mapNotNull { GuidelineGroup.of(it) }.distinct().sortedBy { it.ordinal }
    }
    val weightKg = weightText.replace(',', '.').toDoubleOrNull()?.takeIf { it in 20.0..250.0 }
    val hasWeightDosing = condition.tiers.any { t -> t.drugs.any { it.weightDose != null } }
    val listState = rememberLazyListState()

    // Index of the highlighted drug's card, for scrolling there when opened from search.
    val targetIndex = remember(condition.id, highlightDrug) {
        if (highlightDrug == null) return@remember -1
        var i = 1 // title
        if (condition.tool != null) i++
        if (hasWeightDosing) i++
        if (condition.firstSteps.isNotEmpty()) i += 2
        i += condition.info.size
        for (tier in condition.tiers) {
            i++ // tier header
            for (drug in tier.drugs) {
                if (drug.name == highlightDrug) return@remember i
                i++
            }
        }
        -1
    }
    LaunchedEffect(targetIndex) { if (targetIndex > 0) listState.animateScrollToItem(targetIndex) }

    LazyColumn(Modifier.fillMaxSize(), state = listState, contentPadding = contentPadding) {
        item {
            Column {
                ScreenTitle(condition.title, condition.summary, onBack = onBack)
                Row(Modifier.padding(horizontal = Gutter).padding(top = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Tag(
                        condition.population.label,
                        fg = if (condition.population == Population.Pregnancy) Theme.colors.accent else Theme.colors.positive,
                        bg = if (condition.population == Population.Pregnancy) Theme.colors.accentSoft else Theme.colors.positiveSoft,
                    )
                    Spacer(Modifier.width(10.dp))
                    val authorities = (condition.citations + condition.info.flatMap { it.citations } +
                        condition.tiers.flatMap { t -> t.drugs.flatMap { it.citations } })
                        .map { com.medicalaid.app.content.Sources.byId(it.sourceId).authority.short }.distinct()
                    Text("Based on " + authorities.joinToString(" · "), style = Theme.type.caption, color = Theme.colors.textTertiary)
                }
                if (groupsOnPage.size >= 2) {
                    com.medicalaid.app.ui.components.FilterTabs(
                        listOf<Pair<GuidelineGroup?, String>>(null to "All") + groupsOnPage.map { it to it.label },
                        filter, { filter = it }, Modifier.padding(top = 10.dp),
                    )
                }
            }
        }

        condition.tool?.let { tool ->
            item {
                Box(Modifier.padding(horizontal = Gutter).padding(top = 16.dp)) {
                    when (tool) {
                        ToolKind.Glycemia -> GlycemiaTool()
                        ToolKind.TshPregnancy -> TshPregnancyTool(trimester)
                        ToolKind.TshGeneral -> TshGeneralTool()
                        ToolKind.Renal -> com.medicalaid.app.ui.tools.RenalTool()
                        ToolKind.Burns -> com.medicalaid.app.ui.tools.BurnsTool()
                        ToolKind.AcidBase -> com.medicalaid.app.ui.tools.AcidBaseTool()
                    }
                }
            }
        }

        if (hasWeightDosing) {
            item {
                SurfaceCard(Modifier.padding(horizontal = Gutter).padding(top = 16.dp), padding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("Patient weight", style = Theme.type.bodyStrong, color = Theme.colors.text)
                            Text("For mg/kg doses on this page", style = Theme.type.caption, color = Theme.colors.textSecondary)
                        }
                        NumberField(weightText, { weightText = it }, placeholder = "kg", suffix = "kg", modifier = Modifier.width(110.dp))
                    }
                }
            }
        }

        if (condition.firstSteps.isNotEmpty()) {
            item { SectionHeader("First steps", Modifier.padding(horizontal = Gutter)) }
            item {
                SurfaceCard(Modifier.padding(horizontal = Gutter)) { Bullets(condition.firstSteps) }
            }
        }

        condition.info.forEach { block ->
            val points = block.points.filter { GuidelineGroup.shows(it, filter) }
            if (points.isNotEmpty()) {
                item(key = "info-" + block.title) { InfoCard(block.copy(points = points, citations = GuidelineGroup.filterCitations(block.citations, filter)), Modifier.padding(horizontal = Gutter).padding(top = 16.dp)) }
            }
        }

        condition.tiers.forEachIndexed { tIndex, tier ->
            if (!GuidelineGroup.shows(tier.label, filter)) return@forEachIndexed
            item(key = "tier-$tIndex") {
                Column(Modifier.padding(horizontal = Gutter)) {
                    SectionHeader(tier.label)
                    if (tier.note != null) {
                        Text(
                            tier.note, style = Theme.type.caption, color = Theme.colors.textSecondary,
                            modifier = Modifier.padding(top = 0.dp, bottom = 10.dp),
                        )
                    }
                }
            }
            tier.drugs.forEachIndexed { dIndex, drug ->
                item(key = "drug-$tIndex-$dIndex") {
                    DrugCard(
                        drug.copy(
                            cautions = drug.cautions.filter { GuidelineGroup.shows(it, filter) },
                            citations = GuidelineGroup.filterCitations(drug.citations, filter),
                        ),
                        weightKg,
                        highlighted = drug.name == highlightDrug,
                        onOpenDrug = onOpenDrug,
                        modifier = Modifier.padding(horizontal = Gutter).padding(bottom = 8.dp),
                    )
                }
            }
        }

        if (condition.avoid.isNotEmpty()) {
            item { SectionHeader("Avoid", Modifier.padding(horizontal = Gutter)) }
            condition.avoid.forEachIndexed { i, caution ->
                item(key = "avoid-$i") { AvoidCard(caution, Modifier.padding(horizontal = Gutter).padding(bottom = 8.dp)) }
            }
        }

        if (condition.escalate.isNotEmpty()) {
            item { SectionHeader("Escalate or refer", Modifier.padding(horizontal = Gutter)) }
            item {
                SurfaceCard(Modifier.padding(horizontal = Gutter)) {
                    Bullets(condition.escalate.filter { GuidelineGroup.shows(it, filter) })
                    Spacer(Modifier.height(14.dp))
                    GuidelineBasis(GuidelineGroup.filterCitations(condition.citations, filter), compact = true)
                }
            }
        }

        item {
            Column(Modifier.padding(horizontal = Gutter).padding(top = 28.dp)) {
                Text("All sources on this page", style = Theme.type.label, color = Theme.colors.textSecondary)
                Spacer(Modifier.height(8.dp))
                val all = (condition.citations + condition.info.flatMap { it.citations } +
                    condition.tiers.flatMap { t -> t.drugs.flatMap { it.citations } } +
                    condition.avoid.flatMap { it.citations })
                    .map { it.sourceId }.distinct().map { com.medicalaid.app.content.Citation(it) }
                GuidelineBasis(all)
            }
        }
    }
}

@Composable
private fun InfoCard(block: InfoBlock, modifier: Modifier = Modifier) {
    SurfaceCard(modifier) {
        Text(block.title, style = Theme.type.headline, color = Theme.colors.text)
        Spacer(Modifier.height(10.dp))
        Bullets(block.points)
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(block.citations, compact = true)
    }
}

@Composable
private fun DrugCard(drug: Drug, weightKg: Double?, highlighted: Boolean, onOpenDrug: (String) -> Unit, modifier: Modifier = Modifier) {
    val c = Theme.colors
    val monograph = remember(drug.name) { Drugs.forConditionDrug(drug.name) }
    SurfaceCard(
        modifier.then(if (highlighted) Modifier.border(1.5.dp, c.accent, Radius.card) else Modifier),
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Text(drug.name, style = Theme.type.headline, color = c.text, modifier = Modifier.weight(1f))
            if (monograph != null) {
                Text(
                    "Drug profile ›",
                    style = Theme.type.label,
                    color = c.accent,
                    modifier = Modifier.padding(start = 8.dp, top = 2.dp).clip(Radius.chip).clickable { onOpenDrug(monograph.id) }
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                )
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(drug.regimen, style = Theme.type.body, color = c.text)
        if (drug.maxDose != null) {
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Max", style = Theme.type.label, color = c.textSecondary)
                Spacer(Modifier.width(8.dp))
                Text(drug.maxDose, style = Theme.type.label, color = c.text)
            }
        }
        drug.weightDose?.let { wd ->
            Spacer(Modifier.height(12.dp))
            Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.accentSoft).padding(12.dp)) {
                if (weightKg == null) {
                    Text(
                        "${formatNumber(wd.minPerKg)}–${formatNumber(wd.maxPerKg)} ${wd.unit}/kg ${wd.per}. Enter weight above to calculate.",
                        style = Theme.type.caption, color = c.accent,
                    )
                } else {
                    val lo = wd.minPerKg * weightKg
                    val hi = wd.maxPerKg * weightKg
                    Text("For ${formatNumber(weightKg)} kg", style = Theme.type.caption, color = c.textSecondary)
                    Text(
                        "${formatNumber(lo)}–${formatNumber(hi)} ${wd.unit} ${wd.per}",
                        style = Theme.type.bodyStrong, color = c.accent,
                    )
                    wd.ceilingPerKg?.let { ceil ->
                        Text(
                            "Ceiling ${formatNumber(ceil)} ${wd.unit}/kg = ${formatNumber(ceil * weightKg)} ${wd.unit} per day",
                            style = Theme.type.caption, color = c.textSecondary,
                        )
                    }
                    Text("Round to an available strength clinically.", style = Theme.type.caption, color = c.textTertiary)
                }
            }
        }
        if (drug.cautions.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                drug.cautions.forEach { CautionLine(it) }
            }
        }
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(drug.citations, compact = true)
    }
}

@Composable
private fun CautionLine(text: String, color: Color = Theme.colors.caution) {
    Row(verticalAlignment = Alignment.Top) {
        Icon(Icons.Outlined.WarningAmber, null, tint = color, modifier = Modifier.padding(top = 2.dp).size(14.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, style = Theme.type.caption, color = Theme.colors.textSecondary)
    }
}

@Composable
private fun AvoidCard(caution: Caution, modifier: Modifier = Modifier) {
    val c = Theme.colors
    SurfaceCard(modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Dot(c.danger)
            Spacer(Modifier.width(10.dp))
            Text(caution.name, style = Theme.type.bodyStrong, color = c.text)
        }
        Spacer(Modifier.height(6.dp))
        Text(caution.reason, style = Theme.type.body, color = c.textSecondary)
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(caution.citations, compact = true)
    }
}
