plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
}

kotlin {
    androidTarget()

    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.commonUi)
            implementation(projects.common.commonDomain)
            implementation(projects.feature.todo.todoDomain)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(libs.kotlin.inject.runtime)
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

compose.resources {
    generateResClass = never
}

android {
    namespace = "io.github.vasilyrylov.archsample.feature.todo.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
