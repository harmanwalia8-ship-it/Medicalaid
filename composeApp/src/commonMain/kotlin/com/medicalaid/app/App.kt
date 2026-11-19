package com.medicalaid.app

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material.icons.outlined.PregnantWoman
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Conditions
import com.medicalaid.app.content.Population
import com.medicalaid.app.pregnancy.DatingOutcome
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.medicines.ConditionScreen
import com.medicalaid.app.ui.medicines.MedicinesScreen
import com.medicalaid.app.ui.pregnancy.PregnancyScreen
import com.medicalaid.app.ui.pregnancy.PregnancyState
import com.medicalaid.app.ui.sources.SourcesScreen
import com.medicalaid.app.ui.theme.AppTheme
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme
import com.medicalaid.app.ui.today

@Composable
expect fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit)

enum class Tab(val label: String, val icon: ImageVector) {
    Pregnancy("Pregnancy", Icons.Outlined.PregnantWoman),
    Medicines("Medicines", Icons.Outlined.Medication),
    Sources("Sources", Icons.AutoMirrored.Outlined.MenuBook),
}

private data class OpenCondition(val id: String, val drug: String?)

@Composable
fun App() {
    AppTheme {
        val pregnancy = remember { PregnancyState() }
        var tab by remember { mutableStateOf(Tab.Pregnancy) }
        var query by remember { mutableStateOf("") }
        var population by remember { mutableStateOf(Population.Pregnancy) }
        var open by remember { mutableStateOf<OpenCondition?>(null) }
        val today = remember { today() }

        PlatformBackHandler(enabled = open != null) { open = null }
        PlatformBackHandler(enabled = open == null && tab != Tab.Pregnancy) { tab = Tab.Pregnancy }

        val top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
        val padding = PaddingValues(top = top + 8.dp, bottom = 32.dp)

        Column(Modifier.fillMaxSize().background(Theme.colors.background)) {
            Box(Modifier.weight(1f).fillMaxWidth().imePadding()) {
                AnimatedContent(
                    targetState = Triple(tab, open, Unit),
                    transitionSpec = {
                        val forward = targetState.second != null && initialState.second == null
                        val back = targetState.second == null && initialState.second != null
                        when {
                            forward -> (slideInHorizontally(tween(260)) { it / 4 } + fadeIn(tween(220))) togetherWith fadeOut(tween(160))
                            back -> fadeIn(tween(220)) togetherWith (slideOutHorizontally(tween(240)) { it / 4 } + fadeOut(tween(180)))
                            else -> fadeIn(tween(200)) togetherWith fadeOut(tween(140))
                        }
                    },
                    label = "screen",
                ) { (currentTab, currentOpen, _) ->
                    when (currentTab) {
                        Tab.Pregnancy -> PregnancyScreen(pregnancy, today, padding)
                        Tab.Medicines -> {
                            val condition = currentOpen?.let { Conditions.byId(it.id) }
                            if (condition != null) {
                                val trimester = (pregnancy.outcome(today) as? DatingOutcome.Ok)?.result?.trimester
                                ConditionScreen(condition, currentOpen.drug, trimester, onBack = { open = null }, contentPadding = padding)
                            } else {
                                MedicinesScreen(
                                    query = query,
                                    onQueryChange = { query = it },
                                    population = population,
                                    onPopulationChange = { population = it },
                                    onOpen = { id, drug -> open = OpenCondition(id, drug) },
                                    contentPadding = padding,
                                )
                            }
                        }
                        Tab.Sources -> SourcesScreen(padding)
                    }
                }
            }
            BottomBar(tab) { selected ->
                if (selected == tab) open = null
                tab = selected
            }
        }
    }
}

@Composable
private fun BottomBar(selected: Tab, onSelect: (Tab) -> Unit) {
    val c = Theme.colors
    Column(Modifier.fillMaxWidth().background(c.surface)) {
        Hairline()
        Row(
            Modifier.fillMaxWidth().windowInsetsPadding(WindowInsets.navigationBars).padding(horizontal = 12.dp, vertical = 6.dp),
        ) {
            Tab.entries.forEach { tab ->
                val active = tab == selected
                Column(
                    Modifier.weight(1f).clip(Radius.inner)
                        .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { onSelect(tab) }
                        .padding(vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        Modifier.clip(Radius.pill).background(if (active) c.accentSoft else c.surface)
                            .padding(horizontal = 18.dp, vertical = 4.dp),
                    ) {
                        Icon(tab.icon, tab.label, tint = if (active) c.accent else c.textTertiary, modifier = Modifier.size(22.dp))
                    }
                    Spacer(Modifier.height(3.dp))
                    Text(tab.label, style = Theme.type.caption, color = if (active) c.text else c.textTertiary)
                }
            }
        }
    }
}
