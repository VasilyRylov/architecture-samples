package io.github.vasilyrylov.archsample.feature.root.component.di

import io.github.vasilyrylov.archsample.common.data.repository.AuthorizedUserRepositoryDemo
import io.github.vasilyrylov.archsample.common.domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.auth.domain.api.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.ui.api.IRootFlowRouter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.root.ui.RootViewModel
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.GetCurrentLoggedInUserUseCase
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootAsyncWorker
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.domain.usecase.AuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.domain.usecase.LogoutUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

internal fun createRootModule(initialState: RootFSMState) = module {
    singleOf(::RootViewModel)
    factoryOf(::GetCurrentLoggedInUserUseCase)
    singleOf(::RootAsyncWorker)
    single<IAuthorizedUserRepository> { AuthorizedUserRepositoryDemo() }
    single { RouterHolder<IRootFlowRouter>(null) }
    single {
        RootFeature(
            initialState = initialState,
            asyncWorker = get(),
        )
    }
    factoryOf(::LogoutUseCase) bind ILogoutUseCase::class

    factoryOf(::AuthCompletionUseCase) bind IAuthCompletionUseCase::class
}