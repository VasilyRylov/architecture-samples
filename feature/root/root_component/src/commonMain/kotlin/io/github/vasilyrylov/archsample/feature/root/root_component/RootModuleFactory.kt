package io.github.vasilyrylov.archsample.feature.root.root_component

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
import org.koin.core.module.dsl.singleOf

internal fun createRootModule(initialState: RootFSMState) = module {
    singleOf(::RootViewModel)
    factoryOf(::GetCurrentLoggedInUserUseCase)
    singleOf(::RootAsyncWorker)
    single { RouterHolder<IRootFlowRouter>(null) }
    single {
        RootFeature(
            initialState,
            get(),
        )
    }
    factory<IAuthCompletionUseCase> {
        AuthCompletionUseCase(get())
    }
}