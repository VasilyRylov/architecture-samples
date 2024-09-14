package io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model

import io.github.vasilyrylov.archsample.common.domain.model.TodoItem

data class TodoListViewState(
    val todoItems: List<TodoItem>,
    val dialog: TodoListScreenDialog = TodoListScreenDialog.None
)

sealed class TodoListScreenDialog {
    data object None : TodoListScreenDialog()
    data object AddTodo : TodoListScreenDialog()
}
