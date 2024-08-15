package io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.AsyncWorkState
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.Login

internal class Authenticate : AuthFSMAction() {
    inner class AuthenticationStart : Transition<Login, AsyncWorkState.Authenticating>() {
        override fun transform(state: Login): AsyncWorkState.Authenticating {
            return AsyncWorkState.Authenticating(mail = state.mail, password = state.password)
        }
    }
}