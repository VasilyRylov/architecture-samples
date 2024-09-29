package io.github.vasilyrylov.archsample.feature.todo.component.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.feature.todo.component.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.list.di.TodoListDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.list.di.create

class TodoListComponent(
    componentContext: ComponentContext,
    todoFlowDIComponent: TodoFlowDIComponent,
) : ComponentContext by componentContext {

    private val diComponent = instanceKeeper.getOrCreate {
        TodoListDIComponent::class.create(todoFlowDIComponent)
    }

    val viewModel = diComponent.viewModel
}