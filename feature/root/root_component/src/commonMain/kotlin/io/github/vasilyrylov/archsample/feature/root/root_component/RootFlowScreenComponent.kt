package io.github.vasilyrylov.archsample.feature.root.root_component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import io.github.vasilyrylov.archsample.feature.auth.auth_component.AuthFlowScreenComponent
import io.github.vasilyrylov.archsample.feature.tab.tab_component.compose.TabFlowScreenComponent

@Composable
fun RootFlowScreenComponent(rootFlowComponent: RootFlowComponent) {
    val childSlot by rootFlowComponent.router.childSlot.subscribeAsState()

    when (val child = childSlot.child?.instance) {
        is RootFlowRouter.SlotChild.Auth -> AuthFlowScreenComponent(child.component)
        is RootFlowRouter.SlotChild.ToDo -> TabFlowScreenComponent(child.component)
        null -> Unit
    }
}
