package io.github.vasilyrylov.archsample.feature.auth.component.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.component.AuthFlowComponent
import io.github.vasilyrylov.archsample.feature.auth.component.AuthViewModel
import io.github.vasilyrylov.archsample.feature.auth.domain.di.AuthFlowScope
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeatureImpl
import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.api.IUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.impl.AuthorizedUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.impl.UserRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@AuthFlowScope
@Component
internal abstract class AuthDIComponent(
    private val initialState: AuthFSMState,
    @Component val dependencies: AuthFlowComponent.Dependencies,
) : InstanceKeeper.Instance {

    abstract val viewModel: AuthViewModel

    abstract val authFeature: AuthFeature

    @Provides
    protected fun getInitialState(): AuthFSMState = initialState

    protected val AuthFeatureImpl.bind: AuthFeature
        @Provides get() = this

    protected val AuthorizedUserRepository.bind: IAuthorizedUserRepository
        @Provides get() = this

    protected val UserRepository.bind: IUserRepository
        @Provides get() = this

    protected val ArchSampleDatabase.userDao: UserDao
        @Provides get() = getUserDao()

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}
