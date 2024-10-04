package io.github.vasilyrylov.archsample.feature.root.component.di.dependencies

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase
import me.tatarka.inject.annotations.Inject

@Inject
internal class TodoComponentDependencies(
    private val _authorizedUserRepository: Lazy<IAuthorizedUserRepository>,
    private val _logoutUseCase: Lazy<ILogoutUseCase>,
    private val _todoDao: Lazy<TodoDao>,
) : ITodoComponentDependencies {
    override val authorizedUserRepository: IAuthorizedUserRepository
        get() = _authorizedUserRepository.value
    override val logoutUseCase: ILogoutUseCase
        get() = _logoutUseCase.value
    override val todoDao: TodoDao
        get() = _todoDao.value
}