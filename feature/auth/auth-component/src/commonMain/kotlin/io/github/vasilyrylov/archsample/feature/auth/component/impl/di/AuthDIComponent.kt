package io.github.vasilyrylov.archsample.feature.auth.component.impl.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.feature.auth.component.api.AuthFlowComponent
import io.github.vasilyrylov.archsample.feature.auth.component.impl.AuthViewModel
import io.github.vasilyrylov.archsample.feature.auth.domain.di.AuthFlowScope
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeatureImpl
import io.github.vasilyrylov.archsample.user.data.repository.api.di.AuthDataDI
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@AuthFlowScope
@Component
internal abstract class AuthDIComponent(
    private val initialState: AuthFSMState,
    @Component val dependencies: AuthFlowComponent.Dependencies,
    @Component val authData: AuthDataDI = AuthDataDI.Instance,
) : InstanceKeeper.Instance {

    abstract val viewModel: AuthViewModel

    abstract val authFeature: AuthFeature

    @Provides
    protected fun getInitialState(): AuthFSMState = initialState

    protected val AuthFeatureImpl.bind: AuthFeature
        @Provides get() = this

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}
