package io.github.vasilyrylov.archsample.feature.root.component.api

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.component.UiComponent
import io.github.vasilyrylov.archsample.feature.root.component.impl.RootFlowComponentImpl

interface RootFlowComponent : UiComponent {

    interface Factory {
        fun create(context: ComponentContext): RootFlowComponent
    }

    object DI {
        val factory: Factory = RootFlowComponentImpl.Factory()
    }
}
