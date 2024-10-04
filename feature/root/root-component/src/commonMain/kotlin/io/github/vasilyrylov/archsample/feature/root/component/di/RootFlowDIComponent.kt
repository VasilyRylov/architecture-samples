package io.github.vasilyrylov.archsample.feature.root.component.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.common.data.repository.AuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.component.di.dependencies.AuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.component.di.dependencies.TodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.domain.di.RootFlowScope
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.root.domain.usecase.AuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.domain.usecase.LogoutUseCase
import io.github.vasilyrylov.archsample.feature.root.ui.RootViewModel
import io.github.vasilyrylov.archsample.feature.root.ui.api.IRootFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@RootFlowScope
@Component
internal abstract class RootFlowDIComponent(
    private val initialState: RootFSMState,
    private val dependencies: IRootComponentDependencies
) : InstanceKeeper.Instance {

    private val routerHolder = RouterHolder<IRootFlowRouter>()

    abstract val viewModel: RootViewModel

    abstract val rootFeature: RootFeature

    abstract val authComponentDependencies: IAuthComponentDependencies

    abstract val todoComponentDependencies: ITodoComponentDependencies

    @Provides
    fun getRouterHolder(): RouterHolder<IRootFlowRouter> = routerHolder

    @Provides
    protected fun getInitialState(): RootFSMState = initialState

    @Provides
    protected fun getPreferences(): IPreferences = dependencies.preferences

    @Provides
    protected fun getTodoDao(): TodoDao = dependencies.todoDao

    @Provides
    protected fun getUserDao(): UserDao = dependencies.userDao

    @Provides
    protected fun bind(it: AuthorizedUserRepository): IAuthorizedUserRepository = it

    @Provides
    protected fun bind(it: AuthCompletionUseCase): IAuthCompletionUseCase = it

    @Provides
    protected fun bind(it: LogoutUseCase): ILogoutUseCase = it

    @Provides
    protected fun bind(it: AuthComponentDependencies): IAuthComponentDependencies = it

    @Provides
    protected fun bind(it: TodoComponentDependencies): ITodoComponentDependencies = it

    override fun onDestroy() {
        viewModel.onDestroy()
    }
}