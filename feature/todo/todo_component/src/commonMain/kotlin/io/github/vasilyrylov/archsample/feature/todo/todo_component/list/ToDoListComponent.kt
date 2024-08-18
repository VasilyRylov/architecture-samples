package io.github.vasilyrylov.archsample.feature.todo.todo_component.list

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.common_component.createChildScope
import io.github.vasilyrylov.archsample.common.common_component.createViewModel
import io.github.vasilyrylov.archsample.feature.todo.todo_component.list.di.createToDoListModule
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.ToDoListViewModel
import org.koin.core.scope.Scope

class ToDoListComponent(
    componentContext: ComponentContext,
    parentScope: Scope,
) : ComponentContext by componentContext {

    private val koinScope = createChildScope(parentScope, listOf(createToDoListModule()))

    @Suppress("UNUSED")
    val viewModel = createViewModel<ToDoListViewModel>(koinScope)
}