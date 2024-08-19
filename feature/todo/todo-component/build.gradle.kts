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
            implementation(project(":feature:todo:todo-ui"))
            implementation(project(":feature:todo:todo-domain"))
            implementation(project(":feature:todo:todo-data"))

            implementation(project(":common:common-component"))
            implementation(project(":common:common-domain"))
            implementation(project(":common:common-ui"))

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
