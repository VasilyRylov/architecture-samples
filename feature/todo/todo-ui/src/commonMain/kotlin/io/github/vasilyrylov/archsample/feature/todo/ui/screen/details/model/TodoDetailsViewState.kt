package io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model

import io.github.vasilyrylov.archsample.common.domain.model.TodoItem

data class TodoDetailsViewState(
    val item: TodoItem,
    val dialog: TodoDetailsScreenDialog = TodoDetailsScreenDialog.None
)

sealed class TodoDetailsScreenDialog {
    data object None : TodoDetailsScreenDialog()
    data object EditTodo : TodoDetailsScreenDialog()
}
