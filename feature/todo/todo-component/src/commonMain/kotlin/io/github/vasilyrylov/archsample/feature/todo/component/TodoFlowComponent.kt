package io.github.vasilyrylov.archsample.feature.todo.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.feature.todo.component.api.ITodoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.component.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.di.create

class TodoFlowComponent(
    componentContext: ComponentContext,
    componentDependencies: ITodoComponentDependencies
) : ComponentContext by componentContext {

    private val diComponent = instanceKeeper.getOrCreate {
        TodoFlowDIComponent::class.create(componentDependencies)
    }

    val router = TodoFlowRouter(componentContext, diComponent)

    init {
        diComponent.getRouterHolder().updateInstance(router)
    }
}