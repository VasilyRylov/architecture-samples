package io.github.vasilyrylov.archsample.feature.todo.component.details

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.common.domain.model.TodoItem
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.component.details.di.TodoDetailsDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.details.di.create
import io.github.vasilyrylov.archsample.feature.todo.component.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.TodoDetailsViewState

class TodoDetailsComponent(
    componentContext: ComponentContext,
    todoFlowDIComponent: TodoFlowDIComponent,
    itemId: TodoItemId
) : ComponentContext by componentContext {

    private val savedState: TodoDetailsViewState = registerAndGetSavedState(
        key = TODO_DETAILS_SAVED_STATE,
        initialValue = TodoDetailsViewState(
            item = TodoItem(text = "", completed = false)
        ),
        deserialization = TodoDetailsViewState.serializer(),
        serialization = TodoDetailsViewState.serializer()
    ) {
        viewModel.currentState
    }

    private val diComponent = instanceKeeper.getOrCreate {
        TodoDetailsDIComponent::class.create(todoFlowDIComponent, savedState, itemId)
    }

    val viewModel = diComponent.viewModel

    companion object {
        private const val TODO_DETAILS_SAVED_STATE = "TODO_DETAILS_SAVED_STATE"
    }
}
