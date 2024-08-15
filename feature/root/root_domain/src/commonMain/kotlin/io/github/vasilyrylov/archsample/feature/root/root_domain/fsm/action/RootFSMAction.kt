package io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.action

import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFSMState
import ru.kontur.mobile.visualfsm.Action

sealed class RootFSMAction : Action<RootFSMState>()
