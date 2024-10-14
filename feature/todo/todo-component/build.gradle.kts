import com.google.devtools.ksp.gradle.KspTaskMetadata

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.ksp)
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
            implementation(projects.feature.todo.todoUi)
            implementation(projects.feature.todo.todoDomain)
            implementation(projects.feature.todo.todoData)

            implementation(projects.feature.user.userData)

            implementation(projects.common.commonComponent)
            implementation(projects.common.commonId)

            implementation(projects.data.database)
            implementation(projects.data.preferences)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.kotlin.inject.runtime)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

dependencies {
    kspCommonMainMetadata(libs.kotlin.inject.compiler)
}

kotlin.sourceSets.commonMain { tasks.withType<KspTaskMetadata> { kotlin.srcDir(destinationDirectory) } }
