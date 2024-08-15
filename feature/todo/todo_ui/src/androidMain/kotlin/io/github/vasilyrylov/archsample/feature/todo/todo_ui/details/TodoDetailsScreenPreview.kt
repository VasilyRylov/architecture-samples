package io.github.vasilyrylov.archsample.feature.todo.todo_ui.details

import ToDoItemData
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
private fun TodoDetailsScreenPreview() {
    TodoDetailsScreenMain(ToDoItemData(
        text = "One",
        completed = false
    ), {}, {}, {})
}