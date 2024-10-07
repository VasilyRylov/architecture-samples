package io.github.vasilyrylov.archsample.feature.todo.ui.screen.list

import io.github.vasilyrylov.archsample.common.ui.base.BaseStateViewModel
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.di.TodoListScope
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ObserveTodoListUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.TodoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.ui.api.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListViewState
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListScreenDialog
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@TodoListScope
@Inject
class TodoListViewModel(
    private val observeTodoList: ObserveTodoListUseCase,
    routerHolder: RouterHolder<ITodoFlowRouter>,
    private val completedChange: TodoCompletedChangeUseCase,
    private val saveTodo: SaveTodoUseCase,
    private val logout: ILogoutUseCase,
    private val initialState: TodoListViewState,
) : BaseStateViewModel<TodoListViewState>() {

    private val router by routerHolder

    init {
        initObservers()
    }

    fun onAddClick() {
        setState { copy(dialog = TodoListScreenDialog.AddTodo) }
    }

    fun onConfirmAdd(newItem: TodoItem) {
        coroutineScope.launch {
            saveTodo(newItem)
            setState { copy(dialog = TodoListScreenDialog.None) }
        }
    }

    fun onCancelAdd() {
        setState { copy(dialog = TodoListScreenDialog.None) }
    }

    fun onCompletedChange(id: TodoItemId) {
        coroutineScope.launch {
            completedChange(id = id)
        }
    }

    fun onTodoClick(id: TodoItemId) {
        router.toDetailTodo(id = id)
    }

    fun onLogoutClick() {
        logout()
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
