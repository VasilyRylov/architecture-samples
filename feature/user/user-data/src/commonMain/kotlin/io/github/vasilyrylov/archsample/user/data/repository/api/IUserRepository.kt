package io.github.vasilyrylov.archsample.user.data.repository.api

import io.github.vasilyrylov.archsample.common.data.id.UserId
import io.github.vasilyrylov.archsample.user.data.repository.api.model.User

interface IUserRepository {
    suspend fun saveUser(user: User, pass: String)
    suspend fun getUserPasswordByName(name: String): String?
    suspend fun getUserByName(name: String): User?
    suspend fun getUserById(id: UserId): User?
}
