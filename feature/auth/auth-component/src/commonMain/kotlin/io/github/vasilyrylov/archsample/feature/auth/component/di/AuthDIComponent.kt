package io.github.vasilyrylov.archsample.feature.auth.component.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.auth.data.repository.UserRepository
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.domain.di.AuthFlowScope
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeatureImpl
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IUserRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@AuthFlowScope
@Component
abstract class AuthDIComponent(
    private val initialState: AuthFSMState,
    @Component val dependencies: IAuthComponentDependencies
) : InstanceKeeper.Instance {

    abstract val authFeature: AuthFeature

    @Provides
    protected fun getInitialState(): AuthFSMState = initialState

    @Provides
    protected fun bind(impl: AuthFeatureImpl): AuthFeature = impl

    protected val UserRepository.bind: IUserRepository // Это я вообще не понял что тут делает, надо куда-то ближе к UserRepository утажить
        @Provides get() = this

    override fun onDestroy() {
        authFeature.onDestroy()
    }
}
