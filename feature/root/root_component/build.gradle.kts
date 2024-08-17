plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget()

    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":feature:root:root_ui"))
            implementation(project(":feature:root:root_domain"))

            implementation(project(":feature:auth:auth_component"))
            implementation(project(":feature:auth:auth_domain"))

            implementation(project(":feature:todo:todo_component"))
            implementation(project(":feature:todo:todo_domain"))

            implementation(project(":common:common_component"))
            implementation(project(":common:common_domain"))
            implementation(project(":common:common_ui"))
            implementation(project(":common:common_data"))
            implementation(project(":database"))

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
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(compose.uiTooling)
        }
    }
}

android {
    namespace = "io.github.vasilyrylov.archsample.feature.todo.todo_list.ui"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
