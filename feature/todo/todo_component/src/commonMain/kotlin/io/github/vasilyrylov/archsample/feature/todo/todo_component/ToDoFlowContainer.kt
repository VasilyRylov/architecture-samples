package io.github.vasilyrylov.archsample.feature.todo.todo_component

import ToDoItemData
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.details.TodoDetailsScreenMain
import io.github.vasilyrylov.archsample.feature.todo.todo_ui.list.TodoListScreenMain

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
                TodoListScreenMain(
                    todoItems = listOf(ToDoItemData(text = "test", completed = false)),
                    onAddClick = {},
                    onTodoItemClick = {
                        child.component.viewModel.onToDoClick(toDoId = "1234")
                    }
                )
            }

            is ToDoFlowRouter.Child.ToDoDetail -> {
                TodoDetailsScreenMain(
                    todoItem = ToDoItemData(text = "test", completed = false),
                    onBackClick = { },
                    onDeleteClick = { },
                    onEditClick = { }
                )
            }
        }
    }
}