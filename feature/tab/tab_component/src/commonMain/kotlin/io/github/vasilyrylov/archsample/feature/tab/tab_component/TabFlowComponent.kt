package io.github.vasilyrylov.archsample.feature.tab.tab_component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.common_component.createKoinScope
import io.github.vasilyrylov.archsample.common.common_component.updateRouterInstance
import io.github.vasilyrylov.archsample.feature.tab.tab_component.api.ITabComponentDependencies
import io.github.vasilyrylov.archsample.feature.tab.tab_component.di.createTabFlowModule

class TabFlowComponent(
    componentContext: ComponentContext,
    dependencies: ITabComponentDependencies
) : ComponentContext by componentContext {

    private val koinScope = createKoinScope(listOf(createTabFlowModule()))

    val router = TabFlowRouter(componentContext = componentContext, dependencies = dependencies)

    init {
        koinScope.updateRouterInstance(router)
    }
}