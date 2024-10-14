package io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions

import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import ru.kontur.mobile.visualfsm.Action

sealed class AuthFSMAction : Action<AuthFSMState>()
