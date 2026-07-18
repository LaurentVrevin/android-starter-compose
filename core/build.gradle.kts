plugins {
    id("androidstarter.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.laurentvrevin.androidstarter.core"
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.ktor.client.core)
    implementation(libs.kotlinx.serialization.json)
    
    // Compose dependencies needed for UiState and Feedback
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)
}
