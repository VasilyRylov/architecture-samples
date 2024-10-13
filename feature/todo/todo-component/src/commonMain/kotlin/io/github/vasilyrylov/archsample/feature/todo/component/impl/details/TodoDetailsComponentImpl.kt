package io.github.vasilyrylov.archsample.feature.todo.component.impl.details

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.component.impl.details.di.TodoDetailsDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.details.di.create
import io.github.vasilyrylov.archsample.feature.todo.component.impl.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.model.TodoDetailsViewState
import io.github.vasilyrylov.archsample.todo.data.api.model.TodoItem
import me.tatarka.inject.annotations.Inject

internal class TodoDetailsComponentImpl(
    componentContext: ComponentContext,
    itemId: TodoItemId,
    parent: TodoFlowDIComponent,
) : ComponentContext by componentContext, TodoDetailsComponent {

    @Composable
    override fun Ui() = viewModel.Ui()

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
        TodoDetailsDIComponent::class.create(parent, savedState, itemId)
    }

    private val viewModel = diComponent.viewModel

    companion object {
        private const val TODO_DETAILS_SAVED_STATE = "TODO_DETAILS_SAVED_STATE"
    }

    @Inject
    class Factory(
        private val parent: TodoFlowDIComponent,
    ) : TodoDetailsComponent.Factory {

        override fun create(
            context: ComponentContext,
            itemId: TodoItemId
        ) = TodoDetailsComponentImpl(context, itemId, parent)
    }
}
