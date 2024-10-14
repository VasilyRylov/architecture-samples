package io.github.vasilyrylov.archsample.feature.auth.domain.usecase

import io.github.vasilyrylov.archsample.user.data.repository.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.api.IUserRepository
import me.tatarka.inject.annotations.Inject

enum class AuthResult {
    SUCCESS,
    BAD_CREDENTIAL,
    NO_INTERNET
}

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
