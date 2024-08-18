plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:todo:todo_component"))
            implementation(project(":feature:todo:todo_domain"))

            implementation(project(":feature:tab:tab_ui"))

            implementation(project(":common:common_component"))
            implementation(project(":common:common_domain"))
            implementation(project(":common:common_ui"))

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.koin.core)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
        }
        commonTest.dependencies {
            // implementation(libs.kotlin.test)
        }
    }
}
