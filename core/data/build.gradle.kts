plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.hilt)
    alias(libs.plugins.togetherpet.test)
}

android {
    namespace = "com.jnu.data"
}

dependencies {
    implementation("com.kakao.maps.open:android:2.11.9")
    implementation("com.kakao.sdk:v2-user:2.20.6")
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.core.database)
}