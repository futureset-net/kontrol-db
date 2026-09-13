
import net.futureset.kontroldb.DbDockerExtension

plugins {
    `dokka-convention`
    id("kontrol-db.database.setup")
    id("kontrol-db.docker")
}

extensions.configure<DbDockerExtension> {
    imageId.set("postgres@sha256:1090bc3a8ccfb0b55f78a494d76f8d603434f7e4553543d6e807bc7bd6bbd17f")
    envProperties.put("POSTGRES_USER", "SA")
    envProperties.put("POSTGRES_PASSWORD", "Th1sIsW0rking")
    internalToExternalPortMap.put(5432, 5432)
    waitForStartupLogMessage.set("database system is ready to accept connections")
}

description = "postgres extensions for kontrol-db"

dependencies {
    api(libs.postgres)
}
