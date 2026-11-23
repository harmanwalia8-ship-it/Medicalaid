package com.medicalaid.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.medicalaid.app.content.Conditions
import com.medicalaid.app.content.drugs.Drugs
import com.medicalaid.app.content.protocols.ProtocolCategory
import com.medicalaid.app.content.protocols.Protocols
import com.medicalaid.app.content.scores.Scores
import com.medicalaid.app.platform.Prefs
import com.medicalaid.app.ui.components.Gutter
import com.medicalaid.app.ui.components.Hairline
import com.medicalaid.app.ui.components.SectionHeader
import com.medicalaid.app.ui.theme.Radius
import com.medicalaid.app.ui.theme.Theme

/** A page that can be opened, pinned or revisited: c = condition, p = protocol, s = score, d = drug. */
data class PageKey(val type: Char, val id: String) {
    override fun toString() = "$type:$id"

    /** Title and a short "what is it" line, or null if the page does not exist. */
    fun describe(): Pair<String, String>? = when (type) {
        'c' -> Conditions.byId(id)?.let { it.title to it.population.label }
        'p' -> Protocols.byId(id)?.let { it.title to "Protocol · ${it.category.label}" }
        's' -> Scores.byId(id)?.let { it.name to "Score" }
        'd' -> Drugs.byId(id)?.let { it.name to "Drug · ${it.drugClass}" }
        else -> null
    }

    companion object {
        fun parse(s: String): PageKey? = s.split(":", limit = 2).takeIf { it.size == 2 && it[0].length == 1 }
            ?.let { PageKey(it[0][0], it[1]) }
        fun condition(id: String) = PageKey('c', id)
        fun protocol(id: String) = PageKey('p', id)
    }
}

/** Opens a page from anywhere (provided by App). */
val LocalOpenPage = staticCompositionLocalOf<(PageKey) -> Unit> { {} }

/** The page currently shown, for the pin button in its title (null on section screens). */
val LocalCurrentPage = compositionLocalOf<PageKey?> { null }

/** Recently opened and pinned pages. Stored on the device like a setting — page ids only, never patient data. */
object Library {
    private const val RECENT = "recent_pages"
    private const val PINNED = "pinned_pages"

    var recent by mutableStateOf(load(RECENT))
        private set
    var pinned by mutableStateOf(load(PINNED))
        private set

    private fun load(key: String): List<PageKey> =
        Prefs.getString(key, "").split("|").mapNotNull { PageKey.parse(it) }.filter { it.describe() != null }

    fun opened(page: PageKey) {
        recent = (listOf(page) + recent.filter { it != page }).take(8)
        Prefs.putString(RECENT, recent.joinToString("|"))
    }

    fun isPinned(page: PageKey) = page in pinned

    fun togglePin(page: PageKey) {
        pinned = if (page in pinned) pinned - page else pinned + page
        Prefs.putString(PINNED, pinned.joinToString("|"))
    }
}

/** Pages on the same subject in different sections (e.g. the dengue infection page and the severe dengue protocol). */
object Related {
    private fun c(vararg ids: String) = ids.map { PageKey.condition(it) }
    private fun p(vararg ids: String) = ids.map { PageKey.protocol(it) }

