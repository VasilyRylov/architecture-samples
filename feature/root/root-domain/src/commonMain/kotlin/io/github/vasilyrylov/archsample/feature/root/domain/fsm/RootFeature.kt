package io.github.vasilyrylov.archsample.feature.root.domain.fsm

import io.github.vasilyrylov.archsample.feature.root.domain.di.RootFlowScope
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.action.Login
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.action.Logout
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.action.RootFSMAction
import me.tatarka.inject.annotations.Inject
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@RootFlowScope
@Inject
@GenerateTransitionsFactory
class RootFeature(
    initialState: RootFSMState,
    private val asyncWorker: RootAsyncWorker
) : Feature<RootFSMState, RootFSMAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedRootFeatureTransitionsFactory()
) {
    fun login() {
        proceed(Login())
    }

    fun logout() {
        proceed(Logout())
    }

    fun onDestroy() {
        asyncWorker.unbind()
    }
}
