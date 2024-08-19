package io.github.vasilyrylov.archsample.feature.auth.ui.data

data class LoginViewState(
    val name: String,
    val password: String,
    val errorMessage: String?,
    val isAuthenticationInProgress: Boolean,
    val snackBarMessage: String?
) : AuthViewState()
