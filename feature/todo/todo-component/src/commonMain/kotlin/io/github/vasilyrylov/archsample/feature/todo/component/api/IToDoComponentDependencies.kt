package io.github.vasilyrylov.archsample.feature.todo.component.api

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase


interface IToDoComponentDependencies {
    val authorizedUserRepository: IAuthorizedUserRepository
    val logoutUseCase: ILogoutUseCase
}