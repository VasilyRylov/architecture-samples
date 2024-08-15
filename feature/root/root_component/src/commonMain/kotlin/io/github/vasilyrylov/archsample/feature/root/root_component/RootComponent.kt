package io.github.vasilyrylov.archsample.feature.root.root_component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.common_component.createKoinScope
import io.github.vasilyrylov.archsample.common.common_component.createViewModel
import io.github.vasilyrylov.archsample.common.common_component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.common.common_component.updateRouterInstance
import io.github.vasilyrylov.archsample.feature.root.root_ui.RootViewModel
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFSMState

class RootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {


    private val savedState: RootFSMState = registerAndGetSavedState(
        key = ROOT_FSM_SAVED_STATE,
        initialValue = RootFSMState.AsyncWorkState.Initial,
        deserialization = RootFSMState.serializer(),
        serialization = RootFSMState.serializer()
    ) {
        koinScope.get<RootFeature>(RootFeature::class).getCurrentState()
    }

    private val koinScope = createKoinScope(
        listOf(createRootModule(savedState))
    )

    val router = RootFlowRouter(componentContext, koinScope)

    @Suppress("UNUSED")
    val viewModel = createViewModel<RootViewModel>(koinScope)

    init {
        koinScope.updateRouterInstance(router)
    }

    companion object {
        private const val ROOT_FSM_SAVED_STATE = "ROOT_FSM_SAVED_STATE"
    }
}
