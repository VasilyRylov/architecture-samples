package io.github.vasilyrylov.archsample.feature.auth.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.feature.auth.component.BaseComponent.Companion.withCoroutine
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.component.di.AuthDIComponent
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.ui.ViewStateMapper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AuthFlowComponentImpl(
    componentContext: ComponentContext,
    dependencies: IAuthComponentDependencies,
) : AuthFlowComponent, CoroutineComponentContext by componentContext.withCoroutine() {

    @Composable
    override fun Ui() {
        val state by state.collectAsState()
        state.Ui(authFeature)
    }

    init {
        initObservers()
    }

    // Это бы я как-то скрыл в общую или базовую реализацию.
    // В идеале вообще сразу получить в конструктор authFeature и authCompletionUseCase.
    private val diComponent = instanceKeeper.getOrCreate<AuthDIComponent> {
        AuthDIComponent::class.create(savedState, dependencies)
    }

    private val authFeature get() = diComponent.authFeature

    private val savedState: AuthFSMState = registerAndGetSavedState(
        key = AUTH_FSM_SAVED_STATE,
        initialValue = AuthFSMState.Login("", ""),
        deserialization = AuthFSMState.serializer(),
        serialization = AuthFSMState.serializer()
    ) {
        authFeature.getCurrentState()
    }

    private val state = authFeature.observeState().map(ViewStateMapper::map)
        .stateIn(initialValue = ViewStateMapper.map(authFeature.getCurrentState()))

    private fun initObservers() {
        launch {
            authFeature.observeState().collect { fsmState ->
                if (fsmState is AuthFSMState.UserAuthorized) {
                    diComponent.dependencies.authCompletionUseCase(fsmState.name)
                }
            }
        }
    }

    companion object {
        private const val AUTH_FSM_SAVED_STATE = "AUTH_FSM_SAVED_STATE"
    }

    class Factory(
        private val authComponentDependencies: IAuthComponentDependencies,
    ) : AuthFlowComponent.Factory {

        override fun create(
            context: ComponentContext,
        ) = AuthFlowComponentImpl(context, authComponentDependencies)
    }
}
