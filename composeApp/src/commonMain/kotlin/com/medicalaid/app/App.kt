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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Emergency
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material.icons.outlined.PregnantWoman
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Conditions
import com.medicalaid.app.content.Population
import com.medicalaid.app.content.Sources
import com.medicalaid.app.content.drugs.Drugs
import com.medicalaid.app.platform.Prefs
import com.medicalaid.app.pregnancy.DatingOutcome
import com.medicalaid.app.ui.drugs.DrugScreen
import com.medicalaid.app.ui.drugs.DrugsScreen
import com.medicalaid.app.ui.home.APP_VERSION
import com.medicalaid.app.ui.home.AboutScreen
import com.medicalaid.app.ui.home.CalculatorsScreen
import com.medicalaid.app.ui.home.HomeScreen
import com.medicalaid.app.ui.home.INTRO_VERSION
import com.medicalaid.app.ui.home.IconBadge
import com.medicalaid.app.ui.home.IntendedUseDialog
import com.medicalaid.app.ui.medicines.ConditionScreen
import com.medicalaid.app.ui.medicines.MedicinesScreen
import com.medicalaid.app.ui.pregnancy.PregnancyScreen
import com.medicalaid.app.ui.pregnancy.PregnancyState
import com.medicalaid.app.ui.sources.SourcesScreen
import com.medicalaid.app.ui.theme.AppTheme
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme
import com.medicalaid.app.ui.today
import com.medicalaid.app.ui.plural
import kotlinx.coroutines.launch

@Composable
expect fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit)

enum class Destination(val label: String, val icon: ImageVector) {
    Home("Home", Icons.Outlined.Home),
    Pregnancy("Pregnancy", Icons.Outlined.PregnantWoman),
    Treatment("Treatment", Icons.Outlined.MedicalServices),
    Drugs("Drugs", Icons.Outlined.Medication),
    Scores("Scores", Icons.Outlined.Checklist),
    Protocols("Protocols", Icons.Outlined.Emergency),
    Calculators("Calculators", Icons.Outlined.Calculate),
    Sources("Sources", Icons.AutoMirrored.Outlined.MenuBook),
    About("About", Icons.Outlined.Info),
}

/** Screens opened on top of a section. */
sealed interface Detail {
    data class ConditionPage(val id: String, val drug: String?) : Detail
    data class DrugPage(val id: String) : Detail
    data class ToolPage(val kind: com.medicalaid.app.content.ToolKind) : Detail
    data class ScorePage(val id: String) : Detail
    data class ProtocolPage(val id: String) : Detail
}

private const val INTRO_KEY = "intro_ack"

