package io.github.vasilyrylov.archsample.feature.todo.component.list

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.component.createChildScope
import io.github.vasilyrylov.archsample.common.component.createViewModel
import io.github.vasilyrylov.archsample.feature.todo.component.list.di.createToDoListModule
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.ToDoListViewModel
import org.koin.core.scope.Scope

class ToDoListComponent(
    componentContext: ComponentContext,
    parentScope: Scope,
) : ComponentContext by componentContext {

    private val koinScope = createChildScope(parentScope, listOf(createToDoListModule()))

    @Suppress("UNUSED")
    val viewModel = createViewModel<ToDoListViewModel>(koinScope)
}