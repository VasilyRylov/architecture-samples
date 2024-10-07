package io.github.vasilyrylov.archsample.feature.todo.component.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.common.domain.interfaces.ITodoRepository
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.ui.api.ITodoFlowRouter
import io.github.vasilyrylov.archsample.todo.data.repository.TodoRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class TodoFlowDIComponent(
    @Component val dependencies: ITodoComponentDependencies
) : InstanceKeeper.Instance {

    private val routerHolder = RouterHolder<ITodoFlowRouter>()

    @Provides
    fun getRouterHolder(): RouterHolder<ITodoFlowRouter> = routerHolder

    val TodoRepository.bind: ITodoRepository
        @Provides get() = this
}
