package io.github.vasilyrylov.archsample.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val pass: String, // don't do this in real app, use hash
)