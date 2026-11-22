package com.medicalaid.app.ui.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Citation
import com.medicalaid.app.tools.AcidBase
import com.medicalaid.app.tools.Burns
import com.medicalaid.app.ui.components.GuidelineBasis
import com.medicalaid.app.ui.components.Overline
import com.medicalaid.app.ui.components.Segmented
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.formatDecimals
import com.medicalaid.app.ui.formatNumber
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

// =============================================================================================
// Burns chart
// =============================================================================================

private data class Rect(val x: Int, val y: Int, val w: Int, val h: Int, val round: Int = 8)

private const val FIG_W = 140
private const val FIG_H = 290

/** Front-view geometry (patient's right on the viewer's left). Back view mirrors bilateral parts. */
private val frontRects = mapOf(
    "head" to Rect(50, 0, 40, 40, 20),
    "neck" to Rect(60, 41, 20, 10, 4),
    "ant-trunk" to Rect(40, 52, 60, 90, 14),
    "genitalia" to Rect(62, 143, 16, 12, 6),
    "r-upper-arm" to Rect(21, 54, 17, 46),
    "l-upper-arm" to Rect(102, 54, 17, 46),
    "r-lower-arm" to Rect(17, 101, 17, 40),
    "l-lower-arm" to Rect(106, 101, 17, 40),
    "r-hand" to Rect(13, 142, 19, 20, 9),
    "l-hand" to Rect(108, 142, 19, 20, 9),
    "r-thigh" to Rect(42, 157, 27, 58),
    "l-thigh" to Rect(71, 157, 27, 58),
    "r-leg" to Rect(44, 216, 23, 54),
    "l-leg" to Rect(73, 216, 23, 54),
    "r-foot" to Rect(40, 271, 27, 16, 7),
    "l-foot" to Rect(73, 271, 27, 16, 7),
)

private val backOnlyRects = mapOf(
    "post-trunk" to Rect(40, 52, 60, 90, 14),
    "l-buttock" to Rect(40, 143, 29, 20, 10),
    "r-buttock" to Rect(71, 143, 29, 20, 10),
)

