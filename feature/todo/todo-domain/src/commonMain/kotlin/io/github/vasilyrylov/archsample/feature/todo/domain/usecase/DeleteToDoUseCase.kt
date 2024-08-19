package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.feature.todo.domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItemId

class DeleteToDoUseCase(
    private val todoRepository: IToDoRepository,
) {
    suspend operator fun invoke(id: ToDoItemId) {
        return todoRepository.delete(id)
    }
}