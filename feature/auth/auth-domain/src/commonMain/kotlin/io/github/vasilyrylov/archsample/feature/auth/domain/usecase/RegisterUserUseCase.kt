package io.github.vasilyrylov.archsample.feature.auth.domain.usecase

import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IUserRepository
import io.github.vasilyrylov.archsample.common.domain.model.User
import io.github.vasilyrylov.archsample.feature.auth.domain.data.RegistrationResult
import me.tatarka.inject.annotations.Inject

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