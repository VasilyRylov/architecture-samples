package io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase

import io.github.vasilyrylov.archsample.common.common_domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import kotlinx.coroutines.flow.Flow

class ObserveToDoListUseCase(
    private val todoRepository: IToDoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository
) {
    operator fun invoke(): Flow<List<ToDoItem>> {
        return todoRepository.observeToDoList(authorizedUserRepository.getAuthorizedUser().id)
    }
}