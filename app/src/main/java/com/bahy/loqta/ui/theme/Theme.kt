package com.bahy.loqta.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = LoqtaGreen,
    onPrimary = Color.White,
    primaryContainer = LoqtaGreenLight,
    onPrimaryContainer = LoqtaGreenDark,
    secondary = LoqtaTeal,
    onSecondary = Color.White,
    tertiary = LoqtaOrange,
    onTertiary = Color.White,
    background = LoqtaBackground,
    onBackground = LoqtaTextPrimary,
    surface = LoqtaSurface,
    onSurface = LoqtaTextPrimary,
    surfaceVariant = Color(0xFFF0F2F5),
    onSurfaceVariant = LoqtaTextSecondary,
    error = LoqtaError,
    onError = Color.White,
)

private val DarkColorScheme = darkColorScheme(
    primary = LoqtaGreenLight,
    onPrimary = LoqtaCharcoal,
    primaryContainer = LoqtaGreenDark,
    onPrimaryContainer = Color.White,
    secondary = LoqtaTeal,
    onSecondary = Color.White,
    tertiary = LoqtaOrange,
    onTertiary = Color.White,
    background = LoqtaCharcoal,
    onBackground = Color.White,
    surface = Color(0xFF243238),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF2C3E44),
    onSurfaceVariant = LoqtaTextSecondary,
    error = LoqtaError,
    onError = Color.White,
)

@Composable
fun LoqtaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
