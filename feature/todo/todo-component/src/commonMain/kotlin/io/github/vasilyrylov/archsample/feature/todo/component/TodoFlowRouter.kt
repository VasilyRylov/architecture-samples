package io.github.vasilyrylov.archsample.feature.todo.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.benasher44.uuid.uuidFrom
import io.github.vasilyrylov.archsample.feature.todo.component.details.TodoDetailsComponent
import io.github.vasilyrylov.archsample.feature.todo.component.list.TodoListComponent
import io.github.vasilyrylov.archsample.common.domain.model.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.ui.api.ITodoFlowRouter
import kotlinx.serialization.Serializable
import org.koin.core.scope.Scope

class TodoFlowRouter(
    componentContext: ComponentContext,
    private val koinScope: Scope
) : ITodoFlowRouter {

    private val stackNavigation = StackNavigation<Configuration>()

    internal val childStack: Value<ChildStack<*, Child>> = componentContext.childStack(
        source = stackNavigation,
        serializer = Configuration.serializer(),
        handleBackButton = true,
        childFactory = ::childFactory,
        initialStack = { listOf(Configuration.TodoList) }
    )

    private fun childFactory(config: Configuration, componentContext: ComponentContext): Child {
        return when (config) {
            is Configuration.TodoList -> Child.TodoList(
                component = TodoListComponent(componentContext = componentContext, parentScope = koinScope)
            )

            is Configuration.TodoDetail -> Child.TodoDetail(
                component = TodoDetailsComponent(
                    componentContext = componentContext, parentScope = koinScope, itemId = TodoItemId(uuidFrom(config.todoId))
                )
            )
        }
    }

    internal sealed interface Child {
        class TodoList(val component: TodoListComponent) : Child
        class TodoDetail(val component: TodoDetailsComponent) : Child
    }

    @Serializable
    internal sealed class Configuration {
        @Serializable
        data object TodoList : Configuration()

        @Serializable
        data class TodoDetail(val todoId: String) : Configuration()
    }

    override fun toDetailTodo(id: TodoItemId) {
        stackNavigation.pushNew(Configuration.TodoDetail(todoId = id.value.toString()))
    }

    override fun back() {
        stackNavigation.pop()
    }
}