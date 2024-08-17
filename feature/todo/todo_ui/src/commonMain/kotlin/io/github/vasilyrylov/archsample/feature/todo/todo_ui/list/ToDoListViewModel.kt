package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list

import io.github.vasilyrylov.archsample.common.common_ui.BaseStateViewModel
import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.ObserveToDoListUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.ToDoCompleteChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.model.ToDoListScreenData
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.model.ToDoListScreenDialog
import kotlinx.coroutines.launch

class ToDoListViewModel(
    private val observeToDoList: ObserveToDoListUseCase,
    private val routerHolder: RouterHolder<IToDoFlowRouter>,
    private val completedChange: ToDoCompleteChangeUseCase,
    private val saveToDo: SaveToDoUseCase,
) : BaseStateViewModel<ToDoListScreenData>() {
    private val router: IToDoFlowRouter
        get() = routerHolder.router!!

    init {
        initObservers()
    }

    fun onAddClick() {
        setState { currentState.copy(dialog = ToDoListScreenDialog.AddToDo) }
    }

    fun confirmAdd(newItem: ToDoItem) {
        coroutineScope.launch {
            saveToDo(newItem)
            setState { currentState.copy(dialog = ToDoListScreenDialog.None) }
        }
    }

    fun cancelAdd() {
        setState { currentState.copy(dialog = ToDoListScreenDialog.None) }
    }

    fun onCompletedChange(id: ToDoItemId) {
        coroutineScope.launch {
            completedChange(id)
        }
    }

    fun onToDoClick(toDoItemId: ToDoItemId) {
        router.toDetailToDo(toDoId = toDoItemId.toString())
    }

    private fun initObservers() {
        coroutineScope.launch {
            observeToDoList().collect {
                setState { currentState.copy(todoItems = it) }
            }
        }
    }

    override fun createInitialState() = ToDoListScreenData(
        todoItems = listOf(),
        dialog = ToDoListScreenDialog.None
    )
}