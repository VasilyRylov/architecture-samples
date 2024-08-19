package io.github.vasilyrylov.archsample.feature.auth.ui

import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.ui.data.AuthViewState
import io.github.vasilyrylov.archsample.feature.auth.ui.data.LoginViewState
import io.github.vasilyrylov.archsample.feature.auth.ui.data.RegistrationViewState
import io.github.vasilyrylov.archsample.feature.auth.ui.data.UserAuthorizedViewState

object ViewStateMapper {
    fun map(state: AuthFSMState): AuthViewState {
        return when (state) {
            is AuthFSMState.Login -> LoginViewState(
                name = state.name,
                password = state.password,
                errorMessage = state.errorMessage,
                isAuthenticationInProgress = false,
                snackBarMessage = state.snackBarMessage
            )
            is AuthFSMState.AsyncWorkState.Authenticating -> LoginViewState(
                name = state.name,
                password = state.password,
                errorMessage = null,
                isAuthenticationInProgress = true,
                snackBarMessage = null
            )
            is AuthFSMState.Registration -> RegistrationViewState(
                name = state.name,
                password = state.password,
                repeatedPassword = state.repeatedPassword,
                errorMessage = state.errorMessage,
                isRegistrationInProgress = false,
                isConfirmationRequested = false
            )
            is AuthFSMState.AsyncWorkState.Registering -> RegistrationViewState(
                name = state.name,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = null,
                isRegistrationInProgress = true,
                isConfirmationRequested = false
            )
            is AuthFSMState.ConfirmationRequested -> RegistrationViewState(
                name = state.name,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = null,
                isRegistrationInProgress = false,
                isConfirmationRequested = true
            )
            is AuthFSMState.UserAuthorized -> UserAuthorizedViewState(
                name = state.name
            )
        }
    }
}