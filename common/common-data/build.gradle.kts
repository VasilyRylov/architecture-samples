plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.ksp)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.common.commonDomain)
            implementation(projects.data.preferences)
            implementation(projects.data.database)
            implementation(libs.kotlinx.coroutines.core)
            api(libs.uuid)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
