package io.github.vasilyrylov.archsample.feature.root.component.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.value.Value
import io.github.vasilyrylov.archsample.common.component.UiComponent
import io.github.vasilyrylov.archsample.common.component.navigation.IRouter
import io.github.vasilyrylov.archsample.feature.auth.component.api.AuthFlowComponent
import io.github.vasilyrylov.archsample.feature.root.domain.di.RootFlowScope
import io.github.vasilyrylov.archsample.feature.todo.component.api.TodoFlowComponent
import kotlinx.serialization.Serializable
import me.tatarka.inject.annotations.Inject

internal interface IRootFlowRouter : IRouter {
    fun toAuth()
    fun toTodo()
}

@RootFlowScope
@Inject
internal class RootFlowRouter(
    authFlowComponentFactory: Lazy<AuthFlowComponent.Factory>,
    todoFlowComponentFactory: Lazy<TodoFlowComponent.Factory>,
) : IRootFlowRouter {

    private val authFlowComponentFactory by authFlowComponentFactory
    private val todoFlowComponentFactory by todoFlowComponentFactory

    private val slotNavigation = SlotNavigation<Configuration>()

    internal fun childSlot(context: ComponentContext): Value<ChildSlot<*, UiComponent>> {
        return context.childSlot(
            source = slotNavigation,
            serializer = Configuration.serializer(),
            handleBackButton = false,
            childFactory = ::slotChild,
        )
    }

    private fun slotChild(config: Configuration, context: ComponentContext): UiComponent {
        return when (config) {
            Configuration.Auth -> authFlowComponentFactory.create(context)
            Configuration.Todo -> todoFlowComponentFactory.create(context)
        }
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
