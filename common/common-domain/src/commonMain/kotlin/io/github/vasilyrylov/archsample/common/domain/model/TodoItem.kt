package io.github.vasilyrylov.archsample.common.domain.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import io.github.vasilyrylov.archsample.common.domain.serializer.UuidSerializer
import kotlinx.serialization.Serializable

@Serializable
data class TodoItem(
    val text: String,
    val completed: Boolean,
    val id: TodoItemId = TodoItemId(uuid4()),
)

@Serializable
data class TodoItemId(@Serializable(with = UuidSerializer::class) val value: Uuid)
