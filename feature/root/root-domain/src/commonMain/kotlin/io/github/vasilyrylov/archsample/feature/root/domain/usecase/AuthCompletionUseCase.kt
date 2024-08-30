package io.github.vasilyrylov.archsample.feature.root.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.model.User
import io.github.vasilyrylov.archsample.feature.auth.domain.api.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature

class AuthCompletionUseCase(
    private val rootFeature: RootFeature,
    private val authorizedUserRepository: IAuthorizedUserRepository
) : IAuthCompletionUseCase {
    override suspend operator fun invoke(name: String) {
        authorizedUserRepository.saveAuthorizedUser(User(name = name))
        rootFeature.login()
    }
}