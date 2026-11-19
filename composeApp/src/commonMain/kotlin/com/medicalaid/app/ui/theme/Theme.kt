package com.medicalaid.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.medicalaid.app.resources.Res
import com.medicalaid.app.resources.inter_display_medium
import com.medicalaid.app.resources.inter_display_semibold
import com.medicalaid.app.resources.inter_medium
import com.medicalaid.app.resources.inter_regular
import com.medicalaid.app.resources.inter_semibold
import org.jetbrains.compose.resources.Font

/** The full palette. One accent; everything else is neutral. */
@Immutable
data class AppColors(
    val background: Color,
    val surface: Color,
    val surfaceMuted: Color,
    val hairline: Color,
    val text: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val accent: Color,
    val accentSoft: Color,
    val onAccent: Color,
    val positive: Color,
    val positiveSoft: Color,
    val caution: Color,
    val cautionSoft: Color,
    val danger: Color,
    val dangerSoft: Color,
    val isDark: Boolean,
)

private val Light = AppColors(
    background = Color(0xFFF7F7F5),
    surface = Color(0xFFFFFFFF),
    surfaceMuted = Color(0xFFF0F0ED),
    hairline = Color(0xFFE7E6E2),
    text = Color(0xFF141417),
    textSecondary = Color(0xFF66666E),
    textTertiary = Color(0xFF9B9BA3),
    accent = Color(0xFF3F47CC),
    accentSoft = Color(0xFFEDEEFC),
    onAccent = Color(0xFFFFFFFF),
    positive = Color(0xFF16805A),
    positiveSoft = Color(0xFFE6F4EE),
    caution = Color(0xFF9A5B00),
    cautionSoft = Color(0xFFFFF3DD),
    danger = Color(0xFFB4322B),
    dangerSoft = Color(0xFFFCEBE9),
    isDark = false,
)

private val Dark = AppColors(
    background = Color(0xFF0E0E11),
    surface = Color(0xFF17171B),
    surfaceMuted = Color(0xFF202026),
    hairline = Color(0xFF2A2A31),
    text = Color(0xFFF2F2F4),
    textSecondary = Color(0xFFA3A3AC),
    textTertiary = Color(0xFF6E6E78),
    accent = Color(0xFF9198F5),
    accentSoft = Color(0xFF22244A),
    onAccent = Color(0xFF0E0E11),
    positive = Color(0xFF4CC394),
    positiveSoft = Color(0xFF15302A),
    caution = Color(0xFFE5A54B),
    cautionSoft = Color(0xFF33271A),
    danger = Color(0xFFF08A82),
    dangerSoft = Color(0xFF3A1F1E),
    isDark = true,
)

@Immutable
data class AppType(
    val display: TextStyle,
    val title: TextStyle,
    val headline: TextStyle,
    val body: TextStyle,
    val bodyStrong: TextStyle,
    val label: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle,
)

val LocalAppColors = staticCompositionLocalOf { Light }
val LocalAppType = staticCompositionLocalOf<AppType> { error("AppType not provided") }

object Theme {
    val colors: AppColors @Composable get() = LocalAppColors.current
    val type: AppType @Composable get() = LocalAppType.current
}

object Radius {
    val card = RoundedCornerShape(20.dp)
    val inner = RoundedCornerShape(14.dp)
    val pill = RoundedCornerShape(50)
}

@Composable
private fun appType(): AppType {
    val inter = FontFamily(
        Font(Res.font.inter_regular, FontWeight.Normal),
        Font(Res.font.inter_medium, FontWeight.Medium),
        Font(Res.font.inter_semibold, FontWeight.SemiBold),
    )
    val display = FontFamily(
        Font(Res.font.inter_display_medium, FontWeight.Medium),
        Font(Res.font.inter_display_semibold, FontWeight.SemiBold),
    )
    return AppType(
        display = TextStyle(fontFamily = display, fontWeight = FontWeight.SemiBold, fontSize = 52.sp, lineHeight = 56.sp, letterSpacing = (-1.6).sp),
        title = TextStyle(fontFamily = display, fontWeight = FontWeight.SemiBold, fontSize = 30.sp, lineHeight = 36.sp, letterSpacing = (-0.7).sp),
        headline = TextStyle(fontFamily = display, fontWeight = FontWeight.SemiBold, fontSize = 19.sp, lineHeight = 25.sp, letterSpacing = (-0.25).sp),
        body = TextStyle(fontFamily = inter, fontWeight = FontWeight.Normal, fontSize = 15.sp, lineHeight = 22.sp, letterSpacing = (-0.1).sp),
        bodyStrong = TextStyle(fontFamily = inter, fontWeight = FontWeight.Medium, fontSize = 15.sp, lineHeight = 22.sp, letterSpacing = (-0.15).sp),
        label = TextStyle(fontFamily = inter, fontWeight = FontWeight.Medium, fontSize = 13.sp, lineHeight = 18.sp, letterSpacing = 0.sp),
        caption = TextStyle(fontFamily = inter, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 17.sp, letterSpacing = 0.sp),
        overline = TextStyle(fontFamily = inter, fontWeight = FontWeight.SemiBold, fontSize = 11.sp, lineHeight = 14.sp, letterSpacing = 1.1.sp),
    )
}

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) Dark else Light
    val type = appType()
    val scheme = if (darkTheme) {
        darkColorScheme(
            primary = colors.accent, onPrimary = colors.onAccent,
            primaryContainer = colors.accentSoft, onPrimaryContainer = colors.text,
            background = colors.background, onBackground = colors.text,
            surface = colors.surface, onSurface = colors.text,
            surfaceVariant = colors.surfaceMuted, onSurfaceVariant = colors.textSecondary,
            surfaceContainerHigh = colors.surface, surfaceContainerHighest = colors.surfaceMuted,
            outline = colors.hairline, outlineVariant = colors.hairline,
            error = colors.danger,
        )
    } else {
        lightColorScheme(
            primary = colors.accent, onPrimary = colors.onAccent,
            primaryContainer = colors.accentSoft, onPrimaryContainer = colors.text,
            background = colors.background, onBackground = colors.text,
            surface = colors.surface, onSurface = colors.text,
            surfaceVariant = colors.surfaceMuted, onSurfaceVariant = colors.textSecondary,
            surfaceContainerHigh = colors.surface, surfaceContainerHighest = colors.surfaceMuted,
            outline = colors.hairline, outlineVariant = colors.hairline,
            error = colors.danger,
        )
    }
    val typography = Typography(
        bodyLarge = type.body, bodyMedium = type.body, bodySmall = type.caption,
        labelLarge = type.label, labelMedium = type.label, labelSmall = type.caption,
        titleLarge = type.headline, titleMedium = type.bodyStrong, titleSmall = type.label,
        headlineLarge = type.title, headlineMedium = type.title, headlineSmall = type.headline,
        displayLarge = type.display, displayMedium = type.display, displaySmall = type.title,
    )
    CompositionLocalProvider(LocalAppColors provides colors, LocalAppType provides type) {
        MaterialTheme(
            colorScheme = scheme,
            typography = typography,
            shapes = Shapes(
                small = RoundedCornerShape(10.dp),
                medium = RoundedCornerShape(14.dp),
                large = RoundedCornerShape(20.dp),
                extraLarge = RoundedCornerShape(28.dp),
            ),
            content = content,
        )
    }
}
