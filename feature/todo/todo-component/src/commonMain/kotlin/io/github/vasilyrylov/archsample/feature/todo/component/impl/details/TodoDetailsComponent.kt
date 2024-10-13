package io.github.vasilyrylov.archsample.feature.todo.component.impl.details

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.common.ui.base.UiComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.di.TodoFlowDIComponent
import me.tatarka.inject.annotations.Inject

internal interface TodoDetailsComponent : UiComponent {

    interface Factory {
        fun create(
            context: ComponentContext,
            itemId: TodoItemId,
        ): TodoDetailsComponent
    }

    @Inject
    class DI(parent: TodoFlowDIComponent) {
        val factory: Factory = TodoDetailsComponentImpl.Factory(parent)
    }
}
