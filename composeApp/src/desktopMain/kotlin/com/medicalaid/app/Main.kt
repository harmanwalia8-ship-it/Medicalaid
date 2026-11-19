package com.medicalaid.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
actual fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit) = Unit

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Medicalaid",
        state = rememberWindowState(width = 420.dp, height = 880.dp),
    ) { App() }
}
