package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.ITodoRepository
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId


class DeleteToDoUseCase(
    private val todoRepository: ITodoRepository,
) {
    suspend operator fun invoke(id: TodoItemId) {
        return todoRepository.delete(id)
    }
}