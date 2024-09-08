package io.github.vasilyrylov.archsample.feature.todo.ui.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vasilyrylov.archsample.common.domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.ui.element.dialog.EditToDoItemDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListScreen
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListScreenContent
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.model.ToDoListViewState

@Preview(showBackground = true)
@Composable
private fun TodoListScreenPreview() {
    TodoListScreen(
        viewState = ToDoListViewState(
            listOf()
        ),
        onAddClick = {},
        onCompletedChange = {},
        onToDoClick = {},
        onConfirmAddClick = {},
        onCancelAddClick = {},
        onLogoutClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun TodoListScreenContentPreview() {
    TodoListScreenContent(toDoItemsPreviewData, {}, {})
}

@Preview(showBackground = true)
@Composable
private fun AddToDoDialogPreview() {
    EditToDoItemDialog(ToDoItem("test", false), {}, {})
}


private val toDoItemsPreviewData = listOf(
    ToDoItem(
        text = "One",
        completed = false
    ),
    ToDoItem(
        text = "Two",
        completed = false
    ),
    ToDoItem(
        text = "Three",
        completed = true
    ),
)