package com.medicalaid.app.ui.medicines

/**
 * Groups attributed statements ("ICMR: …", "NICE 1.3.3: …", "AHA/ACC 2025: …") by guideline body,
 * so a page that compares several guidelines can show one at a time. Statements without a
 * recognised prefix are always shown.
 */
enum class GuidelineGroup(val label: String, private val prefixes: List<String>) {
    India("India", listOf("India", "ICMR", "IHCI", "MoHFW", "CSI", "WHO India")),
    Nice("NICE", listOf("NICE")),
    AccAha("ACC/AHA", listOf("AHA", "ACC")),
    Esc("ESC", listOf("ESC", "EHRA")),
    Kdigo("KDIGO", listOf("KDIGO"));

    private fun matches(text: String): Boolean = prefixes.any { p ->
        text.startsWith(p) && (text.length == p.length || !text[p.length].isLetter())
    }

    companion object {
        fun of(text: String): GuidelineGroup? = entries.firstOrNull { it.matches(text.trimStart()) }

        /** Which guideline group a cited document belongs to (null if none). */
        fun ofSource(source: com.medicalaid.app.content.Source): GuidelineGroup? = when (source.authority) {
            com.medicalaid.app.content.Authority.MoHFW, com.medicalaid.app.content.Authority.ICMR -> India
            com.medicalaid.app.content.Authority.NICE -> Nice
            com.medicalaid.app.content.Authority.AHA -> AccAha
            com.medicalaid.app.content.Authority.KDIGO -> Kdigo
            com.medicalaid.app.content.Authority.ESC -> Esc
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

        /** True if [text] should show under [filter] (null = all guidelines). */
        fun shows(text: String, filter: GuidelineGroup?): Boolean {
            if (filter == null) return true
            val g = of(text) ?: return true
            return g == filter
        }
    }
}
