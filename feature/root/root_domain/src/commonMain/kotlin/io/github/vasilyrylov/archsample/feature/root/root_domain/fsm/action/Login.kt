package io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.action

import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFSMState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

data class Login(val userId: String) : RootFSMAction() {

    @Edge("Login")
    inner class OnLogin : Transition<RootFSMState.AuthState, RootFSMState.ToDoFlow>() {
        override fun transform(state: RootFSMState.AuthState): RootFSMState.ToDoFlow {
            return RootFSMState.ToDoFlow(userId)
        }
    }
}
