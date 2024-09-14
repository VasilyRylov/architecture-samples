package io.github.vasilyrylov.archsample.feature.todo.component.list

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.component.createChildScope
import io.github.vasilyrylov.archsample.common.component.createViewModel
import io.github.vasilyrylov.archsample.feature.todo.component.list.di.createTodoListModule
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListViewModel
import org.koin.core.scope.Scope

class TodoListComponent(
    componentContext: ComponentContext,
    parentScope: Scope,
) : ComponentContext by componentContext {

    private val koinScope = createChildScope(parentScope, listOf(createTodoListModule()))

    @Suppress("UNUSED")
    val viewModel = createViewModel<TodoListViewModel>(koinScope)
}