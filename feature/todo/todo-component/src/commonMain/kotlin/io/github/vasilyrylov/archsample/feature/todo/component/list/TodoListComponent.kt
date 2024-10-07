package io.github.vasilyrylov.archsample.feature.todo.component.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.feature.todo.component.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.list.di.TodoListDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.list.di.create
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListViewState

class TodoListComponent(
    componentContext: ComponentContext,
    todoFlowDIComponent: TodoFlowDIComponent,
) : ComponentContext by componentContext {

    private val savedState: TodoListViewState = registerAndGetSavedState(
        key = TODO_LIST_SAVED_STATE,
        initialValue = TodoListViewState(
            todoItems = listOf(),
            dialog = TodoListScreenDialog.None
        ),
        deserialization = TodoListViewState.serializer(),
        serialization = TodoListViewState.serializer()
    ) {
        viewModel.currentState
    }

    private val diComponent = instanceKeeper.getOrCreate {
        TodoListDIComponent::class.create(todoFlowDIComponent, savedState)
    }

    val viewModel = diComponent.viewModel

    companion object {
        private const val TODO_LIST_SAVED_STATE = "TODO_LIST_SAVED_STATE"
    }
}
