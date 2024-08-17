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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.model.ToDoListScreenDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewModel: ToDoListViewModel
) {
    val state by viewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ToDo list") })
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = viewModel::onAddClick) {
                Text("Add")
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            TodoListScreenContent(
                todoItems = state.todoItems,
                onCompletedChange = viewModel::onCompletedChange,
                onTodoItemClick = viewModel::onToDoClick
            )
        }
    }

    when (state.dialog) {
        ToDoListScreenDialog.AddToDo -> AddToDoDialog(
            onConfirm = viewModel::confirmAdd,
            onCancel = viewModel::cancelAdd
        )

        ToDoListScreenDialog.None -> Unit
    }
}