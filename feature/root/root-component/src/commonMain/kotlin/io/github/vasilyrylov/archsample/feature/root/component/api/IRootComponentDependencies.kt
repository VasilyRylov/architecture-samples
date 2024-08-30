package io.github.vasilyrylov.archsample.feature.root.component.api

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.ArchSampleDatabase

interface IRootComponentDependencies {
    val preferences: IPreferences
    val database: ArchSampleDatabase
}