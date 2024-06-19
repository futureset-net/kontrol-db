import net.futureset.kontroldb.DbDockerExtension

plugins {
    id("kontrol-db.database.setup")
    id("kontrol-db.docker")
}

extensions.configure<DbDockerExtension> {
    imageId.set("gvenzl/oracle-free:latest")
    envProperties.put("ORACLE_PASSWORD", "Th1sIsW0rking")
    internalToExternalPortMap.put(1521, 1526)
    waitForStartupLogMessage.set("DATABASE IS READY TO USE!")
}

description = "Oracle extensions for kontrol-db"

dependencies {
    api(libs.oracle)
}
