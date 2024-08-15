package io.github.vasilyrylov.archsample.feature.todo.todo_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import io.github.vasilyrylov.archsample.feature.todo.todo_component.detail.ToDoDetailComponent
import io.github.vasilyrylov.archsample.feature.todo.todo_component.list.ToDoListComponent
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter
import kotlinx.serialization.Serializable
import org.koin.core.scope.Scope

class ToDoFlowRouter(
    componentContext: ComponentContext,
    private val userId: String,
    private val koinScope: Scope
) : IToDoFlowRouter {

    private val slotNavigation = StackNavigation<Configuration>()

    internal val childStack: Value<ChildStack<*, Child>> = componentContext.childStack(
        source = slotNavigation,
        serializer = Configuration.serializer(),
        handleBackButton = true,
        childFactory = ::slotChild,
        initialStack = { listOf(Configuration.ToDo(userId = userId)) }
    )

    private fun slotChild(config: Configuration, componentContext: ComponentContext): Child {
        return when (config) {
            is Configuration.ToDo -> Child.ToDoList(
                component = ToDoListComponent(componentContext, parentScope = koinScope)
            )

            is Configuration.ToDoDetail -> Child.ToDoDetail(
                component = ToDoDetailComponent(componentContext, config.toDoId)
            )
        }
    }

    internal sealed interface Child {
        class ToDoList(val component: ToDoListComponent) : Child
        class ToDoDetail(val component: ToDoDetailComponent) : Child
    }

    @Serializable
    internal sealed class Configuration {
        @Serializable
        data class ToDo(val userId: String) : Configuration()

        @Serializable
        data class ToDoDetail(val toDoId: String) : Configuration()
    }

    override fun toDetailToDo(toDoId: String) {
        slotNavigation.pushNew(Configuration.ToDoDetail(toDoId = toDoId))
    }
}