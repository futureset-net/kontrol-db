plugins {
    id("kontrol-db.database.setup")
    id("org.jetbrains.dokka")
}

description = "hsqldb extensions for kontrol-db"

dependencies {
    api(libs.hsqldb)
    api(libs.hsqldb.tool)
}
