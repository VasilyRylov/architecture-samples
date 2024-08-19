package io.github.vasilyrylov.archsample.feature.auth.domain.interactor

import kotlinx.coroutines.delay
import io.github.vasilyrylov.archsample.feature.auth.domain.data.AuthResult
import io.github.vasilyrylov.archsample.feature.auth.domain.data.RegistrationResult

class AuthInteractor {
    private var registeredName: String = ""
    private var registeredPassword: String = ""

    suspend fun check(name: String, password: String): AuthResult {
        delay(1500)
        return if (registeredName == name && registeredPassword == password
            && name.isNotBlank() && password.isNotBlank()
        ) {
            AuthResult.SUCCESS
        } else {
            AuthResult.BAD_CREDENTIAL
        }
    }

    suspend fun register(name: String, password: String): RegistrationResult {
        delay(1500)
        return if (registeredName == name) {
            RegistrationResult.USER_ALREADY_REGISTERED
        } else {
            registeredName = name
            registeredPassword = password
            RegistrationResult.SUCCESS
        }
    }
}