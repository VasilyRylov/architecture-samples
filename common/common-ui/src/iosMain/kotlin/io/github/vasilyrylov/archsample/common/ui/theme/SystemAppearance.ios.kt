package io.github.vasilyrylov.archsample.common.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarStyleDarkContent
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.setStatusBarStyle

@Composable
internal actual fun SystemAppearance(useDarkTheme: Boolean) {
    LaunchedEffect(useDarkTheme) {
        UIApplication.sharedApplication.setStatusBarStyle(
            if (useDarkTheme) {
                UIStatusBarStyleDarkContent
            } else {
                UIStatusBarStyleLightContent
            }
        )
    }
}
