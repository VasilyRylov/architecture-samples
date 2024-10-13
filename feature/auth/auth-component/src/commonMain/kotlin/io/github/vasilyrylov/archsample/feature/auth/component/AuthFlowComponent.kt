package io.github.vasilyrylov.archsample.feature.auth.component

import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.data.preferences.IPreferencesComponent
import io.github.vasilyrylov.archsample.common.ui.base.UiComponent
import io.github.vasilyrylov.archsample.data.database.di.IDatabaseComponent
import me.tatarka.inject.annotations.Inject

interface AuthFlowComponent : UiComponent {

    interface Dependencies : IDatabaseComponent, IPreferencesComponent {
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
