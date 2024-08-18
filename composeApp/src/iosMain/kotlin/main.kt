import androidx.compose.ui.window.ComposeUIViewController
import io.github.vasilyrylov.archsample.ComposeApp
import io.github.vasilyrylov.archsample.feature.root.root_component.RootFlowComponent
import platform.UIKit.UIViewController

fun MainViewController(root: RootFlowComponent): UIViewController = ComposeUIViewController {
    ComposeApp(root)
}
