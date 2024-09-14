package io.github.vasilyrylov.archsample.feature.todo.component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.component.createKoinScope
import io.github.vasilyrylov.archsample.common.component.updateRouterInstance
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.component.di.createTodoFlowModule

class TodoFlowComponent(
    componentContext: ComponentContext,
    componentDependencies: ITodoComponentDependencies
) : ComponentContext by componentContext {
    // Work in progress

    private val koinScope = createKoinScope(listOf(createTodoFlowModule(componentDependencies)))

    val router = TodoFlowRouter(componentContext, koinScope)

    init {
        koinScope.updateRouterInstance(router)
    }
}