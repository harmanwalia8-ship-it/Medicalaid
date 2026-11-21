package com.medicalaid.app.content.protocols.data

import com.medicalaid.app.content.Authority
import com.medicalaid.app.content.Source

/**
 * Sources for the burns and acid–base calculators (cited directly by the UI).
 * DRAFT — to be verified and completed by the emergency-content review (ids must stay stable).
 */
internal val toolSources = listOf(
    Source(
        "tool-lund-browder", Authority.Paper, "Surg Gynecol Obstet 1944;79:352–358",
        "The estimation of areas of burns (Lund & Browder)", "1944",
        "https://www.ncbi.nlm.nih.gov/search/all/?term=Lund+Browder+estimation+of+areas+of+burns",
        status = "Draft — pending verification",
    ),
    Source(
        "tool-burns-fluids", Authority.Paper, "American Burn Association practice guideline",
        "Burn shock resuscitation (ABA practice guidelines)", "2008",
        "https://ameriburn.org/",
        status = "Draft — pending verification",
    ),
    Source(
        "tool-acid-base", Authority.Paper, "N Engl J Med 2014;371:1434–1445",
        "Physiological approach to assessment of acid–base disturbances (Berend, de Vries, Gans)", "2014",
        "https://doi.org/10.1056/NEJMra1003327",
        status = "Draft — pending verification",
    ),
)
