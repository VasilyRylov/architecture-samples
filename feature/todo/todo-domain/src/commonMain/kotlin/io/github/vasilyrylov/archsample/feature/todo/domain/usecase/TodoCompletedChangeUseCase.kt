package io.github.vasilyrylov.archsample.feature.todo.domain.usecase

import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.todo.data.api.ITodoRepository
import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
import me.tatarka.inject.annotations.Inject

@Inject
class TodoCompletedChangeUseCase(
    private val todoRepository: ITodoRepository,
    private val authorizedUserRepository: IAuthorizedUserRepository
) {
    suspend operator fun invoke(id: TodoItemId) {
        val oldValue = todoRepository.getById(todoItemId = id)
        val newValue = oldValue.copy(completed = !oldValue.completed)
        todoRepository.save(
            todoItem = newValue,
            userId = authorizedUserRepository.getAuthorizedUserId()
        )
    }
}
