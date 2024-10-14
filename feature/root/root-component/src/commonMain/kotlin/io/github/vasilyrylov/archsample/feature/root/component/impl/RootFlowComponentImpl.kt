package io.github.vasilyrylov.archsample.feature.root.component.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.feature.root.component.api.RootFlowComponent
import io.github.vasilyrylov.archsample.feature.root.component.impl.di.RootFlowDIComponent
import io.github.vasilyrylov.archsample.feature.root.component.impl.di.create
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import me.tatarka.inject.annotations.Inject

internal class RootFlowComponentImpl(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, RootFlowComponent {

    @Composable
    override fun Ui() {
        val childSlot by childSlot.subscribeAsState()
        childSlot.child?.instance?.Ui()
    }

    private val savedState: RootFSMState = registerAndGetSavedState(
        key = ROOT_FSM_SAVED_STATE,
        initialValue = RootFSMState.AsyncWorkState.Initial,
        deserialization = RootFSMState.serializer(),
        serialization = RootFSMState.serializer()
    ) {
        diComponent.rootFeature.getCurrentState()
    }

    private val diComponent = instanceKeeper.getOrCreate<RootFlowDIComponent> {
        RootFlowDIComponent::class.create(savedState)
    }

    private val childSlot = diComponent.router.childSlot(componentContext)

    init {
        diComponent.viewModel
    }

    companion object {
        private const val ROOT_FSM_SAVED_STATE = "ROOT_FSM_SAVED_STATE"
    }

    @Inject
    class Factory : RootFlowComponent.Factory {

        override fun create(
            context: ComponentContext,
        ) = RootFlowComponentImpl(context)
    }
}
