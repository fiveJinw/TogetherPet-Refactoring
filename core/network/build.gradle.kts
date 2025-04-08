import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.hilt)
}


android {
    namespace = "com.jnu.network"

    defaultConfig {
        buildConfigField(
            "String",
            "KAKAO_NATIVE_APP_KEY",
            gradleLocalProperties(rootDir, providers).getProperty("KAKAO_NATIVE_APP_KEY")
        )
        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] =
            gradleLocalProperties(rootDir, providers).getProperty("KAKAO_NATIVE_APP_KEY")

        buildConfigField(
            "String",
            "BASE_URL",
            gradleLocalProperties(rootDir, providers).getProperty("BASE_URL")
        )
        buildConfigField(
            "String",
            "KAKAO_BASE_URL",
            gradleLocalProperties(rootDir, providers).getProperty("KAKAO_BASE_URL")
        )
        buildConfigField(
            "String",
            "KAKAO_LOCAL_API_KEY",
            gradleLocalProperties(rootDir, providers).getProperty("KAKAO_LOCAL_API_KEY")
        )
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies{
    implementation("com.kakao.maps.open:android:2.11.9")
    implementation("com.kakao.sdk:v2-user:2.20.6")
    implementation(projects.core.model)
}
