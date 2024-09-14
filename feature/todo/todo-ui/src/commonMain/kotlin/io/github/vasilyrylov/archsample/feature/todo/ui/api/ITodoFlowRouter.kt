package io.github.vasilyrylov.archsample.feature.todo.ui.api

import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId

interface ITodoFlowRouter {
    fun toDetailTodo(id: TodoItemId)
    fun back()
}
