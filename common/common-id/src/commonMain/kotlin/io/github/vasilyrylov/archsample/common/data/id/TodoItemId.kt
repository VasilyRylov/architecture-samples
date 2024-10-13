package io.github.vasilyrylov.archsample.common.data.id

import com.benasher44.uuid.Uuid
import io.github.vasilyrylov.archsample.common.data.serializer.UuidSerializer
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
value class TodoItemId(@Serializable(with = UuidSerializer::class) val value: Uuid)
