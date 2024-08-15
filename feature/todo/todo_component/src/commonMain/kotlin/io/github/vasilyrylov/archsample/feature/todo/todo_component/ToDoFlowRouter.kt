package io.github.vasilyrylov.archsample.feature.todo.todo_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
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

    private val stackNavigation = StackNavigation<Configuration>()

    internal val childStack: Value<ChildStack<*, Child>> = componentContext.childStack(
        source = stackNavigation,
        serializer = Configuration.serializer(),
        handleBackButton = true,
        childFactory = ::childFactory,
        initialStack = { listOf(Configuration.ToDoList(userId = userId)) }
    )

    private fun childFactory(config: Configuration, componentContext: ComponentContext): Child {
        return when (config) {
            is Configuration.ToDoList -> Child.ToDoList(
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
        data class ToDoList(val userId: String) : Configuration()

        @Serializable
        data class ToDoDetail(val toDoId: String) : Configuration()
    }

    override fun toDetailToDo(toDoId: String) {
        stackNavigation.pushNew(Configuration.ToDoDetail(toDoId = toDoId))
    }

    override fun back() {
        stackNavigation.pop()
    }
}