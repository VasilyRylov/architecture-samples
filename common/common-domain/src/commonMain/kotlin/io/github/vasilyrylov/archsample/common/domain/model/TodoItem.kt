package io.github.vasilyrylov.archsample.common.domain.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

data class TodoItem(
    val text: String,
    val completed: Boolean,
    val id: TodoItemId = TodoItemId(uuid4()),
)

data class TodoItemId(val value: Uuid)
