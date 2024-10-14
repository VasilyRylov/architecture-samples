package io.github.vasilyrylov.archsample.feature.todo.component.impl.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.common.component.viewmodel.BaseStateViewModel
import io.github.vasilyrylov.archsample.feature.todo.component.api.list.TodoListCallback
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.impl.list.di.TodoListScope
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ObserveTodoListUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.TodoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.ui.api.TodoListScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.api.TodoListViewState
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import me.tatarka.inject.annotations.Inject

@TodoListScope
@Inject
internal class TodoListViewModel(
    initialState: TodoListViewState,
    private val router: ITodoFlowRouter,
    private val callback: TodoListCallback,
    private val observeTodoList: ObserveTodoListUseCase,
    private val completedChange: TodoCompletedChangeUseCase,
    private val saveTodo: SaveTodoUseCase,
) : BaseStateViewModel<TodoListViewState>(initialState) {

    @Composable
    fun Ui() {
        val viewState by viewState.collectAsState()
        viewState.Ui(
            onAddClick = ::onAddClick,
            onCompletedChange = ::onCompletedChange,
            onTodoClick = ::onTodoClick,
            onConfirmAddClick = ::onConfirmAdd,
            onCancelAddClick = ::onCancelAdd,
            onLogoutClick = ::onLogoutClick,
        )
    }

    init {
        initObservers()
    }

    private fun initObservers() = launch {
        observeTodoList().collect {
            setState { copy(todoItems = it) }
        }
    }

    private fun onAddClick() {
        setState { copy(dialog = TodoListScreenDialog.AddTodo) }
    }

    private fun onConfirmAdd(newItem: TodoItem) = launch {
        saveTodo(newItem)
        setState { copy(dialog = TodoListScreenDialog.None) }
    }

    private fun onCancelAdd() {
        setState { copy(dialog = TodoListScreenDialog.None) }
    }

    private fun onCompletedChange(id: TodoItemId) = launch {
        completedChange(id = id)

    }

    private fun onTodoClick(id: TodoItemId) {
        router.toDetailTodo(id = id)
    }

    private fun onLogoutClick() {
        callback.onLogout()
    }
}
