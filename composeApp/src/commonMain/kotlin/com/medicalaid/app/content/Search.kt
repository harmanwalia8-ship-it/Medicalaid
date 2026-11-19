package com.medicalaid.app.content

data class SearchHit(val condition: Condition, val drug: Drug? = null)

/**
 * Simple offline search over conditions, their synonyms and drug names.
 * Every word of the query must match somewhere (prefix match on any word of the haystack).
 */
object Search {
    private fun normalize(s: String) = s.lowercase()
        .replace('-', ' ')
        .replace('/', ' ')
        .replace(Regex("[^a-z0-9 ]"), " ")

    private fun words(s: String) = normalize(s).split(' ').filter { it.isNotBlank() }

    private fun matchesAll(tokens: List<String>, haystack: List<String>) =
        tokens.all { t -> haystack.any { it.startsWith(t) } }

    fun run(query: String, conditions: List<Condition> = Conditions.all): List<SearchHit> {
        val tokens = words(query)
        if (tokens.isEmpty()) return emptyList()

        val conditionHits = conditions.mapNotNull { c ->
            val titleWords = words(c.title)
            val keywordWords = c.keywords.flatMap(::words)
            val score = when {
                matchesAll(tokens, titleWords) -> 0
                matchesAll(tokens, titleWords + keywordWords) -> 1
                matchesAll(tokens, titleWords + keywordWords + words(c.summary)) -> 2
                else -> return@mapNotNull null
            }
            score to SearchHit(c)
        }.sortedBy { it.first }.map { it.second }

        val drugHits = conditions.flatMap { c ->
            c.tiers.flatMap { it.drugs }
                .filter { matchesAll(tokens, words(it.name)) }
                .distinctBy { it.name }
                .map { SearchHit(c, it) }
        }
        return conditionHits + drugHits
    }
}
