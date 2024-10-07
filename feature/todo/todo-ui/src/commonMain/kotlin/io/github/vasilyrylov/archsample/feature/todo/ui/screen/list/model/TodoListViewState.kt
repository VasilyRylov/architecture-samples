package io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model

import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import kotlinx.serialization.Serializable

@Serializable
data class TodoListViewState(
    val todoItems: List<TodoItem>,
    val dialog: TodoListScreenDialog = TodoListScreenDialog.None
)

@Serializable
sealed class TodoListScreenDialog {
    @Serializable
    data object None : TodoListScreenDialog()

    @Serializable
    data object AddTodo : TodoListScreenDialog()
}
