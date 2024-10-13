package io.github.vasilyrylov.archsample.feature.auth.domain.fsm

import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState.AsyncWorkState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.AuthFSMAction
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleAuthResult
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.HandleRegistrationResult
import io.github.vasilyrylov.archsample.feature.auth.domain.usecase.AuthorizeUseCase
import io.github.vasilyrylov.archsample.feature.auth.domain.usecase.RegisterUserUseCase
import io.github.vasilyrylov.archsample.user.data.repository.api.model.User
import me.tatarka.inject.annotations.Inject

@Inject
class AuthAsyncWorker(
    private val authorize: AuthorizeUseCase,
    private val registerUser: RegisterUserUseCase,
) : AsyncWorker<AuthFSMState, AuthFSMAction>() {

    override fun onNextState(state: AuthFSMState): AsyncWorkerTask<AuthFSMState> {
        return when (state) {
            is AsyncWorkState.Authenticating -> {
                AsyncWorkerTask.ExecuteAndCancelExist(state) {
                    val result = authorize(state.name, state.password)
                    proceed(HandleAuthResult(result))
                }
            }

            is AsyncWorkState.Registering -> {
                AsyncWorkerTask.ExecuteIfNotExist(state) {
                    val newUser = User(state.name)
                    val result = registerUser(newUser, state.password)
                    proceed(HandleRegistrationResult(result))
                }
            }

            else -> AsyncWorkerTask.Cancel()
        }
    }
}
