package io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.details

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
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.details.model.ToDoDetailsScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.details.model.ToDoDetailsViewState
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.element.dialog.EditToDoItemDialog
import io.github.vasilyrylov.archsample.resources.Res
import io.github.vasilyrylov.archsample.resources.back
import io.github.vasilyrylov.archsample.resources.delete
import io.github.vasilyrylov.archsample.resources.edit
import io.github.vasilyrylov.archsample.resources.todo
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailsScreen(
    viewState: ToDoDetailsViewState,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    onConfirmEdit: (ToDoItem) -> Unit,
    onCancelEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(Res.string.todo)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(Res.string.back))
                    }
                },
                actions = {
                    IconButton(onClick = onEditClick) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = stringResource(Res.string.edit))
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = stringResource(Res.string.delete))
                    }
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .consumeWindowInsets(paddingValues = paddingValues)
                .padding(paddingValues = paddingValues),
        ) {
            Box(modifier = Modifier.padding(all = 16.dp)) {
                Text(text = viewState.item.text)
            }
        }
    }

    when (viewState.dialog) {
        ToDoDetailsScreenDialog.None -> Unit

        ToDoDetailsScreenDialog.EditToDo -> EditToDoItemDialog(
            toDoItem = viewState.item,
            onConfirmClick = onConfirmEdit,
            onCancelClick = onCancelEdit,
        )
    }
}
