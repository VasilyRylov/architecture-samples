package io.github.vasilyrylov.archsample.feature.todo.ui.api

import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.ui.impl.screen.list.TodoListScreen
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import kotlinx.serialization.Serializable

@Serializable
data class TodoListViewState(
    val todoItems: List<TodoItem>,
    val dialog: TodoListScreenDialog = TodoListScreenDialog.None
) {
    @Composable
    fun Ui(
        onAddClick: () -> Unit,
        onCompletedChange: (TodoItemId) -> Unit,
        onTodoClick: (TodoItemId) -> Unit,
        onConfirmAddClick: (TodoItem) -> Unit,
        onCancelAddClick: () -> Unit,
        onLogoutClick: () -> Unit,
    ) = TodoListScreen(
        viewState = this,
        onAddClick = onAddClick,
        onCompletedChange = onCompletedChange,
        onTodoClick = onTodoClick,
        onConfirmAddClick = onConfirmAddClick,
        onCancelAddClick = onCancelAddClick,
        onLogoutClick = onLogoutClick,
    )
}

@Serializable
sealed class TodoListScreenDialog {
    @Serializable
    data object None : TodoListScreenDialog()

    @Serializable
    data object AddTodo : TodoListScreenDialog()
}
