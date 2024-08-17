package io.github.vasilyrylov.archsample.common.common_domain.api

import io.github.vasilyrylov.archsample.common.common_domain.model.User

interface IAuthorizedUserRepository {
    fun isUserAuthorized(): Boolean
    fun saveAuthorizedUser(user: User)
    fun getAuthorizedUser(): User
}