package io.github.vasilyrylov.archsample.feature.todo.todo_ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.model.ToDoDetailsScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.model.ToDoDetailsScreenViewState
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.EditToDoItemDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailsScreen(
    viewState: ToDoDetailsScreenViewState,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    onConfirmEdit: (ToDoItem) -> Unit,
    onCancelEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("ToDo") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit"
                        )
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete"
                        )
                    }
                })
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.consumeWindowInsets(innerPadding)
                .padding(innerPadding),
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Text(viewState.item.text)
            }
        }
    }

    when (viewState.dialog) {
        ToDoDetailsScreenDialog.EditToDo -> EditToDoItemDialog(
            toDoItem = viewState.item,
            onConfirm = onConfirmEdit,
            onCancel = onCancelEdit,
        )

        ToDoDetailsScreenDialog.None -> Unit
    }
}
