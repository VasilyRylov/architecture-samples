package io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm

import ru.kontur.mobile.visualfsm.State
import kotlinx.serialization.Serializable

@Serializable
sealed class AuthFSMState : State {

    @Serializable
    data class Login(
        val mail: String,
        val password: String,
        val errorMessage: String? = null,
        val snackBarMessage: String? = null,
    ) : AuthFSMState()

    @Serializable
    data class Registration(
        val mail: String,
        val password: String,
        val repeatedPassword: String,
        val errorMessage: String? = null
    ) : AuthFSMState()

    @Serializable
    data class ConfirmationRequested(
        val mail: String,
        val password: String
    ) : AuthFSMState()

    @Serializable
    sealed class AsyncWorkState : AuthFSMState() {
        @Serializable
        data class Authenticating(
            val mail: String,
            val password: String
        ) : AsyncWorkState()

        @Serializable
        data class Registering(
            val mail: String,
            val password: String
        ) : AsyncWorkState()
    }

    @Serializable
    data class UserAuthorized(val mail: String) : AuthFSMState()
}