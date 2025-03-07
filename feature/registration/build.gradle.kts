plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.hilt)
}

android {
    namespace = "com.jnu.registration"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
}