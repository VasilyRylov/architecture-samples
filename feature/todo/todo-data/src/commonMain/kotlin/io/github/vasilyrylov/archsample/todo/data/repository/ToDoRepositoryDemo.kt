package io.github.vasilyrylov.archsample.todo.data.repository

import io.github.vasilyrylov.archsample.common.domain.model.UserId
import io.github.vasilyrylov.archsample.feature.todo.domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItemId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class ToDoRepositoryDemo : IToDoRepository {

    private val flow = MutableSharedFlow<List<ToDoItem>>()
    private val todoList = mutableListOf<ToDoItem>()

    override fun observeToDoList(userId: UserId): Flow<List<ToDoItem>> {
        return flow
    }

    override suspend fun save(toDoItem: ToDoItem, userId: UserId) {
        val index = todoList.indexOfFirst { it.id == toDoItem.id }
        if (index >= 0) {
            todoList[index] = toDoItem
        } else {
            todoList.add(toDoItem)
        }
        flow.emit(todoList.toList())
    }

    override suspend fun getById(toDoItemId: ToDoItemId): ToDoItem {
        return todoList.find { it.id == toDoItemId } ?: error("ToDo item not found")
    }

    override suspend fun delete(toDoItemId: ToDoItemId) {
        val index = todoList.indexOfFirst { it.id == toDoItemId }
        if (index >= 0) {
            todoList.removeAt(index)
            flow.emit(todoList.toList())
        } else {
            error("ToDo item not found")
        }
    }
}
