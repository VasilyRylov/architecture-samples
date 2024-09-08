package io.github.vasilyrylov.archsample.feature.root.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature

class AuthCompletionUseCase(
    private val rootFeature: RootFeature,
    private val authorizedUserRepository: IAuthorizedUserRepository
) : IAuthCompletionUseCase {
    override suspend operator fun invoke(name: String) {
        authorizedUserRepository.saveAuthorizedUser(name)
        rootFeature.login()
    }
}