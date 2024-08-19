package io.github.vasilyrylov.archsample.feature.auth.component

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.auth.ui.AuthFlowScreenContainer


@Composable
fun AuthFlowScreenComponent(authComponent: AuthComponent) {
    AuthFlowScreenContainer(authComponent.viewModel)
}