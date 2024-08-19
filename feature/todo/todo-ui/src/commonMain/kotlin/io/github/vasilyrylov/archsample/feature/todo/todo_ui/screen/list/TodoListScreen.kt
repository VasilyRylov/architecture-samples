package io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.element.dialog.EditToDoItemDialog
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.list.model.ToDoListViewState
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.screen.list.model.ToDoListScreenDialog
import io.github.vasilyrylov.archsample.resources.Res
import io.github.vasilyrylov.archsample.resources.add
import io.github.vasilyrylov.archsample.resources.todo_list
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewState: ToDoListViewState,
    onAddClick: () -> Unit,
    onCompletedChange: (ToDoItemId) -> Unit,
    onToDoClick: (ToDoItemId) -> Unit,
    onConfirmAddClick: (ToDoItem) -> Unit,
    onCancelAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.todo_list))
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onAddClick) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(Res.string.add))
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(text = stringResource(Res.string.add))
                }
            }
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
            TodoListScreenContent(
                todoItems = viewState.todoItems,
                onCompletedChange = onCompletedChange,
                onTodoItemClick = onToDoClick
            )
        }
    }

    when (viewState.dialog) {
        ToDoListScreenDialog.None -> Unit

        ToDoListScreenDialog.AddToDo -> EditToDoItemDialog(
            toDoItem = ToDoItem(text = "", completed = false),
            onConfirmClick = onConfirmAddClick,
            onCancelClick = onCancelAddClick
        )
    }
}
