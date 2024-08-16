package io.github.vasilyrylov.archsample.todo.todo_data.repository

import io.github.vasilyrylov.archsample.common.common_domain.model.UserId
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ToDoRepositoryDemo : IToDoRepository {

    private val flow = MutableStateFlow<List<ToDoItem>>(listOf())

    override fun observeToDoList(userId: UserId): Flow<List<ToDoItem>> {
        return flow
    }

    override suspend fun save(toDoItem: ToDoItem, userId: UserId) {
        flow.value += toDoItem
    }

    override suspend fun getById(toDoItemId: ToDoItemId): ToDoItem {
        return flow.value.find { it.id == toDoItemId } ?: throw IllegalStateException("ToDo item not found")
    }

    override suspend fun delete(toDoItemId: ToDoItemId) {
        TODO()
    }
}
