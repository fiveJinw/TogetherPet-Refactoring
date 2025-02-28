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
}