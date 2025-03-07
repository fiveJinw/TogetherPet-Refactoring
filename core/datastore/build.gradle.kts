plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.hilt)
}

android {
    namespace = "com.jnu.datastore"
}

dependencies {
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}