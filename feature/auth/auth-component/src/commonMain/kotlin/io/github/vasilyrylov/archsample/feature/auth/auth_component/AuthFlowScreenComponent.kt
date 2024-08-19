package io.github.vasilyrylov.archsample.feature.auth.auth_component

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.auth.AuthFlowScreenContainer


@Composable
fun AuthFlowScreenComponent(authComponent: AuthComponent) {
    AuthFlowScreenContainer(authComponent.viewModel)
}