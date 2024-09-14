package io.github.vasilyrylov.archsample.feature.root.component.api

import io.github.vasilyrylov.archsample.common.data.preferences.IPreferences
import io.github.vasilyrylov.archsample.data.database.dao.TodoDao
import io.github.vasilyrylov.archsample.data.database.dao.UserDao

interface IRootComponentDependencies {
    val preferences: IPreferences
    val userDao: UserDao
    val todoDao: TodoDao
}