package io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase

import io.github.vasilyrylov.archsample.common.common_domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId

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
