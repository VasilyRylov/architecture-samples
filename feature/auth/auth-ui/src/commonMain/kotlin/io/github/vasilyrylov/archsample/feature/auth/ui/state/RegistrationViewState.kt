package io.github.vasilyrylov.archsample.feature.auth.ui.state

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.ui.screen.RegistrationScreen

internal data class RegistrationViewState(
    val name: String,
    val password: String,
    val repeatedPassword: String,
    val errorMessage: String?,
    val isRegistrationInProgress: Boolean,
    val isConfirmationRequested: Boolean,
) : AuthViewState {

    @Composable
    override fun Ui(authFeature: AuthFeature) = RegistrationScreen(
        viewState = this,
        onBackClick = authFeature::toLogin,
        handleChangeRegistrationData = authFeature::handleChangeRegistrationData,
        startRegistration = authFeature::startRegistration,
        declineRegistrationData = authFeature::declineRegistrationData,
        confirmRegistrationData = authFeature::confirmRegistrationData
    )
}
