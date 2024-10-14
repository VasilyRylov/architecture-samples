package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.AsyncWorkState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.Login
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.Registration
import io.github.vasilyrylov.archsample.feature.auth.domain.usecase.RegistrationResult
import ru.kontur.mobile.visualfsm.Transition

internal class HandleRegistrationResult(private val result: RegistrationResult) : AuthFSMAction() {

    inner class Success : Transition<AsyncWorkState.Registering, Login>() {
        override fun predicate(state: AsyncWorkState.Registering): Boolean {
            return result == RegistrationResult.SUCCESS
        }

        override fun transform(state: AsyncWorkState.Registering): Login {
            return Login(
                name = state.name,
                password = state.password,
                snackBarMessage = "${state.name} registered"
            )
        }
    }

    inner class BadCredential : Transition<AsyncWorkState.Registering, Registration>() {
        override fun predicate(state: AsyncWorkState.Registering): Boolean {
            return result == RegistrationResult.USER_ALREADY_REGISTERED
        }

        override fun transform(state: AsyncWorkState.Registering): Registration {
            return Registration(
                name = state.name,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = "User already registered"
            )
        }
    }

    inner class ConnectionFailed : Transition<AsyncWorkState.Registering, Registration>() {
        override fun predicate(state: AsyncWorkState.Registering): Boolean {
            return result == RegistrationResult.NO_INTERNET
        }

        override fun transform(state: AsyncWorkState.Registering): Registration {
            return Registration(
                name = state.name,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = "No internet"
            )
        }
    }
}
