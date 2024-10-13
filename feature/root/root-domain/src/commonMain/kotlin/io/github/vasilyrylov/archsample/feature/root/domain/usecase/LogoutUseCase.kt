package io.github.vasilyrylov.archsample.feature.root.domain.usecase

import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
import me.tatarka.inject.annotations.Inject

@Inject
class LogoutUseCase(
    private val rootFeature: RootFeature,
    private val authorizedUserRepository: IAuthorizedUserRepository
) {
    operator fun invoke() {
        authorizedUserRepository.logout()
        rootFeature.logout()
    }
}
