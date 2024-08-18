package io.github.vasilyrylov.archsample.common.common_ui.ui_theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }

    CompositionLocalProvider(LocalThemeIsDark provides isDarkState) {
        val isDark by isDarkState

        SystemAppearance(!isDark)

        MaterialTheme(
            colorScheme = if (isDark) DarkColorScheme else LightColorScheme,
            content = { Surface(content = content) }
        )
    }
}

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }
