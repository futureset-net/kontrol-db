rootProject.name = "kontrol-db"
include(":kontrol-db-core")
include(":kontrol-db-hsqldb")
include(":kontrol-db-sqlserver")
include(":kontrol-db-postgres")
include(":integrationTest")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
