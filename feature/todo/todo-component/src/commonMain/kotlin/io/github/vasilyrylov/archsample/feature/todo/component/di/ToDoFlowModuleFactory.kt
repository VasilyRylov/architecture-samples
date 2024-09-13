package io.github.vasilyrylov.archsample.feature.todo.component.di

import io.github.vasilyrylov.archsample.common.ui.navigation.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.common.domain.interfaces.ITodoRepository
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ToDoCompletedChangeUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.ui.api.IToDoFlowRouter
import io.github.vasilyrylov.archsample.todo.data.repository.TodoRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind


internal fun createTodoFlowModule(
    componentDependencies: ITodoComponentDependencies
) = module {
    single<RouterHolder<IToDoFlowRouter>> { RouterHolder(null) }
    factoryOf(::ToDoCompletedChangeUseCase)
    factoryOf(::SaveToDoUseCase)
    singleOf(::TodoRepository) bind ITodoRepository::class
    factory {
        componentDependencies.logoutUseCase
    }
    factory {
        componentDependencies.authorizedUserRepository
    }
    factory {
        componentDependencies.database
    }
}