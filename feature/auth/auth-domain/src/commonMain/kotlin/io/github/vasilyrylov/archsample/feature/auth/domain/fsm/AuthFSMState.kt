package io.github.vasilyrylov.archsample.feature.auth.domain.fsm

import ru.kontur.mobile.visualfsm.State
import kotlinx.serialization.Serializable

@Serializable
sealed class AuthFSMState : State {

    @Serializable
    data class Login(
        val name: String,
        val password: String,
        val errorMessage: String? = null,
        val snackBarMessage: String? = null,
    ) : AuthFSMState()

    @Serializable
    data class Registration(
        val name: String,
        val password: String,
        val repeatedPassword: String,
        val errorMessage: String? = null
    ) : AuthFSMState()

    @Serializable
    data class ConfirmationRequested(
        val name: String,
        val password: String
    ) : AuthFSMState()

    @Serializable
    sealed class AsyncWorkState : AuthFSMState() {
        @Serializable
        data class Authenticating(
            val name: String,
            val password: String
        ) : AsyncWorkState()

        @Serializable
        data class Registering(
            val name: String,
            val password: String
        ) : AsyncWorkState()
    }

    @Serializable
    data class UserAuthorized(val name: String) : AuthFSMState()
}