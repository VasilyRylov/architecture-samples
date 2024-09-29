package io.github.vasilyrylov.archsample.feature.todo.component.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.component.details.di.TodoDetailsDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.details.di.create
import io.github.vasilyrylov.archsample.feature.todo.component.di.TodoFlowDIComponent

class TodoDetailsComponent(
    componentContext: ComponentContext,
    todoFlowDIComponent: TodoFlowDIComponent,
    itemId: TodoItemId
) : ComponentContext by componentContext {

    private val diComponent = instanceKeeper.getOrCreate {
        TodoDetailsDIComponent::class.create(itemId, todoFlowDIComponent)
    }

    val viewModel = diComponent.viewModel
}