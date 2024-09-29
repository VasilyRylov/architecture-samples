package io.github.vasilyrylov.archsample.feature.root.domain.usecase

import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import me.tatarka.inject.annotations.Inject

@Inject
class AuthCompletionUseCase(
    private val rootFeature: RootFeature,
) : IAuthCompletionUseCase {
    override suspend operator fun invoke(name: String) {
        rootFeature.login()
    }
}