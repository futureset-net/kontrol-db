import net.futureset.kontroldb.DbDockerExtension

plugins {
    `dokka-convention`
    id("kontrol-db.database.setup")
    id("kontrol-db.docker")
}

extensions.configure<DbDockerExtension> {
    imageId.set("gvenzl/oracle-free@sha256:7ed34d0ade89c91a553c9cbe9d42457c758fa29a762aaaa3137541ff30a2d1cb")
    envProperties.put("ORACLE_PASSWORD", "Th1sIsW0rking")
    internalToExternalPortMap.put(1521, 1526)
    waitForStartupLogMessage.set("DATABASE IS READY TO USE!")
}

description = "Oracle extensions for kontrol-db"

dependencies {
    api(libs.oracle)
}
