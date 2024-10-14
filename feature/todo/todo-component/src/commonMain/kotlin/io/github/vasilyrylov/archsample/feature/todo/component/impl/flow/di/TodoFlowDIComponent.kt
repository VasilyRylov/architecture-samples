package io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.feature.todo.component.api.TodoFlowComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.TodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.impl.details.TodoDetailsComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.list.TodoListComponent
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@TodoFlowScope
@Component
internal abstract class TodoFlowDIComponent(
    @Component val dependencies: TodoFlowComponent.Dependencies,
) : InstanceKeeper.Instance {

    abstract val router: TodoFlowRouter

    @Provides
    fun bind(): ITodoFlowRouter = router

    @Provides
    fun parent(): TodoFlowDIComponent = this

    @Provides
    fun TodoListComponent.DI.bind(): TodoListComponent.Factory = factory

    @Provides
    fun TodoDetailsComponent.DI.bind(): TodoDetailsComponent.Factory = factory
}
