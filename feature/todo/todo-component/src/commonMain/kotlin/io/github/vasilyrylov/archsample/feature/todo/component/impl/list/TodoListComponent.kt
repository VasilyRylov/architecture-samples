package io.github.vasilyrylov.archsample.feature.todo.component.impl.list

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.component.UiComponent
import io.github.vasilyrylov.archsample.feature.todo.component.api.list.TodoListCallback
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.ITodoFlowRouter
import me.tatarka.inject.annotations.Inject


internal interface TodoListComponent : UiComponent {

    interface Factory {
        fun create(context: ComponentContext): TodoListComponent
    }

    @Inject
    class DI(
        router: ITodoFlowRouter,
        callback: TodoListCallback,
    ) {
        val factory: Factory = TodoListComponentImpl.Factory(router, callback)
    }
}
