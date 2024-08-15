package io.github.vasilyrylov.archsample.feature.todo.todo_component.list

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.common_component.createViewModel
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.ToDoListViewModel
import org.koin.core.scope.Scope

class ToDoListComponent(
    componentContext: ComponentContext,
    parentScope: Scope, // TODO cильная зависимость на флоу компоненте
) : ComponentContext by componentContext {

    @Suppress("UNUSED")
    val viewModel = createViewModel<ToDoListViewModel>(parentScope)
}