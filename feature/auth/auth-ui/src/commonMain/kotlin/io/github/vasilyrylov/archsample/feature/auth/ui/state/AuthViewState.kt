package io.github.vasilyrylov.archsample.feature.auth.ui.state

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature

sealed interface AuthViewState {

    @Composable
    fun Ui(authFeature: AuthFeature)
}
