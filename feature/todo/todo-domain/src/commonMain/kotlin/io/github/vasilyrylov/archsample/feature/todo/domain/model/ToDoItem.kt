package io.github.vasilyrylov.archsample.feature.todo.domain.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

data class ToDoItem(
    val text: String,
    val completed: Boolean,
    val id: ToDoItemId = ToDoItemId(uuid4()),
)

data class ToDoItemId(val value: Uuid)
