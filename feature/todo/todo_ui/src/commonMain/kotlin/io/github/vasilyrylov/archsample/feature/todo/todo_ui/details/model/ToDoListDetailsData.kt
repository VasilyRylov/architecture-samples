package io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.model

import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem

data class ToDoDetailsScreenViewState(
    val item: ToDoItem,
    val dialog: ToDoDetailsScreenDialog = ToDoDetailsScreenDialog.None
)

sealed class ToDoDetailsScreenDialog {
    data object EditToDo : ToDoDetailsScreenDialog()
    data object None : ToDoDetailsScreenDialog()
}