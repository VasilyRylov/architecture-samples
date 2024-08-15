package io.github.vasilyrylov.archsample.feature.auth.auth_component

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.auth.AuthFlowScreen


@Composable
fun AuthFlowContainer(authComponent: AuthComponent) {
    AuthFlowScreen(authComponent.viewModel)
}