private fun rectFor(id: String, side: Burns.Side): Rect? {
    if (side == Burns.Side.Back) {
        backOnlyRects[id]?.let { return it }
        val f = frontRects[id] ?: return null
        val bilateral = id.startsWith("r-") || id.startsWith("l-")
        return if (bilateral) f.copy(x = FIG_W - f.x - f.w) else f
    }
    return frontRects[id]
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BurnsTool(initialMarks: Map<String, Double> = emptyMap(), initialWeight: String = "") {
    val c = Theme.colors
    var age by remember { mutableStateOf(Burns.AgeBand.Adult) }
    var marks by remember { mutableStateOf(initialMarks) }
    var weightText by remember { mutableStateOf(initialWeight) }
    val weight = weightText.toDecimalOrNull()?.takeIf { it in 2.0..250.0 }
    val tbsa = Burns.tbsa(age, marks)

    SurfaceCard {
        Overline("Calculator", color = c.accent)
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Burns — TBSA and fluids", style = Theme.type.headline, color = c.text, modifier = Modifier.weight(1f))
            if (marks.isNotEmpty()) {
                Row(
                    Modifier.clip(Radius.chip).clickable { marks = emptyMap() }.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Outlined.RestartAlt, null, tint = c.textSecondary, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Clear", style = Theme.type.label, color = c.textSecondary)
                }
            }
        }
        Text("Tap a region: none → half → full. Count partial- and full-thickness burns only.", style = Theme.type.caption, color = c.textSecondary)
        Spacer(Modifier.height(12.dp))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Burns.AgeBand.entries.forEach { a ->
                val active = a == age
                Text(
                    a.label, style = Theme.type.label,
                    color = if (active) c.onAccent else c.textSecondary,
                    modifier = Modifier.clip(Radius.chip).background(if (active) c.accent else c.surfaceMuted)
                        .clickable { age = a }.padding(horizontal = 12.dp, vertical = 6.dp),
                )
            }
        }
        Spacer(Modifier.height(14.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf(Burns.Side.Front to "Front", Burns.Side.Back to "Back").forEach { (side, label) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(Modifier.size(FIG_W.dp, FIG_H.dp)) {
                        Burns.regions.filter { side in it.shares }.forEach { region ->
                            val r = rectFor(region.id, side) ?: return@forEach
                            val k = Burns.key(region.id, side)
                            val v = marks[k] ?: 0.0
                            val fill: Color = when {
                                v >= 1.0 -> c.danger
                                v > 0.0 -> c.caution.copy(alpha = 0.55f)
                                else -> c.surfaceMuted
                            }
                            Box(
                                Modifier.offset(r.x.dp, r.y.dp).size(r.w.dp, r.h.dp)
                                    .clip(RoundedCornerShape(r.round.dp))
                                    .background(fill)
                                    .border(1.dp, c.hairline, RoundedCornerShape(r.round.dp))
                                    .clickable {
                                        val next = when { v == 0.0 -> 0.5; v < 1.0 -> 1.0; else -> 0.0 }
                                        marks = if (next == 0.0) marks - k else marks + (k to next)
                                    },
                            )
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(label, style = Theme.type.caption, color = c.textSecondary)
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Legend(c.surfaceMuted, "None"); Legend(c.caution.copy(alpha = 0.55f), "Half"); Legend(c.danger, "Full")
        }
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            Text(formatNumber(tbsa), style = Theme.type.title, color = c.text)
            Spacer(Modifier.width(6.dp))
            Text("% TBSA", style = Theme.type.bodyStrong, color = c.textSecondary, modifier = Modifier.padding(bottom = 4.dp))
        }
        Text("Lund–Browder chart, ${age.label} column", style = Theme.type.caption, color = c.textSecondary)

        Spacer(Modifier.height(14.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Weight", style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
            NumberField(weightText, { weightText = it }, placeholder = "kg", suffix = "kg", modifier = Modifier.width(110.dp))
        }
        if (weight != null && tbsa > 0) {
            val child = Burns.isChild(age)
            val regimens = Burns.regimens(age, tbsa)
            Spacer(Modifier.height(12.dp))
            if (regimens.isEmpty()) {
                Text(
                    if (child) {
                        "Formula IV resuscitation not indicated below ${formatNumber(Burns.MOHFW_CHILD_IV_FROM_PCT)}% TBSA in children (MoHFW 2025)."
                    } else {
                        "Formula IV resuscitation not indicated at ${formatNumber(Burns.MOHFW_ADULT_IV_ABOVE_PCT)}% TBSA or less in adults (MoHFW 2025)."
                    },
                    style = Theme.type.bodyStrong, color = c.text,
                )
                if (child) {
                    Text(
                        "JTS 2025: ${formatNumber(Burns.JTS_SMALL_BURN_CHILD_MAINTENANCE_FACTOR)} × the 4-2-1 maintenance rate — " +
                            "${ml(Burns.JTS_SMALL_BURN_CHILD_MAINTENANCE_FACTOR * Burns.maintenance421MlPerH(weight))} mL/h for ${formatNumber(weight)} kg.",
                        style = Theme.type.body, color = c.text,
                    )
                }
                Spacer(Modifier.height(8.dp))
                Details(
                    "Guideline details",
                    if (child) {
                        listOf(
                            "JTS 2025: children under 10% TBSA usually need no calculated resuscitation; weigh diapers for urine output and allow feeds if the child can eat.",
                        )
                    } else {
                        listOf(
                            "MoHFW 2025: below 15%, advise plenty of oral fluids, at least 2 L, for adequate clear urine output.",
                            "JTS 2025: in 10–20% TBSA burns start maintenance IV fluids and monitor urine output closely.",
                        )
                    },
                )
            } else {
                regimens.forEachIndexed { i, r ->
                    if (i > 0) Spacer(Modifier.height(8.dp))
                    FluidPlanCard(
                        "${r.name} · ${formatNumber(r.mlPerKgPerPct)} mL/kg/%TBSA Ringer's lactate",
                        Burns.fluids(r.mlPerKgPerPct, weight, tbsa),
                    )
                }
                if (child) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "PLUS dextrose-containing maintenance: ${ml(Burns.maintenance421MlPerH(weight))} mL/h (4-2-1 rule), on top of the resuscitation fluid.",
                        style = Theme.type.bodyStrong, color = c.text,
                    )
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    "Hours count from the burn, not arrival. Starting estimates only — titrate to hourly urine output.",
                    style = Theme.type.caption, color = c.textSecondary,
                )
                Spacer(Modifier.height(8.dp))
                Details(
                    "Guideline details",
                    buildList {
                        if (child) {
                            add(
                                "4-2-1 rule: 4 mL/kg/h for the first 10 kg, 2 mL/kg/h for the next 10 kg, 1 mL/kg/h above 20 kg. " +
                                    "JTS 2025: D5LR for children under 13 y, not titrated. MoHFW 2025: N/2 DNS or Isolyte P.",
                            )
                        }
                        add(
                            "Half in the first 8 h counted from the time of burn, not arrival; the rest over the next 16 h. If fluids start late, the hourly rate " +
                                "shown will not deliver the first-8-h volume on time; MoHFW 2025: delayed presentation, inhalational injury or electrical burns need more fluid.",
                        )
                        add(
                            "Urine output targets — JTS 2025: adults 30–50 mL/h (75–100 mL/h with high-voltage electrical injury " +
                                "or rhabdomyolysis), children 0.5–1 mL/kg/h; MoHFW 2025: adults 0.5–1 mL/kg/h, children 1 mL/kg/h.",
                        )
                        add("Use Ringer's lactate (or Plasma-Lyte); avoid normal saline where possible (JTS 2025).")
                        if (child) {
                            add(
                                "Children: MoHFW 2025 gives formula IV resuscitation from 10% TBSA. Adult regimens (Parkland, ABA 2024) are not shown: " +
                                    "ABA 2024 covers adults only.",
                            )
                        } else {
                            add("ABA 2024 (adults, ≥20% TBSA): consider starting at 2 rather than 4 mL/kg/%TBSA to reduce fluid volumes.")
                            add(
                                "MoHFW 2025: IV formula above 15% TBSA in adults; its adult Safdarjung (modified Brooke) formula is " +
                                    "2 mL/kg/%TBSA Ringer's lactate plus maintenance DNS (50 mL/kg/day; 2,500 mL in the text).",
                            )
                            add("JTS 2025: acute fluid resuscitation is typically needed from 20% TBSA; maintenance IV fluids for 10–20%.")
                        }
                    },
                )
            }
        }
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(
            listOf(
                Citation("tool-lund-browder", "Appendices B–C (chart); pp. 8–10 (resuscitation, urine output); p. 18 (children); Appendix D (timing)"),
                Citation("tool-burns-fluids", "Question 3 (2 vs 4 mL/kg/%TBSA, adults ≥20% TBSA)"),
                Citation("tool-mohfw-burns-2025", "Section E, pp. 30–32 (indications, formulas, urine output)"),
            ),
            compact = true,
        )
    }
}

@Composable
private fun Legend(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(12.dp).clip(RoundedCornerShape(3.dp)).background(color).border(1.dp, Theme.colors.hairline, RoundedCornerShape(3.dp)))
        Spacer(Modifier.width(6.dp))
        Text(label, style = Theme.type.caption, color = Theme.colors.textSecondary)
    }
}

