package io.github.vasilyrylov.archsample.feature.auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vasilyrylov.archsample.feature.auth.ui.screen.LoginScreen
import io.github.vasilyrylov.archsample.feature.auth.ui.screen.RegistrationScreen
import io.github.vasilyrylov.archsample.feature.auth.ui.state.LoginViewState
import io.github.vasilyrylov.archsample.feature.auth.ui.state.RegistrationViewState

@Preview
@Composable
private fun RegistrationScreenPreview() {

    RegistrationScreen(
        viewState = RegistrationViewState(
            name = "",
            password = "",
            repeatedPassword = "",
            errorMessage = null,
            isRegistrationInProgress = false,
            isConfirmationRequested = false,
        ),
        onBackClick = { },
        handleChangeRegistrationData = { _, _, _ -> },
        startRegistration = { },
        declineRegistrationData = { }) {
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {

    LoginScreen(
        viewState = LoginViewState(
            name = "",
            password = "",
            errorMessage = null,
            isAuthenticationInProgress = false,
            snackBarMessage = null
        ),
        onChangeLoginData = { _, _ -> },
        startAuthenticating = {},
        toRegistration = {}
    )
}
