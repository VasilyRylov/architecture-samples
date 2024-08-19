package io.github.vasilyrylov.archsample.feature.todo.todo_ui.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.details.model.ToDoDetailsViewState
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.details.TodoDetailsScreen

@Preview(showBackground = true)
@Composable
private fun TodoDetailsScreenPreview() {
    TodoDetailsScreen(
        ToDoDetailsViewState(
            item = ToDoItem(
                text = "One",
                completed = false
            )
        ), {}, {}, {}, {}, {})
}