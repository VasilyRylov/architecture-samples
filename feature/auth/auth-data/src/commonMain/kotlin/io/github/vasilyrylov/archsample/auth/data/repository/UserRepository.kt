package io.github.vasilyrylov.archsample.auth.data.repository

import io.github.vasilyrylov.archsample.common.data.mapper.UserMapper
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IUserRepository
import io.github.vasilyrylov.archsample.common.domain.model.User
import io.github.vasilyrylov.archsample.common.domain.model.UserId
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class UserRepository(
    private val userDao: UserDao,
) : IUserRepository {
    override suspend fun saveUser(user: User, pass: String) {
        withContext(Dispatchers.IO) {
            userDao.insert(UserMapper.toDatabase(user, pass))
        }
    }

    override suspend fun getUserPasswordByName(name: String): String? {
        return withContext(Dispatchers.IO) {
            userDao.getUserByName(name)?.pass
        }
    }

    override suspend fun getUserByName(name: String): User? {
        return withContext(Dispatchers.IO) {
            userDao.getUserByName(name)?.let { UserMapper.fromDatabase(it) }
        }
    }

    override suspend fun getUserById(id: UserId): User? {
        return withContext(Dispatchers.IO) {
            userDao.getUserById(id.value.toString())?.let { UserMapper.fromDatabase(it) }
        }
    }
}
