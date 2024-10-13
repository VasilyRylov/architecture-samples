package io.github.vasilyrylov.archsample.feature.todo.component.impl

import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.common.ui.navigation.IRouter

internal interface ITodoFlowRouter: IRouter {
    fun toDetailTodo(id: TodoItemId)
    fun back()
}
