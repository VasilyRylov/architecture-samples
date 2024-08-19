package io.github.vasilyrylov.archsample.feature.auth.ui.data

data class RegistrationViewState(
    val name: String,
    val password: String,
    val repeatedPassword: String,
    val errorMessage: String?,
    val isRegistrationInProgress: Boolean,
    val isConfirmationRequested: Boolean,
) : AuthViewState()
