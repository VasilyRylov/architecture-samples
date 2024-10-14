package io.github.vasilyrylov.archsample.user.data.repository.impl

import io.github.vasilyrylov.archsample.common.data.id.UserId
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.user.data.repository.api.IUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.api.model.User
import io.github.vasilyrylov.archsample.user.data.repository.impl.mapper.UserMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

@Inject
internal class UserRepository(
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
