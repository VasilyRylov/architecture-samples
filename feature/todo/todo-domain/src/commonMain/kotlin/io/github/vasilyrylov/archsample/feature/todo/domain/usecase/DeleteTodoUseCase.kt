package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.todo.data.api.ITodoRepository
import me.tatarka.inject.annotations.Inject

@Inject
class DeleteTodoUseCase(
    private val todoRepository: ITodoRepository,
) {
    suspend operator fun invoke(id: TodoItemId) {
        return todoRepository.delete(id)
    }
}
