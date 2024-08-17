package io.github.vasilyrylov.archsample

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.root.root_component.RootFlowComponent
import io.github.vasilyrylov.archsample.feature.root.root_component.RootFlowScreenComponent
import io.github.vasilyrylov.archsample.theme.AppTheme

@Composable
internal fun App(rootFlowComponent: RootFlowComponent) = AppTheme {
        RootFlowScreenComponent(rootFlowComponent)
}
