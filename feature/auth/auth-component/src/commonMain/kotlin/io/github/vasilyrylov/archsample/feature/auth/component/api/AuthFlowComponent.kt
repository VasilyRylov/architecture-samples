package io.github.vasilyrylov.archsample.feature.auth.component.api

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.component.UiComponent
import io.github.vasilyrylov.archsample.common.data.preferences.PreferencesDI
import io.github.vasilyrylov.archsample.data.database.di.DatabaseDI
import io.github.vasilyrylov.archsample.feature.auth.component.impl.AuthFlowComponentImpl
import me.tatarka.inject.annotations.Inject

interface AuthFlowComponent : UiComponent {

    interface Dependencies {
        val callback: AuthFlowCallback
    }

    interface Factory {
        fun create(context: ComponentContext): AuthFlowComponent
    }

    @Inject
    class DI(dependencies: Dependencies) {
        val factory: Factory = AuthFlowComponentImpl.Factory(dependencies)
    }
}
