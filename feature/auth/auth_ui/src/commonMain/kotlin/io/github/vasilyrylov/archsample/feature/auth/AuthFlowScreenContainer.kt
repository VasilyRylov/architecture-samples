package io.github.vasilyrylov.archsample.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.AuthViewModel
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.LoginViewState
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.RegistrationViewState
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.UserAuthorizedViewState
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen.LoginScreen
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.screen.RegistrationScreen

@Composable
fun AuthFlowScreenContainer(viewModel: AuthViewModel) {

    val viewState by viewModel.state.collectAsState()

    when (val authViewState = viewState) {
        is LoginViewState -> LoginScreen(
            viewState = authViewState,
            onChangeLoginData = viewModel::handleChangeLoginData,
            startAuthenticating = viewModel::startAuthenticating,
            toRegistration = viewModel::toRegistration
        )

        is RegistrationViewState -> RegistrationScreen(
            viewState = authViewState,
            onBackClick = viewModel::toLogin,
            handleChangeRegistrationData = viewModel::handleChangeRegistrationData,
            startRegistration = viewModel::startRegistration,
            declineRegistrationData = viewModel::declineRegistrationData,
            confirmRegistrationData = viewModel::confirmRegistrationData
        )

        is UserAuthorizedViewState -> Unit
    }
}
