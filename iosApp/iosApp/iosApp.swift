import UIKit
import ComposeApp

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    lazy var rootFlowComponent: RootFlowComponent = RootFlowComponent(
        componentContext: DefaultComponentContext(
            lifecycle: ApplicationLifecycle(),
            stateKeeper: nil,
            instanceKeeper: nil,
            backHandler: nil
        )
    )
    
    var window: UIWindow?
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        if let window = window {
            window.rootViewController = MainKt.MainViewController(root: rootFlowComponent)
            window.makeKeyAndVisible()
        }
        return true
    }
}
