package io.github.vasilyrylov.archsample.feature.auth.auth_domain.api

interface IAuthCompletionUseCase {
    operator fun invoke(name: String)
}