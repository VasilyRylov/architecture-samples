package io.github.vasilyrylov.archsample.common.common_ui.ui_theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
internal actual fun appColorScheme(useDarkTheme: Boolean): ColorScheme {
    return when {
        useDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
}
