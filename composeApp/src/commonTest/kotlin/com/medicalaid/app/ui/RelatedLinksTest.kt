package com.medicalaid.app.ui

import kotlin.test.Test
import kotlin.test.assertTrue

class RelatedLinksTest {
    @Test
    fun everyRelatedPageExists() {
        Related.groups.flatten().forEach { assertTrue(it.describe() != null, "Related link to missing page $it") }
    }

    @Test
    fun pageKeysRoundTrip() {
        val k = PageKey('p', "pph")
        assertTrue(PageKey.parse(k.toString()) == k)
    }
}
