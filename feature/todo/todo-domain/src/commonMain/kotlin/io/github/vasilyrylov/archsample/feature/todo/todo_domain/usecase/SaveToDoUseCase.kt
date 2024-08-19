package io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase

import io.github.vasilyrylov.archsample.common.common_domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem

class SaveToDoUseCase(
    private val todoRepository: IToDoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository,
) {
    suspend operator fun invoke(toDoItem: ToDoItem) {
        return todoRepository.save(toDoItem, authorizedUserRepository.getAuthorizedUser().id)
    }
}