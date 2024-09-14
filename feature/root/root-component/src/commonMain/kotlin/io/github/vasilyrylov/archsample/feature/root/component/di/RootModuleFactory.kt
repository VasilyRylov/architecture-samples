package io.github.vasilyrylov.archsample.feature.root.component.di

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.common.data.repository.AuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies
import io.github.vasilyrylov.archsample.feature.root.ui.api.IRootFlowRouter
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFeature
import io.github.vasilyrylov.archsample.feature.root.ui.RootViewModel
import io.github.vasilyrylov.archsample.feature.root.domain.usecase.GetCurrentLoggedInUserUseCase
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootAsyncWorker
import io.github.vasilyrylov.archsample.feature.root.domain.fsm.RootFSMState
import io.github.vasilyrylov.archsample.feature.root.domain.usecase.AuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.domain.usecase.LogoutUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.api.ILogoutUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

internal fun createRootModule(
    initialState: RootFSMState,
    dependencies: IRootComponentDependencies
) =
    module {
        singleOf(::RootViewModel)
        factoryOf(::GetCurrentLoggedInUserUseCase)
        singleOf(::RootAsyncWorker)
        singleOf(::AuthorizedUserRepository) bind IAuthorizedUserRepository::class
        single { RouterHolder<IRootFlowRouter>(null) }
        single {
            RootFeature(
                initialState = initialState,
                asyncWorker = get(),
            )
        }
        factoryOf(::LogoutUseCase) bind ILogoutUseCase::class

        factoryOf(::AuthCompletionUseCase) bind IAuthCompletionUseCase::class
        factory<IPreferences> { dependencies.preferences }
        factory<UserDao> { dependencies.userDao }
        factory<TodoDao> { dependencies.todoDao }
    }