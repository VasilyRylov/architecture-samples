package io.github.vasilyrylov.archsample.feature.favorites.favorites_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import io.github.vasilyrylov.archsample.feature.favorites.favorites_ui.api.IFavoritesRouter
import kotlinx.serialization.Serializable

class FavoritesFlowRouter(
    componentContext: ComponentContext,
) : IFavoritesRouter {

    private val stackNavigation = StackNavigation<Configuration>()

    internal val childStack: Value<ChildStack<*, Child>> = componentContext.childStack(
        source = stackNavigation,
        serializer = Configuration.serializer(),
        handleBackButton = true,
        childFactory = ::childFactory,
        initialStack = { listOf(Configuration.FavoritesList) }
    )

    private fun childFactory(config: Configuration, componentContext: ComponentContext): Child {
        return when (config) {
            Configuration.FavoritesList -> Child.FavoritesList
            is Configuration.ToDoDetail -> TODO()
        }
    }

    internal sealed interface Child {
        data object FavoritesList : Child
    }

    @Serializable
    internal sealed class Configuration {
        @Serializable
        data object FavoritesList : Configuration()

        @Serializable
        data class ToDoDetail(val toDoId: String) : Configuration()
    }

    override fun toDetailToDo(toDoId: String) {
        stackNavigation.pushNew(Configuration.ToDoDetail(toDoId = toDoId))
    }
}