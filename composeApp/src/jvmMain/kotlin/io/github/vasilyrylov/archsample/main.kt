package io.github.vasilyrylov.archsample

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import io.github.vasilyrylov.archsample.di.AppComponent
import io.github.vasilyrylov.archsample.di.JvmPlatformComponent
import io.github.vasilyrylov.archsample.di.create
import io.github.vasilyrylov.archsample.feature.root.component.RootFlowComponent
import java.awt.Button
import java.awt.Dialog
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Frame
import java.awt.Label

fun main() {
    Thread.setDefaultUncaughtExceptionHandler { _, e ->
        Dialog(Frame(), e.message ?: "Error").apply {
            layout = FlowLayout()
            val label = Label(e.message)
            add(label)
            val button = Button("OK").apply {
                addActionListener { dispose() }
            }
            add(button)
            setSize(300, 300)
            isVisible = true
        }
    }

    val platformComponent = JvmPlatformComponent::class.create()
    AppComponent.init(platformComponent)

    val lifecycle = LifecycleRegistry()

    val rootFlowComponent = runOnUiThread {
        RootFlowComponent.DI.factory.create(DefaultComponentContext(lifecycle = lifecycle))
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