    internal val groups: List<List<PageKey>> = listOf(
        // Infections ↔ emergency protocols
        c("inf-dengue") + p("dengue-severe"),
        c("inf-malaria") + p("severe-malaria"),
        c("inf-bacterial-meningitis", "inf-japanese-encephalitis") + p("bacterial-meningitis", "status-epilepticus"),
        c("inf-rabies") + p("animal-bite-rabies"),
        c("inf-tetanus") + p("tetanus-prophylaxis"),
        c("inf-cap", "inf-uti", "inf-influenza") + p("sepsis"),
        c("uti", "inf-uti"),
        // Infections that are looked up together
        c("inf-scrub-typhus", "inf-leptospirosis", "inf-enteric-fever", "inf-malaria", "inf-dengue"),
        c("inf-dengue", "inf-chikungunya", "inf-zika"),
        c("inf-hepatitis-a-e", "inf-hepatitis-b", "inf-hepatitis-c"),
        c("inf-acute-diarrhoea", "inf-amoebiasis", "inf-giardiasis"),
        c("inf-ascariasis", "inf-hookworm"),
        c("inf-syphilis", "inf-gonorrhoea", "inf-hiv"),
        c("inf-measles", "inf-rubella", "inf-chickenpox"),
        // Chronic care ↔ emergencies
        c("nvp") + p("hyperemesis-gravidarum"),
        c("hypertension") + p("eclampsia", "severe-pre-eclampsia", "hypertensive-emergency"),
        c("hypertension-general") + p("hypertensive-emergency"),
        c("diabetes-general", "diabetes-pregnancy") + p("dka", "hhs", "hypoglycaemia"),
        c("asthma-general", "asthma-pregnancy") + p("acute-severe-asthma"),
        c("thyroid-general", "thyroid-pregnancy") + p("thyroid-storm", "myxoedema-coma"),
        c("heart-failure") + p("acute-heart-failure"),
        c("atrial-fibrillation") + p("tachycardia-with-pulse"),
        c("chronic-coronary-disease") + p("stemi", "nste-acs"),
        c("cocaine-use-disorder") + p("cocaine-toxicity"),
        c("headache") + p("migraine-acute", "subarachnoid-haemorrhage"),
        // Protocol families
        p("pph", "antepartum-haemorrhage", "maternal-collapse"),
        p("pph", "uterine-inversion", "retained-placenta"),
        p("cord-prolapse", "shoulder-dystocia", "neonatal-resuscitation"),
        p("antepartum-haemorrhage", "preterm-labour"),
        p("ectopic-pregnancy", "miscarriage-septic-abortion"),
        p("major-trauma", "traumatic-haemorrhage", "head-injury", "spinal-injury", "chest-trauma", "abdominal-pelvic-trauma", "trauma-in-pregnancy"),
        p("open-fractures", "compartment-syndrome-crush", "tetanus-prophylaxis"),
        p("cardiac-arrest", "traumatic-cardiac-arrest", "maternal-collapse"),
        p("burns", "electrical-injury"),
        p("snakebite", "scorpion-sting"),
        // Pregnancy and children ↔ adult protocols
        p("maternal-sepsis", "sepsis", "miscarriage-septic-abortion"),
        p("vte-in-pregnancy", "pulmonary-embolism"),
        c("diabetes-pregnancy") + p("dka-in-pregnancy", "dka", "paediatric-dka"),
        p("paediatric-arrest-seriously-ill-child", "neonatal-resuscitation", "cardiac-arrest"),
        p("paediatric-sepsis", "sepsis"),
        p("paediatric-status-epilepticus-febrile-seizures", "status-epilepticus"),
        p("acute-asthma-child", "bronchiolitis", "croup", "acute-severe-asthma"),
        c("inf-acute-diarrhoea") + p("dehydration-diarrhoea-child", "sam-with-complications"),
        c("inf-dengue", "inf-malaria") + p("anaphylaxis-dengue-malaria-child", "anaphylaxis", "dengue-severe", "severe-malaria"),
    ) + Protocols.all.filter { it.category == ProtocolCategory.Poisoning && it.id != "poisoning-general" }
        .map { p(it.id, "poisoning-general") }

    fun of(page: PageKey): List<PageKey> =
        groups.filter { page in it }.flatten().filter { it != page }.distinct().filter { it.describe() != null }
}

/** A card of tappable rows: title, one-line subtitle, chevron. */
@Composable
fun NavRows(rows: List<Triple<String, String, () -> Unit>>, modifier: Modifier = Modifier) {
    val c = Theme.colors
    Column(
        modifier.padding(horizontal = Gutter).fillMaxWidth().clip(Radius.card)
            .background(c.surface).border(1.dp, c.hairline, Radius.card),
    ) {
        rows.forEachIndexed { i, (title, subtitle, onClick) ->
            if (i > 0) Hairline(Modifier.padding(start = 18.dp))
            Row(
                Modifier.fillMaxWidth().clickable(onClick = onClick).padding(horizontal = 18.dp, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(Modifier.weight(1f)) {
                    Text(title, style = Theme.type.bodyStrong, color = c.text, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    Text(subtitle, style = Theme.type.caption, color = c.textSecondary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Spacer(Modifier.width(8.dp))
                Icon(Icons.Outlined.ChevronRight, null, tint = c.textTertiary, modifier = Modifier.size(20.dp))
            }
        }
    }
}

/** Rows for a list of page keys (Pinned, Recent, Related). */
@Composable
fun PageRows(pages: List<PageKey>, modifier: Modifier = Modifier) {
    val open = LocalOpenPage.current
    NavRows(pages.mapNotNull { k -> k.describe()?.let { (t, s) -> Triple(t, s) { open(k) } } }, modifier)
}

/** "Related" section at the foot of a condition or protocol page. */
@Composable
fun RelatedLinks(page: PageKey) {
    val related = Related.of(page)
    if (related.isEmpty()) return
    Column(Modifier.padding(top = 8.dp)) {
        SectionHeader("Related", Modifier.padding(horizontal = Gutter))
        PageRows(related)
    }
}
