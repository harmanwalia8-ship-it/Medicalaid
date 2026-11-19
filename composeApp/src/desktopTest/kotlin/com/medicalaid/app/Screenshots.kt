package com.medicalaid.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.ImageComposeScene
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Conditions
import com.medicalaid.app.content.Population
import com.medicalaid.app.content.drugs.Drugs
import com.medicalaid.app.ui.drugs.DrugScreen
import com.medicalaid.app.ui.drugs.DrugsScreen
import com.medicalaid.app.ui.home.AboutScreen
import com.medicalaid.app.ui.home.CalculatorsScreen
import com.medicalaid.app.ui.home.HomeScreen
import com.medicalaid.app.ui.home.IntendedUseDialog
import com.medicalaid.app.pregnancy.Trimester
import com.medicalaid.app.tools.Thyroid
import com.medicalaid.app.ui.medicines.ConditionScreen
import com.medicalaid.app.ui.medicines.MedicinesScreen
import com.medicalaid.app.ui.pregnancy.PregnancyScreen
import com.medicalaid.app.ui.pregnancy.PregnancyState
import com.medicalaid.app.ui.sources.SourcesScreen
import com.medicalaid.app.ui.theme.AppTheme
import com.medicalaid.app.ui.theme.Theme
import com.medicalaid.app.ui.tools.GlycemiaTool
import com.medicalaid.app.ui.tools.TshPregnancyTool
import kotlinx.datetime.LocalDate
import org.jetbrains.skia.EncodedImageFormat
import java.io.File
import kotlin.test.Test

/**
 * Renders key screens headlessly to PNG for visual review.
 * Run: ./gradlew :composeApp:desktopTest -Pscreenshots  → composeApp/build/screenshots/
 */
class Screenshots {
    private val out = File(System.getProperty("screenshots.dir") ?: "build/screenshots").apply { mkdirs() }
    private val enabled = System.getProperty("screenshots.enabled") == "true"
    private val today = LocalDate(2026, 11, 19)
    private val pad = PaddingValues(top = 16.dp, bottom = 32.dp)

    private fun shot(name: String, heightDp: Int = 851, dark: Boolean = false, content: @Composable () -> Unit) {
        if (!enabled) return
        val density = 2.75f
        val scene = ImageComposeScene(
            width = (393 * density).toInt(),
            height = (heightDp * density).toInt(),
            density = Density(density),
        ) {
            AppTheme(darkTheme = dark) {
                Box(Modifier.fillMaxSize().background(Theme.colors.background)) { content() }
            }
        }
        var t = 0L
        repeat(12) { scene.render(t); t += 100_000_000L; Thread.sleep(40) }
        val image = scene.render(t)
        File(out, "$name.png").writeBytes(image.encodeToData(EncodedImageFormat.PNG)!!.bytes)
        scene.close()
    }

    private fun datedState() = PregnancyState().apply { lmp = LocalDate(2026, 6, 1) }

