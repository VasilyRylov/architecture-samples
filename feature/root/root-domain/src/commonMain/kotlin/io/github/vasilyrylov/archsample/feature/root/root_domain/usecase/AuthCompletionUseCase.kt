package io.github.vasilyrylov.archsample.feature.root.root_domain.usecase

import io.github.vasilyrylov.archsample.common.common_domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.common_domain.model.User
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.api.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFeature

class AuthCompletionUseCase(
    private val rootFeature: RootFeature,
    private val authorizedUserRepository: IAuthorizedUserRepository
) : IAuthCompletionUseCase {
    override operator fun invoke(name: String) {
        authorizedUserRepository.saveAuthorizedUser(User(name = name))
        rootFeature.login()
    }
}