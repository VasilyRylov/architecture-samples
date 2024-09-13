package io.github.vasilyrylov.archsample.feature.todo.component.details

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.component.createChildScope
import io.github.vasilyrylov.archsample.common.component.createViewModel
import io.github.vasilyrylov.archsample.feature.todo.component.details.di.createToDoDetailsModule
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.ToDoDetailsViewModel
import org.koin.core.scope.Scope

class ToDoDetailsComponent(
    componentContext: ComponentContext,
    parentScope: Scope,
    itemId: TodoItemId
) : ComponentContext by componentContext {
    private val koinScope = createChildScope(parentScope, listOf(createToDoDetailsModule(itemId)))

    val viewModel = createViewModel<ToDoDetailsViewModel>(koinScope)
}