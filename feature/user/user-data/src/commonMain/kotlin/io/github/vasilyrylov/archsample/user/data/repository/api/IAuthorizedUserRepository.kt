package io.github.vasilyrylov.archsample.user.data.repository.api

import io.github.vasilyrylov.archsample.common.data.id.UserId

interface IAuthorizedUserRepository {
    suspend fun isUserAuthorized(): Boolean
    suspend fun saveAuthorizedUserId(id: UserId)
    suspend fun getAuthorizedUserId(): UserId
    fun logout()
}
