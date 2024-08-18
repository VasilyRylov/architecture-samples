package io.github.vasilyrylov.archsample.feature.favorites.favorites_component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.common_component.createKoinScope
import io.github.vasilyrylov.archsample.common.common_component.updateRouterInstance
import io.github.vasilyrylov.archsample.feature.favorites.favorites_component.di.createFavoritesFlowModule

class FavoritesFlowComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    private val koinScope = createKoinScope(listOf(createFavoritesFlowModule()))

    val router = FavoritesFlowRouter(componentContext = componentContext)

    init {
        koinScope.updateRouterInstance(router)
    }
}