package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import io.github.vasilyrylov.archsample.feature.auth.domain.data.AuthResult
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.AsyncWorkState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.Login
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.UserAuthorized

internal class HandleAuthResult(private val result: AuthResult) : AuthFSMAction() {

    inner class Success : Transition<AsyncWorkState.Authenticating, UserAuthorized>() {
        override fun predicate(state: AsyncWorkState.Authenticating): Boolean {
            return result == AuthResult.SUCCESS
        }

        override fun transform(state: AsyncWorkState.Authenticating): UserAuthorized {
            return UserAuthorized(name = state.name)
        }
    }

    inner class BadCredential : Transition<AsyncWorkState.Authenticating, Login>() {
        override fun predicate(state: AsyncWorkState.Authenticating): Boolean {
            return result == AuthResult.BAD_CREDENTIAL
        }

        override fun transform(state: AsyncWorkState.Authenticating): Login {
            return Login(name = state.name, password = state.password, errorMessage = "Bad credential")
        }
    }

    inner class ConnectionFailed : Transition<AsyncWorkState.Authenticating, Login>() {
        override fun predicate(state: AsyncWorkState.Authenticating): Boolean {
            return result == AuthResult.NO_INTERNET
        }

        override fun transform(state: AsyncWorkState.Authenticating): Login {
            return Login(name = state.name, password = state.password, errorMessage = "No internet")
        }
    }
}
