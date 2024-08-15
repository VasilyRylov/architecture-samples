package io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.action

import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFSMState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

class Logout : RootFSMAction() {

    @Edge("Logout")
    inner class OnLogout : Transition<RootFSMState.ToDoFlow, RootFSMState.AuthState>() {
        override fun transform(state: RootFSMState.ToDoFlow): RootFSMState.AuthState {
            return RootFSMState.AuthState
        }
    }
}
