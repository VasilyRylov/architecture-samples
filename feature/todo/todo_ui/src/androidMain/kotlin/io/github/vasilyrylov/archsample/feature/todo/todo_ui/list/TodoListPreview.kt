package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.element.dialog.EditToDoItemDialog
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.list.TodoListScreenContent

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