package io.github.vasilyrylov.archsample.feature.root.root_domain.usecase

import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.ILogoutUseCase

class LogoutUseCase(private val rootFeature: RootFeature) : ILogoutUseCase {
    override fun invoke() {
        rootFeature.logout()
    }
}