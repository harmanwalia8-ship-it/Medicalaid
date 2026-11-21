package com.medicalaid.app.content

/**
 * Content model. Every clinical statement in the app must carry at least one [Citation]
 * that resolves to a [Source] in [Sources]. Tests enforce this (see ContentIntegrityTest).
 */

enum class Authority(val short: String, val india: Boolean = false) {
    MoHFW("MoHFW India", india = true),
    ICMR("ICMR India", india = true),
    ACOG("ACOG"),
    CDC("CDC"),
    SMFM("SMFM"),
    USPSTF("USPSTF"),
    FDA("US FDA"),
    IDSA("IDSA"),
    ADA("ADA"),
    ATA("ATA"),
    GINA("GINA"),
    NHLBI("NHLBI (NAEPP)"),
    KDIGO("KDIGO"),
    NKF("NKF"),
    Paper("Original paper"),
    SSC("Surviving Sepsis Campaign"),
    AHA("AHA"),
    ESO("ESO"),
    NICE("NICE"),
    RCOG("RCOG"),
    WHO("WHO"),
    ESC("ESC"),
    ERC("ERC"),
    RCUK("Resuscitation Council UK"),
    JBDS("JBDS-IP"),
    EndoSoc("Endocrine Society"),
    ESE("ESE"),
    GOLD("GOLD"),
    BTS("BTS"),
    AES("AES"),
    ILAE("ILAE"),
    AHS("AHS"),
    ESCMID("ESCMID"),
    ACG("ACG"),
    WAO("WAO"),
    ABA("ABA"),
    ISBI("ISBI"),
    WMS("WMS"),
    UKKA("UKKA"),
    ACEP("ACEP"),
    NDMA("NDMA India", india = true),
    ISCCM("ISCCM India", india = true),
    CSI("CSI India", india = true),
    FOGSI("FOGSI India", india = true),
    FIGO("FIGO"),
    SOGC("SOGC"),
    BOA("BOA"),
    BTF("Brain Trauma Foundation"),
    ACSCOT("ACS COT"),
    WSES("WSES"),
    EAST("EAST"),
    JTS("Joint Trauma System"),
}

/** Which patients a page is written for. */
enum class Population(val label: String) {
    Pregnancy("In pregnancy"),
    General("General adult"),
}

/** Interactive calculators that can be attached to a condition page. */
enum class ToolKind {
    /** Estimated HbA1c from a glucose value (ADAG equation) plus HbA1c interpretation. */
    Glycemia,
    /** TSH interpretation against MoHFW trimester-specific ranges. */
    TshPregnancy,
    /** TSH interpretation for non-pregnant adults. */
    TshGeneral,
    /** eGFR (CKD-EPI 2021), non-indexed eGFR and Cockcroft–Gault CrCl. */
    Renal,
    /** Burns chart (Lund–Browder) and fluid plan. */
    Burns,
    /** ABG interpreter. */
    AcidBase,
}

/** A titled block of guideline statements (diagnostic criteria, targets, thresholds…). */
data class InfoBlock(val title: String, val points: List<String>, val citations: List<Citation>)

data class Source(
    val id: String,
    val authority: Authority,
    /** Document type and number, e.g. "Practice Bulletin No. 189". */
    val document: String,
    val title: String,
    val year: String,
    val url: String,
    /** Status note, e.g. "Reaffirmed 2023". */
    val status: String? = null,
)

/** A pointer to a source, optionally to a specific table/section so clinicians can find the text. */
data class Citation(val sourceId: String, val locator: String? = null)

fun cite(sourceId: String, locator: String? = null) = Citation(sourceId, locator)

// ---------------------------------------------------------------------------------------------
// Medicines
// ---------------------------------------------------------------------------------------------

data class Condition(
    val id: String,
    val title: String,
    /** One line shown on the category list. */
    val summary: String,
    /** Synonyms and lay terms used by search (lower-case not required). */
    val keywords: List<String>,
    val population: Population = Population.Pregnancy,
    val tool: ToolKind? = null,
    /** Non-drug first steps. */
    val firstSteps: List<String> = emptyList(),
    /** Criteria, targets and thresholds shown before the treatment steps. */
    val info: List<InfoBlock> = emptyList(),
    val tiers: List<Tier>,
    val avoid: List<Caution> = emptyList(),
    /** Red flags / when to escalate or refer. */
    val escalate: List<String> = emptyList(),
    val citations: List<Citation>,
)

/** A step of treatment, e.g. "First line" or "If dehydrated". */
data class Tier(val label: String, val note: String? = null, val drugs: List<Drug>)

data class Drug(
    val name: String,
    /** Dose, route, frequency, as written in the source. */
    val regimen: String,
    val maxDose: String? = null,
    val weightDose: WeightDose? = null,
    val cautions: List<String> = emptyList(),
    val citations: List<Citation>,
)

/**
 * Weight-based dosing. The app computes a total daily range for the weight the clinician enters;
 * it never rounds to a product strength — that decision stays with the prescriber.
 */
data class WeightDose(
    val minPerKg: Double,
    val maxPerKg: Double,
    /** Dose unit, e.g. "mg" or "mcg". */
    val unit: String = "mg",
    /** e.g. "per day, in 2–3 divided doses". */
    val per: String,
    /** Upper limit if the source gives one, per kg (e.g. titrate to max 21 mg/kg/day). */
    val ceilingPerKg: Double? = null,
)

data class Caution(val name: String, val reason: String, val citations: List<Citation>)

// ---------------------------------------------------------------------------------------------
// Antenatal schedule
// ---------------------------------------------------------------------------------------------

enum class ItemKind(val label: String) {
    Test("Test"),
    Scan("Scan"),
    Vaccine("Vaccine"),
    Supplement("Supplement"),
    Visit("Visit"),
}

/** Gestational-age window in completed days (inclusive). */
data class GaWindow(val startDays: Int, val endDays: Int, val label: String) {
    companion object {
        /** Window from [fromWeeks]+[fromDays] up to and including [toWeeks]+[toDays]. */
        fun weeks(fromWeeks: Int, toWeeks: Int, toDays: Int = 6, fromDays: Int = 0, label: String) =
            GaWindow(fromWeeks * 7 + fromDays, toWeeks * 7 + toDays, label)
    }
}

data class ScheduleItem(
    val id: String,
    val title: String,
    val kind: ItemKind,
    val authority: Authority,
    /** Null means "at the first antenatal contact", independent of gestational age. */
    val window: GaWindow?,
    val detail: String,
    /** Applies only to some women, e.g. "If Rh(D) negative". */
    val appliesTo: String? = null,
    val citations: List<Citation>,
)
