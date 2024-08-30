package io.github.vasilyrylov.archsample

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.vasilyrylov.archsample.common.data.preferences.JVMPreferences
import io.github.vasilyrylov.archsample.data.database.getRoomDatabase
import java.awt.Dimension
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent
import io.github.vasilyrylov.archsample.feature.root.component.api.IRootComponentDependencies

fun main() {
    val lifecycle = LifecycleRegistry()

    val database = getRoomDatabase()

    val preferences = JVMPreferences()

    val rootComponentDependencies = object : IRootComponentDependencies {
        override val preferences = preferences
        override val database = database
    }

    val rootFlowComponent = runOnUiThread {
        RootFlowComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
            dependencies = rootComponentDependencies
        )
    }

    application {
        Window(
            title = "ArchSample",
            state = rememberWindowState(width = 800.dp, height = 600.dp),
            onCloseRequest = ::exitApplication,
        ) {
            window.minimumSize = Dimension(350, 600)

            ComposeApp(rootFlowComponent)
        }
    }
}
