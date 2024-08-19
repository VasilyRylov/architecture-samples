package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState

internal class HandleChangeLoginData(
    private val name: String,
    private val password: String,
): AuthFSMAction() {

    inner class ChangeLoginData: Transition<AuthFSMState.Login, AuthFSMState.Login>() {
        override fun transform(state: AuthFSMState.Login): AuthFSMState.Login {
            return AuthFSMState.Login(name = name, password = password)
        }
    }
}