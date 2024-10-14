package io.github.vasilyrylov.archsample.todo.data.impl.repository

import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.common.data.id.UserId
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.todo.data.api.ITodoRepository
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import io.github.vasilyrylov.archsample.todo.data.impl.mapper.TodoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

@Inject
internal class TodoRepository(
    private val todoDao: TodoDao,
) : ITodoRepository {

    override fun observeTodoList(userId: UserId): Flow<List<TodoItem>> {
        return todoDao
            .observeAllByUserId(userId.value.toString())
            .map { it.map(TodoMapper::fromDatabase) }.flowOn(Dispatchers.IO)
    }

    override suspend fun save(todoItem: TodoItem, userId: UserId) {
        return withContext(Dispatchers.IO) {
            todoDao.save(TodoMapper.toDatabase(todoItem, userId.value.toString()))
        }
    }

    override suspend fun getById(todoItemId: TodoItemId): TodoItem {
        return withContext(Dispatchers.IO) {
            todoDao.getTodoById(todoItemId.value.toString()).let { TodoMapper.fromDatabase(it) }
        }
    }

    override suspend fun delete(todoItemId: TodoItemId) {
        return withContext(Dispatchers.IO) {
            todoDao.delete(todoItemId.value.toString())
        }
    }
}
