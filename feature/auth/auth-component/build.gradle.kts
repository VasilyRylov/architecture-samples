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
            implementation(projects.feature.auth.authUi)
            implementation(projects.feature.auth.authDomain)
            implementation(projects.feature.auth.authData)

            implementation(projects.common.commonComponent)
            implementation(projects.common.commonUi)
            implementation(projects.common.commonDomain)
            implementation(projects.common.commonData)

            implementation(libs.decompose)
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
