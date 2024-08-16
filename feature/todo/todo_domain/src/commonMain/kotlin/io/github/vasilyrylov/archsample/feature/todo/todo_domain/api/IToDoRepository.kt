package io.github.vasilyrylov.archsample.feature.todo.todo_domain.api

import io.github.vasilyrylov.archsample.common.common_domain.model.UserId
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import kotlinx.coroutines.flow.Flow

interface IToDoRepository {
    fun observeToDoList(userId: UserId): Flow<List<ToDoItem>>
    suspend fun save(toDoItem: ToDoItem, userId: UserId)
    suspend fun getById(toDoItemId: ToDoItemId): ToDoItem
    suspend fun delete(toDoItemId: ToDoItemId)
}