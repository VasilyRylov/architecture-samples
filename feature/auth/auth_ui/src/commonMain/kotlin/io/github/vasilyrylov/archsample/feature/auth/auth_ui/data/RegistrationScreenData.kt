package io.github.vasilyrylov.archsample.feature.auth.auth_ui.data

data class RegistrationScreenData(
    val mail: String,
    val password: String,
    val repeatedPassword: String,
    val errorMessage: String?,
    val isRegistrationInProgress: Boolean,
    val isConfirmationRequested: Boolean,
) : AuthScreenData()