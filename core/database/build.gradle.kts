import com.multi.module.convention.libs

plugins {
    alias(libs.plugins.togetherpet.android.library)
    alias(libs.plugins.togetherpet.android.room)
    alias(libs.plugins.togetherpet.hilt)
}

android {
    namespace = "com.jnu.database"
}


dependencies{
    implementation(projects.core.model)
    implementation("com.kakao.maps.open:android:2.11.9")
    implementation("com.kakao.sdk:v2-user:2.20.6")
}
