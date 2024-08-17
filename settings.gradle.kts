rootProject.name = "ArchSample"

pluginManagement {
    repositories {
        google {
            content { 
              	includeGroupByRegex("com\\.android.*")
              	includeGroupByRegex("com\\.google.*")
              	includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            content { 
              	includeGroupByRegex("com\\.android.*")
              	includeGroupByRegex("com\\.google.*")
              	includeGroupByRegex("androidx.*")
                includeGroupByRegex("android.*")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}
include(":composeApp")
include(":common:common_ui")
include(":common:common_component")
include(":common:common_domain")
include(":common:common_data")

include(":feature:root:root_domain")
include(":feature:root:root_ui")
include(":feature:root:root_component")

include(":feature:auth:auth_domain")
include(":feature:auth:auth_ui")
include(":feature:auth:auth_component")


include(":feature:todo:todo_ui")
include(":feature:todo:todo_domain")
include(":feature:todo:todo_component")
include(":feature:todo:todo_data")
