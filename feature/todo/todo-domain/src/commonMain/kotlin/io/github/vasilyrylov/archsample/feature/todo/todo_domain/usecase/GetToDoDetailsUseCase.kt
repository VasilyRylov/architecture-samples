package io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase

import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId

class GetToDoDetailsUseCase(
    private val todoRepository: IToDoRepository,
) {
    suspend operator fun invoke(toDoItemId: ToDoItemId): ToDoItem {
        return todoRepository.getById(toDoItemId)
    }
}