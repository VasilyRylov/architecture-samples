import com.google.devtools.ksp.gradle.KspTaskMetadata

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
            implementation(projects.feature.user.userData)
            implementation(projects.common.commonId)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlin.inject.runtime)
            api(libs.visualfsm.core)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        jvmTest.dependencies {
            implementation(libs.visualfsm.tools)
        }
    }
}

dependencies {
    kspCommonMainMetadata(libs.visualfsm.compiler)
}

kotlin.sourceSets.commonMain { tasks.withType<KspTaskMetadata> { kotlin.srcDir(destinationDirectory) } }
