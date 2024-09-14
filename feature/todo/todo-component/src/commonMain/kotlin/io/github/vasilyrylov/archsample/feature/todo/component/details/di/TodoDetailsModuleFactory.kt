package io.github.vasilyrylov.archsample.feature.todo.component.details.di

import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.DeleteTodoUseCase
import io.github.vasilyrylov.archsample.feature.todo.domain.usecase.GetTodoDetailsUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.TodoDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf

internal fun createTodoDetailsModule(itemId: TodoItemId) = module {
    singleOf(::TodoDetailsViewModel)
    factoryOf(::DeleteTodoUseCase)
    factoryOf(::GetTodoDetailsUseCase)
    single {
        itemId
    }
}