/** Whole millilitres — fluid volumes and pump rates are not set to fractions of a mL. */
private fun ml(v: Double) = formatDecimals(v, 0)

@Composable
private fun FluidPlanCard(title: String, p: Burns.FluidPlan) {
    val c = Theme.colors
    Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(12.dp)) {
        Text(title.uppercase(), style = Theme.type.overline, color = c.textTertiary)
        Spacer(Modifier.height(4.dp))
        Text("${ml(p.total24hMl)} mL in 24 h", style = Theme.type.bodyStrong, color = c.text)
        Text(
            "First 8 h after the burn: ${ml(p.first8hMl)} mL (${ml(p.first8hRateMlPerH)} mL/h if started at the time of burn) · " +
                "next 16 h: ${ml(p.next16hMl)} mL (${ml(p.next16hRateMlPerH)} mL/h)",
            style = Theme.type.caption, color = c.textSecondary,
        )
        if (p.exceedsJtsHighRisk) {
            Spacer(Modifier.height(4.dp))
            Text(
                "Above ${formatNumber(Burns.JTS_HIGH_RISK_ML_PER_KG_24H)} mL/kg in 24 h: high risk of abdominal and extremity compartment syndromes " +
                    "(JTS 2025; the limit counts all infused fluids). Seek burn-centre advice and consider colloid rescue.",
                style = Theme.type.caption, color = c.danger,
            )
        }
    }
}

// =============================================================================================
// ABG interpreter
// =============================================================================================

