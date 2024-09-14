package io.github.vasilyrylov.archsample.feature.todo.component.details

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.component.createChildScope
import io.github.vasilyrylov.archsample.common.component.createViewModel
import io.github.vasilyrylov.archsample.feature.todo.component.details.di.createTodoDetailsModule
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.TodoDetailsViewModel
import org.koin.core.scope.Scope

class TodoDetailsComponent(
    componentContext: ComponentContext,
    parentScope: Scope,
    itemId: TodoItemId
) : ComponentContext by componentContext {
    private val koinScope = createChildScope(parentScope, listOf(createTodoDetailsModule(itemId)))

    val viewModel = createViewModel<TodoDetailsViewModel>(koinScope)
}