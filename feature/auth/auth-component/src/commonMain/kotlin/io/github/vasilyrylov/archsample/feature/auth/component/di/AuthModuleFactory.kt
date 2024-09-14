package io.github.vasilyrylov.archsample.feature.auth.component.di

import io.github.vasilyrylov.archsample.auth.data.repository.UserRepository
import io.github.vasilyrylov.archsample.common.domain.interfaces.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.data.database.dao.UserDao
import io.github.vasilyrylov.archsample.feature.auth.component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFeature
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthAsyncWorker
import io.github.vasilyrylov.archsample.feature.auth.domain.interfaces.IUserRepository
import io.github.vasilyrylov.archsample.feature.auth.domain.usecase.AuthorizeUseCase
import io.github.vasilyrylov.archsample.feature.auth.domain.usecase.RegisterUserUseCase
import io.github.vasilyrylov.archsample.feature.auth.ui.AuthViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

internal fun createAuthModule(
    initialState: AuthFSMState,
    dependencies: IAuthComponentDependencies
) = module {
    singleOf(::AuthViewModel)
    singleOf(::AuthAsyncWorker)
    factoryOf(::RegisterUserUseCase)
    factoryOf(::AuthorizeUseCase)
    singleOf(::UserRepository) bind IUserRepository::class
    factory<IAuthCompletionUseCase> { dependencies.authCompletionUseCase }
    factory<IAuthorizedUserRepository> { dependencies.authorizedUserRepository }
    factory<UserDao> { dependencies.userDao }
    single { AuthFeature(initialState = initialState, asyncWorker = get()) }
}
