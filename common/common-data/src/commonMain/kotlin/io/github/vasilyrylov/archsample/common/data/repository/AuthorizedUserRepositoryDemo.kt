package io.github.vasilyrylov.archsample.common.data.repository

import io.github.vasilyrylov.archsample.common.domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.model.User
import kotlin.concurrent.Volatile

class AuthorizedUserRepositoryDemo : IAuthorizedUserRepository {
    @Volatile
    private var authorizedUser: User? = null

    override fun isUserAuthorized(): Boolean {
        return authorizedUser != null
    }

    override fun saveAuthorizedUser(user: User) {
        authorizedUser = user
    }

    override fun getAuthorizedUser(): User {
        return authorizedUser ?: error("User is not authorized")
    }
}
