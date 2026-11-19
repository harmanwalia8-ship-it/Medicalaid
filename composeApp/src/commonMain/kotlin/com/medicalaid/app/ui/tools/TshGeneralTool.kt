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
import com.medicalaid.app.tools.Thyroid
import com.medicalaid.app.tools.Thyroid.GeneralFinding
import com.medicalaid.app.ui.components.Bullets
import com.medicalaid.app.ui.components.GuidelineBasis
import com.medicalaid.app.ui.components.Overline
import com.medicalaid.app.ui.components.Segmented
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.formatNumber
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

@Composable
internal fun TshGeneralToolImpl() {
    val c = Theme.colors
    var tshText by remember { mutableStateOf("") }
    var lowText by remember { mutableStateOf("0.4") }
    var highText by remember { mutableStateOf("4.0") }
    var ft4 by remember { mutableStateOf(Thyroid.Ft4.Unknown) }
    var olderOrCvd by remember { mutableStateOf(false) }
    var weightText by remember { mutableStateOf("") }

    val tsh = tshText.toDecimalOrNull()?.takeIf { it in 0.0..500.0 }
    val low = lowText.toDecimalOrNull()
    val high = highText.toDecimalOrNull()
    val range = if (low != null && high != null && low < high) Thyroid.Range(low, high) else Thyroid.DEFAULT_RANGE
    val weight = weightText.toDecimalOrNull()?.takeIf { it in 20.0..250.0 }

    SurfaceCard {
        Overline("Calculator", color = c.accent)
        Spacer(Modifier.height(4.dp))
        Text("TSH — non-pregnant adult", style = Theme.type.headline, color = c.text)
        Spacer(Modifier.height(14.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("TSH", style = Theme.type.body, color = c.text, modifier = Modifier.weight(1f))
            NumberField(tshText, { tshText = it }, placeholder = "mIU/L", suffix = "mIU/L", modifier = Modifier.width(130.dp))
        }
        Spacer(Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text("Lab reference range", style = Theme.type.body, color = c.text)
                Text("Default 0.4–4.0 (ATA)", style = Theme.type.caption, color = c.textTertiary)
            }
            NumberField(lowText, { lowText = it }, placeholder = "low", modifier = Modifier.width(64.dp))
            Text("–", style = Theme.type.body, color = c.textSecondary, modifier = Modifier.padding(horizontal = 6.dp))
            NumberField(highText, { highText = it }, placeholder = "high", modifier = Modifier.width(64.dp))
        }
        Spacer(Modifier.height(12.dp))
        Text("Free T4", style = Theme.type.body, color = c.text)
        Spacer(Modifier.height(6.dp))
        Segmented(
            listOf(Thyroid.Ft4.Low to "Low", Thyroid.Ft4.Normal to "Normal", Thyroid.Ft4.Unknown to "Not known"),
            ft4, { ft4 = it },
        )
        Spacer(Modifier.height(12.dp))
        Text("Age ≥65 or cardiovascular disease", style = Theme.type.body, color = c.text)
        Spacer(Modifier.height(6.dp))
        Segmented(listOf(false to "No", true to "Yes"), olderOrCvd, { olderOrCvd = it })

        if (tsh != null) {
            val finding = Thyroid.general(tsh, ft4, range)
            Spacer(Modifier.height(16.dp))
            Column(Modifier.fillMaxWidth().clip(Radius.inner).background(c.surfaceMuted).padding(14.dp)) {
                val (title, color) = when (finding) {
                    GeneralFinding.BelowRange -> "Below reference range" to c.caution
                    GeneralFinding.WithinRange -> "Within reference range" to c.positive
                    GeneralFinding.Overt -> "Overt hypothyroidism" to c.danger
                    GeneralFinding.SubclinicalTenOrMore -> "Subclinical hypothyroidism · TSH ≥10" to c.caution
                    GeneralFinding.SubclinicalBelowTen -> "Subclinical hypothyroidism · TSH <10" to c.caution
                    GeneralFinding.NeedFt4 -> "Raised TSH — check free T4" to c.caution
                }
                Text(title, style = Theme.type.bodyStrong, color = color)
                Spacer(Modifier.height(8.dp))
                val points = when (finding) {
                    GeneralFinding.BelowRange -> listOf("Low TSH is outside the scope of this hypothyroidism tool.")
                    GeneralFinding.WithinRange -> listOf(
                        "If on levothyroxine: keep TSH within the reference range and avoid doses that suppress TSH (NICE 1.4.1).",
                    )
                    GeneralFinding.NeedFt4 -> listOf(
                        "Low FT4 = overt hypothyroidism; normal FT4 = subclinical hypothyroidism.",
                    )
                    GeneralFinding.Overt -> if (!olderOrCvd) {
                        buildList {
                            add("NICE 1.3.6: consider starting levothyroxine 1.6 mcg/kg/day, rounded to the nearest 25 mcg (adults <65, no cardiovascular disease).")
                            if (weight != null) add("For ${formatNumber(weight)} kg: ${Thyroid.niceFullReplacementMcg(weight)} mcg once daily.")
                            add("ICMR: 1.6–1.8 mcg/kg/day as a single fasting dose, nothing by mouth for 1 hour after.")
                        }
                    } else {
                        listOf(
                            "NICE 1.3.7: consider 25–50 mcg/day with titration (age ≥65 or cardiovascular disease).",
                            "ATA 2014: known coronary artery disease — start 12.5–25 mcg/day.",
                            "ICMR: 12.5–25 mcg/day, increasing by 12.5–25 mcg every 3–4 weeks.",
                        )
                    }
                    GeneralFinding.SubclinicalTenOrMore -> listOf(
                        "NICE 1.5.3: consider levothyroxine if TSH is 10 or higher on 2 separate occasions 3 months apart.",
                        "ATA/AACE 2012: TSH >10 carries higher risk of heart failure and cardiovascular mortality — consider treatment.",
                        "ATA/AACE 2012: a daily dose of 25–75 mcg should be considered for subclinical hypothyroidism.",
                    )
                    GeneralFinding.SubclinicalBelowTen -> listOf(
                        "NICE 1.5.4: consider a 6-month trial of levothyroxine if age <65, TSH above range but <10 on 2 occasions 3 months apart, and symptoms of hypothyroidism.",
                        "If untreated (NICE 1.5.7): TSH and FT4 once a year if features of underlying thyroid disease, otherwise every 2–3 years.",
                        "ICMR: treat if large goitre, positive TPO antibody, ASCVD, heart failure, dyslipidaemia, infertility, depression, refractory anaemia, or autoimmune history.",
                    )
                }
                Bullets(points)
                if (finding == GeneralFinding.Overt && !olderOrCvd) {
                    Spacer(Modifier.height(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Weight for dose", style = Theme.type.caption, color = c.textSecondary, modifier = Modifier.weight(1f))
                        NumberField(weightText, { weightText = it }, placeholder = "kg", suffix = "kg", modifier = Modifier.width(100.dp))
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            Text(
                "After starting or changing the dose: TSH in 4–6 weeks (ATA 2014). NICE: every 3 months until stable, then yearly.",
                style = Theme.type.caption, color = c.textSecondary,
            )
        }
        Spacer(Modifier.height(14.dp))
        GuidelineBasis(
            listOf(
                cite("nice-ng145", "1.3.6–1.3.7, 1.4.1–1.4.3, 1.5.3–1.5.7"),
                cite("ata-2014", "Recs 1b, 4b, 6a"),
                cite("ata-aace-2012", "R15, R16, R22.8"),
                cite("icmr-stw-hypothyroid-2022"),
            ),
        )
    }
}
