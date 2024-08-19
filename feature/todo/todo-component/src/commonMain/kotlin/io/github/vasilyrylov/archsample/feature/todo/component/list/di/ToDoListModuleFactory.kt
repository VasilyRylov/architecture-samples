package io.github.vasilyrylov.archsample.feature.todo.component.list.di

import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.ObserveToDoListUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.ToDoListViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf

internal fun createToDoListModule() = module {
    singleOf(::ToDoListViewModel)
    factoryOf(::ObserveToDoListUseCase)
}