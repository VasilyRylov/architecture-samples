package io.github.vasilyrylov.archsample.feature.tab.tab_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import io.github.vasilyrylov.archsample.common.common_domain.api.IAuthorizedUserRepository
import io.github.vasilyrylov.archsample.feature.tab.tab_component.api.ITabComponentDependencies
import io.github.vasilyrylov.archsample.feature.tab.tab_ui.ITabFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.todo_component.ToDoFlowComponent
import io.github.vasilyrylov.archsample.feature.todo.todo_component.api.IToDoComponentDependencies
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.api.ILogoutUseCase
import kotlinx.serialization.Serializable

class TabFlowRouter(
    componentContext: ComponentContext,
    private val dependencies: ITabComponentDependencies,
) : ITabFlowRouter {
    private val stackNavigation = StackNavigation<Configuration>()

    internal val childStack: Value<ChildStack<*, Child>> = componentContext.childStack(
        source = stackNavigation,
        serializer = Configuration.serializer(),
        handleBackButton = true,
        childFactory = ::childFactory,
        initialStack = { listOf(Configuration.ToDoList) }
    )

    private fun childFactory(config: Configuration, componentContext: ComponentContext): Child {
        return when (config) {
            is Configuration.ToDoList -> Child.ToDoList(
                component = ToDoFlowComponent(
                    componentContext = componentContext,
                    componentDependencies = object : IToDoComponentDependencies {
                        override val authorizedUserRepository: IAuthorizedUserRepository
                            get() = dependencies.authorizedUserRepository
                        override val logoutUseCase: ILogoutUseCase
                            get() = dependencies.logoutUseCase
                    }
                )
            )

            is Configuration.Favorites -> Child.Favorites
        }
    }

    internal sealed interface Child {
        class ToDoList(val component: ToDoFlowComponent) : Child
        data object Favorites : Child
    }

    @Serializable
    internal sealed class Configuration {
        @Serializable
        data object ToDoList : Configuration()

        @Serializable
        data object Favorites : Configuration()
    }

    override fun openToDoList() {
        stackNavigation.bringToFront(Configuration.ToDoList)
    }

    override fun openFavorites() {
        stackNavigation.bringToFront(Configuration.Favorites)
    }
}