package io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions

import ru.kontur.mobile.visualfsm.Action
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState

sealed class AuthFSMAction : Action<AuthFSMState>()