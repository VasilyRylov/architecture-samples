package io.github.vasilyrylov.archsample.feature.auth.auth_ui

import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.AuthScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.LoginScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.RegistrationScreenData
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.data.UserAuthorizedScreenData

object ScreenDataMapper {
    fun map(state: AuthFSMState): AuthScreenData {
        return when (state) {
            is AuthFSMState.Login -> LoginScreenData(
                name = state.name,
                password = state.password,
                errorMessage = state.errorMessage,
                isAuthenticationInProgress = false,
                snackBarMessage = state.snackBarMessage
            )
            is AuthFSMState.AsyncWorkState.Authenticating -> LoginScreenData(
                name = state.name,
                password = state.password,
                errorMessage = null,
                isAuthenticationInProgress = true,
                snackBarMessage = null
            )
            is AuthFSMState.Registration -> RegistrationScreenData(
                name = state.name,
                password = state.password,
                repeatedPassword = state.repeatedPassword,
                errorMessage = state.errorMessage,
                isRegistrationInProgress = false,
                isConfirmationRequested = false
            )
            is AuthFSMState.AsyncWorkState.Registering -> RegistrationScreenData(
                name = state.name,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = null,
                isRegistrationInProgress = true,
                isConfirmationRequested = false
            )
            is AuthFSMState.ConfirmationRequested -> RegistrationScreenData(
                name = state.name,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = null,
                isRegistrationInProgress = false,
                isConfirmationRequested = true
            )
            is AuthFSMState.UserAuthorized -> UserAuthorizedScreenData(
                name = state.name
            )
        }
    }
}