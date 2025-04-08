plugins {
    alias(libs.plugins.togetherpet.android.library)
}

android {
    namespace = "com.jnu.testing"
}

dependencies {
    implementation(libs.androidx.appcompat)
    debugImplementation(libs.androidx.fragment.testing)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.test.runner)
    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)
    implementation(libs.hilt.android.compiler)
}