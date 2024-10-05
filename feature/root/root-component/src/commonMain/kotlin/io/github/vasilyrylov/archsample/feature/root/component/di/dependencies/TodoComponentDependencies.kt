package io.github.vasilyrylov.archsample.feature.root.component.di.dependencies

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase
import me.tatarka.inject.annotations.Inject

@Inject
internal class TodoComponentDependencies(
    private val lazyAuthorizedUserRepository: Lazy<IAuthorizedUserRepository>,
    private val lazyLogoutUseCase: Lazy<ILogoutUseCase>,
    private val lazyTodoDao: Lazy<TodoDao>,
) : ITodoComponentDependencies {
    override val authorizedUserRepository: IAuthorizedUserRepository
        get() = lazyAuthorizedUserRepository.value
    override val logoutUseCase: ILogoutUseCase
        get() = lazyLogoutUseCase.value
    override val todoDao: TodoDao
        get() = lazyTodoDao.value
}