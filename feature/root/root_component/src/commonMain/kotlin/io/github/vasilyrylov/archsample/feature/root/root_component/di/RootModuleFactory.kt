package io.github.vasilyrylov.archsample.feature.root.root_component.di

import io.github.vasilyrylov.archsample.common.common_data.repository.AuthorizedUserRepositoryDemo
import io.github.vasilyrylov.archsample.common.common_domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.api.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.root_ui.api.IRootFlowRouter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.root.root_ui.RootViewModel
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.GetCurrentLoggedInUserUseCase
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootAsyncWorker
import io.github.vasilyrylov.archsample.feature.root.root_domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.root_domain.usecase.AuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.root_domain.usecase.LogoutUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.ILogoutUseCase
import org.koin.core.module.dsl.singleOf

internal fun createRootModule(initialState: RootFSMState) = module {
    singleOf(::RootViewModel)
    factoryOf(::GetCurrentLoggedInUserUseCase)
    singleOf(::RootAsyncWorker)
    single<IAuthorizedUserRepository> { AuthorizedUserRepositoryDemo() }
    single { RouterHolder<IRootFlowRouter>(null) }
    single {
        RootFeature(
            initialState,
            get(),
        )
    }
    factory<ILogoutUseCase> {
        LogoutUseCase(get())
    }
    factory<IAuthCompletionUseCase> {
        AuthCompletionUseCase(get(), get())
    }
}