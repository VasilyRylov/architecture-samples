package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import ru.kontur.mobile.visualfsm.SelfTransition
import ru.kontur.mobile.visualfsm.Transition
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.ConfirmationRequested
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.Registration

internal class StartRegistration : AuthFSMAction() {
    inner class RegistrationStart : Transition<Registration, ConfirmationRequested>() {
        override fun predicate(state: Registration): Boolean {
            return state.password == state.repeatedPassword && state.password.isNotBlank()
        }

        override fun transform(state: Registration): ConfirmationRequested {
            return ConfirmationRequested(name = state.name, password = state.password)
        }
    }

    inner class ValidationFailed : SelfTransition<Registration>() {
        override fun predicate(state: Registration): Boolean {
            return state.password != state.repeatedPassword || state.password.isBlank()
        }

        override fun transform(state: Registration): Registration {
            return Registration(
                name = state.name,
                password = state.password,
                repeatedPassword = state.repeatedPassword,
                errorMessage = "Password and repeated password must be equals and not empty"
            )
        }
    }
}