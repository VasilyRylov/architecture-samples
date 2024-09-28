package io.github.vasilyrylov.archsample.feature.auth.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.component.di.AuthDIComponent
import io.github.vasilyrylov.archsample.feature.auth.component.di.create
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState

class AuthFlowComponent(
    componentContext: ComponentContext,
    dependencies: IAuthComponentDependencies,
) : ComponentContext by componentContext {

    private val savedState: AuthFSMState = registerAndGetSavedState(
        key = AUTH_FSM_SAVED_STATE,
        initialValue = AuthFSMState.Login("", ""),
        deserialization = AuthFSMState.serializer(),
        serialization = AuthFSMState.serializer()
    ) {
        diComponent.authFeature.getCurrentState()
    }

    private val diComponent = instanceKeeper.getOrCreate {
        AuthDIComponent::class.create(savedState, dependencies)
    }

    @Suppress("UNUSED")
    val viewModel = diComponent.viewModel

    companion object {
        private const val AUTH_FSM_SAVED_STATE = "AUTH_FSM_SAVED_STATE"
    }
}