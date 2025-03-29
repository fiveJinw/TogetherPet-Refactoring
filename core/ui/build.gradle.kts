plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.kakao)
}

android {
    namespace = "com.jnu.ui"
    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
}

dependencies{
    implementation(projects.core.database)
    implementation(projects.core.data)
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
}