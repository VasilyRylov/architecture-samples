package io.github.vasilyrylov.archsample.common.data.mapper

import com.benasher44.uuid.uuidFrom
import io.github.vasilyrylov.archsample.common.domain.model.User
import io.github.vasilyrylov.archsample.common.domain.model.UserId
import io.github.vasilyrylov.archsample.data.database.entity.UserEntity

object UserMapper {
    fun toDatabase(user: User, pass: String): UserEntity {
        return UserEntity(
            id = user.id.value.toString(),
            name = user.name,
            pass = pass
        )
    }

    fun fromDatabase(user: UserEntity): User {
        return User(
            id = UserId(uuidFrom(user.id)),
            name = user.name
        )
    }
}