package com.medicalaid.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.medicalaid.app.content.Sources
import com.medicalaid.app.ui.components.Bullets
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

const val APP_VERSION = "0.4.0"

/** Bump to show the intended-use notice again after material changes. */
const val INTRO_VERSION = 1

val intendedUse = listOf(
    "For registered medical practitioners and qualified healthcare professionals.",
    "Summarises published guidelines and official drug labels, and links to each source.",
    "Does not diagnose and does not choose treatment — decisions remain the clinician's.",
    "Verify against the linked source and the patient before acting; guidelines change.",
    "Stores no patient data and has no network access.",
)

@Composable
fun AboutScreen(onMenu: () -> Unit, contentPadding: PaddingValues) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle("About", "Intended use, disclaimer and licences", onMenu = onMenu) }
        item { Card("Intended use", intendedUse) }
        item {
            Card(
                "Sources",
                listOf(
                    "Government of India (MoHFW, ICMR) and ACOG first; then WHO, CDC, USPSTF, FDA, ADA, ATA, GINA, NHLBI, NICE, RCOG, SMFM, IDSA.",
                    "Drug profiles summarise US FDA prescribing information (DailyMed).",
                    "Commercial references (BNF, Medscape, Drugs.com, UpToDate) are linked, never copied.",
                    "Where guidelines disagree, both are shown and labelled.",
                    "Facts are stated in our own words; no guideline text, tables or images are reproduced. Naming an organisation does not imply its endorsement.",
                ),
            )
        }
        item {
            Card(
                "No warranty",
                listOf("Provided “as is”, without warranty of any kind. The authors accept no liability for any use of the software or content."),
            )
        }
        item {
            Card(
                "Licences",
                listOf(
                    "Code: Apache License 2.0.",
                    "Written content: Creative Commons Attribution 4.0 (CC BY 4.0).",
                    "Inter typeface: SIL Open Font License 1.1.",
                ),
            )
        }
        item {
            Text(
                "Version $APP_VERSION · Content reviewed ${Sources.REVIEWED}",
                style = Theme.type.caption, color = Theme.colors.textTertiary,
                modifier = Modifier.padding(horizontal = Gutter).padding(top = 20.dp),
            )
        }
    }
}

@Composable
private fun Card(title: String, points: List<String>) {
    SurfaceCard(Modifier.padding(horizontal = Gutter).padding(top = 12.dp)) {
        Text(title, style = Theme.type.headline, color = Theme.colors.text)
        Spacer(Modifier.height(10.dp))
        Bullets(points)
    }
}

/** Shown once (per [INTRO_VERSION]) before the app can be used. */
@Composable
fun IntendedUseDialog(onAccept: () -> Unit) {
    val c = Theme.colors
    Dialog(onDismissRequest = {}, properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)) {
        Column(
            Modifier.fillMaxWidth().clip(Radius.card).background(c.surface).padding(24.dp),
        ) {
            Text("For clinicians", style = Theme.type.title, color = c.text)
            Spacer(Modifier.height(6.dp))
            Text("Before you start", style = Theme.type.body, color = c.textSecondary)
            Spacer(Modifier.height(18.dp))
            Bullets(intendedUse)
            Spacer(Modifier.height(24.dp))
            Box(
                Modifier.fillMaxWidth().clip(Radius.pill).background(c.accent).clickable(onClick = onAccept).padding(vertical = 14.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text("I understand", style = Theme.type.bodyStrong, color = c.onAccent, textAlign = TextAlign.Center)
            }
        }
    }
}
