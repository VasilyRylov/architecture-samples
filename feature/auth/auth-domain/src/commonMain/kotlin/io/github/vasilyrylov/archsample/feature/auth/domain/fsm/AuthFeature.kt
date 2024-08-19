package io.github.vasilyrylov.archsample.feature.auth.domain.fsm

import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.AuthFSMAction
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.Authenticate
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.ChangeFlow
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleChangeLoginData
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleChangeRegistrationData
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleConfirmation
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleSnackBarShowed
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.StartRegistration
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
class AuthFeature(
    initialState: AuthFSMState,
    private val asyncWorker: AuthAsyncWorker
) : Feature<AuthFSMState, AuthFSMAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedAuthFeatureTransitionsFactory()
) {

    fun toRegistration() {
        proceed(ChangeFlow())
    }

    fun toLogin() {
        proceed(ChangeFlow())
    }

    fun confirmRegistrationData() {
        proceed(HandleConfirmation(true))
    }

    fun declineRegistrationData() {
        proceed(HandleConfirmation(false))
    }

    fun startAuthenticating() {
        proceed(Authenticate())
    }

    fun startRegistration() {
        proceed(StartRegistration())
    }

    fun handleChangeRegistrationData(name: String, password: String, repeatPassword: String) {
        proceed(HandleChangeRegistrationData(name, password, repeatPassword))
    }

    fun handleChangeLoginData(name: String, password: String) {
        proceed(HandleChangeLoginData(name, password))
    }

    fun handleSnackBarShowed() {
        proceed(HandleSnackBarShowed())
    }

    fun onDestroy() {
        asyncWorker.unbind()
    }
}