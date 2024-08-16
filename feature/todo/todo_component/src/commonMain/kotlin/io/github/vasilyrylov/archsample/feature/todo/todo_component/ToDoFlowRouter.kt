package io.github.vasilyrylov.archsample.feature.todo.todo_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.benasher44.uuid.uuidFrom
import io.github.vasilyrylov.archsample.feature.todo.todo_component.detail.ToDoDetailsComponent
import io.github.vasilyrylov.archsample.feature.todo.todo_component.list.ToDoListComponent
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItemId
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.api.IToDoFlowRouter
import kotlinx.serialization.Serializable
import org.koin.core.scope.Scope

class ToDoFlowRouter(
    componentContext: ComponentContext,
    private val koinScope: Scope
) : IToDoFlowRouter {

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
                component = ToDoListComponent(componentContext = componentContext, parentScope = koinScope)
            )

            is Configuration.ToDoDetail -> Child.ToDoDetail(
                component = ToDoDetailsComponent(
                    componentContext = componentContext, parentScope = koinScope, itemId = ToDoItemId(uuidFrom(config.toDoId))
                )
            )
        }
    }

    internal sealed interface Child {
        class ToDoList(val component: ToDoListComponent) : Child
        class ToDoDetail(val component: ToDoDetailsComponent) : Child
    }

    @Serializable
    internal sealed class Configuration {
        @Serializable
        data object ToDoList : Configuration()

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