package io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.action

import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFSMState
import ru.kontur.mobile.visualfsm.Transition

data class Initialize(val userId: String?) : RootFSMAction() {

    inner class UserAuthorized : Transition<RootFSMState.AsyncWorkState.Initial, RootFSMState.ToDoFlow>() {
        override fun predicate(state: RootFSMState.AsyncWorkState.Initial): Boolean {
            return !userId.isNullOrBlank()
        }

        override fun transform(state: RootFSMState.AsyncWorkState.Initial): RootFSMState.ToDoFlow {
            return RootFSMState.ToDoFlow
        }
    }

    inner class UserNotAuthorized : Transition<RootFSMState.AsyncWorkState.Initial, RootFSMState.AuthFlow>() {
        override fun predicate(state: RootFSMState.AsyncWorkState.Initial): Boolean {
            return userId.isNullOrBlank()
        }

        override fun transform(state: RootFSMState.AsyncWorkState.Initial): RootFSMState.AuthFlow {
            return RootFSMState.AuthFlow
        }
    }
}