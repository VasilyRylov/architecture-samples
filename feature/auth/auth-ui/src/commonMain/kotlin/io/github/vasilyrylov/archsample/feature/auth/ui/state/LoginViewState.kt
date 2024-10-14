package io.github.vasilyrylov.archsample.feature.auth.ui.state

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.ui.screen.LoginScreen

internal data class LoginViewState(
    val name: String,
    val password: String,
    val errorMessage: String?,
    val isAuthenticationInProgress: Boolean,
    val snackBarMessage: String?
) : AuthViewState {

    @Composable
    override fun Ui(authFeature: AuthFeature) = LoginScreen(
        viewState = this,
        onChangeLoginData = authFeature::handleChangeLoginData,
        startAuthenticating = authFeature::startAuthenticating,
        toRegistration = authFeature::toRegistration
    )
}
