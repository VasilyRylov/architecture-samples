package io.github.vasilyrylov.archsample.common.data.repository

import io.github.vasilyrylov.archsample.common.data.mapper.UserMapper
import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.model.User
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class AuthorizedUserRepository(
    private val preferences: IPreferences,
    private val database: ArchSampleDatabase,
) : IAuthorizedUserRepository {
    override suspend fun isUserAuthorized(): Boolean {
        val user = withContext(Dispatchers.IO) {
            val userId = preferences.getString(AUTHORIZED_USER_ID, "")
            database.getUserDao().getUserById(userId)
        }
        return user != null
    }

    override suspend fun saveAuthorizedUser(user: User) {
        withContext(Dispatchers.IO) {
            val userId = user.id.value.toString()
            val userFromDatabase = database.getUserDao().getUserById(userId)
            requireNotNull(userFromDatabase)
            preferences.putString(AUTHORIZED_USER_ID, userId)
        }
    }

    override suspend fun getAuthorizedUser(): User {
        val user = withContext(Dispatchers.IO) {
            val userId = preferences.getString(AUTHORIZED_USER_ID, "")
            database.getUserDao().getUserById(userId) ?: error("Authorized user not found")
        }
        return UserMapper.fromDatabase(user)
    }

    companion object {
        private const val AUTHORIZED_USER_ID = "AUTHORIZED_USER_ID"
    }
}