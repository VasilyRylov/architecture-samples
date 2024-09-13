package io.github.vasilyrylov.archsample.feature.todo.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.ToDoDetailsViewState
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.TodoDetailsScreen

@Preview(showBackground = true)
@Composable
private fun TodoDetailsScreenPreview() {
    TodoDetailsScreen(
        ToDoDetailsViewState(
            item = TodoItem(
                text = "One",
                completed = false
            )
        ), {}, {}, {}, {}, {})
}