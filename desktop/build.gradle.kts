import org.jetbrains.compose.desktop.application.dsl.TargetFormat

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    alias(libs.plugins.composeMultiplatform)
}

group = "dev.sergiobelda.todometer"
version = "1.0"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
            }
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.common.core)
                implementation(projects.common.composeUi)
                implementation(projects.common.composeUiDesignsystem)
                implementation(projects.common.domain)
                implementation(projects.common.resources)
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "jvm"
            packageVersion = "1.0.0"
        }
    }
}
