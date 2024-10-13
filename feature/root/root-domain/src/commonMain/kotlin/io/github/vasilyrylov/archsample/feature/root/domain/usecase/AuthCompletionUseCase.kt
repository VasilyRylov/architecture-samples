package io.github.vasilyrylov.archsample.feature.root.domain.usecase

import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import me.tatarka.inject.annotations.Inject

@Inject
class AuthCompletionUseCase(
    private val rootFeature: RootFeature,
) {
    operator fun invoke() {
        rootFeature.login()
    }
}
