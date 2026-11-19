package com.medicalaid.app.ui.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WarningAmber
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.cite
import com.medicalaid.app.pregnancy.Trimester
import com.medicalaid.app.tools.Glycemia
import com.medicalaid.app.tools.Thyroid
import com.medicalaid.app.ui.components.GuidelineBasis
import com.medicalaid.app.ui.components.Overline
import com.medicalaid.app.ui.components.Segmented
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.formatNumber
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

fun String.toDecimalOrNull(): Double? = replace(',', '.').trim().toDoubleOrNull()

/** Compact numeric input used by the calculators. */
@Composable
fun NumberField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    suffix: String? = null,
    modifier: Modifier = Modifier,
) {
    val c = Theme.colors
    Row(
        modifier.clip(Radius.inner).background(c.surfaceMuted).border(1.dp, c.hairline, Radius.inner)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.weight(1f)) {
            if (value.isEmpty()) Text(placeholder, style = Theme.type.bodyStrong, color = c.textTertiary)
            BasicTextField(
                value = value,
                onValueChange = { new -> if (new.length <= 7 && new.all { it.isDigit() || it == '.' || it == ',' }) onValueChange(new) },
                singleLine = true,
                textStyle = Theme.type.bodyStrong.copy(color = c.text),
                cursorBrush = SolidColor(c.accent),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        if (suffix != null && value.isNotEmpty()) {
            Spacer(Modifier.width(6.dp))
            Text(suffix, style = Theme.type.caption, color = c.textSecondary)
        }
    }
}

@Composable
private fun ToolNote(text: String, color: Color = Theme.colors.caution, bg: Color = Theme.colors.cautionSoft) {
    Row(
        Modifier.fillMaxWidth().clip(Radius.inner).background(bg).padding(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Icon(Icons.Outlined.WarningAmber, null, tint = color, modifier = Modifier.padding(top = 1.dp).size(14.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, style = Theme.type.caption, color = color)
    }
}

@Composable
private fun BigResult(value: String, unit: String, caption: String) {
    val c = Theme.colors
    Column {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(value, style = Theme.type.title, color = c.text)
            Spacer(Modifier.width(4.dp))
            Text(unit, style = Theme.type.bodyStrong, color = c.textSecondary, modifier = Modifier.padding(bottom = 4.dp))
        }
        Text(caption, style = Theme.type.caption, color = c.textSecondary)
    }
}

// ---------------------------------------------------------------------------------------------
// Glucose ↔ HbA1c
// ---------------------------------------------------------------------------------------------

private enum class GlyMode { GlucoseToA1c, A1cToGlucose }
private enum class GluUnit(val label: String) { MgDl("mg/dL"), Mmol("mmol/L") }

@Composable
fun GlycemiaTool(initialA1c: String? = null, initialGlucose: String? = null) {
    val c = Theme.colors
    var mode by remember { mutableStateOf(if (initialA1c != null) GlyMode.A1cToGlucose else GlyMode.GlucoseToA1c) }
    var unit by remember { mutableStateOf(GluUnit.MgDl) }
    var input by remember { mutableStateOf(initialA1c ?: initialGlucose ?: "") }
    val value = input.toDecimalOrNull()

    SurfaceCard {
        Overline("Calculator", color = c.accent)
        Spacer(Modifier.height(4.dp))
        Text("Glucose ↔ HbA1c", style = Theme.type.headline, color = c.text)
        Spacer(Modifier.height(12.dp))
        Segmented(
            listOf(GlyMode.GlucoseToA1c to "Glucose → HbA1c", GlyMode.A1cToGlucose to "HbA1c → glucose"),
            mode, { mode = it; input = "" },
        )
        Spacer(Modifier.height(10.dp))
        Segmented(GluUnit.entries.map { it to it.label }, unit, { unit = it; input = "" })
        Spacer(Modifier.height(14.dp))

        when (mode) {
            GlyMode.GlucoseToA1c -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Glucose (RBS / average)", style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
                    NumberField(input, { input = it }, placeholder = unit.label, suffix = unit.label, modifier = Modifier.width(130.dp))
                }
                val valid = value != null && (if (unit == GluUnit.MgDl) value in 40.0..800.0 else value in 2.2..44.0)
                if (valid) {
                    val a1c = if (unit == GluUnit.MgDl) Glycemia.a1cFromMgDl(value!!) else Glycemia.a1cFromMmol(value!!)
                    Spacer(Modifier.height(16.dp))
                    BigResult("≈ ${formatNumber(a1c)}", "%", "HbA1c that corresponds to this as an average glucose")
                } else if (value != null) {
                    Spacer(Modifier.height(10.dp))
                    Text("Enter a glucose between ${if (unit == GluUnit.MgDl) "40 and 800 mg/dL" else "2.2 and 44 mmol/L"}.", style = Theme.type.caption, color = c.textSecondary)
                }
                Spacer(Modifier.height(14.dp))
                ToolNote(
                    "Rough estimate only. The ADAG equation links HbA1c to the AVERAGE glucose over 2–3 months; " +
                        "a single random glucose can differ widely from that average. Measure HbA1c — never diagnose or titrate from this number. " +
                        "Not for use in pregnancy (ADA 15.13).",
                )
            }
            GlyMode.A1cToGlucose -> {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("HbA1c", style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
                    NumberField(input, { input = it }, placeholder = "%", suffix = "%", modifier = Modifier.width(130.dp))
                }
                if (value != null && value in 4.0..20.0) {
                    val eag = if (unit == GluUnit.MgDl) Glycemia.eagMgDl(value) else Glycemia.eagMmol(value)
                    Spacer(Modifier.height(16.dp))
                    BigResult(formatNumber(eag), unit.label, "Estimated average glucose (eAG)")
                    Spacer(Modifier.height(12.dp))
                    val band = Glycemia.band(value)
                    val (fg, bg) = when (band) {
                        Glycemia.A1cBand.Normal -> c.positive to c.positiveSoft
                        Glycemia.A1cBand.Prediabetes -> c.caution to c.cautionSoft
                        Glycemia.A1cBand.Diabetes -> c.danger to c.dangerSoft
                    }
                    Text(
                        band.label, style = Theme.type.label, color = fg,
                        modifier = Modifier.clip(Radius.inner).background(bg).padding(horizontal = 10.dp, vertical = 6.dp),
                    )
                    Spacer(Modifier.height(6.dp))
                    Text("ADA diagnostic bands; confirm a diagnosis with a second abnormal test. Non-pregnant adults only.", style = Theme.type.caption, color = c.textTertiary)
                    Spacer(Modifier.height(12.dp))
                    val therapy = Glycemia.icmrTherapyBand(value)
                    Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(12.dp)) {
                        Text("IF DIAGNOSED TYPE 2 DIABETES · ICMR", style = Theme.type.overline, color = c.textTertiary)
                        Spacer(Modifier.height(4.dp))
                        Text(therapy.label, style = Theme.type.bodyStrong, color = c.text)
                        Text(therapy.detail, style = Theme.type.caption, color = c.textSecondary)
                        if (value > 10.0) {
                            Spacer(Modifier.height(4.dp))
                            Text("ADA: consider insulin if A1C >10%, glucose ≥300 mg/dL, or symptoms of hyperglycaemia.", style = Theme.type.caption, color = c.textSecondary)
                        }
                    }
                } else if (value != null) {
                    Spacer(Modifier.height(10.dp))
                    Text("Enter an HbA1c between 4 and 20%.", style = Theme.type.caption, color = c.textSecondary)
                }
            }
        }
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(
            listOf(
                cite("ada-eag", "eAG = 28.7 × A1C − 46.7 (mg/dL); 1.59 × A1C − 2.59 (mmol/L)"),
                cite("ada-s2", "Tables 2.1–2.2"),
                cite("icmr-stw-t2dm-2022", "Pharmacotherapy by HbA1c"),
                cite("ada-s9", "Rec 9.20"),
            ),
        )
    }
}

