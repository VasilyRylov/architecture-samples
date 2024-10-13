package io.github.vasilyrylov.archsample.feature.root.component.di.dependencies

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.feature.auth.component.AuthFlowCallback
import io.github.vasilyrylov.archsample.feature.auth.component.AuthFlowComponent
import io.github.vasilyrylov.archsample.feature.root.component.di.dependencies.callback.AuthFlowCallbackImpl
import me.tatarka.inject.annotations.Inject

@Inject
internal class AuthComponentDependencies(
    callback: Lazy<AuthFlowCallbackImpl>,
    database: Lazy<ArchSampleDatabase>,
    preferences: Lazy<IPreferences>,
) : AuthFlowComponent.Dependencies {
    override val callback: AuthFlowCallback by callback
    override val database: ArchSampleDatabase by database
    override val preferences: IPreferences by preferences
}
