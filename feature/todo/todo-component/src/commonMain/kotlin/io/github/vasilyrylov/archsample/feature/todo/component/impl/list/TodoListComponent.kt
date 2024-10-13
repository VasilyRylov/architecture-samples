package io.github.vasilyrylov.archsample.feature.todo.component.impl.list

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.ui.base.UiComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.di.TodoFlowDIComponent
import me.tatarka.inject.annotations.Inject


internal interface TodoListComponent : UiComponent {

    interface Factory {
        fun create(context: ComponentContext): TodoListComponent
    }

    @Inject
    class DI(parent: TodoFlowDIComponent) {
        val factory: Factory = TodoListComponentImpl.Factory(parent)
    }
}
