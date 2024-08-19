package io.github.vasilyrylov.archsample.feature.todo.component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.component.createKoinScope
import io.github.vasilyrylov.archsample.common.component.updateRouterInstance
import io.github.vasilyrylov.archsample.feature.todo.component.api.IToDoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.component.di.createToDoFlowModule

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