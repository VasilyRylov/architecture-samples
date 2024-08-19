package io.github.vasilyrylov.archsample

import androidx.compose.ui.window.ComposeUIViewController
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent
import platform.UIKit.UIViewController

fun MainViewController(root: RootFlowComponent): UIViewController = ComposeUIViewController {
    ComposeApp(root)
}
