package io.github.vasilyrylov.archsample.feature.todo.todo_component.list

import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.ObserveToDoListUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.SaveToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.ToDoCompleteChangeUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.ToDoListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf

internal fun createToDoListModule() = module {
    singleOf(::ToDoListViewModel)
    factoryOf(::ObserveToDoListUseCase)
    factoryOf(::ToDoCompleteChangeUseCase)
    factoryOf(::SaveToDoUseCase)
}