package io.github.vasilyrylov.archsample.feature.todo.ui.api

import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.common.ui.navigation.IRouter

interface ITodoFlowRouter: IRouter {
    fun toDetailTodo(id: TodoItemId)
    fun back()
}
