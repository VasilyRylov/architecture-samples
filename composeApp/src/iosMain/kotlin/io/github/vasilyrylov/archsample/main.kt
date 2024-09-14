package io.github.vasilyrylov.archsample

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.data.preferences.IOsPreferences
import io.github.vasilyrylov.archsample.data.database.getRoomDatabase
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies
import platform.Foundation.NSUserDefaults
import platform.UIKit.UIViewController

fun MainViewController(context: ComponentContext): UIViewController {

    val database = getRoomDatabase()

    val preferences = IOsPreferences(NSUserDefaults.standardUserDefaults)

    val rootComponentDependencies = object : IRootComponentDependencies {
        override val preferences = preferences
        override val userDao = database.getUserDao()
        override val todoDao = database.getTodoDao()
    }

    val root = RootFlowComponent(context, rootComponentDependencies)

    return ComposeUIViewController {
        ComposeApp(root)
    }
}
