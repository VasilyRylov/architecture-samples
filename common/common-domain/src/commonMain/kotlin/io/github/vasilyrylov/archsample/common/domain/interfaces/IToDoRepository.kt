package io.github.vasilyrylov.archsample.common.domain.interfaces

import io.github.vasilyrylov.archsample.common.domain.model.ToDoItem
import io.github.vasilyrylov.archsample.common.domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.common.domain.model.UserId
import kotlinx.coroutines.flow.Flow

interface IToDoRepository {
    fun observeToDoList(userId: UserId): Flow<List<ToDoItem>>
    suspend fun save(toDoItem: ToDoItem, userId: UserId)
    suspend fun getById(toDoItemId: ToDoItemId): ToDoItem
    suspend fun delete(toDoItemId: ToDoItemId)
}