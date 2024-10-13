plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.database)
            implementation(projects.common.commonId)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlin.inject.runtime)
            api(libs.uuid)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
