package io.github.vasilyrylov.archsample.feature.todo.component.impl.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.common.ui.base.BaseStateViewModel
import io.github.vasilyrylov.archsample.feature.todo.component.api.list.TodoListCallback
import io.github.vasilyrylov.archsample.feature.todo.component.impl.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.domain.di.TodoListScope
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ObserveTodoListUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.TodoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListScreen
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListViewState
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@TodoListScope
@Inject
internal class TodoListViewModel(
    private val router: ITodoFlowRouter,
    private val initialState: TodoListViewState,
    private val callback: TodoListCallback,
    private val observeTodoList: ObserveTodoListUseCase,
    private val completedChange: TodoCompletedChangeUseCase,
    private val saveTodo: SaveTodoUseCase,
) : BaseStateViewModel<TodoListViewState>() {

    @Composable
    fun Ui() {
        val viewState by viewState.collectAsState()

        TodoListScreen(
            viewState = viewState,
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

    private fun onAddClick() {
        setState { copy(dialog = TodoListScreenDialog.AddTodo) }
    }

    private fun onConfirmAdd(newItem: TodoItem) {
        coroutineScope.launch {
            saveTodo(newItem)
            setState { copy(dialog = TodoListScreenDialog.None) }
        }
    }

    private fun onCancelAdd() {
        setState { copy(dialog = TodoListScreenDialog.None) }
    }

    private fun onCompletedChange(id: TodoItemId) {
        coroutineScope.launch {
            completedChange(id = id)
        }
    }

    private fun onTodoClick(id: TodoItemId) {
        router.toDetailTodo(id = id)
    }

    private fun onLogoutClick() {
        callback.onLogout()
    }

    private fun initObservers() {
        coroutineScope.launch {
            observeTodoList().collect {
                setState { copy(todoItems = it) }
            }
        }
    }

    override fun createInitialState() = initialState
}
