package io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm

import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState.AsyncWorkState
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions.AuthFSMAction
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions.HandleAuthResult
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.actions.HandleRegistrationResult
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.interactor.AuthInteractor

class AuthAsyncWorker(private val authInteractor: AuthInteractor) : AsyncWorker<AuthFSMState, AuthFSMAction>() {

    override fun onNextState(state: AuthFSMState): AsyncWorkerTask<AuthFSMState> {
        return when (state) {
            is AsyncWorkState.Authenticating -> {
                AsyncWorkerTask.ExecuteAndCancelExist(state) {
                    val result = authInteractor.check(state.mail, state.password)
                    proceed(HandleAuthResult(result))
                }
            }

            is AsyncWorkState.Registering -> {
                AsyncWorkerTask.ExecuteIfNotExist(state) {
                    val result = authInteractor.register(state.mail, state.password)
                    proceed(HandleRegistrationResult(result))
                }
            }

            else -> AsyncWorkerTask.Cancel()
        }
    }
}