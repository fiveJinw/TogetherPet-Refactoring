import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.kakao)
}

android {
    namespace = "com.jnu.model"
}
