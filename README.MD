## Compose Multiplatform Application architecture samples

### Frameworks and libraries

- [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)
- [Decompose](https://github.com/arkivanov/Decompose)
- [kotlin-inject](https://github.com/evant/kotlin-inject)
  or [Koin](https://github.com/InsertKoinIO/koin) DI [variants](#variants-and-ready-status)
- [Multiplatform Settings](https://github.com/russhwolf/multiplatform-settings)
- [VisualFSM](https://github.com/Kontur-Mobile/VisualFSM)
- [Room Multiplatform](https://developer.android.com/kotlin/multiplatform/room)

### Key concepts

- FSM based MVI and MVVM+
- Navigation is abstracted from Decompose
- Decompose components are separated into own modules
- Business logic and UI modules do not depend on Component modules
- Multi-module DI, each module can use its own DI framework
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
    :common:common-component["common-component"]
    :common:common-id["common-id"]
    :common:common-ui["common-ui"]
  end
  subgraph :data
    :data:database["database"]
    :data:preferences["preferences"]
  end
  subgraph :feature:auth
    :feature:auth:auth-ui["auth-ui"]
    :feature:auth:auth-domain["auth-domain"]
    :feature:auth:auth-component["auth-component"]
  end
  subgraph :feature:root
    :feature:root:root-domain["root-domain"]
    :feature:root:root-component["root-component"]
  end
  subgraph :feature:todo
    :feature:todo:todo-component["todo-component"]
    :feature:todo:todo-ui["todo-ui"]
    :feature:todo:todo-domain["todo-domain"]
    :feature:todo:todo-data["todo-data"]
  end
  subgraph :feature:user
    :feature:user:user-data["user-data"]
  end
  :feature:todo:todo-component --> :feature:todo:todo-ui
  :feature:todo:todo-component --> :feature:todo:todo-domain
  :feature:todo:todo-component --> :feature:todo:todo-data
  :feature:todo:todo-component --> :feature:user:user-data
  :feature:todo:todo-component --> :common:common-component
  :feature:todo:todo-component --> :common:common-id
  :feature:todo:todo-component --> :data:database
  :feature:todo:todo-component --> :data:preferences
  :feature:auth:auth-ui --> :feature:auth:auth-domain
  :feature:auth:auth-ui --> :common:common-ui
  :feature:root:root-domain --> :feature:user:user-data
  :feature:root:root-domain --> :common:common-id
  :feature:todo:todo-ui --> :feature:todo:todo-data
  :feature:todo:todo-ui --> :common:common-id
  :feature:todo:todo-ui --> :common:common-ui
  :composeApp --> :feature:root:root-component
  :composeApp --> :common:common-component
  :composeApp --> :common:common-ui
  :composeApp --> :data:database
  :composeApp --> :data:preferences
  :feature:user:user-data --> :common:common-id
  :feature:user:user-data --> :data:database
  :feature:user:user-data --> :data:preferences
  :feature:auth:auth-component --> :feature:auth:auth-ui
  :feature:auth:auth-component --> :feature:auth:auth-domain
  :feature:auth:auth-component --> :feature:user:user-data
  :feature:auth:auth-component --> :common:common-component
  :feature:auth:auth-component --> :data:database
  :feature:auth:auth-component --> :data:preferences
  :feature:todo:todo-data --> :common:common-id
  :feature:todo:todo-data --> :data:database
  :feature:todo:todo-domain --> :feature:todo:todo-data
  :feature:todo:todo-domain --> :feature:user:user-data
  :feature:todo:todo-domain --> :common:common-id
  :feature:root:root-component --> :feature:root:root-domain
  :feature:root:root-component --> :feature:user:user-data
  :feature:root:root-component --> :feature:auth:auth-component
  :feature:root:root-component --> :feature:todo:todo-component
  :feature:root:root-component --> :common:common-component
  :feature:root:root-component --> :data:database
  :feature:root:root-component --> :data:preferences
  :feature:auth:auth-domain --> :feature:user:user-data
  :feature:auth:auth-domain --> :common:common-id
```
### Variants and ready status

| Sample                                                                                                 | Description                                                                  | Ready status |
|--------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|--------------|
| [fsm mvi, mvvm plus, kotlin-inject](https://github.com/VasilyRylov/architecture-samples/tree/main)     | Fsm-based MVI(root and auth features), MVVM+(todo feature), kotlin-inject DI | Ready        |
| [fsm mvi, mvvm plus, Koin](https://github.com/VasilyRylov/architecture-samples/tree/mvi-and-mvvm-koin) | Fsm-based MVI(root and auth features), MVVM+(todo feature), Koin DI          | Ready        |

### Root module FSM

<img src="doc/img/rootfsm.png" alt="graph" width="280"/>

### Auth module FSM

<img src="doc/img/authfsm.png" alt="graph"/>

### Screenshots

<table width="100%">
  <tr>
  <td width="33%">Login</td>
  <td width="33%">Registration</td>
  <td width="33%">ConfirmationRequested</td>
  </tr>
  <tr>
  <td width="33%"><img src="./doc/img/screen/auth1.png"/></td>
  <td width="33%"><img src="./doc/img/screen/auth2.png"/></td>
  <td width="33%"><img src="./doc/img/screen/auth3.png"/></td>
  </tr>
  <tr>
  <td width="33%">Add new todo</td>
  <td width="33%">Todo list</td>
  <td width="33%">Todo details</td>
  </tr>
  <tr>
  <td width="33%"><img src="./doc/img/screen/todo1.png"/></td>
  <td width="33%"><img src="./doc/img/screen/todo3.png"/></td>
  <td width="33%"><img src="./doc/img/screen/todo2.png"/></td>
  </tr>
</table>

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
- Or
  use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile)
  for Android Studio
  Run iOS simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`