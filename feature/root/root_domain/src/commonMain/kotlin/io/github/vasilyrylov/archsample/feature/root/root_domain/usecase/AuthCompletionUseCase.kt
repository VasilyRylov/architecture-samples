package io.github.vasilyrylov.archsample.feature.root.root_domain.usecase

import io.github.vasilyrylov.archsample.feature.auth.auth_domain.api.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFeature

class AuthCompletionUseCase(private val rootFeature: RootFeature) : IAuthCompletionUseCase {
    override operator fun invoke(userId: String) {
        rootFeature.login(userId)
    }
}