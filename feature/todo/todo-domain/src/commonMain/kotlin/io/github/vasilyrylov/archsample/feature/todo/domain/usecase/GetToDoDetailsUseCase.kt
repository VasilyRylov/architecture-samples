package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.feature.todo.domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItemId

class GetToDoDetailsUseCase(
    private val todoRepository: IToDoRepository,
) {
    suspend operator fun invoke(toDoItemId: ToDoItemId): ToDoItem {
        return todoRepository.getById(toDoItemId)
    }
}