@Composable
fun AcidBaseTool(
    initialPh: String = "", initialPco2: String = "", initialHco3: String = "",
    initialNa: String = "", initialCl: String = "", initialAlbumin: String = "",
) {
    val c = Theme.colors
    var phT by remember { mutableStateOf(initialPh) }
    var pco2T by remember { mutableStateOf(initialPco2) }
    var hco3T by remember { mutableStateOf(initialHco3) }
    var naT by remember { mutableStateOf(initialNa) }
    var clT by remember { mutableStateOf(initialCl) }
    var albT by remember { mutableStateOf(initialAlbumin) }
    var chronic by remember { mutableStateOf(false) }

    val ph = phT.toDecimalOrNull()?.takeIf { it in 6.5..8.0 }
    val pco2 = pco2T.toDecimalOrNull()?.takeIf { it in 5.0..150.0 }
    val hco3 = hco3T.toDecimalOrNull()?.takeIf { it in 1.0..70.0 }
    val na = naT.toDecimalOrNull()?.takeIf { it in 90.0..200.0 }
    val cl = clT.toDecimalOrNull()?.takeIf { it in 50.0..160.0 }
    val alb = albT.toDecimalOrNull()?.takeIf { it in 0.5..7.0 }

    SurfaceCard {
        Overline("Calculator", color = c.accent)
        Spacer(Modifier.height(4.dp))
        Text("ABG interpreter", style = Theme.type.headline, color = c.text)
        Text(
            "Arterial sample only · PaCO₂ in mm Hg · HCO₃, Na, Cl in mmol/L · albumin in g/dL",
            style = Theme.type.caption, color = c.textTertiary,
        )
        Spacer(Modifier.height(12.dp))
        FieldRow("pH", phT, { phT = it }, "7.40", null)
        FieldRow("PaCO₂", pco2T, { pco2T = it }, "mm Hg", "mm Hg")
        FieldRow("HCO₃⁻", hco3T, { hco3T = it }, "mmol/L", "mmol/L")
        Spacer(Modifier.height(6.dp))
        Text("For the anion gap (optional)", style = Theme.type.caption, color = c.textSecondary)
        FieldRow("Na⁺", naT, { naT = it }, "mmol/L", "mmol/L")
        FieldRow("Cl⁻", clT, { clT = it }, "mmol/L", "mmol/L")
        FieldRow("Albumin", albT, { albT = it }, "g/dL", "g/dL")

        if (ph != null && pco2 != null && hco3 != null) {
            val primary = AcidBase.primary(ph, pco2, hco3)
            // pH 7.35–7.45: StatPearls' 7.40 cut-off decides which disorder the compensation check analyses.
            val side = if (primary == AcidBase.Primary.NormalPh) AcidBase.sideOfCutoff(ph, pco2, hco3) else null
            val analysed = side ?: primary
            Spacer(Modifier.height(14.dp))
            Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(14.dp)) {
                Text("PRIMARY", style = Theme.type.overline, color = c.textTertiary)
                Spacer(Modifier.height(2.dp))
                Text(primary.label, style = Theme.type.bodyStrong, color = c.text)
                if ((primary == AcidBase.Primary.RespiratoryAcidosis || primary == AcidBase.Primary.RespiratoryAlkalosis) &&
                    pco2 >= AcidBase.PCO2_LOW && pco2 <= AcidBase.PCO2_HIGH
                ) {
                    Text(
                        "PaCO₂ is within 35–45 mm Hg; classified by comparing it with 40 mm Hg (StatPearls).",
                        style = Theme.type.caption, color = c.textSecondary,
                    )
                }
                side?.let { Text("By the StatPearls 7.40 cut-off: ${it.label}", style = Theme.type.caption, color = c.textSecondary) }
                val comp = compensationLine(analysed, pco2, hco3, chronic)
                if (comp != null) {
                    Spacer(Modifier.height(6.dp))
                    Text(comp.first, style = Theme.type.caption, color = c.textSecondary)
                    Text(comp.second, style = Theme.type.label, color = c.accent)
                }
            }
            if (analysed == AcidBase.Primary.RespiratoryAcidosis || analysed == AcidBase.Primary.RespiratoryAlkalosis) {
                Spacer(Modifier.height(8.dp))
                Segmented(listOf(false to "Acute", true to "Chronic"), chronic, { chronic = it })
            }
            if (na != null && cl != null) {
                val ag = AcidBase.anionGap(na, cl, hco3)
                val corrected = alb?.let { AcidBase.correctedAnionGap(ag, it) }
                val used = corrected ?: ag
                val dd = AcidBase.deltaDelta(used, hco3)
                val highAgNotes = AcidBase.highAnionGapNotes(used, hco3)
                Spacer(Modifier.height(8.dp))
                Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(14.dp)) {
                    Text("ANION GAP", style = Theme.type.overline, color = c.textTertiary)
                    Spacer(Modifier.height(2.dp))
                    Text("${formatDecimals(ag, 1)} mmol/L" + (corrected?.let { " · albumin-corrected ${formatDecimals(it, 1)}" } ?: ""), style = Theme.type.bodyStrong, color = c.text)
                    if (highAgNotes.isNotEmpty()) {
                        Text(
                            "High anion gap: ${formatDecimals(used - AcidBase.AG_NORMAL, 1)} above 12",
                            style = Theme.type.caption, color = c.textSecondary,
                        )
                    }
                    if (dd != null) {
                        Spacer(Modifier.height(6.dp))
                        Text("Δ–Δ (Berend)", style = Theme.type.label, color = c.textSecondary)
                        Text(
                            "If ketoacidosis: ΔAG − ΔHCO₃ = ${formatDecimals(dd.keto, 1)} → ${meaningOnly(AcidBase.deltaDeltaMeaning(dd.keto))}",
                            style = Theme.type.caption, color = c.text,
                        )
                        Text(
                            "If lactic acidosis: 0.6 × ΔAG − ΔHCO₃ = ${formatDecimals(dd.lactic, 1)} → ${meaningOnly(AcidBase.deltaDeltaMeaning(dd.lactic))}",
                            style = Theme.type.caption, color = c.text,
                        )
                    } else if (highAgNotes.isNotEmpty() && hco3 >= AcidBase.HCO3_NORMAL) {
                        Text("Δ–Δ not calculated: HCO₃ is not low (see details).", style = Theme.type.caption, color = c.textSecondary)
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Text("Interpret with the clinical picture; mixed disorders are common.", style = Theme.type.caption, color = c.textSecondary)
            Spacer(Modifier.height(8.dp))
            Details(
                "How this is calculated",
                buildList {
                    if (na != null && cl != null) {
                        val ag = AcidBase.anionGap(na, cl, hco3)
                        val used = alb?.let { AcidBase.correctedAnionGap(ag, it) } ?: ag
                        addAll(AcidBase.highAnionGapNotes(used, hco3))
                        add(
                            "Δ–Δ bands (Berend): above +5 — high anion gap acidosis with metabolic alkalosis; −5 to +5 — high anion gap acidosis alone; " +
                                "below −5 — high anion gap acidosis with normal anion gap acidosis. 1:1 in ketoacidosis, 0.6 in lactic acidosis.",
                        )
                        add(
                            "Anion gap reference ranges vary by analyser (3–12 up to 8.5–15 mmol/L); ΔAG uses 12 and ΔHCO₃ uses 24 mmol/L. " +
                                "Albumin correction adds 2.5 per 1 g/dL below 4.0 g/dL (4.0 assumed normal); none above 4.0 (Berend describes decreases only).",
                        )
                    }
                    add(
                        "Normal ranges (StatPearls): pH 7.35–7.45, PaCO₂ 35–45 mm Hg, HCO₃ 22–26 mmol/L; values may vary slightly among laboratories " +
                            "and across age groups, from neonates to older adults. " +
                            "The primary disorder is a pH outside 7.35–7.45 with the matching abnormal PaCO₂ and/or HCO₃ (criteria as in Berend Table 1, " +
                            "which uses narrower bands: pH 7.38–7.42, PaCO₂ 38–42 mm Hg). Expected compensation, anion gap and Δ–Δ follow Berend.",
                    )
                    add(
                        "If the pH is outside 7.35–7.45 but neither PaCO₂ nor HCO₃ is outside its range in the matching direction, " +
                            "PaCO₂ is compared with 40 mm Hg (StatPearls: above 40 with pH below 7.40 indicates respiratory acidosis; " +
                            "below 40 with pH above 7.40, respiratory alkalosis). If the pH is within 7.35–7.45, StatPearls uses 7.40 as the " +
                            "cut-off: an abnormal PaCO₂ or HCO₃ on the matching side is named and checked for compensation " +
                            "(StatPearls example: pH 7.39, PaCO₂ 51, HCO₃ 30 is a primary respiratory acidosis).",
                    )
                    add(
                        "Prediction equations are approximations (Berend: data are insufficient to construct confidence limits for severe chronic respiratory disorders). " +
                            "Respiratory disorders are classified by Berend's bands for the HCO₃ change per 10 mm Hg change in PaCO₂ from 40, with no added tolerance.",
                    )
                    add(
                        "Arterial blood gas only. Berend's venous reference values differ (pH 7.36–7.38, PvCO₂ 43–48 mm Hg, HCO₃ 25–26 mmol/L), " +
                            "so do not apply these arterial ranges to a venous sample.",
                    )
                },
            )
        }
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(
            listOf(
                Citation("tool-abg-reference", "Results, Reporting, and Critical Findings (normal ranges; pH 7.40 cut-off; PaCO₂ compared with 40 mm Hg)"),
                Citation("tool-acid-base", "Table 1 (criteria, compensation); Figures 1–2; text pp. 1437–1441; Table 2 footnote"),
            ),
            compact = true,
        )
    }
}

/** "above +5: high anion gap…" → "high anion gap…" (the band itself is listed under details). */
private fun meaningOnly(m: String) = m.substringAfter(": ", m)

/** A quiet, collapsed block for the guideline text behind a result. */
@Composable
private fun Details(title: String, lines: List<String>) {
    if (lines.isEmpty()) return
    val c = Theme.colors
    var open by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth().clip(Radius.inner).border(1.dp, c.hairline, Radius.inner)) {
        Row(
            Modifier.fillMaxWidth().clickable { open = !open }.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(title, style = Theme.type.label, color = c.textSecondary, modifier = Modifier.weight(1f))
            Icon(
                Icons.Outlined.ExpandMore, if (open) "Collapse" else "Expand", tint = c.textTertiary,
                modifier = Modifier.size(18.dp).rotate(if (open) 180f else 0f),
            )
        }
        if (open) {
            Column(Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                lines.forEach { Text(it, style = Theme.type.caption, color = c.textSecondary) }
            }
        }
    }
}

