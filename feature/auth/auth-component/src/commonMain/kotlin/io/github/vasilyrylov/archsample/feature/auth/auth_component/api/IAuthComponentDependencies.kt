package io.github.vasilyrylov.archsample.feature.auth.auth_component.api

import io.github.vasilyrylov.archsample.feature.auth.auth_domain.api.IAuthCompletionUseCase

interface IAuthComponentDependencies {
    val authCompletionUseCase: IAuthCompletionUseCase
}