package io.github.vasilyrylov.archsample.feature.auth.domain.fsm

import kotlinx.coroutines.flow.StateFlow
import ru.kontur.mobile.visualfsm.State

// Этот интерфейс будет вызываться из Ui, так что я бы скрыл функции IFeature в комплексном контексте.
// Типа interface IFeature<STATE : State> {
//     fun IComponent.getCurrentState(): STATE
//     fun IComponent.observeState(): StateFlow<STATE>
// }
interface AuthFeature : IFeature<AuthFSMState> {

    fun toRegistration()

    fun toLogin()

    fun confirmRegistrationData()

    fun declineRegistrationData()

    fun startAuthenticating()

    fun startRegistration()

    fun handleChangeRegistrationData(name: String, password: String, repeatPassword: String)

    fun handleChangeLoginData(name: String, password: String)

    fun handleSnackBarShowed()

    fun onDestroy()
}


interface IFeature<STATE : State> {

    /**
     * Returns current state
     *
     * @return current [state][State]
     */
    fun getCurrentState(): STATE

    fun observeState(): StateFlow<STATE>
}
