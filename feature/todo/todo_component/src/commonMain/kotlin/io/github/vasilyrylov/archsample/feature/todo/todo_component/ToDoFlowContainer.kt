package io.github.vasilyrylov.archsample.feature.todo.todo_component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.vasilyrylov.archsample.feature.todo.todo_domain.model.ToDoItem
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.TodoDetailsScreenMain
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.TodoListScreen

@Composable
fun ToDoFlowContainer(toDoFlowComponent: ToDoFlowComponent) {
    // Work in progress
    val childStack by toDoFlowComponent.router.childStack.subscribeAsState()

    Children(
        stack = childStack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is ToDoFlowRouter.Child.ToDoList -> {
                TodoListScreen(child.component.viewModel)
            }

            is ToDoFlowRouter.Child.ToDoDetail -> {
                TodoDetailsScreenMain(
                    todoItem = ToDoItem(text = "test", completed = false),
                    onBackClick = { },
                    onDeleteClick = { },
                    onEditClick = { }
                )
            }
        }
    }
}