package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.AsyncWorkState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.Login
import ru.kontur.mobile.visualfsm.Transition

internal class Authenticate : AuthFSMAction() {
    inner class AuthenticationStart : Transition<Login, AsyncWorkState.Authenticating>() {
        override fun transform(state: Login): AsyncWorkState.Authenticating {
            return AsyncWorkState.Authenticating(name = state.name, password = state.password)
        }
    }
}
