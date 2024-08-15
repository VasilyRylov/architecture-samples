package io.github.vasilyrylov.archsample.feature.root.root_component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.vasilyrylov.archsample.feature.auth.auth_component.AuthFlowContainer
import io.github.vasilyrylov.archsample.feature.todo.todo_component.ToDoFlowContainer

@Composable
fun RootFlowContainer(rootComponent: RootComponent) {
    val childSlot by rootComponent.router.childSlot.subscribeAsState()

    when (val child = childSlot.child?.instance) {
        is RootFlowRouter.SlotChild.Auth -> AuthFlowContainer(child.component)
        is RootFlowRouter.SlotChild.ToDo -> ToDoFlowContainer(child.component)
        null -> Unit
    }
}
