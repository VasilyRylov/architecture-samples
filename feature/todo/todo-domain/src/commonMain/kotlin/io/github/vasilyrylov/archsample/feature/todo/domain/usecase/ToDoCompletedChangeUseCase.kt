package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.IToDoRepository
import io.github.vasilyrylov.archsample.common.domain.model.ToDoItemId

class ToDoCompletedChangeUseCase(
    private val todoRepository: IToDoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository
) {
    suspend operator fun invoke(id: ToDoItemId) {
        val oldValue = todoRepository.getById(toDoItemId = id)
        val newValue = oldValue.copy(completed = !oldValue.completed)
        todoRepository.save(toDoItem = newValue, userId = authorizedUserRepository.getAuthorizedUserId())
    }
}
