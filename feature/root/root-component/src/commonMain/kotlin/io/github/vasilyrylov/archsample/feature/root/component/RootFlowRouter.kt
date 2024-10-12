package io.github.vasilyrylov.archsample.feature.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.value.Value
import io.github.vasilyrylov.archsample.feature.auth.component.AuthFlowComponent
import io.github.vasilyrylov.archsample.feature.root.component.di.RootFlowDIComponent
import io.github.vasilyrylov.archsample.feature.root.ui.api.IRootFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.component.TodoFlowComponent
import kotlinx.serialization.Serializable

internal class RootFlowRouter(
    componentContext: ComponentContext,
    private val rootFlowDIComponent: RootFlowDIComponent,
    ) : IRootFlowRouter {

    private val slotNavigation = SlotNavigation<Configuration>()

    internal val childSlot: Value<ChildSlot<*, SlotChild>> = componentContext.childSlot(
        source = slotNavigation,
        serializer = Configuration.serializer(),
        handleBackButton = false,
        childFactory = ::slotChild
    )

    private fun slotChild(config: Configuration, componentContext: ComponentContext): SlotChild {
        return when (config) {
            Configuration.Auth -> SlotChild.AuthFlow(
                component = rootFlowDIComponent.authFlowComponentFactory.create(componentContext)
            )

            is Configuration.Todo -> SlotChild.TodoFlow(
                component = TodoFlowComponent(componentContext, rootFlowDIComponent.todoComponentDependencies)
            )
        }
    }

    internal sealed interface SlotChild {
        data class AuthFlow(val component: AuthFlowComponent) : SlotChild
        data class TodoFlow(val component: TodoFlowComponent) : SlotChild
    }

    @Serializable
    internal sealed class Configuration {
        @Serializable
        data object Auth : Configuration()

        @Serializable
        data object Todo : Configuration()
    }

    override fun toAuth() {
        slotNavigation.activate(Configuration.Auth)
    }

    override fun toTodo() {
        slotNavigation.activate(Configuration.Todo)
    }
}
