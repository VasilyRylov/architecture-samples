package io.github.vasilyrylov.archsample.feature.todo.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.details.TodoDetailsScreenContainer
import io.github.vasilyrylov.archsample.feature.todo.ui.screen.list.TodoListScreenContainer

@Composable
fun TodoFlowScreenComponent(todoFlowComponent: TodoFlowComponent) {
    // Work in progress
    val childStack by todoFlowComponent.router.childStack.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is TodoFlowRouter.Child.TodoList -> {
                TodoListScreenContainer(child.component.viewModel)
            }

            is TodoFlowRouter.Child.TodoDetail -> {
                TodoDetailsScreenContainer(child.component.viewModel)
            }
        }
    }
}