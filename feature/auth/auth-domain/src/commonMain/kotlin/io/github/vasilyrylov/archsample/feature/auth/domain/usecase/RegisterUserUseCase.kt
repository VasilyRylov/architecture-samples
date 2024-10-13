package io.github.vasilyrylov.archsample.feature.auth.domain.usecase

import io.github.vasilyrylov.archsample.user.data.repository.api.IUserRepository
import io.github.vasilyrylov.archsample.user.data.repository.api.model.User
import me.tatarka.inject.annotations.Inject

enum class RegistrationResult {
    SUCCESS,
    USER_ALREADY_REGISTERED,
    NO_INTERNET
}

@Inject
class RegisterUserUseCase(
    private val userRepository: IUserRepository,
) {
    suspend operator fun invoke(user: User, pass: String): RegistrationResult {
        val existUser = userRepository.getUserByName(user.name)

        val registrationResult = if (existUser != null) {
            RegistrationResult.USER_ALREADY_REGISTERED
        } else {
            userRepository.saveUser(user, pass)
            RegistrationResult.SUCCESS
        }

        return registrationResult
    }
}
