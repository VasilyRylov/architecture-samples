package io.github.vasilyrylov.archsample.feature.todo.component.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.essenty.instancekeeper.getOrCreate
import io.github.vasilyrylov.archsample.feature.todo.component.api.TodoFlowComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.di.TodoFlowDIComponent
import io.github.vasilyrylov.archsample.feature.todo.component.impl.di.create
import me.tatarka.inject.annotations.Inject

internal class TodoFlowComponentImpl(
    componentContext: ComponentContext,
    dependencies: TodoFlowComponent.Dependencies
) : ComponentContext by componentContext, TodoFlowComponent {

    @Composable
    override fun Ui() {
        val childStack by childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) {
            it.instance.Ui()
        }
    }

    private val diComponent = instanceKeeper.getOrCreate {
        TodoFlowDIComponent::class.create(dependencies)
    }

    private val childStack = diComponent.router.childStack(componentContext)

    @Inject
    class Factory(
        private val dependencies: TodoFlowComponent.Dependencies,
    ) : TodoFlowComponent.Factory {

        override fun create(
            context: ComponentContext,
        ) = TodoFlowComponentImpl(context, dependencies)
    }
}
