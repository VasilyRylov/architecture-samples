package io.github.vasilyrylov.archsample.feature.todo.component.impl.list

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.common.component.registerAndGetSavedState
import io.github.vasilyrylov.archsample.feature.todo.component.api.list.TodoListCallback
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.ITodoFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.list.di.TodoListDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.list.di.create
import io.github.vasilyrylov.archsample.feature.todo.ui.api.TodoListScreenDialog
import io.github.vasilyrylov.archsample.feature.todo.ui.api.TodoListViewState
import me.tatarka.inject.annotations.Inject
import me.tatarka.inject.annotations.Provides

internal class TodoListComponentImpl(
    componentContext: ComponentContext,
    router: ITodoFlowRouter,
    callback: TodoListCallback,
) : ComponentContext by componentContext, TodoListComponent {

    @Composable
    override fun Ui() = viewModel.Ui()

    private val savedState: TodoListViewState = registerAndGetSavedState(
        key = TODO_LIST_SAVED_STATE,
        initialValue = TodoListViewState(
            todoItems = listOf(),
            dialog = TodoListScreenDialog.None
        ),
        deserialization = TodoListViewState.serializer(),
        serialization = TodoListViewState.serializer()
    ) {
        viewModel.currentState
    }

    private val diComponent = instanceKeeper.getOrCreate {
        TodoListDIComponent::class.create(savedState, router, callback)
    }

    private val viewModel = diComponent.viewModel

    companion object {
        private const val TODO_LIST_SAVED_STATE = "TODO_LIST_SAVED_STATE"
    }

    @Inject
    class Factory(
        private val router: ITodoFlowRouter,
        private val callback: TodoListCallback,
    ) : TodoListComponent.Factory {

        override fun create(
            context: ComponentContext,
        ) = TodoListComponentImpl(context, router, callback)
    }
}
