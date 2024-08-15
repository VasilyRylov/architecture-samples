package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list

import io.github.vasilyrylov.archsample.common.common_ui.BaseViewModel
import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter

class ToDoListViewModel(
    private val routerHolder: RouterHolder<IToDoFlowRouter>
) : BaseViewModel() {
    private val router: IToDoFlowRouter
        get() = routerHolder.router!!

    fun onToDoClick(toDoId: String) {
        router.toDetailToDo(toDoId = toDoId)
    }

    override fun onCleared() {

    }
}