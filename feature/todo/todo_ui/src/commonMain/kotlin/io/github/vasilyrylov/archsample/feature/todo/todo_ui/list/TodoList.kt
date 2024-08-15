package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list

import ToDoItemData
import ToDoItemId
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreenMain(
    todoItems: List<ToDoItemData>,
    onAddClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ToDo list") })
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { ExtendedFloatingActionButton(onClick = onAddClick) { Text("Add") } },
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier.consumeWindowInsets(innerPadding),
            contentPadding = innerPadding,
        ) {
            items(
                items = todoItems
            ) { todoItem ->
                ToDoItem(todoItem) { _ -> }
            }
        }
    }
}

@Composable
fun ToDoItem(
    item: ToDoItemData,
    onCompletedChange: (ToDoItemId) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
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


