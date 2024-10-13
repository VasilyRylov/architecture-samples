package io.github.vasilyrylov.archsample.todo.data.api

import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.common.data.id.UserId
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {
    fun observeTodoList(userId: UserId): Flow<List<TodoItem>>
    suspend fun save(todoItem: TodoItem, userId: UserId)
    suspend fun getById(todoItemId: TodoItemId): TodoItem
    suspend fun delete(todoItemId: TodoItemId)
}
