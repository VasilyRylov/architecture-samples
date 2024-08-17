package io.github.vasilyrylov.archsample.feature.todo.todo_component.details

import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.DeleteToDoUseCase
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.usecase.GetToDoDetailsUseCase
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.ToDoDetailsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf

internal fun createToDoDetailsModule(itemId: ToDoItemId) = module {
    singleOf(::ToDoDetailsViewModel)
    factoryOf(::DeleteToDoUseCase)
    factoryOf(::GetToDoDetailsUseCase)
    single {
        itemId
    }
}