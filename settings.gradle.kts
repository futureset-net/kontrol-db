rootProject.name = "kontrol-db"
include(":core")
include(":hsqldb")
include(":sqlserver")
include(":integrationTest")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
