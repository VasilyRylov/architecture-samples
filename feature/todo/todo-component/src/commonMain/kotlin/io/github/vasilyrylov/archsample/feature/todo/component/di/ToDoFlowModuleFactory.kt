package io.github.vasilyrylov.archsample.feature.todo.component.di

import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.component.api.IToDoComponentDependencies
import io.github.vasilyrylov.archsample.common.domain.interfaces.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ToDoCompletedChangeUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.ui.api.IToDoFlowRouter
import io.github.vasilyrylov.archsample.todo.data.repository.ToDoRepositoryDemo
import org.koin.core.module.dsl.factoryOf


internal fun createToDoFlowModule(
    componentDependencies: IToDoComponentDependencies
) = module {
    single<RouterHolder<IToDoFlowRouter>> { RouterHolder(null) }
    factoryOf(::ToDoCompletedChangeUseCase)
    factoryOf(::SaveToDoUseCase)
    single<IToDoRepository> {
        ToDoRepositoryDemo()
    }
    single {
        componentDependencies.logoutUseCase
    }
    single {
        componentDependencies.authorizedUserRepository
    }
}