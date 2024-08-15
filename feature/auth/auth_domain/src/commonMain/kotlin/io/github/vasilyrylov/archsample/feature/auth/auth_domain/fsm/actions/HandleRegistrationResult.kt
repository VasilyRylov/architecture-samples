package io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.data.RegistrationResult
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.AsyncWorkState
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.Login
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.Registration

internal class HandleRegistrationResult(private val result: RegistrationResult) : AuthFSMAction() {

    inner class Success : Transition<AsyncWorkState.Registering, Login>() {
        override fun predicate(state: AsyncWorkState.Registering): Boolean {
            return result == RegistrationResult.SUCCESS
        }

        override fun transform(state: AsyncWorkState.Registering): Login {
            return Login(
                mail = state.mail,
                password = state.password,
                snackBarMessage = "${state.mail} registered"
            )
        }
    }

    inner class BadCredential : Transition<AsyncWorkState.Registering, Registration>() {
        override fun predicate(state: AsyncWorkState.Registering): Boolean {
            return result == RegistrationResult.USER_ALREADY_REGISTERED
        }

        override fun transform(state: AsyncWorkState.Registering): Registration {
            return Registration(
                mail = state.mail,
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
                mail = state.mail,
                password = state.password,
                repeatedPassword = state.password,
                errorMessage = "No internet"
            )
        }
    }
}