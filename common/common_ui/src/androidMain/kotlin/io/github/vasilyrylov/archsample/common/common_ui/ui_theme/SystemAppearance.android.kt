package io.github.vasilyrylov.archsample.common.common_ui.ui_theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

@Composable
internal actual fun SystemAppearance(useDarkTheme: Boolean) {
    val view = LocalView.current

    LaunchedEffect(useDarkTheme) {
        val window = (view.context as Activity).window

        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = !useDarkTheme
            isAppearanceLightNavigationBars = !useDarkTheme
        }
    }
}
