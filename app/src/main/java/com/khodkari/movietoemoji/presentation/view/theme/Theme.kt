package com.khodkari.movietoemoji.presentation.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = White,
    secondary = Gray,
    onSecondary = White,
    onPrimary = Helio,
    surface = Black85 ,
    onSurface = White ,
)

private val LightColorPalette = lightColors(
    primary = White,
    secondary = Gray,
    onSecondary = White,
    onPrimary = Helio,
    surface = Black85 ,
    onSurface = White ,
)

@Composable
fun MovieToEmojiTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}