// ---------------------------------------------------------------------------------------------
// TSH in pregnancy (MoHFW)
// ---------------------------------------------------------------------------------------------

@Composable
fun TshPregnancyTool(initialTrimester: Trimester?, initialTsh: String = "", initialFt4: Thyroid.Ft4 = Thyroid.Ft4.Unknown) {
    val c = Theme.colors
    var trimester by remember { mutableStateOf(initialTrimester ?: Trimester.First) }
    var input by remember { mutableStateOf(initialTsh) }
    var ft4 by remember { mutableStateOf(initialFt4) }
    var weightText by remember { mutableStateOf("") }
    val tsh = input.toDecimalOrNull()?.takeIf { it in 0.0..200.0 }
    val weight = weightText.toDecimalOrNull()?.takeIf { it in 30.0..200.0 }
    val range = Thyroid.mohfwRange(trimester)

    SurfaceCard {
        Overline("Calculator", color = c.accent)
        Spacer(Modifier.height(4.dp))
        Text("TSH in pregnancy", style = Theme.type.headline, color = c.text)
        Text(
            if (initialTrimester != null) "Trimester set from the Pregnancy tab" else "Shows ATA 2026 and MoHFW 2014 side by side",
            style = Theme.type.caption, color = c.textTertiary,
        )
        Spacer(Modifier.height(12.dp))
        Segmented(
            listOf(Trimester.First to "1st", Trimester.Second to "2nd", Trimester.Third to "3rd"),
            trimester, { trimester = it },
        )
        Spacer(Modifier.height(14.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("TSH", style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
            NumberField(input, { input = it }, placeholder = "mIU/L", suffix = "mIU/L", modifier = Modifier.width(130.dp))
        }
        Spacer(Modifier.height(12.dp))
        Text("Free T4", style = Theme.type.body, color = c.text)
        Spacer(Modifier.height(6.dp))
        Segmented(
            listOf(Thyroid.Ft4.Low to "Low", Thyroid.Ft4.Normal to "Normal", Thyroid.Ft4.Unknown to "Not known"),
            ft4, { ft4 = it },
        )

        if (tsh != null) {
            // ---------------- ATA 2026
            val ata = Thyroid.ata2026Pregnancy(tsh, ft4, trimester)
            val (ataTitle, ataPoints, ataColor) = when (ata) {
                Thyroid.AtaPregnancyAction.BelowRange -> Triple("Below 0.1 mU/L", listOf("Low TSH is outside the scope of this hypothyroidism tool."), c.caution)
                Thyroid.AtaPregnancyAction.WithinRange -> Triple(
                    "Within 0.1–4.0 mU/L — no levothyroxine",
                    listOf("Euthyroid TPOAb/TgAb-positive women: do not offer levothyroxine (Strong, High)."),
                    c.positive,
                )
                Thyroid.AtaPregnancyAction.TreatOvert -> Triple(
                    "Overt hypothyroidism, TSH ≥6 — treat",
                    buildList {
                        add("Start levothyroxine (Strong, Moderate): 1.5–1.7 mcg/kg/day plus 20–30% for gestation.")
                        if (weight != null) {
                            val (lo, hi) = Thyroid.ata2026OvertDoseRange(weight)
                            add("For ${formatNumber(weight)} kg: about ${formatNumber(lo)}–${formatNumber(hi)} mcg/day.")
                        }
                    },
                    c.danger,
                )
                Thyroid.AtaPregnancyAction.ConfirmOvert -> Triple(
                    "Overt hypothyroidism, TSH <6",
                    listOf("A confirmatory test within 3 weeks may be done to verify the indication (Conditional, Low); treat if it persists."),
                    c.caution,
                )
                Thyroid.AtaPregnancyAction.TreatProfoundSubclinical -> Triple(
                    "TSH >10 — treat",
                    listOf("Profound subclinical hypothyroidism should be treated with levothyroxine (Good Practice Statement). Check FT4.", "Low-dose start: 25–75 mcg/day depending on TSH and weight."),
                    c.danger,
                )
                Thyroid.AtaPregnancyAction.ConsiderInFirstTrimester -> Triple(
                    "Mild subclinical, 1st trimester — may consider",
                    listOf(
                        "Levothyroxine may be considered (Conditional, Low) — shared decision.",
                        "If started: 25–75 mcg/day depending on TSH and weight. A confirmation test after 1–3 weeks can be considered first.",
                    ),
                    c.caution,
                )
                Thyroid.AtaPregnancyAction.DoNotTreatRecheck -> Triple(
                    "Mild subclinical after 1st trimester — do not treat",
                    listOf("Levothyroxine should not be offered (Strong, High). Recheck TSH after 4–6 weeks."),
                    c.positive,
                )
                Thyroid.AtaPregnancyAction.NeedFt4 -> Triple(
                    "Raised TSH — check free T4",
                    listOf("Low FT4 = overt; normal FT4 = subclinical."),
                    c.caution,
                )
            }
            Spacer(Modifier.height(16.dp))
            VerdictPanel("ATA 2026", ataTitle, ataPoints, ataColor)
            if (ata == Thyroid.AtaPregnancyAction.TreatOvert) {
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Weight for dose", style = Theme.type.caption, color = c.textSecondary, modifier = Modifier.weight(1f))
                    NumberField(weightText, { weightText = it }, placeholder = "kg", suffix = "kg", modifier = Modifier.width(100.dp))
                }
            }

            // ---------------- MoHFW 2014
            val mohfw = Thyroid.mohfwPregnancy(tsh, trimester)
            val (mTitle, mPoints, mColor) = when (mohfw) {
                Thyroid.PregnancyAction.WithinRange -> Triple("Within ${formatNumber(range.low)}–${formatNumber(range.high)} — no levothyroxine", emptyList(), c.positive)
                Thyroid.PregnancyAction.BelowRange -> Triple("Below trimester range", listOf("Outside the scope of this guideline."), c.caution)
                Thyroid.PregnancyAction.Start25 -> Triple("Start levothyroxine 25 mcg/day", listOf("TSH above ${formatNumber(range.high)} and up to 10."), c.accent)
                Thyroid.PregnancyAction.Start50 -> Triple("Start levothyroxine 50 mcg/day", listOf("TSH above 10."), c.accent)
            }
            val mAll = if (mohfw == Thyroid.PregnancyAction.Start25 || mohfw == Thyroid.PregnancyAction.Start50) {
                mPoints + "Repeat TSH after 4–6 weeks. Target <2.5 (1st trimester), <3 later; avoid <0.1."
            } else mPoints
            Spacer(Modifier.height(10.dp))
            VerdictPanel("MoHFW India 2014", mTitle, mAll, mColor)
        }
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(
            listOf(
                cite("ata-2026-pregnancy", "Recommendations Tables 1, 9–12; Flowchart 2"),
                cite("mohfw-thyroid-2014", "Reference ranges and treatment"),
            ),
        )
    }
}

@Composable
private fun VerdictPanel(authority: String, title: String, points: List<String>, color: Color) {
    val c = Theme.colors
    Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(14.dp)) {
        Text(authority.uppercase(), style = Theme.type.overline, color = c.textTertiary)
        Spacer(Modifier.height(4.dp))
        Text(title, style = Theme.type.bodyStrong, color = color)
        points.forEach {
            Spacer(Modifier.height(4.dp))
            Text(it, style = Theme.type.caption, color = c.textSecondary)
        }
    }
}

@Composable
fun TshGeneralTool() {
    TshGeneralToolImpl()
}
