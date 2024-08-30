package io.github.vasilyrylov.archsample.feature.auth.domain.api

interface IAuthCompletionUseCase {
    suspend operator fun invoke(name: String)
}