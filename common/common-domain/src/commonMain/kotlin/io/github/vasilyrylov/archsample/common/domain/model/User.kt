package io.github.vasilyrylov.archsample.common.domain.model

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

data class User(
    val name: String,
    val id: UserId = UserId(uuid4())
)

data class UserId(val value: Uuid)
