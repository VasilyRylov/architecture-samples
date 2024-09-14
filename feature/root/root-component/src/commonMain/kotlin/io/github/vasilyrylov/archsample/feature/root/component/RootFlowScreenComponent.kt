package io.github.vasilyrylov.archsample.feature.root.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.vasilyrylov.archsample.feature.auth.component.AuthFlowScreenComponent
import io.github.vasilyrylov.archsample.feature.todo.component.TodoFlowScreenComponent

@Composable
fun RootFlowScreenComponent(rootFlowComponent: RootFlowComponent) {
    val childSlot by rootFlowComponent.router.childSlot.subscribeAsState()

    when (val child = childSlot.child?.instance) {
        is RootFlowRouter.SlotChild.AuthFlow -> AuthFlowScreenComponent(child.component)
        is RootFlowRouter.SlotChild.TodoFlow -> TodoFlowScreenComponent(child.component)
        null -> Unit
    }
}
