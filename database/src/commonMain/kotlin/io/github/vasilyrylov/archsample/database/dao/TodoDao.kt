package io.github.vasilyrylov.archsample.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.github.vasilyrylov.archsample.database.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(item: TodoEntity)

    @Query("SELECT count(*) FROM TodoEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM TodoEntity")
    fun getAllAsFlow(): Flow<List<TodoEntity>>
}