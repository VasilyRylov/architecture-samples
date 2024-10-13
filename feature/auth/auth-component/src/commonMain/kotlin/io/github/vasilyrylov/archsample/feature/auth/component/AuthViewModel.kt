package io.github.vasilyrylov.archsample.feature.auth.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.vasilyrylov.archsample.common.ui.base.BaseViewModel
import io.github.vasilyrylov.archsample.feature.auth.domain.di.AuthFlowScope
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.ui.ViewStateMapper
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Inject

@AuthFlowScope
@Inject
internal class AuthViewModel(
    private val authFeature: AuthFeature,
    private val callback: AuthFlowCallback,
) : BaseViewModel() {

    @Composable
    fun Ui() {
        val state by state.collectAsState()
        state.Ui(authFeature)
    }

    private val state = authFeature.observeState().map(ViewStateMapper::map)
        .stateIn(initialValue = ViewStateMapper.map(authFeature.getCurrentState()))

    init {
        initObservers()
    }

    private fun initObservers() = launch {
        authFeature.observeState().collect { fsmState ->
            when (fsmState) {
                is AuthFSMState.UserAuthorized -> callback.onAuthCompletion(fsmState.name)
                else -> Unit
            }
        }
    }

    override fun onCleared() {
        authFeature.onDestroy()
    }
}
