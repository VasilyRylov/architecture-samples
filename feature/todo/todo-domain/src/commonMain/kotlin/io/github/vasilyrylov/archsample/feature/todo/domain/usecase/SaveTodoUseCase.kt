package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.todo.data.api.ITodoRepository
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
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
