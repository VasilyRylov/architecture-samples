package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list

import ToDoItemData
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
private fun TodoListScreenPreview() {
    TodoListScreenMain(toDoItemsPreviewData, {}, {})
}

@Preview(showBackground = true)
@Composable
private fun AddToDoDialogPreview() {
    AddToDoDialog({}, {})
}


private val toDoItemsPreviewData = listOf(
    ToDoItemData(
        text = "One",
        completed = false
    ),
    ToDoItemData(
        text = "Two",
        completed = false
    ),
    ToDoItemData(
        text = "Three",
        completed = true
    ),
)