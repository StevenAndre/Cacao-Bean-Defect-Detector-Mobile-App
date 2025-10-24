package com.stevenandre.projects

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


object CacaoColors {
    val Brown900 = Color(0xFF3D2817)
    val Brown700 = Color(0xFF5C4033)
    val Brown500 = Color(0xFF8B6F47)
    val Beige = Color(0xFFF5E6D3)
    val GreenLight = Color(0xFFB4D4A1)
    val GreenDark = Color(0xFF7A9B6C)
}

private val CacaoColorScheme = darkColorScheme(
    primary = CacaoColors.Brown700,
    secondary = CacaoColors.GreenLight,
    background = CacaoColors.Beige,
    surface = CacaoColors.Brown700,
    onPrimary = CacaoColors.Beige,
    onSecondary = CacaoColors.Brown900,
    onBackground = CacaoColors.Brown900,
    onSurface = CacaoColors.GreenLight
)

@Composable
fun CacaoAITheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CacaoColorScheme,
        content = content
    )
}