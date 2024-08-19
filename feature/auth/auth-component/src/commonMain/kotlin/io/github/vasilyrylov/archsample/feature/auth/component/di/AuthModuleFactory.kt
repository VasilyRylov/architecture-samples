package io.github.vasilyrylov.archsample.feature.auth.component.di

import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.domain.api.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthAsyncWorker
import io.github.vasilyrylov.archsample.feature.auth.domain.interactor.AuthInteractor
import io.github.vasilyrylov.archsample.feature.auth.ui.AuthViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

internal fun createAuthModule(
    initialState: AuthFSMState,
    dependencies: IAuthComponentDependencies
) = module {
    singleOf(::AuthViewModel)
    singleOf(::AuthAsyncWorker)
    factoryOf(::AuthInteractor)
    factory<IAuthCompletionUseCase> { dependencies.authCompletionUseCase }
    single { AuthFeature(initialState = initialState, asyncWorker = get()) }
}
