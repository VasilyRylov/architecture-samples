package io.github.vasilyrylov.archsample.feature.root.component

import io.github.vasilyrylov.archsample.common.ui.base.BaseViewModel
import io.github.vasilyrylov.archsample.feature.root.domain.di.RootFlowScope
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.tatarka.inject.annotations.Inject

@RootFlowScope
@Inject
class RootViewModel(
    private val router: IRootFlowRouter,
    private val feature: RootFeature,
) : BaseViewModel() {

    fun onStart() {
        observeState()
    }

    private fun observeState() {
        feature.observeState().onEach { state ->
            when (state) {
                RootFSMState.AsyncWorkState.Initial -> Unit
                RootFSMState.AuthFlow -> router.toAuth()
                RootFSMState.TodoFlow -> router.toTodo()
            }

        }.launchIn(coroutineScope)
    }

    override fun onCleared() {
        feature.onDestroy()
    }
}
