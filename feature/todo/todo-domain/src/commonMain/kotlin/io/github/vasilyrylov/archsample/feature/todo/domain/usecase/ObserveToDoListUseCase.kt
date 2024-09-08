package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.IToDoRepository
import io.github.vasilyrylov.archsample.common.domain.model.ToDoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class ObserveToDoListUseCase(
    private val todoRepository: IToDoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository
) {
    operator fun invoke(): Flow<List<ToDoItem>> = flow {
        val userId = authorizedUserRepository.getAuthorizedUserId()
        emitAll(todoRepository.observeToDoList(userId))
    }
}