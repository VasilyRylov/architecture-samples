package io.github.vasilyrylov.archsample.feature.auth.component.api

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase

interface IAuthComponentDependencies {
    val authCompletionUseCase: IAuthCompletionUseCase
    val authorizedUserRepository: IAuthorizedUserRepository
    val userDao: UserDao
}