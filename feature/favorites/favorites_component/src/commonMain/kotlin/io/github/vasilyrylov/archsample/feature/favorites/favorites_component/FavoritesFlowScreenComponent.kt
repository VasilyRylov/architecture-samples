package io.github.vasilyrylov.archsample.feature.favorites.favorites_component

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import io.github.vasilyrylov.archsample.feature.favorites.favorites_ui.list.FavoriteListScreenContainer

@Composable
fun FavoritesScreenComponent(favoritesFlowComponent: FavoritesFlowComponent) {
    // Work in progress
    val childStack = favoritesFlowComponent.router.childStack
    Children(
        stack = childStack,
    ) {
        when (val child = it.instance) {
            FavoritesFlowRouter.Child.FavoritesList -> {
                FavoriteListScreenContainer()
            }
        }
    }
}