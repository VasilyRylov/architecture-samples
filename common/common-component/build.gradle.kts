plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)

            implementation(libs.decompose)
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
        }
    }
}
