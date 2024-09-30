package io.github.vasilyrylov.archsample.feature.todo.component.list.di

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import io.github.vasilyrylov.archsample.feature.todo.component.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.domain.di.TodoListScope
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListViewModel
import me.tatarka.inject.annotations.Component

@TodoListScope
@Component
abstract class TodoListDIComponent(
    @Component val parent: TodoFlowDIComponent
) : InstanceKeeper.Instance {

    abstract val viewModel: TodoListViewModel
}
