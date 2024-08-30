package io.github.vasilyrylov.archsample

import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ComponentContext
import io.github.vasilyrylov.archsample.common.data.preferences.IOsPreferences
import io.github.vasilyrylov.archsample.data.database.getRoomDatabase
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies
import platform.UIKit.UIViewController

fun MainViewController(context: ComponentContext): UIViewController {

    val database = getRoomDatabase()

    val preferences = IOsPreferences()

    val rootComponentDependencies = object : IRootComponentDependencies {
        override val preferences = preferences
        override val database = database
    }

    val root = RootFlowComponent(context, rootComponentDependencies)

    return ComposeUIViewController {
        ComposeApp(root)
    }
}
