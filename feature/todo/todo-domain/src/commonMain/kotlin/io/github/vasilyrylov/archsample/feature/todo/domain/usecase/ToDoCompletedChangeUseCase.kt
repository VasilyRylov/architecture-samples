package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItemId

class ToDoCompletedChangeUseCase(
    private val todoRepository: IToDoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository
) {
    suspend operator fun invoke(id: ToDoItemId) {
        val oldValue = todoRepository.getById(toDoItemId = id)
        val newValue = oldValue.copy(completed = !oldValue.completed)
        todoRepository.save(toDoItem = newValue, userId = authorizedUserRepository.getAuthorizedUser().id)
    }
}
