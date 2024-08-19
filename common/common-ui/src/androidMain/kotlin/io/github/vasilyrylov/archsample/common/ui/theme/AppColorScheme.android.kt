package io.github.vasilyrylov.archsample.common.ui.theme

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun appColorScheme(useDarkTheme: Boolean): ColorScheme {
    val context = LocalContext.current

    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (useDarkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }
        useDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
}
