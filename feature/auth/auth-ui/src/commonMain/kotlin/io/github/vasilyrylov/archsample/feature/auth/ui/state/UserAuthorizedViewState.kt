package io.github.vasilyrylov.archsample.feature.auth.ui.state

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature

internal data class UserAuthorizedViewState(
    val name: String,
) : AuthViewState {

    @Composable
    override fun Ui(authFeature: AuthFeature) = Unit
}
