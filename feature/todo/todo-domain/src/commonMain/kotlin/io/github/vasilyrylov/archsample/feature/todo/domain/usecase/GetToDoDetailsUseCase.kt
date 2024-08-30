package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IToDoRepository
import io.github.vasilyrylov.archsample.common.domain.model.ToDoItem
import io.github.vasilyrylov.archsample.common.domain.model.ToDoItemId

class GetToDoDetailsUseCase(
    private val todoRepository: IToDoRepository,
) {
    suspend operator fun invoke(toDoItemId: ToDoItemId): ToDoItem {
        return todoRepository.getById(toDoItemId)
    }
}