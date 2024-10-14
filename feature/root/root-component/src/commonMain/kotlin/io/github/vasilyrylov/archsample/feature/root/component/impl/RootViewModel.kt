package io.github.vasilyrylov.archsample.feature.root.component.impl

import io.github.vasilyrylov.archsample.common.component.viewmodel.BaseViewModel
import io.github.vasilyrylov.archsample.feature.root.domain.di.RootFlowScope
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import me.tatarka.inject.annotations.Inject

@RootFlowScope
@Inject
internal class RootViewModel(
    private val router: IRootFlowRouter,
    private val feature: RootFeature,
) : BaseViewModel() {

    init {
        observeState()
    }

    private fun observeState() = launch {
        feature.observeState().collect { state ->
            when (state) {
                RootFSMState.AsyncWorkState.Initial -> Unit
                RootFSMState.AuthFlow -> router.toAuth()
                RootFSMState.TodoFlow -> router.toTodo()
            }
        }
    }

    override fun onCleared() {
        feature.onDestroy()
    }
}
