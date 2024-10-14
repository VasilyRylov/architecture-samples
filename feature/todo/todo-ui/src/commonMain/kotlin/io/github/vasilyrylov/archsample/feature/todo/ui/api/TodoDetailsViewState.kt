package io.github.vasilyrylov.archsample.feature.todo.ui.api

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.todo.ui.impl.screen.details.TodoDetailsScreen
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import kotlinx.serialization.Serializable

@Serializable
data class TodoDetailsViewState(
    val item: TodoItem,
    val dialog: TodoDetailsScreenDialog = TodoDetailsScreenDialog.None
) {
    @Composable
    fun Ui(
        onBackClick: () -> Unit,
        onDeleteClick: () -> Unit,
        onEditClick: () -> Unit,
        onConfirmEdit: (TodoItem) -> Unit,
        onCancelEdit: () -> Unit
    ) = TodoDetailsScreen(
        viewState = this,
        onBackClick = onBackClick,
        onDeleteClick = onDeleteClick,
        onEditClick = onEditClick,
        onConfirmEdit = onConfirmEdit,
        onCancelEdit = onCancelEdit,
    )
}

@Serializable
sealed class TodoDetailsScreenDialog {
    @Serializable
    data object None : TodoDetailsScreenDialog()

    @Serializable
    data object EditTodo : TodoDetailsScreenDialog()
}
