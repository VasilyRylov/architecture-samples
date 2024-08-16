package io.github.vasilyrylov.archsample.feature.todo.todo_component

import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import io.github.vasilyrylov.archsample.feature.todo.todo_component.api.IToDoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.IToDoRepository
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter


internal fun createToDoFlowModule(
    componentDependencies: IToDoComponentDependencies
) = module {
    single<RouterHolder<IToDoFlowRouter>> { RouterHolder(null) }
    single {
        componentDependencies.logoutUseCase
    }
    single<IToDoRepository> {
        componentDependencies.toDoRepository
    }
    single {
        componentDependencies.authorizedUserRepository
    }
}