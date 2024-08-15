package io.github.vasilyrylov.archsample

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.root.root_component.RootComponent
import io.github.vasilyrylov.archsample.feature.root.root_component.RootFlowContainer
import io.github.vasilyrylov.archsample.theme.AppTheme

@Composable
internal fun App(rootComponent: RootComponent) = AppTheme {
        RootFlowContainer(rootComponent)
}
