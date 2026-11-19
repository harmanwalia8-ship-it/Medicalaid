package com.medicalaid.app.ui.sources

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Sources
import com.medicalaid.app.ui.components.Bullets
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.components.ScreenTitle
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.components.SurfaceCard
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

private val authorityNames = mapOf(
    Authority.MoHFW to "Ministry of Health & Family Welfare, Government of India",
    Authority.ICMR to "Indian Council of Medical Research",
    Authority.ACOG to "American College of Obstetricians and Gynecologists",
    Authority.CDC to "US Centers for Disease Control and Prevention",
    Authority.SMFM to "Society for Maternal-Fetal Medicine",
    Authority.USPSTF to "US Preventive Services Task Force",
    Authority.FDA to "US Food and Drug Administration",
    Authority.IDSA to "Infectious Diseases Society of America",
    Authority.ADA to "American Diabetes Association",
    Authority.ATA to "American Thyroid Association",
    Authority.GINA to "Global Initiative for Asthma",
    Authority.NHLBI to "US National Heart, Lung, and Blood Institute (NAEPP)",
    Authority.KDIGO to "Kidney Disease: Improving Global Outcomes",
    Authority.NKF to "National Kidney Foundation (US)",
    Authority.Paper to "Original publications",
    Authority.NICE to "National Institute for Health and Care Excellence (UK)",
    Authority.RCOG to "Royal College of Obstetricians and Gynaecologists",
    Authority.WHO to "World Health Organization",
)

@Composable
fun SourcesScreen(contentPadding: PaddingValues, onMenu: (() -> Unit)? = null) {
    val c = Theme.colors
    val uri = LocalUriHandler.current
    val grouped = Sources.all.groupBy { it.authority }.toList().sortedBy { it.first.ordinal }

    LazyColumn(Modifier.fillMaxSize(), contentPadding = contentPadding) {
        item { ScreenTitle("Sources", "Every recommendation in the app links to one of these documents.", onMenu = onMenu) }
        item {
            SurfaceCard(Modifier.padding(horizontal = Gutter).padding(top = 12.dp)) {
                Text("How content is chosen", style = Theme.type.headline, color = c.text)
                Spacer(Modifier.height(10.dp))
                Bullets(
                    listOf(
                        "Government of India (MoHFW, ICMR) and ACOG first.",
                        "Where they are silent: CDC, SMFM, USPSTF, FDA, ADA, ATA, GINA, NICE, RCOG, WHO, IDSA.",
                        "Doses are written as the source states them. Where guidelines differ, both are shown and labelled.",
                        "Content reviewed: ${Sources.REVIEWED}.",
                    ),
                )
            }
        }
        item {
            SurfaceCard(Modifier.padding(horizontal = Gutter).padding(top = 10.dp)) {
                Text("For clinicians", style = Theme.type.headline, color = c.text)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Decision support, not a substitute for clinical judgement. Verify doses against the linked source and the patient's renal function, allergies, interactions and local formulary. The app stores no patient data.",
                    style = Theme.type.body, color = c.textSecondary,
                )
            }
        }
        grouped.forEach { (authority, sources) ->
            item(key = "h-" + authority.name) {
                SectionHeader(authorityNames[authority] ?: authority.short, Modifier.padding(horizontal = Gutter), trailing = "${sources.size}")
            }
            item(key = "g-" + authority.name) {
                Column(
                    Modifier.padding(horizontal = Gutter).fillMaxWidth().clip(Radius.card)
                        .background(c.surface).border(1.dp, c.hairline, Radius.card),
                ) {
                    sources.forEachIndexed { i, s ->
                        if (i > 0) Hairline(Modifier.padding(start = 18.dp))
                        Row(
                            Modifier.fillMaxWidth().clickable { runCatching { uri.openUri(s.url) } }
                                .padding(horizontal = 18.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.Top,
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(s.title, style = Theme.type.bodyStrong, color = c.text)
                                Spacer(Modifier.height(2.dp))
                                Text(
                                    listOfNotNull(s.document, s.year, s.status).joinToString(" · "),
                                    style = Theme.type.caption, color = c.textSecondary,
                                )
                            }
                            Spacer(Modifier.width(10.dp))
                            Icon(Icons.AutoMirrored.Outlined.OpenInNew, "Open", tint = c.textTertiary, modifier = Modifier.padding(top = 3.dp).size(16.dp))
                        }
                    }
                }
            }
        }
    }
}
