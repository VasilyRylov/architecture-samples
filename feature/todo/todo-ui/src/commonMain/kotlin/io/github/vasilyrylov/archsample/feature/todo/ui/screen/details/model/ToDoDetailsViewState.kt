package io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model

import io.github.vasilyrylov.archsample.common.domain.model.TodoItem

data class ToDoDetailsViewState(
    val item: TodoItem,
    val dialog: ToDoDetailsScreenDialog = ToDoDetailsScreenDialog.None
)

sealed class ToDoDetailsScreenDialog {
    data object None : ToDoDetailsScreenDialog()
    data object EditToDo : ToDoDetailsScreenDialog()
}
