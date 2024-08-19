package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.model.ToDoItem
import kotlinx.coroutines.flow.Flow

class ObserveToDoListUseCase(
    private val todoRepository: IToDoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository
) {
    operator fun invoke(): Flow<List<ToDoItem>> {
        return todoRepository.observeToDoList(authorizedUserRepository.getAuthorizedUser().id)
    }
}