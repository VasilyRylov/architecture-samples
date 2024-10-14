package io.github.vasilyrylov.archsample.feature.root.component.impl.di.dependencies.todo

import io.github.vasilyrylov.archsample.feature.todo.component.api.TodoFlowComponent
import io.github.vasilyrylov.archsample.feature.todo.component.api.list.TodoListCallback
import me.tatarka.inject.annotations.Inject

@Inject
internal class TodoComponentDependencies(
    callback: Lazy<TodoListCallbackImpl>,
) : TodoFlowComponent.Dependencies {
    override val callback: TodoListCallback by callback
}
