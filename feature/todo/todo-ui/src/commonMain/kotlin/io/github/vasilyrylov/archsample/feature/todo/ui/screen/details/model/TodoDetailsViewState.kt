package io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model

import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import kotlinx.serialization.Serializable

@Serializable
data class TodoDetailsViewState(
    val item: TodoItem,
    val dialog: TodoDetailsScreenDialog = TodoDetailsScreenDialog.None
)

@Serializable
sealed class TodoDetailsScreenDialog {
    @Serializable
    data object None : TodoDetailsScreenDialog()

    @Serializable
    data object EditTodo : TodoDetailsScreenDialog()
}
