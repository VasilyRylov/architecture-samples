package io.github.vasilyrylov.archsample.feature.auth.auth_component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.common_component.createKoinScope
import io.github.vasilyrylov.archsample.common.common_component.createViewModel
import io.github.vasilyrylov.archsample.common.common_component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.feature.auth.auth_component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.auth_component.di.createAuthModule
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.AuthViewModel

class AuthComponent(
    componentContext: ComponentContext,
    dependencies: IAuthComponentDependencies,
) : ComponentContext by componentContext {

    private val savedState: AuthFSMState = registerAndGetSavedState(
        key = AUTH_FSM_SAVED_STATE,
        initialValue = AuthFSMState.Login("", ""),
        deserialization = AuthFSMState.serializer(),
        serialization = AuthFSMState.serializer()
    ) {
        koinScope.get<AuthFeature>(AuthFeature::class).getCurrentState()
    }

    private val koinScope = createKoinScope(
        listOf(createAuthModule(savedState, dependencies))
    )

    @Suppress("UNUSED")
    val viewModel = createViewModel<AuthViewModel>(koinScope)

    companion object {
        private const val AUTH_FSM_SAVED_STATE = "AUTH_FSM_SAVED_STATE"
    }
}