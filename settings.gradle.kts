rootProject.name = "kontrol-db"
include(":kontrol-db-core")
include(":kontrol-db-hsqldb")
include(":kontrol-db-sqlserver")
include(":kontrol-db-postgres")
include(":kontrol-db-oracle")
include(":integrationTest")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        id("org.jetbrains.kotlin.jvm") version "2.3.0"
    }
}
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
