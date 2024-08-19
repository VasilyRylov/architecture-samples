package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import ru.kontur.mobile.visualfsm.Edge
import ru.kontur.mobile.visualfsm.Transition
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.Login
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.Registration

internal class ChangeFlow : AuthFSMAction() {

    @Edge("ToLogin")
    inner class RegisterToLogin : Transition<Registration, Login>() {
        override fun transform(state: Registration): Login {
            return Login(name = state.name, password = "")
        }
    }

    @Edge("ToRegistration")
    inner class LoginToRegistration : Transition<Login, Registration>() {
        override fun transform(state: Login): Registration {
            return Registration(name = state.name, password = "", repeatedPassword = "")
        }
    }
}