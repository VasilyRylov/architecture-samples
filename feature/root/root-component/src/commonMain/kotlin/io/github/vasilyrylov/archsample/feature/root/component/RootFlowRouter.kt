package io.github.vasilyrylov.archsample.feature.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.navigate
import com.arkivanov.decompose.value.Value
import io.github.vasilyrylov.archsample.common.ui.base.UiComponent
import io.github.vasilyrylov.archsample.feature.auth.component.AuthFlowComponent
import io.github.vasilyrylov.archsample.feature.root.domain.di.RootFlowScope
import io.github.vasilyrylov.archsample.feature.todo.component.api.TodoFlowComponent
import kotlinx.serialization.Serializable
import me.tatarka.inject.annotations.Inject

@RootFlowScope
@Inject
internal class RootFlowRouter(
    private val authFlowComponentFactory: AuthFlowComponent.Factory,
    private val todoFlowComponentFactory: TodoFlowComponent.Factory,
) : IRootFlowRouter {

    private val slotNavigation = SlotNavigation<Configuration>()

    internal fun childSlot(context: ComponentContext): Value<ChildSlot<*, UiComponent>> {
        return context.childSlot(
            source = slotNavigation,
            serializer = Configuration.serializer(),
            handleBackButton = false,
            childFactory = ::slotChild,
        )
    }

    private fun slotChild(config: Configuration, componentContext: ComponentContext): UiComponent {
        return when (config) {
            Configuration.Auth -> authFlowComponentFactory.create(componentContext)
            Configuration.Todo -> todoFlowComponentFactory.create(componentContext)
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
        slotNavigation.navigate { Configuration.Todo }
    }
}
