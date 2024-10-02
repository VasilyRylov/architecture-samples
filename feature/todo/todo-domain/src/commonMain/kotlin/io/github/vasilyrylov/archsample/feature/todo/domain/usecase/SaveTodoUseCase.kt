package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.ITodoRepository
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import me.tatarka.inject.annotations.Inject

@Inject
class SaveTodoUseCase(
    private val todoRepository: ITodoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository,
) {
    suspend operator fun invoke(item: TodoItem) {
        return todoRepository.save(item, authorizedUserRepository.getAuthorizedUserId())
    }
}