@Composable
fun App() {
    AppTheme {
        val pregnancy = remember { PregnancyState() }
        var root by remember { mutableStateOf(Destination.Home) }
        var stack by remember { mutableStateOf(listOf<Detail>()) }
        var homeQuery by remember { mutableStateOf("") }
        var treatmentQuery by remember { mutableStateOf("") }
        var drugQuery by remember { mutableStateOf("") }
        var scoreQuery by remember { mutableStateOf("") }
        var protocolQuery by remember { mutableStateOf("") }
        var population by remember { mutableStateOf(Population.Pregnancy) }
        var introAccepted by remember { mutableStateOf(Prefs.getInt(INTRO_KEY, 0) >= INTRO_VERSION) }
        val today = remember { today() }
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        fun navigate(d: Destination) {
            root = d
            stack = emptyList()
            scope.launch { drawerState.close() }
        }
        fun push(detail: Detail) { stack = stack + detail }
        fun pop() { stack = stack.dropLast(1) }
        val openMenu: () -> Unit = { scope.launch { drawerState.open() } }

        PlatformBackHandler(enabled = drawerState.isOpen) { scope.launch { drawerState.close() } }
        PlatformBackHandler(enabled = !drawerState.isOpen && stack.isNotEmpty()) { pop() }
        PlatformBackHandler(enabled = !drawerState.isOpen && stack.isEmpty() && root != Destination.Home) { root = Destination.Home }

        val top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
        val bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        val padding = PaddingValues(top = top + 8.dp, bottom = bottom + 32.dp)
        val trimester = (pregnancy.outcome(today) as? DatingOutcome.Ok)?.result?.trimester

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = { Drawer(root) { navigate(it) } },
        ) {
            Box(Modifier.fillMaxSize().background(Theme.colors.background).imePadding()) {
                AnimatedContent(
                    targetState = root to stack,
                    transitionSpec = {
                        val forward = targetState.second.size > initialState.second.size
                        val back = targetState.second.size < initialState.second.size
                        when {
                            forward -> (slideInHorizontally(tween(260)) { it / 4 } + fadeIn(tween(220))) togetherWith fadeOut(tween(160))
                            back -> fadeIn(tween(220)) togetherWith (slideOutHorizontally(tween(240)) { it / 4 } + fadeOut(tween(180)))
                            else -> fadeIn(tween(200)) togetherWith fadeOut(tween(140))
                        }
                    },
                    label = "screen",
                ) { (currentRoot, currentStack) ->
                    when (val detail = currentStack.lastOrNull()) {
                        is Detail.ConditionPage -> Conditions.byId(detail.id)?.let { condition ->
                            ConditionScreen(
                                condition, detail.drug, trimester,
                                onBack = { pop() },
                                contentPadding = padding,
                                onOpenDrug = { push(Detail.DrugPage(it)) },
                            )
                        }
                        is Detail.DrugPage -> Drugs.byId(detail.id)?.let {
                            DrugScreen(
                                it, onBack = { pop() }, contentPadding = padding,
                                onOpenRenalCalculator = { push(Detail.ToolPage(com.medicalaid.app.content.ToolKind.Renal)) },
                            )
                        }
                        is Detail.ToolPage -> com.medicalaid.app.ui.home.ToolScreen(detail.kind, trimester, onBack = { pop() }, contentPadding = padding)
                        is Detail.ScorePage -> com.medicalaid.app.content.scores.Scores.byId(detail.id)?.let {
                            com.medicalaid.app.ui.scores.ScoreScreen(it, onBack = { pop() }, contentPadding = padding)
                        }
                        is Detail.ProtocolPage -> com.medicalaid.app.content.protocols.Protocols.byId(detail.id)?.let {
                            com.medicalaid.app.ui.protocols.ProtocolScreen(it, onBack = { pop() }, onOpenScore = { id -> push(Detail.ScorePage(id)) }, contentPadding = padding)
                        }
                        null -> when (currentRoot) {
                            Destination.Home -> HomeScreen(
                                query = homeQuery,
                                onQueryChange = { homeQuery = it },
                                onNavigate = { navigate(it) },
                                onOpenCondition = { push(Detail.ConditionPage(it, null)) },
                                onOpenDrug = { push(Detail.DrugPage(it)) },
                                onMenu = openMenu,
                                contentPadding = padding,
                                onOpenScore = { push(Detail.ScorePage(it)) },
                                onOpenProtocol = { push(Detail.ProtocolPage(it)) },
                            )
                            Destination.Pregnancy -> PregnancyScreen(pregnancy, today, padding, onMenu = openMenu)
                            Destination.Treatment -> MedicinesScreen(
                                query = treatmentQuery,
                                onQueryChange = { treatmentQuery = it },
                                population = population,
                                onPopulationChange = { population = it },
                                onOpen = { id, drug -> push(Detail.ConditionPage(id, drug)) },
                                contentPadding = padding,
                                onMenu = openMenu,
                            )
                            Destination.Drugs -> DrugsScreen(
                                query = drugQuery,
                                onQueryChange = { drugQuery = it },
                                onOpen = { push(Detail.DrugPage(it)) },
                                onMenu = openMenu,
                                contentPadding = padding,
                            )
                            Destination.Scores -> com.medicalaid.app.ui.scores.ScoresScreen(
                                scoreQuery, { scoreQuery = it }, onOpen = { push(Detail.ScorePage(it)) }, onMenu = openMenu, contentPadding = padding,
                            )
                            Destination.Protocols -> com.medicalaid.app.ui.scores.ProtocolsScreen(
                                onOpen = { push(Detail.ProtocolPage(it)) }, onMenu = openMenu, contentPadding = padding,
                                query = protocolQuery, onQueryChange = { protocolQuery = it },
                            )
                            Destination.Calculators -> CalculatorsScreen(trimester, { navigate(it) }, openMenu, padding)
                            Destination.Sources -> SourcesScreen(padding, onMenu = openMenu)
                            Destination.About -> AboutScreen(openMenu, padding)
                        }
                    }
                }
            }
        }

        if (!introAccepted) {
            IntendedUseDialog {
                Prefs.putInt(INTRO_KEY, INTRO_VERSION)
                introAccepted = true
            }
        }
    }
}

@Composable
private fun Drawer(current: Destination, onSelect: (Destination) -> Unit) {
    val c = Theme.colors
    ModalDrawerSheet(
        drawerContainerColor = c.surface,
        drawerShape = RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp),
        modifier = Modifier.width(300.dp),
    ) {
        Column(Modifier.fillMaxSize().padding(horizontal = 14.dp)) {
            Row(Modifier.padding(start = 10.dp, top = 24.dp, bottom = 20.dp), verticalAlignment = Alignment.CenterVertically) {
                IconBadge(Icons.Outlined.MedicalServices, size = 44)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Medicalaid", style = Theme.type.headline, color = c.text)
                    Text("Sourced clinical reference", style = Theme.type.caption, color = c.textSecondary)
                }
            }
            Destination.entries.forEach { d ->
                val active = d == current
                Row(
                    Modifier.fillMaxWidth().clip(Radius.chip)
                        .background(if (active) c.accentSoft else c.surface)
                        .clickable { onSelect(d) }
                        .padding(horizontal = 16.dp, vertical = 13.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(d.icon, null, tint = if (active) c.accent else c.textSecondary, modifier = Modifier.size(22.dp))
                    Spacer(Modifier.width(14.dp))
                    Text(d.label, style = Theme.type.bodyStrong, color = if (active) c.accent else c.text)
                }
                if (d == Destination.Home || d == Destination.Calculators) {
                    Box(Modifier.padding(vertical = 8.dp, horizontal = 16.dp).fillMaxWidth().height(1.dp).background(c.hairline))
                }
            }
            Spacer(Modifier.weight(1f))
            Text(
                "v$APP_VERSION · Reviewed ${Sources.REVIEWED}\n${plural(Drugs.all.size, "drug")} · ${plural(Conditions.all.size, "topic")} · ${plural(Sources.all.size, "source")}",
                style = Theme.type.caption,
                color = c.textTertiary,
                modifier = Modifier.padding(start = 16.dp, bottom = 24.dp),
            )
        }
    }
}
