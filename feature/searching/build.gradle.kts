plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.hilt)
    alias(libs.plugins.togetherpet.kakao)
    alias(libs.plugins.togetherpet.test)
}

android {
    namespace = "com.jnu.searching"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.ui)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.core.designsystem)
    implementation("androidx.fragment:fragment-ktx:1.8.5")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.8.1")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.8.1")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
}