plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.hilt)
    alias(libs.plugins.togetherpet.kakao)
}

android {
    namespace = "com.jnu.walking"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation("androidx.fragment:fragment-ktx:1.8.5")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.google.android.gms:play-services-location:21.3.0")
}