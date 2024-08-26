package io.github.vasilyrylov.archsample.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(
    @PrimaryKey val id: String,
    val text: String,
    val completed: Boolean
)