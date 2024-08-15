package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId

@Composable
fun TodoListScreenContent(
    todoItems: List<ToDoItem>,
    onTodoItemClick: (ToDoItemId) -> Unit,
    onCompletedChange: (ToDoItemId) -> Unit
) {
    LazyColumn {
        items(
            items = todoItems
        ) { todoItem ->
            ToDoItemContent(
                todoItem,
                onCompletedChange = onCompletedChange,
                onTodoItemClick = onTodoItemClick
            )
        }
    }
}

@Composable
fun ToDoItemContent(
    item: ToDoItem,
    onCompletedChange: (ToDoItemId) -> Unit,
    onTodoItemClick: (ToDoItemId) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clickable { onTodoItemClick(item.id) }
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.text
        )
        Checkbox(
            checked = item.completed,
            onCheckedChange = { onCompletedChange(item.id) }
        )
    }
}


