package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.ITodoRepository
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId

class GetToDoDetailsUseCase(
    private val todoRepository: ITodoRepository,
) {
    suspend operator fun invoke(toDoItemId: TodoItemId): TodoItem {
        return todoRepository.getById(toDoItemId)
    }
}