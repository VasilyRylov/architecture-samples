plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

// ===============
// https://github.com/google/ksp/issues/567
// https://github.com/google/ksp/issues/965
dependencies {
    add("kspCommonMainMetadata", libs.visualfsm.compiler)
}

kotlin.sourceSets.commonMain {
    kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
}
// ===============

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        // ===============
        // https://github.com/google/ksp/issues/567
        // https://github.com/google/ksp/issues/965
        //all { kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin") }
        commonMain.dependencies {
            implementation(projects.common.commonDomain)
            implementation(projects.feature.auth.authDomain)
            implementation(projects.feature.todo.todoDomain)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)

            api(libs.visualfsm.core)
            api(libs.uuid)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        jvmTest.dependencies {
            implementation(libs.visualfsm.tools)
        }
    }

    // AS Iguana run on rebuild project :ui_common:testClasses but task not found
    task("testClasses")
}

// ===============
// https://github.com/google/ksp/issues/567
// https://github.com/google/ksp/issues/965
tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

