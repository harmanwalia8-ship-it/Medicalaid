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
                    Modifier.clip(Radius.pill).clickable { marks = emptyMap() }.padding(horizontal = 8.dp, vertical = 4.dp),
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
                    modifier = Modifier.clip(Radius.pill).background(if (active) c.accent else c.surfaceMuted)
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
            Spacer(Modifier.height(12.dp))
            FluidPlanCard("Parkland · ${formatNumber(Burns.PARKLAND_ML)} mL/kg/%TBSA", Burns.fluids(Burns.PARKLAND_ML, weight, tbsa))
            Spacer(Modifier.height(8.dp))
            FluidPlanCard("ABA/ABLS adult start · ${formatNumber(Burns.ABLS_ADULT_ML)} mL/kg/%TBSA", Burns.fluids(Burns.ABLS_ADULT_ML, weight, tbsa))
            Spacer(Modifier.height(6.dp))
            Text(
                "Times run from the burn, not from arrival. Titrate to urine output and clinical response; see the burns protocol for thresholds and targets.",
                style = Theme.type.caption, color = c.textTertiary,
            )
        }
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(
            listOf(Citation("tool-lund-browder", "Lund–Browder chart"), Citation("tool-burns-fluids", "Fluid resuscitation formulas")),
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
            "First 8 h: ${ml(p.first8hMl)} mL (${ml(p.first8hRateMlPerH)} mL/h) · next 16 h: ${ml(p.next16hMl)} mL (${ml(p.next16hRateMlPerH)} mL/h)",
            style = Theme.type.caption, color = c.textSecondary,
        )
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
        Text("PaCO₂ in mm Hg · HCO₃, Na, Cl in mmol/L · albumin in g/dL", style = Theme.type.caption, color = c.textTertiary)
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
            Spacer(Modifier.height(14.dp))
            Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(14.dp)) {
                Text("PRIMARY", style = Theme.type.overline, color = c.textTertiary)
                Spacer(Modifier.height(2.dp))
                Text(primary.label, style = Theme.type.bodyStrong, color = c.text)
                val comp = compensationLine(primary, pco2, hco3, chronic)
                if (comp != null) {
                    Spacer(Modifier.height(6.dp))
                    Text(comp.first, style = Theme.type.caption, color = c.textSecondary)
                    Text(comp.second, style = Theme.type.label, color = c.accent)
                }
            }
            if (primary == AcidBase.Primary.RespiratoryAcidosis || primary == AcidBase.Primary.RespiratoryAlkalosis) {
                Spacer(Modifier.height(8.dp))
                Segmented(listOf(false to "Acute", true to "Chronic"), chronic, { chronic = it })
            }
            if (na != null && cl != null) {
                val ag = AcidBase.anionGap(na, cl, hco3)
                val corrected = alb?.let { AcidBase.correctedAnionGap(ag, it) }
                val used = corrected ?: ag
                val dr = AcidBase.deltaRatio(used, hco3)
                Spacer(Modifier.height(8.dp))
                Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(14.dp)) {
                    Text("ANION GAP", style = Theme.type.overline, color = c.textTertiary)
                    Spacer(Modifier.height(2.dp))
                    Text("${formatDecimals(ag, 1)} mmol/L" + (corrected?.let { " · albumin-corrected ${formatDecimals(it, 1)}" } ?: ""), style = Theme.type.bodyStrong, color = c.text)
                    if (dr != null) {
                        Text("Delta ratio ${formatDecimals(dr, 2)} — ${AcidBase.deltaRatioMeaning(dr)}", style = Theme.type.caption, color = c.textSecondary)
                    }
                    Text("Reference anion gap varies by laboratory; uses 12 mmol/L here.", style = Theme.type.caption, color = c.textTertiary)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text("Interpret with the clinical picture; mixed disorders are common.", style = Theme.type.caption, color = c.textTertiary)
        }
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(listOf(Citation("tool-acid-base", "Compensation rules, anion gap, delta ratio")), compact = true)
    }
}

/** (expected, verdict) for the compensation check. */
private fun compensationLine(p: AcidBase.Primary, pco2: Double, hco3: Double, chronic: Boolean): Pair<String, String>? {
    fun verdict(measured: Double, e: AcidBase.Expected, above: String, below: String) = when {
        measured > e.high -> above
        measured < e.low -> below
        else -> "Within the expected range — appropriate compensation"
    }
    fun range(e: AcidBase.Expected) = "${e.label}: ${formatDecimals(e.low, 1)}–${formatDecimals(e.high, 1)}"
    return when (p) {
        AcidBase.Primary.MetabolicAcidosis -> AcidBase.wintersPco2(hco3).let {
            range(it) + " · measured ${formatNumber(pco2)}" to verdict(pco2, it, "PaCO₂ higher than expected — concurrent respiratory acidosis", "PaCO₂ lower than expected — concurrent respiratory alkalosis")
        }
        AcidBase.Primary.MetabolicAlkalosis -> AcidBase.alkalosisPco2(hco3).let {
            range(it) + " · measured ${formatNumber(pco2)}" to verdict(pco2, it, "PaCO₂ higher than expected — concurrent respiratory acidosis", "PaCO₂ lower than expected — concurrent respiratory alkalosis")
        }
        AcidBase.Primary.RespiratoryAcidosis -> AcidBase.respAcidosisHco3(pco2, chronic).let {
            range(it) + " · measured ${formatNumber(hco3)}" to verdict(hco3, it, "HCO₃ higher than expected — concurrent metabolic alkalosis", "HCO₃ lower than expected — concurrent metabolic acidosis")
        }
        AcidBase.Primary.RespiratoryAlkalosis -> AcidBase.respAlkalosisHco3(pco2, chronic).let {
            range(it) + " · measured ${formatNumber(hco3)}" to verdict(hco3, it, "HCO₃ higher than expected — concurrent metabolic alkalosis", "HCO₃ lower than expected — concurrent metabolic acidosis")
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
