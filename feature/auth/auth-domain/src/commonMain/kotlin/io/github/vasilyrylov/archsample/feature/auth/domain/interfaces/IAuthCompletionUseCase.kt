package io.github.vasilyrylov.archsample.feature.auth.domain.interfaces

interface IAuthCompletionUseCase {
    suspend operator fun invoke(name: String)
}