package io.github.vasilyrylov.archsample.feature.root.root_domain.fsm

import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.action.Login
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.action.RootFSMAction
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
class RootFeature(
    initialState: RootFSMState,
    private val asyncWorker: RootAsyncWorker
) : Feature<RootFSMState, RootFSMAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedRootFeatureTransitionsFactory()
) {
    fun login(userId: String) {
        proceed(Login(userId))
    }

    fun onDestroy() {
        asyncWorker.unbind()
    }
}
