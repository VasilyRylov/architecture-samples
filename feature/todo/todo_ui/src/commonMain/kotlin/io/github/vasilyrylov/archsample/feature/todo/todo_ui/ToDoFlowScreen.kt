package io.github.vasilyrylov.archsample.feature.todo.todo_ui

import ToDoItemData
import androidx.compose.runtime.Composable
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.TodoListScreenMain

@Composable
fun ToDoFlowScreen() {
    // Work in progress
    TodoListScreenMain(
        todoItems = listOf(ToDoItemData(text = "test", completed = false)),
        onAddClick = {})
}