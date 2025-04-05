plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.test)
}

android {
    namespace = "com.jnu.common"
}

dependencies{
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}