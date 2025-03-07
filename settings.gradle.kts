pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{ url = uri("https://devrepo.kakao.com/nexus/content/groups/public/") }
        maven{ url = uri("https://devrepo.kakao.com/nexus/repository/kakaomap-releases/") }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "TogetherPet"
include(":app")
include(":core:model")
include(":core:database")
include(":core:network")
include(":core:data")
include(":feature:Login")
include(":core:ui")
