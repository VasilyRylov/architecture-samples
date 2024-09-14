package io.github.vasilyrylov.archsample.feature.auth.domain.interfaces

import io.github.vasilyrylov.archsample.common.domain.model.User
import io.github.vasilyrylov.archsample.common.domain.model.UserId

interface IUserRepository {
    suspend fun saveUser(user: User, pass: String)
    suspend fun getUserPasswordByName(name: String): String?
    suspend fun getUserByName(name: String): User?
    suspend fun getUserById(id: UserId): User?
}