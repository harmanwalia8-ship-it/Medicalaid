package com.medicalaid.app

import androidx.compose.runtime.Composable

// iOS has no system back button; screens show an explicit back control instead.
@Composable
actual fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit) = Unit
