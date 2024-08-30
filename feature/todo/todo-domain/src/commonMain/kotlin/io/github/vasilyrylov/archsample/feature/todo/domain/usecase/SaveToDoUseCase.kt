package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.IToDoRepository
import io.github.vasilyrylov.archsample.common.domain.model.ToDoItem

class SaveToDoUseCase(
    private val todoRepository: IToDoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository,
) {
    suspend operator fun invoke(toDoItem: ToDoItem) {
        return todoRepository.save(toDoItem, authorizedUserRepository.getAuthorizedUser().id)
    }
}