package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.ITodoRepository
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import me.tatarka.inject.annotations.Inject

@Inject
class ObserveTodoListUseCase(
    private val todoRepository: ITodoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository
) {
    operator fun invoke(): Flow<List<TodoItem>> = flow {
        val userId = authorizedUserRepository.getAuthorizedUserId()
        emitAll(todoRepository.observeTodoList(userId))
    }
}