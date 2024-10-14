package io.github.vasilyrylov.archsample.feature.auth.component.impl

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.feature.auth.component.api.AuthFlowComponent
import io.github.vasilyrylov.archsample.feature.auth.component.impl.di.AuthDIComponent
import io.github.vasilyrylov.archsample.feature.auth.component.impl.di.create
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import me.tatarka.inject.annotations.Inject

internal class AuthFlowComponentImpl(
    componentContext: ComponentContext,
    dependencies: AuthFlowComponent.Dependencies,
) : AuthFlowComponent, ComponentContext by componentContext {

    @Composable
    override fun Ui() = viewModel.Ui()

    private val savedState: AuthFSMState = registerAndGetSavedState(
        key = AUTH_FSM_SAVED_STATE,
        initialValue = AuthFSMState.Login("", ""),
        deserialization = AuthFSMState.serializer(),
        serialization = AuthFSMState.serializer()
    ) {
        diComponent.authFeature.getCurrentState()
    }

    private val diComponent = instanceKeeper.getOrCreate<AuthDIComponent> {
        AuthDIComponent::class.create(savedState, dependencies)
    }

    private val viewModel = diComponent.viewModel

    companion object {
        private const val AUTH_FSM_SAVED_STATE = "AUTH_FSM_SAVED_STATE"
    }

    @Inject
    class Factory(
        private val dependencies: AuthFlowComponent.Dependencies,
    ) : AuthFlowComponent.Factory {

        override fun create(
            context: ComponentContext,
        ) = AuthFlowComponentImpl(context, dependencies)
    }
}
