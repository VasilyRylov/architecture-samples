package io.github.vasilyrylov.archsample.feature.todo.ui.screen.list

import io.github.vasilyrylov.archsample.common.ui.base.BaseStateViewModel
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ObserveToDoListUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ToDoCompletedChangeUseCase
import io.github.vasilyrylov.archsample.feature.todo.ui.api.IToDoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.ToDoListViewState
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.ToDoListScreenDialog
import kotlinx.coroutines.launch

class ToDoListViewModel(
    private val observeToDoList: ObserveToDoListUseCase,
    private val routerHolder: RouterHolder<IToDoFlowRouter>,
    private val completedChange: ToDoCompletedChangeUseCase,
    private val saveToDo: SaveToDoUseCase,
) : BaseStateViewModel<ToDoListViewState>() {

    private val router: IToDoFlowRouter
        get() = routerHolder.router

    init {
        initObservers()
    }

    fun onAddClick() {
        setState { copy(dialog = ToDoListScreenDialog.AddToDo) }
    }

    fun onConfirmAdd(newItem: ToDoItem) {
        coroutineScope.launch {
            saveToDo(newItem)
            setState { copy(dialog = ToDoListScreenDialog.None) }
        }
    }

    fun onCancelAdd() {
        setState { copy(dialog = ToDoListScreenDialog.None) }
    }

    fun onCompletedChange(id: ToDoItemId) {
        coroutineScope.launch {
            completedChange(id = id)
        }
    }

    fun onToDoClick(toDoItemId: ToDoItemId) {
        router.toDetailToDo(toDoId = toDoItemId.value.toString())
    }

    private fun initObservers() {
        coroutineScope.launch {
            observeToDoList().collect {
                setState { copy(todoItems = it) }
            }
        }
    }

    override fun createInitialState() = ToDoListViewState(
        todoItems = listOf(),
        dialog = ToDoListScreenDialog.None
    )
}
