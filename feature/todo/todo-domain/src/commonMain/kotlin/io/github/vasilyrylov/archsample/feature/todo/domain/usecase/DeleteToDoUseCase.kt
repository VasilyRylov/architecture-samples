package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IToDoRepository
import io.github.vasilyrylov.archsample.common.domain.model.ToDoItemId


class DeleteToDoUseCase(
    private val todoRepository: IToDoRepository,
) {
    suspend operator fun invoke(id: ToDoItemId) {
        return todoRepository.delete(id)
    }
}