package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.AsyncWorkState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.ConfirmationRequested
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.Registration
import ru.kontur.mobile.visualfsm.Transition

internal class HandleConfirmation(private val confirmed: Boolean) : AuthFSMAction() {
    inner class Confirm : Transition<ConfirmationRequested, AsyncWorkState.Registering>() {
        override fun predicate(state: ConfirmationRequested): Boolean {
            return confirmed
        }

        override fun transform(state: ConfirmationRequested): AsyncWorkState.Registering {
            return AsyncWorkState.Registering(name = state.name, password = state.password)
        }
    }

    inner class Cancel : Transition<ConfirmationRequested, Registration>() {
        override fun predicate(state: ConfirmationRequested): Boolean {
            return !confirmed
        }

        override fun transform(state: ConfirmationRequested): Registration {
            return Registration(
                name = state.name,
                password = state.password,
                repeatedPassword = state.password
            )
        }
    }
}
