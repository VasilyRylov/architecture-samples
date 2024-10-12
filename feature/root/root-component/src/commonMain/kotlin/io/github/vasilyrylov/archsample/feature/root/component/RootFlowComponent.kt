package io.github.vasilyrylov.archsample.feature.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.component.di.RootFlowDIComponent
import io.github.vasilyrylov.archsample.feature.root.component.di.create
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState

class RootFlowComponent(
    componentContext: ComponentContext,
    dependencies: IRootComponentDependencies
) : ComponentContext by componentContext {

    private val savedState: RootFSMState = registerAndGetSavedState(
        key = ROOT_FSM_SAVED_STATE,
        initialValue = RootFSMState.AsyncWorkState.Initial,
        deserialization = RootFSMState.serializer(),
        serialization = RootFSMState.serializer()
    ) {
        diComponent.rootFeature.getCurrentState()
    }

    private val diComponent = instanceKeeper.getOrCreate<RootFlowDIComponent> {
        RootFlowDIComponent::class.create(savedState, dependencies)
    }

    internal val router = RootFlowRouter(componentContext, diComponent)

    init {
        diComponent.getRouterHolder().updateInstance(router)
    }

    @Suppress("UNUSED")
    val viewModel = diComponent.viewModel

    companion object {
        private const val ROOT_FSM_SAVED_STATE = "ROOT_FSM_SAVED_STATE"
    }
}
