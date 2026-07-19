plugins {
    id("androidstarter.android.library.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.laurentvrevin.androidstarter.feature.sample"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":designsystem"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)

    implementation(libs.androidx.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
