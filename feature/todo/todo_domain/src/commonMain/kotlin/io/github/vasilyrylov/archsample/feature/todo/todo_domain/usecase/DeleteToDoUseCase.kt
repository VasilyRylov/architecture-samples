package io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase

import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId

class DeleteToDoUseCase(
    private val todoRepository: IToDoRepository,
) {
    suspend operator fun invoke(id: ToDoItemId) {
        return todoRepository.delete(id)
    }
}