plugins {
    alias(libs.plugins.togetherpet.android.library)
}

android {
    namespace = "com.jnu.ui"
}

dependencies{
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
}