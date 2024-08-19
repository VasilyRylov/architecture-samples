package io.github.vasilyrylov.archsample.feature.auth.domain.api

interface IAuthCompletionUseCase {
    operator fun invoke(name: String)
}