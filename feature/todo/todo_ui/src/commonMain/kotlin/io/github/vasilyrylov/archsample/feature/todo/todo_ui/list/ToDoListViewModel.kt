package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list

import io.github.vasilyrylov.archsample.common.common_ui.BaseViewModel
import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.ObserveToDoListUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class ToDoListViewModel(
    observeToDoList: ObserveToDoListUseCase,
    private val routerHolder: RouterHolder<IToDoFlowRouter>,
) : BaseViewModel() {
    private val router: IToDoFlowRouter
        get() = routerHolder.router!!

    val state = observeToDoList()
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = listOf()
        )

    fun onAddClick() {

    }

    fun onCompletedChange(toDoItemId: ToDoItemId) {
    }

    fun onToDoClick(toDoItemId: ToDoItemId) {
        router.toDetailToDo(toDoId = toDoItemId.toString())
    }
}