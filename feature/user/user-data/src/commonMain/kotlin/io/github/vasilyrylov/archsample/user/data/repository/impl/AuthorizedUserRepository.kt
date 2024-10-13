package io.github.vasilyrylov.archsample.user.data.repository.impl

import io.github.vasilyrylov.archsample.common.data.id.UserId
import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.impl.mapper.UserMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

@Inject
class AuthorizedUserRepository(
    private val preferences: IPreferences,
    private val userDao: UserDao,
) : IAuthorizedUserRepository {
    override suspend fun isUserAuthorized(): Boolean {
        val user = withContext(Dispatchers.IO) {
            val userId = preferences.getString(AUTHORIZED_USER_ID, "")
            userDao.getUserById(userId)
        }
        return user != null
    }

    override suspend fun saveAuthorizedUserId(id: UserId) {
        withContext(Dispatchers.IO) {
            val userFromDatabase = userDao.getUserById(id.value.toString())
            requireNotNull(userFromDatabase)
            preferences.putString(AUTHORIZED_USER_ID, userFromDatabase.id)
        }
    }

    override suspend fun getAuthorizedUserId(): UserId {
        val user = withContext(Dispatchers.IO) {
            val userId = preferences.getString(AUTHORIZED_USER_ID, "")
            userDao.getUserById(userId) ?: error("Authorized user not found")
        }
        return UserMapper.fromDatabase(user).id
    }

    override fun logout() {
        preferences.putString(AUTHORIZED_USER_ID, "")
    }

    companion object {
        private const val AUTHORIZED_USER_ID = "AUTHORIZED_USER_ID"
    }
}
