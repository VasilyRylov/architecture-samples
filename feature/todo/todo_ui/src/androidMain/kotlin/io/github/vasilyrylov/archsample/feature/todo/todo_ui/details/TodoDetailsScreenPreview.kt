package io.github.vasilyrylov.archsample.feature.todo.todo_ui.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem

@Preview(showBackground = true)
@Composable
private fun TodoDetailsScreenPreview() {
    TodoDetailsScreenMain(
        ToDoItem(
        text = "One",
        completed = false
    ), {}, {}, {})
}