package io.github.vasilyrylov.archsample.feature.auth.auth_ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.LoginScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.RegistrationScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen.LoginScreen
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen.RegistrationScreen

@Preview
@Composable
private fun RegistrationScreenPreview() {

    RegistrationScreen(
        data = RegistrationScreenData(
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
        data = LoginScreenData(
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