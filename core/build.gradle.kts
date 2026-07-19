plugins {
    id("androidstarter.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.laurentvrevin.androidstarter.core"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}
