package io.github.vasilyrylov.archsample

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.common.ui.theme.AppTheme
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent

@Composable
internal fun ComposeApp(rootFlowComponent: RootFlowComponent) {
    AppTheme {
        rootFlowComponent.Ui()
    }
}
