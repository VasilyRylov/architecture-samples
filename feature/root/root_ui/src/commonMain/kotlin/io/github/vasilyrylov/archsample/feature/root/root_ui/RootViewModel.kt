package io.github.vasilyrylov.archsample.feature.root.root_ui

import io.github.vasilyrylov.archsample.common.common_ui.BaseViewModel
import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.root_ui.api.IRootFlowRouter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RootViewModel(
    private val feature: RootFeature,
    private val routerHolder: RouterHolder<IRootFlowRouter>
) : BaseViewModel() {

    private val router: IRootFlowRouter
        get() = routerHolder.router!!

    init {
        observeState()
    }

    private fun observeState() {
        feature.observeState().onEach { state ->
            when (state) {
                RootFSMState.AsyncWorkState.Initial -> Unit
                RootFSMState.AuthFlow -> router.toAuth()
                is RootFSMState.ToDoFlow -> router.toToDo()
            }

        }.launchIn(coroutineScope)
    }

    override fun onCleared() {
        feature.onDestroy()
    }
}