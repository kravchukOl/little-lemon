package com.oleksiikravchuk.littlelemon.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    primary = darkGreen80,
    onPrimary = darkGreen20,
    primaryContainer = darkGreen33,
    onPrimaryContainer = darkGreen90,
    inversePrimary = darkGreen33,

    secondary = lightYellow80,
    onSecondary = lightYellow15,
    secondaryContainer = lightYellow30,

    tertiary = lightBronze80,
    onTertiary = lightBronze15,
    tertiaryContainer = lightBronze30,

    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,


    background = Grey10,
    onBackground = Grey90,

    surface = GreyGreen30,
    onSurface = GreyGreen90,
    inverseSurface = Grey90,
    inverseOnSurface = Grey10,
    surfaceVariant = GreyGreen30,
    onSurfaceVariant = GreyGreen80,

    outline = GreyGreen80
)

private val LightColorScheme = lightColorScheme(
    primary = darkGreen33,
    onPrimary = Color.White,
    primaryContainer = darkGreen90,
    onPrimaryContainer = darkGreen10,
    inversePrimary = darkGreen80,
    secondary = lightYellow52,
    onSecondary = lightYellow90,
    secondaryContainer = lightYellow30,
    onSecondaryContainer = lightYellow90,
    tertiary = lightBronze69,
    onTertiary = lightBronze15,
    tertiaryContainer = lightBronze90,
    onTertiaryContainer = lightBronze15,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = GreyGreen90,
    onSurface = GreyGreen30,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = GreyGreen90,
    onSurfaceVariant = GreyGreen30,
    outline = GreyGreen40
)

@Composable
fun LittleLemonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}