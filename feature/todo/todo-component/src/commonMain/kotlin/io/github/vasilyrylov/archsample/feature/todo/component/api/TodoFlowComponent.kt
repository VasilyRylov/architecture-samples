package io.github.vasilyrylov.archsample.feature.todo.component.api

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.ui.base.UiComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.TodoFlowComponentImpl
import io.github.vasilyrylov.archsample.feature.todo.component.api.list.TodoListDependencies
import me.tatarka.inject.annotations.Inject

interface TodoFlowComponent : UiComponent {

    interface Dependencies : TodoListDependencies

    interface Factory {
        fun create(context: ComponentContext): TodoFlowComponent
    }

    @Inject
    class DI(dependencies: Dependencies) {
        val factory: Factory = TodoFlowComponentImpl.Factory(dependencies)
    }
}
