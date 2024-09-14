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
            implementation(projects.feature.auth.authDomain)
            implementation(projects.common.commonDomain)
            implementation(projects.common.commonData)
            implementation(libs.kotlinx.coroutines.core)
            api(libs.uuid)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
