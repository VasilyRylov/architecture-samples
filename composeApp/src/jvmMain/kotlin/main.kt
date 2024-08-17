import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import java.awt.Dimension
import io.github.vasilyrylov.archsample.App
import io.github.vasilyrylov.archsample.feature.root.root_component.RootFlowComponent

fun main() {
    val lifecycle = LifecycleRegistry()

    application {
        val rootFlowComponent = remember { RootFlowComponent(DefaultComponentContext(lifecycle = lifecycle)) }

        Window(
            title = "ArchSample",
            state = rememberWindowState(width = 800.dp, height = 600.dp),
            onCloseRequest = ::exitApplication,
        ) {
            window.minimumSize = Dimension(350, 600)
            App(rootFlowComponent)
        }
    }
}