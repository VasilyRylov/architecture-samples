package io.github.vasilyrylov.archsample.feature.todo.component.details.di

import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.DeleteToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.GetToDoDetailsUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.ToDoDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf

internal fun createToDoDetailsModule(itemId: TodoItemId) = module {
    singleOf(::ToDoDetailsViewModel)
    factoryOf(::DeleteToDoUseCase)
    factoryOf(::GetToDoDetailsUseCase)
    single {
        itemId
    }
}