package io.github.vasilyrylov.archsample.feature.todo.todo_component

import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.todo_component.api.IToDoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.IToDoRepository
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.ToDoCompletedChangeUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter
import io.github.vasilyrylov.archsample.todo.todo_data.repository.ToDoRepositoryDemo
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