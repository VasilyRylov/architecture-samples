package io.github.vasilyrylov.archsample.di

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferencesSource
import io.github.vasilyrylov.archsample.common.data.preferences.PreferencesDIContainer
import io.github.vasilyrylov.archsample.data.database.di.DatabaseDIContainer
import io.github.vasilyrylov.archsample.data.database.di.IDatabaseSource
import me.tatarka.inject.annotations.Scope

@Scope
annotation class AppScope

object AppComponent {

    fun init(platformComponent: PlatformComponent) {
        DatabaseDIContainer.apply { platformComponent.deploy() }
        PreferencesDIContainer.apply { platformComponent.deploy() }
    }
}

interface PlatformComponent :
    IDatabaseSource,
    IPreferencesSource

//// Separate for instrumental tests mock
//abstract class NetworkComponent {
//    abstract val api: IApi
//}
