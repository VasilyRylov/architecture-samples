package io.github.vasilyrylov.archsample.todo.data.impl.mapper

import com.benasher44.uuid.uuidFrom
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.data.database.entity.TodoEntity
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem

object TodoMapper {
    fun toDatabase(todo: TodoItem, userId: String): TodoEntity {
        return TodoEntity(
            id = todo.id.value.toString(),
            userId = userId,
            text = todo.text,
            completed = todo.completed
        )
    }

    fun fromDatabase(todoEntity: TodoEntity): TodoItem {
        return TodoItem(
            id = TodoItemId(uuidFrom(todoEntity.id)),
            text = todoEntity.text,
            completed = todoEntity.completed
        )
    }
}
