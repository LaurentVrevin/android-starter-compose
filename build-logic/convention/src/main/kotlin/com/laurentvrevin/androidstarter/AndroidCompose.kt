package com.laurentvrevin.androidstarter

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs += listOf(
                "-opt-in=androidx.compose.foundation.style.ExperimentalFoundationStyleApi"
            )
        }
    }

    dependencies {
        // Hardcoded for now to bypass VersionCatalogsExtension issue
        add("implementation", platform("androidx.compose:compose-bom:2026.06.01"))
        add("androidTestImplementation", platform("androidx.compose:compose-bom:2026.06.01"))
    }
}
