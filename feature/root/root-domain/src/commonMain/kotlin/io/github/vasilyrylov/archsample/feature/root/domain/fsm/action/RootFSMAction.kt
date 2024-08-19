package io.github.vasilyrylov.archsample.feature.root.domain.fsm.action

import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import ru.kontur.mobile.visualfsm.Action

sealed class RootFSMAction : Action<RootFSMState>()
