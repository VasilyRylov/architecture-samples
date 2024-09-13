package io.github.vasilyrylov.archsample.common.domain.interfaces

import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.common.domain.model.UserId
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {
    fun observeToDoList(userId: UserId): Flow<List<TodoItem>>
    suspend fun save(todoItem: TodoItem, userId: UserId)
    suspend fun getById(todoItemId: TodoItemId): TodoItem
    suspend fun delete(todoItemId: TodoItemId)
}