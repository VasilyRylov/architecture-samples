package io.github.vasilyrylov.archsample.feature.todo.todo_ui.api

interface IToDoFlowRouter {
    fun toDetailToDo(toDoId: String)
    fun back()
}
