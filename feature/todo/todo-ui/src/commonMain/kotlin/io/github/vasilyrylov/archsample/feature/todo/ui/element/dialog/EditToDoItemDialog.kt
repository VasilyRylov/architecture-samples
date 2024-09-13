package io.github.vasilyrylov.archsample.feature.todo.ui.element.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.resources.Res
import io.github.vasilyrylov.archsample.resources.cancel
import io.github.vasilyrylov.archsample.resources.save
import io.github.vasilyrylov.archsample.resources.todo
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditToDoItemDialog(
    toDoItem: TodoItem,
    onCancelClick: () -> Unit,
    onConfirmClick: (TodoItem) -> Unit
) {
    var todoState by remember { mutableStateOf(toDoItem) }

    AlertDialog(
        onDismissRequest = onCancelClick,
        title = { Text(text = stringResource(Res.string.todo)) },
        text = {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = todoState.text,
                onValueChange = { todoState = todoState.copy(text = it) },
            )
        },
        dismissButton = {
            TextButton(onClick = onCancelClick) {
                Text(text = stringResource(Res.string.cancel))
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirmClick(todoState) }) {
                Text(text = stringResource(Res.string.save))
            }
        },
    )
}
