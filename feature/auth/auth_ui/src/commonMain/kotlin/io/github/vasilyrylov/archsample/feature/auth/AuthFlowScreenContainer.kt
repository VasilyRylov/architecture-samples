package io.github.vasilyrylov.archsample.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.AuthViewModel
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.LoginScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.RegistrationScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.UserAuthorizedScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen.LoginScreen
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen.RegistrationScreen
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen.UserAuthorizedScreen

@Composable
fun AuthFlowScreenContainer(authViewModel: AuthViewModel) {

    val state by authViewModel.state.collectAsState()

    when (val data = state) {
        is LoginScreenData -> LoginScreen(
            data = data,
            onChangeLoginData = authViewModel::handleChangeLoginData,
            startAuthenticating = authViewModel::startAuthenticating,
            toRegistration = authViewModel::toRegistration
        )

        is RegistrationScreenData -> RegistrationScreen(
            data = data,
            onBackClick = authViewModel::toLogin,
            handleChangeRegistrationData = authViewModel::handleChangeRegistrationData,
            startRegistration = authViewModel::startRegistration,
            declineRegistrationData = authViewModel::declineRegistrationData,
            confirmRegistrationData = authViewModel::confirmRegistrationData
        )

        is UserAuthorizedScreenData -> UserAuthorizedScreen(
            data = data,
            logout = authViewModel::logout
        )
    }
}
