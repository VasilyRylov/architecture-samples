package io.github.vasilyrylov.archsample.feature.todo.component.di

import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.common.domain.interfaces.ITodoRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.TodoCompletedChangeUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.ui.api.ITodoFlowRouter
import io.github.vasilyrylov.archsample.todo.data.repository.TodoRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind


internal fun createTodoFlowModule(
    componentDependencies: ITodoComponentDependencies
) = module {
    factoryOf(::TodoCompletedChangeUseCase)
    factoryOf(::SaveTodoUseCase)
    singleOf(::TodoRepository) bind ITodoRepository::class
    factory {
        componentDependencies.logoutUseCase
    }
    factory {
        componentDependencies.authorizedUserRepository
    }
    factory {
        componentDependencies.todoDao
    }
    single<RouterHolder<ITodoFlowRouter>> { RouterHolder() }
}