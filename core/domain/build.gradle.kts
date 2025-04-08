plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.hilt)
}

android {
    namespace = "com.jnu.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
}