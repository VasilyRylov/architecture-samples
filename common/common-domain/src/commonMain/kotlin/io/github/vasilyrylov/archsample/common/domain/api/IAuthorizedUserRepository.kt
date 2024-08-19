package io.github.vasilyrylov.archsample.common.domain.api

import io.github.vasilyrylov.archsample.common.domain.model.User

interface IAuthorizedUserRepository {
    fun isUserAuthorized(): Boolean
    fun saveAuthorizedUser(user: User)
    fun getAuthorizedUser(): User
}