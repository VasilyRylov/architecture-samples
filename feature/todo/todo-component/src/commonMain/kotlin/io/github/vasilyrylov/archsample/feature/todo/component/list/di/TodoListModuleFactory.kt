package io.github.vasilyrylov.archsample.feature.todo.component.list.di

import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ObserveTodoListUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf

internal fun createTodoListModule() = module {
    singleOf(::TodoListViewModel)
    factoryOf(::ObserveTodoListUseCase)
}