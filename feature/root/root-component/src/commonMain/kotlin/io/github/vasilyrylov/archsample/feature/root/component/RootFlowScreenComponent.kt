package io.github.vasilyrylov.archsample.feature.root.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.vasilyrylov.archsample.feature.auth.component.AuthFlowScreenComponent
import io.github.vasilyrylov.archsample.feature.todo.component.ToDoFlowScreenComponent

@Composable
fun RootFlowScreenComponent(rootFlowComponent: RootFlowComponent) {
    val childSlot by rootFlowComponent.router.childSlot.subscribeAsState()

    when (val child = childSlot.child?.instance) {
        is RootFlowRouter.SlotChild.Auth -> AuthFlowScreenComponent(child.component)
        is RootFlowRouter.SlotChild.ToDo -> ToDoFlowScreenComponent(child.component)
        null -> Unit
    }
}
