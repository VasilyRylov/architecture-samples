package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.SelfTransition

internal class HandleSnackBarShowed : AuthFSMAction() {

    inner class SnackBarShowed : SelfTransition<AuthFSMState.Login>() {
        override fun transform(state: AuthFSMState.Login): AuthFSMState.Login {
            return state.copy(snackBarMessage = null)
        }
    }
}
