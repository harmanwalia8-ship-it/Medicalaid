package com.medicalaid.app.ui.medicines

/**
 * Groups attributed statements ("ICMR: …", "NICE 1.3.3: …", "AHA/ACC 2025: …") by guideline body,
 * so a page that compares several guidelines can show one at a time. Statements without a
 * recognised prefix are always shown.
 */
enum class GuidelineGroup(val label: String, private val prefixes: List<String>) {
    India(
        "India",
        listOf(
            "India", "ICMR", "IHCI", "MoHFW", "CSI", "WHO India", "FOGSI", "NCVBDC", "NCDC", "NPPMBI", "ISCCM",
            "Dte.GHS", "NDMA", "STEMI INDIA", "SBA", "Dakshata", "IPS", "API",
        ),
    ),
    Who("WHO", listOf("WHO")),
    Nice("NICE", listOf("NICE")),
    AccAha("ACC/AHA", listOf("AHA", "ACC", "ASA")),
    Esc("ESC", listOf("ESC", "EHRA", "ESO")),
    Erc("ERC / RCUK", listOf("ERC", "RCUK", "Resuscitation Council")),
    Kdigo("KDIGO", listOf("KDIGO")),
    Ada("ADA", listOf("ADA")),
    Jbds("JBDS", listOf("JBDS")),
    Gina("GINA", listOf("GINA")),
    Gold("GOLD", listOf("GOLD")),
    Bts("BTS", listOf("BTS")),
    Ssc("Surviving Sepsis", listOf("SSC", "Surviving Sepsis")),
    Acog("ACOG", listOf("ACOG")),
    Rcog("RCOG", listOf("RCOG")),
    Ish("ISH", listOf("ISH")),
    Escmid("ESCMID", listOf("ESCMID")),
    Idsa("IDSA", listOf("IDSA")),
    Aes("AES", listOf("AES")),
    Ahs("AHS", listOf("AHS")),
    East("EAST", listOf("EAST")),
    Fda("FDA label", listOf("FDA", "US label"));

    private fun matches(text: String): Boolean = prefixes.any { p ->
        text.startsWith(p) && (text.length == p.length || !text[p.length].isLetter())
    }

    companion object {
        fun of(text: String): GuidelineGroup? = entries.firstOrNull { it.matches(text.trimStart()) }

        /** Which guideline group a cited document belongs to (null if none). */
        fun ofSource(source: com.medicalaid.app.content.Source): GuidelineGroup? = when (source.authority) {
            com.medicalaid.app.content.Authority.MoHFW, com.medicalaid.app.content.Authority.ICMR -> India
            com.medicalaid.app.content.Authority.WHO -> Who
            com.medicalaid.app.content.Authority.NICE -> Nice
            com.medicalaid.app.content.Authority.AHA -> AccAha
            com.medicalaid.app.content.Authority.KDIGO -> Kdigo
            com.medicalaid.app.content.Authority.ESC, com.medicalaid.app.content.Authority.ESO -> Esc
            com.medicalaid.app.content.Authority.ERC, com.medicalaid.app.content.Authority.RCUK -> Erc
            com.medicalaid.app.content.Authority.ADA -> Ada
            com.medicalaid.app.content.Authority.JBDS -> Jbds
            com.medicalaid.app.content.Authority.GINA -> Gina
            com.medicalaid.app.content.Authority.GOLD -> Gold
            com.medicalaid.app.content.Authority.BTS -> Bts
            com.medicalaid.app.content.Authority.SSC -> Ssc
            com.medicalaid.app.content.Authority.ACOG -> Acog
            com.medicalaid.app.content.Authority.RCOG -> Rcog
            com.medicalaid.app.content.Authority.ESCMID -> Escmid
            com.medicalaid.app.content.Authority.IDSA -> Idsa
            com.medicalaid.app.content.Authority.AES -> Aes
            com.medicalaid.app.content.Authority.AHS -> Ahs
            com.medicalaid.app.content.Authority.EAST -> East
            com.medicalaid.app.content.Authority.FDA -> Fda
            else -> if (source.authority.india) India else of(source.document) ?: of(source.title)
        }

        /** Citations to show under [filter]; falls back to all so a block is never left unsourced. */
        fun filterCitations(
            citations: List<com.medicalaid.app.content.Citation>,
            filter: GuidelineGroup?,
        ): List<com.medicalaid.app.content.Citation> {
            if (filter == null) return citations
            val kept = citations.filter { ofSource(com.medicalaid.app.content.Sources.byId(it.sourceId)) == filter }
            return kept.ifEmpty { citations }
        }

        private val attribution = Regex("""^[A-Z][^:]{0,70}?:\s""")

        /** True if [text] opens with an attribution such as "ESC 2024: …" or "Eddleston et al. 2004: …". */
        fun attributed(text: String): Boolean = attribution.containsMatchIn(text.trimStart())

        /** True if [text] should show under [filter] (null = all guidelines). */
        fun shows(text: String, filter: GuidelineGroup?): Boolean {
            if (filter == null) return true
            val g = of(text) ?: return true
            return g == filter
        }
    }
}
