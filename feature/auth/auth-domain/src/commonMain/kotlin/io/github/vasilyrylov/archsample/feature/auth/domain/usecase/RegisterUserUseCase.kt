package io.github.vasilyrylov.archsample.feature.auth.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.IUserRepository
import io.github.vasilyrylov.archsample.common.domain.model.User

class RegisterUserUseCase(
    private val userRepository: IUserRepository,
    private val authorizedIUserRepository: IAuthorizedUserRepository
) {
    suspend operator fun invoke(user: User, pass: String) {
        userRepository.saveUser(user, pass)
        authorizedIUserRepository.saveAuthorizedUser(user)
    }
}