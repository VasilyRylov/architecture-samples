package io.github.vasilyrylov.archsample.feature.todo.todo_component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.common_component.createKoinScope
import io.github.vasilyrylov.archsample.common.common_component.updateRouterInstance
import io.github.vasilyrylov.archsample.feature.todo.todo_component.api.IToDoComponentDependencies

class ToDoFlowComponent(
    componentContext: ComponentContext,
    componentDependencies: IToDoComponentDependencies
) : ComponentContext by componentContext {
    // Work in progress

    private val koinScope = createKoinScope(listOf(createToDoFlowModule(componentDependencies)))

    val router = ToDoFlowRouter(componentContext, koinScope)

    init {
        koinScope.updateRouterInstance(router)
    }
}