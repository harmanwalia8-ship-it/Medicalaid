package com.medicalaid.app.content.drugs

/**
 * References we may link to but not copy (commercial or restricted), plus free government resources.
 * Each opens the publisher's own website in the browser.
 */
data class ExternalRef(val name: String, val note: String, val url: String)

object ExternalRefs {
    private fun encode(s: String): String {
        val plain = s.replace('–', ' ').replace('—', ' ').trim()
        return buildString {
            plain.encodeToByteArray().forEach { b ->
                val c = b.toInt() and 0xFF
                when {
                    c in 'a'.code..'z'.code || c in 'A'.code..'Z'.code || c in '0'.code..'9'.code -> append(c.toChar())
                    c == ' '.code -> append('+')
                    c == '-'.code || c == '.'.code || c == '_'.code -> append(c.toChar())
                    else -> append('%').append(c.toString(16).uppercase().padStart(2, '0'))
                }
            }
        }
    }

    fun forDrug(drug: DrugMonograph): List<ExternalRef> {
        // Search with the most widely used international name (first alias if it is the US name).
        val q = encode(drug.name)
        val us = encode(drug.aliases.firstOrNull() ?: drug.name)
        return listOf(
            ExternalRef("DailyMed", "All US labels for this drug · NIH", "https://dailymed.nlm.nih.gov/dailymed/search.cfm?query=$us"),
            ExternalRef("LiverTox", "Liver injury · NIH", "https://www.ncbi.nlm.nih.gov/books/?term=$us+livertox"),
            ExternalRef("LactMed", "Breastfeeding · NIH", "https://www.ncbi.nlm.nih.gov/books/?term=$us+lactmed"),
            ExternalRef("BNF", "UK formulary · NICE", "https://bnf.nice.org.uk/search/?q=$q"),
            ExternalRef("Medscape", "Drug reference", "https://search.medscape.com/search/?q=$us"),
            ExternalRef("Drugs.com", "Drug reference", "https://www.drugs.com/search.php?searchterm=$us"),
            ExternalRef("UpToDate", "Subscription", "https://www.uptodate.com/contents/search?search=$us"),
        )
    }
}
