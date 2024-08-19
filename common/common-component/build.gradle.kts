plugins {
    alias(libs.plugins.multiplatform)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":common:common-ui"))

            implementation(libs.decompose)
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            // implementation(libs.kotlin.test)
        }
    }
}