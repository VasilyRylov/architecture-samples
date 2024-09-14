package io.github.vasilyrylov.archsample.feature.root.domain.fsm

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
    data object TodoFlow : RootFSMState()

    @Serializable
    data object AuthFlow : RootFSMState()
}
