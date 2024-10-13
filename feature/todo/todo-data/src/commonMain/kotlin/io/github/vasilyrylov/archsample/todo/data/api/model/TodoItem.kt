package io.github.vasilyrylov.archsample.todo.data.api.model

import com.benasher44.uuid.uuid4
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import kotlinx.serialization.Serializable

@Serializable
data class TodoItem(
    val text: String,
    val completed: Boolean,
    val id: TodoItemId = TodoItemId(uuid4()),
)
