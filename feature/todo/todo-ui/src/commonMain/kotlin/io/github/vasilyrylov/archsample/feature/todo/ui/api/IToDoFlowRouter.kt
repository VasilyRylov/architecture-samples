package io.github.vasilyrylov.archsample.feature.todo.ui.api

interface IToDoFlowRouter {
    fun toDetailToDo(toDoId: String)
    fun back()
}
