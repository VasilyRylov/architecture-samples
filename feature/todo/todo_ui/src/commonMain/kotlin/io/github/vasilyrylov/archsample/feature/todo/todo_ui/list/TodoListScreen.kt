package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.model.ToDoListScreenViewState
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.model.ToDoListScreenDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewState: ToDoListScreenViewState,
    onAddClick: () -> Unit,
    onCompletedChange: (ToDoItemId) -> Unit,
    onToDoClick: (ToDoItemId) -> Unit,
    onConfirmAdd: (ToDoItem) -> Unit,
    onCancelAdd: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ToDo list") })
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onAddClick) {
                Text("Add")
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            TodoListScreenContent(
                todoItems = viewState.todoItems,
                onCompletedChange = onCompletedChange,
                onTodoItemClick = onToDoClick
            )
        }
    }

    when (viewState.dialog) {
        ToDoListScreenDialog.AddToDo -> AddToDoDialog(
            onConfirm = onConfirmAdd,
            onCancel = onCancelAdd
        )

        ToDoListScreenDialog.None -> Unit
    }
}