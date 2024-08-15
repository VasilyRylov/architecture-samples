package io.github.vasilyrylov.archsample.feature.root.root_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.value.Value
import io.github.vasilyrylov.archsample.feature.auth.auth_component.AuthComponent
import io.github.vasilyrylov.archsample.feature.auth.auth_component.api.IAuthComponentDependencies
import io.github.vasilyrylov.archsample.feature.auth.auth_domain.api.IAuthCompletionUseCase
import io.github.vasilyrylov.archsample.feature.root.root_ui.api.IRootFlowRouter
import io.github.vasilyrylov.archsample.feature.todo.todo_component.ToDoComponent
import kotlinx.serialization.Serializable
import org.koin.core.scope.Scope

class RootFlowRouter(componentContext: ComponentContext, private val koinScope: Scope) : IRootFlowRouter {

    private val slotNavigation = SlotNavigation<Configuration>()

    internal val childSlot: Value<ChildSlot<*, SlotChild>> = componentContext.childSlot(
        source = slotNavigation,
        serializer = Configuration.serializer(),
        handleBackButton = true,
        childFactory = ::slotChild
    )

    private fun slotChild(config: Configuration, componentContext: ComponentContext): SlotChild {
        return when (config) {
            Configuration.Auth -> SlotChild.Auth(
                component = AuthComponent(componentContext, object : IAuthComponentDependencies {
                    override val authCompletionUseCase: IAuthCompletionUseCase
                        get() = koinScope.get()
                })
            )

            is Configuration.ToDo -> SlotChild.ToDo(
                component = ToDoComponent(componentContext)
            )
        }
    }

    internal sealed interface SlotChild {
        data class Auth(val component: AuthComponent) : SlotChild
        data class ToDo(val component: ToDoComponent) : SlotChild
    }

    @Serializable
    internal sealed class Configuration {
        @Serializable
        data object Auth : Configuration()

        @Serializable
        data class ToDo(val userId: String) : Configuration()
    }

    override fun toAuth() {
        slotNavigation.activate(Configuration.Auth)
    }

    override fun toToDo(userId: String) {
        slotNavigation.activate(Configuration.ToDo(userId))
    }
}