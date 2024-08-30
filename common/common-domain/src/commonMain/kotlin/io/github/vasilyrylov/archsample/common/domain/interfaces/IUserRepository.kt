package io.github.vasilyrylov.archsample.common.domain.interfaces

import io.github.vasilyrylov.archsample.common.domain.model.User
import io.github.vasilyrylov.archsample.common.domain.model.UserId

interface IUserRepository {
    suspend fun saveUser(user: User)
    suspend fun getUserByName(name: String): User?
    suspend fun getUserById(id: UserId): User?
}