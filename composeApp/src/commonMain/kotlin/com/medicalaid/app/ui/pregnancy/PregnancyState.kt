package com.medicalaid.app.ui.pregnancy

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.medicalaid.app.pregnancy.Dating
import com.medicalaid.app.pregnancy.DatingOutcome
import com.medicalaid.app.pregnancy.GestationalAge
import com.medicalaid.app.pregnancy.UltrasoundScan
import kotlinx.datetime.LocalDate

enum class GuidelineFilter(val label: String) { All("All"), India("India"), International("ACOG / CDC") }

/**
 * Inputs for the dating screen. Held in memory only — nothing is written to disk,
 * so no patient data persists after the app is closed.
 */
@Stable
class PregnancyState {
    var lmp by mutableStateOf<LocalDate?>(null)
    var useScan by mutableStateOf(false)
    var scanDate by mutableStateOf<LocalDate?>(null)
    var scanWeeks by mutableStateOf(12)
    var scanDays by mutableStateOf(0)
    var filter by mutableStateOf(GuidelineFilter.All)
    var showPast by mutableStateOf(false)

    fun outcome(today: LocalDate): DatingOutcome {
        val scan = if (useScan) scanDate?.let { UltrasoundScan(it, GestationalAge.of(scanWeeks, scanDays)) } else null
        return Dating.calculate(lmp, scan, today)
    }

    fun reset() {
        lmp = null
        useScan = false
        scanDate = null
        scanWeeks = 12
        scanDays = 0
        showPast = false
    }
}
