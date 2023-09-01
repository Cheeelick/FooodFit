pluginManagement {
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
    }
}

rootProject.name = "FooodFit"
include(":app")
include(":features-2")
include(":features-2:sign-up")
include(":features-2:sign-in")
include(":features-2:home")
include(":features-2:food")
include(":features-2:main")
