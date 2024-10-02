package io.github.vasilyrylov.archsample.feature.root.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.model.UserId
import me.tatarka.inject.annotations.Inject

@Inject
class GetCurrentLoggedInUserUseCase(
    private val authorizedUserRepository: IAuthorizedUserRepository
) {
    suspend operator fun invoke(): UserId? {
        return if (authorizedUserRepository.isUserAuthorized()) {
            authorizedUserRepository.getAuthorizedUserId()
        } else {
            null
        }
    }
}
