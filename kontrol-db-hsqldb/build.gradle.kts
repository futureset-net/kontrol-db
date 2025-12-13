plugins {
    `dokka-convention`
    id("kontrol-db.database.setup")
}

description = "hsqldb extensions for kontrol-db"

dependencies {
    api(libs.hsqldb)
    api(libs.hsqldb.tool)
}
