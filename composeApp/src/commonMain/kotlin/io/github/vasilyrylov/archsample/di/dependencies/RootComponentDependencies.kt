package io.github.vasilyrylov.archsample.di.dependencies

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies
import me.tatarka.inject.annotations.Inject

@Inject
class RootComponentDependencies(
    private val _preferences: Lazy<IPreferences>,
    private val _database: Lazy<ArchSampleDatabase>,
) : IRootComponentDependencies {
    override val preferences: IPreferences
        get() = _preferences.value
    override val database: ArchSampleDatabase
        get() = _database.value
}