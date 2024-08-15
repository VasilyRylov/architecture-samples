package io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions

import ru.kontur.mobile.visualfsm.SelfTransition
import ru.kontur.mobile.visualfsm.Transition
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.ConfirmationRequested
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.Registration

internal class StartRegistration : AuthFSMAction() {
    inner class RegistrationStart : Transition<Registration, ConfirmationRequested>() {
        override fun predicate(state: Registration): Boolean {
            return state.password == state.repeatedPassword && state.password.isNotBlank()
        }

        override fun transform(state: Registration): ConfirmationRequested {
            return ConfirmationRequested(mail = state.mail, password = state.password)
        }
    }

    inner class ValidationFailed : SelfTransition<Registration>() {
        override fun predicate(state: Registration): Boolean {
            return state.password != state.repeatedPassword || state.password.isBlank()
        }

        override fun transform(state: Registration): Registration {
            return Registration(
                mail = state.mail,
                password = state.password,
                repeatedPassword = state.repeatedPassword,
                errorMessage = "Password and repeated password must be equals and not empty"
            )
        }
    }
}