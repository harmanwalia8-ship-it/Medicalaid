package com.medicalaid.app.ui.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.cite
import com.medicalaid.app.tools.Renal
import com.medicalaid.app.tools.Renal.GfrCategory
import com.medicalaid.app.tools.Renal.Sex
import com.medicalaid.app.ui.components.GuidelineBasis
import com.medicalaid.app.ui.components.Overline
import com.medicalaid.app.ui.components.Segmented
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.formatDecimals
import com.medicalaid.app.ui.formatNumber
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

private enum class CrUnit(val label: String) { MgDl("mg/dL"), Umol("µmol/L") }

@Composable
fun RenalTool(
    initialCreatinine: String = "",
    initialAge: String = "",
    initialSex: Sex = Sex.Female,
    initialWeight: String = "",
    initialHeight: String = "",
) {
    val c = Theme.colors
    var unit by remember { mutableStateOf(CrUnit.MgDl) }
    var crText by remember { mutableStateOf(initialCreatinine) }
    var ageText by remember { mutableStateOf(initialAge) }
    var sex by remember { mutableStateOf(initialSex) }
    var weightText by remember { mutableStateOf(initialWeight) }
    var heightText by remember { mutableStateOf(initialHeight) }

    val crRaw = crText.toDecimalOrNull()
    val scr = crRaw?.let { if (unit == CrUnit.MgDl) it else it / Renal.UMOL_PER_MG_DL }?.takeIf { it in 0.1..25.0 }
    val age = ageText.toDecimalOrNull()?.toInt()?.takeIf { it in 18..110 }
    val weight = weightText.toDecimalOrNull()?.takeIf { it in 25.0..300.0 }
    val height = heightText.toDecimalOrNull()?.takeIf { it in 120.0..230.0 }

    SurfaceCard {
        Overline("Calculator", color = c.accent)
        Spacer(Modifier.height(4.dp))
        Text("eGFR & creatinine clearance", style = Theme.type.headline, color = c.text)
        Text("Adults · CKD-EPI 2021 (no race) and Cockcroft–Gault", style = Theme.type.caption, color = c.textTertiary)
        Spacer(Modifier.height(14.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Creatinine", style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
            NumberField(crText, { crText = it }, placeholder = unit.label, suffix = unit.label, modifier = Modifier.width(130.dp))
        }
        Spacer(Modifier.height(8.dp))
        Segmented(CrUnit.entries.map { it to it.label }, unit, { unit = it; crText = "" })
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Age", style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
            NumberField(ageText, { ageText = it }, placeholder = "years", suffix = "y", modifier = Modifier.width(130.dp))
        }
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Sex", style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
            Segmented(listOf(Sex.Female to "Female", Sex.Male to "Male"), sex, { sex = it })
        }
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("Weight", style = Theme.type.body, color = c.text)
                Text("Optional — for CrCl", style = Theme.type.caption, color = c.textTertiary)
            }
            NumberField(weightText, { weightText = it }, placeholder = "kg", suffix = "kg", modifier = Modifier.width(130.dp))
        }
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("Height", style = Theme.type.body, color = c.text)
                Text("Optional — for eGFR in mL/min", style = Theme.type.caption, color = c.textTertiary)
            }
            NumberField(heightText, { heightText = it }, placeholder = "cm", suffix = "cm", modifier = Modifier.width(130.dp))
        }

        if (scr != null && age != null) {
            val egfr = Renal.ckdEpi2021(scr, age, sex)
            val cat = Renal.category(egfr)
            Spacer(Modifier.height(18.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(formatNumber(egfr), style = Theme.type.title, color = c.text)
                Spacer(Modifier.width(6.dp))
                Text("mL/min/1.73 m²", style = Theme.type.bodyStrong, color = c.textSecondary, modifier = Modifier.padding(bottom = 4.dp))
            }
            Text("eGFR · CKD-EPI 2021", style = Theme.type.caption, color = c.textSecondary)
            Spacer(Modifier.height(10.dp))
            val (fg, bg) = when (cat) {
                GfrCategory.G1, GfrCategory.G2 -> c.positive to c.positiveSoft
                GfrCategory.G3a, GfrCategory.G3b -> c.caution to c.cautionSoft
                GfrCategory.G4, GfrCategory.G5 -> c.danger to c.dangerSoft
            }
            Text(
                "KDIGO ${cat.code} · ${cat.label}", style = Theme.type.label, color = fg,
                modifier = Modifier.clip(Radius.inner).background(bg).padding(horizontal = 10.dp, vertical = 6.dp),
            )
            if (cat == GfrCategory.G1 || cat == GfrCategory.G2) {
                Spacer(Modifier.height(4.dp))
                Text("G1–G2 indicate CKD only with other markers of kidney damage (e.g. albuminuria).", style = Theme.type.caption, color = c.textTertiary)
            }

            if (weight != null && height != null) {
                val bsa = Renal.bsaMosteller(height, weight)
                Spacer(Modifier.height(12.dp))
                ResultRow(
                    "eGFR for drug dosing",
                    "${formatNumber(Renal.nonIndexedEgfr(egfr, bsa))} mL/min",
                    "De-indexed with BSA ${formatDecimals(bsa, 2)} m² (Mosteller). FDA: use mL/min, not per 1.73 m², when a label doses by eGFR.",
                )
            }
            if (weight != null) {
                Spacer(Modifier.height(10.dp))
                ResultRow(
                    "Creatinine clearance (Cockcroft–Gault)",
                    "${formatNumber(Renal.cockcroftGault(scr, age, weight, sex))} mL/min",
                    "Actual body weight. FDA: in overweight or obese patients, ideal or adjusted body weight is likely more accurate.",
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                "Many drug labels set renal doses by CrCl (Cockcroft–Gault); others by eGFR. Use the measure the label names.",
                style = Theme.type.caption, color = c.textSecondary,
            )
        } else if (crRaw != null || ageText.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))
            Text("Enter creatinine and an age of 18–110 years.", style = Theme.type.caption, color = c.textSecondary)
        }

        Spacer(Modifier.height(14.dp))
        GuidelineBasis(
            listOf(
                cite("nkf-ckd-epi-2021", "Equation and coefficients"),
                cite("kdigo-ckd-2024", "GFR categories G1–G5"),
                cite("fda-renal-guidance-2024", "Section on estimating renal function"),
                cite("cockcroft-gault-1976"),
            ),
        )
    }
}

@Composable
private fun ResultRow(label: String, value: String, note: String) {
    val c = Theme.colors
    Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(12.dp)) {
        Text(label.uppercase(), style = Theme.type.overline, color = c.textTertiary)
        Spacer(Modifier.height(4.dp))
        Text(value, style = Theme.type.bodyStrong, color = c.text)
        Spacer(Modifier.height(2.dp))
        Text(note, style = Theme.type.caption, color = c.textSecondary)
    }
}
