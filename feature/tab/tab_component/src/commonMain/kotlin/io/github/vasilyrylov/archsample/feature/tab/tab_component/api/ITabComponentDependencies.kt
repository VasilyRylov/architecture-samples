package io.github.vasilyrylov.archsample.feature.tab.tab_component.api

import io.github.vasilyrylov.archsample.common.common_domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.ILogoutUseCase

interface ITabComponentDependencies {
    val authorizedUserRepository: IAuthorizedUserRepository
    val logoutUseCase: ILogoutUseCase
}