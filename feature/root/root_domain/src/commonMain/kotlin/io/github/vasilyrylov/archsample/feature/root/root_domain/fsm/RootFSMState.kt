package io.github.vasilyrylov.archsample.feature.root.root_domain.fsm

import ru.kontur.mobile.visualfsm.State
import kotlinx.serialization.Serializable


@Serializable
sealed class RootFSMState : State {

    @Serializable
    sealed class AsyncWorkState : RootFSMState() {
        @Serializable
        data object Initial : AsyncWorkState()
    }

    @Serializable
    data class ToDoFlow(val userId: String) : RootFSMState()

    @Serializable
    data object AuthState : RootFSMState()
}
