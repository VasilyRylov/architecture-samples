package io.github.vasilyrylov.archsample.feature.root.root_domain.fsm

import kotlinx.coroutines.delay

class GetCurrentLoggedInUserUseCase {
    suspend operator fun invoke(): String? {
        delay(100)
        return null
    }
}
