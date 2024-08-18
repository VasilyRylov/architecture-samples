package io.github.vasilyrylov.archsample

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.common.common_ui.ui_theme.AppTheme
import io.github.vasilyrylov.archsample.feature.root.root_component.RootFlowComponent
import io.github.vasilyrylov.archsample.feature.root.root_component.RootFlowScreenComponent

@Composable
internal fun ComposeApp(rootFlowComponent: RootFlowComponent) {
    AppTheme {
        RootFlowScreenComponent(rootFlowComponent)
    }
}
