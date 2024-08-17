package io.github.vasilyrylov.archsample.feature.todo.todo_component.details

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.common_component.createChildScope
import io.github.vasilyrylov.archsample.common.common_component.createViewModel
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.ToDoDetailsViewModel
import org.koin.core.scope.Scope

class ToDoDetailsComponent(
    componentContext: ComponentContext,
    parentScope: Scope,
    itemId: ToDoItemId
) : ComponentContext by componentContext {
    private val koinScope = createChildScope(parentScope, listOf(createToDoDetailsModule(itemId)))

    val viewModel = createViewModel<ToDoDetailsViewModel>(koinScope)
}