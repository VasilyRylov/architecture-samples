package io.github.vasilyrylov.archsample.feature.auth.component.api

import io.github.vasilyrylov.archsample.feature.auth.domain.api.IAuthCompletionUseCase

interface IAuthComponentDependencies {
    val authCompletionUseCase: IAuthCompletionUseCase
}