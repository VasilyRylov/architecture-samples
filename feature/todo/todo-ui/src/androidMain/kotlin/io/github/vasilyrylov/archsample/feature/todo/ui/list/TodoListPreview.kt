package io.github.vasilyrylov.archsample.feature.todo.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vasilyrylov.archsample.feature.todo.ui.element.dialog.EditTodoItemDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListScreen
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListScreenContent
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.TodoListViewState
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem

@Preview(showBackground = true)
@Composable
private fun TodoListScreenPreview() {
    TodoListScreen(
        viewState = TodoListViewState(
            listOf()
        ),
        onAddClick = {},
        onCompletedChange = {},
        onTodoClick = {},
        onConfirmAddClick = {},
        onCancelAddClick = {},
        onLogoutClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun TodoListScreenContentPreview() {
    TodoListScreenContent(todoItemsPreviewData, {}, {})
}

@Preview(showBackground = true)
@Composable
private fun AddTodoDialogPreview() {
    EditTodoItemDialog(TodoItem("test", false), {}, {})
}


private val todoItemsPreviewData = listOf(
    TodoItem(
        text = "One",
        completed = false
    ),
    TodoItem(
        text = "Two",
        completed = false
    ),
    TodoItem(
        text = "Three",
        completed = true
    ),
)
