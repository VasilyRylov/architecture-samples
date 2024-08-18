package io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.details.model

import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem

data class ToDoDetailsScreenViewState(
    val item: ToDoItem,
    val dialog: ToDoDetailsScreenDialog = ToDoDetailsScreenDialog.None
)

sealed class ToDoDetailsScreenDialog {
    data object None : ToDoDetailsScreenDialog()
    data object EditToDo : ToDoDetailsScreenDialog()
}
