package io.github.vasilyrylov.archsample.feature.auth.auth_component

import io.github.vasilyrylov.archsample.feature.auth.auth_component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.api.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.fsm.AuthAsyncWorker
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.interactor.AuthInteractor
import io.github.vasilyrylov.archsample.feature.auth.auth_ui.AuthViewModel
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
    factory<IAuthCompletionUseCase> {
        dependencies.authCompletionUseCase
    }
    single {
        AuthFeature(
            initialState,
            get(),
        )
    }
}