package io.github.vasilyrylov.archsample.feature.auth.component.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.auth.data.repository.UserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.domain.di.AuthFlowScope
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IUserRepository
import io.github.vasilyrylov.archsample.feature.auth.ui.AuthViewModel
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@AuthFlowScope
@Component
abstract class AuthDIComponent(
    private val initialState: AuthFSMState,
    private val dependencies: IAuthComponentDependencies
) : InstanceKeeper.Instance {
    abstract val viewModel: AuthViewModel

    abstract val authFeature: AuthFeature

    @Provides
    protected fun getAuthCompletionUseCase(): IAuthCompletionUseCase = dependencies.authCompletionUseCase

    @Provides
    protected fun getAuthorizedUserRepository(): IAuthorizedUserRepository = dependencies.authorizedUserRepository

    @Provides
    protected fun getUserDao(): UserDao = dependencies.userDao

    @Provides
    protected fun getInitialState(): AuthFSMState = initialState

    protected val UserRepository.bind: IUserRepository
        @Provides get() = this

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}