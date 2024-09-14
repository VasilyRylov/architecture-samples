package io.github.vasilyrylov.archsample.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.vasilyrylov.archsample.data.database.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insert(item: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE id IS :id")
    suspend fun getUserById(id: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE name IS :name")
    suspend fun getUserByName(name: String): UserEntity?
}
