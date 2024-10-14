import dev.iurysouza.modulegraph.Orientation

plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.room).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.modulegraph).apply(true)
}

moduleGraphConfig {
    readmePath.set("./README.MD")
    heading = "### Module Graph"
    orientation.set(Orientation.TOP_TO_BOTTOM)
}
