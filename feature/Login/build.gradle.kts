plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.hilt)
}

android {
    namespace = "com.jnu.login"
    buildFeatures {
        viewBinding = true
    }
}

dependencies{
    implementation(projects.core.data)
    implementation("com.kakao.maps.open:android:2.11.9")
    implementation("com.kakao.sdk:v2-user:2.20.6")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}