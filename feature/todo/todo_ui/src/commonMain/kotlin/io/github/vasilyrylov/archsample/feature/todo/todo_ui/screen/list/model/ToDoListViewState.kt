package io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.list.model

import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem

data class ToDoListViewState(
    val todoItems: List<ToDoItem>,
    val dialog: ToDoListScreenDialog = ToDoListScreenDialog.None
)

sealed class ToDoListScreenDialog {
    data object None : ToDoListScreenDialog()
    data object AddToDo : ToDoListScreenDialog()
}
