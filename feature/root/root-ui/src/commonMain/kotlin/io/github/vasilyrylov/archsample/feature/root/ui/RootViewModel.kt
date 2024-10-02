package io.github.vasilyrylov.archsample.feature.root.ui

import io.github.vasilyrylov.archsample.common.ui.base.BaseViewModel
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.root.domain.di.RootFlowScope
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.ui.api.IRootFlowRouter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.tatarka.inject.annotations.Inject

@RootFlowScope
@Inject
class RootViewModel(
    private val feature: RootFeature,
    routerHolder: RouterHolder<IRootFlowRouter>
) : BaseViewModel() {

    private val router by routerHolder

    init {
        observeState()
    }

    private fun observeState() {
        feature.observeState().onEach { state ->
            when (state) {
                RootFSMState.AsyncWorkState.Initial -> Unit
                RootFSMState.AuthFlow -> router.toAuth()
                is RootFSMState.TodoFlow -> router.toTodo()
            }

        }.launchIn(coroutineScope)
    }

    override fun onCleared() {
        feature.onDestroy()
    }
}