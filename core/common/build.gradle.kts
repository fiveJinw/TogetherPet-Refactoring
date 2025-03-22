plugins {
    alias(libs.plugins.togetherpet.android.library)
}

android {
    namespace = "com.jnu.common"
}

dependencies{
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}