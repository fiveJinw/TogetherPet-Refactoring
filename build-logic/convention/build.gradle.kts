plugins {
    `kotlin-dsl`
}

repositories{
    gradlePluginPortal()
    google()
}

dependencies{
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin{
    plugins{
        register("androidApplication"){
            id = "togetherpet.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }

    plugins{
        register("androidLibrary"){
            id = "togetherpet.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }

    plugins{
        register("hilt"){
            id = "togetherpet.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }

    plugins{
        register("androidRoom"){
            id = "togetherpet.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
    }

    plugins{
        register("test"){
            id = "togetherpet.test"
            implementationClass = "TestConventionPlugin"
        }
    }

    plugins{
        register("androidTest"){
            id = "togetherpet.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
    }

    plugins{
        register("kakao"){
            id = "togetherpet.kakao"
            implementationClass = "KakaoConventionPlugin"
        }
    }
}