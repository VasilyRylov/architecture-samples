package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import ru.kontur.mobile.visualfsm.SelfTransition
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState

internal class HandleChangeRegistrationData(
    private val name: String,
    private val password: String,
    private val repeatPassword: String,
) : AuthFSMAction() {

    inner class ChangeRegistrationData : SelfTransition<AuthFSMState.Registration>() {
        override fun transform(state: AuthFSMState.Registration): AuthFSMState.Registration {
            return AuthFSMState.Registration(name = name, password = password, repeatedPassword = repeatPassword)
        }
    }
}