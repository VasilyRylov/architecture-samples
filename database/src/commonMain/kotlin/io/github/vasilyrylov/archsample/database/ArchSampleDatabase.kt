package io.github.vasilyrylov.archsample.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import io.github.vasilyrylov.archsample.database.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 1, exportSchema = true)
@ConstructedBy(ArchSampleDatabaseConstructor::class)
abstract class ArchSampleDatabase : RoomDatabase()

expect object ArchSampleDatabaseConstructor : RoomDatabaseConstructor<ArchSampleDatabase> {
    override fun initialize(): ArchSampleDatabase
}