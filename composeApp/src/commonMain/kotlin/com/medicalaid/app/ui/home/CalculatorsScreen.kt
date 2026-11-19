package com.medicalaid.app.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.medicalaid.app.Destination
import com.medicalaid.app.pregnancy.Trimester
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.theme.Theme
import com.medicalaid.app.ui.tools.GlycemiaTool
import com.medicalaid.app.ui.tools.TshGeneralTool
import com.medicalaid.app.ui.tools.TshPregnancyTool

@Composable
fun CalculatorsScreen(
    trimester: Trimester?,
    onNavigate: (Destination) -> Unit,
    onMenu: () -> Unit,
    contentPadding: PaddingValues,
) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle("Calculators", "Published formulas and thresholds, each with its source", onMenu = onMenu) }
        item {
            SurfaceCard(Modifier.padding(horizontal = Gutter).padding(top = 12.dp), onClick = { onNavigate(Destination.Pregnancy) }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconBadge(Destination.Pregnancy.icon)
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Text("Due date & gestational age", style = Theme.type.bodyStrong, color = Theme.colors.text)
                        Text("LMP, dating scan (ACOG redating)", style = Theme.type.caption, color = Theme.colors.textSecondary)
                    }
                    Icon(Icons.Outlined.ChevronRight, null, tint = Theme.colors.textTertiary, modifier = Modifier.size(20.dp))
                }
            }
        }
        item { SectionHeader("Diabetes", Modifier.padding(horizontal = Gutter)) }
        item { Box(Modifier.padding(horizontal = Gutter)) { GlycemiaTool() } }
        item { SectionHeader("Thyroid · pregnancy", Modifier.padding(horizontal = Gutter)) }
        item { Box(Modifier.padding(horizontal = Gutter)) { TshPregnancyTool(trimester) } }
        item { SectionHeader("Thyroid · non-pregnant adult", Modifier.padding(horizontal = Gutter)) }
        item { Box(Modifier.padding(horizontal = Gutter)) { TshGeneralTool() } }
    }
}
