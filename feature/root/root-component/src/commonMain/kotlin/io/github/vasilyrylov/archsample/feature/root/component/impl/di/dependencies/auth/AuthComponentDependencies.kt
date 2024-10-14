package io.github.vasilyrylov.archsample.feature.root.component.impl.di.dependencies.auth

import io.github.vasilyrylov.archsample.feature.auth.component.api.AuthFlowCallback
import io.github.vasilyrylov.archsample.feature.auth.component.api.AuthFlowComponent
import me.tatarka.inject.annotations.Inject

@Inject
internal class AuthComponentDependencies(
    callback: Lazy<AuthFlowCallbackImpl>,
) : AuthFlowComponent.Dependencies {
    override val callback: AuthFlowCallback by callback
}
