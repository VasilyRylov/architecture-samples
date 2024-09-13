package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.ITodoRepository
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem

class SaveToDoUseCase(
    private val todoRepository: ITodoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository,
) {
    suspend operator fun invoke(toDoItem: TodoItem) {
        return todoRepository.save(toDoItem, authorizedUserRepository.getAuthorizedUserId())
    }
}