/** (expected, verdict) for the compensation check. */
private fun compensationLine(p: AcidBase.Primary, pco2: Double, hco3: Double, chronic: Boolean): Pair<String, String>? {
    fun verdict(measured: Double, e: AcidBase.Expected, above: String, below: String) = when {
        measured > e.high -> above
        measured < e.low -> below
        else -> "Within the expected range — appropriate secondary response"
    }
    fun range(e: AcidBase.Expected) =
        if (e.low == e.high) "${e.label}: ${formatDecimals(e.low, 1)}" else "${e.label}: ${formatDecimals(e.low, 1)}–${formatDecimals(e.high, 1)}"
    return when (p) {
        AcidBase.Primary.MetabolicAcidosis -> AcidBase.wintersPco2(hco3).let {
            range(it) + " · measured ${formatNumber(pco2)}" to verdict(pco2, it, "PaCO₂ higher than expected — superimposed respiratory acidosis", "PaCO₂ lower than expected — superimposed respiratory alkalosis")
        }
        AcidBase.Primary.MetabolicAlkalosis -> AcidBase.alkalosisPco2(hco3).let {
            range(it) + " · measured ${formatNumber(pco2)}" to verdict(pco2, it, "PaCO₂ higher than expected — superimposed respiratory acidosis", "PaCO₂ lower than expected — superimposed respiratory alkalosis")
        }
        AcidBase.Primary.RespiratoryAcidosis -> AcidBase.respAcidosisHco3(pco2, chronic).let {
            range(it) + " · measured ${formatNumber(hco3)}" to
                "Berend: HCO₃ rise per 10 mm Hg PaCO₂ rise ${formatDecimals(AcidBase.respAcidosisRisePer10(pco2, hco3), 1)} mmol/L — " +
                AcidBase.respResponseText(AcidBase.respAcidosisResponse(pco2, hco3), acidosis = true)
        }
        AcidBase.Primary.RespiratoryAlkalosis -> AcidBase.respAlkalosisHco3(pco2, chronic).let {
            range(it) + " · measured ${formatNumber(hco3)}" to
                "Berend: HCO₃ fall per 10 mm Hg PaCO₂ fall ${formatDecimals(AcidBase.respAlkalosisFallPer10(pco2, hco3), 1)} mmol/L — " +
                AcidBase.respResponseText(AcidBase.respAlkalosisResponse(pco2, hco3), acidosis = false)
        }
        else -> null
    }
}

@Composable
private fun FieldRow(label: String, value: String, onChange: (String) -> Unit, placeholder: String, suffix: String?, width: Dp = 130.dp) {
    Row(Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(label, style = Theme.type.body, color = Theme.colors.text, modifier = Modifier.weight(1f))
        NumberField(value, onChange, placeholder = placeholder, suffix = suffix, modifier = Modifier.width(width))
    }
}
