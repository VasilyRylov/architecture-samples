package io.github.vasilyrylov.archsample.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.vasilyrylov.archsample.data.database.entity.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(item: TodoEntity)

    @Query("DELETE FROM TodoEntity WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    suspend fun getTodoById(id: String): TodoEntity

    @Query("SELECT * FROM TodoEntity")
    fun getAllAsFlow(): Flow<List<TodoEntity>>
}