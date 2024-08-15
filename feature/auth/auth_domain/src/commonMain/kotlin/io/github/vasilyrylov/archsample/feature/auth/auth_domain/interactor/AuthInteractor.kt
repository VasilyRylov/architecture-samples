package io.github.vasilyrylov.archsample.feature.auth.auth_domain.interactor

import kotlinx.coroutines.delay
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.data.AuthResult
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.data.RegistrationResult

class AuthInteractor {
    private var registeredMail: String = ""
    private var registeredPassword: String = ""

    suspend fun check(mail: String, password: String): AuthResult {
        delay(1500)
        return if (registeredMail == mail && registeredPassword == password
            && mail.isNotBlank() && password.isNotBlank()
        ) {
            AuthResult.SUCCESS
        } else {
            AuthResult.BAD_CREDENTIAL
        }
    }

    suspend fun register(mail: String, password: String): RegistrationResult {
        delay(1500)
        return if (registeredMail == mail) {
            RegistrationResult.USER_ALREADY_REGISTERED
        } else {
            registeredMail = mail
            registeredPassword = password
            RegistrationResult.SUCCESS
        }
    }
}