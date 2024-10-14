package io.github.vasilyrylov.archsample.user.data.repository.api.model

import com.benasher44.uuid.uuid4
import io.github.vasilyrylov.archsample.common.data.id.UserId

data class User(
    val name: String,
    val id: UserId = UserId(uuid4())
)
