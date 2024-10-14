package io.github.vasilyrylov.archsample.feature.root.component.impl.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.feature.auth.component.api.AuthFlowComponent
import io.github.vasilyrylov.archsample.feature.root.component.impl.IRootFlowRouter
import io.github.vasilyrylov.archsample.feature.root.component.impl.RootFlowRouter
import io.github.vasilyrylov.archsample.feature.root.component.impl.RootViewModel
import io.github.vasilyrylov.archsample.feature.root.component.impl.di.dependencies.auth.AuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.component.impl.di.dependencies.todo.TodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.domain.di.RootFlowScope
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.todo.component.api.TodoFlowComponent
import io.github.vasilyrylov.archsample.user.data.repository.api.di.AuthDataDI
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@RootFlowScope
@Component
internal abstract class RootFlowDIComponent(
    @get:Provides val initialState: RootFSMState,
    @Component val authData: AuthDataDI = AuthDataDI.Instance,
) : InstanceKeeper.Instance {

    abstract val router: RootFlowRouter

    @Provides
    protected fun bind(): IRootFlowRouter = router

    abstract val viewModel: RootViewModel

    abstract val rootFeature: RootFeature

    @Provides
    protected fun AuthComponentDependencies.bind(): AuthFlowComponent.Dependencies = this

    @Provides
    protected fun AuthFlowComponent.DI.bind(): AuthFlowComponent.Factory = factory

    @Provides
    protected fun TodoComponentDependencies.bind(): TodoFlowComponent.Dependencies = this

    @Provides
    protected fun TodoFlowComponent.DI.bind(): TodoFlowComponent.Factory = factory

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}
