package io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model

import io.github.vasilyrylov.archsample.common.domain.model.ToDoItem

data class ToDoDetailsViewState(
    val item: ToDoItem,
    val dialog: ToDoDetailsScreenDialog = ToDoDetailsScreenDialog.None
)

sealed class ToDoDetailsScreenDialog {
    data object None : ToDoDetailsScreenDialog()
    data object EditToDo : ToDoDetailsScreenDialog()
}
