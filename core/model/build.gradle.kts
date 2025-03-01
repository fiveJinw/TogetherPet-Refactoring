import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.togetherpet.android.library)
}

android {
    namespace = "com.jnu.model"
}

dependencies {
    implementation("com.kakao.maps.open:android:2.11.9")
}