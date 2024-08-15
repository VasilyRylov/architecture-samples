package io.github.vasilyrylov.archsample.feature.todo.todo_component

import io.github.vasilyrylov.archsample.common.common_ui.RouterHolder
import org.koin.dsl.module
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.ToDoListViewModel
import org.koin.core.module.dsl.singleOf

internal fun createToDoFlowModule() = module {
    singleOf(::ToDoListViewModel) // TODO должно объявляться в ToDoListComponent
    single { RouterHolder<IToDoFlowRouter>(null) }
}