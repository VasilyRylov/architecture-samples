package io.github.vasilyrylov.archsample.feature.root.component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.ui.base.UiComponent

interface RootFlowComponent : UiComponent {

    interface Factory {
        fun create(context: ComponentContext): RootFlowComponent
    }

    object DI {
        val factory: Factory = RootFlowComponentImpl.Factory()
    }
}
