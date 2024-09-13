package io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model

import io.github.vasilyrylov.archsample.common.domain.model.TodoItem

data class ToDoListViewState(
    val todoItems: List<TodoItem>,
    val dialog: ToDoListScreenDialog = ToDoListScreenDialog.None
)

sealed class ToDoListScreenDialog {
    data object None : ToDoListScreenDialog()
    data object AddToDo : ToDoListScreenDialog()
}
