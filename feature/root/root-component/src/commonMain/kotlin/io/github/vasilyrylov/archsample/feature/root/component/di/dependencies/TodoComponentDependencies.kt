package io.github.vasilyrylov.archsample.feature.root.component.di.dependencies

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase
import me.tatarka.inject.annotations.Inject

@Inject
internal class TodoComponentDependencies(
    lazyAuthorizedUserRepository: Lazy<IAuthorizedUserRepository>,
    lazyLogoutUseCase: Lazy<ILogoutUseCase>,
    lazyTodoDao: Lazy<TodoDao>,
) : ITodoComponentDependencies {
    override val authorizedUserRepository by lazyAuthorizedUserRepository
    override val logoutUseCase by lazyLogoutUseCase
    override val todoDao by lazyTodoDao
}
