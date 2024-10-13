package io.github.vasilyrylov.archsample.feature.root.component.di.dependencies.callback

import io.github.vasilyrylov.archsample.feature.auth.component.AuthFlowCallback
import io.github.vasilyrylov.archsample.feature.root.domain.usecase.AuthCompletionUseCase
import me.tatarka.inject.annotations.Inject

@Inject
class AuthFlowCallbackImpl(
    authCompletionUseCase: Lazy<AuthCompletionUseCase>,
) : AuthFlowCallback {

    private val authCompletionUseCase by authCompletionUseCase

    override suspend fun onAuthCompletion(name: String) = authCompletionUseCase()
}
