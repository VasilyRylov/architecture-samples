package io.github.vasilyrylov.archsample.feature.root.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase

class LogoutUseCase(
    private val rootFeature: RootFeature,
    private val authorizedUserRepository: IAuthorizedUserRepository
) : ILogoutUseCase {
    override fun invoke() {
        authorizedUserRepository.logout()
        rootFeature.logout()
    }
}