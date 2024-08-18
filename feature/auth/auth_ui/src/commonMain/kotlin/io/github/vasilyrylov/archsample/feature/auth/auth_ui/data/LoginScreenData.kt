package io.github.vasilyrylov.archsample.feature.auth.auth_ui.data

data class LoginScreenData(
    val name: String,
    val password: String,
    val errorMessage: String?,
    val isAuthenticationInProgress: Boolean,
    val snackBarMessage: String?
) : AuthScreenData()