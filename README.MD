# Work in progress
## Compose Multiplatform Application architecture sample

### Frameworks and libraries

- Compose Multiplatform
- Decompose
- VisualFSM
- Koin DI
- Room Multiplatform

### Key concepts
- FSM based
- Navigation is abstracted from Decompose
- Decompose components are separated into own modules
- Business logic and UI modules do not depend on Component modules
- Component modules are only responsible for routers implementation, DI setup and lifecycle.
- Features are allocated into separate groups of modules,
  each of which is divided into modules with business logic, UI and components with a life cycle.

### Module Graph

```mermaid
%%{
  init: {
    'theme': 'neutral'
  }
}%%

graph TB
  subgraph :common
    :common:common_ui["common_ui"]
    :common:common_component["common_component"]
  end
  subgraph :feature:auth
    :feature:auth:auth_domain["auth_domain"]
    :feature:auth:auth_ui["auth_ui"]
    :feature:auth:auth_component["auth_component"]
  end
  subgraph :feature:root
    :feature:root:root_domain["root_domain"]
    :feature:root:root_component["root_component"]
    :feature:root:root_ui["root_ui"]
  end
  subgraph :feature:todo
    :feature:todo:todo_component["todo_component"]
    :feature:todo:todo_ui["todo_ui"]
    :feature:todo:todo_domain["todo_domain"]
  end
  :feature:root:root_domain --> :feature:auth:auth_domain
  :feature:auth:auth_ui --> :common:common_ui
  :feature:auth:auth_ui --> :feature:auth:auth_domain
  :composeApp --> :feature:root:root_component
  :feature:root:root_ui --> :common:common_ui
  :feature:root:root_ui --> :feature:root:root_domain
  :feature:auth:auth_component --> :feature:auth:auth_ui
  :feature:auth:auth_component --> :feature:auth:auth_domain
  :feature:auth:auth_component --> :common:common_component
  :feature:auth:auth_component --> :common:common_ui
  :feature:todo:todo_component --> :feature:todo:todo_ui
  :feature:todo:todo_component --> :feature:todo:todo_domain
  :feature:todo:todo_ui --> :common:common_ui
  :feature:todo:todo_ui --> :feature:todo:todo_domain
  :common:common_component --> :common:common_ui
  :feature:root:root_component --> :feature:root:root_ui
  :feature:root:root_component --> :feature:root:root_domain
  :feature:root:root_component --> :feature:auth:auth_domain
  :feature:root:root_component --> :feature:auth:auth_component
  :feature:root:root_component --> :feature:todo:todo_component
  :feature:root:root_component --> :common:common_component
  :feature:root:root_component --> :common:common_ui
```

### Root module FSM

<img src="doc/img/rootfsm.png" alt="graph" width="280"/>

### Auth module FSM

<img src="doc/img/authfsm.png" alt="graph"/>

### Before running!

- check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
- install JDK 17 or higher on your machine

#### Android

To run the application on android device/emulator:

- open project in Android Studio and run imported android run configuration

#### Desktop

Run the desktop application: `./gradlew :composeApp:run`

#### iOS

To run the application on iPhone device/simulator:

- Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
- Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio
  Run iOS simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`