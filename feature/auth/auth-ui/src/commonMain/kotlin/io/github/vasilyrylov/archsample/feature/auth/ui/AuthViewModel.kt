package io.github.vasilyrylov.archsample.feature.auth.ui

import io.github.vasilyrylov.archsample.common.ui.base.BaseViewModel
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class AuthViewModel(
    private val authFeature: AuthFeature,
    private val authCompletion: IAuthCompletionUseCase
) : BaseViewModel() {

    val viewState = authFeature.observeState().map(ViewStateMapper::map)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = ViewStateMapper.map(authFeature.getCurrentState())
        )

    init {
        initObservers()
    }

    fun toRegistration() {
        authFeature.toRegistration()
    }

    fun toLogin() {
        authFeature.toLogin()
    }

    fun confirmRegistrationData() {
        authFeature.confirmRegistrationData()
    }

    fun declineRegistrationData() {
        authFeature.declineRegistrationData()
    }

    fun startAuthenticating() {
        authFeature.startAuthenticating()
    }

    fun startRegistration() {
        authFeature.startRegistration()
    }

    fun handleChangeRegistrationData(name: String, password: String, repeatPassword: String) {
        authFeature.handleChangeRegistrationData(name, password, repeatPassword)
    }

    fun handleChangeLoginData(name: String, password: String) {
        authFeature.handleChangeLoginData(name, password)
    }

    override fun onCleared() {
        authFeature.onDestroy()
    }

    private fun initObservers() {
        coroutineScope.launch {
            authFeature.observeState().collect { fsmState ->
                if (fsmState is AuthFSMState.UserAuthorized) {
                    authCompletion(fsmState.name)
                }
            }
        }
    }
}
