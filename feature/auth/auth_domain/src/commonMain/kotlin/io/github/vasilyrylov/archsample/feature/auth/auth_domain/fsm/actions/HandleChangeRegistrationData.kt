package io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions

import ru.kontur.mobile.visualfsm.SelfTransition
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState

internal class HandleChangeRegistrationData(
    private val mail: String,
    private val password: String,
    private val repeatPassword: String,
) : AuthFSMAction() {

    inner class ChangeRegistrationData : SelfTransition<AuthFSMState.Registration>() {
        override fun transform(state: AuthFSMState.Registration): AuthFSMState.Registration {
            return AuthFSMState.Registration(mail = mail, password = password, repeatedPassword = repeatPassword)
        }
    }
}