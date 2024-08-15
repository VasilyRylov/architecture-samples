package io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions

import ru.kontur.mobile.visualfsm.Transition
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.Login
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.UserAuthorized

internal class Logout : AuthFSMAction() {
    inner class Logout : Transition<UserAuthorized, Login>() {
        override fun transform(state: UserAuthorized) = Login(mail = "", password = "")
    }
}