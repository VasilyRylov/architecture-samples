package io.github.vasilyrylov.archsample.feature.root.domain.usecase

import io.github.vasilyrylov.archsample.common.data.id.UserId
import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
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
