package io.github.vasilyrylov.archsample.common.domain.interfaces

import io.github.vasilyrylov.archsample.common.domain.model.User

interface IAuthorizedUserRepository {
    suspend fun isUserAuthorized(): Boolean
    suspend fun saveAuthorizedUser(name: String)
    suspend fun getAuthorizedUser(): User
}