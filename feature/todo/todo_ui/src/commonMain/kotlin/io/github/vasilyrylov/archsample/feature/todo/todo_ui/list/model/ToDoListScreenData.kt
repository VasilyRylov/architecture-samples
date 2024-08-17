package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.model

import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem

data class ToDoListScreenData(
    val todoItems: List<ToDoItem>,
    val dialog: ToDoListScreenDialog = ToDoListScreenDialog.None
)

sealed class ToDoListScreenDialog {
    data object AddToDo : ToDoListScreenDialog()
    data object None : ToDoListScreenDialog()
}