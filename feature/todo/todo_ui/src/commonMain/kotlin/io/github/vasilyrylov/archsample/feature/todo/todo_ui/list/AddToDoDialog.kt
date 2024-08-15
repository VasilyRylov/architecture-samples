package io.github.vasilyrylov.archsample.feature.todo.todo_ui.list

import io.github.vasilyrylov.archsample.common.common_ui.CustomDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem


@Composable
fun AddToDoDialog(
    onConfirm: (ToDoItem) -> Unit,
    onCancel: () -> Unit
) {
    val todoState = remember { mutableStateOf(ToDoItem(text = "", completed = false)) }

    CustomDialog(
        header = "ToDo"
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = todoState.value.text,
                onValueChange = { todoState.value = todoState.value.copy(text = it) },
            )

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = { onConfirm(todoState.value) }
                ) {
                    Text("Add")
                }
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = { onCancel() }
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}
