package io.github.vasilyrylov.archsample.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import io.github.vasilyrylov.archsample.data.database.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Upsert
    suspend fun save(item: TodoEntity)

    @Query("DELETE FROM TodoEntity WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    suspend fun getTodoById(id: String): TodoEntity

    @Query("SELECT * FROM TodoEntity WHERE userId = :userId")
    fun observeAllByUserId(userId: String): Flow<List<TodoEntity>>
}