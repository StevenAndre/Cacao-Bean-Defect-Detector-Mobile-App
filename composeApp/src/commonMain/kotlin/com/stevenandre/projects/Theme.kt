package com.stevenandre.projects

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


object CacaoColors {


    val Brown900 = Color( 145, 77, 74)
    val Brown700 = Color(145, 70, 70)
    val Brown500 = Color(120, 70, 70)
    val Beige = Color(255, 252, 252)
    val GreenLight = Color(0xFFB4D4A1)

    val Green = Color(30, 89, 46)
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