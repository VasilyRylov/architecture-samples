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
fun ToDoFlowScreenComponent(toDoFlowComponent: TodoFlowComponent) {
    // Work in progress
    val childStack by toDoFlowComponent.router.childStack.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is ToDoFlowRouter.Child.ToDoList -> {
                TodoListScreenContainer(child.component.viewModel)
            }

            is ToDoFlowRouter.Child.ToDoDetail -> {
                TodoDetailsScreenContainer(child.component.viewModel)
            }
        }
    }
}