package io.github.vasilyrylov.archsample.feature.todo.todo_component.api

import io.github.vasilyrylov.archsample.common.common_domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.ILogoutUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.IToDoRepository


interface IToDoComponentDependencies {
    val toDoRepository: IToDoRepository
    val authorizedUserRepository: IAuthorizedUserRepository
    val logoutUseCase: ILogoutUseCase
}