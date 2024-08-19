package io.github.vasilyrylov.archsample.feature.root.domain.usecase

import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase

class LogoutUseCase(private val rootFeature: RootFeature) : ILogoutUseCase {
    override fun invoke() {
        rootFeature.logout()
    }
}