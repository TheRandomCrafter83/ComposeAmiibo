package com.example.composeamiibo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = ActionBarDark,
    primaryVariant = ActionBarDark,
    secondary = IconBackgroundDark,
    surface = CardColorDark,
    background = ListBackgroundDark,
    onPrimary = White
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = IconBackgroundLight,
    surface = CardColorLight,
    background = ListBackgroundLight

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ComposeAmiiboTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}