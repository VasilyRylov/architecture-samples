package io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState

internal class HandleChangeLoginData(
    private val mail: String,
    private val password: String,
): AuthFSMAction() {

    inner class ChangeLoginData: Transition<AuthFSMState.Login, AuthFSMState.Login>() {
        override fun transform(state: AuthFSMState.Login): AuthFSMState.Login {
            return AuthFSMState.Login(name = mail, password = password)
        }
    }
}