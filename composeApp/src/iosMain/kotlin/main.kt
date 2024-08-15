import androidx.compose.ui.window.ComposeUIViewController
import io.github.vasilyrylov.archsample.App
import io.github.vasilyrylov.archsample.feature.root.root_component.RootComponent
import platform.UIKit.UIViewController

fun MainViewController(root: RootComponent): UIViewController = ComposeUIViewController {
    App(root)
}
