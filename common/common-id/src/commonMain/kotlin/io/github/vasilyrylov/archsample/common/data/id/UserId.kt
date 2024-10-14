package io.github.vasilyrylov.archsample.common.data.id

import com.benasher44.uuid.Uuid
import kotlin.jvm.JvmInline

@JvmInline
value class UserId(val value: Uuid)
