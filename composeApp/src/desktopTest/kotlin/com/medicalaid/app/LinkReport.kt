package com.medicalaid.app

import com.medicalaid.app.content.Conditions
import com.medicalaid.app.content.drugs.Drugs
import kotlin.test.Test

/** Dev report (with -Pscreenshots): which condition drug lines link to which drug profile. */
class LinkReport {
    @Test
    fun report() {
        if (System.getProperty("screenshots.enabled") != "true") return
        val out = java.io.File(System.getProperty("screenshots.dir"), "links.txt")
        out.writeText(
            Conditions.all.flatMap { c -> c.tiers.flatMap { t -> t.drugs.map { c.id to it.name } } }
                .distinct()
                .joinToString("\n") { (c, n) -> "${Drugs.forConditionDrug(n)?.id ?: "-"}\t$c\t$n" },
        )
    }
}
