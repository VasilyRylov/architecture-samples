package io.github.vasilyrylov.archsample.feature.root.domain.fsm.action

import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition

class Login : RootFSMAction() {

    @Edge("Login")
    inner class OnLogin : Transition<RootFSMState.AuthFlow, RootFSMState.ToDoFlow>() {
        override fun transform(state: RootFSMState.AuthFlow): RootFSMState.ToDoFlow {
            return RootFSMState.ToDoFlow
        }
    }
}
