package io.github.vasilyrylov.archsample.feature.root.domain.fsm.action

import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

class Logout : RootFSMAction() {

    @Edge("Logout")
    inner class OnLogout : Transition<RootFSMState.TodoFlow, RootFSMState.AuthFlow>() {
        override fun transform(state: RootFSMState.TodoFlow): RootFSMState.AuthFlow {
            return RootFSMState.AuthFlow
        }
    }
}
