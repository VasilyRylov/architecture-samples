package io.github.vasilyrylov.archsample.feature.todo.todo_component.detail

import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.ToDoDetailsViewModel
import org.koin.core.module.dsl.singleOf

internal fun createToDoDetailsModule(itemId: ToDoItemId) = module {
    singleOf(::ToDoDetailsViewModel)
    single {
        itemId
    }
}