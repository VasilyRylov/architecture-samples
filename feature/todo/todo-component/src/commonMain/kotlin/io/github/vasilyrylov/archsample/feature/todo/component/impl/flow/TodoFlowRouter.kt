package io.github.vasilyrylov.archsample.feature.todo.component.impl.flow

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import io.github.vasilyrylov.archsample.common.component.UiComponent
import io.github.vasilyrylov.archsample.common.component.navigation.IRouter
import io.github.vasilyrylov.archsample.common.data.id.TodoItemId
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.TodoFlowRouter.Configuration.TodoDetail
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.TodoFlowRouter.Configuration.TodoList
import io.github.vasilyrylov.archsample.feature.todo.component.impl.details.TodoDetailsComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.flow.di.TodoFlowScope
import io.github.vasilyrylov.archsample.feature.todo.component.impl.list.TodoListComponent
import kotlinx.serialization.Serializable
import me.tatarka.inject.annotations.Inject

internal interface ITodoFlowRouter : IRouter {
    fun toDetailTodo(id: TodoItemId)
    fun back()
}

@TodoFlowScope
@Inject
internal class TodoFlowRouter(
    todoListComponentFactory: Lazy<TodoListComponent.Factory>,
    todoDetailsComponentFactory: Lazy<TodoDetailsComponent.Factory>,
) : ITodoFlowRouter {

    private val todoListComponentFactory by todoListComponentFactory
    private val todoDetailsComponentFactory by todoDetailsComponentFactory

    private val stackNavigation = StackNavigation<Configuration>()

    internal fun childStack(context: ComponentContext): Value<ChildStack<*, UiComponent>> {
        return context.childStack(
            source = stackNavigation,
            serializer = Configuration.serializer(),
            handleBackButton = true,
            childFactory = ::childFactory,
            initialStack = { listOf(TodoList) }
        )
    }

    private fun childFactory(
        config: Configuration,
        componentContext: ComponentContext
    ): UiComponent {
        return when (config) {
            is TodoList -> todoListComponentFactory.create(componentContext)
            is TodoDetail -> todoDetailsComponentFactory.create(componentContext, config.todoId)
        }
    }

    @Serializable
    internal sealed class Configuration {
        @Serializable
        data object TodoList : Configuration()

        @Serializable
        data class TodoDetail(val todoId: TodoItemId) : Configuration()
    }

    override fun toDetailTodo(id: TodoItemId) {
        stackNavigation.pushNew(TodoDetail(todoId = id))
    }

    override fun back() {
        stackNavigation.pop()
    }
}
