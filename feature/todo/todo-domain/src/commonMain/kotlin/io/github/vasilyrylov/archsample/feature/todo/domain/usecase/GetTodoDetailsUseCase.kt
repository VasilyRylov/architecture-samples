package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.ITodoRepository
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import me.tatarka.inject.annotations.Inject

@Inject
class GetTodoDetailsUseCase(
    private val todoRepository: ITodoRepository,
) {
    suspend operator fun invoke(id: TodoItemId): TodoItem {
        return todoRepository.getById(id)
    }
}