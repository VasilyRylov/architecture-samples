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
            implementation(project(":feature:root:root-ui"))
            implementation(project(":feature:root:root-domain"))

            implementation(project(":feature:auth:auth-component"))
            implementation(project(":feature:auth:auth-domain"))

            implementation(project(":feature:todo:todo-component"))
            implementation(project(":feature:todo:todo-domain"))

            implementation(project(":common:common-component"))
            implementation(project(":common:common-domain"))
            implementation(project(":common:common-ui"))
            implementation(project(":common:common-data"))

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
