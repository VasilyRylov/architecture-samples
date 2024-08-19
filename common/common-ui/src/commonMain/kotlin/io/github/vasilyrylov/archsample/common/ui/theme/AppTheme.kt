package io.github.vasilyrylov.archsample.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    SystemAppearance(useDarkTheme = useDarkTheme)

    val colorScheme = appColorScheme(useDarkTheme = useDarkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