    @Test
    fun render() {
        shot("01-pregnancy-empty") { PregnancyScreen(PregnancyState(), today, pad) }
        shot("02-pregnancy-dated") { PregnancyScreen(datedState(), today, pad) }
        shot("03-pregnancy-dated-long", heightDp = 2600) { PregnancyScreen(datedState(), today, pad) }
        shot("04-pregnancy-redated", heightDp = 1300) {
            PregnancyScreen(
                PregnancyState().apply {
                    lmp = LocalDate(2026, 6, 1); useScan = true; scanDate = LocalDate(2026, 8, 10); scanWeeks = 8; scanDays = 6
                },
                today, pad,
            )
        }
        shot("05-pregnancy-dark", dark = true) { PregnancyScreen(datedState(), today, pad) }
        shot("06-medicines-pregnancy", heightDp = 1500) { MedicinesScreen("", {}, Population.Pregnancy, {}, { _, _ -> }, pad) }
        shot("07-medicines-general") { MedicinesScreen("", {}, Population.General, {}, { _, _ -> }, pad) }
        shot("08-search-vomiting") { MedicinesScreen("vomiting", {}, Population.Pregnancy, {}, { _, _ -> }, pad) }
        shot("09-condition-nvp", heightDp = 2400) { ConditionScreen(Conditions.byId("nvp")!!, null, null, {}, pad) }
        shot("10-condition-icp", heightDp = 2400) { ConditionScreen(Conditions.byId("pruritus")!!, null, null, {}, pad) }
        shot("11-tool-glycemia-a1c", heightDp = 1100) {
            Box(Modifier.padding(20.dp)) { GlycemiaTool(initialA1c = "9.2") }
        }
        shot("12-tool-glycemia-rbs", heightDp = 900) {
            Box(Modifier.padding(20.dp)) { GlycemiaTool(initialGlucose = "210") }
        }
        shot("13-tool-tsh-pregnancy", heightDp = 1000) {
            Box(Modifier.padding(20.dp)) { TshPregnancyTool(Trimester.Second, "6.2", Thyroid.Ft4.Normal) }
        }
        shot("14-condition-diabetes-general", heightDp = 2600) { ConditionScreen(Conditions.byId("diabetes-general")!!, null, null, {}, pad) }
        shot("15-sources", heightDp = 1600) { SourcesScreen(pad) }
        shot("16-condition-hypertension-dark", heightDp = 2000, dark = true) { ConditionScreen(Conditions.byId("hypertension")!!, "Labetalol IV (order set)", null, {}, pad) }
        shot("20-home") { HomeScreen("", {}, {}, {}, {}, {}, pad) }
        shot("21-home-search", heightDp = 1200) { HomeScreen("metf", {}, {}, {}, {}, {}, pad) }
        shot("22-drugs", heightDp = 1600) { DrugsScreen("", {}, {}, {}, pad) }
        shot("23-drug-metformin", heightDp = 3400) { DrugScreen(Drugs.byId("metformin")!!, {}, pad) }
        shot("24-drug-metformin-dark", dark = true) { DrugScreen(Drugs.byId("metformin")!!, {}, pad) }
        shot("25-calculators", heightDp = 1400) { CalculatorsScreen(null, {}, {}, pad) }
        shot("26-about", heightDp = 1400) { AboutScreen({}, pad) }
        shot("27-intro") { IntendedUseDialog {} }
        shot("29-drugs-list", heightDp = 2400) { DrugsScreen("", {}, {}, {}, pad) }
        shot("30-drug-telmisartan", heightDp = 3600) { DrugScreen(Drugs.byId("telmisartan")!!, {}, pad) }
        shot("31-drug-apixaban") { DrugScreen(Drugs.byId("apixaban")!!, {}, pad) }
        shot("32-protocol-stroke", heightDp = 5200) {
            com.medicalaid.app.ui.protocols.ProtocolScreen(com.medicalaid.app.content.protocols.Protocols.byId("acute-stroke")!!, {}, {}, pad)
        }
        shot("33-score-wells-pe") {
            com.medicalaid.app.ui.scores.ScoreScreen(
                com.medicalaid.app.content.scores.Scores.byId("wells-pe")!!, {}, pad,
                initialAnswers = com.medicalaid.app.content.scores.Scores.byId("wells-pe")!!.items.take(2).associate { it.id to 1 },
            )
        }
        shot("34-score-heart", heightDp = 1500) {
            com.medicalaid.app.ui.scores.ScoreScreen(com.medicalaid.app.content.scores.Scores.byId("heart")!!, {}, pad)
        }
        shot("35-scores-list", heightDp = 1500) { com.medicalaid.app.ui.scores.ScoresScreen("", {}, {}, {}, pad) }
        shot("36-home-v3") { HomeScreen("", {}, {}, {}, {}, {}, pad) }
        shot("28-tool-renal", heightDp = 1500) {
            Box(Modifier.padding(20.dp)) { com.medicalaid.app.ui.tools.RenalTool("1.4", "68", com.medicalaid.app.tools.Renal.Sex.Male, "72", "168") }
        }
    }
}
