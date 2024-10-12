package io.github.vasilyrylov.archsample.feature.auth.domain.fsm

import io.github.vasilyrylov.archsample.feature.auth.domain.di.AuthFlowScope
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.AuthFSMAction
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.Authenticate
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.ChangeFlow
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleChangeLoginData
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleChangeRegistrationData
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleConfirmation
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleSnackBarShowed
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.StartRegistration
import me.tatarka.inject.annotations.Inject
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@AuthFlowScope
@Inject
@GenerateTransitionsFactory
class AuthFeatureImpl(
    initialState: AuthFSMState,
    private val asyncWorker: AuthAsyncWorker
) : Feature<AuthFSMState, AuthFSMAction>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = GeneratedAuthFeatureTransitionsFactory()
), AuthFeature {

    override fun toRegistration() {
        proceed(ChangeFlow())
    }

    override fun toLogin() {
        proceed(ChangeFlow())
    }

    override fun confirmRegistrationData() {
        proceed(HandleConfirmation(true))
    }

    override fun declineRegistrationData() {
        proceed(HandleConfirmation(false))
    }

    override fun startAuthenticating() {
        proceed(Authenticate())
    }

    override fun startRegistration() {
        proceed(StartRegistration())
    }

    override fun handleChangeRegistrationData(name: String, password: String, repeatPassword: String) {
        proceed(HandleChangeRegistrationData(name, password, repeatPassword))
    }

    override fun handleChangeLoginData(name: String, password: String) {
        proceed(HandleChangeLoginData(name, password))
    }

    override fun handleSnackBarShowed() {
        proceed(HandleSnackBarShowed())
    }

    override fun onDestroy() {
        asyncWorker.unbind()
    }
}
