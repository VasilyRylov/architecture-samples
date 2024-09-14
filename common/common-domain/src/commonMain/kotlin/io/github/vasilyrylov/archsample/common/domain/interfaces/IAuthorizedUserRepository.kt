package io.github.vasilyrylov.archsample.common.domain.interfaces

import io.github.vasilyrylov.archsample.common.domain.model.UserId

interface IAuthorizedUserRepository {
    suspend fun isUserAuthorized(): Boolean
    suspend fun saveAuthorizedUserId(id: UserId)
    suspend fun getAuthorizedUserId(): UserId
    fun logout()
}