package io.github.vasilyrylov.archsample.feature.todo.todo_component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.common_component.createKoinScope
import io.github.vasilyrylov.archsample.common.common_component.updateRouterInstance

class ToDoFlowComponent(
    componentContext: ComponentContext,
    userId: String,
) : ComponentContext by componentContext {
    // Work in progress

    private val koinScope = createKoinScope(listOf(createToDoFlowModule()))

    val router = ToDoFlowRouter(componentContext, userId, koinScope)

    init {
        koinScope.updateRouterInstance(router)
    }
}