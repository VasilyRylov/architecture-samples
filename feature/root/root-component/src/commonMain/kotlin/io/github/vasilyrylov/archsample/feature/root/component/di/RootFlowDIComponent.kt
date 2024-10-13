package io.github.vasilyrylov.archsample.feature.root.component.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.common.data.preferences.IPreferencesComponent
import io.github.vasilyrylov.archsample.common.data.preferences.PreferencesComponent
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.data.database.di.DatabaseComponent
import io.github.vasilyrylov.archsample.data.database.di.IDatabaseComponent
import io.github.vasilyrylov.archsample.feature.auth.component.AuthFlowComponent
import io.github.vasilyrylov.archsample.feature.root.component.IRootFlowRouter
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowRouter
import io.github.vasilyrylov.archsample.feature.root.component.di.dependencies.AuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.component.di.dependencies.TodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.root.component.RootViewModel
import io.github.vasilyrylov.archsample.feature.root.domain.di.RootFlowScope
import io.github.vasilyrylov.archsample.feature.todo.component.api.TodoFlowComponent
import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.impl.AuthorizedUserRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@RootFlowScope
@Component
internal abstract class RootFlowDIComponent(
    private val initialState: RootFSMState,
    @Component val preferences: IPreferencesComponent = PreferencesComponent,
    @Component val database: IDatabaseComponent = DatabaseComponent,
) : InstanceKeeper.Instance {

    abstract val router: RootFlowRouter

    @Provides
    fun bind(): IRootFlowRouter = router

    abstract val viewModel: RootViewModel

    abstract val rootFeature: RootFeature

    @Provides
    protected fun getInitialState(): RootFSMState = initialState

    @Provides
    protected fun AuthComponentDependencies.bind(): AuthFlowComponent.Dependencies = this

    @Provides
    protected fun AuthFlowComponent.DI.bind(): AuthFlowComponent.Factory = factory

    @Provides
    protected fun TodoComponentDependencies.bind(): TodoFlowComponent.Dependencies = this

    @Provides
    protected fun TodoFlowComponent.DI.bind(): TodoFlowComponent.Factory = factory

    val AuthorizedUserRepository.bind: IAuthorizedUserRepository
        @Provides get() = this

    @Provides
    fun ArchSampleDatabase.userDao(): UserDao = getUserDao()

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}
