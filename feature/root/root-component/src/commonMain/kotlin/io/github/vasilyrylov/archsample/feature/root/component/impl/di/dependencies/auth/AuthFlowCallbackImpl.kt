package io.github.vasilyrylov.archsample.feature.root.component.impl.di.dependencies.auth

import io.github.vasilyrylov.archsample.feature.auth.component.api.AuthFlowCallback
import io.github.vasilyrylov.archsample.feature.root.domain.usecase.AuthCompletionUseCase
import me.tatarka.inject.annotations.Inject

@Inject
internal class AuthFlowCallbackImpl(
    authCompletionUseCase: Lazy<AuthCompletionUseCase>,
) : AuthFlowCallback {

    private val authCompletionUseCase by authCompletionUseCase

    override suspend fun onAuthCompletion(name: String) = authCompletionUseCase()
}
