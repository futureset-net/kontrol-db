
import net.futureset.kontroldb.DbDockerExtension

plugins {
    id("kontrol-db.database.setup")
    id("kontrol-db.docker")
}

extensions.configure<DbDockerExtension> {
    imageId.set("postgres:latest")
    envProperties.put("POSTGRES_USER", "SA")
    envProperties.put("POSTGRES_PASSWORD", "Th1sIsW0rking")
    internalToExternalPortMap.put(5432, 5432)
    waitForStartupLogMessage.set("database system is ready to accept connections")
}

description = "postgres extensions for kontrol-db"

dependencies {
    api(libs.postgres)
}
