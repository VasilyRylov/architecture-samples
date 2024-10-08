package io.github.vasilyrylov.archsample.feature.auth.domain.usecase

import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IUserRepository
import io.github.vasilyrylov.archsample.feature.auth.domain.data.AuthResult
import me.tatarka.inject.annotations.Inject

@Inject
class AuthorizeUseCase(
    private val userRepository: IUserRepository,
    private val authorizedIUserRepository: IAuthorizedUserRepository
) {
    suspend operator fun invoke(name: String, password: String): AuthResult {
        val user = userRepository.getUserByName(name)
        val existUserPassword = user?.let { userRepository.getUserPasswordByName(user.name) }
        val authResult = if (password == existUserPassword) {
            authorizedIUserRepository.saveAuthorizedUserId(user.id)
            AuthResult.SUCCESS
        } else {
            AuthResult.BAD_CREDENTIAL
        }
        return authResult
    }
}